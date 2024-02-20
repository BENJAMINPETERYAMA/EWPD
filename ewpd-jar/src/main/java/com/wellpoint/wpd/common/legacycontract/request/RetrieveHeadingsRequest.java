/*
 * Created on Aug 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.request;

import java.util.Date;

import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;

/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveHeadingsRequest extends MigrationContractRequest{
	
	
	
	public static final int RETRIEVE_VARIABLE_POPUP = 3;
	public static final int RETRIEVE_VARIABLE_NOTES_POPUP = 4;
	
	public void validate(){
		
	}
	

	private int action;
	
	private int option;
	
	private String structureId;
	
	private String system;
	
	private String majorHeadingId;
	
	private String minorHeadingId;
	
	private String majorHeading;
	
	private String minorHeading;
	
	private String pvar;

	private String contractId;
	
	private String variableLongText;
	
	private Date startDate;
	
	private String variableId;
	
	private String format;
	

	
	/**
	 * @return Returns the structureId.
	 */
	public String getStructureId() {
		return structureId;
	}
	/**
	 * @param structureId The structureId to set.
	 */
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	/**
	 * @return Returns the system.
	 */
	public String getSystem() {
		return system;
	}
	/**
	 * @param system The system to set.
	 */
	public void setSystem(String system) {
		this.system = system;
	}
	/**
	 * @return Returns the majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * @param majorHeadingId The majorHeadingId to set.
	 */
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
	/**
	 * @return Returns the majorHeading.
	 */
	public String getMajorHeading() {
		return majorHeading;
	}
	/**
	 * @param majorHeading The majorHeading to set.
	 */
	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}
	/**
	 * @return Returns the minorHeading.
	 */
	public String getMinorHeading() {
		return minorHeading;
	}
	/**
	 * @param minorHeading The minorHeading to set.
	 */
	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}
	/**
	 * @return Returns the pvar.
	 */
	public String getPvar() {
		return pvar;
	}
	/**
	 * @param pvar The pvar to set.
	 */
	public void setPvar(String pvar) {
		this.pvar = pvar;
	}
	/**
	 * @return Returns the minorHeadingId.
	 */
	public String getMinorHeadingId() {
		return minorHeadingId;
	}
	/**
	 * @param minorHeadingId The minorHeadingId to set.
	 */
	public void setMinorHeadingId(String minorHeadingId) {
		this.minorHeadingId = minorHeadingId;
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
	 * @return Returns the variableLongText.
	 */
	public String getVariableLongText() {
		return variableLongText;
	}
	/**
	 * @param variableLongText The variableLongText to set.
	 */
	public void setVariableLongText(String variableLongText) {
		this.variableLongText = variableLongText;
	}
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the option.
	 */
	public int getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(int option) {
		this.option = option;
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
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
}
