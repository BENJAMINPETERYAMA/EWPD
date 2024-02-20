/*
 * SaveProductComponentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

import java.util.List;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductComponentRequest extends ProductRequest {

	public static final int ADD_PRODUCT_COMPONENT = 1;
	
	private int action;
	private List componentList;
	private List compNameList;
	public static final int UPDATE_SEQUENCE = 3;
	
	public static final int SAVE_BENEFIT_COMPONENT = 2;
	
	private String productFamily;
    
    
    
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the componentList.
	 */
	public List getComponentList() {
		return componentList;
	}
	/**
	 * @param componentList The componentList to set.
	 */
	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}
	
	/**
	 * @return Returns the compNameList.
	 */
	public List getCompNameList() {
		return compNameList;
	}
	/**
	 * @param compNameList The compNameList to set.
	 */
	public void setCompNameList(List compNameList) {
		this.compNameList = compNameList;
	}
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
}
