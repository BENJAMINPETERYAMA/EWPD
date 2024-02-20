/*
 * Created on Jul 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;

/**
 * @author U13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductComponentHierarchyRequest  extends WPDRequest{

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	private ProductKeyObject productKeyObject;


	/**
	 * @return Returns the productKeyObject.
	 */
	public ProductKeyObject getProductKeyObject() {
		return productKeyObject;
	}
	/**
	 * @param productKeyObject The productKeyObject to set.
	 */
	public void setProductKeyObject(ProductKeyObject productKeyObject) {
		this.productKeyObject = productKeyObject;
	}
}
