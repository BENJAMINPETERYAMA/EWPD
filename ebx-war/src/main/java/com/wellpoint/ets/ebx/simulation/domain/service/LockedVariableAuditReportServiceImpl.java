/*
 * <ContractServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.repository.LockAuditRepository;

/**
 * @author UST-GLOBAL
 * 
 */
public class LockedVariableAuditReportServiceImpl implements
		LockedVariableAuditReportService {

	private LockAuditRepository lockAuditRepository;

	public static final Logger log = Logger
			.getLogger(LockedVariableAuditReportServiceImpl.class);

	/**
	 * Method to get the EBX locked variable audits
	 */
	public List getEbxLockedVariableAudits(String startDate, String endDate)
			throws EBXException, Exception {
		List auditList = null;
		auditList = lockAuditRepository.getEbxLockedVariableAudits(startDate,
				endDate);
		return auditList;
	}

	/**
	 * Method to get the LG locked variable audits
	 */
	public List getLgLockedVariableAudits(String systemIndicator,
			String startDate, String endDate) throws EBXException, Exception {
		List auditList = null;
		auditList = lockAuditRepository.getLgLockedVariableAudits(
				systemIndicator, startDate, endDate);
		return auditList;
	}

	/**
	 * Method to get the ISG locked variable audits
	 */
	public List getIsgLockedVariableAudits(String systemIndicator,
			String startDate, String endDate) throws EBXException, Exception {
		List auditList = null;
		auditList = lockAuditRepository.getIsgLockedVariableAudits(
				systemIndicator, startDate, endDate);
		return auditList;
	}

	public LockAuditRepository getLockAuditRepository() {
		return lockAuditRepository;
	}

	public void setLockAuditRepository(LockAuditRepository lockAuditRepository) {
		this.lockAuditRepository = lockAuditRepository;
	}

}
