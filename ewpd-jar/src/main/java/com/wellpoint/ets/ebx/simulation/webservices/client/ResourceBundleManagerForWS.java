package com.wellpoint.ets.ebx.simulation.webservices.client;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceBundleManagerForWS {
	
	private final static Logger logger = Logger.getLogger(ResourceBundleManagerForWS.class.getName());
	
	public static String getURLForWebService(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("webservice", Locale
				.getDefault());
		logger.log(Level.INFO, "ResourceBundleManagerForWS : webservice.ebx.url-->" + bundle.getString(key));
		return bundle.getString(key);
	}
}
