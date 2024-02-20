package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;
import java.util.List;

/*
 * 
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * This Variable is actually the contract variable.
 */
public class Variable {
	
    private String variableId;
    
    private String description;
    
    private String majorHeadings;
    
    private String minorHeadings;
    
    private String pva;
    
    private String dataType;
    
    private boolean isMappingRequired;
    
    private Date createdDate;
    
    private String variableSystem;

	private String variableFormat;
	
	private String variableDescription;
	
	private boolean unmappedVariable = true;
	
	private boolean mappedVariable = true;
	
	private boolean notApplicable = true;
	
	private boolean codedStatus = false;
	
	private String  variableValue;
	
	private String  sensitiveBenefitIndicator;
	
	private String isAdvanceSearch;
	// variable category - section 3 validation
	
	private String isgCatagory;
	
	private String lgCatagory;
	
	private String variableStatus;
	
	private String message;
	
	private boolean auditLock;
	
	private String wpdAccumulator;
	
	 //BXNI CR35 Change
	private String lgAccumulator;
	
	//BXNI CR35 Change
		private String isgAccumulator;
		
		private String variableDescForDisplay;
	
	public String getLgAccumulator() {
		return lgAccumulator;
	}

	public void setLgAccumulator(String lgAccumulator) {
		this.lgAccumulator = lgAccumulator;
	}

	public String getIsgAccumulator() {
		return isgAccumulator;
	}

	public void setIsgAccumulator(String isgAccumulator) {
		this.isgAccumulator = isgAccumulator;
	}
   

	public String getWpdAccumulator() {
		return wpdAccumulator;
	}

	public void setWpdAccumulator(String wpdAccumulator) {
		this.wpdAccumulator = wpdAccumulator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * This variable holds the nature of the variable being searched (unmapped/mapped/not applicable)
	 */
	
	
	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPva() {
		return pva;
	}

	public void setPva(String pva) {
		this.pva = pva;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isMappingRequired() {
		return isMappingRequired;
	}

	public void setMappingRequired(boolean isMappingRequired) {
		this.isMappingRequired = isMappingRequired;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return Returns the variableSystem.
	 */
	public String getVariableSystem() {
		return variableSystem;
	}
	/**
	 * @param variableSystem The variableSystem to set.
	 */
	public void setVariableSystem(String variableSystem) {
		this.variableSystem = variableSystem;
	}
	/**
	 * @return Returns the majorHeadings.
	 */
	public String getMajorHeadings() {
		return majorHeadings;
	}
	/**
	 * @param majorHeadings The majorHeadings to set.
	 */
	public void setMajorHeadings(String majorHeadings) {
		this.majorHeadings = majorHeadings;
	}
	/**
	 * @return Returns the minorHeadings.
	 */
	public String getMinorHeadings() {
		return minorHeadings;
	}
	/**
	 * @param minorHeadings The minorHeadings to set.
	 */
	public void setMinorHeadings(String minorHeadings) {
		this.minorHeadings = minorHeadings;
	}
	/**
	 * @return Returns the variableFormat.
	 */
	public String getVariableFormat() {
		return variableFormat;
	}
	
	public String getVariableDescription() {
		return variableDescription;
	}
	/**
	 * @param variableFormat The variableFormat to set.
	 */
	public void setVariableFormat(String variableFormat) {
		this.variableFormat = variableFormat;
	}
	public void setVariableDescritpion(String variableDescription) {
		this.variableDescription = variableDescription;
	}
    /**
     * @return Returns the mappedVariable.
     */
    public boolean isMappedVariable() {
        return mappedVariable;
    }
    /**
     * @param mappedVariable The mappedVariable to set.
     */
    public void setMappedVariable(boolean mappedVariable) {
        this.mappedVariable = mappedVariable;
    }
    /**
     * @return Returns the notApplicable.
     */
    public boolean isNotApplicable() {
        return notApplicable;
    }
    /**
     * @param notApplicable The notApplicable to set.
     */
    public void setNotApplicable(boolean notApplicable) {
        this.notApplicable = notApplicable;
    }
    /**
     * @return Returns the unmappedVariable.
     */
    public boolean isUnmappedVariable() {
        return unmappedVariable;
    }
    /**
     * @param unmappedVariable The unmappedVariable to set.
     */
    public void setUnmappedVariable(boolean unmappedVariable) {
        this.unmappedVariable = unmappedVariable;
    }
	
	/**
	 * @return Returns the codedStatus.
	 */
	public boolean isCodedStatus() {
		return codedStatus;
	}
	/**
	 * @param codedStatus The codedStatus to set.
	 */
	public void setCodedStatus(boolean codedStatus) {
		this.codedStatus = codedStatus;
	}

	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}

	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}

	public String getIsgCatagory() {
		return isgCatagory;
	}

	public void setIsgCatagory(String isgCatagory) {
		this.isgCatagory = isgCatagory;
	}

	public String getLgCatagory() {
		return lgCatagory;
	}

	public void setLgCatagory(String lgCatagory) {
		this.lgCatagory = lgCatagory;
	}

	/**
	 * @param variableStatus the variableStatus to set
	 */
	public void setVariableStatus(String variableStatus) {
		this.variableStatus = variableStatus;
	}
	/**
	 * @return the variableStatus
	 */
	public String getVariableStatus() {
		return variableStatus;
	}

	public void setIsAdvanceSearch(String isAdvanceSearch) {
		this.isAdvanceSearch = isAdvanceSearch;
	}

	public String getIsAdvanceSearch() {
		return isAdvanceSearch;
	}

	public void setAuditLock(boolean auditLock) {
		this.auditLock = auditLock;
	}

	public boolean isAuditLock() {
		return auditLock;
	}

	public String getVariableDescForDisplay() {
		return variableDescForDisplay;
	}

	public void setVariableDescForDisplay(String variableDescForDisplay) {
		this.variableDescForDisplay = variableDescForDisplay;
	}

}
