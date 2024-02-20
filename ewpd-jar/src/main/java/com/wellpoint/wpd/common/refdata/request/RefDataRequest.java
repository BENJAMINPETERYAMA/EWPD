/*
 * Created on Mar 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.refdata.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefDataRequest extends WPDRequest{
	
	private int popupId;
	
	private String operationMode = REFERENCE_DATA;
	
	public static final String REFERENCE_DATA = "referenceData";
	
	public RefDataRequest(){
		super();
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @return Returns the operationMode.
	 */
	public String getOperationMode() {
		return operationMode;
	}
	/**
	 * @param operationMode The operationMode to set.
	 */
	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}
	/**
	 * @return Returns the popupId.
	 */
	public int getPopupId() {
		return popupId;
	}
	/**
	 * @param popupId The popupId to set.
	 */
	public void setPopupId(int popupId) {
		this.popupId = popupId;
	}
}
