/*
 * CommonBackingBean.java  
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.Environment;

/**
 * Backing bean for Common Information.
 */
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: CommonBackingBean.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class CommonBackingBean extends WPDBackingBean {

    private static final String PATH = "/WEB-INF/version.properties";

    private static final String REVISION_PREFIX = "revision.prefix";

    private static final String BUILD_NUMBER = "build.number";

  

    private String version;

    private String environment;

    /**
     * Constructor
     */
    public CommonBackingBean() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        InputStream inputStream = facesContext.getExternalContext()
                .getResourceAsStream(PATH);
        Properties properties = new Properties();
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                SevereException se = new SevereException(
                        "Unexpected error caught in CommonBackingBean", null, e);
                Logger.logException(se);
            }
            version = properties.getProperty(REVISION_PREFIX) + "."
                    + properties.getProperty(BUILD_NUMBER);
            if (Environment.isDevelopment()) {
                environment = "Development";
            } else if (Environment.isSystemTest()) {
                environment = "System Test";
            } else if (Environment.isUserAcceptanceTest()) {
                environment = "User Acceptance Test";
            } else if (Environment.isProduction()) {
                environment = "Production";
            } else if (Environment.isMigration()) {
                environment = "Migration";
            } else {
                environment = "Unknown";
            }
        }
    }

    /**
     * Returns the version
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the environment
     * @return environment
     */
    public String getEnvironment() {
        return environment;
    }
}