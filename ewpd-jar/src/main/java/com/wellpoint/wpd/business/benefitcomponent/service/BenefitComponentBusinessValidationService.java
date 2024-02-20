/*
 * BenefitComponentBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentBusinessObjectBuilder;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentDeleteLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitHierarchyLocateCriteria;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.app.config.ApplicationConfigManager;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCheckInRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCopyRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentDeleteRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentSaveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentScheduleForTestRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyCreateRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCopyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSaveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentScheduleForTestResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Validation service class for benefit component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBusinessValidationService extends WPDService {
	List messageList = null;

	/**
	 * 
	 * Overrided execute method of WPDService. Do the Business processing
	 * corresponding to request and returns response.
	 * 
	 * @param request
	 *            WPDRequest.
	 * @return WPDResponse WPDRespnose.
	 * @throws ServiceException
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		return null;
	}

	/**
	 * Validation method for CreateAdminOptionRequest.
	 * 
	 * @param request
	 *            CreateAdminOptionRequest
	 * @return WPDResponse CreateAdminOptionResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			BenefitComponentSaveRequest benefitComponentSaveRequest)
			throws ServiceException {
		BenefitComponentSaveResponse benefitComponentSaveResponse = null;
		messageList = new ArrayList(8);
		BenefitComponentVO benefitComponentVO = benefitComponentSaveRequest
				.getBenefitComponentVO();
		BenefitComponentBO benefitComponentBO = this
				.getBenefitComponentBusinessObject(benefitComponentVO);
		benefitComponentSaveResponse = (BenefitComponentSaveResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_BENEFIT_COMPONENT_RESPONSE);
		boolean componentValid = true;
		boolean checkinOperation = benefitComponentSaveRequest.isCheckInFlag() || benefitComponentSaveRequest.isDoneFlag();
		try {

			// Check whether benfit component exist with same name for same domain
			if (isBenefitComponentDuplicate(benefitComponentBO)) {
				messageList.add(new ErrorMessage(BusinessConstants.BENEFIT_COMP_ALREADY_EXISTS));
				componentValid = false;
			} 

			
			if (componentValid) {
				// Check whether bnefit exist with same name.
				if (!isUniqueBenefitComponentName(benefitComponentBO)) {
					messageList.add(new ErrorMessage(BusinessConstants.BENEFIT_HAVING_SAME_BNFT_COMP_NAME));
					componentValid = false;
					//benefitComponentSaveResponse.setSuccessFlagForSave(true);
					benefitComponentSaveResponse.setMessages(messageList);
				} 
				benefitComponentSaveResponse.setSuccessFlagForSave(componentValid);
			}
			
			
			// Checkin Validations
			if (checkinOperation && componentValid ) {
				
				// Checks if associated rule is valid, if attached to GB,GP & SB
				if((WebConstants.GEN_BENEFITS.equals(benefitComponentBO.getName().trim())) ||
						(WebConstants.GEN_PROVISIONS.equals(benefitComponentBO.getName().trim())) ||
						(WebConstants.SUPPLEMENTAL_BENEFITS.equals(benefitComponentBO.getName().trim()))){
					if(null!= benefitComponentBO.getRuleList() && benefitComponentBO.getRuleList().size()>0){
						if (isValidrule(benefitComponentBO)) {
							messageList.add(new ErrorMessage(
									BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
							componentValid = false;
						}
					}
				}
				//Validating rule for other Benefit Components
				else{
					if (isValidrule(benefitComponentBO)) {
						messageList.add(new ErrorMessage(
								BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
						componentValid = false;
					}
				}

				boolean flag = ApplicationConfigManager.isDuplicateBenefitCheckWaived(benefitComponentBO);
				// flag = true check means the benefit component contains ESI business entity hence 
				// validation of duplicate benefits needs to be bypassed
				if(!flag){
					// Validation to check component exist with same benefits and sequence.				
					if (!isValidBenefitComponent(benefitComponentBO,
							benefitComponentSaveRequest.getUser())) {
						componentValid = false;
						messageList.add(new ErrorMessage(BusinessConstants.BENEFIT_COMP_DUPLICATE));
					}
				}		
				// Some validations !!
				if (!isBenefitComponentValidated(benefitComponentBO,
						benefitComponentSaveRequest.getUser(),0)) {
					componentValid = false;
				}
				
				// Checks if at least one benefit is visible.
				if (!isAllLevelHiddenForComponent(
								benefitComponentBO,
								benefitComponentSaveRequest.getUser())) {
					messageList.add(new ErrorMessage(
							BusinessConstants.MSG_ATLEAST_ONE_LEVEL));
					componentValid = false;
				}
				
				if(!this.isValidBenefitPerDomain(benefitComponentBO)){
//        			ErrorMessage errorMessage = new ErrorMessage("benefit.hierarchy.invalid.benefit.change");
//        			messageList.add(errorMessage);
					componentValid = false;
				}
			}
			benefitComponentSaveResponse.setSuccessFlag(componentValid);
			benefitComponentSaveResponse.setMessages(messageList);
			benefitComponentSaveResponse
					.setBenefitComponentBO(benefitComponentBO);
		} catch (WPDException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(benefitComponentSaveRequest);
			String logMessage = "Failed while processing BenefitComponentSaveRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		benefitComponentSaveResponse.setMessages(messageList);
		benefitComponentSaveResponse.setBenefitComponentBO(benefitComponentBO);
		return benefitComponentSaveResponse;
	}

	/**
	 * Method to validate is Benefit Hierarchy has Benefit
	 * 
	 * @param benefitComponentBO
	 * @param user
	 * @return boolean
	 * @throws WPDException
	 */
	private boolean isBenefitComponentValidated(
			BenefitComponentBO benefitComponentBO, User user,int newBenefitListSize)
			throws WPDException {
//		messageList = new ArrayList();
		BenefitHierarchyLocateCriteria criteria = new BenefitHierarchyLocateCriteria();
		criteria.setBenefitComponentId(benefitComponentBO
				.getBenefitComponentId());
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		LocateResults locateResult = benefitComponentBusinessObjectBuilder
				.locate(criteria, user);
		List resultList = locateResult.getLocateResults();
		
		// Enhancement - Validation for benefits with the same name
		boolean duplicateBenefitHierarchyExists = false;
		String benefitName;
		if (null != resultList && resultList.size() != 0) {	
			
			if (resultList.size() > 1) {
				for (int i = 0; i < resultList.size(); i++) {
					BenefitHierarchyAssociationBO bhAssnBO = (BenefitHierarchyAssociationBO) resultList
							.get(i);
					benefitName = bhAssnBO.getBenefitName();
					for (int j = i + 1; j < resultList.size(); j++) {
						BenefitHierarchyAssociationBO bhAssnBO1 = (BenefitHierarchyAssociationBO) resultList
								.get(j);
						if (benefitName.equals(bhAssnBO1.getBenefitName())) {
							duplicateBenefitHierarchyExists = true;
						}
					}
				}
			} else {
				duplicateBenefitHierarchyExists = false;
			}
			
			// End - Enhancement
			int size = resultList.size();
			int count = 0;

			for (int i = 0; i < size; i++) {
				BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) resultList
						.get(i);
				criteria.setBenefitComponentId(benefitComponentBO
						.getBenefitComponentId());
				criteria.setBenefitHierarchyId(benefitHierarchyAssociationBO
						.getBenefitHierarchyId());
				List benefitList = benefitComponentBusinessObjectBuilder
						.searchForBenefits(criteria);
				if (benefitList.size() > 0) {
					count++;
				}
			}
			/*	Newly Added as part of Blaze Rule enhancement.calls the validation method for ruleType
			 *  Calls the validateBenefitComponentRuleType method in RuleTypeValidationBuilder to validate 
			 *  whether the associated benefits of a Component have the same RuleType
			 * */
			/*boolean isRuleTypeSame=true;
			String level="";
			Object[] ruleInfo=RuleTypeValidationBuilder.validateBenefitComponentRuleType(resultList,benefitComponentBO
					.getBenefitComponentId());
			if(ruleInfo!=null){
				isRuleTypeSame = ((Boolean)ruleInfo[1]).booleanValue();
			}
			if(!isRuleTypeSame){		
				messageList
				.add(new ErrorMessage(ruleInfo[0].toString()));
				// return false;
			}*/
			// Enhancement
			if (duplicateBenefitHierarchyExists) {
				messageList.add(new ErrorMessage(
						BusinessConstants.DUPLICATE_NAME_FOR_BENEFIT));
				return false;
				// End - Enhancement
			} else if (size != count) {
				messageList
						.add(new ErrorMessage(
								BusinessConstants.BNFT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BNFT));
				return false;
			} /*else if(!isRuleTypeSame){
				return false;
			}*/else{
				return true;
			}
			
			
			
		
		} else if(newBenefitListSize==0){
			messageList
					.add(new ErrorMessage(
							BusinessConstants.BNFT_COMP_SHOULD_HAVE_ATLEAST_ONE_BNFT_HRCHY));
			return false;
		}else {
			return true;
		}
		
	}

	/*
	 * Validate rule
	 * 
	 * @BenefitComponentBO
	 * 
	 * return boolean
	 */
	private boolean isValidrule(BenefitComponentBO benefitComponentBO)
			throws SevereException {
		StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
		BenefitComponentAdapterManager compAdapterMngr = new BenefitComponentAdapterManager();
		BenefitComponentBusinessObjectBuilder componentBuilder = new BenefitComponentBusinessObjectBuilder();
		BenefitComponentBO retrievedComponent = new BenefitComponentBO();
		retrievedComponent.setBenefitComponentId(benefitComponentBO.getBenefitComponentId());
		componentBuilder.retrieve(retrievedComponent);
		int entityId = benefitComponentBO.getBenefitComponentId();
		String entityType = BusinessConstants.BENEFIT_COMP;
		String ruleId=(String)retrievedComponent.getRuleList().get(0);
		List ruleList = standardBenefitAdapterManager.validateBenefitRule(
				entityId, entityType,ruleId);
		if (null != ruleList && !ruleList.isEmpty())
			return true;
		else
			return false;
	}

	private boolean isAllLevelHiddenForComponent(
			BenefitComponentBO benefitComponentBO, User user)
			throws SevereException {
		boolean validationFlag = true;

		BenefitLine benefitLine = new BenefitLine();
		benefitLine.setBenefitComponentId(benefitComponentBO
				.getBenefitComponentId());
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		List resultList = benefitComponentBusinessObjectBuilder
				.getLevelHiddenCount(benefitLine);
		if (null != resultList && !resultList.isEmpty()) {
			int size = resultList.size();
			int count = 0;

			for (int benefitCount = 0; benefitCount < size; benefitCount++) {

				benefitLine = (BenefitLine) resultList.get(benefitCount);
				if (benefitLine.getRefCount() == 0) {
					validationFlag = false;
					break;
				} else
					count++;
			}
			if (count == size) {
				validationFlag = true;
			}

		}

		return validationFlag;
	}

	//ends
	/**
	 * Check the validity of benefit component
	 * 
	 * @param benefitComponentBO
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	private boolean isValidBenefitComponent(
			BenefitComponentBO benefitComponentBO, User user)
			throws SevereException {
		boolean validationFlag = true;

		BenefitHierarchyLocateCriteria criteria = new BenefitHierarchyLocateCriteria();
		criteria.setBenefitComponentId(benefitComponentBO
				.getBenefitComponentId());
		criteria.setValidationFlag(true);
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		LocateResults locateResult = benefitComponentBusinessObjectBuilder
				.locate(criteria, user);
		List resultList = locateResult.getLocateResults();

		if (null != resultList && resultList.size() > 0)
			validationFlag = false;
		return validationFlag;
	}

	/**
	 * Validation method for CreateAdminOptionRequest.
	 * 
	 * @param request
	 *            CreateAdminOptionRequest
	 * @return WPDResponse CreateAdminOptionResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			BenefitComponentCopyRequest benefitComponentCopyRequest)
			throws ServiceException {
		BenefitComponentCopyResponse benefitComponentCopyResponse = null;
		messageList = new ArrayList(2);
		BenefitComponentVO benefitComponentVO = benefitComponentCopyRequest
				.getBenefitComponentVO();
		BenefitComponentBO benefitComponentBO = this
				.getBenefitComponentBusinessObject(benefitComponentVO);
		benefitComponentCopyResponse = (BenefitComponentCopyResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.COPY_BENEFIT_COMPONENT_RESPONSE);
		try {
			benefitComponentBO.setBenefitComponentParentId(0);
			if (isBenefitComponentDuplicate(benefitComponentBO)) {
				messageList.add(new ErrorMessage(
						BusinessConstants.BENEFIT_COMP_ALREADY_EXISTS));
				benefitComponentCopyResponse.setMessages(messageList);
			}

			if (!isUniqueBenefitComponentName(benefitComponentBO)) {
				messageList.add(new ErrorMessage(
						BusinessConstants.BENEFIT_HAVING_SAME_BNFT_COMP_NAME));

				benefitComponentCopyResponse.setMessages(messageList);
			} else {

			}

		} catch (WPDException e) {
			Logger.logError(e);
			List logParameters = new ArrayList(1);
			logParameters.add(benefitComponentCopyRequest);
			String logMessage = "Failed while processing BenefitComponentSaveRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		benefitComponentCopyResponse.setMessages(messageList);
		benefitComponentCopyResponse.setBenefitComponentBO(benefitComponentBO);
		return benefitComponentCopyResponse;
	}

	/**
	 * Method to validate Benefit Component before deletion.
	 * 
	 * @param benefitComponentDeleteRequest
	 * @return benefitComponentDeleteResponse
	 * @throws WPDException
	 */
	public WPDResponse execute(
			BenefitComponentDeleteRequest benefitComponentDeleteRequest)
			throws ServiceException {
		List searchResultList = null;
		BenefitComponentDeleteResponse benefitComponentDeleteResponse = (BenefitComponentDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_BENEFIT_COMPONENT_RESPONSE);
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		BenefitComponentDeleteLocateCriteria criteria = new BenefitComponentDeleteLocateCriteria();
		criteria.setBenefitComponentKey(benefitComponentDeleteRequest
				.getBenefitComponentKey());
		/*
		 * search if any entry exists in PROD_STR_BNFT_CMPT_ASSN(Search is to be
		 * performed only if the selected value does not have an entry in the
		 * PROD_STR_BNFT_CMPT_ASSN( ie, it is not associated to the
		 * productStructure)
		 */
		try {
			// call searchIfBenefitComponentExists of the builder
			searchResultList = benefitComponentBusinessObjectBuilder
					.searchIfBenefitComponentExists(criteria);
			if (null != searchResultList && !searchResultList.isEmpty()) {
				// set response to false if value exists
				benefitComponentDeleteResponse.setSuccess(false);
			} else {
				// set response to false if no value exists
				benefitComponentDeleteResponse.setSuccess(true);
			}
		} catch (WPDException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(benefitComponentDeleteRequest);
			String logMessage = "Failed while processing benefitComponentDeleteRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return benefitComponentDeleteResponse;
	}

	/**
	 * Check benefit component validity
	 * 
	 * @param request
	 * @return boolean
	 * @throws WPDException
	 */
	private boolean isBenefitComponentValidated(
			BenefitComponentCheckInRequest request) throws WPDException {
		messageList = new ArrayList(2);
		BenefitHierarchyLocateCriteria criteria = new BenefitHierarchyLocateCriteria();
		criteria.setBenefitComponentId(request.getBenefitComponentVO()
				.getBenefitComponentId());
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		LocateResults locateResult = benefitComponentBusinessObjectBuilder
				.locate(criteria, request.getUser());
		List resultList = locateResult.getLocateResults();

		if (null != resultList && resultList.size() > 0) {
			int size = resultList.size();
			int count = 0;
			for (int i = 0; i < size; i++) {
				BenefitHierarchyAssociationBO benefitHierarchyAssociationBO = (BenefitHierarchyAssociationBO) resultList
						.get(i);
				criteria.setBenefitComponentId(request.getBenefitComponentVO()
						.getBenefitComponentId());
				criteria.setBenefitHierarchyId(benefitHierarchyAssociationBO
						.getBenefitHierarchyId());
				List benefitList = benefitComponentBusinessObjectBuilder
						.searchForBenefits(criteria);//FIXME multiple time db
													 // hit, modify the search
													 // query so only a single
													 // query will
													 // do(Performance check)
				if (null != benefitList && benefitList.size() > 0) {
					count++;
				}
			}
			if (size != count) {
				messageList
						.add(new ErrorMessage(
								BusinessConstants.BNFT_HRCHY_SHOULD_HAVE_ATLEAST_ONE_BNFT));
				return false;
			} else {
				return true;
			}

		} else {
			messageList
					.add(new ErrorMessage(
							BusinessConstants.BNFT_COMP_SHOULD_HAVE_ATLEAST_ONE_BNFT_HRCHY));
			return false;

		}

	}

	/**
	 * Creates BenefitComponentBO from a BenefitComponentSaveRequest via a
	 * BenefitComponentVO.
	 * 
	 * @param BenefitComponentSaveRequest
	 * @return BenefitComponentBO
	 */
	private BenefitComponentBO getBenefitComponentBusinessObject(
			BenefitComponentVO benefitComponentVO) {
		BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
		benefitComponentBO.setComponentType(benefitComponentVO
				.getComponentType());
		benefitComponentBO.setMandateType(benefitComponentVO.getMandateType());
		benefitComponentBO.setStateDesc(benefitComponentVO.getStateDesc());
		benefitComponentBO.setStateId(benefitComponentVO.getStateId());
		benefitComponentBO.setRuleList(benefitComponentVO.getRuleIdList());
		benefitComponentBO.setBenefitComponentId(benefitComponentVO
				.getBenefitComponentId());
		benefitComponentBO.setBenefitComponentParentId(benefitComponentVO
				.getBenefitComponentParentId());
		benefitComponentBO.setName(benefitComponentVO.getBenefitComponentName()
				.toUpperCase());
		benefitComponentBO.setBusinessEntity(benefitComponentVO
				.getBusinessEntityCodeList());
		benefitComponentBO.setBusinessGroup(benefitComponentVO
				.getBusinessGroupCodeList());
		benefitComponentBO.setMarketBusinessUnit(benefitComponentVO
				.getMarketBusinessUnitCodeList());
		benefitComponentBO.setLineOfBusiness(benefitComponentVO
				.getLineOfBusinessCodeList());
		benefitComponentBO.setEffectiveDate(benefitComponentVO
				.getEffectivedate());
		benefitComponentBO.setExpiryDate(benefitComponentVO.getExpirydate());
		benefitComponentBO.setDescription(benefitComponentVO.getDescription()
				.toUpperCase());
		benefitComponentBO.setStatus(benefitComponentVO.getStatus());
		benefitComponentBO.setVersion(benefitComponentVO.getVersion());

		if (benefitComponentVO.getBusinessDomainList() == null
				|| benefitComponentVO.getBusinessDomainList().size() == 0) {
			List lineOfBusiness = benefitComponentVO
					.getLineOfBusinessCodeList();
			List businessEntity = benefitComponentVO
					.getBusinessEntityCodeList();
			List businessGroup = benefitComponentVO.getBusinessGroupCodeList();
			List marketBusinessUnit = benefitComponentVO.getMarketBusinessUnitCodeList();
			benefitComponentBO.setBusinessDomainList(BusinessUtil
					.convertToDomains(lineOfBusiness, businessEntity,
							businessGroup, marketBusinessUnit));
		} else
			benefitComponentBO.setBusinessDomainList(benefitComponentVO
					.getBusinessDomainList());
		return benefitComponentBO;

	}

	/**
	 * Creates BenefitComponentBO from a BenefitComponentVO.
	 * 
	 * @param BenefitComponentVO
	 * @return BenefitComponentBO
	 */
	private BenefitComponentBO getBenefitComponentBusinessObjectForCheckIn(
			BenefitComponentVO benefitComponentVO) {
		BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
		benefitComponentBO
				.setName(benefitComponentVO.getBenefitComponentName());
		benefitComponentBO.setBenefitComponentId(benefitComponentVO
				.getBenefitComponentId());
		benefitComponentBO.setBenefitComponentParentId(benefitComponentVO
				.getBenefitComponentParentId());
		benefitComponentBO.setBusinessEntity(benefitComponentVO
				.getBusinessEntityCodeList());
		benefitComponentBO.setBusinessGroup(benefitComponentVO
				.getBusinessGroupCodeList());
		benefitComponentBO.setMarketBusinessUnit(benefitComponentVO
				.getMarketBusinessUnitCodeList());
		benefitComponentBO.setLineOfBusiness(benefitComponentVO
				.getLineOfBusinessCodeList());
		benefitComponentBO.setEffectiveDate(benefitComponentVO
				.getEffectivedate());
		benefitComponentBO.setExpiryDate(benefitComponentVO.getExpirydate());
		benefitComponentBO.setDescription(benefitComponentVO.getDescription());
		benefitComponentBO.setStatus(benefitComponentVO.getStatus());
		benefitComponentBO.setVersion(benefitComponentVO.getVersion());
		benefitComponentBO.setBusinessDomainList(benefitComponentVO
				.getBusinessDomainList());
		return benefitComponentBO;
	}

	/**
	 * Validation method for duplicate Benefit Componenet.
	 * 
	 * @param BenefitComponentBO
	 *            Object
	 * @return boolean
	 * @throws WPDException
	 */
	private boolean isBenefitComponentDuplicate(
			BenefitComponentBO benefitComponentBO) throws WPDException {
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		List benefitComponentList = benefitComponentBusinessObjectBuilder
				.checkDuplicateBenefitComponent(benefitComponentBO
		                .getName(),
		                benefitComponentBO.getBusinessDomainList(), benefitComponentBO
		                        .getBenefitComponentParentId());
		if (benefitComponentList != null && benefitComponentList.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Method for checking if Benefit component has name same as any benefits.
	 * 
	 * @param BenefitComponentBO
	 *            Object
	 * @return boolean
	 * @throws WPDException
	 */
	private boolean isUniqueBenefitComponentName(
			BenefitComponentBO benefitComponentBO) throws WPDException {
		BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
		List benefitNameList = benefitComponentBusinessObjectBuilder
				.checkUniqueBenefitComponentName(benefitComponentBO);
		if (null != benefitNameList && benefitNameList.size() > 0)
			return false;
		else
			return true;
	}

	/**
	 * Method to get the business domain values from BenefitComponentBO
	 * 
	 * @param BenefitComponentBO
	 * @return DomainDetail
	 */
	private DomainDetail getBusinessDomainValues(
			BenefitComponentBO benefitComponentBO) {

		DomainDetail businessDomain = new DomainDetail();
		businessDomain.setEntityType(BusinessConstants.BEN_COMPONENT);
		businessDomain.setEntityName(benefitComponentBO.getName());
		businessDomain.setEntitySystemId(benefitComponentBO
				.getBenefitComponentId());
		businessDomain
				.setLineOfBusiness(benefitComponentBO.getLineOfBusiness());
		businessDomain
				.setBusinessEntity(benefitComponentBO.getBusinessEntity());
		businessDomain.setBusinessGroup(benefitComponentBO.getBusinessGroup());
		/*START CARS*/
		businessDomain.setMarketBusinessUnit(benefitComponentBO.getMarketBusinessUnit());
		/*END CARS*/
		businessDomain.setCreatedUser(benefitComponentBO.getCreatedUser());
		businessDomain.setCreatedTimeStamp(benefitComponentBO
				.getCreatedTimestamp());
		businessDomain.setLastUpdatedUser(benefitComponentBO
				.getLastUpdatedUser());
		businessDomain.setLastUpdatedTimeStamp(benefitComponentBO
				.getLastUpdatedTimestamp());
		return businessDomain;
	}

	/**
	 * Validation method for CreateAdminOptionRequest.
	 * 
	 * @param request
	 *            CreateAdminOptionRequest
	 * @return WPDResponse CreateAdminOptionResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			BenefitHierarchyCreateRequest request)
			throws ServiceException {
		BenefitHierarchyResponse response = null;

		messageList = new ArrayList(4);
		BenefitComponentBO benefitComponentBO = request

		.getBenefitComponentBO();
		BenefitHierarchyVO benefitHierarchyVO = request.getBenefitHierarchyVO();
		response = (BenefitHierarchyResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.BENEFIT_HIERARCHY_RESPONSE);
		boolean componentValid = true;
		int newBenefits =0;
		boolean checkinOperation = request.isCheckInFlag() || request.isDoneFlag();

		try {
			
				boolean valid = false;//
			
				// Checkin Validations
				if (checkinOperation && componentValid ) {
					
					// Checks if associated rule is valid, if attached to GB, GP or SB
					if((WebConstants.GEN_BENEFITS.equals(benefitComponentBO.getName().trim())) ||
							(WebConstants.GEN_PROVISIONS.equals(benefitComponentBO.getName().trim())) ||
							(WebConstants.SUPPLEMENTAL_BENEFITS.equals(benefitComponentBO.getName().trim()))){
						if(null!= benefitComponentBO.getRuleList() && benefitComponentBO.getRuleList().size()>0){
							if (isValidrule(benefitComponentBO)) {
								messageList.add(new ErrorMessage(
										BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
								componentValid = false;
							}
						}
					}
					//Validatin rule for other Benefit Components
					else{
						if (isValidrule(benefitComponentBO)) {
							messageList.add(new ErrorMessage(
									BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
							componentValid = false;
						}
					}
					boolean flag = ApplicationConfigManager.isDuplicateBenefitCheckWaived(benefitComponentBO);
					// flag = true check means the benefit component contains ESI business entity hence 
					// validation of duplicate benefits needs to be bypassed
					if(!flag){
						// Validation to check component exist with same benefits and sequence.
						if (!isValidBenefitComponent(benefitComponentBO,
								request.getUser())) {
							componentValid = false;
							messageList.add(new ErrorMessage(BusinessConstants.BENEFIT_COMP_DUPLICATE));
						}
					}
					if(null!=benefitHierarchyVO.getBenefitHierarchiesList() && !benefitHierarchyVO.getBenefitHierarchiesList().isEmpty()){
						newBenefits = benefitHierarchyVO.getBenefitHierarchiesList().size();
					}
					
					// Some validations !!
					if (!isBenefitComponentValidated(benefitComponentBO,
							request.getUser(),newBenefits)) {
						componentValid = false;
					}
					
					// Checks if at least one benefit is visible.
					if (!isAllLevelHiddenForComponent(
									benefitComponentBO,
									request.getUser())) {
						messageList.add(new ErrorMessage(
								BusinessConstants.MSG_ATLEAST_ONE_LEVEL));
						componentValid = false;
					}
					
					if(!this.isValidBenefitPerDomain(benefitComponentBO)){
	            			ErrorMessage errorMessage = new ErrorMessage("benefit.hierarchy.invalid.benefit.change");	            			
	            			messageList.add(errorMessage);
							componentValid = false;
	            	}
					
					response.setSuccessFlag(componentValid);
					response.setMessages(messageList);
					response.setBenefitComponentBO(benefitComponentBO);
				}
				int attachedBenefits = isMaximumNumberOfBenefit(benefitHierarchyVO);
				newBenefits = 0;
				if(null!=benefitHierarchyVO.getBenefitHierarchiesList() && !benefitHierarchyVO.getBenefitHierarchiesList().isEmpty()){
					newBenefits = benefitHierarchyVO.getBenefitHierarchiesList().size();
				}
				int totalBenefits = attachedBenefits + newBenefits;
				if(totalBenefits <= WebConstants.MAX_BEN_ATTACH){
					response.setMaxFlag(true);
				}else if(attachedBenefits == WebConstants.MAX_BEN_ATTACH){
        			ErrorMessage errorMessage = new ErrorMessage("benefit.zero.number.exceeded");
        			messageList.add(errorMessage);	
        			response.setMaxFlag(false);
        			response.setMessages(messageList);
				}else{
        			ErrorMessage errorMessage = new ErrorMessage("benefit.maximum.number.exceeded");
        			int remaining = WebConstants.MAX_BEN_ATTACH - attachedBenefits;
        			errorMessage.setParameters(new String[]{String.valueOf(remaining)});
        			messageList.add(errorMessage);	
        			response.setMaxFlag(false);
        			response.setMessages(messageList);
				}
			response.setBenefitComponentBO(benefitComponentBO);
		}
		

		catch (WPDException e) {
			Logger.logError(e);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing BenefitHierarchyUpdateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		response.setMessages(messageList);
		return response;
	}

    private boolean isValidBenefitPerDomain(BenefitComponentBO targetBenefitComponentBO)throws SevereException{
        BenefitComponentBusinessObjectBuilder benefitComponentBusinessObjectBuilder = new BenefitComponentBusinessObjectBuilder();
        BenefitHierarchyLocateCriteria benefitHierarchyLocCriteria = new BenefitHierarchyLocateCriteria();
        benefitHierarchyLocCriteria.setBenefitComponentId(targetBenefitComponentBO
                        .getBenefitComponentId());
        benefitHierarchyLocCriteria.setLobList(BusinessUtil.getLobList(targetBenefitComponentBO
                        .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setBusinessEntityList(BusinessUtil
                        .getbusinessEntityList(targetBenefitComponentBO
                                .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setBusinessGroupList(BusinessUtil
                        .getBusinessGroupList(targetBenefitComponentBO
                                .getBusinessDomainList()));
        benefitHierarchyLocCriteria.setMarketBusinessUnitList(BusinessUtil
                .getMarketBusinessUnitList(targetBenefitComponentBO
                        .getBusinessDomainList()));
        LocateResults locateResults = benefitComponentBusinessObjectBuilder
                .locateBenefitHieararchiesToBeRemoved(benefitHierarchyLocCriteria);
        if(null != locateResults 
        		&& null != locateResults.getLocateResults() 
				&& !locateResults.getLocateResults().isEmpty()){
        	return false;
        }
        return true;
    }
    
	/**
	 * Validation method for CreateAdminOptionRequest.
	 * 
	 * @param request
	 *            CreateAdminOptionRequest
	 * @return WPDResponse CreateAdminOptionResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			BenefitComponentScheduleForTestRequest benefitComponentScheduleForTestRequest)
			throws ServiceException {
		BenefitComponentScheduleForTestResponse benefitComponentScheduleForTestResponse = null;
		messageList = new ArrayList(1);
		BenefitComponentBO benefitComponentBO = benefitComponentScheduleForTestRequest
				.getBenefitComponentBO();
		benefitComponentScheduleForTestResponse = (BenefitComponentScheduleForTestResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SCHEDULE_TEST_BENEFIT_COMPONENT_RESPONSE);
		try {
			User user = benefitComponentScheduleForTestRequest.getUser();

			if (isValidBenefitComponent(benefitComponentBO, user)) {
				if (isBenefitComponentValidated(benefitComponentBO, user,0)) {
					benefitComponentScheduleForTestResponse.setErrorFlag(false);
				} else
					benefitComponentScheduleForTestResponse.setErrorFlag(true);
			} else {
				messageList = new ArrayList(1);
				benefitComponentScheduleForTestResponse.setErrorFlag(true);
				messageList.add(new ErrorMessage(
						BusinessConstants.BENEFIT_COMP_DUPLICATE));
			}
		} catch (WPDException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(benefitComponentScheduleForTestRequest);
			String logMessage = "Failed while processing BenefitHierarchyUpdateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		benefitComponentScheduleForTestResponse.setMessages(messageList);
		return benefitComponentScheduleForTestResponse;
	}
	
	private int isMaximumNumberOfBenefit(BenefitHierarchyVO benefitHierarchyVO) throws SevereException{
		BenefitComponentBusinessObjectBuilder builder = new BenefitComponentBusinessObjectBuilder();
		LocateResults locateResults = new LocateResults();
		BenefitHierarchyLocateCriteria criteria = new BenefitHierarchyLocateCriteria();
		if(null!=benefitHierarchyVO && !benefitHierarchyVO.equals(null)){
			criteria.setBenefitComponentId(benefitHierarchyVO.getBenefitComponentId());
        	locateResults = builder.getBenefitCount(criteria);
        	return locateResults.getTotalResultsCount();
		}
		return 0;
	}

}