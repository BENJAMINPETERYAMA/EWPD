package com.wellpoint.ets.bx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;

public interface UMRuleMappingFacade {
	
	public int isValidRuleIDStatus(String ruleId);

	public List viewRuleIdDetails(String ruleId);

	public CreateOrEditUmRuleMappingResult createOrEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList,
			SpiderUMRuleMapping spiderMaster,String persistType);
	
	public List createRuleIdAuditMapping(List<SpiderUMRuleMappingDataAudit> spiderList,String ruleId);
	
	public List findAllRuleStartingWith(String ruleId);
	
	public List findAllRuleDescriptionStartingWith(String ruleIdDescription);
	
	public List findAllMappingByRuleId(String ruleId);
	
	public List findAllUmRuleMappingsDataByRuleId(String ruleId);

	public List findAllUnmappedRules();

	public SpiderUMRuleMapping findByRuleId(String ruleId);

	
	public List viewRuleDetails(SpiderUMRuleMapping spiderMap);

	public int findMappingPresentOrNot(String ruleId, String eb03Value);

	public int findEb03PresentOrNot(String eb03Value);

		
	public boolean deleteMapping(String ruleId, String eb03Value);
	
	public CreateOrEditUmRuleMappingResult sendToTest(SpiderUMRuleMapping spiderUMRuleMapping);

	public List findExistingMappedRuleStartingWith(String ruleId);

	public CreateOrEditUmRuleMappingResult notApplicable(SpiderUMRuleMapping spiderUMRuleMapping);

	public CreateOrEditUmRuleMappingResult saveAndsendToTest(SpiderUMRuleMapping spiderUMRuleMapping);

	public CreateOrEditUmRuleMappingResult saveAndApprove(SpiderUMRuleMapping spiderUMRuleMapping);

	public CreateOrEditUmRuleMappingResult discardChanges(SpiderUMRuleMapping spiderUMRuleMapping);

	
	public List findEb03Exclusions();
	
	public List createEb03Exclusion(EB03Exclusion spiderEb03Exclusion);
	
	public List validateEb03Exclusion(String eb03Value);
	
	public boolean deleteEb03Exclusion(String eb03Value);
	
	
	public List findEb03DefaultMappings();
	
	public List createEb03DefaultMapping(EB03DefaultMapping spiderEb03Exclusion);
	
	public List validateEb03DefaultMapping(String eb03Value,String defaultValue);
	
	public boolean deleteEb03DefaultMapping(String eb03Value);

	public List getHippaSegmentsForAutocomplete(String ruleId,String searchType);

	public List findAllUmRuleMappingsByEb03(String eb03);

	public boolean deleteRuleMapping(String ruleId);

	public List findAllUmRuleComments(String umRuleId);

	public List findAllMappingByRuleIdForEdit(String ruleId);

	public List getEb03DefaultForAutocomplete(String searchString, String searchType);

	public void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderUMRuleMapping);

	public String verifyUmRuleExistByRuleId(String ruleId);
 
	public List findAuditDetailsByRuleId(String umRuleId);

	public int findEb03DefaultMappingsPresentOrNot(String eb03);

	public CreateOrEditUmRuleMappingResult markAsApplicable(SpiderUMRuleMapping spiderUMRuleMapping);

}
