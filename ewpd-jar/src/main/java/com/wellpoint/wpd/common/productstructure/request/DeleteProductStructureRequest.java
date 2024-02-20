/*
 * DeleteProductStructureRequest.java
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
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureLocateCriteriaVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteProductStructureRequest extends WPDRequest {

    private ProductStructureVO productStructureVO;

    private ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO;

    public static final int DELETE_BENEFIT_COMPONENT = 1;

    public static final int DELETE_PRODUCT_STRUCTURE = 2;

    private int action;

    private int benefitComponentId;

    private int productStructureId;
    
//	added for mulitiple benefit component  delete	
    private List benefitComponentList;


    /**
     * @return Returns the productStructureLocateCriteriaVO.
     */
    public ProductStructureLocateCriteriaVO getProductStructureLocateCriteriaVO() {
        return productStructureLocateCriteriaVO;
    }


    /**
     * @param productStructureLocateCriteriaVO
     *            The productStructureLocateCriteriaVO to set.
     */
    public void setProductStructureLocateCriteriaVO(
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO) {
        this.productStructureLocateCriteriaVO = productStructureLocateCriteriaVO;
    }


    /**
     * Returns the productStructureVO
     * 
     * @return ProductStructureVO productStructureVO.
     */

    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * Sets the productStructureVO
     * 
     * @param productStructureVO.
     */

    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * Returns the action
     * 
     * @return int action.
     */

    public int getAction() {
        return action;
    }


    /**
     * Sets the action
     * 
     * @param action.
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
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }
	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
}