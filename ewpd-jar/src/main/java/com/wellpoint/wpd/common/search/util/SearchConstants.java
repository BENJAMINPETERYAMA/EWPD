/*
 * SearchConstants.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.util;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface SearchConstants {
    
    public  String PRODUCT = "PRODUCT";
    public  String CONTRACT = "CONTRACT";
    public  String PRODUCT_STRUCTURES = "PRODUCT_STRUCTURES";
    public  String BENEFIT_COMPONENTS = "BENEFIT_COMPONENTS";
    public  String BENEFIT = "BENEFIT";
    public  String BENEFIT_LINE = "BENEFIT_LINE";
    public  String BENEFIT_LEVEL = "BENEFIT_LEVEL";
    public  String NOTES = "NOTES";
    public 	String SEARCH_RESULTS="SEARCH_RESULTS"; 
    public  String BASIC_SEARCH_IDENTIFIER="IDENTIFIER"; 
    public  String BASIC_SEARCH_CRITERIA="BasicSearchCriteria";
    
	public static final String ASSOCIATION = "ASSOCIATION";
	public static final String OBJECT_DETAIL = "OBJECT_DETAIL";
	public static final String SET_PAGE_NUMBER = "SET_PAGE_NUMBER";
    

    public  String PAGE_NUMBER = "pageNumber";
    public  String OBJECT_TYPE = "objectTypeAndPageNumber";
    public  String ASSOCIATION_OBJECT_TYPE = "ASSOCIATION_OBJECT_TYPE";
    public  String ATTACHMENT_OBJECT_TYPE = "ATTACHMENT_OBJECT_TYPE";
    
    public int BASIC_SEARCH = 1;
    public int ADVANCED_SEARCH = 2;
	public String VIEW_ASSOCIATION = "VIEW_ASSOCIATION";
	public String VIEW_ASSOCIATION_TRUE = "VIEW_ASSOCIATION_TRUE";
	public String VIEW_ATTACHMENT = "VIEW_ATTACHMENT";
	public String VIEW_ATTACHMENT_TRUE = "VIEW_ATTACHMENT_TRUE";
	public String SEARCH_TREE_STATE ="SearchTreeState";
	public String FROM_SUMMARY_PAGE ="FromSummary";
	public final String ADVANCED_SEARCH_REL_AND = "AND";
	public final String ADVANCED_SEARCH_REL_OR = "OR";

	public static String CONTRACT_CONTRACT_ID="Contract Id"; 
	public static String CONTRACT_EXPIRY_DATE="Expiry Date";
	public static String CONTRACT_CONTRACT_TYPE="Contract Type";
	public static String CONTRACT_GROUP_SIZE="Group Size";
	public static String CONTRACT_PRODUCT_CODE="Product Code";
	public static String CONTRACT_STANDARD_PLAN_CODE="Standard Plan Code";
	public static String CONTRACT_BENEFIT_PLAN="Benefit Plan";
	public static String CONTRACT_PRODUCT_FAMILY="Product Family";
	public static String CONTRACT_CORPORATE_PLAN_CODE="Corporate Plan Code";
	public static String CONTRACT_FUNDING_ARRANGEMENT="Funding Arrangement";
	public static String CONTRACT_BRAND_NAME="Brand Name";
	public static String CONTRACT_HEAD_QUARTERS_STATE="Head Quarters State";
	public static String CONTRACT_VERSION="Version";
	public static String CONTRACT_CREATED_DATE="Created Date";
	public static String CONTRACT_CREATED_BY="Created By";
	public static String CONTRACT_LAST_UPDATED_DATE="Last Updated Date";
	public static String CONTRACT_LAST_UPDATED_BY="Last Updated By";
	public static String CONTRACT_EFFECTIVE_DATE="Effective Date";
	public static String CONTRACT_STATUS="Status";
	public static String CONTRACT_COB_A_I="COB Adjudication Indicator";
	public static String CONTRACT_MED_A_I="Medicare Adjudication Indicator";
	public static String CONTRACT_ITS_A_I="ITS Adjudication Indicator";
	public static String CONTRACT_CASE_NUMBER="Case Number";
	public static String CONTRACT_CASE_NAME="Case Name";
	public static String CONTRACT_CASE_EFFECTIVE_DATE="Case Effective Date";
	public static String CONTRACT_CASE_CANCEL_DATE="Case Cancel Date";
	public static String CONTRACT_CASE_HEAD_QUARTER_STATE="Case Head Quarter State";
	public static String CONTRACT_GROUP_NUMBER="Group Number";
	public static String CONTRACT_GROUP_NAME="Group Name";
	public static String CONTRACT_GROUP_EFFECTIVE_DATE="Group Effective Date";
	public static String CONTRACT_GROUP_CANCEL_DATE="Group Cancel Date";
	public static String CONTRACT_FUNDING_ARRANGEMENT_CODE="Funding arrangement code";
	public static String CONTRACT_FUNDING_ARRANGEMENT_VALUE="Funding arrangement value";
	public static String CONTRACT_MBU_CODE="MBU code";
	public static String CONTRACT_MBU_VALUE="MBU value";
	 
//	START new constants added for sept release
	public static String CONTRACT_RGLTRY_AGNCY = "Regulatory Agency";
	public static String CONTRACT_CMPLNC_STTS = "Compliance Status";
	public static String CONTRACT_PRD_PRJCT_NAME_CODE = "Project Name Code";
	public static String CONTRACT_TERM_DATE = "Contract Term Date";
	public static String CONTRACT_MULTI_PLAN_INDCTR = "Multi Plan Indicator";
	public static String CONTRACT_PRFRMNCE_GUARANTEE = "Performance Guarantee";
	public static String CONTRACT_SALES_MARKET_DATE = "Sales Market Date";
	//	END new constants added for sept release
	
	public static String PRODUCT_PRODUCT_NAME="Product Name"; 
	public static String PRODUCT_EFFECTIVE_DATE="Effective Date";
	public static String PRODUCT_EXPIRY_DATE="Expiry Date";
	public static String PRODUCT_PRODUCT_FAMILY="Product Family";
	public static String PRODUCT_PRODUCT_STRUCTURE="Product Structure Id";
	public static String PRODUCT_DESCRIPTION="Description";
	public static String PRODUCT_STATUS="Status";
	public static String PRODUCT_VERSION="Version";
	public static String PRODUCT_CREATED_DATE="Created Date";
	public static String PRODUCT_CREATED_BY="Created By";
	public static String PRODUCT_LAST_UPDATED_DATE="Last Updated Date";
	public static String PRODUCT_LAST_UPDATED_BY="Last Updated By";
	public static String PRODUCT_TYPE="Product Type"; 
	public static String PRODUCT_MANDATE_TYPE="Mandate Type"; 
	public static String PRODUCT_STATE="State"; 



	public static String PRODUCTSTRUCTURE_NAME="Product Structure Name";
	public static String PRODUCTSTRUCTURE_EFFECTIVE_DATE="Effective Date";
	public static String PRODUCTSTRUCTURE_EXPIRY_DATE="Expiry Date";
	public static String PRODUCTSTRUCTURE_DESCRIPTION="Description";
	public static String PRODUCTSTRUCTURE_STATUS="Status";
	public static String PRODUCTSTRUCTURE_VERSION="Version";
	public static String PRODUCTSTRUCTURE_CREATED_DATE="Created Date";
	public static String PRODUCTSTRUCTURE_CREATED_BY="Created By";
	public static String PRODUCTSTRUCTURE_LAST_UPDATED_DATE="Last Updated Date";
	public static String PRODUCTSTRUCTURE_LAST_UPDATED_BY="Last Updated By";
	public static String PRODUCTSTRUCTURE_TYPE="Product Structure Type"; 
	public static String PRODUCTSTRUCTURE_MANDATE_TYPE="Mandate Type"; 
	public static String PRODUCTSTRUCTURE_STATE="State"; 



	public static String BENEFITCOMPONENT_NAME="Name";
	public static String BENEFITCOMPONENT_EFFECTIVE_DATE="Effective Date";
	public static String BENEFITCOMPONENT_EXPIRY_DATE="Expiry Date";
	public static String BENEFITCOMPONENT_DESCRIPTION="Description";
	public static String BENEFITCOMPONENT_STATUS="Status";
	public static String BENEFITCOMPONENT_VERSION="Version";
	public static String BENEFITCOMPONENT_CREATED_DATE="Created Date";
	public static String BENEFITCOMPONENT_CREATED_BY="Created By";		 
	public static String BENEFITCOMPONENT_LAST_UPDATED_DATE="Last Updated Date";
	public static String BENEFITCOMPONENT_LAST_UPDATED_BY="Last Updated By";
	public static String BENEFITCOMPONENT_TYPE="Benefit Component Type"; 
	public static String BENEFITCOMPONENT_MANDATE_TYPE="Mandate Type"; 
	public static String BENEFITCOMPONENT_STATE="State"; 


	public static String BENEFIT_NAME="Name";
	public static String BENEFIT_DESCRIPTION="Description";
	public static String BENEFIT_TERM="Term";
	public static String BENEFIT_QUALIFIER="Qualifier";
	public static String BENEFIT_PROVIDER_ARRANGEMENT="Provider Arrangement";
	public static String BENEFIT_DATA_TYPE="Data Type";
	public static String BENEFIT_STATUS="Status";
	public static String BENEFIT_VERSION="Version";
	public static String BENEFIT_CREATED_BY="Created By";
	public static String BENEFIT_CREATED_DATE="Created Date";
	public static String BENEFIT_LAST_UPDATED_BY="Last Updated By";
	public static String BENEFIT_LAST_UPDATED_DATE="Last Updated Date";
	public static String BENEFIT_TYPE="Benefit Type"; 
	public static String BENEFIT_JURISDICTION="Jurisdiction";
	public static String BENEFIT_MANDATE_CATEGORY="Mandate Category";
	public static String BENEFIT_CATEGORY="Benefit Category";
	public static String BENEFIT_CITATION_NUMBER="Citation Number";
	public static String BENEFIT_FUNDING_ARRANGEMENT="Benefit Funding Arrangement";
	public static String BENEFIT_EFFECTIVENESS="Effectiveness";
	public static String BENEFIT_TEXT="Benefit Text";

	
	

	
	
	     
	public static String BENEFITLEVEL_TERM="Term";     
	public static String BENEFITLEVEL_QUALIFIER="Qualifier"; 
	public static String BENEFITLEVEL_DESCRIPTION="Description"; 
	public static String BENEFITLEVEL_PVA="PVA"; 
	public static String BENEFITLEVEL_DATA_TYPE="Data Type"; 
	     
	     
	     
	public static String NOTES_ID="Note Id";
	public static String NOTES_NAME="Note Name";
	public static String NOTES_TYPE="Type";
	public static String NOTES_TARGET_SYSTEMS="Target Systems";
	public static String NOTES_TEXT="Text";
	public static String NOTES_STATUS="Status";
	public static String NOTES_DOMAINED_PRODUCT="Domained Product";
	public static String NOTES_DOMAINED_BENEFIT_COMPONENT="Domained Benefit Component";
	public static String NOTES_DOMAINED_BENEFIT="Domained Benefit";
	public static String NOTES_DOMAINED_QUESTION="Domained Question";
	public static String NOTES_DOMAINED_TERM="Domained Term";
	public static String NOTES_DOMAINED_QUALIFIER="Domained Qualifier";
	public static String NOTES_CREATED_BY="Created By";
	public static String NOTES_CREATED_DATE="Created Date";
	public static String NOTES_LAST_UPDATED_BY="Last Updated By";
	public static String NOTES_LAST_UPDATED_DATE="Last Updated Date";

	public static final String CONTRACT_ID_ORDER = "Contract_Id";
	public static final String CONTRACT_TYPE_ORDER = "Contract_Type";
	public static final String CONTRACT_STATUS_ORDER = "Contract_Status";
	public static final String CONTRACT_EFFECTIVE_DATE_ORDER = "Contract_Effective_date";
	public static final String CONTRACT_EXPIRY_DATE_ORDER="Contract_Expiry_Date";
	public static final String CONTRACT_TYPE_VERSION = "Contract_version";
	public static final String PRODUCT_NAME_ORDER = "Product_Name";
	public static final String PRODUCT_VERSION_ORDER = "Product_Version";
	public static final String PRODUCT_EFFECTIVE_DATE_ORDER = "Product_Effective_date";
	public static final String PRODUCT_EXPIRY_DATE_ORDER = "Product_Expiry_Date";
	public static final String PRODUCT_STATUS_ORDER = "Product_Status";
	

	public static final String BENEFIT_COMPONENTS_NAME_ORDER = "Benefit_Components_Name";
	public static final String BENEFIT_COMPONENTS_VERSION_ORDER = "Benefit_Components_Version";
	public static final String BENEFIT_COMPONENTS_EFFECTIVE_DATE_ORDER = "Benefit_Components_Effective_date";
	public static final String BENEFIT_COMPONENTS_EXPIRY_DATE_ORDER = "Benefit_Components_Expiry_Date";
	public static final String BENEFIT_COMPONENTS_STATUS_ORDER = "Benefit_Components_Status";
	

	public static final String BENEFIT_NAME_ORDER = "Benefit_Name";
	public static final String BENEFIT_VERSION_ORDER = "Benefit_Version";
	public static final String BENEFIT_STATUS_ORDER = "Benefit_Status";
	public static final String BENEFIT_DESCRIPTION_ORDER = "Benefit_Description";

	public static final String BENEFIT_LEVEL_QUALIFIER_ORDER = "Benefit_Level_Qualifier";
	public static final String BENEFIT_LEVEL_TERM_ORDER = "Benefit_Level_Term";
	public static final String BENEFIT_LEVEL_DESCRIPTION_ORDER = "Benefit_Level_Description";
	public static final String BENEFIT_LEVEL_PVA_ORDER = "Benefit_Line_Pva";

	public static final String PRODUCT_STRUCTUES_NAME_ORDER = "Product_Structures_Name";
	public static final String PRODUCT_STRUCTUES_VERSION_ORDER = "Product_Structures_Version";
	public static final String PRODUCT_STRUCTUES_EFFECTIVE_DATE_ORDER = "Product_Structures_Effective_date";
	public static final String PRODUCT_STRUCTUES_EXPIRY_DATE_ORDER = "Product_Structures_Expiry_Date";
	public static final String PRODUCT_STRUCTUES_STATUS_ORDER = "Product_Structures_Status";
	public static final String NOTE_NAME_ORDER = "NOTE_NAME";
	public static final String NOTE_VERSION_ORDER = "NOTE_VERSION_ORDER";
	public static final String NOTE_TEXT_ORDER = "NOTE_TEXT_ORDER";
	public static final String NOTE_STATUS_ORDER = "NOTE_STATUS_ORDER";
	public static final String NOTE_TYPE_ORDER = "NOTE_TYPE_ORDER";
	public static final String NOTE_ID_ORDER = "NOTE_ID_ORDER";	
	public static final String ASCENDING = "ASC";
	public static final String DESCENDING = "DESC";

	public static final String ASCENDING_IMAGE = "../../images/pushup.png";
	public static final String ASCENDING_SELECTED_IMAGE = "../../images/pushup1.jpg";
	public static final String DESCENDING_IMAGE = "../../images/pushdown.png";
	public static final String DESCENDING_SELECTED_IMAGE = "../../images/pushdown1.jpg";

	public static final String SORT_SESSION_OBJECT = "SORT_SESSION_OBJECT";
    public  static String SORTED_OBJECT_TYPE = "SORTED_OBJECT_TYPE";

    public  String BREAD_CRUMB = "BREAD_CRUMB";
    public  String OLD_BREAD_CRUMB = "OLD_BREAD_CRUMB";

    public static final String SORT_DONE = "SORT_DONE" ;
    public static final String SEARCH_PERFORMED = "SEARCH_PERFORMED" ;
	public String ATTACHMENTS = "ATTACHMENTS";

	public static final int PRODUCT_FAMILY_CATALOD_ID = 1937 ;
	public static final int TERM_CATALOG_ID = 1934 ;
	public static final int PROVIDER_ARNGEMNT_CATALOG_ID = 1936 ;
	public static final int QUALIFIER_CATALOG_ID = 1935 ;
	public static final int TARGET_SYSTEM_CATALOG_ID = 1944;
	public static final int NOTE_TYPE_CATALOG_ID = 1945;
	
	
	public static final int GROUP_SIZE_CATALOG_ID=1940;
	public static final int PRODUCT_CD_CATALOG_ID=1786;
	public static final int STD_PLAN_CD_CATALOG_ID=1701;
	public static final int PROD_FAM_CATALOG_ID=1937;
	public static final int CORP_PLAN_CD_CATALOG_ID=1947;
	public static final int FUN_ARG_CATALOG_ID=1941;
	public static final int CON_TYP_CATALOG_ID=1946;
	public static final int MANDATE_TYPE=1939;
	public static final int FUD_ARG=1941;
	public static final int DATA_TYPE_CATALOG_ID = -1 ;
	public static final int JURISDICTION = 1942 ;
	public static final int STATE_CODE = 1626 ;
	public static final int EFFEC_CODE=335;
	public static final int MAN_CAT_CODE=334;
	public static final int MBU_CODE=1948;
	public static final int REGULATORY_AGENCY=2020;
	public static final int COMPLIANCE_STTS=2060;
	public static final int PROJECT_NAME_CODE=2040;
	public String ATTACHMENT_RESULT_SUMMARY = "ATTACHMENT_RESULT_SUMMARY";}


