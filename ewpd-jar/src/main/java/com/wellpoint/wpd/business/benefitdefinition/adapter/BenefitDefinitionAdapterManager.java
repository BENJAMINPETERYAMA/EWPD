/*
 * BenefitDefinitionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitdefinition.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitTierDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.TierDefinitionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTierDefinitionAssnBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionAdapterManager {

//    private BusinessTransactionContext getTransactionContext(String operation,
//            String userId) {
//        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
//        btc.setBusinessTransactionType(operation);
//        btc.setBusinessTransactionUser(userId);
//        return btc;
//    }


    private AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(BusinessConstants.EWPD);
        return adapterServicesAccess;
    }


    /**
     * 
     * 
     * 
     * @param subObject
     * @param audit
     * @param insertFlag
     * @param standardBenefitAdapterServiceAccess
     * @return
     * @throws WPDException
     */
    public boolean createBenefitDefinitionObject(BenefitDefinitionBO subObject,
            Audit audit, boolean insertFlag) throws SevereException, AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());
        // TODO Remove hard coding whn actual standard benefit object gets
        // created.
        subObject.setBenefitDefinitionMasterType(BusinessConstants.STANDARD);
        subObject.setMandateMasterId(2);
        subObject.setCreatedUser(audit.getUser());

        subObject.setLastUpdatedUser(audit.getUser());
        subObject.setLastUpdatedTimestamp(audit.getTime());
        subObject.setCreatedTimestamp(audit.getTime());
        
        this.getAdapterService().persistObject(subObject,
                    subObject.getClass().getName(), btc);
        

        return true;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieve(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param benefitDefinition
     * @return
     * @throws SevereException
     */
    public Object retrieve(BenefitDefinitionBO benefitDefinition)
            throws SevereException {
        try {
            getAdapterService().retrieveObject(benefitDefinition,
                    benefitDefinition.getClass().getName());

        } catch (AdapterException adapterException) {
            logAdapterException(benefitDefinition, adapterException);
        }

        return benefitDefinition;
    }


    /**
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locate(LocateCriteria locateCriteria)
            throws SevereException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
//        List locateResultsList = new ArrayList();
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = (BenefitDefinitionLocateCriteria) locateCriteria;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_DEFINITION_BO);
        searchCriteria.setMaxSearchResultSize(benefitDefinitionLocateCriteria
                .getMaximumResultSize());
        HashMap refValSet = new HashMap();
        if(!benefitDefinitionLocateCriteria.getAdminPresent()){
        	searchCriteria.setSearchQueryName(BusinessConstants.getAssociatedBenefitDefinition);
        	refValSet.put(BusinessConstants.BENEFIT_MASTER_SYSTEM_ID, new Integer(
                    benefitDefinitionLocateCriteria.getBenefitMasterSystemId()));
        }else{
        	searchCriteria.setSearchQueryName("getDefIdAndAdminId");
        	refValSet.put(BusinessConstants.BENEFIT_DEFN_MASTER_KEY, new Integer(
                    benefitDefinitionLocateCriteria.getBenefitDefinitionMasterKey()));
        }
        
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
       
        
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        return locateResults;
    }


    /**
     * 
     * @param subObject
     * @param audit
     * @param standardBenefitAdapterServiceAccess	
     * @return
     * @throws SevereException
     */
    public boolean updateBenefitDefinitionObject(BenefitDefinitionBO subObject,
            Audit audit ,boolean insertFlag) throws SevereException, AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("edit");
        btc.setBusinessTransactionUser(audit.getUser());
        
        // Remove hard coding whn actual standard benefit object gets created.
        subObject.setBenefitDefinitionMasterKey(subObject
                .getBenefitDefinitionMasterKey());
        subObject.setBenefitDefinitionMasterType(BusinessConstants.STANDARD);
        subObject.setBenefitMasterSystemId(1);
        subObject.setMandateMasterId(2);
        subObject.setLastUpdatedUser(audit.getUser());
        subObject.setLastUpdatedTimestamp(audit.getTime());
        this.getAdapterService().persistObject(subObject, subObject.getClass()
                    .getName(), btc);
       
        return true;
    }


    /**
     * 
     * @param benefitDefinitionLocateCriteria
     * @param audit
     * @param adapterServicesAccess
     * @return true
     * @throws ServiceException,AdapterException
     */

    public boolean deleteBenefitDefinition(
            BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria,
            Audit audit, AdapterServicesAccess adapterServicesAccess) throws ServiceException,AdapterException {
        SearchResults searchResults = null;
//        LocateResults locateResults = new LocateResults();
//        List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        //benefitDefinitionMasterKey --> input value
        /*refValSet.put(BusinessConstants.benefitDefinitionMasterKey,
                new Integer(benefitDefinitionLocateCriteria
                        .getBenefitDefinitionMasterKey()));*/
        refValSet.put("benefitDefinitionKeysForDelete",
                benefitDefinitionLocateCriteria
                        .getBenefitDefinitionKeys());
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_DEFINITION_BO);
        searchCriteria.setMaxSearchResultSize(benefitDefinitionLocateCriteria
                .getMaximumResultSize());
        searchCriteria.setSearchQueryName(BusinessConstants.BNFT_DEFN_DELETE);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        searchCriteria.setReferenceValueSet(refValSet);
       
            searchResults = adapterServicesAccess.searchObject(searchCriteria);// new change 
       

        return true;
    }


    private void logAdapterException(Object obj,
            AdapterException adapterException) throws ServiceException {
        List errorParams = new ArrayList();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
                errorParams, adapterException);

    }


    private void logAdapterExceptionForSearch(String businessObjectName,
            String queryName, AdapterException adapterException)
            throws ServiceException {
        List errorParams = new ArrayList();
        errorParams.add(businessObjectName);
        errorParams.add(queryName);
        throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
                errorParams, adapterException);
    }


    /**
     *
     * @param locateCriteria
     * 
     * @return List
     * @throws WPDException
     */
    public List validateDateRange(BenefitDefinitionLocateCriteria locateCriteria)
            throws ServiceException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
  //      LocateResults locateResults = new LocateResults();
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_DEFINITION_BO);
        searchCriteria.setMaxSearchResultSize(locateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.VALIDATE_DATE_RANGE);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_MASTER_SYSTEM_ID, new Integer(
                locateCriteria.getBenefitMasterSystemId()));
        refValSet.put(BusinessConstants.EFFECTIVE_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getEffectiveDate()));
        refValSet.put(BusinessConstants.EXPIRY_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getExpiryDate()));
        refValSet.put(BusinessConstants.BENEFIT_DEFN_MASTER_KEY, new Integer(
                locateCriteria.getBenefitDefinitionMasterKey()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        return searchResults.getSearchResults();
    }


    /**
     *
     * @param locateCriteria
     * 
     * @return List
     * @throws WPDException
     */
    public List validateDateRange(
            BenefitAdministrationLocateCriteria locateCriteria)
            throws ServiceException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
    //    LocateResults locateResults = new LocateResults();
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_ADMINISTRATION_BO);
        searchCriteria.setMaxSearchResultSize(locateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.VALIDATE_DATE_RANGE);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BNFT_ADMN_KEY, new Integer(
                locateCriteria.getBenefitAdministrationKey()));
        refValSet.put(BusinessConstants.EFFECTIVE_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getEffectiveDate()));
        refValSet.put(BusinessConstants.EXPIRY_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getExpiryDate()));
        refValSet.put(BusinessConstants.BNFT_DEFN_KEY, new Integer(
                locateCriteria.getBenefitDefinitionKey()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        return searchResults.getSearchResults();
    }


    /**
     * @param locateCriteria
     * 
     * @return List
     * @throws WPDException
     */
    public List checkBenefitDefinitionDateInBenefitAdministration(
            BenefitDefinitionLocateCriteria locateCriteria)
            throws ServiceException {

        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_DEFINITION_BO);
        searchCriteria.setMaxSearchResultSize(locateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.checkBenefitDefinitionDateInBenefitAdministration);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.EFFECTIVE_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getEffectiveDate()));
        refValSet.put(BusinessConstants.EXPIRY_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getExpiryDate()));
        refValSet.put(BusinessConstants.BENEFIT_DEFN_MASTER_KEY, new Integer(
                locateCriteria.getBenefitDefinitionMasterKey()));

        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        return searchResults.getSearchResults();

    }
    /**
     * 
     * @param locateCriteria
     * 
     * @return List
     * @throws WPDException
     */
    public List checkIfBenefitAdminAvailable(
            BenefitDefinitionLocateCriteria locateCriteria)
            throws ServiceException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_DEFINITION_BO);
        searchCriteria.setMaxSearchResultSize(locateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.checkIfBenefitAdminAvailable);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_DEFN_MASTER_KEY, new Integer(
                locateCriteria.getBenefitDefinitionMasterKey()));

        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        return searchResults.getSearchResults();
    }
    
    /**
     * The method will retreive all the Tier Definitions
     * for product and benefit     
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locateTierDefinition(LocateCriteria locateCriteria)
    throws SevereException {
		Logger
		        .logInfo("StandardBenefitAdapterManager - Entering " +
		        		"locateTierDefinition()");
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		TierDefinitionLocateCriteria tierDefinitionLocateCriteria = 
		    (TierDefinitionLocateCriteria) locateCriteria;
		HashMap refValSet = new HashMap();      
		SearchCriteria searchCriteria = null;
		if("benefit".equals(tierDefinitionLocateCriteria.getAction())){ // for benefit
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        BusinessConstants.BENEFIT_TIER_DEFINITION_BO,
			        BusinessConstants.LOCATE_TIER_DEFINITIONS, refValSet);
		}else if("product".equals(tierDefinitionLocateCriteria.getAction())){ // for product		   
		    refValSet.put("benefitDefinitionId",new Integer
			        (tierDefinitionLocateCriteria.getBenefitDefinitionId()));		
		     searchCriteria = AdapterUtil.getAdapterSearchCriteria(
			        BusinessConstants.PRODUCT_TIER_DEFINITION_BO,
			       BusinessConstants.RETRIEVE_TIERDEFN_BENEFIT, refValSet);		    
		}	
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
		        .setTotalResultsCount(searchResults.getSearchResultCount());
		Logger
		        .logInfo("BenefitDefinitionAdapterManager - Returning " +
		        		"locateTierDefinition()");
		return locateResults; 
    }
    
    /**
     * The method will insert the Tier Definitions for a particular BenefitDefinition 
     * in the BNFT_TIER_DEFN_ASSN table
     * @param benefitTierDefinitionAssnBO
     * @param audit
     * @throws AdapterException
     */
    public void persistBenefitTierDefinition(	        
			BenefitTierDefinitionAssnBO benefitTierDefinitionAssnBO, Audit audit)
	throws AdapterException {	   
		try {
			AdapterUtil.performInsert(benefitTierDefinitionAssnBO, audit
					.getUser());			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in " +
					"persistBenefitTierDefinition",
					exception);
		}
	}
    
    /**
     * The method will delete the Tier Definitions for a particular BenefitDefinition 
     * in the BNFT_TIER_DEFN_ASSN table
     * @param benefitTierDefinitionAssnBO
     * @param audit
     * @throws AdapterException
     */
    public void deleteBenefitTierDefinition(	        
			BenefitTierDefinitionAssnBO benefitTierDefinitionAssnBO, Audit audit)
	throws AdapterException {	   
		try {
		    BenefitTierDefinitionAssnBO bnftTierDefinitionAssnBO 
		    		= new BenefitTierDefinitionAssnBO();
		    bnftTierDefinitionAssnBO.setBenefitDefinitionId(benefitTierDefinitionAssnBO
		            .getBenefitDefinitionId());
		    AdapterUtil.performRemove(bnftTierDefinitionAssnBO, audit
					.getUser());   //delete the existing benefit tier definitions			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in " +
					"deleteBenefitTierDefinition",
					exception);
		}
	}
    
    /**
     * The method will retrieve the Tier Definitions associated
     * with a particular Benefit
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locateBenefitTierDefinition(LocateCriteria locateCriteria)
    throws SevereException {
		Logger
		        .logInfo("BenefitDefinitionAdapterManager - Entering " +
		        		"locateBenefitTierDefinition()");
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria = 
		    (BenefitTierDefinitionLocateCriteria) locateCriteria;
		HashMap refValSet = new HashMap();  
		refValSet.put(BusinessConstants.BENEFIT_MASTER_SYSTEM_ID,new Integer
		        		(bnftTierDefinitionLocateCriteria.getBenefitId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        BusinessConstants.BENEFIT_DEFINITION_BO,
		        BusinessConstants.GET_BNFT_TIER_DEFNS, refValSet);	
		searchCriteria.setMaxSearchResultSize(BusinessConstants.TIER_DEFN_SIZE);
		searchResults = AdapterUtil.performSearch(searchCriteria);	
		locateResults.setLocateResults(searchResults.getSearchResults());		
		locateResults
		        .setTotalResultsCount(searchResults.getSearchResultCount());
		Logger
		        .logInfo("BenefitDefinitionAdapterManager - Returning " +
		        		"locateBenefitTierDefinition()");
		return locateResults;
    }
    
    
    /**
     * The method will retrieve the Tier Definitions associated
     * with a particular BenefitDefinition
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locateBenefitTierDefinitionForBenDefn(LocateCriteria locateCriteria)
    throws SevereException {
		Logger
		        .logInfo("BenefitDefinitionAdapterManager - Entering " +
		        		"locateBenefitTierDefinitionForBenDefn()");
		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();
		BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria = 
		    (BenefitTierDefinitionLocateCriteria) locateCriteria;
		HashMap refValSet = new HashMap();  
		refValSet.put(BusinessConstants.BENEFIT_DEFN_MASTER_KEY,new Integer
		        		(bnftTierDefinitionLocateCriteria.getBenefitDefinitionId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        BusinessConstants.BENEFIT_DEFINITION_BO,
		        BusinessConstants.RETRIEVE_BNFT_TIER_DEFNS, refValSet);	
		searchCriteria.setMaxSearchResultSize(BusinessConstants.TIER_DEFN_SIZE);
		searchResults = AdapterUtil.performSearch(searchCriteria);	
		locateResults.setLocateResults(searchResults.getSearchResults());		
		locateResults
		        .setTotalResultsCount(searchResults.getSearchResultCount());
		Logger
		        .logInfo("BenefitDefinitionAdapterManager - Returning " +
		        		"locateBenefitTierDefinitionForBenDefn()");
		return locateResults;
    }
}