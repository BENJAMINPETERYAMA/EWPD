/**
 * Message_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Message_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Message_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.Message();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_51) {
          ((Message)value).setRuleCategoryDescription(strValue);
          return true;}
        else if (qName==QName_0_52) {
          ((Message)value).setRuleCategoryName(strValue);
          return true;}
        else if (qName==QName_0_53) {
          ((Message)value).setValidationMessage(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (objValue == null) {
          return true;
        }
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_51 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ruleCategoryDescription");
    private final static javax.xml.namespace.QName QName_0_52 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "ruleCategoryName");
    private final static javax.xml.namespace.QName QName_0_53 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "validationMessage");
}
