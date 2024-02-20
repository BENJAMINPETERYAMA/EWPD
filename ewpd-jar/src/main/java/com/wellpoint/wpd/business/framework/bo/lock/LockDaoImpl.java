/*
 * LockDaoImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.lock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.wellpoint.wpd.business.framework.bo.LockFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id$
 */
public class LockDaoImpl extends JdbcDaoSupport implements LockDao {

    private String insertSQL;

    private String updateSQL;
    
    private String updateKeySQL;

    private String deleteSQL;

    private String retrieveSQL;

    private InsertLock insert;

    private UpdateLock update;
    
    private UpdateLockKey updateKey;    

    private DeleteLock delete;

    private RetrieveLock retrieve;
    
    public LockDaoImpl() {
        super();
    }

    protected void initDao() throws Exception {
        super.initDao();
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.lock.LockDao#insertLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public void insertLock(Lock lockObject) throws SQLException {
        insert = new InsertLock(getDataSource());
        TransactionStatus txStatus = null;
        DataSourceTransactionManager txManager = null ;
        if (lockObject != null) {
        	try{
        		txManager = new DataSourceTransactionManager();
            	txManager.setDataSource(getDataSource());
            	txStatus = beginTransaction(txManager);
        		insert.update(new Object[] { lockObject.getBusinessObjectName(),
                    lockObject.getConcatenatedKey(),
                    lockObject.getLockUserId(),
                    new Integer(lockObject.getDuration()),
                    lockObject.getLockTimestamp() });
        	}catch(Exception e){
        		if(txStatus !=null ){
            		txManager.rollback(txStatus);
            		txStatus = null;
            	}
        		if(e instanceof SQLException )
        			throw (SQLException)e;
        		else
        			throw new RuntimeException(e);
        				 
        	}finally{
        		if(txStatus!=null){
            		txManager.commit(txStatus);
            	}
        	}
        }
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.lock.LockDao#updateLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public void updateLock(Lock lockObject) throws SQLException {
        update = new UpdateLock(getDataSource());
        TransactionStatus txStatus = null;
        DataSourceTransactionManager txManager = null ;
        if (lockObject != null) {
        	try{
        		txManager = new DataSourceTransactionManager();
            	txManager.setDataSource(getDataSource());
            	txStatus = beginTransaction(txManager);
            	update.update(new Object[] { lockObject.getLockUserId(),
                    new Integer(lockObject.getDuration()),
                    lockObject.getLockTimestamp(),
                    lockObject.getBusinessObjectName(),
                    lockObject.getConcatenatedKey() });
          	}catch(Exception e){
        		if(txStatus !=null ){
            		txManager.rollback(txStatus);
            		txStatus = null;
            	}
        		if(e instanceof SQLException )
        			throw (SQLException)e;
        		else
        			throw new RuntimeException(e);
        				 
        	}finally{
        		if(txStatus!=null){
            		txManager.commit(txStatus);
            	}
        	}
        }

    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.lock.LockDao#updateLock(com.wellpoint.wpd.business.framework.bo.lock.Lock, com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param oldLockObject
     * @param newLockObject
     * @throws SQLException
     */
    public void updateLock(Lock oldLockObject, Lock newLockObject) throws SQLException {
        updateKey = new UpdateLockKey(getDataSource());
        TransactionStatus txStatus = null;
        DataSourceTransactionManager txManager = null ;
        if(oldLockObject != null && newLockObject != null){
        	try{
        		txManager = new DataSourceTransactionManager();
            	txManager.setDataSource(getDataSource());
            	txStatus = beginTransaction(txManager);
            	updateKey.update(new Object[]{newLockObject.getConcatenatedKey(),
                    	oldLockObject.getLockUserId(),
                    	new Integer(oldLockObject.getDuration()),
                        oldLockObject.getLockTimestamp(),
                        oldLockObject.getBusinessObjectName(),
                        oldLockObject.getConcatenatedKey() });
          	}catch(Exception e){
        		if(txStatus !=null ){
            		txManager.rollback(txStatus);
            		txStatus = null;
            	}
        		if(e instanceof SQLException )
        			throw (SQLException)e;
        		else
        			throw new RuntimeException(e);
        				 
        	}finally{
        		if(txStatus!=null){
            		txManager.commit(txStatus);
            	}
        	}
        }
    }
    
    /**
     * @see com.wellpoint.wpd.business.framework.bo.lock.LockDao#deleteLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public void deleteLock(Lock lockKeyObject) throws SQLException {
        delete = new DeleteLock(getDataSource());
        TransactionStatus txStatus = null;
        DataSourceTransactionManager txManager = null;
        if (lockKeyObject != null) {
        	try{
        		txManager = new DataSourceTransactionManager();
            	txManager.setDataSource(getDataSource());
            	txStatus = beginTransaction(txManager);
            	delete.update(new Object[] { lockKeyObject.getBusinessObjectName(),
                    lockKeyObject.getConcatenatedKey() });
        	}catch(Exception e){
        		if(txStatus !=null ){
        			txManager.rollback(txStatus);
        			txStatus = null;
        		}
        		if(e instanceof SQLException )
        			throw (SQLException)e;
        		else
        			throw new RuntimeException(e);
    				 
        	}finally{
        		if(txStatus!=null){
        			txManager.commit(txStatus);
        		}
        	}
        }
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.lock.LockDao#retrieveLock(com.wellpoint.wpd.business.framework.bo.lock.Lock)
     * @param lockObject
     * @throws Exception
     */
    public Lock retrieveLock(Lock lockKeyObject) throws SQLException {
        retrieve = new RetrieveLock(getDataSource());
        List lockEntries = null;
        if (lockKeyObject != null) {
            lockEntries = retrieve.execute(new Object[] {
                    lockKeyObject.getBusinessObjectName(),
                    lockKeyObject.getConcatenatedKey() });
        }
        if (lockEntries != null && lockEntries.size() > 0) {
            return (Lock) lockEntries.get(0);
        }
        return null;
    }
    private TransactionStatus beginTransaction(DataSourceTransactionManager txManager){
		DefaultTransactionDefinition td = new DefaultTransactionDefinition(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		td.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
		TransactionStatus txStatus = txManager.getTransaction(td);
		return txStatus;
    }
    public static void main(String args[]) {
        LockDao lockDao = new LockDaoImpl();
        Lock lockObject = new Lock("BO1", "1~", "user2", 0, new Timestamp(0));
        try {
            LockFactory lockFactory = (LockFactory)ObjectFactory.getFactory(ObjectFactory.LOCK);
            Lock lock = 
                lockFactory.retrieveLock(lockObject);
            Logger.logInfo(lock);
        } catch (SQLException e) {
        	Logger.logError(e);
        }
    }

    class InsertLock extends SqlUpdate {
        public InsertLock(DataSource dataSource) {
            super(dataSource, insertSQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.TIMESTAMP));
        }
    }

    class UpdateLock extends SqlUpdate {
        public UpdateLock(DataSource dataSource) {
            super(dataSource, updateSQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.TIMESTAMP));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
        }
    }
    
    class UpdateLockKey extends SqlUpdate {
        public UpdateLockKey(DataSource dataSource) {
            super(dataSource, updateKeySQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.TIMESTAMP));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
        }
    }    

    class DeleteLock extends SqlUpdate {
        public DeleteLock(DataSource dataSource) {
            super(dataSource, deleteSQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
        }
    }

    class RetrieveLock extends MappingSqlQuery {

        public RetrieveLock(DataSource dataSource) {
            super(dataSource, retrieveSQL);
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
        }

        /**
         * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
         *      int)
         * @param rs -
         *            ResultSet object
         * @param rowNum -
         *            the row number to process
         * @return
         * @throws SQLException
         */
        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Lock lockObject = new Lock();
            lockObject.setBusinessObjectName(rs.getString(1));
            lockObject.setConcatenatedKey(rs.getString(2));
            lockObject.setLockUserId(rs.getString(3));
            lockObject.setDuration(rs.getInt(4));
            lockObject.setLockTimestamp(rs.getTimestamp(5));
            return lockObject;
        }

    }

    /**
     * @return Returns the delete.
     */
    public DeleteLock getDelete() {
        return delete;
    }
    /**
     * @return Returns the deleteSQL.
     */
    public String getDeleteSQL() {
        return deleteSQL;
    }
    /**
     * @return Returns the insert.
     */
    public InsertLock getInsert() {
        return insert;
    }
    /**
     * @return Returns the insertSQL.
     */
    public String getInsertSQL() {
        return insertSQL;
    }
    /**
     * @return Returns the retrieve.
     */
    public RetrieveLock getRetrieve() {
        return retrieve;
    }
    /**
     * @return Returns the retrieveSQL.
     */
    public String getRetrieveSQL() {
        return retrieveSQL;
    }
    /**
     * @return Returns the update.
     */
    public UpdateLock getUpdate() {
        return update;
    }
    /**
     * @return Returns the updateSQL.
     */
    public String getUpdateSQL() {
        return updateSQL;
    }
    /**
     * @param delete The delete to set.
     */
    public void setDelete(DeleteLock delete) {
        this.delete = delete;
    }
    /**
     * @param deleteSQL The deleteSQL to set.
     */
    public void setDeleteSQL(String deleteSQL) {
        this.deleteSQL = deleteSQL;
    }
    /**
     * @param insert The insert to set.
     */
    public void setInsert(InsertLock insert) {
        this.insert = insert;
    }
    /**
     * @param insertSQL The insertSQL to set.
     */
    public void setInsertSQL(String insertSQL) {
        this.insertSQL = insertSQL;
    }
    /**
     * @param retrieve The retrieve to set.
     */
    public void setRetrieve(RetrieveLock retrieve) {
        this.retrieve = retrieve;
    }
    /**
     * @param retrieveSQL The retrieveSQL to set.
     */
    public void setRetrieveSQL(String retrieveSQL) {
        this.retrieveSQL = retrieveSQL;
    }
    /**
     * @param update The update to set.
     */
    public void setUpdate(UpdateLock update) {
        this.update = update;
    }
    /**
     * @param updateSQL The updateSQL to set.
     */
    public void setUpdateSQL(String updateSQL) {
        this.updateSQL = updateSQL;
    }
    public UpdateLockKey getUpdateKey() {
        return updateKey;
    }
    public String getUpdateKeySQL() {
        return updateKeySQL;
    }
    public void setUpdateKey(UpdateLockKey updateKey) {
        this.updateKey = updateKey;
    }
    public void setUpdateKeySQL(String updateKeySQL) {
        this.updateKeySQL = updateKeySQL;
    }
}