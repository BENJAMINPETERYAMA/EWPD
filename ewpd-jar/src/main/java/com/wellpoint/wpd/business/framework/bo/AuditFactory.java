/*
 * AuditFactory.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AuditFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface AuditFactory {
	Audit getAudit(User user);
	Audit getAudit();
}

