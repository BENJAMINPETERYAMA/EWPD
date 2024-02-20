/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractHistoryLogRecord extends HistoryLogRecord {

	private int noOfDateSegments;

	/**
	 * @return Returns the noOfDateSegments.
	 */
	public int getNoOfDateSegments() {
		return noOfDateSegments;
	}

	/**
	 * @param noOfDateSegments
	 *            The noOfDateSegments to set.
	 */
	public void setNoOfDateSegments(int noOfDateSegments) {
		this.noOfDateSegments = noOfDateSegments;
	}
}