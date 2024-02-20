package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.List;

public class ContractBenefitAffectedSPSBO {

	private List entitySysIds;
	private int entitySysId;
	/**
	 * @return the entitySysId
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId the entitySysId to set
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	private int productSysId;
	private int benefitCmpntSysId;
	private int benefitSysId;
	private int benefitAdmtnId;
	private int spsSysId;
	private String spsValidationFlag;
	private String processRunningFlag;
	private String processRepeatFlag;
	private String lastChangeUser;
	private Date lastChangeTime;
	private String createdUser;
	private Date createdDate;
	private int contractTierSysId;
	/**
	 * @return the entitySysIds
	 */
	public List getEntitySysIds() {
		return entitySysIds;
	}
	/**
	 * @param entitySysIds the entitySysIds to set
	 */
	public void setEntitySysIds(List entitySysIds) {
		this.entitySysIds = entitySysIds;
	}
	/**
	 * @return the productSysId
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId the productSysId to set
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return the benefitCmpntSysId
	 */
	public int getBenefitCmpntSysId() {
		return benefitCmpntSysId;
	}
	/**
	 * @param benefitCmpntSysId the benefitCmpntSysId to set
	 */
	public void setBenefitCmpntSysId(int benefitCmpntSysId) {
		this.benefitCmpntSysId = benefitCmpntSysId;
	}
	/**
	 * @return the benefitSysId
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId the benefitSysId to set
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return the benefitAdmtnId
	 */
	public int getBenefitAdmtnId() {
		return benefitAdmtnId;
	}
	/**
	 * @param benefitAdmtnId the benefitAdmtnId to set
	 */
	public void setBenefitAdmtnId(int benefitAdmtnId) {
		this.benefitAdmtnId = benefitAdmtnId;
	}
	/**
	 * @return the spsSysId
	 */
	public int getSpsSysId() {
		return spsSysId;
	}
	/**
	 * @param spsSysId the spsSysId to set
	 */
	public void setSpsSysId(int spsSysId) {
		this.spsSysId = spsSysId;
	}
	
	/**
	 * @return the spsValidationFlag
	 */
	public String getSpsValidationFlag() {
		return spsValidationFlag;
	}
	/**
	 * @param spsValidationFlag the spsValidationFlag to set
	 */
	public void setSpsValidationFlag(String spsValidationFlag) {
		this.spsValidationFlag = spsValidationFlag;
	}
	/**
	 * @return the processRunningFlag
	 */
	public String getProcessRunningFlag() {
		return processRunningFlag;
	}
	/**
	 * @param processRunningFlag the processRunningFlag to set
	 */
	public void setProcessRunningFlag(String processRunningFlag) {
		this.processRunningFlag = processRunningFlag;
	}
	/**
	 * @return the processRepeatFlag
	 */
	public String getProcessRepeatFlag() {
		return processRepeatFlag;
	}
	/**
	 * @param processRepeatFlag the processRepeatFlag to set
	 */
	public void setProcessRepeatFlag(String processRepeatFlag) {
		this.processRepeatFlag = processRepeatFlag;
	}
	/**
	 * @return the lastChangeUser
	 */
	public String getLastChangeUser() {
		return lastChangeUser;
	}
	/**
	 * @param lastChangeUser the lastChangeUser to set
	 */
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}
	/**
	 * @return the lastChangeTime
	 */
	public Date getLastChangeTime() {
		return lastChangeTime;
	}
	/**
	 * @param lastChangeTime the lastChangeTime to set
	 */
	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the contractTierSysId
	 */
	public int getContractTierSysId() {
		return contractTierSysId;
	}
	/**
	 * @param contractTierSysId the contractTierSysId to set
	 */
	public void setContractTierSysId(int contractTierSysId) {
		this.contractTierSysId = contractTierSysId;
	}

}
