/*
 * Created on May 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.ExtendedMessageMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.HPNMessageMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.MappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSMappingRetrieveResult;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.entity.VariableValidationResult;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

/**
 * @author u22093
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MappingRepositoryImpl implements MappingRepository {

	private DataSource dataSource;
	
	private static Logger log = Logger.getLogger(MappingRepositoryImpl.class);

	private SequenceGeneratorRepository sequenceGeneratorRepository;

	private CreateMappingMasterTableQuery createMappingMasterTableQuery;

	private CreateMappingChildTableQuery createMappingChildTableQuery;

	private CheckForMappingExistsQuery checkForMappingExistsQuery;

	private Long sysIDFrmMstrTble;

	public static final String masterTbleSeqColName = "VAR_MAPG_SYS_ID_SEQ ";

	public static final String childTbleSeqColName = "VAR_MAPG_VAL_SYS_ID_SEQ";

	public static final String extndMsgTbleSeqColName = "VAR_EXTND_MSG_VAL_SYS_ID_SEQ";

	private Long mstrTbleSysID;

	private Long childTbleSysID;
	
	private Long extndMsgTbleSysID;

	private HippaCodeValue hippaCodeValueObj;

	private StringBuffer childTbleInsertQuery;

	public static final String tempChildTbleSeqColName = "TEMP_VAR_MAPG_VAL_SYS_ID_SEQ";

	public Long tempChildTbleSysID;

	private int sequenceNo = 0;

	List<EB03Association> tempEb03Values;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.repository.MappingRepository#isMappingAlreadyCreated(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */

	protected class CheckForMappingExistsQuery extends MappingSqlQuery {

		/**
		 * Create a new instance of VetsQuery.
		 * 
		 * @param ds
		 *            the DataSource to use for the query
		 * @param sql
		 *            SQL string to use for the query
		 */
		protected CheckForMappingExistsQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}
	}

	public boolean isMappingAlreadyCreated(Mapping mapping) {
		List noOfRecordsPresent = null;
		StringBuffer checkMappingQuery = new StringBuffer(
				"SELECT * FROM BX_CNTRCT_VAR_MAPG where CNTRCT_VAR_ID= '"+ mapping.getVariable().getVariableId() + "'");
		checkForMappingExistsQuery = new CheckForMappingExistsQuery(
				getDataSource(), checkMappingQuery.toString());
		noOfRecordsPresent = checkForMappingExistsQuery.execute();
		if(noOfRecordsPresent.size()>=1){
			return true;
		}
		return false;
	}

	// SSCR19537 April04 Changes in the query to add INDVDL_EB03_ASSN_IND
	protected class CreateMappingMasterTableQuery extends SqlUpdate {
		protected CreateMappingMasterTableQuery(DataSource ds) {
			super(
					ds,
					"Insert into BX_CNTRCT_VAR_MAPG (VAR_MAPG_SYS_ID,CNTRCT_VAR_ID,CNTRCT_VAR_DESC,"
							+ "SEN_BNFT_IND,VAR_MAPG_STTS_CD,IS_MAPG_REQUIRED,CREATD_USER_ID,CREATD_TM_STMP,"
							+ "LST_CHG_TMS,LST_CHG_USR,IN_TEMP_TAB,MAPPNG_CMP_IND, PROC_EXCLD_IND, INDVDL_EB03_ASSN_IND) values (?,?,?,"
							+ "?,?,'Y',?,sysdate,"
							+ "sysdate,?,'N',?,?,?)");
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		protected void insert(Long sysId, Mapping mapping) {
		// SSCR 19537 April04 Change
	//		String message = mapping.getMessage();
	//		if(message != null)message = message.toUpperCase();
			/**
			 * mtm change
			 */
			String mappingCompleteInd = mapping.isFinalized()?"Y":"N";
			String procExcldInd = mapping.getProcedureExcludedInd();
			if (procExcldInd == null || procExcldInd.trim().length() == 0) {
				procExcldInd = "N";
			}


			
			Object[] objs = new Object[] { sysId,
					mapping.getVariable().getVariableId().toUpperCase(),
					mapping.getVariable().getDescription().toUpperCase(),
					// SSCR 19537 April04 Change
	//				message, 
	//				mapping.getMsg_type_required().toUpperCase(),
					mapping.getSensitiveBenefitIndicator().toUpperCase(),
					mapping.getVariableMappingStatus().toUpperCase(), 
					mapping.getUser().getCreatedUserName(),
					mapping.getUser().getCreatedUserName(),
					mappingCompleteInd,
					procExcldInd,
					mapping.getIndvdlEb03AssnIndicator()};

			super.update(objs);
			/**
			 * end
			 */
		}
	}

	protected class FetchSysIDFromMasterTableQuery extends MappingSqlQuery {
		protected FetchSysIDFromMasterTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			compile();
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			return rs.getString(1);
		}
	}

	protected class CreateMappingChildTableQuery extends SqlUpdate {
		protected CreateMappingChildTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(String hippaSegmentName,
				HippaCodeValue hippaCodeValueObj, String createdUserName) {
			Object[] objs = new Object[] { mstrTbleSysID,
					Integer.valueOf(sequenceNo), hippaSegmentName.toUpperCase(),
					hippaCodeValueObj.getValue().toUpperCase(),createdUserName.toUpperCase(), childTbleSysID };
			super.update(objs);
		}
	}
	protected class PersistMappingChildTableQuery extends SqlUpdate {
		protected PersistMappingChildTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(Mapping mapping, String hippaSegmentName,
				HippaCodeValue hippaCodeValueObj) {
			Object[] objs = new Object[] { mapping.getVariableSystemId(),
					hippaCodeValueObj.getSeq_num(), hippaSegmentName,
					hippaCodeValueObj.getValue(), mapping.getUser().getCreatedUserName(), childTbleSysID };
			super.update(objs);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.repository.MappingRepository#persist(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
public boolean persistNewVariableMapping(Mapping mapping) {
		mstrTbleSysID = sequenceGeneratorRepository
				.generateSequence(masterTbleSeqColName);
		createMappingMasterTableQuery = new CreateMappingMasterTableQuery(
				getDataSource());
		createMappingMasterTableQuery.insert(mstrTbleSysID, mapping); 
		if(mapping.getEB01Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getEb01().getName(),mapping.getEb01().getHippaCodeSelectedValues(), mapping.getUser().getCreatedUserName());
			
			persistInExtndMsgTable(mstrTbleSysID,mapping.getEb01().getExtendedMsgsForSelectedValues(), mapping.getEb01().getName(), mapping);
		}
		if(mapping.getEB02Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getEb02().getName(),mapping.getEb02().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		// SSCR19537 change - deleted EB03 values
		/** SSCR19537 - persisting EB03 association object in the BX_CNTRCT_VAR_MAPG_VAL table*/
		String tableName = DomainConstants.MAIN_MAPPING_VAL;
		String createdUser = null;
		if (null != mapping.getUser()) {
			createdUser = mapping.getUser().getCreatedUserName();
		}
		if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() && !mapping.getEb03().getEb03Association().isEmpty()) {
			persistEb03Associations(mapping.getEb03().getEb03Association(), createdUser, tableName);
			HippaCodeValue eb01Obj = (HippaCodeValue) mapping.getEb01().getHippaCodeSelectedValues().get(0);
			if(eb01Obj.getValue().equals("D")){
				createInExtndMsgTableForEb03(mstrTbleSysID,mapping.getEb03().getExtendedMsgsForSelectedValues(), mapping.getEb03().getName(), mapping);
			}
		}
		if(mapping.getEB06Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getEb06().getName(),mapping.getEb06().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getEB09Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getEb09().getName(),mapping.getEb09().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getAccumValues() != null && mapping.getAccumValues().size() > 0){
			persistChildTable(mstrTbleSysID,mapping.getAccum().getName(),mapping.getAccum().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd01Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd01().getName(),mapping.getHsd01().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd02Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd02().getName(),mapping.getHsd02().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd03Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd03().getName(),mapping.getHsd03().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd04Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd04().getName(),mapping.getHsd04().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd05Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd05().getName(),mapping.getHsd05().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd06Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd06().getName(),mapping.getHsd06().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd07Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd07().getName(),mapping.getHsd07().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getHsd08Value() != null){
			persistChildTable(mstrTbleSysID,mapping.getHsd08().getName(),mapping.getHsd08().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		if(mapping.getUtilizationMgmntRule() != null){
			persistChildTable(mstrTbleSysID,mapping.getUtilizationMgmntRule().getName(),mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		// Start Age Mapping for a variable -- BXNI June 2012 Release.
		if(mapping.getStartAge() != null){
			persistChildTable(mstrTbleSysID,mapping.getStartAge().getName(),mapping.getStartAge().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		// End Age Mapping for a variable -- BXNI June 2012 Release.
		if(mapping.getEndAge() != null){
			persistChildTable(mstrTbleSysID,mapping.getEndAge().getName(),mapping.getEndAge().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		// NM1 Message Segment
		if(mapping.getNm1MessageSegment() != null){
			persistChildTable(mstrTbleSysID,mapping.getNm1MessageSegment().getName(),mapping.getNm1MessageSegment().getHippaCodeSelectedValues(),  mapping.getUser().getCreatedUserName());
		}
		return true;
	}
	
	public void persistChildTable(Long sysID, String hippaSegmentName,
			List hippaCodeSelectedValues, String createdUserName) {
		sequenceNo = 0;
		for (int i = 0; i < hippaCodeSelectedValues.size(); i++) {
			hippaCodeValueObj = (HippaCodeValue) hippaCodeSelectedValues.get(i);
			if(hippaCodeValueObj.getValue() != null && hippaCodeValueObj.getValue().trim().length() > 0 ){
				sequenceNo = sequenceNo + 1;
				childTbleInsertQuery = new StringBuffer();
				childTbleSysID = sequenceGeneratorRepository
						.generateSequence(childTbleSeqColName);
				childTbleInsertQuery
						.append("INSERT INTO BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
								+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
								+ "VAR_MAPG_VAL_SYS_ID) VALUES (?,?,?,?,?,"
								+ "sysdate,?)");
				createMappingChildTableQuery = new CreateMappingChildTableQuery(
						getDataSource(), childTbleInsertQuery.toString());
				createMappingChildTableQuery.insert(hippaSegmentName.toUpperCase(),
						hippaCodeValueObj, createdUserName.toUpperCase());
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.repository.MappingRepository#findMatchingVariables(com.wellpoint.ets.bx.mapping.domain.entity.Variable)
	 */
	public List findMatchingVariables(Variable variable) {
		// TODO Auto-generated method stub
		List variables = new ArrayList();
		return variables;
	}

	/**
	 * @return Returns the datasource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param datasource
	 *            The datasource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private class RetrieveMappingQuery extends MappingSqlQuery {

		public RetrieveMappingQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MappingRetrieveResult result = new MappingRetrieveResult();
			result.setVariableId(rs.getString("cntrct_var_id"));
			result.setDescription(rs.getString("cntrct_var_desc"));
			result.setSensitiveBenefitIndicator(rs.getString("sen_bnft_ind"));
			result.setVariableMappingStatus(rs.getString("var_mapg_stts_cd"));
			result.setSeq_num(Long.valueOf(rs.getLong("seq_num")));
			result.setHippaSegment(rs.getString("data_element_id"));
			result.setHippaValues(rs.getString("data_element_val"));
			result.setEB03Association(rs.getString("EB03_ASSN"));
			result.setHippaValuesDesc(rs.getString("val_desc_txt"));
			result.setVariableMappingSysId(Long.valueOf(rs.getLong("var_mapg_sys_id")));
			result.setInTempTab(rs.getString("in_temp_tab"));
			result.setHippaCodeValueSysid(Long.valueOf(rs.getLong("VAR_MAPG_VAL_SYS_ID")));
			result.setCreatedUser(rs.getString("CREATD_USER_ID"));
			result.setCreatedTime(rs.getDate("CREATD_TM_STMP"));
			result.setLastChangedUser(rs.getString("LST_CHG_USR"));
			result.setLastUpdatedTime(rs.getDate("LST_CHG_TMS"));
			result.setRuleShortDesc(rs.getString("rule_shrt_desc"));
			result.setAuditLock(rs.getString("AUDIT_LOCK"));
			result.setProcedureExcludedInd(rs.getString("PROC_EXCLD_IND"));
			// SSCR19537
			result.setIndvdlEb03AssnIndicator(rs.getString("INDVDL_EB03_ASSN_IND"));
			/**
			 * MTM CODE CHANGES
			 */
			String checkForFinalized=rs.getString("MAPPNG_CMP_IND");
			if(null != checkForFinalized){
				if (checkForFinalized.equals("N")){
					result.setFinalized(false);
				}
				else if(checkForFinalized.equals("Y")){
					result.setFinalized(true);
				}
			}	
			/**
			 * END
			 */
			//Added for datafeed req
			result.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			return result;
		}
	}

	private class ExtMsgRetrieveQuery extends MappingSqlQuery {

		public ExtMsgRetrieveQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExtendedMessageMapping result = new ExtendedMessageMapping();
			result.setExtendedMsgValueSysId(Long.valueOf(rs.getString("var_extnd_msg_val_sys_id")));
			result.setSeq_num(Long.valueOf(rs.getString("seq_num")));
			result.setSegmentName(rs.getString("data_element_id"));
			result.setExtndMsg1(rs.getString("EXTND_MSG_TXT1"));
			result.setExtndMsg2(rs.getString("EXTND_MSG_TXT2"));
			result.setExtndMsg3(rs.getString("EXTND_MSG_TXT3"));
			result.setChangeInd(rs.getString("chg_ind"));
			result.setNetworkInd(rs.getString("eb12_ind"));
			result.setValue(rs.getString("eb0_assn"));		
			return result;
		}
	}
	
	private class HPNMapgRetrieveQuery extends MappingSqlQuery {

		public HPNMapgRetrieveQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HPNMessageMapping result = new HPNMessageMapping();
			result.setServiceTyCd(rs.getString("BNFT_SRVC_CTGRY_TYPE_CD"));
			result.setHighPrfrmnNonTierdMsgTxt(rs.getString("HIGH_PRFRMN_NON_TIERD_MSG_TXT"));
			result.setHighPrfrmnTierdMsgTxt(rs.getString("HIGH_PRFRMN_TIERD_MSG_TXT"));
			return result;
		}
	}
	
	private class CountEB03Query extends MappingSqlQuery {

		public CountEB03Query(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Long.valueOf(rs.getInt(1));
		}
	}
	
	private class RetrieveMappingQueryFromTemp extends MappingSqlQuery {

		public RetrieveMappingQueryFromTemp(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MappingRetrieveResult result = new MappingRetrieveResult();
			result.setVariableId(rs.getString("cntrct_var_id"));
			result.setDescription(rs.getString("cntrct_var_desc"));
			result.setSensitiveBenefitIndicator(rs.getString("sen_bnft_ind"));
			result.setVariableMappingStatus(rs.getString("var_mapg_stts_cd"));
			result.setSeq_num(Long.valueOf(rs.getLong("seq_num")));
			result.setHippaSegment(rs.getString("data_element_id"));
			result.setHippaValues(rs.getString("data_element_val"));
			result.setEB03Association(rs.getString("EB03_ASSN"));
			result.setHippaValuesDesc(rs.getString("val_desc_txt"));
			result.setVariableMappingSysId(Long.valueOf(rs.getLong("var_mapg_sys_id")));
			result.setHippaCodeValueSysid(Long.valueOf(rs.getLong("TEMP_VAR_MAPG_VAL_SYS_ID")));
			result.setCreatedUser(rs.getString("CREATD_USER_ID"));
			result.setCreatedTime(rs.getDate("CREATD_TM_STMP"));
			result.setLastChangedUser(rs.getString("LST_CHG_USR"));
			result.setLastUpdatedTime(rs.getDate("LST_CHG_TMS"));
			result.setRuleShortDesc(rs.getString("RULE_SHRT_DESC"));
			result.setAuditLock(rs.getString("AUDIT_LOCK"));
			result.setProcedureExcludedInd(rs.getString("PROC_EXCLD_IND"));
			/**
			 * MTM CODE CHANGES
			 */
			String checkForFinalizedBeingModified=rs.getString("MAPPNG_CMP_IND");
			if(null != checkForFinalizedBeingModified){
				if (checkForFinalizedBeingModified.equals("N")){
					result.setFinalized(false);
				}
				else if(checkForFinalizedBeingModified.equals("Y")){
					result.setFinalized(true);
				}
			}	
			/**
			 * END
			 */
			result.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			//Added as part of SSCR 19537
			result.setIndvdlEb03AssnIndicator(rs.getString("INDVDL_EB03_ASSN_IND"));
			return result;
		}
	}

	public Mapping retrieveMapping(String variableId) {
		Mapping mappingActual = null;
		final String retrieveQuery = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, "
			+ "mstr.sen_bnft_ind, "
			+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
			+ "mapg_val.EB03_ASSN, hippa_val.val_desc_txt,rul.rule_shrt_desc, mstr.var_mapg_sys_id, mstr.in_temp_tab,mapg_val.VAR_MAPG_VAL_SYS_ID, "
			+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS,mstr.MAPPNG_CMP_IND, mstr.DATA_FEED_IND,mstr.AUDIT_LOCK,mstr.PROC_EXCLD_IND, mstr.INDVDL_EB03_ASSN_IND "
			+ "from bx_cntrct_var_mapg mstr left outer join bx_cntrct_var_mapg_val mapg_val on " 
			+ "mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id "
			+ "left outer join rule rul on " 
			+ "mapg_val.data_element_id = rul.rule_typ_cd and mapg_val.data_element_val = rul.rule_id "
			+ "left outer join bx_hippa_segment_val hippa_val on "
			+ "hippa_val.data_element_id = mapg_val.data_element_id "
			+ "and hippa_val.data_element_val= mapg_val.data_element_val "
			+ "where mstr.cntrct_var_id = '"
			+ variableId
			+ "'" 
			+ " order by  mapg_val.data_element_id, mapg_val.seq_num";		
	/*	
		final String retrieveQuery = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, mstr.msg, "
			+ "mstr.msg_type, mstr.sen_bnft_ind, "
			+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
			+ "hippa_val.val_desc_txt, mstr.var_mapg_sys_id, mstr.in_temp_tab, mapg_val.VAR_MAPG_VAL_SYS_ID, "
			+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS "
			+ "from bx_cntrct_var_mapg mstr, bx_cntrct_var_mapg_val mapg_val ,bx_hippa_segment_val hippa_val "
			+ "where mstr.cntrct_var_id = '"
			+ variableId
			+ "'"
			+ " and mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id "
			+ "and hippa_val.data_element_id = mapg_val.data_element_id "
			+ "and hippa_val.data_element_val= mapg_val.data_element_val";*/

		log.debug("Query :" + retrieveQuery);
		RetrieveMappingQuery retrieveMappingQuery = new RetrieveMappingQuery(
				dataSource, retrieveQuery);
	
		List resultMappingRetrieveMstr = retrieveMappingQuery.execute();		
		
		mappingActual = createMappingObjectFromResult(resultMappingRetrieveMstr);
		if(null != mappingActual && null != mappingActual.getEb03()
				&& null != mappingActual.getEb03().getEb03Association() 
				&& !mappingActual.getEb03().getEb03Association().isEmpty())
		{
		tempEb03Values = mappingActual.getEb03().getEb03Association();
		}
		final String retrieveExtndMsgQuery = "SELECT * FROM  BX_EXTND_MSG WHERE VAR_MAPG_SYS_ID = '"
				+ mappingActual.getVariableSystemId()
				+ "'" 
				+ " AND CHG_IND != 'D' order by DATA_ELEMENT_ID, SEQ_NUM";
		
		log.debug("RetrieveExtndMsgQuery :" + retrieveExtndMsgQuery);
		ExtMsgRetrieveQuery extMsgRetrieveQuery = new ExtMsgRetrieveQuery(dataSource, retrieveExtndMsgQuery);
	
		List<Object> retrivedExtMsgList = extMsgRetrieveQuery.execute();		
		mappingActual = createExtMsgObj(retrivedExtMsgList,mappingActual);
		
		//HPN UI Description
		final String retrieveHPNMapgQuery = "SELECT BX.BNFT_SRVC_CTGRY_TYPE_CD, BX.HIGH_PRFRMN_NON_TIERD_MSG_TXT, BX.HIGH_PRFRMN_TIERD_MSG_TXT FROM BX_AUTO_VAR_MAPG BX "
				+ "WHERE BX.CNTRCT_VAR = '"
				+ variableId
				+ "' ";
				/*+ "UNION "
				+ "SELECT BXT.BNFT_SRVC_CTGRY_TYPE_CD, BXT.HIGH_PRFRMN_NON_TIERD_MSG_TXT, BXT.HIGH_PRFRMN_TIERD_MSG_TXT FROM BX_AUTO_VAR_MAPG_TEST BXT "
				+ "WHERE BXT.CNTRCT_VAR = '"
				+ variableId
				+ "'";*/
		log.debug("RetrieveHPNMapgQuery : " + retrieveHPNMapgQuery);
		HPNMapgRetrieveQuery mapgRetrieveQuery = new HPNMapgRetrieveQuery(dataSource, retrieveHPNMapgQuery);
		List<Object> retrivedHPNMapgList = mapgRetrieveQuery.execute();
		mappingActual.setHpnMapgList(retrivedHPNMapgList);
		return mappingActual;
	}
	
	private Mapping createExtMsgObj(List retrivedExtMsgList, Mapping mappingActual) {

		if((!retrivedExtMsgList.isEmpty()) || (retrivedExtMsgList.size() != 0)){
			List<ExtendedMessageMapping> eb03ExtndMsgs = new ArrayList<ExtendedMessageMapping>();
			Iterator iterator = retrivedExtMsgList.iterator();
			while(iterator.hasNext()){
				ExtendedMessageMapping resultObj = (ExtendedMessageMapping) iterator.next();
				if(resultObj.getSegmentName() != null){				
					if (resultObj.getSegmentName().equals("EB01")) {	
						List<ExtendedMessageMapping> eb01ExtndMsgs = new ArrayList<ExtendedMessageMapping>();
						HippaSegment eb01Hippa = mappingActual.getEb01();
						eb01Hippa.setExtendedMsgsForSelectedValues(addExtndMsgs(resultObj,eb01ExtndMsgs));
						mappingActual.setEb01(eb01Hippa);
					}
					if (resultObj.getSegmentName().equals("EB03")) {						
						addExtndMsgs(resultObj,eb03ExtndMsgs);
					}
				}
			}
			HippaSegment eb03Hippa = mappingActual.getEb03();
			if(eb03Hippa != null){
				eb03Hippa.setExtendedMsgsForSelectedValues(eb03ExtndMsgs);
				mappingActual.setEb03(eb03Hippa);
			} else{
				HippaSegment hs = new HippaSegment();
				mappingActual.setEb03(hs);
			}
		}else{
			if(mappingActual.getEb01() ==  null){
				HippaSegment eb01Hippa = new HippaSegment();
				mappingActual.setEb01(eb01Hippa);	
			}
			if(mappingActual.getEb03() ==  null){
				HippaSegment eb03Hippa = new HippaSegment();
				mappingActual.setEb03(eb03Hippa);	
			}
		}		
		return mappingActual;
	}
	
	public List<ExtendedMessageMapping> addExtndMsgs(ExtendedMessageMapping resultObj, List<ExtendedMessageMapping> extndMsgs) {
		ExtendedMessageMapping obj = new ExtendedMessageMapping();
		obj.setExtendedMsgValueSysId(resultObj.getExtendedMsgValueSysId());
		obj.setSeq_num(resultObj.getSeq_num());
		obj.setSegmentName(resultObj.getSegmentName());
		obj.setExtndMsg1(resultObj.getExtndMsg1());
		obj.setExtndMsg2(resultObj.getExtndMsg2());
		obj.setExtndMsg3(resultObj.getExtndMsg3());
		//obj.setChangeInd(resultObj.getChangeInd());
		obj.setNetworkInd(resultObj.getNetworkInd());
		obj.setValue(resultObj.getValue());
		extndMsgs.add(obj);
		return extndMsgs;
	}
	
	public List<Mapping> retrieveMappingForProdDataFeed(int inClauseSize, List<String> variableList) {
		Mapping mappingActual = null;
		List<Mapping> resultMapping = new ArrayList<Mapping>();
		int variableListSize = variableList.size();
		List<MappingRetrieveResult> resultMappingRetrieveMstr = new ArrayList<MappingRetrieveResult>();
		int divisionNo = variableListSize/inClauseSize;
		int reminderNo = variableListSize%inClauseSize;
		int i = 0;
		for(i=0; i<divisionNo ; i+=inClauseSize) {
			List<String> retrieveList = variableList.subList(i,i+(inClauseSize-1));
			final String retrieveQuery = getRetrieveQueryForMappingProdDataFeed(retrieveList);	
			log.debug("Query :" + retrieveQuery);
			RetrieveMappingQuery retrieveMappingQuery = new RetrieveMappingQuery(
					dataSource, retrieveQuery);
			resultMappingRetrieveMstr.addAll(retrieveMappingQuery.execute());
		}
		if(reminderNo != 0) {
			List<String> retrieveList = variableList.subList(i,i+reminderNo);
			final String retrieveQuery = getRetrieveQueryForMappingProdDataFeed(retrieveList);
			RetrieveMappingQuery retrieveMappingQuery = new RetrieveMappingQuery(
					dataSource, retrieveQuery);
			resultMappingRetrieveMstr.addAll(retrieveMappingQuery.execute());
			log.debug("Query :" + retrieveQuery);
		}
		if((!resultMappingRetrieveMstr.isEmpty()) || (resultMappingRetrieveMstr.size() != 0)){
			for(String variable : variableList) {
				List<MappingRetrieveResult> resultMappingObjectList = new ArrayList<MappingRetrieveResult>();
				for(MappingRetrieveResult result : resultMappingRetrieveMstr) {
					if(result.getVariableId().equalsIgnoreCase(variable)){
							resultMappingObjectList.add(result);
					}
				}
				mappingActual = createMappingObjectFromResultForDataFeed(resultMappingObjectList);
				resultMapping.add(mappingActual);
			}
		}
		return resultMapping;
	}

	/**
	 * @param retrieveList
	 * @return
	 */
	private String getRetrieveQueryForMappingProdDataFeed(
			List<String> retrieveList) {
		String parameters = StringUtils.join(retrieveList.iterator(),"','"); 
		final String retrieveQuery = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, "
			+ "mstr.sen_bnft_ind, "
			+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
			+ "mapg_val.EB03_ASSN, hippa_val.val_desc_txt,rul.rule_shrt_desc, mstr.var_mapg_sys_id, mstr.in_temp_tab,mapg_val.VAR_MAPG_VAL_SYS_ID, "
			+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS,mstr.MAPPNG_CMP_IND, mstr.DATA_FEED_IND,mstr.AUDIT_LOCK,mstr.PROC_EXCLD_IND, mstr.INDVDL_EB03_ASSN_IND "
			+ "from bx_cntrct_var_mapg mstr left outer join bx_cntrct_var_mapg_val mapg_val on " 
			+ "mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id "
			+ "left outer join rule rul on " 
			+ "mapg_val.data_element_id = rul.rule_typ_cd and mapg_val.data_element_val = rul.rule_id "
			+ "left outer join bx_hippa_segment_val hippa_val on "
			+ "hippa_val.data_element_id = mapg_val.data_element_id "
			+ "and hippa_val.data_element_val= mapg_val.data_element_val "			
			+ "where mstr.cntrct_var_id IN('"
			+ parameters
			+ "') "
			+ "order by  mapg_val.data_element_id, mapg_val.seq_num";
		return retrieveQuery;
	}
	
	public List<Mapping> retrieveBeingModifiedForProdDataFeed(int inClauseSize,List<String> variableList) {
		Mapping mappingActual = null;
		List<Mapping> resultMapping = new ArrayList<Mapping>();
		int variableListSize = variableList.size();
		List<MappingRetrieveResult> resultMappingRetrieveMstr = new ArrayList<MappingRetrieveResult>();
		int divisionNo = variableListSize/inClauseSize;
		int reminderNo = variableListSize%inClauseSize;
		int i = 0;
		for(i=0; i<divisionNo ; i+=inClauseSize) {
			List<String> retrieveList = variableList.subList(i,i+(inClauseSize-1));
			final String retrieveQuery = getRetrieveQueryForBeingModifiedProdDataFeed(retrieveList);
			RetrieveMappingQueryFromTemp retrieveMappingQuery = new RetrieveMappingQueryFromTemp(
					dataSource, retrieveQuery);
			resultMappingRetrieveMstr.addAll(retrieveMappingQuery.execute());
			log.debug("Query :" + retrieveQuery);
		}
		if(reminderNo != 0) {
			List<String> retrieveList = variableList.subList(i,i+reminderNo);
			final String retrieveQuery = getRetrieveQueryForBeingModifiedProdDataFeed(retrieveList);
			RetrieveMappingQueryFromTemp retrieveMappingQuery = new RetrieveMappingQueryFromTemp(
					dataSource, retrieveQuery);
			resultMappingRetrieveMstr.addAll(retrieveMappingQuery.execute());
			log.debug("Query :" + retrieveQuery);
		}
		if((!resultMappingRetrieveMstr.isEmpty()) || (resultMappingRetrieveMstr.size() != 0)){
			for(String variable : variableList) {
				List<MappingRetrieveResult> resultMappingObjectList = new ArrayList<MappingRetrieveResult>();
				for(MappingRetrieveResult result : resultMappingRetrieveMstr) {
					if(result.getVariableId().equalsIgnoreCase(variable)){
							resultMappingObjectList.add(result);
					}
				}
				mappingActual = createMappingObjectFromResultForDataFeed(resultMappingObjectList);
				resultMapping.add(mappingActual);
			}
		}
		return resultMapping;
	}

	/**
	 * @param retrieveList
	 * @return
	 */
	private String getRetrieveQueryForBeingModifiedProdDataFeed(
			List<String> retrieveList) {
		String parameters = StringUtils.join(retrieveList.iterator(),"','"); 
		final String retrieveQuery = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, "
			+ "mstr.sen_bnft_ind, "
			+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
			+ "mapg_val.EB03_ASSN, hippa_val.val_desc_txt, rul.rule_shrt_desc, mstr.var_mapg_sys_id, mapg_val.TEMP_VAR_MAPG_VAL_SYS_ID, "
			+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS ,mstr.MAPPNG_CMP_IND, mstr.DATA_FEED_IND,mstr.AUDIT_LOCK,mstr.PROC_EXCLD_IND, mstr.INDVDL_EB03_ASSN_IND "
			+ "from temp_bx_cntrct_var_mapg mstr left outer join temp_bx_cntrct_var_mapg_val mapg_val on " 
			+ "mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id " 
			+ "left outer join rule rul on mapg_val.data_element_id = rul.rule_typ_cd and mapg_val.data_element_val = rul.rule_id "
			+ "left outer join bx_hippa_segment_val hippa_val on "
			+ "hippa_val.data_element_id = mapg_val.data_element_id "
			+ "and hippa_val.data_element_val= mapg_val.data_element_val "			
			+ "where mstr.cntrct_var_id IN('"
			+ parameters
			+ "') "
			+ "order by  mapg_val.data_element_id, mapg_val.seq_num";
		return retrieveQuery;
	}
	
	public List<Mapping> retrieveMappingForTestDataFeed() {
		Mapping mappingActual = null;
		List<Mapping> resultMapping = new ArrayList<Mapping>();
		final String retrieveQuery = "(SELECT DISTINCT CNTRCT_VAR_ID, CNTRCT_VAR_DESC, "
			+ " SEN_BNFT_IND, VAR_MAPG_STTS_CD, VAR_MAPG_SYS_ID, "
			+ " CREATD_USER_ID, CREATD_TM_STMP, LST_CHG_USR, LST_CHG_TMS, MAPPNG_CMP_IND, DATA_FEED_IND, AUDIT_LOCK,INDVDL_EB03_ASSN_IND "
			+ " FROM BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST' and IN_TEMP_TAB='N')"
			+ " UNION "
			+ " (SELECT DISTINCT CNTRCT_VAR_ID, CNTRCT_VAR_DESC, "
			+ " SEN_BNFT_IND, VAR_MAPG_STTS_CD, VAR_MAPG_SYS_ID, "
			+ " CREATD_USER_ID, CREATD_TM_STMP, LST_CHG_USR, LST_CHG_TMS, MAPPNG_CMP_IND, DATA_FEED_IND, AUDIT_LOCK,INDVDL_EB03_ASSN_IND "
			+ " FROM TEMP_BX_CNTRCT_VAR_MAPG WHERE VAR_MAPG_STTS_CD = 'SCHEDULED_TO_TEST')";
			RetrieveMappingQueryForDataFeed retrieveMappingQuery = new RetrieveMappingQueryForDataFeed(
					dataSource, retrieveQuery);
			List<MappingRetrieveResult> resultMappingRetrieveMstr = retrieveMappingQuery.execute();
		if((!resultMappingRetrieveMstr.isEmpty()) || (resultMappingRetrieveMstr.size() != 0)){
			for(MappingRetrieveResult result : resultMappingRetrieveMstr) {
				mappingActual = new Mapping();
				Variable variable = new Variable();
				variable.setVariableId(result.getVariableId());
				variable.setDescription(result.getDescription());
				mappingActual.setVariable(variable);
				mappingActual.setVariableMappingStatus(result
						.getVariableMappingStatus());
				mappingActual.setSensitiveBenefitIndicator(result
						.getSensitiveBenefitIndicator());
				mappingActual.setVariableSystemId(result.getVariableMappingSysId());
				mappingActual.getUser().setCreatedUserName(result.getCreatedUser());
				mappingActual.getUser().setLastUpdatedUserName(result.getLastChangedUser());
				mappingActual.setCreatedTmStamp(result.getCreatedTime());
				mappingActual.setLastChangedTmStamp(result.getLastUpdatedTime());
				mappingActual.setFinalized(result.isFinalized());
				mappingActual.setDataFeedInd(result.getDataFeedInd());
				mappingActual.setAuditLock(result.getAuditLock());
				mappingActual.setIndvdlEb03AssnIndicator(result.getIndvdlEb03AssnIndicator());
				resultMapping.add(mappingActual);
			}
		}
		return resultMapping;
	}
	
	private class RetrieveMappingQueryForDataFeed extends MappingSqlQuery {

		public RetrieveMappingQueryForDataFeed(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			//rs.setFetchSize(1000);
			MappingRetrieveResult result = new MappingRetrieveResult();
			result.setVariableId(rs.getString("cntrct_var_id"));
			result.setDescription(rs.getString("cntrct_var_desc"));
			result.setSensitiveBenefitIndicator(rs.getString("sen_bnft_ind"));
			result.setVariableMappingStatus(rs.getString("var_mapg_stts_cd"));
			result.setVariableMappingSysId(Long.valueOf(rs.getLong("var_mapg_sys_id")));
			result.setCreatedUser(rs.getString("CREATD_USER_ID"));
			result.setCreatedTime(rs.getDate("CREATD_TM_STMP"));
			result.setLastChangedUser(rs.getString("LST_CHG_USR"));
			result.setLastUpdatedTime(rs.getDate("LST_CHG_TMS"));
			result.setAuditLock(rs.getString("AUDIT_LOCK"));
			result.setAuditLock(rs.getString("INDVDL_EB03_ASSN_IND"));
			/**
			 * MTM CODE CHANGES
			 */
			String checkForFinalized=rs.getString("MAPPNG_CMP_IND");
			if(null != checkForFinalized){
				if (checkForFinalized.equals("N")){
					result.setFinalized(false);
				}
				else if(checkForFinalized.equals("Y")){
					result.setFinalized(true);
				}
			}	
			/**
			 * END
			 */
			//Added for datafeed req
			result.setDataFeedInd(rs.getString("DATA_FEED_IND"));
			return result;
		}
	}

	public Mapping retrieveBeingModified(String variableId) {

		List resultMappingRetrieveTemp = null;
		Mapping mappingActual = null;
		if(variableId!=null){
		    variableId = variableId.trim();
		}
		final String retrieveQueryFromTemp = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, "
			+ "mstr.sen_bnft_ind, "
			+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
			+ "mapg_val.EB03_ASSN, hippa_val.val_desc_txt, rul.rule_shrt_desc, mstr.var_mapg_sys_id, mapg_val.TEMP_VAR_MAPG_VAL_SYS_ID, "
			+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS ,mstr.MAPPNG_CMP_IND, mstr.DATA_FEED_IND,mstr.AUDIT_LOCK,mstr.PROC_EXCLD_IND, mstr.INDVDL_EB03_ASSN_IND "
			+ "from temp_bx_cntrct_var_mapg mstr left outer join temp_bx_cntrct_var_mapg_val mapg_val on " 
			+ "mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id " 
			+ "left outer join rule rul on mapg_val.data_element_id = rul.rule_typ_cd and mapg_val.data_element_val = rul.rule_id "
			+ "left outer join bx_hippa_segment_val hippa_val on "
			+ "hippa_val.data_element_id = mapg_val.data_element_id "
			+ "and hippa_val.data_element_val= mapg_val.data_element_val "
			+ ""
			+ "where mstr.cntrct_var_id = '"
			+ variableId
			+ "'" 
			+ " order by  mapg_val.data_element_id, mapg_val.seq_num";
		/*final String retrieveQueryFromTemp = "select distinct mstr.cntrct_var_id, mstr.cntrct_var_desc, mstr.msg, "
				+ "mstr.msg_type, mstr.sen_bnft_ind, "
				+ "mstr.var_mapg_stts_cd, mapg_val.seq_num, mapg_val.data_element_id, mapg_val.data_element_val, "
				+ "hippa_val.val_desc_txt, mstr.var_mapg_sys_id, mapg_val.TEMP_VAR_MAPG_VAL_SYS_ID, "
				+ "mstr.CREATD_USER_ID, mstr.CREATD_TM_STMP, mstr.LST_CHG_USR, mstr.LST_CHG_TMS "
				+ "from temp_bx_cntrct_var_mapg mstr, temp_bx_cntrct_var_mapg_val mapg_val ,bx_hippa_segment_val hippa_val "
				+ "where mstr.cntrct_var_id = '"+ variableId+ "' "
				+ "and mstr.var_mapg_sys_id = mapg_val.var_mapg_sys_id "
				+ "and hippa_val.data_element_id = mapg_val.data_element_id "
				+ "and hippa_val.data_element_val= mapg_val.data_element_val";*/
		//WLPRD02076161--->start
		//System.err.println("retrieveBeingModified --> "+retrieveQueryFromTemp);
		log.info("retrieveBeingModified --> "+retrieveQueryFromTemp);
		//WLPRD02076161--->end
		RetrieveMappingQueryFromTemp retrieveMappingQueryFromTemp = new RetrieveMappingQueryFromTemp(
				dataSource, retrieveQueryFromTemp);
		resultMappingRetrieveTemp = retrieveMappingQueryFromTemp.execute();
		mappingActual = createMappingObjectFromResult(resultMappingRetrieveTemp);
		if(null != mappingActual && null != mappingActual.getEb03()
				&& null != mappingActual.getEb03().getEb03Association() 
				&& !mappingActual.getEb03().getEb03Association().isEmpty())
		{
		tempEb03Values = mappingActual.getEb03().getEb03Association();
		}
		//extd msg
		final String retrieveExtndMsgQuery = "SELECT * FROM  BX_EXTND_MSG WHERE VAR_MAPG_SYS_ID = '"
				+ mappingActual.getVariableSystemId()
				+ "'" 
				+ " AND CHG_IND != 'D' order by DATA_ELEMENT_ID, SEQ_NUM";
		
		log.debug("RetrieveExtndMsgQuery :" + retrieveExtndMsgQuery);
		ExtMsgRetrieveQuery extMsgRetrieveQuery = new ExtMsgRetrieveQuery(dataSource, retrieveExtndMsgQuery);
	
		List<Object> retrivedExtMsgList = extMsgRetrieveQuery.execute();		
		mappingActual = createExtMsgObj(retrivedExtMsgList,mappingActual);
	
		//HPN UI Description
		final String retrieveHPNMapgQuery = "SELECT BX.BNFT_SRVC_CTGRY_TYPE_CD, BX.HIGH_PRFRMN_NON_TIERD_MSG_TXT, BX.HIGH_PRFRMN_TIERD_MSG_TXT FROM BX_AUTO_VAR_MAPG BX "
				+ "WHERE BX.CNTRCT_VAR = '"
				+ variableId
				+ "' "
				+ "UNION "
				+ "SELECT BXT.BNFT_SRVC_CTGRY_TYPE_CD, BXT.HIGH_PRFRMN_NON_TIERD_MSG_TXT, BXT.HIGH_PRFRMN_TIERD_MSG_TXT FROM BX_AUTO_VAR_MAPG_TEST BXT "
				+ "WHERE BXT.CNTRCT_VAR = '"
				+ variableId
				+ "'";
		log.debug("RetrieveHPNMapgQuery : " + retrieveHPNMapgQuery);
		HPNMapgRetrieveQuery mapgRetrieveQuery = new HPNMapgRetrieveQuery(dataSource, retrieveHPNMapgQuery);
		List<Object> retrivedHPNMapgList = mapgRetrieveQuery.execute();
		mappingActual.setHpnMapgList(retrivedHPNMapgList);
		
		return 	mappingActual;
	}

	public HippaSegment addHippaSegment(MappingRetrieveResult result) {
		List selectedHippaSegmentValues = new ArrayList();
		HippaSegment hippaSegment = new HippaSegment(result.getHippaSegment());
		HippaCodeValue code = new HippaCodeValue();
		code.setValue(result.getHippaValues());
		code.setDescription(result.getHippaValuesDesc());
		code.setSeq_num(result.getSeq_num());
		code.setHippaCodeValueSysId(result.getHippaCodeValueSysid());
		selectedHippaSegmentValues.add(code);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);

		return hippaSegment;
	}

	private Mapping createMappingObjectFromResult(List retrievedMappingList) {

		Mapping mappingActual = new Mapping();
		if((!retrievedMappingList.isEmpty()) || (retrievedMappingList.size() != 0)){
			MappingRetrieveResult result = (MappingRetrieveResult) retrievedMappingList
					.get(0);
			mappingActual = createMappingObjectFromResult(retrievedMappingList,
					result);
				List variableList = retrieveVariableInfo(mappingActual.getVariable());
				mappingActual.setVariableList(variableList);
		}
		
		return mappingActual;

	}
	
	private Mapping createMappingObjectFromResultForDataFeed(List retrievedMappingList) {

		Mapping mappingActual = new Mapping();
		if((!retrievedMappingList.isEmpty()) || (retrievedMappingList.size() != 0)){
			MappingRetrieveResult result = (MappingRetrieveResult) retrievedMappingList
					.get(0);
			mappingActual = createMappingObjectFromResult(retrievedMappingList,
					result);
		}
		
		return mappingActual;

	}

	private Mapping createMappingObjectFromResult(List retrievedMappingList,
			MappingRetrieveResult result) {
		Mapping mappingActual = new Mapping();
		HippaSegment eb03 = new HippaSegment();
		HippaSegment accum = new HippaSegment();
		HippaSegment hsd02 = new HippaSegment();
		HippaSegment utilizationMgmntRule = new HippaSegment();
		//mappingActual.setVariableSystemId(result.getVariableMappingSysId());
		Variable variable = new Variable();
		variable.setVariableId(result.getVariableId());
		variable.setDescription(result.getDescription());
		mappingActual.setVariable(variable);
		mappingActual.setVariableMappingStatus(result
				.getVariableMappingStatus());
		mappingActual.setSensitiveBenefitIndicator(result
				.getSensitiveBenefitIndicator());
		mappingActual.setInTempTable(result.getInTempTab());
		mappingActual.setVariableSystemId(result.getVariableMappingSysId());
		mappingActual.getUser().setCreatedUserName(result.getCreatedUser());
		mappingActual.getUser().setLastUpdatedUserName(result.getLastChangedUser());
		mappingActual.setCreatedTmStamp(result.getCreatedTime());
		mappingActual.setLastChangedTmStamp(result.getLastUpdatedTime());
		mappingActual.setFinalized(result.isFinalized());
		mappingActual.setDataFeedInd(result.getDataFeedInd());
		mappingActual.setAuditLock(result.getAuditLock());
		//BXNI CR29
		mappingActual.setProcedureExcludedInd(result.getProcedureExcludedInd());
		// SSCR19537 April04
		mappingActual.setIndvdlEb03AssnIndicator(result.getIndvdlEb03AssnIndicator());
		Iterator mappingIterator = retrievedMappingList.iterator();
		List eb03SelectedValues = new ArrayList();
		List hsd02SelectedValues = new ArrayList();
		List accumSelectedValues = new ArrayList();
		List umRuleSelectedValues = new ArrayList();
		List eb03Associations = new ArrayList<EB03Association>();
		Map<String, EB03Association> eb03AssociationMap = new HashMap<String, EB03Association>();
		Map<String, HippaCodeValue> eb03HippaCodeMap = new HashMap<String, HippaCodeValue>();
		
		while (mappingIterator.hasNext()) {

			MappingRetrieveResult resultObj = (MappingRetrieveResult) mappingIterator
					.next();
			if(resultObj.getHippaSegment() != null){
			
				if (resultObj.getHippaSegment().equals("EB01")) {
					mappingActual.setEb01(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("EB02")) {
					mappingActual.setEb02(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("EB03")) {
					eb03.setName(resultObj.getHippaSegment());
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(resultObj.getHippaValues());
					code.setDescription(resultObj.getHippaValuesDesc());
					code.setSeq_num(resultObj.getSeq_num());
					code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
					eb03SelectedValues.add(code);
					
					EB03Association eb03Association;
					if(null == eb03AssociationMap.get(resultObj.getHippaValues())){
						eb03Association = new EB03Association();
						HippaCodeValue eb03Code = new HippaCodeValue();
						eb03Code.setValue(resultObj.getHippaValues());
						eb03Code.setDescription(resultObj.getHippaValuesDesc());
						eb03Code.setSeq_num(resultObj.getSeq_num());
						eb03Code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
						eb03Association.setEb03(eb03Code);
						eb03Association.setEb03String(resultObj.getHippaValues());
						eb03Association.setSeq_num(resultObj.getSeq_num());
					}else{
						eb03Association = eb03AssociationMap.get(resultObj.getHippaValues());
						eb03Association.setSeq_num(resultObj.getSeq_num());
					}
					eb03AssociationMap.put(resultObj.getHippaValues(), eb03Association);
					eb03HippaCodeMap.put(resultObj.getHippaValues(), code);
				}
				if (resultObj.getHippaSegment().equals("EB06")) {
					mappingActual.setEb06(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("EB09")) {
					mappingActual.setEb09(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD01")) {
					mappingActual.setHsd01(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD02")) {
					hsd02.setName(resultObj.getHippaSegment());
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(resultObj.getHippaValues());
					code.setDescription(resultObj.getHippaValuesDesc());
					code.setSeq_num(resultObj.getSeq_num());
					code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
					hsd02SelectedValues.add(code);
					//mappingActual.setHsd02(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD03")) {
					mappingActual.setHsd03(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD04")) {
					mappingActual.setHsd04(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD05")) {
					mappingActual.setHsd05(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD06")) {
					mappingActual.setHsd06(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD07")) {
					mappingActual.setHsd07(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("HSD08")) {
					mappingActual.setHsd08(addHippaSegment(resultObj));
				}
				if (resultObj.getHippaSegment().equals("ACCUM")) {
					accum.setName(resultObj.getHippaSegment());
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(resultObj.getHippaValues());
					code.setDescription(resultObj.getHippaValuesDesc());
					code.setSeq_num(resultObj.getSeq_num());
					code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
					accumSelectedValues.add(code);
				}
				if (resultObj.getHippaSegment().equals(DomainConstants.UMRULE_NAME)) {
					utilizationMgmntRule.setName(resultObj.getHippaSegment());
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(resultObj.getHippaValues());
					code.setDescription(resultObj.getRuleShortDesc());
					code.setSeq_num(resultObj.getSeq_num());
					code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
					umRuleSelectedValues.add(code);
				}
				if (resultObj.getHippaSegment().equals("III02")) {
					if (!DomainConstants.Y.equalsIgnoreCase(resultObj.getIndvdlEb03AssnIndicator())) {
						mappingActual.setIi02(addHippaSegment(resultObj));
					}
					//SSCR15937
					EB03Association eb03Association;
					HippaCodeValue iii02Code;
					if(null != resultObj.getEB03Association() 
							&& !"".equalsIgnoreCase(resultObj.getEB03Association())){
						if(null != eb03AssociationMap.get(resultObj.getEB03Association())){
							eb03Association = eb03AssociationMap.get(resultObj.getEB03Association());
							iii02Code = new HippaCodeValue();
							iii02Code.setValue(resultObj.getHippaValues());
							iii02Code.setDescription(resultObj.getHippaValuesDesc());
							iii02Code.setSeq_num(resultObj.getSeq_num());
							iii02Code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
							eb03Association.setEb03String(resultObj.getEB03Association());
							eb03Association.getIii02List().add(iii02Code);
						}else{
							eb03Association = new EB03Association();
							iii02Code = new HippaCodeValue();
							iii02Code.setValue(resultObj.getHippaValues());
							iii02Code.setDescription(resultObj.getHippaValuesDesc());
							iii02Code.setSeq_num(resultObj.getSeq_num());
							iii02Code.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
							
							eb03Association.getIii02List().add(iii02Code);
							eb03Association.setEb03String(resultObj.getEB03Association());
													
						}
						eb03AssociationMap.put(resultObj.getEB03Association(),eb03Association);
					}
				}
				if (resultObj.getHippaSegment().equals("MSG")) {
					EB03Association eb03Association;
					if(null != resultObj.getEB03Association() 
							&& !"".equalsIgnoreCase(resultObj.getEB03Association())){
						if(null != eb03AssociationMap.get(resultObj.getEB03Association())){
							eb03Association = eb03AssociationMap.get(resultObj.getEB03Association());
							eb03Association.setMessage(resultObj.getHippaValues());
							eb03Association.setMesgValueSysId(resultObj.getHippaCodeValueSysid());
						}else{
							eb03Association = new EB03Association();
							eb03Association.setEb03String(resultObj.getEB03Association());
							eb03Association.setMessage(resultObj.getHippaValues());	
							eb03Association.setMesgValueSysId(resultObj.getHippaCodeValueSysid());
						}
						eb03AssociationMap.put(resultObj.getEB03Association(),eb03Association);
					}
					if (!DomainConstants.Y.equalsIgnoreCase(resultObj.getIndvdlEb03AssnIndicator())) {
						mappingActual.setMessage(resultObj.getHippaValues());
					}
				}
				if (resultObj.getHippaSegment().equals("MSG_REQD")) {
					EB03Association eb03Association;
					if(null != resultObj.getEB03Association() 
							&& !"".equalsIgnoreCase(resultObj.getEB03Association())){
						if(null != eb03AssociationMap.get(resultObj.getEB03Association())){
							eb03Association = eb03AssociationMap.get(resultObj.getEB03Association());
							eb03Association.setMsgReqdInd(resultObj.getHippaValues());
							eb03Association.setMesgReqIndValueSysId(resultObj.getHippaCodeValueSysid());
						}else{
							eb03Association = new EB03Association();
							eb03Association.setEb03String(resultObj.getEB03Association());
							eb03Association.setMsgReqdInd(resultObj.getHippaValues());
							eb03Association.setMesgReqIndValueSysId(resultObj.getHippaCodeValueSysid());
						}
						eb03AssociationMap.put(resultObj.getEB03Association(),eb03Association);
					}
					if (!DomainConstants.Y.equalsIgnoreCase(resultObj.getIndvdlEb03AssnIndicator())) {
						mappingActual.setMsg_type_required(resultObj.getHippaValues());
					}
				}
				if (resultObj.getHippaSegment().equals("NOTE_TYPE_CODE")) {
					if (!DomainConstants.Y.equalsIgnoreCase(resultObj.getIndvdlEb03AssnIndicator())) {
						mappingActual.setNoteTypeCode(addHippaSegment(resultObj));
					}
					//SSCR15937
					EB03Association eb03Association;
					HippaCodeValue noteTypeHippaCode;
					if(null != resultObj.getEB03Association() 
							&& !"".equalsIgnoreCase(resultObj.getEB03Association())){
						if(null != eb03AssociationMap.get(resultObj.getEB03Association())){
							eb03Association = eb03AssociationMap.get(resultObj.getEB03Association());
							noteTypeHippaCode = new HippaCodeValue();
							noteTypeHippaCode.setValue(resultObj.getHippaValues());
							noteTypeHippaCode.setDescription(resultObj.getHippaValuesDesc());
							noteTypeHippaCode.setSeq_num(resultObj.getSeq_num());
							noteTypeHippaCode.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
							eb03Association.setNoteType(noteTypeHippaCode);
						}else{
							eb03Association = new EB03Association();
							eb03Association.setEb03String(resultObj.getEB03Association());
							noteTypeHippaCode = new HippaCodeValue();
							noteTypeHippaCode.setValue(resultObj.getHippaValues());
							noteTypeHippaCode.setDescription(resultObj.getHippaValuesDesc());
							noteTypeHippaCode.setSeq_num(resultObj.getSeq_num());
							noteTypeHippaCode.setHippaCodeValueSysId(resultObj.getHippaCodeValueSysid());
							eb03Association.setNoteType(noteTypeHippaCode);						
						}
						eb03AssociationMap.put(resultObj.getEB03Association(),eb03Association);
					}
				}
				// Start Age Mapping for a variable -- BXNI June 2012 Release.
				if (resultObj.getHippaSegment().equals(DomainConstants.START_AGE_NAME)) {
					mappingActual.setStartAge(addHippaSegment(resultObj));
				}
				// End Age Mapping for a variable -- BXNI June 2012 Release.
				if (resultObj.getHippaSegment().equals(DomainConstants.END_AGE_NAME)) {
					mappingActual.setEndAge(addHippaSegment(resultObj));
				}
				//Added NM1 Message Segment
				if (resultObj.getHippaSegment().equals(DomainConstants.NM1_MSG_SGMNT)) {
					mappingActual.setNm1MessageSegment(addHippaSegment(resultObj));
				}
				
			}//end if	
		}// end while
			if((!eb03SelectedValues.isEmpty()) || (eb03SelectedValues.size()!=0)){
			eb03.setHippaCodeSelectedValues(eb03SelectedValues);
			eb03Associations = new ArrayList<EB03Association>(eb03AssociationMap.values());
			Collections.sort(eb03Associations);
			eb03.setEb03Association(eb03Associations);
			mappingActual.setEb03(eb03);
			}
			if(!hsd02SelectedValues.isEmpty() || hsd02SelectedValues.size()!=0 ){
				hsd02.setHippaCodeSelectedValues(hsd02SelectedValues);
				mappingActual.setHsd02(hsd02);
			}
			if(!accumSelectedValues.isEmpty() || accumSelectedValues.size()!=0){
			
			accum.setHippaCodeSelectedValues(accumSelectedValues);
			mappingActual.setAccum(accum);
			}
			if(!umRuleSelectedValues.isEmpty()){
				utilizationMgmntRule.setHippaCodeSelectedValues(umRuleSelectedValues);
				mappingActual.setUtilizationMgmntRule(utilizationMgmntRule);
			}
			
			return mappingActual;
	}

	private class RetrieveVariableQuery extends MappingSqlQuery {

		public RetrieveVariableQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Variable variable = new Variable();
			variable.setVariableId(rs.getString("Variable"));
			variable.setDescription(rs.getString("Variable_Desc"));
			variable.setMajorHeadings(rs.getString("Major_Heading"));
			variable.setMinorHeadings(rs.getString("Minor_Heading"));
			variable.setVariableFormat(rs.getString("Variable_format"));
			variable.setPva(rs.getString("PVA"));
			variable.setVariableSystem(rs.getString("System"));
			
			return variable;
		}
	}

	public List retrieveVariableForEdit(Mapping mapping) {

		//String variableSystem = mapping.getVariable().getVariableSystem();
		String variableSystem = "LG";
		StringBuffer query = new StringBuffer();
		if (variableSystem.equals("LG")) {
			query = query
			.append("select distinct  a.contract_var as \"Variable\", e.cont_var_tx as \"Variable_Desc\", b.major_text as \"Major_Heading\", "
					 +"c.minor_text as \"Minor_Heading\", "
					 +"e.contract_var_format as \"Variable_format\", e.prov_arng as \"PVA\", 'LG' as \"System\" "
					 +"from lg_contract_detail a, lg_major_heading b, lg_minor_heading c, contvar e "
					 +"where a.benefit_structure = b.benefit_structure "
					 +"and a.benefit_structure = c.benefit_structure "
					 +"and a.major_heading = b.major_heading "
					 +"and a.major_heading = c.major_heading "
					 +"and a.minor_heading = c.minor_heading "
					 +"and a.contract_var = '"+ mapping.getVariable().getVariableId()+ "' "
					 +"and a.contract_var = e.contract_var");
			
			log.debug("Query :" + query);
		}
		if (variableSystem.equals("ISG")) {
			query = query
			.append("select distinct c.cpfxp_contvar as \"Variable\", g.cpfxp_text as \"Variable_Desc\", " 
					+"f.CPFXP_PC_TEXT as \"Major_Heading\", "
					+"e.cpfxp_pc_text as \"Minor_Heading\", g.CPFXP_FORMAT as \"Variable_format\", g.CPFXP_PROV_ARNG as \"PVA\", 'ISG' as \"System\" "
					+"from isg_cpfxp_contxref c, isg_cpfxp_cntathdg d, isg_cpfxp_minhdg e, isg_CPFXP_MAJHDG f, " 
					+"isg_CPFXP_CONTVAR g "
					+"where c.cpfxp_contattr = d.cpfxp_contattr "
					+"and d.cpfxp_pc_heading = e.cpfxp_minhdg "
					+"and e.cpfxp_pc_majhdg = f.CPFXP_MAJHDG "
					+"and c.cpfxp_contvar = '"+ mapping.getVariable().getVariableId()+ "' "
					+"and g.CPFXP_CONTVAR = c.cpfxp_contvar");			
		}
		RetrieveVariableQuery retrieveVariableQuery = new RetrieveVariableQuery(
				dataSource, query.toString());
		List variableList = retrieveVariableQuery.execute();
		 if(null != variableList && variableList.size() > 0){
			 log.debug("*****************variableList size : "+variableList.size());
		    return variableList;
		}
		return null;
	}

	private class UpdateMappingTempMstrQuery extends SqlUpdate {
		// Changed as part of SSCR19537 -- Start
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG SET SEN_BNFT_IND = ?, VAR_MAPG_STTS_CD= ?, "
			+ "LST_CHG_TMS= sysdate, LST_CHG_USR= ?, MAPPNG_CMP_IND=?, PROC_EXCLD_IND=?, INDVDL_EB03_ASSN_IND = ? WHERE CNTRCT_VAR_ID = ?";


		public UpdateMappingTempMstrQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);

			declareParameter(new SqlParameter("SEN_BNFT_IND", Types.CHAR));			
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("MAPPNG_CMP_IND", Types.CHAR));
			declareParameter(new SqlParameter("PROC_EXCLD_IND", Types.CHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));
			compile();
		}

		public void update(Mapping mapping) {
			String mappingCompleteInd = mapping.isFinalized()?"Y":"N";
			String procExcldInd = mapping.getProcedureExcludedInd();
            if (procExcldInd == null || procExcldInd.trim().length() == 0) {
                    procExcldInd = "N";
            }

			Object[] objs = new Object[] { 
					mapping.getSensitiveBenefitIndicator(),				
					mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mappingCompleteInd,
					procExcldInd,
					mapping.getIndvdlEb03AssnIndicator(),
					mapping.getVariable().getVariableId() };
			super.update(objs);
		}
		// Changed as part of SSCR19537 -- End
	}

	private class UpdateMappingTempChildQuery extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE TEMP_BX_CNTRCT_VAR_MAPG_VAL SET DATA_ELEMENT_VAL = ?, SEQ_NUM = ? WHERE TEMP_VAR_MAPG_VAL_SYS_ID =?";

		public UpdateMappingTempChildQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("DATA_ELEMENT_VAL", Types.VARCHAR));
			declareParameter(new SqlParameter("SEQ_NUM", Types.INTEGER));	
			declareParameter(new SqlParameter("TEMP_VAR_MAPG_VAL_SYS_ID", Types.BIGINT));
			compile();
		}

		public void updateTempChild(HippaCodeValue hippaCodeValue) {

			Object[] objs = new Object[] { hippaCodeValue.getValue(),hippaCodeValue.getSeq_num(),
					hippaCodeValue.getHippaCodeValueSysId() };
			super.update(objs);
		}
	}

	private class DeleteTempChildQuery extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM TEMP_BX_CNTRCT_VAR_MAPG_VAL WHERE TEMP_VAR_MAPG_VAL_SYS_ID =?";

		public DeleteTempChildQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("TEMP_VAR_MAPG_VAL_SYS_ID",
					Types.BIGINT));
			compile();
		}

		public void deleteTempChild(HippaCodeValue hippaCodeValue) {

			Object[] objs = new Object[] {hippaCodeValue.getHippaCodeValueSysId() };
			super.update(objs);
		}
	}

	private class UpdateMappingMstrChildQuery extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG_VAL SET DATA_ELEMENT_VAL = ?, SEQ_NUM = ?  WHERE VAR_MAPG_VAL_SYS_ID =?";

		public UpdateMappingMstrChildQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("DATA_ELEMENT_VAL", Types.VARCHAR));
			declareParameter(new SqlParameter("SEQ_NUM", Types.INTEGER));
			declareParameter(new SqlParameter("VAR_MAPG_VAL_SYS_ID",
					Types.BIGINT));
			compile();
		}

		public void updateMstrChild(HippaCodeValue hippaCodeValue) {

			Object[] objs = new Object[] { hippaCodeValue.getValue(),hippaCodeValue.getSeq_num(),
					hippaCodeValue.getHippaCodeValueSysId() };
			super.update(objs);
		}
	}

	private class DeleteMstrChildQuery extends SqlUpdate {
		private static final String SQL_QUERY = "DELETE FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_VAL_SYS_ID =?";

		public DeleteMstrChildQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_VAL_SYS_ID",
					Types.BIGINT));
			compile();
		}

		public void deleteMstrChild(HippaCodeValue hippaCodeValue) {

			Object[] objs = new Object[] {hippaCodeValue.getHippaCodeValueSysId() };
			super.update(objs);
		}
	}
	protected class PersistCreateExtndMsgQuery extends SqlUpdate {
		protected PersistCreateExtndMsgQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(Mapping mapping, String hippaSegmentName,	ExtendedMessageMapping extndMapping) {
			Object[] objs = new Object[] { 
					mstrTbleSysID,
					extndMapping.getSeq_num(), hippaSegmentName,
					extndMapping.getExtndMsg1(), extndMapping.getExtndMsg2(), extndMapping.getExtndMsg3(),
					extndMapping.getChangeInd(),mapping.getUser().getCreatedUserName(),
					extndMapping.getValue(), extndMapping.getNetworkInd(), extndMsgTbleSysID 
					
			};
			super.update(objs);
		}
	}
	protected class PersistExtndMsgQuery extends SqlUpdate {
		protected PersistExtndMsgQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(Mapping mapping, String hippaSegmentName,	ExtendedMessageMapping extndMapping) {
			Object[] objs = new Object[] { 
					mapping.getVariableSystemId(),
					extndMapping.getSeq_num(), hippaSegmentName,
					extndMapping.getExtndMsg1(), extndMapping.getExtndMsg2(), extndMapping.getExtndMsg3(),
					extndMapping.getChangeInd(), mapping.getUser().getCreatedUserName(),
					extndMapping.getValue(), extndMapping.getNetworkInd(), extndMsgTbleSysID 
					
			};
			super.update(objs);
		}
	}
	
	private class UpdateExtndMsgQuery extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_EXTND_MSG SET EB0_ASSN = ?, SEQ_NUM = ?, EXTND_MSG_TXT1 = ?, EXTND_MSG_TXT2 = ?, EXTND_MSG_TXT3 = ?, CHG_IND = ?, EB12_IND = ?  WHERE VAR_EXTND_MSG_VAL_SYS_ID =?";

		public UpdateExtndMsgQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("EB0_ASSN", Types.VARCHAR));
			declareParameter(new SqlParameter("SEQ_NUM", Types.INTEGER));
			declareParameter(new SqlParameter("EXTND_MSG_TXT1", Types.VARCHAR));
			declareParameter(new SqlParameter("EXTND_MSG_TXT2", Types.VARCHAR));
			declareParameter(new SqlParameter("EXTND_MSG_TXT3", Types.VARCHAR));
			declareParameter(new SqlParameter("CHG_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("EB12_IND", Types.CHAR));
			declareParameter(new SqlParameter("VAR_EXTND_MSG_VAL_SYS_ID", Types.BIGINT));
			compile();
		}

		public void updateExtndMsgTable(ExtendedMessageMapping extndMapping) {

			Object[] objs = new Object[] { 
				extndMapping.getValue(),
				extndMapping.getSeq_num(),
				extndMapping.getExtndMsg1(),
				extndMapping.getExtndMsg2(),
				extndMapping.getExtndMsg3(),
				extndMapping.getChangeInd(),
				extndMapping.getNetworkInd(),
				extndMapping.getExtendedMsgValueSysId()
			};
			super.update(objs);
		}
	}
	
	private class UpdateExtndMsgsOfEb03Query extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_EXTND_MSG SET EXTND_MSG_TXT1 = ?, EXTND_MSG_TXT2 = ?, EXTND_MSG_TXT3 = ?, EB12_IND = ?, CHG_IND = ?  WHERE VAR_EXTND_MSG_VAL_SYS_ID =?";

		public UpdateExtndMsgsOfEb03Query(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("EXTND_MSG_TXT1", Types.VARCHAR));
			declareParameter(new SqlParameter("EXTND_MSG_TXT2", Types.VARCHAR));
			declareParameter(new SqlParameter("EXTND_MSG_TXT3", Types.VARCHAR));
			declareParameter(new SqlParameter("EB12_IND", Types.CHAR));
			declareParameter(new SqlParameter("CHG_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("VAR_EXTND_MSG_VAL_SYS_ID", Types.BIGINT));
			compile();
		}

		public void updateExtndMsgsOfEb03(ExtendedMessageMapping extndMapping) {

			Object[] objs = new Object[] { 
				extndMapping.getExtndMsg1(),
				extndMapping.getExtndMsg2(),
				extndMapping.getExtndMsg3(),
				extndMapping.getNetworkInd(),
				extndMapping.getChangeInd(),
				extndMapping.getExtendedMsgValueSysId()
			};
			super.update(objs);
		}
	}
	
	private class UpdateExtndMsgIndQuery extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_EXTND_MSG SET CHG_IND = 'D' WHERE VAR_EXTND_MSG_VAL_SYS_ID = ?";

		public UpdateExtndMsgIndQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_EXTND_MSG_VAL_SYS_ID", Types.BIGINT));
			compile();
		}

		public void updateExtndMsgTableWithChangeInd(ExtendedMessageMapping extndMapping) {

			Object[] objs = new Object[] { 
				extndMapping.getExtendedMsgValueSysId()
			};
			super.update(objs);
		}
	}

	private class UpdateEB03ExtMsgInd extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_EXTND_MSG SET CHG_IND = 'U' WHERE VAR_MAPG_SYS_ID = ? AND DATA_ELEMENT_ID = 'EB03' AND CHG_IND = 'A'";

		public UpdateEB03ExtMsgInd(DataSource dataSource) {
			super(dataSource, SQL_QUERY);
			declareParameter(new SqlParameter("VAR_MAPG_SYS_ID", Types.BIGINT));
			compile();
		}

		public void updateEb03ExtMsgIndWithU(Mapping mapping) {

			Object[] objs = new Object[] { 
				mapping.getVariableSystemId()
			};
			super.update(objs);
		}
	}
	/**
	 * MTM CODE Changes
	 * @author u24053
	 *
	 */
	private class UpdateMappingMstrQuery extends SqlUpdate {
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET SEN_BNFT_IND = ?, VAR_MAPG_STTS_CD= ?, "
			+ "LST_CHG_TMS= sysdate, LST_CHG_USR= ?, IN_TEMP_TAB='N', MAPPNG_CMP_IND=?, PROC_EXCLD_IND=?, INDVDL_EB03_ASSN_IND =? "
			+ "WHERE CNTRCT_VAR_ID = ?";

		public UpdateMappingMstrQuery(DataSource dataSource) {
			super(dataSource, SQL_QUERY);

			declareParameter(new SqlParameter("SEN_BNFT_IND", Types.CHAR));
			declareParameter(new SqlParameter("VAR_MAPG_STTS_CD", Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR", Types.VARCHAR));
			declareParameter(new SqlParameter("MAPPNG_CMP_IND", Types.CHAR));
			declareParameter(new SqlParameter("PROC_EXCLD_IND", Types.CHAR));
			declareParameter(new SqlParameter("INDVDL_EB03_ASSN_IND", Types.VARCHAR));
			declareParameter(new SqlParameter("CNTRCT_VAR_ID", Types.VARCHAR));
			compile();
		}

		public void update(Mapping mapping) {
			/**
			 * mtm code change
			 */
			String mappingCompleteInd = mapping.isFinalized()?"Y":"N";
			String procExcldInd = mapping.getProcedureExcludedInd();
            if (procExcldInd == null || procExcldInd.trim().length() == 0) {
                    procExcldInd = "N";
            }
			Object[] objs = new Object[] { 
					mapping.getSensitiveBenefitIndicator(),
					mapping.getVariableMappingStatus(),
					mapping.getUser().getLastUpdatedUserName(),
					mappingCompleteInd,
					procExcldInd,
					mapping.getIndvdlEb03AssnIndicator(),
					mapping.getVariable().getVariableId() };
			super.update(objs);
			/**
			 * end
			 */
		}
	}
	/**
	 * MTM CODE Changes for Datafeed.
	 * The Audit lock also will be updated in the Master table
	 *
	 */
	private class UpdateMappingMstrForMerge extends SqlUpdate {
		private SimpleJdbcTemplate simpleJdbcTemplate;
		
		private static final String SQL_QUERY = "UPDATE BX_CNTRCT_VAR_MAPG SET SEN_BNFT_IND = ?, "
			+ "VAR_MAPG_STTS_CD= ?, LST_CHG_TMS= sysdate, LST_CHG_USR= ?, IN_TEMP_TAB='N', MAPPNG_CMP_IND=?, AUDIT_LOCK=?, PROC_EXCLD_IND=?, INDVDL_EB03_ASSN_IND=? WHERE CNTRCT_VAR_ID = ?";

		public void setDataSource(DataSource dataSource) {
	        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    }
		
		int count = 0;
		
		protected void batchUpdate(int batchSize, List<Mapping> mappingList) {
			
			List<Object[]> batch = new ArrayList<Object[]>();
	        for (Mapping mapping : mappingList) {
	        	String auditLock = mapping.getAuditLock();
				if (auditLock == null || auditLock.trim().length() == 0) {
					auditLock = "N";
				}
				String procExcldInd = mapping.getProcedureExcludedInd();
	            if (procExcldInd == null || procExcldInd.trim().length() == 0) {
	                    procExcldInd = "N";
	            }
	            String mappingCompleteInd = mapping.isFinalized()?"Y":"N";
	        	Object[] values = new Object[] {
	        			mapping.getSensitiveBenefitIndicator(),
						mapping.getVariableMappingStatus(),
						mapping.getUser().getLastUpdatedUserName(),
						mappingCompleteInd,
						auditLock,
						procExcldInd,
						mapping.getIndvdlEb03AssnIndicator(),
						mapping.getVariable().getVariableId() };
	            batch.add(values);
	            if(++count % batchSize == 0) {
	            	int[] deleteCounts = simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	            	batch = new ArrayList<Object[]>();
	            }
	        }
	        simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
		}
		
	}


	private class RetrieveHippaSegmentFromTempChild extends MappingSqlQuery {

		public RetrieveHippaSegmentFromTempChild(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			MappingRetrieveResult result = new MappingRetrieveResult();
			result.setVariableId(rs.getString(1));
			result.setDescription(rs.getString(2));
			result.setMessage(rs.getString(3));
			result.setMsg_type_required(rs.getString(4));
			result.setSensitiveBenefitIndicator(rs.getString(5));
			result.setVariableMappingStatus(rs.getString(6));
			result.setSeq_num(Long.valueOf(rs.getLong(7)));
			result.setHippaSegment(rs.getString(8));
			result.setHippaValues(rs.getString(9));
			result.setHippaValuesDesc(rs.getString(10));
			result.setVariableMappingSysId(Long.valueOf(rs.getLong(11)));
			return result;
		}
	}

	public boolean updateMapping(Mapping mapping, Mapping retrieveMapping) {

		UpdateMappingMstrQuery updateMappingMstrQuery = new UpdateMappingMstrQuery(
				getDataSource());
		updateMappingMstrQuery.update(mapping);
		
		if (mapping.getEb01() != null && (mapping.getEb01().getName().equals("EB01"))) {
			updateInMstrChildTable(mapping.getEb01().getHippaCodeSelectedValues(),
					mapping.getEb01().getName(), mapping);
			updateInExtndMsgTable(mapping.getEb01().getExtendedMsgsForSelectedValues(), mapping.getEb01().getName(), mapping);
		}
		if (mapping.getEb02() != null && (mapping.getEb02().getName().equals("EB02"))) {
			updateInMstrChildTable(mapping.getEb02().getHippaCodeSelectedValues(),
				mapping.getEb02().getName(), mapping);
		}
	
		/** SSCR 19537 April04 Changes - Insert EB03 and associated values in the BX_CNTRCT_VAR_MAPG_VAL*/
		String tableName = DomainConstants.MAIN_MAPPING_VAL;
		String createdUser = null;
		if (null != mapping.getUser()) {
			createdUser = mapping.getUser().getCreatedUserName();
		}
		if (null != mapping.getEb03()) {
			updateEB03Associations(mapping.getEb03().getEb03Association(), mapping.getVariableSystemId(), createdUser, tableName);
			updateInExtndMsgTableForEb03(mapping.getEb03().getExtendedMsgsForSelectedValues(), mapping.getEb03().getName(), mapping ,retrieveMapping);
		}

		if (mapping.getEb06() != null && mapping.getEb06().getName().equals("EB06")) {
			updateInMstrChildTable(mapping.getEb06().getHippaCodeSelectedValues(),
				mapping.getEb06().getName(), mapping);
		}
		if (mapping.getEb09() != null && (mapping.getEb09().getName().equals("EB09"))) {
			updateInMstrChildTable(mapping.getEb09().getHippaCodeSelectedValues(),
				mapping.getEb09().getName(), mapping);
		}
		if (mapping.getHsd01() != null && (mapping.getHsd01().getName().equals("HSD01"))) {
			updateInMstrChildTable(mapping.getHsd01().getHippaCodeSelectedValues(),
				mapping.getHsd01().getName(), mapping);
		}
		if (mapping.getHsd02() != null && (mapping.getHsd02().getName().equals("HSD02"))) {
			
			updateHSD02Values(mapping.getHsd02().getHippaCodeSelectedValues(),  mapping.getVariableSystemId(), createdUser, tableName);
			/*updateInMstrChildTable(mapping.getHsd02().getHippaCodeSelectedValues(),
				mapping.getHsd02().getName(), mapping);*/
		}
		if (mapping.getHsd03() != null && (mapping.getHsd03().getName().equals("HSD03"))) {
			updateInMstrChildTable(mapping.getHsd03().getHippaCodeSelectedValues(),
				mapping.getHsd03().getName(), mapping);
		}
		if (mapping.getHsd04() != null && (mapping.getHsd04().getName().equals("HSD04"))) {
			updateInMstrChildTable(mapping.getHsd04().getHippaCodeSelectedValues(),
				mapping.getHsd04().getName(), mapping);
		}
		if (mapping.getHsd05() != null && (mapping.getHsd05().getName().equals("HSD05"))) {
			updateInMstrChildTable(mapping.getHsd05().getHippaCodeSelectedValues(),
				mapping.getHsd05().getName(), mapping);
		}
		if (mapping.getHsd06() != null && (mapping.getHsd06().getName().equals("HSD06"))) {
			updateInMstrChildTable(mapping.getHsd06().getHippaCodeSelectedValues(),
				mapping.getHsd06().getName(), mapping);
		}
		if (mapping.getHsd07() != null && (mapping.getHsd07().getName().equals("HSD07"))) {
			updateInMstrChildTable(mapping.getHsd07().getHippaCodeSelectedValues(),
				mapping.getHsd07().getName(), mapping);
		}
		if (mapping.getHsd08() != null && (mapping.getHsd08().getName().equals("HSD08"))) {
			updateInMstrChildTable(mapping.getHsd08().getHippaCodeSelectedValues(),
				mapping.getHsd08().getName(), mapping);
		}
		if (mapping.getAccum() != null && (mapping.getAccum().getName().equals("ACCUM"))) {
			
				updateInMstrChildTable(mapping.getAccum().getHippaCodeSelectedValues(),
					mapping.getAccum().getName(), mapping);
			
		}
	
		if (mapping.getUtilizationMgmntRule() != null && (mapping.getUtilizationMgmntRule().getName().equals(DomainConstants.UMRULE_NAME))) {
			updateInMstrChildTable(mapping.getUtilizationMgmntRule()
				.getHippaCodeSelectedValues(), mapping.getUtilizationMgmntRule()
				.getName(), mapping);
		}
		
		// Start Age Mapping for a variable -- BXNI June 2012 Release.
		if (mapping.getStartAge() != null && (mapping.getStartAge().getName().equals(DomainConstants.START_AGE_NAME))) {
			updateInMstrChildTable(mapping.getStartAge().getHippaCodeSelectedValues(),
					mapping.getStartAge().getName(), mapping);
		}
		
		// End Age Mapping for a variable -- BXNI June 2012 Release.
		if (mapping.getEndAge() != null && (mapping.getEndAge().getName().equals(DomainConstants.END_AGE_NAME))) {
			updateInMstrChildTable(mapping.getEndAge().getHippaCodeSelectedValues(),
					mapping.getEndAge().getName(), mapping);
		}
		
		// Added NM1 Message Segment to mapping
		if (mapping.getNm1MessageSegment() != null && (mapping.getNm1MessageSegment().getName().equals(DomainConstants.NM1_MSG_SGMNT))) {
			updateInMstrChildTable(mapping.getNm1MessageSegment().getHippaCodeSelectedValues(),
					mapping.getNm1MessageSegment().getName(), mapping);
		
			}
		
		return true;
	}
	
	private List getSelectedvaluesList(List hippaCodeSelectedValues){
		
		List selectedValues = new ArrayList();
		int seq = 1;
		for(int j =0; j<hippaCodeSelectedValues.size();j++){
			
			hippaCodeValueObj = (HippaCodeValue) hippaCodeSelectedValues
			.get(j);
			
			if ((null != hippaCodeValueObj.getValue())
					&& (hippaCodeValueObj.getValue().trim().length() > 0)){
				
				hippaCodeValueObj.setSeq_num(Long.valueOf(seq));
				seq = seq + 1;
				
			}
			selectedValues.add(hippaCodeValueObj);
		}
		return selectedValues;
	}
	public void updateInMstrChildTable(List hippaCodeSelectedValues,
			String hippaSegmentName, Mapping mapping) {
		
		Long seqNum;
		
		HippaCodeValue hippaCodeValue = null;
		if (hippaCodeSelectedValues != null) {
			
			if(hippaSegmentName.equals("ACCUM") || (hippaSegmentName.equals("EB03")) || (hippaSegmentName.equals(DomainConstants.UMRULE_NAME))  ){
				
				hippaCodeSelectedValues = getSelectedvaluesList(hippaCodeSelectedValues);
			}
			
			for (int i = 0; i < hippaCodeSelectedValues.size(); i++) {

				hippaCodeValue = (HippaCodeValue) hippaCodeSelectedValues
						.get(i);			
				
				if ((null != hippaCodeValue.getHippaCodeValueSysId())
						&& (!hippaCodeValue.getHippaCodeValueSysId().equals(""))) {

					if ((null != hippaCodeValue.getValue())
							&& (!hippaCodeValue.getValue().equals(""))) {
						if(null  == hippaCodeValue.getSeq_num()){
							seqNum = Long.valueOf(1);
							hippaCodeValue.setSeq_num(seqNum);
						}
						UpdateMappingMstrChildQuery updateMappingMstrChildQuery = new UpdateMappingMstrChildQuery(
								getDataSource());
						updateMappingMstrChildQuery
								.updateMstrChild(hippaCodeValue);
					} else {
						DeleteMstrChildQuery deleteMstrChildQuery = new DeleteMstrChildQuery(
								getDataSource());
						deleteMstrChildQuery.deleteMstrChild(hippaCodeValue);
					}
				} else if (((null == hippaCodeValue.getHippaCodeValueSysId()) || (hippaCodeValue
						.getHippaCodeValueSysId().equals("")))
						&& ((null != hippaCodeValue.getValue() ) &&  (!hippaCodeValue
								.getValue().equals("")))) {
					
							StringBuffer childTbleInsertQuery = new StringBuffer();
						
							childTbleSysID = sequenceGeneratorRepository
							.generateSequence(childTbleSeqColName);
						
							if(!hippaSegmentName.equals("ACCUM")&&(!hippaSegmentName.equals("EB03")) &&(!hippaSegmentName.equals(DomainConstants.UMRULE_NAME))  ){
								
								hippaCodeValue.setSeq_num(Long.valueOf(1));
							}
						
							childTbleInsertQuery
									.append("INSERT INTO BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
											+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
											+ "VAR_MAPG_VAL_SYS_ID) VALUES (?,?,?,?,?,"
											+ "sysdate,?)");
							PersistMappingChildTableQuery persistMappingChildTableQuery = new PersistMappingChildTableQuery(
									getDataSource(), childTbleInsertQuery.toString());
							persistMappingChildTableQuery.insert(mapping, hippaSegmentName,
									hippaCodeValue);					
										
				}			
				
			}
		}
	}
public void persistInExtndMsgTable(Long sysID,List extendedMsgsForSelectedValues,	String hippaSegmentName, Mapping mapping) {
		
		Long seqNum;		
		ExtendedMessageMapping extMsgMapping = null;
		if (extendedMsgsForSelectedValues != null) {				
			for (int i = 0; i < extendedMsgsForSelectedValues.size(); i++) {				
				extMsgMapping = (ExtendedMessageMapping) extendedMsgsForSelectedValues.get(i);			
				 if ( null != extMsgMapping.getValue() && extMsgMapping.getValue().equals("D") && (extMsgMapping.getExtndMsg1() != null || extMsgMapping.getExtndMsg2() != null || extMsgMapping.getExtndMsg3() != null) &&
						 (extMsgMapping.getExtndMsg1() != "" || extMsgMapping.getExtndMsg2() != "" || extMsgMapping.getExtndMsg3() != "")){					
					StringBuffer extndMsgTbleInsertQuery = new StringBuffer();
					extndMsgTbleSysID = sequenceGeneratorRepository.generateSequence(extndMsgTbleSeqColName);
					extMsgMapping.setSeq_num(Long.valueOf(1));
					extMsgMapping.setChangeInd("A");
					extndMsgTbleInsertQuery
							.append("INSERT INTO BX_EXTND_MSG (VAR_MAPG_SYS_ID, SEQ_NUM,"
									+ "DATA_ELEMENT_ID, EXTND_MSG_TXT1, EXTND_MSG_TXT2, EXTND_MSG_TXT3, CHG_IND, CREATD_USER_ID, CREATD_TM_STMP, EB0_ASSN, EB12_IND, "
									+ "VAR_EXTND_MSG_VAL_SYS_ID) VALUES (?,?,?,?,?,?,?,?,"
									+ "sysdate,?,?,?)");
					/*PersistExtndMsgQuery persistExtndMsgQuery = new PersistExtndMsgQuery(getDataSource(), extndMsgTbleInsertQuery.toString());
					persistExtndMsgQuery.insert(mapping, hippaSegmentName, extMsgMapping);*/	
					PersistCreateExtndMsgQuery persistCreateExtndMsgQuery = new PersistCreateExtndMsgQuery(getDataSource(), extndMsgTbleInsertQuery.toString());
					persistCreateExtndMsgQuery.insert(mapping, hippaSegmentName, extMsgMapping);
				}			
			}
		}
	}
	
	public void updateInExtndMsgTable(List extendedMsgsForSelectedValues,	String hippaSegmentName, Mapping mapping) {
		
		Long seqNum;		
		ExtendedMessageMapping extMsgMapping = null;
		if (extendedMsgsForSelectedValues != null) {				
			for (int i = 0; i < extendedMsgsForSelectedValues.size(); i++) {				
				extMsgMapping = (ExtendedMessageMapping) extendedMsgsForSelectedValues.get(i);			
				if ((null != extMsgMapping.getExtendedMsgValueSysId()) && (!extMsgMapping.getExtendedMsgValueSysId().equals(""))) {					
					if (extMsgMapping.getValue().equals("D")) {
						if(extMsgMapping.getExtndMsg1() !=null && extMsgMapping.getExtndMsg1().equals("") && extMsgMapping.getExtndMsg2()!= null && extMsgMapping.getExtndMsg2().equals("") && 
								extMsgMapping.getExtndMsg3() != null && extMsgMapping.getExtndMsg3().equals("")){
							UpdateExtndMsgIndQuery updateExtndMsgIndQuery = new UpdateExtndMsgIndQuery(getDataSource());
							updateExtndMsgIndQuery.updateExtndMsgTableWithChangeInd(extMsgMapping);
						} else if(extMsgMapping.getChangeInd().equals("U") && (extMsgMapping.getExtndMsg1()!=null || extMsgMapping.getExtndMsg2()!=null || extMsgMapping.getExtndMsg3()!=null)){
							if(null  == extMsgMapping.getSeq_num()){
								seqNum = Long.valueOf(1);
								extMsgMapping.setSeq_num(seqNum);
								extMsgMapping.setChangeInd("U");
							}
							UpdateExtndMsgQuery updateExtndMsgQuery = new UpdateExtndMsgQuery(getDataSource());
							updateExtndMsgQuery.updateExtndMsgTable(extMsgMapping);
						} else if(extMsgMapping.getExtndMsg1()==null && extMsgMapping.getExtndMsg2()==null && extMsgMapping.getExtndMsg3()==null){
							UpdateExtndMsgIndQuery updateExtndMsgIndQuery = new UpdateExtndMsgIndQuery(getDataSource());
							updateExtndMsgIndQuery.updateExtndMsgTableWithChangeInd(extMsgMapping);
						}						
					} else {						
						UpdateExtndMsgIndQuery updateExtndMsgIndQuery = new UpdateExtndMsgIndQuery(getDataSource());
						updateExtndMsgIndQuery.updateExtndMsgTableWithChangeInd(extMsgMapping);
					}
				} else if (((null == extMsgMapping.getExtendedMsgValueSysId()) || (extMsgMapping.getExtendedMsgValueSysId().equals("")))
						&& ((null != extMsgMapping.getValue()) &&  (!extMsgMapping.getValue().equals("")) && (extMsgMapping.getValue().equals("D")))) {
					
							if(!(extMsgMapping.getExtndMsg1()==null) || !(extMsgMapping.getExtndMsg2()==null) || !(extMsgMapping.getExtndMsg3()==null)){
								StringBuffer extndMsgTbleInsertQuery = new StringBuffer();
								extndMsgTbleSysID = sequenceGeneratorRepository.generateSequence(extndMsgTbleSeqColName);
								extMsgMapping.setSeq_num(Long.valueOf(1));
								extMsgMapping.setChangeInd("A");
								extndMsgTbleInsertQuery
										.append("INSERT INTO BX_EXTND_MSG (VAR_MAPG_SYS_ID, SEQ_NUM,"
												+ "DATA_ELEMENT_ID, EXTND_MSG_TXT1, EXTND_MSG_TXT2, EXTND_MSG_TXT3, CHG_IND, CREATD_USER_ID, CREATD_TM_STMP, EB0_ASSN, EB12_IND, "
												+ "VAR_EXTND_MSG_VAL_SYS_ID) VALUES (?,?,?,?,?,?,?,?,"
												+ "sysdate,?,?,?)");
								PersistExtndMsgQuery persistExtndMsgQuery = new PersistExtndMsgQuery(getDataSource(), extndMsgTbleInsertQuery.toString());
								persistExtndMsgQuery.insert(mapping, hippaSegmentName, extMsgMapping);
							}
				}			
			}
		}
	}
public void createInExtndMsgTableForEb03(Long sysID, List extendedMsgsForSelectedValues,	String hippaSegmentName, Mapping mapping) {
		
		Long seqNum = 0L;		
		ExtendedMessageMapping extMsgMapping = null;
		if (extendedMsgsForSelectedValues != null) {				
			for (int i = 0; i < extendedMsgsForSelectedValues.size(); i++) {				
				extMsgMapping = (ExtendedMessageMapping) extendedMsgsForSelectedValues.get(i);			
				if ( null != extMsgMapping.getValue() && (extMsgMapping.getExtndMsg1() != null || extMsgMapping.getExtndMsg2() != null || extMsgMapping.getExtndMsg3() != null) &&
						(extMsgMapping.getExtndMsg1() != "" || extMsgMapping.getExtndMsg2() != "" || extMsgMapping.getExtndMsg3() != "")){
					
					StringBuffer extndMsgTbleInsertQuery = new StringBuffer();
					extndMsgTbleSysID = sequenceGeneratorRepository.generateSequence(extndMsgTbleSeqColName);					
					
					seqNum = seqNum+1L;
					extMsgMapping.setSeq_num(seqNum);
					extMsgMapping.setChangeInd("A");
					extndMsgTbleInsertQuery
							.append("INSERT INTO BX_EXTND_MSG (VAR_MAPG_SYS_ID, SEQ_NUM,"
									+ "DATA_ELEMENT_ID, EXTND_MSG_TXT1, EXTND_MSG_TXT2, EXTND_MSG_TXT3, CHG_IND, CREATD_USER_ID, CREATD_TM_STMP, EB0_ASSN, EB12_IND, "
									+ "VAR_EXTND_MSG_VAL_SYS_ID) VALUES (?,?,?,?,?,?,?,?,"
									+ "sysdate,?,?,?)");
					PersistCreateExtndMsgQuery persistCreateExtndMsgQuery = new PersistCreateExtndMsgQuery(getDataSource(), extndMsgTbleInsertQuery.toString());
					persistCreateExtndMsgQuery.insert(mapping, hippaSegmentName, extMsgMapping);					
				}			
			}
		}
	}

public void updateInExtndMsgTableForEb03(List extendedMsgsForSelectedValues, String hippaSegmentName, Mapping mapping, Mapping retrieveMapping) {
	
	Long seqNum = 0L;
	boolean dIndObjFound = false;
	ExtendedMessageMapping extMsgMapping = null;
	if (extendedMsgsForSelectedValues != null) {				
		for (int i = 0; i < extendedMsgsForSelectedValues.size(); i++) {				
			extMsgMapping = (ExtendedMessageMapping) extendedMsgsForSelectedValues.get(i);			
			if ((null != extMsgMapping.getExtendedMsgValueSysId()) && (!extMsgMapping.getExtendedMsgValueSysId().equals(""))) {
				if((extMsgMapping.getChangeInd() != null && extMsgMapping.getChangeInd().equals("D")) || (extMsgMapping.getExtndMsg1().equals("") && extMsgMapping.getExtndMsg2().equals("") && extMsgMapping.getExtndMsg3().equals(""))){
					if(!dIndObjFound){
						UpdateEB03ExtMsgInd updateEB03ExtMsgInd = new UpdateEB03ExtMsgInd(getDataSource());
						updateEB03ExtMsgInd.updateEb03ExtMsgIndWithU(mapping);
						dIndObjFound = true;
					}
					UpdateExtndMsgIndQuery updateExtndMsgIndQuery = new UpdateExtndMsgIndQuery(getDataSource());
					updateExtndMsgIndQuery.updateExtndMsgTableWithChangeInd(extMsgMapping);
				}else if(extMsgMapping.getChangeInd().equals("U")){
						UpdateExtndMsgsOfEb03Query updateExtndMsgsOfEb03Query = new UpdateExtndMsgsOfEb03Query(getDataSource());
						updateExtndMsgsOfEb03Query.updateExtndMsgsOfEb03(extMsgMapping);
					}
					
			} else if (((null == extMsgMapping.getExtendedMsgValueSysId()) || (extMsgMapping.getExtendedMsgValueSysId().equals("")))
					&& ((null != extMsgMapping.getValue()) && (!extMsgMapping.getValue().equals("")))) {
				
					StringBuffer extndMsgTbleInsertQuery = new StringBuffer();
					extndMsgTbleSysID = sequenceGeneratorRepository.generateSequence(extndMsgTbleSeqColName);
					
					if(seqNum ==0L){
						String countOfEb03 = "SELECT count(*) FROM  BX_EXTND_MSG WHERE VAR_MAPG_SYS_ID = '"+mapping.getVariableSystemId()+"' AND DATA_ELEMENT_ID = 'EB03'";							
						log.debug("query for EB03 records :" + countOfEb03);
						CountEB03Query countEB03Query = new CountEB03Query(dataSource, countOfEb03);						
						List list = countEB03Query.execute();
						seqNum = (Long) list.get(0)+1L;
					}else{
						seqNum = seqNum+1L;
					}
					extMsgMapping.setSeq_num(seqNum);
					if(dIndObjFound){
						extMsgMapping.setChangeInd("U");
					} else {
						extMsgMapping.setChangeInd("A");
					}
					extndMsgTbleInsertQuery
							.append("INSERT INTO BX_EXTND_MSG (VAR_MAPG_SYS_ID, SEQ_NUM,"
									+ "DATA_ELEMENT_ID, EXTND_MSG_TXT1, EXTND_MSG_TXT2, EXTND_MSG_TXT3, CHG_IND, CREATD_USER_ID, CREATD_TM_STMP, EB0_ASSN, EB12_IND, "
									+ "VAR_EXTND_MSG_VAL_SYS_ID) VALUES (?,?,?,?,?,?,?,?,"
									+ "sysdate,?,?,?)");
					PersistExtndMsgQuery persistExtndMsgQuery = new PersistExtndMsgQuery(getDataSource(), extndMsgTbleInsertQuery.toString());
					persistExtndMsgQuery.insert(mapping, hippaSegmentName, extMsgMapping);					
			}			
		}
	}
	//Updating with D ind for the deleted EB03
	changeIndForEb03(mapping , retrieveMapping);
	
}
	//Updating with D ind for the deleted EB03
	private void changeIndForEb03(Mapping mapping ,Mapping retrieveMapping) {
	// TODO Auto-generated method stub
		List newValList = new ArrayList();// UI Records
		List oldValList = new ArrayList();// DB Records
		
		List<EB03Association> newEb03List = mapping.getEb03().getEb03Association();
		for (int j = 0; j < newEb03List.size(); j++) {
			String newVal = newEb03List.get(j).getEb03().getValue();
			newValList.add(newVal);
		}
		//Looping DB records
		if(null != tempEb03Values){
			for (int i = 0; i < tempEb03Values.size(); i++) {
				String oldVal = tempEb03Values.get(i).getEb03().getValue();
				oldValList.add(oldVal);
			}
			
			for (int i = 0; i < oldValList.size(); i++) {
				if(!newValList.contains(oldValList.get(i))){
					List extendedMsgsValues = retrieveMapping.getEb03().getExtendedMsgsForSelectedValues();
					Iterator iterator = extendedMsgsValues.iterator();
					while(iterator.hasNext()){
						ExtendedMessageMapping resultObj = (ExtendedMessageMapping) iterator.next();
						if(resultObj.getValue().equals(oldValList.get(i))){
							resultObj.setChangeInd("D");
							UpdateExtndMsgIndQuery updateExtndMsgIndQuery = new UpdateExtndMsgIndQuery(getDataSource());
							updateExtndMsgIndQuery.updateExtndMsgTableWithChangeInd(resultObj);
						}
					}
				}
			}
		}
		
}

	public boolean updateBeingModified(Mapping mapping, Mapping retrieveMapping ) {
		
		UpdateMappingTempMstrQuery updateMappingTempMstrQuery = new UpdateMappingTempMstrQuery(
				getDataSource());
		updateMappingTempMstrQuery.update(mapping);

		if (mapping.getEb01() != null && mapping.getEb01().getName().equals("EB01")){			
			updateInTempChildTable(mapping.getEb01().getHippaCodeSelectedValues(),
					mapping.getEb01().getName(), mapping);
			//EXTENDED MSG
			updateInExtndMsgTable(mapping.getEb01().getExtendedMsgsForSelectedValues(), mapping.getEb01().getName(), mapping);
		
		}
		if (mapping.getEb02() != null && (mapping.getEb02().getName().equals("EB02"))) {
		updateInTempChildTable(mapping.getEb02().getHippaCodeSelectedValues(),
				mapping.getEb02().getName(), mapping);
	
		}
		
		/** SSCR 19537 April04 Changes - Insert EB03 and associated values in the TEMP_BX_CNTRCT_VAR_MAPG_VAL*/ 
		String tableName = DomainConstants.TEMP_MAPPING_VAL;
		String createdUser = null;
		if (null != mapping.getUser()) {
			createdUser = mapping.getUser().getCreatedUserName();
		}
		if (null != mapping.getEb03()) {
			updateEB03Associations(mapping.getEb03().getEb03Association(), mapping.getVariableSystemId(), createdUser, tableName);
			updateInExtndMsgTableForEb03(mapping.getEb03().getExtendedMsgsForSelectedValues(), mapping.getEb03().getName(), mapping,retrieveMapping);
		}
		
		if (mapping.getEb06() != null && mapping.getEb06().getName().equals("EB06")) {
		updateInTempChildTable(mapping.getEb06().getHippaCodeSelectedValues(),
				mapping.getEb06().getName(), mapping);
		
		}
		if (mapping.getEb09() != null && (mapping.getEb09().getName().equals("EB09"))) {
		updateInTempChildTable(mapping.getEb09().getHippaCodeSelectedValues(),
				mapping.getEb09().getName(), mapping);
		
		}
		
		if (mapping.getHsd01() != null && (mapping.getHsd01().getName().equals("HSD01"))) {
		updateInTempChildTable(mapping.getHsd01().getHippaCodeSelectedValues(),
				mapping.getHsd01().getName(), mapping);
		
		}
		if (mapping.getHsd02() != null && (mapping.getHsd02().getName().equals("HSD02"))) {
			updateTempHsd02Values(mapping.getHsd02().getHippaCodeSelectedValues(), mapping.getVariableSystemId(), createdUser, tableName,mapping);
		/*updateInTempChildTable(mapping.getHsd02().getHippaCodeSelectedValues(),
				mapping.getHsd02().getName(), mapping);*/
		}
		if (mapping.getHsd03() != null && (mapping.getHsd03().getName().equals("HSD03"))) {
		updateInTempChildTable(mapping.getHsd03().getHippaCodeSelectedValues(),
				mapping.getHsd03().getName(), mapping);
		
		}
		if (mapping.getHsd04() != null && (mapping.getHsd04().getName().equals("HSD04"))) {
		updateInTempChildTable(mapping.getHsd04().getHippaCodeSelectedValues(),
				mapping.getHsd04().getName(), mapping);
		
		}
		if (mapping.getHsd05() != null && (mapping.getHsd05().getName().equals("HSD05"))) {
		updateInTempChildTable(mapping.getHsd05().getHippaCodeSelectedValues(),
				mapping.getHsd05().getName(), mapping);
		
		}
		if (mapping.getHsd06() != null && (mapping.getHsd06().getName().equals("HSD06"))) {
		updateInTempChildTable(mapping.getHsd06().getHippaCodeSelectedValues(),
				mapping.getHsd06().getName(), mapping);
		
		}
		if (mapping.getHsd07() != null && (mapping.getHsd07().getName().equals("HSD07"))) {
		updateInTempChildTable(mapping.getHsd07().getHippaCodeSelectedValues(),
				mapping.getHsd07().getName(), mapping);
		
		}
		if (mapping.getHsd08() != null && (mapping.getHsd08().getName().equals("HSD08"))) {
		updateInTempChildTable(mapping.getHsd08().getHippaCodeSelectedValues(),
				mapping.getHsd08().getName(), mapping);
		
		}
		if (mapping.getAccum() != null && (mapping.getAccum().getName().equals("ACCUM"))) {
		updateInTempChildTable(mapping.getAccum().getHippaCodeSelectedValues(),
				mapping.getAccum().getName(), mapping);
		}
		if (mapping.getUtilizationMgmntRule() != null && (mapping.getUtilizationMgmntRule().getName().equals(DomainConstants.UMRULE_NAME))) {
			updateInTempChildTable(mapping.getUtilizationMgmntRule()
					.getHippaCodeSelectedValues(), mapping.getUtilizationMgmntRule()
					.getName(), mapping);
		}
		// Start Age Mapping for a variable -- BXNI June 2012 Release.
		if (mapping.getStartAge() != null && (mapping.getStartAge().getName().equals(DomainConstants.START_AGE_NAME))) {
			updateInTempChildTable(mapping.getStartAge().getHippaCodeSelectedValues(),
					mapping.getStartAge().getName(), mapping);
			
			}
		// End Age Mapping for a variable -- BXNI June 2012 Release.
		if (mapping.getEndAge() != null && (mapping.getEndAge().getName().equals(DomainConstants.END_AGE_NAME))) {
			updateInTempChildTable(mapping.getEndAge().getHippaCodeSelectedValues(),
					mapping.getEndAge().getName(), mapping);
			
			}
		
		// Added NM1 Message Segment to mapping
		if (mapping.getNm1MessageSegment() != null && (mapping.getNm1MessageSegment().getName().equals(DomainConstants.NM1_MSG_SGMNT))) {
			updateInTempChildTable(mapping.getNm1MessageSegment().getHippaCodeSelectedValues(),
					mapping.getNm1MessageSegment().getName(), mapping);
		
			}
	
		return true;
	}

	public void updateInTempChildTable(List hippaCodeSelectedValues,
			String hippaSegmentName, Mapping mapping) {
		
		Long seqNum ;
		
		HippaCodeValue hippaCodeValue = null;
		if (hippaCodeSelectedValues != null) {
			
			if(hippaSegmentName.equals("ACCUM") || (hippaSegmentName.equals("EB03") || (hippaSegmentName.equals(DomainConstants.UMRULE_NAME))  )){
				
				hippaCodeSelectedValues = getSelectedvaluesList(hippaCodeSelectedValues);
			}
			
			for (int i = 0; i < hippaCodeSelectedValues.size(); i++) {

				hippaCodeValue = (HippaCodeValue) hippaCodeSelectedValues
						.get(i);
				if ((null != hippaCodeValue.getHippaCodeValueSysId())
						&& (!hippaCodeValue.getHippaCodeValueSysId().equals(""))) {

					if (( null != hippaCodeValue.getValue())
							&& (!hippaCodeValue.getValue().equals(""))) {
						if(null  == hippaCodeValue.getSeq_num()){
							seqNum = Long.valueOf(1);
							hippaCodeValue.setSeq_num(seqNum);
						}
						UpdateMappingTempChildQuery updateMappingTempChildQuery = new UpdateMappingTempChildQuery(
								getDataSource());
						updateMappingTempChildQuery
								.updateTempChild(hippaCodeValue);
					} else {
						DeleteTempChildQuery deleteTempChildQuery = new DeleteTempChildQuery(
								getDataSource());
						deleteTempChildQuery.deleteTempChild(hippaCodeValue);
					}
				} else if (((null == hippaCodeValue.getHippaCodeValueSysId()) || (hippaCodeValue
						.getHippaCodeValueSysId().equals("")))
						&& ((null != hippaCodeValue.getValue() ) && (!hippaCodeValue
								.getValue().equals("")))) {
					if(!hippaSegmentName.equals("ACCUM")&&(!hippaSegmentName.equals("EB03") && (!hippaSegmentName.equals(DomainConstants.UMRULE_NAME)) )){
						
						hippaCodeValue.setSeq_num(Long.valueOf(1));
					}
					
					StringBuffer childTbleInsertQuery = new StringBuffer();
					
					tempChildTbleSysID = sequenceGeneratorRepository
					.generateSequence(childTbleSeqColName);
					
					childTbleInsertQuery
							.append("INSERT INTO TEMP_BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
									+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
									+ "TEMP_VAR_MAPG_VAL_SYS_ID) VALUES (?,?,?,?,?,"
									+ "sysdate,?)");
					PersistTempChildTableQuery persistTempChildTableQuery = new PersistTempChildTableQuery(
							getDataSource(), childTbleInsertQuery.toString());
					
					persistTempChildTableQuery.insert(mapping,
							hippaSegmentName, hippaCodeValue);
					}	
				}
			}
		}	

	protected class PersistTempTableQuery extends SqlUpdate {
		protected PersistTempTableQuery(DataSource ds) {
			super(
					ds,
					"Insert into TEMP_BX_CNTRCT_VAR_MAPG (VAR_MAPG_SYS_ID,CNTRCT_VAR_ID,CNTRCT_VAR_DESC,"
							+ "SEN_BNFT_IND,VAR_MAPG_STTS_CD,IS_MAPG_REQUIRED,CREATD_USER_ID,CREATD_TM_STMP,"
							+ "LST_CHG_TMS,LST_CHG_USR,MAPPNG_CMP_IND,AUDIT_LOCK,PROC_EXCLD_IND,INDVDL_EB03_ASSN_IND) values (?,?,?,"
							+ "?,?,'Y',?,sysdate,"
							+ "sysdate,?,?,?,?,?)");
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			declareParameter(new SqlParameter(Types.CHAR));
			compile();
		}

		protected void insert(Mapping mapping) {
			/**
			 * mtm change
			 */
			String mappingCompleteInd = mapping.isFinalized()?"Y":"N";
			String procExcldInd = mapping.getProcedureExcludedInd();
            if (procExcldInd == null || procExcldInd.trim().length() == 0) {
                    procExcldInd = "N";
            }
			Object[] objs = new Object[] { mapping.getVariableSystemId(),
					mapping.getVariable().getVariableId(),
					mapping.getVariable().getDescription(),
					mapping.getSensitiveBenefitIndicator(),
					mapping.getVariableMappingStatus(),
					mapping.getUser().getCreatedUserName(),
					mapping.getUser().getLastUpdatedUserName(),
					mappingCompleteInd,
					mapping.getAuditLock(),
					procExcldInd,mapping.getIndvdlEb03AssnIndicator()};
			super.update(objs);
			/**
			 * end
			 */
		}
	}
	
	protected class PersistTempChildTableQuery extends SqlUpdate {
		protected PersistTempChildTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(Mapping mapping, String hippaSegmentName,
				HippaCodeValue hippaCodeValueObj) {
			Object[] objs = new Object[] { mapping.getVariableSystemId(),
					hippaCodeValueObj.getSeq_num(), hippaSegmentName,
					hippaCodeValueObj.getValue(), mapping.getUser().getCreatedUserName(),
					tempChildTbleSysID };
			super.update(objs);
		}
	}
	
	protected class PersistTempChildTableQueryForBeingModified extends SqlUpdate {
		protected PersistTempChildTableQueryForBeingModified(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			
			compile();
		}

		protected void insert(Mapping mapping, String hippaSegmentName,
				HippaCodeValue hippaCodeValueObj) {
			Object[] objs = new Object[] { mapping.getVariableSystemId(),
					hippaCodeValueObj.getSeq_num(), hippaSegmentName,
					hippaCodeValueObj.getValue(),
					mapping.getUser().getCreatedUserName(),
					hippaCodeValueObj.getHippaCodeValueSysId(),
					};
			super.update(objs);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.repository.MappingRepository#persist(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean persistVariableMappingInTemp(Mapping mapping) {
		PersistTempTableQuery persistTempTableQuery = new PersistTempTableQuery(
				getDataSource());
		persistTempTableQuery.insert(mapping);
		if (mapping.getEb01() != null) {
			persistTempChildTable(mapping, mapping.getEb01().getName(), mapping
					.getEb01().getHippaCodeSelectedValues());
		}
		if (mapping.getEb02() != null) {
			persistTempChildTable(mapping, mapping.getEb02().getName(), mapping
					.getEb02().getHippaCodeSelectedValues());
		}
		// SSCR19537 change - commented because, it will get added as part of the eb03 association.
	/*	if (mapping.getEb03() != null) {
			persistTempChildTable(mapping, mapping.getEb03().getName(), mapping
					.getEb03().getHippaCodeSelectedValues());
		}*/
		
		/** SSCR19537 - persisting EB03 association object in the TEMP_BX_CNTRCT_VAR_MAPG_VAL table*/
		if (null != mapping.getVariableSystemId()) {
			mstrTbleSysID = mapping.getVariableSystemId();
		} else {
			mstrTbleSysID = sequenceGeneratorRepository.generateSequence(masterTbleSeqColName);
		}
		
		String tableName = DomainConstants.TEMP_MAPPING_VAL;
		String createdUser = null;
		if (null != mapping.getUser()) {
			createdUser = mapping.getUser().getCreatedUserName();
		}
		if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() && !mapping.getEb03().getEb03Association().isEmpty()) {
			persistEb03Associations(mapping.getEb03().getEb03Association(), createdUser, tableName);
		}
		
		if (mapping.getEb06() != null) {
			persistTempChildTable(mapping, mapping.getEb06().getName(), mapping
					.getEb06().getHippaCodeSelectedValues());
		}
		if (mapping.getEb09() != null) {
			persistTempChildTable(mapping, mapping.getEb09().getName(), mapping
					.getEb09().getHippaCodeSelectedValues());
		}
		if (mapping.getAccum() != null) {
			persistTempChildTable(mapping, mapping.getAccum().getName(),
					mapping.getAccum().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd01() != null) {
			persistTempChildTable(mapping, mapping.getHsd01().getName(),
					mapping.getHsd01().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd02() != null) {
			persistTempChildTable(mapping, mapping.getHsd02().getName(),
					mapping.getHsd02().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd03() != null) {
			persistTempChildTable(mapping, mapping.getHsd03().getName(),
					mapping.getHsd03().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd04() != null) {
			persistTempChildTable(mapping, mapping.getHsd04().getName(),
					mapping.getHsd04().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd05() != null) {
			persistTempChildTable(mapping, mapping.getHsd05().getName(),
					mapping.getHsd05().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd06() != null) {
			persistTempChildTable(mapping, mapping.getHsd06().getName(),
					mapping.getHsd06().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd07() != null) {
			persistTempChildTable(mapping, mapping.getHsd07().getName(),
					mapping.getHsd07().getHippaCodeSelectedValues());
		}
		if (mapping.getHsd08() != null) {
			persistTempChildTable(mapping, mapping.getHsd08().getName(),
					mapping.getHsd08().getHippaCodeSelectedValues());
		}
		// SSCR19537 change - commented because, it will get added as part of the eb03 association.
		/*if (mapping.getIi02() != null) {
			persistTempChildTable(mapping, mapping.getIi02().getName(), mapping
					.getIi02().getHippaCodeSelectedValues());
		}
		if (mapping.getNoteTypeCode() != null) {
			persistTempChildTable(mapping, mapping.getNoteTypeCode().getName(),
					mapping.getNoteTypeCode().getHippaCodeSelectedValues());
		}*/
		if (mapping.getUtilizationMgmntRule() != null) {
			persistTempChildTable(mapping, mapping.getUtilizationMgmntRule().getName(),
					mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues());
		}
		//updateInTempTableFlag(mapping);
		// LO change
		// Start Age Mapping for a variable -- LO RELEASE JAN2014 WLPRD00553642.
		if(mapping.getStartAge() != null){
			persistTempChildTable(mapping,mapping.getStartAge().getName(),mapping.getStartAge().getHippaCodeSelectedValues());
		}
		// End Age Mapping for a variable -- LO RELEASE JAN2014 WLPRD00553642.
		if(mapping.getEndAge() != null){
			persistTempChildTable(mapping,mapping.getEndAge().getName(),mapping.getEndAge().getHippaCodeSelectedValues());
		}
		
		// NM1 Message Segment
		if(mapping.getNm1MessageSegment() != null){
			persistTempChildTable(mapping,mapping.getNm1MessageSegment().getName(),mapping.getNm1MessageSegment().getHippaCodeSelectedValues());
		}
		return true;
	}

	public void persistTempChildTable(Mapping mapping, String hippaSegmentName,
			List hippaCodeSelectedValues) {

		if (hippaCodeSelectedValues != null) {
			for (Iterator itr = hippaCodeSelectedValues.iterator(); itr
					.hasNext();) {

				HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
				//tempChildTbleSysID = sequenceGeneratorRepository
				//.generateSequence(tempChildTbleSeqColName);

				StringBuffer childTbleInsertQuery = new StringBuffer();
				childTbleInsertQuery
						.append("INSERT INTO TEMP_BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
								+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
								+ "TEMP_VAR_MAPG_VAL_SYS_ID) VALUES (?,?,?,?,?,"
								+ "sysdate,?)");
				PersistTempChildTableQueryForBeingModified persistTempChildTableQueryForBeingModified = new PersistTempChildTableQueryForBeingModified(
						getDataSource(), childTbleInsertQuery.toString());
				persistTempChildTableQueryForBeingModified.insert(mapping, hippaSegmentName,
						hippaCodeValue);
			}
		}
	}

	private class RetrieveVariableForCreateQuery extends MappingSqlQuery {

		public RetrieveVariableForCreateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Variable variable = new Variable();
			variable.setVariableId(rs.getString("contVar"));
			if(rs.getString("Variable_Desc") != null){
			    variable.setDescription(rs.getString("Variable_Desc").toUpperCase());		
			}
			variable.setDataType(rs.getString("format"));
			variable.setVariableFormat(rs.getString("format"));
			if(rs.getString("arrangment") != null){
			    variable.setPva(rs.getString("arrangment").toUpperCase());
			}
			variable.setVariableSystem(rs.getString("Legacy_System"));
			if(rs.getString("major") != null){
			    variable.setMajorHeadings(rs.getString("major").toUpperCase());
			}
			if(rs.getString("minor") != null){
			    variable.setMinorHeadings(rs.getString("minor").toUpperCase());
			}
			// setting variable lg and isg catagory - section 3 
			variable.setIsgCatagory(rs.getString("isgcatagory"));
			variable.setLgCatagory(rs.getString("lgcatagory"));
			/* Modified existing code to consider both LG and ISG Mapped Accumulators
			 * in case of variables availabe in both LG and ISG- June Release*/
			variable.setLgAccumulator(rs.getString("LGACCUM"));
			variable.setIsgAccumulator(rs.getString("ISGACCUM"));
			if(variable.getLgAccumulator()!=null && variable.getIsgAccumulator()==null)
			{
				variable.setWpdAccumulator(variable.getLgAccumulator());	
			}
			else if(variable.getIsgAccumulator()!=null && variable.getLgAccumulator()==null)
			{
				variable.setWpdAccumulator(variable.getIsgAccumulator());
			}
			else if(variable.getIsgAccumulator()!=null && variable.getLgAccumulator()!=null)
			{
				String WPDAccum= variable.getLgAccumulator().concat(",").concat(variable.getIsgAccumulator());
				variable.setWpdAccumulator(WPDAccum);
			}
			else
				variable.setWpdAccumulator(null);
			return variable;
		}
	}
// modified query to fetch catagory code of lg and isg variables
	//Modified query to fetch WPD mapped Accums by adding COMMA_SEPERATED_WPDACCUM_VAR() function as part of BXNI CR35
	public List retrieveVariableInfo(Variable variable) {
		StringBuffer retrieveVarInfoforCreateQuery = new StringBuffer();
		if (null != variable.getVariableId()
				&& !"".equals(variable.getVariableId())) {
			retrieveVarInfoforCreateQuery
			.append(" select LEGACY.contVar, LEGACY.Variable_Desc, LEGACY.format, LEGACY.arrangment, LEGACY.Legacy_System,LEGACY.lgcatagory,LEGACY.isgcatagory, DETAILS.major, DETAILS.minor,LEGACY.LGACCUM,LEGACY.ISGACCUM"
					+" from ( "
			 		+"SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
			 		       +"DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
			 		        +"DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_FORMAT,  LG.contract_var_format) format,"
			 		        +"DECODE(LG.CONTRACT_VAR, NULL, ISG.CPFXP_PROV_ARNG,  LG.prov_arng) arrangment,"
			 		        +"LG.WPDACCUM LGACCUM,ISG.WPDACCUM ISGACCUM,"
			 		        +"LG.catagory lgcatagory,  ISG.cpfxp_catagory isgcatagory, "
			 		         +"       CASE"
			 		                        +" WHEN LG.CONTRACT_VAR IS NULL"
			 		                        +" THEN 'ISG'"
			 		                        +" WHEN ISG.cpfxp_contvar IS NULL"
			 		                        +" THEN 'LG'"
			 		                         +" ELSE 'LG, ISG'"
			 		                +"END Legacy_System"
			 		       +" FROM ( SELECT e.CONTRACT_VAR, e.cont_var_tx,e.contract_var_format,e.prov_arng,e.catagory,COMMA_SEPERATED_WPDACCUM_VAR(e.CONTRACT_VAR) AS WPDACCUM " 
			 		                  +"FROM CONTVAR E ) LG "
			 		                +"FULL OUTER JOIN "
			 		                +"(SELECT c.CPFXP_CONTVAR, c.CPFXP_TEXT,c.CPFXP_FORMAT, c.CPFXP_PROV_ARNG,c.cpfxp_catagory,COMMA_SEPERATED_ISGACCUM_VAR(c.CPFXP_CONTVAR) AS WPDACCUM"
			 		                 +" FROM ISG_CPFXP_CONTVAR c ) ISG"
			 		                +" ON  LG.CONTRACT_VAR = ISG.cpfxp_contvar"			 		                
			 		      +" )"
			 		       +"LEGACY "
			 		       +"LEFT OUTER JOIN "
			 		       +"(select  e.contract_var contVar, b.major_text major,"
			 		          +" c.minor_text minor from  lg_major_heading b,"
			 		           +" lg_minor_heading c, contvar e , lg_contract_detail a where a.benefit_structure = b.benefit_structure "
			 		           +" and a.benefit_structure = c.benefit_structure and a.major_heading = b.major_heading "
			 		           +" and a.major_heading = c.major_heading and a.minor_heading = c.minor_heading and "
			 		            +"e.contract_var = '"
								+ variable.getVariableId()
								+ "' and a.contract_var = e.contract_var "
			 		            +"union"
			 		            +" select c.cpfxp_contvar contVar,f.CPFXP_PC_TEXT major, e.cpfxp_pc_text minor "
			 		            +" from  isg_cpfxp_contxref c,"
			 		            +" isg_cpfxp_cntathdg d, isg_cpfxp_minhdg e, isg_CPFXP_MAJHDG f, isg_CPFXP_CONTVAR g where" 
			 		            +" c.cpfxp_contattr = d.cpfxp_contattr and d.cpfxp_pc_heading = e.cpfxp_minhdg and"
			 		            +" e.cpfxp_pc_majhdg = f.CPFXP_MAJHDG and c.cpfxp_contvar = '" + variable.getVariableId() + "' "
			 		            +" and g.CPFXP_CONTVAR = c.cpfxp_contvar ) DETAILS"
			 		        +" ON LEGACY.contVar = DETAILS.contVar"
			 		       +" where LEGACY.contVar = '"
							+ variable.getVariableId()
							+ "'");

			
			/*retrieveVarInfoforCreateQuery
					.append("select distinct  a.contract_var as \"VariableID\", e.cont_var_tx as \"Variable_desc\", b.major_text as \"Major_heading\","
							+ " c.minor_text as \"Minor_heading\",e.contract_var_format as \"Variable_format\", e.prov_arng as \"PVA\", 'LG' as \"System\" from lg_contract_detail a, lg_major_heading b,"
							+ " lg_minor_heading c, contvar e where a.benefit_structure = b.benefit_structure "
							+ " and a.benefit_structure = c.benefit_structure and a.major_heading = b.major_heading "
							+ " and a.major_heading = c.major_heading and a.minor_heading = c.minor_heading and "
							+ " a.contract_var = '"
							+ variable.getVariableId()
							+ "'"
							+ " and a.contract_var = e.contract_var(+) union "
							+ " select distinct c.cpfxp_contvar as \"VariableID\", g.cpfxp_text as \"Variable_desc\", "
							+ " f.CPFXP_PC_TEXT as \"Major_heading\", e.cpfxp_pc_text as \"Minor_heading\","
							+ " g.CPFXP_FORMAT as \"Variable_format\", g.CPFXP_PROV_ARNG as \"PVA\" , 'ISG' as \"System\" from  isg_cpfxp_contxref c,"
							+ " isg_cpfxp_cntathdg d, isg_cpfxp_minhdg e, isg_CPFXP_MAJHDG f, isg_CPFXP_CONTVAR g where "
							+ " c.cpfxp_contattr = d.cpfxp_contattr and d.cpfxp_pc_heading = e.cpfxp_minhdg and "
							+ " e.cpfxp_pc_majhdg = f.CPFXP_MAJHDG and c.cpfxp_contvar = '"
							+ variable.getVariableId()
							+ "' and g.CPFXP_CONTVAR = c.cpfxp_contvar");*/

		}
		RetrieveVariableForCreateQuery retrieveVarInfoQuery = new RetrieveVariableForCreateQuery(getDataSource(),retrieveVarInfoforCreateQuery.toString());
		List variableWithInfoList = retrieveVarInfoQuery.execute();
		if(null != variableWithInfoList && variableWithInfoList.size() > 0){
            log.debug("*****************variableList size : "+variableWithInfoList.size());
		    return variableWithInfoList;
		}
        return null;
		
	}


	private class RetrieveMappingInTempFlag extends MappingSqlQuery {

		public RetrieveMappingInTempFlag(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mapping mapping = new Mapping();
			mapping.setInTempTable(rs.getString("in_temp_tab"));
			return mapping;
		}
	}
	public boolean isMappingBeingModified(String varaiableId){
		
		String tempFlag = "";
		
		Mapping retrieveMapping = null;
		
		final String retrieveQuery = "select in_temp_tab "
			+ "from bx_cntrct_var_mapg "
			+ "where cntrct_var_id = '"
			+ varaiableId
			+ "'";			

		log.debug("Query :" + retrieveQuery);
		RetrieveMappingInTempFlag retrieveMappingInTempFlag = new RetrieveMappingInTempFlag(
				dataSource, retrieveQuery);
	
		List resultMappingRetrieveMstr = retrieveMappingInTempFlag.execute();		
		
		if(null != resultMappingRetrieveMstr && resultMappingRetrieveMstr.size() > 0){
            log.debug("*****************variableList size : "+resultMappingRetrieveMstr.size());            
            
            retrieveMapping = (Mapping) resultMappingRetrieveMstr.get(0);
            tempFlag =  retrieveMapping.getInTempTable();
		}
		if(tempFlag.equals("Y")) {
			return true;
		}
		return false;
	}

	private class RetrieveMappingStatusFromMstr extends MappingSqlQuery {

		public RetrieveMappingStatusFromMstr(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mapping mapping = new Mapping();
			mapping.setInTempTable(rs.getString("in_temp_tab"));
			mapping.setVariableMappingStatus(rs.getString("var_mapg_stts_cd"));
			return mapping;
		}
	}
	private class RetrieveMappingStatusFromTemp extends MappingSqlQuery {

		public RetrieveMappingStatusFromTemp(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mapping mapping = new Mapping();
			mapping.setVariableMappingStatus(rs.getString("var_mapg_stts_cd"));
			return mapping;
		}
	}
	public String retrieveStatus(String varaiableId){
		
		String mappingStatus = "";
		String tempFlag = "";	
		Mapping retrieveMapping = null;
		Mapping retrieveMappingMstr = null;
		
		final String retrieveQuery = "select  in_temp_tab, var_mapg_stts_cd "
			+ "from bx_cntrct_var_mapg "
			+ "where cntrct_var_id = '"
			+ varaiableId
			+ "'";			

		log.debug("Query :" + retrieveQuery);
		RetrieveMappingStatusFromMstr retrieveMappingStatusFromMstr = new RetrieveMappingStatusFromMstr(
				dataSource, retrieveQuery);
	
		List resultMappingRetrieveMstr = retrieveMappingStatusFromMstr.execute();		
		
		if(null != resultMappingRetrieveMstr && resultMappingRetrieveMstr.size() > 0){
            log.debug("*****************variableList size : "+resultMappingRetrieveMstr.size());            
            
            retrieveMappingMstr = (Mapping) resultMappingRetrieveMstr.get(0);
            tempFlag =  retrieveMappingMstr.getInTempTable();
            if(tempFlag.equals("Y")){
            	
            	final String retrieveQueryFromTemp = "select var_mapg_stts_cd "
        			+ "from temp_bx_cntrct_var_mapg "
					+ "where cntrct_var_id = '"
					+ varaiableId
					+ "'";	
            	RetrieveMappingStatusFromTemp retrieveMappingStatusFromTemp = new RetrieveMappingStatusFromTemp(
        				dataSource, retrieveQueryFromTemp);
            	
            	List resultMappingRetrieveTemp = retrieveMappingStatusFromTemp.execute();
            	
            	if(null != resultMappingRetrieveMstr && resultMappingRetrieveMstr.size() > 0){
                    log.debug("*****************variableList size : "+resultMappingRetrieveMstr.size());            
                    
                    retrieveMapping = (Mapping) resultMappingRetrieveTemp.get(0);
                    mappingStatus = retrieveMapping.getVariableMappingStatus();
            	}
            }
            else{
            	
            	mappingStatus = retrieveMappingMstr.getVariableMappingStatus();
            }	
		}
		return mappingStatus;
	}
	private class IsValidVariableChkQuery extends MappingSqlQuery {

		public IsValidVariableChkQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			VariableValidationResult result = new VariableValidationResult();
			result.setLegacyVariableId(rs.getString("contVar"));
			result.setLegacyVariableDesc(rs.getString("Variable_Desc"));
			result.setLegacyVariableFrmt(rs.getString("VAR_FRMT"));
			result.setMappedVariableId(rs.getString("CNTRCT_VAR_ID"));
			result.setMappedVariableStts(rs.getString("VAR_MAPG_STTS_CD"));
			result.setValidVariableFrmt(rs.getString("BX_VAR_FRMT"));
			return result;
		}

	}
	public int isValidVariableID(String variableID){
	int statusCode = 0;
	
	final String isValidVariableChkQuery = "SELECT  * FROM   (" +
     "SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar, " +
     "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc, " +                           
     "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT  " +    
     "FROM     (SELECT e.CONTRACT_VAR, e.cont_var_tx ,e.CONTRACT_VAR_FORMAT FROM   CONTVAR E  " +
     "WHERE e.CONTRACT_VAR='" + variableID.toUpperCase()+ "')LG " +             
     "FULL OUTER JOIN " +
     "(SELECT c.cpfxp_contvar ,c. cpfxp_text,c.cpfxp_format FROM ISG_CPFXP_CONTVAR  c  " +
     "WHERE c.cpfxp_contvar='" + variableID.toUpperCase()+ "' ) ISG " +
     "ON  LG.CONTRACT_VAR = ISG.cpfxp_contvar " +
     "ORDER BY 1 " +
	 ") LEGACY " +
	 "LEFT OUTER JOIN (SELECT  CNTRCT_VAR_ID, VAR_MAPG_STTS_CD  FROM  BX_CNTRCT_VAR_MAPG " +
	 "where bx_cntrct_var_mapg.cntrct_var_id = '" + variableID.toUpperCase()+ "') " +
	 "BX_MAPG ON BX_MAPG.CNTRCT_VAR_ID = LEGACY.contVar " +
	 "LEFT OUTER JOIN (select distinct VAR_FRMT as BX_VAR_FRMT from BX_CNTRCT_VAR_VALDN_MAPG ) " +
	 "VALID_FRMT  ON LEGACY.VAR_FRMT = VALID_FRMT.BX_VAR_FRMT";
 
	IsValidVariableChkQuery validVarChkQuery = new IsValidVariableChkQuery(getDataSource(),isValidVariableChkQuery);
	List noOfValidRecords = null;
	noOfValidRecords = validVarChkQuery.execute();
	if(null == noOfValidRecords){
		
		statusCode = 0; // VARIABLE DOES NOT EXIST IN LEGACY 
	}
	if(null != noOfValidRecords && (!noOfValidRecords.isEmpty()) && (noOfValidRecords.size()>0)){		
			
			VariableValidationResult result = (VariableValidationResult) noOfValidRecords.get(0);
			if(null != result.getMappedVariableId()){
				
				if(null != result.getMappedVariableStts()){					
				
						if(result.getMappedVariableStts().equals(DomainConstants.STATUS_NOT_APPLICABLE)){
					
							statusCode = -2; // VARIABLE MARKED AS NOT APPLICABLE 
						}
						else{
						
							statusCode = -1; //MAPPING ALREADY CREATED FOR THE VARIABLE 
						}
				}
			}	
			else
			{
				if(null == result.getValidVariableFrmt()){
				
					statusCode = -3; //CANNOT CREATE MAPPING,INVALID VARIABLE FORMAT 
				}
				else{
					statusCode = 1;	//will allow user to create a mapping for the variable
				}
			}	
		}
		
	return statusCode;
}
	/*public boolean isValidVariableID(String variableID){
		boolean isValidVariableID = false;
		final String isValidVariableChkQuery = "select * from " +
  		"(" +
  		"select  DISTINCT  e.CONTRACT_VAR as \"Variable\" , " +
  		"e.cont_var_tx as \"Variable_Desc\", " +
  		"e.creation_dt as \"Created On\" , " +
  		"'LG' as \"System\" " +
  		"from CONTVAR e where e.CONTRACT_VAR = '" + variableID.toUpperCase()+
  		"' union all " +
  		"select   DISTINCT   c.cpfxp_contvar as \"Variable\", " +
  		"c.cpfxp_text as \"Variable_Desc\", " +
  		"c.cpfxp_create_date as \"Created On\" , " +
  		"'ISG' as \"System\" " +
  		"from ISG_CPFXP_CONTVAR c where c.cpfxp_contvar = '" + variableID.toUpperCase()+
  		"' ) " +
  		"where \"Variable\" not in " +
            "(" +
	            " select DISTINCT a.cntrct_var_id as \"Variable\" " +
	            "from bx_cntrct_var_mapg a " +
            ")" +
    "order by \"Created On\" DESC";
	IsValidVariableChkQuery validVarChkQuery = new IsValidVariableChkQuery(getDataSource(),isValidVariableChkQuery);
	List noOfValidRecords = new ArrayList();
	noOfValidRecords = validVarChkQuery.execute();
	if(null != noOfValidRecords){
		if (!noOfValidRecords.isEmpty() && noOfValidRecords.size()>0){
			isValidVariableID = true;
		}
	}
	return isValidVariableID;
	}*/
	
	protected class MergeChildTableQuery extends SqlUpdate {
		protected MergeChildTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.BIGINT));
			compile();
		}

		protected void insert(String hippaSegmentName,
				HippaCodeValue hippaCodeValueObj, Mapping mapping) {
			Object[] objs = new Object[] { mapping.getVariableSystemId(),
					hippaCodeValueObj.getSeq_num(), hippaSegmentName,
					hippaCodeValueObj.getValue(), mapping.getUser().getCreatedUserName(), 
					hippaCodeValueObj.getHippaCodeValueSysId()};
			super.update(objs);
		}
	}
	
	private void mergeChildTable(List hippaCodeSelectedValues,
			String hippaSegmentName, Mapping mapping) {	
		
		if (hippaCodeSelectedValues != null) {
			
			for (int i = 0; i < hippaCodeSelectedValues.size(); i++) {
				
				hippaCodeValueObj = (HippaCodeValue) hippaCodeSelectedValues.get(i);
				if(hippaCodeValueObj.getValue() != null && hippaCodeValueObj.getValue().trim().length() > 0 ){
					
					childTbleInsertQuery = new StringBuffer();
				
					childTbleInsertQuery
							.append("INSERT INTO BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
									+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
									+ "VAR_MAPG_VAL_SYS_ID) VALUES (?,?,?,?,?,"
									+ "sysdate,?)");
					MergeChildTableQuery mergeChildTableQuery = new MergeChildTableQuery(
							getDataSource(), childTbleInsertQuery.toString());
					mergeChildTableQuery.insert(hippaSegmentName,
							hippaCodeValueObj, mapping);
				}
	
			}
		}
	}
	
	private List<Object[]> createChildTableObjectArray(List hippaCodeSelectedValues,
			String hippaSegmentName, Mapping mapping) {	
		
		List<Object[]> batch = new ArrayList<Object[]>();
		if (hippaCodeSelectedValues != null) {
			for (int i = 0; i < hippaCodeSelectedValues.size(); i++) {
				hippaCodeValueObj = (HippaCodeValue) hippaCodeSelectedValues.get(i);
				if(hippaCodeValueObj.getValue() != null && hippaCodeValueObj.getValue().trim().length() > 0 ){
					Object[] values = new Object[] {
							mapping.getVariableSystemId(),
							hippaCodeValueObj.getSeq_num(), hippaSegmentName,
							hippaCodeValueObj.getValue(), mapping.getUser().getCreatedUserName(), 
							hippaCodeValueObj.getHippaCodeValueSysId(),null};
		            batch.add(values);
				}
			}
		}
		return batch;
	}
	
	private Object[] createChildTableEb03AssociationObjectArray(Mapping mapping,Long seqNo,String hippaSegmentName,
			String value,Long hippaCodeValueSysId,String eb03Assn) {	
		Object[] values = new Object[] {
				mapping.getVariableSystemId(),
				seqNo, hippaSegmentName,
				value, mapping.getUser().getCreatedUserName(),
				hippaCodeValueSysId,
				eb03Assn};
		return values;
	}
	
	protected class MergeSQLChildTable extends SqlUpdate {
		private SimpleJdbcTemplate simpleJdbcTemplate;
		
		public void setDataSource(DataSource dataSource) {
	        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    }
		
		private String query = "INSERT INTO BX_CNTRCT_VAR_MAPG_VAL (VAR_MAPG_SYS_ID,SEQ_NUM,"
			+ "DATA_ELEMENT_ID,DATA_ELEMENT_VAL,CREATD_USER_ID,CREATD_TM_STMP,"
			+ "VAR_MAPG_VAL_SYS_ID,EB03_ASSN) VALUES (?,?,?,?,?,"
			+ "sysdate,?,?)";
		
		protected void batchInsert(int batchSize, List<Object[]> batchList) {
			int divisionNo = batchList.size()/batchSize;
			int reminderNo = batchList.size()%batchSize;
			int i=0;
			for(i=0; i<divisionNo ; i+=batchSize) {
				List<Object[]> retrieveList = batchList.subList(i,i+(batchSize-1));
				simpleJdbcTemplate.batchUpdate(query, retrieveList);
			}
			if(reminderNo != 0) {
				List<Object[]> retrieveList = batchList.subList(i,i+reminderNo);
				simpleJdbcTemplate.batchUpdate(query, retrieveList);
			}
		}
	}
	
	private class DeleteMasterChildQuery extends SqlUpdate {
		private SimpleJdbcTemplate simpleJdbcTemplate;
		
		public void setDataSource(DataSource dataSource) {
	        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    }
		
		int count = 0;
		
		private static final String SQL_QUERY = "DELETE FROM BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID =?" ;

		protected void batchDelete(int batchSize, List<Mapping> mappingList) {
			
			List<Object[]> batch = new ArrayList<Object[]>();
	        for (Mapping mapping : mappingList) {
	        	Object[] values = new Object[] {mapping.getVariableSystemId()};
	            batch.add(values);
	            if(++count % batchSize == 0) {
	            	simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
	            	batch = new ArrayList<Object[]>();
	            }
	        }
	        simpleJdbcTemplate.batchUpdate(SQL_QUERY, batch);
		}

	}
	
	public boolean mergeMapping(int batchSize, List<Mapping> mappingList) {
		
		List<Object[]> totalBatchList = new ArrayList<Object[]>();
		
		if(mappingList != null && mappingList.size() != 0){
		
			UpdateMappingMstrForMerge updateMappingMstrForMerge = new UpdateMappingMstrForMerge();
			updateMappingMstrForMerge.setDataSource(getDataSource());
			updateMappingMstrForMerge.batchUpdate(batchSize, mappingList);
			
			DeleteMasterChildQuery deleteMasterChildQuery = new DeleteMasterChildQuery();
			deleteMasterChildQuery.setDataSource(getDataSource());
			deleteMasterChildQuery.batchDelete(batchSize, mappingList);
			
			for(Mapping mapping : mappingList) {
				if (mapping.getEb01() != null && (mapping.getEb01().getName().equals("EB01"))) {
					List<Object[]> eb01List = createChildTableObjectArray(mapping.getEb01().getHippaCodeSelectedValues(),
						mapping.getEb01().getName(), mapping);
					totalBatchList.addAll(eb01List);
				}
				if (mapping.getEb02() != null && (mapping.getEb02().getName().equals("EB02"))) {
					List<Object[]> eb02List = createChildTableObjectArray(mapping.getEb02().getHippaCodeSelectedValues(),
						mapping.getEb02().getName(), mapping);
					totalBatchList.addAll(eb02List);
				}
				if (mapping.getEb03() != null && (mapping.getEb03().getName().equals("EB03"))) {
					if(null != mapping.getEb03().getEb03Association() && mapping.getEb03().getEb03Association().size() != 0) {
						for(EB03Association eb03Assn : mapping.getEb03().getEb03Association()) {
							String eb03Val = eb03Assn.getEb03().getValue();
							if (null != eb03Assn && null != eb03Assn.getEb03() 
									&& null != eb03Assn.getEb03().getValue() && !DomainConstants.EMPTY.equals(eb03Assn.getEb03().getValue())) {
								Object[] eb03ObjectArray = createChildTableEb03AssociationObjectArray(mapping,eb03Assn.getEb03().getSeq_num(),"EB03",
										eb03Assn.getEb03().getValue(),eb03Assn.getEb03().getHippaCodeValueSysId(),eb03Val);
								totalBatchList.add(eb03ObjectArray);
							}
							if (!eb03Assn.getIii02List().isEmpty()) {
								HippaCodeValue iii02 = eb03Assn.getIii02List().get(0);
								if (null != iii02 && null != iii02.getValue() && !DomainConstants.EMPTY.equals(iii02.getValue())) {
									Object[] iii02ObjectArray = createChildTableEb03AssociationObjectArray(mapping,iii02.getSeq_num(),"III02",
											iii02.getValue(),iii02.getHippaCodeValueSysId(),eb03Val);
									totalBatchList.add(iii02ObjectArray);
								}
							}
							if (null != eb03Assn.getMsgReqdInd() && !DomainConstants.EMPTY.equals(eb03Assn.getMsgReqdInd())) {
								Object[] msgReqIndObjectArray = createChildTableEb03AssociationObjectArray(mapping,eb03Assn.getEb03().getSeq_num(),"MSG_REQD",
										eb03Assn.getMsgReqdInd(),eb03Assn.getMesgReqIndValueSysId(),eb03Val);
								totalBatchList.add(msgReqIndObjectArray);
							}
							if (null != eb03Assn.getMessage() && !DomainConstants.EMPTY.equals(eb03Assn.getMessage())) {
								Object[] msgObjectArray = createChildTableEb03AssociationObjectArray(mapping,eb03Assn.getEb03().getSeq_num(),"MSG",
										eb03Assn.getMessage(),eb03Assn.getMesgValueSysId(),eb03Val);
								totalBatchList.add(msgObjectArray);
							}
							if (null != eb03Assn.getNoteType() && null != eb03Assn.getNoteType().getValue()
									&& !DomainConstants.EMPTY.equals(eb03Assn.getNoteType().getValue())) {
								Object[] noteTypeObjectArray = createChildTableEb03AssociationObjectArray(mapping,eb03Assn.getNoteType().getSeq_num(),"NOTE_TYPE_CODE",
										eb03Assn.getNoteType().getValue(),eb03Assn.getNoteType().getHippaCodeValueSysId(),eb03Val);
								totalBatchList.add(noteTypeObjectArray);
							}
						}
					}
				}
				if (mapping.getEb06() != null && mapping.getEb06().getName().equals("EB06")) {
					List<Object[]> eb06List = createChildTableObjectArray(mapping.getEb06().getHippaCodeSelectedValues(),
						mapping.getEb06().getName(), mapping);
					totalBatchList.addAll(eb06List);
				}
				if (mapping.getEb09() != null && (mapping.getEb09().getName().equals("EB09"))) {
					List<Object[]> eb09List = createChildTableObjectArray(mapping.getEb09().getHippaCodeSelectedValues(),
						mapping.getEb09().getName(), mapping);
					totalBatchList.addAll(eb09List);
				}
				if (mapping.getHsd01() != null && (mapping.getHsd01().getName().equals("HSD01"))) {
					List<Object[]> hsd01List = createChildTableObjectArray(mapping.getHsd01().getHippaCodeSelectedValues(),
						mapping.getHsd01().getName(), mapping);
					totalBatchList.addAll(hsd01List);
				}
				if (mapping.getHsd02() != null && (mapping.getHsd02().getName().equals("HSD02"))) {
					List<Object[]> hsd02List = createChildTableObjectArray(mapping.getHsd02().getHippaCodeSelectedValues(),
						mapping.getHsd02().getName(), mapping);
					totalBatchList.addAll(hsd02List);
				}
				if (mapping.getHsd03() != null && (mapping.getHsd03().getName().equals("HSD03"))) {
					List<Object[]> hsd03List = createChildTableObjectArray(mapping.getHsd03().getHippaCodeSelectedValues(),
						mapping.getHsd03().getName(), mapping);
					totalBatchList.addAll(hsd03List);
				}
				if (mapping.getHsd04() != null && (mapping.getHsd04().getName().equals("HSD04"))) {
					List<Object[]> hsd04List = createChildTableObjectArray(mapping.getHsd04().getHippaCodeSelectedValues(),
						mapping.getHsd04().getName(), mapping);
					totalBatchList.addAll(hsd04List);
				}
				if (mapping.getHsd05() != null && (mapping.getHsd05().getName().equals("HSD05"))) {
					List<Object[]> hsd05List =createChildTableObjectArray(mapping.getHsd05().getHippaCodeSelectedValues(),
						mapping.getHsd05().getName(), mapping);
					totalBatchList.addAll(hsd05List);
				}
				if (mapping.getHsd06() != null && (mapping.getHsd06().getName().equals("HSD06"))) {
					List<Object[]> hsd06List = createChildTableObjectArray(mapping.getHsd06().getHippaCodeSelectedValues(),
						mapping.getHsd06().getName(), mapping);
					totalBatchList.addAll(hsd06List);
				}
				if (mapping.getHsd07() != null && (mapping.getHsd07().getName().equals("HSD07"))) {
					List<Object[]> hsd07List = createChildTableObjectArray(mapping.getHsd07().getHippaCodeSelectedValues(),
						mapping.getHsd07().getName(), mapping);
					totalBatchList.addAll(hsd07List);
				}
				if (mapping.getHsd08() != null && (mapping.getHsd08().getName().equals("HSD08"))) {
					List<Object[]> hsd08List = createChildTableObjectArray(mapping.getHsd08().getHippaCodeSelectedValues(),
						mapping.getHsd08().getName(), mapping);
					totalBatchList.addAll(hsd08List);
				}
				if (mapping.getAccum() != null && (mapping.getAccum().getName().equals("ACCUM"))) {
					List<Object[]> accumList = createChildTableObjectArray(mapping.getAccum().getHippaCodeSelectedValues(),
							mapping.getAccum().getName(), mapping);
					totalBatchList.addAll(accumList);
				}
				if (mapping.getUtilizationMgmntRule() != null && (mapping.getUtilizationMgmntRule().getName().equals(DomainConstants.UMRULE_NAME))) {
					List<Object[]> umRuleList = createChildTableObjectArray(mapping.getUtilizationMgmntRule()
						.getHippaCodeSelectedValues(), mapping.getUtilizationMgmntRule()
						.getName(), mapping);
					totalBatchList.addAll(umRuleList);
				}
				if (mapping.getStartAge() != null && (mapping.getStartAge().getName().equals("START_AGE"))) {
					List<Object[]> startAgeList = createChildTableObjectArray(mapping.getStartAge().getHippaCodeSelectedValues(),
						mapping.getStartAge().getName(), mapping);
					totalBatchList.addAll(startAgeList);
				}
				if (mapping.getEndAge() != null && (mapping.getEndAge().getName().equals("END_AGE"))) {
					List<Object[]> endAgeList = createChildTableObjectArray(mapping.getEndAge().getHippaCodeSelectedValues(),
						mapping.getEndAge().getName(), mapping);
					totalBatchList.addAll(endAgeList);
				}
				// NM1 Message Segment.
				if (mapping.getNm1MessageSegment() != null && (mapping.getNm1MessageSegment().getName().equals(DomainConstants.NM1_MSG_SGMNT))) {
					List<Object[]> nm1MessageSegmentList = createChildTableObjectArray(mapping.getNm1MessageSegment().getHippaCodeSelectedValues(),
						mapping.getNm1MessageSegment().getName(), mapping);
					totalBatchList.addAll(nm1MessageSegmentList);
				}
			}
			MergeSQLChildTable mergeSQLChildTable = new MergeSQLChildTable();
			mergeSQLChildTable.setDataSource(getDataSource());
			mergeSQLChildTable.batchInsert(batchSize, totalBatchList);
		}	
		return true;
	}
	/**
	 * @return Returns the sysIDFrmMstrTble.
	 */
	public Long getSysIDFrmMstrTble() {
		return sysIDFrmMstrTble;
	}

	/**
	 * @param sysIDFrmMstrTble
	 *            The sysIDFrmMstrTble to set.
	 */
	public void setSysIDFrmMstrTble(Long sysIDFrmMstrTble) {
		this.sysIDFrmMstrTble = sysIDFrmMstrTble;
	}

	/**
	 * @return Returns the sequenceGeneratorRepository.
	 */
	public SequenceGeneratorRepository getSequenceGeneratorRepository() {
		return sequenceGeneratorRepository;
	}

	/**
	 * @param sequenceGeneratorRepository
	 *            The sequenceGeneratorRepository to set.
	 */
	public void setSequenceGeneratorRepository(
			SequenceGeneratorRepository sequenceGeneratorRepository) {
		this.sequenceGeneratorRepository = sequenceGeneratorRepository;
	}
	
	public SPSMappingRetrieveResult getSPSMappingMain(String spsId){
		return null;
	}
	
	/** SSCR19537 April 04 Changes - Start*/
	/**
	 * @param eb03Associations
	 * @param createdUser
	 * @param tableName
	 * Iterating EB03 association object list to persist EB03 and associated values in the given VAL table.
	 */
	private void persistEb03Associations(List<EB03Association> eb03Associations, String createdUser, String tableName) {
		sequenceNo = 0;
		for (EB03Association eb03Assn : eb03Associations) {
			persistEb03AssociationValue(eb03Assn, createdUser, tableName);
		}
	}
	
	/**
	 * @param eb03Associations
	 * @param varMapngSysId
	 * @param createdUser
	 * @param tableName
	 * Method to update EB03 and associated values in the given VAL table.
	 * 1. Delete all EB03 associated values for a particular variable
	 * 2. Iterate and Insert all EB03 and associated values for a the same variable. 
	 */
	private void updateEB03Associations(List<EB03Association> eb03Associations,
			Long varMapngSysId, String createdUser, String tableName) {
		
		mstrTbleSysID = varMapngSysId; 
		sequenceNo = 0;
		if (null != varMapngSysId) {
			deleteAllEB03AssociationValues(varMapngSysId, tableName);
		}
		if (null != eb03Associations && !eb03Associations.isEmpty()) {
			for (EB03Association eb03Assn : eb03Associations) {
				if (null != eb03Assn) {
					persistEb03AssociationValue(eb03Assn, createdUser, tableName);
				}
			}
		}
	}
	
	/**
	 * @param varMapngSysId
	 * @param tableName
	 * Method to delete all the EB03 and associated values of a given variable.
	 * 1. Build the delete query
	 * 2. Execute the delete query for the given VAL table.
	 * 
	 */
	private void deleteAllEB03AssociationValues(Long varMapngSysId,
			String tableName) {
		
		String deleteAllEb03AssnQuery = getDeleteAllEb03AssnQuery(tableName);
		DeleteAllEb03AssociationQuery deleteAllEb03AssociationQuery = new DeleteAllEb03AssociationQuery(
				getDataSource(), deleteAllEb03AssnQuery, tableName);
		deleteAllEb03AssociationQuery.deleteAllEb03Association(varMapngSysId);
	}
	
	/**
	 * @param tableName
	 * @return
	 * Method to build query to delete all EB03 and associated values for the given VAL table.
	 */
	private String getDeleteAllEb03AssnQuery(String tableName) {
		StringBuffer deleteAllEb03AssnQuery = new StringBuffer();
		deleteAllEb03AssnQuery.append("DELETE FROM ");
		deleteAllEb03AssnQuery.append(tableName);
		deleteAllEb03AssnQuery.append(" WHERE VAR_MAPG_SYS_ID = ? AND DATA_ELEMENT_ID IN (");
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(DomainConstants.EB03_NAME);
		deleteAllEb03AssnQuery.append("', ");
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(DomainConstants.III02_NAME);
		deleteAllEb03AssnQuery.append("', ");
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(DomainConstants.MESG_REQD_IND);
		deleteAllEb03AssnQuery.append("', ");
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(DomainConstants.VAR_MSG);
		deleteAllEb03AssnQuery.append("', ");
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(DomainConstants.NOTE_TYPE_CODE);
		deleteAllEb03AssnQuery.append("'");
		deleteAllEb03AssnQuery.append(")");
		return deleteAllEb03AssnQuery.toString();
	}

	/**
	 * @param eb03Assn
	 * @param createdUser
	 * @param tableName
	 * Method to persist EB03 and associated values.
	 * 1. Build insert query to insert EB03 and associated values.
	 * 2. increment the sequence number.
	 * 3. Generate the Unique Id for the VAL table and then insert EB03.
	 * 4. Generate the Unique Id for the VAL table and then insert III02.
	 * 5. Generate the Unique Id for the VAL table and then insert MESG_REQD_IND.
	 * 6. Generate the Unique Id for the VAL table and then insert MESSAGE TEXT.
	 * 7. Generate the Unique Id for the VAL table and then insert NOTE_TYPE_CODE. 
	 * 
	 */
	private void persistEb03AssociationValue(EB03Association eb03Assn,
			String createdUser, String tableName) {
		
		if (null != eb03Assn && null != eb03Assn.getEb03() 
				&& null != eb03Assn.getEb03().getValue() && !DomainConstants.EMPTY.equals(eb03Assn.getEb03().getValue())) {
			
			String insertEB03AssnQuery = getInsertEB03AssnQuery(tableName);
			sequenceNo = sequenceNo + 1;
			childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
			String eb03Val = eb03Assn.getEb03().getValue();
			InsetEb03AssociationQuery insetEb03AssociationQuery = new InsetEb03AssociationQuery(getDataSource(), insertEB03AssnQuery);
			
			/** Inserting EB03*/
			insetEb03AssociationQuery.insert(DomainConstants.EB03_NAME, eb03Val, createdUser, eb03Val);
			
			/** Inserting the III02 values corresponding associated EB03*/
			if (!eb03Assn.getIii02List().isEmpty()) {
				HippaCodeValue iii02 = eb03Assn.getIii02List().get(0);
				if (null != iii02 && null != iii02.getValue() && !DomainConstants.EMPTY.equals(iii02.getValue())) {
					childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
					insetEb03AssociationQuery.insert(DomainConstants.III02_NAME, iii02.getValue(), createdUser, eb03Val);
				}
			}
			/** Inserting Message Required Indicator corresponding associated EB03*/
			if (null != eb03Assn.getMsgReqdInd() && !DomainConstants.EMPTY.equals(eb03Assn.getMsgReqdInd())) {
				childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
				insetEb03AssociationQuery.insert(DomainConstants.MESG_REQD_IND, eb03Assn.getMsgReqdInd(), createdUser, eb03Val);
			}
			
			/** Inserting message corresponding associated EB03*/
			if (null != eb03Assn.getMessage() && !DomainConstants.EMPTY.equals(eb03Assn.getMessage())) {
				childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
				insetEb03AssociationQuery.insert(DomainConstants.VAR_MSG, eb03Assn.getMessage(), createdUser, eb03Val);
			}
			/** Inserting Note Type Code corresponding associated EB03*/
			if (null != eb03Assn.getNoteType() && null != eb03Assn.getNoteType().getValue()
					&& !DomainConstants.EMPTY.equals(eb03Assn.getNoteType().getValue())) {
				childTbleSysID = sequenceGeneratorRepository.generateSequence(childTbleSeqColName);
				insetEb03AssociationQuery.insert(DomainConstants.NOTE_TYPE_CODE, eb03Assn.getNoteType().getValue(), createdUser, eb03Val);
			}
		}
		
	}
	
	/**
	 * @param tableName
	 * @return
	 * Method to build the insert query to insert the EB03 and associated values.
	 */
	private String getInsertEB03AssnQuery(String tableName) {
		StringBuffer insertEB03AssnQuery = new StringBuffer();
		insertEB03AssnQuery.append("INSERT INTO ");
		insertEB03AssnQuery.append(tableName);
		if (DomainConstants.MAIN_MAPPING_VAL.equals(tableName)) {
			insertEB03AssnQuery.append("(VAR_MAPG_VAL_SYS_ID,");
		} else if (DomainConstants.TEMP_MAPPING_VAL.equals(tableName)) {
			insertEB03AssnQuery.append("(TEMP_VAR_MAPG_VAL_SYS_ID,");
		}
		insertEB03AssnQuery
				.append(" VAR_MAPG_SYS_ID, SEQ_NUM,  DATA_ELEMENT_ID, DATA_ELEMENT_VAL, CREATD_USER_ID, CREATD_TM_STMP, EB03_ASSN)");
		insertEB03AssnQuery.append(" VALUES ( ?,?,?,?,?,?,sysdate,?)");
		return insertEB03AssnQuery.toString();
	}
	
	protected class DeleteAllEb03AssociationQuery extends SqlUpdate {

		protected DeleteAllEb03AssociationQuery(DataSource dataSource, String sql, String tableName) {
			super(dataSource, sql);
			if (DomainConstants.MAIN_MAPPING_VAL.equals(tableName)) {
				declareParameter(new SqlParameter("VAR_MAPG_VAL_SYS_ID", Types.BIGINT));
			} else if (DomainConstants.TEMP_MAPPING_VAL.equals(tableName)) {
				declareParameter(new SqlParameter("TEMP_VAR_MAPG_VAL_SYS_ID", Types.BIGINT));
			}
			compile();
		}

		public void deleteAllEb03Association(Long varMapngSysId) {
			Object[] objs = new Object[] {varMapngSysId};
			super.update(objs);
		}
	}
	
	protected class InsetEb03AssociationQuery extends SqlUpdate {
		protected InsetEb03AssociationQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.BIGINT));
			declareParameter(new SqlParameter(Types.INTEGER));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			compile();
		}

		protected void insert(String hippaSegmentName, String hippaSegmentVal, String createdUser, String eb03Val) {
			Object[] objs = new Object[] { childTbleSysID, mstrTbleSysID,
					Integer.valueOf(sequenceNo), hippaSegmentName.toUpperCase(),
					hippaSegmentVal.toUpperCase(),createdUser.toUpperCase(),eb03Val};
			super.update(objs);
		}
	}
	/** SSCR19537 April 04 Changes - End*/
	public void updateHSD02Values(List hippaCodeSelectedValues, Long varMapngSysId, String createdUser, String tableName){
		mstrTbleSysID = varMapngSysId;
		if (null != varMapngSysId) {
			StringBuffer deleteAllHSD02Query = new StringBuffer();
			deleteAllHSD02Query.append("Delete from BX_CNTRCT_VAR_MAPG_VAL WHERE VAR_MAPG_SYS_ID = ?");
			//deleteAllHSD02Query.append(varMapngSysId);
			deleteAllHSD02Query.append(" AND DATA_ELEMENT_ID IN ('");
			deleteAllHSD02Query.append(DomainConstants.HSD02_NAME);
			deleteAllHSD02Query.append("')");
			DeleteAllHSD02Assoc deleteAllHSD02Assoc = new DeleteAllHSD02Assoc(getDataSource(), deleteAllHSD02Query.toString(), tableName);
			deleteAllHSD02Assoc.deleteAllHSD02Association(varMapngSysId);
		}
		if(null != hippaCodeSelectedValues && !hippaCodeSelectedValues.isEmpty()){
			persistChildTable(mstrTbleSysID,DomainConstants.HSD02_NAME,hippaCodeSelectedValues,  createdUser);
		}
		
	}
	
	protected class DeleteAllHSD02Assoc extends SqlUpdate {
		protected DeleteAllHSD02Assoc(DataSource dataSource, String sql, String tableName){
			super(dataSource, sql);
			if (DomainConstants.MAIN_MAPPING_VAL.equals(tableName)) {
				declareParameter(new SqlParameter("VAR_MAPG_VAL_SYS_ID", Types.BIGINT));
			} else if (DomainConstants.TEMP_MAPPING_VAL.equals(tableName)) {
				declareParameter(new SqlParameter("TEMP_VAR_MAPG_VAL_SYS_ID", Types.BIGINT));
			}
			compile();
		}
		public void deleteAllHSD02Association(Long varMapngSysId){
			Object[] objs = new Object[] {varMapngSysId};
			super.update(objs);
		
		}
	}
	public void updateTempHsd02Values(List hippaCodeSelectedValues, Long varMapngSysId, String createdUser, String tableName, Mapping mapping){
		mstrTbleSysID = varMapngSysId;
		if (null != varMapngSysId) {
			StringBuffer deleteAllHSD02Query = new StringBuffer();
			deleteAllHSD02Query.append("Delete from ");
			deleteAllHSD02Query.append(tableName);
			deleteAllHSD02Query.append(" WHERE VAR_MAPG_SYS_ID = ?");
			deleteAllHSD02Query.append(" AND DATA_ELEMENT_ID IN ('");
			deleteAllHSD02Query.append(DomainConstants.HSD02_NAME);
			deleteAllHSD02Query.append("')");
			DeleteAllHSD02Assoc deleteAllHSD02Assoc = new DeleteAllHSD02Assoc(getDataSource(), deleteAllHSD02Query.toString(), tableName);
			deleteAllHSD02Assoc.deleteAllHSD02Association(varMapngSysId);
		}
		if(null != hippaCodeSelectedValues && !hippaCodeSelectedValues.isEmpty()){
			List updatedHippaCodeValueList = new ArrayList();
			long counter=1L;
			for(int val=0;val<hippaCodeSelectedValues.size();val++){
				HippaCodeValue hippaCodeVal = (HippaCodeValue) hippaCodeSelectedValues.get(val);
				if(!hippaCodeVal.getValue().isEmpty()){
					hippaCodeVal.setSeq_num(counter);
					hippaCodeVal.setHippaCodeValueSysId(sequenceGeneratorRepository.generateSequence(childTbleSeqColName));
					updatedHippaCodeValueList.add(hippaCodeVal);
					counter = counter +1L;
				}
			}
			persistTempChildTable(mapping,DomainConstants.HSD02_NAME,
					updatedHippaCodeValueList);
		}
		
	
		
	}

}