/**
 * Benefit_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Benefit_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Benefit_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.Benefit();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_67) {
          ((Benefit)value).setBenefitName(strValue);
          return true;}
        else if (qName==QName_0_68) {
          ((Benefit)value).setBenefitMeaning(strValue);
          return true;}
        else if (qName==QName_0_69) {
          ((Benefit)value).setBenefitType(strValue);
          return true;}
        else if (qName==QName_0_70) {
          ((Benefit)value).setBenefitCategory(strValue);
          return true;}
        else if (qName==QName_0_49) {
          ((Benefit)value).setDescription(strValue);
          return true;}
        else if (qName==QName_0_71) {
          ((Benefit)value).setBenefitRuleId(strValue);
          return true;}
        else if (qName==QName_0_46) {
          ((Benefit)value).setVersion(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseInteger(strValue));
          return true;}
        else if (qName==QName_0_72) {
          ((Benefit)value).setAdministration(strValue);
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
            ((Benefit)value).setLineOfBusiness(array);
          } else { 
            ((Benefit)value).setLineOfBusiness((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_5) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setBusinessEntity(array);
          } else { 
            ((Benefit)value).setBusinessEntity((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_6) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setBusinessGroup(array);
          } else { 
            ((Benefit)value).setBusinessGroup((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_60) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setTerm(array);
          } else { 
            ((Benefit)value).setTerm((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_61) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setQualifier(array);
          } else { 
            ((Benefit)value).setQualifier((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_62) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setPva(array);
          } else { 
            ((Benefit)value).setPva((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_63) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setDataType(array);
          } else { 
            ((Benefit)value).setDataType((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_64) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setNotes(array);
          } else { 
            ((Benefit)value).setNotes((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_65) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setBenefitLines(array);
          } else { 
            ((Benefit)value).setBenefitLines((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_0) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setAdminOptions(array);
          } else { 
            ((Benefit)value).setAdminOptions((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_66) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Benefit)value).setSuperProcessSteps(array);
          } else { 
            ((Benefit)value).setSuperProcessSteps((java.lang.Object[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
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
    private final static javax.xml.namespace.QName QName_0_64 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "notes");
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
