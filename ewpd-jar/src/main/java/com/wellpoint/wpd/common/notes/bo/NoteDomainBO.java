/*
 * Created on May 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomainBO extends BusinessObject implements Comparable{
	
	private String noteId;
	
	private String noteName;
	
	private String noteType;
	
	private String noteText;
	
	private String systemDomainId;
	
	private String systemDomainDescription;
	
	private String entityId;
	
	private String entityType;
	
	private List systemDomainIds;
	
	private Map notesDataDomainLists;
	
	private int benefitDefinitionId;
	
	private boolean checkBoxVal;

	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
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
	 * @return Returns the systemDomainDescription.
	 */
	public String getSystemDomainDescription() {
		return systemDomainDescription;
	}
	/**
	 * @param systenDomainDescription The systemDomainDescription to set.
	 */
	public void setSystemDomainDescription(String systenDomainDescription) {
		this.systemDomainDescription = systenDomainDescription;
	}
	/**
	 * @return Returns the systemDomainIds.
	 */
	public List getSystemDomainIds() {
		return systemDomainIds;
	}
	/**
	 * @param systemDomainIds The systemDomainIds to set.
	 */
	public void setSystemDomainIds(List systemDomainIds) {
		this.systemDomainIds = systemDomainIds;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(Object object) {
        if (object instanceof NoteDomainBO) {
        	NoteDomainBO noteDomainBO = (NoteDomainBO) object;
            if (this.systemDomainId.equalsIgnoreCase(noteDomainBO
                    .getSystemDomainId()))
                return true;
        }
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @param obj
     * @return
     */
    public int compareTo(Object obj) {
        if (!(obj instanceof NoteDomainBO))
            throw new ClassCastException("Notedomain object Expected.");
        
        NoteDomainBO noteDomainBO = (NoteDomainBO) obj;
        if(this.systemDomainId.compareToIgnoreCase(noteDomainBO.getSystemDomainId()) > 0){
        	return 1;
        }
        else if(this.systemDomainId.compareToIgnoreCase(noteDomainBO.getSystemDomainId()) < 0){
        	return -1;
        }
        else if(this.systemDomainId.equalsIgnoreCase(noteDomainBO.getSystemDomainId())){
        	return 0;
        }
        return 1;
        
    }
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	
	/**
	 * @return Returns the noteText.
	 */
	public String getNoteText() {
		return noteText;
	}
	/**
	 * @param noteText The noteText to set.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	/**
	 * @return Returns the noteType.
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType The noteType to set.
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return Returns the notesDataDomainLists.
	 */
	public Map getNotesDataDomainLists() {
		return notesDataDomainLists;
	}
	/**
	 * @param notesDataDomainLists The notesDataDomainLists to set.
	 */
	public void setNotesDataDomainLists(Map notesDataDomainLists) {
		this.notesDataDomainLists = notesDataDomainLists;
	}
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
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
	 * @return Returns the systemDomainId.
	 */
	public String getSystemDomainId() {
		return systemDomainId;
	}
	/**
	 * @param systemDomainId The systemDomainId to set.
	 */
	public void setSystemDomainId(String systemDomainId) {
		this.systemDomainId = systemDomainId;
	}
    /**
     * @return Returns the checkBoxVal.
     */
    public boolean isCheckBoxVal() {
        return checkBoxVal;
    }
    /**
     * @param checkBoxVal The checkBoxVal to set.
     */
    public void setCheckBoxVal(boolean checkBoxVal) {
        this.checkBoxVal = checkBoxVal;
    }
}
