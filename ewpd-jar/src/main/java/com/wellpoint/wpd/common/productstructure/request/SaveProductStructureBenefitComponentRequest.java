/*
 * SaveProductStructureBenefitComponentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductStructureBenefitComponentRequest extends WPDRequest {

    public static final int CREATE_PRODUCT_STRUCTURE_BENEFIT = 1;

    public static final int UPDATE_PRODUCT_STRUCTURE_BENEFIT = 2;

    private ProductStructureVO productStructureVO;

    private int action;


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

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
     * @return Returns the action.
     */
    public int getAction() {
        return action;
    }


    /**
     * @param action
     *            The action to set.
     */
    public void setAction(int action) {
        this.action = action;
    }
}