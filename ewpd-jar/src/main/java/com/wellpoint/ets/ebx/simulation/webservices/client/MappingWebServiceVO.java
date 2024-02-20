//
// Generated By:JAX-WS RI IBM 2.1.6 in JDK 6 (JAXB RI IBM JAXB 2.1.10 in JDK 6)
//


package com.wellpoint.ets.ebx.simulation.webservices.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mappingWebServiceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mappingWebServiceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accum" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="auditLock" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auditTrails" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}auditTrailWebServiceVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="businessEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractMapping" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}contractMappingWebServiceVO" minOccurs="0"/>
 *         &lt;element name="createdTmStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dataFeedInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datafeedStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eb01" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="eb02" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="eb03" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="eb06" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="eb09" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="endAge" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="endAgeCodedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finalized" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="finalizedFlagModified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="formattedStringDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hippaSegment" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd01" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd02" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd03" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd04" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd05" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd06" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd07" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="hsd08" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="ii02" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="inTempTable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isMapgRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastChangedTmStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mapped" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mappingComplete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mbu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msg_type_required" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noteTypeCode" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="pageFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rule" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}ruleWebServiceVO" minOccurs="0"/>
 *         &lt;element name="sensitiveBenefitIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spsId" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}spsIdWebServiceVO" minOccurs="0"/>
 *         &lt;element name="startAge" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="startAgeCodedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateMstr" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="user" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}userWebServiceVO" minOccurs="0"/>
 *         &lt;element name="utilizationMgmntRule" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}hippaSegmentWebServiceVO" minOccurs="0"/>
 *         &lt;element name="variable" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}variableWebServiceVO" minOccurs="0"/>
 *         &lt;element name="variableList" type="{http://impl.webservices.simulation.ebx.ets.wellpoint.com/}variableWebServiceVO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="variableMappingStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="variableStatusForAuditTrail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="variableSystemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mappingWebServiceVO", namespace = "http://impl.webservices.simulation.ebx.ets.wellpoint.com/", propOrder = {
    "accum",
    "auditLock",
    "auditTrails",
    "businessEntity",
    "businessGroup",
    "contractMapping",
    "createdTmStamp",
    "dataFeedInd",
    "datafeedStatus",
    "eb01",
    "eb02",
    "eb03",
    "eb06",
    "eb09",
    "endAge",
    "endAgeCodedValue",
    "finalized",
    "finalizedFlagModified",
    "formattedStringDate",
    "hippaSegment",
    "hsd01",
    "hsd02",
    "hsd03",
    "hsd04",
    "hsd05",
    "hsd06",
    "hsd07",
    "hsd08",
    "ii02",
    "inTempTable",
    "isMapgRequired",
    "lastChangedTmStamp",
    "mapped",
    "mappingComplete",
    "mbu",
    "message",
    "msgTypeRequired",
    "noteTypeCode",
    "pageFrom",
    "rule",
    "sensitiveBenefitIndicator",
    "sortField",
    "spsId",
    "startAge",
    "startAgeCodedValue",
    "updateMstr",
    "user",
    "utilizationMgmntRule",
    "variable",
    "variableList",
    "variableMappingStatus",
    "variableStatusForAuditTrail",
    "variableSystemId"
})
public class MappingWebServiceVO {

    protected HippaSegmentWebServiceVO accum;
    protected String auditLock;
    @XmlElement(nillable = true)
    protected List<AuditTrailWebServiceVO> auditTrails;
    protected String businessEntity;
    protected String businessGroup;
    protected ContractMappingWebServiceVO contractMapping;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTmStamp;
    protected String dataFeedInd;
    protected String datafeedStatus;
    protected HippaSegmentWebServiceVO eb01;
    protected HippaSegmentWebServiceVO eb02;
    protected HippaSegmentWebServiceVO eb03;
    protected HippaSegmentWebServiceVO eb06;
    protected HippaSegmentWebServiceVO eb09;
    protected HippaSegmentWebServiceVO endAge;
    protected String endAgeCodedValue;
    protected boolean finalized;
    protected boolean finalizedFlagModified;
    protected String formattedStringDate;
    protected HippaSegmentWebServiceVO hippaSegment;
    protected HippaSegmentWebServiceVO hsd01;
    protected HippaSegmentWebServiceVO hsd02;
    protected HippaSegmentWebServiceVO hsd03;
    protected HippaSegmentWebServiceVO hsd04;
    protected HippaSegmentWebServiceVO hsd05;
    protected HippaSegmentWebServiceVO hsd06;
    protected HippaSegmentWebServiceVO hsd07;
    protected HippaSegmentWebServiceVO hsd08;
    protected HippaSegmentWebServiceVO ii02;
    protected String inTempTable;
    protected String isMapgRequired;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChangedTmStamp;
    protected boolean mapped;
    protected String mappingComplete;
    protected String mbu;
    protected String message;
    @XmlElement(name = "msg_type_required")
    protected String msgTypeRequired;
    protected HippaSegmentWebServiceVO noteTypeCode;
    protected String pageFrom;
    protected RuleWebServiceVO rule;
    protected String sensitiveBenefitIndicator;
    protected String sortField;
    protected SpsIdWebServiceVO spsId;
    protected HippaSegmentWebServiceVO startAge;
    protected String startAgeCodedValue;
    protected boolean updateMstr;
    protected UserWebServiceVO user;
    protected HippaSegmentWebServiceVO utilizationMgmntRule;
    protected VariableWebServiceVO variable;
    @XmlElement(nillable = true)
    protected List<VariableWebServiceVO> variableList;
    protected String variableMappingStatus;
    protected int variableStatusForAuditTrail;
    protected Long variableSystemId;

    /**
     * Gets the value of the accum property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getAccum() {
        return accum;
    }

    /**
     * Sets the value of the accum property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setAccum(HippaSegmentWebServiceVO value) {
        this.accum = value;
    }

    /**
     * Gets the value of the auditLock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditLock() {
        return auditLock;
    }

    /**
     * Sets the value of the auditLock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditLock(String value) {
        this.auditLock = value;
    }

    /**
     * Gets the value of the auditTrails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auditTrails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuditTrails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuditTrailWebServiceVO }
     * 
     * 
     */
    public List<AuditTrailWebServiceVO> getAuditTrails() {
        if (auditTrails == null) {
            auditTrails = new ArrayList<AuditTrailWebServiceVO>();
        }
        return this.auditTrails;
    }

    /**
     * Gets the value of the businessEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Sets the value of the businessEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessEntity(String value) {
        this.businessEntity = value;
    }

    /**
     * Gets the value of the businessGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * Sets the value of the businessGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessGroup(String value) {
        this.businessGroup = value;
    }

    /**
     * Gets the value of the contractMapping property.
     * 
     * @return
     *     possible object is
     *     {@link ContractMappingWebServiceVO }
     *     
     */
    public ContractMappingWebServiceVO getContractMapping() {
        return contractMapping;
    }

    /**
     * Sets the value of the contractMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractMappingWebServiceVO }
     *     
     */
    public void setContractMapping(ContractMappingWebServiceVO value) {
        this.contractMapping = value;
    }

    /**
     * Gets the value of the createdTmStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTmStamp() {
        return createdTmStamp;
    }

    /**
     * Sets the value of the createdTmStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTmStamp(XMLGregorianCalendar value) {
        this.createdTmStamp = value;
    }

    /**
     * Gets the value of the dataFeedInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFeedInd() {
        return dataFeedInd;
    }

    /**
     * Sets the value of the dataFeedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFeedInd(String value) {
        this.dataFeedInd = value;
    }

    /**
     * Gets the value of the datafeedStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatafeedStatus() {
        return datafeedStatus;
    }

    /**
     * Sets the value of the datafeedStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatafeedStatus(String value) {
        this.datafeedStatus = value;
    }

    /**
     * Gets the value of the eb01 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEb01() {
        return eb01;
    }

    /**
     * Sets the value of the eb01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEb01(HippaSegmentWebServiceVO value) {
        this.eb01 = value;
    }

    /**
     * Gets the value of the eb02 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEb02() {
        return eb02;
    }

    /**
     * Sets the value of the eb02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEb02(HippaSegmentWebServiceVO value) {
        this.eb02 = value;
    }

    /**
     * Gets the value of the eb03 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEb03() {
        return eb03;
    }

    /**
     * Sets the value of the eb03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEb03(HippaSegmentWebServiceVO value) {
        this.eb03 = value;
    }

    /**
     * Gets the value of the eb06 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEb06() {
        return eb06;
    }

    /**
     * Sets the value of the eb06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEb06(HippaSegmentWebServiceVO value) {
        this.eb06 = value;
    }

    /**
     * Gets the value of the eb09 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEb09() {
        return eb09;
    }

    /**
     * Sets the value of the eb09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEb09(HippaSegmentWebServiceVO value) {
        this.eb09 = value;
    }

    /**
     * Gets the value of the endAge property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getEndAge() {
        return endAge;
    }

    /**
     * Sets the value of the endAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setEndAge(HippaSegmentWebServiceVO value) {
        this.endAge = value;
    }

    /**
     * Gets the value of the endAgeCodedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndAgeCodedValue() {
        return endAgeCodedValue;
    }

    /**
     * Sets the value of the endAgeCodedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndAgeCodedValue(String value) {
        this.endAgeCodedValue = value;
    }

    /**
     * Gets the value of the finalized property.
     * 
     */
    public boolean isFinalized() {
        return finalized;
    }

    /**
     * Sets the value of the finalized property.
     * 
     */
    public void setFinalized(boolean value) {
        this.finalized = value;
    }

    /**
     * Gets the value of the finalizedFlagModified property.
     * 
     */
    public boolean isFinalizedFlagModified() {
        return finalizedFlagModified;
    }

    /**
     * Sets the value of the finalizedFlagModified property.
     * 
     */
    public void setFinalizedFlagModified(boolean value) {
        this.finalizedFlagModified = value;
    }

    /**
     * Gets the value of the formattedStringDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattedStringDate() {
        return formattedStringDate;
    }

    /**
     * Sets the value of the formattedStringDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattedStringDate(String value) {
        this.formattedStringDate = value;
    }

    /**
     * Gets the value of the hippaSegment property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHippaSegment() {
        return hippaSegment;
    }

    /**
     * Sets the value of the hippaSegment property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHippaSegment(HippaSegmentWebServiceVO value) {
        this.hippaSegment = value;
    }

    /**
     * Gets the value of the hsd01 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd01() {
        return hsd01;
    }

    /**
     * Sets the value of the hsd01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd01(HippaSegmentWebServiceVO value) {
        this.hsd01 = value;
    }

    /**
     * Gets the value of the hsd02 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd02() {
        return hsd02;
    }

    /**
     * Sets the value of the hsd02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd02(HippaSegmentWebServiceVO value) {
        this.hsd02 = value;
    }

    /**
     * Gets the value of the hsd03 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd03() {
        return hsd03;
    }

    /**
     * Sets the value of the hsd03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd03(HippaSegmentWebServiceVO value) {
        this.hsd03 = value;
    }

    /**
     * Gets the value of the hsd04 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd04() {
        return hsd04;
    }

    /**
     * Sets the value of the hsd04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd04(HippaSegmentWebServiceVO value) {
        this.hsd04 = value;
    }

    /**
     * Gets the value of the hsd05 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd05() {
        return hsd05;
    }

    /**
     * Sets the value of the hsd05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd05(HippaSegmentWebServiceVO value) {
        this.hsd05 = value;
    }

    /**
     * Gets the value of the hsd06 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd06() {
        return hsd06;
    }

    /**
     * Sets the value of the hsd06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd06(HippaSegmentWebServiceVO value) {
        this.hsd06 = value;
    }

    /**
     * Gets the value of the hsd07 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd07() {
        return hsd07;
    }

    /**
     * Sets the value of the hsd07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd07(HippaSegmentWebServiceVO value) {
        this.hsd07 = value;
    }

    /**
     * Gets the value of the hsd08 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getHsd08() {
        return hsd08;
    }

    /**
     * Sets the value of the hsd08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setHsd08(HippaSegmentWebServiceVO value) {
        this.hsd08 = value;
    }

    /**
     * Gets the value of the ii02 property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getIi02() {
        return ii02;
    }

    /**
     * Sets the value of the ii02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setIi02(HippaSegmentWebServiceVO value) {
        this.ii02 = value;
    }

    /**
     * Gets the value of the inTempTable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInTempTable() {
        return inTempTable;
    }

    /**
     * Sets the value of the inTempTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInTempTable(String value) {
        this.inTempTable = value;
    }

    /**
     * Gets the value of the isMapgRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMapgRequired() {
        return isMapgRequired;
    }

    /**
     * Sets the value of the isMapgRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMapgRequired(String value) {
        this.isMapgRequired = value;
    }

    /**
     * Gets the value of the lastChangedTmStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastChangedTmStamp() {
        return lastChangedTmStamp;
    }

    /**
     * Sets the value of the lastChangedTmStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastChangedTmStamp(XMLGregorianCalendar value) {
        this.lastChangedTmStamp = value;
    }

    /**
     * Gets the value of the mapped property.
     * 
     */
    public boolean isMapped() {
        return mapped;
    }

    /**
     * Sets the value of the mapped property.
     * 
     */
    public void setMapped(boolean value) {
        this.mapped = value;
    }

    /**
     * Gets the value of the mappingComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMappingComplete() {
        return mappingComplete;
    }

    /**
     * Sets the value of the mappingComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMappingComplete(String value) {
        this.mappingComplete = value;
    }

    /**
     * Gets the value of the mbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMbu() {
        return mbu;
    }

    /**
     * Sets the value of the mbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMbu(String value) {
        this.mbu = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the msgTypeRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgTypeRequired() {
        return msgTypeRequired;
    }

    /**
     * Sets the value of the msgTypeRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgTypeRequired(String value) {
        this.msgTypeRequired = value;
    }

    /**
     * Gets the value of the noteTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getNoteTypeCode() {
        return noteTypeCode;
    }

    /**
     * Sets the value of the noteTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setNoteTypeCode(HippaSegmentWebServiceVO value) {
        this.noteTypeCode = value;
    }

    /**
     * Gets the value of the pageFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageFrom() {
        return pageFrom;
    }

    /**
     * Sets the value of the pageFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageFrom(String value) {
        this.pageFrom = value;
    }

    /**
     * Gets the value of the rule property.
     * 
     * @return
     *     possible object is
     *     {@link RuleWebServiceVO }
     *     
     */
    public RuleWebServiceVO getRule() {
        return rule;
    }

    /**
     * Sets the value of the rule property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuleWebServiceVO }
     *     
     */
    public void setRule(RuleWebServiceVO value) {
        this.rule = value;
    }

    /**
     * Gets the value of the sensitiveBenefitIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSensitiveBenefitIndicator() {
        return sensitiveBenefitIndicator;
    }

    /**
     * Sets the value of the sensitiveBenefitIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSensitiveBenefitIndicator(String value) {
        this.sensitiveBenefitIndicator = value;
    }

    /**
     * Gets the value of the sortField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * Sets the value of the sortField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortField(String value) {
        this.sortField = value;
    }

    /**
     * Gets the value of the spsId property.
     * 
     * @return
     *     possible object is
     *     {@link SpsIdWebServiceVO }
     *     
     */
    public SpsIdWebServiceVO getSpsId() {
        return spsId;
    }

    /**
     * Sets the value of the spsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link SpsIdWebServiceVO }
     *     
     */
    public void setSpsId(SpsIdWebServiceVO value) {
        this.spsId = value;
    }

    /**
     * Gets the value of the startAge property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getStartAge() {
        return startAge;
    }

    /**
     * Sets the value of the startAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setStartAge(HippaSegmentWebServiceVO value) {
        this.startAge = value;
    }

    /**
     * Gets the value of the startAgeCodedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartAgeCodedValue() {
        return startAgeCodedValue;
    }

    /**
     * Sets the value of the startAgeCodedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartAgeCodedValue(String value) {
        this.startAgeCodedValue = value;
    }

    /**
     * Gets the value of the updateMstr property.
     * 
     */
    public boolean isUpdateMstr() {
        return updateMstr;
    }

    /**
     * Sets the value of the updateMstr property.
     * 
     */
    public void setUpdateMstr(boolean value) {
        this.updateMstr = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link UserWebServiceVO }
     *     
     */
    public UserWebServiceVO getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserWebServiceVO }
     *     
     */
    public void setUser(UserWebServiceVO value) {
        this.user = value;
    }

    /**
     * Gets the value of the utilizationMgmntRule property.
     * 
     * @return
     *     possible object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public HippaSegmentWebServiceVO getUtilizationMgmntRule() {
        return utilizationMgmntRule;
    }

    /**
     * Sets the value of the utilizationMgmntRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link HippaSegmentWebServiceVO }
     *     
     */
    public void setUtilizationMgmntRule(HippaSegmentWebServiceVO value) {
        this.utilizationMgmntRule = value;
    }

    /**
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link VariableWebServiceVO }
     *     
     */
    public VariableWebServiceVO getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableWebServiceVO }
     *     
     */
    public void setVariable(VariableWebServiceVO value) {
        this.variable = value;
    }

    /**
     * Gets the value of the variableList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variableList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariableList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariableWebServiceVO }
     * 
     * 
     */
    public List<VariableWebServiceVO> getVariableList() {
        if (variableList == null) {
            variableList = new ArrayList<VariableWebServiceVO>();
        }
        return this.variableList;
    }

    /**
     * Gets the value of the variableMappingStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariableMappingStatus() {
        return variableMappingStatus;
    }

    /**
     * Sets the value of the variableMappingStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariableMappingStatus(String value) {
        this.variableMappingStatus = value;
    }

    /**
     * Gets the value of the variableStatusForAuditTrail property.
     * 
     */
    public int getVariableStatusForAuditTrail() {
        return variableStatusForAuditTrail;
    }

    /**
     * Sets the value of the variableStatusForAuditTrail property.
     * 
     */
    public void setVariableStatusForAuditTrail(int value) {
        this.variableStatusForAuditTrail = value;
    }

    /**
     * Gets the value of the variableSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVariableSystemId() {
        return variableSystemId;
    }

    /**
     * Sets the value of the variableSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVariableSystemId(Long value) {
        this.variableSystemId = value;
    }

}