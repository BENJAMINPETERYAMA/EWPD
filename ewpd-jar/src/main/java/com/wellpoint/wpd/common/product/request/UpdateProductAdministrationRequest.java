/*
 * Created on Jun 17, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProductAdministrationRequest  extends ProductRequest{
	
	private List benefitAdministrationVOList;
	
	private String mainObjectIdentifier;
	
	private List domainList;
	   
	private int entityId;
	
	private int versionNumber;
	
	private int adminOptionAssnId;
	
	private List questionnareList;
	
	private int adminlevelOptionSysId;
	
    private int benefitComponentId;
	
	private List changedIds;
	private boolean changed;
	private String bCompName;
	private int benefitAdminId;

	private boolean qstnsChanged;
	
	private int benefitId;
	/**
	 * @return Returns the adminlevelOptionSysId.
	 */
	public int getAdminlevelOptionSysId() {
		return adminlevelOptionSysId;
	}
	/**
	 * @param adminlevelOptionSysId The adminlevelOptionSysId to set.
	 */
	public void setAdminlevelOptionSysId(int adminlevelOptionSysId) {
		this.adminlevelOptionSysId = adminlevelOptionSysId;
	}
	/**
	 * @return Returns the adminOptionAssnId.
	 */
	public int getAdminOptionAssnId() {
		return adminOptionAssnId;
	}
	/**
	 * @param adminOptionAssnId The adminOptionAssnId to set.
	 */
	public void setAdminOptionAssnId(int adminOptionAssnId) {
		this.adminOptionAssnId = adminOptionAssnId;
	}
	/**
	 * @return Returns the benefitAdministrationVOList.
	 */
	public List getBenefitAdministrationVOList() {
		return benefitAdministrationVOList;
	}
	/**
	 * @param benefitAdministrationVOList The benefitAdministrationVOList to set.
	 */
	public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
		this.benefitAdministrationVOList = benefitAdministrationVOList;
	}
	/**
	 * @return Returns the domainList.
	 */
	public List getDomainList() {
		return domainList;
	}
	/**
	 * @param domainList The domainList to set.
	 */
	public void setDomainList(List domainList) {
		this.domainList = domainList;
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
	 * @return Returns the mainObjectIdentifier.
	 */
	public String getMainObjectIdentifier() {
		return mainObjectIdentifier;
	}
	/**
	 * @param mainObjectIdentifier The mainObjectIdentifier to set.
	 */
	public void setMainObjectIdentifier(String mainObjectIdentifier) {
		this.mainObjectIdentifier = mainObjectIdentifier;
	}
	/**
	 * @return Returns the questionnareList.
	 */
	public List getQuestionnareList() {
		return questionnareList;
	}
	/**
	 * @param questionnareList The questionnareList to set.
	 */
	public void setQuestionnareList(List questionnareList) {
		this.questionnareList = questionnareList;
	}
	/**
	 * @return Returns the versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
	/**
	 * @return Returns the changed.
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	/**
	 * @return Returns the qstnsChanged.
	 */
	public boolean isQstnsChanged() {
		return qstnsChanged;
	}
	/**
	 * @param qstnsChanged The qstnsChanged to set.
	 */
	public void setQstnsChanged(boolean qstnsChanged) {
		this.qstnsChanged = qstnsChanged;
	}
	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}
	/**
	 * @param compName The bCompName to set.
	 */
	public void setBCompName(String compName) {
		bCompName = compName;
	}
	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
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
}
