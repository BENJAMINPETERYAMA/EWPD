/*
 * <AuditLockRepositoryImpl.java>
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
import java.sql.Types;
import java.util.List;
import org.owasp.esapi.ESAPI;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author UST-GLOBAL
 * 
 * Repository class to update the audit lock and unlock. 
 * 
 */
public class AuditLockRepositoryImpl implements AuditLockRepository {
	
	private static Logger log = Logger.getLogger(AuditLockRepositoryImpl.class);
	
	private UpdateAuditLock updateAuditLock;
	private UpdateTempAuditLock updateTempAuditLock;
	private DataSource dataSource;
	/**
	 *  Update the audit lock based on the audit lock status.
	 * @param mapping
	 * @param auditLockStatus
	 * @return
	 */
	public boolean updateAuditLock(Mapping mapping, String system) {
		
		StringBuffer updateAuditLockQuery = new StringBuffer();
			updateAuditLockQuery.append("UPDATE BX_CNTRCT_VAR_MAPG "+
					"SET AUDIT_LOCK = ?, "+
					"LST_CHG_TMS = SYSDATE, "+
					"LST_CHG_USR = ? " +
					"WHERE CNTRCT_VAR_ID = '"+mapping.getVariable().getVariableId()+"'");
			updateAuditLock = new UpdateAuditLock(getDataSource(), updateAuditLockQuery.toString());
			updateAuditLock.update(mapping);
		log.debug("AuditLock Query = " +ESAPI.encoder().encodeForHTML(updateAuditLockQuery.toString()));
		return true;
	}
	protected class UpdateAuditLock extends SqlUpdate {
		protected UpdateAuditLock(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter("AUDIT_LOCK",Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR",Types.VARCHAR));
			compile();
		}

		protected void update(Mapping mapping) {
			String auditLockStatus = mapping.getVariable().isAuditLock()?"Y":"N";
			Object[] objs = new Object[] {auditLockStatus, mapping.getUser().getLastUpdatedUserName()};
			super.update(objs);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.AuditLockRepository#isAuditLock(java.lang.String)
	 * Checks whether the variable mapping is already locked or not
	 * 
	 */
	public boolean isAuditLock(String variableId) {
		List lockList = null;		
		StringBuffer query = new StringBuffer();		
		query.append(" select CNTRCT_VAR_ID from BX_CNTRCT_VAR_MAPG  ");
		query.append(" where audit_lock='Y' and CNTRCT_VAR_ID='"+variableId+"'"); 
		IsAuditLockQuery isAuditLockQuery = new IsAuditLockQuery (
				getDataSource(), query.toString());
		lockList = isAuditLockQuery.execute();
		if(null != lockList && lockList.size() > 0){
		    log.debug("*****************Size of audit lock is: "+lockList.size());
		    return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.AuditLockRepository#isAuditUnLock(java.lang.String)
	 * Checks whether the variable mapping is already unlocked or not
	 */
	public boolean isAuditUnLock(String variableId) {
		List lockList = null;		
		StringBuffer query = new StringBuffer();		
		query.append(" select CNTRCT_VAR_ID from BX_CNTRCT_VAR_MAPG  ");
		query.append(" where audit_lock='Y' and CNTRCT_VAR_ID='"+variableId+"'"); 
		IsAuditUnLockQuery isAuditUnLockQuery = new IsAuditUnLockQuery (
				getDataSource(), query.toString());
		lockList = isAuditUnLockQuery.execute();
		if(null != lockList && lockList.size() > 0){
		    log.debug("*****************Size of audit lock is: "+lockList.size());
		    return false;
		}
		return true;
	}
	
	protected class IsAuditLockQuery extends MappingSqlQuery {
				
		protected IsAuditLockQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Variable variable= new Variable();
			variable.setVariableId(rs.getString("CNTRCT_VAR_ID"));
			return variable;
		}
	}
	protected class IsAuditUnLockQuery extends MappingSqlQuery {
		
		protected IsAuditUnLockQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Variable variable= new Variable();
			variable.setVariableId(rs.getString("CNTRCT_VAR_ID"));
			return variable;
		}
	}
	
	public boolean isAuditUnLockInTemp(String variableId) {
		List lockList = null;		
		StringBuffer query = new StringBuffer();		
		query.append(" select CNTRCT_VAR_ID from TEMP_BX_CNTRCT_VAR_MAPG  ");
		query.append(" where audit_lock='Y' and CNTRCT_VAR_ID='"+variableId+"'"); 
		IsAuditUnLockQuery isAuditUnLockQuery = new IsAuditUnLockQuery (
				getDataSource(), query.toString());
		lockList = isAuditUnLockQuery.execute();
		if(null != lockList && lockList.size() > 0){
		    log.debug("*****************Size of audit lock is: "+lockList.size());
		    return false;
		}
		return true;
	}
	
	public boolean isAuditLockInTemp(String variableId) {
		List lockList = null;		
		StringBuffer query = new StringBuffer();		
		query.append(" select CNTRCT_VAR_ID from TEMP_BX_CNTRCT_VAR_MAPG  ");
		query.append(" where audit_lock='Y' and CNTRCT_VAR_ID='"+variableId+"'"); 
		IsAuditUnLockQuery isAuditUnLockQuery = new IsAuditUnLockQuery (
				getDataSource(), query.toString());
		lockList = isAuditUnLockQuery.execute();
		if(null != lockList && lockList.size() > 0){
		    log.debug("*****************Size of audit lock is: "+lockList.size());
		    return true;
		}
		return false;
	}
	
	public boolean updateTempAuditLock(Mapping mapping, String system) {
		
		StringBuffer updateTempAuditLockQuery = new StringBuffer();
		updateTempAuditLockQuery.append("UPDATE TEMP_BX_CNTRCT_VAR_MAPG "+
					"SET AUDIT_LOCK = ?, "+
					"LST_CHG_TMS = SYSDATE, "+
					"LST_CHG_USR = ? " +
					"WHERE CNTRCT_VAR_ID = '"+mapping.getVariable().getVariableId()+"'");
		updateTempAuditLock = new UpdateTempAuditLock(getDataSource(), updateTempAuditLockQuery.toString());
		updateTempAuditLock.update(mapping);
		log.debug("AuditLock Query = " +ESAPI.encoder().encodeForHTML(updateTempAuditLockQuery.toString()));
		return true;
	}
	protected class UpdateTempAuditLock extends SqlUpdate {
		protected UpdateTempAuditLock(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter("AUDIT_LOCK",Types.VARCHAR));
			declareParameter(new SqlParameter("LST_CHG_USR",Types.VARCHAR));
			compile();
		}

		protected void update(Mapping mapping) {
			String auditLockStatus = mapping.getVariable().isAuditLock()?"Y":"N";
			Object[] objs = new Object[] {auditLockStatus, mapping.getUser().getLastUpdatedUserName()};
			super.update(objs);
		}
	}
	
	/**
	 * method will return all the variables with given lock status.
	 * @param variableIds
	 * @param locked
	 * @return
	 */
	public List getAuditLockStatusOfVariable(List variableIds, boolean locked, boolean checktemptable) {
		String commasepVarList = BxUtil.listToStringForQuery(variableIds, ",");
		List variableIdList = null;
		StringBuffer query = null;
		if(checktemptable) {
			query = new StringBuffer();
			query.append(" SELECT * " );
			query.append(" FROM ");
			query.append("   (SELECT var_mapg.CNTRCT_VAR_ID,");
			query.append("     DECODE(var_mapg.IN_TEMP_TAB, 'Y', temp_var_mapg.AUDIT_LOCK, var_mapg.AUDIT_LOCK) AS lock_status");
			query.append("   FROM bx_cntrct_var_mapg var_mapg left join ");
			query.append("     temp_bx_cntrct_var_mapg temp_var_mapg ");
			query.append("   on var_mapg.CNTRCT_VAR_ID = temp_var_mapg.CNTRCT_VAR_ID ");
			query.append("   where var_mapg.CNTRCT_VAR_ID   IN( ");
			query.append(commasepVarList);
			query.append("  ) ) ");
			if(locked){
				query.append("WHERE lock_status   ='Y'");
			} else {
				query.append("WHERE lock_status   ='N'");
			}
			
		} else {
			query = new StringBuffer();
			query.append("SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG ");
			if(locked){
				query.append("WHERE audit_lock   ='Y'");
			} else {
				query.append("WHERE audit_lock   ='N'");
			}
			query.append(" AND CNTRCT_VAR_ID IN (");
			query.append(commasepVarList);
			query.append(" )");
		}
		
		AuditLockStatusQuery uditLockStatusQuery = new AuditLockStatusQuery (
				getDataSource(), query.toString());
		variableIdList = uditLockStatusQuery.execute();
		return variableIdList;
		
	}
	
	/**
	 * 
	 * @param variableId
	 * @param locked
	 * @param checktemptable
	 * @return
	 */
	public List getAuditLockStatusOfVariable(String variableId, boolean locked, boolean checktemptable) {
		//String commasepVarList = BxUtil.listToStringForQuery(variableIds, ",");
		List variableIdList = null;
		StringBuffer query = null;
		if(checktemptable) {
			query = new StringBuffer();
			query.append(" SELECT * " );
			query.append(" FROM ");
			query.append("   (SELECT var_mapg.CNTRCT_VAR_ID,");
			query.append("     DECODE(var_mapg.IN_TEMP_TAB, 'Y', temp_var_mapg.AUDIT_LOCK, var_mapg.AUDIT_LOCK) AS lock_status");
			query.append("   FROM bx_cntrct_var_mapg var_mapg left join ");
			query.append("     temp_bx_cntrct_var_mapg temp_var_mapg ");
			query.append("   on var_mapg.CNTRCT_VAR_ID = temp_var_mapg.CNTRCT_VAR_ID ");
			query.append("   where var_mapg.CNTRCT_VAR_ID   IN('");
			query.append(variableId);
			query.append("'  ) ) ");
			if(locked){
				query.append("WHERE lock_status   ='Y'");
			} else {
				query.append("WHERE lock_status   ='N'");
			}
			
		} else {
			query = new StringBuffer();
			query.append("SELECT CNTRCT_VAR_ID FROM BX_CNTRCT_VAR_MAPG ");
			if(locked){
				query.append("WHERE audit_lock   ='Y'");
			} else {
				query.append("WHERE audit_lock   ='N'");
			}
			query.append(" AND CNTRCT_VAR_ID IN ('");
			query.append(variableId);
			query.append("' )");
		}
		
		AuditLockStatusQuery uditLockStatusQuery = new AuditLockStatusQuery (
				getDataSource(), query.toString());
		variableIdList = uditLockStatusQuery.execute();
		return variableIdList;
		
	}

	protected class AuditLockStatusQuery extends MappingSqlQuery {
		
		protected AuditLockStatusQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			return (rs.getString("CNTRCT_VAR_ID"));
			
		}
	}

}