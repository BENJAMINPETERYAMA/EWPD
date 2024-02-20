package com.wellpoint.ets.ebx.simulation.webservices.impl;

import java.util.List;

//import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
//import com.wellpoint.ets.ebx.simulation.application.SimulationFacade;
//import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.application.SimulationFacade;
import com.wellpoint.ets.ebx.simulation.webservices.exception.SimulationWebServiceException;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ContractWebServiceVO;
//import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public interface SimulationWebService {
	
	public SimulationFacade getSimulationFacade();
	
	public List<ContractWebServiceVO> getContractInfo(ContractWebServiceVO contractVO,
			String environment, boolean isEBxReportFlag) throws SimulationWebServiceException;
}
