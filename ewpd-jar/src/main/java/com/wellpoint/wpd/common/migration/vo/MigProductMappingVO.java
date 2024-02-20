/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.vo;


/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MigProductMappingVO {

	private String contractId;
	private String product;
	private int benefitComponentId;
	private int stdBenefitId;
	private String bftLineDescription;
	private String bftPva;
	private String bftReference;
	private String value;
	private String variableId = "";
	private int bftLineId;
	private int flag;
	private String varDetails;
	private String format;
	private String pva;
	private int migContractSysId;
	private int mappingSysId;
	private int dateSegmentId;
	private String deleteFlag;
	private String notesFlag;
	private String varNoteFlag;
	
	/**
	 * @return Returns the notesFlag.
	 */
	public String getNotesFlag() {
		return notesFlag;
	}
	/**
	 * @param notesFlag The notesFlag to set.
	 */
	public void setNotesFlag(String notesFlag) {
		this.notesFlag = notesFlag;
	}
    /**
     * @return deleteFlag
     * 
     * Returns the deleteFlag.
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }
    /**
     * @param deleteFlag
     * 
     * Sets the deleteFlag.
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the mappingSysId.
	 */
	public int getMappingSysId() {
		return mappingSysId;
	}
	/**
	 * @param mappingSysId The mappingSysId to set.
	 */
	public void setMappingSysId(int mappingSysId) {
		this.mappingSysId = mappingSysId;
	}
	/**
	 * @return Returns the migContractSysId.
	 */
	public int getMigContractSysId() {
		return migContractSysId;
	}
	/**
	 * @param migContractSysId The migContractSysId to set.
	 */
	public void setMigContractSysId(int migContractSysId) {
		this.migContractSysId = migContractSysId;
	}
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}
	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return Returns the varDetails.
	 */
	public String getVarDetails() {
		return varDetails;
	}
	/**
	 * @param varDetails The varDetails to set.
	 */
	public void setVarDetails(String varDetails) {
		this.varDetails = varDetails;
	}
	/**
	 * @return Returns the bftLineId.
	 */
	public int getBftLineId() {
		return bftLineId;
	}
	/**
	 * @param bftLineId The bftLineId to set.
	 */
	public void setBftLineId(int bftLineId) {
		this.bftLineId = bftLineId;
	}
	/**
	 * @return Returns the variableId.
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * @param variableId The variableId to set.
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	
	/**
	 * @return Returns the flag.
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Returns the bftLineDescription.
	 */
	public String getBftLineDescription() {
//		return modifyData(bftLineDescription,15);
		return bftLineDescription;
	}
	/**
	 * @param bftLineDescription The bftLineDescription to set.
	 */
	public void setBftLineDescription(String bftLineDescription) {
		this.bftLineDescription = bftLineDescription;
	}
	/**
	 * @return Returns the bftPva.
	 */
	public String getBftPva() {
//		return modifyData(bftPva,5);
		return bftPva;
	}
	/**
	 * @param bftPva The bftPva to set.
	 */
	public void setBftPva(String bftPva) {
		this.bftPva = bftPva;
	}
	/**
	 * @return Returns the bftReference.
	 */
	public String getBftReference() {
//		return modifyData(bftReference,10);
		return bftReference;
	}
	/**
	 * @param bftReference The bftReference to set.
	 */
	public void setBftReference(String bftReference) {
		this.bftReference = bftReference;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the product.
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product The product to set.
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return Returns the stdBenefitId.
	 */
	public int getStdBenefitId() {
		return stdBenefitId;
	}
	/**
	 * @param stdBenefitId The stdBenefitId to set.
	 */
	public void setStdBenefitId(int stdBenefitId) {
		this.stdBenefitId = stdBenefitId;
	}

	/**
	 * @return Returns the varNoteFlag.
	 */
	public String getVarNoteFlag() {
		return varNoteFlag;
	}
	/**
	 * @param varNoteFlag The varNoteFlag to set.
	 */
	public void setVarNoteFlag(String varNoteFlag) {
		this.varNoteFlag = varNoteFlag;
	}
}
