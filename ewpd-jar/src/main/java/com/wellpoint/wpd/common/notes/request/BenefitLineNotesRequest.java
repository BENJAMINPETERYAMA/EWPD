/*
 * Created on Aug 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineNotesRequest extends WPDRequest{
	
	private String primaryId;
	private String primaryType;
	private String secondaryID;
	private String secondaryType;
	private String benefitComponentId;
	private int tierSysId;
	
	private int maxResultSize;

	
	
	/**
	 * @return Returns the benefitComponentId.
	 */
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the primaryId.
	 */
	public String getPrimaryId() {
		return primaryId;
	}
	/**
	 * @param primaryId The primaryId to set.
	 */
	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}
	/**
	 * @return Returns the primaryType.
	 */
	public String getPrimaryType() {
		return primaryType;
	}
	/**
	 * @param primaryType The primaryType to set.
	 */
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	/**
	 * @return Returns the secondaryID.
	 */
	public String getSecondaryID() {
		return secondaryID;
	}
	/**
	 * @param secondaryID The secondaryID to set.
	 */
	public void setSecondaryID(String secondaryID) {
		this.secondaryID = secondaryID;
	}
	/**
	 * @return Returns the secondaryType.
	 */
	public String getSecondaryType() {
		return secondaryType;
	}
	/**
	 * @param secondaryType The secondaryType to set.
	 */
	public void setSecondaryType(String secondaryType) {
		this.secondaryType = secondaryType;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the maxResultSize.
	 */
	public int getMaxResultSize() {
		return maxResultSize;
	}
	/**
	 * @param maxResultSize The maxResultSize to set.
	 */
	public void setMaxResultSize(int maxResultSize) {
		this.maxResultSize = maxResultSize;
	}
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
}
