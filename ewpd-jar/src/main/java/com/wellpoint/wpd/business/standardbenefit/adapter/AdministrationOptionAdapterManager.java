/*
 * Created on Mar 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AssociateAdminOptionToBenefitLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionAssociationBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u14617
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdministrationOptionAdapterManager {

    private BusinessTransactionContext getTransactionContext(String operation,
            String userId) {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(operation);
        btc.setBusinessTransactionUser("testUser");
        return btc;
    }


    private AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }


    /**
     * create benefit administration option
     * 
     * @param subObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */

    public boolean createAdministrationOptionObject(
            AdministrationOptionBO subObject, Audit audit, boolean insertFlag,
            AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
    	SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(audit.getUser());
        
        AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO) subObject;
        administrationOptionBO.setCreatedUser(audit.getUser());
        administrationOptionBO.setLastUpdatedUser(audit.getUser());
        administrationOptionBO.setLastUpdatedTimestamp(audit.getTime());
        administrationOptionBO.setCreatedTimestamp(audit.getTime());
// CR        
        administrationOptionBO.setSequenceNumber(getNextSequenceForAdminOption(subObject.getBenefitAdminSystemId())); 
// END - CR
       
            adapterServicesAccess.persistObject(administrationOptionBO,
                    subObject.getClass().getName(), btc);
      
        return true;
    }


    /**
     * Method to locate the necessary data from
     * ADMIN_OPT_TO_QSTN_ASSN,PSBL_ANSWR
     *  
     */
    public LocateResults locateDataForQuestionAssociation(
            AdminOptionLocateCriteria locateCriteria) throws ServiceException {
        //AdapterUtil.performSearch(searchcriteria,access);
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO");
        searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        searchCriteria.setSearchQueryName("getQuestionnaireForAdminOption");
        searchCriteria.setSearchDomain("medical");
        HashMap refValSet = new HashMap();
        //TODO Value hard coded in the AdminOptionLocateCriteria to be removed
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess("ewpd");
        refValSet.put("adminOptionSystemId", new Integer(locateCriteria
                .getAdminOptionSysId()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = adapterServicesAccess.searchObject(searchCriteria);
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
        	refValSet.put("benefitDefinitionKey",new Integer(locateCriteria
                .getBenefitDefinitionKey()));
        }
        else if(locateCriteria.getPSorProduct()){
            searchCriteria
            .setBusinessObjectName("com.wellpoint.wpd.common.product.bo.HideAdminOptionBO");
        	refValSet.put("benefitAdminSystemId", new Integer(locateCriteria
                    .getBenefitAdministrationSystemId()));
        	if(locateCriteria.getEntityType().equals("ProdStructure")){
        		searchCriteria.setSearchQueryName("getAssociatedBenefitAdministrationsForPS");
        		refValSet.put("entityId", new Integer(locateCriteria
                    .getEntityId()));
        		refValSet.put("benefitComponentId", new Integer(locateCriteria
                    .getBenefitComponentId()));
        	}else{
        		searchCriteria.setSearchQueryName("getAssociatedBenefitAdministrations");
        	
        		refValSet.put("productSysId", new Integer(locateCriteria
                .getEntityId()));
        		refValSet.put("benefitComponentId", new Integer(locateCriteria
                .getBenefitComponentId()));
        	} 
        }
        else{
        	searchCriteria
            .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionHideBO");
        	refValSet.put("benefitAdminSystemId", new Integer(locateCriteria
                    .getBenefitAdministrationSystemId()));
        	
        	//dateSegmentId
        	if(locateCriteria
                .getEntityType() !=null && locateCriteria
                .getEntityType().equalsIgnoreCase("contract") ){
        		refValSet.put("productSysId", new Integer(locateCriteria
                        .getEntityId()));
        		refValSet.put("benefitComponentId", new Integer(locateCriteria
                        .getBenefitComponentId()));
        		refValSet.put("dateSegmentId", new Integer(locateCriteria
                        .getDateSegmentId()));
        		
        		searchCriteria.setSearchQueryName("getAssociatedBenefitAdministrationsForContract");
        	}
        	else{
	        	searchCriteria.setSearchQueryName("getVisibleAssociatedBenefitAdministrations");
	        	refValSet.put("entityId", new Integer(locateCriteria
	                .getEntityId()));
	        	refValSet.put("entityType", new String(locateCriteria
	                .getEntityType()));
	        	refValSet.put("benefitComponentId", new Integer(locateCriteria
	                .getBenefitComponentId()));
        	}
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
    
   /* public boolean hideAdminOptions(AdminOptionHideBO adminOptionHideBO,String userId,AdapterServicesAccess access) throws SevereException {
        //modified for solving performance issue on 14th Dec 2007    
        if(adminOptionHideBO.isModified()){
            AdapterUtil.performUpdate(adminOptionHideBO,userId,access);
        }
        //modification ends
        return true;
	}*/

    public AdministrationOptionBO retrieve(AdministrationOptionBO optionBO)
            throws ServiceException {
        try {
            // connect to the adapter and retreive the non-adj mandate details
            optionBO = (AdministrationOptionBO) getAdapterService()
                    .retrieveObject(optionBO, optionBO.getClass().getName());

        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(2);
            errorParams.add(optionBO);
            errorParams.add(optionBO.getClass().getName());
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }

        return optionBO;

    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#persist(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param subObject
     * @param audit
     * @return true
     * @throws WPDException
     */
    public boolean delete(AdministrationOptionBO subObject, Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws ServiceException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
      
        	adapterServicesAccess.removeObject(subObject,
                    subObject.getClass().getName(), btc);
       
        return true;
    }


    /**
     * update benefit Administration option
     * 
     * @param subObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean updateAdministrationOptionObject(
            AdministrationOptionBO subObject, Audit audit, boolean insertFlag, AdapterServicesAccess adapterServicesAccess)
            throws SevereException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("edit");
        btc.setBusinessTransactionUser(audit.getUser());
      
        AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO) subObject;
        administrationOptionBO.setLastUpdatedUser(audit.getUser());
        administrationOptionBO.setLastUpdatedTimestamp(audit.getTime());
       
            adapterServicesAccess.persistObject(administrationOptionBO,
                    subObject.getClass().getName(), btc);

       
        return true;
    }


    public LocateResults isAdminOptionPresent(
            AdministrativeOptionLocateCriteria criteria) {

        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO");
        searchCriteria.setMaxSearchResultSize(100);
        searchCriteria.setSearchQueryName("isAdminOptionPresent");
        searchCriteria.setSearchDomain("medical");
        HashMap refValSet = new HashMap();
        refValSet.put("benefitAdminSystemId", new Integer(criteria
                .getBenefitAdministrationSystemId()));
        refValSet.put("adminOptionSystemId", new Integer(criteria
                .getAdminOptionId()));
        refValSet.put("adminLevelOptionAssnSystemId", new Integer(criteria
                .getAdminLevelAssociationSysemId()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            //logAdapterExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),adapterException);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        return locateResults;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BenefitDefinitionAdapterManager#persist(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param subObject
     * @param audit
     * @return true
     * @throws WPDException
     */
    public boolean delete(AdministrationOptionAssociationBO subObject,
            Audit audit,AdapterServicesAccess adapterServicesAccess) throws ServiceException,AdapterException {
    	
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());
        
        adapterServicesAccess.removeObject(subObject,
                    subObject.getClass().getName(), btc); 
        return true;
    }
    


    public List getPreviousQuestion(AdminOptionLocateCriteria criteria) {

        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO");
        searchCriteria.setMaxSearchResultSize(100);
        searchCriteria.setSearchQueryName("getPreviousQuestion");
        searchCriteria.setSearchDomain("medical");
        HashMap refValSet = new HashMap();
        refValSet.put("adminOptionSystemId", new Integer(criteria
                .getAdminOptSysIdForUpdate()));
        refValSet.put("adminLevelOptionAssnSystemId", new Integer(criteria
                .getAdminLevelOptionAssnSysId()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            //logAdapterExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),adapterException);
        }
        return searchResults.getSearchResults();
    }


    /**
     * @param subObject
     * @param adapterException
     */
    private void logAdapterException(Object obj,
            AdapterException adapterException) throws ServiceException {
        List errorParams = new ArrayList(2);
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new ServiceException("Adapter Exception occured", errorParams,
                adapterException);

    }

	 /**
    * 
    * @param productKey
    * @return
    * @throws SevereException
    */
   public int getNextSequenceForAdminOption(int benefitAdminSysId)
           throws SevereException {
       HashMap hashMap = new HashMap();
       hashMap.put("benefitAdminSystemId", new Integer(benefitAdminSysId));
       SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
               AdministrationOptionBO.class.getName(), "getNextSequence", hashMap);
       SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
       AdministrationOptionBO administrationOptionBO = new AdministrationOptionBO();
       if (null != searchResults) {
           if (searchResults.getSearchResultCount() == 0)
               return 1;
           administrationOptionBO = (AdministrationOptionBO) searchResults.getSearchResults()
                   .get(0);
       }
       return administrationOptionBO.getSequenceNumber();
   }
   
   public LocateResults locateAssnAdmnOptToBnft(
   		AssociateAdminOptionToBenefitLocateCriteria locateCriteria, String user) throws AdapterException {
    //AdapterUtil.performSearch(searchcriteria,access);
    SearchCriteria searchCriteria = new SearchCriteriaImpl();
    SearchResults searchResults = null;
    LocateResults locateResults = new LocateResults();
    List locateResultsList = new ArrayList();
    searchCriteria
            .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO");
    searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    searchCriteria.setSearchQueryName("callAssnAdminOptToBnft");
    searchCriteria.setSearchDomain("medical");
    HashMap refValSet = new HashMap();
    //TODO Value hard coded in the AdminOptionLocateCriteria to be removed
    AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
            .getAdapterServicesAccess("ewpd");
       
    AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO) 
											locateCriteria.getAdministrationOptionBO();
    if(null != administrationOptionBO){
    	refValSet.put("adminLvlSysId", new Integer(administrationOptionBO
            .getAdminLevelSystemId()));
    	refValSet.put("adminOptSysId", WPDStringUtil
    		.getTildaStringFromList(administrationOptionBO.getAdminOptionsList()));
    	refValSet.put("benefitAdminSystemId", new Integer(administrationOptionBO
                .getBenefitAdminSystemId()));
    	refValSet.put("blvlBnftLvlId", new Integer(administrationOptionBO
                .getBenefitLevelSysId()));
    	refValSet.put("benefitDefinitionKey", new Integer(administrationOptionBO
                .getBenefitDefinitionKey()));    	
    	refValSet.put("benefitId", new Integer(administrationOptionBO
                .getBenefitId()));    	
    }
    
    refValSet.put("userId", user);
    refValSet.put("referenceId", WebConstants.ADJUD_REFERNCE_ID);
    refValSet.put("questionDesc", WebConstants.ADJUD_QSTN_DESC);
    
    searchCriteria.setReferenceValueSet(refValSet);
    try {
        searchResults = adapterServicesAccess.searchObject(searchCriteria);
    } catch (AdapterException adapterException) {
    	Logger.logError(adapterException);
        throw new AdapterException("Exception occurred in attaching adminoption to benefit",adapterException);
    }
    if(null != searchResults){
	    locateResults.setLocateResults(searchResults.getSearchResults());
	    locateResults
	            .setTotalResultsCount(searchResults.getSearchResultCount());
    }
    return locateResults;
}
}
