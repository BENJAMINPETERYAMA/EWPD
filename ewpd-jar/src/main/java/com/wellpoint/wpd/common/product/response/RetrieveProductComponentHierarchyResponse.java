/*
 * Created on Jul 24, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductComponentHierarchyResponse extends WPDResponse {
	
	private List componentHierarchyList;

	/**
	 * @return Returns the componentHierarchyList.
	 */
	public List getComponentHierarchyList() {
		return componentHierarchyList;
	}
	/**
	 * @param componentHierarchyList The componentHierarchyList to set.
	 */
	public void setComponentHierarchyList(List componentHierarchyList) {
		this.componentHierarchyList = componentHierarchyList;
	}
}
