/**
 * BenefitComponent_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class BenefitComponent_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public BenefitComponent_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.BenefitComponent();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_74) {
          ((BenefitComponent)value).setBenefitComponentName(strValue);
          return true;}
        else if (qName==QName_0_75) {
          ((BenefitComponent)value).setBenefitComponentType(strValue);
          return true;}
        else if (qName==QName_0_76) {
          ((BenefitComponent)value).setBenefitComponentDescription(strValue);
          return true;}
        else if (qName==QName_0_71) {
          ((BenefitComponent)value).setBenefitRuleId(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((BenefitComponent)value).setEffectiveDate(strValue);
          return true;}
        else if (qName==QName_0_14) {
          ((BenefitComponent)value).setExpiryDate(strValue);
          return true;}
        else if (qName==QName_0_46) {
          ((BenefitComponent)value).setVersion(strValue);
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
        if (qName==QName_0_17) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((BenefitComponent)value).setLineOfBusiness(array);
          } else { 
            ((BenefitComponent)value).setLineOfBusiness((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_5) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((BenefitComponent)value).setBusinessEntity(array);
          } else { 
            ((BenefitComponent)value).setBusinessEntity((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_6) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((BenefitComponent)value).setBusinessGroup(array);
          } else { 
            ((BenefitComponent)value).setBusinessGroup((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_73) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((BenefitComponent)value).setBenefits(array);
          } else { 
            ((BenefitComponent)value).setBenefits((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_64) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((BenefitComponent)value).setNotes(array);
          } else { 
            ((BenefitComponent)value).setNotes((java.lang.Object[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_75 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitComponentType");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "expiryDate");
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
