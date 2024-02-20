/*
 * Created on Feb 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BusinessDomainBOImpl implements BusinessDomainBO{
	private String lobDescription = null;
	private int lobCode;
	
	private String busEntityDescription = null;
	private int busEntityCode;
	
	private String busGroupDescription = null;
	private int busGroupCode;

	/**
	 * @return Returns the busEntityCode.
	 */
	public int getBusEntityCode() {
		return busEntityCode;
	}
	/**
	 * @param busEntityCode The busEntityCode to set.
	 */
	public void setBusEntityCode(int busEntityCode) {
		this.busEntityCode = busEntityCode;
	}
	/**
	 * @return Returns the busEntityDescription.
	 */
	public String getBusEntityDescription() {
		return busEntityDescription;
	}
	/**
	 * @param busEntityDescription The busEntityDescription to set.
	 */
	public void setBusEntityDescription(String busEntityDescription) {
		this.busEntityDescription = busEntityDescription;
	}
	/**
	 * @return Returns the busGroupCode.
	 */
	public int getBusGroupCode() {
		return busGroupCode;
	}
	/**
	 * @param busGroupCode The busGroupCode to set.
	 */
	public void setBusGroupCode(int busGroupCode) {
		this.busGroupCode = busGroupCode;
	}
	/**
	 * @return Returns the busGroupDescription.
	 */
	public String getBusGroupDescription() {
		return busGroupDescription;
	}
	/**
	 * @param busGroupDescription The busGroupDescription to set.
	 */
	public void setBusGroupDescription(String busGroupDescription) {
		this.busGroupDescription = busGroupDescription;
	}
	/**
	 * @return Returns the lobCode.
	 */
	public int getLobCode() {
		return lobCode;
	}
	/**
	 * @param lobCode The lobCode to set.
	 */
	public void setLobCode(int lobCode) {
		this.lobCode = lobCode;
	}
	/**
	 * @return Returns the lobDescription.
	 */
	public String getLobDescription() {
		return lobDescription;
	}
	/**
	 * @param lobDescription The lobDescription to set.
	 */
	public void setLobDescription(String lobDescription) {
		this.lobDescription = lobDescription;
	}
}
