/*
 * Created on Aug 6, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.bo;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitTierCriteria implements Comparable,Cloneable{

	private int benefitTierCriteriaSysId;
	
	private String benefitTierCriteriaName;
	
	private String benefitTierCriteriaValue;
	
	private int sequence;
	
	private int benefitTierCriteriaIndicator;
	
	private BenefitTier benefitTier;
	
	private int noOfPsblValues;
	
	

	/**
	 * This is for holding the no of possible values corresponding to that criteria.
	 * @return Returns the noOfPsblValues.
	 */
	public int getNoOfPsblValues() {
		return noOfPsblValues;
	}
	/**
	 * @param noOfPsblValues The noOfPsblValues to set.
	 */
	public void setNoOfPsblValues(int noOfPsblValues) {
		this.noOfPsblValues = noOfPsblValues;
	}
	public BenefitTier getBenefitTier() {
		return benefitTier;
	}

	public void setBenefitTier(BenefitTier benefitTier) {
		this.benefitTier = benefitTier;
	}

	public String getBenefitTierCriteriaName() {
		return benefitTierCriteriaName;
	}

	public void setBenefitTierCriteriaName(String benefitTierCriteriaName) {
		this.benefitTierCriteriaName = benefitTierCriteriaName;
	}

	public int getBenefitTierCriteriaSysId() {
		return benefitTierCriteriaSysId;
	}

	public void setBenefitTierCriteriaSysId(int benefitTierCriteriaSysId) {
		this.benefitTierCriteriaSysId = benefitTierCriteriaSysId;
	}

	public String getBenefitTierCriteriaValue() {
		return benefitTierCriteriaValue;
	}

	public void setBenefitTierCriteriaValue(String benefitTierCriteriaValue) {
		this.benefitTierCriteriaValue = benefitTierCriteriaValue;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
	
	public int getBenefitTierCriteriaIndicator() {
		return benefitTierCriteriaIndicator;
	}
	public void setBenefitTierCriteriaIndicator(int benefitTierCriteriaIndicator) {
		this.benefitTierCriteriaIndicator = benefitTierCriteriaIndicator;
	}
	/**
	 * Compares Benefit Tier criteria sequence.
	 * @param another BenefitTierCriteria the Criteria to be compared.
	 * the value 0 if this Criteria is equal to the argument Criteria;
	 * a value less than 0 if this Criteria is less than the argument Criteria; 
	 *  and a value greater than 0 if this Criteria is greater than the argument Criteria.
 
	 */
	public int compareTo(Object benefitTierCriteria) {
		BenefitTierCriteria criteria = (BenefitTierCriteria)benefitTierCriteria;
		Integer sequenceParam = new Integer(criteria.getSequence());
		Integer sequence = new Integer(getSequence());
		return sequence.compareTo(sequenceParam);
	}
	
	public boolean isRangeCriteria() {
		if(this.benefitTierCriteriaIndicator == 1) return false;
		else
			return true;
	}
	
	public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
           return null;
        }
    }
	public String toString() {
		StringBuffer buffer = new StringBuffer(benefitTierCriteriaSysId);
		buffer.append(" benefitTierCriteriaName "+benefitTierCriteriaName);
		buffer.append(" benefitTierCriteriaValue "+benefitTierCriteriaValue);
		buffer.append(" sequence "+sequence);
		buffer.append(" benefitTierCriteriaIndicator "+benefitTierCriteriaIndicator);
		buffer.append(" noOfPsblValues "+noOfPsblValues);
		return buffer.toString();
	}
	
	
	
}
