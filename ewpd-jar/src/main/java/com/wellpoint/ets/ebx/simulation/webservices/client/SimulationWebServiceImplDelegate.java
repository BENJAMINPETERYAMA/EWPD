//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.wellpoint.ets.ebx.simulation.webservices.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "SimulationWebServiceImplDelegate", targetNamespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SimulationWebServiceImplDelegate {


    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @return
     *     returns java.util.List<com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO>
     * @throws SimulationWebServiceException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getContractInfo", targetNamespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/", className = "com.wellpoint.ets.ebx.simulation.webservices.client.GetContractInfo")
    @ResponseWrapper(localName = "getContractInfoResponse", targetNamespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/", className = "com.wellpoint.ets.ebx.simulation.webservices.client.GetContractInfoResponse")
    public List<ContractWebServiceVO> getContractInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        ContractWebServiceVO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        boolean arg2)
        throws SimulationWebServiceException_Exception
    ;

}
