/*
 * LocateStandardBenefitNotesResponse.java
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

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateStandardBenefitNotesResponse extends WPDResponse {
    private List benefitNotesList;
	
	

	/**
	 * @return Returns the benefitComponentNotesList.
	 */
	public List getBenefitNotesList() {
		return benefitNotesList;
	}
	/**
	 * @param benefitComponentNotesList The benefitComponentNotesList to set.
	 */
	public void setBenefitNotesList(List benefitComponentNotesList) {
		this.benefitNotesList = benefitComponentNotesList;
	}
}
