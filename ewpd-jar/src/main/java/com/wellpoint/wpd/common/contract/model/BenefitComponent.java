/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 */
public class BenefitComponent implements Serializable{
	
	private List lineOfBusiness;
	
	private List businessEntity;
	
	private List businessGroup;
	
	private String benefitComponentName;

	private String benefitComponentType;
	
	private String benefitComponentDescription;
	
	private String benefitRuleId;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	private String version;
	
	private List benefits;// List of benefits
	
	private List notes;// List of notes
	

	/**
	 * @return Returns the benefitComponentDescription.
	 */
	public String getBenefitComponentDescription() {
		return benefitComponentDescription;
	}
	/**
	 * @param benefitComponentDescription The benefitComponentDescription to set.
	 */
	public void setBenefitComponentDescription(
			String benefitComponentDescription) {
		this.benefitComponentDescription = benefitComponentDescription;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the benefitComponentType.
	 */
	public String getBenefitComponentType() {
		return benefitComponentType;
	}
	/**
	 * @param benefitComponentType The benefitComponentType to set.
	 */
	public void setBenefitComponentType(String benefitComponentType) {
		this.benefitComponentType = benefitComponentType;
	}
	/**
	 * @return Returns the benefitRuleId.
	 */
	public String getBenefitRuleId() {
		return benefitRuleId;
	}
	/**
	 * @param benefitRuleId The benefitRuleId to set.
	 */
	public void setBenefitRuleId(String benefitRuleId) {
		this.benefitRuleId = benefitRuleId;
	}
	/**
	 * @return Returns the benefits.
	 */
	public List getBenefits() {
		return benefits;
	}
	/**
	 * @param benefits The benefits to set.
	 */
	public void setBenefits(List benefits) {
		this.benefits = benefits;
	}
	/**
	 * @return Returns the businessEntity.
	 */
	public List getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(List businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public List getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(List businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the lineOfBusiness.
	 */
	public List getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * @param lineOfBusiness The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(List lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	/**
	 * @return Returns the notes.
	 */
	public List getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(List notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
