package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;

public interface UmRuleMappingRepository {

	public int isValidRuleID(String ruleId);

	public boolean persistNewRuleIdMapping(List<SpiderUMRuleMappingData> spiderList, SpiderUMRuleMapping spiderMaster);
	
	public boolean persistEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList, SpiderUMRuleMapping spiderMaster);
	
	

	public List findAllRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete);

	public List retrieveRuleIdInfo(String ruleId);
	
	public List findAllRuleDescriptionStartingWith(String ruleIdDescription, int noOfRecordsForAutoComplete);
	
	//public List findAllUmRuleMappingsByRuleId(String ruleId);
	
	public List findAllUmRuleMappingsDataByRuleId(String ruleId);

	
	
	
	public List findAllUnmappedRules();
	
	public List retrieveRuleInfo(SpiderUMRuleMapping spiderUMRuleMapping);

	public int findMappingPresentOrNot(String ruleId, String eb03Value);

	public boolean deleteMapping(String ruleId, String eb03Value);

	public void persistChildTable(List<SpiderUMRuleMappingData> spiderList,Long sysID);  
	
	public List persistAuditTable(List<SpiderUMRuleMappingDataAudit> spiderList,String ruleId);
		
	public int findEb03PresentOrNot(String eb03Value);

	public SpiderUMRuleMapping findByRuleId(String ruleId);
	
	public boolean deleteRuleIdMapping(Number id, String eb03Value);
	
	public List findExistingMappedRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete);

	public SpiderUMRuleMapping retrieveMapping(String ruleId);
	
	public List persistEb03Exclusion(EB03Exclusion spiderEB03Exclusion);
	public List findEb03Exclusions();
	
	public List validateEb03Exclusion(String spiderEB03);
	public boolean deleteEb03Exclusion(String spiderEB03);
	
	
	public List persistEb03DefaultMapping(EB03DefaultMapping spiderEB03Exclusion);
	
	public List validateEb03DefaultMapping(String spiderEB03,String defaultValue);

	public List findEb03DefaultMappings();
	public boolean deleteEb03DefaultMapping(String spiderEB03);
	
	
	public List getMatchingEb03FromDataDictionaryForAutoComplete(String searchString,int noRecords,String searchType);

	public List findAllUmRuleMappingsByEb03(String eb03);

	public boolean deleteRuleMapping(String ruleId);
	
	public boolean deleteRuleIdForSinglemapping(Number id);

	public List findAllUmRuleComments(String umRuleId);

	public List findAllUmRuleMappingResultForEdit(String ruleId);

	public List findAllUmRuleMappingsByRuleIdFromLocate(String ruleId);

	public List getEb03DefaultForAutocomplete(String searcheb03, int noOfRecordsForAutoComplete, String searchType);

	public void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderUMRuleMapping);

	public String verifyUmRuleExistByRuleId(String ruleId);
	
	public List findAuditDetailsByRuleId(String umRuleId);

	public void persistNewLogForMapping(SpiderUMRuleMapping spiderUMRuleMapping,Long sysID);

	public int findEb03DefaultMappingsPresentOrNot(String eb03);
}
