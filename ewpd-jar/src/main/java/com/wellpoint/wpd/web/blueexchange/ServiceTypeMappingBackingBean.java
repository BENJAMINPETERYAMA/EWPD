/*
 * ServiceTypeMappingBackingBean.java
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

import javax.faces.context.FacesContext;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.RuleServiceTypeAssociation;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.blueexchange.request.DeleteServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.RetrieveServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.SaveServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.RetrieveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SaveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.vo.ServiceTypeMappingVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingBackingBean extends WPDBackingBean{
	private String eb03Identifier;
	private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;	
    private boolean eb03IdentifierValid = true;
    List messages;
    private String initView;
    private String initPrint;
    private String breadCrumpText;
    
    //new modification 
    
    private String headerRuleId;
    
    private boolean headerRuleIdValdn;
    
    private String blueExcahngeApplicable = "Y";
    
    private String ruleDescription;
    
    private String ruleForEdit;
    
    private List associatedCodeList;
    
    private String deleteCodeIds;
    
    private String blueExcahngeApplicablePrev;
    
    private boolean eb03IdentifierValdn;
    
    private String eb03IdentifierForEdit;
     
    private String ruleId;
    private String validForBX;
    private List eb03IdentifierList;
    private String codeToSave;
    
    private String sendDynamicInfoCodes;
    
    private String sendDynamicInfoValues;
    
    private String sendDynamicInfo ="Y";
    
    
    
	
    /**
     * Action method for Service type mapping Create.
     * @return pageNavigation String
     */
	public String createAction(){
		messages = new ArrayList();
		
		// Checking values for fields are valid.
		if(isFieldsValid()) {
			
			// Creating request.
			SaveServiceTypeMappingRequest request = (SaveServiceTypeMappingRequest)getServiceRequest(ServiceManager.SAVE_SERVICE_TYPE_MAPPING_REQUEST);
			
			// Setting the given values to request.
			setValuesToRequest(request);
			
			// Setting action to Create.
			request.setAction(SaveServiceTypeMappingRequest.CREATE_ACTION);
			
			// Invoking business Service.
			SaveServiceTypeMappingResponse response =  (SaveServiceTypeMappingResponse)this.executeService(request);

			// If create operation is success
			if(response != null && response.isSuccess()) {
				
				// Setting the values from the service to backing bean.
				setValuesToBackingBean(response.getRuleMapping());
				
				// Navigating to Edit page.
				return WebConstants.SERVICE_TYPE_MAPPING_EDIT_PAGE;
			}
		} else {
			// Setting error messages to Request.
			addAllMessagesToRequest(messages);
		}
		return WebConstants.EMPTY_STRING;
	}
	
    /**
     * Action method for Service type mapping Save for Edit Page.
     * @return pageNavigation String
     */
	public String saveAction() {
		messages = new ArrayList();
		List sendInfoCodeList = null;
		List sendInfoCodeValue = null;
		
		// 	Checking values for fields are valid.
		
			
			// Creating request.
			SaveServiceTypeMappingRequest request = (SaveServiceTypeMappingRequest)getServiceRequest(ServiceManager.SAVE_SERVICE_TYPE_MAPPING_REQUEST);
			
			// 	Setting the given values to request.
			setValuesToRequest(request);
			
			if(null!=this.sendDynamicInfoCodes && !("").equals(this.sendDynamicInfoCodes)){
				sendInfoCodeList =(ArrayList)tiladaSeparation(this.sendDynamicInfoCodes);
				request.setSendInfoCodeList(sendInfoCodeList);
				}
				if(null!=this.sendDynamicInfoValues && !("").equals(this.sendDynamicInfoValues)){
				sendInfoCodeValue = (ArrayList)tiladaSeparation(this.sendDynamicInfoValues);
				request.setSendInfoCodeValue(sendInfoCodeValue);
			}
				
			// Setting action to Update.
			request.setAction(SaveServiceTypeMappingRequest.UPDATE_ACTION);
			
			
			// Invoking business Service.
			SaveServiceTypeMappingResponse response =  (SaveServiceTypeMappingResponse)this.executeService(request);

			// If update operation is success
			if(response != null && response.isSuccess()) {
				setValuesToBackingBean(response.getRuleMapping());
				setRuleIdToSession(this.headerRuleId);
			}
		
		return WebConstants.EMPTY_STRING; 
	}
	


	/**
	 * Action method for Navigating to Edit Page.
	 * @return pageNavigation String
	 */
	public String editAction() {
		
		RetrieveServiceTypeMappingRequest request = (RetrieveServiceTypeMappingRequest)getServiceRequest(ServiceManager.RETRIEVE_SERVICE_TYPE_MAPPING_REQUEST);;
		request.setRuleId(this.ruleId);
		RetrieveServiceTypeMappingResponse response = (RetrieveServiceTypeMappingResponse)executeService(request);
		if(response != null) {
			setValuesToBackingBean(response.getRuleMappingBO());
			setRuleIdToSession(this.ruleId);
			return WebConstants.SERVICE_TYPE_MAPPING_EDIT_PAGE;
		}
		
		return WebConstants.EMPTY_STRING;
	}
	
	public List tiladaSeparation(String tiladaString){
		
		String [] selectedIds = tiladaString.split("~");
		List codeIdList = new ArrayList();
		if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			codeIdList.add(selectedIds[i]);
    		}
		}
		return codeIdList;
	}
	
	/**
	 * Action method for deleting a service type code.
	 * @return pageNavigation String
	 */
	public String deleteAction(){
		DeleteServiceTypeMappingRequest request = (DeleteServiceTypeMappingRequest)getServiceRequest(ServiceManager.DELETE_SERVICE_TYPE_MAPPING_REQUEST);
		String [] selectedIds = deleteCodeIds.split("~");
		List codeIdList = new ArrayList();
		if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			codeIdList.add(selectedIds[i]);
    		}
		}
		request.setCodeList(codeIdList);
		request.setRuleId(this.headerRuleId);
		RetrieveServiceTypeMappingResponse response =(RetrieveServiceTypeMappingResponse)executeService(request); 
//		if(response != null ) {
//			setValuesToBackingBean(response.getRuleMappingBO());
//		}
		return "";
	}
	/**
	 * return  backing bean
	 * @param String
	 * @return Object
	 */
	private Object getBackingBean(String name) {
		Object backingBean = FacesContext.getCurrentInstance().getApplication()
				.createValueBinding("#{" + name +"}").getValue(
						FacesContext.getCurrentInstance());
		return backingBean;
	}
	/**
	 * this method for retrieving service type information 
	 * 
	 * get the associated ebo3mapped codes and make alist
	 */
	
	public String getLoadServiceTypes() {
    	RetrieveServiceTypeMappingRequest request = (RetrieveServiceTypeMappingRequest)getServiceRequest(ServiceManager.RETRIEVE_SERVICE_TYPE_MAPPING_REQUEST);;
		request.setRuleId(this.headerRuleId);
		RetrieveServiceTypeMappingResponse response = (RetrieveServiceTypeMappingResponse)executeService(request);
		if(response != null) {
			RuleMapping mapping = response.getRuleMappingBO();
			if(mapping != null) {
				associatedCodeList = mapping.getServiceTypeCodes();
			}
		}		
		return "";
	}
	/*
	 * this method for setting assaociated eb03 code values.
	 */
	public void setLoadServiceTypes(String temp) {
		
	}
	/**
	 * Process code and description in a hyphen sepearate format to show it in the page.
	 * @param code
	 * @param desc
	 * @return
	 */
	private String getHyphenString(String code, String desc) {
		String hyphenString = "";
		if(code == null)
			return hyphenString;
		hyphenString = code;
		
		if(desc != null) {
			hyphenString = hyphenString + " - " + desc;
		}
		return hyphenString;
	}
	
	/**
	 * Methods set the values into backing bean from the mapping object.
	 * @param mapping
	 */
	private void setValuesToBackingBean(RuleMapping mapping) {

		this.headerRuleId = mapping.getRuleId();
		this.ruleDescription = mapping.getRuleShortDesc();
		if(null!=headerRuleId)
			this.ruleForEdit = this.headerRuleId;
		if(null!=ruleDescription)
			this.ruleForEdit = this.ruleForEdit + " - "+this.ruleDescription;
		this.blueExcahngeApplicable = mapping.getApplicableToBX();
		this.blueExcahngeApplicablePrev = mapping.getApplicableToBX();
		this.associatedCodeList = mapping.getServiceTypeCodes();
		this.setCreatedUser(mapping.getCreatedUser());
		this.setCreatedTimestamp(mapping.getCreatedTimestamp());
		this.setLastUpdatedUser(mapping.getLastUpdatedUser());
		this.setLastUpdatedTimestamp(mapping.getLastUpdatedTimestamp());
		this.eb03Identifier=null;
		setRuleIdToSession(this.headerRuleId);
	}
	
	
	/**
	 * Sets the mappings sys id to session.
	 * @param sysId
	 */
	private void setSysIdToSession(ServiceTypeMapping mapping) {
		if(mapping.getMappingSysId() != 0 )
			getSession().setAttribute(WebConstants.SRV_TYP_MAPPING_SESSION_KEY, new Integer(mapping.getMappingSysId()));
		getSession().setAttribute("EOBCode", mapping.getEb03Identifier() + "-" + mapping.getEb03IdentifierDes());
		
	}

	/**
	 * Process code and description in a tilda sepearate format to show it in the page.
	 * @param code
	 * @param desc
	 * @return
	 */
	private String getTildaString(String code, String desc) {
		if(code == null || desc == null) {
			return "";			
		}
		else {
			return desc + "~" + code;
		}
	}
	
	/**
	 * Validates all the screen fields.
	 * @return
	 */
	private boolean isFieldsValid() {
		boolean valid = true;
		
		if(!isMandatoryFieldsProvided()) {
			valid = false;
		}
		
		return valid;
	}
	

	

	/**
	 * Validates whether mandatory fields are provided.
	 * @return boolean
	 */
	private boolean isMandatoryFieldsProvided() {
		boolean valid = true;
		if ((null == this.headerRuleId) || (WebConstants.EMPTY_STRING.equals(this.headerRuleId.trim()))) {
			valid = false;
			headerRuleIdValdn = true;
        }
		if("Y".equals(this.blueExcahngeApplicable)){
		if(isEmpty(eb03Identifier)) {
			valid = false;
			eb03IdentifierValdn = true;
		}
		}
		if(!valid)
			messages.add(new ErrorMessage(WebConstants.MANDATORY_FIELD_REQUIRED));
		return valid;
	}
	
	
	/**
	 * Validates whether the string ia Empty.
	 * @param String
	 * @return boolean
	 */
	private boolean isEmpty(String value) {
		if(value == null || value.trim().equals(""))
			return true;
		return false;
	}
	
	/**
	 * Setting backing bean values to request.
	 * @param request
	 */
	private void setValuesToRequest(SaveServiceTypeMappingRequest request) {
		ServiceTypeMappingVO serviceTypeMappingVO = new ServiceTypeMappingVO();
		if("Y".equals( this.blueExcahngeApplicable)){
		List eb03codeList = WPDStringUtil.getListFromTildaString(this.eb03Identifier,2,2,2);
		serviceTypeMappingVO.setEb03codeList(eb03codeList);
		}
		serviceTypeMappingVO.setHeaderRuleId((this.headerRuleId.trim()).toUpperCase());
		serviceTypeMappingVO.setBlueExcahngeApplicable(this.blueExcahngeApplicable);
		serviceTypeMappingVO.setSendDynamicInfo(this.sendDynamicInfo);
		request.setServiceTypeMappingVO(serviceTypeMappingVO);
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
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
	 * @return Returns the eb03IdentifierValid.
	 */
	public boolean isEb03IdentifierValid() {
		return eb03IdentifierValid;
	}
	/**
	 * @param eb03IdentifierValid The eb03IdentifierValid to set.
	 */
	public void setEb03IdentifierValid(boolean eb03IdentifierValid) {
		this.eb03IdentifierValid = eb03IdentifierValid;
	}
	/**
	 * 
	 * @return Returns the initView.
	 */
	public String getInitView() {
		String mappingId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("ruleID"));
		if(null!=mappingId  && mappingId.matches("[0-9a-zA-Z_]+")){
			mappingId = mappingId;
		}
			if (mappingId != null  && !WebConstants.EMPTY_STRING.equalsIgnoreCase(mappingId)) {
					setRuleIdToSession(mappingId);
					this.getBasicInfo(mappingId);
			}
			return WebConstants.EMPTY_STRING;
			
		
		
	}
	/**
	 * Sets the rule id to session.
	 * @param sysId
	 */
	private void setRuleIdToSession(String sysId) {
		if(sysId == null || sysId.equalsIgnoreCase(WebConstants.EMPTY_STRING) )
			return;
		getSession().setAttribute(WebConstants.RULE_ID,new String(sysId));
	}
	/**
     * Methode for retreiving the Service Type Mapping for Edit
     * @param int 
     * @return String
     */
	public String getBasicInfo(String id){
    	RetrieveServiceTypeMappingRequest request = (RetrieveServiceTypeMappingRequest)getServiceRequest(ServiceManager.RETRIEVE_SERVICE_TYPE_MAPPING_REQUEST);;
		request.setRuleId(id);
		RetrieveServiceTypeMappingResponse response = (RetrieveServiceTypeMappingResponse)executeService(request);
		if(response != null) {
			setValuesToBackingBeanForView(response.getRuleMappingBO());
			return WebConstants.EMPTY_STRING;
		}
		return WebConstants.EMPTY_STRING;
    }
	/**
	 * sets values to  backing bean
	 * @param mapping
	 * @return void
	 */
	private void setValuesToBackingBeanForView(RuleMapping ruleMappingBO) {
		this.eb03IdentifierList = ruleMappingBO.getServiceTypeCodes();
		if(null!=eb03IdentifierList){
			for(int i=0;i<eb03IdentifierList.size();i++){
				if("Y".equals(((RuleServiceTypeAssociation)eb03IdentifierList.get(i)).getSendDynamicInfo())){
					((RuleServiceTypeAssociation)eb03IdentifierList.get(i)).setSendDynamicInfo("Yes");
				}else{
					((RuleServiceTypeAssociation)eb03IdentifierList.get(i)).setSendDynamicInfo("No");
				}
			}
			
		}
		this.ruleId = getHyphenString(ruleMappingBO.getRuleId(), ruleMappingBO.getRuleShortDesc()); 
		this.validForBX = ruleMappingBO.getApplicableToBX();
		if(null!=this.validForBX && !"".equals(this.validForBX)){
			if("Y".equals(this.validForBX)){
				this.validForBX="Yes";
			}else{
				this.validForBX="No";
			}
		}
		this.createdUser = ruleMappingBO.getCreatedUser();
		this.createdTimestamp = ruleMappingBO.getCreatedTimestamp();
		this.lastUpdatedUser = ruleMappingBO.getLastUpdatedUser();
		this.lastUpdatedTimestamp =	ruleMappingBO.getLastUpdatedTimestamp();		
		setRuleIdToSession(ruleMappingBO.getRuleId());		
	}
	/**
	 * Sets the mappings sys id to session.
	 * @param sysId
	 */
	/*private void setRuleIdToSession(RuleMapping ruleMappingBO) {
		if(ruleMappingBO.getRuleId() != null && !WebConstants.EMPTY_STRING.equalsIgnoreCase(ruleMappingBO.getRuleId()))
			getSession().setAttribute(WebConstants.SRV_TYP_MAPPING_SESSION_KEY, new String(ruleMappingBO.getRuleId()));
		getSession().setAttribute("ruleId", ruleMappingBO.getRuleId());
		
	}*/
	/**
	 * @param initView The initView to set.
	 */
	public void setInitView(String initView) {
		this.initView = initView;
	}
	/**
	 * @return Returns the initPrint.
	 */
	public String getInitPrint() {
		String sysId = getRuleSysIdFromSession();
		this.getBasicInfo(sysId);
		return initPrint;
	}
	/**
	 * Returns the key value taken from session.
	 * @return int
	 */
	private String getRuleSysIdFromSession() {
		String ruleId = (String)getSession().getAttribute(WebConstants.RULE_ID);
		if(ruleId == null || WebConstants.EMPTY_STRING.equalsIgnoreCase(ruleId))
			return "";
		else
			return ruleId;
	}
	/**
	 * @param initPrint The initPrint to set.
	 */
	public void setInitPrint(String initPrint) {
		this.initPrint = initPrint;
	}
	
	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getBreadCrumpText() {
		this.breadCrumpText = "Administration>> Blue Exchange >> Service Type Mapping ("+getSession().getAttribute("ruleID")+ ") >> Print"; 
		return breadCrumpText;
	}
	/**
	 * @param breadCrumpText The breadCrumpText to set.
	 */
	public void setBreadCrumpText(String breadCrumpText) {
		this.breadCrumpText = breadCrumpText;
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
	 * @return Returns the headerRuleIdValdn.
	 */
	public boolean isHeaderRuleIdValdn() {
		return headerRuleIdValdn;
	}
	/**
	 * @param headerRuleIdValdn The headerRuleIdValdn to set.
	 */
	public void setHeaderRuleIdValdn(boolean headerRuleIdValdn) {
		this.headerRuleIdValdn = headerRuleIdValdn;
	}
	/**
	 * @return Returns the blueExcahngeApplicable.
	 */
	public String getBlueExcahngeApplicable() {
		return blueExcahngeApplicable;
	}
	/**
	 * @param blueExcahngeApplicable The blueExcahngeApplicable to set.
	 */
	public void setBlueExcahngeApplicable(String blueExcahngeApplicable) {
		this.blueExcahngeApplicable = blueExcahngeApplicable;
	}
	/**
	 * @return Returns the ruleDescription.
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}
	/**
	 * @param ruleDescription The ruleDescription to set.
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	/**
	 * @return Returns the ruleForEdit.
	 */
	public String getRuleForEdit() {
		return ruleForEdit;
	}
	/**
	 * @param ruleForEdit The ruleForEdit to set.
	 */
	public void setRuleForEdit(String ruleForEdit) {
		this.ruleForEdit = ruleForEdit;
	}
	/**
	 * @return Returns the associatedCodeList.
	 */
	public List getAssociatedCodeList() {
		return associatedCodeList;
	}
	/**
	 * @param associatedCodeList The associatedCodeList to set.
	 */
	public void setAssociatedCodeList(List associatedCodeList) {
		this.associatedCodeList = associatedCodeList;
	}
	/**
	 * @return Returns the deleteCodeIds.
	 */
	public String getDeleteCodeIds() {
		return deleteCodeIds;
	}
	/**
	 * @param deleteCodeIds The deleteCodeIds to set.
	 */
	public void setDeleteCodeIds(String deleteCodeIds) {
		this.deleteCodeIds = deleteCodeIds;
	}
	/**
	 * @return Returns the blueExcahngeApplicablePrev.
	 */
	public String getBlueExcahngeApplicablePrev() {
		return blueExcahngeApplicablePrev;
	}
	/**
	 * @param blueExcahngeApplicablePrev The blueExcahngeApplicablePrev to set.
	 */
	public void setBlueExcahngeApplicablePrev(String blueExcahngeApplicablePrev) {
		this.blueExcahngeApplicablePrev = blueExcahngeApplicablePrev;
	}
	/**
	 * @return Returns the eb03IdentifierValdn.
	 */
	public boolean isEb03IdentifierValdn() {
		return eb03IdentifierValdn;
	}
	/**
	 * @param eb03IdentifierValdn The eb03IdentifierValdn to set.
	 */
	public void setEb03IdentifierValdn(boolean eb03IdentifierValdn) {
		this.eb03IdentifierValdn = eb03IdentifierValdn;
	}
	/**
	 * @return Returns the eb03IdentifierForEdit.
	 */
	public String getEb03IdentifierForEdit() {
		return eb03IdentifierForEdit;
	}
	/**
	 * @param eb03IdentifierForEdit The eb03IdentifierForEdit to set.
	 */
	public void setEb03IdentifierForEdit(String eb03IdentifierForEdit) {
		this.eb03IdentifierForEdit = eb03IdentifierForEdit;
	}
	/**
	 * @return Returns the eb03IdentifierList.
	 */
	public List getEb03IdentifierList() {
		return eb03IdentifierList;
	}
	/**
	 * @param eb03IdentifierList The eb03IdentifierList to set.
	 */
	public void setEb03IdentifierList(List eb03IdentifierList) {
		this.eb03IdentifierList = eb03IdentifierList;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the validForBX.
	 */
	public String getValidForBX() {
		return validForBX;
	}
	/**
	 * @param validForBX The validForBX to set.
	 */
	public void setValidForBX(String validForBX) {
		this.validForBX = validForBX;
	}
	/**
	 * @return Returns the codeToSave.
	 */
	public String getCodeToSave() {
		return codeToSave;
	}
	/**
	 * @param codeToSave The codeToSave to set.
	 */
	public void setCodeToSave(String codeToSave) {
		this.codeToSave = codeToSave;
	}
	/**
	 * @return Returns the sendDynamicInfoCodes.
	 */
	public String getSendDynamicInfoCodes() {
		return sendDynamicInfoCodes;
	}
	/**
	 * @param sendDynamicInfoCodes The sendDynamicInfoCodes to set.
	 */
	public void setSendDynamicInfoCodes(String sendDynamicInfoCodes) {
		this.sendDynamicInfoCodes = sendDynamicInfoCodes;
	}
	/**
	 * @return Returns the sendDynamicInfoValues.
	 */
	public String getSendDynamicInfoValues() {
		return sendDynamicInfoValues;
	}
	/**
	 * @param sendDynamicInfoValues The sendDynamicInfoValues to set.
	 */
	public void setSendDynamicInfoValues(String sendDynamicInfoValues) {
		this.sendDynamicInfoValues = sendDynamicInfoValues;
	}
	/**
	 * @return Returns the sendDynamicInfo.
	 */
	public String getSendDynamicInfo() {
		return sendDynamicInfo;
	}
	/**
	 * @param sendDynamicInfo The sendDynamicInfo to set.
	 */
	public void setSendDynamicInfo(String sendDynamicInfo) {
		this.sendDynamicInfo = sendDynamicInfo;
	}
}
