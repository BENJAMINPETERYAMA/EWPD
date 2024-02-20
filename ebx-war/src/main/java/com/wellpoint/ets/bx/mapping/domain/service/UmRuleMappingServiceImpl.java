package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;
import com.wellpoint.ets.bx.mapping.repository.UmRuleMappingRepository;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

public class UmRuleMappingServiceImpl implements UmRuleMappingService {

	private UmRuleMappingRepository umRuleMappingRepository;
	private EbxStateTransitionsService ebxStateTransitionsService;

	public EbxStateTransitionsService getEbxStateTransitionsService() {
		return ebxStateTransitionsService;
	}

	public void setEbxStateTransitionsService(EbxStateTransitionsService ebxStateTransitionsService) {
		this.ebxStateTransitionsService = ebxStateTransitionsService;
	}

	public UmRuleMappingRepository getUmRuleMappingRepository() {
		return umRuleMappingRepository;
	}

	public void setUmRuleMappingRepository(UmRuleMappingRepository umRuleMappingRepository) {
		this.umRuleMappingRepository = umRuleMappingRepository;
	}

	public int isValidRuleIDStatus(String ruleId) {
		return umRuleMappingRepository.isValidRuleID(ruleId);
	}

	public List retrieveRuleIdInfo(String ruleId) {
		return umRuleMappingRepository.retrieveRuleIdInfo(ruleId);
	}

	public CreateOrEditUmRuleMappingResult createOrEditRuleIdMapping(List<SpiderUMRuleMappingData> spiderList,
			SpiderUMRuleMapping spiderMaster, String persistType) throws DomainException {
		CreateOrEditUmRuleMappingResult createOrEditMappingResult = new CreateOrEditUmRuleMappingResult();

		if (persistType.equalsIgnoreCase(WebConstants.PERSIST_TYPE_CREATE)) {
			umRuleMappingRepository.persistNewRuleIdMapping(spiderList, spiderMaster);
			createOrEditMappingResult.setSpiderList(spiderList);
			createOrEditMappingResult.setSpiderUMRuleMapping(spiderMaster);
		} else if (persistType.equalsIgnoreCase(WebConstants.PERSIST_TYPE_EDIT)) {
			umRuleMappingRepository.persistEditRuleIdMapping(spiderList, spiderMaster);
			createOrEditMappingResult.setSpiderList(spiderList);
			createOrEditMappingResult.setSpiderUMRuleMapping(spiderMaster);
		}

		return createOrEditMappingResult;
	}

	public List createRuleIdAuditMapping(List<SpiderUMRuleMappingDataAudit> spiderList, String ruleId)
			throws DomainException {
		List createOrEditMappingResult = new ArrayList<>();

		umRuleMappingRepository.persistAuditTable(spiderList, ruleId);

		return createOrEditMappingResult;
	}

	public List findAllRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete) {
		List allRules = umRuleMappingRepository.findAllRuleStartingWith(ruleId, noOfRecordsForAutoComplete);
		return allRules;
	}

	public List findAllRuleDescriptionStartingWith(String ruleIdDescription, int noOfRecordsForAutoComplete) {
		List allRules = umRuleMappingRepository.findAllRuleDescriptionStartingWith(ruleIdDescription,
				noOfRecordsForAutoComplete);
		return allRules;
	}

	public List findAllUmRuleMappingResult(String ruleId) {
		List allRules = umRuleMappingRepository.findAllUmRuleMappingsByRuleIdFromLocate(ruleId);
		return allRules;
	}

	public List findAllUmRuleMappingsDataByRuleId(String ruleId) {
		List allRules = umRuleMappingRepository.findAllUmRuleMappingsDataByRuleId(ruleId);
		return allRules;
	}

	public List findAllUnmappedRules() {
		List unmappedRuleList = umRuleMappingRepository.findAllUnmappedRules();
		return unmappedRuleList;
	}

	public SpiderUMRuleMapping findByRuleId(String ruleId) {
		SpiderUMRuleMapping allRules = umRuleMappingRepository.findByRuleId(ruleId);
		return allRules;
	}

	public List retrieveRuleInfo(SpiderUMRuleMapping spiderUMRuleMapping) {
		return umRuleMappingRepository.retrieveRuleInfo(spiderUMRuleMapping);
	}

	public int findMappingPresentOrNot(String ruleId, String eb03Value) {
		return umRuleMappingRepository.findMappingPresentOrNot(ruleId, eb03Value);
	}

	public boolean deleteMapping(String ruleId, String eb03Value) {
		return umRuleMappingRepository.deleteMapping(ruleId, eb03Value);
	}

	public int findEb03PresentOrNot(String eb03Value) {
		return umRuleMappingRepository.findEb03PresentOrNot(eb03Value);
	}

	public CreateOrEditUmRuleMappingResult sendToTest(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult savedResult = new CreateOrEditUmRuleMappingResult();

		int sendToTestStatus = ebxStateTransitionsService.transformToScheduledToTest(spiderUMRuleMapping);
		if (sendToTestStatus != 1) {
			savedResult.setStatus(sendToTestStatus);
		}

		return savedResult;
	}

	public CreateOrEditUmRuleMappingResult sendToProd(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult savedResult = new CreateOrEditUmRuleMappingResult();

		int sendToTestStatus = ebxStateTransitionsService.transformToScheduledToProd(spiderUMRuleMapping);
		if (sendToTestStatus != 1) {
			savedResult.setStatus(sendToTestStatus);
		}

		return savedResult;
	}

	public List findExistingMappedRuleStartingWith(String ruleId, int noOfRecordsForAutoComplete) {
		List allRules = umRuleMappingRepository.findExistingMappedRuleStartingWith(ruleId, noOfRecordsForAutoComplete);
		return allRules;
	}

	public CreateOrEditUmRuleMappingResult markAsNotApplicable(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult createOrEditUmRuleMappingResult = new CreateOrEditUmRuleMappingResult();

		String ruleId = spiderUMRuleMapping.getUmRuleId();

		spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_NOT_APPLICABLE);

		int notApplicable = ebxStateTransitionsService.notApplicable(spiderUMRuleMapping);

		createOrEditUmRuleMappingResult.setStatus(notApplicable);
		return createOrEditUmRuleMappingResult;

	}

	public CreateOrEditUmRuleMappingResult saveMapping(SpiderUMRuleMapping spiderUMRuleMapping) throws DomainException {

		boolean mappingResult = true;
		CreateOrEditUmRuleMappingResult createOrEditUmRuleMappingResult = new CreateOrEditUmRuleMappingResult();

		String ruleId = spiderUMRuleMapping.getUmRuleId();

		// if(createOrEditUmRuleMappingResult.isValidationSucess()){

		mappingResult = ebxStateTransitionsService.transformToEditableState(spiderUMRuleMapping);

		if (mappingResult) {

			createOrEditUmRuleMappingResult.setStatus(DomainConstants.SUCCESS_STATUS);
		}

		return createOrEditUmRuleMappingResult;

	}

	public CreateOrEditUmRuleMappingResult discardChanges(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult createOrEditUmRuleMappingResult = new CreateOrEditUmRuleMappingResult();

		SpiderUMRuleMapping previousPublishedMapping = new SpiderUMRuleMapping();
		int copyDeleteStatus = ebxStateTransitionsService.transformToPreviousPublishedMapping(spiderUMRuleMapping);
		if (copyDeleteStatus == 1) {
			String ruleId = spiderUMRuleMapping.getUmRuleId();
			previousPublishedMapping = umRuleMappingRepository.retrieveMapping(ruleId);
		}
		createOrEditUmRuleMappingResult.setStatus(copyDeleteStatus);
		createOrEditUmRuleMappingResult.setSpiderUMRuleMapping(previousPublishedMapping);
		String auditStatus = DomainConstants.AUDIT_STATUS_DISCARD_CHANGES;
		return createOrEditUmRuleMappingResult;
	}

	public List findEb03Exclusions() {
		List allExclusions = umRuleMappingRepository.findEb03Exclusions();
		return allExclusions;
	}

	public List createEb03Exclusion(EB03Exclusion eb03Exclusion) throws DomainException {
		return umRuleMappingRepository.persistEb03Exclusion(eb03Exclusion);
	}

	
	
	public List validateEb03Exclusion(String eb03Value) {
		return umRuleMappingRepository.validateEb03Exclusion(eb03Value);
	}
	
	public boolean deleteEb03Exclusion(String eb03Value) {
		return umRuleMappingRepository.deleteEb03Exclusion(eb03Value);
	}
	
	
	
	public List findEb03DefaultMappings() {
		List allExclusions = umRuleMappingRepository.findEb03DefaultMappings();
		return allExclusions;
	}

	public List createEb03DefaultMapping(EB03DefaultMapping eb03Exclusion) throws DomainException {
		return umRuleMappingRepository.persistEb03DefaultMapping(eb03Exclusion);
	}

	public List validateEb03DefaultMapping(String eb03Value,String defaultValue) {
		return umRuleMappingRepository.validateEb03DefaultMapping(eb03Value,defaultValue);
	}
	
	public boolean deleteEb03DefaultMapping(String eb03Value) {
		return umRuleMappingRepository.deleteEb03DefaultMapping(eb03Value);
	}
	
	public List getHippaSegmentsForAutocomplete(String searcheb03,int noRecords,String searchType) {
		return umRuleMappingRepository.getMatchingEb03FromDataDictionaryForAutoComplete(searcheb03,noRecords,searchType);
	}
	
	
	public List findAllUmRuleMappingsByEb03(String eb03) {
		List allRules = umRuleMappingRepository.findAllUmRuleMappingsByEb03(eb03);
		return allRules;
	}

	public boolean deleteRuleMapping(String ruleId) {
		return umRuleMappingRepository.deleteRuleMapping(ruleId);
	}

	public List findAllUmRuleComments(String umRuleId) {
		return umRuleMappingRepository.findAllUmRuleComments(umRuleId);
	}
	
	public List findAllUmRuleMappingResultForEdit(String ruleId) {
		List allRules = umRuleMappingRepository.findAllUmRuleMappingResultForEdit(ruleId);
		return allRules;
	}

	
	public List getEb03DefaultForAutocomplete(String searcheb03, int noOfRecordsForAutoComplete, String searchType) {
		return umRuleMappingRepository.getEb03DefaultForAutocomplete(searcheb03,noOfRecordsForAutoComplete,searchType);
	}

	
	public void createIgnoreUnmappedUMRule(SpiderUMRuleMapping spiderUMRuleMapping) {
		umRuleMappingRepository.createIgnoreUnmappedUMRule(spiderUMRuleMapping);
		
	}

	
	public String verifyUmRuleExistByRuleId(String ruleId) {
		// TODO Auto-generated method stub
		return umRuleMappingRepository.verifyUmRuleExistByRuleId(ruleId);
	}
	
	public List findAuditDetailsByRuleId(String umRuleId) {
		List allMapping = umRuleMappingRepository.findAuditDetailsByRuleId(umRuleId);
		return allMapping;
	}

	public int findEb03DefaultMappingsPresentOrNot(String eb03) {
	
			int defaultmapping = umRuleMappingRepository.findEb03DefaultMappingsPresentOrNot(eb03);
			return defaultmapping;
	}

	public CreateOrEditUmRuleMappingResult markAsApplicable(SpiderUMRuleMapping spiderUMRuleMapping) {
		CreateOrEditUmRuleMappingResult savedResult = new CreateOrEditUmRuleMappingResult();

		int sendToTestStatus = ebxStateTransitionsService.transformToApplicable(spiderUMRuleMapping);
		if (sendToTestStatus != 1) {
			savedResult.setStatus(sendToTestStatus);
		}

		return savedResult;
	}
	
}
