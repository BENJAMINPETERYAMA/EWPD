/*
 * AddQuestionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
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
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.SelectedQuestionListLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.Question;
import com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AddQuestionAdapterManager {

    private AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }
    
    public List getAssociatedQuestions(int benefitAdminOptionAssociationId) throws SevereException {
    	HashMap map = new HashMap();
    	map.put("adminOptionsSysId",new Integer(benefitAdminOptionAssociationId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(SelectedQuestionListBO.class.getName(),"getAssociatedQns",map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
    }

    /**
     * This method is to get the mandate list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(LocateCriteria locateCriteria)
            throws SevereException {
        // create the object for the locate results
        LocateResults locateResults = new LocateResults();
        // create a list
        List locateResultList = new ArrayList();
        SelectedQuestionListLocateCriteria selectedQuestionListLocateCriteria = (SelectedQuestionListLocateCriteria) locateCriteria;
        // create the reference of the SearchResults
        SearchResults searchResults = null;
        // create the object for the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        // set the required things in the search criteria
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO");
        searchCriteria
                .setMaxSearchResultSize(Integer.MAX_VALUE);
        
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("searchSelectedQuestionList");
        // create the referenceValueSet Hashmap
        HashMap refValSet = new HashMap();

        // get the adminOptions from the locateCriterial and set it in the hash
        // map
        refValSet.put("adminOptionsSysId", new Integer(
                selectedQuestionListLocateCriteria.getAdminOptionSysId()));
        // Enhancement
        refValSet.put("systemId", new Integer(
                selectedQuestionListLocateCriteria.getAdminOptionId()));
        // End Of Enhancement
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            // connect to the adapter
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(2);
            errorParams.add(searchCriteria.getBusinessObjectName());
            errorParams.add(searchCriteria.getSearchQueryName());
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
        // set the locateResultList to the locateResults
        locateResults.setLocateResults(searchResults.getSearchResults());
        // return locate results
        return locateResults;
    }


    /**
     * This method is to get the open question list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locateOpenQuestions(LocateCriteria locateCriteria)
            throws SevereException {
        return getQuestions(BusinessConstants.LOCATE_OPEN_QUESTIONS,
                locateCriteria);
    }


    /**
     * This method is to get the open question list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locateHiddenQuestions(LocateCriteria locateCriteria)
            throws SevereException {
        return getQuestions(BusinessConstants.LOCATE_HIDDEN_QUESTIONS,
                locateCriteria);
    }


    /**
     * This method is to save question
     * 
     * @param SelectedQuestionListBO
     * @return
     * @throws WPDException
     */
    public boolean persistQuestion(
            SelectedQuestionListBO selectedQuestionListBO, Audit audit,
            boolean flag,AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {

        List questionList = selectedQuestionListBO.getQuestionList();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        Iterator questionListIter = questionList.iterator();
        while (questionListIter.hasNext()) {

            SelectedQuestionListBO selectedQuestionListBOTemp = (SelectedQuestionListBO) questionListIter
                    .next();
            selectedQuestionListBO
                    .setAdminOptionsSysId(selectedQuestionListBOTemp
                            .getAdminOptionsSysId());
            selectedQuestionListBO.setQuestionNumber(selectedQuestionListBOTemp
                    .getQuestionNumber());
            selectedQuestionListBO.setQuestionDesc(selectedQuestionListBOTemp
                    .getQuestionDesc());
            selectedQuestionListBO.setReferenceId(selectedQuestionListBOTemp.getReferenceId());
            if (flag == true) {
                int sequenceNo = this.getNextSequenceForOpenQuestion(
                        selectedQuestionListBOTemp.getAdminOptionsSysId(),
                        selectedQuestionListBOTemp.getSystemId());
                selectedQuestionListBO.setSequenceNumber(sequenceNo);
            } else
                selectedQuestionListBO
                        .setSequenceNumber(selectedQuestionListBOTemp
                                .getSequenceNumber());
            selectedQuestionListBO.setAnswerId(selectedQuestionListBOTemp
                    .getAnswerId());
            if("notOpen".equals(selectedQuestionListBOTemp.getReferenceId())){

                refValSet.put("systemId", new Integer(
                        selectedQuestionListBOTemp.getSystemId()));
                refValSet.put("questionNo", new Integer(selectedQuestionListBO
                        .getQuestionNumber()));
                SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                        BusinessConstants.SELECTED_QUESTION_LIST_BO, "getReferenceId",
                        refValSet);
                searchResults = AdapterUtil.performSearch(searchCriteria);
                List searchResultList = searchResults.getSearchResults();
                Iterator searchResultListIter = searchResultList.iterator();
                while (searchResultListIter.hasNext()) {
                    SelectedQuestionListBO questionListBO = (SelectedQuestionListBO) searchResultListIter
                    		.next();
            
		            selectedQuestionListBO.setReferenceId(questionListBO
		                    .getReferenceId());
                }
                
            }else{
                selectedQuestionListBO.setReferenceId(selectedQuestionListBOTemp.getReferenceId());
            }
            selectedQuestionListBO.setIsOpen(selectedQuestionListBOTemp.getIsOpen());
            selectedQuestionListBO.setCreatedUser(audit.getUser());
            selectedQuestionListBO.setCreatedDate(audit.getTime());
            selectedQuestionListBO.setLastUpdatedUser(audit.getUser());
            selectedQuestionListBO.setLastChangedDate(audit.getTime());

            AdapterUtil
                    .performInsert(selectedQuestionListBO,
                            BusinessConstants.TESTUSER, adapterServicesAccess);
        }
        return true;

    }


    /**
     * This method is to get the question list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    private LocateResults getQuestions(String queryname,
            LocateCriteria locateCriteria) throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        QuestionLocateCriteria openQuestionLocateCriteria = (QuestionLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();

        //	    int sequenceNo =
        // this.getNextSequenceForOpenQuestion(openQuestionLocateCriteria.getAdminOptionsSysId(),openQuestionLocateCriteria.getSystemId());
        //	    int incrimenter=0;

        refValSet.put("adminOptionsSysId", new Integer(
                openQuestionLocateCriteria.getAdminOptionsSysId()));
        refValSet.put("systemId", new Integer(openQuestionLocateCriteria
                .getSystemId()));
        refValSet.put("standardBenefitKey", new Integer(openQuestionLocateCriteria
                .getStandardBenefitKey()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.SELECTED_QUESTION_LIST_BO, queryname,
                refValSet);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List searchResultList = searchResults.getSearchResults();
        Iterator searchResultListIter = searchResultList.iterator();
        while (searchResultListIter.hasNext()) {
            SelectedQuestionListBO selectedQuestionListBO = (SelectedQuestionListBO) searchResultListIter
                    .next();
            //if(selectedQuestionListBO.getSequenceNumber() == 0){
            // SequenceAdapterManager sequenceAdapterManager = new
            // SequenceAdapterManager();
            //int sequenceNo =
            // this.getNextSequenceForOpenQuestion(openQuestionLocateCriteria.getAdminOptionsSysId(),openQuestionLocateCriteria.getSystemId());
            //selectedQuestionListBO.setSequenceNumber(sequenceNo+incrimenter);
            //incrimenter++;
            //} else{
            selectedQuestionListBO.setSequenceNumber(selectedQuestionListBO
                    .getSequenceNumber());
            //}
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        return locateResults;
    }


    /**
     * This method is to update the seq no
     * 
     * @param subObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean updateSeqNo(SelectedQuestionListBO subObject, Audit audit,
            boolean insertFlag,AdapterServicesAccess adapterServicesAccess) throws SevereException,AdapterException {
        // get the BusinessTransactionContext object
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        // set the type as edit i.e update
        btc
                .setBusinessTransactionType(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_EDIT);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        // connect to the adapter       
        // get the Question list from subobject and get all the details of the
        // question
        // and set it in the selectedQuestionListBO
        List updatedSeqList = subObject.getQuestionList();
        if (null != updatedSeqList && !updatedSeqList.isEmpty()) {
            for (int i = 0; i < updatedSeqList.size(); i++) {
                // get the Question Bean from the list
                Question question = (Question) updatedSeqList.get(i);
                // create the SelectedQuesionListBO and set all the details to
                // that
                //modified for solving performance issue on 14th Dec 2007- checking if the modified flag is true
                if(question.isModified()){
                    SelectedQuestionListBO questionListBO = new SelectedQuestionListBO();
                    questionListBO.setAnswer(question.getAnswer());
                    questionListBO.setAnswerId(question.getAnswerId());
                    questionListBO.setLastChangedDate(audit.getTime());
                    questionListBO.setLastUpdatedUser(audit.getUser());
                    // set the answer id
                    //questionListBO.setAnswerId(111);
                    if (null != question.getQuestionNumber()
                            && !"".equals(question.getQuestionNumber())) {
                        questionListBO.setQuestionNumber(new Integer(question
                                .getQuestionNumber()).intValue());
                    }
                    questionListBO.setSequenceNumber(question.getSeqNumber());
                    questionListBO.setAdminOptionsSysId(question
                            .getAdminOptionSysId());
                    questionListBO.setReferenceId(question.getReferenceId());
                  //  questionListBO.setIsOpen(question.getIsOpen());
                    questionListBO.setCreatedDate(audit.getTime());
                    questionListBO.setCreatedUser(audit.getUser());
                    questionListBO.setLastChangedDate(audit.getTime());
                    questionListBO.setLastUpdatedUser(audit.getUser());
                    // update in the table
                  
                        adapterServicesAccess.persistObject(questionListBO,
                                subObject.getClass().getName(), btc);
                   
                }
                //modification ends
            }
        }
        // return true i.e updated
        return true;
    }


    /**
     * 
     * @param subObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean hideQuestion(SelectedQuestionListBO subObject, Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws WPDException,AdapterException {
        // get the BusinessTransactionContext object
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        // set the type as delete
        btc
                .setBusinessTransactionType(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_DELETE);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        // connect to the adapter
            // delete from the table
            adapterServicesAccess.removeObject(subObject, subObject.getClass()
                    .getName(), btc);

        
        // return true i.e deleted
        return true;
    }


    /**
     * This method is to get the possible answers list from the table
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    /*public LocateResults locatePossibleAnswers(LocateCriteria locateCriteria)
            throws SevereException {*/
    public LocateResults locatePossibleAnswers(int questionNumber)
    	throws SevereException {
        // create the object for the locate results
        LocateResults locateResults = new LocateResults();
        //PossibleAnswersLocateCriteria possibleAnswersLocateCriteria = (PossibleAnswersLocateCriteria) locateCriteria;
        // create a list
        List locateResultList = new ArrayList();
        // create the reference of the SearchResults
        SearchResults searchResults = null;
        // create the object for the search criteria
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        // set the required things in the search criteria
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AnswerBO");
        searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("searchPossibleAnswersList");
        // create the referenceValueSet Hashmap
        HashMap refValSet = new HashMap();

        // get the questionNumber from the locateCriteria and set it in the hash
        // map
        refValSet.put("QuestionNumber", new Integer(
                questionNumber));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            // connect to the adapter
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList(2);
            errorParams.add(searchCriteria.getBusinessObjectName());
            errorParams.add(searchCriteria.getSearchQueryName());
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
        // set the locateResultList to the locateResults
        locateResults.setLocateResults(searchResults.getSearchResults());
        // return locate results
        return locateResults;
    }


    /**
     * Update BNFT_ADMIN_OPT_QSTN_ASSN
     * 
     * @param selectedQuestionListBO
     * @throws WPDException
     */
    public boolean updateQuestion(
            SelectedQuestionListBO selectedQuestionListBO, Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException,AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc
                .setBusinessTransactionType(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_DELETE);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
     
        List questionList = selectedQuestionListBO.getQuestionList();
        Iterator questionListIter = questionList.iterator();
        //delete
     
            // delete from the table
            // while(questionListIter.hasNext()){
            //SelectedQuestionListBO selectedQuestionListBOTemp =
            // (SelectedQuestionListBO) questionListIter.next();
            List previousQuestionList = selectedQuestionListBO
                    .getPreviousQnNumber();
            Iterator prQuestionListIter = previousQuestionList.iterator();
            while (prQuestionListIter.hasNext()) {
                AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO) prQuestionListIter
                        .next();
                selectedQuestionListBO
                        .setAdminOptionsSysId(((SelectedQuestionListBO) questionList
                                .get(0)).getAdminLevelOptionSysAssnId());
                selectedQuestionListBO.setQuestionNumber(administrationOptionBO
                        .getAdminQuestionNumber());
                adapterServicesAccess.removeObject(selectedQuestionListBO,
                        selectedQuestionListBO.getClass().getName(), btc);
            }
            //}

       

        // Call insert
        while (questionListIter.hasNext()) {
            // Question question = (Question) questionListIter.next();
            SelectedQuestionListBO selectedQuestionListBOTemp = (SelectedQuestionListBO) questionListIter
                    .next();
            selectedQuestionListBO
                    .setAdminOptionsSysId(selectedQuestionListBOTemp
                            .getAdminOptionsSysId());
            selectedQuestionListBO.setQuestionNumber(selectedQuestionListBOTemp
                    .getQuestionNumber());
            selectedQuestionListBO.setQuestionDesc(selectedQuestionListBOTemp
                    .getQuestionDesc());
            selectedQuestionListBO.setSequenceNumber(selectedQuestionListBOTemp
                    .getSequenceNumber());
            selectedQuestionListBO.setAnswerId(selectedQuestionListBOTemp
                    .getAnswerId());
            selectedQuestionListBO.setReferenceId(selectedQuestionListBOTemp.getReferenceId());
            selectedQuestionListBO.setLastChangedDate(audit.getTime());
            selectedQuestionListBO.setLastUpdatedUser(audit.getUser());
            selectedQuestionListBO.setCreatedUser(audit.getUser());
            selectedQuestionListBO.setCreatedDate(audit.getTime());
            btc.setBusinessTransactionType("CREATE");
           
                adapterServicesAccess.persistObject(selectedQuestionListBO,
                        selectedQuestionListBOTemp.getClass().getName(), btc);

           
        }
        return true;
    }


    /**
     * 
     * @param productKey
     * @return
     * @throws ServiceException
     */
    public int getNextSequenceForOpenQuestion(int adminLevelOptionAssnId,
            int adminOptionId) throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put("adminOptionsSysId", new Integer(adminLevelOptionAssnId));
        hashMap.put("systemId", new Integer(adminOptionId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                SelectedQuestionListBO.class.getName(),
                "getNextSequenceForOpenQuestion", hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        SelectedQuestionListBO selectedQuestionListBO = new SelectedQuestionListBO();
        if (null != searchResults) {
            if (searchResults.getSearchResultCount() == 0)
                return 1;
            selectedQuestionListBO = (SelectedQuestionListBO) searchResults
                    .getSearchResults().get(0);
        }
        return selectedQuestionListBO.getSequenceNumber();
    }
}