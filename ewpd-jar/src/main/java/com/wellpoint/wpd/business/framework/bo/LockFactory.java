/*
 * LockFactory.java
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

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LockFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface LockFactory {
    void insertLock(Lock lockObject) throws SQLException;
    void updateLock(Lock lockObject) throws SQLException;
    void updateLock(Lock oldLockObject, Lock newLockObject) throws SQLException;
    void deleteLock(Lock lockKeyObject) throws SQLException;
    Lock retrieveLock(Lock lockKeyObject) throws SQLException;
}
