/*
 * ProductStructureBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.service.validation;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.request.DeleteProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureLifeCycleRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.response.DeleteProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureLifeCycleResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBusinessValidationService extends WPDService {

	/**
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
	 * Validation method for SaveProductStructureRequest.
	 * 
	 * @param request
	 *            SaveProductStructureRequest
	 * @return WPDResponse SaveProductStructureResponse.
	 * @throws SevereException
	 */
	public WPDResponse execute(
			SaveProductStructureRequest saveProductStructureRequest)
			throws ServiceException {

		ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(saveProductStructureRequest
				.getProductStructureVO());
		SaveProductStructureResponse response = (SaveProductStructureResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_PRODUCT_STRUCTURE_RESPONSE);
		List messageList = new ArrayList(6);
		int productStructureId = productStructureBO.getProductStructureId();
		int productStructureParentId = productStructureBO
				.getProductStructureParentId();
		boolean valid = true;
		try {
			if (saveProductStructureRequest.isActionFromBC() != true) {
				if (saveProductStructureRequest.getAction() == SaveProductStructureRequest.COPY_PRODUCT_STRUCTURE) {
					productStructureBO
							.setProductStructureId(productStructureId);
					productStructureBO
							.setProductStructureParentId(productStructureParentId);
					checkDomainInvalid(response, productStructureBO);
					checkDateRangeInvalid(response, productStructureBO);
					productStructureBO.setProductStructureId(0);
					productStructureBO.setProductStructureParentId(0);
					
				}
				if (isProductStructureDuplicate(productStructureBO)) {
					messageList.add(new ErrorMessage(
							BusinessConstants.PRODUCT_STRUCTURE_DUPLICATES));
					valid = false;
				}
				if ( ((saveProductStructureRequest.getAction() != SaveProductStructureRequest.CREATE_PRODUCT_STRUCTURE) && (saveProductStructureRequest
								.getAction() != SaveProductStructureRequest.COPY_PRODUCT_STRUCTURE))) {
					productStructureBO
							.setProductStructureId(productStructureId);
					productStructureBO
							.setProductStructureParentId(productStructureParentId);
					checkDomainInvalid(response, productStructureBO);
					checkDateRangeInvalid(response, productStructureBO);
					//                    if (isDomainInvaid(productStructureBO)) {
					//                        valid = false;
					//                        messageList
					//                                .add(new ErrorMessage(
					//                                        BusinessConstants.PRODUCT_STRUCTURE_DOMAIN_INVALID));
					//                    }
//					                    if (valid && isDateRangeInvalid(productStructureBO)) {
//					                        valid = false;
//					                        messageList.add(new ErrorMessage(
//					                                BusinessConstants.DATE_RANGE_INVALID));
//					                    }
				}
			}
			if (
					 (saveProductStructureRequest.getAction() == SaveProductStructureRequest.CHECK_IN_PRODUCT_STRUCTURE || saveProductStructureRequest
							.getAction() == SaveProductStructureRequest.DONE)) {
				saveProductStructureRequest.setCheckInFlag(true);
				if (!isValidForCheckIn(productStructureBO)) {
					messageList.add(new ErrorMessage(
							BusinessConstants.BENEFIT_COMPONENT_EMPTY));
					saveProductStructureRequest.setCheckInFlag(false);
				} 
				
				if (isDuplicateHeirarchyPresent(productStructureBO)) {
					messageList
							.add(new ErrorMessage(
									BusinessConstants.PRODUCT_STRUCTURE_HIERARCHY_PRESENT));
					saveProductStructureRequest.setCheckInFlag(false);
				} 
				
				if (saveProductStructureRequest.getProductStructureVO()
						.getStructureType().equals("STANDARD")) {
					if (!ismandatoryComponentPresent(productStructureBO)) {
						messageList
								.add(new ErrorMessage(
										BusinessConstants.MANDATORY_COMPONENT_NOT_PRESENT));
						saveProductStructureRequest.setCheckInFlag(false);
					}
					if (!isMandatoryGenProvision(productStructureBO)) {
						messageList
								.add(new ErrorMessage(
										BusinessConstants.MANDATORY_GEN_PROVISION_NOT_PRESENT));
						saveProductStructureRequest.setCheckInFlag(false);
					}
				}
				this.checkBenefitVisibility(productStructureBO);
				
				
				
				
				if(null!=productStructureBO.getInvalidComponentList() && productStructureBO.getInvalidComponentList().size()>0){
					String benefitComponentsName = productStructureBO.getInvalidComponentList().get(0).toString();
					for(int i=1; i<productStructureBO.getInvalidComponentList().size(); i++){
						benefitComponentsName = benefitComponentsName+", "+productStructureBO.getInvalidComponentList().get(i).toString();
					}
					ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.NO_BENEFIT_VISIBLE);
					errorMessage.setParameters(new String[] {benefitComponentsName.toString()});
					messageList.add(errorMessage);
					saveProductStructureRequest.setCheckInFlag(false);
					
				}
			
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(saveProductStructureRequest);
			String logMessage = "Failed while processing CreateProductStructureRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		response.setSuccess(valid);
		response.setProductStructure(productStructureBO);
		response.setMessages(messageList);
		return response;
	}

	/**
	 * Validation method for ProductStructureLifeCycleRequest.
	 * 
	 * @param request
	 *            ProductStructureLifeCycleRequest
	 * @return WPDResponse ProductStructureLifeCycleResponse.
	 * @throws SevereException
	 */
	public WPDResponse execute(
			ProductStructureLifeCycleRequest productStructureLifeCycleRequest)
			throws ServiceException {
		ProductStructureVO productStructureVO = (ProductStructureVO) productStructureLifeCycleRequest
				.getProductStructureVO();
		ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(productStructureVO);
		List messageList = new ArrayList(3);
		boolean valid = true;
		try {
			if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.SCHEDULE_FOR_TEST_PRODUCT_STRUCTURE) {
				if (!(isValidForCheckIn(productStructureBO))) {
					messageList.add(new ErrorMessage(
							BusinessConstants.COMPONENT_EMPTY_FOR_TEST));
					valid = false;
				} else if (isDuplicateHeirarchyPresent(productStructureBO)) {
					messageList
							.add(new ErrorMessage(
									BusinessConstants.PRODUCT_STRUCTURE_HIERARCHY_PRESENT));
					valid = false;
				} else if (isStandard(productStructureBO)) {
					if (!ismandatoryComponentPresent(productStructureBO)) {
						messageList
								.add(new ErrorMessage(
										BusinessConstants.MANDATORY_COMPONENT_NOT_PRESENT));
						valid = false;
					}
				}
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(productStructureLifeCycleRequest);
			String logMessage = "Failed while processing productStructureLifeCycleRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		ProductStructureLifeCycleResponse productStructureLifeCycleResponse = (ProductStructureLifeCycleResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.PRODUCT_STRUCTURE_LIFE_CYCLE_RESPONSE);
		productStructureLifeCycleResponse.setMessages(messageList);
		productStructureLifeCycleResponse.setSuccess(valid);
		return productStructureLifeCycleResponse;
	}

	/**
	 * Validation method for DeleteProductStructureRequest.
	 * 
	 * @param request
	 *            DeleteProductStructureRequest
	 * @return WPDResponse DeleteProductStructureResponse.
	 * @throws SevereException
	 */
	public WPDResponse execute(
			DeleteProductStructureRequest deleteProductStructureRequest)
			throws ServiceException {
		DeleteProductStructureResponse deleteProductStructureResponse = (DeleteProductStructureResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_PRODUCT_STUCTURE_RESPONSE);
		List messageList = new ArrayList(1);
		int productStructureId = deleteProductStructureRequest
				.getProductStructureVO().getProductStructureId();
		ProductAdapterManager productAdapterManager = new ProductAdapterManager();
		try {
			if (deleteProductStructureRequest.getAction() == DeleteProductStructureRequest.DELETE_PRODUCT_STRUCTURE) {
				if (productAdapterManager
						.isProductStructureAssociatedWithProduct(productStructureId)) {
					messageList.add(new ErrorMessage(
							BusinessConstants.PRODUCT_STRUCTURE_ASSOCIATED));
					deleteProductStructureResponse.setSuccess(false);
					deleteProductStructureResponse.setMessages(messageList);
				} else
					deleteProductStructureResponse.setSuccess(true);
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList(1);
			logParameters.add(deleteProductStructureRequest);
			String logMessage = "Failed while processing productStructureLifeCycleRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		if (deleteProductStructureRequest.getAction() == DeleteProductStructureRequest.DELETE_BENEFIT_COMPONENT)
			deleteProductStructureResponse.setSuccess(true);
		return deleteProductStructureResponse;
	}

	/**
	 * Validation method for duplicate ProductStructure.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO Object
	 * @return boolean
	 * @throws SevereException
	 */
	private boolean isProductStructureDuplicate(
			ProductStructureBO productStructureBO) throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List structureList = adapterManager.getDuplicateNameList(productStructureBO
	            .getProductStructureName(),
	            productStructureBO.getBusinessDomains(), productStructureBO
	                    .getProductStructureParentId());
		if (structureList != null && structureList.size() > 0)
			return true;
		else
			return false;

	}

	/**
	 * Validation method for Type of Product Structure.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO Object
	 * @return boolean
	 */
	private boolean isStandard(ProductStructureBO productStructureBO) {
		if (null != productStructureBO.getStructureType()) {
			if (productStructureBO.getStructureType().equals("STANDARD")) {
				return true;
			} else
				return false;
		} else
			return false;

	}

	/**
	 * To set the values from VO to BO
	 * 
	 * @param productStructureVO
	 * @return ProductStructureBO
	 */
	private ProductStructureBO getProductStructureBO(
			ProductStructureVO productStructureVO) {

		ProductStructureBO productStructureBO = new ProductStructureBO();
		productStructureBO.setProductStructureId(productStructureVO
				.getProductStructureId());
		productStructureBO.setProductStructureParentId(productStructureVO
				.getParentProductStructureId());
		productStructureBO.setProductStructureName(productStructureVO
				.getProductStructureName());
		productStructureBO.setDescription(productStructureVO.getDescription());
		productStructureBO.setEffectiveDate(productStructureVO
				.getEffectiveDate());
		productStructureBO.setExpiryDate(productStructureVO.getExpiryDate());
		productStructureBO.setAssociatedBenefitComponentList(productStructureVO
				.getAssociatedBenefitComponentList());
		List lineOfBusiness = productStructureVO.getLineOfBusiness();
		List businessEntity = productStructureVO.getBusinessEntity();
		List businessGroup = productStructureVO.getBusinessGroup();
		//CARS START
		List marketBusinessUnit = productStructureVO.getMarketBusinessUnit();
		//CARS END
		productStructureBO.setStructureType(productStructureVO
				.getStructureType());
		if (null != productStructureVO.getMandateType())
			productStructureBO.setMandateType(productStructureVO
					.getMandateType());
		if (null != productStructureVO.getStateId())
			productStructureBO.setStateId(productStructureVO.getStateId());
		//CARS START
		productStructureBO.setBusinessDomains(BusinessUtil.convertToDomains(
				lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));
		//CARS END
		productStructureBO.setStatus(productStructureVO.getStatus());
		productStructureBO.setVersion(productStructureVO.getVersion());

		return productStructureBO;
	}

	/**
	 * @return
	 * @throws SevereException
	 */
	private boolean isDateRangeInvalid(ProductStructureBO productStructureBO)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List dateRangeList = adapterManager
				.inValidDateRange(productStructureBO.getProductStructureId(),
						productStructureBO.getEffectiveDate(),
						productStructureBO.getExpiryDate());
		if (dateRangeList != null && dateRangeList.size() > 0)
			return true;
		return false;
	}
	
	/**
	 * @return
	 * @throws SevereException
	 */
	private void checkDateRangeInvalid(SaveProductStructureResponse response,
			ProductStructureBO productStructureBO)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List dateRangeList = adapterManager
				.inValidDateRange(productStructureBO.getProductStructureId(),
						productStructureBO.getEffectiveDate(),
						productStructureBO.getExpiryDate());
		if (dateRangeList != null && dateRangeList.size() > 0)
			response.setComponentInvalidDateList(dateRangeList);
	}

	/**
	 * @return
	 * @throws SevereException
	 */
	private void checkDomainInvalid(SaveProductStructureResponse response,
			ProductStructureBO productStructureBO) throws SevereException {
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		List businessDomain = productStructureBO.getBusinessDomains();
		List lob = BusinessUtil.getLobList(businessDomain);
		List be = BusinessUtil.getbusinessEntityList(businessDomain);
		List bg = BusinessUtil.getBusinessGroupList(businessDomain);
		//CARS START
		List mbu = BusinessUtil.getMarketBusinessUnitList(businessDomain);
		//CARS END
		List removeList = adapterManager
				.getAssociatedBenefitComponentsToBeRemoved(productStructureBO
						.getProductStructureId(), lob, be, bg,mbu);
		if (removeList != null && removeList.size() > 0) {
			response.setBenefitComponentList(removeList);
		}
	}

	/**
	 * Method to check a Product Structure whether applicable for Check In.
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO Object
	 * @return boolean
	 */
	private boolean isValidForCheckIn(ProductStructureBO productStructureBO)
			throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List benefitComponentList = adapterManager
				.retrieveBenefitComponents(productStructureBO
						.getProductStructureId());
		if (null == benefitComponentList || benefitComponentList.size() == 0) 
			return false;
		
		return true;
	}

	/**
	 * Method to check Duplicate Hierarchy .
	 * 
	 * @param productStructureBO
	 *            ProductStructureBO Object
	 * @return boolean
	 */
	private boolean isDuplicateHeirarchyPresent(
			ProductStructureBO productStructureBO) throws SevereException {
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List benefitComponentList = adapterManager
				.getDuplicateHeirarchy(productStructureBO
						.getProductStructureId());
		if (null == benefitComponentList || benefitComponentList.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * @param productStructureBO
	 * @return
	 * @throws SevereException
	 */
	private boolean ismandatoryComponentPresent(
			ProductStructureBO productStructureBO) throws SevereException {
		boolean isGenBenefit = true;
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		List genBenefitList = adapterManager
				.getMandatoryComponents(productStructureBO
						.getProductStructureId());
				if (genBenefitList == null || genBenefitList.size() == 0)
			return false;
		return true;
	}
	
	/**
	 * @param productStructureBO
	 * @return
	 * @throws SevereException
	 */
	private boolean isMandatoryGenProvision(
			ProductStructureBO productStructureBO) throws SevereException {
		
		boolean isGenProvision = true;
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		
		List genProvisionList = adapterManager
				.getMandatoryGenProvision(productStructureBO
						.getProductStructureId());
		
		if (genProvisionList == null || genProvisionList.size() == 0)
			return false;
		
		return true;
	}
	
	/**
	 * Method to check whether the Benefit Component having at least one visisble Benefit
	 * 
	 *  @param productStructureBO
	 * @return List
	 * 
	 */
	private ProductStructureBO checkBenefitVisibility(ProductStructureBO productStructureBO) throws SevereException{
		List invalidBenefitComponentList;
		
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		ProductStructureAssociatedBenefitComponent associatedComponent;
		List benefitComponentList = adapterManager
		.retrieveBenefitComponents(productStructureBO
				.getProductStructureId());
		invalidBenefitComponentList = new ArrayList(benefitComponentList==null?0:benefitComponentList.size());
		if (null!=benefitComponentList && benefitComponentList.size()!=0){
			for(int i=0; i<benefitComponentList.size(); i++){
				//int benefitComponentKey = benefitComponentList.
				associatedComponent = new ProductStructureAssociatedBenefitComponent();
				associatedComponent = (ProductStructureAssociatedBenefitComponent)benefitComponentList.get(i);
				if(!adapterManager.getVisibleBenefits(associatedComponent)){
					invalidBenefitComponentList.add(associatedComponent.getName());
				}
				
			}
			
		}
		productStructureBO.setInvalidComponentList(invalidBenefitComponentList);
		return productStructureBO;
	}

}