/*
 * StructureVariable.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class LegacyVariable extends LegacyObject {
	/*------- Variable Master Attributes ------*/
	String variableId;
	String variableText;
	String variableLongText;
	String category;
	String format;
	String providerArrangement;	
	/*------- Contract Attributes -------------*/
	String contractId;
	Date startDate;
	String codedValue;	
	/*------- Structure Attributes-------------*/
	String structureId;
	String majorHeadingId;
	String minorHeadingId;
	String strAssocociationId;
	/*--------------Note Attributes------------*/
	String notes;
	String noteNumber;
	String varNoteFlag;
	
	
	/**
	 * @return Returns the noteNumber.
	 */
	public String getNoteNumber() {
		return noteNumber;
	}
	/**
	 * @param noteNumber The noteNumber to set.
	 */
	public void setNoteNumber(String noteNumber) {
		this.noteNumber = noteNumber;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public LegacyVariable(String system){
		super(system);
	}
	/**
	 * Returns the variableId.
	 * @return String variableId.
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * Sets the variableId.
	 * @param variableId.
	 */
	
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * Returns the variableText.
	 * @return String variableText.
	 */
	public String getVariableText() {
		return variableText;
	}
	/**
	 * Sets the variableText.
	 * @param variableText.
	 */
	
	public void setVariableText(String variableText) {
		this.variableText = variableText;
	}
	/**
	 * Returns the variableLongText.
	 * @return String variableLongText.
	 */
	public String getVariableLongText() {
		return variableLongText;
	}
	/**
	 * Sets the variableLongText.
	 * @param variableLongText.
	 */
	
	public void setVariableLongText(String variableLongText) {
		this.variableLongText = variableLongText;
	}
	/**
	 * Returns the category.
	 * @return String category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * Sets the category.
	 * @param category.
	 */
	
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * Returns the format.
	 * @return String format.
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * Sets the format.
	 * @param format.
	 */
	
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * Returns the providerArrangement.
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement.
	 * @param providerArrangement.
	 */
	
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the contractId.
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId.
	 * @param contractId.
	 */
	
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the startDate.
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate.
	 * @param startDate.
	 */
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the structureId.
	 * @return String structureId.
	 */
	public String getStructureId() {
		return structureId;
	}
	/**
	 * Sets the structureId.
	 * @param structureId.
	 */
	
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	/**
	 * Returns the majorHeadingId.
	 * @return String majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * Sets the majorHeadingId.
	 * @param majorHeadingId.
	 */
	
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
	/**
	 * Returns the minorHeadingId.
	 * @return String minorHeadingId.
	 */
	public String getMinorHeadingId() {
		return minorHeadingId;
	}
	/**
	 * Sets the minorHeadingId.
	 * @param minorHeadingId.
	 */
	
	public void setMinorHeadingId(String minorHeadingId) {
		this.minorHeadingId = minorHeadingId;
	}
	/**
	 * Returns the strAssocociationId.
	 * @return String strAssocociationId.
	 */
	public String getStrAssocociationId() {
		return strAssocociationId;
	}
	/**
	 * Sets the strAssocociationId.
	 * @param strAssocociationId.
	 */
	
	public void setStrAssocociationId(String strAssocociationId) {
		this.strAssocociationId = strAssocociationId;
	}
	/**
	 * Returns the codedValue.
	 * @return String codedValue.
	 */
	public String getCodedValue() {
		return codedValue;
	}
	/**
	 * Sets the codedValue.
	 * @param codedValue.
	 */
	
	public void setCodedValue(String codedValue) {
		this.codedValue = codedValue;
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
