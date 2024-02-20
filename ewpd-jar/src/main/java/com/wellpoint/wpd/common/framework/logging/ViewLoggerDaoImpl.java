/*
 * ViewLoggerDaoImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ViewLoggerDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class ViewLoggerDaoImpl extends JdbcDaoSupport implements ViewLoggerDao {

    private String detailsSql;

    private String summarySql;

    private String allSummarySql;

    private String deleteEntrySql;
    
    private String deleteMultipleEntriesSql;

    /**
     * @param deleteEntrySql The deleteEntrySql to set.
     */
    public void setDeleteEntrySql(String deleteEntrySql) {
        this.deleteEntrySql = deleteEntrySql;
    }

    /**
     * @param detailsSql The detailsSql to set.
     */
    public void setDetailsSql(String detailsSql) {
        this.detailsSql = detailsSql;
    }

    /**
     * @param summarySql The summarySql to set.
     */
    public void setSummarySql(String summarySql) {
        this.summarySql = summarySql;
    }

    /**
     * @param allSummarySql The allSummarySql to set.
     */
    public void setAllSummarySql(String allSummarySql) {
        this.allSummarySql = allSummarySql;
    }

    /**
     * @param deleteMultipleEntriesSql The deleteMultipleEntriesSql to set.
     */
    public void setDeleteMultipleEntriesSql(String deleteMultipleEntriesSql) {
        this.deleteMultipleEntriesSql = deleteMultipleEntriesSql;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.logging.ViewLoggerDao#retrieveLogEntry(int)
     * @param id
     * @return
     */
    public LogEntry retrieveLogEntry(int id) {
        List logEntries = new LogEntryMapper(getDataSource(), detailsSql)
                .execute(new Object[] { new Integer(id) });
        if (logEntries != null && logEntries.size() > 0) {
            return (LogEntry) logEntries.get(0);
        }
        return null;
    }

    /**
     * Delete a log entry from the database.
     * @param id - id of the log entry to be deleted.
     */
    public void deleteLogEntry(int id) {
        new LogEntryDeleter(getDataSource(), deleteEntrySql).update(id);
    }
    
    public void deleteLogEntries(List ids){
           if(ids != null && ids.size() > 0){
               MultipleLogEntryDeleter mled = new MultipleLogEntryDeleter(getDataSource(),deleteMultipleEntriesSql);
               for(int i=0;i<ids.size();i++){
	               mled.update((((Integer)ids.get(i)).intValue()));
               }
               mled.flush();
           }
     
    }

    /**
     * 
     * @see com.wellpoint.wpd.common.framework.logging.ViewLoggerDao#retrieveLogEntrySummary(int)
     * @param noOfRecords
     * @return
     */
    public List retrieveLogEntrySummary(int noOfRecords) {
        Integer input = null;
        if (noOfRecords != 0) {
            input = new Integer(noOfRecords);
        }
        return new LogEntrySummaryMapper(getDataSource(), summarySql)
                .execute(new Object[] { input });
    }

    /**
     * Implementation of MappingSqlQuery to retrieve log details. Maps one
     * record retrieved to LogEntry object. Inner class in ViewLoggerDaoImpl
     * 
     * @author US Technology Resources - www.ustri.com <br />
     * @version $Id: ViewLoggerDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
     */
    class LogEntryMapper extends MappingSqlQuery {

    	DefaultLobHandler olh;

        /**
         * @param dataSource
         * @param sql
         */
        public LogEntryMapper(DataSource dataSource, String sql) {
            super(dataSource, sql);
            olh = new DefaultLobHandler();
            declareParameter(new SqlParameter(Types.INTEGER));
        }

        /**
         * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
         *      int)
         * @param rs
         * @param rowNum
         * @return
         * @throws java.sql.SQLException
         */
        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            LogEntry le = new LogEntry();

            le.setId(rs.getInt(1));
            le.setServerName(rs.getString(2));
            le.setMessage(rs.getString(3));
            le.setParameters(olh.getClobAsString(rs, 4));
            le.setStackTrace(olh.getClobAsString(rs, 5));
            le.setLoggingEntity(rs.getString(6));
            Timestamp ts = rs.getTimestamp(7);
            le.setUpdateTimestamp(ts != null ? new Date(ts.getTime()) : null);

            return le;
        }

    }

    /**
     * Implementation of MappingSqlQuery to retrieve summary of log entries in
     * the database. Maps each record retrieve to a LogEntry object. parameters
     * and stackTrace attributes are not populated. Inner class in
     * ViewLoggerDaoImpl.
     * 
     * @author US Technology Resources - www.ustri.com <br />
     * @version $Id: ViewLoggerDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
     */
    class LogEntrySummaryMapper extends MappingSqlQuery {

        /**
         * @param dataSource
         * @param sql
         */
        public LogEntrySummaryMapper(DataSource dataSource, String sql) {
            super(dataSource, sql);
            declareParameter(new SqlParameter(Types.INTEGER));
        }

        /**
         * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
         *      int)
         * @param rs
         * @param rowNum
         * @return
         * @throws java.sql.SQLException
         */
        protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            LogEntry le = new LogEntry();

            le.setId(rs.getInt(1));
            le.setServerName(rs.getString(2));
            le.setMessage(rs.getString(3));
            le.setLoggingEntity(rs.getString(4));
            Timestamp ts = rs.getTimestamp(5);
            le.setUpdateTimestamp(ts != null ? new Date(ts.getTime()) : null);

            return le;
        }

    }

    /**
     * Implementation of SqlUpdate to delete a log entry from database.
     * @author US Technology Resources - www.ustri.com <br />
     * @version $Id: ViewLoggerDaoImpl.java 16454 2007-03-30 20:52:06Z U10567 $
     */
    class LogEntryDeleter extends SqlUpdate {

        public LogEntryDeleter(DataSource dataSource, String sql) {
            super(dataSource, sql);
            declareParameter(new SqlParameter(Types.INTEGER));
        }
    }
    /**
     * Implementation of BatchSqlUpdate to delete multiple log entries from database. 
     * @author US Technology Resources - www.ustri.com <br />
     * @version $Id$
     */
    class MultipleLogEntryDeleter extends BatchSqlUpdate{
        public MultipleLogEntryDeleter(DataSource dataSource, String sql) {
            super(dataSource, sql);
            declareParameter(new SqlParameter(Types.INTEGER));
        }
    }

}