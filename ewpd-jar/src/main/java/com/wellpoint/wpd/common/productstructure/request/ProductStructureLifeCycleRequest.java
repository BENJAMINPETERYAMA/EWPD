/*
 * ProductStructureLifeCycleRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureLocateCriteriaVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureLifeCycleRequest extends ProductStructureRequest {

    public static final int SCHEDULE_FOR_TEST_PRODUCT_STRUCTURE = 1;

    public static final int TEST_PASS_PRODUCT_STRUCTURE = 2;

    public static final int TEST_FAIL_PRODUCT_STRUCTURE = 3;

    public static final int PUBLISH_PRODUCT_STRUCTURE = 6;

    public static final int APPROVE_PRODUCT_STRUCTURE = 5;

    public static final int REJECT_PRODUCT_STRUCTURE = 4;
    
    public static final int UNLOCK_PRODUCT_STRUCTURE = 7;

    private ProductStructureVO productStructureVO;

    private int action;

    private ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO;

    private int productStructureId;


    public ProductStructureLifeCycleRequest() {
        super();
    }


    /**
     * @return Returns the productStructureVO.
     */
    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * @param productStructureVO
     *            The productStructureVO to set.
     */
    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


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


    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * @param productStructureId
     *            The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
    }

}