/*
 * RetrieveComponentFromTreeRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveComponentFromTreeRequest extends WPDRequest {
	
	public static final int PRODUCT_STRUCTURE_BENEFIT_RETRIEVE_ALL_DETAILS = 1;
	
	public static final int PRODUCT_STRUCTURE_BENEFIT_RETRIEVE = 2;
	
	public static final int PRODUCT_STRUCTURE_BENEFIT_SAVE = 3;

    private int benefitComponentId;
    
    private String benefitComponentName;

    private ProductStructureVO productStructure;
    
    private StandardBenefitBO standardBenefitVO;
    
    private int action;
    
    private List benefitDetailsList;
    
    private boolean showHiddenStatus;
    
    private String benefitComponentHideFlag;
    
    


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }


    /**
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */

    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the productStructure
     * 
     * @return ProductStructureVO productStructure.
     */

    public ProductStructureVO getProductStructure() {
        return productStructure;
    }


    /**
     * Sets the productStructure
     * 
     * @param productStructure.
     */

    public void setProductStructure(ProductStructureVO productStructure) {
        this.productStructure = productStructure;
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
	 * @return Returns the standardBenefitVO.
	 */
	public StandardBenefitBO getStandardBenefitVO() {
		return standardBenefitVO;
	}
	/**
	 * @param standardBenefitVO The standardBenefitVO to set.
	 */
	public void setStandardBenefitVO(StandardBenefitBO standardBenefitVO) {
		this.standardBenefitVO = standardBenefitVO;
	}
	
	
	/**
	 * @return Returns the benefitDetailsList.
	 */
	public List getBenefitDetailsList() {
		return benefitDetailsList;
	}
	/**
	 * @param benefitDetailsList The benefitDetailsList to set.
	 */
	public void setBenefitDetailsList(List benefitDetailsList) {
		this.benefitDetailsList = benefitDetailsList;
	}
	
	
	/**
	 * @return Returns the showHiddenStatus.
	 */
	public boolean isShowHiddenStatus() {
		return showHiddenStatus;
	}
	/**
	 * @param showHiddenStatus The showHiddenStatus to set.
	 */
	public void setShowHiddenStatus(boolean showHiddenStatus) {
		this.showHiddenStatus = showHiddenStatus;
	}
	
	/**
	 * @return Returns the benefitComponentHideFlag.
	 */
	public String getBenefitComponentHideFlag() {
		return benefitComponentHideFlag;
	}
	/**
	 * @param benefitComponentHideFlag The benefitComponentHideFlag to set.
	 */
	public void setBenefitComponentHideFlag(String benefitComponentHideFlag) {
		this.benefitComponentHideFlag = benefitComponentHideFlag;
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