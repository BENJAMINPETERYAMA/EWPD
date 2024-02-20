package com.wellpoint.ets.ebx.simulation.webservices;

import java.util.List;

import javax.jws.WebMethod;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import com.wellpoint.ets.ebx.simulation.webservices.exception.SimulationWebServiceException;
import com.wellpoint.ets.ebx.simulation.webservices.impl.SimulationWebService;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ContractWebServiceVO;

@javax.jws.WebService (targetNamespace="http://impl.webservices.simulation.ebx.ets.wellpoint.com/", serviceName="SimulationWebServiceImplService", portName="SimulationWebServiceImplPort")
public class SimulationWebServiceDelegate extends SpringBeanAutowiringSupport {
	private static Logger logger = Logger.getLogger(SimulationWebServiceDelegate.class);
	@Autowired
	private SimulationWebService simulationWebService;

	@WebMethod(exclude = true)
	public SimulationWebService getSimulationWebService() {
		return simulationWebService;
	}

	@WebMethod(exclude = true)
	public void setSimulationWebService(
			SimulationWebService simulationWebService) {
		this.simulationWebService = simulationWebService;
	}

	@WebMethod
	public List<ContractWebServiceVO> getContractInfo(
			ContractWebServiceVO contractVO, String environment,
			boolean isEBxReportFlag) throws SimulationWebServiceException {
		//System.out.println("WebMethod: simulationWebService-->"+ simulationWebService);
		logger.info("WebMethod: simulationWebService-->"+ simulationWebService);
		return simulationWebService.getContractInfo(contractVO, environment,
				isEBxReportFlag);
	}

	public SimulationWebServiceDelegate() {
	}

}