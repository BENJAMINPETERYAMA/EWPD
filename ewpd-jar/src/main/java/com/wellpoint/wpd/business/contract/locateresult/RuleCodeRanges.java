/*
 * Created on May 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.locateresult;

/**
 * @author u13531
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleCodeRanges {
	
	private String codeType;
	private String codeTypeLowVal;
	private String codeTypeHighVal;
	//ICD10 Bug Fix 26-09-11
	private String icdVersionIndicator;

	
	
	/**
	 * @return Returns the codeType.
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * @param codeType The codeType to set.
	 */
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	/**
	 * @return Returns the codeTypeHighVal.
	 */
	public String getCodeTypeHighVal() {
		return codeTypeHighVal;
	}
	/**
	 * @param codeTypeHighVal The codeTypeHighVal to set.
	 */
	public void setCodeTypeHighVal(String codeTypeHighVal) {
		this.codeTypeHighVal = codeTypeHighVal;
	}
	/**
	 * @return Returns the codeTypeLowVal.
	 */
	public String getCodeTypeLowVal() {
		return codeTypeLowVal;
	}
	/**
	 * @param codeTypeLowVal The codeTypeLowVal to set.
	 */
	public void setCodeTypeLowVal(String codeTypeLowVal) {
		this.codeTypeLowVal = codeTypeLowVal;
	}
	public void setIcdVersionIndicator(String icdVersionIndicator) {
		this.icdVersionIndicator = icdVersionIndicator;
	}
	public String getIcdVersionIndicator() {
		return icdVersionIndicator;
	}
}
