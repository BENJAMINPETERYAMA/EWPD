/*
 * <SimulationFacadeImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.application;

import java.util.List;

import javax.jms.JMSException;

import org.apache.xmlbeans.XmlException;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.domain.service.ContractService;
import com.wellpoint.ets.ebx.simulation.domain.service.SimulationService;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author UST-GLOBAL
 * 
 * Facade implementation class. This layer will invoke the service
 * layer of the particular service.
 * 
 */
public class SimulationFacadeImpl implements SimulationFacade {

    private ContractService contractService;

    private SimulationService simulationService;
    /**
     * Method for Error Validation invokation
     */
    public List getContractInfo(ContractVO contract,String environment, boolean eBxReportFlag, SystemConfigurationVO systemConfigurationVO) throws EBXException,Exception {
        return contractService.getContractInfo(contract, environment, eBxReportFlag, systemConfigurationVO);
    }

    /**
     * Method for HIPAABX Service invokation
     * @throws EBXException
     * @throws JMSException
     */
    public String get27xHIPAABX(HIPAA270BXVO hipaa27xBX,String environment, String responseFormat, String senderID) throws EBXException, JMSException {
        return simulationService.get27xHIPAABX(hipaa27xBX,environment,responseFormat, senderID);
    }

    public List get27xBenefitAccumInfo(ContractVO contract,String environment, String responseFormat,
        		SystemConfigurationVO systemConfigurationVO) throws EBXException,XmlException, JMSException,Exception {
        return contractService.get27xBenefitAccumInfo(contract,environment,responseFormat,systemConfigurationVO);

    }
    
    public boolean isInactiveContract(String system, String contractId) throws EBXException, Exception{
    	 return contractService.isInactiveContract(system,contractId);
    }

    public ContractService getContractService() {
        return contractService;
    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    public SimulationService getSimulationService() {
        return simulationService;
    }

    public void setSimulationService(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

	public boolean is4010Exists() throws EBXException, Exception {
		return simulationService.is4010Exists();
	}
 // BXNI June Release change - Start
	
	/**
     * Method to populate the start dates of the LG, ISG and eWPD contracts.
     * @param system
     * @param contractId
     * @return the list of start dates.
     */
	public List<String> getStartDates(String system, String contractId, String enviornment) {
		return contractService.getStartDates(system, contractId, enviornment);
    }
	
	/**
     * Method to get the latest version of eWPD Contract.
     * @param contractId
     * @return 
     */
    public String getLatestVersion(String contractId) {
    	return contractService.getLatestVersion(contractId);
    }
    // BXNI June Release change - End
    
    
	@Override
	public List<SystemConfigurationVO> getBackEndRegionDescription(
			SystemConfigurationVO systemConfigurationVO) throws EBXException {
		return simulationService.getBackEndRegionDescription(systemConfigurationVO);
	}

	@Override
	public List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality, String system,
			String environment) throws EBXException {
		return simulationService.loadBackEndRegionBasedOnSystem(functionality, system,
				environment);
		
	}
}
