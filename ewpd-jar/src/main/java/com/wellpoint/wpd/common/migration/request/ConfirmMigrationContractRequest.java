/*
 * Created on Aug 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.request;



/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ConfirmMigrationContractRequest extends MigrationContractRequest{
	
	private int action ;
	private String referenceID;
	public static final int CONFIRM_MIG_CONTRACT_REQUEST = 1;
	public static final int RETRIEVE_MIG_CONTRACT_REQUEST = 2;
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
	 * @return Returns the referenceID.
	 */
	public String getReferenceID() {
		return referenceID;
	}
	/**
	 * @param referenceID The referenceID to set.
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
}
