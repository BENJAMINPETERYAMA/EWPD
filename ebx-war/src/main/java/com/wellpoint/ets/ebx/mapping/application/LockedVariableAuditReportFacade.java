package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;

public interface LockedVariableAuditReportFacade {
	
	/**
	 * Method to get the EBX locked variable audits
	 */
	public List getEbxLockedVariableAudits(String startDate, String endDate) throws EBXException, Exception;
	
	/**
	 * Method to get the LG locked variable audits
	 */
	public List getLgLockedVariableAudits(String systemIndicator, String startDate, String endDate) throws EBXException, Exception;
	
	/**
	 * Method to get the ISG locked variable audits
	 */
	public List getIsgLockedVariableAudits(String systemIndicator, String startDate, String endDate) throws EBXException, Exception;
}
