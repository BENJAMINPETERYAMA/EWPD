/*
 * ItemBackingBean.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;
import com.wellpoint.wpd.common.item.request.CatalogLookUpRequest;
import com.wellpoint.wpd.common.item.request.RetrieveItemRequest;
import com.wellpoint.wpd.common.item.request.SaveItemRequest;
import com.wellpoint.wpd.common.item.response.CatalogLookUpResponse;
import com.wellpoint.wpd.common.item.response.RetrieveItemResponse;
import com.wellpoint.wpd.common.item.response.SaveItemResponse;
import com.wellpoint.wpd.common.item.vo.ItemVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Base class for item functionalities
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemBackingBean extends WPDBackingBean {

    private String catalogId;

    private String primaryCode;

    private String secondaryCode;

    private String description;

    private String catalogName;
    
    private String catalogSize;

    private List catalogList;

    private int itemInfo;
    
    private List catalogSrdaList;
    

    //	User name of the Item created.
    private String createdUser;

    //Timestamp of the Item created.
    private Date createdTimestamp;

    //User name of the last updated Item.
    private String lastUpdatedUser;

    //Timestamp of the last updated Item.
    private Date lastUpdatedTimestamp;

    //	Messages displayed after validating.
    ArrayList validationMessages = null;

    private boolean requiredCatalogID = false;

    private boolean requiredPrimaryCode = false;

    private boolean requiredSecondaryCode = false;

    private boolean requiredDescription = false;
    
    private boolean requiredCatalogName = false;
    
    private String printBreadCrumbText;
    
    private String title;
    
    private String loadValues;
    //CARS START
    private boolean requiredFrequencyFlag = false;
    
    private String frequencyFlag;
    
    private String srdaFlag;
    
    public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
	private List frequencyFlagListForCombo;
    //CARS END
    /**
     * Constructor for setting breadcrumb value
     */
    public ItemBackingBean() {
        super();
        this.setBreadCrumbText("Administration >> Item >> Create");
    }

    /**
     * @return Returns the catalogId.
     */
    public String getCatalogId() {
        return catalogId;
    }

    /**
     * @param catalogId
     *            The catalogId to set.
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
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
     * 
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
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
     * 
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    /**
     * @return Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }

    /**
     * @param primaryCode
     *            The primaryCode to set.
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    /**
     * @return Returns the secondaryCode.
     */
    public String getSecondaryCode() {
        return secondaryCode;
    }

    /**
     * @param secondaryCode
     *            The secondaryCode to set.
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

    /**
     * creates the Item
     * 
     * @return the editpage
     */

    public String createItem() {
        validationMessages = new ArrayList();
        HttpSession httpSession = getSession();
        //String srdaFlag =(String)httpSession.getAttribute("ITEM_SRDA_FLAG");
        
        if (!validateRequiredFields(1,this.srdaFlag)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        //creating item request
        SaveItemRequest saveItemRequest = getCreateItemRequest();
        saveItemRequest.setCreateFlag(true);
     
       
        saveItemRequest.setSrdaFlag(this.srdaFlag);

        //creating item response object
        SaveItemResponse createItemResponse = (SaveItemResponse) executeService(saveItemRequest);

        //setting the values for edit page in the backing bean
 if (null != createItemResponse && (null != createItemResponse.getItemBO() || null!= createItemResponse.getItemsrdaBO() ) && !createItemResponse.isErrorFlag()) 
         {
             if(null!= createItemResponse.getItemsrdaBO()){
        		
        	this .setCatalogName(createItemResponse.getItemsrdaBO().getCatalogName());
                            
            Integer id = new Integer(createItemResponse.getItemsrdaBO()
                    .getCatalogId());
            this.catalogId = id.toString();
            //set the values from response itemBO to the Backing Bean 
            copyBusinessObjectToBackingBean(createItemResponse.getItemsrdaBO());
            this.setBreadCrumbText("Administration >> Item ("
                    + this.primaryCode + ") >> Edit");
            //Setting the values in session for edit page print
            httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, id.toString());
            httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, createItemResponse.getItemsrdaBO().getPrimaryCode());
            return WebConstants.UPDATE_STRUCTURE;
        	}else{
        		this .setCatalogName(createItemResponse.getItemBO().getCatalogName());
      
            Integer id = new Integer(createItemResponse.getItemBO()
                    .getCatalogId());
            this.catalogId = id.toString();
            //set the values from response itemBO to the Backing Bean 
            copyBusinessObjectToBackingBean(createItemResponse.getItemBO());
            this.setBreadCrumbText("Administration >> Item ("
                    + this.primaryCode + ") >> Edit");
            //Setting the values in session for edit page print
            httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, id.toString());
            httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, createItemResponse.getItemBO().getPrimaryCode());
            return WebConstants.UPDATE_STRUCTURE;
        }
             }
       else{
if(null!= createItemResponse.getItemsrdaBO()){
    		   copyBusinessObjectToBackingBean(createItemResponse.getItemsrdaBO());
    	   }else{
    		   copyBusinessObjectToBackingBean(createItemResponse.getItemBO()); 
    	   }
       	
       		return WebConstants.EMPTY_STRING;
       }

    }

    /**
     * updates the item
     * 
     * @return Empty String
     */

    public String updateItem() {
        this.setBreadCrumbText("Administration >> Item ("
                + this.primaryCode + ") >> Edit");
        validationMessages = new ArrayList();
        HttpSession httpSession = getSession();
        String srdaFlag =(String)httpSession.getAttribute("ITEM_SEARCH_FLAG");
        String hcsCode =(String)httpSession.getAttribute("HCS_CODE");
        if (!validateRequiredFields(2,srdaFlag)) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        
        //creating item request
        SaveItemRequest saveItemRequest = getUpdateItemRequest();
        saveItemRequest.getItemVO().setHcsCode(hcsCode);
        if(null != srdaFlag){
        	saveItemRequest.setSrdaFlag(srdaFlag);
        }
        //creating item response object
        SaveItemResponse createItemResponse = (SaveItemResponse) executeService(saveItemRequest);

        //setting the values from response to backing bean
        if (null != createItemResponse
                && (null != createItemResponse.getItemBO() ||null != createItemResponse.getItemsrdaBO() )) {
         //set the values from response itemBO to the Backing Bean 
if(null != createItemResponse.getItemBO()){
            copyBusinessObjectToBackingBean(createItemResponse.getItemBO());
            //Setting the values in session for edit page print
            httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, new Integer(createItemResponse.getItemBO().getCatalogId()).toString());
            httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, createItemResponse.getItemBO().getPrimaryCode());
	}else{
        		copyBusinessObjectToBackingBean(createItemResponse.getItemsrdaBO());
                //Setting the values in session for edit page print
                httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, new Integer(createItemResponse.getItemsrdaBO().getCatalogId()).toString());
                httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, createItemResponse.getItemsrdaBO().getPrimaryCode());
        	}
            this.setBreadCrumbText("Administration >>" + " Item ("
                    + this.getPrimaryCode() + ") >> Edit");
        }
        return WebConstants.EMPTY_STRING;
    }

    /**
     * validation of the fields
     * 
     * @return boolean
     */

    private boolean validateRequiredFields(int action, String flag) {
        validationMessages = new ArrayList();
        boolean requiredField = false;

        requiredCatalogID = false;
        requiredPrimaryCode = false;
        requiredSecondaryCode =false;
        requiredDescription = false;
        requiredCatalogName = false;
        int catSize = 0;
        List sizeList = new ArrayList();
        List catalogIdList = new ArrayList();
        List catalogNameList = new ArrayList();
        String catalogIdCode = null;
        String catalogIdCodeName = null;

        if (this.catalogId == null || WebConstants.EMPTY_STRING.equals(this.catalogId)) {

            this.requiredCatalogID = true;
            requiredField = true;
        }

        if (this.primaryCode.trim() == null || WebConstants.EMPTY_STRING.equals(this.primaryCode.trim())) {

            this.requiredPrimaryCode = true;
            requiredField = true;
        }
        
       
        if((action==1) && "SRDA".equalsIgnoreCase(flag) && (this.secondaryCode.trim() == null || WebConstants.EMPTY_STRING.equals(this.secondaryCode.trim()))){
        	this.requiredSecondaryCode =true;
            requiredField = true;
        }

        if (this.description.trim() == null || WebConstants.EMPTY_STRING.equals(this.description.trim())) {

            this.requiredDescription = true;
            requiredField = true;
        }
         
        //for CMA DOLLAR validation
if (null != this.catalogId && !(WebConstants.EMPTY_STRING.equals(this.catalogId))) {
            
            if(this.catalogId.equalsIgnoreCase("50450~CMA-DOLLARS~9")){
                  
      
                  
                 if (!this.primaryCode.matches("[-+]?[0-9]*\\.?[0-9]+")) {
                   validationMessages.add(new ErrorMessage(
                             WebConstants.PRIMARY_CODE_CMA));
                     return false;
                   }
                  
                  }
            }

        //CARS START
        //DESCRIPTION : Validation for frequency flag(Only for qualifier)
        if (null != this.catalogId && !(WebConstants.EMPTY_STRING.equals(this.catalogId))) {
        	if(action == 1){
        		String[] arrayTilda =  this.catalogId.split("~");
        		 if ("SRDA".equalsIgnoreCase(this.srdaFlag) && arrayTilda.length <=2){
            		 StringBuffer selectedCatalog = new StringBuffer(this.getCatalogId());
            		 selectedCatalog.append(0);
            		 setCatalogId(selectedCatalog.toString());
            	 }
	        	catalogIdList = WPDStringUtil.getListFromTildaString(this
	                    .getCatalogId(), 3, 1, 2);
              	catalogIdCode = (String) catalogIdList.get(0);
              	catalogNameList = WPDStringUtil.getListFromTildaString(this
                    .getCatalogId(), 3, 2, 2);
        		catalogIdCodeName = (String) catalogNameList.get(0);
        	}else{
        		catalogIdCode =this.catalogId;
              	catalogIdCodeName = this.catalogName;
        	}
          	//Checks catalog code equalant to qualifier code
        	if (null != catalogIdCode && catalogIdCode.equals(WebConstants.QUALIFIER_CODE)) {
        		//Checks if the frequency flag is empty.
				if ((null == frequencyFlag
						|| WebConstants.EMPTY_STRING.equals(this.frequencyFlag
								.trim())) && catalogIdCodeName.equals(WebConstants.QUALIFIER_CODE_NAME)) {
					this.requiredFrequencyFlag = true;
					requiredField = true;
				}
			}
        }
        //CARS END
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        
        if(action == 1){
            sizeList = WPDStringUtil.getListFromTildaString(this.catalogId,3,3,2);
        
       
	        if(!"SRDA".equalsIgnoreCase(flag) &&sizeList!= null && sizeList.size() > 0){
	            this.setCatalogSize((String)sizeList.get(0));
	            catSize = new Integer(this.catalogSize).intValue();
	        }
	        
	        if(!"SRDA".equalsIgnoreCase(flag) && this.primaryCode.length() > catSize){
	            ErrorMessage errorMessage = new ErrorMessage((WebConstants.PRIMARYCODE_SIZE_INVALID));
	            errorMessage.setParameters(new String[] { " "+catSize });
	            validationMessages.add(errorMessage);
	           
	            return false;
	        }
        }
        if(this.secondaryCode.trim().length() > 30){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.SECONDARY_CODE_VALIDATION));
            return false;
        	
        }
        if (!this.description.trim().equals(WebConstants.EMPTY_STRING) && this.description.length() > 240) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_1_240));
            return false;
        }
        return true;
    }

    /**
     * Creates SaveItemRequest
     * 
     * @return SaveItemRequest
     */

    private SaveItemRequest getCreateItemRequest() {

        SaveItemRequest saveItemRequest = (SaveItemRequest) this
                .getServiceRequest(ServiceManager.CREATE_ITEM_REQUEST);

        ItemVO itemVO = copyValueObjectfromBackingBean();
        List catalogIdList = WPDStringUtil.getListFromTildaString(this
                .getCatalogId(), 3, 1, 2);
        List catalogNameList = WPDStringUtil.getListFromTildaString(this
                .getCatalogId(), 3, 2, 2);

        if (catalogIdList != null && catalogIdList.size() > 0) {
            String id = ((String) (catalogIdList.get(0)));
            itemVO.setCatalogId(new Integer(id).intValue());
        }

        if (catalogNameList != null && catalogNameList.size() > 0) {
            itemVO.setCatalogName((String) (catalogNameList.get(0)));
        }
        String[] arrayValue = null;
        if(null != this.catalogId && !this.catalogId.equals("")){
       	 arrayValue =this.catalogId.split("~");
        }
        if (arrayValue != null && arrayValue.length > 2 && !"0".equalsIgnoreCase(arrayValue[2]) ){
          	 itemVO.setHcsCode(arrayValue[2]);
           }
        //CARS START
        //DESCRIPTION : Null checks the frequency field 
        if ((null != this.frequencyFlag)
				&& !((WebConstants.EMPTY_STRING).equals(this.frequencyFlag))){
        	if(this.frequencyFlag.equals("YES")){
        		this.setFrequencyFlag("Y");
        	}else{
        		this.setFrequencyFlag("N");
        	}
        	itemVO.setFrequencyRequired(this.getFrequencyFlag());
        }//CARS END
        saveItemRequest.setItemVO(itemVO);
        saveItemRequest.setCreateFlag(true);

        //      Returning the itemVO to the request object
        return saveItemRequest;
    }

    /**
     * creates SaveItemRequest
     * 
     * @return SaveItemRequest
     */
    private SaveItemRequest getUpdateItemRequest() {

        SaveItemRequest saveItemRequest = (SaveItemRequest) this
                .getServiceRequest(ServiceManager.CREATE_ITEM_REQUEST);

        ItemVO itemVO = copyValueObjectfromBackingBean();
        itemVO.setCatalogId((new Integer(this.getCatalogId())).intValue());
        itemVO.setCatalogName(this.getCatalogName());

        //Setting the itemVO to the request object
        saveItemRequest.setItemVO(itemVO);
        saveItemRequest.setCreateFlag(false);

        return saveItemRequest;

    }

    /**
     * Retrieves the item info
     * 
     * @return Returns the itemInfo.
     */
    public int getItemInfo() {
        String action = getRequest().getParameter(WebConstants.ACTION_STRING);
        RetrieveItemRequest request = getRetrieveItemRequest(action);
        
        HttpSession httpSession = getSession();
        String srdaFlag =(String)httpSession.getAttribute("ITEM_SEARCH_FLAG");
        String hcsCode =(String)httpSession.getAttribute("HCS_CODE");
        if(null != srdaFlag){
        	request.setSrdaFlag(srdaFlag);
        }
        if(null!= hcsCode && "SRDA".equalsIgnoreCase(srdaFlag)){
        	request.setHcsCode(hcsCode);
        }
        RetrieveItemResponse response = (RetrieveItemResponse) executeService(request);

         if(null != response.getItemBO() || null!=response.getItemsrdaBO() || null != response.getItemsrdaHCSBO()){
	        // Sets the values in BO to backing bean properties
        	if(null!=response.getItemsrdaBO()){
            copyBusinessObjectToBackingBean(response.getItemsrdaBO());
            this.setItemInfo(response.getItemsrdaBO().getCatalogId());
            this.setBreadCrumbText("Administration >> Item ("
	                + this.primaryCode + ") >> View");
        	}else if(null!=response.getItemBO()){
            copyBusinessObjectToBackingBean(response.getItemBO());

	        this.setItemInfo(response.getItemBO().getCatalogId());
	        this.setBreadCrumbText("Administration >> Item ("
	                + this.primaryCode + ") >> View");
        }else{
        	copyBusinessObjectToBackingBean(response.getItemsrdaHCSBO());
            this.setItemInfo(response.getItemsrdaHCSBO().getCatalogId());
            this.setBreadCrumbText("Administration >> Item ("
	                + this.primaryCode + ") >> View");
        }
        	}
        return itemInfo;
}

private void copyBusinessObjectToBackingBean(ItemSrdaHCSBO itemsrdaHCSBO) {
	 this.setCatalogName(itemsrdaHCSBO.getCatalogName());
     this.setPrimaryCode(itemsrdaHCSBO.getPrimaryCode());
     this.setDescription(itemsrdaHCSBO.getDescription());
     this.setCreatedTimestamp(itemsrdaHCSBO
             .getCreatedTimestamp());
     this.setCreatedUser(itemsrdaHCSBO.getCreatedUser());
     this.setLastUpdatedTimestamp(itemsrdaHCSBO
             .getLastUpdatedTimestamp());
     this.setLastUpdatedUser(itemsrdaHCSBO.getLastUpdatedUser());
}

		


private void copyBusinessObjectToBackingBean(ItemSrdaBO itemsrdaBO) {
		// TODO Auto-generated method stub
		
    	 this.setCatalogName(itemsrdaBO.getCatalogName());
         this.setPrimaryCode(itemsrdaBO.getPrimaryCode());
         this.setDescription(itemsrdaBO.getDescription());
         this.setSecondaryCode(itemsrdaBO.getSecondaryCode());
         this.setCreatedTimestamp(itemsrdaBO
                 .getCreatedTimestamp());
         this.setCreatedUser(itemsrdaBO.getCreatedUser());
         this.setLastUpdatedTimestamp(itemsrdaBO
                 .getLastUpdatedTimestamp());
         this.setLastUpdatedUser(itemsrdaBO.getLastUpdatedUser());
    }

    /**
     * creates RetrieveItemRequest
     * 
     * @return retrieveItemRequest
     */
    private RetrieveItemRequest getRetrieveItemRequest(String action) {

        HttpSession httpSession = getSession();
        String catalogId = WebConstants.EMPTY_STRING, primaryCode = WebConstants.EMPTY_STRING;
        if (action.equals(WebConstants.VIEW_STRING)) {
            this.setBreadCrumbText("Administration >>" + " Item ("
                    + this.getPrimaryCode() + ") >> View");
            catalogId = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("catalogId"));
            if(null!=catalogId  && catalogId.matches("^[0-9a-zA-Z_]+$")){
            	catalogId = catalogId;
    		}else{
    			catalogId=null;
    		}
            primaryCode = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("prmryCode"));
            if(null!=primaryCode  && primaryCode.matches("^[0-9a-zA-Z_]+$")){
            	primaryCode = primaryCode;
    		}else{
    			primaryCode=null;
    		}
            httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, catalogId);
            httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, primaryCode);

        } else if (action.equals(WebConstants.PRINT_STRING)) {
            catalogId = (String) httpSession.getAttribute(WebConstants.CATALOG_ID_STRING);
            primaryCode = (String) httpSession.getAttribute(WebConstants.PRIMARY_CODE_STRING);
        }

        RetrieveItemRequest request = (RetrieveItemRequest) getServiceRequest(ServiceManager.ITEM_VIEW_REQUEST);

        ItemVO itemVO = new ItemVO();
        itemVO.setCatalogId((new Integer(catalogId)).intValue());
        itemVO.setPrimaryCode(primaryCode);

        request.setItemVO(itemVO);

        return request;
    }

    /**
     * Retrieves the item for viewing 
     * @return 
     */
    public int getItemList() {
        String action = getRequest().getParameter(WebConstants.ACTION_STRING);
        RetrieveItemRequest request = getRetrieveItemRequest(action);

        RetrieveItemResponse response = (RetrieveItemResponse) executeService(request);
       
        // Sets the values in BO to backing bean properties
        copyBusinessObjectToBackingBean(response.getItemBO());

        this.setBreadCrumbText("Administration >>" + " Item ("
                + this.getPrimaryCode() + ") >> View");

        return 1;
    }
    /**
     * loads the item for edit
     * 
     * @return "itemEdit"
     */
    public String loadItemForEdit() {
        HttpSession httpSession = getSession();
        Logger
                .logInfo("CatalogBackingBean - Entering loadCatalogForEdit(): Catalog Edit");
        int retrieveKey = new Integer(this.getCatalogId()).intValue();
        String primaryCode = this.getPrimaryCode();
        retrieveItemDetails(retrieveKey, primaryCode);
        //Setting the values of gatalog_id and primary_code for edit print 
        httpSession.setAttribute(WebConstants.CATALOG_ID_STRING, new Integer(retrieveKey).toString());
        httpSession.setAttribute(WebConstants.PRIMARY_CODE_STRING, primaryCode);
        this.setBreadCrumbText("Administration >>" + " Item ("
                + this.getPrimaryCode() + ") >> Edit");
        return WebConstants.ITEM_EDIT;
    }

    /**
     * Retrieves the item
     * @param retrieveKey
     * @param primaryCode
     */
    private void retrieveItemDetails(int retrieveKey, String primaryCode) {
        RetrieveItemRequest request = (RetrieveItemRequest) getServiceRequest(ServiceManager.ITEM_VIEW_REQUEST);

        ItemVO itemVO = new ItemVO();
        itemVO.setCatalogId(retrieveKey);
        itemVO.setPrimaryCode(primaryCode);

        request.setItemVO(itemVO);
        HttpSession httpSession = getSession();
        String srdaFlag =(String)httpSession.getAttribute("ITEM_SEARCH_FLAG");
        String hcsCode =(String)httpSession.getAttribute("HCS_CODE");
        if(null != srdaFlag){
        	request.setSrdaFlag(srdaFlag);
        }
        if(null!= hcsCode &&"SRDA".equalsIgnoreCase(srdaFlag)){
        	request.setHcsCode(hcsCode);
        }

        RetrieveItemResponse response = (RetrieveItemResponse) executeService(request);
      
        // Sets the values in BO to backing bean properties
 if(null!= response.getItemsrdaBO()){
        copyBusinessObjectToBackingBean(response.getItemsrdaBO());
        }else if (null!=response.getItemBO()){
        	 copyBusinessObjectToBackingBean(response.getItemBO());
        }else{
        	copyBusinessObjectToBackingBean(response.getItemsrdaHCSBO());
        }

    }

    /**
     * Copy value object from the BackingBean
     * 
     * @return ItemVO
     */

    public ItemVO copyValueObjectfromBackingBean() {
        ItemVO itemVO = new ItemVO();

        itemVO.setCreatedTimestamp(this
                .getCreatedTimestamp());
        itemVO.setCreatedUser(this.getCreatedUser());
        itemVO.setDescription(this.getDescription().toUpperCase().trim());
        itemVO.setLastUpdatedTimestamp(this
                .getLastUpdatedTimestamp());
        itemVO.setLastUpdatedUser(this.getLastUpdatedUser());
        itemVO.setPrimaryCode(this.getPrimaryCode().toUpperCase().trim());
        itemVO.setSecondaryCode(this.getSecondaryCode().toUpperCase().trim());
        itemVO.setCatalogName(this.getCatalogName());
        //CARS START
        if(null != this.getFrequencyFlag() && !(WebConstants.EMPTY_STRING.equals(this.getFrequencyFlag()))){
        	if(this.getFrequencyFlag().equals("YES")){
        		itemVO.setFrequencyRequired("Y");
        	}else{
        		itemVO.setFrequencyRequired("N");
        	}
        }
        //CARS END
        return itemVO;
    }
    /**
     * Sets the values from ItemBO from the 
     * response Object to Backing Bean
     * @param itemBO
     */
    private void copyBusinessObjectToBackingBean(ItemBO itemBO){
        this.setCatalogName(itemBO.getCatalogName());
        this.setPrimaryCode(itemBO.getPrimaryCode());
        this.setDescription(itemBO.getDescription());
        this.setSecondaryCode(itemBO.getSecondaryCode());
        //CARS START
        //DESCRIPTION : Setting the frequency required flag from BO to backing bean
        if (null != itemBO.getFrequencyRequired()
				&& !((WebConstants.EMPTY_STRING).equals(itemBO
						.getFrequencyRequired()))){
        	if(itemBO.getFrequencyRequired().equals("Y")){
        		this.setFrequencyFlag("YES");
        	}else{
        		this.setFrequencyFlag("NO");
        	}
        }
        //CARS END
        this.setCreatedTimestamp(itemBO
                .getCreatedTimestamp());
        this.setCreatedUser(itemBO.getCreatedUser());
        this.setLastUpdatedTimestamp(itemBO
                .getLastUpdatedTimestamp());
        this.setLastUpdatedUser(itemBO.getLastUpdatedUser());
    }

    /**
     * Retrieves the catalog list for popup page
     * 
     * @return List
     */

    public List getCatalogList() {
        return catalogList;
    }
    
    /**
     * @param catalogList
     *            The catalogList to set.
     */
    public void setCatalogList(List catalogList) {
        this.catalogList = catalogList;
    }

    /**
     * @return Returns the catalogName.
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * @param catalogName
     *            The catalogName to set.
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * @param itemInfo
     *            The itemInfo to set.
     */
    public void setItemInfo(int itemInfo) {
        this.itemInfo = itemInfo;
    }

    /**
     * @return Returns the requiredDescription.
     * @return boolean requiredDescription
     */
    public boolean isRequiredDescription() {
        return requiredDescription;
    }

    /**
     * Sets the requiredDescription
     * 
     * @param requiredDescription
     */
    public void setRequiredDescription(boolean requiredDescription) {
        this.requiredDescription = requiredDescription;
    }

    /**
     * @return Returns the requiredPrimaryCode.
     * @return boolean requiredPrimaryCode
     */
    public boolean isRequiredPrimaryCode() {
        return requiredPrimaryCode;
    }

    /**
     * Sets the requiredPrimaryCode
     * 
     * @param requiredPrimaryCode
     */
    public void setRequiredPrimaryCode(boolean requiredPrimaryCode) {
        this.requiredPrimaryCode = requiredPrimaryCode;
    }
    
    /**
     * @return Returns the catalogSize.
     * @return String catalogSize
     */
    public String getCatalogSize() {
        return catalogSize;
    }
    /**
     * Sets the catalogSize
     * @param catalogSize
     */
    public void setCatalogSize(String catalogSize) {
        this.catalogSize = catalogSize;
    }
    

    /**
     * @return Returns the requiredCatalogName.
     * @return boolean requiredCatalogName
     */
    public boolean isRequiredCatalogName() {
        return requiredCatalogName;
    }
    /**
     * Sets the requiredCatalogName
     * @param requiredCatalogName
     */
    public void setRequiredCatalogName(boolean requiredCatalogName) {
        this.requiredCatalogName = requiredCatalogName;
    }
    
    /**
     * @return Returns the requiredcatalogID.
     * @return boolean requiredcatalogID
     */
    public boolean isRequiredCatalogID() {
        return requiredCatalogID;
    }
    /**
     * Sets the requiredcatalogID
     * @param requiredcatalogID
     */
    public void setRequiredCatalogID(boolean requiredCatalogID) {
        this.requiredCatalogID = requiredCatalogID;
    }
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Administration >> Item ("+ this.primaryCode + ") >> Print";
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
	 * @return Returns the title.
	 */
	public String getTitle() {
		HttpServletRequest request = getRequest();
		return getRequest().getParameter("title");
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the loadValues.
	 */
	public String getLoadValues() {
		CatalogLookUpRequest request = (CatalogLookUpRequest) getServiceRequest(ServiceManager.SEARCH_ITEM);
		 
		request.setSrdaFlag("eWPD"); 
         
        CatalogLookUpResponse response = (CatalogLookUpResponse) executeService(request);
        if(null != response.getCatalogList() && !response.getCatalogList().isEmpty()){
        	this.catalogList = response.getCatalogList();
        }else{
        	this.catalogList = null;
        }  
		return loadValues;
	}
	
	/**
	 * @return Returns the loadValues.
	 */
	public String getLoadSrdaValues() {
		CatalogLookUpRequest request = (CatalogLookUpRequest) getServiceRequest(ServiceManager.SEARCH_ITEM);
		 
        request.setSrdaFlag("SRDA"); 
         
        CatalogLookUpResponse response = (CatalogLookUpResponse) executeService(request);
        if(null != response.getCatalogList() && !response.getCatalogList().isEmpty()){
        	this.catalogSrdaList = response.getCatalogList();
        }else{
        	this.catalogSrdaList = null;
        }  
		return loadValues;
	}
	/**
	 * @param loadValues The loadValues to set.
	 */
	public void setLoadValues(String loadValues) {
		this.loadValues = loadValues;
	}
	/**
	 * @return Returns the frequencyFlag.
	 */
	public String getFrequencyFlag() {
		return frequencyFlag;
	}
	/**
	 * @param frequencyFlag The frequencyFlag to set.
	 */
	public void setFrequencyFlag(String frequencyFlag) {
		this.frequencyFlag = frequencyFlag;
	}
	/**
	 * @return Returns the frequencyFlagListForCombo.
	 */
	public List getFrequencyFlagListForCombo() {
		frequencyFlagListForCombo = new ArrayList();
		frequencyFlagListForCombo.add(new SelectItem(""));
		frequencyFlagListForCombo.add(new SelectItem("YES"));
		frequencyFlagListForCombo.add(new SelectItem("NO"));		
		return frequencyFlagListForCombo;
	}
	/**
	 * @param frequencyFlagListForCombo The frequencyFlagListForCombo to set.
	 */
	public void setFrequencyFlagListForCombo(List frequencyFlagListForCombo) {
		this.frequencyFlagListForCombo = frequencyFlagListForCombo;
	}
	/**
	 * @return Returns the requiredFrequencyFlag.
	 */
	public boolean isRequiredFrequencyFlag() {
		return requiredFrequencyFlag;
	}
	/**
	 * @param requiredFrequencyFlag The requiredFrequencyFlag to set.
	 */
	public void setRequiredFrequencyFlag(boolean requiredFrequencyFlag) {
		this.requiredFrequencyFlag = requiredFrequencyFlag;
	}
	
	
	

	/**
	 * @return the catalogSrdaList
	 */
	public List getCatalogSrdaList() {
		return catalogSrdaList;
	}

	/**
	 * @param catalogSrdaList the catalogSrdaList to set
	 */
	public void setCatalogSrdaList(List catalogSrdaList) {
		this.catalogSrdaList = catalogSrdaList;
	}

	/**
	 * @return the requiredSecondaryCode
	 */
	public boolean isRequiredSecondaryCode() {
		return requiredSecondaryCode;
	}

	/**
	 * @param requiredSecondaryCode the requiredSecondaryCode to set
	 */
	public void setRequiredSecondaryCode(boolean requiredSecondaryCode) {
		this.requiredSecondaryCode = requiredSecondaryCode;
	}

}
