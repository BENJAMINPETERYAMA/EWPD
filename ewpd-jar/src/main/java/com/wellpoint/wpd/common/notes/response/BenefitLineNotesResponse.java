/*
 * Created on Aug 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLineNotesResponse extends WPDResponse  {
	
	private String primaryId;
	private String primaryType;
	private String secondaryID;
	private String secondaryType;
	private String benefitComponentId;
	private List benefitLineNotesList;
		
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
		
	/**
	 * @return Returns the benefitLineNotesList.
	 */
	public List getBenefitLineNotesList() {
		return benefitLineNotesList;
	}
	/**
	 * @param benefitLineNotesList The benefitLineNotesList to set.
	 */
	public void setBenefitLineNotesList(List benefitLineNotesList) {
		this.benefitLineNotesList = benefitLineNotesList;
	}
}
