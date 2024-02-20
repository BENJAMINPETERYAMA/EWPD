/*
 * MetaObjectFactoryImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.bo.MetadataParser;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MetaObjectFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class MetaObjectFactoryImpl extends ObjectFactory implements MetaObjectFactory {

	private String metadataDefinitionFile;
	
	private static Map metaObjectCacheForBOName = new HashMap();
	private static Map locateCriteriaToBONameMap = new HashMap();
	
	public MetaObject getMetaObject(Object businessObject) throws MetadataParserException{
		String businessObjectName = businessObject.getClass().getName();
		MetaObject metaObject = getMetaObjectForBOName(businessObjectName);
		return metaObject;
	}

	public MetaObject getMetaObject(LocateCriteria object) throws MetadataParserException {
	    String locateCriteria = object.getClass().getName();
	    String businessObjectName = (String) locateCriteriaToBONameMap.get(locateCriteria);
	    MetaObject metaObject = null;
	    if(businessObjectName != null)
	        metaObject = getMetaObjectForBOName(businessObjectName);
	    else{
	        //Parse through the XML and populate the Maps
	        MetadataParser metadataParser = new MetadataParser();
	        metaObject = metadataParser.createMetaObjectForLocateCriteria(locateCriteria, metadataDefinitionFile);
	        businessObjectName = metaObject.getName();
	        locateCriteriaToBONameMap.put(locateCriteria, businessObjectName);
	        metaObjectCacheForBOName.put(businessObjectName, metaObject);
	    }
		return metaObject;
	}

	private MetaObject getMetaObjectForBOName(String businessObjectName) throws MetadataParserException{
		MetaObject metaObject = (MetaObject)metaObjectCacheForBOName.get(businessObjectName);
		if(metaObject == null){
		    MetadataParser metadataParser = new MetadataParser();		    
		    metaObject = metadataParser.createMetaObject(businessObjectName, metadataDefinitionFile);
		    if(metaObject != null){
		        metaObjectCacheForBOName.put(businessObjectName, metaObject);
		        List criteriaList = metaObject.getLocateCriteriae();
		        if(criteriaList != null){
		            Iterator criteriaListItr = criteriaList.iterator();
		            while(criteriaListItr.hasNext()){
		                String criteria = (String)criteriaListItr.next();
			            String boName = (String)locateCriteriaToBONameMap.get(criteria);
			            if(boName != null && !businessObjectName.equals(boName)){
			                List params = new ArrayList();
			                params.add(businessObjectName);
			                params.add(boName);
			                params.add(criteria);
			                throw new MetadataParserException(
	                                "Same locate criteria is present for multiple business objects",
	                                params, null);
			            }
			            locateCriteriaToBONameMap.put(criteria, businessObjectName);
		            }
		        }
		    }
		}
		return metaObject;
	}
	
	/**
	 * @return Returns the metadataDefinitionFile.
	 */
	public String getMetadataDefinitionFile() {
		return metadataDefinitionFile;
	}
	/**
	 * @param metadataDefinitionFile The metadataDefinitionFile to set.
	 */
	public void setMetadataDefinitionFile(String metadataDefinitionFile) {
		this.metadataDefinitionFile = metadataDefinitionFile;
	}
	
	/**
	 * Added to facilitate the testing of caching logic
	 * This method should not be called from the application code
	 */
	public static void clearCache(){
	    metaObjectCacheForBOName.clear();
	    locateCriteriaToBONameMap.clear();
	}
}
