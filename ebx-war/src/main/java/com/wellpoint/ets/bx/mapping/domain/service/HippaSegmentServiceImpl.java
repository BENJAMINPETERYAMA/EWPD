/*
 * Created on May 6, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.UMRule;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepositoryImpl;
import com.wellpoint.ets.bx.mapping.repository.MappingRepository;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HippaSegmentServiceImpl implements HippaSegmentService {
    
    private HippaSegmentRepository hippaSegmentRepository;
    
    private static Logger log = Logger.getLogger(HippaSegmentServiceImpl.class);
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService#findMatchingHippaCodeValue(java.lang.String, java.lang.String, com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
     */
     public HippaSegment findMatchingHippaCodeValues(String hippaSegmentName, String searchString, Variable variable, int noOfRecords) {
        
        if(null == searchString || ("".equals(searchString.trim()))){
            log.info("There is no search string to search.");
            return null;
        }
        
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return null;
        }
        List matchingHippaCodeValues = null; 
        HippaSegment hippaSegment = new HippaSegment();
        hippaSegment.setName(hippaSegmentName);
        if(DomainConstants.EB01_NAME.equalsIgnoreCase(hippaSegmentName.trim())){
            if(null == variable){
                log.info("Variable cannot be null.");
                return hippaSegment;
            }
            if(null == variable.getVariableFormat()){
                log.info("Variable format cannot be null.");
                return hippaSegment;  
            }
            if("".equals(variable.getVariableFormat().trim())){
                log.info("Variable format cannot be null.");
                return hippaSegment;
            }
            String variableFormat = variable.getVariableFormat();
            matchingHippaCodeValues = hippaSegmentRepository.findMatchingHippaCodeValuesForEB01(hippaSegmentName.trim(), searchString.trim(), variableFormat.trim(), noOfRecords);
            hippaSegment.setHippaCodePossibleValues(matchingHippaCodeValues);
            return hippaSegment;
        }
        
        matchingHippaCodeValues = hippaSegmentRepository.findMatchingHippaCodeValuesFromDataDictionary(hippaSegmentName.trim(), searchString.trim(), noOfRecords);
        hippaSegment.setHippaCodePossibleValues(matchingHippaCodeValues);
        return hippaSegment;
    }
    
    
    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.VariableMappingLocateService#getAvailableHippaSegmentValues(java.lang.String)
     */
    public HippaSegment getAvailableHippaSegmentValues(String hippaSegmentName,Variable variable,int noOfRecords) {
        
        if(null == hippaSegmentName  || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return null;
        }
        HippaSegment hippaSegment = new HippaSegment();
        hippaSegment.setName(hippaSegmentName.trim());
        List possibleHippaCodeValues = null;
        if("EB01".equalsIgnoreCase(hippaSegmentName.trim()) ){
            if(null == variable){
                log.info("Variable cannot be null.");
                return hippaSegment;
            }
            if(null == variable.getVariableFormat()){
                log.info("Variable format cannot be null.");
                return hippaSegment;  
            }
            if("".equals(variable.getVariableFormat().trim())){
                log.info("Variable format cannot be null.");
                return hippaSegment;
            }
            String variableFormat = variable.getVariableFormat();
            possibleHippaCodeValues = hippaSegmentRepository.getAvailableHippaSegmentValuesForEB01(hippaSegmentName, variableFormat,variable.getPva(), noOfRecords);
            hippaSegment.setHippaCodePossibleValues(possibleHippaCodeValues);
            return hippaSegment;
        }
        
        possibleHippaCodeValues = hippaSegmentRepository.getAvailableHippaSegmentValuesFromDataDictionary(hippaSegmentName,noOfRecords);
        hippaSegment.setHippaCodePossibleValues(possibleHippaCodeValues);
                
        return hippaSegment;
    }
    
    public HippaSegment getHippaSegmentsForAutocomplete(String hippaSegmentName, String searchString,Variable variable, int noOfRecords) {
    	HippaSegment hippaSegment = new HippaSegment();
        if(null == searchString || ("".equals(searchString.trim()))){
            log.info("There is no search string to search.");
            return hippaSegment;
        }
        if(null == hippaSegmentName || ("".equals(hippaSegmentName.trim()))){
            log.info("HippaSegment name cannot be null.");
            return hippaSegment;
        }
        List matchingHippaCodeValues = null;
        hippaSegment.setName(hippaSegmentName);
        searchString = searchString.toUpperCase();
        if("EB01".equalsIgnoreCase(hippaSegmentName.trim())){
            if("".equals(variable.getVariableFormat().trim())){
                log.info("Variable format cannot be null.");
                return hippaSegment;
            }
            String variableFormat = variable.getVariableFormat();
            matchingHippaCodeValues = hippaSegmentRepository.getHippaSegmentsForAutocomplete(hippaSegmentName.trim(), searchString.trim(), variableFormat.trim(), noOfRecords);
            hippaSegment.setHippaCodePossibleValues(matchingHippaCodeValues);
            return hippaSegment;
        }

        matchingHippaCodeValues = hippaSegmentRepository.getMatchingHippaCodeValuesFromDataDictionaryForAutoComplete(hippaSegmentName.trim(), searchString.trim(), noOfRecords);
        hippaSegment.setHippaCodePossibleValues(matchingHippaCodeValues);
        return hippaSegment;
    }
    
    /**
	 * This function is added for mass update EB01 auto populate to get all EB01 values , 
	 * since there are multiple formats.
	 * @param searchString
	 * @param noOfRecords
	 * @return
	 */
	public HippaSegment getAllEB01ForAutocomplete(String searchString, int noOfRecords) {
		List matchingHippaCodeValues = null;
		HippaSegment hippaSegment = new HippaSegment();
		searchString = searchString.toUpperCase();
		matchingHippaCodeValues = hippaSegmentRepository.getAllEB01ForAutocomplete(searchString.trim(), noOfRecords);
        hippaSegment.setHippaCodePossibleValues(matchingHippaCodeValues);
        return hippaSegment;
	}
    //getters and setters
    /**
     * @return Returns the hippaSegmentRepository.
     */
    public HippaSegmentRepository getHippaSegmentRepository() {
        return hippaSegmentRepository;
    }
    /**
     * @param hippaSegmentRepository The hippaSegmentRepository to set.
     */
    public void setHippaSegmentRepository(HippaSegmentRepository hippaSegmentRepository) {
        this.hippaSegmentRepository = hippaSegmentRepository;
    }


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService#getHippaCodeDescription(java.lang.String)
	 */
	public HippaSegment getHippaSegmentDescription(String hippaSegmentName) {		 	
		
		 HippaSegment hippaSegment= null;
		 hippaSegment = hippaSegmentRepository.getHippaSegmentDescription(hippaSegmentName);
		 return hippaSegment;		 
	} 
	public HippaSegment getAvailableUMRule(String hippaSegmentName, int noOfRecords){
		HippaSegment hippaSegment = new HippaSegment();
		hippaSegment.setName(hippaSegmentName.trim());
		List possibleHippaCodeValues = null;
		
		if(DomainConstants.UMRULE_NAME.equalsIgnoreCase(hippaSegmentName.trim())){
			possibleHippaCodeValues = hippaSegmentRepository.getAvailableUMRule(hippaSegmentName,noOfRecords);
			hippaSegment.setHippaCodePossibleValues(possibleHippaCodeValues);
		}
		return hippaSegment;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService#viewRuleDetails(java.lang.String)
	 */
	public List viewRuleSequenceIndicators(String ruleId) {
		List ruleSequenceList = hippaSegmentRepository.viewRuleSequenceIndicators(ruleId);
		return ruleSequenceList;
	}   
	// Start-- Added as part of ICD10-October release//
	// Returns CodeSequences corresponding to a Rule ID //
	public List viewRuleCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List ruleSequenceList = hippaSegmentRepository.viewRuleCodeSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}
	// Returns ClaimLevelSequences corresponding to a Rule ID //
	public List viewClaimLevelSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List ruleSequenceList = hippaSegmentRepository.viewClaimLevelSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}  
	// Returns ClaimCodeSequences corresponding to a Rule ID //
	public List viewRuleClaimCodeSequenceIndicators(String ruleId,List ruleSequenceNumberList,int RuleVersion) {
		List ruleSequenceList = hippaSegmentRepository.viewRuleClaimCodeSequenceIndicators(ruleId,ruleSequenceNumberList,RuleVersion);
		return ruleSequenceList;
	}   
	// End-- Added as part of ICD10-October release//
	public List findMatchingAccumulator(String accumName, String accumDesc){
		List accumList = hippaSegmentRepository.findMatchingAccumulator(accumName, accumDesc);
		/*if(accumList != null && accumList.size() > 0){
			return this.getAccumForDisplay(accumList);
		}*/
		return accumList;
	}
	public List getAvailableAccumulators(int rowCount){
		List accumList = hippaSegmentRepository.getAvailableAccumulators(rowCount);
		/*if(accumList != null && accumList.size() > 0){
			return this.getAccumForDisplay(accumList);
		}*/
		return accumList;
	}
	

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService#fetchRuleInfo(java.lang.String)
	 */
	public UMRule fetchRuleInfo(String ruleId) {
		return hippaSegmentRepository.fetchRuleInfo(ruleId);
	}


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService#viewGrpRule(java.lang.String)
	 */
	public List viewGrpRule(String ruleId) {
		return hippaSegmentRepository.viewGrpRule(ruleId);
	}
	 /**
     * The method will retrieve all possible Age Tier values corresponding to a variable.
     * BXNI June 2012 Release
     * @param hippaSegmentName,seachText,variable,recordCount
     * @return ageTierVariablelist
     */
	 public List<Variable> getAvailableAgeTierVariables(String hippaSegmentName,String searchString,Variable variable, int recordCount){
		 //Find the benefitStructures to which the current variable is associated
		 List variableBenefitStructure = hippaSegmentRepository.getVariableBenefitStructure(variable);
		 //convert the variableBenefitStructure list to Comma Separated Single Quote String to pass in to the IN condition of the query 
		 String CSVwithSingleQuoteVarBnftStructure = BxUtil.convertArrayToCSVwithSingleQuote(variableBenefitStructure);
		 //get the ageTierVariableList which contains variables of the same benefit structure and of format 'AGE'
		 List ageTierVariableList = hippaSegmentRepository.getAgeTierVariables(CSVwithSingleQuoteVarBnftStructure,searchString);
		 return ageTierVariableList;
	 }

	 public Map<String,String> fetchHippaSegmentDescription(String hippaSegmentName){
		 return hippaSegmentRepository.fetchHippaSegmentDescription(hippaSegmentName);
	 }



	public boolean isEBSegmentPresent(String ebSeg) {
		int count=0;
		if (null != ebSeg && !ebSeg.isEmpty()) {
			if (hippaSegmentRepository.isMappingBeingModified(ebSeg)) {
				count = hippaSegmentRepository.getEBSegmentsTempCount(ebSeg);
			} else {
				count = hippaSegmentRepository.getEBSegmentsCount(ebSeg);
			}
		}
		if(count>0){
			return true;
		}else
		return false;
	}
	
	public String getEBMappingAssocDetails(String ebSeg){
		
		String listEBMapped = "";
		if (null != ebSeg && !ebSeg.isEmpty()) {
			if (hippaSegmentRepository.isMappingBeingModified(ebSeg)) {
				listEBMapped = hippaSegmentRepository
						.getEBMappingAssocDetailsTemp(ebSeg);
			} else {
				listEBMapped = hippaSegmentRepository
						.getEBMappingAssocDetails(ebSeg);
			}
		}
		if(!listEBMapped.isEmpty()){
			return listEBMapped;
		}else
		return "";
	}
	
	public boolean isEBSegmentPresentIcon(String ebSeg) {
		int count=0;
		if (null != ebSeg && !ebSeg.isEmpty()) {
			if (hippaSegmentRepository.isMappingBeingModified(ebSeg)) {
				count = hippaSegmentRepository.getEBSegmentsTempCountIcon(ebSeg);
			} else {
				count = hippaSegmentRepository.getEBSegmentsCountIcon(ebSeg);
			}
		}
		if(count>0){
			return true;
		}else
		return false;
	}
	
	public String getEBMappingAssocDetailsIcon(String ebSeg){
		
		String listEBMapped = "";
		if (null != ebSeg && !ebSeg.isEmpty()) {
			if (hippaSegmentRepository.isMappingBeingModified(ebSeg)) {
				listEBMapped = hippaSegmentRepository
						.getEBMappingAssocDetailsTempIcon(ebSeg);
			} else {
				listEBMapped = hippaSegmentRepository
						.getEBMappingAssocDetailsIcon(ebSeg);
			}
		}
		if(!listEBMapped.isEmpty()){
			return listEBMapped;
		}else
		return "";
	}
}
