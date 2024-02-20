/*
 * <ContractInfoRepository.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;

/**
 * @author UST-GLOBAL
 * 
 * Interface for Repository Layer
 * 
 */
public interface LockAuditRepository {

	public List getEbxLockedVariableAudits(String startDate, String endDate) throws EBXException, Exception;
	public List getLgLockedVariableAudits(String systemIndicator, String startDate, String endDate) throws EBXException, Exception;
	public List getIsgLockedVariableAudits(String systemIndicator, String startDate, String endDate) throws EBXException, Exception;
	
}
