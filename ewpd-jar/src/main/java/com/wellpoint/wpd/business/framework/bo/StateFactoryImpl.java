/*
 * StateFactoryImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.wellpoint.wpd.business.framework.bo.manager.LockManager;
import com.wellpoint.wpd.business.framework.bo.manager.StateManager;
import com.wellpoint.wpd.common.bo.Activity;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.InformationElement;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.bo.StateImpl;
import com.wellpoint.wpd.common.bo.Transition;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.exception.bo.StateObjectCreationException;
import com.wellpoint.wpd.common.framework.exception.bo.StatusChangeValidationException;
import com.wellpoint.wpd.common.framework.exception.lock.LockException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * The StateFactoryImpl retrieves the state of the business object
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: StateFactoryImpl.java 48610 2008-08-25 14:24:49Z U14659 $
 */
public class StateFactoryImpl extends ObjectFactory implements StateFactory {

	/**
	 * Get the State object corresponding the business object
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.StateFactory#getState(java.lang.Object,
	 *      com.wellpoint.wpd.common.framework.security.domain.User)
	 * @param object
	 * @param user
	 * @return
	 */
	public State getState(Object object, User user)
			throws StateObjectCreationException {

		if (object == null || user == null) {
			throw new IllegalArgumentException(
					"Null parameter is found. object =" + object + "user ="
							+ user);
		}

		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = null;
		try {
			metaObject = metaObjectFactory.getMetaObject(object);
		} catch (MetadataParserException mpe) {
			throw new StateObjectCreationException(
					"Error in retrieving the metaObject corresponding to the Business Object",
					null, mpe);
		}

		List keyAttributes = metaObject.getKeyAttributes();

		List params = null;
		Iterator keyAttributesIter = keyAttributes.iterator();
		while (keyAttributesIter.hasNext()) {
			InformationElement infoElmnt = (InformationElement) keyAttributesIter
					.next();
			Object keyValue = null;
			try {
				keyValue = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(object,
								infoElmnt.getElementName());
			} catch (SevereException e) {
				params = new ArrayList();
				params.add(object);
				throw new StateObjectCreationException(
						"Error in retrieving the key values of the business object",
						params, e);
			}
			if (keyValue == null) {
				params = new ArrayList();
				params.add(object);
				throw new StateObjectCreationException(
						"Key value(s) is(are) missing", params, null);
			}
		}

		if (user.getUserId() == null || "".equals(user.getUserId())) {
			params = new ArrayList();
			params.add(user.getUserId());
			throw new StateObjectCreationException("Invalid User Id", params,
					null);
		}

		String statusAttributeValue = null;
		try {
			statusAttributeValue = (String) ReflectionUtil
					.getValueOfBusinessAttributeByReflection(object,
							BusinessObject.BUSINESS_OBJECT_STATUS_FIELD_NAME);
		} catch (SevereException se) {
			throw new StateObjectCreationException(
					"Error in retrieving the status attribute value from Business Object",
					null, se);
		}
		if (statusAttributeValue == null || "".equals(statusAttributeValue))
			throw new StateObjectCreationException(
					"Status attribute not set to the business object", null,
					null);

		StateImpl stateObject = new StateImpl();
		int latestVersion = ((BusinessObject) object).getVersion();
		String state = getStateForStatus(statusAttributeValue, latestVersion);
		if (state == null) {
			params = new ArrayList();
			params.add(statusAttributeValue);
			throw new StateObjectCreationException("Invalid status value",
					params, null);
		}
		stateObject.setState(state);
		List toStatuses = new ArrayList();
		Map transitions = metaObject.getTransitions();
		Iterator transitionsItr = transitions.keySet().iterator();
		while (transitionsItr.hasNext()) {
			String key = (String) transitionsItr.next();
			Transition transition = (Transition) transitions.get(key);
			if (transition.getFromStatus().equals(statusAttributeValue))
				toStatuses.add(transition.getToStatus());
		}
		setStateTransitionFlags(object, user, toStatuses, stateObject);
		//Added for delete
		String status = ((BusinessObject) object).getStatus();

		LockManager lockManager = new LockManager();
		Map activities = metaObject.getActivities();
		String moduleName = metaObject.getModule();
		try {
			if (lockManager.isLocked(object)) {
				stateObject.setLocked(true);
			}
			//          if the user is authorized to edit
			Activity copyActivity = (Activity) activities.get(COPY_TASK);
			Activity editActivity = (Activity) activities.get(EDIT_TASK);
			Activity viewActivity = (Activity) activities
					.get(RETRIEVE_TASK);
			Activity createActivity = (Activity) activities
						.get(CREATE_TASK);
			if (null != copyActivity
					&& user.isAuthorized(moduleName, copyActivity.getTaskName())) {
				stateObject.setValidForCopy(true);
			}
			if (null != viewActivity
					&& user.isAuthorized(moduleName, viewActivity
							.getTaskName())) {
				stateObject.setValidForView(true);
			}
			if(null != createActivity 
					&& user.isAuthorized(moduleName, createActivity
							.getTaskName())) {
				stateObject.setValidForCreate(true);
			}
			if (lockManager.isLocked(object, user)) {

				if (null != editActivity
						&& user
								.isAuthorized(moduleName, editActivity
										.getTaskName())) {
					stateObject.setEditableByUser(true);
				}

				//if the user is authorized to delete
				Activity deleteActivity = (Activity) activities.get(DELETE_TASK);
				if (null != deleteActivity
						&& user
								.isAuthorized(moduleName, deleteActivity
										.getTaskName())) {
					if (StateFactory.STATUS_BUILDING.equals(status)) {
						stateObject.setValidForDelete(true);
						
					} else {
						if (latestVersion == 1
								&& (StateFactory.STATUS_OBJECT_TRANSFERRED
										.equals(status)
										|| StateFactory.STATUS_SCHEDULED_FOR_TEST
												.equals(status)
										|| StateFactory.STATUS_TEST_FAILED
												.equals(status)
										|| StateFactory.STATUS_TEST_SUCCESSFUL
												.equals(status) || StateFactory.STATUS_CHECKED_IN
										.equals(status))) {
							stateObject.setValidForDelete(true);
						}
					}
				} else{
					stateObject.setValidForDelete(false);
				}
			}else{
				if (StateFactory.STATUS_BUILDING.equals(status)
						|| StateFactory.STATUS_CHECKED_OUT.equals(status)) {
					stateObject.setValidForDelete(false);
					stateObject.setLockedByUser(true);
					if(user.isAuthorized(moduleName,StateFactory.UNLOCK_TASK ))
						stateObject.setValidForUnlock(true);
					if(!lockManager.isLocked(object)){
						if(user.isAuthorized(moduleName, editActivity
									.getTaskName()))
						stateObject.setEditableByUser(true);
						stateObject.setLockedByUser(false);
						stateObject.setValidForUnlock(false);
						Activity deleteActivity = (Activity) activities.get(DELETE_TASK);
						if (null != deleteActivity
								&& user
										.isAuthorized(moduleName, deleteActivity
												.getTaskName()))
						stateObject.setValidForDelete(true);
					}
				}
			}
		    if(stateObject.isValidForDelete() && latestVersion >1)
		    	stateObject.setValidForDeleteAll(true);
		} catch (LockException le) {
			throw new StateObjectCreationException(
					"Exception occurred while setting the lock flags", null,
					null);
		}
		return stateObject;
	}

	/**
	 * 
	 * @param statusAttributeValue
	 * @param version
	 * @return
	 */
	private String getStateForStatus(String statusAttributeValue, int version) {
		if (StateFactory.STATUS_BUILDING.equals(statusAttributeValue))
			return StateFactory.STATE_NEW;
		if (StateFactory.STATUS_CHECKED_IN.equals(statusAttributeValue))
			return version == 1 ? StateFactory.STATE_NEW
					: StateFactory.STATE_OPENED_FOR_UPDATES;
		if (StateFactory.STATUS_SCHEDULED_FOR_TEST.equals(statusAttributeValue))
			return StateFactory.STATE_UNDER_TEST;
		if (StateFactory.STATUS_OBJECT_TRANSFERRED.equals(statusAttributeValue))
			return StateFactory.STATE_UNDER_TEST;
		if (StateFactory.STATUS_TEST_SUCCESSFUL.equals(statusAttributeValue))
			return StateFactory.STATE_UNDER_TEST;
		if (StateFactory.STATUS_TEST_FAILED.equals(statusAttributeValue))
			return StateFactory.STATE_UNDER_TEST;
		if (StateFactory.STATUS_SCHEDULED_FOR_APPROVAL
				.equals(statusAttributeValue))
			return StateFactory.STATE_AWAITING_APPROVAL;
		if (StateFactory.STATUS_APPROVED.equals(statusAttributeValue))
			return StateFactory.STATE_AWAITING_APPROVAL;
		if (StateFactory.STATUS_REJECTED.equals(statusAttributeValue))
			return StateFactory.STATE_AWAITING_APPROVAL;
		if (StateFactory.STATUS_CHECKED_OUT.equals(statusAttributeValue))
			return StateFactory.STATE_OPENED_FOR_UPDATES;
		if (StateFactory.STATUS_SCHEDULED_FOR_PRODUCTION
				.equals(statusAttributeValue))
			return StateFactory.STATE_PUBLISHED;
		if (StateFactory.STATUS_PUBLISHED.equals(statusAttributeValue))
			return StateFactory.STATE_PUBLISHED;
		if (StateFactory.STATUS_MARKED_FOR_DELETION
				.equals(statusAttributeValue))
			return StateFactory.STATE_MARKED_FOR_DELETION;
		return null;
	}

	/**
	 * 
	 * @param busObject
	 * @param user
	 * @param toStatuses
	 * @param stateObject
	 * @throws StateObjectCreationException
	 */
	private void setStateTransitionFlags(Object busObject, User user,
			List toStatuses, StateImpl stateObject)
			throws StateObjectCreationException {
	    if (null != toStatuses && toStatuses.size() > 0) {
			String toStatus = null;
			Iterator statusIterator = toStatuses.iterator();
			StateManager stateManager = new StateManager();
			while (statusIterator.hasNext()) {
				toStatus = (String) statusIterator.next();
				try {
					if (StateFactory.STATUS_CHECKED_IN.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					    
					        stateObject.setValidForCheckIn(false);
					        continue;
					    }
					    stateObject.setValidForCheckIn(true);
					}
					else if (StateFactory.STATUS_SCHEDULED_FOR_TEST
							.equals(toStatus)){
					    
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
						stateObject.setValidForTest(false);
						continue;
					    }
					    stateObject.setValidForTest(true);
					}
					else if (StateFactory.STATUS_OBJECT_TRANSFERRED
							.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForTransfer(false);
					        continue;
					    }
						stateObject.setValidForTransfer(true);
					}
					else if (StateFactory.STATUS_TEST_SUCCESSFUL
							.equals(toStatus)){
					    if(!stateObject.isValidForTestCompletion()){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForTestCompletion(false);
					        continue;
					    }
						stateObject.setValidForTestCompletion(true);
					    }
					}
					else if (StateFactory.STATUS_TEST_FAILED.equals(toStatus)){
					    if(!stateObject.isValidForTestCompletion()){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForTestCompletion(false);
					        continue;
					    }
						stateObject.setValidForTestCompletion(true);
					   }
					}
					else if (StateFactory.STATUS_SCHEDULED_FOR_APPROVAL
							.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForApproval(false);
					        continue;
					    }
						stateObject.setValidForApproval(true);
					}
					else if (StateFactory.STATUS_APPROVED.equals(toStatus)){
						if(!stateObject.isValidForApprovalCompletion()){
						    try{stateManager.validate(
									busObject, user, toStatus);
						    }catch(StatusChangeValidationException e){
						        throw e;
						    }catch(WPDException e){
						        stateObject.setValidForApprovalCompletion(false);
						        continue;
						    }
						    stateObject.setValidForApprovalCompletion(true);
						}
					}
					else if (StateFactory.STATUS_REJECTED.equals(toStatus)){
						if(!stateObject.isValidForApprovalCompletion()){
						    try{stateManager.validate(
									busObject, user, toStatus);
						    }catch(StatusChangeValidationException e){
						        throw e;
						    }catch(WPDException e){
						        stateObject.setValidForApprovalCompletion(false);
						        continue;
						    }
						    stateObject.setValidForApprovalCompletion(true);   
						}
					}
					else if (StateFactory.STATUS_CHECKED_OUT.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForCheckOut(false);
					        continue;
					    }
						stateObject.setValidForCheckOut(true);
					}
					else if (StateFactory.STATUS_SCHEDULED_FOR_PRODUCTION
							.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForProduction(false);
					        continue;
					    }
						stateObject.setValidForProduction(true);
					}
					else if (StateFactory.STATUS_PUBLISHED.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForPublish(false);
					        continue;
					    }
						stateObject.setValidForPublish(true);
					}
					else if (StateFactory.STATUS_MARKED_FOR_DELETION
							.equals(toStatus)){
					    try{stateManager.validate(
								busObject, user, toStatus);
					    }catch(StatusChangeValidationException e){
					        throw e;
					    }catch(WPDException e){
					        stateObject.setValidForDelete(false);
					        continue;
					    }
						stateObject.setValidForDelete(true);
					}
				} catch (StatusChangeValidationException e) {
					List params = new ArrayList();
					params.add(busObject);
					params.add(toStatus);
					throw new StateObjectCreationException(
							"Invalid status transition validation", params, e);
				}
			}
		}
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.bo.StateFactory#getState()
	 * @return
	 */
	public State getState(User user,String moduleName) {
		StateImpl state = new StateImpl();
		state.setValidForView(user.isAuthorized(moduleName,StateFactory.VIEW_TASK));
		state.setValidForCopy(user.isAuthorized(moduleName,StateFactory.COPY_TASK));
		return state;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.bo.StateFactory#getState()
	 */
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}
}