/*
 * <UserUIPermissions.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author UST-GLOBAL This class for security handling.
 */
public class UserUIPermissions {

	private static final String LANDING_PAGE = "viewlandingpage";	

	private static final String VIEW_PAGE = "mappingview";

	private static final String LOCATE_PAGE = "locateresults";

	private static final String EDIT_PAGE = "editmapping";
	
	private static final String CREATE_PAGE = "createmapping";
	/** Constants for EBX MOdule */
	private static final String EBX_LANDING_PAGE = "viewlandingewpdbx";
  
	private static final String RULE_VIEW_PAGE = "mappingviewrule";

	private static final String RULE_LOCATE_PAGE = "locateruleresults";

	private static final String RULE_EDIT_PAGE = "editrulemapping";
	
	private static final String RULE_CREATE_PAGE = "createrulemapping";
	
	private static final String SPS_VIEW_PAGE = "mappingviewsps";

	private static final String SPS_LOCATE_PAGE = "locatespsresults";

	private static final String SPS_EDIT_PAGE = "editspsmapping";
	
	private static final String SPS_CREATE_PAGE = "createspsmapping";
	
	private static final String CSTM_VIEW_PAGE = "mappingviewcustommessage";

	private static final String CSTM_LOCATE_PAGE = "locatecustommsgresults";

	private static final String CSTM_EDIT_PAGE = "editcustommsgmapping";
	
	private static final String CSTM_CREATE_PAGE = "createcustommsgmapping";
	
	private static final String ADVANCE_SEARCH_PAGE_EWPD = "advancesearchebx";
	
	private static final String ADVANCE_SEARCH_RESULT_PAGE_EWPD = "advancesearchresult";
	
	private static final String ADVANCE_SEARCH_PAGE_WPD = "advancesearch";
	
	private static final String ADVANCE_SEARCH_RESULT_PAGE_WPD = "advancesearchresultbx";
	
	/**
	 * Comment for <code>EBX_EXCLUSIONS_PAGE</code>
	 * EBX Exclusion manage page.
	 */
	private static final String EBX_EXCLUSIONS_PAGE_MAIN = "exclusionManage";
	
	/**
	 * Comment for <code>EBX_EXCLUSIONS_PAGE_MAIN</code>
	 */
	private static final String EBX_EXCLUSIONS_PAGE_SUB = "exclusionSearchResult";

	
	private boolean authorizedToapprove;

	private boolean authorizedToMarkAsNotApplicable;
	
	private boolean authorizedToUnlock;
	
	// Reference Data Values - Start
	
	private static final String EBX_REF_DATA_VAL_PAGE = "referenceDataValueManage";
	private static final String EBX_HPN_REF_DATA_VAL_PAGE = "referenceHPNDataValueManage";
	
	private static final String EBX_DATA_VAL_RESULT_PAGE = "dataValueSearchResult";

	private boolean authroizedToManageDataValues;
	
	private static final String MANAGE_HEADERRULE_TO_EB03_PAGE = "manageHeaderRuleToEB03";
	
	private static final String HEADER_RULE_TO_EB03_SEARCH_RESULT = "headerRuleToEB03SearchResult";
	
	public static final String MANAGE_DATATYPE_TO_EB01_PAGE = "manageDataTypeToEB01";
	
	public static final String EB01_DATA_TYPE_RESULT_PAGE = "dataTypeToEB01SearchResult";
	
	public static final String REFERENCE_STD_MESSAGE_PAGE = "standardmessage";
	
	public static final String REFERENCE_STD_MESSAGE_RESULT_PAGE = "standardmessagesearchresult";
	
	public static final String SYSTEM_CONFIGURATION_PAGE = "viewSystemConfigurations";
	
	public static final String REFERENCE_SRVC_TYP_CODE_PAGE = "manageServiceTypeCodeToEB11";
	
	public static final String REFERENCE_SRVC_TYP_CODE_RESULT_PAGE = "serviceTypeCodetoEB11SearchResult";

	/**
	 * Checks authorization for Audit Lock a variable
	 */
	private boolean authorizedToAuditLock;
	
	/**
	 * Checks authorization for Audit UnLock a variable
	 */
	private boolean authorizedToAuditUnlock;
	/**
	 * Checks authorization for Edit a Audit UnLocked variable.
	 */
	private boolean authorizedEditLockVar; 
	
	/**
	 * @return the authorizedToAuditLock
	 */
	public boolean isAuthorizedToAuditLock() {
		return authorizedToAuditLock;
	}

	/**
	 * @param authorizedToAuditLock the authorizedToAuditLock to set
	 */
	public void setAuthorizedToAuditLock(boolean authorizedToAuditLock) {
		this.authorizedToAuditLock = authorizedToAuditLock;
	}

	/**
	 * @return the authorizedToAuditUnlock
	 */
	public boolean isAuthorizedToAuditUnlock() {
		return authorizedToAuditUnlock;
	}

	/**
	 * @param authorizedToAuditUnlock the authorizedToAuditUnlock to set
	 */
	public void setAuthorizedToAuditUnlock(boolean authorizedToAuditUnlock) {
		this.authorizedToAuditUnlock = authorizedToAuditUnlock;
	}

	public boolean isAuthroizedToManageDataValues() {
		return authroizedToManageDataValues;
	}

	public void setAuthroizedToManageDataValues(boolean authroizedToManageDataValues) {
		this.authroizedToManageDataValues = authroizedToManageDataValues;
	}

	// Reference Data Values - End
	
	/**
	 * Comment for <code>authroizedToEditExclusion</code>
	 */
	private boolean authroizedToManageExclusion;
	/**
	 * Checks authorization whether enable/disable sensitive benefit indicator in the rule edit page
	 */
	private boolean authorizedToEditSensitiveBnftIndctr;
	/**
	 * Checks authorization whether enable/disable accum not required indicator in the variable edit page
	 */
	private boolean authorizedToEditAccumNotRqrdIndctr;
	
	/**
	 * Checks authorization whether the user can edit or delete standard message in the reference data module.
	 */
	private boolean authorizedToEditStandardMessage;
	/**
	 * Checks authorization whether the user can create,edit or delete Service type-EB11 association in the reference data module.
	 */
	private boolean authorizedToEditServiceTypeMapping;
	

	//Authorization for Data Type to EB01 Association --SSCR14181 April 2012 Release
	private boolean authorizedToEditDataTypeToEB01Association;
	//Authorization for Header Rule to EB01 Association --SSCR14181 April 2012 Release
	private boolean authorizedToEditHeaderRuleToEB03Association;
	
	/**
	 * Checks authorization System Configuration functionality
	 */
	private boolean authorizedToMaintainSystemConfiguration; 
	

	public boolean isAuthorizedToEditDataTypeToEB01Association() {
		return authorizedToEditDataTypeToEB01Association;
	}

	public void setAuthorizedToEditDataTypeToEB01Association(
			boolean authorizedToEditDataTypeToEB01Association) {
		this.authorizedToEditDataTypeToEB01Association = authorizedToEditDataTypeToEB01Association;
	}

	public boolean isAuthorizedToEditHeaderRuleToEB03Association() {
		return authorizedToEditHeaderRuleToEB03Association;
	}

	public void setAuthorizedToEditHeaderRuleToEB03Association(
			boolean authorizedToEditHeaderRuleToEB03Association) {
		this.authorizedToEditHeaderRuleToEB03Association = authorizedToEditHeaderRuleToEB03Association;
	}
	
	public boolean isAuthorizedToMaintainSystemConfiguration() {
		return authorizedToMaintainSystemConfiguration;
	}

	public void setAuthorizedToMaintainSystemConfiguration(
			boolean authorizedToMaintainSystemConfiguration) {
		this.authorizedToMaintainSystemConfiguration = authorizedToMaintainSystemConfiguration;
	}
	
	public UserUIPermissions(String viewName, LoginUser user) {
		if(viewName != null && viewName.trim().length() > 0) {
			setPermissionForView(viewName, user);
		}

	}

	private void setPermissionForView(String viewName, LoginUser user) {
		if (viewName.equals(LANDING_PAGE) || viewName.equals(VIEW_PAGE)
				|| viewName.equals(LOCATE_PAGE) || viewName.equals(EDIT_PAGE)
				|| viewName.equals(CREATE_PAGE) || viewName.equals(ADVANCE_SEARCH_PAGE_EWPD) 
				|| viewName.equals(ADVANCE_SEARCH_PAGE_WPD)) {
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_APPROVE)) {
				authorizedToapprove = true;
			}
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}
			// July release - Admin user can only enable Accum not required indicator - WPD landing page
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_ACCUM_NOT_RQRD_INDCTR)) {
				authorizedToEditAccumNotRqrdIndctr = true;
			}
			//updated as part of october release lock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK)) {
				authorizedToAuditLock = true;
			}
			//updated as part of october release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITUNLOCK)) {
				authorizedToAuditUnlock = true;
			}
			//updated as part of November release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK_EDIT)) {
				authorizedEditLockVar = true;
			}
		}
		// For WPD advance search .
		if(viewName.equals(ADVANCE_SEARCH_PAGE_WPD) || viewName.equals(ADVANCE_SEARCH_RESULT_PAGE_WPD)){
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_APPROVE)) {
				authorizedToapprove = true;
			}
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}		
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_UNLOCK)) {
				authorizedToUnlock = true;
			}
			//updated as part of october release lock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK)) {
				authorizedToAuditLock = true;
			}
			//updated as part of october release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITUNLOCK)) {
				authorizedToAuditUnlock = true;
			}
			//updated as part of November release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK_EDIT)) {
				authorizedEditLockVar = true;
			}
		}
		// May release - Admin user can only unlock - WPD landing page
		if (viewName.equals(LANDING_PAGE) || viewName.equals(LOCATE_PAGE) ) {
			
			if (user.isAuthorized(
					SecurityConstants.BX_SECURITY_VARIABLEMAPPING,
					SecurityConstants.BX_UNLOCK)) {
				authorizedToUnlock = true;
			}
			//updated as part of october release lock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK)) {
				authorizedToAuditLock = true;
			}
			//updated as part of october release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITUNLOCK)) {
				authorizedToAuditUnlock = true;
			}
			//updated as part of November release Unlock variable
			if (user.isAuthorized(
					SecurityConstants.BX_VARIABLEAUDITLOCK,
					SecurityConstants.BX_AUDITLOCK_EDIT)) {
				authorizedEditLockVar = true;
			}
		}
		// May release - Admin user can only unlock - eWPD landing page
		if (viewName.equals(EBX_LANDING_PAGE) || viewName.equals(RULE_LOCATE_PAGE)
				|| viewName.equals(SPS_LOCATE_PAGE) || viewName.equals(CSTM_LOCATE_PAGE)|| viewName.equals(ADVANCE_SEARCH_PAGE_EWPD)
				|| viewName.equals(ADVANCE_SEARCH_RESULT_PAGE_EWPD)
				|| viewName.equals(ADVANCE_SEARCH_PAGE_WPD) ||viewName.equals(ADVANCE_SEARCH_RESULT_PAGE_WPD) ) {
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_UNLOCK)) {
				authorizedToUnlock = true;
			}
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}			
		}
		//For eWPD advance search .
		if(viewName.equals(ADVANCE_SEARCH_PAGE_EWPD) || viewName.equals(ADVANCE_SEARCH_RESULT_PAGE_EWPD)){
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_UNLOCK)) {
				authorizedToUnlock = true;
			}
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}	
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_APPROVE)) {
				authorizedToapprove = true;
			}
			
			
		}
		if (viewName.equals(EBX_LANDING_PAGE) || viewName.equals(SPS_VIEW_PAGE)
				|| viewName.equals(SPS_LOCATE_PAGE) || viewName.equals(SPS_EDIT_PAGE)
				|| viewName.equals(SPS_CREATE_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_APPROVE)) {
				authorizedToapprove = true;
			}
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}			
		}
		if (viewName.equals(RULE_VIEW_PAGE)
				|| viewName.equals(RULE_LOCATE_PAGE) || viewName.equals(RULE_EDIT_PAGE)
				|| viewName.equals(RULE_CREATE_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_APPROVE)) {
				authorizedToapprove = true;
			}
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}
			// July release - Admin user can only enable Sensitive Benefit indicator - eWPD landing page
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_SENSITIVE_BNFT_INDCTR)) {
				authorizedToEditSensitiveBnftIndctr = true;
			}
		}
		if (viewName.equals(CSTM_VIEW_PAGE)
				|| viewName.equals(CSTM_LOCATE_PAGE) || viewName.equals(CSTM_EDIT_PAGE)
				|| viewName.equals(CSTM_CREATE_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_APPROVE)) {
				authorizedToapprove = true;
			}
			if (user.isAuthorized(
					SecurityConstants.EBX_SECURITY_MAPPING,
					SecurityConstants.EBX_NOT_APPLICABLE)) {
				authorizedToMarkAsNotApplicable = true;
			}			
		}
		//Added for Exclusions page
		if (viewName.equals(EBX_EXCLUSIONS_PAGE_MAIN) || viewName.equals(EBX_EXCLUSIONS_PAGE_SUB)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.EBX_EXCLUSIONMANAGE)) {
				authroizedToManageExclusion = true;
			}
		}
		// Reference Data Values - Start
		if (viewName.equals(EBX_REF_DATA_VAL_PAGE) || viewName.equals(EBX_DATA_VAL_RESULT_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.EBX_DATA_VAL_MANAGE)) {
					authroizedToManageDataValues = true;
			}
		}		
		//EBX HPN Reference Data screen
		if (viewName.equals(EBX_HPN_REF_DATA_VAL_PAGE) || viewName.equals(EBX_DATA_VAL_RESULT_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.EBX_DATA_VAL_MANAGE)) {
					authroizedToManageDataValues = true;
			}
		}
		// BXNI June Release- Standard Message.
		if (viewName.equals(REFERENCE_STD_MESSAGE_PAGE) || viewName.equals(REFERENCE_STD_MESSAGE_RESULT_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.STANDARD_MESSAGE)) {
				authorizedToEditStandardMessage = true;
			}
		}	
		// Reference Data Values - End
		
		//Authorisation for Data Type to EB01 Association --SSCR14181 April 2012 Release
		if (viewName.equals(MANAGE_DATATYPE_TO_EB01_PAGE) || viewName.equals(EB01_DATA_TYPE_RESULT_PAGE)){
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.EBX_DATA_TYPE_TO_EB01)) {
				authorizedToEditDataTypeToEB01Association = true;
			}
		}	
		//Authorisation for Header Rule to EB03 Association --SSCR14181 April 2012 Release
		if (viewName.equals(MANAGE_HEADERRULE_TO_EB03_PAGE) || viewName.equals(HEADER_RULE_TO_EB03_SEARCH_RESULT) ) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.EBX_HEADER_RULE_TO_EB03)) {
				authorizedToEditHeaderRuleToEB03Association = true;
			}
		}
		//Authorization for system configurations page -- BXNI May 2012 Release
		if (!viewName.equals(SYSTEM_CONFIGURATION_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_ADMINISTRATION_MODULE,
					SecurityConstants.EBX_MAINTAINCONFIGURATION)) {
				authorizedToMaintainSystemConfiguration = true;
			}
		}
		
		//Authorization for Service Type Mappings --BXNI November 2012 Release
		if (viewName.equals(REFERENCE_SRVC_TYP_CODE_PAGE) || viewName.equals(REFERENCE_SRVC_TYP_CODE_RESULT_PAGE)) {
			if (user.isAuthorized(
					SecurityConstants.EBX_REFERENCEDATA_MODULE,
					SecurityConstants.SRVC_TYPE_MAPPING)) {
				authorizedToEditServiceTypeMapping = true;
			}
		}	
	}
	
	/**
	 * @return Returns the authorizedToapprove.
	 */
	public boolean isAuthorizedToapprove() {
		return authorizedToapprove;
	}
	/**
	 * @param authorizedToapprove The authorizedToapprove to set.
	 */
	public void setAuthorizedToapprove(boolean authorizedToapprove) {
		this.authorizedToapprove = authorizedToapprove;
	}
	/**
	 * @return Returns the authorizedToMarkAsNotApplicable.
	 */
	public boolean isAuthorizedToMarkAsNotApplicable() {
		return authorizedToMarkAsNotApplicable;
	}
	/**
	 * @param authorizedToMarkAsNotApplicable The authorizedToMarkAsNotApplicable to set.
	 */
	public void setAuthorizedToMarkAsNotApplicable(
			boolean authorizedToMarkAsNotApplicable) {
		this.authorizedToMarkAsNotApplicable = authorizedToMarkAsNotApplicable;
	}

	/**
	 * @return Returns the authorizedToUnlock
	 */
	public boolean isAuthorizedToUnlock() {
		return authorizedToUnlock;
	}

	/**
	 * @param authorizedToUnlock The authorizedToUnlock to set.
	 */
	public void setAuthorizedToUnlock(boolean authorizedToUnlock) {
		this.authorizedToUnlock = authorizedToUnlock;
	}

	public boolean isAuthroizedToManageExclusion() {
		return authroizedToManageExclusion;
	}

	public void setAuthroizedToManageExclusion(boolean authroizedToManageExclusion) {
		this.authroizedToManageExclusion = authroizedToManageExclusion;
	}

	public void setAuthorizedToEditSensitiveBnftIndctr(
			boolean authorizedToEditSensitiveBnftIndctr) {
		this.authorizedToEditSensitiveBnftIndctr = authorizedToEditSensitiveBnftIndctr;
	}

	public boolean isAuthorizedToEditSensitiveBnftIndctr() {
		return authorizedToEditSensitiveBnftIndctr;
	}

	public void setAuthorizedToEditAccumNotRqrdIndctr(
			boolean authorizedToEditAccumNotRqrdIndctr) {
		this.authorizedToEditAccumNotRqrdIndctr = authorizedToEditAccumNotRqrdIndctr;
	}

	public boolean isAuthorizedToEditAccumNotRqrdIndctr() {
		return authorizedToEditAccumNotRqrdIndctr;
	}

	public boolean isAuthorizedEditLockVar() {
		return authorizedEditLockVar;
	}

	public void setAuthorizedEditLockVar(boolean authorizedEditLockVar) {
		this.authorizedEditLockVar = authorizedEditLockVar;
	}

	/**
	 * @return the authorizedToEditStandardMessage
	 */
	public boolean isAuthorizedToEditStandardMessage() {
		return authorizedToEditStandardMessage;
	}

	/**
	 * @param authorizedToEditStandardMessage the authorizedToEditStandardMessage to set
	 */
	public void setAuthorizedToEditStandardMessage(
			boolean authorizedToEditStandardMessage) {
		this.authorizedToEditStandardMessage = authorizedToEditStandardMessage;
	}

	public void setAuthorizedToEditServiceTypeMapping(
			boolean authorizedToEditServiceTypeMapping) {
		this.authorizedToEditServiceTypeMapping = authorizedToEditServiceTypeMapping;
	}

	public boolean isAuthorizedToEditServiceTypeMapping() {
		return authorizedToEditServiceTypeMapping;
	}
}