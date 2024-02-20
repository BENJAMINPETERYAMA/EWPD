/*
 * <ValidatorConstants.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.domain.service;

public class ValidatorConstants {

	public static final String INVALID_MAPPING = "INVALID_MAPPING";
	public static final String VALIDATION_SUCCESS = "VALIDATION_SUCCESS";
	// public static final String MAPPING_NOT_FOUND = "MAPPING_NOT_FOUND";
	public static final String MAPPING_MANDATORY = "MAPPING_MANDATORY";
	public static final String MAPPING_ALREADY_EXISTS = "MAPPING_ALREADY_EXISTS";
	public static final String MULTIPLE_MAPPING = "MULTIPLE_MAPPING";
	public static final String INVALID_HIPPACODE_VALUE = "INVALID_HIPPACODE_VALUE";
	public static final String INVALID_ACCUM_VALUE = "INVALID_ACCUM_VALUE";
	public static final String LIMIT_EXCEEDED = "LIMIT_EXCEEDED";
	public static final String EB03_INVALID = "EB03_INVALID";
	public static final String EB06_WARNING_MESSAGE_VAR = "EB06_WARNING_MESSAGE_VAR";
	public static final String EB06_WARNING_MESSAGE_SPS = "EB06_WARNING_MESSAGE_SPS";
	public static final String EB09_INVALID = "EB09_INVALID";
	public static final String EB09_NOT_CODED_FOR_AGE = "EB09_NOT_CODED_FOR_AGE";

	public static final String HSD02_EB_SEGMENT = "HSD02_EB_VALIDATION_FAILURE";
	public static final String DEPENDENCY_CHECK_FAILURE_FOR_HSD = "DEPENDENCY_CHECK_FAILURE_FOR_HSD";
	public static final String DEPENDENCY_CHECK_FAILURE = "DEPENDENCY_CHECK_FAILURE";
	// public static final String INVALID_ACCUM = "INVALID_ACCUM";
	public static final String ACCUM_NOT_REQD_INDICATOR_WARNING = "ACCUM_NOT_REQD_INDICATOR_WARNING";
	 //Warning message introduced in eBX eWPD which will throw if EB03 contains a sensitive and a non sensitive benefit -- July Release
    public static final String WARNING_MESSAGE_SENSITIVE_EB03_HEADERRULE = "WARNING_MESSAGE_SENSITIVE_EB03_HEADERRULE";
    public static final String WARNING_MESSAGE_SENSITIVE_EB03_VARIABLE = "WARNING_MESSAGE_SENSITIVE_EB03_VARIABLE";
	public static final String E030_ERROR_WARNING = "E030_ERROR_WARNING";
	public static final String E026_ERROR_WARNING = "E026_ERROR_WARNING";
	public static final String E066_ERROR_WARNING = "E066_ERROR_WARNING";
	public static final String E029_ERROR_WARNING_WPD = "E029_ERROR_WARNING_WPD";
	public static final String W029_ERROR_WARNING_WPD = "W029_ERROR_WARNING_WPD";
	public static final String WB029_ERROR_WARNING_WPD = "WB029_ERROR_WARNING_WPD";
	public static final String E029_ERROR_WARNING_eWPD = "E029_ERROR_WARNING_eWPD";
	public static final String W022_ERROR_WARNING_eWPD = "W022_ERROR_WARNING_eWPD";
	public static final String WB022_ERROR_WARNING_eWPD = "WB022_ERROR_WARNING_eWPD";
	public static final String E100_ERROR_WARNING_eWPD = "E100_ERROR_WARNING_eWPD";

	public static final double HSD02_MIN = 1;
	public static final double HSD02_MAX = 15;
	public static final double HSD04_MIN = 1;
	public static final double HSD04_MAX = 6;
	public static final double HSD06_MIN = 1;
	public static final double HSD06_MAX = 999;

	public static final String INVALID_HSD02 = "INVALID_HSD02";
	public static final String INVALID_HSD04 = "INVALID_HSD04";
	public static final String INVALID_HSD06 = "INVALID_HSD06";

	public static final String DEDUCTIBLE = "C";
	public static final String OUT_OF_POCKET = "G";
	public static final String DEDUCTIBLE_WAIVED = "DW";
	public static final String LIMITATION = "F";
	public static final String CO_PAYMENT = "B";
	public static final String CONTACT_FOLLOWING_ENTITY_U = "U";
	public static final String COINSURANCE = "A";
	public static final String BENEFIT_COVERED = "BC";

	public static final String VARIABLE_FORMAT_AGE = "AGE";
	public static final String VARIABLE_DATA_TYPE_DOL = "DOL";
	public static final String FORMAT_PCT = "PCT";

	public static final String DESCRIPTION_COP_MX = "COP_MX";
	public static final String DESCRIPTION_SURFACE = "SURFACE";

	public static final String DESCRIPTION_BLANK = " ";

	public static final String DATA_TYPE_DOL = "DOL";
	public static final String FORMAT_YN = "Y/N";

	public static final String FORMAT_VISIT = "VST";
	public static final String FORMAT_HOURS = "HRS";
	public static final String FORMAT_DAY = "DAY";
	public static final String FORMAT_DAYS = "DAYS";
	public static final String FORMAT_MTH = "MTH";
	public static final String FORMAT_MTHS = "MTHS";
	public static final String FORMAT_YEARS = "YRS";
	public static final String FORMAT_OCCURENCE = "OCC";
	public static final String FORMAT_OCRS = "OCRS";
	public static final String FORMAT_HRS = "HRS";
	public static final String FORMAT_DOL = "DOL";

	public static final String FORMAT_VAL = "VAL";
	public static final String FORMAT_QUESTION = "QUESTION";

	public static final String SERVICE_YEAR = "22";
	public static final String CALENDAR_YEAR = "23";
	public static final String CONTRACT = "25";
	public static final String LIFETIME = "32";
	public static final String LIFETIME_REMAINING = "33";

	public static final String IN_VITRO_FERTILIZATION = "61";
	public static final String MATERNITY = "69";
	public static final String INFERTILITY = "83";
	public static final String ABORTION = "84";
	public static final String PSYCHOTHERAPY = "A6";
	public static final String PSYCHIATRIC_INPATIENT = "A7";
	public static final String PSYCHIATRIC_OUTPATIENT = "A8";
	public static final String SUBSTANCE_ABUSE = "AI";
	public static final String HEALTH_BENEFIT_PLAN_COVERAGE = "30";
	public static final String HEALTH_BENEFIT_PLAN_COVERAGE1 = "SP";
	public static final String MAPPING_NOT_REQUIRED_FOR_EB02 = "MAPPING_NOT_REQUIRED_FOR_EB02";
	public static final String MAPPING_REQUIRED_FOR_EB02 = "MAPPING_REQUIRED_FOR_EB02";

	public static final String DUPLICATE_EB03_VALUES = "DUPLICATE_EB03_VALUES";
	public static final String DUPLICATE_HIPPACODE_VALUE = "DUPLICATE_HIPPACODE_VALUE";

	public static final String MAPPING_EXISTS = "create.mapping.exists";

	public static final String MAPPING_LOCKED_ANOTHER_USER = "MAPPING_LOCKED_ANOTHER_USER";

	public static final String MAPPING_MANDATORY_SPS = "MAPPING_MANDATORY_SPS";
	public static final String MAPPING_MANDATORY_SPS_HSD02 = "MAPPING_MANDATORY_SPS_HSD02";
	// new validation messages for EB01 - section 3
	public static final String WARNING_MESSAGE_EB01_B_PCT = "WARNING_MESSAGE_EB01_B_PCT";
	public static final String WARNING_MESSAGE_EB01_A_B_PCT = "WARNING_MESSAGE_EB01_A_B_PCT";
	public static final String WARNING_MESSAGE_EB01_YN = "WARNING_MESSAGE_EB01_YN";
	public static final String WARNING_MESSAGE_EB01_F = "WARNING_MESSAGE_EB01_F";
	public static final String WARNING_MESSAGE_EB01_U = "WARNING_MESSAGE_EB01_U";
	public static final String WARNING_MESSAGE_EB01_EB06_ACCUM = "WARNING_MESSAGE_EB01_EB06_ACCUM";
	public static final String WARNING_MESSAGE_EB03 = "WARNING_MESSAGE_EB03";
	public static final String ERROR_MESSAGE_EB09_SPS = "ERROR_MESSAGE_EB09_SPS";
	public static final String ERROR_MESSAGE_EB09_INVALID_SPS = "ERROR_MESSAGE_EB09_INVALID_SPS";

	// new validation messages added for EB06-May release
	public static final String WARNING_MESSAGE_EB06_7or13_DAYorDAYS = "WARNING_MESSAGE_EB06_7or13_DAYorDAYS";
	public static final String WARNING_MESSAGE_EB06_6_HRS = "WARNING_MESSAGE_EB06_6_HRS";
	public static final String WARNING_MESSAGE_EB06_34_MTHSorMTH = "WARNING_MESSAGE_EB06_34_MTHSorMTH";
	public static final String WARNING_MESSAGE_EB06_27_VST = "WARNING_MESSAGE_EB06_27_VST";
	public static final String WARNING_MESSAGE_EB06_27_OCRSorOCC = "WARNING_MESSAGE_EB06_27_OCRSorOCC";
	public static final String WARNING_MESSAGE_EB06_YRS = "WARNING_MESSAGE_EB06_YRS";
	public static final String WARNING_MESSAGE_eWPD_EB06_YRS = "WARNING_MESSAGE_eWPD_EB06_YRS";
	public static final String WARNING_MESSAGE_EB06_EB01 = "WARNING_MESSAGE_EB06_EB01";

	public static final String EB01_NOT_CODED_FOR_DOL = "EB01_NOT_CODED_FOR_DOL";

	// AUGUST RELEASE DECLARATIONS

	// 8.2 && 8.3
	public static final String DESCRIPTION_COPAY = "COPAY";
	public static final String DESCRIPTION_COPAYMENT = "COPAYMENT";
	public static final String DESCRIPTION_C0_PAYMENT = "CO-PAYMENT";
	public static final String DESCRIPTION_CP = "CP";
	public static final String DESCRIPTION_COP = "COP";
	public static final String DESCRIPTION_CO_PAY = "CO-PAY";

	public static final String WARNING_MESSAGE_EB01_B_COPAY_eWPD = "WARNING_MESSAGE_EB01_B_COPAY_eWPD";
	public static final String WARNING_MESSAGE_EB01_B_COPAY = "WARNING_MESSAGE_EB01_B_COPAY";

	// 8.4 && 8.5
	public static final String DESCRIPTION_DEDUCTIBLE = "DEDUCTIBLE";
	public static final String DESCRIPTION_DED = "DED";
	public static final String DESCRIPTION_SERVICE_DEDUCTIBLE = "SERVICE_DEDUCTIBLE";
	public static final String WARNING_MESSAGE_EB01_C_DEDUCTIBLE = "WARNING_MESSAGE_EB01_C_DEDUCTIBLE";
	public static final String WARNING_MESSAGE_EB01_C_DEDUCTIBLE_eWPD = "WARNING_MESSAGE_EB01_C_DEDUCTIBLE_eWPD";

	// 8.6 && 8.7
	public static final String DESCRIPTION_PENALTY = "PENALTY";
	public static final String DESCRIPTION_PNLTY = "PNLTY";
	public static final String DESCRIPTION_PENLTY = "PENLTY";
	public static final String DESCRIPTION_MAXIMUM = "MAXIMUM";
	public static final String DESCRIPTION_MAX = "MAX";
	public static final String DESCRIPTION_PER = "PER";
	public static final String DESCRIPTION_MX = "MX";
	public static final String DESCRIPTION_LIMITATIONS = "LIMITATIONS";
	public static final String DESCRIPTION_LMT = "LMT";
	public static final String DESCRIPTION_LIMIT = "LIMIT";
	public static final String DESCRIPTION_LIM = "LIM";
	public static final String WARNING_MESSAGE_EB01_F_MPL = "WARNING_MESSAGE_EB01_F_MPL";
	public static final String WARNING_MESSAGE_EB01_F_MPL_eWPD = "WARNING_MESSAGE_EB01_F_MPL_eWPD";

	// 8.8 && 8.9
	public static final String DESCRIPTION_OUT_OF_POCKET = "OUT OF POCKET";
	public static final String DESCRIPTION_OUT_OF_POCKET1 = "OUT-OF-POCKET";
	public static final String DESCRIPTION_OOP = "OOP";
	public static final String DESCRIPTION_STL = "STL";
	public static final String DESCRIPTION_SLL = "SLL";
	public static final String WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_SLL = "WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_SLL";
	public static final String WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_STL_eWPD = "WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_STL_eWPD";

	// 8.10

	public static final String DESCRIPTION_DEDUCTIBLE_WAIVED = "DEDUCTIBLE WAIVED";
	public static final String DESCRIPTION_DED_WAIVED = "DED WAIVED";
	public static final String DESCRIPTION_DED_WAIVE = "DED WAIVE";
	public static final String DESCRIPTION_DEDUCT_WAIVED = "DEDUCT WAIVED";
	public static final String DESCRIPTION_DED_WVD = "DED WVD";
	public static final String DESCRIPTION_DED_WAV = "DED WAV";
	public static final String WARNING_MESSAGE_EB01_DW_DEDUCTIBLE_WAIVED = "WARNING_MESSAGE_EB01_DW_DEDUCTIBLE_WAIVED";

	// 8.11
	public static final String DESCRIPTION_COVERED = "COVERED";
	public static final String DESCRIPTION_COVER = "COVER";
	public static final String DESCRIPTION_COVD = "COVD";
	public static final String DESCRIPTION_COV = "COV";
	public static final String DESCRIPTION_PAYABLE = "PAYABLE";
	public static final String DESCRIPTION_PAY = "PAY";
	public static final String WARNING_MESSAGE_EB01_BC_COVERED_PAYABLE = "WARNING_MESSAGE_EB01_BC_COVERED_PAYABLE";

	// 9.1
	public static final String TRUE_SPECIALIST = "SP";
	public static final String CATEGORY_PHO = "PHO";
	public static final String CATEGORY_PHOMM = "PHOMM";
	public static final String SHARED_SPECIALIST = "SS";
	public static final String WARNING_MESSAGE_EB03_SP_SS_PHO = "WARNING_MESSAGE_EB03_SP_SS_PHO";
	public static final String DESCRIPTION_SPE = "SPE";
	public static final String DESCRIPTION_SPC = "SPC";

	public static final String WARNING_MESSAGE_TEXT_PENALTY = "WARNING_MESSAGE_TEXT_PENALTY";
	public static final String WARNING_MESSAGE_TEXT_BLANK = "WARNING_MESSAGE_TEXT_BLANK";

	// AUGUST RELEASE DELCARTIONS ENDS
	
// CONSTANTS FOR RULE 30 VALIDATION, APRIL RELEASE REQ: B4
	
	public static final String EB06_WARNING_MESSAGE_FOR_BENEFIT_YEAR = "EB06_WARNING_FOR_BENEFIT_YEAR";
	public static final String EB06_WARNING_MESSAGE_FOR_CALENDAR_YEAR = "EB06_WARNING_FOR_CALENDAR_YEAR";
	public static final String EB06_WARNING_MESSAGE_FOR_DAYS = "EB06_WARNING_FOR_DAYS";
	public static final String EB06_WARNING_MESSAGE_FOR_HOURS = "EB06_WARNING_FOR_HOURS";
	public static final String EB06_WARNING_MESSAGE_FOR_MONTHS = "EB06_WARNING_FOR_MONTHS";
	public static final String EB06_WARNING_MESSAGE_FOR_VISIT = "EB06_WARNING_FOR_VISIT";
	public static final String EB06_WARNING_MESSAGE_FOR_LIFETIME = "EB06_WARNING_FOR_LIFETIME";
	public static final String EB06_WARNING_MESSAGE_FOR_WEEK = "EB06_WARNING_FOR_WEEK";
	public static final String EB06_WARNING_MESSAGE_FOR_ADMIT = "EB06_WARNING_FOR_ADMIT";

// CONSTANTS ADDED AS PART OF BXNI JUNE2012 RELEASE
	
	public static final String WARNING_MESSAGE01_FOR_EB03_SERVICE_TYPE_CODES = "WARNING_MESSAGE01_FOR_EB03_SERVICE_TYPE_CODES";
	public static final String WARNING_MESSAGE02_FOR_EB03_SERVICE_TYPE_CODES = "WARNING_MESSAGE02_FOR_EB03_SERVICE_TYPE_CODES";
	public static final String WARNING_MESSAGE03_FOR_EB03_SERVICE_TYPE_CODES = "WARNING_MESSAGE03_FOR_EB03_SERVICE_TYPE_CODES";
	public static final String HSD_VALIDATION_SUCCESSFUL = "HSD_VALIDATION_SUCCESSFUL";
	public static final String DEPENDENCY_CHECK_FAILURE_FOR_HSD_7_8 = "DEPENDENCY_CHECK_FAILURE_FOR_HSD_7_8";
	public static final String EB01_EB02_DEPENDENCY_VALIDATION = "EB01_EB02_DEPENDENCY_VALIDATION";
	public static final String WARNING_MESSAGE_VAR_SENSITIVE_IND_PRESENT = "WARNING_MESSAGE_VAR_SENSITIVE_IND_PRESENT";
	public static final String WARNING_MESSAGE_VAR_SENSITIVE_IND_NOT_PRESENT = "WARNING_MESSAGE_VAR_SENSITIVE_IND_NOT_PRESENT";
	public static final String WARNING_MESSAGE_RULE_SENSITIVE_IND_PRESENT = "WARNING_MESSAGE_RULE_SENSITIVE_IND_PRESENT";
	public static final String WARNING_MESSAGE_RULE_SENSITIVE_IND_NOT_PRESENT = "WARNING_MESSAGE_RULE_SENSITIVE_IND_NOT_PRESENT";
	public static final String PROPERTY_FILE_NAME = "messages_en";
	
// CONSTANTS ADDED AS PART OF BXNI NOVEMBER 2012 RELEASE	
	public static final String ERROR_MSG_RESTRICTED_EB03= "ERROR_MSG_RESTRICTED_EB03";
	public static final String ERROR_MSG_RESTRICTED_EB06= "ERROR_MSG_RESTRICTED_EB06";
	public static final String WARNING_MESSAGE_FOR_EB09_BASED_ON_FORMAT = "WARNING_MESSAGE_FOR_EB09_BASED_ON_FORMAT";
	public static final String ERROR_START_END_AGE_MAPPED_FOR_EB09 = "ERROR_START_END_AGE_MAPPED_FOR_EB09";
	public static final String WARNING_MESSAGE_FOR_HSD01_BASED_ON_FORMAT = "WARNING_MESSAGE_FOR_HSD01_BASED_ON_FORMAT";
	public static final String ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05 = "ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05";
	public static final String ERROR_MESSAGE_EB09_MUST_BE_MAPPED_TO_HSD01 = "ERROR_MESSAGE_EB09_MUST_BE_MAPPED_TO_HSD01";
	public static final String ERROR_MESSAGE_BOTH_EB09_HSD01_CANNOT_BE_MAPPED_TOGETHER = "ERROR_MESSAGE_BOTH_EB09_HSD01_CANNOT_BE_MAPPED_TOGETHER";
	public static final String ERROR_MESSAGE_EB09_OR_HSD01_SHOULD_BE_MAPPED = "ERROR_MESSAGE_EB09_OR_HSD01_SHOULD_BE_MAPPED";
	public static final String WARN_MSG_ACCUM_REQUIRED_HSD05_FOR_EB01 = "WARN_MSG_ACCUM_REQUIRED_HSD05_FOR_EB01";
	public static final String WARN_MSG_ACCUM_REQUIRED_EB06_FOR_EB01 = "WARN_MSG_ACCUM_REQUIRED_EB06_FOR_EB01";
	public static final String ERROR_MSG_REQUIRED_EB06_FOR_EB01 = "ERROR_MSG_ACCUM_EB06_FOR_EB01";
	public static final String ERROR_MESSAGE01_FOR_EB03_SERVICE_TYPE_CODES = "ERROR_MESSAGE01_FOR_EB03_SERVICE_TYPE_CODES";
	public static final String ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05_BASED_ON_HSD01 = "ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05_BASED_ON_HSD01";
	public static final String HSD05_WARNING_MESSAGE_VARIABLE = "HSD05_WARNING_MESSAGE_VARIABLE";
	public static final String HSD05_WARNING_MESSAGE_SPS = "HSD05_WARNING_MESSAGE_SPS";
	
	
//CONSTANTS ADDED AS PART OF BXNI DECEMBER 2012 RELEASE
	public static final String WARNING_MESSAGE_EB09_EB06_E020 = "WARNING_MESSAGE_EB09_EB06_E020";
	public static final String WARNING_MESSAGE_HSD05_HSD01_E020 = "WARNING_MESSAGE_HSD05_HSD01_E020";

/* BXNI June 2013 Release Starts */	
// BXNI CR29	
	//public static final String ERROR_MSG_UM_MAPPED_FOR_NON_HMO = "ERROR_MSG_UM_MAPPED_FOR_NON_HMO";
/* BXNI June 2013 Release Ends */	

	//CONSTANTS ADDED AS PART OF SSCR 16332
	public static final String NOTE_TYPE_EB01_F_VALIDATION = "NOTE_TYPE_EB01_F_VALIDATION";
	public static final String NOTE_TYPE_EB01_CG_VALIDATION = "NOTE_TYPE_EB01_CG_VALIDATION";
	public static final String NOTE_TYPE_EB01_F_VALIDATION_CUSTOM_MESSAGE = "NOTE_TYPE_EB01_F_VALIDATION_CUSTOM_MESSAGE";
	public static final String NOTE_TYPE_EB01_CG_VALIDATION_CUSTOM_MESSAGE = "NOTE_TYPE_EB01_CG_VALIDATION_CUSTOM_MESSAGE";
	public static final String NOTE_TYPE_MSG_TEXT_VALIDATION = "NOTE_TYPE_MSG_TEXT_VALIDATION";
	
	// BXNI CR35
	public static final String ACCUMULATOR_EB01_F_VALIDATION = "ACCUMULATOR_EB01_F_VALIDATION";
	public static final String ACCUMULATOR_EB01_AB_VALIDATION = "ACCUMULATOR_EB01_AB_VALIDATION";
	public static final String ACCUMULATOR_EB01_CGF_VALIDATION = "ACCUMULATOR_EB01_CGF_VALIDATION";
	
	/* BXNI- CR37 June Release*/
	public static final String ERROR_START_END_AGE_MAPPED_AND_EB01F_FOR_EB09 = "ERROR_START_END_AGE_MAPPED_AND_EB01F_FOR_EB09";
	public static final String EB06_WARNING_MESSAGE_FOR_EB01 = "EB06_WARNING_MESSAGE_FOR_EB01";
	
	//SSCR 19537 Validation
	public static final String ERROR_FOR_III02_EB03_VALIDATION = "ERROR_FOR_III02_EB03_VALIDATION";
	public static final String ERROR_INDIVIDUAL_MAPPING_PRESENT = "ERROR_INDIVIDUAL_MAPPING_PRESENT";
	public static final String UNMAPPED_HEADER_RULE = "UNMAPPED_HEADER_RULE";
	public static final String NOTE_TYPE_MSG_TEXT_VALIDATION_INDVDL_EB03 = "NOTE_TYPE_MSG_TEXT_VALIDATION_INDVDL_EB03";
	public static final String ERROR_MSG_RESTRICTED_EB03_FOR_IIIO2 = "ERROR_MSG_RESTRICTED_EB03_FOR_III02";
	public static final String INVALID_NOTE_TYPE = "INVALID_NOTE_TYPE";
	public static final String INVALID_III02 = "INVALID_III02";
	public static final String MASSUPDATE_ERROR_INVDL_EB03_ASSN_VAR = "MASSUPDATE_ERROR_INVDL_EB03_ASSN_VAR";
	public static final String MASSUPDATE_ERROR_INVDL_EB03_ASSN_HDR = "MASSUPDATE_ERROR_INVDL_EB03_ASSN_HDR";
	public static final String MASSUPDATE_ERROR_INVDL_EB03_ASSN_CMSG = "MASSUPDATE_ERROR_INVDL_EB03_ASSN_CMSG";
	
	// POR Wave 2 Oct 2015 Changes
	public static final String WARN_MSG_ORTHONET = "WARN_MSG_ORTHONET";
	public static final String WARN_MSG_AIM_SH = "WARN_MSG_AIM_SH";
	public static final String WARN_MSG_AMERICAN_SH = "WARN_MSG_AMERICAN_SH";
	public static final String WARN_MSG_EB03_ORTHONET = "WARN_MSG_EB03_ORTHONET";
	public static final String WARN_MSG_EB03_AIM_SH = "WARN_MSG_EB03_AIM_SH";
	public static final String WARN_MSG_EB03_AMERICAN_SH = "WARN_MSG_EB03_AMERICAN_SH";
	
	//Dec 2015 Rel
	public static final String WARN_MSG_WINFERTILITY = "WARN_MSG_WINFERTILITY";
	public static final String WARN_MSG_EB03_WINFERTILITY = "WARN_MSG_EB03_WINFERTILITY";
	public static final String WARN_MSG_EB03_WINFERTILITY3 = "WARN_MSG_EB03_WINFERTILITY3";
	public static final String ERROR_MSG_EB03_WINFERTILITY = "ERROR_MSG_EB03_WINFERTILITY";
	
	
	public static final String ACCUM_NOT_MAPPED_VAR_VAL = "ACCUM_NOT_MAPPED_VAR_VAL";
	
	public static final String WARNING_MESSAGE_EB03_MULTIPLE = "WARNING_MESSAGE_EB03_MULTIPLE";
	
	public static final String VAL = "VAL";
	
	public static final String WARNING_MESSAGE_EB03_VAR_TYPE_VAL = "WARNING_MESSAGE_EB03_VAR_TYPE_VAL";
}  
