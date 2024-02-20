/*
 * TaskLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.task.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskLocateCriteria extends LocateCriteria {

    private String taskName;
    
    private String srdaFlag;
    
   /* private String Type;
    
    private String entityType;
    */
    private int taskId;
    
	/**
	 * @return Returns the securityType.
	 */
	/*public String getSecurityType() {
		return securityType;
	}
	*//**
	 * @param securityType The securityType to set.
	 *//*
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	
	
	
	*//**
	 * @return Returns the entityType.
	 *//*
	public String getEntityType() {
		return entityType;
	}
	*//**
	 * @param entityType The entityType to set.
	 *//*
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}*/
    
	/**
	 * @return Returns the taskId.
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the taskName.
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName The taskName to set.
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}
	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}