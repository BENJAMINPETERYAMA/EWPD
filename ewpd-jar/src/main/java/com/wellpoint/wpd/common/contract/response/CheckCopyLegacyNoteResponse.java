/*
 * Created on Nov 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u20776
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CheckCopyLegacyNoteResponse extends WPDResponse{
	
	private boolean isCopyLegacyNoteIndcatorOn;
	
	private boolean isLegacyNotesExist;
	
	/**
	 * @return Returns the isCopyLegacyNoteIndcatorOn.
	 */
	public boolean isCopyLegacyNoteIndcatorOn() {
		return isCopyLegacyNoteIndcatorOn;
	}
	/**
	 * @param isCopyLegacyNoteIndcatorOn The isCopyLegacyNoteIndcatorOn to set.
	 */
	public void setCopyLegacyNoteIndcatorOn(boolean isCopyLegacyNoteIndcatorOn) {
		this.isCopyLegacyNoteIndcatorOn = isCopyLegacyNoteIndcatorOn;
	}
	/**
	 * @return Returns the isLegacyNotesExist.
	 */
	public boolean isLegacyNotesExist() {
		return isLegacyNotesExist;
	}
	/**
	 * @param isLegacyNotesExist The isLegacyNotesExist to set.
	 */
	public void setLegacyNotesExist(boolean isLegacyNotesExist) {
		this.isLegacyNotesExist = isLegacyNotesExist;
	}
}
