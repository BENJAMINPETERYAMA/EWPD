/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.response.MigrationContractResponse;

/**
 * @author U13083
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveLegacyContractNotesResponse extends MigrationContractResponse {
	
	private List contractNotes;
	
	private List majorHeading;
	
	private List minorHeading;
	
	private List majorNotes;
	
	private List minorNotes;

	/**
	 * @return Returns the contractNotes.
	 */
	public List getContractNotes() {
		return contractNotes;
	}
	/**
	 * @param contractNotes The contractNotes to set.
	 */
	public void setContractNotes(List contractNotes) {
		this.contractNotes = contractNotes;
	}
	
	/**
	 * @return Returns the majorHeading.
	 */
	public List getMajorHeading() {
		return majorHeading;
	}
	/**
	 * @param majorHeading The majorHeading to set.
	 */
	public void setMajorHeading(List majorHeading) {
		this.majorHeading = majorHeading;
	}
	/**
	 * @return Returns the minorHeading.
	 */
	public List getMinorHeading() {
		return minorHeading;
	}
	/**
	 * @param minorHeading The minorHeading to set.
	 */
	public void setMinorHeading(List minorHeading) {
		this.minorHeading = minorHeading;
	}
	
	/**
	 * @return Returns the majorNotes.
	 */
	public List getMajorNotes() {
		return majorNotes;
	}
	/**
	 * @param majorNotes The majorNotes to set.
	 */
	public void setMajorNotes(List majorNotes) {
		this.majorNotes = majorNotes;
	}
	/**
	 * @return Returns the minorNotes.
	 */
	public List getMinorNotes() {
		return minorNotes;
	}
	/**
	 * @param minorNotes The minorNotes to set.
	 */
	public void setMinorNotes(List minorNotes) {
		this.minorNotes = minorNotes;
	}
}
