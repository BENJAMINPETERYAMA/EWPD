/*
 * Created on Sep 17, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.bo;

/**
 * @author U19129
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ItemFrequencyBO {
	
	 private int catalogId;

	 private String primaryCode;
	 
	 private String frequencyRequired;
	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return Returns the frequencyRequired.
	 */
	public String getFrequencyRequired() {
		return frequencyRequired;
	}
	/**
	 * @param frequencyRequired The frequencyRequired to set.
	 */
	public void setFrequencyRequired(String frequencyRequired) {
		this.frequencyRequired = frequencyRequired;
	}
	/**
	 * @return Returns the primaryCode.
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}
	/**
	 * @param primaryCode The primaryCode to set.
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
}
