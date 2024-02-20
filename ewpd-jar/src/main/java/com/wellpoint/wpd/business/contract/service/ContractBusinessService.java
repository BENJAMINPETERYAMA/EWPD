/*
 * ContractBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ewpd.am.context.ApplicationContext;
import com.wellpoint.ets.ewpd.am.domain.validation.AdminMethodValidationManager;
import com.wellpoint.wpd.business.adminmethod.adapter.AdminMethodAdapterManager;
import com.wellpoint.wpd.business.common.builder.RuleTypeValidationBuilder;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessServiceHelper;
import com.wellpoint.wpd.business.contract.builder.ContractTreeBuilder;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAdminLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAllVersionsLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractMandatesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractNotesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractProductLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.DatafeedLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.DateSegmentLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ExistingContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.AllPossibleAnswerForAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.bo.manager.LockManager;
import com.wellpoint.wpd.business.framework.service.BusinessServiceController;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitTreeBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSPSValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSynchronousValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSPSValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSynchronousValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationStatusResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.app.config.ApplicationConfigManager;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO;
import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.contract.bo.BenefitCustomizationBO;
import com.wellpoint.wpd.common.contract.bo.Comment;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitComponents;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitDefinitions;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractHistory;
import com.wellpoint.wpd.common.contract.bo.ContractNotes;
import com.wellpoint.wpd.common.contract.bo.ContractPricingInfo;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdmin;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdminBO;
import com.wellpoint.wpd.common.contract.bo.ContractQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.contract.bo.ContractRuleBO;
import com.wellpoint.wpd.common.contract.bo.ContractSPSBO;
import com.wellpoint.wpd.common.contract.bo.CopyBenefitHeadingsBO;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.DateSegmentUpdateAfterDeleteBO;
import com.wellpoint.wpd.common.contract.bo.DeletedDSInfoBO;
import com.wellpoint.wpd.common.contract.bo.ProviderSpecialityCodeBO;
import com.wellpoint.wpd.common.contract.bo.ReserveContractId;
import com.wellpoint.wpd.common.contract.bo.RuleIdBO;
import com.wellpoint.wpd.common.contract.bo.SystemContractId;
import com.wellpoint.wpd.common.contract.bo.TestData;
import com.wellpoint.wpd.common.contract.request.BenefitCustomizationRequest;
import com.wellpoint.wpd.common.contract.request.CheckCopyLegacyNoteRequest;
import com.wellpoint.wpd.common.contract.request.ContractBenefitComponentAttachNotesRequest;
import com.wellpoint.wpd.common.contract.request.ContractNoteAttachmentRequest;
import com.wellpoint.wpd.common.contract.request.ContractNotesDeleteRequest;
import com.wellpoint.wpd.common.contract.request.ContractQuestionTierNoteProcessRequest;
import com.wellpoint.wpd.common.contract.request.ContractQuestionTierNotesPopUpRequest;
import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.contract.request.ContractSearchRequest;
import com.wellpoint.wpd.common.contract.request.ContractTransferLogRequest;
import com.wellpoint.wpd.common.contract.request.ContractUncodedNotesRequest;
import com.wellpoint.wpd.common.contract.request.CreateDateSegmentRequest;
import com.wellpoint.wpd.common.contract.request.DatafeedRequest;
import com.wellpoint.wpd.common.contract.request.DateSegmentCheckoutRequest;
import com.wellpoint.wpd.common.contract.request.DeleteContractComponentNoteRequest;
import com.wellpoint.wpd.common.contract.request.DeleteContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.DeleteContractRequest;
import com.wellpoint.wpd.common.contract.request.DeletePricingInfoRequest;
import com.wellpoint.wpd.common.contract.request.LoadContractBenefitNoteRequest;
import com.wellpoint.wpd.common.contract.request.LoadContractComponentNoteRequest;
import com.wellpoint.wpd.common.contract.request.LocatePricingInformationRequest;
import com.wellpoint.wpd.common.contract.request.LocateProductRequest;
import com.wellpoint.wpd.common.contract.request.MembershipInfoRequest;
import com.wellpoint.wpd.common.contract.request.ReleaseReservedContractRequest;
import com.wellpoint.wpd.common.contract.request.ReservedContractSearchRequest;
import com.wellpoint.wpd.common.contract.request.RetreiveBenefitsContractRequest;
import com.wellpoint.wpd.common.contract.request.RetreiveContractRuleTypeRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBaseContractDateRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBaseContractRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitGeneralInfoRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitLinesRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitMandatesRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveCodedNonCodedBenefitHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitAdministrationRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitComponentRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitMandateRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractNoteRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductAdminOptionOverrideRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractSpecificInfoRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractStandardBenefitRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveDateSegmentsRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveDeletedDSRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveExistingContractIdRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveExpiredContractIdRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveReservedContractIdRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveReservedContractRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveRulesRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveSelectedCommentRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveSystemPoolIdRequest;
import com.wellpoint.wpd.common.contract.request.SaveBenefitAdministrationRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractAdaptedInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractAdministrationRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBenefitNoteRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractSpecificInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.request.SavePricingInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveReservedContractRequest;
import com.wellpoint.wpd.common.contract.request.SaveRuleInformationRequest;
import com.wellpoint.wpd.common.contract.request.SaveTestDataRequest;
import com.wellpoint.wpd.common.contract.response.BenefitCustomizationResponse;
import com.wellpoint.wpd.common.contract.response.CheckCopyLegacyNoteResponse;
import com.wellpoint.wpd.common.contract.response.ContractBenefitComponentAttachNotesResponse;
import com.wellpoint.wpd.common.contract.response.ContractNoteAttachmentResponse;
import com.wellpoint.wpd.common.contract.response.ContractNotesDeleteResponse;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNoteResponse;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.contract.response.ContractSearchResponse;
import com.wellpoint.wpd.common.contract.response.ContractTransferLogResponse;
import com.wellpoint.wpd.common.contract.response.ContractUncodedNotesResponse;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.CreateDateSegmentResponse;
import com.wellpoint.wpd.common.contract.response.DatafeedResponse;
import com.wellpoint.wpd.common.contract.response.DateSegmentCheckoutResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractComponentNoteResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractResponse;
import com.wellpoint.wpd.common.contract.response.DeletePricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.LoadContractBenefitNoteResponse;
import com.wellpoint.wpd.common.contract.response.LoadContractComponentNoteResponse;
import com.wellpoint.wpd.common.contract.response.LocatePricingInformationResponse;
import com.wellpoint.wpd.common.contract.response.LocateProductResponse;
import com.wellpoint.wpd.common.contract.response.MembershipInfoResponse;
import com.wellpoint.wpd.common.contract.response.ReleaseReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.ReservedContractSearchResponse;
import com.wellpoint.wpd.common.contract.response.RetreiveBenefitsContractResponse;
import com.wellpoint.wpd.common.contract.response.RetreiveContractRuleTypeResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBaseContractDateResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBaseContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitGeneralInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitLinesResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveCodedNonCodedBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitComponentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitMandateResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractNoteResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminOptionOverrideResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractStandardBenefitResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDeletedDSResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveExistingContractIdResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveExpiredContractIdsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractIdResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveRulesResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveSelectedCommentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveSystemPoolIdResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdaptedInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitNoteResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.SavePricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.SaveRuleInformationResponse;
import com.wellpoint.wpd.common.contract.response.SaveTestDataResponse;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;
import com.wellpoint.wpd.common.contract.vo.ContractLocateCriteriaVO;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.contract.vo.ReserveContractVO;
import com.wellpoint.wpd.common.contract.vo.RuleTypeValidateOutputVO;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedByAnotherUserException;
import com.wellpoint.wpd.common.framework.exception.lock.LockedBySameUserException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.ContractProductAONotesAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ContractAONotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.ContractNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.ContractTeiredNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.ContractAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.ContractNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.ContractTeiredNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.vo.BenefitLineVO;
import com.wellpoint.wpd.common.product.bo.AdminBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.product.vo.ProductAdminVO;
import com.wellpoint.wpd.common.productstructure.bo.EntityBenefitAdministrationPSBO;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitMandatesResponse;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitDefinition;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.common.tierdefinition.request.ContractTierDefSaveRequest;
import com.wellpoint.wpd.common.tierdefinition.request.ContractTierDeleteRequest;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDeleteResponse;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.contractidpool.admin.ContractIDRepositoryAdmin;
import com.wellpoint.wpd.contractidpool.admin.ContractIDReservePool;
import com.wellpoint.wpd.contractidpool.admin.ContractIDSystemPool;
import com.wellpoint.wpd.contractidpool.exceptions.ContractIDPoolException;
import com.wellpoint.wpd.contractidpool.vo.ContractIDPoolRecord;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReserveCriteria;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.contract.ContractKeyObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBusinessService extends WPDBusinessService {

	/**
	 * Service method for Retrieving Uncoded Notes.
	 * 
	 * @param ContractUncodedNotesRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(
			ContractUncodedNotesRequest contractUncodedNotesRequest)
			throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ contractUncodedNotesRequest.getClass().getName());
		ContractUncodedNotesResponse contractUncodedNotesResponse = (ContractUncodedNotesResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CONTRACT_UNCODED_NOTES_RESPONSE);
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		RuleTypeValidationBuilder ruleTypeValidationBuilder = new RuleTypeValidationBuilder();
		List messageList = new ArrayList(2);
		try {
			if (ContractUncodedNotesRequest.RETREIVE_UNCODED_NOTES == contractUncodedNotesRequest
					.getAction()) {
				List allNotesList = null;
				List dateSegmentList = contractBusinessObjectBuilder
						.retrieveDateSegments(contractUncodedNotesRequest
								.getContractId());
				ContractQuestUniqueReferenceBO contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
						.get(0);
				String productName = contractDateSegmentsBO.getProductName();
				contractUncodedNotesResponse.setProductName(productName);
				allNotesList = contractBusinessObjectBuilder
						.getUncodedNotesList(dateSegmentList);
				contractUncodedNotesResponse
						.setUncodedAllNotesList(allNotesList);

				List tierNotesList = null;
				tierNotesList = contractBusinessObjectBuilder
						.getUncodedTierNotesList(dateSegmentList);
				contractUncodedNotesResponse
						.setUncodedTierNotesList(tierNotesList);

				ErrorMessage message = new ErrorMessage(
						BusinessConstants.CONTRACT_NOTES);
				messageList.add(message);

			} else if (ContractUncodedNotesRequest.DELETE_UNCODED_NOTES_CHECKIN == contractUncodedNotesRequest
					.getAction()) {
				Contract contract = new Contract();
				contract.setContractId(contractUncodedNotesRequest
						.getContractId());
				contract.setContractSysId(contractUncodedNotesRequest
						.getContractKeyObject().getContractSysId());
				contract.setVersion(contractUncodedNotesRequest
						.getContractKeyObject().getVersion());
				contract.setStatus(contractUncodedNotesRequest
						.getContractKeyObject().getStatus());
				List chekinDateSegmentList = retrieveCheckInDateSegments(contract
						.getContractSysId());
				contractBusinessObjectBuilder.deleteUncodedNotes(contract
						.getContractSysId());
				/*
				 * if (null != chekinDateSegmentList &&
				 * !chekinDateSegmentList.isEmpty()) { Iterator itr =
				 * chekinDateSegmentList.iterator(); while (itr.hasNext()) {
				 * DateSegment datesegment = (DateSegment) itr.next();
				 * bom.checkIn(datesegment, contractUncodedNotesRequest
				 * .getUser()); } } bom.checkIn(contract,
				 * contractUncodedNotesRequest.getUser()); InformationalMessage
				 * informationalMessage = new InformationalMessage(
				 * BusinessConstants.MSG_CONTRACT_CHECKIN_SUCCESS);
				 * informationalMessage.setParameters(new String[] { contract
				 * .getContractId() }); InformationalMessage deleteMessage = new
				 * InformationalMessage(
				 * BusinessConstants.MSG_UNCODED_NOTE_DELETED);
				 * messageList.add(informationalMessage);
				 * messageList.add(deleteMessage);
				 * contractUncodedNotesResponse.setSuccess(true);
				 */
				RuleTypeValidateOutputVO ruleTypeValidateOutput = new RuleTypeValidateOutputVO();

				if (null != chekinDateSegmentList
						&& !chekinDateSegmentList.isEmpty()) {

					ruleTypeValidateOutput = ruleTypeValidationBuilder
							.validateDateSegmentRuleTypes(chekinDateSegmentList);
					if (ruleTypeValidateOutput.getErrorMessages().isEmpty()) {
						RuleTypeValidationBuilder
								.updateBlzIndicatorInLatestVersion(
										ruleTypeValidateOutput.getDateSegment(),
										contractUncodedNotesRequest.getUser());
						Iterator itr = ruleTypeValidateOutput.getDateSegment()
								.iterator();
						while (itr.hasNext()) {
							DateSegment datesegment = (DateSegment) itr.next();
							bom.checkIn(datesegment,
									contractUncodedNotesRequest.getUser());
							Logger.logInfo("DateSegment Check In Successfull");
						}
						bom.checkIn(contract, contractUncodedNotesRequest
								.getUser());
						InformationalMessage informationalMessage = new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_CHECKIN_SUCCESS);
						informationalMessage
								.setParameters(new String[] { contract
										.getContractId() });
						InformationalMessage deleteMessage = new InformationalMessage(
								BusinessConstants.MSG_UNCODED_NOTE_DELETED);
						messageList.add(informationalMessage);
						messageList.add(deleteMessage);
						contractUncodedNotesResponse.setSuccess(true);
						Logger.logInfo("Contract Check In Successfull");

					} else { // errorMessages.is not Empty
						Logger
								.logDebug("List errorMessages is not Empty,Rule Types are not same ");
						contractUncodedNotesResponse.setSuccess(false);
						messageList.addAll(ruleTypeValidateOutput
								.getErrorMessages());
					} // end of errorMessages.is not Empty

				} else { // if datesegmentList is empty
					bom
							.checkIn(contract, contractUncodedNotesRequest
									.getUser());
					InformationalMessage informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_CHECKIN_SUCCESS);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					InformationalMessage deleteMessage = new InformationalMessage(
							BusinessConstants.MSG_UNCODED_NOTE_DELETED);
					messageList.add(informationalMessage);
					messageList.add(deleteMessage);
					contractUncodedNotesResponse.setSuccess(true);
					Logger.logInfo("Contract Check In Successfull");
				}
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			contractUncodedNotesResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(contractUncodedNotesRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		contractUncodedNotesResponse.setMessages(messageList);
		Logger.logInfo("Exiting execute method, request = "
				+ contractUncodedNotesRequest.getClass().getName());
		return contractUncodedNotesResponse;
	}

	/**
	 * Service method for Retrieving Transfer Log information.
	 * 
	 * @param contractTransferLogrequest
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(
			ContractTransferLogRequest contractTransferLogrequest)
			throws ServiceException {

		ContractTransferLogResponse contractTransferLogResponse = null;
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		List transferLogResultList = new ArrayList();
		User user = contractTransferLogrequest.getUser();
		try {
			transferLogResultList = builder
					.locateTransferLog(contractTransferLogrequest
							.getContractTransferLogBO(), user);
		} catch (SevereException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(contractTransferLogrequest);
			String logMessage = BusinessConstants.FAILED_TO_PROCESS;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		contractTransferLogResponse = (ContractTransferLogResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CONTRACT_TRANSFER_LOG_RESPONSE);
		contractTransferLogResponse
				.setContractTransferLogList(transferLogResultList);
		return contractTransferLogResponse;
	}

	/**
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetrieveRulesRequest request)
			throws ServiceException, AdapterException {
		RetrieveRulesResponse response = null;
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		try {
			response = (RetrieveRulesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RULES_SEARCH_RESPONSE);
			BusinessObjectManager bom = getBusinessObjectManager();

			RuleLocateCriteria locateCriteria;
			LocateResults locateResults = null;
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
			int action = request.getAction();

			switch (action) {

			case RetrieveRulesRequest.RETRIEVE_RULES_POPUP:
				int id = request.getProductSysId();
				int dsId = request.getDateSegmentSysId();
				String ruleType = request.getRuleType();
				locateCriteria = new RuleLocateCriteria();
				locateCriteria.setProductSysId(id);
				locateCriteria.setDateSegmentId(dsId);
				locateCriteria.setRuleType(ruleType);
				locateResults = builder.locate(locateCriteria, request
						.getUser(), true);
				response.setSearchResultList(locateResults.getLocateResults());

				if (null == locateResults.getLocateResults()
						|| locateResults.getLocateResults().size() == 0) {
					response.addMessage(new InformationalMessage(
							BusinessConstants.MSG_NO_RULES_PRODUCT));
				}
				response.setSuccess(true);
				break;

			case RetrieveRulesRequest.ATTACH_RULES:

				DateSegment dateSegment = getDateSegment(request);
				ContractRuleBO contractRuleBO = new ContractRuleBO();
				contractRuleBO.setRuleList(request.getRuleList());

				contractRuleBO.setDateSegmentId(request.getContractKeyObject()
						.getDateSegmentId());
				bom.persist(contractRuleBO, dateSegment, request.getUser(),
						true);
				response.addMessage(new InformationalMessage(
						BusinessConstants.MSG_ATTACH_RULES_CONTRACT));
				response.setSuccess(true);
				break;

			case RetrieveRulesRequest.RETRIVE_RULES_COMPLETE:
				locateCriteria = new RuleLocateCriteria();
				locateCriteria.setDateSegmentId(request.getDateSegmentSysId());
				locateCriteria.setRuleType(request.getRuleType());
				locateResults = builder.locate(locateCriteria, request
						.getUser(), false);
				response.setSearchResultList(locateResults.getLocateResults());
				response.setSuccess(true);
				break;
			case RetrieveRulesRequest.DELETE_RULES:
				DateSegment dateSegmentRuleDeleteBO = getDateSegment(request);
				String rules = null;
				if (request.getRuleIdList() != null
						&& !request.getRuleIdList().isEmpty()) {
					List ruleIdList = request.getRuleIdList();
					List generatedList = request.getGeneratedRuleIdList();
					for (int rulecount = 0; rulecount < ruleIdList.size(); rulecount++) {
						ContractRuleBO contractRuleBONew = new ContractRuleBO();
						contractRuleBONew.setDateSegmentId(request
								.getDateSegmentSysId());
						contractRuleBONew
								.setProductRuleSysId(((Integer) ruleIdList
										.get(rulecount)).intValue());
						bom.delete(contractRuleBONew, dateSegmentRuleDeleteBO,
								request.getUser());
						if (rulecount > 0)
							rules = rules + ","
									+ generatedList.get(rulecount).toString();
						else
							rules = generatedList.get(rulecount).toString();

					}
				}
				InformationalMessage message = new InformationalMessage(
						BusinessConstants.MSG_DELETE_RULES_CONTRACT);
				message.setParameters(new String[] { rules });
				response.addMessage(message);
				response.setSuccess(true);

				break;

			case RetrieveRulesRequest.UPDATE_RULES:

				DateSegment dateSegmentRuleUpdateBO = getDateSegment(request);

				ContractRuleBO contractRuleBONewOne = null;
				ContractRuleBO contractRuleBONewTwo = new ContractRuleBO();
				List newList = null;
				Map tempMap = new HashMap();
				if (null != request.getRuleList()
						&& request.getRuleList().size() != 0) {
					tempMap = (HashMap) request.getRuleList().get(0);
					newList = new ArrayList(request.getRuleList().size());
				}
				Iterator ruleIDIter = tempMap.keySet().iterator();
				int proRuleSysID = 0;
				String ruleComment = null;
				while (ruleIDIter.hasNext()) {
					Object key = ruleIDIter.next();
					proRuleSysID = Integer.parseInt(key.toString());
					ruleComment = (String) tempMap.get(key);
					contractRuleBONewOne = new ContractRuleBO();
					contractRuleBONewOne.setProductRuleSysId(proRuleSysID);
					if (null != ruleComment) {
						contractRuleBONewOne.setRuleOverRidValue(ruleComment
								.trim());
					}

					newList.add(contractRuleBONewOne);
				}
				contractRuleBONewTwo.setRuleList(newList);

				contractRuleBONewTwo.setDateSegmentId(request
						.getContractKeyObject().getDateSegmentId());
				bom.persist(contractRuleBONewTwo, dateSegmentRuleUpdateBO,
						request.getUser(), false);
				response.addMessage(new InformationalMessage(
						BusinessConstants.MSG_UPDATE_RULES_CONTRACT));
				response.setSuccess(true);
				break;

			case RetrieveRulesRequest.RETRIEVE_RULES_SEQUENCE:
				locateCriteria = new RuleLocateCriteria();
				String ruleId = request.getRuleId();
				locateCriteria.setRuleId(ruleId);
				locateResults = builder.locateRulesSequence(locateCriteria,
						request.getUser());
				response.setSearchResultList(locateResults.getLocateResults());
				response.setSuccess(true);
				break;
			}
			Logger.logInfo("Exiting execute method, request = "
					+ request.getClass().getName());
			return response;
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveRulesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveRulesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetreiveBenefitsContractRequest request)
			throws ServiceException {
		RetreiveBenefitsContractResponse response = null;
		try {
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
			response = (RetreiveBenefitsContractResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.BENEFIT_SEARCH_RESPONSE);
			List list = builder.getBenefits(request.getDateSegmentId(), request
					.getBenefitComponentId(), request.isShowHidden(), request
					.getUser());
			if (null != list && list.size() != 0) {
				response.setBenefitList(list);
			}
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetreiveBenefitsContractRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 * @throws AdapterException
	 */
	public WPDResponse execute(BenefitCustomizationRequest request)
			throws ServiceException {

		BenefitCustomizationResponse response = null;
		try {
			response = new BenefitCustomizationResponse();
			List benefitList = request.getBenefitList();
			ContractKeyObject contractKeyObject = request
					.getContractKeyObject();
			BusinessObjectManager bom = null;
			bom = getBusinessObjectManager();
			List messageList = new ArrayList(5);
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
			/*
			 * Contract contract = new Contract();
			 * contract.setContractSysId(contractKeyObject.getContractSysId());
			 * contract.setContractId(contractKeyObject.getContractId());
			 * contract.setStatus(contractKeyObject.getStatus());
			 * contract.setVersion(contractKeyObject.getVersion());
			 */
			BenefitCustomizationBO bo = new BenefitCustomizationBO();
			bo.setBenefitList(benefitList);

			/** Added for comparing flag benefit* */
			// bo.setBenefitFlagHiddenMap(request.getBenefitHiddenFlagMap());
			/** end* */

			DateSegment dateSegment = getDateSegment(request);
			bom.persist(bo, dateSegment, request.getUser(), false);

			updateAMVForBenefitsInBCInContract(request);

			response.setBenefitComponentHide(builder
					.getBenefitComponentStatus(request.getBenefitComponentId(),
							request.getDateSegmentId()));
			response.setShowHidden(request.isShowHidden());
			if (!response.isBenefitComponentHide()) {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_CONTRACT_BENEFITS_HIDEUNHIDE));
			} else {
				InformationalMessage message = new InformationalMessage(
						BusinessConstants.MSG_CONTRACT_BENEFITS_ALLHIDE);
				message.setParameters(new String[] { ""
						+ request.getBenefitComponentDesc() });
				messageList.add(message);

			}
			response.setMessages(messageList);

			response.setSuccess(true);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing BenefitCustomizationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing BenefitCustomizationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * @param request
	 * @throws ServiceException
	 */
	private void updateAMVForBenefitsInBCInContract(
			BenefitCustomizationRequest request) throws ServiceException {
		if (null != request && request.isChanged()) {
			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(request.getContractKeyObject()
					.getProductId());
			validationRqst.setEntityId(request.getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setChangedIds(request.getChangedIds());
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.BENEFITS_CHANGE_IN_ENTITY);
			validationRqst.setBenefitComponentId(request
					.getBenefitComponentId());
			validationRqst
					.setBenefitCompName(request.getBenefitComponentDesc());
			validationRqst.setContractId(request.getContractKeyObject()
					.getContractSysId());

			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);
		}

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 * @throws AdapterException
	 */
	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 * @throws AdapterException
	 */
	public WPDResponse execute(SaveReservedContractRequest request)
			throws WPDException, AdapterException {

		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

		ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
		ContractIDSystemPool contractIDSystemPool = null;
		;
		ContractIDReservePool contractIDReservePool = null;
		try {
			contractIDSystemPool = contractIDRepositoryAdmin
					.getContractSystemPool();
			contractIDReservePool = contractIDRepositoryAdmin
					.getContractReservePool();
		} catch (ContractIDPoolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			throw new ServiceException("Adapter exception", null, e2);
		}

		SaveReservedContractResponse response = (SaveReservedContractResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_RESERVED_RESPONSE);
		int action = request.getAction();
		ReserveContractId reservedContract = new ReserveContractId();
		List messageList = new ArrayList();
		switch (action) {

		case SaveReservedContractRequest.CREATE_RESERVED_CONTRACT:
			Audit audit = getAudit(request.getUser());
			// setValuesToBO(request.getReservedContractVO(), reservedContract);
			List idList = request.getContractIdList();
			if (null != idList && idList.size() > 0) {
				int size = request.getContractIdList().size();
				for (int i = 0; i < size; i++) {
					ContractIDReservePoolRecord contractIDReservePoolRecord = (ContractIDReservePoolRecord) idList
							.get(i);
					contractIDReservePoolRecord.setCreatedUser(request
							.getUser().getUserId());
					contractIDReservePoolRecord.setLastUpdatedUser(request
							.getUser().getUserId());
					contractIDReservePoolRecord.setCreatedTimeStamp(audit
							.getTime());
					contractIDReservePoolRecord.setLastUpdatedTimeStamp(audit
							.getTime());
					contractIDReservePoolRecord.setBusinessDomain(request
							.getReservedContractVO().getBusinessDomains());
					contractIDReservePoolRecord
							.setSystemPoolStatus(contractIDReservePoolRecord
									.getSystemPoolStatus().trim());
				}
				ContractIDReserveCriteria contractIDReserveCriteria = new ContractIDReserveCriteria();
				contractIDReserveCriteria.setContractIDs(request
						.getContractIdList());
				// ContractIDSystemPool systemPool = new ContractIDSystemPool();
				boolean bool = contractIDSystemPool
						.reserveContractIds(contractIDReserveCriteria);
				if (bool)
					response.setSuccess(true);
			} else {
				response.setSuccess(false);
				messageList.add(new InformationalMessage(
						BusinessConstants.NO_LIST_TO_RESERVE));
				response.setMessages(messageList);
			}
			break;

		case SaveReservedContractRequest.UPDATE_RESERVED_CONTRACT:
			ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
			Date date = WPDStringUtil.getDateFromString(request
					.getReservedContractVO().getEndDate());
			contractIDReservePoolRecord.setContractId(request
					.getReservedContractVO().getContractId());
			contractIDReservePoolRecord.setExpiryDate(date);
			contractIDReservePoolRecord.setReservePoolStatus(request
					.getReservedContractVO().getReservePoolStatus());
			contractIDReservePoolRecord.setLastUpdatedUser(request.getUser()
					.getUserId());

			contractIDReservePool
					.updateReserveContract(contractIDReservePoolRecord);

			List contractList = contractIDSystemPool.locateContractIds(
					contractIDReservePoolRecord, 1, false);
			response.setContractList(contractList);

			response.addMessage(new InformationalMessage(
					BusinessConstants.MSG_UPDATE_RESERVED));
			response.setSuccess(true);
			break;

		}
		return response;

	}

	/**
	 * 
	 * @param reservedContractVO
	 * @param reservedContract
	 */
	private void setValuesToBO(ReserveContractVO reservedContractVO,
			ReserveContractId reservedContract) {
		reservedContract.setNumberOfIdToGenerate(Integer
				.parseInt(reservedContractVO.getNumberOfIdToGenerate()));
		reservedContract.setBusinessDomains(reservedContractVO
				.getBusinessDomains());
		reservedContract.setStartDate(WPDStringUtil
				.getDateFromString(reservedContractVO.getStartDate()));
		reservedContract.setEndDate(WPDStringUtil
				.getDateFromString(reservedContractVO.getEndDate()));

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveReservedContractRequest request)
			throws WPDException {

		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		RetrieveReservedContractResponse response = (RetrieveReservedContractResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_RESERVED_RESPONSE);
		int action = request.getAction();

		ReserveContractId reserveContractId = new ReserveContractId();
		switch (action) {

		case RetrieveReservedContractRequest.RETRIEVE_CONTRACT_ALL:
			String contId = request.getContractId();
			int contractSysId = request.getContractSysId();
			reserveContractId.setContractId(contId);
			reserveContractId = builder.retrieve(reserveContractId, request
					.getUser());
			response.setReserveContractId(reserveContractId);

			response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
					"reservecontract", contractSysId));
			break;

		case RetrieveReservedContractRequest.RETRIEVE_CONTRACT_ID:
			ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
			ContractIDSystemPool systemPool = null;
			systemPool = contractIDRepositoryAdmin.getContractSystemPool();
			String contractId = systemPool.getContractId().getContractId();// getContractId()
			// of
			// ContractIDPoolRecord
			// String contractId = builder.getInitialContractId();
			response.setContractId(contractId);
			break;

		case RetrieveReservedContractRequest.RETRIEVE_CONTRACT_SYS_ID:

			int contractSysID = builder.getContractSysID(request
					.getContractId());
			response.setContractSysId(contractSysID);
			break;

		}
		response.setSuccess(true);
		return response;

	}

	public WPDResponse execute(MembershipInfoRequest request)
			throws ServiceException {
		Contract contract = new Contract();
		MembershipInfoResponse response = (MembershipInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.MEMBERSHIP_INFO_RESPONSE);
		ContractKeyObject contractKeyObject = request.getContractKeyObject();

		contract.setContractSysId(contractKeyObject.getContractSysId());
		contract.setContractId(contractKeyObject.getContractId());
		contract.setBaseDateSegmentSysId(contractKeyObject.getDateSegmentId());
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		try {
			List membershipList = builder.getMembershipData(contract);
			if (null != membershipList) {
				response.setMembershipList(membershipList);
			}
		} catch (SevereException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing MembershipInfoRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(LocateProductRequest request)
			throws WPDException {
		Logger.logInfo("Entering execute method, request = "
				+ request.getClass().getName());
		ContractBusinessObjectBuilder bom = new ContractBusinessObjectBuilder();
		LocateProductResponse response = (LocateProductResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.LOCATE_PRODUCT_RESPONSE);
		ContractProductLocateCriteria locateCriteria = new ContractProductLocateCriteria();
		locateCriteria.setLineOfBusiness(request.getLineOfBusiness());
		locateCriteria.setBusinessEntity(request.getBusinessEntity());
		locateCriteria.setBusinessGroup(request.getBusinessGroup());
		/* START CARS */
		locateCriteria.setMarketBusinessUnit(request.getMarketBusinessUnit());
		/* END cARS */
		locateCriteria.setEffectiveDate(request.getEffectiveDate());
		locateCriteria.setExpiryDate(request.getExpiryDate());
		locateCriteria.setProductType(request.getProductType());
		locateCriteria.setTestContractState(request.getTestContractState());
		locateCriteria.setState(request.getState());
		try {
			LocateResults locateResults = bom.locate(locateCriteria, request
					.getUser());
			response.setProducts(locateResults.getLocateResults());
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		}
		return response;

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveDateSegmentsRequest request)
			throws ServiceException {
		RetrieveDateSegmentsResponse response = null;
		try {
			response = (RetrieveDateSegmentsResponse) WPDResponseFactory
			.getResponse(WPDResponseFactory.DATE_SEGMENTS_SEARCH_RESPONSE);
			BusinessObjectManager bom = getBusinessObjectManager();
	
			DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
			LocateResults locateResults = null;
			
			if(request.getAction() == RetrieveDateSegmentsRequest.FETCH_OLDER_VERSIONS){
				locateCriteria.setContractId(request.getContractId());
				locateCriteria.setVersion(request.getVersion());
				locateCriteria.setEffectiveDate(request.getEffectiveDate());
				//locateCriteria.setExpiryDate(request.getExpiryDate());
				locateCriteria.setFetchOlderVersion(true);
				locateResults = bom.locate(locateCriteria, request.getUser());
		
				if (locateResults.getLocateResults().size() <= 0) {
					response.addMessage(new ErrorMessage(
							BusinessConstants.SEARCH_RESULT_NOT_FOUND));
				}
				response.setSearchResultList(locateResults.getLocateResults());
				response.setSuccess(true);
			}else{
					
				locateCriteria.setContractSysId(request.getContractSysId());
				locateResults = bom.locate(locateCriteria, request.getUser());
	
				if (locateResults.getLocateResults().size() <= 0) {
					response.addMessage(new ErrorMessage(
							BusinessConstants.SEARCH_RESULT_NOT_FOUND));
				}
				response.setSearchResultList(locateResults.getLocateResults());
				response.setSuccess(true);
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveDateSegmentsRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;

	}

	public WPDResponse execute(DateSegmentCheckoutRequest request)
			throws ServiceException {
		DateSegmentCheckoutResponse response = null;
		response = (DateSegmentCheckoutResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CHECKOUT_DATESEGMENT_RESPONSE);
		try {

			Map map = new HashMap();
			BusinessObjectManager bom = getBusinessObjectManager();
			DateSegment ds = null;
			map.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			map.put(BusinessConstants.DATESEGMENT_ID, new Integer(request
					.getContractKeyObject().getDateSegmentId()));
			Contract contract = new Contract();
			contract.setContractId(request.getContractKeyObject()
					.getContractId());
			contract.setStatus(request.getContractKeyObject().getStatus());
			contract.setVersion(request.getContractKeyObject().getVersion());
			contract = (Contract) bom
					.retrieve(contract, request.getUser(), map);
			List dateSegList = contract.getDateSegmentList();
			if (null != dateSegList) {
				ds = (DateSegment) dateSegList.get(0);
				ds
						.setContractId(request.getContractKeyObject()
								.getContractId());
				if (!((BusinessConstants.STATUS_BUILDING)
						.equals(ds.getStatus()))
						&& !((BusinessConstants.CHECKED_OUT).equals(ds
								.getStatus()))) {
					ds.setContractSysId(request.getContractKeyObject()
							.getContractSysId());
					ds.setProductStatus("N");
					ds = (DateSegment) bom.checkOut(ds, request.getUser());
				}
				response.setSuccess(true);
				response.setDateSegmentId(ds.getDateSegmentSysId());
			}
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return response;
	}

	// Admin Method Validation for Delete Tier in Contract
	private void updateAMVForTierContract(ContractTierDeleteRequest request)
			throws ServiceException {
		if (null != request) {
			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setEntityId(request.getContractKeyObject()
					.getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setBenefitComponentId(request
					.getBenefitComponentSysId());
			validationRqst.setBenefitId(request.getBenefitId());
			validationRqst.setProductId(request.getProductSysId());
			List deletedIds = new ArrayList(1);
			deletedIds.add(new Integer(request.getContractTierSysId()));
			validationRqst.setChangedIds(deletedIds);
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_TIER_IN_CONTRACT_VALIDATION);

			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);
		}
	}

	/**
	 * This method converts the VO to BO and processess the
	 * SaveContractBenefitDefinitionRequest
	 * 
	 * @param request
	 *            SaveContractBenefitDefinitionRequest.
	 * @return SaveContractBenefitDefinitionResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(SaveContractBenefitDefinitionRequest request)
			throws ServiceException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage",
				"ContractBusinessService", "execute()"));

		Map mapOfLists = null;

		List modfiedLineList = null;
		List newlyCodedLineList = null;
		List uncodedLineList = null;
		// String tierSysLineIdStr = null;

		SaveContractBenefitDefinitionResponse response = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			response = (SaveContractBenefitDefinitionResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE);

			/*
			 * mapOfLists = generateNewLists(request.getChangedLines(), request
			 * .getNotChangedLines(), request.getTierSysLineIdMap());
			 */

			mapOfLists = generateNewLists(request.getChangedLines(), request
					.getNotChangedLines());

			modfiedLineList = (List) mapOfLists.get("0");
			newlyCodedLineList = (List) mapOfLists.get("1");
			uncodedLineList = (List) mapOfLists.get("2");
			// tierSysLineIdStr = (String) mapOfLists.get("3");

			// Adding the newly generated string to the request in the format
			// TierID1:LineID1,LineID2~TierID2:LineID1,LineID2,LineID3
			// request.setTierSysLineStr(tierSysLineIdStr);

			// gets the main object
			DateSegment dateSegment = getDateSegment(request);
			// Contract contract = getContract(request);

			// gets the updated line list from request
			List benefitLineVoList = request.getBenefitLinesList();
			// List benefitLevelsToBeDeleted =
			// request.getDeleteBenefitLevelList();
			int bnftComponentId = request.getBenefitComponentId();

			int dateSegmentId = request.getDateSegmentId();

			int productSysId = request.getProductSysId();

			int bnftSysId = request.getBenefitSysId();

			int bnftDefnSysId = request.getBenefitDefnSysId();

			BusinessObjectManager bom = getBusinessObjectManager();
			User user = request.getUser();

			// creates the sub object
			// ProductBenefitDefinitions productBenefitDefinitions = new
			// ProductBenefitDefinitions();
			ContractBenefitDefinitions contractBenefitDefinitions = new ContractBenefitDefinitions();
			// List updatedLinesList = new ArrayList(benefitLineVoList.size());
			List updatedLinesList = new ArrayList(modfiedLineList.size());
			List savedLinesList = new ArrayList(newlyCodedLineList.size());
			List deletedLineList = new ArrayList(uncodedLineList.size());
			// iterates the list to get the individual VOs n they r converted in
			// to BOs
			if (null != modfiedLineList) {
				for (int i = 0; i < modfiedLineList.size(); i++) {
					BenefitLine contractBenefitDefinitionBO = new BenefitLine();
					BenefitLineVO entityBenefitLine = (BenefitLineVO) modfiedLineList
							.get(i);
					contractBenefitDefinitionBO
							.setBenefitValue(entityBenefitLine
									.getOverridedValue());
					/* START CARS */
					if (null != entityBenefitLine.getOverridedFreqValue()
							&& !WebConstants.EMPTY_STRING
									.equalsIgnoreCase(entityBenefitLine
											.getOverridedFreqValue())) {
						contractBenefitDefinitionBO.setFrequencyValue(Integer
								.parseInt(entityBenefitLine
										.getOverridedFreqValue()));
					}
					contractBenefitDefinitionBO.setLevelDesc(entityBenefitLine
							.getOverridedLvlDescValue());
					/* END CARS */
					contractBenefitDefinitionBO.setLineSysId(entityBenefitLine
							.getBenefitLineId());
					updatedLinesList.add(contractBenefitDefinitionBO);

				}
			}
			if (null != newlyCodedLineList) {
				for (int i = 0; i < newlyCodedLineList.size(); i++) {
					BenefitLine contractBenefitDefinitionBO = new BenefitLine();
					BenefitLineVO entityBenefitLine = (BenefitLineVO) newlyCodedLineList
							.get(i);
					contractBenefitDefinitionBO
							.setBenefitValue(entityBenefitLine
									.getOverridedValue());
					/* START CARS */
					if (null != entityBenefitLine.getOverridedFreqValue()
							&& !WebConstants.EMPTY_STRING
									.equalsIgnoreCase(entityBenefitLine
											.getOverridedFreqValue())) {
						contractBenefitDefinitionBO.setFrequencyValue(Integer
								.parseInt(entityBenefitLine
										.getOverridedFreqValue()));
					}
					contractBenefitDefinitionBO.setLevelDesc(entityBenefitLine
							.getOverridedLvlDescValue());
					/* END CARS */
					contractBenefitDefinitionBO.setLineSysId(entityBenefitLine
							.getBenefitLineId());
					contractBenefitDefinitionBO.setLevelSysId(entityBenefitLine
							.getBenefitLvlId());

					savedLinesList.add(contractBenefitDefinitionBO);

				}
			}
			if (null != uncodedLineList) {
				for (int i = 0; i < uncodedLineList.size(); i++) {
					BenefitLine contractBenefitDefinitionBO = new BenefitLine();
					BenefitLineVO entityBenefitLine = (BenefitLineVO) uncodedLineList
							.get(i);
					contractBenefitDefinitionBO
							.setBenefitValue(entityBenefitLine
									.getOverridedValue());
					/* START CARS */
					if (null != entityBenefitLine.getOverridedFreqValue()
							&& !WebConstants.EMPTY_STRING
									.equalsIgnoreCase(entityBenefitLine
											.getOverridedFreqValue())) {
						contractBenefitDefinitionBO.setFrequencyValue(Integer
								.parseInt(entityBenefitLine
										.getOverridedFreqValue()));
					}
					contractBenefitDefinitionBO.setLevelDesc(entityBenefitLine
							.getOverridedLvlDescValue());
					/* END CARS */
					contractBenefitDefinitionBO.setLineSysId(entityBenefitLine
							.getBenefitLineId());
					deletedLineList.add(contractBenefitDefinitionBO);

				}
			}
			contractBenefitDefinitions.setUpdatedBenefitLines(updatedLinesList);
			contractBenefitDefinitions.setSavedBenefitLines(savedLinesList);
			contractBenefitDefinitions.setDeletedBenefitLines(deletedLineList);

			contractBenefitDefinitions.setBenefitComponentId(bnftComponentId);
			contractBenefitDefinitions.setDateSegmentId(dateSegmentId);
			contractBenefitDefinitions.setProductSysId(productSysId);
			contractBenefitDefinitions.setBnftSysId(bnftSysId);
			contractBenefitDefinitions.setBnftDefnSysId(bnftDefnSysId);

			if (null != request.getBenefitTierDefinitionList()) {
				contractBenefitDefinitions.setBenefitTierDefinitionList(request
						.getBenefitTierDefinitionList());
			}
			if (null != request.getBenefitTierLevelList()) {
				contractBenefitDefinitions.setBenefitTierLevelList(request
						.getBenefitTierLevelList());
			}
			// calls the persist method for update
			bom.persist(contractBenefitDefinitions, dateSegment, user, false);

			if (request.getChangedBenefitLines() != null
					&& request.getChangedBenefitLines().size() > 0)
				updateAMVForBnftLinesInContract(request);

			List messageList = new ArrayList(3);

			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_PRDCT_BEN_DEFN_UPDATED));
			addMessagesToResponse(response, messageList);
			Logger.logInfo(th.endPerfLogging());
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing SaveContractBenefitDefinitionRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * This method create three list depends on lines modifed,newly coded and un
	 * coded
	 * 
	 * @param newLines
	 *            Lines modified
	 * @param oldLines
	 *            Lines loaded
	 * @return A map with three lists
	 */
	private Map generateNewLists(Map newLines, Map oldLines) {

		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage",
				"ContractBusinessService", "generateNewLists()"));

		Set a = newLines.keySet();
		Iterator it = a.iterator();

		BenefitLine benefitLine = null;
		BenefitLineVO benefitLineVO = null;

		List linesToBeModified = new ArrayList();
		List linesToBeInserted = new ArrayList();
		List linesToBeDeleted = new ArrayList();

		Map listMap = new LinkedHashMap();

		while (it.hasNext()) {

			boolean updated = false;
			boolean inserted = false;
			boolean deleted = false;

			String key = (String) it.next();
			benefitLine = (BenefitLine) oldLines.get(key);

			if (null != oldLines && oldLines.containsKey(key)) {
				benefitLineVO = (BenefitLineVO) newLines.get(key);

				if (null != benefitLineVO)
					benefitLineVO.setBenefitDefnId(benefitLine
							.getBenefitDefinitionId());

				/*-- Update condition for benefit value --*/
				if (null != benefitLine.getBenefitValue()
						&& !benefitLine.getBenefitValue().equals("")) {
					if (null != benefitLineVO.getOverridedValue()
							&& !benefitLineVO.getOverridedValue().equals("")
							&& !benefitLine.getBenefitValue().equals(
									benefitLineVO.getOverridedValue())) {
						updated = true;

					}
				}

				/*-- Delete condition for benefit value -- */
				if (null != benefitLine.getBenefitValue()
						&& !benefitLine.getBenefitValue().equals("")) {
					if (null == benefitLineVO.getOverridedValue()
							|| benefitLineVO.getOverridedValue().equals("")) {
						deleted = true;

					}
				}
				/*-- Update condition for FrequencyValue --*/
				if (benefitLine.getFrequencyValue() != 0) {
					if (null != benefitLineVO.getOverridedFreqValue()
							&& !benefitLineVO.getOverridedFreqValue()
									.equals("")
							&& !benefitLineVO.getOverridedFreqValue().equals(
									String.valueOf(benefitLine
											.getFrequencyValue()))) {
						updated = true;
					}
				}
				/*-- Update condition for benefit description --*/
				if (null != benefitLine.getLevelDesc()
						&& !benefitLine.getLevelDesc().equals("")) {
					if (null != benefitLineVO.getOverridedLvlDescValue()
							&& !benefitLineVO.getOverridedLvlDescValue()
									.equals("")
							&& !benefitLine.getLevelDesc().equals(
									benefitLineVO.getOverridedLvlDescValue())) {
						updated = true;
					}
				}

				if (updated)
					linesToBeModified.add(benefitLineVO);
				if (deleted)
					linesToBeDeleted.add(benefitLineVO);
			} else {
				benefitLineVO = (BenefitLineVO) newLines.get(key);
				if (null != benefitLineVO.getOverridedValue()
						&& !benefitLineVO.getOverridedValue().equals("")) {
					inserted = true;
				}
			}
			if (inserted)
				linesToBeInserted.add(benefitLineVO);

		}

		/*
		 * String tempFullStr = "";
		 * 
		 * if (sysLineIds != null && sysLineIds.size() > 0) { Set b =
		 * sysLineIds.keySet(); Iterator itSL = b.iterator(); String tempStr =
		 * ""; while (itSL.hasNext()) { List tempList = new ArrayList(); Object
		 * keyVal = itSL.next(); if (sysLineIds.get(keyVal) != null &&
		 * !sysLineIds.get(keyVal).equals(null)) { tempList = (ArrayList)
		 * sysLineIds.get(keyVal); if (tempList.size() > 0) { Iterator
		 * tempListItr = tempList.iterator(); while (tempListItr.hasNext()) {
		 * 
		 * if (tempStr == "") { tempStr = tempListItr.next().toString(); } else
		 * { tempStr = tempStr + "," + tempListItr.next(); } } if (tempFullStr
		 * == "") { tempFullStr = keyVal + ":" + tempStr; } else { tempFullStr =
		 * tempFullStr + "~" + keyVal + ":" + tempStr; } tempStr = ""; } } } }
		 */

		listMap.put("0", linesToBeModified);
		listMap.put("1", linesToBeInserted);
		listMap.put("2", linesToBeDeleted);
		// listMap.put("3", tempFullStr);

		Logger.logInfo(th.endPerfLogging());

		return listMap;
	}

	// For Tier Question Map to List Added by Krishnakumar as part of eWPD
	// Stabilization
	/*
	 * private Map generateListsFromMap(Map qstnLineIds) { Map listMap = new
	 * LinkedHashMap();
	 * 
	 * if (qstnLineIds != null && qstnLineIds.size() > 0) {
	 * 
	 * Set b = qstnLineIds.keySet(); Iterator itSL = b.iterator();
	 * 
	 * String tempFullStr = ""; String tempStr = ""; while (itSL.hasNext()) {
	 * List tempList = new ArrayList(); Object keyVal = itSL.next(); if
	 * (qstnLineIds.get(keyVal) != null &&
	 * !qstnLineIds.get(keyVal).equals(null)) { tempList = (ArrayList)
	 * qstnLineIds.get(keyVal); if (tempList.size() > 0) { Iterator tempListItr
	 * = tempList.iterator(); while (tempListItr.hasNext()) { if (tempStr == "")
	 * { tempStr = tempListItr.next().toString(); } else { tempStr = tempStr +
	 * "," + tempListItr.next(); } } if (tempFullStr == "") { tempFullStr =
	 * keyVal + ":" + tempStr; } else { tempFullStr = tempFullStr + "~" + keyVal
	 * + ":" + tempStr; } tempStr = ""; } } }
	 * 
	 * listMap.put("0", tempFullStr); } return listMap; }
	 */

	/**
	 * 
	 * @param totalList
	 * @param regularList
	 * @param testList
	 */
	private void getListsOfRegularAndTest(List totalList, List regularList,
			List testList) {

		for (int i = 0; i < totalList.size(); i++) {

			ContractLocateResult locResult = (ContractLocateResult) totalList
					.get(i);

			if (locResult.getTestIndicator().equals("N")) {
				regularList.add(locResult);
			} else {
				testList.add(locResult);
			}
		}

	}

	/**
	 * 
	 * @return
	 * @throws SevereException
	 */
	private boolean isDataFeedRunning() throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		DataFeedStatus dataFeedStatus = new DataFeedStatus();
		dataFeedStatus.setFeedType("EW");
		dataFeedStatus = builder.getDataFeed(dataFeedStatus);
		if ((dataFeedStatus != null) && (1 == dataFeedStatus.getFeedRunFlag())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param oldList
	 * @param dateNew
	 * @param bom
	 * @param request
	 * @param contract
	 * @param dateSegment
	 * @param response
	 * @param contractSysId
	 * @param comment
	 * @param builder
	 * @return
	 * @throws WPDException
	 */
	private CreateDateSegmentResponse getDSAction(List oldList, Date dateNew,
			BusinessObjectManager bom, CreateDateSegmentRequest request,
			Contract contract, DateSegment dateSegment,
			CreateDateSegmentResponse response, int contractSysId,
			Comment comment, ContractBusinessObjectBuilder builder)
			throws WPDException {
		try {
			int iFlag = -1;
			if (null != oldList && oldList.size() > 0) {

				for (int i = 0; i < oldList.size(); i++) {

					ContractLocateResult resultOld = (ContractLocateResult) oldList
							.get(i);
					Date dateOldStart = resultOld.getStartDate();
					if (dateNew.compareTo(dateOldStart) < 0
							|| dateNew.compareTo(dateOldStart) == 0) {
						iFlag = 1;
						break;
					}
					iFlag = 0;
					Date dateOldEnd = resultOld.getEndDate();
					ContractLocateResult resultNew = new ContractLocateResult();
					;
					if (dateNew.compareTo(dateOldEnd) < 0) {
						// this means , the present record is to be updated and
						// the
						// new entry shud be entered.
						// retrieving the complete data so that to update

						int sourceId = resultOld.getDateSegmentId();
						Map map = new HashMap();
						map.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						map.put(BusinessConstants.DATESEGMENT_ID, new Integer(
								resultOld.getDateSegmentId()));
						Contract contractRetrieved = (Contract) bom.retrieve(
								contract, request.getUser(), map);
						List dateSegList = contractRetrieved
								.getDateSegmentList();
						DateSegment dateSegmentNew = new DateSegment();
						if (null != dateSegList && dateSegList.size() != 0) {
							dateSegmentNew = (DateSegment) dateSegList.get(0);
							dateSegmentNew.setContractSysId(contractSysId);
							dateSegmentNew.setContractId(contract
									.getContractId());
							dateSegmentNew.setProductStatus("N");

						}
						if ((!"BUILDING".equals(dateSegmentNew.getStatus()))
								&& (!"CHECKED_OUT".equals(dateSegmentNew
										.getStatus()))) {
							dateSegmentNew = (DateSegment) bom.checkOut(
									dateSegmentNew, request.getUser());

						}
						// updating the present one

						dateSegmentNew.setExpiryDate(dateNew);// SETS END DATE
						// AS
						// NW
						// DATE
						dateSegmentNew.setContractSysId(contractSysId);
						dateSegmentNew.setContractId(contract.getContractId());
						bom.persist(dateSegmentNew, request.getUser(), false);

						// size? inserting the new ds
						dateSegment.setEffectiveDate(dateNew);
						dateSegment.setExpiryDate(dateOldEnd);
						dateSegment.setContractSysId(contractSysId);
						dateSegment.setBrandName(dateSegmentNew.getBrandName());
						dateSegment.setGroupSize(dateSegmentNew.getGroupSize());
						dateSegment.setProductCode(dateSegmentNew
								.getProductCode());
						dateSegment.setProductCodeDesc(dateSegmentNew
								.getProductCodeDesc());
						dateSegment.setContractId(contract.getContractId());
						dateSegment.setBaseDateSegmentSysId(dateSegmentNew
								.getBaseDateSegmentSysId());
						dateSegment.setCobAdjudicationIndicator(dateSegmentNew
								.getCobAdjudicationIndicator());
						dateSegment.setItsAdjudicationIndicator(dateSegmentNew
								.getItsAdjudicationIndicator());
						dateSegment
								.setMedicareAdjudicationIndicator(dateSegmentNew
										.getMedicareAdjudicationIndicator());
						if (resultOld.getTestIndicator().equals("Y")) {
							dateSegment.setDateSegmentType("Y");
						} else {
							dateSegment.setDateSegmentType("N");
						}
						dateSegment.setImageRWDAFlag(dateSegmentNew
								.getImageRWDAFlag());
						bom.persist(dateSegment, request.getUser(), true);

						// calls update again to insert the some more values

						// updating the present one
						// this id comes from the reference of insert operation
						int destinationId = dateSegment.getDateSegmentSysId();
						dateSegment.setDateSegmentSysId(dateSegment
								.getDateSegmentSysId());
						/*
						 * dateSegment.setEffectiveDate(dateNew);
						 * dateSegment.setExpiryDate(dateOldEnd);
						 * dateSegment.setContractSysId(contractSysId);
						 * dateSegment.setContractId(contract.getContractId());
						 * dateSegment
						 * .setGroupSize(dateSegmentNew.getGroupSize());
						 * dateSegment.setProductCode(dateSegmentNew
						 * .getProductCode());
						 */// DUPLICATE CODE !!
						dateSegment.setStandardPlanCode(dateSegmentNew
								.getStandardPlanCode());
						dateSegment.setBenefitPlan(dateSegmentNew
								.getBenefitPlan());
						dateSegment.setProductId(dateSegmentNew.getProductId());
						dateSegment.setCorporatePlanCode(dateSegmentNew
								.getCorporatePlanCode());
						dateSegment.setBrandName(dateSegmentNew.getBrandName());
						dateSegment.setFundingArrangement(dateSegmentNew
								.getFundingArrangement());

						dateSegment.setHeadQuartersState(dateSegmentNew
								.getHeadQuartersState());
						dateSegment.setHeadQuartersStateDesc(dateSegmentNew
								.getHeadQuartersStateDesc());
						dateSegment.setDateSegmentType(dateSegmentNew
								.getDateSegmentType());
						dateSegment.setRegulatoryAgency(dateSegmentNew
								.getRegulatoryAgency());
						dateSegment.setComplianceStatus(dateSegmentNew
								.getComplianceStatus());
						dateSegment.setProjectNameCode(dateSegmentNew
								.getProjectNameCode());
						dateSegment.setContractTermDate(dateSegmentNew
								.getContractTermDate());
						dateSegment.setMultiPlanIndicator(dateSegmentNew
								.getMultiPlanIndicator());
						dateSegment.setPerformanceGuarantee(dateSegmentNew
								.getPerformanceGuarantee());
						dateSegment.setSalesMarketDate(dateSegmentNew
								.getSalesMarketDate());
						dateSegment.setImageRWDAFlag(dateSegmentNew
								.getImageRWDAFlag());
						bom.persist(dateSegment, request.getUser(), false);

						// inserting the comment
						comment.setDateSegmentSysId(destinationId);
						bom.persist(comment, dateSegment, request.getUser(),
								true);

						// setting new datesegment id to the response
						response.setDateSegmentSysId(destinationId);
						// calls the copy
						// LO SUPP :: IM2578725 :: 08-June-2011 fix by
						// Krishnakumar
						if (!BusinessConstants.MSG_REPLACE_PRODUCT
								.equals(request.getContractVO()
										.getProductStatus())) {
							builder.copyDateSegments(dateSegment
									.getContractSysId(), sourceId,
									destinationId, request.isCopyLegacyNotes(),
									request.getUser(), true);
						}
						// END :: LO SUPP :: IM2578725 :: 08-June-2011 fix by
						// Krishnakumar
						HashMap params = new HashMap();
						params.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						params.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(destinationId));
						contract = (Contract) bom.retrieve(contract, request
								.getUser(), params);
						response.setContract(contract);

						break;
					} else {
						// the date entered is not inside this row. check next
						// row
						continue;
					}
				}
				if (iFlag == 1) {
					response.addMessage(new InformationalMessage(
							BusinessConstants.MSG_DATESEG_CREATED));
					response.setSuccess(false);
				} else {
					response.addMessage(new InformationalMessage(
							BusinessConstants.MSG_DATESEG_CREATED));
					response.setSuccess(true);

				}
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		}
		return response;
	}

	private void checkMarkedForDeletionNotes(int contractSysId, List messageList)
			throws SevereException {
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		List noteBOList = cob.checkMarkedForDeletionNotes(contractSysId);
		if (null != noteBOList && !noteBOList.isEmpty()) {
			StringBuffer buffer = new StringBuffer();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			NoteBO noteBO;
			for (java.util.Iterator noteBOListItr = noteBOList.iterator(); noteBOListItr
					.hasNext();) {
				noteBO = (NoteBO) noteBOListItr.next();
				buffer.append(dateFormat.format(noteBO.getCreatedTimestamp()));
				buffer.append(", ");
			}
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.deleteCharAt(buffer.length() - 1);
			InformationalMessage informationalMessage = new InformationalMessage(
					BusinessConstants.MSG_NOTE_EXPIRED);
			informationalMessage
					.setParameters(new String[] { buffer.toString() });
			messageList.add(informationalMessage);
		}
	}

	private void checkMarkedForDeletionProduct(int dsSysId, List messageList)
			throws SevereException {
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		ProductBO currentProduct = cob.retrieveCurrentProduct(dsSysId);
		if (currentProduct != null
				&& BusinessConstants.MSG_MARKED_FOR_DELETION
						.equals(currentProduct.getStatus())) {
			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_PROD_EXPIRED));
		}
	}

	/**
	 * 
	 * @param request
	 * @return response
	 * @throws ServiceException
	 */
	public WPDResponse execute(CheckCopyLegacyNoteRequest request)
			throws ServiceException, AdapterException {
		CheckCopyLegacyNoteResponse response = null;
		int countOfLegacyNotes = 0;
		try {
			response = (CheckCopyLegacyNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CHECK_COPY_LEGACY_NOTE_RESPONSE);
			// calling method to check whether the copy legacy note
			// functionality is on /off
			// if propsvalue value is true means copy legacy note is allowed.
			// else not allowed.
			boolean properyValue = ApplicationConfigManager
					.isContractLegacyNotesCopyAllowed();
			if (properyValue) {
				ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
				countOfLegacyNotes = builder.checkLegacyNotesExists(request
						.getDateSegmentId());
			}
			response.setCopyLegacyNoteIndcatorOn(properyValue);
			response.setLegacyNotesExist((countOfLegacyNotes > 0));

		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing CheckCopyLegacyNoteRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(CreateDateSegmentRequest request)
			throws ServiceException {
		CreateDateSegmentResponse response = null;
		try {
			response = (CreateDateSegmentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.DATE_SEGMENTS_CREATE_RESPONSE);
			BusinessObjectManager bom = getBusinessObjectManager();

			DateSegment dateSegment = new DateSegment();
			dateSegment.setComments(request.getComments());
			List messageList = new ArrayList();
			ContractVO contractVO = request.getContractVO();

			if ("Y".equals(request.getDsType())) {

				CreateDateSegmentResponse responseValidated = (CreateDateSegmentResponse) new ValidationServiceController()
						.execute(request);

				if (null != responseValidated
						&& responseValidated.isValid() == false) {
					responseValidated.setSuccess(false);
					return responseValidated;
				}
			}
			if (("SCHEDULED_FOR_TEST".equals(contractVO.getStatus()))
					&& (isDataFeedRunning())) {
				messageList.add(new InformationalMessage(
						BusinessConstants.DATAFEED_RUNNING));
				response.setSuccess(false);
				response.setMessages(messageList);
				return response;
			} else {
				Contract contract = new Contract();
				contract.setContractId(contractVO.getContractId());
				contract.setContractSysId(contractVO.getContractSysId());
				contract.setVersion(contractVO.getVersion());
				contract.setStatus(contractVO.getStatus());
				int contractSysId = contract.getContractSysId();
				Contract contractNew = new Contract();
				boolean isLockAcquired = true;
				if ("PUBLISHED".equals(contract.getStatus())
						|| "TEST_SUCCESSFUL".equals(contract.getStatus())
						|| "TEST_FAILED".equals(contract.getStatus())
						|| "REJECTED".equals(contract.getStatus())
						|| "CHECKED_IN".equals(contract.getStatus())
						|| "SCHEDULED_FOR_TEST".equals(contract.getStatus())
						|| "SCHEDULED_FOR_APPROVAL"
								.equals(contract.getStatus())) {
					contractNew = (Contract) bom.checkOut(contract, request
							.getUser());

					contractSysId = contractNew.getContractSysId();
					contract = contractNew;
				} else if ("BUILDING".equals(contract.getStatus())
						|| "CHECKED_OUT".equals(contract.getStatus())) {
					isLockAcquired = bom.lock(contract, request.getUser());
					if (isLockAcquired) { // to lock the date segments
						List dateList = retrieveCheckInDateSegments(contract
								.getContractSysId());
						Iterator dateitr = dateList.iterator();
						while (dateitr.hasNext()) {
							DateSegment lockDateSegment = (DateSegment) dateitr
									.next();
							bom.lock(lockDateSegment, request.getUser());
						}
					}
				}
				if (!isLockAcquired) {
					response.setLockAcquired(isLockAcquired);
					InformationalMessage inform = new InformationalMessage(
							BusinessConstants.CONTRACT_LOCKED);
					inform.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(inform);
					response.setMessages(messageList);
					return response;
				}
				Date dateNew = contractVO.getDateEntered();
				String type = request.getDsType();

				DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
				LocateResults locateResults = null;

				locateCriteria.setContractSysId(contractSysId);
				// gets the ds's
				locateResults = bom.locate(locateCriteria, request.getUser());
				ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
				Comment comment = new Comment();
				comment.setCommentText(request.getComments());
				if (locateResults.getLocateResults().size() >= 0) {

					List oldList = locateResults.getLocateResults();

					List regularList = null;
					List testList = null;
					if (null != oldList) {
						regularList = new ArrayList(oldList.size());
						testList = new ArrayList(oldList.size());
						getListsOfRegularAndTest(oldList, regularList, testList);
					}

					if (type.equals("Y")) {

						if ((null != testList) && (testList.size() != 0)) {
							response = getDSAction(testList, dateNew, bom,
									request, contract, dateSegment, response,
									contractSysId, comment, builder);
							if ("PUBLISHED".equals(contractVO.getStatus())
									|| "TEST_SUCCESSFUL".equals(contractVO
											.getStatus())
									|| "TEST_FAILED".equals(contractVO
											.getStatus())
									|| "REJECTED"
											.equals(contractVO.getStatus())
									|| "CHECKED_IN".equals(contractVO
											.getStatus())
									|| "SCHEDULED_FOR_TEST".equals(contractVO
											.getStatus())
									|| "SCHEDULED_FOR_APPROVAL"
											.equals(contractVO.getStatus())) {
								contractNew = contractCheckoutReplace(
										contractNew, contractVO, request
												.getUser());

								// checks existing product/note expired or not
								if ("N".equals(type)) {
									ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
									ProductBO currentProduct = cob
											.retrieveCurrentProduct(contractNew
													.getBaseDateSegmentSysId());
									if (currentProduct != null
											&& BusinessConstants.MSG_MARKED_FOR_DELETION
													.equals(currentProduct
															.getStatus())) {
										messageList
												.add(new InformationalMessage(
														BusinessConstants.MSG_PROD_EXPIRED));
									}
									DateSegmentLocateCriteria locateCriteriaNotes = new DateSegmentLocateCriteria();
									LocateResults locateResultsNotes = null;

									locateCriteriaNotes
											.setContractSysId(contractSysId);
									locateResultsNotes = bom.locate(
											locateCriteriaNotes, request
													.getUser());
									int dateSegmentCount = locateResultsNotes
											.getLocateResults().size();
									if (dateSegmentCount >= 0) {
										for (int i = 0; i < dateSegmentCount; i++) {
											ContractLocateResult contractLocateResult = (ContractLocateResult) locateResultsNotes
													.getLocateResults().get(i);
											if ("N".equals(contractLocateResult
													.getTestIndicator())) {
												NoteBO currentNote = cob
														.retrieveCurrentNote(contractLocateResult
																.getDateSegmentId());
												if (currentNote != null
														&& BusinessConstants.MSG_MARKED_FOR_DELETION
																.equals(currentNote
																		.getStatus())) {
													InformationalMessage informationalMessage = new InformationalMessage(
															BusinessConstants.MSG_NOTE_EXPIRED);
													informationalMessage
															.setParameters(new String[] { WPDStringUtil
																	.getStringDate(contractLocateResult
																			.getStartDate()) });
													messageList
															.add(informationalMessage);

												}
											}
										}
									}
								}
								addMessagesToResponse(response, messageList);
								response.setContract(contractNew);
							}
							return response;

						} else {
							// new Test entry. here i take get(0), just taking a
							// ds
							// id , wil be of type = regular
							ContractLocateResult resultOld = (ContractLocateResult) oldList
									.get(0);
							int sourceId = resultOld.getDateSegmentId();
							Map map = new HashMap();
							map.put(BusinessConstants.ACTION,
									BusinessConstants.RETREIVE_SPECIFIC);
							map.put(BusinessConstants.DATESEGMENT_ID,
									new Integer(resultOld.getDateSegmentId()));
							Contract contractRetrieved = (Contract) bom
									.retrieve(contract, request.getUser(), map);
							List dateSegList = contractRetrieved
									.getDateSegmentList();
							DateSegment dateSegmentNew = new DateSegment();
							if (null != dateSegList) {
								dateSegmentNew = (DateSegment) dateSegList
										.get(0);

							}
							// inserts new entry
							dateSegment.setEffectiveDate(dateNew);
							dateSegment.setExpiryDate(WPDStringUtil
									.getDateFromString("12/31/9999"));
							dateSegment.setContractSysId(contractSysId);
							// dateSegment.setBrandName(dateSegmentNew.getBrandName());
							dateSegment.setGroupSize(dateSegmentNew
									.getGroupSize());
							dateSegment.setDateSegmentSysId(dateSegmentNew
									.getDateSegmentSysId());
							dateSegment.setBaseDateSegmentSysId(dateSegmentNew
									.getBaseDateSegmentSysId());
							// dateSegment.setProductCode(dateSegmentNew.getProductCode());
							dateSegment.setDateSegmentType("Y");
							dateSegment.setContractSysId(contractSysId);
							dateSegment.setContractId(contractRetrieved
									.getContractId());
							bom.persist(dateSegment, request.getUser(), true);

							// updating the present one with more values
							// this id comes from the reference of insert
							// operation
							int destinationId = dateSegment
									.getDateSegmentSysId();
							dateSegment.setDateSegmentSysId(dateSegment
									.getDateSegmentSysId());
							dateSegment.setEffectiveDate(dateNew);
							dateSegment.setExpiryDate(WPDStringUtil
									.getDateFromString("12/31/9999"));
							dateSegment.setContractSysId(contractSysId);
							dateSegment.setGroupSize(dateSegmentNew
									.getGroupSize());
							dateSegment.setDateSegmentType(dateSegmentNew
									.getDateSegmentType());

							/*
							 * dateSegment.setProductCode(dateSegmentNew.getProductCode
							 * ());
							 * dateSegment.setStandardPlanCode(dateSegmentNew
							 * .getStandardPlanCode());
							 * dateSegment.setBenefitPlan
							 * (dateSegmentNew.getBenefitPlan());
							 * dateSegment.setProductId
							 * (dateSegmentNew.getProductId());
							 * dateSegment.setCorporatePlanCode
							 * (dateSegmentNew.getCorporatePlanCode());
							 * dateSegment
							 * .setBrandName(dateSegmentNew.getBrandName());
							 * dateSegment.setFundingArrangement(dateSegmentNew.
							 * getFundingArrangement());
							 */
							dateSegment.setDateSegmentType("Y");
							bom.persist(dateSegment, request.getUser(), false);

							// inserting the comment
							comment.setDateSegmentSysId(destinationId);
							bom.persist(comment, dateSegment,
									request.getUser(), true);

							// setting new datesegment id to the response
							response.setDateSegmentSysId(destinationId);
							// calls the copy
							// builder.copyDateSegments(sourceId,
							// destinationId,request.getUser());

							HashMap params = new HashMap();
							params.put(BusinessConstants.ACTION,
									BusinessConstants.RETREIVE_SPECIFIC);
							params.put(BusinessConstants.DATESEGMENT_ID,
									new Integer(destinationId));
							contract = (Contract) bom.retrieve(contract,
									request.getUser(), params);

							// response.setCount( 0 );

							response.setContract(contract);
							response.addMessage(new InformationalMessage(
									BusinessConstants.MSG_DATESEG_CREATED));
							response.setSuccess(true);

							return response;

						}
					} else if (type.equals("N")) {
						// regular entry
						if (regularList.size() != 0) {
							response = getDSAction(regularList, dateNew, bom,
									request, contract, dateSegment, response,
									contractSysId, comment, builder);

							if ("PUBLISHED".equals(contractVO.getStatus())
									|| "TEST_SUCCESSFUL".equals(contractVO
											.getStatus())
									|| "TEST_FAILED".equals(contractVO
											.getStatus())
									|| "REJECTED"
											.equals(contractVO.getStatus())
									|| "CHECKED_IN".equals(contractVO
											.getStatus())
									|| "SCHEDULED_FOR_TEST".equals(contractVO
											.getStatus())
									|| "SCHEDULED_FOR_APPROVAL"
											.equals(contractVO.getStatus())) {
								contractNew = contractCheckoutReplace(
										contractNew, contractVO, request
												.getUser());

								// checks existing product/note expired or not
								if ("N".equals(type)) {
									ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
									ProductBO currentProduct = cob
											.retrieveCurrentProduct(contractNew
													.getBaseDateSegmentSysId());
									if (currentProduct != null
											&& BusinessConstants.MSG_MARKED_FOR_DELETION
													.equals(currentProduct
															.getStatus())) {
										messageList
												.add(new InformationalMessage(
														BusinessConstants.MSG_PROD_EXPIRED));
									}
									DateSegmentLocateCriteria locateCriteriaNotes = new DateSegmentLocateCriteria();
									LocateResults locateResultsNotes = null;

									locateCriteriaNotes
											.setContractSysId(contractSysId);
									locateResultsNotes = bom.locate(
											locateCriteriaNotes, request
													.getUser());
									int dateSegmentCount = locateResultsNotes
											.getLocateResults().size();
									if (dateSegmentCount >= 0) {
										for (int i = 0; i < dateSegmentCount; i++) {
											ContractLocateResult contractLocateResult = (ContractLocateResult) locateResultsNotes
													.getLocateResults().get(i);
											if ("N".equals(contractLocateResult
													.getTestIndicator())) {
												NoteBO currentNote = cob
														.retrieveCurrentNote(contractLocateResult
																.getDateSegmentId());
												if (currentNote != null
														&& BusinessConstants.MSG_MARKED_FOR_DELETION
																.equals(currentNote
																		.getStatus())) {
													InformationalMessage informationalMessage = new InformationalMessage(
															BusinessConstants.MSG_NOTE_EXPIRED);
													informationalMessage
															.setParameters(new String[] { WPDStringUtil
																	.getStringDate(contractLocateResult
																			.getStartDate()) });
													messageList
															.add(informationalMessage);

												}
											}
										}
									}
								}
								addMessagesToResponse(response, messageList);

								response.setContract(contractNew);
							}

							return response;
						} else {
							// new Regular entry. here i take get(0), just
							// taking a
							// ds id , wil be of type = test
							ContractLocateResult resultOld = (ContractLocateResult) oldList
									.get(0);
							int sourceId = resultOld.getDateSegmentId();
							Map map = new HashMap();
							map.put(BusinessConstants.ACTION,
									BusinessConstants.RETREIVE_SPECIFIC);
							map.put(BusinessConstants.DATESEGMENT_ID,
									new Integer(resultOld.getDateSegmentId()));
							Contract contractRetrieved = (Contract) bom
									.retrieve(contract, request.getUser(), map);
							List dateSegList = contractRetrieved
									.getDateSegmentList();
							DateSegment dateSegmentNew = new DateSegment();
							if (null != dateSegList) {
								dateSegmentNew = (DateSegment) dateSegList
										.get(0);

							}
							// inserts new entry
							dateSegment.setEffectiveDate(dateNew);
							dateSegment.setExpiryDate(WPDStringUtil
									.getDateFromString("12/31/9999"));
							dateSegment.setContractSysId(contractSysId);
							// dateSegment.setBrandName(dateSegmentNew.getBrandName());
							dateSegment.setGroupSize(dateSegmentNew
									.getGroupSize());
							dateSegment.setBaseDateSegmentSysId(dateSegmentNew
									.getBaseDateSegmentSysId());
							// dateSegment.setProductCode(dateSegmentNew.getProductCode());
							dateSegment.setDateSegmentType("N");
							dateSegment.setContractId(contractRetrieved
									.getContractId());
							dateSegment.setContractSysId(contractSysId);
							bom.persist(dateSegment, request.getUser(), true);

							// updating the present one with more values
							// this id comes from the reference of insert
							// operation
							int destinationId = dateSegment
									.getDateSegmentSysId();
							dateSegment.setDateSegmentSysId(dateSegment
									.getDateSegmentSysId());
							dateSegment.setEffectiveDate(dateNew);
							dateSegment.setExpiryDate(WPDStringUtil
									.getDateFromString("12/31/9999"));
							dateSegment.setContractSysId(contractSysId);
							dateSegment.setGroupSize(dateSegmentNew
									.getGroupSize());
							/*
							 * dateSegment.setProductCode(dateSegmentNew.getProductCode
							 * ());
							 * dateSegment.setStandardPlanCode(dateSegmentNew
							 * .getStandardPlanCode());
							 * dateSegment.setBenefitPlan
							 * (dateSegmentNew.getBenefitPlan());
							 * dateSegment.setProductId
							 * (dateSegmentNew.getProductId());
							 * dateSegment.setCorporatePlanCode
							 * (dateSegmentNew.getCorporatePlanCode());
							 * dateSegment
							 * .setBrandName(dateSegmentNew.getBrandName());
							 * dateSegment.setFundingArrangement(dateSegmentNew.
							 * getFundingArrangement());
							 */
							dateSegment.setDateSegmentType("N");
							bom.persist(dateSegment, request.getUser(), false);

							// inserting the comment
							comment.setDateSegmentSysId(destinationId);
							bom.persist(comment, dateSegment,
									request.getUser(), true);

							// setting new datesegment id to the response
							response.setDateSegmentSysId(destinationId);
							// calls the copy
							// builder.copyDateSegments(sourceId,
							// destinationId,request.getUser());

							HashMap params = new HashMap();
							params.put(BusinessConstants.ACTION,
									BusinessConstants.RETREIVE_SPECIFIC);
							params.put(BusinessConstants.DATESEGMENT_ID,
									new Integer(destinationId));
							contract = (Contract) bom.retrieve(contract,
									request.getUser(), params);

							// response.setCount( 0 );

							response.setContract(contract);
							response.addMessage(new InformationalMessage(
									BusinessConstants.MSG_DATESEG_CREATED));
							response.setSuccess(true);

							return response;

						}
					}
				}
			}
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing CreateDateSegmentRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SaveContractBasicInfoRequest request)
			throws ServiceException {
		// Calling validation Service.
		// request.setCopyLegacyNotes(false);
		SaveContractBasicInfoResponse response = (SaveContractBasicInfoResponse) new ValidationServiceController()
				.execute(request);
		ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
		ContractIDSystemPool contractIDSystemPool = null;
		
		ContractIDReservePool contractIDReservePool = null;
		try {
			contractIDSystemPool = contractIDRepositoryAdmin
					.getContractSystemPool();
			contractIDReservePool = contractIDRepositoryAdmin
					.getContractReservePool();
		} catch (ContractIDPoolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			throw new ServiceException("Adapter exception", null, e2);
		}
		// Returns if validation fails.
		if (!response.isValid()) {
			return response;
		}
		boolean isReserved = false;
		int action = request.getAction();

		ContractVO contractVO = request.getContractVO();
		BusinessObjectManager bom = getBusinessObjectManager();
		Contract contract = null;
		User user = request.getUser();
		List messageList = new ArrayList();
		List dateSegmentListLC = null;
		InformationalMessage informationalMessage = null;
		try {
			switch (action) {
			case SaveContractBasicInfoRequest.CREATE_CONTRACT:
				contract = new Contract();
				ContractIDPoolRecord contractIdInfo = null;
				// SystemContractId contractIdInfo = null;
				setBasicInfoToContract(contractVO, contract);
				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					String contractId = generateContractIdFromGroup(request);
					if (validateContractId(contractId)) {
						messageList.add(new ErrorMessage(
								BusinessConstants.MSG_CONTRACTID_EXISTS));
						addMessagesToResponse(response, messageList);
						return response;
					}
					contract.setContractId(contractId);
					contract
							.setStateCode(contractVO.getHeadQuartersStateCode());
				} else {
					if (null == contract.getContractId()
							|| "".equals(contract.getContractId())) {
						// contractIdInfo = generateContractId();
						// contract.setContractId(contractIdInfo.getContractId());

						contractIdInfo = contractIDSystemPool
								.getContractIdSystemPool();

						if (null == contractIdInfo) {
							messageList
									.add(new ErrorMessage(
											BusinessConstants.MSG_CONTRACT_SYSTEM_POOL_EXPIRED));
							addMessagesToResponse(response, messageList);
							return response;

						} else
							// contract.setContractId(contractIdInfo
							// .getContractId());
							contract.setContractId(contractIdInfo
									.getContractId());
					} else {
						isReserved = true;
					}
				}

				if (isReserved) {
					// update status of reserve pool
					ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
					List newList = null;
					contractIdInfo = new ContractIDPoolRecord();
					contractIdInfo.setContractId(contract.getContractId());
					try {
						newList = contractIDSystemPool.locateContractIds(
								contractIdInfo, 1, true);
					} catch (ContractIDPoolException e1) {
						List errorParams = new ArrayList();
						throw new SevereException("Adapter Exception occured",
								errorParams, e1);
					}
					contractIDReservePoolRecord = (ContractIDReservePoolRecord) newList
							.get(0);
					contractIDReservePoolRecord.setContractId(contract
							.getContractId());

					String reservePoolStatus = contractIDReservePoolRecord
							.getReservePoolStatus();
					if (null != reservePoolStatus) {
						if (reservePoolStatus.trim().equals("N")) {
							contractIDReservePoolRecord
									.setReservePoolStatus("U");
							contractIDReservePoolRecord
									.setLastUpdatedUser(request.getUser()
											.getUserId());
							contractIDReservePoolRecord.setSystemDomain("ETAB");
							contractIDReservePool
									.updateReserveContract(contractIDReservePoolRecord);
						}
					} else {
						InformationalMessage message = new InformationalMessage(
								WebConstants.CONTRACT_IN_USE);
						messageList.add(message);
						addMessagesToResponse(response, messageList);
						return response;
					}

				} else {
					if (!(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE
							.equalsIgnoreCase(contract.getContractType()))) {

						ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
						List systemPoolList = contractIDSystemPool
								.locateContractIds(contractIdInfo, 1, true);
						contractIDReservePoolRecord = (ContractIDReservePoolRecord) systemPoolList
								.get(0);
						if (contractIDReservePoolRecord.getSystemPoolStatus()
								.trim().equals("N")) {
							String contractId = contractIdInfo.getContractId();
							contractIdInfo = new ContractIDPoolRecord();
							contractIdInfo.setContractId(contractId);

							contractIdInfo.setCreatedUser(request.getUser()
									.getUserId());
							contractIdInfo.setSystemPoolStatus("U");
							contractIdInfo.setSystemDomain("ETAB");
							contractIDReservePool.markAsUnused(contractIdInfo);
						} else {
							InformationalMessage message = new InformationalMessage(
									WebConstants.CONTRACT_IN_USE);
							messageList.add(message);
							addMessagesToResponse(response, messageList);
							return response;
						}

					}
				}
				List systemPoolList_user = contractIDSystemPool
						.locateContractIds(contractIdInfo, 1, true);
				ContractIDPoolRecord contractIDPool_user = new ContractIDReservePoolRecord();
				contractIDPool_user = (ContractIDReservePoolRecord) systemPoolList_user
						.get(0);

				if (null != (contractIDPool_user.getSystemDomain())
						&& contractIDPool_user.getSystemDomain().equals("ETAB")) {
				} else {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}

				try {
					bom.persist(contract, user, true);
				} catch (LockedBySameUserException se) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				} catch (LockedByAnotherUserException ae) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}
				DateSegment dateSegment = new DateSegment();
				dateSegment.setEffectiveDate(contractVO.getEffectiveDate());
				dateSegment.setExpiryDate(contractVO.getExpiryDate());
				dateSegment.setGroupSize(contractVO.getGroupSize());
				dateSegment.setGroupSizeDesc(contractVO.getGroupSizeDesc());
				dateSegment.setBaseDateSegmentSysId(contractVO
						.getBaseDateSegmentSysId());
				dateSegment.setDateSegmentType("N");
				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					dateSegment.setProductId(contractVO.getProductSysId());
				}
				dateSegment.setContractId(contract.getContractId());
				dateSegment.setContractSysId(contract.getContractSysId());
				dateSegment.setContractStatusBo(contractVO
						.getContractStatusBo());
				if (null != dateSegment.getContractStatusBo()) {
					dateSegment.getContractStatusBo().setContractId(
							contract.getContractId());
					dateSegment.getContractStatusBo().setCreatedUserId(
							user.getUserId());
					dateSegment.getContractStatusBo().setLastChangedUserId(
							user.getUserId());
				}

				if (BusinessUtil.isReadyForImageRewrite(contract)) {
					dateSegment.setImageRWDAFlag("Y");
				} else {
					dateSegment.setImageRWDAFlag("N");
				}
				bom.persist(dateSegment, user, true);

				// to copy product components to the mandate contract
				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
					if (dateSegment.getProductId() != 0)
						contractBuilder.copyProductComponents(dateSegment);
				}

				Map param = new HashMap();
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_CONTRACT_CREATE_SUCCESS));

				if (BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contract
						.getContractType())) {
					Contract contractcopy;
					int basedateSegmentId = contract.getBaseDateSegmentSysId();
					param.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);
					param.put(BusinessConstants.DATESEGMENT_ID, new Integer(
							basedateSegmentId));
					contractcopy = (Contract) bom.retrieve(contract, user,
							param);
					DateSegment retrieveDateSegnment = (DateSegment) contractcopy
							.getDateSegmentList().get(0);

					retrieveDateSegnment.setDateSegmentSysId(dateSegment
							.getDateSegmentSysId());
					retrieveDateSegnment.setContractSysId(dateSegment
							.getContractSysId());
					retrieveDateSegnment.setEffectiveDate(dateSegment
							.getEffectiveDate());
					retrieveDateSegnment.setExpiryDate(dateSegment
							.getExpiryDate());
					retrieveDateSegnment.setVersion(dateSegment.getVersion());
					retrieveDateSegnment.setStatus(dateSegment.getStatus());
					retrieveDateSegnment.setContractId(contractcopy
							.getContractId());

					bom.persist(retrieveDateSegnment, user, false);
					HashMap map = new HashMap();
					map.put(BusinessConstants.ACTION, "copyDateSegment");
					ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
					builder.copyDateSegments(dateSegment.getContractSysId(),
							basedateSegmentId, dateSegment
									.getDateSegmentSysId(), request
									.isCopyLegacyNotes(), user, false);
				} else if (BusinessConstants.STANDARD_TYPE.equals(contract
						.getContractType())
						&& BusinessConstants.VALUE_ZERO != contract
								.getBaseDateSegmentSysId()) {
					Contract contractcopy;
					int basedateSegmentId = contract.getBaseDateSegmentSysId();
					param.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);
					param.put(BusinessConstants.DATESEGMENT_ID, new Integer(
							basedateSegmentId));
					contractcopy = (Contract) bom.retrieve(contract, user,
							param);
					DateSegment retrieveDateSegnment = (DateSegment) contractcopy
							.getDateSegmentList().get(0);

					retrieveDateSegnment.setDateSegmentSysId(dateSegment
							.getDateSegmentSysId());
					retrieveDateSegnment.setContractSysId(dateSegment
							.getContractSysId());
					retrieveDateSegnment.setEffectiveDate(dateSegment
							.getEffectiveDate());
					retrieveDateSegnment.setExpiryDate(dateSegment
							.getExpiryDate());
					retrieveDateSegnment.setVersion(dateSegment.getVersion());
					retrieveDateSegnment.setStatus(dateSegment.getStatus());
					retrieveDateSegnment.setContractId(contractcopy
							.getContractId());

					bom.persist(retrieveDateSegnment, user, false);
					HashMap map = new HashMap();
					map.put(BusinessConstants.ACTION, "copyDateSegment");
					ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
					builder.copyDateSegments(dateSegment.getContractSysId(),
							basedateSegmentId, dateSegment
									.getDateSegmentSysId(), request
									.isCopyLegacyNotes(), user, false);
				}

				param.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				param.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						dateSegment.getDateSegmentSysId()));

				contract = (Contract) bom.retrieve(contract, request.getUser(),
						param);

				response.setSuccess(true);
				response.setContract((Contract) bom.retrieve(contract, user));
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						BusinessConstants.ENTITY_TYPE_CONTRACT, contract
								.getParentSysId()));
				Logger.logInfo("Returning  from execute method for request="
						+ request.getClass().getName());

				break;

			case SaveContractBasicInfoRequest.UPDATE_CONTRACT:
				contract = new Contract();
				setBasicInfoToContract(contractVO, contract);
				Map params = new HashMap();
				Contract retrievedContract = new Contract();
				params.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						contractVO.getDateSegmentSysId()));
				retrievedContract = (Contract) bom.retrieve(contract, request
						.getUser(), params);
				DateSegment retrievedDateSegment = (DateSegment) retrievedContract
						.getDateSegmentList().get(0);

				retrievedDateSegment.setGroupSize(contractVO.getGroupSize());
				retrievedDateSegment.setContractStatusBo(contractVO
						.getContractStatusBo());
				contract.getDateSegmentList().set(0, retrievedDateSegment);
				bom.persist(contract, user, false);
				params.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						contractVO.getDateSegmentSysId()));
				contract = (Contract) bom.retrieve(contract, request.getUser(),
						params);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_CONTRACT_BASICINFO_SAVE_SUCCESS));
				response.setSuccess(true);
				response.setContract((Contract) bom.retrieve(contract, user));
				response.getContract().setProviderSpecialityCodeList(
						retrievedContract.getProviderSpecialityCodeList());
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						BusinessConstants.ENTITY_TYPE_CONTRACT, contract
								.getParentSysId()));
				Logger.logInfo("Returning  from execute method for request="
						+ request.getClass().getName());
				break;
			case SaveContractBasicInfoRequest.CHECKIN_CONTRACT:
			case SaveContractBasicInfoRequest.UPDATE_CHECKIN_CONTRACT:
				contract = new ContractBusinessServiceHelper()
						.updateContract(request);
				if (null != contract) {
					request.setContract(contract);
					messageList
							.add(new InformationalMessage(
									BusinessConstants.MSG_CONTRACT_BASICINFO_SAVE_SUCCESS));
				}

				GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) new ContractBusinessServiceHelper()
						.validateGeneralBenefitAdminMethod(request);
				if (null != gnrlBenefitAdminMethodResponse.getMessages()
						&& gnrlBenefitAdminMethodResponse.getMessages().size() > 0) {
					List errorMessages = gnrlBenefitAdminMethodResponse
							.getMessages();
					messageList.add((ErrorMessage) errorMessages.get(0));

					break;
				}

				RuleTypeValidateOutputVO ruleTypeValidateOutput = new RuleTypeValidateOutputVO();
				RuleTypeValidationBuilder ruleTypeValidationBuilder = new RuleTypeValidationBuilder();

				RefDataDomainValidationRequest refDataDomainValidationRequest = validateReferenceData(
						contract, request);
				RefDataDomainValidationResponse refDataDomainValidationResponse = new RefDataDomainValidationResponse();
				refDataDomainValidationResponse = (RefDataDomainValidationResponse) new ValidationServiceController()
						.execute(refDataDomainValidationRequest);

				if (!refDataDomainValidationResponse.isSuccess()) {
					ErrorMessage errorMessage = new ErrorMessage(
							WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
					errorMessage
							.setParameters(new String[] { " "
									+ refDataDomainValidationResponse
											.getErrorMessage() });
					messageList.add(errorMessage);
					break;
				}
				if (request.isChechIn()) {
					boolean wsResponseFlag = false;
					boolean carOutResponseFlag = false;
					boolean wsErrors =false;
					boolean wsResponseInterruptFlag =false;
					triggerAdminMethodValidations(contract
							.getCheckInDateSegmentList(), request.getUser()
							.getUserId());

					AdminMethodValidationStatusResponse statusResponse = new ContractBusinessServiceHelper()
							.validateAdminMethodStatus(request);
					
					
					
					
					if (null != statusResponse) {
						if (statusResponse.getStatus() == AdminMethodValidationStatusResponse.VALIDATION_WAITING) {
							response
									.setCondition(SaveProductResponse.VALIDATION_WAIT);
						} else if (statusResponse.getStatus() == AdminMethodValidationStatusResponse.VALIDATION_ERRORS) {
							response
									.setCondition(SaveProductResponse.VALIDATION_RESULTS);
						} else if (statusResponse.getStatus() == 0) {
							response
									.addMessage(new ErrorMessage(
											"admin.method.entity.checkin.validation.status.check.failed"));
						} else if (statusResponse.getStatus() == AdminMethodValidationStatusResponse.VALIDATION_SUCCESS) {
							
							AdminMethodSPSValidationRequest adminMethodSPSValidationRequest = new AdminMethodSPSValidationRequest();
							adminMethodSPSValidationRequest
									.setContractSysId(request
											.getContractKeyObject()
											.getContractSysId());
							AdminMethodSPSValidationResponse adminMethodSPSValidationResponse = (AdminMethodSPSValidationResponse) new BusinessServiceController()
									.execute(adminMethodSPSValidationRequest);

							if (adminMethodSPSValidationResponse.getStatus() == adminMethodSPSValidationResponse.VALIDATION_FAIL) {
								ErrorMessage errorMessage = new ErrorMessage(
										BusinessConstants.CONTRACT_SPS_VALIDATION_ERROR);
								errorMessage
										.setLink(BusinessConstants.CONTRACT_SPS_VALIDATION_LINK);
								response.addMessage(errorMessage);
								response.setCondition(SaveProductResponse.SPS_VALIDATION_ERROR);
							} else if (adminMethodSPSValidationResponse
									.getStatus() == adminMethodSPSValidationResponse.VALIDATION_SUCCESS) {
								if (this.validateNotes(contract)) { // uncoded
									// notes
									// validation
									List chekinDateSegmentList = request
											.getContract()
											.getCheckInDateSegmentList();
									//SSCR 16332 -Start
									if(request.isEBXWS() && request.isChechIn()){
										
										ContractWebServiceVO contractWebServiceVO = new ContractWebServiceVO();
										contractWebServiceVO.setContractId(request.getContractKeyObject().getContractId());
										contractWebServiceVO.setEffectiveDate(request.getContractKeyObject().getEffectiveDate());
										contractWebServiceVO.setVersion(request.getContractKeyObject().getVersion());
										contractWebServiceVO.setSystem("EWPD");
										ContractWebServiceResponse contractWebServiceResponse = new ContractBusinessServiceHelper().invokeEBXWSResponse(contractWebServiceVO);
										if(null != contractWebServiceResponse){
											if(null != contractWebServiceResponse.getContractWSErrorList() && !contractWebServiceResponse.getContractWSErrorList().isEmpty()){
												wsResponseFlag = true;
												response.setContractWSErrorList(contractWebServiceResponse.getContractWSErrorList());
												List<EbxWebSerDisplayVO> wSErrorDisplayList =DomainUtil.processListDisplayScreen(contractWebServiceResponse.getContractWSErrorList());
												response.setWSErrorDisplayList(wSErrorDisplayList);
											}else if(null!=contractWebServiceResponse.getWsProcessError()&& contractWebServiceResponse.getWsProcessError().equals(BusinessConstants.DO_EBX_WEBSERVICE_PROCESS_ERROR)){
												wsResponseInterruptFlag = true;
												response.setWsProcessError(contractWebServiceResponse.getWsProcessError());
											}
										}
									}else if(null != request.getEbxAndCarvErrorsByPassCmts() && request.getEbxAndCarvErrorsByPassCmts().equalsIgnoreCase("confirm")){
										Comment comment = new Comment();
										DateSegment dateSegment_cmt = getDateSegment(request);
										int datesegmentid = request.getContractKeyObject().getDateSegmentId();
										//comment.setCommentText("MTM validation errors were bypassed");
										comment.setCommentText("MTM and Carved out Warning Messages are bypassed");
										comment.setDateSegmentSysId(datesegmentid);
										bom.persist(comment, dateSegment_cmt, request.getUser(), true);
									}
									
									//SSCR 16332 -End
									//SSCR 17571 -Tab implementation
									if(request.isCarvConfirm() && response.getCarvedoutMap()!=null && !response.getCarvedoutMap().isEmpty()){
										carOutResponseFlag = true;
									}//SSCR 17571 -End
									if (null != chekinDateSegmentList
											&& !chekinDateSegmentList.isEmpty()) {

										ruleTypeValidateOutput = ruleTypeValidationBuilder
												.validateDateSegmentRuleTypes(chekinDateSegmentList);

										if (null == ruleTypeValidateOutput
												.getErrorMessages()
												|| ruleTypeValidateOutput
														.getErrorMessages()
														.isEmpty()) {
											
											if(wsResponseFlag && request.isEBXWS()){
												// SSCR 16332 EBX error validation flag
												wsErrors =true;
												response.setCondition(SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS);
												
											}else if(wsResponseInterruptFlag){
												wsErrors =true;
												response.setCondition(SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS_FAILURE);	
											}
											if(carOutResponseFlag && request.isCarvConfirm()){
												wsErrors = true; // it will bypass the checkin
												response.setCarvedOutCondition(SaveProductResponse.DO_CARVEDOUT_PROCESS);
											} 
											if(!wsErrors){  //By pass the checkin if ebx error
											/*
											 * RuleTypeValidationBuilder
											 * .updateBlzIndicatorInLatestVersion
											 * (
											 * ruleTypeValidateOutput.getDateSegment
											 * (), request.getUser());
											 */
											Iterator itr = ruleTypeValidateOutput
													.getDateSegment()
													.iterator();
											List dateSegmentIdList = new ArrayList();
											while (itr.hasNext()) {
												DateSegment datesegment = (DateSegment) itr
														.next();
												bom.checkIn(datesegment,
														request.getUser());
												dateSegmentIdList
														.add(new Integer(
																datesegment
																		.getDateSegmentSysId()));
											}
											bom.checkIn(contract, request
													.getUser());
											new ContractBusinessObjectBuilder()
													.deleteContractBenefitAffectedSPS(
															dateSegmentIdList,
															this.getAudit(user));
											informationalMessage = new InformationalMessage(
													BusinessConstants.MSG_CONTRACT_CHECKIN_SUCCESS);
											informationalMessage
													.setParameters(new String[] { contract
															.getContractId() });
											messageList
													.add(informationalMessage);
											response.setSuccess(true);
											response.setContract(contract);
										
										}//wsErrors
										} else { // errorMessages.is not
											// Empty
											response.setSuccess(false);
											messageList
													.addAll(ruleTypeValidateOutput
															.getErrorMessages());
										} // end of errorMessages.is not Empty

									} else { // if chekinDateSegmentList is
										// empty
										
										if(!wsErrors){//By pass the checkin if ebx error
											bom.checkIn(contract, request.getUser());
	
											informationalMessage = new InformationalMessage(
													BusinessConstants.MSG_CONTRACT_CHECKIN_SUCCESS);
											informationalMessage
													.setParameters(new String[] { contract
															.getContractId() });
											messageList.add(informationalMessage);
											response.setSuccess(true);
											response.setContract(contract);
										}//wsErrors
									}

								} else {
									response.setIfUncodedLineNotesExist(true);
									response.setSuccess(false);
								}
							}
						}
					}

				}
				break;
			case SaveContractBasicInfoRequest.SEND_TO_TEST:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
					if ("testDateSegment".equals(contractVO
							.getTestDateSegment())) {
						dateSegmentListLC = retrieveValidStatusDatesegments(
								contract.getContractSysId(), "Y");
						contract.setContractDataFeedIndicator("Y");
					} else {
						dateSegmentListLC = retrieveValidStatusDatesegments(
								contract.getContractSysId(), "N");
						contract.setContractDataFeedIndicator("N");
					}
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.scheduleForTest(datsegment, user);
						}
					}
					Audit audit = getAudit(user);
					cob.persist(contract, audit, false);
					bom.scheduleForTest(contract, user);
					contract = (Contract) bom.retrieve(contract, user);
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_SEND_TO_TEST_SUCCESS);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.TEST_PASS_CONTRACT:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.testPass(datsegment, user);
						}
					}
					bom.testPass(contract, request.getUser());
					// Implicit transition for complex objects.
					// Contract will be Scheduled for approvel implicitly. This
					// is
					// done for
					// those contract for which normal date segment has been
					// scheduled. If test
					// date segment has been scheduled, implicit transition will
					// not
					// happen.
					bom.retrieve(contract, request.getUser());
					if (!("MODEL".equals(contract.getContractType()))) {
						if (!("Y".equals(contract
								.getContractDataFeedIndicator()))) {

							dateSegmentListLC = retrieveValidStatusDatesegments(
									contract.getContractSysId(), null);
							if (null != dateSegmentListLC
									&& !dateSegmentListLC.isEmpty()) {
								Iterator lcItr = dateSegmentListLC.iterator();
								while (lcItr.hasNext()) {
									DateSegment datsegment = (DateSegment) lcItr
											.next();
									bom.scheduleForApproval(datsegment, user);
								}
							}
							bom
									.scheduleForApproval(contract, request
											.getUser());
							bom.retrieve(contract, request.getUser());
							// If contract is model contract, then it cannot be
							// send to
							// production.
							// This will be published directly.
							/*
							 * if ("MODEL".equals(contract.getContractType())) {
							 * bom.approve(contract, request.getUser());
							 * bom.scheduleForProduction(contract, request
							 * .getUser()); bom.publish(contract,
							 * request.getUser()); }
							 */

						}
					}

					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_TEST_PASSED);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.TEST_FAIL_CONTRACT:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.testFail(datsegment, user);
						}
					}
					bom.testFail(contract, request.getUser());
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_TEST_FAILED);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.UNLOCK_CONTRACT:

				contract = new Contract();
				setBasicInfoToContract(contractVO, contract);
				bom.unlock(contract, request.getUser());
				// to get all the date segments in checked out or building
				// status.
				List dateList = retrieveCheckInDateSegments(contract
						.getContractSysId());
				Iterator dateItr = dateList.iterator();
				while (dateItr.hasNext()) {
					DateSegment lockDateSegment = (DateSegment) dateItr.next();
					bom.unlock(lockDateSegment, request.getUser());
				}
				informationalMessage = new InformationalMessage(
						BusinessConstants.CONTRACT_UNLOCKED);
				informationalMessage.setParameters(new String[] { contract
						.getContractId() });
				messageList.add(informationalMessage);
				response.setSuccess(true);
				response.setContract(contract);

				break;

			case SaveContractBasicInfoRequest.SCHEDULE_TO_APPROVE:
				contract = new Contract();
				setBasicInfoToContract(contractVO, contract);
				dateSegmentListLC = retrieveValidStatusDatesegments(contract
						.getContractSysId(), null);
				if (null != dateSegmentListLC && !dateSegmentListLC.isEmpty()) {
					Iterator lcItr = dateSegmentListLC.iterator();
					while (lcItr.hasNext()) {
						DateSegment datsegment = (DateSegment) lcItr.next();
						bom.scheduleForApproval(datsegment, user);
					}
				}
				bom.scheduleForApproval(contract, request.getUser());
				informationalMessage = new InformationalMessage(
						BusinessConstants.MSG_SCHEDULED_TO_APPROVE);
				informationalMessage.setParameters(new String[] { contract
						.getContractId() });
				messageList.add(informationalMessage);
				response.setSuccess(true);
				response.setContract(contract);
				break;
			case SaveContractBasicInfoRequest.APPROVE_CONTRACT:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					DateSegmentLocateCriteria locateCriteriaNotes = new DateSegmentLocateCriteria();
					LocateResults locateResultsNotes = null;
					locateCriteriaNotes.setContractSysId(contract
							.getContractSysId());
					locateResultsNotes = bom.locate(locateCriteriaNotes, user);
					ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
					int dateSegmentCount = locateResultsNotes
							.getLocateResults().size();
					if (dateSegmentCount >= 0) {
						for (int i = 0; i < dateSegmentCount; i++) {
							ContractLocateResult contractLocateResult = (ContractLocateResult) locateResultsNotes
									.getLocateResults().get(i);
							if ("Y".equals(contractLocateResult
									.getTestIndicator())) {
								DateSegment testDateSegment = new DateSegment();
								testDateSegment
										.setContractId(contractLocateResult
												.getContractId());
								testDateSegment
										.setDateSegmentSysId(contractLocateResult
												.getDateSegmentId());
								testDateSegment
										.setEffectiveDate(contractLocateResult
												.getStartDate());
								testDateSegment
										.setDateSegmentType(contractLocateResult
												.getTestIndicator());
								Audit auditDelete = getAudit(user);
								builder.delete(testDateSegment, contract,
										auditDelete);
							}

						}
					}
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.approve(datsegment, user);
						}
					}
					bom.approve(contract, request.getUser());
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.scheduleForProduction(datsegment, user);
						}
					}
					bom.scheduleForProduction(contract, request.getUser());
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_APPROVED_CONTRACT);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.REJECT_CONTRACT:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.reject(datsegment, user);
						}
					}
					bom.reject(contract, request.getUser());
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_REJECTED_CONTRACT);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.CHECKOUT_CONTRACT:
				contract = new Contract();
				int newdateSegmentId = 0;
				int newdateSegmentIdTest = 0;
				DateSegment checkoutDateSegment = null;
				HashMap map = new HashMap();
				if ((BusinessConstants.MSG_SCHEDULED_FOR_TEST.equals(contractVO
						.getStatus()))
						&& (isDataFeedRunning())) {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				} else {
					setBasicInfoToContract(contractVO, contract);
					contract = (Contract) bom.checkOut(contract, user);
					try {
						Map datesegmentMap = new HashMap();
						datesegmentMap.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						datesegmentMap.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(contractVO.getDateSegmentSysId()));
						Contract dtContract = (Contract) bom.retrieve(contract,
								user, datesegmentMap);
						if (null != contract
								&& null != contract.getDateSegmentList()) {
							checkoutDateSegment = (DateSegment) dtContract
									.getDateSegmentList().get(0);
							checkoutDateSegment.setContractId(contract
									.getContractId());
							if (BusinessConstants.MSG_REPLACE_PRODUCT
									.equals(contractVO.getProductStatus())) {
								checkoutDateSegment.setProductStatus("Y");
							} else {
								checkoutDateSegment.setProductStatus("N");
							}

							checkoutDateSegment = (DateSegment) bom.checkOut(
									checkoutDateSegment, user);
						}
						// contract = contractCheckoutReplace(contract,
						// contractVO, request.getUser());
					} catch (WPDException ex) {
						Audit auditDelete = this.getAudit(user);
						new LockManager().unlock(contract, user);
						if (null != checkoutDateSegment) {
							new LockManager().unlock(checkoutDateSegment, user);
						}
						new ContractBusinessObjectBuilder().delete(contract,
								auditDelete);
						throw ex;
					}
					DateSegment dateSegmentAfterCheckout = new DateSegment();
					map.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);

					map.put(BusinessConstants.DATESEGMENT_ID, new Integer(
							checkoutDateSegment.getDateSegmentSysId()));
					contract = (Contract) bom.retrieve(contract, user, map);
					response.setContract(contract);
					response.setDomainDetail(DomainUtil
							.retrieveDomainDetailInfo(
									BusinessConstants.ENTITY_TYPE_CONTRACT,
									contract.getParentSysId()));
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_CHECKOUT_SUCCESS);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
				}
				break;
			case SaveContractBasicInfoRequest.PUBLISH_CONTRACT:
				if (!isDataFeedRunning()) {
					contract = new Contract();
					setBasicInfoToContract(contractVO, contract);
					dateSegmentListLC = retrieveValidStatusDatesegments(
							contract.getContractSysId(), null);
					if (null != dateSegmentListLC
							&& !dateSegmentListLC.isEmpty()) {
						Iterator lcItr = dateSegmentListLC.iterator();
						while (lcItr.hasNext()) {
							DateSegment datsegment = (DateSegment) lcItr.next();
							bom.publish(datsegment, user);
						}
					}
					bom.publish(contract, request.getUser());
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_PUBLISHED_CONTRACT);
					informationalMessage.setParameters(new String[] { contract
							.getContractId() });
					messageList.add(informationalMessage);
					response.setSuccess(true);
					response.setContract(contract);
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.DATAFEED_RUNNING));
				}
				break;
			case SaveContractBasicInfoRequest.SCHEDULE_FOR_PRODUCTION:

				contract = new Contract();
				setBasicInfoToContract(contractVO, contract);
				dateSegmentListLC = retrieveValidStatusDatesegments(contract
						.getContractSysId(), null);
				if (null != dateSegmentListLC && !dateSegmentListLC.isEmpty()) {
					Iterator lcItr = dateSegmentListLC.iterator();
					while (lcItr.hasNext()) {
						DateSegment datsegment = (DateSegment) lcItr.next();
						bom.transfer(datsegment, user);
					}
				}
				bom.transfer(contract, request.getUser());
				informationalMessage = new InformationalMessage(
						BusinessConstants.MSG_SCHEDULED_FOR_PRODUCTION);
				informationalMessage.setParameters(new String[] { contract
						.getContractId() });
				messageList.add(informationalMessage);
				response.setSuccess(true);
				response.setContract(contract);
				break;

			case SaveContractBasicInfoRequest.CHECKOUT_PROCESS:
				int ifLatestProduct;
				if (!request.isRequestFromMigrationWizard()) {
					ifLatestProduct = ifLatestProductExist(contractVO
							.getContractSysId(), contractVO
							.getDateSegmentSysId());
				} else {
					ifLatestProduct = ifLatestProductExist(contractVO
							.getContractId(), contractVO.getContractSysId(),
							contractVO.getProductSysId());
				}
				response.setIfLatestProductExist(ifLatestProduct);
				break;

			case SaveContractBasicInfoRequest.CHECKOUT_PROCESS_CONT:
				List dateSegmentList = ifLatestNoteExist(contractVO, request
						.getUser());
				Contract contractNotes = new Contract();
				contractNotes.setDateSegmentList(dateSegmentList);
				response.setContract(contractNotes);
				break;

			case SaveContractBasicInfoRequest.SEND_TO_TEST_PROCESS:
				boolean ifTestDatesegmentExist = ifTestDatesegmentExist(
						contractVO, request.getUser());
				response.setTestDateSegments(ifTestDatesegmentExist);
				break;

			case SaveContractBasicInfoRequest.COPY_CONTRACT:

				contract = new Contract();
				ContractIDPoolRecord contractIdPoolInfo = null;
				int sequence = 0;
				// to get the effective date of the source contract
				Date oldEffectiveDate = new Date();

				setBasicInfoToContract(contractVO, contract);

				// IMAGE
				String completelyReadyForImageRewrite;
				if (BusinessUtil.isReadyForImageRewrite(contract)) {
					completelyReadyForImageRewrite = "Y";
				} else {
					completelyReadyForImageRewrite = "N";
				}

				response = (SaveContractBasicInfoResponse) (new ValidationServiceController()
						.execute(request));

				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					String contractId = generateContractIdFromGroup(request);
					if (validateContractId(contractId)) {
						messageList.add(new ErrorMessage(
								BusinessConstants.MSG_CONTRACTID_EXISTS));
						addMessagesToResponse(response, messageList);
						return response;
					}
					contract.setContractId(contractId);
					contract
							.setStateCode(contractVO.getHeadQuartersStateCode());
				} else {

					if (null == contract.getContractId()
							|| "".equals(contract.getContractId())) {

						contractIdInfo = contractIDSystemPool
								.getContractIdSystemPool();

						if (null == contractIdInfo) {
							messageList
									.add(new ErrorMessage(
											BusinessConstants.MSG_CONTRACT_SYSTEM_POOL_EXPIRED));
							addMessagesToResponse(response, messageList);
							return response;

						} else {
							contract.setContractId(contractIdInfo
									.getContractId());
							// sequence = contractIdInfo.getContractSequence();
						}
					} else {

						isReserved = true;
					}
				}

				if (isReserved) {
					// update status of reserve pool
					ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
					List newList = null;
					contractIdInfo = new ContractIDPoolRecord();
					contractIdInfo.setContractId(contract.getContractId());
					try {
						newList = contractIDSystemPool.locateContractIds(
								contractIdInfo, 1, true);
					} catch (ContractIDPoolException e1) {
						List errorParams = new ArrayList();
						throw new SevereException("Adapter Exception occured",
								errorParams, e1);
					}
					contractIDReservePoolRecord = (ContractIDReservePoolRecord) newList
							.get(0);
					contractIDReservePoolRecord.setContractId(contract
							.getContractId());

					String reservePoolStatus = contractIDReservePoolRecord
							.getReservePoolStatus();

					if (null != reservePoolStatus
							&& reservePoolStatus.trim().equals("N")) {
						contractIDReservePoolRecord.setReservePoolStatus("U");
						contractIDReservePoolRecord.setLastUpdatedUser(request
								.getUser().getUserId());
						contractIDReservePoolRecord.setSystemDomain("ETAB");
						contractIDReservePool
								.updateReserveContract(contractIDReservePoolRecord);
					} else {
						InformationalMessage message = new InformationalMessage(
								WebConstants.CONTRACT_IN_USE);
						messageList.add(message);
						addMessagesToResponse(response, messageList);
						return response;
					}

				} else {
					if (!(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE
							.equalsIgnoreCase(contract.getContractType()))) {

						ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
						contractIdInfo = new ContractIDPoolRecord();
						contractIdInfo.setContractId(contract.getContractId());
						List systemPoolList = contractIDSystemPool
								.locateContractIds(contractIdInfo, 1, true);
						contractIDReservePoolRecord = (ContractIDReservePoolRecord) systemPoolList
								.get(0);
						if (contractIDReservePoolRecord.getSystemPoolStatus()
								.trim().equals("N")) {
							String contractId = contractIdInfo.getContractId();// contract.getContractId();
							contractIdInfo = new ContractIDPoolRecord();
							contractIdInfo.setContractId(contractId);

							contractIdInfo.setCreatedUser(request.getUser()
									.getUserId());
							contractIdInfo.setSystemPoolStatus("U");
							contractIdInfo.setSystemDomain("ETAB");
							contractIDReservePool.markAsUnused(contractIdInfo);
						} else {
							InformationalMessage message = new InformationalMessage(
									WebConstants.CONTRACT_IN_USE);
							messageList.add(message);
							addMessagesToResponse(response, messageList);
							return response;
						}

					}
				}
				contractIdInfo = new ContractIDPoolRecord();
				contractIdInfo.setContractId(contract.getContractId());
				List systemPoolList_copy = contractIDSystemPool
						.locateContractIds(contractIdInfo, 1, true);
				ContractIDPoolRecord contractIDPool_copy = new ContractIDReservePoolRecord();
				contractIDPool_user = (ContractIDReservePoolRecord) systemPoolList_copy
						.get(0);

				if (null != (contractIDPool_user.getSystemDomain())
						&& contractIDPool_user.getSystemDomain().equals("ETAB")) {

				} else {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}

				try {
					bom.persist(contract, user, true);
				} catch (LockedBySameUserException se) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				} catch (LockedByAnotherUserException ae) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}
				DateSegment dateSegmentCopy = new DateSegment();
				dateSegmentCopy.setEffectiveDate(contractVO.getEffectiveDate());
				dateSegmentCopy.setExpiryDate(contractVO.getExpiryDate());
				dateSegmentCopy.setGroupSize(contractVO.getGroupSize());
				dateSegmentCopy.setGroupSizeDesc(contractVO.getGroupSizeDesc());
				dateSegmentCopy.setDateSegmentType("N");
				dateSegmentCopy.setBaseDateSegmentSysId(contractVO
						.getBaseDateSegmentSysId());
				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					dateSegmentCopy.setProductId(contractVO.getProductSysId());
				}
				dateSegmentCopy.setContractId(contract.getContractId());
				dateSegmentCopy.setContractSysId(contract.getContractSysId());

				dateSegmentCopy.setContractStatusBo(contractVO
						.getContractStatusBo());
				if (null != dateSegmentCopy.getContractStatusBo()) {
					dateSegmentCopy.getContractStatusBo().setContractId(
							contract.getContractId());
					dateSegmentCopy.getContractStatusBo().setCreatedUserId(
							user.getUserId());
					dateSegmentCopy.getContractStatusBo().setLastChangedUserId(
							user.getUserId());
				}
				dateSegmentCopy
						.setImageRWDAFlag(completelyReadyForImageRewrite);
				bom.persist(dateSegmentCopy, user, true);
				Map paramCopy = new HashMap();
				response.setSuccess(true);
				// checks if the contract type is other than custom or standard
				// if it has a base contract
				// if base contract id is null then standard contract does not
				// has a base contract.
				if (!(BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE
						.equals(contract.getContractType()))
						&& (null == contract.getBaseContractId())) {
					Contract contractcopy;
					// int basedateSegmentId =
					// getContractSession().getContractKeyObject().getDateSegmentId();
					int basedateSegmentId = request.getDateSegmentIdForCopy();
					paramCopy.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);
					paramCopy.put(BusinessConstants.DATESEGMENT_ID,
							new Integer(basedateSegmentId));
					contractcopy = (Contract) bom.retrieve(contract, user,
							paramCopy);
					DateSegment retrieveDateSegnment = (DateSegment) contractcopy
							.getDateSegmentList().get(0);

					oldEffectiveDate = retrieveDateSegnment.getEffectiveDate();

					retrieveDateSegnment.setDateSegmentSysId(dateSegmentCopy
							.getDateSegmentSysId());
					retrieveDateSegnment.setContractSysId(dateSegmentCopy
							.getContractSysId());
					retrieveDateSegnment.setEffectiveDate(dateSegmentCopy
							.getEffectiveDate());
					retrieveDateSegnment.setExpiryDate(dateSegmentCopy
							.getExpiryDate());
					retrieveDateSegnment.setVersion(dateSegmentCopy
							.getVersion());
					retrieveDateSegnment.setStatus(dateSegmentCopy.getStatus());

					// doubt
					retrieveDateSegnment.setGroupSize(dateSegmentCopy
							.getGroupSize());
					if (contractVO.getContractType().equalsIgnoreCase(
							BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
						retrieveDateSegnment.setProductId(contractVO
								.getProductSysId());
					}
					retrieveDateSegnment.setContractId(contractcopy
							.getContractId());
					retrieveDateSegnment.setContractSysId(contractcopy
							.getContractSysId());
					retrieveDateSegnment
							.setImageRWDAFlag(completelyReadyForImageRewrite);
					bom.persist(retrieveDateSegnment, user, false);
					HashMap mapCopy = new HashMap();
					mapCopy.put(BusinessConstants.ACTION, "copyDateSegment");
					ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
					builder.copyDateSegments(retrieveDateSegnment
							.getContractSysId(), basedateSegmentId,
							dateSegmentCopy.getDateSegmentSysId(), request
									.isCopyLegacyNotes(), user, false);

					/*
					 * newly added for mandate contracts .This happens if the
					 * product Id selected by the user is different from the
					 * source contract's product Id
					 */
					if (contractVO.getContractType().equalsIgnoreCase(
							BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
						if (contractVO.getProductSysId() != request
								.getProductSysId()) {
							builder.copyProductComponents(retrieveDateSegnment);
						}
					}

					// testing for product higher version

					ProductBO product = new ProductBO();
					if (BusinessConstants.MSG_REPLACE_PRODUCT.equals(request
							.getProductStatusForCopy())) {
						product = builder.retrieveLatestProductVersion(
								retrieveDateSegnment.getContractSysId(),
								retrieveDateSegnment.getDateSegmentSysId());
						if (product != null) {
							retrieveDateSegnment.setProductId(product
									.getProductKey());
							retrieveDateSegnment
									.setOldDateSegmentId(basedateSegmentId);
							retrieveDateSegnment.setContractId(contractcopy
									.getContractId());
							bom.persist(retrieveDateSegnment, user, false);
							builder.replaceProductComponents(
									retrieveDateSegnment, user);
						}
					} else
						product.setProductKey(request.getProductSysId());

					// Deleting domained notes...

					ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
					notesLocateCriteria.setDateSegmentId(dateSegmentCopy
							.getDateSegmentSysId());
					notesLocateCriteria
							.setEntityType(BusinessConstants.ATTACH_CONTRACT);
					notesLocateCriteria
							.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
					LocateResults locateResults = bom.locate(
							notesLocateCriteria, user);
					if (0 < locateResults.getTotalResultsCount()) {
						AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResults
								.getLocateResults().get(0);

						// returns null if it is an undomained note else returns
						// something
						List notesList = builder
								.getDomainedNote(attachedNotesBO);
						if (notesList != null
								&& notesList.size() != 0
								&& (BusinessConstants.MSG_REPLACE_PRODUCT
										.equals(request
												.getProductStatusForCopy()))) {

							AttachedNotesBO noteattachmentOverrideBO = (AttachedNotesBO) notesList
									.get(0);
							noteattachmentOverrideBO
									.setEntityType(BusinessConstants.ATTACH_CONTRACT);
							noteattachmentOverrideBO
									.setEntityId(dateSegmentCopy
											.getDateSegmentSysId());
							// calling the delete method of BOM
							bom.delete(noteattachmentOverrideBO,
									retrieveDateSegnment, user);

						}
						if (BusinessConstants.MSG_REPLACE_NOTE.equals(request
								.getNoteStatusForCopy())) {
							boolean replace = false;
							NoteBO noteBO = new NoteBO();
							noteBO.setNoteId(attachedNotesBO.getNoteId());
							noteBO.setVersion(attachedNotesBO.getVersion());
							List latestVersion = builder
									.getLatestVersionNote(noteBO);
							if (latestVersion != null
									&& latestVersion.size() != 0) {
								attachedNotesBO
										.setVersion(((NoteBO) latestVersion
												.get(0)).getVersion());
								notesList = builder
										.getDomainedNote(attachedNotesBO);

								// checks if the latest note version is
								// undomained
								if (notesList == null || notesList.size() == 0)
									replace = true;
								// checks if the latest version is domained to
								// the product atached
								else {
									AttachedNotesBO attachedNotesBO2 = (AttachedNotesBO) notesList
											.get(0);
									if ((BusinessConstants.ATTACH_PRODUCT
											.equals(attachedNotesBO
													.getEntityType()))
											&& (attachedNotesBO.getEntityId() == product
													.getProductKey()))
										replace = true;
								}
								if (replace == true) {

									AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
									noteattachmentOverrideBO
											.setNoteId(attachedNotesBO
													.getNoteId());
									noteattachmentOverrideBO
											.setEntityType(BusinessConstants.ATTACH_CONTRACT);
									noteattachmentOverrideBO
											.setEntityId(dateSegmentCopy
													.getDateSegmentSysId());
									// calling the delete method of BOM
									bom.delete(noteattachmentOverrideBO,
											retrieveDateSegnment, user);
									List notesIdList = new ArrayList();
									List versionList = new ArrayList();
									notesIdList
											.add(attachedNotesBO.getNoteId());
									versionList.add(new Integer(attachedNotesBO
											.getVersion()));
									List notesBOList = getContractOverrideNotesObject(
											notesIdList, dateSegmentCopy
													.getDateSegmentSysId(),
											product.getProductKey(),
											versionList);
									// call the persist method of the bom - for
									// benefitComponentNotesAttach
									bom.persist(notesBOList,
											retrieveDateSegnment, user, true);

								}
							}

						}
					}

					// This method is used to store the details of the copied
					// contracts for report use
					ContractHistory contractDetails = new ContractHistory();
					getContractCopyDetails(request, contract, oldEffectiveDate,
							retrieveDateSegnment, contractDetails);

					bom.persist(contractDetails, retrieveDateSegnment, user,
							true);
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_COPY_SUCCESS);
					informationalMessage
							.setParameters(new String[] { contractVO
									.getContractIdForCopy() });

					messageList.add(informationalMessage);
				} else {
					// checks if the present dateSegmentId is similar to the
					// session attribute
					// if(contract.getBaseDateSegmentSysId()==((Integer)getSession().getAttribute("baseDateId")).intValue()){
					if (contract.getBaseDateSegmentSysId() == request
							.getBaseDateIdForCopy()) {
						Contract contractcopy;
						// int basedateSegmentId =
						// getContractSession().getContractKeyObject().getDateSegmentId();
						int basedateSegmentId = request
								.getDateSegmentIdForCopy();
						paramCopy.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						paramCopy.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(basedateSegmentId));
						contractcopy = (Contract) bom.retrieve(contract, user,
								paramCopy);
						DateSegment retrieveDateSegnment = (DateSegment) contractcopy
								.getDateSegmentList().get(0);

						oldEffectiveDate = retrieveDateSegnment
								.getEffectiveDate();

						retrieveDateSegnment
								.setDateSegmentSysId(dateSegmentCopy
										.getDateSegmentSysId());
						retrieveDateSegnment.setContractSysId(dateSegmentCopy
								.getContractSysId());
						retrieveDateSegnment.setEffectiveDate(dateSegmentCopy
								.getEffectiveDate());
						retrieveDateSegnment.setExpiryDate(dateSegmentCopy
								.getExpiryDate());

						retrieveDateSegnment.setGroupSize(dateSegmentCopy
								.getGroupSize());
						retrieveDateSegnment.setVersion(dateSegmentCopy
								.getVersion());
						retrieveDateSegnment.setStatus(dateSegmentCopy
								.getStatus());
						retrieveDateSegnment.setContractId(contractcopy
								.getContractId());

						bom.persist(retrieveDateSegnment, user, false);
						HashMap mapCopy = new HashMap();
						mapCopy
								.put(BusinessConstants.ACTION,
										"copyDateSegment");
						ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
						builder.copyDateSegments(retrieveDateSegnment
								.getContractSysId(), basedateSegmentId,
								dateSegmentCopy.getDateSegmentSysId(), request
										.isCopyLegacyNotes(), user, false);

						// testing for product higher version

						ProductBO product = new ProductBO();
						if (BusinessConstants.MSG_REPLACE_PRODUCT
								.equals(request.getProductStatusForCopy())) {
							product = builder.retrieveLatestProductVersion(
									retrieveDateSegnment.getContractSysId(),
									retrieveDateSegnment.getDateSegmentSysId());
							if (product != null) {
								retrieveDateSegnment.setProductId(product
										.getProductKey());
								retrieveDateSegnment
										.setOldDateSegmentId(basedateSegmentId);
								retrieveDateSegnment.setContractId(contractcopy
										.getContractId());

								bom.persist(retrieveDateSegnment, user, false);
								builder.replaceProductComponents(
										retrieveDateSegnment, user);
							}
						} else
							product.setProductKey(request.getProductSysId());

						// Deleting domained notes...

						ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
						notesLocateCriteria.setDateSegmentId(dateSegmentCopy
								.getDateSegmentSysId());
						notesLocateCriteria
								.setEntityType(BusinessConstants.ATTACH_CONTRACT);
						notesLocateCriteria
								.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
						LocateResults locateResults = bom.locate(
								notesLocateCriteria, user);
						if (0 < locateResults.getTotalResultsCount()) {
							AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResults
									.getLocateResults().get(0);

							// returns null if it is an undomained note else
							// returns something
							List notesList = builder
									.getDomainedNote(attachedNotesBO);
							if (notesList != null
									&& notesList.size() != 0
									&& (BusinessConstants.MSG_REPLACE_PRODUCT
											.equals(request
													.getProductStatusForCopy()))) {

								AttachedNotesBO noteattachmentOverrideBO = (AttachedNotesBO) notesList
										.get(0);
								noteattachmentOverrideBO
										.setEntityType(BusinessConstants.ATTACH_CONTRACT);
								noteattachmentOverrideBO
										.setEntityId(dateSegmentCopy
												.getDateSegmentSysId());
								// calling the delete method of BOM
								bom.delete(noteattachmentOverrideBO,
										retrieveDateSegnment, user);

							}
							if (BusinessConstants.MSG_REPLACE_NOTE
									.equals(request.getNoteStatusForCopy())) {
								boolean replace = false;
								NoteBO noteBO = new NoteBO();
								noteBO.setNoteId(attachedNotesBO.getNoteId());
								noteBO.setVersion(attachedNotesBO.getVersion());
								List latestVersion = builder
										.getLatestVersionNote(noteBO);
								if (latestVersion != null
										&& latestVersion.size() != 0) {
									attachedNotesBO
											.setVersion(((NoteBO) latestVersion
													.get(0)).getVersion());
									notesList = builder
											.getDomainedNote(attachedNotesBO);

									// checks if the latest note version is
									// undomained
									if (notesList == null
											|| notesList.size() == 0)
										replace = true;
									// checks if the latest version is domained
									// to the product atached
									else {
										AttachedNotesBO attachedNotesBO2 = (AttachedNotesBO) notesList
												.get(0);
										if ((BusinessConstants.ATTACH_PRODUCT
												.equals(attachedNotesBO
														.getEntityType()))
												&& (attachedNotesBO
														.getEntityId() == product
														.getProductKey()))
											replace = true;
									}
									if (replace == true) {

										AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
										noteattachmentOverrideBO
												.setNoteId(attachedNotesBO
														.getNoteId());
										noteattachmentOverrideBO
												.setEntityType(BusinessConstants.ATTACH_CONTRACT);
										noteattachmentOverrideBO
												.setEntityId(dateSegmentCopy
														.getDateSegmentSysId());
										// calling the delete method of BOM
										bom.delete(noteattachmentOverrideBO,
												retrieveDateSegnment, user);
										List notesIdList = new ArrayList();
										List versionList = new ArrayList();
										notesIdList.add(attachedNotesBO
												.getNoteId());
										versionList.add(new Integer(
												attachedNotesBO.getVersion()));
										List notesBOList = getContractOverrideNotesObject(
												notesIdList, dateSegmentCopy
														.getDateSegmentSysId(),
												product.getProductKey(),
												versionList);
										// call the persist method of the bom -
										// for
										// benefitComponentNotesAttach
										bom.persist(notesBOList,
												retrieveDateSegnment, user,
												true);

									}
								}

							}
						}

						ContractHistory contractDetails = new ContractHistory();
						getContractCopyDetails(request, contract,
								oldEffectiveDate, retrieveDateSegnment,
								contractDetails);

						bom.persist(contractDetails, retrieveDateSegnment,
								user, true);

						informationalMessage = new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_COPY_SUCCESS);
						informationalMessage
								.setParameters(new String[] { contractVO
										.getContractIdForCopy() });

						messageList.add(informationalMessage);

					} else {
						// normal create follow
						Contract contractcopy;
						// int basedateSegmentId = contract
						// .getBaseDateSegmentSysId();
						int basedateSegmentId = request
								.getDateSegmentIdForCopy();
						paramCopy.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						paramCopy.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(basedateSegmentId));
						contractcopy = (Contract) bom.retrieve(contract, user,
								paramCopy);
						DateSegment retrieveDateSegnment = (DateSegment) contractcopy
								.getDateSegmentList().get(0);

						oldEffectiveDate = retrieveDateSegnment
								.getEffectiveDate();

						retrieveDateSegnment
								.setDateSegmentSysId(dateSegmentCopy
										.getDateSegmentSysId());
						retrieveDateSegnment.setContractSysId(dateSegmentCopy
								.getContractSysId());
						retrieveDateSegnment.setEffectiveDate(dateSegmentCopy
								.getEffectiveDate());
						retrieveDateSegnment.setExpiryDate(dateSegmentCopy
								.getExpiryDate());
						retrieveDateSegnment.setGroupSize(dateSegmentCopy
								.getGroupSize());
						retrieveDateSegnment.setVersion(dateSegmentCopy
								.getVersion());
						retrieveDateSegnment.setStatus(dateSegmentCopy
								.getStatus());
						retrieveDateSegnment.setContractId(contractcopy
								.getContractId());

						retrieveDateSegnment
								.setImageRWDAFlag(completelyReadyForImageRewrite);

						bom.persist(retrieveDateSegnment, user, false);
						HashMap map2 = new HashMap();
						map2.put(BusinessConstants.ACTION, "copyDateSegment");
						ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
						builder.copyDateSegments(retrieveDateSegnment
								.getContractSysId(), basedateSegmentId,
								dateSegmentCopy.getDateSegmentSysId(), request
										.isCopyLegacyNotes(), user, false);

						ContractHistory contractDetails = new ContractHistory();
						contractDetails.setSourceContractCode(contract
								.getBaseContractId());
						contractDetails.setSourceDateSegmentId(contract
								.getBaseDateSegmentSysId());

						contractDetails
								.setSourceDateSegmentEffectiveDate(oldEffectiveDate);
						contractDetails.setTargetContractCode(contract
								.getContractId());
						contractDetails
								.setTargetDateSegmentId(retrieveDateSegnment
										.getDateSegmentSysId());
						contractDetails
								.setTargetDateSegmentEffectiveDate(retrieveDateSegnment
										.getEffectiveDate());

						bom.persist(contractDetails, retrieveDateSegnment,
								user, true);

						messageList
								.add(new InformationalMessage(
										BusinessConstants.MSG_CONTRACT_COPY_SOURCE_SUCCESS));

					}
				}

				ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
				NoteBO currentNote = contractBuilder
						.retrieveCurrentNote(dateSegmentCopy
								.getDateSegmentSysId());
				if (currentNote != null
						&& BusinessConstants.MSG_MARKED_FOR_DELETION
								.equals(currentNote.getStatus())) {
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_NOTE_EXPIRED_DATE);
					messageList.add(informationalMessage);
				}
				Map map1 = new HashMap();
				map1.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				map1.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						dateSegmentCopy.getDateSegmentSysId()));
				contract = (Contract) bom.retrieve(contract, request.getUser(),
						map1);
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						BusinessConstants.ENTITY_TYPE_CONTRACT, contract
								.getParentSysId()));
				response.setContract((Contract) bom.retrieve(contract, user));
				Logger.logInfo("Returning  from execute method for request="
						+ request.getClass().getName());
				break;

			case SaveContractBasicInfoRequest.COPY_HEADINGS_CONTRACT:

				contract = new Contract();
				int sequenceContract = 0;
				Date oldEffectiveDateHeadings = new Date();

				setBasicInfoToContract(contractVO, contract);

				// IMAGE
				if (BusinessUtil.isReadyForImageRewrite(contract)) {
					completelyReadyForImageRewrite = "Y";
				} else {
					completelyReadyForImageRewrite = "N";
				}
				response = (SaveContractBasicInfoResponse) (new ValidationServiceController()
						.execute(request));

				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					String contractId = generateContractIdFromGroup(request);
					if (validateContractId(contractId)) {
						messageList.add(new ErrorMessage(
								BusinessConstants.MSG_CONTRACTID_EXISTS));
						addMessagesToResponse(response, messageList);
						return response;
					}
					contract.setContractId(contractId);
					contract
							.setStateCode(contractVO.getHeadQuartersStateCode());
				} else {

					if (null == contract.getContractId()
							|| "".equals(contract.getContractId())) {

						contractIdInfo = contractIDSystemPool
								.getContractIdSystemPool();

						if (null == contractIdInfo) {
							messageList
									.add(new ErrorMessage(
											BusinessConstants.MSG_CONTRACT_SYSTEM_POOL_EXPIRED));
							addMessagesToResponse(response, messageList);
							return response;

						} else {
							// sequenceContract = contractIdInfo
							// .getContractSequence();
							contract.setContractId(contractIdInfo
									.getContractId());
						}

					} else {

						isReserved = true;
					}
				}

				if (isReserved) {
					// update status of reserve pool
					ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
					List newList = null;
					contractIdInfo = new ContractIDPoolRecord();
					contractIdInfo.setContractId(contract.getContractId());
					try {
						newList = contractIDSystemPool.locateContractIds(
								contractIdInfo, 1, true);
					} catch (ContractIDPoolException e1) {
						List errorParams = new ArrayList();
						throw new SevereException("Adapter Exception occured",
								errorParams, e1);
					}
					contractIDReservePoolRecord = (ContractIDReservePoolRecord) newList
							.get(0);
					contractIDReservePoolRecord.setContractId(contract
							.getContractId());

					String reservePoolStatus = contractIDReservePoolRecord
							.getReservePoolStatus();
					if (null != reservePoolStatus
							&& reservePoolStatus.trim().equals("N")) {
						contractIDReservePoolRecord.setReservePoolStatus("U");
						contractIDReservePoolRecord.setLastUpdatedUser(request
								.getUser().getUserId());
						contractIDReservePoolRecord.setSystemDomain("ETAB");
						contractIDReservePool
								.updateReserveContract(contractIDReservePoolRecord);
					} else {
						InformationalMessage message = new InformationalMessage(
								WebConstants.CONTRACT_IN_USE);
						messageList.add(message);
						addMessagesToResponse(response, messageList);
						return response;
					}

				} else {
					if (!(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE
							.equalsIgnoreCase(contract.getContractType()))) {

						ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
						contractIdInfo = new ContractIDPoolRecord();
						contractIdInfo.setContractId(contract.getContractId());
						List systemPoolList = contractIDSystemPool
								.locateContractIds(contractIdInfo, 1, true);
						contractIDReservePoolRecord = (ContractIDReservePoolRecord) systemPoolList
								.get(0);
						if (contractIDReservePoolRecord.getSystemPoolStatus()
								.trim().equals("N")) {
							String contractId = contractIdInfo.getContractId();// contract.getContractId();
							contractIdInfo = new ContractIDPoolRecord();
							contractIdInfo.setContractId(contractId);

							contractIdInfo.setCreatedUser(request.getUser()
									.getUserId());
							contractIdInfo.setSystemPoolStatus("U");
							contractIdInfo.setSystemDomain("ETAB");
							contractIDReservePool.markAsUnused(contractIdInfo);
						} else {
							InformationalMessage message = new InformationalMessage(
									WebConstants.CONTRACT_IN_USE);
							messageList.add(message);
							addMessagesToResponse(response, messageList);
							return response;
						}

					}
				}

				contractIdInfo = new ContractIDPoolRecord();
				contractIdInfo.setContractId(contract.getContractId());
				List systemPoolList_copyHead = contractIDSystemPool
						.locateContractIds(contractIdInfo, 1, true);
				ContractIDPoolRecord contractIDPool_copyHead = new ContractIDReservePoolRecord();
				contractIDPool_user = (ContractIDReservePoolRecord) systemPoolList_copyHead
						.get(0);

				if (null != (contractIDPool_user.getSystemDomain())
						&& contractIDPool_user.getSystemDomain().equals("ETAB")) {
				} else {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}
				try {
					bom.persist(contract, user, true);
				} catch (LockedBySameUserException se) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				} catch (LockedByAnotherUserException ae) {
					InformationalMessage message = new InformationalMessage(
							WebConstants.CONTRACT_IN_USE);
					messageList.add(message);
					addMessagesToResponse(response, messageList);
					return response;
				}
				DateSegment dateSegmentCopyHeading = new DateSegment();
				dateSegmentCopyHeading.setEffectiveDate(contractVO
						.getEffectiveDate());
				dateSegmentCopyHeading
						.setExpiryDate(contractVO.getExpiryDate());
				dateSegmentCopyHeading.setGroupSize(contractVO.getGroupSize());
				dateSegmentCopyHeading.setGroupSizeDesc(contractVO
						.getGroupSizeDesc());
				dateSegmentCopyHeading.setDateSegmentType("N");
				if (contractVO.getContractType().equalsIgnoreCase(
						BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
					dateSegmentCopyHeading.setProductId(contractVO
							.getProductSysId());
				}
				dateSegmentCopyHeading.setContractId(contract.getContractId());
				dateSegmentCopyHeading.setContractSysId(contract
						.getContractSysId());
				dateSegmentCopyHeading.setContractStatusBo(contractVO
						.getContractStatusBo());
				if (null != dateSegmentCopyHeading.getContractStatusBo()) {
					dateSegmentCopyHeading.getContractStatusBo().setContractId(
							contract.getContractId());
					dateSegmentCopyHeading.getContractStatusBo()
							.setCreatedUserId(user.getUserId());
					dateSegmentCopyHeading.getContractStatusBo()
							.setLastChangedUserId(user.getUserId());
				}
				dateSegmentCopyHeading
						.setImageRWDAFlag(completelyReadyForImageRewrite);

				bom.persist(dateSegmentCopyHeading, user, true);
				Map paramCopyHeading = new HashMap();
				response.setSuccess(true);
				// checks if the contract type is other than custom or standard
				// if it has a base contract
				// if base contract id is null then standard contract does not
				// has a base contract.
				if ((!(BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE
						.equals(contract.getContractType())))
						&& (null == contract.getBaseContractId())) {
					Contract contractcopy;
					// int basedateSegmentId =
					// getContractSession().getContractKeyObject().getDateSegmentId();
					int basedateSegmentId = request.getDateSegmentIdForCopy();
					paramCopyHeading.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);
					paramCopyHeading.put(BusinessConstants.DATESEGMENT_ID,
							new Integer(basedateSegmentId));
					contractcopy = (Contract) bom.retrieve(contract, user,
							paramCopyHeading);
					DateSegment retrieveDateSegnment = (DateSegment) contractcopy
							.getDateSegmentList().get(0);

					oldEffectiveDateHeadings = retrieveDateSegnment
							.getEffectiveDate();
					retrieveDateSegnment
							.setDateSegmentSysId(dateSegmentCopyHeading
									.getDateSegmentSysId());
					retrieveDateSegnment
							.setContractSysId(dateSegmentCopyHeading
									.getContractSysId());
					retrieveDateSegnment
							.setEffectiveDate(dateSegmentCopyHeading
									.getEffectiveDate());
					retrieveDateSegnment.setExpiryDate(dateSegmentCopyHeading
							.getExpiryDate());
					retrieveDateSegnment.setVersion(dateSegmentCopyHeading
							.getVersion());
					retrieveDateSegnment.setStatus(dateSegmentCopyHeading
							.getStatus());

					retrieveDateSegnment.setGroupSize(dateSegmentCopyHeading
							.getGroupSize());
					if (contractVO.getContractType().equalsIgnoreCase(
							BusinessConstants.MSG_MANDATE_CONTRACT_TYPE)) {
						retrieveDateSegnment.setProductId(contractVO
								.getProductSysId());
					}
					retrieveDateSegnment.setContractId(contractcopy
							.getContractId());

					bom.persist(retrieveDateSegnment, user, false);
					HashMap mapCopy = new HashMap();
					mapCopy.put(BusinessConstants.ACTION, "copyDateSegment");
					ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
					/*
					 * builder.copyDateSegments(retrieveDateSegnment
					 * .getContractSysId(), basedateSegmentId,
					 * dateSegmentCopyHeading.getDateSegmentSysId(),
					 * request.isCopyLegacyNotes(), user);
					 * builder.deleteBenefitValues
					 * (dateSegmentCopyHeading.getDateSegmentSysId
					 * (),request.getProductSysId(),user);
					 */

					// This method copies the default product components(it uses
					// Call procedure COPY_CONTRACT_PRODUCT
					// to use the new Table
					builder.copyProductComponents(retrieveDateSegnment);

					List headingsList = request.getSelectedLineList();
					int sourceDateSegmentId = 0;
					for (int j = 0; j < headingsList.size(); j++) {
						List lineList = (List) headingsList.get(j);
						for (int k = 0; k < lineList.size(); k++) {
							ContractBenefitHeadings bnftLines = (ContractBenefitHeadings) lineList
									.get(k);
							sourceDateSegmentId = bnftLines.getDateSegmentId();
							bnftLines.setDateSegmentId(dateSegmentCopyHeading
									.getDateSegmentSysId());
							Audit auditOne = getAudit(user);
							bnftLines.setLastUpdatedUser(auditOne.getUser());
							bnftLines.setLastUpdatedTimestamp(auditOne
									.getTime());
							bnftLines.setCreatedUser(auditOne.getUser());
							bnftLines.setCreatedTimestamp(auditOne.getTime());
							// COPY CODED LINES:STABILIZATION RELEASE 2011:BUG -
							// PROD00558153 START
							if (builder.isLineCodedInDateSegment(
									retrieveDateSegnment.getDateSegmentSysId(),
									bnftLines.getBenefitComponentId(),
									bnftLines.getBenefitLineId())) {
								builder.copySelectedBenefitValues(bnftLines,
										sourceDateSegmentId, user.getUserId());
							} else {
								builder
										.saveNewlyCodedLinesForContractBC(bnftLines);
							}
							// COPY CODED LINES:STABILIZATION RELEASE 2011:BUG -
							// PROD00558153 END
						}
						if (null != lineList && !lineList.isEmpty()) {
							ContractBenefitHeadings bnftLine = (ContractBenefitHeadings) lineList
									.get(0);
							CopyBenefitHeadingsBO headingBO = new CopyBenefitHeadingsBO();
							headingBO.setBenefitsysId(bnftLine
									.getStandardBenefitId());
							headingBO.setComponentId(bnftLine
									.getBenefitComponentId());
							headingBO.setTrgtDateSegmentId(bnftLine
									.getDateSegmentId());
							builder.insertBenefitHeadingDetails(headingBO, user
									.getUserId(), null);

						}

					}
					// COPY PRICING INFO:STABILIZATION RELEASE 2011:BUG -
					// PROD00558153 START
					Contract contractPricing = builder
							.retrieveContractPricingInfo(sourceDateSegmentId);
					ArrayList pricingInfoList = null;
					if (contractPricing.getDateSegmentList() != null
							&& contractPricing.getDateSegmentList().size() != 0) {
						pricingInfoList = (ArrayList) ((DateSegment) contractPricing
								.getDateSegmentList().get(0))
								.getPricingInfoList();
						for (int i = 0; i < pricingInfoList.size(); i++) {
							ContractPricingInfo contractPricingInfo = (ContractPricingInfo) pricingInfoList
									.get(i);
							contractPricingInfo
									.setContractDateSegmentSysId(retrieveDateSegnment
											.getDateSegmentSysId());
							bom.persist(contractPricingInfo,
									retrieveDateSegnment, user, true);
						}
					}
					// COPY PRICING INFO:STABILIZATION RELEASE 2011:BUG -
					// PROD00558153 END

					Audit audit = getAudit(user); // CALL UPDATE_CONTRACT_NOTES
					builder.updateContractNotes(sourceDateSegmentId,
							dateSegmentCopyHeading.getDateSegmentSysId(), user
									.getUserId(), null); // CALL
					// CNTRCT_BNFT_COPY
					builder.updateContractInfoForCopy(request
							.getDateSegmentIdForCopy(), dateSegmentCopyHeading
							.getDateSegmentSysId(), request
							.getContractKeyObject().getContractSysId(), audit,
							null);

					List unSelectedList = request.getUnSelectedLineList();
					CopyBenefitHeadingsBO copyBenefitHeadingsBO = null;
					for (int i = 0; i < unSelectedList.size(); i++) { // CALL
						// DELETE_BENEFIT_TIER_DETAILS

						copyBenefitHeadingsBO = new CopyBenefitHeadingsBO();
						String appendedString = (String) unSelectedList.get(i);
						String[] appendedList = appendedString.split("\\.");
						copyBenefitHeadingsBO.setBenefitsysId(Integer
								.parseInt(appendedList[0]));
						copyBenefitHeadingsBO.setComponentId(Integer
								.parseInt(appendedList[1]));
						copyBenefitHeadingsBO
								.setTrgtDateSegmentId(dateSegmentCopyHeading
										.getDateSegmentSysId());
						builder.deleteTierLvlLine(copyBenefitHeadingsBO,
								getAudit(user));
					}

					// Deleting domained notes...

					ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
					notesLocateCriteria.setDateSegmentId(dateSegmentCopyHeading
							.getDateSegmentSysId());
					notesLocateCriteria
							.setEntityType(BusinessConstants.ATTACH_CONTRACT);
					notesLocateCriteria
							.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
					LocateResults locateResults = bom.locate(
							notesLocateCriteria, user);
					if (0 < locateResults.getTotalResultsCount()) {
						AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResults
								.getLocateResults().get(0);

						// returns null if it is an undomained note else returns
						// something
						List notesList = builder
								.getDomainedNote(attachedNotesBO);
						if (notesList != null
								&& notesList.size() != 0
								&& (BusinessConstants.MSG_REPLACE_PRODUCT
										.equals(request
												.getProductStatusForCopy()))) {

							AttachedNotesBO noteattachmentOverrideBO = (AttachedNotesBO) notesList
									.get(0);
							noteattachmentOverrideBO
									.setEntityType(BusinessConstants.ATTACH_CONTRACT);
							noteattachmentOverrideBO
									.setEntityId(dateSegmentCopyHeading
											.getDateSegmentSysId());
							// calling the delete method of BOM
							bom.delete(noteattachmentOverrideBO,
									retrieveDateSegnment, user);

						}
						if (BusinessConstants.MSG_REPLACE_NOTE.equals(request
								.getNoteStatusForCopy())) {
							boolean replace = false;
							NoteBO noteBO = new NoteBO();
							noteBO.setNoteId(attachedNotesBO.getNoteId());
							noteBO.setVersion(attachedNotesBO.getVersion());
							List latestVersion = builder
									.getLatestVersionNote(noteBO);
							if (latestVersion != null
									&& latestVersion.size() != 0) {
								attachedNotesBO
										.setVersion(((NoteBO) latestVersion
												.get(0)).getVersion());
								notesList = builder
										.getDomainedNote(attachedNotesBO);

								// checks if the latest note version is
								// undomained
								if (notesList == null || notesList.size() == 0)
									replace = true;
								// checks if the latest version is domained to
								// the product atached
								else {
									AttachedNotesBO attachedNotesBO2 = (AttachedNotesBO) notesList
											.get(0);
									if ((BusinessConstants.ATTACH_PRODUCT
											.equals(attachedNotesBO
													.getEntityType()))
											&& (attachedNotesBO.getEntityId() == request
													.getProductSysId()))
										replace = true;
								}
								if (replace == true) {

									AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
									noteattachmentOverrideBO
											.setNoteId(attachedNotesBO
													.getNoteId());
									noteattachmentOverrideBO
											.setEntityType(BusinessConstants.ATTACH_CONTRACT);
									noteattachmentOverrideBO
											.setEntityId(dateSegmentCopyHeading
													.getDateSegmentSysId());
									// calling the delete method of BOM
									bom.delete(noteattachmentOverrideBO,
											retrieveDateSegnment, user);
									List notesIdList = new ArrayList();
									List versionList = new ArrayList();
									notesIdList
											.add(attachedNotesBO.getNoteId());
									versionList.add(new Integer(attachedNotesBO
											.getVersion()));
									List notesBOList = getContractOverrideNotesObject(
											notesIdList, dateSegmentCopyHeading
													.getDateSegmentSysId(),
											request.getProductSysId(),
											versionList);
									// call the persist method of the bom - for
									// benefitComponentNotesAttach
									bom.persist(notesBOList,
											retrieveDateSegnment, user, true);

								}
							}

						}
					}

					ContractHistory contractDetails = new ContractHistory();
					getContractCopyDetails(request, contract,
							oldEffectiveDateHeadings, retrieveDateSegnment,
							contractDetails);

					bom.persist(contractDetails, retrieveDateSegnment, user,
							true);

					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_COPY_SUCCESS);
					informationalMessage
							.setParameters(new String[] { contractVO
									.getContractIdForCopy() });

					messageList.add(informationalMessage);
				} else {
					// checks if the present dateSegmentId is similar to the
					// session attribute
					// if(contract.getBaseDateSegmentSysId()==((Integer)getSession().getAttribute("baseDateId")).intValue()){
					if (contract.getBaseDateSegmentSysId() == request
							.getBaseDateIdForCopy()) {
						Contract contractcopy;
						// int basedateSegmentId =
						// getContractSession().getContractKeyObject().getDateSegmentId();
						int basedateSegmentId = request
								.getDateSegmentIdForCopy();
						paramCopyHeading.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						paramCopyHeading.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(basedateSegmentId));
						contractcopy = (Contract) bom.retrieve(contract, user,
								paramCopyHeading);
						DateSegment retrieveDateSegnment = (DateSegment) contractcopy
								.getDateSegmentList().get(0);

						oldEffectiveDateHeadings = retrieveDateSegnment
								.getEffectiveDate();

						retrieveDateSegnment
								.setDateSegmentSysId(dateSegmentCopyHeading
										.getDateSegmentSysId());
						retrieveDateSegnment
								.setContractSysId(dateSegmentCopyHeading
										.getContractSysId());
						retrieveDateSegnment
								.setEffectiveDate(dateSegmentCopyHeading
										.getEffectiveDate());
						retrieveDateSegnment
								.setExpiryDate(dateSegmentCopyHeading
										.getExpiryDate());
						retrieveDateSegnment.setStatus(dateSegmentCopyHeading
								.getStatus());
						retrieveDateSegnment.setVersion(dateSegmentCopyHeading
								.getVersion());
						retrieveDateSegnment
								.setGroupSize(dateSegmentCopyHeading
										.getGroupSize());
						retrieveDateSegnment.setContractId(contractcopy
								.getContractId());

						retrieveDateSegnment
								.setImageRWDAFlag(completelyReadyForImageRewrite);
						bom.persist(retrieveDateSegnment, user, false);
						HashMap mapCopy = new HashMap();
						mapCopy
								.put(BusinessConstants.ACTION,
										"copyDateSegment");
						ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
						builder.copyDateSegments(retrieveDateSegnment
								.getContractSysId(), basedateSegmentId,
								dateSegmentCopyHeading.getDateSegmentSysId(),
								request.isCopyLegacyNotes(), user, false);

						builder.copyProductDefaultComponents(request
								.getProductSysId(), dateSegmentCopyHeading
								.getDateSegmentSysId(), user);

						List headingsList = request.getSelectedLineList();
						Audit auditOne = getAudit(user);
						int sourceDateSegmentId = 0;
						for (int j = 0; j < headingsList.size(); j++) {
							List lineList = (List) headingsList.get(j);
							for (int k = 0; k < lineList.size(); k++) {
								ContractBenefitHeadings bnftLines = (ContractBenefitHeadings) lineList
										.get(k);
								sourceDateSegmentId = bnftLines
										.getDateSegmentId();
								bnftLines
										.setDateSegmentId(dateSegmentCopyHeading
												.getDateSegmentSysId());
								bnftLines
										.setLastUpdatedUser(auditOne.getUser());
								bnftLines.setLastUpdatedTimestamp(auditOne
										.getTime());

								bnftLines.setCreatedUser(auditOne.getUser());
								bnftLines.setCreatedTimestamp(auditOne
										.getTime());
								if (builder.isLineCodedInDateSegment(
										retrieveDateSegnment
												.getDateSegmentSysId(),
										bnftLines.getBenefitComponentId(),
										bnftLines.getBenefitLineId())) {
									builder.copySelectedBenefitValues(
											bnftLines, sourceDateSegmentId,
											user.getUserId(), null);
								} else {
									builder
											.saveNewlyCodedLinesForContractBC(bnftLines);
								}

								/*
								 * builder.copySelectedBenefitValues(bnftLines,
								 * sourceDateSegmentId, user.getUserId(), null);
								 */

							}
							if (null != lineList && !lineList.isEmpty()) {
								ContractBenefitHeadings bnftLine = (ContractBenefitHeadings) lineList
										.get(0);
								CopyBenefitHeadingsBO headingBO = new CopyBenefitHeadingsBO();
								headingBO.setBenefitsysId(bnftLine
										.getStandardBenefitId());
								headingBO.setComponentId(bnftLine
										.getBenefitComponentId());
								headingBO.setTrgtDateSegmentId(bnftLine
										.getDateSegmentId());
								builder.insertBenefitHeadingDetails(headingBO,
										user.getUserId(), null);

							}

						}
						// COPY PRICING INFO:STABILIZATION RELEASE 2011:BUG -
						// PROD00558153 START
						Contract contractPricing = builder
								.retrieveContractPricingInfo(sourceDateSegmentId);
						ArrayList pricingInfoList = null;
						if (contractPricing.getDateSegmentList() != null
								&& contractPricing.getDateSegmentList().size() != 0) {
							pricingInfoList = (ArrayList) ((DateSegment) contractPricing
									.getDateSegmentList().get(0))
									.getPricingInfoList();
							for (int i = 0; i < pricingInfoList.size(); i++) {
								ContractPricingInfo contractPricingInfo = (ContractPricingInfo) pricingInfoList
										.get(i);
								contractPricingInfo
										.setContractDateSegmentSysId(retrieveDateSegnment
												.getDateSegmentSysId());
								bom.persist(contractPricingInfo,
										retrieveDateSegnment, user, true);
							}
						}
						// COPY PRICING INFO:STABILIZATION RELEASE 2011:BUG -
						// PROD00558153 END
						builder.updateContractNotes(sourceDateSegmentId,
								dateSegmentCopyHeading.getDateSegmentSysId(),
								user.getUserId(), null);

						// Deleting domained notes...

						ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
						notesLocateCriteria
								.setDateSegmentId(dateSegmentCopyHeading
										.getDateSegmentSysId());
						notesLocateCriteria
								.setEntityType(BusinessConstants.ATTACH_CONTRACT);
						notesLocateCriteria
								.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
						LocateResults locateResults = bom.locate(
								notesLocateCriteria, user);
						if (0 < locateResults.getTotalResultsCount()) {
							AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResults
									.getLocateResults().get(0);

							// returns null if it is an undomained note else
							// returns something
							List notesList = builder
									.getDomainedNote(attachedNotesBO);
							if (notesList != null
									&& notesList.size() != 0
									&& (BusinessConstants.MSG_REPLACE_PRODUCT
											.equals(request
													.getProductStatusForCopy()))) {

								AttachedNotesBO noteattachmentOverrideBO = (AttachedNotesBO) notesList
										.get(0);
								noteattachmentOverrideBO
										.setEntityType(BusinessConstants.ATTACH_CONTRACT);
								noteattachmentOverrideBO
										.setEntityId(dateSegmentCopyHeading
												.getDateSegmentSysId());
								// calling the delete method of BOM
								bom.delete(noteattachmentOverrideBO,
										retrieveDateSegnment, user);

							}
							if (BusinessConstants.MSG_REPLACE_NOTE
									.equals(request.getNoteStatusForCopy())) {
								boolean replace = false;
								NoteBO noteBO = new NoteBO();
								noteBO.setNoteId(attachedNotesBO.getNoteId());
								noteBO.setVersion(attachedNotesBO.getVersion());
								List latestVersion = builder
										.getLatestVersionNote(noteBO);
								if (latestVersion != null
										&& latestVersion.size() != 0) {
									attachedNotesBO
											.setVersion(((NoteBO) latestVersion
													.get(0)).getVersion());
									notesList = builder
											.getDomainedNote(attachedNotesBO);

									// checks if the latest note version is
									// undomained
									if (notesList == null
											|| notesList.size() == 0)
										replace = true;
									// checks if the latest version is domained
									// to the product atached
									else {
										AttachedNotesBO attachedNotesBO2 = (AttachedNotesBO) notesList
												.get(0);
										if ((BusinessConstants.ATTACH_PRODUCT
												.equals(attachedNotesBO
														.getEntityType()))
												&& (attachedNotesBO
														.getEntityId() == request
														.getProductSysId()))
											replace = true;
									}
									if (replace == true) {

										AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
										noteattachmentOverrideBO
												.setNoteId(attachedNotesBO
														.getNoteId());
										noteattachmentOverrideBO
												.setEntityType(BusinessConstants.ATTACH_CONTRACT);
										noteattachmentOverrideBO
												.setEntityId(dateSegmentCopyHeading
														.getDateSegmentSysId());
										// calling the delete method of BOM
										bom.delete(noteattachmentOverrideBO,
												retrieveDateSegnment, user);
										List notesIdList = new ArrayList();
										List versionList = new ArrayList();
										notesIdList.add(attachedNotesBO
												.getNoteId());
										versionList.add(new Integer(
												attachedNotesBO.getVersion()));
										List notesBOList = getContractOverrideNotesObject(
												notesIdList,
												dateSegmentCopyHeading
														.getDateSegmentSysId(),
												request.getProductSysId(),
												versionList);
										// call the persist method of the bom -
										// for
										// benefitComponentNotesAttach
										bom.persist(notesBOList,
												retrieveDateSegnment, user,
												true);

									}
								}

							}
						}

						ContractHistory contractDetails = new ContractHistory();
						getContractCopyDetails(request, contract,
								oldEffectiveDateHeadings, retrieveDateSegnment,
								contractDetails);

						bom.persist(contractDetails, retrieveDateSegnment,
								user, true);

						informationalMessage = new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_COPY_SUCCESS);
						informationalMessage
								.setParameters(new String[] { contractVO
										.getContractIdForCopy() });

						messageList.add(informationalMessage);

					} else {
						// normal create follow
						Contract contractcopy;
						// int basedateSegmentId = contract
						// .getBaseDateSegmentSysId();
						int basedateSegmentId = request
								.getDateSegmentIdForCopy();
						paramCopyHeading.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						paramCopyHeading.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(basedateSegmentId));
						contractcopy = (Contract) bom.retrieve(contract, user,
								paramCopyHeading);
						DateSegment retrieveDateSegnment = (DateSegment) contractcopy
								.getDateSegmentList().get(0);

						oldEffectiveDateHeadings = retrieveDateSegnment
								.getEffectiveDate();

						retrieveDateSegnment
								.setDateSegmentSysId(dateSegmentCopyHeading
										.getDateSegmentSysId());
						retrieveDateSegnment
								.setContractSysId(dateSegmentCopyHeading
										.getContractSysId());
						retrieveDateSegnment
								.setEffectiveDate(dateSegmentCopyHeading
										.getEffectiveDate());
						retrieveDateSegnment
								.setExpiryDate(dateSegmentCopyHeading
										.getExpiryDate());
						retrieveDateSegnment
								.setGroupSize(dateSegmentCopyHeading
										.getGroupSize());
						retrieveDateSegnment.setStatus(dateSegmentCopyHeading
								.getStatus());
						retrieveDateSegnment.setVersion(dateSegmentCopyHeading
								.getVersion());
						retrieveDateSegnment.setContractId(contractcopy
								.getContractId());
						retrieveDateSegnment
								.setImageRWDAFlag(completelyReadyForImageRewrite);
						bom.persist(retrieveDateSegnment, user, false);
						HashMap map2 = new HashMap();
						map2.put(BusinessConstants.ACTION, "copyDateSegment");
						ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
						builder.copyDateSegments(retrieveDateSegnment
								.getContractSysId(), basedateSegmentId,
								dateSegmentCopyHeading.getDateSegmentSysId(),
								request.isCopyLegacyNotes(), user, false);

						ContractHistory contractDetails = new ContractHistory();
						contractDetails.setSourceContractCode(contract
								.getBaseContractId());
						contractDetails.setSourceDateSegmentId(contract
								.getBaseDateSegmentSysId());

						contractDetails
								.setSourceDateSegmentEffectiveDate(oldEffectiveDateHeadings);
						contractDetails.setTargetContractCode(contract
								.getContractId());
						contractDetails
								.setTargetDateSegmentId(retrieveDateSegnment
										.getDateSegmentSysId());
						contractDetails
								.setTargetDateSegmentEffectiveDate(retrieveDateSegnment
										.getEffectiveDate());
						bom.persist(contractDetails, retrieveDateSegnment,
								user, true);
						messageList
								.add(new InformationalMessage(
										BusinessConstants.MSG_CONTRACT_COPY_SOURCE_SUCCESS));

					}
				}

				ContractBusinessObjectBuilder objectBuilder = new ContractBusinessObjectBuilder();
				NoteBO note = objectBuilder
						.retrieveCurrentNote(dateSegmentCopyHeading
								.getDateSegmentSysId());
				if (note != null
						&& BusinessConstants.MSG_MARKED_FOR_DELETION
								.equals(note.getStatus())) {
					informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_NOTE_EXPIRED_DATE);
					messageList.add(informationalMessage);
				}
				Map mapHeading = new HashMap();
				mapHeading.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				mapHeading.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						dateSegmentCopyHeading.getDateSegmentSysId()));
				contract = (Contract) bom.retrieve(contract, request.getUser(),
						mapHeading);
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						BusinessConstants.ENTITY_TYPE_CONTRACT, contract
								.getParentSysId()));
				response.setContract((Contract) bom.retrieve(contract, user));
				Logger.logInfo("Returning  from execute method for request="
						+ request.getClass().getName());
				break;

			case SaveContractBasicInfoRequest.COPY_PROCESS:

				int latestProduct = ifLatestProductExistForCopy(contractVO
						.getContractSysId(), request.getProductSysId(), request
						.getDateSegmentIdForCopy());
				response.setIfLatestProductExist(latestProduct);
				break;

			case SaveContractBasicInfoRequest.COPY_PROCESS_CONT:
				boolean latestNote = ifLatestNoteExistForCopy(contractVO,
						request.getUser());
				response.setIfLatestNoteExist(latestNote);
				break;

			}
			addMessagesToResponse(response, messageList);
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (SevereException ex) {
			Logger.logError(ex);
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (WPDException e) {
			Logger.logError(e);
			throw new ServiceException("Exception occured while Adapter call",
					null, e);
		}
		return response;

	}
	
	/**
	 * 
	 * @param contractSysId
	 * @return
	 * @throws SevereException
	 */
	private int ifLatestProductExist(int contractSysId, int dateSegmentid)
			throws SevereException {
		ProductBO product = new ProductBO();
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		product = cob
				.retrieveLatestProductVersion(contractSysId, dateSegmentid);
		if (product != null) {
			return product.getProductKey();
		}
		return 0;
	}

	private int ifLatestProductExist(String contractId, int contractSysId,
			int productSysId) throws SevereException {
		ProductBO product = new ProductBO();
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		LegacyBuilder legacyBuilder = new LegacyBuilder();

		// start and end date of legacy contract
		Date effDate = legacyBuilder.getFirstDateSegment(
				LegacyObject.SYSTEM_CP2000, contractId).getStartDate();
		// Date expDate =
		// legacyBuilder.getLatestDateSegment(LegacyObject.SYSTEM_CP2000,
		// migrationContract.getContractId()).getEndDate();

		product = cob.retrieveLatestProductVersion(contractSysId, effDate,
				productSysId);
		if (product != null) {
			return product.getProductKey();
		}
		return 0;
	}

	/**
	 * 
	 * @param contractVO
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	private List ifLatestNoteExist(ContractVO contractVO, User user)
			throws WPDException {
		BusinessObjectManager bom = getBOM();
		DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
		LocateResults locateResults = null;
		DateSegment dateSegment = null;
		List dateSegmentList = new ArrayList();
		locateCriteria.setContractSysId(contractVO.getContractSysId());
		locateResults = bom.locate(locateCriteria, user);
		int dateSegmentCount = locateResults.getLocateResults().size();
		if (dateSegmentCount >= 0) {
			for (int i = 0; i < dateSegmentCount; i++) {
				ContractLocateResult contractLocateResult = (ContractLocateResult) locateResults
						.getLocateResults().get(i);
				if ("N".equals(contractLocateResult.getTestIndicator())) {
					int dateSegmentId = contractLocateResult.getDateSegmentId();
					ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
					notesLocateCriteria.setDateSegmentId(dateSegmentId);
					notesLocateCriteria
							.setEntityType(BusinessConstants.ATTACH_CONTRACT);
					notesLocateCriteria
							.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
					LocateResults locateResultsNotes = bom.locate(
							notesLocateCriteria, user);
					if (0 < locateResultsNotes.getTotalResultsCount()) {
						ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
						AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResultsNotes
								.getLocateResults().get(0);
						List notesList = cob.getDomainedNote(attachedNotesBO);
						if (notesList == null || notesList.size() == 0) {
							NoteBO noteBO = new NoteBO();
							noteBO.setNoteId(attachedNotesBO.getNoteId());
							noteBO.setVersion(attachedNotesBO.getVersion());
							List latestVersion = cob
									.getLatestVersionNote(noteBO);
							if (latestVersion != null
									&& latestVersion.size() != 0) {
								attachedNotesBO
										.setVersion(((NoteBO) latestVersion
												.get(0)).getVersion());
								notesList = cob
										.getDomainedNote(attachedNotesBO);
								if (notesList == null || notesList.size() == 0) {
									dateSegment = new DateSegment();
									dateSegment
											.setEffectiveDate(contractLocateResult
													.getStartDate());
									dateSegment
											.setExpiryDate(contractLocateResult
													.getEndDate());
									dateSegmentList.add(dateSegment);

								} else {
									attachedNotesBO = (AttachedNotesBO) notesList
											.get(0);
									if (BusinessConstants.ATTACH_PRODUCT
											.equals(attachedNotesBO
													.getEntityType())
											&& ((attachedNotesBO.getEntityId() == contractVO
													.getProductSysId()) || (attachedNotesBO
													.getEntityId() == contractVO
													.getOldProductSysId()))) {
										dateSegment = new DateSegment();
										dateSegment
												.setEffectiveDate(contractLocateResult
														.getStartDate());
										dateSegment
												.setExpiryDate(contractLocateResult
														.getEndDate());
										dateSegmentList.add(dateSegment);
									}
								}
							}
						}
					}
				}
			}
		}
		return dateSegmentList;
	}

	/**
	 * 
	 * @param contractVO
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	private boolean ifTestDatesegmentExist(ContractVO contractVO, User user)
			throws WPDException {
		boolean ifTestExists = false;
		DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
		LocateResults locateResults = null;
		BusinessObjectManager bom = getBusinessObjectManager();
		locateCriteria.setContractSysId(contractVO.getContractSysId());
		locateResults = bom.locate(locateCriteria, user);
		int dateSegmentCount = locateResults.getLocateResults().size();
		if (dateSegmentCount >= 0) {
			for (int i = 0; i < dateSegmentCount; i++) {
				ContractLocateResult contractLocateResult = (ContractLocateResult) locateResults
						.getLocateResults().get(i);
				if ("Y".equals(contractLocateResult.getTestIndicator())) {
					ifTestExists = true;
				}
			}
		}
		return ifTestExists;
	}

	/**
	 * 
	 * @param contract
	 * @param contractVO
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	private Contract contractCheckoutReplace(Contract contract,
			ContractVO contractVO, User user) throws WPDException {

		BusinessObjectManager bom = getBusinessObjectManager();
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		ProductBO product = new ProductBO();
		DateSegment localDateSegment = null;
		int dateSegmentKeep = 0;

		if (BusinessConstants.MSG_REPLACE_PRODUCT.equals(contractVO
				.getProductStatus())) {
			product = cob.retrieveLatestProductVersion(contract
					.getContractSysId(), contractVO.getDateSegmentSysId());
			if (product != null) {
				Date dateOld = null;
				if (contractVO.getDateEntered() == null) {
					Map paramCopyHeadingOne = new HashMap();
					paramCopyHeadingOne.put(BusinessConstants.ACTION,
							BusinessConstants.RETREIVE_SPECIFIC);
					paramCopyHeadingOne.put(BusinessConstants.DATESEGMENT_ID,
							new Integer(contractVO.getDateSegmentSysId()));
					Contract contractcopyOne = (Contract) bom.retrieve(
							contract, user, paramCopyHeadingOne);
					DateSegment ds = (DateSegment) contractcopyOne
							.getDateSegmentList().get(0);
					dateOld = ds.getEffectiveDate();
				} else {
					dateOld = contractVO.getDateEntered();
				}

				DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
				LocateResults locateResults = null;
				locateCriteria.setContractSysId(contract.getContractSysId());
				locateResults = bom.locate(locateCriteria, user);
				int dateSegmentCount = locateResults.getLocateResults().size();
				if (dateSegmentCount >= 0) {
					for (int i = 0; i < dateSegmentCount; i++) {
						ContractLocateResult contractLocateResult = (ContractLocateResult) locateResults
								.getLocateResults().get(i);
						if (("N"
								.equals(contractLocateResult.getTestIndicator()) && (0 == contractLocateResult
								.getStartDate().compareTo(dateOld)))) {
							int dateSegmentId = contractLocateResult
									.getDateSegmentId();
							contract.setBaseDateSegmentSysId(dateSegmentId);
							dateSegmentKeep = dateSegmentId;
							localDateSegment = new DateSegment();
							Map paramCopyHeading = new HashMap();
							paramCopyHeading.put(BusinessConstants.ACTION,
									BusinessConstants.RETREIVE_SPECIFIC);
							paramCopyHeading.put(
									BusinessConstants.DATESEGMENT_ID,
									new Integer(dateSegmentId));
							Contract contractcopy = (Contract) bom.retrieve(
									contract, user, paramCopyHeading);
							localDateSegment = (DateSegment) contractcopy
									.getDateSegmentList().get(0);
							localDateSegment.setProductId(product
									.getProductKey());
							Audit audit = getAudit(user);
							localDateSegment
									.setLastUpdatedUser(audit.getUser());
							localDateSegment.setLastUpdatedTimestamp(audit
									.getTime());
							localDateSegment.setOldDateSegmentId(contractVO
									.getDateSegmentSysId());
							localDateSegment.setContractId(contract
									.getContractId());
							localDateSegment.setContractSysId(contractVO
									.getContractSysId());
							bom.persist(localDateSegment, user, false);
							cob
									.replaceProductComponents(localDateSegment,
											user);

							// Deleting domained notes...
							ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
							notesLocateCriteria.setDateSegmentId(dateSegmentId);
							notesLocateCriteria
									.setEntityType(BusinessConstants.ATTACH_CONTRACT);
							notesLocateCriteria
									.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
							AttachedNotesBO attachedNotesBO = null;
							List notesList = null;

							// Deleting Domained Notes
							LocateResults locateResultNote = bom.locate(
									notesLocateCriteria, user);
							if (0 < locateResultNote.getTotalResultsCount()) {
								attachedNotesBO = (AttachedNotesBO) locateResultNote
										.getLocateResults().get(0);
								notesList = cob
										.getDomainedNote(attachedNotesBO);
								if (notesList != null
										&& notesList.size() != 0
										&& (BusinessConstants.MSG_REPLACE_PRODUCT
												.equals(contractVO
														.getProductStatus()))) {
									AttachedNotesBO noteattachmentOverrideBO = (AttachedNotesBO) notesList
											.get(0);
									noteattachmentOverrideBO
											.setEntityType(BusinessConstants.ATTACH_CONTRACT);
									noteattachmentOverrideBO
											.setEntityId(dateSegmentId);
									// calling the delete method of BOM
									bom.delete(noteattachmentOverrideBO,
											localDateSegment, user);

								}
							}
						}
					}
				}
			}
		} else {
			product.setProductKey(contractVO.getProductSysId());
		}

		DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
		locateCriteria.setContractSysId(contract.getContractSysId());
		LocateResults locateResults = bom.locate(locateCriteria, user);
		int dateSegmentCount = locateResults.getLocateResults().size();
		if (dateSegmentCount >= 0) {
			for (int i = 0; i < dateSegmentCount; i++) {
				ContractLocateResult contractLocateResult = (ContractLocateResult) locateResults
						.getLocateResults().get(i);
				if ("N".equals(contractLocateResult.getTestIndicator())) {

					// Replacing the Notes with Latest version Available
					if (BusinessConstants.MSG_REPLACE_NOTE.equals(contractVO
							.getNoteStatus())) {
						Iterator itr = contractVO.getDateSegmentList()
								.iterator();
						while (itr.hasNext()) {
							DateSegment dateSegment = (DateSegment) itr.next();
							if ((dateSegment.getEffectiveDate()
									.compareTo(contractLocateResult
											.getStartDate())) == 0) {
								ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
								notesLocateCriteria
										.setDateSegmentId(contractLocateResult
												.getDateSegmentId());
								notesLocateCriteria
										.setEntityType(BusinessConstants.ATTACH_CONTRACT);
								notesLocateCriteria
										.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
								AttachedNotesBO attachedNotesBO = null;
								LocateResults locateResultNote = bom.locate(
										notesLocateCriteria, user);
								if (0 < locateResultNote.getTotalResultsCount()) {
									attachedNotesBO = (AttachedNotesBO) locateResultNote
											.getLocateResults().get(0);
									List notesList = null;
									boolean replace = false;
									NoteBO noteBO = new NoteBO();
									noteBO.setNoteId(attachedNotesBO
											.getNoteId());
									noteBO.setVersion(attachedNotesBO
											.getVersion());
									List latestVersion = cob
											.getLatestVersionNote(noteBO);
									if (latestVersion != null
											&& latestVersion.size() != 0) {
										attachedNotesBO
												.setVersion(((NoteBO) latestVersion
														.get(0)).getVersion());
										notesList = cob
												.getDomainedNote(attachedNotesBO);
										if (notesList == null
												|| notesList.size() == 0)
											replace = true;
										else {
											AttachedNotesBO attachedNotesBO2 = (AttachedNotesBO) notesList
													.get(0);
											if ((BusinessConstants.ATTACH_PRODUCT
													.equals(attachedNotesBO
															.getEntityType()))
													&& (attachedNotesBO
															.getEntityId() == product
															.getProductKey()))
												replace = true;
										}
										if (replace == true) {
											Map paramCopyHeading = new HashMap();
											paramCopyHeading
													.put(
															BusinessConstants.ACTION,
															BusinessConstants.RETREIVE_SPECIFIC);
											paramCopyHeading
													.put(
															BusinessConstants.DATESEGMENT_ID,
															new Integer(
																	contractLocateResult
																			.getDateSegmentId()));
											Contract contractcopy = (Contract) bom
													.retrieve(contract, user,
															paramCopyHeading);
											localDateSegment = (DateSegment) contractcopy
													.getDateSegmentList()
													.get(0);
											localDateSegment
													.setContractId(contract
															.getContractId());
											localDateSegment
													.setContractSysId(contractVO
															.getContractSysId());
											localDateSegment
													.setProductStatus("N");
											if (!((BusinessConstants.STATUS_BUILDING)
													.equals(localDateSegment
															.getStatus()))
													&& !((BusinessConstants.CHECKED_OUT)
															.equals(localDateSegment
																	.getStatus()))) {
												localDateSegment = (DateSegment) bom
														.checkOut(
																localDateSegment,
																user);
											}
											AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
											noteattachmentOverrideBO
													.setNoteId(attachedNotesBO
															.getNoteId());
											noteattachmentOverrideBO
													.setEntityType(BusinessConstants.ATTACH_CONTRACT);
											noteattachmentOverrideBO
													.setEntityId(localDateSegment
															.getDateSegmentSysId());
											// calling the delete method of BOM
											bom.delete(
													noteattachmentOverrideBO,
													localDateSegment, user);
											List notesIdList = new ArrayList();
											List versionList = new ArrayList();
											notesIdList.add(attachedNotesBO
													.getNoteId());
											versionList.add(new Integer(
													attachedNotesBO
															.getVersion()));
											List notesBOList = getContractOverrideNotesObject(
													notesIdList,
													localDateSegment
															.getDateSegmentSysId(),
													product.getProductKey(),
													versionList);
											// call the persist method of the
											// bom -
											// for
											// benefitComponentNotesAttach
											bom.persist(notesBOList,
													localDateSegment, user,
													true);

										}
									}
								}
							}
						}
					}
				}
			}
		}
		contract = (Contract) bom.retrieve(contract, user);
		contract.setBaseDateSegmentSysId(dateSegmentKeep);
		return contract;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SaveTestDataRequest request)
			throws ServiceException {
		SaveTestDataResponse response = new SaveTestDataResponse();
		TestData testData = new TestData();
		if (SaveTestDataRequest.PERSIST_TEST_DATA == request.getAction()) {
			Contract contract = new Contract();
			testData.setTestDate(request.getTestDate());
			testData.setDateSegmentSysId(request.getContractKeyObject()
					.getDateSegmentId());
			contract.setContractId(request.getContractKeyObject()
					.getContractId());
			contract.setVersion(request.getContractKeyObject().getVersion());
			contract.setStatus(request.getContractKeyObject().getStatus());
			BusinessObjectManager bom = getBusinessObjectManager();
			try {
				if (request.isInsertFlag()) {
					bom.persist(testData, contract, request.getUser(), true);
				} else {
					bom.persist(testData, contract, request.getUser(), false);
				}
			} catch (SecurityException exception) {
				ErrorMessage em = new ErrorMessage(
						BusinessConstants.MSG_SECURITY_INVALID);
				em.setParameters(new String[] { (String) exception
						.getLogParameters().get(1) });
				response.addMessage(em);
				List logParameters = new ArrayList(2);
				logParameters.add(request);
				ServiceException se = new ServiceException(
						"Security Exception while processing service",
						logParameters, exception);
				Logger.logException(se);
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
		} else {
			ContractAdapterManager adapterManager = new ContractAdapterManager();
			testData.setDateSegmentSysId(request.getContractKeyObject()
					.getDateSegmentId());
			try {
				testData = adapterManager.retreiveTestData(testData);
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			if (testData != null)
				response.setTestDate(testData.getTestDate());
		}
		response.setSuccess(true);
		response.addMessage(new InformationalMessage(
				BusinessConstants.TEST_DATE_ADDED));
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveBaseContractRequest request)
			throws ServiceException {
		RetrieveBaseContractResponse response = new RetrieveBaseContractResponse();
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		List baseContracts = null;
		try {
			if (request.getAction() == RetrieveBaseContractRequest.BASECONTRACTS_FOR_MIGRATION) {
				LegacyBuilder legacyBuilder = new LegacyBuilder();
				// start and end date of legacy contract
				Date effDate = legacyBuilder.getFirstDateSegment(
						LegacyObject.SYSTEM_CP2000, request.getContractId())
						.getStartDate();
				// Date expDate =
				// legacyBuilder.getLatestDateSegment(LegacyObject.SYSTEM_CP2000,
				// request.getContractId()).getEndDate();
				Date expDate = DateUtil.getDefaultEndDate();
				/* START CARS */
				baseContracts = adapterManager.getBaseContracts(request
						.getLineOfBusiness(), request.getBusinessEntity(),
						request.getBusinessGroup(), request
								.getProductParentSysId(), effDate, expDate,
						request.getMarketBusinessUnit());
				/* END CARS */
			} else {
				/* START CARS */
				baseContracts = adapterManager.getBaseContracts(request
						.getLineOfBusiness(), request.getBusinessEntity(),
						request.getBusinessGroup(), request
								.getMarketBusinessUnit());
				/* END CARS */
			}
		} catch (SevereException ex) {
			throw new ServiceException("Adapter exception", null, ex);
		}
		if (null != baseContracts) {
			response.setBaseContractList(baseContracts);
		}
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveBaseContractDateRequest request)
			throws ServiceException {
		RetrieveBaseContractDateResponse response = new RetrieveBaseContractDateResponse();
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		List baseContractDates = null;
		switch (request.getAction()) {
		case 1:
			try {
				DateSegment dateSegment = new DateSegment();
				dateSegment.setContractSysId(request.getContractSysId());
				dateSegment.setDateSegmentType(request.getDatafeedIndicator());
				baseContractDates = adapterManager
						.getBaseContractDates(dateSegment);
			} catch (SevereException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			}
			break;

		case 2:
			try {
				DateSegment dateSegment = new DateSegment();
				dateSegment.setContractSysId(request.getContractSysId());
				dateSegment.setDateSegmentType(request.getDatafeedIndicator());
				dateSegment.setIsModified(request.getTestProdIndicator());
				baseContractDates = adapterManager
						.getModifiedContractDatesesgments(dateSegment);
			} catch (SevereException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			}
			break;
		}
		response.setBaseContractDateList(baseContractDates);
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveBasicInfoRequest request)
			throws ServiceException {
		Contract contract = new Contract();
		boolean lockAquired = true;
		contract = getContract(request);
		DateSegment specificInfoDateSegment = new DateSegment();
		RetrieveBasicInfoResponse response = null;
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		BusinessObjectManager bom = getBOM();
		response = (RetrieveBasicInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BASICINFO_RESPONSE);
		Map params = new HashMap();
		params.put(BusinessConstants.ACTION,
				BusinessConstants.RETREIVE_SPECIFIC);
		params.put(BusinessConstants.DATESEGMENT_ID, new Integer(request
				.getDateSegmentId()));
		try {
			// specificInfoDateSegment=contractBusinessObjectBuilder.retrieveContractSpecificInfoDetails(retrieveContractSpecificInfoRequest.getDateSegmentId());

			contract = (Contract) bom.retrieve(contract, request.getUser(),
					params);
			if (request.isEditMode()) {
				lockAquired = bom.lock(contract, request.getUser());
				if (lockAquired) { // to lock the date segments
					List dateList = retrieveCheckInDateSegments(contract
							.getContractSysId());
					Iterator dateitr = dateList.iterator();
					while (dateitr.hasNext()) {
						DateSegment lockDateSegment = (DateSegment) dateitr
								.next();
						bom.lock(lockDateSegment, request.getUser());
					}
				}
			}
			List messages = new ArrayList();
			response.setLockAquired(lockAquired);
			if (lockAquired) { // to check if lock cannot be acquired.
				response.setContract((Contract) bom.retrieve(contract, request
						.getUser()));
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						BusinessConstants.ENTITY_TYPE_CONTRACT, contract
								.getParentSysId()));
				DateSegment dateSegment = ((DateSegment) contract
						.getDateSegmentList().get(0));
				List dateSegmentList = new ArrayList();
				dateSegmentList.add(dateSegment);
				response.getContract().setDateSegmentList(dateSegmentList);

			} else {
				InformationalMessage inform = new InformationalMessage(
						BusinessConstants.CONTRACT_LOCKED);
				inform.setParameters(new String[] { contract.getContractId() });
				messages.add(inform);
				response.setMessages(messages);
			}
			if (request.isMigCompletFlag()) {
				int contractSysID = contract.getContractSysId();
				this.checkMarkedForDeletionProduct(request.getDateSegmentId(),
						messages);
				this.checkMarkedForDeletionNotes(contractSysID, messages);
				response.setMessages(messages);
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * 
	 * @param reserveContractId
	 * @param userId
	 * @throws SevereException
	 */
	private void reserveContract(String reserveContractId, User user)
			throws SevereException {
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		ReserveContractId reservedContractIdInfo = new ReserveContractId();
		reservedContractIdInfo.setContractId(reserveContractId);
		adapterManager.retrieveReserveContract(reservedContractIdInfo);
		reservedContractIdInfo.setStatus("N");
		Audit audit = getAudit(user);
		reservedContractIdInfo.setLastUpdatedUser(audit.getUser());
		reservedContractIdInfo.setLastUpdatedTimeStamp(audit.getTime());
		adapterManager.updateReserveContract(reservedContractIdInfo);
	}

	/**
	 * 
	 * @return
	 * @throws SevereException
	 */
	private SystemContractId generateContractId() throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		return builder.generateContractId();
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Contract getContract(ContractRequest request) {
		ContractKeyObject contractKeyObject = request.getContractKeyObject();
		Contract contract = new Contract();
		if (contractKeyObject == null) {
			Logger.logError("Contract Key Object is null in the request");
			return null;
		}
		contract.setContractSysId(contractKeyObject.getContractSysId());
		contract.setContractId(contractKeyObject.getContractId());
		contract.setVersion(contractKeyObject.getVersion());
		contract.setStatus(contractKeyObject.getStatus());
		contract.setParentSysId(contractKeyObject.getContractParentSysId());

		return contract;
	}

	/**
	 * 
	 * @param contractVO
	 * @param contract
	 */
	private void setBasicInfoToContract(ContractVO contractVO, Contract contract) {
		contract.setContractId(contractVO.getContractId());
		contract.setContractSysId(contractVO.getContractSysId());
		contract.setParentSysId(contractVO.getParentSysId());
		contract.setDateSegmentList(contractVO.getDateSegmentList());
		contract.setContractType(contractVO.getContractType());
		contract.setBusinessDomains(contractVO.getDomainList());
		contract.setProductStatus(contractVO.getProductStatus());
		if (BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contract
				.getContractType())) {
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
			contract.setBaseContractId(contractVO.getBaseContractId());
			contract.setBaseContractDate(contractVO.getBaseContractDate());
		} else if (BusinessConstants.STANDARD_TYPE.equals(contract
				.getContractType())
				&& BusinessConstants.VALUE_ZERO != contractVO
						.getBaseDateSegmentSysId()) {
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
			contract.setBaseContractId(contractVO.getBaseContractId());
			contract.setBaseContractDate(contractVO.getBaseContractDate());
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
		}
		contract.setVersion(contractVO.getVersion());
		contract.setStatus(contractVO.getStatus());
	}

	/**
	 * Method to set the list of messages to response.
	 * 
	 * @param response
	 *            WPDResponse
	 * @param messages
	 *            List.
	 */
	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}

	/**
	 * Method to get Business Object Manager.
	 * 
	 * @return BusinessObjectManager.
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}

	/**
	 * saves the specific information
	 * 
	 * @param saveContractSpecificInfoRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			SaveContractSpecificInfoRequest saveContractSpecificInfoRequest)
			throws ServiceException {
		SaveContractSpecificInfoResponse saveContractSpecificInfoResponse = null;
		try {
			Map params = new HashMap();
			BusinessObjectManager bom = getBusinessObjectManager();
			saveContractSpecificInfoResponse = (SaveContractSpecificInfoResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_SPECIFICINFO_RESPONSE);

			// gets contract object
			Contract contract = getContract(saveContractSpecificInfoRequest);

			User user = saveContractSpecificInfoRequest.getUser();
			// DateSegment dateSegmentVO =
			// getDateSegment(saveContractSpecificInfoRequest);
			DateSegment dateSegmentVO = saveContractSpecificInfoRequest
					.getDateSegmentVO();

			params.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
					saveContractSpecificInfoRequest.getDateSegmentVO()
							.getDateSegmentSysId()));

			// specificInfoDateSegment=contractBusinessObjectBuilder.retrieveContractSpecificInfoDetails(retrieveContractSpecificInfoRequest.getDateSegmentId());

			// explicitly calling the retrieve
			contract = (Contract) bom.retrieve(contract,
					saveContractSpecificInfoRequest.getUser(), params);
			DateSegment dateSegmnt = (DateSegment) contract
					.getDateSegmentList().get(0);
			// copying adapted information
			dateSegmentVO.setRegulatoryAgency(dateSegmnt.getRegulatoryAgency());
			dateSegmentVO.setComplianceStatus(dateSegmnt.getComplianceStatus());
			dateSegmentVO.setProjectNameCode(dateSegmnt.getProjectNameCode());
			dateSegmentVO.setContractTermDate(dateSegmnt.getContractTermDate());
			// if the multiplan ind or performance guarantee value is
			// null,updating the default value as 'N'
			if (null == dateSegmnt.getMultiPlanIndicator()
					|| !"".equals(dateSegmnt.getMultiPlanIndicator())) {
				dateSegmentVO.setMultiPlanIndicator("N");
			} else {
				dateSegmentVO.setMultiPlanIndicator(dateSegmnt
						.getMultiPlanIndicator());
			}
			if (null == dateSegmnt.getPerformanceGuarantee()
					|| "".equals(dateSegmnt.getPerformanceGuarantee())) {
				dateSegmentVO.setPerformanceGuarantee("N");
			} else {
				dateSegmentVO.setPerformanceGuarantee(dateSegmnt
						.getPerformanceGuarantee());
			}
			dateSegmentVO.setSalesMarketDate(dateSegmnt.getSalesMarketDate());
			dateSegmentVO.setContractId(contract.getContractId());
			dateSegmentVO.setContractSysId(contract.getContractSysId());
			// performs the update
			bom.persist(dateSegmentVO, user, false);

			ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();

			// checks if the productComponents have to be copied
			if (saveContractSpecificInfoRequest.isCopyNeeded())
				contractBuilder.copyProductComponents(dateSegmentVO);

			ProviderSpecialityCodeBO providerSpecialityCodeBO = new ProviderSpecialityCodeBO();
			providerSpecialityCodeBO.setDateSegmentSysId(dateSegmentVO
					.getDateSegmentSysId());
			// dummy value set to the code as it is the key value in the BO
			// and will fail the delete otherwise
			providerSpecialityCodeBO.setSpecialityCode("dummy");
			// deleting profider speciality code info
			bom.delete(providerSpecialityCodeBO, dateSegmentVO, user);

			if (dateSegmentVO.getProviderSpecCodeList() != null
					&& dateSegmentVO.getProviderSpecCodeList().size() > 0) {
				for (Iterator iter = dateSegmentVO.getProviderSpecCodeList()
						.iterator(); iter.hasNext();) {

					providerSpecialityCodeBO.setSpecialityCode(iter.next()
							.toString());
					bom.persist(providerSpecialityCodeBO, dateSegmentVO, user,
							true);

				}
			}
			saveContractSpecificInfoResponse.setSuccess(true);

			if (saveContractSpecificInfoRequest.isSave())
				saveContractSpecificInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_SPECIFICINFO_SAVE_SUCCESS));
			else
				saveContractSpecificInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_SPECIFICINFO_UPDATE_SUCCESS));

			params.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
					saveContractSpecificInfoRequest.getDateSegmentVO()
							.getDateSegmentSysId()));

			// specificInfoDateSegment=contractBusinessObjectBuilder.retrieveContractSpecificInfoDetails(retrieveContractSpecificInfoRequest.getDateSegmentId());

			// explicitly writing the retrieve
			contract = (Contract) bom.retrieve(contract,
					saveContractSpecificInfoRequest.getUser(), params);

			if (contract != null) {
				saveContractSpecificInfoResponse.setContract(contract);
				if (contract.getDateSegmentList().size() != 0) {
					DateSegment dateSegmentToSet = (DateSegment) contract
							.getDateSegmentList().get(0);
					saveContractSpecificInfoResponse
							.setDateSegment((DateSegment) contract
									.getDateSegmentList().get(0));
				}
			}

			// explicitly writing the retrieve
			saveContractSpecificInfoResponse.setContract(contract);
			saveContractSpecificInfoResponse.setDomainDetail(DomainUtil
					.retrieveDomainDetailInfo(
							BusinessConstants.ENTITY_TYPE_CONTRACT, contract
									.getContractSysId()));

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			saveContractSpecificInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractSpecificInfoRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractSpecificInfoRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractSpecificInfoRequest);
			String logMessage = "Failed while processing saveContractSpecificInfoRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return saveContractSpecificInfoResponse;
	}

	/**
	 * Service method for Retrieving overrided benfit Defenitions.
	 * 
	 * @param request
	 *            RetrieveProductBenefitDefinitionRequest
	 * @return RetrieveProductBenefitDefinitionResponse
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveContractBenefitDefinitionRequest request)
			throws ServiceException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage",
				"ContractBusinessService",
				"execute(RetrieveContractBenefitDefinitionRequest request)"));

		RetrieveContractBenefitDefinitionResponse retrieveContractBenefitDefinitionResponse = null;
		try {

			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			int benefitId;
			int benefitComponentId;
			List messageList = null;
			ErrorMessage message = null;
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = null;
			benefitId = request.getBenefitId();
			benefitComponentId = request.getBenefitComponentId();

			retrieveContractBenefitDefinitionResponse = (RetrieveContractBenefitDefinitionResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BENEFIT_DEFINITION_RESPONSE);
			if (!request.isDuplicateRefPopup()) {
				ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria = new ContractBenefitDefinitionLocateCriteria();

				contractBenefitDefinitionLocateCriteria.setBenefitId(benefitId);
				contractBenefitDefinitionLocateCriteria
						.setDateSegmentId(request.getContractKeyObject()
								.getDateSegmentId());
				contractBenefitDefinitionLocateCriteria
						.setBenefitComponentId(benefitComponentId);
				contractBenefitDefinitionLocateCriteria.setAction(request
						.getAction());

				BusinessObjectManager bom = getBusinessObjectManager();
				LocateResults locateResults = bom.locate(
						contractBenefitDefinitionLocateCriteria, request
								.getUser());
				retrieveContractBenefitDefinitionResponse
						.setBenefitDefinitionsList(locateResults
								.getLocateResults());
				if (null != locateResults.getLocateResults()
						&& !locateResults.getLocateResults().isEmpty()) {
					contractBenefitDefinitionLocateCriteria.setAction(3);
					LocateResults criteriaResults = bom.locate(
							contractBenefitDefinitionLocateCriteria, request
									.getUser());
					if (null != criteriaResults.getLocateResults()
							&& !criteriaResults.getLocateResults().isEmpty()) {
						retrieveContractBenefitDefinitionResponse
								.setTierCriteriaList(criteriaResults
										.getLocateResults());
						List tierSysIdList = getTierList(criteriaResults
								.getLocateResults());
						contractBenefitDefinitionLocateCriteria.setAction(4);
						contractBenefitDefinitionLocateCriteria
								.setTierSysIdList(tierSysIdList);
						LocateResults lvlLineResults = bom.locate(
								contractBenefitDefinitionLocateCriteria,
								request.getUser());

						if (null != lvlLineResults.getLocateResults()
								&& !lvlLineResults.getLocateResults().isEmpty()) {
							retrieveContractBenefitDefinitionResponse
									.setBenefitLvlLineList(lvlLineResults
											.getLocateResults());
						}
					}
				}
				if (locateResults.getLocateResults().size() == 0) {
					messageList = new ArrayList(2);
					messageList.add(new InformationalMessage(
							BusinessConstants.MSG_PRDCT_BEN_DFN_NOT_FOUND));
					addMessagesToResponse(
							retrieveContractBenefitDefinitionResponse,
							messageList);
				}
				Logger.logInfo("Returning  from execute method for request="
						+ request.getClass().getName());
			} else {
				messageList = new ArrayList(2);
				contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
				List allDuplicateReferenceList = new ArrayList();
				List dateSegmentList = contractBusinessObjectBuilder
						.retrieveDateSegments(request.getContractId());
				ContractQuestUniqueReferenceBO contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
						.get(0);
				String productName = contractDateSegmentsBO.getProductName();
				allDuplicateReferenceList = contractBusinessObjectBuilder
						.getDuplicateUniqueReferences(dateSegmentList);
				retrieveContractBenefitDefinitionResponse
						.setProductName(productName);
				retrieveContractBenefitDefinitionResponse
						.setContractDuplicateReferenceList(allDuplicateReferenceList);
				message = new ErrorMessage(
						BusinessConstants.UNIQUE_REF_VALIDATION);
				messageList.add(message);
				retrieveContractBenefitDefinitionResponse
						.setMessages(messageList);

			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractBenefitDefinitionResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		Logger.logInfo(th.endPerfLogging());
		return retrieveContractBenefitDefinitionResponse;

	}

	private List getTierList(List criteriaListFrmDB) {

		List tierSysIdList = new ArrayList();

		TierDefinitionBO oldTierDef = (TierDefinitionBO) criteriaListFrmDB
				.get(0);
		Integer oldCntrctTierId = new Integer(oldTierDef.getTierSysId());

		tierSysIdList.add(oldCntrctTierId);

		for (int i = 1; i < criteriaListFrmDB.size(); i++) {

			TierDefinitionBO newTierDef = (TierDefinitionBO) criteriaListFrmDB
					.get(i);
			Integer newCntrctTierId = new Integer(newTierDef.getTierSysId());

			if (oldCntrctTierId.intValue() != newCntrctTierId.intValue()) {

				tierSysIdList.add(newCntrctTierId);
			}
			oldCntrctTierId = newCntrctTierId;
		}

		return tierSysIdList;
	}

	/**
	 * 
	 * @return
	 */
	public BusinessObjectManager getBOM() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}

	/**
	 * 
	 * @param contractLocateCriteriaVO
	 * @param contractLocateCriteria
	 */
	private void setSearchLocateCriteria(
			ContractLocateCriteriaVO contractLocateCriteriaVO,
			ContractLocateCriteria contractLocateCriteria) {

		contractLocateCriteria.setContractId(contractLocateCriteriaVO
				.getContractId());
		contractLocateCriteria.setLob(contractLocateCriteriaVO.getLob());
		contractLocateCriteria.setBusinessEntity(contractLocateCriteriaVO
				.getBusinessEntity());
		contractLocateCriteria.setGroupName(contractLocateCriteriaVO
				.getGroupName());
		/* START CARS */
		contractLocateCriteria.setMarketBusinessUnit(contractLocateCriteriaVO
				.getMarketBusinessUnit());
		/* END CARS */
		contractLocateCriteria.setContractType(contractLocateCriteriaVO
				.getContractType());
		contractLocateCriteria.setStartDate(contractLocateCriteriaVO
				.getStartDate());
		contractLocateCriteria
				.setEndDate(contractLocateCriteriaVO.getEndDate());
		contractLocateCriteria
				.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE_CONTRACT);

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ContractSearchRequest request)
			throws ServiceException {
		ContractSearchResponse contractSearchResponse = null;
		try {

			contractSearchResponse = (ContractSearchResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_SEARCH_RESPONSE);
			BusinessObjectManager bom = getBusinessObjectManager();

			ContractLocateCriteria contractLocateCriteria;

			LocateResults locateResults = null;
			int action = request.getAction();
			switch (action) {
			case ContractSearchRequest.SEARCH_ALL_VERSION:
				ContractAllVersionsLocateCriteria contractLocateCriteriaAll = new ContractAllVersionsLocateCriteria();
				contractLocateCriteriaAll
						.setContractId(request.getContractId());

				LocateResults locateResultsPrevVersion = bom.locate(
						contractLocateCriteriaAll, request.getUser());
				List versionList = locateResultsPrevVersion.getLocateResults();
				contractSearchResponse.setSearchResultList(versionList);
				contractSearchResponse.setSuccess(true);

				break;

			case ContractSearchRequest.SEARCH_CONTRACT:

				contractLocateCriteria = new ContractLocateCriteria();
				ContractLocateCriteriaVO contractLocateCriteriaVO = request
						.getContractLocateCriteriaVO();
				setSearchLocateCriteria(contractLocateCriteriaVO,
						contractLocateCriteria);
				Logger.logInfo(" ^^^^^^^^^^^^^ Start time - contract details. :"+ Calendar.getInstance().getTime());
				locateResults = bom.locate(contractLocateCriteria, request
						.getUser());
				Logger.logInfo(" ^^^^^^^^^^^^^ End time - contract details. :"+ Calendar.getInstance().getTime());
				if (null != locateResults
						&& locateResults.getLocateResults().size() > 0) {
					Logger.logInfo(" ^^^^^^^^^^^^^ Start time - domain details. :"+ Calendar.getInstance().getTime());
					for (int i = 0; i < locateResults.getLocateResults().size(); i++) {

						ContractLocateResult locResult = (ContractLocateResult) locateResults
								.getLocateResults().get(i);

						DomainDetail domainDetail = getDomainDetail(DomainUtil
								.retrieveAssociatedDomains(
										BusinessConstants.ENTITY_TYPE_CONTRACT,
										locResult.getContractParentSysId()));
						// List lobList=new ArrayList();
						List lobList = domainDetail.getLineOfBusiness();
						if (null != lobList) {
							locResult.setLob(WPDStringUtil.getTildaString(lobList));
						}

					}
					Logger.logInfo(" ^^^^^^^^^^^^^ End time - domain details. :"+ Calendar.getInstance().getTime());
				}

				if (locateResults.getLocateResults().size() >= 50) {
					contractSearchResponse.addMessage(new InformationalMessage(
							BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
				} else if (locateResults.getLocateResults().size() <= 0) {
					contractSearchResponse.addMessage(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_NOT_FOUND));
				}
				contractSearchResponse.setSearchResultList(locateResults
						.getLocateResults());
				contractSearchResponse.setSuccess(true);
				break;
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			contractSearchResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing ContractSearchRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return contractSearchResponse;

	}

	/**
	 * 
	 * @param domains
	 * @return
	 */
	private DomainDetail getDomainDetail(List domains) {
		Map lobMap = new HashMap();
		Map beMap = new HashMap();
		Map bgMap = new HashMap();
		DomainDetail domainDetail = new DomainDetail();
		domainDetail.setLineOfBusiness(new ArrayList());
		domainDetail.setBusinessEntity(new ArrayList());
		domainDetail.setBusinessGroup(new ArrayList());
		domainDetail.setDomainList(domains);
		for (int i = 0; i < domains.size(); i++) {
			Domain domain = (Domain) domains.get(i);
			if (lobMap.get(domain.getLineOfBusiness()) == null) {
				DomainItem domainItem = new DomainItem();
				domainItem.setItemId(domain.getLineOfBusiness());
				domainItem.setItemDesc(domain.getLineOfBusinessDesc());
				lobMap.put(domainItem.getItemId(), domainItem);
				domainDetail.getLineOfBusiness().add(domainItem);
			}
			if (beMap.get(domain.getBusinessEntity()) == null) {
				DomainItem domainItem = new DomainItem();
				domainItem.setItemId(domain.getBusinessEntity());
				domainItem.setItemDesc(domain.getBusinessEntityDesc());
				beMap.put(domainItem.getItemId(), domainItem);
				domainDetail.getBusinessEntity().add(domainItem);
			}
			if (bgMap.get(domain.getBusinessGroup()) == null) {
				DomainItem domainItem = new DomainItem();
				domainItem.setItemId(domain.getBusinessGroup());
				domainItem.setItemDesc(domain.getBusinessGroupDesc());
				bgMap.put(domainItem.getItemId(), domainItem);
				domainDetail.getBusinessGroup().add(domainItem);
			}
		}
		return domainDetail;
	}

	/**
	 * @param contract
	 * @return List
	 * 
	 */
	private List retrieveValidStatusDatesegments(int contractSysId,
			String testIndicator) throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		return builder.retrieveValidStatusDatesegments(contractSysId,
				testIndicator);
	}

	private List retrieveCheckInDateSegments(int contractSysId)
			throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		return builder.retrieveCheckInDateSegments(contractSysId);
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param DeleteContractRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(DeleteContractRequest deleteContractRequest)
			throws ServiceException {
		Logger
				.logInfo("ContractBusinessService - Entering execute(): Contract Delete");
		DeleteContractResponse deleteContractResponse = null;
		try {

			List messageList = new ArrayList();
			deleteContractResponse = (DeleteContractResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.DELETE_CONTRACT_RESPONSE);
			if (("SCHEDULED_FOR_TEST".equals(deleteContractRequest
					.getContractKeyObject().getStatus()))
					&& (isDataFeedRunning())) {
				messageList.add(new InformationalMessage(
						BusinessConstants.DATAFEED_RUNNING));
			} else {
				Contract contract = getContract(deleteContractRequest);
				DateSegment dateSegment = new DateSegment();
				dateSegment.setDateSegmentSysId(deleteContractRequest
						.getContractDateSegmentSysId());
				int action = deleteContractRequest.getAction();
				ContractAdapterManager adapterManager = new ContractAdapterManager();
				// List dateSegmentList = new ArrayList();
				List dateSegmentList = adapterManager
						.getAllDateSegments(contract.getContractSysId());
				Iterator dt_itr = dateSegmentList.iterator();
				// setting effective date into date segment object
				while (dt_itr.hasNext()) {
					ContractLocateResult dt_seg = (ContractLocateResult) dt_itr
							.next();
					if (deleteContractRequest.getContractDateSegmentSysId() == dt_seg
							.getDateSegmentId()) {
						dateSegment.setContractId(dt_seg.getContractId());
						dateSegment.setEffectiveDate(dt_seg.getStartDate());
						dateSegment.setDateSegmentType(dt_seg
								.getTestIndicator());
					}
				}
				String contractId = contract.getContractId();
				boolean delete = true;
				switch (action) {
				case DeleteContractRequest.SELECTED_DATE_SEGMENT:
					if (null != dateSegmentList) {
						if (dateSegmentList.size() == 1) {
							if (ifPartialDateSegmentExist(contract
									.getContractId())
									&& contract.getVersion() == 1) { // to
								// check
								// if
								// partial
								// date
								// segments
								// exist
								messageList
										.add(new ErrorMessage(
												BusinessConstants.PARTIAL_DATESEGMENT_EXIST));
								delete = false;
								break;
							}
							getBOM().delete(contract,
									deleteContractRequest.getUser());
							if (!(BusinessConstants.MSG_MARKED_FOR_DELETION
									.equals(contract.getStatus()))) {
								// code goes
								updateContractStatus(contract.getContractId(),
										deleteContractRequest);
								// updateReserveContract(contract,
								// deleteContractRequest);
							} else {
								dateSegmentList = adapterManager
										.retrieveCheckInDateSegments(contract
												.getContractSysId());
								Iterator itr = dateSegmentList.iterator();
								while (itr.hasNext()) {
									DateSegment datsegment = (DateSegment) itr
											.next();
									new LockManager().unlock(dateSegment,
											deleteContractRequest.getUser());
								}
							}
						} else {
							Iterator itr = dateSegmentList.iterator();
							int count = 0;
							ContractLocateResult selectedDateSegment = null;
							while (itr.hasNext()) {
								ContractLocateResult dgsgmt = (ContractLocateResult) itr
										.next();
								if (dgsgmt.getTestIndicator().equals("N")) {
									count++;
									selectedDateSegment = dgsgmt;
								}
							}
							if ((count == 1)
									&& (selectedDateSegment.getDateSegmentId() == dateSegment
											.getDateSegmentSysId())) {
								messageList
										.add(new ErrorMessage(
												BusinessConstants.SINGLE_REGULAR_DELETE));
								delete = false;
								break;
							}
							if (ifFirstDateSegment(deleteContractRequest
									.getContractDateSegmentSysId(),
									deleteContractRequest.getContractId())
									&& ifPartialDateSegmentExist(contract
											.getContractId())
									&& "N".equals(dateSegment
											.getDateSegmentType())) { // to
								// check
								// if
								// partial
								// date
								// segments
								// exist
								messageList
										.add(new ErrorMessage(
												BusinessConstants.PARTIAL_DATESEGMENT_EXIST));
								delete = false;
								break;
							}
							new ContractBusinessObjectBuilder().delete(
									dateSegment, contract, this
											.getAudit(deleteContractRequest
													.getUser()));
							updateDateSegmentStartAndEndDateAfterDelete(
									dateSegment, dateSegmentList,
									deleteContractRequest.getUser());
						}
					}
					break;
				case DeleteContractRequest.ALL_DATE_SEGMENTS:
					if (ifPartialDateSegmentExist(contract.getContractId())
							&& contract.getVersion() == 1) { // to check if
						// partial date
						// segments
						// exist
						messageList.add(new ErrorMessage(
								BusinessConstants.PARTIAL_DATESEGMENT_EXIST));
						delete = false;
						break;
					}
					getBOM().delete(contract, deleteContractRequest.getUser());
					if (!(BusinessConstants.MSG_MARKED_FOR_DELETION
							.equals(contract.getStatus()))) { // update the
						// contract id
						// pool
						// code goes
						updateContractStatus(contract.getContractId(),
								deleteContractRequest);

					} else { // unlock the date segments having lock
						dateSegmentList = adapterManager
								.retrieveCheckInDateSegments(contract
										.getContractSysId());
						Iterator itr = dateSegmentList.iterator();
						while (itr.hasNext()) {
							DateSegment datesegmentUnlock = (DateSegment) itr
									.next();
							new LockManager().unlock(datesegmentUnlock,
									deleteContractRequest.getUser());
						}
					}

					break;

				}
				if (delete) {
					adapterManager.deleteContractStatus(contractId,
							deleteContractRequest.getUser().getUserId());
					InformationalMessage informationalMessage = new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_DELETE_SUCCESS);
					informationalMessage
							.setParameters(new String[] { contractId });
					messageList.add(informationalMessage);
				}
			}
			addMessagesToResponse(deleteContractResponse, messageList);
			deleteContractResponse.setSuccess(true);

			Logger
					.logInfo("ContractBusinessService - Returning execute(): Contract Delete");

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			deleteContractResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return deleteContractResponse;
	}

	private boolean ifPartialDateSegmentExist(String contractId)
			throws SevereException {

		LegacyBuilder legacyBuilder = new LegacyBuilder();
		return legacyBuilder.ifPartialDateSegmentExist(contractId);
	}

	private boolean ifFirstDateSegment(int dateSegmentId, int contractId)
			throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		return builder.ifFirstDateSegment(dateSegmentId, contractId);
	}

	/**
	 * This method is used to adjust the effective and expiry dates while
	 * deleting datesegments
	 * 
	 * @param dateSegment
	 * @param mainDateSegmentList
	 * @param user
	 * @throws SevereException
	 * @throws WPDException
	 */
	private void updateDateSegmentStartAndEndDateAfterDelete(
			DateSegment dateSegment, List mainDateSegmentList, User user)
			throws SevereException, WPDException {

		int deletedDateSegmentID = dateSegment.getDateSegmentSysId();
		int updateDateSegmentId = 0;
		List regularDateSegmentList = null;
		List testDateSegmentList = null;
		if (null != mainDateSegmentList) {
			regularDateSegmentList = new ArrayList(mainDateSegmentList.size());
			testDateSegmentList = new ArrayList(mainDateSegmentList.size());
		}
		List dateSegmentList;

		if (isDateSegmentRegular(deletedDateSegmentID, mainDateSegmentList,
				regularDateSegmentList, testDateSegmentList)) {
			dateSegmentList = new ArrayList(regularDateSegmentList);
		} else {
			dateSegmentList = new ArrayList(testDateSegmentList);
		}

		ContractLocateResult locateResult;

		int totalDateSegment = dateSegmentList.size();
		locateResult = (ContractLocateResult) dateSegmentList.get(0);

		// if first ds is deleted do nothing
		if (locateResult.getDateSegmentId() != deletedDateSegmentID) {
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

			// last Datesegment
			locateResult = (ContractLocateResult) dateSegmentList
					.get(totalDateSegment - 1);
			DateSegmentUpdateAfterDeleteBO updateAfterDeleteBO = new DateSegmentUpdateAfterDeleteBO();
			if (locateResult.getDateSegmentId() == deletedDateSegmentID) {
				// update end date of last-1 ds with 12/31/9999
				updateAfterDeleteBO.setExpiryDate(locateResult.getEndDate());
				locateResult = (ContractLocateResult) dateSegmentList
						.get(totalDateSegment - 2);
				if (!((BusinessConstants.STATUS_BUILDING).equals(locateResult
						.getDateSegmentStatus()))
						&& !((BusinessConstants.CHECKED_OUT)
								.equals(locateResult.getDateSegmentStatus()))) {
					updateDateSegmentId = checkoutDateSegment(locateResult,
							user);
				} else {
					updateDateSegmentId = locateResult.getDateSegmentId();
				}
				updateAfterDeleteBO.setDateSegmentSysId(updateDateSegmentId);
				updateAfterDeleteBO.setEffectiveDate(locateResult
						.getStartDate());
				// updateAfterDeleteBO.setExpiryDate(dateSegment.getExpiryDate());
				Audit audit = getAudit(user);
				updateAfterDeleteBO.setLastUpdatedUser(audit.getUser());
				updateAfterDeleteBO.setLastUpdatedTimeStamp(audit.getTime());
				builder.updateAfterDeleteDateSegment(updateAfterDeleteBO);
			} else {
				// update end date of ds just before deleted ds with end date
				// of deleted ds
				ContractLocateResult previousLocateResult = null;
				for (java.util.Iterator dateSegmentListItr = dateSegmentList
						.iterator(); dateSegmentListItr.hasNext();) {
					locateResult = (ContractLocateResult) dateSegmentListItr
							.next();

					if (locateResult.getDateSegmentId() == deletedDateSegmentID) {
						updateAfterDeleteBO
								.setEffectiveDate(previousLocateResult
										.getStartDate());

						if (!((BusinessConstants.STATUS_BUILDING)
								.equals(previousLocateResult
										.getDateSegmentStatus()))
								&& !((BusinessConstants.CHECKED_OUT)
										.equals(previousLocateResult
												.getDateSegmentStatus()))) {
							updateDateSegmentId = checkoutDateSegment(
									previousLocateResult, user);
						} else {
							updateDateSegmentId = previousLocateResult
									.getDateSegmentId();
						}
						updateAfterDeleteBO
								.setDateSegmentSysId(updateDateSegmentId);
						updateAfterDeleteBO.setExpiryDate(locateResult
								.getEndDate());
						Audit audit = getAudit(user);
						updateAfterDeleteBO.setLastUpdatedUser(audit.getUser());
						updateAfterDeleteBO.setLastUpdatedTimeStamp(audit
								.getTime());
						builder
								.updateAfterDeleteDateSegment(updateAfterDeleteBO);
						break;
					}// end if

					// previous ds value for updating
					previousLocateResult = locateResult;
				}// end for
			}// end else
		}

	}

	private int checkoutDateSegment(ContractLocateResult locateResult, User user)
			throws WPDException {
		DateSegment dateSegment = new DateSegment();
		dateSegment.setContractId(locateResult.getContractId());
		dateSegment.setContractSysId(locateResult.getContractKey());
		dateSegment.setDateSegmentSysId(locateResult.getDateSegmentId());
		dateSegment.setEffectiveDate(locateResult.getStartDate());
		dateSegment.setStatus(locateResult.getDateSegmentStatus());
		dateSegment.setVersion(locateResult.getDateSegmentVersion());
		dateSegment.setDateSegmentType(locateResult.getTestIndicator());
		dateSegment.setProductStatus("N");
		dateSegment = (DateSegment) getBOM().checkOut(dateSegment, user);
		return dateSegment.getDateSegmentSysId();
	}

	private boolean isDateSegmentRegular(int dateSegment, List totalList,
			List regularList, List testList) {
		boolean returnFlag = true;
		for (java.util.Iterator totalListItr = totalList.iterator(); totalListItr
				.hasNext();) {

			ContractLocateResult locResult = (ContractLocateResult) totalListItr
					.next();

			if (locResult.getTestIndicator().equals("N")) {
				regularList.add(locResult);
			} else {
				if (locResult.getDateSegmentId() == dateSegment) {
					returnFlag = false;
				}
				testList.add(locResult);
			}
		}
		return returnFlag;
	}

	/**
	 * 
	 * @param contract
	 * @param deleteContractRequest
	 * @throws ServiceException
	 */
	private void updateReserveContract(Contract contract,
			DeleteContractRequest deleteContractRequest)
			throws ServiceException, AdapterException {
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		try {
			List reserveList = adapterManager
					.retrieveReserveContractById(contract.getContractId());
			if (null != reserveList && reserveList.size() != 0) {

				ReserveContractId reserveContractId = (ReserveContractId) reserveList
						.get(0);
				Date date = new Date();
				int year1 = date.getYear();
				int month1 = date.getMonth();
				int day1 = date.getDate();
				Date dateNewOne = new Date(year1, month1, day1);
				int expired = reserveContractId.getEndDate().compareTo(
						dateNewOne); // expired < 0 means the reserve
				// contract
				// is expired
				if (expired < 0) {
					List sysPool = adapterManager.retrieveMaxSysId();
					if (sysPool != null) {
						int sysContractId = ((SystemContractId) sysPool.get(0))
								.getContractSequence();
						SystemContractId systemContractId = new SystemContractId();
						systemContractId.setContractSequence(sysContractId + 1);
						systemContractId.setContractId(reserveContractId
								.getContractId());
						Audit audit = getAudit(deleteContractRequest.getUser());
						systemContractId.setCreatedUser(audit.getUser());

						systemContractId.setCreatedTimeStamp(audit.getTime());
						systemContractId.setLastUpdatedUser(audit.getUser());
						systemContractId.setLastUpdatedTimeStamp(audit
								.getTime());
						adapterManager.createSysPoolEntry(systemContractId);
						adapterManager.deleteReserveContract(reserveContractId,
								deleteContractRequest.getUser().getUserId());
					}

				} else {
					reserveContractId.setContractId(contract.getContractId());
					reserveContractId.setStatus("Y");
					Audit audit = getAudit(deleteContractRequest.getUser());
					reserveContractId.setLastUpdatedTimeStamp(audit.getTime());
					reserveContractId.setLastUpdatedUser(audit.getUser());
					builder.persist(reserveContractId, deleteContractRequest
							.getUser(), false);
				}
			}
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}

	private Audit getAudit(User user) {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}

	// ---------------------------------------Pricing Information Tab methods
	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param SavePricingInfoRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(SavePricingInfoRequest savePricingInfoRequest)
			throws ServiceException {
		Logger
				.logInfo("ContractBusinessService - Entering execute(): Pricing Info Create");
		SavePricingInfoResponse savePricingInfoResponse = null;
		try {

			// calls the validation service
			savePricingInfoResponse = (SavePricingInfoResponse) new ValidationServiceController()
					.execute(savePricingInfoRequest);

			if (!savePricingInfoResponse.isValid()) {
				savePricingInfoResponse.setSuccess(false);
				return savePricingInfoResponse;
			}

			BusinessObjectManager bom = getBOM();
			User user = savePricingInfoRequest.getUser();
			Contract contract = getContract(savePricingInfoRequest);
			DateSegment dateSegment = getDateSegment(savePricingInfoRequest);
			ContractPricingInfo contractPricingInfo = new ContractPricingInfo();

			setValuesToContractPricingInfo(savePricingInfoRequest,
					contractPricingInfo);

			savePricingInfoResponse = (SavePricingInfoResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.PRICING_INFO_RESPONSE);

			bom.persist(contractPricingInfo, dateSegment, user, true);
			savePricingInfoResponse.addMessage(new InformationalMessage(
					BusinessConstants.MSG_CONTRACT_PRICINGINFO_SAVE_SUCCESS));
			savePricingInfoResponse.setSuccess(true);

			Logger
					.logInfo("ContractBusinessService - Returning execute(): Pricing Info Create");

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			savePricingInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(savePricingInfoRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(savePricingInfoRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return savePricingInfoResponse;
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request
	 * @return WPDResponse
	 * @throws SevereException
	 */

	public WPDResponse execute(DeletePricingInfoRequest deletePricingInfoRequest)
			throws SevereException {
		Logger
				.logInfo("DeletePricingInfoRequest - Entering execute(): Contract Pricing Info Delete");
		DeletePricingInfoResponse deletePricingInfoResponse = null;
		BusinessObjectManager bom = getBOM();

		User user = deletePricingInfoRequest.getUser();

		deletePricingInfoResponse = (DeletePricingInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_PRICING_INFO_RESPOSE);
		ContractPricingInfo contractPricingInfo = new ContractPricingInfo();
		List coverageList = deletePricingInfoRequest.getContractCoverage();
		List pricingList = deletePricingInfoRequest.getContractPricing();
		List networkList = deletePricingInfoRequest.getContractNetwork();
		try {
			if (coverageList != null && !coverageList.isEmpty()) {
				DateSegment dateSegment = getDateSegment(deletePricingInfoRequest);
				for (int pricingCount = 0; pricingCount < coverageList.size(); pricingCount++) {
					contractPricingInfo
							.setContractDateSegmentSysId(deletePricingInfoRequest
									.getContractKeyObject().getDateSegmentId());
					contractPricingInfo.setCoverage((coverageList
							.get(pricingCount)).toString());
					contractPricingInfo.setPricing((pricingList
							.get(pricingCount)).toString());
					contractPricingInfo.setNetwork((networkList
							.get(pricingCount)).toString());

					bom.delete(contractPricingInfo, dateSegment, user);
				}

				deletePricingInfoResponse = (DeletePricingInfoResponse) WPDResponseFactory
						.getResponse(WPDResponseFactory.DELETE_PRICING_INFO_RESPOSE);

				deletePricingInfoResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_PRICINGINFO_DELETE_SUCCESS));
				deletePricingInfoResponse.setSuccess(true);
			}
			Logger
					.logInfo("DeletePricingInfoRequest - Entering execute(): Contract Pricing Info Delete");
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			deletePricingInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(deletePricingInfoRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (WPDException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(deletePricingInfoRequest);
			String logMessage = "Failed while processing DeletePricingInfoRequest";
			throw new SevereException(logMessage, logParameters, e);

		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(deletePricingInfoRequest);
			String logMessage = "Failed while processing DeletePricingInfoRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return deletePricingInfoResponse;
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			LocatePricingInformationRequest locatePricingInformationRequest)
			throws ServiceException {
		Logger
				.logInfo("LocatePricingInformationRequest - Entering execute(): Benefit Administration Locate");
		LocatePricingInformationResponse locatePricingInformationResponse = null;
		BusinessObjectManager bom = getBOM();
		Contract contract = getContract(locatePricingInformationRequest);
		ContractPricingInfo contractPricingInfo = new ContractPricingInfo();
		locatePricingInformationResponse = (LocatePricingInformationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.LOCATE_PRICING_INFO_RESPOSE);
		try {
			Map params = new HashMap();
			params.put(BusinessConstants.ACTION, "retrieveContractPricingInfo");
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
					locatePricingInformationRequest.getContractKeyObject()
							.getDateSegmentId()));
			contract = (Contract) bom.retrieve(contract,
					locatePricingInformationRequest.getUser(), params);
			locatePricingInformationResponse
					.setPricingInformationList(((DateSegment) contract
							.getDateSegmentList().get(0)).getPricingInfoList());
			locatePricingInformationResponse.setSuccess(true);
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			locatePricingInformationResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(locatePricingInformationRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(locatePricingInformationRequest);
			String logMessage = "Failed while processing LocatePricingInformationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return locatePricingInformationResponse;
	}

	/**
	 * Method to Set values from SavePricingInfoRequest to ContractPricingInfo
	 * 
	 * @param valueObject
	 *            SavePricingInfoRequest object from which values to be taken.
	 * @param businessObject
	 *            ContractPricingInfo object to which values to be put.
	 */
	private void setValuesToContractPricingInfo(
			SavePricingInfoRequest savePricingInfoRequest,
			ContractPricingInfo businessObject) {
		businessObject.setContractDateSegmentSysId(savePricingInfoRequest
				.getContractKeyObject().getDateSegmentId());
		businessObject
				.setCoverage(savePricingInfoRequest.getContractCoverage());
		businessObject.setPricing(savePricingInfoRequest.getContractPricing());
		businessObject.setNetwork(savePricingInfoRequest.getContractNetwork());
		// businessObject.setLastUpdatedUser(savePricingInfoRequest.getUser().getUserId());
		// businessObject.setLastUpdatedTimestamp(new Date());
		// businessObject.setCreatedUser(savePricingInfoRequest.getUser().getUserId());
		// businessObject.setCreatedTimestamp(new Date());
	}

	// -------------------------------------------- End Pricing Information
	// ------------------------------------------------------

	// processes the RetrieveContractSpecificInfoRequest
	public WPDResponse execute(
			RetrieveContractSpecificInfoRequest retrieveContractSpecificInfoRequest)
			throws ServiceException {
		RetrieveContractSpecificInfoResponse retrieveContractSpecificInfoResponse = null;
		Contract contract = getContract(retrieveContractSpecificInfoRequest);
		BusinessObjectManager bom = getBOM();
		retrieveContractSpecificInfoResponse = (RetrieveContractSpecificInfoResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_SPECIFICINFO_RESPONSE);
		try {
			if (retrieveContractSpecificInfoRequest.getAction() == 2) {
				Map params = new HashMap();
				params.put(BusinessConstants.ACTION,
						"retrieveContractAdaptedInfo");
				params
						.put(BusinessConstants.DATESEGMENT_ID, new Integer(
								retrieveContractSpecificInfoRequest
										.getDateSegmentId()));
				contract = (Contract) bom.retrieve(contract,
						retrieveContractSpecificInfoRequest.getUser(), params);
				if (null != contract) {
					retrieveContractSpecificInfoResponse
							.setAdaptedInfoBO((AdaptedInfoBO) contract
									.getDateSegmentList().get(0));
				}
			} else {
				DateSegment specificInfoDateSegment = new DateSegment();
				ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();

				Map params = new HashMap();
				params.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				params
						.put(BusinessConstants.DATESEGMENT_ID, new Integer(
								retrieveContractSpecificInfoRequest
										.getDateSegmentId()));

				// specificInfoDateSegment=contractBusinessObjectBuilder.retrieveContractSpecificInfoDetails(retrieveContractSpecificInfoRequest.getDateSegmentId());
				contract = (Contract) bom.retrieve(contract,
						retrieveContractSpecificInfoRequest.getUser(), params);

				if (contract != null) {
					retrieveContractSpecificInfoResponse.setContract(contract);
					if (contract.getDateSegmentList().size() != 0) {
						DateSegment dateSegment = (DateSegment) contract
								.getDateSegmentList().get(0);
						dateSegment.setProviderSpecCodeList(contract
								.getProviderSpecialityCodeList());
						retrieveContractSpecificInfoResponse
								.setDateSegment(dateSegment);
					}

				}
				retrieveContractSpecificInfoResponse.setDomainDetail(DomainUtil
						.retrieveDomainDetailInfo(
								BusinessConstants.ENTITY_TYPE_CONTRACT,
								contract.getContractSysId()));

			}
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractSpecificInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveContractSpecificInfoRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveContractSpecificInfoRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveContractSpecificInfoResponse;
	}

	public WPDResponse execute(RetrieveContractBenefitComponentRequest request)
			throws ServiceException, AdapterException {
		Logger.logInfo("Inside RetrieveContractBenefitComponentRequest ");
		try {
			RetrieveContractBenefitComponentResponse retrieveContractBenefitComponentResponse = null;
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			retrieveContractBenefitComponentResponse = (RetrieveContractBenefitComponentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BENEFIT_COMPONENT_VIEW_RESPONSE);
			int action = request.getAction();
			Audit audit = getAudit(request.getUser());
			List messageList = new ArrayList();
			switch (action) {

			case RetrieveContractBenefitComponentRequest.RETRIEVE_BENEFITCOMPONENT_INFORMATION:
				// BusinessObjectManager bom = getBOM();
				Contract contract = new Contract();
				// List componentList = new ArrayList();

				BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
				benefitComponentBO.setBenefitComponentId(request
						.getBenefitComponentId());
				contract.setProductId(request.getContractKeyObject()
						.getProductId());
				Contract contractBO = (Contract) contractBusinessObjectBuilder
						.retrieveBenefitComponentGeneralInfo(
								benefitComponentBO, contract, audit);

				retrieveContractBenefitComponentResponse
						.setContract(contractBO);
				retrieveContractBenefitComponentResponse.setDetail(DomainUtil
						.retrieveDomainDetailInfo("BENEFITCOMP",
								benefitComponentBO
										.getBenefitComponentParentId()));
				break;

			case RetrieveContractBenefitComponentRequest.RETRIEVE_CONTRACT_BENEFITCOMPONENT_ALL:

				Contract contractOne = new Contract();
				// List componentList = new ArrayList();
				int productId = request.getProductId();
				contractOne.setProductId(productId);
				contractOne.setContractDateSegmentSysId(request
						.getDateSegmentId());
				Contract contractBOOne = (Contract) contractBusinessObjectBuilder
						.retrieveBenefitComponentListHideUnhide(contractOne);
				retrieveContractBenefitComponentResponse
						.setContract(contractBOOne);
				break;
			case RetrieveContractBenefitComponentRequest.UPDATE_CONTRACT_BENEFITCOMPONENT_FLAGS:

				Contract contractTwo = new Contract();
				// List componentList = new ArrayList();
				int productIdTwo = request.getProductId();
				int dateSegemtnId = request.getDateSegmentId();

				List list = request.getBenefitComponentVO()
						.getBenefitComponentFlagList();

				ContractKeyObject contractKeyObject = request
						.getContractKeyObject();
				BusinessObjectManager bom = null;
				bom = getBusinessObjectManager();
				ContractBenefitComponents bo = new ContractBenefitComponents();
				bo.setComponentList(list);
				bo.setProductId(productIdTwo);
				bo.setDateSegmentId(dateSegemtnId);

				/**
				 * Setting the benefit component hide unhide flag Map to BO ::
				 * by KK*
				 */
				bo.setBenefitComponentHideMap(request
						.getBenefitHideUnhideFlagMap());
				/** end :: by KK* */

				ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

				contractTwo.setContractSysId(contractKeyObject
						.getContractSysId());
				contractTwo.setContractId(contractKeyObject.getContractId());
				contractTwo.setStatus(contractKeyObject.getStatus());
				contractTwo.setVersion(contractKeyObject.getVersion());
				DateSegment dateSegment = getDateSegment(request);
				bom.persist(bo, dateSegment, request.getUser(), false);

				updateAMVForBCList(request);

				messageList
						.add(new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_BENEFITCOMPONENT_HIDEUNHIDE));

				contractTwo.setContractDateSegmentSysId(dateSegemtnId);
				contractTwo.setProductId(productIdTwo);
				Contract contractBOTwo = (Contract) contractBusinessObjectBuilder
						.retrieveBenefitComponentListHideUnhide(contractTwo);
				retrieveContractBenefitComponentResponse
						.setContract(contractBOTwo);

				retrieveContractBenefitComponentResponse
						.setMessages(messageList);

				break;
			}

			return retrieveContractBenefitComponentResponse;
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveContractBenefitComponentRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}

	/**
	 * @param request
	 * @throws ServiceException
	 */
	private void updateAMVForBCList(
			RetrieveContractBenefitComponentRequest request)
			throws ServiceException {
		if (null != request && request.isChanged()) {

			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(request.getContractKeyObject()
					.getProductId());
			validationRqst.setEntityId(request.getContractKeyObject()
					.getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setChangedIds(request.getChangedIds());

			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.BC_LIST_CHANGE_IN_CNTRCT);
			validationRqst.setContractId(request.getContractKeyObject()
					.getContractSysId());
			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);
		}
	}

	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param StandardBenefitRetrieveRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveContractStandardBenefitRequest request)
			throws ServiceException, AdapterException {
		Logger
				.logInfo("StandardBenefitBusinessService - Entering execute(): Standard Benefit Retrieve");
		RetrieveContractStandardBenefitResponse retrieveContractStandardBenefitResponse = null;
		BusinessObjectManager bom = getBOM();
		StandardBenefitBO standardBenefitBO = getStandardBenefitObject(request);
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		try {
			builder.retrieve(standardBenefitBO);
			retrieveContractStandardBenefitResponse = (RetrieveContractStandardBenefitResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_SB_RESPONSE);
			if (null != retrieveContractStandardBenefitResponse) {
				retrieveContractStandardBenefitResponse
						.setStandardBenefitBO(standardBenefitBO);
			}
			retrieveContractStandardBenefitResponse.setDomainDetail(DomainUtil
					.retrieveDomainDetailInfo(BusinessConstants.STDBENEFIT,
							standardBenefitBO.getParentSystemId()));
			Logger
					.logInfo("StandardBenefitBusinessService - Returning execute(): Standard Benefit Retrieve");
			return retrieveContractStandardBenefitResponse;
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing StandardBenefitRetrieveRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}

	/**
	 * Creates standardBenefitBO from a StandardBenefitRetrieveRequest via a
	 * StandardBenefitVO.
	 * 
	 * @param request
	 * @return standardBenefitBO
	 */
	public StandardBenefitBO getStandardBenefitObject(
			RetrieveContractStandardBenefitRequest request) {
		Logger
				.logInfo("StandardBenefitBusinessService - Entering getStandardBenefitObject(): StandardBenefitRetrieveRequest");
		StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
		standardBenefitBO.setStandardBenefitKey(request.getStandardBenefitVO()
				.getStandardBenefitKey());
		Logger
				.logInfo("StandardBenefitBusinessService - Returning getStandardBenefitObject(): StandardBenefitRetrieveRequest");
		return standardBenefitBO;
	}

	/**
	 * Service method for retrieving Benefit Information.
	 * 
	 * @param request
	 *            RetrieveProductBenefitRequest.
	 * @return RetrieveProductBenefitResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveContractBenefitMandateRequest request)
			throws ServiceException {
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			RetrieveContractBenefitMandateResponse retrieveContractBenefitMandateResponse = null;
			retrieveContractBenefitMandateResponse = (RetrieveContractBenefitMandateResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BENEFIT_MANDATE_RESPONSE);
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

			int benefitMasterSystemId = request.getBenefitMasterSystemId();
			User user = request.getUser();
			LocateResults locateResults = builder.getNonAdjBenefitMandate(
					benefitMasterSystemId, user);
			List nonAdjMandateList = locateResults.getLocateResults();
			retrieveContractBenefitMandateResponse
					.setNonAdjMandateList(nonAdjMandateList);

			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());
			return retrieveContractBenefitMandateResponse;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}

	}

	/**
	 * Service method for save Contract Comment .
	 * 
	 * @param request
	 *            SaveDateSegmentCommentRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(SaveDateSegmentCommentRequest request)
			throws ServiceException {

		try {
			SaveDateSegmentCommentResponse saveDateSegmentCommentResponse = null;
			// Contract contract;
			Comment comment = new Comment();
			BusinessObjectManager bom = getBusinessObjectManager();
			saveDateSegmentCommentResponse = (SaveDateSegmentCommentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_COMMENT_RESPONSE);

			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			// contract = getContract(request);
			DateSegment dateSegment = getDateSegment(request);
			int datesegmentid = request.getContractKeyObject()
					.getDateSegmentId();
			comment.setCommentText(request.getNewComments());
			comment.setDateSegmentSysId(datesegmentid);

			bom.persist(comment, dateSegment, request.getUser(), true);
			saveDateSegmentCommentResponse.addMessage(new InformationalMessage(
					BusinessConstants.MSG_CONTRACT_COMMENT_SAVE_SUCCESS));

			return saveDateSegmentCommentResponse;
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}

	/**
	 * Service method to retrieve Contract Comment .
	 * 
	 * @param request
	 *            RetrieveDateSegmentCommentRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveDateSegmentCommentRequest request)
			throws ServiceException {
		RetrieveDateSegmentCommentResponse retrieveDateSegmentCommentResponse = null;
		try {
			Contract contract;
			Comment comment = new Comment();
			Map params = new HashMap();
			BusinessObjectManager bom = getBusinessObjectManager();

			retrieveDateSegmentCommentResponse = (RetrieveDateSegmentCommentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETIEVE_DATESEGMENT_COMMENT_RESPONSE);

			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			contract = getContract(request);

			int datesegmentid = request.getContractKeyObject()
					.getDateSegmentId();
			comment.setDateSegmentSysId(datesegmentid);
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(comment
					.getDateSegmentSysId()));
			if ("NoLimit".equals(request.getMaxValue())) {
				params.put(BusinessConstants.ACTION, "retrieveAllComment");
			} else {
				params.put(BusinessConstants.ACTION, "retrieveComment");
			}
			Contract contractBO = (Contract) bom.retrieve(contract, request
					.getUser(), params);

			retrieveDateSegmentCommentResponse.setContract(contractBO);

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveDateSegmentCommentResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveDateSegmentCommentResponse;
	}

	/**
	 * Service method to retrieve Selected Contract Comment .
	 * 
	 * @param request
	 *            RetrieveDateSegmentCommentRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveSelectedCommentRequest request)
			throws ServiceException {
		RetrieveSelectedCommentResponse retrieveSelectedCommentResponse = null;
		try {
			Contract contract;
			Comment comment = new Comment();
			Map params = new HashMap();
			BusinessObjectManager bom = getBusinessObjectManager();

			retrieveSelectedCommentResponse = (RetrieveSelectedCommentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETIEVE_SELECTED_COMMENT_RESPONSE);
			comment.setCommentSysId(request.getCommentId());
			comment.setDateSegmentSysId(request.getDateSegmentId());
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			contract = getContract(request);

			int datesegmentid = request.getContractKeyObject()
					.getDateSegmentId();
			comment.setDateSegmentSysId(datesegmentid);
			params.put(BusinessConstants.ACTION, "retrieveSelectedComment");
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(comment
					.getDateSegmentSysId()));
			params.put("CommentBo", comment);
			Contract contractBO = (Contract) bom.retrieve(contract, request
					.getUser(), params);

			retrieveSelectedCommentResponse.setContract(contractBO);

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveSelectedCommentResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveSelectedCommentResponse;
	}

	/**
	 * Service method to retrieve Selected Contract Comment .
	 * 
	 * @param request
	 *            RetrieveContractProductRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			RetrieveContractProductRequest retrieveContractProductRequest)
			throws ServiceException {
		Logger
				.logInfo("ContractBusinessService - Entering execute(): Contract Product Retrieve");

		BusinessObjectManager bom = getBOM();
		Contract contract = getContract(retrieveContractProductRequest);
		// seting values to BusinessObject

		List dateSegmentList = new ArrayList();
		DateSegment dateSegment = new DateSegment();

		ProductBO productBO = new ProductBO();
		dateSegment.setProductBO(productBO);
		dateSegmentList.add(0, dateSegment);
		contract.setDateSegmentList(dateSegmentList);

		((DateSegment) contract.getDateSegmentList().get(0)).getProductBO()
				.setProductKey(retrieveContractProductRequest.getProductKey());

		RetrieveContractProductResponse retrieveContractProductResponse = (RetrieveContractProductResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_PRODUCT);
		try {
			Map params = new HashMap();
			params.put(BusinessConstants.ACTION,
					"retrieveContractProductGeneralInfo");

			contract = (Contract) bom.retrieve(contract,
					retrieveContractProductRequest.getUser(), params);
			productBO = ((DateSegment) contract.getDateSegmentList().get(0))
					.getProductBO();

			retrieveContractProductResponse.setProductBO(productBO);
			retrieveContractProductResponse.setDomainDetail(DomainUtil
					.retrieveDomainDetailInfo(
							BusinessConstants.ENTITY_TYPE_PRODUCT, productBO
									.getParentProductKey()));

			retrieveContractProductResponse.setSuccess(true);

			Logger
					.logInfo("ContractBusinessService - Returning execute(): Contract Product Retrieve");

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractProductResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveContractProductRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveContractProductRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveContractProductResponse;
	}

	/**
	 * Service method to retrieve Benefit Administration .
	 * 
	 * @param request
	 *            RetrieveDateSegmentCommentRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			RetrieveContractBenefitAdministrationRequest request)
			throws ServiceException {

		RetrieveContractBenefitAdministrationResponse retrieveContractBenefitAdministrationResponse = null;
		try {
			Logger.logDebug("Entering execute method, request = "
					+ request.getClass().getName());
			retrieveContractBenefitAdministrationResponse = (RetrieveContractBenefitAdministrationResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BENEFIT_ADMIN_RESPONSE);
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();

			// getting business object manager
			BusinessObjectManager bom = getBusinessObjectManager();
			ContractBenefitAdministrationLocateCriteria benefitLocateCriteria = new ContractBenefitAdministrationLocateCriteria();
			int action = request.getAction();
			User user = request.getUser();
			switch (action) {

			case RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_INITIAL:
				benefitLocateCriteria.setBenefitAdminSysId(request
						.getBenefitAdminSysId());
				benefitLocateCriteria.setEntityId(request.getContractId());
				benefitLocateCriteria.setAdminOptSysId(request
						.getAdminOptSysId());
				benefitLocateCriteria.setBenefitComponentId(request
						.getBenefitComponentId());
				benefitLocateCriteria.setBenefitId(request.getBenefitId());
				// Code change by minu : 5-1-2011 : eWPD System Stabilization
				benefitLocateCriteria.setCntrctParentSysId(request
						.getCntrctParentSysId());
				benefitLocateCriteria.setAdminLevelOptionSysId(request
						.getAdminLevelOptionSysId());

				benefitLocateCriteria
						.setEntiityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
				benefitLocateCriteria
						.setAction(RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_INITIAL);
				benefitLocateCriteria.setAllPossibleAnswerMap(request
						.getAllPossibleAnswerMap());

				LocateResults locateResults = contractBusinessObjectBuilder
						.locate(benefitLocateCriteria, user);
				if (null != locateResults.getLocateResults()) {
					retrieveContractBenefitAdministrationResponse
							.setBenefitAdministrationList(locateResults
									.getLocateResults());
				}
				retrieveContractBenefitAdministrationResponse.setSuccess(true);
				break;
			case RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD:
				benefitLocateCriteria
						.setAction(RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD);
				benefitLocateCriteria.setAdminOptSysId(request
						.getAdminOptSysId());
				benefitLocateCriteria.setBenefitAdminSysId(request
						.getBenefitAdminSysId());
				benefitLocateCriteria.setContractQuesitionnaireBO(request
						.getContractQuesitionnaireBO());
				benefitLocateCriteria
						.setAnswerId(request.getSelectedAnswerId());
				benefitLocateCriteria.setAnswerDesc(request
						.getSelectedAnswerDesc());
				benefitLocateCriteria.setEntityId(request.getContractId());
				benefitLocateCriteria.setQuestionnareList(request
						.getQuestionnareList());
				benefitLocateCriteria.setQuestionareListIndex(request
						.getQuestionareListIndex());
				benefitLocateCriteria.setBenefitComponentId(request
						.getBenefitComponentId());
				benefitLocateCriteria.setBenefitAdminSysId(request
						.getBenefitAdminSysId());
				benefitLocateCriteria.setAllPossibleAnswerMap(request
						.getAllPossibleAnswerMap());
				LocateResults locateResultsNew = contractBusinessObjectBuilder
						.locate(benefitLocateCriteria, user);
				if (null != locateResultsNew.getLocateResults()) {
					retrieveContractBenefitAdministrationResponse
							.setBenefitAdministrationList(locateResultsNew
									.getLocateResults());
				}
				retrieveContractBenefitAdministrationResponse.setSuccess(true);
				break;
			case RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_VIEW_PRINT:
				benefitLocateCriteria.setBenefitAdminSysId(request
						.getBenefitAdminSysId());
				benefitLocateCriteria.setEntityId(request.getContractId());
				benefitLocateCriteria.setAdminOptSysId(request
						.getAdminOptSysId());
				benefitLocateCriteria.setBenefitComponentId(request
						.getBenefitComponentId());
				benefitLocateCriteria.setBenefitId(request.getBenefitId());

				benefitLocateCriteria.setCntrctParentSysId(request
						.getCntrctParentSysId());
				benefitLocateCriteria.setAdminLevelOptionSysId(request
						.getAdminLevelOptionSysId());
				benefitLocateCriteria.setAllPossibleAnswerMap(request
						.getAllPossibleAnswerMap());
				benefitLocateCriteria
						.setEntiityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
				benefitLocateCriteria
						.setAction(RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_VIEW_PRINT);
				benefitLocateCriteria.setAllPossibleAnswerMap(request
						.getAllPossibleAnswerMap());
				LocateResults locateResultsView = contractBusinessObjectBuilder
						.locate(benefitLocateCriteria, user);
				if (null != locateResultsView.getLocateResults()) {
					retrieveContractBenefitAdministrationResponse
							.setBenefitAdministrationList(locateResultsView
									.getLocateResults());
				}
				retrieveContractBenefitAdministrationResponse.setSuccess(true);
				break;

			case RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD_TIER:
				benefitLocateCriteria
						.setAction(RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD_TIER);
				benefitLocateCriteria.setAdminOptSysId(request
						.getAdminOptSysId());
				benefitLocateCriteria.setBenefitAdminSysId(request
						.getBenefitAdminSysId());
				benefitLocateCriteria.setContractQuesitionnaireBO(request
						.getContractQuesitionnaireBO());
				benefitLocateCriteria
						.setAnswerId(request.getSelectedAnswerId());
				benefitLocateCriteria.setAnswerDesc(request
						.getSelectedAnswerDesc());
				benefitLocateCriteria.setEntityId(request.getContractId());
				benefitLocateCriteria.setQuestionnareList(request
						.getQuestionnareList());
				benefitLocateCriteria.setQuestionareListIndex(request
						.getQuestionareListIndex());
				benefitLocateCriteria.setBenefitComponentId(request
						.getBenefitComponentId());
				benefitLocateCriteria.setAllPossibleAnswerMap(request
						.getAllPossibleAnswerMap());
				LocateResults locateResultsNewTier = contractBusinessObjectBuilder
						.locate(benefitLocateCriteria, user);
				if (null != locateResultsNewTier.getLocateResults()) {
					retrieveContractBenefitAdministrationResponse
							.setBenefitAdministrationList(locateResultsNewTier
									.getLocateResults());
				}
				retrieveContractBenefitAdministrationResponse.setSuccess(true);

				break;

			}

			Logger.logDebug("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractBenefitAdministrationResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveContractBenefitAdministrationResponse;
	}

	/**
	 * Service method to retrieve Benefit Administration .
	 * 
	 * @param request
	 *            RetrieveDateSegmentCommentRequest.
	 * @return SaveDateSegmentCommentResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveAllPossibleAnswerRequest request)
			throws ServiceException {
		RetrieveAllPossibleAnswerResponse retrieveAllPossibleAnswerResponse = null;
		try {
			Logger.logDebug("Entering execute method, request = "
					+ request.getClass().getName());
			Contract contract = new Contract();
			List allPossibleAnswerList = null;
			java.util.HashMap allPossibleAnswerMap = new java.util.HashMap();
			HashMap questionAccumulatorMap = new HashMap();
			HashMap associatedAccumulatorsMap = new HashMap();
			HashMap stdAccumulatorsMap = new HashMap();
			retrieveAllPossibleAnswerResponse = (RetrieveAllPossibleAnswerResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_ALL_POSSIBLE_ANSWER_ADMIN_OPTION_RESPONSE);
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
			String BYCYAnswer = null;
			// getting business object manager
			BusinessObjectManager bom = getBusinessObjectManager();
			AllPossibleAnswerForAdminOptionLocateCriteria possibleAnswerLocateCriteria = new AllPossibleAnswerForAdminOptionLocateCriteria();
			User user = request.getUser();

			possibleAnswerLocateCriteria.setAdminOptSysId(request
					.getAdminOptSysId());
			LocateResults locateResults = contractBusinessObjectBuilder.locate(
					possibleAnswerLocateCriteria, user);
			if (null != locateResults.getLocateResults()) {
				retrieveAllPossibleAnswerResponse
						.setAllPossibleAnswerList(locateResults
								.getLocateResults());

			}
			associatedAccumulatorsMap = (HashMap) getAllAssociatedAccumsForAdminOption(request);
			List getImageRWDAFlagLst = contractBusinessObjectBuilder
					.getImageRWDAFlagLst(Integer.toString(request
							.getDateSegmentSysId()));
			DateSegment dateSegment = new DateSegment();
			if (null != getImageRWDAFlagLst && !getImageRWDAFlagLst.isEmpty()) {
				dateSegment = (DateSegment) getImageRWDAFlagLst.get(0);
			}
			if (null != dateSegment
					&& "Y".equalsIgnoreCase(dateSegment.getImageRWDAFlag())) {
				List busDomainList = DomainUtil.retrieveAssociatedDomains(
						BusinessConstants.ENTITY_TYPE_CONTRACT, request
								.getContractParentSysId());
				contract.setBusinessDomains(busDomainList);

				List BYCYAnswerList = contractBusinessObjectBuilder
						.getBYCYAnswerList(request.getDateSegmentSysId());
				if (null != BYCYAnswerList && !BYCYAnswerList.isEmpty()) {
					ContractAssnQuestionnaireBO questionnaireBO = (ContractAssnQuestionnaireBO) BYCYAnswerList
							.get(0);
					BYCYAnswer = questionnaireBO.getSelectedAnswerDesc();
				}

				boolean isCompletelyReadyForImageRewrite = false;
				isCompletelyReadyForImageRewrite = BusinessUtil
						.isReadyForImageRewrite(contract);
				if (isCompletelyReadyForImageRewrite) {
					stdAccumulatorsMap = (HashMap) getStdAccumsForAdminOption(
							request, BYCYAnswer, busDomainList);
				}

				AccumulatorValidationBO accumValidationBO = new AccumulatorValidationBO();
				accumValidationBO.setBenefitSysId(request.getBenefitId());

				if (null != locateResults
						&& null != locateResults.getLocateResults()
						&& locateResults.getLocateResults().size() > 0) {

					allPossibleAnswerList = (List) locateResults
							.getLocateResults();
					Iterator it1 = allPossibleAnswerList.iterator();
					Map adjudQuestionCheck = new HashMap();
					String isAdjudAccumQuestion = "N";
					while (it1.hasNext()) {
						PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) it1
								.next();
						int questionNumber = possibleAnswerBO
								.getQuestionNumber();
						List standardAccumList = new ArrayList();
						isAdjudAccumQuestion = possibleAnswerBO
								.getAccumQuestion();
						if (isAdjudAccumQuestion.equals("Y")) { // IS ADJUD
							// ACCUM
							// QUESTION
							if (!questionAccumulatorMap.containsKey(Integer
									.toString(questionNumber))) {
								List associatedAccumulators = (List) associatedAccumulatorsMap
										.get(new Integer(questionNumber));
								if (null != associatedAccumulators
										&& !associatedAccumulators.isEmpty()) {
									if (isCompletelyReadyForImageRewrite) { // cmpltly
										// ready
										standardAccumList = (List) stdAccumulatorsMap
												.get(new Integer(questionNumber));
										if (null == standardAccumList
												|| standardAccumList.isEmpty()) {
											addAccumulatorsToMap(
													questionAccumulatorMap,
													Integer
															.toString(questionNumber),
													associatedAccumulators);
										} else {
											addAccumulatorsToMap(
													questionAccumulatorMap,
													Integer
															.toString(questionNumber),
													standardAccumList);
										}
									} else {
										addAccumulatorsToMap(
												questionAccumulatorMap,
												Integer
														.toString(questionNumber),
												associatedAccumulators);
									}
								}
							}
							ArrayList possibleAnswerList = allPossibleAnswerMap
									.get(new Integer(questionNumber)) != null ? (ArrayList) allPossibleAnswerMap
									.get(new Integer(questionNumber))
									: new ArrayList();
							if (questionAccumulatorMap.containsKey(Integer
									.toString(questionNumber))) {
								List accumList = (List) questionAccumulatorMap
										.get(Integer.toString(questionNumber));
								if (accumList.contains(possibleAnswerBO
										.getPossibleAnswerDesc())
										|| possibleAnswerBO
												.getPossibleAnswerDesc()
												.equals(
														BusinessConstants.NOT_ANSWERED))
									possibleAnswerList.add(possibleAnswerBO);
								allPossibleAnswerMap.put(new Integer(
										questionNumber), possibleAnswerList);
							}

						} else {
							ArrayList possibleAnswerList = allPossibleAnswerMap
									.get(new Integer(questionNumber)) != null ? (ArrayList) allPossibleAnswerMap
									.get(new Integer(questionNumber))
									: new ArrayList();
							possibleAnswerList.add(possibleAnswerBO);
							allPossibleAnswerMap.put(
									new Integer(questionNumber),
									possibleAnswerList);
						}
					}

				}

				retrieveAllPossibleAnswerResponse
						.setAllPossibleAnswerMap(allPossibleAnswerMap);
			} else {
				if (null != locateResults
						&& null != locateResults.getLocateResults()
						&& locateResults.getLocateResults().size() > 0) {

					allPossibleAnswerList = (List) locateResults
							.getLocateResults();
					Iterator it1 = allPossibleAnswerList.iterator();

					while (it1.hasNext()) {
						PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) it1
								.next();
						int questionNumber = possibleAnswerBO
								.getQuestionNumber();

						ArrayList possibleAnswerList = allPossibleAnswerMap
								.get(new Integer(questionNumber)) != null ? (ArrayList) allPossibleAnswerMap
								.get(new Integer(questionNumber))
								: new ArrayList();
						possibleAnswerList.add(possibleAnswerBO);

						allPossibleAnswerMap.put(new Integer(questionNumber),
								possibleAnswerList);
					}

				}
				retrieveAllPossibleAnswerResponse
						.setAllPossibleAnswerMap(allPossibleAnswerMap);
			}

			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveAllPossibleAnswerResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveAllPossibleAnswerResponse;
	}

	/**
	 * Updates the overridden answers to the db
	 * 
	 * @param administrationRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			SaveBenefitAdministrationRequest administrationRequest)
			throws ServiceException {

		Logger
				.logInfo("Entering execute method for saving benefit administration");
		// Create the response object from the response factory
		SaveContractBenefitAdministrationResponse administrationResponse = (SaveContractBenefitAdministrationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_BENEFIT_ADMINISTRATION_FOR_CONTRACT);
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		Map tierQstnMap = null;
		String tierQstnLineIdStr = null;
		try {
			// Get the BusinessObjectManager
			BusinessObjectManager bom = getBusinessObjectManager();

			DateSegment dateSegment = getDateSegment(administrationRequest);

			EntityBenefitAdministrationPSBO administrationPSBO = new EntityBenefitAdministrationPSBO();
			administrationPSBO.setQuestionnareList(administrationRequest
					.getQuestionnareList());
			administrationPSBO.setTierList(administrationRequest.getTierList());

			// Code change by minu : 28-12-2010 : eWPD System Stabilization
			// Setting questionnaireList to add/update/remove to
			// EntityBenefitAdministrationPSBO object
			administrationPSBO.setQuestionnaireListToAdd(administrationRequest
					.getQuestionnaireListToAdd());
			administrationPSBO
					.setQuestionnaireListToUpdate(administrationRequest
							.getQuestionnaireListToUpdate());
			administrationPSBO
					.setQuestionnaireListToRemove(administrationRequest
							.getQuestionnaireListToRemove());
			administrationPSBO
					.setTierQuestionnaireListToAdd(administrationRequest
							.getTierQuestionnaireListToAdd());
			administrationPSBO
					.setTierQuestionnaireListToUpdate(administrationRequest
							.getTierQuestionnaireListToUpdate());
			administrationPSBO
					.setTierQuestionnaireListToRemove(administrationRequest
							.getTierQuestionnaireListToRemove());

			administrationPSBO.setBenefitComponentid(administrationRequest
					.getBenefitComponentId());
			administrationPSBO.setAdminLevelOptionSysId(administrationRequest
					.getAdminLevelOptionAssnId());
			administrationPSBO.setDateSegmentId(administrationRequest
					.getDateSegmentSysId());
			// Get the list of benefitAdministrationVOs from the request
			List benefitAdministrationVOList = administrationRequest
					.getBenefitAdministrationVOList();

			// Get the BO

			// Create a BO List
			List benefitAdministrationBOList = null;

			// Iterate through the list and save the administration options one
			// by one
			if (null != benefitAdministrationVOList) {
				benefitAdministrationBOList = new ArrayList(
						benefitAdministrationVOList.size());
				for (int i = 0; i < benefitAdministrationVOList.size(); i++) {

					// Get the individual BenefitAdministrationVOs
					BenefitAdministrationOverrideVO administrationVO = (BenefitAdministrationOverrideVO) benefitAdministrationVOList
							.get(i);

					// Create an instance of the BO
					EntityBenefitAdministration administration = new EntityBenefitAdministration();

					// Set the values to the BO from the VO
					administration.setAnswerDesc(administrationVO
							.getAnswerDesc());
					administration.setAnswerId(administrationVO.getAnswerId());
					administration.setQuestionNumber(administrationVO
							.getQuestionId());
					administration.setEntityId(administrationRequest
							.getContractKeyObject().getDateSegmentId());
					administration.setBeneftComponentId(administrationRequest
							.getBenefitComponentId());
					administration
							.setEntityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
					administration.setAdminOptionHideFlag("F");
					administration.setQstnHideFlag("F");
					administration
							.setAdminLevelOptionAssnId(administrationRequest
									.getAdminLevelOptionAssnId());

					// Set the BO to the BOList
					benefitAdministrationBOList.add(administration);

				}
			}

			// Set the BOList to the BO
			administrationPSBO
					.setBenefitAdministrationList(benefitAdministrationBOList);

			// Call the persisit method of the bom to persist the
			// overridden values to the db
			bom.persist(administrationPSBO, dateSegment, administrationRequest
					.getUser(), false);

			/*
			 * tierQstnMap = generateListsFromMap(administrationRequest
			 * .getTierQstnIdMap());
			 */

			// tierQstnLineIdStr = (String) tierQstnMap.get("0");
			// administrationRequest.setTierQstnIdStr(tierQstnLineIdStr);
			updateAMVForBnftAdminInContract(administrationRequest);

			// Set the update success message to the response
			List messageList = new ArrayList(2);

			messageList.add(new InformationalMessage(
					BusinessConstants.ADMIN_OPTION_UPDATE));

			addMessagesToResponse(administrationResponse, messageList);
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			administrationResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			String logMessage = "Failed while processing SaveBenefitAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			String logMessage = "Failed while processing SaveBenefitAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
				.logInfo("Returning execute method for saving benefit administration");
		return administrationResponse;
	}

	/**
	 * @param administrationRequest
	 * @throws ServiceException
	 */
	private void updateAMVForBnftAdminInContract(
			SaveBenefitAdministrationRequest administrationRequest)
			throws ServiceException {

		if (null != administrationRequest && administrationRequest.isChanged()) {
			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(administrationRequest
					.getContractKeyObject().getProductId());
			validationRqst.setEntityId(administrationRequest
					.getContractKeyObject().getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setChangedIds(administrationRequest.getChangedIds());
			validationRqst.setBenefitAdministrationId(administrationRequest
					.getBenefitAdminSysId());
			validationRqst.setBenefitComponentId(administrationRequest
					.getBenefitComponentId());
			validationRqst.setBenefitCompName(administrationRequest
					.getBCompName());
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION);
			validationRqst.setContractId(administrationRequest
					.getContractKeyObject().getContractSysId());
			validationRqst.setBenefitId(administrationRequest.getBenefitId());

			/* START CARS */
			validationRqst.setChangedTiers(administrationRequest
					.getChangedTiers());
			validationRqst.setChangedTierSysIds(administrationRequest
					.getChangedTierSysIds());
			/*
			 * validationRqst.setTierSysLineIds(administrationRequest
			 * .getTierQstnIdStr());
			 */
			/* END CARS */
			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);
		}

	}

	/**
	 * @param administrationRequest
	 * @throws ServiceException
	 */
	private void updateAMVForBnftLinesInContract(
			SaveContractBenefitDefinitionRequest benefitDefinitionRequest)
			throws ServiceException {
		if (null != benefitDefinitionRequest) {
			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(benefitDefinitionRequest
					.getContractKeyObject().getProductId());
			validationRqst.setEntityId(benefitDefinitionRequest
					.getContractKeyObject().getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setChangedIds(benefitDefinitionRequest
					.getChangedBenefitLines());
			validationRqst.setBenefitComponentId(benefitDefinitionRequest
					.getBenefitComponentId());
			validationRqst.setBenefitCompName(benefitDefinitionRequest
					.getBenefitComponentName());
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.BENEFITLEVELS_IN_CONTRACT);
			validationRqst.setContractId(benefitDefinitionRequest
					.getContractKeyObject().getContractSysId());
			validationRqst.setBenefitId(benefitDefinitionRequest
					.getBenefitSysId());
			/* START CARS */
			validationRqst.setChangedTiers(benefitDefinitionRequest
					.getChangedTierLineIds());
			validationRqst.setChangedTierSysIds(benefitDefinitionRequest
					.getChangedTierSysIds());
			/* END CARS */
			/*
			 * validationRqst.setTierSysLineIds(benefitDefinitionRequest
			 * .getTierSysLineStr());
			 */

			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);

		}

	}

	/**
	 * Service method for retrieving Benefit Information.
	 * 
	 * @param request
	 *            RetrieveBenefitGeneralInfoRequest.
	 * @return RetrieveBenefitGeneralInfoResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveBenefitGeneralInfoRequest request)
			throws ServiceException {
		RetrieveBenefitGeneralInfoResponse retrieveBenefitGeneralInfoResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			retrieveBenefitGeneralInfoResponse = (RetrieveBenefitGeneralInfoResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_BENEFIT_GENERALINFO_RESPONSE);
			ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

			int benefitKey = request.getStandardBenefitKey();
			int productKey = request.getContractKeyObject().getProductId();
			AssignedRuleIdBO assignedRuleIdBO = builder
					.getDefaultRule(benefitKey);
			retrieveBenefitGeneralInfoResponse
					.setDefaultRuleID(assignedRuleIdBO.getPrimaryCode());
			retrieveBenefitGeneralInfoResponse
					.setDefaultRuleDesc(assignedRuleIdBO.getCodeDescText());

			StandardBenefitBO standardBenefitBO = builder
					.getBenefitGeneralInfo(benefitKey, request
							.getBenefitComponentId(), request
							.getDateSegmentId(), productKey);
			retrieveBenefitGeneralInfoResponse
					.setStandardBenefitBO(standardBenefitBO);
			retrieveBenefitGeneralInfoResponse.setDomainDetail(DomainUtil
					.retrieveDomainDetailInfo(BusinessConstants.STDBENEFIT,
							standardBenefitBO.getParentSystemId()));
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());
			if (request.getAction() == 3) {
				List messageList = new ArrayList(2);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_RLUE_UPDATE));
				retrieveBenefitGeneralInfoResponse.setSuccess(true);
				addMessagesToResponse(retrieveBenefitGeneralInfoResponse,
						messageList);
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveBenefitGeneralInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveBenefitGeneralInfoResponse;
	}

	public WPDResponse execute(
			ContractNoteAttachmentRequest contractNoteAttachmentRequest)
			throws ServiceException {
		ContractNoteAttachmentResponse contractNoteAttachmentResponse = null;
		try {

			BusinessObjectManager bom = getBOM();
			List notesIdList;
			List versionList;
			contractNoteAttachmentResponse = (ContractNoteAttachmentResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_NOTES_ATTACH_RESPONSE);
			int dateSegmentId = contractNoteAttachmentRequest
					.getContractKeyObject().getDateSegmentId();
			String noteCriteria = contractNoteAttachmentRequest.getNoteName();
			// To attach Notes for Contract Override
			if (contractNoteAttachmentRequest.getAction() == 1) {

				Contract contract = getContract(contractNoteAttachmentRequest);
				notesIdList = contractNoteAttachmentRequest.getNotesIdList();
				versionList = contractNoteAttachmentRequest.getVersionList();
				contract.setVersion(contractNoteAttachmentRequest
						.getContractKeyObject().getVersion());
				contract.setStatus(contractNoteAttachmentRequest
						.getContractKeyObject().getStatus());
				contract.setContractId(contractNoteAttachmentRequest
						.getContractKeyObject().getContractId());
				DateSegment dateSegment = getDateSegment(contractNoteAttachmentRequest);
				// DateSegment dateSegment = retrieveDateSegment(contract,
				// dateSegmentId, contractNoteAttachmentRequest.getUser());
				// Call method getStandardBenefitOverrideNotesObject
				notesIdList = getContractOverrideNotesObject(
						contractNoteAttachmentRequest.getNotesIdList(),
						dateSegmentId, dateSegment.getProductId(), versionList);
				// call the persist method of the bom - for
				// benefitComponentNotesAttach
				bom.persist(notesIdList, dateSegment,
						contractNoteAttachmentRequest.getUser(), true);
				contractNoteAttachmentResponse.setNoteList(locateContractNotes(
						contractNoteAttachmentRequest,
						contractNoteAttachmentRequest.getDateSegmentId(),
						contractNoteAttachmentRequest.getEntityType()));
				// Set success message to response
				contractNoteAttachmentResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_CONTRACT_NOTES_ATTACH_SUCCESS));
				Logger.logInfo("Contract Notes Saved Successfully");
			} else {
				ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
				contractNoteAttachmentResponse.setNoteList(builder
						.locateContractNotes(dateSegmentId, noteCriteria));
			}

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			contractNoteAttachmentResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(contractNoteAttachmentRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(contractNoteAttachmentRequest);
			String logMessage = "Failed while processing ContractNoteAttachmentRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return contractNoteAttachmentResponse;
	}

	// method that retuns the list of subobjects
	private List getContractOverrideNotesObject(List idList, int entityId,
			int productId, List versionList) {

		List noteIdList = null;
		if (null != idList) {
			noteIdList = new ArrayList(idList.size());
			// Iterate through the list
			for (int i = 0; i < idList.size(); i++) {
				// Create the subObject
				AttachedNotesBO overrideNotesBO = new AttachedNotesBO();
				// Set the values to the BO
				overrideNotesBO.setNoteId((String.valueOf(idList.get(i))));
				overrideNotesBO.setEntityId(entityId);
				overrideNotesBO
						.setEntityType(BusinessConstants.ATTACH_CONTRACT);
				Integer version = (Integer) versionList.get(i);
				overrideNotesBO.setVersion(version.intValue());

				// add to the list
				noteIdList.add(overrideNotesBO);
			}
		}
		// return list
		return noteIdList;
	}

	// Return the DateSegment Object
	public DateSegment retrieveDateSegment(Contract contract,
			int dateSegmentSysId, User user) throws WPDException {
		Map params = new HashMap();
		BusinessObjectManager bom = getBOM();
		params.put(BusinessConstants.DATESEGMENT_ID, new Integer(
				dateSegmentSysId));
		params.put(BusinessConstants.ACTION,
				BusinessConstants.RETREIVE_SPECIFIC);
		contract = (Contract) bom.retrieve(contract, user, params);
		DateSegment retrievedDateSegment = null;
		if (null != contract) {
			retrievedDateSegment = (DateSegment) contract.getDateSegmentList()
					.get(0);
		}
		return retrievedDateSegment;

	}

	// Locate and return a list of Notes for contract
	public List locateContractNotes(
			ContractRequest contractNoteAttachmentRequest, int dateSegmentId,
			String entityType) throws WPDException {
		BusinessObjectManager bom = getBOM();
		ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
		notesLocateCriteria.setDateSegmentId(dateSegmentId);
		notesLocateCriteria.setEntityType(entityType);
		notesLocateCriteria
				.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
		LocateResults locateResults = bom.locate(notesLocateCriteria,
				contractNoteAttachmentRequest.getUser());
		return locateResults.getLocateResults();
	}

	/**
	 * Service method for Attaching overrided note Information methode for
	 * benefit component associated to Contract .
	 * 
	 * @param request
	 *            ContractBenefitComponentAttachNotesRequest.
	 * @return ContractBenefitComponentAttachNotesResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			ContractBenefitComponentAttachNotesRequest contractBenefitComponentAttachNotesRequest)
			throws ServiceException {
		ContractBenefitComponentAttachNotesResponse contractBenefitComponentAttachNotesResponse = null;
		List list = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ contractBenefitComponentAttachNotesRequest.getClass()
							.getName());

			contractBenefitComponentAttachNotesResponse = (ContractBenefitComponentAttachNotesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_BENEFIT_COMPONENT_NOTES_ATTACH_RESPONSE);

			BusinessObjectManager bom = getBusinessObjectManager();
			List messageList = new ArrayList();
			// Contract contract =
			// getContract(contractBenefitComponentAttachNotesRequest);
			DateSegment dateSegment = getDateSegment(contractBenefitComponentAttachNotesRequest);
			List notesVOList = contractBenefitComponentAttachNotesRequest
					.getNotes();

			List versionList = contractBenefitComponentAttachNotesRequest
					.getVersionList();
			List productEntitylist = null;
			int productKey;
			int i = 0;
			ContractNotes contractNotes = new ContractNotes();
			if (null != notesVOList) {
				productEntitylist = new ArrayList(notesVOList.size());
				for (i = 0; i < notesVOList.size(); i++) {
					NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
					notesAttachmentOverrideBO.setNoteId((notesVOList.get(i))
							.toString());
					notesAttachmentOverrideBO
							.setPrimaryEntityId(contractBenefitComponentAttachNotesRequest
									.getContractKeyObject().getDateSegmentId());
					notesAttachmentOverrideBO
							.setPrimaryEntityType(WebConstants.ATTACH_CONTRACT);
					notesAttachmentOverrideBO
							.setSecondaryEntityId(contractBenefitComponentAttachNotesRequest
									.getBenefitComponentId());
					notesAttachmentOverrideBO
							.setSecondaryEntityType(WebConstants.ATTACH_BENEFIT_COMP);
					notesAttachmentOverrideBO
							.setBenefitComponentId(contractBenefitComponentAttachNotesRequest
									.getBenefitComponentId());
					notesAttachmentOverrideBO.setVersion(Integer
							.parseInt((String) versionList.get(i)));
					notesAttachmentOverrideBO
							.setOverrideStatus(contractBenefitComponentAttachNotesRequest
									.getOverrideStatus());
					productEntitylist.add(notesAttachmentOverrideBO);
				}
			}
			contractNotes.setNoteList(productEntitylist);
			contractNotes.setAction(2);
			bom.persist(contractNotes, dateSegment,
					contractBenefitComponentAttachNotesRequest.getUser(), true);

			if (contractBenefitComponentAttachNotesRequest.getAction() == 1) {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_NOTE_ATTACHED));
				contractBenefitComponentAttachNotesResponse
						.setMessages(messageList);
				contractBenefitComponentAttachNotesResponse.setSuccess(true);
			} else {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_NOTE_HIDE));
				contractBenefitComponentAttachNotesResponse
						.setMessages(messageList);
				contractBenefitComponentAttachNotesResponse.setSuccess(true);
			}
			list = locateOveridedNotes(
					contractBenefitComponentAttachNotesRequest
							.getContractKeyObject().getDateSegmentId(),
					contractBenefitComponentAttachNotesRequest
							.getBenefitComponentId(),
					WebConstants.ATTACH_BENEFIT_COMP,
					contractBenefitComponentAttachNotesRequest
							.getBenefitComponentId(),
					contractBenefitComponentAttachNotesRequest.getUser());
			if (null != list) {
				contractBenefitComponentAttachNotesResponse
						.setContractBenefitComponentNotesList(list);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ contractBenefitComponentAttachNotesResponse.getClass()
							.getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			contractBenefitComponentAttachNotesResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(contractBenefitComponentAttachNotesRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(contractBenefitComponentAttachNotesRequest);
			String logMessage = "Failed while processing ContractBenefitComponentAttachNotesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return contractBenefitComponentAttachNotesResponse;
	}

	/**
	 * method for Retrieving overrided note Information .
	 * 
	 */
	public List locateOveridedNotes(int contractDateSegmentId,
			int secondaryEntityId, String secondaryEntityType,
			int benefitComponentId, User user) throws WPDException {

		BusinessObjectManager bom = getBusinessObjectManager();
		ContractComponentNotesLocateCriteria contractComponentNotesLocateCriteria = new ContractComponentNotesLocateCriteria();
		contractComponentNotesLocateCriteria
				.setPrimaryEntityId(contractDateSegmentId);
		contractComponentNotesLocateCriteria
				.setPrimaryEntityType(WebConstants.ATTACH_CONTRACT);
		contractComponentNotesLocateCriteria
				.setSecondaryEntityId(secondaryEntityId);
		contractComponentNotesLocateCriteria
				.setSecondaryEntityType(secondaryEntityType);
		contractComponentNotesLocateCriteria
				.setBenefitComponentId(benefitComponentId);
		LocateResults locateResults = bom.locate(
				contractComponentNotesLocateCriteria, user);
		return locateResults.getLocateResults();
	}

	/**
	 * Service method for Retrieving overrided note Information methode for
	 * beenefit component associated to Contract .
	 * 
	 * @param request
	 *            LoadContractComponentNoteRequest.
	 * @return LoadContractComponentNoteResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			LoadContractComponentNoteRequest loadContractComponentNoteRequest)
			throws ServiceException {
		LoadContractComponentNoteResponse loadContractComponentNoteResponse = null;
		List list = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ loadContractComponentNoteRequest.getClass().getName());
			loadContractComponentNoteResponse = (LoadContractComponentNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.LOAD_CONTRACT_COMPONENT_NOTE_RESPONSE);

			BusinessObjectManager bom = getBusinessObjectManager();
			if (loadContractComponentNoteRequest.getAction() == 200) {
				list = locateOveridedNotesForDatafeed(
						loadContractComponentNoteRequest.getContractKeyObject()
								.getDateSegmentId(),
						WebConstants.ATTACH_BENEFIT_COMP,
						loadContractComponentNoteRequest
								.getBenefitComponentId(),
						loadContractComponentNoteRequest.getUser(),
						loadContractComponentNoteRequest
								.getBenefitComponentList(),
						loadContractComponentNoteRequest.getAction());
			} else {
				list = locateOveridedNotes(loadContractComponentNoteRequest
						.getContractKeyObject().getDateSegmentId(),
						loadContractComponentNoteRequest
								.getBenefitComponentId(),
						WebConstants.ATTACH_BENEFIT_COMP,
						loadContractComponentNoteRequest
								.getBenefitComponentId(),
						loadContractComponentNoteRequest.getUser());
			}
			if (null != list) {
				loadContractComponentNoteResponse.setProductNotetList(list);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ loadContractComponentNoteResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			loadContractComponentNoteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(loadContractComponentNoteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return loadContractComponentNoteResponse;
	}

	/**
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param attach_benefit_comp
	 * @param benefitComponentId2
	 * @param user
	 * @return
	 * @throws WPDException
	 */
	private List locateOveridedNotesForDatafeed(int contractDateSegmentId,
			String secondaryEntityType, int benefitComponentId, User user,
			List entityIdList, int action) throws WPDException {
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractComponentNotesLocateCriteria contractComponentNotesLocateCriteria = new ContractComponentNotesLocateCriteria();
		contractComponentNotesLocateCriteria
				.setPrimaryEntityId(contractDateSegmentId);
		contractComponentNotesLocateCriteria
				.setPrimaryEntityType(WebConstants.ATTACH_CONTRACT);
		contractComponentNotesLocateCriteria
				.setSecondaryEntityType(secondaryEntityType);
		contractComponentNotesLocateCriteria
				.setBenefitComponentId(benefitComponentId);
		contractComponentNotesLocateCriteria.setEntityIdsList(entityIdList);
		contractComponentNotesLocateCriteria.setAction(action);
		LocateResults locateResults = bom.locate(
				contractComponentNotesLocateCriteria, user);
		return locateResults.getLocateResults();
	}

	/**
	 * Service method for UnAttaching overrided note Information methode for
	 * beenefit component associated to Contract .
	 * 
	 * @param request
	 *            DeleteContractComponentNoteRequest.
	 * @return DeleteContractComponentNoteResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			DeleteContractComponentNoteRequest deleteContractComponentNoteRequest)
			throws ServiceException {
		DeleteContractComponentNoteResponse deleteContractComponentNoteResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ deleteContractComponentNoteRequest.getClass().getName());

			deleteContractComponentNoteResponse = (DeleteContractComponentNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.DELETE_CONTRACT_COMPONENT_NOTE_RESPONSE);

			List messageList = new ArrayList(3);
			BusinessObjectManager bom = getBusinessObjectManager();
			Contract contract = getContract(deleteContractComponentNoteRequest);

			NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
			notesAttachmentOverrideBO
					.setPrimaryEntityId(deleteContractComponentNoteRequest
							.getContractKeyObject().getDateSegmentId());
			notesAttachmentOverrideBO
					.setPrimaryEntityType(WebConstants.ATTACH_CONTRACT);
			notesAttachmentOverrideBO
					.setSecondaryEntityId(deleteContractComponentNoteRequest
							.getBenefitComponentId());

			notesAttachmentOverrideBO
					.setSecondaryEntityType(WebConstants.ATTACH_BENEFIT_COMP);
			notesAttachmentOverrideBO
					.setNoteId(deleteContractComponentNoteRequest
							.getBenefitComponentNoteId());
			notesAttachmentOverrideBO
					.setBenefitComponentId(deleteContractComponentNoteRequest
							.getBenefitComponentId());
			bom.delete(notesAttachmentOverrideBO, contract,
					deleteContractComponentNoteRequest.getUser());
			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_NOTE_HIDE));
			deleteContractComponentNoteResponse.setMessages(messageList);
			deleteContractComponentNoteResponse
					.setProductNotetList(locateOveridedNotes(contract
							.getContractDateSegmentSysId(),
							deleteContractComponentNoteRequest
									.getBenefitComponentId(),
							WebConstants.ATTACH_BENEFIT_COMP,
							deleteContractComponentNoteRequest
									.getBenefitComponentId(),
							deleteContractComponentNoteRequest.getUser()));
			Logger.logInfo("Returning  from execute method for request="
					+ deleteContractComponentNoteResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			deleteContractComponentNoteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractComponentNoteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return deleteContractComponentNoteResponse;

	}

	public WPDResponse execute(RetrieveReservedContractIdRequest request)
			throws ServiceException {
		List lineOfBusiness = request.getLineOfBusiness();
		List businessGroup = request.getBusinessGroup();
		List businessEntity = request.getBusinessEntity();
		/* START CARS */
		List marketBusinessUnit = request.getMarketBusinessUnit();
		/* END CARS */
		String searchString = request.getSearchString();
		RetrieveReservedContractIdResponse response = new RetrieveReservedContractIdResponse();
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
		ContractIDSystemPool contractIDSystemPool = null;
		;
		ContractIDReservePool contractIDReservePool = null;
		try {
			contractIDSystemPool = contractIDRepositoryAdmin
					.getContractSystemPool();
			contractIDReservePool = contractIDRepositoryAdmin
					.getContractReservePool();
		} catch (ContractIDPoolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			throw new ServiceException("Adapter exception", null, e2);
		}

		List reservedIdList = null;
		List newList = new ArrayList();
		/* START CARS */
		if (lineOfBusiness.size() > 0 && businessEntity.size() > 0
				&& businessGroup.size() > 0 && marketBusinessUnit.size() > 0) {
			/* END CARS */
			try {
				List businessGrpTemp = contractIDReservePool
						.getBusinessGroupList("ETAB");
				List businessGrp = new ArrayList();
				Iterator iter = businessGrpTemp.iterator();
				while (iter.hasNext()) {
					ContractIDReservePoolRecord obj = (ContractIDReservePoolRecord) iter
							.next();
					if (null != obj) {
						businessGrp.add(obj.getContractId());
					}
				}
				/* START CARS */
				reservedIdList = adapterManager.getReservedContracts(
						lineOfBusiness, businessGroup, businessEntity,
						marketBusinessUnit, businessGrp, searchString);
				/* END CARS */
				int size = reservedIdList.size();
				if (size > 500) {
					response.addMessage(new InformationalMessage(
							BusinessConstants.EXP_EXCEED_LIMIT));
					newList = reservedIdList.subList(0, 500);
				} else {
					newList = reservedIdList;
				}
			} catch (ContractIDPoolException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			} catch (SevereException e) {
				throw new ServiceException("Adapter exception", null, e);
			}
			if (null == reservedIdList || reservedIdList.size() == 0) {
				response.addMessage(new InformationalMessage(
						BusinessConstants.MSG_CONTRACT_SYSTEM_POOL_EXPIRED));
			}
		} else {
			// response.addMessage(new
			// ErrorMessage(BusinessConstants.MSG_CONTRACT_DOMAIN_INVALID));
			response.addMessage(new InformationalMessage(
					BusinessConstants.MSG_CONTRACT_SYSTEM_POOL_EXPIRED));
		}
		response.setReservedContractIds(newList);
		return response;
	}

	public WPDResponse execute(
			ContractNotesDeleteRequest contractNotesDeleteRequest)
			throws WPDException {

		ContractNotesDeleteResponse contractNotesDeleteResponse = null;
		try {
			DateSegment dateSegment = getDateSegment(contractNotesDeleteRequest);
			// Create the business object manager object
			BusinessObjectManager bom = getBOM();
			// Get response from responsefactory
			contractNotesDeleteResponse = (ContractNotesDeleteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_NOTES_DELETE_RESPONSE);
			// set values from request to subobject
			AttachedNotesBO noteattachmentOverrideBO = new AttachedNotesBO();
			noteattachmentOverrideBO.setNoteId(contractNotesDeleteRequest
					.getNoteId());
			noteattachmentOverrideBO.setEntityId(contractNotesDeleteRequest
					.getContractKeyObject().getDateSegmentId());
			noteattachmentOverrideBO.setEntityType(contractNotesDeleteRequest
					.getEntityType());

			// calling the delete method of BOM
			bom.delete(noteattachmentOverrideBO, dateSegment,
					contractNotesDeleteRequest.getUser());
			// set status message
			contractNotesDeleteResponse.addMessage(new InformationalMessage(
					BusinessConstants.MSG_CONTRACT_NOTES_DELETE_SUCCESS));
			// retumn response
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			contractNotesDeleteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(contractNotesDeleteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(contractNotesDeleteRequest);
			String logMessage = "Failed while processing contractNotesDeleteRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return contractNotesDeleteResponse;
	}

	public WPDResponse execute(
			RetrieveContractNoteRequest retrieveContractNoteRequest)
			throws ServiceException {
		RetrieveContractNoteResponse retrieveContractNoteResponse = null;
		try {

			Logger.logInfo("Entering execute method, request = "
					+ retrieveContractNoteRequest.getClass().getName());

			retrieveContractNoteResponse = (RetrieveContractNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_NOTE_RESPONSE);

			BusinessObjectManager bom = getBusinessObjectManager();
			List list = locateContractNotes(retrieveContractNoteRequest,
					retrieveContractNoteRequest.getDateSegmentId(),
					retrieveContractNoteRequest.getEntityType());
			if (null != list) {
				retrieveContractNoteResponse.setNoteList(list);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ retrieveContractNoteResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractNoteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveContractNoteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveContractNoteResponse;
	}

	/**
	 * Service method for Attaching overrided note Information methode for
	 * benefit associated to Contract .
	 * 
	 * @param request
	 *            SaveContractBenefitNoteRequest.
	 * @return SaveContractBenefitNoteResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			SaveContractBenefitNoteRequest saveContractBenefitNoteRequest)
			throws ServiceException {
		SaveContractBenefitNoteResponse saveContractBenefitNoteResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ saveContractBenefitNoteRequest.getClass().getName());

			saveContractBenefitNoteResponse = (SaveContractBenefitNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_BENEFIT_NOTE_RESPONSE);

			BusinessObjectManager bom = getBusinessObjectManager();
			List messageList = new ArrayList(5);
			DateSegment dateSegment = getDateSegment(saveContractBenefitNoteRequest);
			List notesVOList = saveContractBenefitNoteRequest.getNoteList();
			List versionList = saveContractBenefitNoteRequest.getVersionList();
			List contractEntitylist = null;
			String secondaryEntityType = "ATTACHBENEFIT";
			int productKey;
			int i = 0;
			ContractNotes contractNotes = new ContractNotes();
			if (null != notesVOList) {
				contractEntitylist = new ArrayList(notesVOList.size());
				for (i = 0; i < notesVOList.size(); i++) {
					NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
					notesAttachmentOverrideBO.setNoteId((notesVOList.get(i))
							.toString());
					notesAttachmentOverrideBO
							.setPrimaryEntityId(saveContractBenefitNoteRequest
									.getContractKeyObject().getDateSegmentId());
					notesAttachmentOverrideBO
							.setPrimaryEntityType(BusinessConstants.ATTACH_CONTRACT);
					notesAttachmentOverrideBO
							.setSecondaryEntityId(saveContractBenefitNoteRequest
									.getBenefitId());
					notesAttachmentOverrideBO
							.setSecondaryEntityType(secondaryEntityType);
					notesAttachmentOverrideBO
							.setBenefitComponentId(saveContractBenefitNoteRequest
									.getBenefitComponentId());
					if (null != versionList) {
						notesAttachmentOverrideBO.setVersion(Integer
								.parseInt((String) versionList.get(i)));
					}
					notesAttachmentOverrideBO
							.setOverrideStatus(saveContractBenefitNoteRequest
									.getOverrideStatus());
					contractEntitylist.add(notesAttachmentOverrideBO);
				}
			}
			contractNotes.setNoteList(contractEntitylist);
			contractNotes.setAction(2);
			bom.persist(contractNotes, dateSegment,
					saveContractBenefitNoteRequest.getUser(), true);
			if (saveContractBenefitNoteRequest.getAction() == SaveContractBenefitNoteRequest.SAVE_NOTE) {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_NOTE_ATTACHED));
				saveContractBenefitNoteResponse.setMessages(messageList);
				saveContractBenefitNoteResponse.setSuccess(true);
			} else {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_NOTE_HIDE));
				saveContractBenefitNoteResponse.setMessages(messageList);
				saveContractBenefitNoteResponse.setSuccess(true);
			}
			List list = locateOveridedNotes(saveContractBenefitNoteRequest
					.getContractKeyObject().getDateSegmentId(),
					saveContractBenefitNoteRequest.getBenefitId(),
					secondaryEntityType, saveContractBenefitNoteRequest
							.getBenefitComponentId(),
					saveContractBenefitNoteRequest.getUser());
			if (null != list) {
				saveContractBenefitNoteResponse.setContractNotesList(list);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ saveContractBenefitNoteResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			saveContractBenefitNoteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractBenefitNoteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractBenefitNoteRequest);
			String logMessage = "Failed while processing saveContractBenefitNoteRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return saveContractBenefitNoteResponse;
	}

	/**
	 * Service method for Retrieving overrided note Information methode for
	 * benefit associated to Contract .
	 * 
	 * @param request
	 *            LoadContractBenefitNoteRequest.
	 * @return LoadContractBenefitNoteResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			LoadContractBenefitNoteRequest loadContractBenefitNoteRequest)
			throws ServiceException {
		LoadContractBenefitNoteResponse loadContractBenefitNoteResponse = null;
		List list = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ loadContractBenefitNoteRequest.getClass().getName());
			String secondaryEntityType = "ATTACHBENEFIT";
			loadContractBenefitNoteResponse = (LoadContractBenefitNoteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.LOAD_CONTRACT_BENEFIT_NOTE_RESPONSE);

			BusinessObjectManager bom = getBusinessObjectManager();
			if (loadContractBenefitNoteRequest.getAction() == 200) {
				list = locateOveridedNotesForDatafeed(
						loadContractBenefitNoteRequest.getContractKeyObject()
								.getDateSegmentId(), secondaryEntityType,
						loadContractBenefitNoteRequest.getBenefitComponentId(),
						loadContractBenefitNoteRequest.getUser(),
						loadContractBenefitNoteRequest.getBenefitIdList(),
						loadContractBenefitNoteRequest.getAction());
			} else {
				list = locateOveridedNotes(loadContractBenefitNoteRequest
						.getContractKeyObject().getDateSegmentId(),
						loadContractBenefitNoteRequest.getBenefitId(),
						secondaryEntityType, loadContractBenefitNoteRequest
								.getBenefitComponentId(),
						loadContractBenefitNoteRequest.getUser());
			}
			if (null != list) {
				loadContractBenefitNoteResponse.setContractNotetList(list);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ loadContractBenefitNoteResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			loadContractBenefitNoteResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(loadContractBenefitNoteRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {

			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return loadContractBenefitNoteResponse;
	}

	public WPDResponse execute(RetrieveBenefitHeadingsRequest request)
			throws ServiceException {
		RetrieveBenefitHeadingsResponse retrieveBenefitHeadingsResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			int dateSegmentId;
			dateSegmentId = request.getDateSegmentId();
			int productId = request.getProductId();

			retrieveBenefitHeadingsResponse = (RetrieveBenefitHeadingsResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_HEADINGS_RESPONSE);

			ContractBusinessObjectBuilder bom = new ContractBusinessObjectBuilder();
			LocateResults locateResults = bom.locate(dateSegmentId, productId,
					request.getUser());
			retrieveBenefitHeadingsResponse
					.setBenefitHeadingsList(locateResults.getLocateResults());
			List messageList = new ArrayList(2);
			if (locateResults.getLocateResults().size() == 0) {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_BEN_DFN_NOT_FOUND));
				addMessagesToResponse(retrieveBenefitHeadingsResponse,
						messageList);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveBenefitHeadingsResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveBenefitHeadingsResponse;
	}

	public WPDResponse execute(ReservedContractSearchRequest request)
			throws ServiceException {
		ReservedContractSearchResponse reservedContractSearchResponse = null;

		try {
			reservedContractSearchResponse = (ReservedContractSearchResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RESERVED_CONTRACT_SEARCH_RESPONSE);

			// BusinessObjectManager bom = getBusinessObjectManager();
			ContractLocateCriteria contractLocateCriteria = new ContractLocateCriteria();
			ContractBusinessObjectBuilder bom = new ContractBusinessObjectBuilder();
			List locateResults = null;

			String contractId = request.getContractId();
			List LOB = request.getLob();
			List businessGroup = request.getBusinessGroup();
			List businessEntity = request.getBusinessEntity();
			List marketBusinessUnit = request.getMarketBusinessUnit();

			locateResults = bom.locateReservedContract(contractId, LOB,
					businessGroup, businessEntity, marketBusinessUnit);
			if (locateResults.size() >= 50) {
				reservedContractSearchResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
			} else if (locateResults.size() <= 0) {
				reservedContractSearchResponse.addMessage(new ErrorMessage(
						BusinessConstants.SEARCH_RESULT_NOT_FOUND));
			}
			reservedContractSearchResponse.setSearchResultList(locateResults);
			reservedContractSearchResponse.setSuccess(true);

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			reservedContractSearchResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing ReservedContractSearchRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return reservedContractSearchResponse;

	}

	// ------------------------------------ for admin option start
	// -----------------------------------------
	/**
	 * Service method for Retrieving Admin Information.
	 * 
	 * @param request
	 *            RetrieveContractProductAdminRequest.
	 * @return ProductAdminResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveContractProductAdminRequest request)
			throws ServiceException {
		RetrieveContractProductAdminResponse retrieveContractProductAdminResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			ContractProductAdminBO productComponentBO = new ContractProductAdminBO();
			// List adminList = new ArrayList();
			List adminList = null;
			retrieveContractProductAdminResponse = (RetrieveContractProductAdminResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_PROD_ADMIN_RESPONSE);
			ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
			int action = request.getAction();
			ProductBO productBO = new ProductBO();
			ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();

			switch (action) {
			case RetrieveContractProductAdminRequest.PRODUCT_ADMIN_ADDED:
				adminList = locateAssociatedAdmin(request
						.getContractKeyObject().getDateSegmentId(), request
						.getUser());
				break;
			case RetrieveContractProductAdminRequest.PRODUCT_ADMIN_POPUP:
				adminList = contractAdapterManager.getAdminListforPopup(request
						.getContractKeyObject().getDateSegmentId(), request
						.getProductKey());
				if (adminList == null || adminList.size() == 0) {
					retrieveContractProductAdminResponse
							.addMessage(new InformationalMessage(
									BusinessConstants.MSG_POPUP_ADMIN_NOT_FOUND));
					// product.admin.notfound= admin option not found.
				}
				break;
			case RetrieveContractProductAdminRequest.PRODUCT_ADMIN_RETRIEVE:
				Map params = new HashMap();
				BusinessObjectManager bom = getBusinessObjectManager();
				// sets benefit component id to the map
				params.put("adminId", new Integer(request.getAdminId()));

				productBO = (ProductBO) bom.retrieve(productBO, request
						.getUser(), params);
				if (null != productBO && null != productBO.getAdminList()) {
					retrieveContractProductAdminResponse
							.setAdminDetails((AdminBO) productBO.getAdminList()
									.get(0));
				}
				// sets the domain details to the response
				retrieveContractProductAdminResponse.setDomainDetail(DomainUtil
						.retrieveDomainDetailInfo("ADMINOPTION",
								retrieveContractProductAdminResponse
										.getAdminDetails().getAdminParentId()));
				break;

			case RetrieveContractProductAdminRequest.CONTRACT_ADJUDICATION:
				adminList = contractBuilder.getContractAdjudicationIndicator(
						request.getContractKeyObject().getDateSegmentId(),
						request.getReferenceId());
				break;

			case RetrieveContractProductAdminRequest.MEMBER_SPEC_INDICATOR:
				adminList = contractBuilder
						.getContractMemberSpecIndicator(request
								.getContractKeyObject().getDateSegmentId());
				break;
			}
			retrieveContractProductAdminResponse.setAdminList(adminList);
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractProductAdminResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveContractProductAdminRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveContractProductAdminResponse;

	}

	public WPDResponse execute(
			DeleteContractProductAdminRequest deleteContractProductAdminRequest)
			throws ServiceException {
		DeleteContractProductAdminResponse deleteContractProductAdminResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ deleteContractProductAdminRequest.getClass().getName());
			deleteContractProductAdminResponse = (DeleteContractProductAdminResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.DELETE_CONTRACT_PRODUCT_ADMIN_RESPONSE);

			List messageList = new ArrayList(3);
			BusinessObjectManager bom = getBusinessObjectManager();
			DateSegment dateSegment = getDateSegment(deleteContractProductAdminRequest);
			List adminIdList = deleteContractProductAdminRequest.getAdminKey();
			List changedIds = new ArrayList(2);
			for (int adminCount = 0; adminCount < adminIdList.size(); adminCount++) {
				ContractProductAdminBO contractProductAdminBO = new ContractProductAdminBO();
				contractProductAdminBO
						.setContractDateSegmentSysKey(deleteContractProductAdminRequest
								.getContractKeyObject().getDateSegmentId());
				contractProductAdminBO.setAdminKey(((Integer) adminIdList
						.get(adminCount)).intValue());
				contractProductAdminBO
						.setEntityType(BusinessConstants.ENTITY_CONTRACT);
				bom.delete(contractProductAdminBO, dateSegment,
						deleteContractProductAdminRequest.getUser());
				changedIds.add(new Integer((adminIdList.get(adminCount)
						.toString())));
			}

			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(deleteContractProductAdminRequest
					.getContractKeyObject().getProductId());
			validationRqst.setEntityId(deleteContractProductAdminRequest
					.getContractKeyObject().getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setChangedIds(changedIds);
			validationRqst.setContractId(deleteContractProductAdminRequest
					.getContractKeyObject().getContractSysId());
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_IN_PRODUCT);

			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);

			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_PRODUCT_ADMINOPTION_DELETE));
			// product.adminoption.delete= Admin option deleted successfully.
			deleteContractProductAdminResponse.setMessages(messageList);
			Logger.logInfo("Returning  from execute method for request="
					+ deleteContractProductAdminResponse.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			deleteContractProductAdminResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractProductAdminRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractProductAdminRequest);
			String logMessage = "Failed while processing DeleteContractProductAdminRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(deleteContractProductAdminRequest);
			String logMessage = "Failed while processing DeleteContractProductAdminRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return deleteContractProductAdminResponse;
	}

	/**
	 * Service method for Admin to product.
	 * 
	 * @param request
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(SaveContractProductAdminRequest request)
			throws ServiceException {
		SaveContractProductAdminResponse response = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			response = (SaveContractProductAdminResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_PROD_ADMIN_RESPONSE);

			int action = request.getAction();
			List adminVOList = request.getAdminList();

			Iterator iterComponent = adminVOList.iterator();
			BusinessObjectManager bom = getBusinessObjectManager();

			DateSegment dateSegment = getDateSegment(request);
			User user = request.getUser();
			List messageList = new ArrayList(3);
			List adminList = null;
			List changedIds = null;
			if (null != adminVOList) {
				adminList = new ArrayList(adminVOList.size());
				changedIds = new ArrayList(adminVOList.size());
			}

			ProductBusinessObjectBuilder productBuilder = new ProductBusinessObjectBuilder();
			ContractAdapterManager adapterManager = new ContractAdapterManager();
			ContractProductAdmin contractProductAdmin = new ContractProductAdmin();
			ContractProductAdminBO contractProductAdminBO = null;

			switch (action) {
			case SaveContractProductAdminRequest.SAVE_ADMIN:
				int productKey = request.getProductKey();

				int dateSegmentKey = request.getContractKeyObject()
						.getDateSegmentId();
				while (iterComponent.hasNext()) {
					contractProductAdminBO = new ContractProductAdminBO();
					contractProductAdminBO
							.setEntityType(BusinessConstants.ENTITY_CONTRACT);
					int adminKey = ((Integer) iterComponent.next()).intValue();
					contractProductAdminBO.setAdminKey(adminKey);
					changedIds.add(new Integer(adminKey));
					contractProductAdminBO
							.setContractDateSegmentSysKey(dateSegmentKey);
					contractProductAdminBO.setProductKey(productKey);
					int sequence = adapterManager.getAdminSequence(adminKey,
							productKey);
					contractProductAdminBO.setSequence(sequence);
					adminList.add(contractProductAdminBO);
				}
				contractProductAdmin.setAdminList(adminList);
				bom.persist(contractProductAdmin, dateSegment, user, true);

				if (null != changedIds && changedIds.size() > 0) {
					AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
					validationRqst.setProductId(request.getContractKeyObject()
							.getProductId());
					validationRqst.setEntityId(request.getContractKeyObject()
							.getDateSegmentId());
					validationRqst
							.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
					validationRqst.setChangedIds(changedIds);
					validationRqst
							.setLevel(AdminMethodSynchronousValidationRequest.SAVE_ADMIN_OPTIONS_IN_PRODUCT);
					validationRqst.setContractId(request.getContractKeyObject()
							.getContractSysId());

					AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
							.execute(validationRqst);
				}

				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_ADMIN_SAVED));
				// product.admin.saved= Admin option(s) attached successfully.
				response.setMessages(messageList);
				response.setSuccess(true);
				break;
			}
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing SaveContractProductAdminRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing BenefitCustomizationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	public WPDResponse execute(
			SaveRuleInformationRequest saveRuleInformationRequest)
			throws ServiceException, AdapterException {
		SaveRuleInformationResponse response = (SaveRuleInformationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_RULEID_CONTRACTBEN_RESPONSE);
		RuleIdBO ruleIdBO = setValuesToBO(saveRuleInformationRequest);
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		try {
			builder.persist(ruleIdBO, saveRuleInformationRequest
					.getLastUpdatesUser());
		} catch (SevereException e) {
			List logParameters = new ArrayList(2);
			logParameters.add(saveRuleInformationRequest);
			String logMessage = "Failed while processing SaveRuleInformationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return response;
	}

	private RuleIdBO setValuesToBO(
			SaveRuleInformationRequest saveRuleInformationRequest) {
		RuleIdBO ruleIdBO = new RuleIdBO();
		ruleIdBO.setBenefitMeaning(saveRuleInformationRequest
				.getBenefitMeaning());
		ruleIdBO.setPrimaryCode(saveRuleInformationRequest.getRuleId());
		ruleIdBO.setBenefitId(new Integer(saveRuleInformationRequest
				.getBenefitId()).intValue());
		ruleIdBO.setBenefitComponentId(saveRuleInformationRequest
				.getBenefitComponentId());
		ruleIdBO
				.setDateSegmentId(saveRuleInformationRequest.getDateSegmentId());
		return ruleIdBO;
	}

	/**
	 * Method to set values to businessObject from values object of Product
	 * Admin.
	 * 
	 * @param valueObject
	 *            ProductAdminVO
	 * @param businessObject
	 *            ContractProductAdminBO
	 */

	private void setValuesToProductAdminBO(ProductAdminVO valueObject,
			ContractProductAdminBO businessObject) {
		businessObject.setProductKey(valueObject.getProductKey());
		businessObject.setAdminKey(valueObject.getAdminKey());
		businessObject.setAdminVersion(valueObject.getAdminVersion());
		businessObject.setAdminDesc(valueObject.getAdminDesc());
		businessObject.setSequence(valueObject.getSequence());
	}

	/**
	 * Method to locate the associated admin for product using business object
	 * manager.
	 * 
	 * @param contractDateSegmentSysKey
	 * @param user
	 * @return List of Associated Admin
	 * @throws WPDException
	 */
	public List locateAssociatedAdmin(int contractDateSegmentSysKey, User user)
			throws WPDException {
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractAdminLocateCriteria locateCriteria = new ContractAdminLocateCriteria();
		locateCriteria.setContractDateSegmentSysKey(contractDateSegmentSysKey);
		LocateResults locateResults = bom.locate(locateCriteria, user);
		return locateResults.getLocateResults();
	}

	// ------------------------------------ admin option end
	// -----------------------------------------
	/**
	 * Method to generate contract Id based on group code
	 * 
	 * @param SaveContractBasicInfoRequest
	 *            request
	 * @return contractId
	 * @throws SevereException
	 */
	private String generateContractIdFromGroup(
			SaveContractBasicInfoRequest request) throws SevereException {
		String contractId = "";
		ContractVO contractVO = request.getContractVO();
		if (contractVO.getGroupSize().equalsIgnoreCase("LG")) {
			contractId = "$";
		} else if (contractVO.getGroupSize().equalsIgnoreCase("SG")) {
			contractId = "#";
		} else if (contractVO.getGroupSize().equalsIgnoreCase("IND")) {
			contractId = "*";
		} else {
			contractId = "@";
		}
		contractId = getContractIdFromProduct(contractId, contractVO);
		return contractId;
	}

	/**
	 * Method to generate contract Id based on product code
	 * 
	 * @param String
	 *            contractId
	 * @param ContractVO
	 *            contractVO
	 * @return contractId
	 * @throws SevereException
	 */
	private String getContractIdFromProduct(String contractId,
			ContractVO contractVO) throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		ProductBO productBO = builder.getProductCode(contractVO.getProduct());
		String productCode = productBO.getProductFamilyDesc();
		if (productCode.equalsIgnoreCase("EPO")) {
			contractId = contractId + "X";
		} else if (productCode.equalsIgnoreCase("FFS")) {
			contractId = contractId + "F";
		} else if (productCode.equalsIgnoreCase("HMO")) {
			contractId = contractId + "H";
		} else if (productCode.equalsIgnoreCase("POS")) {
			contractId = contractId + "Z";
		} else if (productCode.equalsIgnoreCase("PPO")) {
			contractId = contractId + "P";
		} else {
			contractId = contractId + "@";
		}
		contractId = contractId + contractVO.getHeadQuartersStateCode();
		return contractId;
	}

	/**
	 * Method to validate if contract Id is unique.
	 * 
	 * @param String
	 *            contractId
	 * @return boolean contractIdExists
	 * @throws SevereException
	 */
	private boolean validateContractId(String contractId)
			throws SevereException {
		boolean contractIdExists = false;
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		contractIdExists = builder.searchContractId(contractId);
		return contractIdExists;
	}

	/**
	 * Service method to retrieve Benefit Administration .
	 * 
	 * @param request
	 *            RetrieveContractProductAdminOptionOverrideRequest.
	 * @return RetrieveContractProductAdminOptionOverrideResponse.
	 * @throws WPDException
	 */
	public WPDResponse execute(
			RetrieveContractProductAdminOptionOverrideRequest request)
			throws ServiceException {
		RetrieveContractProductAdminOptionOverrideResponse retrieveContractProductAdminOptionOverrideResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			retrieveContractProductAdminOptionOverrideResponse = (RetrieveContractProductAdminOptionOverrideResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CONTRACT_ADMIN_OPTION_FOR_CONTRACT);
			ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();

			ContractAdminOptionLocateCriteria contractAdminOptionLocateCriteria = new ContractAdminOptionLocateCriteria();
			User user = request.getUser();

			int action = request.getAction();
			switch (action) {
			case RetrieveContractProductAdminOptionOverrideRequest.LOAD_CONTRACT_QUESTIONNAIRE:
			case RetrieveContractProductAdminOptionOverrideRequest.LOAD_CONTRACT_QUESTIONNAIRE_DF:
				contractAdminOptionLocateCriteria.setAction(action);
				contractAdminOptionLocateCriteria.setEntityId(request
						.getEntityId());
				contractAdminOptionLocateCriteria.setAdminOptSysId(request
						.getAdminOptSysId());
				contractAdminOptionLocateCriteria
						.setEntityType(BusinessConstants.ENTITY_CONTRACT);
				break;
			case RetrieveContractProductAdminOptionOverrideRequest.LOAD_CHILD_QUESTIONNAIRE:
				contractAdminOptionLocateCriteria.setAction(action);
				contractAdminOptionLocateCriteria.setSelectedAnswerId(request
						.getSelectedAnswerId());
				contractAdminOptionLocateCriteria
						.setQuestionareListIndex(request
								.getQuestionareListIndex());
				contractAdminOptionLocateCriteria.setQuestionnaireList(request
						.getQuestionnareList());
				contractAdminOptionLocateCriteria
						.setContractAssnQuestionnaireBO(request
								.getContractAssnQuestionnaireBO());
				contractAdminOptionLocateCriteria.setContractSytemId(request
						.getEntityId());
				break;
			}
			LocateResults locateResults = contractBusinessObjectBuilder.locate(
					contractAdminOptionLocateCriteria, user);
			retrieveContractProductAdminOptionOverrideResponse
					.setQuestionnaireList(locateResults.getLocateResults());

			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractProductAdminOptionOverrideResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveContractProductAdminOptionOverrideResponse;
	}

	/**
	 * Updates the overridden answers to the db
	 * 
	 * @param SaveContractAdministrationRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			SaveContractAdministrationRequest administrationRequest)
			throws ServiceException {
		Logger
				.logInfo("Entering execute method for saving benefit administration");
		// Create the response object from the response factory
		SaveContractAdministrationResponse administrationResponse = (SaveContractAdministrationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_ADMIN_OPTION_FOR_CONTRACT);
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		try {
			BusinessObjectManager bom = getBusinessObjectManager();
			DateSegment dateSegment = getDateSegment(administrationRequest);

			ContractAssnQuestionnaireBO contractAssnQuesitionnaireBO = new ContractAssnQuestionnaireBO();
			contractAssnQuesitionnaireBO.setAdminOptionId(administrationRequest
					.getAdminSysId());
			contractAssnQuesitionnaireBO.setDateSegmentId(administrationRequest
					.getDateSegmentId());
			contractAssnQuesitionnaireBO
					.setQuestionnaireList(administrationRequest
							.getQuestionnareList());

			bom.persist(contractAssnQuesitionnaireBO, dateSegment,
					administrationRequest.getUser(), true);
			updateAMVForCnrctPrdctAdmin(administrationRequest);

			// Set the update success message to the response
			List messageList = new ArrayList(2);
			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_QUESTIONNARE_SAVE_BC_SUCCESS));
			addMessagesToResponse(administrationResponse, messageList);
		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			administrationResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			String logMessage = "Failed while processing SaveProductAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(administrationRequest);
			String logMessage = "Failed while processing SaveProductAdministrationRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
				.logInfo("Returning execute method for saving benefit administration");
		return administrationResponse;
	}

	/**
	 * @param administrationRequest
	 * @throws ServiceException
	 */
	private void updateAMVForCnrctPrdctAdmin(
			SaveContractAdministrationRequest req) throws ServiceException {

		if (null != req && req.isChanged()) {
			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
			validationRqst.setProductId(req.getContractKeyObject()
					.getProductId());
			validationRqst.setEntityId(req.getContractKeyObject()
					.getDateSegmentId());
			validationRqst
					.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_CONTRACT);
			validationRqst.setAdminSysId(req.getAdminSysId());
			validationRqst.setChangedIds(req.getChangedIds());
			validationRqst
					.setLevel(AdminMethodSynchronousValidationRequest.PRDCT_ADMIN_OPTION_QSTNS_SAVE);
			validationRqst.setContractId(req.getContractKeyObject()
					.getContractSysId());
			validationRqst.setBenefitId(req.getBenefitId());
			validationRqst.setBenefitComponentId(req.getBeneCompId());

			AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
					.execute(validationRqst);
		}

	}

	/**
	 * Datafeed request for retrieving various data.
	 * 
	 * @param datafeedRequest
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(DatafeedRequest datafeedRequest)
			throws ServiceException, AdapterException {
		DataFeedStatus dataFeedStatus = datafeedRequest.getDataFeedStatus();
		DatafeedLocateCriteria datafeedLocateCriteria = new DatafeedLocateCriteria();
		datafeedLocateCriteria.setStatus(datafeedRequest.getStatus());
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
		ContractTreeBuilder builder = new ContractTreeBuilder();
		StandardBenefitTreeBuilder standardBenefitTreeBuilder = new StandardBenefitTreeBuilder();
		ContractTreeProducts contractTreeProducts = null;
		Contract contract = new Contract();
		ProductTreeBenefitComponents productTreeBenefitComponents = null;
		ProductTreeStandardBenefits productTreeStandardBenefits = null;
		User user = datafeedRequest.getUser();
		DatafeedResponse datafeedResponse = (DatafeedResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DATAFEED_REQUEST_CNTRCT_RETRIEVE);
		ContractAdapterManager adapterManager = new ContractAdapterManager();

		switch (datafeedRequest.getAction()) {
		case BusinessConstants.DATAFEED_RETRIEVE_CONTRACT:
			try {
				datafeedLocateCriteria.setContractType(datafeedRequest
						.getAction());
				LocateResults locateResults = contractBuilder.locate(
						datafeedLocateCriteria, datafeedRequest.getUser());
				datafeedResponse.setSearchResultList(locateResults
						.getLocateResults());
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_MANDATE_CONTRACT:

			try {
				datafeedLocateCriteria.setContractType(datafeedRequest
						.getAction());
				LocateResults locateResults = contractBuilder.locate(
						datafeedLocateCriteria, datafeedRequest.getUser());
				datafeedResponse.setSearchResultList(locateResults
						.getLocateResults());
			} catch (WPDException exception) {
				throw new ServiceException("Exception occured while BOM call",
						null, exception);
			}
			break;

		case BusinessConstants.RETRIEVE_TIER_CODE_AND_VALUES:

			try {
				datafeedResponse.setSearchResultList(contractBuilder
						.getTierCodeAndValues(datafeedRequest
								.getDateSegmentSysId()));

			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_SPSBENEFITLINES:

			try {

				datafeedResponse
						.setSpsValueMap(contractBuilder
								.getSPSBnftLines(datafeedRequest
										.getDateSegmentSysId()));

				datafeedResponse.getSpsValueMap().put(
						"contractAdminMethods",
						contractBuilder
								.getContractDFAdminMethods(datafeedRequest
										.getDateSegmentSysId()));

				datafeedResponse.getSpsValueMap().put(
						"contractNotes",
						contractBuilder.getContractDFNotes(datafeedRequest
								.getDateSegmentSysId()));

				datafeedResponse.getSpsValueMap().put(
						"contractBenefits",
						contractBuilder.getContractDFBenefits(datafeedRequest
								.getDateSegmentSysId()));

				datafeedResponse.getSpsValueMap().put(
						"contractQuestions",
						contractBuilder.getContractDFQuestions(datafeedRequest
								.getDateSegmentSysId()));

			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;
		case BusinessConstants.DATAFEED_RETRIEVE_PRODUCT:
			contractTreeProducts = new ContractTreeProducts();
			contractTreeProducts.setContractId(datafeedRequest
					.getContractSysId());
			contractTreeProducts.setDateSegmentId(datafeedRequest
					.getDateSegmentSysId());
			try {
				contractTreeProducts = (ContractTreeProducts) builder
						.retrieveProducts(contractTreeProducts);
				datafeedResponse.setContractTreeProducts(contractTreeProducts);

			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_BENEFITCOMPONENT:
			productTreeBenefitComponents = new ProductTreeBenefitComponents();
			productTreeBenefitComponents.setProductId(datafeedRequest
					.getProductId());
			productTreeBenefitComponents.setDateSegmentId(datafeedRequest
					.getDateSegmentSysId());
			try {
				datafeedResponse.setSearchResultList(builder
						.getBenefitComponents(productTreeBenefitComponents));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_BENEFIT:
			productTreeStandardBenefits = new ProductTreeStandardBenefits();
			productTreeStandardBenefits.setBenefitComponentId(datafeedRequest
					.getBenefitComponentId());
			productTreeStandardBenefits.setDateSegmentId(datafeedRequest
					.getDateSegmentSysId());
			try {
				datafeedResponse.setSearchResultList(builder
						.getStandardBenefits(productTreeStandardBenefits));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_STATUS_SET:
			try {
				datafeedResponse.setSuccess(contractBuilder
						.updateDataFeedStatus(dataFeedStatus, dataFeedStatus
								.getUser()));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_STATUS_UNSET:
			try {
				datafeedResponse.setSuccess(contractBuilder
						.updateDataFeedStatus(dataFeedStatus, dataFeedStatus
								.getUser()));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_ENTITY_RULE_ASSN:
			AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
			assignedRuleIdBO.setDateSegmentId(datafeedRequest
					.getDateSegmentSysId());
			assignedRuleIdBO.setProductId(datafeedRequest.getProductId());
			assignedRuleIdBO.setAction(BusinessConstants.DATAFEED_ACTION);
			try {

				List list = contractBuilder
						.searchEntityRuleAssociation(assignedRuleIdBO);
				if (null != list) {
					datafeedResponse.setSearchResultList(list);
				}
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_STATUS_UPDATE_PROD:

			contract.setContractSysId(datafeedRequest.getContractSysId());
			contract.setVersion(datafeedRequest.getContractKeyObject()
					.getVersion());
			contract.setLastUpdatedUser(datafeedRequest.getLastUpdatedUser());
			contract.setLastUpdatedTimestamp(datafeedRequest
					.getLastUpdatedTimeStamp());
			contract.setStatus(datafeedRequest.getStatus());
			contract.setContractId(datafeedRequest.getContractKeyObject()
					.getContractId());
			try {
				List dateSegmentListLC = retrieveValidStatusDatesegments(
						contract.getContractSysId(), null);
				if (null != dateSegmentListLC && !dateSegmentListLC.isEmpty()) {
					Iterator lcItr = dateSegmentListLC.iterator();
					while (lcItr.hasNext()) {
						DateSegment datsegment = (DateSegment) lcItr.next();
						bom.publish(datsegment, user);
					}
				}
				bom.publish(contract, datafeedRequest.getUser());
				// Updating the datesegment modified status in the
				// CNTRCT_DT_SGMNT_ASSN table as P
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(datafeedRequest.getUser());
				contractBuilder.updateDateSegmentAssnInfo(contract, audit);
				// Deleting the record corresponding to the contract from the
				// DLTD_DT_SGMNT table
				contractBuilder.updateDeleteDateSegmentInfo(datafeedRequest
						.getDeletedDSList(), contract.getStatus(), audit);

				if (datafeedRequest.isRootDeleted()) {
					rootDeleteContract(datafeedRequest, audit);
				}
			} catch (WPDException e) {
				throw new ServiceException("Exception occured while BOM call",
						null, e);
			}
			break;

		case BusinessConstants.DATAFEED_STATUS_UPDATE_TEST:
			contract.setContractSysId(datafeedRequest.getContractSysId());
			contract.setVersion(datafeedRequest.getContractKeyObject()
					.getVersion());
			contract.setLastUpdatedUser(datafeedRequest.getLastUpdatedUser());
			contract.setLastUpdatedTimestamp(datafeedRequest
					.getLastUpdatedTimeStamp());
			contract.setStatus(datafeedRequest.getStatus());
			contract.setContractId(datafeedRequest.getContractKeyObject()
					.getContractId());
			try {
				List dateSegmentListLC = retrieveValidStatusDatesegments(
						contract.getContractSysId(), null);
				if (null != dateSegmentListLC && !dateSegmentListLC.isEmpty()) {
					Iterator lcItr = dateSegmentListLC.iterator();
					while (lcItr.hasNext()) {
						DateSegment datsegment = (DateSegment) lcItr.next();
						bom.transfer(datsegment, user);
					}
				}
				bom.transfer(contract, datafeedRequest.getUser());
				// Updating the datesegment modified status in the
				// CNTRCT_DT_SGMNT_ASSN table as T
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(datafeedRequest.getUser());
				contractBuilder.updateDateSegmentAssnInfo(contract, audit);

				// Updating the deleted test/prod indicator in the DLTD_DT_SGMNT
				// table as T
				contractBuilder.updateDeleteDateSegmentInfo(datafeedRequest
						.getDeletedDSList(), contract.getStatus(), audit);
				// Delete the Hard deleted contracts from Tranfer_log table
				deleteHardDeletedCnts(audit);
				// root delete update in the contract master table
				if (datafeedRequest.isRootDeleted()) {
					rootDeleteContract(datafeedRequest, audit);
				}
			} catch (WPDException e) {
				throw new ServiceException("Exception occured while BOM call",
						null, e);
			}
			break;

		case BusinessConstants.DATAFEED_DELETE_TEST_DATA:
			TestData testData = new TestData();
			testData.setDateSegmentSysId(datafeedRequest.getDateSegmentSysId());
			testData.setLastUpdatedUser(datafeedRequest.getLastUpdatedUser());
			try {
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(datafeedRequest.getUser());
				contractBuilder.deleteTestData(testData, audit);
			} catch (WPDException e) {
				throw new ServiceException("Exception occured while BOM call",
						null, e);
			}
			break;
		case BusinessConstants.DATAFEED_MANDATE_DEFINITION:
			TreeBenefitDefinition benefitDefinition = new TreeBenefitDefinition();
			benefitDefinition.setStandardBenefitId(datafeedRequest
					.getEntitySystemId());
			benefitDefinition.setBenefitDefinitionType(datafeedRequest
					.getEntityType());
			try {
				datafeedResponse.setSearchResultList(standardBenefitTreeBuilder
						.getStandardDefinitionData(benefitDefinition));
			} catch (WPDException e) {
				throw new ServiceException("Exception occured while BOM call",
						null, e);
			}
			break;
		// Retrieve benefit administration for each standard benefit
		case BusinessConstants.DATAFEED_RETRIEVE_BNFT_ADMINISTRATION:

			ProductTreeBenefitAdministration benefitAdministration = new ProductTreeBenefitAdministration();
			benefitAdministration.setBenefitComponentId(datafeedRequest
					.getBenefitComponentId());
			benefitAdministration.setBenefitDefinitionId(datafeedRequest
					.getBenefitId());
			try {
				datafeedResponse.setSearchResultList(builder
						.getBenefitAdminstrations(benefitAdministration));
			} catch (WPDException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			}

			break;
		// Retrieve admin options for each benefit administration
		case BusinessConstants.DATAFEED_RETRIEVE_HEADER_RULES_FOR_BENEFIT:
			try {
				datafeedResponse.setRulesList(contractBuilder
						.getRuleIdDatafeed(datafeedRequest.getEntityIdList(),
								datafeedRequest.getBenefitComponentId(),
								datafeedRequest.getDateSegmentSysId(),
								datafeedRequest.getAction()));
			} catch (WPDException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			}
			break;
		// Retrieve admin options for each benefit administration
		case BusinessConstants.DATAFEED_RETRIEVE_ADMIN_OPTIONS:
			ProductTreeAdminOptions productTreeAdminOptions = new ProductTreeAdminOptions();
			productTreeAdminOptions.setBenefitAdministrationId(datafeedRequest
					.getBenefitAdministrationId());
			productTreeAdminOptions.setAdminLevelType(datafeedRequest
					.getAdminLevelType());
			productTreeAdminOptions.setEntityId(datafeedRequest
					.getEntitySystemId());
			productTreeAdminOptions.setEntityType(datafeedRequest
					.getEntityType());
			productTreeAdminOptions.setBenefitComponentId(datafeedRequest
					.getBenefitComponentId());
			try {
				datafeedResponse.setSearchResultList(builder
						.getAdminOptions(productTreeAdminOptions));
			} catch (WPDException ex) {
				throw new ServiceException("Adapter exception", null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_ADMINOPTIONS:
			try {
				datafeedResponse
						.setSpsValueMap(contractBuilder
								.getAdminOptions(datafeedRequest
										.getDateSegmentSysId()));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;

		case BusinessConstants.DATAFEED_RETRIEVE_ACCUM_QSTN:
			try {
				ContractBenefitAdministrationLocateCriteria locateCriteria = new ContractBenefitAdministrationLocateCriteria();
				locateCriteria.setEntityId(datafeedRequest
						.getDateSegmentSysId());
				locateCriteria.setBenefitComponentId(datafeedRequest
						.getBenefitComponentId());
				locateCriteria.setBenefitAdminSysId(datafeedRequest
						.getBenefitAdministrationId());
				locateCriteria.setReferenceId(datafeedRequest.getReferenceId());
				datafeedResponse.setSearchResultList(contractBuilder
						.getContractAccumulatorIndicator(locateCriteria));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;
		case BusinessConstants.DATAFEED_RETRIEVE_PROV_SPEC_CODE:
			try {
				ProviderSpecialityCodeBO providerSpecialityCodeBO = new ProviderSpecialityCodeBO();
				providerSpecialityCodeBO.setDateSegmentSysId(datafeedRequest
						.getDateSegmentSysId());
				datafeedResponse.setSearchResultList(contractBuilder
						.getProviderSpecialityCodes(providerSpecialityCodeBO));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;
		case BusinessConstants.DATAFEED_RETRIEVE_TIER_INFO:
			try {
				datafeedResponse.setSearchResultList(contractBuilder
						.getContractTierInformation(datafeedRequest
								.getDateSegmentSysId(), datafeedRequest
								.getTierCriteriaVal()));
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;
		}// end of switch-case
		return datafeedResponse;
	}

	public WPDResponse execute(RetrieveExistingContractIdRequest request)
			throws ServiceException {

		ExistingContractLocateCriteria locateCriteria = new ExistingContractLocateCriteria();
		RetrieveExistingContractIdResponse response = new RetrieveExistingContractIdResponse();
		LocateResults locateResults = null;

		LocateResults locateResultsSecond = null;
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

		// ContractAdapterManager adapterManager = new ContractAdapterManager();
		List contractIdExistingList = null;
		locateCriteria.setProductId(request.getProductId());
		locateCriteria.setContractId(request.getContractId());
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractLocateCriteria criteria = null;
		List contractList = null;
		try {
			locateResults = builder.locateExistingContract(locateCriteria);
			LockManager lockMngr = new LockManager();
			// check for locked contracts
			if (null != locateResults
					&& locateResults.getLocateResults().size() != 0) {
				contractList = new ArrayList(locateResults.getLocateResults()
						.size());
				for (int i = 0; i < locateResults.getLocateResults().size(); i++) {

					Contract contract = (Contract) locateResults
							.getLocateResults().get(i);
					boolean isLockedBysameUser = lockMngr.isLocked(contract,
							request.getUser());

					criteria = new ContractLocateCriteria();
					criteria.setContractId(contract.getContractId());

					locateResultsSecond = bom.locate(criteria, request
							.getUser());

					if (null != locateResultsSecond
							&& locateResultsSecond.getLocateResults().size() > 0) {

						ContractLocateResult locResult = (ContractLocateResult) locateResultsSecond
								.getLocateResults().get(0);
						if (null != locResult) {
							boolean bool = locResult.getState()
									.isLockedByUser();
							if (bool == false) {
								contractList.add(contract);
							}
						}
					}
				}
			}

			// contractIdExistingList =
			// adapterManager.getExistingContracts(locateCriteria);
		} catch (SevereException ex) {
			throw new ServiceException("Adapter exception", null, ex);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveExistingContractIdRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		response.setContractIdExistingList(contractList);
		return response;

	}

	public WPDResponse execute(RetrieveBenefitLinesRequest request)
			throws ServiceException {
		RetrieveBenefitLinesResponse retrieveBenefitLinesResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());

			int dateSegmentId = request.getDateSegmentId();
			int standardBenefitId = request.getStandardBenefitId();
			int benefitComponentId = request.getBenefitComponentId();

			retrieveBenefitLinesResponse = (RetrieveBenefitLinesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_LINES_RESPONSE);

			ContractBusinessObjectBuilder bom = new ContractBusinessObjectBuilder();
			LocateResults locateResults = bom.locate(dateSegmentId,
					standardBenefitId, benefitComponentId, request.getUser());
			if (null != locateResults.getLocateResults()) {
				retrieveBenefitLinesResponse.setBenefitLinesList(locateResults
						.getLocateResults());
			}
			List messageList;
			if (locateResults.getLocateResults().size() == 0) {
				messageList = new ArrayList(2);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_BEN_DFN_NOT_FOUND));
				addMessagesToResponse(retrieveBenefitLinesResponse, messageList);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveBenefitLinesResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveBenefitLinesResponse;
	}

	public WPDResponse execute(SaveContractHeadingsRequest request)
			throws ServiceException {
		SaveContractHeadingsResponse saveContractHeadingsResponse = null;
		try {

			List messageList = new ArrayList(3);
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			Contract srcContract = new Contract();
			srcContract.setContractSysId(request.getSourceContractSysId());
			srcContract.setContractDateSegmentSysId(request
					.getSourceDateSegmentId());
			srcContract.setProductId(request.getProductId());
			srcContract.setSelectedHeadingsList(request
					.getSelectedBenefitLineList());
			srcContract.setContractId(request.getSourceContractId());
			srcContract.setVersion(request.getSourceVersion());

			Contract targetContract = new Contract();
			targetContract.setContractSysId(request.getTargetContractSysId());
			targetContract.setContractDateSegmentSysId(request
					.getTargetDateSegmentId());
			targetContract.setContractId(request.getTargetContractId());

			BusinessObjectManager bom = getBusinessObjectManager();
			HashMap hashMap = new HashMap();

			targetContract = (Contract) bom.retrieve(targetContract, request
					.getUser());
			targetContract.setContractDateSegmentSysId(request
					.getTargetDateSegmentId());
			targetContract.setContractId(request.getTargetContractId());
			srcContract.setEffectiveDateForCopy(request.getEffectiveDate());
			bom.copy(srcContract, targetContract, request.getUser(), hashMap);

			saveContractHeadingsResponse = (SaveContractHeadingsResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_HEADINGS_RESPONSE);

			messageList.add(new InformationalMessage(
					BusinessConstants.MSG_CONTRACT_COPY_HEADINGS));
			addMessagesToResponse(saveContractHeadingsResponse, messageList);

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			saveContractHeadingsResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return saveContractHeadingsResponse;
	}

	private int ifLatestProductExistForCopy(int contractSysId, int productId,
			int dateSegmentId) throws SevereException {
		ProductBO product = new ProductBO();
		ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
		product = cob.retrieveLatestProductVersionForCopy(contractSysId,
				productId, dateSegmentId);
		if (product != null) {
			return product.getProductKey();
		}
		return 0;
	}

	private boolean ifLatestNoteExistForCopy(ContractVO contractVO, User user)
			throws WPDException {
		BusinessObjectManager bom = getBOM();
		ContractNotesLocateCriteria notesLocateCriteria = new ContractNotesLocateCriteria();
		notesLocateCriteria.setDateSegmentId(contractVO.getDateSegmentSysId());
		notesLocateCriteria.setEntityType(BusinessConstants.ATTACH_CONTRACT);
		notesLocateCriteria
				.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
		LocateResults locateResults = bom.locate(notesLocateCriteria, user);
		if (0 < locateResults.getTotalResultsCount()) {
			ContractBusinessObjectBuilder cob = new ContractBusinessObjectBuilder();
			AttachedNotesBO attachedNotesBO = (AttachedNotesBO) locateResults
					.getLocateResults().get(0);
			NoteBO notesBO = cob.checkNote(attachedNotesBO.getNoteId(),
					attachedNotesBO.getVersion());

			if (notesBO != null) {
				List notesList = cob.getDomainedNote(attachedNotesBO);
				if (notesList == null || notesList.size() == 0) {
					NoteBO noteBO = new NoteBO();
					noteBO.setNoteId(attachedNotesBO.getNoteId());
					noteBO.setVersion(attachedNotesBO.getVersion());
					List latestVersion = cob.getLatestVersionNote(noteBO);
					if (latestVersion != null && latestVersion.size() != 0) {
						attachedNotesBO.setVersion(((NoteBO) latestVersion
								.get(0)).getVersion());
						notesList = cob.getDomainedNote(attachedNotesBO);
						if (notesList == null || notesList.size() == 0) {
							return true;
						} else {
							attachedNotesBO = (AttachedNotesBO) notesList
									.get(0);
							if (BusinessConstants.ATTACH_PRODUCT
									.equals(attachedNotesBO.getEntityType())
									&& ((attachedNotesBO.getEntityId() == contractVO
											.getProductSysId()) || (attachedNotesBO
											.getEntityId() == contractVO
											.getOldProductSysId())))
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	public RefDataDomainValidationRequest validateReferenceData(
			Contract contract, SaveContractBasicInfoRequest request)
			throws WPDException {
		RefDataDomainValidationRequest refDataDomainValidationRequest = new RefDataDomainValidationRequest();
		List pricingCodeList = null;
		List networkCdList = null;
		List coverageCdList = null;
		List groupSizeList = null;
		List productCodelist = null;
		List stndPlnCdList = null;
		List coprtCdList = null;
		List fundArngmntList = null;
		List headQuarterList = null;
		int dateSegmtCount = 0;
		BusinessObjectManager bom = getBusinessObjectManager();

		List dateSegmentList = request.getContract()
				.getCheckInDateSegmentList();
		if (null != dateSegmentList)
			dateSegmtCount = dateSegmentList.size();
		if (dateSegmtCount >= 0) {
			groupSizeList = new ArrayList(dateSegmtCount);
			productCodelist = new ArrayList(dateSegmtCount);
			stndPlnCdList = new ArrayList(dateSegmtCount);
			coprtCdList = new ArrayList(dateSegmtCount);
			fundArngmntList = new ArrayList(dateSegmtCount);
			headQuarterList = new ArrayList(dateSegmtCount);

			for (int i = 0; i < dateSegmtCount; i++) {
				DateSegment dateSegment = (DateSegment) dateSegmentList.get(i);
				int dateSegmentId = dateSegment.getDateSegmentSysId();
				Map paramsDomain = new HashMap();
				paramsDomain.put(BusinessConstants.ACTION,
						BusinessConstants.RETREIVE_SPECIFIC);
				paramsDomain.put(BusinessConstants.DATESEGMENT_ID, new Integer(
						dateSegmentId));
				contract = (Contract) bom.retrieve(contract, request.getUser(),
						paramsDomain);
				List specificDateDateSegmentList = contract
						.getDateSegmentList();
				for (int p = 0; p < specificDateDateSegmentList.size(); p++) {
					DateSegment dateSegmentBO = (DateSegment) specificDateDateSegmentList
							.get(p);
					groupSizeList.add(dateSegmentBO.getGroupSize());
					productCodelist.add(dateSegmentBO.getProductCode());
					stndPlnCdList.add(dateSegmentBO.getStandardPlanCode());
					coprtCdList.add(dateSegmentBO.getCorporatePlanCode());
					fundArngmntList.add(dateSegmentBO.getFundingArrangement());
					headQuarterList.add(dateSegmentBO.getHeadQuartersState());
				}
				Map paramsPricing = new HashMap();
				paramsPricing.put(BusinessConstants.ACTION,
						"retrieveContractPricingInfo");
				paramsPricing.put(BusinessConstants.DATESEGMENT_ID,
						new Integer(dateSegmentId));
				Contract contractPricing = getContract(request);
				contractPricing = (Contract) bom.retrieve(contractPricing,
						request.getUser(), paramsPricing);
				List pricingDateDateSegmentList = contractPricing
						.getDateSegmentList();
				if (null != pricingDateDateSegmentList) {
					pricingCodeList = new ArrayList(pricingDateDateSegmentList
							.size());
					networkCdList = new ArrayList(pricingDateDateSegmentList
							.size());
					coverageCdList = new ArrayList(pricingDateDateSegmentList
							.size());
					for (int k = 0; k < pricingDateDateSegmentList.size(); k++) {
						DateSegment dateSegmentBO = (DateSegment) pricingDateDateSegmentList
								.get(k);
						List pricingList = dateSegmentBO.getPricingInfoList();
						for (int j = 0; j < pricingList.size(); j++) {
							ContractPricingInfo contractPricingInfo = (ContractPricingInfo) pricingList
									.get(j);
							pricingCodeList.add(contractPricingInfo
									.getPricing());

							networkCdList.add(contractPricingInfo.getNetwork());

							coverageCdList.add(contractPricingInfo
									.getCoverage());

						}

					}
				}

			}

		}

		HashMap paramValues = new HashMap();
		paramValues.put("Group Size", groupSizeList);
		paramValues.put("Product Codes", productCodelist);
		paramValues.put("Standard Plan Code", stndPlnCdList);
		paramValues.put("Corporate Plan Codes", coprtCdList);
		paramValues.put("Funding Arrangement", fundArngmntList);
		paramValues.put("Pricing", pricingCodeList);
		paramValues.put("Network", networkCdList);
		paramValues.put("Coverage", coverageCdList);
		paramValues.put("State Code", headQuarterList);
		// parent catalog list
		List parentCatalogList = new ArrayList();
		parentCatalogList.add("Group Size");
		parentCatalogList.add("Product Codes");
		parentCatalogList.add("Standard Plan Code");
		parentCatalogList.add("Corporate Plan Codes");
		parentCatalogList.add("Funding Arrangement");
		parentCatalogList.add("Pricing");
		parentCatalogList.add("Network");
		parentCatalogList.add("Coverage");
		parentCatalogList.add("State Code");

		SubCatalogVO subCatalogVO = new SubCatalogVO();
		subCatalogVO.setEntityId(contract.getParentSysId());
		subCatalogVO.setEntityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
		refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);
		refDataDomainValidationRequest.setParentCatalogList(parentCatalogList);
		refDataDomainValidationRequest.setSelectedItemMap(paramValues);
		return refDataDomainValidationRequest;
	}

	/**
	 * Retrieves the benefit mandates for display in the view page
	 * 
	 * @param retrieveBenefitMandatesRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			RetrieveBenefitMandatesRequest retrieveBenefitMandatesRequest)
			throws ServiceException {
		RetrieveBenefitMandatesResponse retrieveContractBenefitMandatesResponse = null;
		try {
			Logger
					.logInfo("Entering execute method for retrieving benefit mandates");
			// Get the BusinessObjectManager
			BusinessObjectManager bom = getBusinessObjectManager();

			List benefitMandateBOImplList = new ArrayList();
			BenefitMandateBO benefitMandateBO = new BenefitMandateBO();

			// Create the locateCriteria object

			ContractMandatesLocateCriteria locateCriteria = new ContractMandatesLocateCriteria();

			// Set the locate criteria from the request to the crteriaObject
			locateCriteria.setBenefitId(retrieveBenefitMandatesRequest
					.getMandatesVO().getBenefitId());

			// Call the locateCriteria method of the BOM to get the list
			// of mandates

			LocateResults locateResults = bom.locate(locateCriteria,
					retrieveBenefitMandatesRequest.getUser());

			// Create an instance of the Response object
			retrieveContractBenefitMandatesResponse = (RetrieveBenefitMandatesResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.CONTRACT_MANDATE_INFO_RESPONSE);
			benefitMandateBOImplList.add(0, benefitMandateBO);
			benefitMandateBOImplList = locateResults.getLocateResults();
			if (null != benefitMandateBOImplList) {
				benefitMandateBO = (BenefitMandateBO) benefitMandateBOImplList
						.get(0);
			}

			// Set the above retrieved list in the response object
			retrieveContractBenefitMandatesResponse
					.setBenefitMandateBO(benefitMandateBO);
			Logger
					.logInfo("Returning execute method for retrieving benefit mandates");

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveContractBenefitMandatesResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveBenefitMandatesRequest);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveBenefitMandatesRequest);
			String logMessage = "Failed while processing RetrieveBenefitMandatesResponse";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return retrieveContractBenefitMandatesResponse;
	}

	/**
	 * This method stores the details of the copied contracts for report use
	 * 
	 * @param request
	 * @param contract
	 * @param oldEffectiveDate
	 * @param retrieveDateSegnment
	 * @param contractDetails
	 */
	private void getContractCopyDetails(SaveContractBasicInfoRequest request,
			Contract contract, Date oldEffectiveDate,
			DateSegment retrieveDateSegnment, ContractHistory contractDetails) {
		contractDetails.setSourceContractCode(request.getContractKeyObject()
				.getContractId());
		contractDetails.setSourceDateSegmentId(request.getContractKeyObject()
				.getDateSegmentId());

		contractDetails.setSourceDateSegmentEffectiveDate(oldEffectiveDate);
		contractDetails.setTargetContractCode(contract.getContractId());
		contractDetails.setTargetDateSegmentId(retrieveDateSegnment
				.getDateSegmentSysId());
		contractDetails.setTargetDateSegmentEffectiveDate(retrieveDateSegnment
				.getEffectiveDate());
	}

	public WPDResponse execute(
			RetrieveCodedNonCodedBenefitHeadingsRequest request)
			throws ServiceException {
		RetrieveCodedNonCodedBenefitHeadingsResponse retrieveCodedNonCodedBenefitHeadingsResponse = null;
		try {
			Logger.logInfo("Entering execute method, request = "
					+ request.getClass().getName());
			int dateSegmentId = 0;
			dateSegmentId = request.getDateSegmentId();
			int productId = request.getProductId();

			retrieveCodedNonCodedBenefitHeadingsResponse = (RetrieveCodedNonCodedBenefitHeadingsResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.RETRIEVE_CODED_NONCODED_BENEFIT_HEADINGS_RESPONSE);

			ContractBusinessObjectBuilder bom = new ContractBusinessObjectBuilder();
			LocateResults locateResults = bom.locateCodedNonCodedHeadings(
					dateSegmentId, productId, request.getUser());
			if (null != locateResults.getLocateResults()) {
				retrieveCodedNonCodedBenefitHeadingsResponse
						.setCodedNonCodedBenefitHeadingsList(locateResults
								.getLocateResults());
			}
			List messageList = new ArrayList();
			if (null == locateResults.getLocateResults()
					|| locateResults.getLocateResults().size() == 0) {
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_BEN_DFN_NOT_FOUND));
				addMessagesToResponse(
						retrieveCodedNonCodedBenefitHeadingsResponse,
						messageList);
			}
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			retrieveCodedNonCodedBenefitHeadingsResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
		return retrieveCodedNonCodedBenefitHeadingsResponse;
	}

	public WPDResponse execute(
			SaveContractAdaptedInfoRequest saveContractAdaptedInfoRequest)
			throws ServiceException {
		SaveContractAdaptedInfoResponse saveContractAdaptedInfoResponse = null;
		try {

			BusinessObjectManager bom = getBusinessObjectManager();
			saveContractAdaptedInfoResponse = (SaveContractAdaptedInfoResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_CONTRACT_ADAPTEDINFO_RESPONSE);

			// gets contract object
			Contract contract = getContract(saveContractAdaptedInfoRequest);
			User user = saveContractAdaptedInfoRequest.getUser();
			// DateSegment dateSegment =
			// getDateSegment(saveContractAdaptedInfoRequest);
			Map map = new HashMap();
			DateSegment ds = null;
			map.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			map.put(BusinessConstants.DATESEGMENT_ID, new Integer(
					saveContractAdaptedInfoRequest.getContractKeyObject()
							.getDateSegmentId()));

			contract = (Contract) bom.retrieve(contract,
					saveContractAdaptedInfoRequest.getUser(), map);
			List dateSegList = contract.getDateSegmentList();
			if (null != dateSegList && !dateSegList.isEmpty()) {
				ds = (DateSegment) dateSegList.get(0);
				ds.setContractId(saveContractAdaptedInfoRequest
						.getContractKeyObject().getContractId());
				ds.setContractSysId(saveContractAdaptedInfoRequest
						.getContractKeyObject().getContractSysId());

				if (null != saveContractAdaptedInfoRequest.getAdaptedInfoBO()) {

					AdaptedInfoBO adaptedInfoBO = saveContractAdaptedInfoRequest
							.getAdaptedInfoBO();
					if (null != adaptedInfoBO.getRegulatoryAgency()
							&& !("".equals(adaptedInfoBO.getRegulatoryAgency()))) {
						ds.setRegulatoryAgency(adaptedInfoBO
								.getRegulatoryAgency());
					}

					if (null != adaptedInfoBO.getComplianceStatus()
							&& !("".equals(adaptedInfoBO.getComplianceStatus()))) {
						ds.setComplianceStatus(adaptedInfoBO
								.getComplianceStatus());
					}

					if (null != adaptedInfoBO.getProdProjNameCode()
							&& !("".equals(adaptedInfoBO.getProdProjNameCode()))) {
						ds.setProjectNameCode(adaptedInfoBO
								.getProdProjNameCode());
					}
					if (null != adaptedInfoBO.getContractTermDate()) {
						ds.setContractTermDate(adaptedInfoBO
								.getContractTermDate());
					}
					if (null != adaptedInfoBO.getMultiplanIndicator()
							&& !("".equals(adaptedInfoBO
									.getMultiplanIndicator()))) {
						ds.setMultiPlanIndicator(adaptedInfoBO
								.getMultiplanIndicator());
					}
					if (null != adaptedInfoBO.getPerformanceGuarantee()
							&& !("".equals(adaptedInfoBO
									.getPerformanceGuarantee()))) {
						ds.setPerformanceGuarantee(adaptedInfoBO
								.getPerformanceGuarantee());
					}
					if (null != adaptedInfoBO.getSalesMarketDate()) {
						ds.setSalesMarketDate(adaptedInfoBO
								.getSalesMarketDate());
					}

					ds.setDateSegmentSysId(adaptedInfoBO.getDateSegmentSysId());
					ds.setLastUpdatedUser(user.getUserId());
				}
			}

			// AdaptedInfoBO adaptedInfoBO =
			// saveContractAdaptedInfoRequest.getAdaptedInfoBO();

			// performs the update
			bom.persist(ds, user, false);

			saveContractAdaptedInfoResponse
					.addMessage(new InformationalMessage(
							BusinessConstants.MSG_CONTRACT_ADAPTEDINFO_SAVE_SUCCESS));

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage(
					BusinessConstants.MSG_SECURITY_INVALID);
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			saveContractAdaptedInfoResponse.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractAdaptedInfoResponse);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);
		} catch (WPDException ex) {
			List logParameters = new ArrayList();
			logParameters.add(saveContractAdaptedInfoResponse);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (Exception ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(saveContractAdaptedInfoRequest);
			String logMessage = "Failed while processing SaveContractAdaptedInfoRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return saveContractAdaptedInfoResponse;
	}

	public WPDResponse execute(RetrieveSystemPoolIdRequest request)
			throws ServiceException {

		List domainList = request.getBusinessDomains();
		RetrieveSystemPoolIdResponse response = new RetrieveSystemPoolIdResponse();
		List systemPoolIdList = null;
		int count = 0;
		try {
			ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
			ContractIDSystemPool systemPool = null;
			systemPool = contractIDRepositoryAdmin.getContractSystemPool();
			ContractIDPoolRecord startContractId = new ContractIDPoolRecord();
			ContractIDPoolRecord endContractId = new ContractIDPoolRecord();
			boolean continuous = request.isContinuous();
			if (request.getAction() == 1) {
				startContractId.setContractId(request.getFromContractId());
				endContractId.setContractId(request.getEndContractId());
				systemPoolIdList = systemPool.locateContractIds(
						startContractId, endContractId);
			} else if (request.getAction() == 2) {
				/*
				 * startContractId.setContractId(request.getStartContractId());
				 * count = request.getNoOfIdsToGenerate_Start();
				 * systemPoolIdList = systemPool.locateContractIds(
				 * startContractId, count, true);
				 */
				startContractId.setContractId(request.getStartContractId());
				count = request.getNoOfIdsToGenerate_Start();
				if (continuous) {
					systemPoolIdList = systemPool.locateContractIds(
							startContractId, count, true);
				} else {
					systemPoolIdList = systemPool.locateContractIds(
							startContractId, count, false);
				}

			} else if (request.getAction() == 3) {
				startContractId.setContractId(request.getNextAvailableId());
				count = request.getNoOfIdsToGenerate_Next();
				if (continuous) {
					systemPoolIdList = systemPool.locateContractIds(
							startContractId, count, true);
				} else {
					systemPoolIdList = systemPool.locateContractIds(
							startContractId, count, false);
				}
			}
		} catch (ContractIDPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ContractIDReservePoolRecord id = new ContractIDReservePoolRecord();

		if (null != systemPoolIdList && systemPoolIdList.size() != 0) {

			int size = systemPoolIdList.size();
			boolean allAvailable = true;
			ContractIDReservePoolRecord reservePoolRecords = null;
			// iterate the list to get the ContractIDReservePoolRecord
			for (int i = 0; i < size; i++) {
				reservePoolRecords = (ContractIDReservePoolRecord) systemPoolIdList
						.get(i);
				if (null != reservePoolRecords) {
					if (reservePoolRecords.getSystemPoolStatus().trim().equals(
							"U")
							|| reservePoolRecords.getSystemPoolStatus().trim()
									.equals("R")) {
						// if(reservePoolRecords.getAvailable().trim().equals("N")
						// ){
						allAvailable = false;
						break;
					}

				}

			}
			response.setSuccess(allAvailable);
			// if(!allAvailable)
			response.setContractIdList(systemPoolIdList);

		}

		return response;
	}

	/**
	 * Service method to release the contract ids.
	 * 
	 * @param releaseReservedContractRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws ContractIDPoolException
	 */
	public WPDResponse execute(
			ReleaseReservedContractRequest releaseReservedContractRequest)
			throws ServiceException, ContractIDPoolException {
		ReleaseReservedContractResponse releaseReservedContractResponse = new ReleaseReservedContractResponse();
		boolean success = false;
		List systemPoolIdList;
		try {
			ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
			ContractIDSystemPool systemPool = null;
			systemPool = contractIDRepositoryAdmin.getContractSystemPool();
			ContractIDPoolRecord startContractId = new ContractIDPoolRecord();
			ContractIDPoolRecord endContractId = new ContractIDPoolRecord();
			if (releaseReservedContractRequest.getAction() == 1) {
				startContractId.setContractId(releaseReservedContractRequest
						.getStartContractId());
				endContractId.setContractId(releaseReservedContractRequest
						.getEndContractId());
				systemPoolIdList = systemPool.locateContractIds(
						startContractId, endContractId);
				if (null != systemPoolIdList && systemPoolIdList.size() > 0) {

					int size1 = systemPoolIdList.size();
					for (int i = 0; i < size1; i++) {
						ContractIDReservePoolRecord record = (ContractIDReservePoolRecord) systemPoolIdList
								.get(i);
						if (record.getSystemPoolStatus().trim().equals("R")) {
							if ((releaseReservedContractRequest.getUser()
									.isAuthorized(
											WebConstants.RESERVER_CONTRACT,
											WebConstants.ADMIN_RELEASE))) {
								record.setPrivilege("Yes");
							} else if (releaseReservedContractRequest.getUser()
									.getUserId().toUpperCase().trim().equals(
											record.getCreatedUser()
													.toUpperCase().trim())) {
								record.setPrivilege("Yes");
							} else {
								record.setPrivilege("No");
							}
						}
					}
					releaseReservedContractResponse
							.setContractIdList(systemPoolIdList);

				}

			} else if (releaseReservedContractRequest.getAction() == 2) {
				Audit audit = getAudit(releaseReservedContractRequest.getUser());
				List idList = releaseReservedContractRequest
						.getContractIdList();
				List listToRelease = null;
				if (null != idList && idList.size() > 0) {
					listToRelease = new ArrayList();
					int size = releaseReservedContractRequest
							.getContractIdList().size();
					for (int i = 0; i < size; i++) {
						ContractIDReservePoolRecord contractIDReservePoolRecord = (ContractIDReservePoolRecord) idList
								.get(i);
						contractIDReservePoolRecord
								.setCreatedUser(releaseReservedContractRequest
										.getUser().getUserId());
						contractIDReservePoolRecord
								.setLastUpdatedUser(releaseReservedContractRequest
										.getUser().getUserId());
						contractIDReservePoolRecord.setCreatedTimeStamp(audit
								.getTime());
						contractIDReservePoolRecord
								.setLastUpdatedTimeStamp(audit.getTime());
						if (contractIDReservePoolRecord.getPrivilege().equals(
								"Yes")) {
							listToRelease.add(contractIDReservePoolRecord);
						}
					}
				}

				if (null != listToRelease && !listToRelease.isEmpty()) {
					ContractIDReservePool contractIDReservePool = null;
					contractIDReservePool = contractIDRepositoryAdmin
							.getContractReservePool();
					success = contractIDReservePool
							.releaseContracts(listToRelease);
					if (success) {
						releaseReservedContractResponse.setSuccess(success);
					}
				} else {
					releaseReservedContractResponse.setSuccess(false);
					List messageList = new ArrayList();
					ErrorMessage message = new ErrorMessage(
							"contract.not.released");
					messageList.add(message);
					releaseReservedContractResponse.setMessages(messageList);
				}

			} else if (releaseReservedContractRequest.getAction() == 3) {
				startContractId = (ContractIDReservePoolRecord) releaseReservedContractRequest
						.getContractIdList().get(0);

				systemPoolIdList = systemPool.locateContractIds(
						startContractId, 1, true);
				if (null != systemPoolIdList && systemPoolIdList.size() > 0) {

					int size1 = systemPoolIdList.size();
					for (int i = 0; i < size1; i++) {
						ContractIDReservePoolRecord record = (ContractIDReservePoolRecord) systemPoolIdList
								.get(i);
						if ((releaseReservedContractRequest.getUser()
								.isAuthorized(WebConstants.RESERVER_CONTRACT,
										WebConstants.ADMIN_RELEASE))) {
							record.setPrivilege("Yes");
						} else if (releaseReservedContractRequest.getUser()
								.getUserId().toUpperCase().trim().equals(
										record.getCreatedUser().toUpperCase()
												.trim())) {
							record.setPrivilege("Yes");
						} else {
							record.setPrivilege("No");
						}
					}
					releaseReservedContractResponse
							.setContractIdList(systemPoolIdList);

				}
				if (null != systemPoolIdList && !systemPoolIdList.isEmpty()) {
					ContractIDReservePool contractIDReservePool = null;
					contractIDReservePool = contractIDRepositoryAdmin
							.getContractReservePool();
					success = contractIDReservePool
							.releaseContracts(systemPoolIdList);
					if (success) {
						releaseReservedContractResponse.setSuccess(success);
					}
				} else {
					releaseReservedContractResponse.setSuccess(false);
					List messageList = new ArrayList();
					ErrorMessage message = new ErrorMessage(
							"contract.not.released");
					messageList.add(message);
					releaseReservedContractResponse.setMessages(messageList);
				}
			}

		} catch (ContractIDPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return releaseReservedContractResponse;
	}

	/**
	 * Service method to retrieve all the expired contract Ids to be released
	 * 
	 * @param retrieveExpiredContractIdRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws ContractIDPoolException
	 */
	public WPDResponse execute(
			RetrieveExpiredContractIdRequest retrieveExpiredContractIdRequest)
			throws ServiceException, ContractIDPoolException {
		ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
		ContractIDReservePool contractIDReservePool = null;
		contractIDReservePool = contractIDRepositoryAdmin
				.getContractReservePool();
		String searchString = retrieveExpiredContractIdRequest
				.getSearchString();
		List expiredIds = contractIDReservePool
				.locateExpriredContractIds(retrieveExpiredContractIdRequest
						.getSearchString());
		int size = expiredIds.size();
		RetrieveExpiredContractIdsResponse retrieveExpiredContractIdsResponse = new RetrieveExpiredContractIdsResponse();
		if (size > 500) {
			retrieveExpiredContractIdsResponse
					.addMessage(new InformationalMessage(
							BusinessConstants.EXP_EXCEED_LIMIT));
			expiredIds.remove(500);
		}
		int size1 = expiredIds.size();
		for (int i = 0; i < size1; i++) {
			ContractIDReservePoolRecord record = (ContractIDReservePoolRecord) expiredIds
					.get(i);
			if ((retrieveExpiredContractIdRequest.getUser().isAuthorized(
					WebConstants.RESERVER_CONTRACT, WebConstants.ADMIN_RELEASE))) {
				record.setPrivilege("Yes");
			} else if (retrieveExpiredContractIdRequest.getUser().getUserId()
					.trim().toUpperCase().equals(
							record.getCreatedUser().trim().toUpperCase())) {
				record.setPrivilege("Yes");
			} else {
				record.setPrivilege("No");
			}
		}
		if (null != searchString && !"".equals(searchString)) {
			if (null == expiredIds || expiredIds.size() == 0) {
				retrieveExpiredContractIdsResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
			}
		}
		retrieveExpiredContractIdsResponse.setExpiredContractIdList(expiredIds);
		return retrieveExpiredContractIdsResponse;
	}

	/**
	 * 
	 * @param contractTierDeleteRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ContractTierDeleteRequest contractTierDeleteRequest)
			throws ServiceException {
		Logger.logInfo("Entering execute method, request = "
				+ contractTierDeleteRequest.getClass().getName());
		ContractTierDeleteResponse contractTierDeleteResponse = (ContractTierDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_CONTRACT_TIER_RESPONSE);
		List messageList = null;
		BusinessObjectManager bom = getBusinessObjectManager();
		DateSegment dateSegment = getDateSegment(contractTierDeleteRequest);
		BenefitTier productBenefitTier = new BenefitTier(
				contractTierDeleteRequest.getContractTierSysId());

		try {

			// Trigger AM Validation
			updateAMVForTierContract(contractTierDeleteRequest);

			bom.delete(productBenefitTier, dateSegment,
					contractTierDeleteRequest.getUser());

			messageList = new ArrayList(1);
			messageList.add(new InformationalMessage(
					BusinessConstants.BENEFIT_TIER_DELETE));

		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(contractTierDeleteRequest);
			String logMessage = "Failed while processing ContractTierDeleteRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		addMessagesToResponse(contractTierDeleteResponse, messageList);
		Logger.logInfo("Returning  from execute method for request="
				+ contractTierDeleteRequest.getClass().getName());
		return contractTierDeleteResponse;
	}

	private void updateContractStatus(String contractId,
			WPDRequest deleteContractRequest) throws ServiceException {
		ContractIDRepositoryAdmin contractIDRepositoryAdmin = new ContractIDRepositoryAdmin();
		ContractIDSystemPool contractIDSystemPool = null;

		ContractIDReservePool contractIDReservePool = null;
		try {
			contractIDSystemPool = contractIDRepositoryAdmin
					.getContractSystemPool();
			contractIDReservePool = contractIDRepositoryAdmin
					.getContractReservePool();
		} catch (ContractIDPoolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			throw new ServiceException("Adapter exception", null, e2);
		}

		ContractIDPoolRecord contractIDPoolRecord = new ContractIDPoolRecord();
		contractIDPoolRecord.setContractId(contractId);
		try {
			List poolRecord = contractIDSystemPool.locateContractIds(
					contractIDPoolRecord, 1, true);
			if (null != poolRecord && poolRecord.size() > 0) {
				contractIDPoolRecord = (ContractIDPoolRecord) poolRecord.get(0);
				if (null != contractIDPoolRecord
						&& contractIDPoolRecord.getSystemPoolStatus().trim()
								.equals("U")) {
					ContractIDPoolRecord contractIDPoolRecordnew = new ContractIDPoolRecord();
					contractIDPoolRecordnew.setSystemPoolStatus("N");
					contractIDPoolRecordnew
							.setCreatedUser(deleteContractRequest.getUser()
									.getUserId());
					contractIDPoolRecordnew.setContractId(contractIDPoolRecord
							.getContractId());
					contractIDReservePool.markAsUnused(contractIDPoolRecordnew);

				} else if (null != contractIDPoolRecord
						&& contractIDPoolRecord.getSystemPoolStatus().trim()
								.equals("R")) {
					if (contractIDPoolRecord.getReservePoolStatus().equals("U")) {
						ContractIDReservePoolRecord contractIDReservePoolRecord = new ContractIDReservePoolRecord();
						contractIDReservePoolRecord
								.setContractId(contractIDPoolRecord
										.getContractId());
						contractIDReservePoolRecord.setReservePoolStatus("N");
						contractIDReservePoolRecord.setSystemDomain("");
						contractIDReservePoolRecord
								.setExpiryDate(contractIDPoolRecord
										.getExpiryDate());
						contractIDReservePoolRecord
								.setLastUpdatedUser(deleteContractRequest
										.getUser().getUserId());
						contractIDReservePool
								.updateReserveContract(contractIDReservePoolRecord);
						// update reserve pool status as N, also update system
						// domain to null

					}
				}
			}
		} catch (ContractIDPoolException c) {
			c.printStackTrace();
			throw new ServiceException("Adapter exception", null, c);
		}

	}

	/**
	 * Mehtod used to insert/update/delete the attached notes in an Admin Option
	 * attached to a benefit in a contract
	 * 
	 * @param contractNotesToQuestionAttachmentRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ContractNotesToQuestionAttachmentRequest contractNotesToQuestionAttachmentRequest)
			throws ServiceException {

		ContractNotesToQuestionAttachmentResponse contractNotesToQuestionAttachmentResponse = (ContractNotesToQuestionAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CNTRCT_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE);

		NotesToQuestionAttachmentBO bo = setValuesToNotesToQuestionAttachmentBO(contractNotesToQuestionAttachmentRequest);
		BusinessObjectManager bom = getBOM();

		DateSegment dateSegment = getDateSegment(contractNotesToQuestionAttachmentRequest);

		List messageList = new ArrayList();

		try {
			if (contractNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isInsertRequest()) {
				bom.persist(bo, dateSegment,
						contractNotesToQuestionAttachmentRequest.getUser(),
						true);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService",
					errorParams, exception);

		}
		try {
			if (contractNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isUpdateRequest()) {
				bom.persist(bo, dateSegment,
						contractNotesToQuestionAttachmentRequest.getUser(),
						false);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method, for updating the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService",
					errorParams, exception);

		}
		try {
			if (contractNotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isDeleteRequest()) {
				bom.delete(bo, dateSegment,
						contractNotesToQuestionAttachmentRequest.getUser());
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in delete method , for delete the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService",
					errorParams, exception);

		}
		addMessagesToResponse(contractNotesToQuestionAttachmentResponse,
				messageList);
		return contractNotesToQuestionAttachmentResponse;
	}

	private NotesToQuestionAttachmentBO setValuesToNotesToQuestionAttachmentBO(
			ContractNotesToQuestionAttachmentRequest request) {

		NotesToQuestionAttachmentBO notesToQuestionAttachmentBO = new NotesToQuestionAttachmentBO();

		notesToQuestionAttachmentBO.setRequestType(request
				.getNotesToQuestionAttachmentVO().getRequestType());
		notesToQuestionAttachmentBO.setBenefitCompId(request
				.getNotesToQuestionAttachmentVO().getBenefitCompId());
		notesToQuestionAttachmentBO.setBnftDefId(Integer.toString(request
				.getNotesToQuestionAttachmentVO().getBnftDefId()));
		notesToQuestionAttachmentBO.setCreatedUser(request.getUser()
				.getUserId());
		notesToQuestionAttachmentBO.setNoteId(request
				.getNotesToQuestionAttachmentVO().getNoteId());
		notesToQuestionAttachmentBO.setNoteOverrideStatus(request
				.getNotesToQuestionAttachmentVO().getNoteOverrideStatus());
		notesToQuestionAttachmentBO.setNoteVersionNumber(request
				.getNotesToQuestionAttachmentVO().getNoteVersionNumber());
		notesToQuestionAttachmentBO.setPrimaryEntityType(request
				.getNotesToQuestionAttachmentVO().getPrimaryEntityType());
		notesToQuestionAttachmentBO.setPrimaryId(request
				.getNotesToQuestionAttachmentVO().getPrimaryId());
		notesToQuestionAttachmentBO.setQuestionId(request
				.getNotesToQuestionAttachmentVO().getQuestionId());
		notesToQuestionAttachmentBO.setSecondaryEntityType(request
				.getNotesToQuestionAttachmentVO().getSecondaryEntityType());
		notesToQuestionAttachmentBO.setSecondaryId(request
				.getNotesToQuestionAttachmentVO().getSecondaryId());

		return notesToQuestionAttachmentBO;
	}

	/**
	 * Mehtod used to insert/update/delete the attached notes in an Admin Option
	 * at contract level
	 * 
	 * @param contractAONotesToQuestionAttachmentRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ContractAONotesToQuestionAttachmentRequest contractAONotesToQuestionAttachmentRequest)
			throws ServiceException {

		ContractAONotesToQuestionAttachmentResponse contractAONotesToQuestionAttachmentResponse = (ContractAONotesToQuestionAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CNTRCT_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE);

		ContractProductAONotesAttachmentBO bo = setValuesToNotesToQuestionAttachmentBO(contractAONotesToQuestionAttachmentRequest);
		BusinessObjectManager bom = getBOM();

		DateSegment dateSegment = getDateSegment(contractAONotesToQuestionAttachmentRequest);
		List messageList = new ArrayList();

		try {
			if (contractAONotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isInsertRequest()) {
				bom.persist(bo, dateSegment,
						contractAONotesToQuestionAttachmentRequest.getUser(),
						true);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		try {
			if (contractAONotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isUpdateRequest()) {
				bom.persist(bo, dateSegment,
						contractAONotesToQuestionAttachmentRequest.getUser(),
						false);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method, for updating the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		try {
			if (contractAONotesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isDeleteRequest()) {
				bom.delete(bo, dateSegment,
						contractAONotesToQuestionAttachmentRequest.getUser());
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in delete method , for delete the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		addMessagesToResponse(contractAONotesToQuestionAttachmentResponse,
				messageList);
		return contractAONotesToQuestionAttachmentResponse;
	}

	/**
	 * 
	 * @param ContractAONotesToQuestionAttachmentRequest
	 * @return ContractProductAONotesAttachmentBO
	 */
	private ContractProductAONotesAttachmentBO setValuesToNotesToQuestionAttachmentBO(
			ContractAONotesToQuestionAttachmentRequest request) {

		ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO = new ContractProductAONotesAttachmentBO();

		contractProductAONotesAttachmentBO.setRequestType(request
				.getNotesToQuestionAttachmentVO().getRequestType());
		contractProductAONotesAttachmentBO.setBenefitCompId(request
				.getNotesToQuestionAttachmentVO().getBenefitCompId());
		contractProductAONotesAttachmentBO.setBnftDefId(null);
		contractProductAONotesAttachmentBO.setCreatedUser(request.getUser()
				.getUserId());
		contractProductAONotesAttachmentBO.setNoteId(request
				.getNotesToQuestionAttachmentVO().getNoteId());
		contractProductAONotesAttachmentBO.setNoteOverrideStatus(request
				.getNotesToQuestionAttachmentVO().getNoteOverrideStatus());
		contractProductAONotesAttachmentBO.setNoteVersionNumber(request
				.getNotesToQuestionAttachmentVO().getNoteVersionNumber());
		contractProductAONotesAttachmentBO.setPrimaryEntityType(request
				.getNotesToQuestionAttachmentVO().getPrimaryEntityType());
		contractProductAONotesAttachmentBO.setPrimaryId(request
				.getNotesToQuestionAttachmentVO().getPrimaryId());
		contractProductAONotesAttachmentBO.setQuestionId(request
				.getNotesToQuestionAttachmentVO().getQuestionId());
		contractProductAONotesAttachmentBO.setSecondaryEntityType(request
				.getNotesToQuestionAttachmentVO().getSecondaryEntityType());
		contractProductAONotesAttachmentBO.setSecondaryId(request
				.getNotesToQuestionAttachmentVO().getSecondaryId());

		return contractProductAONotesAttachmentBO;
	}

	/**
	 * Mehtod used to insert/update/delete the attached notes in an Teired Admin
	 * Option at contract level
	 * 
	 * @param ContractTeiredNotesToQuestionAttachmentRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ContractTeiredNotesToQuestionAttachmentRequest contractTeiredNotesToQuestionAttachmentRequest)
			throws ServiceException {

		ContractTeiredNotesToQuestionAttachmentResponse contractTeiredNotesToQuestionAttachmentResponse = (ContractTeiredNotesToQuestionAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CNTRCT_TEIRED_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE);

		TierNotesAttachmentOverrideBO bo = setValuesToTeiredNotesToQuestionAttachmentBO(contractTeiredNotesToQuestionAttachmentRequest);
		BusinessObjectManager bom = getBOM();

		DateSegment dateSegment = getDateSegment(contractTeiredNotesToQuestionAttachmentRequest);
		List messageList = new ArrayList();

		try {
			if (contractTeiredNotesToQuestionAttachmentRequest
					.isInsertRequest()) {
				bom.persist(bo, dateSegment,
						contractTeiredNotesToQuestionAttachmentRequest
								.getUser(), true);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		try {
			if (contractTeiredNotesToQuestionAttachmentRequest
					.isUpdateRequest()) {
				bom.persist(bo, dateSegment,
						contractTeiredNotesToQuestionAttachmentRequest
								.getUser(), false);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in persist method, for updating the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		try {
			if (contractTeiredNotesToQuestionAttachmentRequest
					.isDeleteRequest()) {
				bom.delete(bo, dateSegment,
						contractTeiredNotesToQuestionAttachmentRequest
								.getUser());
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new ServiceException(
					"Exception occured in delete method , for delete the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessService for ContractAO",
					errorParams, exception);

		}
		addMessagesToResponse(contractTeiredNotesToQuestionAttachmentResponse,
				messageList);
		return contractTeiredNotesToQuestionAttachmentResponse;
	}

	private TierNotesAttachmentOverrideBO setValuesToTeiredNotesToQuestionAttachmentBO(
			ContractTeiredNotesToQuestionAttachmentRequest contractTeiredNotesToQuestionAttachmentRequest) {

		TierNotesAttachmentOverrideBO bo = new TierNotesAttachmentOverrideBO();

		bo.setBenefitComponentId(contractTeiredNotesToQuestionAttachmentRequest
				.getBenefitCompId());
		bo
				.setBnftDefnIdString(""
						+ contractTeiredNotesToQuestionAttachmentRequest
								.getBnftDefId());
		bo
				.setNoteId(contractTeiredNotesToQuestionAttachmentRequest
						.getNoteId());
		bo.setVersion(contractTeiredNotesToQuestionAttachmentRequest
				.getNoteVersionNumber());
		bo.setTierSysId(contractTeiredNotesToQuestionAttachmentRequest
				.getTierSysId());
		bo
				.setSecondaryEntityType(contractTeiredNotesToQuestionAttachmentRequest
						.getSecondaryEntityType());
		bo.setSecondaryEntityId(contractTeiredNotesToQuestionAttachmentRequest
				.getSecondaryId());
		bo.setQuestionNumber(contractTeiredNotesToQuestionAttachmentRequest
				.getQuestionId());
		bo.setPrimaryEntityId(contractTeiredNotesToQuestionAttachmentRequest
				.getPrimaryId());
		bo.setPrimaryEntityType(contractTeiredNotesToQuestionAttachmentRequest
				.getPrimaryEntityType());

		return bo;
	}

	/**
	 * Method to get the date segment from Contract request
	 * 
	 * @param ContractRequest
	 * @return Datesegment
	 */
	private DateSegment getDateSegment(ContractRequest request) {
		ContractKeyObject contractKeyObject = request.getContractKeyObject();
		if (contractKeyObject == null) {
			Logger.logError("Contract Key Object is null in the request");
			return null;
		}
		DateSegment dateSegment = new DateSegment();

		dateSegment.setDateSegmentSysId(contractKeyObject.getDateSegmentId());
		dateSegment.setContractSysId(contractKeyObject.getContractSysId());
		dateSegment.setContractId(contractKeyObject.getContractId());
		dateSegment.setEffectiveDate(WPDStringUtil
				.getDateFromString(contractKeyObject.getEffectiveDate()));
		dateSegment.setVersion(contractKeyObject.getDateSegmentVersion());
		dateSegment.setStatus(contractKeyObject.getDateSegmentStatus());
		dateSegment.setDateSegmentType(contractKeyObject.getDsType());
		return dateSegment;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public WPDResponse execute(ContractTierDefSaveRequest request)
			throws SevereException, AdapterException {

		Logger
				.logInfo("ContractBusinessService - Entering execute(ContractTierDefSaveRequest request)");

		boolean saveStatus;
		int dateSegmentSysId;
		int benefitCompSysId;
		int benefitCompDefId;
		int tierDefSysId;
		int benefitLevelId;
		String saveStr;
		String tierDefExists;
		Audit audit = new AuditImpl();

		ContractTierDefSaveResponse response = (ContractTierDefSaveResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CONTRACT_BENEFIT_TIER_DEF_SAVE_RESPONSE);

		dateSegmentSysId = request.getDateSegmentId();
		benefitCompSysId = request.getBenefitComponentSysId();
		benefitCompDefId = request.getBenefitDefinitionSysId();
		tierDefSysId = request.getTierDefinitionId();
		benefitLevelId = request.getBenefitDefinitionLevelId();
		saveStr = request.getBenefitCriteriaSaveString();
		tierDefExists = request.getTierDefExits();

		audit.setUser(request.getUser().getUserId());
		Date now = new Date();
		audit.setTime(now);

		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();

		saveStatus = builder.addTierDefinitionToContract(dateSegmentSysId,
				benefitCompSysId, benefitCompDefId, tierDefSysId,
				benefitLevelId, saveStr, tierDefExists, audit);
		response.setStatusFlag(saveStatus);
		return response;
	}

	/**
	 * Method to validate the notes attached to uncoded lines/ unanswered
	 * questions
	 * 
	 * @param contract
	 * @return boolean
	 * @throws SevereException
	 */
	private boolean validateNotes(Contract contract) throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		return builder.validateNotes(contract);
	}

	/*
	 * method for retrieving tier note details.It takes all values from request
	 * and set the values to TierNotesAttachmentOverrideBO @ param
	 * ContractQuestionTierNotesPopUpRequest
	 */
	public WPDResponse execute(ContractQuestionTierNotesPopUpRequest request)
			throws SevereException {
		List messageList = null;
		List noteList = null;
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		ContractQuestionNotesPopUpResponse response = (ContractQuestionNotesPopUpResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CONTRACT_QUESTION_NOTES__RESPONSE);
		TierNotesAttachmentOverrideBO overrideBO = new TierNotesAttachmentOverrideBO();
		overrideBO.setPrimaryEntityId(new Integer(request.getPrimaryEntityID())
				.intValue());
		overrideBO.setPrimaryEntityType(request.getPrimaryType());
		overrideBO.setSecondaryEntityId(new Integer(request.getSecondaryId())
				.intValue());
		overrideBO.setBnftDefnIdString(request.getBenefitDenId());
		overrideBO.setBenefitComponentId(request.getBenefitComponentId());
		overrideBO.setQuestionNumber(request.getQuestionId());
		overrideBO.setSecondaryEntityType(request.getSecondarEntityType());
		overrideBO.setTierSysId(request.getTierSysId());
		if (request.getAction() == 2) {
			if (request.getSearchAction() == 2) {
				overrideBO.setSearchString(request.getSearchString());
				overrideBO.setSearchAction(2);
			}
			// ContractBusinessObjectBuilder builder = new
			// ContractBusinessObjectBuilder();
			// ProductBusinessObjectBuilder builder = new
			// ProductBusinessObjectBuilder();
			noteList = builder.retrieveQuestionTiernNotes(overrideBO);
			if (null == noteList || noteList.size() == 0) {
				messageList = new ArrayList(2);
				if (request.getSearchAction() == 1) {
					messageList.add(new InformationalMessage(
							BusinessConstants.RECORDS_NOT_FOUND));
				} else {
					messageList.add(new InformationalMessage(
							BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
				}
			}
		} else {

			noteList = builder.retrieveQuestionTiernNotesForView(overrideBO);
			if (null == noteList || noteList.size() == 0) {
				messageList = new ArrayList(2);
				messageList.add(new InformationalMessage(
						BusinessConstants.RECORDS_NOT_FOUND));
			}

		}
		addMessagesToResponse(response, messageList);
		if (null != noteList)
			response.setNotesList(noteList);
		return response;

	}

	/**
	 * This method will update the questionnaire taking in values from the
	 * UpdateQuestionnaireRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ContractQuestionTierNoteProcessRequest request)
			throws ServiceException {
		Logger
				.logInfo("ProductBusinessService - Entering execute(ProductQuestionNoteProcessRequest request)");
		ContractQuestionNoteResponse response = null;

		BusinessObjectManager bom = getBusinessObjectManager();

		List messageList = new ArrayList(2);

		// ProductBO productBO = new ProductBO();
		// productBO.setProductName(request.getMainObjectIdentifier());
		// productBO.setBusinessDomains(request.getDomainList());
		// productBO.setVersion(request.getVersionNumber());

		DateSegment dateSegment = getDateSegment(request);

		TierNotesAttachmentOverrideBO attachmentBO = new TierNotesAttachmentOverrideBO();
		attachmentBO.setPrimaryEntityId(request.getPrimaryEntityId());
		attachmentBO.setPrimaryEntityType(request.getPrimaryType());
		attachmentBO.setSecondaryEntityId(request.getSecondaryEntityId());
		attachmentBO.setSecondaryEntityType("ATTACHQUESTION");
		attachmentBO.setQuestionNumber(request.getQuestionId());
		attachmentBO.setNoteId(request.getNoteId());
		attachmentBO.setVersion(request.getNoteVersion());
		attachmentBO.setOverrideStatus("F");
		attachmentBO.setRequestType(request.getNotesAction());
		attachmentBO.setQuestionNumber(request.getQuestionId());
		attachmentBO.setBenefitComponentId(request.getBenefitComponentId());
		attachmentBO
				.setBnftDefnIdString(new Integer(request.getBenefitDefnId())
						.toString());
		attachmentBO.setTierSysId(request.getTierSysId());

		try {
			if (request.getNotesAction() == 1) {
				bom.persist(attachmentBO, dateSegment, request.getUser(), true);
				messageList.add(new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS));
			}
			if (request.getNotesAction() == 2) {
				bom
						.persist(attachmentBO, dateSegment, request.getUser(),
								false);
				messageList.add(new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS));
			}
			if (request.getNotesAction() == 3) {
				bom.delete(attachmentBO, dateSegment, request.getUser());
				messageList.add(new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS));
			}

		} catch (SevereException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		response = (ContractQuestionNoteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CONTRACT_PROCESS_QUESTION_NOTES__RESPONSE);
		response.setMessages(messageList);
		Logger
				.logInfo("ProductBenefitBusinessService - Exiting execute(ProductQuestionNoteProcessRequest request)");
		return response;
	}

	/**
	 * This method will retreive the rule types taking in values from the
	 * RetreiveContractRuleTypeRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetreiveContractRuleTypeRequest request)
			throws SevereException, AdapterException {

		Logger
				.logDebug("ProductBusinessService - Entering execute(RetreiveProductRuleTypeRequest request)");

		List ruleList = null;
		List messageList = new ArrayList();
		List dateSegList = null;

		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		RetreiveContractRuleTypeResponse response = (RetreiveContractRuleTypeResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETREIVE_RULE_TYPE_CONTRACT_RESPONSE);

		// Retreives the datesegments of a contract.
		dateSegList = builder.retrieveDateSegments(request.getContractId());

		// Retreives the Rule Types of Date Segments.
		if (null != dateSegList && !dateSegList.isEmpty()) {
			ruleList = builder.getRuleListForContract(dateSegList);
		}

		if (null != ruleList && !ruleList.isEmpty()) {
			response.setContractRuleList(ruleList);
		}
		ErrorMessage message = new ErrorMessage(
				BusinessConstants.RULE_TYPE_VALIDATION);
		messageList.add(message);
		response.setMessages(messageList);

		return response;
	}

	/**
	 * Service method will retrieve the deleted DS for the contract for the
	 * contract datafeed.
	 * 
	 * @param deletedDSRequest
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(RetrieveDeletedDSRequest request)
			throws ServiceException, AdapterException {

		ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
		RetrieveDeletedDSResponse response = (RetrieveDeletedDSResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DATAFEED_REQUEST_DEL_DS_RETRIEVE);
		int action = request.getAction();

		switch (action) {

		// Retrieving the deleted DS of a scheduled contract
		case BusinessConstants.DELETED_DATE_SEGMENTS:
			try {
				LocateResults locateResults = contractBuilder
						.locateDeletedDatesegment(request.getContractId(),
								request.getStatus());
				response.setSearchResultsList(locateResults.getLocateResults());
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(request);
				String logMessage = "Failed while processing RetrieveDeletedDSRequest";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;

		// Retrieving the root deleted contracts for which all the contract
		// versions are deleted.
		case BusinessConstants.ROOT_DELETE_CONTRACT:
			try {
				LocateResults locateResults = contractBuilder
						.locateRootDeleteContracts(request.getStatus());
				response.setSearchResultsList(locateResults.getLocateResults());
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(request);
				String logMessage = "Failed while processing RetrieveRootDeleteRequest";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;

		// Retrieving the root deleted contracts which are created from a
		// deleted contract version.
		case BusinessConstants.ROOT_DELETE_SCHEDULED_CONTRACT:
			try {
				LocateResults locateResults = contractBuilder
						.locateRootDeleteScheduledContracts(request
								.getContractId(), request.getStatus());
				response.setSearchResultsList(locateResults.getLocateResults());
			} catch (Exception ex) {
				List logParameters = new ArrayList(2);
				logParameters.add(request);
				String logMessage = "Failed while processing RetrieveRootDeleteRequest";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;

		}

		return response;
	}

	/**
	 * The method to root delete the contracts
	 * 
	 * @param datafeedRequest
	 * @param audit
	 */
	private void rootDeleteContract(DatafeedRequest datafeedRequest, Audit audit) {
		// Updating the root delete contracts
		List rootdeletedContracts = datafeedRequest.getRootDeleteList();
		Iterator iter = rootdeletedContracts.iterator();

		while (iter.hasNext()) {
			DeletedDSInfoBO deletedContract = (DeletedDSInfoBO) iter.next();
			ContractSPSBO root = new ContractSPSBO();
			root.setContractId(deletedContract.getContractId());
			if (BusinessConstants.DATAFEED_STATUS_UPDATE_PROD == datafeedRequest
					.getAction())
				root.setDeletedContractIndicator("P");
			if (BusinessConstants.DATAFEED_STATUS_UPDATE_TEST == datafeedRequest
					.getAction())
				root.setDeletedContractIndicator("T");

			root.setLastUpdatedUser(audit.getUser());
			root.setLastUpdatedTimestamp(audit.getTime());
			ContractAdapterManager adapterManager = new ContractAdapterManager();
			try {
				adapterManager.updateDeletedContractRecords(root);
			} catch (WPDException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to Delete the Hard deleted contract from Transfer_log table after
	 * test feed
	 * 
	 * @param audit
	 */
	private void deleteHardDeletedCnts(Audit audit) {
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		try {
			adapterManager.deleteHardDeletedCnts(audit);
		} catch (WPDException e) {
			e.printStackTrace();
		}
	}

	private void triggerAdminMethodValidations(List dateSegmentList,
			String userId) {

		AdminMethodAdapterManager adminMethodAdapterManager = new AdminMethodAdapterManager();
		for (Iterator iter = dateSegmentList.iterator(); iter.hasNext();) {
			DateSegment dateSegment = (DateSegment) iter.next();

			adminMethodAdapterManager.insertValuesIntoAffectedSps(dateSegment
					.getDateSegmentSysId(), true);

			ApplicationContext context = ApplicationContext
					.createApplicationContext();

			//System.out.println("----------- Start of Validation" + new Date());
			Logger.logInfo("----------- End of Validation" + new Date());
			AdminMethodValidationManager validationManager = (AdminMethodValidationManager) context
					.getContext().getBean("adminMethodValidationManager");
			validationManager.validate(new long[] { dateSegment
					.getDateSegmentSysId() }, userId);
			//System.out.println("----------- End of Validation" + new Date());
			Logger.logInfo("----------- End of Validation" + new Date());
			
		}
	}

	private void addAccumulatorsToMap(HashMap questionAccumulatorMap,
			String questionNumber, List accumulators) {
		ArrayList accumList = new ArrayList();
		questionAccumulatorMap.put(questionNumber, accumList);
		Iterator itr = accumulators.iterator();
		while (itr.hasNext()) {
			Object obj = itr.next();
			accumList.add(obj.toString());
		}
	}

	private Map getAllAssociatedAccumsForAdminOption(
			RetrieveAllPossibleAnswerRequest request) {
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		Map associatedAccumulatorsMap = new HashMap();
		List associatedAccumulators = new ArrayList();
		try {
			associatedAccumulators = contractBusinessObjectBuilder
					.getAccumBenefitAssociationForAdminOption(Integer
							.toString(request.getAdminOptSysId()), Integer
							.toString(request.getBenefitId()));
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // GET ASSOCIATED FOR ADMIN OPTION
		Iterator itrAssAccums = associatedAccumulators.iterator();

		while (itrAssAccums.hasNext()) {
			AccumulatorValidationBO accumValBO = (AccumulatorValidationBO) itrAssAccums
					.next();

			if (associatedAccumulatorsMap.containsKey(new Integer(accumValBO
					.getQuestionNumber()))) {
				List accumCodes = (List) associatedAccumulatorsMap
						.get(new Integer(accumValBO.getQuestionNumber()));
				accumCodes.add(accumValBO.getAccumulatorCode());
			} else {
				List accumCodes = new ArrayList();
				accumCodes.add(accumValBO.getAccumulatorCode());
				associatedAccumulatorsMap.put(new Integer(accumValBO
						.getQuestionNumber()), accumCodes);
			}
		}
		return associatedAccumulatorsMap;
	}

	private Map getStdAccumsForAdminOption(
			RetrieveAllPossibleAnswerRequest request, String BYCYAnswer,
			List busDomainList) {
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		Map stdAccumulatorsMap = new HashMap();
		List stdAccumulatorsLst = new ArrayList();
		try {
			stdAccumulatorsLst = contractBusinessObjectBuilder
					.getStdAccumulatorAdminOption(request.getAdminOptSysId(),
							request.getBenefitId(), BYCYAnswer, busDomainList);
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator itrStdAccums = stdAccumulatorsLst.iterator();
		while (itrStdAccums.hasNext()) {
			StandardAccumulator stdAccumBO = (StandardAccumulator) itrStdAccums
					.next();

			if (stdAccumulatorsMap.containsKey(new Integer(stdAccumBO
					.getQuestion()))) {
				List stdAccums = (List) stdAccumulatorsMap.get(new Integer(
						stdAccumBO.getQuestion()));
				stdAccums.add(stdAccumBO.getStandardAccumulatorStr());
			} else {
				List stdAccums = new ArrayList();
				stdAccums.add(stdAccumBO.getStandardAccumulatorStr());
				stdAccumulatorsMap.put(new Integer(stdAccumBO.getQuestion()),
						stdAccums);
			}
		}
		return stdAccumulatorsMap;
	}

}