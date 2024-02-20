/**
 * XML_RSServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze;

public class XML_RSServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.wellpoint.blaze.XML_RSIF {
    public XML_RSServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
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
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[4];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokeselectComponentsOperation0;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_0"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectComponentsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokeselectComponentsOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectComponents", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponents"), _params0, _returnDesc0, _faults0, "");
        if (_invokeselectComponentsOperation0 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponentsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectComponentsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("outputName","invokeselectComponentsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("inputName","invokeselectComponentsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectComponentsOperation0).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectComponentsOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectComponentsOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokeselectComponentsIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokeselectComponentsInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokeselectComponentsIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(XML_RSServiceSoapBindingStub._invokeselectComponentsOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokeselectComponentsIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String invokeselectComponents(java.lang.String arg_0_0) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokeselectComponentsInvoke0(new java.lang.Object[] {arg_0_0}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokeselectBenefitsOperation1;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectBenefitsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokeselectBenefitsOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectBenefits", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefits"), _params1, _returnDesc1, _faults1, "");
        if (_invokeselectBenefitsOperation1 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefitsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectBenefitsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("outputName","invokeselectBenefitsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("inputName","invokeselectBenefitsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectBenefitsOperation1).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectBenefitsOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectBenefitsOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokeselectBenefitsIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokeselectBenefitsInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokeselectBenefitsIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(XML_RSServiceSoapBindingStub._invokeselectBenefitsOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokeselectBenefitsIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String invokeselectBenefits(java.lang.String arg_0_1) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokeselectBenefitsInvoke1(new java.lang.Object[] {arg_0_1}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokeselectAccidentRiderBenefitsOperation2;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "arg_0_2"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokeselectAccidentRiderBenefitsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokeselectAccidentRiderBenefitsOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokeselectAccidentRiderBenefits", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefits"), _params2, _returnDesc2, _faults2, "");
        if (_invokeselectAccidentRiderBenefitsOperation2 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefitsResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokeselectAccidentRiderBenefitsRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("outputName","invokeselectAccidentRiderBenefitsResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("inputName","invokeselectAccidentRiderBenefitsRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokeselectAccidentRiderBenefitsOperation2).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectAccidentRiderBenefitsOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokeselectAccidentRiderBenefitsOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokeselectAccidentRiderBenefitsIndex2 = 2;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokeselectAccidentRiderBenefitsInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokeselectAccidentRiderBenefitsIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(XML_RSServiceSoapBindingStub._invokeselectAccidentRiderBenefitsOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokeselectAccidentRiderBenefitsIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String invokeselectAccidentRiderBenefits(java.lang.String arg_0_2) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokeselectAccidentRiderBenefitsInvoke2(new java.lang.Object[] {arg_0_2}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static final com.ibm.ws.webservices.engine.description.OperationDesc _invokegetRuleInfoOperation3;
    static {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params3 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc3 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "invokegetRuleInfoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults3 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _invokegetRuleInfoOperation3 = new com.ibm.ws.webservices.engine.description.OperationDesc("invokegetRuleInfo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfo"), _params3, _returnDesc3, _faults3, "");
        if (_invokegetRuleInfoOperation3 instanceof com.ibm.ws.webservices.engine.configurable.Configurable) {
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("targetNamespace","http://blaze.wellpoint.com");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "XML_RSIF"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfoResponse"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://blaze.wellpoint.com", "invokegetRuleInfoRequest"));
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("outputName","invokegetRuleInfoResponse");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("inputName","invokegetRuleInfoRequest");
         ((com.ibm.ws.webservices.engine.configurable.Configurable)_invokegetRuleInfoOperation3).setOption("buildNum","o0444.10");
        }
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokegetRuleInfoOperation3.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        // WAS 6.0 Migration changes - WAS 7 IBM jar has enumtype inplace of enum
        _invokegetRuleInfoOperation3.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
    }

    private int _invokegetRuleInfoIndex3 = 3;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getinvokegetRuleInfoInvoke3(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_invokegetRuleInfoIndex3];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(XML_RSServiceSoapBindingStub._invokegetRuleInfoOperation3);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_invokegetRuleInfoIndex3] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String invokegetRuleInfo() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getinvokegetRuleInfoInvoke3(new java.lang.Object[] {}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

}
