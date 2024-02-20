/*
 * DatafeedRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DatafeedRequest extends ContractRequest {

	private String status;

	private int action;

	private int contractSysId;

	private int productId;

	private int benefitComponentId;

	private int benefitAdministrationId;

	private int adminLevelType;

	private int benefitId;

	private int entitySystemId;

	private String entityType;

	private String lastUpdatedUser;

	private Date lastUpdatedTimeStamp;

	private DataFeedStatus dataFeedStatus;

	private int dateSegmentSysId;

	private List entityIdList;

	private String referenceId;
	
	private List deletedDSList;
	
	private List rootDeleteList;
	
	private boolean rootDeleted;
	
	private List tierCriteriaVal;

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
	 * @return Returns the dataFeedStatus.
	 */
	public DataFeedStatus getDataFeedStatus() {
		return dataFeedStatus;
	}

	/**
	 * @param dataFeedStatus The dataFeedStatus to set.
	 */
	public void setDataFeedStatus(DataFeedStatus dataFeedStatus) {
		this.dataFeedStatus = dataFeedStatus;
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
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}

	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}

	/**
	 * Returns the entitySystemId
	 * @return int entitySystemId.
	 */
	public int getEntitySystemId() {
		return entitySystemId;
	}

	/**
	 * Sets the entitySystemId
	 * @param entitySystemId.
	 */
	public void setEntitySystemId(int entitySystemId) {
		this.entitySystemId = entitySystemId;
	}

	/**
	 * Returns the entityType
	 * @return String entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * Sets the entityType
	 * @param entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * Returns the lastUpdatedTimeStamp
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}

	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}

	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}

	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}

	/**
	 * Returns the benefitAdministratinId
	 * @return int benefitAdministratinId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}

	/**
	 * Sets the benefitAdministratinId
	 * @param benefitAdministratinId.
	 */
	public void setBenefitAdministrationId(int benefitAdministratinId) {
		this.benefitAdministrationId = benefitAdministratinId;
	}

	/**
	 * Returns the adminLevelType
	 * @return int adminLevelType.
	 */
	public int getAdminLevelType() {
		return adminLevelType;
	}

	/**
	 * Sets the adminLevelType
	 * @param adminLevelType.
	 */
	public void setAdminLevelType(int adminLevelType) {
		this.adminLevelType = adminLevelType;
	}

	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	/**
	 * @return Returns the deletedDSList.
	 */
	public List getDeletedDSList() {
		return deletedDSList;
	}
	/**
	 * @param deletedDSList The deletedDSList to set.
	 */
	public void setDeletedDSList(List deletedDSList) {
		this.deletedDSList = deletedDSList;
	}
	/**
	 * @return Returns the rootDeleteList.
	 */
	public List getRootDeleteList() {
		return rootDeleteList;
	}
	/**
	 * @param rootDeleteList The rootDeleteList to set.
	 */
	public void setRootDeleteList(List rootDeleteList) {
		this.rootDeleteList = rootDeleteList;
	}
	/**
	 * @return Returns the rootDeleted.
	 */
	public boolean isRootDeleted() {
		return rootDeleted;
	}
	/**
	 * @param rootDeleted The rootDeleted to set.
	 */
	public void setRootDeleted(boolean rootDeleted) {
		this.rootDeleted = rootDeleted;
	}
	/**
	 * @return Returns the tierCriteriaVal.
	 */
	public List getTierCriteriaVal() {
		return tierCriteriaVal;
	}
	/**
	 * @param tierCriteriaVal The tierCriteriaVal to set.
	 */
	public void setTierCriteriaVal(List tierCriteriaVal) {
		this.tierCriteriaVal = tierCriteriaVal;
	}
}

