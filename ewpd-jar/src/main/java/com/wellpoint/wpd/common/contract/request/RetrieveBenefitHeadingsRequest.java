/*
 * RetrieveBenefitHeadingsRequest.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitHeadingsRequest extends ContractRequest{
    
    
    private int dateSegmentId;
    
    private int productId;
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }


    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
    /**
     * Returns the productId
     * @return int productId.
     */
    public int getProductId() {
        return productId;
    }
    /**
     * Sets the productId
     * @param productId.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
}
