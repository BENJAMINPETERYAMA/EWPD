/*
 * LockManager.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.business.framework.bo.manager;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.LockFactory;
import com.wellpoint.wpd.business.framework.bo.MetaObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.lock.Lock;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.InformationElement;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.exception.lock.LockException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedByAnotherUserException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedBySameUserException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.security.domain.UserImpl;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LockManager.java 31460 2007-08-26 02:39:12Z U12046 $
 */
public class LockManager {

    public static final String KEY_ATTRIBUTE_SEPARATOR = "~";

    /**
     * Lock the business object for the user for the specified duration
     * 
     * @param object
     * @param user
     * @param duration
     * @return
     * @throws WPDException
     */
    public boolean lock(Object object, User user, int duration)
            throws LockException {
    	if(duration > 99 || duration < 1){
    		throw new LockException("Duration should be greater than or equal to 1 or less than 99",null,null);
    	}
        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        String keyValues = concatenatedKeys.toString();
        LockFactory lockFactory = (LockFactory) ObjectFactory
                .getFactory(ObjectFactory.LOCK);
        String businessObjectName = object.getClass().getName();
        //Get the current time using AuditFactory
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        long longCurrentTime = audit.getTime().getTime();
        Timestamp currentTime = new Timestamp(longCurrentTime);
        String userId = user.getUserId();
        try {
            if (lockObject == null) {
                lockObject = new Lock(businessObjectName, keyValues, userId,
                        duration, currentTime);
                lockFactory.insertLock(lockObject);
                return true;
            } else {
            	// Get the expiry time in secoonds
                long expiryTime = new Timestamp((lockObject.getLockTimestamp()).getTime()/1000
                                   + (long)lockObject.getDuration() * 24 * 60 * 60).getTime();
                if ((longCurrentTime/1000) >= expiryTime) {
                    lockObject.setDuration(duration);
                    lockObject.setLockTimestamp(currentTime);
                    lockObject.setLockUserId(userId);
                    lockFactory.updateLock(lockObject);
                    return true;
                } else {
                    if (userId.equals(lockObject.getLockUserId())) {
                        throw new LockedBySameUserException(
                                "Objects locked by the same user", null, null);
                    } else {
                        throw new LockedByAnotherUserException(
                                "Object locked by another user", null, null);
                    }
                }
            }
        } catch (SQLException e) {
            throw new LockException(
                    "Error while trying to access the lock information", null,
                    e);
        }
    }

    public boolean unlock(Object object, User user) throws LockException {

        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        String keyValues = concatenatedKeys.toString();
        LockFactory lockFactory = (LockFactory) ObjectFactory
                .getFactory(ObjectFactory.LOCK);
        String businessObjectName = object.getClass().getName();

        try {
            if (lockObject == null)
                return true;
            else {
                lockFactory.deleteLock(lockObject);
                return true;
            }
        } catch (SQLException e) {
            throw new LockException(
                    "Error while trying to delete the lock information", null,
                    e);
        }
    }

    public boolean update(Object object, User user, int duration)
            throws LockException {
    	if(duration > 99 || duration < 1){
    		throw new LockException("Duration should be greater than or equal to 1 or less than 99",null,null);
    	}
        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        String keyValues = concatenatedKeys.toString();
        LockFactory lockFactory = (LockFactory) ObjectFactory
                .getFactory(ObjectFactory.LOCK);
        String businessObjectName = object.getClass().getName();
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        long longCurrentTime = audit.getTime().getTime();
        Timestamp currentTime = new Timestamp(longCurrentTime);
        String userId = user.getUserId();
        try {
            if (lockObject == null) {
                lockObject = new Lock(businessObjectName, keyValues, userId,
                        duration, currentTime);
                lockFactory.insertLock(lockObject);
                return true;
            } else {
                lockObject.setDuration(duration);
                lockObject.setLockTimestamp(currentTime);
                lockObject.setLockUserId(userId);
                lockFactory.updateLock(lockObject);
                return true;
            }
        } catch (SQLException e) {
            throw new LockException(
                    "Error while trying to delete the lock information", null,
                    e);
        }
    }

    public boolean update(Object oldObjectKey, Object newObjectKey, User user,
            int duration) throws LockException {
    	if(duration > 99 || duration < 1){
    		throw new LockException("Duration should be greater than or equal to 1 or less than 99",null,null);
    	}
        MetaObjectFactory factory = (MetaObjectFactory) ObjectFactory
                .getFactory(ObjectFactory.METAOBJECT);
        MetaObject metaObj = null;
        try {
            metaObj = factory.getMetaObject(oldObjectKey);
        } catch (MetadataParserException e) {
            throw new LockException(
                    "Error occured while parsing the metadata definition",
                    null, e);
        }
        List keyAttributes = metaObj.getKeyAttributes();
        String businessObjectName = metaObj.getName();
        String oldKeyValues = "";
        String newKeyValues = "";
        for (int keyCnt = 0; keyCnt < keyAttributes.size(); keyCnt++) {
            InformationElement infoElmnt = (InformationElement) keyAttributes
                    .get(keyCnt);

            Object oldKeyValue = null;
            Object newKeyValue = null;
            try {
                oldKeyValue = ReflectionUtil
                        .getValueOfBusinessAttributeByReflection(oldObjectKey,
                                infoElmnt.getElementName());
                newKeyValue = ReflectionUtil
                        .getValueOfBusinessAttributeByReflection(newObjectKey,
                                infoElmnt.getElementName());
                if (oldKeyValue == null || "".equals(oldKeyValue)
                        || newKeyValue == null || "".equals(newKeyValue)) {
                    List params = new ArrayList();
                    params.add(oldObjectKey);
                    params.add(newObjectKey);
                    throw new SevereException("Key value(s) is(are) missing",
                            params, null);
                }
                if ("".equals(oldKeyValues)) {
                    oldKeyValues = oldKeyValues + oldKeyValue.toString().toLowerCase();
                } else {
                    oldKeyValues = oldKeyValues + KEY_ATTRIBUTE_SEPARATOR
                            + oldKeyValue.toString().toLowerCase();
                }
                if ("".equals(newKeyValues)) {
                    newKeyValues = newKeyValues + newKeyValue.toString().toLowerCase();
                } else {
                    newKeyValues = newKeyValues + KEY_ATTRIBUTE_SEPARATOR
                            + newKeyValue.toString().toLowerCase();
                }
            } catch (WPDException wpdException) {
                throw new LockException("Key value(s) is(are) missing", null,
                        wpdException);
            }
        }

        Lock oldLockKeyObject = new Lock(businessObjectName, oldKeyValues, "", 0,
                null);
        LockFactory lockFactory = (LockFactory) ObjectFactory
                .getFactory(ObjectFactory.LOCK);
        Lock lockObject = null;

        try {
            lockObject = lockFactory.retrieveLock(oldLockKeyObject);
            if(lockObject == null){
                List params = new ArrayList();
                params.add(oldObjectKey);
                throw new LockException("Lock not already present for update", params, null);
            }
            try{
                Lock newLockKeyObject = new Lock(lockObject.getBusinessObjectName(), newKeyValues, lockObject.getLockUserId(), lockObject.getDuration(), lockObject.getLockTimestamp());
                lockFactory.updateLock(lockObject, newLockKeyObject);
                return true;
            }catch(SQLException e){
                List params = new ArrayList();
                params.add(oldObjectKey);
                params.add(newObjectKey);
                throw new LockException(
                        "Error while trying to change the lock key information from old to new",
                        params, e);                
            }
        } catch (SQLException e) {
            List params = new ArrayList();
            params.add(oldObjectKey);
            throw new LockException(
                    "Error while trying to retrieve the lock key information for the old object",
                    params, e);
        }
    }
    
    public boolean isLocked(Object object) throws LockException {
        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        if (lockObject == null)
            return false;
        else {
            long expiryTime = new Timestamp((lockObject.getLockTimestamp())
                    .getTime()/1000
                    + (long)lockObject.getDuration() * 24 * 60 * 60).getTime();
            AuditFactory auditFactory = (AuditFactory) ObjectFactory
                    .getFactory(ObjectFactory.AUDIT);
            Audit audit = auditFactory.getAudit(new UserImpl());
            long longCurrentTime = audit.getTime().getTime();
            if (longCurrentTime/1000 >= expiryTime) {
            	// Lock expired
                return false;
            }
        }
        return true;
    }

    public boolean isLocked(Object object, User user) throws LockException {
        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        String keyValues = concatenatedKeys.toString();
        //Get the current time using AuditFactory
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(user);
        long longCurrentTime = audit.getTime().getTime();
        Timestamp currentTime = new Timestamp(longCurrentTime);
        String userId = user.getUserId();
        if (lockObject == null) {
            return false;
        } else {
            long expiryTime = new Timestamp((lockObject.getLockTimestamp())
                    .getTime()/1000
                    + (long)lockObject.getDuration() * 24 * 60 * 60).getTime();
            if (longCurrentTime/1000 >= expiryTime) {
            	// Expired lock
                return false;
            } else {
                if (userId.equalsIgnoreCase(lockObject.getLockUserId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLockExpired(Object object) throws LockException {
        StringBuffer concatenatedKeys = new StringBuffer();
        Lock lockObject = retrieveLock(object, concatenatedKeys);
        //Get the current time using AuditFactory
        AuditFactory auditFactory = (AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT);
        Audit audit = auditFactory.getAudit(new UserImpl());
        long longCurrentTime = audit.getTime().getTime();
        Timestamp currentTime = new Timestamp(longCurrentTime);
        if (lockObject == null) {
            throw new LockException("Lock entry not found", null, null);
        } else {
            long expiryTime = new Timestamp((lockObject.getLockTimestamp())
                    .getTime()/1000
                    + (long)lockObject.getDuration() * 24 * 60 * 60).getTime();
            if (longCurrentTime/1000 >= expiryTime) {
                return true;
            } 
        }
        return false;
    }

    private Lock retrieveLock(Object object, StringBuffer concatenatedKeys)
            throws LockException {

        MetaObjectFactory factory = (MetaObjectFactory) ObjectFactory
                .getFactory(ObjectFactory.METAOBJECT);
        MetaObject metaObj = null;
        try {
            metaObj = factory.getMetaObject(object);
        } catch (MetadataParserException e) {
            throw new LockException(
                    "Error occured while parsing the metadata definition",
                    null, e);
        }
        List keyAttributes = metaObj.getKeyAttributes();
        String businessObjectName = metaObj.getName();
        String keyValues = "";
        for (int keyCnt = 0; keyCnt < keyAttributes.size(); keyCnt++) {
            InformationElement infoElmnt = (InformationElement) keyAttributes
                    .get(keyCnt);

            Object keyValue = null;
            try {
                keyValue = ReflectionUtil
                        .getValueOfBusinessAttributeByReflection(object,
                                infoElmnt.getElementName());
                if (keyValue == null) {
                    List params = new ArrayList();
                    params.add(object);
                    throw new SevereException("Key value(s) is(are) missing",
                            params, null);
                }
                if ("".equals(keyValues)) {
                    keyValues = keyValues + keyValue.toString().toLowerCase();
                } else {
                    keyValues = keyValues + KEY_ATTRIBUTE_SEPARATOR
                            + keyValue.toString().toLowerCase();
                }
            } catch (WPDException wpdException) {
                throw new LockException("Key value(s) is(are) missing", null,
                        wpdException);
            }
        }
        concatenatedKeys.append(keyValues);
        Lock lockKeyObject = new Lock(businessObjectName, keyValues, "", 0,
                null);
        LockFactory lockFactory = (LockFactory) ObjectFactory
                .getFactory(ObjectFactory.LOCK);
        Lock lockObject = null;

        try {
            lockObject = lockFactory.retrieveLock(lockKeyObject);
        } catch (SQLException e) {
            throw new LockException(
                    "Error while trying to retrieve the lock information",
                    null, e);
        }
        return lockObject;
    }
        
}