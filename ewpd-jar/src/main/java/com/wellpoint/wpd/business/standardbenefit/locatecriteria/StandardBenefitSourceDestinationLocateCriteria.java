/*
 * Created on Mar 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitSourceDestinationLocateCriteria extends LocateCriteria{
	private String createdUser;
	private int sourceKey;
	private int destinationKey;

	/**
	 * @return Returns the destinationKey.
	 */
	public int getDestinationKey() {
		return destinationKey;
	}
	/**
	 * @param destinationKey The destinationKey to set.
	 */
	public void setDestinationKey(int destinationKey) {
		this.destinationKey = destinationKey;
	}
	
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the sourceKey.
	 */
	public int getSourceKey() {
		return sourceKey;
	}
	/**
	 * @param sourceKey The sourceKey to set.
	 */
	public void setSourceKey(int sourceKey) {
		this.sourceKey = sourceKey;
	}
}
