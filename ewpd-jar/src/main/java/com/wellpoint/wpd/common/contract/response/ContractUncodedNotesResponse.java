/*
 * Created on Jul 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;


/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractUncodedNotesResponse extends ContractResponse{
	
	private List uncodedAllNotesList;
	
	private List uncodedTierNotesList;
	
	private String productName;
	
	private boolean success;
	
	

	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the uncodedAllNotesList.
	 */
	public List getUncodedAllNotesList() {
		return uncodedAllNotesList;
	}
	/**
	 * @param uncodedAllNotesList The uncodedAllNotesList to set.
	 */
	public void setUncodedAllNotesList(List uncodedAllNotesList) {
		this.uncodedAllNotesList = uncodedAllNotesList;
	}
	/**
	 * @return Returns the uncodedTierNotesList.
	 */
	public List getUncodedTierNotesList() {
		return uncodedTierNotesList;
	}
	/**
	 * @param uncodedTierNotesList The uncodedTierNotesList to set.
	 */
	public void setUncodedTierNotesList(List uncodedTierNotesList) {
		this.uncodedTierNotesList = uncodedTierNotesList;
	}
}
