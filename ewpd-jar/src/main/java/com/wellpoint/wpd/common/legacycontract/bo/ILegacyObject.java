/*
 * ILegacyObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public interface ILegacyObject {
	String getSystem();
	void setSystem(String system);
	void setSystemCP();
	void setSystemCP2000();
	boolean isCP();
	boolean isCP2000();
}
