/*
 * Created on May 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductStructureNotesResponse extends WPDResponse{
	
	private List benefitComponentNotesList;
	
	
	

	/**
	 * @return Returns the benefitComponentNotesList.
	 */
	public List getBenefitComponentNotesList() {
		return benefitComponentNotesList;
	}
	/**
	 * @param benefitComponentNotesList The benefitComponentNotesList to set.
	 */
	public void setBenefitComponentNotesList(List benefitComponentNotesList) {
		this.benefitComponentNotesList = benefitComponentNotesList;
	}
    
}
