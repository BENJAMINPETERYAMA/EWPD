/*
 * NotesAttachmentOverrideVO.java
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
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentOverrideVO {

    private int noteId;
	
	private String noteName;
	
	private String noteDesc;
	
	private int primaryEntityId;
	
	private String primaryEntityType;
	
	private int secondaryEntityId;
	
	private String secondaryEntityType;
	
	private int benefitComponentId;
	
	private String term;
	
	private String qualifier;
	
	private String overrideStatus;
	
	private int dateSegmentId;
	
	private List primaryEntityIdList;
		
	private List secondaryEntityIdList;
	
	private int benefitId;
	
	private int tierSysId;
    
	
    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
    /**
     * Returns the overrideStatus
     * @return String overrideStatus.
     */
    public String getOverrideStatus() {
        return overrideStatus;
    }
    /**
     * Sets the overrideStatus
     * @param overrideStatus.
     */
    public void setOverrideStatus(String overrideStatus) {
        this.overrideStatus = overrideStatus;
    }
    /**
     * @return benefitComponentId
     * 
     * Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId
     * 
     * Sets the benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * @return noteDesc
     * 
     * Returns the noteDesc.
     */
    public String getNoteDesc() {
        return noteDesc;
    }
    /**
     * @param noteDesc
     * 
     * Sets the noteDesc.
     */
    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }
    /**
     * @return noteId
     * 
     * Returns the noteId.
     */
    public int getNoteId() {
        return noteId;
    }
    /**
     * @param noteId
     * 
     * Sets the noteId.
     */
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
    /**
     * @return noteName
     * 
     * Returns the noteName.
     */
    public String getNoteName() {
        return noteName;
    }
    /**
     * @param noteName
     * 
     * Sets the noteName.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    /**
     * @return primaryEntityId
     * 
     * Returns the primaryEntityId.
     */
    public int getPrimaryEntityId() {
        return primaryEntityId;
    }
    /**
     * @param primaryEntityId
     * 
     * Sets the primaryEntityId.
     */
    public void setPrimaryEntityId(int primaryEntityId) {
        this.primaryEntityId = primaryEntityId;
    }
    /**
     * @return primaryEntityType
     * 
     * Returns the primaryEntityType.
     */
    public String getPrimaryEntityType() {
        return primaryEntityType;
    }
    /**
     * @param primaryEntityType
     * 
     * Sets the primaryEntityType.
     */
    public void setPrimaryEntityType(String primaryEntityType) {
        this.primaryEntityType = primaryEntityType;
    }
    /**
     * @return qualifier
     * 
     * Returns the qualifier.
     */
    public String getQualifier() {
        return qualifier;
    }
    /**
     * @param qualifier
     * 
     * Sets the qualifier.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    /**
     * @return secondaryEntityId
     * 
     * Returns the secondaryEntityId.
     */
    public int getSecondaryEntityId() {
        return secondaryEntityId;
    }
    /**
     * @param secondaryEntityId
     * 
     * Sets the secondaryEntityId.
     */
    public void setSecondaryEntityId(int secondaryEntityId) {
        this.secondaryEntityId = secondaryEntityId;
    }
    /**
     * @return secondaryEntityType
     * 
     * Returns the secondaryEntityType.
     */
    public String getSecondaryEntityType() {
        return secondaryEntityType;
    }
    /**
     * @param secondaryEntityType
     * 
     * Sets the secondaryEntityType.
     */
    public void setSecondaryEntityType(String secondaryEntityType) {
        this.secondaryEntityType = secondaryEntityType;
    }
    /**
     * @return term
     * 
     * Returns the term.
     */
    public String getTerm() {
        return term;
    }
    /**
     * @param term
     * 
     * Sets the term.
     */
    public void setTerm(String term) {
        this.term = term;
    }
	/**
	 * @return Returns the secondaryEntityIdList.
	 */
	public List getSecondaryEntityIdList() {
		return secondaryEntityIdList;
	}
	/**
	 * @param secondaryEntityIdList The secondaryEntityIdList to set.
	 */
	public void setSecondaryEntityIdList(List secondaryEntityIdList) {
		this.secondaryEntityIdList = secondaryEntityIdList;
	}
    /**
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * @param benefitId The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
	/**
	 * @return Returns the primaryEntityIdList.
	 */
	public List getPrimaryEntityIdList() {
		return primaryEntityIdList;
	}
	/**
	 * @param primaryEntityIdList The primaryEntityIdList to set.
	 */
	public void setPrimaryEntityIdList(List primaryEntityIdList) {
		this.primaryEntityIdList = primaryEntityIdList;
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
