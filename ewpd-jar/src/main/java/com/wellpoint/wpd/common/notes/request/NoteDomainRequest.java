/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import java.util.List;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomainRequest extends WPDRequest {
	
	private String action;
	
	private String noteId;
	
	private String noteName;
	
	private List lobList;
	
	private List businessEntityList;
	
	private List businessGroupList;
	
	private List marketBusinessUnitList;
	
	private int maximumSize;
	
	private int version;
	
	private String check;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

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
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
    /**
     * Returns the noteName
     * @return String noteName.
     */
    public String getNoteName() {
        return noteName;
    }
    /**
     * Sets the noteName
     * @param noteName.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
	/**
	 * Returns the businessEntityList
	 * @return List businessEntityList.
	 */
	public List getBusinessEntityList() {
		return businessEntityList;
	}
	/**
	 * Sets the businessEntityList
	 * @param businessEntityList.
	 */
	public void setBusinessEntityList(List businessEntityList) {
		this.businessEntityList = businessEntityList;
	}
	/**
	 * Returns the businessGroupList
	 * @return List businessGroupList.
	 */
	public List getBusinessGroupList() {
		return businessGroupList;
	}
	/**
	 * Sets the businessGroupList
	 * @param businessGroupList.
	 */
	public void setBusinessGroupList(List businessGroupList) {
		this.businessGroupList = businessGroupList;
	}
	/**
	 * Returns the lobList
	 * @return List lobList.
	 */
	public List getLobList() {
		return lobList;
	}
	/**
	 * Sets the lobList
	 * @param lobList.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}
	
	/**
	 * Returns the maximumSize
	 * @return int maximumSize.
	 */
	public int getMaximumSize() {
		return maximumSize;
	}
	/**
	 * Sets the maximumSize
	 * @param maximumSize.
	 */
	public void setMaximumSize(int maximumSize) {
		this.maximumSize = maximumSize;
	}
	
	/**
	 * Returns the version
	 * @return int version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	/**
	 * Returns the check
	 * @return String check.
	 */
	public String getCheck() {
		return check;
	}
	/**
	 * Sets the check
	 * @param check.
	 */
	public void setCheck(String check) {
		this.check = check;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}
