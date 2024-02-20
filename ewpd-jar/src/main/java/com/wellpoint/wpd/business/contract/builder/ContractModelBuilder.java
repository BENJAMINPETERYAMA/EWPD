/*
 * ContractModelBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.wpd.business.contract.adapter.ContractModelAdapterManager;

import com.wellpoint.wpd.common.contract.model.AdminOption;
import com.wellpoint.wpd.common.contract.model.Benefit;
import com.wellpoint.wpd.common.contract.model.BenefitComponent;
import com.wellpoint.wpd.common.contract.model.BenefitLines;
import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.ContractModel;
import com.wellpoint.wpd.common.contract.model.MembershipInformation;
import com.wellpoint.wpd.common.contract.model.Note;
import com.wellpoint.wpd.common.contract.model.PricingInformation;
import com.wellpoint.wpd.common.contract.model.Product;
import com.wellpoint.wpd.common.contract.model.Question;
import com.wellpoint.wpd.common.contract.model.Rule;
import com.wellpoint.wpd.common.contract.model.SuperProcessSteps;
import com.wellpoint.wpd.common.contract.model.Tier;
import com.wellpoint.wpd.common.contract.model.TierCriteriaValue;
import com.wellpoint.wpd.common.contract.model.TierDefinition;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractModelBuilder {

	/**
	 * 
	 * Method to get the Contract infromation
	 * 
	 * 
	 * @param contractSysId
	 * @param effectiveDate
	 * @return
	 */
	public Contract getContract(int contractSysId, Date effectiveDate)
			throws SevereException {

		ContractModel contractModel = getIntialContractInformation(
				contractSysId, effectiveDate);

		if (contractModel == null)
			return null;
		Contract contract = new Contract();
		loadContractBasicAndSpecificInformation(contractModel, contract);

		loadContractDomain(contractModel, contract);
		loadContractMembership(contractModel, contract);
		loadContractAdapted(contractModel, contract);
		loadContractNotes(contractModel, contract);

		loadContractProduct(contractModel, contract);
		loadContractPricingInformation(contractModel, contract);
		loadContractAdminOption(contractModel, contract);
		loadContractRules(contractModel, contract);
		


		return contract;
	}

	/**
	 * 
	 * 
	 * Method to get the Contract Domain information
	 * 
	 * @param contractModel
	 * @param contract
	 */
	private void loadContractDomain(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractDomain(contractModel.getContractParentSysId());
		} catch (SevereException e) {
			throw e;
		}
		List lob = new ArrayList();
		List be = new ArrayList();
		List bg = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				lob.add(model.getLobNm());
				be.add(model.getBusinessEntityNm());
				bg.add(model.getBusinessGroupNm());
			}
		}

		contract.setLineOfBusiness(lob);
		contract.setBusinessEntity(be);
		contract.setBusinessGroup(bg);
	}

	/**
	 * 
	 * Method to get the Product domain information
	 * 
	 * 
	 * @param contractModel
	 * @param product
	 * @throws SevereException
	 */
	private void loadContractProductDomain(ContractModel contractModel,
			Product product) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractProductDomain(contractModel
							.getProductParentSysId());
		} catch (SevereException e) {
			throw e;
		}
		List lob = new ArrayList();
		List be = new ArrayList();
		List bg = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				lob.add(model.getLobNm());
				be.add(model.getBusinessEntityNm());
				bg.add(model.getBusinessGroupNm());
			}
		}

		product.setLineOfBusiness(lob);
		product.setBusinessEntity(be);
		product.setBusinessGroup(bg);
	}

	/**
	 * 
	 * Method to get the Benefit Component domain information
	 * 
	 * 
	 * @param contractModel
	 * @param benefitComponent
	 * @throws SevereException
	 */
	private void loadContractBenefitComponentDomain(
			ContractModel contractModel, BenefitComponent benefitComponent)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitComponentDomain(contractModel
							.getBenefitComponentParentSysId());
		} catch (SevereException e) {

			throw e;
		}
		List lob = new ArrayList();
		List be = new ArrayList();
		List bg = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				lob.add(model.getLobNm());
				be.add(model.getBusinessEntityNm());
				bg.add(model.getBusinessGroupNm());
			}
		}

		benefitComponent.setLineOfBusiness(lob);
		benefitComponent.setBusinessEntity(be);
		benefitComponent.setBusinessGroup(bg);
	}

	/**
	 * Method to get the Contract memebership information
	 * 
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractMembership(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractMembership(contractModel.getContractSysId());
		} catch (SevereException e) {

			throw e;

		}
		List list = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				MembershipInformation membershipInformation = new MembershipInformation();
				membershipInformation.setCaseNumberName(model.getCaseName()
						+ " " + model.getCaseNumber());
				membershipInformation.setCaseeffectiveCancelDate(model
						.getCaseeffectiveCancelDate());
				membershipInformation.setCaseHQState(model.getCaseHQState());
				membershipInformation.setFundingArrangement(model
						.getFundingArrangement());
				membershipInformation.setGroupeffectiveCancelDate(model
						.getGroupeffectiveCancelDate());
				membershipInformation.setGroupNumberName(model.getGroupNumber()
						+ " " + model.getGroupName());
				membershipInformation.setMbuCodeValue(model.getMbuCodeValue());
				membershipInformation.setRerateCodeValue(model
						.getRerateCodeValue());
				list.add(membershipInformation);
			}
		}
		contract.setMembershipInformations(list);

	}

	/**
	 *  
	 * Method to get the Contract Adapted information
	 * 
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractAdapted(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractAdapted(contractModel.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}

		if (searchResults != null && searchResults.size() > 0) {

			ContractModel model = (ContractModel) searchResults.get(0);
			contract.setRegulatoryAgency(model.getRegulatoryAgency());
			contract.setComplianceStatus(model.getComplianceStatus());
			contract.setProductNameCode(model.getProductNameCode());
			contract.setContractTermDate(model.getContractTermDate());
			contract.setMultiPlanIndicator(model.getMultiPlanIndicator());
			contract.setPerformanceGuarentee(model.getPerformanceGuarentee());
			contract.setSalesMarketDate(WPDStringUtil.getStringDateFormat(model
					.getSalesMarketDate()));
		}
	}

	/**
	 * 
	 * Method to get the Contract Notes information
	 * 
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractNotes(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractNotes(contractModel.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}

		Note note = null;

		if (searchResults != null && searchResults.size() > 0) {
			ContractModel model = (ContractModel) searchResults.get(0);
			note = new Note();
			note.setNoteId(model.getNoteId());
			note.setNoteName(model.getNoteName());
		}
		contract.setNote(note);

	}

	/**
	 * Method to get the Pricing information
	 * 
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractPricingInformation(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractPricingInformation(contractModel
							.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}
		List pricing = new ArrayList();
		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				PricingInformation information = new PricingInformation();
				information.setCoverage(model.getCoverage());
				information.setNetwork(model.getNetwork());
				information.setPricing(model.getPricing());
				pricing.add(information);
			}
		}
		contract.setPricingInformation(pricing);
	}

	/**
	 * 
	 * Method to get the Contract Admin Option
	 * 
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractAdminOption(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractAdminOptions(contractModel.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}
		List contractAdminOptions = new ArrayList();
		Map aoMap = new HashMap();
		Map quesMap = new HashMap();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				if (aoMap.get(new Integer(model.getAdminOptionId())) == null) {
					aoMap
							.put(new Integer(model.getAdminOptionId()),
									model.getAdminOptionName());
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					List quesList = new ArrayList();
					quesList.add(question);
					quesMap
							.put(new Integer(model.getAdminOptionId()),
									quesList);
				} else {
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					List qList = (List) quesMap.get(new Integer(model
							.getAdminOptionId()));
					qList.add(question);
				}
			}
		}
		Set set = aoMap.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			AdminOption adminOption = new AdminOption();
			adminOption.setAdminOptionName((String) aoMap.get(integer));
			adminOption.setAdminLevel("contract");
			adminOption.setQuestions((List) quesMap.get(integer));
			contractAdminOptions.add(adminOption);
		}
		contract.setAdminOptions(contractAdminOptions);
	}

	/**
	 * 
	 * Method to get the Contract Rule information
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractRules(ContractModel contractModel,
			Contract contract) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractRules(contractModel.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}
		List rules = new ArrayList();
		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				Rule rule = new Rule();
				rule.setDescription(model.getDescription());
//				rule.setEwpdRuleId(model.getEwpdRuleId());
				rule.setRuleId(model.getRuleId());
				rule.setRuleType(model.getRuleType());
				rule.setGroupIndicator(model.getGroupIndicator());
				rule.setPva(model.getPva());
				rule.setValue(model.getValue());
				rules.add(rule);
			}
		}
		contract.setRules(rules);
	}

	/**
	 * 
	 * Method to get the Contract initial information
	 * 
	 * 
	 * @param contractSysId
	 * @param effectiveDate
	 * @return
	 * @throws SevereException
	 */
	private ContractModel getIntialContractInformation(int contractSysId,
			Date effectiveDate) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractIntialInformation(contractSysId, effectiveDate);
		} catch (SevereException e) {

			throw e;
		}
		if (searchResults != null && searchResults.size() > 0)
			return (ContractModel) searchResults.get(0);
		return null;

	}

	/**
	 * 
	 * Method to get the Contract basic and specific information
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractBasicAndSpecificInformation(
			ContractModel contractModel, Contract contract)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBasicAndSpecificInfomation(contractModel
							.getDateSegmentId());
		} catch (SevereException e) {

			throw e;
		}

		if (searchResults != null && searchResults.size() > 0) {
			ContractModel model = (ContractModel) searchResults.get(0);
			contract.setContractId(model.getContractId());
			contract.setContractType(model.getContractType());
			contract.setBaseContractId(model.getBaseContractId());
			contract.setBaseContractEffectiveDate(model
					.getBaseContractEffectiveDate());
			if (model.getEffectiveDate() != null
					&& model.getEffectiveDate().indexOf("0:0:0") != -1)
				model.setEffectiveDate(model.getEffectiveDate().substring(0,
						model.getEffectiveDate().indexOf("0:0:0") - 1));
			contract.setEffectiveDate(WPDStringUtil.getDateFromString(model
					.getEffectiveDate()));
			if (model.getExpiryDate() != null
					&& model.getExpiryDate().indexOf("0:0:0") != -1)
				model.setExpiryDate(model.getExpiryDate().substring(0,
						model.getExpiryDate().indexOf("0:0:0") - 1));
			contract.setExpiryDate(WPDStringUtil.getDateFromString(model
					.getExpiryDate()));

			contract.setProductCode(model.getProductCode());
			contract.setStandardPlanCode(model.getStandardPlanCode());
			contract.setBenefitPlan(model.getBenefitPlan());
			contract.setProductFamily(model.getProductFamily());
			contract.setCorporatePlanCode(model.getCorporatePlanCode());
			contract.setBrandName(model.getBrandName());

			contract.setCobAdjudicationIndcator(model
					.getCobAdjudicationIndcator());
			contract.setMedicareAdjudicationIndicator(model
					.getMedicareAdjudicationIndicator());
			contract.setItsAdjudicationIndicator(model
					.getItsAdjudicationIndicator());
		}

	}

	/**
	 * 
	 * Method to intiate the load of Product
	 * 
	 * @param contractModel
	 * @param contract
	 * @throws SevereException
	 */
	private void loadContractProduct(ContractModel contractModel,
			Contract contract) throws SevereException {
		contract.setProduct(getProduct(contractModel, contract));
	}

	/**
	 * 
	 * Method to get the Product
	 * 
	 * @param contractModel
	 * @param contract
	 * @return
	 * @throws SevereException
	 */
	private Product getProduct(ContractModel contractModel, Contract contract)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractProductInfo(contractModel.getProdId());
		} catch (SevereException e) {

			throw e;
		}
		Product product = new Product();
		if (searchResults != null && searchResults.size() > 0) {
			ContractModel model = (ContractModel) searchResults.get(0);
			product.setProductName(model.getProductName());
			product.setProductFamily(model.getProductFamily());
			product.setProductType(model.getProductType());
			product.setProductStructureName(model.getProductStructureName());
			product.setProductStructureVersion(model
					.getProductStructureVersion());
			product.setProductStructureDescription(model
					.getProductStructureDescription());
			if (model.getEffectiveDate() != null
					&& model.getEffectiveDate().indexOf("0:0:0") != -1)
				model.setEffectiveDate(model.getEffectiveDate().substring(0,
						model.getEffectiveDate().indexOf("0:0:0") - 1));
			product.setEffectiveDate(WPDStringUtil.getDateFromString(model
					.getEffectiveDate()));
			if (model.getExpiryDate() != null
					&& model.getExpiryDate().indexOf("0:0:0") != -1)
				model.setExpiryDate(model.getExpiryDate().substring(0,
						model.getExpiryDate().indexOf("0:0:0") - 1));
			product.setExpiryDate(WPDStringUtil.getDateFromString(model
					.getExpiryDate()));
			product.setVersion(model.getVersion());
			product.setBenefitComponents(getBenefitComponents(contractModel,
					product));
			contractModel.setProdId(model.getProdId());
			contractModel.setProductParentSysId(model.getProductParentSysId());
			loadContractProductDomain(contractModel, product);
		}

		return product;
	}

	/**
	 * 
	 * Method to get the Benefit Components
	 * 
	 * @param contractModel
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	private List getBenefitComponents(ContractModel contractModel,
			Product product) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitComponentInfo(contractModel
							.getDateSegmentId(), contractModel.getProdId());
		} catch (SevereException e) {

			throw e;
		}
		List list = new ArrayList();
		if (searchResults != null && searchResults.size() > 0) {

			for (int i = 0; i < searchResults.size(); i++) {
				BenefitComponent benefitComponent = new BenefitComponent();
				ContractModel model = (ContractModel) searchResults.get(i);
				benefitComponent.setBenefitComponentName(model
						.getBenefitComponentName());
				benefitComponent.setBenefitComponentType(model
						.getBenefitComponentType());
				benefitComponent.setBenefitComponentDescription(model
						.getBenefitComponentDescription());
				benefitComponent.setBenefitRuleId(model.getRuleId());
				if (model.getEffectiveDate() != null
						&& model.getEffectiveDate().indexOf("0:0:0") != -1)
					model.setEffectiveDate(model.getEffectiveDate().substring(
							0, model.getEffectiveDate().indexOf("0:0:0") - 1));
				benefitComponent.setEffectiveDate(model.getEffectiveDate());
				if (model.getExpiryDate() != null
						&& model.getExpiryDate().indexOf("0:0:0") != -1)
					model.setExpiryDate(model.getExpiryDate().substring(0,
							model.getExpiryDate().indexOf("0:0:0") - 1));
				benefitComponent.setExpiryDate(model.getExpiryDate());
				benefitComponent.setVersion("" + model.getVersion());
				contractModel.setBenefitComponentId(model
						.getBenefitComponentId());
				contractModel.setBenefitComponentParentSysId(model
						.getBenefitComponentParentSysId());
				benefitComponent.setBenefits(getBenefits(contractModel,
						benefitComponent));
				loadContractBenefitComponentDomain(contractModel,
						benefitComponent);
				loadContractBenefitComponentNotes(contractModel,
						benefitComponent);
				list.add(benefitComponent);
			}

		}

		return list;
	}

	/**
	 * 
	 * Method to get the Contract Benefit Component Notes
	 * 
	 * 
	 * @param contractModel
	 * @param benefitComponent
	 * @throws SevereException
	 */
	private void loadContractBenefitComponentNotes(ContractModel contractModel,
			BenefitComponent benefitComponent) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitComponentNotes(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId());
		} catch (SevereException e) {

			throw e;
		}

		List notes = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				Note note = new Note();
				note.setNoteId(model.getNoteId());
				note.setNoteName(model.getNoteName());
				notes.add(note);
			}
		}
		benefitComponent.setNotes(notes);

	}

	/**
	 * 
	 * Method to get the Benefit Notes
	 * 
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractBenefitNotes(ContractModel contractModel,
			Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitNotes(contractModel.getDateSegmentId(),
							contractModel.getBenefitComponentId(),
							contractModel.getBenefitSysId());
		} catch (SevereException e) {

			throw e;
		}

		List notes = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				Note note = new Note();
				note.setNoteId(model.getNoteId());
				note.setNoteName(model.getNoteName());
				notes.add(note);
			}
		}
		benefit.setNotes(notes);

	}

	/**
	 * 
	 * Method to get the Benefits
	 * 
	 * @param contractModel
	 * @param benefitComponent
	 * @return
	 * @throws SevereException
	 */
	private List getBenefits(ContractModel contractModel,
			BenefitComponent benefitComponent) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager.getContractBenefits(
					contractModel.getDateSegmentId(), contractModel
							.getBenefitComponentId());
		} catch (SevereException e) {

			throw e;
		}
		List list = new ArrayList();
		if (searchResults != null && searchResults.size() > 0) {

			for (int i = 0; i < searchResults.size(); i++) {
				Benefit benefit = new Benefit();
				ContractModel model = (ContractModel) searchResults.get(i);
				benefit.setBenefitName(model.getBenefitName());
				benefit.setDescription(model.getDescription());
				benefit.setBenefitCategory(model.getBenefitCategory());
				benefit.setBenefitType(model.getBenefitType());
				benefit.setVersion(model.getVersion());
				contractModel.setBenefitSysId(model.getBenefitSysId());
				contractModel
						.setBenefitAdminSysid(model.getBenefitAdminSysid());
				contractModel.setBenefitParentSysId(model
						.getBenefitParentSysId());
				loadContractbenefitDomains(contractModel, benefit);
				loadContractBenefitRuleDetails(contractModel, benefit);
				loadContractbenefitDetails(contractModel, benefit);
				loadContractBenefitLines(contractModel, benefit);
				loadContractBenefitAdminOptionsInfo(contractModel, benefit);
				loadContractBenefitAdminMethodsInfo(contractModel, benefit);
				loadContractBenefitNotes(contractModel, benefit);
				
				loadContractTiers(contractModel, benefit);
				
				list.add(benefit);

			}

		}

		return list;

	}

	/**
	 * 
	 * 
	 * Method to get the Contract benefit domains
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractbenefitDomains(ContractModel contractModel,
			Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitDomainDetails(contractModel
							.getBenefitParentSysId());
		} catch (SevereException e) {

			throw e;
		}

		List lob = new ArrayList();
		List be = new ArrayList();
		List bg = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				lob.add(model.getLobNm());
				be.add(model.getBusinessEntityNm());
				bg.add(model.getBusinessGroupNm());
			}
		}
		benefit.setLineOfBusiness(lob);
		benefit.setBusinessEntity(be);
		benefit.setBusinessGroup(bg);
	}

	/**
	 * 
	 * Method to get the Benefit rules
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractBenefitRuleDetails(ContractModel contractModel,
			Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitRuleDetails(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitSysId());
		} catch (SevereException e) {

			throw e;
		}

		if (searchResults != null && searchResults.size() > 0) {

			ContractModel model = (ContractModel) searchResults.get(0);
			benefit.setBenefitMeaning(model.getBenefitMeaning());
			benefit.setBenefitRuleId(model.getRuleDesc());
		}

	}

	/**
	 * 
	 * method to get the Contract Benefit Details
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractbenefitDetails(ContractModel contractModel,
			Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitDetails(contractModel.getBenefitSysId());
		} catch (SevereException e) {

			throw e;
		}
		List term = new ArrayList();
		List qual = new ArrayList();
		List pva = new ArrayList();
		List dt = new ArrayList();
		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				if (model.getCatalogType().equalsIgnoreCase("TERM")) {
					term.add(model.getCatalogDesc());
				} else if (model.getCatalogType().equalsIgnoreCase("QUALIFIER")) {
					qual.add(model.getCatalogDesc());
				} else if (model.getCatalogType().equalsIgnoreCase(
						"PROVIDER ARRANGEMENT")) {
					pva.add(model.getCatalogDesc());
				} else if (model.getCatalogType().equalsIgnoreCase("DATA TYPE")) {
					dt.add(model.getCatalogDesc());
				}
			}
		}
		benefit.setTerm(term);
		benefit.setQualifier(qual);
		benefit.setPva(pva);
		benefit.setDataType(dt);

	}

	/**
	 * 
	 * Method to Benefit Lines
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractBenefitLines(ContractModel contractModel,
			Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitLineInfo(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitSysId());
		} catch (SevereException e) {

			throw e;
		}

		List benefitLines = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				BenefitLines lines = new BenefitLines();
				lines.setTerm(model.getTerm());
				lines.setQualifier(model.getQualifier());
				lines.setPva(model.getPva());
				lines.setFormat(model.getFormat());
				lines.setBenefitValue(model.getBenefitValue());
				lines.setBenefitLevelDescription(model
						.getBenefitLevelDescription());
				lines.setReference(model.getReference());
				lines.setLevelId(model.getBenefitLevelId());
				Note note = new Note();
				note.setNoteId(model.getNoteId());
				note.setNoteName(model.getNoteName());
				lines.setNote(note);
				benefitLines.add(lines);
			}
		}
		benefit.setBenefitLines(benefitLines);

	}

	/**
	 * 
	 * Method to get Admin Options
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractBenefitAdminOptionsInfo(
			ContractModel contractModel, Benefit benefit)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitAdminOptionsInfo(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitAdminSysid());
		} catch (SevereException e) {
			
			throw e;
		}
		List benefitAdminOptions = new ArrayList();
		Map aoMap = new HashMap();
		Map aoNameMap = new HashMap();
		Map quesMap = new HashMap();
		Note note;

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				if (aoMap.get(new Integer(model.getAdminOptionId())) == null) {
					aoMap.put(new Integer(model.getAdminOptionId()), model
							.getAdminOptionLevel());
					if (aoNameMap.get(new Integer(model.getAdminOptionId())) == null)
						aoNameMap.put(new Integer(model.getAdminOptionId()),
								model.getAdminOptionName());						
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					
					note = new Note();
					note.setNoteId(model.getNoteId());
					note.setNoteName(model.getNoteName());
					question.setNote(note);
					
					List quesList = new ArrayList();
					quesList.add(question);
					quesMap
							.put(new Integer(model.getAdminOptionId()),
									quesList);
				} else {
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					
					note = new Note();
					note.setNoteId(model.getNoteId());
					note.setNoteName(model.getNoteName());
					question.setNote(note);
					
					List qList = (List) quesMap.get(new Integer(model
							.getAdminOptionId()));
					qList.add(question);
				}
			}
		}
		Set set = aoMap.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			AdminOption adminOption = new AdminOption();
			adminOption.setAdminLevel((String) aoMap.get(integer));
			adminOption.setQuestions((List) quesMap.get(integer));
			adminOption.setAdminOptionName((String) aoNameMap.get(integer));
			benefitAdminOptions.add(adminOption);
		}
		benefit.setAdminOptions(benefitAdminOptions);

	}

	/**
	 * 
	 * 
	 * Method to get the Contract Admin Methods
	 * 
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	private void loadContractBenefitAdminMethodsInfo(
			ContractModel contractModel, Benefit benefit)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitAdminMethodsInfo(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitAdminSysid());
		} catch (SevereException e) {

			throw e;
		}

		List adminMethods = new ArrayList();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				SuperProcessSteps processSteps = new SuperProcessSteps();
				processSteps.setSuperProcessStep(model.getSpsName());
				processSteps.setAdminMethod(model.getAdminMethodName());
				processSteps.setReference(model.getReference());
				adminMethods.add(processSteps);
			}
		}

		benefit.setSuperProcessSteps(adminMethods);

	}
	
	/**
	 * Method to load contract tier info
	 * @param contractModel
	 * @param benefit
	 * @throws SevereException
	 */
	
	public void loadContractTiers(ContractModel contractModel,
			 Benefit benefit) throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractTiers(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitSysId());
		} catch (SevereException e) {

			throw e;
		}
		
		benefit.setTiers(new ArrayList());
		ContractModel tierData;
		TierDefinition tierDefinition=null;
		Tier tier =null;
		int lastTierId=-1;
		TierCriteriaValue criteriaValue;
		Iterator tierItr = searchResults.iterator();
		while (tierItr.hasNext()){
			tierData =(ContractModel) tierItr.next();
			if (tierDefinition == null || ! (tierDefinition.getCode().equals(tierData.getTierCode()))){
				tierDefinition=new TierDefinition();
				tierDefinition.setTiers(new ArrayList());
				benefit.getTiers().add(tierDefinition);
				
				tierDefinition.setCode(tierData.getTierCode());
				tierDefinition.setDataType(tierData.getTierDataType());
			}
			if(tier == null || tierData.getTierSysId()!= lastTierId){
				tier= new Tier();
				tier.setTierCriteriaValues(new ArrayList());
				tier.setBenefitLines(new ArrayList());
				tier.setAdminOptions(new ArrayList());
				
				tier.setId(tierData.getTierSysId());
				tierDefinition.getTiers().add(tier);
				lastTierId=tierData.getTierSysId();
				
				loadTierBenefitLines(contractModel,tier);
				loadContractBenefitTierAdminOptionsInfo(contractModel,tier);
				
			}
			criteriaValue = new TierCriteriaValue();
			criteriaValue.setCriteriaName(tierData.getCriteriaName());
			criteriaValue.setValue(tierData.getCriteriaValue());
			criteriaValue.setDisplaySequenceNumber(tierData.getDisplaySequenceNumber());
			
			tier.getTierCriteriaValues().add(criteriaValue);

		}
	}
	
	private void loadTierBenefitLines(ContractModel contractModel, Tier tier) throws SevereException{
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractTierBenefitLines(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitSysId(),
							tier.getId());
		} catch (SevereException e) {

			throw e;
		}
		
		List benefitLines = new ArrayList();

		Iterator lineItr = searchResults.iterator();
		while (lineItr.hasNext()){
				ContractModel model = (ContractModel) lineItr.next();
				BenefitLines lines = new BenefitLines();
				lines.setTerm(model.getTerm());
				lines.setQualifier(model.getQualifier());
				lines.setPva(model.getPva());
				lines.setFormat(model.getFormat());
				lines.setBenefitValue(model.getBenefitValue());
				lines.setBenefitLevelDescription(model
						.getBenefitLevelDescription());
				lines.setReference(model.getReference());
				Note note = new Note();
				note.setNoteId(model.getNoteId());
				note.setNoteName(model.getNoteName());
				lines.setNote(note);
				tier.getBenefitLines().add(lines);
		}
		
	}

	private void loadContractBenefitTierAdminOptionsInfo(
			ContractModel contractModel, Tier tier)
			throws SevereException {
		ContractModelAdapterManager contractModelAdapterManager = new ContractModelAdapterManager();
		List searchResults = null;
		try {
			searchResults = contractModelAdapterManager
					.getContractBenefitTierAdminOptionsInfo(contractModel
							.getDateSegmentId(), contractModel
							.getBenefitComponentId(), contractModel
							.getBenefitAdminSysid(),tier.getId());
		} catch (SevereException e) {

			throw e;
		}
		Map aoMap = new HashMap();
		Map quesMap = new HashMap();

		if (searchResults != null && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				ContractModel model = (ContractModel) searchResults.get(i);
				if (aoMap.get(new Integer(model.getAdminOptionId())) == null) {
					aoMap.put(new Integer(model.getAdminOptionId()), model
							.getAdminOptionLevel());
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					List quesList = new ArrayList();
					quesList.add(question);
					quesMap
							.put(new Integer(model.getAdminOptionId()),
									quesList);
				} else {
					Question question = new Question();
					question.setQuestion(model.getQuestion());
					question.setAnswer(model.getAnswer());
					question.setReference(model.getReference());
					question.setPva(model.getPva());
					List qList = (List) quesMap.get(new Integer(model
							.getAdminOptionId()));
					qList.add(question);
				}
			}
		}
		Set set = aoMap.keySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			AdminOption adminOption = new AdminOption();
			adminOption.setAdminLevel((String) aoMap.get(integer));
			adminOption.setQuestions((List) quesMap.get(integer));
			
			tier.getAdminOptions().add(adminOption);
		}
	}

	
}