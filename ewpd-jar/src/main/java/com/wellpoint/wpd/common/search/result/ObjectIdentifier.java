/*
 * ObjectIdentifier.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ObjectIdentifier.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public abstract class ObjectIdentifier {
	
	private String systemId;

	/**
	 * @return Returns the systemId.
	 */
	public String getSystemId() {
		return systemId;
	}
	/**
	 * @param systemId The systemId to set.
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}
