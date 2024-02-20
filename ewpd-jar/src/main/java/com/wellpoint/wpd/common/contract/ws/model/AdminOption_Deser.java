/**
 * AdminOption_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class AdminOption_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public AdminOption_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.AdminOption();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_57) {
          ((AdminOption)value).setAdminLevel(strValue);
          return true;}
        else if (qName==QName_0_59) {
          ((AdminOption)value).setAdminOptionName(strValue);
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
        if (qName==QName_0_58) {
          if (objValue instanceof java.util.List) {
            java.lang.Object[] array = new java.lang.Object[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((AdminOption)value).setQuestions(array);
          } else { 
            ((AdminOption)value).setQuestions((java.lang.Object[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_59 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "adminOptionName");
    private final static javax.xml.namespace.QName QName_0_58 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "questions");
    private final static javax.xml.namespace.QName QName_0_57 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "adminLevel");
}
