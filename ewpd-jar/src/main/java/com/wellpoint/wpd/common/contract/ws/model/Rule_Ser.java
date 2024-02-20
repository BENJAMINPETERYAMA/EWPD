/**
 * Rule_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Rule_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public Rule_Ser(
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
        Rule bean = (Rule) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_92;
          propValue = bean.getRuleType();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_93;
          propValue = bean.getEwpdRuleId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_94;
          propValue = bean.getRuleId();
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
          propQName = QName_0_62;
          propValue = bean.getPva();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_95;
          propValue = bean.getGroupIndicator();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_96;
          propValue = bean.getValue();
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
    private final static javax.xml.namespace.QName QName_0_93 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ewpdRuleId");
    private final static javax.xml.namespace.QName QName_0_49 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "description");
    private final static javax.xml.namespace.QName QName_0_94 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ruleId");
    private final static javax.xml.namespace.QName QName_2_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
    private final static javax.xml.namespace.QName QName_0_96 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "value");
    private final static javax.xml.namespace.QName QName_0_95 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupIndicator");
    private final static javax.xml.namespace.QName QName_0_62 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "pva");
    private final static javax.xml.namespace.QName QName_0_92 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ruleType");
}
