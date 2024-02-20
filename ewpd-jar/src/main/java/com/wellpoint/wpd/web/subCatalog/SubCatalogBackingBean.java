/*
 * SubCatalogBackingBean.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 * Created on July 16, 2007
 */
package com.wellpoint.wpd.web.subCatalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.common.subcatalog.request.ItemAssociationDeleteRequest;
import com.wellpoint.wpd.common.subcatalog.request.ItemLookUpRequest;
import com.wellpoint.wpd.common.subcatalog.request.RetrieveSubCatalogRequest;
import com.wellpoint.wpd.common.subcatalog.request.SaveItemAssociationRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogSaveRequest;
import com.wellpoint.wpd.common.subcatalog.response.ItemAssociationDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.ItemLookUpResponse;
import com.wellpoint.wpd.common.subcatalog.response.RetrieveSubCatalogResponse;
import com.wellpoint.wpd.common.subcatalog.response.SaveItemAssociationResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSaveResponse;
import com.wellpoint.wpd.common.subcatalog.vo.ItemLocateCriteriaVO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * Base class for subCatalog functionalities
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class SubCatalogBackingBean extends WPDBackingBean{

    private String lob;
    
    private String businessEntity;
    
    private String businessGroup;
    
    private String subCatalogName;
    
    private int subCatalogId;
    
    private String parentCatalog;
    
    private String parentCatalogId;
    
    private int subCatalogParentId;
    
    private String description;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private boolean requiredLob;
    
    private boolean requiredBusinessEntity;
    
    private boolean requiredBusinessGroup;
    
    private boolean requiredParentCatalogId;
    
    private boolean requiredSubCatalogName;
    
    private List lobCodeList;

    private List businessEntityCodeList;

  	private List businessGroupCodeList;

  	private String primaryCode;

  	private List itemList;
  	    
  	private ItemLocateCriteriaVO itemLocateCriteriaVO;
  	
  	private int viewSubCatalogId;
	
    private ArrayList validationMessages = null;
    
    private List searchResultList;
    
    private List printSearchResultList;
    
    private String printBreadCrumbText;
    
    private String loadItemPopUpList;
    //CARS START
    private boolean requiredMarketBusinessUnit;
    
    private String marketBusinessUnit;

    private List marketBusinessUnitCodeList;    
    //CARS END
    
    public SubCatalogBackingBean(){
        super();
        this.lob = WebConstants.ALL_99;
        this.businessEntity = WebConstants.ALL_99;
        this.businessGroup = WebConstants.ALL_99;
        this.marketBusinessUnit = WebConstants.ALL_99;
        this.setBreadCrumbText("Administration >> Sub-Catalog >> Create");
    }
   /**
    * method for the creation of sub-catalog
    * @return String
    */ 
    public String create(){
        validationMessages = new ArrayList();
        HttpSession httpSession = getSession();
        if (!validateRequiredFileds(1)) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        //Method to obtain the business domain values from the tilda string 
        getBusinessDomain();
        SubCatalogSaveRequest subCatalogSaveRequest=getSubCatalogSaveRequest(1);
        SubCatalogSaveResponse subCatalogSaveResponse=(SubCatalogSaveResponse) executeService(subCatalogSaveRequest);
        if (null != subCatalogSaveResponse) {
            if (null != subCatalogSaveResponse.getCatalogBO()) {
                if (subCatalogSaveResponse.isSuccess()) {
                	CatalogBO catalogBO = subCatalogSaveResponse.getCatalogBO();
                	setValuesFromResponseToBackingBean(subCatalogSaveResponse.getDomainDetail(), catalogBO);
                	
                }else{
                    return WebConstants.EMPTY_STRING;
                }
            }
        }  
        //Setting the session attribute for edit page print
        httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(this.getSubCatalogId()));
        this.setBreadCrumbText("Administration >> Sub-Catalog ("+ this.subCatalogName +") >> Edit");
        return WebConstants.UPDATE_STRUCTURE;
    }
    /**
     * method for updation
     * @return String
     */
     public String update(){
        validationMessages = new ArrayList();

        if (!validateRequiredFileds(2)) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        getBusinessDomain();
        SubCatalogSaveRequest subCatalogSaveRequest=getSubCatalogSaveRequest(2);
        SubCatalogSaveResponse subCatalogSaveResponse=(SubCatalogSaveResponse) executeService(subCatalogSaveRequest);
        if (null != subCatalogSaveResponse) {
            if (null != subCatalogSaveResponse.getCatalogBO()) {
                if (subCatalogSaveResponse.isSuccess()) {
                	CatalogBO catalogBO = subCatalogSaveResponse.getCatalogBO();
                	setValuesFromResponseToBackingBean(subCatalogSaveResponse.getDomainDetail(), catalogBO);
                	
                }
            }
        }
        HttpSession httpSession = getSession();
        httpSession.setAttribute(WebConstants.ACTION_VIEW_STRING, null);
        //Setting the session attribute for edit page print
        httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(this.getSubCatalogId()));
        this.setBreadCrumbText("Administration >> Sub-Catalog ("+ this.subCatalogName +") >> Edit");
        return WebConstants.UPDATE_STRUCTURE;
    }
        
    /**
     * @param subCatalogSaveResponse
     * @param catalogBO
     */
    private void setValuesFromResponseToBackingBean(DomainDetail domainDetail, CatalogBO catalogBO) {
       	//for setting values from response to backingbean 
        this.setSubCatalogName(catalogBO.getCatalogName());
        this.setSubCatalogId(catalogBO.getCatalogId());
        this.setParentCatalogId(catalogBO.getCatalogParentid()+"~"+catalogBO.getParentCatalog()+"~"+0);
        this.setParentCatalog(catalogBO.getParentCatalog());
        this.setDescription(catalogBO.getDescription());
        this.setSubCatalogParentId(catalogBO.getCatalogParentid());
        this.setCreatedTimestamp(catalogBO.getCreatedTimestamp());
        this.setCreatedUser(catalogBO.getCreatedUser());
        this.setLastUpdatedUser(catalogBO.getLastUpdatedUser());
        this.setLastUpdatedTimestamp(catalogBO.getLastUpdatedTimestamp());
        
        if (domainDetail != null) {
        	this.lob = WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness());
        	this.businessEntity = WPDStringUtil.getTildaString(domainDetail.getBusinessEntity());
        	this.businessGroup = WPDStringUtil.getTildaString(domainDetail.getBusinessGroup());
        	//CARS START
        	this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail.getMarketBusinessUnit());
        	//CARS END
        }
    }
    /**
     * Method to obtain the domain values from the tilda string
     *
     */
    private void getBusinessDomain(){
        this.lobCodeList = WPDStringUtil.getListFromTildaString(this.getLob(),
                2, 2, 2);
        this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessEntity(), 2, 2, 2);
        this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessGroup(), 2, 2, 2);
        //CARS START
        this.marketBusinessUnitCodeList = WPDStringUtil.getListFromTildaString(this
                .getMarketBusinessUnit(), 2, 2, 2);
        //CARS END
    }

    public boolean validateRequiredFileds(int action){
        boolean requiredField = false;
        
        requiredLob = false;
        requiredBusinessEntity = false;
        requiredBusinessGroup =  false;
        requiredParentCatalogId = false;
        requiredSubCatalogName = false;
        //CARS START
        requiredMarketBusinessUnit = false;
        //CARS END
        
        if(action == 1){
            this.setBreadCrumbText("Administration >> Sub-Catalog  >> Create");
        }else if (action == 2){
            this.setBreadCrumbText("Administration >> Sub-Catalog ("+ this.subCatalogName +") >> Edit");
        }
        
        if (this.lob == null || WebConstants.EMPTY_STRING.equals(this.lob)) {
            requiredLob = true;
            requiredField = true;
        }
        if (this.businessEntity == null || WebConstants.EMPTY_STRING.equals(this.businessEntity)) {
            requiredBusinessEntity = true;
            requiredField = true;
        }
        if (this.businessGroup == null || WebConstants.EMPTY_STRING.equals(this.businessGroup)) {
            requiredBusinessGroup = true;
            requiredField = true;
        }
        //CARS START
        if (this.marketBusinessUnit == null || WebConstants.EMPTY_STRING.equals(this.marketBusinessUnit)) {
            requiredMarketBusinessUnit = true;
            requiredField = true;
        }//CARS END
        if(this.parentCatalogId == null || WebConstants.EMPTY_STRING.equals(this.parentCatalogId)){
            requiredParentCatalogId = true;
            requiredField = true;
        }
        if(this.subCatalogName == null || WebConstants.EMPTY_STRING.equals(this.subCatalogName)){
            requiredSubCatalogName = true;
            requiredField = true;
        }        
        
        if((this.getLob().indexOf("ALL") >=0)&&(this.getBusinessEntity().indexOf("ALL") >=0)&&(this.getBusinessGroup().indexOf("ALL") >=0)&&(this.getMarketBusinessUnit().indexOf("ALL") >=0))
        {
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.BUSINESS_DOMAIN_VALUE_ALL));
            return false;
        }
        if (this.subCatalogName.trim().length() > 30) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.INVALID_NAME));
            return false;
        }
        
        if (!this.description.trim().equals(WebConstants.EMPTY_STRING) && this.description.length() > 240) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_1_240));
            return false;
        }
        
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;
        
    }
    
    private SubCatalogSaveRequest getSubCatalogSaveRequest(int action) {
    	SubCatalogSaveRequest subCatalogSaveRequest=(SubCatalogSaveRequest) this
        .getServiceRequest(ServiceManager.SAVE_SUB_CATALOG_REQUEST);
    	CatalogVO  catalogVO=new CatalogVO();
    
    	catalogVO.setCatalogName(this.getSubCatalogName().trim());
    	catalogVO.setLobList(this.getLobCodeList());
    	catalogVO.setBusinessEntityList(this.getBusinessEntityCodeList());
    	catalogVO.setBusinessGroupList(this.getBusinessGroupCodeList());
    	//START CARS
    	catalogVO.setMarketBusinessUnitList(this.getMarketBusinessUnitCodeList());
    	//END CARS
        if(this.description.trim().equals(WebConstants.EMPTY_STRING)){
        	catalogVO.setDescription(" ");
        }else{
        	catalogVO.setDescription(this.getDescription().trim());
        }
       
	    	List parentCatalogIdList = WPDStringUtil.getListFromTildaString(this
	                .getParentCatalogId(), 3, 1, 2);

	        if (parentCatalogIdList != null && parentCatalogIdList.size() > 0) {
	            String id = ((String) (parentCatalogIdList.get(0)));
	            catalogVO.setCatalogParentid(new Integer(id).intValue());
	        }
	        if(action == 2){
	        	//if update,then set subCatalogId to catalogVO
	        	catalogVO.setCatalogId(this.getSubCatalogId());
	        }

        subCatalogSaveRequest.setCatalogVO(catalogVO);
        
        // Set the action to the request
        subCatalogSaveRequest.setAction(action);
    	return subCatalogSaveRequest;
    }
    
    /**
     * Method to load the subcatalog edit page
     * @return forward string
     */
    public String loadEditPage(){
        //to load the edit page on clicking edit button from search results table
        int subCatalogId = this.getSubCatalogId();
        HttpSession httpSession = getSession();
        httpSession.setAttribute(WebConstants.ACTION_VIEW_STRING, null);
        retrieveSubCatalog(subCatalogId, 1);
        //Setting the session attribute for edit page print
        httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(subCatalogId));
        httpSession.setAttribute(WebConstants.SUB_CATALOG_NAME_STRING, this.subCatalogName);
        return WebConstants.UPDATE_STRUCTURE;
    }
    
    /**
     * @param subCatalogId2
     */
    private void retrieveSubCatalog(int subCatalogId,int action) {
        // Create the request object
        RetrieveSubCatalogRequest request = (RetrieveSubCatalogRequest) 
            this.getServiceRequest(ServiceManager.RETRIEVE_SUB_CATALOG);
        
        // Create the catalogVO
        CatalogVO catalogVO = new CatalogVO();
        
        // Set the subCatalog id to the VO
        catalogVO.setCatalogId(subCatalogId);
        
        // Set the VO to the request
        request.setCatalogVO(catalogVO);
        request.setAction(action);
        
        // Call the executeService to get the response
        RetrieveSubCatalogResponse response = (RetrieveSubCatalogResponse)
        	this.executeService(request);
        
        // Set the values from the response BO to the backing bean
        if(null != response.getCatalogBO()){
            // Get the BO from the response
            CatalogBO catalogBO = response.getCatalogBO();
            
            // Set the values to the backing bean from the BO
            setValuesFromResponseToBackingBean(response.getDomainDetail(), catalogBO);
        }
        
        // Set the breadcrumb
        this.setBreadCrumbText("Administration >> Sub Catalog " 
                + "("+ this.subCatalogName + ") >> Edit");
        
    }

    /**
     * @return Returns the searchResultList.
     * @return List searchResultList
     */
    public List getSearchResultList() {
        return searchResultList;
    }
    /**
     * Sets the searchResultList
     * @param searchResultList
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }
    /**
     * @return subCatalogParentId
     * Returns the subCatalogParentId.
     */
    public int getSubCatalogParentId() {
        return subCatalogParentId;
    }
    /**
     * @param subCatalogParentId
     * Sets the subCatalogParentId.
     */
    public void setSubCatalogParentId(int subCatalogParentId) {
        this.subCatalogParentId = subCatalogParentId;
    }
    /**
     * @return subCatalogId
     * Returns the subCatalogId.
     */
    public int getSubCatalogId() {
        return subCatalogId;
    }
    /**
     * @param subCatalogId
     * Sets the subCatalogId.
     */
    public void setSubCatalogId(int subCatalogId) {
        this.subCatalogId = subCatalogId;
    }
	/**
	 * @return Returns the parentCatalogId.
	 */
	public String getParentCatalogId() {
		return parentCatalogId;
	}
	/**
	 * @param parentCatalogId The parentCatalogId to set.
	 */
	public void setParentCatalogId(String parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}
    /**
	 * @return Returns the businessEntityCodeList.
	 */
	public List getBusinessEntityCodeList() {
		return businessEntityCodeList;
	}
	/**
	 * @param businessEntityCodeList The businessEntityCodeList to set.
	 */
	public void setBusinessEntityCodeList(List businessEntityCodeList) {
		this.businessEntityCodeList = businessEntityCodeList;
	}
	/**
	 * @return Returns the businessGroupCodeList.
	 */
	public List getBusinessGroupCodeList() {
		return businessGroupCodeList;
	}
	/**
	 * @param businessGroupCodeList The businessGroupCodeList to set.
	 */
	public void setBusinessGroupCodeList(List businessGroupCodeList) {
		this.businessGroupCodeList = businessGroupCodeList;
	}
	/**
	 * @return Returns the lobCodeList.
	 */
	public List getLobCodeList() {
		return lobCodeList;
	}
	/**
	 * @param lobCodeList The lobCodeList to set.
	 */
	public void setLobCodeList(List lobCodeList) {
		this.lobCodeList = lobCodeList;
	}
    /**
     * @return Returns the businessEntity.
     * @return String businessEntity
     */
    public String getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * @return Returns the businessGroup.
     * @return String businessGroup
     */
    public String getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * @return Returns the createdTimestamp.
     * @return String createdTimestamp
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return Returns the createdUser.
     * @return String createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return Returns the description.
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description.trim().toUpperCase();
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     * @return String lastUpdatedTimestamp
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return Returns the lastUpdatedUser.
     * @return String lastUpdatedUser
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return Returns the lob.
     * @return String lob
     */
    public String getLob() {
        return lob;
    }
    /**
     * Sets the lob
     * @param lob
     */
    public void setLob(String lob) {
        this.lob = lob;
    }
    /**
     * @return Returns the parentCatalogName.
     * @return String parentCatalogName
     */
    public String getParentCatalog() {
        return parentCatalog;
    }
    /**
     * Sets the parentCatalogName
     * @param parentCatalogName
     */
    public void setParentCatalog(String parentCatalog) {
    	if(null != parentCatalog){
    		this.parentCatalog = parentCatalog.trim().toUpperCase();
    	}
    }
    /**
     * @return Returns the subCatalogName.
     * @return String subCatalogName
     */
    public String getSubCatalogName() {
        return subCatalogName;
    }
    /**
     * Sets the subCatalogName
     * @param subCatalogName
     */
    public void setSubCatalogName(String subCatalogName) {
        this.subCatalogName = subCatalogName.trim().toUpperCase();
    }
    
    /**
     * @return Returns the requiredBusinessEntity.
     * @return boolean requiredBusinessEntity
     */
    public boolean isRequiredBusinessEntity() {
        return requiredBusinessEntity;
    }
    /**
     * Sets the requiredBusinessEntity
     * @param requiredBusinessEntity
     */
    public void setRequiredBusinessEntity(boolean requiredBusinessEntity) {
        this.requiredBusinessEntity = requiredBusinessEntity;
    }
    /**
     * @return Returns the requiredBusinessGroup.
     * @return boolean requiredBusinessGroup
     */
    public boolean isRequiredBusinessGroup() {
        return requiredBusinessGroup;
    }
    /**
     * Sets the requiredBusinessGroup
     * @param requiredBusinessGroup
     */
    public void setRequiredBusinessGroup(boolean requiredBusinessGroup) {
        this.requiredBusinessGroup = requiredBusinessGroup;
    }
    /**
     * @return Returns the requiredLob.
     * @return boolean requiredLob
     */
    public boolean isRequiredLob() {
        return requiredLob;
    }
    /**
     * Sets the requiredLob
     * @param requiredLob
     */
    public void setRequiredLob(boolean requiredLob) {
        this.requiredLob = requiredLob;
    }
    /**
     * @return Returns the requiredParentCatalog.
     * @return boolean requiredParentCatalog
     */
    public boolean isRequiredParentCatalogId() {
        return requiredParentCatalogId;
    }
    /**
     * Sets the requiredParentCatalog
     * @param requiredParentCatalog
     */
    public void setRequiredParentCatalogId(boolean requiredParentCatalogId) {
        this.requiredParentCatalogId = requiredParentCatalogId;
    }
    /**
     * @return Returns the requiredSubCatalogName.
     * @return boolean requiredSubCatalogName
     */
    public boolean isRequiredSubCatalogName() {
        return requiredSubCatalogName;
    }
    /**
     * Sets the requiredSubCatalogName
     * @param requiredSubCatalogName
     */
    public void setRequiredSubCatalogName(boolean requiredSubCatalogName) {
        this.requiredSubCatalogName = requiredSubCatalogName;
    }
    /**
     * @return Returns the validationMessages.
     * @return ArrayList validationMessages
     */
    public ArrayList getValidationMessages() {
        return validationMessages;
    }
    /**
     * Sets the validationMessages
     * @param validationMessages
     */
    public void setValidationMessages(ArrayList validationMessages) {
        this.validationMessages = validationMessages;
    }
    /**
     * Method for loading the item associated with the subcatalog
     * which are not in the associated list
     * @return
     */
    public String loadItem(){
        RetrieveSubCatalogRequest retrieveSubCatalogRequest = (RetrieveSubCatalogRequest)getServiceRequest(ServiceManager.RETRIEVE_SUB_CATALOG);
        SubCatalogVO catalogVO = new SubCatalogVO();
        
        int subCatalogId = this.getSubCatalogId();
        int parentCatalogId = this.getSubCatalogParentId();
        String subCatalogName = this.getSubCatalogName();
        catalogVO.setSubCatalogId(new Integer(subCatalogId).toString());
        retrieveSubCatalogRequest.setSubCatalogVO(catalogVO);
        retrieveSubCatalogRequest.setAction(2);
        
        RetrieveSubCatalogResponse response = (RetrieveSubCatalogResponse) executeService (retrieveSubCatalogRequest);
        if(null != response.getItemList() && !response.getItemList().isEmpty()){
            this.searchResultList = response.getItemList();
        }else{
            this.searchResultList = null;
        }
        
        HttpSession httpSession = getSession();
        httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(subCatalogId));
        httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, new Integer(parentCatalogId));
        httpSession.setAttribute(WebConstants.SUB_CATALOG_NAME_STRING, subCatalogName);
        
        String action = (String) httpSession.getAttribute(WebConstants.ACTION_VIEW_STRING);
        if(null != action && !action.equals(WebConstants.EMPTY_STRING) && action.equals(WebConstants.VIEW_LOAD_STRING)){
            this.setBreadCrumbText("Administration >> Sub-Catalog ( "+ subCatalogName +" ) >> View");
            return WebConstants.LOAD_ITEM_FOR_VIEW;
        }
        
        this.setBreadCrumbText("Administration >> Sub-Catalog ( "+ subCatalogName +" ) >> Edit");
        return WebConstants.ASSOCIATED_ITEM;

    }
    
    /**
     * @return Returns the printSearchResultList.
     * @return List printSearchResultList
     */
    public List getPrintSearchResultList() {
        HttpSession httpSession = getSession();
        
        int subCtlgId = ((Integer)httpSession.getAttribute(WebConstants.SUB_CATALOG_ID_STRING)).intValue();
        String subcatalogName = (String)httpSession.getAttribute(WebConstants.SUB_CATALOG_NAME_STRING);
        this.setSubCatalogName(subcatalogName);
        RetrieveSubCatalogRequest retrieveSubCatalogRequest = (RetrieveSubCatalogRequest)getServiceRequest(ServiceManager.RETRIEVE_SUB_CATALOG);
        SubCatalogVO catalogVO = new SubCatalogVO();
        catalogVO.setSubCatalogId(new Integer(subCtlgId).toString());
        retrieveSubCatalogRequest.setSubCatalogVO(catalogVO);
        retrieveSubCatalogRequest.setAction(2);
        RetrieveSubCatalogResponse response = (RetrieveSubCatalogResponse) executeService (retrieveSubCatalogRequest);
        if(null != response.getItemList() && !response.getItemList().isEmpty()){
            this.printSearchResultList = response.getItemList();
        }else{
            this.printSearchResultList = null;
        }
        return printSearchResultList;
    }
    /**
     * Sets the printSearchResultList
     * @param printSearchResultList
     */
    public void setPrintSearchResultList(List printSearchResultList) {
        this.printSearchResultList = printSearchResultList;
    }

    public String loadGeneralInformationView(){
        HttpSession httpSession = getSession();
        
        httpSession.setAttribute(WebConstants.ACTION_STRING, WebConstants.VIEW_SESSION_STRING);
        
        return WebConstants.SUBCATALOG_GENINFO_VIEW;
    }
    
    /**
     * Method to save the items in association with subcatalog
     * @return
     */
    public String createItemAssociation(){
        HttpSession httpSession = getSession();
        String subcatalogName = (String)httpSession.getAttribute(WebConstants.SUB_CATALOG_NAME_STRING);
        this.setBreadCrumbText("Administration >> Sub-Catalog ( "+ subcatalogName +" ) >> Edit");
        
        SaveItemAssociationRequest saveItemAssociationRequest = getSaveItemRequest();
        SaveItemAssociationResponse saveItemAssociationResponse = (SaveItemAssociationResponse)executeService(saveItemAssociationRequest);
        // Get the associated item list from the response
        if((null != saveItemAssociationResponse) && (null != saveItemAssociationResponse.getItemList())){
	        List item = saveItemAssociationResponse.getItemList();
	        // Set it to the BB search list as in loadItem retrieve
	        if(item!=null && !item.isEmpty()){
	            this.searchResultList = item;
	
	        }else{
	            this.searchResultList = null;
	        }
        }else{
            this.searchResultList = null;
        }

        this.setPrimaryCode(null);

        getRequest().setAttribute("RETAIN_Value", "");
        
        return WebConstants.EMPTY_STRING;
    }
    /**
     * Creates the request for inserting the subcatalog
     * @return SubCatalogSaveRequest
     */
    private SaveItemAssociationRequest getSaveItemRequest(){
        SaveItemAssociationRequest saveItemAssociationRequest = (SaveItemAssociationRequest)
		this.getServiceRequest(ServiceManager.SAVE_ITEM_ASSOCIATION_REQUEST);
        
        CatalogVO catalogVO = copyValueObjectFromBackingBean();
        saveItemAssociationRequest.setCatalogVO(catalogVO);
        return saveItemAssociationRequest;
    }
    /**
     * Method to copy value object to the backingbean
     * @return SubCatalogVO
     */
    private CatalogVO copyValueObjectFromBackingBean(){
        CatalogVO catalogVO = new CatalogVO();
        List item = WPDStringUtil.getListFromTildaString(this.getPrimaryCode(),
                2, 2, 2);
        
      
        catalogVO.setCatalogId(this.getSubCatalogId());
        catalogVO.setCatalogParentid(this.getSubCatalogParentId());

        if(item!= null && !item.isEmpty())
            catalogVO.setAssociatedItems(item);
        else{
            catalogVO.setAssociatedItems(null);
        }
        return catalogVO;
    }
    public void createItemLocateCriteriaVO(){
        itemLocateCriteriaVO = new ItemLocateCriteriaVO();
        
        HttpSession httpSession = getSession();
        int subCatalogId = ((Integer)httpSession.getAttribute(WebConstants.SUB_CATALOG_ID_STRING)).intValue();
        String parentCatalogId = ((Integer)httpSession.getAttribute(WebConstants.CATALOG_ID_STRING)).toString();
        
        itemLocateCriteriaVO.setParentCatalogId(parentCatalogId);
        itemLocateCriteriaVO.setSubCatalogId(subCatalogId);
        
    }
    /**
     * @return itemList
     * 
     * Returns the itemList.
     */
    public List getItemList() {

        return itemList;
    }
    
    public String deleteItem(){
        HttpSession httpSession = getSession();
        String subcatalogName = (String)httpSession.getAttribute(WebConstants.SUB_CATALOG_NAME_STRING);
        this.setBreadCrumbText("Administration >> Sub-Catalog ( "+ subcatalogName +" ) >> Edit");
        ItemAssociationDeleteRequest itemAssociationDeleteRequest = (ItemAssociationDeleteRequest) 
        					getServiceRequest(ServiceManager.DELETE_ITEM_ASSOCIATION_REQUEST);
        List parentCatalogList = new ArrayList();
		//Get the selected Items parentCatalog Ids
		String [] selectedIds = parentCatalogId.split("~");
		if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			//Adding selected parentCatalogIds to a list
    			parentCatalogList.add(selectedIds[i]);
    		}
    	}
        SubCatalogVO subCatalogVO = new SubCatalogVO();
        
        subCatalogVO.setParentCatalogList(parentCatalogList);
       //subCatalogVO.setParentCatalogId(new Integer(this.getParentCatalogId()).intValue());
        subCatalogVO.setSubCatalogId(new Integer(this.getSubCatalogId()).toString());
        subCatalogVO.setPrimaryCode(this.getPrimaryCode());
        itemAssociationDeleteRequest.setSubCatalogVO(subCatalogVO);
        
        ItemAssociationDeleteResponse itemAssociationDeleteResponse = (ItemAssociationDeleteResponse)
        			executeService(itemAssociationDeleteRequest);
        
        List item = itemAssociationDeleteResponse.getItemList();
        // Set it to the BB search list as in loadItem retrieve
        if(item!=null && !item.isEmpty()){
            this.searchResultList = item;
            SubCatalogBO subCatalogBO = (SubCatalogBO) item.get(0);
            this.setSubCatalogParentId(subCatalogBO.getParentCatalogId());
        }else{
            this.searchResultList = null;
        }
//        this.primaryCode = null;
        if (null == this.primaryCode || "".equals(this.primaryCode)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
        return WebConstants.EMPTY_STRING;
    }
    /**
     * @param itemList
     * 
     * Sets the itemList.
     */
    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
    /**
     * @return itemLocateCriteriaVO
     * 
     * Returns the itemLocateCriteriaVO.
     */
    public ItemLocateCriteriaVO getItemLocateCriteriaVO() {
        return itemLocateCriteriaVO;
    }
    /**
     * @param itemLocateCriteriaVO
     * 
     * Sets the itemLocateCriteriaVO.
     */
    public void setItemLocateCriteriaVO(
            ItemLocateCriteriaVO itemLocateCriteriaVO) {
        this.itemLocateCriteriaVO = itemLocateCriteriaVO;
    }
    /**
     * @return primaryCode
     * 
     * Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }
    /**
     * @param primaryCode
     * 
     * Sets the primaryCode.
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }
    
	/**
	 * @return Returns the viewSubCatalogId.
	 */
	public int getViewSubCatalogId() {
		Logger.logInfo("SubCatalogBackingBean - Entering retrieveSubCatalogDetails(): Sub-Catalog");
		RetrieveSubCatalogRequest request = (RetrieveSubCatalogRequest) 
        this.getServiceRequest(ServiceManager.RETRIEVE_SUB_CATALOG);
        CatalogVO catalogVO = new CatalogVO();
        HttpSession httpSession = getSession();
        String action = getRequest().getParameter(WebConstants.ACTION_STRING);
        if(null == action || action.equals(WebConstants.EMPTY_STRING)){
            action = (String)httpSession.getAttribute(WebConstants.ACTION_STRING);
        }
        if (null != action && !action.equals(WebConstants.EMPTY_STRING)) {
            int retrieveKey = 0;
            if (action.equals(WebConstants.VIEW_STRING)) {
                String retKey = getRequest().getParameter("subCatalogId");
                retrieveKey = new Integer(retKey).intValue();
                httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(retKey));
                httpSession.setAttribute(WebConstants.ACTION_VIEW_STRING, WebConstants.VIEW_LOAD_STRING);
            } else {
               retrieveKey = ((Integer) httpSession.getAttribute(WebConstants.SUB_CATALOG_ID_STRING)).intValue();
               httpSession.setAttribute(WebConstants.SUB_CATALOG_ID_STRING, new Integer(retrieveKey));
            } 
            if(action.equals(WebConstants.VIEW_SESSION_STRING)){
                retrieveKey = ((Integer) httpSession.getAttribute(WebConstants.SUB_CATALOG_ID_STRING)).intValue(); 
            }
            catalogVO.setCatalogId((new Integer(retrieveKey)).intValue());
            request.setCatalogVO(catalogVO);
            request.setAction(1);
            RetrieveSubCatalogResponse response = (RetrieveSubCatalogResponse)this.executeService(request);
            if (null != response) {
                CatalogBO catalogBO = response.getCatalogBO();
                setValuesFromResponseToBackingBean(response.getDomainDetail(), catalogBO);
                httpSession.setAttribute(WebConstants.SUB_CATALOG_NAME_STRING, catalogBO.getCatalogName());
                this.setBreadCrumbText("Administration >> Sub-Catalog " + "("+ this.subCatalogName + ") >> View");
               
            }
        }
        Logger.logInfo("SubCatalogBackingBean - Returning retrieveSubCatalogDetails(): Sub-Catalog");
		return viewSubCatalogId;
	}
	/**
	 * @param viewSubCatalogId The viewSubCatalogId to set.
	 */
	public void setViewSubCatalogId(int viewSubCatalogId) {
		this.viewSubCatalogId = viewSubCatalogId;
	}
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Administration >> Sub-Catalog " + "("+ this.subCatalogName + ") >> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
	/**
	 * @return Returns the loadItemPopUpList.
	 */
	public String getLoadItemPopUpList() {
        ItemLookUpRequest itemLookUpRequest = (ItemLookUpRequest)getServiceRequest(ServiceManager.ITEM_LOOKUP_REQUEST);
        itemLocateCriteriaVO = new ItemLocateCriteriaVO();
        createItemLocateCriteriaVO();
        itemLookUpRequest.setCriteriaVO(itemLocateCriteriaVO);
        ItemLookUpResponse itemLookUpResponse = (ItemLookUpResponse) executeService(itemLookUpRequest);
        if(null != itemLookUpResponse.getItemList() && !itemLookUpResponse.getItemList().isEmpty()){
            this.setItemList(itemLookUpResponse.getItemList());
        }else{
            this.setItemList(null);
        }
		return loadItemPopUpList;
	}
	/**
	 * @param loadItemPopUpList The loadItemPopUpList to set.
	 */
	public void setLoadItemPopUpList(String loadItemPopUpList) {
		this.loadItemPopUpList = loadItemPopUpList;
	}
	
	/**
	 * @return Returns the requiredMarketBusinessUnit.
	 */
	public boolean isRequiredMarketBusinessUnit() {
		return requiredMarketBusinessUnit;
	}
	/**
	 * @param requiredMarketBusinessUnit The requiredMarketBusinessUnit to set.
	 */
	public void setRequiredMarketBusinessUnit(boolean requiredMarketBusinessUnit) {
		this.requiredMarketBusinessUnit = requiredMarketBusinessUnit;
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
	/**
	 * @return Returns the marketBusinessUnitCodeList.
	 */
	public List getMarketBusinessUnitCodeList() {
		return marketBusinessUnitCodeList;
	}
	/**
	 * @param marketBusinessUnitCodeList The marketBusinessUnitCodeList to set.
	 */
	public void setMarketBusinessUnitCodeList(List marketBusinessUnitCodeList) {
		this.marketBusinessUnitCodeList = marketBusinessUnitCodeList;
	}
}
