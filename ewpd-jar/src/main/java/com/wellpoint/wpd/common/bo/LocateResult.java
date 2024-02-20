/*
 * LocateResult.java
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
 * @version $Id: LocateResult.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateResult {
	private State state;
	private String status;
	private int version;
	private String systemDomain;
	/**
	 * @return
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}
    /**
     * @return
     */
    public String getSystemDomain() {
        return systemDomain;
    }
    /**
     * @param systemDomain
     */
    public void setSystemDomain(String systemDomain) {
        this.systemDomain = systemDomain;
    }
}
