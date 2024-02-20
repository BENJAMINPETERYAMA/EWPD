/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface VariableService {

    public List findMatchingVariables(Variable variable);

	public List findAllUnmappedVariables();
	
	public List findAllInProgressVariables(String loggedInUser, int noOfRecords, boolean printFlag);
	
	public List findUnmappedVariableStartingWith(String variableId, int noOfRecordsForAutoComplete);
	
	public List findLocateResultsMatchingVariables(Variable variable, Page page);
	
	public int findTotalNoOfRecords(Variable variable);
	
	/**
	 * @param variableId
	 * @param noOfRecordsForAutoComplete
	 * @return
	 */
	public List findAllVariableStartingWith(String variableId, int noOfRecordsForAutoComplete);
	public List getRecordsForReport(Variable variable);
}
