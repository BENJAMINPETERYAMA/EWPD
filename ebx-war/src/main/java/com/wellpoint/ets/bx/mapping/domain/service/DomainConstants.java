/*
 * <DomainConstants.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.domain.service;

public class DomainConstants {

	public static final String STATUS_BUILDING = "BUILDING";

	public static final String STATUS_BEING_MODIFIED = "BEING_MODIFIED";

	public static final String STATUS_SCHEDULED_TO_PRODUCTION = "SCHEDULED_TO_PRODUCTION";

	public static final String STATUS_SCHEDULED_TO_TEST = "SCHEDULED_TO_TEST";

	public static final String STATUS_NOT_APPLICABLE = "NOT_APPLICABLE";

	public static final String STATUS_PUBLISHED = "PUBLISHED";

	public static final String STATUS_OBJECT_TRANSFERRED = "OBJECT_TRANSFERRED";

	public static final String AUDIT_STATUS_ADDED = "ADDED";

	public static final String AUDIT_STATUS_BEING_MODIFIED = "MODIFIED";

	public static final String AUDIT_STATUS_SEND_TO_TEST = "SENT TO TEST";

	public static final String AUDIT_STATUS_APPROVE = "APPROVED";

	public static final String AUDIT_STATUS_DISCARD_CHANGES = "DISCARDED CHANGES";

	public static final String AUDIT_STATUS_PUBLISHED = "PUBLISHED";

	public static final String AUDIT_STATUS_NOT_APPLICABLE = "MARKED AS NOT APPLICABLE";

	public static final String AUDIT_STATUS_OBJECT_TRANSFERRED = "OBJECT TRANSFERRED";

	public static final String UPDATE_MAIN_OPERATION = "UPDATE_MAIN";

	public static final String UPDATE_TEMP_OPERATION = "UPDATE_TEMP";

	public static final String INSERT_TEMP_OPERATION = "INSERT_TEMP";

	public static final String UPDATE_STATUS_TEMP_OPERATION = "UPDATE_STATUS_TEMP";

	public static final String UPDATE_STATUS_MAIN_OPERATION = "UPDATE_STATUS_MAIN";

	public static final String DELETE_TEMP_OPERATION = "DELETE_TEMP";
	
	public static final String FROM_OBJECT_TRANSFERRED_TO_SCHEDULED_TO_PRODUCTION = "FROM_OBJECT_TRANSFERRED_TO_SCHEDULED_TO_PRODUCTION";

	public static final String DATAFEED_STATUS = "Y";

	public static final String NEW_BX_URL = "NEW_BX_URL";
	
	public static final String BX_PROP_FILE_NAME = "blueexchange";

	public static final String EB01_NAME = "EB01";

	public static final String EB02_NAME = "EB02";

	public static final String EB03_NAME = "EB03";

	public static final String EB04_NAME = "EB04";

	public static final String EB05_NAME = "EB05";

	public static final String EB06_NAME = "EB06";

	public static final String EB07_NAME = "EB07";

	public static final String EB08_NAME = "EB08";

	public static final String EB09_NAME = "EB09";

	public static final String EB10_NAME = "EB10";

	public static final String EB11_NAME = "EB11";

	public static final String EB12_NAME = "EB12";

	public static final String HSD01_NAME = "HSD01";

	public static final String HSD02_NAME = "HSD02";

	public static final String HSD03_NAME = "HSD03";

	public static final String HSD04_NAME = "HSD04";

	public static final String HSD05_NAME = "HSD05";

	public static final String HSD06_NAME = "HSD06";

	public static final String HSD07_NAME = "HSD07";

	public static final String HSD08_NAME = "HSD08";

	public static final String NOTE_TYPE_CODE = "NOTE_TYPE_CODE";

	public static final String NOTETYPECODE = "NOTE TYPE CODE";

	public static final String III02_NAME = "III02";

	public static final String ACCUM_NAME = "ACCUM";

	public static final String UMRULE_NAME = "UMRULE";

	public static final String USER_CMTS_CREATE = "MAPPING CREATED";

	public static final int FAILURE_STATUS = 0;

	public static final int SUCCESS_STATUS = 1;

	public static final int LOCKED_STATUS = -1;

	public static final int CONDN_TO_FETCH_HIPPACODE_PSBLE_VALUES = -1;

	public static final String ACCUM_REF_NAME = "ACCUMULATOR REFERENCE";

	public static final String DATAFEED_UPDTD_USER = "DATAFEED";

	public static final String IN_TEMP_TAB_FLAG_PERSIST = "N";

	public static final String IN_TEMP_TAB_FLAG_UPDATE = "Y";

	public static final String DATAFEED_UPDATED = "DataFeedUpdate";

	public static final String NOT_FINALIZED_INDICATOR_PERSIST = "N";

	public static final String NOT_FINALIZED_INDICATOR_UPDATE = "Y";

	public static final String SPRING_CONFIG_FILE = "junitConfig.xml";

	public static final String UN_MAPPED = "UN MAPPED";

	public static final String UNMAPPED_STATUS = "UNMAPPED";

	public static final String MAPPED_STATUS = "MAPPED";

	public static final String VIEW_STATUS = "VIEW";

	public static final int noOfAuditInfo = 21;

	public static final String FINALIZED = "FINALIZED";

	public static final String NOT_FINALIZED = "NOT_FINALIZED";

	public static final String FEED_RUN_FLAG = "N";

	public static final String DELETE_CSTM_MESSAGE = "DELETED";

	public static final String FEED_RUN_FLAG_TEST = "T";

	public static final String FEED_RUN_FLAG_PROD = "P";

	public static final String DF_USER_COMMENTS_PUBLISH = "Datafeed publishing";

	public static final String DF_USER_COMMENTS_DELETE = "Datafeed deleting";

	/**
	 * Simulation
	 */
	public static final String ZERO = "0";

	public static final String REQ_ROW_LENGTH = "126";

	public static final String ONE = "1";

	public static final String NO = "N";

	public static final String NAMESPACE = "v4010.schemas.ebx.ets.wellpoint.com";

	public static final String ZERO_ONE = "01";

	public static final String ZERO_TWO = "02";

	public static final String ZERO_THREE = "03";

	public static final String ZERO_FIVE = "05";

	public static final String NUMERIC_EXPRESSION = "[-+]?[0-9]*\\.?[0-9]+$";

	public static final String EMPTY = "";

	public static final String LG_CDHP_VARIABLE1 = "ALUMPRODID";

	public static final String LG_CDHP_VARIABLE2 = "SOLAUPRODID";

	public static final String ISG_VARIABLE = "HEALTHACT";

	/*public static final String HCR_VARIABLE = "AHCRLMTRSTAP";

	public static final String HCR_VARIABLE_E018 = "AHCRPREVAPL";*/

	public static final String BOOLEAN_FORMAT = "Y/N";

	public static final String PERCENTAGE_FORMAT = "PCT";

	public static final String COINSURANCE = "A";

	public static final String COPAYMENT = "B";

	public static final String DEDUCTIBLE = "C";

	public static final String STOP_LOSS = "G";

	public static final String LIMITATIONS = "F";

	public static final String EBO3_SS = "SS";

	public static final String EB03_SP = "SP";

	// Changed UR to UC as mentioned in BTRD for june release
	public static final String EB03_UC = "UC";

	public static final String EB06_32 = "32";

	public static final String EB03_30 = "30";
	
	public static final String EB03_33 = "33";
	
	public static final String EB03_BG = "BG";

	public static final String EB03_88 = "88";

	public static final String EB03_60 = "60";

	public static final String EB03_80 = "80";

	public static final String EB03_81 = "81";

	public static final String EB01_BC = "BC";

	public static final String EB01_U = "U";

	public static final String EB01_I = "I";

	public static final String EB01_D = "D";
	// modified for new business rule
	public static final String EB01_A = "A";

	// Added for June release E027
	public static final String EB01_DW = "DW";

	public static final String EB01_G = "G";

	public static final String EB01_F = "F";

	public static final String EB01_C = "C";

	public static final String EB06_6 = "6";

	public static final String EB06_7 = "7";

	public static final String EB06_13 = "13";

	public static final String EB06_21 = "21";

	public static final String EB06_22 = "22";

	public static final String EB06_23 = "23";
	
	public static final String EB06_25 = "25";

	public static final String EB06_34 = "34";

	public static final String EB06_27 = "27";

	// added for validation of E026 - start
	public static final String EB06_26 = "26";

	public static final String EB06_36 = "36";

	// added for validation of E026 - end
	
	public static final String EB08_0 = "0";
	
	public static final String EB12_Y = "Y";

	public static final String PVA_ALL = "ALL";

	public static final String PVA_NPAR = "NPAR";

	public static final String PVA_PAR = "PAR";

	public static final String IN_NETWORK_INDICATOR = "Y";

	public static final String OUT_NETWORK_INDICATOR = "N";
	
	public static final String IN_OUT_NETWORK_INDICATOR = "W";

	public static final String HMO = "HMO";

	public static final String EPO = "EPO";
	
	/**
	 * September release - HMO logic
	 */
	public static final String CONTBENTYPE = "CONTBENTYPE";
	
	public static final String H = "H";
	public static final String HM = "HM";
	
	public static final String HMA = "HMA";
	
	public static final String CC = "CC";
	
	public static final String HMO_PRODCODES_WPD = "hmo.productcodes.wpd";
	
	public static final String HMO_CONTRACT_WPD = "hmo.contract.wpd";
	
	/**
	 * September release - EPO logic
	 */
	public static final String EPO_PRODCODES_LG = "epo.product.lg";
	
	public static final String EPO_PRODCODES_ISG = "epo.product.isg";
	
	public static final String EPO_CONTRACT_ISG = "epo.contract.isg";
	
	/**
	 * September release - HCR logic
	 */
	public static final String HCR_Variable_E016andE017 = "hcr.variable.E016andE017.wpd";
	
	public static final String HCR_Variable_E018 = "hcr.variable.E018.wpd";
	
	/**
	 * Sepetember release - E06 Autopopulation
	 */
	public static final String SPS_EB06_AUTOPOPULATE_EWPD = "sps.EB06.autopopulate_ewpd";
	
	public static final String SPS_EB06_AUTOPOPULATE_VISIT_EWPD = "sps.EB06.autopopulate_visit_ewpd";
	
	public static final String SPS_EB06_AUTOPOPULATE_ADMISSION_EWPD = "sps.EB06.autopopulate_admission_ewpd";
	
	public static final String SPS_EB06_AUTOPOPULATE_DAY_EWPD = "sps.EB06.autopopulate_day_ewpd";
	
	public static final String WPD_LG = "LG";

	public static final String WPD_ISG = "ISG";

	/**
	 * Comment for <code>EWPD</code>
	 */
	public static final String EWPD = "EWPD";

	/**
	 * Comment for <code>WPD</code>
	 */
	public static final String WPD = "WPD";

	public static final String WPD_LG_ISG = "LG, ISG";

	/**
	 * Error Codes
	 */
	public static final String ERROR_E001 = "E001";

	public static final String ERROR_E004 = "E004";

	public static final String ERROR_E005 = "E005";

	public static final String ERROR_E006 = "E006";

	public static final String ERROR_E007 = "E007";

	public static final String ERROR_E008 = "E008";

	public static final String ERROR_E009 = "E009";

	public static final String ERROR_E010 = "E010";

	public static final String ERROR_E011 = "E011";

	public static final String ERROR_E012 = "E012";

	public static final String ERROR_E014 = "E014";

	public static final String ERROR_E016 = "E016";

	public static final String ERROR_E017 = "E017";

	public static final String ERROR_E018 = "E018";

	public static final String ERROR_E019 = "E019";

	// modified for new business rule
	public static final String ERROR_E020 = "E020";

	public static final String ERROR_E021 = "E021";

	public static final String ERROR_E024 = "E024";

	/* Code change for E025 error validation- Start */
	public static final String ERROR_E025 = "E025";

	/* Code change for E031 error validation- Start */
	public static final String ERROR_E031 = "E031";

	/* Code change for E031 error validation- Start */
	public static final String ERROR_E032 = "E032";

	/* Code change for E031 error validation- Start */
	public static final String ERROR_E033 = "E033";

	/* Code change for E025 error validation- End */

	// added for validation of E026
	public static final String ERROR_E026 = "E026";

	// added for validation of E027
	public static final String ERROR_E027 = "E027";

	/**
	 * Comment for <code>ERROR_E028</code>
	 */
	public static final String ERROR_E028 = "E028";

	// added for validation of E029 and E030
	public static final String ERROR_E029 = "E029";

	// added for validation for checking the warning messages penalty
	public static final String WARNING_W029 = "W029";
	public static final String W029_DESCRIPTION = "MESSAGE DOES NOT CONTAIN PENALTY IN TEXT FOR CONTRACT VARIABLE";
	public static final String WARNING_WB029 = "WB029";
	public static final String WB029_DESCRIPTION = "MESSAGE IS BLANK FOR CONTRACT VARIABLE";
	public static final String WARNING_W022 = "W022";
	public static final String W022_DESCRIPTION = "MESSAGE DOES NOT CONTAIN PENALTY IN TEXT FOR SPS ID";
	public static final String SUBSTRING_PENALTY = "PENALTY";
	public static final String SUBSTRING_PENLTY = "PENLTY";
	public static final String SUBSTRING_PNLTY = "PNLTY";
	public static final String SUBSTRING_PENALTY1 = "PENALTY";
	
	// Substrings for E022 - September release
	public static final String SUBSTRING_COINS = "COINS";
	
	public static final String SUBSTRING_COINSURANCE = "COINSURANCE";
	
	public static final String SUBSTRING_GLOBAL = "GLOBAL";
	public static final String SUBSTRING_TEST = "TEST";
	public static final String SUBSTRING_LIMITATION = "LIMITATION";
	public static final String SUBSTRING_DEDUCT = "DEDUCT";
	
	public static final String ERROR_E030 = "E030";

	public static final String ERROR_MQ = "EMQ";

	public static final String ERROR_DATA = "EDATA";

	public static final String DATE_FORMAT = "MM/dd/yyyy";

	public static final String DATE_FORMAT_EXPIRY = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_1 = "yyyyMMdd";

	/**
	 * Column Names for LG Systems
	 */
	public static final String CNTRCT_VAR_ID = "CNTRCT_VAR_ID";

	public static final String CONTRACT_VAR = "CONTRACT_VAR";

	public static final String DATA_ELEMENT_ID = "DATA_ELEMENT_ID";

	public static final String DATA_ELEMENT_VAL = "DATA_ELEMENT_VAL";

	public static final String MAJOR_TEXT = "MAJOR_TEXT";

	public static final String MINOR_TEXT = "MINOR_TEXT";

	public static final String CONTRACT_VAR_FORMAT = "CONTRACT_VAR_FORMAT";

	public static final String VAR_VALUE = "VAR_VALUE";

	public static final String PROV_ARNG = "PROV_ARNG";

	public static final String PRODUCT_FAMILY = "PRODUCT_FAMILY";

	public static final String SENSITIVE_BENEFIT_IND = "SEN_BNFT_IND";

	public static final String BUSINESS_ENTITY_LG = "BUSINESS_ENTITY";

	public static final String GROUP_STATE_HQ = "GROUP_STATE_HQ";

	public static final String END_DT_LG = "END_DT";

	public static final String START_DT_LG = "START_DT";

	public static final String CONT_VAR_TEXT = "CONT_VAR_TX";

	public static final String CONT_PRODUCT_CODE = "PRODUCT";

	public static final String VAR_MAPG_STTS_CD = "VAR_MAPG_STTS_CD";

	public static final String MAPPNG_CMP_IND = "MAPPNG_CMP_IND";

	public static final String MSG_TYPE = "MSG_TYPE";
	
	public static final String MESG_REQD_IND = "MSG_REQD";

	public static final String VAR_MSG = "MSG";
	
	public static final String EXTND_MSG1 = "EXTND_MSG_TXT1";

	public static final String EXTND_MSG2 = "EXTND_MSG_TXT2";
	
	public static final String EXTND_MSG3 = "EXTND_MSG_TXT3";
	
	public static final String HIGH_PRFRMN_NON_TIERD_MSG = "HIGH_PRFRMN_NON_TIERD_MSG_TXT";
	
	public static final String HIGH_PRFRMN_TIERD_MSG = "HIGH_PRFRMN_TIERD_MSG_TXT";
	
	public static final String EB12_IND = "EB12_IND";
	/* Code Change for EB025 error validation */
	public static final String CATAGORY_CODE = "CATAGORY";
	
	/**
	 * To get the Corporate Plan from result set - September release 
	 */
	public static final String CORPORATE_PLAN = "CORPORATEPLAN";
	
	/**
	 * To get the Benefit Structure from result set - September release 
	 */
	public static final String BENEFIT_STRUCTURE = "BENEFITESTRUCTURE";

	/**
	 * Column Names for ISG Systems
	 */
	public static final String MAPPED_VARID = "MAPPED_VARID";

	public static final String VARIABLE_ID = "VARIABLEID";

	public static final String MAJOR_HEADING = "MAJOR_HEADING";

	public static final String MINOR_HEADING = "MINOR_HEADING";

	public static final String FORMAT = "FORMAT";

	public static final String VARIABLE_VAL = "VARIABLEVAL";

	public static final String PVA = "PVA";

	public static final String STATEHQ = "STATEHQ";

	public static final String MBU = "MBU";

	public static final String EXPIRYDATE = "EXPIRYDATE";

	public static final String EFFECTIVE_DATE = "EFTVDATE";

	public static final String VARIABLE_DESC = "VARIABLEDESC";

	public static final String CONTRACTID = "CONTRACTID";

	public static final String REVISIONDATE = "REVISIONDATE";

	/**
	 * Column Names for eWPD System
	 */
	public static final String SPS_PARAM_ID = "SPS_PARAM_ID";

	public static final String EB01_VALUE = "EB01_VALUE";

	public static final String EB02_VALUE = "EB02_VALUE";

	public static final String EB06_VALUE = "EB06_VALUE";

	public static final String EB09_VALUE = "EB09_VALUE";

	public static final String BENEFIT_NAME = "BENEFITNAME";

	public static final String BENEFIT_COMP_NAME = "BENEFITCOMPNAME";

	public static final String HSD1_VALUE = "HSD1_VALUE";

	public static final String HSD2_VALUE = "HSD2_VALUE";

	public static final String HSD3_VALUE = "HSD3_VALUE";

	public static final String HSD4_VALUE = "HSD4_VALUE";

	public static final String HSD5_VALUE = "HSD5_VALUE";

	public static final String HSD6_VALUE = "HSD6_VALUE";

	public static final String HSD7_VALUE = "HSD7_VALUE";

	public static final String HSD8_VALUE = "HSD8_VALUE";

	public static final String ACCUMR_SPS_ID = "ACCUMR_SPS_ID";

	public static final String DATA_TYPE_LGND = "DATATYPELGND";

	public static final String BENEFIT_VAL = "BNFTVAL";

	public static final String LINE_PVA = "LINEPVA";

	public static final String REFERENCE_ID = "RFRNCID";

	public static final String SPSDESC = "REFDESC";

	public static final String LVLDESC = "LVLDESC";

	public static final String ROW_COUNT = "ROWCOUNT";

	public static final String BUSENTITY = "BUSENTITY";

	public static final String EWPD_RULE_ID = "RULE_ID";

	public static final String SEND_DYNAMIC_INFO = "SEND_DYNMC_INFO";

	public static final String MAPPED_RULE_ID = "MAPPED_RULEID";

	public static final String SERVICE_TYPE_CODE = "SRVC_TYP_CODE";

	public static final String HCR_QN_E018 = "ISHCRQUESTIONCOUNT";

	public static final String CDHPCOUNT = "CDHPCOUNT";

	public static final String BENEFITCOVEREDCOUNT = "BENEFITCOVEREDCOUNT";

	public static final String BENEFITCOVERED = "BENEFIT COVERED";
	
	public static final String BENEFITCOVEREDPAR = "BENEFIT COVERED PAR";
	
	public static final String BENEFITCOVEREDNPAR = "BENEFIT COVERED NPAR";

	public static final String TIER_DESC = "TIERDESC";
	
	public static final String BUS_GRP_NM = "BUS_GRP_NM";

	public static final String DRUG_RIDER_COVERAGE = "DRUG RIDER COVERAGE";

	public static final String DRUG_COVERAGE = "DRUG COVERAGE";

	public static final String PRESCRIPTION_DRUG_PLAN = "PRESCRIPTION DRUG PLAN";

	public static final String STATUS_CD = "STATUS_CD";

	public static final String VALID_FOR_BX = "VALID_FOR_BX";

	/**
	 * Property Key Names
	 */

	
	public static final String EB03_VARIABLES_LIST = "eb03.variables.e017";

	public static final String EB06_VARIABLES_LIST = "eb06.variables.e001";
	//added as part of BXNI CR35
	public static final String HSD05_VARIABLES_LIST = "hsd05.variables.e001";

	public static final String EB01_VARIABLES_LIST = "eb01.variables.e005";
	
	public static final String JUNK_VARIABLES_LIST = "eb01.variables.junkvalue.e005";

	public static final String FORMATS_E009_LIST = "format.e009";

	public static final String IN_NETWORK_PVA = "in_network";

	public static final String OUT_NETWORK_PVA = "out_network";

	public static final String IN_OUT_NETWORK_PVA = "in-out_network";

	// added for validation of E026 - start
	public static final String EB03_VARIABLES_LIST_E026_1 = "eb03.variables.e026.1";

	public static final String EB03_VARIABLES_LIST_E026_2 = "eb03.variables.e026.2";

	public static final String EB03_VARIABLES_LIST_E026_3 = "eb03.variables.e026.3";

	public static final String EB03_VARIABLES_LIST_E026_4 = "eb03.variables.e026.4";

	// added for validation of E026 - end

	/**
	 * EB06_VALUES_EXCLUSIONS_LIST
	 */
	public static final String EB06_VALUES_EXCLUSIONS_LIST = "eb03.variables.e016";

	// modified for new business rule
	public static final String err021_in_network_group1 = "err021_in_network_group1";

	public static final String err021_in_network_group2 = "err021_in_network_group2";

	public static final String EB03LIST_FOR_ERROR_VALIDATION = "EB03LIST_FOR_ERROR_VALIDATION";

	/* Code change for E025 error validation- Start */
	public static final String EXCLUDED_CATAGORY_LIST = "excluded.catagory.e025";

	public static final String E025_DATA_TYPE_FORMAT = "e025.data.type.format";

	/* Code change for E025 error validation- End */
	public static final String WPD_MEDSUP_PRODUCT_CODE = "wpd_medsup_product_code";

	public static final String ISG_MEDSUP_PRODUCT_CODE = "isg_medsup_product_code";

	// Added for Error E030
	public static final String EB03_VARIABLES_LIST_E030 = "eb03.variables.e030";
	
	public static final String SENSITIVE_EB03 = "eb03.sensitive.benefits";

	public static final String PROPERTY_FILE_NAME = "errorvalidator";

	public static final String RULE_ID = "RULEID";

	public static final String SPS_ID = "SPSID";

	public static final String IN_NETWORK = "In-Network";

	public static final String OUT_OF_NETWORK = "Out-of-Network";

	public static final String NOT_APPLICABLE = "Not Applicable(W)";
	
	public static final String Y = "Y";

	public static final String N = "N";
	
	public static final String W = "W";

	public static final String FORMAT_YES = "YES";

	public static final String FORMAT_NO = "NO";

	public static final String TILDA_CHAR = "~";

	public static final String STAR_CHAR = "*";

	public static final String III = "III*";

	public static final String MSG = "MSG*";

	public static final String EB = "EB*";

	public static final String HSD = "HSD*";

	public static final String ZZ = "ZZ*";

	public static final String AT = "@";

	public static final String LT = "LT";

	public static final String TB = "TB";

	public static final String BY = "BY";

	public static final String CY = "CY";

	public static final String CT = "CT";

	public static final String PERCENTAGE = "%";

	public static final String DOLLAR = "$";

	public static final String DOL = "DOL";

	public static final String PALATINO_LINOTYPE = "Palatino Linotype";

	public static final String NM1 = "NM1*IL*1";

	public static final String NM2 = "NM1*03*1";

	public static final String DMG = "DMG*";

	public static final String DTP = "DTP*";

	public static final String SYSTEM_EWPD = "EWPD";

	public static final String SYSTEM_LG = "LG";

	public static final String SYSTEM_ISG = "ISG";
	
	public static final String SYSTEM_LG_ISG = "LG, ISG";
	
	public static final String SYSTEM_ALL = "ALL";

	public static final String SYSTEM_IND = "IND";

	public static final String SYSTEM_SG = "SG";

	public static final String SYSTEM_SENIOR_FACETS = "SENIOR FACETS";

	/**
	 * Comment for <code>CONTRACT_IDENTIFIER</code>
	 */
	public static final String CONTRACT_IDENTIFIER = "CONTRACT";

	/**
	 * Comment for <code>PRODUCT_IDENTIFIER</code>
	 */
	public static final String PRODUCT_IDENTIFIER = "PRODUCT";

	/**
	 * Comment for <code>SPS_IDENTIFIER</code>
	 */
	public static final String SPS_IDENTIFIER = "SPS";

	/**
	 * Comment for <code>HEADERRULE_IDENTIFIER</code>
	 */
	public static final String HEADERRULE_IDENTIFIER = "HEADERRULE";

	/**
	 * Comment for <code>VARIABLE_IDENTIFIER</code>
	 */
	public static final String VARIABLE_IDENTIFIER = "VARIABLE";
	
	public static final String VARIABLE_IDENTIFIER_ICON = "VARIABLEICON";
	
	public static final String VARIABLE_ID_ICON = "VARIABLEIDICON";

	/**
	 * Error Codes Description
	 */
	public static final String E001_DESCRIPTION = "Contract variable error";

	public static final String E004_DESCRIPTION = "Copay-Percentage error";

	public static final String E005_DESCRIPTION = "Cost share definition and Contract value mismatch";

	public static final String E006_DESCRIPTION = "Co-insurance variable mapped to wrong cost share code";

	public static final String E007_DESCRIPTION = "Co-insurance cost share code coded on variable with out percentage value";

	public static final String E008_DESCRIPTION = "Pharmacy status error";

	public static final String E009_DESCRIPTION = "Limitation variable mapped to wrong cost share code";

	public static final String E010_DESCRIPTION = "General Basis error";

	public static final String E011_DESCRIPTION1 = "Specialist coding error - Out-Network/InOut-Network missing";

	public static final String E011_DESCRIPTION2 = "Specialist coding error - In-Network/InOut-Network missing";

	public static final String E011_DESCRIPTION3 = "Specialist coding error - Specialist benefit is NOT coded on the contract";

	public static final String E012_DESCRIPTION1 = "Urgent coding error - Out-Network/InOut-Network missing";

	public static final String E012_DESCRIPTION2 = "Urgent coding error - In-Network/InOut-Network missing";

	public static final String E012_DESCRIPTION3 = "Urgent coding error - Urgent benefit is NOT coded on the contract";

	public static final String E014_DESCRIPTION = "Shared Specialist Coding error";

	public static final String E016_DESCRIPTION = "HCR Lifetime Limitation Error";

	public static final String E017_DESCRIPTION = "HCR annual Limitation error";

	public static final String E018_DESCRIPTION = "HCR Preventive Provision error";

	public static final String E019_DESCRIPTION = "SILENT BENEFIT";

	public static final String E020_DESCRIPTION = "SIMILAR QUALIFIER FOR EB06 AND EB09";
	//Added as part of BXNI December Release
	public static final String E020_DESCRIPTION_FOR_HSD = "SIMILAR QUALIFIER FOR HSD05 AND HSD01";

	public static final String E021_DESCRIPTION = "CO-SHARE AMOUNT >= 60% (PAR), 85% (NPAR)";

	/* Code change for E025 error validation- Start */
	public static final String E025_DESCRIPTION_1 = "UNMAPPED HEADER RULE (";
	public static final String E025_DESCRIPTION_2 = ") IN BX TBL";
	public static final String E025_DESCRIPTION = "UNMAPPED HEADER RULE/SPS ID/VARIABLE IN BX TBL";

	/* Code change for E025 error validation- End */

	public static final String E024_DESCRIPTION = "Same EB01 to EB12 and HSD, Different EB07/EB08/EB10/EB11/HSD02/HSD04/HSD08 Error";

	// added error description for E029
	public static final String E029_DESCRIPTION = "WORLDWIDE SPS ID OR CONT VAR CODED IN BX TABLE";

	// Added for Error E030
	public static final String E030_DESCRIPTION = "EB01=G BUT EB03 NOT 30/60/88/48/CE/CG/CF/CH";

	public static final String EMQ_DESCRIPTION = "Error E024 could not be generated, as MQ Exception occurred";

	public static final String EDATA_DESCRIPTION = "Error E024 could not be generated, as MQ Exception occurred";

	// added for validation of E026
	public static final String E026_DESCRIPTION = "INCORRECT EB03 & EB06=7/26/27/36 COMBO FOR EB01=B";

	// added for validation of E027
	public static final String E027_DESCRIPTION = "SAME EB03 CONFLICTING NETWORK";

	// added for validation of E028
	public static final String E028_DESCRIPTION = "EB03=51/52/86 NOT RETURNED OR INACTIVE IN RESPONSE";

	// added for validation of E031
	public static final String E031_DESCRIPTION_START = "ACCUMULATOR LOOK-UP FOR ";
	public static final String E031_DESCRIPTION_END = " AND EB06 MISMATCH";

	// added for validation of E032
	public static final String E032_DESCRIPTION_START = "ACCUMULATOR FLAG IND FOR  ";
	public static final String E032_DESCRIPTION_END = " AND CV/SPS ID FORMAT MISMATCH";

	// added for validation of E033
	public static final String E033_DESCRIPTION_START = "ACCUMULATOR ROOT MEM CODE ";
	public static final String E033_DESCRIPTION_END = " AND EB02 MISMATCH";

	public static final String MQ_EXCEPTION_GENERAL_ERROR = "Error Validation against 27x static response failed, as MQ Exception occurred";

	public static final String GS = "GS*";

	public static final String GE = "GE*";

	public static final String ST = "ST";

	public static final String SE = "SE*";

	public static final String HL = "HL*";

	public static final String BHT = "BHT*";

	public static final String ISA = "ISA*";

	public static final String IEA = "IEA*";

	public static final String DATE_FORMAT_EXCEPTION = "Date not in Proper Format";

	public static final String NO_MATCH_FOUND = "NO MATCH FOUND";

	public static final String SYSTEM_MISMATCH = "System should be EWPD/LG/ISG";

	// Constants for Creating Excel
	public static final String CONTRACT_ID = "Contract Id";

	public static final String TIER_INFO = "Tier Details";

	public static final String BUSINESS_ENTITY = "Business Entity";

	public static final String STATE_HQ = "State HQ";

	public static final String GROUPSTATE_HQ = "Group State HQ";

	public static final String DATE_SEGMENT = "Date Segment";

	public static final String MINORHEADING = "Minor Heading";

	public static final String MAJORHEADING = "Major Heading";

	public static final String VARIABLE = "Variable";

	public static final String DESCRIPTION = "Description";

	public static final String FORMAT_N = "Format";

	public static final String VALUE_CODED = "Value Coded";

	public static final String ERROR = "Error";

	public static final String ERROR_DESCRIPTION = "Error Description";

	public static final String BENEFIT_COMPONENT = "Benefit Component";

	public static final String BENEFIT = "Benefit";

	public static final String SPS_DESCRIPTION = "SPS Description";

	public static final String SPSID = "SPS Id";

	public static final String VER = "Ver";

	public static final String AUDIT_DATE = "Audit Date :";

	public static final String SUBSCRIBER_NAME = "Subscriber Name :";

	public static final String DOB = "DOB :";

	public static final String GENDER = "Gender :";

	public static final String ELIGIBILITY = "Eligibility :";

	public static final String SUBSCRIBER_ID = "Subscriber ID :";

	public static final String MEMBER_NAME = "Member Name :";

	public static final String HOSP_BEN_CODE = "Hosp Ben Code :";

	public static final String PROF_BEN_CODE = "Prof Ben Code :";

	public static final String EB_STRING = "EB String";

	public static final String SERVICE = "Service";

	public static final String ELIGIBILITY_BENEFIT_INF = "Eligibility Benefit Inf";

	public static final String INN_OON = "INN/OON";

	public static final String PREFIX = "$ / % / #";

	public static final String EB_OTHER = "EB Other";

	public static final String III_DECODED = "III Decoded";

	public static final String MESSAGES = "Messages";

	public static final String OTHER = "Other";
	
	public static final String ACCUM_CDE="ACCUM_CDE";
	
	public static final String WPD_ACCUMULATOR = "WPD Accumulator";  //BXNI CR35

	public static final String VALUE_AS_IN_ABS_PRODUCTION = "Value as in ABS Production";
	// 27x Transaction Changes
	public static final String HEADER_2120_LOOP_NM1_MESSAGE_SEGMENT = "2120 Loop NM1 Message Segment";

	public static final String HYPHEN = "-";

	public static final String X12 = "X12";

	public static final String T = "T";

	public static final String P = "P";

	public static final String LASTNAME = "@LASTNAME";

	public static final String FIRSTNAME = "@FIRSTNAME";

	public static final String ALPHAPREFIX = "@ALPHAPREFIX";

	public static final String MEMBERID = "@MEMBERID";

	public static final String DATEOFBIRTH = "@DATEOFBIRTH";

	public static final String STARTDATE = "@STARTDATE";
	
	public static final String ENDDATE = "@ENDDATE";

	public static final String SERVICETYPECODE = "@SERVICETYPECODE";

	public static final String ENVIRONMENT = "@ENVIRONMENT";
	
	public static final String SENDER_ID = "@SENDERID";
	
	public static final String SRCLGCL = "@SRCLGCL";

	public static final String PRODUCTION = "Production";

	public static final String REQUEST_WITH_DEPENDENT_INFORMATION = "requestWithDependentInformation";

	public static final String REQUEST_WITHOUT_DEPENDENT_INFORMATION = "requestWithoutDependentInformation";
	//request format for dependent if date of birth is entered
	public static final String REQUEST_WITH_DEPENDENT_INFORMATION_5010 = "requestWithDependentInformation5010";
	//request format for dependent if date of birth is not entered
	public static final String REQUEST_WITH_DEPENDENT_INFORMATION_5010_WITHOUT_DOB = "requestWithDependentInformation5010WithoutDOB";
	//request format for subscriber if date of birth is entered
	public static final String REQUEST_WITHOUT_DEPENDENT_INFORMATION_5010 = "requestWithoutDependentInformation5010";
	//request format for subscriber if date of birth is not entered
	public static final String REQUEST_WITHOUT_DEPENDENT_INFORMATION_5010_WITHOUT_DOB = "requestWithoutDependentInformation5010WithoutDOB";

	public static final String RESPONSE_FORMAT_4010 = "4010";

	public static final String RESPONSE_FORMAT_5010 = "5010";

	public static final String REVISION_DATE = "Revision Date";

	public static final String TO = " To ";

	public static final String DATE_FORMAT_2 = "MMddyyyy";

	public static final String CURRDATE = "@CURRDATE";

	public static final String MQADAPTER = "/mqAdapter";

	public static final String TWENTY_SEVEN_X_REQUEST = "27xRequest";

	public static final String MAX_VERSION = "MAX_VERSION";

	// modified for new business rule
	public static final String DAYS = "DAYS";
	public static final String LEN = "LEN";

	public static final String HRS = "HRS";

	public static final String MTHS = "MTHS";

	public static final String VST = "VST";

	public static final String OCRS = "OCRS";

	public static final String YRS = "YRS";

	public static final String DAY = "DAY";

	public static final String MTH = "MTH";

	public static final String OCC = "OCC";
	
	public static final String OCR = "OCR";
	
	public static final String FL = "FL";

	/* Code change for E025 error validation- Start */
	public static final String AGE = "AGE";

	/* Code change for E025 error validation- End */

	/* For New Business Rules */	
	public static final String EB01_B = "B";

	public static final String EB06_BLANK = "";

	public static final String ERROR_E022 = "E022";

	public static final String E022_DESCRIPTION = "EB01=B AND EB06=21/22/23/32/BLANK";

	public static final String ANSWER = "ANSWER";

	public static final String CALENDARYEAR = "CY";

	public static final String BENEFITYEAR = "BY";

	public static final String ERROR_E023 = "E023";

	public static final String E023_DESCRIPTION = "CY VAR W/ EB06=22 OR BY VAR W/ EB06=23";

	public static final String SPSID_YEARLYCOPAYMAX = "spsId.yearlycopaymaximums";

	public static final String LINEOFBUSINESS_MSUP = "MSUP";

	public static final String LINEOFBUSINESS = "LINEOFBUSINESS";

	public static final String PRODUCT_CODE = "PRODUCT";

	public static final String EXCUSIONS_PRODCODES_WPD = "exclusions.productcodes.wpd.e022";

	public static final String EXCUSIONS_PRODCODES_ISG = "exclusions.productcodes.isg.e022";

	public static final String BENEFIT_YEAR_ACCUM_IND = "BENEFIT_YEAR_ACCUM_IND";

	public static final String BENEFITYEAR_N = "N";

	public static final String BENEFITYEAR_Y = "Y";

	public static final String ISG_CALYEARBNFTYEAR_VARIABLE = "PLANRNEWTYPE";

	public static final String CALBENFTYEAR = "CALBENFTYEAR";

	public static final String DRUG_RIDERS = "DRUG RIDERS";

	public static final String EXCLSN_COPAYPRYEAR_E022_LEVLDESC = "COPAY/SERVICE DED PER YEAR MAX";

	public static final String EXCLSN_E018_BENFTVAL = "100";

	public static final String FORMAT_DOLLAR = "DOL";

	public static final String EB03FORE018 = "eb03.variables.e018";

	public static final String EB01_NON_COVERED = "I";

	public static final String EB03FORE006 = "excluded.eb03.e006";
	//added as a part of BXNI CR35
	public static final String EB01FORE001 = "eb01.variables";

	public static final String EB03FORE019 = "excluded.eb03.e019";

	public static final String EB03FORE028 = "excluded.eb03.e028";

	public static final String EB03FORE010 = "excluded.eb03.e010";

	// added for exclusion

	public static final String ERROR_CODE = "ERROR_CD";

	public static final String ENTITY_ID = "ENTY_ID";

	public static final String ENTITY_TYPE = "ENTY_TYPE";

	/* Code change for E025 error validation- Start */
	public static final String INDIV_AND_FAMILY_DEDUCTIBLES = "INDIV AND FAMILY DEDUCTIBLES";

	public static final String STOP_LOSS_LIMITATIONS = "STOP LOSS LIMITATIONS";

	public static final String ELIGIBILITY_E025 = "ELIGIBILITY";

	public static final String CONTRACT_ADMINISTRATION = "CONTRACT ADMINISTRATION";

	public static final String INDIVIDUAL_DEDUCTIBLE = "INDIVIDUAL DEDUCTIBLE";

	public static final String IND = "IND";

	public static final String SG = "SG";
	public static final String FAM = "FAM";

	public static final String FAMILY_DEDUCTIBLE = "FAMILY DEDUCTIBLE";

	public static final String FAMILY_DEDUCTIBLE_OCCURS = "FAMILY DEDUCTIBLE OCCURS";
	public static final String FAMILY_DEDUCTIBLE_WPRENOTE = "FAMILY DEDUCTIBLE W/PRENOTE";
	public static final String INDIV_DEDUCTIBLE_WPRENOTE = "INDIV DEDUCTIBLE W/PRENOTE";

	public static final String GENERAL_BENEFIT_ADMINISTRATION = "GENERAL BENEFIT ADMINISTRATION";

	/* Code change for E025 error validation- End */

	// Added for MQ for calling system FACETS,ACES
	public static final String ACES = "ACES";

	public static final String FACETS = "FACETS";

	public static final String FAC = "FAC";

	public static final String MAC = "MAC";

	public static final String WGS = "WGS";

	public static final String FCTCR = "FCTCR";
	
	// Newly added system for Source System Identifier
	public static final String STAR = "STAR";

	// Added for E016 - Start
	/**
	 * Life time.
	 */
	public static final String EB06_VALUE_LIFETIME = "32";

	// Added for E016 - End

	// Added substrings for E029 error code.
	public static final String SUBSTRING_WW = "WW";
	
	public static final String SUBSTRING_WORLDWIDE = "WORLDWIDE";

	public static final String MAJOR_HEADING_VAL_E029 = "BENEFITS OUTSIDE THE UNITED STATES";
	
	// Added for Error E030
	public static final String OUT_OF_POCKET = "G";

	/**
	 * Comment for <code>EB03_VARIABLES_LIST_E028</code>
	 */
	public static final String EB03_VARIABLES_LIST_E028 = "eb03.variables.e028";

	// Added variable list for EB06 for error code E031
	public static final String EB06_VARIABLES_LIST_E031 = "eb06.variables.e031";
	// Added variable format list for E032
	public static final String VARIABLES_FORMAT_LIST_E032 = "variables.format.e032";

	public static final String EB_02 = "EB02";

	public static final String EB_06 = "EB06";

	public static final String EB_04 = "EB04";

	public static final String EB_05 = "EB05";

	public static final String EB_09 = "EB09";

	/* Reference Data Values START */

	public static final String YES = "YES";

	public static final String REFRESH = "refresh";

	public static final String CATALOG_LOCATE = "catalogNameLocate";

	public static final String DATA_VALUE_LIST = "dataValueList";

	public static final String DATA_VALUE_RESULT_PAGE = "dataValueSearchResult";

	public static final String DATA_VALUE_MANAGE_PAGE = "referenceDataValueManage";
	public static final String HPN_DATA_VALUE_MANAGE_PAGE = "referenceHPNDataValueManage";

	public static final String ACTIONS = "actions";

	public static final String VIEW_HISTORY_POP_UP = "catalogViewHistoryPopUp";

	public static final String DUPLICATE = "DUPLICATE";

	public static final String SUCCESS = "SUCCESS";

	public static final String CREATE_STATUS = "ADDED";

	public static final String UPDATE_STATUS = "UPDATED";

	public static final String DELETE_STATUS = "DELETED";

	public static final String OPEN_COMMENTS_DIALOG = "openCommentsDialog";

	public static final String UNDERSCORE = "_";

	public static final String SINGLE_QUOTATION = "'";

	public static final String EQUAL_TO = "=";

	public static final String VALUE = "VALUE";

	public static final String AND = " and ";

	public static final String VALUE_FOR_SAVE = "valueForSave";

	public static final String PRMRY_CD_FOR_SAVE = "primaryCodeForSave";

	public static final String DESC_FOR_SAVE = "secondaryCodeForSave";

	public static final String DEFN_FOR_SAVE = "descriptionForSave";

	public static final String USER_COMMENTS = "userComments";

	public static final String VAL_DESC_TXT = "VAL_DESC_TXT";

	public static final String VAL_CMNTS = "VAL_CMNTS";

	public static final String DATA_VALUE = "DATA_VALUE";

	public static final String ADD_COMMENT = "ADD_COMMENT";

	public static final String CREATD_TM_STMP = "CREATD_TM_STMP";

	public static final String CREATD_USER_ID = "CREATD_USER_ID";

	public static final String DATA_ELEMENT_LEN_MIN = "DATA_ELEMENT_LEN_MIN";

	public static final String DATA_ELEMENT_LEN_MAX = "DATA_ELEMENT_LEN_MAX";

	public static final String DATA_VAL_STS = "DATA_VAL_STS";
	
	public static final String VALUE_STR = "Value ";

	/* Reference Data Values END */

	// for E036
	public static final String DIABETES = "DIABETES";

	public static final String DIABETIC = "DIABETIC";

	public static final String DIAB = "DIAB";

	public static final String ERROR_E034 = "E034";

	public static final String ERROR_E035 = "E035";

	public static final String ERROR_E036 = "E036";

	// added for validation of E034
	public static final String E034_DESCRIPTION = "INCORRECT OOP INCLUDE/EXCLUDE MESSAGE";

	// added for validation of E035
	public static final String E035_DESCRIPTION = "PCT VARIABLE GRTR THN 100% OR EQUAL TO 0%";

	// added for validation of E036
	public static final String E036_DESCRIPTION = "DIABETES VARIABLE/HDR RULE/SPS ID CODED IN BX TBL";

	// constants added for validation of E034-Start
	public static final String VARIABLE_DESC_1_E034 = "var.desc.1.e034";
	public static final String VARIABLE_DESC_2_E034 = "var.desc.2.e034";
	public static final String VARIABLE_DESC_3_E034 = "var.desc.3.e034";
	public static final String VARIABLE_DESC_4_E034 = "var.desc.4.e034";
	public static final String MESSAGE_1_E034 = "message.1.e034";
	public static final String MESSAGE_2_E034 = "message.2.e034";
	public static final String MESSAGE_3_E034 = "message.3.e034";
	public static final String MESSAGE_4_E034 = "message.4.e034";
	public static final String EB03_VARIABLES_E034 = "eb03.variables.e034";

	public static final String COP_IND_VAR_LG_E034 = "copay.indicator.variables.LG.e034";
	public static final String DED_IND_VAR_LG_E034 = "ded.indicator.variables.LG.e034";
	public static final String COP_IND_VAR_ISG_E034 = "copay.indicator.variables.ISG.e034";
	public static final String DED_IND_VAR_ISG_E034 = "ded.indicator.variables.ISG.e034";

	// constants added for validation of E034-End
	// constants added for validation of E008-Start
	public static final String MIN_HEAD_WPD_E008 = "minorHeadings.wpd.e008";
	public static final String MIN_HEAD_EWPD_E008 = "minorHeadings.ewpd.e008";
	// constants added for validation of E008-End
	// constants added for validation of E025-Start
	public static final String BEN_DESC_EXCLUDED_E025 = "ben.desc.excluded.e025";
	// constants added for validation of E025-End

	public static final String SERVICE_TYPE_CODES_30 = "serviceTypeCodeRequest30";
	public static final String EB_11 = "EB11";
	public static final String MESSAGE_TYPE_URGENT_OR_SPECIALIST = "messageTypeUrgentOrSpecialist";
	
	public static final String WEB_CONSTANT = "webconstant";
	
	public static final String COMMA = ",";
	public static final String CODED_VALUE = "CODEDVALUE";
	
	public static final String EBX = "EBX";
	public static final String LG = "LG";
	public static final String ISG = "ISG";
	
	public static final String LOCKEDMSG = "VARIABLE MAPPING IS LOCKED";
	public static final String UNLOCKEDMSG = "VARIABLE MAPPING IS UNLOCKED";
	public static final String LOCKED = "LOCKED";
	public static final String UNLOCKED = "UNLOCKED";
	
	public static final String LOCK_ERROR_MSG = "Mapping is already locked";
	public static final String LOCK_SUCESS_MSG = "Mapping locked sucessfully";
	public static final String UNLOCK_ERROR_MSG = "Mapping is not locked";
	public static final String UNLOCK_SUCESS_MSG = "Mapping unlocked sucessfully";
	
	// January Release Constants
	public static final String CONTRACT_SYSTEM_LG = "LG";
	public static final String CONTRACT_SYSTEM_ISG = "ISG";
	public static final String CONTRACT_SYSTEM_LG_ISG = "LG,ISG";

/***************************SSCR 14181 April 2012 Release  START**********************************/	
	//Manage Header Rule to EB03
	public static final String MANAGE_HEADERRULE_TO_EB03_PAGE = "manageHeaderRuleToEB03";
	public static final String VIEW_HISTORY_EB03_HEADERRULE = "headerRuleToEB03ViewHistoryPopup";
	public static final String NOT_APPLICABLE_STATUS = "NOT_APPLICABLE";
	public static final String ADDED_STATUS = "ADDED";
	public static final String DELETE_ALL_MAPG_STATUS_START = "HEADER RULE ASSOCIATIONS AGAINST EB03 = ";
	public static final String DELETE_ALL_MAPG_STATUS_END = " HAS BEEN REMOVED";
	public static final String HEADER_RULE_VAL = "headerRuleVal";
	public static final String HEADER_RULE_DESC = "headerRuleDesc";
	public static final String EB03_ID_FROM_EDIT_SAVE_ACTION = "eb03IdFromEditSaveAction";
	public static final String EB03_DESC_FROM_EDIT_SAVE_ACTION = "eb03DescriptionFromEditSaveAction";
	
	
	//Manage Data Type to EB01
	public static final String MANAGE_DATATYPE_TO_EB01_PAGE = "manageDataTypeToEB01";
	public static final String EB01_DATA_TYPE_RESULT_PAGE = "dataTypeToEB01SearchResult";
	public static final String EB01_DATA_TYPE_EDIT_PAGE = "dataTypeToEB01EditMapping";
	public static final String EB01_DESC = "EB01DESC";
	public static final String DATA_TYPE = "DATATYPE";
	public static final String DATA_TYPE_DESC = "DATATYPEDESC";
	public static final String DATA_TYPE_VALUE_LIST = "dataTypeValueList";
	public static final String DATA_TYPE_VO_LIST_EDIT = "dataTypeVOEdit";
	public static final String EB01 = "eb01";
	public static final String DATATYPE = "dataType";
	public static final String KEY = "key";
	public static final String DELETE_ACTION_EB01ID = "deleteAllActionEB01Id";
	public static final String DELETE_ACTION_DATATYPE = "deleteAllActionDataTypeValues";
	public static final String USER_COMMENTS_DELETE = "deleteAllUserComments";
	public static final String USER_COMMENTS_SAVE = "saveUserComments";
	public static final String EB01_VALUE_FETCH = "searchActionEB01Id";
	public static final String DATATYPE_VALUE_FETCH = "searchActionDataTypeId";
	public static final String EB01_VALUE_SEARCH = "searchActionEB01IdSave";
	public static final String DATATYPE_VALUE_SEARCH = "searchActionDataTypeIdSave";
	public static final String EB01_VALUE_UPDATE = "eb01ValueUpdate";
	public static final String EB01_DESC_UPDATE = "eb01DescUpdate";
	public static final String EB01_VALUE_EDIT = "eb01ValueEdit";
	public static final String EB01_DESC_EDIT = "eb01DescEdit";
	public static final String VIEW_HISTORY_EB01_DTYPE = "dataTypeToEB01ViewHistoryPopup";
	
	//E038
	public static final String EB03_AL = "AL";
	public static final String VISION_MINOR_HEADINGS_FOR_E038 = "minorHeadings.wpd.e038";
	public static final String VISION_MAJOR_HEADING_FOR_E038 = "majorHeading.wpd.e038";
	public static final String MINOR_HEADING_DESC_FOR_E038 = "minorHeadings.description.wpd.e038";
	public static final String VARIABLE_DESCRIPTION_EYE_EXAM = "EYE EXAM";
	public static final String VARIABLE_DESCRIPTION_VISION_EXAM = "VISION EXAM";
	public static final String VARIABLE_OPTOMSERVCVD = "OPTOMSERVCVD";
	// added for validation of E038
	public static final String ERROR_E038 = "E038";
	// added for validation of E038
	public static final String E038_DESCRIPTION = "VISION COVERAGE NOT CODED";
	//added to display in the report sps/variable field
	public static final String VISION = "VISION";
	
	//E039
	public static final String EB03_35 = "35";
	public static final String DENTAL_MINOR_HEADINGS_FOR_E039 = "minorHeadings.wpd.e039";
	public static final String DENTAL_MINOR_HEADING_FOR_E039 = "majorHeading.wpd.e039";
	public static final String VARIABLE_DNTLSERVSCOV = "DNTLSERVSCOV";
	// added for validation of E038
	public static final String ERROR_E039 = "E039";
	// added for validation of E038
	public static final String E039_DESCRIPTION = "DENTAL COVERAGE NOT CODED";
	//added to display in the report sps/variable field
	public static final String DENTAL = "DENTAL";
/***************************SSCR 14181 April 2012 Release  END**********************************/	
	
	public static final String ERROR_E040 = "E040";
	
//Constants for EB06validation
	//added as part of April Release Req: B6
	
	public static final String WEEK = "WEEK";
	public static final String EB06_35 = "35";
	public static final String ADMIT = "ADMIT";
	public static final String BEN = "BEN";	
	public static final String CAL = "CAL";	
	public static final String YR = "YR";
	public static final String DAY_DY = "DY";	
	public static final String DAY_DYS = "DYS";	
	public static final String HOUR_HR = "HR";	
	public static final String HOUR = "HOUR";	
	public static final String HOURS = "HOURS";	
	public static final String MONTHS = "MONTHS";	
	public static final String VISIT = "VISIT";	
	public static final String MONTH = "MONTH";	
	public static final String LIFE = "LIFE";	
	public static final String LIFETIME = "LIFETIME";	
	public static final String LFT = "LFT";	
	public static final String LFTMAX = "LFTMAX";	
	public static final String LIFET = "LIFET";	
	public static final String LIF = "LIF";	
	public static final String LFTM = "LFTM";	
	public static final String LTM = "LTM";	
	public static final String LIFETME = "LIFETME";
	public static final String WEEK_WKS = "WKS";	
	public static final String WEEK_WK = "WK";	
	public static final String ADMISSION = "ADMISSION";	
	public static final String ADM = "ADM";
	public static final String ADMT = "ADMT";
	public static final String VISITS = "VISITS";
	public static final String DAY_DAYS = "DAYS";
	
	//Constants for BXNI June 2012 Release --START
	public static final String MESSAGE = "MESSAGE";
	public static final String MAPPED_UMRULE = "MAPPED_UMRULE";
	public static final String SENSITIVE = "sensitive";
	public static final String JSON_VIEW = "jsonView";
	public static final String START_AGE_NAME = "START_AGE";
	public static final String END_AGE_NAME = "END_AGE";
	public static final String START_AGE_FROM_PAGE = "startAge";
	public static final String END_AGE_FROM_PAGE = "endAge";
	
	public static final String EB03_CG = "CG";
	public static final String EB03_CE = "CE";
	public static final String EB03_CH = "CH";
	public static final String EB03_CF = "CF";
	public static final String EB03_CJ = "CJ";
	public static final String EB03_CI = "CI";
	public static final String EB03_A7 = "A7";
	public static final String EB03_A8 = "A8";
	public static final String EB03_A6 = "A6";

	public static final String MANAGE_STANDARD_MSG_PAGE = "standardmessage";
	public static final String STD_MESSAGE = "standardMsg";
	public static final String STD_MSG_RESULT_PAGE = "standardmessagesearchresult";
	public static final String STD_MSG_RESULT_LIST = "standardMsgList";
	public static final String STD_MESSAGE_EDIT = "standardMsgEdit";
	public static final String STD_MSG_EDIT = "stdMsgEdit";
	public static final String STD_MESSAGE_EDIT_PAGE = "standardmessageedit";
	public static final String STD_MESSAGE_TO_FETCH = "stdMsgSearchActionSave";
	public static final String STD_MESSAGE_TO_UPDATE = "stdMsgValueUpdateSaveAction";
	public static final String STD_MESSAGE_TO_SAVE = "standardMsgUpdatedNewSave";
	public static final String STD_MESSAGE_TO_FETCH_DELETE = "searchActionStandardMessage";
	public static final String STD_MESSAGE_TO_DELETE = "deleteActionStandardMessage";
	public static final String STD_MESSAGE_TO_FETCH_CREATE = "stdMsgCreateActionSearch";
	public static final String STD_MESSAGE_TO_SAVE_CREATE = "stdMsgCreateActionSave";
	public static final String STD_MESSAGE_HISTORY_PAGE = "standardmessagehistory";
	public static final String STD_MESSAGE_HISTORY_LIST = "stdMsgHistoryList";
	
	//E041
	public static final String ERROR_E041 = "E041";
	public static final String E041_DESCRIPTION = "Discrepancy in S7/S8 fields";
	public static final String START_AGE = "Start Age";
	public static final String END_AGE = "End Age";
	public static final String STARTAGE_LESS_ENDAGE = "Start Age must be less than End Age";
	public static final String INVALID_ENDAGE = "The value coded for end age should be between 1-100";
	public static final String INVALID_STARTAGE = "The value coded for start age should be between 1-100";
	public static final String INVALID_STARTAGE_ENDAGE = "The value coded for start and end age should be between 1-100";
	public static final String MESSAGE_TEXT = "Message Text";
	//CR-18Changes (BXNI Nov Release)
	public static final String MULTIPLE_AGE_VALUES = "Multiple Start/End Age values present.";
	//Error E041 Descriptions when Variable not coded in the contract --BXNI Defect Fix
	public static final String START_AGE_VARIABLE_NOT_CODED = "Start Age Variable Not Coded in the Contract";
	public static final String END_AGE_VARIABLE_NOT_CODED = "End Age Variable Not Coded in the Contract";
	public static final String START_AGE_END_AGE_VARIABLE_NOT_CODED = "Start Age and End Age Variable Not Coded in the Contract";
	
	public static final String ENCODED_SINGLE_QUOTATION  = "&#039;";
	
	//Constants for BXNI June 2012 Release -- END
	
	// Constants for SSCR 16331 --Start
	public static final String HMO_PROD_FAMILY_LG = "hmo.product.family.lg";
	public static final String HMO_PRICING_INFO_COVERAGE_CODE = "MED";
	public static final String HMO_PRICING_INFO_NETWORK_CODE = "HMO";
	public static final String HMO_CONRACT_ISG = "hmo.contract.isg";
	public static final String NON_HMO_CONRACT_ISG = "non.hmo.contract.isg";
	public static final String CONRACT_GROUP_NM = "GROUP_NM";
	public static final String VARIABLE_ACLMSTPA = "ACLMSTPA";
	public static final String CONTRACT_GROUP_NM_MCS = "MCS";
	public static final String CONTRACT_GROUP_NM_JAA = "JAA";
	public static final String EB01_BENEFIT_COVERED = "BC";
	public static final String VARIABLE_CODED_VALUE_N = "N";
	public static final String MAJ_HEAD_EWPD_E008 = "majorHeadings.ewpd.e008";
	public static final String BC_EWPD_E008_OUTPATIENT_HOSP_BNFTS = "OUTPATIENT HOSPITAL BENEFITS";
	public static final String BNFT_EWPD_E008_MEDICARE = "MEDICARE PART B DEDUCTIBLE";
	public static final String BC_EWPD_E008_SEXUAL_DISORDERS = "SEXUAL DISORDERS";
	public static final String BNFT_EWPD_E008_SEXUAL_DYSFUNCTION = "SEXUAL DYSFUNCTION RX";
	// Constants for SSCR 16331 --End
	
	//Constants for BXNI November 2012 Release -- START
	public static final String RESTRICTED_EB03 = "restricted.EB03";
	public static final String RESTRICTED_EB06 = "restricted.EB06";
	
	//STC-EB11 association module --START
	public static final String MANAGE_SRVC_TYP_EB11_PAGE = "manageServiceTypeCodeToEB11";
	public static final String SRVC_TYP_EB11_RESULT_PAGE = "serviceTypeCodetoEB11SearchResult";
	public static final String SRVC_TYP_EB11_VIEW_PAGE = "serviceTypeCodetoEB11View";
	public static final String SRVC_TYP_EB11_EDIT_PAGE = "serviceTypeCodeToEB11Edit";
	public static final String SRVC_TYP_EB11_CREATE_PAGE = "serviceTypeCodeToEB11Create";
	public static final String SERVICE_TYPE_MAPPING_VIEW_HISTORY = "serviceTypeMappingViewHistoryPopup";
	public static final String SRVC_TYP_EB11_EDIT_PAGE_POPOUT = "serviceTypeCodeToEB11EditPopOut";

	public static final String LOB_NAME = "lobName";
	public static final String MBU_NAME = "mbuName";
	public static final String LOB_ID   = "lobId";
	public static final String SSB_INDICATOR_N   = "N";
	public static final String SSB_INDICATOR_Y   = "Y";
	
	public static final String SRVC_TYP_EB11_RESULT_LIST = "resultList";
	public static final String SRVC_TYP_EB11_FETCH_RESULT_LIST = "fetchResultList";
	public static final String SRVC_TYP_EB11_VO_LIST = "servicetypeMappingsList";
	public static final String ACTION_CREATE = "create";
	public static final String ACTION_UPDATE = "update";
	
	//Messages to be displayed for Service Type Assn 
	public static final String SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG = "Service Type Association saved successfully.";
	public static final String NO_MODFCN_TO_SAVE = "No Modification to be saved.";
	
	public static final String AUDIT_TRIAL_ACTION_DELETE_ALL = "deleteAll";
	//STC-EB11 association module --End
	
	public static final String VS = "VS";
	public static final String HS = "HS";
	public static final String DY = "DY";
	public static final String MN = "MN";
	public static final String YY = "YY";
	
	
	public static final String FORMAT_VALUES_FOR_EB06_EB09_VALIDATION = "format.EB06.EB09";
	//EB06-HSD01 validation for variable/sps
	public static final String FORMAT_VALUES_FOR_EB06_HSD01_VALIDATION = "format.EB06.HSD01";
	//Addition of Start Date and End date.
	public static final String BENEFIT_EFFECTIVE_DATE = "Effective Date";	
	public static final String BENEFIT_TERMINATION_DATE = "Termination Date";

	public static final String ERROR_E043 = "E043";

	public static final String EB03_PT = "PT";
	public static final String EB03_AE = "AE";
	public static final String EB03_98 = "98";
	public static final String EB03_BY = "BY";
	public static final String EB03_4 = "4";
	public static final String EB03_73 = "73";

	public static final String ERROR_E042 = "E042";
	public static final String HSD05_EB06_VALUES = "hsd05.eb06.values";

	public static final String NAMESPACE_V4 = "v5.schemas.ebx.ets.wellpoint.com";		
	
	public static final String ARIAL = "Arial";
	public static final short VERTICAL_TOP = 0*0;
	public static final String BLANK_SPACE = " ";
	public static final String MSG_CONTSA = "CONTSA";
	public static final String MSG_CONTSA_WITH_SPACE = "CONTSA ";
	//Constants for BXNI November 2012 Release -- END

	// BXNI CR29
	public static final String UM = "UM";
	
	//eBX Project PV 25928 - 3 tier DED _271 WGS response March 2015 release

	public static final String NOTE_TYPE_D = "D";
	public static final String NOTE_TYPE_006 = "006";
	
	//SSCR 16332-Constants
	public static final String NOTE_TYPE_B = "B";
	public static final String NOTE_TYPE_A = "A";
	public static final String NOTE_TYPE_013 = "013";
	public static final String NOTE_TYPE_001 = "001";
	public static final String HSD05_22 = "22";
	public static final String HSD05_23 = "23";
	public static final String HSD05_25 = "25";
	public static final String HSD05_32 = "32";
	
	//BXNI CR 35
	public static final String HSD01_BLANK = "";
	public static final String HSD05_BLANK = "";
	
	//Constants added for SSCR 19537
	public static final String EB03_III02_VALIDATION_LIST = "III02.EB03.LIST";
	public static final String MAIN_MAPPING_VAL = "BX_CNTRCT_VAR_MAPG_VAL";
	public static final String TEMP_MAPPING_VAL = "TEMP_BX_CNTRCT_VAR_MAPG_VAL";
	public static final String NEW_EB03_VALUES = "newEb03Values";
	public static final String EXISTING_EB03_VALUES = "existingEb03Values";
	public static final String EXISTING_EB03_ASSN = "existingEb03Assn";
	public static final String VIEW_STATUS_FOR_CUSTOM_MESSAGE = "VIEW_CUSTOM_MESSAGE";
	public static final String CUSTOM_MESSAGE_CREATE = "CUSTOM_MESSAGE_CREATE";
	public static final String III02_DESC = "III02DESC";
	public static final String NOTE_TYPE_DESC = "NOTETYPEDESC";
	
	// Constants for NM1 Message Segment - December2014 Release
	public static final String NM1_MSG_SGMNT = "2120_LOOP_NM1_MESSAGE_SEGMENT";
	public static final String LOOP2120NM1MESSAGESEGMENT = "2120 LOOP NM1 MESSAGE SEGMENT";
	public static final String BLUE_DISTINCTION_PLUS = "BLUE DISTINCTION PLUS"; 
	public static final String BLUE_DISTINCTION = "BLUE DISTINCTION";
	public static final String NM1_STRING = "NM1";
	// Added as per the change from WGS
	public static final String BLUE_DISTINCTION_PLUS_SIGN = "BLUE DISTINCTION+";
	
	// POR Wave 2 Oct 2015 Changes
	public static final String EB01_UM = "UM";
	public static final String EB03_ORTHONET_LIST = "EB03.ORTHONET.LIST";
	public static final String EB03_ASH_LIST = "EB03.ASH.LIST";
	public static final String MESSAGE_ORTHONET = "ORTHONET";
	public static final String MESSAGE_AMERICAN_SPECIALTY_HEALTH = "AMERICAN SPECIALTY HEALTH";
	public static final String MESSAGE_AIM_PREAUTH = "AIM PREAUTH";
	
	//DEC Rel 2015
	public static final String EB03_WINFERTILITY_LIST = "EB03.WINFERTILITY.LIST";
	public static final String MESSAGE_WINFERTILITY = "WINFERTILITY";
	public static final String EB03_61 = "61";
	public static final String EB03_83 = "83";
	//FEB REL 2022
	public static final String EBX_SPIDER_CREATE = "CREATE";
	public static final String EBX_SPIDER_EDIT = "EDIT";
	public static final String EBX_SPIDER_LOCATE = "LOCATE";
	public static final String APPLICABLE_STATUS = "APPLICABLE";
	
	public static final String  forward="forward";
	public static final String  FORWARD="FORWARD";
	public static final String  redirect="redirect";
	public static final String  REDIRECT="REDIRECT";
	
}
