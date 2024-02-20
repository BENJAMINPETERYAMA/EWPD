//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.wellpoint.ets.ebx.simulation.webservices.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for errorExclusionWebServiceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="errorExclusionWebServiceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exclusionCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="exclusionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastUpdatedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryExclusion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryExclusion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "errorExclusionWebServiceVO", namespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/", propOrder = {
    "createdDate",
    "createdUser",
    "errorCode",
    "exclusionCount",
    "exclusionId",
    "lastUpdatedDate",
    "lastUpdatedUser",
    "primaryExclusion",
    "primaryValues",
    "secondaryExclusion",
    "secondaryValues"
})
public class ErrorExclusionWebServiceVO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    protected String createdUser;
    protected String errorCode;
    protected int exclusionCount;
    protected int exclusionId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdatedDate;
    protected String lastUpdatedUser;
    protected String primaryExclusion;
    protected String primaryValues;
    protected String secondaryExclusion;
    protected String secondaryValues;

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the createdUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * Sets the value of the createdUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedUser(String value) {
        this.createdUser = value;
    }

    /**
     * Gets the value of the errorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the value of the errorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorCode(String value) {
        this.errorCode = value;
    }

    /**
     * Gets the value of the exclusionCount property.
     * 
     */
    public int getExclusionCount() {
        return exclusionCount;
    }

    /**
     * Sets the value of the exclusionCount property.
     * 
     */
    public void setExclusionCount(int value) {
        this.exclusionCount = value;
    }

    /**
     * Gets the value of the exclusionId property.
     * 
     */
    public int getExclusionId() {
        return exclusionId;
    }

    /**
     * Sets the value of the exclusionId property.
     * 
     */
    public void setExclusionId(int value) {
        this.exclusionId = value;
    }

    /**
     * Gets the value of the lastUpdatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the value of the lastUpdatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdatedDate(XMLGregorianCalendar value) {
        this.lastUpdatedDate = value;
    }

    /**
     * Gets the value of the lastUpdatedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    /**
     * Sets the value of the lastUpdatedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedUser(String value) {
        this.lastUpdatedUser = value;
    }

    /**
     * Gets the value of the primaryExclusion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryExclusion() {
        return primaryExclusion;
    }

    /**
     * Sets the value of the primaryExclusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryExclusion(String value) {
        this.primaryExclusion = value;
    }

    /**
     * Gets the value of the primaryValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryValues() {
        return primaryValues;
    }

    /**
     * Sets the value of the primaryValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryValues(String value) {
        this.primaryValues = value;
    }

    /**
     * Gets the value of the secondaryExclusion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryExclusion() {
        return secondaryExclusion;
    }

    /**
     * Sets the value of the secondaryExclusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryExclusion(String value) {
        this.secondaryExclusion = value;
    }

    /**
     * Gets the value of the secondaryValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryValues() {
        return secondaryValues;
    }

    /**
     * Sets the value of the secondaryValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryValues(String value) {
        this.secondaryValues = value;
    }

}