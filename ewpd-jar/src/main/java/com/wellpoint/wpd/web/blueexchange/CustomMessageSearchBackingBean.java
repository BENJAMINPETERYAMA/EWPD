/*
 * CustomMessageSearchBackingBean.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.blueexchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.request.DeleteCustomMessageRequest;
import com.wellpoint.wpd.common.blueexchange.request.SearchCustomMessageRequest;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageRetrieveResponse;
import com.wellpoint.wpd.common.blueexchange.response.DeleteCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 *  * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class CustomMessageSearchBackingBean extends WPDBackingBean {
	
	private String customMessageSearch;

	private List searchResults;

	private String retrieveCustomMessageText;

	private String headerRuleId;

	private String headerRuleDesc;

	private String spsId;

	private String spsDescription;

	private String messageText;

	private String messageIndicator;

	private String createdUser;

	private String lastUpdatedUser;
	
	private String noteTypeCode;

	private Date createdDate;

	private Date lastUpdatedDate;
	
			
	private Date lastChangedDate;
	//for Search
	private String spsParameterForList;
	private String headerRuleForList;
	private List spsParameterList;
	private List headerRuleList;
	private String messageIndicatorTxt;
	private List validationMessages = new ArrayList();	
	

	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return Returns the headerRuleDesc.
	 */
	public String getHeaderRuleDesc() {
		return headerRuleDesc;
	}

	/**
	 * @param headerRuleDesc
	 *            The headerRuleDesc to set.
	 */
	public void setHeaderRuleDesc(String headerRuleDesc) {
		this.headerRuleDesc = headerRuleDesc;
	}

	/**
	 * @return Returns the lastUpdatedDate.
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the messageIndicator.
	 */
	public String getMessageIndicator() {
		return messageIndicator;
	}
	/**
	 * @param messageIndicator The messageIndicator to set.
	 */
	public void setMessageIndicator(String messageIndicator) {
		this.messageIndicator = messageIndicator;
	}
	/**
	 * @return Returns the messageText.
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText
	 *            The messageText to set.
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	/**
	 * @return Returns the spsDescription.
	 */
	public String getSpsDescription() {
		return spsDescription;
	}

	/**
	 * @param spsDescription
	 *            The spsDescription to set.
	 */
	public void setSpsDescription(String spsDescription) {
		this.spsDescription = spsDescription;
	}

	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}

	/**
	 * @param headerRuleId
	 *            The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	/**
	 * @return Returns the spsId.
	 */
	public String getSpsId() {
		return spsId;
	}

	/**
	 * @param spsId
	 *            The spsId to set.
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	/**
	 * Method for locate CustomMessageText.
	 * Validations --- Atlest one valid input is present,Search Input contains more than 1000 records
	 * This method call CustomMessageBusinessService by passing SearchCustomMessageRequest
	 * SearchCustomMessageRequest contains CustomMessageTextVO 
	 * SearchCustomMessageResponse contains search result list
	 * 
	 * @param headerRuleId,SPS Id,Message Indicator,message text
	 * @return Search result list
	 */
	
	public String customMessageSearch(){
		SearchCustomMessageRequest searchCustomMessageRequest = 
			(SearchCustomMessageRequest)this.getServiceRequest(ServiceManager.SEARCH_CUSTOM_MESSAGE_REQUEST);
		if (!validate()){
			addAllMessagesToRequest(this.validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		searchCustomMessageRequest=setValuesToRequest(searchCustomMessageRequest);
		if(!validateList()){
			addAllMessagesToRequest(this.validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		searchCustomMessageRequest.setAction(BusinessConstants.LOCATE_CUSTOM_MESSAGES);
		SearchCustomMessageResponse searchCustomMessageResponse = 
			(SearchCustomMessageResponse) executeService(searchCustomMessageRequest);
		if(null!=searchCustomMessageResponse.getSearchResultList() && searchCustomMessageResponse.getSearchResultList().size()>0){
		searchResults = searchCustomMessageResponse.getSearchResultList();
		}
		return WebConstants.EMPTY_STRING;
	}
	/*
	 * This method for validating search inputs-atleast one search criteria is present?
	 * Also set a validation message if search inputs are invalid
	 * @ return boolean 
	 */
	private boolean validate(){
		boolean valid = true;
		if(isEmpty(this.spsParameterForList) && isEmpty(this.headerRuleForList) 
				&& isEmpty(this.messageText)&& isEmpty(this.messageIndicatorTxt))
			valid = false;
		if (!valid) {
	    	validationMessages.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));     
	    }
		return valid;
	}
	/*
	 * Method for checking whether the input string is empty 
	 * @ param String
	 * @ return boolean 
	 */
	private boolean isEmpty(String testString){
		boolean valid =false;
		if(null == testString || "".equals(testString))
		valid=true;
		return valid;
	}
	/*
	 * method for setting all input values to request 
	 * 	it separate tilda separated input string to a input list
	 * @ input SearchCustomMessageRequest
	 * @ returns SearchCustomMessageRequest contains CustomMessageTextVO that hold searchinputs(spsidlist,headerule id list,messaeg text and message indicator)
	 */
	private SearchCustomMessageRequest setValuesToRequest(SearchCustomMessageRequest searchCustomMessageRequest){
		
		CustomMessageTextVO customMessageTextVO = new CustomMessageTextVO();	
		spsParameterList = WPDStringUtil.getListFromTildaString(this.spsParameterForList, 2, 2, 2);
		headerRuleList = WPDStringUtil.getListFromTildaString(this.headerRuleForList, 2, 2, 2);
		customMessageTextVO.setSpsParameterList(spsParameterList);
		customMessageTextVO.setHeaderRuleList(headerRuleList);
		if(null!=this.messageText && !("").equals(this.messageText))
		customMessageTextVO.setMessagetext(this.messageText);
		if(null!=this.messageIndicatorTxt && !("").equals(this.messageIndicatorTxt)){
				if(("YES").equals(this.messageIndicatorTxt))
					customMessageTextVO.setMessageReqIndicator("Y");
				else
					customMessageTextVO.setMessageReqIndicator("N");
		}
		searchCustomMessageRequest.setCustomMessageTextVO(customMessageTextVO);			
		return searchCustomMessageRequest;
	}
	/*
	 * validation method that validate whether the search input list contains more than 100 records
	 * if record is more than 1000 
	 * 			returns valid(false)
	 * 			else valid(true)
	 * 
	 */
	private boolean validateList(){
		boolean valid =true;
		StringBuffer listOfmessage = new StringBuffer("");
		if(null != this.spsParameterList && this.spsParameterList.size() >1000){
			listOfmessage.append("SPS Parameter ID");
			valid = false;
		}if(null != this.headerRuleList && this.headerRuleList.size()>1000){
			if(null!=listOfmessage && !("").equals(listOfmessage.toString()) )
			listOfmessage.append(",");
			listOfmessage.append("Header Rule");
				valid = false;
			}
		if(!valid){
			ErrorMessage message = new ErrorMessage(
					WebConstants.SEARCH_GREATER_LIMIT_SIZE);
		  	message.setParameters(new String[] {listOfmessage.toString()});
			validationMessages.add(message);
		}
		return valid;
		
	}
	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * This method is called when user clicks view option. It gets the
	 * spsParameter Id and headerRuleId from the requestParameter and set to VO
	 * object and then VO is set to request. This request is sent to
	 * CustomMessageBusinessService and the response is received back as value Object
	 * from businessService. CustomMessageText view attributes of backing bean are
	 * set from the value object.
	 * 
	 * @return Returns the retrieveCustomMessageText.
	 */
	public String getRetrieveCustomMessageText() {
		CustomMessageRetrieveRequest messageRetrieveRequest = (CustomMessageRetrieveRequest) this
				.getServiceRequest(ServiceManager.CUSTOM_MESSAGE_RETRIEVE_REQUEST);
		CustomMessageTextVO messageTextVO = new CustomMessageTextVO();
		if (null != getRequest().getParameter(WebConstants.REQUEST_HEADER_RULE_ID) && 
				null != getRequest().getParameter(WebConstants.REQUEST_SPS_ID)) {
			String headerRuleId = getRequest().getParameter(WebConstants.REQUEST_HEADER_RULE_ID);
			String spsId = getRequest().getParameter(WebConstants.REQUEST_SPS_ID);
		
			messageTextVO.setHeaderRuleId(headerRuleId);
			messageTextVO.setSpsParameterId(spsId);
		} else {			
			CustomMessageSessionObject messageSessionObject = (CustomMessageSessionObject)getSession().getAttribute(WebConstants.SESSION_OBJECT_NAME);
			if(null != messageSessionObject)
				messageTextVO.setHeaderRuleId(messageSessionObject.getHeaderRuleId());
				messageTextVO.setSpsParameterId(messageSessionObject.getSpsParameterId());
		}		
		messageRetrieveRequest.setMessageTextVO(messageTextVO);		
		CustomMessageRetrieveResponse messageRetrieveResponse = (CustomMessageRetrieveResponse) this
				.executeService(messageRetrieveRequest);
		if (null != messageRetrieveResponse) {
			setValuesToBackingBean(messageRetrieveResponse.getMessageTextVO());
		}
		this
				.setBreadCrumbText("Administration>> Blue Exchange >> Custom Message Text ("
						+this.headerRuleId+ "-" +  this.spsId + ") >> View");
		CustomMessageSessionObject messageSessionObject = new CustomMessageSessionObject();
		messageSessionObject.setHeaderRuleId(this.headerRuleId);
		messageSessionObject.setSpsParameterId(this.spsId);
		getSession().setAttribute(WebConstants.SESSION_OBJECT_NAME,messageSessionObject);
		return retrieveCustomMessageText;
	}

	/**
	 * @param retrieveCustomMessageText
	 *            The retrieveCustomMessageText to set.
	 */
	public void setRetrieveCustomMessageText(String retrieveCustomMessageText) {
		this.retrieveCustomMessageText = retrieveCustomMessageText;
	}
	/**
	 * @return Returns the breadCrumb String.
	 */
	public String getCustomMessagePrintBreadCrumb() {			
		return "Administration>> Blue Exchange >> Custom Message Text ("+this.headerRuleId+"-"+this.spsId+") >> Print";
	}
	
	/*
	 * This method is to get the customMessageText attributes from the response object
	 * and set it to the backing bean customMessageText attributes
	 * 
	 * @param messageTextVO
	 * 
	 */
	private void setValuesToBackingBean(CustomMessageTextVO messageTextVO) {

		this.headerRuleId = messageTextVO.getHeaderRuleId();
		this.headerRuleDesc = messageTextVO.getHeaderRuleDesc();
		this.spsId = messageTextVO.getSpsParameterId();
		this.spsDescription = messageTextVO.getSpsParameterDesc();
		this.messageText = messageTextVO.getMessagetext();
		this.messageIndicator = messageTextVO.getMessageReqIndicator();
		this.createdUser = messageTextVO.getCreatedUser();
		this.createdDate = messageTextVO.getCreatedTimestamp();
		this.lastUpdatedUser = messageTextVO.getLastUpdatedUser();
		this.lastUpdatedDate = messageTextVO.getLastUpdatedTimestamp();
		this.noteTypeCode = messageTextVO.getNoteTypeDesc();

	}
	/**
	 * this method delete the cusotm message text,that in the search result and return remaining search result
	 *  input for delete -- headerRuleid and spsId
	 * This method calls CustomMessageBusinessService by passing DeleteCustomMessageRequest
	 * DeleteCustomMessageRequest contins CustomMessageTextVO, that having (headerRuleId,SPS ID and locateInputs(headerRuleList list,spsParameterList,messageText and messageIndicatorTxt ))
	 * DeleteCustomMessageResponse contains Delete message and LocateResults
	 * 
	 */
	public String  deleteCustomMessage(){
		DeleteCustomMessageRequest deleteCustomMessageRequest = (DeleteCustomMessageRequest) this
		.getServiceRequest(ServiceManager.DELETE_CUSTOM_MESSAGE_REQUEST);
		CustomMessageTextVO customMessageTextVO = new CustomMessageTextVO();
		spsParameterList = WPDStringUtil.getListFromTildaString(this.spsParameterForList, 2, 2, 2);
		headerRuleList = WPDStringUtil.getListFromTildaString(this.headerRuleForList, 2, 2, 2);
		customMessageTextVO.setSpsParameterId(this.spsId);
		customMessageTextVO.setHeaderRuleId(this.headerRuleId);
		customMessageTextVO.setSpsParameterList(this.spsParameterList);
		customMessageTextVO.setHeaderRuleList(this.headerRuleList);
		if(null!=this.messageText && !("").equals(this.messageText))
		customMessageTextVO.setMessagetext(this.messageText);
		if(null!=this.messageIndicatorTxt && !("").equals(this.messageIndicatorTxt)){
				if(("YES").equals((this.messageIndicatorTxt)))
					customMessageTextVO.setMessageReqIndicator("Y");
				else
					customMessageTextVO.setMessageReqIndicator("N");
		}
		deleteCustomMessageRequest.setCustomMessageTextVO(customMessageTextVO);
		DeleteCustomMessageResponse deleteCustomMessageResponse = (DeleteCustomMessageResponse)
			this.executeService(deleteCustomMessageRequest);
		if(null!=deleteCustomMessageResponse.getSearchResults() && deleteCustomMessageResponse.getSearchResults().size()>0){
			this.searchResults = deleteCustomMessageResponse.getSearchResults();
		}
		if(null == this.searchResults || this.searchResults.size()==0){
			setInputtoEmpty();
			}
		return "";
	}
	/*
	 * method for setting the jsp input parameters to "";
	 * 
	 */
	private void setInputtoEmpty(){
		this.spsParameterForList = "";
		this.headerRuleForList = "";
		this.messageIndicatorTxt ="";
		this.messageText ="";
		}
	/**
	 * @return Returns the headerRuleForList.
	 */
	public String getHeaderRuleForList() {
		return headerRuleForList;
	}
	/**
	 * @param headerRuleForList The headerRuleForList to set.
	 */
	public void setHeaderRuleForList(String headerRuleForList) {
		this.headerRuleForList = headerRuleForList;
	}
	/**
	 * @return Returns the headerRuleList.
	 */
	public List getHeaderRuleList() {
		return headerRuleList;
	}
	/**
	 * @param headerRuleList The headerRuleList to set.
	 */
	public void setHeaderRuleList(List headerRuleList) {
		this.headerRuleList = headerRuleList;
	}
	/**
	 * @return Returns the spsParameterForList.
	 */
	public String getSpsParameterForList() {
		return spsParameterForList;
	}
	/**
	 * @param spsParameterForList The spsParameterForList to set.
	 */
	public void setSpsParameterForList(String spsParameterForList) {
		this.spsParameterForList = spsParameterForList;
	}
	/**
	 * @return Returns the spsParameterList.
	 */
	public List getSpsParameterList() {
		return spsParameterList;
	}
	/**
	 * @param spsParameterList The spsParameterList to set.
	 */
	public void setSpsParameterList(List spsParameterList) {
		this.spsParameterList = spsParameterList;
	}
	/**
	 * @return Returns the messageIndicatorTxt.
	 */
	public String getMessageIndicatorTxt() {
		return messageIndicatorTxt;
	}
	/**
	 * @param messageIndicatorTxt The messageIndicatorTxt to set.
	 */
	public void setMessageIndicatorTxt(String messageIndicatorTxt) {
		this.messageIndicatorTxt = messageIndicatorTxt;
	}
	/**
	 * @return Returns the noteTypeCode.
	 */
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	/**
	 * @param noteTypeCode The noteTypeCode to set.
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
}