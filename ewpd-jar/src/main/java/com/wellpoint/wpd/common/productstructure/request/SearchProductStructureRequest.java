/*
 * SearchProductStructureRequest.java
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
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureLocateCriteriaVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class SearchProductStructureRequest extends WPDRequest {
    private ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO;


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
    }


    /**
     * Returns the productStructureLocateCriteriaVO
     * 
     * @return ProductStructureLocateCriteriaVO
     *         productStructureLocateCriteriaVO.
     */

    public ProductStructureLocateCriteriaVO getProductStructureLocateCriteriaVO() {
        return productStructureLocateCriteriaVO;
    }


    /**
     * Sets the productStructureLocateCriteriaVO
     * 
     * @param productStructureLocateCriteriaVO.
     */

    public void setProductStructureLocateCriteriaVO(
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO) {
        this.productStructureLocateCriteriaVO = productStructureLocateCriteriaVO;
    }
}