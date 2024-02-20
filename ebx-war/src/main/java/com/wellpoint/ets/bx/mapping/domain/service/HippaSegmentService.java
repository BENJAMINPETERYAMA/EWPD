/*
 * Created on May 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface HippaSegmentService {
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param variable
     * @param noOfRecords
     * The method will retrieve possible hippacde values matching searchString.
     * For Eb01,variableFormat is mandatory in Variable.
     * @return
     */
    public HippaSegment findMatchingHippaCodeValues(String hippaSegmentName, String searchString, Variable variable, int noOfRecords);
    
    /**
     * @param hippaSegmentName
     * @param variable
     * @param noOfRecords
     * The method will retrieve all possible hippacode values.
     * For EB01,variab;eFormat is mandatory in Variable
     * @return
     */
    public HippaSegment getAvailableHippaSegmentValues(String hippaSegmentName,Variable variable, int noOfRecords);
    
    public HippaSegment getHippaSegmentsForAutocomplete(String hippaSegmentName, String searchString,Variable variable, int noOfRecords);

	/**
	 * @param domainCode
	 * @return
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentCode);
	
	/**
     * @param hippaSegmentName
     * @param noOfRecords
     * The method will retrieve all possible UM rule values.
     * @return
     */
	public HippaSegment getAvailableUMRule(String hippaSegmentName, int noOfRecords);
	
	/**
	 * The method is used to fetch the sequence indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewRuleSequenceIndicators(String ruleId);
	
	/**
	 * The method is used to fetch the the Rule Code Sequence Indicators of the ruleId
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
	
	/**
	 * 
	 * @param accumName
	 * @param accumDesc
	 * @return List of accums
	 */
	public List findMatchingAccumulator(String accumName, String accumDesc);
	/**
	 * 
	 * @param rowCount
	 * @return List of available accums
	 */
	public List getAvailableAccumulators(int rowCount);
	
	 /**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public HippaSegment getAllEB01ForAutocomplete(String searchString, int noOfRecords);
	 /**
     * The method will retrieve all possible Age Tier values corresponding to a variable.
     * BXNI June 2012 Release
     * @param hippaSegmentName,seachText,variable,recordCount
     * @return ageTierVariablelist
     */
    public List<Variable> getAvailableAgeTierVariables(String hippaSegmentName,String searchString,Variable variable, int recordCount);
    
    /**
     * @param hippaSegmentName
     * @return Map<String,String> Val-Desc pair
     */
    public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName);
    
    /**
     * @author AF48640
     * @param Any variableId
     * @return boolean
     * 	checks if it has EB segment or not and returns the same
     * */
    public boolean isEBSegmentPresent(String ebSeg);
    
    /**
     * @author AF48640
     * @param Any variableId
     * @return boolean
     * 	checks if it has HSD02 or not and returns the same
     * */
    public String getEBMappingAssocDetails(String ebSeg);
    
    public boolean isEBSegmentPresentIcon(String ebSeg);
    
    public String getEBMappingAssocDetailsIcon(String ebSeg);
}
