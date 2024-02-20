/*
 * Created on Apr 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;


/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MandateTestFailRequest extends WPDRequest {

	private MandateVO mandateVO;

	/**
	 * @return Returns the mandateVO.
	 */
	public MandateVO getMandateVO() {
		return mandateVO;
	}
	/**
	 * @param mandateVO The mandateVO to set.
	 */
	public void setMandateVO(MandateVO mandateVO) {
		this.mandateVO = mandateVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}


}
