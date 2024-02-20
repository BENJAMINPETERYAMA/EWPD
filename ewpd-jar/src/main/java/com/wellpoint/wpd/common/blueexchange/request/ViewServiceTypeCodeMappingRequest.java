/*
 * Created on May 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.request;

import com.wellpoint.wpd.common.blueexchange.vo.ServiceTypeLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewServiceTypeCodeMappingRequest extends WPDRequest {
	public static final int RETRIEVE_UNMAPPED_RULES = 10;
	private ServiceTypeLocateCriteriaVO serviceTypeLocateCriteriaVO;
	private int action;
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the serviceTypeLocateCriteriaVO.
	 */
	public ServiceTypeLocateCriteriaVO getServiceTypeLocateCriteriaVO() {
		return serviceTypeLocateCriteriaVO;
	}
	/**
	 * @param serviceTypeLocateCriteriaVO The serviceTypeLocateCriteriaVO to set.
	 */
	public void setServiceTypeLocateCriteriaVO(
			ServiceTypeLocateCriteriaVO serviceTypeLocateCriteriaVO) {
		this.serviceTypeLocateCriteriaVO = serviceTypeLocateCriteriaVO;
	}
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
}
