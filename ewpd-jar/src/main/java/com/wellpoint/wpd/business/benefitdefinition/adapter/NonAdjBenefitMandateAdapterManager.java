/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.MandateListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author u13664
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NonAdjBenefitMandateAdapterManager {

    private BusinessTransactionContext getTransactionContext(String operation,
            String userId) {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(operation);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        return btc;
    }


    private AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(BusinessConstants.EWPD);
        return adapterServicesAccess;
    }


    /**
     * This method for inserting the nonAdjMandateDetails setting the
     * benefitIdname,Created user,timestamp;updated user,timestamp to
     * BenefitMandateBO connect to the adapter for inserting the values in the
     * BenefitMandateBO into the BMNDT_BNFT_MNDT table
     */

    public boolean createBenefitMandateObject(
            BenefitMandateBO benefitMandateBO, Audit audit, boolean insertFlag, AdapterServicesAccess adapterServicesAccess)
            throws ServiceException,AdapterException {

        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());

        // Remove hard coding whn actual standard benefit object gets created.
        benefitMandateBO.setBenefitIdName(BusinessConstants.BENEFIT);
        benefitMandateBO.setCreatedUser(audit.getUser());
        benefitMandateBO.setLastUpdatedUser(audit.getUser());
        benefitMandateBO.setLastUpdatedTimestamp(audit.getTime());
        benefitMandateBO.setCreatedTimestamp(audit.getTime());
       
            adapterServicesAccess.persistObject(benefitMandateBO,
                    benefitMandateBO.getClass().getName(), btc);
        return true;
    }


    /**
     * This method for inserting the nonAdjMandateDetails setting the
     * benefitIdname,Created user,timestamp;updated user,timestamp to
     * BenefitMandateBO connect to the adapter for inserting the values in the
     * BenefitMandateBO into the BMNDT_BNFT_MNDT table
     */

    public boolean createStateObject(StateBO stateBO, Audit audit,
            boolean insertFlag, AdapterServicesAccess adapterServicesAccess) throws ServiceException,AdapterException {

        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());
        
        // Remove hard coding whn actual standard benefit object gets created.

        stateBO.setCreatedUser(audit.getUser());
        stateBO.setLastUpdatedUser(audit.getUser());
        stateBO.setLastUpdatedTimestamp(audit.getTime());
        stateBO.setCreatedTimestamp(audit.getTime());
            adapterServicesAccess.persistObject(stateBO, stateBO.getClass()
                    .getName(), btc);        
        return true;
    }


    /**
     * This method for inserting the citation no details
     * 
     * connect to the adapter for inserting the values in the BenefitMandateBO
     * into the BMNDT_BNFT_MNDT table
     */

    public boolean createCitationList(CitationNumberBO citationNumberBO,
            Audit audit, boolean insertFlag, AdapterServicesAccess adapterServicesAccess) throws ServiceException,AdapterException {

        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());
     
        // Remove hard coding whn actual standard benefit object gets created.

        citationNumberBO.setCreatedUser(audit.getUser());
        citationNumberBO.setLastUpdatedUser(audit.getUser());
        citationNumberBO.setLastUpdatedTimestamp(audit.getTime());
        citationNumberBO.setCreatedTimestamp(audit.getTime());

      
            adapterServicesAccess.persistObject(citationNumberBO,
                    citationNumberBO.getClass().getName(), btc);
        return true;
    }


    /**
     * @param subObject
     * @param audit
     */
    public boolean deleteCitationNumber(CitationNumberBO subObject, Audit audit,AdapterServicesAccess access)
            throws ServiceException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
        try {
            access.removeObject(subObject,
                    subObject.getClass().getName(), btc);
        } catch (AdapterException adapterException) {
            logAdapterException(subObject, adapterException);
        }
        return true;
    }


    /**
     * @param subObject
     * @param audit
     */
    public boolean deleteStateObject(StateBO subObject, Audit audit,AdapterServicesAccess adapterServicesAccess )
            throws ServiceException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
      
            adapterServicesAccess.removeObject(subObject,
                    subObject.getClass().getName(), btc); 
        
        return true;
    }


    /**
     * This method for retrieving the nonAdjMandateDetails for the corresponding
     * mandateId
     * 
     * @param nonAdjMandateRetrieveBOImpl
     * @return
     * @throws WPDException
     */
    public Object retrieve(BenefitMandateBO benefitMandateBOImpl)
            throws ServiceException {
    	
    	TimeHandler th = new TimeHandler();
        Logger.logInfo(th.startPerfLogging("Product Coverage","NonAdjBenefitMandateAdapterManager","execute"));

        BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
        try {
            // connect to the adapter and retreive the non-adj mandate details

            benefitMandateBO = (BenefitMandateBO) getAdapterService()
                    .retrieveObject(benefitMandateBOImpl,
                            benefitMandateBOImpl.getClass().getName());

        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(benefitMandateBOImpl);
            errorParams.add(benefitMandateBOImpl.getClass().getName());
            if (0 == benefitMandateBO.getBenefitMandateId()) {
            	Logger.logInfo(th.endPerfLogging());
                return benefitMandateBOImpl;
            }
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);

        }
        
        Logger.logInfo(th.endPerfLogging());

        return benefitMandateBOImpl;
    }


    /**
     * This method for retrieving the nonAdjMandateDetails for the corresponding
     * mandateId
     * 
     * @param nonAdjMandateRetrieveBOImpl
     * @return
     * @throws WPDException
     */
    public LocateResults locateCitationNumber(
            BenefitMandateLocateCriteria benefitMandateLocateCriteria)
            throws AdapterException,SevereException {
    	
    	TimeHandler th = new TimeHandler();
        Logger.logInfo(th.startPerfLogging("Product Coverage","NonAdjBenefitMandateAdapterManager","locateCitationNumber"));
        
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        LocateResults locateResults = new LocateResults();
        SearchResults searchResults = null;
        try {
        searchCriteria
                .setBusinessObjectName(BusinessConstants.CITATION_NUMBER_BO);
        searchCriteria.setMaxSearchResultSize(999);
        searchCriteria
                .setSearchQueryName(BusinessConstants.GET_CITATION_NUMBER);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_MANDATE_ID, new Integer(
                benefitMandateLocateCriteria.getBenefitMandateId()));
        searchCriteria.setReferenceValueSet(refValSet);
        
            searchResults = getAdapterService().searchObject(searchCriteria);
 
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
            } catch (Exception ex) {
    			List errorParams = new ArrayList();
    			String obj = ex.getClass().getName();
    			errorParams.add(obj);
    			errorParams.add(obj.getClass().getName());
    			throw new AdapterException(
    					"Exception occured in locateCitationNumber BenefitMandateLocateCriteria method in NonAdjBenefitMandateAdapterManager",
    					ex);
    		}
            
        Logger.logInfo(th.endPerfLogging());
        return locateResults;
    }


    /**
     * This method for retrieving the nonAdjMandateDetails for the corresponding
     * mandateId
     * 
     * @param nonAdjMandateRetrieveBOImpl
     * @return
     * @throws WPDException
     */
    public LocateResults locateStateObject(StateLocateCriteria criteria)
            throws AdapterException,SevereException {
    	
    	TimeHandler th = new TimeHandler();
        Logger.logInfo(th.startPerfLogging("Product Coverage","NonAdjBenefitMandateAdapterManager","locateStateObject"));
        
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        LocateResults locateResults = new LocateResults();
        SearchResults searchResults = null;
        try {
        searchCriteria.setBusinessObjectName(BusinessConstants.STATE_BO);
        searchCriteria.setMaxSearchResultSize(999);
        searchCriteria.setSearchQueryName(BusinessConstants.GET_STATE_OBJECT);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_MANDATE_ID, new Integer(
                criteria.getBenefitMandateId()));
        searchCriteria.setReferenceValueSet(refValSet);
       
            searchResults = getAdapterService().searchObject(searchCriteria);
      
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        }catch (Exception ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateStateObject StateLocateCriteria method in NonAdjBenefitMandateAdapterManager",
					ex);
		}
        
        Logger.logInfo(th.endPerfLogging());
        return locateResults;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(LocateCriteria locateCriteria)
            throws AdapterException,SevereException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        LocateResults locateResults = new LocateResults();
        SearchResults searchResults = null;
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = (BenefitMandateLocateCriteria) locateCriteria;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_MANDATE_BO);
        searchCriteria.setMaxSearchResultSize(benefitMandateLocateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.getAssociatedBenefitMandate);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_SYSTEM_ID, new Integer(
                benefitMandateLocateCriteria.getBenefitMasterSystemId()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (Exception ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate LocateCriteria method in NonAdjBenefitMandateAdapterManager",
					ex);
		}
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        return locateResults;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param BenefitMandateBO
     * @return
     * @throws WPDException
     */
    public boolean updateBenefitMandateObject(
            BenefitMandateBO benefitMandateBO, Audit audit, boolean insertFlag,AdapterServicesAccess adapterServicesAccess)
            throws ServiceException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("edit");
        btc.setBusinessTransactionUser(audit.getUser());
        //		 Remove hard coding whn actual standard benefit object gets created.
        benefitMandateBO.setBenefitIdName(BusinessConstants.BENEFIT);
        benefitMandateBO.setCreatedUser(audit.getUser());
        benefitMandateBO.setLastUpdatedUser(audit.getUser());
        benefitMandateBO.setLastUpdatedTimestamp(audit.getTime());
        benefitMandateBO.setCreatedTimestamp(audit.getTime());

       
            adapterServicesAccess.persistObject(benefitMandateBO,
                    benefitMandateBO.getClass().getName(), btc);
       
        return true;
    }


    /**
     * @param subObject
     * @param audit
     */
    public boolean deleteBenefitMandate(BenefitMandateBO subObject, Audit audit)
            throws ServiceException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
       
        	this.getAdapterService().removeObject(subObject,
                    subObject.getClass().getName(), btc); 
       
        return true;
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


    private void logAdapterException(Object obj,
            AdapterException adapterException) throws ServiceException {
        List errorParams = new ArrayList();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
                errorParams, adapterException);

    }


    /**
     * This method is to get the mandate list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locateMandateList(LocateCriteria locateCriteria)
            throws SevereException {
        // create the object for the locate results
        LocateResults locateResults = new LocateResults();
        MandateListLocateCriteria mandateListLocateCriteria = (MandateListLocateCriteria) locateCriteria;
        // create a list
        List locateResultList = new ArrayList();
        // create the reference of the SearchResults
        SearchResults searchResults = null;
        // create the object for the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        // set the required things in the search criteria
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_MANDATE_LIST_BO);
        searchCriteria.setMaxSearchResultSize(mandateListLocateCriteria
                .getMaximumResultSize());
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        searchCriteria
                .setSearchQueryName(BusinessConstants.SEARCH_MANDATE_LIST);
        // create the referenceValueSet Hashmap
        HashMap refValSet = new HashMap();
        if ("".equals((mandateListLocateCriteria.getEffDate()).toString())) {
            refValSet.put(BusinessConstants.EFFDATE, null);
        } else {
            refValSet.put(BusinessConstants.EFFDATE, (mandateListLocateCriteria
                    .getEffDate()).toString());
        }
        if ("".equals((mandateListLocateCriteria.getExpDate()).toString())) {
            refValSet.put(BusinessConstants.EXPDATE, null);
        } else {
            refValSet.put(BusinessConstants.EXPDATE, (mandateListLocateCriteria
                    .getExpDate()).toString());
        }
        if (mandateListLocateCriteria.getMandateId() == 0) {
            refValSet.put(BusinessConstants.MANDATE_ID, null);
        } else {
            refValSet.put(BusinessConstants.MANDATE_ID, new Integer(
                    mandateListLocateCriteria.getMandateId()));
        }
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            // connect to the adapter and get the searchResults
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria.getBusinessObjectName());
            errorParams.add(searchCriteria.getSearchQueryName());
            throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
                    errorParams, adapterException);
        }
        // set the locateResultList to the locateResults
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        // return locate results
        return locateResults;
    }


    /**
     * This method is to validate the date range
     * 
     * @param BenefitMandateLocateCriteria
     * @return
     * @throws WPDException
     */
    public List validateDateRange(BenefitMandateLocateCriteria locateCriteria)
            throws ServiceException {
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.BENEFIT_MANDATE_BO);
        searchCriteria.setMaxSearchResultSize(locateCriteria
                .getMaximumResultSize());
        searchCriteria
                .setSearchQueryName(BusinessConstants.VALIDATE_DATE_RANGE);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_SYSTEM_ID, new Integer(
                locateCriteria.getBenefitMasterSystemId()));
        refValSet.put(BusinessConstants.EFFECTIVE_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getEffectiveDate()));
        refValSet.put(BusinessConstants.EXPIRY_DATE, WPDStringUtil
                .getStringDate(locateCriteria.getExpiryDate()));
        refValSet.put(BusinessConstants.BENEFIT_MANDATE_ID, new Integer(
                locateCriteria.getBenefitMandateId()));
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
}