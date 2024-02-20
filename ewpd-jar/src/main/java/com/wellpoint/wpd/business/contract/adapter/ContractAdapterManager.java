/*
 * ContractAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.DomainAdapterManager;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.DatafeedLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ExistingContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.framework.bo.manager.LockManager;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.search.service.SearchController;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO;
import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.contract.bo.AnswerOvrdInfoBO;
import com.wellpoint.wpd.common.contract.bo.BenefitCustomizationBO;
import com.wellpoint.wpd.common.contract.bo.BenefitLineOvrdValBO;
import com.wellpoint.wpd.common.contract.bo.Comment;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractAuditInfo;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitAffectedSPSBO;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitComponents;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractDFBenefit;
import com.wellpoint.wpd.common.contract.bo.ContractDFQuestionnaire;
import com.wellpoint.wpd.common.contract.bo.ContractHistory;
import com.wellpoint.wpd.common.contract.bo.ContractPricingInfo;
import com.wellpoint.wpd.common.contract.bo.ContractPricingInfoBO;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdminBO;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.contract.bo.ContractRuleBO;
import com.wellpoint.wpd.common.contract.bo.ContractSPSBO;
import com.wellpoint.wpd.common.contract.bo.ContractStatusBo;
import com.wellpoint.wpd.common.contract.bo.ContractTierInformationBO;
import com.wellpoint.wpd.common.contract.bo.CopyBenefitHeadingsBO;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.DateSegmentAssociationBO;
import com.wellpoint.wpd.common.contract.bo.DateSegmentUpdateAfterDeleteBO;
import com.wellpoint.wpd.common.contract.bo.DeletedDSInfoBO;
import com.wellpoint.wpd.common.contract.bo.ProviderSpecialityCodeBO;
import com.wellpoint.wpd.common.contract.bo.ReserveContractId;
import com.wellpoint.wpd.common.contract.bo.RuleIdBO;
import com.wellpoint.wpd.common.contract.bo.SystemContractId;
import com.wellpoint.wpd.common.contract.bo.TestData;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.security.domain.UserImpl;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainAssnBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainOvrdValBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.ContractTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.report.bo.ContractReportBean;
import com.wellpoint.wpd.common.report.bo.ContractReportCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicAttribute;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchObject;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord;
import com.wellpoint.wpd.web.contract.ContractTransferLogBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAdapterManager {
    /**
     * This method is to retreive transfer log.
     * 
     * @param contractTransferLogBO
     * @param user
     * @return searchResults
     * @throws SevereException
     */
    public List locateTransferLog(ContractTransferLogBO contractTransferLogBO,
            User user) throws SevereException {
        HashMap hashMap = new HashMap();
        SearchResults searchResults = null;
        hashMap.put(WebConstants.CONTRACT_SYS, new Integer(
                contractTransferLogBO.getContractSysId()));
        hashMap.put(WebConstants.DATESEGMENT, new Integer(contractTransferLogBO
                .getDateSegmentId()));
        hashMap.put(WebConstants.VERSION_ID, new Integer(contractTransferLogBO
                .getVersion()));
        hashMap.put(WebConstants.EFFECTIVEDATE,contractTransferLogBO
                .getEffectiveDate());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                contractTransferLogBO.getClass().getName(),
                WebConstants.LOCATE_NAME, hashMap);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    public ContractBenefitComponents retrieveBenefitComponentFlags(
            ContractBenefitComponents benefitComponents) throws SevereException {
        return (ContractBenefitComponents) AdapterUtil
                .performRetrieve(benefitComponents);
    }
    
    
    public List getFirstdateSegment(int dateSegmentId, int contractSysId)throws SevereException{
    	HashMap hashMap = new HashMap();
        SearchResults searchResults = null;
        hashMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
        		dateSegmentId));
        hashMap.put(WebConstants.CONTRACT_SYS_ID, new Integer(
        		contractSysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.LOCATE_FIRST_DATESEGMENT, hashMap);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    public boolean updateContractBenefitComponentsFlag(
            ContractBenefitComponents details, String userId,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(details, userId, serviceAccess);
        return true;
    }


    public ContractBenefitComponents getBenefitComponentStatus(
            int benefitComponentId, int dateSegmentId) throws SevereException {
        ContractBenefitComponents details = new ContractBenefitComponents();
        details.setBenefitComponentId(benefitComponentId);
        details.setDateSegmentId(dateSegmentId);
        details = (ContractBenefitComponents) AdapterUtil
                .performRetrieve(details);
        return details;
    }


    public List retrieveValidStatusDatesegments(int contractSysId,
            String testIndicator) throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("contractSysId", new Integer(contractSysId));
        refValSet.put("contractModificationInd", testIndicator);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                BusinessConstants.GET_VALID_STATUS_DTSG, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }

    public List getAccumQuestions(DateSegment dateSegment,AccumulatorValidationBO bnftDetailsBasedDSBo) throws SevereException{
    	SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment.getDateSegmentSysId()));
        refValSet.put("benefitComponentSysId", new Integer(bnftDetailsBasedDSBo.getBenefitComponentSysId()));
        refValSet.put("benefitSysId", new Integer(bnftDetailsBasedDSBo.getBenefitSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "accumQuestionSearch", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getCodedAccumQuestions(DateSegment dateSegment) throws SevereException{
    	SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment.getDateSegmentSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "codedAccumQuestionSearch", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
  //sscr 157571
    public List checkcarvedoutQuestionInfo(int dateSegment,String carvedoutQuestdesc,String benftName) throws SevereException{
    	SearchResults searchResults = null;
        
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment));
        refValSet.put("questionDesc", carvedoutQuestdesc);
        refValSet.put("benfitName", benftName); 
        //System.out.println("check Benefite in checkcarved"+benftName);
        Logger.logInfo("check Benefite in checkcarved"+benftName);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "CARVEDOUT_QUESTIONS", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }   
    
    public List findcarvedBenefits(int ctlgbenefitId,String vendorRef) throws SevereException{
    	SearchResults searchResults = null;
        
        HashMap refValSet = new HashMap();
        refValSet.put("catalogBenefitId", new Integer(ctlgbenefitId));
        refValSet.put("vendorDesc", vendorRef);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "CARVEDOUT_BENEFITS", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }   
    public List checkVendorQuestionInfo(int dateSegment,String carvedoutQuestdesc,String benefitCompName,String benfitName) throws SevereException{
    	SearchResults searchResults = null;
        
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment));
        refValSet.put("questionDesc", carvedoutQuestdesc);
        refValSet.put("benefitCompName", benefitCompName);
        refValSet.put("benfitName", benfitName);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "CARVEDOUT_VENDOR_QUESTIONS", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }
    //sscr 17571
    
    public List checkVendorQuestionTierInfo(int dateSegment,String carvedoutQuestdesc,String benefitCompName,String benfitName) throws SevereException{
    	SearchResults searchResults = null;
        
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment));
        refValSet.put("questionDesc", carvedoutQuestdesc);
        refValSet.put("benefitCompName", benefitCompName);
        refValSet.put("benfitName", benfitName);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "CARVEDOUT_VENDOR_TIER_QUESTIONS", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }
    
    public List checkQuestionExists(String vendorRef) throws SevereException{
    	SearchResults searchResults = null;
        
        HashMap refValSet = new HashMap();
        refValSet.put("questionDesc", vendorRef);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "CHECK_VENDOR_QUESTION", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResults = searchResults.getSearchResults();
       
        return locateResults;
    }
    
    public List getCodedTieredAccumQuestions(DateSegment dateSegment) throws SevereException{
    	SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegment.getDateSegmentSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "codedTieredAccumSearch", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getCodedLines(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("questionNumber", new Integer(accumValidationBO.getQuestionNumber()));
        refValSet.put("dateSegmentSysId", new Integer(accumValidationBO.getDateSegmentSysId()));
        refValSet.put("benefitComponentSysId", new Integer(accumValidationBO.getBenefitComponentSysId()));
        refValSet.put("benefitSysId", new Integer(accumValidationBO.getBenefitSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "codedLinesSearch", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List isAdjudAccumQuestion(int questionNo) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("questionNumber", new Integer(questionNo));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "isAdjudAccumQuestion", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getImageRWDAFlagLst(String dateSegSysId) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegSysId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.DateSegment",
                "getImageRWDAFlag", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    
    public List getAccumBenefitAssociation(String questionNo, String benefitSysId) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
       /* refValSet.put("questionNumber", new Integer(questionNo));
        refValSet.put("benefitSysId", new Integer(benefitSysId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "getAccumBenefitAssociation", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();*/
        refValSet.put("benefitSysId", new Integer(benefitSysId));
        refValSet.put("questionNumber", new Integer(questionNo));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "getMappedBnftAccumAssnLst", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getAccumBenefitAssociationForAdminOption(String adminOptionSysId, String benefitSysId) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("adminOptionSysId", new Integer(adminOptionSysId));
        refValSet.put("benefitSysId", new Integer(benefitSysId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "getBnftAccumAssnForAdminOption", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
        
    public List getMappedBnftAccumAssnLst(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("benefitSysId", new Integer(accumValidationBO.getBenefitSysId()));
        refValSet.put("questionNumber", new Integer(accumValidationBO.getQuestionNumber()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "getMappedBnftAccumAssnLst", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    
    public List getAccumulatorSet(String possibleAnswer) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("svcCde", possibleAnswer);
                int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.accumulator.bo.AccumulatorImpl",
                "accumulatorSearchForValidation", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getStandardAccumulatorSet(AccumulatorValidationBO accumValidationBO,Domain domain, int questionNo,String BYCYAnswer)throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("question", new Integer(questionNo));
        refValSet.put("be", domain.getBusinessEntity());
        refValSet.put("group", domain.getBusinessGroup());
        refValSet.put("lineOfBusiness", domain.getLineOfBusiness());
        refValSet.put("mbu", domain.getMarketBusinessUnit());
        refValSet.put("byOrCy", BYCYAnswer);
        refValSet.put("benefit", new Integer(accumValidationBO.getBenefitSysId()));
                int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator",
                "getStandardAccumMappedForaQuestion", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    
    public List getStdAccumulatorAdminOption(int adminOptSysId,int benefitSysId,String BYCYAnswer,Domain domain)throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("adminOptionSysId", new Integer(adminOptSysId));
        refValSet.put("businessdomain", new Integer(domain.getDomainSysId()));
        refValSet.put("byOrCy", BYCYAnswer);
        refValSet.put("benefit", new Integer(benefitSysId));
                int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator",
                "standardAccumMappedForAdminOption", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List checkAdminOptionAnswered(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(accumValidationBO.getDateSegmentSysId()));
        refValSet.put("benefitComponentSysId", new Integer(accumValidationBO.getBenefitComponentSysId()));
        refValSet.put("adminOptionSysId", new Integer(accumValidationBO.getAdminOptionSysId()));
        refValSet.put("benefitSysId", new Integer(accumValidationBO.getBenefitSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "isAdminOptionAnswered", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List checkTierQstnAnswered(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(accumValidationBO.getDateSegmentSysId()));
        refValSet.put("benefitComponentSysId", new Integer(accumValidationBO.getBenefitComponentSysId()));
        refValSet.put("adminOptionSysId", new Integer(accumValidationBO.getAdminOptionSysId()));
        refValSet.put("benefitSysId", new Integer(accumValidationBO.getBenefitSysId()));
        refValSet.put("contractTierSysId", new Integer(accumValidationBO.getContractTierSysId()));
        refValSet.put("questionNumber", new Integer(accumValidationBO.getQuestionNumber()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "isTieredAccumAnswered", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    public List getAnsweredQuestionList(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(accumValidationBO.getDateSegmentSysId()));
        refValSet.put("benefitComponentSysId", new Integer(accumValidationBO.getBenefitComponentSysId()));
        refValSet.put("adminOptionSysId", new Integer(accumValidationBO.getAdminOptionSysId()));
        refValSet.put("benefitSysId", new Integer(accumValidationBO.getBenefitSysId()));
        refValSet.put("questionNumber", new Integer(accumValidationBO.getQuestionNumber()));
        
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "isQuestionAnswered", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    
    public List checkAnsweredInGenBenefits(AccumulatorValidationBO accumValidationBO) throws SevereException{
    	SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(accumValidationBO.getDateSegmentSysId()));
        refValSet.put("adminOptionSysId", new Integer(accumValidationBO.getAdminOptionSysId()));
        refValSet.put("questionNumber", new Integer(accumValidationBO.getQuestionNumber()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                "com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO",
                "isAnsweredInGenBnfts", refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    
    public List retrieveCheckInDateSegments(int contractSysId)
            throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("contractSysId", new Integer(contractSysId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                BusinessConstants.GET_DTSG_CHECKIN, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
//sscr 17571
    
    
    public List retrieveDate(int dateSegmentSysId)
            throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentSysId", new Integer(dateSegmentSysId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                BusinessConstants.GET_DT_RANGE, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }


    public List getContractRulesForValidation(int dateSegmentId)
            throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dsSysId", new Integer(dateSegmentId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_LOCATE_RESULT,
                BusinessConstants.CONTRACT_RULE_VALIDATION, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }
    
    /**
     * This mehod to get the invalid rules attached to the Contract
     * 
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public List getContractInvalidRules(int dateSegmentId)
            throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dsSysId", new Integer(dateSegmentId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_LOCATE_RESULT,
                BusinessConstants.CONTRACT_INVALID_RULES, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }


    public void updateBenefits(BenefitCustomizationBO bo,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(bo, bo.getLastUpdatedUser(), serviceAccess);
    }
    
    public void deleteBnftTierDetails(BenefitCustomizationBO bo, AdapterServicesAccess serviceAccess)throws SevereException{
    	HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		SearchResults searchResults = null;
		referenceValueSet.put("productKey", new Integer(bo.getDateSegmentId()));
		referenceValueSet.put("componentKey", new Integer(bo.getBenefitComponentId()));
		referenceValueSet.put("benefitKey", new Integer(bo.getBenefitId()));
		referenceValueSet.put("entityType", "CONTRACT");
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductComponentBO.class.getName(), "deleteTierDetails",
				referenceValueSet);
		 searchResults = AdapterUtil.performSearch(adapterSearchCriteria,serviceAccess);
	 	 Logger.logInfo("Returning the procedure for removing the tier details of the benefit");
    	
    }
    
    
    public void deleteBnftCompTierDetails(ContractBenefitComponents details, AdapterServicesAccess serviceAccess)throws SevereException{
    	HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		SearchResults searchResults = null;
		referenceValueSet.put("productKey", new Integer(details.getDateSegmentId()));
		referenceValueSet.put("componentKey", new Integer(details.getBenefitComponentId()));
		referenceValueSet.put("entityType", "CONTRACT");
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				 BusinessConstants.PRODUCT_BEN_COMP_ASSC_BO, "deleteComponentTierInfo",
				referenceValueSet);
		searchResults = AdapterUtil.performSearch(adapterSearchCriteria,serviceAccess);
	 	Logger.logInfo("Returning the procedure for removing the tier details of the Benefit Component");
    }


    public List getBenefits(int dateSegmentId, int benefitCompId,
            boolean showHidden, User user) throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        ProductTreeStandardBenefits bo = new ProductTreeStandardBenefits();
        bo.setBenefitComponentId(benefitCompId);
        bo.setDateSegmentId(dateSegmentId);
        //	     List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentId", new Integer(dateSegmentId));
        refValSet.put("benefitComponentId", new Integer(benefitCompId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = null;
        if (showHidden) {
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(bo.getClass()
                    .getName(), BusinessConstants.GET_BENEFITS_ALL, refValSet,
                    maxResultSize);
        } else {
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(bo.getClass()
                    .getName(), BusinessConstants.GET_BENEFITS_VISIBLE,
                    refValSet, maxResultSize);
        }
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }


    /**
     * 
     * @param contractRuleBONew
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteRules(ContractRuleBO contractRuleBONew, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(contractRuleBONew, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param contractRuleBO
     * @return
     * @throws SevereException
     */
    public void updateRuleContractAttachment(ContractRuleBO contractRuleBO,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(contractRuleBO, contractRuleBO
                .getLastUpdatedUser(), serviceAccess);
    }


    /**
     * 
     * @param contractRuleBO
     * @return
     * @throws SevereException
     */
    public void createRuleContractAttachment(ContractRuleBO contractRuleBO,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performInsert(contractRuleBO, contractRuleBO
                .getLastUpdatedUser(), serviceAccess);
    }


    /**
     * 
     * @param locateCriteria
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults locateRuleOverValue(LocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.PRODUCT_RULE_SYSID, new Integer(
                ruleLocateCriteria.getProductRuleSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_LOCATE_RESULT,
                BusinessConstants.LOCATE_RULE_VALUE, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param locateCriteria
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults getMembershipData(Contract contract)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, contract.getContractId());
        refValSet.put(WebConstants.DATESEGMENT_ID, new Integer(contract
                .getBaseDateSegmentSysId()));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.MEMBERSHIP_INFO,
                BusinessConstants.LOCATE_MEMBERSHIP_VALUE, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * Function to perform the search for check in validations:rule
     * 
     * @param dateSegmentId
     * @return locateResultsList
     * @throws SevereException
     * @throws WPDException
     */
    public List validateBenefitRule(int dateSegmentId) throws SevereException {
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        //        List locateResultsList = new ArrayList();
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentId", new Integer(dateSegmentId));
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.CONTRACT_RULE_BO,
                BusinessConstants.LOCATE_RULE_CHECK, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        List locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }


    /**
     * 
     * @param locateCriteria
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults locateRules(LocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        setValuesToMapForRules(refValSet, ruleLocateCriteria);
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_LOCATE_RESULT,
                BusinessConstants.LOCATE_RULE, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param locateCriteria
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults locateRulesAssociated(LocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();

        if (0 != ruleLocateCriteria.getDateSegmentId()) {
            refValSet.put(WebConstants.DS_SYS_ID, new Integer(
                    ruleLocateCriteria.getDateSegmentId()));
        }
        refValSet.put(WebConstants.RULE_GEN_ID, ruleLocateCriteria
                .getRuleType());
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_LOCATE_RESULT,
                BusinessConstants.LOCATE_RULE_ASSOCIATED, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }
    
    /**
	 * To get the list of tiered questions  from the db for
	 * Contract
	 * 
	 * @param adminlevelassociationsysid
	 * @param benefitComponentId
	 * @param productSysId
	 * @param List of TiersysIds
	 * @return List
	 * @throws SevereException
	 */
	public List getTieredQuestionnaireValuesForContract(int adminOptionSysId, int benefitComponentId,int benefitAdminSysId, int entitySysId,List tierSysIdList
			,int adminLevelOptionSysId,int cntrctParentSysId)
			throws SevereException{
		
		
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("dateSegmentId", new Integer(entitySysId));
		map.put("adminOptSysId", new Integer(adminOptionSysId));
		map.put("benftAdminSysId", new Integer(benefitAdminSysId));
		map.put("beneftComponentId", new Integer(benefitComponentId));
		map.put("tierList",tierSysIdList );
//		  Code change by minu : 5-1-2011 : eWPD System Stabilization
		map.put("adminLvlOptSystemId",new Integer(adminLevelOptionSysId) );
		map.put("cntrctParntSysId",new Integer(cntrctParentSysId) );
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractQuesitionnaireBO.class.getName(),
				"getTierQuestionnaireContract", map); 
		
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		ArrayList searchResultsList = (ArrayList)searchResults.getSearchResults();
		for(int i=0;i<searchResultsList.size();i++){
			ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO)searchResultsList.get(i);
			contractQuesitionnaireBO.setAdminLevelOptionSysId(adminLevelOptionSysId);
			contractQuesitionnaireBO.setDateSegmentId(entitySysId);
}
		// Return the resulting list to the builder
		return searchResultsList;
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
	public List getChildTierQuestionnaireValuesForContract(int selectedAnswerid,
			int questionnaireId, int contractSysId, int dateSegmntId,
			int adminLvlOptAssSysId,int bcSysId,int benftAdminSysId, int tierSysId) throws SevereException {

		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("contractSysId", new Integer(contractSysId));
		map.put("dateSegmentId", new Integer(dateSegmntId));
		map.put("adminLvlOptSystemId", new Integer(adminLvlOptAssSysId));
		map.put("beneftComponentId", new Integer(bcSysId));
		map.put("benftAdminSysId", new Integer(benftAdminSysId));
		map.put("tierSysID", new Integer(tierSysId));

		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractQuesitionnaireBO.class.getName(),
				"getChildTierQuestionnaireContract", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		
	}

    /**
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public SearchResults locateRulesSequences(LocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();

        if (null != ruleLocateCriteria.getRuleId()) {
            refValSet.put(WebConstants.RULE_ID, ruleLocateCriteria.getRuleId());
        }
        int maxResultSize = 999999;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RULE_SEQUENCE_RESULT,
                BusinessConstants.LOCATE_RULE_SEQUENCES, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }

    	
    
    /**
     * retrieves the Tier definitions and criterias corresponding to the benefit
     * 
     * @param productId
     * @param benefitComponentId
     * @param benefitId
     * @throws SevereException
     */
	public List getTierCriteriaForContract(int productId, int benefitComponentId, int benefitId) throws SevereException{
		
		HashMap map = new HashMap();
		map.put("entitySysId", new Integer(productId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
				"getTierCriteriaForContract", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	
	/**
	 * retrieves the information whether the adminoption
	 * is excluded from tiering.
	 * @param adminOptSysId
	 * 
	 * @throws SevereException
	 */
	public boolean isAdminOptionExcluded(int adminOptSysId) throws SevereException{
		
		HashMap map = new HashMap();
		map.put("adminOptSysId", new Integer(adminOptSysId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(ContractQuesitionnaireBO.class.getName(),
				"isAdminOptionExcluded", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		List list = searchResults.getSearchResults();
		if (null != list && !list.isEmpty()) {
			if("Y".equalsIgnoreCase(  ((ContractQuesitionnaireBO)list.get(0)).getIsExcluded()  ) ){
				return true;
			}
		}
		return false;
	}

	/**
     * Retrieves the Tier definitions and criterias corresponding to the benefit in a Contract
     * 
     * @param productId
     * @param benefitComponentId
     * @param benefitId
     * @throws SevereException
     */
	public List getTierCriteriaForBenefitInContract(int productId, int benefitComponentId, int benefitId) throws SevereException{
		HashMap map = new HashMap();
		map.put(BusinessConstants.ENTITYSYSID, new Integer(productId));
		map.put(BusinessConstants.BEN_SYS_ID, new Integer(benefitId));
		map.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
				BusinessConstants.GETTIERCRITERIA_FOR_BENEFITIN_CONTRACT, map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	
	/**
     * Retrieves the Tier definitions and criterias corresponding to the benefit
     * 
     * @param productId
     * @param benefitComponentId
     * @param benefitId
     * @throws SevereException
     */
	public List getTierCriteriaForGeneralBenefitsInContract(int productId) throws SevereException{
		HashMap map = new HashMap();
		map.put(BusinessConstants.ENTITYSYSID, new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
				BusinessConstants.GETTIERCRITERIA_FOR_GENERALBENEFITSIN_CONTRACT, map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
    /**
     * 
     * @param map
     * @param ruleLocateCriteria
     * @return
     * @throws
     */
    private void setValuesToMapForRules(HashMap map,
            RuleLocateCriteria ruleLocateCriteria) {
        int productSysId = ruleLocateCriteria.getProductSysId();
        int dsId = ruleLocateCriteria.getDateSegmentId();
        if (0 != ruleLocateCriteria.getProductSysId()) {
            map.put(WebConstants.PROD_SYS_ID, new Integer(productSysId));
        }
        if (0 != dsId) {
            map.put(WebConstants.DS_SYS_ID, new Integer(dsId));
        }
        if (null != ruleLocateCriteria.getRuleType()) {
            String genRuleId = null;
            if ("Exclusion Rule".equals(ruleLocateCriteria.getRuleType()))
                genRuleId = "&%";
            if ("UM Rule".equals(ruleLocateCriteria.getRuleType()))
                genRuleId = "#%";
            if ("Denial Rule".equals(ruleLocateCriteria.getRuleType()))
                genRuleId = "*%";
            if ("PNR Rule".equals(ruleLocateCriteria.getRuleType()))
                genRuleId = "$%";
            map.put(WebConstants.RULE_GEN_ID, genRuleId);
        }

    }


    /**
     * 
     * @param reserveContractId
     * @return reserveContractId
     * @throws SevereException
     */
    public ReserveContractId retrieveReservedContract(
            ReserveContractId reserveContractId) throws SevereException {
        return (ReserveContractId) AdapterUtil
                .performRetrieve(reserveContractId);
    }


    /**
     * 
     * @param sysIdToDelet
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteCreatedReservedContracts(
            SystemContractId sysIdToDelet, User user,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(sysIdToDelet, user.getUserId(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param reserveContractId
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean deleteReserveContract(ReserveContractId reserveContractId,
            String user) throws SevereException {
        AdapterUtil.performRemove(reserveContractId, user);
        return true;
    }


    /**
     * 
     * @param
     * @return searchResult
     * @throws SevereException
     */
    public List getInitialContracts() throws SevereException {
        HashMap map = new HashMap();
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(SystemContractId.class
                        .getName(), WebConstants.GET_INITIAL_CONTRACT, map))
                .getSearchResults();

    }


    /**
     * 
     * @param
     * @return searchResult
     * @throws SevereException
     */
    public List retrieveMaxSysId() throws SevereException {
        HashMap map = new HashMap();
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(SystemContractId.class
                        .getName(), WebConstants.GET_MAX_SYS_ID, map))
                .getSearchResults();
    }


    /**
     * 
     * @param reservedContract
     * @param serviceAccess
     * @return
     * @throws SevereException
     */

    public void updateReservedContract(ReserveContractId reservedContract,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(reservedContract, reservedContract
                .getLastUpdatedUser(), serviceAccess);

    }


    /**
     * 
     * @param reservedContract
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public void createReservedContract(ReserveContractId reservedContract,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performInsert(reservedContract, reservedContract
                .getLastUpdatedUser(), adapterServicesAccess);

    }


    /**
     * 
     * @param contract
     * @return
     * @throws SevereException
     */
    public void createContract(Contract contract,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performInsert(contract, contract.getLastUpdatedUser(),
                serviceAccess);

    }


    /**
     * 
     * @param systemContractId
     * @return
     * @throws SevereException
     */
    public void createSysPoolEntry(SystemContractId systemContractId)
            throws SevereException {
        AdapterUtil.performInsert(systemContractId, systemContractId
                .getLastUpdatedUser());

    }


    /**
     * 
     * @param testData
     * @return
     * @throws SevereException
     */
    public void createTestData(TestData testData,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performInsert(testData, testData.getLastUpdatedUser(),
                serviceAccess);
    }


    /**
     * 
     * @param testData
     * @return
     * @throws SevereException
     */
    public void updateTestData(TestData testData,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(testData, testData.getLastUpdatedUser(),
                serviceAccess);
    }


    /**
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public void updateDateSegment(DateSegment dateSegment,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(dateSegment,
                dateSegment.getLastUpdatedUser(), serviceAccess);
    }


    /**
     * 
     * @param contract
     * @param serviceAccess
     * @return
     * @throws SevereException
     */
    public void updateContract(Contract contract,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(contract, contract.getLastUpdatedUser(),
                serviceAccess);
    }


    /**
     * 
     * @param contract
     * 
     * @return
     * @throws SevereException
     */
    public void updateContract(Contract contract) throws SevereException,
            AdapterException {
        AdapterUtil.performUpdate(contract, contract.getLastUpdatedUser());
    }


    /**
     * 
     * @param contract
     * @return contract
     * @throws SevereException
     */
    public Contract retrieveContract(Contract contract) throws SevereException {
        return (Contract) AdapterUtil.performRetrieve(contract);
    }


    /**
     * 
     * @param dateSegment
     * @return dateSegment
     * @throws SevereException
     */
    public DateSegment retrieveDateSegment(DateSegment dateSegment)
            throws SevereException {
    	
        DateSegment retrievedDateSegment  = (DateSegment) AdapterUtil.performRetrieve(dateSegment);
        if(null != retrievedDateSegment){
        	retrievedDateSegment.setContractStatusBo(retrieveContractStatus(retrievedDateSegment.getContractId()));
        }
        return retrievedDateSegment;

    }


    /**
     * The adapter Manager method to retrieve the provider speciality codes for
     * a contract Datesegment
     * 
     * @param providerSpecialityBO
     * @return
     * @throws SevereException
     */
    public List getProviderSpecialityCodes(
            ProviderSpecialityCodeBO providerSpecialityCodeBO)
            throws SevereException {
        SearchResults searchResults = null;
        List locateResultsList = null;
        HashMap refValSet = new HashMap();
        refValSet.put("dateSegmentId", new Integer(providerSpecialityCodeBO
                .getDateSegmentSysId()));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProviderSpecialityCodeBO.class.getName(),
                BusinessConstants.RETRIEVE_PROVIDER_SPECIALITY_CODES,
                refValSet, BusinessConstants.RESULT_SET_SIZE);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        locateResultsList = searchResults.getSearchResults();

        return locateResultsList;
    }


    /**
     * 
     * @param adaptedInfoBO
     * @return adaptedInfoBO
     * @throws SevereException
     */
    public AdaptedInfoBO retrieveAdaptedInfo(AdaptedInfoBO adaptedInfoBO)
            throws SevereException {
        return (AdaptedInfoBO) AdapterUtil.performRetrieve(adaptedInfoBO);

    }


    /**
     * 
     * @param dataFeedStatus
     * @return dataFeedStatus
     * @throws SevereException
     */
    public DataFeedStatus getDataFeed(DataFeedStatus dataFeedStatus)
            throws SevereException {
        return (DataFeedStatus) AdapterUtil.performRetrieve(dataFeedStatus);
    }


    /**
     * 
     * @param testData
     * @return testData
     * @throws SevereException
     */
    public TestData retreiveTestData(TestData testData) throws SevereException {
        return (TestData) AdapterUtil.performRetrieve(testData);
    }


    /**
     * 
     * @param lob
     * @param be
     * @param bg
     * @return SearchResults
     * @throws SevereException
     */
    public List getBaseContracts(List lob, List be, List bg,List mbu)
            throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.LOB_URL_PARM_NAME, lob);
        map.put(WebConstants.BE_REQ_PARAM_NAME, be);
        map.put(WebConstants.BG_REQ_PARAM_NAME, bg);
		/*START CARS*/
        map.put(WebConstants.MBU_REQ_PARAM, mbu);
		/*END CARS*/
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(Contract.class.getName(),
                        WebConstants.GET_BASE_CONTRACT, map))
                .getSearchResults();
    }


    /**
     * This Method for getting Base contracts for Migration.
     * 
     * @param lob
     * @param be
     * @param bg
     * @param productParentSysId
     * @param query
     * @return
     * @throws SevereException
     */
    public List getBaseContracts(List lob, List be, List bg,
            int productParentSysId, Date effDate, Date expDate,List mbu)
            throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.LOB_URL_PARM_NAME, lob);
        map.put(WebConstants.BE_REQ_PARAM_NAME, be);
        map.put(WebConstants.BG_REQ_PARAM_NAME, bg);
		/*START CARS*/
        map.put(WebConstants.MBU_REQ_PARAM, mbu);
		/*END CARS*/
        if (0 != productParentSysId) {
            map.put(WebConstants.PROD_PAR_SYS_ID, new Integer(
                    productParentSysId));
        }

        if (effDate == null)
            map.put(WebConstants.EFF_DATE_REQ_PARAM_NAME,
                    WebConstants.DEFAULT_EFF_DATE);
        else
            map.put(WebConstants.EFF_DATE_REQ_PARAM_NAME, WPDStringUtil
                    .convertDateToString(effDate));

        map.put(WebConstants.EXP_DATE_REQ_PARAM_NAME, WPDStringUtil
                .convertDateToString(expDate));

        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(Contract.class.getName(),
                        WebConstants.GET_BASE_CONTRACT_FOR_MIGRATION, map))
                .getSearchResults();
    }


    public List getBaseContracts(List lob, List be, List bg, Date effDate,
            Date expDate,List mbu) throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.LOB_URL_PARM_NAME, lob);
        map.put(WebConstants.BE_REQ_PARAM_NAME, be);
        map.put(WebConstants.BG_REQ_PARAM_NAME, bg);
		/*START CARS*/
        map.put(WebConstants.MBU_REQ_PARAM, mbu);
		/*END CARS*/

        if (effDate == null)
            map.put(WebConstants.EFF_DATE_REQ_PARAM_NAME,
                    WebConstants.DEFAULT_EFF_DATE);
        else
            map.put(WebConstants.EFF_DATE_REQ_PARAM_NAME, WPDStringUtil
                    .convertDateToString(effDate));

        map.put(WebConstants.EXP_DATE_REQ_PARAM_NAME, WPDStringUtil
                .convertDateToString(expDate));

        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(Contract.class.getName(),
                        WebConstants.GET_BASE_CONTRACT_FOR_MIGRATION, map))
                .getSearchResults();
    }


    /**
     * 
     * @param LOB
     * @param businessGroup
     * @param businessEntity
     * @return locateResults
     * @throws SevereException
     */
    public List getReservedContracts(List LOB, List businessGroup,
            List businessEntity, List marketBusinessUnit, List possibleBG, String searchString)
            throws SevereException {
        HashMap hashMap = new HashMap();
        List finalReserveContractsList = null;
        int numOfDomains = 0;
        /*START CARS*/
        List domainList = BusinessUtil.convertToDomains(LOB, businessEntity,
                businessGroup,marketBusinessUnit);
        /*END CARS*/
        DomainAdapterManager domainAdapterManager = new DomainAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
        Domain domainListRetrieved = null;
        List domainSysId = null;
        if (null != domainList && !domainList.isEmpty()) {
            domainSysId = new ArrayList(domainList.size());
            Iterator iterator = domainList.iterator();
            while (iterator.hasNext()) {
                Domain domain = (Domain) iterator.next();
                domainListRetrieved = domainAdapterManager.retrieve(domain,
                        adapterServicesAccess);
                if ((null != domainListRetrieved)
                        && (domainListRetrieved.getDomainSysId() != 0))
                    domainSysId.add(new Integer(domainListRetrieved
                            .getDomainSysId()));
            }
        }
        if (null != domainSysId)
            numOfDomains = domainSysId.size();
        hashMap.put("domainList", domainSysId);
        hashMap.put("domainCount", new Integer(numOfDomains));
        //		hashMap.put("group", possibleBG);
        if (null != searchString && !("").equals(searchString)) {
            String newSearchString = WPDStringUtil.escapeString(searchString);
            hashMap.put(WebConstants.POPUP_SEARCH_STRING, "%"
                    + newSearchString.trim().toUpperCase() + "%");
        } else {
            hashMap.put(WebConstants.POPUP_SEARCH_STRING, "%" + "" + "%");
        }
        List reservedContratsList = AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(
                        ContractIDReservePoolRecord.class.getName(),
                        WebConstants.GET_RESERVED_CONTRACT, hashMap))
                .getSearchResults();

        /*
         * finalReserveContractsList = new ArrayList();
         * 
         * if (null != reservedContratsList && !reservedContratsList.isEmpty()) {
         * Iterator iterator = reservedContratsList.iterator();
         * 
         * List contractIdList = new ArrayList(); // List contractIdList1 = new
         * ArrayList(); List domainIdsList = new
         * ArrayList(reservedContratsList.size());
         * 
         * while (iterator.hasNext()) { domainIdsList.clear(); int checkCount =
         * 0; ContractIDReservePoolRecord reserveContractIdDomainForCheck =
         * (ContractIDReservePoolRecord) iterator .next(); for (int i = 0; i <
         * reservedContratsList.size(); i++) { ContractIDReservePoolRecord
         * reserveContractIdDomain = (ContractIDReservePoolRecord)
         * reservedContratsList .get(i); if (null !=
         * reserveContractIdDomainForCheck && null !=
         * reserveContractIdDomainForCheck .getContractId()) { if
         * (reserveContractIdDomainForCheck .getContractId()
         * .equals(reserveContractIdDomain.getContractId()))
         * domainIdsList.add(reserveContractIdDomain); } } if (null !=
         * domainIdsList && !domainIdsList.isEmpty() && null != domainSysId &&
         * !domainSysId.isEmpty()) { if (numOfDomains == domainIdsList.size()) {
         * Iterator iterator2 = domainSysId.iterator(); while
         * (iterator2.hasNext()) { int domainId2 = ((Integer)
         * (iterator2.next())) .intValue(); Iterator iterator3 =
         * domainIdsList.iterator(); while (iterator3.hasNext()) {
         * ContractIDReservePoolRecord reserveContractId2 =
         * (ContractIDReservePoolRecord) iterator3 .next(); if
         * (reserveContractId2.getContractSysId() == domainId2) { checkCount++; } } }
         * if (checkCount == numOfDomains) { if (!contractIdList
         * .contains(reserveContractIdDomainForCheck .getContractId())) {
         * finalReserveContractsList .add(reserveContractIdDomainForCheck);
         * contractIdList .add(reserveContractIdDomainForCheck
         * .getContractId()); } } } }
         * //contractIdList1.add(reserveContractIdDomainForCheck.getContractId()); } }
         */
        return reservedContratsList;
    }


    /**
     * 
     * @param dateSegment
     * @return locateResults
     * @throws SevereException
     */
    public List getBaseContractDates(DateSegment dateSegment)
            throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.CONTRACT_SYS_ID, new Integer(dateSegment
                .getContractSysId()));
        map.put(WebConstants.CONTRACT_MODIFICATION_IND, dateSegment
                .getDateSegmentType());
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(DateSegment.class
                        .getName(), WebConstants.GET_BASE_CONTRACT_DATES, map))
                .getSearchResults();
    }


    /**
     * 
     * @param contractId
     * @return SearchResults
     * @throws SevereException
     */
    public List retrieveReserveContractById(String contractId)
            throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.CONTRACT_ID, contractId);
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(ReserveContractId.class
                        .getName(), WebConstants.GET_RESERVE_CONTRACT_BY_ID,
                        map)).getSearchResults();
    }


    /**
     * 
     * @param contract
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults searchContractLatestVesrion(Contract contract)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, contract.getContractId());

        int maxResultSize = 50;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(),
                BusinessConstants.LATEST_VERSION_CONTRACT, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param dateSegment
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults searchDateSegmentLatestVersionNumber(
            DateSegment datesegment) throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, datesegment.getContractId());
        refValSet.put(WebConstants.EFFECTIVEDATE, WPDStringUtil
                .convertDateToString(datesegment.getEffectiveDate()));
        refValSet.put(WebConstants.CONTRACT_MODIFICATION_IND, datesegment
                .getDateSegmentType());
        int maxResultSize = 50;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                BusinessConstants.LATEST_VERSION_CONTRACT_NUMBER, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param datesegment
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults searchDateSegmentLatestVersion(DateSegment datesegment)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, datesegment.getContractId());
        refValSet.put(WebConstants.EFFECTIVEDATE, WPDStringUtil
                .convertDateToString(datesegment.getEffectiveDate()));
        refValSet.put(WebConstants.CONTRACT_MODIFICATION_IND, datesegment
                .getDateSegmentType());
        int maxResultSize = 50;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                BusinessConstants.LATEST_VERSION_CONTRACT, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param contractID
     * @return
     * @throws SevereException
     */
    public List contractStatusFromLatestVersion(String contractID)
            throws SevereException {
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, contractID);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(),
                BusinessConstants.LATEST_VERSION_CONTRACT, refValSet);
        return AdapterUtil.performSearch(searchCriteria).getSearchResults();
    }


    /**
     * 
     * @param contractId
     * @return
     * @throws SevereException
     */
    public boolean isBaseContractInMarkForDeleteStatus(String contractId)
            throws SevereException {
        List resultList;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.CONTRACT_ID, contractId);
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(Contract.class.getName(),
                        "baseContractStatus", referenceValueSet);
        resultList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        if (null != resultList && !resultList.isEmpty()) {
            Contract contract = (Contract) resultList.get(0);
            if (contract.getStatus().equals(
                    BusinessConstants.MSG_MARKED_FOR_DELETION)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 
     * @param contract
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults searchContractLatestVersionNumber(Contract contract)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        refValSet.put(WebConstants.CONTRACT_ID, contract.getContractId());

        int maxResultSize = 50;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(),
                BusinessConstants.LATEST_VERSION_CONTRACT_NUMBER, refValSet,
                maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param answerOvrdInfoBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllAnswerOvrdInfo(AnswerOvrdInfoBO answerOvrdInfoBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess)
            throws SevereException {
        AdapterUtil.performRemove(answerOvrdInfoBO, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param benefitLineOvrdValBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllBenefitLineOvrdValInfo(
            BenefitLineOvrdValBO benefitLineOvrdValBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(benefitLineOvrdValBO, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param noteDomainAssnBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllNoteDomainAssnInfo(
            NoteDomainAssnBO noteDomainAssnBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(noteDomainAssnBO, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param noteDomainOvrdValBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllNoteDomainOvrdValInfo(
            NoteDomainOvrdValBO noteDomainOvrdValBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(noteDomainOvrdValBO, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param contractPricingInfoBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllPricingInfoObject(
            ContractPricingInfoBO contractPricingInfoBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(contractPricingInfoBO, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param comment
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteAllComment(Comment comment, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(comment, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param dateSegment
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteDateSegment(DateSegment dateSegment,
            Contract contract, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        /*
         * AdapterUtil.performRemove(dateSegment, audit.getUser(),
         * adapterServicesAccess);
         */

        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();

        refValSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(dateSegment
                .getDateSegmentSysId()));
        refValSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(contract
                .getContractSysId()));
        refValSet.put(WebConstants.CONTRACT_ID, contract.getContractId());
        refValSet.put(WebConstants.EFFECTIVE_DATE_D, dateSegment
                .getEffectiveDate());
        refValSet.put(WebConstants.CONTRACT_MODIFICATION_IND, dateSegment
                .getDateSegmentType());
        refValSet.put(WebConstants.USER, audit.getUser());
        refValSet.put(WebConstants.LAST_UPDATED_TIME, audit.getTime());

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.DELETE_FOR_CHECKINCONTRACT, refValSet);

        try {
            searchResults = AdapterUtil.performSearch(searchCriteria,
                    adapterServicesAccess);

        } catch(SevereException ex) {
            throw ex ;
        }finally{
        	// unlocking the date segment
        	User deleteUser = new UserImpl();
			LockManager lockManager = new LockManager();
			dateSegment.setContractId(contract.getContractId());
			lockManager.unlock(dateSegment, deleteUser);
        }

        return true;
    }


    /**
     * 
     * @param dateSegmentUpdateAfterDeleteBO
     * @return
     * @throws SevereException
     */
    public void updateAfterDeleteDateSegment(
            DateSegmentUpdateAfterDeleteBO dateSegment) throws SevereException {
        AdapterUtil
                .performUpdate(dateSegment, dateSegment.getLastUpdatedUser());
    }

    /**
	 * The method will fetch the Tier Definitions for the Product from the 
	 * PROD_TIER_DEFN_OVRD table.
	 * @param pdktBenefitDefinitionLocateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List getTierDefinisionsForContract(ProductBenefitDefinitionLocateCriteria 
	        benefitDefinitionLocateCriteria) throws SevereException {
		List productTierDefnsList = null;
		
		HashMap referenceValueSet = new HashMap();		
		referenceValueSet.put("productId", new Integer
		        (benefitDefinitionLocateCriteria.getProductId()));				
		referenceValueSet.put("benefitComponentId",new Integer
		        (benefitDefinitionLocateCriteria.getBenefitComponentId()));
		referenceValueSet.put("benefitId",new Integer
		        (benefitDefinitionLocateCriteria.getBenefitId()));		
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        ProductTierDefnOverrideBO.class.getName(),
				BusinessConstants.RETRIEVE_TIERDEFN_CONTRACT, referenceValueSet);
		productTierDefnsList = AdapterUtil.performSearch(searchCriteria)
		.getSearchResults();
		return productTierDefnsList;		
	}

    /**
     * 
     * @param contract
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteContract(Contract contract, Audit audit,
            AdapterServicesAccess adapterServicesAccess) throws SevereException {
        AdapterUtil.performRemove(contract, audit.getUser(),
                adapterServicesAccess);
        return true;
    }


    /**
     * 
     * @param contract
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean deleteContract(Contract contract, Audit audit)
            throws SevereException {
        AdapterUtil.performRemove(contract, audit.getUser());
        return true;
    }


    /**
     * 
     * @param locateCriteria
     * @param user
     * @param bool
     * @return locateResults
     * @throws SevereException
     */
    public List getContracts(int contractDateSegmentSysId)
            throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contractDateSegmentSysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                WebConstants.CONTRACT_BO, WebConstants.COUNT_DATE_SEGMENT,
                hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * 
     * @param contractSysId
     * @return
     * @throws SevereException
     */
    public List getAllDateSegments(int contractSysId) throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put(WebConstants.CONTRACT_SYS_ID, new Integer(contractSysId));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.CONTRACT_LOCATE_RESULT,
                WebConstants.GET_DATE_SEGMENT, hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * 
     * @param contractId
     * @return SearchResults
     * @throws SevereException
     */
    public List getAllContractVersions(String contractId)
            throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put(WebConstants.CONTRACT_ID, contractId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.CONTRACT_LOCATE_RESULT,
                WebConstants.GET_ALL_VERSION, hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }



    /**
     * 
     * @param contractId
     * @return SearchResults
     * @throws SevereException
     */
    public List fetchOlderVersionsForDateSegment(String contractId, Date effectiveDate, int version)
            throws SevereException {
    	
    	SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("MM-dd-yyyy");
    	String startDate = dateformatyyyyMMdd.format(effectiveDate);
    	//String endDate = dateformatyyyyMMdd.format(expiryDate);

        HashMap hashMap = new HashMap();
        hashMap.put(WebConstants.CONTRACT_ID, contractId);
        hashMap.put("startDate", startDate);
       // hashMap.put("endDate", endDate);
        hashMap.put(WebConstants.CONTRACT_VERSION, version);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.CONTRACT_LOCATE_RESULT,
                WebConstants.GET_OLDER_VERSION_FOR_DATE_SEGMENT, hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }

    /**
     * 
     * @param sysContractId
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults locateResrevedContract(SystemContractId sysContractId)
            throws SevereException {
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();
        int maxResultSize = 20;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.CONTRACT_LOCATE_RESULT,
                BusinessConstants.LOCATE_CONTRACT, refValSet, maxResultSize);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults;
    }


    /**
     * 
     * @param locateCriteria
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults locateContract(LocateCriteria locateCriteria)
            throws SevereException {
        SearchResults searchResults = null;
        ContractLocateCriteria contractLocateCriteria = (ContractLocateCriteria) locateCriteria;
        HashMap refValSet = new HashMap();
        setValuesToMap(refValSet, contractLocateCriteria);
        int maxResultSize = contractLocateCriteria.getMaximumResultSize();
        SearchCriteria searchCriteria;
        Logger.logInfo(" ^^^^^^^^^^^^^ The time into the Adapter :"
				+ Calendar.getInstance().getTime());
    	// SSCR21516 July2014
        if (((null == contractLocateCriteria.getLob()) || (contractLocateCriteria
				.getLob().size() == 0))
				
				&& ((null == contractLocateCriteria.getBusinessEntity()) || (contractLocateCriteria
						.getBusinessEntity().size() == 0))
						
				&& ((null == contractLocateCriteria.getGroupName()) || (contractLocateCriteria
						.getGroupName().size() == 0))

				&& (null == contractLocateCriteria.getMarketBusinessUnit() || contractLocateCriteria
						.getMarketBusinessUnit().size() == 0)

				&& (null == contractLocateCriteria.getContractType() || contractLocateCriteria
						.getContractType().size() == 0)

				&& (null == contractLocateCriteria.getStartDate())

				&& (null == contractLocateCriteria.getEndDate())) {
        	
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    BusinessConstants.CONTRACT_LOCATE_RESULT,
                    BusinessConstants.LOCATE_CONTRACT_BASIC, refValSet,
                    maxResultSize);
        }
        else if (((null == contractLocateCriteria.getLob()) || (contractLocateCriteria
                .getLob().size() == 0))
                && ((null == contractLocateCriteria.getBusinessEntity()) || (contractLocateCriteria
                        .getBusinessEntity().size() == 0))
                && ((null == contractLocateCriteria.getGroupName()) || (contractLocateCriteria
                        .getGroupName().size() == 0))
				/*START CARS*/
				&& (null == contractLocateCriteria.getMarketBusinessUnit() || contractLocateCriteria
						.getMarketBusinessUnit().size() == 0)/*END CARS*/) {
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    BusinessConstants.CONTRACT_LOCATE_RESULT,
                    BusinessConstants.LOCATE_CONTRACT_UNDOMAINED, refValSet,
                    maxResultSize);
        } else {//atleast one domain is there in the search criteria.calling
            // the old method
            searchCriteria = AdapterUtil
                    .getAdapterSearchCriteria(
                            BusinessConstants.CONTRACT_LOCATE_RESULT,
                            BusinessConstants.LOCATE_CONTRACT, refValSet,
                            maxResultSize);
        }
        searchResults = AdapterUtil.performSearch(searchCriteria);
        Logger.logInfo(" ^^^^^^^^^^^^^ The time out from the Adapter :"
				+ Calendar.getInstance().getTime());
        return searchResults;
    }


    /**
     * 
     * @param map
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    private void setValuesToMap(HashMap map,
            ContractLocateCriteria contractLocateCriteria) {
        String contractId = contractLocateCriteria.getContractId();
        if ((null != contractLocateCriteria.getContractId())
                && !("".equals(contractLocateCriteria.getContractId()))) {
            map.put(WebConstants.CONTRACT_ID, "%"
                    + contractLocateCriteria.getContractId().toUpperCase() + "%");
        }
        if ((null != contractLocateCriteria.getLob())
                && (contractLocateCriteria.getLob().size() > 0)) {
            map.put(WebConstants.LOB_URL_PARM_NAME, contractLocateCriteria
                    .getLob());
        }
        if ((null != contractLocateCriteria.getBusinessEntity())
                && (contractLocateCriteria.getBusinessEntity().size() > 0)) {
            map.put(WebConstants.ENTITY, contractLocateCriteria
                    .getBusinessEntity());
        }
        if ((null != contractLocateCriteria.getGroupName())
                && (contractLocateCriteria.getGroupName().size() > 0)) {
            map.put(WebConstants.GROUP, contractLocateCriteria.getGroupName());
        }
        /*START CARS*/
        //Market Business Unit
        if ((null != contractLocateCriteria.getMarketBusinessUnit())
                && (contractLocateCriteria.getMarketBusinessUnit().size() > 0)) {
            map.put(WebConstants.MARKET_BUSINESS_UNIT, contractLocateCriteria.getMarketBusinessUnit());
        }
        /*END CARS*/
        if ((null != contractLocateCriteria.getContractType())
                && (contractLocateCriteria.getContractType().size() > 0)) {
            map
                    .put(WebConstants.TYPE, contractLocateCriteria
                            .getContractType());
        }
        if ((null != contractLocateCriteria.getStartDate())
                && !("".equals(contractLocateCriteria.getStartDate()))) {
            map
                    .put(WebConstants.START_DATE, WPDStringUtil
                            .convertDateToString(contractLocateCriteria
                                    .getStartDate()));
        }
        if ((null != contractLocateCriteria.getEndDate())
                && !("".equals(contractLocateCriteria.getEndDate()))) {
            map.put(WebConstants.ENDDATE, WPDStringUtil
                    .convertDateToString(contractLocateCriteria.getEndDate()));
        }

    }


    /**
     * 
     * @return SearchResults
     * @throws SevereException
     */
    public List getReservedContractIds() throws SevereException {
        HashMap map = new HashMap();
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(ReserveContractId.class
                        .getName(), WebConstants.GET_RESERVED_CONTRACT, map))
                .getSearchResults();
    }


    /**
     * 
     * @param reservedContractIdInfo
     * @return
     * @throws SevereException
     */
    public void updateReserveContract(ReserveContractId reservedContractIdInfo)
            throws SevereException {
        AdapterUtil.performUpdate(reservedContractIdInfo,
                reservedContractIdInfo.getLastUpdatedUser());
    }


    /**
     * 
     * @param reservedContractIdInfo
     * @return
     * @throws SevereException
     */
    public ReserveContractId retrieveReserveContract(
            ReserveContractId reservedContractIdInfo) throws SevereException {
        return (ReserveContractId) AdapterUtil
                .performRetrieve(reservedContractIdInfo);
    }


    /**
     * 
     * @param contract
     * @return contract
     * @throws SevereException
     */
    public Contract getContractProductGeneralInfo(Contract contract)
            throws SevereException {

        List dateSegmentList = new ArrayList(2);
        ProductBO productBO = (ProductBO) AdapterUtil
                .performRetrieve(((DateSegment) contract.getDateSegmentList()
                        .get(0)).getProductBO());
        DateSegment dateSegment = (DateSegment) contract.getDateSegmentList()
                .get(0);
        dateSegment.setProductBO(productBO);
        dateSegmentList.add(0, dateSegment);
        contract.setDateSegmentList(dateSegmentList);
        return contract;
    }


    /**
     * This method returns duplicate PricingInfo
     * 
     * @param contractPricingInfo
     * @return SearchResults
     * @throws SevereException
     */
    public List getDuplicatePricingInfo(ContractPricingInfo contractPricingInfo)
            throws SevereException {
        //        List pricingInfoList = new ArrayList();
        //        List dateSegmentList = new ArrayList();

        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_DATESEGMENT_SYS_ID,
                new Integer(contractPricingInfo.getContractDateSegmentSysId()));
        referenceValueSet.put(WebConstants.COVERAGE, contractPricingInfo
                .getCoverage());
        referenceValueSet.put(WebConstants.PRICING, contractPricingInfo
                .getPricing());
        referenceValueSet.put(WebConstants.NETWORK, contractPricingInfo
                .getNetwork());

        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractPricingInfo.class.getName(),
                WebConstants.GET_DUPLICATE_PRICING_INFO, referenceValueSet);
        return AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
    }


    /**
     * 
     * @param contractPricingInfo
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean createPricingInfo(ContractPricingInfo contractPricingInfo,
            Audit audit, AdapterServicesAccess serviceAccess)
            throws SevereException {
        AdapterUtil.performInsert(contractPricingInfo, audit.getUser(),
                serviceAccess);
        return true;
    }


    /**
     * 
     * @param contract
     * @param contractDateSegmentSysId
     * @return contract
     * @throws SevereException
     */
    public Contract getContractPricingInfoList(Contract contract,
            int contractDateSegmentSysId) throws SevereException {

        //        List pricingInfoList = new ArrayList();
        List dateSegmentList = new ArrayList(2);

        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_DATESEGMENT_SYS_ID,
                new Integer(contractDateSegmentSysId));

        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractPricingInfo.class.getName(),
                WebConstants.GET_CONTRACT_PRICING_INFO, referenceValueSet);

        List pricingInfoList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        DateSegment dateSegment = new DateSegment();
        dateSegment.setPricingInfoList(pricingInfoList);
        dateSegmentList.add(0, dateSegment);
        contract.setDateSegmentList(dateSegmentList);
        return contract;
    }


    /**
     * 
     * @param contractSysId
     * @return productBO
     * @throws SevereException
     */
    public ProductBO getLatestProductVersion(int contractSysId,
            int dateSegmentid) throws SevereException {
        //        List productList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contractSysId));
        referenceValueSet.put(WebConstants.DATESEGMENT_ID, new Integer(
                dateSegmentid));
        adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ProductBO.class.getName(),
                        WebConstants.RETRIEVE_LATEST_PRODUCT_VERSION,
                        referenceValueSet);

        List productList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        if (null != productList && 0 != productList.size()) {
            ProductBO productBo = new ProductBO();
            productBo = (ProductBO) productList.get(0);
            return productBo;

        }
        return null;
    }


    public ProductBO getLatestProductVersion(int contractSysId,
            Date effectiveDate, int productSysId) throws SevereException {
        //		List productList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contractSysId));
        referenceValueSet.put(WebConstants.EFFECTIVEDATE, WPDStringUtil
                .convertDateToString(effectiveDate));
        referenceValueSet.put(WebConstants.PRODUCT_KEY, new Integer(
                productSysId));

        adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ProductBO.class.getName(),
                        WebConstants.RETRIEVE_PRODUCT_LATEST_VERSION,
                        referenceValueSet);

        List productList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        if (null != productList && 0 != productList.size()) {
            ProductBO productBo = new ProductBO();
            productBo = (ProductBO) productList.get(0);
            return productBo;

        }
        return null;
    }


    /**
     * 
     * @param contractSysId
     * @return productBO
     * @throws SevereException
     */
    public ProductBO getCurrentProduct(int dsSysId) throws SevereException {
        //        List productList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet
                .put(WebConstants.DATESEGMENT_ID, new Integer(dsSysId));

        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(),
                WebConstants.RETRIEVE_CURRENT_PRODUCT_FOR_CONTRACT,
                referenceValueSet);

        List productList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        if (null != productList && 0 != productList.size()) {
            ProductBO productBo = new ProductBO();
            productBo = (ProductBO) productList.get(0);
            return productBo;

        }
        return null;
    }


    /**
     * 
     * @param contractSysId
     * @return
     * @throws SevereException
     */
    public List checkMarkedForDeletionNotes(int contractSysId)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contractSysId));

        adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(NoteBO.class.getName(),
                        WebConstants.CHECK_MARKED_FOR_DELETION_NOTES,
                        referenceValueSet);
        return AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
    }


    /**
     * 
     * @param contractSysId
     * @return noteBO
     * @throws SevereException
     */
    public NoteBO getCurrentNote(int contractSysId) throws SevereException {
        //        List noteList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;

        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contractSysId));

        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                NoteBO.class.getName(),
                WebConstants.RETRIEVE_CURRENT_NOTE_FOR_CONTRACT,
                referenceValueSet);

        List noteList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        if (null != noteList && 0 != noteList.size()) {
            NoteBO noteBO = new NoteBO();
            noteBO = (NoteBO) noteList.get(0);
            return noteBO;

        }
        return null;
    }


    /**
     * 
     * @param contractPricingInfo
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean deletePricingInfoObject(
            ContractPricingInfo contractPricingInfo, Audit audit,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performRemove(contractPricingInfo, audit.getUser(),
                serviceAccess);
        return true;
    }


    /**
     * 
     * @param dateSegment
     * @param specificInfoAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean createDateSegment(DateSegment dateSegment,
            AdapterServicesAccess specificInfoAdapterServiceAccess)
            throws SevereException {
        AdapterUtil.performInsert(dateSegment,
                dateSegment.getLastUpdatedUser(),
                specificInfoAdapterServiceAccess);
        return true;
    }


    /**
     * Deleting the existing provider speciality codes
     * 
     * @param providerSpecialityBO
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteSpecialityCode(
            ProviderSpecialityCodeBO providerSpecialityCodeBO, Audit audit) throws SevereException {
        AdapterUtil.performRemove(providerSpecialityCodeBO, audit.getUser());
        return true;
    }


    /**
     * Method to persist the provider speciality codes
     * 
     * @param providerSpecialityBO
     * @param specificInfoAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public boolean persistProviderSpecialityCode(
            ProviderSpecialityCodeBO providerSpecialityCodeBO)
            throws SevereException {
        AdapterUtil.performInsert(providerSpecialityCodeBO,
                providerSpecialityCodeBO.getLastUpdatedUser());
        return true;
    }


    /**
     * 
     * @param
     * @return
     * @throws SevereException
     */
    public DateSegmentAssociationBO getAssociation(DateSegmentAssociationBO bo,
            AdapterServicesAccess access) throws SevereException {
        return (DateSegmentAssociationBO) AdapterUtil.performRetrieve(bo,
                access);
    }


    /**
     * 
     * @param
     * @return
     * @throws SevereException
     */
    public void deleteAssociation(DateSegmentAssociationBO bo, String user,
            AdapterServicesAccess access) throws SevereException {
        AdapterUtil.performRemove(bo, user, access);
    }


    /**
     * 
     * @param
     * @return
     * @throws SevereException
     */
    public void persistAssociation(DateSegmentAssociationBO bo, String user,
            AdapterServicesAccess access) throws SevereException {
        AdapterUtil.performInsert(bo, user, access);
    }


    /**
     * 
     * @param
     * @return
     * @throws SevereException
     */
    public AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(WebConstants.EWPD);
        return adapterServicesAccess;
    }


    /**
     * 
     * @param contract
     * @param dateSegmentId
     * @return contract
     * @throws SevereException
     */
    public Contract getContractSpecificInfoData(Contract contract,
            int dateSegmentId) throws SevereException {
        DateSegment dateSegment = new DateSegment();
        dateSegment.setDateSegmentSysId(dateSegmentId);
        dateSegment.setContractId(contract.getContractId());
        dateSegment = (DateSegment) retrieveDateSegment(dateSegment);
        List specificInfoList = new ArrayList(2);
        specificInfoList.add(dateSegment);
        contract.setDateSegmentList(specificInfoList);
        return contract;

    }


    /**
     * 
     * @param contract
     * @param dateSegmentId
     * @return contract
     * @throws SevereException
     */
    public Contract getAdaptedInfoData(Contract contract, int dateSegmentId)
            throws SevereException {
        AdaptedInfoBO adaptedInfoBO = new AdaptedInfoBO();
        adaptedInfoBO.setDateSegmentSysId(dateSegmentId);
        adaptedInfoBO = (AdaptedInfoBO) retrieveAdaptedInfo(adaptedInfoBO);
        List adaptedInfoList = new ArrayList(2);
        adaptedInfoList.add(adaptedInfoBO);
        contract.setDateSegmentList(adaptedInfoList);
        return contract;

    }


    /**
     * 
     * @param comment
     * @return
     * @throws SevereException
     */
    public void createComment(Comment comment,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performInsert(comment, comment.getLastUpdatedUser(),
                serviceAccess);
    }


    /**
     * This function updates the specific information
     * 
     * @param dateSegmentVO
     * @param specificInfoAdapterServiceAccess
     * @return
     * @throws SevereException
     */
    public void updateSpecificInfo(DateSegment dateSegmentVO,
            AdapterServicesAccess specificInfoAdapterServiceAccess)
            throws SevereException {
        AdapterUtil.performUpdate(dateSegmentVO, dateSegmentVO
                .getLastUpdatedUser(), specificInfoAdapterServiceAccess);
    }


    public void updateAdaptedInfo(AdaptedInfoBO adaptedInfoBO,
            AdapterServicesAccess adaptedInfoAdapterServiceAccess)
            throws SevereException {
        AdapterUtil.performUpdate(adaptedInfoBO, adaptedInfoBO
                .getLastUpdatedUser(), adaptedInfoAdapterServiceAccess);
    }


    /**
     * 
     * @param dateSgmtId
     * @param size
     * @return commentList
     * @throws SevereException
     */
    public List retrieveComments(int dateSgmtId, String size)
            throws SevereException {

        //        List commentList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                dateSgmtId));
        SearchCriteria adapterSearchCriteria = null;
        if ("10".equals(size)) {
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    Comment.class.getName(), WebConstants.GET_CONTRACT_COMMENT,
                    referenceValueSet, 10);

        } else {
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    Comment.class.getName(), WebConstants.GET_CONTRACT_COMMENT,
                    referenceValueSet);
        }

        List commentList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return commentList;
    }


    /**
     * 
     * @param comment
     * @return comment
     * @throws SevereException
     */
    public Comment retrieveSelectedComments(Comment comment)
            throws SevereException {

        return (Comment) AdapterUtil.performRetrieve(comment);
    }


    /**
     * This function copies the product component associations for the contract
     * 
     * @param sourceProductId
     * @param targetDateSegmentId
     * @param userId
     * @return targetProduct
     * @throws SevereException
     */
    public DateSegment copyProductComponents(int sourceProductId,
            int targetDateSegmentId, String userId) throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(WebConstants.SOURCE_PRODUCT_ID, new Integer(
                sourceProductId));
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                targetDateSegmentId));
        criteriaMap.put(WebConstants.USER, userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.COPY_PRODUCT_COMPONENT, criteriaMap);
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        SearchResults searchResults = null;
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(
                            transactionId,
                            this,
                            "copyProductComponents(int sourceProductId,int targetDateSegmentId, String userId)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(
                            transactionId,
                            this,
                            "copyProductComponents(int sourceProductId,int targetDateSegmentId, String userId)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(
                            transactionId,
                            this,
                            "copyProductComponents(int sourceProductId,int targetDateSegmentId, String userId)");
            throw new SevereException(null, null, ex);
        }
        DateSegment targetProduct = (DateSegment) searchResults
                .getSearchResults().get(0);
        return targetProduct;
    }


    /**
     * This function copies the product component associations for the contract
     * 
     * @param sourceProductId
     * @param targetDateSegmentId
     * @param userId
     * @return targetProduct
     * @throws SevereException
     */
    public DateSegment replaceProductComponents(
            int sourceProductId, int targetDateSegmentId,
            int sourceDateSegmentId, String userId, Audit audit)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(WebConstants.SOURCE_PRODUCT_ID, new Integer(
                sourceProductId));
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                targetDateSegmentId));
        criteriaMap.put(WebConstants.OLD_DATE_SEGMENT_SYS_ID, new Integer(
                sourceDateSegmentId));
        criteriaMap.put(WebConstants.USER, userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.REPLACE_PRODUCT_COMPONENT, criteriaMap);
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        SearchResults searchResults = null;
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(
                            transactionId,
                            this,
                            "replaceProductComponents(int sourceProductId,int targetDateSegmentId,int sourceDateSegmentId, String userId,Audit audit)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(
                            transactionId,
                            this,
                            "replaceProductComponents(int sourceProductId,int targetDateSegmentId,int sourceDateSegmentId, String userId,Audit audit)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(
                            transactionId,
                            this,
                            "replaceProductComponents(int sourceProductId,int targetDateSegmentId,int sourceDateSegmentId, String userId,Audit audit)");
            throw new SevereException(null, null, ex);
        }
        DateSegment targetProduct = (DateSegment) searchResults
                .getSearchResults().get(0);
        return targetProduct;
    }


    /**
     * 
     * @param sourceDateSegmentSysId
     * @param destiantionDateSegmentSysId
     * @param user
     * @param copyLegacyNotes
     * @return dateSegment
     * @throws SevereException
     */
    public DateSegment copyDateSegment(int contractSysId,
            int sourceDateSegmentSysId, int destiantionDateSegmentSysId, boolean copyLegacyNotes,
            String user, Audit audit, boolean createDs) throws SevereException {

        DateSegment dateSegment = null;
        HashMap criteriaMap = new HashMap();
        // criteriaMap.put(WebConstants.CONTRACT_SYS_ID,new
        // Integer(contractSysId));
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                sourceDateSegmentSysId));
        criteriaMap.put(WebConstants.DESTINATION_DATE_SEGMENT_ID, new Integer(
                destiantionDateSegmentSysId));
        criteriaMap.put(WebConstants.USER, user);
        if(createDs){
        	criteriaMap.put(WebConstants.COPY_LEGACY_NOTES, "DS_CREATE");
        }else{
        	criteriaMap.put(WebConstants.COPY_LEGACY_NOTES, "DS_COPY");
        }
        //System.out.println("createDs:====>"+createDs);
       // System.out.println("criteriaMap: ==== >"+criteriaMap);
        Logger.logInfo("createDs:====>"+createDs);
        Logger.logInfo("criteriaMap:====>"+criteriaMap);
        criteriaMap.put(WebConstants.LAST_UPDATED_TIME, audit.getTime());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(), WebConstants.COPY_DATE_SEGMENT,
                criteriaMap);
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        SearchResults searchResults = null;
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
            throw new SevereException(null, null, ex);
        }
        if (searchResults.getSearchResultCount() > 0)
            dateSegment = (DateSegment) searchResults.getSearchResults().get(0);

        return dateSegment;

    }


    /**
     * 
     * @param sourceDateSegmentSysId
     * @param destiantionDateSegmentSysId
     * @param user
     * @return dateSegment
     * @throws SevereException
     */
    public DateSegment copyDateSegmentForCheckout(int sourceDateSegmentSysId,
            int destiantionDateSegmentSysId, String productStatus, Audit audit)
            throws SevereException {

        DateSegment dateSegment = null;
        HashMap criteriaMap = new HashMap();
        // criteriaMap.put(WebConstants.CONTRACT_SYS_ID,new
        // Integer(contractSysId));
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                sourceDateSegmentSysId));
        criteriaMap.put(WebConstants.DESTINATION_DATE_SEGMENT_ID, new Integer(
                destiantionDateSegmentSysId));
        criteriaMap.put(WebConstants.IS_PRODUCT_LATEST, productStatus);
        criteriaMap.put(WebConstants.USER, audit.getUser());
        criteriaMap.put(WebConstants.LAST_UPDATED_TIME, audit.getTime());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.COPY_DATE_SEGMENT_CHECKOUT, criteriaMap);
        criteriaMap.put(WebConstants.COPY_LEGACY_NOTES, "DS_CHKOUT");
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        SearchResults searchResults = null;
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(
                            transactionId,
                            this,
                            "copyDateSegment(int sourceDateSegmentSysId,int destiantionDateSegmentSysId, String user,Audit audit)");
            throw new SevereException(null, null, ex);
        }
        if (searchResults.getSearchResultCount() > 0)
            dateSegment = (DateSegment) searchResults.getSearchResults().get(0);

        return dateSegment;

    }


    /**
     * 
     * @param SrcContractId
     * @param trgtConttractId
     * @param user
     * @return searchResults
     * @throws SevereException
     */
    public SearchResults copyContract(int SrcContractId, int trgtConttractId,
            String user, Audit audit) throws SevereException {
        Logger
                .logInfo("ContractAdapterManager - Entering copyContract(): Standard Benefit Copy");
        SearchResults searchResults = null;
        HashMap refValSet = new HashMap();

        refValSet.put(WebConstants.SOURCE_KEY, new Integer(SrcContractId));
        refValSet.put(WebConstants.DESTINATION_KEY,
                new Integer(trgtConttractId));
        refValSet.put(WebConstants.USER, user);
        refValSet.put(WebConstants.LAST_UPDATED_TIME, audit.getTime());

        SearchCriteria searchCriteria = AdapterUtil
                .getAdapterSearchCriteria(Contract.class.getName(),
                        WebConstants.COPY_CONTRACT, refValSet);
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(transactionId, this,
                            "copyContract(int SrcContractId, int trgtConttractId,String user,Audit audit)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(transactionId, this,
                            "copyContract(int SrcContractId, int trgtConttractId,String user,Audit audit)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(transactionId, this,
                            "copyContract(int SrcContractId, int trgtConttractId,String user,Audit audit)");
            throw new SevereException(null, null, ex);
        }
        Logger
                .logInfo("ContractAdapterManager - Returning copyStandardBenefit(): Standard Benefit Copy");
        return searchResults;
    }


    /**
     * 
     * @param contractId
     * @param LOB
     * @param businessGroup
     * @param businessEntity
     * @return searchResults
     * @throws SevereException
     */
    public List searchReservedContract(String contractId, List LOB,
            List businessGroup, List businessEntity, List marketBusinessUnit) throws SevereException {
        HashMap hashMap = new HashMap();
        if (null != contractId && !("".equals(contractId))) {
            hashMap.put(WebConstants.CONTRACT_ID, "%" + contractId + "%");
        }
        if (null != LOB && LOB.size() > 0) {
            hashMap.put(WebConstants.LOB_URL_PARM_NAME, LOB);
        }
        if (null != businessGroup && businessGroup.size() > 0) {
            hashMap.put(WebConstants.GROUP, businessGroup);
        }
        if (null != businessEntity && businessEntity.size() > 0) {
            hashMap.put(WebConstants.ENTITY, businessEntity);
        }
        if(null != marketBusinessUnit && marketBusinessUnit.size()>0){
        	hashMap.put("unit", marketBusinessUnit);
        }

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BusinessConstants.RESERVED_CONTRACT_LOCATE,
                WebConstants.GET_REAERVED_CONTRACT_ID, hashMap,
                WebConstants.MAX_SEARCH_RESULT_SIZE_CONTRACT);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * 
     * @param contractDateSegmentSysKey
     * @return adminList
     * @throws SevereException
     */
    public List getAdminList(int contractDateSegmentSysKey)
            throws SevereException {
        //        List adminList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.CONTRACT_DATE_SEGMENT_SYS_KEY,
                new Integer(contractDateSegmentSysKey));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractProductAdminBO.class.getName(), WebConstants.GET_ADMIN,
                referenceValueSet);

        SearchResults searchResults = AdapterUtil
                .performSearch(adapterSearchCriteria);
        List adminList = searchResults.getSearchResults();

        return adminList;
    }


    /**
     * this method gets the admin list for pop up
     * 
     * @param contractDateSegmentSysKey
     * @param productKey
     * @return adminList
     * @throws SevereException
     */
    public List getAdminListforPopup(int contractDateSegmentSysKey,
            int productKey) throws SevereException {
        //        List adminList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.CONTRACT_DATE_SEGMENT_SYS_KEY,
                new Integer(contractDateSegmentSysKey));
        referenceValueSet
                .put(WebConstants.PRODUCT_KEY, new Integer(productKey));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractProductAdminBO.class.getName(),
                WebConstants.RETRIEVE_ALL_LATEST_ADMIN_OPTION,
                referenceValueSet);
        List adminList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return adminList;
    }


    public int getAdminSequence(int adminKey, int productKey)
            throws SevereException {
        //    	 List adminList = new ArrayList();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.CONTRACT_ADMIN_KEY, new Integer(
                adminKey));
        referenceValueSet
                .put(WebConstants.PRODUCT_KEY, new Integer(productKey));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractProductAdminBO.class.getName(),
                WebConstants.RETRIEVE_ADMIN_SEQUENCE, referenceValueSet);
        List adminList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();

        return ((ContractProductAdminBO) adminList.get(0)).getSequence();

    }


    /**
     * 
     * @param contractProductAdminBO
     * @param audit
     * @param serviceAccess
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean createProductAdminOption(
            ContractProductAdminBO contractProductAdminBO, Audit audit,
            AdapterServicesAccess serviceAccess) throws SevereException,
            AdapterException {
        AdapterUtil.performInsert(contractProductAdminBO, audit.getUser(),
                serviceAccess);
        // To insert the note details attached to the admin option
        insertNotesInAdminOptions(contractProductAdminBO);

        return true;
    }


    private void insertNotesInAdminOptions(
            ContractProductAdminBO contractProductAdminBO)
            throws AdapterException {

        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.PRODUCT_KEY, new Integer(
                contractProductAdminBO.getProductKey()));
        referenceValueSet.put(WebConstants.CONTRACT_ADMIN_KEY, new Integer(
                contractProductAdminBO.getAdminKey()));
        referenceValueSet.put("contractDateSegmentSysKey", new Integer(
                contractProductAdminBO.getContractDateSegmentSysKey()));
        referenceValueSet.put("userName", contractProductAdminBO
                .getCreatedUser());

        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractProductAdminBO.class.getName(),
                WebConstants.INSERT_ADMIN_NOTES_SEQUENCE, referenceValueSet);
        try {
            List adminList = AdapterUtil.performSearch(adapterSearchCriteria)
                    .getSearchResults();
        } catch (SevereException exception) {

            throw new AdapterException(
                    "Exception Occured while inserting notes information",
                    exception);
        }

    }


    /**
     * 
     * @param contractProductAdminBO
     * @param audit
     * @param serviceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteProductAdminOption(
            ContractProductAdminBO contractProductAdminBO, Audit audit,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performRemove(contractProductAdminBO, audit.getUser(),
                serviceAccess);
        return true;
    }


    /**
     * 
     * @param benefitId
     * @return ruleIdBO
     * @throws SevereException
     */
    public RuleIdBO getRuleId(int benefitId, int benefitComponentId,
            int dateSegmentId) throws SevereException {
        RuleIdBO ruleIdBO = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.BENEFIT_ID, new Integer(benefitId));
        referenceValueSet.put(WebConstants.BENEFIT_COMP_ID, new Integer(
                benefitComponentId));
        referenceValueSet.put(WebConstants.DATESEGMENT_ID, new Integer(
                dateSegmentId));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RuleIdBO.class.getName(), WebConstants.RETRIEVE_RULE_ID,
                referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        if (null != searchResultList && searchResultList.size() > 0) {
            ruleIdBO = ((RuleIdBO) searchResultList.get(0));
        }
        return ruleIdBO;
    }


    public void insertBenefitHeadingDetails(CopyBenefitHeadingsBO headingsBO,
            String user, AdapterServicesAccess access) throws SevereException {
        if (null != access) {
            AdapterUtil.performInsert(headingsBO, user, access);
        } else {
            AdapterUtil.performInsert(headingsBO, user);
        }
    }

    public void deleteTierLvlLine(CopyBenefitHeadingsBO copyBenefitHeadingsBO,Audit audit) throws SevereException {
       
    	
    	HashMap criteriaMap = new HashMap();
        criteriaMap.put("standardBenefitId", new Integer(copyBenefitHeadingsBO.getBenefitsysId()));
        criteriaMap.put("benefitComponentId", new Integer( copyBenefitHeadingsBO.getComponentId()));
        criteriaMap.put("dateSegmentId", new Integer(copyBenefitHeadingsBO.getTrgtDateSegmentId()));
        criteriaMap.put("user", audit.getUser());
        criteriaMap.put("lastUpdatedTime", audit.getTime());
        
        //System.out.println("COPY_standardBenefitId"+ new Integer(copyBenefitHeadingsBO.getBenefitsysId()));
        //System.out.println("COPY_benefitComponentId"+ new Integer( copyBenefitHeadingsBO.getComponentId()));
        //System.out.println("COPY_dateSegmentId"+ new Integer(copyBenefitHeadingsBO.getTrgtDateSegmentId()));
        //System.out.println("COPY_user"+ audit.getUser());
        //System.out.println("COPY_lastUpdatedTime"+ audit.getTime());
        
        Logger.logInfo("COPY_standardBenefitId"+ new Integer(copyBenefitHeadingsBO.getBenefitsysId()));
        Logger.logInfo("COPY_benefitComponentId"+ new Integer( copyBenefitHeadingsBO.getComponentId()));
        Logger.logInfo("COPY_dateSegmentId"+ new Integer(copyBenefitHeadingsBO.getTrgtDateSegmentId()));
        Logger.logInfo("COPY_user"+ audit.getUser());
        Logger.logInfo("COPY_lastUpdatedTime"+ audit.getTime());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(ContractBenefitHeadings.class.getName(),"deleteTierDetailsForBenefit", criteriaMap);
        
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        SearchResults searchResults = null;
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil.logBeginTranstn(transactionId,
                            this,"deleteTierLvlLine(CopyBenefitHeadingsBO copyBenefitHeadingsBO,String user)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil.logTheEndTransaction(transactionId,
                            this,"deleteTierLvlLine(CopyBenefitHeadingsBO copyBenefitHeadingsBO,String user)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil.logAbortTxn(transactionId,
                            this,"copyProductDefaultComponents(CopyBenefitHeadingsBO copyBenefitHeadingsBO,String user)");
            throw new SevereException(null, null, ex);
        }
    }

    /**
     * 
     * @param benefitId
     * @return ruleIdBO
     * @throws SevereException
     */
    public List getRuleIdForDatafeed(List benefitIdList,
            int benefitComponentId, int dateSegmentId) throws SevereException {
        RuleIdBO ruleIdBO = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.BENEFIT_ID_LIST, benefitIdList);
        referenceValueSet.put(WebConstants.BENEFIT_COMP_ID, new Integer(
                benefitComponentId));
        referenceValueSet.put(WebConstants.DATESEGMENT_ID, new Integer(
                dateSegmentId));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                RuleIdBO.class.getName(),
                WebConstants.RETRIEVE_RULE_ID_DATAFEED, referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        return searchResultList;
    }


    /**
     * 
     * @param ruleIdBO
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean saveRuleInfo(RuleIdBO ruleIdBO, String user,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(ruleIdBO, user, serviceAccess);
        return true;
    }


    /**
     * 
     * @param state
     * @return contract
     * @throws SevereException
     */
    public Contract getStateCode(String state) throws SevereException {
        Contract contract = new Contract();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.STATE_DESC, state);
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(), WebConstants.RETRIEVE_STATE_CODE,
                referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        if (null != searchResultList && searchResultList.size() > 0) {
            contract = ((Contract) searchResultList.get(0));
        }
        return contract;
    }


    /**
     * 
     * @param contractId
     * @return contractIdExists
     * @throws SevereException
     */
    public boolean searchContractId(String contractId) throws SevereException {
        boolean contractIdExists = false;
        Contract contract = new Contract();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.CONTRACT_ID, contractId);
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(), WebConstants.RETRIEVE_CONTRACT_ID,
                referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        if (null != searchResultList && searchResultList.size() > 0) {
            contract = ((Contract) searchResultList.get(0));
            if (null != contract.getContractId()) {
                contractIdExists = true;
            }
        }
        return contractIdExists;
    }


    /**
     * 
     * @param contractId
     * @return
     * @throws SevereException
     */
    public int getContractSysID(String contractId) throws SevereException {
        HashMap referenceValueSet = new HashMap();

        referenceValueSet.put(WebConstants.CONTRACT_ID, contractId);
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(Contract.class.getName(),
                        WebConstants.RETRIEVE_CONTRACT_ID, referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        if (null != searchResultList && !searchResultList.isEmpty()) {
            return ((Contract) searchResultList.get(0)).getContractSysId();
        }
        return 0;
    }


    /**
     * 
     * @param productName
     * @return productBO
     * @throws SevereException
     */
    public ProductBO getProductCode(String productName) throws SevereException {
        ProductBO productBO = new ProductBO();
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.PRODUCT_NAME, productName);
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductBO.class.getName(), WebConstants.RETRIEVE_PRODUCT_CODE,
                referenceValueSet);
        List searchResultList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        if (null != searchResultList && searchResultList.size() > 0) {
            productBO = ((ProductBO) searchResultList.get(0));
        }
        return productBO;
    }


    /**
     * 
     * @param locateCriteria
     * @return SearchResults
     * @throws SevereException
     */
    public List getExistingContracts(
            ExistingContractLocateCriteria locateCriteria)
            throws SevereException {
        HashMap hashMap = new HashMap();
        SearchResults searchResult = null;
        hashMap.put(WebConstants.PRODUCT_ID, new Integer(locateCriteria
                .getProductId()));
        hashMap.put(WebConstants.CONTRACT_ID, locateCriteria.getContractId());
        SearchCriteria adapterSearchCriteria = null;
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(), WebConstants.RETIEVE_EXIT_CONTRACTS,
                hashMap);
        searchResult = AdapterUtil.performSearch(adapterSearchCriteria);

        return searchResult.getSearchResults();

    }


    /**
     * 
     * @param datafeedLocateCriteria
     * @return SearchResults
     * @throws SevereException
     */
    public List getScheduledCntrctsForDatafeed(
			DatafeedLocateCriteria datafeedLocateCriteria)
			throws SevereException {
		SearchResults searchResult = null;
		SearchCriteria adapterSearchCriteria = null;
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put(WebConstants.STATUS_DESC, datafeedLocateCriteria
				.getStatus());
		if (datafeedLocateCriteria.getStatus().equals(
				"SCHEDULED_FOR_PRODUCTION")) {
			referenceValueSet.put(WebConstants.DT_SGMNT_MDFD, "T");
		} else if (datafeedLocateCriteria.getStatus().equals(
				"SCHEDULED_FOR_TEST")) {
			referenceValueSet.put(WebConstants.DT_SGMNT_MDFD, "Y");
		}
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				Contract.class.getName(),
				WebConstants.RETRIEVE_SCHEDULED_CONTRACTS, referenceValueSet);
		searchResult = AdapterUtil.performSearch(adapterSearchCriteria);

		return searchResult.getSearchResults();

	}


    /**
     * 
     * @param datafeedLocateCriteria
     * @return SearchResults
     * @throws SevereException
     */
    public List getScheduledMandateCntrctsForDatafeed(
            DatafeedLocateCriteria datafeedLocateCriteria)
            throws SevereException {

        SearchResults searchResult = null;
        SearchCriteria adapterSearchCriteria = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.STATUS_DESC, datafeedLocateCriteria
                .getStatus());
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                Contract.class.getName(),
                WebConstants.RETRIEVE_SCHEDULE_MANDATE_CONTRACTS,
                referenceValueSet);
        searchResult = AdapterUtil.performSearch(adapterSearchCriteria);

        return searchResult.getSearchResults();

    }


    /**
     * 
     * @param dataFeedStatus
     * @param serviceAccess
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean updateDataFeedStatus(DataFeedStatus dataFeedStatus,
            String userId, AdapterServicesAccess serviceAccess)
            throws SevereException {
        AdapterUtil.performUpdate(dataFeedStatus, userId, serviceAccess);
        return true;
    }


    /**
     * 
     * @param benefitHeadings
     * @param user
     * @param serviceAccess
     * @return
     * @throws SevereException
     */
    public boolean deleteBenefitValues(ContractBenefitHeadings benefitHeadings,
            String user, AdapterServicesAccess serviceAccess)
            throws SevereException {
        AdapterUtil.performRemove(benefitHeadings, user, serviceAccess);
        return true;
    }


    /**
     * 
     * @param benefitHeadings
     * @param user
     * @param serviceAccess
     * @return
     * @throws SevereException
     */
    public void insertProductBenefitValues(
            ContractBenefitHeadings benefitHeadings, String user)
            throws SevereException {
        ContractBenefitHeadings bnftHeadings = null;
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(WebConstants.DATESEGMENT_ID, new Integer(
                benefitHeadings.getDateSegmentId()));
        //    criteriaMap.put("benefitComponentId",new Integer(sourceLineId));
        criteriaMap.put("productId", new Integer(benefitHeadings
                .getDateSegmentId()));
        criteriaMap.put(WebConstants.USER, user);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractBenefitHeadings.class.getName(), "updateContractLines",
                criteriaMap);
        //   AdapterServicesAccess access = AdapterUtil.getAdapterService();
        SearchResults searchResults = null;
    }


    /**
     * 
     * @param contractBenefitHeadings
     * @param user
     * @return
     * @throws SevereException
     */
    public void updateBenefitValues(
            ContractBenefitHeadings contractBenefitHeadings, String user,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(contractBenefitHeadings, user, serviceAccess);
    }


    /**
     * This function copies the product component associations for the contract
     * 
     * @param sourceProductId
     * @param targetDateSegmentId
     * @param userId
     * @return
     * @throws SevereException
     */
    public DateSegment copyProductDefaultComponents(int sourceProductId,
            int targetDateSegmentId, String userId) throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(WebConstants.SOURCE_PRODUCT_ID, new Integer(
                sourceProductId));
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                targetDateSegmentId));
        criteriaMap.put(WebConstants.USER, userId);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.COPY_PRODUCT_DEFAULT_COMPONENTS, criteriaMap);
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        SearchResults searchResults = null;
        try {
            AdapterUtil.beginTransaction(access);
            AdapterUtil
                    .logBeginTranstn(
                            transactionId,
                            this,
                            "copyProductDefaultComponents(int sourceProductId,int targetDateSegmentId, String userId)");
            searchResults = AdapterUtil.performSearch(searchCriteria, access);
            AdapterUtil.endTransaction(access);
            AdapterUtil
                    .logTheEndTransaction(
                            transactionId,
                            this,
                            "copyProductDefaultComponents(int sourceProductId,int targetDateSegmentId, String userId)");
        } catch (Exception ex) {
            AdapterUtil.abortTransaction(access);
            AdapterUtil
                    .logAbortTxn(
                            transactionId,
                            this,
                            "copyProductDefaultComponents(int sourceProductId,int targetDateSegmentId, String userId)");
            throw new SevereException(null, null, ex);
        }
        DateSegment targetProduct = (DateSegment) searchResults
                .getSearchResults().get(0);
        return targetProduct;
    }


    /**
     * 
     * @param contractID
     * @return
     * @throws SevereException
     */
    public DateSegment minEffectiveOfLatestContract(String contractID)
            throws SevereException {
        HashMap criteriaMap = new HashMap();
        criteriaMap.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(0));
        criteriaMap.put(WebConstants.CONTRACT_ID, contractID);
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                WebConstants.MIN_EFFECTIVE_OF_LATEST_CONTRACT, criteriaMap);
        List resultList = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        if (null != resultList && !resultList.isEmpty()) {
            return (DateSegment) resultList.get(0);
        }
        return null;
    }


    /**
     * 
     * @param sourceStandardBenefitId
     * @param destiantionDateSegmentSysId
     * @param sourceLineId
     * @param sourceDateSegmentSysId
     * @param user
     * @param benefitComponentId
     * @param serviceAccess
     * @return targetProduct
     * @throws SevereException
     */
    public ContractBenefitHeadings updateContractNotes(
            int sourceDateSegmentSysId, int trgtDateSegmentSysId, String user,
            AdapterServicesAccess serviceAccess) throws SevereException {

        ContractBenefitHeadings bnftHeadings = null;
        HashMap criteriaMap = new HashMap();

        criteriaMap.put(WebConstants.SOURCE_ID, new Integer(
                sourceDateSegmentSysId));
        criteriaMap.put(WebConstants.DATESEGMENT_ID, new Integer(
        		trgtDateSegmentSysId));
        criteriaMap.put(WebConstants.USER, user);

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractBenefitHeadings.class.getName(),
                WebConstants.UPDATED_CONTRACT_NOTES, criteriaMap);

        SearchResults searchResults = null;
        if (null != serviceAccess) {
            searchResults = AdapterUtil.performSearch(searchCriteria,
                    serviceAccess);
        } else {
            searchResults = AdapterUtil.performSearch(searchCriteria);
        }

        ContractBenefitHeadings targetProduct = null;
        if (searchResults.getSearchResults().size() != 0)
            targetProduct = (ContractBenefitHeadings) searchResults
                    .getSearchResults().get(0);
        return targetProduct;

    }
    
    /**
     * 
     * @param sourceDateSegmentSysId
     * @param destiantionDateSegmentSysId
     * @param user
     * @param serviceAccess
     * @return void
     * @throws SevereException
     */
    public void updateContractInfoForCopy(int sourceDateSegmentSysId,int targetDateSegmentSysId, int contractSysId,Audit audit,
    	    AdapterServicesAccess serviceAccess) throws SevereException {
    	
    	ContractBenefitHeadings bnftHeadings = null;
    	SearchResults searchResults = null;
        HashMap criteriaMap = new HashMap();

        criteriaMap.put(WebConstants.SOURCE_ID, new Integer(
                sourceDateSegmentSysId));
        criteriaMap.put(WebConstants.DATESEGMENT_ID, new Integer(
                        targetDateSegmentSysId));
        criteriaMap.put(WebConstants.CONTRACT_SYS_ID,new Integer(contractSysId));
        criteriaMap.put(WebConstants.USER, audit.getUser());
        criteriaMap.put(WebConstants.LAST_UPDATED_TIME, audit.getTime());

        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractBenefitHeadings.class.getName(),
                WebConstants.CNTRCT_BNFT_COPY, criteriaMap);

        searchResults = AdapterUtil.performSearch(searchCriteria,
                    serviceAccess);
    }


    /**
     * 
     * @param testData
     * @param audit
     * @return
     * @throws SevereException
     */
    public void deleteTestData(TestData testData, Audit audit,
            AdapterServicesAccess serviceAccess) throws SevereException {

        AdapterUtil.performRemove(testData, audit.getUser(), serviceAccess);

    }


    /**
     * 
     * @param contractSysId
     * @param effectiveDate
     * @return
     * @throws SevereException
     */
    public List getEffectiveDateSegmentId(int contractSysId,
            String effectiveDate) throws SevereException {
        HashMap map = new HashMap();
        map.put(WebConstants.CONTRACT_SYS_ID, new Integer(contractSysId));
        map.put(WebConstants.EFFECTIVE_DATE_FOR_COPY, effectiveDate);
        return AdapterUtil.performSearch(
                AdapterUtil.getAdapterSearchCriteria(DateSegment.class
                        .getName(), WebConstants.GET_EFFECTIVE_DATE_SEGMENT_ID,
                        map)).getSearchResults();
    }


    /**
     * 
     * @param contractSysId
     * @param productId
     * @return productBO
     * @throws SevereException
     */
    public ProductBO getLatestProductVersionForCopy(int contractSysId,
            int productId, int dateSegmentId) throws SevereException {

        ProductBO productBO = new ProductBO();
        productBO.setProductKey(productId);

        productBO = (ProductBO) AdapterUtil.performRetrieve(productBO);
        if (productBO != null
                && WebConstants.MARKED_FOR_DELETION.equals(productBO
                        .getStatus())) {
            productBO = getLatestProductVersion(contractSysId, dateSegmentId);
        } else
            productBO = null;

        return productBO;

    }


    /**
     * 
     * @param noteId
     * @param version
     * @return
     * @throws SevereException
     */
    public NoteBO checkNote(String noteId, int version) throws SevereException {
        NoteBO noteBO = new NoteBO();
        noteBO.setNoteId(noteId);
        noteBO.setVersion(version);

        noteBO = (NoteBO) AdapterUtil.performRetrieve(noteBO);

        if (noteBO != null
                && WebConstants.MARKED_FOR_DELETION.equals(noteBO.getStatus())) {

        } else
            noteBO = null;

        return noteBO;

    }


    /**
     * @param subObject
     * @param contractHistoryAdapterServiceAccess
     */
    public boolean createContractHistoryDetails(ContractHistory subObject,
            AdapterServicesAccess contractHistoryAdapterServiceAccess)
            throws SevereException {
        // TODO Auto-generated method stub

        AdapterUtil.performInsert(subObject, subObject.getLastUpdatedUser(),
                contractHistoryAdapterServiceAccess);
        return true;

    }


    /**
     * Method to retrieve SPS Benefit Lines
     * 
     * @param dateSegmentSysId
     * @return List
     * @throws SevereException
     */

    public Map getSPSBnftLines(int dateSegmentSysId) throws SevereException {

        HashMap benefitLineMap = new HashMap();
    	ContractSPSBO contractSPSBO = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                dateSegmentSysId));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractSPSBO.class.getName(),
                BusinessConstants.RETRIEVE_SPS_BENEFITLINES, referenceValueSet);
        List benefitLineList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractSPSBO.class.getName(),
                BusinessConstants.RETRIEVE_TIERED_SPS_BENEFITLINES, referenceValueSet);
        List tieredBenefitLineList = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        
        benefitLineMap.put("benefitLineList", benefitLineList);
        benefitLineMap.put("tieredBenefitLineList", tieredBenefitLineList);

        return benefitLineMap;

    }
    
    public List getContractDFAdminMethods(int dateSegmentId) throws SevereException {
    	HashMap criteriaParmas = new HashMap();
    	criteriaParmas.put("dateSegmentId", new Integer(dateSegmentId));
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			"com.wellpoint.wpd.common.contract.bo.ContractDFMethod", "getContractAdminMethods", criteriaParmas);
    	SearchResults results = AdapterUtil.performSearch(searchCriteria);
    	return results.getSearchResults();
    }
    
    public List getContractDFNotes(int dateSegmentId) throws SevereException {
    	HashMap criteriaParmas = new HashMap();
    	criteriaParmas.put("dateSegmentId", new Integer(dateSegmentId));
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			"com.wellpoint.wpd.common.contract.bo.ContractDFNote", "retrieveNotes", criteriaParmas);
    	SearchResults results = AdapterUtil.performSearch(searchCriteria);
    	return results.getSearchResults();
    }
    
    public List getContractDFBenefits(int dateSegmentId) throws SevereException {
    	HashMap criteriaParmas = new HashMap();
    	criteriaParmas.put("dateSegmentSysId", new Integer(dateSegmentId));
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			ContractDFBenefit.class.getName(), "retrieveContractBenefits", criteriaParmas);
    	SearchResults results = AdapterUtil.performSearch(searchCriteria);
    	return results.getSearchResults();
    }
    
    public List getContractDFQuestions(int dateSegmentId) throws SevereException {
    	HashMap criteriaParmas = new HashMap();
    	criteriaParmas.put("dateSegmentSysId", new Integer(dateSegmentId));
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    			ContractDFQuestionnaire.class.getName(), "retrieveQuestions", criteriaParmas);
    	SearchResults results = AdapterUtil.performSearch(searchCriteria);
    	return results.getSearchResults();
    }

    /**
     * Method to retrieve Admin Options
     * 
     * @param dateSegmentSysId
     * @return List
     * @throws SevereException
     */

    public Map getAdminOptions(int dateSegmentSysId) throws SevereException {

		HashMap adminOptionMap = new HashMap();
		ContractSPSBO contractSPSBO = null;
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
				dateSegmentSysId));

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractSPSBO.class.getName(),
				BusinessConstants.UPDATE_CNTRCT_BNFT_QSTNNR, referenceValueSet);

		AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractSPSBO.class.getName(),
				BusinessConstants.RETRIEVE_ADMINOPTIONS, referenceValueSet);
		List adminOptionList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractSPSBO.class.getName(),
				BusinessConstants.RETRIEVE_TIERED_ADMINOPTIONS,
				referenceValueSet);
		List tieredAdminOptionList = AdapterUtil.performSearch(
				adapterSearchCriteria).getSearchResults();

		adminOptionMap.put("adminOptionList", adminOptionList);
		adminOptionMap.put("tieredAdminOptionList", tieredAdminOptionList);

		return adminOptionMap;

	}


    /**
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
    public List locateContractLevelQuestionaire(LocateCriteria locateCriteria)
            throws SevereException {

        ContractAdminOptionLocateCriteria contractAdminOptionLocateCriteria = (ContractAdminOptionLocateCriteria) locateCriteria;

        SearchResults searchResults = null;

        SearchCriteria searchCriteria = null;

        HashMap refValSet = new HashMap();

        // Retrieving the root question
        if (contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_QUESTIONAIRE) {

            refValSet.put("adminOptSysId", new Integer(
                    contractAdminOptionLocateCriteria.getAdminOptSysId()));
            refValSet.put("dateSegmentId", new Integer(
                    contractAdminOptionLocateCriteria.getEntityId()));
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ContractAssnQuestionnaireBO.class.getName(),
                    "getContractLevelQuestionnaire", refValSet);

        } else if (contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_CHILD_QUESTIONAIRE) {

            ContractAssnQuestionnaireBO contractAssnQuestionnaireBO = (ContractAssnQuestionnaireBO) contractAdminOptionLocateCriteria
                    .getContractAssnQuestionnaireBO();
            int questionnaireId = contractAssnQuestionnaireBO
                    .getQuestionnaireId();

            refValSet.put("selectedAnswerid", new Integer(
                    contractAdminOptionLocateCriteria.getSelectedAnswerId()));
            refValSet.put("questionnaireId", new Integer(questionnaireId));
            refValSet.put("contractSysId", new Integer(
                    contractAdminOptionLocateCriteria.getContractSytemId()));
            refValSet.put("adminOptSysId", new Integer(
                    contractAdminOptionLocateCriteria.getAdminOptSysId()));
            refValSet.put("dateSegmentId", new Integer(
                    contractAdminOptionLocateCriteria.getEntityId()));
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ContractAssnQuestionnaireBO.class.getName(),
                    "getChildQuestionnaire", refValSet);

        } else if (contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_QUESTIONAIRE_DF) {

            refValSet.put("adminOptSysId", new Integer(
                    contractAdminOptionLocateCriteria.getAdminOptSysId()));
            refValSet.put("dateSegmentId", new Integer(
                    contractAdminOptionLocateCriteria.getEntityId()));
            searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ContractAssnQuestionnaireBO.class.getName(),
                    "getContractLevelQuestionnaireDF", refValSet);
        }

        searchCriteria.setReferenceValueSet(refValSet);
        searchResults = AdapterUtil.performSearch(searchCriteria);

        return searchResults.getSearchResults();
    }


    /**
     * @param subObject
     * @param audit
     * @param adapterServicesAccess
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public boolean delete(ContractAssnQuestionnaireBO subObject, Audit audit,
            AdapterServicesAccess adapterServicesAccess)
            throws ServiceException, AdapterException {
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType("delete");
        btc.setBusinessTransactionUser(audit.getUser());

        adapterServicesAccess.removeObject(subObject, subObject.getClass()
                .getName(), btc);

        return true;
    }


    public boolean persistContractQuestionnaire(
            ContractAssnQuestionnaireBO questionnaireBO, Audit audit,
            boolean insertFlag, AdapterServicesAccess access)
            throws AdapterException {
        Logger
                .logInfo("StandardBenefitAdapterManager - Entering persistQuestionnaireForBenefit(): Questionnaire for benefit insert");
        BusinessTransactionContext btc = new BusinessTransactionContextImpl();
        btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
        btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
        questionnaireBO.setCreatedUser(audit.getUser());
        questionnaireBO.setLastUpdatedUser(audit.getUser());
        questionnaireBO.setCreatedTimestamp(audit.getTime());
        questionnaireBO.setLastUpdatedTimestamp(audit.getTime());
        try {
            AdapterUtil.performInsert(questionnaireBO, audit.getUser(), access);
        } catch (SevereException e) {
            throw new AdapterException(e.getMessage(), e);
        }
        Logger
                .logInfo("StandardBenefitAdapterManager - Returning persistQuestionnaireForBenefit(): Questionnaire for benefit insert");

        return true;
    }


    /**
     * @param dateSegmentId
     * @param referenceId
     * @return
     * @throws SevereException
     */
    public List locateContractAdjudicationIndicator(int dateSegmentId,
            String referenceId) throws SevereException {

        SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();

        refValSet.put("dateSegmentId", new Integer(dateSegmentId));
        refValSet.put("reference", referenceId);

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractAssnQuestionnaireBO.class.getName(),
                "getContractAdjudicationIndicator", refValSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public List locateContractAccumulatorIndicator(
            ContractBenefitAdministrationLocateCriteria locateCriteria)
            throws SevereException {

        SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();

        refValSet.put("dateSegmentId",
                new Integer(locateCriteria.getEntityId()));
        refValSet.put("benefitCompId", new Integer(locateCriteria
                .getBenefitComponentId()));
        refValSet.put("benefitAdmnId", new Integer(locateCriteria
                .getBenefitAdminSysId()));
        refValSet.put("reference", locateCriteria.getReferenceId());

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractAssnQuestionnaireBO.class.getName(),
                "getContractAccumulatorIndicator", refValSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * @param datesegment
     * @return
     * @throws SevereException
     */
    public List getBYCYAnswerList(int datesegmentId) throws SevereException {
        SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();

        refValSet.put("dateSegmentId", new Integer(datesegmentId));
        refValSet.put("reference", WebConstants.BY_CY_REFERNCE_ID);
        refValSet.put("answerDesc", WebConstants.BY_CY_QSTN_DESC);

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractAssnQuestionnaireBO.class.getName(), "getBYCYQuestion",
                refValSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }


    /**
     * Method get duplicate refernce in benefit line and in question attached
     * toa contract
     * 
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public boolean getDuplicateReferences(int dateSegment)
            throws SevereException {
        List duplicateRef = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(),
                        WebConstants.GET_CONTRACT_DUPLICATE_BENEFIT_LINE_REF,
                        referenceValueSet);
        duplicateRef = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        //Checks if the list of duplicate references in benefit line is null.
        if (null == duplicateRef || duplicateRef.size() == WebConstants.VERSION) {
            adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                    ContractQuestUniqueReferenceBO.class.getName(),
                    WebConstants.GET_CONTRACT_DUPLICATE_ADMIN_QUEST_REF,
                    referenceValueSet);
            duplicateRef = AdapterUtil.performSearch(adapterSearchCriteria)
                    .getSearchResults();
            //if the list is null returns that datesegment doesn't has any
            // unique reference.
            if (null == duplicateRef
                    || duplicateRef.size() == WebConstants.VERSION) {
                return true;
            }
        }
        return false;
    }


    /**
     * Retrieves all the corresponding DateSegments for a Contract
     * 
     * @param contractId
     * @return
     * @throws SevereException
     */
    public List retrieveDateSegments(String contractId) throws SevereException {
        List dateSegmentList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.CONTRACT_ID, new String(contractId)
                .toUpperCase().trim());
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), WebConstants.GET_CONTRACT_DATE_SEGMENTS,
                        referenceValueSet);
        dateSegmentList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
         return dateSegmentList;
    }


    /**
     * deletes all the notes attached to uncoded benefitlines/questions
     * 
     * @param contractSysId
     * @return
     * @throws SevereException
     */
    public void deleteUncodedNotes(int contractSysid) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(BusinessConstants.CONTRACT_SYS_ID, new Integer(
                contractSysid));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), BusinessConstants.DELETE_UNCODED_NOTES,
                        referenceValueSet);
        AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
    }


    /**
     * Retrieves all the duplicate references from benefit level
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getUncodedLineNotes(int dateSegment, int count)
            throws SevereException {
        List uncodedBenefitNotes = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), WebConstants.GET_UNCODED_LINE_NOTES,
                        referenceValueSet);
        uncodedBenefitNotes = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return uncodedBenefitNotes;
    }

    public List getUncodedLineNotesForTier(int dateSegment, int count)
    throws SevereException {
		List uncodedBenefitNotes = null;
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(dateSegment));
		referenceValueSet.put(WebConstants.COUNT, new Integer(count));
		SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), 
				"getUncodedLineNotesForTier",referenceValueSet);
		uncodedBenefitNotes = AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
		return uncodedBenefitNotes;
	}
	
	
	public List getTierDetailList(List tierSysIdList, int dateSegment)
    throws SevereException {
		List uncodedBenefitNotes = null;
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("tierSysIdList", tierSysIdList);
		referenceValueSet.put("entitySysId", new Integer(dateSegment));
		SearchCriteria adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), 
				"getTierDetForUncodedLineNotes",referenceValueSet);
		uncodedBenefitNotes = AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
		return uncodedBenefitNotes;
	}
    /**
     * Retrieves all the duplicate references from benefit level
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getUncodedQuestNotes(int dateSegment, int count)
            throws SevereException {
        List uncodedQuestNotes = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), WebConstants.GET_UNCODED_QUESTION_NOTES,
                        referenceValueSet);
        uncodedQuestNotes = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return uncodedQuestNotes;
    }

    /**
     * Retrieves all the duplicate references from benefit level
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getUncodedQuestNotesForTier(int dateSegment, int count)
            throws SevereException {
        List uncodedQuestNotes = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(TierDefinitionBO.class
                        .getName(), "getUnansweredQuesForTier",
                        referenceValueSet);
        uncodedQuestNotes = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return uncodedQuestNotes;
    }
    /**
     * Retrieves all the duplicate references from benefit level
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getUncodedPrdQuestNotes(int dateSegment, int count)
            throws SevereException {
        List uncodedProductNotes = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), WebConstants.GET_UNCODED_PRD_QUEST_NOTES,
                        referenceValueSet);
        uncodedProductNotes = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        return uncodedProductNotes;
    }


    /**
     * Retrieves all the duplicate references from benefit level
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getDuplicateAllBenefitRef(int dateSegment, int count)
            throws SevereException {
        List duplicateAllBenefitRef = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(),
                        WebConstants.GET_CONTRACT_DUPLICATE_ALL_BENEFIT_REF,
                        referenceValueSet);
        duplicateAllBenefitRef = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        return duplicateAllBenefitRef;
    }


    /**
     * Retrieves all Question which has duplicate references
     * 
     * @param dateSegment
     * @return
     * @throws SevereException
     */
    public List getDuplicateAllQuestionRef(int dateSegment, int count)
            throws SevereException {
        List duplicateAllBenefitRef = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                dateSegment));
        referenceValueSet.put(WebConstants.COUNT, new Integer(count));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(),
                        WebConstants.GET_CONTRACT_DUPLICATE_ALL_QUEST_REF,
                        referenceValueSet);
        duplicateAllBenefitRef = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        return duplicateAllBenefitRef;
    }


    /**
     * Method to update the audit information for contract
     * 
     * @param contractAuditInfo
     * @param serviceAccess
     * @throws SevereException
     */
    public void updateContractAduitInformation(
            ContractAuditInfo contractAuditInfo,
            AdapterServicesAccess serviceAccess) throws SevereException {
        AdapterUtil.performUpdate(contractAuditInfo, contractAuditInfo
                .getLastUpdatedUser(), serviceAccess);
    }


    /**
     * Method to update the Datesegment
     * 
     * @param dateSegment
     * @throws SevereException
     */
    public void updateDateSegment(DateSegment dateSegment)
            throws SevereException, AdapterException {
        AdapterUtil
                .performUpdate(dateSegment, dateSegment.getLastUpdatedUser());
    }


    /**
     * Method to retrieve the notes attached to the unanswered admin option
     * Question at benefit level
     * 
     * @param contract
     * @return boolean
     * @throws SevereException
     */
    public boolean validateNotes(Contract contract) throws SevereException {
        List unAnsweredQuestionsList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contract.getContractSysId()));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(),
                        BusinessConstants.RETRIEVE_UNANSWERED_QUESTION,
                        referenceValueSet);
        unAnsweredQuestionsList = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        if (null != unAnsweredQuestionsList
                && (unAnsweredQuestionsList.size() != 0)) {
            return false;
        }
        return true;
    }


    /**
     * Method to retrieve the notes attached to the uncoded benefit lines
     * 
     * @param contract
     * @return boolean
     * @throws SevereException
     */
    public boolean validateLineNotes(Contract contract) throws SevereException {
        List unAnsweredQuestionsList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contract.getContractSysId()));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(), BusinessConstants.IS_UNCODED_LINE_NOTE,
                        referenceValueSet);
        unAnsweredQuestionsList = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        if (null != unAnsweredQuestionsList
                && (unAnsweredQuestionsList.size() != 0)) {
            return false;
        }
        return true;
    }
    
    /**
     * Method to retrieve the notes attached to the uncoded benefit lines
     * 
     * @param contract
     * @return boolean
     * @throws SevereException
     */
    public boolean validateTieredLineNotes(Contract contract) throws SevereException {
        List unAnsweredQuestionsList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                contract.getContractSysId()));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(TierDefinitionBO.class
                        .getName(), "validateTieredLineNotes",
                        referenceValueSet);
        unAnsweredQuestionsList = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        if (null != unAnsweredQuestionsList
                && (unAnsweredQuestionsList.size() != 0)) {
            return false;
        }
        return true;
    }
    
    /**
     * Method to retrieve the notes attached to the uncoded benefit lines
     * 
     * @param contract
     * @return boolean
     * @throws SevereException
     */
    public boolean validateUnansweredTieredQues(Contract contract) throws SevereException {
        List unAnsweredQuestionsList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.ENTITY_SYS_ID, new Integer(
                contract.getContractSysId()));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(TierDefinitionBO.class
                        .getName(), "validateUnansweredTieredQues",
                        referenceValueSet);
        unAnsweredQuestionsList = AdapterUtil.performSearch(
                adapterSearchCriteria).getSearchResults();
        if (null != unAnsweredQuestionsList
                && (unAnsweredQuestionsList.size() != 0)) {
            return false;
        }
        return true;
    }


    /**
     * Method to retrieve the notes attached to the unanswered admin option
     * Question at product level
     * 
     * @param contract
     * @return boolean
     * @throws SevereException
     */
    public boolean validateProductLevelAONotes(Contract contract)
            throws SevereException {
        List unCodedLineNotesList = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put(WebConstants.CONTRACT_SYS_ID, new Integer(
                contract.getContractSysId()));
        SearchCriteria adapterSearchCriteria = AdapterUtil
                .getAdapterSearchCriteria(ContractQuestUniqueReferenceBO.class
                        .getName(),
                        BusinessConstants.IS_UNANSWERED_QUESTION_NOTE,
                        referenceValueSet);
        unCodedLineNotesList = AdapterUtil.performSearch(adapterSearchCriteria)
                .getSearchResults();
        if (null != unCodedLineNotesList && (unCodedLineNotesList.size() != 0)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @param contractBenefitTier
     * @return
     * @throws AdapterException
     * @throws SevereException
     */
    public boolean deleteBenefitTier( BenefitTier contractBenefitTier)
	    throws AdapterException,SevereException {
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
	    HashMap refValSet = new HashMap();
	    refValSet.put("tierSysId",new Integer(contractBenefitTier.getBenefitTierSysId()));
	    refValSet.put("entityType","CONTRACT");        
	    try{
	    	searchCriteria = AdapterUtil.getAdapterSearchCriteria(BenefitTier.class.getName(), "deleteBenefitTier", refValSet);
	    	searchResults = AdapterUtil.performSearch(searchCriteria);
	    }catch(Exception ex){
	    	throw new AdapterException ("Exception occured while adapter call",ex);
	    }
	    return true;   
	}

    /**
	 * Method to update the Benefit tier info
	 * @param tierObject
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws AdapterException
	 */
	public boolean updateBenefitTierDetail(ContractTierBindingObject tierObject, Audit audit, AdapterServicesAccess adapterServicesAccess) throws AdapterException{
		try{
	        AdapterUtil.performUpdate(tierObject, audit.getUser(),adapterServicesAccess);
	    	}catch(Exception e){
	    		throw new AdapterException("Exception occured while adapter call",e);
	    	}
		return true;
	}
    
    public List locateContractMemberSpecIndicator(int dateSegmentId)
            throws SevereException {

        SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();

        refValSet.put("dateSegmentId", new Integer(dateSegmentId));

        searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractAssnQuestionnaireBO.class.getName(),
                "getContractMemberSpecIndicator", refValSet);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        return searchResults.getSearchResults();
    }
    
    /**
     * Calls stored procedure "CNTRCT_ADD_TIER" 
     * @param dateSegmentSysId
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

	public boolean addTierToContract(int dateSegmentSysId, int benefitComponentSysId, 
			int benefitSysId, int tierDefinitionId, int levelId, String criteriaString, 
			String isExistingTier, Audit audit)throws AdapterException,SevereException {
		
		SearchResults searchResults = null;
		SearchCriteria searchCriteria = null;
        HashMap refValSet = new HashMap();
        
        refValSet.put("entitySysId",new Integer(dateSegmentSysId));
        refValSet.put("benefitComponentId",new Integer(benefitComponentSysId));
        refValSet.put("benefitSysId",new Integer(benefitSysId));
        refValSet.put("tierDefId",new Integer(tierDefinitionId));
        refValSet.put("levelSysId",new Integer(levelId));
        refValSet.put("criteriaString",criteriaString);
        refValSet.put("isExistingTier",isExistingTier);
        refValSet.put("lastUser",audit.getUser());
        refValSet.put("lastTMS",audit.getTime());
        
 
        try{
        	searchCriteria = AdapterUtil.getAdapterSearchCriteria(TierDefinitionBO.class.getName(), "addTierToContract", refValSet);
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;   
	}
	
    /**
	 * Method to retrieve the tier information corresponding to a contract datesegment
	 * 
	 * @param dateSegmentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractTierInformation(int dateSegmentSysId, List tierCriteriaVal)
			throws SevereException {
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("dateSegmentSysId", new Integer(dateSegmentSysId));
		refValSet.put("tierCriteriaVal", tierCriteriaVal);
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractTierInformationBO.class.getName(),
				BusinessConstants.RETRIEVE_TIER_INFORMATION, refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);

		return searchResults.getSearchResults();
	}
	
	 /**
     * This method retreives the Benefit Compoenent Rule Types associated with a Contract.
     * @param dateSegSysId
     * @throws AdapterException
     */
    public List getRuleListBC(int dateSegSysId)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(RuleDetailBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getRuleListBCforContract");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("entitySysId",new Integer(dateSegSysId));
    	
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
     * This method retreives the Benefit Rule Types associated with a Contract.
     * @param dateSegSysId
     * @throws AdapterException
     */
	public List getRuleListBenefit(int dateSegSysId)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(RuleDetailBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getRuleListBenefitForContract");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("entitySysId",new Integer(dateSegSysId));
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
	 * This method retrives the list All legacy notes associated with dateSegmentId. 
	 * @param dateSegmentID
	 * @throws AdapterException
	 * @return the count of Legacy notes
	 */
	public int checkLegacyNotesExists(int dateSegmentID) throws AdapterException{
		HashMap dateSegmentMap = new HashMap();
		SearchResults searchResults = null;
		dateSegmentMap.put(BusinessConstants.PRI_ENTITY_ID,new Integer(dateSegmentID));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NotesAttachmentOverrideBO.class.getName(),BusinessConstants.CHECK_LEGACY_NOTE_EXIST, dateSegmentMap);
		try{
		searchResults = AdapterUtil.performSearch(searchCriteria);
		}catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",	e);
		}
		return searchResults.getSearchResultCount();
	}
	
    /**
	 * This method returns the modified datesegments for the latest version
	 * contracts which needs to be send via the contract datafeed
	 * 
	 * @param dateSegment
	 * @return
	 * @throws SevereException
	 */
	public List getModifiedContractDatesesgments(DateSegment dateSegment)
			throws SevereException {
		HashMap map = new HashMap();
		map.put(WebConstants.CONTRACT_SYS_ID, new Integer(dateSegment
				.getContractSysId()));
		map.put(WebConstants.CONTRACT_MODIFICATION_IND, dateSegment
				.getDateSegmentType());
		map.put(WebConstants.DT_SGMNT_MDFD_IND, dateSegment.getIsModified());
		
		return AdapterUtil.performSearch(
				AdapterUtil.getAdapterSearchCriteria(DateSegment.class
						.getName(), WebConstants.GET_BASE_CONTRACT_DATES, map))
				.getSearchResults();
	}	
	
    /**
	 * This function updates the datesegment association table with the flag as T or P for test and production respectively.
	 * 
	 * @param dateSegmentAssociationBO
	 * @param specificInfoAdapterServiceAccess
	 * @return
	 * @throws SevereException
	 */
	public void updateDateSegmentAssnInfo(
			DateSegmentAssociationBO dateSegmentAssociationBO)
			throws SevereException {
		AdapterUtil.performUpdate(dateSegmentAssociationBO,
				dateSegmentAssociationBO.getLastUpdatedUser());
	}

    /**
	 * Adapter method for retrieving the deleted DS for the contract
	 * 
	 * @param contractId
	 * @return SearchResults
	 * @throws SevereException
	 */
	public List getDeletedDateSegments(String contractId, String status)
			throws SevereException {
		SearchResults searchResult = null;
		SearchCriteria adapterSearchCriteria = null;
		HashMap hashMap = new HashMap();
		hashMap.put(WebConstants.CONTRACT_ID, contractId);
		hashMap.put(WebConstants.STATUS_DESC, status);
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				DeletedDSInfoBO.class.getName(),
				WebConstants.RETRIEVE_DELETED_DATESEGMENTS, hashMap);
		searchResult = AdapterUtil.performSearch(adapterSearchCriteria);
		return searchResult.getSearchResults();

	}	
	
	/**
	 * @param deletedDSInfoBO
	 * @throws SevereException
	 */
	public void updateDeletedDateSegmentInfo(DeletedDSInfoBO deletedDSInfoBO,
			Audit audit) throws SevereException {
		AdapterUtil.performUpdate(deletedDSInfoBO, audit.getUser());
	}

	/**
	 * @param deletedDSInfoBO
	 * @param audit
	 * @throws SevereException
	 */
	public void deleteDeletedDateSegmentInfo(DeletedDSInfoBO deletedDSInfoBO,
			Audit audit) throws SevereException {
		AdapterUtil.performRemove(deletedDSInfoBO, audit.getUser());
	}
	
	/**
	 * Adapter method for retrieving the deleted DS for the contract
	 * 
	 * @param contractId
	 * @return SearchResults
	 * @throws SevereException
	 */
	public List getRootDeleteContracts(String status)throws SevereException {
		SearchResults searchResult = null;
		SearchCriteria adapterSearchCriteria = null;
		HashMap hashMap = new HashMap();
		//If the contract is test scheduled, then retrieve all contracts with CNTRCT_DLT_IND as NULL
		if(status.equals("SCHEDULED_FOR_TEST"))
			hashMap.put("testDel", new Integer(1));
		//If the contract is prod scheduled, then retrieve all contracts with CNTRCT_DLT_IND as T		
		else if(status.equals("SCHEDULED_FOR_PRODUCTION"))
			hashMap.put("prodDel", new Integer(2));
		
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				DeletedDSInfoBO.class.getName(),
				WebConstants.RETRIEVE_ROOT_DELETE_CONTRACTS, hashMap);
		searchResult = AdapterUtil.performSearch(adapterSearchCriteria);
		return searchResult.getSearchResults();

	}	
	
	/**
	 * Adapter method for retrieving the deleted DS for the contract
	 * 
	 * @param contractId
	 * @return SearchResults
	 * @throws SevereException
	 */
	public List getRootDeleteScheduledContracts(String contractId, String status)
			throws SevereException {
		SearchResults searchResult = null;
		SearchCriteria adapterSearchCriteria = null;
		HashMap hashMap = new HashMap();
		hashMap.put(WebConstants.CONTRACT_ID, contractId);
		hashMap.put(WebConstants.STATUS_DESC, status);
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				DeletedDSInfoBO.class.getName(),
				WebConstants.RETRIEVE_ROOT_DELETE_SCHEDULED_CONTRACTS, hashMap);
		searchResult = AdapterUtil.performSearch(adapterSearchCriteria);
		
		return searchResult.getSearchResults();

	}
	
    /**
     * @param contractSPSBO
     * @throws SevereException
     */
    public void updateDeletedContractRecords(ContractSPSBO contractSPSBO)
			throws SevereException {
		AdapterUtil.performUpdate(contractSPSBO, contractSPSBO
				.getLastUpdatedUser());
	}
    
    /**
     * Method to delete hard deleted contracts after test feed.
     * @param audit
     * @throws SevereException
     */
    public void deleteHardDeletedCnts(Audit audit)throws SevereException {
    	ContractSPSBO contractSPSBO = new ContractSPSBO();
    	// To avoid the primarykey violation, need to dummy contractID
    	contractSPSBO.setContractId("NODATA");
    	AdapterUtil.performRemove(contractSPSBO,audit.getUser());
    }


	/**
	 * @param dateSegmentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getTierCodeAndValues(int dateSegmentSysId) throws SevereException {
       	ContractSPSBO contractSPSBO = null;
        HashMap referenceValueSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        referenceValueSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
                dateSegmentSysId));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ContractSPSBO.class.getName(),
                "getTierCodeAndValues", referenceValueSet);
        
        List tierCodeAndValues = AdapterUtil
                .performSearch(adapterSearchCriteria).getSearchResults();
        
             return tierCodeAndValues;
	}
	public void updateProductForNewDS(int dateSegmentId, int newProductId)throws SevereException{
		
		HashMap dateSegmentUpdateSet = new HashMap();
        SearchCriteria adapterSearchCriteria = null;
        dateSegmentUpdateSet.put(WebConstants.DATE_SEGMENT_SYS_ID, new Integer(
        		dateSegmentId));
        dateSegmentUpdateSet.put(WebConstants.SOURCE_PRODUCT_ID, new Integer(
        		newProductId));
        adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
                DateSegment.class.getName(),
                "updateNewPdtIdForDS", dateSegmentUpdateSet);
        
 	 SearchResults searchResults= AdapterUtil
				        .performSearch(adapterSearchCriteria);
	}
	public void deleteContractBenefitAffectedSPS(List entityIds, Audit audit)throws SevereException{
		if(null != entityIds && entityIds.size() > 0){
			ContractBenefitAffectedSPSBO affectedSPSBO = new ContractBenefitAffectedSPSBO();
			for(int i = 0;i < entityIds.size(); i++){
				affectedSPSBO.setEntitySysId(((Integer)entityIds.get(i)).intValue());
				AdapterUtil.performRemove(affectedSPSBO, audit.getUser());
			}
		}
	}
	public boolean isLineCodedInDateSegment(int dateSegmentSysID, int benefitComponentSysID, int benefitLineSysID)
	throws SevereException{
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("dateSegmentId", new Integer(dateSegmentSysID));
		refValSet.put("benefitComponentId", new Integer(benefitComponentSysID));
		refValSet.put("benefitLineId", new Integer(benefitLineSysID));
		int maxResultSize = 999999;
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria("com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings",
				"isLineCodedInDateSegment", refValSet,
				maxResultSize);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		List locateResultsList = searchResults.getSearchResults();
		if(locateResultsList!=null && locateResultsList.size()> 0){
			return true;
		}else
			return false;
}
	
	public List getComponents(List components) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("benefitComponent", WPDStringUtil.getAppendedString(components, "~"));
		
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ContractReportBean.class.getName(),
                "getComponents", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        
        return searchResults.getSearchResults();
	}
	
	public List getBenefits(List benefits) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("benefit", WPDStringUtil.getAppendedString(benefits, "~"));
		
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ContractReportBean.class.getName(),
                "getBenefits", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        
        return searchResults.getSearchResults();
	}
	
	public List getContracts(List contracts) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("contractId", WPDStringUtil.getAppendedString(contracts, "~"));
		
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ContractReportBean.class.getName(),
                "getContracts", criteriaMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        
        return searchResults.getSearchResults();
	}
	
	public List getContractReport(ContractReportCriteria criteria) throws SevereException {
		
        SearchResults searchResults = null;
        HashMap hashMap = new HashMap();
        
        if(criteria.getContractId() != null && criteria.getContractId().size() > 0) {
        	hashMap.put("contractId", WPDStringUtil.getAppendedString(criteria.getContractId(),"~"));
        }
        
        if(criteria.getStartDate() != null) {
        	hashMap.put("startDate", criteria.getStartDate() );
        }
        
        if(criteria.getBenefitComponents() != null && criteria.getBenefitComponents().size() > 0) {
        	hashMap.put("benefitComponent", WPDStringUtil.getAppendedString(criteria.getBenefitComponents(),"~"));	
        }
        
        if(criteria.getBenefits() != null && criteria.getBenefits().size() > 0) {
        	 hashMap.put("benefit", WPDStringUtil.getAppendedString(criteria.getBenefits(),"~"));      
        }
       
        if(criteria.isRetrieveHeaderRule())
        	hashMap.put("retrieveHeaderRule", "Y");
        else
        	hashMap.put("retrieveHeaderRule", "N");
        
        if(criteria.isRetrieveBenefitLines())
        	hashMap.put("retrieveLine", "Y");
        else
        	hashMap.put("retrieveLine", "N");
        
        if(criteria.isRetrieveQuestions())
        	hashMap.put("retrieveQuestion", "Y");
        else
        	hashMap.put("retrieveQuestion", "N");
        
        if(criteria.isRetrieveAdminMethods())
        	hashMap.put("retrieveMethod", "Y");
        else
        	hashMap.put("retrieveMethod", "N");
        
        if(criteria.isRetrieveNotes())
        	hashMap.put("retrieveNoteFlag", "Y");
        else
        	hashMap.put("retrieveNoteFlag", "N");
        
        
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
        		ContractReportBean.class.getName(),
                "reportProcedure", hashMap);
        searchResults = AdapterUtil.performSearch(searchCriteria);
        
        List result = searchResults.getSearchResults(); 
        
        return result;		
	}
	
	public ContractStatusBo retrieveContractStatus(String contractId){
		ContractStatusBo contractStatus = new ContractStatusBo();
		contractStatus.setContractId(contractId);
		try{
			ContractStatusBo retrievedContractStatus =  (ContractStatusBo)AdapterUtil.performRetrieve(contractStatus);
			if(null == retrievedContractStatus){
				Logger.logInfo("Contract status not exists in DB for Contract Id : "+contractId);
				return getNewContractStatusBo(contractId, WebConstants.CNTRT_STATUS_EMPTY);
			}
			return retrievedContractStatus;
		}catch(Exception e){
			Logger.logInfo("Contract status not exists in DB for Contract Id : "+contractId);
			return getNewContractStatusBo(contractId, WebConstants.CNTRT_STATUS_EMPTY);
			
		}
		
	}
	
	private ContractStatusBo getNewContractStatusBo(String contractId, String statusCode){
    	ContractStatusBo contractStatusBo = new ContractStatusBo();;
    	contractStatusBo.setContractId(contractId);
    	contractStatusBo.setStatusCode(statusCode);
    	
    	return contractStatusBo;
    }
	
	public void updateContractStatus(ContractStatusBo contractStatusBo, AdapterServicesAccess serviceAccess) throws SevereException {
		
		if(null == contractStatusBo){
			Logger.logInfo("Contract status is null for update");
			return;
		}
    	if(!contractStatusBo.isPersist()){
    		Logger.logInfo("Contract status not ment for persist");
    		return;
    	}
		
		if(null == retrieveContractStatus(contractStatusBo.getContractId()).getCreatedDate()){
			contractStatusBo.setCreatedUserId(contractStatusBo.getLastChangedUserId());
			AdapterUtil.performInsert(contractStatusBo, contractStatusBo.getLastChangedUserId(),serviceAccess);
		}else{
			AdapterUtil.performUpdate(contractStatusBo, contractStatusBo.getLastChangedUserId(),serviceAccess);
		}
		
	}
	
	public void createContractStatus(ContractStatusBo contractStatusBo, AdapterServicesAccess serviceAccess) throws SevereException {
		
		if(null == contractStatusBo){
			Logger.logInfo("Contract status is null for update");
			return;
		}
    	if(!contractStatusBo.isPersist()){
    		Logger.logInfo("Contract status not ment for persist");
    		return;
    	}
		AdapterUtil.performInsert(contractStatusBo, contractStatusBo.getLastChangedUserId(),serviceAccess);
				
	}
	
	
	public void deleteContractStatus(String contractId,String userId){
		try{
			 SearchController controller = new SearchController();
			 BasicSearchCriteria bsc = new BasicSearchCriteria();
			 List basicSearchObjects = new ArrayList();

			 BasicSearchObject searchObject = new BasicSearchObject();
			 searchObject.setType(SearchConstants.CONTRACT);
			 searchObject.setChecked(true);
			 basicSearchObjects.add(searchObject);
			 bsc.setBasicSearchObjects(basicSearchObjects);
			
			 BasicAttribute basicAttribute = new BasicAttribute();
		     basicAttribute.setCriteria(contractId);
		     basicAttribute.setName(SearchConstants.BASIC_SEARCH_IDENTIFIER);
		     bsc.setBasicAttribute(basicAttribute);
			
		     LimitedTo limitedTo = new LimitedTo();
		     limitedTo.setBusinessEntity(new ArrayList());
		     limitedTo.setBusinessGroup(new ArrayList());
		     limitedTo.setLineOfBusiness(new ArrayList());
		     limitedTo.setMarketBusinessUnit(new ArrayList());
		     bsc.setLimitedTo(limitedTo);
			
			 List searchResult = controller.basicSearch(bsc);
			 
			 if(null == searchResult || searchResult.size() == 0){
				 ContractStatusBo contractStatusBo = new ContractStatusBo();;
			     contractStatusBo.setContractId(contractId);
			     AdapterUtil.performRemove(contractStatusBo, userId, AdapterUtil.getAdapterService());	
			    
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Method for getting the details of UM/Denail and Exclusion for contract
	 * @param dateSegmentId
	 * @param gnrtdRuleIDCondition
	 * @return A list containing the rule details 
	 * @throws SevereException
	 */
	
	public List getUmDenailAndExclusionDetailsForContract(int dateSegmentId,List gnrtdRuleIDCondition) throws SevereException{
		
		List tempResultList = null;
		
		HashMap hashmap = new HashMap();//Hash map normal rule locate criteria
		hashmap.put(WebConstants.DT_SGMNT_SYS_ID, new Integer(dateSegmentId));
		hashmap.put(WebConstants.EXTRACT_RULE_TYPE,gnrtdRuleIDCondition );
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.CONTRACT_EXTRACT_RULE, hashmap
		);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		tempResultList = searchResults.getSearchResults(); 
		
		return tempResultList;
	}
	/**
	 * Method for getting the details of header rule for contract
	 * @param dateSegmentId
	 * @return A list containing rule details
	 * @throws SevereException
	 */
	
	public List getHeaderRuleDetailsForContract(int dateSegmentId) throws SevereException{
		
		List tempResultList =null;
		
		HashMap hashmap = new HashMap();//Hash map normal rule locate criteria
		hashmap.put(WebConstants.DT_SGMNT_SYS_ID, new Integer(dateSegmentId));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.CONTRACT_EXTRACT_HEADER_RULE, hashmap
		);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		tempResultList = searchResults.getSearchResults(); 
		
		return tempResultList;
				
	}
	/**
	 * Method for getting the details of group rules for contract
	 * @param groupRuleIds List of group rule ids
	 * @return A list containing the details of group rules of contract
	 * @throws SevereException
	 */
	public List getGroupRuleDetailsForContract(List groupRuleIds) throws SevereException{
		
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
	/**
	 * Method for getting the details of individual rule details for UM/Denail and Exclusion
	 * @param ruleId Rule Id
	 * @return A list containing the details of individual rule details
	 * @throws SevereException
	 */
	public List getIndividualRuleDetails(String ruleId) throws SevereException{
		
		List ruleList = null;
		
		HashMap hashmap = new HashMap();
		hashmap.put(WebConstants.IND_RULE_ID,ruleId);
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.INDIVIDUAL_RULE_EXTRACT_QUERY, hashmap
		);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		ruleList = searchResults.getSearchResults(); 
		
		return ruleList;
	
	}
	/**
	 * Method for getting the details of individual rule details for benefit component
	 * @param ruleId Rule ID
	 * @return A list containing the details individual rule details for benefit component
	 * @throws SevereException
	 */
	public List getIndividualRuleDetailsHeaderBnftCmpnt(String ruleId) throws SevereException{
		List ruleList = null;
		HashMap hashmap = new HashMap();
		hashmap.put(WebConstants.IND_RULE_ID,ruleId);
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.INDIVIDUAL_HEADER_RULE_EXTRACT_QUERY_BC, hashmap
		);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		ruleList = searchResults.getSearchResults();
		
		return ruleList;		
	}
	/**
	 * Method for getting the details of individual rule details for benefit
	 * @param ruleId Rule id
	 * @param benefitComponentId Benefit component Id
	 * @return A list containg the header rule details
	 * @throws SevereException
	 */
	public List getIndividualRuleDetailsHeaderBnft(String ruleId,String benefitComponentId) throws SevereException{
		List ruleList = null;
		HashMap hashmap = new HashMap();
		hashmap.put(WebConstants.IND_RULE_ID,ruleId);
		hashmap.put(WebConstants.BNFT_CMPNT_ID, benefitComponentId);
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_REPORT_RESULT,
				BusinessConstants.INDIVIDUAL_HEADER_RULE_EXTRACT_QUERY_BNFT, hashmap
		);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		ruleList = searchResults.getSearchResults(); 
		
		return ruleList;
		
	}
	public List getIMAGEReadyBusinessDomains() throws SevereException{
		List domainList = null;
		HashMap hashmap = new HashMap();
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.IMAGE_READY_DOMAIN,
				"getIMAGEReadyBusinessDomains", hashmap
		);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		domainList = searchResults.getSearchResults();
		return domainList;
	}
}