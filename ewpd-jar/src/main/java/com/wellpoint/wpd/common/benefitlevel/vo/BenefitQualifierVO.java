/*
 * BenefitQualifierVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitQualifierVO {
    private int benefitLevelId;
	
	private String benefitQualifier;
	
	private String benefitQualifierCode;
	
	

    /**
     * Returns the benefitLevelId
     * @return int benefitLevelId.
     */
    public int getBenefitLevelId() {
        return benefitLevelId;
    }
    /**
     * Sets the benefitLevelId
     * @param benefitLevelId.
     */
    public void setBenefitLevelId(int benefitLevelId) {
        this.benefitLevelId = benefitLevelId;
    }
    /**
     * Returns the benefitQualifier
     * @return String benefitQualifier.
     */
    public String getBenefitQualifier() {
        return benefitQualifier;
    }
    /**
     * Sets the benefitQualifier
     * @param benefitQualifier.
     */
    public void setBenefitQualifier(String benefitQualifier) {
        this.benefitQualifier = benefitQualifier;
    }
    /**
     * Returns the benefitQualifierCode
     * @return String benefitQualifierCode.
     */
    public String getBenefitQualifierCode() {
        return benefitQualifierCode;
    }
    /**
     * Sets the benefitQualifierCode
     * @param benefitQualifierCode.
     */
    public void setBenefitQualifierCode(String benefitQualifierCode) {
        this.benefitQualifierCode = benefitQualifierCode;
    }
}
