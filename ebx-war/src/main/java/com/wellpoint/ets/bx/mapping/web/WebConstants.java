package com.wellpoint.ets.bx.mapping.web;

public class WebConstants {

	public static final String WARNING_MESSAGES = "warning_messages";
	public static final String ERROR_MESSAGES = "error_messages";
	public static final String INFO_MESSAGES = "info_messages";
	public static final String REDIRECT_LANDING_PAGE_NAME = "/viewlandingpage.html";
	public static final String REDIRECT_EDIT_PAGE_NAME = "/editmapping/editMapping.html";
	public static final String REDIRECT_LANDING_EWPD_BX_PAGE_NAME = "/viewlandingewpdbx.html";
	public static final String REDIRECT_LANDING_EWPD_BX_SPIDER_PAGE_NAME = "/viewlandingpagespider.html";//added for Spider 
	public static final String REDIRECT_ADVANCE_SEARCH_PAGE = "/advancesearchebx/viewHistorySearch.html";
	public static final String REDIRECT_ADVANCE_SEARCH_PAGE_WPD = "/advancesearch/viewHistorySearch.html";
	public static final String REDIRECT_ADVANCE_SEARCH_PAGE_WPD_ADV = "/advancesearch/viewSearchAfterPrint.html";
	/**
	 * Comment for <code>REDIRECT_ADVANCE_SEARCH_PAGE_WPD</code>
	 */
	public static final String REDIRECT_SIMULATION_PAGE = "/simulation/viewSimulationRequest.html";
	public static final String REDIRECT_EBX_SPIDER_LANDING_PAGE_NAME = "/viewlandingpagespider.html";
	
	public static final String INVALID_VARIABLE_LEGACY = "create.invalid.variable.legacy";
	public static final String INVALID_VARIABLE_FORMAT = "create.invalid.variable.format";
	public static final String INVALID_VARIABLE_NOT_APPLICABLE = "create.invalid.variable.notapplicable";
	public static final String INVALID_VARIABLE_MAPPING_EXIST = "create.invalid.variable.mapping.exist";	
	public static final String COPY_INVALID_VARIABLE_LEGACY = "copy.invalid.variable.legacy";
	public static final String COPY_INVALID_VARIABLE_FORMAT = "copy.invalid.variable.format";
	public static final String COPY_INVALID_VARIABLE_NOT_APPLICABLE = "copy.invalid.variable.notapplicable";
	public static final String COPY_INVALID_VARIABLE_MAPPING_EXIST = "copy.invalid.variable.mapping.exist";
	public static final String VARIABLE_MANDATORY = "create.variable.mandate";
	public static final String MAPPING_EXISTS = "create.mapping.exists";
	public static final String MAPPING_SAVE_SUCCESS= "mapping.save.success";
	public static final String MAPPING_DELETE_SUCCESS= "mapping.delete.success";
	public static final String MAPPING_RULEID_NOT_APPLICABLE= "mapping.ruleId.notApplicable";
	public static final String MAPPING_SEND_TO_TEST_SUCCESS= "mapping.sendToTest.success";
	public static final String MAPPING_APPROVE_SUCCESS= "mapping.approve.success";
	public static final String MAPPING_DISCARD_CHANGES_SUCCESS= "mapping.discardChanges.success";	
	public static final String INVALID_RULE_ID = "create.invalid.rule";
	public static final String INVALID_SPS_ID = "create.invalid.spsid";
	public static final String MAPPING_SPSID_NOT_APPLICABLE= "mapping.spsId.notApplicable";
	public static final String MAPPING_RULEID_EXISTS = "create.mapping.ruleid.exists";
	public static final String MAPPING_SPSID_EXISTS = "create.mapping.spsid.exists";
	public static final String MAPPING_CUSTOM_MSG_EXISTS = "create.mapping.custommsg.exists";
	public static final String MAPPING_USER_CMNTS_FAILURE = "mapping.usercomments.failure";
	public static final String MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS = "mapping.sendToTestFromViewOrLocate.success";
	public static final String MAPPING_APPROVE_VIEW_LOCATE_SUCCESS = "mapping.approveFromViewOrLocate.success";
	public static final String MAPPING_APPROVE_FAILURE = "mapping.approve.failure";
	public static final String MAPPING_SEND_TO_TEST_FAILURE ="mapping.sendToTest.failure";
	
    public static final String CHANGE_COMMENTS = "changeComments";
    public static final String SENSITIVE_BNFT_INDICATOR_TRUE = "true";
    public static final String SENSITIVE_BNFT_INDICATOR_FALSE = "false";
    public static final String MESSAGE_REQUIRED_INDICATOR_TRUE = "true";
    public static final String MESSAGE_REQUIRED_INDICATOR_FALSE = "false";
    public static final String TRANSACTION_TOKEN_KEY = "TRANSACTION_TOKEN_KEY";
    public static final String SENSITIVE_BNFT_INDICATOR_Y = "Y";
    public static final String SENSITIVE_BNFT_INDICATOR_N = "N";
    public static final String MESSAGE_REQUIRED_INDICATOR_Y = "Y";
    public static final String MESSAGE_REQUIRED_INDICATOR_N = "N";
    public static final String MAPPING = "mapping";
    public static final String JSON_VIEW = "jsonView";
    public static final String USER_COMNTS = "userComments";
    public static final String AUDIT_TRAIL = "auditTrail";
    public static final String SPS_ID = "spsId";
    public static final String RULE_ID = "ruleId";
    public static final String EDIT_RULE_MAPPING = "editrulemapping";
    public static final String EDIT_CUSTOM_MESSAGE_MAPPING = "editcustommsgmapping";
    public static final String CREATE_CUSTOM_MESSAGE_MAPPING = "createcustommsgmapping";
    public static final String EB03_VAL = "eb03Val";
    public static final String NOTE_TYPE_CODE = "noteType";
    public static final String NOTE_TYPE_CODE_DESC = "NoteTypeDesc";
    public static final String ROWS = "rows";
    public static final String EDIT_SPS_MAPPING = "editspsmapping";

	//Constants Used for displaying the view rule pop up
    
    public static final String EXCLSN_IND = "Exclusion&nbsp;Indicator";
    public static final String CLM_TYPE = "Claim&nbsp;Type";
    public static final String PLC_OF_SRVC = "Place&nbsp;of&nbsp;Service&nbsp;Code";
    public static final String PAT_LOW_AGE = "Patient&nbsp;Low&nbsp;Age";
    public static final String PAT_HIGH_AGE = "Patient&nbsp;High&nbsp;Age";
    public static final String GNDR_CD = "Gender&nbsp;Code";
    public static final String PRVDR_ID = "Provider&nbsp;ID";
    public static final String PRVDR_SPCLTY_CD = "Provider&nbsp;Speciality&nbsp;Code";
    public static final String BNFT_ACCUM_NM = "Benefit&nbsp;Accumulator&nbsp;Name";
    public static final String BNFT_ACCUM_LMT_AMT = "Benefit&nbsp;Accumulator&nbsp;Limit&nbsp;Amount";
    public static final String BNFT_ACCUM_LMT_NBR = "Benefit&nbsp;Accumulator&nbsp;Limit&nbsp;Occurrence&nbsp;Number";
    public static final String NTFY_ONLY_IND = "Notify&nbsp;Only&nbsp;Indicator";
    public static final String CLNL_RVW_IND = "Clinical&nbsp;Review&nbsp;Indicator";
    public static final String DLR_LMT = "Dollar&nbsp;Limit";
    public static final String SERIVCE_CD = "Service&nbsp;Code";
    public static final String GRP_ST = "Group&nbsp;State";
    public static final String LEN_OF_STAY = "Length&nbsp;Of&nbsp;stay";
    public static final String ITS_SPCLTY_CD = "ITS&nbsp;specialty&nbsp;Code";
    public static final String SRVC_STRT_DT = "Service&nbsp;Start&nbsp;Date";
    public static final String SRVC_END_DT = "Service&nbsp;End&nbsp;Date";
    public static final String MBR_RELSHP_CDE = "Member&nbsp;Relashionship&nbsp;Code";
    public static final String PRCDR_MODFR_CDE = "Procedure&nbsp;Modifier&nbsp;Code";
    public static final String EDIT_CDE_1 = "Edit&nbsp;Code1";
    public static final String EDIT_CDE_2 = "Edit&nbsp;Code2";
    public static final String PRVDR_ST_CD = "Provider&nbsp;State&nbsp;Code";
    public static final String BILL_TYP_CD = "Bill&nbsp;Type&nbsp;Code";
    public static final String TT_CD = "Treatment&nbsp;Type&nbsp;Code";
    public static final String ATCHMT_IND = "Attachment&nbsp;Indicator";
    public static final String PAT_MBR_CD = "Patient&nbsp;Member&nbsp;Code";
    public static final String HOSP_FCIL_CD = "Hospital&nbsp;Facility&nbsp;Code";
    public static final String SRVC_CLS_HIGH = "Service&nbsp;Class&nbsp;High";
    public static final String SRVC_CLS_LOW = "Service&nbsp;Class&nbsp;Low";
    public static final String LMT_CLS_HIGH = "Limit&nbsp;Class&nbsp;High";
    public static final String LMT_CLS_LOW = "Limit&nbsp;Class&nbsp;Low";
    public static final String DAYS_FROM_INJRY = "Days&nbsp;From&nbsp;Injury";
    public static final String DAYS_FROM_ILNS = "Days&nbsp;From&nbsp;Illnes";
    public static final String HMO_CLS_CD = "Class&nbsp;Code";
    public static final String TOT_CHRG_SIGN = "Total&nbsp;Charge&nbsp;Sign";
    public static final String TOT_CHARGE_AMOUNT = "Total&nbsp;Charged&nbsp;Amount";
    //public static final String WPD_DEL_FLAG = wpd&nbsp;del&nbsp;flag; =======
   
    public static final String CLM_SRVC_CD = "Claim&nbsp;Service&nbsp;Code";
    public static final String CLM_SRVC_CLS_HIGH = "Claim&nbsp;Service&nbsp;Class&nbsp;High";
    public static final String CLM_SRVC_CLS_LOW = "Claim&nbsp;Service&nbsp;Class&nbsp;Low";
    public static final String CLM_LMT_CLS_HIGH = "Claim&nbsp;Limit&nbsp;Class&nbsp;High";
    public static final String CLM_LMT_CLS_LOW = "Claim&nbsp;Limit&nbsp;Class&nbsp;Low";
    public static final String CLM_PROC_MDFR_CD = "Clm&nbsp;Procr&nbsp;Modifr&nbsp;Ind";
   
    public static final String CLM_PLACE_OF_SRVC = "Clm&nbsp;Place&nbsp;Of&nbsp;Service";
    
    
    public static final String CLM_HCPT_LOW_VAL = "Clm&nbsp;Hcpt&nbsp;Low&nbsp;val";
    public static final String CLM_HCPT_HIGH_VAL = "Clm&nbsp;Hcpt&nbsp;High&nbsp;Val";
    public static final String CLM_ICD_VRSN_IND = "Clm&nbsp;ICD&nbsp;Vrsn&nbsp;Ind";  //ICD10 Enhancement
    public static final String CLM_DIAG_LOW_VAL = "Clm&nbsp;Diag&nbsp;Low&nbsp;Val";
    public static final String CLM_DIAG_HIGH_VAL = "Clm&nbsp;Diag&nbsp;High&nbsp;Val";
    public static final String CLM_REV_LOW_VAL = "Clm&nbsp;Rev&nbsp;Low&nbsp;Val";
    public static final String CLM_REV_HIGH_VAL = "Clm&nbsp;Rev&nbsp;High&nbsp;Val";
    public static final String CLM_ICDP_LOW_VAL = "Clm&nbsp;ICDP&nbsp;Low&nbsp;val";
    public static final String CLM_ICDP_HIGH_VAL = "Clm&nbsp;ICDP&nbsp;High&nbsp;val";
    public static final String CPAY_IND = "Copay&nbsp;Indicator";
    public static final String HNDRD_PCT_IND = "100%&nbsp;Indicator";
    public static final String MEDCR_ASGN_IND = "Medicare&nbsp;Assignment&nbsp;Indicator";
    public static final String PROC_MDFR_CD2 = "Proc&nbsp;Mdfr&nbsp;Cd2";
    public static final String SPRT_HCPS_IND = "Support&nbsp;HCPS&nbsp;indicator";
	public static final String DIAG_IND = "Diag&nbsp;Ind";
	public static final String PCP_IND = "Pcp&nbsp;Ind";
	
    
    public static final String CLM_SPRT_HCPS_IND = "Claim&nbsp;Support&nbsp;HCPS&nbsp;indicator";
    
    public static final String PVDR_LIC_ID = "Pvdr&nbsp;Lic&nbsp;Id";
    public static final String PVDR_MDCR_ID = "Pvdr&nbsp;Mdcr&nbsp;Id";
    public static final String BLNG_PVDR_NR = "Blng&nbsp;Pvdr&nbsp;Nr";
    public static final String RNDR_PVDR_NR = "Rndr&nbsp;Pvdr&nbsp;Nr";
    public static final String BLNG_NPI = "Blng&nbsp;Npi";
    public static final String RNDR_NPI = "Rndr&nbsp;Npi";
    public static final String ELGBL_EXPNS_SIGN_CD = "Elgbl&nbsp;Expns&nbsp;Sign&nbsp;Cd";
    public static final String ELGBL_EXPNS_AMT = "Elgbl&nbsp;Expns&nbsp;Amt";
    public static final String AGE_TYPE_CD = "Age&nbsp;Type&nbsp;Cd";
    public static final String RULE_VRSN_NBR = "Rule&nbsp;Vrsn&nbsp;Nbr";
    public static final String DRG_CD = "Drg&nbsp;Cd";
    public static final String PROV_SPEC_CD_IND = "Pvdr&nbsp;Spec&nbsp;Cd&nbsp;Ind";
    
//    "Diagnosis&nbsp;CD&nbsp;Ind";
//    "Claim&nbsp;Diagnosis&nbsp;CD&nbsp;Ind";
//    "DUM";

    public static final String LOCK_ERROR_MSG = "Mapping is already locked";

	
	public static final int TOTAL_NO_OF_RECORDS = -1;
	public static final int TOTAL_NO_OF_AUDIT_TRAIL = 21;
	public static final int LANDING_PAGE_TOTAL_COUNT = 26;
	public static final int USER_COMMENTS_MAX_LENGTH = 250;
	public static final int AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY = 20;
//ICD10 Constants
	public static final String ICD_CODE = "ICD";
//ICD10 End
	
	// MTM April2012 EB01 - DataType- Start
	public static final String DATA_TYPE_VAL = "dataTypeVal";
	public static final String DATA_TYPE_DESC = "dataTypeDesc";
	// MTM April2012 EB01 - DataType- End
	

	
	
	//Constants Used for displaying the view rule Code sequence ppop up
	public static final String CODE_SEQUENCES = "Code&nbsp;Sequences&nbsp;";
	public static final String VIEW_RULE_ID = "rule&nbsp;id";
    public static final String RULE_SEQUENCE_NUMBER = "Rule&nbsp;Sequence&nbsp;Number";
    public static final String RULE_TYPE_CODE = "Rule&nbsp;Type&nbsp;Code";
    public static final String RULE_VERSION = "Rule&nbsp;Version";
	public static final String CODE_SEQUENCE_NUMBER = "Code&nbsp;Sequence&nbsp;Number";
	public static final String LN_HCPT_VAL = "HCPT:&nbsp;";
	public static final String LN_DIAG_VAL = "DIAG:&nbsp;";
	public static final String LN_REV_VAL =  "Rev:&nbsp";
    public static final String LN_ICDP_VAL = "ICDP:&nbsp;";
    public static final String LN_SRVC_CLS = "Service&nbsp;Class:&nbsp;";
    public static final String LN_LMT_CLS= "Limit&nbsp;Class:&nbsp;";
    public static final String LN_ICDP_VRSN_IND = "Line&nbsp;ICDP&nbsp;Version&nbsp;Ind:&nbsp;";
    public static final String LN_DIAG_VRSN_IND = "Line&nbsp;DIAG&nbsp;Version&nbsp;Ind:&nbsp;";
    
  //Constants Used for displaying the view rule Claim level sequence pop up
    public static final String CLAIM_LEVEL_SEQUENCE = "Claim&nbsp;level&nbsp;Sequences&nbsp;";
    public static final String CLAIM_SEQUENCE_NUMBER = "Claim&nbsp;Sequence&nbsp;Number";
    public static final String CLAIM_SERVICE_CODE = "Clm&nbsp;Service&nbsp;Code";
    public static final String CLM_PROC_MDFR_CD1 = "Clm&nbsp;Procr&nbsp;Modifr&nbsp;Code1";
    public static final String CLM_PROC_MDFR_CD2 = "Clm&nbsp;Procr&nbsp;Modifr&nbsp;Code2";
    public static final String CLM_TT_CD = "Clm&nbsp;TT&nbsp;Code";
    public static final String CLM_POS_CD = "Clm&nbsp;POS&nbsp;Code";
    public static final String CLM_HMO_CLS_CD = "Clm&nbsp;Hmo&nbsp;Cls&nbsp;Code";
    public static final String CLM_SAME_DAY_SVC_IND = "Clm&nbsp;Same&nbsp;Day&nbsp;Service&nbsp;Ind";
    public static final String CLM_SPRT_PROC_CD_IND = "Claim&nbsp;Support&nbsp;PROC_CD&nbsp;indicator";
    public static final String CLM_DIAG_IND = "clm&nbsp;diag&nbsp;ind";
    
    //Constants Used for displaying the view rule Claim Code sequence pop up
    public static final String CLAIM_CODE_COUNT = "Claim&nbsp;Code&nbsp;count&nbsp;";
    public static final String CLAIM_CODE_SEQUENCE = "Claim&nbsp;Code&nbsp;Sequences&nbsp;";
    public static final String CLAIM_CODE_SEQUENCE_NUMBER = "Claim&nbsp;Code&nbsp;Sequence&nbsp;Number:&nbsp;";
    public static final String CLAIM_HCPT_VAL = "Clm&nbsp;Hcpt:&nbsp;";
	public static final String CLAIM_DIAG_VAL = "Clm&nbsp;Diag:&nbsp;";
	public static final String CLAIM_REV_VAL = "Clm&nbsp;Rev:&nbsp;";
    public static final String CLAIM_ICDP_VAL = "Clm&nbsp;ICDP:&nbsp;";
    public static final String CLAIM_SRVC_CLS = "Clm&nbsp;Service&nbsp;Class:&nbsp;";
    public static final String CLAIM_LMT_CLS= "Clm&nbsp;Limit&nbsp;Class:&nbsp;";
    public static final String CLAIM_ICDP_VRSN_IND = "Clm&nbsp;ICDP&nbsp;Version&nbsp;Ind:&nbsp;";
    public static final String CLAIM_DIAG_VRSN_IND = "Clm&nbsp;DIAG&nbsp;Version&nbsp;Ind:&nbsp;";
    public static final String UNMAPPED_RULE = "unmappedRule";
    public static final String NO_MAPPING_FOUND = "NO_MAPPING_FOUND";
    
    
    //Constants Added for SSCR 19537
    public static final String INDIVIDUAL_MAPPING_PRESENT = "INDIVIDUAL_MAPPING_PRESENT";
    
    public static final String PERSIST_TYPE_CREATE = "create";
    public static final String PERSIST_TYPE_EDIT = "edit";
}
