/*
 * Logger.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.logging;

import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
import org.owasp.esapi.ESAPI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.exception.WPDException;

/**
 * Interface for application logging. All logging operations should be done
 * through the static methods of this class. Internally it uses Log4J. It is
 * capable of logging information to the database
 * (logException(SevereException))
 * <p />
 * The logger can be configured using the Spring configuration file - logger-context.xml. The
 * entries are <br />
 * log4jConfigFile: Path to configuration file for Log4J - log4j.properties. <br />
 * duplicateToFile: If set to true, SevereException will be logged to file in addition
 * to the database<br/>
 * loggingEntity:  Name of the application creating the log entry in the database. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Logger.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class Logger {

    protected static final String LOGGER_CONFIG_FILE = "com/wellpoint/wpd/common/configfiles/logger-context.xml";
    
    protected static ApplicationContext loggerContext;
    protected static LoggerConfiguration LOGGER_CONFIGURATION;
    protected static org.apache.log4j.Logger STDOUT;
    protected static org.apache.log4j.Logger STDERR;
    protected static org.owasp.esapi.Logger logSTDOUT;
    protected static org.owasp.esapi.Logger logSTDERR;
    
    static{
        loggerContext = new ClassPathXmlApplicationContext(LOGGER_CONFIG_FILE);
        LOGGER_CONFIGURATION = (LoggerConfiguration)loggerContext.getBean("loggerConfiguration");
        try{
            URL url = Loader.getResource(LoggerConfiguration.log4jConfigFile);
            PropertyConfigurator.configure(url);
            STDOUT = org.apache.log4j.Logger.getLogger("STDOUT");
            STDERR = org.apache.log4j.Logger.getLogger("STDERR");
            logSTDOUT= ESAPI.getLogger("STDOUT");
            logSTDERR= ESAPI.getLogger("STDERR");
            
        }catch(Exception e){
            throw new RuntimeException("ERROR initializing Logger",e);
        }
    }
    /**
     *  
     */
    public Logger() {
    }

    public static void logDebug(Object obj) {
    	if(obj instanceof List) {
    		 logSTDOUT.debug(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, StringUtils.join((List)obj, ", "));
    	} else {
    		logSTDOUT.debug(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, (String) obj);
    	}
    }

    public static void logDebug(Exception exception) {
        STDOUT.debug(exception,exception);
    }

    public static void logError(Object obj) {
    	if(obj instanceof List) {
    		logSTDERR.error(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, StringUtils.join((List)obj, ", "));
    } else {
    		logSTDERR.error(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, (String) obj);
    	}
    }

    public static void logError(Exception exception) {
        STDERR.error(exception,exception);
    }

    public static void logInfo(Object obj) {
    	if(obj instanceof List) {
    		logSTDOUT.info(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, StringUtils.join((List)obj, ", "));
    	} else {
    		logSTDOUT.info(org.owasp.esapi.Logger.EVENT_UNSPECIFIED, (String) obj);
    	}
    }
    public static void logInfo(Exception exception) {
        STDOUT.info(exception,exception);
    }

    public static void logWarn(Object obj) {
        STDERR.warn(obj);
    }

    public static void logWarn(Exception exception) {
        STDERR.warn(exception,exception);
    }

    public static void logException(SevereException exception) {
        try {
            logToDb(exception);
            if(LoggerConfiguration.isDuplicateToFile()){
                logToFile(exception);
            }
        } catch (Exception e) {
            logError(e);
            try{
                logToFile(exception);
            }catch(Exception ex){
                //logging exceptions should not be propogated up the heirarchy.
                ex.printStackTrace();
            }
        }
    }

    public static void logException(ValidationException exception) {
        try {
            logToFile(exception);
        } catch (Exception e) {
            logError("Logger error.  Unable to log Exception to file "
                    + e.getMessage());
            logError(exception);
        }
    }

    protected static void logToDb(WPDException exception) throws Exception {
        if (exception != null) {
            ((ExceptionLogger)loggerContext.getBean("dbLogger")).log(exception);
        }
    }
    
    protected static void logToFile(WPDException exception) throws Exception{
        if(exception != null){
            ExceptionLogger logger = (ExceptionLogger)loggerContext.getBean("fileLogger");
            logger.log(exception);
        }
    }


}