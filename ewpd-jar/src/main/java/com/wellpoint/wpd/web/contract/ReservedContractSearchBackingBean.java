/*
 * ReservedContractSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;
import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.contract.request.ReleaseReservedContractRequest;
import com.wellpoint.wpd.common.contract.request.ReservedContractSearchRequest;
import com.wellpoint.wpd.common.contract.response.ReleaseReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.ReservedContractSearchResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ReservedContractSearchBackingBean extends WPDBackingBean {
    
    private List validationMessages;
    private String lob;
	private String businessEntity;
	private String businessGroup;
	private String marketBusinessUnit;
	private String contractId;
	private List locateResultList;
	private String contractID;
	
    /**
     * 
     * @return
     */
    public String searchContract(){
         //checks if the fields are empty or not
         if (!isValidateRequiredFields()) {
             addAllMessagesToRequest(validationMessages);
             return "";
         }
         ReservedContractSearchResponse reservedContractSearchResponse = (ReservedContractSearchResponse) this.executeService(getContractSearchRequest());
	 	if(null != reservedContractSearchResponse.getSearchResultList() && reservedContractSearchResponse.getSearchResultList().size()>0){
	 	   locateResultList = new ArrayList();
 		   setLocateResultList(reservedContractSearchResponse.getSearchResultList());
	 	}
    	return "reservedContractView";	
    }
    /**
     * Function for performing the validations.
     * 
     * @return boolean
     */
    private boolean isValidateRequiredFields() {
        validationMessages = new ArrayList();
        boolean valid = false;
        if ((null == this.contractId || "".equals(this.contractId.trim()))
                && (null == this.lob || "".equals(this.lob.trim()))
                && (null == this.businessEntity || "".equals(this.businessEntity))
                && (null == this.businessGroup || "".equals(this.businessGroup.trim()))
				&& (null == this.marketBusinessUnit || "".equals(this.marketBusinessUnit.trim()))
			)
            valid = true;
        if (valid) {
        	validationMessages.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));
            locateResultList = null;
            return false;
        }
        return true;
    }
    private ReservedContractSearchRequest getContractSearchRequest(){
        ReservedContractSearchRequest reservedContractSearchRequest = (ReservedContractSearchRequest) this
			.getServiceRequest(ServiceManager.SEARCH_RESERVED_CONTRACT_REQUEST);
        
        
    	
        reservedContractSearchRequest.setContractId(this.getContractId().trim());
    	
    	List lobList = WPDStringUtil.getListFromTildaString(
                this.lob, 2, 2, 2);
        List entityList = WPDStringUtil.getListFromTildaString(
                this.businessEntity, 2, 2, 2);
        List groupList = WPDStringUtil.getListFromTildaString(
                this.businessGroup, 2, 2, 2);
        List unitList = WPDStringUtil.getListFromTildaString(
        		this.marketBusinessUnit, 2, 2, 2);
      
        reservedContractSearchRequest.setLob(lobList);
        reservedContractSearchRequest.setBusinessEntity(entityList);
        reservedContractSearchRequest.setBusinessGroup(groupList);
        reservedContractSearchRequest.setMarketBusinessUnit(unitList);
       	
    	return reservedContractSearchRequest;
    }
    
    /**
     * Method for releasing a reserved contract
     * @return String
     */
    public String releaseAction(){
    	List idList = new ArrayList();
    	String returnMessage="";
    	ContractIDReservePoolRecord poolRecord = new ContractIDReservePoolRecord();
    	poolRecord.setContractId(this.contractID);
    	poolRecord.setReservePoolStatus("N");
    	idList.add(poolRecord);
    	ReleaseReservedContractRequest releaseReservedContractRequest;
        releaseReservedContractRequest = (ReleaseReservedContractRequest) this.getServiceRequest(ServiceManager.RELEASE__RESERVED_CONTRACT);
    	releaseReservedContractRequest.setAction(3);
        releaseReservedContractRequest.setContractIdList(idList);
        ReleaseReservedContractResponse releaseResponse = (ReleaseReservedContractResponse) executeService(
    			releaseReservedContractRequest);
        
        if(null != releaseResponse ){
        	InformationalMessage message =null;
           	if(releaseResponse.isSuccess()){
           		this.validationMessages=new ArrayList();
           		returnMessage=this.searchContract();        		
     
           		if(1==idList.size()){
           		 	message = new InformationalMessage("selected.contractid.release.single.success.info");
           			String[] argMsg = new String[1];
	                argMsg[0] = this.contractID;	               
	                message.setParameters(new String[] {argMsg[0]});
           		}else {           		
           			message = new InformationalMessage("selected.contractid.release.success.info"); 			
           		}
           		this.validationMessages.add(message);
           		addAllMessagesToRequest(validationMessages);
           	}
        }
    	
    	return returnMessage;
    }
    
    private void clearbackingbean(){
    	this.lob="";
    	this.businessEntity="";
    	this.businessGroup="";
    	this.marketBusinessUnit="";
    	this.contractId ="";
    	
    }
    /**
     * Returns the businessEntity
     * @return String businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return String businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the contractId
     * @return String contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * Returns the lob
     * @return String lob.
     */
    public String getLob() {
        return lob;
    }
    /**
     * Sets the lob
     * @param lob.
     */
    public void setLob(String lob) {
        this.lob = lob;
    }
    /**
     * Returns the locateResultList
     * @return List locateResultList.
     */
    public List getLocateResultList() {
        return locateResultList;
    }
    /**
     * Sets the locateResultList
     * @param locateResultList.
     */
    public void setLocateResultList(List locateResultList) {
        this.locateResultList = locateResultList;
    }
    /**
     * Returns the validationMessages
     * @return List validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }
    /**
     * Sets the validationMessages
     * @param validationMessages.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }
	
	/**
	 * @return Returns the contractID.
	 */
	public String getContractID() {
		return contractID;
	}
	/**
	 * @param contractID The contractID to set.
	 */
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
