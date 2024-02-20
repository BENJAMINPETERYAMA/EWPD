/*
 * CancelMigration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;


/**
 * Object to get the benefit component and benefits.
 * Mainly to find out the next benefit component and benefit.
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBenefit {
    private int benefitComponentId;
    private String benefitComponentDesc;
    private int benefitId;
    private String benefitDesc;
    private int sequenceNo;
    
    public String getBenefitComponentDesc() {
        return benefitComponentDesc;
    }

    public void setBenefitComponentDesc(String benefitComponentDesc) {
        this.benefitComponentDesc = benefitComponentDesc;
    }

    public int getBenefitComponentId() {
        return benefitComponentId;
    }

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }

    public String getBenefitDesc() {
        return benefitDesc;
    }

    public void setBenefitDesc(String benefitDesc) {
        this.benefitDesc = benefitDesc;
    }

    public int getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
	/**
	 * @return Returns the sequenceNo.
	 */
	public int getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * @param sequenceNo The sequenceNo to set.
	 */
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
}
