/*
 * LockFactoryImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.sql.SQLException;

import com.wellpoint.wpd.business.framework.bo.lock.Lock;
import com.wellpoint.wpd.business.framework.bo.lock.LockDao;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LockFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LockFactoryImpl extends ObjectFactory implements LockFactory {

    private LockDao lockDao;
    /**
     * @see com.wellpoint.wpd.business.framework.bo.LockFactory#insertLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public void insertLock(Lock lockObject) throws SQLException {
        lockDao.insertLock(lockObject);
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.LockFactory#updateLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public void updateLock(Lock lockObject) throws SQLException {
        lockDao.updateLock(lockObject);
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.LockFactory#updateLock(com.wellpoint.wpd.business.framework.bo.lock.Lock, com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param oldLockObject
     * @param newLockObject
     * @throws SQLException
     */
    public void updateLock(Lock oldLockObject, Lock newLockObject) throws SQLException {
        lockDao.updateLock(oldLockObject, newLockObject);       
    }
    
    /**
     * @see com.wellpoint.wpd.business.framework.bo.LockFactory#deleteLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockKeyObject
     * @throws Exception
     */
    public void deleteLock(Lock lockKeyObject) throws SQLException {
        lockDao.deleteLock(lockKeyObject);
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.LockFactory#retrieveLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockKeyObject
     * @return
     * @throws Exception
     */
    public Lock retrieveLock(Lock lockKeyObject) throws SQLException {
        return lockDao.retrieveLock(lockKeyObject);
    }

    /**
     * @return Returns the lockDao.
     */
    public LockDao getLockDao() {
        return lockDao;
    }
    /**
     * @param lockDao The lockDao to set.
     */
    public void setLockDao(LockDao lockDao) {
        this.lockDao = lockDao;
    }

}
