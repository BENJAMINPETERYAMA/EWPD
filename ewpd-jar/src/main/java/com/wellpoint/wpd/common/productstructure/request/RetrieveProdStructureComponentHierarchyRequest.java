/*
 * RetrieveProdStructureComponentHierarchyRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or 
 * use Confidential Information without the express written
 *  agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProdStructureComponentHierarchyRequest extends WPDRequest{
	
	private ProductStructureVO productStructureVO;
	
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
	/**
	 * @return Returns the productStructureVO.
	 */
	public ProductStructureVO getProductStructureVO() {
		return productStructureVO;
	}
	/**
	 * @param productStructureVO The productStructureVO to set.
	 */
	public void setProductStructureVO(ProductStructureVO productStructureVO) {
		this.productStructureVO = productStructureVO;
	}

}
