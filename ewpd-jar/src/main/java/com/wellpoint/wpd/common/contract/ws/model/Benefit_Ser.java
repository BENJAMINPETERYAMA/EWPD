/**
 * Benefit_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Benefit_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public Benefit_Ser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    public void serialize(
        javax.xml.namespace.QName name,
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        context.startElement(name, addAttributes(attributes, value, context));
        addElements(value, context);
        context.endElement();
    }
    protected org.xml.sax.Attributes addAttributes(
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        Benefit bean = (Benefit) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_17;
          propValue = bean.getLineOfBusiness();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_5;
          propValue = bean.getBusinessEntity();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_6;
          propValue = bean.getBusinessGroup();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_60;
          propValue = bean.getTerm();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_61;
          propValue = bean.getQualifier();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_62;
          propValue = bean.getPva();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_63;
          propValue = bean.getDataType();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_64;
          propValue = bean.getNotes();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_65;
          propValue = bean.getBenefitLines();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_0;
          propValue = bean.getAdminOptions();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_66;
          propValue = bean.getSuperProcessSteps();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_32,
              true,null,context);
          propQName = QName_0_67;
          propValue = bean.getBenefitName();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_68;
          propValue = bean.getBenefitMeaning();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_69;
          propValue = bean.getBenefitType();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_70;
          propValue = bean.getBenefitCategory();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_49;
          propValue = bean.getDescription();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_71;
          propValue = bean.getBenefitRuleId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_46;
          propValue = bean.getVersion();
          serializeChild(propQName, null, 
              propValue, 
              QName_2_47,
              true,null,context);
          propQName = QName_0_72;
          propValue = bean.getAdministration();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
        }
    }
    private final static javax.xml.namespace.QName QName_0_71 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitRuleId");
    private final static javax.xml.namespace.QName QName_0_62 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "pva");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessGroup");
    private final static javax.xml.namespace.QName QName_0_67 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitName");
    private final static javax.xml.namespace.QName QName_0_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "version");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "adminOptions");
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lineOfBusiness");
    private final static javax.xml.namespace.QName QName_0_69 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitType");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessEntity");
    private final static javax.xml.namespace.QName QName_0_70 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitCategory");
    private final static javax.xml.namespace.QName QName_2_47 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "int");
    private final static javax.xml.namespace.QName QName_0_64 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "notes");
    private final static javax.xml.namespace.QName QName_2_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
    private final static javax.xml.namespace.QName QName_0_68 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitMeaning");
    private final static javax.xml.namespace.QName QName_0_65 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitLines");
    private final static javax.xml.namespace.QName QName_0_60 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "term");
    private final static javax.xml.namespace.QName QName_1_32 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://contractvalidation.ruleservice.blaze.wellpoint.com",
                  "ArrayOfXSDAnyType");
    private final static javax.xml.namespace.QName QName_0_63 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "dataType");
    private final static javax.xml.namespace.QName QName_0_66 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "superProcessSteps");
    private final static javax.xml.namespace.QName QName_0_49 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "description");
    private final static javax.xml.namespace.QName QName_0_61 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "qualifier");
    private final static javax.xml.namespace.QName QName_0_72 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "administration");
}
