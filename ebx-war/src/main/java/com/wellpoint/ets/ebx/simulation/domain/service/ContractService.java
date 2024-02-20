/*
 * <ContractService.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.util.List;

import javax.jms.JMSException;

import org.apache.xmlbeans.XmlException;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author UST-GLOBAL
 * 
 * Interface for Service Layer
 * 
 */
public interface ContractService {

    public List getContractInfo(ContractVO contract,String environment, boolean eBxReportFlag, SystemConfigurationVO systemConfigurationVO) throws EBXException, Exception;
    //Added for report 

    public boolean isInactiveContract(String system, String contractId) throws EBXException, Exception;
    
    public List get27xBenefitAccumInfo(ContractVO contract,String environment, String responseFormat,
    					SystemConfigurationVO systemConfigurationVO) throws EBXException, JMSException, XmlException, Exception;;
    
    // BXNI June Release change - Start
    /**
     * Method to populate the start dates of the LG, ISG and eWPD contracts.
     * @param system
     * @param contractId
     * @param enviornment 
     * @return
     */
    public  List<String> getStartDates(String system, String contractId, String enviornment);
    
    /**
     * Method to get the latest version of eWPD Contract.
     * @param contractId
     * @return
     * @throws EBXException
     */
    public String getLatestVersion(String contractId);
    // BXNI June Release change - End

    }
