/*
 * Created on Aug 18, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierNotesAttachmentOverrideBO extends NotesAttachmentOverrideBO {
	
	private int tierSysId;
	
	private int requestType;

	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	
	/**
	 * @return Returns the requestType.
	 */
	public int getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType The requestType to set.
	 */
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
}
