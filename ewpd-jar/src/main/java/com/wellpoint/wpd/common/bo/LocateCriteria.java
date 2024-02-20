/*
 * LocateCriteria.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateCriteria.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateCriteria {
	private int maximumResultSize ;

	/**
	 * @return
	 */
	public int getMaximumResultSize() {
		return maximumResultSize;
	}

	/**
	 * @param maximumResultSize
	 */
	public void setMaximumResultSize(int maximumResultSize) {
		this.maximumResultSize = maximumResultSize;
	}
}
