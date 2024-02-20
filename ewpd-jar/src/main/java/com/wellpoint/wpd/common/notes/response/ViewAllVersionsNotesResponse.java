/*
 * Created on May 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewAllVersionsNotesResponse extends WPDResponse{
	
	private List allVersionList;

	/**
	 * @return Returns the allVersionList.
	 */
	public List getAllVersionList() {
		return allVersionList;
	}
	/**
	 * @param allVersionList The allVersionList to set.
	 */
	public void setAllVersionList(List allVersionList) {
		this.allVersionList = allVersionList;
	}

}
