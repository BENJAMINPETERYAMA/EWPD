/*
 * SubCatalogSearchBackingBean.java
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.subCatalog;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogDeleteRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogSearchRequest;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSearchResponse;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * Base class for subCatalog Search and delete
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class SubCatalogSearchBackingBean extends WPDBackingBean{
    private String subCatalogName;
    
    private String catalogName;
    
    private List validationMessage;
    
    private String catalogId;
    
    private List searchResultList;
    
    private String description;
    
    private int selectedSubCatalogId;
    
    private SubCatalogLocateCriteria subCatalogLocateCriteria;
    
    /**
     * Constructor of the class
     *
     */
    public SubCatalogSearchBackingBean(){
        this.setBreadCrumbText("Administration >> Sub-Catalog " + ">> Locate");
    }
    
    /**
     * Method to search the sub catalog based on the search criteria entered by
     * the user
     * @return String
     */
    public String search(){
        
        // Validate the fields
        if(!validateFields()){
            // Add the messages to the request
            addAllMessagesToRequest(this.validationMessage);
            
            // Return the forward string
            return WebConstants.EMPTY_STRING;
        }
        
        // Create the request object
        SubCatalogSearchRequest subCatalogSearchRequest = getSearchRequest();
        
        // Call the executeService() to get the response
        SubCatalogSearchResponse subCatalogSearchResponse = 
            (SubCatalogSearchResponse) this.executeService(subCatalogSearchRequest);
        
        // Get the result list from the response
        List subCatalogList = subCatalogSearchResponse.getSubCatalogList();
        
        // Check whether the result list is null or empty
        if(null != subCatalogList && !subCatalogList.isEmpty()){
        	// If not, set the list to the backing bean
            List newList = new ArrayList();
            for(int i = 0 ; i < subCatalogList.size() ; i++){
                CatalogBO catalogBO = (CatalogBO) subCatalogList.get(i);
                String description = "";
                if(catalogBO.getDescription().length() > 15){
                    description = catalogBO.getDescription();
                    description = description.substring(0,15).concat("...");
                    catalogBO.setDescription(description);
                    newList.add(catalogBO);
                }
            }
            this.searchResultList = subCatalogList;
        }else{
        	// If yes, set the list to null in the backing bean
            this.searchResultList = null;
        }
        
        // Set the bread crumb to locate
        this.setBreadCrumbText("Administration >> Sub-Catalog " + ">> Locate");
        // Return the forward string
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method to create the request object and set the values to it
     * @return SubCatalogSearchRequest
     */
    private SubCatalogSearchRequest getSearchRequest() {
        // Create an instance of the request object
        SubCatalogSearchRequest request = 
            (SubCatalogSearchRequest)this.getServiceRequest(ServiceManager.SEARCH_SUB_CATALOG);
        
        // Create an instance of the VO
        SubCatalogVO subCatalogVO = new SubCatalogVO();
        
        // Set the values in the VO
        if(null != this.subCatalogName && !this.subCatalogName.trim().equals("")){
            
            // Check for the case when '%' or '_' is entered in the search criteria
            String name = this.subCatalogName;
            if(name.indexOf("%") >= 0 ){
                name = name.replaceAll("%", "`%");
        	}else if(name.indexOf("_") >= 0){
        	    name = name.replaceAll("_", "`_");
        	}
            subCatalogVO.setSubCatalogName(name.trim());
        }
        
        if(null != this.catalogId && !this.catalogId.equals("")){
            
            // Get the selected catalog ids to a list
            List catalogIdList = WPDStringUtil.getListFromTildaString(this.catalogId, 3, 1, 2);
            
            // Set the catalog id list to the VO
            subCatalogVO.setCatalogIdList(catalogIdList);
        }
        
        // Set the VO in the request
        request.setSubCatalogVO(subCatalogVO);
        
        // Return the request
        return request;
    }
    
    /**
     * Method to delete catalog
     * @return String
     */
    public String deleteSubCatalog() {
        Logger.logInfo("SubCatalogBackingBean - Entering deleteCatalog(): Catalog");
        SubCatalogDeleteRequest subCatalogDeleteRequest = (SubCatalogDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_SUB_CATALOG_REQUEST);
        SubCatalogVO subCatalogVO = new SubCatalogVO();
        subCatalogVO.setSelectedSubCatalogId(this.getSelectedSubCatalogId());
        if(null != this.subCatalogName && !this.subCatalogName.equals("")){
            String name = this.subCatalogName;
            // Check for the case when % is entered as search criteria
            if(name.indexOf("%") >= 0 ){
                name = name.replaceAll("%", "`%");
        	}else if(name.indexOf("_") >= 0){
        	    name = name.replaceAll("_", "`_");
        	}

            subCatalogVO.setSubCatalogName(name);

        }
        
        if(null != this.catalogId && !this.catalogId.equals("")){
            
            // Get the selected catalog ids to a list
            List catalogIdList = WPDStringUtil.getListFromTildaString(this.catalogId, 2, 1, 1);
            
            // Set the catalog id list to the VO
            subCatalogVO.setCatalogIdList(catalogIdList);
        }
        subCatalogDeleteRequest.setSubCatalogVO(subCatalogVO);
		SubCatalogDeleteResponse subCatalogDeleteResponse= (SubCatalogDeleteResponse) executeService(subCatalogDeleteRequest);
		if (null != subCatalogDeleteResponse.getSubCatalogList()&& !subCatalogDeleteResponse.getSubCatalogList().isEmpty()) {
			if(null != subCatalogDeleteResponse.getSubCatalogList()){
				searchResultList=subCatalogDeleteResponse.getSubCatalogList();
		   for(int i = 0 ; i < searchResultList.size() ; i++){
            CatalogBO catalogBO = (CatalogBO) searchResultList.get(i);
            String description = "";
            if(catalogBO.getDescription().length() > 15){
                description = catalogBO.getDescription();
                description = description.substring(0,15).concat("...");
                catalogBO.setDescription(description);
            }
        }
			}
		}
		else
		    this.setSearchResultList(null);
		this.setBreadCrumbText("Administration >>" + " Sub-Catalog >> Locate");
		
			
        Logger.logInfo("SubCatalogSearchBackingBean - Returning deleteSubCatalog(): Sub-Catalog");
        return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Method to validate the search page fields
     * @return boolean
     */
    private boolean validateFields() {
        validationMessage = new ArrayList();
        // Set the breadcrumb to lacate
        this.setBreadCrumbText("Administration >> Sub-Catalog " + ">> Locate");
        // Check whether all the fields in the search page are empty
        if((null == this.subCatalogName || this.subCatalogName.trim().equals(""))
                && (null == this.catalogId || this.catalogId.equals(""))){
            
        	// If so, set the required error message 
            validationMessage.add(new ErrorMessage(BusinessConstants.REQUIRED_FIELDS));
            return false;
        }
        
        return true;
    }
    
    
    /**
     * @return Returns the catalogName.
     * @return String catalogName
     */
    public String getCatalogName() {
        return catalogName;
    }
    /**
     * Sets the catalogName
     * @param catalogName
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
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
        this.subCatalogName = subCatalogName;
    }
    
    /**
     * @return Returns the validationMessage.
     * @return List validationMessage
     */
    public List getValidationMessage() {
        return validationMessage;
    }
    /**
     * Sets the validationMessage
     * @param validationMessage
     */
    public void setValidationMessage(List validationMessage) {
        this.validationMessage = validationMessage;
    }
    
    /**
     * @return Returns the catalogId.
     * @return String catalogId
     */
    public String getCatalogId() {
        return catalogId;
    }
    /**
     * Sets the catalogId
     * @param catalogId
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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
        this.description = description;
    }
    
	/**
	 * @return Returns the selectedSubCatalogId.
	 */
	public int getSelectedSubCatalogId() {
		return selectedSubCatalogId;
	}
	/**
	 * @param selectedSubCatalogId The selectedSubCatalogId to set.
	 */
	public void setSelectedSubCatalogId(int selectedSubCatalogId) {
		this.selectedSubCatalogId = selectedSubCatalogId;
	}
	
	/**
	 * @return Returns the subCatalogLocateCriteria.
	 */
	public SubCatalogLocateCriteria getSubCatalogLocateCriteria() {
		return subCatalogLocateCriteria;
	}
	/**
	 * @param subCatalogLocateCriteria The subCatalogLocateCriteria to set.
	 */
	public void setSubCatalogLocateCriteria(
			SubCatalogLocateCriteria subCatalogLocateCriteria) {
		this.subCatalogLocateCriteria = subCatalogLocateCriteria;
	}
}
