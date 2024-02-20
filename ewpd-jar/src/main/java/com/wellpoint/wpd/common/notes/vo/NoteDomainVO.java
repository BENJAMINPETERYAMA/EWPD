/*
 * Created on Jul 2, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.vo;

/**
 * @author U13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomainVO {
    private String notesId;
    private String notesName;
    private int versionNumber;
    private String domainId;
    private String domainType;
    private boolean dataDomainDelete;
    private String state;

	/**
	 * @return Returns the dataDomainDelete.
	 */
	public boolean isDataDomainDelete() {
		return dataDomainDelete;
	}
	/**
	 * @param dataDomainDelete The dataDomainDelete to set.
	 */
	public void setDataDomainDelete(boolean dataDomainDelete) {
		this.dataDomainDelete = dataDomainDelete;
	}
	/**
	 * @return Returns the domainId.
	 */
	public String getDomainId() {
		return domainId;
	}
	/**
	 * @param domainId The domainId to set.
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	/**
	 * @return Returns the domainType.
	 */
	public String getDomainType() {
		return domainType;
	}
	/**
	 * @param domainType The domainType to set.
	 */
	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
	/**
	 * @return Returns the notesId.
	 */
	public String getNotesId() {
		return notesId;
	}
	/**
	 * @param notesId The notesId to set.
	 */
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
	/**
	 * @return Returns the notesName.
	 */
	public String getNotesName() {
		return notesName;
	}
	/**
	 * @param notesName The notesName to set.
	 */
	public void setNotesName(String notesName) {
		this.notesName = notesName;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

}
