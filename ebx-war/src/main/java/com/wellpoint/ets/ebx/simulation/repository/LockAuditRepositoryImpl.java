/*
 * <LockAuditRepositoryImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.domain.entity.LockAuditTrail;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL
 * 
 *         Repository class to fetch lock audit trail data for EBX, LG and
 *         ISG.
 * 
 */
public class LockAuditRepositoryImpl implements LockAuditRepository {

	private DataSource dataSource;

	/**
	 * Query for fetching the Line and Question Mappings
	 * 
	 */

	public class RetrieveEBXLockAuditTrailQuery extends MappingSqlQuery {

		private RetrieveEBXLockAuditTrailQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			LockAuditTrail lockAuditTrail = new LockAuditTrail();
			lockAuditTrail.setVariableId(rs.getString("CNTRCT_VAR_ID"));
			lockAuditTrail.setUserComments(rs.getString("USR_CMNTS"));
			lockAuditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
			String auditTime = BxUtil.getFormattedDate(rs
					.getTimestamp("CREATD_TM_STMP"));
			lockAuditTrail.setCreatedAuditDate(auditTime);

			return lockAuditTrail;
		}
	}

	private List getEbxAuditInfo(String startDate, String endDate) {

		List auditTrailList = null;
		StringBuffer query = new StringBuffer();

		query = query.append("SELECT * FROM ");
		query = query.append("( ");
		query = query
				.append("(SELECT A.CNTRCT_VAR_ID,A.USR_CMNTS,A.CREATD_USER_ID,A.CREATD_TM_STMP ");
		query = query.append("FROM BX_CNTRCT_VAR_AUDIT_TRAIL A ");
		query = query
				.append("JOIN ((select CNTRCT_VAR_ID,AUDIT_LOCK from BX_CNTRCT_VAR_MAPG where in_temp_tab='N') union ");
		query = query
				.append("(select CNTRCT_VAR_ID,AUDIT_LOCK from TEMP_BX_CNTRCT_VAR_MAPG)) varData ");
		query = query
				.append("ON varData.CNTRCT_VAR_ID= A.CNTRCT_VAR_ID WHERE AUDIT_LOCKED='1' ");
		query = query.append("AND varData.AUDIT_LOCK='Y' ");
		if (null != startDate && !"".equals(startDate)) {
			query = query.append(" AND trunc(CREATD_TM_STMP) >= to_date('"
					+ startDate + "','mm/dd/yyyy') ");
		}
		if (null != endDate && !"".equals(endDate)) {
			query = query.append(" AND trunc(CREATD_TM_STMP) <= to_date('"
					+ endDate + "','mm/dd/yyyy') ");
		}
		query = query.append(") ");
		query = query.append("union ");
		query = query.append("( ");
		query = query
				.append("select CNTRCT_VAR_ID,USR_CMNTS,CREATD_USER_ID,CREATD_TM_STMP from ");
		query = query
				.append("BX_CNTRCT_VAR_AUDIT_TRAIL where AUDIT_LOCKED='2' ");
		if (null != startDate && !"".equals(startDate)) {
			query = query.append(" AND trunc(CREATD_TM_STMP) >= to_date('"
					+ startDate + "','mm/dd/yyyy') ");
		}
		if (null != endDate && !"".equals(endDate)) {
			query = query.append(" AND trunc(CREATD_TM_STMP) <= to_date('"
					+ endDate + "','mm/dd/yyyy') ");
		}
		query = query.append(") ");
		query = query.append(" ORDER BY CREATD_TM_STMP DESC)");
		RetrieveEBXLockAuditTrailQuery retrieveAuditTrailQuery = new RetrieveEBXLockAuditTrailQuery(
				dataSource, query.toString());
		auditTrailList = retrieveAuditTrailQuery.execute();
		if (null != auditTrailList && auditTrailList.size() > 0) {
			return auditTrailList;
		}

		return null;
	}


	public class RetrieveLGLockAuditTrailQuery extends MappingSqlQuery {

		private RetrieveLGLockAuditTrailQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			LockAuditTrail lockAuditTrail = new LockAuditTrail();
			lockAuditTrail.setVariableId(rs.getString("CNTRCT_VAR_ID"));
			lockAuditTrail.setContractId(rs.getString("CONTRACT_ID"));
			lockAuditTrail.setDateSegment(BxUtil.getFormattedDateWithOutTime(rs
					.getDate("DATE_SEGMENT")));
			lockAuditTrail.setCurrentValue(rs.getString("CURRENT_VAL"));
			lockAuditTrail.setNewValue(rs.getString("NEW_VAL"));
			lockAuditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
			String auditTime = BxUtil.getFormattedDate(rs
					.getTimestamp("CREATD_TM_STMP"));
			lockAuditTrail.setCreatedAuditDate(auditTime);
			lockAuditTrail.setSystemIndicator(rs.getString("SYSTEM_IND"));
			lockAuditTrail.setSystem("LG");

			return lockAuditTrail;
		}
	}

	private List getLGAuditInfo(String systemIndicator, String startDate,
			String endDate) {

		List auditTrailList = null;
		StringBuffer query = new StringBuffer();

		if (null != systemIndicator) {
			if ((systemIndicator.indexOf("P") != -1)
					|| (systemIndicator.indexOf("T") != -1)) {
				query = query.append("SELECT * FROM ");
				query = query
						.append("(SELECT A.CNTRCT_VAR_ID, CONTRACT_ID, DATE_SEGMENT, CURRENT_VAL, NEW_VAL, ");
				query = query
						.append("CREATD_USER_ID, CREATD_TM_STMP,SYSTEM_IND FROM LG_CNTRCT_VAR_AUDIT_TRIAL A " +
								"JOIN ((select CNTRCT_VAR_ID,AUDIT_LOCK from BX_CNTRCT_VAR_MAPG where in_temp_tab='N') " +
								"union (select CNTRCT_VAR_ID,AUDIT_LOCK from TEMP_BX_CNTRCT_VAR_MAPG)) varData " +
								"ON varData.CNTRCT_VAR_ID= A.CNTRCT_VAR_ID  WHERE ");

				// System indicator check
				query = query.append("(");
				if (systemIndicator.indexOf("T") != -1) {
					query = query.append("SYSTEM_IND = 'T' ");
					if (systemIndicator.indexOf("P") != -1) {
						query = query.append(" OR SYSTEM_IND = 'P' ");
					}
				} else {
					query = query.append(" SYSTEM_IND = 'P' ");
				}
				query = query.append(")");
				// End : System indicator check

				query = query.append(" AND varData.AUDIT_LOCK='Y' ");
					 
				if (null != startDate && !"".equals(startDate)) {
					query = query.append("AND trunc(CREATD_TM_STMP) >= to_date('"
							+ startDate + "','mm/dd/yyyy') ");
				}
				if (null != endDate && !"".equals(endDate)) {
					query = query.append("AND trunc(CREATD_TM_STMP) <= to_date('"
							+ endDate + "','mm/dd/yyyy') ");
				}
				query = query.append("ORDER BY CREATD_TM_STMP DESC )");
				RetrieveLGLockAuditTrailQuery retrieveAuditTrailQuery = new RetrieveLGLockAuditTrailQuery(
						dataSource, query.toString());
				auditTrailList = retrieveAuditTrailQuery.execute();
				if (null != auditTrailList && auditTrailList.size() > 0) {
					return auditTrailList;
				}
			}
		}
		return null;
	}

	public class RetrieveISGLockAuditTrailQuery extends MappingSqlQuery {

		private RetrieveISGLockAuditTrailQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			compile();
		}

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			LockAuditTrail lockAuditTrail = new LockAuditTrail();
			lockAuditTrail.setVariableId(rs.getString("CNTRCT_VAR_ID"));
			lockAuditTrail.setContractId(rs.getString("CONTRACT_ID"));
			lockAuditTrail.setDateSegment(BxUtil.getFormattedDateWithOutTime(rs
					.getDate("DATE_SEGMENT")));
			lockAuditTrail.setCurrentValue(rs.getString("CURRENT_VAL"));
			lockAuditTrail.setNewValue(rs.getString("NEW_VAL"));
			lockAuditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
			String auditTime = BxUtil.getFormattedDate(rs
					.getTimestamp("CREATD_TM_STMP"));
			lockAuditTrail.setCreatedAuditDate(auditTime);
			lockAuditTrail.setSystemIndicator(rs.getString("SYSTEM_IND"));
			lockAuditTrail.setSystem("ISG");
			return lockAuditTrail;
		}
	}

	private List getISGAuditInfo(String systemIndicator, String startDate,
			String endDate) {

		List auditTrailList = null;
		StringBuffer query = new StringBuffer();

		if (null != systemIndicator) {
			if ((systemIndicator.indexOf("P") != -1)
					|| (systemIndicator.indexOf("T") != -1)) {
				query = query.append("SELECT * FROM ");
				query = query
						.append("(SELECT A.CNTRCT_VAR_ID, CONTRACT_ID, DATE_SEGMENT, CURRENT_VAL, NEW_VAL, ");
				query = query
						.append("CREATD_USER_ID, CREATD_TM_STMP,SYSTEM_IND FROM ISG_CNTRCT_VAR_AUDIT_TRIAL A " +
								"JOIN ((select CNTRCT_VAR_ID,AUDIT_LOCK from BX_CNTRCT_VAR_MAPG where in_temp_tab='N') " +
								"union (select CNTRCT_VAR_ID,AUDIT_LOCK from TEMP_BX_CNTRCT_VAR_MAPG)) varData " +
								"ON varData.CNTRCT_VAR_ID= A.CNTRCT_VAR_ID WHERE ");

				// System indicator check
				query = query.append("(");
				if (systemIndicator.indexOf("T") != -1) {
					query = query.append(" SYSTEM_IND = 'T' ");
					if (systemIndicator.indexOf("P") != -1) {
						query = query.append(" OR SYSTEM_IND = 'P' ");
					}
				} else {
					query = query.append(" SYSTEM_IND = 'P' ");
				}
				query = query.append(" ) ");
				// End : System indicator check

				query = query.append(" AND varData.AUDIT_LOCK='Y' ");
				
				if (null != startDate && !"".equals(startDate)) {
					query = query.append(" AND trunc(CREATD_TM_STMP) >= to_date('"
							+ startDate + "','mm/dd/yyyy') ");
				}
				if (null != endDate && !"".equals(endDate)) {
					query = query.append("AND trunc(CREATD_TM_STMP) <= to_date('"
							+ endDate + "','mm/dd/yyyy') ");
				}
				query = query.append("ORDER BY CREATD_TM_STMP DESC )");
				RetrieveISGLockAuditTrailQuery retrieveAuditTrailQuery = new RetrieveISGLockAuditTrailQuery(
						dataSource, query.toString());
				auditTrailList = retrieveAuditTrailQuery.execute();
				if (null != auditTrailList && auditTrailList.size() > 0) {
					return auditTrailList;
				}
			}
		}
		return null;
	}

	public List getEbxLockedVariableAudits(String startDate, String endDate)
			throws EBXException, Exception {
		return getEbxAuditInfo(startDate, endDate);
	}

	public List getLgLockedVariableAudits(String systemIndicator,
			String startDate, String endDate) throws EBXException, Exception {
		return getLGAuditInfo(systemIndicator, startDate, endDate);
	}

	public List getIsgLockedVariableAudits(String systemIndicator,
			String startDate, String endDate) throws EBXException, Exception {
		return getISGAuditInfo(systemIndicator, startDate, endDate);
	}

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

}