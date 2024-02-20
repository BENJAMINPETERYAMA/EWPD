package com.wellpoint.ets.ebx.simulation.webservices.client;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.StringUtil;

import java.util.List;

public class SimulationWebServiceImplPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplService _service = null;
        private com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplDelegate _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplService)ctx.lookup("java:comp/env/service/SimulationWebServiceImplService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    //System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    Logger.logInfo("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getSimulationWebServiceImplPort();
        }

        public com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplDelegate getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://impl.webservices.simulation.ebx.ets.wellpoint.com/", "SimulationWebServiceImplPort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
           String EndPntAddsPrty= (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
           if(StringUtil.regExPatterValidation(EndPntAddsPrty)){
        	   EndPntAddsPrty=EndPntAddsPrty;
   			}else{
   				EndPntAddsPrty=null;
   			}
            return EndPntAddsPrty;
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

    }

    public SimulationWebServiceImplPortProxy() {
        _descriptor = new Descriptor();
    }

    public SimulationWebServiceImplPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public List<ContractWebServiceVO> getContractInfo(ContractWebServiceVO arg0, String arg1, boolean arg2) throws SimulationWebServiceException_Exception {
        return _getDescriptor().getProxy().getContractInfo(arg0,arg1,arg2);
    }

}