/*
 * ContractBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.builder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.SerializationUtils;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.adapter.AdminOptionAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentBusinessObjectBuilder;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.adapter.BenefitlevelAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.BenefitAdapterManager;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAdminLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractAllVersionsLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractMandatesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractNotesLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ContractProductLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.DatafeedLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.DateSegmentLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.ExistingContractLocateCriteria;
import com.wellpoint.wpd.business.contract.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.AllPossibleAnswerForAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.contract.locateresult.RuleCodeRanges;
import com.wellpoint.wpd.business.contract.locateresult.RuleLocateResult;
import com.wellpoint.wpd.business.contract.locateresult.RuleSequenceResults;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.notes.adapter.NotesAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.product.adapter.ProductTreeAdapterManager;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitBusinessObjectBuilder;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.assocobj.bo.AffiliationObject;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO;
import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.contract.bo.BenefitCustomizationBO;
import com.wellpoint.wpd.common.contract.bo.Comment;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractAuditInfo;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitComponents;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitDefinitions;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractHistory;
import com.wellpoint.wpd.common.contract.bo.ContractNotes;
import com.wellpoint.wpd.common.contract.bo.ContractPricingInfo;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdmin;
import com.wellpoint.wpd.common.contract.bo.ContractProductAdminBO;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.contract.bo.ContractRuleBO;
import com.wellpoint.wpd.common.contract.bo.ContractStatusBo;
import com.wellpoint.wpd.common.contract.bo.CopyBenefitHeadingsBO;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.DateSegmentAssociationBO;
import com.wellpoint.wpd.common.contract.bo.DateSegmentUpdateAfterDeleteBO;
import com.wellpoint.wpd.common.contract.bo.DeletedDSInfoBO;
import com.wellpoint.wpd.common.contract.bo.ProviderSpecialityCodeBO;
import com.wellpoint.wpd.common.contract.bo.ReserveContractId;
import com.wellpoint.wpd.common.contract.bo.RuleIdBO;
import com.wellpoint.wpd.common.contract.bo.SystemContractId;
import com.wellpoint.wpd.common.contract.bo.TestData;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.ContractProductAONotesAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBenefitBO;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.ContractTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierNoteOverRide;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.bo.EntityProductBenefitAdministration;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.productstructure.bo.EntityBenefitAdministrationPSBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.report.bo.ContractReportBean;
import com.wellpoint.wpd.common.report.bo.ContractReportCriteria;
import com.wellpoint.wpd.common.report.bo.RuleReportBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.util.RuleReportSort;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.contract.ContractTransferLogBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ContractBusinessObjectBuilder {

	ContractAdapterManager adapterManager;

	ProductBusinessObjectBuilder productbuilder;

	/**
	 * Constructor
	 *  
	 */
	public ContractBusinessObjectBuilder() {
		adapterManager = new ContractAdapterManager();
	}

	/**
	 * This method is retrieve benefit Component list for hide/unhide
	 * 
	 * @param mainObject
	 * @return mainObject
	 * @throws SevereException
	 */
	public Contract retrieveBenefitComponentListHideUnhide(Contract contract)
			throws SevereException {
		
		ProductTreeAdapterManager adapterManager = new ProductTreeAdapterManager();
		ProductTreeBenefitComponents details = new ProductTreeBenefitComponents();
		details.setProductId(contract.getProductId());
		details.setDateSegmentId(contract.getContractDateSegmentSysId());
		List list = adapterManager
				.getContractProductBenefitComponentsHideUnhide(details);
		Contract contractBO = new Contract();
		if (null != list && list.size() > 0) {
			contractBO.setComponentList(list);
		} else {
			contractBO.setComponentList(new ArrayList());
		}

		return contractBO;

	}

	/**
	 * This method is to delete the rule
	 * 
	 * @param contractRuleBONew
	 * @param mainObject
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public boolean delete(ContractRuleBO contractRuleBONew,
			DateSegment mainObject, Audit audit) throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"delete(ContractRuleBO contractRuleBONew,Contract mainObject, User user)");
			adapterManager.deleteRules(contractRuleBONew, audit, serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"delete(ContractRuleBO contractRuleBONew,Contract mainObject, User user)");
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"delete(ContractRuleBO contractRuleBONew,Contract mainObject, User user)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * This method is to delete the speciality Codes
	 * 
	 * @param ProviderSpecialityCodeBO
	 * @param mainObject
	 * @param Audit
	 * @return
	 * @throws WPDException
	 */
	public boolean delete(ProviderSpecialityCodeBO providerSpecialityCodeBO,
			DateSegment mainObject, Audit audit) throws SevereException {
		providerSpecialityCodeBO.setLastUpdatedUser(audit.getUser());
		providerSpecialityCodeBO.setLastUpdatedTimestamp(audit.getTime());
		providerSpecialityCodeBO.setCreatedTimestamp(audit.getTime());
		providerSpecialityCodeBO.setCreatedUser(audit.getUser());
		adapterManager.deleteSpecialityCode(providerSpecialityCodeBO, audit);
		return true;
	}

	public boolean ifFirstDateSegment(int dateSegmentId, int contractId)throws SevereException{
		List dateSegmentList =adapterManager.getFirstdateSegment(dateSegmentId,contractId);
		if(null ==dateSegmentList || dateSegmentList.isEmpty())
			return true;
		return false;
	}
	/**
	 * This method is to insert the speciality Codes
	 * 
	 * @param ProviderSpecialityCodeBO
	 * @param mainObject
	 * @param Audit
	 * @return
	 * @throws WPDException
	 */
	public boolean persist(ProviderSpecialityCodeBO providerSpecialityCodeBO,
			DateSegment mainObject, Audit audit, boolean insertFlag)
			throws SevereException {
		providerSpecialityCodeBO.setLastUpdatedUser(audit.getUser());
		providerSpecialityCodeBO.setLastUpdatedTimestamp(audit.getTime());
		providerSpecialityCodeBO.setCreatedTimestamp(audit.getTime());
		providerSpecialityCodeBO.setCreatedUser(audit.getUser());
		adapterManager.persistProviderSpecialityCode(providerSpecialityCodeBO);
		return true;
	}

	public List retrieveValidStatusDatesegments(int contractSysId,
			String testIndicator) throws SevereException {
		return adapterManager.retrieveValidStatusDatesegments(contractSysId,
				testIndicator);
	}

	public List retrieveCheckInDateSegments(int contractSysId)
			throws SevereException {
		List dateSegList = adapterManager.retrieveCheckInDateSegments(contractSysId);
		return dateSegList;
	}

//sscr 17571
	
	
	public List retrieveDate(int contractSysId)
			throws SevereException {
		List dateRangeList = adapterManager.retrieveDate(contractSysId);
		return dateRangeList;
	}
	public List getTierCodeAndValues(int dateSegmentSysId)
	throws SevereException {
			return adapterManager.getTierCodeAndValues(dateSegmentSysId);
		}
	
	/**
	 * This method is for benefit component hide/unhide process.
	 * 
	 * @param bo
	 * @param contract
	 * @param audit
	 * @param inserflag
	 * @return
	 * @throws SevereException
	 */

	public boolean persist(ContractBenefitComponents bo,
			DateSegment dateSegment, Audit audit, boolean inserflag)
			throws SevereException {

		List list = bo.getComponentList();

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ContractBenefitComponents bo, Contract contract, Audit audit,boolean inserflag)");
			if (null != list && list.size() > 0) {
				ContractBenefitComponents details = new ContractBenefitComponents();
				for (int i = 0; i < list.size(); i++) {
					details = new ContractBenefitComponents();
					details = (ContractBenefitComponents) list.get(i);

					details.setProductId(bo.getProductId());

					//already bencomp sys id sis ther.
					details.setDateSegmentId(bo.getDateSegmentId());
					String flag = details.getBenefitComponentHideFlag();
					
                    /** Commented this adapter call part and implemented using Map :: by KK**/
					
					/*ContractBenefitComponents detailsFlags = new ContractBenefitComponents();
					detailsFlags.setDateSegmentId(bo.getDateSegmentId());
					detailsFlags.setBenefitComponentId(details
							.getBenefitComponentId());
					detailsFlags = adapterManager
							.retrieveBenefitComponentFlags(detailsFlags);
					
					if (!(detailsFlags.getBenefitComponentHideFlag()
					.equals(flag))) {*/ 
					
					/**Implemented the above using Map :: by KK**/
					String keyvalue=details.getBenefitComponentId()+"||"+bo.getDateSegmentId();
					Object flagvalue=bo.getBenefitComponentHideMap().get(keyvalue);
					if (!(flagvalue.equals(flag))) {
					/** end :: by KK**/
						
						if (details.getBenefitComponentHideFlag().equals("T")) {
							details.setBenefitHideFlag("T");
							details.setQuestionsFlag("T");
							details.setAdminOptionFlag("T");
							adapterManager.deleteBnftCompTierDetails(details, serviceAccess);
							
						} else {
							details.setBenefitHideFlag("F");
							details.setQuestionsFlag("F");
							details.setAdminOptionFlag("F");
						}

						details.setLastUpdatedTimeStamp(audit.getTime());
						details.setLastUpdatedUserId(audit.getUser());
						details.setEntityType("contract");
						adapterManager.updateContractBenefitComponentsFlag(
								details, audit.getUser(), serviceAccess);
					}
				}
				this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ContractBenefitComponents bo, Contract contract, Audit audit,boolean inserflag)");
		} catch (SevereException se) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractBenefitComponents bo, Contract contract, Audit audit,boolean inserflag)");
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist(ContractBenefitComponents bo, Contract contract, Audit audit,boolean inserflag) in ContractBusinessObjectBuilder",
					errorParams, se);

		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractBenefitComponents bo, Contract contract, Audit audit,boolean inserflag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * This method updates the benefit Component hide flags
	 * 
	 * @param BenefitCustomizationBO
	 * @param mainObject
	 * @param user
	 * @return
	 * @throws SevereException
	 */

	public boolean persist(BenefitCustomizationBO bo, DateSegment dateSegment,
			Audit audit, boolean inserflag) throws SevereException {
		if (bo.getBenefitList() != null && bo.getBenefitList().size() > 0) {
			int benefitCount = 0;
			List benefitList = bo.getBenefitList();
			AdapterServicesAccess serviceAccess = AdapterUtil
					.getAdapterService();

			long transactionId = AdapterUtil.getTransactionId();
			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(BenefitCustomizationBO bo, Contract contract, Audit audit,boolean inserflag)");
				if (benefitList != null && benefitList.size() > 0) {
					BenefitCustomizationBO benefit1 = (BenefitCustomizationBO) benefitList
							.get(0);
					for (int i = 0; i < benefitList.size(); i++) {
						BenefitCustomizationBO benefit = (BenefitCustomizationBO) benefitList
								.get(i);
						benefit.setLastUpdatedTime(audit.getTime());
						benefit.setLastUpdatedUser(audit.getUser());
						benefit.setEntityType("contract");
						
						//String flag = benefit.getIsHidden();
						
						/**Implemented the above using Map :: by KK**/
						/*String keyvalue=benefit.getBenefitComponentId()+"||"+bo.getDateSegmentId();
						Object flagvalue=bo.getBenefitFlagHiddenMap().get(keyvalue);
						
						if(!(flagvalue.equals(flag))){*/
							
						if ("T".equals(benefit.getIsHidden()))
							benefitCount++;

						if (benefit.getIsHidden().equals("T")) {

							benefit.setQuestionsFlag("T");
							benefit.setAdminOptionFlag("T");
							adapterManager.deleteBnftTierDetails(benefit, serviceAccess);
							
						} else {

							benefit.setQuestionsFlag("F");
							benefit.setAdminOptionFlag("F");
						}
						benefit.setLastUpdatedTime(audit.getTime());
						benefit.setLastUpdatedUser(audit.getUser());
						adapterManager.updateBenefits(benefit, serviceAccess);
					//}
					}
					
					if (benefitCount == benefitList.size()) {
						ContractBenefitComponents details = new ContractBenefitComponents();
						details.setBenefitHideFlag("T");
						details.setBenefitComponentHideFlag("T");
						details.setQuestionsFlag("T");
						details.setAdminOptionFlag("T");
						details.setEntityType("contract");
						details.setBenefitComponentId(benefit1
								.getBenefitComponentId());
						details.setProductId(benefit1.getProductId());
						details.setDateSegmentId(benefit1.getDateSegmentId());
						details.setLastUpdatedTimeStamp(audit.getTime());
						details.setLastUpdatedUserId(audit.getUser());
						adapterManager.updateContractBenefitComponentsFlag(
								details, audit.getUser(), serviceAccess);
					}
					this.updateContractAuditInfo(dateSegment, audit,
							serviceAccess);
				}
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(BenefitCustomizationBO bo, Contract contract, Audit audit,boolean inserflag)");
			} catch (SevereException se) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(BenefitCustomizationBO bo, Contract contract, Audit audit,boolean inserflag)");
				List errorParams = new ArrayList(3);
				String obj = se.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception in persist in ContractBusinessObjectBuilder",
						errorParams, se);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(BenefitCustomizationBO bo, Contract contract, Audit audit,boolean inserflag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}
		}

		return true;
	}

	/**
	 * This method delete the notes attached to the uncoded lines/unanswered.
	 * 
	 * @param contractSysId
	 * @return
	 * @throws SevereException
	 */
	public void deleteUncodedNotes(int contractSysId) throws SevereException {
		adapterManager.deleteUncodedNotes(contractSysId);
	}

	public boolean getBenefitComponentStatus(int benefitComponentId,
			int dateSegmentId) throws SevereException {
		ContractBenefitComponents details = new ContractBenefitComponents();
		details = adapterManager.getBenefitComponentStatus(benefitComponentId,
				dateSegmentId);
		return "T".equals(details.getBenefitComponentHideFlag()) ? true : false;

	}

	public List getDomainedNote(AttachedNotesBO attachedNotesBO)
			throws SevereException {
		NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
		List notesList = adapterManager
				.locateUndomainedNotesForContract(attachedNotesBO);
		return notesList;
	}

	/**
	 * This method is to get the latest version of the note
	 * 
	 * @param noteBO
	 * @return List
	 * @throws SevereException
	 */
	public List getLatestVersionNote(NoteBO noteBO) throws SevereException {
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		return adapterManager.getLatestVersionNoteForContract(noteBO);
	}

	public List getBenefits(int dateSegmentId, int benefitCompId,
			boolean showHidden, User user) throws SevereException {

		return adapterManager.getBenefits(dateSegmentId, benefitCompId,
				showHidden, user);
	}

	/**
	 * This method is to get the membership info of the datesegment
	 * 
	 * @param Contract
	 * @return List
	 * @throws SevereException
	 */
	public List getMembershipData(Contract contract) throws SevereException {
		return adapterManager.getMembershipData(contract).getSearchResults();

	}

	/**
	 * 
	 * 
	 * @param dataFeedStatus
	 * @return dataFeedStatus
	 * @throws SevereException
	 */
	public DataFeedStatus getDataFeed(DataFeedStatus dataFeedStatus)
			throws SevereException {
		return adapterManager.getDataFeed(dataFeedStatus);

	}

	/**
	 * 
	 * @param locateCriteria
	 * @param user
	 * @param bool
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(RuleLocateCriteria locateCriteria, User user,
			boolean bool) throws SevereException {

		LocateResults locateResults = new LocateResults();
		SearchResults searchResults = null;

		if (bool == true) {
			searchResults = adapterManager.locateRules(locateCriteria);
		} else {
			searchResults = adapterManager
					.locateRulesAssociated(locateCriteria);
		}
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
				.setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults;
	}

	/**
	 * 
	 * @param locateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateRulesSequence(RuleLocateCriteria locateCriteria,
			User user) throws SevereException {
		LocateResults locateResults = new LocateResults();
		SearchResults searchResults = null;

		searchResults = adapterManager.locateRulesSequences(locateCriteria);

		if (null != searchResults && null != searchResults.getSearchResults()
				&& searchResults.getSearchResults().size() > 0) {

			// List initialList = new ArrayList();
			List finalList = new ArrayList();
			List initialList = searchResults.getSearchResults();
			RuleSequenceResults ruleSequenceResults = new RuleSequenceResults();
			RuleSequenceResults ruleSequenceResultsNext = new RuleSequenceResults();
			int currentId = 0;
			int nextId = 0;
			List codeList = null;
			RuleCodeRanges ruleCodeRanges;
			int iFlag = 0;
			int alreadyAdded = 0;
			if (null != initialList) {
				codeList = new ArrayList(initialList.size());
				for (int i = 0; i < searchResults.getSearchResults().size(); i++) {
					String clmProcessModifierCode = null;
					String procedureModifierCode = null;
					ruleSequenceResults = (RuleSequenceResults) initialList
							.get(i);
					// START CARS CR59
					// DESCRIPTION : checking for claim procedure modifier code
					// and claim procedure modifier code2 is empty or null
					if ((!StringUtil.isEmpty(ruleSequenceResults
							.getClmProcessModifierCode()))
							&& (!StringUtil.isEmpty(ruleSequenceResults
									.getClmProcessModifierSecondaryCode()))) {
						clmProcessModifierCode = ruleSequenceResults
								.getClmProcessModifierCode();
							//	+ ", "
							//	+ ruleSequenceResults
								//		.getClmProcessModifierSecondaryCode();
					} else if (!StringUtil.isEmpty(ruleSequenceResults
							.getClmProcessModifierCode())) {// Checking for
															// claim procedure
															// modifier code is
															// empty or null
						clmProcessModifierCode = ruleSequenceResults
								.getClmProcessModifierCode();
					} else if (!StringUtil.isEmpty(ruleSequenceResults
							.getClmProcessModifierSecondaryCode())) {// Checking
																		// for
																		// claim
																		// procedure
																		// modifier
																		// code2
																		// is
																		// empty
																		// or
																		// null
						clmProcessModifierCode = ruleSequenceResults
								.getClmProcessModifierSecondaryCode();
					}
					// DESCRIPTION : Checking for procedure modifier code and
					// procedure modifier code2 is empty or null
					if ((!StringUtil.isEmpty(ruleSequenceResults
							.getPrecedrModifierCode()))
							&& (!StringUtil.isEmpty(ruleSequenceResults
									.getProcedureModifierSecondaryCode()))) {
						procedureModifierCode = ruleSequenceResults
								.getPrecedrModifierCode();
							/*	+ ", "
								+ ruleSequenceResults
										.getProcedureModifierSecondaryCode();*/
					} else if (!StringUtil.isEmpty(ruleSequenceResults
							.getPrecedrModifierCode())) {// Checking for
															// procedure
															// modifier code is
															// empty or null
						procedureModifierCode = ruleSequenceResults
								.getPrecedrModifierCode();
					} else if (!StringUtil.isEmpty(ruleSequenceResults
							.getProcedureModifierSecondaryCode())) {// Checking
																	// for
																	// procedure
																	// modifier
																	// code2 is
																	// empty or
																	// null
						procedureModifierCode = ruleSequenceResults
								.getProcedureModifierSecondaryCode();
					}
					ruleSequenceResults
							.setClmProcessModifierCode(clmProcessModifierCode);
					ruleSequenceResults
							.setPrecedrModifierCode(procedureModifierCode);
					// END CARS CR59
					currentId = ruleSequenceResults.getRuleSequenceNumber();
					// ruleCodeRanges = new RuleCodeRanges();
					// if (iFlag != 1) {
					// Commented as part of RMA ICD-10 - 05-Jun-2013
					/*
					 * ruleCodeRanges.setCodeType(ruleSequenceResults
					 * .getCodeType());
					 * ruleCodeRanges.setCodeTypeHighVal(ruleSequenceResults
					 * .getCodeTypeHighVal());
					 * ruleCodeRanges.setCodeTypeLowVal(ruleSequenceResults
					 * .getCodeTypeLowVal()); //ICD10 Bug Fix 26-09-11
					 * if(!"".equals
					 * (ruleSequenceResults.getIcdVersionIndicator()) && null !=
					 * ruleSequenceResults.getIcdVersionIndicator())
					 * ruleCodeRanges
					 * .setIcdVersionIndicator(ruleSequenceResults.
					 * getIcdVersionIndicator()); codeList.add(ruleCodeRanges);
					 */
					// }
					// if (i < searchResults.getSearchResults().size() - 1) {
					/*
					 * ruleSequenceResultsNext = (RuleSequenceResults)
					 * initialList .get(i + 1); nextId = ruleSequenceResultsNext
					 * .getRuleSequenceNumber();
					 * 
					 * if (nextId == currentId) { ruleCodeRanges = new
					 * RuleCodeRanges(); //Commented as part of RMA ICD-10 -
					 * 05-Jun-2013
					 * /*ruleCodeRanges.setCodeType(ruleSequenceResultsNext
					 * .getCodeType()); ruleCodeRanges
					 * .setCodeTypeHighVal(ruleSequenceResultsNext
					 * .getCodeTypeHighVal()); ruleCodeRanges
					 * .setCodeTypeLowVal(ruleSequenceResultsNext
					 * .getCodeTypeLowVal()); //ICD10 Bug Fix 26-09-11
					 * if(!"".equals
					 * (ruleSequenceResultsNext.getIcdVersionIndicator()) &&
					 * null != ruleSequenceResultsNext.getIcdVersionIndicator())
					 * ruleCodeRanges
					 * .setIcdVersionIndicator(ruleSequenceResultsNext
					 * .getIcdVersionIndicator());
					 * 
					 * codeList.add(ruleCodeRanges);
					 * 
					 * ruleSequenceResults.setCodeList(codeList); if
					 * (alreadyAdded != 1) { finalList.add(ruleSequenceResults);
					 * } iFlag = 1; alreadyAdded = 1;
					 */
					// } else {
					/*
					 * if (iFlag != 1) {
					 * ruleSequenceResults.setCodeList(codeList);
					 * finalList.add(ruleSequenceResults); } codeList = new
					 * ArrayList(); iFlag = 0; alreadyAdded = 0; continue;
					 */
					// }
					// } else {
					// if(searchResults.getSearchResults().size() ==1 ){
					/*
					 * if (iFlag != 1) {
					 * ruleSequenceResults.setCodeList(codeList);
					 * finalList.add(ruleSequenceResults); } } break; } } }
					 * locateResults.setLocateResults(finalList);
					 * locateResults.setTotalResultsCount(searchResults
					 * .getSearchResultCount()); } else {
					 */
					locateResults.setLocateResults(searchResults
							.getSearchResults());
					locateResults.setTotalResultsCount(searchResults
							.getSearchResultCount());
				}
			}
		}
		
		return locateResults;
	}

	/**
	 * 
	 * @param reserveContractId
	 * @param user
	 * @return reserveContractId
	 * @throws SevereException
	 */
	public ReserveContractId retrieve(ReserveContractId reserveContractId,
			User user) throws SevereException {

		return adapterManager.retrieveReservedContract(reserveContractId);

	}

	/**
	 * 
	 * @param contractRuleBO
	 * @param user
	 * @param insertFlag
	 * @return reserveContractId
	 * @throws SevereException
	 *             ,AdapterException
	 */

	public boolean persist(ContractRuleBO contractRuleBO,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(ContractRuleBO contractRuleBO, User user,boolean insertFlag)");
			if (insertFlag) {
				SearchResults searchResults = null;
				List list = contractRuleBO.getRuleList();
				if (null != list) {
					for (int i = 0; i < list.size(); i++) {

						ContractRuleBO contractRuleBONew = new ContractRuleBO();
						String id = (String) list.get(i);

						int idInt = Integer.parseInt(id);
						contractRuleBONew.setDateSegmentId(contractRuleBO
								.getDateSegmentId());
						contractRuleBONew.setProductRuleSysId(idInt);
						//get the over rid value
						RuleLocateCriteria locateCriteria = new RuleLocateCriteria();
						locateCriteria.setProductRuleSysId(idInt);
						searchResults = adapterManager
								.locateRuleOverValue(locateCriteria);
						if (searchResults != null
								&& searchResults.getSearchResultCount() > 0) {
							List listOne = searchResults.getSearchResults();
							RuleLocateResult ruleResult = (RuleLocateResult) listOne
									.get(0);
							if (null != ruleResult.getRuleOverRideValue())
								contractRuleBONew
										.setRuleOverRidValue(ruleResult
												.getRuleOverRideValue().trim());
						}
						contractRuleBONew.setCreatedTimeStamp(audit.getTime());
						contractRuleBONew.setCreatedUser(audit.getUser());
						contractRuleBONew.setLastUpdatedUser(audit.getUser());
						contractRuleBONew.setLastUpdatedTimeStamp(audit
								.getTime());

						adapterManager.createRuleContractAttachment(
								contractRuleBONew, serviceAccess);

					}
				} else {
					AdapterUtil.endTransaction(serviceAccess);
					AdapterUtil
							.logTheEndTransaction(transactionId, this,
									"persist(ContractRuleBO contractRuleBO, User user,boolean insertFlag)");
					return true;
				}
			} else {// update process
				List listOne = contractRuleBO.getRuleList();
				if (null != listOne) {
					for (int i = 0; i < listOne.size(); i++) {

						ContractRuleBO contractRuleBONew = new ContractRuleBO();
						ContractRuleBO contrctRulB0 = (ContractRuleBO) listOne
								.get(i);
						int idInt = contrctRulB0.getProductRuleSysId();
						//getting the values form the list
						contractRuleBONew.setProductRuleSysId(idInt);
						contractRuleBONew.setRuleOverRidValue(contrctRulB0
								.getRuleOverRidValue());
						//setting common date segment id
						contractRuleBONew.setDateSegmentId(contractRuleBO
								.getDateSegmentId());
						contractRuleBONew.setLastUpdatedUser(audit.getUser());
						contractRuleBONew.setLastUpdatedTimeStamp(audit
								.getTime());
						adapterManager.updateRuleContractAttachment(
								contractRuleBONew, serviceAccess);
					}

				}
			}
			this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(ContractRuleBO contractRuleBO, User user,boolean insertFlag)");
		} catch (SevereException se) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ContractRuleBO contractRuleBO, User user,boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ContractRuleBO contractRuleBO, User user,boolean insertFlag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;

	}

	/**
	 * 
	 * @param reserveContract
	 * @param user
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 *             ,AdapterException
	 */
	public boolean persist(ReserveContractId reserveContract, User user,
			boolean insertFlag) throws SevereException {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		ArrayList reservedList = null;
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
			if (insertFlag) {
				ReserveContractId reserveContractNew;
				int number = reserveContract.getNumberOfIdToGenerate();
				reservedList = new ArrayList(number);
				while (number > 0) {
					//      String contractId = generateContractId();
					SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
					int contractIdSequence = sequenceAdapterManager
							.getNextContractIdSequence();
					if (0 == contractIdSequence) {
						AdapterUtil.endTransaction(serviceAccess);
						AdapterUtil
								.logTheEndTransaction(transactionId, this,
										"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
						return false;
					}
					SystemContractId contractIdInfo = new SystemContractId();
					contractIdInfo.setContractSequence(contractIdSequence);
					contractIdInfo = (SystemContractId) AdapterUtil
							.performRetrieve(contractIdInfo);

					if (null == contractIdInfo
							|| "".equals(contractIdInfo.getContractId())) {
						AdapterUtil.endTransaction(serviceAccess);
						AdapterUtil
								.logTheEndTransaction(transactionId, this,
										"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
						return false;
					}
					String contractId = contractIdInfo.getContractId();
					reserveContractNew = new ReserveContractId();
					//creating process
					reserveContractNew.setContractId(contractId);
					reserveContractNew.setStatus("Y");
					reserveContractNew.setCreatedUser(audit.getUser());
					reserveContractNew.setLastUpdatedUser(audit.getUser());
					reserveContractNew.setBusinessDomains(reserveContract
							.getBusinessDomains());
					reserveContractNew.setStartDate(reserveContract
							.getStartDate());
					reserveContractNew.setEndDate(reserveContract.getEndDate());
					reserveContractNew.setCreatedTimeStamp(audit.getTime());
					reserveContractNew.setLastUpdatedTimeStamp(audit.getTime());
					int sysId = sequenceAdapterManager
							.getNextReserevedContractSysIdSequence();
					reserveContractNew.setParentSysId(sysId);
					adapterManager.createReservedContract(reserveContractNew,
							serviceAccess);

					//deleting process
					SystemContractId sysIdToDelete = new SystemContractId();
					sysIdToDelete.setContractId(contractId);
					sysIdToDelete.setContractSequence(contractIdSequence);

					adapterManager.deleteCreatedReservedContracts(
							sysIdToDelete, user, serviceAccess);
					reservedList.add(sysIdToDelete.getContractId());

					//save domain values
					DomainDetail domainDetail = new DomainDetail();

					setValuesToDomainForReserved(reserveContractNew,
							domainDetail, audit);
					DomainUtil.persistAssociatedDomains(domainDetail,
							serviceAccess);

					number--;

				}
				reserveContract.setReservedList(reservedList);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
				return true;
			} else {

				ReserveContractId reserveContractNew = new ReserveContractId();
				reserveContractNew.setContractId(reserveContract
						.getContractId());
				ReserveContractId reserveContractIdRetrieved = adapterManager
						.retrieveReservedContract(reserveContractNew);
				reserveContractNew.setEndDate(reserveContract.getEndDate());
				reserveContractNew.setLastUpdatedUser(audit.getUser());
				reserveContractNew.setLastUpdatedTimeStamp(audit.getTime());
				reserveContractNew.setStatus(reserveContractIdRetrieved
						.getStatus());
				reserveContractNew
						.setCreatedTimeStamp(reserveContractIdRetrieved
								.getCreatedTimeStamp());
				reserveContractNew.setCreatedUser(reserveContractIdRetrieved
						.getCreatedUser());
				reserveContractNew.setContractId(reserveContractIdRetrieved
						.getContractId());
				adapterManager.updateReservedContract(reserveContractNew,
						serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
				return true;

			}
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ReserveContractId reserveContract, User user,boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Persist failed for ReserveContractId in ContractBuilder",
					errorParams, ex);
		}
	}

	/**
	 * 
	 * @param reserveContract
	 * @param user
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 *             ,AdapterException
	 */
	public void setValuesToDomainForReserved(ReserveContractId reserveContract,
			DomainDetail domainDetail, Audit audit) {
		domainDetail.setEntityType(WebConstants.RESERVER_CONTRACT);
		domainDetail.setEntityName(reserveContract.getContractId());
		domainDetail.setCreatedTimeStamp(audit.getTime());
		domainDetail.setCreatedUser(reserveContract.getLastUpdatedUser());
		domainDetail.setLastUpdatedUser(reserveContract.getLastUpdatedUser());
		domainDetail.setLastUpdatedTimeStamp(audit.getTime());
		domainDetail.setEntityParentId(reserveContract.getParentSysId());
		domainDetail.setDomainList(reserveContract.getBusinessDomains());
	}

	/**
	 * 
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(Contract contract, Audit audit, boolean insertFlag)
			throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {

			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(Contract contract, Audit audit, boolean insertFlag)");
			if (insertFlag) {
				SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
				int sysId = sequenceAdapterManager
						.getNextContractSysIdSequence();
				contract.setContractSysId(sysId);
				if (1 >= contract.getVersion())
					contract.setParentSysId(sysId);
				contract.setCreatedUser(audit.getUser());
				contract.setCreatedTimestamp(audit.getTime());
				contract.setLastUpdatedUser(audit.getUser());
				contract.setLastUpdatedTimestamp(audit.getTime());
				
				adapterManager.createContract(contract, serviceAccess);
				if (1 >= contract.getVersion()) {
					DomainDetail domainDetail = new DomainDetail();
					
					setValuesToDomain(contract, domainDetail);
					DomainUtil.persistAssociatedDomains(domainDetail,
							serviceAccess);
				}

			} else {
				contract.setLastUpdatedUser(audit.getUser());
				contract.setLastUpdatedTimestamp(audit.getTime());
				adapterManager.updateContract(contract, serviceAccess);
				List dateSegmentList = contract.getDateSegmentList();
				if (dateSegmentList != null) {
					DateSegment dateSegment = (DateSegment) dateSegmentList
							.get(0);
					dateSegment.setLastUpdatedUser(audit.getUser());
					dateSegment.setLastUpdatedTimestamp(audit.getTime());
					adapterManager
							.updateDateSegment(dateSegment, serviceAccess);
					dateSegment.getContractStatusBo().setLastChangedUserId(audit.getUser());
					adapterManager.updateContractStatus(dateSegment.getContractStatusBo(),serviceAccess);
				}

			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(Contract contract, Audit audit, boolean insertFlag)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(Contract contract, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist Contract in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(Contract contract, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}
		return true;
	}

	/**
	 * 
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(TestData subObject, Contract maniObject,
			Audit audit, boolean insertFlag) throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(TestData subObject, Contract maniObject,Audit audit, boolean insertFlag)");
			subObject.setLastUpdatedUser(audit.getUser());
			subObject.setLastUpdatedTimeStamp(audit.getTime());
			if (insertFlag) {
				subObject.setCreatedUser(audit.getUser());
				subObject.setCreatedTimeStamp(audit.getTime());
				adapterManager.createTestData(subObject, serviceAccess);
			} else {
				adapterManager.updateTestData(subObject, serviceAccess);
			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(TestData subObject, Contract maniObject,Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(TestData subObject, Contract maniObject,Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist TestData in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(TestData subObject, Contract maniObject,Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}
		return true;

	}

	/**
	 * 
	 * @param ruleIdBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(RuleIdBO ruleIdBO, String user)
			throws SevereException {

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"persist(RuleIdBO ruleIdBO, String user)");
			adapterManager.saveRuleInfo(ruleIdBO, user, serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"persist(RuleIdBO ruleIdBO, String user)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"persist(RuleIdBO ruleIdBO, String user)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist RuleIdBO in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"persist(RuleIdBO ruleIdBO, String user)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}
		return true;
	}

	/**
	 * This method updates the benefitline values
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */

	public boolean persist(ContractBenefitDefinitions subObject,
			DateSegment mainObject, Audit audit, boolean insertFlag)
			throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractBusinessObjectBuilder","persist()"));

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering persist(ContractBenefitDefinitions subObject="
						+ subObject
						+ ":Contract mainObject="
						+ mainObject
						+ ":Audit audit="
						+ audit
						+ ":boolean insertFlag="
						+ insertFlag);

		List updatedBenefitLineList = subObject.getUpdatedBenefitLines();
		List savedBenefitLineList = subObject.getSavedBenefitLines();
		List deletedBenefitLineList = subObject.getDeletedBenefitLines();
		BenefitLine benefitLineB0 = null;
		int bnftComponentId = subObject.getBenefitComponentId();

		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ContractBenefitDefinitions subObject,Contract mainObject, Audit audit, boolean insertFlag)");
			if (updatedBenefitLineList.size() != 0) {
				BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
				for (int i = 0; i < updatedBenefitLineList.size(); i++) {

					benefitLineB0 = (BenefitLine) updatedBenefitLineList.get(i);

					ContractBenefitHeadings contractBenefitHeadings = new ContractBenefitHeadings();
					contractBenefitHeadings
							.setBenefitComponentId(bnftComponentId);
					contractBenefitHeadings.setBenefitValue(benefitLineB0
							.getBenefitValue());
					/* START CARS */
					contractBenefitHeadings.setFrequencyValue(benefitLineB0
							.getFrequencyValue());
					contractBenefitHeadings.setLevelDesc(benefitLineB0
							.getLevelDesc());
					/* END CARS */
					contractBenefitHeadings.setBenefitLineId(benefitLineB0
							.getLineSysId());
					contractBenefitHeadings.setDateSegmentId(subObject
							.getDateSegmentId());
					contractBenefitHeadings.setLastUpdatedUser(audit.getUser());
					contractBenefitHeadings.setLastUpdatedTimestamp(audit
							.getTime());
					contractBenefitHeadings.setBenefitComponentName("BCNAME");
					contractBenefitHeadings.setStandardBenefitName("SBNAME");
					//passes each individual benefit line for updation
					benefitAdapterManager.updateOverridenValueForContractBC(
							contractBenefitHeadings, adapterServicesAccess);

				}
				this.updateContractAuditInfo(mainObject, audit,
						adapterServicesAccess);
			}
			if (savedBenefitLineList.size() != 0) { 
				BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
				SequenceAdapterManager sequenceAdapter = new SequenceAdapterManager();
				for (int i = 0; i < savedBenefitLineList.size(); i++) {

					benefitLineB0 = (BenefitLine) savedBenefitLineList.get(i);
					//int seq = sequenceAdapter.getNextCntrctBnftCstmzdSysId();				
					
					ContractBenefitHeadings contractBenefitHeadings = new ContractBenefitHeadings();
					//contractBenefitHeadings.setSequenceNumber(seq);
					contractBenefitHeadings.setBenefitComponentId(bnftComponentId);
					contractBenefitHeadings.setBenefitValue(benefitLineB0.getBenefitValue());
					 //START CARS 
					contractBenefitHeadings.setFrequencyValue(benefitLineB0.getFrequencyValue());
					contractBenefitHeadings.setLevelDesc(benefitLineB0.getLevelDesc());
					// END CARS 
					contractBenefitHeadings.setBenefitLineId(benefitLineB0.getLineSysId());
					contractBenefitHeadings.setDateSegmentId(subObject.getDateSegmentId());
					contractBenefitHeadings.setProductId(subObject.getProductSysId());
					contractBenefitHeadings.setStandardBenefitId(subObject.getBnftSysId());
					contractBenefitHeadings.setCreatedUser(audit.getUser());
					contractBenefitHeadings.setCreatedTimestamp(audit.getTime());
					contractBenefitHeadings.setLastUpdatedUser(audit.getUser());
					contractBenefitHeadings.setLastUpdatedTimestamp(audit.getTime());
					contractBenefitHeadings.setBenefitComponentName("BCNAME");
					contractBenefitHeadings.setStandardBenefitName("SBNAME");
					//contractBenefitHeadings.setLevelHideStatus(benefitLineB0.isLevelHideStatus());
					//contractBenefitHeadings.setLineHideStatus(benefitLineB0.isLineHideStatus());
					contractBenefitHeadings.setBnftCmpntHideStatus("F");
					contractBenefitHeadings.setBnftHideStatus("F");
					contractBenefitHeadings.setLevelSysId(benefitLineB0.getLevelSysId());
					
					contractBenefitHeadings.setBenefitDefnSysId(subObject.getBnftDefnSysId());
					//passes each individual benefit line for insertion
					benefitAdapterManager.saveNewlyCodedLinesForContractBC(
							contractBenefitHeadings, adapterServicesAccess);

				}
				this.updateContractAuditInfo(mainObject, audit,
						adapterServicesAccess);
			}
			if (deletedBenefitLineList.size() != 0) {
				BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
				for (int i = 0; i < deletedBenefitLineList.size(); i++) {

					benefitLineB0 = (BenefitLine) deletedBenefitLineList.get(i);

					ContractBenefitHeadings contractBenefitHeadings = new ContractBenefitHeadings();
					contractBenefitHeadings
							.setBenefitComponentId(bnftComponentId);
					contractBenefitHeadings.setBenefitValue(benefitLineB0
							.getBenefitValue());
					// START CARS 
					contractBenefitHeadings.setFrequencyValue(benefitLineB0
							.getFrequencyValue());
					contractBenefitHeadings.setLevelDesc(benefitLineB0
							.getLevelDesc());
					// END CARS 
					contractBenefitHeadings.setBenefitLineId(benefitLineB0
							.getLineSysId());
					contractBenefitHeadings.setDateSegmentId(subObject
							.getDateSegmentId());
					contractBenefitHeadings.setLastUpdatedUser(audit.getUser());
					contractBenefitHeadings.setLastUpdatedTimestamp(audit
							.getTime());
					contractBenefitHeadings.setBenefitComponentName("BCNAME");
					contractBenefitHeadings.setStandardBenefitName("SBNAME");
					//passes each individual benefit line for updation
					benefitAdapterManager.deleteUnCodedLinesForContractBC(
							contractBenefitHeadings, adapterServicesAccess);
					/*benefitAdapterManager.updateOverridenValueForContractBC(
							contractBenefitHeadings, adapterServicesAccess);*/

				}
				this.updateContractAuditInfo(mainObject, audit,
						adapterServicesAccess);
			}
			
			if (null != subObject.getBenefitTierDefinitionList()) {
				
				ContractTierBindingObject tierObject = null;
				for (Iterator defIterator = subObject
						.getBenefitTierDefinitionList().iterator(); defIterator
						.hasNext();) {

					BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator
							.next();
					for (Iterator tierBoIterator = tierDefinition
							.getBenefitTiers().iterator(); tierBoIterator
							.hasNext();) {
						BenefitTier benefitTier = (BenefitTier) tierBoIterator
								.next();
						for (Iterator criteriaIterator = benefitTier
								.getBenefitTierCriteriaList().iterator(); criteriaIterator
								.hasNext();) {
							BenefitTierCriteria tierCriteria = (BenefitTierCriteria) criteriaIterator
									.next();
							tierObject = new ContractTierBindingObject();
							tierObject.setTierDefinitionId(tierDefinition
									.getBenefitTierDefinitionSysId());
							tierObject.setBenefitTierId(benefitTier
									.getBenefitTierSysId());
							tierObject.setTierCriteriaId(tierCriteria
									.getBenefitTierCriteriaSysId());
							tierObject.setCriteriaValue(tierCriteria
									.getBenefitTierCriteriaValue());
							tierObject.setLastUpdatedTimestamp(audit.getTime());
							tierObject.setLastUpdatedUser(audit.getUser());
							
							adapterManager.updateBenefitTierDetail(tierObject,
									audit, adapterServicesAccess);
						}
					}
				}

			}
			if (null != subObject.getBenefitTierLevelList()) {
				
				ContractTierBindingObject tierObject = null;
				for (Iterator benLevelItr = subObject.getBenefitTierLevelList()
						.iterator(); benLevelItr.hasNext();) {
					BenefitLevel benefitLevel = (BenefitLevel) benLevelItr
							.next();
					for (Iterator benLineItr = benefitLevel.getBenefitLines()
							.iterator(); benLineItr.hasNext();) {
						BenefitLine benefitLine = (BenefitLine) benLineItr
								.next();
						tierObject = new ContractTierBindingObject();
						tierObject.setBenefitLineId(benefitLine.getLineSysId());
						tierObject.setBenefitLevelId(benefitLevel.getLevelId());
						tierObject.setLineValue(benefitLine.getLineValue());
						/* Start CARS */
						tierObject.setLevelDescription(benefitLine
								.getLevelDesc());
						tierObject.setFrequencyValue(benefitLine
								.getFrequencyValue());
						/* End CARS */
						tierObject
								.setBenefitTierId(benefitLevel.getTierSysId());
						tierObject.setLastUpdatedTimestamp(audit.getTime());
						tierObject.setLastUpdatedUser(audit.getUser());
						
						adapterManager.updateBenefitTierDetail(tierObject,
								audit, adapterServicesAccess);
					}
				}
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ContractBenefitDefinitions subObject,Contract mainObject, Audit audit, boolean insertFlag)");

		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractBenefitDefinitions subObject,Contract mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist ContractBenefitDefinitions in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractBenefitDefinitions subObject,Contract mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		Logger.logInfo(th.endPerfLogging());
		return true;
	}

	/**
	 * 
	 * @param dateSegmentCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(DateSegmentLocateCriteria dateSegmentCriteria,
			User user) throws SevereException {

		LocateResults locateResults = new LocateResults();
		
		if (dateSegmentCriteria.isFetchOlderVersion()) {
			locateResults.setLocateResults(adapterManager
					.fetchOlderVersionsForDateSegment(dateSegmentCriteria
							.getContractId(), dateSegmentCriteria
							.getEffectiveDate(),
							// dateSegmentCriteria.getExpiryDate(), 
							 dateSegmentCriteria.getVersion()));
		}else{
			locateResults.setLocateResults(adapterManager
					.getAllDateSegments(dateSegmentCriteria.getContractSysId()));
		}
		return locateResults;
	}

	/**
	 * 
	 * @param contractLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractAllVersionsLocateCriteria contractLocateCriteria, User user)
			throws SevereException {

		LocateResults locateResultsAllVersions = new LocateResults();
		locateResultsAllVersions
				.setLocateResults(adapterManager
						.getAllContractVersions(contractLocateCriteria
								.getContractId()));
		return locateResultsAllVersions;
	}

	/**
	 * 
	 * @param contract
	 * @return contract
	 * @throws SevereException
	 */
	public Contract retrieveLatestVersion(Contract contract)
			throws SevereException {
		SearchResults result = (SearchResults) adapterManager
				.searchContractLatestVesrion(contract);
		if (null != result) {
			List resultList = result.getSearchResults();

			if (null != resultList && !resultList.isEmpty()) {
				contract = (Contract) resultList.get(0);
			}
		}
		if (contract == null){
			return null;
		}
		else {
			contract.setBusinessDomains(DomainUtil.retrieveAssociatedDomains(
					WebConstants.CONTRACT, contract.getParentSysId()));
			return contract;
		}
	}

	/**
	 * 
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	public int retrieveLatestVersionNumber(Contract contract)
			throws SevereException {

		SearchResults result = (SearchResults) adapterManager
				.searchContractLatestVersionNumber(contract);
		if (null != result) {
			List resultList = result.getSearchResults();
			if (null != resultList && !resultList.isEmpty()) {

				int version = ((Contract) resultList.get(0)).getVersion();
				return version;
			}
		}
		return -1;
	}

	public String contractStatusFromLatestVersion(String contractID)
			throws SevereException {
		List resultList = this.adapterManager
				.contractStatusFromLatestVersion(contractID);
		if (null != resultList && !resultList.isEmpty()) {
			return ((Contract) resultList.get(0)).getStatus();
		}
		return null;
	}

	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public boolean isBaseContractInMarkForDeleteStatus(String contractId)
			throws SevereException {
		return this.adapterManager
				.isBaseContractInMarkForDeleteStatus(contractId);
	}

	/**
	 * 
	 * @param catlogId
	 * @param code
	 * @return affiliationObject
	 * @throws SevereException
	 */
	private AffiliationObject getAffiliationObj(int catlogId, String code)
			throws SevereException {
		if (code == null || "".equals(code))
			return null;
		AffiliationObject affiliationObject = new AffiliationObject();
		affiliationObject.setCatlogId(catlogId);
		affiliationObject.setCode(code);
		affiliationObject = (AffiliationObject) AdapterUtil
				.performRetrieve(affiliationObject);
		return affiliationObject;
	}

	/**
	 * 
	 * @param contract
	 * @return contract
	 * @throws SevereException
	 */
	public Contract retrieve(Contract contract) throws SevereException {
		Contract contract2 = adapterManager.retrieveContract(contract);
		return contract2; 
	}

	/**
	 * 
	 * @param contractSysId
	 * @return ProductBO
	 * @throws SevereException
	 */
	public ProductBO retrieveCurrentProduct(int contractSysId)
			throws SevereException {
		return adapterManager.getCurrentProduct(contractSysId);
	}

	/**
	 * 
	 * @param contractSysId
	 * @return
	 * @throws SevereException
	 */
	public List checkMarkedForDeletionNotes(int contractSysId)
			throws SevereException {
		return adapterManager.checkMarkedForDeletionNotes(contractSysId);
	}

	/**
	 * 
	 * @param contractSysId
	 * @return NoteBO
	 * @throws SevereException
	 */
	public NoteBO retrieveCurrentNote(int contractSysId) throws SevereException {
		return adapterManager.getCurrentNote(contractSysId);
	}

	/**
	 * 
	 * @param contractSysId
	 * @return ProductBO
	 * @throws SevereException
	 */
	public ProductBO retrieveLatestProductVersion(int contractSysId,
			int dateSegmentid) throws SevereException {
		return adapterManager.getLatestProductVersion(contractSysId,
				dateSegmentid);
	}

	public ProductBO retrieveLatestProductVersion(int contractSysId,
			Date effectiveDate, int productSysId) throws SevereException {
		return adapterManager.getLatestProductVersion(contractSysId,
				effectiveDate, productSysId);
	}

	/**
	 * 
	 * @param contract
	 * @param params
	 * @return contract
	 * @throws SevereException
	 */

	public Contract retrieve(Contract contract, Map params)
			throws SevereException {
		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_CONTRACT_PRODUCT_GENERAL_INFO)) {
			contract = adapterManager.getContractProductGeneralInfo(contract);
		}
		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_CONTRACT_SPECIFIC_INFO)) {

			int dateSegmentId = ((Integer) params
					.get(WebConstants.DATESEGMENT_ID)).intValue();
			contract = adapterManager.getContractSpecificInfoData(contract,
					dateSegmentId);

			//to retrieve the list of provider specilaity codes
			ProviderSpecialityCodeBO providerSpecialityCodeBO = new ProviderSpecialityCodeBO();
			providerSpecialityCodeBO.setDateSegmentSysId(dateSegmentId);
			List codeList = adapterManager
					.getProviderSpecialityCodes(providerSpecialityCodeBO);
			contract.setProviderSpecialityCodeList(codeList);

		}
		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_CONTRACT_ADAPTED_INFO)) {

			int dateSegmentId = ((Integer) params
					.get(WebConstants.DATESEGMENT_ID)).intValue();
			contract = adapterManager.getAdaptedInfoData(contract,
					dateSegmentId);
		}

		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_CONTRACT_PRICING_INFO)) {

			int dateSegmentId = ((Integer) params
					.get(WebConstants.DATESEGMENT_ID)).intValue();
			contract = adapterManager.getContractPricingInfoList(contract,
					dateSegmentId);
		}

		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_COMMENT)) {
			DateSegment dateSegment = new DateSegment();
			int dateSegmentId = ((Integer) params
					.get(WebConstants.DATESEGMENT_ID)).intValue();
			List commentList = adapterManager.retrieveComments(dateSegmentId,
					"10");
			dateSegment.setCommentsList(commentList);
			List dateSegmentList = new ArrayList(2);
			dateSegmentList.add(dateSegment);
			contract.setDateSegmentList(dateSegmentList);

		}
		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_ALL_COMMENT)) {
			DateSegment dateSegment = new DateSegment();
			int dateSegmentId = ((Integer) params
					.get(WebConstants.DATESEGMENT_ID)).intValue();
			List commentList = adapterManager.retrieveComments(dateSegmentId,
					WebConstants.NO_LIMIT);
			dateSegment.setCommentsList(commentList);
			List dateSegmentList = new ArrayList(2);
			dateSegmentList.add(dateSegment);
			contract.setDateSegmentList(dateSegmentList);

		}
		if (params.get(WebConstants.ACTION).equals(
				WebConstants.RETRIEVE_SELECTED_COMMENT)) {
			DateSegment dateSegment = new DateSegment();
			Comment comment = (Comment) params.get(WebConstants.COMMENT_BO);
			List commentList = new ArrayList(2);
			Comment commentBO = adapterManager
					.retrieveSelectedComments(comment);
			commentList.add(commentBO);
			dateSegment.setCommentsList(commentList);
			List dateSegmentList = new ArrayList(2);
			dateSegmentList.add(dateSegment);
			contract.setDateSegmentList(dateSegmentList);
		}
		return contract;
	}

	/**
	 * 
	 * @param dateSegmentUpdateAfterDeleteBO
	 * @return
	 * @throws SevereException
	 */
	public void updateAfterDeleteDateSegment(
			DateSegmentUpdateAfterDeleteBO dateSegment) throws SevereException {
		this.adapterManager.updateAfterDeleteDateSegment(dateSegment);
	}

	/**
	 * 
	 * @param dateSegment
	 * @param contract
	 * @param audit
	 * @return
	 * @throws SevereException,
	 *             AdapterException
	 */

	public boolean delete(DateSegment dateSegment, Contract contract,
			Audit audit) throws SevereException {

		AdapterServicesAccess dateSegmentAdapterServiceAccess = AdapterUtil
				.getAdapterService();

		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"delete(DateSegment dateSegment, Contract contract,Audit audit)");
			this.adapterManager.deleteDateSegment(dateSegment, contract, audit,
					dateSegmentAdapterServiceAccess);
			AdapterUtil.endTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"delete(DateSegment dateSegment, Contract contract,Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"delete(DateSegment dateSegment, Contract contract,Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in delete DateSegment in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"delete(DateSegment dateSegment, Contract contract,Audit audit)");
			throw new SevereException(null, null, ex);
		}
		return true;
	}

	/**
	 * 
	 * @param datesegment
	 * @return DtaeSegment
	 * @throws SevereException
	 */
	public DateSegment retrieveLatestVersion(DateSegment dateSegment)
			throws SevereException {
		SearchResults result = (SearchResults) adapterManager
				.searchDateSegmentLatestVersion(dateSegment);

		if (null != result) {
			List resultList = result.getSearchResults();

			if (null != resultList && !resultList.isEmpty()) {
				dateSegment = (DateSegment) resultList.get(0);
			}
		}

		return dateSegment;
	}

	/**
	 * 
	 * @param datesegment
	 * @return int
	 * @throws SevereException
	 */
	public int retrieveLatestVersionNumber(DateSegment dateSegment)
			throws SevereException {

		SearchResults result = (SearchResults) adapterManager
				.searchDateSegmentLatestVersionNumber(dateSegment);
		if (null != result) {
			List resultList = result.getSearchResults();
			if (null != resultList && !resultList.isEmpty()) {

				int version = ((DateSegment) resultList.get(0)).getVersion();
				return version;
			}
		}
		return -1;
	}

	/**
	 * Method to Delete latest version of contract.
	 * 
	 * @param contract
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean delete(Contract contract, Audit audit)
			throws SevereException {

		int contractSysId = contract.getContractSysId();
		int contractDateSegmentSysId;
		//        List dateSegmentList = new ArrayList();
		List dateSegmentList = this.adapterManager
				.getAllDateSegments(contractSysId);

		DateSegment dateSegment = new DateSegment();
		ContractLocateResult contractLocateResult = new ContractLocateResult();

		AdapterServicesAccess dateSegmentAdapterServiceAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"delete(Contract contract, Audit audit)");

			for (int i = 0; i < dateSegmentList.size(); i++) {
				contractLocateResult = (ContractLocateResult) dateSegmentList
						.get(i);
				contractDateSegmentSysId = contractLocateResult
						.getDateSegmentId();

				dateSegment.setDateSegmentSysId(contractDateSegmentSysId);
				dateSegment.setEffectiveDate(contractLocateResult
						.getStartDate());
				dateSegment.setDateSegmentType(contractLocateResult
						.getTestIndicator());
				this.adapterManager.deleteDateSegment(dateSegment, contract,
						audit, dateSegmentAdapterServiceAccess);
			}
			this.adapterManager.deleteContract(contract, audit,
					dateSegmentAdapterServiceAccess);
			AdapterUtil.endTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"delete(Contract contract, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"delete(Contract contract, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in delete Contract in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(dateSegmentAdapterServiceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"delete(Contract contract, Audit audit)");
			throw new SevereException(null, null, ex);
		}
		return true;
	}

	/**
	 * Method to Delete latest version of contract.
	 * 
	 * @param contract
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean deleteLatestVersion(Contract contract, Audit audit)
			throws SevereException {
		this.delete(contract, audit);
		return true;
	}

	/**
	 * 
	 * @param locateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(ContractLocateCriteria locateCriteria, User user)
			throws SevereException {
		LocateResults locateResults = new LocateResults();
		SearchResults searchResults = null;
		searchResults = adapterManager.locateContract(locateCriteria);
		//logic for separating repeated ds with test and regular
		List searchResultList = new ArrayList();
		searchResultList = searchResults.getSearchResults();
		locateResults.setLocateResults(searchResultList);
		locateResults
				.setTotalResultsCount(searchResults.getSearchResultCount());
		locateResults.setLatestVersion(true);
		return locateResults;
	}

	/**
	 * 
	 * @param contract
	 * @param domainDetail
	 * @return
	 * @throws
	 */
	public void setValuesToDomain(Contract contract, DomainDetail domainDetail) {
		domainDetail.setEntityType(WebConstants.CONTRACT);
		domainDetail.setEntityName(contract.getContractId());
		domainDetail.setCreatedTimeStamp(contract.getLastUpdatedTimestamp());
		domainDetail.setCreatedUser(contract.getLastUpdatedUser());
		domainDetail.setLastUpdatedUser(contract.getLastUpdatedUser());
		domainDetail
				.setLastUpdatedTimeStamp(contract.getLastUpdatedTimestamp());
		domainDetail.setEntityParentId(contract.getParentSysId());
		domainDetail.setDomainList(contract.getBusinessDomains());
	}

	/**
	 * 
	 * @param
	 * @return contractId
	 * @throws SevereException
	 */
	public String getInitialContractId() throws SevereException {
		String contractId = "";
		List list = adapterManager.getInitialContracts();
		if (null != list && list.size() > 0) {

			SystemContractId sysId = (SystemContractId) list.get(0);
			if (null != sysId) {
				contractId = sysId.getContractId();
			}
		}
		return contractId;
	}

	/**
	 * 
	 * @param
	 * @return contractId
	 * @throws SevereException
	 */
	public SystemContractId generateContractId() throws SevereException {
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int contractIdSequence = sequenceAdapterManager
				.getNextContractIdSequence();
		SystemContractId contractIdInfo = new SystemContractId();
		contractIdInfo.setContractSequence(contractIdSequence);
		contractIdInfo = (SystemContractId) AdapterUtil
				.performRetrieve(contractIdInfo);
		return contractIdInfo;

	}

	/**
	 * 
	 * @param
	 * @return contractId
	 * @throws SevereException
	 */
	public String generateCurrentContractId() throws SevereException {
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int contractIdSequence = sequenceAdapterManager
				.getCurrentContractIdSequence();
		SystemContractId contractIdInfo = new SystemContractId();
		//increments to get the unused cntractid
		contractIdSequence++;
		contractIdInfo.setContractSequence(contractIdSequence);
		contractIdInfo = (SystemContractId) AdapterUtil
				.performRetrieve(contractIdInfo);

		if (null == contractIdInfo) {
			return null;
		}
		return contractIdInfo.getContractId();
	}

	/**
	 * Method to retrieve Overrided Benefit Defenitions.
	 * 
	 * @param contractBenefitDefinitionLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws WPDException
	 */
	public LocateResults locate(
			ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria,
			User user) throws SevereException {	
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractBusinessObjectBuilder","locate()"));
		
		
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering locate(ContractBenefitDefinitionLocateCriteria contractBenefitDefinitionLocateCriteria="
						+ contractBenefitDefinitionLocateCriteria
						+ ":User user=" + user);
		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		LocateResults locateResults = new LocateResults();
		if (contractBenefitDefinitionLocateCriteria.getAction() == 2) {
			locateResults.setLocateResults(benefitAdapterManager
					.getOverridedBenefitLinesForDatafeed(
							BusinessConstants.ENTITY_TYPE_CONTRACT,
							contractBenefitDefinitionLocateCriteria
									.getDateSegmentId(),
							contractBenefitDefinitionLocateCriteria
									.getBenefitId(),
							contractBenefitDefinitionLocateCriteria
									.getBenefitComponentId()));
		} else if (contractBenefitDefinitionLocateCriteria.getAction() == 3) {

			List searchResults = benefitAdapterManager.getTierCriteriaList(
					BusinessConstants.ENTITY_TYPE_CONTRACT,
					contractBenefitDefinitionLocateCriteria.getDateSegmentId(),
					contractBenefitDefinitionLocateCriteria.getBenefitId(),
					contractBenefitDefinitionLocateCriteria
							.getBenefitComponentId());
			locateResults.setLocateResults(searchResults);
		} else if (contractBenefitDefinitionLocateCriteria.getAction() == 4) {

			List searchResults = benefitAdapterManager.getBenefitLvlLineList(
					BusinessConstants.ENTITY_TYPE_CONTRACT,
					contractBenefitDefinitionLocateCriteria.getDateSegmentId(),
					contractBenefitDefinitionLocateCriteria
							.getBenefitComponentId(),
					contractBenefitDefinitionLocateCriteria.getTierSysIdList());
			//searchBenefitTermsQualifierDescForCorrespondingBenefitLevelsForTier(searchResults);
			locateResults.setLocateResults(searchResults);
		}else if (contractBenefitDefinitionLocateCriteria.getAction() == 5) {

			List searchResults = benefitAdapterManager.getBenefitTierTermsAndPVAs(
					BusinessConstants.ENTITY_TYPE_CONTRACT,
					contractBenefitDefinitionLocateCriteria.getDateSegmentId(),
					contractBenefitDefinitionLocateCriteria.getBenefitComponentId(),
					contractBenefitDefinitionLocateCriteria.getTierSysIdList());
			//searchBenefitTermsQualifierDescForCorrespondingBenefitLevelsForTier(searchResults);
			locateResults.setLocateResults(searchResults);
		}
		else {

			List searchResults = benefitAdapterManager
					.getAllOverridedBenefitLinesSearch(
							BusinessConstants.ENTITY_TYPE_CONTRACT,
							contractBenefitDefinitionLocateCriteria
									.getDateSegmentId(),
							contractBenefitDefinitionLocateCriteria
									.getBenefitId(),
							contractBenefitDefinitionLocateCriteria
									.getBenefitComponentId());
			/*
			 * List benefitLevels = null; BenefitlevelAdapterManager
			 * adapterManager = new BenefitlevelAdapterManager(); if (null !=
			 * searchResults) { benefitLevels =
			 * convertTOLevelBOsList(searchResults);
			 * searchBenefitTermsDescForCorrespondingBenefitLevel(benefitLevels);
			 * searchBenefitQualifiersDescForCorrespondingBenefitLevel(benefitLevels); }
			 */
			//searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(searchResults);

			locateResults.setLocateResults(searchResults);

		}
		Logger.logInfo(th.endPerfLogging());
		return locateResults;
	}

	/**
	 * 
	 * @param searchResultsList
	 * @throws SevereException
	 */
	private void searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(
			List searchResultsList) throws SevereException {
		//        List benefitTermsSearchResultsList = null;
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractBusinessObjectBuilder","searchBenefitTermsQualifierDescForCorrespondingBenefitLevels()"));
		
		LocateResults searchResults = null;
		BenefitlevelAdapterManager adapterManager = new BenefitlevelAdapterManager();
		if (null != searchResultsList) {
			for (int i = 0; i < searchResultsList.size(); i++) {
				BenefitLine benefitLine = (BenefitLine) searchResultsList
						.get(i);
				BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
				benefitLevelLocateCriteria.setBenefitLevelId(benefitLine
						.getLevelSysId());
				//                benefitTermsSearchResultsList = new ArrayList();
				try {
					// get the benefitTermCodes for the benefit level
					String benefitTermCodes = benefitLine.getTermCode();
					if (null != benefitTermCodes) {
						StringTokenizer benefitTerms = new StringTokenizer(
								benefitTermCodes, ",");
						int noOfTokens = benefitTerms.countTokens();
						if (noOfTokens > 1) {
							StringBuffer strBuffer = new StringBuffer();
							for (int j = 0; j < noOfTokens; j++) {
								if (benefitTerms.hasMoreTokens()) {

									String benefitTerm = benefitTerms
											.nextToken();
									benefitLevelLocateCriteria
											.setBenefitTerm(benefitTerm);
									searchResults = adapterManager
											.locateBenefitTermsDesc(benefitLevelLocateCriteria);
									List benefitTermDesc = searchResults
											.getLocateResults();

									if (null != benefitTermDesc
											&& benefitTermDesc.size() > 0) {
										if (j != 0) {
											strBuffer.append(",\n");
										}
										BenefitTermBO benefitTermBO = (BenefitTermBO) benefitTermDesc
												.get(0);
										strBuffer.append(benefitTermBO
												.getBenefitTerm());
									}
								}
							}
							benefitLine.setTermDesc(strBuffer.toString());
						} else if (noOfTokens == 1) {

						}
					}

					String benefitQualCodes = benefitLine.getQualifierCode();
					if (null != benefitQualCodes
							&& !"0".equals(benefitQualCodes)) {
						StringTokenizer benefitQualifiers = new StringTokenizer(
								benefitQualCodes, ",");
						int noOfTokens = benefitQualifiers.countTokens();
						if (noOfTokens > 1) {
							StringBuffer strBufferQual = new StringBuffer();
							for (int k = 0; k < noOfTokens; k++) {
								if (benefitQualifiers.hasMoreTokens()) {
									String benefitQual = benefitQualifiers
											.nextToken();
									benefitLevelLocateCriteria
											.setBenefitQualifier(benefitQual);
									searchResults = adapterManager
											.locateBenefitQualifiersDesc(benefitLevelLocateCriteria);
									List benefitQualDesc = searchResults
											.getLocateResults();
									if (null != benefitQualDesc
											&& benefitQualDesc.size() > 0) {
										if (k != 0) {
											strBufferQual.append(",\n");
										}
										BenefitQualifierBO benefitQualifierBO = (BenefitQualifierBO) benefitQualDesc
												.get(0);
										strBufferQual.append(benefitQualifierBO
												.getBenefitQualifier());
									}
								}
							}
							benefitLine.setQualifierDesc(strBufferQual
									.toString());
						} else if (noOfTokens == 1) {

						}
					}
					

				} catch (Exception ex) {
					Logger.logError(ex);
					List logParameters = new ArrayList(2);
					String logMessage = "Failed while processing searchBenefitTermDesc";
					throw new ServiceException(logMessage, logParameters, ex);
				}

			}//for loop end
		}
		Logger.logInfo(th.endPerfLogging());
	}

	/**
	 * 
	 * @param searchResultsList
	 * @throws SevereException
	 */
	private void searchBenefitTermsQualifierDescForCorrespondingBenefitLevelsForTier(
			List searchResultsList) throws SevereException {
		//        List benefitTermsSearchResultsList = null;
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractBusinessObjectBuilder","searchBenefitTermsQualifierDescForCorrespondingBenefitLevelsForTier()"));
		
		LocateResults searchResults = null;
		BenefitlevelAdapterManager adapterManager = new BenefitlevelAdapterManager();
		if (null != searchResultsList) {
			for (int i = 0; i < searchResultsList.size(); i++) {
				TierDefinitionBO benefitLine = (TierDefinitionBO) searchResultsList
						.get(i);
				BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
				benefitLevelLocateCriteria.setBenefitLevelId(benefitLine
						.getLevelSysId());
				//                benefitTermsSearchResultsList = new ArrayList();
				try {
					// get the benefitTermCodes for the benefit level
					String benefitTermCodes = benefitLine.getBnftLvlTerm();
					if (null != benefitTermCodes) {
						StringTokenizer benefitTerms = new StringTokenizer(
								benefitTermCodes, ",");
						int noOfTokens = benefitTerms.countTokens();
						if (noOfTokens > 1) {
							StringBuffer strBuffer = new StringBuffer();
							for (int j = 0; j < noOfTokens; j++) {
								if (benefitTerms.hasMoreTokens()) {

									String benefitTerm = benefitTerms
											.nextToken();
									benefitLevelLocateCriteria
											.setBenefitTerm(benefitTerm);
									searchResults = adapterManager
											.locateBenefitTermsDesc(benefitLevelLocateCriteria);
									List benefitTermDesc = searchResults
											.getLocateResults();

									if (null != benefitTermDesc
											&& benefitTermDesc.size() > 0) {
										if (j != 0) {
											strBuffer.append(",\n");
										}
										BenefitTermBO benefitTermBO = (BenefitTermBO) benefitTermDesc
												.get(0);
										strBuffer.append(benefitTermBO
												.getBenefitTerm());
									}
								}
							}
							benefitLine.setLvlTermDesc(strBuffer.toString());
						} else if (noOfTokens == 1) {

						}
					}

					String benefitQualCodes = benefitLine.getBnftLvlQual();
					if (null != benefitQualCodes
							&& !"0".equals(benefitQualCodes)) {
						StringTokenizer benefitQualifiers = new StringTokenizer(
								benefitQualCodes, ",");
						int noOfTokens = benefitQualifiers.countTokens();
						if (noOfTokens > 1) {
							StringBuffer strBufferQual = new StringBuffer();
							for (int k = 0; k < noOfTokens; k++) {
								if (benefitQualifiers.hasMoreTokens()) {
									String benefitQual = benefitQualifiers
											.nextToken();
									benefitLevelLocateCriteria
											.setBenefitQualifier(benefitQual);
									searchResults = adapterManager
											.locateBenefitQualifiersDesc(benefitLevelLocateCriteria);
									List benefitQualDesc = searchResults
											.getLocateResults();
									if (null != benefitQualDesc
											&& benefitQualDesc.size() > 0) {
										if (k != 0) {
											strBufferQual.append(",\n");
										}
										BenefitQualifierBO benefitQualifierBO = (BenefitQualifierBO) benefitQualDesc
												.get(0);
										strBufferQual.append(benefitQualifierBO
												.getBenefitQualifier());
									}
								}
							}
							benefitLine.setLevelQualDesc(strBufferQual
									.toString());
						} else if (noOfTokens == 1) {

						}
					}

				} catch (Exception ex) {
					Logger.logError(ex);
					List logParameters = new ArrayList(2);
					String logMessage = "Failed while processing searchBenefitTermDesc";
					throw new ServiceException(logMessage, logParameters, ex);
				}

			}//for loop end
		}
		Logger.logInfo(th.endPerfLogging());
	}

	/**
	 * Method to throw the exception.
	 * 
	 * @param obj,adapterException
	 * @throws ServiceException
	 */
	private void logAdapterException(Object obj,
			AdapterException adapterException) throws ServiceException {
		List errorParams = new ArrayList(2);
		errorParams.add(obj);
		throw new ServiceException("Adapter Exception occured", errorParams,
				adapterException);

	}

	/**
	 * Function to persist a Pricing Information. Flag is used to differentiate
	 * whether an insertion or updation is done.
	 * 
	 * @param BusinessObject
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException,
	 *             AdapterException
	 */
	public boolean persist(ContractPricingInfo businessObject,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering persist(): Pricing Information");
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ContractPricingInfo businessObject,Contract contract, Audit audit, boolean insertFlag)");
			businessObject.setLastUpdatedUser(audit.getUser());
			businessObject.setLastUpdatedTimestamp(audit.getTime());
			businessObject.setCreatedUser(audit.getUser());
			businessObject.setCreatedTimestamp(audit.getTime());
			ContractPricingInfo contractPricingInfo = (ContractPricingInfo) businessObject;
			if (insertFlag) {
				this.adapterManager.createPricingInfo(contractPricingInfo,
						audit, serviceAccess);
				createSystemComment(contractPricingInfo
						.getContractDateSegmentSysId(), "", (Timestamp) audit
						.getTime(), audit.getUser(), serviceAccess);
				this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ContractPricingInfo businessObject,Contract contract, Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractPricingInfo businessObject,Contract contract, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in persist ContractPricingInfo in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractPricingInfo businessObject,Contract contract, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning persist(): Pricing Information");
		return true;
	}

	/**
	 * 
	 * @param contractPricingInfo
	 * @param contract
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean delete(ContractPricingInfo contractPricingInfo,
			DateSegment dateSegment, Audit audit) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering delete(): Pricing Information");
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ContractPricingInfo contractPricingInfo,Contract contract, Audit audit)");
			adapterManager.deletePricingInfoObject(contractPricingInfo, audit,
					serviceAccess);
			createSystemComment(contractPricingInfo
					.getContractDateSegmentSysId(), "", (Timestamp) audit
					.getTime(), audit.getUser(), serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ContractPricingInfo contractPricingInfo,Contract contract, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ContractPricingInfo contractPricingInfo,Contract contract, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in delete ContractPricingInfo in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ContractPricingInfo contractPricingInfo,Contract contract, Audit audit)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning delete(): Pricing Information");
		return true;
	}

	/**
	 * 
	 * @param benefitComponentBO
	 * @param contract
	 * @param audit
	 * @return
	 * @throws SevereException ,
	 *             WPDException
	 */
	public Contract retrieveBenefitComponentGeneralInfo(
			BenefitComponentBO benefitComponentBO, Contract contract,
			Audit audit) throws SevereException {

		productbuilder = new ProductBusinessObjectBuilder();
		int productId = contract.getProductId();
		int benefitComponentId = benefitComponentBO.getBenefitComponentId();
		List benefitComponentList = new ArrayList(3);
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		benefitComponentBO = (BenefitComponentBO) benefitComponentBusinessObjectBuilder
				.retrieve(benefitComponentBO);
		productbuilder.getRule(benefitComponentId, productId,
				benefitComponentBO);
		benefitComponentList.add(benefitComponentBO);
		Contract contractBO = new Contract();
		contractBO.setComponentList(benefitComponentList);
		return contractBO;

	}

	/**
	 * 
	 * @param standardBenefitBO
	 * @return
	 * @throws SevereException
	 */
	public StandardBenefitBO retrieve(StandardBenefitBO standardBenefitBO)
			throws SevereException {
		StandardBenefitBusinessObjectBuilder standardBenefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
		standardBenefitBO = (StandardBenefitBO) standardBenefitBusinessObjectBuilder
				.retrieve(standardBenefitBO);
		return standardBenefitBO;
	}

	/**
	 * 
	 * @param benefitMasterSystemId
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults getNonAdjBenefitMandate(int benefitMasterSystemId,
			User user) throws SevereException {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering getNonAdjBenefitMandate(int benefitMasterSystemId="
						+ benefitMasterSystemId + ":User user=" + user);

		BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
		benefitMandateLocateCriteria
				.setBenefitMasterSystemId(benefitMasterSystemId);
		StandardBenefitBusinessObjectBuilder standardBenefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
		LocateResults locateResults = (LocateResults) standardBenefitBusinessObjectBuilder
				.locate(benefitMandateLocateCriteria, user);
		return locateResults;
	}

	/**
	 * 
	 * @param comment
	 * @return
	 * @throws SevereException
	 */
	public void createComment(Comment comment) throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"createComment(Comment comment)");
			SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
			int sysId = sequenceAdapterManager.getNextCommentIdSequence();
			comment.setCommentSysId(sysId);
			adapterManager.createComment(comment, serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"createComment(Comment comment)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"createComment(Comment comment)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in createComment in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"createComment(Comment comment)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

	}

	/**
	 * 
	 * @param comment
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(Comment comment, DateSegment dateSegment,
			Audit audit, boolean insertFlag) throws SevereException {
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		if (insertFlag) {

			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(transactionId, this,
								"persist(Comment comment, Contract contract, Audit audit,boolean insertFlag)");
				int sysId = sequenceAdapterManager.getNextCommentIdSequence();
				comment.setCommentSysId(sysId);
				comment.setCreatedTimeStamp((Timestamp) (audit.getTime()));
				comment.setCreatedUser(audit.getUser());
				comment.setLastUpdatedTimeStamp((Timestamp) audit.getTime());
				comment.setLastUpdatedUser(audit.getUser());
				adapterManager.createComment(comment, serviceAccess);
				this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"persist(Comment comment, Contract contract, Audit audit,boolean insertFlag)");
			} catch (SevereException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"persist(Comment comment, Contract contract, Audit audit,boolean insertFlag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist Comment in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"persist(Comment comment, Contract contract, Audit audit,boolean insertFlag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}

		}
		return true;
	}

	/**
	 * 
	 * @param dateSegment
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(DateSegment dateSegment, Audit audit,
			boolean insertFlag) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering persist(): Contract");
		AdapterServicesAccess specificInfoAdapterServiceAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			
			AdapterUtil.beginTransaction(specificInfoAdapterServiceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(DateSegment subObject, Contract contract,Audit audit, boolean insertFlag)");
			SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
			dateSegment.setLastUpdatedUser(audit.getUser());
			dateSegment.setCreatedUser(audit.getUser());
			dateSegment.setLastUpdatedTimestamp(audit.getTime());

			if (insertFlag) {

				int dateSegmentSysId = sequenceAdapterManager
						.getNextDtSegmentSysIdSequence();
				dateSegment.setDateSegmentSysId(dateSegmentSysId);
				persistContractAssociationInfo(dateSegment, audit,
						specificInfoAdapterServiceAccess);
				dateSegment.setCreatedTimestamp(audit.getTime());
				ContractStatusBo contractStatusBo = dateSegment.getContractStatusBo();
				adapterManager.createDateSegment(dateSegment,
						specificInfoAdapterServiceAccess);
				adapterManager.createContractStatus(contractStatusBo, specificInfoAdapterServiceAccess);

			} else {

				adapterManager.updateSpecificInfo(dateSegment,
						specificInfoAdapterServiceAccess);
				createSystemComment(dateSegment.getDateSegmentSysId(), "",
						(Timestamp) audit.getTime(), audit.getUser(),
						specificInfoAdapterServiceAccess);
				this.updateContractAuditInfo(dateSegment, audit,
						specificInfoAdapterServiceAccess);

			}
			AdapterUtil.endTransaction(specificInfoAdapterServiceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(DateSegment subObject, Contract contract,Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(specificInfoAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(DateSegment subObject, Contract contract,Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in persist Comment in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(specificInfoAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(DateSegment subObject, Contract contract,Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning persist(): Contract");
		return true;
	}

	public void persistContractAssociationInfo(DateSegment dateSegment,
			Audit audit, AdapterServicesAccess access) throws SevereException {
		//		AdapterServicesAccess access = AdapterUtil
		//.getAdapterService();
		Date createdTimeStamp = null;
		DateSegmentAssociationBO associationBO = new DateSegmentAssociationBO();
		associationBO.setContractSysId(dateSegment.getContractSysId());
		associationBO.setEffectiveDate(WPDStringUtil.getStringDate(dateSegment
				.getEffectiveDate()));
		associationBO.setDateSegmentType(dateSegment.getDateSegmentType());
		associationBO = adapterManager.getAssociation(associationBO, access);
		if (null != associationBO) {
			createdTimeStamp = associationBO.getCreatedTimeStamp();
			adapterManager.deleteAssociation(associationBO, audit.getUser(),
					access);
		} else {
			associationBO = new DateSegmentAssociationBO();
			associationBO.setContractSysId(dateSegment.getContractSysId());
		}
		associationBO.setDateSegmentSysId(dateSegment.getDateSegmentSysId());
		if (null != createdTimeStamp)
			associationBO.setCreatedTimeStamp(createdTimeStamp); // for transfer
		// log
		else
			associationBO.setCreatedTimeStamp(audit.getTime());
		associationBO.setCreatedUser(audit.getUser());
		associationBO.setLastUpdatedTimeStamp(audit.getTime());
		associationBO.setLastUpdatedUser(audit.getUser());
		adapterManager.persistAssociation(associationBO, audit.getUser(),
				access);
		//adapterManager.getAssocaitionInfo()

	}

	/**
	 * 
	 * @param subObject
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persist(AdaptedInfoBO subObject, DateSegment dateSegment,
			Audit audit, boolean insertFlag) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering persist(): Contract");

		AdapterServicesAccess adaptedInfoAdapterServiceAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adaptedInfoAdapterServiceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(AdaptedInfoBO subObject, Contract contract,Audit audit, boolean insertFlag)");
			SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
			subObject.setLastUpdatedUser(audit.getUser());
			subObject.setLastUpdatedTimeStamp(audit.getTime());

			adapterManager.updateAdaptedInfo(subObject,
					adaptedInfoAdapterServiceAccess);
			createSystemComment(subObject.getDateSegmentSysId(), "",
					(Timestamp) audit.getTime(), audit.getUser(),
					adaptedInfoAdapterServiceAccess);
			this.updateContractAuditInfo(dateSegment, audit,
					adaptedInfoAdapterServiceAccess);
			AdapterUtil.endTransaction(adaptedInfoAdapterServiceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(AdaptedInfoBO subObject, Contract contract,Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adaptedInfoAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(AdaptedInfoBO subObject, Contract contract,Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in persist Comment in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adaptedInfoAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(AdaptedInfoBO subObject, Contract contract,Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning persist(): Contract");
		return true;
	}

	/**
	 * 
	 * @param noteIdList
	 * @param mainobject
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean persist(ArrayList noteIdList, DateSegment mainobject,
			Audit audit, boolean insertFlag) throws SevereException {
		// Create an object of adapter manager
		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		// insert logic - to insert the selected notes - iterate thorough them
		if (noteIdList.size() != 0) {
			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(ArrayList noteIdList, Contract mainobject,Audit audit, boolean insertFlag)");
				for (int i = 0; i < noteIdList.size(); i++) {
					if (insertFlag == true) {
						// Set values to bo in each iteration
						AttachedNotesBO attachedNotesBO = (AttachedNotesBO) noteIdList
								.get(i);
						// call the attach method in the adapter manager - for
						// benefitComponentNotesAttach
						notesAttachmentAdapterManager.attachNotesForEntity(
								attachedNotesBO, audit, insertFlag,
								serviceAccess);
						createSystemComment(attachedNotesBO.getEntityId(), "",
								(Timestamp) audit.getTime(), audit.getUser(),
								serviceAccess);
						// For override StdBenefitNotes
					} else if (insertFlag == false) {
						// Set values to bo in each iteration
						NotesAttachmentOverrideBO overridebo = (NotesAttachmentOverrideBO) noteIdList
								.get(i);
						// call method in the adapter manager
						notesAttachmentAdapterManager
								.attachNotesForOverrideEntity(overridebo,
										audit, true, serviceAccess);
						createSystemComment(overridebo.getDateSegmentId(), "",
								(Timestamp) audit.getTime(), audit.getUser(),
								serviceAccess);
					}
				}
				this.updateContractAuditInfo(mainobject, audit, serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(ArrayList noteIdList, Contract mainobject,Audit audit, boolean insertFlag)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList noteIdList, Contract mainobject,Audit audit, boolean insertFlag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist ArrayList in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (SevereException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList noteIdList, Contract mainobject,Audit audit, boolean insertFlag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist ArrayList in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ArrayList noteIdList, Contract mainobject,Audit audit, boolean insertFlag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}

		}

		return true;
	}

	/**
	 * This method is for creating new date segment.
	 * 
	 * @param sourceDateSegmentSysId
	 * @param destiantionDateSegmentSysId
	 * @param user
	 * @param copyLegacyNotes
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copyDateSegments(int contractSysId,
			int sourceDateSegmentSysId, int destiantionDateSegmentSysId,
			boolean copyLegacyNotes, User user, boolean createDs) throws SevereException {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);

		adapterManager.copyDateSegment(contractSysId, sourceDateSegmentSysId,
				destiantionDateSegmentSysId, copyLegacyNotes, user.getUserId(),
				audit, createDs);

		return true;

	}

	/**
	 * 
	 * @param locateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(ContractProductLocateCriteria locateCriteria,
			User user) throws SevereException {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering locate(ContractProductLocateCriteria locateCriteria="
						+ locateCriteria + ":User user=" + user);

		ProductAdapterManager adapterManager = new ProductAdapterManager();

		List products = null;
		//gets the valid products for the contract
		//passed zero as the last attribute(product parent sys id)
		//this method is commonly used by contract and CMW
		products = adapterManager.getProductsForContract(locateCriteria
				.getLineOfBusiness(), locateCriteria.getBusinessEntity(),
				locateCriteria.getBusinessGroup(), locateCriteria.getMarketBusinessUnit(), locateCriteria
						.getEffectiveDate(), locateCriteria.getExpiryDate(),
				locateCriteria.getProductType(), locateCriteria
						.getTestContractState(), locateCriteria.getState(), 0);

		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(products);
		return locateResults;

	}
	
	/**
	 * retrieves the ProductStructureBO by ProductStructureId
	 * 
	 * @param administrationLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			AllPossibleAnswerForAdminOptionLocateCriteria allPossibleAnswerForAdminOptionLocateCriteria,
			User user) throws SevereException {
		
		LocateResults locateResults = new LocateResults();
		List finalList = null;
		int adminOptSysId = allPossibleAnswerForAdminOptionLocateCriteria.getAdminOptSysId();
		
		try {
				//	Create an instance of the adapter manager
				QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

				// Create the Adapter Service Access
				AdapterServicesAccess adapterServicesAccess = AdapterUtil
						.getAdapterService();

					// Get the individual entitybenefitAdministration objects from
					// the list
					List possibleAnswerList = questionAdapterManager
							.getAllPossibleAnswer(adminOptSysId, adapterServicesAccess);
					locateResults.setLocateResults(possibleAnswerList);
					
		} catch (SevereException se) {
			List errorParams = new ArrayList();
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractBenefitAdministrationLocateCriteria method in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractBenefitAdministrationLocateCriteria method in ContractBusinessObjectBuilder",
					null, ex);
		}
		// return the locate results to the business service
		return locateResults;
	}
	
	/**
	 * retrieves the ProductStructureBO by ProductStructureId
	 * 
	 * @param administrationLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractBenefitAdministrationLocateCriteria administrationLocateCriteria,
			User user) throws SevereException {
		
		// Create an instance of the adapter manager
		BenefitAdapterManager adapterManager = new BenefitAdapterManager();
		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();

		LocateResults locateResults = new LocateResults();
		List finalList = null;
		int action = administrationLocateCriteria.getAction();
		try {
			switch (action) {

			case ContractBenefitAdministrationLocateCriteria.QUESTIONNARE_INITIAL:
				int entityId = administrationLocateCriteria.getEntityId();
				String entityType = administrationLocateCriteria
						.getEntiityType();
				int adminOptSysId = administrationLocateCriteria
						.getAdminOptSysId();
				int beneftAdminSysId = administrationLocateCriteria
						.getBenefitAdminSysId();
				int beneftComponentId = administrationLocateCriteria
						.getBenefitComponentId();
				int benefitId = administrationLocateCriteria.getBenefitId();
				HashMap allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				//	 Code change by minu : 5-1-2011 : eWPD System Stabilization
				int cntrctParentSysId = administrationLocateCriteria.getCntrctParentSysId();
				int adminLevelOptionSysId = administrationLocateCriteria.getAdminLevelOptionSysId();
				List locateResultList = adapterManager
						.getBenefitAdministrationValuesForContract(entityId,
								adminOptSysId, beneftAdminSysId,
								beneftComponentId, cntrctParentSysId, adminLevelOptionSysId);
				if (null != locateResultList && 0 < locateResultList.size()) {
					getPossibleAnswerList(locateResultList,allPossibleAnswerMap);
					//13 jan 2011 - possible answer retrieval
				}
				finalList = BusinessUtil
						.getQuestionareHierarchy(locateResultList);
				if (null != finalList && !finalList.isEmpty()) { //if
					// questionnaire
					// exist
					List tierandNontierList = new ArrayList();
					tierandNontierList.add(finalList);
					
					boolean isAdminOptionExcluded = contractAdapterManager.isAdminOptionExcluded(adminOptSysId); 
					if(!isAdminOptionExcluded){
					List tierCriteriaList = contractAdapterManager
							.getTierCriteriaForContract(entityId,
									beneftComponentId, benefitId);
					if (null != tierCriteriaList && !tierCriteriaList.isEmpty()) { // if
						// the
						// admin
						// option
						// is
						// tiered
						List benefitTierList = BenefitTierUtil
								.getTieredList(tierCriteriaList); // The tier
						// objects are
						// created
						// from the
						// values
						// returned by
						// the query
						List tieredQuestionnaireList = contractAdapterManager
								.getTieredQuestionnaireValuesForContract(
										adminOptSysId, beneftComponentId,
										beneftAdminSysId, entityId,
										getTierList(tierCriteriaList),adminLevelOptionSysId,cntrctParentSysId);
						// iterate benefitTierList and put the questionaires
						// corresponding to each tier in the tier object
						for (int i = 0; i < benefitTierList.size(); i++) {
							BenefitTierDefinition definitionBO = (BenefitTierDefinition) benefitTierList
									.get(i);
							List tierList = definitionBO.getBenefitTiers();
							for (int k = 0; k < tierList.size(); k++) {
								BenefitTier tierBo = (BenefitTier) tierList
										.get(k);
								List tierQuestionnaireList = new ArrayList();
								for (int q = 0; q < tieredQuestionnaireList
										.size(); q++) {
									ContractQuesitionnaireBO questionnaireBO = (ContractQuesitionnaireBO) tieredQuestionnaireList
											.get(q);
									if (tierBo.getBenefitTierSysId() == questionnaireBO
											.getTierSysId()) {
										tierQuestionnaireList
												.add(questionnaireBO);
										
									}
								}
								if (null != tierQuestionnaireList
										&& !tierQuestionnaireList.isEmpty())
									getPossibleAnswerList(tierQuestionnaireList, allPossibleAnswerMap);
								finalList = BusinessUtil
										.getQuestionareHierarchy(tierQuestionnaireList);
								tierBo.setQuestionnaireList(finalList);
							}
						}
						tierandNontierList.add(benefitTierList);
					}
					}
					
					locateResults.setLocateResults(tierandNontierList);
				} else {
					locateResults.setLocateResults(finalList);
				}
				break;

			case ContractBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD:
				int answerId = administrationLocateCriteria.getAnswerId();
				String answerDesc = administrationLocateCriteria.getAnswerDesc();
				ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO) administrationLocateCriteria
						.getContractQuesitionnaireBO();
				int questionnaireId = contractQuesitionnaireBO
						.getQuestionnaireId();
				int contractSysId = administrationLocateCriteria.getEntityId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				List oldQuestionnareList = administrationLocateCriteria
						.getQuestionnareList();
				List oldListForUnsavedQuestion = new ArrayList(
						administrationLocateCriteria.getQuestionnareList());
				int reaArrangedQuestIndex = administrationLocateCriteria
						.getQuestionareListIndex();
				((ContractQuesitionnaireBO) oldQuestionnareList
						.get(reaArrangedQuestIndex))
						.setSelectedAnswerid(answerId);
				((ContractQuesitionnaireBO) oldQuestionnareList
						.get(reaArrangedQuestIndex))
						.setSelectedAnswerDesc(answerDesc);
				int bendfnID = 0;
				if (null != administrationLocateCriteria
						.getContractQuesitionnaireBO().getBnftDefId()
						&& !("").equals(administrationLocateCriteria
								.getContractQuesitionnaireBO().getBnftDefId())) {
					bendfnID = Integer.parseInt(administrationLocateCriteria
							.getContractQuesitionnaireBO().getBnftDefId());
				}
				//	  Code change by minu : 5-1-2011 : eWPD System Stabilization
				beneftAdminSysId = administrationLocateCriteria
				.getBenefitAdminSysId();
				Logger.logInfo("beneftAdminSysId in builder.."+
						beneftAdminSysId); 
				
				
				List childList = adapterManager
						.getChildQuestionnaireValuesForContract(answerId,
								questionnaireId, contractSysId,
								contractQuesitionnaireBO.getDateSegmentId(),
								contractQuesitionnaireBO
										.getAdminLevelOptionSysId(),
								administrationLocateCriteria
										.getBenefitComponentId(), beneftAdminSysId);
				if (null != childList) {
					getPossibleAnswerList(childList, allPossibleAnswerMap);
				}
				finalList = BusinessUtil.getRearrangedQuestionnareList(
						childList, oldQuestionnareList, reaArrangedQuestIndex);
				List notesToDelete = getBenefitQuestionareNoteListToDelete(
						finalList, oldListForUnsavedQuestion);
				deleteUnsavedBenefitQuestionNote(notesToDelete,
						contractQuesitionnaireBO.getDateSegmentId(),
						administrationLocateCriteria.getBenefitComponentId(),
						contractQuesitionnaireBO.getAdminLevelOptionSysId(),
						bendfnID, user);
				locateResults.setLocateResults(finalList);
				break;
			case ContractBenefitAdministrationLocateCriteria.QUESTIONNARE_VIEW_PRINT:
				int viewEntityId = administrationLocateCriteria.getEntityId();
				//		       // String entityType =
				// administrationLocateCriteria.getEntiityType();
				int viewAdminOptSysId = administrationLocateCriteria
						.getAdminOptSysId();
				int viewBeneftAdminSysId = administrationLocateCriteria
						.getBenefitAdminSysId();
				int viewBeneftComponentId = administrationLocateCriteria
						.getBenefitComponentId();
				int viewBenefitId = administrationLocateCriteria.getBenefitId();
				//	 Code change by minu : 5-1-2011 : eWPD System Stabilization
				cntrctParentSysId = administrationLocateCriteria.getCntrctParentSysId();
				adminLevelOptionSysId = administrationLocateCriteria.getAdminLevelOptionSysId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				
				List viewLocateResultList = adapterManager
						.getBenefitAdministrationValuesForContract(
								viewEntityId, viewAdminOptSysId,
								viewBeneftAdminSysId, viewBeneftComponentId,
								cntrctParentSysId, adminLevelOptionSysId);

				finalList = BusinessUtil
						.getQuestionareHierarchy(viewLocateResultList);
				if (null != finalList && !finalList.isEmpty()) { //if
					// questionnaire
					// exist
					List tierandNontierList = new ArrayList();
					tierandNontierList.add(finalList);
					
					adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
					boolean isAdminOptionExcluded = contractAdapterManager.isAdminOptionExcluded(adminOptSysId); 
					if(!isAdminOptionExcluded){
					
					List tierCriteriaList = contractAdapterManager
							.getTierCriteriaForContract(viewEntityId,
									viewBeneftComponentId, viewBenefitId);
					if (null != tierCriteriaList && !tierCriteriaList.isEmpty()) { // if
						// the
						// admin
						// option
						// is
						// tiered
						List benefitTierList = BenefitTierUtil
								.getTieredList(tierCriteriaList); // The tier
						// objects are
						// created
						// from the
						// values
						// returned by
						// the query
						List tieredQuestionnaireList = contractAdapterManager
								.getTieredQuestionnaireValuesForContract(
										viewAdminOptSysId,
										viewBeneftComponentId,
										viewBeneftAdminSysId, viewEntityId,
										getTierList(tierCriteriaList),adminLevelOptionSysId,cntrctParentSysId);
						// iterate benefitTierList and put the questionaires
						// corresponding to each tier in the tier object
						for (int i = 0; i < benefitTierList.size(); i++) {
							BenefitTierDefinition definitionBO = (BenefitTierDefinition) benefitTierList
									.get(i);
							List tierList = definitionBO.getBenefitTiers();
							for (int k = 0; k < tierList.size(); k++) {
								BenefitTier tierBo = (BenefitTier) tierList
										.get(k);
								List tierQuestionnaireList = new ArrayList();
								for (int q = 0; q < tieredQuestionnaireList
										.size(); q++) {
									ContractQuesitionnaireBO questionnaireBO = (ContractQuesitionnaireBO) tieredQuestionnaireList
											.get(q);
									if (tierBo.getBenefitTierSysId() == questionnaireBO
											.getTierSysId()) {
										tierQuestionnaireList
												.add(questionnaireBO);
									}
								}
								finalList = BusinessUtil
										.getQuestionareHierarchy(tierQuestionnaireList);
								tierBo.setQuestionnaireList(finalList);
							}
						}
						tierandNontierList.add(benefitTierList);
					}
				}
					locateResults.setLocateResults(tierandNontierList);
				} else {
					locateResults.setLocateResults(finalList);
				}
				break;
			case ContractBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD_TIER:
				int answerIdTier = administrationLocateCriteria.getAnswerId();
			    String answerDescTier = administrationLocateCriteria.getAnswerDesc();
			    ContractQuesitionnaireBO contractQuesitionnaireBOTier = (ContractQuesitionnaireBO) administrationLocateCriteria
						.getContractQuesitionnaireBO();
				int questionnaireIdTier = contractQuesitionnaireBOTier
						.getQuestionnaireId();
				int contractSysIdTier = administrationLocateCriteria
						.getEntityId();
				List oldQuestionnareListTier = administrationLocateCriteria
						.getQuestionnareList();
				List oldListForUnsavedQuestionTier = new ArrayList(
						administrationLocateCriteria.getQuestionnareList());
				int reaArrangedQuestIndexTier = administrationLocateCriteria
						.getQuestionareListIndex();
				((ContractQuesitionnaireBO) oldQuestionnareListTier
						.get(reaArrangedQuestIndexTier))
						.setSelectedAnswerid(answerIdTier);
				 
				((ContractQuesitionnaireBO) oldQuestionnareListTier
						.get(reaArrangedQuestIndexTier))
						.setSelectedAnswerDesc(answerDescTier);
				int bendfnIDTier = 0;
				if (null != administrationLocateCriteria
						.getContractQuesitionnaireBO().getBnftDefId()
						&& !("").equals(administrationLocateCriteria
								.getContractQuesitionnaireBO().getBnftDefId())) {
					bendfnIDTier = Integer
							.parseInt(administrationLocateCriteria
									.getContractQuesitionnaireBO()
									.getBnftDefId());
				}
				beneftAdminSysId = administrationLocateCriteria
				.getBenefitAdminSysId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				List childListTier = contractAdapterManager
						.getChildTierQuestionnaireValuesForContract(
								answerIdTier, questionnaireIdTier,
								contractSysIdTier, contractQuesitionnaireBOTier
										.getDateSegmentId(),
								contractQuesitionnaireBOTier
										.getAdminLevelOptionSysId(),
								administrationLocateCriteria
										.getBenefitComponentId(), beneftAdminSysId,
								contractQuesitionnaireBOTier.getTierSysId());
				if (null != childListTier) {
					getPossibleAnswerList(childListTier, allPossibleAnswerMap);
				}
				finalList = BusinessUtil.getRearrangedQuestionnareList(
						childListTier, oldQuestionnareListTier,
						reaArrangedQuestIndexTier);
				List notesToDeleteTier = getBenefitQuestionareNoteListToDelete(
						finalList, oldListForUnsavedQuestionTier);

				deleteUnsavedBenefitTierQuestionNote(
						notesToDeleteTier,
						contractQuesitionnaireBOTier.getDateSegmentId(),
						administrationLocateCriteria.getBenefitComponentId(),
						contractQuesitionnaireBOTier.getAdminLevelOptionSysId(),
						bendfnIDTier, user, contractQuesitionnaireBOTier
								.getTierSysId());
				locateResults.setLocateResults(finalList);
				break;
			}

		} catch (SevereException se) {
			List errorParams = new ArrayList();
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractBenefitAdministrationLocateCriteria method in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractBenefitAdministrationLocateCriteria method in ContractBusinessObjectBuilder",
					null, ex);
		}
		// return the locate results to the business service
		return locateResults;
	}

	/**
	 * This method for formatting the definition lite to get a list of tier
	 * system id.
	 * 
	 * @param ComponentsBenefitAdministrationLocateCriteria.
	 * @param User.
	 * @return locateResults. This list contain list of questionnare objects.
	 * @throws SevereException
	 */
	private List getTierList(List criteriaListFrmDB) {

		List tierSysIdList = new ArrayList();

		TierDefinitionBO oldTierDef = (TierDefinitionBO) criteriaListFrmDB
				.get(0);
		int oldCntrctTierId = oldTierDef.getTierSysId();

		tierSysIdList.add(new Integer(oldCntrctTierId));

		for (int i = 1; i < criteriaListFrmDB.size(); i++) {

			TierDefinitionBO newTierDef = (TierDefinitionBO) criteriaListFrmDB
					.get(i);
			int newCntrctTierId = newTierDef.getTierSysId();

			if (oldCntrctTierId != newCntrctTierId) {

				tierSysIdList.add(new Integer(newCntrctTierId));
			}
			oldCntrctTierId = newCntrctTierId;
		}

		return tierSysIdList;
	}

	/*
	 * this method filter the questionareLiat and flag the question notes to be
	 * deleted
	 * 
	 *  
	 */
	public static List getBenefitQuestionareNoteListToDelete(
			List newQuestionnareList, List oldListForUnsavedQuestion) {

		List listToDelete = new ArrayList();

		for (int i = 0; i < oldListForUnsavedQuestion.size(); i++) {

			ContractQuesitionnaireBO oldQuestionnareBo = (ContractQuesitionnaireBO) oldListForUnsavedQuestion
					.get(i);

			for (int j = 0; j < newQuestionnareList.size(); j++) {

				ContractQuesitionnaireBO newQuestionnareBo = (ContractQuesitionnaireBO) newQuestionnareList
						.get(j);
				if (newQuestionnareBo.compareTo(oldQuestionnareBo)) {
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			//listToDelete.add(oldQuestionnareBo);
		}

		return newQuestionnareList;

	}

	private void deleteUnsavedBenefitTierQuestionNote(List noteDetailList,
			int dateSegemntId, int beneftComponentId, int adminLevelOptionId,
			int benefitDefId, User user, int tierSysId) throws SevereException {
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		for (int i = 0; i < noteDetailList.size(); i++) {
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ContractQuesitionnaireBO questionnareBo = (ContractQuesitionnaireBO) noteDetailList
					.get(i);
			if (questionnareBo.isUnsavedData()) {
				questionnareBo.setNoteExist("N");
				int questionId = questionnareBo.getQuestionId();
				TierNotesAttachmentOverrideBO attachmentBo = new TierNotesAttachmentOverrideBO();
				attachmentBo.setPrimaryEntityId(dateSegemntId);
				attachmentBo.setSecondaryEntityId(adminLevelOptionId);
				attachmentBo.setQuestionNumber(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHCONTRACT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNote_status("F");
				attachmentBo.setTierSysId(tierSysId);
				attachmentBo.setBnftDefnIdString(new Integer(benefitDefId)
						.toString());
				attachmentBo.setBenefitComponentId(beneftComponentId);

				TierNotesAttachmentOverrideBO newattachmentBo = getNoteInfo(attachmentBo);
				if (null != newattachmentBo
						&& null != newattachmentBo.getNoteId()
						&& !("").equals(newattachmentBo.getNoteId())) {
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setVersion(newattachmentBo.getVersion());
					try {
						adapterManager.deleteNotesAttachedToQuestion(
								attachmentBo, audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

	private TierNotesAttachmentOverrideBO getNoteInfo(
			TierNotesAttachmentOverrideBO attachmentBo) {
		TierNotesAttachmentOverrideBO newAttachmentBo = null;
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if (null != resultList && resultList.size() > 0) {
				newAttachmentBo = (TierNotesAttachmentOverrideBO) resultList
						.get(0);
			}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newAttachmentBo;
	}

	/*
	 * this method perfotm delete the unsaved question note deatils while the
	 * questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 *  
	 */
	private void deleteUnsavedBenefitQuestionNote(List noteDetailList,
			int dateSegemntId, int beneftComponentId, int adminLevelOptionId,
			int benefitDefId, User user) throws SevereException {
		
	    
		NotesAdapterManager adapterManager = new NotesAdapterManager();

		for (int i = 0; i < noteDetailList.size(); i++) {
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ContractQuesitionnaireBO questionnareBo = (ContractQuesitionnaireBO) noteDetailList
					.get(i);
			if (questionnareBo.isUnsavedData()) {
				questionnareBo.setNoteExist("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBO attachmentBo = new NotesToQuestionAttachmentBO();
				attachmentBo.setPrimaryId(dateSegemntId);
				attachmentBo.setSecondaryId(adminLevelOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHCONTRACT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNoteOverrideStatus("F");
				attachmentBo.setBnftDefId(new Integer(benefitDefId).toString());
				attachmentBo.setBenefitCompId(beneftComponentId);

				NotesToQuestionAttachmentBO newattachmentBo = getNoteInfo(attachmentBo);
				if (null != newattachmentBo
						&& null != newattachmentBo.getNoteId()
						&& !("").equals(newattachmentBo.getNoteId())) {
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setNoteVersionNumber(newattachmentBo
							.getNoteVersionNumber());
					try {
						adapterManager.deleteNotesAttachedToQuestion(
								attachmentBo, audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private NotesToQuestionAttachmentBO getNoteInfo(
			NotesToQuestionAttachmentBO attachmentBo) {
		NotesToQuestionAttachmentBO newAttachmentBo = null;
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if (null != resultList && resultList.size() > 0) {
				newAttachmentBo = (NotesToQuestionAttachmentBO) resultList
						.get(0);
			}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newAttachmentBo;
	}

	/*
	 * this method is for getting possible answer list. @param locateResultList
	 * this list contains questions needed for possible answer. @ returns answer
	 * list
	 */
	public void getPossibleAnswerList(List locateResultList)
			throws SevereException {
		
		if(locateResultList != null && !locateResultList.isEmpty()){    
			QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

			// Create the Adapter Service Access
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();

			// Iterate through the list to get the possible answers for the
			// question
			for (int i = 0; i < locateResultList.size(); i++) {
				// Get the individual entitybenefitAdministration objects from
				// the list
				ContractQuesitionnaireBO contarctQuesitionnaireBO = (ContractQuesitionnaireBO) locateResultList
						.get(i);
				int questionNumber = contarctQuesitionnaireBO.getQuestionId();

				QuestionBO questionBO = new QuestionBO();

				questionBO.setQuestionNumber(questionNumber);

				// Call the getPossibleAnswers() of the question adaptermanager
				List possibleAnswerList = questionAdapterManager
						.getPossibleAnswer(questionBO, adapterServicesAccess);
				List arrangedPossibleAnswerList = BusinessUtil
						.getRearrangedPossibleAnswerList(possibleAnswerList);
				// Set the possible answers list to the contarctQuesitionnaireBO
				contarctQuesitionnaireBO
						.setPossibleAnswerList(arrangedPossibleAnswerList);
			}
		}
		
	}
	
	/*
	 * this method is for getting possible answer list. @param locateResultList
	 * this list contains questions needed for possible answer. @ returns answer
	 * list
	 */
	public void getPossibleAnswerList(List locateResultList, HashMap allPossibleAnswerMap)
			throws SevereException {
		
		
		if(locateResultList != null && !locateResultList.isEmpty()){    
			QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

			// Create the Adapter Service Access
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();

			// Iterate through the list to get the possible answers for the
			// question
			for (int i = 0; i < locateResultList.size(); i++) {
				// Get the individual entitybenefitAdministration objects from
				// the list
				ContractQuesitionnaireBO contarctQuesitionnaireBO = (ContractQuesitionnaireBO) locateResultList
						.get(i);
				int questionNumber = contarctQuesitionnaireBO.getQuestionId();

				QuestionBO questionBO = new QuestionBO();

				questionBO.setQuestionNumber(questionNumber);
				List possibleAnswerList;
				if(null != allPossibleAnswerMap && allPossibleAnswerMap.containsKey(new Integer(questionNumber))){
					possibleAnswerList = (ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)); 
					Collections.sort(possibleAnswerList);
					
				}else{
					// Call the getPossibleAnswers() of the question adaptermanager
					possibleAnswerList = questionAdapterManager
							.getPossibleAnswer(questionBO, adapterServicesAccess);
				}
				List arrangedPossibleAnswerList = BusinessUtil
						.getRearrangedPossibleAnswerList(possibleAnswerList);
				// Set the possible answers list to the contarctQuesitionnaireBO
				contarctQuesitionnaireBO
						.setPossibleAnswerList(arrangedPossibleAnswerList);
				
			}
		}
		
	}

	/**
	 * 
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
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {
		
		// Create an instance of the adapter manager
		// Code change by minu : 28-12-2010 : eWPD System Stabilization  
		AdminOptionAdapterManager adapterManager = new AdminOptionAdapterManager();
		
		// Get the BOList from the BO
		List benefitAdministrationList = (List) subObject.getQuestionnareList();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil.logBeginTranstn(transactionId, this,
							"persist(EntityBenefitAdministrationPSBO subObject," +
							"Contract mainObject,Audit audit,boolean insertFlag)");
			if (benefitAdministrationList != null) {

				adapterManager.saveQuestionnareForContract(subObject, audit, 
						adapterServicesAccess);
				this.updateContractAuditInfo(dateSegment, audit,
						adapterServicesAccess);
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId,this,
							"persist(EntityBenefitAdministrationPSBO subObject," +
							"Contract mainObject,Audit audit,boolean insertFlag)");
		} catch (SevereException se) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId,this,
							"persist(EntityBenefitAdministrationPSBO subObject," +
							"Contract mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist EntityBenefitAdministrationPSBO" +
					" method in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId,this,
							"persist(EntityBenefitAdministrationPSBO subObject," +
							"Contract mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		return true;
	}

	/**
	 * 
	 * @param benefitKey
	 * @return assignedRuleIdBO
	 * @throws SevereException
	 */
	public AssignedRuleIdBO getDefaultRule(int benefitKey)
			throws SevereException {
		AssignedRuleIdBO assignedRuleIdBO = new AssignedRuleIdBO();
		assignedRuleIdBO.setEntitySystemId(benefitKey);
		assignedRuleIdBO.setEntityType("BENEFIT");
		RefdataAdapterManager refdataAdapterManager = new RefdataAdapterManager();
		List searchRuleId = refdataAdapterManager
				.searchRuleId(assignedRuleIdBO);
		if (null != searchRuleId && !searchRuleId.isEmpty()) {
			assignedRuleIdBO = (AssignedRuleIdBO) searchRuleId.get(0);
		}
		return assignedRuleIdBO;
	}

	/**
	 * 
	 * @param benefitKey
	 * @return standardBenefitBO
	 * @throws SevereException
	 */
	public StandardBenefitBO getBenefitGeneralInfo(int benefitKey,
			int benefitComponentId, int dateSegmentId, int productId)
			throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering getBenefitGeneralInfo(int benefitKey="
						+ benefitKey);

		StandardBenefitAdapterManager standardAdapterManager = new StandardBenefitAdapterManager();
		StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
		standardBenefitBO.setStandardBenefitKey(benefitKey);
		standardBenefitBO = (StandardBenefitBO) standardAdapterManager
				.retrieveSB(standardBenefitBO);
		List domainList = DomainUtil
				.retrieveAssociatedDomains(WebConstants.STD_BENEFIT,
						standardBenefitBO.getParentSystemId());
		standardBenefitBO.setBusinessDomains(domainList);
		UniverseBO universeBO = new UniverseBO();
		universeBO.setStandardBenefitKey(benefitKey);
		List searchList = standardAdapterManager.searchUniverse(universeBO);
		if (null != searchList && !searchList.isEmpty()) {
			standardBenefitBO = this.prepareUniverseDisplayList(searchList,
					standardBenefitBO);
		}
		StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
		standardBenefitDatatypeBO.setStandardBenefitKey(benefitKey);
		List dataTypeResultList = standardAdapterManager
				.searchDatatype(standardBenefitDatatypeBO);
		standardBenefitBO.setDataTypeList(dataTypeResultList);
		if (null != standardBenefitBO) {
			RuleIdBO ruleIdBO = adapterManager
					.getRuleId(standardBenefitBO.getStandardBenefitKey(),
							benefitComponentId, dateSegmentId);
			if (null != ruleIdBO) {
				if (null != ruleIdBO.getBenefitMeaning()) {
					standardBenefitBO.setBenefitMeaning(ruleIdBO
							.getBenefitMeaning());
				}
				if (null != ruleIdBO.getPrimaryCode()) {
					standardBenefitBO.setRuleId(ruleIdBO.getRuleDesc() + '~'
							+ ruleIdBO.getPrimaryCode());
				}
				// Did the code change for Adjudication Rules i.e RuleType is
				// added
				if (null != ruleIdBO.getStrRuleType()) {
					standardBenefitBO.setStrRuleType(ruleIdBO.getStrRuleType());
				}
			}
		}
		ProductBenefitDefinitionLocateCriteria definitionLocateCriteria = new ProductBenefitDefinitionLocateCriteria();
		definitionLocateCriteria.setProductId(productId);
		definitionLocateCriteria.setBenefitComponentId(benefitComponentId);
		definitionLocateCriteria.setBenefitId(benefitKey);
		List defnOverrideBOList = adapterManager
				.getTierDefinisionsForContract(definitionLocateCriteria);
		if (null != defnOverrideBOList && (defnOverrideBOList.size() != 0)) {
			List tierDefinitionList = new ArrayList();
			for (Iterator tierDefinitionIter = defnOverrideBOList.iterator(); tierDefinitionIter
					.hasNext();) {
				ProductTierDefnOverrideBO tierDefnOverrideBO = (ProductTierDefnOverrideBO) tierDefinitionIter
						.next();
				tierDefinitionList.add(tierDefnOverrideBO.getTierDescription());
			}
			standardBenefitBO.setTierDefinitionList(tierDefinitionList);
		}

		return standardBenefitBO;
	}

	/**
	 * Method prepareUniverseDisplayList
	 * 
	 * @param searchList
	 * @param standardBenefitBO
	 * @return standardBenefitBO
	 *  
	 */
	private StandardBenefitBO prepareUniverseDisplayList(List searchList,
			StandardBenefitBO standardBenefitBO) {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering method prepareUniverseDisplayList()");

		if (null != searchList) {
			Iterator searchListIterator = searchList.iterator();
			List termList = new ArrayList(searchList.size());
			List qualifierList = new ArrayList(searchList.size());
			List pvaList = new ArrayList(searchList.size());

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
		}
		return standardBenefitBO;
	}

	/**
	 * To locate the overided notes of benefit component for the Product.
	 * 
	 * @param ContractComponentNotesLocateCriteria
	 * @return LocateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractComponentNotesLocateCriteria contractComponentNotesLocateCriteria,
			User user) throws SevereException {

		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
		try {
			int primaryEntityId = contractComponentNotesLocateCriteria
					.getPrimaryEntityId();
			String primaryEntityType = contractComponentNotesLocateCriteria
					.getPrimaryEntityType();
			int secondaryEntityId = contractComponentNotesLocateCriteria
					.getSecondaryEntityId();
			String secondaryEntityType = contractComponentNotesLocateCriteria
					.getSecondaryEntityType();
			int benefitComponentId = contractComponentNotesLocateCriteria
					.getBenefitComponentId();
			if (contractComponentNotesLocateCriteria.getAction() == 200) {
				if (secondaryEntityType
						.equals(WebConstants.ATTACH_BENEFIT_COMP)) {
					return notesAttachmentAdapterManager
							.locateAttachedBCNotesForOverrideForDatafeed(
									primaryEntityId, primaryEntityType,
									secondaryEntityType,
									contractComponentNotesLocateCriteria
											.getEntityIdsList());
				} else {
					if (secondaryEntityType.equals(WebConstants.ATTACH_BENEFIT)) {
						return notesAttachmentAdapterManager
								.locateAttachedBenefitNotesForOverrideForDatafeed(
										primaryEntityId, primaryEntityType,
										secondaryEntityType,
										contractComponentNotesLocateCriteria
												.getEntityIdsList(),
										benefitComponentId);
					} else {
						return notesAttachmentAdapterManager
								.locateAttachedNotesForOverrideForDatafeed(
										primaryEntityId, primaryEntityType,
										secondaryEntityType,
										contractComponentNotesLocateCriteria
												.getEntityIdsList(),
										benefitComponentId);
					}
				}
			} else {
				return notesAttachmentAdapterManager
						.locateAttachedNotesForOverride(primaryEntityId,
								primaryEntityType, secondaryEntityId,
								secondaryEntityType, benefitComponentId);
			}
		} catch (SevereException se) {
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractComponentNotesLocateCriteria method in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractComponentNotesLocateCriteria method in ContractBusinessObjectBuilder",
					null, ex);
		}

	}

	/**
	 * To attach Note for a Contract.
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	public boolean persist(ContractNotes subObject, DateSegment dateSegment,
			Audit audit, boolean insertFlag) throws SevereException {
		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();

		int action = subObject.getAction();
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering method persist(ProductComponents subObject="
						+ subObject
						+ ":Audit audit="
						+ audit
						+ ":boolean insertFlag=" + insertFlag);
		long transactionId = AdapterUtil.getTransactionId();

		if (insertFlag) {
			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
				if (action == 1) {
					List noteList = subObject.getNoteList();
					AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
					NoteDomainBO retrievedNoteDomainBO = null;
					if (noteList == null || noteList.size() == 0) {
						AdapterUtil.endTransaction(serviceAccess);
						AdapterUtil
								.logTheEndTransaction(
										transactionId,
										this,
										"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
						return true;
					}
					// Persisting new Note
					for (int i = 0; i < noteList.size(); i++) {

						attachedNotesBO = (AttachedNotesBO) noteList.get(i);
						notesAttachmentAdapterManager.attachNotesForEntity(
								attachedNotesBO, audit, true, serviceAccess);
					}

				} else {
					List noteList = subObject.getNoteList();
					NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
					notesAttachmentOverrideBO = (NotesAttachmentOverrideBO) noteList
							.get(0);
					createSystemComment(notesAttachmentOverrideBO
							.getPrimaryEntityId(), "", (Timestamp) audit
							.getTime(), audit.getUser(), serviceAccess);
					if (noteList == null || noteList.size() == 0) {
						AdapterUtil.endTransaction(serviceAccess);
						AdapterUtil
								.logTheEndTransaction(
										transactionId,
										this,
										"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
						return true;
					}
					// Persisting new Note
					for (int i = 0; i < noteList.size(); i++) {

						notesAttachmentOverrideBO = (NotesAttachmentOverrideBO) noteList
								.get(i);
						notesAttachmentAdapterManager
								.attachNotesForOverrideEntity(
										notesAttachmentOverrideBO, audit,
										false, serviceAccess);

					}
				}
				this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist ContractNotes in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(ContractNotes subObject, Contract mainObject,Audit audit, boolean insertFlag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}

		}
		return true;

	}

	/**
	 * Method to persist the overridden notes
	 * 
	 * @param overrideBO
	 * @param contract
	 * @param audit
	 * @param insertflag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(NotesAttachmentOverrideBO overrideBO,
			Contract contract, Audit audit, boolean insertflag)
			throws SevereException {

		// Create an instance of the adapter manager
		NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();

		List persistNotesList = (List) overrideBO.getNotesList();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		if (null != persistNotesList && !persistNotesList.isEmpty()) {
			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				for (int i = 0; i < persistNotesList.size(); i++) {
					NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO) persistNotesList
							.get(i);

					overrideNotesBO.setPrimaryEntityId(overrideBO
							.getPrimaryEntityId());
					overrideNotesBO.setPrimaryEntityType(overrideBO
							.getPrimaryEntityType());
					overrideNotesBO.setSecondaryEntityId(overrideBO
							.getSecondaryEntityId());
					overrideNotesBO.setSecondaryEntityType(overrideBO
							.getSecondaryEntityType());
					overrideNotesBO.setBenefitComponentId(overrideBO
							.getBenefitComponentId());
					if (0 != overrideBO.getTierSysId()) {
						overrideNotesBO.setTierSysId(overrideBO.getTierSysId());
					}
					adapterManager.attachNotesForOverrideEntity(
							overrideNotesBO, audit, false, serviceAccess);
				}
				createSystemComment(overrideBO.getDateSegmentId(), "",
						(Timestamp) audit.getTime(), audit.getUser(),
						serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist NotesAttachmentOverrideBO in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}

		}

		return true;
	}

	/**
	 * Method to persist the overridden notes
	 * 
	 * @param overrideBO
	 * @param contract
	 * @param audit
	 * @param insertflag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(TierNoteOverRide tierNoteOverRide,
			Contract contract, Audit audit, boolean insertflag)
			throws SevereException {

		// Create an instance of the adapter manager
		NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();

		List persistNotesList = (List) tierNoteOverRide.getNotesList();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		if (null != persistNotesList && !persistNotesList.isEmpty()) {
			try {
				AdapterUtil.beginTransaction(serviceAccess);
				AdapterUtil
						.logBeginTranstn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				for (int i = 0; i < persistNotesList.size(); i++) {
					NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO) persistNotesList
							.get(i);

					tierNoteOverRide.setStatus(overrideNotesBO
							.getOverrideStatus());
					tierNoteOverRide.setNoteId(overrideNotesBO.getNoteId());
					tierNoteOverRide.setVersion(overrideNotesBO.getVersion());

					adapterManager.attachNotesForOverrideEntityForTier(
							tierNoteOverRide, audit, false, serviceAccess);
				}
				createSystemComment(tierNoteOverRide.getDateSegmentId(), "",
						(Timestamp) audit.getTime(), audit.getUser(),
						serviceAccess);
				AdapterUtil.endTransaction(serviceAccess);
				AdapterUtil
						.logTheEndTransaction(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				List errorParams = new ArrayList(3);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());

				throw new SevereException(
						"Exception in persist NotesAttachmentOverrideBO in ContractBusinessObjectBuilder",
						errorParams, ex);
			} catch (Exception ex) {
				AdapterUtil.abortTransaction(serviceAccess);
				AdapterUtil
						.logAbortTxn(
								transactionId,
								this,
								"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit, boolean insertflag)");
				throw new SevereException("Unhandled Exception occured", null,
						ex);
			}

		}

		return true;
	}

	/**
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean delete(NotesAttachmentOverrideBO subObject,
			Contract mainObject, Audit audit) throws SevereException {

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"delete(NotesAttachmentOverrideBO subObject,Contract mainObject, Audit audit)");
			createSystemComment(subObject.getDateSegmentId(), "",
					(Timestamp) audit.getTime(), audit.getUser(), serviceAccess);
			NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();
			attachedNotesAdapterManager.deleteNotesForOverriddenEntity(
					subObject, audit);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"delete(NotesAttachmentOverrideBO subObject,Contract mainObject, Audit audit)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"delete(NotesAttachmentOverrideBO subObject,Contract mainObject, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in delete NotesAttachmentOverrideBO in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"delete(NotesAttachmentOverrideBO subObject,Contract mainObject, Audit audit)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param subObject
	 * @param mainObject
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean delete(AttachedNotesBO subObject, DateSegment dateSegment,
			Audit audit) throws SevereException {
		NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();

		try {

			attachedNotesAdapterManager.deleteNotesForEntity(subObject, audit);

		} catch (SevereException se) {
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method in ContractBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(3);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method in ContractBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method in ContractBuilder",
					null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param contractNotesLocateCriteria
	 * @param user
	 * @return LocateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractNotesLocateCriteria contractNotesLocateCriteria, User user)
			throws SevereException {

		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
		int entityId = contractNotesLocateCriteria.getDateSegmentId();
		String entityType = contractNotesLocateCriteria.getEntityType();
		int maxResults = contractNotesLocateCriteria.getMaximumResultSize();

		return notesAttachmentAdapterManager.locateAttachedNotes(entityId,
				entityType, maxResults, null);

	}

	/**
	 * Copies the Business Object from source to target
	 * 
	 * @param srcContract
	 * @param trgtContract
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copy(Contract srcContract, Contract trgtContract, Audit audit)
			throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering copy(): Contract");
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning copy(): Contract");
		adapterManager.copyContract(srcContract.getContractSysId(),
				trgtContract.getContractSysId(), audit.getUser(), audit);
		return false;
	}

	/**
	 * Copies the Business Object from source to target
	 * 
	 * @param srcContract
	 * @param trgtContract
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copyForCheckOut(Contract srcContract, Contract trgtContract,
			Audit audit) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering copy(): Contract");
		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning copy(): Contract");
		contractAdapterManager.copyContract(srcContract.getContractSysId(),
				trgtContract.getContractSysId(), audit.getUser(), audit);
		return false;
	}

	/**
	 * Copies the Business Object from source to target
	 * 
	 * @param srcContract
	 * @param trgtContract
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copyForCheckOut(DateSegment srcDatesegment,
			DateSegment trgtDatesegment, Audit audit) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering copy(): Contract");
		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning copy(): Contract");
		

		
		if (srcDatesegment.getProductStatus().equalsIgnoreCase("Y")) {
			
			ProductBO productBO=(ProductBO)	contractAdapterManager.getLatestProductVersion(trgtDatesegment.getContractSysId(),srcDatesegment.getDateSegmentSysId());		
		
			contractAdapterManager.updateProductForNewDS(trgtDatesegment.getDateSegmentSysId(), productBO.getProductKey());

			contractAdapterManager.replaceProductComponents(productBO.getProductKey(), trgtDatesegment.getDateSegmentSysId(),
					srcDatesegment.getDateSegmentSysId(), audit.getUser(),audit);

		} else {
			contractAdapterManager.updateProductForNewDS(trgtDatesegment.getDateSegmentSysId(),trgtDatesegment.getProductId());
			contractAdapterManager.copyDateSegmentForCheckout(srcDatesegment
					.getDateSegmentSysId(), trgtDatesegment
					.getDateSegmentSysId(), srcDatesegment.getProductStatus(),
					audit);

		}

		return false;
	}

	/**
	 * 
	 * @param subObject
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean copyProductComponents(DateSegment subObject)
			throws SevereException {
        adapterManager.copyProductComponents(subObject.getProductId(),
				subObject.getDateSegmentSysId(), subObject.getCreatedUser());
		return true;

	}
	public Contract retrieveContractPricingInfo(int dateSegmentSysId)throws SevereException {
		Contract contract = new Contract();
		contract = adapterManager.getContractPricingInfoList(contract,dateSegmentSysId);
		return contract;
	}
    
	/**
	 * 
	 * @param subObject
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean replaceProductComponents(DateSegment subObject, User user)
			throws WPDException {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		adapterManager.replaceProductComponents(
				subObject.getProductId(), subObject.getDateSegmentSysId(),
				subObject.getOldDateSegmentId(), subObject.getCreatedUser(),
				audit);
		return true;

	}

	/**
	 * 
	 * @param dateSegmentId
	 * @return List
	 * @throws WPDException
	 */
	public List locateContractNotes(int dateSegmentId, String noteCriteria)
			throws WPDException {

		NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
		return adapterManager.lookupContractNotes(dateSegmentId, noteCriteria);
	}

	/**
	 * Method to retrieve coded benefit components and its benefits.
	 * 
	 * @param dateSegmentId
	 * @param user
	 * @return locateResults
	 * @throws WPDException
	 */
	public LocateResults locate(int dateSegmentId, int productId, User user)
			throws SevereException {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering locate(int dateSegmentId="
						+ dateSegmentId + ":User user=" + user);

		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(benefitAdapterManager
				.getBenefitHeadings(dateSegmentId, productId));
		return locateResults;
	}

	/**
	 * To insert the Sytem generated comment
	 * 
	 * @param dateSegmentId
	 * @param comment
	 * @param currentTime
	 * @param user
	 * @return void
	 * @throws SevereException
	 */
	public void createSystemComment(int dateSegmentId, String comment,
			Timestamp currentTime, String user,
			AdapterServicesAccess serviceAccess) throws SevereException {
		if (comment == null || comment.trim().equals(""))
			comment = WebConstants.CONTRACT_UPDATED;
		Comment commentBO = new Comment();
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int sysId = sequenceAdapterManager.getNextCommentIdSequence();
		commentBO.setCommentSysId(sysId);
		commentBO.setCreatedTimeStamp(currentTime);
		commentBO.setCreatedUser(user);
		commentBO.setLastUpdatedTimeStamp(currentTime);
		commentBO.setLastUpdatedUser(user);
		commentBO.setDateSegmentSysId(dateSegmentId);
		commentBO.setCommentText(comment);
		adapterManager.createComment(commentBO, serviceAccess);
	}

	/**
	 * 
	 * @param contractId
	 * @param LOB
	 * @param businessGroup
	 * @param businessEntity
	 * @return searchResult
	 * @throws SevereException
	 */
	public List locateReservedContract(String contractId, List LOB,
			List businessGroup, List businessEntity, List marketBusinessUnit) throws SevereException {
		List searchResult = adapterManager.searchReservedContract(contractId,
				LOB, businessGroup, businessEntity, marketBusinessUnit);
		return searchResult;
	}

	/**
	 * 
	 * @param contractAdminLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractAdminLocateCriteria contractAdminLocateCriteria, User user)
			throws SevereException {
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(adapterManager
				.getAdminList(contractAdminLocateCriteria
						.getContractDateSegmentSysKey()));
		return locateResults;
	}

	/**
	 * Function to persist a Product Admin Option.
	 * 
	 * @param ContractProductAdminBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException,
	 *             AdapterException
	 */
	public boolean persist(ContractProductAdmin contractProductAdmin,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException, AdapterException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ContractProductAdmin contractProductAdmin,Contract contract, Audit audit, boolean insertFlag)");
			if (insertFlag) {

				String entityType = WebConstants.CONTRACT_PRODUCT_ADMIN;

				List newAdminList = contractProductAdmin.getAdminList();
				ContractProductAdminBO contractProductAdminBO = null;

				if (newAdminList == null || newAdminList.size() == 0) {
					AdapterUtil.endTransaction(serviceAccess);
					AdapterUtil
							.logTheEndTransaction(
									transactionId,
									this,
									"persist(ContractProductAdmin contractProductAdmin,Contract contract, Audit audit, boolean insertFlag)");
					return true;
				}

				// Persisting new Admin

				for (int i = 0; i < newAdminList.size(); i++) {
					contractProductAdminBO = (ContractProductAdminBO) newAdminList
							.get(i);

					contractProductAdminBO.setEntityType(entityType);
					contractProductAdminBO.setLastUpdatedUser(audit.getUser());
					contractProductAdminBO.setCreatedUser(audit.getUser());
					contractProductAdminBO.setCreatedTimestamp(audit.getTime());
					contractProductAdminBO.setLastUpdatedTimestamp(audit
							.getTime());

					this.adapterManager.createProductAdminOption(
							contractProductAdminBO, audit, serviceAccess);
				}
				createSystemComment(contractProductAdminBO
						.getContractDateSegmentSysKey(), "", (Timestamp) audit
						.getTime(), audit.getUser(), serviceAccess);
				this.updateContractAuditInfo(dateSegment, audit, serviceAccess);
			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ContractProductAdmin contractProductAdmin,Contract contract, Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractProductAdmin contractProductAdmin,Contract contract, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in persist ContractProductAdmin in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractProductAdmin contractProductAdmin,Contract contract, Audit audit, boolean insertFlag)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param Contract
	 * @param contract
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistTimeStamp(Contract contract, Audit audit)
			throws SevereException {

		try {

			contract.setLastUpdatedUser(audit.getUser());
			contract.setLastUpdatedTimestamp(audit.getTime());

			adapterManager.updateContract(contract);

		} catch (AdapterException ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject Contract, in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param contractProductAdminBO
	 * @param contract
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean delete(ContractProductAdminBO contractProductAdminBO,
			DateSegment dateSegment, Audit audit) throws SevereException {

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(ContractProductAdminBO contractProductAdminBO,Contract contract, Audit audit)");
			this.adapterManager.deleteProductAdminOption(
					contractProductAdminBO, audit, serviceAccess);

			createSystemComment(contractProductAdminBO
					.getContractDateSegmentSysKey(), "", (Timestamp) audit
					.getTime(), audit.getUser(), serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(ContractProductAdminBO contractProductAdminBO,Contract contract, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ContractProductAdminBO contractProductAdminBO,Contract contract, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in ContractBusinessObjectBuilder in delete (ContractProductAdmin)",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(ContractProductAdminBO contractProductAdminBO,Contract contract, Audit audit)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param contractId
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean searchContractId(String contractId) throws SevereException {

		return adapterManager.searchContractId(contractId);
	}

	/**
	 * 
	 * @param contractId
	 * @return
	 * @throws SevereException
	 */
	public int getContractSysID(String contractId) throws SevereException {
		return adapterManager.getContractSysID(contractId);
	}

	/**
	 * 
	 * @param productName
	 * @return productBO
	 * @throws SevereException
	 */
	public ProductBO getProductCode(String productName) throws SevereException {
		return adapterManager.getProductCode(productName);

	}

	/**
	 * @param contractAdminOptionLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locate(
			ContractAdminOptionLocateCriteria contractAdminOptionLocateCriteria,
			User user) throws SevereException {

		LocateResults locateResults = new LocateResults();

		List sortedList = null;

		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
		List locateResultList = contractAdapterManager
				.locateContractLevelQuestionaire(contractAdminOptionLocateCriteria);
		getPossibleAnswerListContract(locateResultList);

		if (contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_QUESTIONAIRE
				|| contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_QUESTIONAIRE_DF) {
			sortedList = BusinessUtil.getQuestionareHierarchy(locateResultList);
		} else if (contractAdminOptionLocateCriteria.getAction() == WebConstants.RETRIEVE_CHILD_QUESTIONAIRE) {
			int answerId = contractAdminOptionLocateCriteria
					.getSelectedAnswerId();
			int oldQuestionaireListIndex = contractAdminOptionLocateCriteria
					.getQuestionareListIndex();
			List oldQuestionnareList = contractAdminOptionLocateCriteria
					.getQuestionnaireList();
			List oldListForUnsavedQuestion = new ArrayList(
					contractAdminOptionLocateCriteria.getQuestionnaireList());
			ContractAssnQuestionnaireBO contractAssnQuestionnaireBO = (ContractAssnQuestionnaireBO) contractAdminOptionLocateCriteria
					.getContractAssnQuestionnaireBO();
			int questionnaireId = contractAssnQuestionnaireBO
					.getQuestionnaireId();
			((ContractAssnQuestionnaireBO) oldQuestionnareList
					.get(oldQuestionaireListIndex))
					.setSelectedAnswerid(answerId);
			sortedList = BusinessUtil.getRearrangedQuestionnareList(
					locateResultList, oldQuestionnareList,
					oldQuestionaireListIndex);
			int contractId = contractAdminOptionLocateCriteria.getEntityId();
			List notesToDelete = getQuestionareNoteListToDelete(sortedList,
					oldListForUnsavedQuestion);
			deleteUnsavedQuestionNote(notesToDelete, contractId,
					contractAdminOptionLocateCriteria.getAdminOptSysId(), user);
		}

		locateResults.setLocateResults(sortedList);
		return locateResults;
	}

	/*
	 * this method filter the questionareLiat and flag the question notes to be
	 * deleted
	 * 
	 *  
	 */
	public static List getQuestionareNoteListToDelete(List newQuestionnareList,
			List oldListForUnsavedQuestion) {

		List listToDelete = new ArrayList();

		for (int i = 0; i < oldListForUnsavedQuestion.size(); i++) {
			ContractAssnQuestionnaireBO oldQuestionnareBo = (ContractAssnQuestionnaireBO) oldListForUnsavedQuestion
					.get(i);

			for (int j = 0; j < newQuestionnareList.size(); j++) {

				ContractAssnQuestionnaireBO newQuestionnareBo = (ContractAssnQuestionnaireBO) newQuestionnareList
						.get(j);
				if (newQuestionnareBo.compareTo(oldQuestionnareBo)) {
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			listToDelete.add(oldQuestionnareBo);
		}

		return newQuestionnareList;

	}

	/*
	 * this method perfotm delete the unsaved question note deatils while the
	 * questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 *  
	 */
	private void deleteUnsavedQuestionNote(List noteDetailList, int productId,
			int adminOptionId, User user) throws SevereException {
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();

		for (int i = 0; i < noteDetailList.size(); i++) {
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ContractAssnQuestionnaireBO questionnareBo = (ContractAssnQuestionnaireBO) noteDetailList
					.get(i);
			if (questionnareBo.isUnsavedData()) {
				questionnareBo.setNotesExists("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBenefitBO attachmentBo = new NotesToQuestionAttachmentBenefitBO();
				attachmentBo.setPrimaryId(productId);
				attachmentBo.setSecondaryId(adminOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHCONTRACT");
				attachmentBo.setSecondaryEntityType("ATTACHADMNQUEST");
				attachmentBo.setNoteOverrideStatus("F");
				NotesToQuestionAttachmentBenefitBO newattachmentBo = getNoteInfo(attachmentBo);
				if (null != newattachmentBo
						&& null != newattachmentBo.getNoteId()
						&& !("").equals(newattachmentBo.getNoteId())) {
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setNoteVersionNumber(newattachmentBo
							.getNoteVersionNumber());
					try {
						adapterManager.deleteNotesAttachedToQuestion(
								attachmentBo, audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private NotesToQuestionAttachmentBenefitBO getNoteInfo(
			NotesToQuestionAttachmentBenefitBO attachmentBo) {
		NotesToQuestionAttachmentBenefitBO newAttachmentBo = null;
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if (null != resultList && resultList.size() > 0) {
				newAttachmentBo = (NotesToQuestionAttachmentBenefitBO) resultList
						.get(0);
			}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newAttachmentBo;
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
	public boolean persist(EntityProductBenefitAdministration subObject,
			Contract mainObject, Audit audit, boolean insertFlag)
			throws SevereException {

		// Get the BOList from the BO
		List benefitAdministrationList = (List) subObject
				.getBenefitAdministrationList();

		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		ProductAdapterManager productAdapterManager = new ProductAdapterManager();

		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(EntityProductBenefitAdministration subObject,Contract mainObject, Audit audit, boolean insertFlag)");

			// Iterate through the List
			if (null != benefitAdministrationList) {
				for (int i = 0; i < benefitAdministrationList.size(); i++) {

					// Get the individual BOs from the list
					EntityProductAdministration administration = (EntityProductAdministration) benefitAdministrationList
							.get(i);
					administration.setLastUpdatedTimeStamp(audit.getTime());
					administration.setLastUpdatedUser(audit.getUser());
					// Call the persist() of the adapterManager to update the
					// administration
					productAdapterManager.updateProductAdministrationValues(
							administration, audit.getUser(),
							adapterServicesAccess);
				}
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(EntityProductBenefitAdministration subObject,Contract mainObject, Audit audit, boolean insertFlag)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(EntityProductBenefitAdministration subObject,Contract mainObject, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in updateDataFeedStatus in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(EntityProductBenefitAdministration subObject,Contract mainObject, Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		return true;
	}

	/**
	 * 
	 * @param dataFeedStatus
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateDataFeedStatus(DataFeedStatus dataFeedStatus,
			String userId) throws SevereException {

		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"updateDataFeedStatus(DataFeedStatus dataFeedStatus,String userId)");
			adapterManager.updateDataFeedStatus(dataFeedStatus, userId,
					serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"updateDataFeedStatus(DataFeedStatus dataFeedStatus,String userId)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"updateDataFeedStatus(DataFeedStatus dataFeedStatus,String userId)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in updateDataFeedStatus in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"updateDataFeedStatus(DataFeedStatus dataFeedStatus,String userId)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param assignedRuleIdBO
	 * @return locateResults
	 * @throws SevereException
	 */
	public List searchEntityRuleAssociation(AssignedRuleIdBO assignedRuleIdBO)
			throws SevereException {
		RefdataAdapterManager adapterManager = new RefdataAdapterManager();
		if (assignedRuleIdBO.getAction().equals(
				BusinessConstants.DATAFEED_ACTION))
			return adapterManager.searchRuleIdForDatafeed(assignedRuleIdBO);
		else
			return adapterManager.searchRuleId(assignedRuleIdBO);
	}

	/**
	 * 
	 * @param datafeedLocateCriteria
	 * @param user
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locate(DatafeedLocateCriteria datafeedLocateCriteria,
			User user) throws SevereException {
		LocateResults locateResults = new LocateResults();
		if (datafeedLocateCriteria.getContractType() == BusinessConstants.DATAFEED_RETRIEVE_CONTRACT)
			locateResults.setLocateResults(adapterManager
					.getScheduledCntrctsForDatafeed(datafeedLocateCriteria));
		else if (datafeedLocateCriteria.getContractType() == BusinessConstants.DATAFEED_RETRIEVE_MANDATE_CONTRACT)
			locateResults
					.setLocateResults(adapterManager
							.getScheduledMandateCntrctsForDatafeed(datafeedLocateCriteria));

		return locateResults;

	}

	/**
	 * 
	 * @param existingContractLocateCriteria
	 * @return locateResults
	 * @throws SevereException
	 */
	public LocateResults locateExistingContract(
			ExistingContractLocateCriteria existingContractLocateCriteria)
			throws SevereException {
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(adapterManager
				.getExistingContracts(existingContractLocateCriteria));
		return locateResults;

	}

	/**
	 * Method to retrieve coded benefit components and its benefits.
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param user
	 * @return locateResults
	 * @throws WPDException
	 */
	public LocateResults locate(int dateSegmentId, int standardBenefitId,
			int benefitComponentId, User user) throws SevereException {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering locate(int dateSegmentId="
						+ dateSegmentId + ":User user=" + user);

		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(benefitAdapterManager.getBenefitLines(
				dateSegmentId, standardBenefitId, benefitComponentId));
		return locateResults;
	}

	/**
	 * 
	 * @param destiantionDateSegmentSysId
	 * @param productId
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean deleteBenefitValues(int destiantionDateSegmentSysId,
			int productId, User user) throws SevereException {

		ContractBenefitHeadings benefitHeadings = new ContractBenefitHeadings();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"deleteBenefitValues(int destiantionDateSegmentSysId,int productId, User user)");
			benefitHeadings.setDateSegmentId(destiantionDateSegmentSysId);
			benefitHeadings.setProductId(productId);
			benefitHeadings
					.setBenefitComponentName(WebConstants.BENEFIT_COMPONENT_NAME_SET);
			benefitHeadings
					.setStandardBenefitName(WebConstants.STANDARD_BENEFIT_NAME_SET);
			benefitHeadings.setBenefitLineId(123);
			adapterManager.deleteBenefitValues(benefitHeadings, user
					.getUserId(), serviceAccess);
			adapterManager.insertProductBenefitValues(benefitHeadings, user
					.getUserId());
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"deleteBenefitValues(int destiantionDateSegmentSysId,int productId, User user)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"deleteBenefitValues(int destiantionDateSegmentSysId,int productId, User user)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in deleteBenefitValues in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"deleteBenefitValues(int destiantionDateSegmentSysId,int productId, User user)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return true;

	}

	/**
	 * 
	 * @param bnftHeadings
	 * @param sourceDateSegmentId
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean copySelectedBenefitValues(
			ContractBenefitHeadings bnftHeadings, int sourceDateSegmentId,
			String user, AdapterServicesAccess serviceAccess)
			throws SevereException {
		if (null == serviceAccess) {
			serviceAccess = AdapterUtil.getAdapterService();
		}
		//This method updates the line values
		adapterManager.updateBenefitValues(bnftHeadings, user, serviceAccess);
		//This method calls the procedure UPDATE_CONTRACT_NOTES .. It has to be
		// changed to accomodate the new table(CNTRCT_BNFT_CSTMZD)
		//		updateContractNotes(bnftHeadings.getStandardBenefitId(), bnftHeadings
		//				.getDateSegmentId(), bnftHeadings.getBenefitLineId(),
		//				sourceDateSegmentId, user,
		//				bnftHeadings.getBenefitComponentId(), serviceAccess);

		return true;

	}

	/**
	 * 
	 * @param bnftHeadings
	 * @param sourceDateSegmentId
	 * @param user
	 * @throws SevereException
	 */
	public void copySelectedBenefitValues(ContractBenefitHeadings bnftHeadings,
			int sourceDateSegmentId, String user) throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		String methodName = "copySelectedBenefitValues(ContractBenefitHeadings bnftHeadings, int sourceDateSegmentId,String user)";

		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil.logBeginTranstn(transactionId, this, methodName);
			//This method updates the line values
			adapterManager.updateBenefitValues(bnftHeadings, user,
					serviceAccess);
			//This method calls the procedure UPDATE_CONTRACT_NOTES .. It has
			// to be changed to accomodate the new table(CNTRCT_BNFT_CSTMZD)
			//			updateContractNotes(bnftHeadings.getStandardBenefitId(),
			//					bnftHeadings.getDateSegmentId(), bnftHeadings
			//							.getBenefitLineId(), sourceDateSegmentId, user,
			//					bnftHeadings.getBenefitComponentId(), serviceAccess);

			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil.logTheEndTransaction(transactionId, this, methodName);
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this, methodName);
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in deleteBenefitValues in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this, methodName);
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

	}
	
	public boolean isLineCodedInDateSegment(int dateSegmentSysID, int benefitComponentSysID, int benefitLineSysID)
	 throws SevereException {
		return adapterManager.isLineCodedInDateSegment(dateSegmentSysID,benefitComponentSysID,benefitLineSysID);
	}

	/**
	 * 
	 * @param productId
	 * @param dateSegmnetId
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean copyProductDefaultComponents(int productId,
			int dateSegmnetId, User user) throws SevereException {

		adapterManager.copyProductDefaultComponents(productId, dateSegmnetId,
				user.getUserId());
		return true;

	}

	/**
	 * 
	 * @param contractID
	 * @return
	 * @throws SevereException
	 */
	public Date minEffectiveOfLatestContract(String contractID)
			throws SevereException {
		DateSegment dateSegment = adapterManager
				.minEffectiveOfLatestContract(contractID);
		if (null != dateSegment) {
			return dateSegment.getEffectiveDate();
		}
		return null;
	}

	/**
	 * 
	 * @param sourceStandardBenefitId
	 * @param destiantionDateSegmentSysId
	 * @param sourceLineId
	 * @param sourceDateSegmentSysId
	 * @param user
	 * @param benefitComponentId
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean updateContractNotes(int sourceDateSegmentSysId, int trgtDateSegmentSysId, String user,
			AdapterServicesAccess serviceAccess) throws SevereException {

		adapterManager.updateContractNotes(sourceDateSegmentSysId, trgtDateSegmentSysId, user,
				serviceAccess);
		return true;

	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param sourceDateSegmentSysId
	 * @param destiantionDateSegmentSysId
	 * @param user
	 * @param adapterServiceaccess
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean updateContractInfoForCopy(int sourceDateSegmentSysId, int targetdatesegmentId,int contractSysId,Audit audit,
			AdapterServicesAccess serviceAccess) throws SevereException {
		if(null!=serviceAccess)
			adapterManager.updateContractInfoForCopy(sourceDateSegmentSysId, targetdatesegmentId,contractSysId,audit,serviceAccess);
		else{
			AdapterServicesAccess access = AdapterUtil.getAdapterService();
			adapterManager.updateContractInfoForCopy(sourceDateSegmentSysId, targetdatesegmentId,contractSysId,audit,access);
		}
		return true;

	}

	/**
	 * 
	 * @param contractSysId
	 * @param effectiveDate
	 * @return
	 * @throws SevereException
	 */
	public int getDateSegmentSysId(int contractSysId, String effectiveDate)
			throws SevereException {
		//        List effectiveDateList = new ArrayList();
		List effectiveDateList = adapterManager.getEffectiveDateSegmentId(
				contractSysId, effectiveDate);
		DateSegment dateSegment;
		if (effectiveDateList != null && effectiveDateList.size() != 0) {
			dateSegment = (DateSegment) effectiveDateList.get(0);
			return dateSegment.getDateSegmentSysId();
		}
		return 0;
	}

	/**
	 * 
	 * @param srcContract
	 * @param trgtContract
	 * @param audit
	 * @param hashMap
	 * @return boolean
	 * @throws WPDException
	 */

	public boolean copy(Contract srcContract, Contract trgtContract,
			Audit audit, HashMap hashMap) throws SevereException {

		//        List effectiveDateList = new ArrayList();
		List effectiveDateList = adapterManager.getEffectiveDateSegmentId(
				trgtContract.getContractSysId(), srcContract
						.getEffectiveDateForCopy());
		DateSegment dateSegment = new DateSegment();
		if (effectiveDateList != null && effectiveDateList.size() != 0) {
			dateSegment = (DateSegment) effectiveDateList.get(0);
			trgtContract.setContractDateSegmentSysId(dateSegment
					.getDateSegmentSysId());
		}
		List headingsList = srcContract.getSelectedHeadingsList();
		int sourceDateSegmentId = srcContract.getContractDateSegmentSysId();
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"copy(Contract srcContract, Contract trgtContract,Audit audit, HashMap hashMap)");
			if(headingsList != null && !headingsList.isEmpty()) {    
				Iterator copyItr = 	headingsList.iterator();
				while(copyItr.hasNext()){
					ContractBenefitHeadings bnftLine = (ContractBenefitHeadings) copyItr.next();
					CopyBenefitHeadingsBO headingBO = new CopyBenefitHeadingsBO();
					headingBO.setBenefitsysId(bnftLine
									.getStandardBenefitId());
					headingBO.setComponentId(bnftLine
									.getBenefitComponentId());
					headingBO.setTrgtDateSegmentId(trgtContract
									.getContractDateSegmentSysId());
					insertBenefitHeadingDetails(headingBO, audit.getUser(),
									serviceAccess);
	
				}
				updateContractInfoForCopy(sourceDateSegmentId,trgtContract
						.getContractDateSegmentSysId(),trgtContract.getContractSysId(), audit,
						serviceAccess);
			}
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"copy(Contract srcContract, Contract trgtContract,Audit audit, HashMap hashMap)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copy(Contract srcContract, Contract trgtContract,Audit audit, HashMap hashMap)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in copy in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"copy(Contract srcContract, Contract trgtContract,Audit audit, HashMap hashMap)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

		return false;
	}

	/**
	 * 
	 * @param testData
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void insertBenefitHeadingDetails(CopyBenefitHeadingsBO headingsBO,
			String user, AdapterServicesAccess access) throws SevereException {
		adapterManager.insertBenefitHeadingDetails(headingsBO, user, access);
	}
	
	/**
	 * 
	 * @param testData
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void deleteTierLvlLine(CopyBenefitHeadingsBO copyBenefitHeadingsBO,
			Audit audit) throws SevereException {
		adapterManager.deleteTierLvlLine(copyBenefitHeadingsBO, audit);
	}

	/**
	 * 
	 * @param testData
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void deleteTestData(TestData testData, Audit audit)
			throws SevereException {
		AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccess);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"deleteTestData(TestData testData, Audit audit)");
			adapterManager.deleteTestData(testData, audit, serviceAccess);
			AdapterUtil.endTransaction(serviceAccess);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"deleteTestData(TestData testData, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"deleteTestData(TestData testData, Audit audit)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());

			throw new SevereException(
					"Exception in deleteTestData in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(serviceAccess);
			AdapterUtil.logAbortTxn(transactionId, this,
					"deleteTestData(TestData testData, Audit audit)");
			throw new SevereException("Unhandled Exception occured", null, ex);
		}

	}

	/**
	 * 
	 * @param standardBenefitId
	 * @return ruleId
	 * @throws SevereException
	 */
	public RuleIdBO getRuleId(int standardBenefitId, int benefitComponentId,
			int dateSegmentId) throws SevereException {
		return adapterManager.getRuleId(standardBenefitId, benefitComponentId,
				dateSegmentId);
	}

	/**
	 * 
	 * @param standardBenefitId
	 * @return ruleId
	 * @throws SevereException
	 */
	public List getRuleIdDatafeed(List benefitList, int benefitComponentId,
			int dateSegmentId, int action) throws SevereException {
		return adapterManager.getRuleIdForDatafeed(benefitList,
				benefitComponentId, dateSegmentId);
	}

	/**
	 * 
	 * @param contractSysId
	 * @param productId
	 * @return productBo
	 * @throws SevereException
	 */
	public ProductBO retrieveLatestProductVersionForCopy(int contractSysId,
			int productId, int dateSegmentId) throws SevereException {

		return adapterManager.getLatestProductVersionForCopy(contractSysId,
				productId, dateSegmentId);
	}

	/**
	 * 
	 * @param noteId
	 * @param version
	 * @return noteBO
	 * @throws SevereException
	 */
	public NoteBO checkNote(String noteId, int version) throws SevereException {
		return adapterManager.checkNote(noteId, version);
	}

	/**
	 * To get the mandates associated with a product Structure
	 * 
	 * @param productStructureMandatesLocateCriteriaBO
	 * @param user
	 * @return locateResults
	 */
	public LocateResults locate(
			ContractMandatesLocateCriteria contractMandatesLocateCriteria,
			User user) throws SevereException {
		LocateResults locateResults = new LocateResults();
		List benefitMandateBOImplList = new ArrayList();
		//		List stateList = new ArrayList();
		List state = null;
		NonAdjBenefitMandateAdapterManager nonAdjAdapterManager = new NonAdjBenefitMandateAdapterManager();

		BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();
		try {
			benefitMandateBOImpl
					.setBenefitSystemId(contractMandatesLocateCriteria
							.getBenefitId());
			nonAdjAdapterManager.retrieve(benefitMandateBOImpl);
			contractMandatesLocateCriteria
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
			List list = citationResults.getLocateResults();
			List citationList = new ArrayList();
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				CitationNumberBO citationNumberBO = (CitationNumberBO) iterator
						.next();
				citationList.add(citationNumberBO);
			}
			stateLocateCriteria.setBenefitMandateId(benefitMandateBOImpl
					.getBenefitMandateId());
			LocateResults stateResults = nonAdjBenefitMandateAdapterManager
					.locateStateObject(stateLocateCriteria);
			List stateList = stateResults.getLocateResults();
			if (null != stateList) {
				state = new ArrayList(stateList.size());
				Iterator stateIterator = stateList.iterator();
				while (stateIterator.hasNext()) {
					StateBO stateBO = (StateBO) stateIterator.next();
					state.add(stateBO);
				}
			}
			benefitMandateBOImpl.setCitationNumberList(citationList);
			benefitMandateBOImpl.setBenefitStateCodeList(state);
			benefitMandateBOImplList.add(0, benefitMandateBOImpl);
			locateResults.setLocateResults(benefitMandateBOImplList);
		} catch (SevereException se) {
			List errorParams = new ArrayList(3);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractMandatesLocateCriteria method in ContractBusinessObjectBuilder",
					errorParams, se);
		} catch (Exception ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ContractMandatesLocateCriteria method in ContractBusinessObjectBuilder",
					null, ex);
		}
		return locateResults;
	}

	/**
	 * 
	 * @param subObject
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException
	 */

	public boolean persist(ContractHistory subObject, DateSegment dateSegment,
			Audit audit, boolean insertFlag) throws SevereException {
		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering persist(): ContractHistory");

		AdapterServicesAccess contractHistoryAdapterServiceAccess = AdapterUtil
				.getAdapterService(); // new
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(contractHistoryAdapterServiceAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(ContractHistory subObject, Contract contract,Audit audit, boolean insertFlag)");
			subObject.setCreatedTimeStamp(audit.getTime());
			subObject.setLastUpdatedUser(audit.getUser());
			subObject.setCreatedUser(audit.getUser());
			subObject.setLastUpdatedTimeStamp(audit.getTime());
			if (insertFlag) {

				adapterManager.createContractHistoryDetails(subObject,
						contractHistoryAdapterServiceAccess);
			}
			AdapterUtil.endTransaction(contractHistoryAdapterServiceAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(ContractHistory subObject, Contract contract,Audit audit, boolean insertFlag)");
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(contractHistoryAdapterServiceAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(ContractHistory subObject, Contract contract,Audit audit, boolean insertFlag)");
			throw new SevereException(null, null, ex);
		}
		Logger
				.logInfo("ContractBusinessObjectBuilder - Returning persist(): Contract");
		return true;
	}

	/**
	 * Method to retrieve coded and noncoded benefit components and its
	 * benefits.
	 * 
	 * @param dateSegmentId
	 * @param user
	 * @return locateResults
	 * @throws WPDException
	 */

	public LocateResults locateCodedNonCodedHeadings(int dateSegmentId,
			int productId, User user) throws SevereException {

		Logger
				.logInfo("ContractBusinessObjectBuilder - Entering locate(int dateSegmentId="
						+ dateSegmentId + ":User user=" + user);

		BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(benefitAdapterManager
				.getCodedNonCodedBenefitHeadings(dateSegmentId, productId));
		return locateResults;
	}

	/**
	 * Method to retrieve SPS Benefit Lines for datafeed
	 * 
	 * @param dateSegmentId
	 * @return Map
	 * @throws WPDException
	 */

	public Map getSPSBnftLines(int dateSegmentSysId) throws SevereException {

		return adapterManager.getSPSBnftLines(dateSegmentSysId);

	}
	
	public List getContractDFAdminMethods(int dateSegmentSysId) throws SevereException {
		return adapterManager.getContractDFAdminMethods(dateSegmentSysId);
	}
	
	public List getContractDFNotes(int dateSegmentSysId) throws SevereException {
		return adapterManager.getContractDFNotes(dateSegmentSysId);
	}
	
	 public List getContractDFBenefits(int dateSegmentSysId) throws SevereException {
		 return adapterManager.getContractDFBenefits(dateSegmentSysId);
	 }

	 public List getContractDFQuestions(int dateSegmentId) throws SevereException {
		 return adapterManager.getContractDFQuestions(dateSegmentId);
	 }
	/**
	 * Method to retrieve Admin Options
	 * 
	 * @param dateSegmentId
	 * @return List
	 * @throws WPDException
	 */

	public Map getAdminOptions(int dateSegmentSysId) throws SevereException {

		return adapterManager.getAdminOptions(dateSegmentSysId);

	}

	/**
	 * @param locateResultList
	 * @throws SevereException
	 */
	public void getPossibleAnswerListContract(List locateResultList)
			throws SevereException {
	      if(locateResultList != null && !locateResultList.isEmpty()){
			QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();
			for (int i = 0; i < locateResultList.size(); i++) {
				ContractAssnQuestionnaireBO contractAssnQuesitionnaireBO = (ContractAssnQuestionnaireBO) locateResultList
						.get(i);
				int questionNumber = contractAssnQuesitionnaireBO
						.getQuestionId();
				QuestionBO questionBO = new QuestionBO();
				questionBO.setQuestionNumber(questionNumber);
				List possibleAnswerList = questionAdapterManager
						.getPossibleAnswer(questionBO, adapterServicesAccess);
				List arrangedPossibleAnswerList = BusinessUtil
						.getRearrangedPossibleAnswerList(possibleAnswerList);
				contractAssnQuesitionnaireBO
						.setPossibleAnswerList(arrangedPossibleAnswerList);
			}
		}
	}

	/**
	 * @param contractAssnQuestionnaireBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	 */
	public boolean persist(
			ContractAssnQuestionnaireBO contractAssnQuestionnaireBO,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();

		List questionnaireList = contractAssnQuestionnaireBO
				.getQuestionnaireList();
		int dateSegmentId = contractAssnQuestionnaireBO.getDateSegmentId();
		int adminOptionId = contractAssnQuestionnaireBO.getAdminOptionId();

		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(ContractAssnQuestionnaireBO,Contract, Audit,boolean)");
			contractAdapterManager.delete(contractAssnQuestionnaireBO, audit,
					adapterServicesAccess);
			int size = questionnaireList.size();
			for (int i = 0; i < size; i++) {
				ContractAssnQuestionnaireBO questionnaireBO = (ContractAssnQuestionnaireBO) questionnaireList
						.get(i);
				questionnaireBO.setAdminOptionId(adminOptionId);
				questionnaireBO.setDateSegmentId(dateSegmentId);
				questionnaireBO.setCreatedUser(audit.getUser());
				questionnaireBO.setCreatedTimestamp(audit.getTime());
				questionnaireBO.setLastUpdatedUser(audit.getUser());
				questionnaireBO.setLastUpdatedTimestamp(audit.getTime());
				contractAdapterManager.persistContractQuestionnaire(
						questionnaireBO, audit, insertFlag,
						adapterServicesAccess);
			}
			this.updateContractAuditInfo(dateSegment, audit,
					adapterServicesAccess);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(ContractAssnQuestionnaireBO,Contract, Audit,boolean)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(ContractAssnQuestionnaireBO,Contract, Audit,boolean)");
			throw new SevereException(
					"Persist for ContractAssnQuestionnaireBO failed.Unknown exception is caught",
					null, ex);
		}
		return true;
	}

	public List getContractAdjudicationIndicator(int dateSegmentSysId,
			String referenceId) throws SevereException {

		return adapterManager.locateContractAdjudicationIndicator(
				dateSegmentSysId, referenceId);

	}

	public List getContractAccumulatorIndicator(
			ContractBenefitAdministrationLocateCriteria locateCriteria)
			throws SevereException {

		return adapterManager
				.locateContractAccumulatorIndicator(locateCriteria);

	}

	/**
	 * Method to retrieve DateSegment Transfer Log
	 * 
	 * @param contractTransferLogBO
	 * @param user
	 * @return List
	 * @throws SevereException
	 */
	public List locateTransferLog(ContractTransferLogBO contractTransferLogBO,
			User user) throws SevereException {

		List result = new ArrayList();
		result = adapterManager.locateTransferLog(contractTransferLogBO, user);
		return result;
	}

	/**
	 * @param datesegmentId
	 * @return
	 * @throws SevereException
	 */
	public List getBYCYAnswerList(int datesegmentId) throws SevereException {
		List BYCYAnswerList = adapterManager.getBYCYAnswerList(datesegmentId);
		return BYCYAnswerList;
	}

	/**
	 * This method is to verify that the contract has any duplicate references.
	 * 
	 * @param dateSegmentList(List)
	 * @return status(boolean)
	 * @throws SevereException
	 */
	public boolean getDuplicateReference(List dateSegmentList)
			throws SevereException {
		boolean status = true;
		ContractQuestUniqueReferenceBO contractDateSegmentsBO = new ContractQuestUniqueReferenceBO();
		int dateSegmentListSize = dateSegmentList.size();
		//iterating through the dateSegment List of the contract
		for (int dateSegmentCount = 0; dateSegmentCount < dateSegmentListSize; dateSegmentCount++) {
			contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
					.get(dateSegmentCount);
			int contractDateSegmentId = contractDateSegmentsBO
					.getContractdateSegmentSysId();
			if (status) {
				status = adapterManager
						.getDuplicateReferences(contractDateSegmentId);
			} else {
				return status;
			}
		}
		return status;
	}

	/**
	 * This method is to reterive all the datesegment fro the contract
	 * 
	 * @param contractId
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveDateSegments(String contractId) throws SevereException {
		List dateSegList = adapterManager.retrieveDateSegments(contractId);
		return dateSegList;
	}

	/**
	 * This method is to get the duplicate reference list of benefit level and
	 * question of a contract
	 * 
	 * @param dateSegmentList(List)
	 * @return allDuplicateReferenceList(List)
	 * @throws SevereException
	 */
	public List getDuplicateUniqueReferences(List dateSegmentList)
			throws SevereException {

		int lineCount = 0;
		int questCount = 0;
		int dateSegmentListSize = dateSegmentList.size();

		List lineDuplicateList = new ArrayList();
		List questDuplicateList = new ArrayList();
		List benefitDuplicateList = new ArrayList();
		List questustionDuplicateList = new ArrayList();
		List allDuplicateReferenceList = new ArrayList();
		ContractQuestUniqueReferenceBO contractDateSegmentsBO = new ContractQuestUniqueReferenceBO();
		//iterating through the dateSegment List of the contract
		for (int dateSegmentCount = 0; dateSegmentCount < dateSegmentListSize; dateSegmentCount++) {
			contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
					.get(dateSegmentCount);
			int contractDateSegmentId = contractDateSegmentsBO
					.getContractdateSegmentSysId();
			String dateRange = WPDStringUtil
					.getStringDate(contractDateSegmentsBO.getStartDate())
					+ "-"
					+ WPDStringUtil.getStringDate(contractDateSegmentsBO
							.getEndDate());
			String prdName = contractDateSegmentsBO.getProductName();
			List duplicateReferenceList = getAllDuplicateReference(
					contractDateSegmentId, lineCount, questCount, dateRange,
					prdName);
			lineDuplicateList = (List) duplicateReferenceList.get(0);
			questDuplicateList = (List) duplicateReferenceList.get(1);

			lineCount = lineCount + lineDuplicateList.size();
			questCount = questCount + questDuplicateList.size();
			benefitDuplicateList.addAll(lineDuplicateList);
			questustionDuplicateList.addAll(questDuplicateList);

		}
		allDuplicateReferenceList.add(0, benefitDuplicateList);
		allDuplicateReferenceList.add(1, questustionDuplicateList);
		return allDuplicateReferenceList;
	}

	/**
	 * This method is to get the duplicate reference list of benefit level and
	 * question of a contract
	 * 
	 * @param dateSegmentList(List)
	 * @return allDuplicateReferenceList(List)
	 * @throws SevereException
	 */
	public List getUncodedNotesList(List dateSegmentList)
			throws SevereException {

		int lineCount = 0;
		int questCount = 0;
		int prdcount = 0;
		int dateSegmentListSize = dateSegmentList.size();

		List allNotesList = new ArrayList();
		List dsNoteList = null;
		int dsSize = dateSegmentList.size();
		ContractQuestUniqueReferenceBO contractDateSegmentsBO = new ContractQuestUniqueReferenceBO();
		//iterating through the dateSegment List of the contract
		for (int dsCount = 0; dsCount < dsSize; dsCount++) {
			dsNoteList = new ArrayList();
			contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
					.get(dsCount);
			int contractDateSegmentId = contractDateSegmentsBO
					.getContractdateSegmentSysId();
			String prodName = contractDateSegmentsBO.getProductName();
			String dateRange = WPDStringUtil
					.getStringDate(contractDateSegmentsBO.getStartDate())
					+ "-"
					+ WPDStringUtil.getStringDate(contractDateSegmentsBO
							.getEndDate());

			dsNoteList = getAllUncodedNotes(contractDateSegmentId, lineCount,
					questCount, prdcount, dateRange, prodName);
			allNotesList.add(dsCount, dsNoteList);

			lineCount = lineCount + ((List) dsNoteList.get(0)).size();
			questCount = questCount + ((List) dsNoteList.get(1)).size();
			prdcount = prdcount + ((List) dsNoteList.get(2)).size();

		}

		return allNotesList;
	}

	public List getUncodedTierNotesList(List dateSegmentList)
			throws SevereException {

		int lineCount = 0;
		int questCount = 0;

		int dateSegmentListSize = dateSegmentList.size();

		List allNotesList = new ArrayList();
		List dsNoteList = null;
		int dsSize = dateSegmentList.size();
		ContractQuestUniqueReferenceBO contractDateSegmentsBO = new ContractQuestUniqueReferenceBO();
		//iterating through the dateSegment List of the contract
		for (int dsCount = 0; dsCount < dsSize; dsCount++) {
			dsNoteList = new ArrayList();
			contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) dateSegmentList
					.get(dsCount);
			int contractDateSegmentId = contractDateSegmentsBO
					.getContractdateSegmentSysId();
			String prodName = contractDateSegmentsBO.getProductName();
			String dateRange = WPDStringUtil
					.getStringDate(contractDateSegmentsBO.getStartDate())
					+ "-"
					+ WPDStringUtil.getStringDate(contractDateSegmentsBO
							.getEndDate());

			dsNoteList = getAllUncodedTierNotes(contractDateSegmentId,
					lineCount, questCount, dateRange, prodName);
			allNotesList.add(dsCount, dsNoteList);

			lineCount = lineCount + ((List) dsNoteList.get(0)).size();
			questCount = questCount + ((List) dsNoteList.get(2)).size();
		}

		return allNotesList;
	}

	/**
	 * Mehtod used to insert/update the attached notes in an Admin Option
	 * attached to a benefit in a contract
	 * 
	 * @param notesToQuestionAttachmentBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	public void persist(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {

		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();

		notesToQuestionAttachmentBO.setCreatedTimestamp(dateSegment
				.getCreatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedTimestamp(dateSegment
				.getLastUpdatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedUser(dateSegment
				.getLastUpdatedUser());
		notesToQuestionAttachmentBO
				.setCreatedUser(dateSegment.getCreatedUser());

		try {

			if (insertFlag)
				notesAdapterManager.insertNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
					errorParams, exception);

		}
		try {

			if (!insertFlag)
				notesAdapterManager.updateNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for updating the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
					errorParams, exception);

		}
	}

	/**
	 * Mehtod used to insert/update the attached notes in a teired Admin Option
	 * attached to a benefit in a contract
	 * 
	 * @param notesToQuestionAttachmentBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	//	public void persist(
	//			TierNotesAttachmentOverrideBO attachmentOverrideBO,
	//			DateSegment dateSegment, Audit audit, boolean insertFlag)
	//			throws SevereException {
	//
	//		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
	//
	//		attachmentOverrideBO.setCreatedTimestamp(dateSegment
	//				.getCreatedTimestamp());
	//		attachmentOverrideBO.setLastUpdatedTimestamp(dateSegment
	//				.getLastUpdatedTimestamp());
	//		attachmentOverrideBO.setLastUpdatedUser(dateSegment
	//				.getLastUpdatedUser());
	//		attachmentOverrideBO
	//				.setCreatedUser(dateSegment.getCreatedUser());
	//
	//		try {
	//
	//			if (insertFlag)
	//				notesAdapterManager.insertNotesAttachedToQuestion(
	//						attachmentOverrideBO, audit);
	//
	//		} catch (AdapterException exception) {
	//
	//			List errorParams = new ArrayList();
	//			String obj = exception.getClass().getName();
	//			errorParams.add(obj);
	//
	//			throw new SevereException(
	//					"Exception occured in persist method , for persisting the BusinessObject
	// NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
	//					errorParams, exception);
	//
	//		}
	//		try {
	//
	//			if (!insertFlag)
	//				notesAdapterManager.updateNotesAttachedToQuestion(
	//						attachmentOverrideBO, audit);
	//
	//		} catch (AdapterException exception) {
	//
	//			List errorParams = new ArrayList();
	//			String obj = exception.getClass().getName();
	//			errorParams.add(obj);
	//
	//			throw new SevereException(
	//					"Exception occured in persist method , for updating the BusinessObject
	// NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
	//					errorParams, exception);
	//
	//		}
	//	}
	//	
	//	/**
	//	 * Mehtod used to delete the attached notes in a teired Admin Option
	// attached to a
	//	 * benefit in a contract
	//	 *
	//	 * @param notesToQuestionAttachmentBO
	//	 * @param contract
	//	 * @param audit
	//	 * @throws SevereException
	//	 */
	//	public void delete(TierNotesAttachmentOverrideBO attachmentOverrideBO,
	//			DateSegment dateSegment, Audit audit) throws SevereException {
	//		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
	//		try {
	//			notesAdapterManager.deleteNotesAttachedToQuestion(
	//					attachmentOverrideBO, audit);
	//		} catch (AdapterException exception) {
	//			List errorParams = new ArrayList();
	//			String obj = exception.getClass().getName();
	//			errorParams.add(obj);
	//
	//			throw new SevereException(
	//					"Exception occured in delete method , for deleting the BusinessObject
	// NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
	//					errorParams, exception);
	//		}
	//
	//	}
	//
	//	
	/**
	 * Mehtod used to delete the attached notes in an Admin Option attached to a
	 * benefit in a contract
	 * 
	 * @param notesToQuestionAttachmentBO
	 * @param contract
	 * @param audit
	 * @throws SevereException
	 */
	public void delete(NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
			DateSegment dateSegment, Audit audit) throws SevereException {
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		try {
			notesAdapterManager.deleteNotesAttachedToQuestion(
					notesToQuestionAttachmentBO, audit);
		} catch (AdapterException exception) {
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject NotesToQuestionAttachmentBO, in ContractBusinessObjectBuilder",
					errorParams, exception);
		}

	}

	/**
	 * Mehtod used to insert/update the attached notes in an Admin Option at
	 * contract level
	 * 
	 * @param contractProductAONotesAttachmentBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	public void persist(
			ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {

		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();

		contractProductAONotesAttachmentBO.setCreatedTimestamp(dateSegment
				.getCreatedTimestamp());
		contractProductAONotesAttachmentBO.setLastUpdatedTimestamp(dateSegment
				.getLastUpdatedTimestamp());
		contractProductAONotesAttachmentBO.setLastUpdatedUser(dateSegment
				.getLastUpdatedUser());
		contractProductAONotesAttachmentBO.setCreatedUser(dateSegment
				.getCreatedUser());

		try {

			if (insertFlag) {
				notesAdapterManager.insertNotesAttachedToQuestion(
						contractProductAONotesAttachmentBO, audit);
			} else {
				notesAdapterManager.updateNotesAttachedToQuestion(
						contractProductAONotesAttachmentBO, audit);
			}
		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for updating the BusinessObject ContractProductAONotesAttachmentBO, in ContractBusinessObjectBuilder",
					errorParams, exception);

		}
	}

	/**
	 * Mehtod used to delete the attached notes in an Admin Option at contract
	 * level
	 * 
	 * @param contractProductAONotesAttachmentBO
	 * @param contract
	 * @param audit
	 * @throws SevereException
	 */
	public void delete(
			ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO,
			DateSegment dateSegment, Audit audit) throws SevereException {
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		try {
			notesAdapterManager.deleteNotesAttachedToQuestion(
					contractProductAONotesAttachmentBO, audit);
		} catch (AdapterException exception) {
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject ContractProductAONotesAttachmentBO, in ContractBusinessObjectBuilder",
					errorParams, exception);
		}

	}

	/**
	 * This method is to get the duplicate reference list of benefit level and
	 * question of a contract
	 * 
	 * @param dateSegment,lineCount,
	 *            questCount, dateRange
	 * @return allDuplicateRef(List)
	 * @throws SevereException
	 */
	public List getAllDuplicateReference(int dateSegment, int lineCount,
			int questCount, String dateRange, String productName)
			throws SevereException {
		List allDuplicateRef = new ArrayList();
		List allDuplicateBenefitRefList;
		List allDuplicateQuestRefList;
		List duplicateBenefitRefList = new ArrayList();
		List duplicateQuestRefList = new ArrayList();
		List emptyList = new ArrayList(0);
		allDuplicateRef.add(0, emptyList);
		allDuplicateRef.add(1, emptyList);
		ContractQuestUniqueReferenceBO contractDateSegmentsBO;
		try {
			if (lineCount < 501) {
				int count = 501 - lineCount;
				allDuplicateBenefitRefList = this.adapterManager
						.getDuplicateAllBenefitRef(dateSegment, count);
				if (null != allDuplicateBenefitRefList) {
					for (int index = 0; index < allDuplicateBenefitRefList
							.size(); index++) {
						contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) allDuplicateBenefitRefList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(productName);
						duplicateBenefitRefList.add(contractDateSegmentsBO);
					}
					allDuplicateRef.add(0, duplicateBenefitRefList);
				}
			}
			if (questCount < 501) {
				int count = 501 - questCount;
				allDuplicateQuestRefList = this.adapterManager
						.getDuplicateAllQuestionRef(dateSegment, count);
				if (null != allDuplicateQuestRefList) {
					for (int index = 0; index < allDuplicateQuestRefList.size(); index++) {
						contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) allDuplicateQuestRefList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(productName);
						duplicateQuestRefList.add(contractDateSegmentsBO);
					}
					allDuplicateRef.add(1, duplicateQuestRefList);
				}
			}
		} catch (WPDException ex) {

			throw new SevereException("Unhandled exception is caught", null, ex);
		}
		return allDuplicateRef;
	}

	/**
	 * This method is to get the duplicate reference list of benefit level and
	 * question of a contract
	 * 
	 * @param dateSegment,lineCount,
	 *            questCount, dateRange
	 * @return allDuplicateRef(List)
	 * @throws SevereException
	 */
	public List getAllUncodedNotes(int dateSegment, int lineCount,
			int questCount, int prdCount, String dateRange, String prodName)
			throws SevereException {

		List allNotes = new ArrayList();
		List uncodedBenefitNotesList = new ArrayList();
		List uncodedQuestNotesList = new ArrayList();
		List uncodedPrdQuestList = new ArrayList();

		List emptyList = new ArrayList(0);
		allNotes.add(0, emptyList);
		allNotes.add(1, emptyList);
		allNotes.add(2, emptyList);

		ContractQuestUniqueReferenceBO contractDateSegmentsBO;
		try {
			if (lineCount < 501) {
				int count = 501 - lineCount;
				uncodedBenefitNotesList = this.adapterManager
						.getUncodedLineNotes(dateSegment, count);
				if (null != uncodedBenefitNotesList) {
					for (int index = 0; index < uncodedBenefitNotesList.size(); index++) {
						contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) uncodedBenefitNotesList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(prodName);
					}
					allNotes.add(0, uncodedBenefitNotesList);
				}
			}
			if (questCount < 501) {
				int count = 501 - questCount;
				uncodedQuestNotesList = this.adapterManager
						.getUncodedQuestNotes(dateSegment, count);
				if (null != uncodedQuestNotesList) {
					for (int index = 0; index < uncodedQuestNotesList.size(); index++) {
						contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) uncodedQuestNotesList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(prodName);
					}
					allNotes.add(1, uncodedQuestNotesList);
				}
			}
			if (questCount < 501) {
				int count = 501 - prdCount;
				uncodedPrdQuestList = this.adapterManager
						.getUncodedPrdQuestNotes(dateSegment, count);
				if (null != uncodedPrdQuestList) {
					for (int index = 0; index < uncodedPrdQuestList.size(); index++) {
						contractDateSegmentsBO = (ContractQuestUniqueReferenceBO) uncodedPrdQuestList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(prodName);
					}
					allNotes.add(2, uncodedPrdQuestList);
				}
			}
		} catch (WPDException ex) {

			throw new SevereException("Unhandled exception is caught", null, ex);
		}
		return allNotes;
	}

	public List getAllUncodedTierNotes(int dateSegment, int lineCount,
			int questCount, String dateRange, String prodName)
			throws SevereException {

		List allNotes = new ArrayList();
		List uncodedBenefitNotesList = new ArrayList();
		List uncodedQuestNotesList = new ArrayList();
		List tierSysIdList = null;
		List emptyList = new ArrayList(0);
		allNotes.add(0, emptyList);
		List tierDetailList = null;

		TierDefinitionBO contractDateSegmentsBO;
		try {
			if (lineCount < 501) {
				int count = 501 - lineCount;
				uncodedBenefitNotesList = this.adapterManager
						.getUncodedLineNotesForTier(dateSegment, count);
				if (null != uncodedBenefitNotesList
						&& !uncodedBenefitNotesList.isEmpty()) {
					for (int index = 0; index < uncodedBenefitNotesList.size(); index++) {
						contractDateSegmentsBO = (TierDefinitionBO) uncodedBenefitNotesList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(prodName);
					}
					allNotes.add(0, uncodedBenefitNotesList);
					tierSysIdList = getTierSysId(uncodedBenefitNotesList);
					tierDetailList = this.adapterManager.getTierDetailList(
							tierSysIdList, dateSegment);
					allNotes.add(1, tierDetailList);
				} else {
					allNotes.add(0, uncodedBenefitNotesList);
					allNotes.add(1, tierDetailList);
				}
			}
			if (questCount < 501) {
				int count = 501 - questCount;
				uncodedQuestNotesList = this.adapterManager
						.getUncodedQuestNotesForTier(dateSegment, count);
				if (null != uncodedQuestNotesList
						&& !uncodedQuestNotesList.isEmpty()) {
					for (int index = 0; index < uncodedQuestNotesList.size(); index++) {
						contractDateSegmentsBO = (TierDefinitionBO) uncodedQuestNotesList
								.get(index);
						contractDateSegmentsBO.setDateRange(dateRange);
						contractDateSegmentsBO.setProductName(prodName);
					}
					allNotes.add(2, uncodedQuestNotesList);
					tierSysIdList = getTierSysId(uncodedQuestNotesList);
					tierDetailList = this.adapterManager.getTierDetailList(
							tierSysIdList, dateSegment);
					allNotes.add(3, tierDetailList);
				} else {
					allNotes.add(2, uncodedQuestNotesList);
					allNotes.add(3, tierDetailList);
				}
			}
		} catch (WPDException ex) {

			throw new SevereException("Unhandled exception is caught", null, ex);
		}
		return allNotes;
	}

	private List getTierSysId(List uncodedBenefitNotesList) {

		List tierSysIdList = new ArrayList();
		int listSize = uncodedBenefitNotesList.size();

		TierDefinitionBO tierDefinitionBO = (TierDefinitionBO) uncodedBenefitNotesList
				.get(0);
		int tierSysId = tierDefinitionBO.getTierSysId();
		tierSysIdList.add(new Integer(tierSysId));

		for (int i = 1; i < listSize; i++) {
			TierDefinitionBO definitionBO = (TierDefinitionBO) uncodedBenefitNotesList
					.get(i);
			if (!tierSysIdList
					.contains(new Integer(definitionBO.getTierSysId()))) {
				tierSysIdList.add(new Integer(definitionBO.getTierSysId()));
			}
		}
		return tierSysIdList;
	}

	/**
	 * Method to update the Contract audit information
	 * 
	 * @param dateSegment
	 * @param audit
	 * @param serviceAccess
	 * @throws SevereException
	 */
	private void updateContractAuditInfo(DateSegment dateSegment, Audit audit,
			AdapterServicesAccess serviceAccess) throws SevereException {

		ContractAuditInfo contractAuditInfo = new ContractAuditInfo();
		contractAuditInfo.setContractSysID(dateSegment.getContractSysId());
		contractAuditInfo.setContractID(dateSegment.getContractId());
		contractAuditInfo.setLastUpdatedTimestamp(audit.getTime());
		contractAuditInfo.setLastUpdatedUser(audit.getUser());
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		adapterManager.updateContractAduitInformation(contractAuditInfo,
				serviceAccess);
	}

	/**
	 * 
	 * @param contractBenefitTier
	 * @param dateSegment
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public boolean delete(BenefitTier contractBenefitTier,
			DateSegment dateSegment, Audit audit) throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete( BenefitTier contractBenefitTier, DateSegment dateSegment, Audit audit)");
			adapterManager.deleteBenefitTier(contractBenefitTier);
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete( BenefitTier contractBenefitTier, DateSegment dateSegment, Audit audit)");
		} catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete( BenefitTier contractBenefitTier, DateSegment dateSegment, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject BenefitTier, in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete( BenefitTier contractBenefitTier, DateSegment dateSegment, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject BenefitTier, in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete( BenefitTier contractBenefitTier, DateSegment dateSegment, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

	/**
	 * 
	 * @param DateSegment
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistTimeStamp(DateSegment dateSegment, Audit audit)
			throws SevereException {

		try {

			dateSegment.setLastUpdatedUser(audit.getUser());
			dateSegment.setLastUpdatedTimestamp(audit.getTime());

			adapterManager.updateDateSegment(dateSegment);

		} catch (AdapterException ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject DateSegment, in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

	/**
	 * Method to validate the notes attached to uncoded lines/unanswered
	 * questions
	 * 
	 * @param contract
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean validateNotes(Contract contract) throws SevereException {
		if (!adapterManager.validateProductLevelAONotes(contract)) {
			return false;
		} else if (!adapterManager.validateLineNotes(contract)) {
			return false;
		} else if (!adapterManager.validateNotes(contract)) {
			return false;
		} else if (!adapterManager.validateTieredLineNotes(contract)) {
			return false;
		}
		if (!adapterManager.validateUnansweredTieredQues(contract)) {
			return false;
		}
		return true;
	}

	/**
	 * Method to retrive the MemberSpec Indicator for contract datafeed.
	 * 
	 * @param dateSegmentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractMemberSpecIndicator(int dateSegmentSysId)
			throws SevereException {

		return adapterManager
				.locateContractMemberSpecIndicator(dateSegmentSysId);

	}

	/**
	 * Method to retrieve the Provider Speciality Codes for a contract
	 * datesgement
	 * 
	 * @param providerSpecialityCodeBO
	 * @return
	 * @throws SevereException
	 */
	public List getProviderSpecialityCodes(
			ProviderSpecialityCodeBO providerSpecialityCodeBO)
			throws SevereException {
		return adapterManager
				.getProviderSpecialityCodes(providerSpecialityCodeBO);
	}

	/**
	 * 
	 * @param productSysId
	 * @param benCompId
	 * @param defnId
	 * @param tierDefId
	 * @param levelId
	 * @param saveStr
	 * @param isExists
	 * @param audit
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public boolean addTierDefinitionToContract(int dateSegmentId,
			int benCompId, int defnId, int tierDefId, int levelId,
			String saveStr, String isExists, Audit audit)
			throws SevereException, AdapterException {
		return this.adapterManager.addTierToContract(dateSegmentId, benCompId,
				defnId, tierDefId, levelId, saveStr, isExists, audit);
	}

	/**
	 * Method to retrieve the tier information corresponding to a contract
	 * datesegment
	 * 
	 * @param dateSegmentSysId
	 * @param TierCriteriaVal
	 * @return
	 * @throws SevereException
	 */
	public List getContractTierInformation(int dateSegmentSysId, List tierCriteriaVal)
			throws SevereException {
		return adapterManager.getContractTierInformation(dateSegmentSysId, tierCriteriaVal);
	}

	public List retrieveQuestionTiernNotes(
			TierNotesAttachmentOverrideBO overrideBO) throws SevereException {
		List noteList = null;
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		noteList = notesAdapterManager.getQuestionTierNote(overrideBO);
		return noteList;

	}

	public List retrieveQuestionTiernNotesForView(
			TierNotesAttachmentOverrideBO overrideBO) throws SevereException {
		List noteList = null;
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		noteList = notesAdapterManager.getOverridedQuestionTierNote(overrideBO);
		if (null != noteList && noteList.size() > 0) {
			((TierNotesAttachmentOverrideBO) noteList.get(0))
					.setOverrideStatus("Y");
		}
		return noteList;

	}

	public boolean persist(TierNotesAttachmentOverrideBO attachmentBO,
			DateSegment dateSegment, Audit audit, boolean insertFlag)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		attachmentBO.setCreatedUser(audit.getUser());
		attachmentBO.setCreatedTimestamp(audit.getTime());
		attachmentBO.setLastUpdatedUser(audit.getUser());
		attachmentBO.setLastUpdatedTimestamp(audit.getTime());

		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(transactionId, this,
							"persist(TierNotesAttachmentOverrideBO,DateSegment, Audit,boolean)");

			if (insertFlag) {
				adapterManager.insertNotesAttachedToQuestion(attachmentBO,
						audit);
			} else {
				adapterManager.updateNotesAttachedToQuestion(attachmentBO,
						audit);
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(transactionId, this,
							"persist(TierNotesAttachmentOverrideBO,DateSegment, Audit,boolean)");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(transactionId, this,
							"persist(TierNotesAttachmentOverrideBO,DateSegment, Audit ,boolean)");
			throw new SevereException(
					"Persist for TierNotesAttachmentOverrideBO failed.Unknown exception is caught",
					null, ex);
		}
		return true;
	}

	/**
	 * @return
	 * @throws SevereException
	 */

	public boolean delete(TierNotesAttachmentOverrideBO attachmentBO,
			DateSegment dateSegment, Audit audit) throws SevereException,
			AdapterException {
		AdapterServicesAccess access = AdapterUtil.getAdapterService();
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			access.beginTransaction();
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"delete(TierNotesAttachmentOverrideBO subObject,DateSegment mainObject, Audit audit)");
			adapterManager.deleteNotesAttachedToQuestion(attachmentBO, audit);
			access.endTransaction();
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"delete(TierNotesAttachmentOverrideBO subObject,DateSegment mainObject, Audit audit)");
		} catch (Exception se) {
			access.abortTransaction();
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete(TierNotesAttachmentOverrideBO subObject,DateSegment mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method in ContracttBusinessObjectBuilder",
					errorParams, se);
		}
		return true;
	}

	/**
	 * Retreives the Benefit Rule details for Contract.
	 * 
	 * @param dateSegList
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List getRuleListForContract(List dateSegList)
			throws SevereException, AdapterException {

		ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
		List ruleList = new ArrayList();
		List bcRuleList = null;
		List benefitRuleList = null;

		List fullBcRuleList = new ArrayList();
		List fullBenefitRuleList = new ArrayList();
		ContractQuestUniqueReferenceBO dateSeg = null;
		int dsSysId = 0;
		String dateSegment = null;
		String productName = null;

		/*
		 * Retreiving the Benefit Component and Benefit Rule details for
		 * datesegments in th contract.
		 */
		for (int dsCount = 0; dsCount < dateSegList.size(); dsCount++) {
			dateSeg = new ContractQuestUniqueReferenceBO();
			dateSeg = (ContractQuestUniqueReferenceBO) dateSegList.get(dsCount);
			dsSysId = dateSeg.getContractdateSegmentSysId();
			productName = dateSeg.getProductName();
			dateSegment = WPDStringUtil.getStringDate(dateSeg.getStartDate())
					+ "-" + WPDStringUtil.getStringDate(dateSeg.getEndDate());

			//Retreives the Benefit Component rule details
			bcRuleList = contractAdapterManager.getRuleListBC(dsSysId);
			if (null != bcRuleList && !bcRuleList.isEmpty()) {
				for (int bcRuleCount = 0; bcRuleCount < bcRuleList.size(); bcRuleCount++) {

					RuleDetailBO ruleDetailBO = (RuleDetailBO) bcRuleList
							.get(bcRuleCount);
					ruleDetailBO.setDateSegment(dateSegment);
					ruleDetailBO.setProductName(productName);
				}
				fullBcRuleList.add(bcRuleList);
			}

			//			Retreives the Benefit rule details
			benefitRuleList = contractAdapterManager
					.getRuleListBenefit(dsSysId);
			if (null != benefitRuleList && !benefitRuleList.isEmpty()) {
				for (int bnftRuleCount = 0; bnftRuleCount < benefitRuleList
						.size(); bnftRuleCount++) {

					RuleDetailBO ruleDetailBO = (RuleDetailBO) benefitRuleList
							.get(bnftRuleCount);
					ruleDetailBO.setDateSegment(dateSegment);
					ruleDetailBO.setProductName(productName);
				}
				fullBenefitRuleList.add(benefitRuleList);
			}

		}

		if (null != fullBcRuleList && !fullBcRuleList.isEmpty()) {
			ruleList.add(0, fullBcRuleList);
		}
		if (null != fullBenefitRuleList && !fullBenefitRuleList.isEmpty()) {
			ruleList.add(1, fullBenefitRuleList);
		}
		return ruleList;
	}

	/**
	 * This method check the legacyNotes existancy by return count of
	 * legacyNotes.
	 * 
	 * @param dateSegmentId
	 * @return count of legacyNotes.
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public int checkLegacyNotesExists(int dateSegmentId)
			throws SevereException, AdapterException {
		return adapterManager.checkLegacyNotesExists(dateSegmentId);
	}

	/**
	 * @param contractId
	 * @param status
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateDeletedDatesegment(String contractId,
			String status) throws SevereException {
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(adapterManager.getDeletedDateSegments(
				contractId, status));
		return locateResults;
	}

	/**
	 * This function updates the datesegment association table with the flag as
	 * T or P for test and production respectively by retrieving the appropriate
	 * datsegments.
	 * 
	 * @param contract
	 * @param audit
	 * @throws SevereException
	 */
	public void updateDateSegmentAssnInfo(Contract contract, Audit audit)
			throws SevereException {

		//retrieve the valid scheduled datesegements which needs to be updated
		// for the contract
		DateSegment dateSegment = new DateSegment();
		dateSegment.setContractSysId(contract.getContractSysId());
		if (BusinessConstants.OBJECT_TRANSFERRED.equals(contract.getStatus()))
			dateSegment.setIsModified("Y");
		else if (BusinessConstants.MSG_PUBLISHED.equals(contract.getStatus()))
			dateSegment.setIsModified("T");
		dateSegment.setDateSegmentType(contract.getContractDataFeedIndicator());
		List dateSegmentList = adapterManager
				.getModifiedContractDatesesgments(dateSegment);

		//Iterating through the list of datesegments and updating the status in
		// the datesegemnt association table.
		if (null != dateSegmentList && !dateSegmentList.isEmpty()) {
			Iterator iterator = dateSegmentList.iterator();
			while (iterator.hasNext()) {
				DateSegment datesegment = (DateSegment) iterator.next();
				DateSegmentAssociationBO dateSegmentAssociationBO = new DateSegmentAssociationBO();
				dateSegmentAssociationBO.setContractSysId(contract
						.getContractSysId());
				dateSegmentAssociationBO.setDateSegmentSysId(datesegment
						.getDateSegmentSysId());
				dateSegmentAssociationBO.setLastUpdatedUser(audit.getUser());
				dateSegmentAssociationBO.setLastUpdatedTimeStamp(audit
						.getTime());
				if (BusinessConstants.OBJECT_TRANSFERRED.equals(contract
						.getStatus()))
					dateSegmentAssociationBO.setDatesegmentModified("T");
				else if (BusinessConstants.MSG_PUBLISHED.equals(contract
						.getStatus()))
					dateSegmentAssociationBO.setDatesegmentModified("P");

				adapterManager
						.updateDateSegmentAssnInfo(dateSegmentAssociationBO);

			}
		}
	}

	/**
	 * This method will update the status of DLTD_TEST_PROD_IND in the
	 * DLTD_DT_SGMNT table for test contracts and delete the record for the
	 * production contracts
	 * 
	 * @param deletedDSList
	 * @param status
	 * @param audit
	 * @throws SevereException
	 */
	public void updateDeleteDateSegmentInfo(List deletedDSList, String status,
			Audit audit) throws SevereException {
		if (null != deletedDSList && !deletedDSList.isEmpty()) {
			Iterator iterator = deletedDSList.iterator();
			while (iterator.hasNext()) {
				DeletedDSInfoBO deletedDS = (DeletedDSInfoBO) iterator.next();
				if (BusinessConstants.OBJECT_TRANSFERRED.equals(status)) {
					deletedDS.setDeletedTestProdIndicator("T");
					adapterManager.updateDeletedDateSegmentInfo(deletedDS,
							audit);
				} else if (BusinessConstants.MSG_PUBLISHED.equals(status)) {
					adapterManager.deleteDeletedDateSegmentInfo(deletedDS,
							audit);
				}
			}
		}

	}

	/**
	 * Method to retrieve the contracts for which all the contract versions are
	 * deleted
	 * 
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateRootDeleteContracts(String status)
			throws SevereException {
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(adapterManager
				.getRootDeleteContracts(status));
		return locateResults;
	}

	/**
	 * Method to retrieve the scheduled contracts whose previous versions were
	 * deleted.
	 * 
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateRootDeleteScheduledContracts(String contractId,
			String status) throws SevereException {
		LocateResults locateResults = new LocateResults();
		locateResults.setLocateResults(adapterManager
				.getRootDeleteScheduledContracts(contractId, status));
		return locateResults;
	}
	/**
	 * method to delete data in contract benefit affected sps table. 
	 * @param entityIds
	 * @param audit
	 * @throws SevereException
	 */
	public void deleteContractBenefitAffectedSPS(List entityIds, Audit audit) throws SevereException{
		adapterManager.deleteContractBenefitAffectedSPS(entityIds, audit);
	}
	
	public void saveNewlyCodedLinesForContractBC(
			ContractBenefitHeadings contractBenefitHeadings) throws SevereException {


		AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
			.logBeginTranstn(
					transactionId,
					this,
			"saveNewlyCodedLinesForContractBC(ContractBenefitHeadings contractBenefitHeadings)");
			BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
			benefitAdapterManager.saveNewlyCodedLinesForContractBC(
					contractBenefitHeadings, adapterServicesAccess);


			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
			.logTheEndTransaction(
					transactionId,
					this,
			"saveNewlyCodedLinesForContractBC(ContractBenefitHeadings contractBenefitHeadings)");
		}
		catch (SevereException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
			.logAbortTxn(
					transactionId,
					this,
			"saveNewlyCodedLinesForContractBC(ContractBenefitHeadings contractBenefitHeadings)");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception in saveNewlyCodedLinesForContractBC(ContractBenefitHeadings contractBenefitHeadings) in ContractBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
			.logAbortTxn(
					transactionId,
					this,
			"saveNewlyCodedLinesForContractBC(ContractBenefitHeadings contractBenefitHeadings)");
			throw new SevereException(null, null, ex);
		}
}
	public List getComponents(List components) throws SevereException {
        List result = new ContractAdapterManager().getComponents(components);
        List validComponents = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator
				.hasNext();) {
			ContractReportBean reportBean = (ContractReportBean) iterator.next();
			validComponents.add(reportBean.getBenefitComponent());
		}
		return validComponents;
	}
	
	public List getBenefits(List benefits) throws SevereException {
        List result = new ContractAdapterManager().getBenefits(benefits);
        List validBenefits = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator
				.hasNext();) {
			ContractReportBean reportBean = (ContractReportBean) iterator.next();
			validBenefits.add(reportBean.getBenefit());
		}
        
        return validBenefits;
	}
	
	public List getContracts(List contracts) throws SevereException {
		List result = new ContractAdapterManager().getContracts(contracts);
        List validContractCodes = new ArrayList();
        for (Iterator iterator = result.iterator(); iterator
				.hasNext();) {
			ContractReportBean reportBean = (ContractReportBean) iterator.next();
			validContractCodes.add(reportBean.getContractId());
		}
        return validContractCodes;
	}
	
	public List getContractReport(ContractReportCriteria reportCriteria) throws SevereException {
		return new ContractAdapterManager().getContractReport(reportCriteria);
	}
	
	public List getAccumQuestions(DateSegment dateSegment,AccumulatorValidationBO bnftDetailsBasedDSBo) throws SevereException{
		 List result = new ContractAdapterManager().getAccumQuestions(dateSegment,bnftDetailsBasedDSBo);
		 return result;
	}
	
	public List getCodedAccumQuestions(DateSegment dateSegment) throws SevereException{
		 List result = new ContractAdapterManager().getCodedAccumQuestions(dateSegment);
		 return result; 
	}

	public List getCodedTieredAccumQuestions(DateSegment dateSegment) throws SevereException{
		 List result = new ContractAdapterManager().getCodedTieredAccumQuestions(dateSegment);
		 return result; 
	}
	
	public List getCodedLines(AccumulatorValidationBO accumulatorValidationBO) throws SevereException{
		List result = new ContractAdapterManager().getCodedLines(accumulatorValidationBO);
		 return result;
	}
	
	public List isAdjudAccumQuestion(int questionNo) throws SevereException{
		List result = new ContractAdapterManager().isAdjudAccumQuestion(questionNo);
		 return result;
	}
	
	public List getImageRWDAFlagLst(String dateSegmentSysId) throws SevereException{
		List result = new ContractAdapterManager().getImageRWDAFlagLst(dateSegmentSysId);
		 return result;
	}
	
	
	public List getAccumBenefitAssociation(String questionNo,String benefitSysId) throws SevereException{
		List result = new ContractAdapterManager().getAccumBenefitAssociation(questionNo, benefitSysId);
		 return result;
	}
	
	public List getAccumBenefitAssociationForAdminOption(String adminOptionSysId,String benefitSysId) throws SevereException{
		List result = new ContractAdapterManager().getAccumBenefitAssociationForAdminOption(adminOptionSysId, benefitSysId);
		 return result;
	}
	
	public List getMappedBnftAccumAssnLst(AccumulatorValidationBO accumulatorValidationBO) throws SevereException{
		List result = new ContractAdapterManager().getMappedBnftAccumAssnLst(accumulatorValidationBO);
		 return result;
	}
	
	public List getAccumulatorSet(String possibleAnswer) throws SevereException{
		List result = new ContractAdapterManager().getAccumulatorSet(possibleAnswer);
		return result;
	}
	
	public List getStandardAccumulatorSet(AccumulatorValidationBO accumValidationBO,Contract contract, int questionNo,String BYCYAnswer)throws SevereException{
		
		List businessDomain = contract.getBusinessDomains();
		Domain domain = (Domain) businessDomain.get(0);

		List result = new ContractAdapterManager().getStandardAccumulatorSet(accumValidationBO,domain,questionNo,BYCYAnswer);
		return result;
	}
	
	public List getStdAccumulatorAdminOption(int adminOptSysId,int benefitSysId, String BYCYAnswer, List domainLst)throws SevereException{

		Domain domain = (Domain) domainLst.get(0);
		List result = new ContractAdapterManager().getStdAccumulatorAdminOption(adminOptSysId,benefitSysId,BYCYAnswer,domain);
		return result;
	}
	
	public List checkAdminOptionAnswered(AccumulatorValidationBO accumulatorValidationBO)throws SevereException {
		List result = new ContractAdapterManager().checkAdminOptionAnswered(accumulatorValidationBO);
		return result;
	}
	
	public List checkTierQstnAnswered(AccumulatorValidationBO accumulatorValidationBO)throws SevereException {
		List result = new ContractAdapterManager().checkTierQstnAnswered(accumulatorValidationBO);
		return result;
	}
	
	public List getAnsweredQuestionList(AccumulatorValidationBO accumulatorValidationBO)throws SevereException {
		List result = new ContractAdapterManager().getAnsweredQuestionList(accumulatorValidationBO);
		return result;
	}
	
	public List checkAnsweredInGenBenefits(AccumulatorValidationBO accumulatorValidationBO)throws SevereException {
		List result = new ContractAdapterManager().checkAnsweredInGenBenefits(accumulatorValidationBO);
		return result;
	}
	
	public List getAccumulatorSetFromGenBenefits(String questName){
		return null;
	}
	
	/**
	 * Method for getting the details of individual rule report
	 * @param ruleReportCriteria
	 * @return A list containing individual rule details
	 * @throws SevereException
	 */
	
	public List getIndividualRuleReport(RuleLocateCriteria ruleReportCriteria) throws SevereException {
		
		long indTotalTimeStart = System.currentTimeMillis();
		
		List resultList = new ArrayList();
		
		RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) ruleReportCriteria;
		
		if(WebConstants.EXCLUSION_RULE_DESC.equals(ruleLocateCriteria.getRuleTypeDescription()) 
				||	WebConstants.DENIAL_RULE_DESC.equals(ruleLocateCriteria.getRuleTypeDescription())
				||	WebConstants.UM_RULE_DESC.equals(ruleLocateCriteria.getRuleTypeDescription())) {
			
			resultList = new ContractAdapterManager().getIndividualRuleDetails(ruleLocateCriteria.getRuleId()); 
		}
		else if(WebConstants.HEADER_RULE_DESC.equals(ruleLocateCriteria.getRuleTypeDescription())){
			
			if(WebConstants.HEADER_RULE_ASSOCIATED_BC.equals(ruleLocateCriteria.getHeaderRuleAssociated())){
				
				resultList = new ContractAdapterManager().getIndividualRuleDetailsHeaderBnftCmpnt(ruleLocateCriteria.getRuleId());  
			}
			else if(WebConstants.HEADER_RULE_ASSOCIATED_BNFT.equals(ruleLocateCriteria.getHeaderRuleAssociated())){
				
				resultList = new ContractAdapterManager().getIndividualRuleDetailsHeaderBnft(ruleLocateCriteria.getRuleId(),ruleLocateCriteria.getBenefitComponentId()) ; 
			}
		}
		long indTotalTimeEnd = System.currentTimeMillis();
		
		Logger.logInfo("Total time taken for Individual Rule Query Execution :"+(indTotalTimeEnd-indTotalTimeStart));
		
		return resultList;
	}
	/**
	 * Method for returning the rule and group rule details
	 * @param ruleReportCriteria
	 * @return A map containing the rule and group rule details as lists.
	 * @throws SevereException
	 */
	public Map getRuleReport(RuleLocateCriteria ruleReportCriteria) throws SevereException {
		long startTime = System.currentTimeMillis();
		
		Map listMap = new LinkedHashMap();//Map which holds the rule list and group rule.
		Map gropuRuleIdMap = new LinkedHashMap();//Map which holds the group rule ids asscoaited to benefit and benefit component
		
		List resultList = new ArrayList(); //Main List which holds the final result
		List tempResultList = null; // Temp list which holds individual query result
		List groupRuleIds = new ArrayList();//List of group rule ids assocaited to exclusion rule,denial rule,um rule.
		List tempResultListWithGroup = null;// List holds the group rule details for UM rule,Denail Rule,Exclusion Rule
		List finalListWithGroup = new ArrayList();//Final list which holds the values from tempResultListWithGroup and tempResultListWithGroupForHeader
		List groupWithBnftandBnftCmpntList = new ArrayList();//List of benefit and benefit component associated to group rule
		List tempListwithoutHeader = new ArrayList();//List holds all the rule details in tempResultListWithGroup list except the header rules
		List tempListwithHeaderOnly = new ArrayList();//List holds the hedaer rules only
		
		RuleLocateCriteria ruleLocateCriteria = (RuleLocateCriteria) ruleReportCriteria;
		
		
		/**Exclusion/Denial/UM Rule Extract **/
		if((WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractExclusionChecked())
				|| (WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractDenialChecked())
				|| (WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractUMChecked())){
			
			long startTimeForRuleExtract = System.currentTimeMillis();
			
			List gnrtdRuleIDCondition = new ArrayList();	
			
			if((WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractExclusionChecked())){
				gnrtdRuleIDCondition.add("&");
			}
			
			if((WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractDenialChecked())){
				gnrtdRuleIDCondition.add("*");
			}
			
			if((WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractUMChecked())){
				gnrtdRuleIDCondition.add("#");
			}
			
			tempResultList = new ContractAdapterManager().getUmDenailAndExclusionDetailsForContract(ruleLocateCriteria.getDateSegmentId(),gnrtdRuleIDCondition); 
			/*-- for separating rules and group rules -- */
			List tempResultListNoGroup = new ArrayList();	
			
			for(Iterator iterator =tempResultList.iterator();iterator.hasNext();){
				RuleReportBO reportBO = (RuleReportBO)iterator.next();
				
				
				if(null != reportBO.getRuleGrpInd())
				if(null != reportBO.getRuleGrpInd() && reportBO.getRuleGrpInd().equals("N")){
					tempResultListNoGroup.add(reportBO);
				}else{
					Logger.logInfo("Group Rule Id----> "+" "+reportBO.getRuleID());
					BusinessUtil.populateGroupRuleList(reportBO.getRuleID(),groupRuleIds);					
				}			
			}
			resultList.addAll(tempResultListNoGroup);
			
			Logger.logInfo("Group Rule Ids for Exclsuion/Denial/UM:"+groupRuleIds.toString());
			long endTimeForRuleExtract = System.currentTimeMillis();
			Logger.logInfo("Total time taken for Exclsuion/Denial/UM Rule Query Execution :"+(endTimeForRuleExtract-startTimeForRuleExtract));
		}
		
		/** Header Rule Extract **/
		if((WebConstants.TRUE).equalsIgnoreCase(ruleLocateCriteria.getExtractHeaderChecked())){
			
			long startTimeForHeader = System.currentTimeMillis();
			
			tempResultList = new ContractAdapterManager().getHeaderRuleDetailsForContract(ruleLocateCriteria.getDateSegmentId());
			
			/*-- for separating rules and group rules -- */
			List tempResultListNoGroup = new ArrayList();			
			
			for(Iterator iterator =tempResultList.iterator();iterator.hasNext();){
				RuleReportBO reportBO = (RuleReportBO)iterator.next();

				
					
				
				if(null != reportBO.getRuleGrpInd() && reportBO.getRuleGrpInd().equals("N")){
					
					tempResultListNoGroup.add(reportBO);
				}else{
					if(null != reportBO.getRuleID()){
						BusinessUtil.populateGroupRuleList(reportBO.getRuleID(),groupRuleIds);
						/** Create a map with group rule id as key,and list of concatenated benefit and benefit component as value*/
						List benifitUtil = (ArrayList) gropuRuleIdMap.get(reportBO.getRuleID());
						if(null == benifitUtil){
							benifitUtil = new ArrayList();
							benifitUtil.add(reportBO.getBenefitComponent()+"`"+reportBO.getBenefit());
							gropuRuleIdMap.put(reportBO.getRuleID(),benifitUtil);
						}else{
							benifitUtil.add(reportBO.getBenefitComponent()+"`"+reportBO.getBenefit());
						}
					}
				}			
			}
			
			Logger.logInfo("Group Rule Ids for Header:"+groupRuleIds.toString());
			Logger.logInfo("Map for group rule ids:"+gropuRuleIdMap);
			
			/**----- Sort the list based on benefit component and benefit --------*/
			if(null != tempResultListNoGroup && !tempResultListNoGroup.isEmpty()){
				Collections.sort(tempResultListNoGroup, new RuleReportSort());
			}
			
			resultList.addAll(tempResultListNoGroup);
			
			long endTimeForHeader = System.currentTimeMillis();
			Logger.logInfo("Total time taken for Header Rule Query Execution :"+(endTimeForHeader-startTimeForHeader));
		}
		
		/*----- Fetching Rules Associated to a group for UM/Denial/Exclusion/Header Rules --------*/
		if(null != groupRuleIds && !groupRuleIds.isEmpty()){
			
			long startTimeGrpRule = System.currentTimeMillis();
			
			long endTimeGrpRule = System.currentTimeMillis();
			Logger.logInfo("Total time taken for Group Rule Query Execution :"+(endTimeGrpRule-startTimeGrpRule));
			tempResultListWithGroup = new ContractAdapterManager().getGroupRuleDetailsForContract(groupRuleIds);
			/**
			 * Iterate through the tempResultListWithGroup list to get a seperate list
			 * without header rules.
			 */
			for(Iterator iterator = tempResultListWithGroup.iterator();iterator.hasNext();){
				RuleReportBO reportBO = (RuleReportBO)iterator.next();
				if( null != reportBO.getRuleTypeDesc() && !reportBO.getRuleTypeDesc().equals(WebConstants.HEADER_RULE_DESC)){
					tempListwithoutHeader.add(reportBO);
				}else{
					tempListwithHeaderOnly.add(reportBO);//List contains HeaderRules only
				}
			}
			finalListWithGroup.addAll(tempListwithoutHeader);
		}
		
		/** ------ CODE FOR SETTING BENEFT AND BENEFIT COMPONENET NAME TO RULE OBJECT FOR HEADER RULE ------ **/
		if(null != tempListwithHeaderOnly && !tempListwithHeaderOnly.isEmpty()){
			for(Iterator iterator = tempListwithHeaderOnly.iterator();iterator.hasNext();){
				RuleReportBO reportBO = (RuleReportBO)iterator.next();
				if(reportBO.getRuleTypeDesc().equals(WebConstants.HEADER_RULE_DESC)){
					Set groupRuleIdMapKeySet = gropuRuleIdMap.keySet();
					Iterator groupRuleIdMapKeySetIterator = groupRuleIdMapKeySet.iterator();
					/**
					 * Iterate through the map to check whether the map key and group rule id key are equal.If its equal get the list 
					 * associated to the group rule from map.
					 */
					while(groupRuleIdMapKeySetIterator.hasNext()){
						
						Object groupRuleIdKey = groupRuleIdMapKeySetIterator.next();                
						String groupRuleId = groupRuleIdKey.toString();
						
						if(groupRuleId.equals(reportBO.getGrpRuleId())){
							List bnftAndbnftCmpnts = (ArrayList) gropuRuleIdMap.get(groupRuleIdKey);
							/**
							 * Iterate through the list of benefit and benefit component and set the values to the object 
							 * by creating a copy of the RuleReportBO object.
							 */
							for(Iterator bnftIterator = bnftAndbnftCmpnts.iterator();bnftIterator.hasNext();){
								
								RuleReportBO ruleReportBOCopy = (RuleReportBO)SerializationUtils.clone(reportBO);
								
								String bnftWithCmpnt = (String) bnftIterator.next();
								String array[] = bnftWithCmpnt.split("`");
								
								ruleReportBOCopy.setBenefitComponent(array[0]);
								if(null == array[1] || WebConstants.NULL.equals(array[1])){
									ruleReportBOCopy.setBenefit("");
								}
								else{
									ruleReportBOCopy.setBenefit(array[1]);
								}
								
								groupWithBnftandBnftCmpntList.add(ruleReportBOCopy);
							}
						}
					}
				}
			}
		}
		if(null != groupWithBnftandBnftCmpntList && !groupWithBnftandBnftCmpntList.isEmpty()){
			finalListWithGroup.addAll(groupWithBnftandBnftCmpntList);
		}
		
		//Logger.logInfo("List Size with  group: -->"+tempResultListWithGroup.size());
		long endTime = System.currentTimeMillis();
		Logger.logInfo("Total time taken for all query execution : "+(endTime-startTime));
		
		if(null!= resultList && !resultList.isEmpty()){
			listMap.put(WebConstants.RULES_LIST_KEY_NAME,resultList);
		}
		if(null!= finalListWithGroup && !finalListWithGroup.isEmpty()){
			listMap.put(WebConstants.GROUP_RULES_LIST_KEY_NAME,finalListWithGroup);
		}
		
		return listMap;
	}
	public List getIMAGEReadyBusinessDomains() throws SevereException{
		return new ContractAdapterManager().getIMAGEReadyBusinessDomains();
	}
}