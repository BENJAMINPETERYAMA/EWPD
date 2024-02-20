/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.SelectedQuestionaireLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitDeleteLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitSourceDestinationLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitViewAllLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefit.bo.TermSpsBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13259
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitAdapterManager {

    private BusinessTransactionContext getTransactionContext(String operation,
            String userId) {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(operation);
        btc.setBusinessTransactionUser("testUser");
        return btc;
    }


    public AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }


    /*
     * Function to create a Standard benefit
     *  
     */
    public boolean createStandardBenefit(StandardBenefitBO standardBenefitBO,
            Audit audit, boolean insertFlag,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering createStandardBenefit(): Standard Benefit Create");
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        AdapterUtil.performInsert(standardBenefitBO, audit.getUser(),
                standardBenefitAdapterServiceAccess);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning createStandardBenefit(): Standard Benefit Create");
        return true;
    }

    /**
     * Method to retrieve the Benefit Names matching the Benefit Component name
     * @param standardBenefitBO
     * @return
     * @throws SevereException
     */
    public List checkUniqueBenefitName(
    		StandardBenefitBO standardBenefitBO) throws SevereException {
        HashMap criteriaMap = new HashMap();
        SearchCriteria searchCriteria;
        SearchResults searchResults;
        if(null != standardBenefitBO.getLobList() && null != standardBenefitBO.getBeList() 
        		&& null != standardBenefitBO.getBgList() && null != standardBenefitBO.getMarketBusinessUnitList()){
            if(standardBenefitBO.getLobList().contains("ALL") 
            		&& standardBenefitBO.getBgList().contains("ALL") 
    					&& standardBenefitBO.getBeList().contains("ALL")
						&& standardBenefitBO.getMarketBusinessUnitList().contains("ALL")){
                criteriaMap.put("benefitComponentName", standardBenefitBO.getBenefitIdentifier());
                searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                        BenefitBO.class.getName(),
                        "benefitComponentNameSearchForAll", criteriaMap);
                searchResults  = AdapterUtil.performSearch(searchCriteria);
                if (searchResults != null) {
                    return searchResults.getSearchResults();
                }
            }
            else{
                criteriaMap.put("benefitComponentName", standardBenefitBO.getBenefitIdentifier());

                criteriaMap.put("lobList", standardBenefitBO.getLobList());
                criteriaMap.put("businessEntityList", standardBenefitBO
                        .getBeList());
                criteriaMap.put("businessGroupList", standardBenefitBO
                        .getBgList());
                criteriaMap.put("marketBusinessUnitList", standardBenefitBO.getMarketBusinessUnitList()); 
                searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                        BenefitBO.class.getName(),
                        "benefitComponentNameSearch", criteriaMap);
                searchResults  = AdapterUtil.performSearch(searchCriteria);
                if (searchResults != null) {
                    return searchResults.getSearchResults();
                }
            }
        }
        
        return null;

    }
    /*
     * Function to create the universe values
     *  
     */
    public boolean createUniverse(UniverseBO universeBO,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException {
        //BusinessTransactionContext btc = new
        // BusinessTransactionContextImpl();
        //btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        //btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        AdapterUtil.performInsert(universeBO, BusinessConstants.TESTUSER,
                standardBenefitAdapterServiceAccess);
        return true;
    }


    /*
     * to update the datas regarding term,qualifier,PVA and datatype and store
     * them to DB
     *  
     */
    public boolean deleteUniverse(UniverseBO universeBO,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException {
        //	BusinessTransactionContext btc = new
        // BusinessTransactionContextImpl();
        //	btc.setBusinessTransactionType("delete");
        //	btc.setBusinessTransactionUser("USER");
        AdapterUtil.performRemove(universeBO, BusinessConstants.TESTUSER,
                standardBenefitAdapterServiceAccess);
        return true;
    }


    /*
     * Function to update the standard benefit
     *  
     */
    public boolean updateStandardBenefit(StandardBenefitBO standardBenefitBO,
            Audit audit, boolean insertFlag,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering updateStandardBenefit(): Standard Benefit Update");
        //	BusinessTransactionContext btc = new
        // BusinessTransactionContextImpl();
        //	btc.setBusinessTransactionType("edit");
        //	btc.setBusinessTransactionUser("USER");
        AdapterUtil
                .performUpdate(standardBenefitBO, BusinessConstants.TESTUSER,
                        standardBenefitAdapterServiceAccess);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning updateStandardBenefit(): Standard Benefit Update");
        return true;
    }

    /*
     * Function to update the standard benefit
     * Created on 6th March,2008
     *  
     */
    public boolean updateStandardBenefit(StandardBenefitBO standardBenefitBO)
            throws AdapterException,SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering updateStandardBenefit(): Standard Benefit Update");
        AdapterUtil
                .performUpdate(standardBenefitBO, BusinessConstants.TESTUSER);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning updateStandardBenefit(): Standard Benefit Update");
        return true;
    }

    /*
     * Function to retrieve the standard benefit
     *  
     */
    public Object retrieveSB(StandardBenefitBO standardBenefitBO)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering retrieveSB(): Standard Benefit Retrieve");
        AdapterUtil.performRetrieve(standardBenefitBO);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning retrieveSB(): Standard Benefit Retrieve");
        return standardBenefitBO;
    }


    /*
     * Function to search for a particular standard benefit using the key
     *  
     */
    public List searchUniverse(UniverseBO universeBO) throws SevereException {
        HashMap criteriaValues = new HashMap();

        criteriaValues.put("standardBenefitKey", new Integer(universeBO
                .getStandardBenefitKey()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(universeBO
                .getClass().getName(), "UniverseSearch", criteriaValues, 999999);
        SearchResults searchResults = performSearch(searchCriteria);

        if (searchResults.getSearchResultCount() > 0) {
            return searchResults.getSearchResults();
        }
        return null;

    }


    /*
     * Function to search for a particular datatype using the key
     *  
     */
    public List searchDatatype(
            StandardBenefitDatatypeBO standardBenefitDatatypeBO)
            throws SevereException {
        HashMap criteriaValues = new HashMap();

        criteriaValues.put("standardBenefitKey", new Integer(
                standardBenefitDatatypeBO.getStandardBenefitKey()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                standardBenefitDatatypeBO.getClass().getName(),
                "searchForDatatype", criteriaValues, 999999);
        SearchResults searchResults = performSearch(searchCriteria);
        if (null != searchResults) {
            if (searchResults.getSearchResultCount() > 0) {
                return searchResults.getSearchResults();
            }
        }
        return null;

    }


    /*
     * Function to check for duplicate values using the key
     *  
     */
    public Object checkForDuplicateValues(StandardBenefitBO standardBenefitBO)
            throws SevereException {
        HashMap criteriaMap = getCriteriaForStandardBenefitBO(standardBenefitBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                standardBenefitBO.getClass().getName(),
                "searchForDuplicateValues", criteriaMap, 999999);
        SearchResults searchResults = performSearch(searchCriteria);

        if (searchResults.getSearchResultCount() > 0) {
            return standardBenefitBO;
        }
        return null;
    }


    /*
     * Function to generate hash map to put the criteria values to search for a
     * particular standard benefit
     *  
     */
    private HashMap getCriteriaForStandardBenefitBO(
            StandardBenefitBO standardBenefitBO) {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("benefitIdentifier", standardBenefitBO
                .getBenefitIdentifier());
        return criteriaValues;
    }


    /*
     * Function to generate the criteria values to search for a particular
     * standard benefit
     *  
     */
    private SearchCriteria getAdapterSearchCriteria(String businessObjectName,
            String queryName, HashMap criteriaValues, int resultSize) {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(criteriaValues);
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchQueryName(queryName);
        searchCriteria.setMaxSearchResultSize(resultSize);
        searchCriteria.setSearchDomain("medical");
        return searchCriteria;
    }


    /*
     * Function to perform the search for a particular standard benefit
     *  
     */
    private SearchResults performSearch(SearchCriteria searchCriteria)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering performSearch()");
        SearchResults searchResults = null;
        searchResults = AdapterUtil.performSearch(searchCriteria);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning performSearch()");
        return searchResults;
    }


    /**
     * Function to perform the search for a particular standard benefit
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     * @throws WPDException
     */
    public LocateResults locateStandardBenefit(LocateCriteria locateCriteria)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering locateStandardBenefit(): Standard Benefit Locate");
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        StandardBenefitLocateCriteria standardBenefitLocateCriteria = (StandardBenefitLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();

        setValuesToMap(refValSet, standardBenefitLocateCriteria);
        int maxResultSize = standardBenefitLocateCriteria
                .getMaximumResultSize();
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.STANDARD_BENEFIT_BO,
                BusinessConstants.LOCATE_STANDARD_BENEFIT, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning locateStandardBenefit(): Standard Benefit Locate");
        return locateResults;
    }


    /**
     * Function to perform the search for a particular standard benefit
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     * @throws WPDException
     */
    public LocateResults locateStandardBenefitRuleId(
            LocateCriteria locateCriteria) throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering locateStandardBenefit(): Standard Benefit Locate");
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        RuleLocateCriteria criteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        if(null!=criteria.getSearchString()&& !("").equals(criteria.getSearchString())){
        refValSet.put("searchString", "%"+(criteria.getSearchString()).trim().toUpperCase()+"%");
        }else{
        	refValSet.put("searchString", "%%");
        }
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_BO, BusinessConstants.LOCATE_RULEID,
                refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning locateStandardBenefit(): Standard Benefit Locate");
        return locateResults;
    }
    /**
     * Function to perform the search for a particular standard benefit
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     * @throws WPDException
     */
    public LocateResults filterRuleIdLocate(
    		RuleLocateCriteria locateCriteria) throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering locateStandardBenefit(): Standard Benefit Locate");
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        RuleLocateCriteria criteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        if(null!=locateCriteria.getSearchRuleID())
        	refValSet.put("ruleId", "%"+locateCriteria.getSearchRuleID()+"%");
        else
        	refValSet.put("ruleId", "%");
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_BO, BusinessConstants.LOCATE_FILTERED_RULEID,
                refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        if(0== searchResults.getSearchResultCount()){
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    BusinessConstants.RULE_BO, BusinessConstants.LOCATE_UNFILTERED_RULEID,
                    refValSet, maxResultSize);
            searchResults = AdapterUtil.performSearch(searchCriteria);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning locateStandardBenefit(): Standard Benefit Locate");
        return locateResults;
    }
    
    /**
     * Function to perform the search for check in validations:rule
     * 
     * @param entityId key,entityType
     * @return locateResultsList
     * @throws SevereException
     * @throws WPDException
     */
    public List validateBenefitRule(
            int entityId,String entityType,String ruleId) throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering locateStandardBenefit(): Standard Benefit Locate");
        SearchResults searchResults = null;
        List locateResultsList = null;
        HashMap refValSet = new HashMap();
        refValSet.put("benefitKey", new Integer(entityId));
        refValSet.put("entityType", entityType);
        refValSet.put("ruleId", ruleId);
        
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_BO, BusinessConstants.LOCATE_RULE_CHECK,
                refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResultsList=searchResults.getSearchResults();
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning locateStandardBenefit(): Standard Benefit Locate");
        return locateResultsList;
    }

    /**
     * Function to perform the search for all version of a particular standard
     * benefit
     * @param locateCriteria
     * @return list
     * @throws SevereException
     * @throws WPDException
     */
    public LocateResults locateStandardBenefit(
            StandardBenefitViewAllLocateCriteria locateCriteria)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering locateStandardBenefit(): Standard Benefit View All Locate");
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        refValSet.put("standardBenefitKey", new Integer(locateCriteria
                .getBenefitkey()));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO",
                        "locateAllVersion", refValSet);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResults.setLocateResults(searchResults.getSearchResults());
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning locateStandardBenefit(): Standard Benefit View All Locate");
        return locateResults;
    }


    /**
     * Function to set the criteria values to a map
     * 
     * @param map
     * @param standardBenefitLocateCriteria
     */
    private void setValuesToMap(HashMap map,
            StandardBenefitLocateCriteria standardBenefitLocateCriteria) {
        String benefitIdentifier = standardBenefitLocateCriteria.getName();
        if (benefitIdentifier != null && benefitIdentifier.trim().length() > 0) {
            benefitIdentifier = "%"
                    + benefitIdentifier.toUpperCase()
                    + "%";
        } else
            benefitIdentifier = "%";
        map.put("benefitIdentifier", benefitIdentifier);
        map.put("lobCodeList", standardBenefitLocateCriteria.getLobCodeList());
        map.put("businessEntityCodeList", standardBenefitLocateCriteria
                .getBusinessEntityCodeList());
        map.put("businessGroupCodeList", standardBenefitLocateCriteria
                .getBusinessGroupCodeList());
        map.put("marketBusinessUnitList", standardBenefitLocateCriteria
        		.getMarketBusinessUnitList() );
        map
                .put("termCodeList", standardBenefitLocateCriteria
                        .getTermCodeList());
        map.put("qualifierCodeList", standardBenefitLocateCriteria
                .getQualifierCodeList());
        map.put("providerArrangementCodeList", standardBenefitLocateCriteria
                .getProviderArrangementCodeList());
        map.put("dataTypeCodeList", standardBenefitLocateCriteria
                .getDataTypeCodeList());
        if(null!=standardBenefitLocateCriteria.getBenefitCategory() && 
        		!standardBenefitLocateCriteria.getBenefitCategory().equals(""))
	        map.put("benefitCategory", standardBenefitLocateCriteria
	        		.getBenefitCategory());
        else
        	map.put("benefitCategory", "%");
        if(null!=standardBenefitLocateCriteria.getBenefitTypeCode() &&
        		!standardBenefitLocateCriteria.getBenefitTypeCode().equals(""))
        	map.put("benefitTypeCodeList", standardBenefitLocateCriteria
        			.getBenefitTypeCode());
        else
        	map.put("benefitTypeCodeList","%");
    }


    /**
     * @param locateCriteria
     * @return
     */
    public SearchResults isStandardBenefitPresent(
            StandardBenefitDeleteLocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        StandardBenefitDeleteLocateCriteria standardBenefitDeleteLocateCriteria = (StandardBenefitDeleteLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        setValuesToMap(refValSet, standardBenefitDeleteLocateCriteria);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO",
                "locateStandardBenefitForDelete", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;
    }


    /**
     * Function to set the criteria values to a map
     * @param map
     * @param standardBenefitDeleteLocateCriteria
     */
    private void setValuesToMap(
            HashMap map,
            StandardBenefitDeleteLocateCriteria standardBenefitDeleteLocateCriteria) {
    	map.put("standardBenefitKey", new Integer(
                standardBenefitDeleteLocateCriteria.getStandardBenefitKey()));
        //setting the action ie DELETE or CHECK IN to hashmap
        map.put("action",standardBenefitDeleteLocateCriteria.getAction());
    }


    /**
     * @param standardBenefitDeleteLocateCriteria
     * @return
     */

    public SearchResults removeStandardBenefit(
            StandardBenefitDeleteLocateCriteria standardBenefitDeleteLocateCriteria,AdapterServicesAccess access)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering removeStandardBenefit(): Standard Benefit Delete");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        setValuesToMap(refValSet, standardBenefitDeleteLocateCriteria);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO",
                "deleteStandardBenefit", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria,access);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning removeStandardBenefit(): Standard Benefit Delete");
        return searchResults;
    }


    /**
     * Function to copy one benefit to other
     * @param criteria
     * @param copyAction
     */
    public SearchResults copyStandardBenefit(
            StandardBenefitSourceDestinationLocateCriteria criteria,
            String copyAction,AdapterServicesAccess access) throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering copyStandardBenefit(): Standard Benefit Copy");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("sourceKey", new Integer(criteria.getSourceKey()));
        refValSet.put("destinationKey", new Integer(criteria
                .getDestinationKey()));
        refValSet.put("user", criteria.getCreatedUser());
        refValSet.put("copyAction", copyAction);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO",
                "copyOneBenefitToAnother", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria,access);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning copyStandardBenefit(): Standard Benefit Copy");
        return searchResults;
    }


    /**
     * Function to copy one benefit to other for checkout
     * @param criteria
     */
    public SearchResults copyStandardBenefitForCheckOut(
            StandardBenefitSourceDestinationLocateCriteria criteria,AdapterServicesAccess access)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering copyStandardBenefitForCheckOut(): Standard Benefit Copy for CheckOut");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("sourceKey", new Integer(criteria.getSourceKey()));
        refValSet.put("destinationKey", new Integer(criteria
                .getDestinationKey()));
        refValSet.put("user", criteria.getCreatedUser());

        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO",
                "copyOneBenefitToAnotherForCheckOut", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria,access);
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning copyStandardBenefitForCheckOut(): Standard Benefit Copy for CheckOut");
        return searchResults;
    }


    /**
     * Retrieves the latest StandardBenefitBO corresponding to the
     * StandardBenefitBOName of the StandardBenefitBO.
     * @param name
     * @param lob,businessEntity,businessGroup
     * @return StandardBenefitBO.
     * @throws ServiceException
     */
    public StandardBenefitBO retrieveStandardBenefitLatestVersion(String name,
            List lob, List businessEntity, List businessGroup, List marketBusinessUnit)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering retrieveStandardBenefitLatestVersion(): Standard Benefit Retrieve");
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("benefitIdentifier", name);
        criteriaMap.put("lobCodeList", lob);
        criteriaMap.put("businessEntityCodeList", businessEntity);
        criteriaMap.put("businessGroupCodeList", businessGroup);
        criteriaMap.put("marketBusinessUnitList", marketBusinessUnit);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(), "retrieveLatestVersion",
                criteriaMap, 999999);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (StandardBenefitBO) searchResults.getSearchResults().get(0);
        }
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning retrieveStandardBenefitLatestVersion(): Standard Benefit Retrieve");
        return null;

    }


    /**
     * Retrieves the latest StandardBenefitBO corresponding to the
     * StandardBenefitBOName of the StandardBenefitBO.
     * @param name
     * @param lob,businessEntity,businessGroup
     * @return StandardBenefitBO.
     * @throws ServiceException
     */
    public StandardBenefitBO retrieveStandardBenefitLatestVersionNumber(
            String name, List lob, List businessEntity, List businessGroup, List marketBusinessUnit)
            throws SevereException {

        HashMap criteriaMap = new HashMap();
        criteriaMap.put("benefitIdentifier", name);
        criteriaMap.put("lobCodeList", lob);
        criteriaMap.put("businessEntityCodeList", businessEntity);
        criteriaMap.put("businessGroupCodeList", businessGroup);
        criteriaMap.put(" marketBusinessUnitList", marketBusinessUnit);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(),
                "retrieveLatestVersionNumber", criteriaMap, 999999);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (StandardBenefitBO) searchResults.getSearchResults().get(0);
        }
        return null;

    }


    /**
     * Function to check for term update
     * @param standardBenefitLocateCriteria
     */
    public SearchResults checkTermUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("standardBenefitKey", new Integer(
                standardBenefitLocateCriteria.getStandardBenefitKey()));

        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO",
                "checkTermUpdate", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;

    }

//** Change: Qualifier Aggregate
    /**
     * Function to check for qualifier update
     * @param standardBenefitLocateCriteria
     */
    public SearchResults checkQualifierUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("standardBenefitKey", new Integer(
                standardBenefitLocateCriteria.getStandardBenefitKey()));

        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO",
                "checkQualifierUpdate", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;

    }
    //**end


    /**
     * Function to check for pva update
     * @param standardBenefitLocateCriteria
     */
    public SearchResults checkPVAUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("standardBenefitKey", new Integer(
                standardBenefitLocateCriteria.getStandardBenefitKey()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO",
                "checkPVAUpdate", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;
    }


    /**
     * Function to check for datatype update
     * @param StandardBenefitLocateCriteria
     */
    public SearchResults checkDataTypeUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("standardBenefitKey", new Integer(
                standardBenefitLocateCriteria.getStandardBenefitKey()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO",
                "checkDataTypeUpdate", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;
    }


//  Change started on 15th Nov
    /**
     * Function to check for the duplicate domain. This function checks for the abc-ALL-ALL condition.
     * @param String,List,int
     */
    public boolean searchForDuplicateDomain(String entityName,List lob, List busEntity, List busGroup, List marketBus, int entityParentId) throws SevereException {
        HashMap valueSet = new HashMap();
        valueSet.put("benefitIdentifier", entityName);
        valueSet.put("entityParentId", new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(), "findDuplicate", valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if(searchResults.getSearchResults().size() > 0){
            List searchResultList = searchResults.getSearchResults();
            if(searchResultList.size() > 0){
                Iterator iterator = searchResultList.iterator();
                String lineOfBusiness = null;
                String businessEntity = null;
                String businessGroup = null;
                String marketBusinessUnit = null;
                boolean lobFlag = true,busEntityFlag = true,busGroupFlag = true,marketBusinessUnitFlag=true;
                
                while(iterator.hasNext()){
                    StandardBenefitBO benefitBO = (StandardBenefitBO)iterator.next();
                    lineOfBusiness = benefitBO.getLineOfBusiness();
                    businessEntity = benefitBO.getBusinessEntity();
                    businessGroup = benefitBO.getBusinessGroup();
                    marketBusinessUnit = benefitBO.getMarketBusinessUnit();
                    
                    if(lineOfBusiness.equals("ALL")){
                        if(!(lob.contains("ALL"))){
                            lobFlag = false;
                        }
                    }if(businessEntity.equals("ALL")){
                        if(!(busEntity.contains("ALL"))){
                            busEntityFlag = false;
                        }
                    }if(businessGroup.equals("ALL")){
                        if(!(busGroup.contains("ALL"))){
                            busGroupFlag = false;
                        }
                    }if(marketBusinessUnit.equals("ALL")){
                        if(!(marketBus.contains("ALL"))){
                        	marketBusinessUnitFlag = false;
                        }
                    }
                    if(lob.contains("ALL")){
                        if(!(lineOfBusiness.equals("ALL"))){
                            lobFlag = false;
                        }
                    }if(busEntity.contains("ALL")){
                        if(!(businessEntity.equals("ALL"))){
                            busEntityFlag = false;
                        }
                    }if(busGroup.contains("ALL")){
                        if(!(businessGroup.equals("ALL"))){
                            busGroupFlag = false;
                        }
                    }if(marketBus.contains("ALL")){
                        if(!(marketBusinessUnit.equals("ALL"))){
                        	marketBusinessUnitFlag = false;
                        }
                    }
                    if(lob.contains(lineOfBusiness))
                    	lobFlag = false;
                    if(busEntity.contains(businessEntity))
                    	busEntityFlag = false;
                    if(busGroup.contains(businessGroup))
                    	busGroupFlag = false;
                    if(marketBus.contains(marketBusinessUnit))
                    	marketBusinessUnitFlag = false;                    
                    if(!lobFlag && !busEntityFlag && !busGroupFlag && !marketBusinessUnitFlag){
                        return false;
                    }
                }
            } 
        }
        return true;
    }

    /**
     * Function to search for duplicate domain that contain ALL
     * @param entityName,entityParentId
     */
    public boolean searchForDomainAll(String entityName,int entityParentId) throws SevereException {
        HashMap valueSet = new HashMap();
        valueSet.put("benefitIdentifier", entityName);
        valueSet.put("entityParentId", new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(), "findDuplicateAll", valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if(searchResults.getSearchResults().size() > 0)
            return false;
        else
            return true;
    }

    /**
     * Function to search for duplicate domain that does not contain ALL
     * @param entityName,lob,busEntity,busGroup,entityParentId
     * @param string
     */
    public boolean searchForDomainNotContainingAll(String entityName,List lob, List busEntity, List busGroup, List marketBus, int entityParentId) throws SevereException {
        HashMap valueSet = new HashMap();
        valueSet.put("benefitIdentifier", entityName);
        valueSet.put("lobCodeList", lob);
        valueSet.put("businessEntityCodeList", busEntity);
        valueSet.put("businessGroupCodeList", busGroup);
        valueSet.put("marketBusinessUnitList", marketBus);
        valueSet.put("entityParentId", new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(), "findDuplicateNotContainingAll", valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if(searchResults.getSearchResults().size() > 0)
            return false;
        else
            return true;
        
        
       
    }
    /**
     * change made on 17th Dec 2007 to get the benefits present in any domain containing ALL wih the same name
     * 
     * @param StandardBenefitLocateCriteria
     * @param string
     */
    public boolean searchBenefitsWithAllDomains(String entityName,List lob, List busEntity, List busGroup, List marketBus, int entityParentId) throws SevereException {
        HashMap valueSet = new HashMap();
        valueSet.put("benefitIdentifier", entityName);
        valueSet.put("entityParentId", new Integer(entityParentId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                StandardBenefitBO.class.getName(), "findBenefitsWithAllDomains", valueSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if(searchResults.getSearchResults().size() > 0){
            List searchResultList = searchResults.getSearchResults();
            if(searchResultList.size() > 0){
                Iterator iterator = searchResultList.iterator();
                String lineOfBusiness = null;
                String businessEntity = null;
                String businessGroup = null;
                String marketBusinessUnit = null;
                while(iterator.hasNext()){
                    StandardBenefitBO benefitBO = (StandardBenefitBO)iterator.next();
                    lineOfBusiness = benefitBO.getLineOfBusiness();
                    businessEntity = benefitBO.getBusinessEntity();
                    businessGroup = benefitBO.getBusinessGroup();
                    marketBusinessUnit = benefitBO.getMarketBusinessUnit();
                    if("ALL".equals(lineOfBusiness) && ("ALL").equals(businessEntity) && ("ALL".equals(businessGroup) &&("ALL".equals(marketBusinessUnit)))){
                        return false;
                    }else if(lob.contains(lineOfBusiness) && busGroup.contains(businessGroup) && busEntity.contains(businessGroup) && marketBus.equals(marketBusinessUnit)){
                        return false;
                    }else{
                        if(lob.contains(lineOfBusiness) && "ALL".equals(businessEntity) && "ALL".equals(businessGroup)){
                            return false;                            
                        }
                        if("ALL".equals(lob) && busEntity.contains(businessEntity) && "ALL".equals(businessGroup)){
                            return false;                            
                        }
                        if("ALL".equals(lob) && "ALL".equals(businessEntity) && busGroup.contains(businessGroup)){
                            return false;                            
                        }
                    }
                }
            } 
            return true;        
        }
        else
            return true;
        
        
       
    }
     
    // 15th Nov Change ends


    // **Change**
    /**
     * Function to retrieve desc
     * @param standardBenefitLocateCriteria
     */
    public SearchResults retrieveDescForAggregateTerm(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("termCodeList", standardBenefitLocateCriteria
                .getTermCodeList());
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO",
                "getTermCodeDesc", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;

    }
    // **End**
    
//Change: Qualifier Aggregate
    /**
     * Function to retrieve desc
     * @param standardBenefitLocateCriteria
     */
    public SearchResults retrieveDescForAggregateQualifier(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("qualifierCodeList", standardBenefitLocateCriteria
                .getQualifierCodeList());
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO",
                "getQualifierCodeDesc", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;
    }
    // **End**
    /**
	 * Locate the list of Catalogs with the corresponding searchCriteria
	 * @param benefitLevelBO
	 * @return List of state code
	 * @throws SevereException
	 */
	public List locateReferance(BenefitLevelBO benefitLevelBO) throws SevereException {
		Logger.logInfo("Entering the method for locating refData");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;
		int benefitSysId  = benefitLevelBO.getBenefitSysId();
		criteriaValues.put("benefitSysId", new Integer(benefitSysId));
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(BenefitLevelBO.class
				.getName(), "getReference", criteriaValues);
		searchCriteria.setMaxSearchResultSize(51);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for locating refData");
		return searchResults.getSearchResults();
	}
	 /**
	 * Locate the list of Catalogs with the corresponding searchCriteria
	 * @param benefitLevelBO
	 * @return List
	 * @throws SevereException
	 */
	public List locateStateFundingArrangement(BenefitLevelBO benefitLevelBO) throws SevereException {
		Logger.logInfo("Entering the method for locating refData");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		HashMap criteriaValues = new HashMap();
		SearchResults searchResults = null;
		int benefitSystemId  = benefitLevelBO.getBenefitSysId();
		criteriaValues.put("benefitSystemId", new Integer(benefitSystemId));
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(BenefitMandateBO.class
				.getName(), "getStateAndFundArrangForRefData", criteriaValues);
		searchCriteria.setMaxSearchResultSize(51);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for locating refData");
		return searchResults.getSearchResults();
	}
	
	/**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            AdministrativeOptionLocateCriteria locateCriteria)
            throws SevereException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        
        searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        
        searchCriteria.setSearchDomain("medical");
        HashMap refValSet = new HashMap();
        if(locateCriteria.getEntityId() == 0 ||locateCriteria.getBenefitComponentId() == 0 ){
        	searchCriteria
            .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO");
        	searchCriteria.setSearchQueryName("getAssociatedBenefitAdministration");
        	refValSet.put("benefitAdminSystemId", new Integer(locateCriteria
                .getBenefitAdministrationSystemId()));
        }
        else{
        	searchCriteria
            .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO");
        	refValSet.put("benefitAdminSystemId", new Integer(locateCriteria
                    .getBenefitAdministrationSystemId()));
        	searchCriteria.setSearchQueryName("getAssociatedBenefitAdministrations");
        	refValSet.put("entityId", new Integer(locateCriteria
                .getEntityId()));
        	refValSet.put("entityType", new String(locateCriteria
                .getEntityType()));
        	refValSet.put("benefitComponentId", new Integer(locateCriteria
                .getBenefitComponentId()));
        }
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            //logAdapterExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),adapterException);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        return locateResults;
    }
    
    /**
     * Method returns the duplicate benefits for a given domain values.
     * 
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public SearchResults findDuplicateBenefits(String entityName,
			List domainList, int entityParentId) throws SevereException {
        List lineOfBusiness = new ArrayList();
        List businessEntity = new ArrayList();
        List businessGroup = new ArrayList();
        List marketBusinessUnit = new ArrayList();
        Domain domain = null;
        boolean flag = true;
        if(null != domainList && !domainList.isEmpty()) {
	        for (Iterator iter = domainList.iterator(); iter.hasNext();) {
	            domain = (Domain) iter.next();
	            lineOfBusiness.add(domain.getLineOfBusiness());
	            businessEntity.add(domain.getBusinessEntity());
	            businessGroup.add(domain.getBusinessGroup());
	            marketBusinessUnit.add(domain.getMarketBusinessUnit());
	        }
        }
        HashMap criteria = new HashMap();
        criteria.put("benefitIdentifier",escapeWildCharacter(entityName));
        criteria.put("entityParentId",new Integer(entityParentId));
        
        // If Line of business is 'ALL' for new Benefit, then benefits with same name will be considered as 
        // duplicate for any value of Line of Business if Business entity and Buseinss Group are matching.
        // So Line of business needs to be excluded from the query if it is 'ALL'.
        // This is also valid for BE and BG.
        if(!lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteria.put("lobCodeList",lineOfBusiness);
        }
        
        if(!businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
            criteria.put("businessEntityCodeList",businessEntity);        	
        }
        
        if(!businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
            criteria.put("businessGroupCodeList",businessGroup);        	
        }
        
        if(!marketBusinessUnit.contains(BusinessConstants.UNIVERSAL_DOMAIN)){
        	criteria.put("marketBusinessUnitList",marketBusinessUnit);
        }
        
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(StandardBenefitBO.class.getName(),"findDuplicateBenefits",criteria,1);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults;
    }
    
	private String escapeWildCharacter(String queryPart) {
		StringBuffer queryString = new StringBuffer();
		int index = 0;
		while (index < queryPart.length()) {
			char wildChar = queryPart.charAt(index++);
			switch (wildChar) {

			case '_':
				queryString.append("~_");
				break;
			case '%':
				queryString.append("~%");
				break;
			case '~':
				queryString.append("~~");
				break;
			default:
				queryString.append(String.valueOf(wildChar));
			}
		}
		return queryString.toString();
	}
    public RuleBO retrieveRuleInfo(String ruleId) throws SevereException{
    	RuleBO ruleBo = new RuleBO();
    	ruleBo.setRuleId(ruleId);
    	return (RuleBO)AdapterUtil.performRetrieve(ruleBo);
    }
    public SearchResults locateGroupRuleInfo(String ruleId) throws SevereException{
    	SearchCriteria searchCriteria = new SearchCriteriaImpl();
    	searchCriteria.setBusinessObjectName(RuleBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("locateGroupRuleInfo");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("ruleId",ruleId);
    	return AdapterUtil.performSearch(searchCriteria);
    }
    
    /**
     * Inserts the questionnaire attached to a benefit.
     * @param benefitQuestionnaireAssnBO
     * @param audit
     * @param insertFlag
     * @param access
     * @return boolean
     * @throws AdapterException
     */
    public boolean persistQuestionnaireForBenefit(BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO, 
    		Audit audit, boolean insertFlag,AdapterServicesAccess access) throws AdapterException{
    	
        if(!"Not Answered".equals( benefitQuestionnaireAssnBO.getAnswerDesc().trim()) ){
    	Logger
         .logInfo("StandardBenefitAdapterManager - Entering persistQuestionnaireForBenefit(): Questionnaire for benefit insert");
		 BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		 btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
		 btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
		 benefitQuestionnaireAssnBO.setCreatedUser(audit.getUser());
		 benefitQuestionnaireAssnBO.setLastUpdatedUser(audit.getUser());
		 benefitQuestionnaireAssnBO.setCreatedTimestamp(audit.getTime());
		 benefitQuestionnaireAssnBO.setLastUpdatedTimestamp(audit.getTime());
		 try {
			AdapterUtil.performInsert(benefitQuestionnaireAssnBO, audit.getUser(),
			 		access);
		} catch (SevereException e) {
			throw new AdapterException(e.getMessage(),e);
		}
        }
		 Logger
		         .logInfo("StandardBenefitAdapterManager - Returning persistQuestionnaireForBenefit(): Questionnaire for benefit insert");
		 return true;
    }
    
    /**
     * Inserts the questionnaire attached to a benefit.
     * @param benefitQuestionnaireAssnBO
     * @param audit
     * @param insertFlag
     * @param access
     * @return boolean
     * @throws AdapterException
     */
    public boolean persistQuestionnaireForBenefit(BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO, 
    		StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag, 
			AdapterServicesAccess access) throws AdapterException{
    	
    	Logger
         .logInfo("StandardBenefitAdapterManager - Entering persistQuestionnaireForBenefit(): Questionnaire for benefit insert");
    	
		List newQuestions = benefitQuestionnaireAssnBO.getNewQuestions();
		List modifiedQuestions = benefitQuestionnaireAssnBO.getModifiedQuestions();
		List removedQuestions = benefitQuestionnaireAssnBO.getRemovedQuestions();
		int adminLvlOptId = benefitQuestionnaireAssnBO
		.getAdminLvlOptionAssnSysId();
		
		Iterator newIt = newQuestions.iterator();
		while(newIt.hasNext()){
			BenefitQuestionnaireAssnBO newQuestionnaireBO = (BenefitQuestionnaireAssnBO) newIt.next();
			newQuestionnaireBO.setAdminLvlOptionAssnSysId(adminLvlOptId);
			newQuestionnaireBO.setCreatedUser(audit.getUser());
			newQuestionnaireBO.setCreatedTimestamp(audit.getTime());
			newQuestionnaireBO.setLastUpdatedUser(audit.getUser());
			newQuestionnaireBO.setLastUpdatedTimestamp(audit.getTime());
			BusinessTransactionContext btc = new BusinessTransactionContextImpl();
			btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
			btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
			try {
				AdapterUtil.performInsert(newQuestionnaireBO, audit.getUser(),
				 		access);
			} catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			} 
		}
		
		Iterator modIt = modifiedQuestions.iterator();
		while(modIt.hasNext()){
			BenefitQuestionnaireAssnBO modQuestionnaireBO = (BenefitQuestionnaireAssnBO) modIt.next();
			modQuestionnaireBO.setAdminLvlOptionAssnSysId(adminLvlOptId);
			modQuestionnaireBO.setCreatedUser(audit.getUser());
			modQuestionnaireBO.setCreatedTimestamp(audit.getTime());
			modQuestionnaireBO.setLastUpdatedUser(audit.getUser());
			modQuestionnaireBO.setLastUpdatedTimestamp(audit.getTime());
			BusinessTransactionContext btc = new BusinessTransactionContextImpl();
			btc.setBusinessTransactionType(WebConstants.UPDATE_STRUCTURE);
			btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
			try {
				AdapterUtil.performUpdate(modQuestionnaireBO, audit.getUser(),
				 		access);
			} catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			} 
		}
		
		Iterator remIt = removedQuestions.iterator();
		while(remIt.hasNext()){
			BenefitQuestionnaireAssnBO remQuestionnaireBO = (BenefitQuestionnaireAssnBO) remIt.next();
			remQuestionnaireBO.setAdminLvlOptionAssnSysId(adminLvlOptId);
			remQuestionnaireBO.setCreatedUser(audit.getUser());
			remQuestionnaireBO.setCreatedTimestamp(audit.getTime());
			remQuestionnaireBO.setLastUpdatedUser(audit.getUser());
			remQuestionnaireBO.setLastUpdatedTimestamp(audit.getTime());
			BusinessTransactionContext btc = new BusinessTransactionContextImpl();
	        btc.setBusinessTransactionType("delete");
	        btc.setBusinessTransactionUser(audit.getUser());
			try {
				AdapterUtil.performRemove(remQuestionnaireBO, audit.getUser(),
				 		access);
			} catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			} 
		}
			
		 Logger
		         .logInfo("StandardBenefitAdapterManager - Returning persistQuestionnaireForBenefit(): Questionnaire for benefit insert");
		 return true;
    }
    /**
	 * This method returns the list of questionnaire that is attached to an admin option.
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List locateQuestionaire(LocateCriteria locateCriteria)
			throws SevereException {
		SelectedQuestionaireLocateCriteria selectedQuestionaireLocateCriteria = (SelectedQuestionaireLocateCriteria) locateCriteria;

		SearchResults searchResults = null;

		HashMap refValSet = new HashMap();

		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		searchCriteria
				.setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO");
		searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
		searchCriteria.setSearchDomain("medical");

		// Retrieving the root question
		if (selectedQuestionaireLocateCriteria.getAction() == 1) {
			searchCriteria.setSearchQueryName("retrieveQuestionaireList");
			refValSet.put("adminOptionSystemId", new Integer(
					selectedQuestionaireLocateCriteria.getAdminOptionId()));
			refValSet.put("benefitId", new Integer(
					selectedQuestionaireLocateCriteria.getBenefitId()));
			refValSet.put("adminLvlOptSystemId", new Integer(
					selectedQuestionaireLocateCriteria.getAdminLevelOptionSysId()));
			refValSet.put("parentId", new Integer(
					selectedQuestionaireLocateCriteria.getParentId()));	
		}

		// Retrieving the Child Question
		else if (selectedQuestionaireLocateCriteria.getAction() == 2) {
			searchCriteria.setSearchQueryName("retrieveChildQuestionnarieList");
			int answerId = selectedQuestionaireLocateCriteria.getAnswerId();
			int benefitId = selectedQuestionaireLocateCriteria.getBenefitId();
			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) selectedQuestionaireLocateCriteria
					.getBenefitQuestionnaireAssnBO();
			int questionnaireId = benefitQuestionnaireAssnBO
					.getQuestionnaireId();

			refValSet
					.put("parentQuestionnaireId", new Integer(questionnaireId));
			refValSet.put("parentAnswerId", new Integer(answerId));
			refValSet.put("benefitId", new Integer(benefitId));
			refValSet.put("adminLvlOptSystemId", new Integer(
					selectedQuestionaireLocateCriteria.getAdminLevelOptionSysId()));
		}

		searchCriteria.setReferenceValueSet(refValSet);

		try {
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList(2);
			errorParams.add(searchCriteria.getBusinessObjectName());
			errorParams.add(searchCriteria.getSearchQueryName());
			throw new ServiceException("Adapter Exception occured",
					errorParams, adapterException);
		}
		return searchResults.getSearchResults();
	}


	/**
	 * Locates a list of questionnaires invalid for the domains of the benefit.
	 * 
	 * @param standardBenefitBO
	 * @return List
	 * @throws AdapterException
	 */
	public List getQuestionnairesWithInvalidDomains(StandardBenefitBO standardBenefitBO) throws AdapterException {
		 Logger
         .logInfo("StandardBenefitAdapterManager - Entering getQuestionnairesWithInvalidDomains(): getting questionnaires with invalid domains");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List invalidQuestionnaires = null;
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(BenefitQuestionnaireAssnBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getQuestionnairesWithInvalidDomains");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("benefitId",new Integer(standardBenefitBO.getStandardBenefitKey()));
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("SevereException occured",
					e);
		}
    	if(null != searchResults && null != searchResults.getSearchResults())
    		invalidQuestionnaires = searchResults.getSearchResults();
    	 Logger
         .logInfo("StandardBenefitAdapterManager - Exiting getQuestionnairesWithInvalidDomains(): getting questionnaires with invalid domains");
    	return invalidQuestionnaires;
	}

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * deletes the questionnaire associated to a benefit.
     * @param subObject
     * @param audit
     * @return true
     * @throws WPDException
     */
    public boolean delete(BenefitQuestionnaireAssnBO subObject,
            Audit audit,AdapterServicesAccess adapterServicesAccess) throws ServiceException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
        
        adapterServicesAccess.removeObject(subObject,
                    subObject.getClass().getName(), btc); 
       
        return true;
    }


	/** Locates a list of questionnaires valid for the domains of the benefit.
	 * @param standardBenefitBO
	 * @return List
	 * @throws AdapterException
	 */
	public List getQuestionnairesWithValidDomains(StandardBenefitBO standardBenefitBO) throws AdapterException {
		 Logger
         .logInfo("StandardBenefitAdapterManager - Entering getQuestionnairesWithValidDomains(): getting questionnaires with valid domains");
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List validQuestionnaires = null;
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(BenefitQuestionnaireAssnBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getQuestionnairesWithValidDomains");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("benefitId",new Integer(standardBenefitBO.getStandardBenefitKey()));
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",
					e);
		}
    	if(null != searchResults && null != searchResults.getSearchResults())
    		validQuestionnaires = searchResults.getSearchResults();
    	 Logger
         .logInfo("StandardBenefitAdapterManager - Exiting getQuestionnairesWithValidDomains(): getting questionnaires with valid domains");
    	return validQuestionnaires;
	}
	/**
	 * Inserting  notes to questions -product-benefit level adminoption
	 * @param NotesToQuestionAttachmentBenefitBO 
	 * @param Audit
	 * @throws AdapterException
	 */
	public void insertNotesAttachedToQuestion(
			NotesToQuestionAttachmentBenefitBO attachmentBO, Audit audit)
	throws AdapterException {
		try {
			AdapterUtil.performInsert(attachmentBO, audit
					.getUser());
			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in insert",
					exception);
		}
	}
	
	/**
	 * updating  notes to questions -benefit level adminoption
	 * @param NotesToQuestionAttachmentBenefitBO 
	 * @param Audit
	 * @throws AdapterException
	 */
	public void updateNotesAttachedToQuestion(
			NotesToQuestionAttachmentBenefitBO attachmentBO, Audit audit)
	throws AdapterException {
		
		try {
			AdapterUtil.performUpdate(attachmentBO, audit
					.getUser());
			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in update",
					exception);
		}
	}
	
	/**
	 * deleting   notes to questions -benefit level adminoption
	 * @param NotesToQuestionAttachmentBenefitBO 
	 * @param Audit
	 * @throws AdapterException
	 */
	
	public void deleteNotesAttachedToQuestion(
			NotesToQuestionAttachmentBenefitBO attachmentBO, Audit audit)
	throws AdapterException {
		try {
			AdapterUtil.performRemove(attachmentBO, audit
					.getUser());
			
		} catch (SevereException exception) {
			throw new AdapterException("Exception occured while adapter call in delete",
					exception);
		}
	}
	
	public List getNoteInfo(NotesToQuestionAttachmentBenefitBO attachmentBo)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List validQuestionnaires = null;
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(NotesToQuestionAttachmentBenefitBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getNoteInfo");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("primaryId",new Integer(attachmentBo.getPrimaryId()));
    	refValSet.put("secondaryId",new Integer(attachmentBo.getSecondaryId()));
    	refValSet.put("questionId",new Integer(attachmentBo.getQuestionId()));
    	refValSet.put("primaryEntityType",attachmentBo.getPrimaryEntityType());
    	refValSet.put("secondaryEntityType",attachmentBo.getSecondaryEntityType());
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",
					e);
		}
		
		List resultList = searchResults.getSearchResults();
		
		return resultList;
	}


	/**
	 * @param benefitDefinitionId
	 * @return
	 * @throws SevereException 
	 */
	public List getTermListTobeInserted( long benefitSysId) throws SevereException {
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(TermSpsBO.class.getName());
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getTermListTobeInserted");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("benefitSysId",new Integer((int)benefitSysId));
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}


	/**
	 * @param spsList
	 * @throws SevereException 
	 */
	public void createTermToSpsMap(TermSpsBO termSpsBO,  String  userId) throws SevereException {
		AdapterUtil.performInsert(termSpsBO, userId);
	}
	
}