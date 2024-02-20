/*
 * ServiceTypeMappingSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.blueexchange;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;


import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.blueexchange.request.ServiceTypeMappingDeleteRequest;
import com.wellpoint.wpd.common.blueexchange.request.ViewServiceTypeCodeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingDeleteResponse;
import com.wellpoint.wpd.common.blueexchange.response.ViewServiceTypeCodeMappingResponse;

import com.wellpoint.wpd.common.blueexchange.request.ServiceTypeMappingSearchRequest;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingSearchResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingSearchBackingBean extends WPDBackingBean {
	
	private String eb03Identifier;
	private String headerRule;
	private String isApplicable;
	private List  applicableList;
	private List validationMessages = new ArrayList();
	private List searchResultList; 
	private List mappingList;	
	private String mappingSysIdHidden;
	private String headerRuleId;
	private String sendDynamicInformation="Y";
	private List unMappedRules;
	/**
	 * Action Method for Service Type Mapping Search
	 * @return String
	 */
	public String locate(){
		if (!validate()){
			addAllMessagesToRequest(this.validationMessages);
			return WebConstants.EMPTY_STRING;
			
		}
		ServiceTypeMappingSearchResponse response = (ServiceTypeMappingSearchResponse)this.executeService(getServiceTypeSearchRequest());
		if(null != response && null != response.getSearchResultList() && (!(response.getSearchResultList().isEmpty()))){
			this.searchResultList =response.getSearchResultList();
			Iterator itr = searchResultList.iterator();
			while (itr.hasNext()){
				RuleMapping bo = (RuleMapping)itr.next();
				List codeList = bo.getServiceTypeCodes();
				validateList(codeList);
				String codeString ="";
				if(null != codeList && (!codeList.isEmpty())){
					Iterator itrcode = codeList.iterator();
					if(itrcode.hasNext()){
						codeString = itrcode.next().toString();
					}
					while(itrcode.hasNext()){
						codeString = codeString +", "+itrcode.next().toString(); 
					}
				}
				String abbr = bo.getApplicableToBX();
				bo.setApplicableToBX((abbr.equals("Y"))?"Yes":"No");
				bo.setServiceCodesString(codeString);
			}
		}
		if(response.getMessages()!= null){
			this.validationMessages.addAll(response.getMessages());
			addAllMessagesToRequest(this.validationMessages);
		}
		return WebConstants.EMPTY_STRING;
		
	}

	private void validateList(List codeList){
		for (Iterator iter = codeList.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			if(element == null){
				iter.remove();
			}
				
		}
	}

	
	/**
	 * for deleting ServiceTypeMapping
	 * @return String
	 */
	public String deleteServiceTypeMapping(){
		
		
		ServiceTypeMapping serviceTypeMapping = new ServiceTypeMapping();
		
		ServiceTypeMappingDeleteRequest request = (ServiceTypeMappingDeleteRequest)
        this.getServiceRequest(ServiceManager.DELETE_SERVICETYPEMAPPING);
		int id = 0;
		if(null != this.mappingSysIdHidden){
			id = Integer.parseInt(this.mappingSysIdHidden);
		}
		request.setMappingSysId(id);
		//set id to request
		ServiceTypeMappingDeleteResponse response = 
			(ServiceTypeMappingDeleteResponse)this.executeService(request);
		if(null != response && response.getMessages()!= null ) {
			this.validationMessages.addAll(response.getMessages());
		}
		
		this.locate();
		return "";
		
	}
		
	/**
	 * Method for creating the request and  populating the same with values from the backing bean. 
	 * @return Returns ServiceTypeMappingSearchRequest 
	 */
	private  ServiceTypeMappingSearchRequest getServiceTypeSearchRequest(){
		ServiceTypeMappingSearchRequest request = (ServiceTypeMappingSearchRequest) this
		.getServiceRequest(ServiceManager.SEARCH_SERVICE_TYPE_REQUEST);
		request.setAction(WebConstants.LOCATE_SERVICE_MAPPING);
		if(!this.getIsApplicable().equals("N"))
			request.setEB03List(WPDStringUtil.getListFromTildaString(this.eb03Identifier,2,2,2)); 
		if(this.getIsApplicable().equals("N"))
			this.eb03Identifier = "";
		request.setHeaderRuleList(WPDStringUtil.getListFromTildaString(this.headerRule,2,2,2)); 
		request.setIsApplicable(this.getIsApplicable());
		return request;
	}
	
	private boolean validate(){
		boolean valid = true;
		if((null == this.getEb03Identifier() || "".equals(this.getEb03Identifier()))&&
				(null == this.getIsApplicable() || "".equals(this.getIsApplicable()))&&
				(null == this.getHeaderRule() || "".equals(this.getHeaderRule()))){
		  valid = false;	
		}
		if (!valid) {
	    	validationMessages.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));     
	    }
		return valid;
	}

	
	/**
	 * @return Returns the eb03Identifier.
	 */
	public String getEb03Identifier() {
		return eb03Identifier;
	}
	/**
	 * @param eb03Identifier The eb03Identifier to set.
	 */
	public void setEb03Identifier(String eb03Identifier) {
		this.eb03Identifier = eb03Identifier;
	}
	
	
	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the mappingList.
	 */
	public List getMappingList() {
		return mappingList;
	}
	/**
	 * @param mappingList The mappingList to set.
	 */
	public void setMappingList(List mappingList) {
		this.mappingList = mappingList;
	}
	
		/**
	 * @return Returns the mappingSysIdHidden.
	 */
	public String getMappingSysIdHidden() {
		return mappingSysIdHidden;
	}
	/**
	 * @param mappingSysIdHidden The mappingSysIdHidden to set.
	 */
	public void setMappingSysIdHidden(String mappingSysIdHidden) {
		this.mappingSysIdHidden = mappingSysIdHidden;
	}
	
	public String getLoadUnmappedRules() {
        this.setBreadCrumbText(" Administration >> Blue Exchange >> Unmapped Header Rules");	
		ViewServiceTypeCodeMappingRequest request = 
			(ViewServiceTypeCodeMappingRequest)this.getServiceRequest(ServiceManager.VIEW_SERVICE_TYPE_CODE_MAPPING_REQUEST);
		request.setAction(ViewServiceTypeCodeMappingRequest.RETRIEVE_UNMAPPED_RULES);
		ViewServiceTypeCodeMappingResponse response = (ViewServiceTypeCodeMappingResponse)this.executeService(request);
		if(null != response){
			if(response.getUnMappedRules() != null &&  response.getUnMappedRules().size()> 0)
				this.unMappedRules = response.getUnMappedRules();	
		}			
//		this.unMappedRules = new ArrayList();
//		RuleMapping maping = new RuleMapping();
//		maping.setRuleId("AANCXXX01");
//		maping.setRuleShortDesc("WPD INPATIENT ANCILLARY");
//		this.unMappedRules.add(maping);
		return "";
	}
	
	public void setLoadUnmappedRules(String temp) {
		
	}

	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
		
	/**
	 * @return Returns the sendDynamicInformation.
	 */
	public String getSendDynamicInformation() {
		return sendDynamicInformation;
	}
	/**
	 * @param sendDynamicInformation The sendDynamicInformation to set.
	 */
	public void setSendDynamicInformation(String sendDynamicInformation) {
		this.sendDynamicInformation = sendDynamicInformation;
	}
		/**
	 * @return Returns the unMappedRules.
	 */
	public List getUnMappedRules() {
		return unMappedRules;
	}
	/**
	 * @param unMappedRules The unMappedRules to set.
	 */
	public void setUnMappedRules(List unMappedRules) {
		this.unMappedRules = unMappedRules;
	}
	/**
	 * @return Returns the applicableList.
	 */
	public List getApplicableList() {
		applicableList = new ArrayList();
		applicableList.add(new SelectItem(""));
		applicableList.add(new SelectItem("Y","Yes"));
		applicableList.add(new SelectItem("N","No"));
		return applicableList;
	}
	/**
	 * @param applicableList The applicableList to set.
	 */
	public void setApplicableList(List applicableList) {
		this.applicableList = applicableList;
	}
	/**
	 * @return Returns the headerRule.
	 */
	public String getHeaderRule() {
		return headerRule;
	}
	/**
	 * @param headerRule The headerRule to set.
	 */
	public void setHeaderRule(String headerRule) {
		this.headerRule = headerRule;
	}
	/**
	 * @return Returns the isApplicable.
	 */
	public String getIsApplicable() {
		return isApplicable;
	}
	/**
	 * @param isApplicable The isApplicable to set.
	 */
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}
}
