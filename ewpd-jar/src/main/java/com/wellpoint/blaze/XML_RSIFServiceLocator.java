/**
 * XML_RSIFServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.wellpoint.blaze;

import java.util.Locale;
import java.util.ResourceBundle;

public class XML_RSIFServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.wellpoint.blaze.XML_RSIFService {

    public XML_RSIFServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://blaze.wellpoint.com",
           "XML_RSIFService"));

        context.setLocatorName("com.wellpoint.blaze.XML_RSIFServiceLocator");
    }

    public XML_RSIFServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.wellpoint.blaze.XML_RSIFServiceLocator");
    }

    public java.lang.String getXML_RSServiceAddress() {
    	
    	ResourceBundle bundle = ResourceBundle.getBundle("/config/webservice", Locale.getDefault());
        // Use to get a proxy class for XML_RSService
        java.lang.String XML_RSService_address = bundle.getString("webservice.blaze");
        if (context.getOverriddingEndpointURIs() == null) {
            return XML_RSService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("XML_RSService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return XML_RSService_address;
        }
    }

    private java.lang.String XML_RSServicePortName = "XML_RSService";

    // The WSDD port name defaults to the port name.
    private java.lang.String XML_RSServiceWSDDPortName = "XML_RSService";

    public java.lang.String getXML_RSServiceWSDDPortName() {
        return XML_RSServiceWSDDPortName;
    }

    public void setXML_RSServiceWSDDPortName(java.lang.String name) {
        XML_RSServiceWSDDPortName = name;
    }

    synchronized public com.wellpoint.blaze.XML_RSIF getXML_RSService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getXML_RSServiceAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getXML_RSService(endpoint);
    }

    public com.wellpoint.blaze.XML_RSIF getXML_RSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.wellpoint.blaze.XML_RSIF _stub =
            (com.wellpoint.blaze.XML_RSIF) getStub(
                XML_RSServicePortName,
                (String) getPort2NamespaceMap().get(XML_RSServicePortName),
                com.wellpoint.blaze.XML_RSIF.class,
                "com.wellpoint.blaze.XML_RSServiceSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(XML_RSServiceWSDDPortName);
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
            if (com.wellpoint.blaze.XML_RSIF.class.isAssignableFrom(serviceEndpointInterface)) {
                return getXML_RSService();
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
        if ("XML_RSService".equals(inputPortName)) {
            return getXML_RSService();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        XML_RSServiceWSDDPortName = prefix + "/" + XML_RSServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return super.getServiceName();
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "XML_RSService",
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
        if  (portName.getLocalPart().equals("XML_RSService")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "invokeselectComponents", "invokeselectComponentsRequest"),
                createCall(portName, "invokeselectBenefits", "invokeselectBenefitsRequest"),
                createCall(portName, "invokeselectAccidentRiderBenefits", "invokeselectAccidentRiderBenefitsRequest"),
                createCall(portName, "invokegetRuleInfo", "invokegetRuleInfoRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
