/**
 * This class having method to create instance of classes to retrieve db/file properties.
 * The method createInstance  insatnce will return an object acording to the storeType input. 
 * 
 */
package com.wellpoint.wpd.common.app.config;

import com.wellpoint.wpd.common.framework.exception.SevereException;


public abstract class ApplicationProperties {
	
	public static final String PROPERTIES_DB ="DBproperties";
	
	protected ApplicationProperties(){	}

	/**
	 * This method is for creating object according to the type string we are passing. 
	 * IF we need properties from database, type string will be ApplicationProperties.PROPERTIES_DB.
	 * @ param storeType
	 *  The storeType will determine type of object to create 
	 * @ return applicationProperties
	 */
	public static ApplicationProperties createInstance(String storeType) {
		if(storeType == null )return null;
		ApplicationProperties applicationProperties = null;
		if(storeType.equals(PROPERTIES_DB)){
			applicationProperties =	new AppPropertiesFromDataStore();
		}
		return applicationProperties;
	}
	/*
	 * this will return the value from db/file according to the input string 
	 * @ param key
	 * @ return string 
	 * 
	 */
	protected abstract String getProperties(String key) throws SevereException; 
	
}
