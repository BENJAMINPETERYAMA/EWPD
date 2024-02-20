/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductAdminOptionRequest extends ProductRequest{
	
	
	  private int adminOptSysId;
		
	    
		 private int productId;
		 
	  /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }

	/**
	 * @return Returns the adminOptSysId.
	 */
	public int getAdminOptSysId() {
		return adminOptSysId;
	}
	/**
	 * @param adminOptSysId The adminOptSysId to set.
	 */
	public void setAdminOptSysId(int adminOptSysId) {
		this.adminOptSysId = adminOptSysId;
	}
		/**
		 * @return Returns the productId.
		 */
		public int getProductId() {
			return productId;
		}
		/**
		 * @param productId The productId to set.
		 */
		public void setProductId(int productId) {
			this.productId = productId;
		}
}
