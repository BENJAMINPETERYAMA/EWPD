/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application;

import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface LocateFacade {
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param Variable
     * The method will retrieve possible hippacde values matching searchString for EB01.
     * For Eb01,variableFormat is mandatory in Variable.
     * @return
     */
    public HippaSegment findMatchingHippaCodeValuesForEB01(String hippaSegmentName, String searchString, Variable Variable );
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param Variable
     * The method will retrieve possible hippacde values matching searchString other than  EB01.
     * @return
     */
    public HippaSegment findMatchingHippaCodeValues(String hippaSegmentName, String searchString);
    
    /**
     * @param hippaSegmentName
     * @param Variable
     * The method will retrieve all possible hippacode values for EB01.
     * For EB01,variab;eFormat is mandatory in Variable
     * @return
     */
    public HippaSegment getAvailableHippaSegmentValuesForEB01(String hippaSegmentName,Variable variable);
    
    /**
     * @param hippaSegmentName
     * @param Variable
     * The method will retrieve all possible hippacode values except EB01.
     * @return
     */
    public HippaSegment getAvailableHippaSegmentValues(String hippaSegmentName);
    
    /**
     * This method will return all the unmapped variables from LG,ISG systems.
     * @return
     */
    public List findAllUnmappedVariables();
    
    /**
     * This method will return all the variables, created by the loggedin user, that are in the lifecycle.
     * Variable status : building,beign mosified,send to test,approve,send to production,published.
     * @param loggedInUser, noOfRecords
     * @return
     */
    public List findAllInProgressVariables(String loggedInUser, int noOfRecords, boolean printFlag);
    
    /**
     * This method would fetch the list to be populated in the locate results page.
     * @param variable
     * @return the locateresults list
     */
    public List findLocateResultsMatchingVariables(Variable variable, Page page);
    
    /**
     * @param variableId
     * To retrieve matching Variables from LG and ISG , starting with the variableId.
     * @return
     */
    public List findUnmappedVariableStartingWith(String variableId);
    
    /**
     * @param variableId
     * @return List
     * All variable list.
     */
    public List findAllVariableStartingWith(String variableId);
    
    /**
     * This method will return the total no of records matching the search criteria.
     * @param variableId
     * @param variableDesc
     * @return
     */
    public int findTotalNoOfRecordsForLocate(Variable variable);
    
    public HippaSegment getHippaSegmentsForAutocomplete(String hippaSegmentName, String searchString,Variable Variable,int noOfRecords);

	/**
	 * @param domainCode
	 * @return
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentCode);
	/**
	 * The method will return possible UM rule values.
	 * @param hippaSegmentName
	 * @return Available UM rules
	 */
	public HippaSegment getAvailableUMRule(String hippaSegmentName);
	
	/**
	 * The method is used to fetch the Rule Code Sequence Indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewRuleCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion);

	/**
	 * The method is used to fetch the Rule Claim Level Code Sequence Indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewClaimLevelSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion);
	/**
	 * The method is used to fetch the Rule Claim Code Sequence Indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewRuleClaimCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion);
	
	/**
	 * The method is used to fetch the sequence indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewRuleSequenceIndicators(String ruleId);
	
	/**
	 * The method is used to fetch the search word info and grp rule indicator of the ruleId
	 * @param ruleId
	 * @return
	 */
	public UMRule fetchRuleInfo(String ruleId);
	
	/**
	 * The method is used to fetch the group rules
	 * @param ruleId
	 * @return
	 */
	public List viewGrpRule(String ruleId);
	
	public List findMatchingAccumulator(String accumName, String accumDesc);
	public List getAvailableAccumulators();
	
	 /**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public HippaSegment getAllEB01ForAutocomplete(String searchString, int noOfRecords);
	public List getRecordsForReport(Variable variable);
	 /**
     * The method will retrieve all possible Age Tier values corresponding to a variable.
     * BXNI June 2012 Release
     * @param hippaSegmentName,seachText,variable
     * @return ageTierVariablelist
     */
    public List<Variable> getAvailableAgeTierVariables(String hippaSegmentName,String searchText,Variable variable);
    
    
    /**
     * @param hippaSegmentName
     * @return Map<String , String>-- HippaCodeValue and Description
     */
    public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName);
    
    /**
     * @param hippaSegmentName
     * @return validates EB segment in variables.
     */
    public boolean isEBSegmentPresent(String hippaSegmentName);
    
    /**
     * @author AF48640
     * @param Any variableId
     * @return boolean
     * 	checks if it has HSD02 or not and returns the same
     * */
    public String getEBMappingAssocDetails(String ebSeg);
    
    /**
     * @param hippaSegmentName
     * @return validates EB segment in variables.
     */
    public boolean isEBSegmentPresentIcon(String hippaSegmentName);
    
    /**
     * @author AF48640
     * @param Any variableId
     * @return boolean
     * 	checks if it has HSD02 or not and returns the same
     * */
    public String getEBMappingAssocDetailsIcon(String ebSeg);
}
