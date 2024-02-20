/*
 * StandardBenefitBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionLocateCriteria;
import com.wellpoint.wpd.business.benefitdefinition.adapter.BenefitDefinitionAdapterManager;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.adapter.BenefitlevelAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelTermLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLineLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitPopUpLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenfitLevelLineDeleteLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.DataTypeLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.lookup.adapter.LookupAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.AddQuestionAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.adapter.AdministrationOptionAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.adapter.BenefitAdministrationAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.adapter.LookupAdminOptionAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdminLevelLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AssociateAdminOptionToBenefitLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitLevelListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitTierDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.LookupAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.MandateListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.NotesAttachmentForBenefitLineLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.PossibleAnswersLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.SelectedQuestionListLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.SelectedQuestionaireLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitDeleteLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitNotesLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitSourceDestinationLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitViewAllLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.TierDefinitionLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locateresult.StandardBenefitLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefit.bo.TermSpsBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelPopUpBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitWrapperBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.NotesBOForDefId;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBenefitBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionAssociationBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTierDefinitionAssnBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleBO;
import com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.TermQualifierPVABO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitBusinessObjectBuilder {

    
	/**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieveLatestVersion(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param standardBenefitBO
     * @return
     * @throws WPDException
     */
    public BusinessObject retrieveLatestVersion(
            StandardBenefitBO standardBenefitBO) throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering retrieveLatestVersion(): Standard Benefit");
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        List domainList = standardBenefitBO.getBusinessDomains();
        List lob = BusinessUtil.getLobList(domainList);
        List businessEntity = BusinessUtil.getbusinessEntityList(domainList);
        List businessGroup = BusinessUtil.getBusinessGroupList(domainList);
        List marketBusinessUnit = BusinessUtil.getMarketBusinessUnitList(domainList);
        standardBenefitBO = standardBenefitAdapterManager
                .retrieveStandardBenefitLatestVersion(standardBenefitBO
                        .getBenefitIdentifier(), lob, businessEntity,
                        businessGroup, marketBusinessUnit);
       
        List domainList_new = DomainUtil.retrieveAssociatedDomains(
        		BusinessConstants.STD_BENEFIT, standardBenefitBO.getParentSystemId());
        standardBenefitBO.setBusinessDomains(domainList_new);

        UniverseBO universeBO = new UniverseBO();
        universeBO.setStandardBenefitKey(standardBenefitBO
                .getStandardBenefitKey());
        List searchList = standardBenefitAdapterManager
                .searchUniverse(universeBO);
        if (null != searchList) {
            if (searchList.size() > 0) {
                standardBenefitBO = this.getUniverseCode(searchList,
                        standardBenefitBO);
            }
        }
        //Code Enhancement:::::BO for performing the search for Rule Id in the
        // new table
        AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
        assignedRuleIdBO.setEntitySystemId(standardBenefitBO
                .getStandardBenefitKey());
        assignedRuleIdBO.setEntityType(BusinessConstants.STANDARDBENEFIT);
        RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
        List refList = refdataAdapterManager.searchRuleId(assignedRuleIdBO);
        List refNameList = new ArrayList();
        List refIdList = new ArrayList();
        if (null != refList && 0 != refList.size()) {
            for (int i = 0; i < refList.size(); i++) {
                assignedRuleIdBO = (AssignedRuleIdBO) refList.get(i);
                refNameList.add(assignedRuleIdBO.getCodeDescText());
                refIdList.add(assignedRuleIdBO.getPrimaryCode());
            }
        }
        standardBenefitBO.setRuleTypeList(refIdList);
        
        //End of Code Enhancement
        StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
        standardBenefitDatatypeBO.setStandardBenefitKey(standardBenefitBO
                .getStandardBenefitKey());
        List dataTypeResultList = standardBenefitAdapterManager
                .searchDatatype(standardBenefitDatatypeBO);
        standardBenefitBO.setDataTypeList(this
                .getDataTypeCode(dataTypeResultList));
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning retrieveLatestVersion(): StandardBenefitBO");
        return standardBenefitBO;
    }


    /**
     * 
     * @param domainItems
     * @return
     * @throws WPDException
     */
    public List getDomainCode(List domainItems) {
        List domainCodes = new ArrayList();
        if (null != domainItems) {
            Iterator itr = domainItems.iterator();
            while (itr.hasNext()) {
                DomainItem domainItem = (DomainItem) itr.next();
                domainCodes.add(domainItem.getItemId());
            }
        }
        return domainCodes;
    }


    /**
     * 
     * @param dataTypeItems
     * @return
     * @throws WPDException
     */
    public List getDataTypeCode(List dataTypeItems) {
        List dataTypeCodes = new ArrayList();
        if (null != dataTypeItems) {
            Iterator itr = dataTypeItems.iterator();
            while (itr.hasNext()) {
                StandardBenefitDatatypeBO standardBenefitDataTypeItem = (StandardBenefitDatatypeBO) itr
                        .next();
                dataTypeCodes.add(standardBenefitDataTypeItem
                        .getSelectedItemCode());
            }
        }
        return dataTypeCodes;
    }
    /**
     * retrieves the BO with the latest version number
     * 
     * @param standardBenefitBO
     * @return 
     * @throws SevereException
     */
    public int retrieveLatestVersionNumber(StandardBenefitBO standardBenefitBO)
            throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        List domainList = standardBenefitBO.getBusinessDomains();
        List lob = BusinessUtil.getLobList(domainList);
        List businessEntity = BusinessUtil.getbusinessEntityList(domainList);
        List businessGroup = BusinessUtil.getBusinessGroupList(domainList);
        List marketBusinessUnit = BusinessUtil.getMarketBusinessUnitList(domainList);
        standardBenefitBO = standardBenefitAdapterManager
                .retrieveStandardBenefitLatestVersionNumber(standardBenefitBO
                        .getBenefitIdentifier(), lob, businessEntity,
                        businessGroup, marketBusinessUnit);
        if (null != standardBenefitBO)
            return standardBenefitBO.getVersion();
        else
            return 0;
    }


    /**
     * Function to persist a StandardBenefitBO. Flag is used to differentiate
     * whether an insertion or updation is done.
     * 
     * @param businessObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException,
     *             AdapterException
     */
    public boolean persist(StandardBenefitBO businessObject, Audit audit,
            boolean insertFlag) throws SevereException, AdapterException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit");
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();

        
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
                .getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
        try {
            standardBenefitAdapterServiceAccess.beginTransaction(); 
            AdapterUtil.logBeginTranstn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");

            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) businessObject;
            standardBenefitBO.setCreatedUser(audit.getUser());
            standardBenefitBO.setCreatedTimestamp(audit.getTime());
            standardBenefitBO.setLastUpdatedUser(audit.getUser());
            standardBenefitBO.setLastUpdatedTimestamp(audit.getTime());

            if (insertFlag) {

                SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
                standardBenefitBO.setStandardBenefitKey(sequenceAdapterManager
                        .getNextStandardBenefitSequence());
                
            if (!BusinessConstants.CHECKED_OUT.equals(standardBenefitBO.getStatus())) {
                    standardBenefitBO.setParentSystemId(standardBenefitBO
                            .getStandardBenefitKey());
                }
                if (standardBenefitAdapterManager.createStandardBenefit(
                        standardBenefitBO, audit, insertFlag,
                        standardBenefitAdapterServiceAccess)) {

                }
            } else {
                if (BusinessConstants.MANDATE.equals(standardBenefitBO.getBenefitType())) {
                    String mandateType = standardBenefitBO.getMandateType();
                    StandardBenefitAdapterManager benefitAdapterManager = new StandardBenefitAdapterManager();
                    StandardBenefitBO benefitBOTemp = new StandardBenefitBO();
                    benefitBOTemp.setStandardBenefitKey(standardBenefitBO
                            .getStandardBenefitKey());
                    benefitBOTemp = (StandardBenefitBO) benefitAdapterManager
                            .retrieveSB(benefitBOTemp);
                    //standardBenefitBO.setMandateType(mandateType);
                    if(null != benefitBOTemp.getMandateType()){
                        if (!benefitBOTemp.getMandateType().equals(mandateType)) {
                            NonAdjBenefitMandateAdapterManager adapterManager = new NonAdjBenefitMandateAdapterManager();
                            BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
                            benefitMandateBO
                                    .setBenefitSystemId(standardBenefitBO
                                            .getStandardBenefitKey());
                            benefitMandateBO = (BenefitMandateBO) adapterManager
                                    .retrieve(benefitMandateBO);

                            StateLocateCriteria criteria = new StateLocateCriteria();
                            criteria.setBenefitMandateId(benefitMandateBO
                                    .getBenefitMandateId());
                            LocateResults locateResults = adapterManager
                                    .locateStateObject(criteria);
                            List list = locateResults.getLocateResults();
                            if (null != list) {
                                Iterator iterator = list.iterator();
                                while (iterator.hasNext()) {
                                    StateBO stateBO = (StateBO) iterator.next();
                                    adapterManager.deleteStateObject(stateBO,
                                            audit,standardBenefitAdapterServiceAccess); 
                                }
                            }
                            if (BusinessConstants.MANDATE_TYPE_FED.equals(mandateType)) {
                                benefitMandateBO
                                        .setBenefitSystemId(standardBenefitBO
                                                .getStandardBenefitKey());
                                benefitMandateBO = (BenefitMandateBO) adapterManager
                                	.retrieve(benefitMandateBO);
                                if(benefitMandateBO.getBenefitMandateId() != 0){
                                    StateBO stateBO = new StateBO();
                                    stateBO.setBenefitMandateId(benefitMandateBO.getBenefitMandateId());
                                    stateBO.setBenefitStateCode(BusinessConstants.BENEFIT_STATE_CODE_ALL);
                                    stateBO.setFlag(BusinessConstants.STATE_FLAG_D);
                                    adapterManager.createStateObject(stateBO,audit,insertFlag,standardBenefitAdapterServiceAccess);
                                }
                            }
                        } 
                    }
                    standardBenefitBO.setMandateType(mandateType);
                }

                standardBenefitAdapterManager.updateStandardBenefit(
                        standardBenefitBO, audit, insertFlag,
                        standardBenefitAdapterServiceAccess);
                // included if condition for improving the performance
                if(standardBenefitBO.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_BUILDING) || 
                		standardBenefitBO.getStatus().equalsIgnoreCase(BusinessConstants.CHECKED_OUT)){
	                this.deleteUniverse(standardBenefitBO,
	                        standardBenefitAdapterServiceAccess, audit);
                }
                //Deleting all the existing RuleIds of the SB

            }
            // persist domain only if version is 0 or 1
            if (standardBenefitBO.getVersion() <= 1) {
                DomainDetail businessDomain = getBusinessDomainValues(standardBenefitBO);
                DomainUtil.persistAssociatedDomains(businessDomain,
                        standardBenefitAdapterServiceAccess);
                // When the domain is modified for a benefit, all the questionnaires
                //which are invalid for this changed domain are removed.
               List invalidQuestionnairesList = 
               	standardBenefitAdapterManager.getQuestionnairesWithInvalidDomains(standardBenefitBO);
               if(null != invalidQuestionnairesList && !invalidQuestionnairesList.isEmpty()){
               		for(int i = 0; i < invalidQuestionnairesList.size(); i++){
               			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO)
						invalidQuestionnairesList.get(i);
               			standardBenefitAdapterManager.delete(benefitQuestionnaireAssnBO,audit,
               					standardBenefitAdapterServiceAccess);
               		}
               }
               //When the domain is modified for a benefit, all the questionnaires
               //which are valid for this changed domain are removed.
               List validQuestionnairesList = standardBenefitAdapterManager.getQuestionnairesWithValidDomains
			   (standardBenefitBO);
               if(null != validQuestionnairesList && !validQuestionnairesList.isEmpty()){
           		for(int i = 0; i < validQuestionnairesList.size(); i++){
           			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO)
						validQuestionnairesList.get(i);
           			standardBenefitAdapterManager.persistQuestionnaireForBenefit(benefitQuestionnaireAssnBO,audit,
           					true,standardBenefitAdapterServiceAccess);
           		}
           }
            }
            // included if condition for improving the performance
            if(standardBenefitBO.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_BUILDING) || 
            		standardBenefitBO.getStatus().equalsIgnoreCase(BusinessConstants.CHECKED_OUT)){
	            this.createUniverse(standardBenefitBO.getTermList(), BusinessConstants.TERM,
	                    standardBenefitBO.getStandardBenefitKey(), audit,
	                    standardBenefitAdapterServiceAccess);
	
	            this.createUniverse(standardBenefitBO.getQualifierList(),
	                    BusinessConstants.QUALIFIER, standardBenefitBO.getStandardBenefitKey(),
	                    audit, standardBenefitAdapterServiceAccess);
	            this.createUniverse(standardBenefitBO.getPVAList(),
	            		BusinessConstants.PROVIDER_ARRANGEMENT, standardBenefitBO
	                            .getStandardBenefitKey(), audit,
	                    standardBenefitAdapterServiceAccess);
	            this.createUniverse(standardBenefitBO.getDataTypeList(),
	            		BusinessConstants.DATA_TYPE, standardBenefitBO.getStandardBenefitKey(),
	                    audit, standardBenefitAdapterServiceAccess);
	
	            this.createRuleId(standardBenefitBO.getRuleTypeList(), BusinessConstants.STANDARDBENEFIT,
                    standardBenefitBO.getStandardBenefitKey(), audit,
                    standardBenefitAdapterServiceAccess);
            }
           
            standardBenefitBO = (StandardBenefitBO) standardBenefitAdapterManager
                    .retrieveSB(standardBenefitBO);

            List domainList = DomainUtil.retrieveAssociatedDomains(
                    BusinessConstants.STDBENEFIT , standardBenefitBO.getParentSystemId());
            standardBenefitBO.setBusinessDomains(domainList);
            UniverseBO universeBO = new UniverseBO();
            universeBO.setStandardBenefitKey(standardBenefitBO
                    .getStandardBenefitKey());
            List searchList = standardBenefitAdapterManager
                    .searchUniverse(universeBO);
            if (null != searchList) {
                if (searchList.size() > 0) {
                    standardBenefitBO = this.prepareUniverseDisplayList(
                            searchList, standardBenefitBO);
                }
            }

            //Code Enhancement:::::BO for performing the search for Rule Id in
            // the new table
            AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
            assignedRuleIdBO.setEntitySystemId(standardBenefitBO
                    .getStandardBenefitKey());
            assignedRuleIdBO.setEntityType(BusinessConstants.STANDARDBENEFIT);
            RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
            List searchRuleId = refdataAdapterManager
                    .searchRuleId(assignedRuleIdBO);
            if (null != searchRuleId) {
                if (searchRuleId.size() > 0) {
                    standardBenefitBO = this.prepareRuleIdBOList(searchRuleId,
                            standardBenefitBO);
                }
            }
            //End of Code Enhancement

            StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
            standardBenefitDatatypeBO.setStandardBenefitKey(standardBenefitBO
                    .getStandardBenefitKey());
            List dataTypeResultList = standardBenefitAdapterManager
                    .searchDatatype(standardBenefitDatatypeBO);
            standardBenefitBO.setDataTypeList(dataTypeResultList);
           
            standardBenefitAdapterServiceAccess.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
        } catch (Exception ex) {
            standardBenefitAdapterServiceAccess.abortTransaction();
            AdapterUtil.logAbortTxn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for StandardBenefitBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");
        return true;
    }

    /**
     * Method for checking if Benefit has name same as any benefit components.
     * 
     * @param standardBenefitBO
     * @return 
     * @throws SevereException
     */
    public List checkUniqueBenefitName(
    		StandardBenefitBO standardBenefitBO) throws SevereException {
    	StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
        return adapterManager
                .checkUniqueBenefitName(standardBenefitBO);
    }
    /**
     * 
     * @param srcStandardBenefitBO,standardBenefitBO
     * @return true
     * @throws SevereException
     */
    public boolean changeIdentity(StandardBenefitBO srcStandardBenefitBO,
            StandardBenefitBO standardBenefitBO, Audit audit)
            throws SevereException, AdapterException {
        persist(standardBenefitBO, audit, false);
        return true;

    }


    /**
     * Method to get the business domain values from standardBenefitBO
     * 
     * @param standardBenefitBO
     * @return DomainDetail
     */
    private DomainDetail getBusinessDomainValues(
            StandardBenefitBO standardBenefitBO) {
        DomainDetail businessDomain = new DomainDetail();
        businessDomain.setEntityType(BusinessConstants.STDBENEFIT);
        businessDomain.setEntityName(standardBenefitBO.getBenefitIdentifier());
        businessDomain.setEntityParentId(standardBenefitBO.getParentSystemId());
        businessDomain.setDomainList(standardBenefitBO.getBusinessDomains());
        businessDomain.setLastUpdatedUser(standardBenefitBO
                .getLastUpdatedUser());
        businessDomain.setLastUpdatedTimeStamp(standardBenefitBO
                .getLastUpdatedTimestamp());
        return businessDomain;
    }


    /**
     * Function to insert the rule ID values
     * 
     * @param universeList
     * @param universeType
     * @param standardBenefitKey
     */
    private void createRuleId(List universeList, String universeType,
            int standardBenefitKey, Audit audit,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException , AdapterException {
        AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
        RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
        if (null != universeList) {

            for (int i = 0; i < universeList.size(); i++) {

                if (universeList.get(i) instanceof AssignedRuleIdBO) {
                    AssignedRuleIdBO assignedRuleIdBO1;
                    assignedRuleIdBO1 = (AssignedRuleIdBO) universeList.get(i);
                    assignedRuleIdBO.setPrimaryCode(assignedRuleIdBO1
                            .getPrimaryCode());
                } else {
                    assignedRuleIdBO.setPrimaryCode(universeList.get(i)
                            .toString());
                }
                assignedRuleIdBO.setEntitySystemId(standardBenefitKey);
                assignedRuleIdBO.setLastChangesTimeStamp(audit.getTime());
                assignedRuleIdBO.setCreatedTimeStamp(audit.getTime());
                assignedRuleIdBO.setLastchangesUser(audit.getUser());
                assignedRuleIdBO.setCreatedUserId(audit.getUser());
                assignedRuleIdBO.setEntityType(universeType);
                try{            
                	refdataAdapterManager.createRuleId(assignedRuleIdBO,
                            standardBenefitAdapterServiceAccess, audit);
                 
                }catch(Exception e){
                 
                    List errorParams = new ArrayList(2);
                    String obj = e.getClass().getName();
                    errorParams.add(obj);
                    errorParams.add(obj.getClass().getName());
                    throw new SevereException(
                            "Persist failed for StandardBenefitBO persist in StandardBenefitBuisnessObjectBuilder",
                            errorParams, e);
  
                }
            }

        }
    }

    /**
     * Function to get the values from the universe list and set them to BO
     * 
     * @param searchList
     * @param standardBenefitBO
     */
    private StandardBenefitBO getUniverseCode(List searchList,
            StandardBenefitBO standardBenefitBO) {
        Iterator searchListIterator = searchList.iterator();
        List termList = new ArrayList();
        List qualifierList = new ArrayList();
        List pvaList = new ArrayList();

        while (searchListIterator.hasNext()) {
            UniverseBO universeBO = (UniverseBO) searchListIterator.next();
           if (BusinessConstants.TERM.equals(universeBO.getCatDescText())) {
                termList.add(universeBO.getUniverseCode());
            } else if (BusinessConstants.QUALIFIER.equals(universeBO.getCatDescText())) {
                qualifierList.add(universeBO.getUniverseCode());
            } else if (BusinessConstants.PROVIDER_ARRANGEMENT.equals(universeBO
                    .getCatDescText())) {
                pvaList.add(universeBO.getUniverseCode());
            }
        }
        standardBenefitBO.setTermList(termList);
        standardBenefitBO.setQualifierList(qualifierList);
        standardBenefitBO.setPVAList(pvaList);
        return standardBenefitBO;
    }

    /**
     * Function to get the values from the universe list and set them to BO
     * 
     * @param searchRuleId
     * @param standardBenefitBO
     */
    public StandardBenefitBO prepareRuleIdBOList(List searchRuleId,
            StandardBenefitBO standardBenefitBO) {
        Iterator searchListIterator = searchRuleId.iterator();
        List ruleIdList = new ArrayList();

        while (searchListIterator.hasNext()) {

            AssignedRuleIdBO assignedRuleIdBO = (AssignedRuleIdBO) searchListIterator
                    .next();
            ruleIdList.add(assignedRuleIdBO);

        }
        standardBenefitBO.setRuleTypeList(ruleIdList);
        return standardBenefitBO;
    }


    /**
     * Function to get the values from the universe list and set them to BO
     * 
     * @param searchList
     * @param standardBenefitBO
     */
    private StandardBenefitBO prepareUniverseDisplayList(List searchList,
            StandardBenefitBO standardBenefitBO) {
        Iterator searchListIterator = searchList.iterator();
        List termList = new ArrayList();
        List qualifierList = new ArrayList();
        List pvaList = new ArrayList();
        List ruleList = new ArrayList();
        while (searchListIterator.hasNext()) {
            UniverseBO universeBO = (UniverseBO) searchListIterator.next();
            if (BusinessConstants.TERM.equalsIgnoreCase(universeBO.getCatDescText())) {
                termList.add(universeBO);
            } else if (BusinessConstants.QUALIFIER.equalsIgnoreCase(universeBO.getCatDescText())) {
                qualifierList.add(universeBO);
            } else if (BusinessConstants.PROVIDER_ARRANGEMENT.equalsIgnoreCase(universeBO
                    .getCatDescText())) {
                pvaList.add(universeBO);
            } else if (BusinessConstants.REFERENCE.equalsIgnoreCase(universeBO.getCatDescText())) {
                ruleList.add(universeBO);
            }
        }
        standardBenefitBO.setTermList(termList);
        standardBenefitBO.setQualifierList(qualifierList);
        standardBenefitBO.setPVAList(pvaList);
        standardBenefitBO.setRuleTypeList(ruleList);
        return standardBenefitBO;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param benefitDefinitionBO
     * @param mainObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean persist(BenefitDefinitionBO benefitDefinitionBO,
            StandardBenefitBO mainObject, Audit audit, boolean insertFlag)
           throws SevereException, AdapterException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        try {
	        if (insertFlag) {
	            benefitDefinitionBO
	                    .setBenefitDefinitionMasterKey(sequenceAdapterManager
	                            .getNextBenefitDefinitionSequence());
	            benefitDefinitionAdapterManager.createBenefitDefinitionObject(
	                    benefitDefinitionBO, audit, insertFlag);
	        } else {
	            benefitDefinitionAdapterManager.updateBenefitDefinitionObject(
	                    benefitDefinitionBO, audit, insertFlag);
	        }
        }catch (Exception ex) {
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitDefinitionBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
		} 
        return true;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param subObject
     * @param mainObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws AdapterException
     * @throws WPDException
     */
    public boolean persist(BenefitAdministrationBO subObject,
            StandardBenefitBO mainObject, Audit audit, boolean insertFlag)
            throws SevereException, AdapterException {
        BenefitAdministrationAdapterManager benefitAdministrationAdapterManager = new BenefitAdministrationAdapterManager();
        boolean createSuccess;
        BenefitAdministrationBO benefitAdministrationBO = (BenefitAdministrationBO) subObject;
        try {
	        if (insertFlag) {
	            SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
	            benefitAdministrationBO
	                    .setBenefitAdministrationKey(sequenceAdapterManager
	                            .getNextBenefitAdministrationSequence());
	            benefitAdministrationBO.setCreatedUser(audit.getUser());
	            benefitAdministrationBO.setCreatedTimestamp(audit.getTime());
	            benefitAdministrationBO.setLastUpdatedUser(audit.getUser());
	            benefitAdministrationBO.setLastUpdatedTimestamp(audit.getTime());
	            createSuccess = benefitAdministrationAdapterManager
	                    .createBenefitAdministrationObject(benefitAdministrationBO,
	                            audit, insertFlag);
	        } else {
	            benefitAdministrationBO.setLastUpdatedUser(audit.getUser());
	            benefitAdministrationBO.setLastUpdatedTimestamp(audit.getTime());
	            createSuccess = benefitAdministrationAdapterManager
	                    .updateBenefitAdministrationObject(benefitAdministrationBO,
	                            audit, insertFlag);
	        }
        }catch (Exception ex) {
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitAdministrationBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
		} 
        return true;
    }


    /**
     * Builder for NonAdjBenefitMandate save
     * 
     * @param benefitMandateBO
     * @param standardBenefitBO
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException
     *             Setting the benefitMandateId to BenefitMandateBO Passing the
     *             benefitMandateBO,audit,insertFlag to
     *             NonAdjBenefitMandateAdapterManager Insert flag determines
     *             which function (save or update) to takes place
     */

    public boolean persist(BenefitMandateBO benefitMandateBO,
            StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
	    Logger
	    .logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Benefit Mandate");
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        .getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	standardBenefitAdapterServiceAccess.beginTransaction(); 
        	AdapterUtil.logBeginTranstn(transactionId,this, "persist(BenefitMandateBO benefitMandateBO, StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
        if (insertFlag) {
            int nextSequenceNuber = sequenceAdapterManager
                    .getNextNonAdjBenefitMandateSequence();
            benefitMandateBO.setBenefitMandateId(nextSequenceNuber);
            nonAdjBenefitMandateAdapterManager.createBenefitMandateObject(
                    benefitMandateBO, audit, insertFlag,standardBenefitAdapterServiceAccess); 

            List stateList = (List) benefitMandateBO.getBenefitStateCodeList();
            if(null != stateList && !stateList.isEmpty() ){
	            Iterator stateIterator = stateList.iterator();
	            while (stateIterator.hasNext()) {
	                StateBO stateBO = (StateBO) stateIterator.next();
	                stateBO.setBenefitMandateId(benefitMandateBO
	                        .getBenefitMandateId());
	                nonAdjBenefitMandateAdapterManager.createStateObject(stateBO,
	                        audit, insertFlag,standardBenefitAdapterServiceAccess); 
	            }
            }
            List citationList = (List) benefitMandateBO.getCitationNumberList();
            if(null != citationList && !citationList.isEmpty()){
            	Iterator iterator = citationList.iterator();
	            while (iterator.hasNext()) {
	                CitationNumberBO citationNumberBO = new CitationNumberBO();
	                citationNumberBO.setBenefitMandateId(benefitMandateBO
	                        .getBenefitMandateId());
	                citationNumberBO.setCitationNumber((String) iterator.next());
	                nonAdjBenefitMandateAdapterManager.createCitationList(
	                        citationNumberBO, audit, insertFlag,standardBenefitAdapterServiceAccess);
	            }
            }

        } else {
            List citationList = (List) benefitMandateBO.getCitationNumberList();
            List stateList = (List) benefitMandateBO.getBenefitStateCodeList();


            nonAdjBenefitMandateAdapterManager.updateBenefitMandateObject(
                    benefitMandateBO, audit, insertFlag,standardBenefitAdapterServiceAccess);
            updateStateCode(stateList, benefitMandateBO, audit, insertFlag,standardBenefitAdapterServiceAccess);
            updateCitationNumber(citationList, benefitMandateBO, audit,
                    insertFlag,standardBenefitAdapterServiceAccess);
        }

    	standardBenefitAdapterServiceAccess.endTransaction();
    	AdapterUtil.logTheEndTransaction(transactionId,this, "persist(BenefitMandateBO benefitMandateBO, StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
        }catch (Exception ex) {
        	standardBenefitAdapterServiceAccess.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId,this, "persist(BenefitMandateBO benefitMandateBO, StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");

            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
		}
        Logger
        	.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Benefit Mandate");
        return true;
    }


    /**
     * 
     * @param stateList
     * @param benefitMandateBO
     * @param adapterServicesAccess
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException
     *             updating the statecode list
     *             NonAdjBenefitMandateAdapterManager Insert flag determines
     *             which function (save or update) to takes place
     */

    private void updateStateCode(List stateList,
            BenefitMandateBO benefitMandateBO, Audit audit, boolean insertFlag,AdapterServicesAccess adapterServicesAccess)
            throws SevereException,AdapterException {
    	Iterator stateIterator = null ;
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        StateLocateCriteria locateCriteria = new StateLocateCriteria();
        locateCriteria.setBenefitMandateId(benefitMandateBO
                .getBenefitMandateId());
        LocateResults locateResults = locate(locateCriteria);
	        List stateCodeList = locateResults.getLocateResults();
	        
	        if(null != stateCodeList && !stateCodeList.isEmpty()){ 
		         stateIterator = stateCodeList.iterator();
		        while (stateIterator.hasNext()) {
		            StateBO stateBO = (StateBO) stateIterator.next();
		            stateBO.setBenefitMandateId(benefitMandateBO.getBenefitMandateId());
		            nonAdjBenefitMandateAdapterManager
		                    .deleteStateObject(stateBO, audit,adapterServicesAccess);
		        }
	        }
        if(null != stateList && !stateList.isEmpty()){
	        stateIterator = stateList.iterator();
	        while (stateIterator.hasNext()) {
	            StateBO stateBO = (StateBO) stateIterator.next();
	            stateBO.setBenefitMandateId(benefitMandateBO.getBenefitMandateId());
	            try{
	            	nonAdjBenefitMandateAdapterManager.createStateObject(stateBO,
	                        audit, insertFlag ,adapterServicesAccess);
	             
	            }catch(Exception e){
	            	List errorParams = new ArrayList(2);
	                String obj = e.getClass().getName();
	                errorParams.add(obj);
	                errorParams.add(obj.getClass().getName());
	                throw new SevereException(
	                        "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
	                        errorParams, e);

	            }
	        }
        }
    }


    /**
     * Builder for NonAdjBenefitMandate save
     * 
     * @param benefitMandateBO
     * @param citationList
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException
     *             updating the citationNumberlist
     *             NonAdjBenefitMandateAdapterManager Insert flag determines
     *             which function (save or update) to takes place
     */
    private void updateCitationNumber(List citationList,
            BenefitMandateBO benefitMandateBO, Audit audit, boolean insertFlag, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
        benefitMandateLocateCriteria.setBenefitMandateId(benefitMandateBO
                .getBenefitMandateId());
        LocateResults locateResults = nonAdjBenefitMandateAdapterManager
                .locateCitationNumber(benefitMandateLocateCriteria);
        List list = locateResults.getLocateResults();
        try{
	        if(null != list && !list.isEmpty()){
		        Iterator iterator = list.iterator();

		        while (iterator.hasNext()) {
		            CitationNumberBO citationNumberBO = (CitationNumberBO) iterator
		                    .next();
		            citationNumberBO.setBenefitMandateId(benefitMandateBO
		                    .getBenefitMandateId());
		            nonAdjBenefitMandateAdapterManager.deleteCitationNumber(
		                    citationNumberBO, audit,adapterServicesAccess);
		        }
	        }
	        if(null != citationList && !citationList.isEmpty()){
		        Iterator iterator2 = citationList.iterator();
		        while (iterator2.hasNext()) {
		            String citation = (String) iterator2.next();
		            CitationNumberBO citationNumberBO = new CitationNumberBO();
		            citationNumberBO.setCitationNumber(citation);
		            citationNumberBO.setBenefitMandateId(benefitMandateBO
		                    .getBenefitMandateId());
		             nonAdjBenefitMandateAdapterManager.createCitationList(
			                    citationNumberBO, audit, insertFlag, adapterServicesAccess);
		        }
	        }
        }catch(Exception e){
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, e);
        }
     }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitDefinitionLocateCriteria locateCriteria,
            User user) throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        return benefitDefinitionAdapterManager.locate(locateCriteria);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            BenefitAdministrationLocateCriteria locateCriteria, User user)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering locate(): Benefit Administration");
        BenefitAdministrationAdapterManager benefitAdministrationAdapterManager = new BenefitAdministrationAdapterManager();
        return benefitAdministrationAdapterManager.locate(locateCriteria);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitMandateLocateCriteria locateCriteria)
            throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try{
        return nonAdjBenefitMandateAdapterManager
                .locateCitationNumber(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					null, ex);
		}
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(StateLocateCriteria locateCriteria)
            throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try{
        return nonAdjBenefitMandateAdapterManager
                .locateStateObject(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					null, ex);
		}
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitMandateLocateCriteria locateCriteria,
            User user) throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try{
            return nonAdjBenefitMandateAdapterManager.locate(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitMandateLocateCriteria method in StandardBenefitBusinessObjectBuilder",
					null, ex);
		}
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieve(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param benefitDefinition
     * @return
     * @throws WPDException
     */
    public Object retrieve(BenefitDefinitionBO benefitDefinition)
            throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        benefitDefinitionAdapterManager.retrieve(benefitDefinition);
        return benefitDefinition;
    }


    /**
     * Function to retrieve benefit values from datbase
     * 
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieve(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param standardBenefitBO
     * @return
     * @throws WPDException
     */
    public Object retrieve(StandardBenefitBO standardBenefitBO, Map params)
            throws WPDException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering retrieve(): Standard Benefit");
        // get the subObjectName from the map
        String subObjectName = (String) params
                .get(BusinessConstants.SUB_OBJECT_NAME);
        // TODO Get the status through retrieve. Waiting for confirmation from
        // framework team
        standardBenefitBO.setStatus(WebConstants.STATUS_BUILDING);
        int keyForRetrieve = ((Integer) params
                .get(BusinessConstants.KEY_FOR_RETRIEVE)).intValue();
        if (null != subObjectName && !WebConstants.EMPTY_STRING.equals(subObjectName)) {
            if (subObjectName.equals(BusinessConstants.BenefitDefinitionBOImpl)) {
                BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();

                BenefitDefinitionBO benefitDefinition = new BenefitDefinitionBO();
                benefitDefinition.setBenefitDefinitionMasterKey(keyForRetrieve);
                benefitDefinitionAdapterManager.retrieve(benefitDefinition);
                standardBenefitBO.setBenefitDefinition(benefitDefinition);
            } else if (subObjectName
                    .equals(BusinessConstants.BenefitMandateBOImpl)) {
                // create the object for the nonAdjMandateAdapterManager
                NonAdjBenefitMandateAdapterManager adapterManager = new NonAdjBenefitMandateAdapterManager();
                // create the object for the subOjectName
                BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();
                // get the keyForAttribute from the map and set it to the BO
                benefitMandateBOImpl.setBenefitSystemId(keyForRetrieve);
                // call the retrieve method in the adapter manager
                adapterManager.retrieve(benefitMandateBOImpl);
                // set the retrieved object in the StandardBenefitBO
                standardBenefitBO.setBenefitMandateBOImpl(benefitMandateBOImpl);

                BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
                benefitMandateLocateCriteria
                        .setBenefitMandateId(standardBenefitBO
                                .getBenefitMandateBOImpl()
                                .getBenefitMandateId());

                StateLocateCriteria locateCriteria = new StateLocateCriteria();
                locateCriteria.setBenefitMandateId(standardBenefitBO
                        .getBenefitMandateBOImpl().getBenefitMandateId());

                LocateResults locateResults = locate(benefitMandateLocateCriteria);
                List list = locateResults.getLocateResults();
                List citationList = new ArrayList();
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    CitationNumberBO citationNumberBO = (CitationNumberBO) iterator
                            .next();
                    citationList.add(citationNumberBO);
                }
                standardBenefitBO.getBenefitMandateBOImpl()
                        .setCitationNumberList(citationList);

                locateResults = locate(locateCriteria);
                List stateList = locateResults.getLocateResults();
                List state = new ArrayList();
                Iterator stateIterator = stateList.iterator();
                while (stateIterator.hasNext()) {
                    StateBO stateBO = (StateBO) stateIterator.next();
                    state.add(stateBO);
                }
                standardBenefitBO.getBenefitMandateBOImpl()
                        .setBenefitStateCodeList(state);

            } else if ("AdministrationOptionBO".equals(subObjectName)) {
                AdministrationOptionAdapterManager adapterManager = new AdministrationOptionAdapterManager();
                AdministrationOptionBO optionBO = new AdministrationOptionBO();
                BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
                String adminLevelSystemIdForBenefitLevel = params.get(
                        "adminLevelSystemIdForBenefitLevel").toString();
                optionBO.setAdminLevelOptionAssnSystemId(keyForRetrieve);
                optionBO
                        .setAdminLevelSystemIdForBenefitLevel(adminLevelSystemIdForBenefitLevel);
                adapterManager.retrieve(optionBO);
                benefitDefinitionBO.setAdministrationOptionBO(optionBO);
                standardBenefitBO.setBenefitDefinition(benefitDefinitionBO);
            }
        }
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning retrieve(): Standard Benefit");
        return standardBenefitBO;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param subObject
     * @param mainObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(BenefitDefinitionBO subObject,
            StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
        Logger
        	.logInfo("StandardBenefitBusinessObjectBuilder - Entering delete(): Standard Benefit");
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new BenefitDefinitionLocateCriteria();
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        .getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
		try {
		    standardBenefitAdapterServiceAccess.beginTransaction();
		    AdapterUtil.logBeginTranstn(transactionId,this, "delete(BenefitDefinitionBO subObject,StandardBenefitBO mainObject, Audit audit)");
	        /*benefitDefinitionLocateCriteria.setBenefitDefinitionMasterKey(subObject
	                .getBenefitDefinitionMasterKey());*/
	        benefitDefinitionLocateCriteria.setBenefitDefinitionKeys
									(subObject.getBenefitDefinitionKeys());
	        benefitDefinitionAdapterManager.deleteBenefitDefinition(
	                benefitDefinitionLocateCriteria, audit,standardBenefitAdapterServiceAccess);
	       
	        standardBenefitAdapterServiceAccess.endTransaction();
	        AdapterUtil.logTheEndTransaction(transactionId,this, "delete(BenefitDefinitionBO subObject,StandardBenefitBO mainObject, Audit audit)");
		}catch (Exception ex) {
			 standardBenefitAdapterServiceAccess.abortTransaction();
			 AdapterUtil.logAbortTxn(transactionId,this, "delete(BenefitDefinitionBO subObject,StandardBenefitBO mainObject, Audit audit)");
			 List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
	                 "Persist failed for BenefitDefinitionBO delete in StandardBenefitBuisnessObjectBuilder",
	                 errorParams, ex);
		}
     Logger
     	.logInfo("StandardBenefitBusinessObjectBuilder - Returning delete(): Standard Benefit");
	 return true;
    }


    /**
     * This method is used to get the mandate list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(MandateListLocateCriteria locateCriteria,
            User user) throws SevereException {
        // create the object of the adapter manager
        //MandateDefenitionAdapterManager mandateDefenitionAdapterManager = new
        // MandateDefenitionAdapterManager();
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        // return the mandate list
        //return
        // mandateDefenitionAdapterManager.locateMandateList(locateCriteria);
        return nonAdjBenefitMandateAdapterManager
                .locateMandateList(locateCriteria);
    }


    /**
     * This method is used to get the mandate list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(RuleLocateCriteria locateCriteria, User user)
            throws SevereException {
        // create the object of the adapter manager
        StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
        return adapterManager.locateStandardBenefitRuleId(locateCriteria);

    }

    /**
     * This method is used to get the mandate list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults filterRuleLocate(RuleLocateCriteria locateCriteria, User user)
            throws SevereException {
        // create the object of the adapter manager
        StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
        return adapterManager.filterRuleIdLocate(locateCriteria);

    }

    /**
     * This method is used to get the BenefitLevel list for BenefitLevelpopup
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitLevelListLocateCriteria locateCriteria,
            User user) throws SevereException {
        LookupAdapterManager lookupAdapterManager = new LookupAdapterManager();
        return lookupAdapterManager.locateBenefitLevelList(locateCriteria);
    }


    /**
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public List validateDateRange(BenefitDefinitionLocateCriteria locateCriteria)
            throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        return benefitDefinitionAdapterManager
                .validateDateRange(locateCriteria);
    }


    /**
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public List validateDateRange(
            BenefitAdministrationLocateCriteria locateCriteria)
            throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        return benefitDefinitionAdapterManager
                .validateDateRange(locateCriteria);
    }


    /**
     * This method is used to get the Benefit details
     * @param businessObject
     * @return
     * @throws WPDException
     */
    public Object retrieve(StandardBenefitBO businessObject)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering retrieve(): Standard Benefit1");
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitBO standardBenefitBO = (StandardBenefitBO) businessObject;
        standardBenefitBO = (StandardBenefitBO) standardBenefitAdapterManager
                .retrieveSB(standardBenefitBO);

        //change
        List domainList = DomainUtil.retrieveAssociatedDomains(BusinessConstants.STDBENEFIT,
                standardBenefitBO.getParentSystemId());
        standardBenefitBO.setBusinessDomains(domainList);
        //changes end

        UniverseBO universeBO = new UniverseBO();
        universeBO.setStandardBenefitKey(standardBenefitBO
                .getStandardBenefitKey());
        List searchList = standardBenefitAdapterManager
                .searchUniverse(universeBO);
        if (null != searchList) {
            if (searchList.size() > 0) {
                standardBenefitBO = this.prepareUniverseDisplayList(searchList,
                        standardBenefitBO);
            }
        }

        //Code Enhancement:::::BO for performing the search for Rule Id in the
        // new table
        AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
        assignedRuleIdBO.setEntitySystemId(standardBenefitBO
                .getStandardBenefitKey());
        assignedRuleIdBO.setEntityType(BusinessConstants.STANDARDBENEFIT);
        RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
        List searchRuleId = refdataAdapterManager
                .searchRuleId(assignedRuleIdBO);
        if (null != searchRuleId) {
            if (searchRuleId.size() > 0) {
                standardBenefitBO = this.prepareRuleIdBOList(searchRuleId,
                        standardBenefitBO);
            }
        }
        //End of Code Enhancement

        StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
        standardBenefitDatatypeBO.setStandardBenefitKey(standardBenefitBO
                .getStandardBenefitKey());
        List dataTypeResultList = standardBenefitAdapterManager
                .searchDatatype(standardBenefitDatatypeBO);
        standardBenefitBO.setDataTypeList(dataTypeResultList);
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning retrieve(): Standard Benefit1");
        return standardBenefitBO;
    }


    /**
     * 
     * @param businessObject
     * @return
     * @throws WPDException
     */
    public Object retrieveduplicateSB(StandardBenefitBO businessObject)
            throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitBO standardBenefitBO =  businessObject;
        if (null == standardBenefitAdapterManager
                .checkForDuplicateValues(standardBenefitBO)) {
            return null;
        } else {
            return standardBenefitBO;
        }

    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(StandardBenefitLocateCriteria locateCriteria,
            User user) throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering locate(): Standard Benefit");
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        LocateResults locateResults = standardBenefitAdapterManager
                .locateStandardBenefit(locateCriteria);
        locateResults.setLatestVersion(true);
	        List locateResultList = locateResults.getLocateResults();
	        List locateResultsWithDomain = new ArrayList();
	        
	        if(null != locateResultList && !locateResultList.isEmpty()){
		        Iterator locateResultListIter = locateResultList.iterator();
		        while (locateResultListIter.hasNext()) {
		            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) locateResultListIter
		                    .next();
		            List domainList = DomainUtil.retrieveAssociatedDomains(
		                   BusinessConstants.STDBENEFIT , standardBenefitBO.getParentSystemId());
		            standardBenefitBO.setBusinessDomains(domainList);
		            standardBenefitBO.setDomainDetail(getDomainDetail(domainList));
		            locateResultsWithDomain.add(standardBenefitBO);
		        }
	        }
          locateResults.setLocateResults(locateResultsWithDomain);
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning locate(): Standard Benefit");
        return locateResults;
    }
    private DomainDetail getDomainDetail(List domains){
    	Map lobMap=new HashMap();
    	Map beMap=new HashMap();
    	Map bgMap=new HashMap();
    	Map mbuMap = new HashMap();
    	DomainDetail domainDetail=new DomainDetail();
    	domainDetail.setLineOfBusiness(new ArrayList());
    	domainDetail.setBusinessEntity(new ArrayList());
    	domainDetail.setBusinessGroup(new ArrayList());
    	/*START CARS*/
    	domainDetail.setMarketBusinessUnit(new ArrayList());
    	/*END CARS*/
    	domainDetail.setDomainList(domains);
    	for(int i=0;i<domains.size();i++){
    		Domain domain=(Domain)domains.get(i);
    		if(lobMap.get(domain.getLineOfBusiness())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getLineOfBusiness());
    			domainItem.setItemDesc(domain.getLineOfBusinessDesc());
    			lobMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getLineOfBusiness().add(domainItem);
    		}
    		if(beMap.get(domain.getBusinessEntity())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getBusinessEntity());
    			domainItem.setItemDesc(domain.getBusinessEntityDesc());
    			beMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getBusinessEntity().add(domainItem);
    		}
    		if(bgMap.get(domain.getBusinessGroup())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getBusinessGroup());
    			domainItem.setItemDesc(domain.getBusinessGroupDesc());
    			bgMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getBusinessGroup().add(domainItem);
    		}
    		if(mbuMap.get(domain.getMarketBusinessUnit())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getMarketBusinessUnit());
    			domainItem.setItemDesc(domain.getMarketBusinessUnitDesc());
    			mbuMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getMarketBusinessUnit().add(domainItem);
    		}
    	}
    	return domainDetail;
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(String)
     * @param locateCriteria
     * @return list
     * @throws WPDException
     */
    //To get all the version of a standard benefit.
    public LocateResults locate(
            StandardBenefitViewAllLocateCriteria locateCriteria, User user)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering locate(): Standard Benefit View All");
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        LocateResults locateResults = standardBenefitAdapterManager
                .locateStandardBenefit(locateCriteria);

        // changes
        List locateResultList = locateResults.getLocateResults();
        List locateResultsWithDomain = new ArrayList();
        if(null != locateResultList && !locateResultList.isEmpty()){
	        Iterator locateResultListIter = locateResultList.iterator();
	        while (locateResultListIter.hasNext()) {
	            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) locateResultListIter
	                    .next();
	            List domainList = DomainUtil.retrieveAssociatedDomains(
	                  BusinessConstants.STDBENEFIT , standardBenefitBO.getParentSystemId());
	            standardBenefitBO.setBusinessDomains(domainList);
	            locateResultsWithDomain.add(standardBenefitBO);
	        }
        }
        locateResults.setLocateResults(locateResultsWithDomain);

        // changes end
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning locate(): Standard Benefit View All");
        return locateResults;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param subObject
     * @param mainObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(BenefitMandateBO subObject,
        StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try {
		    nonAdjBenefitMandateAdapterManager.deleteBenefitMandate(subObject,
		            audit);
        }catch (Exception ex) {
        	 List errorParams = new ArrayList(2);
             String obj = ex.getClass().getName();
             errorParams.add(obj);
             errorParams.add(obj.getClass().getName());
             throw new SevereException(
                     "Persist failed for BenefitMandateBO delete in StandardBenefitBuisnessObjectBuilder",
                     errorParams, ex);
		}
        return true;
		    
    }


    /**
     * Function to insert the universe values
     * 
     * @param universeList
     * @param universeType
     * @param standardBenefitKey
     */
    private void createUniverse(List universeList, String universeType,
            int standardBenefitKey, Audit audit,
            AdapterServicesAccess standardBenefitAdapterServiceAccess)
            throws SevereException,AdapterException {
        UniverseBO universeBO = new UniverseBO();
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        if (null != universeList) {
            for (int i = 0; i < universeList.size(); i++) {
                universeBO.setStandardBenefitKey(standardBenefitKey);
                universeBO.setUniverseType(universeType);
                universeBO.setUniverseCode(universeList.get(i).toString());
                universeBO.setCodeDescText(BusinessConstants.CODE_DESC);
                universeBO.setCreatedUser(audit.getUser());
                universeBO.setCreatedTimestamp(audit.getTime());
                universeBO.setLastUpdatedUser(audit.getUser());
                universeBO.setLastUpdatedTimestamp(audit.getTime());
                try{
	                standardBenefitAdapterManager.createUniverse(universeBO,
	                            standardBenefitAdapterServiceAccess);
                }catch(Exception e){
                	List errorParams = new ArrayList(2);
	                 String obj = e.getClass().getName();
	                 errorParams.add(obj);
	                 errorParams.add(obj.getClass().getName());
	                 throw new SevereException(
	                         "Persist failed for BenefitMandateBO delete in StandardBenefitBuisnessObjectBuilder",
	                         errorParams, e);

                }
            }
        }
    }


    /**
     * Function to delete the universe values
     * 
     * @param standardBenefitBO
     * @param standardBenefitAdapterServiceAccess
     *  
     */
    private void deleteUniverse(StandardBenefitBO standardBenefitBO,
            AdapterServicesAccess standardBenefitAdapterServiceAccess,
            Audit audit) throws SevereException,AdapterException {
        UniverseBO universeBO = new UniverseBO();
        universeBO.setStandardBenefitKey(standardBenefitBO
                .getStandardBenefitKey());
        universeBO.setUniverseType(BusinessConstants.UNIVERSE);
        universeBO.setUniverseCode(BusinessConstants.UNIVERSE_CODE);
        universeBO.setCodeDescText(BusinessConstants.CODE_DESC);
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        
        standardBenefitAdapterManager.deleteUniverse(universeBO,
                standardBenefitAdapterServiceAccess);
        // Code to delete the rule Id from the new table as part of enhancement
        RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
        AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
        assignedRuleIdBO.setEntitySystemId(standardBenefitBO
                .getStandardBenefitKey());
        assignedRuleIdBO.setPrimaryCode(BusinessConstants.REFERENCE);
        refdataAdapterManager.deleteRuleId(assignedRuleIdBO,
                standardBenefitAdapterServiceAccess, audit);

    }


    /**
     * Function to validate Date Range
     * 
     * @param locateCriteria
     * 
     *  
     */
    public List validateDateRangeForNonAdjMandate(
            BenefitMandateLocateCriteria locateCriteria) throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        return nonAdjBenefitMandateAdapterManager
                .validateDateRange(locateCriteria);
    }


    /**
     * This method is used to get the openQuestion list
     * 
     * @param questionLocateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(QuestionLocateCriteria questionLocateCriteria,
            User user) throws SevereException {
        AddQuestionAdapterManager addQuestionAdapterManager = new AddQuestionAdapterManager();
        if (questionLocateCriteria.getLocateAction() == 1) {
            return addQuestionAdapterManager
                    .locateOpenQuestions(questionLocateCriteria);
        } else if (questionLocateCriteria.getLocateAction() == 2) {
            return addQuestionAdapterManager
                    .locateHiddenQuestions(questionLocateCriteria);
        }
        return null;
    }


    /**
     * This method is used to get the selectedQuestion list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            SelectedQuestionListLocateCriteria locateCriteria, User user)
            throws SevereException {
        // create the object of the adapter manager
        AddQuestionAdapterManager addQuestionAdapterManager = new AddQuestionAdapterManager();
        //return
        return addQuestionAdapterManager.locate(locateCriteria);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     *             Builder class for AdminOption Popup Calling the locate method
     *             of LookupAdminOptionAdapterManager
     */
    public LocateResults locate(LookupAdminOptionLocateCriteria locateCriteria,
            User user) throws SevereException {
        LookupAdminOptionAdapterManager lookupAdminOptionAdapterManager = new LookupAdminOptionAdapterManager();
        return lookupAdminOptionAdapterManager.locate(locateCriteria);
    }


    /**
     * Method to insert/update question
     * 
     * @param benefitWrapperBO,standardBenefitBO,audit,insertFlag
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(BenefitWrapperBO benefitWrapperBO,
            StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
        Logger
     		.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit");
        TimeHandler th = new TimeHandler();
        Logger.logInfo(th.startPerfLogging("U23057 : Save benefit Coverage", "StandaredBenefitBusinessBuilder", "persist()"));
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        .getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	standardBenefitAdapterServiceAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "persist(BenefitWrapperBO benefitWrapperBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
        	if (insertFlag) {
        		benefitlevelAdapterManager.insertBenefitLevels(benefitWrapperBO, audit,
        				standardBenefitAdapterServiceAccess);
        	}else if (!insertFlag) {
	            benefitlevelAdapterManager.persistBenefit(benefitWrapperBO, audit,
	                    insertFlag,standardBenefitAdapterServiceAccess);
	        }
	        standardBenefitAdapterServiceAccess.endTransaction();
	        AdapterUtil.logTheEndTransaction(transactionId,this, "persist(BenefitWrapperBO benefitWrapperBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
	        Logger.logDebug(th.endPerfLogging());
	        
        }catch (Exception ex) {
        	standardBenefitAdapterServiceAccess.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId,this, "persist(BenefitWrapperBO benefitWrapperBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitWrapperBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
	        
		}
        Logger
     		.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");
        return true;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param subObject
     * @param mainObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(BenefitAdministrationBO subObject,
            StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
        BenefitAdministrationAdapterManager benefitAdministrationAdapterManager = new BenefitAdministrationAdapterManager();
        try{
            benefitAdministrationAdapterManager.deleteBenefitAdministrationObject(
                    subObject, audit);
        }catch (Exception ex) {
	       	 List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
	                 "Persist failed for BenefitAdministrationBO delete in StandardBenefitBuisnessObjectBuilder",
	                 errorParams, ex);
		}
        return true;
    }


    /*
     * to retrieve the data in the selected row for editing in
     * benefitAdministration.jsp
     *  
     */
    public Object retrieve(BenefitAdministrationBO businessObject)
            throws SevereException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering retrieve(): Benefit Administration");
        BenefitAdministrationAdapterManager benefitAdministrationAdapterManager = new BenefitAdministrationAdapterManager();
        BenefitAdministrationBO benefitAdministrationBO = (BenefitAdministrationBO) businessObject;
        benefitAdministrationAdapterManager
                .retrieveForEdit(benefitAdministrationBO);
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning retrieve(): Benefit Administration");
        return benefitAdministrationBO;

    }


    /**
     * Method to insert/update Administration Option to a benefit.
     * 
     * @param subObject,mainObject,audit,insertFlag
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(AdministrationOptionBO subObject,
            StandardBenefitBO mainObject, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
        Logger
     		.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit");
        boolean create = false;
        List questionnairesList = null;
        List previousQnList = null;
        List validQuestionnairesList = null;
        int previousQuestion = 0;
        StandardBenefitAdapterManager benefitAdapterManager = new StandardBenefitAdapterManager();
        AdminOptionLocateCriteria locateCriteria = new AdminOptionLocateCriteria();
        AdapterServicesAccess access = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        AdministrationOptionBO administrationOptionBO = (AdministrationOptionBO) subObject;
        SequenceAdapterManager sequenceAdapterManager = null;
        AdministrationOptionBO   adminOptionBO1;
        boolean isUpdateAdminOption = false;	
        try {
        	// Transaction begins.
        	access.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId, this, "persist(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit, boolean insertFlag)");
            AdministrationOptionAdapterManager administrationOptionAdapterManager = new AdministrationOptionAdapterManager();
            
            //Persist values of the attached admin options to 
            // BNFT_ADMIN_TO_LVL_ASSN
            if (insertFlag) {
            	sequenceAdapterManager = new SequenceAdapterManager();
                //for create
                int nextSequenceNumber = sequenceAdapterManager
                        .getNextBenefitAdminOptionSequence();
                administrationOptionBO
                        .setAdminLevelOptionAssnSystemId(nextSequenceNumber);
                create = administrationOptionAdapterManager
                        .createAdministrationOptionObject(
                                administrationOptionBO, audit, insertFlag,
                                access);
            } else {
            	if(WebConstants.UPDATE_ADMIN_OPTION.equals(subObject.getFlag())){
            		List listForUpdation = subObject.getAdminList();
                    for(int i =0; i< listForUpdation.size() ;i++){
                        adminOptionBO1  = (AdministrationOptionBO)listForUpdation.get(i);
                         administrationOptionAdapterManager
                            .updateAdministrationOptionObject(adminOptionBO1, audit, insertFlag,access);
                    }
                    isUpdateAdminOption = true;
            	}else{
	                //for update
	                administrationOptionAdapterManager
	                        .updateAdministrationOptionObject(
	                                administrationOptionBO, audit, insertFlag,access);
	                locateCriteria.setAdminOptSysIdForUpdate(administrationOptionBO
	                        .getAdminOptionSysIdForUpdate());
	                locateCriteria
	                        .setAdminLevelOptionAssnSysId(administrationOptionBO
	                                .getAdminLevelOptionAssnSystemId());
	                previousQnList = administrationOptionAdapterManager
	                        .getPreviousQuestion(locateCriteria);
                    }

            }
            if(! isUpdateAdminOption){	
	            locateCriteria.setAdminOptionSysId(administrationOptionBO
	                    .getAdminOptionSystemId());
	            //Locate- get values from QSTNR_MSTR,PSBL_ANSWR and insert to BNFT_QSTNR_ANSWRD
	            LocateResults locateResults = administrationOptionAdapterManager
	                    .locateDataForQuestionAssociation(locateCriteria);
	            List locateResultList = locateResults.getLocateResults();
	            //insert to BNFT_QSTNR_ANSWRD
	            if (insertFlag) { 
	            	if(null != locateResultList && !locateResultList.isEmpty()){
	            		for(int i = 0;i < locateResultList.size(); i++){
	            			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO)locateResultList.get(i);
	            			benefitAdapterManager.persistQuestionnaireForBenefit(
				            			benefitQuestionnaireAssnBO, audit, true,access); 
	            					
	            		}
	            	}
	            }
            }    
            // Transaction ends.
            access.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId, this, "persist(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit, boolean insertFlag)");
        } catch (SevereException ex) {
        	access.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId, this, "persist(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit, boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for AdministrationOptionBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
        } catch (AdapterException ex){
        	access.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId, this, "persist(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit, boolean insertFlag)");
        	throw new SevereException("Unknown exception found.",null,ex);
        	
		}
        Logger
     		.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");
        return true;
    }
	/**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            AdministrativeOptionLocateCriteria locateCriteria, User user)
            throws SevereException {
        AdministrationOptionAdapterManager manager = new AdministrationOptionAdapterManager();
        return manager.locate(locateCriteria);
    }


    /**
     * Method to insert/update question
     * 
     * @param selectedQuestionListBO,audit,insertFlag
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(SelectedQuestionListBO selectedQuestionListBO,
            StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
        Logger
     	.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit");
    	AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        .getAdapterService(); // new
    	long transactionId = AdapterUtil.getTransactionId();
    	try {
    		standardBenefitAdapterServiceAccess.beginTransaction(); //new change
    		AdapterUtil.logBeginTranstn(transactionId,this, "persist(SelectedQuestionListBO selectedQuestionListBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
	        if (insertFlag) {
	            // insert logic
	            selectedQuestionListBO.setCreatedDate(audit.getTime());
	            selectedQuestionListBO.setLastChangedDate(audit.getTime());
	            selectedQuestionListBO.setCreatedUser(audit.getUser());
	            selectedQuestionListBO.setLastUpdatedUser(audit.getUser());
	            AddQuestionAdapterManager addQuestionAdapterManager = new AddQuestionAdapterManager();
	            addQuestionAdapterManager.persistQuestion(selectedQuestionListBO,
	                    audit, true,standardBenefitAdapterServiceAccess);
	        } else if (!insertFlag) {
	            // update logic
	            // create the object of addQuestionAdapterManager
	            AddQuestionAdapterManager addQuestionAdapterManager = new AddQuestionAdapterManager();
	            // call the updateSeqNo method in the adaptermanager
	            addQuestionAdapterManager.updateSeqNo(selectedQuestionListBO,
	                    audit, insertFlag,standardBenefitAdapterServiceAccess);
	            //addQuestionAdapterManager.updateSeqNo(selectedQuestionListBO,
	            // insertFlag);
	        }
	        standardBenefitAdapterServiceAccess.endTransaction(); //new change
	        AdapterUtil.logTheEndTransaction(transactionId,this, "persist(SelectedQuestionListBO selectedQuestionListBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
    	}catch (Exception ex) {
    		standardBenefitAdapterServiceAccess.abortTransaction(); //new change
    		AdapterUtil.logAbortTxn(transactionId,this, "persist(SelectedQuestionListBO selectedQuestionListBO,StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)");
            throw new SevereException(null, null, ex);
    		
		}
        Logger
        .logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");
        return true;
    }
	/**
	 * 
	 * @param benefitDefinitionId
	 * @return
	 * @throws SevereException
	 */
	public List getBenefitValue(int benefitDefinitionId)throws SevereException{
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        return benefitlevelAdapterManager.getBenefitValue(benefitDefinitionId);		
	}

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitLocateCriteria locateCriteria, User user)
            throws SevereException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "StandaredBenefitAdapterManager", "locate"));
    	
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        LocateResults locateResults = benefitlevelAdapterManager.locateBenefitLevels(locateCriteria);
        Logger.logInfo(th.endPerfLogging());
        return locateResults;
    }
    
    public LocateResults locate(BenefitLevelTermLocateCriteria locateCriteria, User user)
    		throws SevereException {
    	BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
    	return benefitlevelAdapterManager.locateBenefitLevelTerm(locateCriteria);
    }
    


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(BenefitLevelLocateCriteria locateCriteria,
            User user) throws SevereException {
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        return benefitlevelAdapterManager.locateBenefitLines(locateCriteria);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public boolean locate(BenefitLevelLocateCriteria locateCriteria)
            throws SevereException {
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        return benefitlevelAdapterManager
                .benefitLevelDependancySearch(locateCriteria);
    }



    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param benefitLineBO
     * @param standardBenefitBO
     * @param audit
     * @return
     * @throws WPDExceptiona
     */
    public boolean delete(BenefitLineBO benefitLineBO,
            StandardBenefitBO standardBenefitBO, Audit audit)
            throws SevereException,AdapterException {
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        	.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        boolean bool = true;
        try {
        	standardBenefitAdapterServiceAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "delete(BenefitLineBO benefitLineBO,StandardBenefitBO standardBenefitBO, Audit audit)");
            bool = benefitlevelAdapterManager.removeBenefitLine(benefitLineBO,standardBenefitAdapterServiceAccess);
        	standardBenefitAdapterServiceAccess.endTransaction();
        	AdapterUtil.logTheEndTransaction(transactionId,this, "delete(BenefitLineBO benefitLineBO,StandardBenefitBO standardBenefitBO, Audit audit)");
        } catch (AdapterException ex) {
	    	 List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
                     "Persist failed for BenefitLineBO delete in BenefitLevelAdapterManager",
                     errorParams, ex);
        }
        return bool;
    }


    /**
     * 
     * @param question
     * @param mainObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(SelectedQuestionListBO question,
            StandardBenefitBO mainObject, Audit audit) throws WPDException,AdapterException {

    	AddQuestionAdapterManager adapterManager = new AddQuestionAdapterManager();
        AdapterServicesAccess adapterService = AdapterUtil.getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
        try{
        	adapterService.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "delete(SelectedQuestionListBO subObject,StandardBenefitBO mainObject, Audit audit)");
        	SelectedQuestionListBO deleteQuestion = new SelectedQuestionListBO();
        	deleteQuestion.setAdminOptionsSysId(question.getAdminOptionsSysId());

        	// Deleting the selected questions for deletion.
        	for (Iterator iter = question.getQuestionList().iterator(); iter.hasNext();) {
				Long questionNumber = (Long) iter.next();
				deleteQuestion.setQuestionNumber(questionNumber.intValue());
				AdapterUtil.performRemove(deleteQuestion, audit.getUser(), adapterService);
			}
        	
        	// Retrieve all questions
        	List associatedQuestions = adapterManager.getAssociatedQuestions(question.getAdminOptionsSysId());
        	
        	// Updating sequences
        	int sequenceNum = 1;
        	for (Iterator iter = associatedQuestions.iterator(); iter.hasNext();) {
				SelectedQuestionListBO associatedQn = (SelectedQuestionListBO) iter.next();
				if(associatedQn.getSequenceNumber() != sequenceNum) {
					associatedQn.setSequenceNumber(sequenceNum);
					associatedQn.setLastChangedDate(audit.getTime());
					associatedQn.setLastUpdatedUser(audit.getUser());
					AdapterUtil.performUpdate(associatedQn,audit.getUser(), adapterService);
				}
				sequenceNum++;
			}
        	adapterService.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId,this, "delete(SelectedQuestionListBO subObject,StandardBenefitBO mainObject, Audit audit)");
        }catch (Exception ex) {
        	adapterService.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId,this, "delete(SelectedQuestionListBO subObject,StandardBenefitBO mainObject, Audit audit)");
        	List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
			         "Persist failed for SelectedQuestionListBO delete in StandardBenefitBuisnessObjectBuilder",
					 errorParams, ex);
		}
        Logger
     		.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");
        return true;
    }


    /**
     * This method is used to get the possible answer list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(PossibleAnswersLocateCriteria locateCriteria,
            User user) throws SevereException {
        // create the object of the adapter manager
        AddQuestionAdapterManager addQuestionAdapterManager = new AddQuestionAdapterManager();
        int questionNumber = 0;
        // return the possible answer list
        //return addQuestionAdapterManager.locatePossibleAnswers(locateCriteria);
        if(null != locateCriteria){
        	questionNumber = locateCriteria.getQuestionNumber();
        }
        return addQuestionAdapterManager.locatePossibleAnswers(questionNumber);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param subObject
     * @param mainObject
     * @param audit
     * @return boolean
     * @throws WPDException
     */
    public boolean delete(AdministrationOptionBO subObject,
            StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
        Logger
     	.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit");
        List adminDeleteList =null;
        AdministrationOptionAssociationBO administrationOptionAssociationBO = new AdministrationOptionAssociationBO();
        AdapterServicesAccess standardBenefitAdapterServiceAccess = AdapterUtil
        .getAdapterService(); 
        long transactionId = AdapterUtil.getTransactionId();
        try{
        	adminDeleteList = subObject.getAdminDeleteList();
        	
        	standardBenefitAdapterServiceAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "delete(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit)");
        	subObject.setBenefitId(mainObject.getParentSystemId());
        	if(null!=adminDeleteList){
        		for (int i=0;i<adminDeleteList.size();i++){
        	
        	administrationOptionAssociationBO.setAdminLvlOptionAsscnId(Integer.parseInt((adminDeleteList.get(i)).toString()));
	        AdministrationOptionAdapterManager manager = new AdministrationOptionAdapterManager();
	        manager.delete(administrationOptionAssociationBO, audit,standardBenefitAdapterServiceAccess);
	        subObject.setAdminLevelOptionAssnSystemId(Integer.parseInt((adminDeleteList.get(i)).toString()));
	        manager.delete(subObject, audit,standardBenefitAdapterServiceAccess);
            	}
        	}
        	standardBenefitAdapterServiceAccess.endTransaction();
	        AdapterUtil.logTheEndTransaction(transactionId,this, "delete(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit)");
            	
        }catch (Exception ex) {
        	standardBenefitAdapterServiceAccess.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId,this, "delete(AdministrationOptionBO subObject,StandardBenefitBO mainObject, Audit audit)");
        	 List errorParams = new ArrayList(2);
             String obj = ex.getClass().getName();
             errorParams.add(obj);
             errorParams.add(obj.getClass().getName());
             throw new SevereException(
                     "Persist failed for AdministrationOptionBO delete in StandardBenefitBuisnessObjectBuilder",
                     errorParams, ex);
		}
        Logger
     	.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit");

        return true;
    }


    /**
     * This method is used to get the possible answer list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(DataTypeLocateCriteria dataTypeLocateCriteria,
            User user) throws SevereException {
        LocateResults locateResults = new LocateResults();
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
        standardBenefitDatatypeBO.setStandardBenefitKey(dataTypeLocateCriteria
                .getStandardBenefitkey());
        locateResults.setLocateResults(standardBenefitAdapterManager
                .searchDatatype(standardBenefitDatatypeBO));
        return locateResults;
    }


    /**
     * This method is used to check if admin option is prsent
     * 
     * @param criteria
     * @return
     * @throws WPDException
     */
    public List isAdminOptionPresent(AdministrativeOptionLocateCriteria criteria) {
        AdministrationOptionAdapterManager manager = new AdministrationOptionAdapterManager();
        List adminOptionList = manager.isAdminOptionPresent(criteria)
                .getLocateResults();
        return adminOptionList;

    }


    /**
     * This method is used to check if StandardBenefit is prsent
     * 
     * @param criteria
     * @return
     * @throws WPDException
     */
    public List isStandardBenefitPresent(
            StandardBenefitDeleteLocateCriteria criteria) throws WPDException {
        StandardBenefitAdapterManager manager = new StandardBenefitAdapterManager();
        SearchResults searchResults = null;
        searchResults = manager.isStandardBenefitPresent(criteria);
        List locateResultsList = new ArrayList();
        if(null != searchResults.getSearchResults()&& !searchResults.getSearchResults().isEmpty()){
	        Iterator searchResultIterator = searchResults.getSearchResults()
	                .iterator();
	       
	        while (searchResultIterator.hasNext()) {
	            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) searchResultIterator
	                    .next();
	            StandardBenefitLocateResult sbLocateResult = new StandardBenefitLocateResult();
	            sbLocateResult.setStandardBenefitKey(standardBenefitBO
	                    .getStandardBenefitKey());
	            sbLocateResult.setBenefitIdentifier(standardBenefitBO
	                    .getBenefitIdentifier());
	            locateResultsList.add(sbLocateResult);
	        }
        }

        return locateResultsList;
    }


    /**
     * Method to delete latest version
     * 
     * @param criteria
     * @return boolean
     * @throws WPDException
     */

    public List deleteLatestVersion1(
            StandardBenefitDeleteLocateCriteria criteria,AdapterServicesAccess access)
            throws SevereException,AdapterException {
        StandardBenefitAdapterManager manager = new StandardBenefitAdapterManager();
        SearchResults searchResults = null;
        try{
        	searchResults = manager.removeStandardBenefit(criteria,access);
        	
        }catch (Exception ex) {
        	List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
	                 "Persist failed for StandardBenefitDeleteLocateCriteria deleteLatestVersion in StandardBenefitBuisnessObjectBuilder",
	                 errorParams, ex);
		}
        List locateResultsList = new ArrayList();
        if(null != searchResults.getSearchResults() && !searchResults.getSearchResults().isEmpty()){
	        Iterator searchResultIterator = searchResults.getSearchResults()
	                .iterator();
	       
	        while (searchResultIterator.hasNext()) {
	            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) searchResultIterator
	                    .next();
	            StandardBenefitLocateResult sbLocateResult = new StandardBenefitLocateResult();
	            sbLocateResult.setStandardBenefitKey(standardBenefitBO
	                    .getStandardBenefitKey());
	
	            locateResultsList.add(sbLocateResult);
	
	        }
        }
        return locateResultsList;
    }


    /**
     * Copies the Business Object from source to target
     * 
     * @param srcStandardBenefitBO
     * @param trgtStandardBenefitBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean copy(StandardBenefitBO srcStandardBenefitBO,
            StandardBenefitBO trgtStandardBenefitBO, Audit audit)
            throws SevereException, AdapterException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering copy(): Standard Benefit");
        long transactionId = AdapterUtil.getTransactionId();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
        	.getAdapterService(); 
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitSourceDestinationLocateCriteria criteria = new StandardBenefitSourceDestinationLocateCriteria();
        criteria.setSourceKey(srcStandardBenefitBO.getStandardBenefitKey());
        criteria.setDestinationKey(trgtStandardBenefitBO
                .getStandardBenefitKey());
        criteria.setCreatedUser(audit.getUser());
        try{
	        adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            standardBenefitAdapterManager.copyStandardBenefit(criteria,
                    WebConstants.STATUS_CHECK_IN,adapterServicesAccess);
	        adapterServicesAccess.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");

        }catch(Exception e){
            adapterServicesAccess.abortTransaction();
            AdapterUtil.logAbortTxn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, e);
        }
        Logger
        	.logInfo("StandardBenefitBusinessObjectBuilder - Returning copy(): Standard Benefit");
        return false;
    }


    /**
     * Copies the Business Object from source to target
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean copy(StandardBenefitBO srcStandardBenefitBO,
            StandardBenefitBO trgtStandardBenefitBO, Audit audit,
            HashMap hashMap) throws SevereException, AdapterException {
        Logger
		        .logInfo("StandardBenefitBusinessObjectBuilder - Entering copy(): Standard Benefit");
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService(); 
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitSourceDestinationLocateCriteria criteria = new StandardBenefitSourceDestinationLocateCriteria();
        criteria.setSourceKey(srcStandardBenefitBO.getStandardBenefitKey());
        criteria.setDestinationKey(trgtStandardBenefitBO
                .getStandardBenefitKey());
        criteria.setCreatedUser(audit.getUser());
        try{
	        adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            standardBenefitAdapterManager.copyStandardBenefit(criteria,
                    WebConstants.STATUS_COPY,adapterServicesAccess);
            // Modified for the domain validation check.
            // When the domain is modified for a benefit, all the questionnaires
            // which are invalid for this changed domain are removed.
           List invalidQuestionnairesList = 
           	standardBenefitAdapterManager.getQuestionnairesWithInvalidDomains(trgtStandardBenefitBO);
           if(null != invalidQuestionnairesList && !invalidQuestionnairesList.isEmpty()){
           		for(int i = 0; i < invalidQuestionnairesList.size(); i++){
           			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO)
					invalidQuestionnairesList.get(i);
           			standardBenefitAdapterManager.delete(benefitQuestionnaireAssnBO,audit,
           					adapterServicesAccess);
           		}
           }
           //When the domain is modified for a benefit, all the questionnaires
           //which are valid for this changed domain are removed.
           List validQuestionnairesList = standardBenefitAdapterManager.getQuestionnairesWithValidDomains
		   				(trgtStandardBenefitBO);
           if(null != validQuestionnairesList && !validQuestionnairesList.isEmpty()){
       		for(int i = 0; i < validQuestionnairesList.size(); i++){
       			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO)
					validQuestionnairesList.get(i);
       			standardBenefitAdapterManager.persistQuestionnaireForBenefit(benefitQuestionnaireAssnBO,audit,
       					true,adapterServicesAccess);
       		}
           }
            
	        adapterServicesAccess.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");

        }catch(Exception e){
            adapterServicesAccess.abortTransaction();
            AdapterUtil.logAbortTxn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, e);
        }
        Logger
        .logInfo("StandardBenefitBusinessObjectBuilder - Returning copy(): Standard Benefit");
        return false;

    }


    /**
     * Copies the Business Object for checkout
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */

    public boolean copyForCheckOut(StandardBenefitBO srcStandardBenefitBO,
            StandardBenefitBO trgtStandardBenefitBO, Audit audit)
            throws SevereException,AdapterException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering copy(): Standard Benefit Copy for CheckOut");
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService(); 
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        StandardBenefitSourceDestinationLocateCriteria criteria = new StandardBenefitSourceDestinationLocateCriteria();
        criteria.setSourceKey(srcStandardBenefitBO.getStandardBenefitKey());
        criteria.setDestinationKey(trgtStandardBenefitBO
                .getStandardBenefitKey());
        criteria.setCreatedUser(audit.getUser());
        try{
	        adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
        	standardBenefitAdapterManager.copyStandardBenefitForCheckOut(criteria,adapterServicesAccess);
	        adapterServicesAccess.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");

        }catch(Exception e){
            adapterServicesAccess.abortTransaction();
            AdapterUtil.logAbortTxn(transactionId,this, "persist(StandardBenefitBO businessObject, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for BenefitMandateBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning copy(): Standard Benefit Copy for CheckOut");
        return false;
    }


    /**
     * deletes the Business Object
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(StandardBenefitBO mainObject, Audit audit)
            throws SevereException,AdapterException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering delete(): Standard Benefit");
        StandardBenefitDeleteLocateCriteria criteria = new StandardBenefitDeleteLocateCriteria();
        int key = mainObject.getStandardBenefitKey();
        criteria.setStandardBenefitKey(mainObject.getStandardBenefitKey());
        //setting the action variable to criteria ,ie either CHECK IN or DELETE
        //setting the action variable to identify if the delete is for CHECK IN 
        criteria.setAction(mainObject.getAction());
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        List locateResultsList = null;
        try{
        	adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId, this, "delete(StandardBenefitBO mainObject, Audit audit)");
        	locateResultsList = this.deleteLatestVersion1(criteria,adapterServicesAccess);
        	adapterServicesAccess.endTransaction();
        	AdapterUtil.logTheEndTransaction(transactionId, this, "delete(StandardBenefitBO mainObject, Audit audit)");
        }catch (Exception ex) {
			adapterServicesAccess.abortTransaction();
			AdapterUtil.logAbortTxn(transactionId, this, "delete(StandardBenefitBO mainObject, Audit audit)");
			 List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
	                 "Persist failed for StandardBenefitBO delete in StandardBenefitBuisnessObjectBuilder",
	                 errorParams, ex);
		}
        
        int size = locateResultsList.size();
        for (int i = 0; i < size; i++) {
            StandardBenefitLocateResult sbLocateResult = (StandardBenefitLocateResult) locateResultsList
                    .get(i);
            if (key == sbLocateResult.getStandardBenefitKey()) {
                return true;
            }
        }
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning delete(): Standard Benefit");
        return false;
    }


    /**
     * deletes the latest version of benefit
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean deleteLatestVersion(StandardBenefitBO mainObject, Audit audit)
            throws SevereException,AdapterException {
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Entering deleteLatestVersion(): Standard Benefit");
        StandardBenefitDeleteLocateCriteria criteria = new StandardBenefitDeleteLocateCriteria();
        int key = mainObject.getStandardBenefitKey();
        criteria.setStandardBenefitKey(mainObject.getStandardBenefitKey());
//      setting the action variable to criteria ,ie either CHECK IN or DELETE
        criteria.setAction(mainObject.getAction());
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        List locateResultsList;
        try{
        	adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId, this, "deleteLatestVersion(StandardBenefitBO mainObject, Audit audit)");
        	locateResultsList = this.deleteLatestVersion1(criteria,adapterServicesAccess);
        	adapterServicesAccess.endTransaction();
        	AdapterUtil.logTheEndTransaction(transactionId, this, "deleteLatestVersion(StandardBenefitBO mainObject, Audit audit)");
        }catch (Exception ex) {
			adapterServicesAccess.abortTransaction();
			AdapterUtil.logAbortTxn(transactionId, this, "deleteLatestVersion(StandardBenefitBO mainObject, Audit audit)");
			 List errorParams = new ArrayList(2);
	         String obj = ex.getClass().getName();
	         errorParams.add(obj);
	         errorParams.add(obj.getClass().getName());
	         throw new SevereException(
	                 "Persist failed for StandardBenefitBO delete in StandardBenefitBuisnessObjectBuilder",
	                 errorParams, ex);
		}
        if(null != locateResultsList ){
	        int size = locateResultsList.size();
	        for (int i = 0; i < size; i++) {
	            StandardBenefitLocateResult sbLocateResult = (StandardBenefitLocateResult) locateResultsList
	                    .get(i);
	            if (key == sbLocateResult.getStandardBenefitKey()) {
	                return true;
	            }
	        }
        }
        Logger
                .logInfo("StandardBenefitBusinessObjectBuilder - Returning deleteLatestVersion(): Standard Benefit");
        return false;
    }


    /**
     * This method is used to get the possible answer list
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            BenefitPopUpLocateCriteria benefitPopUpLocateCriteria, User user)
            throws SevereException {
        LocateResults locateResults = new LocateResults();
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        BenefitLevelPopUpBO benefitLevelPopUpBO = new BenefitLevelPopUpBO();
        benefitLevelPopUpBO.setStandardBenefitKey(benefitPopUpLocateCriteria
                .getStandardBenefitId());
        benefitLevelPopUpBO.setCatalogId(benefitPopUpLocateCriteria.getPopUpId());
        if (benefitPopUpLocateCriteria.getPopUpId() == 1934)
            benefitLevelPopUpBO.setSelectedType(BusinessConstants.TERM);
        if (benefitPopUpLocateCriteria.getPopUpId() == 1935)
            benefitLevelPopUpBO.setSelectedType(BusinessConstants.QUALIFIER);
        if (benefitPopUpLocateCriteria.getPopUpId() == 1936)
            benefitLevelPopUpBO.setSelectedType(BusinessConstants.PROVIDER_ARRANGEMENT);
        return benefitlevelAdapterManager.locate(benefitLevelPopUpBO);
    }


    /**
     * @param benefitLineLocateCriteria
     * @return
     * @throws SevereException
     */
    public boolean locate(BenefitLineLocateCriteria benefitLineLocateCriteria)
            throws SevereException {
        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        return benefitlevelAdapterManager
                .locateDependantBenefitLines(benefitLineLocateCriteria);
    }


    /**
     * @param StandardBenefitLocateCriteria
     * @return
     * @throws SevereException
     */
    public List checkTermUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        SearchResults searchResults = standardBenefitAdapterManager
                .checkTermUpdate(standardBenefitLocateCriteria);
        // **Change**
        List aggregateTerm = null;
        List nonAggreagateTerm = null;
        List finalTerms = null;
        List listToBeRemoved = null;
        // from the search results seperate the aggregate and non-aggregate
        // terms
        if (searchResults != null) {
            List locateresult = (List) searchResults.getSearchResults();
            Iterator iter = locateresult.iterator();
            aggregateTerm = new ArrayList();
            nonAggreagateTerm = new ArrayList();
            finalTerms = new ArrayList();
            if (locateresult != null) {
                while (iter.hasNext()) {
                    TermQualifierPVABO termQualifierPVABO = (TermQualifierPVABO) iter
                            .next();
                    String termCode = termQualifierPVABO.getTermCode();
                    if (null != termCode && !termCode.equals(WebConstants.EMPTY_STRING)) {
                        int isAggregate = termCode.indexOf(WebConstants.COMMA);
                        if (isAggregate == -1) {
                            nonAggreagateTerm.add(termCode);
                            finalTerms.add(termQualifierPVABO);
                        } else {
                            StringTokenizer tokenizer = new StringTokenizer(
                                    termCode, WebConstants.COMMA);
                            while (tokenizer.hasMoreTokens()) {
                                String aggSepratedTerm = tokenizer.nextToken();
                                if (!aggregateTerm.contains(aggSepratedTerm)) {
                                    aggregateTerm.add(aggSepratedTerm);
                                }
                            }
                        }
                    }
                }
            }
        }
        // compare the non-aggregate term with the aggregate term
        // list and remove if it present in the non-aggregate term list
        if (null != aggregateTerm && !aggregateTerm.isEmpty()) {
            listToBeRemoved = new ArrayList();
            for (int i = 0; i < aggregateTerm.size(); i++) {
                String aggTermCode = aggregateTerm.get(i).toString();
                if (null != nonAggreagateTerm && !nonAggreagateTerm.isEmpty()) {
                    if (nonAggreagateTerm.contains(aggTermCode)) {
                        listToBeRemoved.add(aggTermCode);
                    }
                }
            }
            if (null != listToBeRemoved && !listToBeRemoved.isEmpty()) {
                aggregateTerm.removeAll(listToBeRemoved);
            }
        }
        // fetch the description for the code in the aggregate term list
        if (null != aggregateTerm && !aggregateTerm.isEmpty()) {
            standardBenefitLocateCriteria.setTermCodeList(aggregateTerm);
            SearchResults descSearchResults = standardBenefitAdapterManager
                    .retrieveDescForAggregateTerm(standardBenefitLocateCriteria);
            if (null != descSearchResults) {
                List locateResult = (List) descSearchResults.getSearchResults();
                finalTerms.addAll(locateResult);
            }
        }
        return finalTerms;
        // **End**
        //return searchResults;

    }


    /**
     * @param StandardBenefitLocateCriteria
     * @return
     * @throws SevereException
     */
    public List checkQualifierUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
    	
    	 StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
         SearchResults searchResults = standardBenefitAdapterManager
                 .checkQualifierUpdate(standardBenefitLocateCriteria);
         // Change: Qualifier Aggregate
         List aggregateQualifier = null;
         List nonAggregateQualifier = null;
         List finalQualifiers = null;
         List listToBeRemoved = null;
         // from the search results seperate the aggregate and non-aggregate
         // qualifiers
         if (searchResults != null) {
             List locateresult = (List) searchResults.getSearchResults();
             Iterator iter = locateresult.iterator();
             aggregateQualifier = new ArrayList();
             nonAggregateQualifier = new ArrayList();
             finalQualifiers = new ArrayList();
             if (locateresult != null) {
                 while (iter.hasNext()) {
                     TermQualifierPVABO termQualifierPVABO = (TermQualifierPVABO) iter
                             .next();
                     String qualifierCode = termQualifierPVABO.getQualifierCode();
                     if (null != qualifierCode && !qualifierCode.equals(WebConstants.EMPTY_STRING)) {
                         int isAggregate = qualifierCode.indexOf(WebConstants.COMMA);
                         if (isAggregate == -1) {
                         	nonAggregateQualifier.add(qualifierCode);
                         	finalQualifiers.add(termQualifierPVABO);
                         } else {
                             StringTokenizer tokenizer = new StringTokenizer(
                             		qualifierCode,WebConstants.COMMA);
                             while (tokenizer.hasMoreTokens()) {
                                 String aggSeperatedQualifier = tokenizer.nextToken();
                                 if (!aggregateQualifier.contains(aggSeperatedQualifier)) {
                                 	aggregateQualifier.add(aggSeperatedQualifier);
                                 }
                             }
                         }
                     }
                 }
             }
         }
         // compare the non-aggregate qualifier with the aggregate qualifier
         // list and remove if it present in the non-aggregate qualifier list
         if (null != aggregateQualifier && !aggregateQualifier.isEmpty()) {
             listToBeRemoved = new ArrayList();
             for (int i = 0; i < aggregateQualifier.size(); i++) {
                 String aggQualifierCode = aggregateQualifier.get(i).toString();
                 if (null != nonAggregateQualifier && !nonAggregateQualifier.isEmpty()) {
                     if (nonAggregateQualifier.contains(aggQualifierCode)) {
                         listToBeRemoved.add(aggQualifierCode);
                     }
                 }
             }
             if (null != listToBeRemoved && !listToBeRemoved.isEmpty()) {
             	aggregateQualifier.removeAll(listToBeRemoved);
             }
         }
         // fetch the description for the code in the aggregate qualifier list
         if (null != aggregateQualifier && !aggregateQualifier.isEmpty()) {
             standardBenefitLocateCriteria.setQualifierCodeList(aggregateQualifier);
             SearchResults descSearchResults = standardBenefitAdapterManager
                     .retrieveDescForAggregateQualifier(standardBenefitLocateCriteria);
             if (null != descSearchResults) {
                 List locateResult = (List) descSearchResults.getSearchResults();
                 finalQualifiers.addAll(locateResult);
             }
         }
         return finalQualifiers;
         // **End**
         

    }


    /**
     * @param StandardBenefitLocateCriteria
     * @return
     * @throws SevereException
     */

    public SearchResults checkPVAUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        SearchResults searchResults = standardBenefitAdapterManager
                .checkPVAUpdate(standardBenefitLocateCriteria);
        return searchResults;
    }


    /**
     * @param StandardBenefitLocateCriteria
     * @return
     * @throws SevereException
     */

    public SearchResults checkDataTypeUpdate(
            StandardBenefitLocateCriteria standardBenefitLocateCriteria)
            throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        SearchResults searchResults = standardBenefitAdapterManager
                .checkDataTypeUpdate(standardBenefitLocateCriteria);
        return searchResults;
    }


    /**
     * For Date Validation : To check if the benefitAdm dates lie within the
     * benefitAdm dates
     *  
     */
    public List checkBenefitDefinitionDateInBenefitAdministration(
            BenefitDefinitionLocateCriteria locateCriteria)
            throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        return benefitDefinitionAdapterManager
                .checkBenefitDefinitionDateInBenefitAdministration(locateCriteria);
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(AdminLevelLocateCriteria locateCriteria,
            User user) throws SevereException {
        LookupAdminOptionAdapterManager lookupAdminOptionAdapterManager = new LookupAdminOptionAdapterManager();
        return lookupAdminOptionAdapterManager.locate(locateCriteria);
    }


    /**
     * @param BenefitDefinitionLocateCriteria
     * @return
     * @throws SevereException
     */
    public List checkIfBenefitAdminAvailable(
            BenefitDefinitionLocateCriteria locateCriteria)
            throws SevereException {
        BenefitDefinitionAdapterManager benefitDefinitionAdapterManager = new BenefitDefinitionAdapterManager();
        return benefitDefinitionAdapterManager
                .checkIfBenefitAdminAvailable(locateCriteria);
    }


//  changed on 15th Nov
    /**
     * The function to check if the business domain is valid while saving the benefit.
     * It checks for the ALL-ALL-ALL condition,abc-ALL-ALL condition, abc-bcd-def condition. 
     * @param String,list,int
     * @return boolean
     * @throws SevereException
     */
    public boolean isEntityDuplicate(String entityName, List domainList,
            int entityParentId) throws SevereException {
        StandardBenefitAdapterManager adapter = new StandardBenefitAdapterManager();
        SearchResults searchResults = adapter.findDuplicateBenefits(entityName, domainList, entityParentId);
        if(searchResults.getSearchResultCount() > 0) 
        	return true;
        else 
        	return false;

//        List lineOfBusiness = new ArrayList();
//        List businessEntity = new ArrayList();
//        List businessGroup = new ArrayList();
//        Domain domain = null;
//        List resultList = new ArrayList();
//        boolean flag = true;
//        if(null != domainList && !domainList.isEmpty()) {
//	        for (Iterator iter = domainList.iterator(); iter.hasNext();) {
//	            domain = (Domain) iter.next();
//	            lineOfBusiness.add(domain.getLineOfBusiness());
//	            businessEntity.add(domain.getBusinessEntity());
//	            businessGroup.add(domain.getBusinessGroup());
//	        }
//        }
//        
//        if(lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN) && businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN) && businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN)){
//            //to get the benefits present in domain ALL wih the same name
//            flag = adapter.searchForDomainAll(entityName,entityParentId);
//        }
//        else if(!(lineOfBusiness.contains(BusinessConstants.UNIVERSAL_DOMAIN)) && !(businessEntity.contains(BusinessConstants.UNIVERSAL_DOMAIN)) && !(businessGroup.contains(BusinessConstants.UNIVERSAL_DOMAIN))){
//            flag = adapter.searchForDomainNotContainingAll(entityName,
//                    lineOfBusiness, businessEntity, businessGroup, entityParentId);
//            if(flag){
//                //**change made on 17th Dec 2007 to get the benefits present in any domain containing ALL wih the same name
//                flag = adapter.searchBenefitsWithAllDomains(entityName,
//                        lineOfBusiness, businessEntity, businessGroup, entityParentId);
//                //**change ends
//            }
//        }
//        else{
//            // to get the benefits present in same domain wih the same name
//            flag = adapter.searchForDomainNotContainingAll(entityName,
//                    lineOfBusiness, businessEntity, businessGroup, entityParentId);
//            if(flag){
//                // to get the differnt domain combinations for the same benefit
//                flag = adapter.searchForDuplicateDomain(entityName,
//                        lineOfBusiness, businessEntity, businessGroup, entityParentId);
//            }
//        }
//        // change ends
//        
//        if(!flag)
//            return true;
//        
//        return false;
    }
    
    // modified on 16th Nov
    /**
     *  new check for duplicate function for checkin
     * @param entityName,domainList
     * @return
     * @throws SevereException
     */
    public boolean isDuplicate(String entityName, List domainList,
            int entityParentId) throws SevereException {
        StandardBenefitAdapterManager adapter = new StandardBenefitAdapterManager();
        List lineOfBusiness = new ArrayList();
        List businessEntity = new ArrayList();
        List businessGroup = new ArrayList();
        List marketBusinessUnit = new ArrayList();
        Domain domain = null;
        List resultList = new ArrayList();
        boolean flag = true;
        for (Iterator iter = domainList.iterator(); iter.hasNext();) {
            domain = (Domain) iter.next();
            lineOfBusiness.add(domain.getLineOfBusiness());
            businessEntity.add(domain.getBusinessEntity());
            businessGroup.add(domain.getBusinessGroup());
            marketBusinessUnit.add(domain.getMarketBusinessUnit());
        }
        flag = adapter.searchForDomainNotContainingAll(entityName,
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit, entityParentId);
        if(!flag)
            return true;
        return false;
    }
    // modification ends


    /**
     * @param list
     * @return
     * @throws SevereException
     */
    public boolean persist(ArrayList attachedNotesList,
            StandardBenefitBO mainobject, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
        Logger
        	.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit Copy for CheckOut");
        AttachedNotesBO attachedNotesBO;
        NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
        	adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId, this, "persist(ArrayList attachedNotesList, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
        	 // insert logic - to insert the selected notes - iterate thorough them
        	
        	
            if (null != attachedNotesList && attachedNotesList.size() != 0) {
                for (int i = 0; i < attachedNotesList.size(); i++) {

                    attachedNotesBO = (AttachedNotesBO) attachedNotesList.get(i);
                    notesAttachmentAdapterManager.attachNotesForEntity(
                            attachedNotesBO, audit, insertFlag, adapterServicesAccess);
                }
            }
            adapterServicesAccess.endTransaction();
            AdapterUtil.logTheEndTransaction(transactionId, this, "persist(ArrayList attachedNotesList, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
        }catch (Exception ex) {
        	adapterServicesAccess.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId, this, "persist(ArrayList attachedNotesList, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
            List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for attachedNotesList,StandardBenefitBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
		}
        Logger
        	.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit Copy for CheckOut");
        return true;
    }


    /**
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public LocateResults locate(
            StandardBenefitNotesLocateCriteria locateCriteria, User user)
            throws SevereException {
        NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
        int entityId = locateCriteria.getEntityId();
        String entityType = locateCriteria.getEntityType();
        int maxResult = locateCriteria.getMaximumResultSize();
        //changed 28th Nov
        String benefitDefinitionKey = locateCriteria.getBenefitDefinitionKey() ;
        return notesAttachmentAdapterManager.locateAttachedNotes(entityId,
                entityType, maxResult,benefitDefinitionKey);
                //change ends
    }


    /**
     * @return
     * @throws SevereException
     */

    public boolean delete(AttachedNotesBO subObject,
            StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
    	AdapterServicesAccess access = AdapterUtil.getAdapterService();
    	long transactionId = AdapterUtil.getTransactionId();
        try {
        	access.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId,this,"delete(AttachedNotesBO subObject,StandardBenefitBO mainObject, Audit audit)");
        	AttachedNotesBO lineNote = new AttachedNotesBO();
        	NotesBOForDefId benefitNote = new NotesBOForDefId();
        	boolean isLineNote = true;
        	if(subObject.getBenefitDefinitionKey() == null)
        		isLineNote = false;
        	
        	for (Iterator iter = subObject.getAttachNotes().iterator(); iter.hasNext();) {
				String noteId = (String) iter.next();
				if(isLineNote) {
					// Line Note
					lineNote.setNoteId(noteId);
					lineNote.setEntityType(subObject.getEntityType());
					lineNote.setEntityId(subObject.getEntityId());
					lineNote.setBenefitDefinitionKey(subObject.getBenefitDefinitionKey());
		        	AdapterUtil.performRemove(lineNote,audit.getUser(), access);
				} else {
					// Benefit Note
					benefitNote.setNoteId(noteId);
					benefitNote.setEntityType(subObject.getEntityType());
					benefitNote.setEntityId(subObject.getEntityId());
//					lineNote.setBenefitDefinitionKey(subObject.getBenefitDefinitionKey());
		        	AdapterUtil.performRemove(benefitNote,audit.getUser(), access);					
				}
			}
        	access.endTransaction();
        	AdapterUtil.logTheEndTransaction(transactionId,this,"delete(AttachedNotesBO subObject,StandardBenefitBO mainObject, Audit audit)");
        }catch (Exception se) {
        	access.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId,this,"delete(AttachedNotesBO subObject,StandardBenefitBO mainObject, Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in StandardBenefitBusinessObjectBuilder",
                    errorParams, se);
        } 
        return true;
    }


    /**
     * This method is used to get the notes list for the benefit line
     * 
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(
            NotesAttachmentForBenefitLineLocateCriteria locateCriteria,
            User user) throws SevereException {
        // create the object of the adapter manager
        NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
        return adapterManager.locateNotesListForBnftLine(locateCriteria, user);
    }


    /**
     * This method to insert the selected notes for the benefit line.
     * 
     * @param attachedNotesBO
     * @param mainobject
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public boolean persist(AttachedNotesBO attachnotesBO,
            StandardBenefitBO mainobject, Audit audit, boolean insertFlag)
            throws SevereException,AdapterException {
        Logger
    		.logInfo("StandardBenefitBusinessObjectBuilder - Entering persist(): Standard Benefit Notes");
        AttachedNotesBO attachedNotesBO;
        List attachedNotesList = attachnotesBO.getAttachNotes();
        NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
        	adapterServicesAccess.beginTransaction();
        	AdapterUtil.logBeginTranstn(transactionId, this, "persist(AttachedNotesBO attachnotesBO, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
            // insert logic - to insert the selected notes - iterate thorough them
            if (null != attachedNotesList && !attachedNotesList.isEmpty()) {
                for (int i = 0; i < attachedNotesList.size(); i++) {
                    attachedNotesBO = (AttachedNotesBO) attachedNotesList.get(i);
                    notesAttachmentAdapterManager.attachNotesForEntity(
                            attachedNotesBO, audit, insertFlag, adapterServicesAccess);
                }
            }
			adapterServicesAccess.endTransaction();
			AdapterUtil.logTheEndTransaction(transactionId, this, "persist(AttachedNotesBO attachnotesBO, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
        }catch (Exception ex) {
        	adapterServicesAccess.abortTransaction();
        	AdapterUtil.logAbortTxn(transactionId, this, "persist(AttachedNotesBO attachnotesBO, StandardBenefitBO mainobject, Audit audit, boolean insertFlag)");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Persist failed for AttachedNotesBO persist in StandardBenefitBuisnessObjectBuilder",
                    errorParams, ex);
		}
        Logger
    		.logInfo("StandardBenefitBusinessObjectBuilder - Returning persist(): Standard Benefit Notes");

        return true;
    }

    /**
     * To locate the BenefitLevelPopUpBO *
     * 
     * @param BenefitLevelPopUpBO
     * @return resultList
     * @throws SevereException
     */
    public List locateRefData(BenefitLevelPopUpBO benefitLevelPopUpBO) throws SevereException {
    	List resultList = null;
    	BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
        LocateResults locateResults = new LocateResults();
        locateResults = benefitlevelAdapterManager.locate(benefitLevelPopUpBO);
        resultList = locateResults.getLocateResults();
        return resultList;
    }
    /**
     * To locate the benefitLevelBO *
     * 
     * @param BenefitLevelBO
     * @return LocateResults
     * @throws SevereException
     */
    public List locateStateFundingArrangement(BenefitLevelBO benefitLevelBO) throws SevereException {
        StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        LocateResults locateResults = new LocateResults();
        List resultList = standardBenefitAdapterManager.locateStateFundingArrangement(benefitLevelBO);
        return resultList;
    }
    public List getStateList()throws SevereException {
	    NonAdjBenefitMandateAdapterManager adapterManager = new NonAdjBenefitMandateAdapterManager();
	    StateLocateCriteria criteria = new StateLocateCriteria();
	    BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
	    List resultList =null;
	    try{
	    criteria.setBenefitMandateId(benefitMandateBO
	            .getBenefitMandateId());
	    LocateResults locateResults = adapterManager
	    		.locateStateObject(criteria);
	    resultList = locateResults.getLocateResults();
	    }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateStateFundingArrangement BenefitLevelBO method in StandardBenefitBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateStateFundingArrangement BenefitLevelBO method in StandardBenefitBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateStateFundingArrangement BenefitLevelBO method in StandardBenefitBusinessObjectBuilder",
					null, ex);
		}
	    return  resultList;
    }
    /**
     * To locate the benefitLevelBO *
     * 
     * @param benefitLevelBO
     * @return List of BenefitLevelBO
     * @throws SevereException
     */
    public List locateReferance(BenefitLevelBO benefitLevelBO) throws SevereException {
    	List resultList =null;
    	StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
        LocateResults locateResults = new LocateResults();
        resultList = standardBenefitAdapterManager.locateReferance(benefitLevelBO);
        return resultList;
    }
    
    /**
     * Created on 6th march 2008
     * To persist the Standardbenefit BO
     * 
     * @param Standardbenefit
     * @return boolean
     * @throws SevereException
     */
    public boolean persistTimeStamp(StandardBenefitBO standardBenefitBO, Audit audit) throws SevereException {
		StandardBenefitAdapterManager benefitAdapterManager = new StandardBenefitAdapterManager();
		try {
			
			standardBenefitBO.setLastUpdatedUser(audit.getUser());
			standardBenefitBO.setLastUpdatedTimestamp(audit.getTime());				
			
			benefitAdapterManager.updateStandardBenefit(
					standardBenefitBO);
	
		} catch (AdapterException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject StandardBenefitBO, in StandardBenefitBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

  
    /**
     * This method is associate the multiple admin options to the benefit.
     * @param locateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public LocateResults locate(AssociateAdminOptionToBenefitLocateCriteria locateCriteria,
            User user) throws SevereException,AdapterException {
    	AdministrationOptionAdapterManager 
			administrationOptionAdapterManager =
					new AdministrationOptionAdapterManager();
        return administrationOptionAdapterManager.
					locateAssnAdmnOptToBnft(locateCriteria, 
												user.getUserId());
    }
    
    /**
     * Method to delete the levels and lines.
     * @param locateCriteria
     * @param user
     * @return
     * @throws SevereException
     */
    public boolean delete(BenefitLevelBO benefitLevelBO,
            		StandardBenefitBO standardBenefitBO, Audit audit)
            			throws SevereException, AdapterException {

		BenefitlevelAdapterManager adapterManager = null;
		long transactionId = 0;
		AdapterServicesAccess adapterServicesAccess = null;
		LocateResults locateResults = null;
        BenfitLevelLineDeleteLocateCriteria locateCriteria = null;
        boolean status = false;
			
		transactionId =	AdapterUtil.getTransactionId();		
		adapterServicesAccess = AdapterUtil.getAdapterService();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(BenefitLevelBO benefitLevelBO, " +
							"StandardBenefitBO standardBenefitBO, Audit audit)");
			adapterManager = new BenefitlevelAdapterManager();
			// set the required details to the locate criteria
			locateCriteria = new BenfitLevelLineDeleteLocateCriteria();
        	locateCriteria.setBenefitDefnId
						(benefitLevelBO.getBenefitDefinitionId());
        	locateCriteria.setBenefitLevels
						(benefitLevelBO.getBenefitLevels());
        	locateCriteria.setBenefitLines
							(benefitLevelBO.getBenefitLineIds());        	
			locateResults = adapterManager.
								deleteBenefitLevelAndLine
									(locateCriteria, audit.getUser());
			if(null != locateResults && 
        			locateResults.getTotalResultsCount() > 0){
        		status = true;
        	}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(BenefitLevelBO benefitLevelBO, " +
							"StandardBenefitBO standardBenefitBO, Audit audit)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(BenefitLevelBO benefitLevelBO, " +
							"StandardBenefitBO standardBenefitBO, Audit audit)");
			throw new SevereException(null, null, e);
		} catch (AdapterException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(BenefitLevelBO benefitLevelBO, " +
							"StandardBenefitBO standardBenefitBO, Audit audit)");
			throw new SevereException("Unknown Exception is caught", null, e);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(BenefitLevelBO benefitLevelBO, " +
							"StandardBenefitBO standardBenefitBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return status;
	}
    public RuleBO retrieveRuleInfo(String ruleId) throws SevereException{
    	  StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
    	  return adapterManager.retrieveRuleInfo(ruleId);
    }
    public List locateGroupRuleInfo(String ruleId) throws SevereException{
  	  StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
  	  SearchResults searchResults = adapterManager.locateGroupRuleInfo(ruleId);
  	  return searchResults.getSearchResults();
    }
    
    /**
	 * Method to retrive the parent and child questions in an admin option
	 * 
	 * @param locateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locate(
			SelectedQuestionaireLocateCriteria locateCriteria, User user)
			throws SevereException {

		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		LocateResults locateResults = new LocateResults();
		
		List locateResultList = adapterManager
				.locateQuestionaire(locateCriteria);
		int benefitId =locateCriteria.getBenefitId();
		int adminLevelOptionId =locateCriteria.getAdminLevelOptionSysId();
		HashMap allPossibleAnswerMap = locateCriteria.getAllPossibleAnswerMap();

		if (locateCriteria.getAction() == WebConstants.RETRIEVE_QUESTIONAIRE) {
			getPossibleAnswerList(locateResultList, allPossibleAnswerMap);
			List sortedList = BusinessUtil
					.getQuestionareHierarchy(locateResultList);
			locateResults.setLocateResults(sortedList);
		} else if (locateCriteria.getAction() == WebConstants.RETRIEVE_CHILD_QUESTIONAIRE) {
			int answerId = locateCriteria.getAnswerId();
			String answerDesc = locateCriteria.getAnswerDesc();
			int oldQuestionaireListIndex = locateCriteria
					.getQuestionareListIndex();
			
			List oldQuestionnareList = locateCriteria.getQuestionnareList();
			List oldListForUnsavedQuestion = new ArrayList(locateCriteria.getQuestionnareList());
			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) locateCriteria
					.getBenefitQuestionnaireAssnBO();
			int questionnaireId = benefitQuestionnaireAssnBO
					.getQuestionnaireId();
			((BenefitQuestionnaireAssnBO) oldQuestionnareList
					.get(oldQuestionaireListIndex)).setAnswerId(answerId);
			((BenefitQuestionnaireAssnBO) oldQuestionnareList
					.get(oldQuestionaireListIndex)).setAnswerDesc(answerDesc);
			
			allPossibleAnswerMap = locateCriteria.getAllPossibleAnswerMap();
			if (null != locateResultList) {
				getPossibleAnswerList(locateResultList, allPossibleAnswerMap);
			}
			List sortedList = BusinessUtil.getRearrangedQuestionnareList(
					locateResultList, oldQuestionnareList,
					oldQuestionaireListIndex);
			locateResults.setLocateResults(sortedList);
			
			List notesToDelete = getQuestionareNoteListToDelete(sortedList,oldListForUnsavedQuestion);
			deleteUnsavedQuestionNote(notesToDelete,benefitId,adminLevelOptionId,user);
		}
		return locateResults;
	}
	/*
	 * this method filter the questionareLiat and flag the question notes to be deleted
	 * 
	 * 
	 */
	public static List getQuestionareNoteListToDelete(List newQuestionnareList,List oldListForUnsavedQuestion){
		
		List listToDelete= new ArrayList();
		
		for(int i=0;i<oldListForUnsavedQuestion.size();i++){
			
			BenefitQuestionnaireAssnBO oldQuestionnareBo = (BenefitQuestionnaireAssnBO)oldListForUnsavedQuestion.get(i);
			
			for (int j=0;j<newQuestionnareList.size();j++){
				
				BenefitQuestionnaireAssnBO newQuestionnareBo = (BenefitQuestionnaireAssnBO)newQuestionnareList.get(j);
				if(newQuestionnareBo.compareTo(oldQuestionnareBo)){
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			//listToDelete.add(oldQuestionnareBo);
		}
		
		return newQuestionnareList;
		
	}
	
	
	/* this method perfotm delete the unsaved question note deatils while the questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 * 
	 */
	private void deleteUnsavedQuestionNote(List noteDetailList,int benefitId,int adminLevelOptionId,User user)
	throws SevereException{
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			BenefitQuestionnaireAssnBO questionnareBo = (BenefitQuestionnaireAssnBO)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotes_exists("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBenefitBO attachmentBo = new NotesToQuestionAttachmentBenefitBO();
				attachmentBo.setPrimaryId(benefitId);
				attachmentBo.setSecondaryId(adminLevelOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHBENEFIT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNoteOverrideStatus("F");
				NotesToQuestionAttachmentBenefitBO newattachmentBo=getNoteInfo(attachmentBo);
				if(null!=newattachmentBo && null!=newattachmentBo.getNoteId() && !("").equals(newattachmentBo.getNoteId())){
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setNoteVersionNumber(newattachmentBo.getNoteVersionNumber());
					try {
						adapterManager.deleteNotesAttachedToQuestion(attachmentBo,audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	 private NotesToQuestionAttachmentBenefitBO getNoteInfo(NotesToQuestionAttachmentBenefitBO attachmentBo){
	 	NotesToQuestionAttachmentBenefitBO newAttachmentBo = null;
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if(null!=resultList && resultList.size()>0){
			newAttachmentBo = (NotesToQuestionAttachmentBenefitBO)resultList.get(0);
		 	}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAttachmentBo;
	 }
	
	/**
	 * Method to retrieve the possible answers for the questions
	 * 
	 * @param locateResultList
	 * @throws SevereException
	 */
	public void getPossibleAnswerList(List locateResultList)
			throws SevereException {
		Logger
				.logInfo("StandardBenefitBusinessObjectBuilder - Entering getPossibleAnswerList()");
		if (locateResultList != null && !locateResultList.isEmpty()) {
			QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();
			int size = locateResultList.size();
			for (int i = 0; i < size; i++) {
				BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) locateResultList
						.get(i);
				int questionNumber = benefitQuestionnaireAssnBO.getQuestionId();
				QuestionBO questionBO = new QuestionBO();
				questionBO.setQuestionNumber(questionNumber);
				List possibleAnswerList = questionAdapterManager
						.getPossibleAnswer(questionBO, adapterServicesAccess);
				List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
				benefitQuestionnaireAssnBO
						.setPossibleAnswerList(arrangedPossibleAnswerList);
			}
		}
		Logger
				.logInfo("StandardBenefitBusinessObjectBuilder - Exiting getPossibleAnswerList()");
	}
	
	/**
	 * this method is for getting possible answer list.
	 * 
	 * @param locateResultList, this list contains  questions needed for possible answer
	 * and allPossibleAnswerMap.
	 * @return answer list
	 * @throws SevereException
	 */
    public void getPossibleAnswerList(List locateResultList, HashMap allPossibleAnswerMap) throws SevereException{
    	
    	  if(locateResultList != null && !locateResultList.isEmpty()){        
            QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
            // Create the Adapter Service Access
            AdapterServicesAccess adapterServicesAccess = AdapterUtil
                    .getAdapterService();

            // Iterate through the list to get the possible answers for the
            // question
            int listSize = locateResultList.size();
            for (int i = 0; i < listSize; i++) {
                // Get the individual entitybenefitAdministration objects from
                // the list
            	BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) locateResultList
                        .get(i);
                int questionNumber = benefitQuestionnaireAssnBO.getQuestionId();

                QuestionBO questionBO = new QuestionBO();

                questionBO.setQuestionNumber(questionNumber);

                List possibleAnswerList;
				if(null != allPossibleAnswerMap && allPossibleAnswerMap.containsKey(new Integer(questionNumber))){
					possibleAnswerList = (ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)); 
					Collections.sort(possibleAnswerList);					
				}else{
					//	 Call the getPossibleAnswers() of the question adaptermanager
	                possibleAnswerList = questionAdapterManager
	                        .getPossibleAnswer(questionBO, adapterServicesAccess);
				}
                
                List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
                // Set the possible answers list to the
                // benefitQuestionnaireAssnBO
                benefitQuestionnaireAssnBO
				.setPossibleAnswerList(arrangedPossibleAnswerList);
            }
        }
    	
    }
	
	/**
	 * Saving the questionnaire list that is attched to an admin option.
	 * 
	 * @param benefitQuestionnaireAssnBO
	 * @param standardBenefitBO
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(
			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO,
			StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
			throws SevereException {
		
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(BenefitQuestionnaireAssnBO,StandardBenefitBO, Audit,boolean)");
			
			adapterManager.persistQuestionnaireForBenefit(benefitQuestionnaireAssnBO, standardBenefitBO,
					audit, insertFlag, adapterServicesAccess);
			
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(BenefitQuestionnaireAssnBO,StandardBenefitBO, Audit,boolean)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(BenefitQuestionnaireAssnBO,StandardBenefitBO, Audit ,boolean)");
			throw new SevereException(
					"Persist for BenefitQuestionnaireAssnBO failed.Unknown exception is caught",
					null, ex);
		}
		return true;
	}
	
	/**
	 * this method for performing insert and update for benefit level question note 
	 * @param NotesToQuestionAttachmentBenefitBO
	 * @param Audit
	 * @param insertFlag
	 * @return boolean 
	 * 
	 */
	
	public boolean persist(
			NotesToQuestionAttachmentBenefitBO attachmentBO,
			StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
	throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		attachmentBO.setCreatedUser(audit.getUser());
		attachmentBO.setCreatedTimestamp(audit.getTime());
		attachmentBO.setLastUpdatedUser(audit.getUser());
		attachmentBO.setLastUpdatedTimestamp(audit.getTime());
		
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
			.logBeginTranstn(transactionId, this,
			"persist(NotesToQuestionAttachmentBO,StandardBenefitBO, Audit,boolean)");
			
			if(insertFlag){
				adapterManager.insertNotesAttachedToQuestion(
						attachmentBO, audit	);
			}else{
				adapterManager.updateNotesAttachedToQuestion(
						attachmentBO, audit	);
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
			.logTheEndTransaction(transactionId, this,
			"persist(NotesToQuestionAttachmentBO,StandardBenefitBO, Audit,boolean)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
			.logAbortTxn(transactionId, this,
			"persist(NotesToQuestionAttachmentBO,StandardBenefitBO, Audit ,boolean)");
			throw new SevereException(
					"Persist for NotesToQuestionAttachmentBO failed.Unknown exception is caught",
					null, ex);
		}
		return true;
	}
	

	/**
	 * this method for performing delete  for product-benefit level question note 
	 * @param NotesToQuestionAttachmentBenefitBO
	 * @param Audit
	 * @param insertFlag
	 * @return boolean 
	 * @throws SevereException,AdapterException
	 * 
	 */
	public boolean delete(NotesToQuestionAttachmentBenefitBO attachmentBO,
			StandardBenefitBO mainObject, Audit audit) throws SevereException,AdapterException {
		AdapterServicesAccess access = AdapterUtil.getAdapterService();
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			access.beginTransaction();
			AdapterUtil.logBeginTranstn(transactionId,this,"delete(NotesToQuestionAttachmentBenefitBO subObject,StandardBenefitBO " +
			"mainObject, Audit audit)");
			adapterManager.deleteNotesAttachedToQuestion(
					attachmentBO, audit	);
			access.endTransaction();
			AdapterUtil.logTheEndTransaction(transactionId,this,"delete(NotesToQuestionAttachmentBenefitBO subObject,StandardBenefitBO mainObject," +
			" Audit audit)");
		}catch (Exception se) {
			access.abortTransaction();
			AdapterUtil.logAbortTxn(transactionId,this,"delete(NotesToQuestionAttachmentBenefitBO subObject,StandardBenefitBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method in StandardBenefitBusinessObjectBuilder",
					errorParams, se);
		} 
		return true;
	}
	
	 /**
	 * The method will invoke the BenefitDefinitionAdapterManager to load all the
	 * Tier Definitions for benefit or product
	 * @param tierDefinitionlocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locate(TierDefinitionLocateCriteria tierDefinitionlocateCriteria,
	            User user) throws SevereException {
	     BenefitDefinitionAdapterManager benefitDefinitionAdapterManager 
	     									= new BenefitDefinitionAdapterManager();
	     return benefitDefinitionAdapterManager
	     									.locateTierDefinition(tierDefinitionlocateCriteria);
	 }
	 
	/**
	 * The method will insert/update Tier Definitions for a particular BenefitDefinition
	 * in the BNFT_TIER_DEFN_ASSN table
	 * @param benefitTierDefinitionAssnBO
	 * @param standardBenefitBO
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(
		     BenefitTierDefinitionAssnBO benefitTierDefinitionAssnBO,
				StandardBenefitBO standardBenefitBO, Audit audit, boolean insertFlag)
				throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		BenefitDefinitionAdapterManager bnftDefinitionAdapterManager = 
		    new BenefitDefinitionAdapterManager();
		
		List benefitTierDefinitionsList = benefitTierDefinitionAssnBO
				.getBenefitTierDefinitionsList();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(BenefitTierDefinitionAssnBO," +
							"StandardBenefitBO,Audit,boolean)");
			
			//To update the Tier Definitions,first deleting the
			//existing Tier Definitions for the particular Benefit Definition
			if(!insertFlag){ 
			     BenefitTierDefinitionAssnBO bnftTierDefinitionAssnBO = 
			         new BenefitTierDefinitionAssnBO();
			     bnftDefinitionAdapterManager.deleteBenefitTierDefinition
					(benefitTierDefinitionAssnBO,audit);
			}	
			//insert the Tier Definitions
			int listSize = benefitTierDefinitionsList.size();
			for (int i = 0; i < listSize; i++) {
			    BenefitTierDefinitionAssnBO bnftTierDefinitionAssnBO = 
			        (BenefitTierDefinitionAssnBO) benefitTierDefinitionsList.get(i);				
			    bnftTierDefinitionAssnBO.setCreatedUser(audit.getUser());
			    bnftTierDefinitionAssnBO.setCreatedTimestamp(audit.getTime());
			    bnftTierDefinitionAssnBO.setLastUpdatedUser(audit.getUser());
			    bnftTierDefinitionAssnBO.setLastUpdatedTimestamp(audit.getTime());	
			    
			    bnftDefinitionAdapterManager.persistBenefitTierDefinition
					(bnftTierDefinitionAssnBO,audit);
			   		    		    
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(BenefitTierDefinitionAssnBO," +
							"StandardBenefitBO,Audit,boolean)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(BenefitTierDefinitionAssnBO," +
							"StandardBenefitBO,Audit ,boolean)");
			throw new SevereException(
					"Persist for BenefitTierDefinitionAssnBO failed." +
					"Unknown exception is caught",
					null, ex);
		}
		return true;
	}
	
	 /**
	 * The method will retrieve the Tier Definitions associated 
	 * with the particular Benefit
	 * @param bnftTierDefinitionLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locate(BenefitTierDefinitionLocateCriteria 
	         bnftTierDefinitionLocateCriteria,User user) throws SevereException {
	    BenefitDefinitionAdapterManager benefitDefinitionAdapterManager 
	    	= new BenefitDefinitionAdapterManager();
	    int benefitDefinitionId = bnftTierDefinitionLocateCriteria.getBenefitDefinitionId();
	    if(benefitDefinitionId != 0){
	        return benefitDefinitionAdapterManager
	        	.locateBenefitTierDefinitionForBenDefn(bnftTierDefinitionLocateCriteria);
	    }else{
	        return benefitDefinitionAdapterManager.locateBenefitTierDefinition
	    	(bnftTierDefinitionLocateCriteria);
	    }
	 }
	
	/**
	 * The method will retrieve the Tier Definitions associated 
	 * with the particular BenefitDefinition 
	 * @param bnftTierDefinitionLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults retrieveTierDefnForBenDefn(BenefitTierDefinitionLocateCriteria 
	         bnftTierDefinitionLocateCriteria,User user) throws SevereException {
	    BenefitDefinitionAdapterManager benefitDefinitionAdapterManager 
	    	= new BenefitDefinitionAdapterManager();
	    return benefitDefinitionAdapterManager.locateBenefitTierDefinitionForBenDefn
	    	(bnftTierDefinitionLocateCriteria);
	 }

	/**
	 * The method will retrieve the Tier Definitions associated 
	 * with the particular Benefit.This is for detailed print from benefit node of benefit component.
	 * @param bnftTierDefinitionLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateForDetailedPrint(BenefitTierDefinitionLocateCriteria 
			bnftTierDefinitionLocateCriteria,User user) throws SevereException {
		BenefitDefinitionAdapterManager benefitDefinitionAdapterManager 
		= new BenefitDefinitionAdapterManager();
		return benefitDefinitionAdapterManager.locateBenefitTierDefinition
		(bnftTierDefinitionLocateCriteria);
		
	}


	/**
	 * @param benefitDefinitionId
	 * @throws SevereException 
	 */
	public void updateLineTermToSpsMapping(long benefitSysId, String userId) throws SevereException {
		
		StandardBenefitAdapterManager  benefitAdapterManager= new StandardBenefitAdapterManager();
		
		List termList=benefitAdapterManager.getTermListTobeInserted(benefitSysId);
		
		List  spsList= getSpsToTermMap(termList);
		
		insertNewTermSPSMappings(spsList,benefitAdapterManager,userId );
		
	}


	/**
	 * @param termList
	 * @return
	 */
	private List getSpsToTermMap(List termList) {
		
		List termSPSList = new ArrayList();
		for (Iterator iter = termList.iterator(); iter.hasNext();) {
			TermSpsBO termSpsBO = (TermSpsBO) iter.next();
			
			String[] terms=termSpsBO.getTermDesc().split(",");
			for (int i = 0; i < terms.length; i++) {
				TermSpsBO  newTermSPSBO= new TermSpsBO();
				
				newTermSPSBO.setTermDesc(termSpsBO.getTermDesc());
				
				newTermSPSBO.setSpsName(terms[i].trim());
				termSPSList.add(newTermSPSBO);
			}
		}
		return termSPSList;
	}


	/**
	 * @param spsList
	 * @throws SevereException 
	 */
	private void insertNewTermSPSMappings(List spsList,StandardBenefitAdapterManager  benefitAdapterManager, String userId ) throws SevereException {

		for (Iterator iter = spsList.iterator(); iter.hasNext();) {
			TermSpsBO termSpsBO = (TermSpsBO) iter.next();
			benefitAdapterManager.createTermToSpsMap(termSpsBO,userId);
		}
	}
	
}
 