/*
 * ProductBusinessValidationService.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.common.builder.RuleTypeValidationBuilder;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponent;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociationBO;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.request.CheckProductTierDefnRequest;
import com.wellpoint.wpd.common.product.request.ProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRulesRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitTierValidationResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductRulesResponse;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;
import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;
import com.wellpoint.wpd.common.product.vo.ProductVO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBusinessValidationService extends WPDBusinessService
{
	
	// validation for duplicate product rule value
	
	public WPDResponse execute(SaveProductRulesRequest saveProductRulesRequest) throws ServiceException
	{
		try
		{
			SaveProductRulesResponse response = (SaveProductRulesResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_RULES_RESPONSE);
			List messageList = new ArrayList();
			ProductRuleAssociation productRuleAssociation = new ProductRuleAssociation();
			boolean valid = true;
			//------------------------------------- create product validation
			// -------------------------------
			setValuesToProductRules(saveProductRulesRequest, productRuleAssociation);
			//checks product rules duplicate
			if (isProductRulesDuplicate(productRuleAssociation, saveProductRulesRequest.getRulesList()))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRODUCT_RULE_SAVE_DUPLICATE));
			}
			else
			{
				response.setValidateRulesList(productRuleAssociation.getRulesList());
			}
			response.setValid(valid);
			response.setMessages(messageList);
			return response;
		}
		catch (Exception ex)
		{
			throw new ServiceException("Exception occured while BOM call", null, ex);
		}
	}
	// validation for product rule mandatory field:value
	// Start of Check In Validation CR
	//public boolean isRuleValue(ProductBO product) throws SevereException
	public List retrieveUncodedRules(ProductBO product) throws SevereException
	// End of Check In Validation CR
	{
		
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		ProductRuleAssociation productRuleAssociation = new ProductRuleAssociation();
		productRuleAssociation.setProductKey(product.getProductKey());
		List searchList = adapterManager.getProductRulesMandatoryValue(productRuleAssociation);
		//  Start of Check In Validation CR    
		/*if (searchList.size() > 0)
		 {
		 return false;
		 }
		 else
		 {
		 return true;
		 }*/
		return searchList;
		//  End of Check In Validation CR
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SaveProductComponentRequest request) throws ServiceException
	{
		try
		{
			Logger.logInfo("Entering execute method of validation, request = " + request.getClass().getName());
			// int action = request.getAction();
			
			SaveProductComponentResponse response = new SaveProductComponentResponse();//FIXME Use response factory
			List messageList = new ArrayList();
			boolean valid = true;
			List componentVOList = request.getComponentList();
			Iterator iterComponent = componentVOList.iterator();
			ProductComponentBO productComponentBO = null;
			ProductBO product = getProductBO(request);
			while (iterComponent.hasNext())
			{
				productComponentBO = new ProductComponentBO();
				Integer compKeyInteger = (Integer) iterComponent.next();
				
				if (isProductComponentDuplicate(product.getProductKey(), compKeyInteger.intValue()))
				{
					//duplicate exists
					valid = false;
					messageList.add(new ErrorMessage("product.component.duplicate"));//FIXME Remove hardcoded
					break;
				}
			}
			response.setValid(valid);
			response.setMessages(messageList);
			Logger.logInfo("Returning  from execute method of validation  for request=" + request.getClass().getName());
			return response;
		}
		catch (SevereException ex)
		{
			throw new ServiceException("Exception occured while BOM call", null, ex);
		}
		
	}
	
	
	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(SaveProductRequest request) throws ServiceException
	{
		try
		{
			Logger.logInfo("Save Product Service - Request = { " + request + " }");
			int action = request.getAction();
			ProductVO productValueObject = request.getProduct();
			//BusinessObjectManager bom = getBusinessObjectManager();//FIXME Remove commented
			ProductBO product = new ProductBO();
			User user = request.getUser();
			// int productKey = productValueObject.getProductKey();
			//creates the response
			SaveProductResponse response = (SaveProductResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_RESPONSE);
			List messageList = new ArrayList();
			boolean valid = true;
			boolean isProuctRuleTypeSame=true;
			switch (action)
			{
			//create product validation
			case SaveProductRequest.CREATE_PRODUCT:
				setValuesToProductBO(productValueObject, product);
			//checks product duplicate
			if (isProductDuplicate(product))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_DUPLICATE));
			}
			//checks the product structure is valid or not
			if (!isProductStructureValid(product, messageList))
				valid = false;
			break;
			//updates product
			case SaveProductRequest.UPDATE_PRODUCT:
				product = getProductBO(request);
			setValuesToProductBO(productValueObject, product);
			ProductStructureBO productStructureBO = new ProductStructureBO();
			ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
			productStructureBO.setProductStructureId(Integer.parseInt(product.getProductStructureKey()));
			try
			{
				//retrieves the product structure
				builder.retrieve(productStructureBO);
			}
			catch (WPDException e)
			{
				throw new ServiceException(null, null, e);
			}
			//checks product duplicate
			if (isProductDuplicate(product))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_DUPLICATE));
			}
			if(!isValidDateRange(product,messageList)){
				
				valid = false;
				
			}
			if (!isDomainValid(product, productStructureBO))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_DOMAIN_INVALID));
			}
			//checks the product structure is valid or not
			/* if (!isProductStructureValid(product, messageList))
			 valid = false;*/
			//checks product structure is modified
			if (isProductStructureModified(product))
			{
				response.setProductStructureChanged(true);
			}
			//checks the key values have been changed
			if (isKeyValuesChanged(request))
			{
				response.setKeyChanged(true);
				if (product.getVersion() > 0)
				{
					messageList.add(new ErrorMessage("product.key.change.invalid"));//FIXME Remove hardcoded
					valid = false;
				}
			}
			break;
			case SaveProductRequest.CHECKIN_PRODUCT:
				product = getProductBO(request);
			//setValuesToProductBO(productValueObject, product);
			valid=true;
			
			/*if (!isLatestPublishedVersion(request.getProduct().getProductStructureKey()))
			 {
			 valid = false;
			 messageList.add(new ErrorMessage(BusinessConstants.PRODUCT_STR_VERSION_INVALID));
			 
			 }*/
			
			List errorMessageForBCValidation = this.validateBenefitComponenets(product);
			if(null != errorMessageForBCValidation && errorMessageForBCValidation.size() > 0){
				messageList.addAll(errorMessageForBCValidation);
			}
			
			/*if (!isProductComponentAssociated(product))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_CHECKIN_INVALID));
			}
			
			// If at least one component attached.
			if ((WebConstants.STANDARD_TYPE_CAP).equals(product.getProductType()) && (!isMandatoryBenefitComponentsAssociated(product.getProductKey())))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.GENERAL_BENEFIT_MANDATORY));
			}
			if ((WebConstants.STANDARD_TYPE_CAP).equals(product.getProductType()) &&(!isMandatoryGenProvisionAssociated(product.getProductKey())))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.GENERAL_PROVISIONS_MANDATORY));
			}
			// check benefit component contain atleast one benefit
			
			ErrorMessage messageValidBenefit = checkValidBenefitComponent(product);
			if(messageValidBenefit!=null)
			{
				messageList.add(messageValidBenefit);
			}*/
			
			if (duplicatesPresent(product))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_BNFT_CMPNT_DUPLICATE));
			}
			if (messageList.size() > 0)
			{
				valid = false;
			}
			//if benefit component validations are valid ,check rule validations
			// Start Of CheckIn Validation CR            
			/*if (isValidrule(product))
			 {
			 valid = false;
			 messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_RULE_VALIDATE));
			 }
			 
			 if (!isRuleValue(product))
			 {
			 valid = false;
			 messageList.add(new ErrorMessage(BusinessConstants.PRODUCT_RULE_VALIDATION_FAILURE));
			 }*/
			
			// Rule Validation
			List deletedRulesList = retrieveDeletedRules(product);
			List unCodedRulesList = retrieveUncodedRules(product);
			if((null != deletedRulesList && !deletedRulesList.isEmpty())
					|| (null != unCodedRulesList && !unCodedRulesList.isEmpty())){
				valid = false;
				ErrorMessage errorMessage = new ErrorMessage
				(BusinessConstants.PRODUCT_RULE_VALIDATION_ERROR);
				errorMessage.setLink(BusinessConstants.
						PRODUCT_RULE_VALIDATION_LINK);
				messageList.add(errorMessage);
				response.setDeletedRulesList(deletedRulesList);
				response.setUnCodedRulesList(unCodedRulesList);
			}
			// End Of CheckIn Validation CR         
			////  check whether all the reference attched to the benefit line are unique
			/*ErrorMessage messageUniqueRef = isUnique(product);
			 if(null!=messageUniqueRef){
			 valid = false;
			 messageList.add(messageUniqueRef);
			 }*/
			
			
			/* Validation ProductRuleTypes - Calls validateProductRuleTypes(int productId)method in RuleTypeValidationBuilder 
			 * to check if the associated benefit components and product has same Rule Type 
			 */
			Logger.logDebug("Product Id== "+ product.getProductKey()+ " Validationf Product Rule Types start here");
			if(valid){
				isProuctRuleTypeSame=RuleTypeValidationBuilder.validateProductRuleTypes(product.getProductKey());
			}				
			if(!isProuctRuleTypeSame){
				Logger.logDebug("isProuctRuleTypeSame=="+isProuctRuleTypeSame + "Product Rules should be of same Rule Type");
				valid=false;
				ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.PRODUCT_RULE_TYPE_VALIDATION);
				errorMessage.setLink(BusinessConstants.RULE_TYPE_VALIDATION_LINK_PRODUCT);
				messageList.add(errorMessage);
			}
			//End of validation ProductRuleTypes
			
			if (messageList.size() > 0)
			{
				valid = false;
			}
			
			if (valid)
			{
				messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_CHECKIN_VALID));
			}
			break;
			
			case SaveProductRequest.COPY_PRODUCT:
				product = getProductBO(request);
			product.setAction(request.getAction());
			setValuesToProductBO(productValueObject, product);
			// Set parent key as zero for duplicate validation.
			product.setParentProductKey(0);
			if (isProductDuplicate(product))
			{
				valid = false;
				messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_DUPLICATE));
			}
			if (!isProductStructureValid(product, messageList))
				valid = false;
			break;
			
			case SaveProductRequest.SEND_TO_TEST_PRODUCT:
				product = getProductBO(request);
			// Validation needed for versions greater than one because for
			// version one it has been done
			// in checkin time.
			if (product.getVersion() > 0)
			{
				product = this.retrieveProduct(product, user);
				if (!isLatestPublishedVersion(Integer.parseInt(product.getProductStructureKey())))
				{
					valid = false;
					messageList.add(new ErrorMessage("product.str.version.invalid"));//FIXME Remove hardcoded
				}
				if (!isProductComponentAssociated(product))
				{
					valid = false;
					messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_SCHEDULE_TEST_FAILURE));
				}
				if (valid)
				{
					if (duplicatesPresent(product))
					{
						valid = false;
						messageList.add(new ErrorMessage("product.benefitcomponent.duplicate.sendToTest"));//FIXME Remove hardcoded
					}
					if (WebConstants.STANDARD_TYPE_CAP.equals(request.getProduct().getProductType()))
					{
						if (!isMandatoryBenefitComponentsAssociated(product.getProductKey()))
						{
							valid = false;
							messageList.add(new ErrorMessage("product.gen.benf.mandatory.sendToTest"));//FIXME Remove hardcoded
						}
					}
				}
			}
			break;
			
			case SaveProductRequest.PUBLISH_PRODUCT:
				break;
			
			case SaveProductRequest.SCHEDULE_FOR_APPROVAL_PRODUCT:
				break;
			
			case SaveProductRequest.TEST_FAIL_PRODUCT:
				break;
			
			case SaveProductRequest.TEST_PASS_PRODUCT:
				break;
			case SaveProductRequest.SCHEDULE_TO_PRODUCTION:
				break;
			case SaveProductRequest.APPROVE_PRODUCT:
				break;
			case SaveProductRequest.REJECT_PRODUCT:
				break;
			case SaveProductRequest.CHECKOUT_PRODUCT:
				break;
			case SaveProductRequest.UNLOCK_PRODUCT:
				break;
			
			default:
				throw new ServiceException("Unknown Action in request", null, null);
			}
			
			response.setValid(valid);
			response.setMessages(messageList);
			Logger.logInfo("Returning  from execute method of validation  for request=" + request.getClass().getName());
			return response;
		}
		catch (SevereException ex)
		{
			throw new ServiceException("Exception occured while BOM call", null, ex);
		}
	}
	/*
	 * Check In validation
	 *
	 * validating Atleast one benefit in all  benefit component at the time of check in
	 *
	 *
	 */
	
	private ErrorMessage checkValidBenefitComponent(ProductBO product) throws SevereException
	{
		boolean status = false;
		List messageList = new ArrayList();
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List bclist = adapterManager.getBenefitComponentsList(product.getProductKey(),false);
		ErrorMessage message = null;
		if (null != bclist && bclist.size() != 0)
		{
			Iterator bcitr = bclist.iterator();
			while (bcitr.hasNext())
			{
				ProductComponentBO productComponentBO = (ProductComponentBO) bcitr.next();
				List bcDetalilist = adapterManager.getBenefitComponentValues(product.getProductKey(), productComponentBO.getComponentKey());
				if (null != bcDetalilist && bcDetalilist.size() > 0)
				{
					status = true;
				}
				else
				{
					status = false;
					message = new ErrorMessage(BusinessConstants.BENEFIT_COMPONENT_MANDATORY_BENEFIT);
					message.setParameters(new String[]
													 { productComponentBO.getComponentDesc() });
					// messageList.add(message);
					break;
				}
			}
		}
		
		return message;
		
	}
	
	/*
	 * select and check whether the reference added to the lines are unique
	 *
	 * checking unique reference validation in product line and questions
	 *
	 */
	
	private ErrorMessage isUnique(ProductBO product) throws SevereException
	{
		//	boolean status = false;
		List newbenLvlList = null;
		List messageList = new ArrayList();
		List refList = null;
		List refBOList = null;
		int refBOListSize = 0;
		boolean status =true;
		ErrorMessage message =null;
		List duplicateRefList =null;
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		duplicateRefList = adapterManager.getDuplcateAdminRef(product.getProductKey());
		if(null==duplicateRefList || duplicateRefList.size()==0){
			
			duplicateRefList = adapterManager.getDuplcateAdminQuestRef(product.getProductKey());
		}
		if(null!= duplicateRefList && duplicateRefList.size()>0)
		{
			message = new ErrorMessage(BusinessConstants.MSG_PRDCT_CHECKIN_INVALID_REFERENCE_NOT_UNIQUE);
			message.setLink("productDuplicateReferenceView");
			
		}
		return message;
	}
	
	
	/**
	 * This method checks the key values have been changed.
	 * @param product
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	private ProductBO retrieveProduct(ProductBO product, User user) throws SevereException
	{
		try
		{
			return (ProductBO) getBusinessObjectManager().retrieve(product, user);
		}
		catch (WPDException ex)
		{
			throw new SevereException("Exception occured while BOM call", null, ex);
		}
		
	}
	
	
	/**
	 * This method checks whether the key values have been changed
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	private boolean isKeyValuesChanged(SaveProductRequest request) throws SevereException
	{
		ProductBO oldProduct = getProductBO(request);
		ProductVO productVO = request.getProduct();
		ProductBO modifiedProduct = new ProductBO();
		setValuesToProductBO(productVO, modifiedProduct);
		if (!oldProduct.getProductName().equalsIgnoreCase(modifiedProduct.getProductName()))
			return true;
		if (!BusinessUtil.isBuisnessDomainsEqual(oldProduct.getBusinessDomains(), modifiedProduct.getBusinessDomains()))
			return true;
		return false;
	}
	
	
	/**
	 * This method checks the product component's duplicate
	 * 
	 * @param productKey
	 * @param compKey
	 * @return
	 * @throws SevereException
	 */
	public static boolean isProductComponentDuplicate(int productKey, int compKey) throws SevereException
	{
		
		ProductComponentBO productComponentBO = new ProductComponentBO();
		productComponentBO.setProductKey(productKey);
		productComponentBO.setComponentKey(compKey);
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		if (null == adapterManager.retrieve(productComponentBO))
		{
			return false;
		}
		return true;
		
	}
	
	
	/**
	 * This method checks whether the product structure is modified or not
	 * 
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	public boolean isProductStructureModified(ProductBO product) throws SevereException
	{
		boolean valid = false;
		ProductBO productInDB = new ProductBO();
		productInDB.setProductKey(product.getProductKey());
		ProductAdapterManager productAdapterManager = new ProductAdapterManager();
		//calls the retrieve method
		productAdapterManager.retrieve(productInDB);
		if (null != productInDB.getProductStructureKey() && Integer.parseInt(product.getProductStructureKey()) > 0)
		{
			if (Integer.parseInt(productInDB.getProductStructureKey()) != Integer.parseInt(product.getProductStructureKey()))
			{
				valid = true;
			}
			else
			{
				valid = false;
			}
		}
		else
		{
			valid = false;
		}
		return valid;
	}
	
	
	/**
	 * This mehtod returns the product sttructure
	 *
	 * @param productStructreId
	 * @return
	 * @throws SevereException
	 */
	/*
	 private ProductStructureBO retrieveProductStructure(int productStructreId)
	 throws SevereException {
	 boolean valid = true;
	 ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
	 ProductStructureBO productStructureBO = new ProductStructureBO();
	 productStructureBO.setProductStructureId(productStructreId);
	 try {
	 builder.retrieve(productStructureBO);
	 } catch (WPDException e) {														//FIXME Remove commented
	 throw new ServiceException(null, null, null);
	 }
	 return productStructureBO;
	 }
	 */
	
	/**
	 * This method checks product component is associated
	 * 
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	private boolean isProductComponentAssociated(ProductBO product) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List list = adapterManager.getBenefitComponentsList(product.getProductKey(),false);
		if (list.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
	/**
	 * This method checks a product is duplicate or not.returns a boolean
	 * 
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	public static boolean isProductDuplicate(ProductBO product) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List duplicateList = adapterManager.getDuplicateProducts(product
				.getProductName(),
				product.getBusinessDomains(), product
				.getParentProductKey());
		if (duplicateList != null && duplicateList.size() > 0)
			return true;
		return false;
		
	}
	
	
	/**
	 * This mehtod is for getting the business object manager
	 * 
	 * @return
	 */
	private static BusinessObjectManager getBusinessObjectManager()
	{
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
	
	
	/**
	 * This method checks whether the product structure is valid or not.
	 * 
	 * @param product
	 * @param messages
	 * @return
	 * @throws SevereException
	 */
	private boolean isProductStructureValid(ProductBO product, List messages) throws SevereException
	{
		boolean valid = true;
		//  ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
		ProductStructureBO productStructureBO = new ProductStructureBO();
		productStructureBO.setProductStructureId(Integer.parseInt(product.getProductStructureKey()));
		try
		{
			//retrieves the product structure
			builder.retrieve(productStructureBO);
		}
		catch (WPDException e)
		{
			throw new ServiceException(null, null, e);
		}
		//adapterManager.retrieveByProductId(productStructureBO);
		//checks domain valid
		if (!isDomainValid(product, productStructureBO))
		{
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_DOMAIN_INVALID));
		}
		//checks effective period valid
		if (!isEffectivePeriodValid(product, productStructureBO))
		{
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_EFCTV_PERIOD_INVALID));
		}
		//checks whether the product structure is in marked for deletion status - this functionality is just for copy.
		if(isProductStuctureDeleted(productStructureBO.getProductStructureId())){
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_STR_MKD_FOR_DLTD));
		}
		//checks whether the product structure is latest publishes version
		else if (!isLatestPublishedVersion(productStructureBO.getProductStructureId())){
			if (product.getAction() == 4){
				// valid = true;
				messages.add(new InformationalMessage(BusinessConstants.MSG_PRODUCT_LATEST_VERSION_AVLBL));
			}      
		}
		
		return valid;
	}
	//check date range while Saving a product
	private boolean isValidDateRange(ProductBO product, List messages) throws SevereException
	{
		boolean valid = true;
		ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
		ProductStructureBO productStructureBO = new ProductStructureBO();
		productStructureBO.setProductStructureId(Integer.parseInt(product.getProductStructureKey()));
		try
		{
			//retrieves the product structure
			builder.retrieve(productStructureBO);
		}
		catch (WPDException e)
		{
			throw new ServiceException(null, null, e);
		}
		//  checks effective period valid
		if (!isEffectivePeriodValid(product, productStructureBO))
		{
			valid = false;
			messages.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_EFCTV_PERIOD_INVALID));
		}
		return valid;
	}
	
	/**
	 * This method checks whether the product structure is of latest published
	 * version
	 * 
	 * @param productStructureId
	 * @return
	 * @throws SevereException
	 */
	private boolean isLatestPublishedVersion(int productStructureId) throws SevereException
	{
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		ProductStructureBO productStructureBO = adapterManager.retrieveLatestPublishedVersion(productStructureId);//FIXME Use builder
		if (productStructureId != productStructureBO.getProductStructureId())
			return false;
		
		return true;
	}
	
	/**
	 * This method checks whether the product structure is of status marked for deletion
	 * version
	 * 
	 * @param productStructureId
	 * @return
	 * @throws SevereException
	 */
	private boolean isProductStuctureDeleted(int productStructureId) throws SevereException
	{
		ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
		ProductStructureBO productStructureBO = adapterManager.retrieveLatestPublishedVersion(productStructureId);//FIXME Use builder
		if(null != productStructureBO){
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * this smethod checks whether the manadtory benfits associated
	 * 
	 * @param productKey
	 * @return
	 * @throws SevereException
	 */
	private boolean isMandatoryBenefitComponentsAssociated(int productKey) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List componentList = adapterManager.getAssociatedMandatoryBenefits(productKey);//FIXME Use builder
		if (componentList != null && componentList.size() > 0)
			return true;
		return false;
	}
	
	/**
	 * this smethod checks whether the manadtory General Provision associated
	 * 
	 * @param productKey
	 * @return
	 * @throws SevereException
	 */
	private boolean isMandatoryGenProvisionAssociated(int productKey) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List componentList = adapterManager.getMandatoryGenProvision(productKey);//FIXME Use builder
		if (componentList != null && componentList.size() > 0)
			return true;
		return false;
	}
	
	/**
	 * This method checks whether the rules are valid.
	 * 
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	// Start of Check In Validation CR  
	//private boolean isValidrule(ProductBO product) throws SevereException  
	private List retrieveDeletedRules (ProductBO product) throws SevereException
	// End of Check In Validation CR   
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List productRulesList = adapterManager.getProductRulesList(product);
		//  Start of Check In Validation CR     
		/*if (productRulesList != null && productRulesList.size() > 0)
		 return true;
		 return false;*/
		return productRulesList;
		//  End of Check In Validation CR
	}
	
	/**
	 * This method checks whether the product structure of effective time period
	 * 
	 * @param product
	 * @param productStructure
	 * @return
	 */
	private boolean isEffectivePeriodValid(ProductBO product, ProductStructureBO productStructure)
	{
		Date prodEffDate = product.getEffectiveDate();
		Date prodExpDate = product.getExpiryDate();
		Date prodStrEffDate = productStructure.getEffectiveDate();
		Date prodStrExpDate = productStructure.getExpiryDate();
		boolean valid = true;
		
		if (prodStrEffDate.after(prodEffDate) || prodExpDate.after(prodStrExpDate))
		{
			valid = false;
		}
		return valid;
	}
	
	
	/**
	 * checks whether the domain is valid
	 * 
	 * @param product
	 * @param productStructure
	 * @return
	 * @throws SevereException
	 */
	private boolean isDomainValid(ProductBO product, ProductStructureBO productStructure) throws SevereException
	{
		List productDomainItems = null;
		List prodStrDomainItems = null;
		
		Iterator iter1 = null;
		Iterator iter2 = null;
		String domainItem1 = null;
		String domainItem2 = null;
		boolean found = false;
		boolean valid = true;
		
		productDomainItems = BusinessUtil.getLobList(product.getBusinessDomains());
		prodStrDomainItems = DomainUtil.getLineOfBusiness(WebConstants.PROD_STRUCT, productStructure.getProductStructureParentId());
		
		lobCheck:
			for (iter1 = productDomainItems.iterator(); iter1.hasNext(); )
			{
				domainItem1 = (String) iter1.next();
				found = false;
				for (iter2 = prodStrDomainItems.iterator(); iter2.hasNext(); )
				{
					domainItem2 = ((DomainItem) iter2.next()).getItemId();
					if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
						break lobCheck;
					if (domainItem1.equals(domainItem2))
					{
						found = true;
						break;
					}
				}
				if (!found)
				{
					valid = false;
					break;
				}
			}
		
		if (valid)
		{
			productDomainItems = BusinessUtil.getbusinessEntityList(product.getBusinessDomains());
			prodStrDomainItems = DomainUtil.getBusinessEntity(WebConstants.PROD_STRUCT, productStructure.getProductStructureParentId());
			beCheck:
				for (iter1 = productDomainItems.iterator(); iter1.hasNext(); )
				{
					domainItem1 = (String) iter1.next();
					found = false;
					for (iter2 = prodStrDomainItems.iterator(); iter2.hasNext(); )
					{
						domainItem2 = ((DomainItem) iter2.next()).getItemId();
						if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
							break beCheck;
						if (domainItem1.equals(domainItem2))
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						valid = false;
						break;
					}
				}
		}
		
		if (valid)
		{
			productDomainItems = BusinessUtil.getBusinessGroupList(product.getBusinessDomains());
			prodStrDomainItems = DomainUtil.getBusinessGroup(WebConstants.PROD_STRUCT, productStructure.getProductStructureParentId());
			bgCheck:
				for (iter1 = productDomainItems.iterator(); iter1.hasNext(); )
				{
					domainItem1 = (String) iter1.next();
					found = false;
					for (iter2 = prodStrDomainItems.iterator(); iter2.hasNext(); )
					{
						domainItem2 = ((DomainItem) iter2.next()).getItemId();
						if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
							break bgCheck;
						if (domainItem1.equals(domainItem2))
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						valid = false;
						break;
					}
				}
		}
		//CARS START
		if (valid)
		{
			productDomainItems = BusinessUtil.getMarketBusinessUnitList(product.getBusinessDomains());
			prodStrDomainItems = DomainUtil.getMarketBusinessUnit(WebConstants.PROD_STRUCT, productStructure.getProductStructureParentId());
			mbuCheck:
				for (iter1 = productDomainItems.iterator(); iter1.hasNext(); )
				{
					domainItem1 = (String) iter1.next();
					found = false;
					for (iter2 = prodStrDomainItems.iterator(); iter2.hasNext(); )
					{
						domainItem2 = ((DomainItem) iter2.next()).getItemId();
						if (BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
							break mbuCheck;
						if (domainItem1.equals(domainItem2))
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						valid = false;
						break;
					}
				}
		}
		//CARS END
		return valid;
	}
	
	
	/**
	 * Method to Set values from ProductVO to ProductBO
	 * 
	 * @param valueObject
	 *            ProductVO object from which values to be taken.
	 * @param businessObject
	 *            ProductBO object to which values to be put.
	 */
	private void setValuesToProductBO(ProductVO valueObject, ProductBO businessObject)
	{
		businessObject.setProductName(valueObject.getProductName());
		businessObject.setProductDesc(valueObject.getProductDesc());
		businessObject.setProductStructureKey(new Integer(valueObject.getProductStructureKey()).toString());
		businessObject.setEffectiveDate(valueObject.getEffectiveDate());
		businessObject.setExpiryDate(valueObject.getExpiryDate());
		businessObject.setProductFamilyId(valueObject.getProductFamilyId());
		businessObject.setBusinessDomains(valueObject.getBusinessDomains());
	}
	
	
	/**
	 * 
	 * @param productRequest
	 * @return
	 * @throws ServiceException
	 */
	private ProductBO getProductBO(ProductRequest productRequest) throws ServiceException
	{
		ProductBO productBO = new ProductBO();
		ProductKeyObject productKeyObject = productRequest.getProductKeyObject();
		if (productKeyObject == null)
		{
			List list = new ArrayList();
			list.add(productRequest);
			throw new ServiceException("ProductKeyObject not found in the request.", list, null);
		}
		productBO.setProductKey(productKeyObject.getProductId());
		productBO.setProductName(productKeyObject.getProductName());
		productBO.setVersion(productKeyObject.getVersion());
		productBO.setParentProductKey(productKeyObject.getParentId());
		productBO.setStatus(productKeyObject.getStatus());
		productBO.setBusinessDomains(productKeyObject.getBusinessDomains());
		productBO.setProductType(productKeyObject.getProductType());
		return productBO;
	}
	
	
	/**
	 * checks if there are benefit components with same sequence number as the
	 * checked in benefit component.
	 */
	private boolean duplicatesPresent(ProductBO product) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List list = adapterManager.getDuplicateComponentsList(product.getProductKey());
		if (list.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * This method checks a Product Rules is duplicate or not.returns a boolean.
	 * @param productRuleAssociation
	 * @param originalList
	 * @return
	 * @throws SevereException
	 */
	public static boolean isProductRulesDuplicate(ProductRuleAssociation productRuleAssociation, List originalList) throws SevereException
	{
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		
		List searchList = adapterManager.getDuplicateProductRules(productRuleAssociation);
		//     List duplicateList = new ArrayList();//FIXME Remove commented code
		if (null == searchList || 0 == searchList.size() )
		{
			productRuleAssociation.setRulesList(originalList);
			return false;
		}
		else if (null !=searchList   && searchList.size() == (originalList.size() / 2))
		{
			return true;
		}
		else
		{
			int index = 0;
			Iterator searchListItr = searchList.iterator();
			while (searchListItr.hasNext())
			{
				index = originalList.lastIndexOf(new String(((ProductRuleAssociationBO) searchListItr.next()).getGenRuleID()));
				if (index != -1)
				{
					try
					{
						originalList.remove(index);
						originalList.remove(index - 1);
					}
					catch (IndexOutOfBoundsException iobe)
					{
					}
				}
			}
			productRuleAssociation.setRulesList(originalList);
			return false;
		}
	}
	
	/**
	 * Method to Set values from SaveProductRulesRequest to ContractPricingInfo
	 * @param saveProductRulesRequest
	 * @param businessObject
	 */
	private void setValuesToProductRules(SaveProductRulesRequest saveProductRulesRequest, ProductRuleAssociation businessObject)
	{
		List rulesList = new ArrayList(saveProductRulesRequest.getRulesList());
		
		for (int i = rulesList.size() - 2; i >= 0; i -= 2)
		{
			rulesList.remove(i);
		}
		businessObject.setProductKey(saveProductRulesRequest.getProductKeyObject().getProductId());
		businessObject.setRulesList(rulesList);
	}
	
	/**
	 * The method validates whether an already used Tier is removed
	 * @param checkProductTierDefnRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(CheckProductTierDefnRequest checkProductTierDefnRequest)
	  throws ServiceException {
	    boolean isPresent = true;
	      Logger
	      .logInfo("ProductBusinessValidationService - Entering execute(): " +
	      		"CheckProductTierDefnRequest");
	      ProductBenefitGeneralInformationVO productTierDefinitionVO 
	      = checkProductTierDefnRequest.getProductTierDefinitionVO();
	      List tierDefnsList = productTierDefinitionVO.getTierDefinitionsList();
	      ProductBusinessObjectBuilder pdktBusinessObjectBuilder 
	      = new ProductBusinessObjectBuilder();      
	      ProductBenefitTierValidationResponse checkProductTierDefnResponse = 
	          (ProductBenefitTierValidationResponse) WPDResponseFactory
	      .getResponse(WPDResponseFactory.PRODUCT_BNFT_TIER_VALIDN_RESPONSE);
	      ProductBenefitDefinitionLocateCriteria pdktBnftDefnLocateCriteria = 
	          new ProductBenefitDefinitionLocateCriteria();
	     try {          
	          pdktBnftDefnLocateCriteria.setProductId(productTierDefinitionVO.
	                  getProductId());	          
	          pdktBnftDefnLocateCriteria.setBenefitComponentId(productTierDefinitionVO.
	                  getBenefitComponentId());
	          pdktBnftDefnLocateCriteria.setBenefitId(productTierDefinitionVO.
	                  getBenefitId());
	          pdktBnftDefnLocateCriteria.setProductStructureId(productTierDefinitionVO.
	                  getProductStructureId());	          	
			  List associatedTierDefnList = pdktBusinessObjectBuilder
			  .getTiersAddedInProduct(
			          pdktBnftDefnLocateCriteria,checkProductTierDefnRequest.getUser());		
			  if(null!= associatedTierDefnList && associatedTierDefnList.size()>0) {
			      Iterator pdktTierDefnIterator = associatedTierDefnList.iterator();				    	   
				    while(pdktTierDefnIterator.hasNext()){		    	          
				        ProductTierDefnOverrideBO productTierDefnOverrideBO = 
				            (ProductTierDefnOverrideBO)pdktTierDefnIterator.next(); 
				       if(!tierDefnsList.contains(new Integer(productTierDefnOverrideBO
				               .getTierDefinitionId()))){
				           isPresent = false;				        
				       }
				    }
			  }
			  if(isPresent){
				  checkProductTierDefnResponse.setValidationSuccess(true);	
			  }else{
		         checkProductTierDefnResponse.addMessage(new
			              ErrorMessage(BusinessConstants.MSG_PRODUCT_TIER_SAVEINVALID));
		         checkProductTierDefnResponse.setValidationSuccess(false);			  			
			  }
	     } catch (WPDException e) {
	            List logParameters = new ArrayList(1);
	      	    logParameters.add(checkProductTierDefnRequest);
	      	    String logMessage = "Failed while processing CheckProductTierDefnRequest";
	      	    throw new ServiceException(logMessage, logParameters, e);
	     }
	      Logger
	      .logInfo("ProductBusinessValidationService - Returning execute(): " +
	      		"CheckProductTierDefnRequest");
	      return checkProductTierDefnResponse;
	  }
	/**
	 * validate benefit components like at least one benefit component is associated, 
	 * check for benefit component with zero visible benefit,
	 * check for mandatory benefit components etc.
	 * @param product
	 * @return
	 * @throws SevereException
	 */
	private List validateBenefitComponenets(ProductBO product)throws SevereException {
		List messageList = new ArrayList();
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List benefitComponentList = adapterManager.getBenefitComponentsList(product.getProductKey(),false);
		boolean benefitComponenetAssociated = this.isBenefitComponentAssociated(benefitComponentList);
		if(!benefitComponenetAssociated){
			messageList.add(new ErrorMessage(BusinessConstants.MSG_PRDCT_CHECKIN_INVALID));
		}
		List mandatoryBenefitAssociatedErrorList = this.checkForMandatoryBenefitsAssociated(benefitComponentList);
		if(mandatoryBenefitAssociatedErrorList.size() >0){
			messageList.addAll(mandatoryBenefitAssociatedErrorList);
		}
		List bcWithZeroVisibleBenefits = this.getBenefitComponentWithZeroVisibleBenefits(product.getProductKey(),
				populateBenefitComponenetIds(benefitComponentList));
		ErrorMessage errorMessageForZeroVisibleBenefit = this.populateErrorMessageForZeroVisbleBenefits(bcWithZeroVisibleBenefits);
		if(errorMessageForZeroVisibleBenefit != null){
			messageList.add(errorMessageForZeroVisibleBenefit);
		}
		return messageList;
		
	}
	/**
	 * populate error message for benefit component with zero visible benefits.
	 * @param bcWithZeroVisibleBenefits
	 * @return
	 */
	private ErrorMessage populateErrorMessageForZeroVisbleBenefits(List bcWithZeroVisibleBenefits) {
		if(null != bcWithZeroVisibleBenefits && bcWithZeroVisibleBenefits.size() >0){
			ErrorMessage message = null;
			for(int i = 0; i < bcWithZeroVisibleBenefits.size(); i++) {
				ProductComponentBO productComponentBO = (ProductComponentBO) bcWithZeroVisibleBenefits.get(i);
				message = new ErrorMessage(BusinessConstants.BENEFIT_COMPONENT_MANDATORY_BENEFIT);
				message.setParameters(new String[]
												 { productComponentBO.getComponentDesc() });
				break;
				
			}
			return message;
		} else {
			return null;
		}
	}
	/**
	 * check whether at least one benefit component is associated or not.
	 * @param benefitComponenetList
	 * @return
	 */
	private boolean isBenefitComponentAssociated(List benefitComponenetList){
		if(null != benefitComponenetList && benefitComponenetList.size() > 0){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * populate benefit component ids.
	 * @param benefitComponentList
	 * @return
	 */
	private List populateBenefitComponenetIds(List benefitComponentList){
		List bcIds = new ArrayList();
		if(null != benefitComponentList && benefitComponentList.size() > 0){
			for(int i = 0; i < benefitComponentList.size(); i++) {
				ProductComponentBO pc =(ProductComponentBO) benefitComponentList.get(i);
				bcIds.add(new Integer(pc.getComponentKey()));
			}
			return bcIds;
		}
		return bcIds;
	}
	
	/**
	 * Check whether mandatory benefit component is associated or not. 
	 * @param benefitComponentList
	 * @return
	 */
	private List checkForMandatoryBenefitsAssociated(List benefitComponentList){
		boolean isgeneralBneAttached = false;
		boolean isGeneralProAttached = false;
		List messageList = new ArrayList();
		if(!(null != benefitComponentList && benefitComponentList.size() > 0)){
			
		} else {
			
			for(int i = 0; i < benefitComponentList.size(); i++) {
				ProductComponentBO pc =(ProductComponentBO) benefitComponentList.get(i);
				if(pc != null){
					if("GENERAL BENEFITS".equalsIgnoreCase(pc.getComponentDesc())){
						isgeneralBneAttached = true;
					} else if("GENERAL PROVISIONS".equalsIgnoreCase(pc.getComponentDesc())) {
						isGeneralProAttached = true;
					}
					if(isgeneralBneAttached && isGeneralProAttached){
						break;
					}
				}
			}
			if(!isgeneralBneAttached){
				messageList.add(new ErrorMessage(BusinessConstants.GENERAL_BENEFIT_MANDATORY));
			}
			if(!isGeneralProAttached){
				messageList.add(new ErrorMessage(BusinessConstants.GENERAL_PROVISIONS_MANDATORY));
			}
		}
		return messageList;
	}
	
	/**
	 * get benefit component with zero visible benefit.
	 * @param productId
	 * @param benefitComponentIds
	 * @return
	 * @throws SevereException
	 */
	private List getBenefitComponentWithZeroVisibleBenefits(int productId, List benefitComponentIds) throws SevereException {
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		List benefitComponentList = adapterManager.retrieveBenefitComponentWithZeroVisibleBenefits(productId, benefitComponentIds);
		return benefitComponentList;
	}
	
	 
}
