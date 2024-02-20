/*
 * BenefitHierarchyAssociationVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.vo;

/**
 * VO for Benefit Hierarchy Association
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyAssociationVO implements Comparable {

    private int sequenceNumber;

    private String benefitName;

    private int benefitId;

    private int benefitHierarchyId;

    private int benefitComponentId;

    private int benefitHierarchyAssociationId;

    private boolean validBenefit;

    /**
     * @return Returns the benefitHierarchyAssociationId.
     */
    public int getBenefitHierarchyAssociationId() {
        return benefitHierarchyAssociationId;
    }


    /**
     * @param benefitHierarchyAssociationId
     *            The benefitHierarchyAssociationId to set.
     */
    public void setBenefitHierarchyAssociationId(
            int benefitHierarchyAssociationId) {
        this.benefitHierarchyAssociationId = benefitHierarchyAssociationId;
    }


    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the benefitHierarchyId.
     */
    public int getBenefitHierarchyId() {
        return benefitHierarchyId;
    }


    /**
     * @param benefitHierarchyId
     *            The benefitHierarchyId to set.
     */
    public void setBenefitHierarchyId(int benefitHierarchyId) {
        this.benefitHierarchyId = benefitHierarchyId;
    }


    /**
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }


    /**
     * @param benefitId
     *            The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }


    /**
     * @return Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }


    /**
     * @param benefitName
     *            The benefitName to set.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }


    /**
     * @return Returns the sequenceNumber.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * @param sequenceNumber
     *            The sequenceNumber to set.
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        int result = 0;
        BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = (BenefitHierarchyAssociationVO) o;
        if (this.getSequenceNumber() > benefitHierarchyAssociationVO
                .getSequenceNumber()) {
            result = 1;
        }
        if (this.getSequenceNumber() < benefitHierarchyAssociationVO
                .getSequenceNumber()) {
            result = -1;
        }
        if (this.getSequenceNumber() == benefitHierarchyAssociationVO
                .getSequenceNumber()) {
            result = 0;
        }
        return result;
    }


	/**
	 * Returns the validBenefit
	 * @return boolean validBenefit.
	 */
	public boolean isValidBenefit() {
		return validBenefit;
	}
	/**
	 * Sets the validBenefit
	 * @param validBenefit.
	 */
	public void setValidBenefit(boolean validBenefit) {
		this.validBenefit = validBenefit;
	}
	/**
	 * Sets the validBenefit
	 * @param validBenefit.
	 */
	public void setValidBenefit(String validBenefit) {
		if(null != validBenefit && validBenefit.equalsIgnoreCase("TRUE")){
			this.validBenefit = true;
		}else{
			this.validBenefit = false;			
		}
	}
    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitComponentId = ").append(this.benefitComponentId);
        buffer.append(", sequenceNumber = ").append(this.sequenceNumber);
        buffer.append(", benefitName = ").append(this.benefitName);
        buffer.append(", benefitId = ").append(this.benefitId);
        buffer.append(", benefitHierarchyId = ").append(this.benefitHierarchyId);
        buffer.append(", benefitHierarchyAssociationId = ").append(this.benefitHierarchyAssociationId);
        buffer.append(", validBenefit = ").append(this.validBenefit);
        return buffer.toString();
    }
}