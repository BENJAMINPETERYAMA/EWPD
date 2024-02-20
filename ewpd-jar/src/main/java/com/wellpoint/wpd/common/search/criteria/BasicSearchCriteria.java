/*
 * BasicSearchCriteria.java
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
 * @version $Id: BasicSearchCriteria.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class BasicSearchCriteria extends SearchCriteria {

	private List basicSearchObjects;
	private BasicAttribute basicAttribute;
	/**
	 * @return Returns the basicSearchObjects.
	 */
	public List getBasicSearchObjects() {
		return basicSearchObjects;
	}
	/**
	 * @param basicSearchObjects The basicSearchObjects to set.
	 */
	public void setBasicSearchObjects(List basicSearchObject) {
		this.basicSearchObjects = basicSearchObject;
	}
	/**
	 * @return Returns the basicAttribute.
	 */
	public BasicAttribute getBasicAttribute() {
		return basicAttribute;
	}
	/**
	 * @param basicAttribute The basicAttribute to set.
	 */
	public void setBasicAttribute(BasicAttribute basicAttribute) {
		this.basicAttribute = basicAttribute;
	}
}
