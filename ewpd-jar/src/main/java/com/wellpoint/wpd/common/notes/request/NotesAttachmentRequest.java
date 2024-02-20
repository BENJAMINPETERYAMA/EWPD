/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentOverrideVO;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentVO;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentRequest extends WPDRequest {

	private int action;
	
	private NotesAttachmentVO attachmentVO;
	
	private NotesAttachmentOverrideVO attachmentOverrideVO;
	
	private int noteId;
	
	private int entityId;
	
	private int parentId;
	
	private String entityType;
	
	private String parentType;
	
	private String noteName;
	
	public static final int VIEW_NOTE_DESCRIPTION = 1;
	
	public static final int NOTES_ATTACH_LOOK_UP = 2;
	
	public static final int NOTES_OVERRIDE_LOOK_UP = 3;
	
    /**
     * @return attachmentOverrideVO
     * 
     * Returns the attachmentOverrideVO.
     */
    public NotesAttachmentOverrideVO getAttachmentOverrideVO() {
        return attachmentOverrideVO;
    }
    /**
     * @param attachmentOverrideVO
     * 
     * Sets the attachmentOverrideVO.
     */
    public void setAttachmentOverrideVO(
            NotesAttachmentOverrideVO attachmentOverrideVO) {
        this.attachmentOverrideVO = attachmentOverrideVO;
    }
	/**
	 * @return Returns the attachmentVO.
	 */
	public NotesAttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	/**
	 * @param attachmentVO The attachmentVO to set.
	 */
	public void setAttachmentVO(NotesAttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
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
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
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
	public int getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the parentId.
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return Returns the parentType.
	 */
	public String getParentType() {
		return parentType;
	}
	/**
	 * @param parentType The parentType to set.
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

    /**
     * @return noteName
     * 
     * Returns the noteName.
     */
    public String getNoteName() {
        return noteName;
    }
    /**
     * @param noteName
     * 
     * Sets the noteName.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
}
