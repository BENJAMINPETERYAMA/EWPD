/**
 * Contract_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Contract_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(Contract.class);

    static {
        typeDesc.setOption("buildNum","o0444.10");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("adminOptions");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "adminOptions"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("baseContractEffectiveDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "baseContractEffectiveDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("baseContractId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "baseContractId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitPlan");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitPlan"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("brandName");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "brandName"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("businessEntity");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "businessEntity"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("businessGroup");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "businessGroup"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("cobAdjudicationIndcator");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "cobAdjudicationIndcator"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("complianceStatus");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "complianceStatus"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("contractId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "contractId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("contractTermDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "contractTermDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("contractType");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "contractType"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("corporatePlanCode");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "corporatePlanCode"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("effectiveDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "effectiveDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("expiryDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "expiryDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("groupSize");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "groupSize"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("itsAdjudicationIndicator");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "itsAdjudicationIndicator"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("lineOfBusiness");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "lineOfBusiness"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("medicareAdjudicationIndicator");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "medicareAdjudicationIndicator"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("membershipInformations");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "membershipInformations"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("multiPlanIndicator");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "multiPlanIndicator"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("note");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "note"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Note"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("performanceGuarentee");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "performanceGuarentee"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("pricingInformation");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "pricingInformation"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("product");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "product"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Product"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("productCode");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "productCode"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("productFamily");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "productFamily"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("productNameCode");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "productNameCode"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("regulatoryAgency");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "regulatoryAgency"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("rules");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "rules"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("salesMarketDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "salesMarketDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("standardPlanCode");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "standardPlanCode"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static com.ibm.ws.webservices.engine.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new Contract_Ser(
            javaType, xmlType, typeDesc);
    };

    /**
     * Get Custom Deserializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new Contract_Deser(
            javaType, xmlType, typeDesc);
    };

}
