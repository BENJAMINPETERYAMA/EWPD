/*
 * Created on Oct 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.vo;

import java.util.List;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodVO {
	
    private String spsName;

    private String reference;
    
    private String adminMethod;
    
    private int entityId;
    
    private String entityType ;
    
    private int administrationId; 
    
    private int benefitComponentId;
    
    private int adminMethodId;
    
    private int spsId;
    
    private List adminIdList;
    
    private List spsIdList;
    
    private String mode;
    
    private String benefitComponentName;

	/**
	 * @return Returns the adminIdList.
	 */
	public List getAdminIdList() {
		return adminIdList;
	}
	/**
	 * @param adminIdList The adminIdList to set.
	 */
	public void setAdminIdList(List adminIdList) {
		this.adminIdList = adminIdList;
	}
	/**
	 * @return Returns the administrationId.
	 */
	public int getAdministrationId() {
		return administrationId;
	}
	/**
	 * @param administrationId The administrationId to set.
	 */
	public void setAdministrationId(int administrationId) {
		this.administrationId = administrationId;
	}
	/**
	 * @return Returns the adminMethod.
	 */
	public String getAdminMethod() {
		return adminMethod;
	}
	/**
	 * @param adminMethod The adminMethod to set.
	 */
	public void setAdminMethod(String adminMethod) {
		this.adminMethod = adminMethod;
	}
	/**
	 * @return Returns the adminMethodId.
	 */
	public int getAdminMethodId() {
		return adminMethodId;
	}
	/**
	 * @param adminMethodId The adminMethodId to set.
	 */
	public void setAdminMethodId(int adminMethodId) {
		this.adminMethodId = adminMethodId;
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
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
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
	 * @return Returns the mode.
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * @param mode The mode to set.
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return Returns the spsId.
	 */
	public int getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(int spsId) {
		this.spsId = spsId;
	}
	/**
	 * @return Returns the spsIdList.
	 */
	public List getSpsIdList() {
		return spsIdList;
	}
	/**
	 * @param spsIdList The spsIdList to set.
	 */
	public void setSpsIdList(List spsIdList) {
		this.spsIdList = spsIdList;
	}
	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}
	/**
	 * @param spsName The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}
	
}
