/*
 * <VariableLocateRepositoryImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL This is an implementation class for locating or searching
 *         a Sps id or multiple Sps id based on certain search criteria
 */
public class VariableLocateRepositoryImpl implements VariableLocateRepository {

	private DataSource dataSource;

	private static Logger log = Logger
			.getLogger(VariableLocateRepositoryImpl.class);

	private static final String unmappedVariableQuery = "SELECT "
			+ "/*+ CPU_COSTING */ "
			+ "* "
			+ "FROM   (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
			+ "              CASE"
			+ "                       WHEN LG.creation_dt IS NULL"
			+ "                       THEN ISG.cpfxp_create_date"
			+ "                       WHEN ISG.cpfxp_create_date IS NULL"
			+ "                       THEN LG.creation_dt"
			+ "                       WHEN LG.creation_dt > ISG.cpfxp_create_date"
			+ "                       THEN ISG.cpfxp_create_date"
			+ "                       ELSE LG.creation_dt"
			+ "              END            create_date  ,"
			+ "              DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
			+ "				DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT,"
			+ "              CASE"
			+ "                       WHEN LG.CONTRACT_VAR IS NULL"
			+ "                       THEN 'ISG'"
			+ "                       WHEN ISG.cpfxp_contvar IS NULL"
			+ "                       THEN 'LG'"
			+ "                       ELSE 'LG, ISG'"
			+ "              END Legacy_System "
			+ "     FROM     (SELECT e.CONTRACT_VAR,"
			+ "                      e.cont_var_tx ,"
			+ "                      e.creation_dt ,"
			+ "						e.CONTRACT_VAR_FORMAT,"
			+ "                      'LG'"
			+ "              FROM    CONTVAR E"
			+ "              )"
			+ "              LG"
			+ "              FULL OUTER JOIN"
			+ "                       (SELECT c.cpfxp_contvar    ,"
			+ "                               c.cpfxp_text       ,"
			+ "                               c.cpfxp_create_date,"
			+ "								 c.cpfxp_format,"
			+ "                               'ISG'"
			+ "                       FROM    ISG_CPFXP_CONTVAR c"
			+ "                       )"
			+ "                       ISG"
			+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
			+ "     ORDER BY 2 DESC,"
			+ "              1"
			+ "     )"
			+ "     LEGACY"
			+ " INNER JOIN"
			+ " (select distinct VAR_FRMT from BX_CNTRCT_VAR_VALDN_MAPG ) VALID_FRMT"
			+ "  ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT"
			+ "	WHERE  LEGACY.contVar NOT IN" + "	      (SELECT  CNTRCT_VAR_ID"
			+ "	      FROM     BX_CNTRCT_VAR_MAPG"
			+ "	      GROUP BY CNTRCT_VAR_ID" + "	      )";

	private static final String unmappedVariableWith50Rows = unmappedVariableQuery
			+ "	AND    rownum < 51";

	//Commented the query as not used.
	/**
	 * may release change for inprogress query to bring mappings of all users
	 * and lock information
	 *//*
	private static final String legacy = " select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( "
			+ "SELECT * FROM("
			+ "SELECT"
			+ "     BX.*, LEGACY.Legacy_System "
			+ "FROM (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
			+ "              CASE"
			+ "                      WHEN LG.CONTRACT_VAR IS NULL"
			+ "                       THEN 'ISG'"
			+ "                       WHEN ISG.cpfxp_contvar IS NULL"
			+ "                       THEN 'LG'"
			+ "                       ELSE 'LG, ISG'"
			+ "              END Legacy_System"
			+ "     FROM     (SELECT e.CONTRACT_VAR  FROM    CONTVAR E )  LG"
			+ "              FULL OUTER JOIN"
			+ "              (SELECT c.cpfxp_contvar   FROM    ISG_CPFXP_CONTVAR c ) ISG"
			+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
			+ "     )"
			+ "     LEGACY,"
			+ "            ("
			+ "              select  TRIM(b.CNTRCT_VAR_ID) CNTRCT_VAR_ID,b.CNTRCT_VAR_DESC ,b.var_mapg_stts_cd,b.var_mapg_sys_id,b.LST_CHG_TMS,b.MAPPNG_CMP_IND,b.LST_CHG_USR "
			+ "              from bx_cntrct_var_mapg b"
			+ "              where b.VAR_MAPG_STTS_CD <> '"
			+ DomainConstants.STATUS_NOT_APPLICABLE
			+ "' "
			+ "              and b.VAR_MAPG_STTS_CD <> '"
			+ DomainConstants.STATUS_PUBLISHED
			+ "' "
			+ "              and b.in_temp_tab = 'N' ";*/
	
	private static final String inProgressQuery = " select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( "
		+ "SELECT * FROM("
		+ "SELECT"
		+ "     BX.*, LEGACY.Legacy_System "
		+ "FROM (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
		+ "              CASE"
		+ "                      WHEN LG.CONTRACT_VAR IS NULL"
		+ "                       THEN 'ISG'"
		+ "                       WHEN ISG.cpfxp_contvar IS NULL"
		+ "                       THEN 'LG'"
		+ "                       ELSE 'LG, ISG'"
		+ "              END Legacy_System "
		+ "     FROM     (SELECT e.CONTRACT_VAR  FROM    CONTVAR E )  LG"
		+ "              FULL OUTER JOIN"
		+ "              (SELECT c.cpfxp_contvar   FROM    ISG_CPFXP_CONTVAR c ) ISG"
		+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
		+ "     )"
		+ "     LEGACY,"
		+ "            ("
		+ "              select  TRIM(b.CNTRCT_VAR_ID) CNTRCT_VAR_ID,b.CNTRCT_VAR_DESC ,b.var_mapg_stts_cd,b.var_mapg_sys_id,b.LST_CHG_TMS,b.MAPPNG_CMP_IND,b.LST_CHG_USR,b.AUDIT_LOCK "
		+ "              from bx_cntrct_var_mapg b"
		+ "              where b.VAR_MAPG_STTS_CD <> '"
		+ DomainConstants.STATUS_NOT_APPLICABLE
		+ "' "
		+ "              and b.VAR_MAPG_STTS_CD <> '"
		+ DomainConstants.STATUS_PUBLISHED
		+ "' "
		+ "              and b.VAR_MAPG_STTS_CD <> '"
		+ "INITIAL_LOAD"
		+ "' "
		+ "              and b.in_temp_tab = 'N' ";
	
	private static final String tempTableInProgress = " select TRIM(t.CNTRCT_VAR_ID) CNTRCT_VAR_ID,t.CNTRCT_VAR_DESC ,t.var_mapg_stts_cd ,t.var_mapg_sys_id,t.LST_CHG_TMS,t.MAPPNG_CMP_IND,t.LST_CHG_USR,t.AUDIT_LOCK "
			+ "from temp_bx_cntrct_var_mapg t ";

	public List findUnmappedVariableStartingWith(String variableId,
			int noOfRecords) {
		List unmappedVariables = new ArrayList();
		if (variableId == null || "".equals(variableId.trim())) {
			log.debug("There is no variableId.");
			return unmappedVariables;
		}
		variableId = variableId.trim();
		String query = unmappedVariableQuery + "AND LEGACY.contVar like '"
				+ variableId + "%'";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query += "and rownum < " + noOfRecords + " ";
		}

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UnmappedVariableLocateQuery varLocQry = new UnmappedVariableLocateQuery(
				dataSource, query);
		unmappedVariables = varLocQry.execute();
		if (null != unmappedVariables && unmappedVariables.size() > 0) {
			log
					.trace("*****************Size of unmapped Variables (autocomplete of Variabe ID ): "
							+ unmappedVariables.size());
		}
		return unmappedVariables;
	}

	private static class UnmappedVariableLocateQuery extends MappingSqlQuery {

		private UnmappedVariableLocateQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.Mapping mapping = new com.wellpoint.ets.bx.mapping.domain.entity.Mapping();
			mapping.setVariable(new Variable());
			mapping.getVariable().setVariableId(rs.getString("contVar"));
			mapping.getVariable().setCreatedDate(rs.getDate("create_date"));
			if (rs.getString("Variable_Desc") != null) {
				mapping.getVariable().setDescription(
						rs.getString("Variable_Desc").toUpperCase());
			}
			mapping.getVariable().setVariableDescForDisplay(mapping.getVariable().getDescription());
			mapping.getVariable().setVariableSystem(
					rs.getString("Legacy_System"));
			// mapping.setVariableMappingStatus()
			return mapping;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.repository.VariableLocateRepository#findAllUnMappedVariable()
	 */
	public List findAllUnmappedVariables() {

		log
				.debug("Query for unmapped variable : "
						+ unmappedVariableWith50Rows);
		UnmappedVariableLocateQuery varLocQry = new UnmappedVariableLocateQuery(
				dataSource, unmappedVariableWith50Rows);
		List unmappedVariables = varLocQry.execute();
		if (null != unmappedVariables && unmappedVariables.size() > 0) {
			log.debug("*****************Size of unmapped Variables : "
					+ unmappedVariables.size());
		}
		return unmappedVariables;
	}
	/**
	 * Inner class for InProgress variable Locate Mapping
	 */
	private static class InprogressVariableLocateQuery extends MappingSqlQuery {
		boolean printFlag;
		private InprogressVariableLocateQuery(DataSource dataSource,
				String query, boolean printFlag) {
			
			super(dataSource, query);
			super.compile();
			this.printFlag = printFlag;
		}

		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		protected Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Mapping mapping = new Mapping();
			mapping.setUser(new User());
			mapping.setVariable(new Variable());
			mapping.setVariableSystemId(Long.valueOf(rs
					.getString("VAR_MAPG_SYS_ID")));
			mapping.getVariable().setVariableId(
					rs.getString("CNTRCT_VAR_ID").trim());
			String desc = rs.getString("CNTRCT_VAR_DESC");
			if (desc != null) {
				mapping.getVariable().setDescription(desc.toUpperCase());
			}

			mapping.setVariableMappingStatus(rs.getString("VAR_MAPG_STTS_CD"));
			mapping.getVariable().setCreatedDate(rs.getDate("LST_CHG_TMS"));
			String checkForFinalized = rs.getString("MAPPNG_CMP_IND");
			if (null != checkForFinalized) {
				if (checkForFinalized.equals("N")) {
					mapping.setFinalized(false);
				} else if (checkForFinalized.equals("Y")) {
					mapping.setFinalized(true);
				}
			}
			mapping.isFinalized();
			mapping.getVariable().setVariableSystem(
					rs.getString("LEGACY_SYSTEM"));
			mapping.setAuditLock(rs.getString("AUDIT_LOCK"));
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if (printFlag) {
				mapping.setSortField("Variable");
			}
			if (null != lockUserId) {

				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			mapping.getUser().setLastUpdatedUserName(
					rs.getString("LST_CHG_USR"));
			return mapping;
		}
	}

	/**
	 * Returns a list of mappings which have a mapping in the blue exchange tables
	 * @param String
	 * @param int
	 * @param boolean
	 * @return List
	 */
	public List findAllInProgressVariables(String loggedInUser,
			int noOfRecords, boolean printFlag) {

		// log.trace("query for inprogress variables : "
		// + inProgressVarableQuery50Records);
		String inProgressVariable = inProgressQuery;
		// for may release, if logged in user is not null append query to
		// retrieve
		// based on logged in user
		if (null != loggedInUser) {

			inProgressVariable = inProgressVariable + " and b.LST_CHG_USR= '"
					+ loggedInUser + "' " + " union " + tempTableInProgress
					+ " where t.LST_CHG_USR = '" + loggedInUser + "'" + " ) BX"
					+ " where LEGACY.contVar = BX.CNTRCT_VAR_ID order by LST_CHG_TMS DESC )";
					
		} else {

			inProgressVariable = inProgressVariable + " union "
					+ tempTableInProgress + " ) BX"
					+ "      where LEGACY.contVar = BX.CNTRCT_VAR_ID order by LST_CHG_TMS DESC )";
					
		}

		if (noOfRecords != -1) {

			inProgressVariable = inProgressVariable + "	Where    rownum < "
					+ noOfRecords;
		}

		// May release - bring lock details with inprogress mappings
		inProgressVariable = inProgressVariable
				+ " ) vareBX left outer join bolk_bus_obj_lock lk "
				+ " on vareBX.CNTRCT_VAR_ID = lk.bolk_bus_obj_key_id "
				+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'"
				+ " order by LST_CHG_TMS DESC ";
		
		if (printFlag) {
			inProgressVariable = "select * from ( " + inProgressVariable
					+ ") ORDER BY CNTRCT_VAR_ID";
		}
		long locateMappingStartTime = System.currentTimeMillis();
		InprogressVariableLocateQuery inprogressVariableLocateQuery = new InprogressVariableLocateQuery(
				dataSource, inProgressVariable, printFlag);
		
		


		List inProgressVariables = inprogressVariableLocateQuery.execute();
		
		long locateMappingEndTime = System.currentTimeMillis();
		log.info("Time taken for executing variable Query = "+ (locateMappingEndTime - locateMappingStartTime));

		if (null != inProgressVariables && inProgressVariables.size() > 0) {
			log.trace("*****************inProgressVariables size : "
					+ inProgressVariables.size());
		}
		return inProgressVariables;
	}

	/**
	 * Inner class for locating the variable mapping
	 *
	 */
	private static class LocateResultFromBXQuery extends MappingSqlQuery {

		private LocateResultFromBXQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.Mapping mapping = new com.wellpoint.ets.bx.mapping.domain.entity.Mapping();
			mapping.setVariable(new Variable());
			mapping.setVariableSystemId(Long.valueOf(rs
					.getString("VAR_MAPG_SYS_ID")));
			mapping.getVariable().setVariableId(rs.getString("CNTRCT_VAR_ID"));
			mapping.getVariable().setDescription(
					rs.getString("CNTRCT_VAR_DESC"));
			mapping.setVariableMappingStatus(rs.getString("VAR_MAPG_STTS_CD"));
			mapping.getVariable().setCreatedDate(rs.getDate("LST_CHG_TMS"));
			mapping.getVariable().setVariableSystem(
					rs.getString("LEGACY_SYSTEM"));
			mapping.setUser(new User());
			mapping.getUser().setLastUpdatedUserName(
					rs.getString("LST_CHG_USR"));
			mapping.setAuditLock(rs.getString("AUDIT_LOCK"));
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if (null != lockUserId) {

				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			return mapping;
		}

	}

	private static class GetUnmappedVariablesForLocateQuery extends
			MappingSqlQuery {

		private GetUnmappedVariablesForLocateQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.Mapping mapping = new com.wellpoint.ets.bx.mapping.domain.entity.Mapping();
			mapping.setVariable(new Variable());
			mapping.getVariable().setVariableId(rs.getString("contVar"));
			if (rs.getString("Variable_Desc") != null) {
				mapping.getVariable().setDescription(
						rs.getString("Variable_Desc").toUpperCase());
			}
			mapping.getVariable().setCreatedDate(rs.getDate("create_date"));
			mapping.setVariableMappingStatus(DomainConstants.UN_MAPPED);
			mapping.getVariable().setVariableSystem(
					rs.getString("Legacy_System"));
			if (rs.getString("VARIABLE_STATUS").equals("C")) {
				mapping.getVariable().setCodedStatus(true);
			} else {
				mapping.getVariable().setCodedStatus(false);
			}

			return mapping;
		}

	}
	/**
	 * Inner class for getting the mappings of unmapped mapped and not applicable
	 *
	 */
	private static class GetUnmppdMappdNotAppVarQuery extends MappingSqlQuery {

		private GetUnmppdMappdNotAppVarQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			com.wellpoint.ets.bx.mapping.domain.entity.Mapping mapping = new com.wellpoint.ets.bx.mapping.domain.entity.Mapping();
			mapping.setVariable(new Variable());
			mapping.getVariable().setVariableId(rs.getString("contVar"));
			if (rs.getString("Variable_Desc") != null) {
				mapping.getVariable().setDescription(
						rs.getString("Variable_Desc").toUpperCase());
			}
			mapping.getVariable().setCreatedDate(rs.getDate("create_date"));
			mapping.setVariableMappingStatus(rs.getString("status"));
			mapping.getVariable().setVariableSystem(
					rs.getString("Legacy_System"));
			if (mapping.getVariableMappingStatus().equals(
					DomainConstants.UN_MAPPED)) {
				if (rs.getString("CODED_STATUS").equals("C")) {
					mapping.getVariable().setCodedStatus(true);
				} else {
					mapping.getVariable().setCodedStatus(false);
				}
			}
			mapping.setUser(new User());
			mapping.getUser().setLastUpdatedUserName(
					rs.getString("last_changed_usr"));
			mapping.setAuditLock(rs.getString("AUDIT_LOCK"));
			String lockUserId = rs.getString("bolk_bus_obj_lock_usr_id");
			if (null != lockUserId) {

				Lock lock = new Lock();
				lock.setLockUserId(lockUserId);
				mapping.setLock(lock);
			}
			return mapping;
		}

	}

	/**
	 * Returns a list of mappings that match the locate criteria
	 * @param variable
	 * @param page	 
	 * @return List
	 */
	public List findLocateResultsMatchingVariables(Variable variable, Page page) {
		StringBuffer locateMappedAndNotAppVariablesQuery = new StringBuffer();
		StringBuffer locateUnMappedVariablesQuery = new StringBuffer();
		StringBuffer locateUnMapdMapdNotAppQuery = new StringBuffer();
		List locateResultList = new ArrayList();
		String contionalquery = "";
		String cndtnlQry = "";
		String ConditionalQuery = "";

		String upperCondtnStrt = "";
		String upperCondtnEnd = "";
		if ((null != variable.getDescription())
				&& !(variable.getDescription().equals(""))) {
			upperCondtnStrt = " UPPER( ";
			upperCondtnEnd = " )";
		}

		if (!(variable.isMappedVariable()) && !(variable.isNotApplicable())) {
			if (variable.isUnmappedVariable()) {
				locateUnMappedVariablesQuery
						.append("SELECT * FROM ( "
								+ "SELECT UNMAPPED.contVar, UNMAPPED.Variable_Desc, UNMAPPED.create_date,"
								+ " UNMAPPED.Legacy_System, DECODE(CODED.VARIABLE, NULL, 'N', 'C') as VARIABLE_STATUS, ROWNUM rnm "
								+ "FROM"
								+ "(SELECT "
								+ "/*+ CPU_COSTING */ "
								+ "* "
								+ "FROM   (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                       WHEN LG.creation_dt IS NULL"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       WHEN ISG.cpfxp_create_date IS NULL"
								+ "                       THEN LG.creation_dt"
								+ "                       WHEN LG.creation_dt > ISG.cpfxp_create_date"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       ELSE LG.creation_dt"
								+ "              END            create_date  ,"
								+ "              DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
								+ "				DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT,"
								+ "              CASE"
								+ "                       WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System "
								+ "     FROM     (SELECT e.CONTRACT_VAR,"
								+ "                      e.cont_var_tx ,"
								+ "                      e.creation_dt ,"
								+ "						e.CONTRACT_VAR_FORMAT,"
								+ "                      'LG'"
								+ "              FROM    CONTVAR E"
								+ "              )"
								+ "              LG"
								+ "              FULL OUTER JOIN"
								+ "                       (SELECT c.cpfxp_contvar    ,"
								+ "                               c.cpfxp_text       ,"
								+ "                               c.cpfxp_create_date,"
								+ "								 c.cpfxp_format,"
								+ "                               'ISG'"
								+ "                       FROM    ISG_CPFXP_CONTVAR c"
								+ "                       )"
								+ "                       ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     ORDER BY 2 DESC,"
								+ "              1"
								+ "     )"
								+ "     LEGACY"
								+ " INNER JOIN"
								+ " (select distinct VAR_FRMT from BX_CNTRCT_VAR_VALDN_MAPG ) VALID_FRMT"
								+ "  ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT"
								+ "	WHERE  LEGACY.contVar NOT IN"
								+ "	      (SELECT  CNTRCT_VAR_ID"
								+ "	      FROM     BX_CNTRCT_VAR_MAPG"
								+ "	      GROUP BY CNTRCT_VAR_ID" + " )" + ")"
								+ "UNMAPPED "
								+ "LEFT OUTER JOIN mv_variable_stts CODED "
								+ "ON UNMAPPED.contVar = CODED.VARIABLE "
								+ "WHERE " + "UNMAPPED.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' AND " + upperCondtnStrt
								+ " UNMAPPED.VARIABLE_DESC" + upperCondtnEnd
								+ " like '%"
								+ variable.getDescription().toUpperCase()
								+ "%'" + " order by UNMAPPED.create_date desc "
								+ " )");
				locateUnMappedVariablesQuery
						.append("WHERE rnm between " + page.getStartRowNum()
								+ " AND " + page.getEndRowNum());

				GetUnmappedVariablesForLocateQuery unMappedVarQry = new GetUnmappedVariablesForLocateQuery(
						dataSource, locateUnMappedVariablesQuery.toString());
				log
						.debug("query for unmapped variables in locate results section: "
								+ESAPI.encoder().encodeForHTML(locateUnMappedVariablesQuery.toString()));
				List unMappedList = unMappedVarQry.execute();
				locateResultList.add(unMappedList);
			}
		}

		if (!(variable.isUnmappedVariable())) {
			if ((variable.isMappedVariable()) || (variable.isNotApplicable())) {
				if ((variable.isMappedVariable())
						&& !(variable.isNotApplicable())) {
					contionalquery = " and b.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
					cndtnlQry = " and bx.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
				}
				if ((variable.isNotApplicable())
						&& !(variable.isMappedVariable())) {
					contionalquery = " and b.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE'";
					cndtnlQry = " and bx.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE'";
				}
				if ((variable.isMappedVariable())
						&& (variable.isNotApplicable())) {
					contionalquery = "";
					cndtnlQry = "";
				}

				locateMappedAndNotAppVariablesQuery
						.append("select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( "
								+ "SELECT *  from ("
								+ "SELECT CNTRCT_VAR_ID,CNTRCT_VAR_DESC,VAR_MAPG_STTS_CD,"
								+ "VAR_MAPG_SYS_ID,LST_CHG_TMS,LEGACY_SYSTEM ,  LST_CHG_USR ,AUDIT_LOCK, ROWNUM rnm "
								+ "FROM "
								+ "( SELECT"
								+ "     BX.*, LEGACY.Legacy_System "
								+ "FROM (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                      WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System"
								+ "     FROM     (SELECT e.CONTRACT_VAR  FROM    CONTVAR E )  LG"
								+ "              FULL OUTER JOIN"
								+ "              (SELECT c.cpfxp_contvar   FROM    ISG_CPFXP_CONTVAR c ) ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     )"
								+ "     LEGACY,"
								+ "            ("
								+ "              select  b.CNTRCT_VAR_ID,b.CNTRCT_VAR_DESC ,b.var_mapg_stts_cd,b.var_mapg_sys_id,b.LST_CHG_TMS, b.LST_CHG_USR,b.AUDIT_LOCK"
								+ "                from bx_cntrct_var_mapg b"
								+ "      				where b.in_temp_tab = 'N'"
								+ contionalquery
								+ "      				union "
								+ "      				select t.CNTRCT_VAR_ID,t.CNTRCT_VAR_DESC ,t.var_mapg_stts_cd ,t.var_mapg_sys_id,t.LST_CHG_TMS, t.LST_CHG_USR,t.AUDIT_LOCK"
								+ "      				from temp_bx_cntrct_var_mapg t "
								+ "      				) BX"
								+ "      where LEGACY.contVar = BX.CNTRCT_VAR_ID "
								+ cndtnlQry
								+ " and LEGACY.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' and "
								+ upperCondtnStrt
								+ "BX.CNTRCT_VAR_DESC "
								+ upperCondtnEnd
								+ " like '%"
								+ variable.getDescription().toUpperCase()
								+ "%'"
								+ "      order by BX.LST_CHG_TMS desc )"
								+ " )");
				locateMappedAndNotAppVariablesQuery
						.append("Where  rnm between " + page.getStartRowNum()
								+ " AND " + page.getEndRowNum());

				locateMappedAndNotAppVariablesQuery
						.append(" )vareBX left outer join bolk_bus_obj_lock lk "
								+ " on vareBX.CNTRCT_VAR_ID = lk.bolk_bus_obj_key_id "
								+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'");
				//WLPRD02076161--->start
				//System.err.println("Locate results query-->"+locateMappedAndNotAppVariablesQuery);
				log.info("Locate results query-->"+ESAPI.encoder().encodeForHTML(locateMappedAndNotAppVariablesQuery.toString()));
				//WLPRD02076161--->end
				LocateResultFromBXQuery mappedAndOrNotAppVarQry = new LocateResultFromBXQuery(
						dataSource, locateMappedAndNotAppVariablesQuery
								.toString());
				log
						.debug("query for mapped/not app variables in locate results section: "
								+ mappedAndOrNotAppVarQry.toString());
				List mappedAndOrNotAppVarList = mappedAndOrNotAppVarQry
						.execute();
				locateResultList.add(mappedAndOrNotAppVarList);
			}

		}

		if ((variable.isUnmappedVariable())
				&& ((variable.isMappedVariable()) || (variable
						.isNotApplicable()))) {
			if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = "";
			} else if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& !(variable.isNotApplicable())) {
				ConditionalQuery = "and results.status != 'NOT_APPLICABLE'";
			} else if ((variable.isUnmappedVariable())
					&& !(variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = " and (results.status = 'NOT_APPLICABLE' OR results.status = 'UN MAPPED') ";
			}
			locateUnMapdMapdNotAppQuery
					.append("select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( "
							+ "SELECT * FROM "
							+ "(SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status, CODED_STATUS, last_changed_usr,AUDIT_LOCK, ROWNUM rnm FROM ( "
							+ "SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status, CODED_STATUS, last_changed_usr ,AUDIT_LOCK from ( "
							+ "SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS , "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr,BX.AUDIT_LOCK "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ "UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' ,"
							+ " e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' ,"
							+ " c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr,b.AUDIT_LOCK "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ "UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS  , t.lst_chg_usr,t.AUDIT_LOCK "
							+ "FROM temp_bx_cntrct_var_mapg t ) BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ "results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ " LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%'"
							+ ConditionalQuery
							+ " order by results.create_date desc "
							+ " )"
							+ " )");
			locateUnMapdMapdNotAppQuery.append("WHERE rnm between "
					+ page.getStartRowNum() + " AND " + page.getEndRowNum());

			locateUnMapdMapdNotAppQuery
					.append(")vareBX left outer join bolk_bus_obj_lock lk "
							+ " on vareBX.contVar = lk.bolk_bus_obj_key_id "
							+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'");

			GetUnmppdMappdNotAppVarQuery unMappdMappdNotAppQry = new GetUnmppdMappdNotAppVarQuery(
					dataSource, locateUnMapdMapdNotAppQuery.toString());
			log
					.debug("query for unmapped/mapped/notapplicable variables in locate results section: "
							+ESAPI.encoder().encodeForHTML(locateUnMappedVariablesQuery.toString()));
			List combinedList = unMappdMappdNotAppQry.execute();
			locateResultList.add(combinedList);

		}

		if (!(variable.isUnmappedVariable()) && !(variable.isMappedVariable())
				&& !(variable.isNotApplicable())) {
			ConditionalQuery = "";
			locateUnMapdMapdNotAppQuery
					.append("select vareBX.*, lk.bolk_bus_obj_key_id, lk.bolk_bus_obj_lock_usr_id from ( "
							+ "SELECT * FROM "
							+ "(SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status, CODED_STATUS, last_changed_usr, ROWNUM rnm FROM ( "
							+ "SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status, CODED_STATUS ,last_changed_usr from ("
							+ "SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS ,"
							+ " DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ " UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ " DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' ,"
							+ "e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' ,"
							+ "c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ "UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS  , t.lst_chg_usr "
							+ "FROM temp_bx_cntrct_var_mapg t "
							+ ") BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ "results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ "LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%'"
							+ ConditionalQuery
							+ " order by results.create_date desc "
							+ " )"
							+ " )");
			locateUnMapdMapdNotAppQuery.append("WHERE rnm between "
					+ page.getStartRowNum() + " AND " + page.getEndRowNum());

			locateUnMapdMapdNotAppQuery
					.append(" )vareBX left outer join bolk_bus_obj_lock lk "
							+ "on vareBX.contVar = lk.bolk_bus_obj_key_id "
							+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping'");

			GetUnmppdMappdNotAppVarQuery unMappdMappdNotAppQry = new GetUnmppdMappdNotAppVarQuery(
					dataSource, locateUnMapdMapdNotAppQuery.toString());
			log
					.debug("query for unmapped/mapped/notapplicable variables in locate results section: "
							+ESAPI.encoder().encodeForHTML(locateUnMapdMapdNotAppQuery.toString()));
			List combinedList = unMappdMappdNotAppQry.execute();
			locateResultList.add(combinedList);
		}

		if (null != locateResultList && locateResultList.size() > 0) {
			log.debug("Size of locate results Variables list: "
					+ locateResultList.size());
			return locateResultList;
		}
		return null;
	}
	/**
	 * Inner class for getting the record count for pagination of the results
	 *
	 */
	private static class FindTotalNoOfRecordsQuery extends MappingSqlQuery {

		private FindTotalNoOfRecordsQuery(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}
		/**
		 * sets the values to mapping object
		 * @param ResultSet
		 * @param arg1
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Integer.valueOf(rs.getInt(1));
		}

	}
	/**
	 * Returns the record count for locate inorder to paginate the results
	 * @param variable
	 * @return int
	 */
	public int findTotalNoOfRecords(Variable variable) {
		StringBuffer totalNoOfRcrdsQuery = new StringBuffer();
		String ConditionalQuery = "";
		String contionalquery = "";
		String upperCondtnStrt = "";
		String upperCondtnEnd = "";
		if ((null != variable.getDescription())
				&& !(variable.getDescription().equals(""))) {
			upperCondtnStrt = " UPPER( ";
			upperCondtnEnd = " )";
		}
		// When only unmapped is checked
		if (!(variable.isMappedVariable()) && !(variable.isNotApplicable())) {
			if (variable.isUnmappedVariable()) {
				totalNoOfRcrdsQuery
						.append("SELECT COUNT(*) FROM ( "
								+ "SELECT UNMAPPED.contVar, UNMAPPED.Variable_Desc, UNMAPPED.create_date,"
								+ " UNMAPPED.Legacy_System, DECODE(CODED.VARIABLE, NULL, 'N', 'C') as VARIABLE_STATUS, ROWNUM rnm "
								+ "FROM"
								+ "(SELECT "
								+ "/*+ CPU_COSTING */ "
								+ "* "
								+ "FROM   (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                       WHEN LG.creation_dt IS NULL"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       WHEN ISG.cpfxp_create_date IS NULL"
								+ "                       THEN LG.creation_dt"
								+ "                       WHEN LG.creation_dt > ISG.cpfxp_create_date"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       ELSE LG.creation_dt"
								+ "              END            create_date  ,"
								+ "              DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
								+ "				DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT,"
								+ "              CASE"
								+ "                       WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System "
								+ "     FROM     (SELECT e.CONTRACT_VAR,"
								+ "                      e.cont_var_tx ,"
								+ "                      e.creation_dt ,"
								+ "						e.CONTRACT_VAR_FORMAT,"
								+ "                      'LG'"
								+ "              FROM    CONTVAR E"
								+ "              )"
								+ "              LG"
								+ "              FULL OUTER JOIN"
								+ "                       (SELECT c.cpfxp_contvar    ,"
								+ "                               c.cpfxp_text       ,"
								+ "                               c.cpfxp_create_date,"
								+ "								 c.cpfxp_format,"
								+ "                               'ISG'"
								+ "                       FROM    ISG_CPFXP_CONTVAR c"
								+ "                       )"
								+ "                       ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     ORDER BY 2 DESC,"
								+ "              1"
								+ "     )"
								+ "     LEGACY"
								+ " INNER JOIN"
								+ " (select distinct VAR_FRMT from BX_CNTRCT_VAR_VALDN_MAPG ) VALID_FRMT"
								+ "  ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT"
								+ "	WHERE  LEGACY.contVar NOT IN"
								+ "	      (SELECT  CNTRCT_VAR_ID"
								+ "	      FROM     BX_CNTRCT_VAR_MAPG"
								+ "	      GROUP BY CNTRCT_VAR_ID" + " )" + ")"
								+ "UNMAPPED "
								+ "LEFT OUTER JOIN mv_variable_stts CODED "
								+ "ON UNMAPPED.contVar = CODED.VARIABLE "
								+ "WHERE " + "UNMAPPED.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' AND " + upperCondtnStrt
								+ "UNMAPPED.VARIABLE_DESC " + upperCondtnEnd
								+ "like '%"
								+ variable.getDescription().toUpperCase()
								+ "%'" + ")");
			}
		}
		// when either mapped or notapplicable or both of them are checked
		if (!(variable.isUnmappedVariable())) {
			if ((variable.isMappedVariable()) || (variable.isNotApplicable())) {
				if ((variable.isMappedVariable())
						&& !(variable.isNotApplicable())) {
					contionalquery = "b.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE' and ";
				}
				if ((variable.isNotApplicable())
						&& !(variable.isMappedVariable())) {
					contionalquery = "b.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE'and";
				}
				if ((variable.isMappedVariable())
						&& (variable.isNotApplicable())) {
					contionalquery = "";
				}

				totalNoOfRcrdsQuery
						.append("SELECT COUNT(*) FROM("
								+ " select vareBX.*,lk.* from ( "
								+ "SELECT"
								+ "     BX.*, LEGACY.Legacy_System "
								+ "FROM (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                      WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System"
								+ "     FROM     (SELECT e.CONTRACT_VAR  FROM    CONTVAR E )  LG"
								+ "              FULL OUTER JOIN"
								+ "              (SELECT c.cpfxp_contvar   FROM    ISG_CPFXP_CONTVAR c ) ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     )"
								+ "     LEGACY,"
								+ "            ("
								+ "              select  b.CNTRCT_VAR_ID,b.CNTRCT_VAR_DESC ,b.var_mapg_stts_cd,b.var_mapg_sys_id,b.LST_CHG_TMS, b.LST_CHG_USR "
								+ "                from bx_cntrct_var_mapg b"
								+ "      				where "
								+ contionalquery
								+ "                 b.in_temp_tab = 'N'"
								+ "      				union "
								+ "      				select t.CNTRCT_VAR_ID,t.CNTRCT_VAR_DESC ,t.var_mapg_stts_cd ,t.var_mapg_sys_id,t.LST_CHG_TMS, t.LST_CHG_USR "
								+ "      				from temp_bx_cntrct_var_mapg t "
								+ "      				) BX"
								+ "      where LEGACY.contVar = BX.CNTRCT_VAR_ID and LEGACY.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' and "
								+ upperCondtnStrt
								+ " BX.CNTRCT_VAR_DESC "
								+ upperCondtnEnd
								+ " like '%"
								+ variable.getDescription().toUpperCase()
								+ "%'"
								+ "      order by BX.LST_CHG_TMS desc) "
								+ "vareBX left outer join bolk_bus_obj_lock lk "
								+ "on vareBX.CNTRCT_VAR_ID = lk.bolk_bus_obj_key_id "
								+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping')");
			}
		}

		// when unmapped is checked along with the combination of mapped and or
		// not applicable
		if ((variable.isUnmappedVariable())
				&& ((variable.isMappedVariable()) || (variable
						.isNotApplicable()))) {
			if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = ") ";
			} else if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& !(variable.isNotApplicable())) {
				ConditionalQuery = "and results.status != 'NOT_APPLICABLE') ";
			} else if ((variable.isUnmappedVariable())
					&& !(variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = " and (results.status = 'NOT_APPLICABLE' OR results.status = 'UN MAPPED') )";
			}
			totalNoOfRcrdsQuery
					.append("SELECT COUNT (*) from ( "
							+ "select vareBX.*,lk.* from ( "
							+ "SELECT * FROM "
							+ "(SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS ,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ "UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ " DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' , "
							+ "e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' , "
							+ "c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ " UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS , t.lst_chg_usr "
							+ "FROM temp_bx_cntrct_var_mapg t ) BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ " results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ " LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%'"
							+ ConditionalQuery
							+ "vareBX left outer join bolk_bus_obj_lock lk "
							+ "on vareBX.contVar = lk.bolk_bus_obj_key_id "
							+ "and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping')");

		}
		// when all the three are unchecked
		if (!(variable.isUnmappedVariable()) && !(variable.isMappedVariable())
				&& !(variable.isNotApplicable())) {
			ConditionalQuery = " ) ";
			totalNoOfRcrdsQuery
					.append("SELECT COUNT (*) from ( "
							+ "select vareBX.*,lk.* from ( "
							+ " SELECT * FROM ( "
							+ "SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS ,"
							+ " DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ "UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr  "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' ,"
							+ " e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' ,"
							+ " c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ "UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS , t.lst_chg_usr "
							+ "FROM temp_bx_cntrct_var_mapg t "
							+ ") BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ " results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ " LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%'"
							+ ConditionalQuery
							+ " vareBX left outer join bolk_bus_obj_lock lk "
							+ " on vareBX.contVar = lk.bolk_bus_obj_key_id "
							+ " and lk.bolk_bus_obj_type_nm = 'com.wellpoint.ets.bx.mapping.domain.entity.Mapping')");

		}
		FindTotalNoOfRecordsQuery totalNoOfRcrdsQry = new FindTotalNoOfRecordsQuery(
				dataSource, totalNoOfRcrdsQuery.toString());

		List totalNoOfRecordsList = totalNoOfRcrdsQry.execute();
		Integer totalNoOfRecords = (Integer) totalNoOfRecordsList.get(0);
		return totalNoOfRecords.intValue();
	}
	
	/**
	 * @see com.wellpoint.ets.bx.mapping.repository.VariableLocateRepository#findAllmappedVariableStartingWith(java.lang.String, int)
	 * @param variableId
	 * @param noOfRecordsForAutoComplete
	 * @return
	 */
	public List findAllVariableStartingWith(String variableId, int noOfRecords) {
		List allVariables = new ArrayList();
		if (variableId == null || "".equals(variableId.trim())) {
			log.debug("There is no variableId.");
			return allVariables;
		}
		variableId = variableId.trim();
		String query = allVariableQuery + "WHERE LEGACY.contVar like '"
				+ variableId + "%'";
		if (noOfRecords > 0) {
			noOfRecords = noOfRecords + 1;
			query += " and rownum < " + noOfRecords + " ";
		}

		log.trace("%%%%%%%%%%%%%%%%from ajax: " + query);
		UnmappedVariableLocateQuery varLocQry = new UnmappedVariableLocateQuery(
				dataSource, query);
		allVariables = varLocQry.execute();
		if (null != allVariables && allVariables.size() > 0) {
			log
					.trace("*****************Size of all Variables (autocomplete of Variabe ID ): "
							+ allVariables.size());
		}
		return allVariables;
	
	}
	
	 /**
	 * for generating reports from locate
	 * @param variable
	 * @return List
	 */
	public List getRecordsForReport(Variable variable) {
		
		StringBuffer locateMappedAndNotAppVariablesQuery = new StringBuffer();
		StringBuffer locateUnMappedVariablesQuery = new StringBuffer();
		StringBuffer locateUnMapdMapdNotAppQuery = new StringBuffer();
		String contionalquery = "";
		String cndtnlQry = "";
		String ConditionalQuery = "";
		List allVariables = null;
		String locateQuery = "";

		String upperCondtnStrt = "";
		String upperCondtnEnd = "";
		if ((null != variable.getDescription())
				&& !(variable.getDescription().equals(""))) {
			upperCondtnStrt = " UPPER( ";
			upperCondtnEnd = " )";
		}

		if (!(variable.isMappedVariable()) && !(variable.isNotApplicable())) {
			if (variable.isUnmappedVariable()) {
				locateUnMappedVariablesQuery
						.append("SELECT UNMAPPED.contVar , UNMAPPED.Variable_Desc, UNMAPPED.create_date,"
								+ " UNMAPPED.Legacy_System, 'UN MAPPED' as status "
								+ "FROM"
								+ "(SELECT "
								+ "/*+ CPU_COSTING */ "
								+ "* "
								+ "FROM   (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                       WHEN LG.creation_dt IS NULL"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       WHEN ISG.cpfxp_create_date IS NULL"
								+ "                       THEN LG.creation_dt"
								+ "                       WHEN LG.creation_dt > ISG.cpfxp_create_date"
								+ "                       THEN ISG.cpfxp_create_date"
								+ "                       ELSE LG.creation_dt"
								+ "              END            create_date  ,"
								+ "              DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
								+ "				DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT,"
								+ "              CASE"
								+ "                       WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System "
								+ "     FROM     (SELECT e.CONTRACT_VAR,"
								+ "                      e.cont_var_tx ,"
								+ "                      e.creation_dt ,"
								+ "						e.CONTRACT_VAR_FORMAT,"
								+ "                      'LG'"
								+ "              FROM    CONTVAR E"
								+ "              )"
								+ "              LG"
								+ "              FULL OUTER JOIN"
								+ "                       (SELECT c.cpfxp_contvar    ,"
								+ "                               c.cpfxp_text       ,"
								+ "                               c.cpfxp_create_date,"
								+ "								 c.cpfxp_format,"
								+ "                               'ISG'"
								+ "                       FROM    ISG_CPFXP_CONTVAR c"
								+ "                       )"
								+ "                       ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     ORDER BY 2 DESC,"
								+ "              1"
								+ "     )"
								+ "     LEGACY"
								+ " INNER JOIN"
								+ " (select distinct VAR_FRMT from BX_CNTRCT_VAR_VALDN_MAPG ) VALID_FRMT"
								+ "  ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT"
								+ "	WHERE  LEGACY.contVar NOT IN"
								+ "	      (SELECT  CNTRCT_VAR_ID"
								+ "	      FROM     BX_CNTRCT_VAR_MAPG"
								+ "	      GROUP BY CNTRCT_VAR_ID" + " )" + ")"
								+ "UNMAPPED "
								+ "LEFT OUTER JOIN mv_variable_stts CODED "
								+ "ON UNMAPPED.contVar = CODED.VARIABLE "
								+ "WHERE " + "UNMAPPED.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' AND " + upperCondtnStrt
								+ " UNMAPPED.VARIABLE_DESC" + upperCondtnEnd
								+ " like '%"
								+ variable.getDescription().toUpperCase()
								+ "%'");
				locateQuery = locateUnMappedVariablesQuery.toString();
			}
		}

		if (!(variable.isUnmappedVariable())) {
			if ((variable.isMappedVariable()) || (variable.isNotApplicable())) {
				if ((variable.isMappedVariable())
						&& !(variable.isNotApplicable())) {
					contionalquery = " and b.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
					cndtnlQry = " and bx.VAR_MAPG_STTS_CD != 'NOT_APPLICABLE'";
				}
				if ((variable.isNotApplicable())
						&& !(variable.isMappedVariable())) {
					contionalquery = " and b.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE'";
					cndtnlQry = " and bx.VAR_MAPG_STTS_CD = 'NOT_APPLICABLE'";
				}
				if ((variable.isMappedVariable())
						&& (variable.isNotApplicable())) {
					contionalquery = "";
					cndtnlQry = "";
				}

				locateMappedAndNotAppVariablesQuery
						.append(" SELECT CNTRCT_VAR_ID contVar ,CNTRCT_VAR_DESC Variable_Desc,VAR_MAPG_STTS_CD status,"
								+ "VAR_MAPG_SYS_ID ,LST_CHG_TMS create_date,LEGACY_SYSTEM  Legacy_System "
								+ "FROM "
								+ "( SELECT"
								+ "     BX.*, LEGACY.Legacy_System "
								+ "FROM (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
								+ "              CASE"
								+ "                      WHEN LG.CONTRACT_VAR IS NULL"
								+ "                       THEN 'ISG'"
								+ "                       WHEN ISG.cpfxp_contvar IS NULL"
								+ "                       THEN 'LG'"
								+ "                       ELSE 'LG, ISG'"
								+ "              END Legacy_System"
								+ "     FROM     (SELECT e.CONTRACT_VAR  FROM    CONTVAR E )  LG"
								+ "              FULL OUTER JOIN"
								+ "              (SELECT c.cpfxp_contvar   FROM    ISG_CPFXP_CONTVAR c ) ISG"
								+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
								+ "     )"
								+ "     LEGACY,"
								+ "            ("
								+ "              select  b.CNTRCT_VAR_ID,b.CNTRCT_VAR_DESC ,b.var_mapg_stts_cd,b.var_mapg_sys_id,b.LST_CHG_TMS, b.LST_CHG_USR"
								+ "                from bx_cntrct_var_mapg b"
								+ "      				where b.in_temp_tab = 'N'"
								+ contionalquery
								+ "      				union "
								+ "      				select t.CNTRCT_VAR_ID,t.CNTRCT_VAR_DESC ,t.var_mapg_stts_cd ,t.var_mapg_sys_id,t.LST_CHG_TMS, t.LST_CHG_USR"
								+ "      				from temp_bx_cntrct_var_mapg t "
								+ "      				) BX"
								+ "      where LEGACY.contVar = BX.CNTRCT_VAR_ID "
								+ cndtnlQry
								+ " and LEGACY.contVar like '%"
								+ variable.getVariableId().toUpperCase()
								+ "%' and "
								+ upperCondtnStrt
								+ "BX.CNTRCT_VAR_DESC "
								+ upperCondtnEnd
								+ " like '%"
								+ variable.getDescription().toUpperCase()
								+ "%' ) ");
				locateQuery = locateMappedAndNotAppVariablesQuery.toString();
			}
		}

		if ((variable.isUnmappedVariable())
				&& ((variable.isMappedVariable()) || (variable
						.isNotApplicable()))) {
			if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = "";
			} else if ((variable.isUnmappedVariable())
					&& (variable.isMappedVariable())
					&& !(variable.isNotApplicable())) {
				ConditionalQuery = "and results.status != 'NOT_APPLICABLE'";
			} else if ((variable.isUnmappedVariable())
					&& !(variable.isMappedVariable())
					&& (variable.isNotApplicable())) {
				ConditionalQuery = " and (results.status = 'NOT_APPLICABLE' OR results.status = 'UN MAPPED') ";
			}
			locateUnMapdMapdNotAppQuery
					.append("SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status FROM ( "
							+ "SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS , "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ "UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' ,"
							+ " e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' ,"
							+ " c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ "UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS  , t.lst_chg_usr "
							+ "FROM temp_bx_cntrct_var_mapg t ) BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ "results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ " LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%'"
							+ ConditionalQuery);
			locateQuery = locateUnMapdMapdNotAppQuery.toString();
		}

		if (!(variable.isUnmappedVariable()) && !(variable.isMappedVariable())
				&& !(variable.isNotApplicable())) {
			ConditionalQuery = "";
			locateUnMapdMapdNotAppQuery
					.append("SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status FROM ( "
							+ "SELECT contVar, Variable_Desc, Legacy_System, "
							+ "create_date, status, CODED_STATUS ,last_changed_usr from ("
							+ "SELECT UMAPPED_CODE_STS.contVar contVar,UMAPPED_CODE_STS.Variable_Desc Variable_Desc, "
							+ "UMAPPED_CODE_STS.Legacy_System Legacy_System, "
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.create_date, BX.LST_CHG_TMS)  create_date,"
							+ "DECODE(BX.CNTRCT_VAR_ID, NULL,'UN MAPPED', BX.var_mapg_stts_cd)  status,"
							+ "UMAPPED_CODE_STS.VARIABLE_STATUS CODED_STATUS ,"
							+ " DECODE(BX.CNTRCT_VAR_ID, NULL,UMAPPED_CODE_STS.last_changed_usr, BX.LST_CHG_USR) last_changed_usr "
							+ "from ( "
							+ "SELECT UNMAPPED.contVar,"
							+ "UNMAPPED.Variable_Desc, "
							+ "UNMAPPED.create_date ,"
							+ "UNMAPPED.Legacy_System,"
							+ "DECODE(CODED.VARIABLE, NULL, 'N', 'C') AS VARIABLE_STATUS ,"
							+ " UNMAPPED.last_changed_usr "
							+ "FROM "
							+ "(SELECT "
							+ "/* + CPU_COSTING */"
							+ " * "
							+ "FROM "
							+ "(SELECT DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
							+ "CASE "
							+ "WHEN LG.creation_dt IS NULL "
							+ "THEN ISG.cpfxp_create_date "
							+ "WHEN ISG.cpfxp_create_date IS NULL "
							+ "THEN LG.creation_dt "
							+ "WHEN LG.creation_dt > ISG.cpfxp_create_date "
							+ "THEN ISG.cpfxp_create_date "
							+ "ELSE LG.creation_dt "
							+ "END create_date , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text, LG.cont_var_tx) Variable_Desc , "
							+ "DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format, LG.CONTRACT_VAR_FORMAT) VAR_FRMT, "
							+ "CASE "
							+ "WHEN LG.CONTRACT_VAR IS NULL "
							+ "THEN 'ISG' "
							+ "WHEN ISG.cpfxp_contvar IS NULL "
							+ "THEN 'LG' "
							+ "ELSE 'LG, ISG' "
							+ "END Legacy_System ,"
							+ " DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_lst_chngd_usr_id, LG.last_updated_userid) last_changed_usr "
							+ " FROM "
							+ "(SELECT e.CONTRACT_VAR , "
							+ "e.cont_var_tx        , "
							+ "e.creation_dt        , "
							+ "e.CONTRACT_VAR_FORMAT, "
							+ "'LG' ,"
							+ "e.last_updated_userid "
							+ " FROM CONTVAR E "
							+ ") LG "
							+ "FULL OUTER JOIN "
							+ "(SELECT c.cpfxp_contvar , "
							+ "c.cpfxp_text          , "
							+ "c.cpfxp_create_date   , "
							+ "c.cpfxp_format        , "
							+ "'ISG' ,"
							+ "c.cpfxp_lst_chngd_usr_id "
							+ "FROM ISG_CPFXP_CONTVAR c "
							+ ") ISG "
							+ "ON LG.CONTRACT_VAR = ISG.cpfxp_contvar "
							+ "ORDER BY 2 DESC, "
							+ "1 "
							+ ") LEGACY "
							+ "INNER JOIN "
							+ "(SELECT DISTINCT VAR_FRMT FROM BX_CNTRCT_VAR_VALDN_MAPG "
							+ ") VALID_FRMT "
							+ "ON LEGACY.VAR_FRMT = VALID_FRMT.VAR_FRMT "
							+ ")UNMAPPED "
							+ "LEFT OUTER JOIN mv_variable_stts CODED "
							+ "ON UNMAPPED.contVar = CODED.VARIABLE "
							+ ") "
							+ "UMAPPED_CODE_STS "
							+ "LEFT OUTER JOIN "
							+ "(SELECT b.CNTRCT_VAR_ID, "
							+ "b.CNTRCT_VAR_DESC    , "
							+ "b.var_mapg_stts_cd   , "
							+ "b.var_mapg_sys_id    , "
							+ "b.LST_CHG_TMS , b.lst_chg_usr "
							+ "FROM bx_cntrct_var_mapg b "
							+ "WHERE b.in_temp_tab = 'N' "

							+ "UNION "

							+ "SELECT t.CNTRCT_VAR_ID, "
							+ "t.CNTRCT_VAR_DESC    , "
							+ "t.var_mapg_stts_cd   , "
							+ "t.var_mapg_sys_id    , "
							+ "t.LST_CHG_TMS  , t.lst_chg_usr "
							+ "FROM temp_bx_cntrct_var_mapg t "
							+ ") BX "
							+ "ON UMAPPED_CODE_STS.contVar = BX.CNTRCT_VAR_ID "
							+ ") results "
							+ "WHERE  results.contVar LIKE '%"
							+ variable.getVariableId().toUpperCase()
							+ "%'"
							+ " AND "
							+ upperCondtnStrt
							+ "results.VARIABLE_DESC "
							+ upperCondtnEnd
							+ "LIKE '%"
							+ variable.getDescription().toUpperCase()
							+ "%' ) "
							+ ConditionalQuery);
			locateQuery = locateUnMapdMapdNotAppQuery.toString();
		}
		locateQuery = "select * from ( "+locateQuery+ " ) order by contVar";
		LocateVariableForReport locateVariable = new LocateVariableForReport(
				dataSource, locateQuery);
		 allVariables = locateVariable.execute();

		
		return allVariables;
	}
	private static class LocateVariableForReport extends MappingSqlQuery {

		private LocateVariableForReport(DataSource dataSource, String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SearchResult searchResult = new SearchResult();
			searchResult.setVariableId(rs.getString("contVar"));
			searchResult.setFormattedDate(BxUtil.getFormattedDateWithOutTime(rs.getDate("create_date")));
			searchResult.setDescription(rs.getString("Variable_Desc"));
			searchResult.setSystem(rs.getString("Legacy_System"));
			searchResult.setStatus(rs.getString("status"));
			return searchResult;
		}
	}
	
	private static final String allVariableQuery = "SELECT "
		+ "/*+ CPU_COSTING */ "
		+ "* "
		+ "FROM   (SELECT  DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_contvar, LG.CONTRACT_VAR) contVar,"
		+ "              CASE"
		+ "                       WHEN LG.creation_dt IS NULL"
		+ "                       THEN ISG.cpfxp_create_date"
		+ "                       WHEN ISG.cpfxp_create_date IS NULL"
		+ "                       THEN LG.creation_dt"
		+ "                       WHEN LG.creation_dt > ISG.cpfxp_create_date"
		+ "                       THEN ISG.cpfxp_create_date"
		+ "                       ELSE LG.creation_dt"
		+ "              END            create_date  ,"
		+ "              DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_text,  LG.cont_var_tx) Variable_Desc,"
		+ "				DECODE(LG.CONTRACT_VAR, NULL, ISG.cpfxp_format,  LG.CONTRACT_VAR_FORMAT) VAR_FRMT,"
		+ "              CASE"
		+ "                       WHEN LG.CONTRACT_VAR IS NULL"
		+ "                       THEN 'ISG'"
		+ "                       WHEN ISG.cpfxp_contvar IS NULL"
		+ "                       THEN 'LG'"
		+ "                       ELSE 'LG, ISG'"
		+ "              END Legacy_System "
		+ "     FROM     (SELECT e.CONTRACT_VAR,"
		+ "                      e.cont_var_tx ,"
		+ "                      e.creation_dt ,"
		+ "						e.CONTRACT_VAR_FORMAT,"
		+ "                      'LG'"
		+ "              FROM    CONTVAR E WHERE e.CONTRACT_VAR_FORMAT IN ('HRS','VST','OCC','DAY','MIN','MTH','NUM','VAL','YRS')"
		+ "              )"
		+ "              LG"
		+ "              FULL OUTER JOIN"
		+ "                       (SELECT c.cpfxp_contvar    ,"
		+ "                               c.cpfxp_text       ,"
		+ "                               c.cpfxp_create_date,"
		+ "								 c.cpfxp_format,"
		+ "                               'ISG'"
		+ "                       FROM    ISG_CPFXP_CONTVAR c where c.cpfxp_format IN ('HRS','VST','OCC','DAY','MIN','MTH','NUM','VAL','YRS')"
		+ "                       )"
		+ "                       ISG"
		+ "              ON       LG.CONTRACT_VAR = ISG.cpfxp_contvar"
		+ "     ORDER BY 2 DESC,"
		+ "              1"
		+ "     )"
		+ "     LEGACY ";

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}