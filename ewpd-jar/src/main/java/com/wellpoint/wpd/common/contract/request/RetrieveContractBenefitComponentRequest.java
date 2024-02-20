/*
 * ContractbenefitComponentRetrieveRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractBenefitComponentRequest  extends ContractRequest 
{
	 private BenefitComponentVO benefitComponentVO;
	 private int mainObjVersion;
	 private int benefitComponentId; 
	 private int productId;
	 private int action;
	 public static final int UPDATE_CONTRACT_BENEFITCOMPONENT_FLAGS = 3;
	 public static final int RETRIEVE_CONTRACT_BENEFITCOMPONENT_ALL = 2;
	 public static final int RETRIEVE_BENEFITCOMPONENT_INFORMATION = 1;
	 private int dateSegmentId;
	 private int contractSysId;
	 private boolean changed;
	 private List changedIds;
	 private Map benefitHideUnhideFlagMap;
	 
	/**
	 * Returns the benefitComponentVO
	 * @return BenefitComponentVO benefitComponentVO.
	 */
	public BenefitComponentVO getBenefitComponentVO() {
		return benefitComponentVO;
	}
	/**
	 * Sets the benefitComponentVO
	 * @param benefitComponentVO.
	 */
	public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
		this.benefitComponentVO = benefitComponentVO;
	}
	/**
	 * Returns the mainObjVersion
	 * @return int mainObjVersion.
	 */
	public int getMainObjVersion() {
		return mainObjVersion;
	}
	/**
	 * Sets the mainObjVersion
	 * @param mainObjVersion.
	 */
	public void setMainObjVersion(int mainObjVersion) {
		this.mainObjVersion = mainObjVersion;
	}
	/**
	 * Returns the benefitComponentId
	 * @return int benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * Sets the benefitComponentId
	 * @param benefitComponentId.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
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
	
	/**Getter and Setter method for Benefit Component Hide Unhide Map :: by KK**/
	public Map getBenefitHideUnhideFlagMap() {
		return benefitHideUnhideFlagMap;
	}
	public void setBenefitHideUnhideFlagMap(Map benefitHideUnhideFlagMap) {
		this.benefitHideUnhideFlagMap = benefitHideUnhideFlagMap;
	}
	/** end :: by KK**/
	
	
}
