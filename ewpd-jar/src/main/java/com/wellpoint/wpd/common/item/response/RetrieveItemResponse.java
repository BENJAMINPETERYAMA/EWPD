/*
 * RetrieveItemResponse.java
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.item.bo.ItemBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaBO;
import com.wellpoint.wpd.common.item.bo.ItemSrdaHCSBO;

public class RetrieveItemResponse extends WPDResponse{
    private ItemBO itemBO;
    private List itemList;
    private ItemSrdaBO ItemsrdaBO;
    private ItemSrdaHCSBO ItemsrdaHCSBO;
    
    public ItemSrdaBO getItemsrdaBO() {
		return ItemsrdaBO;
	}
	public void setItemsrdaBO(ItemSrdaBO itemsrdaBO) {
		ItemsrdaBO = itemsrdaBO;
	}
	/**
     * @return Returns the itemList.
     */
    public List getItemList() {
        return itemList;
    }
    /**
     * @param itemList The itemList to set.
     */
    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
    /**
     * @return Returns the itemBO.
     */
    public ItemBO getItemBO() {
        return itemBO;
    }
    /**
     * @param itemBO The itemBO to set.
     */
    public void setItemBO(ItemBO itemBO) {
        this.itemBO = itemBO;
    }
	public ItemSrdaHCSBO getItemsrdaHCSBO() {
		return ItemsrdaHCSBO;
	}
	public void setItemsrdaHCSBO(ItemSrdaHCSBO itemsrdaHCSBO) {
		ItemsrdaHCSBO = itemsrdaHCSBO;
	}
}
