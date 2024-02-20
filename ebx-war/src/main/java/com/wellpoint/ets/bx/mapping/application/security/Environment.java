/*
 * Environment.java  
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Environment.java 58608 2010-01-08 07:06:30Z u20622 $
 */
public class Environment {
	private static final String ENVIRONMENT_PROPERTY = "WPD_ENV";
	private static String environment;
	
	static{
		environment = System.getProperty(ENVIRONMENT_PROPERTY);
        if (environment == null || environment.trim().length() == 0) {
            environment = "Unknown";
           // Logger.logError("Environment variable " + ENVIRONMENT_PROPERTY + " not specified.");
        }
	}
        
	public static boolean isDevelopment(){
		if("DEV".equalsIgnoreCase(environment)){
			return true;
		}
		return false;
	}
	public static boolean isSystemTest(){
		if("SIT".equalsIgnoreCase(environment)){
			return true;
		}
		return false;
	}
	public static boolean isUserAcceptanceTest(){
		if("UAT".equalsIgnoreCase(environment)){
			return true;
		}
		return false;
	}
	public static boolean isProduction(){
		if("PROD".equalsIgnoreCase(environment)){
			return true;
		}
		return false;
	}
	public static boolean isMigration() {
		if("MIG".equalsIgnoreCase(environment)){
			return true;
		}
		return false;
	}
	
	public static String getCurrentEnvironment() {
		String environment = null;
		if (isDevelopment()) {
			environment = "Development";
		} else if (isSystemTest()) {
			environment = "System Test";
		} else if (isUserAcceptanceTest()) {
			environment = "User Acceptance Test";
		} else if (isProduction()) {
			environment = "Production";
		} else if (isMigration()) {
			environment = "Migration";
		} else {
			environment = "Unknown";
		}
		return environment;
	}
}
