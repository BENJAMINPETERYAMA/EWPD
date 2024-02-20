/*
 * CreateItemResponse.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;

public class SaveItemResponse extends WPDResponse {

    private ItemBO itemBO;

    private boolean errorFlag;
    
    private ItemSrdaHCSBO itemSrdaHCSBO;
    
    private ItemSrdaBO itemsrdaBO;


    public ItemSrdaBO getItemsrdaBO() {
		return itemsrdaBO;
	}


	public void setItemsrdaBO(ItemSrdaBO itemsrdaBO) {
		this.itemsrdaBO = itemsrdaBO;
	}


	/**
     * @return Returns the errorFlag.
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }


    /**
     * @param errorFlag
     *            The errorFlag to set.
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }


    /**
     * @return Returns the itemBO.
     */
    public ItemBO getItemBO() {
        return itemBO;
    }


    /**
     * @param itemBO
     *            The itemBO to set.
     */
    public void setItemBO(ItemBO itemBO) {
        this.itemBO = itemBO;
    }


	/**
	 * @return the itemSrdaHCSBO
	 */
	public ItemSrdaHCSBO getItemSrdaHCSBO() {
		return itemSrdaHCSBO;
	}


	/**
	 * @param itemSrdaHCSBO the itemSrdaHCSBO to set
	 */
	public void setItemSrdaHCSBO(ItemSrdaHCSBO itemSrdaHCSBO) {
		this.itemSrdaHCSBO = itemSrdaHCSBO;
	}

}