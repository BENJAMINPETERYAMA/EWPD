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
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveProductBenefitNoteResponse extends WPDResponse {
	
	private boolean valid;
	private boolean success;	
	private ProductComponentBO productComponentBO;
	private List productNotetList = new ArrayList();

	/**
	 * @return Returns the productComponentBO.
	 */
	public ProductComponentBO getProductComponentBO() {
		return productComponentBO;
	}
	/**
	 * @param productComponentBO The productComponentBO to set.
	 */
	public void setProductComponentBO(ProductComponentBO productComponentBO) {
		this.productComponentBO = productComponentBO;
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
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
