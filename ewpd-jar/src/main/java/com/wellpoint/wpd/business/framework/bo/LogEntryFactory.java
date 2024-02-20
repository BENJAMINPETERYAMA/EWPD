/*
 * LogEntryFactory.java
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

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LogEntryFactory.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface LogEntryFactory {
    LogEntry getLogEntry(int id);

    List getLogEntries(int noOfRecords);

    void deleteLogEntry(int id);
    
    void deleteLogEntries(List ids);
}