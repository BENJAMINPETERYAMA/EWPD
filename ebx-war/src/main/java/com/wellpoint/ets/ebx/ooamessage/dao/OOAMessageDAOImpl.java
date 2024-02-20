/*
 * <OOAMessageDAOImpl.java>
 *
 * © 2016 - 2017 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.ooamessage.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;

/*
 * This class contains the method for maintaining ooa message 
 */
/**
 * @author AF17776
 */
public class OOAMessageDAOImpl implements OOAMessageDAO {

	private DataSource dataSource;
	private String tableFlag;
	private static Logger logger = Logger.getLogger(OOAMessageDAOImpl.class);

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * The inner class to retrieve network messages
	 */
	public class RetrieveEBXOOAMessageSearchQueryForNetworkId extends
			MappingSqlQuery { // 30296

		private RetrieveEBXOOAMessageSearchQueryForNetworkId(
				DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();

			try {

				maintainOOAMessageRequest.setNetworkOrContractCode(rs
						.getString("NTWRK_ID"));
				maintainOOAMessageRequest.setExchangeIndicator(rs
						.getString("EXCHNG_IND"));
				// maintainOOAMessageRequest.setIsNetwork(true);
				maintainOOAMessageRequest.setMessageEffectiveDate(formatDate(rs
						.getDate("MSG_EFFCTV_DT").toString()));

				maintainOOAMessageRequest.setMessageExpiryDate(formatDate(rs
						.getDate("MSG_EXPRY_DT").toString()));
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(rs
						.getString("MSG_STTS_CD"));
				maintainOOAMessageRequest.setMessageId(String.valueOf(rs
						.getInt("NTWRK_MSG_SYS_ID")));
				maintainOOAMessageRequest.setMessageTextOne(rs
						.getString("MSG_TXT_1"));
				maintainOOAMessageRequest.setMessageTextTwo(rs
						.getString("MSG_TXT_2"));//SND_TO_TEST_IND,SND_TO_PROD_IND
				maintainOOAMessageRequest.setMessageTextThree(rs
						.getString("MSG_TXT_3"));
				maintainOOAMessageRequest. setTestInd(rs
						.getString("SND_TO_TEST_IND"));
				maintainOOAMessageRequest.setProdInd(rs
						.getString("SND_TO_PROD_IND"));
				maintainOOAMessageRequest.setContractCdDisp("none");
				maintainOOAMessageRequest.setSearch("Network");

			} catch (ParseException parEx) {
				logger.error("ParseException occurred in RetrieveEBXOOAMessageSearchQueryForNetworkId.mapRow Method");
				logger.error(parEx.getMessage());
				// e.printStackTrace();
			}

			return maintainOOAMessageRequest;

		}

	}

	/*
	 * The inner class to retrieve Contract messages
	 */
	public class RetrieveEBXOOAMessageSearchQueryForContractCode extends
			MappingSqlQuery { // 30296

		// 30296

		private RetrieveEBXOOAMessageSearchQueryForContractCode(
				DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();

			try {

				maintainOOAMessageRequest.setNetworkOrContractCode(rs
						.getString("CNTRCT_ID"));
				maintainOOAMessageRequest.setMessageExempted(rs
						.getString("MSG_EXMPT_FLG"));

				// maintainOOAMessageRequest.setIsNetwork(false);
				maintainOOAMessageRequest.setMessageEffectiveDate(formatDate(rs
						.getDate("MSG_EFFCTV_DT").toString()));

				maintainOOAMessageRequest.setMessageExpiryDate(formatDate(rs
						.getDate("MSG_EXPRY_DT").toString()));
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(rs
						.getString("MSG_STTS_CD"));
				maintainOOAMessageRequest.setMessageId(String.valueOf(rs
						.getInt("CNTRCT_MSG_SYS_ID")));
				maintainOOAMessageRequest.setMessageTextOne(rs
						.getString("MSG_TXT_1"));
				maintainOOAMessageRequest.setMessageTextTwo(rs
						.getString("MSG_TXT_2"));
				maintainOOAMessageRequest.setMessageTextThree(rs
						.getString("MSG_TXT_3"));
				maintainOOAMessageRequest. setTestInd(rs
						.getString("SND_TO_TEST_IND"));
				maintainOOAMessageRequest.setProdInd(rs
						.getString("SND_TO_PROD_IND"));
				maintainOOAMessageRequest.setContractCdDisp("block");
				maintainOOAMessageRequest.setSearch("Contract");

			} catch (ParseException parEx) {
				logger.error("ParseException occurred in RetrieveEBXOOAMessageSearchQueryForContractCode.mapRow Method");
				logger.error(parEx.getMessage());
				// parEx.printStackTrace();
			}
			return maintainOOAMessageRequest;

		}

	}

	/*
	 * The inner class to retrieve network messages for view screen
	 */
	public class RetrieveEBXOOAMessageViewQueryForNetworkId extends
			MappingSqlQuery { // 30296

		private RetrieveEBXOOAMessageViewQueryForNetworkId(
				DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
			try {
				maintainOOAMessageRequest.setNetworkOrContractCode(rs
						.getString("NTWRK_ID"));
				maintainOOAMessageRequest.setExchangeIndicator(rs
						.getString("EXCHNG_IND"));
				// maintainOOAMessageRequest.setIsNetwork(true);
				maintainOOAMessageRequest.setMessageEffectiveDate(formatDate(rs
						.getDate("MSG_EFFCTV_DT").toString()));
				maintainOOAMessageRequest.setMessageExpiryDate(formatDate(rs
						.getDate("MSG_EXPRY_DT").toString()));
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(rs
						.getString("MSG_STTS_CD"));
				maintainOOAMessageRequest.setMessageId(String.valueOf(rs
						.getInt("NTWRK_MSG_SYS_ID")));
				maintainOOAMessageRequest.setMessageTextOne(rs
						.getString("MSG_TXT_1"));
				maintainOOAMessageRequest.setMessageTextTwo(rs
						.getString("MSG_TXT_2"));
				maintainOOAMessageRequest.setMessageTextThree(rs
						.getString("MSG_TXT_3"));
				maintainOOAMessageRequest.setComments(rs.getString("AUDIT_COMMENTS"));
				maintainOOAMessageRequest.setLstChgUserId(rs.getString("LST_CHG_USR"));
				maintainOOAMessageRequest.setUserId(rs.getString("CREATD_USR_ID"));
				maintainOOAMessageRequest.setLstChgTimestamp(rs.getTimestamp("LST_CHG_TMS"));
				maintainOOAMessageRequest.setCreatedTimeStamp(rs.getTimestamp("CREATD_TM_STMP"));
				maintainOOAMessageRequest. setTestInd(rs
						.getString("SND_TO_TEST_IND"));
				maintainOOAMessageRequest.setProdInd(rs
						.getString("SND_TO_PROD_IND"));
				maintainOOAMessageRequest.setContractCdDisp("none");
				maintainOOAMessageRequest.setSearch("Network");
				maintainOOAMessageRequest.setExindOrMsgExmt("Exchange Indicator");
				
			} catch (ParseException parEx) {
				logger.error("ParseException occurred in RetrieveEBXOOAMessageViewQueryForNetworkId.mapRow Method");
				logger.error(parEx.getMessage());
				// e.printStackTrace();
			}
			return maintainOOAMessageRequest;

		}

	}

	/*
	 * The inner class to retrieve contract messages for view screen
	 */
	public class RetrieveEBXOOAMessageViewQueryForContractCode extends
			MappingSqlQuery { // 30296

		// 30296

		private RetrieveEBXOOAMessageViewQueryForContractCode(
				DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();

			try {
				maintainOOAMessageRequest.setNetworkOrContractCode(rs
						.getString("CNTRCT_ID"));
				maintainOOAMessageRequest.setMessageExempted(rs
						.getString("MSG_EXMPT_FLG"));

				// maintainOOAMessageRequest.setIsNetwork(false);
				maintainOOAMessageRequest.setMessageEffectiveDate(formatDate(rs
						.getDate("MSG_EFFCTV_DT").toString()));

				maintainOOAMessageRequest.setMessageExpiryDate(formatDate(rs
						.getDate("MSG_EXPRY_DT").toString()));
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(rs
						.getString("MSG_STTS_CD"));
				maintainOOAMessageRequest.setMessageId(String.valueOf(rs
						.getInt("CNTRCT_MSG_SYS_ID")));
				maintainOOAMessageRequest.setMessageTextOne(rs
						.getString("MSG_TXT_1"));
				maintainOOAMessageRequest.setMessageTextTwo(rs
						.getString("MSG_TXT_2"));
				maintainOOAMessageRequest.setMessageTextThree(rs
						.getString("MSG_TXT_3"));
				maintainOOAMessageRequest.setComments(rs.getString("AUDIT_COMMENTS")); 
				maintainOOAMessageRequest.setLstChgUserId(rs.getString("LST_CHG_USR"));
				maintainOOAMessageRequest.setUserId(rs.getString("CREATD_USR_ID"));
				maintainOOAMessageRequest.setLstChgTimestamp(rs.getTimestamp("LST_CHG_TMS"));
				maintainOOAMessageRequest.setCreatedTimeStamp(rs.getTimestamp("CREATD_TM_STMP"));
				maintainOOAMessageRequest. setTestInd(rs
						.getString("SND_TO_TEST_IND"));
				maintainOOAMessageRequest.setProdInd(rs
						.getString("SND_TO_PROD_IND"));
				maintainOOAMessageRequest.setContractCdDisp("block");
				maintainOOAMessageRequest.setSearch("Contract");
				maintainOOAMessageRequest.setExindOrMsgExmt("Message Exempted");

			} catch (ParseException parEx) {
				logger.error("ParseException occurred in RetrieveEBXOOAMessageViewQueryForContractCode.mapRow Method");
				logger.error(parEx.getMessage());
				// e.printStackTrace();
			}

			return maintainOOAMessageRequest;

		}

	}

	/**
	 * This method is used to retrieve the ooa message
	 * 
	 * @param search
	 *            String
	 * @param searchCriteria
	 *            String
	 * @param viewOrSearchFunction
	 *            String
	 * @param exchangeInd
	 *            String
	 * @param messageId
	 *            String
	 * @return List - java.util.List Object holding ooa message details
	 */
	public List getOOAMessageSearchQuery(String search, String searchCriteria,
			String viewOrSearchFunction, String exchangeInd, String messageId) { // 30296
		List OOAMessageSearchDetails = null;
		StringBuffer searchQuery = new StringBuffer();

		if (viewOrSearchFunction.equals("searchFunction")) {
			if ("Network".equals(search)) {
				if (null != exchangeInd && !exchangeInd.isEmpty()) {
					searchQuery
							.append("SELECT NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, NTWRK_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_NTWRK_MSG_TXT WHERE NTWRK_ID LIKE '"
									+ searchCriteria+"%' and EXCHNG_IND='"+ exchangeInd+ "' ORDER BY NTWRK_ID, MSG_EFFCTV_DT,EXCHNG_IND ASC");
				} else {
					searchQuery
							.append("SELECT NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, NTWRK_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_NTWRK_MSG_TXT WHERE NTWRK_ID LIKE '"
									+ searchCriteria+"%' ORDER BY NTWRK_ID, MSG_EFFCTV_DT,EXCHNG_IND ASC");
				}
				RetrieveEBXOOAMessageSearchQueryForNetworkId retrieveEBXOOAMessageSearchQueryForNetworkId = new RetrieveEBXOOAMessageSearchQueryForNetworkId(
						dataSource, searchQuery.toString());
				OOAMessageSearchDetails = retrieveEBXOOAMessageSearchQueryForNetworkId
						.execute();

			} else if ("Contract".equals(search)) {
				searchQuery
						.append("SELECT CNTRCT_ID, MSG_EXMPT_FLG, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, CNTRCT_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_CNTRCT_MSG_TXT  WHERE CNTRCT_ID LIKE '"
								+ searchCriteria+"%' ORDER BY CNTRCT_ID, MSG_EFFCTV_DT,MSG_EXMPT_FLG ASC");
				RetrieveEBXOOAMessageSearchQueryForContractCode retrieveEBXOOAMessageSearchQueryForContractCode = new RetrieveEBXOOAMessageSearchQueryForContractCode(
						dataSource, searchQuery.toString());
				OOAMessageSearchDetails = retrieveEBXOOAMessageSearchQueryForContractCode
						.execute();

			}
		} else if (viewOrSearchFunction.equals("viewFunction")) {

			if ("Network".equals(search)) {
				if (null != messageId) {

					if (null != exchangeInd && !exchangeInd.isEmpty()) {
						searchQuery
						.append("SELECT NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, NTWRK_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, AUDIT_COMMENTS,LST_CHG_USR,LST_CHG_TMS,CREATD_USR_ID,CREATD_TM_STMP, SND_TO_TEST_IND, SND_TO_PROD_IND FROM  BX_OOA_NTWRK_MSG_TXT WHERE NTWRK_ID='"+ searchCriteria+ "' and " +
								"EXCHNG_IND='"+ exchangeInd+ "' and NTWRK_MSG_SYS_ID ='"+ messageId + "' "); 
						} else {
						
							searchQuery
							.append("SELECT NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, NTWRK_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, AUDIT_COMMENTS,LST_CHG_USR,LST_CHG_TMS,CREATD_USR_ID,CREATD_TM_STMP,SND_TO_TEST_IND,SND_TO_PROD_IND FROM  BX_OOA_NTWRK_MSG_TXT WHERE NTWRK_ID='"
							+ searchCriteria
							+ "' and NTWRK_MSG_SYS_ID ='"
							+ messageId + "' "); 	
						}
				}
				RetrieveEBXOOAMessageViewQueryForNetworkId retrieveEBXOOAMessageViewQueryForNetworkId = new RetrieveEBXOOAMessageViewQueryForNetworkId(
						dataSource, searchQuery.toString());
				OOAMessageSearchDetails = retrieveEBXOOAMessageViewQueryForNetworkId
						.execute();

			} else if ("Contract".equals(search)) {

				if (null != messageId) {
					
					searchQuery
					.append("SELECT CNTRCT_ID, MSG_EXMPT_FLG, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, CNTRCT_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, AUDIT_COMMENTS,LST_CHG_USR,LST_CHG_TMS,CREATD_USR_ID,CREATD_TM_STMP,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_CNTRCT_MSG_TXT WHERE CNTRCT_ID='"
					+ searchCriteria
					+ "' and CNTRCT_MSG_SYS_ID='"
					+ messageId +"'"); 
				} else {
					
					searchQuery
					.append("SELECT CNTRCT_ID, MSG_EXMPT_FLG, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, CNTRCT_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, AUDIT_COMMENTS,LST_CHG_USR,LST_CHG_TMS,CREATD_USR_ID,CREATD_TM_STMP,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_CNTRCT_MSG_TXT WHERE CNTRCT_ID='"
					+ searchCriteria +"'"); 
					}

				RetrieveEBXOOAMessageViewQueryForContractCode retrieveEBXOOAMessageViewQueryForContractCode = new RetrieveEBXOOAMessageViewQueryForContractCode(
						dataSource, searchQuery.toString());
				OOAMessageSearchDetails = retrieveEBXOOAMessageViewQueryForContractCode
						.execute();

			}

		}

		return OOAMessageSearchDetails;
	}

	/**
	 * This method is used to retrieve the ooa message
	 * 
	 * @param search
	 *            String
	 * @param searchCriteria
	 *            String
	 * @param viewOrSearchFunction
	 *            String
	 * @param exchangeInd
	 *            String
	 * @param messageId
	 *            String
	 * @return List - java.util.List Object holding ooa message details
	 */
	public List getOOAMessageSearchDetails(String search,
			String searchCriteria, String viewOrSearchFunction,
			String exchangeInd, String messageID) // 30296
			throws EBXException, Exception {
		return getOOAMessageSearchQuery(search, searchCriteria,
				viewOrSearchFunction, exchangeInd, messageID);
	}

	/**
	 * This method is used to create the ooa message
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return Boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	@Override
	public Boolean createOOAMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {

		String queryForDuplicateCheck = this.checkDuplicateMessage(
				maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
						"Network"), (maintainOOAMessageRequest
						.getExchangeIndicator().startsWith("BT")),
				Boolean.FALSE);
		CheckDuplicateEntriesQuery checkDuplicateEntriesQuery = new CheckDuplicateEntriesQuery(
				this.getDataSource(), queryForDuplicateCheck);
		Object[] inputParams = new Object[] {
				maintainOOAMessageRequest.getNetworkOrContractCode(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageExpiryDate(),
				maintainOOAMessageRequest.getExchangeIndicator() };
		List countList = checkDuplicateEntriesQuery.execute(inputParams);

		int countOfRecords = 0;
		if (null != countList && countList.size() > 0) {
			countOfRecords = ((Integer) countList.get(0)).intValue();
		}

		/*
		 * if there are no records existing with same data value for the catalog
		 * the new record will be inserted to the table
		 */
		if (countOfRecords == 0) {
			
			if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
					"Network")) {
				try {
					maintainOOAMessageRequest.setMessageId(String.valueOf(getNextNetworkIdSequence()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.insertNetwork(maintainOOAMessageRequest);
			} else {
				try {
					maintainOOAMessageRequest.setMessageId(String.valueOf(getNextContractIdSequence()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.insertContract(maintainOOAMessageRequest);

			}

			return true;
		}

		return false;
	}

	

	// MESSAGE_ID
	// TEXT_LINE_1
	// TEXT_LINE_2
	// TEXT_LINE_3
	// MESSAGE_CREATED_BY
	// MESSAGE_LAST_UPDATED_BY
	// MESSAGE_CREATED_DATE
	// MESSAGE_LAST_UPDATED_DATE

	/**
	 * This method is used to create the ooa message and does the inset in
	 * BX_OOA_NETWORK table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	public boolean insertNetwork(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer networkInsertQuery = new StringBuffer();
		networkInsertQuery
		.append("INSERT INTO BX_OOA_NTWRK_MSG_TXT( NTWRK_MSG_SYS_ID, NTWRK_ID, "
				+ " EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT,"
				+ " MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, "
				+ " CREATD_USR_ID, LST_CHG_USR," 
				+ " MSG_STTS_CD, AUDIT_COMMENTS,"
				+ " CREATD_TM_STMP, LST_CHG_TMS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?," 
				+ " ?, ?, ?, sysdate, sysdate)");
		NetworkInsertTableQuery networkInsertTableQuery = new NetworkInsertTableQuery(
				getDataSource(), networkInsertQuery.toString());
		networkInsertTableQuery.insertNetwork(maintainOOAMessageRequest);

		return true;
	}

	protected class NetworkInsertTableQuery extends SqlUpdate {
		protected NetworkInsertTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CLOB));
			compile();

		}

		protected void insertNetwork(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] {
					maintainOOAMessageRequest.getMessageId(),
					maintainOOAMessageRequest.getNetworkOrContractCode(),
					maintainOOAMessageRequest.getExchangeIndicator(),
					maintainOOAMessageRequest.getMessageEffectiveDate(),
					maintainOOAMessageRequest.getMessageExpiryDate(),
					maintainOOAMessageRequest.getMessageTextOne(),
					maintainOOAMessageRequest.getMessageTextTwo(),
					maintainOOAMessageRequest.getMessageTextThree(),
					maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getCurrentStatus(),
					maintainOOAMessageRequest.getComments() };
			super.update(objs);
		}

	}

	/**
	 * This method is used to update the ooa message and does the update in
	 * BX_OOA_NTWRK_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	public boolean updateNetwork(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer networkUpdateQuery = new StringBuffer();
		networkUpdateQuery
				.append("UPDATE BX_OOA_NTWRK_MSG_TXT SET MSG_EXPRY_DT  = ? "
						+ " ,LST_CHG_USR   = ?,MSG_STTS_CD = ? , LST_CHG_TMS  = sysdate where NTWRK_ID = ? and NTWRK_MSG_SYS_ID = ?");
		NetworkUpdateTableQuery networkUpdateTableQuery = new NetworkUpdateTableQuery(
				getDataSource(), networkUpdateQuery.toString());
		networkUpdateTableQuery.updateNetwork(maintainOOAMessageRequest);

		return true;
	}

	protected class NetworkUpdateTableQuery extends SqlUpdate {
		public NetworkUpdateTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));
			compile();

		}

		protected void updateNetwork(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] {
					getPreviousDayTimestamp(maintainOOAMessageRequest
							.getMessageEffectiveDate()),maintainOOAMessageRequest.getUserId(),
							maintainOOAMessageRequest.getWorkFlowAssosciationStatus(),
					maintainOOAMessageRequest.getNetworkOrContractCode(),
					Integer.parseInt(maintainOOAMessageRequest.getMessageId()) };
			super.update(objs);
		}

	}

	private Date getPreviousDayTimestamp(Date date) {
		Calendar previousDay = Calendar.getInstance();
		previousDay.setTime(date);
		previousDay.add(previousDay.DATE, -1);
		return previousDay.getTime();
	}

	/**
	 * This method is used to update the ooa message and does the update in
	 * BX_OOA_CNTRCT_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	public boolean insertContract(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer contractInsertQuery = new StringBuffer();
		contractInsertQuery
		.append("INSERT INTO BX_OOA_CNTRCT_MSG_TXT( CNTRCT_MSG_SYS_ID, CNTRCT_ID, "
				+ " MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_EXMPT_FLG,"
				+ " MSG_TXT_1, MSG_TXT_2, MSG_TXT_3, "
				+ " CREATD_USR_ID, LST_CHG_USR," 
				+ " MSG_STTS_CD, AUDIT_COMMENTS,"
				+ " CREATD_TM_STMP, LST_CHG_TMS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?," 
				+ " ?, ?, ?, sysdate, sysdate)");
		
		ContractInsertTableQuery contractInsertTableQuery = new ContractInsertTableQuery(
				getDataSource(), contractInsertQuery.toString());
		contractInsertTableQuery.insertContract(maintainOOAMessageRequest);

		return true;
	}

	
	protected class ContractInsertTableQuery extends SqlUpdate {
		protected ContractInsertTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CLOB));
			compile();


		}

		protected void insertContract(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] {
					maintainOOAMessageRequest.getMessageId(),
					maintainOOAMessageRequest.getNetworkOrContractCode(),
					maintainOOAMessageRequest.getMessageEffectiveDate(),
					maintainOOAMessageRequest.getMessageExpiryDate(),
					maintainOOAMessageRequest.getMessageExempted(),
					maintainOOAMessageRequest.getMessageTextOne(),
					maintainOOAMessageRequest.getMessageTextTwo(),
					maintainOOAMessageRequest.getMessageTextThree(),
					maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getCurrentStatus(),
					maintainOOAMessageRequest.getComments() };
			super.update(objs);
		}

	}

	

	/**
	 * This method is used to update the ooa message and does the update in
	 * BX_OOA_NTWRK_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	public boolean updateNetworkForEdit(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer networkUpdateQuery = new StringBuffer();


		networkUpdateQuery.append("UPDATE BX_OOA_NTWRK_MSG_TXT"
				+ " SET  LST_CHG_USR = ?," + " EXCHNG_IND = ?, "
				+ " MSG_STTS_CD = ?,"
				+ " AUDIT_COMMENTS = ?, MSG_EFFCTV_DT=?,MSG_EXPRY_DT=?, MSG_TXT_1=?,MSG_TXT_2=?,MSG_TXT_3=?,"
				+ " LST_CHG_TMS = sysdate" + " WHERE NTWRK_ID = '"
				+ maintainOOAMessageRequest.getNetworkOrContractCode() + "'"
				+ " AND NTWRK_MSG_SYS_ID  = "
				+ maintainOOAMessageRequest.getMessageId());
		NetworkUpdateForEditTableQuery networkUpdateForEditTableQuery = new NetworkUpdateForEditTableQuery(
				getDataSource(), networkUpdateQuery.toString());
		networkUpdateForEditTableQuery.update(maintainOOAMessageRequest);

		return true;
	}

	protected class NetworkUpdateForEditTableQuery extends SqlUpdate {
		protected NetworkUpdateForEditTableQuery(DataSource ds, String sql) {
			super(ds, sql);

			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			
			compile();
		}


		protected void update(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] { maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getExchangeIndicator(), 
					maintainOOAMessageRequest.getCurrentStatus(),
					maintainOOAMessageRequest.getComments(),
					maintainOOAMessageRequest.getMessageEffectiveDate(),
					maintainOOAMessageRequest.getMessageExpiryDate(),
					maintainOOAMessageRequest.getMessageTextOne(),
					maintainOOAMessageRequest.getMessageTextTwo(),
					maintainOOAMessageRequest.getMessageTextThree()};
			super.update(objs);
		}


	}
	/**
	 * This method is used to update the ooa message and does the update in
	 * BX_OOA_CNTRCT_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.Boolean - Representing ooa message creation
	 *         status
	 */
	public boolean updateContractForEdit(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer contractUpdateQuery = new StringBuffer();

		contractUpdateQuery.append("UPDATE BX_OOA_CNTRCT_MSG_TXT"
		+ " SET  LST_CHG_USR = ?," + " MSG_EXMPT_FLG  = ?, "
		+ " MSG_STTS_CD = ?,"
		+ " AUDIT_COMMENTS = ?, MSG_EFFCTV_DT=?, MSG_EXPRY_DT=?,MSG_TXT_1=?,MSG_TXT_2=?,MSG_TXT_3=?,"
		+ " LST_CHG_TMS = sysdate" + " WHERE CNTRCT_ID = '"
		+ maintainOOAMessageRequest.getNetworkOrContractCode() + "'"
		+ " AND CNTRCT_MSG_SYS_ID  = "
		+ maintainOOAMessageRequest.getMessageId());
		ContractUpdateForEditTableQuery contractUpdateForEditTableQuery = new ContractUpdateForEditTableQuery(
				getDataSource(), contractUpdateQuery.toString());
		contractUpdateForEditTableQuery.update(maintainOOAMessageRequest);

		return true;
	}

	protected class ContractUpdateForEditTableQuery extends SqlUpdate {
		protected ContractUpdateForEditTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter("LST_CHG_USR",Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_EXMPT_FLG",Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_STTS_CD",Types.VARCHAR));
			declareParameter(new SqlParameter("AUDIT_COMMENTS",Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_EFFCTV_DT",Types.DATE));
			declareParameter(new SqlParameter("MSG_EXPRY_DT",Types.DATE));
			declareParameter(new SqlParameter("MSG_TXT_1",Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_TXT_2",Types.VARCHAR));
			declareParameter(new SqlParameter("MSG_TXT_3",Types.VARCHAR));
			compile();
		}

		protected void update(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] {
					maintainOOAMessageRequest.getUserId(),
					maintainOOAMessageRequest.getMessageExempted(), 
					maintainOOAMessageRequest.getCurrentStatus(),
					maintainOOAMessageRequest.getComments(),
					maintainOOAMessageRequest.getMessageEffectiveDate(),
					maintainOOAMessageRequest.getMessageExpiryDate(),
					maintainOOAMessageRequest.getMessageTextOne(),
					maintainOOAMessageRequest.getMessageTextTwo(),
					maintainOOAMessageRequest.getMessageTextThree()};
			super.update(objs);
		}

	}

	

	/**
	 * This method is used to modify the ooa message
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return Boolean - java.lang.Boolean - Representing ooa message
	 *         modification status
	 */
	@Override
	public Boolean modifyOOAMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {

		boolean isBoth = maintainOOAMessageRequest.getExchangeIndicator()
				.startsWith("BT");

		String queryForDuplicateCheck = this.checkDuplicateMessage(
				maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
						"Network"), isBoth, Boolean.TRUE);
		CheckDuplicateEntriesQuery checkDuplicateEntriesQuery = new CheckDuplicateEntriesQuery(
				this.getDataSource(), queryForDuplicateCheck);
		Object[] inputParams = new Object[] {
				maintainOOAMessageRequest.getNetworkOrContractCode(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageEffectiveDate(),
				maintainOOAMessageRequest.getMessageExpiryDate(),
				maintainOOAMessageRequest.getExchangeIndicator() };
		List countList = checkDuplicateEntriesQuery.execute(inputParams);

		int countOfRecords = 0;
		if (null != countList && countList.size() > 0) {
			countOfRecords = ((Integer) countList.get(0)).intValue();
		}

		/*
		 * if there are no records existing with same data value for the catalog
		 * the new record will be inserted to the table
		 */
		if (countOfRecords == 0 || (countOfRecords < 2 && isBoth)
				|| !maintainOOAMessageRequest.isHasExchOrExplChanged()) {
			if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
					"Network")) {
				this.updateNetworkForEdit(maintainOOAMessageRequest);
			} else {
				this.updateContractForEdit(maintainOOAMessageRequest);
			}

			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is used to add date segment to the ooa message
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return Boolean - java.lang.Boolean - Representing ooa message add date
	 *         segment status
	 */
	@Override
	public Boolean addDateSegmentToMessage(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {

		Boolean success = false;

		try {
			if (maintainOOAMessageRequest.getSearch() != null) {

				if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
						"Network")) {
					this.updateNetwork(maintainOOAMessageRequest);
					maintainOOAMessageRequest.setMessageId(String.valueOf(getNextNetworkIdSequence()));
					this.insertNetwork(maintainOOAMessageRequest);
				} else {
					this.updateContract(maintainOOAMessageRequest);
					maintainOOAMessageRequest.setMessageId(String.valueOf(getNextContractIdSequence()));
					this.insertContract(maintainOOAMessageRequest);
				}

				success = true;
			}

		} catch (Exception ex) {
			logger.error("Exception occurred in addDateSegmentToMessage Method");
			logger.error(ex.getMessage());
			// e.printStackTrace();
		}

		return success;
	}

	public int getNextNetworkIdSequence() throws SQLException {
		String nextNetworkIdSequence = getNextNetworkIdSequenceSQL();
		NextNetworkIdSequence nextID = new NextNetworkIdSequence(
				getDataSource(), nextNetworkIdSequence);
		List nextDateSegmentID = nextID.execute();
		Integer id = (Integer) nextDateSegmentID.get(0);
		return id.intValue();
	}
	public int getNextContractIdSequence() throws SQLException {
		String nextContractIdSequence = getNextContractIdSequenceSQL();
		NextContractIdSequence nextID = new NextContractIdSequence(
				getDataSource(), nextContractIdSequence);
		List nextDateSegmentID = nextID.execute();
		Integer id = (Integer) nextDateSegmentID.get(0);
		return id.intValue();
	}

	private String getNextMessageIdSequenceSQL() {
		String nextMessageIDSQL = "select BX_OOA_MESSAGE_SEQ.nextval from dual";
		return nextMessageIDSQL;
	}
	private String getNextContractIdSequenceSQL() {
		String nextContractIDSQL = "select CNTRCT_MSG_SYS_ID_SEQ.nextval from dual";
		return nextContractIDSQL;
	}
	private String getNextNetworkIdSequenceSQL() {
		String nextNetworkIDSQL = "select NTWRK_MSG_SYS_ID_SEQ.nextval from dual";
		return nextNetworkIDSQL;
	}

	class NextMessageIdSequence extends MappingSqlQuery {

		public NextMessageIdSequence(DataSource dataSource,
				String nextMessageIdSequence) {
			super(dataSource, nextMessageIdSequence);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int sysId;
			sysId = rs.getInt(1);
			return new Integer(sysId);
		}
	}
	class NextNetworkIdSequence extends MappingSqlQuery {

		public NextNetworkIdSequence(DataSource dataSource,
				String nextNetworkIdSequence) {
			super(dataSource, nextNetworkIdSequence);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int sysId;
			sysId = rs.getInt(1);
			return new Integer(sysId);
		}
	}
	
	class NextContractIdSequence extends MappingSqlQuery {

		public NextContractIdSequence(DataSource dataSource,
				String nextContractIdSequence) {
			super(dataSource, nextContractIdSequence);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int sysId;
			sysId = rs.getInt(1);
			return new Integer(sysId);
		}
	}

	/**
	 * This method is used to update status of the ooa message in
	 * BX_OOA_CNTRCT_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return Boolean - java.lang.Boolean - Representing ooa message update
	 *         status
	 */
	public boolean updateContract(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		StringBuffer contractUpdateQuery = new StringBuffer();
		contractUpdateQuery
				.append("UPDATE BX_OOA_CNTRCT_MSG_TXT SET MSG_EXPRY_DT = ? "
						+ " , LST_CHG_USR   = ? , MSG_STTS_CD= ? , LST_CHG_TMS = sysdate where CNTRCT_ID = ? and CNTRCT_MSG_SYS_ID = ?");
		ContractUpdateTableQuery contractUpdateTableQuery = new ContractUpdateTableQuery(
				getDataSource(), contractUpdateQuery.toString());
		contractUpdateTableQuery.updateContract(maintainOOAMessageRequest);

		return true;
	}

	protected class ContractUpdateTableQuery extends SqlUpdate {
		public ContractUpdateTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));
			compile();

		}

		protected void updateContract(
				MaintainOOAMessageRequest maintainOOAMessageRequest) {
			Object[] objs = new Object[] {
					getPreviousDayTimestamp(maintainOOAMessageRequest
							.getMessageEffectiveDate()), maintainOOAMessageRequest.getUserId(),
							maintainOOAMessageRequest.getWorkFlowAssosciationStatus(),
					maintainOOAMessageRequest.getNetworkOrContractCode(),
					Integer.parseInt(maintainOOAMessageRequest.getMessageId()) };
			super.update(objs);
		}

	}

	/**
	 * This method is used to update status of the ooa message
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return Boolean - java.lang.Boolean - Representing ooa message update
	 *         status
	 */
	public Boolean updateNetworkOrContractStatus(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		try {
			if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase(
					"Network")) {
				updateNetworkStatus(maintainOOAMessageRequest);
			} else {
				updateContractStatus(maintainOOAMessageRequest);
			}
			return true;
		} catch (Exception ex) {
			logger.error("Exception occurred in updateNetworkOrContractStatus Method");
			logger.error(ex.getMessage());
			// ex.printStackTrace();
			return false;
		}

	}

	/**
	 * This method is used to update status of the ooa message in
	 * BX_OOA_CNTRCT_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.boolean - Representing ooa message update
	 *         status
	 */
	public boolean updateContractStatus(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		    StringBuffer contractUpdateQuery = new StringBuffer();
            String userComment=maintainOOAMessageRequest.getComments();
            if("SCHEDULED_TO_TEST".equalsIgnoreCase(maintainOOAMessageRequest.getWorkFlowAssosciationStatus())){
            contractUpdateQuery
                         .append("UPDATE BX_OOA_CNTRCT_MSG_TXT SET MSG_STTS_CD = '"
                                       + maintainOOAMessageRequest
                                                     .getWorkFlowAssosciationStatus()
                                       + "' , LST_CHG_USR = '"+maintainOOAMessageRequest.getUserId()+"' , AUDIT_COMMENTS ='"+userComment+"' || AUDIT_COMMENTS,  LST_CHG_TMS = sysdate where CNTRCT_ID = '"
                                       + maintainOOAMessageRequest.getNetworkOrContractCode()
                                       + "' and MSG_STTS_CD ='BUILDING' or MSG_STTS_CD ='BEING_MODIFIED' ");
            }else{
                   contractUpdateQuery
                   .append("UPDATE BX_OOA_CNTRCT_MSG_TXT SET MSG_STTS_CD = '"
                                + maintainOOAMessageRequest
                                              .getWorkFlowAssosciationStatus()
                                + "' , LST_CHG_USR = '"+maintainOOAMessageRequest.getUserId()+"' , AUDIT_COMMENTS ='"+userComment+"' || AUDIT_COMMENTS,  LST_CHG_TMS = sysdate where CNTRCT_ID = '"
                                + maintainOOAMessageRequest.getNetworkOrContractCode()
                                + "' and MSG_STTS_CD ='OBJECT_TRANSFERRED' and SND_TO_TEST_IND ='Y' ");       
            }

            UpdateContractStatus updateContractStatus = new UpdateContractStatus(
                         getDataSource(), contractUpdateQuery.toString());
            updateContractStatus.update();

            return true;
}

	protected class UpdateContractStatus extends SqlUpdate {
		public UpdateContractStatus(DataSource ds, String sql) {
			super(ds, sql);
			compile();

		}
	}

	
	/**
	 * This method is used to update status of the ooa message in
	 * BX_OOA_NTWRK_MSG_TXT table
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return boolean - java.lang.boolean - Representing ooa message update
	 *         status
	 */
	public boolean updateNetworkStatus(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		String user=maintainOOAMessageRequest.getUserId();
		String userComment=maintainOOAMessageRequest.getComments();
		StringBuffer networkUpdateQuery = new StringBuffer();
		if("SCHEDULED_TO_TEST".equalsIgnoreCase(maintainOOAMessageRequest.getWorkFlowAssosciationStatus())){
		networkUpdateQuery
				.append("UPDATE BX_OOA_NTWRK_MSG_TXT SET MSG_STTS_CD = '"
						+ maintainOOAMessageRequest.getWorkFlowAssosciationStatus()
						+ "' , LST_CHG_USR = '"+user+"' , AUDIT_COMMENTS ='"+userComment+"' || AUDIT_COMMENTS, LST_CHG_TMS = sysdate where NTWRK_ID = '"
						+ maintainOOAMessageRequest.getNetworkOrContractCode()
						+ "' and MSG_STTS_CD ='BUILDING' or MSG_STTS_CD ='BEING_MODIFIED' ");
		}else{
		networkUpdateQuery
		.append("UPDATE BX_OOA_NTWRK_MSG_TXT SET MSG_STTS_CD = '"
				+ maintainOOAMessageRequest.getWorkFlowAssosciationStatus()
				+ "' , LST_CHG_USR = '"+user+"' , AUDIT_COMMENTS ='"+userComment+"' || AUDIT_COMMENTS, LST_CHG_TMS = sysdate where NTWRK_ID = '"
				+ maintainOOAMessageRequest.getNetworkOrContractCode()
				+ "' and MSG_STTS_CD ='OBJECT_TRANSFERRED' and SND_TO_TEST_IND ='Y' ");
		}
		UpdateNetworkStatus updateNetworkStatus = new UpdateNetworkStatus(
				getDataSource(), networkUpdateQuery.toString());
		updateNetworkStatus.update();

		return true;
	}

	protected class UpdateNetworkStatus extends SqlUpdate {
		public UpdateNetworkStatus(DataSource ds, String sql) {
			super(ds, sql);
			compile();

		}
	}

	
	public Date formatDate(String timeStampFormatString) throws ParseException {
		Date convertedDateFormat;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		convertedDateFormat = (Date) sdf.parse(timeStampFormatString);
		return convertedDateFormat;
	}
	
	private String checkDuplicateMessage(Boolean isNetwork, Boolean isBoth,
			Boolean isModify) {
		String networkDupQuery = "";

if (isNetwork) {
			
			if (isModify && !isBoth) {
				
				networkDupQuery = "SELECT " + " COUNT(*) "
						+ " FROM BX_OOA_NTWRK_MSG_TXT " + " WHERE NTWRK_ID = ? "
						+ " AND ((MSG_EFFCTV_DT <= ? "
						+ " AND MSG_EXPRY_DT >= ?  "
						+ ") OR (MSG_EFFCTV_DT >= ? "
						+ " AND MSG_EFFCTV_DT <= ?  "
						+ ")) AND EXCHNG_IND IN (?) "
						+ " AND MSG_STTS_CD not like 'MARKED_FOR_DELETION' "
						+ " AND MSG_STTS_CD not like 'DELETED' ";
				
			} else if (isModify && isBoth){
				
				networkDupQuery = "SELECT " + " COUNT(*) "
						+ " FROM BX_OOA_NTWRK_MSG_TXT " + " WHERE NTWRK_ID = ? "
						+ " AND ((MSG_EFFCTV_DT <= ? "
						+ " AND MSG_EXPRY_DT >= ?  "
						+ ") OR (MSG_EFFCTV_DT >= ? "
						+ " AND MSG_EFFCTV_DT <= ?  "
						+ ")) AND EXCHNG_IND IN ('ON','OF') "
						+ " AND EXCHNG_IND not like ? "
						+ " AND MSG_STTS_CD not like 'MARKED_FOR_DELETION'"
						+ " AND MSG_STTS_CD not like 'DELETED' ";
				
			} else if (!isBoth) {
				networkDupQuery = "SELECT " + " COUNT(*) "
						+ " FROM BX_OOA_NTWRK_MSG_TXT " + " WHERE NTWRK_ID = ? "
						+ " AND ((MSG_EFFCTV_DT <= ? "
						+ " AND MSG_EXPRY_DT >= ?  "
						+ ") OR (MSG_EFFCTV_DT >= ? "
						+ " AND MSG_EFFCTV_DT <= ?  "
						+ ")) AND EXCHNG_IND IN ('BT',?) "
						+ " AND MSG_STTS_CD not like 'MARKED_FOR_DELETION' "
						+ " AND MSG_STTS_CD not like 'DELETED' ";
			} else {
				networkDupQuery = "SELECT " + " COUNT(*) "
						+ " FROM BX_OOA_NTWRK_MSG_TXT " + " WHERE NTWRK_ID = ? "
						+ " AND ((MSG_EFFCTV_DT <= ? "
						+ " AND MSG_EXPRY_DT >= ?  "
						+ ") OR (MSG_EFFCTV_DT >= ? "
						+ " AND MSG_EFFCTV_DT <= ?  "
						+ " )) AND EXCHNG_IND IN ('ON','OF',?)"
						+ " AND MSG_STTS_CD not like 'MARKED_FOR_DELETION' "
						+ " AND MSG_STTS_CD not like 'DELETED' ";
			}
			
			
			
		} else {
			networkDupQuery = "SELECT " + " COUNT(*) "
					+ " FROM BX_OOA_CNTRCT_MSG_TXT " + " WHERE CNTRCT_ID = ? "
					+ " AND ((MSG_EFFCTV_DT <= ? "
					+ " AND MSG_EXPRY_DT >= ?  "
					+ ") OR (MSG_EFFCTV_DT >= ? "
					+ " AND MSG_EFFCTV_DT <= ?  "
					+ ")) AND MSG_EXMPT_FLG IN ('N','Y',?) "
					+ " AND MSG_STTS_CD not like 'MARKED_FOR_DELETION' "
					+ " AND MSG_STTS_CD not like 'DELETED' ";
		}

		return networkDupQuery;
	}

	/**
	 * @author
	 * 
	 */
	private class CheckDuplicateEntriesQuery extends MappingSqlQuery {

		/**
		 * @param dataSource
		 */
		private CheckDuplicateEntriesQuery(DataSource dataSource,
				String checkDuplicatedEntriesQuery) {
			super(dataSource, checkDuplicatedEntriesQuery);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));

		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param rowCountO
		 * @return
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return Integer.valueOf(resultSet.getInt(1));
		}
	}

	public class UpdateOOAMessage extends SqlUpdate {

		private UpdateOOAMessage(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

	}

	protected class RetrieveForDeleteQuery extends MappingSqlQuery {

		protected RetrieveForDeleteQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			MaintainOOAMessageRequest contractVO = new MaintainOOAMessageRequest();
			contractVO.setNetworkOrContractCode(rs.getString(1));
			contractVO.setMessageEffectiveDate(rs.getDate(3));
			contractVO.setMessageExpiryDate(rs.getDate(4));
			contractVO.setWorkFlowAssosciationStatus(rs.getString(2));

			return contractVO;
		}
	}

	/**
	 * This method is used to retrieve ooa message from contract or network
	 * table
	 * 
	 * @param tableName
	 *            String
	 * @param columnName
	 *            String
	 * @param messageId
	 *            java.lang.Integer
	 * 
	 * @return List - java.util.List Object holding ooa message details
	 */
	@Override
	public List<MaintainOOAMessageRequest> retrieveOOAMessageDataByMessageID(
			String tableName, String columnName, Integer messageId) {
		StringBuffer contractRetrieveQuery = new StringBuffer();
		// messageId = 16;
		contractRetrieveQuery
				.append("SELECT "
						+ columnName
						+ ",MESSAGE_ID,MSG_ASSOCIATION_EFFECTIVE_DATE,MSG_ASSOCIATION_EXP_DATE,CURRENT_STATUS  FROM "
						+ tableName + " where message_id =" + messageId + "");
		RetrieveForDeleteQuery retrieveForDeleteQuery = new RetrieveForDeleteQuery(
				getDataSource(), contractRetrieveQuery.toString());
		List<MaintainOOAMessageRequest> contractData = retrieveForDeleteQuery
				.execute();
		return contractData;
	}

	/**
	 * This method is used to retrieve ooa message from contract or network
	 * table based on netWorkOrContractId
	 * 
	 * @param tableName
	 *            String
	 * @param columnName
	 *            String
	 * @param messageId
	 *            java.lang.Integer
	 * @param netWorkOrContractId
	 *            String
	 * 
	 * @return List - java.util.List Object holding ooa message details
	 */
	@Override
	public List<MaintainOOAMessageRequest> retrieveOOAMessageData(
			String tableName, String columnName, Integer messageId,
			String netWorkOrContractId) {
		StringBuffer contractRetrieveQuery = new StringBuffer();
		// messageId=16;
		contractRetrieveQuery
				.append("SELECT "
						+ columnName
						+ ",MESSAGE_ID,MSG_ASSOCIATION_EFFECTIVE_DATE,MSG_ASSOCIATION_EXP_DATE,CURRENT_STATUS  FROM "
						+ tableName
						+ " where message_id in((select min(message_id) from "
						+ tableName + " where message_id >" + messageId
						+ " and " + columnName + "='" + netWorkOrContractId
						+ "'),");
		contractRetrieveQuery.append("(select max(message_id) from "
				+ tableName + " where message_id <" + messageId + " and "
				+ columnName + "='" + netWorkOrContractId + "'))");
		RetrieveForDeleteQuery retrieveForDeleteQuery = new RetrieveForDeleteQuery(
				getDataSource(), contractRetrieveQuery.toString());
		List<MaintainOOAMessageRequest> contractList = retrieveForDeleteQuery
				.execute();
		return contractList;
	}

	/**
	 * This method is used to delete(just changing the status to D) the ooa
	 * message,
	 * 
	 * @param maintainOOAMessageRequest
	 *            - An object of type MaintainOOAMessageRequest representing the
	 *            ooa message details
	 * @return String - java.lang.String
	 */
	@Override
	public Boolean deleteOOAMessage(String DeleteType, Integer messageID,
			String netWorkOrContractId, String columnName, String msgIDClmName,MaintainOOAMessageRequest maintainOOAMessageRequest) {
		try {

			StringBuffer query = new StringBuffer();
			String status=OOAMessageConstants.MARKED_FOR_DELETION;
			String userId=maintainOOAMessageRequest.getUserId();
			String userComment=maintainOOAMessageRequest.getComments();
			query = query
					.append("update "+DeleteType+ " set MSG_STTS_CD='"+status+"', LST_CHG_TMS= sysdate, LST_CHG_USR='"+userId+"',AUDIT_COMMENTS='"+userComment+"' where "+columnName+" = '"+netWorkOrContractId+"' and "+msgIDClmName+" ='"+messageID+"'");
			UpdateOOAMessage updateOOAMessage = new UpdateOOAMessage(
					dataSource, query.toString());
			updateOOAMessage.update();
		} catch (Exception ex) {
			logger.error("Exception occurred in deleteOOAMessage Method");
			logger.error(ex.getMessage());
			// e.printStackTrace();
		}
		return true;
	}

	protected class RetrieveContractData extends MappingSqlQuery {

		protected RetrieveContractData(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			MaintainOOAMessageRequest contractVO = new MaintainOOAMessageRequest();
			contractVO.setNetworkOrContractCode(rs.getString(1));
			contractVO.setMessageEffectiveDate(rs.getDate(2));
			contractVO.setMessageExpiryDate(rs.getDate(3));
			contractVO.setWorkFlowAssosciationStatus(rs.getString(4));
			contractVO.setComments(rs.getString(5));
			contractVO.setMessageTextOne(rs.getString(6));
			contractVO.setMessageTextTwo(rs.getString(7));
			contractVO.setMessageTextThree(rs.getString(8));
			if (OOAMessageConstants.contact.equals(tableFlag)) {
				contractVO.setMessageExempted(rs.getString(9));
			} else {
				contractVO.setExchangeIndicator(rs.getString(9));
			}
			contractVO.setUserId(rs.getString(10));
			contractVO.setCreatedTimeStamp(rs.getTimestamp(11));
			contractVO.setLstChgUserId(rs.getString(12));
			contractVO.setLstChgTimestamp(rs.getTimestamp(13));
			return contractVO;
		}
	}

	/**
	 * This method is used to generate the excel report for ooa message
	 * 
	 * @return List - java.util.List Object holding ooa message details
	 */
	@Override
	public List<MaintainOOAMessageRequest> getAllRecordsForExcelReport(
			String tableName, String columnName, String msgFlag,
			String exportType) {

		this.tableFlag = exportType;
		StringBuffer contractRetrieveQuery = new StringBuffer();

		contractRetrieveQuery
				.append("SELECT "
						+ columnName
						+ ", MSG_EFFCTV_DT, MSG_EXPRY_DT,MSG_STTS_CD,AUDIT_COMMENTS,MSG_TXT_1,MSG_TXT_2,MSG_TXT_3, "
						+ msgFlag + ",CREATD_USR_ID,CREATD_TM_STMP,LST_CHG_USR,LST_CHG_TMS FROM " + tableName
						+ "  order by "+ columnName + "");

		RetrieveContractData retrieveForDeleteQuery = new RetrieveContractData(
				getDataSource(), contractRetrieveQuery.toString());
		List<MaintainOOAMessageRequest> contractData = retrieveForDeleteQuery
				.execute();

		return contractData;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaintainOOAMessageRequest> getOOAMessagesForDuplicateDatesCheck(
			MaintainOOAMessageRequest maintainOOAMessageRequest) {
		List<MaintainOOAMessageRequest> OOAMessageSearchDetails;
		StringBuffer searchQuery = new StringBuffer();

		if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase("Network")) {

			searchQuery
					.append("SELECT NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, NTWRK_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3,SND_TO_TEST_IND,SND_TO_PROD_IND FROM BX_OOA_NTWRK_MSG_TXT WHERE NTWRK_ID  ='"
							+ maintainOOAMessageRequest.getNetworkOrContractCode()+ "' and EXCHNG_IND='"+maintainOOAMessageRequest.getExchangeIndicator()+"' and NTWRK_MSG_SYS_ID <>'"+ maintainOOAMessageRequest.getMessageId()+ "' and  (MSG_STTS_CD <> 'DELETED' and MSG_STTS_CD <> 'MARKED_FOR_DELETION') ");
			RetrieveEBXOOAMessageSearchQueryForNetworkId retrieveEBXOOAMessageSearchQueryForNetworkId = new RetrieveEBXOOAMessageSearchQueryForNetworkId(
					dataSource, searchQuery.toString());
			OOAMessageSearchDetails = (List<MaintainOOAMessageRequest>) retrieveEBXOOAMessageSearchQueryForNetworkId
					.execute();
		} else {
			searchQuery
					.append("SELECT CNTRCT_ID, MSG_EXMPT_FLG, MSG_EFFCTV_DT, MSG_EXPRY_DT, MSG_STTS_CD, CNTRCT_MSG_SYS_ID, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3,SND_TO_TEST_IND, SND_TO_PROD_IND FROM BX_OOA_CNTRCT_MSG_TXT  WHERE CNTRCT_ID ='"
							+ maintainOOAMessageRequest.getNetworkOrContractCode()+ "' and CNTRCT_MSG_SYS_ID <>'"+ maintainOOAMessageRequest.getMessageId()+ "'  and   (MSG_STTS_CD <> 'DELETED' and MSG_STTS_CD <> 'MARKED_FOR_DELETION') ");

			RetrieveEBXOOAMessageSearchQueryForContractCode retrieveEBXOOAMessageSearchQueryForContractCode = new RetrieveEBXOOAMessageSearchQueryForContractCode(
					dataSource, searchQuery.toString());
			OOAMessageSearchDetails = (List<MaintainOOAMessageRequest>) retrieveEBXOOAMessageSearchQueryForContractCode
					.execute();
		}

		return OOAMessageSearchDetails;
	}


}
