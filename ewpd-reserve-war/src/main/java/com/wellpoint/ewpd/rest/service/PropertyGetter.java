/**
 * 
 */
package com.wellpoint.ewpd.rest.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.wellpoint.contractcodepool.exception.InvalidInputParameterException;

/**
 * @author AF37766
 *
 */
@Service
//@PropertySource({"classpath:usr/WebSphere/wasLiberty/usr/servers/ewpdRestService/EWPDFILES/adapter.properties"})
public class PropertyGetter {
	/*@Value("${business-objects}")
	private String businessobjects;*/
	
	

	
	public String getValue(String key) throws InvalidInputParameterException {
        //Get the path of the adapter.properties file from the CLASSPATH
        //And get the handle to the adapter.properties file
		//System.out.println("businessobjects  " +businessobjects);
		
    	String path = null;
        //Class objClass = "".getClass();
        InputStream inputStream = PropertyGetter.class.getClassLoader().getResourceAsStream("/eWPDRestServiceProperties.properties");
        if(inputStream==null){
            throw new InvalidInputParameterException(
                    "The eWPDRestServiceProperties.properties is not available in CLASSPATH");
        }else{
            //Read the properties file to get the business-objects key
            //business-objects key should have the complete path of xml file
            //that defines the business objects
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
                path = properties.getProperty(key);
                if(path==null){
                    throw new InvalidInputParameterException(
                            "Specify the "+key+" parameter in the eWPDRestServiceProperties.properties file");
                }
            } catch (IOException e) {
                throw new InvalidInputParameterException(
                        "Error in reading the content of the eWPDRestServiceProperties.properties");
            }
        }
        return path;
    }

}
