/*
 * LogViewerBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.LogEntryFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.logging.LogEntry;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LogViewerBackingBean.java 19204 2007-04-13 18:13:18Z U10567 $
 */
public class LogViewerBackingBean extends WPDBackingBean {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "MM.dd.yyyy HH:mm:ss z");

    private List logEntries;

    private LogEntry logEntry;

    private int logId;

    private DataModel logEntriesModel;

    private int entriesShown;

    /**
     * @return Returns the logEntries.
     */
    public List getLogEntries() {
        return logEntries;
    }

    /**
     * @param logEntries
     *            The logEntries to set.
     */
    public void setLogEntries(List logEntries) {
        this.logEntries = logEntries;
    }

    /**
     * @return Returns the logEntry.
     */
    public LogEntry getLogEntry() {
        return logEntry;
    }

    /**
     * @param logEntry
     *            The logEntry to set.
     */
    public void setLogEntry(LogEntry logEntry) {
        this.logEntry = logEntry;
    }

    /**
     * @return Returns the logId.
     */
    public int getLogId() {
        return logId;
    }

    /**
     * @param logId
     *            The logId to set.
     */
    public void setLogId(int logId) {
        this.logId = logId;
    }

    /**
     * @return Returns the logEntriesModel.
     */
    public DataModel getLogEntriesModel() {
        return logEntriesModel;
    }

    public boolean isSummaryRendered() {
        return logEntriesModel != null;
    }

    public boolean isDetailsRendered() {
        return logEntry != null;
    }

    public String show50() {
        entriesShown = 50;
        logEntriesModel = new ListDataModel(retrieveLogEntries(50));
        logEntry = null;
        setBreadCrumbText("Log Viewer >> Last 50");
        return "success";
    }

    public String show100() {
        entriesShown = 100;
        logEntriesModel = new ListDataModel(retrieveLogEntries(100));
        logEntry = null;
        setBreadCrumbText("Log Viewer >> Last 100");
        return "success";
    }

    public String show200() {
        entriesShown = 200;
        logEntriesModel = new ListDataModel(retrieveLogEntries(200));
        logEntry = null;
        setBreadCrumbText("Log Viewer >> Last 200");
        return "success";
    }

    public String showAll() {
        entriesShown = 0;
        logEntriesModel = new ListDataModel(retrieveLogEntries(0));
        logEntry = null;
        setBreadCrumbText("Log Viewer >> All");
        return "success";
    }

    public String showDetails() {
        LogEntry le = (LogEntry) logEntriesModel.getRowData();
        int selectedLogId = le.getId();
        logEntriesModel = null;
        LogEntryFactory lef = (LogEntryFactory) ObjectFactory
                .getFactory(ObjectFactory.LOG);
        logEntry = lef.getLogEntry(selectedLogId);
        return "success";
    }

    public String showLogId() {
        logEntriesModel = null;
        LogEntryFactory lef = (LogEntryFactory) ObjectFactory
                .getFactory(ObjectFactory.LOG);
        logEntry = lef.getLogEntry(getLogId());
        return "success";
    }

    public String delete() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String[] logIds = (String[]) fc.getExternalContext()
                .getRequestParameterValuesMap().get("logIds");
        //converting String to int to catch any bad data in the backing
        //bean rather than handle it lower down the heirarchy.
        if (logIds != null && logIds.length > 0) {
            List ids = new ArrayList();
            for (int i = 0; i < logIds.length; i++) {
                try {
                    ids.add(Integer.valueOf(logIds[i]));
                } catch (Exception e) {
                    //nothing to do except skip the record.
                	Logger.logError(e);
                }
            }
            LogEntryFactory lef = (LogEntryFactory) ObjectFactory
                    .getFactory(ObjectFactory.LOG);
            lef.deleteLogEntries(ids);
            logEntriesModel = new ListDataModel(
                    retrieveLogEntries(entriesShown));
        } else {
            //display error message?
        }
        return "success";
    }

    public String deleteEntry() {
        LogEntry le = (LogEntry) logEntriesModel.getRowData();
        if (le != null) {
            int selectedLogId = le.getId();
            LogEntryFactory lef = (LogEntryFactory) ObjectFactory
                    .getFactory(ObjectFactory.LOG);
            lef.deleteLogEntry(selectedLogId);
            logEntriesModel = new ListDataModel(
                    retrieveLogEntries(entriesShown));
        } else {
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    null);
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);
        }
        return "success";
    }

    protected List retrieveLogEntries(int noOfRecords) {
        LogEntryFactory lef = (LogEntryFactory) ObjectFactory
                .getFactory(ObjectFactory.LOG);
        return lef.getLogEntries(noOfRecords);
    }

    public String getFormattedDate() {
        if (logEntry.getUpdateTimestamp() != null) {
            return sdf.format(logEntry.getUpdateTimestamp());
        }
        return "";
    }

    public String getFormattedParameters() {
        if (logEntry.getParameters() != null) {
            return replaceNewlineTab(logEntry.getParameters());
        }
        return "";
    }

    public String getFormattedStackTrace() {
        if (logEntry.getParameters() != null) {
            return replaceNewlineTab(logEntry.getStackTrace());
        }
        return "";
    }

    protected String replaceNewlineTab(String input) {
        String temp = input.replaceAll("\n", "<BR>");
        return temp.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
    }
    
    public TimeZone getTimeZone(){
    	 AuditFactory auditFactory = (AuditFactory) ObjectFactory
         .getFactory(ObjectFactory.AUDIT);
    	 Audit audit = auditFactory.getAudit();
         return audit.getTimeZone();
    }
}