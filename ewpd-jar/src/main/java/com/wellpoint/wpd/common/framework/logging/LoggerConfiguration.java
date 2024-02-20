/*
 * LoggerConfiguration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LoggerConfiguration.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LoggerConfiguration {

    public static String log4jConfigFile = "log4j2.properties";
    public static boolean duplicateToFile = false;
    public static String loggingEntity = "";
    
    /**
     * @return Returns the loggingEntity.
     */
    public String getLoggingEntity() {
        return loggingEntity;
    }
    /**
     * @param userId The loggingEntity to set.
     */
    public void setLoggingEntity(String userId) {
        LoggerConfiguration.loggingEntity = userId;
    }
    /**
     * 
     */
    public LoggerConfiguration() {
        super();
    }

    /**
     * @param log4jConfigFile The log4jConfigFile to set.
     */
    public void setLog4jConfigFile(String log4jConfigFile) {
        LoggerConfiguration.log4jConfigFile = log4jConfigFile;
    }
    /**
     * @param duplicateToFile The logToFile to set.
     */
    public void setDuplicateToFile(boolean duplicateToFile) {
        LoggerConfiguration.duplicateToFile = duplicateToFile;
    }
    
    /**
     * @return Returns the duplicateToFile.
     */
    public static boolean isDuplicateToFile() {
        return duplicateToFile;
    }
}
