/*
 * Created on Aug 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveHeadingsResponse extends WPDResponse{

	private List majorHeadingsList;
	
	private List minorHeadingList; 
	
	private List variableList;
	private List variableNotesList;
	/**
	 * @return Returns the majorHeadingsList.
	 */
	public List getMajorHeadingsList() {
		return majorHeadingsList;
	}
	/**
	 * @param majorHeadingsList The majorHeadingsList to set.
	 */
	public void setMajorHeadingsList(List majorHeadingsList) {
		this.majorHeadingsList = majorHeadingsList;
	}
	/**
	 * @return Returns the minorHeadingList.
	 */
	public List getMinorHeadingList() {
		return minorHeadingList;
	}
	/**
	 * @param minorHeadingList The minorHeadingList to set.
	 */
	public void setMinorHeadingList(List minorHeadingList) {
		this.minorHeadingList = minorHeadingList;
	}
	/**
	 * @return Returns the variableList.
	 */
	public List getVariableList() {
		return variableList;
	}
	/**
	 * @param variableList The variableList to set.
	 */
	public void setVariableList(List variableList) {
		this.variableList = variableList;
	}
	/**
	 * @return Returns the variableNotesList.
	 */
	public List getVariableNotesList() {
		return variableNotesList;
	}
	/**
	 * @param variableNotesList The variableNotesList to set.
	 */
	public void setVariableNotesList(List variableNotesList) {
		this.variableNotesList = variableNotesList;
	}
}
