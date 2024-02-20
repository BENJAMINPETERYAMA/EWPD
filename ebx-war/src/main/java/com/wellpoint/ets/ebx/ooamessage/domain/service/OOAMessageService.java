/*
 * <OOAMessageService.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.ooamessage.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
/*
 * This interface contains the method for maintaining ooa message 
 */
/**
 * @author AF17776
 */
public interface OOAMessageService {
	
	public List<MaintainOOAMessageRequest> searchOOAMessageDetails(String search, String searchCriteria, String viewOrSearchFunction, String exchangeInd, String messageId) throws EBXException, Exception;   //search variable describes the radio button in the screen, whether network id or contract code....
    
	public Boolean createOOAMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean addDateSegmentToMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean updateNetworkOrContractStatus(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean modifyOOAMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);

	public Boolean deleteOOAMessage(String contractId, Integer messageId, String netWorkOrContractId,MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public List getAllRecordsForExcelReport(String exportType);

	public String duplicateDatesCheck(MaintainOOAMessageRequest maintainOOAMessageRequest);

}
