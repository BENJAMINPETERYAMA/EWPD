/*
 * MigrationBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.builder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.legacy.builder.LegacyBuilder;
import com.wellpoint.wpd.business.migration.adapter.MigrationAdapterManager;
import com.wellpoint.wpd.business.migration.util.ExclusionMigrationUtil;
import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.product.locatecriteria.ProductRuleRefDataLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.MigrationDomain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;
import com.wellpoint.wpd.common.migration.bo.BenefitLineNote;
import com.wellpoint.wpd.common.migration.bo.BenefitNote;
import com.wellpoint.wpd.common.migration.bo.CancelMigration;
import com.wellpoint.wpd.common.migration.bo.CancelSecondMigration;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContractForSave;
import com.wellpoint.wpd.common.migration.bo.DetachProduct;
import com.wellpoint.wpd.common.migration.bo.LastAccessDateSegmentUpdate;
import com.wellpoint.wpd.common.migration.bo.MigrateNotesBO;
import com.wellpoint.wpd.common.migration.bo.MigratedDateSegmentInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesAssnBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationPricing;
import com.wellpoint.wpd.common.migration.bo.MigrationProduct;
import com.wellpoint.wpd.common.migration.bo.MigrationProductAdministration;
import com.wellpoint.wpd.common.migration.bo.MigrationProductStructureMappingUpdate;
import com.wellpoint.wpd.common.migration.bo.MigrationVariableInsert;
import com.wellpoint.wpd.common.migration.bo.NavigationInfo;
import com.wellpoint.wpd.common.migration.bo.ProductRule;
import com.wellpoint.wpd.common.migration.bo.RemoveProduct;
import com.wellpoint.wpd.common.migration.bo.Rule;
import com.wellpoint.wpd.common.migration.bo.UpdateMigrationContractStatus;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationBusinessObjectBuilder {
	MigrationAdapterManager adapterManager;

	/**
	 * 
	 *  
	 */
	public MigrationBusinessObjectBuilder() {
		super();
		adapterManager = new MigrationAdapterManager();
	}

	public List getInfoForTree(int productSysId) throws SevereException {
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		List list = adapterManager.getInfoForTree(productSysId);
		return list;

	}
	
	public List getInfoForTree(int productSysId, int baseDateSegId) throws SevereException {
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		List list = adapterManager.getInfoForTree(productSysId, baseDateSegId);
		return list;

	} 

	/**
	 * 
	 * @param domainInfo
	 * @param audit
	 * @throws SevereException
	 */
	public void insertMigDomainInfo(MigrationDomainInfo domainInfo, Audit audit)
			throws SevereException {
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		adapterManager.saveDomainInfo(domainInfo, audit);
	}

	/**
	 * 
	 * @param dateSegmentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractEffectiveDate(int dateSegmentSysId)
			throws SevereException {
		return adapterManager.getContractEffectiveDate(dateSegmentSysId);
	}

	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public List getContractRenewDate(String contractId) throws SevereException {
		return adapterManager.getContractRenewDate(contractId);
	}

	public Date maxEffectiveOfMigratingContract(String contractID)
			throws SevereException {
		MigrationDateSegment dateSegment = adapterManager
				.maxEffectiveOfMigratingContract(contractID);
		if (null != dateSegment) {
			return dateSegment.getEffectiveDate();
		}
		return null;
	}

	/**
	 * 
	 * @param dateSegmentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractStartANDEndDate(int dateSegmentSysId)
			throws SevereException {
		return adapterManager.getContractStartANDEndDate(dateSegmentSysId);
	}

	/**
	 * 
	 * @param migDomain
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean persistDomainInfo(MigrationDomain migDomain, Audit audit)
			throws SevereException {
		List domainList = migDomain.getDomainList();
		Iterator iter;
		MigrationDomainInfo migrationDomainInfo = new MigrationDomainInfo();
		migrationDomainInfo
				.setContractSysId(migDomain.getMigContractSystemId());
		migrationDomainInfo.setBusinessEntityId("ALL"); //FIXME Remove
														// hardcoded
		migrationDomainInfo.setBusinessGroupId("ALL");
		/*START CARS*/
		migrationDomainInfo.setMarketBusinessUnitId("ALL");
		/*END CARS*/
		migrationDomainInfo.setLobId("ALL");
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persistDomainInfo(MigrationDomain migDomain, Audit audit)");

			adapterManager.removeDomain(migrationDomainInfo, audit,
					serviceAccessEWPDB);

			if ((domainList != null) && (!domainList.isEmpty())) {
				for (iter = domainList.iterator(); iter.hasNext();) {
					migrationDomainInfo = (MigrationDomainInfo) iter.next();
					migrationDomainInfo.setContractSysId(migDomain
							.getMigContractSystemId());
					adapterManager.saveDomainInfo(migrationDomainInfo, audit,
							serviceAccessEWPDB);
				}
			}
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persistDomainInfo(MigrationDomain migDomain, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistDomainInfo(MigrationDomain migDomain, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationDomainInfo, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistDomainInfo(MigrationDomain migDomain, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationDomainInfo, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistDomainInfo(MigrationDomain migDomain, Audit audit)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}

		return true;
	}

	/**
	 * 
	 * @param migSysId
	 * @return
	 * @throws SevereException
	 */
	public List getAllConflictingMappings(int migDatesegmentSysId) throws SevereException {
		return adapterManager.getAllConfilictingMappings(migDatesegmentSysId);
	}

	/**
	 * This method finds the legacy exclusions to eWPD rules mappings and
	 * returns a list of ProductRule Objects which is created by processing the
	 * exclusions and coded variables in the contract.
	 * 
	 * @param contract
	 *            Legacy Contract object. This should have ServiceCode
	 *            Exclusions, ServiceClass Exclusions, LimitClass Exclusions,
	 *            SpecialityCode Exclusions and CodedVariable to find out
	 *            corresponding ewpd Rules.
	 * @param productSysId
	 *            Associated product. Rules will be taken based on the product.
	 * @return List of ProductRule Objects. These objects will have a value
	 *         which is migrated from the Legacy.
	 */
	private List migrateExclusionsToRules(LegacyContract contract,
			int productSysId) throws SevereException {
		MigrationBusinessObjectBuilder builder = new MigrationBusinessObjectBuilder();
		List generatedRules = builder.getProductAssociatedRules(productSysId);
		List ruleDetails = builder.getProductExclusionRuleDetails(productSysId);
		ExclusionMigrationUtil migrationUtil = new ExclusionMigrationUtil(
				contract, ruleDetails, generatedRules);
		List mappingRules = migrationUtil.generateRuleMapping();
		return mappingRules;
	}

	public void copyRulesFromLegacyToEwpdb(int contractDateSegmentSysId,
			int productSysId, Audit audit) throws SevereException {
		if (contractDateSegmentSysId == 0) {
			return;
		}
		//		MigrationBusinessObjectBuilder migrationBuilder = new
		// MigrationBusinessObjectBuilder();
//		List migrationDateSegmentList = new ArrayList();
		List migrationDateSegmentList = this
				.getContractEffectiveDate(contractDateSegmentSysId);

		if (null != migrationDateSegmentList
				|| 0 != migrationDateSegmentList.size()) {
			MigrationDateSegment migrationDateSegment = (MigrationDateSegment) migrationDateSegmentList
					.get(0);
			LegacyBuilder legacyBuilder = new LegacyBuilder();
			LegacyContract contract = new LegacyContract();
			contract.setContractId(migrationDateSegment.getContractId());
			contract.setStartDate(migrationDateSegment.getLegacyStartDate());
			contract.setSystemCP2000();

			contract = legacyBuilder.retrieveDateSegment(contract,
					LegacyBuilder.OPT_DS_DETAIL_INFO,
					BusinessConstants.STATUS_MIGRATION_IN_PROGRESS);
			if (null != contract) {

				List mappedRules = migrateExclusionsToRules(contract,
						productSysId);
				List unmappedRules = this.getUnmappedRules(productSysId,
						mappedRules);
				List dateSegmentRules = null;
				if(null!=mappedRules && null!=unmappedRules){
					dateSegmentRules =new ArrayList(mappedRules.size()+unmappedRules.size());
				}else if(null!=mappedRules){
					dateSegmentRules =new ArrayList(mappedRules.size());
				}else if(null!=unmappedRules){
					dateSegmentRules =new ArrayList(unmappedRules.size());
				}
				Iterator iterator;
				ProductRule element;
				MigrationContractRulesBO migrationContractRulesBO = null;
				// Mapped Rules will be shown as associated and others will be
				// shown as available but not
				// associated to date segment.
				for (iterator = mappedRules.iterator(); iterator.hasNext();) {
					element = (ProductRule) iterator.next();
					migrationContractRulesBO = new MigrationContractRulesBO();
					migrationContractRulesBO
							.setContractDateSegmentSysId(contractDateSegmentSysId);
					migrationContractRulesBO.setProductRuleSysID(element
							.getProductRuleSysId());
					migrationContractRulesBO.setRuleComment(element.getValue());
					migrationContractRulesBO.setFlag("N");
					dateSegmentRules.add(migrationContractRulesBO);
				}
				for (iterator = unmappedRules.iterator(); iterator.hasNext();) {
					element = (ProductRule) iterator.next();
					migrationContractRulesBO = new MigrationContractRulesBO();
					migrationContractRulesBO
							.setContractDateSegmentSysId(contractDateSegmentSysId);
					migrationContractRulesBO.setProductRuleSysID(element
							.getProductRuleSysId());
					migrationContractRulesBO.setRuleComment(element.getValue());
					migrationContractRulesBO.setFlag("Y");
					dateSegmentRules.add(migrationContractRulesBO);
				}
				ProductRuleAssociation productRuleAssociation = new ProductRuleAssociation();
				productRuleAssociation.setRulesList(dateSegmentRules);
				try {
					this.persist(productRuleAssociation, audit);
				} catch (WPDException ex) {
					throw new ServiceException(
							"Exception occured while BusinessObjectBuilder call",
							null, ex);
				}
			}
		}
	}

	/**
	 * 
	 * @param productSysId
	 * @return
	 * @throws SevereException
	 */
	public List getProductAssociatedRules(int productSysId)
			throws SevereException {
		return adapterManager.getAssociatedRules(productSysId);
	}

	/**
	 * 
	 * @param productSysId
	 * @return
	 * @throws SevereException
	 */
	public List getProductExclusionRuleDetails(int productSysId)
			throws SevereException {
		List rules = adapterManager.getProductExclusionRules(productSysId);
		Rule rule;
		Rule childRule;
		List childRules;
		Iterator iterator2;
		if (null == rules) {
			return new ArrayList();
		}
		for (Iterator iterator = rules.iterator(); iterator.hasNext();) {
			rule = (Rule) iterator.next();
			if ("Y".equals(rule.getGroupInd())) {
				childRules = adapterManager.getChildRules(rule.getRuleId());
				for (iterator2 = childRules.iterator(); iterator2.hasNext();) {
					childRule = (Rule) iterator2.next();
					retrieveRuleSequences(childRule);
				}
			} else {
				retrieveRuleSequences(rule);
			}
		}
		return rules;
	}

	/**
	 * 
	 * @param rule
	 * @throws SevereException
	 */
	private void retrieveRuleSequences(Rule rule) throws SevereException {
		List sequences = adapterManager.getRuleSequences(rule.getRuleId());
		rule.setSequences(sequences);
	}

	/**
	 * 
	 * @param productSysId
	 * @param mappedProductRules
	 * @return
	 * @throws SevereException
	 */
	public List getUnmappedRules(int productSysId, List mappedProductRules)
			throws SevereException {
		return adapterManager
				.getUnmappedRules(productSysId, mappedProductRules);
	}

	/**
	 * 
	 * @param migDateSegment
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean persistMigGenInfo(MigrationDateSegment migDateSegment,
			Audit audit) throws SevereException {
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		adapterManager.saveMigGeneralInfo(migDateSegment, audit);
		return true;

	}

	/**
	 * 
	 * @param migDateSegment
	 * @return
	 * @throws SevereException
	 */
	public MigrationDateSegment retrieveMigGenInfo(
			MigrationDateSegment migDateSegment) throws SevereException {
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		MigrationDateSegment migDtSeg = adapterManager
				.getMigGeneralInfo(migDateSegment);
		//For auto-poulating the model contract and product
		if(migDtSeg.getEwpdProductSystemId()==0){
			List list = adapterManager.getAutoPopulateValues(migDtSeg);
			if(list!=null && !list.isEmpty()){
				MigrationDateSegment migrationDateSegment = (MigrationDateSegment)list.get(0);		
				migDtSeg.setEwpdProductSystemId(migrationDateSegment.getEwpdProductSystemId());
				migDtSeg.setProductName(migrationDateSegment.getProductName());
				migDtSeg.setProductFamily(migrationDateSegment.getProductFamily());
				if(migrationDateSegment.getBaseDateSegmentSysId() != 0){					
					migDtSeg.setBaseDateSegmentSysId(migrationDateSegment.getBaseDateSegmentSysId());
					migDtSeg.setBaseContractId(migrationDateSegment.getBaseContractId());
					migDtSeg.setBaseContractSysId(migrationDateSegment.getBaseContractSysId());
					migDtSeg.setBaseDateSegmentEffectiveDate(migrationDateSegment.getBaseDateSegmentEffectiveDate());
					migDtSeg.setAutoPopulateValues(true);
				}
			}else{
				migDtSeg.setAutoPopulateValues(false);
			}
		}
		return migDtSeg;
	}

	/**
	 * 
	 * @param contractSysId
	 * @return
	 * @throws SevereException
	 */
	public List retrieveMigDomainInfo(int contractSysId) throws SevereException {
		List migDomainList = null;
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		if (null != adapterManager.getMigDomainInfo(contractSysId))
			migDomainList = adapterManager.getMigDomainInfo(contractSysId)
					.getSearchResults();
		if (migDomainList == null)
			return null;
		return migDomainList;
	}

	/**
	 * @param map
	 * @param user
	 * @return
	 */
	public List getBenefitList(HashMap map, User user) throws WPDException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		map.put("createdUser", audit.getUser());
		return migWizardAdapterManager.getBenefitLineList(map);
	}

	/**
	 * 
	 * @param variableMappingList
	 * @param user
	 * @throws WPDException
	 */
	public void saveVariableMappingList(List variableMappingList,
			List migrateNotesList, User user) throws WPDException {
		if (((variableMappingList != null) && (!variableMappingList.isEmpty()))
				|| (((migrateNotesList != null) && (!migrateNotesList.isEmpty())))) {

			AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
					.getAdapterService();
			long transactionId = AdapterUtil.getTransactionId();
			try {
				AdapterUtil.beginTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logBeginTranstn(transactionId, this,
								"saveVariableMappingList(List variableMappingList, User user)");
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(user);
				MigrationAdapterManager migWizardAdapterManager = null;
				if ((variableMappingList != null)
						&& (!variableMappingList.isEmpty())) {
					Iterator variableMappingIterator = variableMappingList
							.iterator();
					while (variableMappingIterator.hasNext()) {
						MigrationVariableInsert migrationVariable = (MigrationVariableInsert) variableMappingIterator
								.next();

						migrationVariable.setCreatedUser(audit.getUser());
						migrationVariable.setCreatedTimestamp(audit.getTime());
						migrationVariable.setLastUpdatedTimestamp(audit
								.getTime());
						migrationVariable.setLastUpdatedUser(audit.getUser());
						migWizardAdapterManager = new MigrationAdapterManager();
						migWizardAdapterManager.saveVariableMapping(
								migrationVariable, audit.getUser(),
								serviceAccessEWPDB);
					}
				}

				if ((migrateNotesList != null) && (!migrateNotesList.isEmpty())) {

					Iterator migrateNotesIterator = migrateNotesList.iterator();
					while (migrateNotesIterator.hasNext()) {
						BenefitLineNote benefitLineNote = (BenefitLineNote) migrateNotesIterator
								.next();
						benefitLineNote.setCreatedUser(audit.getUser());
						benefitLineNote.setCreatedTimestamp(audit.getTime());
						benefitLineNote
								.setLastUpdatedTimestamp(audit.getTime());
						benefitLineNote.setLastUpdatedUser(audit.getUser());
						migWizardAdapterManager = new MigrationAdapterManager();
						migWizardAdapterManager.deleteMigrateNotesInfo(
								benefitLineNote, audit.getUser(),
								serviceAccessEWPDB);
						migWizardAdapterManager.saveMigrateNotesInfo(
								benefitLineNote, audit.getUser(),
								serviceAccessEWPDB);

					}

				}

				AdapterUtil.endTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"saveVariableMappingList(List variableMappingList, User user)");
			} catch (SevereException ex) {
				AdapterUtil.abortTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"saveVariableMappingList(List variableMappingList, User user)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in persist method , for persisting the BusinessObject MigrationVariableInsert, in MigrationBusinessObjectBuilder",
						errorParams, ex);
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"saveVariableMappingList(List variableMappingList, User user)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in persist method , for persisting the BusinessObject MigrationVariableInsert, in MigrationBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception e) {
				AdapterUtil.abortTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"saveVariableMappingList(List variableMappingList, User user)");
				throw new SevereException("Unhandled exception is caught",
						null, e);
			}
		}
	}

	/**
	 * 
	 * @param migrationContractSysID
	 * @param doneFlag
	 * @param option
	 * @param audit
	 * @throws SevereException
	 */
	private void updateMigrationStatus(int migrationContractSysID,
			String doneFlag, String option, Audit audit,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		UpdateMigrationContractStatus migrationContractStatus = new UpdateMigrationContractStatus();
		migrationContractStatus.setMigrationSystemId(migrationContractSysID);
		migrationContractStatus.setDoneFlag(doneFlag);
		migrationContractStatus.setOption(option);
		this.updateMigrationStatus(migrationContractStatus, audit,
				adapterServicesAccess);
	}

	/**
	 * Function to update Migration completion status.
	 * 
	 * @param status
	 * @param audit
	 * @throws SevereException
	 */
	public void updateMigrationStatus(UpdateMigrationContractStatus status,
			Audit audit) throws SevereException {
		status.setLastUpdatedUser(audit.getUser());
		status.setLastUpdatedTimeStamp(audit.getTime());
		this.adapterManager.updateMigrationStatus(status, audit.getUser());
	}

	/**
	 * 
	 * @param status
	 * @param audit
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void updateMigrationStatus(UpdateMigrationContractStatus status,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		status.setLastUpdatedUser(audit.getUser());
		status.setLastUpdatedTimeStamp(audit.getTime());
		this.adapterManager.updateMigrationStatus(status, audit.getUser(),
				adapterServicesAccess);
	}

	/**
	 * Function to retrieve a Migration Pricing Information.
	 * 
	 * @param map
	 * @return list
	 * @throws WPDException
	 */
	public List getPricingInfoList(HashMap map) throws WPDException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.getPricingInfoList(map);
	}

	/**
	 * Function to persist a Migration Pricing Information.
	 * 
	 * @param migrationPricing
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean persistMigrationPricingInfo(
			MigrationPricing migrationPricing, Audit audit)
			throws SevereException, AdapterException {
		migrationPricing.setLastUpdatedUser(audit.getUser());
		migrationPricing.setLastUpdatedTimestamp(audit.getTime());
		migrationPricing.setCreatedUser(audit.getUser());
		migrationPricing.setCreatedTimestamp(audit.getTime());
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persistMigrationPricingInfo(MigrationPricing migrationPricing, Audit audit)");

			migWizardAdapterManager.persistMigrationPricingInfo(migrationPricing,
					audit,serviceAccessEWPDB);
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persistMigrationPricingInfo(MigrationPricing migrationPricing, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistMigrationPricingInfo(MigrationPricing migrationPricing, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistMigrationPricingInfo(MigrationPricing migrationPricing, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistMigrationPricingInfo(MigrationPricing migrationPricing, Audit audit)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
		return true;
	}

	/**
	 * Function to remove a Migration Pricing Information.
	 * 
	 * @param migrationPricing
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean deleteMigrationPricingInfo(
			MigrationPricing migrationPricing, Audit audit)
			throws SevereException, AdapterException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.deleteMigrationPricingInfo(migrationPricing,
				audit);
		return true;
	}

	/**
	 * 
	 * @param cancelMigration
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean cancelMigration(CancelMigration cancelMigration, Audit audit)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.cancelMigration(cancelMigration, audit);
		return true;

	}
	
	/**
	 * 
	 * @param cancelMigration
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean detachProduct(DetachProduct detachProduct, Audit audit)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		detachProduct.setLastChangedUser(audit.getUser());
		detachProduct.setLastUpdatedTime(audit.getTime());
		migWizardAdapterManager.detachProduct(detachProduct, audit);
		return true;

	}

	/**
	 * 
	 * @param cancelSecMigration
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean cancelMigration(CancelSecondMigration cancelSecMigration,
			Audit audit) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.cancelSecMigration(cancelSecMigration, audit);
		return true;

	}

	/**
	 * Function to remove a Migration Pricing Information.
	 * 
	 * @param migrationPricing
	 * @param audit
	 * @param
	 * @return
	 * @throws SevereException
	 */
	public boolean rollbackAllMigrationPricingInfo(
			MigrationPricing migrationPricing, Audit audit)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.deleteMigrationPricingInfo(migrationPricing,
				audit);
		return true;
	}

	/**
	 * Function to retrieve a Migration Product Information.
	 * 
	 * @param map
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getProductInfo(HashMap map) throws SevereException,
			AdapterException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.getProductInfo(map);
	}

	/**
	 * Function to Update a Migration Product Information.
	 * 
	 * @param businessObject
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean updateMigrationProductInfo(MigrationProduct businessObject,
			Audit audit) throws SevereException, AdapterException {
		businessObject.setLastUpdatedUser(audit.getUser());
		businessObject.setLastUpdatedTimestamp(audit.getTime());
		businessObject.setCreatedUser(audit.getUser());
		businessObject.setCreatedTimestamp(audit.getTime());
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		MigrationProduct migrationProduct = businessObject;
		migWizardAdapterManager.updateMigrationProductInfo(migrationProduct,
				audit);
		return true;
	}

	/**
	 * Function to persist a Migration Product Information.
	 * 
	 * @param businessObject
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean persistMigrationProductInfo(MigrationProduct businessObject,
			Audit audit) throws SevereException, AdapterException {
		businessObject.setLastUpdatedUser(audit.getUser());
		businessObject.setLastUpdatedTimestamp(audit.getTime());
		businessObject.setCreatedUser(audit.getUser());
		businessObject.setCreatedTimestamp(audit.getTime());
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		MigrationProduct migrationProduct = businessObject;
		migWizardAdapterManager.persistMigrationProductInfo(migrationProduct,
				audit);
		return true;
	}

	/**
	 * Function to retrieve Unmapped Variables for Report Generation.
	 * 
	 * @param map
	 * @return list
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List locateUnmappedVariables(HashMap map) throws SevereException,
			AdapterException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.locateUnmappedVariables(map);
	}

	/**
	 * Function to retrieve Mapped Variables for Report Generation.
	 * 
	 * @param map
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List locateMappedVariables(HashMap map) throws SevereException,
			AdapterException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.locateMappedVariables(map);
	}

	/**
	 * Function to Insert/Update Last Accesed page and Step Number completed.
	 * 
	 * @param navigationInfo
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */
	public boolean persistPageInformation(NavigationInfo navigationInfo,
			Audit audit, boolean insertFlag) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		if (insertFlag) {
			return migWizardAdapterManager.persistPageInformation(
					navigationInfo, audit.getUser());
		} else {
			return migWizardAdapterManager.updatePageInformation(
					navigationInfo, audit.getUser());
		}
	}

	/**
	 * Function to update last access date segment information.
	 * 
	 * @param migDateSegmentSysID
	 * @param audit
	 * @throws SevereException
	 */
	public void updateLastAccessDateSegment(int migDateSegmentSysID, Audit audit)
			throws SevereException {
		LastAccessDateSegmentUpdate accessDateSegmentUpdate = new LastAccessDateSegmentUpdate();
		accessDateSegmentUpdate.setMigDateSegmentSysId(migDateSegmentSysID);
		accessDateSegmentUpdate.setLastUpdatedUser(audit.getUser());
		accessDateSegmentUpdate.setLastUpdatedTimeStamp(audit.getTime());
		adapterManager.updateLastAccessDateSegment(accessDateSegmentUpdate,
				audit.getUser());
	}

	/**
	 * Checks a PricingInfo is duplicate or not.returns a boolean
	 * 
	 * @param migrationPricing
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean isPricingInfoDuplicate(MigrationPricing migrationPricing)
			throws SevereException, AdapterException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		List duplicateList = migWizardAdapterManager
				.getDuplicatePricingInfo(migrationPricing);
		if (duplicateList != null && duplicateList.size() > 0)
			return true;
		return false;
	}

	/**
	 * Method to retrieve Product Rule associated with product. migraction
	 * contract step 6
	 * 
	 * @param locateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locate(
			ProductRuleRefDataLocateCriteria locateCriteria, User user)
			throws SevereException {

		List list = adapterManager.getRulesList(locateCriteria.getQueryName(),
				locateCriteria.getRuleType(), locateCriteria.getKey());
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(list);
		return locateResults;
	}

	/**
	 * This method fetches the migrationContractRulesAssociationBO and adds it
	 * to the MigrationContract migraction contract step 6
	 * 
	 * @param migrationContract
	 * @param params
	 * @return
	 * @throws WPDException
	 */
	public MigrationContract retrieve(MigrationContract migrationContract,
			java.util.Map params) throws WPDException {
		if (null != params.get("action")
				&& params.get("action").equals("retrieveDenialAndExclusion")) {
			this.adapterManager.getContractRulesList(migrationContract, params
					.get("ruleType").toString());
		}
		return migrationContract;
	}

	public MigrationContract retrieve(MigrationContract migrationContract)
			throws SevereException {
		migrationContract = (MigrationContract) AdapterUtil
				.performRetrieve(migrationContract);
		return migrationContract;
	}

	/**
	 * Persist method for Product Rule. migraction contract step 6
	 * 
	 * @param productRuleAssociation
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean persist(ProductRuleAssociation productRuleAssociation,
			Audit audit, boolean insertFlag) throws WPDException {

		List newRulesList = productRuleAssociation.getRulesList();

		MigrationContractRulesBO migrationContractRulesBO = null;
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			if (insertFlag) {
				if (newRulesList == null || newRulesList.size() == 0)
					return true;

				AdapterUtil.beginTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");

				MigrationContractRulesAssnBO migrationContractRulesAssnBO = null;
				String user = audit.getUser();
				java.util.Date time = audit.getTime();

				// Updating Rule comments
				for (int i = 0; i < newRulesList.size(); i++) {
					migrationContractRulesAssnBO = (MigrationContractRulesAssnBO) newRulesList
							.get(i);
					migrationContractRulesAssnBO.setLastUpdatedUser(user);
					migrationContractRulesAssnBO.setLastUpdatedTimestamp(time);
					this.adapterManager.updateContractRules(
							migrationContractRulesAssnBO, user,
							serviceAccessEWPDB);
				}

				AdapterUtil.endTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");
			} else {
				if (newRulesList == null || newRulesList.size() == 0)
					return true;

				AdapterUtil.beginTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");

				String user = audit.getUser();
				java.util.Date time = audit.getTime();
				// Updating Rules
				for (int i = 0; i < newRulesList.size(); i++) {
					migrationContractRulesBO = (MigrationContractRulesBO) newRulesList
							.get(i);
					migrationContractRulesBO.setLastUpdatedUser(user);
					migrationContractRulesBO.setLastUpdatedTimestamp(time);
					migrationContractRulesBO.setRuleType("toMakenotEmpty");
					this.adapterManager.updateContractRule(
							migrationContractRulesBO, user, serviceAccessEWPDB);
				}

				AdapterUtil.endTransaction(serviceAccessEWPDB);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");
			}
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContractRulesBO, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContractRulesBO, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductRuleAssociation productRuleAssociation,Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
		return true;
	}

	/**
	 * Persist method for Product Rule. migraction contract step 6
	 * 
	 * @param productRuleAssociation
	 * @param audit
	 * @return
	 * @throws WPDException
	 */
	public void persist(ProductRuleAssociation productRuleAssociation,
			Audit audit) throws WPDException {

		List newRulesList = productRuleAssociation.getRulesList();

		MigrationContractRulesBO migrationContractRulesBO = null;

		if (newRulesList == null || newRulesList.size() == 0)
			return;

		String user = audit.getUser();
		java.util.Date time = audit.getTime();
		// Updating Rules
		int listSize = newRulesList.size();
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(ProductRuleAssociation productRuleAssociation, Audit audit)");

			for (int i = 0; i < listSize; i++) {
				migrationContractRulesBO = (MigrationContractRulesBO) newRulesList
						.get(i);
				migrationContractRulesBO.setCreatedUser(user);
				migrationContractRulesBO.setLastUpdatedUser(user);
				migrationContractRulesBO.setCreatedTimestamp(time);
				migrationContractRulesBO.setLastUpdatedTimestamp(time);
				migrationContractRulesBO.setRuleType("toMakenotEmpty");
				this.adapterManager.createProductRules(
						migrationContractRulesBO, user, serviceAccessEWPDB);
			}

			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(ProductRuleAssociation productRuleAssociation, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ProductRuleAssociation productRuleAssociation, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContractRulesBO, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ProductRuleAssociation productRuleAssociation, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContractRulesBO, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ProductRuleAssociation productRuleAssociation, Audit audit)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
	}

	private MigrationContract copyLegacyContractToMigrationContract(
			String option, LegacyContract legacyContract, int sysId)
			throws SevereException {
		MigrationContract migrationContract = new MigrationContract();

		migrationContract.setContractId(legacyContract.getContractId());
		migrationContract.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationContract.setCreatedUser(legacyContract.getCreatedUser());
		migrationContract.setDoneFlag("N"); //TODO add it to constant for not
		// done
		migrationContract.setEwpdProductSystemId(null);
		migrationContract.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationContract.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());
		migrationContract.setMigrationSystemId(new Integer(sysId).toString());
		migrationContract.setOption(option);
		migrationContract.setProductFamily(null);

		migrationContract.setStructreProductMappingId(legacyContract
				.getBenefitStructure());
		migrationContract.setSystem(legacyContract.getSystem());

		//added
		//migrationContract.setHeadQuartersState(legacyContract.getHeadQuarterState());
		migrationContract.setProductFamily(legacyContract.getProductFamily());
		String contractInd = legacyContract.getModelIndicator();
		String migContractType = null;
		boolean isType = false;
		if (contractInd != null) {
			if (contractInd.equals("C")) {

				migContractType = WebConstants.CUSTOM;
				isType = true;
			} else if (contractInd.equals("M")) {
				migContractType = WebConstants.MODEL;
				isType = true;
			} else if (contractInd.equals("S")) {
				migContractType = WebConstants.STANDAR;
				isType = true;
			}
			if (isType) {
				migrationContract.setContractType(migContractType);
			}
		}

		migrationContract.setHeadQuartersState(legacyContract
				.getHeadQuarterState());

		return migrationContract;
	}

	private MigrationDateSegment copyMigrationContractToMigrationDateSegment(
			LegacyContract legacyContract, MigrationContract migrationContract)
			throws SevereException {
		MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();

		migrationDateSegment.setBenefitPlan(legacyContract.getBenefitPlan());

		migrationDateSegment.setContractSysId(Integer
				.parseInt(migrationContract.getMigrationSystemId()));

		if (migrationBusinessObjectBuilder.validateItemField(1947,
				legacyContract.getCorporatePlanCode())) {
			migrationDateSegment.setCorporatePlanCode(legacyContract
					.getCorporatePlanCode());
		}

		migrationDateSegment.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationDateSegment.setCreatedUser(legacyContract.getCreatedUser());
		migrationDateSegment.setGroupSizeDesc(legacyContract.getGroupName());
		if (migrationBusinessObjectBuilder.validateItemField(1626,
				legacyContract.getHeadQuarterState())) {
			migrationDateSegment.setHeadQuartersState(legacyContract
					.getHeadQuarterState());
		}
		migrationDateSegment.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationDateSegment.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());
		if (migrationBusinessObjectBuilder.validateItemField(1786,
				legacyContract.getProductCode())) {
			migrationDateSegment
					.setProductCode(legacyContract.getProductCode());
		}

		migrationDateSegment
				.setProductFamily(legacyContract.getProductFamily());
		if (migrationBusinessObjectBuilder.validateItemField(1701,
				legacyContract.getStandardPlanCode())) {
			migrationDateSegment.setStandardPlanCode(legacyContract
					.getStandardPlanCode());
		}
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getMigratedDateSegSysIdSequence();
		migrationDateSegment.setSystemId(sysId);
		migrationDateSegment.setLastAccessedPage("step2");
		migrationDateSegment.setStepCompleted("2");
		migrationDateSegment.setEffectiveDate(legacyContract.getStartDate());
		migrationDateSegment.setLegacyStartDate(legacyContract.getStartDate());
		migrationDateSegment.setExpiryDate(legacyContract.getEndDate());

		CP2000Contract contract = (CP2000Contract) legacyContract;

		if (null == contract.getItsHomeAdjInd()) {
			migrationDateSegment.setItsHomeAdjInd("N");
		} else {
			migrationDateSegment.setItsHomeAdjInd(contract.getItsHomeAdjInd());
		}

		if (null == contract.getMedicareAdjudInd()) {
			migrationDateSegment.setMedicareAdjudInd("N");
		} else {
			migrationDateSegment.setMedicareAdjudInd(contract
					.getMedicareAdjudInd());
		}

		if (null == contract.getCobAdjudInd()) {
			migrationDateSegment.setCobAdjudInd("N");
		} else {
			migrationDateSegment.setCobAdjudInd(contract.getCobAdjudInd());
		}

		return migrationDateSegment;
	}

	private MigrationDateSegment copyRenewContractToMigrationDateSegment(
			LegacyContract legacyContract, MigrationContract migrationContract,
			Date newDate) throws SevereException {

		MigrationDateSegment migrationDateSegment = new MigrationDateSegment();
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		Date endDate = WPDStringUtil.getDateFromString("12/31/9999");
		migrationDateSegment.setBenefitPlan(legacyContract.getBenefitPlan());

		migrationDateSegment.setContractSysId(Integer
				.parseInt(migrationContract.getMigrationSystemId()));

		if (migrationBusinessObjectBuilder.validateItemField(1947,
				legacyContract.getCorporatePlanCode())) {
			migrationDateSegment.setCorporatePlanCode(legacyContract
					.getCorporatePlanCode());
		}

		migrationDateSegment.setCreatedTimeStamp(legacyContract
				.getCreatedTimestamp());
		migrationDateSegment.setCreatedUser(legacyContract.getCreatedUser());
		migrationDateSegment.setGroupSizeDesc(legacyContract.getGroupName());

		migrationDateSegment.setLastUpdatedTimeStamp(legacyContract
				.getLastUpdatedTimestamp());
		migrationDateSegment.setLastUpdatedUser(legacyContract
				.getLastUpdatedUser());
		if (migrationBusinessObjectBuilder.validateItemField(1786,
				legacyContract.getProductCode())) {
			migrationDateSegment
					.setProductCode(legacyContract.getProductCode());
		}

		migrationDateSegment
				.setProductFamily(legacyContract.getProductFamily());
		if (migrationBusinessObjectBuilder.validateItemField(1701,
				legacyContract.getStandardPlanCode())) {
			migrationDateSegment.setStandardPlanCode(legacyContract
					.getStandardPlanCode());
		}

		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getMigratedDateSegSysIdSequence();
		migrationDateSegment.setSystemId(sysId);

		migrationDateSegment.setEffectiveDate(newDate);

		migrationDateSegment.setExpiryDate(endDate);
		migrationDateSegment.setLastAccessedPage("step2");
		migrationDateSegment.setStepCompleted("2");
		migrationDateSegment.setLegacyStartDate(legacyContract.getStartDate());

		CP2000Contract contract = (CP2000Contract) legacyContract;

		if (null == contract.getItsHomeAdjInd()) {
			migrationDateSegment.setItsHomeAdjInd("N");
		} else {
			migrationDateSegment.setItsHomeAdjInd(contract.getItsHomeAdjInd());
		}

		if (null == contract.getMedicareAdjudInd()) {
			migrationDateSegment.setMedicareAdjudInd("N");
		} else {
			migrationDateSegment.setMedicareAdjudInd(contract
					.getMedicareAdjudInd());
		}

		if (null == contract.getCobAdjudInd()) {
			migrationDateSegment.setCobAdjudInd("N");
		} else {
			migrationDateSegment.setCobAdjudInd(contract.getCobAdjudInd());
		}

		return migrationDateSegment;
	}

	public MigrationContractSession persistMigrationInfo(
			List legacyContractList, String option,
			boolean isSecondTimeMigration, String selectedDateSegmentStartDate,
			String selectedNewRenewDate, Audit audit) throws SevereException {

		String userID = audit.getUser();
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		LegacyContract legacyContract = null;
		LegacyContract contract = new LegacyContract();
		MigrationContract migrationContract = null;
		MigrationDateSegment migrationDateSegment;
		//		MigrationContractSession migrationContractSession = new
		// MigrationContractSession();

		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		MigrationContractSession migrationContractSession = new MigrationContractSession();
		int contractSystemId = 0;
		List searchResults = new ArrayList(); 
		Format format;
		MigrationDateSegment dateSegment = null;
		String dateString = "";
		String methodName = "persistMigrationInfo(List legacyContractList, String option, boolean isSecondTimeMigration, String selectedDateSegmentStartDate, String selectedNewRenewDate,Audit audit)";

		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionIdEwpd = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil.logBeginTranstn(transactionIdEwpd, this, methodName); //for
																			  // serviceAccessEWPDB

//			int sysId = sequenceAdapterManager
//					.getMigratedContractSysIdSequence(serviceAccessEWPDB);

			if (option
					.equals(BusinessConstants.OPT_MIGRATE_DS)) {
				int listSize = legacyContractList.size();
				List latestTwoDSList = new ArrayList(listSize);
				int count = 0;
				legacyContract = (LegacyContract) legacyContractList
					.get(listSize - 1);
				contract = legacyContract;
				
				if (isSecondTimeMigration) {
					//second time migration
					//retrive the migContractSysID
					String contractID = legacyContract.getContractId();
					List migrationContractList = this
							.getMigrationContract(contractID);
					if (null != migrationContractList
							&& !migrationContractList.isEmpty()) {
						migrationContract = (MigrationContract) migrationContractList
								.get(0);

						this.updateMigrationStatus(Integer
								.parseInt(migrationContract
										.getMigrationSystemId()),
								MigrationContractUtil.DONE_FLAG_SECOND,
								BusinessConstants.OPT_MIGRATE_DS, audit,
								serviceAccessEWPDB);

						migrationContract
								.setDoneFlag(MigrationContractUtil.DONE_FLAG_SECOND);
						migrationContract.setCreatedUser(userID);
					}
					Iterator dsListIterator = legacyContractList.iterator();
					// to get the start date of all the selected date segments
					while (dsListIterator.hasNext()) {
						legacyContract = (LegacyContract) dsListIterator.next();
						format = new SimpleDateFormat("dd-MMM-yyyy");
						dateString = dateString + format.format(legacyContract.getStartDate());
						
						if(dsListIterator.hasNext()){
							dateString = dateString+"~";
						}
					}
					// calls a procedure which will insert values in to MGRTD_CNTRCT_DT_SGMNT_MSTR table 
					searchResults = adapterManager.persistMigrationInfo(contractID,option,"","",dateString,isSecondTimeMigration,audit.getUser(),Integer
							.parseInt(migrationContract
									.getMigrationSystemId()),serviceAccessEWPDB);
					Iterator iterator = searchResults.iterator();
					while(iterator.hasNext()){
						dateSegment = (MigrationDateSegment)iterator.next();
						contractSystemId = dateSegment.getContractSysId();
						break;
					}

				}else{	
					
					Iterator dsListIterator = legacyContractList.iterator();
					// to get the start date of all the selected date segments
					while (dsListIterator.hasNext()) {
						legacyContract = (LegacyContract) dsListIterator.next();
						format = new SimpleDateFormat("dd-MMM-yyyy");
						dateString = dateString + format.format(legacyContract.getStartDate());
						
						if(dsListIterator.hasNext()){
							dateString = dateString+"~";
						}
					}
					// calls a procedure which will insert values in to 
					//MGRTD_CNTRCT_MSTR and MGRTD_CNTRCT_DT_SGMNT_MSTR tables 
					searchResults = adapterManager.persistMigrationInfo(legacyContract.getContractId(),option,"","",dateString,isSecondTimeMigration,audit.getUser(),0,serviceAccessEWPDB);
					Iterator iterator = searchResults.iterator();
					while(iterator.hasNext()){
						dateSegment = (MigrationDateSegment)iterator.next();
						contractSystemId = dateSegment.getContractSysId();
						migrationContract = copyLegacyContractToMigrationContract(
								option, contract, contractSystemId);
						break;
					}
				}
			} else if (option.equals(BusinessConstants.OPT_RENEW_DS)) {
				legacyContract = (LegacyContract) legacyContractList.get(0);
				contract = legacyContract;
				Iterator legacyListIterator = legacyContractList.iterator();

				while (legacyListIterator.hasNext()) {
					legacyContract = (LegacyContract) legacyListIterator.next();
					SimpleDateFormat formatter = new SimpleDateFormat(
							WebConstants.DATE_FORMAT_STRING);

					String legacyContractStartDate = formatter
							.format(legacyContract.getStartDate());

					if (legacyContractStartDate
							.equals(selectedDateSegmentStartDate)) {
						format = new SimpleDateFormat("dd-MMM-yyyy");
						dateString = dateString + format.format(WPDStringUtil.getDateFromString(selectedDateSegmentStartDate));
						// calls a procedure which will insert values in to 
						//MGRTD_CNTRCT_MSTR and MGRTD_CNTRCT_DT_SGMNT_MSTR tables 
						searchResults = adapterManager.persistMigrationInfo(legacyContract
								.getContractId(),option,selectedNewRenewDate,
								"12/31/9999",dateString,isSecondTimeMigration,audit.getUser(),0,serviceAccessEWPDB);
						Iterator iterator = searchResults.iterator();
						while(iterator.hasNext()){
							dateSegment = (MigrationDateSegment)iterator.next();
							contractSystemId = dateSegment.getContractSysId();
							migrationContract = copyLegacyContractToMigrationContract(
									option, contract, contractSystemId);
							break;
						}
						break;
					}//end if
				}//emd while
			}
			
			
			//end if OPT_RENEW_DS
			//			 DB Operations in Legacy
			AdapterServicesAccess legacyServiceAccess = legacyBuilder
					.getAdapterServiceAccess(LegacyObject.SYSTEM_CP2000);
			long transactionIdCp2000 = AdapterUtil.getTransactionId();
			AdapterUtil.beginTransaction(legacyServiceAccess);
			AdapterUtil.logBeginTranstn(transactionIdCp2000, this, methodName); // for
																				// legacyServiceAccess
			try {
				if (null != legacyContract) {
					legacyContract.setCreatedUser(userID);
					legacyBuilder.lockAndChangeContractStatus(legacyContractList,
							option, legacyServiceAccess);
				}
				AdapterUtil.endTransaction(legacyServiceAccess);
				AdapterUtil.logTheEndTransaction(transactionIdCp2000, this,
						methodName);// for legacyServiceAccess
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(legacyServiceAccess);
				AdapterUtil.logAbortTxn(transactionIdCp2000, this, methodName);// for
																			   // legacyServiceAccess
				throw ex;
			}
			//end
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil.logTheEndTransaction(transactionIdEwpd, this,
					methodName); //for serviceAccessEWPDB
	
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionIdEwpd, this, methodName);//for
																		 // serviceAccessEWPDB
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContract, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} /*catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionIdEwpd, this, methodName);//for
																		 // serviceAccessEWPDB
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject MigrationContract, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		}*/ catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionIdEwpd, this, methodName);//for
																		 // serviceAccessEWPDB
			throw new SevereException("Unhandled exception is caught", null, e);
		}


			//	MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new
			// MigrationBusinessObjectBuilder();
			List dateSegments = this.getDateSegmentDetails(String.valueOf(contractSystemId));
	
/*			//copy rules in second time migration
			if (option.equals(BusinessConstants.OPT_MIGRATE_DS)
					&& isSecondTimeMigration) {
				//	if (productChanged) {b
				if (null != dateSegments && !dateSegments.isEmpty()) {
					for (int i = 0; i < dateSegments.size(); i++) {
						migrationDateSegment = (MigrationDateSegment) dateSegments
								.get(i);
						MigrationContractRulesAssnBO migrationContractRulesAssnBO = new MigrationContractRulesAssnBO();
						migrationContractRulesAssnBO
								.setContractDateSegmentSysId(migrationDateSegment
										.getSystemId());
						this.removeDateSegmentRules(migrationContractRulesAssnBO,
								audit);
					}
				}
				//		migrationBusinessObjectBuilder.removeContractMapping(migrationProduct,
				// audit);
				//	}
	
				//copy rules from legacy to migration table
				//	if(productChanged) {
				String ewpdProductSysID = migrationContract
						.getEwpdProductSystemId();
				int ewpdProductSysId = 0;
				if (null != ewpdProductSysID
						&& !ewpdProductSysID.equals(WebConstants.EMPTY_STRING)) {
					ewpdProductSysId = Integer.parseInt(ewpdProductSysID);
				}
				//FIXME check for null if contract type in first migration is shell
				if (null != dateSegments && !dateSegments.isEmpty()) {
					for (int i = 0; i < dateSegments.size(); i++) {
						migrationDateSegment = (MigrationDateSegment) dateSegments
								.get(i);
						this.copyRulesFromLegacyToEwpdb(migrationDateSegment
								.getSystemId(), ewpdProductSysId, audit);
					}
				}
				//	}
	
			}*/
			if(migrationContract != null){
				migrationContractSession = MigrationContractUtil
					.setCreateMigrationContractSessionToResponse(migrationContract,
						dateSegments, audit);
			}else{
				migrationContractSession = null;
			}
		
		return migrationContractSession;
	}

	/**
	 * 
	 * @param migrationContract
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	public void persistMigrationContractInfo(
			MigrationContract migrationContract, Audit audit,
			boolean insertFlag, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		migrationContract.setLastUpdatedUser(audit.getUser());
		migrationContract.setLastUpdatedTimeStamp(audit.getTime());
		migrationContract.setCreatedUser(audit.getUser());
		migrationContract.setCreatedTimeStamp(audit.getTime());
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		if (insertFlag)
			migWizardAdapterManager.persistMigrationContractInfo(
					migrationContract, audit, adapterServicesAccess);
		else
			migWizardAdapterManager.updateMigrationContractInfo(
					migrationContract, audit);
	}

	/**
	 * 
	 * @param contractNo
	 * @param migrationDateSegment
	 * @param audit
	 * @throws SevereException
	 */
	public void persistMigrationDateSegmentInfo(String contractNo,
			MigrationDateSegment migrationDateSegment, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		migrationDateSegment.setLastUpdatedUser(audit.getUser());
		migrationDateSegment.setLastUpdatedTimeStamp(audit.getTime());
		migrationDateSegment.setCreatedUser(audit.getUser());
		migrationDateSegment.setCreatedTimeStamp(audit.getTime());
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		//		AdapterServicesAccess serviceAccessEWPDB =
		// AdapterUtil.getAdapterService();
		//		long transactionId = AdapterUtil.getTransactionId();
		//		try{
		//		    AdapterUtil.beginTransaction(serviceAccessEWPDB);
		//		    AdapterUtil.logBeginTranstn(transactionId , this
		// ,"persistMigrationDateSegmentInfo(String
		// contractNo,MigrationDateSegment migrationDateSegment, Audit audit)");
		migWizardAdapterManager.persistMigrationDateSegmentInfo(contractNo,
				migrationDateSegment, audit, adapterServicesAccess);
		//			AdapterUtil.endTransaction(serviceAccessEWPDB);
		//			AdapterUtil.logTheEndTransaction(transactionId , this
		// ,"persistMigrationDateSegmentInfo(String
		// contractNo,MigrationDateSegment migrationDateSegment, Audit audit)");
		/*
		 * }catch (SevereException ex) {
		 * AdapterUtil.abortTransaction(serviceAccessEWPDB);
		 * AdapterUtil.logAbortTxn(transactionId , this
		 * ,"persistMigrationDateSegmentInfo(String
		 * contractNo,MigrationDateSegment migrationDateSegment, Audit audit)");
		 * List errorParams = new ArrayList(); String obj =
		 * ex.getClass().getName(); errorParams.add(obj);
		 * errorParams.add(obj.getClass().getName()); throw new SevereException(
		 * "Exception occured in persist method , for persisting the
		 * BusinessObject LegacyVariableInfo, in
		 * MigrationBusinessObjectBuilder", errorParams, ex);
		 * }catch(AdapterException ex){
		 * AdapterUtil.abortTransaction(serviceAccessEWPDB);
		 * AdapterUtil.logAbortTxn(transactionId , this
		 * ,"persistMigrationDateSegmentInfo(String
		 * contractNo,MigrationDateSegment migrationDateSegment, Audit audit)");
		 * List errorParams = new ArrayList(); String obj =
		 * ex.getClass().getName(); errorParams.add(obj);
		 * errorParams.add(obj.getClass().getName()); throw new SevereException(
		 * "Exception occured in persist method , for persisting the
		 * BusinessObject LegacyVariableInfo, in
		 * MigrationBusinessObjectBuilder", errorParams, ex); } catch (Exception
		 * e){ AdapterUtil.abortTransaction(serviceAccessEWPDB);
		 * AdapterUtil.logAbortTxn(transactionId , this
		 * ,"persistMigrationDateSegmentInfo(String
		 * contractNo,MigrationDateSegment migrationDateSegment, Audit audit)");
		 * throw new SevereException("Unhandled exception is caught",null,e); }
		 */
	}

	/**
	 * 
	 * @param migrationContract
	 * @return
	 * @throws SevereException
	 */
	public List getMigrationDateSeg(MigrationContract migrationContract)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.retreiveDateSeg(migrationContract);
	}

	/**
	 * 
	 * @param migrationContract
	 * @return
	 * @throws SevereException
	 */
	public List getMigrationDateSegment(MigrationContract migrationContract)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.retreiveDateSegment(migrationContract);
	}

	/**
	 * 
	 * @param legacyContract
	 * @return
	 * @throws SevereException
	 */
	public List getMigrationContract(LegacyContract legacyContract)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager
				.retrieveMigrationContract(legacyContract);
	}

	/**
	 * 
	 * @param contractId
	 * @return list
	 * @throws SevereException
	 */
	public List getMigrationContract(String contractId) throws SevereException {
		return this.adapterManager.retrieveMigrationContract(contractId);
	}

	/**
	 * @param migContrSysId
	 * @param mappingSysId
	 * @param user
	 * @return List
	 * @throws WPDException
	 */
	public List retrieveConfirmDataForInsert(int migContrSysId,
			int mappingSysId, User user) throws SevereException {

		HashMap map = new HashMap();
		map.put("migContractSysId", new Integer(migContrSysId));
		map.put("mappingSysId", new Integer(mappingSysId));
		map.put("prodStrMapSysId", new Integer(mappingSysId));
		List insertDataList = adapterManager.retrieveConfirmDataForInsert(map);

		return insertDataList;
	}

	/**
	 * @param confirmMigSaveDataList
	 * @param user
	 * @param flag
	 */
	public void saveConfirmMigrationData(List confirmMigSaveDataList,
			User user, String flag) throws SevereException {
		if ((confirmMigSaveDataList != null)
				&& (!confirmMigSaveDataList.isEmpty())) {
			Iterator confirmMigSaveDataIterator = confirmMigSaveDataList
					.iterator();
			while (confirmMigSaveDataIterator.hasNext()) {

				ConfirmMigrationContractForSave confirmMigrationContractForSave = (ConfirmMigrationContractForSave) confirmMigSaveDataIterator
						.next();
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
						.getFactory(ObjectFactory.AUDIT);
				Audit audit = auditFactory.getAudit(user);
				confirmMigrationContractForSave.setLastUpdatedTimestamp(audit
						.getTime());
				confirmMigrationContractForSave.setLastUpdatedUser(audit
						.getUser());
				MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

				AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
						.getAdapterService();
				long transactionId = AdapterUtil.getTransactionId();
				try {
					AdapterUtil.beginTransaction(serviceAccessEWPDB);
					AdapterUtil
							.logBeginTranstn(transactionId, this,
									"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
					if (flag.equals("insert")) {
						confirmMigrationContractForSave.setCreatedUser(audit
								.getUser());
						confirmMigrationContractForSave
								.setCreatedTimestamp(audit.getTime());
						migWizardAdapterManager.insertConfirmedMigrationData(
								confirmMigrationContractForSave, audit
										.getUser(), serviceAccessEWPDB);
					} else if (flag.equals("update")) {
						migWizardAdapterManager.updateConfirmedMigrationData(
								confirmMigrationContractForSave, audit
										.getUser(), serviceAccessEWPDB);
					}

					AdapterUtil.endTransaction(serviceAccessEWPDB);
					AdapterUtil
							.logTheEndTransaction(transactionId, this,
									"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
				} catch (SevereException ex) {
					AdapterUtil.abortTransaction(serviceAccessEWPDB);
					AdapterUtil
							.logAbortTxn(transactionId, this,
									"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
					List errorParams = new ArrayList(3);
					String obj = ex.getClass().getName();
					errorParams.add(obj);
					errorParams.add(obj.getClass().getName());
					throw new SevereException(
							"Exception occured in persist method , for persisting the BusinessObject ConfirmMigrationContractForSave, in MigrationBusinessObjectBuilder",
							errorParams, ex);
				} catch (AdapterException ex) {
					AdapterUtil.abortTransaction(serviceAccessEWPDB);
					AdapterUtil
							.logAbortTxn(transactionId, this,
									"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
					List errorParams = new ArrayList(3);
					String obj = ex.getClass().getName();
					errorParams.add(obj);
					errorParams.add(obj.getClass().getName());
					throw new SevereException(
							"Exception occured in persist method , for persisting the BusinessObject ConfirmMigrationContractForSave, in MigrationBusinessObjectBuilder",
							errorParams, ex);
				} catch (Exception e) {
					AdapterUtil.abortTransaction(serviceAccessEWPDB);
					AdapterUtil
							.logAbortTxn(transactionId, this,
									"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
					throw new SevereException("Unhandled exception is caught",
							null, e);
				}
			}
		}
	}

	/**
	 * @param migContrSysId
	 * @param mappingSysId
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public List retrieveConfirmDataForUpdate(int migContrSysId,
			int mappingSysId, User user) throws SevereException {
		HashMap map = new HashMap();
		map.put("migContractSysId", new Integer(migContrSysId));
		map.put("mappingSysId", new Integer(mappingSysId));
		map.put("prodStrMapSysId", new Integer(mappingSysId));
		List updateDataList = adapterManager.retrieveConfirmDataForUpdate(map);

		return updateDataList;
	}

	/**
	 * 
	 * @param migrationcontractSysID
	 * @param ewpdContractSysID
	 * @param user
	 * @throws SevereException
	 */
	public void confirmedMigrationDataToEWPD(String migrationcontractSysID,
			int ewpdContractSysID, User user) throws SevereException {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();

//		List migrationDateSegmentList = new ArrayList();
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)"); //for
																															   // serviceAccessEWPDB
			List migrationDateSegmentList = adapterManager.confirmedMigrationData(
					migrationcontractSysID, ewpdContractSysID,
					"Contract Migrated from CP2000", audit, serviceAccessEWPDB);

			// Migrate the notes from Legacy to Ewpd
			//migrateNotes(Integer.parseInt(migrationcontractSysID),
					//ewpdContractSysID, audit, serviceAccessEWPDB);

			if (null != migrationDateSegmentList
					&& !migrationDateSegmentList.isEmpty()) {
				MigrationContract migrationContract = new MigrationContract();
				migrationContract.setMigrationSystemId(migrationcontractSysID);
				migrationContract = retrieve(migrationContract);
				LegacyContract legacyContract = new LegacyContract(
						LegacyObject.SYSTEM_CP2000);
				legacyContract.setContractId(migrationContract.getContractId());
				legacyContract.setLastUpdatedUser(user.getUserId());

				AdapterServicesAccess legacyServiceAccess = legacyBuilder
						.getAdapterServiceAccess(LegacyObject.SYSTEM_CP2000);
				long transactionIdCp2000 = AdapterUtil.getTransactionId();
				AdapterUtil.beginTransaction(legacyServiceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionIdCp2000,
								this,
								"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)"); // for
																																   // legacyServiceAccess
				try {
					 if (migrationContract.getOption().equals(
							BusinessConstants.OPT_RENEW_DS)) {
						// Renew Date Segment Option
						MigrationDateSegment migrationDateSegment = (MigrationDateSegment) migrationDateSegmentList
								.get(0);
						legacyContract.setStartDate(migrationDateSegment
								.getLegacyStartDate());
						legacyBuilder.finalizeMigrationForRenewal(
								legacyContract, migrationDateSegment
										.getEffectiveDate(),
								legacyServiceAccess);

					} else if (migrationContract.getOption().equals(
							BusinessConstants.OPT_MIGRATE_DS)) {
						// Migrate Latest two date Segments Option
						legacyBuilder.finalizeMigrationForDateSegments(
								legacyContract,
								LegacyBuilder.OPT_MIGRATION,
								legacyServiceAccess);
					} else {
						throw new IllegalArgumentException(
								"Invalid option found");
					}
					AdapterUtil.endTransaction(legacyServiceAccess);
					AdapterUtil
							.logTheEndTransaction(
									transactionIdCp2000,
									this,
									"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)");// for
																																	  // legacyServiceAccess
				} catch (Exception ex) {
					AdapterUtil.abortTransaction(legacyServiceAccess);
					AdapterUtil
							.logAbortTxn(
									transactionIdCp2000,
									this,
									"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)");// for
																																	  // legacyServiceAccess
					throw ex;
				}
			}
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)"); //for
																															   // serviceAccessEWPDB


		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)");//for
																															  // serviceAccessEWPDB
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject CP2000Contract, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)");//for
																															  // serviceAccessEWPDB
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject CP2000Contract, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"confirmedMigrationDataToEWPD(String migrationcontractSysID, int ewpdContractSysID,  User user)");//for
																															  // serviceAccessEWPDB
			throw new SevereException("Unhandled exception is caught", null, e);
		}
	}

	public void migrateNotes(int migContractSysId, int ewpdContractSysId,
			Audit audit, AdapterServicesAccess access) throws SevereException {
		List migratedDateSegments = getMigratedDateSegmentInfo(
				migContractSysId, ewpdContractSysId);
		for (Iterator iter = migratedDateSegments.iterator(); iter.hasNext();) {
			MigratedDateSegmentInfo migratedDateSegmentInfo = (MigratedDateSegmentInfo) iter
					.next();
			migratedDateSegmentInfo.setAudit(audit);
			migratedDateSegmentInfo.setAccess(access);

			migrateNotes(migratedDateSegmentInfo);

			// Setting migrate flag to 'Y' to indicate migration completed.
			AdapterUtil.performUpdate(migratedDateSegmentInfo, audit.getUser(),
					access);
		}
	}

	public List getMigratedDateSegmentInfo(int migContractSysId,
			int ewpdContractSysId) throws SevereException {
		HashMap inputValues = new HashMap();
		inputValues.put("migContractSysId", new Integer(migContractSysId));
		inputValues.put("ewpdContractSysId", new Integer(ewpdContractSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				MigratedDateSegmentInfo.class.getName(),
				"getMigratedDateSegments", inputValues);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	private void migrateNotes(MigratedDateSegmentInfo dateSegment)
			throws SevereException {
		String contractId = dateSegment.getContractId();
		int ewpdDateSegSysId = dateSegment.getEwpdDateSegSysId();
		int ewpdContractSysId = dateSegment.getEwpdContractSysId();
		int migDateSegSysId = dateSegment.getMigDateSegSysId();
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		Audit audit = dateSegment.getAudit();
		final String CONTRACT_STATUS = "%";
		AdapterServicesAccess serviceAccessEwpd = dateSegment.getAccess();

		// Migrating Contract Level note.
		if (dateSegment.getMigrateContractNoteFlag() != null
				&& dateSegment.getMigrateContractNoteFlag().equals("Y")) {
			LegacyContract contract = new CP2000Contract();
			contract.setContractId(contractId);
			contract.setStartDate(dateSegment.getLegacyStartDate());
			contract = legacyBuilder.retrieveDateSegment(contract,
					LegacyBuilder.OPT_DS_BASIC_INFO, CONTRACT_STATUS);
			if (contract.getNote() != null) {
				NoteBO note = createEwpdNote(contract.getNote(), audit,
						serviceAccessEwpd);
				domainAndAttachNoteToContract(note, ewpdDateSegSysId, audit,
						serviceAccessEwpd);
			}
		}

		// Migrating Major heading notes according to the mappings.
		List componentMappings = getComponentNoteMappings(migDateSegSysId);
		for (Iterator iter = componentMappings.iterator(); iter.hasNext();) {
			BenefitComponentNote componentNote = (BenefitComponentNote) iter
					.next();
			migrateComponentNote(componentNote, dateSegment);
		}

		// Migrating Minor heading notes according to the mappings.
		List benefitMappings = getBenefitNoteMappings(migDateSegSysId);
		for (Iterator iter = benefitMappings.iterator(); iter.hasNext();) {
			BenefitNote benefitNote = (BenefitNote) iter.next();
			migrateBenefitNote(benefitNote, dateSegment);
		}

		// Migrating variable notes according to the mappings.
		List lineMappings = getLineNoteMappings(migDateSegSysId);
		for (Iterator iter = lineMappings.iterator(); iter.hasNext();) {
			BenefitLineNote lineNote = (BenefitLineNote) iter.next();
			migrateLineNotes(lineNote, dateSegment);
		}
	}

	private void migrateComponentNote(BenefitComponentNote componentNote,
			MigratedDateSegmentInfo dateSegment) throws SevereException {
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		String noteText = legacyBuilder.getMajorHeadingNote(dateSegment
				.getContractId(), dateSegment.getLegacyStartDate(),
				componentNote.getMajorHeadingId());
		if (noteText != null) {
			NoteBO note = createEwpdNote(noteText, dateSegment.getAudit(),
					dateSegment.getAccess());
			attachNote(dateSegment, note, BusinessConstants.ATTACH_COMPONENT, componentNote
					.getBenefitCompSysId(),
					componentNote.getBenefitCompSysId(), null);
		}
	}

	public void migrateBenefitNote(BenefitNote benefitNote,
			MigratedDateSegmentInfo dateSegment) throws SevereException {
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		String noteText = legacyBuilder.getMinorHeadingNote(dateSegment
				.getContractId(), dateSegment.getLegacyStartDate(), benefitNote
				.getMinorHeadingId());
		if (noteText != null) {
			NoteBO note = createEwpdNote(noteText, dateSegment.getAudit(),
					dateSegment.getAccess());
			attachNote(dateSegment, note, BusinessConstants.ATTACH_BENEFIT, benefitNote
					.getBenefitSysId(), benefitNote.getBenefitCompSysId(), null);
		}
	}

	public void migrateLineNotes(BenefitLineNote benefitLineNote,
			MigratedDateSegmentInfo dateSegment) throws SevereException {
		LegacyBuilder legacyBuilder = new LegacyBuilder();
		String noteText = legacyBuilder.getVariableNote(dateSegment
				.getContractId(), dateSegment.getLegacyStartDate(),
				benefitLineNote.getLegacyVariableId());
		if (noteText != null) {
			NoteBO note = createEwpdNote(noteText, dateSegment.getAudit(),
					dateSegment.getAccess());
			String defnSysId = (benefitLineNote.getBnftDefnSysId() == 0) ? null
					: String.valueOf(benefitLineNote.getBnftDefnSysId());
			attachNote(dateSegment, note, BusinessConstants.ATTACH_BENEFITLINE, benefitLineNote
					.getBenefitLineSysId(), benefitLineNote
					.getBenefitCompSysId(), defnSysId);
		}
	}

	private NotesAttachmentOverrideBO attachNote(
			MigratedDateSegmentInfo dateSegment, NoteBO note, String entyType,
			int entyId, int benCompSysId, String defnId) throws SevereException {
		Audit audit = dateSegment.getAudit();
		NotesAttachmentOverrideBO noteOverride = new NotesAttachmentOverrideBO();
		// Primary entity
		noteOverride.setPrimaryEntityId(dateSegment.getEwpdDateSegSysId());
		noteOverride.setPrimaryEntityType(BusinessConstants.ATTACH_CONTRACT);
		// Secondary entity
		noteOverride.setSecondaryEntityId(entyId);
		noteOverride.setSecondaryEntityType(entyType);
		// Benefit component Id
		noteOverride.setBenefitComponentId(benCompSysId);
		// Note details
		noteOverride.setNoteId(note.getNoteId());
		noteOverride.setVersion(note.getVersion());
		// Override status - F -> Attached
		noteOverride.setOverrideStatus("F");
		// Audit fields
		noteOverride.setCreatedUser(audit.getUser());
		noteOverride.setCreatedTimestamp(audit.getTime());
		noteOverride.setLastUpdatedUser(audit.getUser());
		noteOverride.setLastUpdatedTimestamp(audit.getTime());
		// Defenition sys id.
		noteOverride.setBnftDefnIdString(defnId);
		AdapterUtil.performInsert(noteOverride, audit.getUser(), dateSegment
				.getAccess());
		return noteOverride;
	}

	public List getComponentNoteMappings(int migDateSegmentSysId)
			throws SevereException {
		HashMap inputValues = new HashMap();
		inputValues.put("migDateSegSysId", new Integer(migDateSegmentSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitComponentNote.class.getName(), "getAllComponentsForaDS",
				inputValues);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public List getBenefitNoteMappings(int migDateSegmentSysId)
			throws SevereException {
		HashMap inputValues = new HashMap();
		inputValues.put("migDateSegSysId", new Integer(migDateSegmentSysId));
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(BenefitNote.class.getName(),
						"getAllMappingsForDS", inputValues);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public List getLineNoteMappings(int migDateSegmentSysId)
			throws SevereException {
		HashMap inputValues = new HashMap();
		inputValues.put("migDateSegSysId", new Integer(migDateSegmentSysId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitLineNote.class.getName(), "getLineMappingsForDS",
				inputValues);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	private void domainAndAttachNoteToContract(NoteBO note, int wpdDateSegSysId,
			Audit audit, AdapterServicesAccess access) throws SevereException {
		final String CONT_NOTE_AVAIL = "LEGACYCONTRACT";
		final String CONT_NOTE_ATTACH = "ATTACHCONTRACT";
		NoteDomainBO noteDomainBO = new NoteDomainBO();
		noteDomainBO.setNoteId(note.getNoteId());
		//		noteDomainBO.setEntityId(String.valueOf(wpdDateSegSysId));
		noteDomainBO.setVersion(note.getVersion());
		noteDomainBO.setCreatedUser(audit.getUser());
		noteDomainBO.setCreatedTimestamp(audit.getTime());
		noteDomainBO.setLastUpdatedUser(audit.getUser());
		noteDomainBO.setLastUpdatedTimestamp(audit.getTime());

		// Setting some values to pass adapter key validation
		noteDomainBO.setSystemDomainId(" ");

		// System domain ids actually represents entity sys id in the table.
		List list = new ArrayList();
		list.add(String.valueOf(wpdDateSegSysId));
		noteDomainBO.setSystemDomainIds(list);

		// Domaining note to contract
		noteDomainBO.setEntityType(CONT_NOTE_AVAIL);
		AdapterUtil.performInsert(noteDomainBO, audit.getUser(), access);

		// Attaching note to contract
		noteDomainBO.setEntityType(CONT_NOTE_ATTACH);
		AdapterUtil.performInsert(noteDomainBO, audit.getUser(), access);
	}

	public NoteBO createEwpdNote(String noteText, Audit audit,
			AdapterServicesAccess access) throws SevereException {
		List targetSystems = new ArrayList();
		targetSystems.add("81");//WGS
		SequenceAdapterManager adapterManager = new SequenceAdapterManager();
		int nextSeq = adapterManager.getNextMigratedNoteSequence();
		String noteId;

		// convert to string and concat with "N" in the front
		noteId = "L" + lpad((new Integer(nextSeq)).toString(), 5);

		NoteBO noteBo = new NoteBO();
		noteBo.setNoteId(noteId);
		noteBo.setNoteParentId(noteId);
		String noteName = noteText.length() >= 15 ? noteText.substring(0, 15)
				: noteText;
		noteBo.setNoteName(noteName);
		noteBo.setSystemDomain(targetSystems);
		noteBo.setNoteText(noteText.length() > 3000 ? noteText.substring(0,
				3000) : noteText);
		noteBo.setNoteType("82"); //CUSTOM CERTIFICATE LANGUAGE
		noteBo.setNoteSystemDomain(targetSystems);
		noteBo.setVersion(1);
		noteBo.setStatus("PUBLISHED");
		noteBo.setCreatedUser(audit.getUser());
		noteBo.setCreatedTimestamp(audit.getTime());
		noteBo.setLastUpdatedUser(audit.getUser());
		noteBo.setLastUpdatedTimestamp(audit.getTime());
		noteBo.setLegacyIndicator("Y");
		AdapterUtil.performInsert(noteBo, audit.getUser(), access);
		return noteBo;
	}

	private String lpad(String seqNo, int maxLength) {
		for (int i = seqNo.length(); i < maxLength; i++) {
			seqNo = "0" + seqNo;
		}
		return seqNo;
	}

	/**
	 * @param migrationSystemId
	 * @throws SevereException
	 */
	public List getDateSegmentDetails(String migrationSystemId)
			throws SevereException {
		HashMap map = new HashMap();

		map.put("contractSysId", new Integer(migrationSystemId));
		List dateSegmentDetails = adapterManager
				.retrieveDateSegmentDetails(map);
		return dateSegmentDetails;

	}

	/**
	 * @param migrationVariableForDelete
	 * @param user
	 * @throws SevereException
	 */
	public void deleteVariableMappingList(
			MigrationVariableInsert migrationVariableForDelete, User user)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		// AuditFactory auditFactory = (AuditFactory) ObjectFactory
		// 		.getFactory(ObjectFactory.AUDIT);
		//	Audit audit = auditFactory.getAudit(user);
		migWizardAdapterManager.deleteVariableMappingList(
				migrationVariableForDelete, user.getUserId());
	}

	/**
	 * Function to Update a Migration Product Structure lock.
	 * 
	 * @param mappingUpdate
	 * @param audit
	 * @throws SevereException
	 */
	public void updateMigrationProductStructureLockStatus(
			MigrationProductStructureMappingUpdate mappingUpdate, Audit audit)
			throws SevereException {
		mappingUpdate.setLastUpdatedUser(audit.getUser());
		mappingUpdate.setLastUpdatedTimestamp(audit.getTime());
		adapterManager.updateMigrationProductStructureLockStatus(mappingUpdate,
				audit.getUser());
	}
	
	static final int ACQUIRE_LOCK = 1;
	static final int RELEASE_LOCK = 2;
	
	public boolean acquireLockOnMapping(int mappingSysId, Audit audit) throws SevereException {
		if(manageLockOnMapping(mappingSysId, audit, ACQUIRE_LOCK) == true)
			return true;
		else
			return false;
	}
	
	public boolean releaseLockOnMapping(int mappingSysId, Audit audit) throws SevereException {
		if(manageLockOnMapping(mappingSysId, audit, RELEASE_LOCK) == true)
			return true;
		else
			return false;
		
	}
	
	public static synchronized boolean manageLockOnMapping(int mappingSysId, Audit audit, int action) throws SevereException {
  		MigrationAdapterManager migAdapterManager = new MigrationAdapterManager();
  		MigrationProductStructureMappingUpdate mappingUpdate = new MigrationProductStructureMappingUpdate();
		mappingUpdate.setLastUpdatedUser(audit.getUser());
		mappingUpdate.setLastUpdatedTimestamp(audit.getTime());
		
		switch(action) {
			// Lock
			case ACQUIRE_LOCK:
				if(migAdapterManager.isMigratedProductStructureLocked(mappingSysId)) {
					// Mapping already locked. Not able to acquire Lock.
					return false;
				} else {
					// Mapping Not locked. Lock  the mapping and return true;
					mappingUpdate.setMigratedProdStructureMappingSysID(mappingSysId);
					mappingUpdate.setProdStructureLockStatus("Y");
					migAdapterManager.updateMigrationProductStructureLockStatus(mappingUpdate, audit.getUser());
					return true;
				}
			// Unlock
			case RELEASE_LOCK:
				mappingUpdate.setMigratedProdStructureMappingSysID(mappingSysId);
				mappingUpdate.setProdStructureLockStatus("N");				
				migAdapterManager.updateMigrationProductStructureLockStatus(mappingUpdate,	audit.getUser());
				return true;
				
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * 
	 * @param migratedProdStructureMappingSysID
	 * @return
	 * @throws SevereException
	 */
	public boolean isMigratedProductStructureLocked(
			int migratedProdStructureMappingSysID) throws SevereException {
		return adapterManager
				.isMigratedProductStructureLocked(migratedProdStructureMappingSysID);
	}

	/**
	 * 
	 * @param catalogId
	 * @param itemId
	 * @return
	 * @throws SevereException
	 */
	public boolean validateItemField(int catalogId, String itemId)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		List itemList = migWizardAdapterManager.validateItemField(catalogId,
				itemId);
		if (null != itemList && itemList.size() > 0)
			return true;

		return false;
	}

	/**
	 * Method to retrieve product info
	 * 
	 * @param productSysId
	 * @param sourceSystemName
	 * @param structureName
	 * @return
	 * @throws SevereException
	 */
	public List retrieveProductInfo(int productSysId, String sourceSystemName,
			String structureName) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		List productMappingList = migWizardAdapterManager.retrieveProductInfo(
				productSysId, sourceSystemName, structureName);
		return productMappingList;
	}

	/**
	 * @param confirmMigrationContractForSave
	 * @param user
	 * @throws SevereException
	 */
	public void deleteVariableMappingMasterList(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			User user) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		migWizardAdapterManager.deleteVariableMappingMasterList(
				confirmMigrationContractForSave, audit.getUser());

	}

	/**
	 * 
	 * @param migrationContractRulesAssnBO
	 * @param audit
	 * @throws SevereException
	 */
	public void removeDateSegmentRules(
			MigrationContractRulesAssnBO migrationContractRulesAssnBO,
			Audit audit) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.removeDateSegments(
				migrationContractRulesAssnBO, audit.getUser());
	}

	/**
	 * 
	 * @param migrationProduct
	 * @param audit
	 * @throws SevereException
	 */
	public void removeContractMapping(MigrationProduct migrationProduct,
			Audit audit) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		migWizardAdapterManager.removeContractMapping(migrationProduct, audit
				.getUser());
	}

	/**
	 * @param map
	 * @param user
	 * @return list
	 * @throws SevereException
	 */
	public List getConflictRecordList(HashMap map, User user)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.getConflictRecordList(map);
	}

	/**
	 * @param confirmMigrationContractForSave
	 * @param user
	 * @throws SevereException
	 */
	public void deleteFromTempContractMapping(
			ConfirmMigrationContractForSave confirmMigrationContractForSave,
			User user) throws SevereException {

		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

		migWizardAdapterManager.deleteFromTempContractMapping(
				confirmMigrationContractForSave, user.getUserId());

	}

	/**
	 * 
	 * @param contractID
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isContractExistingInEwpdb(String contractID)
			throws SevereException {
		ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder();
		return contractBusinessObjectBuilder.searchContractId(contractID);
	}

	/**
	 * 
	 * @param map
	 * @return list
	 * @throws SevereException
	 */
	public List retrievePossibleAnswer(HashMap map) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.retrievePossibleAnswer(map);
	}

	/**
	 * To update the overridded values of the administration option questions
	 * 
	 * @param productAdministration
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean persistProductAdministrationValues(
			MigrationProductAdministration productAdministration, Audit audit)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		productAdministration.setLastUpdatedUser(audit.getUser());
		productAdministration.setLastUpdatedTimestamp(audit.getTime());
		//    	productAdministration.setCreatedUser(audit.getUser());
		//    	productAdministration.setCreatedTimestamp(audit.getTime());
		return migWizardAdapterManager.persistProductAdministrationValues(
				productAdministration, audit.getUser());
	}

	/**
	 * @param migDS
	 * @param audit
	 * @throws SevereException
	 */
	public List getProductAttachedToBaseContract(MigrationDateSegment migDS,
			Audit audit) throws SevereException {

		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

		return migWizardAdapterManager.getProductAttachedToBaseContract(migDS,
				audit.getUser());

	}

	/**
	 * @param migDS
	 * @param audit
	 * @return
	 */
	public List getMappedProductID(MigrationDateSegment migDS, Audit audit)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

		return migWizardAdapterManager.getMappedProductID(migDS, audit
				.getUser());
	}

	/**
	 * @param type
	 * @param entitySysId
	 * @return String
	 * @throws SevereException
	 */
	public String getPlanRenewalType(String type, int entitySysId)
			throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
		return migWizardAdapterManager.getPlanRenewalType(type, entitySysId);
	}
	
	public String getPlanRenewalTypeForProduct(String type, int entitySysId)
		throws SevereException {
	    	MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();
	    		return migWizardAdapterManager.getPlanRenewalTypeForProduct(type, entitySysId);
	}
	
	

	/**
	 * @param benefitLineNote
	 * @param migrateNotesBO
	 * @param audit
	 * @return
	 */
	public List searchBenefitsForMigration(MigrateNotesBO migrateNotesBO,
			Audit audit) throws SevereException {
		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
		List list = null;
		int baseDateSegId = 0;
		if(!StringUtil.isEmpty(migrateNotesBO.getBaseDateSegId())){
			baseDateSegId = Integer.parseInt(migrateNotesBO.getBaseDateSegId());
		}
		if(baseDateSegId==0){
			list = migrationAdapterManager.searchBenefitsForMigration(		
				migrateNotesBO, audit);
		}else{
			list = migrationAdapterManager.searchBenefitsForMigrationWithBaseDateSegment(		
					migrateNotesBO, audit);
		}
		return list;

	}

	/**
	 * @param benefitComponentNote
	 * @param audit
	 * @return
	 */
	public BenefitComponentNote searchBenefitComponentForMigration(
			BenefitComponentNote benefitComponentNote, Audit audit)
			throws SevereException {
		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
//		List list = new ArrayList();
		benefitComponentNote = migrationAdapterManager
				.searchBenefitComponentForMigration(benefitComponentNote, audit);
		return benefitComponentNote;
	}

	/**
	 * @param migrateNotesList
	 * @param user
	 * @throws SevereException
	 */
	public void deleteMigrateNotesInfo(BenefitLineNote benefitLineNote,
			User user) throws SevereException {
		MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

		migWizardAdapterManager.deleteMigrateNotesInfo(benefitLineNote, user
				.getUserId());

	}

	/**
	 * @param benefitNoteList
	 * @param benefitComponentNote
	 * @param user
	 * @throws SevereException
	 */
	public void saveNotesMigrationInfo(List benefitNoteList,
			BenefitComponentNote benefitComponentNote,
			MigrationDateSegment migrationDateSegment, User user)
			throws SevereException {
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"saveNotesMigrationInfo(List benefitNoteList, BenefitComponentNote benefitComponentNote,MigrationDateSegment migrationDateSegment, User user)");
			AuditFactory auditFactory = (AuditFactory) ObjectFactory
					.getFactory(ObjectFactory.AUDIT);
			Audit audit = auditFactory.getAudit(user);
			MigrationAdapterManager migWizardAdapterManager = new MigrationAdapterManager();

			//Saving benefit component notes
			if (null != benefitComponentNote) {
				benefitComponentNote.setCreatedUser(audit.getUser());
				benefitComponentNote.setCreatedTimestamp(audit.getTime());
				benefitComponentNote.setLastUpdatedTimestamp(audit.getTime());
				benefitComponentNote.setLastUpdatedUser(audit.getUser());

				migWizardAdapterManager.deleteBenefitComponentNotes(
						benefitComponentNote, audit.getUser(),
						serviceAccessEWPDB);

				migWizardAdapterManager.saveBenefitComponentNotes(
						benefitComponentNote, audit.getUser(),
						serviceAccessEWPDB);
			}
			//Saving benefit notes
			if (null != benefitNoteList && benefitNoteList.size() > 0) {
				Iterator benefitNoteIter = benefitNoteList.iterator();
				while (benefitNoteIter.hasNext()) {
					BenefitNote benefitNote = (BenefitNote) benefitNoteIter
							.next();
					if (null != benefitNote) {
						benefitNote.setCreatedUser(audit.getUser());
						benefitNote.setCreatedTimestamp(audit.getTime());
						benefitNote.setLastUpdatedTimestamp(audit.getTime());
						benefitNote.setLastUpdatedUser(audit.getUser());

						migWizardAdapterManager.deleteBenefitNotes(benefitNote,
								audit.getUser(), serviceAccessEWPDB);
						migWizardAdapterManager.saveBenefitNotes(benefitNote,
								audit.getUser(), serviceAccessEWPDB);
					}
				}
			}

			//Saving contract level notes
			if (null != migrationDateSegment) {
				migrationDateSegment.setLastUpdatedTimeStamp(audit.getTime());
				migrationDateSegment.setLastUpdatedUser(audit.getUser());
				migWizardAdapterManager.saveContractNotes(migrationDateSegment,
						audit.getUser(), serviceAccessEWPDB);
			}

			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"saveNotesMigrationInfo(List benefitNoteList, BenefitComponentNote benefitComponentNote,	MigrationDateSegment migrationDateSegment, User user)");

		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"saveNotesMigrationInfo(List benefitNoteList, BenefitComponentNote benefitComponentNote,MigrationDateSegment migrationDateSegment, User user)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for saveNotesMigrationInfo, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"saveNotesMigrationInfo(List benefitNoteList, BenefitComponentNote benefitComponentNote,	MigrationDateSegment migrationDateSegment, User user)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for saveNotesMigrationInfo, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"saveNotesMigrationInfo(List benefitNoteList, BenefitComponentNote benefitComponentNote,	MigrationDateSegment migrationDateSegment, User user)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}

	}

	/**
	 * @param dateSegment
	 * @param audit
	 * @return
	 */
	public List searchContractForMigration(MigrationDateSegment dateSegment,
			Audit audit) throws SevereException {
		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
//		List list = new ArrayList();
		List list = migrationAdapterManager.searchContractForMigration(dateSegment,
				audit);
		return list;
	}

	/**
	 * this function persists the legacy pricing info into migration tables using procedure
	 * @param migratedDSSysID
	 */
	public void persistPricingInfo(int migratedDSSysID,Audit audit,String startDate) throws SevereException{
		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persistPricingInfo(int migratedDSSysID,Audit audit,String startDate)");

			migrationAdapterManager.persistPricingInfo(migratedDSSysID,audit.getUser(),startDate,serviceAccessEWPDB);
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persistPricingInfo(int migratedDSSysID,Audit audit,String startDate)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistPricingInfo(int migratedDSSysID,Audit audit,String startDate)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistPricingInfo(int migratedDSSysID,Audit audit,String startDate)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistPricingInfo(int migratedDSSysID,Audit audit,String startDate)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}


	}

	/**
	 * this function persists the legacy variable info into migration variable using procedure
	 * @param dateSegmentId
	 * @param audit
	 */
	public void persistVariableInfo(int dateSegmentId,String startDate ) throws SevereException{
		MigrationAdapterManager migrationAdapterManager = new MigrationAdapterManager();
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persistVariableInfo(int dateSegmentId,String startDate )");
		
			migrationAdapterManager.persistVariableInfo(dateSegmentId,startDate,serviceAccessEWPDB);
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persistVariableInfo(int dateSegmentId,String startDate )");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistVariableInfo(int dateSegmentId,String startDate )");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistVariableInfo(int dateSegmentId,String startDate )");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persistVariableInfo(int dateSegmentId,String startDate )");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
	}

	/**
	 * @param dateSegmentId
	 */
	public boolean isDateSegmentContainPricing(String dateSegmentId) throws SevereException{
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		int count = adapterManager.isDateSegmentContainPricing(new Integer(dateSegmentId).intValue());
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param i
	 */
	public boolean isMigrationProductRulesCoded(int dateSegmentId) throws SevereException{
		MigrationAdapterManager adapterManager = new MigrationAdapterManager();
		int count = adapterManager.isMigrationProductRulesCoded(dateSegmentId);
		if(count > 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @param migContrSysId
	 * @param mappingSysId
	 * @param user
	 * @throws SevereException
	 */
	public void confirmLineMappings(int migContrSysId,
			int mappingSysId, User user,String authorized) throws SevereException{
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);	
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();

		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"confirmLineMappings(List confirmMigSaveDataList, User user, String flag)");

			adapterManager.confirmLineMappings(migContrSysId, mappingSysId,audit, serviceAccessEWPDB,authorized);
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"confirmLineMappings(List confirmMigSaveDataList, User user, String flag)");

		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"confirmLineMappings(List confirmMigSaveDataList, User user, String flag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ConfirmMigrationContractForSave, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"saveConfirmMigrationData(List confirmMigSaveDataList, User user, String flag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ConfirmMigrationContractForSave, in MigrationBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"confirmLineMappings(List confirmMigSaveDataList, User user, String flag)");
			throw new SevereException("Unhandled exception is caught",
					null, e);
		}
	}
	
	/**
	 * 
	 * @param migrationProduct
	 * @param audit
	 * @throws SevereException
	 */
	public void mappingProductForNewVersion(MigrationProduct migrationProduct,
			Audit audit) throws SevereException {
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		adapterManager = new MigrationAdapterManager();
		migrationProduct.setLastUpdatedUser(audit.getUser());
		migrationProduct.setLastUpdatedTimestamp(audit.getTime());
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"mappingProductForNewVersion(MigrationProduct migrationProduct, Audit audit)");
			adapterManager.mappingProductForNewVersion(migrationProduct, audit,
					adapterServicesAccess);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"mappingProductForNewVersion(MigrationProduct migrationProduct, Audit audit)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"mappingProductForNewVersion(MigrationProduct migrationProduct, Audit audit)");
			throw new SevereException(null, null, e);
		} catch (AdapterException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"mappingProductForNewVersion(MigrationProduct migrationProduct, Audit audit)");
			throw new SevereException("Unknown Exception is caught", null, e);
		}
	}

	/** This Method is used to get Product Parent System Id. 
	 * @param  MigrationDateSegment
	 * @throws SevereException
	 */
	public  MigrationDateSegment retrieveProductParentSysId( MigrationDateSegment  migrationDateSegment) throws SevereException {				
		return adapterManager.retrieveProductParentSysId(migrationDateSegment);
	}
	
	/**
	 * @param removeProduct
	 * @param audit
	 * @throws SevereException
	 */
	public void updateAndRemoveProduct(RemoveProduct removeProduct, Audit audit) throws SevereException{
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		adapterManager = new MigrationAdapterManager();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"updateAndRemoveProduct(RemoveProduct removeProduct)");
			removeProduct.setStepNumberCompleted(3);
			adapterManager.updateAndRemoveProduct(removeProduct,audit,
					adapterServicesAccess);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"updateAndRemoveProduct(RemoveProduct removeProduct)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"updateAndRemoveProduct(RemoveProduct removeProduct)");
			throw new SevereException(null, null, e);
		} 

	}
	
}