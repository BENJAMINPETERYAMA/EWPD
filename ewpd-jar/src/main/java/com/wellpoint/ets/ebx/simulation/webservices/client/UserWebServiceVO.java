//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.wellpoint.ets.ebx.simulation.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userWebServiceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userWebServiceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userWebServiceVO", namespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/", propOrder = {
    "createdUserName",
    "lastUpdatedUserName"
})
public class UserWebServiceVO {

    protected String createdUserName;
    protected String lastUpdatedUserName;

    /**
     * Gets the value of the createdUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUserName() {
        return createdUserName;
    }

    /**
     * Sets the value of the createdUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUserName(String value) {
        this.createdUserName = value;
    }

    /**
     * Gets the value of the lastUpdatedUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedUserName() {
        return lastUpdatedUserName;
    }

    /**
     * Sets the value of the lastUpdatedUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedUserName(String value) {
        this.lastUpdatedUserName = value;
    }

}