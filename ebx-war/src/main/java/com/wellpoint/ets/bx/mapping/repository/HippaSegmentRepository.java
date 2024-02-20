/*
 * Created on May 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface HippaSegmentRepository {
    
    /**
     * @param hippaSegmentName
     * @param variableFormat
     * @param pva 
     * @param noOfRecords
     * The method will return all the hippacodes of EB01
     * @return
     */
    public List getAvailableHippaSegmentValuesForEB01(String hippaSegmentName,String variableFormat,String pva, int noOfRecordsForPopUp);
    
    /**
     * @param hippaSegmentName
     * @param noOfRecordsFor
     * The method will return all the hippacodes except EB01
     * @return
     */
    public List getAvailableHippaSegmentValuesFromDataDictionary(String hippaSegmentName,int noOfRecords);
    
    
    //For EB01
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param variableFormat
     * @param noOfRecordsForPopUp
     * The method will return all the hippacodes of EB01 that matches the searchString
     * @return
     */
    public List findMatchingHippaCodeValuesForEB01(String hippaSegmentName, String searchString, String variableFormat, int noOfRecords);
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param noOfRecordsForPopUp
     * The method will return all the hippacodes except EB01 that matches the searchString
     * @return
     */
    public List findMatchingHippaCodeValuesFromDataDictionary(String hippaSegmentName, String searchString, int noOfRecords);
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param variableFormat
     * @param noOfRecords
     * @return list of EB01 values that starts with searchString
     */
    public List getHippaSegmentsForAutocomplete(String hippaSegmentName, String searchString, String variableFormat, int noOfRecords);
    
    /**
     * @param hippaSegmentName
     * @param searchString
     * @param noOfRecords
     * @return list of hippasegment values that starts with searchString other than EB01
     */
    public List getMatchingHippaCodeValuesFromDataDictionaryForAutoComplete(String hippaSegmentName, String searchString, int noOfRecords);

	/**
	 * @param hippaSegmentName
	 * @return the definition of hippaSegmentName
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentName);
	
	/**
	 * Added for retrieving value and description for UMRULE popup.
	 * @param hippaSegmentName
	 * @return HippaSegment
	 */
	public List getAvailableUMRule(String hippaSegmentName, int noOfRecords);
	
	/**
	 * The method is used to fetch the sequence indicators of the ruleId
	 * @param ruleId
	 * @return
	 */
	public List viewRuleSequenceIndicators(String ruleId);
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
	 * @return list of accums
	 */
	public List getAvailableAccumulators(int rowCount);
	/**
	 * 
	 * @param accumHippaSegmentList
	 * @return list of accums
	 */
	public List getAccumulators(Set accumHippaSegmentList, String System);
	
	/**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public List getAllEB01ForAutocomplete(String searchString, int noOfRecords);
	/**
	 * This function returns the associated Benefit structure of a variable
	 * @param Variable object
	 * @return variableBenefitStructure
	 */
	public List<String> getVariableBenefitStructure(Variable variable);
	/**
	 * This function returns the age tier variables associated to the corresponding benefit structure and of format 'AGE'
	 * @param variableBenefitStructure,searchString
	 * @return ageTierVariableList
	 */
	public List<Variable> getAgeTierVariables(String variableBenefitStructure,String searchString);
	
	 /**
	 * @param hippaSegmentName
	 * @return Map<String,String> Val_desc pair
	 */
	public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName);
	
	/**
	 * @author AF48640
	 * @return Int 
	 *  checks if the variable has EB segment and returns the number of them present
	 */
	public int getEBSegmentsCount(String variableId);
	
	/**
	 * @author AF37763
	 * @return Int 
	 *  checks if the variable has EB segment in temp and returns the number of them present
	 */
	public int getEBSegmentsTempCount(String variableId);
	
	/**
	 * @author AF48640
	 * @return Int 
	 *  checks if the variable is in HSD02 and returns the number of them present
	 */
	public String getEBMappingAssocDetails(String variableId);
	
	/**
	 * @author AF37763
	 * @return Int 
	 *  checks if the variable is in HSD02 in temp and returns the number of them present
	 */
	public String getEBMappingAssocDetailsTemp(String variableId);
	
	/**
	 * @author AF37763
	 * @return Int 
	 *  checks if the variable is in HSD02 in temp and returns the number of them present
	 */
	public boolean isMappingBeingModified(String variableId);
	
	
	public int getEBSegmentsCountIcon(String variableId);
	
	/**
	 * @author AF37763
	 * @return Int 
	 *  checks if the variable has EB segment in temp and returns the number of them present
	 */
	public int getEBSegmentsTempCountIcon(String variableId);
	
	/**
	 * @author AF48640
	 * @return Int 
	 *  checks if the variable is in HSD02 and returns the number of them present
	 */
	public String getEBMappingAssocDetailsIcon(String variableId);
	
	/**
	 * @author AF37763
	 * @return Int 
	 *  checks if the variable is in HSD02 in temp and returns the number of them present
	 */
	public String getEBMappingAssocDetailsTempIcon(String variableId);
}
