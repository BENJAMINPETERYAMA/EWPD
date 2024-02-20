	/*
 * LoggerDaoImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Object to insert a LogEntry to the database. The database table definition is
 * <p />
 * COLUMN_NAME TYPE_NAME <br />
 * APXL_LOG_ID NUMBER <br>
 * APXL_SRVR_NM VARCHAR2 <br>
 * APXL_MSG VARCHAR2 <br>
 * APXL_PARM CLOB <br>
 * APXL_STK CLOB <br>
 * APXL_LST_CHG_USRID VARCHAR2 <br>
 * APXL_LST_TMS DATE <br>
 * <p>
 * The log id is retrieved from an Oracle sequence - APXL_LOG_ID_SEQ using
 * OracleSequenceMaxValueIncrementer. This id is set in SevereException and for
 * end user display
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id$
 */
public class LoggerDaoImpl extends JdbcDaoSupport implements LoggerDao {

    private static final String UPDATE_LOG_SQL = "INSERT INTO apxl_appl_xcptn_log (APXL_LOG_ID,APXL_SRVR_NM,APXL_MSG_TXT,APXL_PARM,APXL_STK,APXL_LST_CHG_USR_ID,APXL_LST_TMS) VALUES(?,?,?,?,?,?,?)";

    private AbstractDataFieldMaxValueIncrementer sequenceIncrementer;
    //private DataSourceTransactionManager txManager ;

    /**
     * @param sequenceIncrementer
     *            The sequenceIncrementer to set.
     */
    public void setSequenceIncrementer(
            AbstractDataFieldMaxValueIncrementer sequenceIncrementer) {
        this.sequenceIncrementer = sequenceIncrementer;
    }

    private InsertLog insert;

    /**
     *  
     */
    public LoggerDaoImpl() {
        super();
    }

    protected void initDao() throws Exception {
        super.initDao();
        insert = new InsertLog(getDataSource());
    }

    public void insertLog(LogEntry entry) throws Exception {
        //retrieve unique id from sequence
        //call insert.update - parameters Object array of data.
        //set to LogEntry object.
    	TransactionStatus txStatus = null;
    	DataSourceTransactionManager txManager = null ;
        if (entry != null) {
            try{
            	txManager = new DataSourceTransactionManager();
            	txManager.setDataSource(getDataSource());
            	txStatus = beginTransaction(txManager);
            	setLogId(entry);
            	insert.update(new Object[] { new Integer(entry.getId()),
                    entry.getServerName(), entry.getMessage(),
                    new SqlLobValue(entry.getParameters()),
                    new SqlLobValue(entry.getStackTrace()),
                    entry.getLoggingEntity(),entry.getUpdateTimestamp() });
            }catch(Exception e){
            	if(txStatus !=null ){
            		txManager.rollback(txStatus);
            		txStatus = null;
            	}
            	throw e;
            }finally{
            	if(txStatus!=null){
            		txManager.commit(txStatus);
            	}
            }
        }
    }

    public void test() {
    	DefaultLobHandler olh = new DefaultLobHandler();
        LobCreator lc = olh.getLobCreator();

    }

    /**
     * Retrieve log id from an Oracle sequence and sets it to the LogEntry. 
     * @param entry : LogEntry object that needs an unique id
     * @throws Exception Any database exception that may be thrown. 
     */
    protected void setLogId(LogEntry entry) throws Exception {
        entry.setId(sequenceIncrementer.nextIntValue());
    }

    private TransactionStatus beginTransaction(DataSourceTransactionManager txManager){
		DefaultTransactionDefinition td = new DefaultTransactionDefinition(
				TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		td.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT);
		TransactionStatus txStatus = txManager.getTransaction(td);
		return txStatus;
    }
	
	
    class InsertLog extends SqlUpdate {
        public InsertLog(DataSource dataSource) {
            super(dataSource, UPDATE_LOG_SQL);
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.CLOB));
            declareParameter(new SqlParameter(Types.CLOB));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.TIMESTAMP));
        }
    }
    
  

}