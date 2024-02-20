package com.wellpoint.ets.ebx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public interface ContractBxReportFacade {

	public List getReportInfo(ContractVO contract,String environment, boolean eBxReportFlag,SystemConfigurationVO systemConfigurationVO) throws EBXException, Exception;

	public String getLatestVersion(String contractId);

	public List<String> getStartDates(String system, String contractId, String enviornment);
}
