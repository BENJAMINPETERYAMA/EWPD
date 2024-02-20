/*
 * SessionCleanUp.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.util;

import com.wellpoint.wpd.web.util.WebConstants;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import javax.servlet.http.HttpSession;


/**
 * Class for cleaning the session
 */
public class SessionCleanUp {

    /**
     * Method for cleaning the session
     *  
     */
    public static void cleanUp() {
        removeManagedBean(WebConstants.QUESTION_MANAGED_BEAN);
        removeValuesInSessionForQuestion();
        removeValueInSessionForProductStructure();
        removeValueInSessionForStandardBenefit();
        removeValueInSessionForBenefitComponent();
        removeValueInSessionForAdministration();
        removeValuesInSessionForStandardBenefitTree();
        removeValuesinSessionForbenefitComponentTree();
        removeValuesInSessionForContract();  
        removeValuesInSessionForAdminMethod();  
        removeValuesInsessionForMigrationTree();
        removeValuesInsessionForMigrationMessage3();
        removeValuesInSessionForProduct();
        removeValuesForServTypeMapping();
        removeValuesForSecurityRole();
        removeValuesForSecurityTask();
        removeValuesForAdminOption();
        removeValuesForAdminMethod();
        removeManagedBean("testCaseBackingBean");
        removeObjectFromSessionForWebServiceAndCarvedout();
    }
    /**
	 * 
	 */
    private static void removeValuesForAdminOption(){
        removeValueInSession("rootQuestAdminId");
        removeValueInSession("rootQuestAdminName");
        removeValueInSession("rootQuestAdminVersion");
        removeValueInSession("AdminId");
        removeValueInSession("AdminName");
        removeValueInSession("AdminVersion");
    }
    
    /**
	 * 
	 */
    private static void removeValuesForAdminMethod(){
        removeValueInSession("AM_BENEFIT");
        removeValueInSession("AM_BC_KEY");
        removeValueInSession("AM_ENTITY_KEY");
        removeValueInSession("DIRECT_CLICK");
        removeValueInSession("SESSION_BNFT_ID");
    }
    /**
	 * 
	 */
	private static void removeValuesForSecurityRole() {
		removeValueInSession("SESSION_ACTION");
		
	}

	/**
	 * 
	 */
	private static void removeValuesForSecurityTask() {
		removeValueInSession("TASK_TYPE");
		removeValueInSession("TASK_ID");
	}

	private static void removeValuesForServTypeMapping() {
    	removeValueInSession(WebConstants.SRV_TYP_MAPPING_SESSION_KEY);
    }
    private static void removeValuesInsessionForMigrationTree(){
        removeValueInSession("SESSION_TREE_STATE_MW");
    }
    private static void removeValuesInsessionForMigrationMessage3(){
        removeValueInSession("MESSAGELIST_STEP3");
    }
    
    /**
	 * 
	 */
	private static void removeValueInSessionForAdministration() {
		 removeValueInSession("CATALOG_ID");
		 removeValueInSession("PRMRY_CODE");
		 removeValueInSession("SUB_CTLG_ID");
		 removeValueInSession("SUB_CATALOG_NAME");
		 removeValueInSession("actionView");
		 removeValueInSession("SESSION_ROLE_SYS_ID");
		 removeValueInSession("moduleIdFromTree");
		 removeValueInSession("taskIdFromTree");
		 removeValueInSession("SESSION_MOD_SYS_ID");
		 removeValueInSession("SESSION_MOD_NAME");
		 removeValueInSession("taskIdFromTree");
		 removeValueInSession("SESSION_ROLE_NAME");
		 removeValueInSession("SESSION_TREE_STATE_ROLE");
		 removeValueInSession("SESSION_TREE_STATE_MODULE");
	}

    /**
     * 
     *
     */
    private static void removeValuesInSessionForContract() {
        removeValueInSession("contractSearch");
        removeValueInSession("SESSION_TREE_STATE_CONTRACT_SEARCH");
        removeValueInSession("contract");
        removeValueInSession("SESSION_TREE_STATE_CONTRACT");
        removeValueInSession("CHECKOUT_DATESEGMENT_INDICATOR");
        removeValueInSession("CHECKOUT_DATESEGMENT_ID");
        removeValueInSession("SESSION_BNFT_DEFN_ID_CONTRACT");
    }
/**
     *  
     */
    private static void removeValuesInSessionForQuestion() {
        removeValueInSession("possibleAnswerList");
    }
    
    /**
     *  
     */
    private static void removeValueInSessionForProductStructure() {
        removeValueInSession("productStructure");
        removeValueInSession("productStructureSearchResult");
        removeValueInSession("productStructureTreeBackingBean");
        
    }

    /**
     *  
     */
    private static void removeValueInSessionForStandardBenefit() {
        removeValueInSession("standard");
        removeValueInSession("standardBenefitSearchResult");
        removeValueInSession("standardBenefitViewAllVersionsSearchResult");
        
    }   
    
    /**
     *  
     */
    private static void removeValueInSessionForBenefitComponent() {
        removeValueInSession("benefitComponent");
        removeValueInSession("benefitComponentSearchResult");
    } 
    /**
     *  
     */
    private static void removeValuesInSessionForStandardBenefitTree(){
    	removeValueInSession("SESSION_TREE_STATE_SB");
    	removeValueInSession("SESSION_NODE_TYPE");
    	removeValueInSession("SESSION_BNFT_DEFN_ID");
    	removeValueInSession("SESSION_BNFT_DEFN_DESC");
    	removeValueInSession("SESSION_BNFT_ADMIN_ID");
    	removeValueInSession("SESSION_BNFT_ADMIN_DESC");
    	removeValueInSession("SESSION_ADMIN_OPTN_ID");
    }
    /**
     *  
     */
    private static void removeValuesinSessionForbenefitComponentTree(){
    	removeValueInSession("SESSION_TREE_STATE_BC");
    	removeValueInSession("SESSION_NODE_TYPE_COMP");
    	removeValueInSession("SESSION_BNFT_ID");
    	removeValueInSession("SESSION_BNFT_NM");
    	removeValueInSession("SESSION_ADMIN_ID");
    	removeValueInSession("SESSION_ADMINISTRATION_ID");
    }
    /**
     *  
     */
    private static void removeValueInSession(String attribute) {
        if (null != getSession().getAttribute(attribute))
            getSession().removeAttribute(attribute);
    }
    
    /**
     * Method for removing a managed bean from the session
     */
    public static void removeManagedBean(String beanName) {
        ValueBinding valueBinding = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding("#{" + beanName + "}");
        if (valueBinding != null) {
            valueBinding.setValue(FacesContext.getCurrentInstance(), null);
        }
    }

    private static HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return session;
    }
    /**
     *  
     */
    private static void removeValuesInSessionForAdminMethod(){
    	removeValueInSession("SESSION_BNFT_ADMIN_DESC");
    	removeValueInSession("ADMIN_KEY");
    	removeValueInSession("BNFT_CMPNT_KEY");
    	removeValueInSession("AM_BENEFIT");
    	removeValueInSession("AM_BC_KEY");
    }
    /**
     *  
     */
    private static void removeValuesInSessionForProduct(){
    	if(null != getSession().getAttribute(WebConstants.PROD_TYPE)){
    		getSession().removeAttribute(WebConstants.PROD_TYPE);
    	}
		if(null != getSession().getAttribute("PRODUCT_NODE_TYPE")){
			getSession().removeAttribute("PRODUCT_NODE_TYPE");
		}
		if(null != getSession().getAttribute("PRODUCT_SESSION_TREE_STATE")){
    		getSession().removeAttribute("PRODUCT_SESSION_TREE_STATE");
    	}
		if(null!= getSession().getAttribute("productTreeBackingBean")){
			getSession().removeAttribute("productTreeBackingBean");
		}
		if(null!= getSession().getAttribute("SESSION_BNFTDEFNID_PRODUCT")){
			getSession().removeAttribute("SESSION_BNFTDEFNID_PRODUCT");
		}
    }
    
    private static void removeObjectFromSessionForWebServiceAndCarvedout(){
    	getSession().removeAttribute("contractWSErrorList");
    	getSession().removeAttribute("wSErrorDisplayList");
    	getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
    	getSession().removeAttribute("VendorFlag");
    	getSession().removeAttribute("WebServiceFlag");
    	getSession().removeAttribute("ebxProcessInterruptMsg");
    }

}