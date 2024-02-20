/*
 * AuditFactoryImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.TimeZone;

import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AuditFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class AuditFactoryImpl extends ObjectFactory implements AuditFactory {
	
	private AuditDao auditDao;
	private static TimeZone timeZone;
	/**
	 * @return Returns the auditDao.
	 */
	public AuditDao getAuditDao() {
		return auditDao;
	}
	/**
	 * @param auditDao The auditDao to set.
	 */
	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.AuditFactory#getAudit(java.lang.Object)
	 * @param object
	 * @return
	 */
	public Audit getAudit(User user) {
		AuditImpl audit = new AuditImpl();
		audit.setTime(auditDao.retrieveCurrentDate());
		if(timeZone == null){
		    audit.setTimeZone(auditDao.retrieveTimeZone());
		    timeZone=audit.getTimeZone();
		}else{
		    audit.setTimeZone(timeZone);
		}
		audit.setUser(user.getUserId());
		return audit;
	}
	
	public Audit getAudit(){
	    AuditImpl audit = new AuditImpl();
	    audit.setTime(auditDao.retrieveCurrentDate());
		if(timeZone == null){
		    audit.setTimeZone(auditDao.retrieveTimeZone());
		    timeZone=audit.getTimeZone();
		}else{
		    audit.setTimeZone(timeZone);
		}
	    return audit;
	}

}
