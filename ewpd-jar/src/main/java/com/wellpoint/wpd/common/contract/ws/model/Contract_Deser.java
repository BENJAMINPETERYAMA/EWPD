/**
 * Contract_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Contract_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Contract_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.Contract();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_1) {
          ((Contract)value).setBaseContractEffectiveDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_2) {
          ((Contract)value).setBaseContractId(strValue);
          return true;}
        else if (qName==QName_0_3) {
          ((Contract)value).setBenefitPlan(strValue);
          return true;}
        else if (qName==QName_0_4) {
          ((Contract)value).setBrandName(strValue);
          return true;}
        else if (qName==QName_0_7) {
          ((Contract)value).setCobAdjudicationIndcator(strValue);
          return true;}
        else if (qName==QName_0_8) {
          ((Contract)value).setComplianceStatus(strValue);
          return true;}
        else if (qName==QName_0_9) {
          ((Contract)value).setContractId(strValue);
          return true;}
        else if (qName==QName_0_10) {
          ((Contract)value).setContractTermDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_11) {
          ((Contract)value).setContractType(strValue);
          return true;}
        else if (qName==QName_0_12) {
          ((Contract)value).setCorporatePlanCode(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((Contract)value).setEffectiveDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_14) {
          ((Contract)value).setExpiryDate(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseDateTimeToCalendar(strValue));
          return true;}
        else if (qName==QName_0_15) {
          ((Contract)value).setGroupSize(strValue);
          return true;}
        else if (qName==QName_0_16) {
          ((Contract)value).setItsAdjudicationIndicator(strValue);
          return true;}
        else if (qName==QName_0_18) {
          ((Contract)value).setMedicareAdjudicationIndicator(strValue);
          return true;}
        else if (qName==QName_0_20) {
          ((Contract)value).setMultiPlanIndicator(strValue);
          return true;}
        else if (qName==QName_0_22) {
          ((Contract)value).setPerformanceGuarentee(strValue);
          return true;}
        else if (qName==QName_0_25) {
          ((Contract)value).setProductCode(strValue);
          return true;}
        else if (qName==QName_0_26) {
          ((Contract)value).setProductFamily(strValue);
          return true;}
        else if (qName==QName_0_27) {
          ((Contract)value).setProductNameCode(strValue);
          return true;}
        else if (qName==QName_0_28) {
          ((Contract)value).setRegulatoryAgency(strValue);
          return true;}
        else if (qName==QName_0_30) {
          ((Contract)value).setSalesMarketDate(strValue);
          return true;}
        else if (qName==QName_0_31) {
          ((Contract)value).setStandardPlanCode(strValue);
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
        if (qName==QName_0_0) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setAdminOptions(array);
          } else { 
            ((Contract)value).setAdminOptions((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_5) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setBusinessEntity(array);
          } else { 
            ((Contract)value).setBusinessEntity((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_6) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setBusinessGroup(array);
          } else { 
            ((Contract)value).setBusinessGroup((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_17) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setLineOfBusiness(array);
          } else { 
            ((Contract)value).setLineOfBusiness((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_19) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setMembershipInformations(array);
          } else { 
            ((Contract)value).setMembershipInformations((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_21) {
          ((Contract)value).setNote((com.wellpoint.wpd.common.contract.ws.model.Note)objValue);
          return true;}
        else if (qName==QName_0_23) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setPricingInformation(array);
          } else { 
            ((Contract)value).setPricingInformation((java.lang.Object[])objValue);}
          return true;}
        else if (qName==QName_0_24) {
          ((Contract)value).setProduct((com.wellpoint.wpd.common.contract.ws.model.Product)objValue);
          return true;}
        else if (qName==QName_0_29) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((Contract)value).setRules(array);
          } else { 
            ((Contract)value).setRules((java.lang.Object[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessGroup");
    private final static javax.xml.namespace.QName QName_0_26 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productFamily");
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "groupSize");
    private final static javax.xml.namespace.QName QName_0_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "product");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "note");
    private final static javax.xml.namespace.QName QName_0_20 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "multiPlanIndicator");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "effectiveDate");
    private final static javax.xml.namespace.QName QName_0_4 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "brandName");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "adminOptions");
    private final static javax.xml.namespace.QName QName_0_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "benefitPlan");
    private final static javax.xml.namespace.QName QName_0_31 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "standardPlanCode");
    private final static javax.xml.namespace.QName QName_0_25 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productCode");
    private final static javax.xml.namespace.QName QName_0_7 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cobAdjudicationIndcator");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "pricingInformation");
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lineOfBusiness");
    private final static javax.xml.namespace.QName QName_0_16 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "itsAdjudicationIndicator");
    private final static javax.xml.namespace.QName QName_0_18 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "medicareAdjudicationIndicator");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "businessEntity");
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "baseContractEffectiveDate");
    private final static javax.xml.namespace.QName QName_0_29 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "rules");
    private final static javax.xml.namespace.QName QName_0_27 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "productNameCode");
    private final static javax.xml.namespace.QName QName_0_30 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "salesMarketDate");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "membershipInformations");
    private final static javax.xml.namespace.QName QName_0_8 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "complianceStatus");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "baseContractId");
    private final static javax.xml.namespace.QName QName_0_12 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "corporatePlanCode");
    private final static javax.xml.namespace.QName QName_0_9 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "contractId");
    private final static javax.xml.namespace.QName QName_0_28 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "regulatoryAgency");
    private final static javax.xml.namespace.QName QName_0_11 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "contractType");
    private final static javax.xml.namespace.QName QName_0_10 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "contractTermDate");
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "performanceGuarentee");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "expiryDate");
}
