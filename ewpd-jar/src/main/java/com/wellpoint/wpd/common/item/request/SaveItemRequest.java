/*
 * CreateItemRequest.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.item.vo.ItemVO;

public class SaveItemRequest extends WPDRequest {

    private boolean createFlag;
       private ItemVO itemVO;
       
       private String srdaFlag;
       


    /**
     * Returns the createFlag
     * 
     * @return boolean createFlag.
     */
    public boolean isCreateFlag() {
        return createFlag;
    }


    /**
     * Sets the createFlag
     * 
     * @param createFlag.
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }


    /**
     * @return Returns the itemVO.
     */
    public ItemVO getItemVO() {
        return itemVO;
    }


    /**
     * @param itemVO
     *            The itemVO to set.
     */
    public void setItemVO(ItemVO itemVO) {
        this.itemVO = itemVO;
    }

    

    /**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}


	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}


	public void validate() throws ValidationException {

        if (itemVO.getCatalogId() == 0)
            throw new ValidationException("Catalog Name is Missing", null, null);

        if (null == this.itemVO.getDescription()
                || "".equals(this.itemVO.getDescription()))
            throw new ValidationException("Description is Missing", null, null);

        if (null == this.itemVO.getPrimaryCode()
                || "".equals(this.itemVO.getPrimaryCode()))
            throw new ValidationException("Primary Code is Missing", null, null);

    }
}