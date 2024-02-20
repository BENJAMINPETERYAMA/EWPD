/*
 * Created on May 25, 2007
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
public class DeleteProductBenefitNoteResponse extends WPDResponse {
	
	private List productNotetList = new ArrayList();

	/**
	 * @return Returns the productNotetList.
	 */
	public List getProductNotetList() {
		return productNotetList;
	}
	/**
	 * @param productNotetList The productNotetList to set.
	 */
	public void setProductNotetList(List productNotetList) {
		this.productNotetList = productNotetList;
	}
}
