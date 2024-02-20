/**
 * Product_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Product_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Product_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.Product();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_13) {
          ((Product)value).setEffectiveDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_14) {
          ((Product)value).setExpiryDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_26) {
          ((Product)value).setProductFamily(strValue);
          return true;}
        else if (qName==QName_0_41) {
          ((Product)value).setProductName(strValue);
          return true;}
        else if (qName==QName_0_42) {
          ((Product)value).setProductStructureDescription(strValue);
          return true;}
        else if (qName==QName_0_43) {
          ((Product)value).setProductStructureName(strValue);
          return true;}
        else if (qName==QName_0_44) {
          ((Product)value).setProductStructureVersion(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseint(strValue));
          return true;}
        else if (qName==QName_0_45) {
          ((Product)value).setProductType(strValue);
          return true;}
        else if (qName==QName_0_46) {
          ((Product)value).setVersion(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseint(strValue));
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
        if (qName==QName_0_40) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Product)value).setBenefitComponents(array);
          } else { 
            ((Product)value).setBenefitComponents((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_5) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Product)value).setBusinessEntity(array);
          } else { 
            ((Product)value).setBusinessEntity((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_6) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Product)value).setBusinessGroup(array);
          } else { 
            ((Product)value).setBusinessGroup((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_17) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Product)value).setLineOfBusiness(array);
          } else { 
            ((Product)value).setLineOfBusiness((java.lang.Object[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_42 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productStructureDescription");
    private final static javax.xml.namespace.QName QName_0_40 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitComponents");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "expiryDate");
    private final static javax.xml.namespace.QName QName_0_43 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productStructureName");
    private final static javax.xml.namespace.QName QName_0_45 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productType");
    private final static javax.xml.namespace.QName QName_0_44 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productStructureVersion");
    private final static javax.xml.namespace.QName QName_0_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "version");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "effectiveDate");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessGroup");
    private final static javax.xml.namespace.QName QName_0_41 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productName");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessEntity");
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lineOfBusiness");
    private final static javax.xml.namespace.QName QName_0_26 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productFamily");
}
