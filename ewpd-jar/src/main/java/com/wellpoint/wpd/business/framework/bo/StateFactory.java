/*
 * StateFactory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.exception.bo.StateObjectCreationException;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * The StateFactory is a interface for getting the state of the BusinessObject.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: StateFactory.java 40282 2007-12-18 11:34:59Z U14659 $
 */
public interface StateFactory {

    public static final String STATE_NEW = "NEW";

    public static final String STATE_UNDER_TEST = "UNDER_TEST";

    public static final String STATE_AWAITING_APPROVAL = "AWAITING_APPROVAL";

    public static final String STATE_OPENED_FOR_UPDATES = "OPENED_FOR_UPDATES";

    public static final String STATE_PUBLISHED = "PUBLISHED";

    public static final String STATE_MARKED_FOR_DELETION = "MARKED_FOR_DELETION";

    public static final String STATUS_BUILDING = "BUILDING";

    public static final String STATUS_CHECKED_IN = "CHECKED_IN";

    public static final String STATUS_SCHEDULED_FOR_TEST = "SCHEDULED_FOR_TEST";

    public static final String STATUS_TEST_FAILED = "TEST_FAILED";

    public static final String STATUS_TEST_SUCCESSFUL = "TEST_SUCCESSFUL";

    public static final String STATUS_OBJECT_TRANSFERRED = "OBJECT_TRANSFERRED";

    public static final String STATUS_SCHEDULED_FOR_APPROVAL = "SCHEDULED_FOR_APPROVAL";

    public static final String STATUS_APPROVED = "APPROVED";

    public static final String STATUS_REJECTED = "REJECTED";

    public static final String STATUS_CHECKED_OUT = "CHECKED_OUT";

    public static final String STATUS_PUBLISHED = "PUBLISHED";

    public static final String STATUS_SCHEDULED_FOR_PRODUCTION = "SCHEDULED_FOR_PRODUCTION";

    public static final String STATUS_MARKED_FOR_DELETION = "MARKED_FOR_DELETION";

    public static final String CREATE_TASK = "create";

    public static final String EDIT_TASK = "edit";
    
    public static final String UNLOCK_TASK = "unlock";

    public static final String DELETE_TASK = "delete";

    public static final String RETRIEVE_TASK = "retrieve";

    public static final String VIEW_TASK = "VIEW";
    
    public static final String COPY_TASK = "copy";
    
    public static final String CHECKIN_TASK = "CHECKIN";
    
    public static final String CHECKOUT_TASK = "CHECKOUT";
    
    public static final String PUBLISH_TASK = "PUBLISH";
    
    public static final String APPROVE_TASK = "APPROVE";
    
    public static final String REJECT_TASK = "REJECT";
    
    public static final String SCHEDULE_TO_TEST_TASK = "SCHEDULETOTEST";
    
    public static final String SCHEDULE_TO_PRODUCTION_TASK = "SCHEDULETOPRODUCTION";
    
    public static final String OBJECT_TRANSFERRED_TASK = "OBJECTTRANSFERRED";
    
    public static final String TEST_PASS_TASK = "TESTPASS";
    
    public static final String TEST_FAIL_TASK = "TESTFAIL";
    
    public static final String SCHEDULE_TO_APPROVAL_TASK = "SCHEDULETOAPPROVAL";
    
    


    State getState(Object object, User user)
            throws StateObjectCreationException;

    State getState();
}