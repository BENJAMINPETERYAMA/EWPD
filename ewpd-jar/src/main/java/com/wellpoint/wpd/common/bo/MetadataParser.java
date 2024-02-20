/*
 * MetadataParser.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.bo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.wellpoint.wpd.business.framework.bo.MetaObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.framework.exception.MetadataParserException;
import com.wellpoint.wpd.common.framework.logging.Logger;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MetadataParser.java 49163 2008-09-29 10:42:21Z u18600 $
 */
public class MetadataParser {

    public static Map transitionUniverse;

    /**
     * Create the MetaObject corresponding to the metaObjectName as defined in
     * the XML definition
     * 
     * @param metaObjectName
     * @param metaObjectDefinitionFileName
     * @return
     * @throws MetadataParserException
     */
    public MetaObject createMetaObject(String metaObjectName,
            String metaObjectDefinitionFileName) throws MetadataParserException {
        if (metaObjectName == null || metaObjectDefinitionFileName == null)
            throw new IllegalArgumentException(
                    "Meta Object Name or definition file is not specified");
        Document document = retrieveDocument(metaObjectDefinitionFileName);
        Element root = document.getRootElement();
        if (transitionUniverse == null) {
            populateValidTransitions(root);
        }
        MetaObject metaObject = retrieveMetaObject(root, metaObjectName);
        return metaObject;
    }

    /**
     * @param root
     * @return
     */
    private void populateValidTransitions(Element root)
            throws MetadataParserException {
        transitionUniverse = new HashMap();
        Element statusTransitionUniverseElement = root
                .getChild(MetadataConstants.TAG_STATUS_TRANSITION_UNIVERSE);
        if (statusTransitionUniverseElement == null)
            throw new MetadataParserException(
                    "The status transition universe not defined", null, null);
        List transitions = ((Element) statusTransitionUniverseElement)
                .getChildren(MetadataConstants.TAG_TRANSITION);
        Iterator transitionsItr = transitions.iterator();
        while (transitionsItr.hasNext()) {
            Element elmt = (Element) transitionsItr.next();
            String name = elmt.getAttributeValue(MetadataConstants.ATTRIB_NAME);
            String from = elmt.getAttributeValue(MetadataConstants.ATTRIB_FROM);
            String to = elmt.getAttributeValue(MetadataConstants.ATTRIB_TO);
            String taskName = elmt.getAttributeValue(MetadataConstants.ATTRIB_TASKNAME);
            if (name == null || from == null || to == null)
                throw new MetadataParserException(
                        "Transition defined in 'status transition universe' should have a name, from and to values",
                        null, null);
            Transition transition = new Transition(from, to,taskName);
            transitionUniverse.put(name, transition);
        }
    }

    /**
     * Retrieve the Metadata information from the root Element and form the
     * MetaObject
     * 
     * @param root
     * @param metaObjectName
     * @return
     * @throws MetadataParserException
     */
    private MetaObject retrieveMetaObject(Element root, String metaObjectName)
            throws MetadataParserException {
        List metaObjectList = root
                .getChildren(MetadataConstants.TAG_METAOBJECT);
        if (metaObjectList != null) {
            Iterator moIt = metaObjectList.iterator();
            Element moElement = null;
            boolean isFound = false;
            while (moIt.hasNext()) {
                Element moelmnt = (Element) moIt.next();
                String businessObjectName = moelmnt
                        .getAttributeValue(MetadataConstants.ATTRIB_NAME);
                if (businessObjectName.equals(metaObjectName)) {
                    if (!isFound) {
                        moElement = moelmnt;
                        isFound = true;
                    } else {
                        throw new MetadataParserException(
                                "Duplicate metadata definition for business object ( "
                                        + metaObjectName + ")", null, null);
                    }
                }
            }
            if (moElement != null) {
                MetaObject mo = retrieveBusinessObjectMetaData(moElement);
                return mo;
            } else {
                throw new MetadataParserException(
                        "Metadata definition for business object ( "
                                + metaObjectName + ") is not found", null, null);
            }
        } else
            return null;
    }

    /**
     * Form the MetaObject from the Element of the metaObject tag
     * 
     * @param moElement
     * @return
     */
    private MetaObject retrieveBusinessObjectMetaData(Element moElement)
            throws MetadataParserException {
        if (moElement != null) {
            MetaObjectImpl mo = new MetaObjectImpl();

            //Get the Name, Type, checkout duration, builder class, locate
            // criteria of the
            // Business Object
            String busName = moElement
            	.getAttributeValue(MetadataConstants.ATTRIB_NAME);
            mo.setName(busName);
            mo.setType(moElement
                    .getAttributeValue(MetadataConstants.ATTRIB_TYPE));
            mo.setModule(moElement
                    .getAttributeValue(MetadataConstants.ATTRIB_MODULE));

            Element e = moElement
                    .getChild(MetadataConstants.TAG_CHECKOUT_DURATION);
            int checkOutDuration = Integer.parseInt(e
                    .getAttributeValue(MetadataConstants.ATTRIB_VALUE));
            //validaion for check out duration
            if( checkOutDuration > 99 || checkOutDuration < 1){
            	throw new MetadataParserException(
            			"Checkout Duration should be greater than or equal to 1 or less than 99" +
            			" days for business object ' "+busName+" ';",
            			new ArrayList(),null);
            }
            mo.setCheckOutDuration(checkOutDuration);

            e = moElement.getChild(MetadataConstants.TAG_BUILDER_CLASS);
            mo.setBuilderClassName(e
                    .getAttributeValue(MetadataConstants.ATTRIB_NAME));
            //add logger name
            e = moElement.getChild(MetadataConstants.TAG_LOGGER_NAME);
            if(e!=null){
            mo.setLoggerName(e
                    .getAttributeValue(MetadataConstants.ATTRIB_NAME));
            }

            e = moElement.getChild(MetadataConstants.TAG_LOCATE);
            if(e != null){
                List locateCriteriae = new ArrayList();
                List criteriaList = e.getChildren(MetadataConstants.TAG_CRITERIA);
                Iterator criteriaListItr = criteriaList.iterator();
                while(criteriaListItr.hasNext()){
                    Element elmt = (Element)criteriaListItr.next();
                    String criteria = elmt.getAttributeValue(MetadataConstants.ATTRIB_NAME);
                    locateCriteriae.add(criteria);
                }
                mo.setLocateCriteriae(locateCriteriae);
            }
            
            //Get the key attribute
            List informationElementList = moElement
                    .getChildren(MetadataConstants.TAG_INFORMATION_ELEMENT);
            List keyAttributes = new ArrayList();
            Iterator informationElementListIter = informationElementList
                    .iterator();
            InformationElement ie = null;
            while (informationElementListIter.hasNext()) {
                Element elmt = (Element) informationElementListIter.next();
                ie = new InformationElement();
                String attributeType = elmt
                        .getAttributeValue(MetadataConstants.ATTRIB_TYPE_OF_ATTRIB);
                ie.setAttributeType(attributeType);
                String dataType = elmt
                        .getAttributeValue(MetadataConstants.ATTRIB_TYPE);
                ie.setDataType(dataType);
                ie.setElementName(elmt
                        .getAttributeValue(MetadataConstants.ATTRIB_NAME));
                if (attributeType
                        .equals(MetadataConstants.TYPE_OF_ATTRIBUTE_KEY)) {
                    keyAttributes.add(ie);
                }
            }

            //Atleast one key attribute has to be present
            if (keyAttributes.size() == 0)
                throw new MetadataParserException(
                        "Atleast one key attribute have to be defined", null,
                        null);
            else
                mo.setKeyAttributes(keyAttributes);

            //Form the state transitions
            e = moElement.getChild(MetadataConstants.TAG_STATUS_TRANSITIONS);
            List transitionElements = e
                    .getChildren(MetadataConstants.TAG_TRANSITION);
            Map transitions = new HashMap();
            Iterator transitionElementsIter = transitionElements.iterator();
            while (transitionElementsIter.hasNext()) {
                Element elmt = (Element) transitionElementsIter.next();
                String name = elmt
                        .getAttributeValue(MetadataConstants.ATTRIB_NAME);
                String from = elmt
                        .getAttributeValue(MetadataConstants.ATTRIB_FROM);
                String to = elmt.getAttributeValue(MetadataConstants.ATTRIB_TO);
                String taskName = elmt.getAttributeValue(MetadataConstants.ATTRIB_TASKNAME);
                String override=elmt
                .getAttributeValue(MetadataConstants.ATTRIB_OVERRIDE);
                //enableLog
                String enableLog=elmt.getAttributeValue(MetadataConstants.ATTRIB_ENABLELOG);
                boolean log=false;
                if(enableLog!=null&&!"".equalsIgnoreCase(enableLog)){
                	if(enableLog.equalsIgnoreCase("Y")){
                		log=true;
                	}
                	
                }
                List overrideList=new ArrayList();
                if(override!=null&&!"".equalsIgnoreCase(override)){
                    StringTokenizer stringTokenizer=new StringTokenizer(override,"/");
                    while(stringTokenizer.hasMoreTokens()){
                        overrideList.add(stringTokenizer.nextToken());
                    }
                }
                if (name == null)
                    throw new MetadataParserException(
                            "Name of the transition need to be specified",
                            null, null);
                if (from != null || to != null)
                    throw new MetadataParserException(
                            "Only name of the transition specified in the universe should be specified",
                            null, null);
                Transition transition = (Transition) transitionUniverse
                        .get(name);
                if(null == taskName){
                	taskName = transition.getTaskName();
                }
                Transition tn = null;
                if(overrideList.isEmpty()){
                  tn=new Transition(transition.getFromStatus(), transition.getToStatus(),
                  		taskName,log);
                    
                }else{
                    tn=new Transition(transition.getFromStatus(), transition.getToStatus(),
                      		taskName,overrideList,log);
                }
                
                
                transitions.put(name, tn);
            }
            mo.setTransitions(transitions);
            e = moElement.getChild(MetadataConstants.TAG_SECURITY);
            List activities = e
                    .getChildren(MetadataConstants.TAG_ACTIVITY);
            Map activityMap = new HashMap();
            Iterator activityIter = activities.iterator();
            while (activityIter.hasNext()) {
            	Element elmt = (Element) activityIter.next();
            	   String name = elmt
                   .getAttributeValue(MetadataConstants.ATTRIB_NAME);
            	   String taskName = elmt
                   .getAttributeValue(MetadataConstants.ATTRIB_TASKNAME);
            	   activityMap.put(name,new Activity(name,taskName));
            	   if (name == null || taskName==null)
                    throw new MetadataParserException(
                            "Name or the Task Name  of the activity need to be specified",
                            null, null);
            }
            mo.setActivities(activityMap);
            return mo;
        } else
            return null;
    }

    /**
     * Parse the metadata definition XML to return the Document object Do xsd
     * validation for the XML
     * 
     * @param metaObjectDefinitionFileName
     * @return
     * @throws MetadataParserException
     */
    private Document retrieveDocument(String metaObjectDefinitionFileName)
            throws MetadataParserException {
        SAXBuilder builder = new SAXBuilder(MetadataConstants.SAX_PARSER_CLASS,
                true);
        builder.setFeature(MetadataConstants.PARSER_VALIIDATION_FEATURE, true);
        URL url = getClass().getResource(MetadataConstants.XSD_LOCATION);

        if (url == null) {
            throw new MetadataParserException("The xsd file cannot be found.",
                    null, null);
        }
        Logger.logInfo("Metadata XSD Location -->" + url.toString());
        builder.setProperty(MetadataConstants.PARSER_XSD_LOC_PPRTY, url
                .toString());

        Document doc = null;
        try {
            Logger.logInfo("Metadata XML Location -->"
                    + metaObjectDefinitionFileName);
            InputStream xmlStream = getClass().getResourceAsStream(
                    metaObjectDefinitionFileName);
            if (xmlStream == null) {
                throw new MetadataParserException("The XML file location "
                        + metaObjectDefinitionFileName
                        + " may not be available in the CLASSPATH", null, null);
            } else {
                doc = builder.build(xmlStream);
            }
        } catch (JDOMException e) {
            throw new MetadataParserException(
                    "Parsing the XML Failed.XML may not be wellformed ", null,
                    e);
        } catch (IOException e) {
            throw new MetadataParserException(
                    "IO Error!!.Probably the file specified can't be found",
                    null, e);
        }
        return doc;
    }
    
    /**
     * Create the metaObject corresponding to the given locate criteria
     * 
     * @param locateCriteria
     * @param metadataDefinitionFile
     * @return
     * @throws MetadataParserException
     */
    public MetaObject createMetaObjectForLocateCriteria(String locateCriteria,
            String metadataDefinitionFile) throws MetadataParserException {
        Document document = retrieveDocument(metadataDefinitionFile);
        Element root = document.getRootElement();
        if (transitionUniverse == null) {
            populateValidTransitions(root);
        }
        MetaObject metaObject = retrieveMetaObjectForLocateCriteria(root, locateCriteria);
        return metaObject;
    }    

    /**
     * @param root
     * @param locateCriteria
     * @return
     * @throws MetadataParserException
     */
    private MetaObject retrieveMetaObjectForLocateCriteria(Element root,
            String locateCriteria) throws MetadataParserException {
        List metaObjectList = root
                .getChildren(MetadataConstants.TAG_METAOBJECT);
        MetaObject metaObject = null;
        if (metaObjectList != null) {
            Iterator moIt = metaObjectList.iterator();
            Element moElement = null, mo = null;
            boolean isFound = false;
            List params = new ArrayList();
            params.add(locateCriteria);
            while (moIt.hasNext()) {
                moElement = (Element) moIt.next();
                if (moElement != null) {
                    Element e = moElement.getChild(MetadataConstants.TAG_LOCATE);
                    if(e == null)
                        continue;
                    List criteriaElements = e.getChildren(MetadataConstants.TAG_CRITERIA);
                    Iterator criteriaElementsItr = criteriaElements.iterator();
                    while(criteriaElementsItr.hasNext()){
                        Element elmt = (Element)criteriaElementsItr.next();
                        String locateCriteriaTemp = elmt.getAttributeValue(MetadataConstants.ATTRIB_NAME);
	                    if(locateCriteriaTemp.equals(locateCriteria))
	                        if(!isFound){
	                            isFound = true;
	                            mo = moElement;
	                        }
	                        else
	                            throw new MetadataParserException(
	                                    "Same locate criteria present for more than one metaobject definition",
	                                    params, null);
                    }
                } else {
                    throw new MetadataParserException(
                            "Metadata definition invalid", null,
                            null);
                }
            }
            if(isFound){
                metaObject = retrieveBusinessObjectMetaData(mo);
            }else{
                throw new MetadataParserException("No metaObject definition present for the given locate criteria", params, null);
            }
        }
        return metaObject;
    }

    //for test
    public static void main(String args[])throws Exception {

       ObjectFactory metaObjectFactory = ObjectFactory
                .getFactory(ObjectFactory.METAOBJECT);
        MetaObject mo = null;
        try {
            //mo = ((MetaObjectFactory) metaObjectFactory)
            //        .getMetaObject(new BO2());
            mo = ((MetaObjectFactory)metaObjectFactory).getMetaObject(new BO2LocateCriteria());

            mo = ((MetaObjectFactory)metaObjectFactory).getMetaObject(new BO1LocateCriteria());

        } catch (MetadataParserException e) {
            e.printStackTrace();
        }
        BO2 bo2 = new BO2();
        bo2.setStatus("BUILDING");



    }
    

}

//test BO

class BO2 extends BusinessObject{
    private String boKey1;

    private String boKey2;

    public boolean equals(BusinessObject object) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }
    
}

class BO1 extends BusinessObject{
    private String boKey1;

    private String boKey2;

    public boolean equals(BusinessObject object) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }
}

class BO2LocateCriteria extends LocateCriteria{
    private String testArgument;
}

class BO1LocateCriteria extends LocateCriteria{
    private String testArgument;
}