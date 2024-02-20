package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;

public interface UmRuleMappingService {

	int isValidRuleIDStatus(String ruleId);

	List retrieveRuleIdInfo(String ruleId);

	CreateOrEditUmRuleMappingResult createOrEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList,
			SpiderUMRuleMapping spiderMaster, String persistType) throws DomainException;

	List createRuleIdAuditMapping(List<SpiderUMRuleMappingDataAudit> spiderList, String ruleId);

	List findAllRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete);

	List findAllRuleDescriptionStartingWith(String ruleIdDescription, int noOfRecordsForAutoComplete);

	List findAllUmRuleMappingResult(String ruleId);

	List findAllUmRuleMappingsDataByRuleId(String ruleId);

	List findAllUnmappedRules();

	public SpiderUMRuleMapping findByRuleId(String ruleId);

	List retrieveRuleInfo(SpiderUMRuleMapping spiderUMRuleMapping);

	int findMappingPresentOrNot(String ruleId, String eb03Value);

	boolean deleteMapping(String ruleId, String eb03Value);

	int findEb03PresentOrNot(String eb03Value);

	CreateOrEditUmRuleMappingResult sendToTest(SpiderUMRuleMapping spiderUMRuleMapping);

	List findExistingMappedRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete);

	CreateOrEditUmRuleMappingResult markAsNotApplicable(SpiderUMRuleMapping spiderUMRuleMapping);

	CreateOrEditUmRuleMappingResult saveMapping(SpiderUMRuleMapping spiderUMRuleMapping);

	CreateOrEditUmRuleMappingResult sendToProd(SpiderUMRuleMapping spiderUMRuleMapping);

	CreateOrEditUmRuleMappingResult discardChanges(SpiderUMRuleMapping spiderUMRuleMapping);

	List findEb03Exclusions();

	List createEb03Exclusion(EB03Exclusion spiderEb03Exclusion);

	boolean deleteEb03Exclusion(String eb03Value);
	
	List validateEb03Exclusion(String eb03Value);
		
	List findEb03DefaultMappings();
	
	List createEb03DefaultMapping(EB03DefaultMapping spiderEb03Exclusion);
	
	List validateEb03DefaultMapping(String eb03Value,String defaultValue);

	boolean deleteEb03DefaultMapping(String eb03Value);

	List getHippaSegmentsForAutocomplete(String ruleId,int noOfRecords,String searchType);

	List findAllUmRuleMappingsByEb03(String eb03);

	boolean deleteRuleMapping(String ruleId);

	List findAllUmRuleComments(String umRuleId);

	List findAllUmRuleMappingResultForEdit(String ruleId);

	List getEb03DefaultForAutocomplete(String searcheb03, int noOfRecordsForAutoComplete, String searchType);

	void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderUMRuleMapping);

	String verifyUmRuleExistByRuleId(String ruleId);
	
	List findAuditDetailsByRuleId(String umRuleId);

	int findEb03DefaultMappingsPresentOrNot(String eb03);

	CreateOrEditUmRuleMappingResult markAsApplicable(SpiderUMRuleMapping spiderUMRuleMapping);
}
