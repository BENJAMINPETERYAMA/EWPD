/*
 * BusinessConstants.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.util;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface BusinessConstants {

	String UNIVERSAL_DOMAIN = "ALL";

	final String VARIABLE_FORMATE_DOL = "DOL";

	final String ACCUM_MONIES_FLG_Y = "getAllAccumWithMoniesFlgY";

	final String VARIABLE_FORMATE_DAY = "DAY";

	final String VARIABLE_FORMATE_DAYS = "DAYS";

	final String ACCUM_DAYS_FLG_Y = "getAllAccumWithDaysFlgY";

	final String VARIABLE_FORMATE_VST = "VST";

	final String ACCUM_OCCURS_FLG_Y = "getAllAccumWithOccursFlgY";

	final String VARIABLE_FORMATE_OCRS = "OCRS";

	final String GET_ALL_ACCUMULATOR = "getAllAccumulator";

	final String GET_ALL_ACCUMULATOR_VAR_MAPPED = "getAllAccumulatorVarMapped";

	final String GET_ALL_MAPPED_ACCUMS = "getAllMappedAccums";

	public static final int MAPPED_ACCUMULATOR_SEARCH_ACTION = 895;

	String ENTITY_TYPE_PRODUCT = "product";

	public static final String BUSINESS_TRANSACTION_CONTEXT_CREATE = "CREATE";
	
	public static final String DO_EBX_WEBSERVICE_PROCESS_ERROR ="mtm.process.error"; //SSCR - 16332

	String ENTITY_TYPE_PRODUCT_STRUCTURE = "prodStructure";

	final String STD_ACCUMULATOR_NOTHING_TOSAVE = "std.accumulator.nothingto.save";

	String ENTITY_TYPE_CONTRACT = "contract";

	String ENTITY_CONTRACT = "CONTRACT";

	String GENERAL_BENEFITS = "GENERAL BENEFITS";

	final String ACCUM_POPUP_FILTER = "ACCUM_POPUP_FILTER";
	
	final String POPUP_REQUEST = "POPUP_REQUEST";

	String DATESEGMENT_ID = "dateSegmentId";

	String PRIMARY_CODE = "code";

	String DESCRIPTION = "desc";

	final String MEDICAL_DOMAIN = "medical";

	final String ADATPER_EXCEPTION_MSG = "AdapterManager";

	final String ACCUM_DESC = "name";

	final String ACCUM_PVA = "pva";

	final String ACCUM_CST_TYP = "cstTyp";

	final String ACCUM_SVC_CDE = "svcCde";

	final String ACCUM_LIST = "accumCodesList";

	final String SYSTEM = "system";

	public static final String CONTRACT_DUPLICATE_REFERENCE = "contractDuplicateReferenceView";

	public static final String COVERAGE = "Coverage";

	public static final String NETWORK = "Network";

	public static final String PRODUCT_CODES = "Product Codes";

	public static final String STANDARD_PLAN_CODES = "Standard Plan Code";

	public static final String PROVIDER_SPECIALITY_CODE = "REL PROVIDER SPECIALITY CODE";

	public static final String CORPORATE_PLAN_CODES = "Corporate Plan Codes";

	// For Questinnaire
	public static final String ADMIN_OPTION_ID = "adminOptionId";

	public static final String QUESTIONNAIRE_TILDA_STRING = "questionnaireIdTildaString";

	public static final String DELETE_QUESTIONNAIRE = "deleteQuestionnaire";

	public static final String USER_ID = "userId";

	public static final int ACCUMULATOR_CREATE_ACTION = 890;

	final String STD_ACCUMULATOR_SAVE_SUCCESS_INFO = "std.accumulator.save.success.info";

	final String SEARCH_RESULT_NOTFOUND = "mandate.question.searchresult.notfound";

	public static final int ACCUMULATOR_RESPONSE = 891;

	final String MAPPED_RESULT_NOTFOUND = "mapped.searchresult.notfound";

	public static final int ACCUMULATOR_SEARCH_ACTION = 892;

	public static final String UPDATE_QUESTIONNAIRE_DOMAIN = "updateQuestionnaireDomain";

	public static final String QUESTIONNAIRE_HIERARCHY_SYS_ID = "questionnaireHierarchySysId";

	public static final String LOCATE_ROOT_QUESTIONNAIRE_DETAILS = "locateRootQuestionnaireDetails";

	public static final String PARENT_QUESTIONNAIRE_SYS_ID = "parentQuestionnaireSysId";

	public static final String PARENT_REQD = "parentRequired";

	public static final String ON_LOAD = "ON_LOAD";

	public static final String LOCATE_CHILD_QUESTIONNAIRE = "locateChildQuestionnaires";

	public static final String RETRIEVE_NA_VAR_MAPPING_DF = "retriveNotApplicableMappings";

	public static final String RETRIEVE_TEMP_NA_VAR_MAPPING_DF = "retriveTempNotApplicableMappings";

	public static final String ON_CLOSE = "ON_CLOSE";

	public static final String LOCATE_INVALID_CHILD_QUESTIONNAIRES = "locateInvalidChildQuestionnaires";

	public static final String ADMIN_NAME = "adminName";

	public static final String VIEW_ALL_ADMIN_OPTION = "viewAllAdminOption";

	// for Admin Methods
	public static final String ADMINMETHODID = "adminId";

	public static final String ADMINSYSID = "adminSysId";

	public static final String BENECOMPID = "benComId";

	public static final String ENTYID = "entityId";

	/*-------------------- constants for rules----------------------*/
	String RULE_TYPE_DENIAL_KEY = "BNFTDENY";

	String RULE_TYPE_EXCLUSION_KEY = "ADJUD";

	String RULE_TYPE_UM_KEY = "UMRULE";

	String RULE_TYPE_PNR_KEY = "UMRULE";

	String RULE_PVA_PARAM_TYPE = "PVA";

	String RULE_PARAM_TYPE = "RULE";

	String RULE_TYPE_DENIAL = "Denial Rule";

	String RULE_TYPE_EXCLUSION = "Exclusion Rule";

	String RULE_TYPE_UM = "UM Rule";

	String RULE_TYPE_PNR = "PNR Rule";

	char SPECIAL_CHAR_RULE_TYPE_DENIAL = '*';

	char SPECIAL_CHAR_RULE_TYPE_EXCLUSION = '&';

	char SPECIAL_CHAR_RULE_TYPE_UM = '#';

	char SPECIAL_CHAR_RULE_TYPE_PNR = '$';

	String MSG_PRODUCT_RULE_SAVE_SUCCESS = "product.rule.save.success";

	String MSG_PRODUCT_RULE_SAVE_DUPLICATE = "product.rule.save.duplicate";

	String MSG_PRODUCT_RULE_UPDATE_SUCCESS = "product.rule.updated.success";

	String MSG_PRODUCT_RULE_DELETE_SUCCESS = "product.rule.delete.success";

	String MSG_PRODUCT_RULES_DELETE_SUCCESS = "product.rules.delete.success";

	String MSG_PRODUCT_RULE_DELETE_SUCCESS2 = "product.rule.delete.success2";

	String MSG_PRODUCT_RULE_INVALID = "product.rule.invalid";

	String MSG_CONTRACT_RULE_INVALID = "contract.rule.invalid";

	String MSG_LOCKED_USER = "locked.by.another.user";

	String RESERVED_CONTRACT_LOCATE = "com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord";

	/*--------------------Admin Messages----------------------*/
	String MSG_PRDCT_ADMIN_ATTACHED = "product.admin.attached";

	String MSG_POPUP_ADMIN_NOT_FOUND = "product.admin.notfound";

	String MSG_PRDCT_ADMIN_SAVED = "product.admin.saved";

	String MSG_PRDCT_ADMIN_UPDATED = "product.admin.updated";

	/* ---------- Contract Messages ----------------------*/
	String FAILED_TO_PROCESS = "Failed while processing ContractTransferLogrequest";

	String STATUS_MIGRATION_IN_PROGRESS = "Migration in Progress";//"MIGRATION_IN_PROGRESS";

	String STATUS_MIGRATED = "Migrated";//"MIGRATED";

	String STATUS_RENEWED_IN_ETAB = "Renewed in Etab";//"RENEWED_IN_ETAB";

	String STATUS_MARKED_FOR_DELETION = "Marked For Deletion";

	String STATUS_SCHEDULE_TO_TEST = "Schedule to Test";

	String STATUS_TRANSFERRED_TO_PRODUCTION = "Transferred to Production";

	String STATUS_BEING_MODIFIED = "Being Modified";

	String PLACE_OF_SERVICE = "POS";

	String MSG_CONTRACT_PRODUCT_VALIDATION = "contract.product.validation";

	String MSG_CONTRACT_BENEFITS_ALLHIDE = "contract.benefits.allhide";

	String MSG_CONTRACT_BENEFITS_HIDEUNHIDE = "contract.benefits.hideunhide";

	String MSG_CONTRACT_BENEFITCOMPONENT_HIDEUNHIDE = "contract.benefitcomponent.hideunhide";

	String MSG_CONTRACT_CREATE_SUCCESS = "contract.create.successful";

	String NOTE_INSERT_SUCCESS = "note.attach.success";

	String NOTE_DELETE_SUCCESS = "standardBenefit.notes.delete.success";

	String NOTE_UPDATE_SUCCESS = "mandate.notes.update.success";

	String MSG_CONTRACT_DELETE_SUCCESS = "contract.create.delete";

	String MSG_SERVICETYPE_DELETE_SUCCESS = "serviceType.create.delete";

	String MSG_CONTRACT_BASICINFO_SAVE_SUCCESS = "contract.basicinfo.save.success";

	String MSG_CONTRACT_SYSTEM_POOL_EXPIRED = "contract.system.pool.expired";

	String MSG_CONTRACT_SPECIFICINFO_SAVE_SUCCESS = "contract.specificinfo.save.success";

	String MSG_CONTRACT_ADAPTEDINFO_SAVE_SUCCESS = "contract.adaptedinfo.save.success";

	String MSG_CONTRACT_PRICINGINFO_SAVE_SUCCESS = "contract.pricinginfo.save.success";

	String MSG_CONTRACT_PRICINGINFO_SAVE_DUPLICATE = "contract.pricinginfo.save.duplicate";

	String MSG_CONTRACT_PRICINGINFO_DELETE_SUCCESS = "contract.pricinginfo.delete.success";

	String CONTRACT_SEARCH_RESULT_NOT_FOUND = "contract.searchresult.not.found";

	String MSG_CONTRACT_COMMENT_SAVE_SUCCESS = "contract.comment.save.success";

	String MSG_CONTRACT_NOTES_ATTACH_SUCCESS = "contract.notes.attach.success";

	String MSG_CONTRACT_CHECKIN_SUCCESS = "contract.checkin.successful";

	String MSG_UNCODED_NOTE_DELETED = "uncoded.notes.deleted";

	String MSG_SEND_TO_TEST_SUCCESS = "contract.sentto.test";

	String MSG_CONTRACT_TEST_PASSED = "contract.test.passed";

	String MSG_CONTRACT_TEST_FAILED = "contract.test.failed";

	String CONTRACT_UNLOCKED = "contract.unlocked";

	String CONTRACT_LOCKED = "contract.locked";

	String MSG_SCHEDULED_TO_APPROVE = "contract.scheduledto.approve";

	String MSG_APPROVED_CONTRACT = "contract.approved.successful";

	String MSG_REJECTED_CONTRACT = "contract.rejected.successful";

	String MSG_CONTRACT_CHECKOUT_SUCCESS = "contract.checkout.successful";

	String MSG_PUBLISHED_CONTRACT = "contract.publish.successful";

	String MSG_SCHEDULED_FOR_PRODUCTION = "contract.scheduledfor.production";

	String CHECKIN_VALID_FAIL_PRICING = "contract.checkin.invalid.pricing";

	String CHECKIN_VALID_FAIL_DATE = "contract.checkin.invalid.date";

	String CHECKIN_VALID = "contract.checkin.valid";

	String RESERVE_ID_IN_USE = "reserve.id.notavailable";

	String CONSECUTIVE_IDS_NOT_AVAILABLE = "consecutive.ids.not.available.for.reserving";

	String EFFECTIVE_DATE_LESS = "effective.date.less";

	String EFFECTIVE_DATE_GREATER = "effective.date.greater";

	String MSG_CONTRACT_NOTES_DELETE_SUCCESS = "contract.notes.delete.success";

	String MSG_CONTRACT_SPECIFICINFO_UPDATE_SUCCESS = "contract.specificinfo.update.success";

	String MSG_CONTRACT_COPY_SUCCESS = "contract.copy.successful";

	String TEST_DATE_ADDED = "test.date.added";

	String DOMAIN_MISMATCH = "domain.basecontract.mismatch";

	String MSG_CONTRACT_COPY_SOURCE_SUCCESS = "contract.copy.source.successful";

	String MSG_CONTRACT_DOMAIN_INVALID = "contract.domain.invalid";

	String MSG_RLUE_UPDATE = "contract.benefitrule.updated";

	String MSG_CONTRACTID_EXISTS = "contract.id.exists";

	String MSG_CNTRCT_EFCTV_PERIOD_INVALID = "contract.prod.effective.date.invalid";

	String COPY_FROM_OTHER_TO_MANDATE = "copy.from.other.contracttype";

	String COPY_FROM_MANDATE_TO_OTHER = "copy.from.mandate.contracttype";

	String MSG_CONTRACT_COPY_DOMAIN_INVALID = "contract.domain.copy.invalid";

	String MSG_PROD_EXPIRED = "contract.product.expired";

	String MSG_NOTE_EXPIRED = "contract.note.expired";

	String MSG_NOTE_EXPIRED_DATE = "contract.note.expired.dateSegment";

	String MSG_CONTRACT_COPY_HEADINGS = "contract.copy.headings";

	String DATAFEED_RUNNING = "datafeed.running";

	String NO_REGULAR_DATESEGMENTS = "no.regular.dateSegments";

	String MSG_CONTRACT_COPY_PRODUCT_INVALID = "contract.product.copy.invalid";

	String SINGLE_REGULAR_DELETE = "single.regular.delete";

	String PARTIAL_DATESEGMENT_EXIST = "partial.datesegment.exist";

	String MSG_NO_RULES_PRODUCT = "no.rules.product";

	String MSG_ATTACH_RULES_CONTRACT = "attach.rules.contract";

	String MSG_DELETE_RULES_CONTRACT = "delete.rules.contract";

	String MSG_UPDATE_RULES_CONTRACT = "update.rules.contract";

	String MSG_SECURITY_INVALID = "security.invalid";

	String MSG_UPDATE_RESERVED = "update.reserved";

	String MSG_DATESEG_NOT_CREATED = "datesegment.not.created";

	String MSG_DATESEG_CREATED = "datesegment.created";

	String MSG_MANDATE_CONTRACT_TYPE = "mandate";

	String MSG_REPLACE_NOTE = "replaceNote";

	String MSG_REPLACE_PRODUCT = "replaceProduct";

	String MSG_CUSTOM_CONTRACT_TYPE = "CUSTOM";

	String MSG_SHELL_CONTRACT_TYPE = "SHELL";

	String MSG_SCHEDULED_FOR_TEST = "SCHEDULED_FOR_TEST";

	String MSG_TEST_SUCCESSFUL = "TEST_SUCCESSFUL";

	String MSG_TEST_FAILED = "TEST_FAILED";

	String MSG_MARKED_FOR_DELETION = "MARKED_FOR_DELETION";

	String MSG_PUBLISHED = "PUBLISHED";

	String MSG_CHECKED_IN = "CHECKED_IN";

	String MSG_APPROVED = "APPROVED";

	String MSG_REJECTED = "REJECTED";

	String MSG_CREATE_RESERVED = "create.reserved";

	String NO_LIST_TO_RESERVE = "no.list.to.reserve";

	String RETREIVE_SPECIFIC = "retrieveContractSpecificInfo";

	public static final String ATTACH_CONTRACT = "ATTACHCONTRACT";

	String PRODUCT_FAMILY_HMO = "HMO";

	String PRODUCT_FAMILY_POS = "POS";

	String PRODUCT_FAMILY_PPO = "PPO"; //cars

	String APPLICABLE_TO_BX = "Y";

	String NOT_APPLICABLE_TO_BX = "N";

	/*-------------Item Messages-------------------------*/
	String MSG_ITEM_SAVE_SUCCESS = "item.save.success";

	String MSG_ITEM_UPDATE_SUCCESS = "item.update.success";

	/* ---------- Product Messages ----------------------*/
	String MSG_PRDCT_CHECKIN_VALID = "product.checkin.valid";

	String MSG_PRDCT_CHECKIN_INVALID = "product.checkin.invalid";

	String MSG_PRDCT_CHECKIN_SUCCESS = "product.checkin.successful";

	String MSG_PRDCT_DELETE_SUCCESS = "product.delete.successful";

	String MSG_PRDCT_CREATE_SUCCESS = "product.create.successful";

	String MSG_PRDCT_UPDATE_SUCCESS = "product.update.successful";

	String MSG_PRDCT_DUPLICATE = "product.name.duplicate";

	String MSG_PRDCT_DOMAIN_INVALID = "product.prodStr.domain.invalid";

	String MSG_PRDCT_EFCTV_PERIOD_INVALID = "product.prodStr.effective.date.invalid";

	String MSG_PRDCT_COMPONENT_DELETED = "product.benefitComponent.delete.suceessfully";

	String MSG_PRDCT_PROD_STR_MODIFIED = "product.str.modified";

	String MSG_PRDCT_COPY_SUCCESS = "product.copy.successful";

	String MSG_PRDCT_TEST_SUCCESS = "product.test.successful";

	String MSG_PRDCT_TEST_FAILURE = "product.test.failed";

	String MSG_PRDCT_TEST_PASSED = "product.test.passed";

	String MSG_PRDCT_PUBLISH_SUCCESS = "product.publish.successful";

	String MSG_PRDCT_SCHEDULE_FOR_APPROVAL_SUCCESS = "product.schedule_for_approval.successful";

	String MSG_APPROVE_SUCCESS = "product.approve.successful";

	String MSG_REJECT_SUCCESS = "product.reject.successful";

	String MSG_PRDCT_BNFT_CMPNT_DUPLICATE = "product.benefitcomponent.duplicate";

	String MSG_PRDCT_SCHEDULE_TEST_FAILURE = "product.schedule_test.failure";

	String MSG_CHECKOUT_SUCCESS = "product.checkout.successful";

	String MSG_CMPNTS_NOT_COPIED = "product.str.change.cmpnts.not.copied";

	String MSG_PRDCT_ADMIN_UPDATE_SUCCESS = "benefit.admin.updated";

	String MSG_POPUP_CMPNTS_NOT_FOUND = "prod.str.comp.not.found";

	String MSG_PRDCT_CMPNT_SAVED = "product.component.saved";

	String MSG_PRDCT_STR_MKD_FOR_DLTD = "product.structure.marked.deleted";

	String MSG_PRDCT_CMNT_UPDATED = "product.component.updated";

	String MSG_PRDCT_BEN_DFN_NOT_FOUND = "no.benefit.definitions";

	String MSG_PRDCT_NO_SEARCH_RESULT = "search.result.not.found";

	String MSG_PRDCT_SEARCH_RESULT_EXCEEDS = "search.result.exceeds";

	String MSG_PRDCT_BEN_DEFN_UPDATED = "benefit.value.updated";

	String MSG_PRDCT_ADMINOPTION_DELETE_SUCCESS = "product.adminoption.delete.successful";

	String BENEFIT_SYS_ID = null;

	/* ----------- End of Product Messages --------------*/

	/* ---------- Catalog Messages ----------------------*/
	String MSG_CATALOG_SAVE_SUCCESS = "catalog.save.success";

	String MSG_CATALOG_UPDATE_SUCCESS = "catalog.update.success";

	String MSG_CATALOG_DESC_UPDATE_SUCCESS = "catalog.desc.update.success";

	String MSG_CATALOG_DELETE_SUCCESS = "catalog.delete.success";

	String MSG_CATALOG_ITEM_ASSOCIATED = "catalog.item.associated";

	String MSG_CATALOG_DATATYPE_UPDATE = "catalog.datatype.update";

	String MSG_CATALOG_SIZE_UPDATE = "catalog.size.update";

	/* ----------- End of Catalog Messages --------------*/
	String MSG_SUB_CATALOG_SAVE_SUCCESS = "sub.catalog.save.success";

	String MSG_SUB_CATALOG_DELETE_SUCCESS = "sub.catalog.delete.success";

	String MSG_SUB_CATALOG_UPDATE_SUCCESS = "sub.catalog.update.success";

	/* ---------- Sub-Catalog Messages ----------------------*/

	/* ----------  End of Sub-Catalog Messages ----------------------*/

	/* ---------- Product Structure Messages ----------------------*/
	String REQUIRED_FIELDS = "requiredField";

	String BENEFIT_VALUE_UPDATED = "benefit.value.updated";

	String PRODUCT_STRUCTURE_SAVED = "product.structure.save.success";

	String PRODSTRUCTURE = "ProdStructure";

	String STDBENEFIT = "stdbenefit";

	String BENEFIT_KEY = "benefitKey";

	String PRODUCT_STRUCTURE_DUPLICATE = "product.structure.duplicate";

	String PRODUCT_STRUCTURE_DUPLICATES = "product.structure.duplicates";

	String PRODUCT_STRUCTURE_DOMAIN_INVALID = "product.structure.domain.invalid";

	String DATE_RANGE_INVALID = "product.structure.daterang.invalid";

	String BENEFIT_COMPONENT_EMPTY = "product.structure.benefitcmpnt.empty";

	String PRODUCT_STRUCTURE_HIERARCHY_PRESENT = "product.structure.heirarchy.present";

	String MANDATORY_COMPONENT_NOT_PRESENT = "product.structure.component.not.present";

	String MANDATORY_GEN_PROVISION_NOT_PRESENT = "benefit.component.gen.provision.not.present";

	String COMPONENT_EMPTY_FOR_TEST = "product.structure.benefitcmpnt.empty.for.test";

	String PRODUCT_STRUCTURE_ASSOCIATED = "product.structure.associated";

	String PRODUCT_STRUCTURE_CANBE_CHECKED_IN = "product.structure.checkin.validation";

	String PRODUCT_STRUCTURE_UPDATED = "product.structure.update.success";

	String PRODUCT_STRUCTURE_CHECKED_IN = "product.structure.checkin.success";

	String PRODUCT_STRUCTURE_COPIED = "product.structure.copy.success";

	String PRODUCT_STRUCTURE_COPIED_REMOVED_COMPONENT = "product.structure.copy.remove.benefit.component";

	String ADMIN_OPTION_UPDATE = "adminoption.update.success.info";

	String ADMIN_OPTIONS_UPDATE = "adminoptions.save.success.info";

	String PRODUCT_STRUCTURE_DELETE = "product.structure.delete";

	String NO_MORE_RESULT = "no.more.result.to.display";

	String BENEFIT_COMPONENT_DELETED = "produsctStructure.benftComponent.delete.suceessfully";

	String BENEFIT_COMPONENT_ADDED = "product.structure.benefitcmpnt.add.success";

	String BENEFIT_COMPONENT_UPDATED = "product.structure.benefitcmpnt.update.success";

	String PRODUCT_STRUCTURE_SCHEDULED_TO_TEST = "product.structure.schedule.for.test.success";

	String PRODUCT_STRUCTURE_PASSED_TEST = "product.structure.test.pass.success";

	String PRODUCT_STRUCTURE_FAILED_TEST = "product.structure.test.fail.success";

	String PRODUCT_STRUCTURE_PUBLISHED = "product.structure.publish.success";

	String PRODUCT_STRUCTURE_UNLOCKED = "product.structure.unlock.success";

	String SEARCH_RESULT_EXCEEDS = "search.result.exceeds";

	String SEARCH_RESULT_NOT_FOUND = "search.result.not.found";

	String RECORDS_NOT_FOUND = "records.not.found";

	String CHECK_OUT_SUCCESS = "check.out.success";

	String ASSO_COMP_DELETED = "component.deleted";

	String ASSO_COMP_DELETED_DOMAIN = "invalid.domain.component.deleted";

	String ASSO_COMP_DELETED_DATE = "invalid.date.component.deleted";

	String BENEFIT_SAVE = "benefit.saved";

	String PRODUCT_BENEFIT_HIDE_STATUS = "product.associated.benefit.save.success.info";

	String PRODUCT_STRUCTURE_BENEFIT_HIDE_STATUS = "product.structure.associated.benefit.save.success.info";

	String NO_BENEFIT_VISIBLE = "no.benefit.visible";

	String PRODSTRUCTURE_ENTITY = "PRODUCTSTRUCT";

	String STANDARD_BENEFIT_ID = "standardBenefitId";

	String BNFT_COMP_ID = "bnftCmpntId";

	String GET_BENEFIT_DATE = "getBenefitDate";

	String SELECTED_ANSWER_ID = "selectedAnswerid";

	String QUESTIONNAIRE_ID = "questionnaireId";

	String ADMN_LVL_OPTION_SYS_ID = "adminLevelOptionSysId";

	String HIERARCHY_FLAG = "compHierarchyFlag";

	/* ----------- End of Product Structure Messages --------------*/

	/*------------Standard Benefit Messages------------------------------------*/
	String MSG_BENEFIT_DEFN_UPDATE_SUCCESS = "benefit.definition.update.success";

	String MSG_BENEFIT_DEFN_SAVE_SUCCESS = "benefit.definition.save.success";

	String MSG_BENEFIT_DEFN_DELETE_SUCCESS = "benefit.definition.delete.success";

	String MSG_BENEFIT_MANDATE_UPDATE_SUCCESS = "benefit.mandate.update.success";

	String MSG_MANDATE_INFO_UPDATE_SUCCESS = "mandate.info.update.success";

	String MSG_BENEFIT_MANDATE_SAVE_SUCCESS = "benefit.mandate.save.success";

	String MSG_MANDATE_INFO_SAVE_SUCCESS = "mandate.info.save.success";

	String MSG_BENEFIT_MANDATE_DELETE_SUCCESS = "benefit.mandate.delete.success";

	String MSG_BNFT_SAVE_SUCCESS = "benefit.save.success";

	String MSG_BNFT_UPDATE_SUCCESS = "benefit.update.success";

	String MSG_BNFT_DELETE_SUCCESS = "benefit.delete.success";

	String MSG_BNFT_DELETE_FAILURE = "benefit.delete.failure";

	String MSG_ERROR = "Failed while processing StandardBenefitCreateRequest";

	String MSG_ERROR_UPDATE = "Failed while processing StandardBenefitUpdateRequest";

	String MSG_SEARCH_RESULT_EXCEEDS = "search.result.exceeds";

	String MSG_ADMN_METHOD_MAPPED = "admin.method.is.mapped";

	String MSG_ADMN_METHOD_CODED = "admin.method.is.coded";

	String MSG_ADMN_METHOD_DELETED = "admin.method.deleted.sucessfully";

	String MSG_FOR_SEARCH_RESULT_EXCEEDS = "search.result.exceeds.limit";

	String MSG_BENEFIT_MANDATE_SEARCH_RESULTS = "mandate.benefitlevel.searchresult.notfound";

	String MSG_LOG = "Failed while processing CreateBenefitRequest";

	String MSG_BENEFIT_QUESTION_UPDATE_SUCCESS = "benefit.question.update.success";

	String MSG_BENEFIT_ADMN_UPDATE_SUCCESS = "benefit.administration.update.success";

	String MSG_BENEFIT_ADMN_SAVE_SUCCESS = "benefit.administration.save.success";

	String MSG_BENEFIT_ADMN_OPTION_UPDATE_SUCCESS = "benefit.adminoption.update.success";

	String MSG_BENEFIT_ADMN_OPTION_SAVE_SUCCESS = "benefit.adminoption.save.success";

	String MSG_BENEFIT_LEVEL_MAX_TERM_CHECK = "benefitlevel.max.term.check";

	String MSG_BENEFIT_LEVEL_MAX_QUALIFIER_CHECK = "benefitlevel.max.qualifier.check";

	String MSG_BENEFIT_LEVEL_VALUE_NOT_NUMBER = "benefitlevel.benefit.value.not.number";

	String MSG_BENEFIT_LEVEL_VALUE_MAX_INTEGER = "benefitlevel.benefit.value.max.integer";

	String MSG_BENEFIT_LEVEL_VALUE_NOT_DECIMAL_NO = "benefitlevel.benefit.value.not.decimalnumber";

	String MSG_BENEFIT_LEVEL_VALUE_PRECISION = "benefitlevel.benefit.value.valid.precision";

	String MSG_BENEFIT_LEVEL_VALUE_GREATER_HUNDRED = "benefitlevel.benefit.value.greater.hundred";

	String MSG_QUESTION_SEQUENCE_UPDATE = "question.sequence.update.success.info";

	String MSG_QUESTION_SEQUENCE_SAVE = "question.add.success.info";

	String MSG_QUESTION_SEQUENCE_DELETE = "question.delete.success.info";

	String MSG_BENEFIT_UNLOCKED = "benefit.unlocked";

	String BENEFIT_UNLOCKED = "unlock.benefit";

	String BENEFIT_LOCKED_USER = "benefit.locked.by.another.user";

	String MGS_NO_NOTES_AVAILABLE = "benefit.no.notes.available";

	/*------------Standard Benefit -----------------------------------*/
	String RULE_ID = "RuleId";

	String SUB_OBJECT_NAME = "subObjectName";

	String BenefitDefinitionBOImpl = "BenefitDefinitionBO";

	String BenefitMandateBOImpl = "BenefitMandateBOImpl";

	String STD_BENEFIT = "stdbenefit";

	String RuleBO = "RuleBO";

	String KEY_FOR_RETRIEVE = "keyForRetrieve";

	String BENEFIT_MANDATE_BO = "com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO";

	String CITATION_NUMBER_BO = "com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO";

	String STATE_BO = "com.wellpoint.wpd.common.standardbenefit.bo.StateBO";

	String BENEFIT_MANDATE_LIST_BO = "com.wellpoint.wpd.common.standardbenefit.bo.MandateListBOImpl";

	String getAssociatedBenefitMandate = "getAssociatedBenefitMandate";

	String GET_CITATION_NUMBER = "getCitationNumber";

	String GET_STATE_OBJECT = "getStateObject";

	String TESTUSER = "testUser";

	String EWPD = "ewpd";

	String BENEFIT = "benefit";

	String ADAPTER_EXCEPTION = "Adapter Exception occured";

	String SEARCH_MANDATE_LIST = "searchMandateList";

	String SEARCH_DOMAIN = "medical";

	String BENEFIT_SYSTEM_ID = "benefitSystemId";

	String STANDARD_BENEFIT_BO = "com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO";
	String ADMIN_METHOD_BO = "com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO";
	String ADMIN_METHOD_CONFIG_BO = "com.wellpoint.wpd.common.adminmethodmaintain.bo.ConfigurationBO";
	String ADMIN_METHOD_REQPARAM_BO = "com.wellpoint.wpd.common.adminmethodmaintain.bo.RequiredParamBO";
	String ADMIN_METHOD_REFGRP_BO = "com.wellpoint.wpd.common.adminmethodmaintain.bo.ReferenceGroupBO";
	

	String RULE_BO = "com.wellpoint.wpd.common.standardbenefit.bo.RuleBO";

	String LOCATE_STANDARD_BENEFIT = "locateStandardBenefit";
	String LOCATE_ADMIN_METHOD = "locateAdminMethod";
	String LOCATE_ADMIN_METHOD_UNIQUE = "locateAdminMethodUnique";
	
	String GET_QSTN_ANSWERS = "getQuestionAnswers";
	String VIEW_ADMIN_METHOD = "viewAdminMethod";
	String IS_ADMIN_METHOD_MAPPED = "isAdminMethodMapped";
	String IS_ADMIN_METHOD_CODED = "isAdminMethodCoded";
	String VIEW_CONFIGLIST_ADMIN_METHOD = "viewConfigListAdminMethod";
	String VIEW_REQPARAMGLIST_ADMIN_METHOD = "reqParamListAdminMethod";
	String VIEW_REFGRPLIST_ADMIN_METHOD = "refGroupListAdminMethod";



	String LOCATE_RULEID = "locateRuleId";

	String LOCATE_FILTERED_RULEID = "locateFilteredRuleId";

	String LOCATE_UNFILTERED_RULEID = "locateUnFilteredRuleId";

	String LOCATE_OPEN_QUESTIONS = "locateOpenQuestions";

	String LOCATE_HIDDEN_QUESTIONS = "locateHiddenQuestions";

	String SELECTED_QUESTION_LIST_BO = "com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO";

	String AdminLevelLocateCriteria = "com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdminLevelLocateCriteria";

	String VALIDATE_DATE_RANGE = "validateDateRange";

	String BENEFIT_MANDATE_ID = "benefitMandateId";

	String EFFECTIVE_DATE = "effectiveDate";

	String EXPIRY_DATE = "expiryDate";

	String BNFT_DEFN_DELETE = "deleteBenefitDefinition";

	String BENEFIT_DEFINITION_BO = "com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO";

	String BENEFIT_ADMINISTRATION_BO = "com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO";

	String STANDARD = "Standard";

	String BENEFIT_MASTER_SYSTEM_ID = "benefitMasterSystemId";

	String BENEFIT_DEFN_MASTER_KEY = "benefitDefinitionMasterKey";

	String BNFT_ADMN_KEY = "benefitAdministrationKey";

	String BNFT_DEFN_KEY = "benefitDefinitionKey";

	String getAssociatedBenefitDefinition = "getAssociatedBenefitDefinition";

	String checkBenefitDefinitionDateInBenefitAdministration = "checkBenefitDefinitionDateInBenefitAdministration";

	String checkIfBenefitAdminAvailable = "checkIfBenefitAdminAvailable";

	String getAssociatedAdminLevels = "getAssociatedAdminLevels";

	String ADMIN_LEVEL_SYSTEM_ID = "adminSystemId";

	String benefitDefinitionMasterKey = "benefitDefinitionMasterKey";

	String EFFDATE = "mandateEffDate";

	String EXPDATE = "mandateExpDate";

	String MANDATE_ID = "mandateId";

	//Admin option popup
	String BENEFIT_LEVEL_SYS_ID = "benefitLevelSyatemId";

	String BENEFIT_ADMIN_SYS_ID = "benefitAdminSystemId";

	String ADMIN_OPT_REF_ID = "adminOptRefId";

	String LOOK_UP_ADMIN_OPTION_BO = "com.wellpoint.wpd.common.standardbenefit.bo.LookupAdminOptionBO";

	String getAdminOptionList = "getAdminOptionList";

	String ADMIN_OPTION_SAVE = "adminoption.save.success.info";

	String ADMIN_OPTION_CHECKEDIN = "adminoption.checked.in.success.info";

	String ADMIN_OPTION_CHECKEDIN_VALIDATION = "adminoption.checked.in.validation.success.info";

	String ADMIN_OPTION_CHECKEDIN_FAILURE = "adminoption.checked.in.failure.info";

	String ADMIN_OPTION_CHECKEDOUT = "adminoption.checked.out.success.info";

	String ADMIN_OPTION_CHECKEDOUT_FAILURE = "adminoption.checked.out.failure.info";

	String ADMIN_OPTION_SCHEDULED_FOR_TEST = "schedule.for.test.adminoption.success.info";

	String ADMIN_OPTION_SCHEDULED_FOR_TEST_FAILURE = "schedule.for.test.adminoption.failure.info";

	String ADMIN_OPTION_PUBLISH = "publish.adminoption.success.info";

	String ADMIN_OPTION_PUBLISH_FAILURE = "publish.adminoption.failure.info";

	String ADMIN_OPTION_APPROVE = "approve.adminoption.success.info";

	String ADMIN_OPTION_UNLOCKED = "unlock.adminoption.success.info";

	String ADMIN_OPTION_TEST_PASS = "testPass.adminoption.success.info";

	String ADMIN_OPTION_TEST_PASS_FAILURE = "testPass.adminoption.failure.info";

	String ADMIN_OPTION_REJECT_SUCCESS = "reject.adminoption.success.info";

	String ADMIN_OPTION_TEST_FAIL_SUCCESS = "testFail.adminoption.success.info";

	String ADMIN_OPTION_TEST_FAIL_FAILURE = "testFail.adminoption.failure.info";

	String ADMIN_OPTION_SEARCH_RESULT_NOTFOUND = "adminoption.searchresult.notfound";

	String ADMIN_OPTION_DELETE_SUCCESS = "adminOption.delete.success.info";

	String ADMIN_OPTION_DELETE_ALL_SUCCESS = "adminOption.delete.all.success.info";

	String ADMIN_OPTION_MARK_FOR_DELETE_SUCCESS = "adminOption.mark.for.delete.success.info";

	String ADMIN_OPTION_QN_ALREADY_EXIST = "adminoption.question.already.exist.info";

	String ADMIN_OPTION_QN_CANNOT_BE_DELETED = "adminoption.question.cannot.be.deleted.info";

	String ADMIN_OPTION_CANNOT_BE_DELETED = "adminoption.cannot.be.deleted.info";

	String ADMIN_OPTION_ALREADY_EXIST = "adminoption.already.exist.info";

	String ADMIN_OPTION_QN_NOT_EXIST = "adminoption.question.not.exist.info";

	String ADMIN_OPTION_REFERENCE_NOT_EXIST = "adminoption.reference.not.exist.info";

	String ADMIN_OPTION_REFERENCE_UNIQUE = "adminoption.reference.unique";

	/*----------------Benefit Component ---------------------------------*/
	String BENEFIT_COMPONENT_BO = "com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO";

	String BENEFIT_HIERARCHY_ASSC_BO = "com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO";

	String BENEFIT_HIERARCHY_BO = "com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyBO";

	String TREE_BENEFIT_COMPONENT = "com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitComponent";

	String TREE_BENEFIT_ADMIN = "com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitAdministration";

	String TREE_ADMIN_LEVELS = "com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeAdminLevels";

	String LOCATE_BENEFIT_COMPONENT = "locateBenefitComponent";

	String LOCATE_STATE = "locateStateForBenefit";

	String LOCATE_SEARCH_QUERY = "selectBenefitComponent";

	String LOCATE_BENEFIT_HIERARCHY_SEARCH = "benefitHierarchySearch";

	String LOCATE_BENEFIT_HIERARCHY_SEARCH_FOR_PRINT_AND_VIEW = "benefitHierarchySearchForPrintandView";

	String LOCATE_BENEFIT_HIERARCHY_ID = "benefitHierarchyIdSearch";

	String RETRIEVE_BENEFIT_HIERARCHY_REMOVAL = "retrieveBenefitHierarchiesToBeRemoved";

	String RETRIEVE_DUPLICATE_BENEFIT_HIERARCHY = "retrieveDuplicateBenefitHierarchies";

	String STATUS_BUILDING = "BUILDING";

	String UNKNOWN_REQUEST_EXCEPTION = "Unknown Request Type";

	String MSG_BENEFIT_LEVEL_CREATE = "benefit.level.create.success";

	String MSG_BENEFIT_LEVEL_SAVE = "benefit.level.save.success";

	String MSG_BENEFIT_LEVEL_SPS_SAVE = "benefit.level.sps.save.success";

	String MSG_BENEFIT_LEVEL_DELETE = "benefit.level.delete.success";

	String MSG_BENEFIT_LINE_DELETE = "benefit.line.delete.success";

	String MSG_BENEFITS_ASSOCIATED_DELETE = "benefit.deleted";

	String BENEFIT_LEVEL_ID = "benefitLevelId";

	String BC_LOCKED_USER = "benefitcomponent.locked.by.another.user";

	/*----------------Contract ---------------------------------*/
	String CONTRACT_DATE_SEGMENT_BO = "com.wellpoint.wpd.common.contract.bo.DateSegment";

	String CONTRACT_RULE_BO = "com.wellpoint.wpd.common.contract.bo.ContractRuleBO";

	String CONTRACT_LOCATE_RESULT = "com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult";

	String RULE_LOCATE_RESULT = "com.wellpoint.wpd.business.contract.locateresult.RuleLocateResult";
	
	//sscr 17571
	String CARVEDOUT_LOCATE_RESULT = "com.wellpoint.wpd.business.contract.locateresult.CarvedOutLocateResult";

	String RULE_SEQUENCE_RESULT = "com.wellpoint.wpd.business.contract.locateresult.RuleSequenceResults";

	String MEMBERSHIP_INFO = "com.wellpoint.wpd.common.contract.bo.MembershipInfo";

	String LOCATE_CONTRACT = "locateContract";

	String LOCATE_CONTRACT_UNDOMAINED = "locateContractUndomained";
	
	// SSCR21516 July2014
	String LOCATE_CONTRACT_BASIC = "locateContractBasedOnContractId";

	String LOCATE_RULE = "locateRule";

	String LOCATE_RULE_VALUE = "locateRuleOverValue";

	String LOCATE_MEMBERSHIP_VALUE = "getMembershipData";

	String LOCATE_RULE_ASSOCIATED = "locateRuleAssociated";

	String LOCATE_RULE_SEQUENCES = "locateRuleSequences";

	String LATEST_VERSION_CONTRACT = "retrieveLatestVersion";

	String LATEST_VERSION_CONTRACT_NUMBER = "retrieveLatestVersionNumber";

	String RESERVED_CONTRACT_LOCATE_RESULT = "com.wellpoint.wpd.common.contract.bo.ReserveContractId";

	String LOCATE_SERVICE_TYPE_MAPPING = "locateServiceTypeMapping";

	String RULE_MAPPING = "com.wellpoint.wpd.common.blueexchange.bo.RuleMapping";

	/*-----------------Notes Maintenance----------------------------------------*/
	String MSG_FILTERED_RESULT_EXCEEDS = "filtered.result.exceeds";

	/*-----------------Notes Attachment----------------------------------------*/
	String NOTES_ATTACHMENT = "notesAttachment";

	String PRODUCT_LOWER = "product";

	String COMPONENT = "component";

	String DATA_DOMAINS = "dataDomains";

	String SYSTEM_DOMAIN = "SYSTEM DOMAIN";

	String COMPARE = "compare";

	String COMPARE_BASE_NOTE = "compareBaseNote";

	String RETRIEVE_LATEST_VERSION_COMPARE = "retrieveLatestVersionByNoteNameForCompare";

	String RETRIEVE_LATEST_VERSION_COMPARE_BASE_NOTE = "retrieveLatestVersionByNoteNameForCompareBaseNote";

	String RETRIEVE_LATEST_VERSION = "retrieveLatestVersionByNoteName";

	String RETRIEVE_BY_NUMBER = "retrieveByNumber";

	String RETRIEVE_BY_NOTEID = "retrieveByNoteId";

	String RETRIEVE_BY_NOTE_NAME = "retrieveByNoteName";

	String RETRIEVE_BY_NOTE_EDIT = "retrieveByNoteNameForEdit";

	String COPY_FOR_CHECKOUT = "copyForCheckout";

	String LATEST_VERSION_NOTES_FOR_CONTRACT = "getLatestVersionNotesForContract";

	String RETRIEVE_PRODUCT = "retrieveProductDataDomainByNoteID";

	String RETRIEVE_BENEFIT = "retrieveBenefitDataDomainByNoteID";

	String RETRIEVE_BENEFIT_COMPONENT = "retrieveBenefitCompDataDomainByNoteID";

	String RETRIEVE_TERM = "retrieveTermDataDomainByNoteID";

	String RETRIEVE_QUALIFIER = "retrieveQualifierDataDomainByNoteID";

	String LOCATE_TARGET_SYSTEMS = "locateTargetSystemsForNotes";

	String LOCATE_BENEFITS = "locateBenefits";

	String SEARCH_NOTES = "searchNotes";

	String LOCATE_COMPONENTS = "locateComponents";

	String ENTITY_BO = "com.wellpoint.wpd.common.notes.bo.AttachedNotesBO";

	String ENTITY_OVERRIDE_BO = "com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO";

	String getAssociatedEntityList = "getAssociatedEntityList";

	String getAssociatedEntityListNull = "getAssociatedEntityListNull";

	String getAssociatedEntityListForOverride = "getAssociatedEntityListForOverride";

	String getAssociatedBenefitNoteListForOverride = "overrideBenefitLookUpQuery";

	String DELETE_UNCODED_NOTES = "deleteUnCodedNotes";

	String ENTITY_ID = "entityId";

	String ENTITY_TYPE = "entityType";

	String PRI_ENTITY_ID = "primaryEntityId";

	String PRI_ENTITY_TYPE = "primaryEntityType";

	String SEC_ENTITY_ID = "secondaryEntityId";

	String SEC_ENTITY_TYPE = "secondaryEntityType";

	String BEN_COMP_ID = "benefitCmpntId";

	String BEN_COMPONENT_ID = "benefitComponentId";

	String CONTRACT_SYS_ID = "contractSysId";

	//	 Resuse the same message as for product
	String MSG_BENEFIT_COMPONENT_NOTES_DELETE_SUCCESS = "product.note.deleted";

	String MSG_BENEFIT_COMPONENT_NOTES_MULTI_DELETE_SUCCESS = "bc.notes.deleted";

	String MSG_BENEFIT_COMPONENT_NOTES_DELETE_FAILURE = "product.note.delete.failed";

	String MSG_BENEFIT_COMPONENT_NOTES_ATTACH_SUCCESS = "product.note.attached";

	String MSG_STANDARD_BENEFIT_NOTES_OVERRIDE_DELETE_SUCCESS = "notes.hide";

	String MSG_PRDCT_NOTE_ATTACHED = "product.note.attached";

	String MSG_PRDCT_DELETE = "bc.notes.deleted";

	String MSG_NOTE_HIDE = "notes.hide";

	String MSG_PRDCT_QUESTION_DELETE = "product.question.deleted";

	/*-----------------Notes Attachment To Standard Benefit----------------------------------------*/
	String MSG_STANDARD_BENEFIT_NOTES_SAVE_SUCCESS = "standard.benefit.notes.save.success";

	String MSG_STANDARD_BENEFIT_LOCATE_NOTES_SUCCESS = "standardBenefit.notes.locate.success";

	String MSG_STANDARD_BENEFIT_NOTES_DELETE_SUCCESS = "standardBenefit.notes.delete.success";

	String MSG_PRODUCT_ADMINOPTION_DELETE = "product.adminoption.delete";
	
	
	String MSG_CONTRACT_ADMINOPTION_DELETE = "contract.adminoption.delete";
	

	/*-------------Contract Benefit Line Note Override----------------*/
	String MSG_CNT_BENLINE_UPDATED = "contract.benlevelnote.override.success";

	/*-------Administration-Security------*/
	String MSG_TASK_DELETE_SUCCESS = "task.delete.success";

	String MSG_SUBTASK_DELETE_SUCCESS = "subtask.delete.success";

	String MSG_MODULE_DELETE_SUCCESS = "module.delete.success";

	String MSG_ROLE_DELETE_SUCCESS = "role.delete.success";

	String PRODUCT_STRUCTURE_APPROVED = "product.structure.approve.success";

	String PRODUCT_STRUCTURE_REJECTED = "product.structure.reject.success";

	/*-------Start - Product Structure------*/
	String PRODUCT_STRUCTURE_BEN_COMP_ASSC_BO = "com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent";

	String PRODUCT_STRUCTURE_NAME = "productStructureName";

	String RETRIEVE_BY_PRODUCT_ID = "retrieveByProductId";

	String PRODUCT_STRUCTURE_ID = "productStructureId";

	String RETRIEVE_BENEFIT_COMPONENTS = "retrieveBenefitCmpnts";

	String BENEFIT_COMP_ID = "benefitCmpntId";

	String RETRIEVE_STD_BENEFITS = "retrieveStandardBenefits";

	String LINE_OF_BUSINESS = "lineOfBusiness";

	String BUSINESS_ENTITY = "businessEntity";

	String BUSINESS_GROUP = "businessGroup";

	String ATTACH_BC_TO_PS = "attachBCToPS";

	String LEVEL_ID = "lvlId";

	String RETRIEVE_BYADMIN_FOR_BEN_LEVEL = "retrieveByAdministrationIdForBenefitLevel";

	String RETRIEVE_BY_ADMIN_FOR_BENEFIT = "retrieveByAdministrationIdForBenefit";

	String LOCATE_PROD_STRUCTURE = "locateProductStructure";

	String VIEW_ALL_VERSIONS = "viewAllVersions";

	String VIEW_MANDATES = "viewMandates";

	/*-------End - Product Structure------*/

	/*-------FOR DATAFEED------*/
	int DATAFEED_RETRIEVE_CONTRACT = 1;

	int DATAFEED_RETRIEVE_SPSBENEFITLINES = 15;

	int DATAFEED_RETRIEVE_MANDATE_CONTRACT = 2;

	int DATAFEED_RETRIEVE_PRODUCT = 3;

	int DATAFEED_RETRIEVE_BENEFITCOMPONENT = 4;

	int DATAFEED_RETRIEVE_BENEFIT = 5;

	int DATAFEED_RETRIEVE_ADMIN_OPTIONS = 20;

	int DATAFEED_RETRIEVE_BNFT_ADMINISTRATION = 21;

	int DATAFEED_STATUS_SET = 6;

	int DATAFEED_STATUS_UNSET = 7;

	int DATAFEED_ENTITY_RULE_ASSN = 8;

	int DATAFEED_STATUS_UPDATE_PROD = 10;

	int DATAFEED_STATUS_UPDATE_TEST = 9;

	int DATAFEED_DELETE_TEST_DATA = 11;

	int DATAFEED_RETRIEVE_BENEFIT_LINES = 50;

	int DATAFEED_RETRIEVE_ADMINOPTIONS = 16;

	int DATAFEED_RETRIEVE_ACCUM_QSTN = 234;
	
	int DATAFEED_RETRIEVE_PROV_SPEC_CODE = 345;
	
	int DATAFEED_RETRIEVE_TIER_INFO = 456;
	
	int  RETRIEVE_TIER_CODE_AND_VALUES = 123;
	
	String DATAFEED_USER = "DATAFEED";

	String DATAFEED_TYPE = "EWPD";

	String RETRIEVE_SPS_BENEFITLINES = "retrieveSpsBenefitLines";
	
	String RETRIEVE_TIERED_SPS_BENEFITLINES = "retrieveTieredSpsBenefitLines";

	String RETRIEVE_ADMINOPTIONS = "retrieveAdminOptions";
	
	String RETRIEVE_TIERED_ADMINOPTIONS = "retrieveTieredAdminOptions";

	public static final String UPDATE_CNTRCT_BNFT_QSTNNR = "updateContractBenefitQuestionnaire";

	int RESULT_SET_SIZE = 51;

	int SPS_RESULT_SET_SIZE = 50;

	/*-----------Blue Exchange-------------*/
	int LOCATE_SPS_MAPPING = 1;

	int RETRIEVE_SPS_MAPPING_DF = 2;
	
	int RETRIEVE_NA_SPS_MAPPING_DF = 3;

	int LOCATE_SERVICE_MAPPING = 1;

	int RETRIEVE_SERVICE_MAPPING_DF = 2;
	
	int RETRIEVE_NA_SERVICE_TYPE_MAPPING_DF = 3;

	int LOCATE_CUSTOM_MESSAGES = 1;

	int RETRIEVE_CUSTOM_MESSAGES = 2;
	
int  RETRIEVE_DELETED_CUSTOM_MESSAGES_SEND_TO_TEST  = 3;
	
	int  RETRIEVE_DELETED_CUSTOM_MESSAGES_SEND_TO_PRODUCTION  = 4;

	public static final int RETRIEVE_QUESTIONAIRE = 1;

	public static final int RETRIEVE_CHILD_QUESTIONAIRE = 2;
	
	public static final int RETRIEVE_CNTRCT_VAR_MAPPING_DF = 1;
	
	public static final int RETRIEVE_NA_CNTRCT_VAR_MAPPING_DF = 2;
	
	public static final int	UPDATE_STATUS_CNTRCT_VAR_MAPPING_DF = 3;

	/*-------FOR MIGRATION WIZARD------*/
	public static final int MIGRATION_SAVE_ADD_PRICING_INFO = 1;

	public static final int MIGRATION_DELETE_PRICING_INFO = 2;

	public static final int MIGRATION_RETRIEVE_PRICING_INFO = 3;

	public static final int MIGRATION_DELETE_ALL_PRICING_INFO = 4;

	public static final int MIGRATION_RETRIEVE_PRODUCT_INFO = 1;

	public static final int MIGRATION_RETRIEVE_UNMAPPED_VARIABLES = 1;

	String MIGRATION_DOMAIN_BO = "com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo";

	public static final int MIGRATION_PERSIST_PRODUCT_INFO = 2;

	public static final int REMOVE_PRODUCT_INFO = 33;

	String MIGRATION_SAVE_GENERAL_INFO = "migration.generalinfo.saved";

	String MIG_DOMAIN_RETREIVE = "retrieveDomainInfo";

	String SEARCH_CONTRACT_LOCKED = "search.contract.locked";

	String SEARCH_CONTRACT_DOES_NOT_EXISTS = "search.contract.does.not.exists";

	String NO_VALID_CONTRACTS = "no.valid.contract";

	// String SESSION_MIGRATION_DATE_SEGMENT_INFO_OBJECT = "SESSION_MIGRATION_DATE_SEGMENT_INFO_OBJECT";
	String SESSION_MIGRATION_CONTRACT_SESSION_OBJECT = "SESSION_MIGRATION_CONTRACT_SESSION_OBJECT";

	int DATAFEED_MANDATE_DEFINITION = 12;

	String MIGRATION_COMPLETED_FOR_CONTRACT = "migation.completed.for.contract";

	String SESSION_MIGRATION_PROD_SYS_ID = "ewpdProductSysId";

	String SESSION_MIGRATION_PERSIST_FLAG = "persistFlag";

	String MIGRATION_STARTED_FOR_CONTRACT = "migration.started.for.contract";

	String SESSION_MIGRATION_PARENT_NAME = "SESSION_MIGRATION_PARENT_NAME";

	String MIGRATION_ENDED_FOR_CONTRACT = "migration.ended.for.contract";

	String MIGRATION_CONTRACT_EXISTING = "migration.contract.existing";

	String MIGRATION_CONTRACT_DATESEGMENT_STEP8 = "migration.contract.datesegement.allthroughstep8";

	String MIGRATION_CONTRACT_RULE_INVALID = "migration.rule.invalid";

	String PRODUCT_DOMAIN_MISMATCH = "product.domain.mismatch";

	String CANCEL_MIGRATION_SUCCESS = "cancel.migration.success";

	String PRODUCT_DETACHED_SUCCESSFULLY = "product.detached.successfully";

	String VARIABLES_SAVED_SUCCESS = "variables.saved.success";

	String VARIABLES_DELETED_SUCCESS = "variables.deleted.success";

	String PRICING_INFO_SAVE_SUCCESS = "pricing.info.save.success";

	String PRICING_INFO_DELETE_SUCCESS = "pricing.info.delete.success";

	String PRICING_INFO_DELETE_ALL_SUCCESS = "pricing.info.delete.all.success";

	String STEP_NUMBER_UPDATE_SUCCESS = "step.number.update.success";

	String PRODUCT_INFO_SAVE_SUCCESS = "product.info.save.success";

	public static final int STEP_NUMBER_COMPLETED = 7;

	public static final int UPDATE_STEP_COMPLETED = 5;

	public static final int REPORT_UPDATE_STEP_COMPLETED = 2;

	public static final int RULES_UPDATE_STEP_COMPLETED = 7;

	public static final int PRODUCT_UPDATE_STEP_COMPLETED = 3;

	public static final int STEP8 = 8;

	public static final int STEP7 = 7;

	public static final int STEP5 = 5;

	public static final int STEP4 = 4;

	public static final int STEP2 = 2;

	public static final int STEP3 = 3;

	public static final int INT_1 = 1;

	public static final int INT_2 = 2;

	public static final int INT_3 = 3;

	public static final int INT_4 = 4;

	boolean MIGRATION_NAVIGATION_FLAG_TRUE = true;

	boolean MIGRATION_NAVIGATION_FLAG_FALSE = false;

	String MIGRATION_NOT_POSSIBLE = "migration.not.possible";

	int DATAFEED_RETRIEVE_HEADER_RULES_FOR_BENEFIT = 22;

	String MIGRATION_PRICINGINFO_SAVE_DUPLICATE = "migration.pricinginfo.save.duplicate";

	String MSG_PRDCT_RULE_VALIDATE = "product.rule.inavlid";

	String MSG_ATLEAST_ONE_LEVEL = "atleast.one.level.for.all.benefits";

	String LOCATE_RULE_CHECK = "locateRules";

	String BENEFIT_COMP = "BENEFITCOMP";

	String BENEFIT_TYPE = "BENEFIT";

	String MIGRATION_NOT_POSSIBLE_CONTRACT = "migration.not.possible.for.contract.with.null.values";

	String MIGRATION_ALREADY_EXISTS_IN_EWPD = "migration.already.exists.ewpd";

	String MIGRATION_ALREADY_STARTED_ANOTHER_OPTION = "migration.already.started.another.option";

	String MIGRATION_PARTIALLY_DONE = "migration.partially.done.renew";

	String BENEFIT_STRUCTURE_NOT_ASSOCIATED = "benefit.structure.not.associated";

	String CONTRACT_SCHEDULED_TO_TEST = "contract.scheduled.to.test";

	String CONTRACT_TRANSFERRED_TO_PRODUCTION = "contract.transferred.to.production";

	String CONTRACT_SCHEDULED_TO_PRODUCTION = "contract.scheduled.to.production";

	String OPT_MIGRATE_LATEST_DS = "0";

	String OPT_MIGRATE_ALL_DS = "1";

	String OPT_RENEW_DS = "2";

	String OPT_MIGRATE_DS = "3";

	String NOT_ENOUGH_DATE_SEGMENTS = "not.enough.date.segments";

	String SUBTABLE_YES = "YES";

	String SUBTABLE_NO = "NO";

	String MSG_MIGRATION_VALIDATION_STATUS = "migration.validation.status";

	String MSG_MIGRATION_CONTRACT_DELETED = "migration.contract.deleted";

	String PRODUCT_STRUCTURE_LOCKED = "product.structure.locked";

	String CONTRACT_RULE_VALIDATION = "contractRulesValidation";

	String GET_VALID_STATUS_DTSG = "retrieveValidStatusDatesegments";

	String GET_DTSG_CHECKIN = "retrieveCheckInDateSegments";
	//sscr 17571
	String GET_DT_RANGE = "retrieveDateRange";
	
	String CONTRACT_INVALID_RULES = "contractInvalidRules";
	
	//sscr 17571
	String CARVEDOUT_QUESTIONS = "carvedoutquestions";

	String GET_BENEFITS_ALL = "getAllBenefits";

	String GET_BENEFITS_VISIBLE = "getAllBenefitsVisible";

	String MSG_CONTRACT_RULE_VALIDATE = "contract.rule.invalid";

	String PRODUCT_RULE_VALIDATION_FAILURE = "product.rule.validation.failure";

	String DATAFEED_ACTION = "DATAFEED";

	String MIGR_CRPRT_PLANCODE_NULL = "migration.no.corporate.plan.code";

	String MSG_CONTRACT_VALIDATE_RULE_CHECKIN = "contract.rule.invalidPNR";

	String MSG_PRODUCT_LATEST_VERSION_AVLBL = "product.latest.version.avlbl";

	String ADMIN_OPTION_UPDATE_SUCCESS = "admin.option.update";

	String ADMIN_OPTION_HIDE_SUCCESS = "admin.option.hide.success";

	String PRODUCT_BEN_COMP_ASSC_BO = "com.wellpoint.wpd.common.product.bo.ProductComponentBO";

	String MSG_PRDCT_CHECKIN_INVALID_REFERENCE_NOT_UNIQUE = "product.checkin.invalid.not.unique";

	String MSG_PRDCT_CHECKIN_INVALID_QUESTION_REFERENCE_NOT_UNIQUE = "product.checkin.invalid.questrefnot.unique";

	//	 Start of CR Check In Reference Data Validation  
	String MSG_CONTRACT_CHECKIN_INVALID_REFERENCE_NOT_UNIQUE = "contract.checkin.invalid.not.unique";

	//	 End of CR Check In Reference Data Validation      
	String CHECKOUT_FINAL_MESSAGE_1 = "checkout.final.message.1";

	String CHECKOUT_FINAL_MESSAGE_2 = "checkout.final.message.2";

	String CHECKOUT_FINAL_MESSAGE_3 = "checkout.final.message.3";

	String CHECKOUT_FINAL_MESSAGE_4 = "checkout.final.message.4";

	String CHECKOUT_FINAL_MESSAGE_5 = "checkout.final.message.5";

	String CHECKOUT_FINAL_MESSAGE_6 = "checkout.final.message.6";

	String BENEFIT_COMPONENT_MANDATORY_BENEFIT = "benefitcomponent.mandatory.benefit";

	String HIDE_FLAG_T = "T";

	String HIDE_FLAG_F = "F";

	String PRODUCT_TYPE = "PRODUCT";

	String STANDARD_TYPE = "STANDARD";

	String PRODUCT_FAMILY = "Product Family";

	String STATE_CODE = "State Code";

	String PROVIDER_ARRANGEMENTS = "provider arrangement";

	String ATTACH_PRODUCT = "ATTACHPRODUCT";

	String ADMIN_ID = "adminId";

	String PRODUCT_RULE_TYPE_NOT_FOUND = "product.rule.type.not.found";

	String PRODUCT_RULE_ID_NOT_FOUND = "product.rule.id.not.found";

	String ACTION = "action";

	String RETRIEVE_DENIAL_EXCLUSION = "retrieveDenialAndExclusion";
	
	String POS_PRODUCT_FAMILY="productFamily";

	/*-------------------- constants for Admin Method ----------------------*/

	public static final String VALUE_NEGONE = "-1";
	// Constant for benefit component unhide for CNTRCTADMNMTHDMAINVLDNPROC Procedure
	public static final String BC_UNHIDE = "BCUNHIDE";
	// Constant for benefit unhide for CNTRCTADMNMTHDMAINVLDNPROC Procedure
	public static final String BEN_UNHIDE = "BENUNHIDE";

	public static final String VALUE_TRUE = "true";

	boolean QUESTION_PRODUCT_FLAG = true;


	boolean QUESTION_PRODUCTSTRUCTURE_FLAG = false;

	public static final int VALUE_ZERO = 0;

	public static final String LEVEL_TYPE = "TERM";

	public static final String BENEFIT_VALIDATION_TYPE = "VALIDATE_BENEFIT";

	public static final String ADMIN_OPTION_TYPE = "AMOPT";
	
	//	Constants for Tier
    public static final String ADMIN_PROD_TIER_OPTION_TYPE ="TIERDEL";
    public static final String ADMIN_CONTRACT_TIER_OPTION_TYPE = "TIERDEL";
    

	public static final String QUESTION_CHANGE_TYPE = "QUES";

	public static final String ANSWER_CHANGE_TYPE = "ANSCH";

	public static final String GENERAL_BENEFIT_TYPE = "GBSEL";

	public static final String SYNCHRONOUS_PROCESS = "SYN";

	public static final String ASYNCHRONOUS_PROCESS = "ASYN";

	public static final String BENEFIT_CONSTANT = "BENEFIT";

	public static final String BENEFIT_COMP_CONSTANT = "BENEFITCOMP";

	public static final String PRODUCT_STUCT_CONSTANT = "ProdStructure";

	public static final String STD_BEN_ID = "stdbenId";

	public static final String PRODUCT_CONSTANT = "product";

	public static final String CONTRACT_CONSTANT = "contract";

	public static final String BEN_ADMIN_CONSTANT = "benefitAdministration";

	public static final String BEN_VALIDATION_CONSTANT = "admin.method.filter.failed.benefit";

	public static final String BEN_COMP_NAME = "benefitComponentName";

	public static final String EN_ID_CONSTANT = "entityId";

	public static final String BEN_COMP_CONSTANT = "benefitComponent";

	public static final String GEN_BENEFIT_CONSTANT = "GENERAL BENEFITS";

	public static final String GEN_BEN_VALIDATION_CONSTANT = "all.superprocess.Steps.notselected";

	public static final String CONTRACT_ID_CONSTANT = "contractId";

	public static final String PRODUCT_ID_CONSTANT = "productId";

	public static final String ADMIN_METHOD_VALIDATION_CONSTANT = "admin.method.filter.failed";

	public static final String ADMIN_METHOD_VALIDATION_CONSTANT_CONTRACT = "admin.method.filter.failed.contract";

	public static final String GEN_BEN_ADMIN_METHOD_VALID_CONST = "admin.method.general.filter.failed";

	public static final String GEN_BEN_ADMIN_METHOD_VALID_CONST_CONTRACT = "admin.method.general.filter.failed.contract";

	public static final String GEN_BEN_CONTRACT_VALIDATION_CONSTANT = "all.superprocess.Steps.notselected.contract";

	public static final String BENEFITCOMP = "BENEFITCOMP";

	public static final String ADMINMETHODFORBC = "getAdminMethodsForBenefitComponent";

	public static final String BENID = "benId";

	public static final String BENEID = "benftId";

	public static final String PRODId = "prodId";

	public static final String BENCOMID = "benComId";

	public static final String QUALLIST = "qualList";

	public static final String TERMLIST = "termList";

	public static final String STANDARDBENEFIT = "BENEFIT";

	public static final String ADMINMETHODFORBENEFIT = "getAdminMethodsForBenefit";

	public static final String BENDEFID = "benDefId";

	public static final String PRODUCTSTRUCTURE = "PRODSTRUCTURE";

	public static final String PRODSTRID = "prodStrId";

	public static final String DATESEGID = "dateSegId";

	public static final String ADMINMETHODFORPS = "getAdminMethodsForProductStructure";

	public static final String ADMINEMTHODFORPRODUCT = "getAdminMethodsForProduct";

	public static final String ADMINEMTHODFORCONTRACT = "getAdminMethodsForContract";

	public static final String PRODUCT = "PRODUCT";

	public static final String CONTRACT = "CONTRACT";;

	public static final String SPSID = "spsId";

	public static final String POSSIBLEANSID = "getPossibleAnswerIds";

	public static final String ADMINID = "adminId";

	public static final String ENTITYID = "entityId";

	public static final String GETQUESTIONFORBC = "getQuestionForBC";

	public static final String GETQUESTIONFORB = "getQuestionForB";

	public static final String GETQUESTIONFORPS = "getQuestionForPS";

	public static final String GETQUESTIONFORC = "getQuestionForC";

	public static final String GENERALBENEFITS = "GENERAL BENEFITS";

	public static final String BCNAME = "bcName";

	public static final String GETQUESTIONFORP = "getQuestionForP";

	public static final String GETADMINMETHODFORANS = "getAdminMethodsForAnswer";

	public static final String ANSWERLIST = "answerList";

	public static final String SEARCHADMINMETHODS = "searchAdminMethods";

	public static final String ADMINISTRATIONID = "administrationId";

	public static final String SEARCHSPSNAME = "searchSPSNames";

	public static final String VIEW_SPSMAPPING = "viewSpsMapping";

	public static final String VIEW_SERVICE_TYPE_CODE_MAPPING = "viewServiceTypeCodeMapping";

	public static final String SERVICE_TYPE_CODE_MAPPING_VIEW = "serviceTypeCodeMappingView";

	public static final String CUSTOM_MESSAGE_TEXT_VIEW = "retrieveCustomMesssageText";

	public static final String CUSTOM_MESSAGE_TEXT_SPS_ID = "spsId";

	public static final String CUSTOM_MESSAGE_TEXT_RULE_ID = "headerRuleId";

	public static final String SPS_PARAMETER = "SPS_Paramater";

	public static final String SEARCHSPAANDREFNAME = "searchSPSAndRefNames";

	public static final String ENTITYSYSID = "entitySysId";

	public static final String ENTITYTYPE = "entityType";

	public static final String BNFTADMINID = "bnftAdmnId";

	public static final String BENEFITCOMID = "benefitComponentId";

	public static final String SEARCHSPSNAMEFOROVERRIDE = "searchSPSNamesForOverride";

	public static final String STANDARDBENEFITKEY = "standardBenefitKey";

	public static final String GETBENEFITADMINISTRATION = "getBenefitAdministration";

	public static final String RETRIEVEBYBC = "retrieveByBenefitComponent";

	public static final String SEARCHSPSNAMEFORVALIDATION = "searchSPSNamesForValidation";
	
	public static final String SEARCHSPSNAMEFORCNTRVALIDATION = "searchSPSNamesForContractValidation";

	public static final String GETBENEFITADMINISTRATIONFROMBC = "getBenefitAdministrationFromBC";

	public static final String GETSPSNAME = "getSPSName";

	public static final String GETANSWERLIST = "getAnswerList";

	public static final String GETADMINMETHODS = "getAdminMethods";

	public static final String GETSPSIDS = "getSpsIds";

	public static final String ANSWERIDS = "answerIds";

	public static final String GETANSWEROVERIDEVALUES = "getAnswerOverideValues";

	public static final String DEFID = "defId";

	public static final String GETANSWEROVERIDEVALUESFORBENEFIT = "getAnswerOverideValuesForbenefit";

	public static final String GETTERM = "getTerms";

	public static final String GETTERMFROMBENEFIT = "getTermsFromBenefit";

	public static final String GETTERMFROMBENEFITCOMP = "getTermsFromBenefitComp";

	public static final String GETTERMFROMPRODUCT = "getTermsFromProduct";

	public static final String GETTERMFROMPRODUCTSTRUCT = "getTermsFromProductStructure";

	public static final String GETTERMFROMCONTRACT = "getTermsFromContract";

	public static final String GETQUALIFIERFORBC = "getQualifiersForBC";

	public static final String BENSYSID = "benSysId";

	public static final String GETQUALIFIERFORB = "getQualifiersForB";

	public static final String GETQUALIFIERFORPS = "getQualifiersForPS";

	public static final String GETQUALIFIERFORP = "getQualifiersForP";

	public static final String GETQUALIFIERFORC = "getQualifiersForC";

	public static final String SEARCHBENEFITADMIN = "searchBenefitAdministration";

	public static final String PRODSYSID = "prodSysId";

	public static final String BENCOMPSYSID = "benCompSysId";

	public static final String BENADMNID = "bnftAdminId";
	
	public static final String BENEFITSYSID = "benefitSysId";
	    
	public static final String SEARCHSPSNAMEFORPOSOVERRIDE = "searchSPSNamesForPOSContractOverride";
    
    public static final String GETPRODUCTFAMILYNAME = "getProductFamilyName";
    
    public static final String GETBASEPRODUCTFAMILY = "getBaseProductFamily";
    
    public static final String GETBASEPRODUCTFAMILYFROMLINES = "getBaseProductFamilyFromLines";
    
    public static final String SEARCHSPSNAMEFORBASEPRODUCTFAMILY = "searchSPSNamesForBaseProductFamily";
    /*CARS|AM2|POS|START*/
    public static final String FETCH_PROD_FAMILY_FROM_TIERED_LINES_FOR_CONTRACT = "fetchProductFamilyFromTieredLinesForContract"; 
    public static final String FETCH_SPS_AM_MAPPINGS_FOR_TIERED_OVERRIDEN = "searchSPSNamesForOverrideInTiers";
    public static final String FETCH_SPS_AM_MAPPINGS_FOR_TIERED_OVERRIDEN_WITH_PRODUCT_FAMILY = "fetchSPS_AM_MappingForTieredOverridenWithProductFamily";
    public static final String FETCH_TIERED_AM_PRODUCT = "fetchSPS_TieredAMforProduct";
    
    /*CARS|AM2|POS|END*/
     
    
    
    public static final String SEARCHSPSNAMEFORHMOOVERRIDE = "searchSPSNamesForHMOContractOverride";
    
   
	int BENEFIT_COMPONENT_LOCATE1 = 1;

	int BENEFIT_COMPONENT_LOCATE2 = 2;

	String BENEFIT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BENEFIT = "benefit.Hierarchy.should.have.atleast.one.benefit";

	String BENEFIT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BENEFIT_HRCHY = "benefit.Hierarchy.should.have.atleast.one.benefit.hierarchy";

	String BENEFIT_LINES_VALUES_OVERRIDE_SUCCESS = "benefit.component.benefit.lines.values.override.success";

	String BENEFIT_COMP_BENEFIT_ADMN_QUESTION_HIDE_SUCCESS = "benefit.component.benefit.administration.question.hide.success";

	String BENEFIT_COMP_BENEFIT_ADMN_ANSWER_OVERRIDE_SUCCESS = "benefit.component.benefit.administration.answer.override.success";

	String BENEFIT_COMP_BENEFIT_ADMN_QUESTION_UNHIDE_SUCCESS = "benefit.component.benefit.administration.question.unhide.success";

	String BENEFIT_COMP_SCHEDULE_FOR_TEST_SUCCESS = "benefit.component.schedule.for.test.success.info";

	String BENEFIT_COMP_TESTPASS_SUCCESS = "benefit.component.testPass.success.info";

	String BENEFIT_COMP_TESTFAIL = "benefit.component.testFail.success.info";

	String BENEFIT_COMPONENT_COPY_SUCCESS = "benefit.component.copy.success";

	String BENEFIT_HRCHY_INVALID_BENEFIT_CHANGE = "benefit.hierarchy.invalid.benefit.change";

	String BENEFIT_COMP_APPROVE_SUCCESS = "benefit.component.approve.success.info";

	String BENEFIT_COMP_REJECT_SUCCESS = "benefit.component.reject.success.info";

	String BENEFIT_COMP_ADMN_UPDATE_SUCCESS = "benefitcomp.adminoption.update.successful";

	String MANDATE_QUESTION_SEARCHRESULT_NOT_FOUND = "mandate.question.searchresult.notfound";

	String BENEFIT_COMP_ALREADY_EXISTS = "benefit.component.already.exist.info";

	String BENEFIT_HAVING_SAME_BNFT_COMP_NAME = "benefit.with.same.benefitcomponent.name";

	String BENEFIT_COMP_DUPLICATE = "benefit.component.duplicate.failure";

	String DUPLICATE_NAME_FOR_BENEFIT = "duplicate.name.for.benefit";

	String BNFT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BNFT = "benefit.Hierarchy.should.have.atleast.one.benefit";

	String BNFT_COMP_SHOULD_HAVE_ATLEAST_ONE_BNFT_HRCHY = "benefit.component.should.have.atleast.one.benefit.hierarchy";

	String BENEFIT_COMPONENT_SAVE_SUCCESS = "benefit.component.save.success";

	public static final String ATTACH_COMPONENT = "ATTACHCOMP";

	public static final String ATTACH_BENEFIT = "ATTACHBENEFIT";

	public static final String ATTACH_BENEFITLINE = "ATTACHBNFTLINE";

	String BENEFIT_HIERARCHY_INVALID_BENEFIT = "benefit.hierarchy.invalid.benefit";

	String BENEFIT_HIERARCHY_INVALID_BENEFIT_CHANGE = "benefit.hierarchy.invalid.benefit.change";

	String BENEFIT_COMPONENT_UPDATED_SUCCESS = "benefit.component.update.success";

	String BENEFIT_COMPONENT = "BENEFITCOMP";

	String BENFIT_COMPONENT_VALIDATION_SUCCESS = "benefit.component.validation.success";

	String BEN_COMPONENT = "benComponent";

	public static final String CHECKED_OUT = "CHECKED_OUT";

	public static final String MANDATE = "MANDATE";

	public static final String MANDATE_TYPE_FED = "FED";

	public static final String BENEFIT_STATE_CODE_ALL = "ALL";

	public static final String STATE_FLAG_D = "D";

	public static final String TERM = "term";

	public static final String QUALIFIER = "qualifier";

	public static final String PROVIDER_ARRANGEMENT = "provider arrangement";

	public static final String DATA_TYPE = "data type";

	public static final String REFERENCE = "reference";

	public static final String CODE_DESC = "codedesc";

	public static final String UNIVERSE = "universe";

	public static final String UNIVERSE_CODE = "1";

	public static final String BENEFIT_LINE_UPDATED_SUCCESSFULLY = "benefit.line.hidden.success";

	public static final String ANSWER_OVERRIDDEN = "answer.overriddden.successfully";

	public static final String QUESTION_HIDE_STATUS_UPDATED = "question.hide.status.update";

	public static final String QUESTION_OVERRIDDEN_UPDATED = "question.overridden.successfully";

	public static final String PRODUCT_UNLOCKED = "product.unlocked";

	public static final String BENEFIT_COMPONENT_UNLOCKED = "benefitcomponent.unlocked";

	public static final String UPDATE_BENEFIT_DETAILS = "updateBenefitDetails(ProductTreeStandardBenefits productTreeStandardBenefits)";

	public static final String EXCEPTION_OCCURED_UPDATE_BENEFITDETAILS = "Exception occured in updateBenefitDetails method in ProductBusinessObjectBuilder";

	public static final String PERSIST_METHODE = "persist(HideAdminOptionBO subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)";

	public static final int TWO = 2;

	public static final String ENTITY_ID_LIST = "EntityIdsList";

	String GET_NOTES_FOR_DATAFEED = "getAssociatedNotesDatafeed";

	String GET_ASSOCIATED_BENEFIT_NOTES_DATAFEED = "getAssociatedBenefitNotesDatafeed";

	String GET_BC_NOTES_FOR_DATAFEED = "getAssociatedBCNotesDatafeed";

	public static final String GENERAL_BENEFIT_MANDATORY = "product.gen.benf.mandatory";

	public static final String ENTERED_SEQUENCE_INVALID = "entered.sequence.invalid";

	public static final String GENERAL_PROVISIONS_MANDATORY = "product.gen.provisions.mandatory";

	public static final String GENERAL_BENEFIT_SEQ_ONE = "general.benefit.seq.one";

	public static final String GENERAL_PROVISION_SEQ_ONE = "general.provision.seq.one";

	public static final String GENERAL_PROVISION_SEQ_TWO = "general.provision.seq.two";

	public static final String PRODUCT_STR_VERSION_INVALID = "product.str.version.invalid";

	public static final String SPS_DELETED_SUCCESSFULY = "sps.deleted.success";

	public static final String UNIQUE_REF_VALIDATION = "unique.ref.validation";

	public static final String PRODUCT_RULE_VALIDATION_ERROR = "product.rule.validation.error";
	
	public static final String ACCUM_QUESTION_NOTCODED = "contract.accum.validation.error";
	
	public static final String TIERED_ACCUM_QUESTION_NOTCODED = "contract.tier.accum.validation.error";
	
	public static final String NOT_AVAILABLE_IN_CLAIMSYSTEM = "accum.not.available.claimsys";
	
	public static final String STD_ACCUM_NOT_SET = "non.standard.accumulator.set";
	
	public static final String INVALID_ACCUM_SET = "invalid.accumulator.set";
	
	public static final String ACCUM_DELETED="accum.deleted.in.sys";

	public static final String PRODUCT_RULE_VALIDATION_LINK = "product.rule.validation.link";

	public static final String CONTRACT_SPS_VALIDATION_ERROR = "contract.sps.validation.error";

	public static final String CONTRACT_SPS_VALIDATION_LINK = "contract.sps.validation.link";

	public static final String CONTRACT_RULE_VALIDATION_LINK = "contract.rule.validation.link";

	public static String RETRIEVE_SPS_MAPPING_VALUES = "retrieveSPSMappingValues";
	
	public static String RETRIEVE_NOT_APPLICABLE_SPS_MAPPING_VALUES = "retrieveNotApplicableSPSMappingValues";
	
	public static String RETRIEVE_VAR_MAPPING_DF = "retrievevariableMappingValues";
	
	public static String RETRIEVE_TEMP_VAR_MAPPING_DF = "retrieveTempVariableMappingValues";

	public static String RETRIEVE_SERVICE_MAPPING_VALUES = "retrieveServiceTypeMappingDF";
	
	public static String RETRIEVE_NA_SERVICE_MAPPING_VALUES = "retrieveNAServiceTypeMappingDF";

	public static String CONTRACT_NOTES = "uncoded.variable.notes";

	public static String RETRIEVE_CUSTOM_MESSAGE_VALUES = "retrieveCustomMessagesDF";
	
    public static String RETRIEVE_CUSTOM_MESSAGE_VALUES_FOR_SEND_TO_TEST = "retrieveCustomMessagesDFSendToTest";
	
	public static String RETRIEVE_CUSTOM_MESSAGE_VALUES_FOR_SEND_TO_PRODUCTION = "retrieveCustomMessagesDFSendToProduction";

	public static String CUSTOM_MESSAGE_DELETE_SUCCESS = "custom.message.delete.success";

	public static String IND_MAP_DELETE_SUCCESS = "indicative.map.delete.success";

	public static String REF_MAP_DELETE_SUCCESS = "reference.map.delete.success";
	
	public static String ADMIN_METHOD_MAP_DELETE_SUCCESS = "adminmethodmapping.map.delete.success";

	public static String CUSTOM_MESSAGE_DUPLICATE = "customMessageText.duplicate";

	public static String CUSTOM_MESSAGE_CREATE_SUCCESS = "customMessageText.create.success";

	public static String CUSTOM_MESSAGE_UPDATE_SUCCESS = "customMessageText.update.success";

	public static String MSG_QUESTIONNARE_SAVE_BC_SUCCESS = "benefit.component.questionnare.save.successful";

	public static String SAVE_QUESTIONNAIRE_BENEFIT_SUCCESS = "benefit.questionnaire.save.successful";

	public static String DELETE_QUESTIONNAIRE_SUCCESS = "questionnaire.deleted.success";

	public static String INSERT_ROOT_QUESTION_SUCCESS = "questionnaire.insert.success";

	public static String UPDATE_ROOT_QUESTION_SUCCESS = "questionnaire.update.success";

	public static String DELETE_ROOT_QUESTION_SUCCESS = "questionnaire.delete.success";

	public static String DELETE_QUESTIONNAIRE_FAILURE = "questionnaire.deleted.failure";

	public static String LOCATE_ROOT_QUESTIONS = "locateRootQuestions";

	public static String NO_RESULT_FOR_FILTER_POPUP = "Filter.results.zero";

	public static String EXP_CONTRACT_RELEASE_SUCCESS = "expired.contracts.release.success";

	public static String EXP_EXCEED_LIMIT = "search.result.exceeds.500.limit";

	public static String BY_CY_ANSWER = "contract.checkin.bycy.answr";

	public static String PRODUCT_COMPONENT_RULE_UPDATED = "contract.benefit.component.rule.updated";

	public static String NOT_ANSWERED = "Not Answered";

	public static String SAVE_QUESTION_NOTE__SUCCESS = "benefit.question.note.save.successful";

	public static String UPDATE_QUESTION_NOTE__SUCCESS = "benefit.question.note.update.successful";

	public static String DELETE_QUESTION_NOTE__SUCCESS = "benefit.question.note.delete.successful";

	public static String QUESTION = "question";

	public static String RETRIEVE_QUESTION = "retrieveQuestionDataDomainByNoteID";

	public static String LOCATE_QUESTIONS = "locateQuestions";

	public static String RETRIEVE_UNANSWERED_QUESTION = "retrieveUnansweredQuestions";

	public static String IS_UNCODED_LINE_NOTE = "ifUncodedLineNotesExist";

	public static String IS_UNANSWERED_QUESTION_NOTE = "ifUnansweredQuestionNotesExist";

	/*-----------Indicative Mapping-------------*/
	int LOCATE_INDICATIVE_MAPPING = 1;

	int LOCATE_INDICATIVE_MAPPING_DF = 2;

	int LOCATE_REF_MAPPING = 1;

	public static String ATTACH_QUESTION = "ATTACHQUESTION";

	public static String NOTE_FLAG_F = "F";

	public static String BENEFIT_DEFNID = "benefitDefinitionId";

	public static String ALERT_MESSAGE_GENERAL_BENEFITS = "alert.message.general.benefits";

	public static String ALERT_MESSAGE_GENERAL_PROVISIONS = "alert.message.general.provisions";

	public static String ALERT_MESSAGE_SUPPLEMENTAL_BENEFITS = "alert.message.general.supplemental";

	public static String getAssociatedContractAdminOptionNotesDatafeed = "getAssociatedContractAdminOptionNotesDatafeed";

	public static String getAssociatedBenefitAdminOptionNotesDatafeed = "getAssociatedBenefitAdminOptionNotesDatafeed";

	public static int MAX_SEARCH_RESULT_SIZE = 50;

	public static String BENEFIT_COMPONENT_WITH_INAVLID_LINES = "productStructure.benefitcmpntcompoent.inavlidlines";

	public static String BENEFIT_COMPONENT_PS_UPDATE_PRODUCTFAMILY = "productStructure.update.productfamily";

	public static String PRODUCT_BENEFIT_COMPONENT_WITH_INAVLID_LINES = "product.benefitcmpntcompoent.inavlidlines";

	public static String BENEFIT_COMPONENT_PRODUCT_UPDATE_PRODUCTFAMILY = "product.update.productfamily.businessentity";

	public static String BENEFIT_TIER_DEFINITION_BO = "com.wellpoint.wpd.common.standardbenefit.bo.BenefitTierDefinitionBO";

	public static String LOCATE_TIER_DEFINITIONS = "locateTierDefinitions";

	public static String GET_BNFT_TIER_DEFNS = "getAssociatedTierDefinitions";

	public static final int TIER_DEFN_SIZE = 20;   

	public static String RETRIEVE_PROVIDER_SPECIALITY_CODES = "getProviderSpecialityCodes";
	
	public static String GET_TIER_DEFINITION ="getTierDefinition";
	
	public static String INVALID_RANGE_IN_TIER = "invalid.range.in.tier";
	
	public static String INVALID_RANGE_IN_TIER_WCLC = "invalid.range.in.tier.wclc";
	
	public static String INVALID_NUMBER_IN_TIER = "benefit.tier.criteria.not.number";
	
	public static String OVERLAPPING_VALUES_IN_TIER = "overlapping.values.in.tier";
	
	public static String INVALID_TIER_CRITERIA_WCLC = "invalid.values.in.tier.wclc";
	
	public static String DUPLICATE_LEVEL_WITH_SAME_CRTVALUES = "duplicate.level.with.samecrt.values";

	/*-----------------------Blaze Rule--------------------*/

	String BNFTS_SHOULD_BE_OF_SAME_RULE_TYPE = "benefits.should.be.of.same.ruletype";

	String BNFT_COMP_SHOULD_BE_OF_SAME_RULE_TYPE_AS_BNFTS = "benefit.comp.should.be.of.same.ruletype.as.associated.benefits";
	
	String PROD_COMPONENTS_SHOULD_BE_OF_SAME_RULE_TYPE = "product.components.should.be.of.same.ruletype";

	String CNTR_SEGMENTS_SHOULD_BE_OF_SAME_RULE_TYPE = "contract.segments.should.be.of.same.ruletype";

	String RULE_VALIDAT_BO = "com.wellpoint.wpd.common.bo.RuleValidationBO";

	String FIND_BENEFIT_RULES = "findBenefitComponentRuleIds";

	String FIND_PROD_RULES = "findproductRuleIds";

	String FIND_CONTRCT_RULES = "findContractRuleIds";
	
	String BENEFIT_TIER_DELETE = "benefit.tier.delete";
		
	String LEVEL_IN_BENEFIT_TIER_DELETE = "level.in.benefit.tier.delete";
	
	
	// for product benefit general info

	public static String RETRIEVE_TIERDEFN_PRODUCT = "retrieveTierDefinitionsForProduct";
	
	public static String RETRIEVE_TIERDEFN_CONTRACT = "retrieveTierDefinitionsForContract";
	
	public static String RETRIEVE_TIERDEFN_BENEFIT = "retrieveTierDefinitionsBenefit";	
	
	public static String PRODUCT_TIER_DEFINITION_BO = "com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO";
	
	/* ---------- WTT Messages ----------------------*/ 
	String MSG_TESTCASE_SAVE_SUCCESS = "testcase.save.success";
	String MSG_TESTCASE_UPDATE_SUCCESS = "testcase.update.success";
	String MSG_TESTSUITE_SAVE_SUCCESS = "testsuite.save.success";
	String MSG_TESTSUITE_UPDATE_SUCCESS = "testsuite.update.success";
	String MSG_TESTCASE_ALREADY_EXISTS	="testcase.exists";
	int DROP_DOWN_ACTION = 999;
	
	public static String GET_TIERDEFN_PRODUCT = "getTiersAddedInProduct";	
	
	String MSG_PRODUCT_TIER_SAVEINVALID = "product.tier.save.result";
	
	public static String RETRIEVE_TIER_INFORMATION = "getContractTierInformation";
	
	public static String CHECK_LEGACY_NOTE_EXIST = "checkLegacyNotesExist";
	
	//String PRODUCT_BENEFIT_TIERDEFN_UPDATE = "product.tier.update.success.result";
	
	public static String RETRIEVE_BNFT_TIER_DEFNS = "retrieveTierDefinitionsForBenDefn";
	
	public static String COMMA = ",";
	//CARS START
	//DESCRIPTION : Validation message for frequency
	String MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER = "benefitlevel.benefit.frequency.not.number";
	
	String MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY = "benefitlevel.benefit.frequency.not.empty";
	
	public static int QUALIFIER_CODE = 1935;
	
	public static final String SPACE_STRING = " ";
	
	public static final String PER_STRING = " PER ";
	
	public static final String EMPTY_STRING = "";
	//CARS AM1 START
	String GET_QUESTION_FOR_TIERP = "getQuestionForTierP";	
	String GET_QUESTION_FOR_TIERC = "getQuestionForTierC";	
	String GETQUALIFIERFORTIERP = "getQualifiersForTierP";
	String GETQUALIFIERFORTIERC = "getQualifiersForTierC";
	String GETTERMFROMTIERPRODUCT = "getTermsFromTierProduct";	
	String GETTERMFROMTIERCONTRACT = "getTermsFromTierContract";
	String ADMINEMTHODFORTIERPRODUCT = "getAdminMethodsForTierProduct";
	String ADMINEMTHODFORTIERCONTRACT = "getAdminMethodsForTierContract";
	String BENEFITADMINISTRATION_FOR_TIERPRODUCT = "getBenefitAdministrationForTierProduct";
	String BENEFITADMINISTRATION_FOR_TIERCONTRACT = "getBenefitAdministrationForTierContract";
	String BENEFIT_COM_SYSID = "benefitComSysId";
	String BEN_SYS_ID="benefitSysId";
	String BEN_TIER_SYS_ID = "benefitTierSysId";
	String ENTY_TIER_SYS_ID = "entityTierSysId";
	String GETTIERCRITERIA_FOR_GENERALBENEFITSIN_CONTRACT = "getTierCriteriaForGeneralBenefitsInContract";
	String GETTIERCRITERIA_FOR_BENEFITIN_CONTRACT = "getTierCriteriaForBenefitInContract";
	String GETTIERCRITERIA_FOR_GENERALBENEFITSIN_PRODUCT = "getTierCriteriaForGeneralBenefitsInProduct";
	String TIERCONTRACT = "TIERCONTRACT";
	String TIERPRODUCT = "TIERPRODUCT";
	//CARS AM1 END
	String MARKET_BUSINESS_UNIT = "marketBusinessUnit";

	public static final String SEARCHSPSNAMEFOROVERRIDE_FOR_DATAFEED = "searchSPSNamesForOverrideDatafeed";
	//CARS END

	public static String PRODUCT_BENEFIT_GENINFO_SAVE = "product.benefit.geninfo.save.success.result";
	
	public static String PRODUCT_BC_GENINFO_SAVE = "product.bc.geninfo.save.success.result";
	
	public static String RETRIEVE_BENEFIT_RULEID = "getBenefitRuleId";
	
	public static String RULE_TYPE_CD_WPDAUTOBG = "WPDAUTOBG";
	
	public static String RULE_TYPE_HEADER    = "HEADER";
	
	public static String RULE_TYPE_CD_BLZWPDAB  = "BLZWPDAB";
	
	public static String RULE_TYPE_BLAZE    = "BLAZE";
	
	public static String RULE_TYPE_VALIDATION_LINK_PRODUCT = "rule.type.validation.link.product";
	
	public static String RULE_TYPE_VALIDATION = "rule.type.validation";
	
	public static String RULE_TYPE_VALIDATION_LINK_CONTRACT = "rule.type.validation.link.contract";
	
	public static String CONTRACT_RULE_TYPE_VALIDATION = "contract.rule.type.validation";
	
	public static String PRODUCT_RULE_TYPE_VALIDATION = "product.rule.type.validation";
	
	public static String OBJECT_TRANSFERRED = "OBJECT_TRANSFERRED";
	
	public static final int DELETED_DATE_SEGMENTS = 1;
	
	public static final int ROOT_DELETE_CONTRACT = 2;
	
	public static final int ROOT_DELETE_SCHEDULED_CONTRACT = 3;
	
	public static final int ADMN_METHOD_FIELDS = 250;
	
	//for BX data feed
	public static final String NOT_APPLICABLE_STATUS ="NOT_APPLICABLE";
	
	public static final String SCHEDULED_TO_TEST ="SCHEDULED_TO_TEST";
	
	public static final String BX_DATAFEED_USER ="DATAFEED";
	
	public static final String CONTRACT_STATUS_IS_NOT_AVAILABLE = "contract.status.code.not.available";
    
	public static final String CONTRACT_REASON_IS_NOT_AVAILABLE = "contract.reason.code.not.available";
    
	public static final String CONTRACT_STATUSDATE_IS_NOT_AVAILABLE = "contract.statusdate.code.not.available";
	
	public static final String CONTRACT_STATUS_VALIDATION_ERROR = "contract.status.validation.error";
	
	//ICD10 ChangesStart
	public static final String RULE_REPORT_RESULT = "com.wellpoint.wpd.common.report.bo.RuleReportBO";
	
	public static final String CONTRACT_EXTRACT_RULE = "retrieveContractRules";
	
	public static final String CONTRACT_EXTRACT_HEADER_RULE = "retrieveContractHeaderRules";
	
	public static final String PRODUCT_EXTRACT_DENAIL_UM_RULE_QUERY = "retriveProductExclusionDenailUmRules";
	
	public static final String INDIVIDUAL_RULE_EXTRACT_QUERY = "individualRuleExtractQuery";
	
	public static final String INDIVIDUAL_HEADER_RULE_EXTRACT_QUERY_BC = "individualHeaderRuleExtractQueryBC";
	
	public static final String INDIVIDUAL_HEADER_RULE_EXTRACT_QUERY_BNFT = "individualHeaderRuleExtractQueryBnft";
	
	public static final String CONTRACT_GROUP_RULE = "retrieveContractGroupRules";
	
	public static final String PRODUCT_EXTRACT_HEADER_RULE = "retriveProductAllRules";;
	
	public static final String IMAGE_READY_DOMAIN ="com.wellpoint.wpd.common.contract.bo.IMAGEReadyBusinessDomainBO";
	
	//ICD10 End
	
	// Indicative Long Term Solution
	public static final int DUPLICATE_SEG_CODE = 4;
	public static final int MANDATORY_PARAMS_MISSING = 5;
	public static final int CORRECT_INDICATIVE_CODES = 6;
	public static final int FILE_DOES_NOT_EXIST = 7;
	public static final int ERROR_IN_READING_UPLOAD_FILE = 8;
	public static final int NO_DATA_IN_UPLOAD_FILE = 9;	
	public static final int VALIDATIONS_WHILE_PROCESSING_IMPORT_EXCEL = 10;
	public static final String NO_CHANGES_IN_THE_EXCEL_FILE = "file.nochanges";
	public static final String NO_CHANGES_IN_THE_PROD_REGION = "prod.nochanges";
	public static final String UPLOAD_ERROR = "upload.error";
	public static String  FILE_IS_EMPTY = "empty.file";
	public static String FILE_NOT_EXIST_ERROR = "file.notexist";
	public static String DUPLICATE_SEG_CODES_ERROR="duplicate.segCodes";
	public static String NO_CHANGES_IDENTIFIED="no.changes.identified";
	public static String SEG_CODES_DOES_NOT_EXIST_ERROR="segCodes.doesnot.exists";
	public static String FILENAME_MANDATORY = "mandatory.fileName";
	public static String  IMPORT_ALLOWED_TEST_REGION = "import.only.test";
	public static String  INVALID_FILE = "importfile.invalid";	
	public static String  INVALID_DATA_IN_FILE = "importfile.invaliddata";
	public static String MANDATORY_PARAMS_MISSING_ERR = "mandatory.params.missing";
	public static final int PROCESS_EXCEL_FILE_IMPORT = 0;
	public static final int CONFIRM_IMPORT = 0;
	public static final String IMPORT_SUCCESS = "indicative.upload.done.success";
	public static final String COPY_TO_PROD_SUCCESS = "indicative.copytoprod.done.success";
	// End Indicative Long Term Solution
	
	public static final String REGION_PROD = "PROD";
	public static final String REGION_TEST = "TEST";
	String CATALOG_ID_FOR_INDICATIVE = "4147";

	String ADDED_INDICATIVES = "ADDED_INDICATIVES"; 
	String UPDATED_INDICATIVES = "UPDATED_INDICATIVES"; 
	String DELETED_INDICATIVES = "DELETED_INDICATIVES";

	String MANDATORY_FIELD_MISSING = "mandatory.field.missing";
	
	public static final String INDICATIVE_MAPPING_OPERATION_PERFORMED_IMPORT = "IMPORT";
	public static final String INDICATIVE_MAPPING_OPERATION_PERFORMED_COPY_TO_PROD = "COPY_TO_PROD";
	//Added as part of Indicative Mapping APRIL 2015 Release
	int DATAFEED_INDICATIVE_CONFIGURATION = 18;
}

