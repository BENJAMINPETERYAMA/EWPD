/*
 * BusinessObjectManagerImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.HistoryLogManagerFactory;
import com.wellpoint.wpd.business.framework.bo.MetaObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.business.framework.bo.StateFactoryImpl;
import com.wellpoint.wpd.common.bo.Activity;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.InformationElement;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResult;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.bo.Transition;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.exception.bo.BusinessObjectNotFoundException;
import com.wellpoint.wpd.common.framework.exception.bo.CheckedOutByAnotherUserException;
import com.wellpoint.wpd.common.framework.exception.bo.CheckedOutBySameUserException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalCheckoutRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalCopyTargetException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalPublishRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalVersionCancelCheckoutException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalVersionExtendCheckoutException;
import com.wellpoint.wpd.common.framework.exception.bo.IllegalVersionPersistRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.InvalidDeleteRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.InvalidPersistRequestException;
import com.wellpoint.wpd.common.framework.exception.bo.InvalidStatusCancelCheckoutException;
import com.wellpoint.wpd.common.framework.exception.bo.InvalidStatusExtendCheckoutException;
import com.wellpoint.wpd.common.framework.exception.bo.LockNotFoundException;
import com.wellpoint.wpd.common.framework.exception.bo.StatusChangeValidationException;
import com.wellpoint.wpd.common.framework.exception.lock.LockException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedByAnotherUserException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedBySameUserException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessObjectManagerImpl.java 30821 2007-08-17 15:44:24Z
 *          u12218 $
 */
public class BusinessObjectManagerImpl implements BusinessObjectManager {

	private String RETRIEVE_LATEST_VERSION_METHOD = "retrieveLatestVersion";

	private String RETRIEVE_LATEST_VERSION_NUMBER_METHOD = "retrieveLatestVersionNumber";

	private String RETRIEVE_METHOD = "retrieve";

	private String DELETE_LATEST_VERSION_METHOD = "deleteLatestVersion";

	private String PERSIST_METHOD = "persist";

	private String PERSIST_TIME_STAMP_METHOD = "persistTimeStamp";

	private String LOCATE_METHOD = "locate";

	private String COPY_METHOD = "copy";

	private String COPY_CHECKOUT_METHOD = "copyForCheckOut";

	private String DELETE_METHOD = "delete";

	private String RETRIEVE_ALL_VERSION_METHOD = "retrieveAllVersions";

	private String CHANGE_IDENTITY = "changeIdentity";

	private static final String BUILDING = "BUILDING";

	public static final String KEY_ATTRIBUTE_SEPARATOR = "~";

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#checkOut(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 *  
	 */
	public BusinessObject checkOut(BusinessObject object, User user)
			throws WPDException {

		if (object == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + object + "user ="
							+ user);
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObj = metaObjectFactory.getMetaObject(object);
		int duration = metaObj.getCheckOutDuration();
		return checkOut(object, user, duration);

	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#checkOut(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User, int)
	 * @param object
	 * @param user
	 * @param duration
	 * @return
	 * @throws WPDException
	 * @throws CheckedOutBySameUserException
	 */
	public BusinessObject checkOut(BusinessObject object, User user,
			int duration) throws WPDException, CheckedOutBySameUserException {

		if (object == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + object + "user ="
							+ user);
		}
		if (duration <= 0) {
			throw new IllegalArgumentException("Invalid duration ( " + duration
					+ " ) passed  ");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObj = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObj.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		Object builderObj = getBuilderObject(metaObj, object);
		BusinessObject retrivedObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		String moduleName = metaObj.getModule();
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = retrivedObject.getVersion();

		if (orgVersionAttrValue != -1) {
			if (orgVersionAttrValue != reterviedVersionAttrValue) {
				List params = new ArrayList();
				params.add(object);
				IllegalCheckoutRequestException illegalCheckoutRequestException = new IllegalCheckoutRequestException(
						"The version specified in object for check out is not the latest one",
						params, null);
				Logger.logException(illegalCheckoutRequestException);
				throw illegalCheckoutRequestException;
			}
		}
		new StateManager().validate(retrivedObject, user,
				StateFactory.STATUS_CHECKED_OUT);
		boolean lock = false;
		LockManager lockMngr = new LockManager();
		boolean lockExpired = false;
		try {
			lockExpired = lockMngr.isLockExpired(object);
		} catch (LockException le) {
			//nothing to do; LockException thrown from isLockExpired if lock is
			// not present.
		}
		try {
			lock = lockMngr.lock(object, user, duration);
		} catch (LockedBySameUserException lockedBySameUserException) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new CheckedOutBySameUserException(
					"The object is already checked out for the user, "
							+ user.getUserId(), params,
					lockedBySameUserException);
			Logger.logException(severeException);
			throw severeException;
		} catch (LockedByAnotherUserException lockedByAnotherUserException) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new CheckedOutByAnotherUserException(
					"The Object is already checked out/ Locked by another user",
					params, lockedByAnotherUserException);
			Logger.logException(severeException);
			throw severeException;
		}
		if (lockExpired && lock) {
			return object;
		}
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		Object latestVersionNumber = null;
		try {
			latestVersionNumber = invokeMethod(builderObj,
					RETRIEVE_LATEST_VERSION_NUMBER_METHOD, new Class[] { object
							.getClass() }, new Object[] { object });
		} catch (Exception e) {
			try {
				lockMngr.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new SevereException(
						"The lock removal failed after check out failed. Invalid entry in Lock table",
						params, lockException);
				Logger.logException(se);
			}
			List params = new ArrayList();
			params.add(object);
			SevereException se1 = new SevereException(
					"Object cannot be checked out", params, e.getCause());
			Logger.logException(se1);
			throw se1;
		}

		int currentVersion = ((Integer) latestVersionNumber).intValue();
		Boolean inserOrUpdateFlag= true;
		if(object instanceof  Contract)
		{
			Contract contract= (Contract)object;
			if("continueProduct".equals(contract.getProductStatus())) {
			retrivedObject.setVersion(currentVersion);
			inserOrUpdateFlag=false;
		}
			else {
				retrivedObject.setVersion(++currentVersion);
				} 
		} else {
			retrivedObject.setVersion(++currentVersion);
		}
		
		retrivedObject.setStatus(StateFactory.STATUS_CHECKED_OUT);

		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { retrivedObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { retrivedObject, audit, inserOrUpdateFlag });
		} catch (Exception e) {
			try {
				lockMngr.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new SevereException(
						"The lock removal failed after check out failed. Invalid entry in Lock table",
						params, lockException);
				Logger.logException(se);
			}
			List params = new ArrayList();
			params.add(object);
			SevereException se1 = new SevereException(
					"Object cannot be checked out", params, e.getCause());
			Logger.logException(se1);
			throw se1;
		}

		try {
			invokeMethod(builderObj, COPY_CHECKOUT_METHOD,
					new Class[] { object.getClass(), retrivedObject.getClass(),
							Audit.class }, new Object[] { object,
							retrivedObject, audit });
		} catch (Exception e) {
			try {
				invokeMethod(builderObj, DELETE_LATEST_VERSION_METHOD,
						new Class[] { retrivedObject.getClass(), Audit.class },
						new Object[] { retrivedObject, audit });
			} catch (Exception e1) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new SevereException(
						"Deleting the new version inserted as part of check out failed after check out failure.Invalide entry in db",
						params, e1.getCause());
				Logger.logException(se);
			}
			try {
				lockMngr.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new SevereException(
						"The lock removal failed after check out failed. Invalid entry in Lock table",
						params, lockException);
				Logger.logException(se);
			}

			List params = new ArrayList();
			params.add(object);
			SevereException se1 = new SevereException("Check out failed.",
					params, e.getCause());
			Logger.logException(se1);
			throw se1;
		}
		setState(retrivedObject, user);
		return retrivedObject;
	}

	/**
	 * 
	 * @param object
	 * @param user
	 * @throws WPDException
	 */
	private void setState(BusinessObject object, User user) throws WPDException {
		StateFactory stateFactory = (StateFactory) ObjectFactory
				.getFactory(ObjectFactory.STATE);
		State reterivedState = stateFactory.getState(object, user);
		object.setState(reterivedState);
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#assign(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param admin
	 * @param user
	 * @return
	 */
	public boolean assign(BusinessObject object, User admin, User user) {
		// your code here
		return false;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#checkIn(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean checkIn(BusinessObject object, User user)
			throws WPDException {
		if (object == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + object + "user ="
							+ user);
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObj = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObj.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		LockManager lockMngr = new LockManager();
		String moduleName = metaObj.getModule();
		String status = object.getStatus();
		if (!lockMngr.isLocked(object, user)) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new LockNotFoundException(
					"Check in failed. The object is not " + ""
							+ "locked for the user ," + user.getUserId(),
					params, null);
			Logger.logException(severeException);
			throw severeException;
		}

		Object builderObj = getBuilderObject(metaObj, object);
		BusinessObject retrievedObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		try {
			new StateManager().validate(retrievedObject, user,
					StateFactory.STATUS_CHECKED_IN);
		} catch (SecurityException e) {

			if (!user.isAuthorized(moduleName, StateFactory.CHECKOUT_TASK)
					&& !user.isAuthorized(moduleName, StateFactory.CREATE_TASK)) {
				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.CHECKIN_TASK);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;

			}
		}
		boolean fromBuilding = retrievedObject.getVersion() == 0
				&& StateFactory.STATUS_BUILDING.equals(retrievedObject
						.getStatus());
		boolean fromCheckeOut = retrievedObject.getVersion() >= 1
				&& StateFactory.STATUS_CHECKED_OUT.equals(retrievedObject
						.getStatus());

		Audit audit = null;
		if (fromBuilding || fromCheckeOut) {
			retrievedObject.setStatus(StateFactory.STATUS_CHECKED_IN);
			if (fromBuilding) {
				retrievedObject.setVersion(1);
			}
			AuditFactory auditFactory = (AuditFactory) ObjectFactory
					.getFactory(ObjectFactory.AUDIT);
			audit = auditFactory.getAudit(user);

			try {
				invokeMethod(builderObj, PERSIST_METHOD,
						new Class[] { retrievedObject.getClass(), Audit.class,
								boolean.class },
						new Object[] { retrievedObject, audit,
								Boolean.valueOf(false) });

			} catch (Exception e) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new SevereException("Check In failed.",
						params, e.getCause());
				Logger.logException(se);
				throw se;
			}

		} else {
			// Invalid check in request
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Check In failed. Invalid check in request", params, null);
			Logger.logException(se);
			throw se;
		}

		try {
			lockMngr.unlock(object, user);
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			Logger
					.logException(new SevereException(
							"Removing the lock failed .Invalid record created in the DB!!",
							params, lockException));
		}

		object.setStatus(StateFactory.STATUS_CHECKED_IN);
		if (fromBuilding) {
			object.setVersion(1);
		}
		setState(object, user);
		checkLog(object, status, audit);
		return true;

	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#copy(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param sourceObj
	 * @param targetObj
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean copy(BusinessObject sourceObj, BusinessObject targetObj,
			User user) throws WPDException {
		return copy(sourceObj, targetObj, user, null);
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#cancelCheckOut(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean cancelCheckOut(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null parameter found. busObj - " + object + ".user - "
							+ user + ".");
		}
		MetaObjectFactory factory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = factory.getMetaObject(object);

		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);

		boolean lock = false;
		LockManager lockManager = new LockManager();
		if (!lockManager.isLocked(object, user)) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new LockNotFoundException(
					"The Lock is not present or expired for user,"
							+ user.getUserId(), params, null);
			Logger.logException(severeException);
			throw severeException;
		}
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject retrievedBusObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		String status = retrievedBusObject.getStatus();
		if (!StateFactory.STATUS_CHECKED_OUT.equals(status)) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new InvalidStatusCancelCheckoutException(
					"The object is found to be not checked out", params, null);
			Logger.logException(se);
			throw se;
		}
		int originalVersionNumber = object.getVersion();
		int retrievedVersionNumber = retrievedBusObject.getVersion();

		if (originalVersionNumber != -1
				&& retrievedVersionNumber != originalVersionNumber) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalVersionCancelCheckoutException(
					"Operation failed. Object is not the latest one", params,
					null);
			Logger.logException(se);
			throw se;
		}
		Object builderObject = getBuilderObject(metaObject, object);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		try {
			invokeMethod(builderObject, DELETE_LATEST_VERSION_METHOD,
					new Class[] { retrievedBusObject.getClass(), Audit.class },
					new Object[] { retrievedBusObject, audit });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException("Cancel checkout failed",
					params, e.getCause());
			Logger.logException(se);
			throw se;
		}
		try {
			lockManager.unlock(object, user);
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			Logger.logException(new SevereException(
					"Object cannot be unlocked.Invalid entry in lock table",
					params, lockException));
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#extendCheckout(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User, int)
	 * @param object
	 * @param user
	 * @param duration
	 * @return
	 * @throws WPDException
	 */
	public boolean extendCheckout(BusinessObject object, User user, int duration)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null parameter found. source - " + object + ".user - "
							+ user + ".");
		}
		if (duration <= 0) {
			throw new IllegalArgumentException(
					"Inavalid duration has been passes" + duration);
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		boolean lock = false;

		LockManager lockManager = new LockManager();

		/*
		 * Anil : lockManager.isLocked(object, user); will be easier.
		 */
		try {
			lock = lockManager.lock(object, user, metaObject
					.getCheckOutDuration());
		} catch (LockedByAnotherUserException lockedByAnotherUserException) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new SevereException(
					"The object is already Locked by the Another user "
							+ user.getUserId(), params,
					lockedByAnotherUserException);
			Logger.logException(severeException);
			throw severeException;
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			SevereException severeException = new SevereException(
					"The Lock is not present or expired for user,"
							+ user.getUserId(), params, lockException);
			Logger.logException(severeException);
			throw severeException;
		}
		Object builderObj = getBuilderObject(metaObject, object);
		Object businessObject = null;
		try {
			businessObject = invokeMethod(builderObj,
					RETRIEVE_LATEST_VERSION_METHOD, new Class[] { object
							.getClass() }, new Object[] { object });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Cannot extend the check out for object", params, e
							.getCause());
			Logger.logException(se);
			throw se;
		}
		if (businessObject == null) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new BusinessObjectNotFoundException(
					"The business object can not be retrieved", params, null);
			Logger.logException(se);
			throw se;
		}

		Object orgVersionAttrValue = ReflectionUtil
				.getValueOfBusinessAttributeByReflection(object,
						BusinessObject.BUSINESS_OBJECT_VERSION_FIELD_NAME);

		Object reterviedVersionAttrValue = ReflectionUtil
				.getValueOfBusinessAttributeByReflection(businessObject,
						BusinessObject.BUSINESS_OBJECT_VERSION_FIELD_NAME);
		if (orgVersionAttrValue != null) {
			if (!orgVersionAttrValue.equals(reterviedVersionAttrValue)) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new IllegalVersionExtendCheckoutException(
						"The version specified in object for check out is not the latest one",
						params, null);
				Logger.logException(se);
				throw se;
			}
		}
		Object state = ReflectionUtil.getValueOfBusinessAttributeByReflection(
				businessObject,
				BusinessObject.BUSINESS_OBJECT_VERSION_FIELD_NAME);
		if (!state.equals("")) { //checking whether state is not CHECKED_OUT
			List params = new ArrayList();
			params.add(object);
			SevereException se = new InvalidStatusExtendCheckoutException(
					"The object is not checked out", params, null);
			Logger.logException(se);
			throw se;
		}
		//extending the lock for a user for specifed duration
		lockManager.lock(object, user, duration);

		return true;
	}

	/**
	 * 
	 * @param object
	 * @param admin
	 * @param user
	 * @param duration
	 * @return
	 */
	public boolean extendCheckout(BusinessObject object, User admin, User user,
			int duration) {

		// your code here
		return false;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#publish(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean publish(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for object-" + object + "And user"
							+ user + ".");
		}
		String status = object.getStatus();
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String builderClassName = metaObject.getBuilderClassName();

		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject retrievedBusObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		StateManager manager = new StateManager();
		String moduleName = metaObject.getModule();
		manager.validate(retrievedBusObject, user,
				StateFactory.STATUS_PUBLISHED);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = retrievedBusObject.getVersion();

		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalPublishRequestException(
					"The version specified in object for publish is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}

		boolean lock = false;
		LockManager lockManager = new LockManager();
		try {
			lock = lockManager.lock(object, user, metaObject
					.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "Publish
			 * operation failed. Lock can not be acquired for the user ," +
			 * user.getUserId(), params, lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		retrievedBusObject.setStatus(StateFactory.STATUS_PUBLISHED);
		object.setStatus(StateFactory.STATUS_PUBLISHED);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(builderObj, PERSIST_METHOD,
					new Class[] { retrievedBusObject.getClass(), Audit.class,
							boolean.class }, new Object[] { retrievedBusObject,
							audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be published", params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger
						.logException(new SevereException(
								"Object cannot be unlocked. Invalid entry in Lock table",
								params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		checkLog(object, status, audit);
		//retreiveLog(object,actions);
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#delete(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean delete(BusinessObject object, User user) throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		try {
			validateTask(object, user, StateFactory.DELETE_TASK);
		} catch (WPDException exception) {
			List params = new ArrayList();
			params.add(object);
			params.add(StateFactory.DELETE_TASK);
			params.add(object.getStatus());
			SecurityException se = new SecurityException(
					"User is not authorized for this task", params, null);

			throw se;
		}
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersion = object.getVersion();
		int latestVersion = latestObject.getVersion();
		if (orgVersion != -1 && latestVersion != orgVersion) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new InvalidDeleteRequestException(
					"The version specified in object for delete is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}
		Object status = latestObject.getStatus();

		Object latestVersionNumber = null;
		try {
			latestVersionNumber = invokeMethod(builderObj,
					RETRIEVE_LATEST_VERSION_NUMBER_METHOD, new Class[] { object
							.getClass() }, new Object[] { object });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se1 = new SevereException(
					"Object cannot be deleted. Getting the latest version number for the object failed",
					params, e.getCause());
			Logger.logException(se1);
			throw se1;
		}
		int currentVersion = ((Integer) latestVersionNumber).intValue();
		/*
		 * Getting latest version number would be a temporary fix.If the objects
		 * those are marked for deletion got purged,this wont work.
		 */
		//Check if the object can be destroyed or marked for deletion
		boolean canbeDestroyed = false;
		boolean canbeMarkedForDeletion = true;
		if (StateFactory.STATUS_BUILDING.equals(status)) {
			canbeDestroyed = true;
		} else {
			if (currentVersion == 1
					&& (StateFactory.STATUS_OBJECT_TRANSFERRED.equals(status)
							|| StateFactory.STATUS_SCHEDULED_FOR_TEST
									.equals(status)
							|| StateFactory.STATUS_TEST_FAILED.equals(status)
							|| StateFactory.STATUS_TEST_SUCCESSFUL
									.equals(status) || StateFactory.STATUS_CHECKED_IN
							.equals(status))) {
				canbeDestroyed = true;
			} else {
				// Need to verify whether it can be trasitioned to
				// MARKED_FOR_DELETION
				try {
					new StateManager().validate(latestObject, user,
							StateFactory.STATUS_MARKED_FOR_DELETION);
				} catch (WPDException e) {
					canbeMarkedForDeletion = false;
					throw e;
				}

			}
		}

		if (canbeDestroyed || canbeMarkedForDeletion) {
			//continue to lock and do the appropriate delete operation
		} else {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new InvalidDeleteRequestException(
					"Object cannot be destroyed or deleted", params, null);
			Logger.logException(se);
			throw se;
		}

		//Lock the business object
		boolean lock = false;
		LockManager lockManager = new LockManager();
		try {
			lock = lockManager.lock(object, user, metaObject
					.getCheckOutDuration());
		} catch (LockedBySameUserException lockedBySameUserException) {

		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException se = new SevereException( "The Lock can not be
			 * acquired for the object for the delete operation" +
			 * user.getUserId(), params, lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}

		boolean objectDestroyed = false;
		boolean markedAsDeleted = false;
		try {
			if (canbeDestroyed) {
				//Remove the object
				Object builderObject = getBuilderObject(metaObject, object);
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(user);
				try {
					invokeMethod(builderObject, DELETE_LATEST_VERSION_METHOD,
							new Class[] { object.getClass(), Audit.class },
							new Object[] { object, audit });
				} catch (Exception e) {
					List params = new ArrayList();
					params.add(object);
					SevereException se = new SevereException(
							"Object cannot be deleted", params, e.getCause());
					Logger.logException(se);
					throw se;
				}
				objectDestroyed = true;
			} else {
				if (canbeMarkedForDeletion) {
					latestObject
							.setStatus(StateFactory.STATUS_MARKED_FOR_DELETION);
					object.setStatus(StateFactory.STATUS_MARKED_FOR_DELETION);
					AuditFactory auditFactory = (AuditFactory) ObjectFactory
							.getFactory(ObjectFactory.AUDIT);
					Audit audit = auditFactory.getAudit(user);
					try {
						invokeMethod(builderObj, PERSIST_METHOD, new Class[] {
								latestObject.getClass(), Audit.class,
								boolean.class }, new Object[] { latestObject,
								audit, Boolean.valueOf(false) });
					} catch (Exception e) {
						List params = new ArrayList();
						params.add(object);
						SevereException se = new SevereException(
								"Object cannot be deleted", params, e
										.getCause());
						Logger.logException(se);
						throw se;
					}
					markedAsDeleted = true;
				}
			}
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be destroyed or deleted", params, e);
			Logger.logException(se);
			throw se;
		} finally {
			//unlock the business object
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger
						.logException(new SevereException(
								"Object cannot be unlocked during the delete operation",
								params, lockException));
			}
			if (markedAsDeleted)
				setState(object, user);
		}

		//Return the resultant status
		if (objectDestroyed || markedAsDeleted)
			return true;
		else {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be destroyed or deleted", null, null);
			Logger.logException(se);
			throw se;
		}
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#deleteAll(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean deleteAll(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		try {
			validateTask(object, user, StateFactory.DELETE_TASK);
		} catch (WPDException exception) {
			List params = new ArrayList();
			params.add(object);
			params.add(StateFactory.DELETE_TASK);
			params.add(object.getStatus());
			SecurityException se = new SecurityException(
					"User is not authorized for this task", params, null);

			throw se;
		}
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersion = object.getVersion();
		int latestVersion = latestObject.getVersion();
		if (orgVersion != -1 && latestVersion != orgVersion) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new InvalidDeleteRequestException(
					"The version specified in object for delete is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}

		/*
		 * Getting all the versions of the Business Object for doing the delete
		 * operation
		 *  
		 */

		if (latestVersion <= 1)
			return delete(object, user);

		LocateResults locateResults;
		try {
			locateResults = (LocateResults) invokeMethod(builderObj,
					RETRIEVE_ALL_VERSION_METHOD, new Class[] { object
							.getClass() }, new Object[] { object });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException("Locate failed.", params,
					e.getCause());
			Logger.logException(se);
			throw se;
		}
		List locateResultList = locateResults.getLocateResults();

		boolean lock = false;
		LockManager lockManager = new LockManager();
		try {
			lock = lockManager.lock(object, user, metaObject
					.getCheckOutDuration());
		} catch (LockedBySameUserException lockedBySameUserException) {

		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException se = new SevereException( "The Lock can not be
			 * acquired for the object for the delete operation" +
			 * user.getUserId(), params, lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}

		boolean markedAsDeleted = false;
		Iterator it = locateResultList.iterator();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		try {
			while (it.hasNext()) {
				BusinessObject businessObject = (BusinessObject) it.next();
				boolean canbeMarkedForDeletion = false;
				if (!StateFactory.STATUS_MARKED_FOR_DELETION
						.equalsIgnoreCase(businessObject.getStatus())) {
					canbeMarkedForDeletion = true;

					try {
						new StateManager().validate(businessObject, user,
								StateFactory.STATUS_MARKED_FOR_DELETION);
					} catch (WPDException e) {
						canbeMarkedForDeletion = false;
						throw e;
					}

					if (canbeMarkedForDeletion) {
						businessObject
								.setStatus(StateFactory.STATUS_MARKED_FOR_DELETION);
						try {
							invokeMethod(builderObj, PERSIST_METHOD,
									new Class[] { businessObject.getClass(),
											Audit.class, boolean.class },
									new Object[] { businessObject, audit,
											Boolean.valueOf(false) });
						} catch (Exception e) {
							List params = new ArrayList();
							params.add(object);
							SevereException se = new SevereException(
									"Object cannot be deleted", params, e
											.getCause());
							Logger.logException(se);
							throw se;
						}
						if (businessObject.getVersion() == latestVersion) {
							markedAsDeleted = true;
							object
									.setStatus(StateFactory.STATUS_MARKED_FOR_DELETION);
						}

					}
				}
			}
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be destroyed or deleted", params, e);
			Logger.logException(se);
			throw se;
		} finally {
			//unlock the business object
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger
						.logException(new SevereException(
								"Object cannot be unlocked during the delete operation",
								params, lockException));
			}
			if (markedAsDeleted)
				setState(object, user);
		}

		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#approve(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean approve(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);

		try {
			new StateManager().validate(latestObject, user,
					StateFactory.STATUS_APPROVED);
		} catch (SecurityException securityException) {
			if (!user.isAuthorized(moduleName, StateFactory.REJECT_TASK)) {

				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.APPROVE_TASK);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;
			}
		}

		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();

		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object approve is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}
		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_APPROVED);
		object.setStatus(StateFactory.STATUS_APPROVED);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be approved", params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException le) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after approve operation",
						params, le));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#reject(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean reject(BusinessObject object, User user) throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);

		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();
		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object reject is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}
		try {
			new StateManager().validate(latestObject, user,
					StateFactory.STATUS_REJECTED);
		} catch (SecurityException securityException) {
			if (!user.isAuthorized(moduleName, StateFactory.APPROVE_TASK)) {

				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.REJECT_TASK);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;
			}
		}

		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_REJECTED);
		object.setStatus(StateFactory.STATUS_REJECTED);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be moved to rejected state", params, e
							.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after reject operation",
						params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#scheduleForTest(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean scheduleForTest(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		String oldStatus = object.getStatus();

		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();

		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object scheduled for test is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}
		new StateManager().validate(latestObject, user,
				StateFactory.STATUS_SCHEDULED_FOR_TEST);
		String status = latestObject.getStatus();
		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_SCHEDULED_FOR_TEST);
		object.setStatus(StateFactory.STATUS_SCHEDULED_FOR_TEST);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be scheduled for test", params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after schedule for test",
						params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		checkLog(object, oldStatus, audit);

		/*
		 * List val=new ArrayList(); val.add("SCHEDULED_FOR_PRODUCTION");
		 */
		//  List aval=retrieveLog(contract,val);
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#scheduleForProduction(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean scheduleForProduction(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		String oldStatus= object.getStatus();
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();

		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object scheduled for production is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}

		new StateManager().validate(latestObject, user,
				StateFactory.STATUS_SCHEDULED_FOR_PRODUCTION);

		String status = latestObject.getStatus();
		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}

		latestObject.setStatus(StateFactory.STATUS_SCHEDULED_FOR_PRODUCTION);
		object.setStatus(StateFactory.STATUS_SCHEDULED_FOR_PRODUCTION);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be scheduled for production", params, e
							.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger
						.logException(new SevereException(
								"Object cannot be unlocked after schedule for production",
								params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		
		checkLog(object, oldStatus, audit);
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#transfer(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean transfer(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		String oldStatus= object.getStatus();
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();
		if (orgVersionAttrValue != -1
				&& orgVersionAttrValue != reterviedVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object transfer is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}

		StateManager sm = new StateManager();
		boolean testTransfer = false;
		sm.validate(latestObject, user, StateFactory.STATUS_OBJECT_TRANSFERRED);

		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_OBJECT_TRANSFERRED);
		object.setStatus(StateFactory.STATUS_OBJECT_TRANSFERRED);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"Object cannot be transferred", params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after transfer operation",
						params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		checkLog(object,oldStatus,audit);
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#testPass(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */

	public boolean testPass(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();

		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object pass to test is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}
		try {
			new StateManager().validate(latestObject, user,
					StateFactory.STATUS_TEST_SUCCESSFUL);
		} catch (SecurityException e) {
			if (!user.isAuthorized(moduleName, StateFactory.TEST_FAIL_TASK)) {
				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.TEST_PASS_TASK);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;

			}
		}

		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_TEST_SUCCESSFUL);
		object.setStatus(StateFactory.STATUS_TEST_SUCCESSFUL);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException("The operation  failed",
					params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after test pass operation",
						params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#testFail(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean testFail(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		String moduleName = metaObject.getModule();
		try {
			new StateManager().validate(latestObject, user,
					StateFactory.STATUS_TEST_FAILED);
		} catch (SecurityException e) {
			if (!user.isAuthorized(moduleName, StateFactory.TEST_PASS_TASK)) {
				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.STATUS_TEST_FAILED);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;

			}
		}

		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();
		if (orgVersionAttrValue != -1) {
			if (orgVersionAttrValue != reterviedVersionAttrValue) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new IllegalRequestException(
						"The version specified in object for testfail is not the latest one",
						params, null);
				Logger.logException(se);
				throw se;
			}
		}
		String status = latestObject.getStatus();
		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_TEST_FAILED);
		object.setStatus(StateFactory.STATUS_TEST_FAILED);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"The Operation ,test pass failed", params, e.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger.logException(new SevereException(
						"Object cannot be unlocked after test fail operation",
						params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#scheduleForApproval(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean scheduleForApproval(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, object);
		BusinessObject latestObject = getLatestVersionOfBusinessObject(
				builderObj, object);
		int orgVersionAttrValue = object.getVersion();
		int reterviedVersionAttrValue = latestObject.getVersion();
		if (orgVersionAttrValue != -1
				&& reterviedVersionAttrValue != orgVersionAttrValue) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new IllegalRequestException(
					"The version specified in object scheduled for approval is not the latest one",
					params, null);
			Logger.logException(se);
			throw se;
		}

		new StateManager().validate(latestObject, user,
				StateFactory.STATUS_SCHEDULED_FOR_APPROVAL);

		LockManager lockManager = new LockManager();
		//lock on the business object for the given user
		try {
			lockManager.lock(object, user, metaObject.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(object);
			/*
			 * SevereException severeException = new SevereException( "The Lock
			 * can not be acquired for the user" + user.getUserId(), params,
			 * lockException);
			 */
			Logger.logException(lockException);
			throw lockException;
		}
		latestObject.setStatus(StateFactory.STATUS_SCHEDULED_FOR_APPROVAL);
		object.setStatus(StateFactory.STATUS_SCHEDULED_FOR_APPROVAL);
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		boolean isSuccess = false;
		try {
			invokeMethod(
					builderObj,
					PERSIST_METHOD,
					new Class[] { latestObject.getClass(), Audit.class,
							boolean.class },
					new Object[] { latestObject, audit, Boolean.valueOf(false) });
			isSuccess = true;

		} catch (Exception e) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException(
					"The operation, schedule for approval failed", params, e
							.getCause());
			Logger.logException(se);
			throw se;
		} finally {
			try {
				lockManager.unlock(object, user);
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(object);
				Logger
						.logException(new SevereException(
								"Object cannot be unlocked after schedule for approval",
								params, lockException));
			}
			if (isSuccess) {
				setState(object, user);
			}
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#retrieve(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public BusinessObject retrieve(BusinessObject object, User user)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		return doRetrieve(object, user, null);
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#retrieve(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User,
	 *      java.util.Map)
	 * @param object
	 * @param user
	 * @param params
	 * @return
	 * @throws WPDException
	 */
	public BusinessObject retrieve(BusinessObject object, User user, Map params)
			throws WPDException {
		if (null == object || null == user || params == null) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "and user -"
							+ user + "and params -" + params + ".");
		}
		return doRetrieve(object, user, params);
	}

	/**
	 * 
	 * @param object
	 * @param user
	 * @param configParams
	 * @return
	 * @throws WPDException
	 */
	private BusinessObject doRetrieve(BusinessObject object, User user,
			Map configParams) throws WPDException {
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);

		try {
			validateTask(object, user, StateFactory.RETRIEVE_TASK);
		} catch (SevereException e) {
			try {
				validateTask(object, user, StateFactory.CREATE_TASK);
			} catch (SevereException severeException) {
				List params = new ArrayList();
				params.add(object);
				params.add(StateFactory.CREATE_TASK);
				params.add(object.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;
			}
		}
		Object builderObject = getBuilderObject(metaObject, object);
		BusinessObject businessObject = null;
		int version = object.getVersion();
		if (version != -1) {
			if (configParams == null) {

				try {
					businessObject = (BusinessObject) invokeMethod(
							builderObject, RETRIEVE_METHOD,
							new Class[] { object.getClass() },
							new Object[] { object });
				} catch (Exception e) {
					List params = new ArrayList();
					params.add(object);
					SevereException se = new SevereException(
							"Object cannot be retrieved", params, e.getCause());
					Logger.logException(se);
					throw se;
				}
			} else {
				try {
					businessObject = (BusinessObject) invokeMethod(
							builderObject, RETRIEVE_METHOD, new Class[] {
									object.getClass(), Map.class },
							new Object[] { object, configParams });
				} catch (Exception e) {
					List params = new ArrayList();
					params.add(object);
					SevereException se = new SevereException(
							"Object cannot be retrieved", params, e.getCause());
					Logger.logException(se);
					throw se;
				}
			}
			if (businessObject == null) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new BusinessObjectNotFoundException(
						"The business object can not be retrieved", params,
						null);
				Logger.logException(se);
				throw se;
			}
		} else {
			if (configParams == null)
				businessObject = getLatestVersionOfBusinessObject(
						builderObject, object);
		}

		setState(businessObject, user);
		return businessObject;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#copy(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User,
	 *      java.util.Map)
	 * @param sourceObj
	 * @param targetObj
	 * @param user
	 * @param parameters
	 * @return
	 * @throws WPDException
	 */
	public boolean copy(BusinessObject sourceObj, BusinessObject targetObj,
			User user, Map parameters) throws WPDException {

		if (null == sourceObj || null == targetObj || null == user) {
			throw new IllegalArgumentException(
					"Null parameter found. source - " + sourceObj
							+ ".target - " + targetObj + ".user - " + user
							+ ".");
		}
		// Check whether the objects are of same type
		if (!sourceObj.getClass().getName().equals(
				targetObj.getClass().getName())) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(targetObj);
			SevereException se = new SevereException(
					"Invalid copy request.Source and target objects are of different types",
					params, null);
			Logger.logException(se);
			throw se;
		}

		MetaObjectFactory factory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = factory.getMetaObject(sourceObj);

		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(sourceObj, keyAttributes);
		validateKeyValues(targetObj, keyAttributes);
		try {
			validateTask(sourceObj, user, StateFactory.COPY_TASK);
		} catch (SevereException severeException) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(StateFactory.COPY_TASK);
			params.add(sourceObj.getStatus());
			SecurityException se = new SecurityException(
					"User is not authorized for this task", params, null);

			throw se;
		}
		int versionValue = sourceObj.getVersion();
		if (-1 == versionValue) {
			List params = new ArrayList();
			params.add(sourceObj);
			SevereException severeException = new SevereException(
					"Copy failed.Version of the source objet should be specified",
					params, null);
			Logger.logException(severeException);
			throw severeException;
		}
		Object builderObj = getBuilderObject(metaObject, sourceObj);

		BusinessObject retrievedSrcObj = null;
		try {
			retrievedSrcObj = (BusinessObject) invokeMethod(builderObj,
					RETRIEVE_METHOD, new Class[] { sourceObj.getClass() },
					new Object[] { sourceObj });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(targetObj);
			SevereException se = new SevereException(
					"Copy failed.Source Object cannot be found", params, e
							.getCause());
			Logger.logException(se);
			throw se;
		}

		if (null == retrievedSrcObj) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(targetObj);
			SevereException se = new BusinessObjectNotFoundException(
					"Copy failed.Source Object cannot be found", params, null);
			Logger.logException(se);
			throw se;
		}
		BusinessObject retrievedTagtObj = null;
		try {
			retrievedTagtObj = (BusinessObject) invokeMethod(builderObj,
					RETRIEVE_LATEST_VERSION_METHOD, new Class[] { targetObj
							.getClass() }, new Object[] { targetObj });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(targetObj);
			SevereException se = new SevereException(
					"Copy failed.The target business object can not be retrieved",
					params, e.getCause());
			Logger.logException(se);
			throw se;
		}

		if (retrievedTagtObj == null) {
			List params = new ArrayList();
			params.add(targetObj);
			SevereException se = new BusinessObjectNotFoundException(
					"Copy failed.The target business object can not be retrieved",
					params, null);
			Logger.logException(se);
			throw se;
		}

		int retrivedVersion = retrievedTagtObj.getVersion();
		int targetVersion = targetObj.getVersion();

		if (-1 != retrivedVersion && retrivedVersion != targetVersion) {
			List params = new ArrayList();
			params.add(targetObj);
			SevereException se = new IllegalCopyTargetException(
					"Copy failed.The target business object should be of the latest version",
					params, null);
			Logger.logException(se);
			throw se;
		}
		boolean isalreadyCheckedOut = false;
		int currentTargetVersion = retrivedVersion;
		if (currentTargetVersion == 0) {
			LockManager lockMngr = new LockManager();
			try {
				lockMngr
						.lock(targetObj, user, metaObject.getCheckOutDuration());
			} catch (LockedBySameUserException lbsue) {
				// do nothing . Continue
			} catch (LockException lockException) {
				List params = new ArrayList();
				params.add(targetObj);
				/*
				 * SevereException severeException = new SevereException( "Lock
				 * on target object can not be obtained ", params,
				 * lockException);
				 */
				Logger.logException(lockException);
				throw lockException;
			}
		} else if (currentTargetVersion > 0) {
			BusinessObject newBusObject;
			LockManager lockMngr = new LockManager();
			if (!lockMngr.isLocked(targetObj, user)) {
				try {
					newBusObject = checkOut(targetObj, user);
					targetObj = newBusObject;
				} catch (CheckedOutBySameUserException e) {
					isalreadyCheckedOut = true;
				} catch (SevereException se) {
					List params = new ArrayList();
					params.add(sourceObj);
					params.add(targetObj);
					SevereException se1 = new SevereException("Copy Failed",
							params, se.getCause());
					Logger.logException(se1);
				}
			}
		}
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		Object builderObject = getBuilderObject(metaObject, targetObj);
		try {
			if (null != parameters) {
				invokeMethod(builderObject, COPY_METHOD, new Class[] {
						sourceObj.getClass(), targetObj.getClass(),
						Audit.class, parameters.getClass() }, new Object[] {
						sourceObj, targetObj, audit, parameters });
			} else {
				invokeMethod(builderObject, COPY_METHOD,
						new Class[] { sourceObj.getClass(),
								targetObj.getClass(), Audit.class },
						new Object[] { sourceObj, targetObj, audit });
			}
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(sourceObj);
			params.add(targetObj);
			SevereException se = new SevereException("Copy Failed", params, e
					.getCause());
			Logger.logException(se);
			throw se;
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#persist(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User, boolean)
	 * @param object
	 * @param user
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	public boolean persist(BusinessObject object, User user, boolean insertFlag)
			throws WPDException {
		if (null == object || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + object + "And user"
							+ user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(object);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(object, keyAttributes);

		if (insertFlag) {
			try {
				validateTask(object, user, StateFactory.CREATE_TASK);
			} catch (SevereException e) {
				try {
					validateTask(object, user, StateFactory.COPY_TASK);
				} catch (SevereException severeException) {
					List params = new ArrayList();
					params.add(object);
					params.add(StateFactory.CREATE_TASK);
					params.add(object.getStatus());
					SecurityException se = new SecurityException(
							"User is not authorized for this task", params,
							null);

					throw se;
				}
			}
		} else {
			try {
				validateTask(object, user, StateFactory.EDIT_TASK);
			} catch (SevereException e) {
				try {
					validateTask(object, user, StateFactory.CREATE_TASK);
				} catch (SevereException severeException) {
					List params = new ArrayList();
					params.add(object);
					params.add(StateFactory.EDIT_TASK);
					params.add(object.getStatus());
					SecurityException se = new SecurityException(
							"User is not authorized for this task", params,
							null);

					throw se;
				}
			}
		}

		boolean lock = false;
		LockManager lockManager = new LockManager();
		if (insertFlag) {
			object.setStatus(StateFactory.STATUS_BUILDING);
			object.setVersion(0);
			try {
				lockManager
						.lock(object, user, metaObject.getCheckOutDuration());
			} catch (LockException le) {
				List params = new ArrayList();
				params.add(object);
				//SevereException se = new SevereException("Error in acquring
				// lock",params,le);
				Logger.logException(le);
				throw le;
			}
		}
		Object builderObj = getBuilderObject(metaObject, object);
		if (!insertFlag) {
			BusinessObject latestObject = getLatestVersionOfBusinessObject(
					builderObj, object);
			int orgVersionAttrValue = object.getVersion();
			if (-1 == orgVersionAttrValue) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new InvalidPersistRequestException(
						"The version attribute for the business object is not present",
						params, null);
				Logger.logException(se);
				throw se;
			}
			int reterviedVersionAttrValue = latestObject.getVersion();
			if (orgVersionAttrValue != reterviedVersionAttrValue) {
				List params = new ArrayList();
				params.add(object);
				SevereException se = new IllegalVersionPersistRequestException(
						"The persist operation failed. A newer version of the object is availble",
						params, null);
				Logger.logException(se);
				throw se;
			}
			object.setStatus(latestObject.getStatus());
		}

		if (!new LockManager().isLocked(object, user)) {
			List params = new ArrayList();
			params.add(object);
			SevereException se = new LockNotFoundException(
					"The persist operation failed. Lock can not be acquired",
					params, null);
			Logger.logException(se);
			throw se;
		}
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);

		try {
			invokeMethod(builderObj, PERSIST_METHOD, new Class[] {
					object.getClass(), Audit.class, boolean.class },
					new Object[] { object, audit, Boolean.valueOf(insertFlag) });
		} catch (Exception e) {
			if (insertFlag) {
				try {
					lockManager.unlock(object, user);
				} catch (LockException lockException) {
					Logger
							.logError("Fatal error Occured While unLocking the Object During Persist.");
				}
			}
			List params = new ArrayList();
			params.add(object);
			SevereException se = new SevereException("Persist failed.", params,
					e.getCause());
			Logger.logException(se);
			throw se;
		}

		setState(object, user);

		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#persist(java.lang.Object,
	 *      com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User, boolean)
	 * @param subObject
	 * @param mainObject
	 * @param user
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	public boolean persist(Object subObject, BusinessObject mainObject,
			User user, boolean insertFlag) throws WPDException {
		if (null == subObject || null == mainObject || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + mainObject
							+ "And user" + user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(mainObject);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(mainObject, keyAttributes);
		LockManager lockManager = new LockManager();

		try {
			validateTask(mainObject, user, StateFactory.CREATE_TASK);
		} catch (SevereException e) {
			try {
				validateTask(mainObject, user, StateFactory.COPY_TASK);
			} catch (SevereException severeException) {
				List params = new ArrayList();
				params.add(subObject);
				params.add(StateFactory.CREATE_TASK);
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;
			}
		}

		if (!lockManager.isLocked(mainObject, user)) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new LockNotFoundException(
					"The persist operation failed. Lock can not be acquired",
					params, null);
			Logger.logException(se);
			throw se;
		}

		Object builderObj = getBuilderObject(metaObject, mainObject);

		int mainVersionAttrValue = mainObject.getVersion();
		if (mainVersionAttrValue == -1) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new InvalidPersistRequestException(
					"The version attribute for the business object is not present",
					params, null);
			Logger.logException(se);
			throw se;
		}
		BusinessObject latestBusinessObject = getLatestVersionOfBusinessObject(
				builderObj, mainObject);
		int reterviedVersionAttrValue = latestBusinessObject.getVersion();
		if (mainVersionAttrValue != reterviedVersionAttrValue) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new IllegalVersionPersistRequestException(
					"The persist operation failed. A newer version of the object is available",
					params, null);
			Logger.logException(se);
			throw se;
		}

		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);

		try {
			invokeMethod(builderObj, PERSIST_METHOD, new Class[] {
					subObject.getClass(), latestBusinessObject.getClass(),
					Audit.class, boolean.class }, new Object[] { subObject,
					latestBusinessObject, audit, Boolean.valueOf(insertFlag) });

			invokeMethod(builderObj, PERSIST_TIME_STAMP_METHOD, new Class[] {
					latestBusinessObject.getClass(), Audit.class },
					new Object[] { latestBusinessObject, audit });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(subObject);
			SevereException se = new SevereException("Persist failed.", params,
					e.getCause());
			Logger.logException(se);
			throw se;
		}
		return true;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#locate(com.wellpoint.wpd.common.bo.LocateCriteria,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param locateCriteria
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public LocateResults locate(LocateCriteria locateCriteria, User user)
			throws WPDException {
		if (null == locateCriteria || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for LocateCriteria-" + locateCriteria
							+ "And user" + user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(locateCriteria);
		String moduleName = metaObject.getModule();
		Object builderObj = getBuilderObject(metaObject, locateCriteria);

		LocateResults locateResults;
		try {
			locateResults = (LocateResults) invokeMethod(builderObj,
					LOCATE_METHOD, new Class[] { locateCriteria.getClass(),
							User.class }, new Object[] { locateCriteria, user });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(locateCriteria);
			SevereException se = new SevereException("Locate failed.", params,
					e.getCause());
			Logger.logException(se);
			throw se;
		}
		List locateResultList = locateResults.getLocateResults();

		//Create an instance of the business object corresponding to the
		// locateCriteria
		//Set the values in this object to set the key values
		Class businessObjectClass = null;
		BusinessObject businessObject = null;
		try {
			businessObjectClass = Class.forName(metaObject.getName());
		} catch (ClassNotFoundException e) {
			List params = new ArrayList();
			params.add(locateCriteria);
			SevereException se = new SevereException(
					"Business Object class not found in the classpath for the locate criteria. locate criteria ="
							+ locateCriteria.getClass()
							+ " business object class = "
							+ metaObject.getName(), params, e);
			Logger.logException(se);
			throw se;
		}
		//Create an instance
		try {
			businessObject = (BusinessObject) businessObjectClass.newInstance();
		} catch (InstantiationException instantiationException) {
			List params = new ArrayList();
			params.add(businessObjectClass);
			SevereException se = new SevereException(
					"Business Object Instantiation Error", params,
					instantiationException);
			Logger.logException(se);
			throw se;
		} catch (IllegalAccessException illegalAccessException) {
			List params = new ArrayList();
			params.add(businessObjectClass);
			SevereException se = new SevereException(
					"Business Object Instantiation Error", params,
					illegalAccessException);
			Logger.logException(se);
			throw se;
		}

		//Create a HashMap to store the latest version corresponding to the
		// given keys
		Map keysToLatestVersionMap = new HashMap();

		//Iterate through the locate results and set the state object
		Iterator it = locateResultList.iterator();
		while (it.hasNext()) {
			Object locateResult = it.next();
			if (!(locateResult instanceof BusinessObject || locateResult instanceof LocateResult)) {
				break;
			}
			String status = "";
			if (locateResult instanceof BusinessObject)
				status = ((BusinessObject) locateResult).getStatus();
			else if (locateResult instanceof LocateResult)
				status = ((LocateResult) locateResult).getStatus();

			if (status == null) {
				if (metaObject.getTransitions() != null) {
					List params = new ArrayList();
					params.add(locateResult);
					SevereException se = new SevereException(
							"Status should be set in locate result", params,
							null);
					Logger.logException(se);
					throw se;
				}
			} else {
				//getting version from locate result
				int version = -1;
				int latestVersionOfBO = -1;
				if (locateResult instanceof BusinessObject)
					version = ((BusinessObject) locateResult).getVersion();
				else if (locateResult instanceof LocateResult)
					version = ((LocateResult) locateResult).getVersion();

				String concatenatedKeys = "";
				//Set the value of the key attributes and status to business
				// object
				List keyAttributes = metaObject.getKeyAttributes();
				Iterator keyItr = keyAttributes.iterator();
				while (keyItr.hasNext()) {
					InformationElement informationElement = (InformationElement) keyItr
							.next();
					String key = informationElement.getElementName();
					String datatype = informationElement.getDataType();
					Class datatypeClass = null;
					//Get value from the locate result
					Object keyValue = ReflectionUtil
							.getValueOfBusinessAttributeByReflection(
									locateResult, key);
					//Build the concatenatedKeys; used as the key to the map
					// that stores the latest version
					//corresponding to a key
					concatenatedKeys += keyValue + KEY_ATTRIBUTE_SEPARATOR;
					//Find the type of the parameter for the setter method
					if (datatype.equals("Int")) {
						datatypeClass = int.class;
					} else if (datatype.equals("Long")) {
						datatypeClass = long.class;
					} else if (datatype.equals("String")) {
						datatypeClass = String.class;
					} else if (datatype.equals("Date")) {
						datatypeClass = java.util.Date.class;
					} else if (datatype.equals("Float")) {
						datatypeClass = float.class;
					} else if (datatype.equals("Double")) {
						datatypeClass = double.class;
					} else if (datatype.equals("List")) {
						datatypeClass = List.class;
					}
					//Set the value of key attribute to business object
					ReflectionUtil.setValueOfBusinessAttributeByReflection(
							businessObject, key, new Class[] { datatypeClass },
							new Object[] { keyValue });
				}

				if (locateResults.isLatestVersion()) {
					//already getting the latest version
					latestVersionOfBO = version;
				} else {
					//if not the latest version, getting the latest version of
					// the object.
					//Get the value of the latest version from the HashMap if
					// its
					// not already present
					Integer latestVersion = (Integer) keysToLatestVersionMap
							.get(concatenatedKeys);
					if (latestVersion == null) {
						try {
							BusinessObject retrievedBusinessObject = getLatestVersionOfBusinessObject(
									builderObj, businessObject);
							latestVersionOfBO = retrievedBusinessObject
									.getVersion();
						} catch (SevereException e) {
							//If all the versions are deleted for a business
							// object
							// key, latest version
							latestVersionOfBO = -1;
						}
						keysToLatestVersionMap.put(
								new String(concatenatedKeys), new Integer(
										latestVersionOfBO));
					} else {
						latestVersionOfBO = latestVersion.intValue();
					}
				}
				//Set the value of the status attribute to business object

				State state = null;
				if (version == latestVersionOfBO) {
					businessObject.setStatus(status);
					businessObject.setVersion(version);
					setState(businessObject, user);
					state = ((BusinessObject) businessObject).getState();
				} else {
					state = new StateFactoryImpl().getState(user, moduleName);
				}

				if (locateResult instanceof BusinessObject)
					((BusinessObject) locateResult).setState(state);
				else if (locateResult instanceof LocateResult)
					((LocateResult) locateResult).setState(state);
			}
		}
		return locateResults;
	}

	/**
	 * Change the identity (key attribute values) of the BO with the new values.
	 * This operation is only if the object is in the BUILDING status.
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#changeIdentity(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param oldBOKey
	 * @param newBOKey
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean changeIdentity(BusinessObject oldBOKey,
			BusinessObject newBOKey, User user) throws WPDException {
		//Validate the arguments
		if (oldBOKey == null || newBOKey == null || user == null)
			throw new IllegalArgumentException(
					"One of the old or new key BOs or user is NULL. Pass objects with key attributes populated");
		if (!(oldBOKey.getClass().getName()).equals(newBOKey.getClass()
				.getName())) {
			throw new IllegalArgumentException(
					"The old and the new key BOs have to be of same type");
		}
		//Get the meta object and validate the key attributes
		//Exception is thrown if the provided key fields are empty or if the
		// identity doesn't change
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(oldBOKey);
		List keyAttributes = metaObject.getKeyAttributes();
		if (!validateKeyValues(oldBOKey, newBOKey, keyAttributes)) {
			List params = new ArrayList();
			params.add(oldBOKey);
			params.add(newBOKey);
			SevereException se = new SevereException(
					"The change identity operation failed. The key values in the old and new key BOs are same",
					params, null);
			Logger.logException(se);
			throw se;
		}

		//Check whether lock is present for the oldBOKey
		LockManager lockManager = new LockManager();
		if (!lockManager.isLocked(oldBOKey, user)) {
			List params = new ArrayList();
			params.add(oldBOKey);
			SevereException se = new LockNotFoundException(
					"The change identity operation failed. Lock can not be acquired for the old BO",
					params, null);
			Logger.logException(se);
			throw se;
		}

		//Check whether the BO is in BUILDING status
		Object builderObj = getBuilderObject(metaObject, oldBOKey);
		BusinessObject object = getLatestVersionOfBusinessObject(builderObj,
				oldBOKey);
		if (object.getVersion() == 0
				&& StateFactory.STATUS_BUILDING.equals(object.getStatus())) {
			//valid status
		} else {
			List params = new ArrayList();
			params.add(oldBOKey);
			params.add(newBOKey);
			SevereException se = new SevereException(
					"Object is not in BUILDING status for the changeIdentity operation",
					params, null);
			Logger.logException(se);
			throw se;
		}

		//Update the lock for the BO with the new key information
		boolean lock = false;
		try {
			lock = lockManager.update(oldBOKey, newBOKey, user, metaObject
					.getCheckOutDuration());
		} catch (LockException lockException) {
			List params = new ArrayList();
			params.add(oldBOKey);
			params.add(newBOKey);
			SevereException severeException = new SevereException(
					"Lock can't be updated", params, lockException);
			Logger.logException(severeException);
			throw severeException;
		}

		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);

		//Call the change identity method on the builder
		if (lock) {
			try {
				invokeMethod(builderObj, CHANGE_IDENTITY,
						new Class[] { oldBOKey.getClass(), newBOKey.getClass(),
								Audit.class }, new Object[] { oldBOKey,
								newBOKey, audit });
				return true;
			} catch (Exception e) {
				//Revert the lock information if the change identity fails
				try {
					lock = lockManager.update(newBOKey, oldBOKey, user,
							metaObject.getCheckOutDuration());
				} catch (LockException lockException) {
					List params = new ArrayList();
					params.add(oldBOKey);
					params.add(newBOKey);
					SevereException severeException = new SevereException(
							"Lock can't be reverted on failure of builder's changeIdentity method",
							params, lockException);
					Logger.logException(severeException);
					throw severeException;
				}
				List params = new ArrayList();
				params.add(builderObj);
				SevereException se = new SevereException(
						"Business Object's identity could not be changed",
						params, e.getCause());
				Logger.logException(se);
				throw se;
			}
		}
		return false;

	}

	/**
	 * 
	 * @param metaObject
	 * @param businessObject
	 * @return
	 * @throws SevereException
	 */
	private Object getBuilderObject(MetaObject metaObject, Object businessObject)
			throws SevereException {
		Class builderClass = null;
		Object builderObject = null;
		try {
			builderClass = Class.forName(metaObject.getBuilderClassName());
		} catch (ClassNotFoundException classNotFoundException) {
			List params = new ArrayList();
			params.add(businessObject);
			throw new SevereException(
					"Builder class not found in the classpath.Builder class = "
							+ metaObject.getBuilderClassName(), params,
					classNotFoundException);
		}
		try {
			builderObject = builderClass.newInstance();
		} catch (InstantiationException instantiationException) {
			List params = new ArrayList();
			params.add(businessObject);
			throw new SevereException("Builder Instantiation Error", params,
					instantiationException);
		} catch (IllegalAccessException illegalAccessException) {
			List params = new ArrayList();
			params.add(businessObject);
			throw new SevereException("Builder Instantiation Error", params,
					illegalAccessException);
		}
		return builderObject;
	}

	/**
	 * 
	 * @param builderObj
	 * @param busObject
	 * @return
	 * @throws WPDException
	 */
	private BusinessObject getLatestVersionOfBusinessObject(Object builderObj,
			BusinessObject busObject) throws WPDException {
		BusinessObject businessObject = null;

		try {
			businessObject = (BusinessObject) invokeMethod(builderObj,
					RETRIEVE_LATEST_VERSION_METHOD, new Class[] { busObject
							.getClass() }, new Object[] { busObject });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(builderObj);
			throw new SevereException("Business Object couldnt be retrieved",
					params, e.getCause());
		}
		if (businessObject == null) {
			List params = new ArrayList();
			params.add(busObject);
			throw new BusinessObjectNotFoundException(
					"The business object can not be retrieved", params, null);
		}
		return businessObject;
	}

	/**
	 * @param object
	 * @param keyAttributes
	 * @throws SevereException
	 */
	private void validateKeyValues(Object object, List keyAttributes)
			throws SevereException {
		for (int keyCnt = 0; keyCnt < keyAttributes.size(); keyCnt++) {
			InformationElement infoElmnt = (InformationElement) keyAttributes
					.get(keyCnt);

			Object keyValue = null;
			try {
				keyValue = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(object,
								infoElmnt.getElementName());
				if (keyValue == null || "".equals(keyValue)) {
					List params = new ArrayList();
					params.add(object);
					SevereException severeException = new SevereException(
							"Key value(s) is(are) missing", params, null);
					Logger.logException(severeException);
					throw severeException;
				}
			} catch (SevereException wpdException) {
				Logger.logException(wpdException);
				throw wpdException;
			}
		}
	}

	/**
	 * 
	 * @param oldKeyBO
	 * @param newKeyBO
	 * @param keyAttributes
	 * @return
	 * @throws SevereException
	 */
	private boolean validateKeyValues(Object oldKeyBO, Object newKeyBO,
			List keyAttributes) throws SevereException {
		boolean keyChanged = false;
		for (int keyCnt = 0; keyCnt < keyAttributes.size(); keyCnt++) {
			InformationElement infoElmnt = (InformationElement) keyAttributes
					.get(keyCnt);

			Object oldKeyValue = null;
			Object newKeyValue = null;
			try {
				oldKeyValue = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(oldKeyBO,
								infoElmnt.getElementName());
				newKeyValue = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(newKeyBO,
								infoElmnt.getElementName());
				if (oldKeyValue == null || "".equals(oldKeyValue)
						|| newKeyValue == null || "".equals(newKeyValue)) {
					List params = new ArrayList();
					params.add(oldKeyBO);
					params.add(newKeyBO);
					SevereException severeException = new SevereException(
							"Key value(s) is(are) missing", params, null);
					Logger.logException(severeException);
					throw severeException;
				}

				if (!keyChanged)
					if (!oldKeyValue.equals(newKeyValue))
						keyChanged = true;

			} catch (SevereException wpdException) {
				Logger.logException(wpdException);
				throw wpdException;
			}
		}
		return keyChanged;
	}

	/**
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager#delete(java.lang.Object,
	 *      com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param subObject
	 * @param mainObject
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean delete(Object subObject, BusinessObject mainObject, User user)
			throws WPDException {

		if (null == subObject || null == mainObject || null == user) {
			throw new IllegalArgumentException(
					"Null Paramater found for Source-" + mainObject
							+ "And user" + user + ".");
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(mainObject);
		List keyAttributes = metaObject.getKeyAttributes();
		validateKeyValues(mainObject, keyAttributes);
		try {
			validateTask(mainObject, user, StateFactory.EDIT_TASK);
		} catch (SevereException e) {
			try {
				validateTask(mainObject, user, StateFactory.CREATE_TASK);
			} catch (SevereException severeException) {
				List params = new ArrayList();
				params.add(mainObject);
				params.add(StateFactory.EDIT_TASK);
				params.add(mainObject.getStatus());
				SecurityException se = new SecurityException(
						"User is not authorized for this task", params, null);

				throw se;
			}
		}
		LockManager lockManager = new LockManager();
		if (!lockManager.isLocked(mainObject, user)) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new LockNotFoundException(
					"The delete operation failed. Lock can not be acquired",
					params, null);
			Logger.logException(se);
			throw se;
		}

		Object builderObj = getBuilderObject(metaObject, mainObject);

		int mainVersionAttrValue = mainObject.getVersion();
		if (-1 == mainVersionAttrValue) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new InvalidDeleteRequestException(
					"The version attribute for the business object is not present",
					params, null);
			Logger.logException(se);
			throw se;
		}
		BusinessObject mainBusinessObject = getLatestVersionOfBusinessObject(
				builderObj, mainObject);
		int retrievdVersionAttrValue = mainBusinessObject.getVersion();
		if (mainVersionAttrValue != retrievdVersionAttrValue) {
			List params = new ArrayList();
			params.add(mainObject);
			SevereException se = new InvalidDeleteRequestException(
					"The delete operation failed. A newer version of the object is available",
					params, null);
			Logger.logException(se);
			throw se;
		}

		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);

		try {
			invokeMethod(builderObj, DELETE_METHOD, new Class[] {
					subObject.getClass(), mainBusinessObject.getClass(),
					Audit.class }, new Object[] { subObject,
					mainBusinessObject, audit });

			//persisting main object with updated audit object.
			invokeMethod(builderObj, PERSIST_METHOD,
					new Class[] { mainBusinessObject.getClass(), Audit.class,
							boolean.class }, new Object[] { mainBusinessObject,
							audit, Boolean.valueOf(false) });
		} catch (Exception e) {
			List params = new ArrayList();
			params.add(subObject);
			SevereException se = new SevereException("Delete failed.", params,
					e.getCause());
			Logger.logException(se);
			throw se;
		}
		return true;
	}

	/**
	 * 
	 * @param mainObject
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean unlock(BusinessObject mainObject, User user)
			throws WPDException {
		if (mainObject == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + mainObject + "user ="
							+ user);
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObj = metaObjectFactory.getMetaObject(mainObject);
		String moduleName = metaObj.getModule();
		if (!user.isAuthorized(moduleName, StateFactory.UNLOCK_TASK)) {
			List params = new ArrayList();
			params.add(mainObject);
			params.add(StateFactory.UNLOCK_TASK);
			params.add(mainObject.getStatus());
			SecurityException se = new SecurityException(
					"User is not authorized for this task", params, null);

			throw se;
		}
		try {
			LockManager lockMngr = new LockManager();
			lockMngr.unlock(mainObject, user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param mainObject
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	public boolean lock(BusinessObject mainObject, User user)
			throws WPDException {
		if (mainObject == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + mainObject + "user ="
							+ user);
		}
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObj = metaObjectFactory.getMetaObject(mainObject);
		String moduleName = metaObj.getModule();

		try {
			LockManager lockMngr = new LockManager();
			if (lockMngr.isLocked(mainObject, user)) {
				return true;
			} else {
				if (lockMngr.isLocked(mainObject))
					return false;
				else
					lockMngr.lock(mainObject, user, metaObj
							.getCheckOutDuration());
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param businessObject
	 * @param user
	 * @param taskName
	 * @throws WPDException
	 */
	private void validateTask(BusinessObject businessObject, User user,
			String taskName) throws WPDException {
		boolean isAuthorized = false;
		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = metaObjectFactory.getMetaObject(businessObject);
		Map activities = metaObject.getActivities();
		String moduleName = metaObject.getModule();
		Activity activity = (Activity) activities.get(taskName);
		if (activity != null) {
			isAuthorized = user
					.isAuthorized(moduleName, activity.getTaskName());
		}
		if (!isAuthorized) {
			List params = new ArrayList();
			params.add(businessObject);
			params.add(user);
			throw new SevereException("User not authorized for the task",
					params, null);
		}
	}

	/**
	 * 
	 * @param builderObj
	 * @param methodName
	 * @param types
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	private Object invokeMethod(Object builderObj, String methodName,
			Class[] types, Object[] objects) throws Exception {

		try {
			return ReflectionUtil.invokeMethod(builderObj, methodName, types,
					objects);
		} catch (InvocationTargetException ite) {
			throw new Exception(ite.getTargetException());
		} catch (SevereException se) {
			throw new Exception(se);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/*
	 * Method which will insert the log details to the db Get the object of
	 * metaobject Get the transitions map from the metaobject Check whether the
	 * log is enabled or not If it is enabled, call the log method inside
	 * history log manager
	 */
	private void checkLog(BusinessObject businessObject, String oldStatus,
			Audit audit) throws SevereException {
		if (businessObject == null || oldStatus == null || audit == null) {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + businessObject
							+ "user =" + audit.getUser() + "where status="
							+ oldStatus);
		} else {

			MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
					.getFactory(ObjectFactory.METAOBJECT);
			MetaObject metaObject;
			try {
				metaObject = metaObjectFactory.getMetaObject(businessObject);

				Map transitions = metaObject.getTransitions();
				String newStatus;

				newStatus = (String) ReflectionUtil
						.getValueOfBusinessAttributeByReflection(
								businessObject,
								BusinessObject.BUSINESS_OBJECT_STATUS_FIELD_NAME);
				if (null != newStatus && !("".equals(newStatus))) {
					String transitionKey = "TRANSITION_" + oldStatus + "_TO_"
							+ newStatus;
					Transition transition = (Transition) transitions
							.get(transitionKey);
					if (transition != null && transition.isLog() == true) {

						HistoryLogManager historyLogManager = HistoryLogManagerFactory
								.getHistoryLogManger();
						historyLogManager.log(businessObject, audit);

					}
				} else if (newStatus == null)
					throw new StatusChangeValidationException(
							"Status is not populated in the business object",
							null, null);

			} catch (SevereException severeException) {

				throw severeException;
			} catch (MetadataParserException e1) {
				
			} catch (WPDException e) {
				
				
			}

		}
	}

	private List retreiveLog(BusinessObject businessObject, List actions) {

		if (null != businessObject && null != actions) {

			HistoryLogManager historyLogManager = HistoryLogManagerFactory
					.getHistoryLogManger();
			List aval;
			try {
				aval = historyLogManager.retrieveLog(businessObject, actions);
				return aval;
			} catch (WPDException e) {
				
			}
			

		} else {
			throw new IllegalArgumentException(
					"Null parameter is found.object =" + businessObject
							+ "list =" + actions);
		}
		return null;

	}

}