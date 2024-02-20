/**
 * Note_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Note_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public Note_Ser(
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
        Note bean = (Note) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_38;
          propValue = bean.getNoteId();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_34,
              true,null,context);
          }
          propQName = QName_0_39;
          propValue = bean.getNoteName();
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
    private final static javax.xml.namespace.QName QName_0_39 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "noteName");
    private final static javax.xml.namespace.QName QName_0_38 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "noteId");
    private final static javax.xml.namespace.QName QName_2_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
}
