/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomainResponse extends WPDResponse {
	
	private List results;
	
	private boolean notesInActivelyUsedStatus;

	/**
	 * @return Returns the results.
	 */
	public List getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(List results) {
		this.results = results;
	}
	
	/**
	 * @return Returns the notesInActivelyUsedStatus.
	 */
	public boolean isNotesInActivelyUsedStatus() {
		return notesInActivelyUsedStatus;
	}
	/**
	 * @param notesInActivelyUsedStatus The notesInActivelyUsedStatus to set.
	 */
	public void setNotesInActivelyUsedStatus(boolean notesInActivelyUsedStatus) {
		this.notesInActivelyUsedStatus = notesInActivelyUsedStatus;
	}
}
