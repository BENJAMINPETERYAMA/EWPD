/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.lookup.locateCriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 *  *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesOverrideLocateCriteria extends LocateCriteria {

	private int primaryEntityId;
	private String primaryEntityType; 
	private int secondaryEntityId;
	private String secondaryEntityType;
	private int benefitComponentId ;
	 
	 
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
	 * @return Returns the primaryEntityId.
	 */
	public int getPrimaryEntityId() {
		return primaryEntityId;
	}
	/**
	 * @param primaryEntityId The primaryEntityId to set.
	 */
	public void setPrimaryEntityId(int primaryEntityId) {
		this.primaryEntityId = primaryEntityId;
	}
	/**
	 * @return Returns the primaryEntityType.
	 */
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	/**
	 * @param primaryEntityType The primaryEntityType to set.
	 */
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	/**
	 * @return Returns the secondaryEntityId.
	 */
	public int getSecondaryEntityId() {
		return secondaryEntityId;
	}
	/**
	 * @param secondaryEntityId The secondaryEntityId to set.
	 */
	public void setSecondaryEntityId(int secondaryEntityId) {
		this.secondaryEntityId = secondaryEntityId;
	}
	/**
	 * @return Returns the secondaryEntityType.
	 */
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	/**
	 * @param secondaryEntityType The secondaryEntityType to set.
	 */
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}
	
}
