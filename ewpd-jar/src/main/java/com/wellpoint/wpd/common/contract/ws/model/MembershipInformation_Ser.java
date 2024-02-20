/**
 * MembershipInformation_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class MembershipInformation_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public MembershipInformation_Ser(
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
        MembershipInformation bean = (MembershipInformation) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_82;
          propValue = bean.getCaseNumberName();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_83;
          propValue = bean.getCaseeffectiveCancelDate();
          serializeChild(propQName, null, 
              propValue, 
              QName_2_33,
              true,null,context);
          propQName = QName_0_84;
          propValue = bean.getCaseHQState();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_85;
          propValue = bean.getGroupNumberName();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_86;
          propValue = bean.getFundingArrangement();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_87;
          propValue = bean.getMbuCodeValue();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_88;
          propValue = bean.getRerateCodeValue();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_89;
          propValue = bean.getGroupeffectiveCancelDate();
          serializeChild(propQName, null, 
              propValue, 
              QName_2_33,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_0_89 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupeffectiveCancelDate");
    private final static javax.xml.namespace.QName QName_0_85 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupNumberName");
    private final static javax.xml.namespace.QName QName_0_84 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseHQState");
    private final static javax.xml.namespace.QName QName_0_82 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseNumberName");
    private final static javax.xml.namespace.QName QName_0_86 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "fundingArrangement");
    private final static javax.xml.namespace.QName QName_2_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
    private final static javax.xml.namespace.QName QName_0_88 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "rerateCodeValue");
    private final static javax.xml.namespace.QName QName_0_83 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseeffectiveCancelDate");
    private final static javax.xml.namespace.QName QName_0_87 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "mbuCodeValue");
    private final static javax.xml.namespace.QName QName_2_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "dateTime");
}
