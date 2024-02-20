/*
 * StateImpl.java
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
 * @version $Id: StateImpl.java 44272 2008-04-03 09:53:04Z U14659 $
 */
public class StateImpl implements State {

    /**
     * Field to hold the value of the state
     */
    private String state;

    /**
     * Flag to indicate if the business object can be checked in
     */
    private boolean validForCheckIn;
    
    /**
     * Flag to indicate if the business object can be copied
     */
    private boolean validForCopy;

    /**
     * Flag to indicate if the business object can be checked out
     */
    private boolean validForCheckOut;

    /**
     * Flag to indicate if the business object can be deleted
     */
    private boolean validForDelete;

    /**
     * Flag to indicate if the business object can be published
     */
    private boolean validForPublish;

    /**
     * Flag to indicate if the business object can be transferred for approval
     */
    private boolean validForApproval;
    
    /**
     * Flag to indicate if the business object can complete the approval process
     */
    private boolean validForApprovalCompletion;    

    /**
     * Flag to indicate if the business object can be transferred for testing
     */
    private boolean validForTest;
    
    /**
     * Flag to indicate if the business object can be scheduled for production
     */
    private boolean validForProduction;
    /**
     * Flag to indicate if the business object can complete testing process 
     */
    private boolean validForTestCompletion;
    
    /**
     * Flag to indicate if the business object can be transferred
     */
    private boolean validForTransfer;

    /**
     * Flag to indicate if the business object is editable by user
     */
    private boolean isEditableByUser;

    /**
     * Flag to indicate if the business object is locked
     */
  private boolean isLocked;
    
    /**
     * Flag to indicate if the business object is locked
     */
    private boolean lockedByUser;
    
    /**
     * Flag to indicate if the business object can be viewed for all versions
     */
    private boolean validForView;
    
    /**
     * Flag to indicate whether the business object can be created
     */
    private boolean validForCreate;
    
    /**
     * Flag to indicate whether the business object can be unlocked
     */
    private boolean validForUnlock;

    /**
     * Flag to indicate whether the business object can be unlocked
     */
    private boolean validForDeleteAll;
    
    
	/**
	 * @return Returns the validForDeleteAll.
	 */
	public boolean isValidForDeleteAll() {
		return validForDeleteAll;
	}
	/**
	 * @param validForDeleteAll The validForDeleteAll to set.
	 */
	public void setValidForDeleteAll(boolean validForDeleteAll) {
		this.validForDeleteAll = validForDeleteAll;
	}
	/**
	 * @return Returns the validForUnlock.
	 */
	public boolean isValidForUnlock() {
		return validForUnlock;
	}
	/**
	 * @param validForUnlock The validForUnlock to set.
	 */
	public void setValidForUnlock(boolean validForUnlock) {
		this.validForUnlock = validForUnlock;
	}
	/**
	 * @return Returns the isEditableByUser.
	 */
	public boolean isEditableByUser() {
		return isEditableByUser;
	}
	/**
	 * @param isEditableByUser The isEditableByUser to set.
	 */
	public void setEditableByUser(boolean isEditableByUser) {
		this.isEditableByUser = isEditableByUser;
	}
/**
	 * @return Returns the isLocked.
	 */
	public boolean isLocked() {
		return isLocked;
	}
	/**
	 * @param isLocked The isLocked to set.
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the validForApproval.
	 */
	public boolean isValidForApproval() {
		return validForApproval;
	}
	/**
	 * @param validForApproval The validForApproval to set.
	 */
	public void setValidForApproval(boolean validForApproval) {
		this.validForApproval = validForApproval;
	}
	/**
	 * @return Returns the validForCheckIn.
	 */
	public boolean isValidForCheckIn() {
		return validForCheckIn;
	}
	/**
	 * @param validForCheckIn The validForCheckIn to set.
	 */
	public void setValidForCheckIn(boolean validForCheckIn) {
		this.validForCheckIn = validForCheckIn;
	}
	/**
	 * @return Returns the validForCheckOut.
	 */
	public boolean isValidForCheckOut() {
		return validForCheckOut;
	}
	/**
	 * @param validForCheckOut The validForCheckOut to set.
	 */
	public void setValidForCheckOut(boolean validForCheckOut) {
		this.validForCheckOut = validForCheckOut;
	}
	/**
	 * @return Returns the validForDelete.
	 */
	public boolean isValidForDelete() {
		return validForDelete;
	}
	/**
	 * @param validForDelete The validForDelete to set.
	 */
	public void setValidForDelete(boolean validForDelete) {
		this.validForDelete = validForDelete;
	}
	/**
	 * @return Returns the validForPublish.
	 */
	public boolean isValidForPublish() {
		return validForPublish;
	}
	/**
	 * @param validForPublish The validForPublish to set.
	 */
	public void setValidForPublish(boolean validForPublish) {
		this.validForPublish = validForPublish;
	}
	/**
	 * @return Returns the validForTest.
	 */
	public boolean isValidForTest() {
		return validForTest;
	}
	/**
	 * @param validForTest The validForTest to set.
	 */
	public void setValidForTest(boolean validForTest) {
		this.validForTest = validForTest;
	}
	/**
	 * @return Returns the validForTransfer.
	 */
	public boolean isValidForTransfer() {
		return validForTransfer;
	}
	/**
	 * @param validForTransfer The validForTransfer to set.
	 */
	public void setValidForTransfer(boolean validForTransfer) {
		this.validForTransfer = validForTransfer;
	}
	
    public boolean isValidForApprovalCompletion() {
        return validForApprovalCompletion;
    }
    
    public boolean isValidForTestCompletion() {
        return validForTestCompletion;
    }
    
    public void setValidForApprovalCompletion(boolean validForApprovalCompletion) {
        this.validForApprovalCompletion = validForApprovalCompletion;
    }
    
    public void setValidForTestCompletion(boolean validForTestCompletion) {
        this.validForTestCompletion = validForTestCompletion;
    }
	/**
	 * @return Returns the validForProduction.
	 */
	public boolean isValidForProduction() {
		return validForProduction;
	}
	/**
	 * @param validForProduction The validForProduction to set.
	 */
	public void setValidForProduction(boolean validForProduction) {
		this.validForProduction = validForProduction;
	}
	/**
	 * @return Returns the validForCopy.
	 */
	public boolean isValidForCopy() {
		return validForCopy;
	}
	/**
	 * @param validForCopy The validForCopy to set.
	 */
	public void setValidForCopy(boolean validForCopy) {
		this.validForCopy = validForCopy;
	}
	
	public String toString(){
	    return "State: " + this.state + " validForApproval:" + this.validForApproval
	    		+ " validForApprovalCompletion:" + this.validForApprovalCompletion
                + " validForCheckIn:" + this.validForCheckIn
                + " validForCheckOut:" + this.validForCheckOut + " validForDelete:"
                + this.validForDelete 
                + " validForPublish:" + this.validForPublish + " validForTest:"
                + this.validForTest + " validForTestCompletion:"
                + this.validForTestCompletion+ " validForTransfer:"
                + this.validForTransfer + " isEditableByUser:"
                + this.isEditableByUser + " isLocked:" + this.isLocked 
	            + " isLockedByUser:" + this.lockedByUser; 
	}


	/**
	 * @return Returns the validForView.
	 */
	public boolean isValidForView() {
		return validForView;
	}
	/**
	 * @param validForView The validForView to set.
	 */
	public void setValidForView(boolean validForView) {
		this.validForView = validForView;
	}
	/**
	 * @return Returns the validForCreate.
	 */
	public boolean isValidForCreate() {
		return validForCreate;
	}
	/**
	 * @param validForCreate The validForCreate to set.
	 */
	public void setValidForCreate(boolean validForCreate) {
		this.validForCreate = validForCreate;
	}
	/**
	 * @return Returns the lockedByUser.
	 */
	public boolean isLockedByUser() {
		return lockedByUser;
	}
	/**
	 * @param lockedByUser The lockedByUser to set.
	 */
	public void setLockedByUser(boolean lockedByUser) {
		this.lockedByUser = lockedByUser;
	}
}