/*
 * StandardBenefitNoteAttachmentResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitNoteAttachmentResponse extends WPDResponse {
    
    private StandardBenefitBO standardBenefitBO;
	
	List noteList;
	
    /**
     * @return noteList
     * 
     * Returns the noteList.
     */
    public List getNoteList() {
        return noteList;
    }
    /**
     * @param noteList
     * 
     * Sets the noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }
	/**
	 * @return Returns the standardBenefitBO.
	 */
	public StandardBenefitBO getStandardBenefitBO() {
		return standardBenefitBO;
	}
	/**
	 * @param standardBenefitBO The standardBenefitBO to set.
	 */
	public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
		this.standardBenefitBO = standardBenefitBO;
	}
}
