/*
 * Created on Jul 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.refdata.bo;

import java.util.Date;
import java.util.List;

/**
 * @author u14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssignedRuleIdBO {
	
	private int entitySystemId ;   
	private String entityType ; 
	private int catalogId; 
	private String primaryCode ; 
	private String codeDescText;
	private String createdUserId ; 
	private Date createdTimeStamp;
	private String lastchangesUser ;
	private Date lastChangesTimeStamp;
	private List enityIdList;
	private String action;
	private int productId;
	private int dateSegmentId;


	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * @return Returns the createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * @param createdTimeStamp The createdTimeStamp to set.
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	/**
	 * @return Returns the createdUserId.
	 */
	public String getCreatedUserId() {
		return createdUserId;
	}
	/**
	 * @param createdUserId The createdUserId to set.
	 */
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}
	/**
	 * @return Returns the entitySystemId.
	 */
	public int getEntitySystemId() {
		return entitySystemId;
	}
	/**
	 * @param entitySystemId The entitySystemId to set.
	 */
	public void setEntitySystemId(int entitySystemId) {
		this.entitySystemId = entitySystemId;
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
	 * @return Returns the lastChangesTimeStamp.
	 */
	public Date getLastChangesTimeStamp() {
		return lastChangesTimeStamp;
	}
	/**
	 * @param lastChangesTimeStamp The lastChangesTimeStamp to set.
	 */
	public void setLastChangesTimeStamp(Date lastChangesTimeStamp) {
		this.lastChangesTimeStamp = lastChangesTimeStamp;
	}
	/**
	 * @return Returns the lastchangesUser.
	 */
	public String getLastchangesUser() {
		return lastchangesUser;
	}
	/**
	 * @param lastchangesUser The lastchangesUser to set.
	 */
	public void setLastchangesUser(String lastchangesUser) {
		this.lastchangesUser = lastchangesUser;
	}
	/**
	 * @return Returns the primaryCode.
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}
	/**
	 * @param primaryCode The primaryCode to set.
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
	/**
	 * @return Returns the codeDescText.
	 */
	public String getCodeDescText() {
		return codeDescText;
	}
	/**
	 * @param codeDescText The codeDescText to set.
	 */
	public void setCodeDescText(String codeDescText) {
		this.codeDescText = codeDescText;
	}
	/**
	 * @return Returns the enityIdList.
	 */
	public List getEnityIdList() {
		return enityIdList;
	}
	/**
	 * @param enityIdList The enityIdList to set.
	 */
	public void setEnityIdList(List enityIdList) {
		this.enityIdList = enityIdList;
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
