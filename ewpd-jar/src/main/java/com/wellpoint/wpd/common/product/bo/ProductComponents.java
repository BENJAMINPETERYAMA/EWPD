/*
 * ProductComponents.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductComponents {

	private List componentList; 
	
	/**
	 * Returns the componentList
	 * @return List componentList.
	 */
	public List getComponentList() {
		return componentList;
	}
	/**
	 * Sets the componentList
	 * @param componentList.
	 */
	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}
	
	/**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("componentList = ").append(componentList);
        return buffer.toString();
    }
}
