/*
 * ProductStructureBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Base class for all product structure backing beans.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBackingBean extends WPDBackingBean {

    protected static String PRODUCT_STRUCTURE_SESSION_KEY = "productStructure";
    
    public static String PRODUCT_STRUCTURE_SEARCH_SESSION_KEY = "productStructureSearchResult";

    protected static String PRODUCT_STRUCTURE_SEARCH_SESSION_KEY_ALL_VERSIONS = "viewAllVersionsResult";

    protected static String VIEW_FLAG = "view";
    
   
    /**
     * Retrieves the ProductStructureSessionObject from session.
     * 
     * @return ProductStructureSessionObject
     */
    protected ProductStructureSessionObject getProductStructureSessionObject() {
        ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) getSession()
                .getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);

        if (productStructureSessionObject == null) {
            productStructureSessionObject = new ProductStructureSessionObject();
            getSession().setAttribute(PRODUCT_STRUCTURE_SESSION_KEY,
                    productStructureSessionObject);
        }
        return productStructureSessionObject;
    }
    
    protected void setValuesForAdminMEthodValidation(){

		String entityId = getProductStructureSessionObject().getId() + "";
		String entityName = getProductStructureSessionObject().getName();
		String entityType = "ProdStructure";
			
		getSession().setAttribute("AM_ENTITY_ID",entityId);
 		getSession().setAttribute("AM_ENTITY_TYPE",entityType);
 		getSession().setAttribute("AM_ENTITY_NAME",entityName);
    }


    /**
     * Sets Productstructure session object.
     * 
     * @param productStructure
     */
    protected void setProductStructureSessionObject(
            ProductStructureBO productStructure) {

        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        productStructureSessionObject.setId(productStructure
                .getProductStructureId());
        productStructureSessionObject.setBusinessDomains(productStructure
                .getBusinessDomains());
        productStructureSessionObject.setName(productStructure
                .getProductStructureName());
        productStructureSessionObject.setVersion(productStructure.getVersion());
        productStructureSessionObject.setParentId(productStructure
                .getProductStructureParentId());
        productStructureSessionObject.setStatus(productStructure.getStatus());
        if (productStructure.getState() != null) {
            productStructureSessionObject.setState(productStructure.getState()
                    .getState());
        }
        productStructureSessionObject.setStructureType(productStructure
                .getStructureType());
        productStructureSessionObject.setMandateType(productStructure
                .getMandateType());
        productStructureSessionObject.setStateId(productStructure.getStateId());
        
        productStructureSessionObject.setBenefitComponentId(productStructure.getBenifitComponentId());
        
        productStructureSessionObject.setProductFamily(productStructure.getProductFamilyId());
        if (null!=productStructureSessionObject.getBusinessDomains()) {
        Collections.sort(productStructureSessionObject.getBusinessDomains());
        }
    }


    /**
     * Returns action from session.
     * 
     * @return String.
     */
    protected String getActionFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getAction();
    }


    /**
     * Sets action to session.
     * 
     * @param action
     */
    protected void setActionToSession(String action) {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        productStructureSessionObject.setAction(action);
    }


    /**
     * Returns id from session.
     * 
     * @return int.
     */
    protected int getIdFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getId();
    }


    /**
     * Returns name from session.
     * 
     * @return String
     */
    protected String getNameFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getName();

    }


    /**
     * Returns parentid from session.
     * 
     * @return int
     */
    protected int getParentIdFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getParentId();

    }


    /**
     * Returns version from session.
     * 
     * @return int
     */
    protected int getVersionFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getVersion();

    }


    /**
     * Returns status from session.
     * 
     * @return String
     */
    protected String getStatusFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getStatus();

    }


    /**
     * Returns state from session.
     * 
     * @return String
     */
    protected String getStateFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getState();

    }


    /**
     * Returns business domain from session.
     * 
     * @return List
     */
    protected List getBusinessDomainFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getBusinessDomains();

    }


    /**
     * Returns Structure type from session.
     * 
     * @return List
     */
    protected String getStructureTypeFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getStructureType();

    }


    /**
     * Returns status from session.
     * 
     * @return String
     */
    protected String getMandateTypeFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getMandateType();

    }


    /**
     * Returns state from session.
     * 
     * @return String
     */
    protected String getStateIdFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getStateId();

    }


    /**
     * 
     * @param productStructureVO
     * @return ProductStructureVO
     */
    protected ProductStructureVO getProductStructureFromSession(
            ProductStructureVO productStructureVO) {

        productStructureVO.setProductStructureId(getIdFromSession());
        productStructureVO.setProductStructureName(getNameFromSession());
        productStructureVO
                .setParentProductStructureId(getParentIdFromSession());
        productStructureVO.setVersion(getVersionFromSession());
        productStructureVO.setState(getStateFromSession());
        productStructureVO.setStatus(getStatusFromSession());
        productStructureVO.setProductStructureName(getNameFromSession());
        productStructureVO.setBusinessDomains(getBusinessDomainFromSession());
        productStructureVO.setStructureType(getStructureTypeFromSession());
        productStructureVO.setMandateType(getMandateTypeFromSession());
        productStructureVO.setStateId(getStateIdFromSession());
        productStructureVO.setProductFamilyId(getProductStructureSessionObject().getProductFamily());
        return productStructureVO;

    }


    /**
     * To set search results to session.
     * 
     * @param result
     */
    protected void setSearchResultToSession(List result) {
        getSession().setAttribute(PRODUCT_STRUCTURE_SEARCH_SESSION_KEY, result);
    }


    /**
     * To get search results from session.
     * 
     * @return List.
     */
    protected List getSearchResultFromSession() {
    	List searchResult = (List) getSession().getAttribute(
                PRODUCT_STRUCTURE_SEARCH_SESSION_KEY);
        if (null == searchResult) {
            return searchResult;
        }
        return searchResult;

    }


    /**
     * Setting search results for viewallversion functionality.
     * 
     * @param result
     */
    protected void setSearchResultToSessionForViewAllVersions(List result) {
        getSession().setAttribute(
                PRODUCT_STRUCTURE_SEARCH_SESSION_KEY_ALL_VERSIONS, result);
    }


    /**
     * Getting searchresults for viewallversion functionality.
     * 
     * @return List
     */
    protected List getSearchResultFromSessionForViewAllVersions() {
    	List searchResult = (List) getSession().getAttribute(
                PRODUCT_STRUCTURE_SEARCH_SESSION_KEY_ALL_VERSIONS);
        if (null == searchResult) {
            return searchResult;
        }
        return searchResult;

    }


    /**
     * Creating productStructureVo using values from session.
     * 
     * @param id
     * @return ProductStructureVO
     */
    protected ProductStructureVO getproductureKeyValuesFromSession(int id) {
        List searchResult = getSearchResultFromSession();
        if (searchResult == null) {
            return null;
        }
        Iterator iter = searchResult.iterator();
        while (iter.hasNext()) {
            ProductStructureBO productStructure = (ProductStructureBO) iter
                    .next();
            if (productStructure.getProductStructureId() == id) {
                this.setProductStructureSessionObject(productStructure);
                ProductStructureVO productStructureVO = new ProductStructureVO();
                productStructureVO = this
                        .getProductStructureFromSession(productStructureVO);
                return productStructureVO;
            }
        }
        return null;
    }


    /**
     * 
     * @param id.
     * @return ProductStructureVO
     */
    protected ProductStructureVO getproductureKeyValuesFromSessionForViewAllVersions(
            int id) {

        List searchResult = getSearchResultFromSessionForViewAllVersions();
        if (searchResult == null) {
            return null;
        }
        Iterator iter = searchResult.iterator();
        while (iter.hasNext()) {
            ProductStructureBO productStructure = (ProductStructureBO) iter
                    .next();
            if (productStructure.getProductStructureId() == id) {
                ProductStructureVO productStructureVO = new ProductStructureVO();
                this.setProductStructureSessionObject(productStructure);
                productStructureVO = this
                        .getProductStructureFromSession(productStructureVO);
                return productStructureVO;
            }
        }
        return null;
    }


    /**
     * Setting viewflag to session.
     * 
     * @param value
     */
    protected void setViewFlag(String value) {
        getSession().setAttribute(VIEW_FLAG, value);
    }


    /**
     * Checking whether view flag is in session.
     * 
     * @return String
     */
    protected String getViewFlag() {
        if (getSession().getAttribute(VIEW_FLAG) == null) {
            return "false";
        } else {
            return "true";
        }
    }


    /**
     * Setting breadcrumb for edit.
     *  
     */
    public void setBreadCrumbTextForEdit() {
        this.setBreadCrumbText("Product Configuration >> Product Structure ("
                + this.getNameFromSession() + ") >> Edit");
    }


    /**
     * Setting breadcrumb for view.
     *  
     */
    protected void setBreadCrumbTextForView() {
        this.setBreadCrumbText("Product Configuration >> Product Structure ("
                + this.getNameFromSession() + ") >> View");
    }


    /**
     * Setting breadcrumb for copy.
     *  
     */
    protected void setBreadCrumbTextForCopy() {
        this.setBreadCrumbText("Product Configuration >> Product Structure ("
                + this.getNameFromSession() + ") >> Copy");
    }


    /**
     * Setting bread crumb.
     */
    protected void setBreadCrumbTextForPS() {
        if (getActionFromSession() != null
                && getActionFromSession().equals("VIEW")) {
            this.setBreadCrumbTextForView();
        } else {
            if (this.getNameFromSession() == null) {
                this.setBreadCrumbText("Product Configuration >> Product"
                        + " Structure >> Create");
            } else {
                this.setBreadCrumbTextForEdit();
            }
        }

    }
    /**
     * Returns BenefitComponentId from session.
     * 
     * @return String.
     */
    protected int getBenefitComponentIdFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getBenefitComponentId();
    }


    /**
     * Sets BenefitComponentId to session.
     * 
     * @param benefitComponentId
     */
    protected void setBenefitComponentIdToSession(int benefitComponentId) {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        productStructureSessionObject.setBenefitComponentId(benefitComponentId);
    }
    /**
     * Returns benefitId from session.
     * 
     * @return String.
     */
    protected int getBenefitIdFromSession() {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        return productStructureSessionObject.getBenefitId();
    }


    /**
     * Sets benefitId to session.
     * 
     * @param benefitId
     */
    protected void setBenefitToSession(int benefitId) {
        ProductStructureSessionObject productStructureSessionObject = getProductStructureSessionObject();
        productStructureSessionObject.setBenefitId(benefitId);
    }
    //Code change for product structure tree rendering optimization
	public boolean isTreeStructureUpdated() {
		return getProductStructureSessionObject().isTreeStructureUpdated();
	}
	
	public void updateTreeStructure() {
		setTreeStructureUpdated(true);
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		getProductStructureSessionObject().setTreeStructureUpdated(treeStructureUpdated);
	}
}