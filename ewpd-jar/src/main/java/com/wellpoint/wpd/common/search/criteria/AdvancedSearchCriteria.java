/*
 * AdvancedSearchCriteria.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.criteria;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AdvancedSearchCriteria.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class AdvancedSearchCriteria extends SearchCriteria {

	private List advancedSearchObjects;
	/**
	 * @return Returns the advancedSearchObjects.
	 */
	public List getAdvancedSearchObjects() {
		return advancedSearchObjects;
	}
	/**
	 * @param advancedSearchObjects The advancedSearchObjects to set.
	 */
	public void setAdvancedSearchObjects(List advancedSearchObject) {
		this.advancedSearchObjects = advancedSearchObject;
	}
}
