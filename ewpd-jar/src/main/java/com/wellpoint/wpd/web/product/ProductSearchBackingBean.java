/*
 * ProductSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.product.bo.ProductSearch;
import com.wellpoint.wpd.common.product.request.DeleteProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.request.SearchProductRequest;
import com.wellpoint.wpd.common.product.response.DeleteProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.response.SearchProductResponse;
import com.wellpoint.wpd.common.product.vo.ProductSearchCriteriaVO;
import com.wellpoint.wpd.common.product.vo.ProductVO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductSearchBackingBean extends ProductBackingBean {

    private String selectedKeyFromSearch;
	
	private String productName;

    private String productDescription;

    private String productStructure;

    private String productFamily;

    private List searchResultList;

    private int selectedProductId;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;

    private String effectiveDate;

    private String expiryDate;

    private String view;

    private String copy;

    private String edit;

    private String delete;

    private String previous;

    private List testResultList;

    private List validationMessages;
    
    private List previousVersionList;
    
    private boolean invalidDateField = false;
    
    private String hiddenInit;

    ProductSearch productSearch = new ProductSearch();
    
    private boolean versionListRetrieved;
    
    private String pageId;
    
    private String selectedProductTypeFromSearch;
    
    private String productSearchPrint;
    
    private boolean productPrint;
    
    private String printBreadCrumbText;
    
    private String breadCrumbViewAllVersions;
    //CARS START
    private String marketBusinessUnit;
    //CARS END

    /**
     * @return productPrint
     * 
     * Returns the productPrint.
     */
    public boolean isProductPrint() {
        return productPrint;
    }
    /**
     * @param productPrint
     * 
     * Sets the productPrint.
     */
    public void setProductPrint(boolean productPrint) {
        this.productPrint = productPrint;
    }
    /**
     * @return productSearchPrint
     * 
     * Returns the productSearchPrint.
     */
    public String getProductSearchPrint() {
        this.setProductPrint(true);
        SearchProductRequest searchProductRequest = (SearchProductRequest) this
		        .getServiceRequest(ServiceManager.SEARCH_PRODUCT);
		
		searchProductRequest = setValuesToRequest(searchProductRequest);
		searchProductRequest.setAction(SearchProductRequest.SEARCH_PRODUCT);
		SearchProductResponse searchProductResponse = null;
		//calls the service
		searchProductResponse = (SearchProductResponse) executeService(searchProductRequest);
		super.setSearchResultsToSession(searchProductResponse.getProductList());      
		//sets the response to the list
		if (searchProductResponse != null
		        && searchProductResponse.getProductList() != null
		        && searchProductResponse.getProductList().size() != 0) {
		    searchResultList = searchProductResponse.getProductList();
		}
        return productSearchPrint;
    }
    /**
     * @param productSearchPrint
     * 
     * Sets the productSearchPrint.
     */
    public void setProductSearchPrint(String productSearchPrint) {
        this.productSearchPrint = productSearchPrint;
    }
    public ProductSearchBackingBean(){
    	
    		 this.setBreadCumbTextForSearch();
    	
    }
    
    public String unLockAction()throws WPDException {
    	int key= Integer.parseInt(selectedKeyFromSearch);
    	ProductVO productVO =new ProductVO();
    	super.setKeyObjectToSessionForEdit(key);
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	saveProductRequest.setAction(SaveProductRequest.UNLOCK_PRODUCT);
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }

    /**
     * This method sends the product for test
     * @return
     * @throws WPDException
     */
    public String sendToTestAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	ProductVO productVO =new ProductVO();
    	String prdType = selectedProductTypeFromSearch;
    	productVO.setProductType(prdType);
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.SEND_TO_TEST_PRODUCT);
    	saveProductRequest.setProduct(productVO);
  
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    /**
     * this method is used for test pass process
     * @return
     * @throws WPDException
     */
    		
    public String testPassAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.TEST_PASS_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }

    /**
     * 
     * @return
     * @throws WPDException
     */
    public String testFailAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.TEST_FAIL_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    
    
    
    /**
     * This method publishes the product
     * @return
     * @throws WPDException
     */
    public String publishAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.PUBLISH_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    
    /**
     * This method schedules the Product for Approval
     * @return
     * @throws WPDException
     */
    public String scheduleForApprovalAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.SCHEDULE_FOR_APPROVAL_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
    	//calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    /**
     * This method deletes the Product
     * @return
     * @throws WPDException
     */
    public String deleteAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
    	//ProductSearchResult productSearchResult = super.getLocateObjectFromSession(key);
        //cleanSession();
        //setProductKeyObject(productSearchResult);
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	DeleteProductRequest deleteProductRequest = (DeleteProductRequest) this
         .getServiceRequest(ServiceManager.DELETE_PRODUCT);
    	
    	deleteProductRequest.setProductKey(key);
    	//calls the service
    	DeleteProductResponse deleteProductResponse=(DeleteProductResponse)executeService(deleteProductRequest);
    	if(null != deleteProductResponse){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = deleteProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
     /**
      * transfer
      * @return
      */
    public String transferAction(){
        List messageList;
        int key= Integer.parseInt(selectedKeyFromSearch);
        super.setKeyObjectToSessionForEdit(key);
    	//gets request
        SaveProductRequest request = (SaveProductRequest) this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
        request.setAction(SaveProductRequest.SCHEDULE_TO_PRODUCTION);
        //calls the service
        SaveProductResponse response = (SaveProductResponse) this.executeService(request);
        
        if(null != response){
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = response.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
        }
        return "";
    }
    
    
    /**
     * This method approves the product
     * @return
     * @throws WPDException
     */
    public String approveAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.APPROVE_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
        //calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
    		//this.setProductName(saveProductRequest.getProductKeyObject().getProductName());
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    
    /**
     * This method rejects the product
     * @return
     * @throws WPDException
     */
    public String rejectAction() throws WPDException {
    	
    	int key= Integer.parseInt(selectedKeyFromSearch);
  
    	super.setKeyObjectToSessionForEdit(key);
    	//gets request
    	SaveProductRequest saveProductRequest = (SaveProductRequest)
		this.getServiceRequest(ServiceManager.SAVE_PRODUCT);
    	
    	saveProductRequest.setAction(SaveProductRequest.REJECT_PRODUCT);
    	
    	SaveProductResponse saveProductResponse=null;
        //calls the service
    	saveProductResponse =(SaveProductResponse)this.executeService( saveProductRequest );
    	
    	if(null != saveProductResponse ){
    		this.setProductName(saveProductRequest.getProductKeyObject().getProductName());
   	    	if(this.pageId.equals("searchVersionPage"))
   	    	    this.versionListRetrieved = false;
   	    	else {
   	   	    	List list = saveProductResponse.getMessages();
   	    	    this.productSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	}
    	}
    	return "";
    }
    
    
    /**
     * This method searches the details of the product
     * @return
     * @throws WPDException
     */
    public String productSearch() {
        cleanSession();
        //checks if the fields are empty or not
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        
        //valdates the date fields
        if(!isDateFieldsValid()){
            addAllMessagesToRequest(validationMessages);
            return "";
        }
    	//gets request
        SearchProductRequest searchProductRequest = (SearchProductRequest) this
                .getServiceRequest(ServiceManager.SEARCH_PRODUCT);

        searchProductRequest = setValuesToRequest(searchProductRequest);
        searchProductRequest.setAction(SearchProductRequest.SEARCH_PRODUCT);
        SearchProductResponse searchProductResponse = null;
        //calls the service
        searchProductResponse = (SearchProductResponse) executeService(searchProductRequest);
        super.setSearchResultsToSession(searchProductResponse.getProductList());      
        //sets the response to the list
        if (searchProductResponse != null
                && searchProductResponse.getProductList() != null
                && searchProductResponse.getProductList().size() != 0) {
            searchResultList = searchProductResponse.getProductList();
        }
        return "";

    }

    /**
     * sets the values to request
     * @param searchProductRequest
     * @return
     */
    private SearchProductRequest setValuesToRequest(
            SearchProductRequest searchProductRequest) {
        
        ProductSearchCriteriaVO productSearchCriteriaVO = new ProductSearchCriteriaVO();
        if(!isProductPrint()){
            //          saving new data

            List lobList = WPDStringUtil.getListFromTildaString(
                    this.lineOfBusiness, 2, 2, 2);
            List entityList = WPDStringUtil.getListFromTildaString(
                    this.businessEntity, 2, 2, 2);
            List groupList = WPDStringUtil.getListFromTildaString(
                    this.businessGroup, 2, 2, 2);
            //CARS START
            List marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
                    this.marketBusinessUnit, 2, 2, 2);
            //CARS END
            List structureList = WPDStringUtil.getListFromTildaString(
                    this.productStructure, 2, 1, 2);

            List familyList = WPDStringUtil.getListFromTildaString(
                    this.productFamily, 2, 2, 2);

            productSearchCriteriaVO.setLineOfBusinessList(lobList);
            productSearchCriteriaVO.setBusinessEntityList(entityList);
            productSearchCriteriaVO.setBusinessGroupList(groupList);
            productSearchCriteriaVO.setMarketBusinessUnitList(marketBusinessUnitList);
            productSearchCriteriaVO.setProductFamilyList(familyList);
            productSearchCriteriaVO.setProductStructureList(structureList);

            productSearchCriteriaVO.setEffectiveDate(WPDStringUtil
                    .getDateFromString(this.effectiveDate));
            productSearchCriteriaVO.setExpiryDate(WPDStringUtil
                    .getDateFromString(this.expiryDate));

            productSearchCriteriaVO.setProductName(this.productName.trim());

            searchProductRequest
                    .setProductSearchCriteriaVO(productSearchCriteriaVO);
            getRequest().getSession().removeAttribute("productSearchCriteriaVO");
            getRequest().getSession().setAttribute("productSearchCriteriaVO",searchProductRequest.getProductSearchCriteriaVO());
        }else{
            if(null != getRequest().getSession().getAttribute("productSearchCriteriaVO")){
                searchProductRequest.setProductSearchCriteriaVO((ProductSearchCriteriaVO)getRequest().getSession().getAttribute("productSearchCriteriaVO"));
    		}
        }
        return searchProductRequest;

    }

    /**
     * Function for performing the validations.
     * 
     * @return boolean
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList(1);
        boolean valid = false;
        if ((null == this.productName || "".equals(this.productName.trim()))
                && (null == this.effectiveDate || "".equals(this.effectiveDate.trim()))
                && (null == this.expiryDate || "".equals(this.expiryDate.trim()))
                && (null == this.productStructure || ""
                        .equals(this.productStructure.trim()))
                && (null == this.productFamily || "".equals(this.productFamily.trim()))
                && (null == this.lineOfBusiness || ""
                        .equals(this.lineOfBusiness.trim()))
                && (null == this.businessEntity || ""
                        .equals(this.businessEntity))
                && (null == this.businessGroup || "".equals(this.businessGroup.trim()))
				&& (null == this.marketBusinessUnit || "".equals(this.marketBusinessUnit.trim())))
            valid = true;
        if (valid) {
            validationMessages.add(new ErrorMessage("all.fields.blank"));
            searchResultList = null;
            return false;
        }
        return true;
    }
    
    /**
     * checks for the validation of date fields
     * @return
     */
    private boolean isDateFieldsValid(){
        
         	//validates the effective date
            if(null!=this.effectiveDate && !(this.effectiveDate.trim().equals("")) && !(StringUtil.isDate(this.effectiveDate))){        	
	            ErrorMessage errorMessage = new ErrorMessage(
	                    WebConstants.INPUT_FORMAT_INVALID);
	            errorMessage.setParameters(new String[] {"Effective Date" });
	           	validationMessages.add( errorMessage);
	           	invalidDateField=true;
	         }
            //validates the expiry date
	        if(null!=this.expiryDate && !(this.expiryDate.trim().equals("")) && !(StringUtil.isDate(this.expiryDate))){        	
	            ErrorMessage errorMessage = new ErrorMessage(
	                    WebConstants.INPUT_FORMAT_INVALID);
	            errorMessage.setParameters(new String[] {"Expiry Date" });
	           	validationMessages.add( errorMessage);
	           	invalidDateField=true;
	         }
	        //checks if the effective date is greater than expiry date
            if(WPDStringUtil.isValidDate(this.effectiveDate.trim()) && WPDStringUtil.isValidDate(this.expiryDate.trim()) ){
		           Date  effectDate = WPDStringUtil.getDateFromString(this.effectiveDate.trim());
		           Date  expDate = WPDStringUtil.getDateFromString(this.expiryDate.trim());
		           if(null!=effectDate&&null!=expDate)
		        {
		           if(effectDate.compareTo(expDate)>0){         	
		         	  validationMessages.add(new ErrorMessage(
		                    WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
		         	 invalidDateField=true;
		         	
		          }
		        }
	        }
	 
        
            if(invalidDateField){
                //will add invalid field messages to request
                return false;
            }
            else{
                return true;
            }
            
       
    }
    
    //Method for retrieving previous versions.
    
    
//    public List  getPreviousVersions(){
//        //super.setKeyObjectForViewFromSearchResultList(key);
//    	ProductSessionObject productSessionObject = getProductSessionObject();
//		int productId = super.getProductKeyFromSession();
//		SearchProductRequest searchProductRequest = (SearchProductRequest) this
//        .getServiceRequest(ServiceManager.SEARCH_PRODUCT);
//		searchProductRequest.setAction(SearchProductRequest.SEARCH_ALL_VERSION);
//		SearchProductResponse searchProductResponse = (SearchProductResponse)executeService(searchProductRequest);  
//		searchProductRequest.setProductId(productId);
//    	return previousVersionList; 
//    }

    /**
     * 
     * Returns the productDescription
     * @return String productDescription.
     *  
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * 
     * Sets the productDescription
     * @param productDescription.
     *  
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * 
     * Returns the productFamily
     * @return String productFamily.
     *  
     */
    public String getProductFamily() {
        return productFamily;
    }

    /**
     * 
     * Sets the productFamily
     * @param productFamily.
     *  
     */
    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    /**
     * 
     * Returns the productName
     * @return String productName.
     *  
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 
     * Sets the productName
     * @param productName.
     *  
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 
     * Returns the productStructure
     * @return String productStructure.
     *  
     */
    public String getProductStructure() {
        return productStructure;
    }

    /**
     * 
     * Sets the productStructure
     * @param productStructure.
     *  
     */
    public void setProductStructure(String productStructure) {
        this.productStructure = productStructure;
    }

    /**
     * 
     * Returns the selectedProductId
     * @return int selectedProductId.
     *  
     */

    public int getSelectedProductId() {
        return selectedProductId;
    }

    /**
     * 
     * Returns the copy
     * @return String copy.
     *  
     */
    public String getCopy() {
        return copy;
    }

    /**
     * 
     * Sets the copy
     * @param copy.
     *  
     */
    public void setCopy(String copy) {
        this.copy = copy;
    }

    /**
     * 
     * Returns the delete
     * @return String delete.
     *  
     */
    public String getDelete() {
        return delete;
    }

    /**
     * 
     * Sets the delete
     * @param delete.
     *  
     */
    public void setDelete(String delete) {
        this.delete = delete;
    }

    /**
     * 
     * Returns the edit
     * @return String edit.
     *  
     */
    public String getEdit() {
        return edit;
    }

    /**
     * 
     * Sets the edit
     * @param edit.
     *  
     */
    public void setEdit(String edit) {
        this.edit = edit;
    }

    /**
     * 
     * Returns the previous
     * @return String previous.
     *  
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * 
     * Sets the previous
     * @param previous.
     *  
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * 
     * Returns the view
     * @return String view.
     *  
     */
    public String getView() {
        return view;
    }

    /**
     * 
     * Sets the view
     * @param view.
     *  
     */
    public void setView(String view) {
        this.view = view;
    }

    /**
     * 
     * Returns the searchResultList
     * @return String searchResultList.
     *  
     */
    public List getSearchResultList() {
        return searchResultList;
    }

    /**
     * 
     * Sets the searchResultList
     * @param searchResultList.
     *  
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * 
     * Returns the businessEntity
     * @return String businessEntity.
     *  
     */

    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * 
     * Returns the businessGroup
     * @return String businessGroup.
     *  
     */

    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * 
     * Returns the lineOfBusiness
     * @return String lineOfBusiness.
     *  
     */

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    /**
     * 
     * Sets the businessEntity
     * @param businessEntity.
     *  
     */

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * 
     * Sets the businessGroup
     * @param businessGroup.
     *  
     */

    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * 
     * Sets the lineOfBusiness
     * @param lineOfBusiness.
     *  
     */

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    /**
     * 
     * Returns the productSearch
     * @return ProductSearch productSearch.
     *  
     */

    public ProductSearch getProductSearch() {
        return productSearch;
    }

    /**
     * 
     * Sets the productSearch
     * @param productSearch.
     *  
     */

    public void setProductSearch(ProductSearch productSearch) {
        this.productSearch = productSearch;
    }

    /**
     * 
     * Returns the validationMessages
     * @return List validationMessages.
     *  
     */

    public List getValidationMessages() {
        return validationMessages;
    }

    /**
     * 
     * Sets the validationMessages
     * @param validationMessages.
     *  
     */

    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }

    /**
     * 
     * Returns the effectiveDate
     * @return String effectiveDate.
     *  
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 
     * Sets the effectiveDate
     * @param effectiveDate.
     *  
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 
     * Returns the expiryDate
     * @return String expiryDate.
     *  
     */

    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * 
     * Sets the expiryDate
     * @param expiryDate.
     *  
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 
     * Sets the selectedProductId
     * @param selectedProductId.
     *  
     */

    public void setSelectedProductId(int selectedProductId) {
        this.selectedProductId = selectedProductId;
    }
	
	/**
	 * Returns the selectedKeyFromSearch.
	 * @return String selectedKeyFromSearch.
	 */
	public String getSelectedKeyFromSearch() {
		return selectedKeyFromSearch;
	}
	
	/**
	 * Sets the selectedKeyFromSearch 
	 * @param selectedKeyFromSearch 
	 */
	public void setSelectedKeyFromSearch(String selectedKeyFromSearch) {
		this.selectedKeyFromSearch = selectedKeyFromSearch;
	}
	/**
	 * Returns the previousVersionList
	 * @return List previousVersionList.
	 */

    public List getPreviousVersionList() {
        if( !this.versionListRetrieved) {
            String requestProductKey = getRequest().getParameter("productKey");
	        int productId = -1;
	        if(null != requestProductKey ) {
	            productId = Integer.parseInt(requestProductKey);
	            getProductSessionObject().setProductKeyForVersionPage(productId);
	        } 
	        productId = getProductSessionObject().getProductKeyForVersionPage();
	        
	        SearchProductRequest searchProductRequest = (SearchProductRequest) this.getServiceRequest(ServiceManager.SEARCH_PRODUCT);
	        searchProductRequest.setProductId(productId);
	        searchProductRequest.setAction(SearchProductRequest.SEARCH_ALL_VERSION);
	        SearchProductResponse searchProductResponse = (SearchProductResponse) executeService(searchProductRequest);
	        if (null != searchProductResponse) {
	            previousVersionList = searchProductResponse
	                    .getAllProductVersionsList();
	            getProductSessionObject().setAllVersionSearchResults(previousVersionList);
	        }
	        versionListRetrieved = true;
        }
        return previousVersionList;
    }
		
	/**
	 * Sets the previousVersionList
	 * @param previousVersionList.
	 */

	public void setPreviousVersionList(List previousVersionList) {
		this.previousVersionList = previousVersionList;
	}
	/**
	 * Returns the hiddenInit
	 * @return String hiddenInit.
	 */
	public String getHiddenInit() {
		return hiddenInit;
	}
	/**
	 * Sets the hiddenInit
	 * @param hiddenInit.
	 */
	public void setHiddenInit(String hiddenInit) {
		this.hiddenInit = hiddenInit;
	}
    /**
     * Returns the pageId
     * @return String pageId.
     */
    public String getPageId() {
        return pageId;
    }
    /**
     * Sets the pageId
     * @param pageId.
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
	/**
	 * @return Returns the selectedProductTypeFromSearch.
	 */
	public String getSelectedProductTypeFromSearch() {
		return selectedProductTypeFromSearch;
	}
	/**
	 * @param selectedProductTypeFromSearch The selectedProductTypeFromSearch to set.
	 */
	public void setSelectedProductTypeFromSearch(
			String selectedProductTypeFromSearch) {
		this.selectedProductTypeFromSearch = selectedProductTypeFromSearch;
	}
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Product >> Locate >> Print";
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
     * Returns the breadCrumbViewAllVersions
     * 
     * @return String breadCrumbViewAllVersions.
     */

    public String getBreadCrumbViewAllVersions() {
        String keyString = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("PName")));
        if(null!=keyString  && keyString.matches("^[0-9a-zA-Z_]+$")){
        	keyString = keyString;
       }else{
    	   keyString=null;
       }
        if(null!=keyString)
        	getRequest().getSession().setAttribute("keyString",keyString);
        if(null == keyString || keyString.equals(""))
        	keyString =(String)getRequest().getSession().getAttribute("keyString");
        this.setBreadCrumbText("Product Configuration >> Product"
                + "("+keyString+") >> View All Versions");
        return "dummy";
    }


    /**
     * Sets the breadCrumbViewAllVersions
     * 
     * @param breadCrumbViewAllVersions.
     */

    public void setBreadCrumbViewAllVersions(String breadCrumbViewAllVersions) {
        String keyString = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("PSName")));
        if(null!=keyString  && keyString.matches("^[0-9a-zA-Z_]+$")){
        	keyString = keyString;
       }else{
    	   keyString=null;
       }
        this.setBreadCrumbText("Product Configuration >> Product"
                + " ("+keyString+") >> View All Versions");
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