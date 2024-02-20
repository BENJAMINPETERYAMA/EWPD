/*
 * Created on Mar 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductLocatePreviousVersionsCriteria extends LocateCriteria{

	private int productId;
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
