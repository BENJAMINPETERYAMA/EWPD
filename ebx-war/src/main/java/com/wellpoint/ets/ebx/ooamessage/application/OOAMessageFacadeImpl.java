/*
 * <OOAMessageFacadeImpl.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.ooamessage.application;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.ooamessage.domain.service.OOAMessageService;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;

/*
 * This class contains the method for maintaining ooa message 
 */
/**
 * @author AF17776
 */
public class OOAMessageFacadeImpl implements OOAMessageFacade {
	
	private OOAMessageService oOAMessageService;
	
	private static Logger logger = Logger
			.getLogger(OOAMessageFacadeImpl.class);
	
	public OOAMessageService getoOAMessageService() {
		return oOAMessageService;
	}

	public void setoOAMessageService(OOAMessageService oOAMessageService) {
		this.oOAMessageService = oOAMessageService;
	}

	/**
	 * This method is used to retrieve the ooa message
	 * 
	 * @param search String 
	 * @param searchCriteria String 
	 * @param viewOrSearchFunction String 
	 * @param exchangeInd String 
	 * @param messageId String
	 * @return List - java.util.List Object holding ooa message details
	 */		
	public List searchOOAMessageDetails(String search, String searchCriteria, String viewOrSearchFunction, String exchangeInd, String messageId){
		List OOAMessageSearchDetailsList = null;
		try {
			OOAMessageSearchDetailsList=oOAMessageService.searchOOAMessageDetails(search, searchCriteria, viewOrSearchFunction, exchangeInd, messageId);
			} catch (EBXException ex) {
				logger.error("EBXException occurred in searchOOAMessageDetails Method");
				logger.error(ex.getMessage());
//			e.printStackTrace();
		} catch (Exception ex) {
			logger.error("Exception occurred in searchOOAMessageDetails Method");
			logger.error(ex.getMessage());
//			e.printStackTrace();
		}
		return OOAMessageSearchDetailsList;
		
	}
	
	/**
	 * This method is used to create the ooa message
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message creation status
	 */
	@Override
	public Boolean createOOAMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		return oOAMessageService.createOOAMessage(maintainOOAMessageRequest);
				
	}
	
	/**
	 * This method is used to add date segment to the ooa message
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message add date segment status
	 */
	@Override
	public Boolean addDateSegmentToMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {		 
		return oOAMessageService.addDateSegmentToMessage(maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to update status of the ooa message
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message update status
	 */
	@Override
	public Boolean updateNetworkOrContractStatus(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {		 
		return oOAMessageService.updateNetworkOrContractStatus(maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to modify the ooa message
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message modification status
	 */
	@Override
	public Boolean modifyOOAMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		return oOAMessageService.modifyOOAMessage(maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to delete(just changing the status to D) the ooa message, 
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message modification status
	 */
	@Override
	public Boolean deleteOOAMessage(
			String searchType,Integer messageId, String networkOrContractId,MaintainOOAMessageRequest maintainOOAMessageRequest) {
		return oOAMessageService.deleteOOAMessage(searchType,messageId, networkOrContractId,maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to generate the excel report for ooa message
	 * 
	 * @param exportType String 
	 * @return List - java.util.List Object holding ooa message details
	 */	
	@Override
	public List exportOOAMessageExcelFile(String exportType)
			throws EBXException, IOException {		
		List list=oOAMessageService.getAllRecordsForExcelReport(exportType);		
		return list;
	}

	@Override
	public String duplicateDatesCheck(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		// TODO Auto-generated method stub
		return oOAMessageService.duplicateDatesCheck(maintainOOAMessageRequest);
	}

}
