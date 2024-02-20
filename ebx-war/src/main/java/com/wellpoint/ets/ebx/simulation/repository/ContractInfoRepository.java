/*
 * <ContractInfoRepository.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;

/**
 * @author UST-GLOBAL
 * 
 * Interface for Repository Layer
 * 
 */
public interface ContractInfoRepository {

	public List getContractInfo(ContractVO contract, boolean eBxReportFlag) throws EBXException, Exception;
	
	public boolean isInactiveContract(String contractId) throws EBXException, Exception;
	 
	// BXNI June Release - Start 
	/**
	  * Method to get the start dates of the LG, ISG and eWPD contracts. 
	 * @param contractId
	 * @param enviornment 
	 * @return
	 */ 
	public List<String> getStartDates(String contractId, String enviornment);

	/**
	  * Method to get the latest version of eWPD Contract.
	 * @param contractId
	 * @return
	 * @throws EBXException
	 */ 
	public String getLatestVersion(String contractId);
	// BXNI June Release - End

}
