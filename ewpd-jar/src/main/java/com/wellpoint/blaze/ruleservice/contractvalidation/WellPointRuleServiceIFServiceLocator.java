/**
 * WellPointRuleServiceIFServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze.ruleservice.contractvalidation;

public class WellPointRuleServiceIFServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFService {

    public WellPointRuleServiceIFServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://contractvalidation.ruleservice.blaze.wellpoint.com",
           "WellPointRuleServiceIFService"));

        context.setLocatorName("com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFServiceLocator");
    }
    
    public WellPointRuleServiceIFServiceLocator(String path) {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://contractvalidation.ruleservice.blaze.wellpoint.com",
           "WellPointRuleServiceIFService"));

        context.setLocatorName("com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFServiceLocator");
        wellPointRuleServiceService_address=path;
    }


    public WellPointRuleServiceIFServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFServiceLocator");
    }

    // Use to get a proxy class for wellPointRuleServiceService
    private java.lang.String wellPointRuleServiceService_address;
//    private final java.lang.String wellPointRuleServiceService_address = "http://10.10.43.135:9080/Rule/services/WellPointRuleServiceService";
    public java.lang.String getWellPointRuleServiceServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return wellPointRuleServiceService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("WellPointRuleServiceService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return wellPointRuleServiceService_address;
        }
    }

    private java.lang.String wellPointRuleServiceServicePortName = "WellPointRuleServiceService";

    // The WSDD port name defaults to the port name.
    private java.lang.String wellPointRuleServiceServiceWSDDPortName = "WellPointRuleServiceService";

    public java.lang.String getWellPointRuleServiceServiceWSDDPortName() {
        return wellPointRuleServiceServiceWSDDPortName;
    }

    public void setWellPointRuleServiceServiceWSDDPortName(java.lang.String name) {
        wellPointRuleServiceServiceWSDDPortName = name;
    }

    public com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF getWellPointRuleServiceService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getWellPointRuleServiceServiceAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getWellPointRuleServiceService(endpoint);
    }

    public com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF getWellPointRuleServiceService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF _stub =
            (com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF) getStub(
                wellPointRuleServiceServicePortName,
                (String) getPort2NamespaceMap().get(wellPointRuleServiceServicePortName),
                com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF.class,
                "com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceServiceSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(wellPointRuleServiceServiceWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF.class.isAssignableFrom(serviceEndpointInterface)) {
                return getWellPointRuleServiceService();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("WellPointRuleServiceService".equals(inputPortName)) {
            return getWellPointRuleServiceService();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        wellPointRuleServiceServiceWSDDPortName = prefix + "/" + wellPointRuleServiceServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return super.getServiceName();
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "WellPointRuleServiceService",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("WellPointRuleServiceService")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "invokeRuleCategoryEntryPoint", "invokeRuleCategoryEntryPointRequest"),
                createCall(portName, "invokeRuleExecutionEntryPoint", "invokeRuleExecutionEntryPointRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
    
    
}
