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
 */
public class Benefit implements Serializable{
	
	private List lineOfBusiness;
	
	private List businessEntity;
	
	private List businessGroup;
	
	private String benefitName;
	
	private String benefitMeaning;
	
	private String benefitType;
	
	private String benefitCategory;
	
	private String description;
	
	private String benefitRuleId;
	
	private List term;
	
	private List qualifier;
	
	private List pva;

	private List dataType;
	
	private int version;
	
	private List notes;// List of notes
	
	private List benefitLines;//List of benefitLines
	
	private List adminOptions;//List of adminOptions
	
	private List superProcessSteps;//List of SuperProcessSteps
	
	private String administration;
	
	private List tiers;
	

	/**
	 * @return Returns the tiers.
	 */
	public List getTiers() {
		return tiers;
	}
	/**
	 * @param tiers The tiers to set.
	 */
	public void setTiers(List tiers) {
		this.tiers = tiers;
	}
	/**
	 * @return Returns the administration.
	 */
	public String getAdministration() {
		return administration;
	}
	/**
	 * @param administration The administration to set.
	 */
	public void setAdministration(String administration) {
		this.administration = administration;
	}
	/**
	 * @return Returns the adminOptions.
	 */
	public List getAdminOptions() {
		return adminOptions;
	}
	/**
	 * @param adminOptions The adminOptions to set.
	 */
	public void setAdminOptions(List adminOptions) {
		this.adminOptions = adminOptions;
	}
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
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
	 * @return Returns the benefitMeaning.
	 */
	public String getBenefitMeaning() {
		return benefitMeaning;
	}
	/**
	 * @param benefitMeaning The benefitMeaning to set.
	 */
	public void setBenefitMeaning(String benefitMeaning) {
		this.benefitMeaning = benefitMeaning;
	}
	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
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
	 * @return Returns the benefitType.
	 */
	public String getBenefitType() {
		return benefitType;
	}
	/**
	 * @param benefitType The benefitType to set.
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
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
	 * @return Returns the dataType.
	 */
	public List getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(List dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return Returns the pva.
	 */
	public List getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(List pva) {
		this.pva = pva;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public List getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(List qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the superProcessSteps.
	 */
	public List getSuperProcessSteps() {
		return superProcessSteps;
	}
	/**
	 * @param superProcessSteps The superProcessSteps to set.
	 */
	public void setSuperProcessSteps(List superProcessSteps) {
		this.superProcessSteps = superProcessSteps;
	}
	/**
	 * @return Returns the term.
	 */
	public List getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(List term) {
		this.term = term;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
