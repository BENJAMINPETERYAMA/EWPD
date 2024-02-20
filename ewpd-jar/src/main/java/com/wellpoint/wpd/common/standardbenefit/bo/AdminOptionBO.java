/*
 * AdminOptionBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * Business Object of Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface AdminOptionBO {

	/**
	 * @param string
	 */
	void setQualifier(String string);

	/**
	 * @param string
	 */
	void setTerm(String string);

	/**
	 * @param string
	 */
	void setAdminName(String string);

	/**
	 * @param string
	 */
	public String getTerm();
	public String getQualifier();
	public String getAdminName() ;

}
