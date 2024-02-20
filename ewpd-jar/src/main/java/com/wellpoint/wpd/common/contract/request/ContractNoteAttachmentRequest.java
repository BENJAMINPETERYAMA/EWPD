package com.wellpoint.wpd.common.contract.request;


import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractNoteAttachmentRequest extends ContractRequest {
    
	private List notesIdList;
	private int action;
	private int productId;
	private String noteName;
	private String entityType;
	private List versionList;
	private int dateSegmentId;

	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }	
    
	/**
	 * Returns the notesIdList
	 * @return ArrayList notesIdList.
	 */
	public List getNotesIdList() {
		return notesIdList;
	}
	/**
	 * Sets the notesIdList
	 * @param notesIdList.
	 */
	public void setNotesIdList(List notesIdList) {
		this.notesIdList = notesIdList;
	}
	
	/**
	 * Returns the productId
	 * @return int productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * Sets the productId
	 * @param productId.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * Returns the versionList
	 * @return List versionList.
	 */
	public List getVersionList() {
		return versionList;
	}
	/**
	 * Sets the versionList
	 * @param versionList.
	 */
	public void setVersionList(List versionList) {
		this.versionList = versionList;
	}
	/**
	 * Returns the dateSegmentId
	 * @return int dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * Sets the dateSegmentId
	 * @param dateSegmentId.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
}

