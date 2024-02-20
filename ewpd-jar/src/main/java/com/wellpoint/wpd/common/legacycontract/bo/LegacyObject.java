/*
 * LegacyObject.java
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
public class LegacyObject {
	public static final String SYSTEM_CP2000 = "CP2000";
	public static final String SYSTEM_CP = "CP";
	private String system;

	public LegacyObject(String system) {
		this.system = system;
	}
	
	public LegacyObject() {
	}
	
	public String getSystem() {
		return this.system;
	}

	public final void  setSystem(String system) {
		if(SYSTEM_CP.equals(system))
			this.system = SYSTEM_CP;
		else if (SYSTEM_CP2000.equals(system))
			this.system = SYSTEM_CP2000;
		else
			throw new IllegalArgumentException("The value given for system = " + system + " is not valid");
	}

	public final boolean isCP() {
		if (SYSTEM_CP.equals(this.system))
			return true;
		else
			return false;
	}

	public final boolean isCP2000() {
		if(SYSTEM_CP2000.equals(this.system))
			return true;
		else
			return false;
	}

	public final void setSystemCP() {
		this.system = SYSTEM_CP;
	}

	public final void setSystemCP2000() {
		this.system = SYSTEM_CP2000;
	}
}
