package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.Eb03CodeValueVO;
import com.wellpoint.ets.bx.mapping.repository.SequenceGeneratorRepositoryImpl.SequenceQuery;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class UmRuleMappingRepositoryImpl implements UmRuleMappingRepository {

	private DataSource dataSource;
	private SequenceQuery sequenceQuery;
	private SequenceGeneratorRepository sequenceGeneratorRepository;
	private static Logger log = Logger.getLogger(UmRuleMappingRepositoryImpl.class);
	private CreateRuleMappingMasterTableQuery createRuleMappingMasterTableQuery;
	private CreateRuleMappingAssociationTableQuery createRuleMappingAssociationTableQuery;

	private EditRuleMappingAssociationTableQuery editRuleMappingAssociationTableQuery;

	private CreateRuleMappingAuditTableQuery createRuleMappingAuditTableQuery;

	private CreateEB03ExclusionTableQuery createEb03ExclusionTableQuery;

	private CreateEB03DefaultTableQuery createEb03DefaultTableQuery;

	private EditRuleMappingMasterTableQuery editRuleMappingMasterTableQuery;
	
	private CreateLogTableQuery createLogTableQuery;

	public static final String masterTbleSeqColName = "SPDR_UM_MAPG_SYS_ID_SEQ";

	public static final String childTbleSeqColName = "SPDR_UM_ASOC_SYS_ID_SEQ";

	public static final String auditTbleSeqColName = "SPDR_UM_MAP_AUDIT_SYS_ID_SEQ";

	public static final String eb03TbleSeqColName = "SPDR_EB03_EXLSION_SYS_ID_SEQ";

	public static final String eb03DefaultTbleSeqColName = "SPDR_EB03_DEFLT_SYS_ID_SEQ";

	private Long mstrTbleSysID;

	private Long childTbleSysID;

	private Long auditTbleSysID;

	private Long eb03TbleSysID;
	
	private Long ignrTbleSysID;
	
	private Long logTbleSysID;
	
	public static final String ignoreTbleSeqColName= "SPDR_UM_EXCL_SYS_ID_SEQ";
	
	public static final String logTbleSeqColName = "SPDR_LOG_SYS_ID_SEQ";

	private StringBuffer childTbleInsertQuery;

	private StringBuffer auditTbleInsertQuery;

	private int sequenceNo = 0;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SequenceGeneratorRepository getSequenceGeneratorRepository() {
		return sequenceGeneratorRepository;
	}

	public void setSequenceGeneratorRepository(SequenceGeneratorRepository sequenceGeneratorRepository) {
		this.sequenceGeneratorRepository = sequenceGeneratorRepository;
	}

	private class IsValidUmRuleChkQuery extends MappingSqlQuery {

		public IsValidUmRuleChkQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMapping result = new SpiderUMRuleMapping();
			result.setUmRuleId(rs.getString("MDCL_RULE_CD"));
			return result;
		}

	}

	public int isValidRuleID(String ruleID) {
		int statusCode = 0;

		final String isValidUmRuleChkQuery = " SELECT DISTINCT RMADB.MDCLRULE.MDCL_RULE_CD,RMADB.MDCLRULE.MDCL_RULE_desc FROM "
				+ " (SELECT RMADB.MDCLRULE.MDCL_RULE_ID AS RULEID, MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR) AS RVERSION "
				+ " FROM RMADB.MDCL_RULE MDCLRULE LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
				+ " ON ( RMADB.MDCLRULE.MDCL_RULE_ID = RMADB.MDCLRULEVRSN.MDCL_RULE_ID) "
				+ " WHERE RMADB.MDCLRULE.MDCL_RULE_CD = '" + ruleID.toUpperCase()
				+ "' AND RMADB.MDCLRULE.MDCL_RULE_TYPE_CD ='UMRULE' "
				+ " GROUP BY RMADB.MDCLRULE.MDCL_RULE_ID ) masterInfo LEFT OUTER JOIN RMADB.MDCL_RULE MDCLRULE "
				+ " ON (RMADB.MDCLRULE.MDCL_RULE_ID = masterInfo.RULEID) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
				+ " ON ( RMADB.MDCLRULEVRSN.MDCL_RULE_ID = masterInfo.RULEID "
				+ " AND RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN_TAG TAG "
				+ " ON(TAG.MDCL_RULE_ID = masterInfo.RULEID AND TAG.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) "
				+ " ORDER BY RMADB.MDCLRULE.MDCL_RULE_CD ";

		IsValidUmRuleChkQuery validVarChkQuery = new IsValidUmRuleChkQuery(getDataSource(), isValidUmRuleChkQuery);
		List noOfValidRecords = null;
		noOfValidRecords = validVarChkQuery.execute();
		if (null == noOfValidRecords || noOfValidRecords.isEmpty()) {

			statusCode = 0; // RULE ID DOES NOT EXIST IN LEGACY
		} else {
			statusCode = 1;
		}

		return statusCode;
	}

	public List retrieveRuleIdInfo(String ruleId) {
		StringBuffer retrieveVarInfoforCreateQuery = new StringBuffer();
		if (null != ruleId && !"".equals(ruleId)) {
			retrieveVarInfoforCreateQuery
					.append(" SELECT DISTINCT RMADB.MDCLRULE.MDCL_RULE_CD,RMADB.MDCLRULE.MDCL_RULE_desc FROM "
							+ " (SELECT RMADB.MDCLRULE.MDCL_RULE_ID AS RULEID, MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR) AS RVERSION "
							+ " FROM RMADB.MDCL_RULE MDCLRULE LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
							+ " ON ( RMADB.MDCLRULE.MDCL_RULE_ID = RMADB.MDCLRULEVRSN.MDCL_RULE_ID) "
							+ " WHERE RMADB.MDCLRULE.MDCL_RULE_CD = '" + ruleId.toUpperCase()
							+ "' AND RMADB.MDCLRULE.MDCL_RULE_TYPE_CD ='UMRULE' "
							+ " GROUP BY RMADB.MDCLRULE.MDCL_RULE_ID ) masterInfo LEFT OUTER JOIN RMADB.MDCL_RULE MDCLRULE "
							+ " ON (RMADB.MDCLRULE.MDCL_RULE_ID = masterInfo.RULEID) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
							+ " ON ( RMADB.MDCLRULEVRSN.MDCL_RULE_ID = masterInfo.RULEID "
							+ " AND RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN_TAG TAG "
							+ " ON(TAG.MDCL_RULE_ID = masterInfo.RULEID AND TAG.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) ");

		}
		RetrieveRuleIdForCreateQuery retrieveVarInfoQuery = new RetrieveRuleIdForCreateQuery(getDataSource(),
				retrieveVarInfoforCreateQuery.toString());
		List variableWithInfoList = retrieveVarInfoQuery.execute();
		if (null != variableWithInfoList && variableWithInfoList.size() > 0) {
			log.debug("*****************variableList size : " + variableWithInfoList.size());
			return variableWithInfoList;
		}
		return null;

	}

	private class RetrieveRuleIdForCreateQuery extends MappingSqlQuery {

		public RetrieveRuleIdForCreateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleId(rs.getString("MDCL_RULE_CD"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("MDCL_RULE_DESC"));
			spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_BUILDING);

			return spiderUMRuleMapping;
		}
	}

	public boolean persistNewRuleIdMapping(List<SpiderUMRuleMappingData> spiderList, SpiderUMRuleMapping spiderMaster) {
		mstrTbleSysID = sequenceGeneratorRepository.generateSequence(masterTbleSeqColName);
		try {

			if (spiderMaster != null && !spiderList.isEmpty()) {
				createRuleMappingMasterTableQuery = new CreateRuleMappingMasterTableQuery(getDataSource());

				createRuleMappingMasterTableQuery.insert(spiderMaster);

				persistChildTable(spiderList, mstrTbleSysID);
				persistNewLogForMapping(spiderMaster,mstrTbleSysID);
				// persistAuditTable(spiderList,spiderMaster,mstrTbleSysID);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return true;
		}
	}

	protected class CreateRuleMappingMasterTableQuery extends SqlUpdate {
		protected CreateRuleMappingMasterTableQuery(DataSource ds) {
			super(ds,
					" Insert into BX_SPDR_UM_MAPG (SPDR_UM_MAPG_SYS_ID,UM_RULE_ID,UM_RULE_ID_DESCRIPTION,VERSION, "
							+ "COMMENTS,STATUS,CREATED_USER,CREATED_DATE, "
							+ "LAST_UPDT_USER,LAST_UPDT_DATE) values (?,?,?,?, " + " ?,?,?,sysdate," + "?,sysdate)");

			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));

			compile();
		}

		protected void insert(SpiderUMRuleMapping spiderUMRuleMapping) {

			Object[] objs = new Object[] { mstrTbleSysID, spiderUMRuleMapping.getUmRuleId().toUpperCase(),
					spiderUMRuleMapping.getUmRuleDesc().toUpperCase(), spiderUMRuleMapping.getDefaultVersion(),
					spiderUMRuleMapping.getComment(), spiderUMRuleMapping.getUmStatus().toUpperCase(),
					spiderUMRuleMapping.getUser().getCreatedUserName(),
					spiderUMRuleMapping.getUser().getLastUpdatedUserName()

			};
			super.update(objs);
			/**
			 * end
			 */
		}
	}

	public boolean persistEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList,
			SpiderUMRuleMapping spiderMaster) {
		// mstrTbleSysID =
		// sequenceGeneratorRepository.generateSequence(masterTbleSeqColName);
		try {

			if (spiderMaster != null && !spiderList.isEmpty()) {

				editRuleMappingMasterTableQuery = new EditRuleMappingMasterTableQuery(getDataSource());

				editRuleMappingMasterTableQuery.insert(spiderMaster);

				List<SpiderUMRuleMappingData> newMappingData = new ArrayList<SpiderUMRuleMappingData>();
				List<SpiderUMRuleMappingData> existingMappingData = new ArrayList<SpiderUMRuleMappingData>();
				spiderList.stream().forEach(mapping -> {

					if (mapping.getUmRuleMappingSystemId() == null) {
						newMappingData.add(mapping);
					} else {
						existingMappingData.add(mapping);
					}
				});
				if (!newMappingData.isEmpty()) {
					persistChildTable(newMappingData,
							Long.parseLong(String.valueOf(spiderMaster.getUmRuleIdSystemId())));
				}
				if (!existingMappingData.isEmpty()) {
					persistEditChildTable(spiderList);
				}
				String id  = (spiderMaster.getUmRuleIdSystemId()).toString();
				Long sysId= Long.valueOf(id);
				persistNewLogForMapping(spiderMaster,sysId); // for Log table

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return true;
		}
	}

	// "UPDATE record SET title = ?, release_date = ? WHERE id = ?";

	protected class EditRuleMappingMasterTableQuery extends SqlUpdate {
		protected EditRuleMappingMasterTableQuery(DataSource ds) {
			super(ds,
					"Update BX_SPDR_UM_MAPG set COMMENTS=?,VERSION=?,STATUS=?,LAST_UPDT_USER=?,LAST_UPDT_DATE=sysdate WHERE SPDR_UM_MAPG_SYS_ID=? ");

			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(SpiderUMRuleMapping spiderUMRuleMapping) {

			System.out.println("UM rule id" + spiderUMRuleMapping.getUmRuleId());

			Object[] objs = new Object[] { spiderUMRuleMapping.getComment(),
					spiderUMRuleMapping.getDefaultVersion(), spiderUMRuleMapping.getUmStatus().toUpperCase(),
					spiderUMRuleMapping.getUser().getCreatedUserName().toUpperCase(),
					spiderUMRuleMapping.getUmRuleIdSystemId() };
			super.update(objs);
			/**
			 * end
			 */
		}
	}

	protected class EditRuleMappingAuditTableQuery extends SqlUpdate {
		protected EditRuleMappingAuditTableQuery(DataSource ds) {
			super(ds, "Update  BX_SPD_UM_MAPG_AUDIT SET  SPDR_UM_MAPG_SYS_ID=?,UM_EB03=?,UM_EB03_DEFAULT=?,"
					+ "UM_MAPG_MSG=?,VERSION=?,LAST_UPDT_USER=?,LAST_UPDT_DATE=sysdate  WHERE SPDR_UM_MAPPING_AUDIT_SYS_ID=?");

			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));

			compile();
		}

		protected void insert(SpiderUMRuleMappingDataAudit spiderUMRuleMapping) {

			Object[] objs = new Object[] { spiderUMRuleMapping.getUmRuleSystemId(),
					spiderUMRuleMapping.getUmEB03().toUpperCase(), spiderUMRuleMapping.getUmEB03Default().toUpperCase(),
					spiderUMRuleMapping.getUmMessage().toUpperCase(), spiderUMRuleMapping.getVersion(),
					spiderUMRuleMapping.getLastModifiedBy(), spiderUMRuleMapping.getUmRuleMappingAuditSystemId()

			};
			super.update(objs);
			/**
			 * end
			 */
		}
	}

	/*
	 * protected class CreateRuleMappingAuditTableQuery extends SqlUpdate {
	 * protected CreateRuleMappingAuditTableQuery(DataSource ds) { super(ds,
	 * "Insert into BX_SPD_UM_MAPG_AUDIT (SPDR_UM_MAPPING_AUDIT_SYS_ID,SPDR_UM_MAPG_SYS_ID,UM_RULE_ID,UM_EB03,UM_EB03_DEFAULT,"
	 * + "UM_MAPG_MSG,VERSION,CREATED_USER,CREATED_DATE," +
	 * "LAST_UPDT_USER,LAST_UPDT_DATE) values (?,?,?," + "?,?,?,sysdate," +
	 * "?,sysdate)");
	 * 
	 * declareParameter(new SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); declareParameter(new
	 * SqlParameter(Types.VARCHAR)); compile(); }
	 * 
	 * protected void insert(SpiderUMRuleMappingDataAudit spiderUMRuleMapping) {
	 * 
	 * Object[] objs = new Object[] { spiderUMRuleMapping.getUmRuleSystemId(),
	 * spiderUMRuleMapping.getUmRuleMappingAuditSystemId(),
	 * spiderUMRuleMapping.getUmEB03().toUpperCase(),
	 * spiderUMRuleMapping.getUmEB03Default().toUpperCase(),
	 * spiderUMRuleMapping.getUmMessage().toUpperCase(),
	 * spiderUMRuleMapping.getVersion(), spiderUMRuleMapping.getCreatedBy(),
	 * spiderUMRuleMapping.getLastModifiedBy()
	 * 
	 * }; super.update(objs);
	 *//**
		 * end
		 *//*
		 * } }
		 */

	public List findAllRuleDescriptionStartingWith(String ruleIdDescription, int noOfRecords) {
		List allRules = new ArrayList();
		if (ruleIdDescription == null || "".equals(ruleIdDescription.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		ruleIdDescription = ruleIdDescription.trim();

		String query = " select UM_RULE_ID,UM_RULE_ID_DESCRIPTION from BX_SPDR_UM_MAPG where UM_RULE_ID_DESCRIPTION LIKE '"
				+ ruleIdDescription + "%' ";

		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query += " and rownum < " + noOfRecords + " ";
		}

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UnmappedRuleLocateQuery varLocQry = new UnmappedRuleLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	public List findAllRuleStartingWith(String ruleId, int noOfRecords) {
		List allRules = new ArrayList();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		
		//    " HAVING RMADB.MDCLRULE.MDCL_RULE_CD NOT IN (Select EXCLN.UM_RULE_ID from BX_SPDR_UM_MAPG_EXCL EXCLN WHERE MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR)<=EXCLN.VERSION) "+
		
		
		ruleId = ruleId.trim();
		String query = "SELECT DISTINCT RMADB.MDCLRULE.MDCL_RULE_CD,RMADB.MDCLRULE.MDCL_RULE_desc " + " FROM "
				+ " (SELECT RMADB.MDCLRULE.MDCL_RULE_ID AS RULEID, MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR) AS RVERSION "
				+ " FROM " + " RMADB.MDCL_RULE MDCLRULE,RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
				+ " WHERE RMADB.MDCLRULE.MDCL_RULE_CD NOT IN (Select SPDR1.UM_RULE_ID from BX_SPDR_UM_MAPG SPDR1 ) "
				+ " AND RMADB.MDCLRULE.MDCL_RULE_ID = RMADB.MDCLRULEVRSN.MDCL_RULE_ID "
				+ " AND RMADB.MDCLRULEVRSN.CURNT_STTS_CD IN('FINALIZED','TESTING') "
				+ " AND RMADB.MDCLRULE.MDCL_RULE_TYPE_CD ='UMRULE' GROUP BY RMADB.MDCLRULE.MDCL_RULE_ID,RMADB.MDCLRULE.MDCL_RULE_CD "
				//+" HAVING RMADB.MDCLRULE.MDCL_RULE_CD NOT IN (Select EXCLNBX_SPDR_UM_MAPG_EXCL.UM_RULE_ID from  EXCLN WHERE MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR)<=EXCLN.VERSION) "
				+ ") masterInfo, "
				+ " RMADB.MDCL_RULE MDCLRULE,RMADB.MDCL_RULE_VRSN MDCLRULEVRSN,RMADB.MDCL_RULE_VRSN_TAG TAG "
				+ " where RMADB.MDCLRULE.MDCL_RULE_ID = masterInfo.RULEID "
				+ " AND RMADB.MDCLRULEVRSN.MDCL_RULE_ID = masterInfo.RULEID "
				+ " AND RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION "
				+ " AND TAG.MDCL_RULE_ID = masterInfo.RULEID " + " AND TAG.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION "
				+ " AND RMADB.MDCLRULE.MDCL_RULE_CD like '" + ruleId + "%'";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query += " and rownum < " + noOfRecords + " ";
		}

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		
		RetrieveRuleIdForCreateQuery varLocQry = new RetrieveRuleIdForCreateQuery(dataSource,query);
		
		//UnmappedRuleLocateQuery varLocQry = new UnmappedRuleLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	public List getMatchingEb03FromDataDictionaryForAutoComplete(String searchString, int noOfRecordsForPopUp,String searchType) 
	{
		if (null == searchString) {
			searchString = "";
		}

		String query;

		if(searchType.equals("locate")){
			/*query = " select b.data_element_val as \"Value\", "
					+ " b.val_desc_txt as \"Description\" "
					+ " from bx_hippa_segment_val b where b.data_element_id= 'EB03' "
					+ " AND b.data_element_val   IN(SELECT UM_EB03 from BX_SPD_UM_MAPG_ASSOCIATION)  "
					+ " and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
					+ " order by \"Value\" ASC ";
             */			
		query = " select b.data_element_val as \"Value\", "
				 + " b.val_desc_txt as \"Description\",defaultmapping.EB03_DEFAULT as \"DefaultValue\" "
				 + " from bx_hippa_segment_val b left outer join bx_spdr_eb03_default_mapping defaultmapping on(b.data_element_val = defaultmapping.EB03_VALUE)  where b.data_element_id= 'EB03' "
				 + " AND b.data_element_val IN(SELECT UM_EB03 from BX_SPD_UM_MAPG_ASSOCIATION)  "
				 + " and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
				 + " order by \"Value\" ASC ";
		}else{
		query = " select b.data_element_val as \"Value\", "
				+ " b.val_desc_txt as \"Description\" ,defaultmapping.EB03_DEFAULT as \"DefaultValue\" "
				+ " from bx_hippa_segment_val b left outer join bx_spdr_eb03_default_mapping defaultmapping on(b.data_element_val = defaultmapping.EB03_VALUE)  where b.data_element_id= 'EB03' "
				+ " AND b.data_element_val   NOT IN(SELECT EB03_VALUE from BX_SPDR_EB03_EXCLUSION)  "
				+ " and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
				+ " order by \"Value\" ASC ";

		}
		
		
		if (noOfRecordsForPopUp > 0) {
			noOfRecordsForPopUp = noOfRecordsForPopUp + 1;
			query = "select * from (" + query + ") where rownum <" + noOfRecordsForPopUp;
		}
		log.trace("Query in HRepo (find xcept eb01): " + query);
		HippaCodeLocateQuery hippaCodeLocateQuery = new HippaCodeLocateQuery(dataSource, query);// List<HippaCodeValue>
		List matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues && !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for " + searchString.trim() + " is "
					+ matchingHippaCodeValues.size());
			return matchingHippaCodeValues;
		}
		return new ArrayList();
	}

	private class HippaCodeLocateQuery extends MappingSqlQuery {

		private HippaCodeLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.
		 * ResultSet, int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Eb03CodeValueVO hippaCodeValue = new Eb03CodeValueVO();
			hippaCodeValue.setValue(rs.getString("Value"));
			hippaCodeValue.setDescription(rs.getString("Description"));
			hippaCodeValue.setEb03Default(rs.getString("DefaultValue"));

			//log.trace("Desc, Value " + hippaCodeValue.getDescription() + ", " + hippaCodeValue.getValue());
			return hippaCodeValue;
		}
	}

	private class RetrieveRuleIdQuery extends MappingSqlQuery {

		public RetrieveRuleIdQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();
			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("UM_RULE_ID_DESCRIPTION"));
			spiderUMRuleMapping.setUmRuleIdSystemId(rs.getInt("SPDR_UM_MAPG_SYS_ID"));
			// spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_BUILDING);
			spiderUMRuleMapping.setDefaultVersion(rs.getInt("VERSION"));
			spiderUMRuleMapping.setUmStatus(rs.getString("STATUS"));
			spiderUMRuleMapping.setComment(rs.getString("COMMENTS"));
			return spiderUMRuleMapping;
		}
	}

	public SpiderUMRuleMapping findByRuleId(String ruleId) {

		SpiderUMRuleMapping umRuleData = new SpiderUMRuleMapping();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return umRuleData;
		}
		ruleId = ruleId.trim();
		String query = "SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMMAP.SPDR_UM_MAPG_SYS_ID,UMMAP.VERSION,UMMAP.COMMENTS,UMMAP.STATUS FROM BX_SPDR_UM_MAPG UMMAP WHERE UMMAP.UM_RULE_ID='"
				+ ruleId + "'";

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		RetrieveRuleIdQuery varLocQry = new RetrieveRuleIdQuery(dataSource, query);
		umRuleData = (SpiderUMRuleMapping) varLocQry.execute().get(0);
		if (null != umRuleData) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + umRuleData.toString());
		}
		return umRuleData;
	}

	public List findAllUmRuleMappingsDataByRuleId(String ruleSysId) {
		List allRules = new ArrayList();
		if (ruleSysId == null || "".equals(ruleSysId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		ruleSysId = ruleSysId.trim();
		String query = " SELECT UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMAS.SPDR_UM_ASOC_SYS_ID,UMAS.VERSION,UMAS.CREATED_USER,UMAS.CREATED_DATE, "
				+ " UMAS.LAST_UPDT_USER,UMAS.LAST_UPDT_DATE FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS "
				+ " WHERE UMMAP.UM_RULE_ID = '" + ruleSysId
				+ "' AND UMMAP.SPDR_UM_MAPG_SYS_ID = UMAS.SPDR_UM_MAPG_SYS_ID ";

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UMRuleMappingsDataLocateQuery varLocQry = new UMRuleMappingsDataLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	private static class UMRuleMappingsDataLocateQuery extends MappingSqlQuery {

		private UMRuleMappingsDataLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingData spiderUMRuleMapping = new SpiderUMRuleMappingData();
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData
			// spiderUMRuleAssoc = new
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData();

			spiderUMRuleMapping.setUmEB03(rs.getString("UM_EB03"));
			spiderUMRuleMapping.setUmEB03Default(rs.getString("UM_EB03_DEFAULT"));
			spiderUMRuleMapping.setUmMessage(rs.getString("UM_MAPG_MSG"));
			spiderUMRuleMapping.setUmRuleMappingSystemId(rs.getInt("SPDR_UM_ASOC_SYS_ID"));
			spiderUMRuleMapping.setVersion(rs.getInt("VERSION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setLastModifiedDate(rs.getDate("LAST_UPDT_DATE"));
			spiderUMRuleMapping.setCreatedBy(rs.getString("CREATED_USER"));
			spiderUMRuleMapping.setLastModifiedBy(rs.getString("LAST_UPDT_USER"));
			return spiderUMRuleMapping;
		}

	}

	public List findAllUmRuleMappingResultForEdit(String ruleId) {
		List allRules = new ArrayList();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		ruleId = ruleId.trim();
		String query = " SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS,UMMAP.COMMENTS,EB03DESC.EB03_DESCRIPTION "
				       +" FROM (select b.data_element_val as Value, "
					   +" trim(b.val_desc_txt) as EB03_DESCRIPTION "
					   +" from bx_hippa_segment_val b where b.data_element_id= 'EB03' "
					//	+" AND b.data_element_val NOT IN(SELECT EB03_VALUE from BX_SPDR_EB03_EXCLUSION) "
						+" ) EB03DESC,BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS "
						+" WHERE UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID "
						+" AND UMMAP.UM_RULE_ID='" + ruleId + "' "
						+" AND upper(UMAS.UM_EB03) = EB03DESC.Value ";
				
				
				/*"SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS,UMMAP.COMMENTS FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS"
				+ " WHERE  UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID AND UMMAP.UM_RULE_ID='" + ruleId + "'";*/

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UMRuleMappingLocateForEditQuery varLocQry = new UMRuleMappingLocateForEditQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	private static class UMRuleMappingLocateForEditQuery extends MappingSqlQuery {

		private UMRuleMappingLocateForEditQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO spiderUMRuleMapping = new SpiderUMRuleMappingVO();
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData
			// spiderUMRuleAssoc = new
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData();

			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setEb03Value(rs.getString("UM_EB03"));
			spiderUMRuleMapping.setEb03DefaultValue(rs.getString("UM_EB03_DEFAULT"));
			spiderUMRuleMapping.setMsgValue(rs.getString("UM_MAPG_MSG"));
			spiderUMRuleMapping.setStatus(rs.getString("STATUS"));
			spiderUMRuleMapping.setUmRuleDescription(rs.getString("UM_RULE_ID_DESCRIPTION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setComments(rs.getString("COMMENTS"));
			spiderUMRuleMapping.setEb03Desc(rs.getString("EB03_DESCRIPTION"));
			return spiderUMRuleMapping;
		}

	}
	
	
	public List findAllUmRuleMappingsByRuleIdFromLocate(String ruleId) {
		List allRules = new ArrayList();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		ruleId = ruleId.trim();
		String query =  " SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS,UMMAP.COMMENTS "
				        +" FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS " 
						+" WHERE UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID  "
						+" AND UMMAP.UM_RULE_ID='" + ruleId + "' ";
				
				
				/*"SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS,UMMAP.COMMENTS FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS"
				+ " WHERE  UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID AND UMMAP.UM_RULE_ID='" + ruleId + "'";*/

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UMRuleMappingLocateQuery varLocQry = new UMRuleMappingLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	private static class UMRuleMappingLocateQuery extends MappingSqlQuery {

		private UMRuleMappingLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO spiderUMRuleMapping = new SpiderUMRuleMappingVO();
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData
			// spiderUMRuleAssoc = new
			// com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData();

			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setEb03Value(rs.getString("UM_EB03"));
			spiderUMRuleMapping.setEb03DefaultValue(rs.getString("UM_EB03_DEFAULT"));
			spiderUMRuleMapping.setMsgValue(rs.getString("UM_MAPG_MSG"));
			spiderUMRuleMapping.setStatus(rs.getString("STATUS"));
			spiderUMRuleMapping.setUmRuleDescription(rs.getString("UM_RULE_ID_DESCRIPTION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setComments(rs.getString("COMMENTS"));

			return spiderUMRuleMapping;
		}

	}

	private static class UnmappedRuleLocateQuery extends MappingSqlQuery {

		private UnmappedRuleLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("UM_RULE_ID_DESCRIPTION"));

			return spiderUMRuleMapping;
		}

	}

	public List findAllUnmappedRules() {

		String query = " SELECT DISTINCT RMADB.MDCLRULE.MDCL_RULE_CD,RMADB.MDCLRULE.MDCL_RULE_TYPE_CD,RMADB.MDCLRULE.MDCL_RULE_desc,RMADB.MDCLRULE.CREATN_DTM,  "+
				       " RMADB.MDCLRULEVRSN.CURNT_STTS_CD,RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR FROM  "+
				       " ( "+
                       " SELECT RMADB.MDCLRULE.MDCL_RULE_ID AS RULEID, MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR) AS RVERSION , "+
                       " RMADB.MDCLRULE.MDCL_RULE_CD "+
				       " FROM RMADB.MDCL_RULE MDCLRULE,RMADB.MDCL_RULE_VRSN MDCLRULEVRSN  "+
				       " WHERE RMADB.MDCLRULE.MDCL_RULE_CD NOT IN (Select SPDR1.UM_RULE_ID from BX_SPDR_UM_MAPG SPDR1) "+ 
					   " AND RMADB.MDCLRULE.MDCL_RULE_ID = RMADB.MDCLRULEVRSN.MDCL_RULE_ID "+
					   " AND RMADB.MDCLRULEVRSN.CURNT_STTS_CD IN('FINALIZED','TESTING')  "+
					   " AND RMADB.MDCLRULE.MDCL_RULE_TYPE_CD ='UMRULE' "+
		               " GROUP BY RMADB.MDCLRULE.MDCL_RULE_ID,RMADB.MDCLRULE.MDCL_RULE_CD "+
		               " HAVING RMADB.MDCLRULE.MDCL_RULE_CD NOT IN (Select EXCLN.UM_RULE_ID from BX_SPDR_UM_MAPG_EXCL EXCLN WHERE MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR)<=EXCLN.VERSION) "+
		               " ) masterInfo, "+
					   " RMADB.MDCL_RULE MDCLRULE,RMADB.MDCL_RULE_VRSN MDCLRULEVRSN,RMADB.MDCL_RULE_VRSN_TAG TAG "+
					   " where RMADB.MDCLRULE.MDCL_RULE_ID = masterInfo.RULEID "+
					   " AND RMADB.MDCLRULEVRSN.MDCL_RULE_ID = masterInfo.RULEID "+
					   " AND RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION "+
					   " AND TAG.MDCL_RULE_ID = masterInfo.RULEID AND TAG.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION  "+
					   " ORDER BY MDCL_RULE_CD " ;
		
		log.debug("Query for unmapped Rules : " + query);
		UnmappedRuleIdLocateQuery varLocQry = new UnmappedRuleIdLocateQuery(dataSource, query);
		List unmappedRules = varLocQry.execute();
		if (null != unmappedRules && unmappedRules.size() > 0) {
			log.debug("*****************Size of unmapped Rules : " + unmappedRules.size());
		}
		return unmappedRules;
	}

	private static class UnmappedRuleIdLocateQuery extends MappingSqlQuery {

		private UnmappedRuleIdLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleType(rs.getString("MDCL_RULE_TYPE_CD"));
			spiderUMRuleMapping.setUmRuleId(rs.getString("MDCL_RULE_CD"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("MDCL_RULE_DESC"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATN_DTM"));
			spiderUMRuleMapping.setUmStatus(rs.getString("CURNT_STTS_CD"));
			spiderUMRuleMapping.setDefaultVersion(rs.getInt("MDCL_RULE_VRSN_NBR"));
			return spiderUMRuleMapping;
		}

	}

	public List retrieveRuleInfo(SpiderUMRuleMapping spiderUMRuleMapping) {
		StringBuffer retrieveRuleInfoforCreateQuery = new StringBuffer();
		if (null != spiderUMRuleMapping.getUmRuleId() && !"".equals(spiderUMRuleMapping.getUmRuleId())) {
			retrieveRuleInfoforCreateQuery
					.append("SELECT DISTINCT RMADB.MDCLRULE.MDCL_RULE_CD,RMADB.MDCLRULE.MDCL_RULE_TYPE_CD,RMADB.MDCLRULE.MDCL_RULE_desc,RMADB.MDCLRULE.CREATN_DTM "
							+ " FROM "
							+ " (SELECT RMADB.MDCLRULE.MDCL_RULE_ID AS RULEID, MAX(RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR) AS RVERSION , "
							+ " RMADB.MDCLRULE.MDCL_RULE_CD AS RULECD "
							+ " FROM RMADB.MDCL_RULE MDCLRULE LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
							+ " ON ( RMADB.MDCLRULE.MDCL_RULE_ID = RMADB.MDCLRULEVRSN.MDCL_RULE_ID) AND RMADB.MDCLRULEVRSN.CURNT_STTS_CD IN('FINALIZED','TESTING') "
							+ " WHERE RMADB.MDCLRULE.MDCL_RULE_TYPE_CD ='UMRULE' "
							+ " GROUP BY RMADB.MDCLRULE.MDCL_RULE_ID,RMADB.MDCLRULE.MDCL_RULE_CD) masterInfo LEFT OUTER JOIN RMADB.MDCL_RULE MDCLRULE "
							+ " ON (RMADB.MDCLRULE.MDCL_RULE_ID = masterInfo.RULEID) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN MDCLRULEVRSN "
							+ " ON ( RMADB.MDCLRULEVRSN.MDCL_RULE_ID = masterInfo.RULEID  "
							+ " AND RMADB.MDCLRULEVRSN.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) LEFT OUTER JOIN RMADB.MDCL_RULE_VRSN_TAG TAG "
							+ " ON(TAG.MDCL_RULE_ID = masterInfo.RULEID AND TAG.MDCL_RULE_VRSN_NBR = masterInfo.RVERSION) "
							+ " WHERE RMADB.MDCLRULE.MDCL_RULE_CD =  '" + spiderUMRuleMapping.getUmRuleId() + "'");

		}
		RetrieveRuleForCreateQuery retrieveRuleInfoQuery = new RetrieveRuleForCreateQuery(getDataSource(),
				retrieveRuleInfoforCreateQuery.toString());
		List ruleWithInfoList = retrieveRuleInfoQuery.execute();
		if (null != ruleWithInfoList && ruleWithInfoList.size() > 0) {
			log.debug("*****************ruleList size : " + ruleWithInfoList.size());
			return ruleWithInfoList;
		}
		return null;

	}

	private class RetrieveRuleForCreateQuery extends MappingSqlQuery {

		public RetrieveRuleForCreateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleId(rs.getString("MDCL_RULE_CD"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("MDCL_RULE_DESC"));
			spiderUMRuleMapping.setUmRuleType(rs.getString("MDCL_RULE_TYPE_CD"));
			/*
			 * spiderUMRuleMapping.setUmEB03(rs.getString("UM_EB03"));
			 * spiderUMRuleMapping.setUmEB03Default(rs.getString(
			 * "UM_EB03_DEFAULT"));
			 * spiderUMRuleMapping.setUmMessage(rs.getString("UM_MAPG_MSG"));
			 * spiderUMRuleMapping.setUmStatus("UM_MAPG_STATUS");
			 */
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATN_DTM"));
			return spiderUMRuleMapping;
		}
	}

	public int findMappingPresentOrNot(String ruleId, String eb03Value) {
		String query = " select UM_EB03 FROM BX_SPDR_UM_MAPG MST "
				+ " INNER JOIN BX_SPD_UM_MAPG_ASSOCIATION ASSOC ON MST.SPDR_UM_MAPG_SYS_ID=ASSOC.SPDR_UM_MAPG_SYS_ID where UM_RULE_ID='"
				+ ruleId + "' ";
		int count = 0;
		mappedRuleQuery varLocQry = new mappedRuleQuery(dataSource, query);
		List mappedRules = varLocQry.execute();
		if (null != mappedRules && mappedRules.size() > 0) {
			count = mappedRules.size();
		}
		return count;
	}

	public int findEb03PresentOrNot(String eb03Value) {
		String query = " select EB03_VALUE from BX_SPDR_EB03_EXCLUSION " + "where EB03_VALUE='" + eb03Value + "' ";
		int count = 0;

		eb03ExclusionQuery varLocQry = new eb03ExclusionQuery(dataSource, query);
		List mappedRules = varLocQry.execute();
		if (null != mappedRules && mappedRules.size() > 0) {
			count = mappedRules.size();
		}
		return count;
	}

	public boolean deleteRuleIdMapping(Number id, String eb03Value) {
		String query = " delete from BX_SPD_UM_MAPG_ASSOCIATION where SPDR_UM_MAPG_SYS_ID='" + id + "' and UM_EB03='"
				+ eb03Value + "'  ";
		DeleteUmRuleMappings deleteUmRuleMappings = new DeleteUmRuleMappings(dataSource, query);
		int rowsAffected = 0;
		rowsAffected = deleteUmRuleMappings.update();
		return true ? rowsAffected > 0 : false;
	}

	private final class DeleteUmRuleMappings extends SqlUpdate {

		private DeleteUmRuleMappings(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}

	private static class mappedRuleQuery extends MappingSqlQuery {

		private mappedRuleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData();

			spiderUMRuleMapping.setUmEB03(rs.getString("UM_EB03"));

			return spiderUMRuleMapping;
		}

	}

	private static class eb03ExclusionQuery extends MappingSqlQuery {

		private eb03ExclusionQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion();

			spiderUMRuleMapping.setEb03(rs.getString("EB03_VALUE"));

			return spiderUMRuleMapping;
		}
	}

	public void persistChildTable(List<SpiderUMRuleMappingData> spiderUMRuleAssoc, Long sysID) {
		sequenceNo = 0;
		sequenceNo = sequenceNo + 1;
		childTbleInsertQuery = new StringBuffer();
		mstrTbleSysID = sysID;

		childTbleInsertQuery
				.append(" Insert into BX_SPD_UM_MAPG_ASSOCIATION (SPDR_UM_MAPG_SYS_ID,UM_EB03,UM_EB03_DEFAULT, "
						+ " UM_MAPG_MSG,VERSION,CREATED_USER,CREATED_DATE, "
						+ " LAST_UPDT_USER,LAST_UPDT_DATE,SPDR_UM_ASOC_SYS_ID) " + " values ( ?,?,?, "
						+ "?,?,?,sysdate, " + "?,sysdate,?) ");

		try {
			if (!spiderUMRuleAssoc.isEmpty()) {
				createRuleMappingAssociationTableQuery = new CreateRuleMappingAssociationTableQuery(getDataSource(),
						childTbleInsertQuery.toString());

				for (SpiderUMRuleMappingData spider : spiderUMRuleAssoc) {
					childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
					createRuleMappingAssociationTableQuery.insert(spider);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	protected class CreateRuleMappingAssociationTableQuery extends SqlUpdate {

		protected CreateRuleMappingAssociationTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();

		}

		protected void insert(SpiderUMRuleMappingData spiderUMRuleMappingAssoc) {

			Object[] objs = new Object[] { mstrTbleSysID, spiderUMRuleMappingAssoc.getUmEB03().toUpperCase(),
					spiderUMRuleMappingAssoc.getUmEB03Default().toUpperCase(),
					spiderUMRuleMappingAssoc.getUmMessage().toUpperCase(), spiderUMRuleMappingAssoc.getVersion(),
					spiderUMRuleMappingAssoc.getUser().getCreatedUserName(),
					spiderUMRuleMappingAssoc.getUser().getLastUpdatedUserName(), childTbleSysID };
			super.update(objs);

		}
	}

	public void persistEditChildTable(List<SpiderUMRuleMappingData> spiderUMRuleAssoc) {
		sequenceNo = 0;
		sequenceNo = sequenceNo + 1;
		childTbleInsertQuery = new StringBuffer();
		// childTbleSysID =
		// sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
		childTbleInsertQuery.append(" Update BX_SPD_UM_MAPG_ASSOCIATION SET UM_EB03=?,UM_EB03_DEFAULT=?, "
				+ " UM_MAPG_MSG=?,VERSION=?," + " LAST_UPDT_USER=?,LAST_UPDT_DATE=sysdate WHERE SPDR_UM_ASOC_SYS_ID=?");

		try {
			if (!spiderUMRuleAssoc.isEmpty()) {
				editRuleMappingAssociationTableQuery = new EditRuleMappingAssociationTableQuery(getDataSource(),
						childTbleInsertQuery.toString());

				for (SpiderUMRuleMappingData spider : spiderUMRuleAssoc) {
					editRuleMappingAssociationTableQuery.insert(spider);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	protected class EditRuleMappingAssociationTableQuery extends SqlUpdate {

		protected EditRuleMappingAssociationTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter("UM_EB03", Types.VARCHAR));
			declareParameter(new SqlParameter("UM_EB03_DEFAULT", Types.VARCHAR));
			declareParameter(new SqlParameter("UM_MAPG_MSG", Types.VARCHAR));
			declareParameter(new SqlParameter("VERSION", Types.VARCHAR));
			declareParameter(new SqlParameter("LAST_UPDT_USER", Types.VARCHAR));
			declareParameter(new SqlParameter("SPDR_UM_ASOC_SYS_ID", Types.BIGINT));
			// declareParameter(new SqlParameter(Types.BIGINT));
			compile();

		}

		protected void insert(SpiderUMRuleMappingData spiderUMRuleMappingAssoc) {

			Object[] objs = new Object[] { spiderUMRuleMappingAssoc.getUmEB03().toUpperCase(),
					spiderUMRuleMappingAssoc.getUmEB03Default().toUpperCase(),
					spiderUMRuleMappingAssoc.getUmMessage().toUpperCase(), spiderUMRuleMappingAssoc.getVersion(),
					spiderUMRuleMappingAssoc.getUser().getLastUpdatedUserName(),
					spiderUMRuleMappingAssoc.getUmRuleMappingSystemId() };
			super.update(objs);

		}
	}

	public List persistAuditTable(List<SpiderUMRuleMappingDataAudit> spiderUMRuleAuditList, String ruleId) {
		sequenceNo = 0;
		sequenceNo = sequenceNo + 1;
		auditTbleInsertQuery = new StringBuffer();
		// auditTbleSysID =
		// sequenceGeneratorRepository.generateSequence(auditTbleSeqColName);
		auditTbleInsertQuery
				.append(" Insert into BX_SPD_UM_MAPG_AUDIT (SPDR_UM_MAPG_SYS_ID,UM_RULE_ID,UM_EB03,UM_EB03_DEFAULT, "
						+ " UM_MAPG_MSG,VERSION,CREATED_USER,CREATED_DATE, "
						+ " LAST_UPDT_USER,LAST_UPDT_DATE,SPDR_UM_MAP_AUDIT_SYS_ID) " + " values ( ?,?,?,?, "
						+ " ?,?,?,sysdate, " + " ?,sysdate,?) ");

		try {
			if (!spiderUMRuleAuditList.isEmpty()) {
				createRuleMappingAuditTableQuery = new CreateRuleMappingAuditTableQuery(getDataSource(),
						auditTbleInsertQuery.toString());

				for (SpiderUMRuleMappingDataAudit spider : spiderUMRuleAuditList) {
					auditTbleSysID = sequenceGeneratorRepository.generateSequence(auditTbleSeqColName);
					createRuleMappingAuditTableQuery.insert(spider, ruleId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return spiderUMRuleAuditList;
	}

	protected class CreateRuleMappingAuditTableQuery extends SqlUpdate {

		protected CreateRuleMappingAuditTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();

		}

		protected void insert(SpiderUMRuleMappingDataAudit spiderUMRuleAssoc, String ruleId) {

			Object[] objs = new Object[] { spiderUMRuleAssoc.getUmRuleSystemId(), ruleId,
					spiderUMRuleAssoc.getUmEB03().toUpperCase(), spiderUMRuleAssoc.getUmEB03Default(),
					spiderUMRuleAssoc.getUmMessage(), spiderUMRuleAssoc.getVersion(),
					spiderUMRuleAssoc.getCreatedBy(), spiderUMRuleAssoc.getLastModifiedBy(), auditTbleSysID

			};
			super.update(objs);

		}
	}

	/*
	 * public List findEb03DefaultMapping(); public boolean
	 * deleteEb03DefaultMapping(String spiderEB03);
	 */

	public List persistEb03DefaultMapping(EB03DefaultMapping spiderUMRuleAuditList) {
		sequenceNo = 0;
		sequenceNo = sequenceNo + 1;
		auditTbleInsertQuery = new StringBuffer();
		auditTbleInsertQuery
				.append(" Insert into BX_SPDR_EB03_DEFAULT_MAPPING (SPDR_EB03_DEFLT_SYS_ID,EB03_VALUE,EB03_DEFAULT,"
						+ " CREATED_USER,CREATED_DATE)  values (?,?,?,?,sysdate) ");

		List eb03ExclusionList = new ArrayList<>();

		try {
			createEb03DefaultTableQuery = new CreateEB03DefaultTableQuery(getDataSource(),
					auditTbleInsertQuery.toString());

			eb03TbleSysID = sequenceGeneratorRepository.generateSequence(eb03DefaultTbleSeqColName);
			createEb03DefaultTableQuery.insert(spiderUMRuleAuditList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return eb03ExclusionList;
	}

	protected class CreateEB03DefaultTableQuery extends SqlUpdate {

		protected CreateEB03DefaultTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();

		}

		protected void insert(EB03DefaultMapping eb03Exclusion) {

			Object[] objs = new Object[] { eb03TbleSysID, eb03Exclusion.getEb03(), eb03Exclusion.getEb03Default(),
					eb03Exclusion.getCreatedBy()

			};
			super.update(objs);

		}
	}

	public List persistEb03Exclusion(EB03Exclusion spiderUMRuleAuditList) {
		sequenceNo = 0;
		sequenceNo = sequenceNo + 1;
		auditTbleInsertQuery = new StringBuffer();
		auditTbleInsertQuery
				.append(" Insert into BX_SPDR_EB03_EXCLUSION (SPDR_MAPG_EB03_EXCLSN_SYS_ID,EB03_VALUE,EB03_DESCRIPTION,"
						+ " CREATED_USER,CREATED_DATE)  values (?,?,?,?,sysdate) ");

		List eb03ExclusionList = new ArrayList<>();

		try {
			createEb03ExclusionTableQuery = new CreateEB03ExclusionTableQuery(getDataSource(),
					auditTbleInsertQuery.toString());

			eb03TbleSysID = sequenceGeneratorRepository.generateSequence(eb03TbleSeqColName);
			createEb03ExclusionTableQuery.insert(spiderUMRuleAuditList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return eb03ExclusionList;
	}

	protected class CreateEB03ExclusionTableQuery extends SqlUpdate {

		protected CreateEB03ExclusionTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();

		}

		protected void insert(EB03Exclusion eb03Exclusion) {

			Object[] objs = new Object[] { eb03TbleSysID, eb03Exclusion.getEb03(), eb03Exclusion.getEb03Description(),
					eb03Exclusion.getCreatedBy()

			};
			super.update(objs);

		}
	}

	/*
	 * public boolean deleteEb03Exclusion(String eb03Exclusion) { String query =
	 * " select SPDR_MAPG_EB03_EXCLSN_SYS_ID  FROM BX_SPDR_EB03_EXCLUSION WHERE EB03_VALUE ='"
	 * + eb03Exclusion +"' "; Integer id = 0; deleteEB03SelectQuery varLocQry =
	 * new deleteEB03SelectQuery(dataSource, query); List mappedRules =
	 * varLocQry.execute(); if (null != mappedRules && mappedRules.size() > 0) {
	 * EB03Exclusion spiderId = new EB03Exclusion(); spiderId = (EB03Exclusion)
	 * mappedRules.get(0); id = spiderId.getEb03SysId(); } boolean rowsAffected
	 * = false; rowsAffected = deleteEB03Mapping(eb03Exclusion); return
	 * rowsAffected; }
	 */

	/*
	 * private static class deleteEB03SelectQuery extends MappingSqlQuery {
	 * private deleteEB03SelectQuery(DataSource dataSource, String query) {
	 * super(dataSource, query); compile(); }
	 * 
	 * protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	 * com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion
	 * spiderUMRuleMapping = new
	 * com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion();
	 * spiderUMRuleMapping.setEb03SysId(rs.getInt("SPDR_MAPG_EB03_EXCLSN_SYS_ID"
	 * )); return spiderUMRuleMapping; } }
	 */

	
	
    public List validateEb03DefaultMapping(String eb03,String defaultValue) {
		
    	List allRules = new ArrayList<>();
    	
    	eb03 = eb03.trim();
		String query = " SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS "
				+" WHERE  UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID AND UMAS.UM_EB03='" + eb03 + "'"
				+" AND  UMAS.UM_EB03_DEFAULT NOT IN('"+defaultValue+"')";

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		ValidateEB03DefaultMapping validateDefaultqry = new ValidateEB03DefaultMapping(dataSource, query);
		allRules = validateDefaultqry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;
	}
	
	
	private static class ValidateEB03DefaultMapping extends MappingSqlQuery {

		private ValidateEB03DefaultMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO spiderUMRuleMapping = new SpiderUMRuleMappingVO();
			
			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDescription(rs.getString("UM_RULE_ID_DESCRIPTION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setEb03Value(rs.getString("UM_EB03"));
			spiderUMRuleMapping.setEb03DefaultValue(rs.getString("UM_EB03_DEFAULT"));
			spiderUMRuleMapping.setMsgValue(rs.getString("UM_MAPG_MSG"));
			spiderUMRuleMapping.setStatus(rs.getString("STATUS"));
			return spiderUMRuleMapping;
		}

	}
    
	
	

	public List validateEb03Exclusion(String eb03Exclusion) {
		
		List mappingList = new ArrayList<>();
		final String retrieveQuery = "SELECT UMMAP.UM_RULE_ID,UMMAP.UM_EB03  "
				+ " FROM BX_SPDR_UM_MAPG UMMAP  WHERE  UMMAP.UM_EB03='" + eb03Exclusion + "' ";

		log.debug("Query :" + retrieveQuery);
		ValidateEB03Exclusions retrieveMappingQuery = new ValidateEB03Exclusions(dataSource, retrieveQuery);

		mappingList = retrieveMappingQuery.execute();
		return mappingList;
	}
	
	
	private final class ValidateEB03Exclusions extends MappingSqlQuery {
		
		public ValidateEB03Exclusions(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO result = new SpiderUMRuleMappingVO();
			result.setUmRuleId(rs.getString("UM_EB03"));
			return result;
		}
	}
	
	
	public boolean deleteEb03Exclusion(String eb03Exclusion) {
		String query = " delete from BX_SPDR_EB03_EXCLUSION where EB03_VALUE='" + eb03Exclusion + "'";
		DeleteEB03Exclusions deleteUmRuleMappings = new DeleteEB03Exclusions(dataSource, query);
		int rowsAffected = 0;
		rowsAffected = deleteUmRuleMappings.update();
		return true ? rowsAffected > 0 : false;
	}
	
	
	private final class DeleteEB03Exclusions extends SqlUpdate {
		private DeleteEB03Exclusions(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}

	public boolean deleteMapping(String ruleId, String eb03Value) {
		String query = " select SPDR_UM_MAPG_SYS_ID FROM BX_SPDR_UM_MAPG WHERE UM_RULE_ID='" + ruleId + "' ";

		Number id = 0;
		deleteMappedRuleQuery varLocQry = new deleteMappedRuleQuery(dataSource, query);
		List mappedRules = varLocQry.execute();
		if (null != mappedRules && mappedRules.size() > 0) {
			SpiderUMRuleMapping spiderId = new SpiderUMRuleMapping();
			spiderId = (SpiderUMRuleMapping) mappedRules.get(0);
			id = spiderId.getUmRuleIdSystemId();
		}
		boolean rowsAffected = false;
		rowsAffected = deleteRuleIdMapping(id, eb03Value);
		return rowsAffected;
	}

	private static class deleteMappedRuleQuery extends MappingSqlQuery {
		private deleteMappedRuleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleIdSystemId(rs.getInt("SPDR_UM_MAPG_SYS_ID"));

			return spiderUMRuleMapping;
		}
	}

	public SpiderUMRuleMapping retrieveMapping(String ruleId) {
		SpiderUMRuleMapping spiderUMRuleMapping = new SpiderUMRuleMapping();
		List mappingList = new ArrayList<>();
		final String retrieveQuery = "SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION  "
				+ " FROM BX_SPDR_UM_MAPG UMMAP " + " WHERE  UMMAP.UM_RULE_ID='" + ruleId + "' ";

		log.debug("Query :" + retrieveQuery);
		RetrieveMappingQuery retrieveMappingQuery = new RetrieveMappingQuery(dataSource, retrieveQuery);

		mappingList = retrieveMappingQuery.execute();
		spiderUMRuleMapping = (SpiderUMRuleMapping) mappingList.get(0);

		return spiderUMRuleMapping;
	}

	private class RetrieveMappingQuery extends MappingSqlQuery {

		public RetrieveMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO result = new SpiderUMRuleMappingVO();
			result.setUmRuleId(rs.getString("UM_RULE_ID"));
			// result.setEb03Value(rs.getString("UM_EB03"));
			// result.setEb03DefaultValue(rs.getString("UM_EB03_DEFAULT"));
			// result.setMsgValue(rs.getString("UM_MAPG_MSG"));
			result.setUmRuleDescription(rs.getString("UM_RULE_ID_DESCRIPTION"));
			return result;
		}
	}

	private SpiderUMRuleMapping createMappingObjectFromResult(List retrievedMappingList) {

		SpiderUMRuleMapping mappingActual = new SpiderUMRuleMapping();
		if ((!retrievedMappingList.isEmpty()) || (retrievedMappingList.size() != 0)) {
			SpiderUMRuleMapping result = (SpiderUMRuleMapping) retrievedMappingList.get(0);

		}

		return mappingActual;

	}

	public List findExistingMappedRuleStartingWith(String ruleId, int noOfRecords) {
		List allRules = new ArrayList();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		ruleId = ruleId.trim();
		String query = " select UM_RULE_ID,UM_RULE_ID_DESCRIPTION from BX_SPDR_UM_MAPG where UM_RULE_ID LIKE '"
				+ ruleId + "%' ";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query += " and rownum < " + noOfRecords + " ";
		}

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		MappedRuleLocateQuery varLocQry = new MappedRuleLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}

	private static class MappedRuleLocateQuery extends MappingSqlQuery {

		private MappedRuleLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("UM_RULE_ID_DESCRIPTION"));
			return spiderUMRuleMapping;
		}

	}

	public List findEb03Exclusions() {
		String query = " select EB03_VALUE,EB03_DESCRIPTION from BX_SPDR_EB03_EXCLUSION ORDER BY EB03_VALUE ASC";

		eb03ExclusionList varLocQry = new eb03ExclusionList(dataSource, query);
		List exclusiveEb03Values = varLocQry.execute();

		return exclusiveEb03Values;
	}

	private static class eb03ExclusionList extends MappingSqlQuery {

		private eb03ExclusionList(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion();

			spiderUMRuleMapping.setEb03(rs.getString("EB03_VALUE"));
			spiderUMRuleMapping.setEb03Description(rs.getString("EB03_DESCRIPTION"));

			return spiderUMRuleMapping;
		}
	}

	public List findEb03DefaultMappings() {
		String query = " select EB03_VALUE,EB03_DEFAULT from BX_SPDR_EB03_DEFAULT_MAPPING order by eb03_value asc";

		Eb03DefaultMappingsList varLocQry = new Eb03DefaultMappingsList(dataSource, query);
		List exclusiveEb03Values = varLocQry.execute();

		return exclusiveEb03Values;
	}

	private static class Eb03DefaultMappingsList extends MappingSqlQuery {

		private Eb03DefaultMappingsList(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping();

			spiderUMRuleMapping.setEb03(rs.getString("EB03_VALUE"));
			spiderUMRuleMapping.setEb03Default(rs.getString("EB03_DEFAULT"));

			return spiderUMRuleMapping;
		}
	}

	public boolean deleteEb03DefaultMapping(String eb03Exclusion) {
		String query = " delete from BX_SPDR_EB03_DEFAULT_MAPPING where EB03_VALUE='" + eb03Exclusion + "'";
		DeleteEB03DefaultMapping deleteUmRuleMappings = new DeleteEB03DefaultMapping(dataSource, query);
		int rowsAffected = 0;
		rowsAffected = deleteUmRuleMappings.update();
		return true ? rowsAffected > 0 : false;
	}

	private final class DeleteEB03DefaultMapping extends SqlUpdate {
		private DeleteEB03DefaultMapping(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}

	public List findAllUmRuleMappingsByEb03(String eb03) {
		List allRules = new ArrayList();
		if (eb03 == null || "".equals(eb03.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		eb03 = eb03.trim();
		String query = " SELECT UMMAP.UM_RULE_ID,UMMAP.UM_RULE_ID_DESCRIPTION,UMAS.CREATED_DATE,UMAS.UM_EB03,UMAS.UM_EB03_DEFAULT,UMAS.UM_MAPG_MSG,UMMAP.STATUS FROM BX_SPDR_UM_MAPG UMMAP,BX_SPD_UM_MAPG_ASSOCIATION UMAS "
				+" WHERE  UMAS.SPDR_UM_MAPG_SYS_ID = UMMAP.SPDR_UM_MAPG_SYS_ID AND UMAS.UM_EB03='" + eb03 + "'";

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UMRuleMappingEb03Query varLocQry = new UMRuleMappingEb03Query(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
		}
		return allRules;

	}
	
	private static class UMRuleMappingEb03Query extends MappingSqlQuery {

		private UMRuleMappingEb03Query(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO spiderUMRuleMapping = new SpiderUMRuleMappingVO();
			
			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDescription(rs.getString("UM_RULE_ID_DESCRIPTION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setEb03Value(rs.getString("UM_EB03"));
			spiderUMRuleMapping.setEb03DefaultValue(rs.getString("UM_EB03_DEFAULT"));
			spiderUMRuleMapping.setMsgValue(rs.getString("UM_MAPG_MSG"));
			spiderUMRuleMapping.setStatus(rs.getString("STATUS"));
			return spiderUMRuleMapping;
		}

	}
	
	public boolean deleteRuleMapping(String ruleId) {
		String query = " select SPDR_UM_MAPG_SYS_ID FROM BX_SPDR_UM_MAPG WHERE UM_RULE_ID='" + ruleId + "' ";

		Number id = 0;
		deleteUnMappedRuleQuery varLocQry = new deleteUnMappedRuleQuery(dataSource, query);
		List mappedRules = varLocQry.execute();
		if (null != mappedRules && mappedRules.size() > 0) {
			SpiderUMRuleMapping spiderId = new SpiderUMRuleMapping();
			spiderId = (SpiderUMRuleMapping) mappedRules.get(0);
			id = spiderId.getUmRuleIdSystemId();
		}
		boolean rowsAffected = false;
		boolean rowAffectedforhistory = false;
		rowAffectedforhistory = deleteRuleIdForSinglemappingHistory(id);
		rowsAffected = deleteRuleIdForSinglemapping(id);
		return rowsAffected;
	}


	private static class deleteUnMappedRuleQuery extends MappingSqlQuery {
		private deleteUnMappedRuleQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();
			spiderUMRuleMapping.setUmRuleIdSystemId(rs.getInt("SPDR_UM_MAPG_SYS_ID"));
			return spiderUMRuleMapping;
		}
	}
	
	public boolean deleteRuleIdForSinglemapping(Number id) {
		String query = " delete from BX_SPDR_UM_MAPG where SPDR_UM_MAPG_SYS_ID='" + id + "' ";
		DeleteMappings deleteUmRuleMappings = new DeleteMappings(dataSource, query);
		int rowsAffected = 0;
		rowsAffected = deleteUmRuleMappings.update();
		return true ? rowsAffected > 0 : false;
	}
	
	public boolean deleteRuleIdForSinglemappingHistory(Number id) {
		String query = " delete from BX_SPD_UM_MAPG_LOG where SPDR_UM_MAPG_SYS_ID='" + id + "' ";
		DeleteMappings deleteUmRuleMappings = new DeleteMappings(dataSource, query);
		int rowsAffected = 0;
		rowsAffected = deleteUmRuleMappings.update();
		return true ? rowsAffected > 0 : false;
	}

	private final class DeleteMappings extends SqlUpdate {

		private DeleteMappings(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
	}
	
	public List findAllUmRuleComments(String umRuleId) {

		String query = " select comments from BX_SPDR_UM_MAPG where um_rule_id='"+umRuleId+"' ";
		viewCommentsQuery varLocQry = new viewCommentsQuery(dataSource, query);
		List commentsList = varLocQry.execute();
		if (null != commentsList && commentsList.size() > 0) {
			log.debug("*****************Size of comments : " + commentsList.size());
		}
		return commentsList;
	}

	private static class viewCommentsQuery extends MappingSqlQuery {

		private viewCommentsQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setComment(rs.getString("COMMENTS"));
		
			return spiderUMRuleMapping;
		}

	}

	
	public List getEb03DefaultForAutocomplete(String searchString, int noOfRecordsForPopUp,String searchType) 
	{
		if (null == searchString) {
			searchString = "";
		}

		String query;

		//if(searchType.equals("locate")){
			/*query = " select b.data_element_val as \"Value\", "
					+ " b.val_desc_txt as \"Description\" "
					+ " from bx_hippa_segment_val b where b.data_element_id= 'EB03' "
					+ " AND b.data_element_val   IN(SELECT UM_EB03 from BX_SPD_UM_MAPG_ASSOCIATION)  "
					+ " and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
					+ " order by \"Value\" ASC ";*/
			
			query = " select b.data_element_val as \"Value\", "
					+" b.val_desc_txt as \"Description\" "
					+" from bx_hippa_segment_val b where b.data_element_id= 'EB03' "
					+" AND b.data_element_val NOT IN(SELECT EB03_VALUE from BX_SPDR_EB03_EXCLUSION) "
					+" AND b.data_element_val NOT IN(SELECT EB03_VALUE from BX_SPDR_EB03_DEFAULT_MAPPING) "
					+" and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
					+" order by \"Value\" ASC ";
		/*}else{
		query = " select b.data_element_val as \"Value\", "
				+ " b.val_desc_txt as \"Description\"  "
				+ " from bx_hippa_segment_val b where b.data_element_id= 'EB03' "
				+ " AND b.data_element_val   NOT IN(SELECT EB03_VALUE from BX_SPDR_EB03_EXCLUSION)  "
				+ " and upper(b.data_element_val) like upper('" + searchString.trim() + "%') "
				+ " order by \"Value\" ASC ";

		}*/
		
		
		if (noOfRecordsForPopUp > 0) {
			noOfRecordsForPopUp = noOfRecordsForPopUp + 1;
			query = "select * from (" + query + ") where rownum <" + noOfRecordsForPopUp;
		}
		log.trace("Query in HRepo (find xcept eb01): " + query);
		Eb03DefaultListQuery hippaCodeLocateQuery = new Eb03DefaultListQuery(dataSource, query);// List<HippaCodeValue>
		List matchingHippaCodeValues = hippaCodeLocateQuery.execute();
		if (null != matchingHippaCodeValues && !matchingHippaCodeValues.isEmpty()) {
			log.trace("No. of  matching HippaCodeValues for " + searchString.trim() + " is "
					+ matchingHippaCodeValues.size());
			return matchingHippaCodeValues;
		}
		return new ArrayList();
	}


	private class Eb03DefaultListQuery extends MappingSqlQuery {

		private Eb03DefaultListQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.
		 * ResultSet, int)
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Eb03CodeValueVO hippaCodeValue = new Eb03CodeValueVO();
			hippaCodeValue.setValue(rs.getString("Value"));
			hippaCodeValue.setDescription(rs.getString("Description"));
			//hippaCodeValue.setEb03Default(rs.getString("eb03Default"));

			//log.trace("Desc, Value " + hippaCodeValue.getDescription() + ", " + hippaCodeValue.getValue());
			return hippaCodeValue;
		}
	}

	
	public void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderMaster) {
		
	  ignrTbleSysID = sequenceGeneratorRepository.generateSequence(ignoreTbleSeqColName);
	  CreateRuleMappingIgnoreTableQuery createRuleMappingIgnoreTableQuery;
	  
	  try 
	  {
		  createRuleMappingIgnoreTableQuery = new CreateRuleMappingIgnoreTableQuery(getDataSource());
		  createRuleMappingIgnoreTableQuery.insert(spiderMaster);
	  }
	  catch (Exception e) {
		    e.printStackTrace();
	  }
		
	}
	
	protected class CreateRuleMappingIgnoreTableQuery extends SqlUpdate {
		protected CreateRuleMappingIgnoreTableQuery(DataSource ds) {
		super(ds,
		" Insert into BX_SPDR_UM_MAPG_EXCL(SPDR_UM_EXCL_SYS_ID,UM_RULE_ID,VERSION, "
		+ "STATUS,CREATED_USER,CREATED_DATE, "
		+ "LAST_UPDT_USER,LAST_UPDT_DATE) values (?,?,?, " + " ?,?,sysdate," + "?,sysdate)");



		declareParameter(new SqlParameter(Types.BIGINT));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));
		declareParameter(new SqlParameter(Types.VARCHAR));

		declareParameter(new SqlParameter(Types.VARCHAR));



		compile();
		}



		protected void insert(SpiderUMRuleMapping spiderUMRuleMapping) {



		Object[] objs = new Object[] { ignrTbleSysID, spiderUMRuleMapping.getUmRuleId().toUpperCase(),
		spiderUMRuleMapping.getDefaultVersion(),
		spiderUMRuleMapping.getUmStatus(),
		spiderUMRuleMapping.getUser().getCreatedUserName(),
		spiderUMRuleMapping.getUser().getLastUpdatedUserName()



		};
		super.update(objs);
		/**
		* end
		*/
		}
		}

	
	public String verifyUmRuleExistByRuleId(String ruleId) {
		String mappedString = "UM Rule Already Mapped";
		String excludedString = "UM Rule Already Excluded";
		String deafultString = "Invalid UM Rule";
		List allRules = new ArrayList();
		if (ruleId == null || "".equals(ruleId.trim())) {
			log.debug("There is no ruleId.");
			return deafultString;
		}
		ruleId = ruleId.trim();
		String query = " select UM_RULE_ID,UM_RULE_ID_DESCRIPTION from BX_SPDR_UM_MAPG where UM_RULE_ID='"+ ruleId + "' ";
		

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		MappedRuleLocateQuery varLocQry = new MappedRuleLocateQuery(dataSource, query);
		allRules = varLocQry.execute();
		if (null != allRules && allRules.size() > 0) {
			log.trace("*****************Size of all Um Rules (autocomplete of Rule ID ): " + allRules.size());
			return mappedString;
		}
		/*List excludedList = new ArrayList();
		
		String excludedLocateQuery = " select UM_RULE_ID,VERSION from BX_SPDR_UM_MAPG_EXCL where UM_RULE_ID='"+ ruleId + "' ";
		
		ExcludedRuleLocateQuery exRuleLocateQuery = new ExcludedRuleLocateQuery(dataSource, excludedLocateQuery);
		excludedList = exRuleLocateQuery.execute();
		if(excludedList != null && excludedList.size() > 0){
			System.out.println("Excluded Size" + excludedList.size());
			return excludedString;
		}*/
		
		return deafultString;
	}
	
	

	public List findAuditDetailsByRuleId(String umRuleId) {
		List allRules = new ArrayList();
		if (umRuleId == null || "".equals(umRuleId.trim())) {
			log.debug("There is no ruleId.");
			return allRules;
		}
		umRuleId = umRuleId.trim();
		String query = " select LOG.VERSION,LOG.CREATED_DATE,LOG.CREATED_USER,LOG.STATUS,LOG.SYSTEM_STATUS,LOG.COMMENTS from BX_SPD_UM_MAPG_LOG LOG,BX_SPDR_UM_MAPG UMMAP "
		               +" where LOG.UM_RULE_ID = UMMAP.UM_RULE_ID "
		               +" AND LOG.UM_RULE_ID = '" + umRuleId+ "' ORDER BY LOG.CREATED_DATE DESC ";

		UMRuleMappingsAuditData varLocQry = new UMRuleMappingsAuditData(dataSource, query);
		allRules = varLocQry.execute();
		
		return allRules;
	}

	private static class UMRuleMappingsAuditData extends MappingSqlQuery {

		private UMRuleMappingsAuditData(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpiderUMRuleMappingVO spiderUMRuleMapping = new SpiderUMRuleMappingVO();
			
			
			spiderUMRuleMapping.setVersion(rs.getInt("VERSION"));
			spiderUMRuleMapping.setCreatedDate(rs.getDate("CREATED_DATE"));
			spiderUMRuleMapping.setCreatedBy(rs.getString("CREATED_USER"));
			spiderUMRuleMapping.setStatus(rs.getString("STATUS"));
			spiderUMRuleMapping.setSystemStatus(rs.getString("SYSTEM_STATUS"));
			spiderUMRuleMapping.setComments(rs.getString("COMMENTS"));
			return spiderUMRuleMapping;
		}

	}

	private static class ExcludedRuleLocateQuery extends MappingSqlQuery {

		private ExcludedRuleLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping spiderUMRuleMapping = new com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping();

			spiderUMRuleMapping.setUmRuleId(rs.getString("UM_RULE_ID"));
			spiderUMRuleMapping.setUmRuleDesc(rs.getString("VERSION"));
			return spiderUMRuleMapping;
		}

	}

	public void persistNewLogForMapping(SpiderUMRuleMapping spiderUMRuleMapping, Long mstrTbleSysID) {
		
		logTbleSysID = sequenceGeneratorRepository.generateSequence(logTbleSeqColName);
		childTbleInsertQuery = new StringBuffer();
		
		childTbleInsertQuery
				.append(" Insert into BX_SPD_UM_MAPG_LOG (SPDR_LOG_SYS_ID,UM_RULE_ID,VERSION, "
						+ "COMMENTS,STATUS,SYSTEM_STATUS,CREATED_USER,CREATED_DATE,SPDR_UM_MAPG_SYS_ID) "
						+ " values (?,?,?," + "?,?,?,?,sysdate,?) " );
	
		try {
			
				createLogTableQuery = new CreateLogTableQuery(getDataSource(),
						childTbleInsertQuery.toString());
	
					createLogTableQuery.insert(spiderUMRuleMapping,mstrTbleSysID);
				
	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
		}
	}
	
	
	protected class CreateLogTableQuery extends SqlUpdate {	
		protected CreateLogTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();

		}

		protected void insert(SpiderUMRuleMapping spiderUMRuleMapping,Long mstrTbleSysID) {

			Object[] objs = new Object[] { logTbleSysID, spiderUMRuleMapping.getUmRuleId().toUpperCase(),
					spiderUMRuleMapping.getDefaultVersion(),
					spiderUMRuleMapping.getComment(), spiderUMRuleMapping.getUmStatus().toUpperCase(),
					spiderUMRuleMapping.getSystemStatus(),
					spiderUMRuleMapping.getUser().getCreatedUserName(),
					mstrTbleSysID };
			super.update(objs);

		}
	}
	
	public int findEb03DefaultMappingsPresentOrNot(String eb03) 
	{
		String query = " select EB03_VALUE,EB03_DEFAULT from BX_SPDR_EB03_DEFAULT_MAPPING WHERE eb03_value = '"+eb03+"' ";

		Eb03DefaultMappingsList varLocQry = new Eb03DefaultMappingsList(dataSource, query);
		List exclusiveEb03Values = varLocQry.execute();
        int count = 0;
        if(!exclusiveEb03Values.isEmpty())
        {
        	count = exclusiveEb03Values.size();
        }
		return count;
	}
	
}
