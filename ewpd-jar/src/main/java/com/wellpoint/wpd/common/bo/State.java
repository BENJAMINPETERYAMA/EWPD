/*
 * State.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: State.java 44272 2008-04-03 09:53:04Z U14659 $
 */
public interface State {
    
    public static final String BUSINESS_OBJECT_STATE_ATTRIBUTE = "state";
    
    /**
     * Checks if the business object can be checked in
     * @return
     */
    public boolean isValidForCheckIn();

    /**
     * Checks if the business object can be transferred for testing(remote or local)
     * @return
     */
    public boolean isValidForTest();
    /**
     * Checks if the business object can be scheduled for production
     * @return
     */
    public boolean isValidForProduction();
    /**
     * Checks if the business object is ready to complete the testing
     *     The object can either pass testing or fail testing
     * @return
     */
    public boolean isValidForTestCompletion();

    /**
     * Checks if the business object can be transferred for approving
     * @return
     */
    public boolean isValidForApproval();
    
    /**
     * Checks if the business object is ready to complete approval
     *		The object can either get approved or either get rejected
     * @return
     */
    public boolean isValidForApprovalCompletion();

    /**
     * Checks if the business object can be checked out
     * @return
     */
    public boolean isValidForCheckOut();

    /**
     * Checks if the business object can be published
     * @return
     */
    public boolean isValidForPublish();

    /**
     * Checks if the business object can be deleted
     * @return
     */
    public boolean isValidForDelete();

    /**
     * Checks if the business object can be transferred
     * @return
     */
    public boolean isValidForTransfer();
    
    /**
     * Checks if the business object can be copied
     * @return
     */
    public boolean isValidForCopy(); 
    
    /**
     * Checks if valid for viewing the business object.
     * @return
     */
    public boolean isValidForView();
    
    /**
     * Checks if valid for creating the business object.
     * @return
     */
    public boolean isValidForCreate();

    /**
     * Gets the value of the state attribute
     * (There is one to one mapping from status to state)
     * @return
     */
    public String getState();

    /**
     * Checks if the business object is editable by user
     * @return
     */
    public boolean isEditableByUser();

    /**
     * Checks if the business object is locked by any user
     * @return
     */
    public boolean isLocked();
    
    /**
     * Checks if the business object is locked by any user
     * @return
     */
    public boolean isLockedByUser();
    
    /**
     * Checks if valid for unlocking the business object.
     * @return
     */
    public boolean isValidForUnlock();
    
    /**
     * Checks if valid for deleting all the versions of business object.
     * @return
     */
    public boolean isValidForDeleteAll();
}