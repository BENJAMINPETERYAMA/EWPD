/**
 * BenefitComponent_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class BenefitComponent_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(BenefitComponent.class);

    static {
        typeDesc.setOption("buildNum","o0444.10");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("lineOfBusiness");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "lineOfBusiness"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
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
        field.setFieldName("benefits");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefits"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("notes");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "notes"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitComponentName");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitComponentName"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitComponentType");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitComponentType"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitComponentDescription");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitComponentDescription"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitRuleId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitRuleId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("effectiveDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "effectiveDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("expiryDate");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "expiryDate"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("version");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "version"));
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
          new BenefitComponent_Ser(
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
          new BenefitComponent_Deser(
            javaType, xmlType, typeDesc);
    };

}
