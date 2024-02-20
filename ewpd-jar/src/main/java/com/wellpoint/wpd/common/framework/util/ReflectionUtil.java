/*
 * ReflectionUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReflectionUtil.java 6456 2006-10-03 00:28:41Z U10567 $
 */
public class ReflectionUtil {
    /**
     * Get the value of the business object attributes from the businessObject.
     * 
     * @param businessObject
     * @param businessObjectAttributeName
     * @return
     * @throws AdapterException
     */
    public static Object getValueOfBusinessAttributeByReflection(
            Object businessObject, String businessObjectAttributeName)
            throws SevereException {
//      Check whether the inputs are null
        if (businessObject == null || businessObjectAttributeName == null
                || "".equals(businessObjectAttributeName))
           throw new IllegalArgumentException("Either of the inputs to getValueOfBusinessAttributeByReflection is null");
        Class cls = businessObject.getClass();
        // Find the getter method corresponding to the business
        // object attribute
        String getMethodName = "get"
                + StringUtil.capitalizeFirstLetter(businessObjectAttributeName);
        String businessObjectName = businessObject.getClass().getName();
        Method getMethod = null;
        Object valueOfBusinessAttribute = null;
        try {
                      getMethod = cls.getMethod(getMethodName, null);
            valueOfBusinessAttribute = getMethod.invoke(businessObject, null);
        } catch (NoSuchMethodException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("The Method " + getMethodName
                    + "() is not found for the businessObject "
                    + businessObjectName,params, e);
        } catch (Exception e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("Error in calling " + getMethodName
                    + "() correspoinding to " + businessObjectAttributeName
                    + " in BO :" + businessObjectName,params, e);
        }
        return valueOfBusinessAttribute;
    }
    /**
     * Set the value of the business object attributes to the businessObject.
     * 
     * @param businessObject
     * @param businessObjectAttributeName
     * @param parameterType     
     * @param boAttributeValue
     * @return
     * @throws AdapterException
     */
    public static void setValueOfBusinessAttributeByReflection(
            Object businessObject, String businessObjectAttributeName,
            Class[] parameterType, Object[] boAttributeValue)
            throws SevereException {
        //Check whether the inputs are null
        if (businessObject == null || businessObjectAttributeName == null
                || "".equals(businessObjectAttributeName)) {
            throw new IllegalArgumentException(
                    "Either businessObject or businessObjectAttributeName parameters to setValueOfBusinessAttributeByReflection is null");
        }
        
        if (parameterType == null) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException(
                    "The setter method should be defined as per the java bean specification",params,
                    null);
        }        
        //Create the classObject
        Class classObject = businessObject.getClass();
        String businessObjectName = classObject.getName();
        // Find the setter method corresponding to the business
        // object attribute
        String methodName = "set"
                + StringUtil.capitalizeFirstLetter(businessObjectAttributeName);
        try {
            Method method = classObject.getMethod(methodName, parameterType);
            method.invoke(businessObject, boAttributeValue);
        } catch (NoSuchMethodException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("The Method " + methodName
                    + " is not found for the businessObject "
                    + businessObjectName,params, e);
        } catch (IllegalArgumentException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException(
                    "Illegal arguments used for the setter method, "
                            + methodName + ", for the businessObject "
                            + businessObjectName,params, e);
        } catch (InvocationTargetException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException(
                    "Illegal object used for invoking the setter method, "
                            + methodName + ", for the businessObject "
                            + businessObjectName,params, e);
        } catch (Exception e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("Error in calling the setter method, "
                    + methodName + ", correspoinding to "
                    + businessObjectAttributeName + " in BO :"
                    + businessObjectName,params, e);
        }
    }
    
    public static Object invokeMethod(Object businessObject,String methodName,Class [] parameterTypes,Object[] parameters) 
    throws SevereException, InvocationTargetException{
    	
    	Class clsObj = businessObject.getClass();
    	String businessObjectName = clsObj.getName();
    	Object returnObject ;
    	try{
    		Method method = clsObj.getMethod(methodName,parameterTypes);
    		returnObject = method.invoke(businessObject,parameters);
    	 } catch (NoSuchMethodException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("The Method " + methodName
                    + " is not found for the businessObject "
                    + businessObjectName,params, e);
        } catch (IllegalArgumentException e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException(
                    "Illegal arguments used for the method, "
                            + methodName + ", for the businessObject "
                            + businessObjectName,params, e);
        }  catch (Exception e) {
        	List params = new ArrayList();
        	params.add(businessObject);
            throw new SevereException("Error in calling the method, "
            		 + methodName + ", for the businessObject "
                     + businessObjectName,params, e);
        }
        return returnObject;
    }
}