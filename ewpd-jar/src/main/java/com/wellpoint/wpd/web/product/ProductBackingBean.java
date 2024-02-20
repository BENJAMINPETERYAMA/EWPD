/*
 * ProductBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductSearchResult;
import com.wellpoint.wpd.common.product.request.ProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.response.RetrieveProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.Collections;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBackingBean extends WPDBackingBean {
	
	public static String PRODUCT_SESSION_KEY = WebConstants.PROD_TYPE;
	public static String PRODUCT_TREE_BEAN = WebConstants.PRODUCT_TREE_BACKING_BEAN;
	
	/**
	 * Retrieves the ProductSessionObject from session.
	 * @return ProductSessionObject
	 */
	protected ProductSessionObject getProductSessionObject(){
		ProductSessionObject productSessionObject = (ProductSessionObject)getSession().getAttribute(PRODUCT_SESSION_KEY);
		
		if(productSessionObject == null) {
			productSessionObject = new ProductSessionObject();
			getSession().setAttribute(PRODUCT_SESSION_KEY,productSessionObject);
		}
		return productSessionObject;
	}
	
	protected void setValuesForAminMethodValidation(){
		String entityId = getProductSessionObject().getProductKeyObject().getProductId() + "";
		String entityName = getProductSessionObject().getProductKeyObject().getProductName();
		String entityType = "product";
		
		getSession().setAttribute("AM_ENTITY_ID",entityId);
 		getSession().setAttribute("AM_ENTITY_TYPE",entityType);
 		getSession().setAttribute("AM_ENTITY_NAME",entityName);
	}
	
    private void setProductKeyObject(ProductBO productBO){
        ProductKeyObject productKeyObject = getProductSessionObject().getProductKeyObject();
        if(productKeyObject == null){
            productKeyObject = new ProductKeyObject();
            getProductSessionObject().setProductKeyObject(productKeyObject);
        }
        productKeyObject.setProductId(productBO.getProductKey());
        productKeyObject.setProductName(productBO.getProductName());
        productKeyObject.setVersion(productBO.getVersion());
        productKeyObject.setStatus(productBO.getStatus());
        productKeyObject.setBusinessDomains(productBO.getBusinessDomains());
        productKeyObject.setParentId(productBO.getParentProductKey());
        productKeyObject.setProductType(productBO.getProductType());
        if(productBO.getState() != null)
            productKeyObject.setState(productBO.getState().getState());
        Collections.sort(productKeyObject.getBusinessDomains());
    }
    
    protected void setTierDefinitionListToSession(List tierDefinitionList){
    	getProductSessionObject().setBenefitTierDefinitionList(tierDefinitionList);
    }
    
    protected void setTierLevalListToSession(List tierLevelList){
    	getProductSessionObject().setBenefitTierLevelList(tierLevelList);
    }
    
    protected void setTierDefWithPsvlListToSession(List tierDefPsvlList){
    	getProductSessionObject().setBenefitTierPsvlList(tierDefPsvlList);
    }
    
    protected void removeTierDefWithPsvlListFromSession(){
    	if (null != getProductSessionObject().getBenefitTierPsvlList())
    		getProductSessionObject().setBenefitTierPsvlList(null);
    }
    
    protected void removeTierDefinitionListFromSession(){
    	if (null != getProductSessionObject().getBenefitTierDefinitionList())
    		getProductSessionObject().setBenefitTierDefinitionList(null);
    }
    
    protected void removeTierLevalListFromSession(){
    	if (null != getProductSessionObject().getBenefitTierLevelList())
    		getProductSessionObject().setBenefitTierLevelList(null);
    }
    
    protected void cleanSession(){
        int productKeyForVersionPage = getProductSessionObject().getProductKeyForVersionPage();
        getSession().removeAttribute(PRODUCT_SESSION_KEY);
        SessionCleanUp.removeManagedBean(PRODUCT_TREE_BEAN);
        getSession().removeAttribute(WebConstants.
        						SESSION_DELETED_RULES_LIST);
        getSession().removeAttribute(WebConstants.
        						SESSION_UNCODED_RULES_LIST);
        // Retain key for all version page.
        getProductSessionObject().setProductKeyForVersionPage(productKeyForVersionPage);
    }
    
    protected ProductKeyObject getProductKeyObject(){
        return getProductSessionObject().getProductKeyObject();
    }
    
    private void setProductKeyObjectToRequest(ProductRequest productRequest) {
        productRequest.setProductKeyObject(this.getProductKeyObject());
    }

    protected WPDResponse executeService(ProductRequest request) {
        setProductKeyObjectToRequest(request);
        WPDResponse response = super.executeService(request);
        // Setting the key values to session.
        if(response instanceof SaveProductResponse){
            int action = ((SaveProductRequest)request).getAction(); 
            if(action == SaveProductRequest.CREATE_PRODUCT || action == SaveProductRequest.UPDATE_PRODUCT || action == SaveProductRequest.COPY_PRODUCT ||action == SaveProductRequest.CHECKOUT_PRODUCT) {
	            SaveProductResponse saveProductResponse = (SaveProductResponse) response;
	            if(saveProductResponse.isSuccess())
	                setProductKeyObject(saveProductResponse.getProductBO());
            }
        }
        if(response instanceof RetrieveProductResponse){
            RetrieveProductResponse retrieveProductResponse = (RetrieveProductResponse) response;
            if(retrieveProductResponse.isSuccess())
                setProductKeyObject(retrieveProductResponse.getProductBO());
        }
        //getProductSessionObject().getProductKeyObject();//FIXME Remove hardcoded values
        return response;
    }
    
    private void setProductKeyObject(ProductSearchResult productLocateResult){
        ProductKeyObject productKeyObject = getProductSessionObject().getProductKeyObject();
        if(productKeyObject == null){	
            productKeyObject = new ProductKeyObject();
            getProductSessionObject().setProductKeyObject(productKeyObject);
        }
        productKeyObject.setProductId(productLocateResult.getProductKey());
        productKeyObject.setProductName(productLocateResult.getProductName());
        productKeyObject.setVersion(productLocateResult.getVersion());
        productKeyObject.setParentId(productLocateResult.getParentKey());
        productKeyObject.setStatus(productLocateResult.getStatus());
        productKeyObject.setBusinessDomains(productLocateResult.getBusinessDomains());
        if(productLocateResult.getState() != null)
            productKeyObject.setState(productLocateResult.getState().getState());
        Collections.sort(productKeyObject.getBusinessDomains()); 
    }
    
    private ProductSearchResult getLocateObjectFromSessionList(int productKey){
        boolean found = false;
        List searchResultList = getProductSessionObject().getAllVersionSearchResults();

        ProductSearchResult searchResult;
        int i = -1;
        
        // Searching in SearchResultList
        if(searchResultList != null && searchResultList.size() > 0) {
            for (i=0; i<searchResultList.size(); i++) {
                searchResult = (ProductSearchResult) searchResultList.get(i);
                if(searchResult.getProductKey() == productKey) {
                    found = true;
                    break;
                }
            }
        }
        

        if (!found) {
            searchResultList = getProductSessionObject().getSearchResultList();
            
            if(searchResultList != null && searchResultList.size() > 0) {
                for (i=0; i<searchResultList.size(); i++) {
                    searchResult = (ProductSearchResult) searchResultList.get(i);
                    if(searchResult.getProductKey() == productKey) {
                        found = true;
                        break;                    
                    }
                }
            }
        }
        
        if(!found) 
            throw new IllegalArgumentException("The given product Key not found in the session List");
        
        return (ProductSearchResult)searchResultList.get(i);
    }
    
    protected void setSearchResultsToSession(List list){
        getProductSessionObject().setSearchResultList(list);
    }
    
    private void removeSearchResultListFromSession(){
        getProductSessionObject().setSearchResultList(null);
    }
    
    private ProductSearchResult getProductFromList(int productKey, List searchResultList){
        if(searchResultList == null || searchResultList.size() == 0) {
            throw new IllegalStateException("Search Result List of product not found in Session");
        }
        
        ProductSearchResult searchResult;
        int i;
        for (i=0; i<searchResultList.size(); i++) {
            searchResult = (ProductSearchResult) searchResultList.get(i);
            if(searchResult.getProductKey() == productKey)
                break;
        }
        if(i == searchResultList.size())
            throw new IllegalArgumentException("The given product Key not found in the session List");
        return (ProductSearchResult)searchResultList.get(i);
    }
    
    protected void setKeyObjectToSessionForEdit(int productKey){
        List resultList = getProductSessionObject().getAllVersionSearchResults();
        ProductSearchResult productSearchResult = getLocateObjectFromSessionList(productKey);
        cleanSession();
        getProductSessionObject().setAllVersionSearchResults(resultList);
        setProductKeyObject(productSearchResult);
        getProductSessionObject().setMode(ProductSessionObject.EDIT_MODE);
    }
    
    protected void setKeyObjectToSessionForView(int productKey){
        List resultList = getProductSessionObject().getSearchResultList();
        ProductSearchResult productSearchResult = getLocateObjectFromSessionList(productKey);
        cleanSession();
        getProductSessionObject().setSearchResultList(resultList);
        setProductKeyObject(productSearchResult);
        getProductSessionObject().setMode(ProductSessionObject.VIEW_MODE);        
    }

    
    // Methods for handling values in Product Session Object.
    protected void setMode(int mode){
        if(mode != ProductSessionObject.EDIT_MODE && mode != ProductSessionObject.VIEW_MODE)
            throw new IllegalArgumentException("The give mode is not valid");
        getProductSessionObject().setMode(mode);
    }

    private int getModeFromSession(){
        return getProductSessionObject().getMode();
    }
    
    protected boolean isEditMode(){
        if(getModeFromSession() == ProductSessionObject.EDIT_MODE)
            return true;
        return false;
    }
    
    protected boolean isViewMode(){
        if(getModeFromSession() == ProductSessionObject.VIEW_MODE)
            return true;
        return false;        
    }
    
	protected int getProductKeyFromSession(){
	    return getProductKeyObject().getProductId();
	}
	
	protected String getStateFromSession(){
	    return getProductKeyObject().getState();
	}
	
	protected int getVersionFromSession(){
	    return getProductKeyObject().getVersion();
	}
	
	protected String getProductNameFromSession(){
	    return getProductKeyObject().getProductName();
	}
	
	protected String getStatusFromSession(){
	    return getProductKeyObject().getStatus();
	}
	
	protected int getBenefitIdFromSession(){
	    return getProductSessionObject().getBenefitId();
	}
	
	protected void setBenefitIdFromSession(int benefitId){
	    getProductSessionObject().setBenefitId(benefitId);
	}
	
	protected int getBenefitAdminIdFromSession(){
	    return getProductSessionObject().getBenefitAdminId();
	}
	
	protected void setBenefitAdminIdToSession(int benefitAdminId){
	    getProductSessionObject().setBenefitAdminId(benefitAdminId);
	}
	
	protected int getBenefitComponentIdFromSession(){
	    return getProductSessionObject().getBenefitComponentId();
	}
	
	protected void setBenefitComponentIdToSession(int benefitComponentId){
	    getProductSessionObject().setBenefitComponentId(benefitComponentId);
	}
	
	protected String getProductTypeFromSession(){
	    return getProductKeyObject().getProductType();
	}
	
	public void setBreadCumbTextForEdit(){
	    String productName = null;
	    if(getProductKeyObject() != null ) {
	        productName = getProductKeyObject().getProductName();
	        super.setBreadCrumbText("Product Configuration >> Product ("+productName+") >> Edit");
	    } else {
	        super.setBreadCrumbText("Product Configuration >> Product >> Create");
	    }
	}
	
	protected void setBreadCumbTextForView(){
	    String productName = null;
	    if(getProductKeyObject() != null ) {
	        productName = getProductKeyObject().getProductName();
	        super.setBreadCrumbText("Product Configuration >> Product ("+productName+") >> View");
	    } else {
	        super.setBreadCrumbText("Product Configuration >> Product >> View");
	    }
	}

	protected void setBreadCumbTextForSearch(){
	    super.setBreadCrumbText("Product Configuration >> Product >> Maintain");
	}
	protected void setBreadCumbTextForCreate(){
	    super.setBreadCrumbText("Product Configuration >> Product >> Create");
	}
	
	 //Code change for product tree rendering optimization
	public boolean isTreeStructureUpdated() {
		return getProductSessionObject().isTreeStructureUpdated();
	}
	
	public void updateTreeStructure() {
		setTreeStructureUpdated(true);
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		getProductSessionObject().setTreeStructureUpdated(treeStructureUpdated);
	}
    
	
}
