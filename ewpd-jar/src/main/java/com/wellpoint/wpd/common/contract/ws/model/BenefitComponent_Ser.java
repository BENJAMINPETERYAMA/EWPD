/**
 * BenefitComponent_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class BenefitComponent_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public BenefitComponent_Ser(
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
        BenefitComponent bean = (BenefitComponent) value;
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
          propQName = QName_0_73;
          propValue = bean.getBenefits();
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
          propQName = QName_0_74;
          propValue = bean.getBenefitComponentName();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_75;
          propValue = bean.getBenefitComponentType();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_76;
          propValue = bean.getBenefitComponentDescription();
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
          propQName = QName_0_13;
          propValue = bean.getEffectiveDate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_14;
          propValue = bean.getExpiryDate();
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
    private final static javax.xml.namespace.QName QName_0_75 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitComponentType");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "expiryDate");
    private final static javax.xml.namespace.QName QName_1_32 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://contractvalidation.ruleservice.blaze.wellpoint.com",
                  "ArrayOfXSDAnyType");
    private final static javax.xml.namespace.QName QName_0_64 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "notes");
    private final static javax.xml.namespace.QName QName_0_74 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitComponentName");
    private final static javax.xml.namespace.QName QName_0_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "version");
    private final static javax.xml.namespace.QName QName_0_71 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitRuleId");
    private final static javax.xml.namespace.QName QName_0_76 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitComponentDescription");
    private final static javax.xml.namespace.QName QName_2_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "effectiveDate");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessGroup");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessEntity");
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lineOfBusiness");
    private final static javax.xml.namespace.QName QName_0_73 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefits");
}
