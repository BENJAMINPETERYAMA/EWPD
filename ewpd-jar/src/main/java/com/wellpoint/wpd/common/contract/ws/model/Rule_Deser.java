/**
 * Rule_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Rule_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Rule_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.Rule();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_92) {
          ((Rule)value).setRuleType(strValue);
          return true;}
        else if (qName==QName_0_93) {
          ((Rule)value).setEwpdRuleId(strValue);
          return true;}
        else if (qName==QName_0_94) {
          ((Rule)value).setRuleId(strValue);
          return true;}
        else if (qName==QName_0_49) {
          ((Rule)value).setDescription(strValue);
          return true;}
        else if (qName==QName_0_62) {
          ((Rule)value).setPva(strValue);
          return true;}
        else if (qName==QName_0_95) {
          ((Rule)value).setGroupIndicator(strValue);
          return true;}
        else if (qName==QName_0_96) {
          ((Rule)value).setValue(strValue);
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
