/*
 * RetrieveProductBenefitRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductBenefitRequest extends ProductRequest{

	public static final int RETRIEVE_GENERAL_INFO = 1;
	public static final int RETRIEVE_NONADJ_BENEFIT_MANDATES = 2;
	private int action;
	private int benefitKey;
	private int benefitMasterSystemId;
	private ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO;
	
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
	 * @return Returns the benefitKey.
	 */
	public int getBenefitKey() {
		return benefitKey;
	}
	/**
	 * @param benefitKey The benefitKey to set.
	 */
	public void setBenefitKey(int benefitKey) {
		this.benefitKey = benefitKey;
	}
	/**
	 * @return Returns the benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * @param benefitMasterSystemId The benefitMasterSystemId to set.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
	/**
	 * @return Returns the productBenefitGeneralInformationVO.
	 */
	public ProductBenefitGeneralInformationVO getProductBenefitGeneralInformationVO() {
		return productBenefitGeneralInformationVO;
	}
	/**
	 * @param productBenefitGeneralInformationVO The productBenefitGeneralInformationVO to set.
	 */
	public void setProductBenefitGeneralInformationVO(
			ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO) {
		this.productBenefitGeneralInformationVO = productBenefitGeneralInformationVO;
	}
}
