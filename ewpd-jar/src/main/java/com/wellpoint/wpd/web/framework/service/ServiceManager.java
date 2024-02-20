/*
 * ServiceManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.framework.service;

import com.wellpoint.wpd.business.framework.service.BusinessServiceController;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * Service manager class of framework.
 */
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ServiceManager.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class ServiceManager {

	public static int BUSINESS_SERVICE = 1;

	public static int VALIDATION_SERVICE = 2;

	public static final String CONTRACT_RULE_SET_REQUEST = "contractRulesetRequest";

	public static final String CONTRACT_RULE_VALIDATION_REQUEST = "contractRuleValidationRequest";

	//ContractTransferLog Request
	public static final String CONTRACT_TRANSFER_LOG_REQUEST = "contractTransferLogRequest";

	//Sps mapping requests
	public static final String SPS_MAPPING_CREATE_REQUEST = "spsMappingCreateRequest";

	public static final String SPS_MAPPING_UPDATE_REQUEST = "spsMappingUpdateRequest";

	public static final String SPS_MAPPING_RETRIEVE_REQUEST = "spsMappingRetrieveRequest";
	//Web Service Request
	public static final String CREATE_CONTRACT_WEBSERVICE_REQUEST ="contractWebServiceRequest";

	// Custom Message Test Requests
	public static final String CUSTOM_MESSAGE_CREATE_REQUEST = "customMessageTextCreateRequest";

	public static final String CUSTOM_MESSAGE_UPDATE_REQUEST = "customMessageTextUpdateRequest";

	public static final String CUSTOM_MESSAGE_RETRIEVE_REQUEST = "customMessageRetrieveRequest";

	//migration wizard	
	public static final String RETRIEVE_LAST_ACCESS_PAGE_REQUEST = "saveLastAccessPageInfoRequest";

	public static final String RETRIEVE_LEGACY_CONTRACT_REQUEST = "retrieveLegacyContractRequest";

	public static final String SAVE_LEGACY_CONTRACT_REQUEST = "saveLegacyContractRequest";

	//Search Engine requests
	public static final String BASIC_SEARCH_REQUEST = "basicSearchRequest";

	public static final String ADVANCED_SEARCH_REQUEST = "advancedSearchRequest";

	public static final String RETRIEVE_REQUEST = "retrieveRequest";

	public static final String SORT_REQUEST = "sortRequest";

	public static final String RETRIEVE_ASSOCIATION_REQUEST = "retrieveAssociationRequest";

	public static final String DOMAIN_FECTH_REQUEST = "domainFetchRequest";

	public static final String BENEFIT_VIEW_REQUEST = "benefitViewRequest";

	public static final String BENEFIT_COMPONENT_VIEW_REQUEST = "benefitComponentViewRequest";

	public static final String BENEFIT_COMPONENT_SEARCH_VIEW_REQUEST = "benefitComponentSearchViewRequest";

	public static final String PRODUCT_STRUCTURE_VIEW_REQUEST = "productStructureViewRequest";

	public static final String CONTRACT_VIEW_REQUEST = "contractViewRequest";

	public static final String PRODUCT_VIEW_REQUEST = "productViewRequest";

	//User or security service requests
	public static final String RETRIEVE_USER = "retrieveUser";

	//contract requests
	public static final String DELETE_SERVICETYPEMAPPING = "deleteServiceTypeMappingRequest";

	public static final String DELETE_CONTRACT = "deleteContractRequest";

	public static final String CREATE_CONTRACT_BASICINFO_BENEFIT = "saveContractBasicInfoRequest";
	
	public static final String CREATE_CONTRACT_WS_INFO_BENEFIT ="saveContractWebServiceRequest";

	public static final String SAVE_CONTRACTSPINFOREQUEST = "saveContractSpecificInfoRequest";

	public static final String SEARCH_CONTRACT_REQUEST = "contractSearchRequest";

	public static final String RETRIEVE_RES_CONT_ID = "retrieveResContIdRequest";

	public static final String RETRIEVE_CONTRACTPRICING_INFO_REQUEST = "retrievePricingInfoRequest";

	public static final String SAVE_ADD_PRICING_INFO = "savePricingInfoRequest";

	public static final String LOCATE_PRICING_INFO = "locatePricingInfoRequest";

	public static final String DELETE_PRICING_INFO_REQUEST = "deletePricingInfoRequest";

	public static final String RETRIEVE_CONTRACTSPINFOREQUEST = "retrieveContractSpecificInfoRequest";

	public static final String LOCATE_PRODUCT_REQUEST = "locateProductRequest";

	public static final String RETRIEVE_CONTRACT_BENEFIT_COMPONENT_REQUEST = "retrieveContractBenefitComponentRequest";

	public static final String RETRIEVE_CONTRACTSBREQUEST = "retrieveContractStandardBenefitRequest";

	public static final String BENEFIT_CUSTOMIZATION = "benefitCustomizationRequest";

	public static final String RETRIEVE_CONTRACTBENEFITMANDATEREQUEST = "retrieveContractBenefitMandateRequest";

	public static final String SAVE_DATE_SEGMENT_CONTRACT_COMMENT = "saveDateSegmentCommentRequest";

	public static final String RETRIEVE__DATE_SEGMENT_CONTRACT_COMMENT = "retieveDateSegmentCommentRequest";

	public static final String SAVE_CONTRACT_HEADINGS = "saveContractHeadingsRequest";

	public static final String RETRIEVE_CODED_NONCODED_BENEFIT_HEADINGS_REQUEST = "retrieveCodedNonCodedBenefitHeadingsRequest";

	public static final String SAVE_ADAPTEDINFOREQUEST = "saveContractAdaptedInfoRequest";

	public static final String SEARCH_SERVICE_TYPE_REQUEST = "serviceTypeMappingSearchRequest";

	public static final String VIEW_SERVICE_TYPE_CODE_MAPPING_REQUEST = "viewServiceTypeCodeMappingRequest";

	//	Administration security  requests

	public static final String TASK_SEARCH_REQUEST = "taskSearchRequest";

	public static final String DELETE_TASK_REQUEST = "taskDeleteRequest";

	public static final String SUB_TASK_LOOK_UP_REQUEST = "subTaskLookUpRequest";

	public static final String LOCATE_SUB_TASK_REQUEST = "locateSubTaskRequest";

	public static final String SAVE_MODULE_REQUEST = "saveModuleRequest";

	public static final String SAVE_SUB_TASK_REQUEST = "saveSubTaskRequest";

	public static final String DELETE_MODULE_REQUEST = "moduleDeleteRequest";

	public static final String MODULE_TASK_LOOKUP_REQUEST = "moduleTaskLookupRequest";

	public static final String ROLE_TASK_LOOKUP_REQUEST = "roleTaskLookupRequest";

	public static final String MODULE_ASSOCIATION_REQUEST = "moduleAssociationRequest";

	public static final String ROLE_ASSOCIATION_REQUEST = "roleAssociationRequest";

	public static final String SAVE_MODULE_ASSOCIATION_REQUEST = "saveModuleAssociationRequest";

	public static final String DELETE_MODULE_TASK_ASSOCIATION_REQUEST = "DeleteModuleAssociationRequest";

	public static final String DELETE_ROLE_REQUEST = "roleDeleteRequest";

	public static final String SAVE_ROLE_ASSOCIATION_REQUEST = "saveRoleAssociationRequest";

	public static final String SAVE__RESERVED_CONTRACT = "saveReservedContractRequest";

	public static final String RETRIEVE__RESERVED_CONTRACT = "retrieveReservedContractRequest";

	public static final String RELEASE__RESERVED_CONTRACT = "releaseReservedContractRequest";

	public static final String RETRIEVE_SYSTEMPOOL_CONTRACT = "retrieveSystemPoolIdRequest";

	public static final String CREATE_REF_MAPPING_REQUEST = "createReferenceMappingRequest";
	
	public static final String CREATE_ADMINMETHOD_CREATE_REQUEST = "createAdminMethodCreateRequest";	
	
	public static final String CREATE_ADMINMETHOD_MAPPING_CREATE_REQUEST = "createAdminMethodMappingCreateRequest";	
	
	public static final String EDIT_ADMINMETHOD_MAPPING_CREATE_REQUEST = "adminMethodMappingEditRequest";	
	
	
	public static final String QUESTION_ANSWER_LOOKUP_REQUEST = "QuestionAnswerLookupRequest";	
	

	public static final String EDIT_REF_MAPPING_REQUEST = "editReferenceMappingRequest";

	public static final String SEARCH_REF_MAPPING_REQUEST = "searchReferenceMappingRequest";
	public static final String SEARCH_ADMIN_METHOD_REQUEST = "adminMethodSearchRequest";
	
	public static final String DELETE_ADMIN_METHOD_REQUEST = "adminMethodDeleteRequest";
	
	public static final String VIEW_ADMIN_METHOD_REQUEST = "viewAdminMethodRequest";
	public static final String VIEW_ADMIN_METHOD_MAPPING_REQUEST = "viewAdminMethodMappingRequest";
	public static final String EDIT_ADMIN_METHOD_REQUEST = "editAdminMethodRequest";
	public static final String SEARCH_ADMINMETHOD_VIEWALL_REQUEST = "viewAllAdminMethodRequest";
	public static final String SEARCH_ADMINMETHOD_MAPPING_REQUEST = "searchAdminMethodMappingRequest";
	public static final String DELETE_ADMINMETHOD_MAPPING_DELETE_REQUEST = "deleteAdminMethodMappingRequest";

	

	public static final String DELETE_REF_MAPPING_REQUEST = "deleteReferenceMappingRequest";

	public static final String RETRIEVE_REF_MAPPING_REQUEST = "retrieveReferenceMappingRequest";

	//public static final String RETRIEVE_RESERVED_CONTRACT = "releaseReservedContractRequest";
	public static final String DELETE_ROLE_MODULE_ASSOCIATION_REQUEST = "deleteRoleModuleAssociationRequest";

	public static final String RETRIEVE__RULES = "retieveRuleRequest";

	public static final String RETRIEVE__DATE_SEGMENTS = "retieveDateSegmentRequest";

	public static final String CREATE__DATE_SEGMENTS = "createDateSegmentRequest";
	
	public static final String CHECK_COPY_LEGACY_NOTE = "checkCopyLegacyNote";

	public static final String RETREIVE_CONTRACT_BASICINFO_REQUEST = "retrieveBasicInfoRequest";

	public static final String RETRIEVE__SELECTED_CONTRACT_COMMENT = "retieveSelectedCommentRequest";

	public static final String RETRIEVE_CONTRACT_BENEFIT_ADMINISTRATION = "retrieveContractBenefitAdministrationRequest";
	
	public static final String RETRIEVE_ALL_POSSIBLE_ANSWER_LIST = "retrieveAllPossibleAnswerRequest";

	public static final String SAVE_CONTRACT_BENEFIT_ADMINISTRATION = "saveContractBenefitAdministrationRequest";

	public static final String RETRIEVE_BENEFIT_GENRALINFO = "retrieveBenefitGeneralInfoRequest";

	public static final String ATTACH_CONTRACT_BENEFIT_COMPONENT_NOTES_REQUEST = "attachContractBenefitComponentNotesRequest";

	public static final String RETREIVE_BASE_CONTRACT_REQUEST = "retrieveBaseContractRequest";

	public static final String RETREIVE_BASE_CONTRACT_DATE_REQUEST = "retrieveBaseContractDateRequest";

	public static final String RETREIVE_RESERVED_ID_REQUEST = "retrieveReservedContractIdRequest";

	public static final String RETRIEVE_BENEFIT_HEADINGS_REQUEST = "retrieveBenefitHeadingsRequest";

	public static final String ATTACH_CONTRACT_NOTES_REQUEST = "contractNoteAttachmentRequest";

	public static final String RETRIEVE_CONTRACT_PRODUCT = "retrieveContractProductRequest";

	public static final String DELETE_CONTRACT_NOTES_REQUEST = "contractNotesDeleteRequest";

	public static final String LOAD_CONTRACT_COMPONENT_NOTE_REQUEST = "loadContractComponentNoteRequest";

	public static final String DELETE_CONTRACT_COMPONENT_NOTE_REQUEST = "deleteContractComponentNoteRequest";

	public static final String RETRIEVE_CONTRACT_NOTES = "retrieveContractNoteRequest";

	public static final String SAVE_CONTRACT_BENEFIT_NOTE_REQUEST = "saveContractBenefitNoteRequest";

	public static final String LOAD_CONTRACT_BENEFIT_NOTE_REQUEST = "loadContractBenefitNoteRequest";

	public static final String SAVE_TEST_DATA_REQUEST = "SaveTestDataRequest";

	public static final String SEARCH_RESERVED_CONTRACT_REQUEST = "reservedContractSearchRequest";

	public static final String SAVE_CONTRACT_PRODUCT_ADMIN = "saveContractProductAdminRequest";

	public static final String CONTRACT_PRODUCT_ADMIN = "retrieveContractProductAdminRequest";

	public static final String DELETE_CONTRACT_PRODUCT_ADMIN = "deleteContractProductAdminRequest";

	public static final String SAVE_RULEID_CONTRACTBEN_REQUEST = "saveRuleIdContractBenefitRequest";

	public static final String RETRIEVE_CONTRACT_PRODUCT_ADMINOPTION_OVERRIDE_REQUEST = "retrieveContractProductAdminOptionOverrideRequest";

	public static final String SAVE_CONTRACT_ADMINOPTION_OVERRIDE_REQUEST = "saveContractAdministrationRequest";

	public static final String RETRIEVE_CONTRACT_IDEXISTING_REQUEST = "retrieveExistingContractRequest";

	public static final String RETRIEVE_CONTRACT_IDEXPIRED_REQUEST = "retrieveExpiredContractIdRequest";

	public static final String DATESEGMENT_CHECKOUT_REQUEST = "dateSegmentCheckoutRequest";

	public static final String MEMBERSHIP_INFO = "membershipInfoRequest";

	public static final String BENEFIT_MANDATE_INFO = "retrieveBenefitMandatesRequest";

	//contract requests ends

	//Benefit service requests
	public static final String RETRIEVE_BENEFIT = "retrieveBenefit";

	public static final String PERSIST_BENEFIT = "persistBenefit";

	public static final String DELETE_BENEFIT = "deleteBenefit";

	public static final String CHECKOUT_BENEFIT = "checkOutBenefit";

	public static final String LOCATE_BENEFIT = "locateBenefit";

	public static final String RETRIEVE_ATTACHMENT_REQUEST = "attachmentRequest";

	public static final String CREATE_BENEFIT = "createBenefitRequest";

	public static final String CREATE_QUESTION = "createQuestionRequest";
	
	public static final String FETCH_STDACCUM = "fetchStdaccumRequest";
	
	public static final String FETCH_ACCUM_FORPOPUP = "fetchpopupforaccum";
	
	public static final String SEARCH_STDACCUM = "searchStdaccumRequest";
	
	public static final String SAVE_STDACCUM = "saveStdaccumRequest";
	
	public static final String SEARCH_STDACCUM1 = "searchStdaccumRequest";

	public static final String EDIT_STDACCUM = "editStdaccumRequest";

	public static final String SB_SEARCH_REQUEST = "StandardBenefitSearchRequest";

	public static final String UNLOCK_BENEFIT = "unLockBenefitRequest";

	public static final String SEARCH_BENEFIT_LEVEL_TERM_REQUEST = "searchBenefitLevelTermRequest";

	//admin
	public static final String ADMIN_ATTACHMENT_REQUEST = "adminAttachmentRequest";

	// Notes
	public static final String NOTES_ATTACHMENT_REQUEST = "notesAttachmentRequest";

	public static final String BNFT_COMP_NOTES_TO_QUESTION_ATTACHMENT_REQUEST = "bnftCompNotesToQuestionAttachmentRequest";

	public static final String CNTRCT_NOTES_TO_QUESTION_ATTACHMENT_REQUEST = "contractNotesToQuestionAttachmentRequest";
	
	public static final String CNTRCT_TEIRED_NOTES_TO_QUESTION_ATTACHMENT_REQUEST = "contractTeiredNotesToQuestionAttachmentRequest";

	public static final String CNTRCT_AO_NOTES_TO_QUESTION_ATTACHMENT_REQUEST = "contractAONotesToQuestionAttachmentRequest";

	public static final String PROD_AO_NOTES_TO_QUESTION_ATTACHMENT_REQUEST = "productAONotesToQuestionAttachmentRequest";

	public static final String BENEFIT_LEVEL_NOTES_OVERRIDE_REQUEST = "benefitLevelNotesRequest";

	public static final String BENEFIT_LINE_NOTES_OVERRIDE_VIEW_REQUEST = "benefitLineNotesRequest";

	// Product Requests
	public static final String PRODUCT_RULE_REF_DATA = "productRuleRefDataRequest";

	public static final String SAVE_PRODUCT_RULES = "saveProductRulesRequest";

	public static final String DELETE_PRODUCT_RULE = "deleteProductRuleRequest";

	public static final String PRODUCT_NOTE_DELETE = "deleteProductNoteRequest";

	public static final String SAVE_PRODUCT_NOTE = "saveProductNoteRequest";

	public static final String DELETE_PRODUCT = "deleteProductRequest";

	public static final String SEARCH_PRODUCT = "searchProductRequest";

	public static final String SAVE_PRODUCT = "saveProductRequest";

	public static final String RETRIEVE_PRODUCT = "retrieveProduct";

	public static final String RETRIEVE_VALID_PROD_STRS = "retrieveValidProdStrRequest";

	public static final String PRODUCT_BENEFIT_COMPONENT = "retrieveProductBenefitComponents";

	public static final String PRODUCT_BENEFIT_COMPONENT_DELETE = "deleteProductBenefitComponents";

	public static final String SAVE_PRODUCT_COMPONENT = "saveProductComponents";

	public static final String RETRIEVE_PRODUCT_BENEFIT = "retrieveProductBenefit";

	public static final String RETRIEVE_PRODUCT_BENEFIT_ADMIN = "retrieveProductBenefitAdmin";

	public static final String SAVE_PRODUCT_BENEFIT_ADMINISTRATION = "saveProductBenefitAdmin";

	public static final String CREATE_BENEFIT_DEFINITION = "saveBenefitDefintionRequest";

	public static final String CREATE_STANDARD_BENEFIT = "createStandardBenefitRequest";

	public static final String SAVE_PRODUCT_ADMIN = "saveProductAdmin";

	public static final String PRODUCT_ADMIN = "retrieveProductAdmin";

	public static final String PRODUCT_ADMIN_DELETE = "deleteProductAdmin";

	public static final String PRODUCT_BENEFIT_ADMIN_OPTION = "productBenefitAdminOptionRequest";

	public static final String ADMIN_METHOD_SPS_VALIDATION = "adminMethodSPSRetreiveValidationRequest";

	public static final String SAVE_PRODUCT_BENEFIT_ADMIN_OPTION = "saveProductAdministrationRequest";

	public static final String RETRIEVE_PRODUCT_COMPONENT_HIERARCHY = "retrieveProductComponentHierarchyRequest";

	//Catalog service requests
	public static final String SAVE_CATALOG_REQUEST = "saveCatalogRequest";

	public static final String EDIT_CATALOG_REQUEST = "editCatalogRequest";

	public static final String DELETE_CATALOG_REQUEST = "deleteCatalogRequest";

	public static final String SEARCH_CATALOG = "searchCatalogRequest";

	public static final String SEARCH_ITEM = "searchItemRequest";

	public static final String RETRIEVE_CATALOG_ID = "retrieveCatalogIdRequest";
	
	public static final String RETRIEVE_SRDA_CATALOG_ID = "retrieveSrdaCatalogIdRequest";

	//  Sub-Catalog service requests
	public static final String SAVE_SUB_CATALOG_REQUEST = "saveSubCatalogRequest";

	public static final String DELETE_SUB_CATALOG_REQUEST = "deleteSubCatalogRequest";

	public static final String SEARCH_SUB_CATALOG = "searchSubCatalogRequest";

	public static final String RETRIEVE_SUB_CATALOG = "retrieveSubCatalogRequest";

	public static final String ITEM_LOOKUP_REQUEST = "searchAssociatedItemRequest";

	public static final String SAVE_ITEM_ASSOCIATION_REQUEST = "saveItemAssociationRequest";

	public static final String DELETE_ITEM_ASSOCIATION_REQUEST = "deleteItemAssociationRequest";

	public static final String UPDATE_STANDARD_BENEFIT = "updateStandardBenefitRequest";

	public static final String RETRIEVE_STANDARD_BENEFIT = "retrieveStandardBenefitRequest";

	public static final String DELETE_STANDARD_BENEFIT = "deleteStandardBenefitRequest";

	public static final String DELETE_STANDARD_BENEFIT_VERSION = "deleteStandardBenefitVersionsRequest";

	public static final String STD_BEN_VERSIONS_LIFECYCLE = "benefitVersionsLifeCycle";

	public static final String CREATE_BENEFIT_ADMINISTRATION = "createBenefitAdministrationRequest";

	public static final String RETRIEVE_BENEFIT_ADMINISTRATION = "retrieveBenefitAdministrationRequest";

	public static final String UPDATE_BENEFIT_ADMINISTRATION = "updateBenefitAdministrationRequest";

	public static final String DELETE_BENEFIT_ADMINISTRATION = "deleteBenefitAdministrationRequest";

	// Item Requests

	public static final String MAINTAIN_ITEM = "maintainItemRequest";

	public static final String CREATE_ITEM_REQUEST = "createItemRequest";

	public static final String RETRIEVE_ITEM_REQUEST = "retrieveItemRequest";

	public static final String ITEM_VIEW_REQUEST = "locateItemRequest";

	//  Question service requests
	public static final String SAVE_QUESTION_REQUEST = "saveQuestionRequest";

	public static final String RETRIEVE_QUESTION_REQUEST = "retrieveQuestionRequest";

	public static final String CHECKOUT_QUESTION_REQUEST = "checkOutQuestionRequest";

	public static final String SEARCH_QUESTION_REQUEST = "searchQuestionRequest";

	public static final String SCHEDULEFORTEST_QUESTION_REQUEST = "scheduleForTestQuestionRequest";

	public static final String PUBLISH_QUESTION_REQUEST = "publishQuestionRequest";

	public static final String UNLOCK_QUESTION_REQUEST = "unlockQuestionRequest";

	public static final String RETRIEVE_BENEFIT_DEFINITION = "retrieveBenefitDefinition";

	public static final String LOCATE_BENEFIT_DEFINITION = "locateBenefitDefinition";

	//Benefit Mandate
	public static final String CREATE_NON_ADJ_BENEFIT_MANDATE = "saveNonAdjBenefitMandateRequest";

	public static final String LOCATE_BENEFIT_ADMINISTRATION = "locateBenefitAdministration";

	public static final String LOCATE_BENEFIT_MANDATE = "locateBenefitMandate";

	public static final String DELETE_BENEFIT_MANDATE = "deleteBenefitMandate";

	// NonAdjBenefitMAndate in benefitComponent
	public static final String LOCATE_NON_ADJ_BENEFIT_MANDATE = "locateNonAdjBenefitMandate";

	public static final String SEARCH_DATATYPE_REQUEST = "searchDataTypeRequest";

	public static final String DELETE_QUESTION_REQUEST = "deleteQuestionRequest";

	//	 Admin Option popup
	public static final String LOOK_UP_ADMIN_OPTION_LIST = "adminOptionList";

	// mandate list request for the mandatePopUp in the mandateDefinitionCreate.jsp
	public static final String LOCATE_MANDATE_LIST = "locateMandateList";

	//	 Benefit Level list request for the benefitLevelPopUp in the benefitLevelPopUp.jsp
	public static final String LOCATE_BENEFITLEVEL_LIST = "locateBenefitLevelList";

	// non adj mandate retrieve request for the edit icon in the benefitMandateCreate.jsp
	public static final String RETRIEVE_NON_ADJ_MANDATE = "retrieveNonAdjMandateForEdit";

	//Product Structure
	public static final String SAVE_PRODUCT_STRUCTURE = "saveProductStructureRequest";

	public static final String SAVE_PRODUCT_STRUCTURE_COMPONENT = "saveProductStructureComponentRequest";

	public static final String RETRIEVE_BENEFIT_COMPONENT = "retrieveBenefitComponentForPSRequest";

	public static final String RETRIEVE_BENEFIT_COMPONENT_PS = "retrieveBenefitComponentRequest";

	public static final String RETRIEVE_PRODUCT_STRUCTURE = "retrieveProductStructureRequest";

	public static final String RETRIEVE_BENEFIT_DEFENITION = "retrieveBenefitDefenitionRequest";

	public static final String DELETE_BENEFIT_DEFINITION = "deleteBenefitDefinition";

	public static final String CHECKOUT_PRODUCT_STRUCTURE = "checkoutProductStructureRequest";

	public static final String RETRIEVE_PRODUCT_STUCTURE_BENEFIT_ADMINISTRATION = "retrieveProductStructureBenefitAdministrationRequest";

	public static final String SAVE_PRODUCT_STUCTURE_BENEFIT_ADMINISTRATION = "saveProductStructureBenefitAdministrationRequest";

	public static final String SAVE_PRODUCT_STRUCTURE_BENEFIT_DEFINITION_REQUEST = "saveProductStructureBenefitDefinitionRequest";

	public static final String LIFE_CYCLE_PRODUCT_STRUCTURE = "lifeCycleProductStructureRequest";

	public static final String RETRIEVE_COMPONENT_HIERARCHY = "retrieveComponentHierarchyRequest";

	// Admin Option requests
	public static final String CREATE_ADMIN_OPTION_REQUEST = "createAdminOptionRequest";

	public static final String RETRIEVE_ADMIN_OPTION_REQUEST = "retrieveAdminOptionRequest";

	public static final String RETRIEVE_ADMIN_OPTION_QUESTION_REQUEST = "retrieveAdminOptionQuestionRequest";

	public static final String DUPLICATE_REFERENCE_REQUEST = "searchDuplicateReferenceRequest";

	public static final String ADD_ADMIN_OPTION_QUESTION_REQUEST = "addAdminOptionQuestionRequest";

	public static final String DELETE_ADMIN_OPTION_QUESTION_REQUEST = "deleteAdminOptionQuestionRequest";

	public static final String ADMIN_OPTION_SEARCH_REQUEST = "searchAdminOptionRequest";

	public static final String ADMIN_OPTION_DELETE_REQUEST = "deleteAdminOptionRequest";

	public static final String ADMIN_OPTION_DELETE_ALL_REQUEST = "deleteAllAdminOptionRequest";

	public static final String ADMIN_OPTION_CHECKIN_REQUEST = "checkInAdminOptionRequest";

	public static final String ADMIN_OPTION_CHECKOUT_REQUEST = "checkOutAdminOptionRequest";

	public static final String TEST_FAIL_ADMINOPTION_REQUEST = "testFailAdminOptionRequest";

	public static final String ADMIN_METHOD_SYNC_VALIDATION_REQ = "adminMethodSynchronousValidationRequest";

	public static final String DELETE_QUESTIONNAIRE_REQUEST = "deleteQuestionnaireRequest";

	public static final String ADMIN_QUESTION_REQUEST = "adminQuestionRequest";

	//Locate Check out objects requests
	public static final String LOCATE_CHKOUT_OBJ_REQUEST = "locateChkOutObjRequest";

	public static final String CHECK_IN_REQUEST = "checkinRequest";

	public static final String CHECK_OUT_REQUEST = "checkoutRequest";

	public static final String PUBLISH_REQUEST = "publishRequest";

	public static final String LOCATE_REQUEST = "locateRequest";

	public static final String REF_DATA_REQUEST = "RefDataRequest";

	public static final String REF_DATA_LOOK_UP_REQUEST = "refDataLookUpRequest";
	
	public static final String QUES_ANS_LOOKUP_REQUEST  = "quesAnswerRequest";

	public static final String RET_INDICATIVE_MAPPING_REQUEST = "retIndicativeMappingRequest";

	public static final String SEARCH_PRODUCT_STRUCTURE = "searchProductStructureRequest";

	public static final String DELETE_PRODUCT_STRUCTURE = "deleteProductStructureRequest";

	public static final String VIEW_PRODUCT_STRUCTURE_VERSIONS_REQUEST = "viewAllProductStructureVersionsRequest";

	public static final String DELETE_BENEFIT_LEVEL_REQUEST = "deleteBenefitLevelRequest";

	public static final String SAVE_BENEFIT_LEVEL_REQUEST = "saveBenefitLevelRequest";

	public static final String CREATE_BENEFIT_LEVEL_REQUEST = "benefitLevelRequest";

	// selectedQuestionListRequest for the addQuestion.jsp
	public static final String SELECTED_QUESTION_LIST = "locateSelectedQuestionList";

	// saveQuestionRequest for the addQuestion.jsp
	public static final String SAVE_UPDATE_QUESTION_REQUEST = "addQuestionRequest";

	// hideQuestionRequest for the addQuestion.jsp
	public static final String HIDE_QUESTION_REQUEST = "hideQuestionRequest";

	// possibleAnswersRequest for the addQuestion.jsp
	public static final String POSSIBLE_ANSWERS_REQUEST = "possibleAnswersRequest";

	public static final String QUESTION_VIEW_REQUEST = "questionViewRequest";

	public static final String SAVE_ADMIN_OPTION_REQUEST = "saveAdminOptionRequest";

	public static final String LOCATE_ADMIN_OPTION_REQUEST = "locateAdministrationOptionRequest";

	public static final String RETRIEVE_ADMINISTRATIVE_OPTION_REQUEST = "retrieveAdministrationOptionRequest";

	public static final String DELETE_ADMINISTRATIVE_OPTION_REQUEST = "deleteAdministrationOptionRequest";

	// Mandate Requests
	public static final String MANDATE_LOCATE_REQUEST = "locateMandateRequest";

	public static final String RETRIEVE_LOCATE_REQUEST = "retrieveMandateRequest";

	public static final String CREATE_MANDATE_REQUEST = "createMandateRequest";

	public static final String RETRIEVE_MANDATE_REQUEST = "retrieveMandateRequest";

	public static final String MANDATE_DELETE_REQUEST = "deleteMandateRequest";

	public static final String DATA_TYPE_RETRIEVE_REQUEST = "dataTypeRetrieveRequest";

	public static final String SEARCH_BENEFIT_LEVEL_REQUEST = "searchBenefitLevelRequest";

	//Benefit Component Requests
	public static final String SAVE_BENEFIT_COMPONENT_REQUEST = "saveBenefitComponentRequest";

	public static final String SEARCH_BENEFIT_COMPONENT_REQUEST = "benefitComponentSearchRequest";

	public static final String CHECKOUT_BENEFIT_COMPONENT_REQUEST = "benefitComponentCheckOutRequest";

	public static final String PUBLISH_BENEFIT_COMPONENT_REQUEST = "benefitComponentPublishRequest";

	public static final String SCHEDULE_TEST_BENEFIT_COMPONENT_REQUEST = "benefitComponentScheduleForTestRequest";

	public static final String TEST_PASS_BENEFIT_COMPONENT_REQUEST = "benefitComponentTestPassRequest";

	public static final String TEST_FAIL_BENEFIT_COMPONENT_REQUEST = "benefitComponentTestFailRequest";

	public static final String LOCATE_BENEFIT_COMPONENT_BENEFIT_ADMINISTRATION = "locateBenefitComponentBenefitAdministrationRequest";

	public static final String UPDATE_BENEFIT_COMPONENT_BENEFIT_ADMINISTRATION = "updateBenefitComponentBenefitAdministrationRequest";

	public static final String BENEFIT_COMPONENT_RETRIEVE_REQUEST = "benefitComponentRetrieveRequest";

	public static final String DELETE_BENEFIT_COMPONENT_REQUEST = "benefitComponentDeleteRequest";

	public static final String ATTACH_BENEFIT_COMPONENT_NOTES_REQUEST = "benefitComponentAttachNotesRequest";

	public static final String LOCATE_BENEFIT_COMPONENT_NOTES_REQUEST = "locateBenefitComponentNotesRequest";

	public static final String DELETE_BENEFIT_COMPONENT_NOTES_REQUEST = "deleteBenefitComponentNotesRequest";

	public static final String LOCATE_STD_BEN_OVERRIDE_NOTES_REQUEST = "locateStdBenOverrideNotesRequest";

	public static final String PRODUCT_BENEFIT_MANDATE_RETRIEVE_REQUEST = "productBenefitMandateRetrieveRequest";

	//END : BENEFIT COMPONENT REQUESTS

	public static final String CHECKIN_STANDARD_BENEFIT = "checkInStandardBenefitRequest";

	public static final String COPY_STANDARD_BENEFIT = "copyStandardBenefitRequest";

	public static final String CHECKOUT_STANDARD_BENEFIT = "checkOutStandardBenefit";

	//BenefitComponentRetrieveRequest for edit
	public static final String BENEFIT_COMPONENT_RETRIEVE_REQUEST_FOR_EDIT = "benefitComponentRetrieveRequestForEdit";

	public static final String BENEFIT_HIERARCHY_CREATE_REQUEST = "benefitHierarchyCreateRequest";

	public static final String BENEFIT_RETRIEVE_REQUEST = "benefitRetrieveRequest";

	public static final String RULE_REQUEST = "ruleRequest";

	public static final String GROUP_RULE_REQUEST = "groupRuleRequest";

	public static final String RETRIEVE_PRODUCT_BENEFIT_DEFINITION_REQUEST = "retrieveProductBenefitDefinitionRequest";

	//	BenefitComponentRetrieveRequest for view
	public static final String RETRIEVE_BENEFIT_VIEW_REQUEST = "retrieveBenefitRequest";

	// componentsBenefitDefinitionRequest
	public static final String LOCATE_COMPONENTS_BENEFIT_DEFINITION_REQUEST = "componentsBenefitDefinitionRequest";

	//	hideQuestionRequest for the addQuestion.jsp
	public static final String HIDE_BENEFIT_LEVEL_REQUEST = "hideBenefitLevelRequest";

	// updateBenefitLinesRequest for the addQuestion.jsp
	public static final String UPDATE_BENEFIT_LINES_REQUEST = "updateBenefitLinesRequest";

	public static final String SAVE_PRODUCT_BENEFIT_DEFINITION_REQUEST = "saveProductBenefitDefinitionRequest";

	public static final String SAVE_CONTRACT_BENEFIT_DEFINITION_REQUEST = "saveContractBenefitDefinitionRequest";

	public static final String MANDATE_CHECKIN_REQUEST = "mandateCheckinRequest";

	public static final String CHECKOUT_MANDATE_REQUEST = "checkOutMandateRequest";

	public static final String BENEFIT_HIERARCHY_UPDATE_REQUEST = "benefitHierarchyUpdateRequest";

	public static final String BENEFIT_HIERARCHY_DELETE_REQUEST = "benefitHierarchyDeleteRequest";

	public static final String SEARCH_BENEFIT_HIEARARCHY_REQUEST = "benefitHierarchySearchRequest";

	public static final String COPY_MANDATE_REQUEST = "copyMandateRequest";

	public static final String VIEW_ALL_MANDATE_REQUEST = "viewAllMandateRequest";

	public static final String BENEFIT_LEVEL_POPUP_REQUEST = "benefitLevelPopUpRequest";

	public static final String CHECKIN_BENEFIT_COMPONENT = "benefitComponentCheckInRequest";

	public static final String ADMIN_OPTION_VIEW_REQUEST = "adminOptionViewRequest";

	public static final String SEND_FOR_TEST_MANDATE_REQUEST = "mandateSendForTestRequest";

	public static final String TEST_FAIL_MANDATE_REQUEST = "testFailMandateRequest";

	public static final String PUBLISH_MANDATE_REQUEST = "mandatePublishRequest";

	public static final String VIEW_ADMIN_OPTION_REQUEST = "viewadminoptionrequest";

	public static final String PUBLISH_STD_BENEFIT_REQUEST = "publishStandardBenefitRequest";

	public static final String BENEFIT_COMPONENT_COPY_REQUEST = "benefitComponentCopyRequest";

	public static final String STD_BEN_SCHEDULE_FOR_TEST = "stdBenScheduleForTestRequest";

	public static final String STD_BEN_TEST_PASS = "stdBenefitTestPassRequest";

	public static final String STD_BEN_TEST_FAIL = "stdBenefitTestFailRequest";

	public static final String BENEFIT_COMPONENT_VIEW_ALL_REQUEST = "benefitComponentViewAllRequest";

	public static final String TEST_PASS_ADMINOPTION_REQUEST = "testPassAdminOptionRequest";

	public static final String SCHEDULEFORTEST_ADMINOPTION_REQUEST = "scheduleForTestAdminOptionRequest";

	public static final String PUBLISH_ADMINOPTION_REQUEST = "publishAdminOptionRequest";

	public static final String TEST_PASS_QUESTION_REQUEST = "testPassQuestionRequest";

	public static final String ADMIN_LVL_REQUEST = "adminLevelRequest";

	public static final String TEST_PASS_MANDATE_REQUEST = "testPassMandateRequest";

	public static final String TEST_FAIL_QUESTION_REQUEST = "testFailQuestionRequest";

	public static final String DELETE_BENEFIT_LINE_REQUEST = "deleteBenefitLineRequest";

	public static final String RETRIEVE_CONTRACT_BENEFIT_DEFINITION_REQUEST = "retrieveContractBenefitDefinitionRequest";

	public static final String CONTRACT_UNCODED_NOTES_REQUEST = "contractUncodedNotesRequest";

	public static final String CONTRACT_BENEFIT_COMPONENT = "retrieveContractBenefitComponents";

	public static final String CONTRACT_BENEFIT_RETREIVE = "retreiveBenefitsContractRequest";

	//Notes Request.
	public static final String CREATE_NOTES_REQUEST = "createNotesRequest";

	public static final String RETRIEVE_PRODUCT_NOTES_REQUEST = "retrieveProductNoteRequest";

	public static final String RETRIEVE_NOTES_REQUEST = "retrieveNotesRequest";

	public static final String NOTE_DOMAIN_REQUEST = "noteDomainRequest";

	public static final String CREATE_NOTES_DATA_DOMAIN_REQUEST = "createNotesDataDomainRequest";

	public static final String NOTES_SEARCH_REQUEST = "notesSearchRequest";

	public static final String NOTES_LIFE_CYCLE_REQUEST = "notesLifeCycleRequest";

	public static final String NOTES_CEHCKIN_REQUEST = "notesCheckInRequest";

	public static final String LOCATE_TARGET_SYSTEM_FOR_NOTES = "locateTargetSystemForNotes";

	public static final String NOTES_ALLVERSIONS_REQUEST = "notesAllVersionsRequest";

	public static final String NOTES_CHECKOUT_REQUEST = "notesCheckoutRequest";

	public static final String DELETE_NOTES = "deleteNotesRequest";

	public static final String NOTES_COPY_REQUEST = "notesCopyRequest";

	public static final String LOAD_PRODUCT_COMPONENT_NOTE_REQUEST = "loadProductComponentNoteRequest";

	public static final String LOAD_PRODUCT_BENEFIT_NOTE_REQUEST = "loadProductBenefitNoteRequest";

	public static final String SAVE_PRODUCT_COMPONENT_NOTE_REQUEST = "saveProductComponentNoteRequest";

	public static final String SAVE_PRODUCT_BENEFIT_NOTE_REQUEST = "saveProductBenefitNoteRequest";

	public static final String DELETE_PRODUCT_COMPONENT_NOTE_REQUEST = "deleteProductComponentNoteRequest";

	public static final String DELETE_PRODUCT_BENEFIT_NOTE_REQUEST = "deleteProductBenefitNoteRequest";

	public static final String HIDE_PRODUCT_ADMIN_OPTION_REQUEST = "hideProductAdminOptionRequest";

	//Notes Attachment To Benefit
	public static final String ATTACH_STANDARD_BENEFIT_NOTES_REQUEST = "attachStandardBenefitNotesRequest";

	public static final String LOCATE_STANDARD_BENEFIT_NOTES_REQUEST = "locateStandardBenefitNotesRequest";

	public static final String DELETE_STANDARD_BENEFIT_NOTES_REQUEST = "deleteStandardBenefitNotesRequest";

	public static final String ADMIN_METHOD_CHECKIN_VALIDATION_REQ = "adminMethodValidationRequest";

	// notesAttachmentrequest for benefitline
	public static final String NOTES_ATTACHMENT_REQUEST_FOR_BNFT_LINE = "notesAttachmentRequestForBnftLine";

	public static final String ATTACH_BENEFIT_LINE_NOTES_REQUEST = "attachBenefitLineNotesRequest";

	//product benefit line note override request
	public static final String PRODUCT_BENEFIT_LINE_OVERRIDE_NOTE_REQUEST = "productBenefitLineOverrideNoteRequest";

	//security for administration
	public static final String SAVE_TASK_REQUEST = "saveTaskRequest";

	public static final String RETRIEVE_TASK_REQUEST = "retrieveTaskRequest";

	public static final String SAVE_SUBTASK_ASSOCIATION_REQUEST = "saveSubTaskAssociationRequest";

	public static final String SAVE_TASK_ASSOCIATION_REQUEST = "saveTaskAssociationRequest";

	public static final String DELETE_SUBTASK_ASSOCIATION_REQUEST = "deleteSubTAskAssociationRequest";

	public static final String SAVE_ROLE_REQUEST = "saveRoleRequest";

	public static final String RETRIEVE_ROLE_ASSOCIATION_REQUEST = "retrieveRoleAssociationRequest";

	public static final String SEARCH_ROLE_REQUEST = "searchRoleRequest";

	public static final String SEARCH_MODULE_REQUEST = "searchModuleRequest";

	public static final String MODULE_RETRIEVE_REQUEST = "moduleRetrieveRequest";

	public static final String RETRIEVE_ROLE_REQUEST = "retrieveRoleRequest";

	public static final String APPROVE_ADMIN_OPTION_REQUEST = "approveAdminOptionRequest";

	public static final String UNLOCK_ADMIN_OPTION_REQUEST = "unlockAdminOptionRequest";

	public static final String REJECT_ADMIN_OPTION_REQUEST = "rejectAdminOptionRequest";

	public static final String APPROVE_STD_BENEFIT_REQUEST = "stdBenefitApproveRequest";

	public static final String REJECT_STD_BENEFIT_REQUEST = "stdBenefitRejectRequest";

	public static final String APPROVE_BENEFIT_COMPONENT_REQUEST = "benefitComponentApproveRequest";

	public static final String REJECT_BENEFIT_COMPONENT_REQUEST = "benefitComponentRejectRequest";

	//  Migration Wizard starts here
	public static final String MIGRATION_CONTRACT_RULES_REQ = "migrationContractRulesRequest";

	public static final String RETRIEVE_MIG_VARIABLE_REQUEST = "retrieveMigVariableMappingRequest";

	public static final String RETRIEVE_LEGACY_LOOKUP_REQUEST = "retrieveLegacyLookUpRequest";

	public static final String SAVE_MIG_VARIABLE_REQUEST = "saveMigVariableMappingRequest";

	public static final String SAVE_MIGRATION_NOTES_REQUEST = "saveMigrationNotesRequest";

	public static final String RETRIEVE_BENEFIT_DETAILS_REQUEST = "retrieveBenefitDetailsRequest";

	public static final String RETRIEVE_ROLE_MODULE_REQUEST = "retrieveRoleModuleAssociationRequest";
    public static final String RETRIEVE_PRODUCT_BENEFIT_TIER = "retrieveProductTierDefnAssnRequest";
    
    public static final String RETRIEVE_PRODBENEFIT_TIER = "retrieveBenefitTierDefnRequest";
    
    

	public static final String ROLE_MODULE_LOOKUP_REQUEST = "roleModuleLookUpRequest";

	public static final String SAVE_ROLE_MODULE_ASSOCIATION_REQUEST = "saveRoleModuleAssociationRequest";

	public static final String RETRIEVE_BENEFIT_LINES_REQUEST = "retrieveBenefitLinesRequest";

	public static final String LEGACY_HEADING_REQUEST = "retrieveHeadingsRequest";

	public static final String MIGRATION_PRODUCT_INFO_REQUEST = "migrationProductInfoRequest";

	public static final String MIGRATION_PRICING_INFO_REQUEST = "migrationPricingInfoRequest";

	public static final String RETREIVE_MIG_GENINFO_REQUEST = "retrieveMigGenInfoRequest";

	public static final String SAVE_MIG_GENINFO_REQUEST = "saveMigGenInfoRequest";

	public static final String CONFIRM_MIG_CONTRACT_REQUEST = "confirmMigrationContractRequest";

	public static final String CANCEL_MIG_REQUEST = "cancelMigRequest";

	public static final String MIGRATION_GENERATE_REPORT_REQUEST = "migrationGenerateReportRequest";

	public static final String ADMIN_METHOD_POPUP_REQUEST = "retrieveAdminMethodsRequest";

	public static final String ADMIN_METHOD_REQUEST = "adminMethodsRequest";

	public static final String SAVE_ADMIN_METHOD_REQUEST = "saveAdminMethodsRequest";

	public static final String OVERRIDE_ADMIN_METHOD_REQUEST = "overrideAdminMethodsRequest";

	public static final String ADMIN_METHOD_OVERRIDE_REQUEST = "adminMethodsOverrideRequest";

	public static final String PRODUCT_STRUCTURE_NOTES_REQUEST = "productStructureNotesRequest";

	public static final String QUESTION_DELETE = "questionDeleteRequest";

	public static final String HIDE_ADMIN_OPTION_REQUEST = "hideAdminOptionRequest";

	public static final String SAVE_ADMIN_OPTION = "saveAdminOptionHideRequest";

	public static final String SAVE_ADMIN_OPTION_FOR_PS = "saveAdminOptionRequestForPS";

	public static final String BC_UNLOCK = "benefitComponentUnlock";

	public static final String RETRIEVE_LEGACY_CONTRACT_NOTES_REQUEST = "retrieveLegacyContractNotesRequest";

	public static final String SEARCH_SPS_MAPPING_REQUEST = "searchSPSMappingRequest";

	public static final String EDIT_IND_MAPPING_REQUEST = "editIndicativeMappingRequest";

	public static final String CREATE_IND_MAPPING_REQUEST = "createIndicativeMappingRequest";

	public static final String SEARCH_IND_MAPPING_REQUEST = "searchIndicativeMappingRequest";

	public static final String DELETE_IND_MAPPING_REQUEST = "deleteIndicativeMappingRequest";

	public static final String RETRIEVE_IND_MAPPING_REQUEST = "retrieveIndicativeMappingRequest";

	public static final String SAVE_SERVICE_TYPE_MAPPING_REQUEST = "saveServiceTypeMappingRequest";

	public static final String DELETE_SERVICE_TYPE_MAPPING_REQUEST = "deleteServiceTypeMappingCodeRequest";

	public static final String RETRIEVE_SERVICE_TYPE_MAPPING_REQUEST = "retrieveServiceTypeMappingRequest";

	public static final String DELETE_SPS__MAPPING_REQUEST = "deleteSpsMappingRequest";

	public static final String SEARCH_CUSTOM_MESSAGE_REQUEST = "searchCustomMessageRequest";

	public static final String DELETE_CUSTOM_MESSAGE_REQUEST = "deleteCustomMessageRequest";

	//Benefit Questionnaire Requests
	public static final String RETRIVE_QUESTIONAIRE_REQUEST = "retrieveQuestionaireRequest";

	public static final String UPDATE_QUESTIONAIRE_REQUEST = "updateQuestionnaireRequest";

	public static final String RETRIEVE_BENEFIT_COMPONENT_QUESTIONNARE_REQUEST = "retrieveBenefitComponentQuestionnairRequest";

	public static final String RETRIEVE_PRODUCT_STRUCTURE_QUESTIONNARE_REQUEST = "retrieveProductStructureQuestionnaireRequest";

	public static final String UPDATE_PRODUCT_STRUCTURE_BENEFIT_ADMINISTRATION = "updateProductStructureBenefitAdministrationRequest";

	public static final String ADD_ROOT_QUESTION_REQUEST = "addRootQuestionRequest";

	public static final String LOCATE_ROOT_QUESTION_REQUEST = "locateRootQuestionRequest";

	public static final String RETREIVE_SPS_REF_REQUEST = "retreiveSPSReferenceRequest";

	public static final String EDIT_ROOT_QUESTION_REQUEST = "editRootQuestionRequest";

	public static final String RETRIEVE_PRODUCT_QUESTIONNARE_REQUEST = "retrieveProductQuestionareRequest";

	public static final String UPDATE_PRODUCT_QUESTIONAIRE_REQUEST = "updateProductQuestionareRequest";

	public static final String RETRIEVE_ROOT_QUESTIONNAIRE_REQUEST = "retrieveRootQuestionnaireRequest";

	public static final String RETRIEVE_CHILD_QUESTIONNAIRE_REQUEST = "retrieveChildQuestionnaireRequest";

	public static final String PERSIST_CHILD_QUESTIONNAIRE_REQUEST = "persistChildQuestionnaireRequest";

	public static final String POPUP_REQUEST = "popupRequest";

	public static final String SAVE_ADM_OPT_PR_QUESTIONNAIRE_REQUEST = "updateQuestionnaireParentReqdRequest";

	public static final String PRODUCT_COMPONENT_RULE_REQUEST = "saveProductComponentRuleInformationRequest";

	public static final String QUESTION_NOTES_POPUP_REQUEST = "questionNotesPopUpRequest";

	public static final String BENEFIR_QUESTION_NOTES_PROCESS_REQUEST = "benefitQuestionNoteProcessRequest";

	public static final String PRODUCT_QUESTION_NOTES_POPUP_REQUEST = "productQuestionNotesPopUpRequest";

	public static final String PRODUCT_QUESTION_NOTES_PROCESS_REQUEST = "productQuestionNoteProcessRequest";
	
	public static final String PRODUCT_QUESTION_TIER_NOTES_PROCESS_REQUEST = "productQuestionTierNoteProcessRequest";
	
	public static final String CONTRACT_QUESTION_TIER_NOTES_PROCESS_REQUEST = "contractQuestionTierNoteProcessRequest";
	
	public static final String NOTES_TO_QUESTION_ATTACHMENT_REQUEST_PS = "questionNotesAttachRequestPS";

	public static final String TESTSUITE_CREATE_REQUEST = "testSuiteCreateRequest";

	public static final String TESTSUITE_SEARCH_REQUEST = "testSuiteSearchRequest";

	public static final String TESTSUITE_DELETE_REQUEST = "testSuiteDeleteRequest";

	public static final String TESTSUITE_CHANGE_REQUEST = "testSuiteChangeRequest";

	public static final String TIER_LOOKUP_REQUEST = "tierLookUpRequest";

	public static final String CREATE_BENEFIT_TIERDEFN_ASSN_REQUEST = "createBenefitTierDefnAssnRequest";

	public static final String GET_BENEFIT_TIERDEFN_ASSN_REQUEST = "getBenefitTierDefnAssnRequest";
	
	public static final String GET_BENEFIT_TIERDEFN_ASSN_DETAIL_PRINT_REQUEST = "getBenefitTierDefinitionForDetailPrintRequest";
	
	public static final String TESTCASE_CREATE_REQUEST = "testCaseCreateRequest";

	public static final String TESTCASE_SEARCH_REQUEST = "testCaseSearchRequest";
	
	public static final String TESTCASE_BENEFIT_COMPNT_POPUP_SEARCH_REQUEST = "benefitComponentPopupSearchRequest";

	public static final String TESTCASE_DELETE_REQUEST = "testCaseDeleteRequest";

	public static final String TESTCASE_CHANGE_REQUEST = "testCaseChangeRequest";

	public static final String TESTCASE_REF_CREATE_REQUEST = "testCaseRefCreateRequest";

	public static final String TESTCASE_REF_DELETE_REQUEST = "testCaseRefDeleteRequest";
	
	public static final String DELETE_BENEFIT_TIER_REQUEST = "productTierDeleteRequest";
	
	public static final String DELETE_CONTRACT_TIER_REQUEST = "contractTierDeleteRequest";

	public static final String BENEFIT_TIER_DEFINITION_REQUEST =  "tierDefinitionRequest";
	
	
	public static final String UPDATE_PRODUCT_BNFT_GENINFO_REQUEST = "updateProductBenGenInfoRequest";
	
	/* WTT Test Case and Test Suite starts */
 
    public static final String TESTSUITE_EXECUTE_REQUEST = "testSuiteExecuteRequest";
    
  
    public static final String TESTCASE_EDIT_REQUEST = "testCaseEditRequest";
  
    
    public static final String BENEFITCOMPONENT_POPUP_REQUEST = "benefitcomponentPopupRequest";
    public static final String BENEFIT_POPUP_REQUEST = "benefitPopupRequest";
	public static final String DELETE_LEVEL_FROM_TIER_REQUEST =  "deleteLevelFromTierRequest";
	
	public static final String CHECK_PRODUCT_BENEFIT_TIER = "checkProductTierDefnRequest";
	public static final String PRODUCT_TIER_DEF_SAVE_REQUEST =  "productTierDefSaveRequest";
	
	public static final String CONTRACT_TIER_DEF_SAVE_REQUEST =  "ContractTierDefSaveRequest";
	
	public static final String PRODUCT_QUESTION_TIER_NOTES_POPUP_REQUEST = "productQuestionTierNotesPopUpRequest";
	
	public static final String CONTRACT_QUESTION_TIER_NOTES_POPUP_REQUEST = "contractQuestionTierNotesPopUpRequest";
	
	public static final String RETRIEVE_PRODUCT_RULE_TYPE = "retreiveProductRuleTypeRequest";
	
	public static final String RETRIEVE_CONTRACT_RULE_TYPE = "retreiveContractRuleTypeRequest";
	
	public static final String CONTRACT_REPORT_REQUEST = "contractReportRequest";
	
	// Added for Indicative long term solution
	public static final String IMPORT_INDICATIVE_REQUEST = "importIndicativeRequest";
	public static final String COPY_TO_PRODUCTION_REQUEST = "copyToProductionRequest";
	public static final String CONFIRM_INDICATIVE_REQUEST = "confirmImportIndicativeRequest";
	
	public static final String INDICATIVE_DETAIL_REQUEST = "indicativeDetailRequest";
	
	
	// End Indicative long term solution
	
	/**
	 * Constructor
	 */
	private ServiceManager() {
		super();
	}

	/**
	 * This execute method calls the execute method of BusinessServiceController
	 */
	public static WPDResponse execute(int serviceType, WPDRequest request)
			throws ServiceException {
		switch (serviceType) {
		case 1:
			return new BusinessServiceController().execute(request);
		case 2:
			return new ValidationServiceController().execute(request);
		default:
			throw new IllegalArgumentException("The speficifed service type : "
					+ serviceType + " does not exist.");
		}
	}

	public static WPDRequest getServiceRequest(String serviceName)
			throws ServiceException {
		return new BusinessServiceController().getServiceRequest(serviceName);
	}
}