/*
 * Created on Mar 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;


/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewAllMandateRequest extends WPDRequest {
	
	private MandateVO mandateVO;
	
	private String action;


	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
    /**
     * Returns the mandateVO
     * 
     * @return MandateVO mandateVO.
     */
    public MandateVO getMandateVO() {
        return mandateVO;
    }


    /**
     * Sets the mandateVO
     * 
     * @param mandateVO.
     */
    public void setMandateVO(MandateVO mandateVO) {
        this.mandateVO = mandateVO;
    }


    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (-1 == this.mandateVO.getMandateId())
            throw new ValidationException("Mandate Id is missing", null, null);
    }

}
