/*
 * ProductStructureObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.BenefitAdapterManager;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.notes.adapter.NotesAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureMandatesLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureNotesLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureShowHiddenLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ViewAllVersionsLocateCriteria;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.ProductStructureBenefitCustomizedBO;
import com.wellpoint.wpd.common.productstructure.bo.EntityBenefitAdministrationPSBO;
import com.wellpoint.wpd.common.productstructure.bo.HideAdminOption;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefit;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministration;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministrationBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitDefinitions;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeAdminOptions;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitCmpnts;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitDate;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeStandardBenefits;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBusinessObjectBuilder {

	/**
	 * retrieves the ProductStructureBO by ProductStructureId
	 * 
	 * @param businessObject
	 * @return BusinessObject
	 * @throws SevereException
	 */
	public BusinessObject retrieve(ProductStructureBO productStructureBO)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		productStructureBO = adapterManager
				.retrieveByProductId(productStructureBO);
		DomainDetail businessDomain = this
				.createBusinessDomain(productStructureBO);
		List domainList = DomainUtil.retrieveAssociatedDomains(
				BusinessConstants.PRODSTRUCTURE, productStructureBO
						.getProductStructureParentId());
		productStructureBO.setBusinessDomains(domainList);
		return productStructureBO;
	}

	/**
	 * retrieves the BO with the latest version number
	 * 
	 * @param businessObject
	 * @return BusinessObject
	 * @throws SevereException
	 */
	public BusinessObject retrieveLatestVersion(
			ProductStructureBO productStructureBO) throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List domainList = productStructureBO.getBusinessDomains();
		List lob = BusinessUtil.getLobList(domainList);
		List businessEntity = BusinessUtil.getbusinessEntityList(domainList);
		List businessGroup = BusinessUtil.getBusinessGroupList(domainList);
		//CARS START
		List marketBusinessUnit = BusinessUtil.getMarketBusinessUnitList(domainList);
		productStructureBO = adapterManager.retrieveProductLatestVersion(
				productStructureBO.getProductStructureName(), lob,
				businessEntity, businessGroup,marketBusinessUnit);
		//CARS END
		List domainList_new = DomainUtil.retrieveAssociatedDomains(
				BusinessConstants.PRODSTRUCTURE, productStructureBO
						.getProductStructureParentId());
		productStructureBO.setBusinessDomains(domainList_new);
		return productStructureBO;
	}

	/**
	 * retrieves the BO with the latest version number
	 * 
	 * @param businessObject
	 * @return BusinessObject
	 * @throws SevereException
	 */
	public BusinessObject retrieveLatestVersion(
			BenefitComponentBO businessObject) throws SevereException {
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		BenefitComponentBO benefitComponentBO = (BenefitComponentBO) businessObject;
		try {
			benefitComponentBO = adapterManager
					.retrieveBenefitComponentLatestVersion(benefitComponentBO);
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveLatestVersion BenefitComponentBO method in ProductStructureBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve retrieveLatestVersion method in ProductStructureBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve retrieveLatestVersion method in ProductStructureBusinessObjectBuilder",
					null, ex);
		}
		return benefitComponentBO;
	}

	/**
	 * To update the standard definitions associated with a productStructure
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean persist(ProductStructureBenefitDefinitions subObject,
			ProductStructureBO mainObject, Audit audit, boolean insertFlag)
			throws SevereException {
		
//		TimeHandler th = new TimeHandler();
//		Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBusinessObjectBuilder","persist"));
		

		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		List updatedBenefitLineList = subObject.getUpdatedBenefitLines();
		ProductStructureBenefitCustomizedBO benefitLineBO = null;
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			if (null != updatedBenefitLineList
					&& !updatedBenefitLineList.isEmpty()) {
				for (int i = 0; i < updatedBenefitLineList.size(); i++) {

					benefitLineBO = (ProductStructureBenefitCustomizedBO) updatedBenefitLineList
							.get(i);
					if (benefitLineBO.isModified()) {
						benefitLineBO.setLastUpdatedUser(audit.getUser());
						benefitLineBO.setCreatedUser(audit.getUser());
						benefitLineBO.setLastUpdatedTimestamp(audit.getTime());
						benefitLineBO.setProductStructureSysId(mainObject
								.getProductStructureId());
						//passes each individual benefit line for updation
						benefitAdapterManager.updateDefenitionOverridenValue(
								benefitLineBO, adapterServicesAccess);
					}
					if (benefitLineBO.getBenefitHideFlag().equals(
							BusinessConstants.HIDE_FLAG_T)) {
						BenefitLine benefitLine = new BenefitLine();
						benefitLine.setLastUpdatedUser(audit.getUser());
						benefitLine.setLastUpdatedTimestamp(audit.getTime());
						benefitLine.setEntitySysId(mainObject
								.getProductStructureId());
						benefitLine.setBenefitComponentId(benefitLineBO
								.getBenefitComponentSysId());
						benefitLine.setBenefitSysId(benefitLineBO
								.getBenefitSysId());
						benefitLine.setCustomizedSysId(benefitLineBO
								.getBenefitCustomizedSysId());
						benefitLine.setBenefitHide(benefitLineBO
								.getBenefitHideFlag());
						benefitAdapterManager.updateDefenitoinOverridenValue(
								benefitLine, adapterServicesAccess);
					}

				}
			}

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitDefinitions, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitDefinitions, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
//		Logger.logInfo(th.endPerfLogging());
		return true;

	}

	/**
	 * To delete the Benefit definitions associated with a productStructure
	 * 
	 * @param mainObject
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean delete(ProductStructureBenefitDefinitions subObject,
			ProductStructureBO mainObject, Audit audit) throws SevereException {

		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		List benefitLevelsToBeDeleted = subObject.getDeletedBenefitLevels();
		BenefitLine benefitLineBO = null;
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit)");
			if (null != benefitLevelsToBeDeleted
					&& !benefitLevelsToBeDeleted.isEmpty()) {
				for (int i = 0; i < benefitLevelsToBeDeleted.size(); i++) {
					benefitLineBO = (BenefitLine) benefitLevelsToBeDeleted
							.get(i);
					benefitLineBO
							.setEntityType(BusinessConstants.PRODSTRUCTURE);
					benefitLineBO.setEntitySysId(mainObject
							.getProductStructureId());
					benefitAdapterManager.deleteBenefitDefinitionLevel(
							benefitLineBO, audit.getUser(),
							adapterServicesAccess);

				}
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitDefinitions, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitDefinitions, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureBenefitDefinitions subObject,ProductStructureBO mainObject, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

	/**
	 * retrieves the BO with the latest version number
	 * 
	 * @param businessObject
	 * @return BusinessObject
	 * @throws SevereException
	 */
	public int retrieveLatestVersionNumber(ProductStructureBO productStructureBO)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List domainList = productStructureBO.getBusinessDomains();
		List lob = BusinessUtil.getLobList(domainList);
		List businessEntity = BusinessUtil.getbusinessEntityList(domainList);
		List businessGroup = BusinessUtil.getBusinessGroupList(domainList);
		//CARS START
		List marketBusinessUnit = BusinessUtil.getMarketBusinessUnitList(domainList);
		//CARS END
		return adapterManager.retrieveProductLatestVersionNumber(
				productStructureBO.getProductStructureName(), lob,
				businessEntity, businessGroup, marketBusinessUnit);
	}

	/**
	 * Persists the ProductStructureBO
	 * 
	 * @param productStructureBO
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException,AdapterException
	 * @throws AdapterException
	 */
	public boolean persist(ProductStructureBO productStructureBO, Audit audit,
			boolean insertFlag) throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
			productStructureBO.setLastUpdatedUser(audit.getUser());
			productStructureBO.setLastUpdatedTimestamp(audit.getTime());
			if (insertFlag) {
				SequenceAdapterManager sequenceManager = new SequenceAdapterManager();
				productStructureBO.setProductStructureId(sequenceManager
						.getNextProductStructureSequence());

				productStructureBO.setCreatedUser(audit.getUser());
				productStructureBO.setCreatedTimestamp(audit.getTime());

				if (productStructureBO.getVersion() <= 1) {
					productStructureBO
							.setProductStructureParentId(productStructureBO
									.getProductStructureId());
				}
				productStructureAdapterManager.createProductStructure(
						productStructureBO, adapterServicesAccess);
			} else {
				String oldProdFamily = productStructureAdapterManager.getProductFamily(productStructureBO.getProductStructureId());
				String newProdFamily =  productStructureBO.getProductFamilyId();
				productStructureAdapterManager.updateProductStructure(
						productStructureBO, adapterServicesAccess);
				if(oldProdFamily != null && newProdFamily != null && !oldProdFamily.equalsIgnoreCase(newProdFamily)) {
					productStructureAdapterManager.refreshQuestionnaire(productStructureBO.getProductStructureId(),0,audit.getUser(), adapterServicesAccess);
				}				
			}
			if (productStructureBO.getVersion() <= 1) {
				DomainDetail businessDomain = createBusinessDomain(productStructureBO);
				DomainUtil.persistAssociatedDomains(businessDomain,
						adapterServicesAccess);
			}
			productStructureAdapterManager
					.retrieveByProductId(productStructureBO);
			List domainList = DomainUtil.retrieveAssociatedDomains(
					BusinessConstants.PRODSTRUCTURE, productStructureBO
							.getProductStructureParentId());
			productStructureBO.setBusinessDomains(domainList);

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}
	
	public boolean persistTimeStamp(ProductStructureBO productStructureBO, Audit audit) throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		try {
			
			productStructureBO.setLastUpdatedUser(audit.getUser());
			productStructureBO.setLastUpdatedTimestamp(audit.getTime());				
			
			productStructureAdapterManager.updateProductStructure(
						productStructureBO);
	
		} catch (AdapterException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

	/**
	 * Method to set the values in domainDetial
	 * 
	 * @param productStructureBO
	 * @return businessDomain DomainDetail
	 */
	private DomainDetail createBusinessDomain(
			ProductStructureBO productStructureBO) {
		DomainDetail businessDomain = new DomainDetail();
		businessDomain.setEntityName(productStructureBO
				.getProductStructureName());
		businessDomain.setEntityParentId(productStructureBO
				.getProductStructureParentId());
		businessDomain.setEntityType(BusinessConstants.PRODSTRUCTURE);
		businessDomain.setDomainList(productStructureBO.getBusinessDomains());
		businessDomain.setLastUpdatedTimeStamp(productStructureBO
				.getLastUpdatedTimestamp());
		businessDomain.setLastUpdatedUser(productStructureBO
				.getLastUpdatedUser());
		return businessDomain;
	}

	/**
	 * Persists the subObject
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean persist(EntityBenefitAdministrationPSBO subObject,
			ProductStructureBO mainObject, Audit audit, boolean insertFlag)
			throws SevereException {

		// Get the BOList from the BO
		List benefitAdministrationList = (List) subObject
				.getBenefitAdministrationList();

		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();

		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(EntityBenefitAdministrationPSBO subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");

			// Iterate through the List
			if (null != benefitAdministrationList
					&& !benefitAdministrationList.isEmpty()) {
				for (int i = 0; i < benefitAdministrationList.size(); i++) {

					// Get the individual BOs from the list
					EntityBenefitAdministration administration = (EntityBenefitAdministration) benefitAdministrationList
							.get(i);
					administration.setLastUpdatedUser(audit.getUser());
					administration.setLastUpdatedTimestamp(audit.getTime());
					// Create an instance of the adapter manager
					BenefitAdapterManager adapterManager = new BenefitAdapterManager();

					// Call the persist() of the adapterManager to update the
					// administration
					adapterManager.updateBenefitAdministrationValues(
							administration, audit.getUser(),
							adapterServicesAccess);
				}
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(EntityBenefitAdministrationPSBO subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(EntityBenefitAdministrationPSBO subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject EntityBenefitAdministrationPSBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(EntityBenefitAdministrationPSBO subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject EntityBenefitAdministrationPSBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(EntityBenefitAdministrationPSBO subObject,ProductStructureBO mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return true;
	}

	//enhancement
	public boolean persist(ProductStructureBenefitAdministration subObject,
			ProductStructureBO mainObject, Audit audit, boolean insertFlag)
			throws SevereException {

		// Get the adminoption hide flag from the BO

		try {

			BenefitAdapterManager adapterManager = new BenefitAdapterManager();
			// Call the persist() of the adapterManager to update the
			// administration
			adapterManager
					.updateBenefitAdministrationValuesForHidingAdminOption(
							subObject, audit.getUser());

		} catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitAdministration, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureBenefitAdministration, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return true;
	}

	public boolean persist(ProductStructureBenefitAdministrationBO administrationBO,
            ProductStructureBO productStructureBO, Audit audit,
            boolean insertFlag) throws  SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
            adapterManager.saveQuestionnaire(administrationBO, audit, adapterServicesAccess);
            AdapterUtil.endTransaction(adapterServicesAccess);
            AdapterUtil.logTheEndTransaction(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        }catch(Exception ex){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        	 throw new SevereException(
                    "Persist for ComponentsBenefitAdministrationBO failed.Unknown exception is caught",
                    null, ex);	
        }
        
        return true;
    }
	
	//enhancement

	
	/**
	 * Persists the benefit component subObject
	 * 
	 * @param productStructureAssociatedBenefitComponent
	 * @param productStructureBO
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException,AdapterException
	 */
	private boolean persist(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			ProductStructureBO productStructureBO,
			AdapterServicesAccess adapterServicesAccess, Audit audit,
			boolean insertFlag) throws SevereException, AdapterException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		productStructureAssociatedBenefitComponent.setCreatedTimestamp(audit.getTime());
		productStructureAssociatedBenefitComponent.setCreatedUser(audit.getUser());
		productStructureAssociatedBenefitComponent.setLastUpdatedTimestamp(audit.getTime());
		productStructureAssociatedBenefitComponent.setLastUpdatedUser(audit.getUser());

		if (insertFlag) {
			int proStreSysId = productStructureAssociatedBenefitComponent
					.getProductStructureId();
			int benCompSysId = productStructureAssociatedBenefitComponent
					.getBenefitComponentId();
			productStructureAdapterManager.addBenefitComponentProc(
					productStructureAssociatedBenefitComponent, proStreSysId,
					benCompSysId, audit, adapterServicesAccess);
		} else {
			productStructureAdapterManager.updateBenefitComponent(
					productStructureAssociatedBenefitComponent,
					adapterServicesAccess);
		}
		return true;
	}

	/**
	 * To save and update all the benefit components associated with a
	 * ProductStructure
	 * 
	 * @param benefitComponentList
	 * @param productStructureBO
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean persist(ArrayList benefitComponentList,
			ProductStructureBO productStructureBO, Audit audit,
			boolean insertFlag) throws SevereException {

		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		if (null != benefitComponentList && !benefitComponentList.isEmpty()) {
			Iterator iter = benefitComponentList.iterator();
			try {
				AdapterUtil.beginTransaction(adapterServicesAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(ArrayList benefitComponentList, ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
				if (insertFlag) {
					while (iter.hasNext()) {
						ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = (ProductStructureAssociatedBenefitComponent) iter
								.next();
						persist(productStructureAssociatedBenefitComponent,
								productStructureBO, adapterServicesAccess,
								audit, true);
					}
				} else {
					while (iter.hasNext()) {
						ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = (ProductStructureAssociatedBenefitComponent) iter
								.next();
						persist(productStructureAssociatedBenefitComponent,
								productStructureBO, adapterServicesAccess,
								audit, false);
					}
				}

				AdapterUtil.endTransaction(adapterServicesAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(ArrayList benefitComponentList, ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
			} catch (SevereException ex) {
				AdapterUtil.abortTransaction(adapterServicesAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList benefitComponentList, ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
				List errorParams = new ArrayList(2);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in persist method , for persisting the Benefit Component List, in ProductStructureBusinessObjectBuilder",
						errorParams, ex);
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(adapterServicesAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList benefitComponentList, ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
				List errorParams = new ArrayList(2);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in persist method , for persisting the Benefit Component List, in ProductStructureBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(adapterServicesAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList benefitComponentList, ProductStructureBO productStructureBO, Audit audit,boolean insertFlag)");
				throw new SevereException("Unhandled exception occured", null,
						ex);
			}
		}
		return true;

	}

	/**
	 * Gets the Sequence number associated with a benefit component
	 * 
	 * @param productStructure
	 * @return
	 * @throws ServiceException
	 */
	private int getSequenceNumber(ProductStructureBO productStructure)
			throws SevereException {
		ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
		productStructureAssociatedBenefitComponent
				.setProductStructureId(productStructure.getProductStructureId());
		ProductStructureAdapterManager sequenceManager = new ProductStructureAdapterManager();
		return sequenceManager
				.getSequenceNumber(productStructureAssociatedBenefitComponent);

	}

	/**
	 * Deletes the ProductStructure
	 * 
	 * @param ProductStructureBO
	 * @param audit
	 * @return List
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean deleteLatestVersion(ProductStructureBO productStructureBO,
			Audit audit) throws SevereException {

		// Create an instance of the AdapterManager
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();

		// Call the retrieve method to get the associated benefitComponent List
		List benefitComponentList = productStructureAdapterManager
				.retrieveBenefitComponents(productStructureBO
						.getProductStructureId());
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"deleteLatestVersion(ProductStructureBO productStructureBO,Audit audit)");
			// Check whether the list is not null or empty
			if(benefitComponentList != null && !benefitComponentList.isEmpty()){        	
				// Iterate through the list and delete them one by one
				for (int i = 0; i < benefitComponentList.size(); i++) {
					// Get the individual benefitComponents
					ProductStructureAssociatedBenefitComponent benefitComponent = (ProductStructureAssociatedBenefitComponent) benefitComponentList
							.get(i);

					// Set the value of created time in the benefitComponent
					benefitComponent.setCreatedTimestamp(audit.getTime());

					// Set the value of Created User in the benefitComponent
					benefitComponent.setCreatedUser(audit.getUser());

					// Call the adapterManager to delete the individual
					// component
					productStructureAdapterManager.deleteBenefitComponent(
							benefitComponent, audit.getUser(),
							adapterServicesAccess);

				}
			}
			// Delete the ProductStructure from the db table
			productStructureBO.setCreatedTimestamp(audit.getTime());
			productStructureBO.setCreatedUser(audit.getUser());
			productStructureAdapterManager.deleteProductStructure(
					productStructureBO, audit.getUser(), adapterServicesAccess);

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"deleteLatestVersion(ProductStructureBO productStructureBO,Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"deleteLatestVersion(ProductStructureBO productStructureBO,Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"deleteLatestVersion(ProductStructureBO productStructureBO,Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"deleteLatestVersion(ProductStructureBO productStructureBO,Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return true;
	}
	/*
	 * 
	 * 
	 */
	public boolean deleteComponent(ProductStructureAssociatedBenefitComponent associatedBenefitComponent,
			ProductStructureBO productStructure, Audit audit,AdapterServicesAccess adapterServicesAccess)throws SevereException {
	int j=1;
	boolean isGenProvision = false;
	ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
	associatedBenefitComponent.setCreatedTimestamp(audit
			.getTime());
	associatedBenefitComponent.setCreatedUser(audit
			.getUser());
	try {
//		added for mulitiple benefit component  delete	
		List benefitComponentDeleteList = associatedBenefitComponent.getBenefitComponentDeleteList();
		if(null!=benefitComponentDeleteList){
			for(int i=0;i<benefitComponentDeleteList.size();i++){
				associatedBenefitComponent.
					setBenefitComponentId(Integer.parseInt(benefitComponentDeleteList.get(i).toString()));
				productStructureAdapterManager.deleteBenefitComponent(
						associatedBenefitComponent,
				audit.getUser(), adapterServicesAccess);
			}
		}
		List benefitComponentList = productStructureAdapterManager
				.retrieveBenefitComponents(associatedBenefitComponent
						.getProductStructureId());
		if (benefitComponentList != null) {
			for (int i = 0; i < benefitComponentList.size(); i++) {
				ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = 
					(ProductStructureAssociatedBenefitComponent) benefitComponentList.get(i);
				productStructureAssociatedBenefitComponent.setSequenceNum(i+1);
				productStructureAssociatedBenefitComponent.setLastUpdatedTimestamp(audit
						.getTime());
				productStructureAssociatedBenefitComponent.setLastUpdatedUser(audit
						.getUser());
				productStructureAdapterManager.updateBenefitComponent(
						productStructureAssociatedBenefitComponent, adapterServicesAccess);
			}
		}

		
	} catch (SevereException ex) {
		List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist method , for persisting the BusinessObject ProductStructureAssociatedBenefitComponent, in ProductStructureBusinessObjectBuilder",
				errorParams, ex);
	} catch (AdapterException ex) {
		List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist method , for persisting the BusinessObject ProductStructureAssociatedBenefitComponent, in ProductStructureBusinessObjectBuilder",
				errorParams, ex);
	} catch (Exception ex) {
		throw new SevereException("Unhandled exception occured", null, ex);
	}
	return false;
		
	}

	/**
	 * Deletes the subObject
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean delete(
			ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent,
			ProductStructureBO productStructure, Audit audit)
			throws SevereException {
		
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedBenefitComponent,ProductStructureBO productStructure, Audit audit)");
			
			deleteComponent( productStructureAssociatedBenefitComponent,productStructure,audit,adapterServicesAccess);

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ProductStructureAssociatedBenefitComponent,ProductStructureBO productStructure, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedBenefitComponent,ProductStructureBO productStructure, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureAssociatedBenefitComponent, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedBenefitComponent,ProductStructureBO productStructure, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return false;
	}

	/**
	 * Deletes the subObject
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean delete(ProductStructureComponent productStructureComponent,
			ProductStructureBO productStructure, Audit audit)
			throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		productStructureComponent.setCreatedTimestamp(audit.getTime());
		productStructureComponent.setCreatedUser(audit.getUser());
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ProductStructureComponent productStructureComponent,ProductStructureBO productStructure, Audit audit)");
			productStructureAdapterManager.deleteProductStructureComponent(
					productStructureComponent, audit.getUser(),
					adapterServicesAccess);

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ProductStructureComponent productStructureComponent,ProductStructureBO productStructure, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureComponent productStructureComponent,ProductStructureBO productStructure, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureComponent, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureComponent productStructureComponent,ProductStructureBO productStructure, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureComponent, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureComponent productStructureComponent,ProductStructureBO productStructure, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return false;
	}

	/**
	 * Deletes the BenefitComponent which does not match the domain change
	 * 
	 * @param ProductStructureAssociatedComponent
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean delete(
			ProductStructureAssociatedComponent productStructureAssociatedComponent,
			ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		productStructureAssociatedComponent
				.setCreatedTimestamp(audit.getTime());
		productStructureAssociatedComponent.setCreatedUser(audit.getUser());
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedComponent productStructureAssociatedComponent,ProductStructureBO productStructureBO, Audit audit)");
			productStructureAdapterManager
					.deleteInvalidProductStructureComponent(
							productStructureAssociatedComponent, audit
									.getUser(), adapterServicesAccess);

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ProductStructureAssociatedComponent productStructureAssociatedComponent,ProductStructureBO productStructureBO, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedComponent productStructureAssociatedComponent,ProductStructureBO productStructureBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureAssociatedComponent, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedComponent productStructureAssociatedComponent,ProductStructureBO productStructureBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the Business Object ProductStructureAssociatedComponent, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ProductStructureAssociatedComponent productStructureAssociatedComponent,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return false;
	}

	/**
	 * To get the standard benefit associated with a ProductStructure
	 * 
	 * @param locateCriteria
	 * @return LocateResults
	 * @throws WPDException
	 */
	public LocateResults locate(
			ProductStructureBenefitDefinitionLocateCriteria productStructureBenefitDefinitionLocateCriteria,
			User user) throws SevereException {
		
//		TimeHandler th = new TimeHandler();
//    	Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBusinessObjectBuilder","locate"));
    	
		// Create an instance of the adaptermanager
		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();

		// Create an instance of the locateResults
		LocateResults locateResults = new LocateResults();
		try {
			// Get the locate results by calling the adaptermanager method
			List resultList = benefitAdapterManager
					.getAllOverridedBenefitLines(
							BusinessConstants.PRODSTRUCTURE,
							productStructureBenefitDefinitionLocateCriteria
									.getProductStructureId(),
							productStructureBenefitDefinitionLocateCriteria
									.getStandardBenefitId(),
							productStructureBenefitDefinitionLocateCriteria
									.getBenefitComponentId(),
							productStructureBenefitDefinitionLocateCriteria
									.getBenefitLevelHideFlag(),
							productStructureBenefitDefinitionLocateCriteria
									.getBenefitLineHideFlag());
			locateResults.setLocateResults(resultList);
			locateResults.setTotalResultsCount(resultList.size());
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureBenefitDefinitionLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureBenefitDefinitionLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureBenefitDefinitionLocateCriteria method in ProductStructureBusinessObjectBuilder",
					null, ex);
		}
		// Return the locateResult to the business class
//		Logger.logInfo(th.endPerfLogging());
		return locateResults;
	}

	/**
	 * Method for getting benefit components
	 * 
	 * @param prodStructureTreeBenefitCmpnts
	 * @return benefitComponentList
	 * @throws SevereException
	 */
	public List getBenefitComponents(
			ProdStructureTreeBenefitCmpnts prodStructureTreeBenefitCmpnts)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List benefitComponentList = adapterManager
				.getBenefitComponents(prodStructureTreeBenefitCmpnts);
		return benefitComponentList;
	}

	/**
	 * Method for getting standard benefits
	 * 
	 * @param prodStructureTreeStandardBenefits
	 * @return standardBenefitList
	 * @throws SevereException
	 */
	public List getStandardBenefits(
			ProdStructureTreeStandardBenefits prodStructureTreeStandardBenefits)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List standardBenefitList = adapterManager
				.getStandardBenefits(prodStructureTreeStandardBenefits);
		return standardBenefitList;
	}

	/**
	 * Method for getting benefit dates
	 * 
	 * @param prodStructureTreeBenefitDate
	 * @return benefitDateList
	 * @throws SevereException
	 */
	public List getBenefitDate(
			ProdStructureTreeBenefitDate prodStructureTreeBenefitDate)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List benefitDateList = adapterManager
				.getBenefitDate(prodStructureTreeBenefitDate);
		return benefitDateList;
	}

	/**
	 * Method for getting admin options
	 * 
	 * @param treeAdminOptions
	 * @return adminOptionsList
	 * @throws SevereException
	 */
	public List getAdminOptions(ProdStructureTreeAdminOptions treeAdminOptions)
			throws SevereException {

		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List adminOptionsList = adapterManager
				.getAdminOptions(treeAdminOptions);
		return adminOptionsList;
	}

	/**
	 * To locate the ProductStructures
	 * 
	 * @param locateCriteria
	 * @return LocateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ProductStructureLocateCriteria productStructureLocateCriteriaBO,
			User user) throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		LocateResults locateResults = new LocateResults();
		String name = productStructureLocateCriteriaBO
				.getProductStructureName();
		List resultList = productStructureAdapterManager
				.locate(productStructureLocateCriteriaBO);
		List updatedList = new ArrayList(resultList==null?0:resultList.size());
		if (resultList != null) {
			for (int i = 0; i < resultList.size(); i++) {
				ProductStructureBO productStructure = (ProductStructureBO) resultList
						.get(i);
				List domainList = DomainUtil.retrieveAssociatedDomains(
						BusinessConstants.PRODSTRUCTURE, productStructure
								.getProductStructureParentId());
				
				List lobList = getDomainDetail(domainList).getLineOfBusiness();
				String lobDesc = WPDStringUtil.getTildaString(lobList);
				String description = "";
                if(null != productStructure.getDescription()){
                 if (productStructure.getDescription().length() > 25) {
                    description = productStructure.getDescription();
                    description = description.substring(0, 25)
                            .concat("...");
                    productStructure.setDescription(description);
                  }
                }
				productStructure.setBusinessDomains(domainList);
				productStructure.setLineOfBusiness(lobDesc);
				updatedList.add(productStructure);
			}
		}
	
		locateResults.setLocateResults(updatedList);
		locateResults.setTotalResultsCount(updatedList.size());
		locateResults.setLatestVersion(true);
		return locateResults;
	}

	/**
	 * 
	 * @param domains
	 * @return
	 */
	private DomainDetail getDomainDetail(List domains){
		Map lobMap=new HashMap();
		Map beMap=new HashMap();
		Map bgMap=new HashMap();
		DomainDetail domainDetail=new DomainDetail();
		domainDetail.setLineOfBusiness(new ArrayList(domains==null?0:domains.size()));
		domainDetail.setBusinessEntity(new ArrayList(domains==null?0:domains.size()));
		domainDetail.setBusinessGroup(new ArrayList(domains==null?0:domains.size()));
		domainDetail.setDomainList(domains);
		for(int i=0;i<domains.size();i++){
			Domain domain=(Domain)domains.get(i);
			if(lobMap.get(domain.getLineOfBusiness())==null){
				DomainItem domainItem=new DomainItem();
				domainItem.setItemId(domain.getLineOfBusiness());
				domainItem.setItemDesc(domain.getLineOfBusinessDesc());
				lobMap.put(domainItem.getItemId(),domainItem);
				domainDetail.getLineOfBusiness().add(domainItem);
			}
			if(beMap.get(domain.getBusinessEntity())==null){
				DomainItem domainItem=new DomainItem();
				domainItem.setItemId(domain.getBusinessEntity());
				domainItem.setItemDesc(domain.getBusinessEntityDesc());
				beMap.put(domainItem.getItemId(),domainItem);
				domainDetail.getBusinessEntity().add(domainItem);
			}
			if(bgMap.get(domain.getBusinessGroup())==null){
				DomainItem domainItem=new DomainItem();
				domainItem.setItemId(domain.getBusinessGroup());
				domainItem.setItemDesc(domain.getBusinessGroupDesc());
				bgMap.put(domainItem.getItemId(),domainItem);
				domainDetail.getBusinessGroup().add(domainItem);
			}
		}
		return domainDetail;
	}
	/**
	 * To get all the versions of a Product Structure
	 * 
	 * @param viewAllVersionsLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria,
			User user) throws SevereException {
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		int id = viewAllVersionsLocateCriteria.getProductStructureId();
		LocateResults locateResults = new LocateResults();
		List resultList = productStructureAdapterManager
				.locate(viewAllVersionsLocateCriteria);
		List updatedList = new ArrayList(resultList==null?0:resultList.size());
		if (null != resultList && !resultList.isEmpty()) {
			for (int i = 0; i < resultList.size(); i++) {
				ProductStructureBO productStructure = (ProductStructureBO) resultList
						.get(i);
				List domainList = DomainUtil.retrieveAssociatedDomains(
						BusinessConstants.PRODSTRUCTURE, productStructure
								.getProductStructureParentId());
				List lobList = DomainUtil.getLineOfBusiness(
						BusinessConstants.PRODSTRUCTURE, productStructure
								.getProductStructureParentId());
				String lobDesc = WPDStringUtil.getTildaString(lobList);
				String description = "";
                if(null != productStructure.getDescription()){
                 if (productStructure.getDescription().length() > 25) {
                    description = productStructure.getDescription();
                    description = description.substring(0, 25)
                            .concat("...");
                    productStructure.setDescription(description);
                  }
                }
				productStructure.setBusinessDomains(domainList);
				productStructure.setLineOfBusiness(lobDesc);
				updatedList.add(productStructure);
			}
			
			locateResults.setLocateResults(updatedList);
			locateResults.setTotalResultsCount(resultList.size());
		}else{
			locateResults.setLocateResults(resultList);
			locateResults.setTotalResultsCount(resultList.size());
		}
		return locateResults;
	}

	/**
	 * retrieves the ProductStructureBO by ProductStructureId
	 * 
	 * @param productStructureBO
	 * @return ProductStructureBO
	 * @throws SevereException
	 */
	public ProductStructureBO retrieve(ProductStructureBO productStructureBO,
			Map params) throws SevereException {
		try {
			if (params.get(BusinessConstants.BEN_COMPONENT_ID) != null) {
				int id = ((Integer) params
						.get(BusinessConstants.BEN_COMPONENT_ID)).intValue();
				BenefitComponentAdapterManager adapterManger = new BenefitComponentAdapterManager();
				BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
				benefitComponentBO.setBenefitComponentId(id);
				benefitComponentBO = (BenefitComponentBO) adapterManger
						.retrieveBenefitComponent(benefitComponentBO);
				RefdataAdapterManager rfmanager = new RefdataAdapterManager();
				AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
				ruleIdBO.setEntitySystemId(id);
				ruleIdBO.setEntityType(BusinessConstants.BENEFIT_COMP);

				List refList = rfmanager.searchRuleId(ruleIdBO);
				List refNameList = new ArrayList(refList==null?0:refList.size());
				List refIdList = new ArrayList(refList==null?0:refList.size());
				List refTypeList = new ArrayList(refList==null?0:refList.size());
				if (null != refList && 0 != refList.size()) {
					for (int i = 0; i < refList.size(); i++) {
						ruleIdBO = (AssignedRuleIdBO) refList.get(i);
						refNameList.add(ruleIdBO.getCodeDescText());
						refIdList.add(ruleIdBO.getPrimaryCode());
						refTypeList.add(ruleIdBO.getEntityType());
					}
				}
				benefitComponentBO.setRuleNameList(refNameList);
				benefitComponentBO.setRuleList(refIdList);
				benefitComponentBO.setRuleTypeList(refTypeList);
				List benefitComponentList = new ArrayList(1);
				benefitComponentList.add(benefitComponentBO);
				productStructureBO
						.setAssociatedBenefitComponentList(benefitComponentList);
			} else if (params.get(BusinessConstants.BENEFIT_KEY) != null) {
				int id = ((Integer) params.get(BusinessConstants.BENEFIT_KEY))
						.intValue();
				StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
				StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
				standardBenefitBO.setStandardBenefitKey(id);
				standardBenefitBO = (StandardBenefitBO) adapterManager
						.retrieveSB(standardBenefitBO);
				UniverseBO universeBO = new UniverseBO();
				universeBO.setStandardBenefitKey(standardBenefitBO
						.getStandardBenefitKey());
				List searchList = adapterManager.searchUniverse(universeBO);
				if (null != searchList) {
					if (searchList.size() > 0) {
						standardBenefitBO = this.prepareUniverseDisplayList(
								searchList, standardBenefitBO);
					}
				}
				StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
				standardBenefitDatatypeBO
						.setStandardBenefitKey(standardBenefitBO
								.getStandardBenefitKey());
				List dataTypeResultList = adapterManager
						.searchDatatype(standardBenefitDatatypeBO);
				standardBenefitBO.setDataTypeList(dataTypeResultList);
				int keyForRetrieve = ((Integer) params
						.get(BusinessConstants.BENEFIT_KEY)).intValue();
				// Create an object for adapterManager and BO to retrieve the
				// reference values
				RefdataAdapterManager rfmanager = new RefdataAdapterManager();
				AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
				ruleIdBO.setEntitySystemId(keyForRetrieve);
				ruleIdBO.setEntityType(BusinessConstants.BENEFIT_TYPE);

				List refList = rfmanager.searchRuleId(ruleIdBO);
				List refNameList = new ArrayList(refList==null?0:refList.size());
				List refIdList = new ArrayList(refList==null?0:refList.size());
				List refTypeList = new ArrayList(refList==null?0:refList.size());
				if (null != refList && 0 != refList.size()) {
					for (int i = 0; i < refList.size(); i++) {
						ruleIdBO = (AssignedRuleIdBO) refList.get(i);
						refNameList.add(ruleIdBO.getCodeDescText());
						refIdList.add(ruleIdBO.getPrimaryCode());
						refTypeList.add(ruleIdBO.getEntityType());
					}
				}
				standardBenefitBO.setRuleNameList(refNameList);
				standardBenefitBO.setRuleIdList(refIdList);
				standardBenefitBO.setRuleTypeList(refTypeList);
				List componentList = new ArrayList(1);
				ProductStructureAssociatedBenefitComponent associatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
				associatedBenefitComponent
						.setStandardBenefitBO(standardBenefitBO);
				componentList.add(associatedBenefitComponent);
				productStructureBO
						.setAssociatedBenefitComponentList(componentList);

			} else {
				ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
				List benefitComponentList = adapterManager
						.retrieveBenefitComponents(productStructureBO
								.getProductStructureId());
				productStructureBO
						.setAssociatedBenefitComponentList(benefitComponentList);
			}
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve ProductStructureBO method in ProductStructureBusinessObjectBuilder",
					errorParams, se);
		}  catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve ProductStructureBO method in ProductStructureBusinessObjectBuilder",
					null, ex);
		}
		return productStructureBO;
	}

	/**
	 * Function to get the values from the universe list and set them to BO
	 * 
	 * @param searchList
	 * @param standardBenefitBO
	 */
	private StandardBenefitBO prepareUniverseDisplayList(List searchList,
			StandardBenefitBO standardBenefitBO) {
		Iterator searchListIterator = searchList.iterator();
		List termList = new ArrayList(searchList==null?0:searchList.size());
		List qualifierList = new ArrayList(searchList==null?0:searchList.size());
		List pvaList = new ArrayList(searchList==null?0:searchList.size());

		while (searchListIterator.hasNext()) {
			UniverseBO universeBO = (UniverseBO) searchListIterator.next();
			if (WebConstants.TERM.equals(universeBO.getCatDescText())) {
				termList.add(universeBO);
			} else if (WebConstants.QUALIFIER.equals(universeBO
					.getCatDescText())) {
				qualifierList.add(universeBO);
			} else if (WebConstants.PROVIDER_ARRANGEMENT.equals(universeBO
					.getCatDescText())) {
				pvaList.add(universeBO);
			}

		}
		standardBenefitBO.setTermList(termList);
		standardBenefitBO.setQualifierList(qualifierList);
		standardBenefitBO.setPVAList(pvaList);
		return standardBenefitBO;
	}

	/**
	 * To get the mandates associated with a product Structure
	 * 
	 * @param productStructureMandatesLocateCriteriaBO
	 * @param user
	 * @return locateResults
	 */
	public LocateResults locate(
			ProductStructureMandatesLocateCriteria productStructureMandatesLocateCriteriaBO,
			User user) throws SevereException {
		List mandateList = new ArrayList(0);
		LocateResults locateResults = new LocateResults();
		List benefitMandateBOImplList = new ArrayList(1);
		List citationList = new ArrayList(0);
		List stateList = new ArrayList(0);
		List state = new ArrayList(0);
		//Create an instance of adapterManager
		NonAdjBenefitMandateAdapterManager nonAdjAdapterManager = new NonAdjBenefitMandateAdapterManager();

		BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();
		try {
			benefitMandateBOImpl
					.setBenefitSystemId(productStructureMandatesLocateCriteriaBO
							.getBenefitId());
			nonAdjAdapterManager.retrieve(benefitMandateBOImpl);
			productStructureMandatesLocateCriteriaBO
					.setBenefitMandateId(benefitMandateBOImpl
							.getBenefitMandateId());
			NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
			BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
			StateLocateCriteria stateLocateCriteria = new StateLocateCriteria();
			benefitMandateLocateCriteria
					.setBenefitMandateId(benefitMandateBOImpl
							.getBenefitMandateId());
			LocateResults citationResults = nonAdjBenefitMandateAdapterManager
					.locateCitationNumber(benefitMandateLocateCriteria);
			if (null != citationResults
					&& citationResults.getTotalResultsCount() != 0) {
				citationList=new ArrayList(citationResults.getTotalResultsCount());
				List list = citationResults.getLocateResults();
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					CitationNumberBO citationNumberBO = (CitationNumberBO) iterator
							.next();
					citationList.add(citationNumberBO);
				}
			}
			stateLocateCriteria.setBenefitMandateId(benefitMandateBOImpl
					.getBenefitMandateId());
			LocateResults stateResults = nonAdjBenefitMandateAdapterManager
					.locateStateObject(stateLocateCriteria);
			stateList = stateResults.getLocateResults();
			Iterator stateIterator = stateList.iterator();
			state=new ArrayList(stateList==null?0:stateList.size());
			while (stateIterator.hasNext()) {
				StateBO stateBO = (StateBO) stateIterator.next();
				state.add(stateBO);
			}
			benefitMandateBOImpl.setCitationNumberList(citationList);
			benefitMandateBOImpl.setBenefitStateCodeList(state);
			benefitMandateBOImplList.add(0, benefitMandateBOImpl);
			locateResults.setLocateResults(benefitMandateBOImplList);
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureMandatesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureMandatesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureMandatesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					null, ex);
		}
		return locateResults;
	}

	/**
	 * retrieves the ProductStructureBO by ProductStructureId
	 * 
	 * @param productStructureBO
	 * @return ProductStructureBO
	 * @throws SevereException
	 */
	public LocateResults locate(
			ProductStructureBenefitAdministrationLocateCriteria administrationLocateCriteria,
            User user) throws SevereException {
		Logger.logInfo("Entering execute method, request = "
				+ administrationLocateCriteria.getClass().getName());
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		AuditFactory auditFactory = (AuditFactory) ObjectFactory.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		LocateResults locateResults = new LocateResults();
    	List finalList = null;
    	int action = administrationLocateCriteria.getAction();
        switch(action){
    	
        case ProductStructureBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST:
        	int entityId = administrationLocateCriteria.getEntityId();
        	int adminOptAssnSysId = administrationLocateCriteria.getAdminLvlAssnSysId(); 
        	int beneftComponentId = administrationLocateCriteria.getBenefitComponentId();
        	int benefitAdminId = administrationLocateCriteria.getBenefitAdminSysId();
			int adminOptionId  = administrationLocateCriteria.getAdminOptSysId();
			int parentId       = administrationLocateCriteria.getParentId();
			HashMap allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
			
		    List locateResultList = adapterManager.getQuestionnaireValues
		    (adminOptAssnSysId,beneftComponentId,entityId,benefitAdminId,adminOptionId,parentId);
		        if(null!=locateResultList && 0<locateResultList.size()){
		        getPossibleAnswerList(locateResultList,allPossibleAnswerMap);
		        }
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
        break;
    	
    	case ProductStructureBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD:	
	    		int answerId = administrationLocateCriteria.getAnswerId();
    			String answerDesc = administrationLocateCriteria.getAnswerDesc();
	    		ProductStructureQuestionnaireBO questionnaireBO = 
	    			(ProductStructureQuestionnaireBO)administrationLocateCriteria.
	    			getProductStructureQuestionnaireBO(); 
	    		int questionnaireId = questionnaireBO.getQuestionnaireId();
				int productStructureId = administrationLocateCriteria.getEntityId();
				List oldQuestionnareList = administrationLocateCriteria.getQuestionnareList();
				List oldListForUnsavedQuestion = new ArrayList(administrationLocateCriteria.getQuestionnareList());
				int reaArrangedQuestIndex = administrationLocateCriteria.getQuestionareListIndex();
				 
				int adminLvlOptAssnId = administrationLocateCriteria.getAdminLvlAssnSysId();
				int benefitComponentId = administrationLocateCriteria.getBenefitComponentId();
				int benefitDefinitionId = administrationLocateCriteria.getBenefitDefnId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				
				((ProductStructureQuestionnaireBO)oldQuestionnareList.get(reaArrangedQuestIndex)).
				setSelectedAnswerId(answerId);
				((ProductStructureQuestionnaireBO)oldQuestionnareList.get(reaArrangedQuestIndex)).
				setSelectedAnswerDesc(answerDesc);
				
	    	    List childList=adapterManager.getChildQuestionnaireValues(answerId,questionnaireId,
	    	            productStructureId,adminLvlOptAssnId,
	    	            benefitComponentId,benefitDefinitionId);
	    	    if(null!=childList){ 
	    	    	getPossibleAnswerList(childList,allPossibleAnswerMap);
	    	    }
	    	    finalList=BusinessUtil.getRearrangedQuestionnareList(childList,oldQuestionnareList,
	    	            reaArrangedQuestIndex);
	    	    List notesToDelete = getQuestionareNoteListToDelete(finalList,oldListForUnsavedQuestion);
	    	    deleteUnsavedQuestionNote(notesToDelete,productStructureId,benefitComponentId,adminLvlOptAssnId,benefitDefinitionId,user);
	    		locateResults.setLocateResults(finalList);
    	break;
    	case ProductStructureBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW:
    		    beneftComponentId = administrationLocateCriteria.getBenefitComponentId();
    			productStructureId = administrationLocateCriteria.getEntityId();
    			adminOptAssnSysId = administrationLocateCriteria.getAdminLvlAssnSysId();
    			
    			benefitAdminId = administrationLocateCriteria.getBenefitAdminSysId();
    			adminOptionId  = administrationLocateCriteria.getAdminOptSysId();
    			parentId       = administrationLocateCriteria.getParentId();
    			allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
    			
		        locateResultList = adapterManager.getQuestionnaireValues(adminOptAssnSysId,
		                beneftComponentId,productStructureId,benefitAdminId,adminOptionId,parentId);
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
        break;
        }
        Logger.logInfo("Exiting execute method, request = "
				+ administrationLocateCriteria.getClass().getName());
        // return the locate results to the business service 
        return locateResults;
    }
	
	 /*
	 * this method filter the questionareLiat and flag the question notes to be deleted
	 * 
	 * 
	 */
	public static List getQuestionareNoteListToDelete(List newQuestionnareList,List oldListForUnsavedQuestion){
		
		List listToDelete= new ArrayList();
		
		for(int i=0;i<oldListForUnsavedQuestion.size();i++){
			
			ProductStructureQuestionnaireBO oldQuestionnareBo = (ProductStructureQuestionnaireBO)oldListForUnsavedQuestion.get(i);
			
			for (int j=0;j<newQuestionnareList.size();j++){
				
				ProductStructureQuestionnaireBO newQuestionnareBo = (ProductStructureQuestionnaireBO)newQuestionnareList.get(j);
				if(newQuestionnareBo.compareTo(oldQuestionnareBo)){
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			//listToDelete.add(oldQuestionnareBo);
		}
		
		return newQuestionnareList;
		
	}
	 
	/* this method perfotm delete the unsaved question note deatils while the questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 * 
	 */
	private void deleteUnsavedQuestionNote(List noteDetailList,int productStructureId,int beneftComponentId,int adminLevelOptionId,int benefitDefId,User user)
	throws SevereException{
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ProductStructureQuestionnaireBO questionnareBo = (ProductStructureQuestionnaireBO)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotesExists("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBO attachmentBo = new NotesToQuestionAttachmentBO();
				attachmentBo.setPrimaryId(productStructureId);
				attachmentBo.setSecondaryId(adminLevelOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHPRODSTRCT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNoteOverrideStatus("F");
				attachmentBo.setBnftDefId(new Integer(benefitDefId).toString());
				attachmentBo.setBenefitCompId(beneftComponentId);
				
				NotesToQuestionAttachmentBO newattachmentBo=getNoteInfo(attachmentBo);
				if(null!=newattachmentBo && null!=newattachmentBo.getNoteId() && !("").equals(newattachmentBo.getNoteId())){
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setNoteVersionNumber(newattachmentBo.getNoteVersionNumber());
					try {
						adapterManager.deleteNotesAttachedToQuestion(attachmentBo,audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	 private NotesToQuestionAttachmentBO getNoteInfo(NotesToQuestionAttachmentBO attachmentBo){
	 	NotesToQuestionAttachmentBO newAttachmentBo = null;
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if(null!=resultList && resultList.size()>0){
			newAttachmentBo = (NotesToQuestionAttachmentBO)resultList.get(0);
		 	}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAttachmentBo;
	 }
	/**
	 * this method is for getting possible answer list.
	 * 
	 * @param locateResultList, this list contains  questions needed for possible answer
	 * @return answer list
	 * @throws SevereException
	 */
    public void getPossibleAnswerList(List locateResultList) throws SevereException{
        
    	  if(locateResultList != null && !locateResultList.isEmpty()){        
            QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
            // Create the Adapter Service Access
            AdapterServicesAccess adapterServicesAccess = AdapterUtil
                    .getAdapterService();

            // Iterate through the list to get the possible answers for the
            // question
            int listSize = locateResultList.size();
            for (int i = 0; i < listSize; i++) {
                // Get the individual entitybenefitAdministration objects from
                // the list
            	ProductStructureQuestionnaireBO questionnaireBO = (ProductStructureQuestionnaireBO) locateResultList
                        .get(i);
                int questionNumber = questionnaireBO.getQuestionId();

                QuestionBO questionBO = new QuestionBO();

                questionBO.setQuestionNumber(questionNumber);

                // Call the getPossibleAnswers() of the question adaptermanager
                List possibleAnswerList = questionAdapterManager
                        .getPossibleAnswer(questionBO, adapterServicesAccess);
                List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
                // Set the possible answers list to the
                // entityBenefitAdministration
                questionnaireBO.setPossibleAnswerList(arrangedPossibleAnswerList);
            }
        }

    }
	
    /**
	 * this method is for getting possible answer list.
	 * 
	 * @param locateResultList, this list contains  questions needed for possible answer
	 * and allPossibleAnswerMap.
	 * @return answer list
	 * @throws SevereException
	 */
    public void getPossibleAnswerList(List locateResultList, HashMap allPossibleAnswerMap) throws SevereException{
    	
    	  if(locateResultList != null && !locateResultList.isEmpty()){        
            QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
            // Create the Adapter Service Access
            AdapterServicesAccess adapterServicesAccess = AdapterUtil
                    .getAdapterService();

            // Iterate through the list to get the possible answers for the
            // question
            int listSize = locateResultList.size();
            for (int i = 0; i < listSize; i++) {
                // Get the individual entitybenefitAdministration objects from
                // the list
            	ProductStructureQuestionnaireBO questionnaireBO = (ProductStructureQuestionnaireBO) locateResultList
                        .get(i);
                int questionNumber = questionnaireBO.getQuestionId();

                QuestionBO questionBO = new QuestionBO();

                questionBO.setQuestionNumber(questionNumber);

                List possibleAnswerList;
				if(null != allPossibleAnswerMap && allPossibleAnswerMap.containsKey(new Integer(questionNumber))){
					possibleAnswerList = (ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)); 
					Collections.sort(possibleAnswerList);
					
				}else{
					//	 Call the getPossibleAnswers() of the question adaptermanager
	                possibleAnswerList = questionAdapterManager
	                        .getPossibleAnswer(questionBO, adapterServicesAccess);

				}
                
                List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
                // Set the possible answers list to the
                // entityBenefitAdministration
                questionnaireBO.setPossibleAnswerList(arrangedPossibleAnswerList);
            }
        }
    	
    }
	
    
	  /*
     * 
     * @createFinalList
     *
     * TODO To change the template for this generated type comment go to
     * Window - Preferences - Java - Code Style - Code Templates
     */
    public  List   createParentlist(List totalList)
    {
    	
    List parentQuestionList = new ArrayList();
    List childQuestionList = new ArrayList();
    List finalList = new ArrayList();
     int k=1;
     for(int i= 0;i<totalList.size();i++)
     {
     	ProductStructureQuestionnaireBO productStructureQuestionnaireBO = (ProductStructureQuestionnaireBO)totalList.get(i);
      if(productStructureQuestionnaireBO.getParentQuestionnaireId()==productStructureQuestionnaireBO.getQuestionnaireId())
      {
      	productStructureQuestionnaireBO.setLevel(1);
      	productStructureQuestionnaireBO.setQuestionOrder(k);
        parentQuestionList.add(productStructureQuestionnaireBO);
        k++;
      }else
      {
        childQuestionList.add(productStructureQuestionnaireBO);
      }
     }
     if(null!=parentQuestionList && 0<parentQuestionList.size()&&
           0==childQuestionList.size()){
     	finalList = parentQuestionList;
     }
     if(null!=parentQuestionList && 0<parentQuestionList.size()&&
          null!=childQuestionList && 0<childQuestionList.size())
          {
            finalList = createFinalList(parentQuestionList,childQuestionList);
          }
     
     return finalList;
    }
    
    /*
     * 
     * @createFinalList
     *
     * TODO To change the template for this generated type comment go to
     * Window - Preferences - Java - Code Style - Code Templates
     */
    public List createFinalList(List parentQuestionList,List childQuestionList)
    {
   
    
    List newParentList = new ArrayList();
    if(null!=childQuestionList && 0< childQuestionList.size())
    {
      for(int i=0;i<parentQuestionList.size();i++)
      {
      	
      	ProductStructureQuestionnaireBO parentQuestionBean = (ProductStructureQuestionnaireBO)parentQuestionList.get(i);
          newParentList.add(parentQuestionBean);
          for(int j = 0;j<childQuestionList.size();j++)
          {
          	ProductStructureQuestionnaireBO childQuestionBean = (ProductStructureQuestionnaireBO)childQuestionList.get(j);
            
              if(childQuestionBean.getParentQuestionnaireId() == parentQuestionBean.getQuestionnaireId())
              {
                int level = parentQuestionBean.getLevel();
                childQuestionBean.setLevel(level+1);
                int order = parentQuestionBean.getQuestionOrder();
                childQuestionBean.setQuestionOrder(order);
                newParentList.add(childQuestionBean);
              }
          }
        }
      if(null!=childQuestionList || 0< childQuestionList.size())
      {
        for(int i=0;i<newParentList.size();i++){
        	ProductStructureQuestionnaireBO parentQuestionBean = (ProductStructureQuestionnaireBO)newParentList.get(i);
             for(int p=0;p<childQuestionList.size();p++){
             	ProductStructureQuestionnaireBO childQuestionBean = (ProductStructureQuestionnaireBO)childQuestionList.get(p);
                  if(childQuestionBean.getQuestionId()== parentQuestionBean.getQuestionId()){
                     childQuestionList.remove(p);
               
                  }
           }
        }
        
        if(null!=childQuestionList && 0< childQuestionList.size())
        {
        	newParentList = createFinalList(newParentList,childQuestionList);
        }
      }
    }
   
     
   
      return newParentList;
    }

	/**
	 * To compare two lists of benefit components associated with a Product
	 * Structure
	 * 
	 * @param newComponentList
	 * @param oldComponentList
	 * @return ProductStructureAssociatedBenefitComponent
	 */
	private ProductStructureAssociatedBenefitComponent isPresent(
			List newComponentList, List oldComponentList) {
		Iterator newIter = newComponentList.iterator();
		Iterator oldIter = oldComponentList.iterator();
		while (oldIter.hasNext()) {
			ProductStructureAssociatedBenefitComponent oldAssociatedBenefitComponent = (ProductStructureAssociatedBenefitComponent) oldIter
					.next();
			while (newIter.hasNext()) {
				ProductStructureAssociatedBenefitComponent newAssociatedBenefitComponent = (ProductStructureAssociatedBenefitComponent) newIter
						.next();
				if (newAssociatedBenefitComponent.getBenefitComponentId() == oldAssociatedBenefitComponent
						.getBenefitComponentId()) {
					return oldAssociatedBenefitComponent;
				}
			}
		}
		return null;
	}

	/**
	 * To remove the benefit components associated with a Product Structure
	 * 
	 * @param productStructureId
	 * @param lob
	 * @param be
	 * @param bg
	 * @throws SevereException
	 */
	private void removeBenefitComponents(ProductStructureBO productStructure,
			Audit audit) throws SevereException {
		List domainList = productStructure.getBusinessDomains();
		List lob = BusinessUtil.getLobList(domainList);
		List be = BusinessUtil.getbusinessEntityList(domainList);
		List bg = BusinessUtil.getBusinessGroupList(domainList);
		List mbu = BusinessUtil.getMarketBusinessUnitList(domainList);
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
		List componetList = adapterManager
				.getAssociatedBenefitComponentsToBeRemoved(productStructure
						.getProductStructureId(), lob, be, bg, mbu);
		AdapterServicesAccess access = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(access);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"removeBenefitComponents(ProductStructureBO productStructure,Audit audit)");
			if (componetList != null) {
				for (int i = 0; i < componetList.size(); i++) {
					BenefitComponentBO benefitComponentBO = (BenefitComponentBO) componetList
							.get(i);
					ProductStructureAssociatedBenefitComponent associatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
					associatedBenefitComponent
							.setBenefitComponentId(benefitComponentBO
									.getBenefitComponentId());
					associatedBenefitComponent
							.setProductStructureId(productStructure
									.getProductStructureId());
					productStructureAdapterManager
							.deleteBenefitComponent(associatedBenefitComponent,
									audit.getUser(), access);
				}
			}
			componetList = productStructureAdapterManager.inValidDateRange(
					productStructure.getProductStructureId(), productStructure
							.getEffectiveDate(), productStructure
							.getExpiryDate());
			if (componetList != null) {
				for (int i = 0; i < componetList.size(); i++) {
					ProductStructureAssociatedBenefitComponent associatedBenefitComponent = (ProductStructureAssociatedBenefitComponent) componetList
							.get(i);
					productStructureAdapterManager
							.deleteBenefitComponent(associatedBenefitComponent,
									audit.getUser(), access);
				}
			}
			AdapterUtil.endTransaction(access);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"removeBenefitComponents(ProductStructureBO productStructure,Audit audit)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"removeBenefitComponents(ProductStructureBO productStructure,Audit audit)");
			throw new SevereException(null, null, e);
		} catch (AdapterException e) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"removeBenefitComponents(ProductStructureBO productStructure,Audit audit)");
			throw new SevereException("Unknown Exception is caught", null, e);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"removeBenefitComponents(ProductStructureBO productStructure,Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
	}

	/**
	 * Copy method called by checkin
	 * 
	 * @param srcProductStructureBO
	 * @param productStructureBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copy(ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {

		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		boolean status = false;
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"copy(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			status = adapterManager.copyProductStructure(srcProductStructureBO,
					productStructureBO, audit, adapterServicesAccess);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"copy(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copy(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException(null, null, e);
		} catch (AdapterException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copy(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException("Unknown Exception is caught", null, e);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copy(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return status;
	}

	/**
	 * Copy method for the CheckOut
	 * 
	 * @param srcProductStructureBO
	 * @param productStructureBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean copyForCheckOut(ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {

		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		boolean status = false;
		long transactionId = AdapterUtil.getTransactionId();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"copyForCheckOut(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			status = adapterManager.checkoutProductStructure(
					srcProductStructureBO, productStructureBO, audit,
					adapterServicesAccess);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"copyForCheckOut(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
		} catch (SevereException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copyForCheckOut(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException(null, null, e);
		} catch (AdapterException e) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copyForCheckOut(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException("Unknown Exception is caught", null, e);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copyForCheckOut(ProductStructureBO srcProductStructureBO,ProductStructureBO productStructureBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return status;
	}

	/**
	 * 
	 * @param ProductStructureBO
	 * @param audit
	 * @throws SevereException
	 * @throws AdapterException
	 * @return boolean
	 */
	public boolean delete(ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {
		deleteLatestVersion(productStructureBO, audit);
		return true;
	}

	/**
	 * Method to change Identity if the Domain value is edited
	 * 
	 * @param ProductStructureBO
	 * @param audit
	 * @throws SevereException
	 * @throws AdapterException
	 * @return boolean
	 */
	public boolean changeIdentity(ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {
		ProductStructureAdapterManager adapter = new ProductStructureAdapterManager();
		persist(productStructureBO, audit, false);
		adapter.refreshQuestionnaire(srcProductStructureBO.getProductStructureId(),srcProductStructureBO.getBenifitComponentId(),audit.getUser());
		return true;

	}

	/**
	 * Method for copying associated benefit components to target
	 * 
	 * @param srcProductStructureBO
	 * @param productStructureBO
	 * @param audit
	 * @throws SevereException
	 */
	public void copy(ProductStructureBO srcProductStructureBO,
			ProductStructureBO productStructureBO, AuditImpl audit)
			throws SevereException {
		Audit audit1 = (Audit) audit;
		copy(srcProductStructureBO, productStructureBO, audit1);

	}

	public ProductStructureBO retrieveForReference(
			ProductStructureBO productStructureBO) throws SevereException {
		ProductStructureAdapterManager adapter = new ProductStructureAdapterManager();
		return adapter.retrieveByProductId(productStructureBO);
	}

	/*
	 * Locate For notes attached to benefitComponent
	 *  @ input ProductStructureNotesLocateCriteria
	 * 
	 * return list of notes
	 *  
	 */
	public LocateResults locate(
			ProductStructureNotesLocateCriteria locateCriteria, User user)
			throws SevereException {
		LocateResults locateResults = null;
		// 	 create an object of adapter manager
		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
		try {
			// Set values from locate criteria to the corresponding variables
			int entityId = locateCriteria.getEntityId();
			String entityType = locateCriteria.getEntityType();
			int maxResultSize = locateCriteria.getMaximumResultSize();
			int action = locateCriteria.getAction();
			if (action == WebConstants.ACTION_ONE) {
				// call the locate method in the adapter manager
				locateResults = notesAttachmentAdapterManager
						.locateAttachedNotes(entityId, entityType, null);
			} else if (action == WebConstants.ACTION_TWO) {
				int secEntityId = locateCriteria.getSecEntityId();
				String secEntityType = locateCriteria.getSecEntityType();
				int bcId = locateCriteria.getBenefitComponentId();
				// call the locate method in the adapter manager
				locateResults = notesAttachmentAdapterManager
						.locateAttachedNotesForOverride(entityId, entityType,
								secEntityId, secEntityType, bcId);
			}
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureNotesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureNotesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ProductStructureNotesLocateCriteria method in ProductStructureBusinessObjectBuilder",
					null, ex);
		}
		return locateResults;
	}

	/**
	 * @param migrationSystemId
	 * @throws SevereException
	 */
	public LocateResults locate(
			ProductStructureShowHiddenLocateCriteria productStructureLocateCriteria,
			User user) throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		return adapterManager
				.retrieveProductStructureAssociatedBenefit(productStructureLocateCriteria);
	}

	/**
	 * @param migrationSystemId
	 * @throws SevereException
	 */
	public boolean persist(
			ProductStructureAssociatedBenefit productStructureAssociatedBenefits,
			ProductStructureBO productStructureBO, Audit audit,
			boolean insertFlag) throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		AdapterServicesAccess access = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();

		try {
			AdapterUtil.beginTransaction(access);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"updateBenefitDetails(ProductStructureAssociatedBenefit productStructureAssociatedBenefits)");
			adapterManager.updateAllBenefitDetails(
					productStructureAssociatedBenefits, access, audit.getUser());
			AdapterUtil.endTransaction(access);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"updateBenefitDetails(ProductStructureAssociatedBenefit productStructureAssociatedBenefits)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"updateBenefitDetails(ProductStructureAssociatedBenefit productStructureAssociatedBenefits)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureAssociatedBenefit, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"updateBenefitDetails(ProductStructureAssociatedBenefit productStructureAssociatedBenefits)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ProductStructureAssociatedBenefit, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(access);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"updateBenefitDetails(ProductStructureAssociatedBenefit productStructureAssociatedBenefits)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return true;

	}

	/**
	 * Method for persisting product admin override values.
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(HideAdminOption subObject,
			ProductStructureBO mainObject, Audit audit, boolean insertFlag)
			throws SevereException {

		Logger
				.logInfo("ProductStructureBusinessObjectBuilder - Entering " +
						"persist(AdministrationOptionBO subObject="
						+ subObject
						+ ":ProductStructureBO mainObject="
						+ mainObject
						+ ":Audit audit="
						+ audit
						+ ":boolean insertFlag=" + insertFlag);

		List adminList = (List) subObject.getAdminBOList();

		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(HideAdminOption subObject,ProductStructureBO mainObject, " +
							"Audit audit, boolean insertFlag)");
			if (null != adminList && !adminList.isEmpty()) {
				for (int i = 0; i < adminList.size(); i++) {

					HideAdminOption administration = (HideAdminOption) adminList
							.get(i);

					// Call the persist() of the adapterManager to update the
					// administration

					administration.setLastUpdatedUser(audit.getUser());
					administration.setCreatedUser(audit.getUser());
					administration.setLastUpdatedTimestamp(audit.getTime());
					administration.setCreatedTimestamp(audit.getTime());

					adapterManager.updateAdminOptionValues(administration,
							audit.getUser(), adapterServicesAccess);
				}
			}

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(HideAdminOption subObject,ProductStructureBO mainObject, " +
							"Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(HideAdminOption subObject,ProductStructureBO mainObject," +
							" Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject " +
					"HideAdminOption, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(HideAdminOption subObject,ProductStructureBO mainObject," +
							" Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject " +
					"HideAdminOption, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(HideAdminOption subObject,ProductStructureBO " +
							"mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}
		return true;
	}
	
	/**
	 * @param notesToQuestionAttachmentBO
	 * @param productStructureBO
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	public void persist(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
			ProductStructureBO productStructureBO, Audit audit, boolean insertFlag)
			throws SevereException {	   
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();

		notesToQuestionAttachmentBO.setCreatedTimestamp(productStructureBO
				.getCreatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedTimestamp(productStructureBO
				.getLastUpdatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedUser(productStructureBO
				.getLastUpdatedUser());
		notesToQuestionAttachmentBO.setCreatedUser(productStructureBO
				.getCreatedUser());

		try {  

			if (insertFlag){
				notesAdapterManager.insertNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);
			}

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for persisting the " +
					"BusinessObject NotesToQuestionAttachmentBO, in PSBusinessObjectBuilder",
					errorParams, exception);
		}
		try {
		    if (!insertFlag){			   
			    notesAdapterManager.updateNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);
			}
		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for updating the " +
					"BusinessObject NotesToQuestionAttachmentBO, in PSBusinessObjectBuilder",
					errorParams, exception);
		}
	}

	/**
	 * @param notesToQuestionAttachmentBO
	 * @param productStructureBO
	 * @param audit
	 * @throws SevereException
	 */
	public void delete(NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
	        ProductStructureBO productStructureBO, Audit audit)
			throws SevereException {	    
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		try {
			notesAdapterManager.deleteNotesAttachedToQuestion(
					notesToQuestionAttachmentBO, audit);
		} catch (AdapterException exception) {
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in delete method , for deleting the " +
					"BusinessObject NotesToQuestionAttachmentBO, in PSBusinessObjectBuilder",
					errorParams, exception);
		}

	}
}