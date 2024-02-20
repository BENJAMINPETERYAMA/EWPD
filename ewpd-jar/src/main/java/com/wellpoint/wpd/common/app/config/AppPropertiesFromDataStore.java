/*
 * Created on Nov 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.app.config;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.app.adapter.AppPropertiesSettingAdapterManger;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * This class have method to retreiving property value from db source.
 * By passing key to the getProperties will return the value of that key.
 */
public class AppPropertiesFromDataStore extends ApplicationProperties {

	/**
	 * This method is implemented from ApplicationProperties class.
	 * @param key
	 * using the input 'key' there will perform a seacrh in the db table to get the value against the key.
	 */
	protected String getProperties(String key) throws SevereException {
		AppPropertiesSettingAdapterManger appPropertiesAdapterManger = new AppPropertiesSettingAdapterManger();
		String propertyValue =null;
		
		try {
			propertyValue=appPropertiesAdapterManger.getApplicationConfiguration(key);
		} catch (AdapterException e) {
			List params = new ArrayList(2);
			String obj = e.getClass().getName();
			params.add(obj);
			params.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in getProperties()of AppPropertiesFromDataStore class",
					params, e);
		} 
		
		return propertyValue;
	}

}
