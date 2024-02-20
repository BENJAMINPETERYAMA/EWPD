/*
 * Created on Sep 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.bo;

/**
 * @author U13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LegacyVariableInfo extends LegacyObject{
	private String sysId;
	
	private String variableId;
	
	private String codedValue;
	private String varNotesFlag;

	/**
	 * @return Returns the varNotesFlag.
	 */
	public String getVarNotesFlag() {
		return varNotesFlag;
	}
	/**
	 * @param varNotesFlag The varNotesFlag to set.
	 */
	public void setVarNotesFlag(String varNotesFlag) {
		this.varNotesFlag = varNotesFlag;
	}
	/**
	 * @return Returns the codedValue.
	 */
	public String getCodedValue() {
		return codedValue;
	}
	/**
	 * @param codedValue The codedValue to set.
	 */
	public void setCodedValue(String codedValue) {
		this.codedValue = codedValue;
	}
	/**
	 * @return Returns the sysId.
	 */
	public String getSysId() {
		return sysId;
	}
	/**
	 * @param sysId The sysId to set.
	 */
	public void setSysId(String sysId) {
		this.sysId = sysId;
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
