package com.wellpoint.ets.ebx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.owasp.esapi.ESAPI;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL This is an class for locating or message text for search,
 *         and get count of total records based on search
 */

public class AdvanceSearchEWPDRepositoryImpl implements AdvanceSearchRepository {

	private DataSource dataSource;

	private static Logger logger = Logger
			.getLogger(AdvanceSearchEWPDRepositoryImpl.class.getName());

	private final String SPS_ID_CONDITION_FOR_MAPPED = " AND BX.SPS_PARAM_ID = ";
	private final String SPS_ID_CONDITION_FOR_UNMAPPED = " AND C.PRMRY_CD = ";
	private final String SPS_EB01_CONDITION_FOR_MAPPED = " AND BX.EB01_VALUE =";
	private final String SPS_IS_FINALIZED_CONDITION = " AND BX.MAPPNG_CMP_IND = 'N'";
	private final String SPS_NOT_APPLICATBLE = " AND BX.STATUS_CD = 'NOT_APPLICABLE' ";
	private final String SPS_NOT_APPLICATBLE_NOTEQUAL = " AND BX.STATUS_CD != 'NOT_APPLICABLE' ";

	private final String SPS_USER_CONDITION_MAPPED = " AND UPPER(BX.LST_CHG_USR) in(";
	private final String SPS_USER_CONDITION_UNMAPPED = " AND UPPER(C.CREATD_USER_ID) in(";

	private final String RULE_NOT_APPLICABLE_MAIN = " AND B.STATUS_CD = 'NOT_APPLICABLE'";
	private final String RULE_NOT_APPLICABLE_TEMP = " AND T.STATUS_CD = 'NOT_APPLICABLE'";
	private final String RULE_NOT_APPLICABLE_MAIN_NOTEQUAL = " AND B.STATUS_CD != 'NOT_APPLICABLE'";
	private final String RULE_NOT_APPLICABLE_TEMP_NOTEQUAL = " AND T.STATUS_CD != 'NOT_APPLICABLE'";
	private final String RULE_CONDITION_MAPPED = " AND R.RULE_ID = ";
	private final String RULE_CONDITION_UNMAPPED = " AND RULE_ID = ";
	private final String RULE_USER_CONDITION_MAIN = " AND UPPER(B.LST_CHG_USR) in( ";
	private final String RULE_USER_CONDITION_TEMP = " AND UPPER(T.LST_CHG_USR) in( ";

	private final String RULE_GROUP_BY_CONDITION_FOR_MAIN = " GROUP BY R.RULE_ID, COMMA_SEPERATED_EB03_HDR(R.RULE_ID,'N'), ";

	private final String RULE_GROUP_BY_CONDITION_FOR_TEMP = " GROUP BY R.RULE_ID, COMMA_SEPERATED_EB03_HDR(R.RULE_ID,'Y'), ";

	private final String RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 = " GROUP BY R.RULE_ID, comma_seperated_eb03search_hdr(R.RULE_ID,'N', ";

	private final String RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 = " GROUP BY R.RULE_ID, comma_seperated_eb03search_hdr(R.RULE_ID,'Y', ";

	private final String MSG_TEXT_LIKE_CONDITION = " AND UPPER(MSG_TEXT) LIKE '%, ";
	private final String MSG_USER_CONDITION = " AND UPPER(A.LST_CHG_USR) IN (";
	private final String MSG_NOTE_TYPE_CODE = " AND NOTE_TYP_CD LIKE ";
	private final String MSG_SPS_ID = " AND A.SPS_ID = ";
	private final String MSG_RULE_ID = " AND A.HDR_RULE_ID = ";

	private final String SPS_SELECT_LOCK = "select spsBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";

	private final String SPS_LOCK_CONDITION = ")spsBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = spsBx.spsId"
			+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";

	private final String RULE_SELECT_LOCK = "select ruleBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";

	private final String RULE_LOCK_CONDITION = ")ruleBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = ruleBx.rule_id"
			+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";

	private final String MSG_SELECT_LOCK = "select customMsgBx.*, lk.bolk_bus_obj_lock_usr_id  from ( ";

	private final String MSG_LOCK_CONDITION = ")customMsgBx left outer join bolk_bus_obj_lock lk on lk.bolk_bus_obj_key_id = customMsgBx.hdr_rule_id || customMsgBx.sps_id "
			+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'";

	private final String ORDER_BY_SPSID = " order by spsId";
	private final String ORDER_BY_RULEID = " ORDER BY RULE_ID ";
	private final String ORDER_BY_SPS_RULE_ID = " ORDER BY HDR_RULE_ID,SPS_ID ";

	private final String unionCondition = " UNION ALL ";

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

		// Standard Message Select Query BXNI CHANGE
		if (searchCriteria.getShowOnlyStandardMesages()) {

			if (searchCriteria.getShowOnlyStandardMesages()) {
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

			if (!BxUtil.hasText(searchCriteria.getEB03())
					&& !BxUtil.hasText(searchCriteria.getMessageText())
					&& !BxUtil.hasText(searchCriteria.getHeaderRuleId())) {
				selectQueryForStandardMessage = "Select STD_MESSAGE MSGTXT , "
						+ " ' ' HDRRULEID, " + " ' ' SPSID," + " ' ' VARID,"
						+ " ' ' EB03" + " FROM BX_STANDARD_MESSAGE "
						+ "WHERE STD_MESSAGE IS NOT NULL ";

				return selectQueryForStandardMessage;
			}
		}

		// BXNI CHANGE ENDS

		if (BxUtil.hasText(searchCriteria.getEB03())
				|| BxUtil.hasText(searchCriteria.getMessageText())) {
			useHdrPart = true;
			useVarPart = true;
		}
		if (BxUtil.hasText(searchCriteria.getHeaderRuleId())) {
			useHdrPart = true;
		}

		String formattedEB0 = BxUtil.getFormattedEB03(searchCriteria.getEB03());
		String formattedRuleId = BxUtil.getFormattedRuleIds(searchCriteria
				.getHeaderRuleId());
		selectQuery += "(";
		if (useHdrPart) {

			String extraWhereCondition = "";
			if (BxUtil.hasText(searchCriteria.getMessageText())) {
				extraWhereCondition += " upper(MSG.MSG_TEXT) like '%"
						+ searchCriteria.getMessageText().trim().toUpperCase()
						+ "%'";
			}
			if (BxUtil.hasText(extraWhereCondition)
					&& BxUtil.hasText(formattedRuleId)) {
				extraWhereCondition += " or ";
			}
			if (BxUtil.hasText(formattedRuleId)) {
				extraWhereCondition += "  upper(MSG.HDR_RULE_ID) in ("
						+ formattedRuleId.toUpperCase() + ")";
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
			}
			selectQuery += " GROUP BY MSG.MSG_TEXT,MSG.HDR_RULE_ID,MSG.SPS_ID,MSG.EB03) ";

			selectQuery += " union ";
			// header rule temp tables
			selectQuery += "(SELECT MSG.MSG_TEXT MSGTXT, MSG.HDR_RULE_ID HDRRULEID, MSG.SPS_ID SPSID, "
				+ " ' ' VARID, MSG.EB03 AS EB03 "
				+ " FROM TEMP_BX_CSTM_MSG_TEXT MSG "
				+ " WHERE MSG.MSG_TEXT is not null ";
			
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + ")";
				extraWhereConditionForHeaderRule = extraWhereCondition;
			}
			selectQuery += " GROUP BY MSG.MSG_TEXT,MSG.HDR_RULE_ID,MSG.SPS_ID,MSG.EB03) ";
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
					&& BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " or";
			}

			if (BxUtil.hasText(formattedEB0)) {
				extraWhereCondition += " upper(VAREB0.EB03_ASSN) in ("
					+ formattedEB0.toUpperCase() + ")";
			}

			// variable main tables

			selectQuery += " (SELECT VAR.MSG MSGTXT, ' ' HDRRULEID, ' ' SPSID, "
					+ " VAR.CNTRCT_VAR_ID VARID, VAREB0.EB03_ASSN AS EB03 "
					+ " FROM BX_CNTRCT_VAR_MAPG VAR "
					+ " RIGHT OUTER JOIN BX_CNTRCT_VAR_MAPG_VAL VAREB0 ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
					+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + ") ";
				// BXNI CHANGE
				selectQueryForStandardMessage += " and ( "
						+ extraWhereCondition + ") ";
				// BXNI CHANGE ENDS
			}
			selectQuery += " GROUP BY VAR.MSG,VAR.CNTRCT_VAR_ID,VAR.VAR_MAPG_SYS_ID,VAREB0.EB03_ASSN) ";

			selectQuery += " union ";
			// variable temp tables
			selectQuery += " (SELECT VAR.MSG MSGTXT, ' ' HDRRULEID, ' ' SPSID, "
					+ " VAR.CNTRCT_VAR_ID VARID, VAREB0.EB03_ASSN AS EB03 "
					+ " FROM TEMP_BX_CNTRCT_VAR_MAPG VAR "
					+ " RIGHT OUTER JOIN TEMP_BX_CNTRCT_VAR_MAPG_VAL VAREB0 ON VAREB0.VAR_MAPG_SYS_ID = VAR.VAR_MAPG_SYS_ID "
					+ "    WHERE (VAREB0.DATA_ELEMENT_ID ='MSG' and VAREB0.DATA_ELEMENT_VAL IS NOT NULL) ";
			if (BxUtil.hasText(extraWhereCondition)) {
				selectQuery += " and ( " + extraWhereCondition + ")";
			}
			selectQuery += " GROUP BY VAR.MSG,VAR.CNTRCT_VAR_ID,VAR.VAR_MAPG_SYS_ID,VAREB0.EB03_ASSN) ";

			// BXNI CHANGE
			if (searchCriteria.getShowOnlyStandardMesages()) {

				// FOR TEMP TABLE OF VARIABLE
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
					selectQueryForStandardMessage += " and ( "
							+ extraWhereCondition + "))";
				}
				// FOR MAIN TABLE OF HEADER RULE-SPS ID
				selectQueryForStandardMessage += " UNION SELECT STDMSG.STD_MESSAGE MSGTXT, "
						+ "  MSG.HDR_RULE_ID HDRRULEID, MSG.SPS_ID SPSID, "
						+ " ' ' VARID, MSG.EB03 AS EB03 "
						+ "  FROM BX_CSTM_MSG_TEXT MSG "
						+ "  INNER JOIN BX_STANDARD_MESSAGE STDMSG "
						+ "  ON MSG.MSG_TEXT = STDMSG.STD_MESSAGE "
						+ " WHERE MSG.MSG_TEXT IS NOT NULL ";
				if (BxUtil.hasText(extraWhereConditionForHeaderRule)) {
					selectQueryForStandardMessage += " and ( "
							+ extraWhereConditionForHeaderRule + ")";
				}
				// FOR TEMP TABLE OF HEADER RULE-SPS ID
				selectQueryForStandardMessage += " UNION SELECT STDMSG.STD_MESSAGE MSGTXT, "
						+ " MSG.HDR_RULE_ID HDRRULEID, MSG.SPS_ID SPSID, "
						+ "  ' ' VARID, MSG.EB03 AS EB03 "
						+ "  FROM TEMP_BX_CSTM_MSG_TEXT MSG "
						+ "  INNER JOIN BX_STANDARD_MESSAGE STDMSG "
						+ "  ON MSG.MSG_TEXT = STDMSG.STD_MESSAGE "
						+ " WHERE MSG.MSG_TEXT IS NOT NULL ";
				if (BxUtil.hasText(extraWhereConditionForHeaderRule)) {
					selectQueryForStandardMessage += " and ( "
							+ extraWhereConditionForHeaderRule + ")";
				}
				if (!"".equals(searchCriteria.getMessageText().trim()
						.toUpperCase())
						&& null != searchCriteria.getMessageText().trim()
								.toUpperCase()) {
					selectQueryForStandardMessage += " UNION ALL "
							+ " (SELECT STD_MESSAGE MSGTXT, "
							+ "   ' ' HDRRULEID,"
							+ "  ' ' SPSID, "
							+ "  ' '  VARID,"
							+ "   ' ' EB03 "
							+ "  FROM BX_STANDARD_MESSAGE  "
							+ " WHERE ( upper(STD_MESSAGE) like '%"
							+ searchCriteria.getMessageText().trim()
									.toUpperCase() + "%')) ";
				}

				return selectQueryForStandardMessage;
			}
			// BXNI CHANGE ENDS

		}
		selectQuery += ")";
		logger.debug(ESAPI.encoder().encodeForHTML(selectQuery));
		return selectQuery;
	}

	/**
	 * Query mapper class for Search Result
	 * 
	 * @author U25727
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
			searchResult
					.setIsStandardMessage(rs.getString("ISSTANDARDMESSAGE"));
			return searchResult;

		}
	}

	/**
	 * Query mapper for total record count.
	 * 
	 * @author U25727
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

	private final class FindTotalNoOfRecordsForAdvanceQuery extends
			MappingSqlQuery {

		private FindTotalNoOfRecordsForAdvanceQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}

	}

	private final class AdvanceSearchResultMapping extends MappingSqlQuery {

		private String loggedUser;
		private String authorizeApprove;

		private AdvanceSearchResultMapping(DataSource dataSource, String query,
				String user, String authorizeApprove) {
			super(dataSource, query);
			super.compile();
			this.loggedUser = user;
			this.authorizeApprove = authorizeApprove;
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult searchResult = new SearchResult();
			searchResult.setSpsId(rs.getString("SPSID"));
			// searchResult.setDataTypesAndPva(getDataTypePva(rs.getString("DTYPE_PVA")));
			searchResult.setEB01(rs.getString("EB01_VALUE"));
			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs
					.getDate("CREATD_TM_STMP")));
			searchResult.setStatus(rs.getString("STATUS"));
			searchResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			searchResult.setEB02(rs.getString("EB02_VALUE"));
			searchResult.setEB06(rs.getString("EB06_VALUE"));
			searchResult.setEB09(rs.getString("EB09_VALUE"));
			searchResult.setHsd01(rs.getString("HSD1_VALUE"));
			searchResult.setHsd02(rs.getString("HSD2_VALUE"));
			searchResult.setHsd03(rs.getString("HSD3_VALUE"));
			searchResult.setHsd04(rs.getString("HSD4_VALUE"));
			searchResult.setHsd05(rs.getString("HSD5_VALUE"));
			searchResult.setHsd06(rs.getString("HSD6_VALUE"));
			searchResult.setHsd07(rs.getString("HSD7_VALUE"));
			searchResult.setHsd08(rs.getString("HSD8_VALUE"));
			searchResult.setFinalizedFlag(rs.getString("ISFINALIZED"));
			searchResult.setDescription(rs.getString("SPSDESC"));
			searchResult.setAccumulatorSpsID(rs.getString("ACCUMR_SPS_ID"));
			searchResult.setLockedUserId(rs
					.getString("bolk_bus_obj_lock_usr_id"));

			return getSpsLockDetails(searchResult, this.loggedUser, "",
					this.authorizeApprove);

		}
	}

	private final class AdvanceSearchResultRuleIdMapping extends
			MappingSqlQuery {
		private String loggedUser;
		private String authorizeApprove;

		private AdvanceSearchResultRuleIdMapping(DataSource dataSource,
				String query, String user, String authorizeApprove) {
			super(dataSource, query);
			super.compile();
			this.loggedUser = user;
			this.authorizeApprove = authorizeApprove;
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult searchResult = new SearchResult();
			if (null != rs.getString("RULE_ID")) {
				searchResult.setHeaderRuleId(rs.getString("RULE_ID"));
			}
			if (null != rs.getString("RULE_SHRT_DESC")) {
				searchResult.setDescription(rs.getString("RULE_SHRT_DESC"));
			}
			if (null != rs.getDate("CREATD_DATE")) {
				searchResult
						.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs
								.getDate("CREATD_DATE")));
			}
			if (null != rs.getString("STATUS_CD")) {
				searchResult.setStatus(rs.getString("STATUS_CD"));
			}
			if (null != rs.getString("LST_CHG_USR")) {
				searchResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			}
			if (null != rs.getString("EB03")) {
				searchResult.setEB03(BxUtil.removeCommaFromEB03(rs.getString(
						"EB03").toUpperCase()));
			}
			if (null != rs.getString("III02")) {
				searchResult.setIII02(rs.getString(
						"III02"));
			}
			if (null != rs.getString("bolk_bus_obj_lock_usr_id")) {
				searchResult.setLockedUserId(rs
						.getString("bolk_bus_obj_lock_usr_id"));
			}
			return getRuleLockDetails(searchResult, this.loggedUser, "",
					authorizeApprove);

		}
	}

	private final class AdvanceSearchResultMsgMapping extends MappingSqlQuery {
		private String loggedUser;
		private String authorizeApprove;

		private AdvanceSearchResultMsgMapping(DataSource dataSource,
				String query, String user, String authorizeApprove) {
			super(dataSource, query);
			super.compile();
			this.loggedUser = user;
			this.authorizeApprove = authorizeApprove;
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult searchResult = new SearchResult();
			searchResult.setHeaderRuleId(rs.getString("HDR_RULE_ID"));
			searchResult.setSpsId(rs.getString("SPS_ID"));
			searchResult.setHeaderRuleDescription(rs.getString("HDR_DESC"));
			searchResult.setSpsRuleDescription(rs.getString("SPS_DESC"));
			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs
					.getDate("CREATD_TM_STMP")));
			searchResult.setStatus(rs.getString("STATUS_CD"));
			searchResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			if(null != rs.getString("INDVDL_EB03_ASSN_IND") && rs.getString("INDVDL_EB03_ASSN_IND").equals("Y")){
				if (null != rs.getString("MSG_TEXT")) {
					String[] msgArray = BxUtil.removeCommaFromEB03(
							rs.getString("MSG_TEXT").toUpperCase()).split(",");
					if (null != msgArray && msgArray.length > 0) {
						StringBuffer sb = new StringBuffer();
						for(int i = 0; i < msgArray.length; i++){
							String msg = msgArray[i];
							if(null != msg && !msg.equals(DomainConstants.EMPTY)){
								if(null == sb.toString() || (null != sb.toString() && sb.toString().equals(DomainConstants.EMPTY))){
									sb.append(msg);
								}else{
									sb.append("...");
									break;
								}
							}
						}
						searchResult.setMessageText(sb.toString());
					}
				}
			}else{
				StringBuffer sb = new StringBuffer();
				if (null != rs.getString("MSG_TEXT")) {
					String[] msgArray = BxUtil.removeCommaFromEB03(
							rs.getString("MSG_TEXT").toUpperCase()).split(",");
					for(int i = 0; i < msgArray.length; i++){
						String msg = msgArray[i];
						if(null != msg && !msg.equals(DomainConstants.EMPTY)){
							sb.append(msg);
							break;
						}					
					}
				}
				searchResult.setMessageText(sb.toString());
			}
			if (null != rs.getString("NOTE_TYP_CD")) {
				String[] noteTypArray = BxUtil.removeCommaFromEB03(
						rs.getString("NOTE_TYP_CD").toUpperCase()).split(",");
				if (null != noteTypArray && noteTypArray.length > 0) {
					searchResult.setNoteTypeCode(noteTypArray[0]);
				}
			}
			searchResult.setMessageIndicator("MSG_RQRD_IND");
			searchResult.setLockedUserId(rs
					.getString("bolk_bus_obj_lock_usr_id"));

			return getMsgLockDetails(searchResult, this.loggedUser, "",
					this.authorizeApprove);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.wellpoint.ets.ebx.mapping.repository.AdvanceSearchRepository#
	 * getAdvanceRecordCount
	 * (com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria)
	 */
	public int getAdvanceRecordCount(SearchCriteria searchCriteria) {

		// BxUtil.replaceSingleQuote(searchCriteria);
		String selectQuery = "";
		Integer totalNoOfRecords = 0;
		if (searchCriteria.isSpsIdCriteria()) {
			selectQuery = getAdvanceSearchQueryForSpsCriteria(searchCriteria);
			selectQuery = SPS_SELECT_LOCK + selectQuery + SPS_LOCK_CONDITION;
		}
		if (searchCriteria.isRuleIdCriteria()) {
			selectQuery = getAdvanceSearchQueryForRuleCriteria(searchCriteria);
			selectQuery = RULE_SELECT_LOCK + selectQuery + RULE_LOCK_CONDITION;
		}
		if (searchCriteria.isMsgCrteria()) {
			selectQuery = getAdvanceSearchRecordCountQueryForMsgCriteria(searchCriteria);
			selectQuery = MSG_SELECT_LOCK + selectQuery + MSG_LOCK_CONDITION;
		}
		if (!searchCriteria.isRuleIdCriteria() || (searchCriteria.isRuleIdCriteria() && !searchCriteria.getIII02().contains(","))) {
			selectQuery = "select count(*) from (" + selectQuery + ")";
			logger.debug("SelectQuery for Count:" +ESAPI.encoder().encodeForHTML(selectQuery));
			FindTotalNoOfRecordsForAdvanceQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsForAdvanceQuery(
					dataSource, selectQuery);
			List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
			totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
			logger.debug("Records Count : >" + totalNoOfRecords);
		}
		if (searchCriteria.isRuleIdCriteria() && searchCriteria.getIII02().contains(",")) {
			AdvanceSearchResultRuleIdMapping searchResultMapping = new AdvanceSearchResultRuleIdMapping(
					dataSource, selectQuery, searchCriteria
							.getLoggedUser(), searchCriteria
							.getAuthorizedToApproveFlag());
			List<SearchResult> result = searchResultMapping.execute();
			result = getResultOnIii02SearchCriteria(result,searchCriteria);
			if(null != result) {
				totalNoOfRecords = result.size();
			}
		}
		return totalNoOfRecords.intValue();
	}

	public List<SearchResult> getAdvanceRecords(SearchCriteria searchCriteria,
			int noOfRecords, Page page) {

		List<SearchResult> result = null;
		if (searchCriteria.isSpsIdCriteria()) {
			String selectQueryForAdvanceSearch = getAdvanceSearchQueryForSpsCriteria(searchCriteria);
			String appendQueryString = "";

			if (page != null) {
				selectQueryForAdvanceSearch = "select * from (select SPSDATA.*, ROWNUM rnm from ("
						+ selectQueryForAdvanceSearch
						+ ") SPSDATA) WHERE rnm between "
						+ page.getStartRowNum() + " and " + page.getEndRowNum();
			}
			appendQueryString = SPS_SELECT_LOCK + selectQueryForAdvanceSearch
					+ SPS_LOCK_CONDITION;
			logger.info("Final Query Constructed For Sps Based Criteria:"
					+ESAPI.encoder().encodeForHTML(appendQueryString));
			AdvanceSearchResultMapping searchResultMapping = new AdvanceSearchResultMapping(
					dataSource, appendQueryString, searchCriteria
							.getLoggedUser(), searchCriteria
							.getAuthorizedToApproveFlag());
			result = searchResultMapping.execute();
		}
		if (searchCriteria.isRuleIdCriteria()) {
			String selectQueryForAdvanceSearch = getAdvanceSearchQueryForRuleCriteria(searchCriteria);
			String appendQueryString = "";

			if (page != null) {
				if(null != searchCriteria.getIII02() && !searchCriteria.getIII02().equals("")) {
					if(searchCriteria.getIII02().contains(",")) {
						selectQueryForAdvanceSearch = "select * from (select RULEDATA.*, ROWNUM rnm from ("
							+ selectQueryForAdvanceSearch
							+ ") RULEDATA) " 
							+ ORDER_BY_RULEID;
					}else {
						selectQueryForAdvanceSearch = "select * from (select RULEDATA.*, ROWNUM rnm from ("
							+ selectQueryForAdvanceSearch
							+ ") RULEDATA) WHERE rnm between "
							+ page.getStartRowNum()
							+ " and "
							+ page.getEndRowNum()
							+ ORDER_BY_RULEID;
					}
				}else {
					selectQueryForAdvanceSearch = "select * from (select RULEDATA.*, ROWNUM rnm from ("
						+ selectQueryForAdvanceSearch
						+ ") RULEDATA) WHERE rnm between "
						+ page.getStartRowNum()
						+ " and "
						+ page.getEndRowNum()
						+ ORDER_BY_RULEID;
				}
			}
			appendQueryString = RULE_SELECT_LOCK + selectQueryForAdvanceSearch
					+ RULE_LOCK_CONDITION;
			logger.info("Final Query Constructed For Rule Based Criteria:"
					+ESAPI.encoder().encodeForHTML(appendQueryString));
			AdvanceSearchResultRuleIdMapping searchResultMapping = new AdvanceSearchResultRuleIdMapping(
					dataSource, appendQueryString, searchCriteria
							.getLoggedUser(), searchCriteria
							.getAuthorizedToApproveFlag());
			result = searchResultMapping.execute();
			result = getResultOnIii02SearchCriteria(result,searchCriteria);
		}
		if (searchCriteria.isMsgCrteria()) {
			String selectQueryForAdvanceSearch = getAdvanceSearchQueryForMsgCriteria(searchCriteria);
			String appendQueryString = "";

			if (page != null) {
				selectQueryForAdvanceSearch = "select * from (select MESSAGE.*, ROWNUM rnm from ("
						+ selectQueryForAdvanceSearch
						+ ") MESSAGE) WHERE rnm between "
						+ page.getStartRowNum() + " and " + page.getEndRowNum();
			}
			appendQueryString = MSG_SELECT_LOCK + selectQueryForAdvanceSearch
					+ MSG_LOCK_CONDITION;
			logger.info("Final Query Constructed For Message Based Criteria:"
					+ESAPI.encoder().encodeForHTML(appendQueryString));
			AdvanceSearchResultMsgMapping searchResultMapping = new AdvanceSearchResultMsgMapping(
					dataSource, appendQueryString, searchCriteria
							.getLoggedUser(), searchCriteria
							.getAuthorizedToApproveFlag());
			result = searchResultMapping.execute();
		}
		logger.info("Result size after advance search:" + result.size());
		return result;
	}

	/**
	 * Returns the SPS query
	 * 
	 * @param searchCriteria
	 * @return
	 */
	private String getAdvanceSearchQueryForSpsCriteria(
			SearchCriteria searchCriteria) {

		String selectQuery = "";

		StringBuffer mappedRecordsFromMain = new StringBuffer();

		mappedRecordsFromMain
				.append("SELECT BX.SPS_PARAM_ID SPSID,C.CD_DESC_TXT SPSDESC, ");
		mappedRecordsFromMain
				.append("BX.CREATD_TM_STMP CREATD_TM_STMP,BX.STATUS_CD STATUS,");
		mappedRecordsFromMain
				.append("BX.LST_CHG_USR LST_CHG_USR,BX.EB01_VALUE,");
		mappedRecordsFromMain
				.append("BX.EB02_VALUE,BX.EB06_VALUE,BX.EB09_VALUE,BX.HSD1_VALUE,");
		mappedRecordsFromMain
				.append("BX.HSD2_VALUE,BX.HSD3_VALUE,BX.HSD4_VALUE,BX.HSD5_VALUE,");
		mappedRecordsFromMain
				.append("BX.HSD6_VALUE,BX.HSD7_VALUE,BX.HSD8_VALUE,BX.MAPPNG_CMP_IND ISFINALIZED,BX.ACCUMR_SPS_ID ");
		mappedRecordsFromMain.append("FROM BX_SPS_MAPNG BX,CATALOG B,ITEM C ");
		mappedRecordsFromMain.append("WHERE B.CTLG_ID IN ");
		mappedRecordsFromMain
				.append("(SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference') ) ");
		mappedRecordsFromMain.append("AND B.CTLG_ID       = C.CTLG_ID ");
		mappedRecordsFromMain.append("AND BX.SPS_PARAM_ID =C.PRMRY_CD ");
		mappedRecordsFromMain.append("AND BX.IN_TEMP_TAB  = 'N' ");

		StringBuffer mappedRecordsFromTemp = new StringBuffer();

		mappedRecordsFromTemp
				.append("SELECT BX.SPS_PARAM_ID SPSID,C.CD_DESC_TXT SPSDESC, ");
		mappedRecordsFromTemp
				.append("BX.CREATD_TM_STMP CREATD_TM_STMP,BX.STATUS_CD STATUS,");
		mappedRecordsFromTemp
				.append("BX.LST_CHG_USR LST_CHG_USR,BX.EB01_VALUE,");
		mappedRecordsFromTemp
				.append("BX.EB02_VALUE,BX.EB06_VALUE,BX.EB09_VALUE,BX.HSD1_VALUE,");
		mappedRecordsFromTemp
				.append("BX.HSD2_VALUE,BX.HSD3_VALUE,BX.HSD4_VALUE,BX.HSD5_VALUE,");
		mappedRecordsFromTemp
				.append("BX.HSD6_VALUE,BX.HSD7_VALUE,BX.HSD8_VALUE,BX.MAPPNG_CMP_IND ISFINALIZED,BX.ACCUMR_SPS_ID ");
		mappedRecordsFromTemp
				.append("FROM TEMP_BX_SPS_MAPG BX,CATALOG B,ITEM C ");
		mappedRecordsFromTemp.append("WHERE B.CTLG_ID IN ");
		mappedRecordsFromTemp
				.append("(SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference') ) ");
		mappedRecordsFromTemp.append("AND B.CTLG_ID       = C.CTLG_ID ");
		mappedRecordsFromTemp.append("AND BX.SPS_PARAM_ID =C.PRMRY_CD ");

		StringBuffer unMappedRecords = new StringBuffer();

		unMappedRecords
				.append("SELECT C.PRMRY_CD SPSID, C.CD_DESC_TXT SPSDESC,");
		unMappedRecords
				.append("C.CREATD_TM_STMP CREATD_TM_STMP,  'UNMAPPED' STATUS,");
		unMappedRecords.append("'' LST_CHG_USR ,");
		unMappedRecords
				.append("'' EB01_VALUE, '' EB02_VALUE,  '' EB06_VALUE,  '' EB09_VALUE,");
		unMappedRecords
				.append("'' HSD1_VALUE,  '' HSD2_VALUE,  '' HSD3_VALUE,  '' HSD4_VALUE, ");
		unMappedRecords
				.append("'' HSD5_VALUE,  '' HSD6_VALUE,  '' HSD7_VALUE,  '' HSD8_VALUE, ");
		unMappedRecords.append(" 'Y' ISFINALIZED,'' ACCUMR_SPS_ID");
		unMappedRecords.append(" FROM CATALOG B,  ITEM C ");
		unMappedRecords.append(" WHERE C.PRMRY_CD NOT IN ");
		unMappedRecords
				.append("(SELECT BX.SPS_PARAM_ID FROM BX_SPS_MAPNG BX  )");
		unMappedRecords.append(" AND B.CTLG_ID IN ");
		unMappedRecords
				.append("( SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference')  )");
		unMappedRecords.append(" AND B.CTLG_ID = C.CTLG_ID ");

		// Unmapped checked and mapped and not applicable un checked

		if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 1");
			StringBuffer sb = new StringBuffer();
			sb.append(unMappedRecords);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}

			if (searchCriteria.isNotFinalized()) {
				sb.append(unionCondition);
				sb.append(mappedRecordsFromMain);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}

			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}

		// Mapped checked and un mapped unchecked and not applicalble un checked

		if (searchCriteria.isMapped() && !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 2");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// Mapped and un mapped checked and not applicable un checked
		if (searchCriteria.isUnMapped() && searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 3");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);
			sb.append(unMappedRecords);

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		// for both condition
		// unmapped unchecked ,mapped checked and not applicable
		// unmapped unchcked ,mapped unchecked and not applicable checked
		if ((!searchCriteria.isUnMapped() && searchCriteria.isMapped() && searchCriteria
				.isNotApplicable())
				|| (!searchCriteria.isUnMapped() && !searchCriteria.isMapped() && searchCriteria
						.isNotApplicable())) {
			logger.info("Sps based search criteria 4");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(SPS_NOT_APPLICATBLE);
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(SPS_NOT_APPLICATBLE);
			}
			// if not applicable and not finalized condition checked above query
			// will fetch the not applicable
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()
					&& searchCriteria.isNotFinalized()) {
				/**
				 * The following query will return the not finalized records so
				 * the final result will mapped not applicable union mapped not
				 * finalized
				 **/
				sb.append(unionCondition);

				sb.append(mappedRecordsFromMain);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getEB01()
						&& !searchCriteria.getEB01().equals("")) {
					sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getEB01());
					sb.append("'");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
				sb.append(unionCondition);

				sb.append(mappedRecordsFromTemp);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getEB01()
						&& !searchCriteria.getEB01().equals("")) {
					sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getEB01());
					sb.append("'");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}

			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// unmapped chcked ,mapped unchecked and not applicable checked
		if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 5");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);
			sb.append(unMappedRecords);

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// un mapped checked ,mapped checked and not applicable checked
		if (searchCriteria.isMapped() && searchCriteria.isUnMapped()
				&& searchCriteria.isNotApplicable()) {

			logger.info("Sps based search criteria 6");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * sb.append(SPS_IS_FINALIZED_CONDITION); }
			 */
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			// sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * sb.append(SPS_IS_FINALIZED_CONDITION); }
			 */
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			// sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// Only spsID or EB01 value
		if (((null != searchCriteria.getSpsId() && !searchCriteria.getSpsId()
				.equals("")) || (null != searchCriteria.getEB01() && !searchCriteria
				.getEB01().equals("")))
				&& !searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 7");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);
			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(unionCondition);
				sb.append(unMappedRecords);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_UNMAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		// Only isFinalized checked and all the others unchecked
		if (searchCriteria.isNotFinalized() && !searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 8");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_IS_FINALIZED_CONDITION);

			sb.append(unionCondition);
			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_IS_FINALIZED_CONDITION);
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().trim().length() > 0
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {

			logger.info("Sps based search criteria 9");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}

			if (searchCriteria.isNotApplicable())
				sb.append(SPS_NOT_APPLICATBLE);
			sb.append(SPS_USER_CONDITION_MAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}

			if (searchCriteria.isNotApplicable())
				sb.append(SPS_NOT_APPLICATBLE);
			sb.append(SPS_USER_CONDITION_MAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}

			sb.append(SPS_USER_CONDITION_UNMAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}

		return selectQuery;
	}

	/**
	 * Query for Rule Criteria
	 * 
	 * @param searchCriteria
	 * @return
	 */
	private String getAdvanceSearchQueryForRuleCriteria(
			SearchCriteria searchCriteria) {

		StringBuffer mappedRecordsFromMain = new StringBuffer();
		String selectQuery = "";
		boolean containsIII02Comma = false;
		if (null != searchCriteria.getIII02()
				&& !"".equals(searchCriteria.getIII02())) {
			String[] iii02Array = searchCriteria.getIII02().split(",");
			if(iii02Array.length >1){
				containsIII02Comma = true;
			}
			
		}

		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			//if (containsEb03Comma) {
				mappedRecordsFromMain
						.append(
								"SELECT * FROM ( SELECT R.RULE_ID, COMMA_SEPERATED_EB03SEARCH_HDR(r.rule_id,    'N', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								" ) as EB03, ");

			/*} else {
				mappedRecordsFromMain
						.append("SELECT * FROM ( SELECT R.RULE_ID, ', ' || comma_seperated_eb03_hdr(r.rule_id,    'N')|| ' ,' as EB03, ");
			}*/
		} else {
			mappedRecordsFromMain
					.append("SELECT * FROM ( SELECT R.RULE_ID, ', ' || comma_seperated_eb03_hdr(r.rule_id,    'N')|| ' ,' as EB03, ");
		}

		mappedRecordsFromMain
				.append(" COMMA_SEPERATED_III02_HDR(r.rule_id,    'N') as III02, ");

		mappedRecordsFromMain
				.append(" R.RULE_SHRT_DESC,MAX(B.CREATD_TM_STMP) AS CREATD_DATE,B.STATUS_CD  AS STATUS_CD ,");
		mappedRecordsFromMain.append(" B.LST_CHG_USR    AS LST_CHG_USR, B.INDVDL_EB03_ASSN_IND AS INDVDL_EB03_ASSN_IND ");
		mappedRecordsFromMain.append(" FROM BX_RULE_SRVC_TYP_ASSN B,RULE R ");
		mappedRecordsFromMain
				.append(" WHERE  B.RULE_ID = R.RULE_ID  AND B.in_temp_tab ='N' ");
		/*if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			if (!containsEb03Comma) {
				mappedRecordsFromMain.append(" AND (B.SRVC_TYP_CODE LIKE '%");
				mappedRecordsFromMain.append(searchCriteria.getEB03());
				mappedRecordsFromMain.append("%')");
			}
		}*/
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
			if (!containsIII02Comma) {
				mappedRecordsFromMain.append(" AND (B.III02 LIKE '%");
				mappedRecordsFromMain.append(searchCriteria.getIII02());
				mappedRecordsFromMain.append("%')");
			} 
		}

		StringBuffer mappedRecordsFromTemp = new StringBuffer();

		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			//if (containsEb03Comma) {
				mappedRecordsFromTemp
						.append(
								"SELECT * FROM ( SELECT R.RULE_ID, COMMA_SEPERATED_EB03SEARCH_HDR(r.rule_id,    'Y', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								" ) as EB03, ");

			/*} else {
				mappedRecordsFromTemp
						.append("SELECT * FROM( SELECT R.RULE_ID,', ' || comma_seperated_eb03_hdr(r.rule_id,    'Y')|| ' ,' as EB03, ");
			}*/
		} else {
			mappedRecordsFromTemp
					.append("SELECT * FROM( SELECT R.RULE_ID,', ' || comma_seperated_eb03_hdr(r.rule_id,    'Y')|| ' ,' as EB03, ");
		}

		mappedRecordsFromTemp
				.append(" COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y') as III02, ");

		mappedRecordsFromTemp
				.append(" R.RULE_SHRT_DESC,MAX(T.CREATD_TM_STMP) AS CREATD_DATE,");
		mappedRecordsFromTemp
				.append(" T.STATUS_CD AS STATUS_CD ,T.LST_CHG_USR AS LST_CHG_USR, T.INDVDL_EB03_ASSN_IND AS INDVDL_EB03_ASSN_IND ");
		mappedRecordsFromTemp
				.append(" FROM TEMP_BX_RULE_SRVC_TYP_ASSN T,BX_RULE_SRVC_TYP_ASSN B,RULE R");
		mappedRecordsFromTemp
				.append(" WHERE  T.RULE_ID = R.RULE_ID  AND T.RULE_ID = B.RULE_ID AND B.IN_TEMP_TAB ='Y'");
		/*if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			if (!containsEb03Comma) {
				mappedRecordsFromTemp.append(" AND (T.SRVC_TYP_CODE LIKE '%");
				mappedRecordsFromTemp.append(searchCriteria.getEB03());
				mappedRecordsFromTemp.append("%')");
			}
		}*/
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")) {
			if (!containsIII02Comma) {
				mappedRecordsFromTemp.append(" AND (T.III02 LIKE '%");
				mappedRecordsFromTemp.append(searchCriteria.getIII02());
				mappedRecordsFromTemp.append("%')");
			} 
		}

		StringBuffer unMappedRecords = new StringBuffer();

		unMappedRecords
				.append(" SELECT RULE_ID,NULL AS EB03, NULL AS III02, RULE_SHRT_DESC,RULE_APRVD_DT AS CREATD_DATE ,");
		unMappedRecords
				.append(" 'UNMAPPED'    AS STATUS_CD,NULL AS LST_CHG_USR,NULL AS INDVDL_EB03_ASSN_IND FROM RULE ");
		unMappedRecords.append(" WHERE RULE_TYP_CD = 'WPDAUTOBG' ");
		unMappedRecords.append(" AND WPD_DEL_FLAG  = 'N' AND RULE_ID NOT  IN ");
		unMappedRecords
				.append(" (SELECT RULE_ID FROM BX_RULE_SRVC_TYP_ASSN  )");

		// Not applicable checked ,mapped and unmapped unchecked both condition
		// Not applicable checked,Mapped checked and unmapped unchecked
		if ((!searchCriteria.isMapped() && !searchCriteria.isUnMapped() && searchCriteria
				.isNotApplicable())
				|| (searchCriteria.isMapped() && !searchCriteria.isUnMapped() && searchCriteria
						.isNotApplicable())) {
			logger.info("Rule based search criteria 1");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (!searchCriteria.isMapped() && !searchCriteria.isUnMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(RULE_NOT_APPLICABLE_MAIN);
			}
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}*/
			}/* else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");
			}*/
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (!searchCriteria.isMapped() && !searchCriteria.isUnMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(RULE_NOT_APPLICABLE_TEMP);
			}
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			selectQuery = sb.toString();
		}

		// Not applicable un checked ,mapped checked and unmapped un checked
		if (!searchCriteria.isNotApplicable() && searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()) {
			logger.info("Rule based search criteria 2");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(RULE_NOT_APPLICABLE_MAIN_NOTEQUAL);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(RULE_NOT_APPLICABLE_TEMP_NOTEQUAL);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			selectQuery = sb.toString();

		}
		// Not applicable un checked ,Mapped un checked and un mapped checked
		// both
		// Not applicable checked ,mapped un checked and un mapped checked
		if (!searchCriteria.isNotApplicable() && !searchCriteria.isMapped()
				&& searchCriteria.isUnMapped()) {
			logger.info("Rule based search criteria 3");
			StringBuffer sb = new StringBuffer();
			sb.append(unMappedRecords);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			selectQuery = sb.toString();
		}
		// Not applicable un checked ,Mapped checked and Un Mapped checked
		if (!searchCriteria.isNotApplicable() && searchCriteria.isMapped()
				&& searchCriteria.isUnMapped()) {
			logger.info("Rule based search criteria 4");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(RULE_NOT_APPLICABLE_MAIN_NOTEQUAL);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");
			}*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(RULE_NOT_APPLICABLE_TEMP_NOTEQUAL);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			selectQuery = sb.toString();

		}
		// Not applicable checked ,Mapped checked and Un Mapped checked
		if (searchCriteria.isNotApplicable() && searchCriteria.isMapped()
				&& searchCriteria.isUnMapped()) {
			logger.info("Rule based search criteria 5");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			// sb.append(RULE_NOT_APPLICABLE_MAIN);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			// sb.append(RULE_NOT_APPLICABLE_TEMP);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			selectQuery = sb.toString();

		}
		// NotApplicable checked,Mapped unchecked and UnMapped checked
		if (searchCriteria.isNotApplicable() && !searchCriteria.isMapped()
				&& searchCriteria.isUnMapped()) {
			logger.info("Rule based search criteria 6");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(RULE_NOT_APPLICABLE_MAIN);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(RULE_NOT_APPLICABLE_TEMP);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);
			sb.append(unMappedRecords);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			selectQuery = sb.toString();
		}
		// Only RuleID with all check boxes un checked
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()) {

			logger.info("Rule based search criteria 7");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);

			sb.append(RULE_CONDITION_MAPPED);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);

			sb.append(RULE_CONDITION_MAPPED);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}

			sb.append(unionCondition);
			sb.append(unMappedRecords);

			sb.append(RULE_CONDITION_UNMAPPED);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");

			selectQuery = sb.toString();

		}
		// Only EB03 with all check boxes un checked
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()) {

			logger.info("Rule based search criteria 8");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC,  B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");
			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);

			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(unionCondition);
				sb.append(unMappedRecords);
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}

			selectQuery = sb.toString();

		}
		// Only III02 with all check boxes un checked
		if (null != searchCriteria.getIII02()
				&& !searchCriteria.getIII02().equals("")
				&& (null == searchCriteria.getEB03() || searchCriteria
						.getEB03().equals(""))
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()) {

			logger.info("Rule based search criteria 9");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'N'),");
			}
			sb.append("R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,B.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);

			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(RULE_CONDITION_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP_WITH_EB03 + "'"
							+ searchCriteria.getEB03() + "'" + " ), ");
				/*} else {
					sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
				}*/
			} else {
				sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			}
			if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append("COMMA_SEPERATED_III02_HDR(r.rule_id,    'Y'),");
			}
			sb.append("R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,T.INDVDL_EB03_ASSN_IND");

			sb.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				sb.append(" WHERE eb03  IS ");
				sb.append("NOT NULL ");
				/*if (null != searchCriteria.getIII02()
						&& !searchCriteria.getIII02().equals("")) {
					sb.append(" AND (III02 LIKE '%, ");
					sb.append(searchCriteria.getIII02());
					sb.append(" ,%') ");
				}
			} else if (null != searchCriteria.getIII02()
					&& !searchCriteria.getIII02().equals("")) {
				sb.append(" WHERE (III02 LIKE '%, ");
				sb.append(searchCriteria.getIII02());
				sb.append(" ,%') ");*/
			}

			if (null != searchCriteria.getHeaderRuleId()
					&& !searchCriteria.getHeaderRuleId().equals("")) {
				sb.append(unionCondition);
				sb.append(unMappedRecords);
				sb.append(RULE_CONDITION_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getHeaderRuleId());
				sb.append("'");
			}

			selectQuery = sb.toString();

		}
		// Only user with all other criteria empty
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0
				&& !searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()
				&& (null == searchCriteria.getEB03() || searchCriteria
						.getEB03().equals(""))
				&& (null == searchCriteria.getIII02() || searchCriteria
						.getIII02().equals(""))
				&& (null == searchCriteria.getHeaderRuleId() || searchCriteria
						.getHeaderRuleId().equals(""))) {

			logger.info("Rule based search criteria 10");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_MAIN);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			sb.append(RULE_GROUP_BY_CONDITION_FOR_MAIN);
			sb.append(" )");
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				sb.append(RULE_USER_CONDITION_TEMP);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(" )");
			}

			sb.append(RULE_GROUP_BY_CONDITION_FOR_TEMP);
			sb.append(" )");
			sb.append(unionCondition);
			sb.append(unMappedRecords);

			sb.append(RULE_CONDITION_UNMAPPED);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");
			// sb.append(ORDER_BY_RULEID);
			selectQuery = sb.toString();

		}
		//System.out.println("Select Query for Rule advance search:: "+ selectQuery);
		logger.info("Select Query for Rule advance search:: "+ESAPI.encoder().encodeForHTML(selectQuery));
		return selectQuery;

	}

	/**
	 * Query for message search
	 * 
	 * @param searchCriteria
	 * @return String
	 */

	private String getAdvanceSearchQueryForMsgCriteria(
			SearchCriteria searchCriteria) {
		String selectQuery = "";
		StringBuffer msgFromMain = new StringBuffer();

		msgFromMain
				.append(" SELECT DISTINCT A.HDR_RULE_ID, A.SPS_ID, B.RULE_SHRT_DESC AS HDR_DESC, C.CD_DESC_TXT    AS SPS_DESC,");
		// Changed for Reference Data Values START
		msgFromMain.append(" A.CREATD_TM_STMP, A.STATUS_CD, A.LST_CHG_USR,");
		
		msgFromMain
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'EB03', 'N')|| ' ,' AS EB03, ");

		msgFromMain
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'MSG_TEXT', 'N')|| ' ,' as MSG_TEXT, ");

		msgFromMain
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'NOTE_TYP_CD', 'N')|| ' ,' as NOTE_TYP_CD, ");

		/*
		 * " A.MSG_TEXT, A.NOTE_TYP_CD,"); msgFromMain.append(
		 * "(SELECT VAL_DESC_TXT FROM BX_HIPPA_SEGMENT_VAL WHERE DATA_ELEMENT_ID = 'NOTE_TYPE_CODE' "
		 * );msgFromMain.append(
		 * "AND DATA_ELEMENT_VAL = A.NOTE_TYP_CD  ) NOTE_TYP_CD_DESC, ");
		 */
		msgFromMain.append(" A.MSG_RQRD_IND, ");
		msgFromMain.append(" A.INDVDL_EB03_ASSN_IND ");
		// Changed for Reference Data Values END
		msgFromMain.append(" FROM BX_CSTM_MSG_TEXT A, RULE B, ITEM C ");
		msgFromMain
				.append(" WHERE A.SPS_ID    = C.PRMRY_CD AND A.HDR_RULE_ID = B.RULE_ID AND C.CTLG_ID     = 1938 AND A.IN_TEMP_TAB = 'N' ");

		StringBuffer msgFromTemp = new StringBuffer();

		msgFromTemp
				.append(" SELECT DISTINCT A.HDR_RULE_ID, A.SPS_ID, B.RULE_SHRT_DESC AS HDR_DESC, C.CD_DESC_TXT    AS SPS_DESC,");
		msgFromTemp.append(" A.CREATD_TM_STMP, A.STATUS_CD, A.LST_CHG_USR, ");

		msgFromTemp
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'EB03', 'Y')|| ' ,' AS EB03, ");
		
		msgFromTemp
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'MSG_TEXT', 'Y')|| ' ,' as MSG_TEXT, ");

		msgFromTemp
				.append("', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'NOTE_TYP_CD', 'Y')|| ' ,' as NOTE_TYP_CD, ");

		/*
		 * "A.MSG_TEXT, A.NOTE_TYP_CD,"); msgFromTemp.append(
		 * "(SELECT CD_DESC_TXT FROM item WHERE CTLG_ID = 3887 AND PRMRY_CD  = A.NOTE_TYP_CD  ) NOTE_TYP_CD_DESC,"
		 * );
		 */
		msgFromTemp
				.append("A.MSG_RQRD_IND, A.INDVDL_EB03_ASSN_IND FROM TEMP_BX_CSTM_MSG_TEXT A, RULE B, ITEM C ");
		msgFromTemp
				.append(" WHERE A.SPS_ID    = C.PRMRY_CD AND A.HDR_RULE_ID = B.RULE_ID AND C.CTLG_ID     = 1938 ");

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM ( ");
		sb.append(msgFromMain);
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")) {
			sb.append(MSG_RULE_ID);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");
		}
		if (null != searchCriteria.getSpsId()
				&& !searchCriteria.getSpsId().equals("")) {
			sb.append(MSG_SPS_ID);
			sb.append("'");
			sb.append(searchCriteria.getSpsId());
			sb.append("'");
		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0) {
			sb.append(MSG_USER_CONDITION);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(" ) ");
		}

		sb.append(unionCondition);

		sb.append(msgFromTemp);
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")) {
			sb.append(MSG_RULE_ID);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");
		}
		if (null != searchCriteria.getSpsId()
				&& !searchCriteria.getSpsId().equals("")) {
			sb.append(MSG_SPS_ID);
			sb.append("'");
			sb.append(searchCriteria.getSpsId());
			sb.append("'");
		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0) {
			sb.append(MSG_USER_CONDITION);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(" ) ");
		}
		sb.append(" ) ");

		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")) {
			sb.append(" WHERE NOTE_TYP_CD LIKE ");
			sb.append("'%, ");
			sb.append(searchCriteria.getNoteType());
			sb.append(" ,%'");
			if (null != searchCriteria.getMessageText()
					&& !searchCriteria.getMessageText().equals("")) {
				sb.append(" AND UPPER(MSG_TEXT) LIKE '%, ");
				sb.append(searchCriteria.getMessageText().trim().toUpperCase());
				sb.append(" ,%'");
			}
		} else if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")) {
			sb.append(" WHERE UPPER(MSG_TEXT) LIKE '%, ");
			sb.append(searchCriteria.getMessageText().trim().toUpperCase());
			sb.append(" ,%'");
		}
		sb.append(ORDER_BY_SPS_RULE_ID);

		selectQuery = sb.toString();
		
		//System.out.println("Advanced Search Custom Message Query : "+selectQuery);
		logger.info("Advanced Search Custom Message Query : "+ESAPI.encoder().encodeForHTML(selectQuery));
		return selectQuery;

	}

	/**
	 * Count query for message
	 * 
	 * @param searchCriteria
	 * @return
	 */
	private String getAdvanceSearchRecordCountQueryForMsgCriteria(
			SearchCriteria searchCriteria) {

		String selectQuery = "";
		StringBuffer msgFromMain = new StringBuffer();

		msgFromMain.append(" SELECT DISTINCT A.HDR_RULE_ID, A.SPS_ID ");
		msgFromMain.append(" FROM BX_CSTM_MSG_TEXT A, RULE B, ITEM C ");
		msgFromMain
				.append(" WHERE A.SPS_ID    = C.PRMRY_CD AND A.HDR_RULE_ID = B.RULE_ID AND C.CTLG_ID     = 1938 AND A.IN_TEMP_TAB = 'N' ");

		StringBuffer msgFromTemp = new StringBuffer();

		msgFromTemp.append(" SELECT DISTINCT A.HDR_RULE_ID, A.SPS_ID ");
		msgFromTemp.append(" FROM TEMP_BX_CSTM_MSG_TEXT A, RULE B, ITEM C ");
		msgFromTemp
				.append(" WHERE A.SPS_ID    = C.PRMRY_CD AND A.HDR_RULE_ID = B.RULE_ID AND C.CTLG_ID     = 1938 ");

		StringBuffer sb = new StringBuffer();
		sb.append(msgFromMain);
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")) {
			sb.append(MSG_RULE_ID);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");
		}
		if (null != searchCriteria.getSpsId()
				&& !searchCriteria.getSpsId().equals("")) {
			sb.append(MSG_SPS_ID);
			sb.append("'");
			sb.append(searchCriteria.getSpsId());
			sb.append("'");
		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0) {
			sb.append(MSG_USER_CONDITION);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(" ) ");
		}
		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")) {
			sb.append(" AND ', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'NOTE_TYP_CD', 'N')|| ' ,' LIKE ");
			sb.append("'%, ");
			sb.append(searchCriteria.getNoteType());
			sb.append(" ,%'");
		} else if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")) {
			sb.append(" AND UPPER(', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'MSG_TEXT', 'N')|| ' ,') LIKE '%, ");
			sb.append(searchCriteria.getMessageText().trim().toUpperCase());
			sb.append(" ,%'");
		}
		sb.append(unionCondition);

		sb.append(msgFromTemp);
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")) {
			sb.append(MSG_RULE_ID);
			sb.append("'");
			sb.append(searchCriteria.getHeaderRuleId());
			sb.append("'");
		}
		if (null != searchCriteria.getSpsId()
				&& !searchCriteria.getSpsId().equals("")) {
			sb.append(MSG_SPS_ID);
			sb.append("'");
			sb.append(searchCriteria.getSpsId());
			sb.append("'");
		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0) {
			sb.append(MSG_USER_CONDITION);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(" ) ");
		}

		if (null != searchCriteria.getNoteType()
				&& !searchCriteria.getNoteType().equals("")) {
			sb.append(" AND ', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'NOTE_TYP_CD', 'Y')|| ' ,' LIKE ");
			sb.append("'%, ");
			sb.append(searchCriteria.getNoteType());
			sb.append(" ,%'");
		} else if (null != searchCriteria.getMessageText()
				&& !searchCriteria.getMessageText().equals("")) {
			sb.append(" AND UPPER(', ' || COMMA_SEPERATED_FLD_CM(A.HDR_RULE_ID, A.SPS_ID, 'MSG_TEXT', 'Y')|| ' ,') LIKE '%, ");
			sb.append(searchCriteria.getMessageText().trim().toUpperCase());
			sb.append(" ,%'");
		}
		sb.append(ORDER_BY_SPS_RULE_ID);
		selectQuery = sb.toString();
		logger.info("AdvanceSearchRecordCountQueryForMsgCriteria : "
				+ESAPI.encoder().encodeForHTML(selectQuery));
		return selectQuery;

	}

	/**
	 * Lock details for sps based criteria
	 * 
	 * @param searchResult
	 * @param loggerUser
	 * @param authorizedToUnlock
	 * @param authorizedToApprove
	 * @return SearchResult
	 */
	private SearchResult getSpsLockDetails(SearchResult searchResult,
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
	 * Get lock details for rule
	 * 
	 * @param searchResult
	 * @param loggerUser
	 * @param authorizedToUnlock
	 * @param authorizedToApprove
	 * @return
	 */
	private SearchResult getRuleLockDetails(SearchResult searchResult,
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
	 * Get Lock details for msg based criteria
	 * 
	 * @param searchResult
	 * @param loggerUser
	 * @param authorizedToUnlock
	 * @param authorizedToApprove
	 * @return
	 */
	private SearchResult getMsgLockDetails(SearchResult searchResult,
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
	 * Get Search details based on criteria
	 * 
	 * @param searchCriteria
	 * 
	 * @return List
	 */

	public List<SearchResult> getRecordsForReport(SearchCriteria searchCriteria) {
		List<SearchResult> result = null;
		long startTime = System.currentTimeMillis();
		// Search when criteria is ruleid.
		if (searchCriteria.isRuleIdCriteria()) {
			String selectQueryForAdvanceSearch = getExcelReportSearchQueryForRule(searchCriteria);
			ExcelReportResultRuleIdMapping searchResultMapping = new ExcelReportResultRuleIdMapping(
					dataSource, selectQueryForAdvanceSearch);
			result = searchResultMapping.execute();
			result = getResultOnIii02SearchCriteria(result,searchCriteria);
			logger.info(ESAPI.encoder().encodeForHTML(selectQueryForAdvanceSearch));
			// result = resultsWithEB03CommaSeperated(result);

		}// Search when criteria is spsid.
		else if (searchCriteria.isSpsIdCriteria()) {
			String selectQueryForAdvanceSearch = getExcelReportSearchQueryForSPS(searchCriteria);
			PrintExcelResultSpsMapping reportSearchResultMapping = new PrintExcelResultSpsMapping(
					dataSource, selectQueryForAdvanceSearch);
			result = reportSearchResultMapping.execute();
			logger.info(ESAPI.encoder().encodeForHTML(selectQueryForAdvanceSearch));

		}// Search when criteria is msgtxt.
		else if (searchCriteria.isMsgCrteria()) {
			String selectQueryForAdvanceSearch = getAdvanceSearchQueryForMsgCriteria(searchCriteria);
			PrintExcelResultMsgMapping searchResultMapping = new PrintExcelResultMsgMapping(
					dataSource, selectQueryForAdvanceSearch);
			result = searchResultMapping.execute();
			logger.info(ESAPI.encoder().encodeForHTML(selectQueryForAdvanceSearch));
		}
		long endTime = System.currentTimeMillis();
		logger.debug(">>>>>Time for querring EWPD search "
				+ (endTime - startTime));
		return result;
	}

	private List<SearchResult> getResultOnIii02SearchCriteria(List<SearchResult> result,SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<SearchResult> finalSearchResult = new ArrayList<SearchResult>();
		if(null != searchCriteria && null != searchCriteria.getIII02() && !"".equals(searchCriteria.getIII02())) {
			List<String> iii02CriteriaList = new ArrayList<String>(Arrays.asList(searchCriteria.getIII02().split(",")));
			if(null != result) {
				for(SearchResult searchResult : result) {
					boolean isPresent = false;
					List<String> iii02ResultList = new ArrayList<String>(Arrays.asList(searchResult.getIII02().split(";")));
					List<String> iii02IndvdlResultList = new ArrayList<String>();
					for(String iii02Result : iii02ResultList) {
						if(iii02Result.contains(","))
						{
							iii02IndvdlResultList.addAll(Arrays.asList(iii02Result.split(",")));
						} else {
							iii02IndvdlResultList.add(iii02Result);
						}
					}
					if(null != searchResult.getIII02() && !"".equals(searchResult.getIII02())) {
						for(String iii02Criteria : iii02CriteriaList) {
							for(String iii02Result : iii02IndvdlResultList) {
								if(iii02Criteria.trim().equals(iii02Result.trim())) {
									isPresent = true;
									break;
								}
							}
							if(isPresent == true) {
								finalSearchResult.add(searchResult);
								break;
							}
						}
					}
				}
			}
		}else {
			finalSearchResult = result;
		}
		return finalSearchResult;
	}

	/***************************** January Release **********************/

	public List populateSystemAndStartDateForContract(String contractId,
			String contractSystem, boolean startDateFlag,
			String startDateForContract) {
		return null;
	}

	/***************************** January Release **********************/

	private final class ExcelReportResultRuleIdMapping extends MappingSqlQuery {
		private ExcelReportResultRuleIdMapping(DataSource dataSource,
				String query) {
			super(dataSource, query);
			super.compile();
		}
		
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult searchResult = new SearchResult();
			if (null != rs.getString("BUS_ENTY_NM")) {
				searchResult.setBusinessEntity(rs.getString("BUS_ENTY_NM"));
			}
			if (null != rs.getString("BUS_GRP_NM")) {
				searchResult.setBusinessGroup(rs.getString("BUS_GRP_NM"));
			}
			if (null != rs.getString("MRKT_BUS_UNT")) {
				searchResult.setContractMbu(rs.getString("MRKT_BUS_UNT"));
			}
			if (null != rs.getString("RULE_ID")) {
				searchResult.setHeaderRuleId(rs.getString("RULE_ID"));
			}
			if (null != rs.getString("RULE_SHRT_DESC")) {
				searchResult.setDescription(rs.getString("RULE_SHRT_DESC"));
			}
			if (null != rs.getDate("CREATD_DATE")) {
				searchResult
						.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs
								.getDate("CREATD_DATE")));
			}
			if (null != rs.getString("STATUS_CD")) {
				searchResult.setStatus(rs.getString("STATUS_CD"));
			}
			if (null != rs.getString("LST_CHG_USR")) {
				searchResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			}
			if (null != rs.getString("SEND_DYNMC_INFO")) {
				searchResult.setSensitiveBenefitIndicator(rs
						.getString("SEND_DYNMC_INFO"));
			}
			if (null != rs.getString("EB03")) {
				searchResult.setEB03(BxUtil.removeCommaFromEB03(rs
						.getString("EB03")));
			}
			if (null != rs.getString("III02")) {
				searchResult.setIII02(rs
						.getString("III02"));
			}
			// display UM rules as comma separated in advance search report --
			// BXNI November
			
			if (null != rs.getString("UM_RULE")) {
				searchResult.setCommaSeperatedUMRules(rs.getString("UM_RULE"));
			}
			// display PROCEDURE EXCLUSIVE INDICATOR in advance search report --
			// BXNI JUNE RELEASE
			if (null != rs.getString("PROC_EXCLD_IND")) {
				searchResult.setProcedureExcludedInd(rs
						.getString("PROC_EXCLD_IND"));
			}
			if(null != rs.getString("INDVDL_EB03_ASSN_IND")) {
				searchResult.setIndividualEB03AssnInd(rs.getString("INDVDL_EB03_ASSN_IND"));
			}

			return searchResult;

		}
	}

	/**
	 * Get sql query of rule ID for different criteria
	 * 
	 * @param searchCriteria
	 * 
	 * @return String
	 */
	/*
	 * Modified existing query to add PROCEDURE EXCLUSIVE INDICATOR AS PART OF
	 * June Release
	 */
	private String getExcelReportSearchQueryForRule(
			SearchCriteria searchCriteria) {

		boolean containsEb03Comma = false;
		if (null != searchCriteria.getEB03()
				&& !"".equals(searchCriteria.getEB03())) {
			String[] ebo3Array = searchCriteria.getEB03().split(",");
			if (ebo3Array.length > 1) {
				containsEb03Comma = true;
			}

		}
		StringBuffer mappedRecordsFromMain = new StringBuffer();
		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			//if (containsEb03Comma) {
				mappedRecordsFromMain
						.append(
								" select * from ( SELECT DISTINCT R.RULE_ID,  comma_seperated_eb03search_hdr(r.rule_id,    'N', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								" ) as EB03, ");
			/*} else {
				mappedRecordsFromMain
						.append(" select * from ( SELECT DISTINCT R.RULE_ID,  ', ' || comma_seperated_eb03_hdr(r.rule_id,    'N')|| ' ,' as EB03, ");
			}*/
		} else {
			mappedRecordsFromMain
					.append(" select * from ( SELECT DISTINCT R.RULE_ID,  ', ' || comma_seperated_eb03_hdr(r.rule_id,    'N')|| ' ,' as EB03, ");
		}

		mappedRecordsFromMain
				.append(" COMMA_SEPERATED_III02_HDR(r.rule_id,'N') as III02,R.RULE_SHRT_DESC, MAX(B.CREATD_TM_STMP) AS CREATD_DATE,B.STATUS_CD AS STATUS_CD ,");
		mappedRecordsFromMain.append(" B.LST_CHG_USR   AS LST_CHG_USR, ");
		mappedRecordsFromMain.append(" B.SEND_DYNMC_INFO,");
		mappedRecordsFromMain.append(" NULL AS BUS_GRP_NM, ");
		mappedRecordsFromMain.append(" NULL AS BUS_ENTY_NM, ");
		mappedRecordsFromMain.append(" NULL AS MRKT_BUS_UNT, ");
		mappedRecordsFromMain
				.append("  COMMA_SEPERATED_UMRULE(r.rule_id) UM_RULE, ");
		mappedRecordsFromMain.append("  B.PROC_EXCLD_IND as PROC_EXCLD_IND, ");
		mappedRecordsFromMain.append("  B.INDVDL_EB03_ASSN_IND as INDVDL_EB03_ASSN_IND ");
		mappedRecordsFromMain.append(" FROM BX_RULE_SRVC_TYP_ASSN B,RULE R ");
		mappedRecordsFromMain
				.append(" WHERE  B.RULE_ID = R.RULE_ID  AND B.in_temp_tab ='N' ");
		/*if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			//if (!containsEb03Comma) {
				mappedRecordsFromMain.append(" AND (B.SRVC_TYP_CODE LIKE '%");
				mappedRecordsFromMain.append(searchCriteria.getEB03());
				mappedRecordsFromMain.append("%')");
			//}
		}*/

		StringBuffer mappedRecordsFromTemp = new StringBuffer();

		if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			//if (containsEb03Comma) {
				mappedRecordsFromTemp
						.append(
								" select * from ( SELECT DISTINCT R.RULE_ID, comma_seperated_eb03search_hdr(r.rule_id,    'Y', ")
						.append("'" + searchCriteria.getEB03() + "'").append(
								" ) as EB03, ");
			/*} else {
				mappedRecordsFromTemp
						.append(" select * from ( SELECT DISTINCT R.RULE_ID, ', ' || comma_seperated_eb03_hdr(r.rule_id,    'Y')|| ' ,' as EB03, ");
			}*/
		} else {
			mappedRecordsFromTemp
					.append(" select * from ( SELECT DISTINCT R.RULE_ID, ', ' || comma_seperated_eb03_hdr(r.rule_id,    'Y')|| ' ,' as EB03, ");
		}

		mappedRecordsFromTemp
				.append(" COMMA_SEPERATED_III02_HDR(r.rule_id,'Y') as III02,R.RULE_SHRT_DESC,MAX(T.CREATD_TM_STMP) AS CREATD_DATE,");
		mappedRecordsFromTemp
				.append(" T.STATUS_CD AS STATUS_CD ,T.LST_CHG_USR AS LST_CHG_USR, ");
		mappedRecordsFromTemp.append("   T.SEND_DYNMC_INFO, ");
		mappedRecordsFromTemp.append(" '' AS BUS_GRP_NM, ");
		mappedRecordsFromTemp.append(" '' AS BUS_ENTY_NM, ");
		mappedRecordsFromTemp.append(" '' AS MRKT_BUS_UNT, ");
		mappedRecordsFromTemp
				.append("	COMMA_SEPERATED_UMRULE(r.rule_id) UM_RULE ,");
		mappedRecordsFromTemp.append("  T.PROC_EXCLD_IND as PROC_EXCLD_IND, ");
		mappedRecordsFromTemp.append("  T.INDVDL_EB03_ASSN_IND as INDVDL_EB03_ASSN_IND ");
		mappedRecordsFromTemp
				.append(" FROM TEMP_BX_RULE_SRVC_TYP_ASSN T,BX_RULE_SRVC_TYP_ASSN B,RULE R");
		mappedRecordsFromTemp
				.append(" WHERE  T.RULE_ID = R.RULE_ID  AND T.RULE_ID = B.RULE_ID AND B.IN_TEMP_TAB ='Y' ");
		/*if (null != searchCriteria.getEB03()
				&& !searchCriteria.getEB03().equals("")) {
			if (!containsEb03Comma) {
				mappedRecordsFromTemp.append(" AND (T.SRVC_TYP_CODE LIKE '%");
				mappedRecordsFromTemp.append(searchCriteria.getEB03());
				mappedRecordsFromTemp.append("%')");
			}
		}*/

		StringBuffer unMappedRecords = new StringBuffer();

		unMappedRecords
				.append("SELECT DISTINCT BX.*, BUS_GRP_NM, BUS_ENTY_NM, MRKT_BUS_UNT, NULL AS UM_RULE,NULL AS PROC_EXCLD_IND FROM ( ");

		unMappedRecords
				.append(" SELECT RULE_ID,NULL AS EB03,NULL AS III02, RULE_SHRT_DESC,RULE_APRVD_DT AS CREATD_DATE ,");
		unMappedRecords
				.append(" 'UNMAPPED'    AS STATUS_CD,NULL AS LST_CHG_USR, null as SEND_DYNMC_INFO, NULL as INDVDL_EB03_ASSN_IND FROM RULE ");
		unMappedRecords.append(" WHERE RULE_TYP_CD = 'WPDAUTOBG' ");
		unMappedRecords.append(" AND WPD_DEL_FLAG  = 'N' AND RULE_ID NOT  IN ");
		unMappedRecords
				.append(" (SELECT RULE_ID FROM BX_RULE_SRVC_TYP_ASSN  )");
		if (null != searchCriteria.getHeaderRuleId()
				&& !searchCriteria.getHeaderRuleId().equals("")) {
			mappedRecordsFromMain.append(RULE_CONDITION_MAPPED);
			mappedRecordsFromMain.append("'");
			mappedRecordsFromMain.append(searchCriteria.getHeaderRuleId());
			mappedRecordsFromMain.append("'");
			mappedRecordsFromTemp.append(RULE_CONDITION_MAPPED);
			mappedRecordsFromTemp.append("'");
			mappedRecordsFromTemp.append(searchCriteria.getHeaderRuleId());
			mappedRecordsFromTemp.append("'");
			unMappedRecords.append(RULE_CONDITION_UNMAPPED);
			unMappedRecords.append("'");
			unMappedRecords.append(searchCriteria.getHeaderRuleId());
			unMappedRecords.append("'");
		}
		unMappedRecords.append(") BX LEFT OUTER JOIN ( SELECT RULE_ID, ");
		unMappedRecords
				.append("BUS_GRP_NM, BUS_ENTY_NM, MRKT_BUS_UNT FROM MV_RULE_BUS_DETAILS )X ON X.RULE_ID = BX.RULE_ID ");

		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().length() > 0) {
			mappedRecordsFromMain.append(RULE_USER_CONDITION_MAIN);
			mappedRecordsFromMain
					.append(searchCriteria.getCommaSeperatedUser());
			mappedRecordsFromMain.append(" )");
			mappedRecordsFromTemp.append(RULE_USER_CONDITION_MAIN);
			mappedRecordsFromTemp
					.append(searchCriteria.getCommaSeperatedUser());
			mappedRecordsFromTemp.append(" )");
		}
		if (!searchCriteria.isMapped() && !searchCriteria.isUnMapped()
				&& searchCriteria.isNotApplicable()) {
			mappedRecordsFromMain.append(RULE_NOT_APPLICABLE_MAIN);
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,  B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(unionCondition);
			mappedRecordsFromTemp.append(RULE_NOT_APPLICABLE_TEMP);
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR, T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(mappedRecordsFromTemp);
		} else if (!searchCriteria.isNotApplicable()
				&& searchCriteria.isMapped() && !searchCriteria.isUnMapped()) {
			mappedRecordsFromMain.append(RULE_NOT_APPLICABLE_MAIN_NOTEQUAL);
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,  B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");

			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(unionCondition);
			mappedRecordsFromTemp.append(RULE_NOT_APPLICABLE_TEMP_NOTEQUAL);
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,  T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(mappedRecordsFromTemp);
		} else if (!searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && searchCriteria.isUnMapped()) {
			return unMappedRecords.toString() + " ORDER BY bx.rule_id";
		} else if (!searchCriteria.isNotApplicable()
				&& searchCriteria.isMapped() && searchCriteria.isUnMapped()) {
			mappedRecordsFromMain.append(RULE_NOT_APPLICABLE_MAIN_NOTEQUAL);
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,  B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromTemp.append(RULE_NOT_APPLICABLE_TEMP_NOTEQUAL);
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,  T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(unionCondition).append(
					mappedRecordsFromTemp).append(unionCondition).append(
					unMappedRecords);
		} else if (searchCriteria.isNotApplicable()
				&& searchCriteria.isMapped() && searchCriteria.isUnMapped()) {
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR,  B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR,  T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				mappedRecordsFromMain.append(unionCondition).append(
						mappedRecordsFromTemp);
			} else {
				mappedRecordsFromMain.append(unionCondition).append(
						mappedRecordsFromTemp).append(unionCondition).append(
						unMappedRecords);
			}

		} else if (searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && searchCriteria.isUnMapped()) {
			mappedRecordsFromMain.append(RULE_NOT_APPLICABLE_MAIN);
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR, B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(unionCondition);
			mappedRecordsFromTemp.append(RULE_NOT_APPLICABLE_TEMP);
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR, T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(mappedRecordsFromTemp).append(
					unionCondition).append(unMappedRecords);
		} else if (!searchCriteria.isNotApplicable()
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()) {
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR, B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR, T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().length() > 0) {
				mappedRecordsFromMain.append(unionCondition).append(
						mappedRecordsFromTemp);
			} else {

				if (null != searchCriteria.getEB03()
						&& !searchCriteria.getEB03().equals("")) {
					mappedRecordsFromMain.append(unionCondition).append(
							mappedRecordsFromTemp);
				} else {
					mappedRecordsFromMain.append(unionCondition).append(
							mappedRecordsFromTemp).append(unionCondition)
							.append(unMappedRecords);
				}
			}
		} else {
			mappedRecordsFromMain
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, B.STATUS_CD, B.LST_CHG_USR, B.SEND_DYNMC_INFO,B.PROC_EXCLD_IND,B.INDVDL_EB03_ASSN_IND ");
			mappedRecordsFromMain.append(" ) ");
			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromMain.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromTemp
					.append(" group by R.RULE_ID,R.RULE_SHRT_DESC, T.STATUS_CD, T.LST_CHG_USR, T.SEND_DYNMC_INFO,T.PROC_EXCLD_IND,T.INDVDL_EB03_ASSN_IND");
			mappedRecordsFromTemp.append(" ) ");

			if (null != searchCriteria.getEB03()
					&& !searchCriteria.getEB03().equals("")) {
				//if (containsEb03Comma) {
					mappedRecordsFromTemp.append(" where EB03 IS NOT NULL ");
				//}
			}
			mappedRecordsFromMain.append(unionCondition).append(
					mappedRecordsFromTemp);
		}

		// mappedRecordsFromMain =
		// mappedRecordsFromMain.append(ORDER_BY_RULEID);
		//System.out.println("Query$$$$" + mappedRecordsFromMain.toString());
		logger.debug("Query$$$$" +ESAPI.encoder().encodeForHTML(mappedRecordsFromMain.toString()));
		String queryString = "select * from ("
				+ mappedRecordsFromMain.toString() + " ) " + ORDER_BY_RULEID;
		return queryString;
	}

	/**
	 * Get sql query of sps ID for different criteria
	 * 
	 * @param searchCriteria
	 * 
	 * @return String
	 */

	private String getExcelReportSearchQueryForSPS(SearchCriteria searchCriteria) {

		String selectQuery = "";

		StringBuffer mappedRecordsFromMain = new StringBuffer();

		mappedRecordsFromMain
				.append("SELECT BX.SPS_PARAM_ID SPSID,C.CD_DESC_TXT SPSDESC, ");
		mappedRecordsFromMain
				.append("BX.CREATD_TM_STMP CREATD_TM_STMP,BX.STATUS_CD STATUS,");
		mappedRecordsFromMain
				.append("BX.LST_CHG_USR LST_CHG_USR,BX.EB01_VALUE,");
		mappedRecordsFromMain
				.append("BX.EB02_VALUE,BX.EB06_VALUE,BX.EB09_VALUE,BX.HSD1_VALUE,");
		mappedRecordsFromMain
				.append("BX.HSD2_VALUE,BX.HSD3_VALUE,BX.HSD4_VALUE,BX.HSD5_VALUE,");
		mappedRecordsFromMain
				.append("BX.HSD6_VALUE,BX.HSD7_VALUE,BX.HSD8_VALUE,BX.MAPPNG_CMP_IND ISFINALIZED,COMMA_SEPERATED_PVA(C.PRMRY_CD) PVA, COMMA_SEPERATED_DTTYPE(C.PRMRY_CD) DATATYPE, BX.ACCUMR_SPS_ID,'' bus_enty_nm, '' bus_grp_nm, '' mrkt_bus_unt,''refid ");
		mappedRecordsFromMain.append("FROM BX_SPS_MAPNG BX,CATALOG B,ITEM C ");
		mappedRecordsFromMain.append("WHERE B.CTLG_ID IN ");
		mappedRecordsFromMain
				.append("(SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference') ) ");
		mappedRecordsFromMain.append("AND B.CTLG_ID       = C.CTLG_ID ");
		mappedRecordsFromMain.append("AND BX.SPS_PARAM_ID =C.PRMRY_CD ");
		mappedRecordsFromMain.append("AND BX.IN_TEMP_TAB  = 'N' ");

		StringBuffer mappedRecordsFromTemp = new StringBuffer();

		mappedRecordsFromTemp
				.append("SELECT BX.SPS_PARAM_ID SPSID,C.CD_DESC_TXT SPSDESC, ");
		mappedRecordsFromTemp
				.append("BX.CREATD_TM_STMP CREATD_TM_STMP,BX.STATUS_CD STATUS,");
		mappedRecordsFromTemp
				.append("BX.LST_CHG_USR LST_CHG_USR,BX.EB01_VALUE,");
		mappedRecordsFromTemp
				.append("BX.EB02_VALUE,BX.EB06_VALUE,BX.EB09_VALUE,BX.HSD1_VALUE,");
		mappedRecordsFromTemp
				.append("BX.HSD2_VALUE,BX.HSD3_VALUE,BX.HSD4_VALUE,BX.HSD5_VALUE,");
		mappedRecordsFromTemp
				.append("BX.HSD6_VALUE,BX.HSD7_VALUE,BX.HSD8_VALUE,BX.MAPPNG_CMP_IND ISFINALIZED,COMMA_SEPERATED_PVA(C.PRMRY_CD) PVA, COMMA_SEPERATED_DTTYPE(C.PRMRY_CD) DATATYPE, BX.ACCUMR_SPS_ID,'' bus_enty_nm, '' bus_grp_nm, '' mrkt_bus_unt,''refid ");
		mappedRecordsFromTemp
				.append("FROM TEMP_BX_SPS_MAPG BX,CATALOG B,ITEM C ");
		mappedRecordsFromTemp.append("WHERE B.CTLG_ID IN ");
		mappedRecordsFromTemp
				.append("(SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference') ) ");
		mappedRecordsFromTemp.append("AND B.CTLG_ID       = C.CTLG_ID ");
		mappedRecordsFromTemp.append("AND BX.SPS_PARAM_ID =C.PRMRY_CD ");

		StringBuffer unMappedRecords = new StringBuffer();
		unMappedRecords
				.append("SELECT * FROM (SELECT C.PRMRY_CD SPSID, C.CD_DESC_TXT SPSDESC,");
		unMappedRecords
				.append("C.CREATD_TM_STMP CREATD_TM_STMP,  'UNMAPPED' STATUS,");
		unMappedRecords.append(" '' LST_CHG_USR ,");
		unMappedRecords
				.append("'' EB01_VALUE, '' EB02_VALUE,  '' EB06_VALUE,  '' EB09_VALUE,");
		unMappedRecords
				.append("'' HSD1_VALUE,  '' HSD2_VALUE,  '' HSD3_VALUE,  '' HSD4_VALUE, ");
		unMappedRecords
				.append("'' HSD5_VALUE,  '' HSD6_VALUE,  '' HSD7_VALUE,  '' HSD8_VALUE, ");
		unMappedRecords
				.append(" 'Y' ISFINALIZED,COMMA_SEPERATED_PVA(C.PRMRY_CD) PVA, COMMA_SEPERATED_DTTYPE(C.PRMRY_CD) DATATYPE , '' AS ACCUMR_SPS_ID ");
		unMappedRecords.append(" FROM CATALOG B,  ITEM C ");
		unMappedRecords.append(" WHERE C.PRMRY_CD NOT IN ");
		unMappedRecords
				.append("(SELECT BX.SPS_PARAM_ID FROM BX_SPS_MAPNG BX  )");
		unMappedRecords.append(" AND B.CTLG_ID IN ");
		unMappedRecords
				.append("( SELECT CTLG_ID FROM CATALOG WHERE UPPER(CTLG_NAME) =UPPER('reference')  )");
		unMappedRecords.append(" AND B.CTLG_ID = C.CTLG_ID");
		StringBuffer unMappedBusDetails = new StringBuffer();
		unMappedBusDetails.append(" ) BX LEFT JOIN (SELECT test.bus_enty_nm, ");
		unMappedBusDetails
				.append(" test.bus_grp_nm, test.mrkt_bus_unt,test.refid ");
		unMappedBusDetails
				.append(" FROM MV_SPS_BUS_DETAILS TEST ) BUS ON BX.SPSID = BUS.REFID ");

		if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 1");
			StringBuffer sb = new StringBuffer();

			if (searchCriteria.isNotFinalized()) {
				sb.append(mappedRecordsFromMain);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);

				sb.append(unionCondition);
			}
			sb.append(unMappedRecords);

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unMappedBusDetails.toString());
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}

		// Mapped checked and un mapped unchecked and not applicalble un checked

		if (searchCriteria.isMapped() && !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 2");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// Mapped and un mapped checked and not applicable un checked
		if (searchCriteria.isUnMapped() && searchCriteria.isMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 3");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			sb.append(SPS_NOT_APPLICATBLE_NOTEQUAL);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);
			sb.append(unMappedRecords);

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unMappedBusDetails.toString());
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		// for both condition
		// unmapped unchecked ,mapped checked and not applicable
		// unmapped unchcked ,mapped unchecked and not applicable checked
		if ((!searchCriteria.isUnMapped() && searchCriteria.isMapped() && searchCriteria
				.isNotApplicable())
				|| (!searchCriteria.isUnMapped() && !searchCriteria.isMapped() && searchCriteria
						.isNotApplicable())) {
			logger.info("Sps based search criteria 4");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(SPS_NOT_APPLICATBLE);
			}
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()) {
				sb.append(SPS_NOT_APPLICATBLE);
			}
			// if not applicable and not finalized condition checked above query
			// will fetch the not applicable
			if (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
					&& searchCriteria.isNotApplicable()
					&& searchCriteria.isNotFinalized()) {
				/**
				 * The following query will return the not finalized records so
				 * the final result will mapped not applicable union mapped not
				 * finalized
				 **/
				sb.append(unionCondition);

				sb.append(mappedRecordsFromMain);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getEB01()
						&& !searchCriteria.getEB01().equals("")) {
					sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getEB01());
					sb.append("'");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
				sb.append(unionCondition);

				sb.append(mappedRecordsFromTemp);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
				}
				if (null != searchCriteria.getEB01()
						&& !searchCriteria.getEB01().equals("")) {
					sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
					sb.append("'");
					sb.append(searchCriteria.getEB01());
					sb.append("'");
				}
				sb.append(SPS_IS_FINALIZED_CONDITION);
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_MAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}

			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// unmapped chcked ,mapped unchecked and not applicable checked
		if (searchCriteria.isUnMapped() && !searchCriteria.isMapped()
				&& searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 5");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);
			sb.append(unMappedRecords);

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unMappedBusDetails.toString());
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// un mapped checked ,mapped checked and not applicable checked
		if (searchCriteria.isMapped() && searchCriteria.isUnMapped()
				&& searchCriteria.isNotApplicable()) {

			logger.info("Sps based search criteria 6");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * sb.append(SPS_IS_FINALIZED_CONDITION); }
			 */
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			// sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			/*
			 * if (searchCriteria.isNotFinalized()) {
			 * sb.append(SPS_IS_FINALIZED_CONDITION); }
			 */
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			// sb.append(SPS_NOT_APPLICATBLE);
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_UNMAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unMappedBusDetails.toString());
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();
		}
		// Only spsID or EB01 value
		if (((null != searchCriteria.getSpsId() && !searchCriteria.getSpsId()
				.equals("")) || (null != searchCriteria.getEB01() && !searchCriteria
				.getEB01().equals("")))
				&& !searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 7");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(unionCondition);
			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}

			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(unionCondition);
				sb.append(unMappedRecords);
				if (null != searchCriteria.getSpsId()
						&& !searchCriteria.getSpsId().equals("")) {
					sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
					sb.append("'");
					sb.append(searchCriteria.getSpsId());
					sb.append("'");
					sb.append(unMappedBusDetails.toString());
				}
				if (null != searchCriteria.getCommaSeperatedUser()
						&& searchCriteria.getCommaSeperatedUser().trim()
								.length() > 0) {
					sb.append(SPS_USER_CONDITION_UNMAPPED);
					sb.append(searchCriteria.getCommaSeperatedUser());
					sb.append(") ");
				}
			}
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		// Only isFinalized checked and all the others unchecked
		if (searchCriteria.isNotFinalized() && !searchCriteria.isMapped()
				&& !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {
			logger.info("Sps based search criteria 8");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_IS_FINALIZED_CONDITION);

			sb.append(unionCondition);
			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (null != searchCriteria.getCommaSeperatedUser()
					&& searchCriteria.getCommaSeperatedUser().trim().length() > 0) {
				sb.append(SPS_USER_CONDITION_MAPPED);
				sb.append(searchCriteria.getCommaSeperatedUser());
				sb.append(") ");
			}
			sb.append(SPS_IS_FINALIZED_CONDITION);
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		if (null != searchCriteria.getCommaSeperatedUser()
				&& searchCriteria.getCommaSeperatedUser().trim().length() > 0
				&& !searchCriteria.isMapped() && !searchCriteria.isUnMapped()
				&& !searchCriteria.isNotApplicable()) {

			logger.info("Sps based search criteria 9");
			StringBuffer sb = new StringBuffer();
			sb.append(mappedRecordsFromMain);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}
			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}

			if (searchCriteria.isNotApplicable())
				sb.append(SPS_NOT_APPLICATBLE);
			sb.append(SPS_USER_CONDITION_MAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(unionCondition);

			sb.append(mappedRecordsFromTemp);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}
			if (null != searchCriteria.getEB01()
					&& !searchCriteria.getEB01().equals("")) {
				sb.append(SPS_EB01_CONDITION_FOR_MAPPED);
				sb.append("'");
				sb.append(searchCriteria.getEB01());
				sb.append("'");
			}

			if (searchCriteria.isNotFinalized()) {
				sb.append(SPS_IS_FINALIZED_CONDITION);
			}

			if (searchCriteria.isNotApplicable())
				sb.append(SPS_NOT_APPLICATBLE);
			sb.append(SPS_USER_CONDITION_MAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(unionCondition);

			sb.append(unMappedRecords);
			if (null != searchCriteria.getSpsId()
					&& !searchCriteria.getSpsId().equals("")) {
				sb.append(SPS_ID_CONDITION_FOR_UNMAPPED);
				sb.append("'");
				sb.append(searchCriteria.getSpsId());
				sb.append("'");
			}

			sb.append(SPS_USER_CONDITION_UNMAPPED);
			sb.append(searchCriteria.getCommaSeperatedUser());
			sb.append(") ");
			sb.append(unMappedBusDetails.toString());
			sb.append(ORDER_BY_SPSID);
			selectQuery = sb.toString();

		}
		logger.debug("&&& Queryy::::" +ESAPI.encoder().encodeForHTML(selectQuery));
		return selectQuery;

	}

	private final class PrintExcelResultSpsMapping extends MappingSqlQuery {
		private PrintExcelResultSpsMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult excelReportResult = new SearchResult();
			excelReportResult.setSpsId(rs.getString("SPSID"));
			excelReportResult.setSpsRuleDescription(rs.getString("SPSDESC"));
			excelReportResult.setFormattedDate(BxUtil
					.getFormattedDateWithOutTime(rs.getDate("CREATD_TM_STMP")));
			excelReportResult.setStatus(rs.getString("STATUS"));
			excelReportResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			excelReportResult.setEB01(rs.getString("EB01_VALUE"));
			excelReportResult.setEB02(rs.getString("EB02_VALUE"));
			excelReportResult.setEB06(rs.getString("EB06_VALUE"));
			excelReportResult.setEB09(rs.getString("EB09_VALUE"));
			excelReportResult.setHsd01(rs.getString("HSD1_VALUE"));
			excelReportResult.setHsd02(rs.getString("HSD2_VALUE"));
			excelReportResult.setHsd03(rs.getString("HSD3_VALUE"));
			excelReportResult.setHsd04(rs.getString("HSD4_VALUE"));
			excelReportResult.setHsd05(rs.getString("HSD5_VALUE"));
			excelReportResult.setHsd06(rs.getString("HSD6_VALUE"));
			excelReportResult.setHsd07(rs.getString("HSD7_VALUE"));
			excelReportResult.setHsd08(rs.getString("HSD8_VALUE"));
			excelReportResult.setFinalizedFlag(rs.getString("ISFINALIZED"));
			excelReportResult.setPva(rs.getString("PVA"));
			excelReportResult.setDataType(rs.getString("DATATYPE"));
			excelReportResult.setBusinessEntity(rs.getString("bus_enty_nm"));
			excelReportResult.setBusinessGroup(rs.getString("bus_grp_nm"));
			excelReportResult.setContractMbu(rs.getString("mrkt_bus_unt"));
			excelReportResult.setAccumulator(rs.getString("ACCUMR_SPS_ID"));

			return excelReportResult;

		}
	}

	private final class PrintExcelResultMsgMapping extends MappingSqlQuery {
		private PrintExcelResultMsgMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			super.compile();
		}

		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {

			SearchResult excelReportResult = new SearchResult();
			excelReportResult.setHeaderRuleId(rs.getString("HDR_RULE_ID"));
			excelReportResult.setSpsId(rs.getString("SPS_ID"));
			excelReportResult
					.setHeaderRuleDescription(rs.getString("HDR_DESC"));
			excelReportResult.setSpsRuleDescription(rs.getString("SPS_DESC"));
			excelReportResult.setFormattedDate(BxUtil
					.getFormattedDateWithOutTime(rs.getDate("CREATD_TM_STMP")));
			excelReportResult.setStatus(rs.getString("STATUS_CD"));
			excelReportResult.setLastUpdatedUser(rs.getString("LST_CHG_USR"));
			excelReportResult.setEB03(rs.getString("EB03"));
			excelReportResult.setMessageText(rs.getString("MSG_TEXT"));
			excelReportResult.setNoteTypeCode(rs.getString("NOTE_TYP_CD"));
			/*excelReportResult.setNoteTypeCodeDesc(rs
					.getString("NOTE_TYP_CD_DESC"));*/
			excelReportResult.setMessageIndicator(rs.getString("MSG_RQRD_IND"));
			excelReportResult.setIndividualEB03AssnInd(rs.getString("INDVDL_EB03_ASSN_IND"));

			return excelReportResult;

		}
	}

}