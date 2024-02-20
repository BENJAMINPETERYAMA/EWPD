/*
 * LogEntryFactoryImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.List;

import com.wellpoint.wpd.common.framework.logging.LogEntry;
import com.wellpoint.wpd.common.framework.logging.ViewLoggerDao;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LogEntryFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LogEntryFactoryImpl extends ObjectFactory implements
        LogEntryFactory {

    private ViewLoggerDao viewLoggerDao;

    public LogEntryFactoryImpl() {
        super();
    }

    /**
     * @param loggerDao The loggerDao to set.
     */
    public void setViewLoggerDao(ViewLoggerDao viewLoggerDao) {
        this.viewLoggerDao = viewLoggerDao;
    }

    /**
     * @see com.wellpoint.wpd.business.framework.bo.LogEntryFactory#getLogEntry(int)
     * @param id
     * @return
     */
    public LogEntry getLogEntry(int id) {
        LogEntry le = viewLoggerDao.retrieveLogEntry(id);
        return le;
    }

    /**
     * 
     * @see com.wellpoint.wpd.business.framework.bo.LogEntryFactory#deleteLogEntry(int)
     * @param id
     */
    public void deleteLogEntry(int id) {
        viewLoggerDao.deleteLogEntry(id);
    }
    
    public void deleteLogEntries(List ids){
        viewLoggerDao.deleteLogEntries(ids);
    }

    /**
     * 
     * @see com.wellpoint.wpd.business.framework.bo.LogEntryFactory#getLogEntries(int)
     * @param noOfRecords
     * @return
     */
    public List getLogEntries(int noOfRecords) {
        return viewLoggerDao.retrieveLogEntrySummary(noOfRecords);
    }

}