/*
 * MigrationAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyPricing;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariableInfo;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;
import com.wellpoint.wpd.common.migration.bo.BenefitLineNote;
import com.wellpoint.wpd.common.migration.bo.BenefitNote;
import com.wellpoint.wpd.common.migration.bo.CancelMigration;
import com.wellpoint.wpd.common.migration.bo.CancelSecondMigration;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContract;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContractForSave;
import com.wellpoint.wpd.common.migration.bo.DetachProduct;
import com.wellpoint.wpd.common.migration.bo.Item;
import com.wellpoint.wpd.common.migration.bo.LastAccessDateSegmentUpdate;
import com.wellpoint.wpd.common.migration.bo.LocalLineMapping;
import com.wellpoint.wpd.common.migration.bo.MigrateNotesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesAssnBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationMappedVariables;
import com.wellpoint.wpd.common.migration.bo.MigrationPossibleAnswer;
import com.wellpoint.wpd.common.migration.bo.MigrationPricing;
import com.wellpoint.wpd.common.migration.bo.MigrationProduct;
import com.wellpoint.wpd.common.migration.bo.MigrationProductAdministration;
import com.wellpoint.wpd.common.migration.bo.MigrationProductStructureMappingUpdate;
import com.wellpoint.wpd.common.migration.bo.MigrationReportGeneration;
import com.wellpoint.wpd.common.migration.bo.MigrationVariable;
import com.wellpoint.wpd.common.migration.bo.MigrationVariableInsert;
import com.wellpoint.wpd.common.migration.bo.NavigationInfo;
import com.wellpoint.wpd.common.migration.bo.ProductRule;
import com.wellpoint.wpd.common.migration.bo.RemoveProduct;
import com.wellpoint.wpd.common.migration.bo.Rule;
import com.wellpoint.wpd.common.migration.bo.RuleSequence;
import com.wellpoint.wpd.common.migration.bo.UpdateMigrationContractStatus;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationAdapterManager {

	public List getInfoForTree(int productSysId) throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productSysId", new Integer(productSysId));
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitComponentBenefit.class.getName(), "getInfoForTree",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;

	}

	
	
	public List getInfoForTree(int productSysId, int baseDateSegId) throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("productSysId", new Integer(productSysId));
		referenceValueSet.put("baseDateSegId", new Integer(baseDateSegId));
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitComponentBenefit.class.getName(), "getInfoForTreeForBaseContract",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;

	}
	/**
	 * 
	 * @param migrationContract
	 * @return list
	 * @throws SevereException
	 */
	public List retreiveDateSeg(MigrationContract migrationContract)
			throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("contractSysId", migrationContract
				.getMigrationSystemId());
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "getDateSegmentDetails",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;

	}

	/**
	 * 
	 * @param migrationContract
	 * @return list
	 * @throws SevereException
	 */
	public List retreiveDateSegment(MigrationContract migrationContract)
			throws SevereException {
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("systemId", new Integer(migrationContract
				.getLastAccessMigDateSegmentSysID()));
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "dateSegmentDetails",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * 
	 * @param migrationSysId
	 * @return list
	 * @throws SevereException
	 */
	public List getAllConfilictingMappings(int migrationDatesegmentSysId)
			throws SevereException {
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("migrationDatesegmentSysId", new Integer(migrationDatesegmentSysId));
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				LocalLineMapping.class.getName(), "getLineConflictDetails",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * 
	 * @param migrationDomainInfo
	 * @param audit
	 * @throws SevereException
	 */
	public void saveDomainInfo(MigrationDomainInfo migrationDomainInfo,
			Audit audit) throws SevereException {
		AdapterUtil.performInsert(migrationDomainInfo, audit.getUser());
	}

	/**
	 * 
	 * @param migrationDomainInfo
	 * @param audit
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void saveDomainInfo(MigrationDomainInfo migrationDomainInfo,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(migrationDomainInfo, audit.getUser(),
				adapterServicesAccess);
	}

	/**
	 * 
	 * @param migDomain
	 * @param audit
	 * @throws SevereException
	 */
	public void removeDomain(MigrationDomainInfo migDomain, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performRemove(migDomain, audit.getUser(),
				adapterServicesAccess);
	}

	/**
	 * Function to remove a Migration Information.
	 * 
	 * @param cancelMigration
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean cancelMigration(CancelMigration cancelMigration, Audit audit)
			throws SevereException {
		AdapterUtil.performRemove(cancelMigration, audit.getUser());
		return true;
	}

	public boolean cancelSecMigration(CancelSecondMigration cancelSecMigration,
			Audit audit) throws SevereException {
		AdapterUtil.performRemove(cancelSecMigration, audit.getUser());
		return true;
	}
	
	/**
	 * Function to remove a Migration Information.
	 * 
	 * @param cancelMigration
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean detachProduct(DetachProduct detachProduct, Audit audit)
			throws SevereException {
		AdapterUtil.performRemove(detachProduct, audit.getUser());
		AdapterUtil.performUpdate(detachProduct,audit.getUser());
		return true;
	}

	/**
	 * 
	 * @param dateSegmentSysId
	 * @return list
	 * @throws SevereException
	 */
	public List getContractEffectiveDate(int dateSegmentSysId)
			throws SevereException {
		HashMap map = new HashMap();
		map.put("systemId", new Integer(dateSegmentSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "contractStartDate", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public List getContractRenewDate(String contractId) throws SevereException {
		HashMap map = new HashMap();
		map.put("contractSysId", contractId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "contractRenewDate", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param contractID
	 * @return
	 * @throws SevereException
	 */
	public MigrationDateSegment maxEffectiveOfMigratingContract(
			String contractID) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("systemId", new Integer(0));
		criteriaMap.put(WebConstants.CONTRACT_ID, contractID);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(),
				WebConstants.MAX_EFFECTIVE_OF_MIGRATING_CONTRACT, criteriaMap);
		List resultList = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		if (null != resultList && !resultList.isEmpty()) {
			return (MigrationDateSegment) resultList.get(0);
		}
		return null;
	}

	/**
	 * 
	 * @param dateSegmentSysId
	 * @return list
	 * @throws SevereException
	 */
	public List getContractStartANDEndDate(int dateSegmentSysId)
			throws SevereException {
		HashMap map = new HashMap();
		map.put("systemId", new Integer(dateSegmentSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(),
				"contractStartANDEndDate", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param productSysId
	 * @return list
	 * @throws SevereException
	 */
	public List getAssociatedRules(int productSysId) throws SevereException {
		HashMap map = new HashMap();
		map.put("productSysId", new Integer(productSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductRule.class.getName(), "getProductRules", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();

	}

	/**
	 * 
	 * @param productSysId
	 * @return list
	 * @throws SevereException
	 */
	public List getProductExclusionRules(int productSysId)
			throws SevereException {
		HashMap map = new HashMap();
		map.put("productSysId", new Integer(productSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				Rule.class.getName(), "getExclusionRules", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param ruleId
	 * @return list
	 * @throws SevereException
	 */
	public List getRuleSequences(String ruleId) throws SevereException {
		HashMap map = new HashMap();
		map.put("ruleId", ruleId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				RuleSequence.class.getName(), "getRuleSequences", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param ruleId
	 * @return list
	 * @throws SevereException
	 */
	public List getChildRules(String ruleId) throws SevereException {
		HashMap map = new HashMap();
		map.put("groupRuleId", ruleId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				Rule.class.getName(), "getChildRules", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param productSysId
	 * @param mappedProductRules
	 * @return list
	 * @throws SevereException
	 */
	public List getUnmappedRules(int productSysId, List mappedProductRules)
			throws SevereException {
		if (mappedProductRules == null || mappedProductRules.size() == 0) {
			return getAssociatedRules(productSysId);
		}
		List prodRuleSysIds = new ArrayList(mappedProductRules.size());
		for (Iterator iterator = mappedProductRules.iterator(); iterator
				.hasNext();) {
			ProductRule productRule = (ProductRule) iterator.next();
			prodRuleSysIds.add(new Integer(productRule.getProductRuleSysId()));
		}
		HashMap map = new HashMap();
		map.put("productSysId", new Integer(productSysId));
		map.put("productRuleSysIdList", prodRuleSysIds);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ProductRule.class.getName(), "getUnmappedProductRules", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();

	}

	/**
	 * Function to update Migration completion status.
	 * 
	 * @param status
	 * @param user
	 * @throws SevereException
	 */
	public void updateMigrationStatus(UpdateMigrationContractStatus status,
			String user) throws SevereException {
		AdapterUtil.performUpdate(status, user);
	}

	/**
	 * 
	 * @param status
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void updateMigrationStatus(UpdateMigrationContractStatus status,
			String user, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		AdapterUtil.performUpdate(status, user, adapterServicesAccess);
	}

	/**
	 * 
	 * @param migDS
	 * @param audit
	 * @throws SevereException
	 */
	public void saveMigGeneralInfo(MigrationDateSegment migDS, Audit audit)
			throws SevereException {
		AdapterUtil.performUpdate(migDS, audit.getUser());
	}

	public MigrationDateSegment getMigGeneralInfo(
			MigrationDateSegment migDateSegment) throws SevereException {
		return (MigrationDateSegment) AdapterUtil
				.performRetrieve(migDateSegment);

	}

	/**
	 * 
	 * @param contractSysId
	 * @return searchResults
	 * @throws SevereException
	 */
	public SearchResults getMigDomainInfo(int contractSysId)
			throws SevereException {
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("contractSysId", new Integer(contractSysId));
		int maxResultSize = 999999;
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						BusinessConstants.MIGRATION_DOMAIN_BO,
						BusinessConstants.MIG_DOMAIN_RETREIVE, refValSet,
						maxResultSize);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults;
	}

	/**
	 * 
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List getBenefitLineList(HashMap map) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationVariable.class.getName(), "getBenefitLineData", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param migrationVariable
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void saveVariableMapping(MigrationVariableInsert migrationVariable,
			String userId, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(migrationVariable,
				BusinessConstants.TESTUSER, adapterServicesAccess);
	}

	/**
	 * Function to retrieve a Migration Pricing Information.
	 * 
	 * @param map
	 * @return
	 * @throws SevereException
	 */
	public List getPricingInfoList(HashMap map) throws SevereException {

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationPricing.class.getName(), "locateMigrationPricingInfo",
				map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * Function to persist a Migration Pricing Information.
	 * 
	 * @param migrationPricing
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistMigrationPricingInfo(
			MigrationPricing migrationPricing, Audit audit,AdapterServicesAccess serviceAccessEWPDB)
			throws SevereException,AdapterException {
		AdapterUtil.performInsert(migrationPricing, audit.getUser(),serviceAccessEWPDB);
		return true;
	}

	/**
	 * Function to remove a Migration Pricing Information.
	 * 
	 * @param migrationPricing
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteMigrationPricingInfo(
			MigrationPricing migrationPricing, Audit audit)
			throws SevereException {
		AdapterUtil.performRemove(migrationPricing, audit.getUser());
		return true;
	}

	/**
	 * Function to retrieve a Migration Product Information.
	 * 
	 * @param map
	 * @param
	 * @param
	 * @return list
	 * @throws SevereException
	 */
	public List getProductInfo(HashMap map) throws SevereException {

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProduct.class.getName(), "locateMigrationProductInfo",
				map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * Function to persist a Migration Product Information.
	 * 
	 * @param migrationProduct
	 * @param audit
	 * @param
	 * @return
	 * @throws SevereException
	 */
	public boolean persistMigrationProductInfo(
			MigrationProduct migrationProduct, Audit audit)
			throws SevereException {
		AdapterUtil.performInsert(migrationProduct, audit.getUser());
		return true;
	}

	/**
	 * Function to Update a Migration Product Information.
	 * 
	 * @param migrationProduct
	 * @param audit
	 * @param
	 * @return
	 * @throws SevereException
	 */
	public boolean updateMigrationProductInfo(
			MigrationProduct migrationProduct, Audit audit)
			throws SevereException {
		AdapterUtil.performUpdate(migrationProduct, audit.getUser());
		return true;
	}

	/**
	 * Function to Update a Migration Product Structure lock.
	 * 
	 * @param updateBO
	 * @param user
	 * @throws SevereException
	 */
	public void updateMigrationProductStructureLockStatus(
			MigrationProductStructureMappingUpdate updateBO, String user)
			throws SevereException {
		AdapterUtil.performUpdate(updateBO, user);
	}

	/**
	 * Function to retrieve Unmapped Variables for Report Generation.
	 * 
	 * @param map
	 * @return list
	 * @throws SevereException,
	 *             AdapterException
	 */
	public List locateUnmappedVariables(HashMap map) throws SevereException {

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationReportGeneration.class.getName(),
				"locateUnmappedVariables", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * Function to retrieve Mapped Variables for Report Generation.
	 * 
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List locateMappedVariables(HashMap map) throws SevereException {

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationMappedVariables.class.getName(),
				"locateMappedVariables", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * Function to insert Page Information.
	 * 
	 * @param navigationInfo
	 * @param userId
	 * @return list
	 * @throws SevereException
	 */
	public boolean persistPageInformation(NavigationInfo navigationInfo,
			String userId) throws SevereException {
		AdapterUtil.performInsert(navigationInfo, userId);
		return true;
	}

	/**
	 * Function to update Page Information.
	 * 
	 * @param navigationInfo
	 * @param userId
	 * @return list
	 * @throws SevereException
	 */
	public boolean updatePageInformation(NavigationInfo navigationInfo,
			String userId) throws SevereException {
		AdapterUtil.performUpdate(navigationInfo, userId);
		return true;
	}

	/**
	 * Function to update last access date segment information.
	 * 
	 * @param accessDateSegmentUpdate
	 * @param userId
	 * @return void
	 * @throws SevereException
	 */
	public void updateLastAccessDateSegment(
			LastAccessDateSegmentUpdate accessDateSegmentUpdate, String userId)
			throws SevereException {
		AdapterUtil.performUpdate(accessDateSegmentUpdate, userId);
	}

	/**
	 * This method returns duplicate PricingInfo
	 * 
	 * @param migrationPricing
	 * @return list
	 * @throws SevereException
	 */
	public List getDuplicatePricingInfo(MigrationPricing migrationPricing)
			throws SevereException {

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("contractDateSegmentSysId", new Integer(
				migrationPricing.getContractDateSegmentSysId()));
		referenceValueSet.put("coverage", migrationPricing.getCoverage());
		referenceValueSet.put("pricing", migrationPricing.getPricing());
		referenceValueSet.put("network", migrationPricing.getNetwork());

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationPricing.class.getName(), "getDuplicatePricingInfo",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * this method gets the rule id/type list migration contarct step 6
	 * 
	 * @param queryName
	 * @param ruleType
	 * @param dateSegmentKey
	 * @return list
	 * @throws SevereException
	 */
	public List getRulesList(String queryName, String ruleType,
			int dateSegmentKey) throws SevereException {
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		String value = "N";
		if (queryName.equals("migrationRuleID")) {
			value = "Y";
			referenceValueSet
					.put("dateSegmentKey", new Integer(dateSegmentKey));
		}
		referenceValueSet.put("flag", value);
		if (queryName.equals("ruleID") || queryName.equals("migrationRuleID")) {
			referenceValueSet.put("ruleType", ruleType);
		}
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationContractRulesBO.class.getName(), queryName,
				referenceValueSet);

		SearchResults searchResults = AdapterUtil
				.performSearch(adapterSearchCriteria);

		return searchResults.getSearchResults();
	}

	/**
	 * creates the migration contract rule
	 * 
	 * @param migrationContractRulesBO
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void createProductRules(
			MigrationContractRulesBO migrationContractRulesBO, String userId,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(migrationContractRulesBO, userId,
				adapterServicesAccess);
	}

	/**
	 * updates the comments for migration contract rule
	 * 
	 * @param migrationContractRulesAssnBO
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void updateContractRules(
			MigrationContractRulesAssnBO migrationContractRulesAssnBO,
			String userId, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performUpdate(migrationContractRulesAssnBO, userId,
				adapterServicesAccess);
	}

	/**
	 * updates the contract rule
	 * 
	 * @param migrationContractRulesBO
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void updateContractRule(
			MigrationContractRulesBO migrationContractRulesBO, String userId,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performUpdate(migrationContractRulesBO, userId,
				adapterServicesAccess);
	}

	/**
	 * this method gets the rule id/type list
	 * 
	 * @param ruleType
	 * @return
	 * @throws SevereException
	 */
	public MigrationContract getContractRulesList(
			MigrationContract migrationContract, String ruleType)
			throws SevereException {
		List productRulesList;
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("contractDateSegmentSysId", new Integer(
				((MigrationDateSegment) migrationContract
						.getMigrationDateSegments().get(0)).getSystemId()));
		referenceValueSet.put("ruleType", ruleType);
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationContractRulesBO.class.getName(),
				"migrationContractAllRules", referenceValueSet);

		SearchResults searchResults = AdapterUtil
				.performSearch(adapterSearchCriteria);

		productRulesList = searchResults.getSearchResults();
		migrationContract
				.setDenialAndExclusionList(productRulesList != null ? productRulesList
						: null);
		return migrationContract;
	}

	/**
	 * 
	 * @param migrationContract
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void persistMigrationContractInfo(
			MigrationContract migrationContract, Audit audit,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		AdapterUtil.performInsert(migrationContract, audit.getUser(),
				adapterServicesAccess);
	}

	/**
	 * 
	 * @param migrationContract
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean updateMigrationContractInfo(
			MigrationContract migrationContract, Audit audit)
			throws SevereException {
		AdapterUtil.performUpdate(migrationContract, audit.getUser());
		return true;
	}

	/**
	 * 
	 * @param contractNo
	 * @param migrationDateSegment
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean persistMigrationDateSegmentInfo(String contractNo,
			MigrationDateSegment migrationDateSegment, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(migrationDateSegment, audit.getUser(),
				adapterServicesAccess);
		if (migrationDateSegment.getLegacyStartDate() != null) {
			if (migrationDateSegment.getEffectiveDate() != migrationDateSegment
					.getLegacyStartDate()) {
				migrationDateSegment.setEffectiveDate(migrationDateSegment
						.getLegacyStartDate());
			}
		}
		persistSubTables(contractNo, migrationDateSegment, audit,
				adapterServicesAccess);
		return true;
	}

	/**
	 * 
	 * @param contractNo
	 * @param migrationDateSegment
	 * @param audit
	 * @param adapterServicesAccess
	 * @throws ServiceException
	 * @throws SevereException
	 */
	private void persistSubTables(String contractNo,
			MigrationDateSegment migrationDateSegment, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws ServiceException, SevereException {
		LegacyBuilder builder = new LegacyBuilder();
		LegacyContract legacyContract = new CP2000Contract();
		legacyContract.setContractId(contractNo);
		legacyContract.setSystemCP2000();
		legacyContract.setStartDate(migrationDateSegment.getLegacyStartDate());
		legacyContract = builder.retrieveDateSegment(legacyContract,
				LegacyBuilder.OPT_DS_DETAIL_INFO, "Transferred to Production");
		persistLegacyVariable(migrationDateSegment.getSystemId(),
				legacyContract.getCodedVariables(), audit,
				adapterServicesAccess);
		persistLegacyPricing(migrationDateSegment.getSystemId(), legacyContract
				.getPricing(), audit, adapterServicesAccess);
	}

	/**
	 * 
	 * @param dateSegmentId
	 * @param legacyVariable
	 * @param audit
	 * @param adapterServicesAccess
	 * @throws ServiceException
	 * @throws SevereException
	 */
	private void persistLegacyVariable(int dateSegmentId, List legacyVariable,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws ServiceException, SevereException {
		if (legacyVariable != null) {
			Iterator iterator = legacyVariable.iterator();
			while (iterator.hasNext()) {
				LegacyVariable variable = (LegacyVariable) iterator.next();
				LegacyVariableInfo legacyVariableInfo = new LegacyVariableInfo();
				legacyVariableInfo.setSysId(String.valueOf(dateSegmentId));
				legacyVariableInfo.setVariableId(variable.getVariableId());
				if (variable.getCodedValue() != null) {
					if (variable.getCodedValue().equalsIgnoreCase("Y"))
						variable.setCodedValue(BusinessConstants.SUBTABLE_YES);
					else if (variable.getCodedValue().equalsIgnoreCase("N"))
						variable.setCodedValue(BusinessConstants.SUBTABLE_NO);
				}
				legacyVariableInfo.setCodedValue(variable.getCodedValue());
				if (!StringUtil.isEmpty(variable.getVarNoteFlag())) {
					legacyVariableInfo.setVarNotesFlag(variable
							.getVarNoteFlag());
				} else {
					legacyVariableInfo.setVarNotesFlag("N");
				}

				AdapterUtil.performInsert(legacyVariableInfo, audit.getUser(),
						adapterServicesAccess);
			}
		}
	}

	/**
	 * 
	 * @param dateSegmentId
	 * @param legacyPricing
	 * @param audit
	 * @param adapterServicesAccess
	 * @throws ServiceException
	 * @throws SevereException
	 */
	private void persistLegacyPricing(int dateSegmentId, List legacyPricing,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws ServiceException, SevereException {
		if (legacyPricing != null) {
			MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
			Iterator iterator = legacyPricing.iterator();
			while (iterator.hasNext()) {
				LegacyPricing pricing = (LegacyPricing) iterator.next();
				if (builder.validateItemField(1695, pricing.getCoverage())
						&& builder
								.validateItemField(1749, pricing.getNetwork())
						&& builder
								.validateItemField(1696, pricing.getPricing())) {
					MigrationPricing migrationPricing = new MigrationPricing();
					migrationPricing.setContractDateSegmentSysId(dateSegmentId);
					migrationPricing.setCoverage(pricing.getCoverage());
					migrationPricing.setCreatedTimestamp(pricing
							.getCreatedTimestamp());
					migrationPricing.setNetwork(pricing.getNetwork());
					migrationPricing.setPricing(pricing.getPricing());
					migrationPricing.setCreatedUser(pricing.getCreatedUser());
					migrationPricing.setLastUpdatedTimestamp(pricing
							.getLastUpdatedTimestamp());
					migrationPricing.setLastUpdatedUser(pricing
							.getLastUpdatedUser());
					AdapterUtil.performInsert(migrationPricing,
							audit.getUser(), adapterServicesAccess);
				}
			}
		}
	}

	/**
	 * 
	 * @param legacyContract
	 * @return list
	 * @throws SevereException
	 */
	public List retrieveMigrationContract(LegacyContract legacyContract)
			throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("contractId", legacyContract.getContractId());
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationContract.class.getName(), "getMigrationContract",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;
	}

	/**
	 * 
	 * @param contractId
	 * @return list
	 * @throws SevereException
	 */
	public List retrieveMigrationContract(String contractId)
			throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("contractId", contractId);
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationContract.class.getName(), "migrationContractSysID",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;
	}

	/**
	 * 
	 * @param contractId
	 * @return list
	 * @throws SevereException
	 */
	public List getMigrationContracts(String contractId) throws SevereException {
//		List migrationList = new ArrayList();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("contractId", contractId);
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationContract.class.getName(), "getMigrationContract",
				referenceValueSet);
		List migrationList = AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
		return migrationList;
	}

	/**
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List retrieveConfirmDataForInsert(HashMap map)
			throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ConfirmMigrationContract.class.getName(),
				"getConfirmDataForInsert", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();

	}

	/**
	 * 
	 * @param confirmMigrationContractForSave
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void insertConfirmedMigrationData(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			String user, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(confirmMigrationContractForSave,
				BusinessConstants.TESTUSER, adapterServicesAccess);

	}

	/**
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List retrieveConfirmDataForUpdate(HashMap map)
			throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ConfirmMigrationContract.class.getName(),
				"getConfirmDataForUpdate", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * 
	 * @param confirmMigrationContractForSave
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void updateConfirmedMigrationData(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			String user, AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performUpdate(confirmMigrationContractForSave,
				BusinessConstants.TESTUSER, adapterServicesAccess);

	}

	/**
	 * This method returns step and datesegment id having step <8
	 * 
	 * @param migrationContractID
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isAllDateSegmentThroughStep8(String migrationContractID)
			throws SevereException {

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("migrationContractID", migrationContractID);

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NavigationInfo.class.getName(), "stepChekForMigContConfirm",
				referenceValueSet);
		if (AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResultCount() > 0)
			return false;
		return true;
	}

	/**
	 * This method returns datesegment id of not completed pricing info
	 * 
	 * @param migrationContractID
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isAllDateSegmentContainPricing(String migrationContractID)
			throws SevereException {

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("migrationContractID", migrationContractID);

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NavigationInfo.class.getName(), "pricingChekForMigContConfirm",
				referenceValueSet);
		if (AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResultCount() > 0)
			return false;
		return true;
	}

	/**
	 * This method returns step and datesegment id having step <8
	 * 
	 * @param migrationContractID
	 * @return List
	 * @throws SevereException
	 */
	public List getAllDateSegmentNotThroughStep8(String migrationContractID)
			throws SevereException {

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("migrationContractID", migrationContractID);

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NavigationInfo.class.getName(), "stepChekForMigContConfirm",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * This method returns datesegment id of not completed pricing info
	 * 
	 * @param migrationContractID
	 * @return List
	 * @throws SevereException
	 */
	public List getAllDateSegmentNotContainPricing(String migrationContractID)
			throws SevereException {

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("migrationContractID", migrationContractID);

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NavigationInfo.class.getName(), "pricingChekForMigContConfirm",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * 
	 * @param migrationContractSysID
	 * @param ewpdContractSysID
	 * @param ewpdContractComment
	 * @param audit
	 * @param adapterServicesAccess
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List confirmedMigrationData(String migrationContractSysID,
			int ewpdContractSysID, String ewpdContractComment, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("contractSysId", new Integer(Integer
				.parseInt(migrationContractSysID)));
		referenceValueSet.put("systemId", new Integer(ewpdContractSysID));
		referenceValueSet.put("brandName", ewpdContractComment);
		referenceValueSet.put("lastUpdatedUser", audit.getUser());
		referenceValueSet.put("lastUpdatedTimeStamp", audit.getTime());

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "copyMigratedContract",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria,
				adapterServicesAccess).getSearchResults();
	}

	public List retrieveDateSegmentDetails(HashMap map) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "getDateSegmentDetails",
				map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();

	}

	/**
	 * @param migrationVariableForDelete
	 * @param user
	 * @throws SevereException
	 */
	public void deleteVariableMappingList(
			MigrationVariableInsert migrationVariableForDelete, String user)
			throws SevereException {
		AdapterUtil.performRemove(migrationVariableForDelete, user);

	}

	/**
	 * 
	 * @param catalogId
	 * @param itemId
	 * @return list
	 * @throws SevereException
	 */
	public List validateItemField(int catalogId, String itemId)
			throws SevereException {
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("catalogId", new Integer(catalogId));
		referenceValueSet.put("itemId", itemId);

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(Item.class
				.getName(), "getItem", referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();

	}

	/**
	 * 
	 * @param productSysId
	 * @param sourceSystemName
	 * @param structureName
	 * @return list
	 * @throws SevereException
	 */
	public List retrieveProductInfo(int productSysId, String sourceSystemName,
			String structureName) throws SevereException {
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("productSysId", new Integer(productSysId));
		referenceValueSet.put("sourceSystemName", sourceSystemName);
		referenceValueSet.put("structureName", structureName);
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProduct.class.getName(), "retrieveProductInfo",
				referenceValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();
	}

	/**
	 * 
	 * @param migratedProdStructureMappingSysID
	 * @return
	 * @throws SevereException
	 */
	public boolean isMigratedProductStructureLocked(
			int migratedProdStructureMappingSysID) throws SevereException {
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("migratedProdStructureMappingSysID", new Integer(
				migratedProdStructureMappingSysID));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProductStructureMappingUpdate.class.getName(),
				"prodStructureLockStatus", referenceValueSet);

		if (AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResultCount() == 1) {
			return true;
		}
		return false;
	}

	public List isCorporatePlanCodeNull(int migrationSysId)
			throws SevereException {
		HashMap refValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		refValueSet.put("contractSysId", new Integer(migrationSysId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "searchNullPlanCode",
				refValueSet);
		return AdapterUtil.performSearch(adapterSearchCriteria)
				.getSearchResults();

	}

	public List migrationProductRulesUMAndPNRCoded(String migContractId)
			throws SevereException {
		HashMap refValSet = new HashMap();
		refValSet.put("migContractId", migContractId);
		refValSet.put("productRuleSysId", new Integer(0));//to make key value
														  // not null only
		refValSet.put("productSysId", new Integer(0));//to make key value not
													  // null only

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BusinessConstants.RULE_LOCATE_RESULT,
				"migrationContractRulesValidation", refValSet);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	
	/**
	 * @param confirmMigrationContractForSave
	 * @param user
	 * @throws SevereException
	 */
	public void deleteVariableMappingMasterList(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			String user) throws SevereException {
		AdapterUtil.performRemove(confirmMigrationContractForSave, user);

	}

	/**
	 * Method to remove the mappings of a product modified.
	 * 
	 * @param migrationProduct
	 * @param user
	 * @throws SevereException
	 */
	public void removeContractMapping(MigrationProduct migrationProduct,
			String user) throws SevereException {
		AdapterUtil.performRemove(migrationProduct, user);

	}

	/**
	 * Method to remove the rules associated to a product modified.
	 * 
	 * @param migrationContractRulesAssnBO
	 * @param user
	 * @throws SevereException
	 */
	public void removeDateSegments(
			MigrationContractRulesAssnBO migrationContractRulesAssnBO,
			String user) throws SevereException {
		AdapterUtil.performRemove(migrationContractRulesAssnBO, user);
	}

	/**
	 * @param map
	 * @return
	 * @throws SevereException
	 */
	public List getConflictRecordList(HashMap map) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationVariable.class.getName(),
				"getConflictingBenefitLineId", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * @param confirmMigrationContractForSave
	 * @param userId
	 * @throws SevereException
	 */
	public void deleteFromTempContractMapping(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			String userId) throws SevereException {
		AdapterUtil.performRemove(confirmMigrationContractForSave, userId);

	}

	/**
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List retrievePossibleAnswer(HashMap map) throws SevereException {
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationPossibleAnswer.class.getName(),
				"possibleAnswerSearch", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * To update the overridded values of the administration option questions
	 * 
	 * @param productAdministration
	 * @param userId
	 * @throws SevereException
	 */
	public boolean persistProductAdministrationValues(
			MigrationProductAdministration productAdministration, String userId)
			throws SevereException {
		//		AdapterUtil.performInsert(productAdministration, userId);
		AdapterUtil.performUpdate(productAdministration, userId);
		return true;
	}

	/**
	 * @param migDS
	 * @param user
	 * @throws SevereException
	 */
	public List getProductAttachedToBaseContract(MigrationDateSegment migDS,
			String user) throws SevereException {
		Map map = new HashMap();
		map.put("legExpiryDate", WPDStringUtil.convertDateToString(migDS
				.getExpiryDate()));
		map.put("legEffectiveDate", WPDStringUtil.convertDateToString(migDS
				.getEffectiveDate()));
		map.put("contractSysId", new Integer(migDS.getBaseContractSysId()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProduct.class.getName(),
				"retrieveProductAttachedToBaseContract", (HashMap) map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();

	}

	/**
	 * @param migDS
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public List getMappedProductID(MigrationDateSegment migDS, String user)
			throws SevereException {
		Map map = new HashMap();
		map.put("mappingSysID", new Integer(migDS.getMappingSysId()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProduct.class.getName(), "retrieveEwpdProductSysId",
				(HashMap) map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	public String getPlanRenewalType(String type, int entitySysId)
			throws SevereException {
		HashMap params = new HashMap();
		params.put("entitySysID", String.valueOf(entitySysId));
		params.put("entityType", type);
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationPossibleAnswer.class
						.getName(), "getPlanRenewalType", params));
		String planRenewalType = null;
		if (searchResults.getSearchResultCount() > 0) {
			MigrationPossibleAnswer answer = (MigrationPossibleAnswer) searchResults
					.getSearchResults().get(0);
			planRenewalType = answer.getPossibleAnswerDesc();
		}
		return planRenewalType;
	}
	public String getPlanRenewalTypeForProduct(String type, int entitySysId)
			throws SevereException {
			HashMap params = new HashMap();
			params.put("entitySysID", String.valueOf(entitySysId));
			params.put("entityType", type);
			SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
					.getAdapterSearchCriteria(MigrationPossibleAnswer.class
							.getName(), "getPlanRenewalTypeForProduct", params));
			String planRenewalType = null;
			if (searchResults.getSearchResultCount() > 0) {
				MigrationPossibleAnswer answer = (MigrationPossibleAnswer) searchResults
						.getSearchResults().get(0);
				planRenewalType = answer.getPossibleAnswerDesc();
			}
			return planRenewalType;
		}
	

	/**
	 * @param migrateNotesBO
	 * @param audit
	 */
	//public void persistMigrateNotes(MigrateNotesBO migrateNotesBO,Audit
	// audit) throws SevereException {
	//AdapterUtil.performInsert(migrateNotesBO,audit.getUser());
	//AdapterUtil.performUpdate(migrateNotesBO,audit.getUser());
	//}
	/**
	 * @param migrateNotesBO
	 * @param audit
	 */
	public List searchBenefitsForMigration(MigrateNotesBO migrateNotesBO,
			Audit audit) throws SevereException {
		HashMap params = new HashMap();
		params.put("dateSegmentId", migrateNotesBO.getMigratedDateSegmentId());
		params.put("benefitComponentId", String.valueOf(migrateNotesBO
				.getBenefitComponentId()));
		params.put("contractSystemId", String.valueOf(migrateNotesBO
				.getContractSystemId()));
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrateNotesBO.class.getName(),
						"getBenefitList", params));
		return searchResults.getSearchResults();
	}
	
	/**
	 * @param migrateNotesBO
	 * @param audit
	 */
	public List searchBenefitsForMigrationWithBaseDateSegment(MigrateNotesBO migrateNotesBO,
			Audit audit) throws SevereException {
		HashMap params = new HashMap();
		params.put("dateSegmentId", migrateNotesBO.getMigratedDateSegmentId());
		params.put("benefitComponentId", String.valueOf(migrateNotesBO
				.getBenefitComponentId()));
		params.put("baseDateSegId", new Integer(migrateNotesBO
				.getBaseDateSegId()));
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrateNotesBO.class.getName(),
						"getBenefitListWithBaseDateSeg", params));
		return searchResults.getSearchResults();
	}
	/**
	 * @param benefitComponentNote
	 * @param audit
	 * @return
	 */
	public BenefitComponentNote searchBenefitComponentForMigration(
			BenefitComponentNote benefitComponentNote, Audit audit)
			throws SevereException {
		benefitComponentNote = (BenefitComponentNote) AdapterUtil
				.performRetrieve(benefitComponentNote);
		return benefitComponentNote;

	}

	/**
	 * @param benefitLineNote
	 * @param user
	 * @param serviceAccessEWPDB
	 * @throws SevereException
	 */
	public void saveMigrateNotesInfo(BenefitLineNote benefitLineNote,
			String user, AdapterServicesAccess serviceAccessEWPDB)
			throws SevereException {
		AdapterUtil.performInsert(benefitLineNote, BusinessConstants.TESTUSER,
				serviceAccessEWPDB);

	}

	/**
	 * @param benefitLineNote
	 * @param userId
	 * @throws SevereException
	 */
	public void deleteMigrateNotesInfo(BenefitLineNote benefitLineNote,
			String userId) throws SevereException {
		AdapterUtil.performRemove(benefitLineNote, userId);

	}

	/**
	 * @param benefitLineNote
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void deleteMigrateNotesInfo(BenefitLineNote benefitLineNote,
			String userId, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		AdapterUtil.performRemove(benefitLineNote, userId,
				adapterServicesAccess);

	}

	/**
	 * @param benefitComponentNote
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void saveBenefitComponentNotes(
			BenefitComponentNote benefitComponentNote, String userId,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(benefitComponentNote, userId,
				adapterServicesAccess);
	}

	/**
	 * @param benefitComponentNote
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void deleteBenefitComponentNotes(
			BenefitComponentNote benefitComponentNote, String userId,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {

		AdapterUtil.performRemove(benefitComponentNote, userId,
				adapterServicesAccess);
	}

	/**
	 * @param benefitNote
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void saveBenefitNotes(BenefitNote benefitNote, String userId,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		AdapterUtil.performInsert(benefitNote, userId, adapterServicesAccess);
	}

	/**
	 * @param benefitNote
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void deleteBenefitNotes(BenefitNote benefitNote, String userId,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		AdapterUtil.performRemove(benefitNote, userId, adapterServicesAccess);
	}

	/**
	 * @param migDS
	 * @param userId
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void saveContractNotes(MigrationDateSegment migDS, String userId,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		AdapterUtil.performUpdate(migDS, userId, adapterServicesAccess);	
	}

	/**
	 * @param dateSegment
	 * @param audit
	 * @return
	 */
	public List searchContractForMigration(MigrationDateSegment dateSegment,
			Audit audit) throws SevereException {
		HashMap params = new HashMap();
		params.put("systemId", String.valueOf(dateSegment.getSystemId()));
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationDateSegment.class.getName(),
						"getDateSegmentNotesFlag", params));
		return searchResults.getSearchResults();
	}
	/**
	 * This method calls a procedure which will insert values
	 *  in to MGRTD_CNTRCT_MSTR and MGRTD_CNTRCT_DT_SGMNT_MSTR tables
	 * @param contractId
	 * @param selectedOption
	 * @param selectedDateSegmentStartDate
	 * @param selectedNewRenewDate
	 * @param dateString
	 * @return
	 */
	public List persistMigrationInfo(String contractId,String selectedOption,
		     String selectedDateSegmentStartDate,
		     String selectedNewRenewDate,
		     String dateString,boolean isSecondTimeMigration,String user,int dateSegmentId,AdapterServicesAccess serviceAccessEWPDB) throws SevereException {
		Logger
        	.logInfo("MigrationAdapterManager - Entering persistMigrationInfo(): Migration Persist from step 2 to 3");
		HashMap params = new HashMap();
		params.put("contractId", contractId);
		params.put("selectedOption", selectedOption);
		params.put("selectedDateSegmentStartDate", selectedDateSegmentStartDate);
		params.put("selectedNewRenewDate", selectedNewRenewDate);
		params.put("dateString", dateString);
		params.put("isSecondTimeMigration", String.valueOf(isSecondTimeMigration));
		params.put("user", user);
		params.put("dateSegmentId", String.valueOf(dateSegmentId));
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationDateSegment.class.getName(),
						"persistMigrationInfo", params),serviceAccessEWPDB);
        Logger
                .logInfo("MigrationAdapterManager - Returning persistMigrationInfo(): Migration Persist from step 2 to 3");
        return searchResults.getSearchResults();
	}



	/**
	 * this function persists the legacy pricing info into migration tables using procedure
	 * @param migratedDSSysID
	 * @param user
	 * @param serviceAccessEWPDB
	 */
	public SearchResults persistPricingInfo(int migratedDSSysID, String user,String startDate,AdapterServicesAccess serviceAccessEWPDB)  throws SevereException, AdapterException{
		Logger
	    	.logInfo("MigrationAdapterManager - Entering persistPricingInfo(): Pricing Info Persist from step 3 to 4");
		HashMap params = new HashMap();
		params.put("systemId", String.valueOf(migratedDSSysID));
		params.put("user", user);
		params.put("startDate", startDate);
		
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationDateSegment.class.getName(),
						"persistPricingInfo", params),serviceAccessEWPDB);
	    Logger
	            .logInfo("MigrationAdapterManager - Returning persistPricingInfo(): Pricing Info Persist from step 3 to 4");
	    return searchResults;
	}



	/**
	 * this function persists the legacy Variable info into migration tables using procedure
	 * @param dateSegmentId
	 * @param user
	 */
	public SearchResults persistVariableInfo(int dateSegmentId,String startDate,AdapterServicesAccess serviceAccessEWPDB)throws SevereException,AdapterException {
		Logger
	    	.logInfo("MigrationAdapterManager - Entering persistVariableInfo(): Variable Info Persist from step 6 to 7");
		HashMap params = new HashMap();
		params.put("systemId", String.valueOf(dateSegmentId));
		params.put("startDate", startDate);
		
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationDateSegment.class.getName(),
						"persistVariableInfo", params),serviceAccessEWPDB);
	    Logger
	            .logInfo("MigrationAdapterManager - Returning persistVariableInfo(): Variable Info Persist from step 6 to 7");
	    return searchResults;
	}



	/**
	 * @param dateSegmentId
	 */
	public int isDateSegmentContainPricing(int dateSegmentId)throws SevereException {
		Logger
	    	.logInfo("MigrationAdapterManager - Entering isDateSegmentContainPricing():");
		HashMap params = new HashMap();
		params.put("contractDateSegmentSysId", String.valueOf(dateSegmentId));
		
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(MigrationPricing.class.getName(),
						"isDateSegmentContainPricing", params));
	    Logger
	            .logInfo("MigrationAdapterManager - Returning isDateSegmentContainPricing()");
	    return searchResults.getSearchResultCount();
	}



	/**
	 * @param dateSegmentId
	 * @return
	 */
	public int isMigrationProductRulesCoded(int dateSegmentId)throws SevereException  {
		Logger
	    	.logInfo("MigrationAdapterManager - Entering isMigrationProductRulesCoded():");
		HashMap params = new HashMap();
		params.put("dsSysId", String.valueOf(dateSegmentId));
		
		SearchResults searchResults = AdapterUtil.performSearch(AdapterUtil
				.getAdapterSearchCriteria(BusinessConstants.RULE_LOCATE_RESULT,
						"migrationRuleValidation", params));
	    Logger
	            .logInfo("MigrationAdapterManager - Returning isMigrationProductRulesCoded()");
	    return searchResults.getSearchResultCount();
	}
	/**
	 * @param migContrSysId
	 * @param mappingSysId
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void confirmLineMappings(int migContrSysId,
			int mappingSysId,  Audit audit,
			AdapterServicesAccess adapterServicesAccess,String authorized)
			throws SevereException, AdapterException {		
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;

		referenceValueSet.put("migContractSysId", new Integer(migContrSysId));
		referenceValueSet.put("mappingSysId", new Integer(mappingSysId));		
		referenceValueSet.put("lastUpdatedUser", audit.getUser());
		referenceValueSet.put("lastUpdatedTimeStamp", audit.getTime());
		referenceValueSet.put("authorized", authorized);
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ConfirmMigrationContract.class.getName(), "confirmLineMappings",
				referenceValueSet);		
		 AdapterUtil.performSearch(adapterSearchCriteria,
				adapterServicesAccess).getSearchResults();
	}
	
	/**
	 * 
	 * @param migrationProduct
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean mappingProductForNewVersion(
			MigrationProduct migrationProduct, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productSysId", new Integer(migrationProduct
				.getEwpdProductSysId()));
		criteriaMap.put("structureName", migrationProduct
				.getLegacyStructure());
		criteriaMap.put("lastChangedUser", migrationProduct
				.getLastUpdatedUser());
		criteriaMap.put("lastUpdatedTimeStamp", migrationProduct
				.getLastUpdatedTimestamp());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationProduct.class.getName(),
				"mappingProductForNewVersion", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess);
		if (searchResults != null)
			return true;
		else
			return false;
	}



	/** This Method is used to get Product Parent System Id 
	 * @throws SevereException
	 * 
	 */
	public MigrationDateSegment retrieveProductParentSysId(MigrationDateSegment migrationDateSegment)
			throws SevereException {

		HashMap map = new HashMap();
		map.put("contractSysId", new Integer(migrationDateSegment.getContractSysId()));
		SearchCriteria adapterSearchCriteria = null;
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(),
				WebConstants.RETRIEVE_PROD_PAR_SYS_ID, map);

		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		if (null != searchResultList && searchResultList.size() > 0) {
			migrationDateSegment = ((MigrationDateSegment) searchResultList
					.get(0));
			return migrationDateSegment;
		}
		return null;
	}
	
	/**
	 * @param removeProduct
	 * @param audit
	 * @param access
	 * @throws SevereException
	 */
	public void updateAndRemoveProduct(RemoveProduct removeProduct,Audit audit,AdapterServicesAccess access) throws SevereException{
		//To update the product id and mapping system id
		AdapterUtil.performUpdate(removeProduct,audit.getUser(),access);
		//To delete the product information
		AdapterUtil.performRemove(removeProduct,audit.getUser(),access);
		
	}
	
	/**
	 * @param dateSegment
	 * @return
	 * @throws SevereException
	 */
	public List getAutoPopulateValues(MigrationDateSegment dateSegment)
			throws SevereException {
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("contractSysId", new Integer(dateSegment
				.getContractSysId()));
		refValSet.put("systemId", new Integer(dateSegment.getSystemId()));
		int maxResultSize = 999999;
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigrationDateSegment.class.getName(), "getAutoPopulateValues",
				refValSet, maxResultSize);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
}