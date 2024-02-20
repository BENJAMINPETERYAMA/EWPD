/*
 * BenefitTermBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitTermBO {
    
    private int benefitLevelId;
	
	private String benefitTerm;
	
	private String benefitTermCode;
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
     * Returns the benefitTerm
     * @return String benefitTerm.
     */

    public String getBenefitTerm() {
        return benefitTerm;
    }
    /**
     * Sets the benefitTerm
     * @param benefitTerm.
     */

    public void setBenefitTerm(String benefitTerm) {
        this.benefitTerm = benefitTerm;
    }
    /**
     * Returns the benefitTermCode
     * @return String benefitTermCode.
     */

    public String getBenefitTermCode() {
        return benefitTermCode;
    }
    /**
     * Sets the benefitTermCode
     * @param benefitTermCode.
     */

    public void setBenefitTermCode(String benefitTermCode) {
        this.benefitTermCode = benefitTermCode;
    }
}
