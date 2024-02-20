/*
 * Created on May 18, 2007
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
public class SaveProductNoteResponse extends WPDResponse {
	private boolean valid;
	private boolean success;	
	private List productNotetList = new ArrayList();
	
	/**
	 * Returns the success.
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * Sets the success.
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

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
	/**
	 * Returns the valid.
	 * @return boolean valid.
	 */
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * Sets the valid.
	 * @param valid. 
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
