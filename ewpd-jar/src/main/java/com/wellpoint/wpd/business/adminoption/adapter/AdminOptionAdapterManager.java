/*
 * AdminOptionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */

package com.wellpoint.wpd.business.adminoption.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionQuestionLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireCpyBO;
import com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractTierQuesitionnaireBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.productstructure.bo.EntityBenefitAdministrationPSBO;

/**
 * AdapterManager for the Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionAdapterManager {

    /**
     * Default Constructor
     *  
     */
    public AdminOptionAdapterManager() {
    }


    /**
     * Method to insert Admin Option Question to the database.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistAdminOptionQuestion(
            AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit) throws SevereException, AdapterException {
       
        List list = null;
        
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
      												.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "persistAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
	        if(null != associatedQuestionBO){
	        	list = new ArrayList(associatedQuestionBO.getAssociatedQuestionList().size());
	        	if(null != list){
	        		list = associatedQuestionBO.getAssociatedQuestionList();
		            Iterator iterator = list.iterator();
		            while(iterator.hasNext()){
		                HashMap criteriaMap = new HashMap();
		                int seqNumber = 1;
		                
		                criteriaMap.put("adminOptionId", new Integer(associatedQuestionBO
		                        .getAdminOptionId()));
		                AssociatedQuestionBO associatedQuestionBO2 = (AssociatedQuestionBO)iterator.next();
		                
		                SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		                        AssociatedQuestionBO.class.getName(), "getSeqNumber",
		                        criteriaMap);
		                SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		                if (searchResults.getSearchResultCount() > 0) {
		                    AssociatedQuestionBO tempAssociatedQuestionBO = (AssociatedQuestionBO) searchResults
		                            .getSearchResults().get(0);
		                    seqNumber = tempAssociatedQuestionBO.getSeqNumber() + 1;
		
		                }
		                associatedQuestionBO2.setSeqNumber(seqNumber);
		                associatedQuestionBO2.setAdminOptionId(associatedQuestionBO.getAdminOptionId());
		                associatedQuestionBO2.setReferenceId(associatedQuestionBO.getReferenceId());
		                associatedQuestionBO2.setCreatedUser(audit.getUser());
		                associatedQuestionBO2.setCreatedTimestamp(audit.getTime());
		                associatedQuestionBO2.setLastUpdatedUser(audit.getUser());
		                associatedQuestionBO2.setLastUpdatedTimestamp(audit.getTime());
		                AdapterUtil.performInsert(associatedQuestionBO2, audit.getUser(), adapterServicesAccess); 
		            }
	        	}
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this , "persistAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
        
        }catch (Exception e){ 
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this , "persistAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
        	 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }
    
    public List getduplicateReferenceQuesations(int adminOptionId) throws SevereException,AdapterException{
    	HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("adminOptionId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionnaireBO.class.getName(),
                "searchDuplicateReferenceQuestions", referenceValueSet);
        return AdapterUtil.performSearch(searchCriteria).getSearchResults();
    }
    
    public List getRemainingQuestions(String quesnrId,boolean isRootQuestion) throws SevereException,AdapterException{
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("questionnaireId", new Integer(quesnrId));
        SearchCriteria searchCriteria  = null;
        if(isRootQuestion){
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    AssociatedQuestionnaireBO.class.getName(),
                    "searchRemaingRootQuestions", referenceValueSet);
        }
        else{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionnaireBO.class.getName(),
                "searchRemaingQuestions", referenceValueSet);
        }
        return AdapterUtil.performSearch(searchCriteria).getSearchResults();
    }
    
    public void updateReOrderedQuestions(AssociatedQuestionnaireBO bo,String user)throws SevereException{
        AdapterUtil.performUpdate(bo,user);
    }
    
    public List getQuestionnaire(int adminOptionId) throws SevereException,AdapterException{
        
        List questionnaire = null;
        HashMap referenceValueSet = new HashMap();
        if (-1 != adminOptionId) {
            referenceValueSet.put("adminOptionId", new Integer(adminOptionId));
           
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionnaireBO.class.getName(),
                "searchQuestionnaire", referenceValueSet);
        questionnaire = AdapterUtil.performSearch(searchCriteria).getSearchResults();
        
        return questionnaire;
    }
    
    public List getProductViewQuestionnaire(int adminOptionId,int productId) throws SevereException,AdapterException{
        
        List questionnaire = null;
        HashMap referenceValueSet = new HashMap();
        if (-1 != adminOptionId && -1!= productId) {
            referenceValueSet.put("adminOptionId", new Integer(adminOptionId));
            referenceValueSet.put("productId", new Integer(productId));
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionnaireBO.class.getName(),
                "searchProductViewQuestionnaire", referenceValueSet);
        questionnaire = AdapterUtil.performSearch(searchCriteria).getSearchResults();
        
        return questionnaire;
    }
    public List getContractViewQuestionnaire(int adminOptionId,int contractSysId) throws SevereException,AdapterException{
        
        List questionnaire = null;
        HashMap referenceValueSet = new HashMap();
        if (-1 != adminOptionId && -1!= contractSysId) {
            referenceValueSet.put("adminOptionId", new Integer(adminOptionId));
            referenceValueSet.put("contractSysId",new Integer(contractSysId));
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionnaireBO.class.getName(),
                "searchContractViewQuestionnaire", referenceValueSet);
        questionnaire = AdapterUtil.performSearch(searchCriteria).getSearchResults();
        
        return questionnaire;
    }
    
    
    
    /**
     * 
     * @param questionnaireIds
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public List getChildQuestionnaireForPRUpdate(List questionnaireIds) throws SevereException,AdapterException{
        
        List questionnaire = null;
        HashMap referenceValueSet = new HashMap();
        if (null != questionnaireIds ) {
            referenceValueSet.put("questionnaireHierarchySystemId", getCombinedHierarchySysId(questionnaireIds));            
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ChildQuestionnaireBO.class.getName(),
                "locateChildQuestionnairesForPR", referenceValueSet);
        questionnaire = AdapterUtil.performSearch(searchCriteria).getSearchResults();        
        return questionnaire;
    }
    /**
     * The method is to append the items in a list into a String 
     * @param questionnaireIds
     * @return eg: (1,2)
     */
    private Object getCombinedHierarchySysId(List questionnaireIds) {
		StringBuffer buffer = new StringBuffer();
		
		Integer id = null;
		if(questionnaireIds != null && questionnaireIds.size() > 0){
			buffer.append("(");
			for(int i =0 ; i < questionnaireIds.size() ; i++){
				id = (Integer)questionnaireIds.get(i);	
				buffer.append(id);
				if(i != questionnaireIds.size()-1){
				buffer.append(",");
				}
			}
			buffer.append(")");
		}		
		return buffer;
    }
    
    /**
     * Method to update Admin Option to the database.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateAdminOptionQuestion(
            AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit) throws SevereException, AdapterException {
        List associatedQuestionList = associatedQuestionBO
                .getAssociatedQuestionList();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
										.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "updateAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
        	if (null != associatedQuestionList) {
                Iterator itr = associatedQuestionList.iterator();
                while (itr.hasNext()) {
                    associatedQuestionBO = (AssociatedQuestionBO) itr.next();
                    associatedQuestionBO.setLastUpdatedUser(audit.getUser());
                    associatedQuestionBO.setLastUpdatedTimestamp(audit.getTime());
                    //modified for solving performance issue on 18th Dec 2007
                    if(associatedQuestionBO.isModified()){
                        AdapterUtil
                        .performUpdate(associatedQuestionBO, audit.getUser(),adapterServicesAccess);
                    }
                    //modification ends
                }
            }
        	AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this , "updateAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
       
        }catch (Exception e){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this , "updateAdminOptionQuestion(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
     	
            throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * Method to update Admin Option to the database.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean adminOptionQuestionUpdate(
            AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit) throws SevereException, AdapterException {
        List associatedQuestionList = associatedQuestionBO
                .getAssociatedQuestionList();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
											.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "adminOptionQuestionUpdate(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
	        if (null != associatedQuestionList) {
	            Iterator itr = associatedQuestionList.iterator();
	            while (itr.hasNext()) {
	                associatedQuestionBO = (AssociatedQuestionBO) itr.next();
	                String reference = associatedQuestionBO.getReferenceId();
	                String[] referenceArray = reference.split("~");
	                associatedQuestionBO.setReferenceId(referenceArray[1]);
	                associatedQuestionBO.setLastUpdatedUser(audit.getUser());
	                associatedQuestionBO.setLastUpdatedTimestamp(audit.getTime());
	                AdapterUtil
						.performUpdate(associatedQuestionBO, audit.getUser(),adapterServicesAccess);
	            }
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this , "adminOptionQuestionUpdate(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
        
        }catch (Exception e){ 
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this , "adminOptionQuestionUpdate(AssociatedQuestionBO associatedQuestionBO,AdminOptionBO adminOptionBO, Audit audit)");
            throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * Method to delete Admin Option Question to the database.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean deleteAdminOptionQuestion(
            AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit) throws SevereException, AdapterException {
    	try{
    		AdapterUtil.performRemove(associatedQuestionBO, audit.getUser());
    	}catch (Exception e){        	
    		 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * Method to insert a newly created Admin Option to the database.
     * 
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persistAdminOption(AdminOptionBO adminOptionBO, Audit audit)
            throws SevereException, AdapterException {
        SequenceAdapterManager sequenceAdapterManager = null;
        sequenceAdapterManager = new SequenceAdapterManager();
        try{
	        adminOptionBO.setAdminOptionId(sequenceAdapterManager
	                .getNextAdminOptionSequence());
	        if(adminOptionBO.getVersion()<=1){
	        adminOptionBO.setAdminOptionParentSysId(adminOptionBO.getAdminOptionId());
	        }
	        adminOptionBO.setCreatedUser(audit.getUser());
	        adminOptionBO.setCreatedTimestamp(audit.getTime());
	        adminOptionBO.setLastUpdatedUser(audit.getUser());
	        adminOptionBO.setLastUpdatedTimestamp(audit.getTime());
	        AdapterUtil.performInsert(adminOptionBO, audit.getUser());
        }catch (Exception e){        	
        	 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * Method to update the Admin Option details in the database.
     * 
     * @param adminOptionBO   
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean updateAdminOption(AdminOptionBO adminOptionBO, Audit audit)
            throws SevereException, AdapterException {
    	try{
	        adminOptionBO.setLastUpdatedUser(audit.getUser());
	        adminOptionBO.setLastUpdatedTimestamp(audit.getTime());
	        AdapterUtil.performUpdate(adminOptionBO, audit.getUser());
    	}catch (Exception e){        	
    		 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * This method for retrieving the Admin Option Details for the corresponding
     * adminOptionId
     * 
     * @param adminOptionBO
     * @return AdminOptionBO Object
     * @throws SevereException
     */
    public Object retrieve(AdminOptionBO adminOptionBO) throws SevereException {
        adminOptionBO = (AdminOptionBO) AdapterUtil
                .performRetrieve(adminOptionBO);
        return adminOptionBO;
    }


    /**
     * Method to retrieve the Admin Option details from the database, using
     * Admin Name.
     * 
     * @param adminOptionBO
     * @return AdminOptionBO
     * @throws SevereException
     */
    public AdminOptionBO retrieveByName(AdminOptionBO adminOptionBO)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("adminName", adminOptionBO.getAdminName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                adminOptionBO.getClass().getName(), "retrieveByAdminName",
                criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (AdminOptionBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * Method to retrieve Latest Version of Admin Option , using Admin Name.
     * 
     * @param adminOptionBO
     * @return AdminOptionBO
     * @throws SevereException
     */
    public AdminOptionBO retrieveLatestVersion(AdminOptionBO adminOptionBO)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("adminName", adminOptionBO.getAdminName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                adminOptionBO.getClass().getName(), "retrieveLatestVersion",
                criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (AdminOptionBO) searchResults.getSearchResults().get(0);
        }
        return null;
    }


    /**
     * Method to retrieve all the Latest Version of Admin Options , having
     * PUBLISHED status.
     * 
     * @return AdminOptionBO
     * @throws SevereException
     */
    public List retrieveAllAdminOptions() throws SevereException {
        HashMap criteriaMap = new HashMap();
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO",
                "retrieveAllLatestAdminOptons", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return searchResults.getSearchResults();
        }
        return null;
    }


    /**
     * Method to retrieve the latest version number
     * 
     * @param adminOptionBO
     * @return int
     * @throws SevereException
     */
    public int retrieveLatestVersionNumber(AdminOptionBO adminOptionBO)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
//        List adminOptionSearchResult = new ArrayList();

        HashMap criteriaMap = new HashMap();
        criteriaMap.put("adminName", adminOptionBO.getAdminName());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                adminOptionBO.getClass().getName(), "retrieveByAdminName",
                criteriaMap);
        List adminOptionSearchResult = AdapterUtil.performSearch(searchCriteria,
                adapterServicesAccess).getSearchResults();
        if (null != adminOptionSearchResult
                && adminOptionSearchResult.size() > 0) {
            adminOptionBO = (AdminOptionBO) adminOptionSearchResult.get(0);
            return adminOptionBO.getVersion();
        }
        return -1;
    }


    /**
     * Method to retrieve the Admin Option details from the database, using
     * Admin Name.
     * 
     * @param associatedQuestionBO
     * @return AssociatedQuestionBO
     * @throws SevereException
     */
    public AssociatedQuestionBO checkDuplicateAdminOptionQuestion(
            AssociatedQuestionBO associatedQuestionBO) throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put("adminOptionId", new Integer(associatedQuestionBO
                .getAdminOptionId()));
        criteriaMap.put("adminOptionQuestionId", new Integer(
                associatedQuestionBO.getAssociatedQuestionId()));
        criteriaMap.put("adminOptionQuestionName", 
                associatedQuestionBO.getAssociatedQuestionDesc());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                AssociatedQuestionBO.class.getName(),
                "checkAdminQuestionDuplicate", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        if (searchResults.getSearchResultCount() > 0) {
            return (AssociatedQuestionBO) searchResults.getSearchResults().get(
                    0);
        }
        return null;
    }


    /**
     * Method to search the question
     * 
     * @param adminOptionQuestionLocateCriteria
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults searchAdminOptionQuestion(
            AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria)
            throws SevereException {
        LocateResults locateResults = new LocateResults();
        List searchResult = this
                .getAdminOptionQuestion(adminOptionQuestionLocateCriteria
                        .getAdminOptionId());
        locateResults.setLocateResults(searchResult);
        return locateResults;
    }

    /**
     * Method to search the question
     * 
     * @param adminOptionQuestionLocateCriteria
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults locateAdminOptionRootQuestion(
            AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria)
            throws SevereException {
        LocateResults locateResults = new LocateResults();
        List searchResult = this
                .getRootQuestion(adminOptionQuestionLocateCriteria
                        .getAdminOptionId());
        locateResults.setLocateResults(searchResult);
        return locateResults;
    }

    /**
     * Method to search the question
     * 
     * @param adminOptionId
     * @return List
     * @throws SevereException
     */
    public List getAdminOptionQuestion(int adminOptionId)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        //List locateResults = null;
        criteriaMap.put("adminOptionId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO.class
                                .getName(), "retrieveAdminOptionQuestions",
                        criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
        Iterator iterator = locateResults.iterator();
        List list = new ArrayList(locateResults.size());
        if(null != list){
        	while (iterator.hasNext()) {
                AssociatedQuestionBO associatedQuestionBO = (AssociatedQuestionBO) iterator
                        .next();
                associatedQuestionBO.setReferenceId(associatedQuestionBO
                        .getReferenceDesc()
                        + '~' + associatedQuestionBO.getReferenceId());
                list.add(associatedQuestionBO);
            }
        }
        return list;
    }

    /**
     * Method to search the question
     * 
     * @param adminOptionId
     * @return List
     * @throws SevereException
     */
    public List getRootQuestion(int adminOptionId)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        //List locateResults = null;
        criteriaMap.put("adminOptionId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        RootQuestionnaireBO.class
                                .getName(), "locateRootQuestions",
                        criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }
    
    /**
     * Method to search the question
     * 
     * @param adminOptionId
     * @return List
     * @throws SevereException
     */
    public List getMaxVersionForAdminOption(int adminOptionId)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        //List locateResults = null;
        criteriaMap.put("adminOptionId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        RootQuestionnaireBO.class
                                .getName(), "getMaxAdminOptionVersion",
                        criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }

    /**
     * Method to check whether the admin option is in use
     * 
     * @param adminOptionBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isAdminOptionInUse(AdminOptionBO adminOptionBO)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
       // List searchResult = new ArrayList();
        criteriaMap.put("adminOptionSystemId", new Integer(adminOptionBO
                .getAdminOptionId()));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO.class
                                .getName(), "isAdminOptionInUse", criteriaMap);
        List searchResult = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        if (null != searchResult && searchResult.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * Method to check whether the admin option is in use
     * 
     * @param associatedQuestionBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isAdminOptionQuestionInUse(
            AssociatedQuestionBO associatedQuestionBO) throws SevereException {
        HashMap criteriaMap = new HashMap();
       // List searchResult = new ArrayList();
        criteriaMap.put("adminOptionSystemId", new Integer(associatedQuestionBO
                .getAdminOptionId()));
        criteriaMap.put("adminOptionQuestionNumber", new Integer(
                associatedQuestionBO.getAssociatedQuestionId()));
        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(
                        com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO.class
                                .getName(), "isAdminOptionQuestionInUse",
                        criteriaMap);
        List searchResult = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        if (null != searchResult && searchResult.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * Method to locate admin option question
     * 
     * @param adminOptionLocateCriteria
     * @param user
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults locateAdminOption(
            AdminOptionLocateCriteria adminOptionLocateCriteria, User user)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        LocateResults locateResults = new LocateResults();

        String adminName = adminOptionLocateCriteria.getAdminNameCriteria();
        if (null != adminName) {
            adminName = "%" + adminName.toUpperCase() + "%";
        }else
        	adminName = "%";

        criteriaMap.put("adminName", adminName);
        criteriaMap.put("criteriaTerm", adminOptionLocateCriteria
                .getBenefitTermList());
        criteriaMap.put("criteriaQualifier", adminOptionLocateCriteria
                .getBenefitQualifierList());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO.class
                        .getName(), "getAdminOption", criteriaMap,
                adminOptionLocateCriteria.getMaximumResultSize());

        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);

        if (null != searchResults) {
            locateResults.setLocateResults(searchResults.getSearchResults());
            locateResults.setTotalResultsCount(searchResults
                    .getSearchResultCount());
        }
        return locateResults;
    }


    /**
     * Method to delete admin option question
     * 
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean deleteAdminOption(AdminOptionBO adminOptionBO, Audit audit)
            throws SevereException, AdapterException {
    	try{
    		AdapterUtil.performRemove(adminOptionBO, audit.getUser());
    	}catch (Exception e){        	
    		 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }


    /**
     * Method to copy associated questions of Admin Option to new Admin Option.
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean copyAssocaitedQuestions(AdminOptionBO srcBO,
            AdminOptionBO trgtBO, Audit audit) throws SevereException, AdapterException {

        AssociatedQuestionBO associatedQuestionBO = null;
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        HashMap criteriaMap = new HashMap();

        criteriaMap.put("adminOptionId", new Integer(srcBO.getAdminOptionId()));
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "copyAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");
	        SearchCriteria searchCriteria = AdapterUtil
	                .getAdapterSearchCriteria(
	                        com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO.class
	                                .getName(), "retrieveAdminOptionQuestions",
	                        criteriaMap);
	
	        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	
	        if (null != searchResults) {
	            List associatedQuestionsList = searchResults.getSearchResults();
	
	            if (null != associatedQuestionsList) {
	
	                
	                Iterator iterator = associatedQuestionsList.iterator();
	
	                while (iterator.hasNext()) {
	                    associatedQuestionBO = (AssociatedQuestionBO) iterator
	                            .next();
	
	                    associatedQuestionBO.setAdminOptionId(trgtBO
	                            .getAdminOptionId());
	                    associatedQuestionBO.setCreatedUser(audit.getUser());
	                    associatedQuestionBO.setCreatedTimestamp(audit.getTime());
	                    associatedQuestionBO.setLastUpdatedUser(audit.getUser());
	                    associatedQuestionBO.setLastUpdatedTimestamp(audit
	                            .getTime());
	
	                    AdapterUtil.performInsert(associatedQuestionBO, audit
	                            .getUser(),adapterServicesAccess);
	                }
	            }
	
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
        	AdapterUtil.logTheEndTransaction(transactionId , this , "copyAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");
        
    }catch (Exception e){ 
        AdapterUtil.abortTransaction(adapterServicesAccess);
    	AdapterUtil.logAbortTxn(transactionId , this , "copyAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");
    	throw new AdapterException("Unhandled exception is caught",e);
    }
        return true;
    }

    public void copyForCheckout(AdminOptionBO srcBO, AdminOptionBO trgtBO, Audit audit) throws SevereException {
    	HashMap map = new HashMap();
    	map.put("sourceAdminOptId", new Integer(srcBO.getAdminOptionId()));
		map.put("adminOptionId", new Integer(trgtBO.getAdminOptionId()));
		map.put("lastUpdatedUser", audit.getUser());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(AdminOptionBO.class.getName(),"copyForCheckout",map);
		AdapterUtil.performSearch(searchCriteria);
    }

    /**
     * Method to copy associated questions of Admin Option, for Check Out.
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean copyLatestAssocaitedQuestions(AdminOptionBO srcBO,
            AdminOptionBO trgtBO, Audit audit) throws SevereException, AdapterException {

        AssociatedQuestionBO associatedQuestionBO = null;
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        HashMap criteriaMap = new HashMap();

        criteriaMap.put("adminOptionId", new Integer(srcBO.getAdminOptionId()));
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "copyLatestAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");
         
	        SearchCriteria searchCriteria = AdapterUtil
	                .getAdapterSearchCriteria(
	                        com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO.class
	                                .getName(),
	                        "retrieveLatestAdminOptionQuestions", criteriaMap);
	
	        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	
	        if (null != searchResults) {
	            List associatedQuestionsList = searchResults.getSearchResults();
	
	            if (null != associatedQuestionsList) {
	
	                
	                Iterator iterator = associatedQuestionsList.iterator();
	                //modified on 7th Jan
	                int seqNumber = 1;
	                while (iterator.hasNext()) {
	                    associatedQuestionBO = (AssociatedQuestionBO) iterator
	                            .next();
	                    associatedQuestionBO.setSeqNumber(seqNumber);
	                    associatedQuestionBO.setAdminOptionId(trgtBO
	                            .getAdminOptionId());
	                    associatedQuestionBO.setCreatedUser(audit.getUser());
	                    associatedQuestionBO.setCreatedTimestamp(audit.getTime());
	                    associatedQuestionBO.setLastUpdatedUser(audit.getUser());
	                    associatedQuestionBO.setLastUpdatedTimestamp(audit
	                            .getTime());
	
	                    AdapterUtil.performInsert(associatedQuestionBO, audit
	                            .getUser(),adapterServicesAccess);
	                    seqNumber += 1;
	                    //ends
	                }
	            }
	
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
        	AdapterUtil.logTheEndTransaction(transactionId , this , "copyLatestAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");
        
        }catch (Exception e){ 
            AdapterUtil.abortTransaction(adapterServicesAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "copyLatestAssocaitedQuestions(AdminOptionBO srcBO,AdminOptionBO trgtBO, Audit audit)");

        	 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }

    /**
	 * Method to insert the RootQuestion
	 * 
	 * @param RootQuestion
	 *            RootQuestion object.
	 * @return boolean
	 * @throws AdapterException
	 */
	public boolean addRootQuestion(
			RootQuestionnaireBO rootQuestionnaireBO, AdminOptionBO adminOptionBO,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {
		Logger.logInfo("Entering the method to add Root Question");
		try {
			AdapterUtil.performInsert(rootQuestionnaireBO, adminOptionBO
					.getCreatedUser(), adapterServicesAccess);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
		Logger.logInfo("Returning the method from add Root Question");
		return true;
	}
	
	 /**
	 * Method to update the RootQuestion
	 * 
	 * @param RootQuestion
	 *            RootQuestion object.
	 * @return boolean
	 * @throws AdapterException
	 */
	public boolean updateRootQuestion(
			RootQuestionnaireBO rootQuestionnaireBO, AdminOptionBO adminOptionBO,
			AdapterServicesAccess adapterServicesAccess)
			throws AdapterException {
		Logger.logInfo("Entering the method to update Root Question");
		try {
			AdapterUtil.performUpdate(rootQuestionnaireBO, adminOptionBO
					.getLastUpdatedUser(), adapterServicesAccess);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
		Logger.logInfo("Returning the method from update Root Question");
		return true;
	}
    
    /**
     * This method executes the view all admin option search criteria and
     * retrieves the search results.
     * 
     * @param adminOptionLocateCriteria
     * @param user
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults viewAllAdminOption(
            String adminName)
            throws SevereException {
        SearchResults adminOptionSearchResults = null;
        HashMap criteriaMap = new HashMap();
        LocateResults locateResults = new LocateResults();

        criteriaMap.put(BusinessConstants.ADMIN_NAME, adminName);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO.class
                        .getName(), BusinessConstants.VIEW_ALL_ADMIN_OPTION, criteriaMap);

        adminOptionSearchResults = AdapterUtil.performSearch(searchCriteria);

        if (null != adminOptionSearchResults) {
            locateResults.setLocateResults(adminOptionSearchResults
                    .getSearchResults());
            locateResults.setTotalResultsCount(adminOptionSearchResults
                    .getSearchResultCount());
        }
        return locateResults;
    }


	/**Method to delete the associated questions of the questionnaire attached to an adminoption.
	 * @param associatedQuestionnaireBO
	 * @return LocateResults
	 * @throws AdapterException
	 */
	public LocateResults deleteAssociatedQuestionnaire(String questionnaieTildaString,int adminOptionId,AdapterServicesAccess adapterServicesAccess) throws AdapterException {
		 	SearchResults adminOptionSearchResults = null;
	        HashMap criteriaMap = new HashMap();
	        LocateResults locateResults = new LocateResults();
	        criteriaMap.put(BusinessConstants.ADMIN_OPTION_ID,new Integer(adminOptionId));
	        criteriaMap.put(BusinessConstants.QUESTIONNAIRE_TILDA_STRING,questionnaieTildaString);
	        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	        		com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO.class
	                        .getName(), BusinessConstants.DELETE_QUESTIONNAIRE, criteriaMap);

	        try {
	        	
				adminOptionSearchResults = AdapterUtil.performSearch(searchCriteria,adapterServicesAccess);		

		        if (null != adminOptionSearchResults) {
		            locateResults.setLocateResults(adminOptionSearchResults
		                    .getSearchResults());
		            locateResults.setTotalResultsCount(adminOptionSearchResults
		                    .getSearchResultCount());
		        }
	        } catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			}
	        return locateResults;
	}
	
	/**Method to update the domains of the child questions in the questionnaire when the parent question's domain is modified.
	 * @param associatedQuestionnaireBO
	 * @return LocateResults
	 * @throws AdapterException
	 */
	public LocateResults updateQuestionnaireDomain(int questionnaireId,String user,AdapterServicesAccess access) throws AdapterException {
		
		 	SearchResults adminOptionSearchResults = null;
	        HashMap criteriaMap = new HashMap();
	        LocateResults locateResults = new LocateResults();
	        criteriaMap.put(BusinessConstants.QUESTIONNAIRE_ID,new Integer(questionnaireId));
	        criteriaMap.put(BusinessConstants.USER_ID,user);
	        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	        		com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO.class
	                        .getName(), BusinessConstants.UPDATE_QUESTIONNAIRE_DOMAIN, criteriaMap);
	        try {
	        	
				adminOptionSearchResults = AdapterUtil.performSearch(searchCriteria,access);		

		        if (null != adminOptionSearchResults) {
		            locateResults.setLocateResults(adminOptionSearchResults
		                    .getSearchResults());
		            locateResults.setTotalResultsCount(adminOptionSearchResults
		                    .getSearchResultCount());
		        }
	        } catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			}
	        return locateResults;
	}
	
	/**
	 * Method to retrieve the details of the root questionnaire.
	 * @param questionnaireId
	 * @return
	 * @throws AdapterException
	 */
	public LocateResults locateRootQuestionnaireDetails(int questionnaireId) throws AdapterException {
		
		 	SearchResults rootQuestionnaireSearchResults = null;
	        HashMap criteriaMap = new HashMap();
	        LocateResults locateResults = new LocateResults();
	        criteriaMap.put(BusinessConstants.QUESTIONNAIRE_HIERARCHY_SYS_ID,new Integer(questionnaireId));
	        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
	        		com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO.class
	                        .getName(), BusinessConstants.LOCATE_ROOT_QUESTIONNAIRE_DETAILS, criteriaMap);
	        try {
	        	
	        	rootQuestionnaireSearchResults = AdapterUtil.performSearch(searchCriteria);		

		        if (null != rootQuestionnaireSearchResults) {
		            locateResults.setLocateResults(rootQuestionnaireSearchResults
		                    .getSearchResults());
		            locateResults.setTotalResultsCount(rootQuestionnaireSearchResults
		                    .getSearchResultCount());
		        }
	        } catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			}
	        return locateResults;
	}
	
	/**
	 * Method to retrieve the details of the root questionnaire.
	 * @param questionnaireId
	 * @return
	 * @throws AdapterException
	 */
	public LocateResults locateChildQuestionnaires(int questionnaireId, String action) throws AdapterException {
		
		 	SearchResults childQuestionnaireSearchResults = null;
	        HashMap criteriaMap = new HashMap();
	        LocateResults locateResults = new LocateResults();
	        SearchCriteria searchCriteria = null;
	        criteriaMap.put(BusinessConstants.PARENT_QUESTIONNAIRE_SYS_ID, new Integer(questionnaireId));
	        if(action.equals(BusinessConstants.ON_LOAD))
		        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        		com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO.class
		                        .getName(), BusinessConstants.LOCATE_CHILD_QUESTIONNAIRE, criteriaMap);
	        else if(action.equals(BusinessConstants.ON_CLOSE))
	        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        		com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO.class
		                        .getName(), BusinessConstants.LOCATE_INVALID_CHILD_QUESTIONNAIRES, criteriaMap);
	        try {
	        	
	        	childQuestionnaireSearchResults = AdapterUtil.performSearch(searchCriteria);		

		        if (null != childQuestionnaireSearchResults) {
		            locateResults.setLocateResults(childQuestionnaireSearchResults
		                    .getSearchResults());
		            locateResults.setTotalResultsCount(childQuestionnaireSearchResults
		                    .getSearchResultCount());
		        }
	        } catch (SevereException e) {
				throw new AdapterException(e.getMessage(),e);
			}
	        return locateResults;
	}
	
	/**
	 * Method to insert/update the child questionnaires.
	 * @param childQuestionnaireBO
	 * @param access
	 * @param user
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
    public boolean persistChildQuestionnaires(
            ChildQuestionnaireBO childQuestionnaireBO, AdapterServicesAccess access, 
			String user, boolean insertFlag) 
    		throws SevereException, AdapterException {
        try{
        	if(insertFlag)
        		AdapterUtil.performInsert(childQuestionnaireBO, user, access);
        	else
        		AdapterUtil.performUpdate(childQuestionnaireBO, user, access);
        }catch (Exception e){ 
        	 throw new AdapterException("Unhandled exception is caught",e);
        }
        return true;
    }
    
    public void updateSeqLvelQuestions(AssociatedQuestionnaireCpyBO bo,Audit audit)throws SevereException{
        AdapterUtil.performUpdate(bo,audit.getUser());
    }
	
	/*
	 * This method is for persist new questionnare 
	 * 
	 * @param ComponentsBenefitAdministrationBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 * This method add/update/remove both tiered and non-tiered questionnare
	 */
	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	public void saveQuestionnareForContract(EntityBenefitAdministrationPSBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		
			List questionnareList = administrationBO.getQuestionnareList();
			List tierList =administrationBO.getTierList();
			
			List questionnaireListToAdd         = administrationBO.getQuestionnaireListToAdd();
			List questionnaireListToUpdate      = administrationBO.getQuestionnaireListToUpdate();
			List questionnaireListToRemove      = administrationBO.getQuestionnaireListToRemove();

			List tierListToAdd                  = administrationBO.getTierQuestionnaireListToAdd();
			List tierListToUpdate               = administrationBO.getTierQuestionnaireListToUpdate();
			List tierListToRemove               = administrationBO.getTierQuestionnaireListToRemove();
			
			int benefitComponentId = administrationBO.getBenefitComponentid();
			
			for(int e=0;e<questionnaireListToAdd.size();e++){
				ContractQuesitionnaireBO contractQuesitionnaireBOToAdd = (ContractQuesitionnaireBO)questionnaireListToAdd.get(e);
				contractQuesitionnaireBOToAdd.setBenefitComponentId(benefitComponentId);
				contractQuesitionnaireBOToAdd.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
				contractQuesitionnaireBOToAdd.setDateSegmentId(administrationBO.getDateSegmentId());
				contractQuesitionnaireBOToAdd.setCreatedUser(audit.getUser());
				contractQuesitionnaireBOToAdd.setCreatedTimestamp(audit.getTime());
				contractQuesitionnaireBOToAdd.setLastUpdatedUser(audit.getUser());
				contractQuesitionnaireBOToAdd.setLastUpdatedTimestamp(audit.getTime());
				AdapterUtil.performInsert(contractQuesitionnaireBOToAdd, audit.getUser(),access);
			}
			
			for(int f=0;f<questionnaireListToUpdate.size();f++){
				ContractQuesitionnaireBO contractQuesitionnaireBOToUpdate = (ContractQuesitionnaireBO)questionnaireListToUpdate.get(f);
				contractQuesitionnaireBOToUpdate.setBenefitComponentId(benefitComponentId);
				contractQuesitionnaireBOToUpdate.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
				contractQuesitionnaireBOToUpdate.setDateSegmentId(administrationBO.getDateSegmentId());
				contractQuesitionnaireBOToUpdate.setCreatedUser(audit.getUser());
				contractQuesitionnaireBOToUpdate.setCreatedTimestamp(audit.getTime());
				contractQuesitionnaireBOToUpdate.setLastUpdatedUser(audit.getUser());
				contractQuesitionnaireBOToUpdate.setLastUpdatedTimestamp(audit.getTime());
				AdapterUtil.performUpdate(contractQuesitionnaireBOToUpdate, audit.getUser(),access);
			}
			
			for(int g=0;g<questionnaireListToRemove.size();g++){
				ContractQuesitionnaireBO contractQuesitionnaireBOToRemove = (ContractQuesitionnaireBO)questionnaireListToRemove.get(g);
				contractQuesitionnaireBOToRemove.setBenefitComponentId(benefitComponentId);
				contractQuesitionnaireBOToRemove.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
				contractQuesitionnaireBOToRemove.setDateSegmentId(administrationBO.getDateSegmentId());
				AdapterUtil.performRemove(contractQuesitionnaireBOToRemove, audit.getUser(),access);
			}
						
			
			if(null != tierListToAdd && tierListToAdd.size()>0){
				for(int j =0;j<tierListToAdd.size();j++){
					
							ContractTierQuesitionnaireBO contracttierQuestionareBOForSave = new ContractTierQuesitionnaireBO();
							ContractQuesitionnaireBO bo = (ContractQuesitionnaireBO)tierListToAdd.get(j);
							contracttierQuestionareBOForSave.setQuestionnaireId(bo.getQuestionnaireId());
							contracttierQuestionareBOForSave.setSelectedAnswerid(bo.getSelectedAnswerid());
							contracttierQuestionareBOForSave.setDateSegmentId(administrationBO.getDateSegmentId());
							contracttierQuestionareBOForSave.setBenefitComponentId(administrationBO.getBenefitComponentid());
							contracttierQuestionareBOForSave.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
							contracttierQuestionareBOForSave.setTierSysId(bo.getTierSysId());
							contracttierQuestionareBOForSave.setCreatedUser(audit.getUser());
							contracttierQuestionareBOForSave.setCreatedTimestamp(audit.getTime());
							contracttierQuestionareBOForSave.setLastUpdatedUser(audit.getUser());
							contracttierQuestionareBOForSave.setLastUpdatedTimestamp(audit.getTime());
							AdapterUtil.performInsert(contracttierQuestionareBOForSave, audit.getUser(),access);
						
				}			
			}
			
			if(null != tierListToUpdate && tierListToUpdate.size()>0){
				for(int m =0;m<tierListToUpdate.size();m++){
					
							ContractTierQuesitionnaireBO contracttierQuestionareBOForUpdate = new ContractTierQuesitionnaireBO();
							ContractQuesitionnaireBO bo = (ContractQuesitionnaireBO)tierListToUpdate.get(m);
							contracttierQuestionareBOForUpdate.setQuestionnaireId(bo.getQuestionnaireId());
							contracttierQuestionareBOForUpdate.setSelectedAnswerid(bo.getSelectedAnswerid());
							contracttierQuestionareBOForUpdate.setDateSegmentId(administrationBO.getDateSegmentId());
							contracttierQuestionareBOForUpdate.setBenefitComponentId(administrationBO.getBenefitComponentid());
							contracttierQuestionareBOForUpdate.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
							contracttierQuestionareBOForUpdate.setTierSysId(bo.getTierSysId());
							contracttierQuestionareBOForUpdate.setCreatedUser(audit.getUser());
							contracttierQuestionareBOForUpdate.setCreatedTimestamp(audit.getTime());
							contracttierQuestionareBOForUpdate.setLastUpdatedUser(audit.getUser());
							contracttierQuestionareBOForUpdate.setLastUpdatedTimestamp(audit.getTime());
							AdapterUtil.performUpdate(contracttierQuestionareBOForUpdate, audit.getUser(),access);
						}
							
			}
			
			if(null != tierListToRemove && tierListToRemove.size()>0){ // if tiered questions exist
				for(int q =0;q<tierListToRemove.size();q++){
					
							ContractTierQuesitionnaireBO contracttierQuestionareBOForRemove = new ContractTierQuesitionnaireBO();
							ContractQuesitionnaireBO bo = (ContractQuesitionnaireBO)tierListToRemove.get(q);
							contracttierQuestionareBOForRemove.setQuestionnaireId(bo.getQuestionnaireId());
							contracttierQuestionareBOForRemove.setSelectedAnswerid(bo.getSelectedAnswerid());
							contracttierQuestionareBOForRemove.setDateSegmentId(administrationBO.getDateSegmentId());
							contracttierQuestionareBOForRemove.setBenefitComponentId(administrationBO.getBenefitComponentid());
							contracttierQuestionareBOForRemove.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
							contracttierQuestionareBOForRemove.setTierSysId(bo.getTierSysId());
							contracttierQuestionareBOForRemove.setCreatedUser(audit.getUser());
							contracttierQuestionareBOForRemove.setCreatedTimestamp(audit.getTime());
							contracttierQuestionareBOForRemove.setLastUpdatedUser(audit.getUser());
							contracttierQuestionareBOForRemove.setLastUpdatedTimestamp(audit.getTime());
							AdapterUtil.performRemove(contracttierQuestionareBOForRemove, audit.getUser(),access);
						}
								
			}
						
	}
	
}