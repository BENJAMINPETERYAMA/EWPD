/**
 * MembershipInformation_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class MembershipInformation_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public MembershipInformation_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.MembershipInformation();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_82) {
          ((MembershipInformation)value).setCaseNumberName(strValue);
          return true;}
        else if (qName==QName_0_83) {
          ((MembershipInformation)value).setCaseeffectiveCancelDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_84) {
          ((MembershipInformation)value).setCaseHQState(strValue);
          return true;}
        else if (qName==QName_0_85) {
          ((MembershipInformation)value).setGroupNumberName(strValue);
          return true;}
        else if (qName==QName_0_86) {
          ((MembershipInformation)value).setFundingArrangement(strValue);
          return true;}
        else if (qName==QName_0_87) {
          ((MembershipInformation)value).setMbuCodeValue(strValue);
          return true;}
        else if (qName==QName_0_88) {
          ((MembershipInformation)value).setRerateCodeValue(strValue);
          return true;}
        else if (qName==QName_0_89) {
          ((MembershipInformation)value).setGroupeffectiveCancelDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
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
    private final static javax.xml.namespace.QName QName_0_84 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseHQState");
    private final static javax.xml.namespace.QName QName_0_87 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "mbuCodeValue");
    private final static javax.xml.namespace.QName QName_0_86 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "fundingArrangement");
    private final static javax.xml.namespace.QName QName_0_83 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseeffectiveCancelDate");
    private final static javax.xml.namespace.QName QName_0_85 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupNumberName");
    private final static javax.xml.namespace.QName QName_0_89 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupeffectiveCancelDate");
    private final static javax.xml.namespace.QName QName_0_82 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "caseNumberName");
    private final static javax.xml.namespace.QName QName_0_88 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "rerateCodeValue");
}
