/*
 * Created on Mar 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelPopUpBO {
	
	private boolean termValueCheckBx;
	
	private int standardBenefitKey;
	
	private String description;
	
	private String code;	
	
	private String selectedType;
	
	private int catalogId;


	/**
	 * @return Returns the selectedType.
	 */
	public String getSelectedType() {
		return selectedType;
	}
	/**
	 * @param selectedType The selectedType to set.
	 */
	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}
	/**
	 * @return Returns the standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * @param standardBenefitKey The standardBenefitKey to set.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
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
	public boolean isTermValueCheckBx() {
		return termValueCheckBx;
	}
	public void setTermValueCheckBx(boolean termValueCheckBx) {
		this.termValueCheckBx = termValueCheckBx;
	}
}
