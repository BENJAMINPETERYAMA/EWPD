/*
 * ContractNotes.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractNotes {
    private List noteList; 
	
	private int action;
	
	

    /**
     * Returns the action
     * @return int action.
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action.
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * Returns the noteList
     * @return List noteList.
     */
    public List getNoteList() {
        return noteList;
    }
    /**
     * Sets the noteList
     * @param noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }
}
