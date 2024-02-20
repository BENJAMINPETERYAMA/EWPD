/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.vo;

import java.util.List;

/**
 * @author U12238
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentVO {

	private String noteId;
	
	private String noteName;
	
	private int entityId;
	
	private int parentId;
	
	private String entityType;
	
	private String parentType;
	
	private String availableEntityType;
	
	private int availableEntityId;
	
	private int benefitComponentId;
	
	// For BenefitComponentAttachment
	private List selectedNoteIdList;
	private String entityName;
	private List businessDomainList;
	private List sysDomainIds;
	private int version;
	private String status;
	
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
	 * @return Returns the availableEntityId.
	 */
	public int getAvailableEntityId() {
		return availableEntityId;
	}
	/**
	 * @param availableEntityId The availableEntityId to set.
	 */
	public void setAvailableEntityId(int availableEntityId) {
		this.availableEntityId = availableEntityId;
	}
	/**
	 * @return Returns the availableEntityType.
	 */
	public String getAvailableEntityType() {
		return availableEntityType;
	}
	/**
	 * @param availableEntityType The availableEntityType to set.
	 */
	public void setAvailableEntityType(String availableEntityType) {
		this.availableEntityType = availableEntityType;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	/**
	 * @return Returns the parentId.
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return Returns the parentType.
	 */
	public String getParentType() {
		return parentType;
	}
	/**
	 * @param parentType The parentType to set.
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * @return Returns the businessDomainList.
	 */
	public List getBusinessDomainList() {
		return businessDomainList;
	}
	/**
	 * @param businessDomainList The businessDomainList to set.
	 */
	public void setBusinessDomainList(List businessDomainList) {
		this.businessDomainList = businessDomainList;
	}
	/**
	 * @return Returns the sysDomainIds.
	 */
	public List getSysDomainIds() {
		return sysDomainIds;
	}
	/**
	 * @param sysDomainIds The sysDomainIds to set.
	 */
	public void setSysDomainIds(List sysDomainIds) {
		this.sysDomainIds = sysDomainIds;
	}
	/**
	 * @return Returns the selectedNoteIdList.
	 */
	public List getSelectedNoteIdList() {
		return selectedNoteIdList;
	}
	/**
	 * @param selectedNoteIdList The selectedNoteIdList to set.
	 */
	public void setSelectedNoteIdList(List selectedNoteIdList) {
		this.selectedNoteIdList = selectedNoteIdList;
	}
	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName The entityName to set.
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
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
}
