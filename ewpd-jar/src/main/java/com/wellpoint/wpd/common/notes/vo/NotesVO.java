/*
 * NotesVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.vo;

import java.util.List;

/**
 * Value Object class for NotesSearch.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesVO {

	private String noteId;
	
	private int versionNo;
	
	private String noteName;
	
	private String noteType;	
	
	private List systemDomainList;			
	
	private String text;
	
	private String status;
	
	private List availBenefits;
	
	private List availBenefitComponents;
	
	private List availProducts;
	
	private List availTerms;
	
	private List availQualifiers;

	private List noteTypeList;
	
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
	 * @return Returns the systemDomainList.
	 */
	public List getSystemDomainList() {
		return systemDomainList;
	}
	/**
	 * @param systemDomainList The systemDomainList to set.
	 */
	public void setSystemDomainList(List systemDomainList) {
		this.systemDomainList = systemDomainList;
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
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	/**
	 * Returns the status
	 * @return String status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Sets the status
	 * @param status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Returns the text
	 * @return String text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets the text
	 * @param text.
	 */
	public void setText(String text) {
		this.text = text;
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
}