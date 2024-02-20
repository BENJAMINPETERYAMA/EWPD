/**
 * BenefitLines_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class BenefitLines_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public BenefitLines_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.BenefitLines();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_60) {
          ((BenefitLines)value).setTerm(strValue);
          return true;}
        else if (qName==QName_0_61) {
          ((BenefitLines)value).setQualifier(strValue);
          return true;}
        else if (qName==QName_0_62) {
          ((BenefitLines)value).setPva(strValue);
          return true;}
        else if (qName==QName_0_77) {
          ((BenefitLines)value).setFormat(strValue);
          return true;}
        else if (qName==QName_0_78) {
          ((BenefitLines)value).setReference(strValue);
          return true;}
        else if (qName==QName_0_79) {
          ((BenefitLines)value).setBenefitValue(strValue);
          return true;}
        else if (qName==QName_0_80) {
          ((BenefitLines)value).setBenefitLevelDescription(strValue);
          return true;}
        else if (qName==QName_0_81) {
          ((BenefitLines)value).setLevelId(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseInteger(strValue));
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
        if (qName==QName_0_21) {
          ((BenefitLines)value).setNote((com.wellpoint.wpd.common.contract.ws.model.Note)objValue);
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_62 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "pva");
    private final static javax.xml.namespace.QName QName_0_80 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitLevelDescription");
    private final static javax.xml.namespace.QName QName_0_79 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitValue");
    private final static javax.xml.namespace.QName QName_0_60 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "term");
    private final static javax.xml.namespace.QName QName_0_78 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "reference");
    private final static javax.xml.namespace.QName QName_0_61 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "qualifier");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "note");
    private final static javax.xml.namespace.QName QName_0_81 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "levelId");
    private final static javax.xml.namespace.QName QName_0_77 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "format");
}
