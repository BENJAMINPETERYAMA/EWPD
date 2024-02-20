/**
 * SuperProcessSteps_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.wpd.common.contract.ws.model;

public class SuperProcessSteps_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public SuperProcessSteps_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_97) {
          ((SuperProcessSteps)value).setSuperProcessStep(strValue);
          return true;}
        else if (qName==QName_0_98) {
          ((SuperProcessSteps)value).setAdminMethod(strValue);
          return true;}
        else if (qName==QName_0_78) {
          ((SuperProcessSteps)value).setReference(strValue);
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
    private final static javax.xml.namespace.QName QName_0_78 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "reference");
    private final static javax.xml.namespace.QName QName_0_97 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "superProcessStep");
    private final static javax.xml.namespace.QName QName_0_98 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "adminMethod");
}
