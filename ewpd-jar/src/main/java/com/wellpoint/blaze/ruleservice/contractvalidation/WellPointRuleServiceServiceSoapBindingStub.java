/**
 * WellPointRuleServiceServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze.ruleservice.contractvalidation;

public class WellPointRuleServiceServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF {
    public WellPointRuleServiceServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[2];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = java.lang.Object[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "ArrayOfXSDAnyType");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "anyType");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "anyType");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.AdminOption.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "AdminOption");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Benefit.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Benefit");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.BenefitComponent.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "BenefitComponent");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.BenefitLines.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "BenefitLines");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Contract.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Contract");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.MembershipInformation.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "MembershipInformation");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Message.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Message");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Messages.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Messages");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Note.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Note");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.PricingInformation.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "PricingInformation");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Product.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Product");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Question.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Question");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.Rule.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Rule");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.RuleCategory.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "RuleCategory");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = com.wellpoint.wpd.common.contract.ws.model.SuperProcessSteps.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "SuperProcessSteps");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokeRuleCategoryEntryPointOperation0;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeRuleCategoryEntryPointReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://xml.apache.org/xml-soap", "Vector"), java.util.Vector.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokeRuleCategoryEntryPointOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeRuleCategoryEntryPoint", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPoint"), _params0, _returnDesc0, _faults0, "");
        if (_invokeRuleCategoryEntryPointOperation0 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("targetNamespace","http://contractvalidation.ruleservice.blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "WellPointRuleServiceIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPointResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleCategoryEntryPointRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("outputName","invokeRuleCategoryEntryPointResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("inputName","invokeRuleCategoryEntryPointRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleCategoryEntryPointOperation0).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeRuleCategoryEntryPointOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeRuleCategoryEntryPointOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokeRuleCategoryEntryPointIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokeRuleCategoryEntryPointInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokeRuleCategoryEntryPointIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(WellPointRuleServiceServiceSoapBindingStub._invokeRuleCategoryEntryPointOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokeRuleCategoryEntryPointIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.util.Vector invokeRuleCategoryEntryPoint() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokeRuleCategoryEntryPointInvoke0(new java.lang.Object[] {}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.util.Vector) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.util.Vector) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.util.Vector.class);
        }
    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokeRuleExecutionEntryPointOperation1;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Contract"), com.wellpoint.wpd.common.contract.ws.model.Contract.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_1_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://xml.apache.org/xml-soap", "Vector"), java.util.Vector.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeRuleExecutionEntryPointReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://model.ws.contract.common.wpd.wellpoint.com", "Messages"), com.wellpoint.wpd.common.contract.ws.model.Messages.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokeRuleExecutionEntryPointOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeRuleExecutionEntryPoint", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPoint"), _params1, _returnDesc1, _faults1, "");
        if (_invokeRuleExecutionEntryPointOperation1 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("targetNamespace","http://contractvalidation.ruleservice.blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "WellPointRuleServiceIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPointResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://contractvalidation.ruleservice.blaze.wellpoint.com", "invokeRuleExecutionEntryPointRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("outputName","invokeRuleExecutionEntryPointResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("inputName","invokeRuleExecutionEntryPointRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeRuleExecutionEntryPointOperation1).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeRuleExecutionEntryPointOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeRuleExecutionEntryPointOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokeRuleExecutionEntryPointIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokeRuleExecutionEntryPointInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokeRuleExecutionEntryPointIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(WellPointRuleServiceServiceSoapBindingStub._invokeRuleExecutionEntryPointOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokeRuleExecutionEntryPointIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public com.wellpoint.wpd.common.contract.ws.model.Messages invokeRuleExecutionEntryPoint(com.wellpoint.wpd.common.contract.ws.model.Contract arg_0_1, java.util.Vector arg_1_1) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokeRuleExecutionEntryPointInvoke1(new java.lang.Object[] {arg_0_1, arg_1_1}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (com.wellpoint.wpd.common.contract.ws.model.Messages) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (com.wellpoint.wpd.common.contract.ws.model.Messages) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), com.wellpoint.wpd.common.contract.ws.model.Messages.class);
        }
    }

}
