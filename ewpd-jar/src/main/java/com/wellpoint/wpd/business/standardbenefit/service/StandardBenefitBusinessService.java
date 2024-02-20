/*
 * StandardBenefitBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.UncategorizedSQLException;

import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelTermLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitPopUpLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.DataTypeLocateCriteria;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitBusinessObjectBuilder;
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
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitNotesLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StandardBenefitViewAllLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.TierDefinitionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelPopUpBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitWrapperBO;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLevelPopUpRequest;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLineNotesAttachmentRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DataTypeRetrieveRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLineRequest;
import com.wellpoint.wpd.common.benefitlevel.request.NotesAttachmentRequestForBnftLine;
import com.wellpoint.wpd.common.benefitlevel.request.SaveBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SearchBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SearchBenefitLevelTermRequest;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelPopUpResponse;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLineNotesAttachmentResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DataTypeRetrieveResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLineResponse;
import com.wellpoint.wpd.common.benefitlevel.response.NotesAttachmentResponseForBnftLine;
import com.wellpoint.wpd.common.benefitlevel.response.SaveBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelTermResponse;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLevelVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitQualifierVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitTermVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.bo.GroupRule;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBenefitBO;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTierDefinitionAssnBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitTierDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.Question;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleBO;
import com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.request.AdminLevelRequest;
import com.wellpoint.wpd.common.standardbenefit.request.AdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.ApproveStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.BenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.BenefitQuestionNoteProcessRequest;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitTierDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionForDetailPrintRequest;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.GroupRuleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.HideQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitLevelListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateMandateListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateSelectedQuestionListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LookupAdminOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.NonAdjBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.PossibleAnswersRequest;
import com.wellpoint.wpd.common.standardbenefit.request.PublishStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RejectStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveAdministrationOptionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveNonAdjMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveOpenQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveQuestionaireRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RuleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.SaveQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.ScheduleForTestSBRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCheckInRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCheckOutRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCopyRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCreateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteVersionsRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitNoteAttachmentRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitRetrieveRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitSearchRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitUnLockRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitUpdateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitVersionsLifeCycleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TestFailStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TestPassStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TierLookUpRequest;
import com.wellpoint.wpd.common.standardbenefit.request.UpdateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.UpdateQuestionnaireRequest;
import com.wellpoint.wpd.common.standardbenefit.response.AdminLevelResponse;
import com.wellpoint.wpd.common.standardbenefit.response.AdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ApproveStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitQuestionNoteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitTierDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.GetBenefitTierDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.GroupRuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.HideQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitLevelListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateMandateListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateSelectedQuestionListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LookupAdminOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.NonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PossibleAnswersResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PublishStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RejectStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveAdministrationOptionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveNonAdjMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveOpenQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveQuestionaireResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.SaveQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ScheduleForTestSBResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckInResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckOutResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCopyResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitDeleteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitNoteAttachmentResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitSearchResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitUnLockResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitVersionsLifeCycleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestFailStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestPassStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TierLookUpResponse;
import com.wellpoint.wpd.common.standardbenefit.response.UpdateQuestionnaireResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitAdministrationVO;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitTierDefinitionVO;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitBusinessService extends WPDService {

    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }


    /**
     * This method sets the values from BenefitDefinitionRequest to
     * BenefitDefinitionBO for creating/updating a benefit definition
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitDefinitionRequest request)
            throws WPDException {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        Map params = new HashMap();
        BenefitDefinitionLocateCriteria criteria = new BenefitDefinitionLocateCriteria();
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        BenefitDefinitionBO benefitDefinitionBO = getBenefitDefinitionObject(request);
        
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        standardBenefitBO.setVersion(request.getBenefitDefinitionVO()
                .getMasterVersion());
        standardBenefitBO.setStandardBenefitKey(request
                .getBenefitDefinitionVO().getBenefitMasterSystemId());
        standardBenefitBO.setBenefitIdentifier(request.getBenefitDefinitionVO()
                .getBenefitIdentifier());
        standardBenefitBO.setParentSystemId(request.getBenefitDefinitionVO()
                .getStandardBenefitParentKey());
        standardBenefitBO.setStatus(request.getBenefitDefinitionVO()
                .getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBenefitDefinitionVO()
                .getBusinessDomains());

        BenefitDefinitionResponse benefitDefinitionResponse = (BenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.BENEFIT_DEFINITION_RESPONSE);

        // business validation for checking if the benefit definition entered
        // throught the jsp page
        // overlaps the date range of existing benefit definition
        benefitDefinitionResponse = (BenefitDefinitionResponse) (new ValidationServiceController()
                .execute(request));
        if (benefitDefinitionResponse != null
                && (benefitDefinitionResponse.getMessages() == null || benefitDefinitionResponse
                        .getMessages().size() == 0)) {
        	if (benefitDefinitionBO.getBenefitDefinitionMasterKey() != -1) {
                bom.persist(benefitDefinitionBO, standardBenefitBO, request
                        .getUser(), false);
                benefitDefinitionResponse.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_DEFN_UPDATE_SUCCESS));
                
                criteria.setBenefitDefinitionMasterKey(benefitDefinitionBO.getBenefitDefinitionMasterKey());
        		criteria.setAdminPresent(true);
        		LocateResults locateResults = bom.locate(criteria, request.getUser());
        		if(null!=locateResults){
        			benefitDefinitionBO = (BenefitDefinitionBO)locateResults.getLocateResults().get(0);
        			benefitDefinitionResponse.setBenefitDefinitionBO(benefitDefinitionBO);
        		}
            } else {
                bom.persist(benefitDefinitionBO, standardBenefitBO, request
                        .getUser(), true);
                benefitDefinitionResponse.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_DEFN_SAVE_SUCCESS));
                
                params.put(BusinessConstants.SUB_OBJECT_NAME,
	                    BusinessConstants.BenefitDefinitionBOImpl);
	            params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
	                    benefitDefinitionBO.getBenefitDefinitionMasterKey()));
	            standardBenefitBO = (StandardBenefitBO) bom.retrieve(
	                    standardBenefitBO, request.getUser(), params);
	            if(standardBenefitBO.getBenefitDefinition().getBenefitAdminId() == 0){
	            	standardBenefitBO.getBenefitDefinition().setBenefitAdminId(-1);
	            }
	            benefitDefinitionResponse.setBenefitDefinitionBO(standardBenefitBO
	                    .getBenefitDefinition());
            }
        }
    
        return benefitDefinitionResponse;
    }

    /**
	 * This method sets the values from RetrieveQuestionaireRequest to locate
	 * the questionnaire. It will locate either the parent questions or the
	 * child questions.
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveQuestionaireRequest request)
			throws ServiceException {
		try {
			
			Logger
					.logInfo("StandardBenefitBusinessService - Entering execute(RetrieveQuestionaireRequest request)");
			RetrieveQuestionaireResponse response = null;

			BusinessObjectManager bom = getBOM();

			SelectedQuestionaireLocateCriteria locateCriteria = new SelectedQuestionaireLocateCriteria();
			response = (RetrieveQuestionaireResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_QUESTIONNAIRE_RESPONSE);

			int action = request.getAction();
			switch (action) {
			case BusinessConstants.RETRIEVE_QUESTIONAIRE:
				locateCriteria
						.setAction(BusinessConstants.RETRIEVE_QUESTIONAIRE);
				locateCriteria.setAdminLevelOptionSysId(request
						.getAdminLevelOptionAssnSysId());
				locateCriteria.setAdminOptionId(request.getAdminOptionId());
				locateCriteria.setMaximumResultSize(request
						.getMaxSearchResultSize());
				locateCriteria.setBenefitId(request.getBenefitId());
				
				locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
				locateCriteria.setParentId(request.getParentId());
				
				break;
			case BusinessConstants.RETRIEVE_CHILD_QUESTIONAIRE:
				locateCriteria
						.setAction(BusinessConstants.RETRIEVE_CHILD_QUESTIONAIRE);
				locateCriteria.setQuestionareListIndex(request
						.getQuestionareListIndex());
				locateCriteria.setQuestionnareList(request
						.getQuestionnareList());
				locateCriteria.setAnswerId(request.getSelectedAnswerId());
				locateCriteria.setBenefitQuestionnaireAssnBO(request
						.getBenefitQuestionnaireAssnBO());
				locateCriteria.setAdminLevelOptionSysId(request
						.getAdminLevelOptionAssnSysId());
				locateCriteria.setBenefitId(request.getBenefitId());
				locateCriteria.setMaximumResultSize(request
						.getMaxSearchResultSize());
				
				locateCriteria.setAnswerDesc(request.getSelectedAnswerDesc());
		        locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
				break;
			}
			LocateResults locateResults = bom.locate(locateCriteria, request
					.getUser());
			response.setSelectedQuestionaireList(locateResults
					.getLocateResults());
			Logger
					.logInfo("StandardBenefitBusinessService - Exiting execute(RetrieveQuestionaireRequest request)");
			return response;
		} catch (WPDException ex) {
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveQuestionaireRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}

	/**
	 * This method will update the questionnaire taking in values from the
	 * UpdateQuestionnaireRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(UpdateQuestionnaireRequest request)
			throws ServiceException {
		
		Logger
				.logInfo("StandardBenefitBusinessService - Entering execute(UpdateQuestionnaireRequest request)");
		UpdateQuestionnaireResponse response = null;

		BusinessObjectManager bom = getBOM();

		List messageList = new ArrayList(2);

		StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
		standardBenefitBO.setStandardBenefitKey(request.getBenefitId());
		standardBenefitBO.setBenefitIdentifier(request
				.getMainObjectIdentifier());
		standardBenefitBO.setVersion(request.getVersionNumber());
		standardBenefitBO.setStatus(request.getStatus());
		standardBenefitBO.setBusinessDomains(request.getDomainList());
		standardBenefitBO.setParentSystemId(request.getParentSystemId());

		BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = new BenefitQuestionnaireAssnBO();
		benefitQuestionnaireAssnBO.setAdminLvlOptionAssnSysId(request
				.getAdminlevelOptionSysId());
		benefitQuestionnaireAssnBO.setQuestionnaireList(request
				.getQuestionnareList());
		benefitQuestionnaireAssnBO.setNewQuestions(request
				.getQuestionnaireListToAdd());
		benefitQuestionnaireAssnBO.setModifiedQuestions(request
				.getQuestionnaireListToUpdate());
		benefitQuestionnaireAssnBO.setRemovedQuestions(request
				.getQuestionnaireListToRemove());

		try {
			bom.persist(benefitQuestionnaireAssnBO, standardBenefitBO, request
					.getUser(), true);
			messageList.add(new InformationalMessage(
					BusinessConstants.SAVE_QUESTIONNAIRE_BENEFIT_SUCCESS));
		} catch (SevereException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		response = (UpdateQuestionnaireResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.UPDATE_QUESTIONNAIRE_RESPONSE);
		response.setMessages(messageList);
		Logger
				.logInfo("StandardBenefitBusinessService - Exiting execute(UpdateQuestionnaireRequest request)");
		return response;
	}

    /**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
    /**
     * Business Service for NonAdjBenefitMandate Getting values from
     * NonAdjBenefitMandateRequest and set it to StandardBenefitBO Check whether
     * save function or update function to takesplace and based on that
     * parameters are passing
     */
    public WPDResponse execute(NonAdjBenefitMandateRequest request)
            throws WPDException {
        NonAdjBenefitMandateResponse nonAdjBenefitMandateResponse = null;

        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        Map params = new HashMap();
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        BenefitMandateBO benefitMandateBO = getBenefitMandateObject(request);

        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setVersion(request.getNonAdjBenefitMandateVO()
                .getMasterVersion());
        standardBenefitBO.setStandardBenefitKey(request
                .getNonAdjBenefitMandateVO().getBenefitSystemId());
        standardBenefitBO.setBenefitIdentifier(request
                .getNonAdjBenefitMandateVO().getBenefitIdName());
        standardBenefitBO.setStatus(request.getNonAdjBenefitMandateVO()
                .getStandardBenefitStatus());
        standardBenefitBO.setParentSystemId(request.getNonAdjBenefitMandateVO()
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request
                .getNonAdjBenefitMandateVO().getBusinessDomains());

        nonAdjBenefitMandateResponse = (NonAdjBenefitMandateResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.NON_ADJ_BENEFIT_MANDATE_RESPONSE);
        // check for whether a mandate benefit is to be created/updated
        if (benefitMandateBO.getBenefitMandateId() == 0) {
            bom.persist(benefitMandateBO, standardBenefitBO, request.getUser(),
                    true);
            nonAdjBenefitMandateResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_MANDATE_INFO_SAVE_SUCCESS));
        } else {
            bom.persist(benefitMandateBO, standardBenefitBO, request.getUser(),
                    false);
            nonAdjBenefitMandateResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_MANDATE_INFO_UPDATE_SUCCESS));
        }
        params.put(BusinessConstants.SUB_OBJECT_NAME,
                BusinessConstants.BenefitMandateBOImpl);
        params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
                benefitMandateBO.getBenefitSystemId()));
        standardBenefitBO = (StandardBenefitBO) bom.retrieve(standardBenefitBO,
                request.getUser(), params);
        nonAdjBenefitMandateResponse.setBenefitMandateBO(standardBenefitBO
                .getBenefitMandateBOImpl());
        return nonAdjBenefitMandateResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitCreateRequest
     * @return
     * @throws WPDException
     * 
     * This method gets the values from StandardBenefitCreateRequest and sets
     * them to StandardBenefitBO so as to create a benefit
     *  
     */
    public WPDResponse execute(StandardBenefitCreateRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Create");
        try {
            StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
            BusinessObjectManager bom = getBOM();
            StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.STANDARD_BENEFIT_RESPONSE);

            /*
             * Calling the service controller class for business validations
             */
            standardBenefitResponse = (StandardBenefitResponse) new ValidationServiceController()
                    .execute(request);
            if(null != standardBenefitResponse){
	            if (standardBenefitResponse.isSuccess()) {
	                /*
	                 * Calling the persist method to create a benefit
	                 */
	            	try{
	                    /*
	                     * Checking Too many domain combinations
	                     */
	            		bom.persist(standardBenefitBO, request.getUser(), true);
	            	}catch(UncategorizedSQLException e){
	            		standardBenefitResponse = null;
	            		return standardBenefitResponse;
	            	}
	                
	                standardBenefitResponse.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_BNFT_SAVE_SUCCESS));
	            }
	            standardBenefitResponse.setDomainDetail(DomainUtil
	                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
	                            .getParentSystemId()));
	            standardBenefitResponse.setStandardBenefitBO(standardBenefitBO);
            }
            Logger
                    .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Create");
            return standardBenefitResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = BusinessConstants.MSG_ERROR;
            throw new ServiceException(logMessage, logParameters, ex);
        }

    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitUpdateRequest
     * @return
     * @throws WPDException
     * 
     * This method gets the values from StandardBenefitUpdateRequest and sets
     * them to StandardBenefitBO so as to update a benefit
     */
    public WPDResponse execute(StandardBenefitUpdateRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Update");
        try {
        	StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.STANDARD_BENEFIT_RESPONSE);
            BusinessObjectManager bom = getBOM();
            StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
            /*
             * Calling the service controler class for business validations
             */
            standardBenefitResponse = (StandardBenefitResponse) new ValidationServiceController()
                    .execute(request);
            if(null != standardBenefitResponse){
	            if (standardBenefitResponse.isSuccess()) {
	
	                StandardBenefitBO oldKeyStandardBenefitBO = new StandardBenefitBO();
	                StandardBenefitVO oldKeystandardBenefitVO = (StandardBenefitVO) request
	                        .getOldKeystandardBenefitVO();
	                oldKeyStandardBenefitBO
	                        .setStandardBenefitKey(oldKeystandardBenefitVO
	                                .getStandardBenefitKey());
	                oldKeyStandardBenefitBO
	                        .setBenefitIdentifier(oldKeystandardBenefitVO
	                                .getBenefitIdentifier());
	                oldKeyStandardBenefitBO
	                        .setParentSystemId(oldKeystandardBenefitVO
	                                .getStandardBenefitParentKey());
	                oldKeyStandardBenefitBO
	                        .setBusinessDomains(oldKeystandardBenefitVO
	                                .getBusinessDomains());
	                oldKeyStandardBenefitBO.setStatus(oldKeystandardBenefitVO
	                        .getStatus());
	                oldKeyStandardBenefitBO.setVersion(oldKeystandardBenefitVO
	                        .getVersion());
	                
	                if (isKeyValueChanged(standardBenefitBO,
	                        oldKeyStandardBenefitBO)) {
	                    bom.changeIdentity(oldKeyStandardBenefitBO,
	                            standardBenefitBO, request.getUser());
	                } else {
	                    bom.persist(standardBenefitBO, request.getUser(), false);
	                }
	                standardBenefitResponse.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_BNFT_UPDATE_SUCCESS));
	            }
	            standardBenefitResponse.setDomainDetail(DomainUtil
	                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
	                            .getParentSystemId()));
	
	            standardBenefitResponse.setStandardBenefitBO(standardBenefitBO);
            }
            Logger
                    .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Update");
            return standardBenefitResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = BusinessConstants.MSG_ERROR_UPDATE;
            throw new ServiceException(logMessage, logParameters, ex);
        }

    }


    /**
     * @param standardBenefitBO
     * @param oldKeyStandardBenefitBO
     * @return
     */
    private boolean isKeyValueChanged(StandardBenefitBO standardBenefitBO,
            StandardBenefitBO oldKeyStandardBenefitBO) {

        if (!standardBenefitBO.getBenefitIdentifier().equals(
                oldKeyStandardBenefitBO.getBenefitIdentifier()))
            return true;
        if (!BusinessUtil.isBuisnessDomainsEqual(standardBenefitBO
                .getBusinessDomains(), oldKeyStandardBenefitBO
                .getBusinessDomains()))
            return true;
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitRetrieveRequest
     * @return
     * @throws WPDException
     * 
     * This method gets the values from StandardBenefitRetrieveRequest and sets
     * them to StandardBenefitBO so as to retrieve a benefit
     */
    public WPDResponse execute(StandardBenefitRetrieveRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Retrieve");
        StandardBenefitResponse standardBenefitResponse = null;
        boolean lockAquired = true;
        List messages = new ArrayList(1);
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
        try {
        	 	standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                        standardBenefitBO, request.getUser());
        	 	
                standardBenefitResponse = (StandardBenefitResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.STANDARD_BENEFIT_RESPONSE);
                if(request.isEdit()){
        	 		lockAquired = bom.lock(standardBenefitBO, request.getUser());
        	 	}
        	 	standardBenefitResponse.setLockAcquired(lockAquired);
        	 	if(!lockAquired){
        	 		InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_LOCKED_USER);
                	message.setParameters(new String[] {request.getStandardBenefitVO().getBenefitIdentifier()});
                	messages.add(message);
                	standardBenefitResponse.setMessages(messages);
                	return standardBenefitResponse;
        	 	}
                standardBenefitResponse.setStandardBenefitBO(standardBenefitBO);
                standardBenefitResponse.setDomainDetail(DomainUtil
                        .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
                                .getParentSystemId()));
                //standardBenefitResponse.setLockAquired(lockAquired);
                Logger
                        .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Retrieve");
               
            	
            
            return standardBenefitResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from StandardBenefitDeleteRequest and sets
     * them to StandardBenefitBO so as to delete a benefit
     */
    public WPDResponse execute(StandardBenefitDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Delete");
        StandardBenefitDeleteResponse standardBenefitDeleteResponse = null;
        StandardBenefitSearchVO standardBenefitSearchVO = request
                .getStandardBenefitSearchVO();

		standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) WPDResponseFactory
		        .getResponse(WPDResponseFactory.STANDARD_BENEFIT_DELETE_RESPONSE);
		BusinessObjectManager bom = getBOM();
		StandardBenefitBO standardBenefitBO = getStandardBenefitDeleteObject(request);
		try {
		    /*
		     * Calling the persist method to create a benefit
		     */
			standardBenefitBO.setAction("DELETE");
		    boolean bool = bom.delete(standardBenefitBO, request.getUser());
		    if (bool) {
		        InformationalMessage informationalMessage = new InformationalMessage(
		                BusinessConstants.MSG_BNFT_DELETE_SUCCESS);
		        informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
		        standardBenefitDeleteResponse
		                .addMessage(informationalMessage);
		    } else {
		        ErrorMessage errorMessage = new ErrorMessage(
		                BusinessConstants.MSG_BNFT_DELETE_FAILURE);
		        errorMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
		        standardBenefitDeleteResponse.addMessage(errorMessage);
		    }
		    
		    LocateResults locateResults = null;
		    //Searching using a benefitkey is applicable only for view all
		    // version functionality.
		    if (standardBenefitSearchVO.getBenefitKey() > 0) {
		        StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
		        locateResults = getBOM()
		                .locate(standardBenefitViewAllLocateCriteria,
		                        request.getUser());
		    } else{
		    	StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
		        locateResults = getBOM().locate(standardBenefitLocateCriteria,
		                request.getUser());
		    }
		
		    List voList = new ArrayList();
		    if(null != locateResults.getLocateResults()&& locateResults.getLocateResults().size()>0){
		        Iterator iterator = locateResults.getLocateResults().iterator();
		        while (iterator.hasNext()) {
		            StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
		            standardBenefitBO = (StandardBenefitBO) iterator.next();
		            DomainDetail domainDetail=DomainUtil.retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
		                    .getParentSystemId());
		            standardBenefitVO.setDomainDetail(domainDetail);
		            standardBenefitVO.setBenefitIdentifier(standardBenefitBO
		                    .getBenefitIdentifier());
		            standardBenefitVO.setVersion(standardBenefitBO.getVersion());
		            if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
		            standardBenefitVO.setStatus(standardBenefitBO.getStatus());
		            standardBenefitVO.setState(standardBenefitBO.getState());
		            standardBenefitVO.setStandardBenefitKey(standardBenefitBO
		                    .getStandardBenefitKey());
		            standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
		                    .getParentSystemId());
		            standardBenefitVO.setBusinessDomains(standardBenefitBO
		                    .getBusinessDomains());
		            voList.add(standardBenefitVO);
		        }
		        locateResults.setLocateResults(voList);
		       
		        if (locateResults.getLocateResults().size() >= 50) {
		            standardBenefitDeleteResponse
		                    .addMessage(new InformationalMessage(
		                            BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
		        }
		        standardBenefitDeleteResponse.setSearchResultList(locateResults
		                .getLocateResults());
		    }
		} catch (WPDException ex) {
		    List logParameters = new ArrayList(1);
		    logParameters.add(request);
		    String logMessage = "Failed while processing StandardBenefitDeleteRequest";
		    throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
		        .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Delete");
		return standardBenefitDeleteResponse;
}


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from StandardBenefitDeleteVersionsRequest and
     * sets them to StandardBenefitBO so as to delete the versions of a benefit
     */
    public WPDResponse execute(StandardBenefitDeleteVersionsRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit DeleteVersions");
        StandardBenefitDeleteResponse standardBenefitDeleteResponse = null;
        standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_DELETE_RESPONSE);
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        try {
            /*
             * Calling the persist method to create a benefit
             */
            standardBenefitBO.setStandardBenefitKey(request
                    .getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(request
                    .getStandardBenefitName());
            standardBenefitBO.setParentSystemId(request
                    .getStandardBenefitParentKey());
            standardBenefitBO.setStatus(request.getStatus());
            standardBenefitBO.setVersion(request.getVersion());
            standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
            
            standardBenefitBO.setAction("DELETE");
            boolean bool = bom.delete(standardBenefitBO, request.getUser());
            if (bool) {
            	InformationalMessage informationalMessage = new InformationalMessage(BusinessConstants.MSG_BNFT_DELETE_SUCCESS);
            	informationalMessage.setParameters(new String[] {standardBenefitBO.getBenefitIdentifier()});
                standardBenefitDeleteResponse
                        .addMessage(informationalMessage);
            } else {
                standardBenefitDeleteResponse.addMessage(new ErrorMessage(
                        BusinessConstants.MSG_BNFT_DELETE_FAILURE));
            }
            LocateResults locateResults = null;
            StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
            standardBenefitViewAllLocateCriteria.setBenefitkey(request
                    .getStandardBenefitKey());
            standardBenefitViewAllLocateCriteria.setName(request
                    .getStandardBenefitName());

            // to get the remaining versions after deletin a particular version
            // of a benefit
            locateResults = getBOM().locate(
                    standardBenefitViewAllLocateCriteria, request.getUser());

            List voList = new ArrayList();
            if(null != locateResults.getLocateResults() && locateResults.getLocateResults().size()>0){
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
	                if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            /*
	             * Checks if the result that is obtained after performing a search
	             * is null or not
	             */
	            if (locateResults.getLocateResults().size() >= 50) {
	                standardBenefitDeleteResponse
	                        .addMessage(new InformationalMessage(
	                                BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
	            standardBenefitDeleteResponse.setSearchResultList(locateResults
	                    .getLocateResults());
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitDeleteRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit DeleteVersions");
        return standardBenefitDeleteResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from StandardBenefitVersionsLifeCycleRequest
     * and sets them to StandardBenefitBO so as to perform the life cycle
     * operations which include scheduled for test, test pass, test fail,
     * publish, approve and reject a benefit
     */
    public WPDResponse execute(StandardBenefitVersionsLifeCycleRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Versions LifeCycle");
        StandardBenefitVersionsLifeCycleResponse response = (StandardBenefitVersionsLifeCycleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_VERSIONS_LIFECYCLE_RESPONSE);
        BusinessObjectManager bom = getBOM();

        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO
                .setStandardBenefitKey(request.getStandardBenefitKey());
        standardBenefitBO
                .setBenefitIdentifier(request.getStandardBenefitName());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setVersion(request.getStandardBenefitVersion());
        try {
            // checks for the different life cycle operations by using
            // request.getAction()
            if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.SCHEDULE_FOR_TEST_STANDARD_BENEFIT) {
                response = (StandardBenefitVersionsLifeCycleResponse) new ValidationServiceController()
                        .execute(request);
	            if (null != response && response.isSuccess()) {
		            bom.scheduleForTest(standardBenefitBO, request.getUser());
		            response.addMessage(new InformationalMessage(
		                            WebConstants.SCHEDULE_FOR_TEST_BEN));
                }
            } else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.TEST_PASS_STANDARD_BENEFIT) {
                bom.testPass(standardBenefitBO, request.getUser());
                response.addMessage(new InformationalMessage(
                        WebConstants.TEST_PASS_BEN));
            } else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.TEST_FAIL_STANDARD_BENEFIT) {
                bom.testFail(standardBenefitBO, request.getUser());
                response.addMessage(new InformationalMessage(
                        WebConstants.TEST_FAIL_BEN));

            } else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.PUBLISH_STANDARD_BENEFIT) {
                bom.publish(standardBenefitBO, request.getUser());
                response.addMessage(new InformationalMessage(
                        WebConstants.BENEFIT_PUBLISH));
            } else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.APPROVE_STANDARD_BENEFIT) {
                bom.approve(standardBenefitBO, request.getUser());
                bom.publish(standardBenefitBO, request.getUser());
                InformationalMessage informationalMessage = new InformationalMessage(WebConstants.APPROVE_BEN);
                informationalMessage.setParameters(new String[] {standardBenefitBO.getBenefitIdentifier()});
                response.addMessage(informationalMessage);
            } else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.REJECT_STANDARD_BENEFIT) {
                bom.reject(standardBenefitBO, request.getUser());
                InformationalMessage informationalMessage = new InformationalMessage(WebConstants.REJECT_BEN);
				informationalMessage.setParameters(new String[] {standardBenefitBO.getBenefitIdentifier()});
                response.addMessage(informationalMessage);
            }else if (request.getAction() == StandardBenefitVersionsLifeCycleRequest.UNLOCK_STANDARD_BENEFIT) {
                bom.unlock(standardBenefitBO, request.getUser());
                InformationalMessage informationalMessage = new InformationalMessage(BusinessConstants.BENEFIT_UNLOCKED);
				informationalMessage.setParameters(new String[] {standardBenefitBO.getBenefitIdentifier()});
                response.addMessage(informationalMessage);
            }

            LocateResults locateResults = null;
            StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
            standardBenefitViewAllLocateCriteria.setBenefitkey(request
                    .getStandardBenefitKey());
            standardBenefitViewAllLocateCriteria.setName(request
                    .getStandardBenefitName());
            // to search for different benefits after performing the life cycle
            // operation
            locateResults = getBOM().locate(
                    standardBenefitViewAllLocateCriteria, request.getUser());

            List voList = new ArrayList();
            if(null != locateResults.getLocateResults() && locateResults.getLocateResults().size() >0){
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
	                if (standardBenefitBO.getDescription().length() > 30) {
                        String description = standardBenefitBO.getDescription();
                        description = description.substring(0, 30)
                                .concat("...");
                        standardBenefitBO.setDescription(description);
	                }
	                if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            /*
	             * Checks if the result that is obtained after performing a search
	             * is null or not
	             */
	            if (locateResults.getLocateResults().size() >= 50) {
	                response.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
	            response.setSearchResultList(locateResults.getLocateResults());
            }
            Logger
                    .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Versions LifeCycle");
            return response;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitVersionsLifeCycleRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from RetrieveBenefitDefinitionRequest and
     * sets them to StandardBenefitBO so as to retrieve the details of a
     * particular benefit
     */
    public WPDResponse execute(RetrieveBenefitDefinitionRequest request)
            throws WPDException {
        RetrieveBenefitDefinitionResponse retrieveBenefitDefinitionResponse = null;
        Map params = new HashMap();
        BusinessObjectManager bom = getBOM();

        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request
                .getBenefitMasterSystemId());
        standardBenefitBO.setVersion(request.getMainObjectVersion());
        standardBenefitBO.setBenefitIdentifier(request.getBenefitIdentifier());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());

        BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
        benefitDefinitionBO.setBenefitDefinitionMasterKey(request
                .getBenefitDefinitionMasterKey());
        retrieveBenefitDefinitionResponse = (RetrieveBenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_DEFINITION_RESPONSE);
        params.put(BusinessConstants.SUB_OBJECT_NAME,
                BusinessConstants.BenefitDefinitionBOImpl);
        params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
                benefitDefinitionBO.getBenefitDefinitionMasterKey()));
        standardBenefitBO = (StandardBenefitBO) bom.retrieve(standardBenefitBO,
                request.getUser(), params);
        
        BenefitDefinitionBO retrievedBenefitDefinitionBO = standardBenefitBO
        				.getBenefitDefinition();        
        
        // for retrieving tier definitions associated with each benefit definition
        StandardBenefitBusinessObjectBuilder stdBnftBusinessObjectBuilder 
        				= new StandardBenefitBusinessObjectBuilder();
        BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria 
						= new BenefitTierDefinitionLocateCriteria();
        bnftTierDefinitionLocateCriteria.setBenefitId(request
                .getBenefitMasterSystemId());
        bnftTierDefinitionLocateCriteria.setBenefitDefinitionId(
                		retrievedBenefitDefinitionBO.getBenefitDefinitionMasterKey());
        LocateResults bnftTierDefnlocateResults = stdBnftBusinessObjectBuilder
        				.locate(bnftTierDefinitionLocateCriteria,request.getUser());		            
        List bnftTierDefnList = bnftTierDefnlocateResults.getLocateResults();      
        
        if(null!=bnftTierDefnList && bnftTierDefnList.size()>0){		               
	        Iterator bnftTierDefnIterator = bnftTierDefnList.iterator();
	        StringBuffer tierDescriptionAndIdBuffer = new StringBuffer();
	        while(bnftTierDefnIterator.hasNext()){		    	          
	            BenefitDefinitionBO benefitTierDefinitionBO = 
	                (BenefitDefinitionBO)bnftTierDefnIterator.next();
	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDescription());
	            tierDescriptionAndIdBuffer.append("~");
	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDefinitionId());
	            if(bnftTierDefnIterator.hasNext()){	               
	                tierDescriptionAndIdBuffer.append("~");
	            }
	        }	     	        
	        retrievedBenefitDefinitionBO.setTierDefinitions(tierDescriptionAndIdBuffer.toString());
        }	        
        retrieveBenefitDefinitionResponse
                .setBenefitDefinitionBO(retrievedBenefitDefinitionBO);
        return retrieveBenefitDefinitionResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitCopyRequest
     * @return
     * @throws WPDException
     * 
     * This method gets the values from StandardBenefitCopyRequest and sets them
     * to StandardBenefitBO so as to copy the details of a particular benefit to
     * the newly created one
     */
    public WPDResponse execute(StandardBenefitCopyRequest request)
            throws ServiceException {
        HashMap mapParam = new HashMap();
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Copy");
        StandardBenefitCopyResponse standardBenefitCopyResponse = (StandardBenefitCopyResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_COPY_RESPONSE);

        StandardBenefitBO targetBO = getStandardBenefitCopyObject(request);
        StandardBenefitBO sourceBO = getStandardBenefitCopyObject(request);
        try {
            standardBenefitCopyResponse = (StandardBenefitCopyResponse) new ValidationServiceController()
                    .execute(request);
            if (null != standardBenefitCopyResponse) {
                if (standardBenefitCopyResponse.isSuccess()) {
                    getBOM().persist(targetBO, request.getUser(), true);
                    getBOM().copy(sourceBO, targetBO, request.getUser(),
                            mapParam);
                    InformationalMessage informationalMessage = new InformationalMessage(
                    	"benefit.copy.success");
                    informationalMessage.setParameters(new String[]{sourceBO.getBenefitIdentifier()});
                    standardBenefitCopyResponse
                            .addMessage(informationalMessage);
                }
                standardBenefitCopyResponse.setDomainDetail(DomainUtil
                        .retrieveDomainDetailInfo("stdbenefit", targetBO
                                .getParentSystemId()));
                standardBenefitCopyResponse.setStandardBenefitBO(targetBO);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCopyRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Copy");
        return standardBenefitCopyResponse;
    }


    /*
     * This method gets the values from StandardBenefitCopyRequest and sets them
     * to StandardBenefitBO so as to copy the details of a particular benefit to
     * the newly created one
     */
    public StandardBenefitBO getStandardBenefitCopyObject(
            StandardBenefitCopyRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Retrieve");
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        modifyDomainValues(standardBenefitVO);

        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitKey());

        List lineOfBusiness = standardBenefitVO.getLobList();
        List businessEntity = standardBenefitVO.getBusinessEntityList();
        List businessGroup = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = standardBenefitVO.getMarketBusinessUnitList();
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());
        standardBenefitBO.setVersion(standardBenefitVO.getVersion());
        //Start of new code for enhancement
        standardBenefitBO.setMandateType(standardBenefitVO.getMandateType());
        standardBenefitBO.setBenefitType(standardBenefitVO.getBenefitType());
        standardBenefitBO.setStateCode(standardBenefitVO.getStateId());
        standardBenefitBO.setStateDesc(standardBenefitVO.getStateDesc());
        standardBenefitBO.setRuleTypeList(standardBenefitVO.getRuleTypeList());
        standardBenefitBO.setBenefitCategory(standardBenefitVO.getBenefitCategory());
        //End of new code for enhancement
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Retrieve");
        return standardBenefitBO;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException *
     *             This method gets the values from
     *             LocateBenefitDefinitionRequest and sets them to
     *             BenefitDefinitionLocateCriteria so as to locate the
     *             definition details of a particular benefit
     */

    public WPDResponse execute(LocateBenefitDefinitionRequest request)
            throws WPDException {
        LocateBenefitDefinitionResponse locateBenefitDefinitionResponse = null;
        BusinessObjectManager bom = getBOM();
        BenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria 
        		= new BenefitDefinitionLocateCriteria();
        locateBenefitDefinitionResponse = (LocateBenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFIT_DEFINITION_RESPONSE);
        benefitDefinitionLocateCriteria.setBenefitMasterSystemId(request
                .getBenefitMasterSystemId());
        benefitDefinitionLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        LocateResults locateResults = bom.locate(
                benefitDefinitionLocateCriteria, request.getUser());
        List list = locateResults.getLocateResults();
        
        StandardBenefitBusinessObjectBuilder stdBnftBusinessObjectBuilder 
        				= new StandardBenefitBusinessObjectBuilder();
        if(null!=list && list.size()>0){
	        Iterator iterator = list.iterator();
	        while(iterator.hasNext()){
	            BenefitDefinitionBO benefitDefinitionBO = (BenefitDefinitionBO)iterator.next();	
	            if(null!=benefitDefinitionBO){
	                
	                // for retrieving tier definitions associated with each benefit definition
		            BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria 
									= new BenefitTierDefinitionLocateCriteria();
		            bnftTierDefinitionLocateCriteria.setBenefitDefinitionId(benefitDefinitionBO
		                    .getBenefitDefinitionMasterKey());
		            LocateResults bnftTierDefnlocateResults = stdBnftBusinessObjectBuilder
    				.retrieveTierDefnForBenDefn(bnftTierDefinitionLocateCriteria,request.getUser());	
		            List bnftTierDefnList = bnftTierDefnlocateResults.getLocateResults();
		            List tierDefnDescriptionList = new ArrayList();
		            
		            if(null!=bnftTierDefnList && bnftTierDefnList.size()>0){		               
		    	        Iterator bnftTierDefnIterator = bnftTierDefnList.iterator();
		    	        while(bnftTierDefnIterator.hasNext()){		    	          
		    	            BenefitDefinitionBO benefitTierDefinitionBO = 
		    	                (BenefitDefinitionBO)bnftTierDefnIterator.next();		    	            
		    	            tierDefnDescriptionList.add(benefitTierDefinitionBO.getTierDescription());		    	            
		    	        }
		    	        String tierDescription = WPDStringUtil
		    	        			.getCommaSeperatedValuesFromList(tierDefnDescriptionList);		    	       
		    	        benefitDefinitionBO.setTierDefinitions(tierDescription);
		            }	                
		            if(null != benefitDefinitionBO.getDescription() 
		                    && benefitDefinitionBO.getDescription().length() > 30){
		            	if(! request.isFlag()){
		                    benefitDefinitionBO.setDescription(benefitDefinitionBO.getDescription());	
		            	}else{
		            		benefitDefinitionBO.setDescription(benefitDefinitionBO.getDescription());
		            	}
		
		            }else
		                benefitDefinitionBO.setDescription(benefitDefinitionBO.getDescription());
		         }
	        }
        
        locateBenefitDefinitionResponse.setBenefitDefinitionList(locateResults
                .getLocateResults());
        }
        return locateBenefitDefinitionResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from LocateBenefitAdministrationRequest and
     * sets them to BenefitAdministrationLocateCriteria so as to locate the
     * administration details of a particular benefit
     */
    public WPDResponse execute(LocateBenefitAdministrationRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Benefit Administration Locate");
        LocateBenefitAdministrationResponse locateBenefitAdministrationResponse = null;
        BusinessObjectManager bom = getBOM();
        BenefitAdministrationLocateCriteria benefitAdministrationLocateCriteria = new BenefitAdministrationLocateCriteria();
        locateBenefitAdministrationResponse = (LocateBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFIT_ADMINISTRATION_RESPONSE);
        try {
            benefitAdministrationLocateCriteria.setBenefitDefinitionKey(request
                    .getBenefitDefinitionKey());
            benefitAdministrationLocateCriteria.setMaximumResultSize(request
                    .getMaxSearchResultSize());
            LocateResults locateResults = bom.locate(
                    benefitAdministrationLocateCriteria, request.getUser());
            locateBenefitAdministrationResponse
                    .setBenefitAdministrationsList(locateResults
                            .getLocateResults());
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Benefit Administration Locate");
        return locateBenefitAdministrationResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from DeleteBenefitDefinitionRequest and sets
     * them to BenefitDefinitionBO so as to delete the definition details of a
     * particular benefit
     */
    public WPDResponse execute(DeleteBenefitDefinitionRequest request)
            throws WPDException {
        DeleteBenefitDefinitionResponse deleteBenefitDefinitionResponse = null;
        BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
        /*benefitDefinitionBO.setBenefitDefinitionMasterKey(request
                .getBenefitDefinitionMasterKey());*/
        benefitDefinitionBO.setBenefitDefinitionKeys
						(request.getBenefitDefenitionKeys());
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setVersion(request.getVersion());
        standardBenefitBO.setStandardBenefitKey(request
                .getStandardBenefitMasterKey());
        standardBenefitBO.setBenefitIdentifier(request.getBenefitIdentifier());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());

        BusinessObjectManager bom = getBOM();

        deleteBenefitDefinitionResponse = (DeleteBenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_DEFINITION_RESPONSE);

        bom.delete(benefitDefinitionBO, standardBenefitBO, request.getUser());

        deleteBenefitDefinitionResponse.addMessage(new InformationalMessage(
                BusinessConstants.MSG_BENEFIT_DEFN_DELETE_SUCCESS));
        return deleteBenefitDefinitionResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from LocateBenefitMandateRequest and sets
     * them to BenefitMandateLocateCriteria so as to locate the mandate details
     * of a particular benefit
     */
    public WPDResponse execute(LocateBenefitMandateRequest request)
            throws WPDException {
        LocateBenefitMandateResponse locateBenefitMandateResponse = null;
        BusinessObjectManager bom = getBOM();
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
        locateBenefitMandateResponse = (LocateBenefitMandateResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFIT_MANDATE_RESPONSE);
        benefitMandateLocateCriteria.setBenefitMasterSystemId(request
                .getBenefitMasterSystemId());
        benefitMandateLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        LocateResults locateResults = bom.locate(benefitMandateLocateCriteria,
                request.getUser());
        locateBenefitMandateResponse.setBenefitMandateList(locateResults
                .getLocateResults());
        return locateBenefitMandateResponse;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitSearchRequest
     * @return
     * @throws WPDException
     * 
     * This method sets the criteria for the search functionality in to a
     * standardBenefitLocateCriteria and passes it to the builder
     */
    public WPDResponse execute(StandardBenefitSearchRequest request)
    throws ServiceException {
		Logger
		        .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Search");
		StandardBenefitSearchResponse standardBenefitSearchResponse = null;
		StandardBenefitSearchVO standardBenefitSearchVO = request
		        .getStandardBenefitSearchVO();
		standardBenefitSearchResponse = (StandardBenefitSearchResponse) WPDResponseFactory
		        .getResponse(WPDResponseFactory.SB_SEARCH_RESPONSE);
		
		
		
		LocateResults locateResults = null;
		//Searching using a benefitkey is applicable only for view all version
		// functionality.
		try {
		    if (standardBenefitSearchVO.getBenefitKey() > 0) {
		        StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
		        standardBenefitViewAllLocateCriteria
		                .setBenefitkey(standardBenefitSearchVO.getBenefitKey());
		        locateResults = getBOM()
		                .locate(standardBenefitViewAllLocateCriteria,
		                        request.getUser());
		    } else{
		    	StandardBenefitLocateCriteria standardBenefitLocateCriteria = new StandardBenefitLocateCriteria();
		    	standardBenefitLocateCriteria
		        .setBusinessEntityCodeList(standardBenefitSearchVO
		                .getBusinessEntityCodeList());
		        standardBenefitLocateCriteria
		                .setBusinessGroupCodeList(standardBenefitSearchVO
		                        .getBusinessGroupCodeList());
		        standardBenefitLocateCriteria
						.setMarketBusinessUnitList(standardBenefitSearchVO
								.getMarketBusinessUnitList());
		        standardBenefitLocateCriteria.setLobCodeList(standardBenefitSearchVO
		                .getLobCodeList());
		        standardBenefitLocateCriteria
		                .setName(BusinessUtil.escpeSpecialCharacters(standardBenefitSearchVO.getName()));
		        standardBenefitLocateCriteria
		                .setProviderArrangementCodeList(standardBenefitSearchVO
		                        .getProviderArrangementCodeList());
		        standardBenefitLocateCriteria
		                .setDataTypeCodeList(standardBenefitSearchVO
		                        .getDataTypeCodeList());
		        standardBenefitLocateCriteria
		                .setQualifierCodeList(standardBenefitSearchVO
		                        .getQualifierCodeList());
		        standardBenefitLocateCriteria.setTermCodeList(standardBenefitSearchVO
		                .getTermCodeList());
		        //As part of Enhancement
		        standardBenefitLocateCriteria
		                .setBenefitTypeCode(standardBenefitSearchVO
		                        .getBenefitTypeCode());
		        standardBenefitLocateCriteria
						.setBenefitCategory(standardBenefitSearchVO
								.getBenefitCategory());
		        standardBenefitLocateCriteria
		                .setMaximumResultSize(standardBenefitSearchVO
		                        .getMaxSearchResultSize());
		        locateResults = getBOM().locate(standardBenefitLocateCriteria,
		                request.getUser());
		    }
		    List voList = new ArrayList();
		    List list = locateResults.getLocateResults();
		    if(null != list && list.size() >0){
		        Iterator iterator = list.iterator();
		        while (iterator.hasNext()) {
		            StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
		            StandardBenefitBO standardBenefitBO = (StandardBenefitBO) iterator
		                    .next();
//		            DomainDetail domainDetail=DomainUtil
//		                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
//		                            .getParentSystemId());
		            DomainDetail domainDetail=standardBenefitBO.getDomainDetail();
		            standardBenefitVO.setDomainDetail(domainDetail);
		            standardBenefitVO.setBenefitIdentifier(standardBenefitBO
		                    .getBenefitIdentifier());
		            standardBenefitVO.setVersion(standardBenefitBO.getVersion());
		            if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
		            standardBenefitVO.setStatus(standardBenefitBO.getStatus());
		            standardBenefitVO.setState(standardBenefitBO.getState());
		            standardBenefitVO.setStandardBenefitKey(standardBenefitBO
		                    .getStandardBenefitKey());
		            standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
		                    .getParentSystemId());
		            standardBenefitVO.setBusinessDomains(standardBenefitBO
		                    .getBusinessDomains());
		            voList.add(standardBenefitVO);
		        }
		        locateResults.setLocateResults(voList);
		        /*
		         * Checks if the result that is obtained after performing a search
		         * is null or not
		         */
		        if (locateResults.getLocateResults().size() >= 50) {
		            standardBenefitSearchResponse
		                    .addMessage(new InformationalMessage(
		                            BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
		        }
		        standardBenefitSearchResponse.setSearchResultList(locateResults
		                .getLocateResults());
		    }
		} catch (WPDException ex) {
		    List logParameters = new ArrayList(1);
		    logParameters.add(request);
		    String logMessage = "Failed while processing StandardBenefitSearchRequest";
		    throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
		        .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Search");
		return standardBenefitSearchResponse;
}


    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     * 
     * This method gets the values from CreateBenefitAdministrationRequest and
     * sets them to BenefitAdministrationBO so as to create the Administration
     * details of a particular benefit
     */
    public WPDResponse execute(CreateBenefitAdministrationRequest request)
            throws ServiceException {

        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Benefit Administration Create");
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CREATE_BENEFIT_ADMINISTRATION_RESPONSE);
        createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) (new ValidationServiceController()
                .execute(request));

        Map params = new HashMap();
        if (null != createBenefitAdministrationResponse && createBenefitAdministrationResponse.isSuccess()) { 
            BenefitAdministrationBO benefitAdministrationBO = new BenefitAdministrationBO();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

            BenefitAdministrationVO benefitAdministrationVO = (BenefitAdministrationVO) request
                    .getBenefitAdministrationVO();
            if(null == benefitAdministrationVO.getDescription()
            		|| benefitAdministrationVO.getDescription().equals(WebConstants.EMPTY_STRING)){
            	benefitAdministrationBO.setDescription(" ");
            }else{
            benefitAdministrationBO.setDescription(benefitAdministrationVO
                    .getDescription());
            }
            benefitAdministrationBO.setEffectiveDate(benefitAdministrationVO
                    .getEffectiveDate());
            benefitAdministrationBO.setExpiryDate(benefitAdministrationVO
                    .getExpiryDate());
            benefitAdministrationBO
                    .setBenefitDefinitionKey(benefitAdministrationVO
                            .getBenefitDefinitionKey());
            benefitAdministrationBO
                    .setBenefitAdministrationKey(benefitAdministrationVO
                            .getBenefitAdministrationKey());

            standardBenefitBO.setStandardBenefitKey(benefitAdministrationVO
                    .getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(benefitAdministrationVO
                    .getStandardBenefitName());
            standardBenefitBO.setVersion(benefitAdministrationVO
                    .getStandardBenefitVersion());
            standardBenefitBO.setStatus(benefitAdministrationVO
                    .getStandardBenefitStatus());
            standardBenefitBO.setParentSystemId(benefitAdministrationVO
                    .getStandardBenefitParentKey());
            standardBenefitBO.setBusinessDomains(benefitAdministrationVO
                    .getBusinessDomains());

            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();

            try {
                if (benefitAdministrationBO.getBenefitAdministrationKey() != -1) {
                    bom.persist(benefitAdministrationBO, standardBenefitBO,
                            request.getUser(), false);
                    createBenefitAdministrationResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.MSG_BENEFIT_ADMN_UPDATE_SUCCESS));
                } else {
                    bom.persist(benefitAdministrationBO, standardBenefitBO,
                            request.getUser(), true);
                    createBenefitAdministrationResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.MSG_BENEFIT_ADMN_SAVE_SUCCESS));
                }
                params.put(BusinessConstants.SUB_OBJECT_NAME,
                        BusinessConstants.BenefitDefinitionBOImpl);
                params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
                		benefitAdministrationBO.getBenefitDefinitionKey()));
                standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                        standardBenefitBO, request.getUser(), params);
            } catch (WPDException ex) {
                List logParameters = new ArrayList(1);
                logParameters.add(request);
                String logMessage = "Failed while processing CreateBenefitAdministrationRequest";
                throw new ServiceException(logMessage, logParameters, ex);
            }
            createBenefitAdministrationResponse
                    .setBenefitAdministrationBO(benefitAdministrationBO);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Benefit Administration Create");
        return createBenefitAdministrationResponse;

    }


    /**
     * Deletes the selected Benefit Mandate from the datatable
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return deleteBenefitMandateResponse
     * @throws ServiceException
     * 
     * This method gets the values from DeleteBenefitMandateRequest and sets
     * them to BenefitMandateBO so as to delete the mandate details of a
     * particular benefit
     */
    public WPDResponse execute(DeleteBenefitMandateRequest request)
            throws WPDException {
        DeleteBenefitMandateResponse deleteBenefitMandateResponse = null;
        BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
        benefitMandateBO.setBenefitMandateId(request.getMandateMasterKey());

        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setVersion(request.getVersion());
        standardBenefitBO.setStandardBenefitKey(request
                .getStandardBenefitMasterKey());
        standardBenefitBO.setBenefitIdentifier(request.getMandateIdentifier());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());

        BusinessObjectManager bom = getBOM();
        deleteBenefitMandateResponse = (DeleteBenefitMandateResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_MANDATE_RESPONSE);
        bom.delete(benefitMandateBO, standardBenefitBO, request.getUser());
        deleteBenefitMandateResponse.addMessage(new InformationalMessage(
                BusinessConstants.MSG_BENEFIT_MANDATE_DELETE_SUCCESS));
        return deleteBenefitMandateResponse;
    }


    /**
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return RetrieveOpenQuestionResponse
     * @throws ServiceException
     * 
     * This method gets the values from RetrieveOpenQuestionRequest and sets
     * them to QuestionLocateCriteria so as to retrieve the open questions
     */
    public WPDResponse execute(RetrieveOpenQuestionRequest request)
            throws WPDException {
        RetrieveOpenQuestionResponse retrieveOpenQuestionResponse = new RetrieveOpenQuestionResponse();
        BusinessObjectManager bom = getBOM();
        QuestionLocateCriteria questionLocateCriteria = request
                .getOpenQuestionLocateCriteria();
        LocateResults locateResults = bom.locate(questionLocateCriteria,
                request.getUser());
        List locateResultList = locateResults.getLocateResults();
        retrieveOpenQuestionResponse.setOpenQuestionList(locateResultList);
        return retrieveOpenQuestionResponse;
    }


    /**
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return SaveQuestionResponse
     * @throws ServiceException
     * 
     * This method gets the values from SaveQuestionRequest and sets them to
     * SelectedQuestionListBO so as to save the selected questions
     */
    public WPDResponse execute(SaveQuestionRequest request) throws WPDException {
    	 SaveQuestionResponse saveQuestionResponse = (SaveQuestionResponse) WPDResponseFactory
		 		.getResponse(WPDResponseFactory.UPDATE_QUESTION_RESPONSE);
        if (request.getAction() == 1) {
            //save
            SelectedQuestionListBO selectedQuestionListBO = new SelectedQuestionListBO();
            List questionList = request.getQuestion();
            List selectedBOList = new ArrayList();
            Iterator iter = questionList.iterator();
            while (iter.hasNext()) {
                Question question = (Question) iter.next();
                SelectedQuestionListBO selectedQuestionListBOTemp = new SelectedQuestionListBO();
                selectedQuestionListBOTemp.setAdminOptionsSysId(question
                        .getAdminOptionSysId());
                selectedQuestionListBOTemp.setSystemId(question.getSystemId());
                selectedQuestionListBOTemp.setQuestionDesc(question
                        .getQuestion());
                selectedQuestionListBOTemp.setQuestionNumber(new Integer(
                        question.getQuestionNumber()).intValue());
                selectedQuestionListBOTemp.setSequenceNumber(new Integer(
                        question.getSeqNumber()).intValue());
                selectedQuestionListBOTemp.setReferenceId(question.getReferenceId());
                selectedQuestionListBOTemp.setIsOpen(question.getIsOpen());
                selectedQuestionListBOTemp.setAnswerId(question.getAnswerId());
                selectedBOList.add(selectedQuestionListBOTemp);
            }

            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setStandardBenefitKey(request
                    .getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(request
                    .getBenefitIdentifier());
            standardBenefitBO.setVersion(request.getVersion());
            standardBenefitBO.setStatus(request.getStandardBenefitStatus());
            standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
            standardBenefitBO.setParentSystemId(request
                    .getStandardBenefitParentKey());
            selectedQuestionListBO.setQuestionList(selectedBOList);
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);

            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            bom.persist(selectedQuestionListBO, standardBenefitBO, request
                    .getUser(), true);

        } else if (request.getAction() == 2) {
            //update
            // get the the business object manager object
            BusinessObjectManager bom = getBOM();
            // create the main object
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            // set the required things to the main object
            standardBenefitBO.setStandardBenefitKey(request
                    .getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(request
                    .getBenefitIdentifier());
            standardBenefitBO.setVersion(request.getVersion());
            standardBenefitBO.setStatus(request.getStandardBenefitStatus());
            standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
            standardBenefitBO.setParentSystemId(request
                    .getStandardBenefitParentKey());
            // get the list of bo from the request
            List updatedSeqList = request.getQuestion();
            // create the SelectedQuesionListBO and set all the details to that
            SelectedQuestionListBO questionListBO = new SelectedQuestionListBO();
            // set the list inside the selectedQuestionListBO
            questionListBO.setQuestionList(updatedSeqList);
            // call the persit method of the bom
            bom.persist(questionListBO, standardBenefitBO, request.getUser(),
                    false);
            // create the response object
           
            // add the message to the response
            saveQuestionResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_BENEFIT_QUESTION_UPDATE_SUCCESS));
        }

        return saveQuestionResponse;
    }


    /**
     * Creates BenefitAdministrationObject
     * 
     * @param request
     * @return benefitDefinitionBO
     * 
     * This method gets the values from CreateBenefitAdministrationRequest and
     * sets them to BenefitAdministrationBO
     */
    public BenefitAdministrationBO getBenefitAdministrationObject(
            CreateBenefitAdministrationRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getBenefitAdministrationObject(): CreateBenefitAdministrationRequest");
        BenefitAdministrationBO benefitAdministrationBO = new BenefitAdministrationBO();
        benefitAdministrationBO.setDescription(request
                .getBenefitAdministrationVO().getDescription());
        benefitAdministrationBO.setEffectiveDate(request
                .getBenefitAdministrationVO().getEffectiveDate());
        benefitAdministrationBO.setExpiryDate(request
                .getBenefitAdministrationVO().getExpiryDate());
        benefitAdministrationBO.setBenefitDefinitionKey(request
                .getBenefitAdministrationVO().getBenefitDefinitionKey());
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getBenefitAdministrationObject(): CreateBenefitAdministrationRequest");
        return benefitAdministrationBO;

    }


    /**
     * Creates BenefitAdministrationObject
     * 
     * @param request
     * @return benefitDefinitionBO
     * 
     * This method gets the values from UpdateBenefitAdministrationRequest and
     * sets them to BenefitAdministrationBO
     */
    public BenefitAdministrationBO getBenefitAdministrationObject(
            UpdateBenefitAdministrationRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getBenefitAdministrationObject(): UpdateBenefitAdministrationRequest");
        BenefitAdministrationBO benefitAdministrationBO = new BenefitAdministrationBO();
        benefitAdministrationBO.setDescription(request
                .getBenefitAdministrationVO().getDescription());
        benefitAdministrationBO.setEffectiveDate(request
                .getBenefitAdministrationVO().getEffectiveDate());
        benefitAdministrationBO.setExpiryDate(request
                .getBenefitAdministrationVO().getExpiryDate());
        benefitAdministrationBO.setBenefitDefinitionKey(request
                .getBenefitAdministrationVO().getBenefitDefinitionKey());
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getBenefitAdministrationObject(): UpdateBenefitAdministrationRequest");
        return benefitAdministrationBO;

    }


    /**
     * Creates standardBenefitBO from a StandardBenefitCreateRequest via a
     * StandardBenefitVO.
     * 
     * @param StandardBenefitCreateRequest
     * @return standardBenefitBO
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitCreateRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getStandardBenefitObject(): StandardBenefitCreateRequest");
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        modifyDomainValues(standardBenefitVO);

        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitKey());

        //New fields for enhancement
        standardBenefitBO.setMandateType(standardBenefitVO.getMandateType());
        standardBenefitBO.setBenefitType(standardBenefitVO.getBenefitType());

        List lineOfBusiness = standardBenefitVO.getLobList();
        List businessEntity = standardBenefitVO.getBusinessEntityList();
        List businessGroup = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = standardBenefitVO.getMarketBusinessUnitList();
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());

        //New fields for enhancement
        standardBenefitBO.setStateCode(standardBenefitVO.getStateId());
        standardBenefitBO.setStateDesc(standardBenefitVO.getStateDesc());
        standardBenefitBO.setRuleTypeList(standardBenefitVO.getRuleTypeList());
        
        //Enhancement: Benefit Category
        standardBenefitBO.setBenefitCategory(standardBenefitVO.getBenefitCategory());

        Logger
                .logInfo("StandardBenefitBusinessService - Returning getStandardBenefitObject(): StandardBenefitCreateRequest");
        return standardBenefitBO;
    }


    /**
     * Creates standardBenefitBO from a StandardBenefitDeleteRequest via a
     * StandardBenefitVO.
     * 
     * @param StandardBenefitDeleteRequest
     * @return standardBenefitBO
     */
    public StandardBenefitBO getStandardBenefitDeleteObject(
            StandardBenefitDeleteRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getStandardBenefitDeleteObject(): StandardBenefitDeleteRequest");
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setStatus(standardBenefitVO.getStatus());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(standardBenefitVO
                .getBusinessDomains());
        standardBenefitBO.setVersion(standardBenefitVO.getVersion());
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getStandardBenefitDeleteObject(): StandardBenefitDeleteRequest");
        return standardBenefitBO;
    }


    /**
     * Gets the domain values from StandardBenefitVO and if any list contains a
     * value as 'ALL' then the whole list is replaced with a single entry 'ALL'
     * 
     * @param StandardBenefitVO
     *  
     */
    private void modifyDomainValues(StandardBenefitVO standardBenefitVO) {
        Iterator iter = null;
        List lineOfBusiness = standardBenefitVO.getLobList();
        List businessEntity = standardBenefitVO.getBusinessEntityList();
        List businessGroup = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = standardBenefitVO.getMarketBusinessUnitList();
        
        String item = null;
        for (iter = lineOfBusiness.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if ("ALL".equals(item)) {
                lineOfBusiness.clear();
                lineOfBusiness.add("ALL");
                break;
            }
        }
        for (iter = businessEntity.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if ("ALL".equals(item)) {
                businessEntity.clear();
                businessEntity.add("ALL");
                break;
            }
        }
        for (iter = businessGroup.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if ("ALL".equals(item)) {
                businessGroup.clear();
                businessGroup.add("ALL");
                break;
            }
        }
        for (iter = marketBusinessUnit.iterator(); iter.hasNext();) {
            item = (String) iter.next();
            if ("ALL".equals(item)) {
            	marketBusinessUnit.clear();
            	marketBusinessUnit.add("ALL");
                break;
            }
        }
    }


    /**
     * Creates standardBenefitBO from a StandardBenefitUpdateRequest via a
     * StandardBenefitVO.
     * 
     * @param request
     * @return standardBenefitBO
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitUpdateRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getStandardBenefitObject(): StandardBenefitUpdateRequest");
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        modifyDomainValues(standardBenefitVO);

        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitParentKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setDescription(standardBenefitVO.getDescription());

        List lineOfBusiness = standardBenefitVO.getLobList();
        List businessEntity = standardBenefitVO.getBusinessEntityList();
        List businessGroup = standardBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = standardBenefitVO.getMarketBusinessUnitList();
        standardBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));

        standardBenefitBO.setTermList(standardBenefitVO.getTermList());
        standardBenefitBO
                .setQualifierList(standardBenefitVO.getQualifierList());
        standardBenefitBO.setDataTypeList(standardBenefitVO.getDataTypeList());
        standardBenefitBO.setPVAList(standardBenefitVO.getPVAList());
        standardBenefitBO.setStatus(standardBenefitVO.getStatus());
        standardBenefitBO.setVersion(standardBenefitVO.getVersion());

        // Copying the additional fields from the VO to the BO
        standardBenefitBO.setBenefitType(standardBenefitVO.getBenefitType());
        standardBenefitBO.setMandateType(standardBenefitVO.getMandateType());
        standardBenefitBO.setRuleTypeList(standardBenefitVO.getRuleTypeList());
        standardBenefitBO.setStateCode(standardBenefitVO.getStateId());
        standardBenefitBO.setStateDesc(standardBenefitVO.getStateDesc());
                //Enhancement: Benefit Category
        standardBenefitBO.setBenefitCategory(standardBenefitVO.getBenefitCategory());

        Logger
                .logInfo("StandardBenefitBusinessService - Returning getStandardBenefitObject(): StandardBenefitUpdateRequest");
        return standardBenefitBO;
    }


    /**
     * Creates standardBenefitBO from a StandardBenefitRetrieveRequest via a
     * StandardBenefitVO.
     * 
     * @param request
     * @return standardBenefitBO
     */
    public StandardBenefitBO getStandardBenefitObject(
            StandardBenefitRetrieveRequest request) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getStandardBenefitObject(): StandardBenefitRetrieveRequest");
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request.getStandardBenefitVO()
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(request.getStandardBenefitVO()
                .getBenefitIdentifier());
        standardBenefitBO.setParentSystemId(request.getStandardBenefitVO()
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request.getStandardBenefitVO()
                .getBusinessDomains());
        standardBenefitBO.setStatus(request.getStandardBenefitVO().getStatus());
        standardBenefitBO.setVersion(request.getStandardBenefitVO()
                .getVersion());
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getStandardBenefitObject(): StandardBenefitRetrieveRequest");
        return standardBenefitBO;
    }


    /**
     * Creates benefitDefinitionObject from BenefitDefinitionRequest
     * 
     * @param request
     * @return benefitDefinitionBO
     */
    public BenefitDefinitionBO getBenefitDefinitionObject(
            BenefitDefinitionRequest request) {
        BenefitDefinitionBO benefitDefinitionBO = new BenefitDefinitionBO();
        benefitDefinitionBO.setDescription(request.getBenefitDefinitionVO()
                .getDescription());
        benefitDefinitionBO.setEffectiveDate(request.getBenefitDefinitionVO()
                .getEffectiveDate());
        benefitDefinitionBO.setExpiryDate(request.getBenefitDefinitionVO()
                .getExpiryDate());
        benefitDefinitionBO.setBenefitDefinitionMasterKey(request
                .getBenefitDefinitionVO().getMasterKeyUsedForUpdate());
        benefitDefinitionBO.setBenefitMasterSystemId(request
                .getBenefitDefinitionVO().getBenefitMasterSystemId());      
        return benefitDefinitionBO;

    }


    /**
     * Setting all the values from NonAdjBenefitMandateRequest to
     * benefitMandateBO
     * 
     * @param request
     * @return
     */
    public BenefitMandateBO getBenefitMandateObject(
            NonAdjBenefitMandateRequest request) {
        BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
        benefitMandateBO.setBenefitSystemId(request.getNonAdjBenefitMandateVO()
                .getBenefitSystemId());
        benefitMandateBO.setBenefitMandateId(request
                .getNonAdjBenefitMandateVO().getBenefitMandateId());
        //enhancement
        benefitMandateBO.setBenefitStateCodeList(request
                .getNonAdjBenefitMandateVO().getStateCode());
        benefitMandateBO.setMandateCategory(request.getNonAdjBenefitMandateVO()
                .getMandateCategoy());
        benefitMandateBO.setFundingArrangement(request
                .getNonAdjBenefitMandateVO().getFundingArrangement());
        benefitMandateBO.setEffectiveness(request.getNonAdjBenefitMandateVO()
                .getEffectiveness());
        benefitMandateBO.setText(request.getNonAdjBenefitMandateVO().getText());
        benefitMandateBO.setCitationNumberList(request
                .getNonAdjBenefitMandateVO().getCitationNumberList());
        return benefitMandateBO;
    }


    /**
     * Creates an object of the BusinessObjectManagerFactory from where an
     * object for BusinessObjectManager is created
     * 
     * 
     * @return BusinessObjectManager
     */
    public BusinessObjectManager getBOM() {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering method to get bom");
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        Logger
                .logInfo("StandardBenefitBusinessService - Returning bom from getBOM()");
        return bom;
    }


    /**
     * This method is used to get the businesslevel list from the table
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(LocateBenefitLevelListRequest request)
            throws WPDException {
        LocateBenefitLevelListResponse locateBenefitLevelListResponse = null;
        BenefitLevelListLocateCriteria benefitLevelListLocateCriteria = new BenefitLevelListLocateCriteria();
        benefitLevelListLocateCriteria.setBenefitsystemId(request
                .getBenefitsystemId());
        locateBenefitLevelListResponse = (LocateBenefitLevelListResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFITLEVEL_LIST_RESPONSE);
        StandardBenefitBusinessObjectBuilder bom = new StandardBenefitBusinessObjectBuilder();
        LocateResults locateResults = bom.locate(
                benefitLevelListLocateCriteria, request.getUser());
        locateBenefitLevelListResponse.setBenefitlevelList(locateResults
                .getLocateResults());
        return locateBenefitLevelListResponse;
    }


    /**
     * This method get values from LocateMandateListRequest and is set in
     * LocateMandateListRequest so as to search for the MandateList
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(LocateMandateListRequest request)
            throws WPDException {
        // create the response reference
        LocateMandateListResponse locateMandateListResponse = null;
        // get the the business object manager object
        BusinessObjectManager bom = getBOM();
        // create the locate criteria object
        MandateListLocateCriteria mandateListLocateCriteria = new MandateListLocateCriteria();
        mandateListLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        mandateListLocateCriteria.setEffDate(request.getEffectiveDate());
        mandateListLocateCriteria.setExpDate(request.getExpiryDate());
        // create the response object
        locateMandateListResponse = (LocateMandateListResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_MANDATE_LIST_RESPONSE);

        // call the locateMandateList method to get the mandateList
        LocateResults locateResults = bom.locate(mandateListLocateCriteria,
                request.getUser());

        // set the mandateList in the response
        locateMandateListResponse.setMandateList(locateResults
                .getLocateResults());
        // return the response
        return locateMandateListResponse;
    }


    /**
     * This method is used to retrieve the nonAdjMandateDetails according to the
     * searched mandateId
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(RetrieveNonAdjMandateRequest request)
            throws WPDException {
        // create the response reference
        RetrieveNonAdjMandateResponse retrieveNonAdjMandateResponse = null;
        // get the business object manager object
        BusinessObjectManager bom = getBOM();
        // create the hasp map
        HashMap params = new HashMap();
        // create the parent object for the sub-object which is going to be
        // included in the map
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request.getMainObjectKey());
        standardBenefitBO.setVersion(request.getMainObjectVersion());

        standardBenefitBO.setBenefitIdentifier(request
                .getMainObjectIdentifier());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        // put the sub-object name and keyattribute for retrieve in the hashmap
        params.put(BusinessConstants.SUB_OBJECT_NAME,
                BusinessConstants.BenefitMandateBOImpl);
        // get the keyattribute from the request and set it in the map
        params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(request
                .getMainObjectKey()));
        /*
         * call the retrieve method in the business object manager and get
         * the business object
         */
        standardBenefitBO = (StandardBenefitBO) bom.retrieve(standardBenefitBO,
                request.getUser(), params);

        // create the response object
        retrieveNonAdjMandateResponse = (RetrieveNonAdjMandateResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_NON_ADJ_MANDATE_RESPONSE);
        // set the bo in the respone
        retrieveNonAdjMandateResponse
                .setBenefitMandateBO((BenefitMandateBO) standardBenefitBO
                        .getBenefitMandateBOImpl());
        // return the response
        return retrieveNonAdjMandateResponse;
    }


    /**
     * This method is used to get the selected question list from the table
     * 
     * @param LocateSelectedQuestionListRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(LocateSelectedQuestionListRequest request)
            throws WPDException {
        // create the response reference
        LocateSelectedQuestionListResponse locateSelectedQuestionListResponse = null;
        // get the the business object manager object
        BusinessObjectManager bom = getBOM();
        // create the locate criteria object
        SelectedQuestionListLocateCriteria selectedQuestionListLocateCriteria = new SelectedQuestionListLocateCriteria();
        // get the adminOptionsSysId from the request and set to this
        // locateCriteria based on which the search will be made
        // Enhancement
        selectedQuestionListLocateCriteria.setAdminOptionId(request
                .getAdminOptionId());
        // End - Enhancement
        selectedQuestionListLocateCriteria.setAdminOptionSysId(request
                .getAdminOptionSysId());
        selectedQuestionListLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        // create the response object
        locateSelectedQuestionListResponse = (LocateSelectedQuestionListResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SELECTED_QUESTION_LIST);
        // call the locateMandateList method to get the mandateList
        LocateResults locateResults = bom.locate(
                selectedQuestionListLocateCriteria, request.getUser());
        // set the selectedQuestionList in the response
        locateSelectedQuestionListResponse
                .setSelectedQuestionList(locateResults.getLocateResults());
        // return the response
        return locateSelectedQuestionListResponse;
    }


    /**
     * This method is used to get the Admin Option list from the table
     * 
     * @param request
     * @return
     * @throws WPDException
     *             For AdminOption Popup Setting the resultsize and
     *             benefitlevelSystemId from LookupAdminOptionRequest to
     *             LookupAdminOptionLocateCriteria
     */

    public WPDResponse execute(LookupAdminOptionRequest request)
            throws WPDException {
        BusinessObjectManager bom = getBOM();
        LookupAdminOptionResponse lookupAdminOptionResponse = null;
        LookupAdminOptionLocateCriteria lookupAdminOptionLocateCriteria = new LookupAdminOptionLocateCriteria();
        lookupAdminOptionLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        lookupAdminOptionLocateCriteria.setBenefitLevelSystemId(request
                .getAdministrationOptionVO().getBenefitLevelSystemId());
        lookupAdminOptionLocateCriteria.setBenefitAdminSystemId(request.getAdministrationOptionVO().getBenefitAdminSystemId());
        lookupAdminOptionResponse = (LookupAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOOK_UP_ADMIN_OPTION_RESPONSE);
        LocateResults locateResults = bom.locate(
                lookupAdminOptionLocateCriteria, request.getUser());
        lookupAdminOptionResponse.setLookupAdminOption(locateResults
                .getLocateResults());
        return lookupAdminOptionResponse;
    }


    /**
     * This method get values from BenefitLevelRequest and is set in
     * StandardBenefitBO so as to search for the BenefitLevel
     * 
     * @param benefitLevelRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(BenefitLevelRequest benefitLevelRequest)
            throws ServiceException {
        BenefitLevelResponse benefitLevelResponse = null;
        BenefitWrapperVO benefitWrapperVO2 = null;
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            Map params = new HashMap();
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setCreatedUser((benefitLevelRequest.getUser())
                    .toString());
            standardBenefitBO.setVersion(benefitLevelRequest
                    .getBenefitWrapperVO().getMasterVersion());
            standardBenefitBO.setStandardBenefitKey(benefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(benefitLevelRequest
                    .getBenefitWrapperVO().getBenefitIdentifier());
            standardBenefitBO.setStatus(benefitLevelRequest
                    .getBenefitWrapperVO().getMasterStatus());
            standardBenefitBO.setBusinessDomains(benefitLevelRequest
                    .getBenefitWrapperVO().getBusinessDomains());
            standardBenefitBO.setParentSystemId(benefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitParentKey());
            BenefitWrapperVO benefitWrapperVO = benefitLevelRequest
                    .getBenefitWrapperVO();
            BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
            List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
            List benefitLevelBOs = null;
            List benefitlineBOs = null;
            // **Change**
            List benefitTermBOs = null;
            // **End**
            //Change: Qualifier Aggregate
            List benefitQualifierBOs = null;
            //End
            
            List errorList = null;
            List messages = null;
            if (null != benefitLevels) {
                benefitLevelBOs = new ArrayList();
                for (int i = 0; i < benefitLevels.size(); i++) {
                    BenefitLevelVO benefitLevelVO = (BenefitLevelVO) benefitLevels
                            .get(i);
                    BenefitLevelBO benefitLevelBO = copyBusinessObjectFromValueObject(benefitLevelVO);
                    List benefitLines = benefitLevelVO.getBenefitLines();
                    if (null != benefitLines) {
                        benefitlineBOs = new ArrayList();
                        for (int j = 0; j < benefitLines.size(); j++) {
                            BenefitLineVO benefitLineVO = (BenefitLineVO) benefitLines
                                    .get(j);
                            BenefitLineBO benefitLineBO = copyBusinessObjectFromValueObject(benefitLineVO);
                            benefitLineBO.setBenefitLevelId(benefitLevelBO
                                    .getBenefitLevelId());
                            // Changed for enhancement
                            benefitLineBO.setBenefitDefenitionId(benefitWrapperVO.getBenefitDefinitionId());
                            //Change ends
                            benefitlineBOs.add(benefitLineBO);
                        }
                    }
                    benefitLevelBO.setBenefitLines(benefitlineBOs);
                    // **Change**
                    List benefitTerms = benefitLevelVO.getBenefitTerms();
                    if (null != benefitTerms) {
                        benefitTermBOs = new ArrayList();
                        for (int k = 0; k < benefitTerms.size(); k++) {
                            BenefitTermVO benefitTermVO = (BenefitTermVO) benefitTerms
                                    .get(k);
                            BenefitTermBO benefitTermBO = copyBusinessObjectFromValueObject(benefitTermVO);
                            benefitTermBO.setBenefitLevelId(benefitLevelBO
                                    .getBenefitLevelId());
                            benefitTermBOs.add(benefitTermBO);
                        }
                    }
                    benefitLevelBO.setBenefitTerms(benefitTermBOs);
                    // **End**
// Change: Qualifier Aggregate
                    List benefitQualifiers = benefitLevelVO.getBenefitQualifiers();
                    if (null != benefitQualifiers) {
                        benefitQualifierBOs = new ArrayList();
                        for (int l = 0; l < benefitQualifiers.size(); l++) {
                            BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
                                    .get(l);
                            BenefitQualifierBO benefitQualifierBO = copyQualifierBusinessObjectFromValueObject(benefitQualifierVO);
                            benefitQualifierBO.setBenefitLevelId(benefitLevelBO
                                    .getBenefitLevelId());
                            benefitQualifierBOs.add(benefitQualifierBO);
                        }
                    }
                    benefitLevelBO.setBenefitQualifiers(benefitQualifierBOs);
// **End**
                    benefitLevelBOs.add(benefitLevelBO);
                }
            }
            benefitWrapperBO.setBenefitLevelsList(benefitLevelBOs);
            benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                    .getBenefitDefinitionId());
            benefitWrapperBO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
            if (bom.persist(benefitWrapperBO, standardBenefitBO,
                    benefitLevelRequest.getUser(), true))
                benefitWrapperVO2 = searchBenefit(benefitWrapperBO,
                        benefitLevelRequest.getUser());
            if (null != benefitWrapperVO2) {
                benefitLevelResponse = (BenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.CREATE_BENEFIT_LEVEL_RESPONSE);
                messages = new ArrayList(1);
                messages.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_LEVEL_CREATE));
                benefitLevelResponse.setMessages(messages);
                benefitLevelResponse.setBenefitWrapperVO(benefitWrapperVO2);
            } else {
                errorList = new ArrayList(1);
                benefitLevelResponse = (BenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.CREATE_BENEFIT_LEVEL_RESPONSE);
                errorList.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_MANDATE_SEARCH_RESULTS));
                benefitLevelResponse.setMessages(errorList);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitLevelRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelResponse;
    }


    /**
     * This method get values from AdministrationOptionRequest and is set in
     * AdministrationOptionBO so as to search for the AdministrationOption
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(AdministrationOptionRequest request)
            throws WPDException {
    	
        BusinessObjectManager bom = getBOM();
        Map params = new HashMap();
        AdministrationOptionBO adminOptionBO = null;
        int adminLevelOptionSysId = 0;
        
        if(request.isActionForUpdateSequence()){
        	AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) WPDResponseFactory
	        .getResponse(WPDResponseFactory.CREATE_BENEFIT_ADMINOPTION_RESPONSE);
        	StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
//        	List adminListUpdate = new ArrayList();
        	List adminVOList = request.getAdminList();
        	//Iterator iterComponent = adminVOList.iterator();
        	List messageList = new ArrayList(1);
        	if(null != request.getAdminList() && request.getAdminList().size()!=0){
        		AdministrationOptionVO adminOptionVO1 = (AdministrationOptionVO) (request.getAdminList().get(0));
        		standardBenefitBO.setStandardBenefitKey(adminOptionVO1.getStandardBenefitId());
				standardBenefitBO.setParentSystemId(adminOptionVO1.getStandardBenefitParentId());
				standardBenefitBO.setBenefitIdentifier(adminOptionVO1.getStandardBenefitName());
				standardBenefitBO.setVersion(adminOptionVO1.getStandardBenefitVersion());
				standardBenefitBO.setBusinessDomains(adminOptionVO1.getSbBusinessDomains());
				
        	}

        	AdministrationOptionBO adminOptionBO1 = setValuesToBenefitAdminBO(request.getAdminList(), adminOptionBO); 
        	
        	if(null != adminOptionBO1.getAdminList() && adminOptionBO1.getAdminList().size()!=0){
             bom.persist(adminOptionBO1,standardBenefitBO,request.getUser(),false);
        		messageList.add(new InformationalMessage(
        				BusinessConstants.MSG_PRDCT_ADMIN_UPDATED));
        		administrationOptionResponse.setMessages(messageList);
        	}
        	//modified on 31stDec to search for the remaining admin options after updation
            AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
            criteria.setBenefitAdministrationSystemId(request
                    .getBenefitAdminSysId());
            criteria.setBenefitDefinitionKey(request.getBenefitDefiniitonKey());
            
            LocateResults locateResults = bom.locate(criteria, request.getUser());
            administrationOptionResponse.setAdminOptionList(locateResults
                    .getLocateResults());
			
            return administrationOptionResponse;
        }else{
        	AdministrationOptionResponse administrationOptionResponse = (AdministrationOptionResponse) WPDResponseFactory
	        .getResponse(WPDResponseFactory.CREATE_BENEFIT_ADMINOPTION_RESPONSE);
        	
        	AdministrationOptionBO administrationOptionBO = getAdministrationOptionObject(request);
            //create the parent object for the sub-object which is going to be
            // included in the map
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setCreatedUser(request.getUser().getUserId());
            standardBenefitBO.setVersion(request.getAdministrationOptionVO()
                    .getMasterVersion());
            standardBenefitBO.setStandardBenefitKey(request
                    .getAdministrationOptionVO().getBenefitMasterSystemId());
            standardBenefitBO.setBenefitIdentifier(request
                    .getAdministrationOptionVO().getBenefitIdentifier());
            standardBenefitBO.setStatus(request.getAdministrationOptionVO()
                    .getStandardBenefitStatus());
            standardBenefitBO.setParentSystemId(request.getAdministrationOptionVO()
                    .getStandardBenefitParentKey());
            standardBenefitBO.setBusinessDomains(request
                    .getAdministrationOptionVO().getBusinessDomains());
// clarify
            administrationOptionResponse = (AdministrationOptionResponse) (new ValidationServiceController()
                    .execute(request));
            if (administrationOptionResponse != null
                    && (administrationOptionResponse.getMessages() == null || administrationOptionResponse
                            .getMessages().size() == 0)) {
                if (request.getAdministrationOptionVO().getMasterKeyUsedForUpdate() != -1) {
                	// to delete the selected Admin option
                	Long val = new Long(administrationOptionBO.getAdminLevelOptionAssnSystemId());
                	List valueList = new ArrayList();
                	valueList.add(val);
                	administrationOptionBO.setAdminDeleteList(valueList);
                	bom.delete(administrationOptionBO, standardBenefitBO, request
                            .getUser());
                	//to locate the remaining Admin Options
                	AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
                    criteria.setBenefitAdministrationSystemId(administrationOptionBO
                            .getBenefitAdminSystemId());
                    criteria.setBenefitDefinitionKey(administrationOptionBO.getBenefitDefinitionKey());
                    LocateResults locateResults = bom.locate(criteria, request.getUser());    
                    //to reorder the sequence nos after deletion
                    SequenceUtil sequenceUtil = new SequenceUtil();
            		List adminoptionList = sequenceUtil.reOrderObjects(locateResults.getLocateResults());
            		AdministrationOptionBO optionBO = null;
            		//to save the admin options after reordering the sequence nos 
            		if(null != adminoptionList && adminoptionList.size()!=0){
            			AdministrationOptionBO optionBO2 = new AdministrationOptionBO();
            	    	List adminOptionBOsList = new ArrayList();
            	    	int adminOptLvlAssnSysId = 0;
        	    		for(int i = 0; i < adminoptionList.size();i++){ 
        	    			AdministrationOptionBO valueObject = (AdministrationOptionBO)adminoptionList.get(i);
        	    			optionBO  = new AdministrationOptionBO();
        	    			optionBO.setAdminLevelOptionAssnSystemId(valueObject.getAdminLevelOptionAssnSystemId());		    	
        	    			optionBO.setAdminLevelSystemId(valueObject.getAdminLevelSystemId());
        	    			optionBO.setAdminOptionSystemId(valueObject.getAdminOptionSystemId());
        	    			optionBO.setBenefitAdminSystemId(valueObject.getBenefitAdminSystemId());		    	
        	    			optionBO.setBenefitLevelSysId(valueObject.getBenefitLevelSystemId());		    	
        	    			optionBO.setSequenceNumber(valueObject.getSequenceNumber());
        	    			optionBO.setBenefitDefinitionKey(valueObject.getBenefitDefinitionKey());
        			    	adminOptionBOsList.add(optionBO);
        	    		}	
        	    		optionBO2.setAdminList(adminOptionBOsList);    
        	    		optionBO2.setAdminLevelOptionAssnSystemId(adminOptLvlAssnSysId);
        	    		optionBO2.setFlag("UPDATE_ADMIN_OPTION");
            			
                        bom.persist(optionBO2,standardBenefitBO,request.getUser(),false);
            		}
                    AssociateAdminOptionToBenefitLocateCriteria criteria1 = new AssociateAdminOptionToBenefitLocateCriteria();
                    criteria1.setAdministrationOptionBO(administrationOptionBO);                    
                    locateResults = bom.locate(criteria1, request.getUser());
                    if( null != locateResults && !locateResults.getLocateResults().isEmpty()){
                    	AdministrationOptionBO administrationOption = 
                    		(AdministrationOptionBO) locateResults.getLocateResults().get(0);
                    	adminLevelOptionSysId = administrationOption.getAdminLevelOptionAssnSystemId();
                    }
                   	
                    administrationOptionResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.MSG_BENEFIT_ADMN_OPTION_UPDATE_SUCCESS));
                } else {
                    /*bom.persist(administrationOptionBO, standardBenefitBO, request
                            .getUser(), true);*/
                	
                	AssociateAdminOptionToBenefitLocateCriteria criteria = new AssociateAdminOptionToBenefitLocateCriteria();
                    criteria.setAdministrationOptionBO(administrationOptionBO);                    
                    LocateResults locateResults = bom.locate(criteria, request.getUser());
                    if( null != locateResults && !locateResults.getLocateResults().isEmpty()){
                    	AdministrationOptionBO administrationOption = 
                    		(AdministrationOptionBO) locateResults.getLocateResults().get(0);
                    	adminLevelOptionSysId = administrationOption.getAdminLevelOptionAssnSystemId();
                    }
                    administrationOptionResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.MSG_BENEFIT_ADMN_OPTION_SAVE_SUCCESS));
                }
                /*params.put(BusinessConstants.SUB_OBJECT_NAME,
                        "AdministrationOptionBO");
                params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
                        administrationOptionBO.getAdminLevelOptionAssnSystemId()));                
                params.put("adminLevelSystemIdForBenefitLevel", "1");
                standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                        standardBenefitBO, request.getUser(), params);
                administrationOptionResponse
                        .setAdministrationOptionBO(standardBenefitBO
                                .getBenefitDefinition().getAdministrationOptionBO());*/
                if(adminLevelOptionSysId != 0){
                	params.put(BusinessConstants.SUB_OBJECT_NAME,
                    		"AdministrationOptionBO");
                	params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(
                			adminLevelOptionSysId));
                	params.put("adminLevelSystemIdForBenefitLevel", "1");
                    standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                            standardBenefitBO, request.getUser(), params);
                    administrationOptionResponse
                            .setAdministrationOptionBO(standardBenefitBO
                                    .getBenefitDefinition().getAdministrationOptionBO());
                }
            }    
            return administrationOptionResponse;
        }      

       
    }

    private AdministrationOptionBO setValuesToBenefitAdminBO(List valueObjectList,
    		AdministrationOptionBO businessObject) {
    	AdministrationOptionBO adminOptionBO = new AdministrationOptionBO();
    	List adminOptionBOsList = new ArrayList();
    	int adminOptLvlAssnSysId = 0;
    	if(null != valueObjectList && valueObjectList.size()!=0){
    		for(int i = 0; i < valueObjectList.size();i++){ 
    			AdministrationOptionVO valueObject = (AdministrationOptionVO)valueObjectList.get(i);
    	    	businessObject  = new AdministrationOptionBO();
		    	businessObject.setAdminLevelOptionAssnSystemId(valueObject.getAdminLevelOptionAssnSystemId());		    	
		    	businessObject.setAdminLevelSystemId(valueObject.getAdminLevelSystemId());
		    	businessObject.setAdminOptionSystemId(valueObject.getAdminOptionSystemId());
		    	businessObject.setBenefitAdminSystemId(valueObject.getBenefitAdminSystemId());		    	
		    	businessObject.setBenefitLevelSysId(valueObject.getBenefitLevelSystemId());		    	
		    	businessObject.setSequenceNumber(valueObject.getSequenceNumber());
		    	businessObject.setBenefitDefinitionKey(valueObject.getBenefitDefinitionkey());
		    	adminOptionBOsList.add(businessObject);
    		}	
    		adminOptionBO.setAdminList(adminOptionBOsList);    
    		adminOptionBO.setAdminLevelOptionAssnSystemId(adminOptLvlAssnSysId);
    		adminOptionBO.setFlag("UPDATE_ADMIN_OPTION");
    	}	
    	return adminOptionBO;
    }

    /**
     * This method get values from LocateAdministrationOptionRequest and is set
     * in AdministrativeOptionLocateCriteria so as to search for the
     * AdministrationOption
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(LocateAdministrationOptionRequest request)
            throws WPDException {
        LocateAdministrationOptionResponse response = (LocateAdministrationOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_BENEFIT_ADMINOPTION_RESPONSE);
        AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
        criteria.setBenefitAdministrationSystemId(request
                .getBenefitAdminSysId());
        criteria.setBenefitComponentId(request.getBenefitComponentId());
        criteria.setEntityId(request.getEntityId());
        criteria.setEntityType(request.getEntityType());
        criteria.setPSorProduct(request.getPSorProductorBenefit());
        criteria.setBenefitDefinitionKey(request.getBenefitDefinitionKey());
        criteria.setDateSegmentId(request.getDateSegmentId());
        BusinessObjectManager bom = getBOM();
        LocateResults locateResults = bom.locate(criteria, request.getUser());       
        response.setAssociatedBenefitAdministrationOptionList(locateResults
                .getLocateResults());
// Hidden + Visisble AdminOptions count    - Jan23rd    
        response.setHiddenAdminOptionCount(locateResults.getTotalResultsCount());
// End    
        return response;
    }

    
   
    /**
     * This method get values from RetrieveAdministrationOptionRequest and is
     * set in standardBenefitBO so as to retrieve the details of the
     * AdministrationOption
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveAdministrationOptionRequest request)
            throws WPDException {
        RetrieveAdministrationOptionResponse response = null;
        Map params = new HashMap();
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request.getMainObjKey());
        standardBenefitBO.setVersion(request.getMainObjVersion());
        standardBenefitBO.setBenefitIdentifier(request.getMainObjIdentifier());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());

        AdministrationOptionBO optionBO = new AdministrationOptionBO();
        optionBO.setAdminLevelOptionAssnSystemId(request
                .getAdminLevelOptionAssnSystemId());
        optionBO.setAdminLevelSystemIdForBenefitLevel(request.getItemCode());
        response = (RetrieveAdministrationOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_ADMINOPTION_RESPONSE);
        params.put(BusinessConstants.SUB_OBJECT_NAME, "AdministrationOptionBO");
        params.put(BusinessConstants.KEY_FOR_RETRIEVE, new Integer(optionBO
                .getAdminLevelOptionAssnSystemId()));
        params.put("adminLevelSystemIdForBenefitLevel", optionBO
                .getAdminLevelSystemIdForBenefitLevel());
        standardBenefitBO = (StandardBenefitBO) bom.retrieve(standardBenefitBO,
                request.getUser(), params);
        response.setAdministrationOptionBO(standardBenefitBO
                .getBenefitDefinition().getAdministrationOptionBO());
        return response;
    }


    /**
     * 
     * @param benefitWrapperBO
     * @param benefitLevelRequest
     * @return
     * @throws ServiceException
     */
    private BenefitWrapperVO searchBenefit(BenefitWrapperBO benefitWrapperBO,
            User user) throws ServiceException {
        BenefitWrapperBO benefitWrapperBO2 = null;
        BenefitWrapperVO benefitWrapperVO = null;
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            List benefitSearchResultsList = null;
//            List errorList = new ArrayList();
            LocateResults locateResults = null;
            BenefitLocateCriteria benefitLocateCriteria = new BenefitLocateCriteria();
            benefitLocateCriteria.setBenefitDefinitionId(benefitWrapperBO
                    .getBenefitDefinitionId());            
            if(null!=benefitWrapperBO.getSelectedBenefitTerm()){
            	benefitLocateCriteria.setSelectedBenefitTerm(benefitWrapperBO.getSelectedBenefitTerm());
            }else{
            	benefitLocateCriteria.setSelectedBenefitTerm("%");
            }
            locateResults = bom.locate(benefitLocateCriteria, user);
            int searchResultCount = locateResults.getTotalResultsCount();
            benefitSearchResultsList = locateResults.getLocateResults();
            if (null != benefitSearchResultsList && benefitSearchResultsList.size() > 0) {        	
                benefitWrapperBO2 = new BenefitWrapperBO();
                benefitWrapperBO2.setBenefitDefinitionId(benefitWrapperBO
                        .getBenefitDefinitionId());                   
                benefitWrapperBO2
                        .setBenefitLevelsList(benefitSearchResultsList);
                benefitWrapperVO = copyBusinessObjectToValueObject(benefitWrapperBO2);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing searchBenefit";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitWrapperVO;
    }


    /**
     * This method is used to copy the values from BenefitWrapperBO to
     * BenefitWrapperVO
     * 
     * @param benefitWrapperBO
     * @return
     */
    private BenefitWrapperVO copyBusinessObjectToValueObject(
            BenefitWrapperBO benefitWrapperBO) {
    	//START CARS
    	String termValue = null;
    	String qualifierValue = null;
    	String descriptionValue = null;
    	boolean descriptionFlag = false;
    	boolean dataTypeFlag = false;
    	//END CARS
        BenefitWrapperVO benefitWrapperVO = null;
        List benefitLevelVOsList = new ArrayList();
        if (null != benefitWrapperBO) {
            List benefitLevelBOsList = benefitWrapperBO.getBenefitLevelsList();
            if (null != benefitLevelBOsList && !benefitLevelBOsList.isEmpty()) {
                for (int i = 0; i < benefitLevelBOsList.size(); i++) {
                    BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevelBOsList
                            .get(i);
                    
	                    BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
	                    benefitLevelVO.setBenefitDefinitionId(benefitLevelBO
	                            .getBenefitDefinitionId());
	                    benefitLevelVO.setBenefitLevelDesc(benefitLevelBO
	                            .getBenefitLevelDesc());
	                    benefitLevelVO.setBenefitLevelId(benefitLevelBO
	                            .getBenefitLevelId());
	                    benefitLevelVO.setBenefitLevelSeq(benefitLevelBO
	                            .getBenefitLevelSeq());
	                    benefitLevelVO.setBenefitQualifier(benefitLevelBO
	                            .getBenefitQualifier());
	                    benefitLevelVO.setBenefitTerm(benefitLevelBO
	                            .getBenefitTerm());
	                    benefitLevelVO.setReference(benefitLevelBO.getReference());
	                    benefitLevelVO.setReferenceCode(benefitLevelBO
	                            .getReferenceCode());
	                    benefitLevelVO.setBenefitQualifierCode(benefitLevelBO
	                            .getBenefitQualifierCode());
	                    benefitLevelVO.setBenefitTermCode(benefitLevelBO
	                            .getBenefitTermCode());
	                    //CARS START
	                    //DESCRIPTION : Getting the frefrequenct value from the BO and setting it in the VO.
	                    benefitLevelVO.setBenefitFrequency(benefitLevelBO.getBenefitFrequency());
	                    //CARS END
	                    List benefitLineBOs = benefitLevelBO.getBenefitLines();
	                    List benefitLineVOs = new ArrayList();
	                    if (null != benefitLineBOs) {
	                        for (int j = 0; j < benefitLineBOs.size(); j++) {
	                            BenefitLineBO benefitLineBO = (BenefitLineBO) benefitLineBOs
	                                    .get(j);
	                            BenefitLineVO benefitLineVO = new BenefitLineVO();
	                            benefitLineVO.setBenefitLevelId(benefitLineBO
	                                    .getBenefitLevelId());
	                            benefitLineVO.setBenefitLineId(benefitLineBO
	                                    .getBenefitLineId());
	                            benefitLineVO.setBenefitValue(benefitLineBO
	                                    .getBenefitValue());
	                            benefitLineVO.setDataTypeId(benefitLineBO
	                                    .getDataTypeId());
	                            benefitLineVO.setDataTypeName(benefitLineBO
	                                    .getDataTypeName());
	                            benefitLineVO.setPVA(benefitLineBO.getPVA());
	                            benefitLineVO
	                                    .setPvaCode(benefitLineBO.getPvaCode());
	                            // **Change**
	                            benefitLineVO.setReference(benefitLineBO
	                                    .getReference());
	                            benefitLineVO.setReferenceCode(benefitLineBO
	                                    .getReferenceCode());
	                            benefitLineVO.setBnftLineNotesExist(benefitLineBO
	                                    .getBnftLineNotesExist());
	                            // **End**
	                            benefitLineVOs.add(benefitLineVO);
	                            //Checks whether data type is null
	                            if (null == benefitLineBO.getDataTypeName()
									|| BusinessConstants.EMPTY_STRING
											.equalsIgnoreCase(benefitLineBO
													.getDataTypeName())){
	                            	dataTypeFlag = true;
	                            	descriptionValue = benefitLevelBO.getBenefitLevelDesc();
	                            }
	                        }
	                    }
	                    benefitLevelVO.setBenefitLines(benefitLineVOs);
	                    // **Change**
	                    List benefitTermBOs = benefitLevelBO.getBenefitTerms();
	                    List benefitTermVOs = new ArrayList();
	                    if (null != benefitTermBOs) {
	                        for (int k = 0; k < benefitTermBOs.size(); k++) {
	                            BenefitTermBO benefitTermBO = (BenefitTermBO) benefitTermBOs
	                                    .get(k);
	                            BenefitTermVO benefitTermVO = new BenefitTermVO();
	                            benefitTermVO.setBenefitLevelId(benefitTermBO
	                                    .getBenefitLevelId());
	                            benefitTermVO.setBenefitTerm(benefitTermBO
	                                    .getBenefitTerm());
	                            benefitTermVO.setBenefitTermCode(benefitTermBO
	                                    .getBenefitTermCode());
	                            benefitTermVOs.add(benefitTermVO);
	                            //If aggregate term conact them to a string
	                            if(null != benefitTermBO.getBenefitTerm()){
		                            if (null != termValue
										&& !(BusinessConstants.EMPTY_STRING
												.equals(termValue)))
		                            	termValue = termValue+BusinessConstants.SPACE_STRING+benefitTermBO.getBenefitTerm();		                            	
		                            else
		                            	termValue = benefitTermBO.getBenefitTerm();
	                            }
	                        }
	                    }
	                    benefitLevelVO.setBenefitTerms(benefitTermVOs);
	                    // **End**
	                    //Change: Qualifier Aggregate
	                    List benefitQualifierBOs = benefitLevelBO.getBenefitQualifiers();
	                    List benefitQualifierVOs = new ArrayList();
	                    if (null != benefitQualifierBOs) {
	                        for (int k = 0; k < benefitQualifierBOs.size(); k++) {
	                            BenefitQualifierBO benefitQualifierBO = (BenefitQualifierBO) benefitQualifierBOs
	                                    .get(k);
	                            BenefitQualifierVO benefitQualifierVO = new BenefitQualifierVO();
	                            benefitQualifierVO.setBenefitLevelId(benefitQualifierBO
	                                    .getBenefitLevelId());
	                            benefitQualifierVO.setBenefitQualifier(benefitQualifierBO
	                                    .getBenefitQualifier());
	                            benefitQualifierVO.setBenefitQualifierCode(benefitQualifierBO
	                                    .getBenefitQualifierCode());
	                            benefitQualifierVOs.add(benefitQualifierVO);
	                            //If aggregate Qualifier conact them to a string
	                            if(null != benefitQualifierBO.getBenefitQualifier()){
		                            if (null != qualifierValue
										&& !(BusinessConstants.EMPTY_STRING
												.equals(qualifierValue)))
		                            	qualifierValue = qualifierValue+BusinessConstants.SPACE_STRING+benefitQualifierBO.getBenefitQualifier();
		                            else
		                            	qualifierValue = benefitQualifierBO.getBenefitQualifier();		                            	
	                            }    
	                        }
	                    }
	                    benefitLevelVO.setBenefitTerms(benefitTermVOs);
	                    benefitLevelVO.setBenefitQualifiers(benefitQualifierVOs);
	                    // **End**
	                    benefitLevelVOsList.add(benefitLevelVO);
	                    //dataTypeFlag would be false only when the levels are new
	                    if(dataTypeFlag){
	                    	//Calls the method whcih compares the description
		                    descriptionFlag = createDescriptionObject(termValue,qualifierValue,(new Integer(benefitLevelBO.getBenefitFrequency()).toString()),benefitLevelBO.getBenefitLevelDesc());
	                    }
	                    termValue = null;
	                    qualifierValue = null;
	                    dataTypeFlag = false;
                }
            }
        }
        benefitWrapperVO = new BenefitWrapperVO();
        benefitWrapperVO.setBenefitDefinitionId(benefitWrapperBO
                .getBenefitDefinitionId());
        benefitWrapperVO.setBenefitIdentifier(benefitWrapperBO
                .getBenefitIdentifier());
        benefitWrapperVO.setBenefitLevelsList(benefitLevelVOsList);
        benefitWrapperVO.setDescriptionFlag(descriptionFlag);
        benefitWrapperVO.setDescriptionValue(descriptionValue);
        return benefitWrapperVO;
    }
    /**
     * This method craetes the description from the term quelifier and frequency.
     * @param termValue
     * @param qualifierValue
     * @param frequencyValue
     * @return
     */
    private boolean createDescriptionObject(String termValue,String qualifierValue,String frequencyValue,String descriptionValue){
    	String description;
    	description = termValue+ BusinessConstants.PER_STRING +qualifierValue;
    	if(description.equalsIgnoreCase(descriptionValue)){
    		return true;
    	}
    	return false;
    }
    /**
     * This method get values from DeleteBenefitAdministrationRequest and is set
     * in BenefitAdministrationVO so as to delete the Administrationdetails of a
     * particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteBenefitAdministrationRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Delete Benefit Administration");
        DeleteBenefitAdministrationResponse deleteBenefitAdministrationResponse = null;

        BenefitAdministrationVO benefitAdministrationVO = (BenefitAdministrationVO) request
                .getBenefitAdministrationVO();
        BenefitAdministrationBO benefitAdministrationBO = new BenefitAdministrationBO();
        benefitAdministrationBO.setBenefitDefinitionKey(benefitAdministrationVO
                .getBenefitDefinitionKey());
        /*benefitAdministrationBO
                .setBenefitAdministrationKey(benefitAdministrationVO
                        .getBenefitAdministrationKey());*/
        benefitAdministrationBO.setBenefitAdministrationKeysForDelete
			(benefitAdministrationVO.getBenefitAdministrationKeysForDelete());

        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();

            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setStandardBenefitKey(benefitAdministrationVO
                    .getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(benefitAdministrationVO
                    .getStandardBenefitName());
            standardBenefitBO.setParentSystemId(benefitAdministrationVO
                    .getStandardBenefitParentKey());
            standardBenefitBO.setBusinessDomains(benefitAdministrationVO
                    .getBusinessDomains());
            standardBenefitBO.setStatus(benefitAdministrationVO
                    .getStandardBenefitStatus());
            standardBenefitBO.setVersion(benefitAdministrationVO
                    .getStandardBenefitVersion());

            bom.delete(benefitAdministrationBO, standardBenefitBO, request
                    .getUser());

            deleteBenefitAdministrationResponse = (DeleteBenefitAdministrationResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.DELETE_BENEFIT_ADMINISTRATION_RESPONSE);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing DeleteBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        if (null != deleteBenefitAdministrationResponse) {
            deleteBenefitAdministrationResponse
                    .addMessage(new InformationalMessage(
                            "benefit.administration.delete.success"));
            deleteBenefitAdministrationResponse
                    .setBenefitAdministrationBO(benefitAdministrationBO);
        } else {
            deleteBenefitAdministrationResponse
                    .addMessage(new InformationalMessage(
                            "benefit.administration.delete.failure"));
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Delete Benefit Administration");
        return deleteBenefitAdministrationResponse;
    }


    /**
     * This method get values from RetrieveBenefitAdministrationRequest and is
     * set in BenefitAdministrationBO so as to retrieve the
     * Administrationdetails of a particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveBenefitAdministrationRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Retrieve Benefit Administration");
        RetrieveBenefitAdministrationResponse retrieveBenefitAdministrationResponse = null;
        BusinessObjectManager bom = getBOM();
        BenefitAdministrationBO benefitAdministrationBO = new BenefitAdministrationBO();
        benefitAdministrationBO.setBenefitAdministrationKey(request
                .getBenefitAdministrationVO().getBenefitAdministrationKey());
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(request
                .getBenefitAdministrationVO().getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(request
                .getBenefitAdministrationVO().getStandardBenefitName());
        standardBenefitBO.setParentSystemId(request
                .getBenefitAdministrationVO().getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request
                .getBenefitAdministrationVO().getBusinessDomains());
        standardBenefitBO.setStatus(request.getBenefitAdministrationVO()
                .getStandardBenefitStatus());
        standardBenefitBO.setVersion(request.getBenefitAdministrationVO()
                .getStandardBenefitVersion());
        retrieveBenefitAdministrationResponse = (RetrieveBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_ADMINISTRATION_RESPONSE);
        try {
            StandardBenefitBusinessObjectBuilder std = new StandardBenefitBusinessObjectBuilder();
            benefitAdministrationBO = (BenefitAdministrationBO) std
                    .retrieve(benefitAdministrationBO);

            retrieveBenefitAdministrationResponse
                    .setBenefitAdministrationBO(benefitAdministrationBO);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Retrieve Benefit Administration");
        return retrieveBenefitAdministrationResponse;

    }


    /**
     * This method get values from DeleteAdministrationOptionRequest and is set
     * in AdministrationOptionBO so as to delete the Administrationdetails of a
     * particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteAdministrationOptionRequest request)
            throws WPDException {
        DeleteAdministrationOptionResponse response = null;
        AdministrationOptionBO optionBO = new AdministrationOptionBO();
        optionBO.setAdminLevelOptionAssnSystemId(request
                .getAdminLevelOptionAssnSystemId());
        optionBO.setAdminDeleteList(request.getAdminDeleteList());
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setVersion(request.getMainObjVersion());
        standardBenefitBO.setStandardBenefitKey(request.getMainObjKey());
        standardBenefitBO.setBenefitIdentifier(request.getMainObjIdentifier());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());

        BusinessObjectManager bom = getBOM();

        response = (DeleteAdministrationOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_BENEFIT_ADMINOPTION_RESPONSE);

        bom.delete(optionBO, standardBenefitBO, request.getUser());

        response.addMessage(new InformationalMessage(
                "benefit.administration.option.delete.success"));
        return response;
    }


    // **Change**
    /**
     * @param benefitTermVO
     * @return
     * @throws ServiceException
     */
    private BenefitTermBO copyBusinessObjectFromValueObject(
            BenefitTermVO benefitTermVO) throws ServiceException {
        BenefitTermBO benefitTermBO = new BenefitTermBO();
        benefitTermBO.setBenefitTerm(benefitTermVO.getBenefitTerm());
        benefitTermBO.setBenefitTermCode(benefitTermVO.getBenefitTermCode());
        return benefitTermBO;
    }


    // **End**
    
// Change: Qualifier Aggregate
    /**
     * @param benefitQualifierVO
     * @return
     * @throws ServiceException
     */
    private BenefitQualifierBO copyQualifierBusinessObjectFromValueObject(
    		BenefitQualifierVO benefitQualifierVO) throws ServiceException {
        BenefitQualifierBO benefitQualifierBO = new BenefitQualifierBO();
        benefitQualifierBO.setBenefitQualifier(benefitQualifierVO.getBenefitQualifier());
        benefitQualifierBO.setBenefitQualifierCode(benefitQualifierVO.getBenefitQualifierCode());
        return benefitQualifierBO;
    }


    // **End**

    /**
     * @param benefitLineVO
     * @return
     * @throws ServiceException
     */
    private BenefitLineBO copyBusinessObjectFromValueObject(
            BenefitLineVO benefitLineVO) throws ServiceException {
        BenefitLineBO benefitLineBO = new BenefitLineBO();
        benefitLineBO.setBenefitValue(benefitLineVO.getBenefitValue());
        benefitLineBO.setDataTypeId(benefitLineVO.getDataTypeId());
        benefitLineBO.setPVA(benefitLineVO.getPVA());
        benefitLineBO.setPvaCode(benefitLineVO.getPvaCode());
        // **Change**
        benefitLineBO.setReference(benefitLineVO.getReference());
        benefitLineBO.setReferenceCode(benefitLineVO.getReferenceCode());
        // **End**
        return benefitLineBO;
    }


    /**
     * @param benefitLevelVO
     * @return
     */
    private BenefitLevelBO copyBusinessObjectFromValueObject(
            BenefitLevelVO benefitLevelVO) {
        BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
        benefitLevelBO.setBenefitDefinitionId(benefitLevelVO
                .getBenefitDefinitionId());
        benefitLevelBO
                .setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
        benefitLevelBO.setBenefitTerm(benefitLevelVO.getBenefitTerm());
        benefitLevelBO.setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
        benefitLevelBO
                .setBenefitQualifier(benefitLevelVO.getBenefitQualifier());
        benefitLevelBO.setReference(benefitLevelVO.getReference());
        benefitLevelBO.setReferenceCode(benefitLevelVO.getReferenceCode());
        benefitLevelBO.setBenefitTermCode(benefitLevelVO.getBenefitTermCode());
        benefitLevelBO.setBenefitQualifierCode(benefitLevelVO
                .getBenefitQualifierCode());
        
        return benefitLevelBO;
    }


    /**
     * Creates administrationOptionObject
     * 
     * @param request
     * @return administrationOptionBO
     */
    public AdministrationOptionBO getAdministrationOptionObject(
            AdministrationOptionRequest request) {
        AdministrationOptionBO administrationOptionBO = new AdministrationOptionBO();
        administrationOptionBO.setBenefitAdminSystemId(request
                .getAdministrationOptionVO().getBenefitAdminSystemId());
        administrationOptionBO.setAdminLevelSystemId(request
                .getAdministrationOptionVO().getAdminLevelSystemId());
        administrationOptionBO.setBenefitLevelSystemId(request
                .getAdministrationOptionVO().getBenefitLevelSystemId());
        administrationOptionBO.setBenefitLevelSysId(request
                .getAdministrationOptionVO().getBenefitLevelSystemId());
        administrationOptionBO.setAdminOptionSystemId(request
                .getAdministrationOptionVO().getAdminOptionSystemId());
        administrationOptionBO.setAdminLevelOptionAssnSystemId(request
                .getAdministrationOptionVO().getAdminLevelOptionAssnSystemId());      
        administrationOptionBO.setIsOpen(request.getAdministrationOptionVO().getIsOpen());
        administrationOptionBO.setSequenceNumber(request.getAdministrationOptionVO().getSequenceNumber());
        //  previous value for adminoptsysid
        administrationOptionBO.setAdminOptionSysIdForUpdate(request
                .getAdministrationOptionVO().getAdminOptionSystemIdForUpdate());
        administrationOptionBO.setBenefitDefinitionKey(request.getAdministrationOptionVO().getBenefitDefinitionkey());
        administrationOptionBO.setAdminOptionsList(request.getAdministrationOptionVO().getAdminOptionsList());
        administrationOptionBO.setBenefitId(request.getAdministrationOptionVO().getBenefitMasterSystemId());
        return administrationOptionBO;
    }


    /**
     * This method get values from SaveBenefitLevelRequest and is set in
     * StandardBenefitBO so as to save the BenefitLevel details of a particular
     * benefit
     * 
     * @param saveBenefitLevelRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(SaveBenefitLevelRequest saveBenefitLevelRequest)
            throws ServiceException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save benefit Coverage", "StandaredBenefitBusinessService", "execute()"));
        SaveBenefitLevelResponse saveBenefitLevelResponse = null;
        BenefitWrapperVO benefitWrapperVO2 = null;
        try {
            BenefitWrapperVO benefitWrapperVO = saveBenefitLevelRequest
                    .getBenefitWrapperVO();
            BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
            List benefitLevelBOs = new ArrayList();
            List errorList = null;
            List messages = null;
            List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
            List descriptionList = new ArrayList();
            boolean exceedLevelDescription = false;
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setCreatedUser(saveBenefitLevelRequest.getUser()
                    .toString());
            standardBenefitBO.setVersion(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getMasterVersion());
            standardBenefitBO.setStandardBenefitKey(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getBenefitIdentifier());
            standardBenefitBO.setStatus(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getMasterStatus());
            standardBenefitBO.setBusinessDomains(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getBusinessDomains());
            standardBenefitBO.setParentSystemId(saveBenefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitParentKey());
            if (null != benefitWrapperVO.getBenefitLevelsList()) {
                for (int i = 0; i < benefitWrapperVO.getBenefitLevelsList()
                        .size(); i++) {
                    BenefitLevelVO benefitLevelVO = (BenefitLevelVO) benefitLevels
                            .get(i);
                    BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
                    benefitLevelBO.setBenefitLevelId(benefitLevelVO
                            .getBenefitLevelId());
                    benefitLevelBO.setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
                    benefitLevelBO.setBenefitDefinitionId(benefitLevelVO
                            .getBenefitDefinitionId());
                    benefitLevelBO.setReference(benefitLevelVO.getReference());
                    benefitLevelBO.setReferenceCode(benefitLevelVO
                            .getReferenceCode());
                    benefitLevelBO.setBenefitLevelSeq(benefitLevelVO
                            .getBenefitLevelSeq());
                    //CARS START
                    //DESCRIPTION : Converts frequency value from VO to BO
                    benefitLevelBO.setBenefitFrequency(benefitLevelVO.getBenefitFrequency());
                    //CARS END
                    String termVal=benefitLevelVO.getBenefitTermCode();
                    if(termVal != null && !"".equalsIgnoreCase(termVal)){
                    	String termValues[]=termVal.split(",");
                    	List terms=new ArrayList(termValues.length);
                    	for(int j=0;j<termValues.length;j++){
                    		String termCode=termValues[j].split("~")[0];
                    		String termValue=termValues[j].split("~")[1];
                    		BenefitTermBO benefitTermBO=new BenefitTermBO();
                    		benefitTermBO.setBenefitLevelId(benefitLevelVO
                            .getBenefitLevelId());
                    		benefitTermBO.setBenefitTermCode(termCode);
                    		benefitTermBO.setBenefitTerm(termValue);
                    		terms.add(benefitTermBO);
                    	}
                    	benefitLevelBO.setBenefitTerms(terms);
                    }
                    
                    String qualVal=benefitLevelVO.getBenefitQualifierCode();
                    if(qualVal != null && !"".equalsIgnoreCase(qualVal)){
                    	String qualValues[]=qualVal.split(",");
                    	List quals=new ArrayList(qualValues.length);
                    	for(int j=0;j<qualValues.length;j++){
                    		String qualCode=qualValues[j].split("~")[0];
                    		String qualValue=qualValues[j].split("~")[1];
                    		BenefitQualifierBO benefitQualifierBO=new BenefitQualifierBO();
                    		benefitQualifierBO.setBenefitLevelId(benefitLevelVO
                            .getBenefitLevelId());
                    		benefitQualifierBO.setBenefitQualifierCode(qualCode);
                    		benefitQualifierBO.setBenefitQualifier(qualValue);
                    		quals.add(benefitQualifierBO);
                    	}
                    	benefitLevelBO.setBenefitQualifiers(quals);
                    }
                    	
                    //Changes For Performance Fix Start
                    if (!benefitWrapperVO.isDeleteFlag())
                        getBenefitLineBos(benefitLevelVO, benefitLevelBO);
                    else
                        benefitLevelBO.setBenefitLines(null);
                    //Changes For Performance Fix End
                    
                    //change for performance
                    benefitLevelBO.setModified(benefitLevelVO.isModified());
                    //end
                    benefitLevelBOs.add(benefitLevelBO);
                }
            }
            benefitWrapperBO.setBenefitLevelsList(benefitLevelBOs);
            benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                    .getBenefitDefinitionId());
            benefitWrapperBO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
            if (bom.persist(benefitWrapperBO, standardBenefitBO,
                    saveBenefitLevelRequest.getUser(), false))
                benefitWrapperVO2 = searchBenefit(benefitWrapperBO,
                        saveBenefitLevelRequest.getUser());
            if (null != benefitWrapperVO2) {
                saveBenefitLevelResponse = (SaveBenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.SAVE_BENEFIT_LEVEL_RESPONSE);
                messages = new ArrayList(1);
                messages.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_LEVEL_SAVE));
                messages.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_LEVEL_SPS_SAVE));
                saveBenefitLevelResponse.setMessages(messages);
                saveBenefitLevelResponse.setBenefitWrapperVO(benefitWrapperVO2);
            } else {
                errorList = new ArrayList(1);
                saveBenefitLevelResponse = (SaveBenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.SAVE_BENEFIT_LEVEL_RESPONSE);
                errorList.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_MANDATE_SEARCH_RESULTS));
                saveBenefitLevelResponse.setMessages(errorList);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(saveBenefitLevelRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return saveBenefitLevelResponse;
    }

    /**
     * @param benefitLevelVO
     * @param benefitLevelBO
     */
    private void getBenefitLineBos(BenefitLevelVO benefitLevelVO,
            BenefitLevelBO benefitLevelBO) {
        List benefitLineBOs = new ArrayList();
        List benefitLines = benefitLevelVO.getBenefitLines();
        if (null != benefitLines) {
            for (int j = 0; j < benefitLines.size(); j++) {
                BenefitLineVO benefitLineVO = (BenefitLineVO) benefitLines
                        .get(j);
                BenefitLineBO benefitLineBO = new BenefitLineBO();
                benefitLineBO.setBenefitLevelId(benefitLineVO
                        .getBenefitLevelId());
                benefitLineBO
                        .setBenefitLineId(benefitLineVO.getBenefitLineId());
                benefitLineBO.setBenefitDefenitionId(benefitLineVO.getBenefitDefenitionId());
                benefitLineBO.setDataTypeId(benefitLineVO.getDataTypeId());
                benefitLineBO.setBenefitValue(benefitLineVO.getBenefitValue());

                //    						Start Added By Arun 4/7/2007 to save pva code
                benefitLineBO.setPVA(benefitLineVO.getPVA());
                benefitLineBO.setPvaCode(benefitLineVO.getPvaCode());
                //    						end Added By Arun 4/7/2007 to save pva code
                // **Change**
                benefitLineBO.setReference(benefitLineVO.getReference());
                benefitLineBO
                        .setReferenceCode(benefitLineVO.getReferenceCode());
                // **End**
                
                //change for performance
                benefitLineBO.setModified(benefitLineVO.isModified());
                //end
                
                benefitLineBOs.add(benefitLineBO);
            }
        }
        benefitLevelBO.setBenefitLines(benefitLineBOs);
    }


    /**
     * This method get values from DeleteBenefitLevelRequest and is set in
     * StandardBenefitBO so as to delete the BenefitLevel details of a
     * particular benefit
     * 
     * @param deleteBenefitLevelRequest
     * @return
     * @throws ServiceException
     */
    /*public WPDResponse execute(
            DeleteBenefitLevelRequest deleteBenefitLevelRequest)
            throws ServiceException {
        DeleteBenefitLevelResponse deleteBenefitLevelResponse = null;
        BenefitWrapperVO benefitWrapperVO2 = null;
        try {
            BenefitWrapperVO benefitWrapperVO = deleteBenefitLevelRequest
                    .getBenefitWrapperVO();
            BenefitLevelVO benefitLevelVO = deleteBenefitLevelRequest
                    .getBenefitLevelVO();
            BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
            List benefitLevelBOs = new ArrayList();
            List messages = null;
            boolean lineDeletedFlag = false;
            boolean levelDeletedFlag = false;
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setCreatedUser(deleteBenefitLevelRequest
                    .getUser().toString());
            standardBenefitBO.setVersion(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getMasterVersion());
            standardBenefitBO.setStandardBenefitKey(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getBenefitIdentifier());
            standardBenefitBO.setStatus(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getMasterStatus());
            standardBenefitBO.setBusinessDomains(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getBusinessDomains());
            standardBenefitBO.setParentSystemId(deleteBenefitLevelRequest
                    .getBenefitWrapperVO().getStandardBenefitParentKey());
            BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
            benefitLevelBO
                    .setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
            benefitLevelBO.setBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
            benefitLevelBO.setBenefitLevelSeq(1);
            deleteBenefitLevelResponse = (DeleteBenefitLevelResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.DELETE_BENEFIT_LEVEL_RESPONSE);
            if (null != deleteBenefitLevelResponse
                    && (null == deleteBenefitLevelResponse.getMessages() || deleteBenefitLevelResponse
                            .getMessages().size() == 0)) {
                if (bom.delete(benefitLevelBO, standardBenefitBO,
                        deleteBenefitLevelRequest.getUser())) {                	
                    messages = new ArrayList();
                    messages.add(new InformationalMessage(
                            BusinessConstants.MSG_BENEFIT_LEVEL_DELETE));
                    deleteBenefitLevelResponse.setMessages(messages);
                    benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                            .getBenefitDefinitionId());
                    benefitWrapperBO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
                    benefitWrapperVO2 = searchBenefit(benefitWrapperBO,
                            deleteBenefitLevelRequest.getUser());
                }
                if (null != benefitWrapperVO2) {
                    deleteBenefitLevelResponse
                            .setBenefitWrapperVO(benefitWrapperVO2);
                }
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList();
            logParameters.add(deleteBenefitLevelRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return deleteBenefitLevelResponse;
    }*/

    /**
     * Method to delete the levels and lines and fetch the levels and lines of the selected term.
     * @param deleteBenefitLevelRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            DeleteBenefitLevelRequest deleteBenefitLevelRequest)
            throws ServiceException {
    	// Variable Declarations
        DeleteBenefitLevelResponse deleteBenefitLevelResponse = null;
        BenefitWrapperVO benefitWrapperVO = null;
        List messages = null;
        BenefitWrapperBO benefitWrapperBO = null;
        BusinessObjectManagerFactory bomf = null;
        BusinessObjectManager bom = null;
        boolean status = false;
        StandardBenefitBO benefitBO = null;    
        BenefitLevelBO levelBO = null;
        
        try {                 
            // Get the BusinessObjectManager
            bomf = (BusinessObjectManagerFactory) 
						ObjectFactory.getFactory(ObjectFactory.BOM);
            bom = bomf.getBusinessObjectManager();
            
            // Get the response
            deleteBenefitLevelResponse = 
            	(DeleteBenefitLevelResponse) 
					WPDResponseFactory.getResponse
						(WPDResponseFactory.DELETE_BENEFIT_LEVEL_RESPONSE);
            
            if (null != deleteBenefitLevelResponse && 
            		(null == deleteBenefitLevelResponse.getMessages() || 
            		 deleteBenefitLevelResponse.getMessages().size() == 0)) {
            	          	
            	// get the mainObject
            	benefitBO = getMainObjectFroDeleteLevelsLines
									(deleteBenefitLevelRequest);
            	// get the subObject
            	levelBO = getSubObjectFroDeleteLevelsLines
									(deleteBenefitLevelRequest); 
            	// Delete the levels and lines  
            	status = bom.delete(levelBO, benefitBO, 
            				deleteBenefitLevelRequest.getUser());
            	messages = new ArrayList(1);
                benefitWrapperBO = new BenefitWrapperBO();
                String levelToBeDeleted = deleteBenefitLevelRequest.getBenefitLevels();
                String linesToBeDeleted = deleteBenefitLevelRequest.getBenefitLines();
                if(null != levelToBeDeleted & !"".equals(levelToBeDeleted) 
                		&& null != linesToBeDeleted & !"".equals(linesToBeDeleted)){
	                messages.add(new InformationalMessage(
	                        "delete.level.line.success"));
                }
                else if(null != levelToBeDeleted & !"".equals(levelToBeDeleted) 
                		&& (null == linesToBeDeleted || "".equals(linesToBeDeleted))){
	                messages.add(new InformationalMessage(
	                		BusinessConstants.MSG_BENEFIT_LEVEL_DELETE));
                }
                else if(null != linesToBeDeleted & !"".equals(linesToBeDeleted) 
                		&& (null == levelToBeDeleted || "".equals(levelToBeDeleted))){
	                messages.add(new InformationalMessage(
	                		BusinessConstants.MSG_BENEFIT_LINE_DELETE));
                }
                deleteBenefitLevelResponse.setMessages(messages);
                if (status) {                    
                    benefitWrapperBO.setBenefitDefinitionId
								(deleteBenefitLevelRequest.getBenefitDefnId());
                    benefitWrapperBO.setSelectedBenefitTerm
								(deleteBenefitLevelRequest.getSelectedTerm());
                    // locate the levels and lines
                    benefitWrapperVO = searchBenefit(benefitWrapperBO,
                            deleteBenefitLevelRequest.getUser());
                    if (null != benefitWrapperVO) {
                        deleteBenefitLevelResponse
                                .setBenefitWrapperVO(benefitWrapperVO);
                    }
                }
                
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(deleteBenefitLevelRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return deleteBenefitLevelResponse;
    }
    
    /**
     * Method to set the required data in the main 
     * object for deleting the levels and lines.
     * @return
     */
    private StandardBenefitBO 
			getMainObjectFroDeleteLevelsLines
					(DeleteBenefitLevelRequest request){
    	// Variable Declarations
    	StandardBenefitBO benefitBO = null;
    	BenefitWrapperVO wrapperVO = null;
    	
    	if(null != request){
    		// get the benefit wrapperVO from the request
    		wrapperVO = request.getBenefitWrapperVO();
    		if(null != wrapperVO){
    			benefitBO = new StandardBenefitBO();
    			benefitBO.setCreatedUser(wrapperVO.toString());
    			benefitBO.setVersion(wrapperVO.getMasterVersion());
    			benefitBO.setStandardBenefitKey
								(wrapperVO.getStandardBenefitKey());
    			benefitBO.setBenefitIdentifier
								(wrapperVO.getBenefitIdentifier());
    			benefitBO.setStatus(wrapperVO.getMasterStatus());
    			benefitBO.setBusinessDomains(wrapperVO.getBusinessDomains());
    			benefitBO.setParentSystemId
								(wrapperVO.getStandardBenefitParentKey());
    		}
    	}
    	
    	// Return the main object
    	return benefitBO;
    }
    
    /**
     * Method to set the required data in the subobject
     * for deleting the levels and lines.
     * @return
     */
    private BenefitLevelBO 
				getSubObjectFroDeleteLevelsLines
						(DeleteBenefitLevelRequest request){
    	// Variable Decalrations
    	BenefitLevelBO levelBO = null;
    	
    	if(null != request){
    		levelBO = new BenefitLevelBO();
    		levelBO.setBenefitDefinitionId(request.getBenefitDefnId());
    		levelBO.setBenefitLevels(request.getBenefitLevels());
    		levelBO.setBenefitLineIds(request.getBenefitLines());
    	}
    	
    	// Return the Sub Object
    	return levelBO;
    }
    
    /**
     * This method get values from DeleteBenefitLineRequest and is set in
     * StandardBenefitBO so as to delete the BenefitLine details of a particular
     * benefit
     * 
     * 
     * @param deleteBenefitLevelRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteBenefitLineRequest deleteBenefitLineRequest)
            throws ServiceException {
        DeleteBenefitLineResponse deleteBenefitLineResponse = null;
        BenefitWrapperVO benefitWrapperVO2 = null;
        BenefitWrapperVO benefitWrapperVO3 = null;
        try {
            BenefitWrapperVO benefitWrapperVO = deleteBenefitLineRequest
                    .getBenefitWrapperVO();
            BenefitLineVO benefitLineVO = deleteBenefitLineRequest
                    .getBenefiLineVO();
            BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
//            List benefitLevelBOs = new ArrayList();
            List messages = null;
            boolean lineDeletedFlag = false;
            boolean levelDeletedFlag = false;
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setCreatedUser(deleteBenefitLineRequest.getUser()
                    .toString());
            standardBenefitBO.setVersion(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getMasterVersion());
            standardBenefitBO.setStandardBenefitKey(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getStandardBenefitKey());
            standardBenefitBO.setBenefitIdentifier(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getBenefitIdentifier());
            standardBenefitBO.setStatus(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getMasterStatus());
            standardBenefitBO.setBusinessDomains(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getBusinessDomains());
            standardBenefitBO.setParentSystemId(deleteBenefitLineRequest
                    .getBenefitWrapperVO().getStandardBenefitParentKey());
            BenefitLineBO benefitLineBO = new BenefitLineBO();
            benefitLineBO.setBenefitLineId(benefitLineVO.getBenefitLineId());
            benefitLineBO.setBenefitDefenitionId(benefitWrapperVO.getBenefitDefinitionId());
            deleteBenefitLineResponse = (DeleteBenefitLineResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.DELETE_BENEFIT_LINE_RESPONSE);
            if (null != deleteBenefitLineResponse
                    && (null == deleteBenefitLineResponse.getMessages() || deleteBenefitLineResponse
                            .getMessages().size() == 0)) {
                if (bom.delete(benefitLineBO, standardBenefitBO,
                        deleteBenefitLineRequest.getUser())) {
                    benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                            .getBenefitDefinitionId());
                    benefitWrapperBO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
                    messages = new ArrayList(1);
                    messages.add(new InformationalMessage(
                            BusinessConstants.MSG_BENEFIT_LINE_DELETE));
                    deleteBenefitLineResponse.setMessages(messages);
                    benefitWrapperVO2 = searchBenefit(benefitWrapperBO,
                            deleteBenefitLineRequest.getUser());
/*                    //Changes For Performance Fix Start
                    boolean isLevelDeleted = removeBenefitLevelsWithoutLines(
                            benefitWrapperVO2.getBenefitLevelsList(),
                            standardBenefitBO, deleteBenefitLineRequest
                                    .getUser());
                    //Changes For Performance Fix End
                    benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                            .getBenefitDefinitionId());
                    benefitWrapperVO3 = searchBenefit(benefitWrapperBO,
                            deleteBenefitLineRequest.getUser());
                    // ** Performance code start.This is where one unwanted
                    // search is avoided
                    if (isLevelDeleted) {
                        benefitWrapperVO3 = searchBenefit(benefitWrapperBO,
                                deleteBenefitLineRequest.getUser());
                    } else {
                        benefitWrapperVO3 = benefitWrapperVO2;
                    }
                    //** Performance code End
                        if(null != benefitWrapperVO3)
                            benefitWrapperVO3.setDeleteFlag(isLevelDeleted);
                    messages = new ArrayList();
                    messages.add(new InformationalMessage(
                            BusinessConstants.MSG_BENEFIT_LINE_DELETE));
                    deleteBenefitLineResponse.setMessages(messages);*/
                }
                if (null != benefitWrapperVO2) {
                    deleteBenefitLineResponse
                            .setBenefitWrapperVO(benefitWrapperVO2);
                }
             }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(deleteBenefitLineRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return deleteBenefitLineResponse;
    }


    /**
     * This method get values from HideQuestionRequest and is set in
     * StandardBenefitBO so as to hide a particular qn
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(HideQuestionRequest request) throws WPDException {
        // create the response reference
        HideQuestionResponse response = null;
        // create the bo and set the values to it by taking the value from the
        // request
        SelectedQuestionListBO questionListBO = new SelectedQuestionListBO();
        questionListBO.setAdminOptionsSysId(request.getAdminOptionSysId());
        
//        questionListBO.setQuestionNumber(request.getQuestionNumber());
        questionListBO.setQuestionList(request.getQuestionNumbers());
        // create the main obect
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        // set the required things to the main object
        standardBenefitBO
                .setStandardBenefitKey(request.getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(request.getBenefitIdentifier());
        standardBenefitBO.setVersion(request.getVersion());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());

        // get the business object manager
        BusinessObjectManager bom = getBOM();
        // create the response object
        response = (HideQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.HIDE_QUESTION_RESPONSE);
        // call the delete method in the bom
        bom.delete(questionListBO, standardBenefitBO, request.getUser());
        // set the message in teh response
        response.addMessage(new InformationalMessage(
                "standard.benefit.hide.question"));
        // return the response
        return response;
    }
    
    
    /**
     * This method get values from PossibleAnswersRequest and is set in
     * possibleAnswersLocateCriteria so as to search for the PossibleAnswers
     * already stored, for a particluar qn
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(PossibleAnswersRequest request)
            throws WPDException {
        // create the response reference
        PossibleAnswersResponse possibleAnswersResponse = null;
        // get the the business object manager object
        BusinessObjectManager bom = getBOM();
        // create the locate criteria object
        PossibleAnswersLocateCriteria possibleAnswersLocateCriteria = new PossibleAnswersLocateCriteria();
        // get the questionNumber from the request and set to this
        // locateCriteria based on which the search will be made
        possibleAnswersLocateCriteria.setQuestionNumber(request
                .getQuestionNumber());
        possibleAnswersLocateCriteria.setMaximumResultSize(request
                .getMaxSearchResultSize());
        
        // create the response object
        possibleAnswersResponse = (PossibleAnswersResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.POSSIBLE_ANSWERS_RESPONSE);
        // call the locate method to get the answersList
        LocateResults locateResults = bom.locate(possibleAnswersLocateCriteria,
                request.getUser());
        // set the answersList in the response
        possibleAnswersResponse.setPossibleAnswers(locateResults
                .getLocateResults());
        // return the response
        return possibleAnswersResponse;
    }


    /**
     * This method get values from DataTypeRetrieveRequest and is set in
     * DataTypeLocateCriteria so as to retrieve the datatype
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(DataTypeRetrieveRequest dataTypeRetrieveRequest)
            throws WPDException {
        DataTypeRetrieveResponse dataTypeRetrieveResponse = null;
        LocateResults dataTypeResults = null;
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        DataTypeLocateCriteria dataTypeLocateCriteria = new DataTypeLocateCriteria();
        dataTypeLocateCriteria.setStandardBenefitkey(dataTypeRetrieveRequest
                .getStandardBenefitKey());
        dataTypeResults = bom.locate(dataTypeLocateCriteria,
                dataTypeRetrieveRequest.getUser());
        if (null != dataTypeResults) {
            dataTypeRetrieveResponse = (DataTypeRetrieveResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.DATA_TYPE_RETRIEVE_RESPONSE);
            dataTypeRetrieveResponse.setDataTypeList(dataTypeResults
                    .getLocateResults());
        }
        return dataTypeRetrieveResponse;
    }


    /**
     * This method get values from SearchBenefitLevelRequest and is set in
     * BenefitWrapperBO so as to search the benefit level
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            SearchBenefitLevelRequest searchBenefitLevelRequest)
            throws ServiceException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "StandaredBenefitBusinessService", "execute()"));
        SearchBenefitLevelResponse searchBenefitLevelResponse = null;
        BenefitWrapperVO benefitWrapperVO2 = null;
        List errorList = null;
        try {
            BenefitWrapperVO benefitWrapperVO = searchBenefitLevelRequest
                    .getBenefitWrapperVO();
            BenefitWrapperBO benefitWrapperBO = new BenefitWrapperBO();
            benefitWrapperBO.setBenefitDefinitionId(benefitWrapperVO
                    .getBenefitDefinitionId());
            benefitWrapperBO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
            benefitWrapperVO2 = searchBenefit(benefitWrapperBO,
                    searchBenefitLevelRequest.getUser());
            if (null != benefitWrapperVO2) {
                searchBenefitLevelResponse = (SearchBenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.SEARCH_BENEFIT_LEVEL_RESPONSE);
                searchBenefitLevelResponse
                        .setBenefitWrapperVO(benefitWrapperVO2);
            } else {
                errorList = new ArrayList(1);
                errorList.add(new InformationalMessage(
                        BusinessConstants.MSG_BENEFIT_MANDATE_SEARCH_RESULTS));
                searchBenefitLevelResponse = (SearchBenefitLevelResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.SEARCH_BENEFIT_LEVEL_RESPONSE);
                searchBenefitLevelResponse.setMessages(errorList);
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(searchBenefitLevelRequest);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo(th.endPerfLogging());
        return searchBenefitLevelResponse;
    }
    
    public WPDResponse execute(
    		SearchBenefitLevelTermRequest searchBenefitLevelTermRequest) throws ServiceException{
    	SearchBenefitLevelTermResponse searchBenefitLevelTermResponse =null;
    	try{
		    BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		    BusinessObjectManager bom = bomf.getBusinessObjectManager();
		    List benefitLevelTermSearchResultsList = null;
//		    List errorList = new ArrayList();
		    LocateResults locateResults = null;
		    BenefitLevelTermLocateCriteria benefitLevelTermLocateCriteria =new BenefitLevelTermLocateCriteria();		    
		    benefitLevelTermLocateCriteria.setBenefitDefinitionId(searchBenefitLevelTermRequest.getBenefitDefinitionId());
		    locateResults = bom.locate(benefitLevelTermLocateCriteria,searchBenefitLevelTermRequest.getUser());
		    benefitLevelTermSearchResultsList = locateResults.getLocateResults();
		    searchBenefitLevelTermResponse = (SearchBenefitLevelTermResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_BENEFIT_LEVEL_TERM_RESPONSE);
		    searchBenefitLevelTermResponse.setTermList(benefitLevelTermSearchResultsList);

    	}catch(Exception ex){
            List logParameters = new ArrayList(1);
            String logMessage = "Failed while processing execute SearchBenefitLevelTermRequest";
            throw new ServiceException(logMessage, logParameters, ex);    		
    	}
    	return searchBenefitLevelTermResponse;
    }


    /**
     * This method get values from RuleRequest and is set in RuleLocateCriteria
     * so as to search the rules
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(RuleRequest request) throws ServiceException {
//        List errorList = null;
    	int action = request.getAction();
        BusinessObjectManager bom = getBOM();
        List messageList;
        RuleResponse response = (RuleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RULE_RESPONSE);
        if(request.getRuleId()== null || "".equals(request.getRuleId())){
	        RuleLocateCriteria criteria = new RuleLocateCriteria();
	        criteria.setSearchRuleID(request.getSearchRuleID());
	        if(null!=request.getSearchString()&& !("").equals(request.getSearchString())){
	        		criteria.setSearchString(request.getSearchString());
	        }
	        StandardBenefitBusinessObjectBuilder benefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
	        try {
	        if(action==1){
	            LocateResults locateResults = benefitBusinessObjectBuilder.filterRuleLocate(
	                    criteria, request.getUser());
	            List ruleList = locateResults.getLocateResults();
	            if (ruleList != null) {
	                response.setRuleList(ruleList);
	            }
	        }else{
	        	LocateResults locateResults = benefitBusinessObjectBuilder.locate(
	                    criteria, request.getUser());
	            List ruleList = locateResults.getLocateResults();
	            if (ruleList != null) {
	                response.setRuleList(ruleList);
	            }
	            if(null==ruleList ||ruleList.size()==0){
	        	  	messageList = new ArrayList(2);
	        	  	messageList.add(new InformationalMessage(
	        				BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
	        	  	 addMessagesToResponse(response, messageList);
	        	  }
	        }
	        } catch (WPDException e) {
	            List logParameters = new ArrayList(1);
	            logParameters.add(request);
	            String logMessage = "Failed while processing RuleRequest";
	            throw new ServiceException(logMessage, logParameters, e);
	        }
        }else{
        	try{
        		StandardBenefitBusinessObjectBuilder benefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
        		RuleBO ruleBo = benefitBusinessObjectBuilder.retrieveRuleInfo(request.getRuleId());
        		List ruleList = new ArrayList();
        		ruleList.add(ruleBo);
        		response.setRuleList(ruleList);
        	}catch(WPDException e ){
        		List logParameters = new ArrayList(1);
	            logParameters.add(request);
	            String logMessage = "Failed while processing Rule Retrieve Request";
	            throw new ServiceException(logMessage, logParameters, e);
        	}
        }
       
        return response;
    }
    /**
     * Method to set the list of messages to response.
     * 
     * @param response
     *            WPDResponse
     * @param messages
     *            List.
     */
    private void addMessagesToResponse(WPDResponse response, List messages)
    {
      if (null == messages || messages.size() == 0)
        return;
      if (null == response.getMessages())
        response.setMessages(messages);
      else
        response.getMessages().addAll(messages);
    }
    public WPDResponse execute(GroupRuleRequest request) throws ServiceException {
//        List errorList = null;
        GroupRuleResponse response = (GroupRuleResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.GROUP_RULE_RESPONSE);
    	try{
    		StandardBenefitBusinessObjectBuilder benefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
    		RuleBO ruleBo = benefitBusinessObjectBuilder.retrieveRuleInfo(request.getRuleId());
    		List ruleList = benefitBusinessObjectBuilder.locateGroupRuleInfo(request.getRuleId());
    		GroupRule grpRule = new GroupRule();
    		grpRule.setRuleId(ruleBo.getRuleId());
    		grpRule.setRuleDesc(ruleBo.getRuleDesc());
    		grpRule.setSearchWord1(ruleBo.getSearchWord1());
    		grpRule.setSearchWord2(ruleBo.getSearchWord2());
    		grpRule.setSearchWord3(ruleBo.getSearchWord3());
    		grpRule.setRuleVersion(ruleBo.getRuleVersion());
    		grpRule.setTag(ruleBo.getTag());
    		grpRule.setRules(ruleList);
    		response.setRule(grpRule);
    	}catch(WPDException e ){
    		List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing Group Rule Retrieve Request";
            throw new ServiceException(logMessage, logParameters, e);
    	}
        return response;
    }


    /**
     * This method get values from StandardBenefitCheckInRequest and is set in
     * StandardBenefitBO so as to checkin a particular benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitCheckInRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(StandardBenefitCheckInRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit CheckIn");
        List messageList = new ArrayList(1);
        List errorMessages = new ArrayList(1);
     
        BusinessObjectManager bom = getBOM();
     
        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = getStandardBenefitObject(standardBenefitVO);
   
        StandardBenefitCheckInResponse standardBenefitCheckInResponse = (StandardBenefitCheckInResponse) (new ValidationServiceController()
                .execute(request));

//            AdminMethodRequest adminMethodRequest = new AdminMethodRequest();
//            adminMethodRequest.setEntityId(request.getStandardBenefitVO().getStandardBenefitKey());
//            adminMethodRequest.setEntityType("STANDARDBENEFIT");
//            adminMethodRequest.setBenefitName(request.getStandardBenefitVO().getBenefitIdentifier());
//            AdminMethodResponse adminMethodResponse = (AdminMethodResponse) (new ValidationServiceController()
//                    .execute(adminMethodRequest));
//            if(null != adminMethodResponse){
//	            if(null != adminMethodResponse.getMessages()&& adminMethodResponse.getMessages().size() > 0){
//	                errorMessages = adminMethodResponse.getMessages();
//	                if(standardBenefitCheckInResponse.isCheckinSuccess()){
//	                    
//	                    standardBenefitCheckInResponse.setMessages(adminMethodResponse.getMessages());
//	                }else{
//	                    standardBenefitCheckInResponse.addMessage((Message)errorMessages.get(0));
//	                }
//	                    standardBenefitCheckInResponse.setValidationSuccess(false);
//	                    adminMethodResponse.setValidationSuccess(false);
//	            }else{
//	                adminMethodResponse.setValidationSuccess(true);
//	            }
//            }
            
            
        try {
        	
            if (!(standardBenefitVO.isUpdateFromDone())) {
                if (standardBenefitCheckInResponse.isValidationSuccess() ) {
                    StandardBenefitBO oldKeyStandardBenefitBO = new StandardBenefitBO();
                    StandardBenefitVO oldKeystandardBenefitVO = (StandardBenefitVO) request
                            .getOldKeystandardBenefitVO();
                    oldKeyStandardBenefitBO
                            .setStandardBenefitKey(oldKeystandardBenefitVO
                                    .getStandardBenefitKey());
                    oldKeyStandardBenefitBO
                            .setBenefitIdentifier(oldKeystandardBenefitVO
                                    .getBenefitIdentifier());
                    oldKeyStandardBenefitBO
                            .setParentSystemId(oldKeystandardBenefitVO
                                    .getStandardBenefitParentKey());
                    oldKeyStandardBenefitBO
                            .setBusinessDomains(oldKeystandardBenefitVO
                                    .getBusinessDomains());
                    oldKeyStandardBenefitBO.setStatus(oldKeystandardBenefitVO
                            .getStatus());
                    oldKeyStandardBenefitBO.setVersion(oldKeystandardBenefitVO
                            .getVersion());

                    if (isKeyValueChanged(standardBenefitBO,
                            oldKeyStandardBenefitBO)) {
                        bom.changeIdentity(oldKeyStandardBenefitBO,
                                standardBenefitBO, request.getUser());
                    } else {
                        //standardBenefitBO.setRuleTypeList(request.getStandardBenefitVO().getRuleTypeList());
                        bom
                                .persist(standardBenefitBO, request.getUser(),
                                        false);
                    }
                    standardBenefitCheckInResponse
                            .addMessage(new InformationalMessage(
                                    BusinessConstants.MSG_BNFT_UPDATE_SUCCESS));               
                }	
                
//              CheckInValidation 'CR' Start
                //if (standardBenefitCheckInResponse.isCheckinSuccess()) {
//CheckInValidation 'CR' End                    	
                    if (standardBenefitVO.isCheckIn()) {
                        // reference data validations
                        RefDataDomainValidationRequest refDataDomainValidationRequest = refDataBenefitValidation(request);
                        RefDataDomainValidationResponse refDataDomainValidationResponse = new RefDataDomainValidationResponse();
                        refDataDomainValidationResponse=(RefDataDomainValidationResponse) new ValidationServiceController().execute(refDataDomainValidationRequest);
                    	    	
                    	if(!refDataDomainValidationResponse.isSuccess()){
                    	    ErrorMessage errorMessage = new ErrorMessage(WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
                    	    errorMessage.setParameters (new String[] {" "+refDataDomainValidationResponse.getErrorMessage()} );
                    	    messageList.add(errorMessage);
// CheckInValidation 'CR' Start
                            standardBenefitCheckInResponse.addMessage(errorMessage);
// CheckInValidation 'CR' End                      	    
                    	}
//CheckInValidation 'CR' Start                        	
                    	//if(refDataDomainValidationResponse.isSuccess()){
                    	if(refDataDomainValidationResponse.isSuccess() &&
                    			standardBenefitCheckInResponse.isCheckinSuccess() &&
								standardBenefitCheckInResponse.isValidationSuccess() 
//								&&
//								null != adminMethodResponse &&
//								adminMethodResponse.isValidationSuccess()
								){
//CheckInValidation 'CR' End    
                    		//to delete the version 0  using the delete proc() after check in
                    		
//                    	 Updating the term SPS mapping for new aggregate terms
                   		 updateLineTermToSpsMapping(standardBenefitBO.getStandardBenefitKey(),request.getUser().getUserId() );
                    		standardBenefitBO.setAction("CHECK IN");
                    	    bom.checkIn(standardBenefitBO, request.getUser());
                    	    InformationalMessage informationalMessage = new InformationalMessage(
                            "benefit.checkin.success");
                    	    informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
                    	    messageList.add(informationalMessage);
// CheckInValidation 'CR' Start
                            standardBenefitCheckInResponse
                                    .setMessages(messageList);
// CheckInValidation 'CR' End                      	    
                    	}                    	
                       // messageList = standardBenefitCheckInResponse
                            //    .getMessages();
                    //    messageList.remove(0);
// CheckInValidation 'CR' Start
                        /*standardBenefitCheckInResponse
                                .setMessages(messageList);*/
// CheckInValidation 'CR' End                         
                    }
//CheckInValidation 'CR' Start                        
                //}  
//CheckInValidation 'CR' End         
            } else {
// CheckInValidation 'CR' Start            	
                //if (standardBenefitCheckInResponse.isCheckinSuccess() && adminMethodResponse.isValidationSuccess()) {
// CheckInValidation 'CR' End                	
                   if (standardBenefitVO.isCheckIn()) {
                        standardBenefitBO.setRuleTypeList(request
                                .getStandardBenefitVO().getRuleTypeList());
                        // reference data validations
                        RefDataDomainValidationRequest refDataDomainValidationRequest = refDataBenefitValidation(request);
                        
                        RefDataDomainValidationResponse refDataDomainValidationResponse=(RefDataDomainValidationResponse) new ValidationServiceController().execute(refDataDomainValidationRequest);
                    	    	
                    	if(!refDataDomainValidationResponse.isSuccess()){
                    	    ErrorMessage errorMessage = new ErrorMessage(WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
                    	    errorMessage.setParameters (new String[] {" "+refDataDomainValidationResponse.getErrorMessage()} );
                    	    messageList.add(errorMessage);
// CheckInValidation 'CR' Start
                    	    standardBenefitCheckInResponse.addMessage(errorMessage);
// CheckInValidation 'CR' End                    	    
                    	}
// CheckInValidation 'CR' Start                     	
                    	//if(refDataDomainValidationResponse.isSuccess()){
                    	if(refDataDomainValidationResponse.isSuccess() &&
                    			standardBenefitCheckInResponse.isCheckinSuccess() &&
								standardBenefitCheckInResponse.isValidationSuccess() 
//								&&
//								null != adminMethodResponse &&
//								adminMethodResponse.isValidationSuccess()
								){
// CheckInValidation 'CR' End                     		
                    		//  to delete the version 0  using the delete proc() after check in	
                    		// Updating the term SPS mapping for new aggregate terms
                    		 updateLineTermToSpsMapping(standardBenefitBO.getStandardBenefitKey(),request.getUser().getUserId() );
                    		 
                    		standardBenefitBO.setAction("CHECK IN");
                    	    bom.checkIn(standardBenefitBO, request.getUser());
                    	    InformationalMessage informationalMessage = new InformationalMessage(
                            "benefit.checkin.success");
                    	    informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
                    	    messageList.add(informationalMessage);
// CheckInValidation 'CR' Start                    	
                            standardBenefitCheckInResponse.setMessages(messageList);
// CheckInValidation 'CR' End                     	    
                    	}
// CheckInValidation 'CR' Start                    	
                        //standardBenefitCheckInResponse.setMessages(messageList);
                    	else{
                    		standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                                    standardBenefitBO, request.getUser());
                    	}
// CheckInValidation 'CR' End                        
                         
                    } else {
                        standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                                standardBenefitBO, request.getUser());
                    }
//                 CheckInValidation 'CR' Start                    
                /*} else {
                    standardBenefitBO = (StandardBenefitBO) bom.retrieve(
                            standardBenefitBO, request.getUser());
                }*/
//              CheckInValidation 'CR' End                
            }
            //long benefitDefinitionId
            standardBenefitCheckInResponse.setDomainDetail(DomainUtil
                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
                            .getParentSystemId()));
            standardBenefitCheckInResponse
                    .setStandardBenefitBO(standardBenefitBO);

        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing StandardBenefitCheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit CheckIn");
        return standardBenefitCheckInResponse;

    }


    /**
	 * @param i
     * @throws SevereException 
	 */
	private void updateLineTermToSpsMapping(long benefitSysId, String userId) throws SevereException {
		
		StandardBenefitBusinessObjectBuilder benefitBusinessObjectBuilder= new StandardBenefitBusinessObjectBuilder();
		
		benefitBusinessObjectBuilder.updateLineTermToSpsMapping(benefitSysId, userId);
		
	}


	/**
     * This method get values from BenefitLevelPopUpRequest and is set in
     * BenefitPopUpLocateCriteria so as to search for a benefit level
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param benefitLevelPopUpRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(BenefitLevelPopUpRequest benefitLevelPopUpRequest)
            throws WPDException {
        BenefitLevelPopUpResponse benefitLevelPopUpResponse = null;
        List messages = null;
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            List searchResultsList = null;
            LocateResults locateResults = null;
            BenefitPopUpLocateCriteria benefitPopUpLocateCriteria = new BenefitPopUpLocateCriteria();
            benefitPopUpLocateCriteria
                    .setStandardBenefitId(benefitLevelPopUpRequest
                            .getStandardBenfefitId());
            benefitPopUpLocateCriteria.setPopUpId(benefitLevelPopUpRequest
                    .getPopUpId());
            locateResults = bom.locate(benefitPopUpLocateCriteria,
                    benefitLevelPopUpRequest.getUser());
            int searchResultCount = locateResults.getTotalResultsCount();
            searchResultsList = locateResults.getLocateResults();
            if (null != searchResultsList && searchResultsList.size() > 0) {
                benefitLevelPopUpResponse = new BenefitLevelPopUpResponse();
                benefitLevelPopUpResponse
                        .setSearchResultList(searchResultsList);
            } else {
                benefitLevelPopUpResponse = new BenefitLevelPopUpResponse();
                messages = new ArrayList(1);
                messages.add(new InformationalMessage(WebConstants.BENLEVEL_POPUP_NO_RESULTS));
                benefitLevelPopUpResponse.setMessages(messages);
            }

        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitLevelPopUpRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return benefitLevelPopUpResponse;

    }


    /**
     * This method get values from ScheduleForTestSBRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to
     * schedule fot test
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param ScheduleForTestSBRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(ScheduleForTestSBRequest scheduleForTestSBRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): ScheduleForTest Standard Benefit");
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        // Setting the values from the request to the StandardBenefitBO
        standardBenefitBO.setStandardBenefitKey(scheduleForTestSBRequest
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(scheduleForTestSBRequest
                .getStandardBenefitName());
        standardBenefitBO.setVersion(scheduleForTestSBRequest
                .getStandardBenefitVersion());
        standardBenefitBO.setParentSystemId(scheduleForTestSBRequest
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(scheduleForTestSBRequest
                .getBusinessDomains());
        standardBenefitBO.setStatus(scheduleForTestSBRequest
                .getStandardBenefitStatus());

        //Create Response object
        ScheduleForTestSBResponse response = (ScheduleForTestSBResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SCHEDULE_FOR_TEST_STD_BEN_RESPONSE);
        try {
            // validation
            response = (ScheduleForTestSBResponse) (new ValidationServiceController()
                    .execute(scheduleForTestSBRequest));
            if (response.isSuccess()) {
                // validation end
                //call the bom s scheduleForTest method
                bom.scheduleForTest(standardBenefitBO, scheduleForTestSBRequest
                        .getUser());
                //Set the message for success
                response.addMessage(new InformationalMessage(
                        WebConstants.SCHEDULE_FOR_TEST_BEN));
            }

            //search change
            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) scheduleForTestSBRequest
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM().locate(
                        standardBenefitViewAllLocateCriteria,
                        scheduleForTestSBRequest.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        scheduleForTestSBRequest.getUser());
            if(null != locateResults.getLocateResults() && locateResults.getLocateResults().size() > 0){//1/8
	            List voList = new ArrayList();
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
	                standardBenefitVO.setDescription(standardBenefitBO
	                        .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            if (locateResults.getLocateResults().size() >= 50) {
	                response.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
	            response.setSearchResultList(locateResults.getLocateResults());
            }//1/8 end
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(scheduleForTestSBRequest);
            String logMessage = "Failed while processing ScheduleForTestSBRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): ScheduleForTest Standard Benefit");
        return response;
    }


    /**
     * This method get values from TestPassStandardBenefitRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to test
     * pass
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param TestPassStandardBenefitRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            TestPassStandardBenefitRequest testPassStandardBenefitRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Test Pass Standard Benefit");
        TestPassStandardBenefitResponse response = null;
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        // Setting the values from the request to the StandardBenefitBO
        standardBenefitBO.setStandardBenefitKey(testPassStandardBenefitRequest
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(testPassStandardBenefitRequest
                .getStandardBenefitIdentifier());
        standardBenefitBO.setVersion(testPassStandardBenefitRequest
                .getStandardBenefitVersion());
        standardBenefitBO.setParentSystemId(testPassStandardBenefitRequest
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(testPassStandardBenefitRequest
                .getBusinessDomains());
        standardBenefitBO.setStatus(testPassStandardBenefitRequest
                .getStandardBenefitStatus());
        try {
            //Create Response object
            response = (TestPassStandardBenefitResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.TEST_PASS_STD_BEN_RESPONSE);
            //call the bom s scheduleForTest method
            bom.testPass(standardBenefitBO, testPassStandardBenefitRequest
                    .getUser());
            //Set the message for success
            response.addMessage(new InformationalMessage(
                    WebConstants.TEST_PASS_BEN));

            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) testPassStandardBenefitRequest
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM().locate(
                        standardBenefitViewAllLocateCriteria,
                        testPassStandardBenefitRequest.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        testPassStandardBenefitRequest.getUser());

            List voList = new ArrayList();
            Iterator iterator = locateResults.getLocateResults().iterator();
            while (iterator.hasNext()) {
                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
                standardBenefitBO = (StandardBenefitBO) iterator.next();
                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
                        .getBenefitIdentifier());
                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
                standardBenefitVO.setDescription(standardBenefitBO
                        .getDescription());
                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
                standardBenefitVO.setState(standardBenefitBO.getState());
                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
                        .getStandardBenefitKey());
                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
                        .getParentSystemId());
                standardBenefitVO.setBusinessDomains(standardBenefitBO
                        .getBusinessDomains());
                voList.add(standardBenefitVO);
            }
            locateResults.setLocateResults(voList);
            if (locateResults.getLocateResults().size() >= 50) {
                response.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
            }
            response.setSearchResultList(locateResults.getLocateResults());
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(testPassStandardBenefitRequest);
            String logMessage = "Failed while processing TestPassStandardBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Test Pass Standard Benefit");
        return response;
    }


    /**
     * This method get values from TestFailStandardBenefitRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to test
     * fail
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param TestFailStandardBenefitRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            TestFailStandardBenefitRequest testFailStandardBenefitRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Test Fail Standard Benefit");
        BusinessObjectManager bom = getBOM();
        //  Create Response object
    	TestFailStandardBenefitResponse response = (TestFailStandardBenefitResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_FAIL_STD_BEN_RESPONSE);
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        
        // Setting the values from the request to the StandardBenefitBO
        standardBenefitBO.setStandardBenefitKey(testFailStandardBenefitRequest
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(testFailStandardBenefitRequest
                .getStandardBenefitIdentifier());
        standardBenefitBO.setVersion(testFailStandardBenefitRequest
                .getStandardBenefitVersion());
        standardBenefitBO.setParentSystemId(testFailStandardBenefitRequest
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(testFailStandardBenefitRequest
                .getBusinessDomains());
        standardBenefitBO.setStatus(testFailStandardBenefitRequest
                .getStandardBenefitStatus());
        try {
            
            //call the bom s scheduleForTest method
            bom.testFail(standardBenefitBO, testFailStandardBenefitRequest
                    .getUser());
            //Set the message for success
            response.addMessage(new InformationalMessage(
                    WebConstants.TEST_FAIL_BEN));

            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) testFailStandardBenefitRequest
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM().locate(
                        standardBenefitViewAllLocateCriteria,
                        testFailStandardBenefitRequest.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        testFailStandardBenefitRequest.getUser());

            List voList = new ArrayList();
            if(null!=locateResults.getLocateResults() && locateResults.getLocateResults().size() > 0){//1/8
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
	                standardBenefitVO.setDescription(standardBenefitBO
	                        .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            if (locateResults.getLocateResults().size() >= 50) {
	                response.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
	            response.setSearchResultList(locateResults.getLocateResults());
            }//1/8 end
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(testFailStandardBenefitRequest);
            String logMessage = "Failed while processing TestFailStandardBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Test Fail Standard Benefit");
        return response;
    }


    private StandardBenefitBO getStandardBenefitObject(
            StandardBenefitVO stdBenefitVO) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getStandardBenefitObject(): StandardBenefitVO");
        StandardBenefitBO stdBenefitBO = new StandardBenefitBO();
        stdBenefitBO
                .setStandardBenefitKey(stdBenefitVO.getStandardBenefitKey());
        stdBenefitBO.setBenefitIdentifier(stdBenefitVO.getBenefitIdentifier());

        List lobList = stdBenefitVO.getLobList();
        List businessEntityList = stdBenefitVO.getBusinessEntityList();
        List businessGroupList = stdBenefitVO.getBusinessGroupList();
        List marketBusinessUnit = stdBenefitVO.getMarketBusinessUnitList();
        stdBenefitBO.setBusinessDomains(BusinessUtil.convertToDomains(lobList,
                businessEntityList, businessGroupList, marketBusinessUnit));

        stdBenefitBO.setDataTypeList(stdBenefitVO.getDataTypeList());
        stdBenefitBO.setDescription(stdBenefitVO.getDescription());
        stdBenefitBO.setTermList(stdBenefitVO.getTermList());
        stdBenefitBO.setQualifierList(stdBenefitVO.getQualifierList());
        stdBenefitBO.setPVAList(stdBenefitVO.getPVAList());
        stdBenefitBO.setStatus(stdBenefitVO.getStatus());
        stdBenefitBO.setVersion(stdBenefitVO.getVersion());

        //New code for Enhancement
        stdBenefitBO.setMandateType(stdBenefitVO.getMandateType());
        stdBenefitBO.setBenefitType(stdBenefitVO.getBenefitType());
        stdBenefitBO.setStateCode(stdBenefitVO.getStateId());
        stdBenefitBO.setStateDesc(stdBenefitVO.getStateDesc());
        stdBenefitBO.setRuleTypeList(stdBenefitVO.getRuleTypeList());
                //Enhancement: Benefit Category
        stdBenefitBO.setBenefitCategory(stdBenefitVO.getBenefitCategory());
        //end of new code for enhancement

        stdBenefitBO.setParentSystemId(stdBenefitVO
                .getStandardBenefitParentKey());
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getStandardBenefitObject(): StandardBenefitVO");
        return stdBenefitBO;
    }


    /**
     * This method get values from PublishStandardBenefitRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to
     * publish
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param PublishStandardBenefitRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(PublishStandardBenefitRequest request)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Publish Standard Benefit");
        PublishStandardBenefitResponse response = (PublishStandardBenefitResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PUBLISH_STD_BENEFIT_RESPONSE);
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO
                .setStandardBenefitKey(request.getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(request
                .getStandardBenefitIdentifier());
        standardBenefitBO.setVersion(request.getStandardBenefitVersion());
        standardBenefitBO.setStatus(request.getStandardBenefitStatus());
        standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
        standardBenefitBO.setParentSystemId(request
                .getStandardBenefitParentKey());

        try {
            getBOM().publish(standardBenefitBO, request.getUser());
            InformationalMessage informationalMessage = new InformationalMessage(
                    WebConstants.BENEFIT_PUBLISH);
            informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
            response.addMessage(informationalMessage);
            //search change
            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) request
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM()
                        .locate(standardBenefitViewAllLocateCriteria,
                                request.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        request.getUser());

            List voList = new ArrayList();
            Iterator iterator = locateResults.getLocateResults().iterator();
            while (iterator.hasNext()) {
                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
                standardBenefitBO = (StandardBenefitBO) iterator.next();
                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
                        .getBenefitIdentifier());
                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
                standardBenefitVO.setDescription(standardBenefitBO
                        .getDescription());
                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
                standardBenefitVO.setState(standardBenefitBO.getState());
                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
                        .getStandardBenefitKey());
                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
                        .getParentSystemId());
                standardBenefitVO.setBusinessDomains(standardBenefitBO
                        .getBusinessDomains());
                voList.add(standardBenefitVO);
            }
            locateResults.setLocateResults(voList);
            if (locateResults.getLocateResults().size() >= 50) {
                response.addMessage(new InformationalMessage(
                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
            }
            response.setSearchResultList(locateResults.getLocateResults());
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing PublishStandardBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Publish Standard Benefit");
        return response;
    }
    
    	
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(StandardBenefitUnLockRequest request)
		    throws ServiceException {
		Logger
		        .logInfo("StandardBenefitBusinessService - Entering execute(): unlock Standard Benefit");
		
		StandardBenefitUnLockResponse response = (StandardBenefitUnLockResponse) WPDResponseFactory
		        .getResponse(WPDResponseFactory.UNLOCK_STD_BENEFIT_RESPONSE);
		StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
		standardBenefitBO
		        .setStandardBenefitKey(request.getStandardBenefitKey());
		standardBenefitBO.setBenefitIdentifier(request
		        .getStandardBenefitIdentifier());
		standardBenefitBO.setVersion(request.getStandardBenefitVersion());
		standardBenefitBO.setStatus(request.getStandardBenefitStatus());
		standardBenefitBO.setBusinessDomains(request.getBusinessDomains());
		standardBenefitBO.setParentSystemId(request
		        .getStandardBenefitParentKey());
		
		try {
			getBOM().unlock(standardBenefitBO, request.getUser());
						
			InformationalMessage informationalMessage = new InformationalMessage(
		            WebConstants.BENEFIT_UNLOCK);
		    informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
		    response.addMessage(informationalMessage);
		    //search change
		    StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) request
		            .getStandardBenefitSearchVO();
		    StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
		    LocateResults locateResults = null;
		    //Searching using a benefitkey is applicable only for view all
		    // version functionality.
		    if (standardBenefitSearchVO.getBenefitKey() > 0) {
		        StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
		        locateResults = getBOM()
		                .locate(standardBenefitViewAllLocateCriteria,
		                        request.getUser());
		    } else
		        locateResults = getBOM().locate(standardBenefitLocateCriteria,
		                request.getUser());
		
		    List voList = new ArrayList();
		    Iterator iterator = locateResults.getLocateResults().iterator();
		    while (iterator.hasNext()) {
		        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
		        standardBenefitBO = (StandardBenefitBO) iterator.next();
		        standardBenefitVO.setBenefitIdentifier(standardBenefitBO
		                .getBenefitIdentifier());
		        standardBenefitVO.setVersion(standardBenefitBO.getVersion());
		        standardBenefitVO.setDescription(standardBenefitBO
		                .getDescription());
		        standardBenefitVO.setStatus(standardBenefitBO.getStatus());
		        standardBenefitVO.setState(standardBenefitBO.getState());
		        standardBenefitVO.setStandardBenefitKey(standardBenefitBO
		                .getStandardBenefitKey());
		        standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
		                .getParentSystemId());
		        standardBenefitVO.setBusinessDomains(standardBenefitBO
		                .getBusinessDomains());
		        voList.add(standardBenefitVO);
		    }
		    locateResults.setLocateResults(voList);
		    if (locateResults.getLocateResults().size() >= 50) {
		        response.addMessage(new InformationalMessage(
		                BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
		    }
		    response.setSearchResultList(locateResults.getLocateResults());
		    // search end
		} catch (WPDException ex) {
		    List logParameters = new ArrayList(1);
		    logParameters.add(request);
		    String logMessage = "Failed while processing unlock StandardBenefitRequest";
		    throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
		        .logInfo("StandardBenefitBusinessService - Returning execute(): UnLock Standard Benefit");
		return response;
		}


    /**
     * This method get values from AdminLevelRequest and is set in
     * AdminLevelLocateCriteria so as to get the details of AdminLevel
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param AdminLevelRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(AdminLevelRequest request)
            throws ServiceException {
        AdminLevelResponse response = (AdminLevelResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ADMIN_LEVEL_RESPONSE);
        AdminLevelLocateCriteria criteria = new AdminLevelLocateCriteria();
        try {
            LocateResults locateResults = getBOM().locate(criteria,
                    request.getUser());
            response.setAdminLevelList(locateResults.getLocateResults());
            
        } catch (WPDException e) {
        	 List logParameters = new ArrayList(1);
             logParameters.add(request);
             String logMessage = "Failed while processing StandardBenefitCheckOutRequest";
             throw new ServiceException(logMessage, logParameters, e);
        }
        return response;
    }


    /**
     * This method get values from StandardBenefitCheckOutRequest and is set in
     * StandardBenefitBO so as to checkout the benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitCheckOutRequest
     * @return
     * @throws WPDException
     */

    public WPDResponse execute(
            StandardBenefitCheckOutRequest standardBenefitCheckOutRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): CheckOut Standard Benefit");
        StandardBenefitCheckOutResponse standardBenefitCheckOutResponse = (StandardBenefitCheckOutResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.STANDARD_BENEFIT_CHECKOUT_RESPONSE);
        StandardBenefitBO standardBenefitBO = getStandardBenefitCheckOutObject(standardBenefitCheckOutRequest);
        int parentKey = standardBenefitBO.getParentSystemId();
        try {
            standardBenefitBO = (StandardBenefitBO) getBOM()
                    .checkOut(standardBenefitBO,
                            standardBenefitCheckOutRequest.getUser());

            //          Code Enhancement:::::BO for performing the search for Rule Id in
            // the new table
            AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
            assignedRuleIdBO.setEntitySystemId(standardBenefitBO
                    .getStandardBenefitKey());
            assignedRuleIdBO.setEntityType("reference");
            StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
            RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
            List searchRuleId = refdataAdapterManager
                    .searchRuleId(assignedRuleIdBO);
            if (null != searchRuleId) {
                if (searchRuleId.size() > 0) {

                    standardBenefitBO = builder.prepareRuleIdBOList(
                            searchRuleId, standardBenefitBO);
                }
            }
            //End of Code Enhancement

            standardBenefitCheckOutResponse
                    .setStandardBenefitBO(standardBenefitBO);
            standardBenefitCheckOutResponse.setDomainDetail(DomainUtil
                    .retrieveDomainDetailInfo("stdbenefit", parentKey));
            standardBenefitCheckOutResponse
                    .setStandardBenefitBO(standardBenefitBO);
            InformationalMessage informationalMessage = new InformationalMessage(
            	"benefit.checked.out.success.info");
            informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
            standardBenefitCheckOutResponse
                    .addMessage(informationalMessage);
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(standardBenefitCheckOutRequest);
            String logMessage = "Failed while processing StandardBenefitCheckOutRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): CheckOut Standard Benefit");
        return standardBenefitCheckOutResponse;
    }


    /**
     * @param StandardBenefitCheckOutRequest
     * @return StandardBenefitBO
     */
    public StandardBenefitBO getStandardBenefitCheckOutObject(
            StandardBenefitCheckOutRequest request) {

        StandardBenefitVO standardBenefitVO = (StandardBenefitVO) request
                .getStandardBenefitVO();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                .getBenefitIdentifier());
        standardBenefitBO.setParentSystemId(standardBenefitVO
                .getStandardBenefitParentKey());
        standardBenefitBO.setStatus(standardBenefitVO.getStatus());
        standardBenefitBO.setBusinessDomains(standardBenefitVO
                .getBusinessDomains());
        standardBenefitBO.setVersion(standardBenefitVO.getVersion());
        return standardBenefitBO;
    }


    /**
     * @param StandardBenefitLocateCriteria
     * @return StandardBenefitBO
     */
    private StandardBenefitLocateCriteria getSearchCriteria(
            StandardBenefitSearchVO standardBenefitSearchVO) {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering getSearchCriteria(): StandardBenefitSearchVO");
        StandardBenefitLocateCriteria standardBenefitLocateCriteria = new StandardBenefitLocateCriteria();
        standardBenefitLocateCriteria
                .setBusinessEntityCodeList(standardBenefitSearchVO
                        .getBusinessEntityCodeList());
        standardBenefitLocateCriteria
                .setBusinessGroupCodeList(standardBenefitSearchVO
                        .getBusinessGroupCodeList());
        standardBenefitLocateCriteria
				.setMarketBusinessUnitList(standardBenefitSearchVO
						.getMarketBusinessUnitList());        
        standardBenefitLocateCriteria.setLobCodeList(standardBenefitSearchVO
                .getLobCodeList());
        standardBenefitLocateCriteria
                .setName(standardBenefitSearchVO.getName());
        standardBenefitLocateCriteria
                .setProviderArrangementCodeList(standardBenefitSearchVO
                        .getProviderArrangementCodeList());
        standardBenefitLocateCriteria
                .setDataTypeCodeList(standardBenefitSearchVO
                        .getDataTypeCodeList());
        standardBenefitLocateCriteria
                .setQualifierCodeList(standardBenefitSearchVO
                        .getQualifierCodeList());
        standardBenefitLocateCriteria.setTermCodeList(standardBenefitSearchVO
                .getTermCodeList());
        standardBenefitLocateCriteria
                .setMaximumResultSize(standardBenefitSearchVO
                        .getMaxSearchResultSize()); // new
        Logger
                .logInfo("StandardBenefitBusinessService - Returning getSearchCriteria(): StandardBenefitSearchVO");
        return standardBenefitLocateCriteria;
    }


    /**
     * @param benefitLevels
     * @throws WPDException
     * @throws ServiceException
     */
    private boolean removeBenefitLevelsWithoutLines(List benefitLevels,
            StandardBenefitBO standardBenefitBO, User user)
            throws ServiceException {
        //Changes For Performance Fix Start
        boolean isLevelDeleted = false;
        //Changes For Performance Fix End
        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            if (null != benefitLevels) {
                for (int i = 0; i < benefitLevels.size(); i++) {
                    BenefitLevelVO benefitLevelVO = (BenefitLevelVO) benefitLevels
                            .get(i);
                    
                    
                    if (null == benefitLevelVO.getBenefitLines()
                            || benefitLevelVO.getBenefitLines().isEmpty()) {
                        BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
                        benefitLevelBO.setBenefitLevelId(benefitLevelVO
                                .getBenefitLevelId());
                        //changed on 27th Dec
                        benefitLevelBO.setBenefitDefinitionId(benefitLevelVO.getBenefitDefinitionId());
                        //end
                        bom.delete(benefitLevelBO, standardBenefitBO, user);
                        //Changes For Performance Fix Start
                        isLevelDeleted = true;
                        // Changes For Performance Fix End
                    }
                }
            }
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            String logMessage = BusinessConstants.MSG_LOG;
            throw new ServiceException(logMessage, logParameters, ex);
        }
        //Changes For Performance Fix Start
        return isLevelDeleted;
        // Changes For Performance Fix End
    }


    /**
     * This method get values from StandardBenefitNoteAttachmentRequest and is
     * set in StandardBenefitBO so as to attache notes to a benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param StandardBenefitNoteAttachmentRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            StandardBenefitNoteAttachmentRequest standardBenefitNoteAttachmentRequest)
            throws ServiceException {
        try {

            Logger.logInfo("Inside Benefit Component Attach Notes Service");
            StandardBenefitNoteAttachmentResponse standardBenefitNoteAttachmentResponse = null;
            // Get the notes dtails from the request to the standardbenefit
            StandardBenefitVO standardBenefitVO = (StandardBenefitVO) standardBenefitNoteAttachmentRequest
                    .getStandardBenefitVO();
            // Create new object for BenefitBO
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setStatus(standardBenefitVO.getStatus());
            standardBenefitBO.setVersion(standardBenefitVO.getVersion());
            standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                    .getBenefitIdentifier());
            standardBenefitBO.setBusinessDomains(standardBenefitVO
                    .getBusinessDomains());
            standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                    .getStandardBenefitKey());
            List attachedNotesList = getBenefitNotesObject(standardBenefitVO
                    .getAttachedNotes(), standardBenefitVO
                    .getStandardBenefitKey(), standardBenefitVO
                    .getVersionList());

            BusinessObjectManager bom = getBOM();
            // create a response object
            standardBenefitNoteAttachmentResponse = (StandardBenefitNoteAttachmentResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.ATTACH_STANDARD_BENEFIT_NOTES_RESPONSE);

            bom.persist(attachedNotesList, standardBenefitBO,
                    standardBenefitNoteAttachmentRequest.getUser(), true);
            standardBenefitNoteAttachmentResponse
                    .addMessage(new InformationalMessage(
                            BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_SAVE_SUCCESS));
            Logger
                    .logInfo(" Standard Benefit Notes Attachment Saved Successfully");
            return standardBenefitNoteAttachmentResponse;
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(standardBenefitNoteAttachmentRequest);
            String logMessage = "Failed while processing StandardBenefitAttachNotesRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * @param list
     * @throws WPDException
     * @throws ServiceException
     */
    private List getBenefitNotesObject(List idList, int entityId,
            List versionList) {

        List attachedNotesList = new ArrayList();

        for (int i = 0; i < idList.size(); i++) {
            // Create a new object for BO
            AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
            attachedNotesBO.setNoteId(String.valueOf(idList.get(i)));
            attachedNotesBO.setEntityId(entityId);
            attachedNotesBO.setEntityType("ATTACHBENEFIT");
            attachedNotesBO.setVersion(Integer.parseInt((String) versionList
                    .get(i)));
            attachedNotesList.add(attachedNotesBO);
        }

        return attachedNotesList;
    }


    /**
     * This method get values from LocateStandardBenefitNotesRequest and is set
     * in StandardBenefitNotesLocateCriteria so as to locate the attached notes
     * in a benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param LocateStandardBenefitNotesRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(LocateStandardBenefitNotesRequest request)
            throws WPDException {
        LocateStandardBenefitNotesResponse locateStandardBenefitNotesResponse = null;
        BusinessObjectManager bom = getBOM();
        locateStandardBenefitNotesResponse = (LocateStandardBenefitNotesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_STANDARD_BENEFIT_NOTES_RESPONSE);
        StandardBenefitNotesLocateCriteria notesLocateCriteria = new StandardBenefitNotesLocateCriteria();
        notesLocateCriteria.setEntityId(request.getEntityId());
        notesLocateCriteria.setEntityType(request.getEntityType());
        notesLocateCriteria.setMaximumResultSize(request.getMaxResultSize());
        //changed 28th Nov
        notesLocateCriteria.setBenefitDefinitionKey(request.getBenefitDefinitionKey());
        //change ends
        LocateResults locateResults = bom.locate(notesLocateCriteria, request
                .getUser());
        locateStandardBenefitNotesResponse.setBenefitNotesList(locateResults
                .getLocateResults());
        return locateStandardBenefitNotesResponse;
    }


    /**
     * This method get values from DeleteStandardBenefitNotesRequest and is set
     * in AttachedNotesBO so as to delete the attached notes in a benefit
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param DeleteStandardBenefitNotesRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            DeleteStandardBenefitNotesRequest request)
            throws WPDException {
        DeleteStandardBenefitNotesResponse deleteStandardBenefitNotesResponse = null;
        AttachedNotesBO attachedNotesBO = new AttachedNotesBO();

//        attachedNotesBO.setNoteId(deleteStandardBenefitNotesRequest
//                .getStandardBenefitNoteId());
        
        attachedNotesBO.setAttachNotes(request.getNotedIds());
        attachedNotesBO.setEntityId(request.getEntityId());
        // **Change**July16th
        attachedNotesBO.setEntityType(request.getEntityType());
        // **End**July16th
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
        standardBenefitBO
                .setBenefitIdentifier(request
                        .getStandardBenefitName());
        standardBenefitBO
                .setStandardBenefitKey(request
                        .getEntityId());
        standardBenefitBO.setBusinessDomains(request
                .getBusinessDomains());
        standardBenefitBO.setVersion(request
                .getVersion());
        standardBenefitBO.setStatus(request
                .getStatus());
        // changed 28thNov
        attachedNotesBO.setBenefitDefinitionKey(request.getBenefitDefinitionKey());
        // change ends
        BusinessObjectManager bom = getBOM();

        deleteStandardBenefitNotesResponse = (DeleteStandardBenefitNotesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_STANDARD_BENEFIT_NOTES_RESPONSE);

        bom.delete(attachedNotesBO, standardBenefitBO,
                request.getUser());
        deleteStandardBenefitNotesResponse.addMessage(new InformationalMessage(
                BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_DELETE_SUCCESS));
        return deleteStandardBenefitNotesResponse;
    }


    /**
     * This method is used to get the notes list for the corresponding benefit
     * line from the table
     * 
     * @param request
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(NotesAttachmentRequestForBnftLine request)
            throws ServiceException {
        try {
            Logger
                    .logInfo("Inside Notes Attachment Search Service for Benefit Line");
            // create the response reference
            NotesAttachmentResponseForBnftLine response = null;
            // get the the business object manager object
            BusinessObjectManager bom = getBOM();
            // create the locate criteria object
            NotesAttachmentForBenefitLineLocateCriteria locateCriteria = new NotesAttachmentForBenefitLineLocateCriteria();
            // set the benefitId to the locateCriteria from the request
            locateCriteria.setBenefitLineId(request.getBenefitLineID());
            locateCriteria.setBenefitQualifierCode(request
                    .getBenefitQualifier());
            locateCriteria.setBenefitTermCode(request.getBenefitTerms());
            locateCriteria.setNoteFilterString(request.getNoteFilterString());
            locateCriteria.setBenefitDefinitionId(request.getBenefitDefinitionId());
            // create the response object
            response = (NotesAttachmentResponseForBnftLine) WPDResponseFactory
                    .getResponse(WPDResponseFactory.LOCATE_NOTES_FOR_BENEFIT_LINE);
            // call the locate method in the business object manager
            LocateResults locateResults = bom.locate(locateCriteria, request
                    .getUser());
            // set the benefitDefinitionslist in the response
            response.setSelectedNotesList(locateResults.getLocateResults());
            // return the response
            return response;
        } catch (WPDException ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateComponentsBenefitDefinitionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * This method is to insert the selected notes for the benefit line.
     * 
     * @param benefitLineNotesAttachmentRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            BenefitLineNotesAttachmentRequest benefitLineNotesAttachmentRequest)
            throws ServiceException {
        try {

            Logger.logInfo("Inside Benefit Line Attach Notes Service");
            BenefitLineNotesAttachmentResponse response = null;
            // Get the notes dtails from the request to the standardbenefit
            StandardBenefitVO standardBenefitVO = (StandardBenefitVO) benefitLineNotesAttachmentRequest
                    .getStandardBenefitVO();
            // Create new object for BenefitBO
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
            standardBenefitBO.setStatus(standardBenefitVO.getStatus());
            standardBenefitBO.setVersion(standardBenefitVO.getVersion());
            standardBenefitBO.setBenefitIdentifier(standardBenefitVO
                    .getBenefitIdentifier());
            standardBenefitBO.setBusinessDomains(standardBenefitVO
                    .getBusinessDomains());
            standardBenefitBO.setStandardBenefitKey(standardBenefitVO
                    .getStandardBenefitKey());
            List attachedNotesList = getBenefitLineNotesObject(benefitLineNotesAttachmentRequest);

            BusinessObjectManager bom = getBOM();
            // create a response object
            response = (BenefitLineNotesAttachmentResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.ATTACH_BENEFIT_LINES_NOTES_RESPONSE);

            // create the subobject
            AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
            attachedNotesBO.setAttachNotes(attachedNotesList);

            bom.persist(attachedNotesBO, standardBenefitBO,
                    benefitLineNotesAttachmentRequest.getUser(), true);
            response.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_SAVE_SUCCESS));
            Logger.logInfo(" Benefit Line Notes Attachment Saved Successfully");
            return response;
        } catch (Exception ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitLineNotesAttachmentRequest);
            String logMessage = "Failed while processing BenefitLineNotesAttachmentRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * 
     * @param idList
     * @param entityId
     * @param versionList
     * @return
     */
    private List getBenefitLineNotesObject(
            BenefitLineNotesAttachmentRequest request) {

        List attachedNotesList = new ArrayList();
        List idList = request.getStandardBenefitVO().getAttachedNotes();
        List versionList = request.getStandardBenefitVO().getVersionList();
        String bnftLineId = request.getBenefitLineId();
        if (null != idList && !idList.isEmpty()) {
            for (int i = 0; i < idList.size(); i++) {
                // Create a new object for BO
                AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
                attachedNotesBO.setNoteId(String.valueOf(idList.get(i)));
                if (null != bnftLineId && !bnftLineId.equals("")) {
                    attachedNotesBO.setEntityId(Integer.parseInt(bnftLineId));
                }
                attachedNotesBO.setEntityType("ATTACHBNFTLINE");
                attachedNotesBO.setVersion(Integer
                        .parseInt((String) versionList.get(i)));
                // changed 28thNov
                attachedNotesBO.setBenefitDefinitionKey(request.getStandardBenefitVO().getBenefitDefinitionKey());
                //change ends
                attachedNotesList.add(attachedNotesBO);
            }
        }

        return attachedNotesList;
    }


    /**
     * This method get values from ApproveStandardBenefitRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to
     * approve
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param ApproveStandardBenefitRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            ApproveStandardBenefitRequest approveStandardBenefitRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Test Pass Standard Benefit");
        ApproveStandardBenefitResponse response = null;
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        // Setting the values from the request to the StandardBenefitBO
        standardBenefitBO.setStandardBenefitKey(approveStandardBenefitRequest
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(approveStandardBenefitRequest
                .getStandardBenefitIdentifier());
        standardBenefitBO.setVersion(approveStandardBenefitRequest
                .getStandardBenefitVersion());
        standardBenefitBO.setParentSystemId(approveStandardBenefitRequest
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(approveStandardBenefitRequest
                .getBusinessDomains());
        standardBenefitBO.setStatus(approveStandardBenefitRequest
                .getStandardBenefitStatus());
        try {
            //Create Response object
            response = (ApproveStandardBenefitResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.APPROVE_STD_BEN_RESPONSE);
            //call the bom s scheduleForTest method
            bom.approve(standardBenefitBO, approveStandardBenefitRequest
                    .getUser());
            bom.publish(standardBenefitBO, approveStandardBenefitRequest
                    .getUser());
            //Set the message for success
            InformationalMessage informationalMessage = new InformationalMessage(
                    WebConstants.APPROVE_BEN);
            informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
            response.addMessage(informationalMessage);

            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) approveStandardBenefitRequest
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM().locate(
                        standardBenefitViewAllLocateCriteria,
                        approveStandardBenefitRequest.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        approveStandardBenefitRequest.getUser());

            List voList = new ArrayList();
            if(null != locateResults.getLocateResults() && locateResults.getLocateResults().size() > 0){
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                DomainDetail domainDetail=DomainUtil
                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
                            .getParentSystemId());
	                standardBenefitVO.setDomainDetail(domainDetail);
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
		            if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            if (locateResults.getLocateResults().size() >= 50) {
	                response.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
	            response.setSearchResultList(locateResults.getLocateResults());
            }
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(approveStandardBenefitRequest);
            String logMessage = "Failed while processing TestPassStandardBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Test Pass Standard Benefit");
        return response;
    }


    /**
     * This method get values from RejectStandardBenefitRequest and is set in
     * StandardBenefitBO so as to change the life cycle of the benefit to reject
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param RejectStandardBenefitRequest
     * @return
     * @throws WPDException
     */
    public WPDResponse execute(
            RejectStandardBenefitRequest rejectStandardBenefitRequest)
            throws ServiceException {
        Logger
                .logInfo("StandardBenefitBusinessService - Entering execute(): Test Pass Standard Benefit");
        RejectStandardBenefitResponse response = null;
        BusinessObjectManager bom = getBOM();
        StandardBenefitBO standardBenefitBO = new StandardBenefitBO();

        // Setting the values from the request to the StandardBenefitBO
        standardBenefitBO.setStandardBenefitKey(rejectStandardBenefitRequest
                .getStandardBenefitKey());
        standardBenefitBO.setBenefitIdentifier(rejectStandardBenefitRequest
                .getStandardBenefitIdentifier());
        standardBenefitBO.setVersion(rejectStandardBenefitRequest
                .getStandardBenefitVersion());
        standardBenefitBO.setParentSystemId(rejectStandardBenefitRequest
                .getStandardBenefitParentKey());
        standardBenefitBO.setBusinessDomains(rejectStandardBenefitRequest
                .getBusinessDomains());
        standardBenefitBO.setStatus(rejectStandardBenefitRequest
                .getStandardBenefitStatus());
        try {
            //Create Response object
            response = (RejectStandardBenefitResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.REJECT_STD_BEN_RESPONSE);
            //call the bom s scheduleForTest method
            bom.reject(standardBenefitBO, rejectStandardBenefitRequest
                    .getUser());
            //Set the message for success
            InformationalMessage informationalMessage = new InformationalMessage(
                    WebConstants.REJECT_BEN);
            informationalMessage.setParameters(new String[]{standardBenefitBO.getBenefitIdentifier()});
            response.addMessage(informationalMessage);

            StandardBenefitSearchVO standardBenefitSearchVO = (StandardBenefitSearchVO) rejectStandardBenefitRequest
                    .getStandardBenefitSearchVO();
            StandardBenefitLocateCriteria standardBenefitLocateCriteria = getSearchCriteria(standardBenefitSearchVO);
            
            LocateResults locateResults = null;
            //Searching using a benefitkey is applicable only for view all
            // version functionality.
            if (standardBenefitSearchVO.getBenefitKey() > 0) {
                StandardBenefitViewAllLocateCriteria standardBenefitViewAllLocateCriteria = new StandardBenefitViewAllLocateCriteria();
                locateResults = getBOM().locate(
                        standardBenefitViewAllLocateCriteria,
                        rejectStandardBenefitRequest.getUser());
            } else
                locateResults = getBOM().locate(standardBenefitLocateCriteria,
                        rejectStandardBenefitRequest.getUser());

            List voList = new ArrayList();
            if(null != locateResults.getLocateResults() && locateResults.getLocateResults().size() > 0){
	            Iterator iterator = locateResults.getLocateResults().iterator();
	            while (iterator.hasNext()) {
	                StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
	                standardBenefitBO = (StandardBenefitBO) iterator.next();
	                standardBenefitVO.setBenefitIdentifier(standardBenefitBO
	                        .getBenefitIdentifier());
	                DomainDetail domainDetail=DomainUtil
                    .retrieveDomainDetailInfo("stdbenefit", standardBenefitBO
                            .getParentSystemId());
	                standardBenefitVO.setDomainDetail(domainDetail);
	                standardBenefitVO.setVersion(standardBenefitBO.getVersion());
		            if(null!=standardBenefitBO.getDescription()&& (standardBenefitBO.getDescription().length()>25)){
		            	standardBenefitVO.setDescription((standardBenefitBO
		                        .getDescription().substring(0,25)).concat("...."));
		            }else
		            	standardBenefitVO.setDescription(standardBenefitBO
		                    .getDescription());
	                standardBenefitVO.setStatus(standardBenefitBO.getStatus());
	                standardBenefitVO.setState(standardBenefitBO.getState());
	                standardBenefitVO.setStandardBenefitKey(standardBenefitBO
	                        .getStandardBenefitKey());
	                standardBenefitVO.setStandardBenefitParentKey(standardBenefitBO
	                        .getParentSystemId());
	                standardBenefitVO.setBusinessDomains(standardBenefitBO
	                        .getBusinessDomains());
	                voList.add(standardBenefitVO);
	            }
	            locateResults.setLocateResults(voList);
	            if (locateResults.getLocateResults().size() >= 50) {
	                response.addMessage(new InformationalMessage(
	                        BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
	            }
            response.setSearchResultList(locateResults.getLocateResults());
            }
            // search end
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(rejectStandardBenefitRequest);
            String logMessage = "Failed while processing TestPassStandardBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("StandardBenefitBusinessService - Returning execute(): Test Pass Standard Benefit");
        return response;
    }
    /*
     * Validation methode for referance date
     * 
     * @StandardBenefitCheckInRequest
     * 
     * return RefDataDomainValidationRequest
     */
    private RefDataDomainValidationRequest refDataBenefitValidation(StandardBenefitCheckInRequest request) throws ServiceException
    {
    	String benfitType=request.getStandardBenefitVO().getBenefitType();
        RefDataDomainValidationRequest refDataDomainValidationRequest=new RefDataDomainValidationRequest();
        List termList = new ArrayList();
        List qualifierList = new ArrayList();
        List referenceList = new ArrayList();
        List fundArangementList = new ArrayList();
        List providerArrangementList = new ArrayList();
        List locateResults = new ArrayList();
        HashMap selectedItemMap = new HashMap();
        List parentCatalogList = new ArrayList();
    	SubCatalogVO subCatalogVO = new SubCatalogVO();
    	BenefitMandateBO benefitMandateBO = new BenefitMandateBO();
    	subCatalogVO.setEntityId(request.getStandardBenefitVO().getStandardBenefitParentKey());
    	subCatalogVO.setEntityType("stdbenefit");
    	parentCatalogList.add("term");
	    parentCatalogList.add("qualifier");
	    parentCatalogList.add("provider arrangement");
	    if(!"STANDARD".equals(benfitType))
	    {
		    parentCatalogList.add("State Code");
		    parentCatalogList.add("Funding Arrangement");
	    }
	    parentCatalogList.add("Reference");
	    refDataDomainValidationRequest.setParentCatalogList(parentCatalogList);
    	refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);
    	StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
    	BenefitLevelBO benefitLevelBO=new BenefitLevelBO();
    	BenefitLevelPopUpBO benefitLevelPopUpBO = new BenefitLevelPopUpBO();
    	termList = getTermList(request.getStandardBenefitVO().getStandardBenefitKey(),"term");
    	qualifierList = getTermList(request.getStandardBenefitVO().getStandardBenefitKey(),"qualifier");
       	providerArrangementList = getTermList(request.getStandardBenefitVO().getStandardBenefitKey(),"provider arrangement");
       	benefitLevelBO.setBenefitSysId(request.getStandardBenefitVO().getStandardBenefitKey());
        try {
    	     locateResults=builder.locateStateFundingArrangement(benefitLevelBO);
    	     if(!"STANDARD".equals(benfitType)){
	    	     if(null!= locateResults ){
	    	     	for(int i=0;i<locateResults.size();i++){
	    	     		benefitMandateBO = (BenefitMandateBO)locateResults.get(i);
	    	     		fundArangementList.add(benefitMandateBO.getFundingArrangement());
	    	     	}
   	         }}
    	 } catch (SevereException e) {
             List logParameters = new ArrayList(1);
             logParameters.add(request);
             String logMessage = "Failed while processing RefDataRetrieveRequest";
             throw new ServiceException(logMessage, logParameters, e);
         }
    	try{
    		locateResults=builder.locateReferance(benefitLevelBO);
    		 if(null!= locateResults ){
    		 	for(int i=0;i<locateResults.size();i++){
    		 		benefitLevelBO = (BenefitLevelBO)locateResults.get(i);
    		 		referenceList = benefitLevelBO.getReferenceList();
    			
    		}}
    	}catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RefDataRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
    	selectedItemMap.put("term",termList);
    	selectedItemMap.put("qualifier",qualifierList);
    	selectedItemMap.put("provider arrangement",providerArrangementList);
    	if(!"STANDARD".equals(benfitType)){
	    	selectedItemMap.put("State Code",benefitMandateBO.getBenefitStateCodeList());
	    	selectedItemMap.put("Funding Arrangement",fundArangementList);
    	}
    	selectedItemMap.put("Reference",referenceList);
    	refDataDomainValidationRequest.setSelectedItemMap(selectedItemMap);
    	return refDataDomainValidationRequest;
    }
    /*
     * Methode for getting 
     * term,qualifier,provider arrangement
     * @benefitKey,type
     * return List of term/qualifier/provider arrangement
     */
    public List getTermList(int benefitKey,String type) throws ServiceException
    {
    	List termList = new ArrayList();
    	List entityList = new ArrayList();
    	StandardBenefitBusinessObjectBuilder builder = new StandardBenefitBusinessObjectBuilder();
    	BenefitLevelPopUpBO benefitLevelPopUpBO = new BenefitLevelPopUpBO();
    	try{
	    	benefitLevelPopUpBO.setStandardBenefitKey(benefitKey);
	    	benefitLevelPopUpBO.setSelectedType(type);
	    	termList=builder.locateRefData(benefitLevelPopUpBO);
	    	if(null != termList && termList.size() > 0){
		    	for(int i=0;i<termList.size();i++)
		    	{
		    		benefitLevelPopUpBO=(BenefitLevelPopUpBO)termList.get(i);
		    		entityList.add(benefitLevelPopUpBO.getCode());
		    	}
	    	}
    	}catch(WPDException e){
    		 List logParameters = new ArrayList(1);
             String logMessage = "Failed while processing RefDataRetrieveRequest";
        throw new ServiceException(logMessage, logParameters, e);
    
    	}
    	return entityList; 
    }
    
    /**
     * This method will perform the persist,update and delete for question note in  -benefit level adminoption 
     * 
     * @param BenefitQuestionNoteProcessRequest
     * @return BenefitQuestionNoteResponse
     * @throws ServiceException
     * 
     */
    public WPDResponse execute(BenefitQuestionNoteProcessRequest request)
    throws ServiceException {
    	Logger
		.logInfo("StandardBenefitBusinessService - Entering execute(BenefitQuestionNoteProcessRequest request)");
    	BenefitQuestionNoteResponse response = null;
    	
    	BusinessObjectManager bom = getBOM();
    	
    	List messageList = new ArrayList(2);
    	
    	StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
    	standardBenefitBO.setStandardBenefitKey(request.getBenefitId());
    	standardBenefitBO.setBenefitIdentifier(request
    			.getMainObjectIdentifier());
    	standardBenefitBO.setVersion(request.getVersionNumber());
    	standardBenefitBO.setStatus(request.getStatus());
    	standardBenefitBO.setBusinessDomains(request.getDomainList());
    	standardBenefitBO.setParentSystemId(request.getParentSystemId());
    	
    	NotesToQuestionAttachmentBenefitBO attachmentBO = new NotesToQuestionAttachmentBenefitBO();
    	attachmentBO.setPrimaryId(request.getPrimaryEntityId());
    	attachmentBO.setPrimaryEntityType(request.getPrimaryType());
    	attachmentBO.setSecondaryId(request.getSecondaryEntityId());
    	attachmentBO.setSecondaryEntityType(BusinessConstants.ATTACH_QUESTION);
    	attachmentBO.setQuestionId(request.getQuestionId());
    	attachmentBO.setNoteId(request.getNoteId());
    	attachmentBO.setNoteVersionNumber(request.getNoteVersion());
    	attachmentBO.setNoteOverrideStatus(BusinessConstants.NOTE_FLAG_F);
    	attachmentBO.setRequestType(request.getNotesAction());
    	attachmentBO.setQuestionId(request.getQuestionId());
    	try {
    		if(request.getNotesAction()==1){
    			bom.persist(attachmentBO, standardBenefitBO, request
    					.getUser(), true);
    			messageList.add(new InformationalMessage(
    					BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS));
    		}
    		if(request.getNotesAction()==2){
    			bom.persist(attachmentBO, standardBenefitBO, request
    					.getUser(), false);
    			messageList.add(new InformationalMessage(
    					BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS));
    		}
    		if(request.getNotesAction()==3){
    			bom.delete(attachmentBO, standardBenefitBO, request
    					.getUser());
    			messageList.add(new InformationalMessage(
    					BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS));
    		}
    		
    	} catch (SevereException ex) {
    		Logger.logError(ex);
    		List logParameters = new ArrayList(1);
    		logParameters.add(request);
    		String logMessage = "Failed while processing BenefitQuestionNoteProcessRequest";
    		throw new ServiceException(logMessage, logParameters, ex);
    	} catch (WPDException ex) {
    		Logger.logError(ex);
    		List logParameters = new ArrayList(1);
    		logParameters.add(request);
    		String logMessage = "Failed while processing BenefitQuestionNoteProcessRequest";
    		throw new ServiceException(logMessage, logParameters, ex);
    	}
    	response = (BenefitQuestionNoteResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.BENEFIT_QUESTION_NOTES__RESPONSE);
    	response.setMessages(messageList);
    	Logger
		.logInfo("StandardBenefitBusinessService - Exiting execute(BenefitQuestionNoteProcessRequest request)");
    	return response;
    }
    
    
    /**
     * The method retrieves the Tier Definitions for Benefit and Product popups
     * for Tier Definitions
     * @param tierLookUpRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(TierLookUpRequest tierLookUpRequest)
    throws ServiceException {
    	Logger
		.logInfo("StandardBenefitBusinessService - Entering execute(TierLookUpRequest ");
    	TierLookUpResponse tierLookUpResponse = null;
    	LocateResults locateResults = null;
    	TierDefinitionLocateCriteria tierDefinitionLocateCriteria = 
    	    new TierDefinitionLocateCriteria();
    	//action is whether benefit or product
    	tierDefinitionLocateCriteria.setAction(tierLookUpRequest.getAction());
    	tierDefinitionLocateCriteria.setBenefitDefinitionId(tierLookUpRequest.getBenefitDefinitionId());
    	BenefitTierDefinitionBO benefitTierDefinitionBO = null;
    	ProductTierDefnOverrideBO productTierDefnOverrideBO = null;
    	List benefitTierDefinitionVOList = null;
    	StandardBenefitBusinessObjectBuilder stdBenefitBusinessObjectBuilder = 
    	    new StandardBenefitBusinessObjectBuilder();
    	try{
    	tierLookUpResponse = (TierLookUpResponse) WPDResponseFactory
    		.getResponse(WPDResponseFactory.TIER_LOOKUP_RESPONSE);   
    	
    	locateResults = stdBenefitBusinessObjectBuilder.locate(tierDefinitionLocateCriteria,
    	        tierLookUpRequest.getUser());    	
    	}
    	catch (WPDException ex) {
    		Logger.logError(ex);
    		List logParameters = new ArrayList(1);
    		logParameters.add(tierLookUpRequest);
    		String logMessage = "Failed while processing TierLookUpRequest";
    		throw new ServiceException(logMessage, logParameters, ex);
    	}    
    	if(null != locateResults.getLocateResults() && locateResults
    	        .getLocateResults().size() > 0){
    	        benefitTierDefinitionVOList = new ArrayList();
	            Iterator iterator = locateResults.getLocateResults()
	            .iterator();
	            while (iterator.hasNext()) { 
	               BenefitTierDefinitionVO benefitTierDefinitionVO 
	               = new BenefitTierDefinitionVO();	               
	               Object obj = iterator.next();
	               if(obj instanceof BenefitTierDefinitionBO){
	                   benefitTierDefinitionBO = (BenefitTierDefinitionBO)obj;
		               benefitTierDefinitionVO.setTierDescription(benefitTierDefinitionBO
		                        .getTierDescription());
		               benefitTierDefinitionVO.setTierCode(benefitTierDefinitionBO
		                        .getTierCode());
		               benefitTierDefinitionVO.setTierDefnSysId(benefitTierDefinitionBO
		                        .getTierDefinitionId());
		               benefitTierDefinitionVO.setDataType(benefitTierDefinitionBO
		                        .getDataType());     
		               benefitTierDefinitionVOList.add(benefitTierDefinitionVO);	                   
	              }
	               else if(obj instanceof ProductTierDefnOverrideBO){ 
	                   productTierDefnOverrideBO = (ProductTierDefnOverrideBO)obj;     	                   
		               benefitTierDefinitionVO.setTierDescription(productTierDefnOverrideBO
		                        .getTierDescription());		              
		               benefitTierDefinitionVO.setTierDefnSysId(productTierDefnOverrideBO
		                        .getTierDefinitionId());		                   
		               benefitTierDefinitionVOList.add(benefitTierDefinitionVO);	
	               }	              
	            }
    	}   
    	tierLookUpResponse.setTierList(benefitTierDefinitionVOList);    	
    	Logger
		.logInfo("StandardBenefitBusinessService - Exiting execute(TierLookUpRequest request)");
    	return tierLookUpResponse;
    }
    
       
    /**
     * The method sets the Tier Definitions for a particular Benefit Definition
     * @param createBenefitTierDefinitionRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(CreateBenefitTierDefinitionRequest 
            createBenefitTierDefinitionRequest) throws ServiceException {
        Logger
        .logInfo("StandardBenefitBusinessService - Entering execute(): " +
        		"CreateBenefitTierDefinitionRequest");       
        CreateBenefitTierDefinitionResponse createBenefitTierDefinitionResponse 
        = (CreateBenefitTierDefinitionResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.BENEFIT_TIER_DEFN_ASSN_RESPONSE);       
        BenefitTierDefinitionVO benefitTierDefinitionVO = 
            createBenefitTierDefinitionRequest.getBenefitTierDefinitionVO();        
        if (null != createBenefitTierDefinitionResponse){  
            StandardBenefitBO standardBenefitBO = new StandardBenefitBO(); 
            standardBenefitBO.setBenefitIdentifier(benefitTierDefinitionVO.getBenefitName());    
            standardBenefitBO.setBusinessDomains(benefitTierDefinitionVO.getBusinessDomains());
            standardBenefitBO.setVersion(benefitTierDefinitionVO.getBenefitVersion());
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
            .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();
            List bnftTierDefinitionAssnBOList =  new ArrayList(); 
            BenefitTierDefinitionAssnBO mainBnftTierDefinitionAssnBO =  
                new BenefitTierDefinitionAssnBO(); 
            mainBnftTierDefinitionAssnBO.setBenefitDefinitionId
            		(benefitTierDefinitionVO.getBenefitDefinitionId());
            try {                   
	            List tierDefintionsForBenefit = benefitTierDefinitionVO.
	            								getTierDefinitionsList();
	            if(null != tierDefintionsForBenefit){
		            for(int i=0;i<tierDefintionsForBenefit.size();i++){            
		                BenefitTierDefinitionAssnBO benefitTierDefinitionAssnBO =  
		                    new BenefitTierDefinitionAssnBO();         
		                benefitTierDefinitionAssnBO.setTierDefinitionId((
		                        (Integer)tierDefintionsForBenefit.get(i)).intValue());
		                benefitTierDefinitionAssnBO.setBenefitDefinitionId(
		                        benefitTierDefinitionVO.getBenefitDefinitionId());
		                bnftTierDefinitionAssnBOList.add(benefitTierDefinitionAssnBO);
		            } 
	            }
	            mainBnftTierDefinitionAssnBO.setBenefitTierDefinitionsList
	            (bnftTierDefinitionAssnBOList);
	            if (benefitTierDefinitionVO.getBenefitDefinitionId() != -1) {
	                bom.persist(mainBnftTierDefinitionAssnBO, standardBenefitBO, 
		                    createBenefitTierDefinitionRequest
	                        .getUser(), false);  //update the TierDefns in the BNFT_TIER_DEFN_ASSN
	            }else{
	                bom.persist(mainBnftTierDefinitionAssnBO, standardBenefitBO, 
		                    createBenefitTierDefinitionRequest
	                        .getUser(), true); //insert new TierDefns BNFT_TIER_DEFN_ASSN
	            }	            
            }
            catch (WPDException ex) {
		        List logParameters = new ArrayList(1);
		        logParameters.add(createBenefitTierDefinitionRequest);
		        String logMessage = "Failed while processing " +
		        		"CreateBenefitTierDefinitionRequest";
		        throw new ServiceException(logMessage, logParameters, ex);
            } 
        }
	    Logger
	    .logInfo("StandardBenefitBusinessService - Returning execute()" +
	    		"BenefitTierDefinition Create");
	    return createBenefitTierDefinitionResponse;
     }
    
    
    
    /**
     * The method retrieves the Tier Definitions associated with a particular
     * BenefitDefinition
     * @param bnftTierDefinitionRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(GetBenefitTierDefinitionRequest bnftTierDefinitionRequest)
    throws ServiceException {
        Logger
        .logInfo("StandardBenefitBusinessService - Entering execute(): " +
        		"GetBenefitTierDefinitionRequest");       
        GetBenefitTierDefinitionResponse bnftTierDefinitionResponse 
        = (GetBenefitTierDefinitionResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.BENEFIT_TIER_DEFN_RESPONSE); 
        if (null != bnftTierDefinitionResponse){  
            
            // for retrieving tier definitions associated with each benefit definition
            StandardBenefitBusinessObjectBuilder stdBnftBusinessObjectBuilder 
            				= new StandardBenefitBusinessObjectBuilder();
            BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria 
    						= new BenefitTierDefinitionLocateCriteria();
            bnftTierDefinitionLocateCriteria.setBenefitId(bnftTierDefinitionRequest
                    		.getBenefitId()); 
            bnftTierDefinitionLocateCriteria.setBenefitDefinitionId(bnftTierDefinitionRequest
            		.getBenefitDefinitionId());
            try{
	            LocateResults bnftTierDefnlocateResults = stdBnftBusinessObjectBuilder
	            				.locate(bnftTierDefinitionLocateCriteria,
	            				 bnftTierDefinitionRequest.getUser());		            
	            List bnftTierDefnList = bnftTierDefnlocateResults.getLocateResults();      
	            
	            if(null!=bnftTierDefnList && bnftTierDefnList.size()>0){		               
	    	        Iterator bnftTierDefnIterator = bnftTierDefnList.iterator();
	    	        StringBuffer tierDescriptionAndIdBuffer = new StringBuffer();
	    	        while(bnftTierDefnIterator.hasNext()){		    	          
	    	            BenefitDefinitionBO benefitTierDefinitionBO = 
	    	                (BenefitDefinitionBO)bnftTierDefnIterator.next();
	    	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDescription());
	    	            tierDescriptionAndIdBuffer.append("~");
	    	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDefinitionId());
	    	            if(bnftTierDefnIterator.hasNext()){	               
	    	                tierDescriptionAndIdBuffer.append("~");
	    	            }
	    	        }	     	    
	    	        //The String will be similar to  " TierDefn1~1~TierDefn2~2 "
	    	        bnftTierDefinitionResponse.setTier(tierDescriptionAndIdBuffer.toString());
	            }	
            }
            catch (SevereException e) {
                List logParameters = new ArrayList(1);
                logParameters.add(bnftTierDefinitionRequest);
                String logMessage = "Failed while processing GetBenefitTierDefinitionRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
        }
	    Logger
	    .logInfo("StandardBenefitBusinessService - Returning execute()" +
	    		"GetBenefitTierDefinitionRequest");
	    return bnftTierDefinitionResponse;
     }
  
    
    /**
     * The method retrieves the Tier Definitions associated with a particular
     * BenefitDefinition
     * @param bnftTierDefinitionRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(GetBenefitTierDefinitionForDetailPrintRequest bnftTierDefinitionRequest)
    throws ServiceException {
        Logger
        .logInfo("StandardBenefitBusinessService - Entering execute(): " +
        		"GetBenefitTierDefinitionForDetailPrintRequest");       
        GetBenefitTierDefinitionResponse bnftTierDefinitionResponse 
        = (GetBenefitTierDefinitionResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.BENEFIT_TIER_DEFN_RESPONSE); 
        if (null != bnftTierDefinitionResponse){  
            
            // for retrieving tier definitions associated with each benefit definition
            StandardBenefitBusinessObjectBuilder stdBnftBusinessObjectBuilder 
            				= new StandardBenefitBusinessObjectBuilder();
            BenefitTierDefinitionLocateCriteria bnftTierDefinitionLocateCriteria 
    						= new BenefitTierDefinitionLocateCriteria();
            bnftTierDefinitionLocateCriteria.setBenefitId(bnftTierDefinitionRequest
                    		.getBenefitId()); 
            bnftTierDefinitionLocateCriteria.setBenefitDefinitionId(bnftTierDefinitionRequest
            		.getBenefitDefinitionId());
            try{
	            LocateResults bnftTierDefnlocateResults = stdBnftBusinessObjectBuilder
	            				.locateForDetailedPrint(bnftTierDefinitionLocateCriteria,
	            				 bnftTierDefinitionRequest.getUser());		            
	            List bnftTierDefnList = bnftTierDefnlocateResults.getLocateResults();      
	            
	            if(null!=bnftTierDefnList && bnftTierDefnList.size()>0){		               
	    	        Iterator bnftTierDefnIterator = bnftTierDefnList.iterator();
	    	        StringBuffer tierDescriptionAndIdBuffer = new StringBuffer();
	    	        while(bnftTierDefnIterator.hasNext()){		    	          
	    	            BenefitDefinitionBO benefitTierDefinitionBO = 
	    	                (BenefitDefinitionBO)bnftTierDefnIterator.next();
	    	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDescription());
	    	            tierDescriptionAndIdBuffer.append("~");
	    	            tierDescriptionAndIdBuffer.append(benefitTierDefinitionBO.getTierDefinitionId());
	    	            if(bnftTierDefnIterator.hasNext()){	               
	    	                tierDescriptionAndIdBuffer.append("~");
	    	            }
	    	        }	     	    
	    	        //The String will be similar to  " TierDefn1~1~TierDefn2~2 "
	    	        bnftTierDefinitionResponse.setTier(tierDescriptionAndIdBuffer.toString());
	            }	
            }
            catch (SevereException e) {
                List logParameters = new ArrayList(1);
                logParameters.add(bnftTierDefinitionRequest);
                String logMessage = "Failed while processing GetBenefitTierDefinitionForDetailPrintRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
        }
	    Logger
	    .logInfo("StandardBenefitBusinessService - Returning execute()" +
	    		"GetBenefitTierDefinitionForDetailPrintRequest");
	    return bnftTierDefinitionResponse;
     }
}
