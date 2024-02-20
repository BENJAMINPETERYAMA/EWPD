/*
 * Transition.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Transition.java 49209 2008-10-06 11:18:14Z U14659 $
 */
public class Transition {

    public static final String TRANSITION_BUILDING_TO_CHECKED_IN = "TRANSITION_BUILDING_TO_CHECKED_IN";

    public static final String TRANSITION_CHECKED_IN_TO_SCHEDULED_FOR_TEST = "TRANSITION_CHECKED_IN_TO_SCHEDULED_FOR_TEST";

    public static final String TRANSITION_SCHEDULED_FOR_TEST_TO_OBJECT_TRANSFERRED = "TRANSITION_SCHEDULED_FOR_TEST_TO_OBJECT_TRANSFERRED";

    public static final String TRANSITION_SCHEDULED_FOR_TEST_TO_TEST_FAILED = "TRANSITION_SCHEDULED_FOR_TEST_TO_TEST_FAILED";
    
    public static final String TRANSITION_SCHEDULED_FOR_TEST_TO_TEST_SUCCESSFUL = "TRANSITION_SCHEDULED_FOR_TEST_TO_TEST_SUCCESSFUL";

    public static final String TRANSITION_OBJECT_TRANSFERRED_TO_TEST_FAILED = "TRANSITION_OBJECT_TRANSFERRED_TO_TEST_FAILED";

    public static final String TRANSITION_OBJECT_TRANSFERRED_TO_TEST_SUCCESSFUL = "TRANSITION_OBJECT_TRANSFERRED_TO_TEST_SUCCESSFUL";
    
	public static final String TRANSITION_TEST_SUCCESSFUL_TO_CHECKED_OUT = "TRANSITION_TEST_SUCCESSFUL_TO_CHECKED_OUT";

	public static final String TRANSITION_TEST_FAILED_TO_CHECKED_OUT = "TRANSITION_TEST_FAILED_TO_CHECKED_OUT";
	
	public static final String TRANSITION_TEST_SUCCESSFUL_TO_MARKED_FOR_DELETION = "TRANSITION_TEST_SUCCESSFUL_TO_MARKED_FOR_DELETION";
	
	public static final String TRANSITION_TEST_FAILED_TO_MARKED_FOR_DELETION = "TRANSITION_TEST_FAILED_TO_MARKED_FOR_DELETION";

    public static final String TRANSITION_TEST_SUCCESSFUL_TO_SCHEDULED_FOR_APPROVAL = "TRANSITION_TEST_SUCCESSFUL_TO_SCHEDULED_FOR_APPROVAL";
    
    public static final String TRANSITION_TEST_SUCCESSFUL_TO_PUBLISHED	=	"TRANSITION_TEST_SUCCESSFUL_TO_PUBLISHED";
    
    public static final String TRANSITION_TEST_SUCCESSFUL_TO_SCHEDULED_FOR_PRODUCTION = "TEST_SUCCESSFUL_TO_SCHEDULED_FOR_PRODUCTION";

    public static final String TRANSITION_SCHEDULED_FOR_APPROVAL_TO_APPROVED = "TRANSITION_SCHEDULED_FOR_APPROVAL_TO_APPROVED";

    public static final String TRANSITION_SCHEDULED_FOR_APPROVAL_TO_REJECTED = "TRANSITION_SCHEDULED_FOR_APPROVAL_TO_REJECTED";

    public static final String TRANSITION_APPROVED_TO_CHECKED_OUT = "TRANSITION_APPROVED_TO_CHECKED_OUT";

    public static final String TRANSITION_REJECTED_TO_CHECKED_OUT = "TRANSITION_REJECTED_TO_CHECKED_OUT";
    
	public static final String TRANSITION_APPROVED_TO_MARKED_FOR_DELETION = "TRANSITION_APPROVED_TO_MARKED_FOR_DELETION";
	
	public static final String TRANSITION_REJECTED_TO_MARKED_FOR_DELETION = "TRANSITION_REJECTED_TO_MARKED_FOR_DELETION";

    public static final String TRANSITION_CHECKED_OUT_TO_SCHEDULED_FOR_TEST = "TRANSITION_CHECKED_OUT_TO_SCHEDULED_FOR_TEST";

    public static final String TRANSITION_APPROVED_TO_PUBLISHED = "TRANSITION_APPROVED_TO_PUBLISHED";

    public static final String TRANSITION_APPROVED_TO_SCHEDULED_FOR_PRODUCTION = "TRANSITION_APPROVED_TO_SCHEDULED_FOR_PRODUCTION";

    public static final String TRANSITION_SCHEDULED_FOR_PRODUCTION_TO_TRANSFERRED_TO_PRODUCTION = "TRANSITION_SCHEDULED_FOR_PRODUCTION_TO_TRANSFERRED_TO_PRODUCTION";

    public static final String TRANSITION_PUBLISHED_TO_CHECKED_OUT = "TRANSITION_PUBLISHED_TO_CHECKED_OUT";

    public static final String TRANSITION_PUBLISHED_TO_MARKED_FOR_DELETION = "TRANSITION_PUBLISHED_TO_MARKED_FOR_DELETION";

    public static final String TRANSITION_TRANSFERRED_TO_PRODUCTION_TO_CHECKED_OUT = "TRANSITION_TRANSFERRED_TO_PRODUCTION_TO_CHECKED_OUT";

    public static final String TRANSITION_TRANSFERRED_TO_PRODUCTION_TO_MARKED_FOR_DELETION = "TRANSITION_TRANSFERRED_TO_PRODUCTION_TO_MARKED_FOR_DELETION";
    
    private String fromStatus;

    private String toStatus;
    
    private String taskName;
    
    private List override;
    private boolean isLog;
    
    public Transition(String fromStatus, String toStatus,String taskName,boolean log){
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.taskName=taskName;
        this.isLog=log;
    }
    
    public Transition(String fromStatus, String toStatus,String taskName){
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.taskName=taskName;
       
    }
    
    public Transition(String fromStatus, String toStatus,String taskName,List override,boolean log){
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.taskName=taskName;
        this.override=override;
        this.isLog=log;
    }
    

    public Transition(String fromStatus, String toStatus){
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

    /**
     * @return Returns the fromStatus.
     */
    public String getFromStatus() {
        return fromStatus;
    }

    /**
     * @return Returns the toStatus.
     */
    public String getToStatus() {
        return toStatus;
    }

    /**
     * @param fromStatus
     *            The fromStatus to set.
     */
    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    /**
     * @param toStatus
     *            The toStatus to set.
     */
    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
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
     * Returns the override
     * @return List override.
     */
    public List getOverride() {
        return override;
    }
    /**
     * Sets the override
     * @param override.
     */
    public void setOverride(List override) {
        this.override = override;
    }
	/**
	 * @return Returns the isLog.
	 */
	public boolean isLog() {
		return isLog;
	}
	/**
	 * @param isLog The isLog to set.
	 */
	public void setLog(boolean isLog) {
		this.isLog = isLog;
	}
}