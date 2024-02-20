/*
 * Item.java
 * 
 * © 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Item {
	
	private String itemId;
	
	private int catalogId;

	
	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
