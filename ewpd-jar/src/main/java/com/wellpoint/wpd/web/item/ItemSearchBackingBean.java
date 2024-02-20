/*
 * ItemSearchBackingBean.java 
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 * Created on May 16, 2007
 */
package com.wellpoint.wpd.web.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.item.request.SearchItemRequest;
import com.wellpoint.wpd.common.item.response.SearchItemResponse;
import com.wellpoint.wpd.common.item.vo.ItemLocateCriteriaVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Base class for item search and delete
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemSearchBackingBean extends WPDBackingBean {

    private String catalogName;
    
    private String selectedCatalogName;

    private String primaryCode;

    private String secondaryCode;
    
    private String description;

    private List searchResultList;
    
    private int catalogId;

    private ItemLocateCriteriaVO itemLocateCriteriaVO;

    private List validationMessage;
    
    private List catalogIdList;
    
    private String srdaFlag;
    
    public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
	/**
     * @return Returns the selectedCatalogName.
     * @return String selectedCatalogName
     */
    public String getSelectedCatalogName() {
        return selectedCatalogName;
    }
    /**
     * Sets the selectedCatalogName
     * @param selectedCatalogName
     */
    public void setSelectedCatalogName(String selectedCatalogName) {
        this.selectedCatalogName = selectedCatalogName;
    }
    /**
     * constructor.
     */
    public ItemSearchBackingBean() {
        this.setBreadCrumbText("Administration >> Item " 
        		+ ">> Locate");
    }


    /**
     * This method is used to search a Item according to the Search Criteria.
     * 
     * @return String
     */
    public String itemSearch() {
    	
    	if (!validateFields()) {
            addAllMessagesToRequest(this.validationMessage);
            return WebConstants.EMPTY_STRING;
        }
        //Get the request object
        SearchItemRequest searchItemRequest = getSearchItemRequest();
        HttpSession httpSession = getSession();
        httpSession.setAttribute("ITEM_SEARCH_FLAG",this.srdaFlag);
        
        // Call the executeService method to get the response
        SearchItemResponse searchItemResponse = (SearchItemResponse) this
                .executeService(searchItemRequest);
        if (null != searchItemResponse) {
            //Set the resulting Item from the database
            // from the response to the searchResultList
            searchResultList = searchItemResponse.getItemList();
          
            //Set breadcrumb text
            this.setBreadCrumbText("Administration >>" + " Item >> Locate");
        }
        return WebConstants.EMPTY_STRING;
    }
    
    /**
     * Checks whether the fields are empty
     * 
     * @return boolean
     */
    private boolean validateFields() {
    	validationMessage = new ArrayList();
    	
        if ((null == this.selectedCatalogName || this.selectedCatalogName.equals(""))
                && (null == this.primaryCode || "".equals(this.primaryCode.trim())
                        && (null == secondaryCode || ""
                                .equals(this.secondaryCode.trim())))) {
        	validationMessage.add(new ErrorMessage(BusinessConstants.REQUIRED_FIELDS));
            return false;
        }

        return true;
    }

    
    /**
     * This method is used to get the request object to which the values from VO
     * is added.
     * 
     * @return SearchItemRequest
     */
    private SearchItemRequest getSearchItemRequest() {
        SearchItemRequest searchItemRequest = (SearchItemRequest) this
                .getServiceRequest(ServiceManager.MAINTAIN_ITEM);
        //function call to create VO
        createItemLocateCriteriaVO();
        //set values from VO to request
        searchItemRequest
                .setCriteriaVO(itemLocateCriteriaVO);
        return searchItemRequest;
    }

    /**
     * This method will set the values from backing bean to the VO.
     * 
     * @return void.
     */
    private void createItemLocateCriteriaVO() {
        itemLocateCriteriaVO = new ItemLocateCriteriaVO();
        String prmryCode = this.primaryCode;
        String secCode = this.secondaryCode;
        String[] arrayTilda = null;
        if(null != this.selectedCatalogName && !this.selectedCatalogName.equals("")){
        	 arrayTilda = this.selectedCatalogName.split("~");
        	 if ("SRDA".equalsIgnoreCase(this.srdaFlag) && arrayTilda.length <=2){
        		 StringBuffer selectedCatalog = new StringBuffer(this.selectedCatalogName);
        		 selectedCatalog.append(0);
        		 setSelectedCatalogName(selectedCatalog.toString());
        	 }
	        this.setCatalogIdList( WPDStringUtil.getListFromTildaString(this.selectedCatalogName, 3, 1, 1));
        
	        itemLocateCriteriaVO.setCatalogIdList(catalogIdList);
        }
        
        if(prmryCode.indexOf("%") >= 0 ){
        	prmryCode = prmryCode.replaceAll("%", "`%");
    	}else if(prmryCode.indexOf("_") >= 0){
    	    prmryCode = prmryCode.replaceAll("_", "`_");
    	}
        if(secCode.indexOf("%") >= 0 ){
            secCode = secCode.replaceAll("%", "`%");
        }else if(secCode.indexOf("_") >= 0){
            secCode = secCode.replaceAll("_", "`_");
    	}
        
        HttpSession httpSession = getSession();
       
       if (arrayTilda != null && arrayTilda.length > 2 && "SRDA".equalsIgnoreCase(this.srdaFlag) && !"0".equalsIgnoreCase(arrayTilda[2])){
    	   itemLocateCriteriaVO.setDescription(arrayTilda[2]);
    	   httpSession.setAttribute("HCS_CODE",arrayTilda[2]);
       }
        itemLocateCriteriaVO.setPrimaryCode(prmryCode.trim());
        itemLocateCriteriaVO.setSecondaryCode(secCode.trim());
        itemLocateCriteriaVO.setSrdaFlag(this.srdaFlag);
    }

    /**
     * Returns the catalogName
     * 
     * @return String catalogName.
     */

    public String getCatalogName() {
        return catalogName;
    }

    /**
     * Sets the catalogName
     * 
     * @param catalogName.
     */

    public void setCatalogName(String catalogName) {

        this.catalogName = catalogName;
    }

    /**
     * Returns the searchResultList
     * 
     * @return List searchResultList.
     */

    public List getSearchResultList() {
        return searchResultList;
    }

    /**
     * Sets the searchResultList
     * 
     * @param searchResultList.
     */

    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * Returns the primaryCode
     * 
     * @return String primaryCode.
     */

    public String getPrimaryCode() {
        return primaryCode;
    }

    /**
     * Sets the primaryCode
     * 
     * @param primaryCode.
     */

    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    /**
     * Returns the secondaryCode
     * 
     * @return String secondaryCode.
     */

    public String getSecondaryCode() {
        return secondaryCode;
    }

    /**
     * Sets the secondaryCode
     * 
     * @param secondaryCode.
     */

    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
    /**
     * @return Returns the catalogIdList.
     * @return List catalogIdList
     */
    public List getCatalogIdList() {
        return catalogIdList;
    }
    /**
     * Sets the catalogIdList
     * @param catalogIdList
     */
    public void setCatalogIdList(List catalogIdList) {
        this.catalogIdList = catalogIdList;
    }
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}