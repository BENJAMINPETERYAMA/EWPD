/*
 * <OOAMessageServiceImpl.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.ooamessage.domain.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;
import com.wellpoint.ets.ebx.ooamessage.dao.OOAMessageDAO;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
/*
 * This class contains the method for maintaining ooa message 
 */
/**
 * @author AF17776
 */
public class OOAMessageServiceImpl implements OOAMessageService {
	// The variable to hold OOAMessageDAO object
	private OOAMessageDAO oOAMessageDAO;
	
	/**
	 * This method returns an object of type OOAMessageDAO
	 * 
	 * @return oOAMessageDAO an object of type OOAMessageDAO 
	 */	
    public OOAMessageDAO getoOAMessageDAO() {
		return oOAMessageDAO;
	}
    
	/**
	 * This method sets an object of type OOAMessageDAO
	 * 
	 * @param oOAMessageDAO an object of type OOAMessageDAO 
	 */	
	public void setoOAMessageDAO(OOAMessageDAO oOAMessageDAO) {
		this.oOAMessageDAO = oOAMessageDAO;
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
		
  	  List maintainOOAMessageRequestList = null;
  	  try {
  		  maintainOOAMessageRequestList = oOAMessageDAO.getOOAMessageSearchDetails(search, searchCriteria, viewOrSearchFunction, exchangeInd, messageId);
			} catch (EBXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
  	  return maintainOOAMessageRequestList;
  	  
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
		 return oOAMessageDAO.createOOAMessage(maintainOOAMessageRequest);
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
		 return oOAMessageDAO.addDateSegmentToMessage(maintainOOAMessageRequest);
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
		 return oOAMessageDAO.updateNetworkOrContractStatus(maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to modify the ooa message
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return Boolean  - java.lang.Boolean - Representing ooa message modification status
	 */
	@Override
	public Boolean modifyOOAMessage(MaintainOOAMessageRequest maintainOOAMessageRequest) {
		return oOAMessageDAO.modifyOOAMessage(maintainOOAMessageRequest);
	}
	
	/**
	 * This method is used to delete(just changing the status to D) the ooa message, 
	 * 
	 * @param maintainOOAMessageRequest - An object of type MaintainOOAMessageRequest representing the ooa message details
	 * @return String  - java.lang.String
	 */	
	@Override
	public Boolean deleteOOAMessage(String deleteType, Integer messageId, String netWorkOrContractId,MaintainOOAMessageRequest maintainOOAMessageRequest) {
		String tableName = null;
		String columnName = null;
		String msgIDClmName = null;
		if (OOAMessageConstants.contact.equals(deleteType)) {
			tableName = OOAMessageConstants.contactTableName;
			columnName = OOAMessageConstants.contactCode;
			msgIDClmName=OOAMessageConstants.cntrctMsgId;
			
		} else {
			tableName = OOAMessageConstants.networkTableName;
			columnName = OOAMessageConstants.networkId;
			msgIDClmName=OOAMessageConstants.ntwrkMsgId;
		}

		Boolean  flag = oOAMessageDAO.deleteOOAMessage(tableName,messageId,netWorkOrContractId,columnName,msgIDClmName,maintainOOAMessageRequest);
//		if(flag){
//			List<MaintainOOAMessageRequest> list = oOAMessageDAO.retrieveOOAMessageData(tableName, columnName, messageId, netWorkOrContractId);
//			List<MaintainOOAMessageRequest> ooaMessageList = oOAMessageDAO.retrieveOOAMessageDataByMessageID(tableName, columnName, messageId);
//			for(MaintainOOAMessageRequest maintainOOAMessageRequest:ooaMessageList){
//			oOAMessageDAO.updateMsgAssociationDates(tableName, columnName, list, maintainOOAMessageRequest);
//			}
//		}
		return flag;
	}

	
	/**
	 * This method is used to generate the excel report for ooa message
	 * 
	 * @return List - java.util.List Object holding ooa message details
	 */
	@Override
	public List getAllRecordsForExcelReport(String exportType) {
		String tableName = null;
		String columnName = null;
		String msgFlag=null;

		if (OOAMessageConstants.contact.equals(exportType)) {
			//System.out.println("in contract loop");
			tableName = OOAMessageConstants.contactTableName;
			columnName = OOAMessageConstants.contactCode;
			msgFlag=OOAMessageConstants.msgExemptFlag;
			
		} else {
			tableName = OOAMessageConstants.networkTableName;
			columnName = OOAMessageConstants.networkId;
			msgFlag=OOAMessageConstants.exangeIndicator;
		}

		List<MaintainOOAMessageRequest> contractList = oOAMessageDAO
				.getAllRecordsForExcelReport(tableName, columnName,msgFlag,exportType);
		return contractList;
	}

	@Override
	public String duplicateDatesCheck(MaintainOOAMessageRequest maintainOOAMessageRequest) {
		
		Date effectiveDate=maintainOOAMessageRequest.getMessageEffectiveDate();
		Date expiryDate=maintainOOAMessageRequest.getMessageExpiryDate();
		String duplicateDatesFoundMsg="";
		List<MaintainOOAMessageRequest> messages= oOAMessageDAO.getOOAMessagesForDuplicateDatesCheck(maintainOOAMessageRequest);
		
		for (MaintainOOAMessageRequest maintainOOAMessageRequest2 : messages) {
			if(effectiveDate.equals(maintainOOAMessageRequest2.getMessageEffectiveDate())){
				duplicateDatesFoundMsg = " another message having Effective Date as "+formatDate(maintainOOAMessageRequest2.getMessageEffectiveDate())
						+" & Expiry Date as"+formatDate(maintainOOAMessageRequest2.getMessageExpiryDate());
				break;
			}
			
			if(effectiveDate.before(maintainOOAMessageRequest2.getMessageEffectiveDate())){
				if(expiryDate.before(maintainOOAMessageRequest2.getMessageEffectiveDate())){
					
				} else {
					duplicateDatesFoundMsg = " another message having Effective Date as "+formatDate(maintainOOAMessageRequest2.getMessageEffectiveDate())
							+" & Expiry Date as"+formatDate(maintainOOAMessageRequest2.getMessageExpiryDate());
					duplicateDatesFoundMsg="please choose expiry date lessthan "+formatDate(maintainOOAMessageRequest2.getMessageEffectiveDate());
					break;
				}
				
			}
			
			if(effectiveDate.after(maintainOOAMessageRequest2.getMessageEffectiveDate())){
				if(effectiveDate.after(maintainOOAMessageRequest2.getMessageExpiryDate())){
					
				} else {
					duplicateDatesFoundMsg = " another message having Effective Date as "+formatDate(maintainOOAMessageRequest2.getMessageEffectiveDate())
							+" & Expiry Date as"+formatDate(maintainOOAMessageRequest2.getMessageExpiryDate());
					duplicateDatesFoundMsg="please choose effective from date greaterthan "+formatDate(maintainOOAMessageRequest2.getMessageExpiryDate());
					break;
				}
			}

		}
		
		return duplicateDatesFoundMsg;
	} 
	
	public String formatDate(Date date){
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MM/dd/yyyy");
		return simpleDateFormat.format(date);
		
	} 
}
