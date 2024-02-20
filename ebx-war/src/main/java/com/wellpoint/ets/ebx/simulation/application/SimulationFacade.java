/*
 * <SimulationFacade.java>
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
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author UST-GLOBAL
 * 
 * Interface for Facade Layer
 * 
 */
public interface SimulationFacade {

    public List getContractInfo(ContractVO contract, String environment, boolean eBxReportFlag, SystemConfigurationVO systemConfigurationVO) throws EBXException, Exception;

    public String get27xHIPAABX(HIPAA270BXVO hipaa27xBX,String environment, String responseFormat, String senderID) throws EBXException, JMSException;

    public boolean isInactiveContract(String system, String contractId) throws EBXException, Exception;
    
    public List get27xBenefitAccumInfo(ContractVO contract,String environment, String responseFormat, SystemConfigurationVO systemConfigurationVO) throws EBXException, JMSException, XmlException, Exception;;
    
    public boolean is4010Exists() throws EBXException, Exception;
    
    // BXNI June Release change - Start
    /**
     * Method to populate the start dates of the LG, ISG and eWPD contracts.
     * @param system
     * @param contractId
     * @param enviornment 
     * @return the list of start dates.
     */
    public List<String> getStartDates(String system, String contractId, String enviornment);
    
    /**
     * Method to get the latest version of eWPD Contract.
     * @param contractId
     * @return 
     * @throws EBXException
     */
    public String getLatestVersion(String contractId);
   
    // BXNI June Release change - End
    /**
     * This method gets the back end region description details based on functionality, environment,back end region
     * @param systemConfigurationVO
     * @return List<SystemConfigurationVO>
     */
	public List<SystemConfigurationVO> getBackEndRegionDescription(SystemConfigurationVO systemConfigurationVO) throws EBXException;

	/**
	 * This function loads back end region for a particular combination of functionality,
	 * system and environment
	 * @param functionality
	 * @param system
	 * @param environment
	 */
	public List<SystemConfigurationVO> loadBackEndRegionBasedOnSystem(String functionality, String system,
			String environment)throws EBXException;
    

}
