/*
 * BenefitDefinitionPrintVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionPrintVO {

    private String levelDesc;

    private String termDesc;

    private String qualifierDesc;

    private String providerArrangementDesc;

    private String dataTypeDesc;

    private String referenceDesc;

    private String benefitValue;
    
    private String providerArrangementId;
    
    private String sequenceNo;
    //CASR START
    //DESCRIPTION : vaiable to hold frequnecy value
    private String benefitFrequency;
    //CASR END

    
    /**
     * @return sequenceNo
     * 
     * Returns the sequenceNo.
     */
    public String getSequenceNo() {
        return sequenceNo;
    }
    /**
     * @param sequenceNo
     * 
     * Sets the sequenceNo.
     */
    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }
    /**
     * @return Returns the benefitValue.
     */
    public String getBenefitValue() {
        return benefitValue;
    }


    /**
     * @param benefitValue
     *            The benefitValue to set.
     */
    public void setBenefitValue(String benefitValue) {
        this.benefitValue = benefitValue;
    }


    /**
     * @return Returns the dataTypeDesc.
     */
    public String getDataTypeDesc() {
        return dataTypeDesc;
    }


    /**
     * @param dataTypeDesc
     *            The dataTypeDesc to set.
     */
    public void setDataTypeDesc(String dataTypeDesc) {
        this.dataTypeDesc = dataTypeDesc;
    }


    /**
     * @return Returns the levelDesc.
     */
    public String getLevelDesc() {
        return levelDesc;
    }


    /**
     * @param levelDesc
     *            The levelDesc to set.
     */
    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }


    /**
     * @return Returns the providerArrangementDesc.
     */
    public String getProviderArrangementDesc() {
        return providerArrangementDesc;
    }


    /**
     * @param providerArrangementDesc
     *            The providerArrangementDesc to set.
     */
    public void setProviderArrangementDesc(String providerArrangementDesc) {
        this.providerArrangementDesc = providerArrangementDesc;
    }


    /**
     * @return Returns the qualifierDesc.
     */
    public String getQualifierDesc() {
        return qualifierDesc;
    }


    /**
     * @param qualifierDesc
     *            The qualifierDesc to set.
     */
    public void setQualifierDesc(String qualifierDesc) {
        this.qualifierDesc = qualifierDesc;
    }


    /**
     * @return Returns the referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }


    /**
     * @param referenceDesc
     *            The referenceDesc to set.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }


    /**
     * @return Returns the termDesc.
     */
    public String getTermDesc() {
        return termDesc;
    }


    /**
     * @param termDesc
     *            The termDesc to set.
     */
    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }
    /**
     * Returns the providerArrangementId
     * @return String providerArrangementId.
     */

    public String getProviderArrangementId() {
        return providerArrangementId;
    }
    /**
     * Sets the providerArrangementId
     * @param providerArrangementId.
     */

    public void setProviderArrangementId(String providerArrangementId) {
        this.providerArrangementId = providerArrangementId;
    }
	/**
	 * @return Returns the benefitFrequency.
	 */
	public String getBenefitFrequency() {
		return benefitFrequency;
	}
	/**
	 * @param benefitFrequency The benefitFrequency to set.
	 */
	public void setBenefitFrequency(String benefitFrequency) {
		this.benefitFrequency = benefitFrequency;
	}
}