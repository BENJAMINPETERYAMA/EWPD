/*
 * ViewLoggerDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.util.List;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ViewLoggerDao.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface ViewLoggerDao {

    /**
     * Retrieves the log details of the specified log id
     * @param id
     * @return
     */
    LogEntry retrieveLogEntry(int id);

    /**
     * Retrieves a summary of log entries in the database.
     * @param noOfRecords The number of records to be retrieved. Pass 0 to retrieve all.
     * @return List of LogEntry objects. parameters and stackTrace attribute
     *         will not be populated.
     */
    List retrieveLogEntrySummary(int noOfRecords);

    /**
     * Deletes the log entry for the specified id.
     * @param id
     */
    void deleteLogEntry(int id);
    
    void deleteLogEntries(List ids);
}