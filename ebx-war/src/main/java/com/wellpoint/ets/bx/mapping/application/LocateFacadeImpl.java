/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.owasp.esapi.ESAPI;
import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService;
import com.wellpoint.ets.bx.mapping.domain.service.VariableService;
import com.wellpoint.ets.bx.mapping.domain.vo.LocateResultHelper;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateFacadeImpl implements LocateFacade {
    
	private VariableService variableService;
	private HippaSegmentService hippaSegmentService;
	private int noOfRecords;
	private int noOfRecordsForAutoComplete;

	private static Logger log = Logger.getLogger(LocateFacadeImpl.class);
	
	/* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#getVariableStartingWith(java.lang.String)
     */
    public List findUnmappedVariableStartingWith(String variableId) {
        List variable = variableService.findUnmappedVariableStartingWith(variableId,noOfRecordsForAutoComplete);
        return variable;
    }
    
    public HippaSegment getHippaSegmentsForAutocomplete(String hippaSegmentName, String typedInValue, Variable variable,int noOfRecords){
    	if(null == typedInValue || ("".equals(typedInValue.trim()))){
            log.info("There is no search string to list down the auto complete.");
            return null;
        }
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return null;
        }
        log.debug("@@@@@@@@@@@@noOfRecords in locatefacade : "+noOfRecords);
        HippaSegment hippaSegment = hippaSegmentService.getHippaSegmentsForAutocomplete(hippaSegmentName, typedInValue,variable,noOfRecords);
        return hippaSegment;
    }
	
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#findMatchingHippaCodeValuesForEB01(java.lang.String, java.lang.String, com.wellpoint.ets.bx.mapping.domain.entity.Variable)
     */
    public HippaSegment findMatchingHippaCodeValuesForEB01(String hippaSegmentName, String searchString, Variable variable) {
        HippaSegment hippaSegment = new HippaSegment();
        
        if(null == searchString || ("".equals(searchString.trim()))){
            log.info("There is no search string to search.");
            return hippaSegment;
        }
        
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return hippaSegment;
        }
        
        if(!hippaSegmentName.equals(DomainConstants.EB01_NAME)){
            log.info(ESAPI.encoder().encodeForHTML("Expected hippaSegmentName is "+DomainConstants.EB01_NAME+"but found :"+hippaSegmentName));
            return hippaSegment;
        }
        int recordCount = -1;
        hippaSegment = hippaSegmentService.findMatchingHippaCodeValues(hippaSegmentName, searchString, variable, recordCount);
        return hippaSegment;
    }
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#findMatchingHippaCodeValues(java.lang.String, java.lang.String)
     */
    public HippaSegment findMatchingHippaCodeValues(String hippaSegmentName, String searchString){
        HippaSegment hippaSegment = new HippaSegment();
        
        if(null == searchString || ("".equals(searchString.trim()))){
            log.info("There is no search string to search.");
            return hippaSegment;
        }
        
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return hippaSegment;
        }
        int recordCount = -1;
        if(hippaSegmentName.equals(DomainConstants.ACCUM_NAME)){
            recordCount = noOfRecords;
        }
        
        hippaSegment = hippaSegmentService.findMatchingHippaCodeValues(hippaSegmentName, searchString, null,recordCount);
        return hippaSegment;
    }
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#getAvailableHippaSegmentValuesForEB01(java.lang.String, java.lang.String,com.wellpoint.ets.bx.mapping.domain.entity.Variable)
     */
    public HippaSegment getAvailableHippaSegmentValuesForEB01(String hippaSegmentName, Variable variable){
        HippaSegment hippaSegment = new HippaSegment();
        
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return hippaSegment;
        }
        
        if(!hippaSegmentName.equals(DomainConstants.EB01_NAME)){
            log.info(ESAPI.encoder().encodeForHTML("Expected hippaSegmentName is "+DomainConstants.EB01_NAME+"but found :"+hippaSegmentName));
            return hippaSegment;
        }
        int recordCount = -1;
        hippaSegment = hippaSegmentService.getAvailableHippaSegmentValues(hippaSegmentName, variable,recordCount);
        return hippaSegment;
    }
    
    public HippaSegment getAvailableHippaSegmentValues(String hippaSegmentName){
        HippaSegment hippaSegment = new HippaSegment();
       
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return hippaSegment;
        }
        
        int recordCount = -1;
        if(hippaSegmentName.equals(DomainConstants.ACCUM_NAME)){
            recordCount = noOfRecords;
        }
        
        hippaSegment = hippaSegmentService.getAvailableHippaSegmentValues(hippaSegmentName, null,recordCount);
        return hippaSegment;
    }

    public List findMatchingVariables(Variable variable) {
    	variableService.findMatchingVariables(variable);
        return null;
    }

	public List findAllUnmappedVariables() {
	    List unmappedVariableList = variableService.findAllUnmappedVariables();//List<Mapping>
	   // unmappedVariableList = wrapTheLocateResultInLocateResultDisplayHelper(unmappedVariableList);//List<LocateResultDisplayHelper>
		return unmappedVariableList;
	}
	
	/* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#findAllInProgressVariables()
     */
    public List findAllInProgressVariables(String loggedInUser, int noOfRecords, boolean printFlag) {
        List inProgressVariables = variableService.findAllInProgressVariables(loggedInUser, noOfRecords, printFlag);//List<Mapping>
       // inProgressVariables = wrapTheLocateResultInLocateResultDisplayHelper(inProgressVariables);//List<LocateResultDisplayHelper>
        return inProgressVariables;
    }
    
    public List findLocateResultsMatchingVariables(Variable variable, Page page) {
    	List locateResultsList = variableService.findLocateResultsMatchingVariables(variable, page);//List<Locate result List Objects>
    	List locateResultsIndList = null;
    	List finalLocalResultsList = new ArrayList();
    	if(null == locateResultsList || locateResultsList.isEmpty()){
    		return new ArrayList();
    	}
    	// iterating the individual lists containing unmapped variables and mapped/notapplicable variables and 
    	// putting it in a single list
    	for(int i=0;i<locateResultsList.size();i++){
    		locateResultsIndList = (ArrayList)locateResultsList.get(i);
    		for(int j=0;j<locateResultsIndList.size();j++){
    				finalLocalResultsList.add(locateResultsIndList.get(j));
    		}
    	} 
        return finalLocalResultsList;
    }
	/**
	 * @param variables
	 * mapping is a list of Mapping object
	 * The method will return a list of LocateResultDisplayHelper
	 * @return
	 */
	public List wrapTheLocateResultInLocateResultDisplayHelper(List mapping){
	    if((null == mapping) || (null != mapping && mapping.isEmpty())){
	        return null;
	    }
	    LocateResultHelper locateResultDisplayHelper;
	    List list = new ArrayList(); //List<LocateResultDisplayHelper>
	    for(Iterator itr = mapping.iterator();itr.hasNext();){
	        Mapping mappingObj = (Mapping) itr.next();
	        locateResultDisplayHelper = new LocateResultHelper();
	        locateResultDisplayHelper.setMapping(mappingObj);
	        list.add(locateResultDisplayHelper);
	    }
	    return list;   
	}
	
	
	
	//getters and setters
	
    /**
     * @return Returns the variableService.
     */
    public VariableService getVariableService() {
        return variableService;
    }
    /**
     * @param variableService The variableService to set.
     */
    public void setVariableService(VariableService variableService) {
        this.variableService = variableService;
    }

    /**
     * @return Returns the hippaSegmentService.
     */
    public HippaSegmentService getHippaSegmentService() {
        return hippaSegmentService;
    }
    /**
     * @param hippaSegmentService The hippaSegmentService to set.
     */
    public void setHippaSegmentService(HippaSegmentService hippaSegmentService) {
        this.hippaSegmentService = hippaSegmentService;
    }


    /**
     * @return Returns the noOfRecordsForAutoComplete.
     */
    public int getNoOfRecordsForAutoComplete() {
        return noOfRecordsForAutoComplete;
    }
    /**
     * @param noOfRecordsForAutoComplete The noOfRecordsForAutoComplete to set.
     */
    public void setNoOfRecordsForAutoComplete(int noOfRecordsForAutoComplete) {
        this.noOfRecordsForAutoComplete = noOfRecordsForAutoComplete;
    }
	/**
	 * @return Returns the noOfRecords.
	 */
	public int getNoOfRecords() {
		return noOfRecords;
	}
	/**
	 * @param noOfRecords The noOfRecords to set.
	 */
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#getHippaCodeDescription(java.lang.String)
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentCode) {		              
		HippaSegment hippaSegment= null;
		hippaSegment = hippaSegmentService.getHippaSegmentDescription(hippaSegmentCode);		
		return hippaSegment;
	}
	
	public int findTotalNoOfRecordsForLocate(Variable variable){
		int totalNoOfRecords = variableService.findTotalNoOfRecords(variable);
		return totalNoOfRecords;
	}
	 /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#getAvailableUMRule(java.lang.String)
     */
    public HippaSegment getAvailableUMRule(String hippaSegmentName) {
    	HippaSegment hippaSegment = new HippaSegment();
    	
    	  if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
              log.info("HippaSegment name cannot be null.");
              return hippaSegment;
          }
		hippaSegment =  hippaSegmentService.getAvailableUMRule(hippaSegmentName, noOfRecords);
        return hippaSegment;
	}
    /* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#viewRuleDetails(java.lang.String)
	 */
	public List viewRuleSequenceIndicators(String umRuleId) {
		List ruleSequenceList = hippaSegmentService.viewRuleSequenceIndicators(umRuleId);
		return ruleSequenceList;
	}
	// Start-- Added as part of ICD10-October release//
	// Returns CodeSequences corresponding to a Rule ID //
	public List viewRuleCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion){
		List ruleSequenceList = hippaSegmentService.viewRuleCodeSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}
	// Returns ClaimLevelSequences corresponding to a Rule ID //
	public List viewClaimLevelSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion){
		List ruleSequenceList = hippaSegmentService.viewClaimLevelSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}
	// Returns ClaimCodeSequences corresponding to a Rule ID //
	public List viewRuleClaimCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion){
		List ruleSequenceList = hippaSegmentService.viewRuleClaimCodeSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}
	// End-- Added as part of ICD10-October release//
	/**
	 * 
	 */
	public List findMatchingAccumulator(String accumName, String accumDesc){
		return hippaSegmentService.findMatchingAccumulator(accumName, accumDesc);
	}
	/**
	 * 
	 */
	public List getAvailableAccumulators(){
		return hippaSegmentService.getAvailableAccumulators(50);
		 
	}

	public List findAllVariableStartingWith(String variableId) {
        List variable = variableService.findAllVariableStartingWith(variableId,noOfRecordsForAutoComplete);
        return variable;
	}
	
	 /**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public HippaSegment getAllEB01ForAutocomplete(String searchString, int noOfRecords) {
		return hippaSegmentService.getAllEB01ForAutocomplete(searchString, noOfRecords);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#fetchRuleInfo(java.lang.String)
	 */
	public UMRule fetchRuleInfo(String ruleId) {
		return hippaSegmentService.fetchRuleInfo(ruleId);
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.LocateFacade#getRecordsForReport(variable)
	 */
	public List getRecordsForReport(Variable variable) {
		return variableService.getRecordsForReport(variable);
	}
	public List viewGrpRule(String ruleId) {
		return hippaSegmentService.viewGrpRule(ruleId);
	}
	 /**
     * The method will retrieve all possible Age Tier values corresponding to a variable.
     * BXNI June 2012 Release
     * @param hippaSegmentName,seachText,variable
     * @return ageTierVariablelist
     */
    public List<Variable> getAvailableAgeTierVariables(String hippaSegmentName,String searchString,Variable variable){
        int recordCount = -1;
    	return hippaSegmentService.getAvailableAgeTierVariables(hippaSegmentName,searchString,variable,recordCount);
    }
    
    public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
    	return hippaSegmentService.fetchHippaSegmentDescription(hippaSegmentName);
    }

	@Override
	public boolean isEBSegmentPresent(String hippaSegmentName) {
		return hippaSegmentService.isEBSegmentPresent(hippaSegmentName);
	}

	@Override
	public String getEBMappingAssocDetails(String ebSeg) {
		return hippaSegmentService.getEBMappingAssocDetails(ebSeg);
	}
	
	@Override
	public boolean isEBSegmentPresentIcon(String hippaSegmentName) {
		return hippaSegmentService.isEBSegmentPresentIcon(hippaSegmentName);
	}

	@Override
	public String getEBMappingAssocDetailsIcon(String ebSeg) {
		return hippaSegmentService.getEBMappingAssocDetailsIcon(ebSeg);
	}
}
