/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.notes.vo.AdminAttachmentVO;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminAttachmentRequest extends WPDRequest {

	private int action;
	
	private AdminAttachmentVO attachmentVO;
	
	
	private int adminId;
	
	private int entityId;
	
	private int parentId;
	
	private String entityType;
	
	private String parentType;
	
	public static final int VIEW_ADMIN_DESCRIPTION = 1;
	
	
	
   
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the vIEW_ADMIN_DESCRIPTION.
	 */
	public static int getVIEW_ADMIN_DESCRIPTION() {
		return VIEW_ADMIN_DESCRIPTION;
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
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
	/**
	 * @return Returns the attachmentVO.
	 */
	public AdminAttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	/**
	 * @param attachmentVO The attachmentVO to set.
	 */
	public void setAttachmentVO(AdminAttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
}
