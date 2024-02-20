/*
 * Created on Aug 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.util.List;

/**
 * @author u20656
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierDefinition {

	private String code;
	private String dataType;
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
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
