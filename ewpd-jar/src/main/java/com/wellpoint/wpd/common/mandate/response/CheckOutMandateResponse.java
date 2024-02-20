/*
 * Created on Mar 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;


/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CheckOutMandateResponse extends WPDResponse{
	
	private MandateBO mandateBO;
	private boolean action;

	/**
	 * @return Returns the mandateBO.
	 */
	public MandateBO getMandateBO() {
		return mandateBO;
	}
	/**
	 * @param mandateBO The mandateBO to set.
	 */
	public void setMandateBO(MandateBO mandateBO) {
		this.mandateBO = mandateBO;
	}
	/**
	 * @return Returns the action.
	 */
	public boolean isAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(boolean action) {
		this.action = action;
	}
}
