/*
 * <SecurityConstants.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author UST-GLOBAL This class constants for security handling
 */
public class SecurityConstants {

	public static final String BX_SECUITY_EDIT_PAGE="BXEDITMAPPINGPAGE";
	public static final String BX_SECUITY_LOCATE_PAGE="BXLOCATEMAPPINGPAGE";
	public static final String BX_SECUITY_VIEWLANDING_PAGE="BXVIEWLANDINGPAGE";
	public static final String BX_SECUITY_VIEWMAPPING_PAGE="BXVIEWMAPPINGPAGE";
	public static final String BX_SECUITY_CREATEMAPPING_PAGE="BXCREATEMAPPINGPAGE";
	public static final String BX_SECURITY_VARIABLEMAPPING="BXVARIABLEMAPPING";
	public static final String EBX_SECURITY_MAPPING="EBXMAPPING";
	
	public static final String BX_APPROVE="BXAPPROVE";
	public static final String BX_NOT_APPLICABLE="BXNOTAPPLICABLE";
	public static final String BX_UNLOCK="BXUNLOCK";
	
	public static final String EBX_APPROVE="EBXAPPROVE";
	public static final String EBX_NOT_APPLICABLE="EBXNOTAPPLICABLE";
	public static final String EBX_UNLOCK="EBXUNLOCK";
	
	/**
	 * Comment for <code>EBX_EXCLUSIONMANAGE</code>
	 * TODO : Change ti the new task.
	 */
	public static final String EBX_EXCLUSIONMANAGE = "EXCLUSIONMANAGE";
	
	/**
	 * Comment for <code>EBX_SECURITY_MAPPING</code>
	 * TODO
	 */
	public static final String EBX_REFERENCEDATA_MODULE = "EBXREFERENCEDATA";
	public static final String EBX_HPN_REFERENCEDATA_MODULE = "EBXHPNREFERENCEDATA";
	
	public static String SM_USER = "SM_USER";
	public static String SM_ROLES = "SM_ROLES";
	public static String USER = "USER";
	public static String USER_NAME = "userName";
	public static String DEFAULT_ROLE = "WSBADMINISTRATOR";//"DEFAULT" "WSBADMINISTRATOR" ;
	public static String RETRIEVE_USER = "retrieveUser";
    public static final String SITEMINDER_ROLE_SEPARATOR ="^";
	
    /**
	 * task to enable sensitive benefit indicator to Admin and disable for other user
	 */
    public static final String EBX_SENSITIVE_BNFT_INDCTR="EBXSENSITIVEBNFTINDCTR";
    /**
	 * task to enable accum not required indicator to Admin and disable for other user
	 */
    public static final String BX_ACCUM_NOT_RQRD_INDCTR="BXACCUMNOTRQRDINDCTR";
	
	// Reference Data Values - Start
	public static final String EBX_DATA_VAL_MANAGE = "DATAVALUEMANAGE";    
	// Reference Data Values - End		
	
	public static final String BX_VARIABLEAUDITLOCK ="BXVARIABLEAUDITLOCK";
	public static final String BX_AUDITLOCK ="AUDITLOCK";
	public static final String BX_AUDITUNLOCK ="AUDITUNLOCK";
	/**
	 * Identifier to store the new task for edit Audit UnLocked variable.
	 */
	public static final String BX_AUDITLOCK_EDIT = "AUDITLOCKEDIT";
	//task for data type to EB01 Edit privillage -- SSCR 14181 April 2012 Release
	public static final String EBX_DATA_TYPE_TO_EB01 = "DATATYPETOEB01ASSOCIATION";
	//task for header rule to EB03 Edit privillage -- SSCR 14181 April 2012 Release
	public static final String EBX_HEADER_RULE_TO_EB03 = "HEADERRULETOEB03ASSOCIATION";
	// Task to edit and delete standard message.
	public static final String STANDARD_MESSAGE = "STANDARDMESSAGE";
	
	public static final String EBX_ADMINISTRATION_MODULE = "EBXADMINISTRATION";
	public static final String EBX_MAINTAINCONFIGURATION = "MAINTAINCONFIGURATION";
	//Task to edit and delete service type mappings -- BXNI November Release
	public static final String SRVC_TYPE_MAPPING = "SERVICETYPEMAPPING";
}
