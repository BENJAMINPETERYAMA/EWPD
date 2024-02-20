/**
 * Benefit_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class Benefit_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(Benefit.class);

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
        field.setFieldName("term");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "term"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("qualifier");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "qualifier"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("pva");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "pva"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("dataType");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "dataType"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("notes");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "notes"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitLines");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitLines"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("adminOptions");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "adminOptions"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("superProcessSteps");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "superProcessSteps"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitName");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitName"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitMeaning");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitMeaning"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitType");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitType"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitCategory");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitCategory"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("description");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "description"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("benefitRuleId");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "benefitRuleId"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("version");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "version"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("administration");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "administration"));
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
          new Benefit_Ser(
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
          new Benefit_Deser(
            javaType, xmlType, typeDesc);
    };

}
