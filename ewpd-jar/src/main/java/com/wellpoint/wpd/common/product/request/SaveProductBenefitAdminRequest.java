/*
 * SaveProductBenefitAdminRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.vo.ProductVO;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductBenefitAdminRequest extends ProductRequest {
	private List benefitAdministrationVOList;
	private int productBenefitAdminId;
	private int productAdminOptionId;
	private int benefitComponentId;
	private int action;
	private ProductVO productVO;
	private int adminLevelOptionAssnId;
	private boolean answerOverrideFlag;
	private boolean hideStatusFlag;
	private boolean qstnsChanged;
	private List changedIds;
	private int benefitId;
	private String benefitComponentName;
	
	
	
	 //enhancement
    private String adminOptionHideFlag;
	
	
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * Returns the benefitAdministrationVOList
	 * @return List benefitAdministrationVOList.
	 */
	public List getBenefitAdministrationVOList() {
		return benefitAdministrationVOList;
	}
	/**
	 * Sets the benefitAdministrationVOList
	 * @param benefitAdministrationVOList.
	 */
	public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
		this.benefitAdministrationVOList = benefitAdministrationVOList;
	}
	/**
	 * Returns the productVO
	 * @return ProductVO productVO.
	 */
	public ProductVO getProductVO() {
		return productVO;
	}
	/**
	 * Sets the productVO
	 * @param productVO.
	 */
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the productAdminOptionId
	 * @return int productAdminOptionId.
	 */
	public int getProductAdminOptionId() {
		return productAdminOptionId;
	}
	/**
	 * Sets the productAdminOptionId
	 * @param productAdminOptionId.
	 */
	public void setProductAdminOptionId(int productAdminOptionId) {
		this.productAdminOptionId = productAdminOptionId;
	}
	/**
	 * Returns the productBenefitAdminId
	 * @return int productBenefitAdminId.
	 */
	public int getProductBenefitAdminId() {
		return productBenefitAdminId;
	}
	/**
	 * Sets the productBenefitAdminId
	 * @param productBenefitAdminId.
	 */
	public void setProductBenefitAdminId(int productBenefitAdminId) {
		this.productBenefitAdminId = productBenefitAdminId;
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
    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
    }
	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}
	/**
	 * @param adminOptionHideFlag The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}
	/**
	 * @return Returns the answerOverrideFlag.
	 */
	public boolean getAnswerOverrideFlag() {
		return answerOverrideFlag;
	}
	/**
	 * @param answerOverrideFlag The answerOverrideFlag to set.
	 */
	public void setAnswerOverrideFlag(boolean answerOverrideFlag) {
		this.answerOverrideFlag = answerOverrideFlag;
	}
	/**
	 * @return Returns the hideStatusFlag.
	 */
	public boolean getHideStatusFlag() {
		return hideStatusFlag;
	}
	/**
	 * @param hideStatusFlag The hideStatusFlag to set.
	 */
	public void setHideStatusFlag(boolean hideStatusFlag) {
		this.hideStatusFlag = hideStatusFlag;
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
}
