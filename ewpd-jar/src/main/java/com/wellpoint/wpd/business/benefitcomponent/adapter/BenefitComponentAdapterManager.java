/*
 * BenefitComponentAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.adapter;

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
import com.wellpoint.adapter.access.SearchResultsImpl;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentDeleteLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitHierarchyLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponent;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentQuesitionnaireBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.ComponentsBenefitAdministrationBO;
import com.wellpoint.wpd.common.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionHideBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Adapter Manager class for Benefit Component.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentAdapterManager {

    public final String USER_NAME = "USER";

    public final String CREATE = "create";

    public final String EDIT = "edit";

    public final String DELETE = "delete";

    public final String EWPDB_DOMAIN = "ewpd";


    /**
     * To get all the valid BenefitComponents for ProductStructure
     * 
     * @param productStructureId
     * @param lob
     * @param bEntity
     * @param bGroup
     * @return List
     * @throws SevereException
     */
    public List locate(int productStructureId, List lob, List bEntity,
            List bGroup, List marketBusiness) throws SevereException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_COMPONENT_BO);
        searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        searchCriteria
                .setSearchQueryName(BusinessConstants.LOCATE_SEARCH_QUERY);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put("productStructureId", new Integer(productStructureId));
        refValSet.put("lobCodeList", lob);
        refValSet.put("businessEntityCodeList", bEntity);
        refValSet.put("businessGroupCodeList", bGroup);
        refValSet.put("marketBusinessUnitList", marketBusiness);
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            SearchResults searchResults = getAdapterService().searchObject(
                    searchCriteria);
            return searchResults.getSearchResults();
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(1);
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);
        }
    }


    public void RefereshQuestionare(int bnftCompSysId, String userId) throws SevereException {
    	HashMap values = new HashMap();
    	values.put("beneftComponentId",new Integer(bnftCompSysId));
		values.put("lastUpdatedUser",userId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(BenefitComponentQuesitionnaireBO.class.getName(),"refreshQuestionaireProc",values);
		AdapterUtil.performSearch(searchCriteria);
    }
    
    /**
     * Gets Associated Benefit Components for addition
     * 
     * @param productStructureId
     * @return searchResults
     * @throws SevereException
     */
    public List getAssociatedBenefitComponentsToBeAdded(int productStructureId)
            throws SevereException {
        HashMap refValSet = new HashMap();
        refValSet.put("productStructureId", new Integer(productStructureId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitComponentBO.class.getName(),
                "retrieveComponentsToBeAdded", refValSet);
        try {
            SearchResults searchResults = getAdapterService().searchObject(
                    searchCriteria);
            return searchResults.getSearchResults();
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(1);
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);
        }
    }


    /**
     * 
     * @param productStructureId
     * @param lob
     * @param bEntity
     * @param bGroup
     * @return searchResults
     * @throws SevereException
     */
    public List getAssociatedBenefitComponentsToBeRemoved(
            int productStructureId, List lob, List bEntity, List bGroup, List marketBusiness)
            throws SevereException {
        HashMap refValSet = new HashMap();
        refValSet.put("productStructureId", new Integer(productStructureId));
        refValSet.put("lobCodeList", lob);
        refValSet.put("businessEntityCodeList", bEntity);
        refValSet.put("businessGroupCodeList", bGroup);
        refValSet.put("marketBusinessUnitList", marketBusiness);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitComponentBO.class.getName(),
                "retrieveComponentsToBeRemoved", refValSet);
        try {
            SearchResults searchResults = getAdapterService().searchObject(
                    searchCriteria);
            return searchResults.getSearchResults();
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(1);
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);
        }
    }


    /**
     * Get the Adapter Services Access
     * 
     * @return AdapterServicesAccess.
     */
    public AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(BusinessConstants.EWPD);
        return adapterServicesAccess;
    }


    /**
     * Get transaction Context
     * 
     * @param operation
     * @param userId
     * @return BusinessTransactionContext
     */
//    private BusinessTransactionContext getTransactionContext(String operation,
//            String userId) {
//        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
//        btc.setBusinessTransactionType(operation);
//        btc.setBusinessTransactionUser(userId);
//        return btc;
//    }


    /**
     * To search for the benefit component
     * 
     * @return locateResults LocateResults
     * @throws ServiceException
     */
    public LocateResults locateBenefitComponent(LocateCriteria locateCriteria)
            throws WPDException {

        BenefitComponentLocateCriteria benefitComponentLocateCriteria = (BenefitComponentLocateCriteria) locateCriteria;
        LocateResults locateResults = new LocateResults();
       
        HashMap refValSet = new HashMap();
        try{
        setValuesToMap(refValSet, benefitComponentLocateCriteria);
        int maxResultSize = benefitComponentLocateCriteria
                .getMaximumResultSize();
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_COMPONENT_BO,
                BusinessConstants.LOCATE_BENEFIT_COMPONENT, refValSet,
                maxResultSize);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitComponent LocateCriteria method in BenefitComponentAdapterManager",
					null, ex);
		}
        return locateResults;
    }


    /**
     * Get the Adapter Search Criteria
     * 
     * @param businessObjectName
     *            Name of the Business Object
     * @param queryName
     *            Name of the query to be executed
     * @param criteriaValues
     *            HashMap to map the values
     * @return SearchCriteria.
     */
    private SearchCriteria getAdapterSearchCriteria(String businessObjectName,
            String queryName, HashMap criteriaValues, int resultCount) {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(criteriaValues);
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchQueryName(queryName);
        searchCriteria.setMaxSearchResultSize(resultCount);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        return searchCriteria;
    }


    /**
     * Function to set the criteria values to a map
     * 
     * @param map
     * @param benefitComponentLocateCriteria
     */
    private void setValuesToMap(HashMap map,
            BenefitComponentLocateCriteria benefitComponentLocateCriteria) {
        String benefitComponentName = benefitComponentLocateCriteria.getName();
        if (benefitComponentName != null
                && benefitComponentName.trim().length() > 0) {
            benefitComponentName = "%"
                    + benefitComponentName.toUpperCase()
                    + "%";
        } else
            benefitComponentName = "%";
        map.put("benefitComponentName", benefitComponentName);
        map.put("lobCodeList", benefitComponentLocateCriteria.getLob());
        map.put("businessEntityCodeList", benefitComponentLocateCriteria
                .getBusinessEntity());
        map.put("businessGroupCodeList", benefitComponentLocateCriteria
                .getBusinessGroup());
        map.put("marketBusinessUnitList", benefitComponentLocateCriteria.getMarketBusinessUnit());
        if (benefitComponentLocateCriteria.getEffeciveDate().equals(""))
            map.put("effectiveDate", null);
        else
            map.put("effectiveDate", benefitComponentLocateCriteria
                    .getEffeciveDate());
        if (benefitComponentLocateCriteria.getExpiryDate().equals(""))
            map.put("expiryDate", null);
        else
            map.put("expiryDate", benefitComponentLocateCriteria
                    .getExpiryDate());
    }


    /**
     * Retrieves the latest BenefitComponent corresponding to the
     * BenefitComponentName of the BenefitComponent.
     * 
     * @param benefitComponentBO
     *            BenefitComponentBO object.
     * @return BenefitComponentBO.
     * @throws ServiceException
     */
    public BenefitComponentBO retrieveBenefitComponentLatestVersion(
            BenefitComponentBO benefitComponentBO) throws AdapterException,SevereException {
        HashMap criteriaMap = getCriteriaForBenefitComponentBO(benefitComponentBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                benefitComponentBO.getClass().getName(),
                "retrieveBenefitComponentLatestVersion", criteriaMap);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (BenefitComponentBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * Retrieves the latest published BenefitComponent
     * 
     * @param benefitComponentBO
     *            BenefitComponentBO object.
     * @return BenefitComponentBO.
     * @throws SevereException
     */
    public BenefitComponentBO retrieveBenefitComponentLatestPublishedVersion(
            BenefitComponentBO benefitComponentBO) throws AdapterException {
        HashMap criteriaMap = getCriteriaForBenefitComponentBO(benefitComponentBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                benefitComponentBO.getClass().getName(),
                "retrieveBenefitComponentLatestPublishedVersion", criteriaMap);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (BenefitComponentBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * Retrieves the latest version number corresponding to the
     * BenefitComponentName of the BenefitComponent.
     * 
     * @param benefitComponentBO
     *            BenefitComponentBO object.
     * @return
     * @throws ServiceException
     */
    public int retrieveLatestVersionNumber(BenefitComponentBO benefitComponentBO)
            throws SevereException,AdapterException {
        HashMap criteriaMap = getCriteriaForBenefitComponentBO(benefitComponentBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                benefitComponentBO.getClass().getName(),
                "retrieveBenefitComponentLatestVersionNumber", criteriaMap);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            benefitComponentBO = (BenefitComponentBO) searchResults
                    .getSearchResults().get(0);
            return benefitComponentBO.getVersion();
        }
        return -1;
    }


    /**
     * Retrieves benefit component by Id
     * 
     * @param benefitComponentBO
     * @return BenefitComponentBO
     */
    public BenefitComponentBO retrieveBenefitComponentById(
            BenefitComponentBO benefitComponentBO) throws SevereException,AdapterException {
        HashMap criteriaMap = getCriteriaForTreeBenefitComponent(benefitComponentBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                benefitComponentBO.getClass().getName(),
                "retrieveBenefitComponentById", criteriaMap);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            BenefitComponentBO myBenefitComponentBO = (BenefitComponentBO) searchResults
                    .getSearchResults().get(0);
            setValuesToBO(myBenefitComponentBO, benefitComponentBO);
        }
        return benefitComponentBO;
    }

    public void setValuesToBO(BenefitComponentBO source,BenefitComponentBO target){
        target.setBenefitComponentId(source.getBenefitComponentId());
        target.setStateId(source.getStateId());
    }
    /**
     * Gets criteria for benefit component tree
     * 
     * @param benefitComponentBO
     * @return HashMap
     */
    private HashMap getCriteriaForTreeBenefitComponent(
            BenefitComponentBO benefitComponentBO) {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("benefitComponentId", new Integer(benefitComponentBO
                .getBenefitComponentId()));
        criteriaValues
                .put("benefitComponentName", benefitComponentBO.getName());
        return criteriaValues;
    }


    /**
     * Gets Criteria For Benefit Component BO
     * 
     * @param benefitComponentBO
     * @return
     */
    private HashMap getCriteriaForBenefitComponentBO(
            BenefitComponentBO benefitComponentBO) throws AdapterException {
        HashMap criteriaValues = new HashMap();

        criteriaValues
                .put("benefitComponentName", benefitComponentBO.getName());
        criteriaValues.put("benefitComponentId", new Integer(benefitComponentBO
                .getBenefitComponentId()));
        criteriaValues.put("lobCodeList", benefitComponentBO
                .getLineOfBusiness());
        criteriaValues.put("businessEntityCodeList", benefitComponentBO
                .getBusinessEntity());
        criteriaValues.put("businessGroupCodeList", benefitComponentBO
                .getBusinessGroup());
        criteriaValues.put("marketBusinessUnitList", benefitComponentBO
                .getMarketBusinessUnit()); 
        return criteriaValues;
    }


    /**
     * Get the Adapter Search Criteria
     * 
     * @param businessObjectName
     *            Name of the Business Object
     * @param queryName
     *            Name of the query to be executed
     * @param criteriaValues
     *            HashMap to map the values
     * @return SearchCriteria.
     */
    private SearchCriteria getAdapterSearchCriteria(String businessObjectName,
            String queryName, HashMap criteriaValues) {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(criteriaValues);
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchQueryName(queryName);
        searchCriteria.setMaxSearchResultSize(999999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        return searchCriteria;
    }


    /**
     * Get the list of ProductStructures with the corresponding searchCriteria
     * 
     * @param searchCriteria
     *            SearchCriteria object
     * @return List of ProductStructures.
     * @throws ServiceException
     */
    private SearchResults performSearch(SearchCriteria searchCriteria)
            throws AdapterException {
        SearchResults searchResults = null;
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (Exception adapterException) {
            List errorParams = new ArrayList(2);
            errorParams.add(searchCriteria.getBusinessObjectName());
            errorParams.add(searchCriteria.getSearchQueryName());
            throw new AdapterException("Exception occured in createBenefitComponent method in benefitComponentAdapterManager",
                   adapterException);
        }
        return searchResults;
    }


    /**
     * Method to insert a newly created Benefit Component to the database.
     * 
     * @param benefitComponentBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean persistBenefitComponent(
            BenefitComponentBO benefitComponentBO, Audit audit)
            throws SevereException {
        SequenceAdapterManager sequenceAdapterManager = null;
        if (null != benefitComponentBO
                && benefitComponentBO.getBenefitComponentId() == -1) {
            sequenceAdapterManager = new SequenceAdapterManager();
            benefitComponentBO.setBenefitComponentId(sequenceAdapterManager
                    .getNextBenefitComponentSequence());
            benefitComponentBO.setBenefitComponentParentId(benefitComponentBO
                    .getBenefitComponentId());
        }
        benefitComponentBO.setCreatedUser(audit.getUser());
        benefitComponentBO.setCreatedTimestamp(audit.getTime());
        benefitComponentBO.setLastUpdatedUser(audit.getUser());
        benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());
        AdapterUtil.performInsert(benefitComponentBO, audit.getUser());
        return true;
    }


    /**
     * Method to retrieve the Benefit Component details from the database, using
     * Benefit Component Name.
     * 
     * @param benefitComponentBO
     * @return
     * @throws SevereException
     */
    public List checkDuplicateBenefitComponent(String entityName, List domainList,
            int entityParentId) throws SevereException {
    	List lineOfBusiness = new ArrayList(domainList==null?0:domainList.size());
        List businessEntity = new ArrayList(domainList==null?0:domainList.size());
        List businessGroup = new ArrayList(domainList==null?0:domainList.size());
        List marketBusinessUnit = new ArrayList(domainList==null?0:domainList.size());
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
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("benefitComponentName",entityName);
        criteriaMap.put("benefitComponentParentId", new Integer(entityParentId));
        
        // If Line of business is 'ALL' for new BenefitComponent, then BenefitComponent with same name will be considered as 
        // duplicate for any value of Line of Business if Business entity and Buseinss Group are matching.
        // So Line of business needs to be excluded from the query if it is 'ALL'.
        // This is also valid for BE and BG.
        if(!lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("lobCodeList",lineOfBusiness);
        }
        
        if(!businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("businessEntityCodeList",businessEntity);        	
        }
        
        if(!businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN)) {
        	criteriaMap.put("businessGroupCodeList",businessGroup);        	
        }

        if(!marketBusinessUnit.contains(BusinessConstants.UNIVERSAL_DOMAIN)){
        	criteriaMap.put("marketBusinessUnitList", marketBusinessUnit);
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitComponentBO.class.getName(),
                "checkBenefitComponentDuplicate", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults != null) {
            return searchResults.getSearchResults();
        }
        return null;

    }
	
    /**
     * Method to retrieve the Benefit Names matching the Benefit Component name
     * 
     * @param benefitComponentBO
     * @return
     * @throws SevereException
     */
    public List checkUniqueBenefitComponentName(
            BenefitComponentBO benefitComponentBO) throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("benefitComponentName", benefitComponentBO.getName());

        criteriaMap.put("lobList", benefitComponentBO.getLineOfBusiness());
        criteriaMap.put("businessEntityList", benefitComponentBO
                .getBusinessEntity());
        criteriaMap.put("businessGroupList", benefitComponentBO
                .getBusinessGroup());
        criteriaMap.put("marketBusinessUnitList", benefitComponentBO
                .getMarketBusinessUnit());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitBO.class.getName(),
                "benefitNameSearch", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults != null) {
            return searchResults.getSearchResults();
        }
        return null;

    }

    /**
     * Method to create the Benefit Component.
     * 
     * 
     * @param benefitComponentBO
     * @param audit
     * @return
     * @throws SevereException
     * @throws WPDException
     */
    public boolean createBenefitComponent(
            BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag,
            AdapterServicesAccess benefitComponentAdapterServiceAccess)
            throws AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());

        try{
            SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
            benefitComponentBO.setBenefitComponentId(sequenceAdapterManager
                    .getNextBenefitComponentSequence());
            if (benefitComponentBO.getVersion() <= 1) {
                benefitComponentBO
                        .setBenefitComponentParentId(benefitComponentBO
                                .getBenefitComponentId());

            }            	
            
            benefitComponentAdapterServiceAccess.persistObject(
                    benefitComponentBO,
                    benefitComponentBO.getClass().getName(), btc);
            
        } 
        catch (Exception ex) {
            	List errorParams = new ArrayList(2);
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
            	   throw new AdapterException( "Exception occured in createBenefitComponent method in benefitComponentAdapterManager",
                        ex);
		}
        return true;
    }


    /**
     * Method to update the Benefit Component.
     * 
     * @param benefitComponentBO
     * @param audit
     * @return boolean
     * @throws WPDException
     */
    public boolean updateBenefitComponent(
            BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag,
            AdapterServicesAccess benefitComponentAdapterServiceAccess)
            throws AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(EDIT);
        btc.setBusinessTransactionUser(audit.getUser());
        try{
            benefitComponentAdapterServiceAccess.persistObject(
                    benefitComponentBO,
                    benefitComponentBO.getClass().getName(), btc);
        }catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new AdapterException( "Exception occured in updateBenefitComponent method in benefitComponentAdapterManager",
                    ex);
		}

        return true;
    }


    /**
     * Method to retrieve benefit Component
     * 
     * @param benefitComponentBO
     * @return benefitComponentBO
     * @throws SevereException
     */
    public Object retrieveBenefitComponent(BenefitComponentBO benefitComponentBO)
            throws SevereException {
        try {
            getAdapterService().retrieveObject(benefitComponentBO,
                    benefitComponentBO.getClass().getName());
           

        } catch (AdapterException e) {
            List errorParams = new ArrayList(2);
            errorParams.add(benefitComponentBO);
            errorParams.add(benefitComponentBO.getClass().getName());
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, e);
        }
        return benefitComponentBO;
    }


    /**
     * @param locateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefits(BenefitLocateCriteria locateCriteria)
            throws SevereException,AdapterException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();       
        HashMap referenceValueSet = new HashMap();
        try{
        referenceValueSet.put("benefitComponentId", new Integer(locateCriteria
                .getBenefitComponentId()));
        referenceValueSet.put("lobList", locateCriteria.getLobList());
        referenceValueSet.put("businessEntityList", locateCriteria
                .getBusinessEntityList());
        referenceValueSet.put("businessGroupList", locateCriteria
                .getBusinessGroupList());
        referenceValueSet.put("marketBusinessUnitList", locateCriteria
                .getMarketBusinessUnit());
        
       
// Modification in the query -start
//        String lobNameForALL = null;
//        String busEntityNameForALL = null;
//        String busGrpNameForALL = null;
//		// For LOB List
//        if(null != locateCriteria.getLobList() && locateCriteria.getLobList().size()!=0){
//        	for(int i=0; i<locateCriteria.getLobList().size() ;i++){
//        		if("ALL".equals(locateCriteria.getLobList().get(i))){
//        			lobNameForALL = Character.toString('%');
//        		}else{
//        			lobNameForALL = null;
//        		}
//        	}
//        
//        }
//        // For businessEntityList
//        if(null != locateCriteria.getBusinessEntityList() && locateCriteria.getBusinessEntityList().size()!=0){
//        	for(int i=0; i<locateCriteria.getBusinessEntityList().size() ;i++){
//        		if("ALL".equals(locateCriteria.getBusinessEntityList().get(i))){
//        			busEntityNameForALL = Character.toString('%');
//        		}else{
//        			busEntityNameForALL = null;
//        		}
//        	}
//        
//        }
//        // For businessGroupList
//        if(null != locateCriteria.getBusinessGroupList() && locateCriteria.getBusinessGroupList().size()!=0){
//        	for(int i=0; i<locateCriteria.getBusinessGroupList().size() ;i++){
//        		if("ALL".equals(locateCriteria.getBusinessGroupList().get(i))){
//        			busGrpNameForALL = Character.toString('%');
//        		}else{
//        			busGrpNameForALL = null;
//        		}
//        	}
//        
//        }
//        referenceValueSet.put("lobNameForALL",lobNameForALL);
//        referenceValueSet.put("busEntityNameForALL",busEntityNameForALL);
//        referenceValueSet.put("busGrpNameForALL",busGrpNameForALL);
// Modification - End        
        
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitcomponent.bo.BenefitBO");

        searchCriteria.setSearchQueryName("benefitSearch");
        searchCriteria.setMaxSearchResultSize(9999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
//        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
//                .getAdapterServicesAccess();
//        try {
            //benefitSearchResults = adapterServicesAccess
              benefitSearchResults = getAdapterService().searchObject(searchCriteria);
//        } catch (AdapterException adapterException) {
//            logAdapterException(null, adapterException);
//        }
        if (null != benefitSearchResults.getSearchResults()) {
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        }
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        locateResults.setTotalResultsCount(benefitSearchResults
                .getSearchResultCount());
        }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateBenefits BenefitLocateCriteria method in BenefitComponentAdapterManager",
					ex);
		}
        return locateResults;
    }
	/**
	 * 
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List invalidBenefitList(BenefitLocateCriteria locateCriteria)throws SevereException {
	    List locateResultsList = null;
	    HashMap referenceValueSet = new HashMap();
	    referenceValueSet.put("benefitComponentId", new Integer(locateCriteria.getBenefitComponentId()));
	    referenceValueSet.put("lobList", locateCriteria.getLobList());
	    referenceValueSet.put("businessEntityList", locateCriteria.getBusinessEntityList());
	    referenceValueSet.put("businessGroupList", locateCriteria.getBusinessGroupList());
	    referenceValueSet.put("marketBusinessUnitList", locateCriteria.getMarketBusinessUnit());
	    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
											    		BenefitBO.class.getName(), 
														"invalidBenefit",
											     		referenceValueSet);
	    locateResultsList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
	     
	    return locateResultsList;
	}
	/**
	 * 
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List locateBenefitHieararchies(BenefitLocateCriteria locateCriteria)throws SevereException,AdapterException {
	    List locateResultsList = null;
	    HashMap referenceValueSet = new HashMap();
	    try{
	    referenceValueSet.put("benefitComponentId", new Integer(locateCriteria.getBenefitComponentId()));
	    referenceValueSet.put("lobList", locateCriteria.getLobList());
	    referenceValueSet.put("businessEntityList", locateCriteria.getBusinessEntityList());
	    referenceValueSet.put("businessGroupList", locateCriteria.getBusinessGroupList());
	    referenceValueSet.put("marketBusinessUnitList", locateCriteria.getMarketBusinessUnit());
	
	    SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	    												BenefitHierarchyAssociationBO.class.getName(), 
														"forBenefitHierarchy",
											     		referenceValueSet);
	    locateResultsList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
	    }catch (Exception e) {
	    	
	    	List errorParams = new ArrayList(2);
			String obj = e.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateBenefitHieararchies BenefitLocateCriteria method in BenefitComponentAdapterManager",
					e);

		}
	    return locateResultsList;
	}

    /**
     * Method to throw the exception.
     * 
     * @param obj,adapterException
     * @throws ServiceException
     */
    private void logAdapterException(Object obj,
            AdapterException adapterException) throws SevereException {
        List errorParams = new ArrayList(1);
        errorParams.add(obj);
        throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                errorParams, adapterException);

    }


    /**
     * Inserts benefit hierarchies to DB
     * 
     * @param benefitHierarchyBO
     * @param user
     * @param insertFlag
     * @throws SevereException
     */
    public boolean persistBenefitHierarchies(
            BenefitHierarchyBO benefitHierarchyBO, String user,
            boolean insertFlag, AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException { 
        	try{
            this.persistBenefitHierarchyMaster(benefitHierarchyBO, user,
                    adapterServicesAccess);
            List benefitHierarchies = benefitHierarchyBO
                    .getBenefitHierarchiesList();
            if (null != benefitHierarchies) {
                for (int i = 0; i < benefitHierarchies.size(); i++) {
                    BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) benefitHierarchies
                            .get(i);
                    benefitHierarchyAssociationBO
                            .setBenefitHierarchyId(benefitHierarchyBO
                                    .getBenefitHierarchyId());
                    this.persistBenefitHierarchy(benefitHierarchyAssociationBO,
                            user, adapterServicesAccess, insertFlag);
                }
            }
        	}catch (Exception ex) {
        		List errorParams = new ArrayList(2);
                String obj = ex.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
            	   throw new AdapterException( "Exception occured in persistBenefitHierarchies method in benefitComponentAdapterManager",
                        ex);
			}
        return true;
    }


    /**
     * @param benefitHierarchyBO
     * @param user
     * @return
     * @throws SevereException
     */
    private boolean persistBenefitHierarchyMaster(
            BenefitHierarchyBO benefitHierarchyBO, String user,
            AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionUser(user);
        int hierarchyId = this.locateBenefitHierarchyId(benefitHierarchyBO
                .getBenefitComponentId());
        if (hierarchyId == -1) {
            btc.setBusinessTransactionType(this.CREATE);
            benefitHierarchyBO.setBenefitHierarchyId(sequenceAdapterManager
                    .getNextBenefitHierarchySequence());
            try {
                adapterServicesAccess.persistObject(benefitHierarchyBO,
                        BusinessConstants.BENEFIT_HIERARCHY_BO, btc);
            } catch (AdapterException adapterException) {
                logAdapterException(benefitHierarchyBO, adapterException);
                List errorParams = new ArrayList(2);
                String obj = adapterException.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
            	   throw new AdapterException( "Exception occured in persist method in persist method in benefitComponentAdapterManager",
            	   		adapterException);
            }
        } else {
            benefitHierarchyBO.setBenefitHierarchyId(hierarchyId);
            btc.setBusinessTransactionType(this.EDIT);
            try {
                adapterServicesAccess.persistObject(benefitHierarchyBO,
                        BusinessConstants.BENEFIT_HIERARCHY_BO, btc);
            } catch (AdapterException adapterException) {
                logAdapterException(benefitHierarchyBO, adapterException);
                List errorParams = new ArrayList(2);
                String obj = adapterException.getClass().getName();
                errorParams.add(obj);
                errorParams.add(obj.getClass().getName());
            	   throw new AdapterException( "Exception occured in persist method in persist method in benefitComponentAdapterManager",
            	   		adapterException);
            }
        }
        return true;
    }


    /**
     * Benefit Hierarchy Insert/Update
     * 
     * @param benefitHierarchyAssociationBO
     * @param user
     * @param insertFlag
     * @throws SevereException
     */
    public void persistBenefitHierarchy(
            BenefitHierarchyAssociationBO benefitHierarchyAssociationBO,
            String user, AdapterServicesAccess adapterServicesAccess,
            boolean insertFlag) throws SevereException,AdapterException {
        try {
            
            SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionUser(user);
            if (insertFlag) {
            	/** eWPD Stabilization Change- The maxSequenceNumber query is moved inside the condition which is used only there**/
            	HashMap criteriaValue = new HashMap();
                criteriaValue.put("benefitComponentId", new Integer(
                        benefitHierarchyAssociationBO.getBenefitComponentId()));
                SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(
                        BenefitHierarchyAssociationBO.class.getName(),
                        "maxSequenceNumber", criteriaValue, 1);
                List resultList = AdapterUtil.performSearch(criteria)
                        .getSearchResults();
                int sequenceNumber = 0;
                if (resultList != null && resultList.size() != 0) {
                    BenefitHierarchyAssociationBO sequenceNumberBo = (BenefitHierarchyAssociationBO) resultList
                            .get(0);
                    sequenceNumber = sequenceNumberBo.getSequenceNumber();
                } else {
                    sequenceNumber = 1;
                }
                btc.setBusinessTransactionType(this.CREATE);
                benefitHierarchyAssociationBO
                        .setBenefitHierarchyAssociationId(sequenceAdapterManager
                                .getNextBenefitHierarchyAssociationSequence());
                benefitHierarchyAssociationBO.setSequenceNumber(sequenceNumber);
                adapterServicesAccess.persistObject(
                        benefitHierarchyAssociationBO,
                        BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO, btc);
            } else {
                btc.setBusinessTransactionType(this.EDIT);
                adapterServicesAccess.persistObject(
                        benefitHierarchyAssociationBO,
                        BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO, btc);
            }
        } catch (AdapterException adapterException) {
            logAdapterException(benefitHierarchyAssociationBO, adapterException);
            List errorParams = new ArrayList(2);
            String obj = adapterException.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new AdapterException( "Exception occured in persist method in persist method in benefitComponentAdapterManager",
                    adapterException);
        }
    }

    /**
     * Loactes Benefit Hierarchies
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws ServiceException
     */
    public LocateResults locateBenefitHieararchies(
            BenefitHierarchyLocateCriteria locateCriteria)
            throws SevereException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(locateCriteria.getBenefitComponentId()));
	    referenceValueSet.put("lobList", locateCriteria.getLobList());
	    referenceValueSet.put("businessEntityList", locateCriteria.getBusinessEntityList());
	    referenceValueSet.put("businessGroupList", locateCriteria.getBusinessGroupList());
	    referenceValueSet.put("marketBusinessUnitList", locateCriteria.getMarketBusinessUnitList());
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO);

        searchCriteria
                .setSearchQueryName(BusinessConstants.LOCATE_BENEFIT_HIERARCHY_SEARCH);
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitSearchResults.getSearchResults()) {
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        }
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        locateResults.setTotalResultsCount(benefitSearchResults
                .getSearchResultCount());
        return locateResults;
    }


    /**
     * Gets Benifit hierarchy Id
     * 
     * @param benefitComponentId
     * @return int
     * @throws ServiceException
     */
    public int locateBenefitHierarchyId(int benefitComponentId)
            throws SevereException {
        SearchResults benefitSearchResults = null;
        int hierarchyId = -1;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(
                benefitComponentId));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_HIERARCHY_BO);

        searchCriteria
                .setSearchQueryName(BusinessConstants.LOCATE_BENEFIT_HIERARCHY_ID);
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitSearchResults
                && null != benefitSearchResults.getSearchResults()) {
            if (benefitSearchResults.getSearchResults().size() > 0) {
                BenefitHierarchyBO benefitHierarchyBO = (BenefitHierarchyBO) benefitSearchResults
                        .getSearchResults().get(0);
                hierarchyId = benefitHierarchyBO.getBenefitHierarchyId();
            }
        }
        return hierarchyId;
    }


    /**
     * To remove Benefit hierarchy
     * 
     * @param benefitHierarchyBO
     * @param user
     * @throws SevereException
     */
    public void removeBenefitHierarchy(BenefitHierarchyBO benefitHierarchyBO, AdapterServicesAccess adapterServicesAccess,
            String user) throws SevereException,AdapterException {
        BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = new BenefitHierarchyAssociationBO();
        benefitHierarchyAssociationBO.setBenefitComponentId(benefitHierarchyBO
                .getBenefitComponentId());
        benefitHierarchyAssociationBO
                .setBenefitHierarchyAssociationId(benefitHierarchyBO
                        .getBenefitHierarchyAssociationId());
        benefitHierarchyAssociationBO.setBenefitHierarchyId(benefitHierarchyBO
                .getBenefitHierarchyId());
        // changed Nov 26
           benefitHierarchyAssociationBO.setBenefitId(benefitHierarchyBO.getBenefitId());
        // change ends
        AdapterUtil.performRemove(benefitHierarchyAssociationBO, user, adapterServicesAccess);
    }


    /**
     * Retrieves the latest BenefitComponent corresponding to the
     * BenefitComponentName of the BenefitComponent.
     * 
     * @param benefitComponentBO
     *            BenefitComponentBO object.
     * @return BenefitComponentBO.
     * @throws ServiceException
     */
    public BenefitComponentBO retrieveLatestVersion(
            BenefitComponentBO benefitComponentBO) throws AdapterException {
        HashMap criteriaMap = getCriteriaForBenefitComponentBO(benefitComponentBO);
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                benefitComponentBO.getClass().getName(),
                "retrieveBenefitComponentLatestVersion", criteriaMap);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (BenefitComponentBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * Method to search if the Benefit Component is associated to
     * ProductStructure( Check if there is an entry in PROD_STR_BNFT_CMPT_ASSN)
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public SearchResults searchIfBenefitComponentExists(
            BenefitComponentDeleteLocateCriteria locateCriteria)
            throws AdapterException,SevereException {
        // Create a reference of SearchResults
        SearchResults searchResults = null;
      
        BenefitComponentDeleteLocateCriteria benefitComponentDeleteLocateCriteria =  locateCriteria;
        // create the refValSet Hashmap
        HashMap refValSet = new HashMap();
        // Set benefitComponentId to the refValSet
        refValSet.put("benefitComponentId", new Integer(
                benefitComponentDeleteLocateCriteria.getBenefitComponentKey()));
        // Set the various required parameters to the searchCriteria
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_COMPONENT_BO,
                "locateBenefitComponentForDelete", refValSet, 50);
        //try {
            // Get the searchResults from the adapter
            searchResults = performSearch(searchCriteria);

        /*} catch (SevereException severeException) {
            List errorParams = new ArrayList();
            String obj = searchCriteria.getBusinessObjectName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, severeException);
        }*/
        // return searchResults
        return searchResults;
    }


    /**
     * Method to remove Benefit Component XML: BenefitDefinition.xml
     * 
     * @param benefitComponentBO
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean removeBenefitComponent(
            BenefitComponentBO benefitComponentBO, Audit audit,AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        // Create an object of BusinessTransactionContext
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        // Specify the transaction type
        btc.setBusinessTransactionType(DELETE);
        // Set the value of the user to btc
        btc.setBusinessTransactionUser(audit.getUser());

        try{
            // Connect to the adapter and call the respective method for
            // deletion
            adapterServicesAccess.removeObject(benefitComponentBO,
                    benefitComponentBO.getClass().getName(), btc);
        }catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new AdapterException( "Exception occured in removeBenefitComponent method in benefitComponentAdapterManager",
                    ex);
		}
        
        return true;
    }


    /**
     * Benefit component copy from source BO to target BO
     * 
     * @param srcBO
     * @param trgtBO
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults copyBenefitComponent(BenefitComponentBO srcBO,
            BenefitComponentBO trgtBO,String copyAction, 
            AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
        SearchResults searchResults = null;
       
        HashMap refValSet = new HashMap();
       // String copyAction = WebConstants.STATUS_CHECK_IN;
        
        BenefitComponentBO sourceBenefitComponentBO =  srcBO;
        BenefitComponentBO targetBenefitComponentBO =  trgtBO;
        refValSet.put("sourceKey", new Integer(sourceBenefitComponentBO
                .getBenefitComponentId()));
        refValSet.put("copyaction", copyAction);
        refValSet.put("destinationKey", new Integer(targetBenefitComponentBO
                .getBenefitComponentId()));
        refValSet.put("user", sourceBenefitComponentBO.getCreatedUser());
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_COMPONENT_BO,
                "copyOneBenefitComponentToAnother", refValSet, 50);
        searchResults = AdapterUtil.performSearch(searchCriteria,adapterServicesAccess);
        return searchResults;
    }


    /**
     * To retrieve all versions of the benefit component
     * 
     * @return locateResults LocateResults
     * @throws ServiceException
     * @throws SevereException
     */
    public LocateResults viewAllBenefitComponent(LocateCriteria locateCriteria)
            throws AdapterException {
        BenefitComponentLocateCriteria benefitComponentLocateCriteria = (BenefitComponentLocateCriteria) locateCriteria;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        try{
        refValSet.put("benefitComponentId", benefitComponentLocateCriteria
                .getComponentId());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_COMPONENT_BO, "viewAllVersions",
                refValSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in viewAllBenefitComponent LocateCriteria method in BenefitComponentAdapterManager",ex);
		}
        return locateResults;
    }


    /**
     * Method for searching benefits
     * 
     * @param criteria
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults searchForBenefits(
            BenefitHierarchyLocateCriteria criteria) throws SevereException {
        SearchResults searchResults = new SearchResultsImpl();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(criteria
                .getBenefitComponentId()));
        referenceValueSet.put("benefitHierarchyId", new Integer(criteria
                .getBenefitHierarchyId()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO, "benefitSearch",
                referenceValueSet, 50);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(2);
            errorParams.add(searchCriteria.getBusinessObjectName());
            errorParams.add(searchCriteria.getSearchQueryName());
            throw new SevereException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);
        }
        return searchResults;

    }


    /**
     * Finding Benefit hierarchies that needs to be removed
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws ServiceException
     */
    public LocateResults locateBenefitHieararchiesToBeRemoved(
            BenefitHierarchyLocateCriteria locateCriteria)
            throws SevereException,AdapterException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();
        
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(locateCriteria
                .getBenefitComponentId()));
       /* referenceValueSet.put("lobList", locateCriteria.getLobList());
        referenceValueSet.put("businessEntityList", locateCriteria
                .getBusinessEntityList());
        referenceValueSet.put("businessGroupList", locateCriteria
                .getBusinessGroupList());
        referenceValueSet.put("marketBusinessUnitList", locateCriteria
                .getMarketBusinessUnitList());*/
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        try {
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO);

        searchCriteria
                .setSearchQueryName(BusinessConstants.RETRIEVE_BENEFIT_HIERARCHY_REMOVAL);
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
  
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
       
        if (null != benefitSearchResults.getSearchResults()) {
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        }
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        locateResults.setTotalResultsCount(benefitSearchResults
                .getSearchResultCount());
        }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateBenefitHieararchiesToBeRemoved BenefitHierarchyLocateCriteria method in BenefitComponentAdapterManager",
					ex);
		}
        return locateResults;
    }


    /**
     * To locate valid benefit hierarchies
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws ServiceException
     */
    public LocateResults locateValidBenefitHieararchies(
            BenefitHierarchyLocateCriteria locateCriteria)
            throws SevereException,AdapterException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();
       
        HashMap referenceValueSet = new HashMap();
        try{
        referenceValueSet.put("benefitComponentId", new Integer(locateCriteria
                .getBenefitComponentId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO);

        searchCriteria
                .setSearchQueryName(BusinessConstants.RETRIEVE_DUPLICATE_BENEFIT_HIERARCHY);
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
//        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
//                .getAdapterServicesAccess();
//        try {
            benefitSearchResults = getAdapterService().searchObject(searchCriteria);
//        } catch (AdapterException adapterException) {
//            logAdapterException(null, adapterException);
//        }
        if (null != benefitSearchResults.getSearchResults()) {
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        }
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        }catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateValidBenefitHieararchies BenefitHierarchyLocateCriteria method in BenefitComponentAdapterManager",
					ex);
		}
        return locateResults;
    }


    /**
     * Copies benefit componet for checkout
     * 
     * @param srcBO
     * @param trgtBO
     */
    public SearchResults copyBenefitComponentForCheckOut(
            BenefitComponentBO srcBO, BenefitComponentBO trgtBO,
            AdapterServicesAccess adapterServicesAccess)
            throws SevereException,AdapterException {
        SearchResults searchResults = null;
      
        HashMap refValSet = new HashMap();
        
        BenefitComponentBO sourceBenefitComponentBO = srcBO;
        BenefitComponentBO targetBenefitComponentBO = trgtBO;
        refValSet.put("sourceKey", new Integer(sourceBenefitComponentBO
                .getBenefitComponentId()));
        refValSet.put("destinationKey", new Integer(targetBenefitComponentBO
                .getBenefitComponentId()));
        refValSet.put("user", sourceBenefitComponentBO.getCreatedUser());
        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_COMPONENT_BO,
                "copyOneBenefitComponentToAnotherForCheckOut", refValSet, 50);
    	searchResults = AdapterUtil.performSearch(searchCriteria,adapterServicesAccess);
        return searchResults;
    }


    /**
     * Method to get the referenceList
     * 
     * @param universebo
     * @return List
     * @throws SevereException
     */
    public List getReferenceValues(UniverseBO universebo)
            throws AdapterException {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("standardBenefitKey", new Integer(universebo
                .getStandardBenefitKey()));
        criteriaValues.put("itemType", universebo.getUniverseType());
        SearchCriteria searchCriteria = getAdapterSearchCriteria(universebo
                .getClass().getName(), "SearchForReferenceIds", criteriaValues,
                999999);
        SearchResults searchResults = performSearch(searchCriteria);

        if (searchResults.getSearchResultCount() > 0) {
            return searchResults.getSearchResults();
        }
        return null;

    }


    /**
     * Call Procedure to attach Benefit to BenefitComponent
     * 
     * @param newBO
     * @param bcId
     * @param benefitId
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean persistBenefitHierarchies_proc(
            int bcId, String benefitId,
            Audit audit,AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
        Logger
                .logInfo("BenefitComponentAdapterManager - Entering persistBenefitHierarchies_proc() : attachBenefitToBC");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("benefitComponentId", new Integer(bcId));
        refValSet.put("benefitIds", benefitId);
        refValSet.put("user", audit.getUser());

        SearchCriteria searchCriteria = getAdapterSearchCriteria(
                BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO, "attachSBToBC",
                refValSet, Integer.MAX_VALUE);
        searchResults = AdapterUtil.performSearch(searchCriteria, adapterServicesAccess);
        Logger
                .logInfo("BenefitComponentAdapterManager - Returning persistBenefitHierarchies_proc(): attachBenefitToBC");
        return true;
    }

    //added on 1/4/08
    public List getLevelHiddenCount(BenefitLine benefitLine)throws SevereException,AdapterException  {
        HashMap criteriaValues = new HashMap();
        criteriaValues.put("benefitComponentId", new Integer(benefitLine.getBenefitComponentId()));
        SearchCriteria searchCriteria = getAdapterSearchCriteria(benefitLine
                .getClass().getName(), "getLevelHiddenCountBO", criteriaValues,
                999999);
        SearchResults searchResults = performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return searchResults.getSearchResults();
        }
        return null;
    }
    //end

    /**
     * To delete the benefits associated to the benefitComponent in case the
     * types dont match
     * 
     * @param benefitComponent
     * @param userId
     * @return
     * @throws SevereException
     */
    public boolean deleteBenefit(BenefitComponent benefitComponent,
            String userId, AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
        Logger.logInfo("Entering the method for deleting benfit component");
        AdapterUtil.performRemove(benefitComponent, userId, adapterServicesAccess);
        Logger.logInfo("Returning the method for deleting benfit component");
        return false;
    }
    
    /**
     * Method to persist the hide/unhide of admin option in benefit component
     * @param adminOptionHideBO
     * @param userId
     * @param access
     * @return
     * @throws SevereException
     */
    public boolean hideAdminOptions(AdminOptionHideBO adminOptionHideBO,String userId,AdapterServicesAccess access) throws SevereException,AdapterException {
        //modified for solving performance issue on 14th Dec 2007    
        if(adminOptionHideBO.isModified()){
        	adminOptionHideBO.setBenefitCompSysId(adminOptionHideBO.getEntityId());
            AdapterUtil.performUpdate(adminOptionHideBO,userId,access);
        }
        //modification ends
        return true;
	}
    /**
     * Created on 6th March,2008
     * Method to update the Benefit Component.
     * 
     * @param benefitComponentBO
     *
     * @return boolean
     * @throws AdapterException
     */
    public boolean updateBenefitComponent(
            BenefitComponentBO benefitComponentBO)
            throws AdapterException {
        try{
            AdapterUtil.performUpdate(
                    benefitComponentBO,
                    BusinessConstants.TESTUSER);
        }catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new AdapterException( "Exception occured in updateBenefitComponent method in benefitComponentAdapterManager",
                    ex);
		}

        return true;
    }


	/**
	 * @param locateCriteria
	 * @return
	 */
	public LocateResults locateBenefitHieararchiesForPrintandView(com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria locateCriteria)
	 				throws SevereException,AdapterException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();
        
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(locateCriteria.getBenefitComponentId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO);

        searchCriteria
                .setSearchQueryName(BusinessConstants.LOCATE_BENEFIT_HIERARCHY_SEARCH_FOR_PRINT_AND_VIEW);
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitSearchResults.getSearchResults()) {
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        }
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        locateResults.setTotalResultsCount(benefitSearchResults
                .getSearchResultCount());
        return locateResults;

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
	public List getQuestionnaireValues(int adminOptionSysId, int benefitAdminSysId, int beneftComponentId,
			 int adminLvlOptSystemId, int beneftCompParentId)
			throws SevereException {
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("adminOptSysId", new Integer(adminOptionSysId));
		map.put("adminLvlOptSystemId", new Integer(adminLvlOptSystemId)); 
		map.put("beneftComponentId", new Integer(beneftComponentId));
		map.put("beneftCompParentId", new Integer(beneftCompParentId));

		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitComponentQuesitionnaireBO.class.getName(),
				"getQuestionnaire", map);
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
	public List getChildQuestionnaireValues(int selectedAnswerid, int questionnaireId, int beneftComponentId,int adminLvlOptSystemId,int benftDefnId)
			throws SevereException {

		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("beneftComponentId", new Integer(beneftComponentId));
		map.put("adminLvlOptSystemId", new Integer(adminLvlOptSystemId));
		map.put("bnftDefenitionId", new Integer(benftDefnId));
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitComponentQuesitionnaireBO.class.getName(),
				"getChildQuestionnaire", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		
	}
	/*
	 * This method is for persist new questionnare 
	 * 
	 * @param ComponentsBenefitAdministrationBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 * This method delete all the existing questionare and persist a new questionnare
	 */
	public void saveQuestionnare(ComponentsBenefitAdministrationBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		
		List questionnareList = administrationBO.getQuestionnareList();
		List newQuestions = administrationBO.getNewQuestions();
		List modifiedQuestions = administrationBO.getModifiedQuestions();
		List removedQuestions = administrationBO.getRemovedQuestions();

		Iterator it1 = newQuestions.iterator();
		while(it1.hasNext()){
			BenefitComponentQuesitionnaireBO quesitionnaireBOToAdd = 
				(BenefitComponentQuesitionnaireBO)it1.next();
			quesitionnaireBOToAdd.setBenefitComponentId(administrationBO.getBenefitComponentid());
			quesitionnaireBOToAdd.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			quesitionnaireBOToAdd.setCreatedUser(audit.getUser());
			quesitionnaireBOToAdd.setCreatedTimestamp(audit.getTime());
			quesitionnaireBOToAdd.setLastUpdatedUser(audit.getUser());
			quesitionnaireBOToAdd.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performInsert(quesitionnaireBOToAdd, audit.getUser(),access);

		}
		
		Iterator it2 = modifiedQuestions.iterator();
		while(it2.hasNext()){
			BenefitComponentQuesitionnaireBO quesitionnaireBOToUpdate = 
				(BenefitComponentQuesitionnaireBO)it2.next();
			quesitionnaireBOToUpdate.setBenefitComponentId(administrationBO.getBenefitComponentid());
			quesitionnaireBOToUpdate.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			quesitionnaireBOToUpdate.setCreatedUser(audit.getUser());
			quesitionnaireBOToUpdate.setCreatedTimestamp(audit.getTime());
			quesitionnaireBOToUpdate.setLastUpdatedUser(audit.getUser());
			quesitionnaireBOToUpdate.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performUpdate(quesitionnaireBOToUpdate, audit.getUser(),access);
		}
		
		Iterator it3 = removedQuestions.iterator();
		while(it3.hasNext()){
			BenefitComponentQuesitionnaireBO quesitionnaireBOToRemove = 
				(BenefitComponentQuesitionnaireBO)it3.next();
			quesitionnaireBOToRemove.setBenefitComponentId(administrationBO.getBenefitComponentid());
			quesitionnaireBOToRemove.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			quesitionnaireBOToRemove.setCreatedUser(audit.getUser());
			quesitionnaireBOToRemove.setCreatedTimestamp(audit.getTime());
			quesitionnaireBOToRemove.setLastUpdatedUser(audit.getUser());
			quesitionnaireBOToRemove.setLastUpdatedTimestamp(audit.getTime());
			AdapterUtil.performRemove(quesitionnaireBOToRemove, audit.getUser(),access);
		}
		
		}
	
    public LocateResults getBenefitCount(
			BenefitHierarchyLocateCriteria locateCriteria)
			throws SevereException {

		SearchResults searchResults = null;
		LocateResults locateResults = new LocateResults();

		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("benefitComponentId", new Integer(locateCriteria
				.getBenefitComponentId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.BENEFIT_HIERARCHY_ASSC_BO, "getMaxBenefitCount",referenceValueSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		if (null != searchResults.getSearchResults()) {
			locateResults.setTotalResultsCount(searchResults.getSearchResults()
					.size());
		}
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
				.setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults;
	}
    
    public List getNoteInfo(NotesToQuestionAttachmentBO attachmentBo)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List validQuestionnaires = null;
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(NotesToQuestionAttachmentBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getNoteInfo");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("primaryId",new Integer(attachmentBo.getPrimaryId()));
    	refValSet.put("secondaryId",new Integer(attachmentBo.getSecondaryId()));
    	refValSet.put("questionId",new Integer(attachmentBo.getQuestionId()));
    	refValSet.put("secondaryEntityType",attachmentBo.getPrimaryEntityType());
    	refValSet.put("primaryEntityType",attachmentBo.getSecondaryEntityType());
    	refValSet.put("bnftDefId",attachmentBo.getBnftDefId());
    	refValSet.put("benefitCompId",new Integer(attachmentBo.getBenefitCompId()));
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",
					e);
		}
		
		List resultList = searchResults.getSearchResults();
		
		return resultList;
	}
}

