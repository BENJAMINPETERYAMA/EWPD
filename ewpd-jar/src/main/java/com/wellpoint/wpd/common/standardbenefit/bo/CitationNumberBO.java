/*
 * CitationNumberBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CitationNumberBO extends BusinessObject{
	
	private int standardBenefitKey;
	private int benefitMandateId;
	private String citationNumber;
	/**
	 * Returns the standardBenefitKey
	 * @return int standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * Sets the standardBenefitKey
	 * @param standardBenefitKey.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
	}
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
        
        return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
        StringBuffer buffer = new StringBuffer();
       
        return buffer.toString();		
	}


	/**
	 * Returns the benefitMandateId
	 * @return int benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * Sets the benefitMandateId
	 * @param benefitMandateId.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
	/**
	 * Returns the citationNumber
	 * @return String citationNumber.
	 */
	public String getCitationNumber() {
		return citationNumber;
	}
	/**
	 * Sets the citationNumber
	 * @param citationNumber.
	 */
	public void setCitationNumber(String citationNumber) {
		this.citationNumber = citationNumber;
	}
}
