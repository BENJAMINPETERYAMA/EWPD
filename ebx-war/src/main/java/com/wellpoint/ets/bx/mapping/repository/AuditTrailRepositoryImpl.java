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
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuditTrailRepositoryImpl implements AuditTrailRepository {
	
	private static Logger log = Logger.getLogger(AuditTrailRepositoryImpl.class);
	private DataSource dataSource;
	
	 private class RetrieveAuditTrailQuery extends MappingSqlQuery {

		private RetrieveAuditTrailQuery(DataSource dataSource,String query) {
	        super(dataSource, query);
		        compile();
	    }		
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditTrail auditTrail = new AuditTrail();
	    	String auditTime = BxUtil.getFormattedDate(rs.getTimestamp("CREATD_TM_STMP"));
	    	auditTrail.setCreatedAuditDate(auditTime);
	    	auditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
	    	auditTrail.setSystemComments(rs.getString("SYS_CMNTS"));	    	
	    	auditTrail.setUserComments(rs.getString("USR_CMNTS"));	    	
	    	auditTrail.setVariableId(rs.getString("CNTRCT_VAR_ID"));
	    	auditTrail.setVariableDesc(rs.getString("CNTRCT_VAR_DESC"));
	    	auditTrail.setMappingStatus(rs.getString("MAPG_STATUS"));
		  	return auditTrail;
		}		
    }
	private List getAuditInfo(String variableId, Integer noOfAuditInfo){
		
		List auditTrailList = null;		
		StringBuffer query = new StringBuffer();
		query = query.append("SELECT * FROM ");
		query = query.append("(SELECT CREATD_TM_STMP, CREATD_USER_ID, SYS_CMNTS, USR_CMNTS, CNTRCT_VAR_ID, CNTRCT_VAR_DESC, MAPG_STATUS FROM ");
		query = query.append("BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID = '"
			+ variableId + "'" );	
		query = query.append("ORDER BY CREATD_TM_STMP DESC)");
		if((null != noOfAuditInfo) && noOfAuditInfo.equals(Integer.valueOf(21))){
			query = query.append("WHERE ROWNUM < "
						+ noOfAuditInfo + " ");
		}	
		
		RetrieveAuditTrailQuery retrieveAuditTrailQuery = new RetrieveAuditTrailQuery(dataSource,query.toString());
		auditTrailList = retrieveAuditTrailQuery.execute();
		if(null != auditTrailList && auditTrailList.size() > 0){
		    log.debug("*****************Size of audit trail: "+auditTrailList.size());
		    setSystemComments(auditTrailList);
		    return auditTrailList;
		}
		
        return null;
	}
	/*
	 * Sets system comment as mapping status if system comment is null.
	 */
	private List setSystemComments(List auditTrailList){
		
		for(int i=0;i<auditTrailList.size();i++){
			
			AuditTrail audit = (AuditTrail) auditTrailList.get(i);
			
			if(null == audit.getSystemComments()){
				
				audit.setSystemComments(audit.getMappingStatus());
			}
		}
		
		return auditTrailList;
	}
	public List retrieveAuditTrail(String variableId, Integer noOfAuditInfo){
		
		return getAuditInfo(variableId, noOfAuditInfo);
	}
	public List retrieveAllAuditTrail(String variableId){
		
		return getAuditInfo(variableId, null);
	}
	 private class PersistAuditTrailQuery extends SqlUpdate {

		private PersistAuditTrailQuery(DataSource dataSource) {
	        super(dataSource, 
	        		"Insert into BX_CNTRCT_VAR_AUDIT_TRAIL (CNTRCT_VAR_ID,SYS_CMNTS,USR_CMNTS,"
					+ "CREATD_USER_ID,CREATD_TM_STMP,CNTRCT_VAR_DESC,MAPG_STATUS,AUDIT_LOCKED)"
					+ "values (?,?,?,?,systimestamp,?,?,?)");
	        declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.NUMERIC));
		    compile();
	    }	
		
		protected void insert(AuditTrail auditTrail) {
		
			
		
			Object[] objs = new Object[] { 
				
					auditTrail.getVariableId(),
					auditTrail.getSystemComments(),
					auditTrail.getUserComments(),
					auditTrail.getCreatedUser(),
					auditTrail.getVariableDesc(),
					auditTrail.getMappingStatus(),
					auditTrail.getVariableAuditStatus()};
			super.update(objs);
		}
		
    }
	 
	 private class PersistBatchAuditTrailQuery extends SqlUpdate {

		 	private SimpleJdbcTemplate simpleJdbcTemplate;

		 	private final String sql = "Insert into BX_CNTRCT_VAR_AUDIT_TRAIL (CNTRCT_VAR_ID,SYS_CMNTS,USR_CMNTS,"
				+ "CREATD_USER_ID,CREATD_TM_STMP,CNTRCT_VAR_DESC,MAPG_STATUS,AUDIT_LOCKED)"
				+ "values (?,?,?,?,systimestamp,?,?,?)";
		 	
		    public void setDataSource(DataSource dataSource) {
		        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		    }
		    
		    int count = 0;
		 
			protected void batchInsert(int inClauseSize, int batchSize,List<AuditTrail> auditTrailList) {
			
				List<Object[]> batch = new ArrayList<Object[]>();
		        for (AuditTrail auditTrail : auditTrailList) {
		            Object[] values = new Object[] {
		            		auditTrail.getVariableId(),
							auditTrail.getSystemComments(),
							auditTrail.getUserComments(),
							auditTrail.getCreatedUser(),
							auditTrail.getVariableDesc(),
							auditTrail.getMappingStatus(),
							auditTrail.getVariableAuditStatus()};
		            batch.add(values);
		            if(++count % batchSize == 0) {
		            	int[] insertCounts = simpleJdbcTemplate.batchUpdate(sql, batch);
		            	log.debug("PersistBatchAuditTrailQuery = "+insertCounts.length);
		            	batch = new ArrayList<Object[]>();
		            }
		        }
		        simpleJdbcTemplate.batchUpdate(sql, batch);
			}
			
	    }
	 
	public boolean insertAuditTrail(AuditTrail auditTrail){
		
		PersistAuditTrailQuery persistAuditTrailQuery = new PersistAuditTrailQuery(dataSource);
		persistAuditTrailQuery.insert(auditTrail);
		return true; 
	}
	
	public boolean batchInsertAuditTrail(int inClauseSize, int batchSize,List<AuditTrail> auditTrail){
		
		PersistBatchAuditTrailQuery persistBatchAuditTrailQuery = new PersistBatchAuditTrailQuery();
		persistBatchAuditTrailQuery.setDataSource(dataSource);
		persistBatchAuditTrailQuery.batchInsert(inClauseSize, batchSize,auditTrail);
		return true;
	}
	
	 private class RetrieveAuditTrailByStatus extends MappingSqlQuery {

		private RetrieveAuditTrailByStatus(DataSource dataSource,String query) {
	        super(dataSource, query);
		        compile();
	    }		
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	AuditTrail auditTrail = new AuditTrail();		    	
	    	auditTrail.setUserComments(rs.getString("USR_CMNTS"));
	    	auditTrail.setSystemComments(rs.getString("SYS_CMNTS"));
	    	auditTrail.setVariableId(rs.getString("CNTRCT_VAR_ID"));	    	
	    	auditTrail.setMappingStatus(rs.getString("MAPG_STATUS"));
	    	auditTrail.setCreatedUser(rs.getString("CREATD_USER_ID"));
	    	auditTrail.setCreatedAuditDate(BxUtil.getFormattedDate(rs.getTimestamp("CREATD_TM_STMP")));
		  	return auditTrail;
		}		
    }
	public AuditTrail retrieveLatestAuditInfo(String auditStatus, String varaiableId){
		
		AuditTrail auditTrail = null;	
		StringBuffer query = new StringBuffer();
		
		query = query.append("SELECT USR_CMNTS, SYS_CMNTS, CNTRCT_VAR_ID, MAPG_STATUS, CREATD_USER_ID, CREATD_TM_STMP FROM ");
		query = query.append("BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID = '"
			+ varaiableId + "'" );
		query = query.append("AND MAPG_STATUS = '" + auditStatus + "'");
		query = query.append("ORDER BY CREATD_TM_STMP DESC");
		
		RetrieveAuditTrailByStatus retrieveLatestAuditTrailQuery = new RetrieveAuditTrailByStatus(dataSource,query.toString());
		List auditTrails = retrieveLatestAuditTrailQuery.execute();
		if(auditTrails.size() > 0){
			auditTrail = (AuditTrail)auditTrails.get(0);
		}
		return auditTrail;
	}
	
	public List retrieveAuditInfo(String variableId, String auditStatus){
		
		StringBuffer query = new StringBuffer();
		query = query.append("SELECT USR_CMNTS, SYS_CMNTS, CNTRCT_VAR_ID, MAPG_STATUS, CREATD_USER_ID, CREATD_TM_STMP FROM ");
		query = query.append("BX_CNTRCT_VAR_AUDIT_TRAIL WHERE CNTRCT_VAR_ID = '"
			+ variableId + "'" );
		query = query.append("AND MAPG_STATUS = '" + auditStatus + "'");
		query = query.append("ORDER BY CREATD_TM_STMP");
		
		RetrieveAuditTrailByStatus auditTrailByStatus = new RetrieveAuditTrailByStatus(dataSource,query.toString());
		return auditTrailByStatus.execute();
	}

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

	public boolean updateEBXAuditTrail(String variableId) {
		StringBuffer auditTrailDeleteQuery = new StringBuffer();		
			
		auditTrailDeleteQuery.append("UPDATE BX_CNTRCT_VAR_AUDIT_TRAIL SET AUDIT_LOCKED='0' " +
				" WHERE	CNTRCT_VAR_ID = ?");
		AuditTrailDeleteQuery lgAuditTrailDeleteQuery = new AuditTrailDeleteQuery(
				getDataSource(), auditTrailDeleteQuery.toString());
		lgAuditTrailDeleteQuery.delete(variableId);
						
		return true;
	}
	
	public boolean removeExistingLGAuditTrail(String variableId) {
		StringBuffer auditTrailDeleteQuery = new StringBuffer();		
			
		auditTrailDeleteQuery.append("DELETE FROM LG_CNTRCT_VAR_AUDIT_TRIAL " +
				" WHERE	CNTRCT_VAR_ID = ?");
		AuditTrailDeleteQuery lgAuditTrailDeleteQuery = new AuditTrailDeleteQuery(
				getDataSource(), auditTrailDeleteQuery.toString());
		lgAuditTrailDeleteQuery.delete(variableId);
						
		return true;
	}
	
	public boolean removeExistingISGAuditTrail(String variableId) {
		StringBuffer auditTrailDeleteQuery = new StringBuffer();		
			
		auditTrailDeleteQuery.append("DELETE FROM ISG_CNTRCT_VAR_AUDIT_TRIAL " +
				" WHERE	CNTRCT_VAR_ID = ?");
		AuditTrailDeleteQuery lgAuditTrailDeleteQuery = new AuditTrailDeleteQuery(
				getDataSource(), auditTrailDeleteQuery.toString());
		lgAuditTrailDeleteQuery.delete(variableId);
						
		return true;
	}
	
	protected class AuditTrailDeleteQuery extends SqlUpdate {
		protected AuditTrailDeleteQuery(DataSource ds, String sql) {
			super(ds, sql);		
			declareParameter(new SqlParameter("CNTRCT_VAR_ID",Types.VARCHAR));
			compile();
		}

		protected void delete(String variableId) {
			Object[] objs = new Object[] {variableId};
			super.update(objs);
		}
	}
}
