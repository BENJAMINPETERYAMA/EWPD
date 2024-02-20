//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.wellpoint.ets.ebx.simulation.webservices.client;

import javax.xml.ws.WebFault;

@WebFault(name = "SimulationWebServiceException", targetNamespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/")
public class SimulationWebServiceException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SimulationWebServiceException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SimulationWebServiceException_Exception(String message, SimulationWebServiceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param message
     * @param cause
     */
    public SimulationWebServiceException_Exception(String message, SimulationWebServiceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceException
     */
    public SimulationWebServiceException getFaultInfo() {
        return faultInfo;
    }

}
