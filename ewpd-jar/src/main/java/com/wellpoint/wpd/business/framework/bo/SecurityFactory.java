/*
 * SecurityFactory.java
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

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SecurityFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface SecurityFactory {
   Role getRole(String roleName);
   User getUserObject(Role role, String userId);

}
