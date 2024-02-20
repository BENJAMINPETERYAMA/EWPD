/*
 * MigrationUnmappedVariables.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationMappedVariables {
	
	private String legacyDatesegmentId;
	private String variableId;


	/**
	 * Returns the variableId
	 * @return String variableId.
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * Sets the variableId
	 * @param variableId.
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * @return Returns the legacyDatesegmentId.
	 */
	public String getLegacyDatesegmentId() {
		return legacyDatesegmentId;
	}
	/**
	 * @param legacyDatesegmentId The legacyDatesegmentId to set.
	 */
	public void setLegacyDatesegmentId(String legacyDatesegmentId) {
		this.legacyDatesegmentId = legacyDatesegmentId;
	}
}
