/*
 * <OOAMessageDAO.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.ooamessage.dao;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
/*
 * This interface contains the method for maintaining ooa message 
 */
/**
 * @author AF17776
 */
public interface OOAMessageDAO {
	
	public List getOOAMessageSearchDetails(String search, String searchCriteria, String viewOrSearchFunction, String exchangeInd, String messageId) throws EBXException, Exception;
	
	public Boolean createOOAMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean addDateSegmentToMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean updateNetworkOrContractStatus(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	public Boolean modifyOOAMessage(MaintainOOAMessageRequest maintainOOAMessageRequest);
	
	//delete related methods start
	public List retrieveOOAMessageData(String tableName,String columnName,Integer messageId,String netWorkOrContractId);
		
	//public void updateMsgAssociationDates(String tableName,String columnName,List<MaintainOOAMessageRequest> list,MaintainOOAMessageRequest contractVo);
		
	public List<MaintainOOAMessageRequest> retrieveOOAMessageDataByMessageID(String tableName,String columnName,Integer messageId);
		
		
	public Boolean deleteOOAMessage(String deleteType, Integer messageId, String netWorkOrContractId,String columnName,String msgIDClmName,MaintainOOAMessageRequest maintainOOAMessageRequest);		
	//delete related methods end
	
	public List<MaintainOOAMessageRequest> getAllRecordsForExcelReport(String tableName,String columnName,String msgFlag,String exportType);

	public List<MaintainOOAMessageRequest> getOOAMessagesForDuplicateDatesCheck(
			MaintainOOAMessageRequest maintainOOAMessageRequest);
}
