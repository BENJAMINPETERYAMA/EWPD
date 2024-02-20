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
public interface BusinessDomainBO {
	/**
	 * @return Returns the lobCode.
	 */
	public int getLobCode();
	/**
	 * @param lobCode The lobCode to set.
	 */
	public void setLobCode(int lobCode);
	/**
	 * @return Returns the lobDescription.
	 */
	public String getLobDescription();
	/**
	 * @param lobDescription The lobDescription to set.
	 */
	public void setLobDescription(String lobDescription);
	
	/**
	 * @return Returns the busEntityCode.
	 */
	public int getBusEntityCode();
	/**
	 * @param busEntityCode The busEntityCode to set.
	 */
	public void setBusEntityCode(int busEntityCode);
	/**
	 * @return Returns the busEntityDescription.
	 */
	public String getBusEntityDescription();
	/**
	 * @param busEntityDescription The busEntityDescription to set.
	 */
	public void setBusEntityDescription(String busEntityDescription);
	/**
	 * @return Returns the busGroupCode.
	 */
	public int getBusGroupCode();
	/**
	 * @param busGroupCode The busGroupCode to set.
	 */
	public void setBusGroupCode(int busGroupCode);
	/**
	 * @return Returns the busGroupDescription.
	 */
	public String getBusGroupDescription();
	/**
	 * @param busGroupDescription The busGroupDescription to set.
	 */
	public void setBusGroupDescription(String busGroupDescription);
}
