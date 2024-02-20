/*
 * SecurityFactoryImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import com.wellpoint.wpd.common.framework.security.domain.Role;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.security.domain.UserImpl;
import com.wellpoint.wpd.db.SecurityDao;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SecurityFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class SecurityFactoryImpl extends ObjectFactory implements
        SecurityFactory {

    private SecurityDao securityDao;
    /**
     * @return Returns the securityDataDao.
     */
    public SecurityDao getSecurityDao() {
        return securityDao;
    }
    /**
     * @param securityDataDao The securityDataDao to set.
     */
    public void setSecurityDao(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }
    /**
     * 
     */
    public SecurityFactoryImpl() {
        super();
    }
    
    public Role getRole(String roleName){
        return (Role)securityDao.getRole(roleName);
    }

	public User getUserObject(Role role, String userId) {
		UserImpl userObject = new UserImpl();
		userObject.addRole(role);
		userObject.setUserId(userId);
		return userObject;
	}
}
