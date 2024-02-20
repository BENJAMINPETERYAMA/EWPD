/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface VariableLocateRepository {
    
    public List findAllUnmappedVariables();
    
    public List findAllInProgressVariables(String loggedInUser, int noOfRecords, boolean printFlag);
    
    public List findUnmappedVariableStartingWith(String variableId,int noOfRecordsForAutoComplete);
    /**
     * @param variableId
     * @param noOfRecordsForAutoComplete
     * @return
     */
    public List findAllVariableStartingWith(String variableId,int noOfRecordsForAutoComplete);
    
    public List findLocateResultsMatchingVariables(Variable variable, Page page);
    
    public int findTotalNoOfRecords(Variable variable);
    public List getRecordsForReport(Variable variable);
}
