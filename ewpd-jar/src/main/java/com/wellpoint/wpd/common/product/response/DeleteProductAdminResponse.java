/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteProductAdminResponse extends WPDResponse {
	
	private List productAdminList = new ArrayList();

	
	/**
	 * @return Returns the productAdminList.
	 */
	public List getProductAdminList() {
		return productAdminList;
	}
	/**
	 * @param productAdminList The productAdminList to set.
	 */
	public void setProductAdminList(List productAdminList) {
		this.productAdminList = productAdminList;
	}
}
