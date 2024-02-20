package com.wellpoint.ets.bx.mapping.domain.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.OOAMessageMappingVO;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

public class BXOutboundDataFeedHelperRepositoryImpl implements
		BXOutboundDataFeedHelperRepository {

	private String insertDeltaNAMappingSQL;
	private DataSource dataSource;
	private final String ID_SPS = "S";
	private final String ID_RULE = "R";
	private final String ID_VARIABLE = "V";

	@SuppressWarnings("rawtypes")
	public boolean deleteDeltaNotApplicableMapping(List mappingsList,
			String itemIdentifier) {
		int rowsAffected = 0;
		if (null != mappingsList && !mappingsList.isEmpty()) {
			String type = this.findMappingType(itemIdentifier);

			List<String> deletionList = new ArrayList<String>();
			Iterator iterator = mappingsList.iterator();
			while (iterator.hasNext()) {
				Mapping mapping = (Mapping) iterator.next();
				String id = this.findMappingId(mapping, type);
				deletionList.add(id);
			}
			String deletionItemsInCSV = BxUtil
					.convertArrayToCSVwithSingleQuote(deletionList);
			String query = "DELETE FROM DELTA_BX_NA_OUTBOUND where TYPE='"
					+ type + "' and ID in (" + deletionItemsInCSV + ")";

			DeleteDeltaNAMappings deleteDeltaNAMappings = new DeleteDeltaNAMappings(
					dataSource, query);
			rowsAffected = deleteDeltaNAMappings.update();
		}
		return true ? rowsAffected > 0 : false;
	}

	public boolean insertDeltaNotApplicableMapping(Mapping mapping,
			String itemIdentifier) {
		InsertDeltaNAMapping insertDeltaNAMapping = new InsertDeltaNAMapping(
				dataSource, getInsertDeltaNAMappingSQL());
		String type = this.findMappingType(itemIdentifier);
		String id = this.findMappingId(mapping, type);

		int rowsAffected = insertDeltaNAMapping.update(new Object[] { id, type,
				"N", "N" });
		return true ? rowsAffected > 0 : false;
	}

	public List<OOAMessageMappingVO> retrieveDeltaNotApplicableMappingforDatafeed(
			String status, String itemIdentifier) {
		String type = this.findMappingType(itemIdentifier);
		String locateQuery = "select ID from DELTA_BX_NA_OUTBOUND where TYPE='"
				+ type + "'";
		if (status.equalsIgnoreCase("SCHEDULED_TO_TEST")) {
			locateQuery += " and TEST_INDICATOR = 'N'";
		} else if (status.equalsIgnoreCase("SCHEDULED_TO_PRODUCTION")) {
			locateQuery += " and PROD_INDICATOR='N'";
		} else {
			throw new DomainException("Invalid Status.");
		}

		RetrieveDeltaNAMapping retrieveDeltaNAMapping = new RetrieveDeltaNAMapping(
				dataSource, locateQuery);
		List<OOAMessageMappingVO> notApplicableList = retrieveDeltaNAMapping
				.execute();

		return notApplicableList;
	}

	@SuppressWarnings("rawtypes")
	public boolean updateDeltaNotApplicableMappingDetails(List mappingIdList,
			String itemIdentifier, String status) {

		int rowsAffected = 0;
		if (null != mappingIdList && !mappingIdList.isEmpty()) {
			String query = "UPDATE DELTA_BX_NA_OUTBOUND SET ";

			if (status.equalsIgnoreCase("SCHEDULED_TO_TEST")) {
				query += " TEST_INDICATOR = 'Y' ";
			} else if (status.equalsIgnoreCase("SCHEDULED_TO_PRODUCTION")) {
				query += " PROD_INDICATOR='Y' ";
			} else {
				throw new DomainException("Invalid Status.");
			}
			rowsAffected = this.updateDeltaNAMappings(query, mappingIdList);

		}

		return true ? rowsAffected > 0 : false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private int updateDeltaNAMappings(String query, List mappingIdList) {
		int rowsAffected = 0;
		List<String> mappingList;
		String updateQuery;
		Iterator iterator = BxUtil.splitListByTargetSize(mappingIdList)
				.iterator();
		while (iterator.hasNext()) {
			mappingList = (List<String>) iterator.next();
			String idInCSV = BxUtil
					.convertArrayToCSVwithSingleQuote(mappingList);
			if (null != idInCSV && idInCSV.trim().length() != 0) {
				updateQuery = query + "	WHERE ID in (" + idInCSV + ") ";
				UpdateDeltaNAMappings updateDeltaNAMappings = new UpdateDeltaNAMappings(
						dataSource, updateQuery);
				rowsAffected += updateDeltaNAMappings.update();
			}
		}
		return rowsAffected;
	}

	public boolean deleteProcessedDeltaMappings(String itemIdentifier) {
		String type = this.findMappingType(itemIdentifier);
		String query = "DELETE FROM DELTA_BX_NA_OUTBOUND where TYPE='" + type
				+ "' and TEST_INDICATOR = 'Y' and PROD_INDICATOR='Y'";
		DeleteDeltaNAMappings deleteDeltaNAMappings = new DeleteDeltaNAMappings(
				dataSource, query);
		int rowsAffected = deleteDeltaNAMappings.update();
		return true ? rowsAffected > 0 : false;
	}

	private String findMappingId(Mapping mapping, String mappingType) {
		String mappingId = "";
		if (this.ID_SPS.equals(mappingType) && null != mapping.getSpsId()) {
			mappingId = mapping.getSpsId().getSpsId();
		}
		if (this.ID_RULE.equals(mappingType) && null != mapping.getRule()) {
			mappingId = mapping.getRule().getHeaderRuleId();
		}
		if (this.ID_VARIABLE.equals(mappingType)
				&& null != mapping.getVariable()) {
			mappingId = mapping.getVariable().getVariableId();
		}
		return mappingId;
	}

	private String findMappingType(String itemIdentifier) {
		String mappingType = "";
		if (DomainConstants.SPS_IDENTIFIER.equals(itemIdentifier)) {
			mappingType = ID_SPS;
		}
		if (DomainConstants.HEADERRULE_IDENTIFIER.equals(itemIdentifier)) {
			mappingType = ID_RULE;
		}
		if (DomainConstants.VARIABLE_IDENTIFIER.equals(itemIdentifier)) {
			mappingType = ID_VARIABLE;
		}
		return mappingType;
	}

	private final class UpdateDeltaNAMappings extends SqlUpdate {

		private UpdateDeltaNAMappings(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}

	private final class InsertDeltaNAMapping extends SqlUpdate {
		private InsertDeltaNAMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter("ID", Types.VARCHAR));
			declareParameter(new SqlParameter("TYPE", Types.CHAR));
			declareParameter(new SqlParameter("TEST_INDICATOR", Types.CHAR));
			declareParameter(new SqlParameter("PROD_INDICATOR", Types.CHAR));
			compile();
		}
	}

	private final class DeleteDeltaNAMappings extends SqlUpdate {

		private DeleteDeltaNAMappings(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}

	private class RetrieveDeltaNAMapping extends MappingSqlQuery {
		public RetrieveDeltaNAMapping(DataSource ds, String query) {
			super(ds, query);
			compile();
		}

		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			return resultSet.getString("ID");
		}
	}

	public String getInsertDeltaNAMappingSQL() {
		return insertDeltaNAMappingSQL;
	}

	public void setInsertDeltaNAMappingSQL(String insertDeltaNAMappingSQL) {
		this.insertDeltaNAMappingSQL = insertDeltaNAMappingSQL;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.domain.service.
	 * BXOutboundDataFeedHelperRepository
	 * #getOOAMessageMappings(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<OOAMessageMappingVO> getOOAMessageMappings(String status,
			String entityName, String recordType) {

		String getMessageMappingsQuery = null;
		String testProdIndJoinDelete = null;
		String testProdIndJoinActive = null;

		if (status.equalsIgnoreCase("SCHEDULED_TO_TEST")) {

			testProdIndJoinDelete = "WHERE MSG_STTS_CD = 'MARKED_FOR_DELETION' AND SND_TO_TEST_IND = 'Y' ";
			testProdIndJoinActive = "SND_TO_TEST_IND";

		} else if (status.equalsIgnoreCase("SCHEDULED_TO_PRODUCTION")) {

			testProdIndJoinDelete = "WHERE MSG_STTS_CD = 'DELETED' AND SND_TO_PROD_IND = 'Y' ";
			testProdIndJoinActive = "SND_TO_PROD_IND";
		}

		if (entityName.equals("contract")) {

			if (recordType.equals("deleted")) {

				getMessageMappingsQuery = "SELECT CNTRCT_MSG_SYS_ID MSG_SYS_ID, CNTRCT_ID ENTITY_ID, '' EXCHNG_IND, TO_CHAR(MSG_EFFCTV_DT,'YYYYMMDD') MSG_EFFCTV_DT, TO_CHAR(MSG_EXPRY_DT,'YYYYMMDD') MSG_EXPRY_DT, MSG_EXMPT_FLG, "
						+ "'D' MSG_CHANGE_IND, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3 FROM BX_OOA_CNTRCT_MSG_TXT "
						+ testProdIndJoinDelete
						+ "ORDER BY CNTRCT_ID, MSG_EFFCTV_DT";

			} else if (recordType.equals("active")) {

				getMessageMappingsQuery = "SELECT CNTRCT_MSG_SYS_ID MSG_SYS_ID, CNTRCT_ID ENTITY_ID, '' EXCHNG_IND, TO_CHAR(MSG_EFFCTV_DT,'YYYYMMDD') MSG_EFFCTV_DT, TO_CHAR(MSG_EXPRY_DT,'YYYYMMDD') MSG_EXPRY_DT, MSG_EXMPT_FLG, "
						+ "DECODE("
						+ testProdIndJoinActive
						+ ", 'Y', 'U', 'N', 'A') MSG_CHANGE_IND, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3 FROM BX_OOA_CNTRCT_MSG_TXT "
						+ "WHERE MSG_STTS_CD   = '"
						+ status
						+ "' ORDER BY CNTRCT_ID, MSG_EFFCTV_DT";

			} else {
				throw new DomainException(
						"Invalid Record Type passed to the query.");
			}

		} else if (entityName.equals("network")) {

			if (recordType.equals("deleted")) {

				getMessageMappingsQuery = "SELECT NTWRK_MSG_SYS_ID MSG_SYS_ID, NTWRK_ID ENTITY_ID, EXCHNG_IND, TO_CHAR(MSG_EFFCTV_DT,'YYYYMMDD') MSG_EFFCTV_DT, TO_CHAR(MSG_EXPRY_DT,'YYYYMMDD') MSG_EXPRY_DT, '' MSG_EXMPT_FLG, "
						+ "'D' MSG_CHANGE_IND, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3 FROM BX_OOA_NTWRK_MSG_TXT "
						+ testProdIndJoinDelete
						+ "ORDER BY NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT";

			} else if (recordType.equals("active")) {

				getMessageMappingsQuery = "SELECT NTWRK_MSG_SYS_ID MSG_SYS_ID,NTWRK_ID ENTITY_ID, EXCHNG_IND, TO_CHAR(MSG_EFFCTV_DT,'YYYYMMDD') MSG_EFFCTV_DT, TO_CHAR(MSG_EXPRY_DT,'YYYYMMDD') MSG_EXPRY_DT, '' MSG_EXMPT_FLG, "
						+ "DECODE("
						+ testProdIndJoinActive
						+ ", 'Y', 'U', 'N', 'A') MSG_CHANGE_IND, MSG_TXT_1, MSG_TXT_2, MSG_TXT_3 FROM BX_OOA_NTWRK_MSG_TXT "
						+ "WHERE MSG_STTS_CD   = '"
						+ status
						+ "' ORDER BY NTWRK_ID, EXCHNG_IND, MSG_EFFCTV_DT";

			} else {
				throw new DomainException(
						"Invalid Record Type passed to the query.");
			}

		} else {
			throw new DomainException(
					"Invalid Entity Name passed to the query.");
		}

		System.out.println("Query for getting " + recordType
				+ " OOA message mappings for " + entityName + " is "
				+ getMessageMappingsQuery);

		RetrieveOOAMessageMappings retrieveOOAMessageMappings = new RetrieveOOAMessageMappings(
				dataSource, getMessageMappingsQuery);
		List<OOAMessageMappingVO> OOAMessageRecords = retrieveOOAMessageMappings
				.execute();

		return OOAMessageRecords;
	}

	private class RetrieveOOAMessageMappings extends MappingSqlQuery {
		public RetrieveOOAMessageMappings(DataSource ds, String query) {
			super(ds, query);
			compile();
		}

		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			OOAMessageMappingVO OOAMessageMapping = new OOAMessageMappingVO();
			OOAMessageMapping
					.setMessageSystemId(resultSet.getInt("MSG_SYS_ID"));
			OOAMessageMapping.setMessageEntityId(resultSet
					.getString("ENTITY_ID"));
			OOAMessageMapping.setMessageExchangeInd(resultSet
					.getString("EXCHNG_IND"));
			OOAMessageMapping.setMessageEffectiveDate(resultSet
					.getString("MSG_EFFCTV_DT"));
			OOAMessageMapping.setMessageExpiryDate(resultSet
					.getString("MSG_EXPRY_DT"));
			OOAMessageMapping.setMessageExemptInd(resultSet
					.getString("MSG_EXMPT_FLG"));
			OOAMessageMapping.setMessageChangeInd(resultSet
					.getString("MSG_CHANGE_IND"));
			OOAMessageMapping.setMessageText1(resultSet.getString("MSG_TXT_1"));
			OOAMessageMapping.setMessageText2(resultSet.getString("MSG_TXT_2"));
			OOAMessageMapping.setMessageText3(resultSet.getString("MSG_TXT_3"));
			return OOAMessageMapping;
		}
	}

	public boolean updateOOAMappingStatus(String status, String entityName,
			String recordType, List<Integer> OOAMappingSysIds) {

		HashMap<Integer, String> OOAMappingSysIdMap = BxUtil
				.getListAsCSVMap(OOAMappingSysIds);

		String mappingSysIdList = null;
		String updateMessageMappingsQuery = null;
		String deleteRecordJoin = null;
		String deleteTestProdInd = null;
		String activeRecordJoin = null;
		String tableName = null;
		String sysIdColumnName = null;

		if (status.equalsIgnoreCase("SCHEDULED_TO_TEST")) {

			deleteRecordJoin = " SET LST_CHG_USR = 'DATAFEED', LST_CHG_TMS =  SYSDATE, SND_TO_TEST_IND = 'T', MSG_STTS_CD = 'DELETED', AUDIT_COMMENTS = '[DATAFEED '|| TO_CHAR(SYSDATE, 'mm/dd/yyyy HH24:MI')||'] DELETE RECORD SEND TO WGS TEST.' || CHR(10) || AUDIT_COMMENTS WHERE MSG_STTS_CD = 'MARKED_FOR_DELETION' AND ";
			deleteTestProdInd = "SND_TO_TEST_IND";

			activeRecordJoin = " SET LST_CHG_USR = 'DATAFEED', LST_CHG_TMS =  SYSDATE, SND_TO_TEST_IND = 'Y', MSG_STTS_CD = '"
					+ DomainConstants.STATUS_OBJECT_TRANSFERRED
					+ "', AUDIT_COMMENTS = '[DATAFEED '|| TO_CHAR(SYSDATE, 'mm/dd/yyyy HH24:MI')||'] OOA MAPPING RECORD SEND TO WGS TEST.' || CHR(10) || AUDIT_COMMENTS";

		} else if (status.equalsIgnoreCase("SCHEDULED_TO_PRODUCTION")) {

			deleteRecordJoin = " SET LST_CHG_USR = 'DATAFEED', LST_CHG_TMS =  SYSDATE, SND_TO_PROD_IND = 'P', MSG_STTS_CD = 'DELETED', AUDIT_COMMENTS = '[DATAFEED '|| TO_CHAR(SYSDATE, 'mm/dd/yyyy HH24:MI')||'] DELETE RECORD SEND TO WGS PROD.' || CHR(10) || AUDIT_COMMENTS WHERE MSG_STTS_CD = 'DELETED' AND ";
			deleteTestProdInd = "SND_TO_PROD_IND";
			activeRecordJoin = " SET LST_CHG_USR = 'DATAFEED', LST_CHG_TMS =  SYSDATE, SND_TO_PROD_IND = 'Y', MSG_STTS_CD = '"
					+ DomainConstants.STATUS_PUBLISHED
					+ "', AUDIT_COMMENTS = '[DATAFEED '|| TO_CHAR(SYSDATE, 'mm/dd/yyyy HH24:MI')||'] OOA MAPPING RECORD SEND TO WGS PROD.' || CHR(10) || AUDIT_COMMENTS";
		}

		if (entityName.equals("contract")) {

			tableName = "BX_OOA_CNTRCT_MSG_TXT";
			sysIdColumnName = "CNTRCT_MSG_SYS_ID";

		} else if (entityName.equals("network")) {

			tableName = "BX_OOA_NTWRK_MSG_TXT";
			sysIdColumnName = "NTWRK_MSG_SYS_ID";

		} else {
			throw new DomainException(
					"Invalid Entity Name passed to the query.");
		}

		if (recordType.equals("deleted")) {

			for (int i = 1; i <= OOAMappingSysIdMap.size(); i++) {

				mappingSysIdList = OOAMappingSysIdMap.get(i);

				updateMessageMappingsQuery = "UPDATE " + tableName
						+ deleteRecordJoin + deleteTestProdInd + " = 'Y' AND "
						+ sysIdColumnName + " IN (" + mappingSysIdList + ")";
				
				updateMessageMappings(recordType, entityName, updateMessageMappingsQuery);
			}

		} else if (recordType.equals("active")) {

			for (int i = 1; i <= OOAMappingSysIdMap.size(); i++) {

				mappingSysIdList = OOAMappingSysIdMap.get(i);

				updateMessageMappingsQuery = "UPDATE " + tableName
						+ activeRecordJoin + " WHERE MSG_STTS_CD = '" + status
						+ "' AND " + sysIdColumnName + " IN ("
						+ mappingSysIdList + ")";
				updateMessageMappings(recordType, entityName, updateMessageMappingsQuery);
			}

		} else {
			throw new DomainException(
					"Invalid Record Type passed to the query.");
		}

		return true;
	}
	
	private void updateMessageMappings(String recordType, String entityName,
			String updateMessageMappingsQuery) {

		System.out.println("Query for updating " + recordType
				+ " OOA message mappings for " + entityName);
		System.out.println(updateMessageMappingsQuery);

		UpdateOOAMessageMappings updateOOAMessageMappings = new UpdateOOAMessageMappings(
				dataSource, updateMessageMappingsQuery);
		updateOOAMessageMappings.update();
	}
	
	private final class UpdateOOAMessageMappings extends SqlUpdate {

		private UpdateOOAMessageMappings(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}
}
