/*
 * MigrationGenerateReportResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationGenerateReportResponse  extends MigrationContractResponse{
	
	private List unmappedVariableList;
	private List conflictingLines;

	/**
	 * Returns the unmappedVariableList
	 * @return List unmappedVariableList.
	 */
	public List getUnmappedVariableList() {
		return unmappedVariableList;
	}
	/**
	 * Sets the unmappedVariableList
	 * @param unmappedVariableList.
	 */
	public void setUnmappedVariableList(List unmappedVariableList) {
		this.unmappedVariableList = unmappedVariableList;
	}
	/**
	 * Returns the conflictingLines.
	 * @return List conflictingLines.
	 */
	public List getConflictingLines() {
		return conflictingLines;
	}
	/**
	 * Sets the conflictingLines.
	 * @param conflictingLines.
	 */

	public void setConflictingLines(List conflictingLines) {
		this.conflictingLines = conflictingLines;
	}
}
