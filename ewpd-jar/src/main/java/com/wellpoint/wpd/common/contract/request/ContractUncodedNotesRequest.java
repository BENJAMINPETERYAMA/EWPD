/*
 * Created on Jul 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractUncodedNotesRequest extends ContractRequest{
	
	public static final int DELETE_UNCODED_NOTES_CHECKIN = 1;
	
	public static final  int RETREIVE_UNCODED_NOTES = 2;
	
	private int action;
	
	private String contractId;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
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
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
}
