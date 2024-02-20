/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locateresult;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateResult;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelLocateResult extends LocateResult implements Comparable{
	private String benefitTerm;
	
	private String benefitQualifier;
	
	private List benefitLines;
	
	private int benefitDefinitionId;
	
	private int benefitLevelId;
	
	private String benefitLevelDesc;
	
	private String reference;
	
	private int benefitLevelSeq;
	/**
	 * @return Returns the benefitLevelSeq.
	 */
	public int getBenefitLevelSeq() {
		return benefitLevelSeq;
	}
	/**
	 * @param benefitLevelSeq The benefitLevelSeq to set.
	 */
	public void setBenefitLevelSeq(int benefitLevelSeq) {
		this.benefitLevelSeq = benefitLevelSeq;
	}

	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
	/**
	 * @return Returns the benefitQualifier.
	 */
	public String getBenefitQualifier() {
		return benefitQualifier;
	}
	/**
	 * @param benefitQualifier The benefitQualifier to set.
	 */
	public void setBenefitQualifier(String benefitQualifier) {
		this.benefitQualifier = benefitQualifier;
	}
	/**
	 * @return Returns the benefitTerm.
	 */
	public String getBenefitTerm() {
		return benefitTerm;
	}
	/**
	 * @param benefitTerm The benefitTerm to set.
	 */
	public void setBenefitTerm(String benefitTerm) {
		this.benefitTerm = benefitTerm;
	}
	
	/**
	 * @return Returns the benefitLines.
	 */
	public List getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(List benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}
	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		int result = 0;
		BenefitLevelLocateResult benefitLevelLocateResult1 = (BenefitLevelLocateResult)o;
		if(this.getBenefitLevelId() > benefitLevelLocateResult1.getBenefitLevelSeq()){
			result = 1;
		}
		if(this.getBenefitLevelSeq() < benefitLevelLocateResult1.getBenefitLevelSeq()){
			result = -1;
		}
		if(this.getBenefitLevelSeq() == benefitLevelLocateResult1.getBenefitLevelSeq()){
			result = 0;
		}
		return result;
	}
}
