/*
 * Created on May 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomainDeleteBO extends BusinessObject {
	
	private String noteId;
	
	private String entityId;
	
	private String entityType;
	
	private int version;
	
	private List noteDomains;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return Returns the entityId.
	 */
	public String getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the noteDomains.
	 */
	public List getNoteDomains() {
		return noteDomains;
	}
	/**
	 * @param noteDomains The noteDomains to set.
	 */
	public void setNoteDomains(List noteDomains) {
		this.noteDomains = noteDomains;
	}
}
