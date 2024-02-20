/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.vo;


/**
 * @author U12238
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminAttachmentVO {

	private String adminId;
	
	private String adminName;
	
	private int entityId;
	
	private int parentId;
	
	private String entityType;
	
	private String parentType;
	
	private String availableEntityType;
	
	private int availableEntityId;
	
	
	
	
	/**
	 * @return Returns the adminId.
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return Returns the adminName.
	 */
	public String getAdminName() {
		return adminName;
	}
	/**
	 * @param adminName The adminName to set.
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
}
