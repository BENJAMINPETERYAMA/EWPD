/*
 * Created on Jun 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LockRepositoryImpl implements LockRepository{

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.repository.LockRepository#insertLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private LockInsertTableQuery lockInsertTableQuery;
	private LockUpdateTableQuery lockUpdateTableQuery;
	private LockRetrieveTableQuery lockRetrieveTableQuery;
	private LockDeleteTableQuery lockDeleteTableQuery;
	
	private DataSource dataSource;
	
	public boolean insertLock(Lock lockObject) {
		StringBuffer lockInsertQuery = new StringBuffer();		
		lockInsertQuery
		.append("INSERT INTO BOLK_BUS_OBJ_LOCK(" +
				" BOLK_BUS_OBJ_TYPE_NM," +
				" BOLK_BUS_OBJ_KEY_ID, BOLK_BUS_OBJ_LOCK_USR_ID," +
				" BOLK_BUS_OBJ_LOCK_DURATION, " +
				" BOLK_BUS_OBJ_LOCK_TMS) VALUES (?, ?, ?, ?, sysdate)");
		lockInsertTableQuery = new LockInsertTableQuery(
				getDataSource(), lockInsertQuery.toString());
		lockInsertTableQuery.insert(lockObject);
		
		return true;
	}

	protected class LockInsertTableQuery extends SqlUpdate {
		protected LockInsertTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.INTEGER));			
			compile();
		}

		protected void insert(Lock lockObject) {
			Object[] objs = new Object[] {lockObject.getBusinessObjectName(),lockObject.getConcatenatedKey(),
					lockObject.getLockUserId(),Integer.valueOf(lockObject.getDuration())};
			super.update(objs);
		}
	}
	
	public boolean updateLock(Lock lockObject) {
		StringBuffer lockUpdateQuery = new StringBuffer();		
			
		lockUpdateQuery
		.append("UPDATE BOLK_BUS_OBJ_LOCK" +
				" SET BOLK_BUS_OBJ_KEY_ID = ?," +
				" BOLK_BUS_OBJ_LOCK_USR_ID = ?," +			
				" BOLK_BUS_OBJ_LOCK_TMS = ?" +
				" WHERE BOLK_BUS_OBJ_TYPE_NM = '"+ lockObject.getBusinessObjectName()+"'" +
				" AND BOLK_BUS_OBJ_LOCK_USR_ID = '"+lockObject.getLockUserId()+"'");
		lockUpdateTableQuery = new LockUpdateTableQuery(getDataSource(), lockUpdateQuery.toString());
		lockUpdateTableQuery.update(lockObject);
						
		return true;
	}
	
	protected class LockUpdateTableQuery extends SqlUpdate {
		protected LockUpdateTableQuery(DataSource ds, String sql) {
			super(ds, sql);
			declareParameter(new SqlParameter("BOLK_BUS_OBJ_KEY_ID",Types.VARCHAR));
			declareParameter(new SqlParameter("BOLK_BUS_OBJ_LOCK_USR_ID",Types.VARCHAR));			
			declareParameter(new SqlParameter("BOLK_BUS_OBJ_LOCK_TMS",Types.DATE));	
			compile();
		}

		protected void update(Lock lockObject) {
			Object[] objs = new Object[] {lockObject.getConcatenatedKey(),
					lockObject.getLockUserId(),lockObject.getLockTimestamp()};
			super.update(objs);
		}
	}
	
	public List retrieveLock(String className, String keyId) {
		StringBuffer lockRetrieveQuery = new StringBuffer();		
		lockRetrieveQuery
		.append(" SELECT BOLK_BUS_OBJ_TYPE_NM, " +
				" BOLK_BUS_OBJ_KEY_ID," +
				" BOLK_BUS_OBJ_LOCK_USR_ID," +
				" BOLK_BUS_OBJ_LOCK_DURATION," +
				" BOLK_BUS_OBJ_LOCK_TMS " +
				" FROM 	BOLK_BUS_OBJ_LOCK " +
				" WHERE BOLK_BUS_OBJ_KEY_ID = '"+ keyId+ "' AND BOLK_BUS_OBJ_TYPE_NM ='"+ className+ "'"); 
		lockRetrieveTableQuery = new LockRetrieveTableQuery (
				getDataSource(), lockRetrieveQuery.toString());
		
		return lockRetrieveTableQuery.execute();
	}
	
	protected class LockRetrieveTableQuery extends MappingSqlQuery {
				
		protected LockRetrieveTableQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Lock lock= new Lock();
			lock.setBusinessObjectName(rs.getString("BOLK_BUS_OBJ_TYPE_NM"));
			lock.setConcatenatedKey(rs.getString("BOLK_BUS_OBJ_KEY_ID"));
			lock.setLockUserId(rs.getString("BOLK_BUS_OBJ_LOCK_USR_ID"));
			lock.setDuration(rs.getInt("BOLK_BUS_OBJ_LOCK_DURATION"));
			lock.setLockTimestamp(rs.getTimestamp("BOLK_BUS_OBJ_LOCK_TMS"));
			return lock;
		}
	}
	
	public List retrieveLock(String className, String keyId, String userId) {
		StringBuffer lockRetrieveForEditQuery = new StringBuffer();		
		lockRetrieveForEditQuery
		.append(" SELECT BOLK_BUS_OBJ_TYPE_NM, " +
				" BOLK_BUS_OBJ_KEY_ID," +
				" BOLK_BUS_OBJ_LOCK_USR_ID," +
				" BOLK_BUS_OBJ_LOCK_DURATION," +
				" BOLK_BUS_OBJ_LOCK_TMS " +
				" FROM 	BOLK_BUS_OBJ_LOCK " +
				" WHERE BOLK_BUS_OBJ_KEY_ID = '"+ keyId+ "' AND BOLK_BUS_OBJ_TYPE_NM = '"+ className+ "' AND BOLK_BUS_OBJ_LOCK_USR_ID='"+userId+"'"); 
		lockRetrieveTableQuery = new LockRetrieveTableQuery (
				getDataSource(), lockRetrieveForEditQuery.toString());
		
		return lockRetrieveTableQuery.execute();
	}
	
	protected class lockRetrieveForEditQuery extends MappingSqlQuery {
				
		protected lockRetrieveForEditQuery(DataSource ds, String sql) {
			super(ds, sql);
		}

		protected Object mapRow(ResultSet rs, int rownum) throws SQLException {
			Lock lock= new Lock();
			lock.setBusinessObjectName(rs.getString("BOLK_BUS_OBJ_TYPE_NM"));
			lock.setConcatenatedKey(rs.getString("BOLK_BUS_OBJ_KEY_ID"));
			lock.setLockUserId(rs.getString("BOLK_BUS_OBJ_LOCK_USR_ID"));
			lock.setDuration(rs.getInt("BOLK_BUS_OBJ_LOCK_DURATION"));
			lock.setLockTimestamp(rs.getTimestamp("BOLK_BUS_OBJ_LOCK_TMS"));
			return lock;
		}
	}
	
	public boolean deleteLock(Mapping mapping) {
		StringBuffer lockDeleteQuery = new StringBuffer();		
			
		lockDeleteQuery
		.append("DELETE FROM BOLK_BUS_OBJ_LOCK" +
				" WHERE	BOLK_BUS_OBJ_TYPE_NM = ?" +
				" AND 	BOLK_BUS_OBJ_KEY_ID = ?");
		lockDeleteTableQuery = new LockDeleteTableQuery(
				getDataSource(), lockDeleteQuery.toString());
		lockDeleteTableQuery.update(mapping);
						
		return true;
	}
	
	protected class LockDeleteTableQuery extends SqlUpdate {
		protected LockDeleteTableQuery(DataSource ds, String sql) {
			super(ds, sql);		
			declareParameter(new SqlParameter("BOLK_BUS_OBJ_TYPE_NM",Types.VARCHAR));
			declareParameter(new SqlParameter("BOLK_BUS_OBJ_KEY_ID",Types.VARCHAR));
			compile();
		}

		protected void update(Mapping mapping) {
			Object[] objs = new Object[] {mapping.getClass().getName(),mapping.getVariable().getVariableId()};
			super.update(objs);
		}
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
}
