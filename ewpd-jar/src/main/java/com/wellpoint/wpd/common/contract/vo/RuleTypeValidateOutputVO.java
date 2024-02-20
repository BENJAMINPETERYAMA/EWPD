
package com.wellpoint.wpd.common.contract.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleTypeValidateOutputVO {

	private List dateSegmentList;
	
	private List errorMessagesList;

	/**
	 * Sets the dateSegmentList
	 * @param dateSegmentList
	 */
	public void setDateSegment(List dateSegment) {
		this.dateSegmentList = dateSegment;
	}

	/**
	 * Returns the dateSegmentList
	 * @return List dateSegmentList
	 */
	public List getDateSegment() {
		return dateSegmentList;
	}

	/**
	 * Sets the errorMessagesList
	 * @param errorMessagesList
	 */
	public void setErrorMessages(List errorMessages) {
		this.errorMessagesList = errorMessages;
	}

	/**
	 * Returns the errorMessagesList
	 * @return List errorMessagesList
	 */
	public List getErrorMessages() {
		return errorMessagesList;
	}
	
}
