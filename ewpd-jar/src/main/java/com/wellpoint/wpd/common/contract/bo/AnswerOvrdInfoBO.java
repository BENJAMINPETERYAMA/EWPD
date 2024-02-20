/*
 * AnswerOvrdInfoBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AnswerOvrdInfoBO {

	private int entityId;
	
	private String entityType;
	
//	--------------------------------- getters/setters -----------------------		
	/**
	 * Returns the entityId
	 * @return int entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * Sets the entityId
	 * @param entityId.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * Returns the entityType
	 * @return String entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * Sets the entityType
	 * @param entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
}
