/*
 * WebConstants.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.util;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WebConstants.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface WebConstants {
    
	public static final String WEB_SERVICE_CONTRACT_OBJECT = "contractWSErrorList";
    
    public static final int MAX_RES_FOR_DUP = 500;
    public static final String RETVALUE_EBX_SUCCESS = "ebxsuccess";  // SSCR 17571 -Tab impl
    //PopUpFilter 
    public static final String FILTER_SEARCH_STRING = "searchString";
    public static final String FILTER_PARENT_CATALOG = "parentCatalog";
    public static final String FILTER_LOOKUP_ACTION = "lookUpAction";
    public static final String FILTER_LINE_OF_BUSINESS = "lob";
    public static final String FILTER_BUSINESS_ENTITY = "be";
    public static final String FILTER_BUSINESS_GROUP = "bg";
    public static final String FILTER_ENTITY_TYPE = "entityType";
    public static final String FILTER_ENTITY_ID = "entityId";  
    public static final String MANDATE_FIELD_REQUIRED = "mandate.field.required";
    public static final String CONTRACT_WEBSERVICE_REQUEST ="webservicerequest";
    final String SEARCH_STD_ACCUMULATOR_MENU_ACTION = "searchStdAccumulatorMenuAction";
   //Security Rights
	public static final String SECURITY_MOD_PARENT = "Parent";
	public static final String SECURITY_MOD_CHILD = "Child";
	public static final String ADMIN_OPTION_MODULE = "ADMINOPTIONDEVELOPMENT";
    String ENTITY_TYPE_PRODUCT_STRUCTURE = "prodStructure";
    String ENTITY_TYPE_CONTRACT = "contract";
	public static final String QUESTION_MODULE = "QUESTIONDEVELOPMENT";
	public static final String INDICATIVE_MAPPING_MODULE = "INDICATIVEMAPPINGDEVELOPMENT";
	
	/* Admin method */
	public static final String ADMIN_METHOD_MODULE = "ADMINMETHODDEVELOPMENT";	
	public static final String STDACCUM_METHOD_MODULE = "STDACCUMMETHODDEVELOPMENT";	
	public static final String ADMIN_METHOD_MAINTAIN_MODULE = "ADMIN METHOD MAINTAIN";
	public static final String ADMIN_METHOD_MAPPING_MODULE="AM MAPPING MAINTAIN";
	public static final String DELETE_ADMIN_MTHD="admin.method.delete.group";
	public static final String DUPLICATE_ADMINMETHOD_GROUP_ADDED="admin.method.duplicate.group";
	public static final String ADMIN_MTHD_NMBR_NOT_NUMERIC = "admin.method.number.notnumeric";
	
	public static final String ADMIN_METHOD_TASK_ADD="ADD ADMIN METHOD";
	public static final String STDACCUM_METHOD_TASK_ADD="ADD STDACCUM METHOD";
	public static final String ADMIN_METHOD_MAINTAIN_TASK="MAINTAIN ADMIN METHOD";
	public static final String STDACCUM_METHOD_MAINTAIN_TASK="MAINTAIN STDACCUM METHOD";
	public static final String ADMIN_METHOD_MAPPING_TASK_ADD="AM MAPPING CREATE";
	public static final String ADMIN_MAINTAIN_MAPPING_TASK_MAINTAIN="AM MAPPING MAINTAIN";
	public static final String ADMIN_METHOD_MAINTAIN_DEL_TASK = "MAINTAIN ADMIN METHOD DEL";
	public static final String ADMIN_METHOD_MAINTAIN_EDT_TASK = "MAINTAIN ADMIN METHOD EDT";
	public static final String ADMIN_METHOD_MAPPING_DEL_TASK = "MAINTAIN ADMIN METHOD MAP DEL";
	public static final String ADMIN_METHOD_MAPPING_EDT_TASK = "MAINTAIN ADMIN METHOD MAP EDT";
	
	public static final String SPS_REFERENCE_MAPPING_MODULE = "SPSREFERENCEMAPPINGDEVELOPMENT";
	public static final String CONTRACT_DEVELOPMENT_MODULE = "CONTRACTDEVELOPMENT";
	public static final String REFERENCE_DATA_MODULE = "REFERENCEDATAMAINTENANCE";
	public static final String SECURITY_MODULE = "SECURITYMAINTENANCE";
	public static final String CONTRACT_ID_MODULE = "RESERVECONTRACT";
	public static final String SYSTEM_MODULE = "SYSTEMADMINISTRATION";
	public static final String MIGRATION_WIZARD_MODULE = "MIGRATIONWIZARD";
	public static final String CATALOG_CREATE_TASK = "CATALOGCREATE";
	public static final String CATALOG_MAINTAIN_TASK = "CATALOGMAINTAIN";
	public static final String ITEM_MAINTAIN_TASK = "ITEMMAINTAIN";
	public static final String SUBCATALOG_CREATE_TASK = "SUBCATALOGCREATE";
	public static final String SUBCATALOG_MAINTAIN_TASK = "SUBCATALOGMAINTAIN";
	public static final String ROLE_CREATE_TASK = "ROLECREATE";
	public static final String ROLE_MAINTAIN_TASK = "ROLEMAINTAIN";
	public static final String MODULE_CREATE_TASK = "MODULECREATE";
	public static final String MODULE_MAINTAIN_TASK = "MODULEMAINTAIN";
	public static final String TASK_CREATE_TASK = "TASKCREATE";
	public static final String TASK_MAINTAIN_TASK = "TASKMAINTAIN";
	public static final String APPLICATION_ACCESS_CONTROL_TASK = "APPLICATIONACCESSCONTROL";
	public static final String LOG_VIEWER_TASK = "LOGVIEWER";
	public static final String BASIC_SEARCH_TASK = "BASICSEARCH";
	public static final String ADVANCED_SEARCH_TASK = "ADVANCEDSEARCH";
	public static final String SEARCH_ITEM_NAVIGATION = "BasicSearchCriteria";
	public static final String SEARCH_ENGINE_MODULE = "SEARCHENGINE";
	public static final String ITEM_CREATE_TASK = "ITEMCREATE";
	public static final String MIGRATE_CP2000_TASK = "MIGRATECP2000CONTRACT";
	public static final String MIGRATE_CP_TASK = "MIGRATECPCONTRACT";
	public static final String BLUE_EXCHANGE_MAPPING_MODULE="BLUEEXCHANGEMAPPING";
	public static final String BLUE_EXCHANGE_MAPPING_TASK="VARIABLEMAPPINGMAINTAIN";
	public static final String EBX_BLUE_EXCHANGE_MAPPING_MODULE="EBXBLUEEXCHANGEMAPPING";
	public static final String EBX_BLUE_EXCHANGE_MAPPING_TASK="RULESPSMAPPINGMAINTAIN";
	public static final String SIMULATION_MAPPING_MODULE="EBXSIMULATION";
	public static final String SIMULATION_MAPPING_TASK="SIMULATION";
	public static final String RMA_WIZARD_MODULE = "RMAWIZARDDEVELOPMENT";
	public static final String CREATE_RULES = "CREATERULES";
	
	 /*For Dictionary Manager Start*/     
	public static final String DICTIONARY_MANAGER = "DICTIONARYMANAGER";
	public static final String DICTIONARY_MANAGER_MODULE = "DICTIONARYMANAGER";
	public static final String ADD_WORD_TASK = "ADDWORD";
	 /*For Dictionary Manager End*/     
	public static final String DESCRIPTION_INVALID="description.invalid";
	public static final String ADMIN_KEY_FOR_QSTNR="ADMIN_KEY_FOR_QSTNR";
	public static final String LOAD_ADMIN_OPTION="loadAdminOption";
	public static final String PS_ADMIN_OPTION="productStructureAdminOption";
	public static final String PRODUCT_ADMIN_OPTION="productAdminOption";
	public static final String PRODUCT_ADMIN_OPTION_VIEW="productAdminOptionView";
	public static final String PS_ADMIN_OPTION_VIEW="productStructureAdminOptionView";
	public static final String PROD_STRUCTURE_NAME="ProdStructure";
	public static final String GEN_BENEFITS="GENERAL BENEFITS";
	public static final String GEN_PROVISIONS="GENERAL PROVISIONS";
	public static final String SUPPLEMENTAL_BENEFITS="SUPPLEMENTAL BENEFITS";
	
    public String PRODUCT_MODULE = "PRODUCTDEVELOPMENT";
    public String CONTRACT_MODULE = "CONTRACTDEVELOPMENT";
    public String PRODUCT_STRUCTURES_MODULE = "PRODUCTSTRUCTUREDEVELOPMENT";
    public String BENEFIT_COMPONENTS_MODULE = "BENEFITCOMPONENTDEVELOPMENT";
    public String BENEFIT_MODULE = "BENEFITDEVELOPMENT";
    public String NOTES_MODULE = "NOTESDEVELOPMENT";
	public static final String TASK_CREATE = "CREATE";
	public static final String MAINTAIN_TASK = "MAINTAIN";
	public static final String VALIDATE_TASK = "VALIDATE";
	public static final String DATA_EXTRACT_TASK = "DATAEXTRACT";
	public static final String RELEASE_TASK = "RELEASE";
	public static final String ADMIN_RELEASE = "ADMINRELEASE";
	public static final String REFERENCE_DATA_VALIDATION_FAILURE = "product.checkin.failure.conflict";
	public static final String UNIQUE_REFERENCE_LIST_TRUNCATED = "unique.ref.list.truncated";
	
    public String IND_MAP_MODULE = "INDICATIVEMAPPINGMODULE";
    
    /* Added For Indicative Long Term Solution */
	public static final String INDICATIVE_LAYOUT_MODULE = "INDICATIVELAYOUTDEVELOPMENT";
	
    public static String NO_PRODUCT_ATTACHED = "no.product.attached";
    public static String COLOR_RED= "color: red";
    public static String TEXT_EXCEEDS = "text.excceds.limit";
    public static String TEST = "Test";
    public static String REGULAR = "Regular";
   
    public static String FLAG_Y = "Y";
    public static String ENTER_DATE_INSIDE = "enter.date.inside";
    public static String STARTDATE ="Start Date";
    public static String CONTRACT_DATEINVALID="contract.dateinvalid";
    public static String PAGE_ID = "pageId";
    public static String PAGE = "page";
    public static String CONT_ID = "contId";
    public static String CONTRACT_SYSTEM_ID = "sysId";
    public static String CONTRACT_VERSION = "version";
    
    public static String REQUIRED_FIELDS = "requiredField";
    
    public static String REQUIRED_SEARCH_FIELDS = "please.enter.valid.data.for.search";
    
    public static String SEARCH_RESULTS_ZERO = "search.results.zero";
    
    public static String SEARCH_RESULTS_ZERO_FOR_TERMPVA = "search.results.zero.for.termpva";
    
    public static String PREVIOUS_VERSIONS = "previousVersion";

    public static String VIEW_STRUCTURE = "view";

    public static String CREATE_STRUCTURE = "create";
    
    public static String UPDATE_STRUCTURE = "update";
    
    public static String BASE_DATE_ID ="baseDateId";
    
    public static String SUCCESS = "success";
    
    public static String SHELL ="SHELL";
    
    public static String TILDA ="~";
    
    public static String COMMA =",";
    
    public static String PRODUCT_STATUS ="productStatus";
    
    public static String NOTE_STATUS ="noteStatus";
    
    public static String TEST_EFFECTIVE_DATE ="Test Effective Date";

    public static String STATUS = "BUILD";
    public  static String  STATE= "New";
    public  static  int  VERSION = 0;
//  Start of CR Check In Reference Data Validation  
    public static String GET_CONTRACT_DUPLICATE_BENEFIT_LINE_REF = "duplicateContractBenefitLineRef";
    public static String GET_CONTRACT_DUPLICATE_ADMIN_QUEST_REF = "duplicateContractAdminQuestRef";    
    public static String GET_CONTRACT_DATE_SEGMENTS = "getContractDateSegments";
    public static String GET_CONTRACT_DUPLICATE_ALL_BENEFIT_REF = "getContractDuplicateAllBenefitRef";
    public static String GET_CONTRACT_DUPLICATE_ALL_QUEST_REF = "getContractDuplicateAllQuestionRef";
//End of CR Check In Reference Data Validation    
    
    //for CMA DOLLAR validation
    public static String PRIMARY_CODE_CMA = "invalid.primary.code";
// For Checkin Validation CR
    public static String GET_UNCODED_LINE_NOTES = "getUncodedLineNotes";
    public static String GET_UNCODED_QUESTION_NOTES = "getUncodedQuestionNotes";
    public static String GET_UNCODED_PRD_QUEST_NOTES = "getUncodedPrdQuestNotes";
    /* For SearchBackingBean *Start* */
    public static String BASIC_SEARCH = "basicSearch";

    public static String ADVANCED_SEARCH = "advancedSearch";

    public static String EFFECTIVE_FROM_DATE = "Effective From Date";

    public static String EFFECTIVE_TO_DATE = "Effective To Date";

    public static String END_FROM_DATE = "End From Date";

    public static String END_TO_DATE = "End To Date";

    public static String CREATED_DATE = "Created Date";

    public static String LAST_UPDATED_DATE = "Last Updated Date";

    public static String MSG_DATE_INVALID = "mandate.dateinvalid";

    public static String MSG_EFFECTIVE_DATES_INVALID = "effectivedates.invalid";

    public static String MSG_END_DATES_INVALID = "enddates.invalid";

    public static String LOB_URL_PARM_NAME = "lob";
    
    public static String EOB03_INDENTIFIER = "eobIdentifierList";
    
    public static String HEADER_RULE ="headerRule";
    
    public static  String APPLICABLE_BX = "applicableToBX";
    
    public static String SEND_DYNAMIC_INFO ="sendDynamicInformation";

    public static String REGEX_DATE_FORMAT_STRING = "[\\d]{1,2}/[\\d]{1,2}/[\\d]{4}";
    
    public static String REGEX_ID_FORMAT_STRING = "[A-Z,a-z,0-9]{4}";

    public static String DATE_FORMAT_STRING = "MM/dd/yyyy";

    public static String CREATED_FROM_DATE = "Created From Date";

    public static String CREATED_TO_DATE = "Created To Date";

    public static String UPDATED_FROM_DATE = "Updated From Date";

    public static String UPDATED_TO_DATE = "Updated To Date";

    public static String MSG_CREATED_DATES_INVALID = "created.invalid";

    public static String MSG_UPDATED_DATES_INVALID = "updated.invalid";

    /* For SearchBackingBean *End* */
    public static String SEARCH_MANAGED_BEAN = "searchBackingBean";

    public static String MANDATE_MANAGED_BEAN = "MandatedBenefitsBackingBean";

    public static String MESSAGE_NAME_REQUEST = "messages";

    public static String END_DATE = "End Date";
    
    public static String CHECKIN = "CHECK IN";
    
    public static String COPY = "COPY";
    //Transfer log
    public static String VERSION_ID = "version";
    public static String CONTRACT_SYS = "contractSysId1";
    public static String DATESEGMENT = "dateSegmentsId";
    public static String LOCATE_NAME = "locateTransferlog";
    public static String LOCATE_FIRST_DATESEGMENT ="locateFirstDateSegment";
    public static String BREADCRUMB_FIRST_STRING = "Contract Development >> Contract (";
    public static String BREADCRUMB_SECOND_STRING = ") >> View Date Segment Transfer Log";
    public static String TRANSFERLOG_RETURN = "viewTransferLog";
    public static String EFFECTIVE_DATE = "Effective Date";
    public static String EXPIRY_DATE = "Expiry Date";
    public static String SALES_MARKET_DATE = "Sales Market Date";
    public static String CONTRACT_TERM_DATE = "Contract Term Date ";
	//	 Contract Entry - Start
    public static String RESERVER_CONTRACT = "reservecontract";
    
    public static String CONTRACT_IN_USE = "contract.used.by.another.user";
    
	public static String START_GREATER_ENDS_DATE = "end.date.greater";
	
	public static String CREATED_START_GREATER_END_DATE ="created.end.date.greater";
	
	public static String UPDATED_CREATED_GREATER_END_DATE="updated.end.date.greater";
	
	public static String CONTRACT_SESSION_KEY = "contractSessionObject";
	
	public static String CONTRACT_COMMENT_FIELDS_SIZE = "contract.comment.max.length";
	
	public static String NOTE_ATTACHED_ALREADY = "single.note.restrict";
	public static String NOTE_ATTACHED_ALREADY_BC = "single.note.restrict.benefitcomponent";
	public static String NOTE_ATTACHED_ALREADY_BENEFIT = "single.note.restrict.benefit";
	public static String NOTE_ATTACHED_ALREADY_BENEFITLINE = "single.note.restrict.benefitline";
	
//	public static String MANDATE = "Mandate";
//	
//	public static String MANDATE_TYPE = "MANDATE";
//	
//	public static String FEDERAL_TYPE = "FED";

	public static String CANNOT_ATTACH_NOTE_TO_MANDATE="cannot.attach.note.to.mandate";
	
	public static String STANDARD_BENEFIT_MANDATE_NOTE="standard.benefit.mandate.note";
	
	public static String BENEFIT_COMPONENT_MANDATE_NOTE="benefit.component.mandate.note";
	
	public static String UPDATED_CONTRACT_LINES ="updateContractLines";
	//	 Contract Entry - End

	public static String COMPONENT_NOT_ATTACHED = "component.not.attached";

    /* For ViewMandatedBenefitsBackingBean *Start* */
    public static String SESSION = "#{sessionScope}";

    public static String MANDATEDBENEFITSBACKINGBEAN = "MandatedBenefitsBackingBean";

    public static String ID = "id";

    /* For ViewMandatedBenefitsBackingBean *END* */
    final String MSG_WARN_ID = "0";

    final String MSG_ERROR_ID = "1";

    final String STATUS_BUILDING = "BUILDING";

    final String STATUS_REDAY_TO_TRANSFER = "READY FOR TRANSFER";

    final String STATUS_BEING_MODIFIED = "BEING MODIFIED";

    final String STATUS_TRANSFERED = "TRANSFERED";
    
    public static String STATUS_CHECK_IN = "CHECK IN";
    
    public static String EDITCONTRACT ="EDITCONTRACT";
    
    public static String REPLACE_NOTE ="replaceNote";
    
    public static String CONTINUE_NOTE ="continueNote";
    
    public static String REPLACE_PROCUCT = "replaceProduct";
    
    public static String CONTINUE_PRODUCT ="continueProduct";
    
    public static String RETRIEVE_PRODUCT_NAME = "retrieveProductName";
    
    public static String RETRIEVE_PRODUCT_RULE_CODES = "retreiveProductRuleCodes";
    
    public static String SEARCH_ALL_STANDARDBENEFITS = "searchAllStandardBenefits";
    
    public static String SEARCH_VISIBLE_STANDARDBENEFITS = "searchVisibleStandardBenefits";
    
    public static String ATTACH_BENEFITCOMP_TO_PRODUCTPS ="attachBnftCmpntToPrdctPS";
    
    public static String COPY_ASSOSIATIONS ="copyAssociations";
    
    public static String GET_DUPLICATE_REFERENCE = "getDuplicateRef";
    
    public static String GET_QUESTION_REFERENCE= "getQuestionReference";
    
    public static String GET_PRODUCT_VALUES = "getProductValues";
    
    public static String GET_PRODUCT_RULES = "productRules";
    
    public static String DUPLICATE_PRODUCT_RULES = "duplicateProductRules";
    
    public static String PRODUCT_RULES_MANDATORY_VALUE_CHECK = "productRulesMandatoryValCheck";
    
    public static String GET_PRODUCT_ALL_RULES = "productAllRules";
    
    public static String GET_BENEFITCOMPONENT_VALUES="getBenefitComponentValues"; 
    
    public static String GET_BENEFITCOMPONENTS = "getBenefitComponents";
    
    public static String GET_BENEFIT_HIERARCHY = "getBenefitHierarchy";
    
    public static String GET_VALID_COMPONENTS_FOR_PRODUCT ="getValidComponentsForProduct";
    
    public static String GET_VALID_PRODUCTS_FOR_CONTRACT = "getValidProductsForContract";
    
    public static String GET_MANDATORY_BENEFITS = "getMandatoryBenefits";
    
    public static String GET_MANDATORY_GEN_PROVISION = "getMandatoryGenProvision";
    
    public static String GET_DUPLICATE_PRODUCTS = "getDuplicateProducts";
    
    public static String REFERENCE_DATA_VALIDATION = "referenceDataValidation";
    
    public static String RETRIEVE_PVA_FOR_VALIDATION ="retrievePvaForValidation";
    
    public static String GET_PRODUCT_STRUCTURE_ID="getProductStructureIdFromProductAndBenefitComponentKey";
    
    public static String LOCATE_ENTITY_PRODUCT_ADMIN = "locateEntityProductAdministration";
    
    public static String GET_NEXT_SEQUENCE = "getNextSequence";
    
    public static String EFFECTIVEDATE = "effectiveDate";
    
    public static String EFFECTIVE_DATE_D ="effectiveDate_Date";
    
    public static String PRODUCT_STRUCTURE_VALIDATION = "productStructureValidation";
    
    public static String CHECKIN_DUPLICATE_VALIDATION = "checkinDuplicateValidation";
    
    public static String RETRIEVE_LATEST_VERSION = "retrieveLatestVersion";
    
    public static String RETRIEVE_BY_PRODUCT_NAME ="retrieveByProductName";
    
    public static String RETRIEVE_LATEST_VERSION_NUMBER = "retrieveLatestVersionNumber";
    
    public static String STATUS_CHECK_OUT = "CHECK OUT";
	
	public static String STATUS_COPY = "COPY";

    final String MSG_BUILDING = "Mandate is in BUILDING status. Are you sure that you want to delete?";

    final String MSG_READY_TO_TRANSFER = "Mandate is in READY FOR TRANSFTER status. Are you sure that you want to delete?";

    final String MSG_BEING_MODIFIED = "The Mandate is in BEING MODIFIED status. This cannot be deleted";

    final String MSG_TRANSFERED = "The Mandate is in transfered status. This cannot be deleted";

    String DEFAULT_ERROR_MSG = "common.default";

    public static String MANDATE_GENERAL_INFO = "mandateGeneralInfo";

    public static String MANDATE_DETAIL_INFO = "mandateInfo";
    
    public static String GET_BC_WITH_ZERO_VISIBLE_BENEFITS = "getBCWithZeroVisibleBenefits" ;

    public final static String PANEL_WIDTH = "925";
    
    public static String RESERVED_DATES_CONDITION01 = "reserved.dates.condition01";
    public static String RESERVED_DATES_CONDITION = "reserved.dates.condition";
    public static String MANDATORY_FIELDS_REQUIRED = "mandatory.fields.required";
    public static String INVALID_CRITERIA_VALUE = "invalid.criteria.value";
    public static String RANGE_GREATER_VALIDATE = "range.greater.validate";
    
    public static String MANDATORY_FIELD_REQUIRED = "mandatory.field.required";
    
    public static String QUESTIONS_LENGTH_INVALID = "questions.length.invalid";
    public static String ADMIN_OPTION_NAME_EXCEED_LIMIT_LENGTH = "admin.option.name.length";
    
    public static String TERM_NOT_VALID_DATE ="term.not.valid.date";
    
    public static String SALES_NOT_VALID_DATE ="sales.not.valid.date";
    
    public static String MANDATORY_NOTE_FIELDS_REQUIRED = "mandatory.fields.notes.required";
    
    public static String MANDATORY_BENEFIT_VISIBLE_REQUIRED = "mandatory.benefit.visible.required";
    
    public static String MANDATORY_BENEFIT_VISIBLE_NEEDED = "mandatory.benefit.visible.needed";
    
    public static String MANDATORY_BENEFIT_LINE_VISIBLE_REQUIRED = "mandatory.benefit.line.visible.required";
    
    public static String MANDATORY_ADMINOPTION_FIELDS_REQUIRED = "mandatory.fields.adminoption.required";
    
    public static String MINOR_HEADING_SIZE = "minor.heading.length";
    
    public static String DESCRIPTION_SIZE_1_240 = "description.field.should.have.minimum.of.1.characters.and.maximum.of.240.characters";
    public static String DESCRIPTION_SIZE_2_250 = "description.field.should.have.minimum.of.10.characters.and.maximum.of.250.characters";
    public static String DESCRIPTION_SIZE_10_250 = "description.field.should.have.minimum.of.10.characters.and.maximum.of.250.characters";
    public static String DESCRIPTION_SIZE_1_250 = "description.field.should.have.minimum.of.1.characters.and.maximum.of.250.characters";
    public static String DESCRIPTION_SIZE_0_250="description.field.should.have.maximum.of.250.characters";
    public static String BUSINESS_DOMAIN_VALUE_ALL= "business.domain.value.all";
    public static String MANDATE_TEXT = "text.field.can.have.maximum.only.upto.3000.characters";
    public static final  String EMPTY_STRING = "";
    //For contract reference validation ""
    public static final  String BENEFIT_LINE = "lines";
    public static final  String QUESTION = "questions";
    public static final  String PRODUCT_QUESTION = "product";
    
    public static final  String EMPTY_REFERENCE = "reference.empty";   
    
    public static final String DUPLICATE_QUESTION_ADDED = "duplicate.question.added";
    
    public static final String  NO_QUESTION = "no.questions.selected";
    
    public static String SECONDARY_CODE_VALIDATION = "please.enter.secondary.code";
    
    public final static String PANEL_WIDTH_PS = "930";

    public final static String PANEL_WIDTH_PS_VIEW = "600";

    public static String ADMINOPTION_MANDATE_TYPE_MESSAGE = "adminoption.mandatory.type";
    
	public static String MANDATE_TERM_REQUIRED = "mandate.term.required";
	
	public static String MANDATE_QUALIFIER_REQUIRED = "mandate.qulaifier.required";
	
	public static String MANDATE_PVA_REQUIRED = "mandate.pva.required";
	
	public static String INVALID_EFFECTIVE_DATE = "invalid.effective.date";
	
	public static String INVALID_EXPIRY_DATE = "invalid.expiry.date";
	
	public static String EXPIRY_GREATER_EFFECTIVE_DATE = "please.enter.correct.date.comparison";
	
	public static String INVALID_DESCRIPTION = "please.enter.description";
	
	public static String INPUT_FORMAT_INVALID = "input.format.invalid";
    
	public static String  EFFECIVE_GRATER_EXPIRY ="effective.greater.expiry";
	
	public static String INVALID_DATA="please.enter.all.required.information";
	
	public static String SEQUENCE_NUMBER_INPUT = "sequence.number.input.validation";
	
	public static String INVALID_CITATION_NUMBER = "invalid.citation.number";
	
	public static String INVALID_BENEFIT_PLAN_NAME = "please.enter.benefit.plan.name";
	public static String MAX_PROVIDER_SPEC_CODE = "contract.datesegment.maximumProviderSpecCode";
	
	public static String INVALID_BRAND_NAME = "please.enter.brand.name";
	public static String INVALID_PRODUCT_CODE_DESC = "please.enter.prodCodeDesc.name";
	
	public static String INVALID_NAME = "please.enter.name";
	public static String NAME_SIZE_INVALID = "please.enter.name";
	//	 Benefit Component Entry : Start
	public static String BENEFIT_COMPONENT_SESSION_KEY = "benefitComponent";
	public static String BENEFIT_COMPONENT_NON_ADJ_BNFT_MNDT = "nonAdjBenefitMandate";
	public static String BNFT_CMPNT_NON_ADJ_BNFT_MNDT_VIEW = "nonAdjBenefitMandateView";
	public static String BENEFIT_COMPONENT_ID = "SESSION_BNFT_ID";
	public static String BENEFIT_COMPONENT_DELETE_SUCCESS ="benefit.component.delete.success";
	public static String BENEFIT_COMPONENT_DELETE_FAILURE ="benefit.component.delete.failure";
	public static String BENEFIT_COMPONENT_CHECKEDOUT_SUCCESS ="benefit.component.checked.out.success.info";
	public static String BENEFIT_COMPONENT_CHECKEDOUT_FAILURE ="benefit.component.checked.out.failure.info";
	public static String BENEFIT_COMPONENT_PUBLISH_SUCCESS ="benefit.component.publish.success.info";
	public static String BENEFIT_COMPONENT_CHECKIN_SUCCESS ="benefit.component.checkin.success";
	public static String BENEFIT_COMPONENT_CHECKIN_FAILURE ="benefit.component.check.in.failure.info";
	public static String BENEFIT_COMPONENT_CREATE_BREADCRUMB ="Product Configuration >> Benefit Component >> Create";
	public static String BENEFIT_COMPONENT_BREADCRUMB = "Product Configuration >> Benefit Component";
	public static String BENEFIT_COMPONENT_View = "View";
	public static int MAX_SEARCH_RESULT_SIZE = 50;
	public static String VIEW = "VIEW";
	public static String VIEW_ALL = "viewAll";
	public static String ALL_LEVEL_HIDE = "atleast.one.level.required";
	
	//Action setting
	
	public static int ACTION_ONE = 1;
	public static int ACTION_TWO = 2;
	public static int ACTION_THREE = 3;
	public static int ACTION_FOUR = 4;
	public static int ACTION_FIVE = 5;
	
	//Blue Exchange
	int LOCATE_SERVICE_MAPPING = 1;
	int RETRIEVE_SERVICE_MAPPING_DF = 2;
	
	// Benefit Component Entry : End
	
	// Question Component Entry : Start
	public static String ADMIN_OPTION_SESSION_KEY ="adminOption";
	
	public static String ADMIN_CREATE_BREADCRUMB ="Administration >> Administration Option >> Create" ;
	
	public static String ANSWER_EXIST = "answer.already.exist.info";
	
	public static String QUESTION_MANAGED_BEAN = "saveQuestionBackingBean";

	public static String EFFECTIVEDATE_NOT_GRETER_THAN_DEFAULTDATE = "effective.date.should.greater.than.current.date";

	public static String EXPIRYEDATE_NOT_GRETER_THAN_DEFAULTDATE = "expiry.date.should.greater.than.current.date";
	
	// Question Component Entry : End
	
	
	// Standard Benefit Entry - start
	
	public static String STANDARD_BNFT_KEY = "STANDARD_BNFT_KEY";
	public static String STANDARD_BENEFIT_SESSION_KEY = "standard";
	public static String BENEFIT_COMPONENT_SEARCH_SESSION_KEY = "benefitComponent";
	public static String PRODUCT_STRUCTURE_SESSION_KEY = "productStructure";
	//Admin Options under standard benefit
	public static String SESSION_BNFT_DEFN_ID = "SESSION_BNFT_DEFN_ID";
	public static String SESSION_BNFT_ADMIN_ID = "SESSION_BNFT_ADMIN_ID";	
	public static String SESSION_BNFT_ADMIN_DESC = "SESSION_BNFT_ADMIN_DESC";
	public static String BENEFIT_COMP_NAME = "BENEFIT_COMP_NAME";
	public static String BNFT_CMPNT_KEY = "BNFT_CMPNT_KEY";
	final String BENEFIT_MANDATE_DELETED = "Benefit Mandate Deleted Successfully.";
	final String BENEFIT_MANDATE_SAVED = "Benefit Mandate Saved Successfully.";
	public static String LOAD_BENEFIT_MANDATE ="loadBenefitMandate"; //Previously it was LoadBenefitMandate
	public static String LOAD_BENEFIT_MANDATE_VIEW ="loadBenefitMandateView";
	public static String LOAD_COMPONENT_BENEFIT_MANDATE_VIEW = "loadComponentBenefitMandateView";
		public static String LOAD_BENEFIT_NOTES_VIEW="loadBenefitNotesView";
	public static String HEADER = "Product Configuration >> Benefit";
	public static String LOAD_BENEFIT_DEFINITION = "benefitDefinitionCreate";
	public static String LOAD_BENEFIT_DEFINITION_VIEW = "benefitDefinitionView";	
	public static String SCHEDULE_FOR_TEST_BEN = "benefit.schedule.for.test.success";
	public static String BENLEVEL_POPUP_NO_RESULTS = "benefitlevel.popup.results.not.found";
	public static String TEST_PASS_BEN = "benefit.test.pass";
	public static String TEST_FAIL_BEN = "benefit.test.fail";
	public static String ALL_99 = "ALL~ALL";
	public static String MEDICAL ="Medical~MED";
	public static String BENEFIT_KEY = "benefitkey";
	public static String STANDARD_BENEFIT_CREATE_BREADCRUMB = "Product Configuration >> Benefit >> Create";
	public static String NOTES_CREATE_BREADCRUMB = "Notes Library >> Note >> Create";
	public static String NOTE_TEXT_VALIDATE_MESSAGE ="notetext.field.validate";
	public static String NOTE_NAME_VALIDATE_MESSAGE ="notename.field.validate";
	public static String BENEFIT_COMPONENT_EDIT = "benefitComponentEdit";
	public static String BENEFIT_COMPONENT_CREATE = "benefitComponentCreate";
	public static String BENEFIT_COMPONENT_COPY = "benefitComponentCopy";
	public static String MANDATE_DEFINITION = "Mandate Definition";
	public static String STANDARD_DEFINITION = "Standard Definition";
	// Standard Benefit Entry - end
	 //For Product Structure
	public static String NO_ADMN_OPTION = "no.admin.option";
   //  Catalog Entry : Start
	public static String CATALOG_EDIT_FORWARD_STING = "catalogEdit";
	
	public static String ACTION_STRING = "action";
	
	public static String VIEW_STRING = "view";
	
	public static String CATALOG_ID_STRING = "CATALOG_ID";
	
	public static String SUB_CATALOG_ID_STRING = "SUB_CATALOG_ID";
	
	public static String SUB_CATALOG_NAME_STRING = "SUB_CATALOG_NAME";
		
	public static String PRINT_STRING = "print";
	
	public static String VIEW_LOAD_STRING = "viewLoad";
	
	public static String VIEW_SESSION_STRING = "viewSession";
	
	public static String ACTION_VIEW_STRING = "actionView";
	
	public static String PRIMARY_CODE_STRING ="PRMRY_CODE";
						
	public static String CATALOG_NAME_SIZE = "catalog.name.size";
	
	public static String CATALOG_SIZE_INVALID = "catalog.size.invalid";
	
	public static String CATALOG_NAME_INVALID  = "catalog.name.invalid";
	
	public static String CATALOG_SIZE_ZERO = "catalog.size.zero";
	
	public static String PRIMARY_CODE_INVALID = "primary.code.invalid";
	
	public static String SEARCH_BLANK_FIELDS = "blank.fields.search";
	
	public static String SEARCH_RESULT_EXCEEDS = "search.result.exceeds";
	
	public static String SEARCH_RESULT_NOT_FOUND = "search.result.not.found";
	
	public static String ROOT = "root";
	
	public static String CUSTOM = "CUSTOM";
	
	public static String REQUEST_HEADER_RULE_ID = "headerRuleId";
	
	public static String REQUEST_SPS_ID = "spsId";

	public static String REQUEST_PLACE_OF_SERVICE = "placeOfService"; 
	
	public static String REQUEST_SERVICE_TYPE_CODE = "eb03Identifier";
	
	public static String REQUEST_SERVICE_TYPE_CODE_DESC = "serviceTypeCodeDesc";
	
	public static String REQUEST_PLACE_OF_SERVICE_DESC = "posDesc";
	
	public static String SESSION_OBJECT_NAME = "customMessageSession";
	
	public static String SERVICE_TYPE_SESSION_OBJECT_NAME = "serviceTypeMappingSession";

	public static String MODEL = "MODEL";
	
    public static String STANDAR = "STANDARD";
    
	public static String PRODUCTID ="PRODUCTID";
	
	public static String SELECTED_LINE_LIST = "selectedLineList";
	
	public static String UNSELECTED_LINE_LIST = "unSelectedLineList";
	
	public static String EDIT_PAGE ="contractBasicInfoEdit";
		    
	public static   final String PROD_STRUCTURE = "Structure";
	
	public static final String FALSE = "false";
	public static final String TRUE = "true";
	    
	public static   String ZERO_STRING = "0";
	    
	public static  String PRODUCT_STRUCTURE = "ProductStructure";
	    
	public static String ACTION_INDICATOR = "actionIndicator";

	public static String MANDATE_DATATYPE_REQUIRED = "benefit.mandate.datatype.required";
    
    public static String ATLEAST_ONE_SEARCH ="all.fields.blank";
    

	public static final String INDICATIVE_MAPPING_CREATE_SUCCESS = "indicative.map.create.success";
	public static final String REFERENCE_MAPPING_CREATE_SUCCESS = "reference.map.create.success";
	public static final String ADMIN_MAPPING_CREATE_SUCCESS = "adminmethod.mapping.create.success";
	
	
	public static final String REFERENCE_MAPPING_EDIT_SUCCESS = "reference.map.edit.success";
	
	
	public static final String CONTRACT_RULE_VALID_FAIL = "contract.rule.datesegment.valid";
	
	
	
    
	public static final String INDICATIVE_MAPPING_CREATE_VALID = "indicative.map.mandatory.valid";
	
	public static final String REFERENCE_MAPPING_CREATE_VALID = "reference.map.mandatory.valid";
	
	public static final String REFERENCE_MAPPING_LINE_VALID = "reference.map.line.mandatory.valid";
	
	public static final String AGGREAGET_TERM_VALID = "reference.map.term.mandatory.valid";
	public static final String AGGREAGET_QUALIFIER_VALID = "reference.map.qualifier.mandatory.valid";
	
	
	
	public static final String CONTRACT_RULE_VALID = "contract.rule.mandatory.valid";
    
	public static final String INDICATIVE_MAPPING_CREATE_UNIQUE_VALID = "indicative.map.mandatory.unique.valid";
	
	public static final String REF_MAPPING_CREATE_UNIQUE_VALID = "ref.map.mandatory.unique.valid";
	
	
	
	public static final String INDICATIVE_MAPPING_DESC_LIMIT = "description.field.limit";
	
	public static final String INDICATIVE_MAPPING_SAVE_SUCCESS = "indicative.map.save.success";
	

    public static String DEFAULT_EXP_DATE = "12/31/9999";
    
    public static String DEFAULT_EFF_DATE = "01/01/1900";

	public static String DATA_TYPE_MISMATCH = "dataType.mismatch.error";

	public static String BENEFIT_HIERARCHY_CREATE = "mandate.benefitHierarchy.create.success";

	public static String BENEFIT_HIERARCHY_UPDATE = "mandate.benefitHierarchy.update.success";
	
	public static String BENEFIT_NOTES_UPDATE = "mandate.notes.update.success";

	public static String BENEFIT_HIERARCHY_DELETE = "mandate.benefitHierarchy.delete.success";

	public static String BENEFIT_HIERARCHY_SEARCH_FAILURE = "mandate.benefitHierarchy.searchresult.notfound";

	public static String MANDATE_SEQUENCE_REQUIRED = "mandate.sequence.required";

	public static String MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED = "mandate.benefitleveldescription.required";
	
	public static String NOT_VALID_DATE = "not.valid.date";
	
	public static String BENEFIT_PUBLISH = "benefit.publish";
	
	public static String BENEFIT_COMPONENT_INVALID_NAME = "please.enter.name.length";
	
	public static String EFFECTIVE_DATE_INVALID = "effective.date.not.within.boundary";

    public static Object GENERAL_BENEFITS = "GENERAL BENEFITS";
    
    public static Object GENERAL_PROVISIONS = "GENERAL PROVISIONS";
    
    public static String DATA_TYPE_MISMATCH_BENEFIT_LEVEL = "dataType.mismatch.error.benefit.level";
    
    public static String INVALID_MANDATE_NAME_LENGTH ="mandate.name.length";

	public static String INVALID_MANDATE_DESCRIPTION = "mandate.name.length";

	public static String MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH = "benefitleveldescription.lessthan32";
	
	public static String PRIMARYCODE_SIZE_INVALID= "item.size.invalid";
	
	// Note Attachment
	public static String MANDATORY_FIELDS_NOTES_REQUIRED="mandatory.fields.notes.required";	
	
	//Sub Catalog
	public static String ASSOCIATED_ITEM="associatedItem";
	
	public static String LOAD_ITEM_FOR_VIEW="loadItemForView";
	
	public static String SUBCATALOG_GENINFO_VIEW="subCatalogGenInfoViewPage";
	
	public static String ITEM_EDIT="itemEdit";
	//ComponentType
	public static String STD_TYPE = "STANDARD";
	
	public static String MNDT_TYPE = "MANDATE";
	//public static String MODE = "Mode";
	
	//Note Module
	public static String AVAIL_PRODUCT = "AVAILPRODUCT";
	
	public static String AVAIL_BENEFIT_COMP = "AVAILCOMP";
	
	public static String AVAIL_BENEFIT = "AVAILBENEFIT";
	
	public static String AVAIL_TERM = "AVAILTERM";
	
	public static String AVAIL_QUALIFIER = "AVAILQUALIFIER";
	
	public static String ATTACH_CONTRACT = "ATTACHCONTRACT";
	
	public static String ATTACH_PRODUCT = "ATTACHPRODUCT";
	
	public static String ATTACH_BENEFIT_COMP = "ATTACHCOMP";
	
	public static String ATTACH_BENEFIT = "ATTACHBENEFIT";
	
	public static String ATTACH_BENEFIT_LINE = "ATTACHBNFTLINE";
	
	public static String CORPORATE_PLAN_CODE_REQUIRED = "corporate.plan.code.required";
	
	public static String CORPORATE_PLAN_CODE_REQUIRED_CHECKIN = "corporate.plan.code.required.checkin";
	
	public static String APPROVE_BEN = "benefit.approved";
	public static String REJECT_BEN = "benefit.rejected";
	
	public static String NO_HEADINGS_FOUND = "no.headings.found";
    
    public static String BENEFIT_VIEW = "View";

    public static String DATE_1900 = "01/01/1900";

    public static String BOOLEAN = "boolean";

    public static String BOOLEAN_NEW = "BOOLEAN";

    public static String DOLLAR = "DOLLAR";

    public static String PERCENTAGE = "PERCENTAGE";

    public static String INTEGER = "INTEGER";

    public static String FLOAT = "FLOAT";

    public static String EDIT_STRUCTURE = "edit";
	
    public static String DELETE_STRUCTURE = "delete";
	public static String MANDATE = "Mandate";
	
	public static String MANDATE_TYPE = "MANDATE";
	
	public static String FEDERAL_TYPE = "FED";
	public static String STATE_TYPE = "ST";
	public static String EXTRA_TERRITORIAL_TYPE = "ET";
    public static String BENEFIT_BREADCRUMB = "Product Configuration >> Benefit";

    public static String BENEFIT_LEVEL_BREADCRUMB = "Product Configuration >> Benefit(";

    public static String LOAD_BENEFIT_LINE_NOTES = "loadBnftLineNotesAttachmentPage";

    public static String LOAD_BENEFIT_NOTES_DETAILS = "loadNotesDetailedPage";
    public static String VIEW_ALL_VERSIONS_BREADCRUMB = "Product Configuration >> Benefit >> View All Versions";
    public static String MODE = "Mode";
	public static String STD_TYPE_DISPLAY = "Standard";
	public static String MNDT_TYPE_DISPLAY = "Mandate";

    public static String STANDARD_BENEFIT_EDIT = "standardBenefitEdit";

    public static String EXTRA_TERRITORIAL = "Extra-Territorial";

    public static String FEDERAL = "Federal";

    public static String STATE_ST = "State";

    public static String STANDARD_BENEFIT_VIEW = "standardBenefitView";

    public static String STANDARD_BENEFIT_CREATE = "standardBenefitCreate";

    public static String STANDARD_BENEFIT_STANDARD = "Standard Definition";

    public static String STANDARD_BENEFIT_MANDATE = "Mandate Definition";

    public static String LOAD_SB_FOR_NOTES = "loadStandardBenefitForNotes";

    public static String SCHEDULE_FOR_TEST = "SCHEDULE_TEST";

    public static String TEST_PASS = "TEST_PASS";

    public static String TEST_FAIL = "TEST_FAIL";

    public static String PUBLISH = "PUBLISH";

    public static String APPROVE = "APPROVE";

    public static String REJECT = "REJECT";
    
    public static String UNLOCK_BENEFIT = "UNLOCK_BENEFIT";
    
    public static String BENEFIT_UNLOCK = "unlock.benefit";
    
    public static String VIEW_ALL_VERSIONS = "standardBenefitViewAllVersions";

    public static String STANDARD = "standard";

    public static String BENEFIT_ADMINISTRATION = "benefitAdministration";

    public static String BENEFIT_ADMINISTRATION_VIEW = "benefitAdministrationView";
    
    public static String SESSION_ADMIN_OPTN_ASSN = "SESSION_ADMIN_OPTN_ASSN";
    public static String SESSION_ADMIN_OPTN_ID = "SESSION_ADMIN_OPTN_ID";
    public static String PRODUCT_SESSION_ADMIN_OPTN_ID = "SESSION_ADMIN_BNFT_SYS_ID";

    public static String PRODUCT_SESSION_ADMIN_LVL_OPTN_ID = "ADMN_LVL_ASSC_ID";
    

    public static String SESSION_BNFT_DEFN_DESC = "SESSION_BNFT_DEFN_DESC";

    public static String BENEFIT_NAME = "benfitNm";

    public static String BENEFIT_LEVEL_PRINT = "benefitLevelPrint";

    public static String STANDARD_BENEFIT_SEARCH_BREADCRUMB = "Product Configuration >> Benefit >> Locate";
    public static String DEFAULT_FEDERAL_FIELD = "ALL~ALL~E";
    public static String BENEFIT_VIEWALL_RESULT = "standardBenefitViewAllVersionsSearchResult";

    public static String BENEFIT_SEARCH_RESULT = "standardBenefitSearchResult";

    public static String BENEFIT_DELETE_SUCCESS = "standard.benefit.delete.success";
    public static String VIEW_ALL_VERSION_TO_COPY = "fromViewAllVersionToCopy";

    public static String VIEW_ALL_VERSION_TO_EDIT = "fromViewAllVersionToEdit";

    public static String VIEW_ALL_VERSION_TO_CHECKOUT = "fromViewAllVersionToCheckOut";

    public static String BENEFIT_SEARCH_SUCCESS = "standardbenefitsearchsuccess";

    public static String STANDARD_BENEFIT = "Standard Benefit(";

    public static String BENEFIT_LINE_ID = "bnftLineId";

    public static String NULL = "null";

    public static String VIEW_VERSION_TYPE = "viewVersion";

    public static String STANDARD_TYPE = "Standard";
    
    public static String BE_REQ_PARAM_NAME = "be";
    
    public static  String BG_REQ_PARAM_NAME = "bg";
	/*START CARS*/
    public static String MBU_REQ_PARAM = "mbu";
	/*END CARS*/
    public static  String PROD_PAR_SYS_ID = "prodParSysId";
    
    public static String EFF_DATE_REQ_PARAM_NAME = "effDate";
    
    public static String EXP_DATE_REQ_PARAM_NAME = "expDate";
    
    public static String PRODUCT_TYPE = "productType";
    
    public static final String MANDATE_TYPE_NAME = "mandateType";
    
    public static String STATE_CODE = "stateCode";
    
    public static String CONST_T = "T";
    
    public static String CONST_F = "F";
    
    //ROLE ENTRY STARTS
    public static String ROLE_EDIT="roleEdit";
    public static String TASK_DETAILS_PAGE="taskDetailsPage";
    public static String ROLE_CONFIGURATION_VIEW_PAGE="roleConfigurationViewPage";
    public static String LOAD_GENERAL_INFORMATION_VIEW="loadGeneralInformationView";
    public static String LOAD_ROLE_CONFIGURATION = "loadRoleConfiguration";
    public static String LOAD_SUB_TASK_CONFIGURATION_VIEW = "loadSubTaskConfigurationView";
    public static String LOAD_TASK_ASSOCIATION_VIEW="loadTaskAssociationView";
    //MODULE ENTRY STARTS
    public static String MODULE_EDIT="moduleEdit";
    public static String MODULE_DETAIL_PAGE="moduleDetailPage";
    public static String LOAD_MODULE_CONFIGURATION="loadModuleConfiguration";
    public static String LOAD_MODULE_CONFIGURATION_VIEW="loadModuleConfigurationView";
    public static String VIEW_MODULE="viewModule";
    public static String VIEW_MODULE_DETAIL_PAGE= "viewModuleDetailsPage";
    public static String VIEW_TASK_DETAIL_PAGE="viewTaskDetailPage";
    public static String MODULE_MAIN_PAGE="moduleMainPage";
	//Task entry starts
    public static String TASK_EDIT = "taskEdit";
    
    public static String ADMIN_OPTION = "ADMINOPTION";
    
    public static String PRODUCT = "PRODUCT";
    
    public static String RULE_TYPE = "ruleType";
    
    public static String RULE_ID = "ruleID";
    
    public static String FLAG_N = "N";
    
    public static String MANDATE_INFO_VIEW = "MANDATE_INFO_VIEW";
    
    public static String BENEFIT_COMP = "BENEFITCOMP";
    
    public static String BENEFIT_COMP_ID = "benefitComponentId";
    
    public static String PROD_STRUCT= "ProdStructure";
    
    public static String OVERRIDE_CONST_F = "F";
    
    public static String BENEFIT_TYPE = "BENEFIT";
    
    public static String PROD_TYPE = "product";
    
    public static String PROD_STRUCTURE_TYPE = "productStructure";
    
    public static String TO_MAKE_NOT_EMPTY = "toMakenotEmpty";
    
    public static String PRODUCT_KEY = "productKey";
    
    public static String CONTRACT_ADMIN_KEY ="adminKey";
    
    public static String TERM = "term";
    
    public static String QUALIFIER = "qualifier";
    
    public static String PROVIDER_ARRANGEMENT = "provider arrangement";
    
    public static String PRODUCT_TREE_BACKING_BEAN = "productTreeBackingBean";
    
    public static String PRODUCT_STRUCT = "PRODSTRUCTURE";
    
    
    /** CONTRACT */
    
    public static String ACTION = "action";
    
    public static String RETRIEVE_CONTRACT_ADAPTED_INFO = "retrieveContractAdaptedInfo";
    
    public static String RETRIEVE_CONTRACT_PRODUCT_GENERAL_INFO = "retrieveContractProductGeneralInfo";
    
    public static String RETRIEVE_CONTRACT_SPECIFIC_INFO = "retrieveContractSpecificInfo";
    
    public static String RETRIEVE_CONTRACT_PRICING_INFO =  "retrieveContractPricingInfo";
    
    public static String RETRIEVE_COMMENT = "retrieveComment";
    
    public static String RETRIEVE_ALL_COMMENT = "retrieveAllComment";
    
    public static String RETRIEVE_SELECTED_COMMENT = "retrieveSelectedComment";
    
    public static String DATESEGMENT_ID = "dateSegmentId";
    
    public static String CONTRACT_KEY = "contractKey";
    
    public static String NO_LIMIT = "NoLimit";
    
    public static String COMMENT_BO = "CommentBo";
    
    public static String CONTRACT = "contract";
    
    public static String CONTRACT_PRODUCT_ADMIN = "CONTRACT";
    
    public static String STD_BENEFIT = "stdbenefit";
    
    public static String BENEFIT_COMPONENT_NAME_SET = "aa";
    
    public static String STANDARD_BENEFIT_NAME_SET = "kk";
    
    public static String CONTRACT_UPDATED = "Contract updated";
    
    public static String PRODUCT_RULE_SYSID = "prodRuleSysId";
    
    public static String DS_SYS_ID = "dsSysId";
    
    public static String RULE_GEN_ID = "ruleGenId";
    
    public static String PROD_SYS_ID = "prodSysId";
    
    public static String GET_INITIAL_CONTRACT = "getInitialContracts";
    
    public static String GET_MAX_SYS_ID = "getMaxSysId";
    
    public static String GET_BASE_CONTRACT = "getBaseContracts";
    
    public static String GET_BASE_CONTRACT_FOR_MIGRATION = "getBaseContractsForMigration";
    
    public static String GROUP = "group";
    
    public static String ENTITY = "entity";
    /*START CARS*/
    public static String MARKET_BUSINESS_UNIT = "marketBusinessUnit";
    
    public static String MARKET_BUSINESS_UNIT_SEARCH = "marketBusinessUnitToSearch";
    /*END CARS*/
    
    public static String GET_RESERVED_CONTRACT = "getReservedContracts";
    
    public static String CONTRACT_SYS_ID = "contractSysId";
    
    public static String CONTRACT_ID = "contractId";
    
    public static String ENTITY_SYS_ID = "entitySysId";
    
    public static String COUNT = "count";
    
    public static String CONTRACT_MODIFICATION_IND = "contractModificationInd";
    
    public static String GET_BASE_CONTRACT_DATES = "getBaseContractDates";
    
    public static String GET_RESERVE_CONTRACT_BY_ID =  "getReserveContractById";
    
    public static String CONTRACT_BO = "com.wellpoint.wpd.common.contract.bo.Contract";
    
    public static String COUNT_DATE_SEGMENT = "countDateSegment";
    
    public static String GET_DATE_SEGMENT = "getDateSegments";
    
    public static String  GET_ALL_VERSION = "getAllVersions";
    
    public static String TYPE = "type";
    
    public static String START_DATE = "startDate";
    
    public static String ENDDATE = "endDate";
    
    public static String COVERAGE = "coverage";
    
    public static String PRICING = "pricing";
    
    public static String NETWORK = "network";
    
    public static String GET_DUPLICATE_PRICING_INFO = "getDuplicatePricingInfo";
    
    public static String CONTRACT_DATESEGMENT_SYS_ID = "contractDateSegmentSysId";
    
    public static String GET_CONTRACT_PRICING_INFO = "getContractPricingInfo";
    
    public static String RETRIEVE_LATEST_PRODUCT_VERSION = "retrieveLatestProductVersion";
    
    public static String RETRIEVE_PRODUCT_LATEST_VERSION = "retrieveProductLatestVersion";
    
    public static String RETRIEVE_CURRENT_PRODUCT_FOR_CONTRACT = "retrieveCurrentProductForContract";
    
    public static String RETRIEVE_CURRENT_NOTE_FOR_CONTRACT = "retrieveCurrentNoteForContract";
    
    public static String CHECK_MARKED_FOR_DELETION_NOTES = "checkMarkedForDeletionNotes";

    public static String EWPD = "ewpd";
    
    public static String DATE_SEGMENT_SYS_ID = "dateSegmentSysId"; 
    
    public static String OLD_DATE_SEGMENT_SYS_ID = "oldDateSegmentSysId"; 
    
    public static String GET_CONTRACT_COMMENT = "getContractComment";
    
    public static String USER = "user";
    
    public static String LAST_UPDATED_TIME = "lastUpdatedTime"; 
    
    public static String DESTINATION_DATE_SEGMENT_ID = "destinationDateSegmentId";
    
    public static String COPY_DATE_SEGMENT = "copyDateSegment";
    
    public static String COPY_DATE_SEGMENT_CHECKOUT ="copyDateSegmentCheckOut";
    
    public static String SOURCE_KEY = "sourceKey";
    public static String EB03_IDENTIFIER = "eb03Identifier";
    
    public static String DESTINATION_KEY = "destinationKey";
    
    public static String COPY_CONTRACT = "copyContract";
    
    public static String DELETE_FOR_CHECKINCONTRACT = "deleteForCheckIn";
    
    public static String GET_REAERVED_CONTRACT_ID = "getReservedContractId";
    
    public static String CONTRACT_DATE_SEGMENT_SYS_KEY = "contractDateSegmentSysKey";
    
    public static String GET_ADMIN = "getAdmin";
    
    public static String RETRIEVE_ALL_LATEST_ADMIN_OPTION = "retrieveAllLatestAdminOptons";
    
    public static String RETRIEVE_ADMIN_SEQUENCE ="getAdminSequence";
    
    public static String INSERT_ADMIN_NOTES_SEQUENCE ="attachAdminOptionFromProduct";
    
    public static String BENEFIT_ID = "benefitId";
    
    public static String RETRIEVE_RULE_ID = "retrieveRuleId";
    
    
    public static String STATE_DESC = "stateDesc";
    
    public static String RETRIEVE_STATE_CODE = "retrieveStateCode";
    
    public static String RETRIEVE_CONTRACT_ID = "retrieveContractId";
    
    public static String RETRIEVE_PROD_PAR_SYS_ID = "retrieveProductParentSysId";
    
    public static String PRODUCT_ID = "productId";
    
    public static String PRODUCT_NAME = "productName";
    
    public static String RETRIEVE_PRODUCT_CODE = "retrieveProductCode";
    
    public static String RETIEVE_EXIT_CONTRACTS = "retrieveExistingContracts";
    
    public static String RETRIEVE_SCHEDULED_CONTRACTS = "retrieveScheduledContracts";
    
    public static String STATUS_DESC = "status";
    
    public static String DT_SGMNT_MDFD = "dtSgmntMdfd";
    
    public static String RETRIEVE_SCHEDULE_MANDATE_CONTRACTS = "retrieveScheduledMandateContracts";
    
    public static String SOURCE_PRODUCT_ID = "sourceProductId";
    
    public static String COPY_PRODUCT_DEFAULT_COMPONENTS = "copyProductDefaultComponents";
    
    public static String MIN_EFFECTIVE_OF_LATEST_CONTRACT = "minEffectiveOfLatestContract";
    
    public static String MAX_EFFECTIVE_OF_MIGRATING_CONTRACT = "maxEffectiveOfMigratingContract";
    
    public static String STANDARD_BENEFIT_ID = "standardBenefitId";
    
    public static String BENT_LINE_ID = "benefitLineId";
    
    public static String SOURCE_ID = "sourceId";
    
    public static String UPDATED_CONTRACT_NOTES = "updateContractNotes";
    
    public static String CNTRCT_BNFT_COPY ="copyContractBenefits";
    
    public static String EFFECTIVE_DATE_FOR_COPY = "effectiveDateForCopy";
    
    public static String GET_EFFECTIVE_DATE_SEGMENT_ID = "getEffectiveDateSegmentId";
    
    public static String  MARKED_FOR_DELETION = "MARKED_FOR_DELETION";
    
    public static String  COPY_PRODUCT_COMPONENT = "copyProductComponents";
    
    public static String  REPLACE_PRODUCT_COMPONENT = "replaceProductComponents";
    
    public static String  INVALID_DATE_SEGMENT_ENTRY = "invalid.Date.Segment.Entry";
    
    public static String ACTION_BENEFIT = "1";
    
    public static String ACTION_OVERRIDE = "2";
    
    public static String BUSINESS_DOMAIN_CHANGED_PLEASE_CHANGE_STATE = "business.domain.changed.please.change.state";
    
    public static String STANDARD_TYPE_CAP = "STANDARD";
    
    /** Migration Wizard starts here ****/
	 
	 //MIGRATION ENTRY STARTS
	 public static String MANDATORY_FIELDS_MIGRATION_PRODUCT_REQUIRED="mandatory.fields.migration.product.required";	
	 public static String UNKNOWN_ERROR_MIGRATION_PRODUCT="Error occured while persisting values to repository.";
	// public static String  STEP_SEVEN_SHOULD_PROCESS = "step.seven.should.process";
	 public static String  STEP_THREE_SHOULD_PROCESS = "step.three.should.process";
	 public static String  STEP_FOUR_SHOULD_PROCESS = "step.four.should.process";
	 public static String  STEP_FIVE_SHOULD_PROCESS = "step.five.should.process";
	 public static String  STEP_SIX_SHOULD_PROCESS = "step.six.should.process";
	 public static String  STEP_SEVEN_SHOULD_PROCESS = "step.seven.should.process";
	 public static String  STEP_EIGHT_SHOULD_PROCESS = "step.eight.should.process";
	 public static String  PRICING_DUPLICATE_RECORD = "pricing.duplicate.record";
	 public static int ALLOWED_NUMBER_OF_PRECISION = 2;
   
    
    public static final String STEP_CONTRACT 	 = "Step1 - Contract";
    public static final String STEP_DATESEGMENT  = "Step2 - Date Segment";
    public static final String STEP_GENERAL_INFO = "Step3 - General Information";
    public static final String STEP_PRICING_INFO = "Step4 - Pricing Information";
    public static final String STEP_PRODUCTS 	 = "Step5 - Product Information";
    public static final String STEP_DENIAL_EXCL  = "Step6 - Rules";
    public static final String STEP_PROD_MAPPING = "Step7 - Benefit Mapping";
    public static final String STEP_MIGRATE_NOTES= "Step8 - Migrate Notes";
    public static final String STEP_REPORT_GEN 	 = "Step9 - Report Generation";
    public static final String STEP_CONFIRM_MIG  = "Step10 - Confirm Migration";
    public static final String MIG_CONTRACT_STEP1 ="step1";
    public static final String MIG_CONTRACT_STEP2 ="step2";
    public static final String MIG_CONTRACT_STEP3 ="step3";
    public static final String MIG_CONTRACT_STEP4 ="step4";
    public static final String MIG_CONTRACT_STEP5 ="step5";
    public static final String MIG_CONTRACT_STEP7 ="step7";
    public static final String MIG_CONTRACT_STEP6 ="step6";
    public static final String MIG_CONTRACT_STEP8 ="step8";
    public static final String MIG_CONTRACT_STEP9 ="step9";
    public static final String MIG_CONTRACT_STEP10 ="step10";
    public static final String MIG_CONTRACT_STEP_ALL_DS ="step2AllDS";
    
    

	public static final String MIGRATION = "Migrate";
	public static final String RENEW = "Renew";
	public static final String FROM_DATE = "From Date";
    public static final String BREAD_CRUMB_CONTRACT ="Contract Id : ";
    public static final String TREE_EXP_FLAG ="treeExpandFlag";
    public static final String STEP8_TREE_EXP_FLAG ="step8TreeExpandFlag";
    public static final String SESSION_TREE_STATE ="SESSION_TREE_STATE_MW";
    public static final String MESSAGE_LIST_STEP3 ="MESSAGELIST_STEP3";
    public static final String STD_BENEFIT_MAP ="standardBenefitMap";
    public static final String MIG_WIZARD ="MIGRATIONWIZARD";
    public static final String OVER_RIDE_VAR ="OVERRIDEVARIABLE";
    public static final String CP2000 ="CP2000";
    public static final String CP ="CP";
    public static final String ALL ="ALL";
    public static final String STANDARD_05 ="Standard05";
    public static final String STANDARD_09 ="Standard09";   
    public static final String LEGACY_CONTRACT_BACKING_BEAN = "legacyContractBackingBean";
    public static final String STANDARD_BENEFITS_HEAD = "Standard-Benefits-Head";
    public static final String STEP8_BENEFITS_HEAD = "Benefit-Components-Notes-Head";
    public static final String SESSION_NODE_TYPE = "SESSION_NODE_TYPE";
    public static final String SESSION_BNFT_NAME = "SESSION_BNFT_NAME";
    public static final String MIGRATION_TREE_NEXT = "MIGRATION_TREE_NEXT";
    public static final String MIGRATION_TREE_BACK = "MIGRATION_TREE_BACK";
    public static final String MIGRATION_STEP8_NEXT = "MIGRATION_STEP8_NEXT";
    public static final String MIGRATION_STEP8_BACK = "MIGRATION_STEP8_BACK";
    public static final String NO_GENERALINFO_FILLED = "no.generalinfo.filled";
    public static final String MIGRATION_BREAD_CRUMB_STEP2 = "Administration >> Contract Migration Wizard >> Date Segment (Step 2)";         
    public static final String LATEST_DATE_SEGMENT = "Migrate Latest Date Segment";
    public static final String ALL_DATE_SEGMENT = "Migrate All Date Segments";
    public static final String RENEW_EXISTING_DATE_SEGMENT = "Renew";	
    public static final String LATEST_TWO_DATE_SEGMENT = "Migrate Latest Two Date Segments";
    public static final String MIGRATE = "Migrate";
    public static final String UPDATE_ADMIN_OPTION =  "UPDATE_ADMIN_OPTION";
    public static final String MINOR_HEADING_FROM_SCREEN =  "minorHeadingFromScreen";   
    public static final String NOTES_MIGRATION_PAGE =  "notesMigrationPage";
    public static final String BENEFIT =  "Benefit";
    public static final String BENEFITCOMPONENT =  "BenefitComponent";
    public static final String SAVE_NOTES =  "saveNotes";
    public static final String CONTRACT_NEW =  "Contract";
    public static final String CONTRACT_BREADCRUMB =  "Administration >> Contract Migration Wizard >> Step1";
    public static final String DATE_SEGMENT =  "dateSegment";
    public static final String RENEW_DATE_SEGMENT =  "renewDateSegment";
    public static final String SELECT_DATE_SEGMENT =  "migration.please.select.datesegment";
    public static final String NOTES_SELECT_BUTTON =  "notesSelectButtonHidden";
    public static final String MINOR_HEADINGID =  "minorHeadingId";
    public static final String MINOR_HEADINGID_FROM_SCREEN =  "minorHeadingFromScreen";
    public static final String COMPONENT_NOTES =  "componentNotesSelectButtonHidden";
    public static final String MAJOR_HEADINGID =  "majorHeadingId";
    public static final String NOTE_CHECKBOX =  "noteCheckBoxHidden";
    public static final String CONTRACT_BREADCRUMB_STEP7 =  "Administration >> Contract Migration Wizard >> Benefit Mapping (Step 7)";
    public static final String VARIABLE =  "variable";
    public static final String FORMAT =  "format";
    public static final String PROVIDER =  "provider";
    public static final String REFERENCE =  "reference";
    public static final String DESCRIPTION =  "description";
    public static final String VARIABLEID =  "variableId";
    public static final String VARIABLE_DESC =  "variableDesc";
    public static final String VARIABLEID_FORNOTES =  "variableIdForNotes";
    public static final String VARIABLEDESC =  "variableDescForNotes";
    public static final String NOTECHECKBOXFLAG =  "notesCheckboxFlag";
    
    
    
    
    
    
    
    
    
    
    
    /** Migration Wizard ends here ****/
    
    public static String CONTRACT_COVERAGE = "Coverage";
    
    public static final String SESSION_TREE_STATE_CONTRACT = "SESSION_TREE_STATE_CONTRACT";
    public static int MAX_SEARCH_RESULT_SIZE_CONTRACT = 50;
    
    public static final String NO_ADMIN_METHOD_FOUND = "no.admin.method.found";
    public static final String ADMIN_METHOD_EXCEEDS_FIFTY = "admin.method.exceeds.fifty";
    public static final String GB_ADMIN_METHOD_APPLICABLE = "gb.admin.method.applicable";
    public static final String ADMIN_METHOD_SAVE_SUCCESS = "adminmethod.save.success";   
    public static final String ADMIN_METHOD_TIER_SAVE_SUCCESS = "adminmethod.tier.save.success"; //CARS AM2
    public static final String NO_SPS_FOR_TERMS_IN_TIER = "No Processing Methods found for the terms in this tier criteria."; //CARS AM2
    
    public static String RETRIEVE_RULE_ID_DATAFEED = "retrieveRuleIdDatafeed";
    
    public static String BENEFIT_ID_LIST = "benefitIdList";
    public static String ENTITY_ID_FOR_CHECKIN = "entityIdForCheckIn";
    public static String CONTRACT_ID_FOR_CHECKIN = "prodIdForCheckIn";
    public static String ENTITY_TYPE_FOR_CHECKIN = "entityTypeForCheckIn";
    public static String OBJECT_KEY_FOR_CHECKIN = "object_key";
    public static String OBJECT_VALUE_FOR_CHECKIN = "object_value";
    public static String ACTION_FOR_CHECKIN = "actionForCheckin";
    public static String CHECK_IN_VALID_SUCCESS_CHECK_FAIL = "admin.method.entity.checkin.validation.status.check.failed";
    
    public static String GET_DUPLICATE_ADMIN_REF = "getDuplicateAdminRef";
    public static String GET_DUPLICATE_BENEFIT_LINE_REF = "duplicateBenefitLineRef";
    public static String GET_DUPLICATE_ADMIN_QUEST_REF = "duplicateAdminQuestRef";
    public static String GET_DUPLICATE_ALL_BENEFIT_REF = "getDuplicateAllBenefitRef";
    public static String GET_DUPLICATE_ALL_QUEST_REF = "getDuplicateAllQuestionRef";
    
    
    public static String licensekey = (SpellcheckProperties
			.getSpellCheckProperties()).get("licensekey")
			.toString();
    public static String jndiName = (SpellcheckProperties
			.getSpellCheckProperties()).get("jndiName")
			.toString();
    
    public static String SESSION_DELETED_RULES_LIST = "deletedRulesList";
    public static String SESSION_UNCODED_RULES_LIST = "unCodedRulesList";
    //sscr 17571
    public static String SESSION_NOTANS_VENDOR_LIST = "notAnsVendorList";
    

	public static final String BLUE_EXCHANGE_MODULE = "BLUEEXCHANGE";
	
	public static final String INDICATIVE_MAPPING = "INDICATIVEMAPPING";
	
	public static final String SPS_MAPPING_CREATE_TASK = "SPSMAPPINGCREATE";
	
	public static final String SPS_MAPPING_MAINTAIN_TASK = "SPSMAPPINGMAINTAIN";
	
	public static final String SERVICE_TYPE_MAPPING_CREATE_TASK = "SERVICETYPEMAPPINGCREATE";
	
	public static final String SERVICE_TYPE_MAPPING_MAINTAIN_TASK = "SERVICETYPEMAPPINGMAINTAIN";
	
	public static final String VIEW_SERVICE_TYPE_CODE_MAPPING_TASK = "VIEWSERVICETYPECODEMAPPING";
	
	public static final String LOOK_UP_ACTION = "lookUpAction";
	
	// for Blue Exchange
	public static final String POPUP_HEADER = "title";
	
	public static final String SPS_MAPPING = "spsMapping";
	
	public static final String UPDATE_SPS_MAPPING = "updateSPSMapping";
	
	public static final String SERVICE_TYPE_MAPPING_EDIT_PAGE = "editServiceTypeMapping";
	
	public static final String SRV_TYP_MAPPING_SESSION_KEY = "servTypeMappingSysId";
	
	public static final String SRV_TYP_MAPPING_RULE_ID_SESSION_KEY = "servTypeRuleId";

	public static String RETRIEVE_SPS_MAPPING_VALUES = "retrieveSPSMappingValues";
	
	public static String SEARCH_GREATER_LIMIT_SIZE ="search.greater.limit.size";

	public static final String CUSTOM_MESSAGE_TEXT_CREATE_TASK = "CUSTOMMESSAGETEXTCREATE";
	
	public static final String CUSTOM_MESSAGE_TEXT_MAINTAIN_TASK = "CUSTOMMESSAGETEXTMAINTAIN";
	
	public static final String CUSTOM_MESSAGE_LENGTH = "custom.message.size.large";
	
	public static final String CUSTOM_MESSAGE_EDIT = "editCustomMessage";
	
	public static final String CUSTOM_MESSAGE_CREATE_BREADCRUMB = "Administration >> Blue Exchange >> Custom Message Text >> Create"; 
	public static final  int MAX_SIZE = 500;  
	public static final  int ADMIN_METHOD_VIEW_POPUP = 1;
	
    public static final int RETRIEVE_QUESTIONAIRE = 1;
    public static final int RETRIEVE_CHILD_QUESTIONAIRE = 2;
    public static final int RETRIEVE_QUESTIONAIRE_DF = 3;
	
	public static String COPY_PRODUCT ="copyProduct";
	
	public static String CHECKOUT_PRODUCT ="checkOutProduct";
	
	public static String ADMN_ID = "ADMIN_ID";
	//for pop up filter
	public static final String POPUP_SEARCH_STRING ="searchString";
	
	public static final String POPUP_HEADER_NAME="headerName";
	
	public static final String POPUP_ADJUD="ADJUD";
	
	public static final String POPUP_UMRULE="UMRULE";
	
	public static final String POPUP_BNFTDENY ="BNFTDENY";
	
	// for admin methods
	public static final String ADMIN_ID = "adminId";
	public static final String ENTITY_ID = "entityId";
	public static final String ENTITY_TYPE = "entityType";
	public static final String ADMIN_METHOD_SYSID = "adminMethodSysId";
	
	public static final int MAX_BEN_ATTACH = 256;
	public static String COMMENTS_LENGTH_INVALID = "comments.length.invalid";
	public static String CONTRACT_ID_INVALID = "valid.contract.id";
	public static String END_CONTRACT_ID_SMALLER = "end.contract.id.smaller";
	public static String VALID_COUNT = "valid.count";
	public static final int FILTER_ACTION=1;
	public static final int SEARCH_ACTION=2;
	
	public static final String CONTRACT_EXCLUSION_RULE="ContractExclusionRule";
	public static final String CONTRACT_DENIAL_RULE="ContractDenialRule";
	public static final String CONTRACT_UM_RULE="ContractUmRule";
	public static final String CONTRACT_PNR_RULE="ContractPnrRule";
	public static final String NO_RESERVED_CONTRACTS = "no.reserved.contracts";
	public static final String CONTRACT_ID_TO_RELEASE="contract.id.to.release";
	public static final String NO_EXPIRED_CONTRACT_IDS="no.expired.contract.ids";
	public static final String ALL_RESERVED_CONTRACTS_USE = "all.reserved.contracts.use";
	public static final String NO_RESERVED_CONTRACTS_IN_RANGE="no.reserved.contracts.in.range";
	public static final String HEADER_NAME_FOR_ADMIN_OPTION="Admin Option";
	public static final String QUERY_NAME_FOR_ADMIN_OPTION="getAdminOptionForContract";
	public static final String ENTITY_ID_FOR_HASHMAP="entityId";
	public static final String SEARCH_STRING_FOR_HASHMAP="searchString";
	public static final String SEARCH_STRING="texas";
	public static final String SEARCH_STRING1="aqua";
	public static final String SEARCH_STRING2="!@";
	public static final String RECORDS_NOT_FOUND="records.not.found";
	public static final String FILTER_RESULTS_NOT_FOUND="Filter.results.zero";
	public static final String ADMIN_OPTION_DESC="!@ADMINOPTION!7";
	public static final String QUERY_NAME_FOR_RULES="getRuleId";
	public static final String RULE_TYPE_DENIAL="BNFTDENY";
	public static final String HEADER_NAME_FOR_HASHMAP="headerName";
	public static final String RULE_ID_EXPECTED="ACUPNDN01";
	public static final String RULE_ID_EXPECTED1="AQUADN01";
	public static final String PARENT_CATALOG_NAME="REGULATORY AGENCY";
	public static final String PARENT_CATALOG_CODE="CDI";
	public static final String PARENT_CATALOG_DESC="CALIFORNIA DEPT OF INSURANCE";
	public static final String PARENT_CATALOG_CODE1="TDI";
	public static final String PARENT_CATALOG_DESC1="TEXAS DEPT OF INSURANCE";
	public static final String INVALID_SEARCH_STRING="!@$%#";
	public static final String USER_NAME="USER";
	public static final String SEARCH_STRING_APPEND="%";
	public static final String ENTER_VALID_CONTRACT_ID="enter.valid.contract.id";
	public static final String ALL_EXPIRED_CONTRACTS_INUSE="all.expired.ids.inuse";
	
	public static final String BNFT_LVL_ID ="bnftLvlAdmnId";
		
	public static final String BY_CY_REFERNCE_ID ="0004";
	public static final String BY_CY_QSTN_DESC ="CALENDAR YEAR OR BENEFIT YEAR";

	public static final String ADJUD_REFERNCE_ID ="2051";
	public static final String ADJUD_QSTN_DESC ="IS CONTRACT ADJUDICABLE";
	
	public static final String exclusionRuleIndicator ="&";
	public static final String denielRuleIndicator="*";
	public static final String umRuleIndicator="#";
	public static final String pnrRuleIndicaot="$";
	
	public static final int INSERT_NOTE=1;
	public static final int UPDATE_NOTE=2;
	public static final int DELETE_NOTE=3;
	
	public static final String INSERT="insert";
	public static final String UPDATE="update";
	public static final String DELETE="delete";
	
	//TEST RULE
	public static final String TESTCASE_MODULE = "TESTCASEDEVELOPMENT";
	public static final String TESTSUITE_MODULE = "TESTSUITEDEVELOPMENT";
	public static final String TASK_TESTCASE_CREATE = "TESTCASECREATE";
	public static final String TASK_TESTCASE_MAINTAIN = "TESTCASEMAINTAIN";
	public static final String TASK_TESTSUITE_CREATE = "TESTSUITECREATE";
	public static final String TASK_TESTSUITE_MAINTAIN = "TESTSUITEMAINTAIN";

	public static final String ADJUD_RMA_MODULE = "ADJUDRMAMODULE";

	// Benefit Tier Definition Popup
	public static final String CRITERIA_STRING = "criteriaStirng";
	public static final String TIER_CRITERIA_PSBL_VALUE_LIST = "tierCrtPsblValueList";
	public static final String BENEFIT_TIER_ADEDD = "benefit.tier.add";
	public static final String QUESTION_NOTE = "questionnote";
	public static final String TIER_NOTE = "tiernote";
	
	public static final String ADMIN_MTHD_NUMBER_INVALID = "adminmethod.number.invalid";
	public static final String ADMIN_MTHD_DESC_INVALID = "adminmethod.desc.invalid";
	public static final String ADMIN_MTHD_DESC_INVALID_PATTERN = "adminmethod.desc.invalidpattern";
	public static final String ADMIN_MTHD_PROCESSMTHD_INVALID = "adminmethod.processmthd.invalid";
	public static final String ADMIN_MTHD_CREATE_UNIQUE_VALID = "admin.map.mandatory.unique.valid";
	public static final String ADMIN_METHOD_CREATE_SUCCESS = "adminmethod.map.create.success";
	public static final String ADMIN_METHOD_MAPPING_CREATE_SUCCESS = "adminmethodmapping.map.create.success";
	public static final String ADMIN_METHOD_EDIT_SUCCESS = "adminmethod.map.edit.success";
	public static final String ADMIN_METHOD_MAPPING_EDIT_SUCCESS = "adminmethodmapping.map.edit.success";
	public static final String ADMIN_METHOD_MAP_DELETE_SUCCESS = "adminmethodmapping.map.delete.success";
	public static final String ADMIN_MTHD_MANDATORY_FIELD_REQUIRED="adminmethod.maintain.mantadory.fields";
	public static final String ADMIN_MTHD_DESC_LENGTH_NOT_EXCEED="adminmethod.maintain.description.length.notexceed";
	public static final String ADMIN_MTHD_CMNTS_LENGTH_NOT_EXCEED="adminmethod.maintain.comments.length.notexceed";
	public static final String ADMIN_MTHD_DESC_AND_COMNTS_LENGTH_NOT_EXCEED="adminmethod.maintain.description.comments.length.notexceed";
		
	public static final String ADMIN_MTHD_LOCATE_INVALID="adminmethod.maintain.locate.invalid.criteria";
	public static final String ADMIN_MTHD_EDIT_INVALID="adminmethod.maintain.edit.invalid.criteria";
	
	public static final int VIEW_ACTION =1;
	
	public static final int EDIT_ACTION =2;

	//CARS START
	//DESCRIPTION : Validation message for frequency
	public static String MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT = "benefitcomponent.frequency.value.check";
	//DESCRIPTION : PER string for description
    public static String PER_STRING = " PER ";
    //DESCRIPTION : Space string
    public static String SPACE_STRING = " ";
    //DESCRIPTION : Hiphan string
    public static String HIPHAN_STRING = " - ";
    //DESCRIPTION : Colon string	
	public static final String COLON=":";
	//DESCRIPTION : Qualifier code string
	public static final String QUALIFIER_CODE="1935";
	//DESCRIPTION : Qualifier code name string
	public static final String QUALIFIER_CODE_NAME="QUALIFIER";
	//Not Support too many domain combinations 
	public static String MSG_NOT_SUPPORT_MANY_DOMAIN_ATTRIBUTES = "notsupport.domain.attributes.many";
	//CARS END
	public static String PAGE_NO= "pageno";

	//	CARS START AM1
	String TIER = "tier";
	String CRITERIA="Criteria";
	//	CARS END AM1

	public static final String MODULE="module";

	public static int REORDS_PER_PAGE =500;
	
	public static final String COPY_LEGACY_NOTES="copyLegacyNotes";
	
	public static final String FAILURE="failure";
	

	//CARS START
	public static final String GEN_METHOD = "GENERAL";
	//CARS END

	public static final String LOAD_UNCODED_NOTES_PRINT = "loadUncodedNotesForPrint";
	
	public static String DT_SGMNT_MDFD_IND = "dateSegmentModified";
	
	public static String RETRIEVE_DELETED_DATESEGMENTS = "retrieveDeletedDateSegments";
	
	public static String RETRIEVE_ROOT_DELETE_CONTRACTS = "retrieveRootDeleteContracts";
	
	public static String RETRIEVE_ROOT_DELETE_SCHEDULED_CONTRACTS = "retrieveRootDeleteScheduledContracts";

	public static String IS_PRODUCT_LATEST = "isProductLatest";
	
	public static final String CONTRACT_REPORT_RUNNING = "reportRunning";
	public static final String CONTRACT_REPORT_PAGE = "contractReportPage";
	
	public static String CNTRT_STATUS_EMPTY = "";
	public static String CNTRT_STATUS_ACTIVE = "ACTIVE";
	public static String CNTRT_STATUS_INACTIVE = "INACTIVE";
	public static String CNTRT_STATUS_TERMED = "TERMED";
	
	//ICD10 Constants
	
	public static final String CONTRACT_PAGE_FROM = "contract";
	public static final String PRODUCT_PAGE_FROM = "product";
	public static final String INDIVIDUAL_RULE_PAGE_FROM = "rule";
	
	public static final String DEFAULT_FILE_NAME = "Rule_Extract";
	public static final String DEFAULT_SHEET_NAME = "Rule Extract";
	public static final String DEFAULT_SHEET_NAME_GRP_RULE = "Group Rule Extract";
	public static final String DT_SGMNT_SYS_ID = "dateSgmntSysId";
	public static final String IND_RULE_ID = "ruleId";
	public static final String BNFT_CMPNT_ID = "benefitComponentId";
	public static final String ZERO = "0";
	
	
	public static final String EXTRACT_RULE_TYPE = "ruleType";
	public static final String SEARCH_KEY_FOR_GROUP_RULE = "groupIdLists";
	
	public static final String EXCLUSION_RULE_CRITERIA = "&%";
	public static final String DENIAL_RULE_CRITERIA = "*%";
	public static final String UM_RULE_CRITERIA = "#%";
	
	public static final String EXCLUSION_RULE_DESC = "Exclusion Rule";
	public static final String DENIAL_RULE_DESC = "Denial Rule";
	public static final String UM_RULE_DESC = "UM Rule";
	public static final String HEADER_RULE_DESC = "Header Rule";
	public static final String HEADER_RULE_ASSOCIATED_BC = "Benefit Component";
	public static final String HEADER_RULE_ASSOCIATED_BNFT = "Benefit";
	public static final String ICD_CODE = "ICD";
	
	public static final String RULES_LIST_KEY_NAME ="rules";
	public static final String GROUP_RULES_LIST_KEY_NAME ="grouprules";
	
	
	//ICD10 Constants End
	
	//MassUpdate
    //public String MASS_UPDATE_MODULE = "MASSUPDATEMODULE";
	//public static final String TASK_MASS_UPDATE = "MASSUPDATE";
//SSCR 21516
	public static String  GET_OLDER_VERSION_FOR_DATE_SEGMENT = "fetchOlderVersionsForDateSegment";
	//Added validation for INDICATIVE LONG TERM SOlution
	public static String  INDICATIVE_SEGMENT_ITEM_ADDED = "New Field";
	public static String  INDICATIVE_SEGMENT_ITEM_UPDATED = "Changed Field";
	public static String  INDICATIVE_SEGMENT_ITEM_DELETED = "Field Removed";
	//Added for Spider Mapping in blue exchange
	public static final String SPIDER_MAPPING_MODULE="EBXSPIDER";
	public static final String SPIDER_MAPPING_TASK="SPIDER";
}

