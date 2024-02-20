/*
 * <VariableServiceImpl.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.repository.VariableLocateRepository;

/**
 * @author UST-GLOBAL
 * This is an interface class for locating or searching a variable
 */
public class VariableServiceImpl implements VariableService{
    
    private VariableLocateRepository variableLocateRepository;
  
  
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableService#getVariableStartingWith(java.lang.String)
     */
    public List findUnmappedVariableStartingWith(String variableId, int noOfRecordsForAutoComplete) {
        List unmappedVariables = variableLocateRepository.findUnmappedVariableStartingWith(variableId,noOfRecordsForAutoComplete);
        return unmappedVariables;
    }
    
    /**
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableService#findAllVariableStartingWith(java.lang.String, int)
     * @param variableId
     * @param noOfRecordsForAutoComplete
     * @return
     */
    public List findAllVariableStartingWith(String variableId, int noOfRecordsForAutoComplete) {
        List allVariables = variableLocateRepository.findAllVariableStartingWith(variableId,noOfRecordsForAutoComplete);
        return allVariables;
    }
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableService#findVariable(java.lang.String)
     */
    public List findMatchingVariables(Variable variable) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableService#findAllUnmappedVariables(java.lang.String)
     */
	public List findAllUnmappedVariables() {
		List unmappedVariableList = variableLocateRepository.findAllUnmappedVariables();
		return unmappedVariableList;
	}
	
	/* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableService#findAllInProgressVariables(java.lang.String)
     */
	public List findAllInProgressVariables(String loggedInUser, int noOfRecords, boolean printFlag){
	    List inProgressVariables = variableLocateRepository.findAllInProgressVariables(loggedInUser, noOfRecords, printFlag);
		return inProgressVariables;
	}
	
	public List findLocateResultsMatchingVariables(Variable variable, Page page) {
    	List locateResultsList = variableLocateRepository.findLocateResultsMatchingVariables(variable, page);
        return locateResultsList;
    }
	
	public int findTotalNoOfRecords(Variable variable){
		int totalNoOfRecords = variableLocateRepository.findTotalNoOfRecords(variable);
		return totalNoOfRecords;
	}
    //getters and setters
    /**
     * @return Returns the variableLocateRepository.
     */
    public VariableLocateRepository getVariableLocateRepository() {
        return variableLocateRepository;
    }
    /**
     * @param variableLocateRepository The variableLocateRepository to set.
     */
    public void setVariableLocateRepository(VariableLocateRepository variableLocateRepository) {
        this.variableLocateRepository = variableLocateRepository;
    }
    public List getRecordsForReport(Variable variable) {
    	return variableLocateRepository.getRecordsForReport(variable);
    }
}
