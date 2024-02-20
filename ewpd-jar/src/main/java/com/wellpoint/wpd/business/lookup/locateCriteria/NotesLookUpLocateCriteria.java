/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.lookup.locateCriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author U12238
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesLookUpLocateCriteria extends LocateCriteria {

	private int entityId;

	private String entityType;

	private int availableEntityId;

	private String availableEntityType;

	private int benefitComponentId;

	private int action;

	private String overrideStatus;

	private String noteName;

	private List entityIdList;

	private int benefitId;

	private String adminOptionEntityType;
	
	private List secondaryEntityIdList;
	
	private int tierSysId;

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
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
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
	 * @return Returns the entityIdList.
	 */
	public List getEntityIdList() {
		return entityIdList;
	}

	/**
	 * @param entityIdList The entityIdList to set.
	 */
	public void setEntityIdList(List entityIdList) {
		this.entityIdList = entityIdList;
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
	 * @return Returns the adminOptionEntityType.
	 */
	public String getAdminOptionEntityType() {
		return adminOptionEntityType;
	}

	/**
	 * @param adminOptionEntityType The adminOptionEntityType to set.
	 */
	public void setAdminOptionEntityType(String adminOptionEntityType) {
		this.adminOptionEntityType = adminOptionEntityType;
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