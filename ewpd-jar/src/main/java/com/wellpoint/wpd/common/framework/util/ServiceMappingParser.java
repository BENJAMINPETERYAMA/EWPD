/*
 * ServiceMappingParser.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;

/**
 * A utility class with methods to parse the service mapping configuration files (XML).  The configuration file
 * format is
 *     <service-mappings>
 *         <request-class>WPDRequest class name</request-class>
 *         <service-name>name of service</service-name>
 *      </service-mappings>
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ServiceMappingParser.java 235 2006-07-15 14:55:05Z U10347 $
 */
public class ServiceMappingParser{

    private final static String SERVICE_MAPPINGS_TAG = "service-mapping";
    private final static String REQUEST_CLASS_TAG = "request-class";
    private final static String SERVICE_NAME_TAG = "service-name";
    /**
     * 
     */
    public ServiceMappingParser() {
        super();
    }
    /**
     * Parses the XML configuration files and creates a Map with the request-class
     * name as the key and the service name as the value.  The service class will be injected by Spring. 
     * @param configFileName The XML configuration file for the service mapping. 
     * @return Map with the request-class name as the key and the service-class name as the value.
     * @throws Exception
     */
    public Map parseServiceMapping(String configFileName) throws Exception{
        if(configFileName != null){
            File file = new ClassPathResource(configFileName).getFile();
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(file);
            Element rootElement = doc.getRootElement();
            return createServicesMap(rootElement);
        }
        return null;
    }
    
    protected Map createServicesMap(Element root) throws Exception{
        Map services = new HashMap();
        List serviceMappings = root.getChildren(SERVICE_MAPPINGS_TAG);
        if(serviceMappings != null){
            Iterator smIter = serviceMappings.iterator();
            while(smIter.hasNext()){
                Element sm = (Element)smIter.next();
                String key = sm.getChildText(REQUEST_CLASS_TAG);
                String value = sm.getChildText(SERVICE_NAME_TAG);
                if(key != null || value != null){
                    services.put(key,value);
                }
            }
        }
        return services;
    }

}
