/*
 * ProductAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.adapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductLocateCriteria;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.product.bo.BenefitAdminOverridenValue;
import com.wellpoint.wpd.common.product.bo.BenefitDefnValueOverride;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;
import com.wellpoint.wpd.common.product.bo.ProductAssociatedBenefit;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductCheckBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentRule;
import com.wellpoint.wpd.common.product.bo.ProductEntityBenefitAdmin;
import com.wellpoint.wpd.common.product.bo.ProductQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;
import com.wellpoint.wpd.common.product.bo.ProductQuestionnaireAssnBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociationBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleIdBO;
import com.wellpoint.wpd.common.product.bo.ProductSearchResult;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.bo.ProductTierQuestionareBO;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.bo.SaveProductQuestionareBO;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductAdapterManager {

	
	
	/**
	 * Returns the product rule parameter codes, types and description
	 * @return
	 * @throws SevereException
	 */
	public List retreiveProductRuleCodes()throws SevereException{
		HashMap map = new HashMap();
	    SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(ProductRuleBO.class.getName(),WebConstants.RETRIEVE_PRODUCT_RULE_CODES,map);
	    SearchResults searchResults=AdapterUtil.performSearch(criteria);
		return searchResults.getSearchResults();
	}
	
	public List retrieveProductName(HashMap map) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ProductBO.class.getName(),WebConstants.RETRIEVE_PRODUCT_NAME,map);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
		
	}
    /**
     * retrieves the Tier definitions and criterias corresponding to the benefit
     * 
     * @param productId
     * @param benefitComponentId
     * @param benefitId
     * @throws SevereException
     */
	public List getTierCriteriaForProduct(int productId, int benefitComponentId, int benefitId) throws SevereException{
		HashMap map = new HashMap();
		map.put("prodSysId", new Integer(productId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
				"getTierCriteriaForProduct", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	 /**
     * Retrieves the Tier definitions and criterias corresponding to the General Benefits
     * 
     * @param productId
     * @param benefitComponentId
     * @throws SevereException
     */
	public List getTierCriteriaForGeneralBenefitsInProduct(int productId) throws SevereException{
		HashMap map = new HashMap();
		map.put(BusinessConstants.PRODSYSID, new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
				BusinessConstants.GETTIERCRITERIA_FOR_GENERALBENEFITSIN_PRODUCT, map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	
	public void attachAdminOption(ProductAdminBO productAdminBO, AdapterServicesAccess access) throws SevereException {
	    HashMap map = new HashMap();
	    map.put("productKey", new Integer(productAdminBO.getProductKey()));
	    map.put("adminKey", new Integer (productAdminBO.getAdminKey()));
	    map.put("lastUpdatedUser", productAdminBO.getLastUpdatedUser());
	    map.put("reference", WebConstants.ADJUD_REFERNCE_ID);
	    map.put("questionDesc", WebConstants.ADJUD_QSTN_DESC);
	    SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(ProductAdminBO.class.getName(),"attachAdminOption",map);
	    AdapterUtil.performSearch(criteria, access);
	}
	
	public void refreshQuestionnaire(ProductBO product, AdapterServicesAccess access) throws SevereException {
	    HashMap map = new HashMap();
	    map.put("productKey", new Integer(product.getProductKey()));
	    map.put("lastUpdatedUser", product.getLastUpdatedUser());
	    SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(ProductAdminBO.class.getName(),"refreshQuestionnaire",map);
	    AdapterUtil.performSearch(criteria, access);
	    
	}
	
	/**
	 * Calls stored procedure "PRD_ADD_TIER" 
	 * @param productSysId
	 * @param benefitComponentSysId
	 * @param benefitSysId
	 * @param tierDefinitionId
	 * @param levelid
	 * @param criteriaString
	 * @param isExistingTier
	 * @param lastUser
	 * @param lastTMS
	 * @return
	 * @throws AdapterException
	 * @throws SevereException
	 */
	
	public boolean addTierToProduct(int productSysId, int benefitComponentSysId, 
			int benefitSysId, int tierDefinitionId, int levelid, String criteriaString, 
			String isExistingTier, Audit audit)throws AdapterException,SevereException {
		
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();
        
        refValSet.put("prodSysId",new Integer(productSysId));
        refValSet.put("benefitComponentId",new Integer(benefitComponentSysId));
        refValSet.put("benefitSysId",new Integer(benefitSysId));
        refValSet.put("tierDefId",new Integer(tierDefinitionId));
        refValSet.put("levelSysId",new Integer(levelid));
        refValSet.put("criteriaString",criteriaString);
        refValSet.put("isExistingTier",isExistingTier);
        refValSet.put("lastUser",audit.getUser());
        refValSet.put("lastTMS",audit.getTime());
        
 
        try{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), "addTierToProduct", refValSet);
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        	if(searchResults !=null){
        		return true;   
        	}
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        
        return false;   
	}
	
	
	/**
	 * 
	 * @param productSysId
	 * @param benCompId
	 * @param defnId
	 * @return TierDefinitionBO List
	 * @throws SevereException
	 */
	public List getTierDefinition(int productSysId, int benCompId, int defnId)throws SevereException {
		 HashMap map = new HashMap();
		    map.put(BusinessConstants.PRODSYSID, new Integer(productSysId));
		    map.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(benCompId));
		    map.put(BusinessConstants.BENDEFID, new Integer(defnId));
		    SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),BusinessConstants.GET_TIER_DEFINITION,map);
		    SearchResults searchResults = AdapterUtil.performSearch(criteria);
		    return searchResults.getSearchResults();
	}
	
	/**
     * retrieves the product Benefit details.
     * 
     * @param benefitDetails
     * @throws SevereException
     */
	public LocateResults retrieveProductAssociatedBenefitDetails(ProductBenefitLocateCriteria productBenefitLocateCriteria) throws SevereException {
	        List benefitDetailsList = null;
	        LocateResults locateResults = new LocateResults();
	        HashMap referenceValueSet = new HashMap();
	        if (null != productBenefitLocateCriteria) {
	            int benefitComponentId = productBenefitLocateCriteria.getBenefitComponentId();
	            int productId = productBenefitLocateCriteria.getProductId();
	            if (0 != benefitComponentId) {
	            	// Setting the search criteria for the query.
	                referenceValueSet.put("benefitComponentId",new Integer(benefitComponentId));
	                referenceValueSet.put("productId",new Integer(productId));
	            }
	        }
	        SearchCriteria searchCriteria = null;
	        // Checking if the Status Hidden is Checked inorder to retrieve entire records.
	        if(productBenefitLocateCriteria.isBenefitVisibilityStatus()){
	        	
	        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        		ProductTreeStandardBenefits.class.getName(),
		                WebConstants.SEARCH_ALL_STANDARDBENEFITS, referenceValueSet);
	        	
	        }
	        else{
	        // Retrieving the records with benefit visibility as true for the time of Load
	        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	        		ProductTreeStandardBenefits.class.getName(),
	                WebConstants.SEARCH_VISIBLE_STANDARDBENEFITS, referenceValueSet);
	        }
	        if(searchCriteria!= null){
	        	locateResults.setLocateResults(AdapterUtil.performSearch(searchCriteria).getSearchResults());
	        }
	        return locateResults;

	    }
	/*
	 * retrieving valid benefit which is visible in product
	 * 
	 * @product id,
	 * @benefitcomponent id
	 * 
	 * return benefit List
	 *  
	 */
	public List getValidBenefit(int productId,int benefitComponentId)throws SevereException{
		List benefitVisibleList = null;
		HashMap referenceValueSet = new HashMap();
		SearchCriteria searchCriteria = null;
		referenceValueSet.put("benefitComponentId",new Integer(benefitComponentId));
        referenceValueSet.put("productId",new Integer(productId));
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductTreeStandardBenefits.class.getName(),
                WebConstants.SEARCH_VISIBLE_STANDARDBENEFITS, referenceValueSet);
        if(searchCriteria!= null){
        	benefitVisibleList = AdapterUtil.performSearch(searchCriteria)
	                .getSearchResults();
	        }
		return benefitVisibleList;
		
	}
	/*
	 * 
	 * @input product id,benefit component id, benefit id 
	 * 
	 * return newbenLvlList contain duplicate reference 
	 * 
	 */
	public List isReferenceValid(int productId,int benefitComponentId,int benefitId)throws SevereException
	{
		List benefitVisibleList = null;
		List newbenLvlList=new ArrayList();//FIXME Move to the place where elemnt adding.
		HashMap referenceValueSet = new HashMap();
		SearchCriteria searchCriteria = null;
		ProductQuestUniqueReferenceBO productQuestUniqueReferenceBO = null;
		ProductQuestUniqueReferenceBO productQuestUniqueReferenceCompareBO = null;
		referenceValueSet.put("benefitComponentId",new Integer(benefitComponentId));
        referenceValueSet.put("entitySysId",new Integer(productId));
        referenceValueSet.put("benefitSysId",new Integer(benefitId));
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductQuestUniqueReferenceBO.class.getName(),
                WebConstants.GET_DUPLICATE_REFERENCE, referenceValueSet); 	
        if(searchCriteria!= null){
        	benefitVisibleList = AdapterUtil.performSearch(searchCriteria)
	                .getSearchResults();
        	if(null != benefitVisibleList && benefitVisibleList.size()>0)
        	{
        		int benefitVisibleListSize = benefitVisibleList.size();
        		for(int i=0;i<2;i++){
        			productQuestUniqueReferenceBO =(ProductQuestUniqueReferenceBO)benefitVisibleList.get(i);
        		    newbenLvlList.add(i,productQuestUniqueReferenceBO);
        		  		}
        	}
        	        		
        	}
     return newbenLvlList;
		
	}
	 /*
     * reference Unique validation for Question 
     * 
     * getQuestionReference
     * 
     * @ inputs   productId,benefitComponentId,benefitId
     * 
     * returns refernceVisibleList
     */
    
    public List isQuestReferenceValid(int productId,int benefitComponentId,int benefitId)throws SevereException
	{
    	List refernceVisibleList = null;
		HashMap referenceValueSet = new HashMap();
		SearchCriteria searchCriteria = null;
		int benefitCount=0;
		referenceValueSet.put("benefitComponentId",new Integer(benefitComponentId));
        referenceValueSet.put("entitySysId",new Integer(productId));
        referenceValueSet.put("benefitSysId",new Integer(benefitId));
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductQuestUniqueReferenceBO.class.getName(),
                WebConstants.GET_QUESTION_REFERENCE, referenceValueSet);
       
        
        if(searchCriteria!= null){
        	refernceVisibleList = AdapterUtil.performSearch(searchCriteria)
	                .getSearchResults();
        	
        }
      return refernceVisibleList;
		
		
	}
    /*
     * product values to find modified benefit component and benefit aftere check out
     * 
     * @ product Sys Id 
     * 
     *  return List contains benefit component and benefits 
     * 
     * 
     */
    
    public List getProductValues(int productId)throws SevereException{
    
    	List productList =null;
    	
    	HashMap referenceValueSet = new HashMap();
		SearchCriteria searchCriteria = null;
		referenceValueSet.put("entitySysId",new Integer(productId));
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductCheckBO.class.getName(),
                WebConstants.GET_PRODUCT_VALUES, referenceValueSet);
       
        
        if(searchCriteria!= null){
        	productList = AdapterUtil.performSearch(searchCriteria)
	                .getSearchResults();
        	
        }
    	
    	return productList;
    	
    }
    
    /*
     * product values to find modified benefit component and benefit aftere check out
     * 
     * @ product Sys Id 
     * 
     *  return List contains benefit component and benefits 
     * 
     * 
     */
    
    public List getBenefitComponentValues(int productId,int bcId)throws SevereException{
    
    	List productList =null;
    	
    	HashMap referenceValueSet = new HashMap();
		SearchCriteria searchCriteria = null;
		referenceValueSet.put("entitySysId",new Integer(productId));
		referenceValueSet.put("benefitComponentId",new Integer(bcId));
        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductCheckBO.class.getName(),
                WebConstants.GET_BENEFITCOMPONENT_VALUES, referenceValueSet);	
       
        
        if(searchCriteria!= null){
        	productList = AdapterUtil.performSearch(searchCriteria)
	                .getSearchResults();
        	
        }
    	
    	return productList;
    	
    }
    
    
	/**
     * updates the product associated benefits with hidden as true or false 
     * 
     * @param productTreeStandardBenefits
     * @throws SevereException
     */
	public void updateAllBenefitDetails(ProductTreeStandardBenefits productTreeStandardBenefits,AdapterServicesAccess adapterServicesAccess, String userId)  throws AdapterException{
			int benefitComponentId = 0;
			int productId = 0;
			List benefitDetailsUpdatedList = null;
	        HashMap referenceValueSet = new HashMap(); //FIXME Move to the place where elemnts adding.
	        
	        if (null != productTreeStandardBenefits) {
	        	 benefitComponentId = productTreeStandardBenefits.getBenefitComponentId();
	          
	            productId = productTreeStandardBenefits.getProductId();
	            
	            if (0 != benefitComponentId) {
	            	// Setting the where condition for search
	                referenceValueSet.put("benefitComponentId", new Integer(benefitComponentId));
	                referenceValueSet.put("productId", new Integer(productId));
	            }
	        }
	        // For updating all the benefit details  //FIXME Can put this inside the above if. Null	check for the list benefitDetailsUpdatedList.
	        benefitDetailsUpdatedList = productTreeStandardBenefits.getBenefitDetailsList();
	        if (null != benefitDetailsUpdatedList && benefitDetailsUpdatedList.size() > 0){
	        	ProductTreeStandardBenefits details = new ProductTreeStandardBenefits();
	        for(int i= 0 ;i<benefitDetailsUpdatedList.size();i++){
	        	details = (ProductTreeStandardBenefits)benefitDetailsUpdatedList.get(i);
    			details.setProductId(productTreeStandardBenefits.getProductId());
    			details.setBenefitComponentId(productTreeStandardBenefits.getBenefitComponentId());
	        	ProductTreeStandardBenefits productTreeStandardBenefitsForUpdates = null;//FIXME Can remove this initialization.
	        	productTreeStandardBenefitsForUpdates = (ProductTreeStandardBenefits)benefitDetailsUpdatedList.get(i);
	        	productTreeStandardBenefitsForUpdates.setBenefitComponentId(benefitComponentId);
	        	productTreeStandardBenefitsForUpdates.setProductId(productId);
	        	productTreeStandardBenefitsForUpdates.setProductType(productTreeStandardBenefits.getProductType());
	        	if(productTreeStandardBenefitsForUpdates.isBenefitVisibilityStatus()){
	        		productTreeStandardBenefitsForUpdates.setBenefitHideFlag(WebConstants.CONST_T);
	        		productTreeStandardBenefitsForUpdates.setBenefitLevelFlag(WebConstants.CONST_T);
	        		productTreeStandardBenefitsForUpdates.setBenefitLineFlag(WebConstants.CONST_T);
	        		productTreeStandardBenefitsForUpdates.setAdminOptionFlag(WebConstants.CONST_T);
	        		productTreeStandardBenefitsForUpdates.setQuestionsFlag(WebConstants.CONST_T);
		        }
	        	else{
	        		productTreeStandardBenefitsForUpdates.setBenefitHideFlag(WebConstants.CONST_F);
	        		productTreeStandardBenefitsForUpdates.setBenefitLevelFlag(WebConstants.CONST_F);
	        		productTreeStandardBenefitsForUpdates.setBenefitLineFlag(WebConstants.CONST_F);	
	        		productTreeStandardBenefitsForUpdates.setAdminOptionFlag(WebConstants.CONST_F);
	        		productTreeStandardBenefitsForUpdates.setQuestionsFlag(WebConstants.CONST_F);
	        	}
	        	
			    try{
			    	/**Implemented using Map for updation:: eWPD Stabilization 2011 **/
					String keyvalue=details.getStandardBenefitId()+"||"+productTreeStandardBenefits.getProductId();
					Object flagvalue=productTreeStandardBenefits.getBenefitComponentHideMap().get(keyvalue);
			    	if (!(flagvalue.equals(details.getBenefitHideFlag()))) {
			    		/** end :: eWPD Stabilization 2011 **/
			    	AdapterUtil.performUpdate(productTreeStandardBenefitsForUpdates,WebConstants.USER,adapterServicesAccess);
			    	
			    	// Remove question customization while hiding benefit
			    	
			    	if(productTreeStandardBenefitsForUpdates.isBenefitVisibilityStatus()) {
				    	HashMap map = new HashMap();
				    	map.put("productId",new Integer(productTreeStandardBenefits.getProductId()));
				    	map.put("benefitComponentId",new Integer(productTreeStandardBenefits.getBenefitComponentId()));
				    	map.put("benefitId",new Integer(productTreeStandardBenefitsForUpdates.getStandardBenefitId()));
				    	map.put("userId",userId);
				    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ProductTreeStandardBenefits.class.getName(),"removeBenefitCustomization", map);
				    	AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);
			    	}
			    	}
			    }catch(Exception e){
			    	throw new AdapterException("Exception occured while adapter call",e);
			    }
	        
	         }
	        }
	    }
		
	
    /**
     * creates the product benfit component
     * 
     * @param componentBO
     * @throws SevereException
     */
    public void createProductBenefitComponent(ProductComponentBO componentBO)
            throws SevereException {
        AdapterUtil
                .performInsert(componentBO, componentBO.getLastUpdatedUser());
    }
    /*
     * @param ProductComponentBO
     * @throws SevereException
     * methode for calling procedure to attach benefit 
     * component to product
     * 
     */
    public boolean addBenefitComponentProc(
    		ProductComponentBO componentBO,Audit audit, AdapterServicesAccess adapterServicesAccess) throws  AdapterException {
		Logger.logInfo("Entering the procedure for attaching Benefit Component to the Product Structure");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        // setting product key in to the map -input parameter 
        refValSet.put("productKey", new Integer(componentBO.getProductKey()));
        // setting bc id in to the map -input parameter
        refValSet.put("componentKey", new Integer(componentBO.getComponentKey()));
        refValSet.put("createdUser", audit.getUser());
        // here calling a procedure PRD_AttachComponent to  attach bc to product 
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
              BusinessConstants.PRODUCT_BEN_COMP_ASSC_BO, WebConstants.ATTACH_BENEFITCOMP_TO_PRODUCTPS,	
              refValSet);
        try{
        	
        searchResults = AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);
 		Logger.logInfo("Returning the procedure for attaching Benefit Component to the Product");
 		
        }catch(Exception e){
        	
        	throw new AdapterException("Exception occured while attaching benefit component(s) to product ",e);
        }
		return true;
	}

    /**
     * updates the product benfit component
     * 
     * @param componentBO
     * @throws SevereException
     */
    public void updateProductBenefitComponent(ProductComponentBO componentBO, AdapterServicesAccess adapterServicesAccess)
            throws AdapterException {
    	try{
    	AdapterUtil
                .performUpdate(componentBO, componentBO.getLastUpdatedUser(),adapterServicesAccess);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
    }


    /**
     * This method creates the product
     * 
     * @param product
     * @throws SevereException
     */
    public void createProduct(ProductBO product) throws AdapterException {
    	try{
    	AdapterUtil.performInsert(product, product.getLastUpdatedUser());
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
    }


    /**
     * this method updates the product
     * 
     * @param product
     * @throws SevereException
     */
    public void updateProduct(ProductBO product) throws SevereException {
        AdapterUtil.performUpdate(product, product.getLastUpdatedUser());
    }


    public void createProduct(ProductBO product, AdapterServicesAccess access)
            throws SevereException {
        AdapterUtil
                .performInsert(product, product.getLastUpdatedUser(), access);
    }


    public void updateProduct(ProductBO product, AdapterServicesAccess access)
            throws AdapterException {
    	try{
        AdapterUtil
                .performUpdate(product, product.getLastUpdatedUser(), access);
    	}catch(Exception e){
    		throw new AdapterException("Exception occured while adapter call",e);
    	}
    }


    public void removeProduct(ProductBO product) throws SevereException {
        AdapterUtil.performRemove(product, product.getLastUpdatedUser());
    }
    
	
    /**
     * retireives the product
     * 
     * @param product
     * @return
     * @throws SevereException
     */
    public List retrieveAdminOptionQuest(ProductQuestionnaireAssnBO productQuestionnaireAssnBO) throws SevereException {
    	
    	 HashMap referenceValueSet = new HashMap();
         List locateResults;
         SearchCriteria adapterSearchCriteria = null;
         referenceValueSet.put("adminOptionSysId", new Integer(productQuestionnaireAssnBO.getAdminOptionSysId()));
         adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
         		ProductQuestionnaireAssnBO.class.getName(), "getRootQuestions", referenceValueSet);

         SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

         locateResults = searchResults.getSearchResults();
         return locateResults;
         
         
       

    }
    
    
    /**
     * retireives the product
     * 
     * @param product
     * @return
     * @throws SevereException
     */
    public ProductBO retrieve(ProductBO product) throws SevereException {
        return (ProductBO) AdapterUtil.performRetrieve(product);

    }


    /*public ProductBO copyAssociatedComponents(int sourceProductId,
            int targetProductId, String userId) throws SevereException,AdapterException{
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productKey", new Integer(targetProductId));
        criteriaMap.put("sourceProdId", new Integer(sourceProductId));
        criteriaMap.put("lastUpdatedUser", userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.COPY_ASSOSIATIONS, criteriaMap);
       
        SearchResults searchResults = null;//FIXME Not needed
       
	    searchResults = AdapterUtil.performSearch(searchCriteria);
	        
       
        ProductBO targetProduct = (ProductBO) searchResults.getSearchResults()//FIXME Nullcheck
                .get(0);
        return targetProduct;
    }*/

    // Change made for Transactions
    /*
     * 
     * copy product 
     */
    
    /*public ProductBO copyAssociatedComponents(int sourceProductId,
            int targetProductId, String userId,String copyAction) throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productKey", new Integer(targetProductId));
        criteriaMap.put("sourceProdId", new Integer(sourceProductId));
        criteriaMap.put("lastUpdatedUser", userId);
        criteriaMap.put("copyAction", copyAction);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.COPY_ASSOSIATIONS, criteriaMap); 
        SearchResults searchResults = null;          //FIXME Not needed
           searchResults = AdapterUtil.performSearch(searchCriteria);	       
        
        if(searchResults.getSearchResultCount()>0){         //FIXME Null check
        ProductBO targetProduct = (ProductBO) searchResults.getSearchResults()
                .get(0);
        return targetProduct;
        }
        else{
        	return null;
        
        }
    }*/
    
    public ProductBO copyAssociatedComponents(int sourceProductId,
            int targetProductId, String userId,String copyAction, AdapterServicesAccess adapterServicesAccess) throws SevereException, AdapterException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productKey", new Integer(targetProductId));
        criteriaMap.put("sourceProdId", new Integer(sourceProductId));
        criteriaMap.put("lastUpdatedUser", userId);
        criteriaMap.put("copyAction", copyAction);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.COPY_ASSOSIATIONS, criteriaMap); 
        SearchResults searchResults = null;          //FIXME Not needed
           searchResults = AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);	       
        
        if(searchResults.getSearchResultCount()>0){         //FIXME Null check
        ProductBO targetProduct = (ProductBO) searchResults.getSearchResults() 
                .get(0);
        return targetProduct;
        }
        else{
        	return null;
        
        }
    }
    // End
    
    /**
     * this method gets the benefit component list
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getBenefitComponentsList(int productKey,boolean flag) throws SevereException {
        List benefitComponentList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productKey", new Integer(productKey));
        SearchCriteria adapterSearchCriteria = null; //FIXME Not needed
        if(flag){
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
            		ProductAssociatedBenefit.class.getName(), WebConstants.GET_BENEFIT_HIERARCHY,
                    referenceValueSet);
        }else{
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ProductComponentBO.class.getName(), WebConstants.GET_BENEFITCOMPONENTS,
                    referenceValueSet);
        }
        benefitComponentList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return benefitComponentList;
    }
    /*
     * Methode calls validation_product procedure 
     * 
     * return duplicate reference in 
     */
    public List getDuplcateAdminRef(int productKey)throws SevereException{
    	List duplicateAdminRef =null;
    	HashMap referenceValueSet = new HashMap();
    	referenceValueSet.put("entitySysId", new Integer(productKey));
    	SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			ProductQuestUniqueReferenceBO.class.getName(), WebConstants.GET_DUPLICATE_BENEFIT_LINE_REF,
                referenceValueSet);
    	duplicateAdminRef=AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
    	return duplicateAdminRef; 
    }
/*
 * Methode for finding the product having adminoption with duplicate reference in questions
 *  @ input productKey
 * 
 * returns a list contains duplicate Reference in product benefit Line and questions.
 * 
 */
    public List getDuplcateAdminQuestRef(int productKey)throws SevereException{
    	List duplicateAdminRef =null;
    	HashMap referenceValueSet = new HashMap();
    	referenceValueSet.put("entitySysId", new Integer(productKey));
    	SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			ProductQuestUniqueReferenceBO.class.getName(), WebConstants.GET_DUPLICATE_ADMIN_QUEST_REF,
                referenceValueSet);
    	duplicateAdminRef=AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
    	return duplicateAdminRef; 
    }
    /*
     *
	 * Methode for finding selecting all lines having duplicate reference
	 *  @ input productKey
	 *  getDuplicateAllBenefitRef
	 */
	    public List  getDuplicateAllBenefitRef(int productKey)throws SevereException{
	    	List duplicateAllBenefitRef =null;
	    	HashMap referenceValueSet = new HashMap();
	    	referenceValueSet.put("entitySysId", new Integer(productKey));
	    	SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
	    			ProductQuestUniqueReferenceBO.class.getName(), WebConstants.GET_DUPLICATE_ALL_BENEFIT_REF,
	                referenceValueSet);
	    	duplicateAllBenefitRef=AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
	    	return duplicateAllBenefitRef; 
	    }
	    /*
	     *
		 * Methode for finding selecting all questions having duplicate reference
		 * @ input productKey
		 *  getDuplicateAllBenefitRef
		 */
		    public List  getDuplicateAllQuestionRef(int productKey)throws SevereException{
		    	List duplicateAllBenefitRef =null;
		    	HashMap referenceValueSet = new HashMap();
		    	referenceValueSet.put("entitySysId", new Integer(productKey));
		    	SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
		    			ProductQuestUniqueReferenceBO.class.getName(), WebConstants.GET_DUPLICATE_ALL_QUEST_REF,
		                referenceValueSet);
		    	duplicateAllBenefitRef=AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
		    	return duplicateAllBenefitRef; 
		    }
    /**
     * this method gets the benfit component list for pop up
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getBenefitComponentsListforPopup(int productKey)
            throws SevereException {
        ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();//FIXME Object craeted unnecessarily
        List benefitComponentList = productStructureAdapterManager
                .getValidComponentsForProduct(productKey);
        return benefitComponentList;
    }


    /**
     * To set the criteria values into the hashMap from the locateCriteria for
     * getting the valid benefitComponents for ProductStructure
     * 
     * @param productId
     * @return List
     * @throws SevereException
     */
    public List getValidComponentsForProduct(int productId)
            throws SevereException {
        Logger
                .logInfo("Entering the method for getting valid components for product");
        HashMap hashMap = new HashMap();
        hashMap.put("productId", new Integer(productId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductStructureAssociatedBenefitComponent.class.getName(),
                WebConstants.GET_VALID_COMPONENTS_FOR_PRODUCT, hashMap);	
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        Logger
                .logInfo("Returning the method for getting valid components for product");
        return searchResults.getSearchResults();
    }


    /*
     * this method deletes benfit component
     */
    public boolean deleteBenefitComponent(ProductComponentBO productComponentBO,AdapterServicesAccess adapterServicesAccess)
            throws AdapterException {
    	try{
        AdapterUtil.performRemove(productComponentBO, productComponentBO
                .getLastUpdatedUser(),adapterServicesAccess);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
        return true;
    }


    /**
     * 
     * @param product
     * @throws SevereException
     */
    public void deleteProduct(ProductBO product) throws AdapterException,SevereException {
    	try{
        AdapterUtil.performRemove(product, product.getLastUpdatedUser());
    	}catch(Exception ex){
    		throw new AdapterException("Excetion occured while adapter call",ex);
    	}
    }


    /**
     * this method retieves latest product version
     * 
     * @param product
     * @return
     * @throws SevereException
     */
    public ProductBO retrieveProductLatestVersion(ProductBO product)
            throws SevereException {
        List domains = product.getBusinessDomains();
        List lobList = BusinessUtil.getLobList(domains);
        List beList = BusinessUtil.getbusinessEntityList(domains);
        List bgList = BusinessUtil.getBusinessGroupList(domains);
        //START CARS
        List marketBusinessUnitList = BusinessUtil.getMarketBusinessUnitList(domains);
        //END CARS
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productName", product.getProductName());
        criteriaMap.put("lob", lobList);
        criteriaMap.put("be", beList);
        criteriaMap.put("bg", bgList);
        //START CARS
        criteriaMap.put("mbu", marketBusinessUnitList);
        //END CARS
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                product.getClass().getName(), WebConstants.RETRIEVE_LATEST_VERSION,
                criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (ProductBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    public int retrieveProductLatestVersionNumber(ProductBO product)
            throws SevereException {
        HashMap criteriaMap = getCriteriaMapForProductBO(product);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                product.getClass().getName(), WebConstants.RETRIEVE_LATEST_VERSION_NUMBER,	
                criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return ((ProductBO) searchResults.getSearchResults().get(0))
                    .getVersion();
        }
        return -1;
    }


    public ProductBO retrieveByProductName(ProductBO product)
            throws SevereException {
        HashMap criteriaMap = getCriteriaMapForProductBO(product);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                product.getClass().getName(), WebConstants.RETRIEVE_BY_PRODUCT_NAME,	
                criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (ProductBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * this method gets the criteria map for the productbo
     * 
     * @param product
     * @return
     */
    private HashMap getCriteriaMapForProductBO(ProductBO product) {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("productKey", new Integer(product.getProductKey()));
        return criteriaValues;
    }


    /**
     * this method gets the criteria for the product componentbo
     * 
     * @param productComponentBO
     * @return
     */
    private HashMap getCriteriaMapForProductComponentBO(
            ProductComponentBO productComponentBO) {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("productKey", String.valueOf(productComponentBO
                .getProductKey()));
        criteriaValues.put("componentKey", String.valueOf(productComponentBO
                .getComponentKey()));
        return criteriaValues;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(LocateCriteria locateCriteria)
            throws SevereException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        //List locateResultsList = new ArrayList();
        ProductLocateCriteria productLocateCriteria = (ProductLocateCriteria) locateCriteria;
        // this should be changed.
        searchCriteria.setBusinessObjectName(ProductSearchResult.class
                .getName());
        searchCriteria.setMaxSearchResultSize(51);
        searchCriteria.setSearchQueryName("getProducts");
        searchCriteria.setSearchDomain("medical");		//FIXME Remove hardcoded values
        HashMap refValSet = new HashMap();
        if (productLocateCriteria.getProductName() != null)
            refValSet.put("prodname", "%"
                    + productLocateCriteria.getProductName() + "%");
        refValSet.put("prodeffectivedate", WPDStringUtil
                .convertDateToString(productLocateCriteria.getEffectiveDate()));
        refValSet.put("prodexpirydate", WPDStringUtil
                .convertDateToString(productLocateCriteria.getExpiryDate()));
        //puting lists
        refValSet.put("prodfamname", productLocateCriteria
                .getProductFamilyList());
        refValSet.put("prodstructname", productLocateCriteria
                .getProductStructureList());
        refValSet.put("prodentlist", productLocateCriteria
                .getBusinessEntityList());
        refValSet.put("prodgrouplist", productLocateCriteria
                .getBusinessGroupList());
        refValSet.put("prodloblist", productLocateCriteria
                .getLineOfBusinessList());
        refValSet.put("prodMarketBusinessUnitlist", productLocateCriteria
                .getMarketBusinessUnitList());
        searchCriteria.setReferenceValueSet(refValSet);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        LocateResults locateResults = new LocateResults();
        locateResults.setLocateResults(searchResults.getSearchResults());
        return locateResults;
    }


    /**
     * this method retrieves the product component
     * 
     * @param productComponentBO
     * @return
     * @throws SevereException
     */
    public ProductComponentBO retrieve(ProductComponentBO productComponentBO)
            throws SevereException {

        return (ProductComponentBO) AdapterUtil
                .performRetrieve(productComponentBO);
    }


    /**
     * this method gets the next sequence number for components
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public int getNextSequenceForComponent(int productKey)
            throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put("productKey", new Integer(productKey));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductComponentBO.class.getName(), WebConstants.GET_NEXT_SEQUENCE, hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() == 0)
            return 1;
        ProductComponentBO productComponentBO = (ProductComponentBO) searchResults
                .getSearchResults().get(0);
        return productComponentBO.getSequence();
    }


    public boolean isProductStructureAssociatedWithProduct(
            int productStructureId) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productStructureId", new Integer(
                productStructureId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.PRODUCT_STRUCTURE_VALIDATION,	
                referenceValueSet);
        List productList = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        if (productList != null && productList.size() == 0)
            return false;
        else
            return true;
    }


    public void copyAllOverridedDefenitions(int prodId, int benefitComponentId,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException {
        BenefitDefnValueOverride defnValueOverride = new BenefitDefnValueOverride();
        defnValueOverride.setProductSysId(prodId);
        defnValueOverride.setComponentSysId(benefitComponentId);
        defnValueOverride.setCreatedUser(audit.getUser());
        defnValueOverride.setCreatedTimestamp(audit.getTime());
        defnValueOverride.setLastUpdatedTimestamp(audit.getTime());
        defnValueOverride.setLastUpdatedUser(audit.getUser());
        AdapterUtil.performInsert(defnValueOverride, audit.getUser(),
                adapterServicesAccess);
    }


    public void copyAllOverridedAdminOptions(int prodId,
            int benefitComponentId, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        BenefitAdminOverridenValue adminOptionOverride = new BenefitAdminOverridenValue();
        adminOptionOverride.setProductSysId(prodId);
        adminOptionOverride.setComponentSysId(benefitComponentId);
        adminOptionOverride.setCreatedUser(audit.getUser());
        adminOptionOverride.setCreatedTimestamp(audit.getTime());
        adminOptionOverride.setLastUpdatedTimestamp(audit.getTime());
        adminOptionOverride.setLastUpdatedUser(audit.getUser());
        AdapterUtil.performInsert(adminOptionOverride, audit.getUser(),
                adapterServicesAccess);
    }


    public List getAllProductVersions(int productSysId) throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put("productSysId", new Integer(productSysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductSearchResult.class.getName(), WebConstants.GET_ALL_VERSION, hashMap); 
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    //gets the product list which have benefit components associated
    public List getDuplicateComponentsList(int productKey)
            throws SevereException {
        List duplicateComponentList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productKey", new Integer(productKey));
        SearchCriteria adapterSearchCriteria = null;	//FIXME No need of this
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.CHECKIN_DUPLICATE_VALIDATION,
                referenceValueSet);
        duplicateComponentList = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        return duplicateComponentList;
    }


    public List getAssociatedMandatoryBenefits(int productKey)
            throws SevereException {
        List benefitComponentList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productKey", new Integer(productKey));
        SearchCriteria adapterSearchCriteria = null;//FIXME No need of this
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductComponentBO.class.getName(), WebConstants.GET_MANDATORY_BENEFITS,
                referenceValueSet);
        benefitComponentList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return benefitComponentList;
    }
    
    public List getMandatoryGenProvision(int productKey)
    throws SevereException {
		List benefitComponentList = null;
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productKey", new Integer(productKey));
		SearchCriteria adapterSearchCriteria = null;//FIXME No need of this
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        ProductComponentBO.class.getName(), WebConstants.GET_MANDATORY_GEN_PROVISION,
		        referenceValueSet);
		benefitComponentList = AdapterUtil.performSearch(adapterSearchCriteria)
		        .getSearchResults();
		return benefitComponentList;
}


    /**
     * This method returns duplicate products
     * 
     * @param product
     * @return
     * @throws SevereException
     */
    public List getDuplicateProducts(String entityName, List domainList,
            	int entityParentId) throws SevereException {
    	
    	List lineOfBusiness = null;
        List businessEntity = null;
        List businessGroup = null;
        List marketBusinessUnit = null;
        Domain domain = null;
        boolean flag = true;
        if(null != domainList && !domainList.isEmpty()) {
        	int domainListSize = domainList.size();
        	lineOfBusiness = new ArrayList(domainListSize);
        	businessEntity = new ArrayList(domainListSize);
        	businessGroup = new ArrayList(domainListSize);
        	marketBusinessUnit = new ArrayList(domainListSize);
	        for (Iterator iter = domainList.iterator(); iter.hasNext();) {
	            domain = (Domain) iter.next();
	            lineOfBusiness.add(domain.getLineOfBusiness());
	            businessEntity.add(domain.getBusinessEntity());
	            businessGroup.add(domain.getBusinessGroup());
	            marketBusinessUnit.add(domain.getMarketBusinessUnit());
	        }
        }
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productName",entityName);
        criteriaMap.put("parentId", new Integer(entityParentId));
        
        // If Line of business is 'ALL' for new Product, then Product with same name will be considered as 
        // duplicate for any value of Line of Business if Business entity and Buseinss Group are matching.
        // So Line of business needs to be excluded from the query if it is 'ALL'.
        // This is also valid for BE and BG.
        if(!lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("lob",lineOfBusiness);
        }
        
        if(!businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("be",businessEntity);        	
        }
        
        if(!businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("bg",businessGroup);        	
        }
        //CARS START
        //Setting the market business unit as a criteria value.
        if(!marketBusinessUnit.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("mbu",marketBusinessUnit);        	
        }
        //CARS END
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.GET_DUPLICATE_PRODUCTS,
                criteriaMap);
        return AdapterUtil.performSearch(searchCriteria).getSearchResults();
    }
   

    public List getProductsForContract(List lineOfBusiness,
            List businessEntity, List businessGroup, List marketBusinessUnit, Date effectiveDate,
            Date expiryDate,String productType,String testContractState,String state, int productParentSysId) throws SevereException {

        Logger
                .logInfo("Entering the method for getting valid products for contract");

        HashMap map = new HashMap();

        map.put("lob", lineOfBusiness);
        map.put("be", businessEntity);
        map.put("bg", businessGroup);
        /*START CARS*/
        map.put("mbu", marketBusinessUnit);
        /*END CARS*/
        map.put("expiryDate", WPDStringUtil.convertDateToString(expiryDate));
        map.put("productType",productType);
		if(0 != productParentSysId){
			map.put("parentId",new Integer(productParentSysId));
		}
        // This is done for making adapter dynamic query working
        if (effectiveDate == null)
            map.put(WebConstants.EFFECTIVEDATE, WebConstants.DEFAULT_EFF_DATE);
        else
            map.put(WebConstants.EFFECTIVEDATE, WPDStringUtil
                    .convertDateToString(effectiveDate));
        map.put("testContractState",testContractState);
        if("".equals(state))
            map.put("stateId",null);
        else
            map.put("stateId",state);

        Logger
                .logInfo("Returning the method for getting valid product structure");

        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(ProductBO.class.getName(),
                        WebConstants.GET_VALID_PRODUCTS_FOR_CONTRACT, map)).getSearchResults();
    }


    /**
     * creates the product admin
     * 
     * @param adminBO
     * @throws SevereException
     */
    public void createProductAdmin(ProductAdminBO adminBO,AdapterServicesAccess adapterServicesAccess)
            throws AdapterException {
    	try{
        AdapterUtil.performInsert(adminBO, adminBO.getLastUpdatedUser(),adapterServicesAccess);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
    }


    /**
     * updates the product admin
     * 
     * @param adminBO
     * @throws SevereException
     */
    public void updateProductAdmin(ProductAdminBO adminBO,AdapterServicesAccess adapterServicesAccess)
            throws AdapterException {
    	try{
        AdapterUtil.performUpdate(adminBO, adminBO.getLastUpdatedUser(),adapterServicesAccess);
    	}catch(Exception e){
    		throw new AdapterException("Exception occured while adapter call",e);
    	}
    }


    /**
     * this method gets the admin list
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getAdminList(int productKey) throws SevereException {
        List adminList = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put("productKey", new Integer(productKey));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductAdminBO.class.getName(), WebConstants.GET_ADMIN, referenceValueSet);

        SearchResults searchResults = AdapterUtil
                .performSearch(adapterSearchCriteria);
        adminList = searchResults.getSearchResults();

        return adminList;
    }


    /**
     * this method gets the admin list for pop up
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getAdminListforPopup(int productKey) throws SevereException {
        List adminList = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;       //FIXME No need of this
        referenceValueSet.put("productKey", new Integer(productKey));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductAdminBO.class.getName(), WebConstants.RETRIEVE_ALL_LATEST_ADMIN_OPTION,
                referenceValueSet);
        adminList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return adminList;
    }


    /*
     * this method deletes admin
     */
    public boolean deleteAdmin(ProductAdminBO productAdminBO, String userId,AdapterServicesAccess access)
            throws SevereException {
        AdapterUtil.performRemove(productAdminBO,userId,access);
        return true;
    }


    /**
     * this method retrieves the product admin
     * @param productAdminBO
     * @return
     * @throws SevereException
     */
    public ProductAdminBO retrieve(ProductAdminBO productAdminBO)
            throws SevereException {

        return (ProductAdminBO) AdapterUtil.performRetrieve(productAdminBO);
    }
    /**
     * To get the list of BenefitAdministration objects from the db for
     * ProductStructure
     * 
     * @param productStructureBO
     * @param benefitAdministrationSubObject
     * @return List
     * @throws SevereException
     */
    public List getBenefitAdministrationValues(String entityType, 
    		int entityId, int adminOptionSysId)throws SevereException{
    	HashMap map = new HashMap();
    	map.put("entitySysId", new Integer(entityId));
        map.put("entityType", entityType);
        map.put("adminOptSysId", new Integer(adminOptionSysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                EntityProductAdministration.class.getName(),WebConstants.LOCATE_ENTITY_PRODUCT_ADMIN,map); 
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    	
    }
    public List getChildQuestionnaireValues(int selectedAnswerid, int questionnaireId, int productParentId,int productId,int adminOptionSysId)
	throws SevereException {

		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("entitySysId", new Integer(productParentId));
		map.put("primaryEntityId", new Integer(productId));
		map.put("adminOptSysId", new Integer(adminOptionSysId));
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				EntityProductAdministration.class.getName(),
				"getChildQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();

    }
    
    /**
     * To update the overridded values of the administration option questions
     * 
     * @param productStructureBO
     * @param benefitAdministration
     * @throws SevereException
     */
    public void updateProductAdministrationValues(
            EntityProductAdministration productAdministration, String userId, AdapterServicesAccess access)
    		throws AdapterException{
    	try{
    	AdapterUtil.performUpdate(productAdministration, userId, access);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
    }
    /**
     * Retrieves the ProductBO corresponding to the ProductStructureId
     * of the ProductStructure.
     * 
     * @param productBO
     *            ProductBO object.
     * @return ProductBO.
     * @throws SevereException
     */
    public ProductBO retrieveByProductId(
            ProductBO product) throws SevereException {
        Logger
                .logInfo("Entering the method for retrieving product structure by product structure id");
        
        product = (ProductBO)AdapterUtil.performRetrieve(product);
        Logger
                .logInfo("Returning the method for retrieving product structure by product structure id");
        return product;
    }
    /**
     * this method gets the rule id/type list
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getRulesList(String queryName, String ruleType) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        String value = WebConstants.FLAG_N;		
        referenceValueSet.put("flag", value);
        if(queryName.equals(WebConstants.RULE_ID))
        {referenceValueSet.put(WebConstants.RULE_TYPE, ruleType);}
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductRuleBO.class.getName(), queryName, referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

        return searchResults.getSearchResults();
    }


    /**
     * creates the product rule
     * 
     * @param productRuleAssociationBO
     * @throws SevereException
     */
    public void createProductRules(ProductRuleAssociationBO productRuleAssociationBO, String userId,
    		AdapterServicesAccess adapterServicesAccess) throws AdapterException {
    	try{
        AdapterUtil.performInsert(productRuleAssociationBO, userId,adapterServicesAccess);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
    }


    /**
     * updates the product rule
     * 
     * @param productRuleAssociationBO
     * @throws SevereException
     */
    public void updateProductRule(ProductRuleAssociationBO productRuleAssociationBO, String userId,
    		AdapterServicesAccess adapterServicesAccess) throws AdapterException {
    	try{
    		AdapterUtil.performUpdate(productRuleAssociationBO, userId,adapterServicesAccess);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);	
    	}
    }
    
    /**
     * creates the product rule
     * 
     * @param productRuleAssociationBO
     * @throws SevereException
     */
    public void deleteProductRule(ProductRuleAssociationBO productRuleAssociationBO, String userId) throws AdapterException,SevereException {
    	try{
        AdapterUtil.performRemove(productRuleAssociationBO, userId);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);	
    	}
    }
    
    /**
     * this method gets the rule id/type list
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public ProductBO getProductRulesList(ProductBO productBO, String ruleType,Integer pageno) throws SevereException {
    	List productRulesList;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put("productKey", new Integer(productBO.getProductKey()));
        referenceValueSet.put("ruleType", ruleType);
        setRuleRecordRange(pageno,referenceValueSet);
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductRuleAssociationBO.class.getName(), WebConstants.GET_PRODUCT_ALL_RULES, referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

        productRulesList = searchResults.getSearchResults();
        productBO.setDenialAndExclusionList(productRulesList != null ? productRulesList : null);
        return productBO;
    }
    /**
     * this method gets the rule id/type list
     * 
     * @param productKey
     * @return
     * @throws SevereException
     */
    public List getProductRulesList(ProductBO productBO) throws SevereException {
    	List productRulesList;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put("productKey", new Integer(productBO.getProductKey()));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductRuleAssociationBO.class.getName(), WebConstants.GET_PRODUCT_RULES, referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

        productRulesList = searchResults.getSearchResults();
        return productRulesList;
    }
    /**
     * This method returns duplicate product rule 
     * @param productRuleAssociation
     * @return
     * @throws SevereException
     */
    public List getDuplicateProductRules(ProductRuleAssociation productRuleAssociation)
    	throws SevereException {
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        List originalList = new ArrayList(productRuleAssociation.getRulesList());
        List returnList = null;//FIXME Move to place where elemnts adding
        List searchResult;
        referenceValueSet.put("productKey", new Integer(productRuleAssociation.getProductKey()));
        int rulesListSize = productRuleAssociation.getRulesList().size();
        	int fromIndex =0;
        	for(int toIndex=1000; toIndex < rulesListSize; toIndex+=1000){
        		
                referenceValueSet.put("productGenRulesList", originalList.subList(fromIndex, toIndex-1));
                fromIndex = toIndex;
                
                adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                		ProductRuleAssociationBO.class.getName(), WebConstants.DUPLICATE_PRODUCT_RULES, referenceValueSet);
                searchResult = AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
                if(null != searchResult && !searchResult.isEmpty()){
                	returnList = new ArrayList(searchResult.size());
                	returnList.addAll(searchResult);
                }
        	}
        	if(fromIndex < rulesListSize){
                referenceValueSet.put("productGenRulesList", originalList.subList(fromIndex, rulesListSize));
                
                adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                		ProductRuleAssociationBO.class.getName(), WebConstants.DUPLICATE_PRODUCT_RULES, referenceValueSet);
                searchResult = AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
                if(null != searchResult && !searchResult.isEmpty()){
                	returnList = new ArrayList(searchResult.size());
                	returnList.addAll(searchResult);
                }        		
        	}
        return returnList;
	}
    /*
     * methode for checking the mandatory field: valie in product rule
     * 
     * @@param productRuleAssociation
     * 
     */
    public List getProductRulesMandatoryValue(ProductRuleAssociation productRuleAssociation)
	throws SevereException {
    HashMap referenceValueSet = new HashMap();
    SearchCriteria adapterSearchCriteria = null;//FIXME No need
    List returnList = null;//FIXME Move to place where elemnts adding
    List searchResult;
    referenceValueSet.put("productKey", new Integer(productRuleAssociation.getProductKey()));
                  
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
            		ProductRuleAssociationBO.class.getName(), WebConstants.PRODUCT_RULES_MANDATORY_VALUE_CHECK, referenceValueSet);
            searchResult = AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
            if(null != searchResult && !searchResult.isEmpty()){
            	returnList = new ArrayList(searchResult.size());
            	returnList.addAll(searchResult);
            }
    	
    return returnList;
}
	/**
	 * @param productKey
	 * @param componentId
	 * @return
	 * @throws SevereException
	 */
	public int retrieveProductStructureId(int productKey, int componentId) throws SevereException {
		HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        
        referenceValueSet.put("productKey", new Integer(productKey));
        referenceValueSet.put("productBenefitComponentKey", new Integer(componentId));
        
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductBO.class.getName(), WebConstants.GET_PRODUCT_STRUCTURE_ID, referenceValueSet);
        SearchResults results = AdapterUtil.performSearch(adapterSearchCriteria);
        int prodStructureId = 0;   
        if(null != results.getSearchResults() && !results.getSearchResults().isEmpty()){
        	 ProductBO resultBO = (ProductBO) results.getSearchResults().get(0);
        	 prodStructureId = Integer.parseInt(resultBO.getProductStructureKey());;
        }
		return prodStructureId;
	}   
    /**
     * Method to retrieve the list of product family and state code
     * for reference data validation
     * @param productBO
     * @return
     * @throws SevereException
     */
    public List retrieveProductForValidation(ProductBO productBO) throws SevereException{
        HashMap referenceValueSet = new HashMap();
        List locateResults;
        SearchCriteria adapterSearchCriteria = null;//FIXME No need
        referenceValueSet.put("productKey", new Integer(productBO.getProductKey()));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.REFERENCE_DATA_VALIDATION, referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

        locateResults = searchResults.getSearchResults();
        return locateResults;
    }
    /**
     * Method to retrieve the list of provider arrangement
     * for reference data validation
     * @param productBO
     * @return
     * @throws SevereException
     */
	public List retrievePvaForValidation(ProductBO productBO) throws SevereException{
        HashMap referenceValueSet = new HashMap();
        List locateResults;
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put("productKey", new Integer(productBO.getProductKey()));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductRuleAssociationBO.class.getName(), WebConstants.RETRIEVE_PVA_FOR_VALIDATION, referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);

        locateResults = searchResults.getSearchResults();
        return locateResults;
    }
	/*
     * this method deletes benfit component
     */
    public boolean deleteQuestion(EntityBenefitAdministration entityBenefitAdminBO, String userId)
            throws AdapterException,SevereException {
    	try{
    		AdapterUtil.performRemove(entityBenefitAdminBO, userId);
    	}catch(Exception ex){
    		throw new AdapterException("Exception occured while adapter call",ex);
    	}
        return true;
    }
    public boolean hideProductAdminOption(ProductEntityBenefitAdmin  productEntityBenefitAdmin, 
    		String user)throws AdapterException,SevereException{    
		try{
	    	AdapterUtil.performRemove(productEntityBenefitAdmin,user);	
	 	}catch(Exception ex){
			throw new AdapterException("Exception occured while adapter call",ex);
		}
		
	return true;
    }
    
    /*
	 * This method is for persist new questionnare 
	 * 
	 * @param ComponentsBenefitAdministrationBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 * This method delete all the existing questionare and persist a new questionnare
	 */
	public void saveQuestionnare(ProductQuestionnaireAssnBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		List questionnareList = administrationBO.getQuestionnareList();
		int productId = administrationBO.getProductSysId();
		ProductQuestionnaireAssnBO administration = new ProductQuestionnaireAssnBO();
		administration.setProductSysId(productId);
		administration.setAdminOptionSysId(administrationBO.getAdminOptionSysId());
		ProductQuestionnaireAssnBO productQuestionnaireAssnBO;
		AdapterUtil.performRemove(administration, audit.getUser(),
				access);
		for(int i=0;i<questionnareList.size();i++){
			EntityProductAdministration entityProductAdministration = (EntityProductAdministration)questionnareList.get(i);
			productQuestionnaireAssnBO = new ProductQuestionnaireAssnBO();
			productQuestionnaireAssnBO.setProductSysId(productId);
			productQuestionnaireAssnBO.setAdminOptionSysId(administrationBO.getAdminOptionSysId());
			productQuestionnaireAssnBO.setQuestionId(entityProductAdministration.getQuestionnaireId());
			productQuestionnaireAssnBO.setAnswerOvrdId(entityProductAdministration.getSelectedAnswerid());
			productQuestionnaireAssnBO.setCreatedUser(audit.getUser());
			productQuestionnaireAssnBO.setCreatedTimestamp(audit.getTime());
			productQuestionnaireAssnBO.setLastUpdatedUser(audit.getUser());
			productQuestionnaireAssnBO.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performInsert(productQuestionnaireAssnBO, audit.getUser(),access);
		}
	}
	/*
	 * This method is for persist new questionnare 
	 * 
	 * @param ComponentsBenefitAdministrationBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 * This method delete all the existing questionare and persist a new questionnare
	 */
	public void saveQuestionnareForProduct(SaveProductQuestionareBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		
		List questionnareList = administrationBO.getQuestionnareList();
		List tierList = administrationBO.getTierList();
		
		//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		List newQuestions = administrationBO.getNewQuestions();
		List modifiedQuestions = administrationBO.getModifiedQuestions();
		List removedQuestions = administrationBO.getRemovedQuestions();
		List newTieredQuestions = administrationBO.getNewTieredQuestions();
		List modifiedTieredQuestions = administrationBO.getModifiedTieredQuestions();
		List removedTieredQuestions = administrationBO.getRemovedTieredQuestions();
		
		int productSysId = administrationBO.getProductSysId();
		ProductQuestionareBO productQuestionareBO = new ProductQuestionareBO();
		productQuestionareBO.setEntitySysId(productSysId);
		productQuestionareBO.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
		
		Iterator it1 = newQuestions.iterator();
		while(it1.hasNext()){
			ProductQuestionareBO productQuestionareBOToAdd = (ProductQuestionareBO)it1.next();
			productQuestionareBOToAdd.setEntitySysId(administrationBO.getProductSysId());
			productQuestionareBOToAdd.setBenefitComponentId(administrationBO.getBenefitComponentid());
			productQuestionareBOToAdd.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
			productQuestionareBOToAdd.setCreatedUser(audit.getUser());
			productQuestionareBOToAdd.setCreatedTimestamp(audit.getTime());
			productQuestionareBOToAdd.setLastUpdatedUser(audit.getUser());
			productQuestionareBOToAdd.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performInsert(productQuestionareBOToAdd, audit.getUser(),access);
		}
		
		Iterator it2 = modifiedQuestions.iterator();
		while(it2.hasNext()){
			ProductQuestionareBO productQuestionareBOToUpdate = (ProductQuestionareBO)it2.next();
			productQuestionareBOToUpdate.setEntitySysId(administrationBO.getProductSysId());
			productQuestionareBOToUpdate.setBenefitComponentId(administrationBO.getBenefitComponentid());
			productQuestionareBOToUpdate.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
			productQuestionareBOToUpdate.setCreatedUser(audit.getUser());
			productQuestionareBOToUpdate.setCreatedTimestamp(audit.getTime());
			productQuestionareBOToUpdate.setLastUpdatedUser(audit.getUser());
			productQuestionareBOToUpdate.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performUpdate(productQuestionareBOToUpdate, audit.getUser(),access);
		}
		
		Iterator it3 = removedQuestions.iterator();
		while(it3.hasNext()){
			ProductQuestionareBO productQuestionareBOToRemove = (ProductQuestionareBO)it3.next();
			productQuestionareBOToRemove.setEntitySysId(administrationBO.getProductSysId());
			productQuestionareBOToRemove.setBenefitComponentId(administrationBO.getBenefitComponentid());
			productQuestionareBOToRemove.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
			productQuestionareBOToRemove.setCreatedUser(audit.getUser());
			productQuestionareBOToRemove.setCreatedTimestamp(audit.getTime());
			productQuestionareBOToRemove.setLastUpdatedUser(audit.getUser());
			productQuestionareBOToRemove.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performRemove(productQuestionareBOToRemove, audit.getUser(),access);
		}
		
		if(null != tierList && tierList.size()>0){

			Iterator it4 = newTieredQuestions.iterator();
			while(it4.hasNext()){
				ProductTierQuestionareBO productTierQuestionareBOToAdd = new ProductTierQuestionareBO();
				ProductQuestionareBO bo1 = (ProductQuestionareBO)it4.next();
				
				productTierQuestionareBOToAdd.setQuestionnaireId(bo1.getQuestionnaireId());
				productTierQuestionareBOToAdd.setSelectedAnswerid(bo1.getSelectedAnswerid());
				productTierQuestionareBOToAdd.setEntitySysId(administrationBO.getProductSysId());
				productTierQuestionareBOToAdd.setBenefitComponentId(administrationBO.getBenefitComponentid());
				productTierQuestionareBOToAdd.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
				productTierQuestionareBOToAdd.setTierSysId(bo1.getTierSysId());
				productTierQuestionareBOToAdd.setCreatedUser(audit.getUser());
				productTierQuestionareBOToAdd.setCreatedTimestamp(audit.getTime());
				productTierQuestionareBOToAdd.setLastUpdatedUser(audit.getUser());
				productTierQuestionareBOToAdd.setLastUpdatedTimestamp(audit.getTime());
				
				AdapterUtil.performInsert(productTierQuestionareBOToAdd, audit.getUser(),access);
			}
			
			Iterator it5 = modifiedTieredQuestions.iterator();
			while(it5.hasNext()){
				ProductTierQuestionareBO productTierQuestionareBOToUpdate = new ProductTierQuestionareBO();
				ProductQuestionareBO bo2 = (ProductQuestionareBO)it5.next();
				
				productTierQuestionareBOToUpdate.setQuestionnaireId(bo2.getQuestionnaireId());
				productTierQuestionareBOToUpdate.setSelectedAnswerid(bo2.getSelectedAnswerid());
				productTierQuestionareBOToUpdate.setEntitySysId(administrationBO.getProductSysId());
				productTierQuestionareBOToUpdate.setBenefitComponentId(administrationBO.getBenefitComponentid());
				productTierQuestionareBOToUpdate.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
				productTierQuestionareBOToUpdate.setTierSysId(bo2.getTierSysId());
				productTierQuestionareBOToUpdate.setCreatedUser(audit.getUser());
				productTierQuestionareBOToUpdate.setCreatedTimestamp(audit.getTime());
				productTierQuestionareBOToUpdate.setLastUpdatedUser(audit.getUser());
				productTierQuestionareBOToUpdate.setLastUpdatedTimestamp(audit.getTime());
				
				AdapterUtil.performUpdate(productTierQuestionareBOToUpdate, audit.getUser(),access);
			}
			
			Iterator it6 = removedTieredQuestions.iterator();
			while(it6.hasNext()){
				ProductTierQuestionareBO productTierQuestionareBOToRemove = new ProductTierQuestionareBO();
				ProductQuestionareBO bo3 = (ProductQuestionareBO)it6.next();
				
				productTierQuestionareBOToRemove.setQuestionnaireId(bo3.getQuestionnaireId());
				productTierQuestionareBOToRemove.setSelectedAnswerid(bo3.getSelectedAnswerid());
				productTierQuestionareBOToRemove.setEntitySysId(administrationBO.getProductSysId());
				productTierQuestionareBOToRemove.setBenefitComponentId(administrationBO.getBenefitComponentid());
				productTierQuestionareBOToRemove.setAdmnLvlAsscId(administrationBO.getAdminLevelOptionSysId());
				productTierQuestionareBOToRemove.setTierSysId(bo3.getTierSysId());
				productTierQuestionareBOToRemove.setCreatedUser(audit.getUser());
				productTierQuestionareBOToRemove.setCreatedTimestamp(audit.getTime());
				productTierQuestionareBOToRemove.setLastUpdatedUser(audit.getUser());
				productTierQuestionareBOToRemove.setLastUpdatedTimestamp(audit.getTime());
				
				AdapterUtil.performRemove(productTierQuestionareBOToRemove, audit.getUser(),access);
				
			}
			}
		
		
	}
	
	/**
	 * To get the list of BenefitAdministration objects from the db for
	 * ProductStructure
	 * 
	 * @param productStructureBO
	 * @param benefitAdministrationSubObject
	 * @return List
	 * @throws SevereException
	 */
	public List getQuestionnaireValuesForProduct(int admnLvlAsscId, int benefitComponentId, int entitySysId)
			throws SevereException {
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("admnLvlAsscId", new Integer(admnLvlAsscId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		map.put("entitySysId", new Integer(entitySysId));
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
	}
	/**
	 * To get the list of BenefitAdministration objects from the db for
	 * ProductStructure
	 * 
	 * @param productStructureBO
	 * @param benefitAdministrationSubObject
	 * @return List
	 * @throws SevereException
	 */
	public List getQuestionnaireValuesForProduct(int admnLvlAsscId, int benefitComponentId, int entitySysId,
			int adminOptSysId, int benftAdminSysId, int productPrntSysId)
			throws SevereException {
		
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("benftAdminSysId", new Integer(benftAdminSysId));// 0
		map.put("entitySysId", new Integer(entitySysId));//1
		map.put("adminOptSysId", new Integer(adminOptSysId));//2
		map.put("benefitComponentId", new Integer(benefitComponentId));//3
		map.put("admnLvlAsscId", new Integer(admnLvlAsscId));//4
		map.put("productSysId", new Integer(productPrntSysId));//5
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
	}
	/**
	 * To get the list of tiered questions  from the db for
	 * Product
	 * 
	 * @param adminlevelassociationsysid
	 * @param benefitComponentId
	 * @param productSysId
	 * @param List of TiersysIds
	 * @return List
	 * @throws SevereException
	 */
	public List getTieredQuestionnaireValuesForProduct(int admnLvlAsscId, int benefitComponentId, int entitySysId,List tierSysIdList)
			throws SevereException{
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("admnLvlAsscId", new Integer(admnLvlAsscId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		map.put("entitySysId", new Integer(entitySysId));
		map.put("tierList",tierSysIdList );
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getTieredQuestionnaires", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
	}
	
	/**
	 * To get the list of tiered questions  from the db for
	 * Product
	 * 
	 * @param adminlevelassociationsysid
	 * @param benefitComponentId
	 * @param productSysId
	 * @param List of TiersysIds
	 * @return List
	 * @throws SevereException
	 */
	public List getTieredQuestionnaireValuesForProduct(int admnLvlAsscId, int benefitComponentId, int entitySysId,List tierSysIdList,
			int adminOptSysId, int benftAdminSysId, int productPrntSysId)
			throws SevereException{
		
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("benftAdminSysId", new Integer(benftAdminSysId));// 0
		map.put("entitySysId", new Integer(entitySysId));//1
		map.put("adminOptSysId", new Integer(adminOptSysId));//2
		map.put("benefitComponentId", new Integer(benefitComponentId));//3
		map.put("admnLvlAsscId", new Integer(admnLvlAsscId));//4
		map.put("productSysId", new Integer(productPrntSysId));//5
		map.put("tierList",tierSysIdList );//6
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getTieredQuestionnaires", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
	}
	
	/**
	 * To get the  child Questionnare List while chenging answer
	 *  
	 * @param selectedAnswerid
	 * @param questionnaireId
	 * @param beneftComponentId
	 * @return List
	 * 		   this list contain child questionnare.
	 * @throws SevereException
	 */
	public List getChildQuestionnaireValuesForProduct(int selectedAnswerid,
			int questionnaireId, int productSysId, int adminLevelAssnSysId,
			int bnftCompId, int bnftDefId,int entytySysId) throws SevereException {

		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("productSysId", new Integer(productSysId));
		map.put("admnLvlAsscId", new Integer(adminLevelAssnSysId));
		map.put("benefitComponentId", new Integer(bnftCompId));
		map.put("bnftDefinitionSysId", new Integer(bnftDefId));
		map.put("entitySysId", new Integer(entytySysId));
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getChildQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		
	}
	
	public List getChildTierQuestionnaireValuesForProduct(int selectedAnswerid,
			int questionnaireId, int productSysId, int adminLevelAssnSysId,
			int bnftCompId, int bnftDefId,int entytySysId, int tierSysId)throws SevereException{
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("productSysId", new Integer(productSysId));
		map.put("admnLvlAsscId", new Integer(adminLevelAssnSysId));
		map.put("benefitComponentId", new Integer(bnftCompId));
		map.put("bnftDefinitionSysId", new Integer(bnftDefId));
		map.put("entitySysId", new Integer(entytySysId));
		map.put ("tierSysID", new Integer(tierSysId));
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductQuestionareBO.class.getName(),
				"getChildTierQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
	}
	
	/**
	 * Method to copy all the associated entity to the new version
	 * @param sourceProductId
	 * @param targetProductId
	 * @param userId
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public ProductBO checkOutProduct(int sourceProductId,
            int targetProductId, String userId, AdapterServicesAccess adapterServicesAccess) 
			throws SevereException, AdapterException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productKey", new Integer(targetProductId));
        criteriaMap.put("sourceProdId", new Integer(sourceProductId));
        criteriaMap.put("lastUpdatedUser", userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.CHECKOUT_PRODUCT, criteriaMap); 
        SearchResults searchResults = null; 
           searchResults = AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);	       
        
        if(searchResults.getSearchResultCount()>0){  
        ProductBO targetProduct = (ProductBO) searchResults.getSearchResults() 
                .get(0);
        return targetProduct;
        }
        else{
        	return null;
        
        }
    }
	
	public ProductBO copyProduct(int sourceProductId,
            int targetProductId, String userId, AdapterServicesAccess adapterServicesAccess) 
			throws SevereException, AdapterException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("productKey", new Integer(targetProductId));
        criteriaMap.put("sourceProdId", new Integer(sourceProductId));
        criteriaMap.put("lastUpdatedUser", userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.COPY_PRODUCT, criteriaMap); 
        SearchResults searchResults = null; 
           searchResults = AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);	       
        
        if(searchResults.getSearchResultCount()>0){  
        ProductBO targetProduct = (ProductBO) searchResults.getSearchResults() 
                .get(0);
        return targetProduct;
        }
        else{
        	return null;
        
        }
    }

	
	/**
	 * This method perform a data base operation to retrieve rule deatils attached to benefit component in product 
	 * using product id and benefit component id as inputs .
	 * @param ProductComponentRule
	 * @return List of ProductComponentRule object
	 * @throws SevereException
	 */
	public List getRuleForproductBenefitComponent(
			ProductComponentRule details) throws SevereException {
		List productComponentRuleList = null;
		HashMap referenceValueSet = new HashMap();
		if (null != details) {
			int productId = details.getProductId();
			if (0 != productId) {
				referenceValueSet.put("productId", new Integer(details
						.getProductId()));
			}
			int bcid=details.getBenefitComponentId();
			if (0 != bcid) {
				referenceValueSet.put("benefitComponentId", new Integer(bcid));
			}
		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductComponentRule.class.getName(),
				"productcomponentrule", referenceValueSet);
		productComponentRuleList = AdapterUtil.performSearch(searchCriteria)
		.getSearchResults();
		return productComponentRuleList;
		
	}
	
	/**
	 * This method is to delete benefit line and levels from tier on hide of lines/levels in Benefits in Product.
	 * @param productId
	 * @param benefitComponentId
	 * @param benefitId
	 * @return boolean value(true,false)
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public boolean deleteHiddenTierLinesAndLevels(int productId, int benefitComponentId, int benefitId, AdapterServicesAccess access) 
	 throws AdapterException,SevereException{	
		Logger.logInfo("Entering the method deleteHiddenTierLinesAndLevels for deleting hidden tier benefit levels and lines");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("prodSysId", new Integer(productId));
		criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
		criteriaMap.put("benefitSysId", new Integer(benefitId));
		SearchResults searchResults = null;			
		 try{
		 	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
					"deleteHiddenTierLinesAndLevels", criteriaMap);	
        	searchResults = AdapterUtil.performSearch(searchCriteria,access);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;			
	}
	
	/**
	 * This method is for updating the product component rule informations 
	 * 
	 * @param ProductComponentRule
	 * @param user
	 * @param AdapterServicesAccess
	 * @return boolean value(true,false)
	 * @throws SevereException
	 */
	public boolean saveRuleInfo(ProductComponentRule benefitComponents, String user,
			AdapterServicesAccess serviceAccess) throws SevereException {
		AdapterUtil.performUpdate(benefitComponents, user,serviceAccess);
		return true;
	}
	
	/**
	 * 
	 * @param productBenefitTier
	 * @param userId
	 * @return
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public boolean deleteProductTier( BenefitTier productBenefitTier, String userId)
	    throws AdapterException,SevereException {
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();
        refValSet.put("tierSysId",new Integer(productBenefitTier.getBenefitTierSysId()));
        refValSet.put("entityType","PRODUCT");        
        try{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(BenefitTier.class.getName(), "deleteBenefitTier", refValSet);
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;   
	}
	
	/**
	 * The method will fetch the Tier Definitions for the Product from the 
	 * PROD_TIER_DEFN_OVRD table.
	 * @param pdktBenefitDefinitionLocateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List getTierDefnsForProduct(ProductBenefitDefinitionLocateCriteria 
	        pdktBenefitDefinitionLocateCriteria) throws SevereException {
		List productTierDefnsList = null;
		
		HashMap referenceValueSet = new HashMap();		
		referenceValueSet.put("productId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getProductId()));			
		referenceValueSet.put("benefitComponentId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitComponentId()));
		referenceValueSet.put("benefitId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitId()));		
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        ProductTierDefnOverrideBO.class.getName(),
				BusinessConstants.RETRIEVE_TIERDEFN_PRODUCT, referenceValueSet);
		productTierDefnsList = AdapterUtil.performSearch(searchCriteria)
		.getSearchResults();
		return productTierDefnsList;		
	}
	
	/**
	 * The method will fetch the Tier Definitions vailable for the
	 * Product from Benefit, from the BNFT_TIER_DEFN_ASSN table
	 * @param pdktBenefitDefinitionLocateCriteria
	 * @return
	 * @throws SevereException
	 */
	public LocateResults getTierDefnsInBenefit(ProductBenefitDefinitionLocateCriteria 
	        pdktBenefitDefinitionLocateCriteria) throws SevereException {	
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		HashMap referenceValueSet = new HashMap();		
		referenceValueSet.put("productId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getProductId()));			
		referenceValueSet.put("benefitComponentId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitComponentId()));
		referenceValueSet.put("benefitDefinitionId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitDefinitionId()));		
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        ProductTierDefnOverrideBO.class.getName(),
				BusinessConstants.RETRIEVE_TIERDEFN_BENEFIT, referenceValueSet);
		searchCriteria.setMaxSearchResultSize(BusinessConstants.TIER_DEFN_SIZE);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
        .setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults;		
	}
	
	/**
	 * Method to update the Benefit tier info
	 * @param tierObject
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws AdapterException
	 */
	public boolean updateBenefitTierDetail(BenefitTierBindingObject tierObject, Audit audit, AdapterServicesAccess adapterServicesAccess) throws AdapterException{
		try{
			TimeHandler th = new TimeHandler();
			Logger.logInfo(th.startPerfLogging("U23914_Product_Coverage","BenefitAdapterManager","updateBenefitTierDetail"));
			
	        AdapterUtil.performUpdate(tierObject, audit.getUser(),adapterServicesAccess);
	        
	        Logger.logInfo(th.endPerfLogging());
	    	}catch(Exception e){
	    		throw new AdapterException("Exception occured while adapter call",e);
	    	}
		return true;
	}

	
	/**
	 * The method will persist the Tier Definitions for product
	 * in the PROD_TIER_DEFN_OVRD table
	 * @param productTierDefnOverrideBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void persistProductTierDefinition(	        
	        ProductTierDefnOverrideBO productTierDefnOverrideBO, Audit audit)
	throws AdapterException {	   
		try {
			AdapterUtil.performInsert(productTierDefnOverrideBO, audit
					.getUser());			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in " +
					"persistProductTierDefinition",
					exception);
		}
	}
	
	/**
	 * The method will delete the Product Tier definitions from the
	 * PROD_TIER_DEFN_OVRD table
	 * @param productTierDefnOverrideBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void deleteProductTierDefinition(	        
	        ProductTierDefnOverrideBO productTierDefnOverrideBO, Audit audit)
	throws AdapterException {	   
		try {		    
		    productTierDefnOverrideBO.setProductId(productTierDefnOverrideBO
		            .getProductId());
		    productTierDefnOverrideBO.setBenefitComponentId(productTierDefnOverrideBO
		            .getBenefitComponentId());
		    productTierDefnOverrideBO.setBenefitDefinitionId(productTierDefnOverrideBO
		            .getBenefitDefinitionId());
		    AdapterUtil.performRemove(productTierDefnOverrideBO, audit
					.getUser());   //delete the existing product tier definitions			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in " +
					"deleteProductTierDefinition",
					exception);
		}
	}
	
		
	/**
	 * Calls stored procedure "PRD_DEL_TIER_LVL" to delete a benefit level from Tiers
	 * @param TierDefinitionBO
	 * @param userId
	 * @return
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public boolean deleteLevelFromTier( TierDefinitionBO tierDefinitionBO, String userId)
	    throws AdapterException,SevereException {
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();
        
        refValSet.put("prodSysId",new Integer(tierDefinitionBO.getProdSysId()));
        refValSet.put("benefitComponentId",new Integer(tierDefinitionBO.getBenCompSysId()));
        refValSet.put("benefitSysId",new Integer(tierDefinitionBO.getBenefitSysId()));
        refValSet.put("levelSysId",new Integer(tierDefinitionBO.getLevelSysId()));      
        try{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), "deleteLevelFromTier", refValSet);
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;   
	}
	
	/**
	 * Calls stored procedure "PRD_ADD_TIER" 
	 * @param productSysId
	 * @param benefitComponentSysId
	 * @param benefitSysId
	 * @param tierDefinitionId
	 * @param levelid
	 * @param criteriaString
	 * @param isExistingTier
	 * @param lastUser
	 * @param lastTMS
	 * @return
	 * @throws AdapterException
	 * @throws SevereException
	 */
	
	public boolean addTierToProduct(int productSysId, int benefitComponentSysId, 
			int benefitSysId, int tierDefinitionId, int levelid, String criteriaString, 
			String isExistingTier, Audit lastUser, Date lastTMS )throws AdapterException,SevereException {
		
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();
        
        refValSet.put("prodSysId",new Integer(productSysId));
        refValSet.put("benefitComponentId",new Integer(benefitComponentSysId));
        refValSet.put("benefitSysId",new Integer(benefitSysId));
        refValSet.put("tierSysId",new Integer(tierDefinitionId));
        refValSet.put("levelSysId",new Integer(levelid));
        refValSet.put("criteriaString",criteriaString);
        refValSet.put("isExistingTier",isExistingTier);
        refValSet.put("lastUser",lastUser.getUser());
        refValSet.put("lastTMS",lastTMS);
        
 
        try{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), "addTierToProduct", refValSet);
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;   
	}
	
	/**
	 * The method will fetch the Tier Definition Ids of the
	 * Tiers added in product
	 * @param pdktBenefitDefinitionLocateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List getTiersAddedInProduct(ProductBenefitDefinitionLocateCriteria 
	        pdktBenefitDefinitionLocateCriteria) throws SevereException {	
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		HashMap referenceValueSet = new HashMap();		
		referenceValueSet.put("productId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getProductId()));			
		referenceValueSet.put("benefitComponentId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitComponentId()));
		referenceValueSet.put("benefitId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getBenefitId()));		
		referenceValueSet.put("productStructureId",new Integer
		        (pdktBenefitDefinitionLocateCriteria.getProductStructureId()));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        ProductTierDefnOverrideBO.class.getName(),
				BusinessConstants.GET_TIERDEFN_PRODUCT, referenceValueSet);
		searchCriteria.setMaxSearchResultSize(BusinessConstants.TIER_DEFN_SIZE);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
        .setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults.getLocateResults();		
	}
	
	
	
    
	 /*
     * this method deletes benfit component tier Info
     */
    public boolean deleteBenefitComponentTier(ProductComponentBO productComponentBO)
            throws AdapterException, SevereException {
    	Logger.logInfo("Entering the procedure for attaching Benefit Component to the Product Structure");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("productKey", new Integer(productComponentBO.getProductKey()));
        refValSet.put("componentKey", new Integer(productComponentBO.getComponentKey()));
        refValSet.put("entityType", "PRODUCT");
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
              BusinessConstants.PRODUCT_BEN_COMP_ASSC_BO, "deleteComponentTierInfo",	
              refValSet);
         searchResults = AdapterUtil.performSearch(searchCriteria);
 		Logger.logInfo("Returning the procedure for attaching Benefit Component to the Product Structure");
        return true;
    }
    /*
     * this method deletes product Info.This will call the procedure for deleting product tier info.
     * @ param ProductBO.
     */
    public boolean deleteProductTier(ProductBO productBO)
            throws AdapterException, SevereException {
    	Logger.logInfo("Entering the procedure for deleting Tier information in a product");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("productKey", new Integer(productBO.getProductKey()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductBO.class.getName(), "deleteTierInfo",	
              refValSet);
         searchResults = AdapterUtil.performSearch(searchCriteria);
 		Logger.logInfo("Returning the procedure for deleting Tier information in a product");
        return true;
    }
    /*
     * method for setting record count for retirveing product rule
     * 
     */
    public void setRuleRecordRange(Integer pageno,HashMap referenceValueSet){
    	
    	int minValue =0;
    	int maxvalue =0;
    	int pagenumber =  pageno.intValue();
    	if (pagenumber==1){
    		minValue=1;
    		maxvalue =WebConstants.REORDS_PER_PAGE;
    	}else{
    		 maxvalue = pagenumber *WebConstants.REORDS_PER_PAGE;
    		 minValue = ((pagenumber-1)*WebConstants.REORDS_PER_PAGE)+1;
    	}
    	
    	referenceValueSet.put("maxRecordRange",new Integer(maxvalue));
    	referenceValueSet.put("minRecordRange",new Integer(minValue));
    }
    /*
     * method for retrieving rule count 
     * 
     */
    public List getProductRuleCount(int productKey, String ruleType)throws SevereException {
    	List productCountList;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put("productKey", new Integer(productKey));
        referenceValueSet.put("ruleType", ruleType);
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ProductRuleAssociationBO.class.getName(), "productAllRulesCount", referenceValueSet);

        SearchResults searchResults = AdapterUtil.performSearch(adapterSearchCriteria);
        
        productCountList = searchResults.getSearchResults();
        return productCountList;
    }
    

	/**
	 * @param productId
	 * @param benCompSysId
	 * @param benefitList
	 */
	public void deleteBnftTierDetails(int productId, int benCompSysId,
			List benefitList) {
		for (Iterator iter = benefitList.iterator(); iter.hasNext();) {
			ProductTreeStandardBenefits productTreeStandardBenefits = (ProductTreeStandardBenefits) iter
					.next();
			deleteBnftTierDetails(productId, benCompSysId,productTreeStandardBenefits.getStandardBenefitId());			
		}
	}
	public void deleteBnftTierDetails(int productId, int benCompSysId,
			int benefitId){
		
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("productKey", new Integer(productId));
		referenceValueSet.put("componentKey", new Integer(benCompSysId));
		referenceValueSet.put("benefitKey", new Integer(benefitId));
		referenceValueSet.put("entityType", "PRODUCT");
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductComponentBO.class.getName(), "deleteTierDetails",
				referenceValueSet);

		try {
			SearchResults searchResults = AdapterUtil
					.performSearch(adapterSearchCriteria);
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
    
    
    /**
     * The method will persist the Rule info in the PROD_BNFT_RULE_OVRD table
     * @param productRuleIdBO
     * @param audit
     * @return
     * @throws AdapterException
     */
    public boolean persistProductBenefitRuleInfo (
            ProductRuleIdBO productRuleIdBO,Audit audit)
    throws AdapterException {
    	try {
			AdapterUtil.performUpdate(productRuleIdBO, audit
					.getUser());			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in " +
					"persistProductBenefitRuleInfo",
					exception);
		}
		return true;
    }
    
    /**
     * The method retrieves the rule associated with the benefit in a product
     * from the PROD_BNFT_RULE_OVRD table
     * @param productRuleIdBO
     * @return
     * @throws SevereException
     */
    public List getProductBenefitRule (
            ProductRuleIdBO productRuleIdBO) throws SevereException {
    	List ruleIdList = null;
		
		HashMap referenceValueSet = new HashMap();		
		referenceValueSet.put("productId",new Integer
		        (productRuleIdBO.getProductId()));			
		referenceValueSet.put("benefitComponentId",new Integer
		        (productRuleIdBO.getBenefitComponentId()));
		referenceValueSet.put("benefitId",new Integer
		        (productRuleIdBO.getBenefitId()));		
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductRuleIdBO.class.getName(),
				BusinessConstants.RETRIEVE_BENEFIT_RULEID, referenceValueSet);
		ruleIdList = AdapterUtil.performSearch(searchCriteria)
		.getSearchResults();
		return ruleIdList;

    }
    
    /**
     * This method retreives the Benefit Component Rule Types associated with a Product
     * @param productSysId
     * @throws AdapterException
     */
    public List getRuleListBC(int productSysId)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(RuleDetailBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getRuleListBCforProduct");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("entitySysId",new Integer(productSysId));
    	
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",e);
		}
		
		List resultList = searchResults.getSearchResults();
		
		return resultList;
	}
	
    /**
     * This method retreives the Benefit Rule Types associated with a Product
     * @param productSysId
     * @throws AdapterException
     */
	public List getRuleListBenefit(int productSysId)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(RuleDetailBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getRuleListBenefitForProduct");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("entitySysId",new Integer(productSysId));
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",e);
		}
		
		List resultList = searchResults.getSearchResults();
		
		return resultList;
	}
	
	/**
	 * retrieve benefit component with zero visible benefit in the product.
	 * @param productId
	 * @param benefitComponentIds
	 * @return
	 * @throws SevereException
	 */
	public List retrieveBenefitComponentWithZeroVisibleBenefits(int productId, List benefitComponentIds) throws SevereException{
		HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productKey", new Integer(productId));
        referenceValueSet.put("componentIds", benefitComponentIds);
        SearchCriteria adapterSearchCriteria = null;
        
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ProductComponentBO.class.getName(), WebConstants.GET_BC_WITH_ZERO_VISIBLE_BENEFITS,
                    referenceValueSet);
        List benefitComponentList = null;
        benefitComponentList = AdapterUtil.performSearch(adapterSearchCriteria)
            .getSearchResults();
		return benefitComponentList;
	}
	/**
	 * Method for getting the details of Um/Denail and Exclusion for product
	 * @param productSysId Product Id
	 * @param gnrtdRuleIDCondition List containg the details of selected rule type
	 * @return A list containing the rule details
	 * @throws SevereException
	 */
	public List getUmDenailAndExclusionDetailsForProduct(int productSysId,List gnrtdRuleIDCondition) throws SevereException{
		
		List tempResultList = null;
		
		HashMap hashmap = new HashMap();//Hash map normal rule locate criteria
		hashmap.put("productSysId",new Integer(productSysId));
		hashmap.put(WebConstants.EXTRACT_RULE_TYPE,gnrtdRuleIDCondition );
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.PRODUCT_EXTRACT_DENAIL_UM_RULE_QUERY, hashmap);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		tempResultList = searchResults.getSearchResults();
		
		return tempResultList;
	}
	/**
	 * Get the header rule details for product
	 * @param productSysId
	 * @return A list containing the details of header rule for product
	 * @throws SevereException
	 */
	public List getHeaderRuleDetailsForProduct(int productSysId) throws SevereException{
		
		List tempResultList =null;
		HashMap hashmap = new HashMap();//Hash map normal rule locate criteria
		hashmap.put("productSysId", new Integer(productSysId));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.PRODUCT_EXTRACT_HEADER_RULE, hashmap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);			
		tempResultList = searchResults.getSearchResults();
		
		return tempResultList;
				
	}
	/**
	 * Get the group rule details
	 * @param groupRuleIds List of group rule ids
	 * @return A list containing the group rule details
	 * @throws SevereException
	 */
	public List getGroupRuleDetailsForProduct(List groupRuleIds) throws SevereException{
		
		List tempResultListWithGroup =null;
		
		HashMap grpMap = new HashMap();//Hash map group rule locate criteria
		grpMap.put(WebConstants.SEARCH_KEY_FOR_GROUP_RULE,groupRuleIds);
		
		SearchCriteria searchCriteriaGrp = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.CONTRACT_GROUP_RULE, grpMap);
		SearchResults searchResultsGrp = AdapterUtil.performSearch(searchCriteriaGrp);
		tempResultListWithGroup = searchResultsGrp.getSearchResults();
		
		return tempResultListWithGroup;
		
	}
}