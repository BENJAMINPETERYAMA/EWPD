/*
 * MetadataConstants.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MetadataConstants.java 49162 2008-09-29 10:40:58Z u18600 $
 */
public class MetadataConstants {
    
    /* xml tags  and attributes */
    public static final String TAG_METAOBJECT = "metaObject";
    public static final String TAG_CHECKOUT_DURATION = "checkoutDuration";
    public static final String TAG_BUILDER_CLASS = "builderClass";
    public static final String TAG_LOGGER_NAME = "loggerName";
    public static final String TAG_LOCATE = "locate";
    public static final String TAG_CRITERIA = "criteria";
    public static final String TAG_INFORMATION_ELEMENT = "informationElement";
    public static final String TAG_STATUS_TRANSITIONS = "status-transitions";
    public static final String TAG_SECURITY = "security";
    public static final String TAG_TRANSITION = "transition";
    public static final String TAG_ACTIVITY = "activity";
    public static final String TAG_STATUS_TRANSITION_UNIVERSE = "status-transition-universe";
    
    public static final String ATTRIB_NAME = "name";
    public static final String ATTRIB_OVERRIDE = "override";
    public static final String ATTRIB_ENABLELOG = "enableLog";
    public static final String ATTRIB_TYPE = "type";
    public static final String ATTRIB_VALUE = "value";
    public static final String ATTRIB_MODULE = "module";
    public static final String ATTRIB_TASKNAME = "taskName";
    public static final String ATTRIB_TYPE_OF_ATTRIB = "attrType";
    public static final String ATTRIB_FROM = "from";
    public static final String ATTRIB_TO = "to";
    
    /* xsd validation constants*/
	public static final String XSD_LOCATION = "/com/wellpoint/wpd/common/configfiles/metadata-definition.xsd";
	public static final String SAX_PARSER_CLASS = "org.apache.xerces.parsers.SAXParser";
	public static final String PARSER_VALIIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
	public static final String PARSER_XSD_LOC_PPRTY = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
	
	/* InformationElement attribute type values*/
	public static final String TYPE_OF_ATTRIBUTE_KEY = "key";
}
