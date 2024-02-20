package com.wellpoint.blaze;

public class XML_RSIFProxy implements com.wellpoint.blaze.XML_RSIF {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.wellpoint.blaze.XML_RSIF xML_RSIF = null;
  
  public XML_RSIFProxy() {
    _initXML_RSIFProxy();
  }
  
  private void _initXML_RSIFProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      xML_RSIF = ((com.wellpoint.blaze.XML_RSIFService)ctx.lookup("java:comp/env/service/XML_RSIFService")).getXML_RSService();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (xML_RSIF == null) {
    try{
      xML_RSIF = (new com.wellpoint.blaze.XML_RSIFServiceLocator()).getXML_RSService();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (xML_RSIF != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)xML_RSIF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)xML_RSIF)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  xML_RSIF = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (xML_RSIF != null)
    ((javax.xml.rpc.Stub)xML_RSIF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public com.wellpoint.blaze.XML_RSIF getXML_RSIF() {
  if (xML_RSIF == null)
    _initXML_RSIFProxy();
  return xML_RSIF;
}

public java.lang.String invokeselectComponents(java.lang.String arg_0_0) throws java.rmi.RemoteException{
  if (xML_RSIF == null)
    _initXML_RSIFProxy();
  return xML_RSIF.invokeselectComponents(arg_0_0);
}

public java.lang.String invokeselectBenefits(java.lang.String arg_0_1) throws java.rmi.RemoteException{
  if (xML_RSIF == null)
    _initXML_RSIFProxy();
  return xML_RSIF.invokeselectBenefits(arg_0_1);
}

public java.lang.String invokeselectAccidentRiderBenefits(java.lang.String arg_0_2) throws java.rmi.RemoteException{
  if (xML_RSIF == null)
    _initXML_RSIFProxy();
  return xML_RSIF.invokeselectAccidentRiderBenefits(arg_0_2);
}

public java.lang.String invokegetRuleInfo() throws java.rmi.RemoteException{
  if (xML_RSIF == null)
    _initXML_RSIFProxy();
  return xML_RSIF.invokegetRuleInfo();
}


}