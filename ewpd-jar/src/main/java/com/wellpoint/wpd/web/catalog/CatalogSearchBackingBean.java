/*
 * CatalogSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.catalog.request.CatalogDeleteRequest;
import com.wellpoint.wpd.common.catalog.request.SearchCatalogRequest;
import com.wellpoint.wpd.common.catalog.response.CatalogDeleteResponse;
import com.wellpoint.wpd.common.catalog.response.SearchCatalogResponse;
import com.wellpoint.wpd.common.catalog.vo.CatalogLocateCriteriaVO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Base class for catalog search and delete
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogSearchBackingBean extends WPDBackingBean {

    private String catalogName;
    
    private int catalogId;

    private String description;

    private CatalogBO catalogBO;

    private List searchResultList;

    private CatalogLocateCriteriaVO catalogLocateCriteriaVO;

    private List validationMessage;
    
    private int selectedCatalogId;
    
    private String srdaFlag;

	/**
	 * @return Returns the selectedCatalogId.
	 */
	public int getSelectedCatalogId() {
		return selectedCatalogId;
	}
	/**
	 * @param selectedCatalogId The selectedCatalogId to set.
	 */
	public void setSelectedCatalogId(int selectedCatalogId) {
		this.selectedCatalogId = selectedCatalogId;
	}
    /**
     * constructor.
     */
    public CatalogSearchBackingBean() {
        this.setBreadCrumbText("Administration >> Catalog " + ">> Locate");
    }

    /**
     * This method is used to search a Catalog according to the Search Criteria.
     * 
     * @return String
     */
    public String catalogSearch() {
    	
    	if (!validateFields()) {
            addAllMessagesToRequest(this.validationMessage);
            return WebConstants.EMPTY_STRING;
        }
    	
    	 HttpSession httpSession = getSession();
         httpSession.setAttribute("SESSION_SRDA_FLAG", this.srdaFlag);
        //Get the request object
        SearchCatalogRequest searchCatalogRequest = getSearchCatalogRequest();    
        // Call the executeService method to get the response
        SearchCatalogResponse searchCatalogResponse = (SearchCatalogResponse) this
                .executeService(searchCatalogRequest);
        if (null != searchCatalogResponse) {
            //Set the resulting Catalog from the database
            // from the response to the searchResultList
            if(null != searchCatalogResponse.getCatalogList()){
            searchResultList = searchCatalogResponse.getCatalogList();
	          if("eWPD".equalsIgnoreCase(this.srdaFlag)){  
	            for(int i = 0 ; i < searchResultList.size() ; i++){
	                CatalogBO catalogBO = (CatalogBO) searchResultList.get(i);
	                String description = "";
	                if(catalogBO.getDescription().length() > 15){
	                    description = catalogBO.getDescription();
	                    description = description.substring(0,15).concat("...");
	                    catalogBO.setDescription(description);
	                }
	            }
	          }else{
	        	  for(int i = 0 ; i < searchResultList.size() ; i++){
		                SrdaCatalogBO srdaCatalogBO = (SrdaCatalogBO) searchResultList.get(i);
		                String description = "";
		                if(srdaCatalogBO.getDescription().length() > 15){
		                    description = srdaCatalogBO.getDescription();
		                    description = description.substring(0,15).concat("...");
		                    srdaCatalogBO.setDescription(description);
		                }
		            }
	          }
	            
	           
            }
            //Set breadcrumb text
            this.setBreadCrumbText("Administration >>" + " Catalog >> Locate");

        }
        return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Checks whether the fields are empty
     * 
     * 
     * @return boolean
     */
    private boolean validateFields() {
    	validationMessage = new ArrayList();
    	
        if ((null == this.catalogName.trim() || this.catalogName.trim().equals(""))
                && (null == this.description.trim() || "".equals(this.description.trim()))) {
        	validationMessage.add(new ErrorMessage(BusinessConstants.REQUIRED_FIELDS));
            return false;
        }
 
        return true;
    }

    /**
     * This method is used to get the request object to which the values from VO
     * is added.
     * 
     * @return SearchCatalogRequest
     */
    private SearchCatalogRequest getSearchCatalogRequest() {
        SearchCatalogRequest searchCatalogRequest = (SearchCatalogRequest) this
                .getServiceRequest(ServiceManager.SEARCH_CATALOG);
        //function call to create VO
        createCatalogLocateCriteriaVO();
        //set values from VO to request
        searchCatalogRequest
                .setCatalogLocateCriteriaVO(catalogLocateCriteriaVO);
        return searchCatalogRequest;
    }

    /**
     * This method will set the values from backing bean to the VO.
     * 
     * @return void.
     */
    private void createCatalogLocateCriteriaVO() {
        catalogLocateCriteriaVO = new CatalogLocateCriteriaVO();
        String desc = this.description;
        String name = this.catalogName;
        	if(desc.indexOf("%")>=0){
        		desc = desc.replaceAll("%", "`%");
        	}else if(desc.indexOf("_") >= 0){
        	    desc = desc.replaceAll("_", "`_");
        	}
            catalogLocateCriteriaVO.setCatalogDescription(desc.trim());

        	if(name.indexOf("%")>=0){
        		name = name.replaceAll("%", "`%");
        	}else if(name.indexOf("_") >= 0){
        	    name = name.replaceAll("_", "`_");
        	}
            catalogLocateCriteriaVO.setCatalogName(name.trim());

            catalogLocateCriteriaVO.setSrdaFlag(this.srdaFlag);
    }
    
    /**
     * Method to delete catalog
     * @return String
     */
    public String deleteCatalog() {
        Logger.logInfo("CatalogBackingBean - Entering deleteCatalog(): Catalog");
        CatalogDeleteRequest catalogDeleteRequest = (CatalogDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_CATALOG_REQUEST);
        CatalogVO catalogVO = new CatalogVO();
        catalogVO.setCatalogId(this.getSelectedCatalogId());
        catalogDeleteRequest.setCatalogVO(catalogVO);
        createCatalogLocateCriteriaVO();
		catalogDeleteRequest.setCatalogLocateCriteriaVO(catalogLocateCriteriaVO);
		CatalogDeleteResponse catalogDeleteResponse= (CatalogDeleteResponse) executeService(catalogDeleteRequest);
		if (null != catalogDeleteResponse.getCatalogList()&&catalogDeleteResponse.getCatalogList().size()==0) {
			this.setSearchResultList(null);	
		}
		else
		{
			if(null != catalogDeleteResponse.getCatalogList()){
				searchResultList=catalogDeleteResponse.getCatalogList();
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
			  this.setBreadCrumbText("Administration >>" + " Catalog >> Locate");
		
			
        Logger.logInfo("CatalogBackingBean - Returning deleteCatalog(): Catalog");
        return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Returns the catalogName
     * @return String catalogName.
     */

    public String getCatalogName() {
        return catalogName;
    }

    /**
     * Sets the catalogName
     * @param catalogName.
     */

    public void setCatalogName(String catalogName) {

        this.catalogName = catalogName;
    }

    /**
     * Returns the searchResultList
     * @return List searchResultList.
     */

    public List getSearchResultList() {
        return searchResultList;
    }

    /**
     * Sets the searchResultList
     * @param searchResultList.
     */

    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * Returns the catalogBO
     * @return CatalogBO catalogBO.
     */

    public CatalogBO getCatalogBO() {
        return catalogBO;
    }
    /**
     * Sets the catalogBO
     * @param catalogBO.
     */

    public void setCatalogBO(CatalogBO catalogBO) {
        this.catalogBO = catalogBO;
    }
    /**
     * Returns the catalogLocateCriteriaVO
     * @return CatalogLocateCriteriaVO catalogLocateCriteriaVO.
     */

    public CatalogLocateCriteriaVO getCatalogLocateCriteriaVO() {
        return catalogLocateCriteriaVO;
    }

    /**
     * Sets the catalogLocateCriteriaVO
     * @param catalogLocateCriteriaVO.
     */

    public void setCatalogLocateCriteriaVO(
            CatalogLocateCriteriaVO catalogLocateCriteriaVO) {
        this.catalogLocateCriteriaVO = catalogLocateCriteriaVO;
    }

    /**
     * Returns the validationMessage
     * @return List validationMessage.
     */

    public List getValidationMessage() {
        return validationMessage;
    }

    /**
     * Sets the validationMessage
     * @param validationMessage.
     */

    public void setValidationMessage(List validationMessage) {
        this.validationMessage = validationMessage;
    }

    /**
     * Returns the description
     * @return String description.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * @param description.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the catalogId
     * @return int catalogId.
     */

    public int getCatalogId() {
        return catalogId;
    }
    
	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}
	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}

    

}