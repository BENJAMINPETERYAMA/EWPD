package com.wellpoint.blaze.ruleservice.contractvalidation;

public class WellPointRuleServiceIFProxy implements com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF wellPointRuleServiceIF = null;
  
  public WellPointRuleServiceIFProxy() {
    _initWellPointRuleServiceIFProxy();
  }
  
  private void _initWellPointRuleServiceIFProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      wellPointRuleServiceIF = ((com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFService)ctx.lookup("java:comp/env/service/WellPointRuleServiceIFService")).getWellPointRuleServiceService();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wellPointRuleServiceIF == null) {
    try{
      wellPointRuleServiceIF = (new com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIFServiceLocator()).getWellPointRuleServiceService();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (wellPointRuleServiceIF != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)wellPointRuleServiceIF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)wellPointRuleServiceIF)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  wellPointRuleServiceIF = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (wellPointRuleServiceIF != null)
    ((javax.xml.rpc.Stub)wellPointRuleServiceIF)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public com.wellpoint.blaze.ruleservice.contractvalidation.WellPointRuleServiceIF getWellPointRuleServiceIF() {
  if (wellPointRuleServiceIF == null)
    _initWellPointRuleServiceIFProxy();
  return wellPointRuleServiceIF;
}

public java.util.Vector invokeRuleCategoryEntryPoint() throws java.rmi.RemoteException{
  if (wellPointRuleServiceIF == null)
    _initWellPointRuleServiceIFProxy();
  return wellPointRuleServiceIF.invokeRuleCategoryEntryPoint();
}

public com.wellpoint.wpd.common.contract.ws.model.Messages invokeRuleExecutionEntryPoint(com.wellpoint.wpd.common.contract.ws.model.Contract arg_0_1, java.util.Vector arg_1_1) throws java.rmi.RemoteException{
  if (wellPointRuleServiceIF == null)
    _initWellPointRuleServiceIFProxy();
  return wellPointRuleServiceIF.invokeRuleExecutionEntryPoint(arg_0_1, arg_1_1);
}


}