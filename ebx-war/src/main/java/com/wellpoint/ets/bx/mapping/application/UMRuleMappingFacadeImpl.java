package com.wellpoint.ets.bx.mapping.application;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.UmRuleMappingService;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;

public class UMRuleMappingFacadeImpl implements UMRuleMappingFacade {

	private UmRuleMappingService umRuleMappingService;
	private int noOfRecordsForAutoComplete;

	public int getNoOfRecordsForAutoComplete() {
		return noOfRecordsForAutoComplete;
	}

	public void setNoOfRecordsForAutoComplete(int noOfRecordsForAutoComplete) {
		this.noOfRecordsForAutoComplete = noOfRecordsForAutoComplete;
	}

	public UmRuleMappingService getUmRuleMappingService() {
		return umRuleMappingService;
	}

	public void setUmRuleMappingService(UmRuleMappingService umRuleMappingService) {
		this.umRuleMappingService = umRuleMappingService;
	}

	public int isValidRuleIDStatus(String ruleId) {
		return umRuleMappingService.isValidRuleIDStatus(ruleId);
	}

	public List viewRuleIdDetails(String ruleId) {

		return umRuleMappingService.retrieveRuleIdInfo(ruleId);
	}

	public CreateOrEditUmRuleMappingResult createOrEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList,
			SpiderUMRuleMapping spiderMaster, String persistType) {

		return umRuleMappingService.createOrEditRuleIdMapping(spiderList, spiderMaster, persistType);
	}

	public List findAllRuleStartingWith(String ruleId) {
		List variable = umRuleMappingService.findAllRuleStartingWith(ruleId, noOfRecordsForAutoComplete);
		return variable;
	}

	public List findAllRuleDescriptionStartingWith(String ruleIdDescription) {
		List variable = umRuleMappingService.findAllRuleDescriptionStartingWith(ruleIdDescription,
				noOfRecordsForAutoComplete);
		return variable;
	}

	public List findAllMappingByRuleId(String ruleId) {
		List variable = umRuleMappingService.findAllUmRuleMappingResult(ruleId);
		return variable;
	}

	public List findAllUmRuleMappingsDataByRuleId(String ruleId) {
		List variable = umRuleMappingService.findAllUmRuleMappingsDataByRuleId(ruleId);
		return variable;
	}

	public List findAllUnmappedRules() {
		List unmappedRuleList = umRuleMappingService.findAllUnmappedRules();// List<Mapping>
		return unmappedRuleList;
	}

	public SpiderUMRuleMapping findByRuleId(String ruleId) {
		SpiderUMRuleMapping variable = umRuleMappingService.findByRuleId(ruleId);
		return variable;
	}

	public List viewRuleDetails(SpiderUMRuleMapping spiderUMRuleMapping) {

		return umRuleMappingService.retrieveRuleInfo(spiderUMRuleMapping);
	}

	public int findMappingPresentOrNot(String ruleId, String eb03Value) {
		return umRuleMappingService.findMappingPresentOrNot(ruleId, eb03Value);
	}

	public int findEb03PresentOrNot(String eb03Value) {
		return umRuleMappingService.findEb03PresentOrNot(eb03Value);
	}

	public boolean deleteMapping(String ruleId, String eb03Value) {
		return umRuleMappingService.deleteMapping(ruleId, eb03Value);
	}

	public List createRuleIdAuditMapping(List<SpiderUMRuleMappingDataAudit> spiderList, String ruleId) {
		return umRuleMappingService.createRuleIdAuditMapping(spiderList, ruleId);
	}

	public CreateOrEditUmRuleMappingResult sendToTest(SpiderUMRuleMapping spiderUMRuleMapping) {

		return umRuleMappingService.sendToTest(spiderUMRuleMapping);
	}

	public List findExistingMappedRuleStartingWith(String ruleId) {
		List variable = umRuleMappingService.findExistingMappedRuleStartingWith(ruleId, noOfRecordsForAutoComplete);
		return variable;
	}

	public CreateOrEditUmRuleMappingResult notApplicable(SpiderUMRuleMapping spiderUMRuleMapping) {
		return umRuleMappingService.markAsNotApplicable(spiderUMRuleMapping);
	}

	public CreateOrEditUmRuleMappingResult saveAndsendToTest(SpiderUMRuleMapping spiderUMRuleMapping) {

		CreateOrEditUmRuleMappingResult savedResult = null;
		savedResult = umRuleMappingService.saveMapping(spiderUMRuleMapping);
		if (savedResult.isValidationSucess()) {
			savedResult = umRuleMappingService.sendToTest(savedResult.getSpiderUMRuleMapping());
		}
		return savedResult;
	}

	public CreateOrEditUmRuleMappingResult saveAndApprove(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult savedResult = null;
		savedResult = umRuleMappingService.saveMapping(spiderUMRuleMapping);
		if (savedResult.isValidationSucess()) {
			savedResult = umRuleMappingService.sendToProd(spiderUMRuleMapping);
		}
		return savedResult;
	}

	public CreateOrEditUmRuleMappingResult discardChanges(SpiderUMRuleMapping spiderUMRuleMapping) {

		return umRuleMappingService.discardChanges(spiderUMRuleMapping);
	}

	public List createEb03Exclusion(EB03Exclusion spiderEb03Exclusion) {
		return umRuleMappingService.createEb03Exclusion(spiderEb03Exclusion);
	}

	public List findEb03Exclusions() {
		List exclusions = umRuleMappingService.findEb03Exclusions();
		return exclusions;
	}

	
	
	public List validateEb03Exclusion(String eb03Value) {
		return umRuleMappingService.validateEb03Exclusion(eb03Value);
	}
	
	public boolean deleteEb03Exclusion(String eb03Value) {
		return umRuleMappingService.deleteEb03Exclusion(eb03Value);
	}

	
		
	public List createEb03DefaultMapping(EB03DefaultMapping spiderEb03Exclusion) {
		return umRuleMappingService.createEb03DefaultMapping(spiderEb03Exclusion);
	}
	
	
	public List validateEb03DefaultMapping(String eb03Value,String defaultValue) {
		return umRuleMappingService.validateEb03DefaultMapping(eb03Value,defaultValue);
	}
	

	public List findEb03DefaultMappings() {
		List exclusions = umRuleMappingService.findEb03DefaultMappings();
		return exclusions;
	}

	public boolean deleteEb03DefaultMapping(String eb03Value) {
		return umRuleMappingService.deleteEb03DefaultMapping(eb03Value);
	}

	public List getHippaSegmentsForAutocomplete(String ruleId,String searchType) {
		List variable = umRuleMappingService.getHippaSegmentsForAutocomplete(ruleId, noOfRecordsForAutoComplete,searchType);
		return variable;
	}
	
	public List findAllUmRuleMappingsByEb03(String eb03) {
		List variable = umRuleMappingService.findAllUmRuleMappingsByEb03(eb03);
		return variable;
	}
	
	public boolean deleteRuleMapping(String ruleId) {
		return umRuleMappingService.deleteRuleMapping(ruleId);
	}

	public List findAllUmRuleComments(String umRuleId) {
		return umRuleMappingService.findAllUmRuleComments(umRuleId);
	}
	
	public List findAllMappingByRuleIdForEdit(String ruleId) {
		List variable = umRuleMappingService.findAllUmRuleMappingResultForEdit(ruleId);
		return variable;
	}
	
	public List getEb03DefaultForAutocomplete(String searcheb03,String searchType) {
		List variable = umRuleMappingService.getEb03DefaultForAutocomplete(searcheb03, noOfRecordsForAutoComplete,searchType);
		return variable;
	}

	
	public void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderUMRuleMapping) {
		umRuleMappingService.createIgnoreUnmappedUMRule(spiderUMRuleMapping);
		
	}

	
	public String verifyUmRuleExistByRuleId(String ruleId) {
		
		return umRuleMappingService.verifyUmRuleExistByRuleId(ruleId);
	}
	
	public List findAuditDetailsByRuleId(String umRuleId) {
		List mappingDetails = umRuleMappingService.findAuditDetailsByRuleId(umRuleId);
		return mappingDetails;
	}

	public int findEb03DefaultMappingsPresentOrNot(String eb03) {
			int defaultmapping = umRuleMappingService.findEb03DefaultMappingsPresentOrNot(eb03);
			return defaultmapping;
		
	}

	public CreateOrEditUmRuleMappingResult markAsApplicable(SpiderUMRuleMapping spiderUMRuleMapping) 
	{
		CreateOrEditUmRuleMappingResult savedResult = null;
		savedResult = umRuleMappingService.saveMapping(spiderUMRuleMapping);
		if (savedResult.isValidationSucess()) {
			savedResult = umRuleMappingService.markAsApplicable(spiderUMRuleMapping);
		}
		return savedResult;
	}

	  
}
