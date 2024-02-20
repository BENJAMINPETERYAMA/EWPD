/*
 * <AdvanceSearchWPDRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import org.owasp.esapi.ESAPI;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.ReportWrapper;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.VariableHdngVO;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.mapping.dao.AdvanceSearchWPDDao;

/**
 * @author UST-GLOBAL This is an class for locating or message text for search,
 *         and get count of total records based on search
 */

public class AdvanceSearchWPDRepositoryImpl implements AdvanceSearchRepository {

	private DataSource dataSource;
	private static Logger log = Logger
			.getLogger(AdvanceSearchWPDRepositoryImpl.class);
	private int dataCount;
	AdvanceSearchWPDDao dao = new AdvanceSearchWPDDao();
	ResourceBundle rb = ResourceBundle.getBundle("blueexchange", Locale.getDefault());
	String mutExportThresholdLimit = rb.getString("MUT_EXPORT_THRESHOLD_LIMIT");
	int maxLimit = Integer.parseInt(mutExportThresholdLimit);
	public Object map;

	/**
	 * 
	 * @return dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * This method returns count of total records based on search criteria
	 */
	public int getRecordCount(SearchCriteria searchCriteria) {
		BxUtil.replaceSingleQuote(searchCriteria);
		String selectQuery = getMessageTextQuery(searchCriteria);
		selectQuery = "select count(*) from (" + selectQuery + ")";
		FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
				dataSource, selectQuery);
 		List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
		Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
		return totalNoOfRecords.intValue();
	}

	/**
	 * This method returns list of search results based on search criteria and
	 * page.
	 */
	public List getRecords(SearchCriteria searchCriteria, int noOfRecords,
			Page page) {
		// additional expression for Standard Message
		String expressionForStdMsg = "";
		BxUtil.replaceSingleQuote(searchCriteria);
		String selectQuery = getMessageTextQuery(searchCriteria);

		if (page != null) {

			if (searchCriteria.getShowOnlyStandardMesages()) {
				expressionForStdMsg = "'N' ISSTANDARDMESSAGE, ";
			} else {
				expressionForStdMsg = "IS_STD_MSG(MESSAGE.MSGTXT) as ISSTANDARDMESSAGE, ";
			}

			selectQuery = "select * from (select MESSAGE.MSGTXT as MSGTXT, "
					+ " HDRRULEID, " + "  SPSID, " + "  VARID, EB03, "
					+ expressionForStdMsg + "  ROWNUM rnm from (" + selectQuery
					+ ") MESSAGE) WHERE rnm between " + page.getStartRowNum()
					+ " and " + page.getEndRowNum();
		}

		SearchResultMapping searchResultMapping = new SearchResultMapping(
				dataSource, selectQuery);
		List result = searchResultMapping.execute();
		return result;

	}
	

	/**
	 * This method creates sql query based on search criteria.
	 * 
	 * @param searchCriteria
	 * @return
	 */
	private String getMessageTextQuery(SearchCriteria searchCriteria) {
		String selectQuery = "";
		String selectQueryForStandardMessage = "";
		String extraWhereConditionForHeaderRule = "";

		boolean useHdrPart = false;
		boolean useVarPart = false;
		
		//Select Query For Standard Messages -- BXNI CHANGE
		if(searchCriteria.getShowOnlyStandardMesages()){
			
			if(searchCriteria.getShowOnlyStandardMesages()){
				selectQueryForStandardMessage += " SELECT "
						+ " STDMSG.STD_MESSAGE MSGTXT, "
						+ "  ' ' HDRRULEID, "
						+ "  ' ' SPSID, "
						+ "    VAR.CNTRCT_VAR_ID VARID, "
						+ "    VAREB0.EB03_ASSN AS EB03 "
						+ "    FROM BX_CNTRCT_VAR_MAPG VAR "
						+ "    RIGHT OUTER JOIN BX_CNTRCT_VAR_MAPG_VAL VAREB0 "
						+ "    ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
						+ "    INNER JOIN BX_STANDARD_MESSAGE STDMSG "
						+ "    ON VAREB0.DATA_ELEMENT_VAL = STDMSG.STD_MESSAGE "						
						+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";

			}
			
			
			if(!BxUtil.hasText(searchCriteria.getEB03()) && !BxUtil.hasText(searchCriteria.getMessageText()) &&
					!BxUtil.hasText(searchCriteria.getVariableId())){
				selectQueryForStandardMessage = " Select STD_MESSAGE MSGTXT , "
				+ " ' ' HDRRULEID, "
				+ " ' ' SPSID, "
				+ " ' ' VARID, "
				+ " ' ' EB03 "
				+ " FROM BX_STANDARD_MESSAGE "
				+ " WHERE STD_MESSAGE IS NOT NULL ";
		
		return selectQueryForStandardMessage;
		}
		}

		//BXNI CHANGE ENDS

		if (BxUtil.hasText(searchCriteria.getEB03())
				|| BxUtil.hasText(searchCriteria.getMessageText())) {
			useHdrPart = true;
			useVarPart = true;
		}
		if (BxUtil.hasText(searchCriteria.getVariableId())) {
			useVarPart = true;
		}

		String formattedEB0 = BxUtil.getFormattedEB03(searchCriteria.getEB03());
		selectQuery += "(";
		if (useHdrPart) {

			String extraWhereCondition = "";
			if (BxUtil.hasText(searchCriteria.getMessageText())) {
				extraWhereCondition += " upper(MSG.MSG_TEXT) like '%"
						+ searchCriteria.getMessageText().trim().toUpperCase()
						+ "%'";
				
			}

			if (BxUtil.hasText(extraWhereCondition)
					&& BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " or ";
			}

			if (BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " upper(MSG.EB03) in ("
						+ formattedEB0.toUpperCase() + ")";
			}
			// header rule main tables

			selectQuery += "(SELECT MSG.MSG_TEXT MSGTXT, MSG.HDR_RULE_ID HDRRULEID, MSG.SPS_ID SPSID, "
					+ " ' ' VARID, MSG.EB03 AS EB03 "
					+ " FROM BX_CSTM_MSG_TEXT MSG "					
					+ " WHERE MSG.MSG_TEXT is not null ";
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + ")";
				extraWhereConditionForHeaderRule = extraWhereCondition;
			}
			selectQuery += " GROUP BY MSG.MSG_TEXT,MSG.HDR_RULE_ID,MSG.SPS_ID,EB03) ";

			selectQuery += "union";
			// header rule temp tables
			selectQuery += "(SELECT MSG.MSG_TEXT MSGTXT, MSG.HDR_RULE_ID HDRRULEID, MSG.SPS_ID SPSID, "
					+ " ' ' VARID, MSG.EB03 AS EB03 "
					+ " FROM TEMP_BX_CSTM_MSG_TEXT MSG "
					+ " WHERE MSG.MSG_TEXT is not null ";
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + " )";
			}
			selectQuery += " GROUP BY MSG.MSG_TEXT,MSG.HDR_RULE_ID,MSG.SPS_ID,EB03) ";
		}

		if (useVarPart) {
			if (useHdrPart) {
				selectQuery += " union ";
			}

			String extraWhereCondition = "";
			if (BxUtil.hasText(searchCriteria.getMessageText())) {
				extraWhereCondition += " ( VAREB0.DATA_ELEMENT_ID ='MSG' and upper(VAREB0.DATA_ELEMENT_VAL) like '%"
						+ searchCriteria.getMessageText().trim().toUpperCase()
						+ "%')";
			}

			if (BxUtil.hasText(extraWhereCondition)
					&& BxUtil.hasText(searchCriteria.getVariableId())) {
				extraWhereCondition += " or ";
			}

			if (BxUtil.hasText(searchCriteria.getVariableId())) {
				extraWhereCondition += "  upper(VAR.CNTRCT_VAR_ID)='"
						+ searchCriteria.getVariableId().trim().toUpperCase() + "'";
			}

			if (BxUtil.hasText(extraWhereCondition)
					&& BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " or ";
			}

			if (BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " upper(VAREB0.EB03_ASSN) in ("
						+ formattedEB0.toUpperCase() + ")";
			}

			// variable main tables
			
			selectQuery += "(SELECT VAREB0.DATA_ELEMENT_VAL MSGTXT, ' ' HDRRULEID, ' ' SPSID, "
					+ " VAR.CNTRCT_VAR_ID VARID, VAREB0.EB03_ASSN AS EB03 "
					+ " FROM BX_CNTRCT_VAR_MAPG VAR "
					+ " RIGHT OUTER JOIN BX_CNTRCT_VAR_MAPG_VAL VAREB0 ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
					+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";
					
					
					
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + " )";
				//BXNI CHANGE
				selectQueryForStandardMessage += " and ( " + extraWhereCondition +" ) ";
				//BXNI CHANGE ENDS
			}
			selectQuery += " GROUP BY VAREB0.DATA_ELEMENT_VAL,VAR.CNTRCT_VAR_ID,VAR.VAR_MAPG_SYS_ID,VAREB0.EB03_ASSN) ";

			selectQuery += " union ";
			// variable temp tables
			selectQuery += " (SELECT VAREB0.DATA_ELEMENT_VAL, ' ' HDRRULEID, ' ' SPSID, "
					+ " VAR.CNTRCT_VAR_ID VARID, VAREB0.EB03_ASSN AS EB03 "
					+ " FROM TEMP_BX_CNTRCT_VAR_MAPG VAR "
					+ " RIGHT OUTER JOIN TEMP_BX_CNTRCT_VAR_MAPG_VAL VAREB0 ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
					+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + " ) ";
			}
			selectQuery += " GROUP BY VAREB0.DATA_ELEMENT_VAL,VAR.CNTRCT_VAR_ID,VAR.VAR_MAPG_SYS_ID,VAREB0.EB03_ASSN) ";

			//BXNI CHANGE
			if(searchCriteria.getShowOnlyStandardMesages()){
				
				//FOR TEMP TABLE OF VARIABLE
				selectQueryForStandardMessage += " UNION (SELECT "
					+ " STDMSG.STD_MESSAGE MSGTXT, "
					+ "  ' ' HDRRULEID, "
					+ "  ' ' SPSID, "
					+ "    VAR.CNTRCT_VAR_ID VARID, "
					+ "    VAREB0.EB03_ASSN AS EB03 "
					+ "  FROM TEMP_BX_CNTRCT_VAR_MAPG VAR "
					+ "    RIGHT OUTER JOIN TEMP_BX_CNTRCT_VAR_MAPG_VAL VAREB0 "
					+ "    ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
					+ "  INNER JOIN BX_STANDARD_MESSAGE STDMSG "
					+ "   ON VAREB0.DATA_ELEMENT_VAL = STDMSG.STD_MESSAGE "
					+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";
					
										
						
				if (BxUtil.hasText(extraWhereCondition)) {				
				selectQueryForStandardMessage += " and ( " + extraWhereCondition +" )) ";
				}
				//FOR MAIN TABLE OF HEADER RULE-SPS ID
				selectQueryForStandardMessage += " UNION SELECT STDMSG.STD_MESSAGE MSGTXT, "
					+ " MSG.HDR_RULE_ID HDRRULEID, "
					+ "  MSG.SPS_ID SPSID, "
					+ "  ' ' VARID, "
						  + "  MSG.EB03 AS EB03 "
						  + "	FROM BX_CSTM_MSG_TEXT MSG "
						  + "	INNER JOIN BX_STANDARD_MESSAGE STDMSG "
						  + "	  ON MSG.MSG_TEXT = STDMSG.STD_MESSAGE "						
						  + "	WHERE MSG.MSG_TEXT IS NOT NULL ";
						  
				
				if (BxUtil.hasText(extraWhereConditionForHeaderRule)) {				
					selectQueryForStandardMessage += " and ( " + extraWhereConditionForHeaderRule +" ) ";
					}			
						//FOR TEMP TABLE OF HEADER RULE-SPS ID
				selectQueryForStandardMessage += " UNION SELECT STDMSG.STD_MESSAGE MSGTXT, "
					+ "	  MSG.HDR_RULE_ID HDRRULEID, "
					  + "	  MSG.SPS_ID SPSID, "
					  + "	  ' ' VARID, "
					  + "	  MSG.EB03 AS EB03"
					  + "	FROM TEMP_BX_CSTM_MSG_TEXT MSG "
					  + "	INNER JOIN BX_STANDARD_MESSAGE STDMSG "
					  + "	  ON MSG.MSG_TEXT = STDMSG.STD_MESSAGE "
					  + "	WHERE MSG.MSG_TEXT IS NOT NULL ";
				if (BxUtil.hasText(extraWhereConditionForHeaderRule)) {				
					selectQueryForStandardMessage += " and ( " + extraWhereConditionForHeaderRule +" )";
					}
					if(! "".equals(searchCriteria.getMessageText().trim().toUpperCase())
							&& null != searchCriteria.getMessageText().trim().toUpperCase()){
				selectQueryForStandardMessage += " UNION ALL "
					+ " (SELECT STD_MESSAGE MSGTXT, "
					+ "   ' ' HDRRULEID, "
					+ "  ' ' SPSID, "
					+ "  ' '  VARID, "
					+ "   ' ' EB03 "
					+ "  FROM BX_STANDARD_MESSAGE  "
					+ " WHERE ( upper(STD_MESSAGE) like '%"
					+ searchCriteria.getMessageText().trim().toUpperCase()
					+ "%')) ";
					}
				
				return selectQueryForStandardMessage;
			}
			//BXNI CHANGE ENDS
		}
		selectQuery += ")";
				
		return selectQuery;
	}
	/**
	 * Query mapper class for Search Result
	 * 
	 */
	private final class SearchResultMapping extends MappingSqlQuery {

		private SearchResultMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setMessageText(rs.getString("MSGTXT"));
			searchResult.setVariableId(rs.getString("VARID"));
			searchResult.setHeaderRuleId(rs.getString("HDRRULEID"));
			searchResult.setEB03(rs.getString("EB03"));
			searchResult.setSpsId(rs.getString("SPSID"));
			searchResult.setIsStandardMessage(rs.getString("ISSTANDARDMESSAGE"));
			return searchResult;

		}
	}

	
	
	/**
	 * Query mapper for advance search total record count.
	 * 
	 */
	private final class FindTotalNoOfRecordsQuery extends MappingSqlQuery {

		private FindTotalNoOfRecordsQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}

	}

	/**
	 * Query mapper for the advance search results
	 * 
	 */
	private class GetAdvanceSearchRecordsVarQry extends MappingSqlQuery {

		private String loggedUser;
		private String authorizeApprove;
		private SearchCriteria searchCriteria;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		private GetAdvanceSearchRecordsVarQry(DataSource dataSource,
				String query, SearchCriteria searchCriteria) {
			super(dataSource, query);
			compile();
			this.searchCriteria = searchCriteria;
			this.loggedUser = searchCriteria.getLoggedUser();
			this.authorizeApprove = searchCriteria.getAuthorizedToApproveFlag();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setVariableId(rs.getString("VAR"));

			if (rs.getString("VAR_DESCRIPTION") != null){
				searchResult.setDescription(rs.getString("VAR_DESCRIPTION")
						.toUpperCase());
			}
			
			if (rs.getString("SYSTEM") != null){
				searchResult.setSystem(rs.getString("SYSTEM").toUpperCase());
			}
			if (rs.getString("EB01") != null){
				searchResult.setEB01(rs.getString("EB01").toUpperCase());
			}
			if (rs.getString("EB03") != null){
				searchResult.setEB03(BxUtil.removeCommaFromEB03(rs.getString(
						"EB03").toUpperCase()));
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				if (rs.getString("III02") != null)
					searchResult.setIII02(rs.getString("III02").toUpperCase());
			}
			if (null != searchCriteria.getMessageText()
					&& !searchCriteria.getMessageText().equals("")) {
				if (rs.getString("MESSAGE") != null){
					searchResult.setMessageText(rs.getString("MESSAGE")
							.toUpperCase());
				}
			}
			if (null != searchCriteria.getNoteType()
					&& !searchCriteria.getNoteType().equals("")) {
				if (rs.getString("NOTE_TYPE_CODE") != null){
					searchResult.setNoteTypeCode(rs.getString("NOTE_TYPE_CODE")
							.toUpperCase());
				}
			}
			if (rs.getString("USR") != null){
				searchResult.setUser(rs.getString("USR").toUpperCase());
			}
			
			if (rs.getDate("create_date") != null){
				searchResult.setFormattedDate(simpleDateFormat.format(rs
						.getDate("create_date")));
			}
			if (rs.getString("Status") != null){
				searchResult.setStatus(rs.getString("Status").toUpperCase());
			}
			
			if (rs.getString("NOT_COMPLTE_FLAG") != null){
				searchResult.setNotCompleteFlag(rs
						.getString("NOT_COMPLTE_FLAG").toUpperCase());
			}
			//Audit Lock Status fetched -- October Release
			if (rs.getString("AUDIT_LOCK") != null){
				searchResult.setAuditLock(rs
						.getString("AUDIT_LOCK").toUpperCase());
			}
			searchResult.setLockedUserId(rs
					.getString("bolk_bus_obj_lock_usr_id"));

			return getVariableLockDetails(searchResult, this.loggedUser, "",
					this.authorizeApprove);
		}

	}

	/**
	 * Lock details for Variable criteria
	 * 
	 * @param searchResult
	 * @param loggerUser
	 * @param authorizedToUnlock
	 * @param authorizedToApprove
	 * @return SearchResult
	 */
	private SearchResult getVariableLockDetails(SearchResult searchResult,
			String loggerUser, String authorizedToUnlock,
			String authorizedToApprove) {
		int sentToTest = 0;
		int approve = 0;
		int lock = 1;

		if (null == searchResult.getLockedUserId()) {
			lock = 0;
		} else {

			if (null != loggerUser
					&& loggerUser.equals(searchResult.getLockedUserId())) {
				lock = 0;
			}
		}

		if (lock == 0) {
			searchResult.setLocked("false");
		} else {
			searchResult.setLocked("true");
		}
		if (null != searchResult.getStatus()
				&& searchResult.getStatus().equals("SCHEDULED_TO_TEST")) {
			sentToTest = 1;
			approve = 1;
		}
		if (null != searchResult.getStatus()
				&& searchResult.getStatus().equals("NOT_APPLICABLE")) {
			sentToTest = 1;
			approve = 1;
		}
		if (null != searchResult.getStatus()
				&& searchResult.getStatus().equals("UNMAPPED")) {
			sentToTest = 1;
			approve = 1;
		}
		if (null != searchResult.getStatus()
				&& searchResult.getStatus().equals("OBJECT_TRANSFERRED")) {
			sentToTest = 1;
		}
		if (null != searchResult.getStatus()
				&& searchResult.getStatus().equals("SCHEDULED_TO_PRODUCTION")
				|| searchResult.getStatus().equals("PUBLISHED")) {
			sentToTest = 1;
			approve = 1;
		}
		if (sentToTest != 1 && lock != 1) {
			searchResult.setSendToTest("true");
		}
		if (approve != 1 && lock != 1) {
			if (authorizedToApprove.equalsIgnoreCase("true")
					|| searchResult.getStatus().equals("OBJECT_TRANSFERRED")) {

				searchResult.setApprove("true");
			}

		}
		return searchResult;

	}

	/**
	 * method gets the total record count for pagination purpose in Advance
	 * Search WPD module
	 * 
	 * @param SearchCriteria
	 *@return totalNoOfRecords
	 */
	public int getAdvanceRecordCount(SearchCriteria searchCriteria) {

		StringBuffer advSearchUnMappedVariablesQuery = new StringBuffer();
		StringBuffer advSearchMappedVariablesQuery = new StringBuffer();
		StringBuffer totalNoOfRecordsQueryToAppend = new StringBuffer();
		StringBuffer advSearchTotalNoOfRecordsQuery = new StringBuffer();
		String varConditionUnMapped = "";
		String varDescConditionUnMapped = "";
		String userConditionUnMapped = "";
		String varConditionMapped = "";
		String varDescConditionMapped = "";		
		String userConditionMapped = "";
		String EB01Condition = "";
		String EB03Condition = "";
		String III02Condition = "";
		String noteTypeCondition = "";
		String messageTextCondition = "";
		String conditionalqueryNA = "";
		String conditionalqueryNotFinalised = "";
		boolean containsEb03Comma = false;
		
		String contractAdvanceSearchUnmappedVarsegCondn1 = "";
		String contractAdvanceSearchUnmappedVarsegCondn2 = "";

		String contractAdvanceSearchMappedVarsegForMainCondn1 = "";
		String contractAdvanceSearchMappedVarsegForMainCondn2 = "";
		
		String contractAdvanceSearchMappedVarsegForTempCondn1 = "";
		String contractAdvanceSearchMappedVarsegForTempCondn2 = "";
		
		String revisionDateForISGContract = "";
		
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			String[] ebo3Array = searchCriteria.getEB03().split(",");
			if(ebo3Array.length >1){
				containsEb03Comma = true;
			}
			
		}
		String count = " SELECT COUNT(VAR) FROM ( ";

		// UnMapped Input Check

		// Variable Input Check
		if (null != searchCriteria.getVariableId()){
			varConditionUnMapped = "  and UNMAPPED.var = '"
					+ searchCriteria.getVariableId() + "' ";
		}
		
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			varDescConditionUnMapped = "  and upper(UNMAPPED.VAR_DESCRIPTION) like upper('%"
					+ searchCriteria.getVariableDescription() + "%')";
		}
		
		// Mapped/NA/NF Input Check

		// Not Applicable Conditions
		if ((searchCriteria.isMapped()) && !(searchCriteria.isNotApplicable())){
			conditionalqueryNA = " and VAR.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE' ";
		}
		if ((searchCriteria.isNotApplicable()) && !(searchCriteria.isMapped())){
			conditionalqueryNA = " and VAR.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE' ";
		}
		if ((searchCriteria.isMapped()) && (searchCriteria.isNotApplicable())){
			conditionalqueryNA = "";
		}
		// Not Finalised Condition
		if (searchCriteria.isNotFinalized()){
			conditionalqueryNotFinalised = " and VAR.MAPPNG_CMP_IND = 'N' ";
		}
		// Variable Input Check
		if (null != searchCriteria.getVariableId()
				&& !searchCriteria.getVariableId().equals("")){
			varConditionMapped = "  and var.VARIABLE = '"
					+ searchCriteria.getVariableId() + "' ";
		}
		
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			varDescConditionMapped = "  and upper(VAR.VARIABLE_DESCRIPTION) like upper('%"
					+ searchCriteria.getVariableDescription() + "%') ";
		}
		// User Input Check
		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals("")){
			userConditionMapped = " and UPPER(VAR.USR) IN( "
					+ searchCriteria.getCommaSeperatedUser() + ")";
		}
		/*-- -  changed for CR  ------*/
		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals("")){
			userConditionUnMapped = " and UPPER(USR) IN( "
					+ searchCriteria.getCommaSeperatedUser() + ")";
		}
		// EB01 Input Check
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			EB01Condition = " and var.EB01 = '" + searchCriteria.getEB01()
					+ "' ";
		}
		// EB03 Input Check
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")){
			if(containsEb03Comma){
				EB03Condition = " and VAR.EB03 IS NOT NULL";
			}else{
				EB03Condition = " and VAR.EB03 LIKE '%, "
					+ searchCriteria.getEB03() + " ,%'";
			}
		}
		

		// III02 Input Check
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			III02Condition = " and var.III02 LIKE '%, " + searchCriteria.getIII02()
					+ " ,%'";
		}
		// Note Type Input Check
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			noteTypeCondition = " and var.note_type_code LIKE '%, "
					+ searchCriteria.getNoteType() + " ,%'";
		}
		// Message Text Input Check
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			messageTextCondition = " and upper(var.message) like upper('%"
					+ searchCriteria.getMessageText() + "%')";
		}
		
		if(null != searchCriteria.getContractId()){
			if(DomainConstants.CONTRACT_SYSTEM_LG.equals(searchCriteria.getContractSystem()) ){
			
			
			contractAdvanceSearchUnmappedVarsegCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchUnmappedVarsegCondn2 = " and LEGACY.contVar = varseg.contract_var    "
														+" AND varseg.contract='"
														+ searchCriteria.getContractId().toUpperCase().trim()
														+ "' "
														+" AND varseg.start_dt= to_date('"
														+searchCriteria.getContractStartDate()
														+"' ,'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForMainCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+"' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+"' , 'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForTempCondn1 =  " , VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForTempCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+ "' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+ "' , 'mm/dd/yyyy') ";
			
			} else if(DomainConstants.CONTRACT_SYSTEM_ISG.equals(searchCriteria.getContractSystem()) ){
				
				//Added Revision Date Search Criteria for Jan 2012 Release CR
				if(null != searchCriteria.getISGContractRevisionDate()){
					revisionDateForISGContract = " and cpfxp_r_date = to_date('"
											   + searchCriteria.getISGContractRevisionDate()
											   + "' ,'mm/dd/yyyy') " ;
				}
				contractAdvanceSearchUnmappedVarsegCondn1 =    	 ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
				 												 + " WHERE cpfxp_cont_id='"
				 												 + searchCriteria.getContractId().toUpperCase().trim()
				 												 + "' "
				 												 + "and cpfxp_s_date = to_date('"
				 												 + searchCriteria.getContractStartDate()
				 												 + "' ,'mm/dd/yyyy') " 
				 												 + revisionDateForISGContract
				 												 + " )  VARSEG ";
				
				contractAdvanceSearchUnmappedVarsegCondn2 = 	 " and LEGACY.contVar = varseg.cpfxp_contvar   ";
				
				contractAdvanceSearchMappedVarsegForMainCondn1 = ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																 + " WHERE cpfxp_cont_id='"
																 + searchCriteria.getContractId().toUpperCase().trim()
																 + "' "
																 + "and cpfxp_s_date = to_date('"
																 + searchCriteria.getContractStartDate()
																 + "' ,'mm/dd/yyyy') " 
																 + revisionDateForISGContract
																 + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				
				contractAdvanceSearchMappedVarsegForTempCondn1 =  ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																  + " WHERE cpfxp_cont_id='"
																  + searchCriteria.getContractId().toUpperCase().trim()
																  + "' "
																  + "and cpfxp_s_date = to_date('"
																  + searchCriteria.getContractStartDate()
																  + "' ,'mm/dd/yyyy') " 
																  + revisionDateForISGContract
																  + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForTempCondn2 	= " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				}
		}
		
		advSearchUnMappedVariablesQuery.append(" SELECT distinct(UNMAPPED.var) VAR,");
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchUnMappedVariablesQuery.append("  UNMAPPED.VAR_DESCRIPTION VAR_DESCRIPTION,"); 
		}
		
			advSearchUnMappedVariablesQuery.append(" UNMAPPED.USR USR ,'UNMAPPED' Status,'' NOT_COMPLTE_FLAG  FROM( ");
		// UnMapped Variable Query
		advSearchUnMappedVariablesQuery.append(" SELECT LEGACY.contVar VAR,");
		
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchUnMappedVariablesQuery.append("  LEGACY.Variable_Desc VAR_DESCRIPTION ,"); 
		}

		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			advSearchUnMappedVariablesQuery.append("'' EB01 ,");
		}
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' EB03 , ");
		}
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' MESSAGE ,");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' NOTE_TYPE_CODE , ");
		}
		advSearchUnMappedVariablesQuery
				.append(" USR USR , 'UNMAPPED' Status,'' NOT_COMPLTE_FLAG ");
		advSearchUnMappedVariablesQuery.append(" FROM ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar , ");
		advSearchUnMappedVariablesQuery
				.append("  DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_LST_CHNGD_USR_ID, LG.LAST_UPDATED_USERID) USR , ");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRM  ");
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchUnMappedVariablesQuery.append("  ,DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.CONT_VAR_TX) Variable_Desc "); 
		}
		
		advSearchUnMappedVariablesQuery.append("   FROM  ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT e.CONTRACT_VAR , e.CONTRACT_VAR_FORMAT , e.LAST_UPDATED_USERID ");
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchUnMappedVariablesQuery.append("  ,e.CONT_VAR_TX "); 
		}
		advSearchUnMappedVariablesQuery.append(" FROM CONTVAR E) LG ");
		advSearchUnMappedVariablesQuery.append("  FULL OUTER JOIN  ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT c.cpfxp_contvar ,c.cpfxp_format , c.CPFXP_LST_CHNGD_USR_ID ");
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchUnMappedVariablesQuery.append(" ,c.CPFXP_TEXT");
		}
		
		advSearchUnMappedVariablesQuery.append(" FROM ISG_CPFXP_CONTVAR c ) ISG ");
		advSearchUnMappedVariablesQuery
				.append(" ON LG.CONTRACT_VAR = ISG.cpfxp_contvar ");
		advSearchUnMappedVariablesQuery.append(" ) LEGACY ");
		advSearchUnMappedVariablesQuery.append(" INNER JOIN ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG) VALID_FRMT ");
		advSearchUnMappedVariablesQuery
				.append(" ON LEGACY.VAR_FRM     = VALID_FRMT.VAR_FRMT ");
		
		//Contract Advance Search filter for Unmapped
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn1);
		
		advSearchUnMappedVariablesQuery
				.append(" WHERE NOT EXISTS (select 1 from BX_CNTRCT_VAR_MAPG where CNTRCT_VAR_ID = LEGACY.contVar) ");
		
		//Contract Advance Search filter for Unmapped
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn2);
		advSearchUnMappedVariablesQuery.append(" )UNMAPPED ");
		if((null != searchCriteria.getMajorHeading() 
				&& !"".equals(searchCriteria.getMajorHeading()))
				|| (null != searchCriteria.getMinorHeading() 
						&& !"".equals(searchCriteria.getMinorHeading()))){
			advSearchUnMappedVariablesQuery.append(" , VAR_HDNG_MV hdng WHERE 1 = 1 AND hdng.var = UNMAPPED.var ");
		}else{
			advSearchUnMappedVariablesQuery.append(" where 1=1 ");
		}
		if(null != searchCriteria.getMajorHeading() 
				&& !"".equals(searchCriteria.getMajorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MAJOR) LIKE upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		if(null != searchCriteria.getMinorHeading() 
				&& !"".equals(searchCriteria.getMinorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MINOR) LIKE upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		// Mapped Variable Query
		advSearchMappedVariablesQuery.append(" SELECT distinct(VAR.VARIABLE) VAR,");
		
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.VARIABLE_DESCRIPTION VAR_DESCRIPTION, "); 
		}
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.EB01 , ");
		}
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.EB03 , ");
		}
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.III02 ,");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.MESSAGE ,");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.NOTE_TYPE_CODE , ");
		}
		advSearchMappedVariablesQuery
				.append(" VAR.USR ,VAR.VAR_MAPG_STTS_CD Status , MAPPNG_CMP_IND NOT_COMPLTE_FLAG ");
		advSearchMappedVariablesQuery.append(" FROM ");
		advSearchMappedVariablesQuery.append(" ( ");
		advSearchMappedVariablesQuery
				.append(" SELECT V.CNTRCT_VAR_ID VARIABLE, ");
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchMappedVariablesQuery.append(" NVL(V.CNTRCT_VAR_DESC, ' ') VARIABLE_DESCRIPTION, "); 
		}
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			advSearchMappedVariablesQuery
					.append(" (SELECT DATA_ELEMENT_VAL FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='EB01' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) EB01, ");
		}
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" COMMA_SEPERATED_VAR_EB03STRING(V.VAR_MAPG_SYS_ID,'EB03','N', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								")  EB03, ");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','N') || ' ,' EB03, ");
			}
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','N') || ' ,' EB03, ");
		}
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','N') || ' ,' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			//advSearchMappedVariablesQuery.append(" V.MSG MESSAGE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'MSG','N') || ' ,' MESSAGE, ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			//advSearchMappedVariablesQuery
			//		.append(" (SELECT UNIQUE(DATA_ELEMENT_VAL) FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='NOTE_TYPE_CODE' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) NOTE_TYPE_CODE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','N') || ' ,' NOTE_TYPE_CODE, ");
		}
		advSearchMappedVariablesQuery
				.append(" V.LST_CHG_USR USR, V.VAR_MAPG_STTS_CD, V.MAPPNG_CMP_IND ");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG V ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))
				||(null != searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" ,var_hdng_mv hdng ");
		}
		
		//Contract Advance Search filter for Unmapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn1);
		
		advSearchMappedVariablesQuery
				.append(" WHERE 1=1 AND V.IN_TEMP_TAB = 'N' ");
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))
				||(null != searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.VAR = V.CNTRCT_VAR_ID ");
		}
		
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append("  and upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append(" %')");
		}
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append("  and upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append(" %')");
		}
		
		//Contract Advance Search filter for Unmapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn2);

		advSearchMappedVariablesQuery.append("UNION all ");

		advSearchMappedVariablesQuery
				.append("SELECT V.CNTRCT_VAR_ID VARIABLE, ");
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			advSearchMappedVariablesQuery.append(" V.CNTRCT_VAR_DESC VARIABLE_DESCRIPTION, "); 
		}
		
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			advSearchMappedVariablesQuery
					.append("(SELECT DATA_ELEMENT_VAL FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='EB01' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) EB01, ");
		}
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" COMMA_SEPERATED_VAR_EB03STRING(V.VAR_MAPG_SYS_ID,'EB03','Y', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								")  EB03, ");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','Y') || ' ,' EB03, ");
			}
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','Y') || ' ,' EB03, ");
		}
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','Y') || ' ,' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			//advSearchMappedVariablesQuery.append("V.MSG MESSAGE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'MSG','Y') || ' ,' MESSAGE, ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			//advSearchMappedVariablesQuery
			//		.append("(SELECT UNIQUE(DATA_ELEMENT_VAL) FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='NOTE_TYPE_CODE' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) NOTE_TYPE_CODE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','Y') || ' ,' NOTE_TYPE_CODE, ");
		}
		advSearchMappedVariablesQuery
				.append("V.LST_CHG_USR USR, V.VAR_MAPG_STTS_CD, V.MAPPNG_CMP_IND ");
		advSearchMappedVariablesQuery
				.append("FROM TEMP_BX_CNTRCT_VAR_MAPG V, BX_CNTRCT_VAR_MAPG MV ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))
				||(null != searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" ,VAR_HDNG_MV hdng ");
		}
		//Contract Advance Search filter for Unmapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn1);
		
		advSearchMappedVariablesQuery
				.append("WHERE 1=1 AND MV.IN_TEMP_TAB = 'Y' AND MV.CNTRCT_VAR_ID = V.CNTRCT_VAR_ID ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))
				||(null != searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.VAR = V.CNTRCT_VAR_ID ");
		}
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append("  and upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append(" %')");
		}
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append("  and upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append(" %')");
		}
		
		//Contract Advance Search filter for Unmapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn2);

		advSearchMappedVariablesQuery
				.append(" ) VAR, CONTVAR E, ISG_CPFXP_CONTVAR C ");

		advSearchMappedVariablesQuery.append("WHERE ");
		advSearchMappedVariablesQuery
				.append("VAR.VARIABLE = E.CONTRACT_VAR (+) AND ");
		advSearchMappedVariablesQuery
				.append("VAR.VARIABLE = c.cpfxp_contvar (+) ");
		// advSearchMappedVariablesQuery.append("VAR.VARIABLE = DTL.CONTRACT_VAR (+) ");

		// Combinations of Various Input Check Box Conditions

		// When unmapped alone is checked
		if (!searchCriteria.isMapped() && !searchCriteria.isNotApplicable()
				&& !searchCriteria.isNotFinalized()) {
			if (searchCriteria.isUnMapped()) {
				log.info("Variable Criteria 1");
				totalNoOfRecordsQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
			}
		}
		// UnMapped along with NA/NF checked
		if ((searchCriteria.isUnMapped()) && !(searchCriteria.isMapped())) {
			log.info("Variable Criteria 2");
			// If NA or NF is also checked
			if (searchCriteria.isNotApplicable()
					|| searchCriteria.isNotFinalized()) {
				log.info("Variable Criteria 3");
				totalNoOfRecordsQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
				if (searchCriteria.isNotApplicable()) {
					log.info("Variable Criteria 4");
					totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
					totalNoOfRecordsQueryToAppend
							.append(advSearchMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend.append(conditionalqueryNA);
					totalNoOfRecordsQueryToAppend.append(varConditionMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionMapped);
					totalNoOfRecordsQueryToAppend.append(EB01Condition);
					totalNoOfRecordsQueryToAppend.append(EB03Condition);
					totalNoOfRecordsQueryToAppend.append(III02Condition);
					totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
					totalNoOfRecordsQueryToAppend.append(messageTextCondition);
				}
				if (searchCriteria.isNotFinalized()) {
					log.info("Variable Criteria 5");
					totalNoOfRecordsQueryToAppend.append(" UNION ");
					totalNoOfRecordsQueryToAppend
							.append(advSearchMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend
							.append(conditionalqueryNotFinalised);
					totalNoOfRecordsQueryToAppend.append(varConditionMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionMapped);
					totalNoOfRecordsQueryToAppend.append(EB01Condition);
					totalNoOfRecordsQueryToAppend.append(EB03Condition);
					totalNoOfRecordsQueryToAppend.append(III02Condition);
					totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
					totalNoOfRecordsQueryToAppend.append(messageTextCondition);
				}
			}

		}

		// Unmapped,Mapped along with NA/NF checked

		if ((searchCriteria.isUnMapped()) && (searchCriteria.isMapped())) {
			log.info("Variable Criteria 6");
			totalNoOfRecordsQueryToAppend
					.append(advSearchUnMappedVariablesQuery);
			totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
			totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
			totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
			totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
			totalNoOfRecordsQueryToAppend.append(advSearchMappedVariablesQuery);
			totalNoOfRecordsQueryToAppend.append(conditionalqueryNA);
			totalNoOfRecordsQueryToAppend.append(varConditionMapped);
			totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
			totalNoOfRecordsQueryToAppend.append(userConditionMapped);
			totalNoOfRecordsQueryToAppend.append(EB01Condition);
			totalNoOfRecordsQueryToAppend.append(EB03Condition);
			totalNoOfRecordsQueryToAppend.append(III02Condition);
			totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
			totalNoOfRecordsQueryToAppend.append(messageTextCondition);
			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * totalNoOfRecordsQueryToAppend.append(" UNION ");
			 * totalNoOfRecordsQueryToAppend
			 * .append(advSearchMappedVariablesQuery);
			 * totalNoOfRecordsQueryToAppend
			 * .append(conditionalqueryNotFinalised);
			 * totalNoOfRecordsQueryToAppend.append(varConditionMapped);
			 * totalNoOfRecordsQueryToAppend.append(userConditionMapped);
			 * totalNoOfRecordsQueryToAppend.append(EB01Condition);
			 * totalNoOfRecordsQueryToAppend.append(EB03Condition);
			 * totalNoOfRecordsQueryToAppend.append(III02Condition);
			 * totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
			 * totalNoOfRecordsQueryToAppend.append(messageTextCondition); }
			 */
		}

		// Mapped along with NA/NF checked
		// If NA/NF alone is checked, then also same condition is used
		if (!searchCriteria.isUnMapped()) {

			if ((searchCriteria.isMapped())
					|| (searchCriteria.isNotApplicable())
					|| (searchCriteria.isNotFinalized())) {

				if ((searchCriteria.isMapped())
						|| (searchCriteria.isNotApplicable())) {
					log.info("Variable Criteria 7");
					totalNoOfRecordsQueryToAppend
							.append(advSearchMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend.append(conditionalqueryNA);
					totalNoOfRecordsQueryToAppend.append(varConditionMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionMapped);
					totalNoOfRecordsQueryToAppend.append(EB01Condition);
					totalNoOfRecordsQueryToAppend.append(EB03Condition);
					totalNoOfRecordsQueryToAppend.append(III02Condition);
					totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
					totalNoOfRecordsQueryToAppend.append(messageTextCondition);
				}

				if (!searchCriteria.isMapped()
						&& searchCriteria.isNotFinalized()) {
					log.info("Variable Criteria 8");
					if ((searchCriteria.isNotApplicable()))
						totalNoOfRecordsQueryToAppend.append(" UNION ");

					totalNoOfRecordsQueryToAppend
							.append(advSearchMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend
							.append(conditionalqueryNotFinalised);
					totalNoOfRecordsQueryToAppend.append(varConditionMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionMapped);
					totalNoOfRecordsQueryToAppend.append(EB01Condition);
					totalNoOfRecordsQueryToAppend.append(EB03Condition);
					totalNoOfRecordsQueryToAppend.append(III02Condition);
					totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
					totalNoOfRecordsQueryToAppend.append(messageTextCondition);
				}
			}

		}

		// Neither of the Check Boxes is Checked
		if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isNotFinalized()) {
			log.info("Variable Criteria 9");
			if (searchCriteria.getVariableId() != null) {
				totalNoOfRecordsQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
				totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
				totalNoOfRecordsQueryToAppend
						.append(advSearchMappedVariablesQuery);
				totalNoOfRecordsQueryToAppend.append(varConditionMapped);
				totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
				totalNoOfRecordsQueryToAppend.append(userConditionMapped);
				totalNoOfRecordsQueryToAppend.append(EB01Condition);
				totalNoOfRecordsQueryToAppend.append(EB03Condition);
				totalNoOfRecordsQueryToAppend.append(III02Condition);
				totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
				totalNoOfRecordsQueryToAppend.append(messageTextCondition);
			}
			if (searchCriteria.getVariableId() == null) {
				

				/*-- -  changed for CR  ------*/

				/** If all check boxes are unchecked and user text box alone is entered, 
				 *  then all the unmapped and mapped records belonging to the user should be fetched.
				 *  
				 *  If all check boxes are unchecked and user text box along with any of 
				 *  EB01,03,III02,Note Type or Message Text is entered,then only mapped records should be fetched.  
				 */
				if (null != searchCriteria.getCommaSeperatedUser() && !searchCriteria.getCommaSeperatedUser().equals("")
						&& (null == searchCriteria.getEB01() || searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() || searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {
					
					//totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
					totalNoOfRecordsQueryToAppend
							.append(advSearchUnMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
				}
				/** Checks for all check boxes unchecked and all text boxes null except contract search criteria
				 * 	Then all the variables corresponding to the contract irrespective of mapped/unmapped/NA/NF is fetched.
				 */
				if(null != searchCriteria.getContractId() && !searchCriteria.getContractId().equals("")){
					if ( (null == searchCriteria.getCommaSeperatedUser()  || searchCriteria.getCommaSeperatedUser().equals(""))
						&& (null == searchCriteria.getEB01() ||  searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() ||  searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {
					
					//totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
					totalNoOfRecordsQueryToAppend.append(advSearchUnMappedVariablesQuery);
					totalNoOfRecordsQueryToAppend.append(varConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(varDescConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(userConditionUnMapped);
					totalNoOfRecordsQueryToAppend.append(" UNION ALL ");
					}
				}
				
				totalNoOfRecordsQueryToAppend.append(advSearchMappedVariablesQuery);
				totalNoOfRecordsQueryToAppend.append(varConditionMapped);
				totalNoOfRecordsQueryToAppend.append(varDescConditionMapped);
				totalNoOfRecordsQueryToAppend.append(userConditionMapped);
				totalNoOfRecordsQueryToAppend.append(EB01Condition);
				totalNoOfRecordsQueryToAppend.append(EB03Condition);
				totalNoOfRecordsQueryToAppend.append(III02Condition);
				totalNoOfRecordsQueryToAppend.append(noteTypeCondition);
				totalNoOfRecordsQueryToAppend.append(messageTextCondition);
			}
		}

		advSearchTotalNoOfRecordsQuery.append(count);
		advSearchTotalNoOfRecordsQuery.append(totalNoOfRecordsQueryToAppend);
		advSearchTotalNoOfRecordsQuery.append(" ) ");

		FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
				dataSource, advSearchTotalNoOfRecordsQuery.toString());

		List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
		Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
		
		log.info("QUERY to get total number of records \n" +ESAPI.encoder().encodeForHTML(
		  advSearchTotalNoOfRecordsQuery.toString()));
		log.info("SIZE of COUNT Query >>>" + totalNoOfRecords.intValue());
		 
		setDataCount(totalNoOfRecords.intValue());
		return totalNoOfRecords.intValue();
	}
	

	/**
	 * method gets the records based on the search criteria
	 * 
	 * @param SearchCriteria
	 *            ,totalNoOfRecords, Page
	 *@return advanceSearchResultList
	 */

	public List getAdvanceRecords(SearchCriteria searchCriteria,
			int noOfRecords, Page page) {

		StringBuffer advSearchUnMappedVariablesQuery = new StringBuffer();
		StringBuffer advSearchMappedVariablesQuery = new StringBuffer();
		StringBuffer advSearchVariablesQueryToAppend = new StringBuffer();
		StringBuffer selectQueryForAdvanceSearch = new StringBuffer();
		List advanceSearchResultList = new ArrayList();
		String varConditionUnMapped = "";
		String varDescConditionUnMapped = "";
		String userConditionUnMapped = "";
		String varConditionMapped = "";
		String varDescConditionMapped = "";
		String userConditionMapped = "";
		String majorHeadingConditionMapped = "";
		String minorHeadingConditionMapped = "";
		String EB01Condition = "";
		String EB03Condition = "";
		String III02Condition = "";
		String noteTypeCondition = "";
		String messageTextCondition = "";
		String conditionalqueryNA = "";
		String conditionalqueryNotFinalised = "";
		boolean containsEb03Comma = false;
		
		String contractAdvanceSearchUnmappedVarsegCondn1 = "";
		String contractAdvanceSearchUnmappedVarsegCondn2 = "";

		String contractAdvanceSearchMappedVarsegForMainCondn1 = "";
		String contractAdvanceSearchMappedVarsegForMainCondn2 = "";
		
		String contractAdvanceSearchMappedVarsegForTempCondn1 = "";
		String contractAdvanceSearchMappedVarsegForTempCondn2 = "";
		
		String revisionDateForISGContract = "";
		
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			String[] ebo3Array = searchCriteria.getEB03().split(",");
			if(ebo3Array.length >1){
				containsEb03Comma = true;
			}
			
		}
		String pagination = " where pagination.rnm BETWEEN "
				+ page.getStartRowNum() + " AND " + page.getEndRowNum() + " ";
		// String orderBy = " order by create_date desc";
		String selectLock = "select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( ";
		String lockCondition = " )vareBX left outer join bolk_bus_obj_lock lk "
				+ " on vareBX.VAR = lk.bolk_bus_obj_key_id "
				+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping' ";

		// UnMapped Input Check

		// Variable Input Check
		if (null != searchCriteria.getVariableId()){
			varConditionUnMapped = "  and UNMAPPED.contVar = '"
					+ searchCriteria.getVariableId() + "' ";
		}
		
		// Variable Description Input Check
		if (null != searchCriteria.getVariableDescription() 
				&& !searchCriteria.getVariableDescription().equals("")){
			varDescConditionUnMapped = "  and upper(UNMAPPED.Variable_Desc) like upper('%"
					+ searchCriteria.getVariableDescription() + "%')";
		}
		// Mapped/NA/NF Input Check

		// Not Applicable Conditions
		if ((searchCriteria.isMapped()) && !(searchCriteria.isNotApplicable())){
			conditionalqueryNA = " and VAR.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
		}
		if ((searchCriteria.isNotApplicable()) && !(searchCriteria.isMapped())){
			conditionalqueryNA = " and VAR.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE' ";
		}
		if ((searchCriteria.isMapped()) && (searchCriteria.isNotApplicable())){
			conditionalqueryNA = "";
		}
		// Not Finalised Condition
		if (searchCriteria.isNotFinalized()){
			conditionalqueryNotFinalised = " and VAR.MAPPNG_CMP_IND = 'N' ";
		}
		// Variable Input Check
		if (null != searchCriteria.getVariableId()
				&& !searchCriteria.getVariableId().equals("")){
			varConditionMapped = "  and var.VARIABLE = '"
					+ searchCriteria.getVariableId() + "' ";
		}
		//Variable Description Check.
		if (null != searchCriteria.getVariableDescription()
				&& !searchCriteria.getVariableDescription().equals("")){
			varDescConditionMapped = "  and upper(VAR.VARIABLE_DESCRIPTION) like upper('%"
					+ searchCriteria.getVariableDescription() + "%') ";
		}
		
		/*//Major heading Check
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			majorHeadingConditionMapped =" AND upper(var.MAJOR) like upper('%" +
					searchCriteria.getMajorHeading()+
							"%')";
		}
		//Minor Heading Check
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			minorHeadingConditionMapped =" AND upper(var.MINOR) like upper('%" +
			searchCriteria.getMinorHeading()+
					"%')";
		}*/
		
		// User Input Check
		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals("")){
			userConditionMapped = " and UPPER(VAR.USR) IN( "
					+ searchCriteria.getCommaSeperatedUser() + ")";
		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals("")){
			userConditionUnMapped = " and UPPER(USR) IN( "
					+ searchCriteria.getCommaSeperatedUser() + ")";
		}
		// EB01 Input Check
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")){
			EB01Condition = " and var.EB01 = '" + searchCriteria.getEB01()
					+ "' ";
		}
		// EB03 Input Check
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")){
			if(containsEb03Comma){
				EB03Condition = " and VAR.EB03 IS NOT NULL";
			}else{
			EB03Condition = " and VAR.EB03 LIKE '%, "
					+ searchCriteria.getEB03() + " ,%'";
			}
		}
		// + " ,%' or var.EB03 like '%, " + searchCriteria.getEB03()
		// + " ,%' or var.EB03 like '%, " + searchCriteria.getEB03()
		// + "' OR VAR.EB03='"+searchCriteria.getEB03()+"' )";

		// III02 Input Check
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			III02Condition = " and var.III02 LIKE '%, " + searchCriteria.getIII02()
					+ " ,%'";
		}
		// Note Type Input Check
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			noteTypeCondition = " and var.note_type_code LIKE '%, "
					+ searchCriteria.getNoteType() + " ,%'";
		}
		// Message Text Input Check
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			messageTextCondition = " and upper(var.message) like upper('%"
					+ searchCriteria.getMessageText() + "%')";
		}
		
		// Contract Advance Search Conditions
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			messageTextCondition = " and upper(var.message) like upper('%"
					+ searchCriteria.getMessageText() + "%')";
		}
		
		if(null != searchCriteria.getContractId()){
			if(DomainConstants.CONTRACT_SYSTEM_LG.equals(searchCriteria.getContractSystem()) ){
			
			
			contractAdvanceSearchUnmappedVarsegCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchUnmappedVarsegCondn2 = " and unmapped.contVar = varseg.contract_var    "
														+" AND varseg.contract='"
														+ searchCriteria.getContractId().toUpperCase().trim()
														+ "' "
														+" AND varseg.start_dt= to_date('"
														+searchCriteria.getContractStartDate()
														+"' ,'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForMainCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+"' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+"' , 'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForTempCondn1 =  " , VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForTempCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+ "' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+ "' , 'mm/dd/yyyy') ";
			
			}else if(DomainConstants.CONTRACT_SYSTEM_ISG.equals(searchCriteria.getContractSystem()) ){
				
				//Added Revision Date Search Criteria for Jan 2012 Release CR
				if(null != searchCriteria.getISGContractRevisionDate()){
					revisionDateForISGContract = " and cpfxp_r_date = to_date('"
											   + searchCriteria.getISGContractRevisionDate()
											   + "' ,'mm/dd/yyyy') " ;
				}
				
				contractAdvanceSearchUnmappedVarsegCondn1 =    	 ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
				 												 + " WHERE cpfxp_cont_id='"
				 												 + searchCriteria.getContractId().toUpperCase().trim()
				 												 + "' "
				 												 + "and cpfxp_s_date = to_date('"
				 												 + searchCriteria.getContractStartDate()
				 												 + "' ,'mm/dd/yyyy') " 
				 												 + revisionDateForISGContract
				 												 + " )  VARSEG ";
				
				contractAdvanceSearchUnmappedVarsegCondn2 = 	 " and unmapped.contVar = varseg.cpfxp_contvar   ";
															
				
				contractAdvanceSearchMappedVarsegForMainCondn1 = ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																 + " WHERE cpfxp_cont_id='"
																 + searchCriteria.getContractId().toUpperCase().trim()
																 + "' "
																 + "and cpfxp_s_date = to_date('"
																 + searchCriteria.getContractStartDate()
																 + "' ,'mm/dd/yyyy') " 
																  + revisionDateForISGContract
																 + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				
				contractAdvanceSearchMappedVarsegForTempCondn1 =  ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																  + " WHERE cpfxp_cont_id='"
																  + searchCriteria.getContractId().toUpperCase().trim()
																  + "' "
																  + "and cpfxp_s_date = to_date('"
																  + searchCriteria.getContractStartDate()
																  + "' ,'mm/dd/yyyy') " 
																  + revisionDateForISGContract
																  + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForTempCondn2 	= " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				}
		}

		// UnMapped Variable Query
		advSearchUnMappedVariablesQuery
				.append(" SELECT  distinct(UNMAPPED.contVar) VAR, UNMAPPED.Variable_Desc VAR_DESCRIPTION , ");
		// advSearchUnMappedVariablesQuery.append(" UNMAPPED.PROVIDER_ARRANGEMENT PROVIDER_ARRANGEMENT , ");
		advSearchUnMappedVariablesQuery.append(" UNMAPPED.VAR_FRM DATA_TYPE, ");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.Legacy_System SYSTEM , ");
		advSearchUnMappedVariablesQuery.append(" '' EB01 , '' EB03 , ");
		// advSearchUnMappedVariablesQuery.append("'' ACCUMULATOR , " );
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' MESSAGE ,");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			advSearchUnMappedVariablesQuery.append(" '' NOTE_TYPE_CODE ,");
		}
		advSearchUnMappedVariablesQuery.append(" USR USR , ");
		// advSearchUnMappedVariablesQuery.append("UNMAPPED.lst_chngd_tms LST_CHG_TMS, "
		// );
		advSearchUnMappedVariablesQuery.append(" UNMAPPED.create_date ,");
		advSearchUnMappedVariablesQuery.append(" 'UNMAPPED' Status, ");
		// advSearchUnMappedVariablesQuery.append(" '' MSG_RQRD_INDCTR , '' ACCUM_NOT_RQRD_INDCTR, ");
		advSearchUnMappedVariablesQuery.append("'' NOT_COMPLTE_FLAG, ");
		advSearchUnMappedVariablesQuery.append("'' AUDIT_LOCK ");
		
		/*if(null != searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchUnMappedVariablesQuery.append(" ,hdng.MAJOR  ");
		}
		if(null != searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchUnMappedVariablesQuery.append(" ,hdng.MINOR  ");
		}*/
		
		advSearchUnMappedVariablesQuery.append(" FROM ");
		advSearchUnMappedVariablesQuery.append(" (SELECT *  ");
		advSearchUnMappedVariablesQuery.append(" FROM   ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar ,   ");
		// advSearchUnMappedVariablesQuery.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_PROV_ARNG, LG.PROV_ARNG) PROVIDER_ARRANGEMENT ,  ");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_LST_CHNGD_USR_ID, LG.LAST_UPDATED_USERID) USR ,   ");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_tms, LG.LAST_UPDATED_DT) lst_chngd_tms ,  ");
		advSearchUnMappedVariablesQuery.append(" CASE  ");
		advSearchUnMappedVariablesQuery
				.append(" WHEN LG.creation_dt IS NULL THEN ISG.cpfxp_create_date  ");
		advSearchUnMappedVariablesQuery
				.append(" WHEN ISG.cpfxp_create_date IS NULL THEN LG.creation_dt   ");
		advSearchUnMappedVariablesQuery
				.append(" WHEN LG.creation_dt > ISG.cpfxp_create_date THEN ISG.cpfxp_create_date  ");
		advSearchUnMappedVariablesQuery.append("  ELSE LG.creation_dt  ");
		advSearchUnMappedVariablesQuery.append(" END create_date ,  ");
		advSearchUnMappedVariablesQuery
				.append("  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , ");
		advSearchUnMappedVariablesQuery
				.append("  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRM , ");
		advSearchUnMappedVariablesQuery.append("  CASE  ");
		advSearchUnMappedVariablesQuery
				.append(" WHEN LG.CONTRACT_VAR IS NULL THEN 'ISG' ");
		advSearchUnMappedVariablesQuery
				.append(" WHEN ISG.cpfxp_contvar IS NULL THEN 'LG' ");
		advSearchUnMappedVariablesQuery.append("  ELSE 'LG, ISG'   ");
		advSearchUnMappedVariablesQuery.append("  END Legacy_System  ");
		advSearchUnMappedVariablesQuery.append("  FROM  CONTVAR LG  ");
		advSearchUnMappedVariablesQuery.append("  FULL OUTER JOIN  ");
		advSearchUnMappedVariablesQuery.append(" ISG_CPFXP_CONTVAR ISG    ");
		advSearchUnMappedVariablesQuery
				.append(" ON LG.CONTRACT_VAR = ISG.cpfxp_contvar   ");
		advSearchUnMappedVariablesQuery.append(" ORDER BY 2 DESC, 1  ");
		advSearchUnMappedVariablesQuery.append(" ) LEGACY   ");
		advSearchUnMappedVariablesQuery.append(" INNER JOIN   ");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG) VALID_FRMT  ");
		advSearchUnMappedVariablesQuery
				.append(" ON LEGACY.VAR_FRM     = VALID_FRMT.VAR_FRMT  ");
		advSearchUnMappedVariablesQuery.append(" WHERE NOT EXISTS   ");
		advSearchUnMappedVariablesQuery
				.append(" (select 1 from BX_CNTRCT_VAR_MAPG where CNTRCT_VAR_ID = LEGACY.contVar)    ");
		advSearchUnMappedVariablesQuery
				.append(" ORDER BY CONTVAR ) UNMAPPED   ");
		
		if((null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())) || 
				(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))){
			advSearchUnMappedVariablesQuery.append(" , VAR_HDNG_MV hdng   ");
		}
		
		//Contract Advance Search filter for Unmapped
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn1);
		advSearchUnMappedVariablesQuery.append("       where 1 = 1  ");
		if((null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())) || 
				(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))){
			advSearchUnMappedVariablesQuery.append(" AND hdng.var = UNMAPPED.contVar   ");
		}
		
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn2);

		// Mapped Variable Query
		advSearchMappedVariablesQuery
				.append("SELECT  distinct(VAR.VARIABLE) VAR, VAR.VARIABLE_DESCRIPTION VAR_DESCRIPTION, ");
		// advSearchMappedVariablesQuery.append(" DECODE(E.CONTRACT_VAR, NULL , DT.CPFXP_PROV_ARNG, DTL.PROV_ARNG) PROVIDER_ARRANGEMENT , ");
		advSearchMappedVariablesQuery.append(" NULL DATA_TYPE, ");
		advSearchMappedVariablesQuery
				.append(" CASE WHEN E.CONTRACT_VAR IS NULL THEN 'ISG' WHEN C.cpfxp_contvar IS NULL THEN 'LG' ELSE 'LG, ISG' END SYSTEM, ");
		advSearchMappedVariablesQuery.append(" VAR.EB01 , VAR.EB03 , ");
		// advSearchMappedVariablesQuery.append(" VAR.ACCUMULATOR, " );
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.MESSAGE, ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			advSearchMappedVariablesQuery.append(" VAR.NOTE_TYPE_CODE, ");
		}
		advSearchMappedVariablesQuery.append(" VAR.USR , ");
		// advSearchMappedVariablesQuery.append(" VAR.LST_CHG_TMS , " );
		advSearchMappedVariablesQuery
				.append(" VAR.CREATD_TM_STMP create_date, ");
		advSearchMappedVariablesQuery.append(" VAR.VAR_MAPG_STTS_CD Status, ");
		// advSearchMappedVariablesQuery.append(" MSG_TYPE MSG_RQRD_INDCTR,
		// SEN_BNFT_IND ACCUM_NOT_RQRD_INDCTR ,
		advSearchMappedVariablesQuery
				.append(" MAPPNG_CMP_IND NOT_COMPLTE_FLAG,AUDIT_LOCK  ");
		
		/*if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,var.MAJOR  ");
		}
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,var.MINOR  ");
		}*/
		
		//eBX Audit Lock added in teh query -- October Release
		//advSearchMappedVariablesQuery
		//.append(" CASE WHEN E.CONTRACT_VAR IS NULL THEN C.AUDIT_LOCK WHEN C.cpfxp_contvar IS NULL THEN E.AUDIT_LOCK ELSE C.AUDIT_LOCK END AUDIT_LOCK ");
		advSearchMappedVariablesQuery.append("	FROM ");
		advSearchMappedVariablesQuery.append("	( ");
		advSearchMappedVariablesQuery
				.append(" SELECT V.CNTRCT_VAR_ID VARIABLE, NVL(V.CNTRCT_VAR_DESC, ' ') VARIABLE_DESCRIPTION,  ");
		advSearchMappedVariablesQuery
				.append(" (SELECT DATA_ELEMENT_VAL FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='EB01' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) EB01, ");
		
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" COMMA_SEPERATED_VAR_EB03STRING(V.VAR_MAPG_SYS_ID,'EB03','N', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								")  EB03, ");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','N') || ' ,' EB03, ");
			}
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','N') || ' ,' EB03, ");
		}
		
		
		// advSearchMappedVariablesQuery.append(" COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'ACCUM','N') ACCUMULATOR, ");
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','N') || ' ,' III02, ");
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','N') || ' ,' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			//advSearchMappedVariablesQuery.append("	V.MSG MESSAGE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'MSG','N') || ' ,' MESSAGE, ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			//advSearchMappedVariablesQuery
			//		.append(" (SELECT UNIQUE(DATA_ELEMENT_VAL) FROM BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='NOTE_TYPE_CODE' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) NOTE_TYPE_CODE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','N') || ' ,' NOTE_TYPE_CODE, ");
		}
		advSearchMappedVariablesQuery.append(" V.LST_CHG_USR USR, ");
		// advSearchMappedVariablesQuery.append(" V.LST_CHG_TMS , " );
		advSearchMappedVariablesQuery.append(" V.CREATD_TM_STMP, ");
		advSearchMappedVariablesQuery.append(" V.VAR_MAPG_STTS_CD, ");
		// advSearchMappedVariablesQuery.append(" V.MSG_TYPE, V.SEN_BNFT_IND, "
		// );
		advSearchMappedVariablesQuery.append(" V.MAPPNG_CMP_IND,V.AUDIT_LOCK ");
		
		/*if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,hdng.MAJOR major ");
		}
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,hdng.MINOR minor ");
		}	*/	
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG V ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" , var_hdng_mv hdng ");
		}
		
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn1);
		
		advSearchMappedVariablesQuery
				.append(" WHERE 1=1 AND V.IN_TEMP_TAB = 'N'  ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.var = V.CNTRCT_VAR_ID ");
		}
		
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn2);

		advSearchMappedVariablesQuery.append("	UNION ALL ");

		advSearchMappedVariablesQuery
				.append(" SELECT V.CNTRCT_VAR_ID VARIABLE, V.CNTRCT_VAR_DESC VARIABLE_DESCRIPTION, ");
		advSearchMappedVariablesQuery
				.append(" (SELECT DATA_ELEMENT_VAL FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='EB01' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) EB01, ");
		
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" COMMA_SEPERATED_VAR_EB03STRING(V.VAR_MAPG_SYS_ID,'EB03','Y', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								")  EB03, ");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','Y') || ' ,' EB03, ");
			}
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'EB03','Y') || ' ,' EB03, ");
		}
		
		// advSearchMappedVariablesQuery.append(" COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'ACCUM','Y') ACCUMULATOR, ");
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
			
				advSearchMappedVariablesQuery
						.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','Y') || ' ,' III02, ");			
		} else {
			advSearchMappedVariablesQuery
					.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'III02','Y') || ' ,' III02, ");
		}
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")){
			//advSearchMappedVariablesQuery.append(" V.MSG MESSAGE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'MSG','Y') || ' ,' MESSAGE, ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")){
			//advSearchMappedVariablesQuery
			//		.append(" (SELECT UNIQUE(DATA_ELEMENT_VAL) FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE DATA_ELEMENT_ID='NOTE_TYPE_CODE' and  VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID) NOTE_TYPE_CODE, ");
			advSearchMappedVariablesQuery
			.append(" ', ' || COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','Y') || ' ,' NOTE_TYPE_CODE, ");
		}
		advSearchMappedVariablesQuery.append(" V.LST_CHG_USR USR, ");
		// advSearchMappedVariablesQuery.append(" V.LST_CHG_TMS, " );
		advSearchMappedVariablesQuery
				.append(" V.CREATD_TM_STMP, V.VAR_MAPG_STTS_CD, ");
		// advSearchMappedVariablesQuery.append("V.MSG_TYPE, V.SEN_BNFT_IND, ");
		advSearchMappedVariablesQuery.append("V.MAPPNG_CMP_IND,V.AUDIT_LOCK ");
		
		/*if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,hdng.MAJOR major ");
		}
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery
			.append(" ,hdng.MINOR minor ");
		}*/	
		
		advSearchMappedVariablesQuery
				.append("	FROM TEMP_BX_CNTRCT_VAR_MAPG V, BX_CNTRCT_VAR_MAPG MV ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" , var_hdng_mv hdng ");
		}
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn1);
		
		advSearchMappedVariablesQuery
				.append("	WHERE 1=1 AND MV.IN_TEMP_TAB = 'Y' AND MV.CNTRCT_VAR_ID = V.CNTRCT_VAR_ID  ");
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.var = V.CNTRCT_VAR_ID ");
		}
		//Major heading Check
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		//Minor Heading Check
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn2);
		
		//advSearchMappedVariablesQuery.append(" ORDER BY VARIABLE ");

		advSearchMappedVariablesQuery
				.append("	) VAR, CONTVAR E, ISG_CPFXP_CONTVAR C ");

		advSearchMappedVariablesQuery.append("	WHERE ");
		advSearchMappedVariablesQuery
				.append("	VAR.VARIABLE = E.CONTRACT_VAR (+) AND ");
		advSearchMappedVariablesQuery
				.append("	VAR.VARIABLE = c.cpfxp_contvar (+) ");
		// advSearchMappedVariablesQuery.append("	VAR.VARIABLE = DTL.CONTRACT_VAR (+)  ");

		// Combinations of Various Input Check Box Conditions

		// When unmapped alone is checked
		if (!searchCriteria.isMapped() && !searchCriteria.isNotApplicable()
				&& !searchCriteria.isNotFinalized()) {
			if (searchCriteria.isUnMapped()) {
				advSearchVariablesQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				advSearchVariablesQueryToAppend.append(varConditionUnMapped);
				advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
				advSearchVariablesQueryToAppend.append(userConditionUnMapped);
			}
		}
		// UnMapped along with NA/NF checked
		if ((searchCriteria.isUnMapped()) && !(searchCriteria.isMapped())) {
			// If NA or NF is also checked
			if (searchCriteria.isNotApplicable()
					|| searchCriteria.isNotFinalized()) {
				advSearchVariablesQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				advSearchVariablesQueryToAppend.append(varConditionUnMapped);
				advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
				advSearchVariablesQueryToAppend.append(userConditionUnMapped);
				advSearchVariablesQueryToAppend.append(" UNION ALL ");
				advSearchVariablesQueryToAppend.append("SELECT * FROM (");
				if (searchCriteria.isNotApplicable()) {					
					advSearchVariablesQueryToAppend
							.append(advSearchMappedVariablesQuery);
					advSearchVariablesQueryToAppend.append(conditionalqueryNA);
					advSearchVariablesQueryToAppend.append(varConditionMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionMapped);
					advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(userConditionMapped);
					advSearchVariablesQueryToAppend.append(EB01Condition);
					advSearchVariablesQueryToAppend.append(EB03Condition);
					advSearchVariablesQueryToAppend.append(III02Condition);
					advSearchVariablesQueryToAppend.append(noteTypeCondition);
					advSearchVariablesQueryToAppend
							.append(messageTextCondition);
				}
				if (searchCriteria.isNotFinalized()) {
					if (searchCriteria.isNotApplicable()) {
						advSearchVariablesQueryToAppend.append(" UNION ");
					}
					advSearchVariablesQueryToAppend
							.append(advSearchMappedVariablesQuery);
					advSearchVariablesQueryToAppend
							.append(conditionalqueryNotFinalised);
					advSearchVariablesQueryToAppend.append(varConditionMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionMapped);
					advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(userConditionMapped);
					advSearchVariablesQueryToAppend.append(EB01Condition);
					advSearchVariablesQueryToAppend.append(EB03Condition);
					advSearchVariablesQueryToAppend.append(III02Condition);
					advSearchVariablesQueryToAppend.append(noteTypeCondition);
					advSearchVariablesQueryToAppend
							.append(messageTextCondition);
				}
				advSearchVariablesQueryToAppend.append(" ORDER BY VAR )");
			}

		}

		// Unmapped,Mapped along with NA/NF checked

		if ((searchCriteria.isUnMapped()) && (searchCriteria.isMapped())) {
			advSearchVariablesQueryToAppend
					.append(advSearchUnMappedVariablesQuery);
			advSearchVariablesQueryToAppend.append(varConditionUnMapped);
			advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
			advSearchVariablesQueryToAppend.append(userConditionUnMapped);
			advSearchVariablesQueryToAppend.append(" UNION ALL ");
			advSearchVariablesQueryToAppend.append("SELECT * FROM (");
			advSearchVariablesQueryToAppend
					.append(advSearchMappedVariablesQuery);
			advSearchVariablesQueryToAppend.append(conditionalqueryNA);
			advSearchVariablesQueryToAppend.append(varConditionMapped);
			advSearchVariablesQueryToAppend.append(varDescConditionMapped);
			advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
			advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
			advSearchVariablesQueryToAppend.append(userConditionMapped);
			advSearchVariablesQueryToAppend.append(EB01Condition);
			advSearchVariablesQueryToAppend.append(EB03Condition);
			advSearchVariablesQueryToAppend.append(III02Condition);
			advSearchVariablesQueryToAppend.append(noteTypeCondition);
			advSearchVariablesQueryToAppend.append(messageTextCondition);
			advSearchVariablesQueryToAppend.append(" ORDER BY VAR )");
			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * advSearchVariablesQueryToAppend.append(" UNION ");
			 * advSearchVariablesQueryToAppend
			 * .append(advSearchMappedVariablesQuery);
			 * advSearchVariablesQueryToAppend
			 * .append(conditionalqueryNotFinalised);
			 * advSearchVariablesQueryToAppend.append(varConditionMapped);
			 * advSearchVariablesQueryToAppend.append(userConditionMapped);
			 * advSearchVariablesQueryToAppend.append(EB01Condition);
			 * advSearchVariablesQueryToAppend.append(EB03Condition);
			 * advSearchVariablesQueryToAppend.append(III02Condition);
			 * advSearchVariablesQueryToAppend.append(noteTypeCondition);
			 * advSearchVariablesQueryToAppend.append(messageTextCondition); }
			 */
		}

		// Mapped along with NA/NF checked
		// If NA/NF alone is checked, then also same condition is used
		if (!searchCriteria.isUnMapped()) {

			if ((searchCriteria.isMapped())
					|| (searchCriteria.isNotApplicable())
					|| (searchCriteria.isNotFinalized())) {
				advSearchVariablesQueryToAppend.append("SELECT * FROM (");
				if (searchCriteria.isMapped()
						|| searchCriteria.isNotApplicable()) {
					advSearchVariablesQueryToAppend
							.append(advSearchMappedVariablesQuery);
					advSearchVariablesQueryToAppend.append(conditionalqueryNA);
					advSearchVariablesQueryToAppend.append(varConditionMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionMapped);
					advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(userConditionMapped);
					advSearchVariablesQueryToAppend.append(EB01Condition);
					advSearchVariablesQueryToAppend.append(EB03Condition);
					advSearchVariablesQueryToAppend.append(III02Condition);
					advSearchVariablesQueryToAppend.append(noteTypeCondition);
					advSearchVariablesQueryToAppend
							.append(messageTextCondition);
				}
				if (!searchCriteria.isMapped()
						&& searchCriteria.isNotFinalized()) {
					if (searchCriteria.isNotApplicable())
						advSearchVariablesQueryToAppend.append(" UNION ");

					advSearchVariablesQueryToAppend
							.append(advSearchMappedVariablesQuery);
					advSearchVariablesQueryToAppend
							.append(conditionalqueryNotFinalised);
					advSearchVariablesQueryToAppend.append(varConditionMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionMapped);
					advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
					advSearchVariablesQueryToAppend.append(userConditionMapped);
					advSearchVariablesQueryToAppend.append(EB01Condition);
					advSearchVariablesQueryToAppend.append(EB03Condition);
					advSearchVariablesQueryToAppend.append(III02Condition);
					advSearchVariablesQueryToAppend.append(noteTypeCondition);
					advSearchVariablesQueryToAppend
							.append(messageTextCondition);
				}
				advSearchVariablesQueryToAppend.append(" ORDER BY VAR )");
			}

		}

		// Neither of the Check Boxes is Checked
		if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isNotFinalized()) {
			if (searchCriteria.getVariableId() != null) {
				advSearchVariablesQueryToAppend
						.append(advSearchUnMappedVariablesQuery);
				advSearchVariablesQueryToAppend.append(varConditionUnMapped);
				advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
				advSearchVariablesQueryToAppend.append(userConditionUnMapped);
				advSearchVariablesQueryToAppend.append(" UNION ALL ");
				advSearchVariablesQueryToAppend.append("SELECT * FROM (");
				advSearchVariablesQueryToAppend
						.append(advSearchMappedVariablesQuery);
				advSearchVariablesQueryToAppend.append(varConditionMapped);
				advSearchVariablesQueryToAppend.append(varDescConditionMapped);
				advSearchVariablesQueryToAppend.append(majorHeadingConditionMapped);
				advSearchVariablesQueryToAppend.append(minorHeadingConditionMapped);
				advSearchVariablesQueryToAppend.append(userConditionMapped);
				advSearchVariablesQueryToAppend.append(EB01Condition);
				advSearchVariablesQueryToAppend.append(EB03Condition);
				advSearchVariablesQueryToAppend.append(III02Condition);
				advSearchVariablesQueryToAppend.append(noteTypeCondition);
				advSearchVariablesQueryToAppend.append(messageTextCondition);
				advSearchVariablesQueryToAppend.append(" ORDER BY VAR )");
			}
			if (searchCriteria.getVariableId() == null) {
				

				/*-- -  changed for CR  ------*/
				/** If all check boxes are unchecked and user text box alone is entered, 
				 *  then all the unmapped and mapped records belonging to the user should be fetched.
				 *  
				 *  If all check boxes are unchecked and user text box along with any of 
				 *  EB01,03,III02,Note Type or Message Text is entered,then only mapped records should be fetched.  
				 */
				if (null != searchCriteria.getCommaSeperatedUser() && !searchCriteria.getCommaSeperatedUser().equals("")
						&& (null == searchCriteria.getEB01() || searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() || searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {
					
					//advSearchVariablesQueryToAppend.append(" UNION ALL ");
					advSearchVariablesQueryToAppend.append(advSearchUnMappedVariablesQuery);
					advSearchVariablesQueryToAppend.append(varConditionUnMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
					advSearchVariablesQueryToAppend.append(userConditionUnMapped);
					advSearchVariablesQueryToAppend.append(" UNION ALL ");
				}
				/** Checks for all check boxes unchecked and all text boxes null except contract search criteria
				 * 	Then all the variables corresponding to the contract irrespective of mapped/unmapped/NA/NF is fetched.
				 */
				if(null != searchCriteria.getContractId() && !searchCriteria.getContractId().equals("")){
					if ( (null == searchCriteria.getCommaSeperatedUser()  || searchCriteria.getCommaSeperatedUser().equals(""))
						&& (null == searchCriteria.getEB01() ||  searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() ||  searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {

					//advSearchVariablesQueryToAppend.append(" UNION ALL ");
					advSearchVariablesQueryToAppend.append(advSearchUnMappedVariablesQuery);
					advSearchVariablesQueryToAppend.append(varConditionUnMapped);
					advSearchVariablesQueryToAppend.append(varDescConditionUnMapped);
					advSearchVariablesQueryToAppend.append(userConditionUnMapped);
					advSearchVariablesQueryToAppend.append(" UNION ALL ");
					}
				}
				advSearchVariablesQueryToAppend.append("SELECT * FROM (");
				advSearchVariablesQueryToAppend.append(advSearchMappedVariablesQuery);
				advSearchVariablesQueryToAppend.append(varConditionMapped);
				advSearchVariablesQueryToAppend.append(varDescConditionMapped);
				advSearchVariablesQueryToAppend.append(userConditionMapped);
				advSearchVariablesQueryToAppend.append(EB01Condition);
				advSearchVariablesQueryToAppend.append(EB03Condition);
				advSearchVariablesQueryToAppend.append(III02Condition);
				advSearchVariablesQueryToAppend.append(noteTypeCondition);
				advSearchVariablesQueryToAppend.append(messageTextCondition);
				advSearchVariablesQueryToAppend.append(" ORDER BY VAR )");
			}
		}

		selectQueryForAdvanceSearch.append(selectLock);
		selectQueryForAdvanceSearch.append("select pagination.*" + " FROM ( ");
		selectQueryForAdvanceSearch
				.append("select  ROWNUM rnm , advSearch.* FROM (  ");
		selectQueryForAdvanceSearch.append(advSearchVariablesQueryToAppend);
		selectQueryForAdvanceSearch.append(" )advSearch   ");
		selectQueryForAdvanceSearch.append(" ) pagination");
		selectQueryForAdvanceSearch.append(pagination);
		selectQueryForAdvanceSearch.append(lockCondition);

		GetAdvanceSearchRecordsVarQry advSearchVarQry = new GetAdvanceSearchRecordsVarQry(
				dataSource, selectQueryForAdvanceSearch.toString(),
				searchCriteria);
		log.info("QUERY to get the records: "
				+ESAPI.encoder().encodeForHTML(selectQueryForAdvanceSearch.toString()));

		advanceSearchResultList = advSearchVarQry.execute();
		return advanceSearchResultList;

	}

	/**
	 * Method for getting records based on the search criteria for excel
	 * generation.
	 * 
	 * @param searchCriteria
	 * @return List
	 */
	/* Modified existing query to add PROCEDURE EXCLUSIVE INDICATOR AS PART OF June Release*/
	
	public List getRecordsForReport(SearchCriteria searchCriteria) throws SQLException {
		int count = 0;
		String criteria;
		
		criteria="{";
		criteria = criteria + "\"Category\":" + "\"";
		
		if(searchCriteria.isMapped()==true)
		{
			criteria=criteria+"Mapped,";
		}
		if(searchCriteria.isUnMapped()==true)
		{
			criteria=criteria+"UnMapped,";
		}
		if(searchCriteria.isNotApplicable()==true)
		{
			criteria=criteria+"Not Applicable,";
		}
		if(searchCriteria.isNotFinalized()==true)
		{
			criteria=criteria+"Not Finalized,";
		}
		criteria = criteria + "\"&&@@";
		
		if(!StringUtils.isEmpty(searchCriteria.getVariableDescription()))
		{
			
			criteria = criteria + "\"Variable Description\":" + "\"";
			criteria=criteria+searchCriteria.getVariableDescription();
			criteria = criteria + "\"&&@@";
			
		}
		if(!StringUtils.isEmpty(searchCriteria.getMajorHeading()))
		{
			criteria = criteria + "\"Major Heading\":" + "\"";
			criteria=criteria+searchCriteria.getMajorHeading();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getMinorHeading()))
		{
			criteria = criteria + "\"Minor Heading\":" + "\"";
			criteria=criteria+searchCriteria.getMinorHeading();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getEB01()))
		{
			criteria = criteria + "\"EB01\":" + "\"";
			criteria=criteria+searchCriteria.getEB01();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getEB03()))
		{
			criteria = criteria + "\"EB03\":" + "\"";
			criteria=criteria+searchCriteria.getEB03();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getIII02()))
		{
			criteria = criteria + "\"III02\":" + "\"";
			criteria=criteria+searchCriteria.getIII02();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getNoteType()))
		{
			criteria = criteria + "\"Note Type\":" + "\"";
			criteria=criteria+searchCriteria.getNoteType();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getMessageText()))
		{
			criteria = criteria + "\"Message Text\":" + "\"";
			criteria=criteria+searchCriteria.getMessageText();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getContractId()))
		{
			criteria = criteria + "\"Contract Id\":" + "\"";
			criteria=criteria+searchCriteria.getContractId();
			criteria = criteria + "\"&&@@";
		}if(!StringUtils.isEmpty(searchCriteria.getContractSystem()))
		{
			criteria = criteria + "\"System\":" + "\"";
			criteria=criteria+searchCriteria.getContractSystem();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getContractStartDate()))
		{
			criteria = criteria + "\"Start Date\":" + "\"";
			criteria=criteria+searchCriteria.getContractStartDate();
			criteria = criteria + "\"&&@@";
		}
		if(!StringUtils.isEmpty(searchCriteria.getUser()))
		{
			criteria = criteria + "\"User \":" + "\"";
			criteria=criteria+searchCriteria.getUser();
			criteria = criteria + "\"&&@@";
		}
		criteria = criteria + "}";
		criteria = criteria.replaceAll(","+"\"","\"");
		criteria = criteria.replaceAll("&&@@}", "}");
		StringBuffer advSearchVariablesMainQueryPart = new StringBuffer();
		List advanceSearchResultList = new ArrayList();
		ReportWrapper finalResult = new ReportWrapper();
		boolean containsEb03Comma = false;
		
		String contractAdvanceSearchUnmappedVarsegCondn1 = "";
		String contractAdvanceSearchUnmappedVarsegCondn2 = "";

		String contractAdvanceSearchMappedVarsegForMainCondn1 = "";
		String contractAdvanceSearchMappedVarsegForMainCondn2 = "";
		
		String contractAdvanceSearchMappedVarsegForTempCondn1 = "";
		String contractAdvanceSearchMappedVarsegForTempCondn2 = "";
		
		String revisionDateForISGContract = "";
		
		
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			String[] ebo3Array = searchCriteria.getEB03().split(",");
			if(ebo3Array.length >1){
				containsEb03Comma = true;
			}
			
		}
		if(null != searchCriteria.getContractId()){
			if(DomainConstants.CONTRACT_SYSTEM_LG.equals(searchCriteria.getContractSystem()) ){
			
			
			contractAdvanceSearchUnmappedVarsegCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchUnmappedVarsegCondn2 = " and unmapped.contVar = varseg.contract_var    "
														+" AND varseg.contract='"
														+ searchCriteria.getContractId().toUpperCase().trim()
														+ "' "
														+" AND varseg.start_dt= to_date('"
														+searchCriteria.getContractStartDate()
														+"' ,'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForMainCondn1 = ", VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+"' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+"' , 'mm/dd/yyyy') ";
			
			contractAdvanceSearchMappedVarsegForTempCondn1 =  " , VARSEG varseg ";
			
			contractAdvanceSearchMappedVarsegForTempCondn2 =  " and v.CNTRCT_VAR_ID = varseg.contract_var  "
															+ " and varseg.contract='"
															+ searchCriteria.getContractId().toUpperCase().trim()
															+ "' "
															+ " AND varseg.start_dt  = to_date('"
															+ searchCriteria.getContractStartDate()
															+ "' , 'mm/dd/yyyy') ";
			
			}else if(DomainConstants.CONTRACT_SYSTEM_ISG.equals(searchCriteria.getContractSystem()) ){
				
				//Added Revision Date Search Criteria for Jan 2012 Release CR
				if(null != searchCriteria.getISGContractRevisionDate()){
					revisionDateForISGContract = " and cpfxp_r_date = to_date('"
											   + searchCriteria.getISGContractRevisionDate()
											   + "' ,'mm/dd/yyyy') " ;
				}
				
				contractAdvanceSearchUnmappedVarsegCondn1 =    	 ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
				 												 + " WHERE cpfxp_cont_id='"
				 												 + searchCriteria.getContractId().toUpperCase().trim()
				 												 + "' "
				 												 + "and cpfxp_s_date = to_date('"
				 												 + searchCriteria.getContractStartDate()
				 												 + "' ,'mm/dd/yyyy') " 
				 												 + revisionDateForISGContract
				 												 + " )  VARSEG ";
				
				contractAdvanceSearchUnmappedVarsegCondn2 = 	 " and unmapped.contVar = varseg.cpfxp_contvar   ";
															
				
				contractAdvanceSearchMappedVarsegForMainCondn1 = ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																 + " WHERE cpfxp_cont_id='"
																 + searchCriteria.getContractId().toUpperCase().trim()
																 + "' "
																 + "and cpfxp_s_date = to_date('"
																 + searchCriteria.getContractStartDate()
																 + "' ,'mm/dd/yyyy') " 
																 + revisionDateForISGContract
																 + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForMainCondn2 =  " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				
				contractAdvanceSearchMappedVarsegForTempCondn1 =  ", (Select distinct cpfxp_contvar from ISG_CPFXP_VARSEG  "
																  + " WHERE cpfxp_cont_id='"
																  + searchCriteria.getContractId().toUpperCase().trim()
																  + "' "
																  + "and cpfxp_s_date = to_date('"
																  + searchCriteria.getContractStartDate()
																  + "' ,'mm/dd/yyyy') " 
																  + revisionDateForISGContract
																  + " )  VARSEG ";
				
				contractAdvanceSearchMappedVarsegForTempCondn2 	= " and v.CNTRCT_VAR_ID = varseg.cpfxp_contvar  ";
				}
		}
		StringBuffer advSearchUnMappedVariablesQuery = new StringBuffer(
				"SELECT * FROM ( SELECT distinct(UNMAPPED.CONTVAR) VAR,");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.VARIABLE_DESC VAR_DESCRIPTION,");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.PROVIDER_ARRANGEMENT PROVIDER_ARRANGEMENT,");
		advSearchUnMappedVariablesQuery.append(" UNMAPPED.VAR_FRM DATA_TYPE,");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.LEGACY_SYSTEM SYSTEM, UNMAPPED.CATAGORY_CODE CATAGORY_CODE, '' EB01,'' EB02,'' EB03,");
		advSearchUnMappedVariablesQuery
				.append(" '' EB06,'' EB09, '' START_AGE, '' END_AGE, '' HSD01,'' HSD02,'' HSD03,'' HSD04,");
		advSearchUnMappedVariablesQuery
				.append(" '' HSD05,'' HSD06,'' HSD07,'' HSD08,'' ACCUMULATOR,");
		advSearchUnMappedVariablesQuery
				.append(" '' III02,'' MESSAGE,'' NOTE_TYPE_CODE, '' UM_RULE,");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.USR,UNMAPPED.LST_CHNGD_TMS LST_CHG_TMS,");
		advSearchUnMappedVariablesQuery
				.append(" UNMAPPED.CREATE_DATE,'UNMAPPED' STATUS,");
		advSearchUnMappedVariablesQuery
				.append(" '' MSG_RQRD_INDCTR,'' PROC_EXCLD_IND,'' ACCUM_NOT_RQRD_INDCTR,");
		advSearchUnMappedVariablesQuery.append(" '' NOT_COMPLTE_FLAG , '' AUDIT_LOCK, '' INDVDL_EB03_ASSN_IND FROM");
		advSearchUnMappedVariablesQuery.append(" (SELECT * FROM");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_CONTVAR, LG.CONTRACT_VAR) CONTVAR,");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_PROV_ARNG, LG.PROV_ARNG) PROVIDER_ARRANGEMENT,");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_LST_CHNGD_USR_ID, LG.LAST_UPDATED_USERID) USR,");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_LST_CHNGD_TMS, LG.LAST_UPDATED_DT) LST_CHNGD_TMS,");
		advSearchUnMappedVariablesQuery.append("  CASE");
		advSearchUnMappedVariablesQuery.append(" WHEN LG.CREATION_DT IS NULL");
		advSearchUnMappedVariablesQuery.append(" THEN ISG.CPFXP_CREATE_DATE");
		advSearchUnMappedVariablesQuery
				.append(" WHEN ISG.CPFXP_CREATE_DATE IS NULL");
		advSearchUnMappedVariablesQuery.append(" THEN LG.CREATION_DT");
		advSearchUnMappedVariablesQuery
				.append(" WHEN LG.CREATION_DT > ISG.CPFXP_CREATE_DATE");
		advSearchUnMappedVariablesQuery.append(" THEN ISG.CPFXP_CREATE_DATE");
		advSearchUnMappedVariablesQuery
				.append(" ELSE LG.CREATION_DT END CREATE_DATE,");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_TEXT, LG.CONT_VAR_TX) VARIABLE_DESC,");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_FORMAT, LG.CONTRACT_VAR_FORMAT) VAR_FRM,");
		advSearchUnMappedVariablesQuery.append(" CASE");
		advSearchUnMappedVariablesQuery
				.append(" WHEN LG.CONTRACT_VAR IS NULL THEN 'ISG'");
		advSearchUnMappedVariablesQuery
				.append(" WHEN ISG.CPFXP_CONTVAR IS NULL THEN 'LG'");
		advSearchUnMappedVariablesQuery
				.append(" ELSE 'LG, ISG'  END LEGACY_SYSTEM, ");
		advSearchUnMappedVariablesQuery
				.append(" DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_catagory, LG.catagory) CATAGORY_CODE   ");
		advSearchUnMappedVariablesQuery
				.append("  FROM CONTVAR LG ");
		advSearchUnMappedVariablesQuery
				.append(" FULL OUTER JOIN ISG_CPFXP_CONTVAR ISG");
		advSearchUnMappedVariablesQuery
				.append(" ON LG.CONTRACT_VAR = ISG.CPFXP_CONTVAR");
		advSearchUnMappedVariablesQuery.append("  ) LEGACY  INNER JOIN");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG");
		advSearchUnMappedVariablesQuery.append(" )VALID_FRMT");
		advSearchUnMappedVariablesQuery
				.append(" ON LEGACY.VAR_FRM = VALID_FRMT.VAR_FRMT WHERE NOT EXISTS");
		advSearchUnMappedVariablesQuery
				.append(" (SELECT 1 FROM BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID = LEGACY.CONTVAR");
		advSearchUnMappedVariablesQuery.append(")) UNMAPPED ");
		if((null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())) || 
				(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))){
			advSearchUnMappedVariablesQuery.append(" , VAR_HDNG_MV hdng   ");
		}
		
		//Contract Advance Search filter for Unmapped
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn1);
		advSearchUnMappedVariablesQuery.append(" WHERE 1=1 ");
		if((null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())) || 
				(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading()))){
			advSearchUnMappedVariablesQuery.append(" AND hdng.var = UNMAPPED.contVar   ");
		}
		
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchUnMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchUnMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchUnMappedVariablesQuery.append("%')");
		}
		//Contract Advance Search filter for Unmapped
		advSearchUnMappedVariablesQuery.append(contractAdvanceSearchUnmappedVarsegCondn2);
		
		StringBuffer usernameCondition = new StringBuffer(" ) UMMAPPEDDETLS");
		usernameCondition
				.append(" LEFT JOIN (SELECT USER_NAME,USER_ID FROM BX_USER_DETAILS) USERDETAILS");
		usernameCondition
				.append(" ON TRIM(UPPER(UMMAPPEDDETLS.USR)) = TRIM(UPPER(USERDETAILS.USER_ID))");
		StringBuffer advSearchMappedVariablesQuery = new StringBuffer(
				"SELECT distinct(VAR.VARIABLE) VAR,");
		advSearchMappedVariablesQuery
				.append(" VAR.VARIABLE_DESCRIPTION VAR_DESCRIPTION,");
		advSearchMappedVariablesQuery
				.append(" DECODE(DTL.CONTRACT_VAR, NULL, DT.CPFXP_PROV_ARNG, DTL.PROV_ARNG) PROVIDER_ARRANGEMENT,");
		advSearchMappedVariablesQuery
				.append(" DECODE(DTL.CONTRACT_VAR, NULL, DT.CPFXP_FORMAT, DTL.CONTRACT_VAR_FORMAT) DATA_TYPE,");
		advSearchMappedVariablesQuery.append(" CASE");
		advSearchMappedVariablesQuery.append(" WHEN DTL.CONTRACT_VAR IS NULL");
		advSearchMappedVariablesQuery.append(" THEN'ISG'");
		advSearchMappedVariablesQuery.append(" WHEN DT.CPFXP_CONTVAR IS NULL");
		advSearchMappedVariablesQuery.append(" THEN 'LG'");
		advSearchMappedVariablesQuery.append(" ELSE 'LG, ISG'");
		advSearchMappedVariablesQuery.append(" END SYSTEM,");
		advSearchMappedVariablesQuery.append(" DECODE(DTL.CONTRACT_VAR, NULL, DT.cpfxp_catagory, DTL.catagory) CATAGORY_CODE, ");
		advSearchMappedVariablesQuery
				.append(" VAR.EB01, VAR.EB02, VAR.EB03, VAR.EB06, VAR.EB09, VAR.START_AGE, VAR.END_AGE, ");
		advSearchMappedVariablesQuery
				.append(" VAR.HSD01, VAR.HSD02, VAR.HSD03, VAR.HSD04, VAR.HSD05,");
		advSearchMappedVariablesQuery
				.append(" VAR.HSD06, VAR.HSD07, VAR.HSD08,");
		advSearchMappedVariablesQuery
				.append(" VAR.ACCUMULATOR, VAR.III02, VAR.MESSAGE,");
		advSearchMappedVariablesQuery
				.append(" VAR.NOTE_TYPE_CODE, VAR.UM_RULE, VAR.USR, VAR.LST_CHG_TMS,");
		advSearchMappedVariablesQuery
				.append(" VAR.CREATD_TM_STMP CREATE_DATE,");
		advSearchMappedVariablesQuery.append(" VAR.VAR_MAPG_STTS_CD STATUS,");
		advSearchMappedVariablesQuery.append(" MSG_TYPE MSG_RQRD_INDCTR,");
		advSearchMappedVariablesQuery.append(" VAR.PROC_EXCLD_IND,");
		advSearchMappedVariablesQuery
				.append(" SEN_BNFT_IND ACCUM_NOT_RQRD_INDCTR,");
		advSearchMappedVariablesQuery
				.append(" MAPPNG_CMP_IND NOT_COMPLTE_FLAG, AUDIT_LOCK, VAR.INDVDL_EB03_ASSN_IND,'' USER_NAME,'' USER_ID ");
		advSearchMappedVariablesQuery
				.append(" FROM (SELECT V.CNTRCT_VAR_ID VARIABLE,");
		advSearchMappedVariablesQuery
				.append(" NVL(V.CNTRCT_VAR_DESC, ' ') VARIABLE_DESCRIPTION,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB01'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB01,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID");
		advSearchMappedVariablesQuery.append(" )EB02,");
		//SSCR 19537 change START
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'III02','N') || ' ,' III02,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'MSG','N') || ' ,' MESSAGE,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','N') || ' ,' NOTE_TYPE_CODE,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'MSG_REQD','N') || ' ,' MSG_TYPE,");
		//SSCR 19537 change END
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" comma_seperated_var_eb03string(V.VAR_MAPG_SYS_ID,'EB03','N', ")
						.append("'" + searchCriteria.getEB03() + "'");
				advSearchMappedVariablesQuery
						.append(" )  EB03, (SELECT DATA_ELEMENT_VAL");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'EB03','N')");
				advSearchMappedVariablesQuery
						.append(" || ' ,' EB03, (SELECT DATA_ELEMENT_VAL");
			}
		} else {
			advSearchMappedVariablesQuery
					.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'EB03','N')");
			advSearchMappedVariablesQuery
					.append(" || ' ,' EB03, (SELECT DATA_ELEMENT_VAL");
		}

		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB06'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB06,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB09'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB09,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'START_AGE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) START_AGE,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'END_AGE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) END_AGE,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD01'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD01,");
		advSearchMappedVariablesQuery.append(" (SELECT LISTAGG(DATA_ELEMENT_VAL, ',') WITHIN GROUP(ORDER BY DATA_ELEMENT_VAL) DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD02,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD03'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD03,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD04'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD04,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD05'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD05,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD06'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD06,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD07'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD07,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD08'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD08,");
		advSearchMappedVariablesQuery
				.append(" COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID, 'ACCUM', 'N') ACCUMULATOR,");
		/*advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'III02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) III02,");
		advSearchMappedVariablesQuery.append(" V.MSG MESSAGE,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'NOTE_TYPE_CODE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID");
		advSearchMappedVariablesQuery.append(" ) NOTE_TYPE_CODE,");*/
		advSearchMappedVariablesQuery.append(" COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'UMRULE','N') UM_RULE, ");
		advSearchMappedVariablesQuery.append(" V.LST_CHG_USR USR,");
		advSearchMappedVariablesQuery.append(" V.LST_CHG_TMS,");
		advSearchMappedVariablesQuery.append(" V.CREATD_TM_STMP,");
		advSearchMappedVariablesQuery.append(" V.VAR_MAPG_STTS_CD,");
		//advSearchMappedVariablesQuery.append(" V.MSG_TYPE,");
		advSearchMappedVariablesQuery.append(" V.SEN_BNFT_IND,");
		advSearchMappedVariablesQuery.append(" V.MAPPNG_CMP_IND,V.PROC_EXCLD_IND AS PROC_EXCLD_IND, V.AUDIT_LOCK,  V.INDVDL_EB03_ASSN_IND ");
		advSearchMappedVariablesQuery.append(" FROM BX_CNTRCT_VAR_MAPG V");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" , var_hdng_mv hdng ");
		}
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn1);
		advSearchMappedVariablesQuery.append(" WHERE V.IN_TEMP_TAB = 'N'");
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.var = V.CNTRCT_VAR_ID ");
		}
		
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForMainCondn2);
		
		advSearchMappedVariablesQuery.append(" UNION ALL");
		advSearchMappedVariablesQuery
				.append(" SELECT V.CNTRCT_VAR_ID VARIABLE,");
		advSearchMappedVariablesQuery
				.append(" V.CNTRCT_VAR_DESC VARIABLE_DESCRIPTION,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB01'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB01,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB02,");
		//SSCR 19537 change START
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'III02','N') || ' ,' III02,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'MSG','N') || ' ,' MESSAGE,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'NOTE_TYPE_CODE','N') || ' ,' NOTE_TYPE_CODE,");
		advSearchMappedVariablesQuery.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'MSG_REQD','N') || ' ,' MSG_TYPE,");
		//SSCR 19537 change END
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			if (containsEb03Comma) {
				advSearchMappedVariablesQuery
						.append(
								" comma_seperated_var_eb03string(V.VAR_MAPG_SYS_ID,'EB03','Y', ")
						.append("'" + searchCriteria.getEB03() + "'");
				advSearchMappedVariablesQuery
						.append(" ) EB03, (SELECT DATA_ELEMENT_VAL");
			} else {
				advSearchMappedVariablesQuery
						.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'EB03','Y')");
				advSearchMappedVariablesQuery
						.append(" || ' ,' EB03, (SELECT DATA_ELEMENT_VAL");
			}

		} else {
			advSearchMappedVariablesQuery
					.append(" ', '|| COMMA_SEPERATED_EB03_ASSN(V.VAR_MAPG_SYS_ID,'EB03','Y')");
			advSearchMappedVariablesQuery
					.append(" || ' ,' EB03, (SELECT DATA_ELEMENT_VAL");
		}
		
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB06'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB06,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'EB09'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) EB09,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'START_AGE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) START_AGE,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery.append(" WHERE DATA_ELEMENT_ID = 'END_AGE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) END_AGE,");
		
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD01'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD01,");
		advSearchMappedVariablesQuery.append(" (SELECT LISTAGG(DATA_ELEMENT_VAL, ',') WITHIN GROUP(ORDER BY DATA_ELEMENT_VAL) DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD02,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD03'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD03,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD04'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD04,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD05'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD05,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD06'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD06,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD07'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD07,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'HSD08'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) HSD08,");
		advSearchMappedVariablesQuery
				.append(" COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID, 'ACCUM', 'Y') ACCUMULATOR,");
		/*advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'III02'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) III02,");
		advSearchMappedVariablesQuery.append(" V.MSG MESSAGE,");
		advSearchMappedVariablesQuery.append(" (SELECT DATA_ELEMENT_VAL");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL");
		advSearchMappedVariablesQuery
				.append(" WHERE DATA_ELEMENT_ID = 'NOTE_TYPE_CODE'");
		advSearchMappedVariablesQuery
				.append(" AND VAR_MAPG_SYS_ID = V.VAR_MAPG_SYS_ID ) NOTE_TYPE_CODE,");*/
		advSearchMappedVariablesQuery.append("  COMMA_SEPERATED_VAR(V.VAR_MAPG_SYS_ID,'UMRULE','Y')   UM_RULE, ");
		
		advSearchMappedVariablesQuery
				.append(" V.LST_CHG_USR USR, V.LST_CHG_TMS, V.CREATD_TM_STMP,");
		advSearchMappedVariablesQuery
				.append(" V.VAR_MAPG_STTS_CD, V.SEN_BNFT_IND,");
		advSearchMappedVariablesQuery.append(" V.MAPPNG_CMP_IND,V.PROC_EXCLD_IND,V.AUDIT_LOCK,  V.INDVDL_EB03_ASSN_IND ");
		advSearchMappedVariablesQuery
				.append(" FROM TEMP_BX_CNTRCT_VAR_MAPG V,");
		advSearchMappedVariablesQuery
				.append(" BX_CNTRCT_VAR_MAPG MV ");
		
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" , var_hdng_mv hdng ");
		}
		
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn1);
		advSearchMappedVariablesQuery.append("WHERE 1 = 1");
		
		advSearchMappedVariablesQuery.append(" AND MV.IN_TEMP_TAB = 'Y'");
		if((null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())) || 
				(null !=searchCriteria.getMinorHeading() && 
						!"".equals(searchCriteria.getMinorHeading()))){
			advSearchMappedVariablesQuery.append(" AND hdng.var = V.CNTRCT_VAR_ID ");
		}
		//Major heading Check
		if(null !=searchCriteria.getMajorHeading() && 
				!"".equals(searchCriteria.getMajorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MAJOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMajorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		//Minor Heading Check
		if(null !=searchCriteria.getMinorHeading() && 
				!"".equals(searchCriteria.getMinorHeading())){
			advSearchMappedVariablesQuery.append(" AND upper(hdng.MINOR) like upper('%");
			advSearchMappedVariablesQuery.append(searchCriteria.getMinorHeading());
			advSearchMappedVariablesQuery.append("%')");
		}
		//Contract Advance Search filter for Mapped
		advSearchMappedVariablesQuery.append(contractAdvanceSearchMappedVarsegForTempCondn2);
		
		advSearchMappedVariablesQuery
				.append(" AND MV.CNTRCT_VAR_ID = V.CNTRCT_VAR_ID) VAR,");
		advSearchMappedVariablesQuery
				.append(" ISG_CPFXP_CONTVAR DT, CONTVAR DTL");
		advSearchMappedVariablesQuery
				.append(" WHERE VAR.VARIABLE = DT.CPFXP_CONTVAR(+)");
		advSearchMappedVariablesQuery
				.append(" AND VAR.VARIABLE = DTL.CONTRACT_VAR(+)");

		
		// Variable Query for fetching major headings
		StringBuffer advSearchVariablesLGHeadingQuery = new StringBuffer(
				"SELECT A.CONTRACT_VAR VAR,");
		advSearchVariablesLGHeadingQuery
				.append(" MAJ.MAJOR_TEXT MAJOR, MON.minor_text MINOR");
		advSearchVariablesLGHeadingQuery.append(" FROM LG_CONTRACT_DETAIL A,");
		advSearchVariablesLGHeadingQuery
				.append(" LG_MAJOR_HEADING MAJ, LG_MINOR_HEADING MON");
		advSearchVariablesLGHeadingQuery
				.append(" WHERE A.BENEFIT_STRUCTURE = MAJ.BENEFIT_STRUCTURE");
		advSearchVariablesLGHeadingQuery
				.append(" AND A.MAJOR_HEADING = MAJ.MAJOR_HEADING");
		advSearchVariablesLGHeadingQuery
				.append(" AND A.MINOR_HEADING = MON.MINOR_HEADING");
		// Variable Query for fetching minor headings
		StringBuffer advSearchVariablesISGHeadingQuery = new StringBuffer(
				" SELECT C.CPFXP_CONTVAR VAR,");
		advSearchVariablesISGHeadingQuery
				.append(" MAJ.CPFXP_PC_TEXT MAJ, MON.CPFXP_PC_TEXT MINOR");
		advSearchVariablesISGHeadingQuery
				.append(" FROM ISG_CPFXP_MAJHDG MAJ, ISG_CPFXP_MINHDG MON,");
		advSearchVariablesISGHeadingQuery
				.append(" ISG_CPFXP_CNTATHDG D, ISG_CPFXP_CONTXREF C");
		advSearchVariablesISGHeadingQuery
				.append(" WHERE MON.CPFXP_PC_MAJHDG = MAJ.CPFXP_MAJHDG");
		advSearchVariablesISGHeadingQuery
				.append(" AND D.CPFXP_PC_HEADING = MON.CPFXP_MINHDG");
		advSearchVariablesISGHeadingQuery
				.append(" AND C.CPFXP_CONTATTR = D.CPFXP_CONTATTR");
		String varConditionUnMapped = "";
		String userConditionUnMapped = "";
		String varConditionMapped = "";
		String varDescConditionMapped = "";
		String varDescConditionUnMapped = "";
		String conditionalqueryNA = "";
		String conditionalqueryHeading = "";
		String userConditionMapped = "";
		String EB01Condition = "";
		String EB03Condition = "";
		String III02Condition = "";
		String noteTypeCondition = "";
		String messageTextCondition = "";
		String conditionalqueryNotFinalised = "";
		String conditionalquery_NA_NotFinalised = " AND (VAR.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE' OR VAR.MAPPNG_CMP_IND = 'N')";
		// Variable Input Check

		if (null != searchCriteria.getVariableId()
				&& !searchCriteria.getVariableId().equals("")) {
			varConditionUnMapped = "  AND UNMAPPED.contVar = '"
					+ searchCriteria.getVariableId() + "' ";
			varConditionMapped = "  AND var.VARIABLE = '"
					+ searchCriteria.getVariableId() + "' ";
			advSearchVariablesLGHeadingQuery.append(" AND A.CONTRACT_VAR = '");
			advSearchVariablesLGHeadingQuery.append(searchCriteria
					.getVariableId());
			advSearchVariablesLGHeadingQuery.append("'");
			advSearchVariablesISGHeadingQuery
					.append(" AND  C.CPFXP_CONTVAR = '");
			advSearchVariablesISGHeadingQuery.append(searchCriteria
					.getVariableId());
			advSearchVariablesISGHeadingQuery.append("'");
		} else {
			if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& !(searchCriteria.isNotApplicable())
					&& !(searchCriteria.isNotFinalized())) {
				conditionalqueryHeading = ") MSTR WHERE MSTR.VAR NOT IN (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG)";
			} else if (!searchCriteria.isUnMapped()
					&& searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()
					&& searchCriteria.isNotFinalized()) {
				conditionalqueryHeading = ") MSTR INNER JOIN (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG) MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID";
			} else if (!searchCriteria.isUnMapped()
					&& !searchCriteria.isMapped()
					&& (searchCriteria.isNotApplicable())
					&& !searchCriteria.isNotFinalized()) {
				conditionalqueryHeading = ") MSTR INNER JOIN (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG "
						+ "WHERE VAR_MAPG_STTS_CD = 'NOT_APPLICABLE') MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID";
			} else if (!searchCriteria.isUnMapped()
					&& !(searchCriteria.isNotApplicable())
					&& (searchCriteria.isMapped() || searchCriteria
							.isNotFinalized())) {
				conditionalqueryHeading = ") MSTR INNER JOIN (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG "
						+ "WHERE VAR_MAPG_STTS_CD != 'NOT_APPLICABLE') MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID";
			}
		}

		// Mapped/NA/NF Input Check

		// Not Applicable Conditions
		if ((searchCriteria.isMapped()) && !(searchCriteria.isNotApplicable())) {
			conditionalqueryNA = " AND VAR.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
		} else if ((searchCriteria.isNotApplicable())
				&& !(searchCriteria.isMapped())) {
			conditionalqueryNA = " AND VAR.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE' ";
		} else if ((searchCriteria.isMapped())
				&& (searchCriteria.isNotApplicable())) {
			conditionalqueryNA = "";
		}

		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals(""))
			userConditionUnMapped = " and UPPER(USR) IN( "
					+ searchCriteria.getCommaSeperatedUser() + ")";

		// Not Finalised Condition
		if (searchCriteria.isNotFinalized()) {
			conditionalqueryNotFinalised = " AND VAR.MAPPNG_CMP_IND = 'N' ";
		}

		// User Input Check
		if (null != searchCriteria.getCommaSeperatedUser()
				&& !searchCriteria.getCommaSeperatedUser().equals("")) {
			userConditionMapped = " AND UPPER(VAR.USR) IN("
					+ searchCriteria.getCommaSeperatedUser() + ")";
		}

		// EB01 Input Check
		if (null != searchCriteria.getEB01()
				&& !searchCriteria.getEB01().equals("")) {
			EB01Condition = " AND VAR.EB01 = '" + searchCriteria.getEB01()
					+ "' ";
		}

		// EB03 Input Check
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			if (containsEb03Comma) {
				EB03Condition = " AND VAR.EB03 IS NOT NULL ";
			} else {
				EB03Condition = " AND VAR.EB03 LIKE '%, "
						+ searchCriteria.getEB03() + " ,%'";
			}
		}

		// III02 Input Check
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
			III02Condition = "AND VAR.III02 LIKE '%" + searchCriteria.getIII02()
					+ "%'";
		}

		// Note Type Input Check
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")) {
			noteTypeCondition = "AND VAR.NOTE_TYPE_CODE = '"
					+ searchCriteria.getNoteType() + "'";
		}

		// Message Text Input Check
		if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")) {
			messageTextCondition = "AND UPPER(VAR.MESSAGE) LIKE UPPER('%"
					+ searchCriteria.getMessageText() + "%')";
		}

		// EB01 Input Check
		if (null != searchCriteria.getVariableDescription()
				&& !searchCriteria.getVariableDescription().equals("")) {
			varDescConditionMapped = " AND upper(VAR.VARIABLE_DESCRIPTION) like upper('%" + searchCriteria.getVariableDescription()
					+ "%') ";
			varDescConditionUnMapped = " AND upper(UNMAPPED.VARIABLE_DESC) like upper('%" + searchCriteria.getVariableDescription()
			+ "%') ";
		}
		/* Combinations of Various Input Check Box Conditions */

		// UnMapped along with NA/NF checked
		if ((searchCriteria.isUnMapped()) && !(searchCriteria.isMapped())) {
			// If NA or NF is also checked
			advSearchVariablesMainQueryPart
					.append(advSearchUnMappedVariablesQuery);
			advSearchVariablesMainQueryPart.append(varConditionUnMapped);
			advSearchVariablesMainQueryPart.append(varDescConditionUnMapped);
			advSearchVariablesMainQueryPart.append(userConditionUnMapped);
			advSearchVariablesMainQueryPart.append(usernameCondition);
			if (searchCriteria.isNotApplicable()
					|| searchCriteria.isNotFinalized()) {
				advSearchVariablesMainQueryPart.append(" UNION ALL ");
				advSearchVariablesMainQueryPart
						.append(advSearchMappedVariablesQuery);
				if (searchCriteria.isNotApplicable()
						&& searchCriteria.isNotFinalized()) {
					advSearchVariablesMainQueryPart
							.append(conditionalquery_NA_NotFinalised);
				} else if (searchCriteria.isNotApplicable()
						&& !searchCriteria.isNotFinalized()) {
					advSearchVariablesMainQueryPart.append(conditionalqueryNA);
				} else if (!searchCriteria.isNotApplicable()
						&& searchCriteria.isNotFinalized()) {
					advSearchVariablesMainQueryPart
							.append(conditionalqueryNotFinalised);
				}
				advSearchVariablesMainQueryPart.append(varConditionMapped);
				advSearchVariablesMainQueryPart.append(varDescConditionMapped);
				advSearchVariablesMainQueryPart.append(userConditionMapped);
				advSearchVariablesMainQueryPart.append(EB01Condition);
				advSearchVariablesMainQueryPart.append(EB03Condition);
				advSearchVariablesMainQueryPart.append(III02Condition);
				advSearchVariablesMainQueryPart.append(noteTypeCondition);
				advSearchVariablesMainQueryPart.append(messageTextCondition);
			}
		}

		// Unmapped,Mapped along with NA/NF checked

		if ((searchCriteria.isUnMapped()) && (searchCriteria.isMapped())) {
			advSearchVariablesMainQueryPart
					.append(advSearchUnMappedVariablesQuery);
			advSearchVariablesMainQueryPart.append(varConditionUnMapped);
			advSearchVariablesMainQueryPart.append(varDescConditionUnMapped);
			advSearchVariablesMainQueryPart.append(userConditionUnMapped);
			advSearchVariablesMainQueryPart.append(usernameCondition);
			advSearchVariablesMainQueryPart.append(" UNION ALL ");
			advSearchVariablesMainQueryPart
					.append(advSearchMappedVariablesQuery);
			advSearchVariablesMainQueryPart.append(conditionalqueryNA);
			advSearchVariablesMainQueryPart.append(varConditionMapped);
			advSearchVariablesMainQueryPart.append(varDescConditionMapped);
			advSearchVariablesMainQueryPart.append(userConditionMapped);
			advSearchVariablesMainQueryPart.append(EB01Condition);
			advSearchVariablesMainQueryPart.append(EB03Condition);
			advSearchVariablesMainQueryPart.append(III02Condition);
			advSearchVariablesMainQueryPart.append(noteTypeCondition);
			advSearchVariablesMainQueryPart.append(messageTextCondition);
		}

//		 Mapped along with NA/NF checked
		// If NA/NF alone is checked, then also same condition is used
		if (!searchCriteria.isUnMapped()) {
 
			if ((searchCriteria.isMapped())
					|| (searchCriteria.isNotApplicable())
					|| (searchCriteria.isNotFinalized())) {
				if (searchCriteria.isMapped()
						|| searchCriteria.isNotApplicable()) {
					advSearchVariablesMainQueryPart
							.append(advSearchMappedVariablesQuery);
					advSearchVariablesMainQueryPart.append(conditionalqueryNA);
					advSearchVariablesMainQueryPart.append(varConditionMapped);
					advSearchVariablesMainQueryPart.append(varDescConditionMapped);
					advSearchVariablesMainQueryPart.append(userConditionMapped);
					advSearchVariablesMainQueryPart.append(EB01Condition);
					advSearchVariablesMainQueryPart.append(EB03Condition);
					advSearchVariablesMainQueryPart.append(III02Condition);
					advSearchVariablesMainQueryPart.append(noteTypeCondition);
					advSearchVariablesMainQueryPart.append(messageTextCondition);

				}
				if (!searchCriteria.isMapped()
						&& searchCriteria.isNotFinalized()) {
					if (searchCriteria.isNotApplicable()) {
						advSearchVariablesMainQueryPart.append(" UNION ");						
					}
					advSearchVariablesMainQueryPart
					.append(advSearchMappedVariablesQuery);
					advSearchVariablesMainQueryPart
							.append(conditionalqueryNotFinalised);
					advSearchVariablesMainQueryPart.append(varConditionMapped);
					advSearchVariablesMainQueryPart.append(varDescConditionMapped);
					advSearchVariablesMainQueryPart.append(userConditionMapped);
					advSearchVariablesMainQueryPart.append(EB01Condition);
					advSearchVariablesMainQueryPart.append(EB03Condition);
					advSearchVariablesMainQueryPart.append(III02Condition);
					advSearchVariablesMainQueryPart.append(noteTypeCondition);
					advSearchVariablesMainQueryPart.append(messageTextCondition);
				}
				
			}

		}

		// Neither of the Check Boxes is Checked
		if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isNotFinalized()) {
			if (searchCriteria.getVariableId() != null) {
				advSearchVariablesMainQueryPart
						.append(advSearchUnMappedVariablesQuery);
				advSearchVariablesMainQueryPart.append(varConditionUnMapped);
				advSearchVariablesMainQueryPart.append(varDescConditionUnMapped);
				advSearchVariablesMainQueryPart.append(userConditionUnMapped);
				advSearchVariablesMainQueryPart.append(usernameCondition);
				advSearchVariablesMainQueryPart.append(" UNION ALL ");
				advSearchVariablesMainQueryPart
						.append(advSearchMappedVariablesQuery);

			} else {
				/** If all check boxes are unchecked and user text box alone is entered, 
				 *  then all the unmapped and mapped records belonging to the user should be fetched.
				 *  
				 *  If all check boxes are unchecked and user text box along with any of 
				 *  EB01,03,III02,Note Type or Message Text is entered,then only mapped records should be fetched.  
				 */
				if (null != searchCriteria.getCommaSeperatedUser() && !searchCriteria.getCommaSeperatedUser().equals("")
						&& (null == searchCriteria.getEB01() || searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() || searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {
					
					advSearchVariablesMainQueryPart.append(advSearchUnMappedVariablesQuery);
					advSearchVariablesMainQueryPart.append(varConditionUnMapped);
					advSearchVariablesMainQueryPart.append(varDescConditionUnMapped);
					advSearchVariablesMainQueryPart.append(userConditionUnMapped);
					advSearchVariablesMainQueryPart.append(usernameCondition);
					advSearchVariablesMainQueryPart.append(" UNION ALL ");
				}
				/** Checks for all check boxes unchecked and all text boxes null except contract search criteria
				 * 	Then all the variables corresponding to the contract irrespective of mapped/unmapped/NA/NF is fetched.
				 */
				if(null != searchCriteria.getContractId() && !searchCriteria.getContractId().equals("")){
					if ( (null == searchCriteria.getCommaSeperatedUser()  || searchCriteria.getCommaSeperatedUser().equals(""))
						&& (null == searchCriteria.getEB01() ||  searchCriteria.getEB01().equals(""))
						&& (null == searchCriteria.getEB03() ||  searchCriteria.getEB03().equals(""))
						&& (null == searchCriteria.getIII02() || searchCriteria.getIII02().equals(""))
						&& (null == searchCriteria.getNoteType() || searchCriteria.getNoteType().equals(""))
						&& (null == searchCriteria.getMessageText() || searchCriteria.getMessageText().equals(""))) {
					
					advSearchVariablesMainQueryPart.append(advSearchUnMappedVariablesQuery);
					advSearchVariablesMainQueryPart.append(varConditionUnMapped);
					advSearchVariablesMainQueryPart.append(varDescConditionUnMapped);
					advSearchVariablesMainQueryPart.append(userConditionUnMapped);
					advSearchVariablesMainQueryPart.append(usernameCondition);
					advSearchVariablesMainQueryPart.append(" UNION ALL ");
					}
				}
				advSearchVariablesMainQueryPart
						.append(advSearchMappedVariablesQuery);
			}
			advSearchVariablesMainQueryPart.append(varConditionMapped);
			advSearchVariablesMainQueryPart.append(varDescConditionMapped);
			advSearchVariablesMainQueryPart.append(userConditionMapped);
			advSearchVariablesMainQueryPart.append(EB01Condition);
			advSearchVariablesMainQueryPart.append(EB03Condition);
			advSearchVariablesMainQueryPart.append(III02Condition);
			advSearchVariablesMainQueryPart.append(noteTypeCondition);
			advSearchVariablesMainQueryPart.append(messageTextCondition);
		}
		StringBuffer advSearchQueryForHeadingsFinal = new StringBuffer(
				"SELECT * from (");
		advSearchQueryForHeadingsFinal.append(advSearchVariablesMainQueryPart);
		advSearchQueryForHeadingsFinal.append(") ORDER BY VAR");

		// Variable Query for fetching major and minor headings
		StringBuffer advSearchQueryForHeadings = new StringBuffer("");
		if (!"".equals(conditionalqueryHeading)) {
			advSearchQueryForHeadings
					.append("select DISTINCT MSTR.VAR,MSTR.MAJOR,MSTR.MINOR from (");
			advSearchQueryForHeadings.append(advSearchVariablesLGHeadingQuery);
			advSearchQueryForHeadings.append(" UNION ");
			advSearchQueryForHeadings.append(advSearchVariablesISGHeadingQuery);
			advSearchQueryForHeadings.append(conditionalqueryHeading);
		} else {
			advSearchQueryForHeadings.append(advSearchVariablesLGHeadingQuery);
			advSearchQueryForHeadings.append(" UNION ");
			advSearchQueryForHeadings.append(advSearchVariablesISGHeadingQuery);
		}
		log.info(">>>>>>>>> Advance search variable query part 1 - "
				+ESAPI.encoder().encodeForHTML(advSearchQueryForHeadingsFinal.toString()));
		//log.info(">>>>>>>>> Advance search variable query part 2 - "+ advSearchQueryForHeadings);
		GetExcelReportSearchRecordsVarMainQry advSearchVarQry1 = new GetExcelReportSearchRecordsVarMainQry(
				dataSource, advSearchQueryForHeadingsFinal.toString());
		//Implementing the materialized view change
//**************************************************************************************** 
		StringBuffer advSearchQueryForHeadings2 = null; 
		
		
		if (null != searchCriteria.getVariableId()
				&& !searchCriteria.getVariableId().equals("")) {
			advSearchQueryForHeadings2= new StringBuffer("");
			advSearchQueryForHeadings2.append("Select * from VAR_HDNG_MV where VAR= '"+searchCriteria.getVariableId()+"'");
		} else {
			//CASE 1 :  UnMapped
			if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& !(searchCriteria.isNotApplicable())
					&& !(searchCriteria.isNotFinalized())) {
				advSearchQueryForHeadings2= new StringBuffer("");
				/*advSearchQueryForHeadings2.append("Select VAR,MAJOR,MINOR from VAR_HDNG_MV WHERE VAR NOT IN " +
						" (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG)");*/
				//Performance Tuning in the query 
				advSearchQueryForHeadings2.append("Select VAR,BENEFIT_STRUCTURE,MAJOR,MINOR from VAR_HDNG_MV MV ");
				if(null != searchCriteria.getContractId() && !searchCriteria.getContractId().equals("")){
					advSearchQueryForHeadings2.append(", VARSEG V ");
				}
				advSearchQueryForHeadings2.append(" WHERE NOT EXISTS  ");
				advSearchQueryForHeadings2.append(" (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE CNTRCT_VAR_ID = MV.VAR)");
				if(null != searchCriteria.getContractId() && !searchCriteria.getContractId().equals("")){
					advSearchQueryForHeadings2.append(" and MV.VAR = V.CONTRACT_VAR ");
					advSearchQueryForHeadings2.append(" and V.CONTRACT ='");
					advSearchQueryForHeadings2.append(searchCriteria.getContractId().toUpperCase().trim());
					advSearchQueryForHeadings2.append("' ");
					advSearchQueryForHeadings2.append(" and V.START_DT = to_date('");
					advSearchQueryForHeadings2.append(searchCriteria.getContractStartDate());
					advSearchQueryForHeadings2.append("' ,'mm/dd/yyyy') ");
				}
			} 
			// CASE 2 : Mapped
			else if (!searchCriteria.isUnMapped() && searchCriteria.isMapped()
					&& !(searchCriteria.isNotApplicable())
					&& !(searchCriteria.isNotFinalized())) {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append("select DISTINCT MSTR.VAR,MSTR.BENEFIT_STRUCTURE,MSTR.MAJOR,MSTR.MINOR from " +
						"VAR_HDNG_MV MSTR INNER JOIN " +
						"(SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD != 'NOT_APPLICABLE') " +
						" MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID");
			} 
			//CASE 3 :  NOT Applicable
			else if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& (searchCriteria.isNotApplicable())
					&& !(searchCriteria.isNotFinalized())) {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append("select DISTINCT MSTR.VAR,MSTR.BENEFIT_STRUCTURE,MSTR.MAJOR,MSTR.MINOR from " +
						"VAR_HDNG_MV MSTR INNER JOIN " +
						" (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD = 'NOT_APPLICABLE') " +
						" MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID");
			} 
//			CASE 4 :  NOT Finilized
			else if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& !(searchCriteria.isNotApplicable())
					&& (searchCriteria.isNotFinalized())) {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append("select DISTINCT MSTR.VAR,MSTR.BENEFIT_STRUCTURE," +
						" MSTR.MAJOR,MSTR.MINOR from VAR_HDNG_MV MSTR INNER JOIN " +
						" (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE MAPPNG_CMP_IND = 'N' " +
						" ) MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID");
			} 
			//CASE 5 :  
			// *  Mapped & Not Applicable	
			// *  Mapped & Not Finalized	
			// *  Mapped & Not Applicable &	Not Finalized

			else if (!searchCriteria.isUnMapped() && searchCriteria.isMapped()
					&& (searchCriteria.isNotApplicable() || searchCriteria.isNotFinalized())) {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append("SELECT DISTINCT MSTR.VAR, MSTR.BENEFIT_STRUCTURE, MSTR.MAJOR, MSTR.MINOR " +
						" FROM VAR_HDNG_MV MSTR INNER JOIN BX_CNTRCT_VAR_MAPG MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID");
			} 
			//CASE 6 :  Not Applicable &	Not Finalized
			else if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable() && searchCriteria.isNotFinalized()) {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append("select DISTINCT MSTR.VAR,MSTR.BENEFIT_STRUCTURE,MSTR.MAJOR,MSTR.MINOR from " +
						"VAR_HDNG_MV MSTR INNER JOIN " +
						" (SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG WHERE " +
						" VAR_MAPG_STTS_CD = 'NOT_APPLICABLE' OR MAPPNG_CMP_IND = 'N') " +
						" MAPG ON MSTR.VAR = MAPG.CNTRCT_VAR_ID");
			} 
			/** CASE 7 : General 
			 * 1)UnMapped & Mapped		
			 * 2)UnMapped & Not-applicable	
			 * 3)UnMapped & Not-Finalized	
			 * 4)UnMapped & Mapped & Not-applicable	
			 * 5)UnMapped & Mapped & Not-Finalized	
			 * 6)UnMapped & Not-applicable & Not-Finalized		
			 * 7)UnMapped & Mapped & Not-applicable & Not-Finalized	
			 */
			
			else {
				advSearchQueryForHeadings2= new StringBuffer("");
				advSearchQueryForHeadings2.append(" Select VAR,BENEFIT_STRUCTURE,MAJOR,MINOR from VAR_HDNG_MV ");
			}
			
		}
		log.info(">>>>>>>>> Advance search variable heading query - "
				+ESAPI.encoder().encodeForHTML(advSearchQueryForHeadings2.toString()));
		
		/*
		 * try { count = dao.wpdRowCount(dataSource,
		 * advSearchQueryForHeadingsFinal.toString()); System.out.println(count); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
		if (getDataCount() > maxLimit) {
			Random r = new Random();
			int low = 10000;
			int high = 99999;
			int result = r.nextInt(high - low) + low;

			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

			String wpdmassid = timeStamp + String.valueOf(result);

			Long wpd_massid = Long.parseLong(wpdmassid);

			String userid = searchCriteria.getLoggedUser();

			dao.addEntryToBatch(dataSource,
					advSearchQueryForHeadingsFinal.toString() + "@@@@#@@@@" + advSearchQueryForHeadings2.toString(),
					wpd_massid, userid, criteria);

			/*
			 * RequestContext context = RequestContext.getCurrentInstance();
			 * context.execute("dlg.show();");
			 */
			return null;
		} else {
			HashMap headingMap=null;
			VaribaleHeadings varibaleHeadings = null;
			Thread varThread = null;
			long startTime = System.currentTimeMillis();
			if(null != advSearchQueryForHeadings2){
				varibaleHeadings = new VaribaleHeadings(dataSource,advSearchQueryForHeadings2.toString());
				varThread =  new Thread(varibaleHeadings);
				varThread.start();
			}
			long advSearchVarQry1StartTime = System.currentTimeMillis();
			List searchResultListPart1 = advSearchVarQry1.execute();

			long advSearchVarQry1endtime = System.currentTimeMillis();
			log.info(">>>>>>>>>> Time taken for advance search mapping query execution is : "
					+ (advSearchVarQry1endtime - advSearchVarQry1StartTime));
			if (null != varThread) {
				try {
					varThread.join();
					headingMap = varibaleHeadings.getSearchResultMapPart2();
				} catch (InterruptedException e) {
					log.error(e.getMessage(), e);
				}
			}
			long endTime = System.currentTimeMillis();
			log.info(">>>>>>>>>> Time taken for advance search heading & mapping query execution is : "
					+ (endTime - startTime));
			// wrapping the result to an object
			finalResult.setVariableDetailList(searchResultListPart1);
			finalResult.setVariableHeaderList(headingMap);
			advanceSearchResultList.add(finalResult);
			if (null != searchResultListPart1) {
				log.info("Size of Report Main Query >>>" + searchResultListPart1.size());
			}
			return advanceSearchResultList;
		}
	}
	
	/*****************************January Release**********************/
	/**
	 * The method populates the System and Start Date for a Contract to load the System and Start date 
	 * in the advance search page while a contract id is entered.
	 * @param contractId
	 * @return List
	 */
	public List populateSystemAndStartDateForContract(String contractId,String contractSystem, boolean startDateFlag, String startDateForContract){
		if(!startDateFlag){
		List contractSystemFromDB = null;
		StringBuffer populateSystemForContract = new StringBuffer("Select SYSTEM from ( ");
		populateSystemForContract.append(" SELECT distinct CASE ");
		populateSystemForContract.append(" WHEN LG.CONTRACT IS NULL THEN 'ISG' ");
		populateSystemForContract.append(" WHEN ISG.CPFXP_CONT_ID IS NULL THEN 'LG' ELSE 'LG,ISG'");
		populateSystemForContract.append(" END SYSTEM, ");
		populateSystemForContract.append(" CASE  WHEN LG.CONTRACT IS NULL THEN ISG.CPFXP_CONT_ID ");
		populateSystemForContract.append("  WHEN ISG.CPFXP_CONT_ID IS NULL THEN LG.CONTRACT  ELSE LG.CONTRACT");
		populateSystemForContract.append("  END contractid ");
		populateSystemForContract.append(" FROM CONTRACT LG" );
		populateSystemForContract.append(" FULL OUTER JOIN ISG_CPFXP_CONTRACT ISG ");
		populateSystemForContract.append(" ON LG.CONTRACT = ISG.CPFXP_CONT_ID ");
		populateSystemForContract.append( " )temp");
		populateSystemForContract.append( " where temp.contractid = '"); 
		populateSystemForContract.append(contractId);
		populateSystemForContract.append("'");
		log.info("populateSystemAndStartDateForContract query "
				+ESAPI.encoder().encodeForHTML(populateSystemForContract.toString()));
		
		PopulateSystemForContract populateSystemForContractQuery = new PopulateSystemForContract(
				dataSource, populateSystemForContract.toString());
		contractSystemFromDB = populateSystemForContractQuery.execute();
		return contractSystemFromDB;
		}else{
			List contractStartDate = null;
			if(DomainConstants.CONTRACT_SYSTEM_LG.equals(contractSystem)){
				StringBuffer populateStartDateForContract = new StringBuffer("Select START_DT FROM CONTRACT ");
				populateStartDateForContract.append(" WHERE CONTRACT ='");
				populateStartDateForContract.append(contractId);
				populateStartDateForContract.append("' ");
				populateStartDateForContract.append(" AND ROW_STATUS <> 0 AND CNTRC_STAT NOT IN('Marked For Deletion' , 'Deleted') ");
				populateStartDateForContract.append(" order by START_DT DESC");
				PopulateStartDateForContractLG populateStartDateForContractQuery = new PopulateStartDateForContractLG(
						dataSource, populateStartDateForContract.toString());
				contractStartDate = populateStartDateForContractQuery.execute();
				return contractStartDate;
			}
			else{
				StringBuffer populateStartDateForContract = new StringBuffer("SELECT DISTINCT(CPFXP_S_DATE), CPFXP_R_DATE FROM ISG_CPFXP_CONTRACT ");
				populateStartDateForContract.append(" WHERE CPFXP_CONT_ID ='");
				populateStartDateForContract.append(contractId);
				populateStartDateForContract.append("' ");
				if(null != startDateForContract){
					populateStartDateForContract.append(" and CPFXP_S_DATE=to_date('"+startDateForContract+"','MM/DD/YYYY')");
					populateStartDateForContract.append(" order by CPFXP_S_DATE DESC,CPFXP_R_DATE DESC");
				}else{
					populateStartDateForContract.append(" order by CPFXP_S_DATE DESC");	
				}

				log.info("populateSystemAndStartDateForContract query "
						+ESAPI.encoder().encodeForHTML(populateStartDateForContract.toString()));
				
				PopulateStartDateForContractISG populateStartDateForContractQuery = new PopulateStartDateForContractISG(
						dataSource, populateStartDateForContract.toString());
				contractStartDate = populateStartDateForContractQuery.execute();
				return contractStartDate;
			}
		}
	}
	/*****************************January Release**********************/
	
	
	
	
	private class GetExcelReportSearchRecordsVarMainQry extends MappingSqlQuery {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

		private GetExcelReportSearchRecordsVarMainQry(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setVariableId(rs.getString("VAR"));

			searchResult.setDescription(rs.getString("VAR_DESCRIPTION"));
			searchResult.setPva(rs.getString("PROVIDER_ARRANGEMENT"));

			searchResult.setDataType(rs.getString("DATA_TYPE"));

			searchResult.setSystem(rs.getString("SYSTEM"));
			//display category code in advance search report -- BXNI November
			searchResult.setCategoryCode(rs.getString("CATAGORY_CODE"));
			searchResult.setEB01(rs.getString("EB01"));
			searchResult.setEB02(rs.getString("EB02"));
			searchResult.setEB03(BxUtil.removeCommaFromEB03(rs
					.getString("EB03")));
			searchResult.setEB06(rs.getString("EB06"));
			searchResult.setEB09(rs.getString("EB09"));
			//display start age in advance search report -- BXNI November
			searchResult.setStartAge(rs.getString("START_AGE"));
			//display end age in advance search report -- BXNI November
			searchResult.setEndAge(rs.getString("END_AGE"));
			searchResult.setHsd01(rs.getString("HSD01"));
			searchResult.setHsd02(rs.getString("HSD02"));
			searchResult.setHsd03(rs.getString("HSD03"));
			searchResult.setHsd04(rs.getString("HSD04"));
			searchResult.setHsd05(rs.getString("HSD05"));
			searchResult.setHsd06(rs.getString("HSD06"));
			searchResult.setHsd07(rs.getString("HSD07"));
			searchResult.setHsd08(rs.getString("HSD08"));
			searchResult.setAccumulator(rs.getString("ACCUMULATOR"));
			searchResult.setIII02(BxUtil.removeFirstCommaFromEB03Assn(rs.getString("III02")));
			searchResult.setMessageText(BxUtil.removeFirstCommaFromEB03Assn(rs.getString("MESSAGE")));
			searchResult.setNoteTypeCode(BxUtil.removeFirstCommaFromEB03Assn(rs.getString("NOTE_TYPE_CODE")));
			//display UM rules as comma separated in advance search report -- BXNI November
			searchResult.setCommaSeperatedUMRules(rs.getString("UM_RULE"));
			searchResult.setUser(rs.getString("USR"));
			searchResult.setLastChangedTime(rs.getDate("CREATE_DATE"));
			searchResult.setFormattedDate(simpleDateFormat.format(rs
					.getDate("create_date")));
			searchResult.setStatus(rs.getString("Status"));
			searchResult.setMsgReqrdIndctr(BxUtil.removeFirstCommaFromEB03Assn(rs.getString("MSG_RQRD_INDCTR")));
			searchResult.setAccumNotReqrdIndctr(rs
					.getString("ACCUM_NOT_RQRD_INDCTR"));
			searchResult.setFinalizedFlag(rs.getString("NOT_COMPLTE_FLAG"));
			searchResult.setUserName(rs.getString("USER_NAME"));
			searchResult.setAuditLock(rs.getString("AUDIT_LOCK"));
			//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
			searchResult.setProcedureExcludedInd(rs.getString("PROC_EXCLD_IND"));
			searchResult.setIndividualEB03AssnInd(rs.getString("INDVDL_EB03_ASSN_IND"));
			return searchResult;
		}
	}
	private class PopulateSystemForContract extends MappingSqlQuery {
		private PopulateSystemForContract(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setContractSystem(rs.getString("SYSTEM"));
			//System.err.println("In map row"+searchResult.getContractSystem());
			return searchResult;
		}
	}
	private class PopulateStartDateForContractLG extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		private PopulateStartDateForContractLG(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setContractStartDate(simpleDateFormat.format(rs
											.getDate("START_DT")));
			//System.err.println("In map row"+searchResult.getContractSystem());
			return searchResult;
		}
	}
	private class PopulateStartDateForContractISG extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		private PopulateStartDateForContractISG(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setContractStartDate(simpleDateFormat.format(rs
											.getDate("CPFXP_S_DATE")));
			searchResult.setISGContractRevisionDate(simpleDateFormat.format(rs
					.getDate("CPFXP_R_DATE")));
			//System.err.println("In map row"+searchResult.getContractSystem());
			return searchResult;
		}
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
}