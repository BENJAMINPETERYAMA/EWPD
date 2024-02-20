/*
 * WPDServiceController.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.request.WPDRequestFactory;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.util.ServiceMappingParser;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDServiceController.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public abstract class WPDServiceController {

    /**
     * A Map to store the Spring ApplicationContext for each WPD Service. The
     * key is the bean factory configuration xml file name and the value, an
     * instance of ApplicationContext.
     */
    private static Map contexts = new HashMap();
    /**
     * A Map to cache the service mappings for each WPD Service. The key is the
     * service mapping configuration XML file name and the value a Map
     * containing the request class and it's associated service class name.
     */
    private static Map serviceMappings = new HashMap();
    /**
     * Initialize the ApplicationContext using the specified file name as the
     * configuration file. The initialize context is added to the contexts Map
     * with the file name as the key.
     * 
     * @param configFileName name of Spring bean factory XML file. 
     * @throws Exception
     */
    protected void initializeContext(String configFileName) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                configFileName);
        if (context == null) {
            throw new Exception(
                    "Error retrieving Spring ApplicationContext for configuration file:  "
                            + configFileName);
        }
        contexts.put(configFileName,context);
    }
    /**
     * Initialize service mappings
     * 
     * @param configFileName name of service mapping file.
     * @throws Exception
     */
    protected void initializeServiceMapping(String configFileName)
            throws Exception {
        Map mapping = new ServiceMappingParser()
                .parseServiceMapping(configFileName);
        if (mapping == null) {
            throw new Exception(
                    "Error retrieving mapping for configuration file:  "
                            + configFileName);
        }
        serviceMappings.put(configFileName, mapping);
    }
    protected abstract String getContextConfigFileName();
    protected abstract String getServiceMappingFileName();
    /**
     * 
     * @param request WPDRequest input to the service. 
     * @return WPDResponse 
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request)
            throws ServiceException {
        try {
            request.validate();
            if (contexts.get(getContextConfigFileName()) == null) {
                initializeContext(getContextConfigFileName());
            }
            if (serviceMappings.get(getServiceMappingFileName()) == null) {
                initializeServiceMapping(getServiceMappingFileName());
            }
            String serviceName = (String) ((Map) serviceMappings
                    .get(getServiceMappingFileName())).get(request.getClass()
                    .getName());
            ApplicationContext context = (ApplicationContext) contexts
                    .get(getContextConfigFileName());
            WPDService service = (WPDService) context.getBean(serviceName);
            Method method = getExecuteMethod(service,request);
            if(method == null){
                List params = new ArrayList();
                params.add(request);
                params.add(service);
                throw new ServiceException("No execute method for service",params,null);
            }
            return (WPDResponse)method.invoke(service,new Object[]{request});
        }catch (ValidationException ve){
            List params = new ArrayList();
            params.add(request);
            ServiceException se = new ServiceException("Request object failed validation",params,ve);
            Logger.logException(se);
            throw se;
        } catch (ServiceException se) {
        	Logger.logError(se);
            Logger.logException(se);
            throw se;
        } catch (Exception e) {
        	Logger.logError(e);
            List params = new ArrayList();
            params.add(request);
            ServiceException se = new ServiceException("Unknown error while executing service",params,e);            
            throw se;
        }
        //return null;
    }
    
    protected Method getExecuteMethod(WPDService service, Object request) throws Exception{
        Class reqClass = request.getClass();
        Method method = null;
        while(reqClass != null){
            try{
                method = service.getClass().getMethod("execute",new Class[]{reqClass});
                return method;
            }catch(NoSuchMethodException nsme){
                reqClass = reqClass.getSuperclass();
            }
        }
        return null;
    }
    
    public WPDRequest getServiceRequest(String serviceName) throws ServiceException{
    	WPDRequestFactory wpdrf = (WPDRequestFactory)ObjectFactory.getFactory(ObjectFactory.REQUEST);
    	return wpdrf.getRequest(serviceName);
    }
}