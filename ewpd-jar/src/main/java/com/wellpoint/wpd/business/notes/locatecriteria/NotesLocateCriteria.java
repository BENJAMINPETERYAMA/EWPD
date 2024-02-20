/*
 * Created on May 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesLocateCriteria extends LocateCriteria{

	private String noteId;
	
	private String noteParentId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private List noteSystemDomain;
	
	private List availBenefits;
	
	private List availBenefitComponents;
	
	private List availProducts;
	
	private List availTerms;
	
	private List availQualifiers;
	
	private String action;
	
	private List noteTypeList;
	
	private List lobList;
	
	private List businessEntityList;
	
	private List businessGroupList;
	
	private List marketBusinessUnitList;
	
	private int version;

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	/**
	 * @return Returns the noteParentId.
	 */
	public String getNoteParentId() {
		return noteParentId;
	}
	/**
	 * @param noteParentId The noteParentId to set.
	 */
	public void setNoteParentId(String noteParentId) {
		this.noteParentId = noteParentId;
	}
	/**
	 * @return Returns the noteSystemDomain.
	 */
	public List getNoteSystemDomain() {
		return noteSystemDomain;
	}
	/**
	 * @param noteSystemDomain The noteSystemDomain to set.
	 */
	public void setNoteSystemDomain(List noteSystemDomain) {
		this.noteSystemDomain = noteSystemDomain;
	}
	/**
	 * @return Returns the noteText.
	 */
	public String getNoteText() {
		return noteText;
	}
	/**
	 * @param noteText The noteText to set.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	/**
	 * @return Returns the noteType.
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType The noteType to set.
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return Returns the availBenefitComponents.
	 */
	public List getAvailBenefitComponents() {
		return availBenefitComponents;
	}
	/**
	 * @param availBenefitComponents The availBenefitComponents to set.
	 */
	public void setAvailBenefitComponents(List availBenefitComponents) {
		this.availBenefitComponents = availBenefitComponents;
	}
	/**
	 * @return Returns the availBenefits.
	 */
	public List getAvailBenefits() {
		return availBenefits;
	}
	/**
	 * @param availBenefits The availBenefits to set.
	 */
	public void setAvailBenefits(List availBenefits) {
		this.availBenefits = availBenefits;
	}
	/**
	 * @return Returns the availProducts.
	 */
	public List getAvailProducts() {
		return availProducts;
	}
	/**
	 * @param availProducts The availProducts to set.
	 */
	public void setAvailProducts(List availProducts) {
		this.availProducts = availProducts;
	}
	/**
	 * @return Returns the availQualifiers.
	 */
	public List getAvailQualifiers() {
		return availQualifiers;
	}
	/**
	 * @param availQualifiers The availQualifiers to set.
	 */
	public void setAvailQualifiers(List availQualifiers) {
		this.availQualifiers = availQualifiers;
	}
	/**
	 * @return Returns the availTerms.
	 */
	public List getAvailTerms() {
		return availTerms;
	}
	/**
	 * @param availTerms The availTerms to set.
	 */
	public void setAvailTerms(List availTerms) {
		this.availTerms = availTerms;
	}
	
	/**
	 * Returns the noteTypeList
	 * @return List noteTypeList.
	 */
	public List getNoteTypeList() {
		return noteTypeList;
	}
	/**
	 * Sets the noteTypeList
	 * @param noteTypeList.
	 */
	public void setNoteTypeList(List noteTypeList) {
		this.noteTypeList = noteTypeList;
	}
	
	/**
	 * Returns the businessEntityList
	 * @return List businessEntityList.
	 */
	public List getBusinessEntityList() {
		return businessEntityList;
	}
	/**
	 * Sets the businessEntityList
	 * @param businessEntityList.
	 */
	public void setBusinessEntityList(List businessEntityList) {
		this.businessEntityList = businessEntityList;
	}
	/**
	 * Returns the businessGroupList
	 * @return List businessGroupList.
	 */
	public List getBusinessGroupList() {
		return businessGroupList;
	}
	/**
	 * Sets the businessGroupList
	 * @param businessGroupList.
	 */
	public void setBusinessGroupList(List businessGroupList) {
		this.businessGroupList = businessGroupList;
	}
	/**
	 * Returns the lobList
	 * @return List lobList.
	 */
	public List getLobList() {
		return lobList;
	}
	/**
	 * Sets the lobList
	 * @param lobList.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}
	
	/**
	 * Returns the version
	 * @return int version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}
