package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.domain.service.LockedVariableAuditReportService;

public class LockedVariableAuditReportFacadeImpl implements LockedVariableAuditReportFacade {

	private LockedVariableAuditReportService lockedVariableAuditReportService;
	
	/**
	 * Method to get the EBX locked variable audits
	 */
	public List getEbxLockedVariableAudits(String startDate, String endDate) 
		throws EBXException, Exception{
		List auditList = null;
		auditList = lockedVariableAuditReportService.getEbxLockedVariableAudits(startDate, endDate);
		return auditList;
	}
	
	/**
	 * Method to get the LG locked variable audits
	 */
	public List getLgLockedVariableAudits(String systemIndicator, String startDate, String endDate) 
		throws EBXException, Exception{
		List auditList = null;
		auditList = lockedVariableAuditReportService.getLgLockedVariableAudits(systemIndicator, startDate, endDate);
		return auditList;
	}
	
	/**
	 * Method to get the ISG locked variable audits
	 */
	public List getIsgLockedVariableAudits(String systemIndicator, String startDate, String endDate) 
		throws EBXException, Exception{
		List auditList = null;
		auditList = lockedVariableAuditReportService.getIsgLockedVariableAudits(systemIndicator, startDate, endDate);
		return auditList;
	}

	public LockedVariableAuditReportService getLockedVariableAuditReportService() {
		return lockedVariableAuditReportService;
	}

	public void setLockedVariableAuditReportService(
			LockedVariableAuditReportService lockedVariableAuditReportService) {
		this.lockedVariableAuditReportService = lockedVariableAuditReportService;
	}

}


