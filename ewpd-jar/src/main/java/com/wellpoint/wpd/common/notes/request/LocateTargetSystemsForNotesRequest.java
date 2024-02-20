/*
 * LocateTargetSystemsForNotesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateTargetSystemsForNotesRequest extends WPDRequest{
    
    private String notesId;
   
    
   
	/**
	 * @return Returns the notesId.
	 */
	public String getNotesId() {
		return notesId;
	}
	/**
	 * @param notesId The notesId to set.
	 */
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
}
