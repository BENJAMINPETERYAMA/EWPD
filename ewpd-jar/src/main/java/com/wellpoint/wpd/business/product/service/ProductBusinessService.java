/*
 * ProductBusinessService.java
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.UncategorizedSQLException;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.lookup.locateCriteria.NotesLookUpLocateCriteria;
import com.wellpoint.wpd.business.notes.builder.NotesAttachmentBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.locatecriteria.AdminLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ComponentLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductAdministrationLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitAdminLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductLocatePreviousVersionsCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductNotesLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductRuleRefDataLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductStructureLocateCriteria;
import com.wellpoint.wpd.business.pva.builder.PVABusinessObjectBuilder;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodSynchronousValidationRequest;
import com.wellpoint.wpd.common.adminmethod.request.GeneralBenefitAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodSynchronousValidationResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionHideVO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.security.domain.UserImpl;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.ContractProductAONotesAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.ProductNotes;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ProductAONotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.ProductAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.ProductBenefitCustomizedBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierNoteOverRide;
import com.wellpoint.wpd.common.override.benefit.vo.ProductBenefitCustomizedVO;
import com.wellpoint.wpd.common.product.bo.AdminBO;
import com.wellpoint.wpd.common.product.bo.EntityBenefitAdministrations;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.bo.EntityProductBenefitAdministration;
import com.wellpoint.wpd.common.product.bo.HideAdminOptionBO;
import com.wellpoint.wpd.common.product.bo.ProductAdmin;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductBenefitDefinitions;
import com.wellpoint.wpd.common.product.bo.ProductBenefitGeneralInfoBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentRule;
import com.wellpoint.wpd.common.product.bo.ProductComponents;
import com.wellpoint.wpd.common.product.bo.ProductEntityBenefitAdmin;
import com.wellpoint.wpd.common.product.bo.ProductQuestionnaireAssnBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociationBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleIdBO;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.bo.SaveProductQuestionareBO;
import com.wellpoint.wpd.common.product.request.DeleteProductAdminRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductBenefitNoteRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductNoteRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductRequest;
import com.wellpoint.wpd.common.product.request.DeleteProductRuleRequest;
import com.wellpoint.wpd.common.product.request.HideProductAdminOptionRequest;
import com.wellpoint.wpd.common.product.request.LoadProductBenefitNoteRequest;
import com.wellpoint.wpd.common.product.request.LoadProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.request.ProductBenefitLineOverrideNoteRequest;
import com.wellpoint.wpd.common.product.request.ProductBenefitMandateRetrieveRequest;
import com.wellpoint.wpd.common.product.request.ProductQuestionNoteProcessRequest;
import com.wellpoint.wpd.common.product.request.ProductQuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.product.request.ProductQuestionTierNoteProcessRequest;
import com.wellpoint.wpd.common.product.request.ProductQuestionTierNotesPopUpRequest;
import com.wellpoint.wpd.common.product.request.ProductRequest;
import com.wellpoint.wpd.common.product.request.ProductRuleRefDataRequest;
import com.wellpoint.wpd.common.product.request.QuestionDeleteRequest;
import com.wellpoint.wpd.common.product.request.RetreiveProductRuleTypeRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductAdminOptionRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductAdminRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitAdminRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitDefinitionRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductComponentHierarchyRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductNoteRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductQuestionareRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductQuestionnairRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductTierDefnRequest;
import com.wellpoint.wpd.common.product.request.RetrieveValidProductStructuresRequest;
import com.wellpoint.wpd.common.product.request.SaveProductAdminRequest;
import com.wellpoint.wpd.common.product.request.SaveProductAdministrationRequest;
import com.wellpoint.wpd.common.product.request.SaveProductBenefitAdminRequest;
import com.wellpoint.wpd.common.product.request.SaveProductBenefitDefinitionRequest;
import com.wellpoint.wpd.common.product.request.SaveProductBenefitNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRuleInformationRequest;
import com.wellpoint.wpd.common.product.request.SaveProductNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRulesRequest;
import com.wellpoint.wpd.common.product.request.SearchProductRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductAdministrationRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductBenefitGeneralInformationRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductQuestionareRequest;
import com.wellpoint.wpd.common.product.response.DeleteProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductNoteResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductResponse;
import com.wellpoint.wpd.common.product.response.DeleteProductRuleResponse;
import com.wellpoint.wpd.common.product.response.HideProductAdminOptionResponse;
import com.wellpoint.wpd.common.product.response.LoadProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.LoadProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.ProductAdminDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductAdminResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitMndateRetrieveResponse;
import com.wellpoint.wpd.common.product.response.ProductLineOverrideNotesResponse;
import com.wellpoint.wpd.common.product.response.ProductQuestionNoteResponse;
import com.wellpoint.wpd.common.product.response.ProductQuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.product.response.ProductRuleRefDataResponse;
import com.wellpoint.wpd.common.product.response.QuestionDeleteResponse;
import com.wellpoint.wpd.common.product.response.RetreiveProductRuleTypeResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductAdminOptionResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitAdminResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductComponentHierarchyResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductQuestionareResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductTierDefnResponse;
import com.wellpoint.wpd.common.product.response.RetrieveValidProductStructuresResponse;
import com.wellpoint.wpd.common.product.response.SaveProductAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitAdministrationResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentRuleInformationResponse;
import com.wellpoint.wpd.common.product.response.SaveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductRulesResponse;
import com.wellpoint.wpd.common.product.response.SearchProductResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductBenefitGeneralInformationResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductQuestionareResponse;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.product.vo.ProductAdminVO;
import com.wellpoint.wpd.common.product.vo.ProductBenefitAdminOverrideVO;
import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;
import com.wellpoint.wpd.common.product.vo.ProductComponentVO;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;
import com.wellpoint.wpd.common.product.vo.ProductSearchCriteriaVO;
import com.wellpoint.wpd.common.product.vo.ProductVO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministration;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitTierDefinitionVO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.common.tierdefinition.request.DeleteLevelFromTierRequest;
import com.wellpoint.wpd.common.tierdefinition.request.ProductTierDefSaveRequest;
import com.wellpoint.wpd.common.tierdefinition.request.ProductTierDeleteRequest;
import com.wellpoint.wpd.common.tierdefinition.request.TierDefinitionRetrieveRequest;
import com.wellpoint.wpd.common.tierdefinition.response.DeleteLevelFromTierResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDeleteResponse;
import com.wellpoint.wpd.common.tierdefinition.response.TierDefinitionRetrieveResponse;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBusinessService extends WPDBusinessService
{

	boolean validateQuestionPVA;
  /**
   * Service method for Saving Overrided Admin options.
   * 
   * @param request
   *            SaveProductBenefitAdminRequest
   * @return SaveProductBenefitAdminResponse
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductBenefitAdminRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      //gets the response
      SaveProductBenefitAdminResponse saveProductResponse = (SaveProductBenefitAdminResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_BENEFIT_ADMIN_RESPONSE);
      List messageList = null;
      List list = request.getBenefitAdministrationVOList();
     
      Iterator iterBenefitAdmin = list.iterator();
      ProductBenefitAdminOverrideVO productBenefitAdminOverrideVO = null;
      EntityBenefitAdministration benefitAdministration = null;
      ProductBO product = getProductBO(request);
      int productId = request.getProductKeyObject().getProductId();
      product.setProductKey(productId);
      List benefitList =null;
      if(null!=list){
      	int listSize = list.size();	
      	benefitList = new ArrayList(listSize);
      }
      while (iterBenefitAdmin.hasNext())
      {
        productBenefitAdminOverrideVO = new ProductBenefitAdminOverrideVO();
        productBenefitAdminOverrideVO = (ProductBenefitAdminOverrideVO) iterBenefitAdmin.next();

        benefitAdministration = new EntityBenefitAdministration();
        benefitAdministration.setQuestionNumber(productBenefitAdminOverrideVO.getQuestionId());
        benefitAdministration.setAnswerId(productBenefitAdminOverrideVO.getAnswerId());
        /* enhancement starts here */
        //setting the QuestinHideFlag value to BO
        benefitAdministration.setQstnHideFlag(productBenefitAdminOverrideVO.getQuestionHideFlag());
        if (request.getAdminOptionHideFlag().equals(BusinessConstants.HIDE_FLAG_T))
        {
          benefitAdministration.setAdminOptionHideFlag(productBenefitAdminOverrideVO.getQuestionHideFlag());
        }
        else
        {
          benefitAdministration.setAdminOptionHideFlag(BusinessConstants.HIDE_FLAG_F);
        }
        /* enhancement ends here */
        benefitAdministration.setEntityId(product.getProductKey());

        benefitAdministration.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
        benefitAdministration.setBeneftComponentId(request.getBenefitComponentId());


        benefitAdministration.setAdminLevelOptionAssnId(request.getAdminLevelOptionAssnId());

        benefitList.add(benefitAdministration);
      }
      //getting business object manager
      BusinessObjectManager bom = getBusinessObjectManager();
      EntityBenefitAdministrations benefitBO = new EntityBenefitAdministrations();
      benefitBO.setBenefitAdministrationList(benefitList);
      User user = request.getUser();
      ProductStructureBenefitAdministration productStructureBenefitAdministration = new ProductStructureBenefitAdministration();
      //enhancement
      if (request.getAdminOptionHideFlag().equals(BusinessConstants.HIDE_FLAG_T))
      {
        productStructureBenefitAdministration.setEnityid(productId);
        productStructureBenefitAdministration.setAdminLevelOptionId(request.getAdminLevelOptionAssnId());
        productStructureBenefitAdministration.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
        productStructureBenefitAdministration.setAdminOptionHideFlag(BusinessConstants.HIDE_FLAG_T);
        bom.persist(productStructureBenefitAdministration, product, user, false);
      }

      //enhancement
      //calls the persist
      bom.persist(benefitBO, product, user, false);
      
      updateAMVForProductBenefitAdministration(request);
      
      messageList = new ArrayList(2);
//      messageList.add(new InformationalMessage(BusinessConstants.QUESTION_OVERRIDDEN_UPDATED));
//      saveProductResponse.setMessages(messageList);
      if(request.getAnswerOverrideFlag()){
		messageList.add(new InformationalMessage(
				BusinessConstants.ANSWER_OVERRIDDEN));
      }
      if(request.getHideStatusFlag()){
		messageList.add(new InformationalMessage(
			BusinessConstants.QUESTION_HIDE_STATUS_UPDATED));
		
      }
      addMessagesToResponse(saveProductResponse, messageList);
      ProductBenefitAdminLocateCriteria benefitLocateCriteria = new ProductBenefitAdminLocateCriteria();
      benefitLocateCriteria.setBenefitAdminId(request.getProductBenefitAdminId());
      benefitLocateCriteria.setProductId(productId);
      benefitLocateCriteria.setAdminOptionId(request.getProductAdminOptionId());
      benefitLocateCriteria.setBenefitComponentId(request.getBenefitComponentId());
      //calls locate again
      LocateResults locateResults = bom.locate(benefitLocateCriteria, user);
      saveProductResponse.setBenefitAdministrationList(locateResults.getLocateResults());
      saveProductResponse.setSuccess(true);
      Logger.logInfo("Returning from execute method for request=" + request.getClass().getName());
      return saveProductResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


    /**
	 * @param request
     * @throws ServiceException
	 */
	private void updateAMVForProductBenefitAdministration(SaveProductBenefitAdminRequest request) throws ServiceException {
		
		// removed the admin method validations at product level
//		if(null != request ){
//			if(request.isQstnsChanged()){
//				AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//				validationRqst.setBenefitComponentId(request.getBenefitComponentId());
//				validationRqst.setBenefitAdministrationId(request.getProductBenefitAdminId());
//				validationRqst.setBenefitId(request.getBenefitId());
//				validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//				validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//				validationRqst.setChangedIds(request.getChangedIds());
//				validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION);
//				validationRqst.setBenefitCompName(request.getBenefitComponentName());
//				
//				AdminMethodSynchronousValidationResponse validationResponse = 
//						(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//			}
//		}
		
	}


/**
   * Service method for retrieving Benefit admin overrided values.
   * 
   * @param request
   *            RetrieveProductBenefitAdminRequest
   * @return RetrieveProductBenefitAdminResponse
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductBenefitAdminRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      RetrieveProductBenefitAdminResponse retrieveProductResponse = 
        (RetrieveProductBenefitAdminResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_BENEFIT_ADMIN_RESPONSE);
      int benefitKey = request.getProductBenefitAdminId();

      //getting business object manager
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBenefitAdminLocateCriteria benefitLocateCriteria = new ProductBenefitAdminLocateCriteria();

      benefitLocateCriteria.setBenefitAdminId(benefitKey);
      benefitLocateCriteria.setProductId(request.getProductKeyObject().getProductId());
      benefitLocateCriteria.setAdminOptionId(request.getProductAdminOptionId());
      benefitLocateCriteria.setBenefitComponentId(request.getBenefitComponentId());
      User user = request.getUser();
      //calls the locate
      LocateResults locateResults = bom.locate(benefitLocateCriteria, user);
      retrieveProductResponse.setBenefitAdministrationList(locateResults.getLocateResults());
      retrieveProductResponse.setSuccess(true);
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return retrieveProductResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for Deleting product.
   * 
   * @param request
   *            DeleteProductRequest
   * @return DeleteProductResponse
   * @throws WPDException
   */
  public WPDResponse execute(DeleteProductRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      DeleteProductResponse deleteProductResponse = (DeleteProductResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_RESPONSE);
      List messageList = null;
      User user = request.getUser();
      //setting business object
      ProductBO product = getProductBO(request);
      //getting business object manager
      BusinessObjectManager bom = getBusinessObjectManager();

      bom.delete(product, user);
      InformationalMessage message = new InformationalMessage(BusinessConstants.MSG_PRDCT_DELETE_SUCCESS);
      message.setParameters(new String[]
          { product.getProductName() });
      messageList = new ArrayList(1);
      messageList.add(message);
      deleteProductResponse.setMessages(messageList);
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return deleteProductResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }

  /**
   * Service method for Hiding Admin Option.
   * 
   * @param request
   *            HideProductBenefitRequest
   * @return HideProductBenefitResponse
   * @throws WPDException
   */
  public WPDResponse execute(HideProductAdminOptionRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      HideProductAdminOptionResponse hideProductAdminOptionResponse = (HideProductAdminOptionResponse) WPDResponseFactory.getResponse(WPDResponseFactory.HIDE_PRODUCT_ADMIN_OPTION_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      List messageList = null;
      User user = request.getUser();
      //setting business object
      ProductBO productBO = new ProductBO();
      ProductEntityBenefitAdmin productEntityBenefitAdmin = new ProductEntityBenefitAdmin();
      //populate subObject
      productEntityBenefitAdmin.setAdminLevelOptionAssnId(request.getAdminLevelOptionAssnId());
      productEntityBenefitAdmin.setBenefitComponentId(request.getBenefitComponentId());
      productEntityBenefitAdmin.setEntityId(request.getEntityId());
      productEntityBenefitAdmin.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
      //populate mainObject
      productBO.setProductName(request.getProductKeyObject().getProductName());
      productBO.setBusinessDomains(request.getProductKeyObject().getBusinessDomains());
      productBO.setVersion(request.getProductKeyObject().getVersion());
      //getting business object manager
      bom.delete(productEntityBenefitAdmin, productBO, user);
      InformationalMessage message = new InformationalMessage(BusinessConstants.MSG_PRDCT_ADMINOPTION_DELETE_SUCCESS);
      messageList = new ArrayList(1);
      messageList.add(message);
      hideProductAdminOptionResponse.setMessages(messageList);
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return hideProductAdminOptionResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }

  /**
   * Business Service Method for SaveProduct Operation.
   * 
   * @param request
   *            SaveProductRequest.
   * @return SaveProductResponse.
   * @throws ServiceException
   */
  public WPDResponse execute(SaveProductRequest request)
			throws ServiceException {
		try {
			Logger.logInfo("Save Product Service - Request = { " + request
					+ " }");
			InformationalMessage message = null;
			SaveProductResponse response = (SaveProductResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_PRODUCT_RESPONSE);
			//gets the action
			int action = request.getAction();
			ProductVO productValueObject = request.getProduct();
			BusinessObjectManager bom = getBusinessObjectManager();
			ProductBO product = null;
			User user = request.getUser();
			List messageList = new ArrayList();
			//calls the validation service
			response = (SaveProductResponse) new ValidationServiceController()
					.execute(request);

			if (!response.isValid())
				return response; 
			List oldBusinessEntities = new ArrayList();
			List updatedBusinessEntities = new ArrayList();
			boolean businessEntityChanged = false;
			int noOfBusinessEntities =0;
			switch (action) {
			//creating the product
			case SaveProductRequest.CREATE_PRODUCT:
				product = new ProductBO();
				setValuesToProductBO(productValueObject, product);
				//calls the BusinessObjectManager
            	try{
                    /*
                     * Checking Too many domain combinations
                     */
    				bom.persist(product, request.getUser(), true);
            	}catch(UncategorizedSQLException e){
            		response = null;
            		return response;
            	}
				product = (ProductBO) bom.retrieve(product, user);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_CREATE_SUCCESS));
				response.setSuccess(true);
				response.setProductBO(product);
				break;
			//updates product
			case SaveProductRequest.UPDATE_PRODUCT:

				
				product = getProductBO(request);
				
				
				for(int i=0;i<product.getBusinessDomains().size();i++){
					if (!oldBusinessEntities.contains(((Domain)product.getBusinessDomains().get(i)).getBusinessEntity()))
							oldBusinessEntities.add(((Domain)product.getBusinessDomains().get(i)).getBusinessEntity());
				}
				
				setValuesToProductBO(productValueObject, product);
				if (response.isKeyChanged()) {
					ProductBO oldProduct = getProductBO(request);
					bom.changeIdentity(oldProduct, product, user);
				}
				bom.persist(product, request.getUser(), false);
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_UPDATE_SUCCESS));
				if (response.isProductStructureChanged()) {
				    int psKey = request.getProduct().getProductStructureKey();
				    if(0 != psKey){
				        product.setProductStructureKey(String.valueOf(psKey));
				    }
					bom.delete(new ProductComponents(), product, user);
					messageList.add(new InformationalMessage(
							BusinessConstants.MSG_PRDCT_PROD_STR_MODIFIED));
				}
				
				
				for(int i=0;i<productValueObject.getBusinessDomains().size();i++){
					if (!updatedBusinessEntities.contains(((Domain)productValueObject.getBusinessDomains().get(i)).getBusinessEntity()))
						updatedBusinessEntities.add(((Domain)productValueObject.getBusinessDomains().get(i)).getBusinessEntity());
				}
				
				
				noOfBusinessEntities = updatedBusinessEntities.size();
				if (oldBusinessEntities.size() != noOfBusinessEntities) {
					businessEntityChanged = true;
				} else {
					for (int i = 0; i < noOfBusinessEntities; i++) {
						businessEntityChanged = true;
						for (int j = 0; j < noOfBusinessEntities; j++) {
							if (((String)oldBusinessEntities.get(i)).equals((String)updatedBusinessEntities.get(i))) {
								businessEntityChanged = false;
							}
							if (businessEntityChanged)
								break;
						}
					}
				}
				//if buisienss entity is changed,the pva validation will happen 
				if (businessEntityChanged) {
					PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
					List benefitComponentsList;
					//getting benefit component list 
					benefitComponentsList = locateAssociatedComponents(product
							.getProductKey(), request.getUser());
					if (benefitComponentsList != null
							&& benefitComponentsList.size() > 0) {
						//validate benefit component lines,hide invaalid lines and -
						//returns invalid benefit component list.
						validateQuestionPVA= true;
						builder.doPVAvalidation(product,benefitComponentsList,user,validateQuestionPVA);
//						List InvalidBc = builder.hideInvalidLines(
//								benefitComponentsList, product, user);
//						if (InvalidBc.size() > 0) {
//							//looping invalid bc list and deleting 
//							for(int i=0;i<InvalidBc.size();i++){
//				          		ProductComponentBO componentBO = (ProductComponentBO)InvalidBc.get(i);
//				          		List bcList = new ArrayList();
//				        		bcList.add(new Integer(componentBO.getComponentKey()));
//				        		componentBO.setBenefitComponentDeleteList(bcList);
//				          		 boolean benefitComponentDeleted = getBusinessObjectManager().delete(componentBO, product, request.getUser());
//				        	}
//						}
//						/*
//						 * pva validation for all the question in remaining valid benefit components
//						 */
//						builder.validateQuestions(product,request.getUser());
                		
					}
					messageList.add(new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_PRODUCT_UPDATE_PRODUCTFAMILY));
            		//response.setMessages(messageList);
				}

				response.setSuccess(true);
				product = (ProductBO) bom.retrieve(product, user);
				response.setProductBO(product);
				break;
			case SaveProductRequest.CHECKIN_PRODUCT:
				//              Setting the values for the validation for domain change
				RefDataDomainValidationRequest refDataDomainValidationRequest = getReferenceRequest(request);
				RefDataDomainValidationResponse refDataDomainValidationResponse = (RefDataDomainValidationResponse) new ValidationServiceController()
						.execute(refDataDomainValidationRequest);
				//Setting the error message with the parameter
				//Breaks if validation fails
				if (!refDataDomainValidationResponse.isSuccess()) {
					ErrorMessage errorMessage = new ErrorMessage(
							WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
					errorMessage
							.setParameters(new String[] { " "
									+ refDataDomainValidationResponse
											.getErrorMessage() });
					messageList.add(errorMessage);
					break;
				}
				product = getProductBO(request);
				
				oldBusinessEntities = new ArrayList();
				
				for(int i=0;i<product.getBusinessDomains().size();i++){
					if (!oldBusinessEntities.contains(((Domain)product.getBusinessDomains().get(i)).getBusinessEntity()))
							oldBusinessEntities.add(((Domain)product.getBusinessDomains().get(i)).getBusinessEntity());
				}
				//added for admin method validation
				GeneralBenefitAdminMethodValidationRequest gnrlBenefitAdminMethodRequest = new GeneralBenefitAdminMethodValidationRequest();
				gnrlBenefitAdminMethodRequest.setEntityId(productValueObject
						.getProductKey());
				gnrlBenefitAdminMethodRequest
						.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
				GeneralBenefitAdminMethodValidationResponse gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) WPDResponseFactory
						.getResponse(WPDResponseFactory.ADMIN_METHOD_RESPONSE);
				gnrlBenefitAdminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) (new ValidationServiceController()
						.execute(gnrlBenefitAdminMethodRequest));
				if (null != gnrlBenefitAdminMethodResponse.getMessages()
						&& gnrlBenefitAdminMethodResponse.getMessages().size() > 0) {
					gnrlBenefitAdminMethodResponse.setValidationSuccess(false);
					List errorMessages = gnrlBenefitAdminMethodResponse.getMessages();
					if (response.isValid())
						response.setMessages(gnrlBenefitAdminMethodResponse.getMessages());
					else
						response.addMessage((Message) errorMessages.get(0));
					break;
				}
				if(productValueObject.getBusinessDomains()!=null){
				for(int i=0;i<productValueObject.getBusinessDomains().size();i++){
					if (!updatedBusinessEntities.contains(((Domain)productValueObject.getBusinessDomains().get(i)).getBusinessEntity()))
						updatedBusinessEntities.add(((Domain)productValueObject.getBusinessDomains().get(i)).getBusinessEntity());
				}
				
				
				noOfBusinessEntities = updatedBusinessEntities.size();
				if (oldBusinessEntities.size() != noOfBusinessEntities) {
					businessEntityChanged = true;
				} else {
					for (int i = 0; i < noOfBusinessEntities; i++) {
						businessEntityChanged = true;
						for (int j = 0; j < noOfBusinessEntities; j++) {
							if (((String)oldBusinessEntities.get(i)).equals((String)updatedBusinessEntities.get(i))) {
								businessEntityChanged = false;
							}
							if (businessEntityChanged)
								break;
						}
					}
				}
				}
				if (businessEntityChanged) {
					String productFamily = getProductFamily(request.getProduct().getProductStructureKey());
					product.setProductFamilyId(productFamily);
					PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
					List benefitComponentsList;

					benefitComponentsList = locateAssociatedComponents(product
							.getProductKey(), request.getUser());
					if (benefitComponentsList != null
							&& benefitComponentsList.size() > 0) {
						
						validateQuestionPVA = true;
						builder.doPVAvalidation(product,benefitComponentsList,user,validateQuestionPVA);
					}
					}
				
				if (!request.isCheckIn()) {
					break;
				}
				//calling validation service to find out if any validation
				// errors are there..
				if (gnrlBenefitAdminMethodResponse.isValidationSuccess()) {
						bom.checkIn(product, request.getUser());
						message = new InformationalMessage(
								BusinessConstants.MSG_PRDCT_CHECKIN_SUCCESS);
						message.setParameters(new String[] { request
								.getProduct().getProductName() });
						messageList.add(message);
						response.setSuccess(true);
						response.setProductBO(product);
				}
				break;

			case SaveProductRequest.COPY_PRODUCT:
				//gets the source object
				HashMap hashMap = new HashMap();
				product = getProductBO(request);
				ProductBO srcProduct = getProductBO(request);
				setValuesToProductBO(productValueObject, product);
				bom.persist(product, request.getUser(), true);

				//copies from the source object to the target object
				srcProduct = (ProductBO) bom.retrieve(srcProduct, user);
				bom.copy(srcProduct, product, request.getUser(), hashMap);
				message = new InformationalMessage(
						BusinessConstants.MSG_PRDCT_COPY_SUCCESS);
				message.setParameters(new String[] { srcProduct
						.getProductName() });
				messageList.add(message);
				product = (ProductBO) bom.retrieve(product, user);
				response.setSuccess(true);
				response.setProductBO(product);
				break;

			case SaveProductRequest.SEND_TO_TEST_PRODUCT:
				product = getProductBO(request);
				//schedules the product for test
				bom.scheduleForTest(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.MSG_PRDCT_TEST_SUCCESS);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;

			case SaveProductRequest.PUBLISH_PRODUCT:
				product = getProductBO(request);
				//publishes the product
				bom.publish(product, request.getUser());
				messageList.add(new InformationalMessage(
						BusinessConstants.MSG_PRDCT_PUBLISH_SUCCESS));
				response.setSuccess(true);
				break;

			case SaveProductRequest.SCHEDULE_FOR_APPROVAL_PRODUCT:
				product = getProductBO(request);
				//schedules the product for approval
				bom.scheduleForApproval(product, request.getUser());
				messageList
						.add(new InformationalMessage(
								BusinessConstants.MSG_PRDCT_SCHEDULE_FOR_APPROVAL_SUCCESS));
				response.setSuccess(true);
				break;
			case SaveProductRequest.TEST_PASS_PRODUCT:
				product = getProductBO(request);
				bom.testPass(product, request.getUser());
				//Implicit transition for complex object.
				bom.scheduleForApproval(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.MSG_PRDCT_TEST_PASSED);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;
			case SaveProductRequest.TEST_FAIL_PRODUCT:
				product = getProductBO(request);
				bom.testFail(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.MSG_PRDCT_TEST_FAILURE);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;
			case SaveProductRequest.SCHEDULE_TO_PRODUCTION:
				product = getProductBO(request);
				bom.transfer(product, request.getUser());
				response.setSuccess(true);
				break;
			case SaveProductRequest.APPROVE_PRODUCT:
				product = getProductBO(request);
				//approves the product
				bom.approve(product, request.getUser());
				// Implicit Transition for Product
				bom.publish(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.MSG_APPROVE_SUCCESS);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;
			case SaveProductRequest.REJECT_PRODUCT:
				product = getProductBO(request);
				//rejects the product
				bom.reject(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.MSG_REJECT_SUCCESS);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;
			case SaveProductRequest.UNLOCK_PRODUCT:
				product = getProductBO(request);
				bom.unlock(product, request.getUser());
				message = new InformationalMessage(
						BusinessConstants.PRODUCT_UNLOCKED);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				response.setSuccess(true);
				break;
			case SaveProductRequest.CHECKOUT_PRODUCT:

				product = getProductBO(request);
				product = (ProductBO) bom.checkOut(product, user);
				product = (ProductBO) bom.retrieve(product, user);
				message = new InformationalMessage(
						BusinessConstants.MSG_CHECKOUT_SUCCESS);
				message
						.setParameters(new String[] { product.getProductName() });
				messageList.add(message);
				ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();
				List newMessageList = productBusinessObjectBuilder
						.checkOutMessages(request.getProductKeyObject()
								.getProductId(), product.getProductKey());
				if (null != newMessageList && newMessageList.size() > 0) {
					Iterator newMessageListItr = newMessageList.iterator();
					while (newMessageListItr.hasNext()) {

						messageList
								.add((InformationalMessage) newMessageListItr
										.next());

					}

				}
				response.setSuccess(true);
				response.setProductBO(product);
				break;

			default:
				throw new ServiceException("Unknown Action in request", null,
						null);
			}
			response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
					WebConstants.PROD_TYPE, product.getParentProductKey()));
			addMessagesToResponse(response, messageList);
			Logger.logInfo("Returning  from execute method for request="
					+ request.getClass().getName());
			return response;
		} catch (WPDException ex) {
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		}
	}

	/**
	 * Method to set the values to the request for validation
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
  private RefDataDomainValidationRequest getReferenceRequest(SaveProductRequest request) throws SevereException
  {
    RefDataDomainValidationRequest refDataDomainValidationRequest = new RefDataDomainValidationRequest();

    //Setting the values for the validation for domain change
    SubCatalogVO subCatalogVO = new SubCatalogVO();
    subCatalogVO.setEntityId(request.getProductKeyObject().getParentId());
    subCatalogVO.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
    //Creating the parent catalog name list
    List parentCatalogNameList = new ArrayList(3);
    parentCatalogNameList.add(BusinessConstants.PRODUCT_FAMILY);
    parentCatalogNameList.add(BusinessConstants.STATE_CODE);
    parentCatalogNameList.add(BusinessConstants.PROVIDER_ARRANGEMENTS);
    //Setting the required parameters to the request
    refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);
    refDataDomainValidationRequest.setParentCatalogList(parentCatalogNameList);
    refDataDomainValidationRequest.setSelectedItemMap(getSelectedItemMap(request));
    return refDataDomainValidationRequest;
  }

  /**
   * Method for creating the key value pair for reference data validation
   * 
   * @param request
   * @return
   * @throws SevereException
   */
  private HashMap getSelectedItemMap(SaveProductRequest request) throws SevereException
  {
    HashMap map;
    List locateResults = null;
    List pvaResultList = new ArrayList();
    List pvaItemList = null;
    List productFamilyList =null;
    List stateCodeList = new ArrayList();
    ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
    //Setting the EntityId to the BO
    ProductBO product = new ProductBO();
    product.setProductKey(request.getProductKeyObject().getProductId());

    locateResults = builder.retrieveProductForReferenceValidation(product);
    pvaResultList = builder.retrievePvaForReferenceValidation(product);
    //Creating the required lists for key-value pair
    if (null != locateResults && !locateResults.isEmpty())
    {
    	int listSize = locateResults.size();
    	pvaItemList = new ArrayList(listSize); 
    	productFamilyList = new ArrayList(listSize);
      for (int i = 0; i < listSize; i++)
      {
        ProductBO productBO = (ProductBO) locateResults.get(i);
        if (null != productBO.getProductFamilyId())
          productFamilyList.add(productBO.getProductFamilyId());
        if (null != productBO.getStateId())
          stateCodeList.add(productBO.getStateId());
      }
    }
    if (null != pvaResultList && !pvaResultList.isEmpty())
    {
      for (int i = 0; i < pvaResultList.size(); i++)
      {
        ProductRuleAssociationBO productBO = (ProductRuleAssociationBO) pvaResultList.get(i);
        pvaItemList.add(productBO.getProviderArrangement());
      }
    }
    //Setting the corresponding list to the parent catalog
    map = new HashMap();
    map.put(BusinessConstants.PRODUCT_FAMILY, productFamilyList);
    map.put(BusinessConstants.STATE_CODE, stateCodeList);
    map.put(BusinessConstants.PROVIDER_ARRANGEMENTS, pvaItemList);
    return map;
  }

  /**
   * Service method for Retrieving Benefit Component Information.
   * 
   * @param request
   *            RetrieveProductBenefitComponentRequest.
   * @return ProductBenefitComponentResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductBenefitComponentRequest request) throws ServiceException, AdapterException
  {
	  
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      List benefitComponentsList = null;
      ProductBenefitComponentResponse productBenefitComponentResponse = 
        (ProductBenefitComponentResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_BENEFIT_COMPONENT_RESPONSE);
      ProductAdapterManager productAdapterManager = new ProductAdapterManager();
      int action = request.getAction();

      ProductBO productBO = getProductBO(request);
      ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();
      ProductBenefitLocateCriteria productBenefitLocateCriteria= new ProductBenefitLocateCriteria();
      BusinessObjectManager bom;
      switch (action)
      {
        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_ADDED:
          benefitComponentsList = locateAssociatedComponents(productBO.getProductKey(), request.getUser());
          break;
        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_POPUP:
          benefitComponentsList = productAdapterManager.getBenefitComponentsListforPopup(productBO.getProductKey());
          if (benefitComponentsList == null || benefitComponentsList.size() == 0)
          {
            productBenefitComponentResponse.addMessage(new InformationalMessage(BusinessConstants.MSG_POPUP_CMPNTS_NOT_FOUND));
          }
          break;
        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_COMPONENT_RETRIEVE:

          Map params = new HashMap();
          bom = getBusinessObjectManager();
          //sets benefit component id to the map
          params.put(WebConstants.BENEFIT_COMP_ID, new Integer(request.getBenefitComponentId()));

          productBO = (ProductBO) bom.retrieve(productBO, request.getUser(), params);

          //sets the benefit component details to the response
          productBenefitComponentResponse.setBenefitComponentDetails((BenefitComponentBO) productBO.getComponentList().get(0));
          //sets the domain details to the response
          productBenefitComponentResponse.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(WebConstants.BENEFIT_COMP, 
                                                                                              productBenefitComponentResponse.getBenefitComponentDetails().getBenefitComponentParentId()));
          break;
          // new Case added for retrieving the benefits associated to a product as part of Customization(Hide/Unhide)  
        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE:
          	
          bom = getBusinessObjectManager();
          List benefitDetailsList = null;
         
          //Setting the locate criteria from request.
          productBenefitLocateCriteria.setBenefitComponentId(request.getBenefitComponentId());
          productBenefitLocateCriteria.setProductId(request.getProductId());
          //Call to BOM to retrieve all the visible benefits at the time of load.
          benefitDetailsList = bom.locate(productBenefitLocateCriteria,request.getUser()).getLocateResults();
          //sets the benefit details to the response
          if(null!=benefitDetailsList){
          productBenefitComponentResponse.setBenefitList(benefitDetailsList);
          }
          break;
        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE_ALL_DETAILS:
        	bom = getBusinessObjectManager();
          List benefitAllDetailsList = null;
          //Setting the locate criteria from request.
          productBenefitLocateCriteria.setBenefitComponentId(request.getBenefitComponentId());
          productBenefitLocateCriteria.setProductId(request.getProductId());
          //Variable for 'Show Hidden' to retrieve all the records associated to Product 
          productBenefitLocateCriteria.setBenefitVisibilityStatus(true);
          //Call to BOM
          benefitAllDetailsList = bom.locate(productBenefitLocateCriteria,request.getUser()).getLocateResults();
          //sets the benefit details to the response
          if(null!=benefitAllDetailsList){
          productBenefitComponentResponse.setBenefitList(benefitAllDetailsList);
          }
          break;

        case RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_DETAILS_UPDATE:
          bom = getBusinessObjectManager();
        List benefitComponents = new ArrayList();
          ProductTreeStandardBenefits productAttachedBenefitDetails = new ProductTreeStandardBenefits();
          // Setting all the required values from the request to the BO
          productAttachedBenefitDetails.setBenefitDetailsList(request.getBenefitDetailsList());
          productAttachedBenefitDetails.setBenefitComponentId(request.getBenefitComponentId());
          productAttachedBenefitDetails.setProductId(request.getProductId());
          productAttachedBenefitDetails.setShowHiddenStatus(request.isShowHiddenStatus());
          productAttachedBenefitDetails.setProductType(request.getProductType());
      	/** Setting the benefit hide/unhide flag Map to BO ProductTreeStandardBenefits:: eWPD Stabilization 2011 **/
          productAttachedBenefitDetails.setBenefitComponentHideMap(request
					.getBenefitHideUnhideFlagMap());
          /** end :: eWPD Stabilization 2011 **/
          bom.persist(productAttachedBenefitDetails,productBO,request.getUser(),false);
          ProductComponentBO productComponentBO = new ProductComponentBO();
          productComponentBO.setComponentKey(request.getBenefitComponentId());
          benefitComponents.add(productComponentBO);
          /** removing PVA Validation while hide/unhide**/
			/** if (benefitComponents != null	&& benefitComponents.size() > 0) {
				validate benefit component lines,hide invaalid lines and -
				returns invalid benefit component list.
				validateQuestionPVA= true;
				builder.doPVAvalidation(productBO,benefitComponents,request.getUser(),validateQuestionPVA);
			}  **/
			// Removed the admin method validations at product level.
//          if(productAttachedBenefitDetails.isShowHiddenStatus())
//              updateAMVForBenefits(request);
          
          //Setting the locate criteria for retrieving records on the basis of Show Hidden status.
          productBenefitLocateCriteria.setBenefitComponentId(request.getBenefitComponentId());
          productBenefitLocateCriteria.setProductId(request.getProductId());
          //Variable for 'Show Hidden' to retrieve all the recods associated to a benefit component  
          productBenefitLocateCriteria.setBenefitVisibilityStatus(productAttachedBenefitDetails.isShowHiddenStatus());
      
          //benefitAllDetailsList = productBusinessObjectBuilder.getProductAssociatedBenefitDetails(productAllBenefits);
          List benefitDetailsUpdatedList = bom.locate(productBenefitLocateCriteria,request.getUser()).getLocateResults();
          //sets the benefit component details to the response
          productBenefitComponentResponse.setBenefitList(benefitDetailsUpdatedList);
          List messageList = new ArrayList();
          
          // For deleting data corresponding to tier tables
          productBusinessObjectBuilder.deleteBnftTierDetails(request.getProductId(),request.getBenefitComponentId(),request.getBenefitDetailsList());
         
          messageList.add(new InformationalMessage(BusinessConstants.PRODUCT_BENEFIT_HIDE_STATUS));
          addMessagesToResponse(productBenefitComponentResponse, messageList);

          break;
      }
      productBenefitComponentResponse.setBenefitComponentList(benefitComponentsList);
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      
      return productBenefitComponentResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
 * @param request
 * @throws ServiceException
 */
private void updateAMVForBenefits(RetrieveProductBenefitComponentRequest request) throws ServiceException {
	
	// removed the admin method validations at product level.
//	if(request.isChanged()){
//		AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			validationRqst.setBenefitComponentId(request.getBenefitComponentId());
//			validationRqst.setBenefitCompName(request.getBcompName());
//			validationRqst.setChangedIds(request.getChangedIds());
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BENEFITS_CHANGE_IN_ENTITY);
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);	
//	}
	
}


/**
   * Service method for Deleting product associated benefit Component.
   * 
   * @param request
   *            DeleteProductBenefitComponentRequest
   * @return productBenefitComponentDeleteResponse
   * @throws WPDException
   */
  public WPDResponse execute(DeleteProductBenefitComponentRequest request) throws ServiceException
  {
	 
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      ProductComponentBO productComponentBO = new ProductComponentBO();
      List messageList = new ArrayList(1);
      ProductBO productBO = getProductBO(request);
      productComponentBO.setBenefitComponentDeleteList(request.getBenefitComponentList());
      productComponentBO.setProductKey(productBO.getProductKey());
      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_COMPONENT_DELETED));
      ProductBenefitComponentDeleteResponse productBenefitComponentDeleteResponse = 
        (ProductBenefitComponentDeleteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_BENEFIT_RESPONSE);
      productBenefitComponentDeleteResponse.setMessages(messageList);
      boolean benefitComponentDeleted = getBusinessObjectManager().delete(productComponentBO, productBO, request.getUser());
      ComponentLocateCriteria componentLocateCriteria = new ComponentLocateCriteria();
      componentLocateCriteria.setProductKey(productBO.getProductKey());
      LocateResults locateResults = getBusinessObjectManager().locate(componentLocateCriteria, request.getUser());
      List benefitComponentsList = locateResults.getLocateResults();
      if(null!=benefitComponentsList){
      productBenefitComponentDeleteResponse.setBenefitComponentsList(benefitComponentsList);
      }
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      
      return productBenefitComponentDeleteResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for retrieving Product Information.
   * 
   * @param request
   *            RetrieveProductRequest
   * @return RetrieveProductResponse
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductRequest request) throws ServiceException
  {
    try
    {
      boolean lockAquired = true;
      List messages = new ArrayList(1);
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      RetrieveProductResponse response = (RetrieveProductResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_RESPONSE);
      ProductBO productBO = getProductBO(request);
      BusinessObjectManager bom = getBusinessObjectManager();
      if(request.isEditMode()){
		lockAquired = bom.lock(productBO,request.getUser());
      }
      response.setLockAquired(lockAquired);
      if(lockAquired){
      productBO = (ProductBO) bom.retrieve(productBO, request.getUser());
      response.setProductBO(productBO);
      response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(BusinessConstants.ENTITY_TYPE_PRODUCT, productBO.getParentProductKey()));
      response.setSuccess(true);
      }else{
			messages.add(new InformationalMessage (BusinessConstants.MSG_LOCKED_USER));
			response.setMessages(messages);
		}
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }

  
  /**
   * Service method for Associating benefit component to product.
   * 
   * @param request
   * @return
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductComponentRequest request) throws ServiceException
  {
	 
  	try
	{
  		Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
  		SaveProductComponentResponse response = (SaveProductComponentResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PROD_COMPONENT_RESPONSE);
  		
  		ProductComponentBO productComponentBO = null;
  		int action = request.getAction();
  		ProductBO product = getProductBO(request);
  		int productKey = product.getProductKey();
  		List componentVOList = request.getComponentList();
  		List componentNameList = request.getCompNameList();
  		int listSize = componentVOList.size();
  		Iterator iterComponent = componentVOList.iterator();
  		BusinessObjectManager bom = getBusinessObjectManager();
  		User user = request.getUser();
  		List messageList = null;
  		List componentList =null;
  		List componentListUpdate = null;
  		switch (action)
		{
  		
		case SaveProductComponentRequest.SAVE_BENEFIT_COMPONENT:
			//	validation for whether the component exists or not.if
			// existing , no need to create
			/**removing the validation code since it is obsolete
			response = (SaveProductComponentResponse) new ValidationServiceController().execute(request);
		if (!response.isValid()){
			Logger.logInfo(th.endPerfLogging());
			return response;
		}**/
		ProductComponents productComponents = new ProductComponents();
		componentList = new ArrayList(listSize);
		for(int i=0;i<listSize;i++){
			productComponentBO = new ProductComponentBO();
			productComponentBO.setComponentKey(((Integer) componentVOList.get(i)).intValue());
			productComponentBO.setComponentDesc((String)componentNameList.get(i));
			productComponentBO.setProductKey(productKey);
			componentList.add(productComponentBO);
		}
		productComponents.setComponentList(componentList);
		bom.persist(productComponents, product, user, true);
		messageList = new ArrayList();
		//Commented to avoid PVA validation in java side
		/*PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
		product.setProductFamilyId(request.getProductFamily());
		doing pva validation while attaching benefit component .
		validateQuestionPVA =false;
		PVAvalidationResult validationResult= builder.doPVAvalidation(product,componentList,user,validateQuestionPVA);
		List contains deleted list while PVA validation
		List deletedComponentList = validationResult.getDletedComponentList();
		
		
		if(deletedComponentList != null && deletedComponentList.size()>0){
			List bcNameList = new ArrayList();
			for(int i=0;i<deletedComponentList.size();i++){
				ProductComponentBO componentBO = (ProductComponentBO)deletedComponentList.get(i);
			bcNameList.add(componentBO.getComponentDesc());
			}
			String bcString =null;
			if(null!= bcNameList && bcNameList.size()>0){
				bcString = StringUtil.commaSeperate(bcNameList);
			}
			InformationalMessage message = new InformationalMessage(BusinessConstants.PRODUCT_BENEFIT_COMPONENT_WITH_INAVLID_LINES);
			message.setParameters(new String[] {bcString});
			messageList.add(message);
		}
		if(null!=deletedComponentList){
			if(componentList.size()>deletedComponentList.size()){
				messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_CMPNT_SAVED));
			}*/
	//	}else{
			messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_CMPNT_SAVED));
	//	}
		response.setMessages(messageList);
		response.setSuccess(true);
		break;
		
		case SaveProductComponentRequest.UPDATE_SEQUENCE:
			ProductComponents productComponentsUpdate = new ProductComponents();
		if(0<listSize){
			componentListUpdate = new ArrayList(listSize);
			
			while (iterComponent.hasNext())
			{
				productComponentBO = new ProductComponentBO();
				ProductComponentVO productComponentVO = (ProductComponentVO) iterComponent.next();
				setValuesToProductComponentBO(productComponentVO, productComponentBO);
				productComponentBO.setProductKey(productKey);
				componentListUpdate.add(productComponentBO);
			}
			productComponentsUpdate.setComponentList(componentListUpdate);
			bom.persist(productComponentsUpdate, product, user, false);
			messageList = new ArrayList(1);
			messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_CMNT_UPDATED));
			response.setMessages(messageList);
			response.setSuccess(true);
		}
		break;
		
		}
  		response.setProductComponentList(locateAssociatedComponents(product.getProductKey(), request.getUser()));
  		if(action == SaveProductComponentRequest.SAVE_BENEFIT_COMPONENT){
  			List changedIds = getChangedIds(response.getProductComponentList(), request.getCompNameList());
  			
  			// Removed the admin method validations at product level.
//  			if(null != changedIds){
//  				AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//  				//AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//  				validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//  				validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//  				//validationRqst.setChangedIds(request.getComponentList());
//  				validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BC_LIST_INPRODUCT);
//  				validationRqst.setChangedIds(changedIds);
//  				AdminMethodSynchronousValidationResponse validationResponse = 
//  					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//  			}
  		}
  		Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
  		
  		return response;
	}
  	catch (WPDException ex)
	{
  		throw new ServiceException("Exception occured while BOM call", null, ex);
	}
  	
  }


  /**
 * @param totalList
 * @param affectedList
 * @return
 */
private List getChangedIds(List totalList, List affectedList) {
	if(null!=totalList){
    List changedIds = new ArrayList(totalList.size());
    if(affectedList.contains(BusinessConstants.GENERAL_BENEFITS)){
        for(Iterator i=totalList.iterator();i.hasNext();){
            ProductComponentBO componentBO = (ProductComponentBO)i.next();
            if(BusinessConstants.GENERAL_BENEFITS.equals(componentBO.getComponentDesc())){
                changedIds.add(new Integer(componentBO.getComponentKey()));
                break;
            }
        }
        return changedIds;
    }else {
        boolean present = false;
        for(Iterator i=totalList.iterator();i.hasNext();){
            ProductComponentBO componentBO = (ProductComponentBO)i.next();
            if(BusinessConstants.GENERAL_BENEFITS.equals(componentBO.getComponentDesc())){
                present = true;
            }
            if(affectedList.contains(componentBO.getComponentDesc())){
                changedIds.add(new Integer(componentBO.getComponentKey()));
            } 
        }

        if(present)
            return changedIds;
    }
}
    return null;
}


/**
   * Service method for Retrieving overrided benfit Defenitions.
   * 
   * @param request
   *            RetrieveProductBenefitDefinitionRequest
   * @return RetrieveProductBenefitDefinitionResponse
   * @throws WPDException
   *  
   * if part is using for product benefit definition view 
   * 
   * else part is for retrieving duplicate reference in benefit line and questions .
   * 
   * 
   */
  public WPDResponse execute(RetrieveProductBenefitDefinitionRequest request) throws ServiceException
  {
    try
    {
      
    
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      ProductBusinessObjectBuilder productBusinessObjectBuilder =null;
      List messageList = null;
      ErrorMessage message =null;
      RetrieveProductBenefitDefinitionResponse retrieveProductBenefitDefinitionResponse = 
      					(RetrieveProductBenefitDefinitionResponse) WPDResponseFactory.
						getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE);
      int productId = request.getProductKeyObject().getProductId();
      
      if(!request.isDuplicateRefPopup()){
      	int benefitId;
      	benefitId = request.getBenefitId();
      	int benefitComponentId = request.getBenefitComponentId();
      	
      	
      	ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria = new ProductBenefitDefinitionLocateCriteria();
      	
      	productBenefitDefinitionLocateCriteria.setBenefitId(benefitId);
      	productBenefitDefinitionLocateCriteria.setProductId(productId);
      	productBenefitDefinitionLocateCriteria.setBenefitComponentId(benefitComponentId);
      	productBenefitDefinitionLocateCriteria.setBenefitLevelHideFlag(request.getBenefitLevelHideFlag());
      	productBenefitDefinitionLocateCriteria.setBenefitLineHideFlag(request.getBenefitLineHideFlag());
      	
      	
      	BusinessObjectManager bom = getBusinessObjectManager();
      	
      	LocateResults locateResults = bom.locate(productBenefitDefinitionLocateCriteria, request.getUser());
      	
      	retrieveProductBenefitDefinitionResponse.setBenefitDefinitionsList(locateResults.getLocateResults());
      	
      	if(null != locateResults.getLocateResults() && !locateResults.getLocateResults().isEmpty()){
      		
      		productBenefitDefinitionLocateCriteria.setType("Criteria");
      		LocateResults criteriaResults = bom.locate(productBenefitDefinitionLocateCriteria, request.getUser());
      		retrieveProductBenefitDefinitionResponse.setCriteriaList(criteriaResults.getLocateResults());
      		
      		if(null != criteriaResults.getLocateResults() && !criteriaResults.getLocateResults().isEmpty()){
      			productBenefitDefinitionLocateCriteria.setType("Level");
      			productBenefitDefinitionLocateCriteria.setTierSysIdList(getTierList(criteriaResults.getLocateResults()));
      			LocateResults lvlLineResults = bom.locate(productBenefitDefinitionLocateCriteria, request.getUser());
      			retrieveProductBenefitDefinitionResponse.setLvlLineList(lvlLineResults.getLocateResults());
      		}
      	}
      	
      	Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      }else{
      	messageList = new ArrayList(1);
      	productBusinessObjectBuilder = new ProductBusinessObjectBuilder();
      	List allDuplicateReferenceList = productBusinessObjectBuilder.getAllDuplicateReference(productId);
      	retrieveProductBenefitDefinitionResponse.
					setProductDuplicateReferenceList(allDuplicateReferenceList);
      	message = new ErrorMessage(BusinessConstants.UNIQUE_REF_VALIDATION);
      	messageList.add(message);
      	retrieveProductBenefitDefinitionResponse.setMessages(messageList);
      }
      
      return retrieveProductBenefitDefinitionResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }

  private List getTierList(List criteriaListFrmDB){
	
	List tierSysIdList = new ArrayList();
	
	TierDefinitionBO oldTierDef = (TierDefinitionBO)criteriaListFrmDB.get(0);
	Integer oldCntrctTierId= new Integer(oldTierDef.getTierSysId());
	
	tierSysIdList.add(oldCntrctTierId);
	
	for(int i=1;i<criteriaListFrmDB.size();i++){
		
    	TierDefinitionBO newTierDef = (TierDefinitionBO)criteriaListFrmDB.get(i);
    	Integer newCntrctTierId = new Integer(newTierDef.getTierSysId());
    	
    	if(oldCntrctTierId.intValue() != newCntrctTierId.intValue()){
    		
    		tierSysIdList.add(newCntrctTierId);
    	}
    	oldCntrctTierId = newCntrctTierId;
	}
	
	return tierSysIdList;
}
  /**
   * Service method for Locate product.
   * 
   * @param request
   *            SearchProductRequest
   * @return SearchProductResponse
   * @throws WPDException
   */
  public WPDResponse execute(SearchProductRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      SearchProductResponse searchProductResponse = (SearchProductResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SEARCH_PRODUCT_RESPONSE);
      int action = request.getAction();
      switch (action)
      {
        case SearchProductRequest.SEARCH_ALL_VERSION:
          ProductLocatePreviousVersionsCriteria productLocatePreviousVersionsCriteria = new ProductLocatePreviousVersionsCriteria();
          productLocatePreviousVersionsCriteria.setProductId(request.getProductId());
          LocateResults locateResultsPrevVersion = getBusinessObjectManager().locate(productLocatePreviousVersionsCriteria, request.getUser());
          List versionList = locateResultsPrevVersion.getLocateResults();
          if(null!=versionList){
          searchProductResponse.setAllProductVersionsList(versionList);
          }
          searchProductResponse.setSuccess(true);
          break;

        case SearchProductRequest.SEARCH_PRODUCT:
          ProductSearchCriteriaVO productSearchCriteriaVO = new ProductSearchCriteriaVO();
          productSearchCriteriaVO = request.getProductSearchCriteriaVO();

          ProductLocateCriteria productLocateCriteria = new ProductLocateCriteria();
          productLocateCriteria.setProductName(BusinessUtil.escpeSpecialCharacters(productSearchCriteriaVO.getProductName()));
          productLocateCriteria.setBusinessEntityList(productSearchCriteriaVO.getBusinessEntityList());
          productLocateCriteria.setBusinessGroupList(productSearchCriteriaVO.getBusinessGroupList());
          productLocateCriteria.setMarketBusinessUnitList(productSearchCriteriaVO.getMarketBusinessUnitList());
          productLocateCriteria.setEffectiveDate(productSearchCriteriaVO.getEffectiveDate());
          productLocateCriteria.setExpiryDate(productSearchCriteriaVO.getExpiryDate());
          productLocateCriteria.setLineOfBusinessList(productSearchCriteriaVO.getLineOfBusinessList());
          productLocateCriteria.setProductFamilyList(productSearchCriteriaVO.getProductFamilyList());
          productLocateCriteria.setProductStructureList(productSearchCriteriaVO.getProductStructureList());
          BusinessObjectManager bom = getBusinessObjectManager();

          LocateResults locateResults = bom.locate(productLocateCriteria, request.getUser());

          searchProductResponse.setProductList(locateResults.getLocateResults());
          searchProductResponse.setSuccess(true);
          List messageList = new ArrayList(2);
          if (null != locateResults && locateResults.getLocateResults().size() == 0)
          {
            messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_NO_SEARCH_RESULT));
            addMessagesToResponse(searchProductResponse, messageList);
          }
          else if (null != locateResults && locateResults.getLocateResults().size() > 50)
          {
            messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_SEARCH_RESULT_EXCEEDS));
            addMessagesToResponse(searchProductResponse, messageList);
          }
          break;
      }
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return searchProductResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for Retrieving Valid product Structures for product.
   * 
   * @param request
   *            RetrieveValidProductStructuresRequest.
   * @return RetrieveValidProductStructuresResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveValidProductStructuresRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      RetrieveValidProductStructuresResponse response = (RetrieveValidProductStructuresResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_VALID_PRD_STR_RESPONSE);

      ProductStructureLocateCriteria locateCriteria = new ProductStructureLocateCriteria();
      locateCriteria.setLineOfBusiness(request.getLineOfBusiness());
      locateCriteria.setBusinessEntity(request.getBusinessEntity());
      locateCriteria.setBusinessGroup(request.getBusinessGroup());
      /*START CARS*/
      locateCriteria.setMarketBusinessUnit(request.getMarketBusinessUnit());
      /*END CARS*/
      locateCriteria.setEffectiveDate(request.getEffectiveDate());
      locateCriteria.setExpiryDate(request.getExpiryDate());
      locateCriteria.setStructureType(request.getProductType());
      locateCriteria.setMandateType(request.getMandateType());
      locateCriteria.setStateId(request.getStateCode());

      ProductBusinessObjectBuilder bom = new ProductBusinessObjectBuilder();
      LocateResults locateResults = bom.locate(locateCriteria, request.getUser());
      response.setValidProductStructureList(locateResults.getLocateResults());
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for retrieving Benefit Information.
   * 
   * @param request
   *            RetrieveProductBenefitRequest.
   * @return RetrieveProductBenefitResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductBenefitRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      RetrieveProductBenefitResponse retrieveProductBenefitResponse = 
      	(RetrieveProductBenefitResponse) WPDResponseFactory.getResponse(
      			WPDResponseFactory.RETRIEVE_PRODUCT_BENEFIT_RESPONSE);
      ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();

      switch (request.getAction())
      {
        case RetrieveProductBenefitRequest.RETRIEVE_GENERAL_INFO:
        // int benefitKey = request.getBenefitKey();        
        ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO = request.
				getProductBenefitGeneralInformationVO();             
          StandardBenefitBO standardBenefitBO = productBusinessObjectBuilder.
		  	getBenefitGeneralInfo(productBenefitGeneralInformationVO);
          retrieveProductBenefitResponse.setStandardBenefitBO(standardBenefitBO);
          if(null != standardBenefitBO){
          	retrieveProductBenefitResponse.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
          			"stdbenefit", standardBenefitBO.getParentSystemId()));
          }
          break;
        case RetrieveProductBenefitRequest.RETRIEVE_NONADJ_BENEFIT_MANDATES:
          int benefitMasterSystemId = request.getBenefitMasterSystemId();
          User user = request.getUser();
          LocateResults locateResults = productBusinessObjectBuilder.getNonAdjBenefitMandate(
          		benefitMasterSystemId, user);
          List nonAdjMandateList = locateResults.getLocateResults();
          retrieveProductBenefitResponse.setNonAdjMandateList(nonAdjMandateList);
          break;
      }
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return retrieveProductBenefitResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

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
   // if(null!=valueObject.getProductFamilyId() && !("").equals(valueObject.getProductFamilyId())){
   //  businessObject.setProductFamilyId(valueObject.getProductFamilyId());
   // }else{
    	String productFamily = getProductFamily(valueObject.getProductStructureKey());
    	businessObject.setProductFamilyId(productFamily);
  //  }
    businessObject.setBusinessDomains(valueObject.getBusinessDomains());
    //for new fields

    //		mandate
    businessObject.setProductType(valueObject.getProductType());
    businessObject.setMandateType(valueObject.getMandateType());
    businessObject.setStateId(valueObject.getStateId());
    businessObject.setStateDesc(valueObject.getStateDesc());
    businessObject.setHiddenProductType(valueObject.getHiddenProductType());

  }
  
  /**
   * 
   * @param productStructureId
   * @param proudctFamily
   */
  private String getProductFamily(int productStructureId)
  {
  	String proudctFamily = null;
  	ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
  	try {
  		proudctFamily = builder.getProductFamily(productStructureId);
	} catch (SevereException e) {
		e.printStackTrace();
	}
  	
  	return proudctFamily;
  }
  
  /**
   * Method to set values to businessObject from values object of Product
   * Component.
   * 
   * @param valueObject
   *            ProductComponentVO
   * @param businessObject
   *            ProductComponentBO
   */
  private void setValuesToProductComponentBO(ProductComponentVO valueObject, ProductComponentBO businessObject)
  {
    businessObject.setProductKey(valueObject.getProductKey());
    businessObject.setComponentKey(valueObject.getComponentKey());
    businessObject.setComponentId(valueObject.getComponentId());
    businessObject.setProductStructureKey(valueObject.getProductStructureKey());
    businessObject.setComponentVersion(valueObject.getComponentVersion());
    businessObject.setComponentDesc(valueObject.getComponentDesc());
    businessObject.setSequence(valueObject.getSequence());
  }


  /**
   * Method to get Business Object Manager.
   * 
   * @return BusinessObjectManager.
   */
  private BusinessObjectManager getBusinessObjectManager()
  {
    BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory.getFactory(ObjectFactory.BOM);
    BusinessObjectManager bom = bomf.getBusinessObjectManager();
    return bom;
  }


  /**
   * Method to set the list of messages to response.
   * 
   * @param response
   *            WPDResponse
   * @param messages
   *            List.
   */
  private void addMessagesToResponse(WPDResponse response, List messages)
  {
    if (null == messages || messages.size() == 0)
      return;
    if (null == response.getMessages())
      response.setMessages(messages);
    else
      response.getMessages().addAll(messages);
  }


  /**
   * Method to retrieve Product Key values from Request.
   * 
   * @param productRequest
   *            ProductRequest
   * @return ProductBO
   * @throws ServiceException
   */
  private ProductBO getProductBO(ProductRequest productRequest) throws ServiceException
  {
    ProductBO productBO = new ProductBO();
    ProductKeyObject productKeyObject = productRequest.getProductKeyObject();
    if (productKeyObject == null)
    {
      List list = new ArrayList(1);
      list.add(productRequest);
      throw new ServiceException("ProductKeyObject not found in the request.", list, null);
    }
    productBO.setProductKey(productKeyObject.getProductId());
    productBO.setProductName(productKeyObject.getProductName());
    productBO.setVersion(productKeyObject.getVersion());
    productBO.setParentProductKey(productKeyObject.getParentId());
    productBO.setStatus(productKeyObject.getStatus());
    productBO.setBusinessDomains(productKeyObject.getBusinessDomains());
    return productBO;
  }


  /**
   * Method to locate the associated components for product using business
   * object manager.
   * 
   * @param productKey
   * @param user
   * @return List of Associated Components.
   * @throws WPDException
   */
  public List locateAssociatedComponents(int productKey, User user) throws WPDException
  {
    BusinessObjectManager bom = getBusinessObjectManager();
    ComponentLocateCriteria locateCriteria = new ComponentLocateCriteria();
    locateCriteria.setProductKey(productKey);
    LocateResults locateResults = bom.locate(locateCriteria, user);
    return locateResults.getLocateResults();
  }
// Admin Method Validation for Delete Tier in Product
  private void updateAMVForTierProduct(ProductTierDeleteRequest request) throws ServiceException {
  	
  	//removed the admin method validations at product level.
//	if(null != request ){		
//			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			validationRqst.setBenefitComponentId(request.getBenefitComponentSysId());
//			validationRqst.setProductId(request.getProductKeyObject().getProductId());
//						
//			List deletedIds = new ArrayList(1);
//			
//			deletedIds.add(new Integer(request.getProductTierSysId()));			
//			validationRqst.setChangedIds(deletedIds);
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_TIER_IN_PRODUCT_VALIDATION);
//									
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		}
  }	



  /**
   * This method converts the VO to BO and processess the
   * SaveProductBenefitDefinitionRequest
   * 
   * @param request
   *            SaveProductBenefitDefinitionRequest.
   * @return SaveProductBenefitDefinitionResponse.
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductBenefitDefinitionRequest request) throws ServiceException
  {
  	List modfiedLineList = null;
  	List modifiedCriteriaList = null;
    try
    {
              
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      SaveProductBenefitDefinitionResponse response = (SaveProductBenefitDefinitionResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_BENEFIT_DEFINITION_RESPONSE);
      
      modfiedLineList = getModifiedList(request.getMapWithOldValues(),request.getMapWithNewValues());
      modifiedCriteriaList = getModifiedTierCriteriaList(request.getMapWithOldCriteriaValues(),request.getMapWithNewCriteriaValues());
      
      //gets the main object
      ProductBO product = getProductBO(request);
      //gets the updated line list from request
      List benefitLineVoList = request.getBenefitLinesList();
      int bnftComponentId = request.getBenefitComponentId();
      String benefitHideFlag = request.getBenefitHideFlag();
      int benefitId = request.getBenefitId();
      BusinessObjectManager bom = getBusinessObjectManager();
      User user = request.getUser();

      //creates the sub object
      ProductBenefitDefinitions productBenefitDefinitions = new ProductBenefitDefinitions();
      List updatedLinesList = null;
      //iterates the list to get the individual VOs n they r converted in
      // to BOs
      if (null != benefitLineVoList)
      {
      	int benefitLineVoListSize = benefitLineVoList.size();
      	
      	updatedLinesList = new ArrayList(benefitLineVoListSize);
        for (int i = 0; i < benefitLineVoListSize; i++)
        {
          ProductBenefitCustomizedBO productBenefitDefinitionBO = new ProductBenefitCustomizedBO();
          ProductBenefitCustomizedVO entityBenefitLine = (ProductBenefitCustomizedVO) benefitLineVoList.get(i);
          if (entityBenefitLine.isBenefitUpdateFlag() || benefitHideFlag.equals(BusinessConstants.HIDE_FLAG_T))
          { //checks if the update status is true
            productBenefitDefinitionBO.setBenefitValue(entityBenefitLine.getOverridedValue());
            productBenefitDefinitionBO.setBenefitLineId(entityBenefitLine.getBenefitLineId());
            productBenefitDefinitionBO.setProductBenefitCustomizedSysId(entityBenefitLine.getBenefitCustomizedSysId());
            productBenefitDefinitionBO.setLineHideFlag(entityBenefitLine.getBenefitLineHideFlag());
            productBenefitDefinitionBO.setLevelHideFlag(entityBenefitLine.getBenefitLevelHideFlag());
            productBenefitDefinitionBO.setBenefitHideFlag(benefitHideFlag);
            /*START CARS */
            //Checking Frequency Value should not be Null
            if(null != entityBenefitLine.getOverridedFreqValue() 
            		&& !WebConstants.EMPTY_STRING.equalsIgnoreCase(entityBenefitLine.getOverridedFreqValue())){
            	productBenefitDefinitionBO.setFrequencyValue(Integer.parseInt(entityBenefitLine.getOverridedFreqValue()));
            }
            productBenefitDefinitionBO.setLevelDesc(entityBenefitLine.getOverridedLvlDescValue());
			/*END CARS */
            updatedLinesList.add(productBenefitDefinitionBO);
          }
        }
      }
      productBenefitDefinitions.setUpdatedBenefitLines(updatedLinesList);
      productBenefitDefinitions.setBenefitComponentId(bnftComponentId);
      productBenefitDefinitions.setBenefitId(benefitId);
     /* if(null != request.getBenefitTierDefinitionList()){
    	  productBenefitDefinitions.setBenefitTierDefinitionList(request.getBenefitTierDefinitionList());
      }*/
      /*if(null != request.getBenefitTierLevelList()){
    	  productBenefitDefinitions.setBenefitTierLevelList(request.getBenefitTierLevelList());
      }*/
      if(null != modfiedLineList){
      	productBenefitDefinitions.setBenefitTierLevelList(modfiedLineList);
      }
      if(null != modifiedCriteriaList){
      	productBenefitDefinitions.setBenefitTierDefinitionList(modifiedCriteriaList);
      }
      //calls the persist method for update
      bom.persist(productBenefitDefinitions, product, user, false);
      
     if("T".equalsIgnoreCase(request.getBenefitHideFlag())){
      ProductBusinessObjectBuilder builder= new ProductBusinessObjectBuilder();
      builder.deleteBnftTierDetails(request.getProductId(),request.getBenefitComponentId(),request.getBenefitId());
     }

      List messageList = new ArrayList(1);

      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_BEN_DEFN_UPDATED));
      
      addMessagesToResponse(response, messageList);
      
      if(request.isHiddenFlagChanged()){
      	
      	AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
      	validationRqst.setBenefitComponentId(request.getBenefitComponentId());
      	validationRqst.setBenefitId(request.getBenefitId());
      	validationRqst.setEntityId(request.getProductId());
      	validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
      	validationRqst.setChangedIds(request.getChangedIds());
      	validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BENEFITLEVELS_IN_ENTITY);
      	validationRqst.setBenefitCompName(request.getBenefitComponentName());
      	
      	AdminMethodSynchronousValidationResponse validationResponse = 
      		(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
      	
      }
      
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while saving", null, ex);
    }
  }
 
  /**
   * This method iterates through the two maps and identify the modified lines
   * @param valuesFromSession
   * @param valuesFromInput
   * @return A list
   */
  private List getModifiedList(Map valuesFromSession,Map valuesFromInput){
  	
  	List modifedTierLines = new ArrayList();
  	
  	BenefitTierBindingObject tierObjectFromSession = null;    
  	BenefitTierBindingObject tierObjectFromInput = null; 
  	
  	if(null == valuesFromSession){
  		return modifedTierLines;
  	}
  	
  	Set a = valuesFromSession.keySet();
  	Iterator it = a.iterator();
  	
  	while(it.hasNext()){
  		
  		boolean updated = false;
  		
  		String key = (String) it.next();
  		tierObjectFromSession = (BenefitTierBindingObject) valuesFromSession.get(key);
  		if(null != valuesFromInput && valuesFromInput.containsKey(key)){
  			tierObjectFromInput = (BenefitTierBindingObject) valuesFromInput.get(key);
  			
  			if(null != tierObjectFromSession && null != tierObjectFromInput){
  				
  				if(null != tierObjectFromSession.getLineValue()&& null != tierObjectFromInput.getLineValue()){  					
  					if(!tierObjectFromSession.getLineValue().equals(tierObjectFromInput.getLineValue())){
  						updated = true;
  					}
  				}
  				
  				if((null == tierObjectFromSession.getLineValue()||tierObjectFromSession.getLineValue().equals(""))&&
  						(null != tierObjectFromInput.getLineValue() && !tierObjectFromInput.getLineValue().equals(""))){
  					updated = true;
  				}
  				if((null == tierObjectFromInput.getLineValue()||tierObjectFromInput.getLineValue().equals(""))&&
  						(null != tierObjectFromSession.getLineValue() && !tierObjectFromSession.getLineValue().equals(""))){
  					updated = true;
  				}
  				
  				//Level description
  				
  				if(null != tierObjectFromSession.getLevelDescription()&& null != tierObjectFromInput.getLevelDescription()){  					
  					if(!tierObjectFromSession.getLevelDescription().equals(tierObjectFromInput.getLevelDescription())){
  						updated = true;
  					}
  				}
  				//Frequency value
  				if(0 != tierObjectFromSession.getFrequencyValue()&& 0 != tierObjectFromInput.getFrequencyValue()){  					
  					if(!(tierObjectFromSession.getFrequencyValue() == tierObjectFromInput.getFrequencyValue())){
  						updated = true;
  					}
  				}
  				if(updated){
  					
  					modifedTierLines.add(tierObjectFromInput);
  				}
  				
  			}			
  		}	
  	}
  	
  	return modifedTierLines;
  	
  }
  /**
   * This method iterated through two maps and identify the modified tier crtieria values
   * @param valuesFromSession
   * @param valuesFromInput
   * @return A list
   */
  private List getModifiedTierCriteriaList(Map valuesFromSession,Map valuesFromInput){
  	
  	List modifedTierCriteria = new ArrayList();
  	
  	BenefitTierBindingObject tierObjectFromSession = null;    
  	BenefitTierBindingObject tierObjectFromInput = null;    
  	
  	if(null == valuesFromSession){
  		return modifedTierCriteria;
  	}
  	
  	Set a = valuesFromSession.keySet();
  	Iterator it = a.iterator();
  	
  	while(it.hasNext()){
  		
  		boolean updated = false;
  		
  		String key = (String) it.next();
  		tierObjectFromSession = (BenefitTierBindingObject) valuesFromSession.get(key);
  		if(null != valuesFromInput && valuesFromInput.containsKey(key)){
  			tierObjectFromInput = (BenefitTierBindingObject) valuesFromInput.get(key);
  			
  			if(null != tierObjectFromSession && null != tierObjectFromInput){
  				if(null != tierObjectFromSession.getCriteriaValue() && null != tierObjectFromInput.getCriteriaValue()){
  					if(!tierObjectFromSession.getCriteriaValue().equals(tierObjectFromInput.getCriteriaValue())){
  						updated = true;
  					}
  				}
  			}			
  		}	
  		if(updated)
  			modifedTierCriteria.add(tierObjectFromInput);
  	}
  	
  	return modifedTierCriteria;
  	
  }

  /**
   * Method to locate the associated Notes for product using business object
   * manager.
   * 
   * @param productKey
   * @param user
   * @return List of Associated Notes.
   * @throws WPDException
   */
  public List locateAssociatedNotes(int productKey, User user) throws WPDException
  {
    //    ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();
    BusinessObjectManager bom = getBusinessObjectManager();
    ProductNotesLocateCriteria notesLocateCriteria = new ProductNotesLocateCriteria();
    notesLocateCriteria.setEntityId(productKey);
    notesLocateCriteria.setEntityType(BusinessConstants.ATTACH_PRODUCT);
    notesLocateCriteria.setMaximumResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
    LocateResults locateResults = bom.locate(notesLocateCriteria, user);
    return locateResults.getLocateResults();
  }


  /**
   * Service method for Associating Note to product.
   * 
   * @param request
   * @return SaveProductNoteResponse
   * @throws WPDException
   *  
   */
  public

  WPDResponse execute(SaveProductNoteRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      SaveProductNoteResponse response = (SaveProductNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PROD_NOTE_RESPONSE);
      ProductBO product = getProductBO(request);
      List notesVOList = request.getNoteList();
      List versionList = request.getVersionList();
      //  Iterator iterNote = notesVOList.iterator();
      BusinessObjectManager bom = getBusinessObjectManager();
      User user = request.getUser();
      List messageList = new ArrayList(1);
      List productDomainlist = null;
      ProductNotes productNotes = new ProductNotes();
      if (null != notesVOList)
      {
      	int notesVOListSize = notesVOList.size();
      	productDomainlist = new ArrayList(notesVOListSize);
        AttachedNotesBO attachedNotesBO;
        for (int i = 0, n = notesVOListSize; i < n; i++)
        {
          attachedNotesBO = new AttachedNotesBO();
          attachedNotesBO.setNoteId(notesVOList.get(i).toString());
          attachedNotesBO.setEntityId(product.getProductKey());
          attachedNotesBO.setEntityType(WebConstants.ATTACH_PRODUCT);
          attachedNotesBO.setVersion(Integer.parseInt((String) versionList.get(i)));
          productDomainlist.add(attachedNotesBO);
        }
      }
      productNotes.setNoteList(productDomainlist);
      productNotes.setAction(1);
      bom.persist(productNotes, product, user, true);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_NOTE_ATTACHED));
      response.setMessages(messageList);
      response.setSuccess(true);
      response.setProductNotetList(locateAssociatedNotes(product.getProductKey(), request.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }

  /**
   * Service method for Retrieving Product note Information.
   * 
   * @param retrieveProductNoteRequest
   *            RetrieveProductBenefitComponentRequest.
   * @return ProductBenefitComponentResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductNoteRequest retrieveProductNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + retrieveProductNoteRequest.getClass().getName());
      RetrieveProductNoteResponse retrieveProductNoteResponse = (RetrieveProductNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_NOTE_RESPONSE);
      ProductBO product = getProductBO(retrieveProductNoteRequest);
      retrieveProductNoteResponse.setProductNotetList(locateAssociatedNotes(product.getProductKey(), retrieveProductNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + retrieveProductNoteResponse.getClass().getName());
      return retrieveProductNoteResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  public WPDResponse execute(DeleteProductNoteRequest deleteProductNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + deleteProductNoteRequest.getClass().getName());
      DeleteProductNoteResponse deleteProductNoteResponse = (DeleteProductNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_NOTE_RESPONSE);
      ProductBO product = getProductBO(deleteProductNoteRequest);
      List messageList = null;
      AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
      attachedNotesBO.setNoteId(deleteProductNoteRequest.getNoteId());
      attachedNotesBO.setEntityId(product.getProductKey());
      attachedNotesBO.setEntityType(WebConstants.ATTACH_PRODUCT);
      BusinessObjectManager bom = getBusinessObjectManager();
      bom.delete(attachedNotesBO, product, deleteProductNoteRequest.getUser());
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_DELETE));
      deleteProductNoteResponse.setMessages(messageList);
      deleteProductNoteResponse.setProductNotetList(locateAssociatedNotes(product.getProductKey(), deleteProductNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + deleteProductNoteResponse.getClass().getName());
      return deleteProductNoteResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Method to locate the associated admin for product using business object
   * manager.
   * 
   * @param productKey
   * @param user
   * @return List of Associated Admin
   * @throws WPDException
   */
  public List locateAssociatedAdmin(int productKey, User user) throws WPDException
  {
    BusinessObjectManager bom = getBusinessObjectManager();
    AdminLocateCriteria locateCriteria = new AdminLocateCriteria();
    locateCriteria.setProductKey(productKey);
    LocateResults locateResults = bom.locate(locateCriteria, user);
    return locateResults.getLocateResults();
  }


  /**
   * Service method for Admin to product.
   * 
   * @param request
   * @return
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductAdminRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      SaveProductAdminResponse response = (SaveProductAdminResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PROD_ADMIN_RESPONSE);

      ProductAdminBO productAdminBO = null;
      int action = request.getAction();
      ProductBO product = getProductBO(request);
      List adminVOList = request.getAdminList();
      List adminList = null;
      Iterator iterComponent = adminVOList.iterator();
      BusinessObjectManager bom = getBusinessObjectManager();
      User user = request.getUser();
      List messageList = new ArrayList(1);
      switch (action)
      {

        case SaveProductAdminRequest.SAVE_ADMIN:
          //	validation for whether the Admin exists or not.if
          // existing , no need to create
          ProductAdmin productAdmin = new ProductAdmin();
         if(null!=adminVOList){
         	adminList = new ArrayList(adminVOList.size());
          while (iterComponent.hasNext())
          {
            productAdminBO = new ProductAdminBO();
            productAdminBO.setAdminKey(((Integer) iterComponent.next()).intValue());
            productAdminBO.setProductKey(product.getProductKey());
            adminList.add(productAdminBO);
          }
          productAdmin.setAdminList(adminList);
         }
          bom.persist(productAdmin, product, user, true);
          
          updateAdminMethodValidationForProductAdminOptions(adminVOList,request);
          
          messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_ADMIN_SAVED));
          response.setMessages(messageList);
          response.setSuccess(true);
          break;

        case SaveProductAdminRequest.UPDATE_SEQUENCE:
          ProductAdmin productAdminUpdate = new ProductAdmin();
          List adminListUpdate = null;
          if(null!=adminVOList){
          	adminListUpdate = new ArrayList(adminVOList.size());
          while (iterComponent.hasNext())
          {
            productAdminBO = new ProductAdminBO();
            ProductAdminVO productAdminVO = (ProductAdminVO) iterComponent.next();
            setValuesToProductAdminBO(productAdminVO, productAdminBO);
            productAdminBO.setProductKey(product.getProductKey());
            adminListUpdate.add(productAdminBO);
          }
          productAdminUpdate.setAdminList(adminListUpdate);
          }
          bom.persist(productAdminUpdate, product, user, false);
          messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_ADMIN_UPDATED));
          response.setMessages(messageList);
          response.setSuccess(true);
          break;

      }
      response.setProductAdminList(locateAssociatedAdmin(product.getProductKey(), request.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  	/**
	 * @param adminVOList
	 * @param request
  	 * @throws ServiceException
	 */
	private void updateAdminMethodValidationForProductAdminOptions(List adminVOList, 
			SaveProductAdminRequest request) throws ServiceException {
		// removed the admin method validations for product.
		
//		if(adminVOList != null && adminVOList.size() > 0 && request != null){
//			//adding admin options in product
//			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			validationRqst.setChangedIds(adminVOList);
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.SAVE_ADMIN_OPTIONS_IN_PRODUCT);
//			
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		}
		
	}


/**
   * Method to set values to businessObject from values object of Product
   * Admin.
   * 
   * @param valueObject
   *            ProductAdminVO
   * @param businessObject
   *            ProductAdminBO
   */
  private void setValuesToProductAdminBO(ProductAdminVO valueObject, ProductAdminBO businessObject)
  {
    businessObject.setProductKey(valueObject.getProductKey());
    businessObject.setAdminKey(valueObject.getAdminKey());
    businessObject.setAdminId(valueObject.getAdminId());
    businessObject.setAdminVersion(valueObject.getAdminVersion());
    businessObject.setAdminDesc(valueObject.getAdminDesc());
    businessObject.setSequence(valueObject.getSequence());
  }


  /**
   * Service method for Retrieving overrided note Information methode for
   * beenefit component associated to Product .
   * 
   * @param loadProductComponentNoteRequest
   *            LoadProductComponentNoteRequest.
   * @return LoadProductComponentNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(LoadProductComponentNoteRequest loadProductComponentNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + loadProductComponentNoteRequest.getClass().getName());
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT_COMP;
      LoadProductComponentNoteResponse loadProductComponentNoteResponse = 
        (LoadProductComponentNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.LOAD_PRODUCT_COMPONENT_NOTE_RESPONSE);
      ProductBO product = getProductBO(loadProductComponentNoteRequest);
      loadProductComponentNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), loadProductComponentNoteRequest.getBenefitComponentId(), secondaryEntityType, 
                                                                               loadProductComponentNoteRequest.getBenefitComponentId(), loadProductComponentNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + loadProductComponentNoteResponse.getClass().getName());
      return loadProductComponentNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }

  /**
   * method for Retrieving overrided note Information 
   * @param productKey
   * @param secondaryEntityId
   * @param secondaryEntityType
   * @param benefitComponentId
   * @param user
   * @return
   * @throws WPDException
   */
  public List locateOveridedNotes(int productKey, int secondaryEntityId, String secondaryEntityType, int benefitComponentId, User user) throws WPDException
  {
    BusinessObjectManager bom = getBusinessObjectManager();
    ProductComponentNotesLocateCriteria productComponentNotesLocateCriteria = new ProductComponentNotesLocateCriteria();
    productComponentNotesLocateCriteria.setPrimaryEntityId(productKey);
    productComponentNotesLocateCriteria.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
    productComponentNotesLocateCriteria.setSecondaryEntityId(secondaryEntityId);
    productComponentNotesLocateCriteria.setSecondaryEntityType(secondaryEntityType);
    productComponentNotesLocateCriteria.setBenefitComponentId(benefitComponentId);
    LocateResults locateResults = bom.locate(productComponentNotesLocateCriteria, user);
    return locateResults.getLocateResults();
  }


  /**
   * Service method for Attaching overrided note Information methode for
   * beenefit component associated to Product .
   * 
   * @param saveProductComponentNoteRequest
   *            SaveProductComponentNoteRequest.
   * @return SaveProductComponentNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductComponentNoteRequest saveProductComponentNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + saveProductComponentNoteRequest.getClass().getName());
      SaveProductComponentNoteResponse saveProductComponentNoteResponse = 
        (SaveProductComponentNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_COMPONENT_NOTE_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      List messageList = new ArrayList(1);
      ProductBO product = getProductBO(saveProductComponentNoteRequest);
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT_COMP;
      ProductNotes productNotes = new ProductNotes();
      List productEntitylist = null;
      
      if (saveProductComponentNoteRequest.getAction() == 1){
	      List notesVOList = saveProductComponentNoteRequest.getNoteList();
	      List versionList = saveProductComponentNoteRequest.getVersionList();
	      int i = 0;
	      if (null != notesVOList)
	      {
	      	int notesVOListSize = notesVOList.size();
	      	productEntitylist = new ArrayList(notesVOListSize);
	        for (i = 0; i < notesVOListSize; i++)
	        {
	          NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
	          notesAttachmentOverrideBO.setNoteId((notesVOList.get(i)).toString());
	          notesAttachmentOverrideBO.setPrimaryEntityId(product.getProductKey());
	          notesAttachmentOverrideBO.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
	          notesAttachmentOverrideBO.setSecondaryEntityId(saveProductComponentNoteRequest.getBenefitComponentId());
	          notesAttachmentOverrideBO.setSecondaryEntityType(secondaryEntityType);
	          notesAttachmentOverrideBO.setBenefitComponentId(saveProductComponentNoteRequest.getBenefitComponentId());
	          notesAttachmentOverrideBO.setVersion(Integer.parseInt((String) versionList.get(i)));
	          notesAttachmentOverrideBO.setOverrideStatus(saveProductComponentNoteRequest.getOverrideStatus());
	          productEntitylist.add(notesAttachmentOverrideBO);
	        }
	      }
//	      productNotes.setNoteList(productEntitylist);
//	      productNotes.setAction(2);
//	      bom.persist(productNotes, product, saveProductComponentNoteRequest.getUser(), true);
	      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_NOTE_ATTACHED));
	      saveProductComponentNoteResponse.setMessages(messageList);
	      saveProductComponentNoteResponse.setSuccess(true);
	      productNotes.setAction(2);
	      
      }else{

      	List  notesIdList = saveProductComponentNoteRequest.getNoteIdList();
      	List notesList = saveProductComponentNoteRequest.getNoteList();
      	NotesAttachmentOverrideBO subObject1 = new NotesAttachmentOverrideBO();
      	productEntitylist = new ArrayList(notesList.size());
		
    	if(null != notesIdList && !notesIdList.isEmpty()){
    		
  			for(int i=0; i<notesIdList.size();i++){
  				String noteId = (String) notesIdList.get(i);
  				if(null != notesList && !notesList.isEmpty()){
  					for(int j=0; j<notesList.size();j++){
  						subObject1 = (NotesAttachmentOverrideBO)notesList.get(j);
  						
  						if(noteId.equals(subObject1.getNoteId())){
  							NotesAttachmentOverrideBO subObject = new NotesAttachmentOverrideBO();
	  						subObject.setNoteId((notesIdList.get(i)).toString());
	  			            subObject.setPrimaryEntityId(product.getProductKey());
	  			            subObject.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
	  			            subObject.setSecondaryEntityId(saveProductComponentNoteRequest.getBenefitComponentId());
	  			            subObject.setSecondaryEntityType(secondaryEntityType);
	  			            subObject.setBenefitComponentId(saveProductComponentNoteRequest.getBenefitComponentId());
	  			            subObject.setOverrideStatus(saveProductComponentNoteRequest.getOverrideStatus());
	  				        subObject.setVersion(subObject1.getVersion());
	  				        productEntitylist.add(subObject);
	  					}
  					}
  				}
  			}
    	}
  			

      saveProductComponentNoteResponse
      .addMessage(new InformationalMessage(
              BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_OVERRIDE_DELETE_SUCCESS));
      saveProductComponentNoteResponse.setSuccess(true);
      productNotes.setAction(2);
      }
      
      productNotes.setNoteList(productEntitylist);
//      productNotes.setAction(2);
      bom.persist(productNotes, product, saveProductComponentNoteRequest.getUser(), true);
      saveProductComponentNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), saveProductComponentNoteRequest.getBenefitComponentId(), secondaryEntityType, 
                                                                               saveProductComponentNoteRequest.getBenefitComponentId(), saveProductComponentNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + saveProductComponentNoteResponse.getClass().getName());
      return saveProductComponentNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for UnAttaching overrided note Information methode for
   * beenefit component associated to Product .
   * 
   * @param deleteProductComponentNoteRequest
   *            DeleteProductComponentNoteRequest.
   * @return DeleteProductComponentNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(DeleteProductComponentNoteRequest deleteProductComponentNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + deleteProductComponentNoteRequest.getClass().getName());
      DeleteProductComponentNoteResponse deleteProductComponentNoteResponse = 
        (DeleteProductComponentNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_COMPONENT_NOTE_RESPONSE);
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT_COMP;
      List messageList = null;
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(deleteProductComponentNoteRequest);
      NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
      notesAttachmentOverrideBO.setPrimaryEntityId(product.getProductKey());
      notesAttachmentOverrideBO.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
      notesAttachmentOverrideBO.setSecondaryEntityId(deleteProductComponentNoteRequest.getBenefitComponentId());
      notesAttachmentOverrideBO.setSecondaryEntityType(secondaryEntityType);
      notesAttachmentOverrideBO.setNoteId(deleteProductComponentNoteRequest.getBenefitComponentNoteId());
      notesAttachmentOverrideBO.setBenefitComponentId(deleteProductComponentNoteRequest.getBenefitComponentId());
      notesAttachmentOverrideBO.setOverrideStatus(WebConstants.OVERRIDE_CONST_F);
      bom.delete(notesAttachmentOverrideBO, product, deleteProductComponentNoteRequest.getUser());
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_NOTE_HIDE));
      deleteProductComponentNoteResponse.setMessages(messageList);
      deleteProductComponentNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), deleteProductComponentNoteRequest.getBenefitComponentId(), secondaryEntityType, 
                                                                                 deleteProductComponentNoteRequest.getBenefitComponentId(), deleteProductComponentNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + deleteProductComponentNoteResponse.getClass().getName());
      return deleteProductComponentNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for Retrieving overrided note Information methode for
   * Standard beenefit associated to Product .
   * 
   * @param loadProductBenefitNoteRequest
   *            LoadProductBenefitNoteRequest.
   * @return LoadProductBenefitNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(LoadProductBenefitNoteRequest loadProductBenefitNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + loadProductBenefitNoteRequest.getClass().getName());
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT;
      LoadProductBenefitNoteResponse loadProductBenefitNoteResponse = (LoadProductBenefitNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.LOAD_PRODUCT_BENEFIT_NOTE_RESPONSE);
      ProductBO product = getProductBO(loadProductBenefitNoteRequest);
      loadProductBenefitNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), loadProductBenefitNoteRequest.getBenefitId(), secondaryEntityType, 
                                                                             loadProductBenefitNoteRequest.getBenefitComponentId(), loadProductBenefitNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + loadProductBenefitNoteResponse.getClass().getName());
      return loadProductBenefitNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for Attaching overrided note Information methode for
   * Standard beenefit associated to Product .
   * 
   * @param saveProductBenefitNoteRequest
   *            SaveProductBenefitNoteRequest.
   * @return SaveProductBenefitNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductBenefitNoteRequest saveProductBenefitNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + saveProductBenefitNoteRequest.getClass().getName());
      SaveProductBenefitNoteResponse saveProductBenefitNoteResponse = (SaveProductBenefitNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_BENEFIT_NOTE_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(saveProductBenefitNoteRequest);
      List messageList = null;
      List notesVOList = saveProductBenefitNoteRequest.getNoteList();
      List versionList = saveProductBenefitNoteRequest.getVersionList();
      List productEntitylist = null;
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT;
      int i = 0;
      ProductNotes productNotes = new ProductNotes();
      if (null != notesVOList)
      {
      	int notesVOListSize = notesVOList.size();
      	productEntitylist = new ArrayList(notesVOListSize);
        for (i = 0; i < notesVOListSize; i++)
        {
          NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
          notesAttachmentOverrideBO.setNoteId((notesVOList.get(i)).toString());
          notesAttachmentOverrideBO.setPrimaryEntityId(product.getProductKey());
          notesAttachmentOverrideBO.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
          notesAttachmentOverrideBO.setSecondaryEntityId(saveProductBenefitNoteRequest.getBenefitId());
          notesAttachmentOverrideBO.setSecondaryEntityType(secondaryEntityType);
          notesAttachmentOverrideBO.setBenefitComponentId(saveProductBenefitNoteRequest.getBenefitComponentId());
          notesAttachmentOverrideBO.setVersion(Integer.parseInt((String) versionList.get(i)));
          notesAttachmentOverrideBO.setOverrideStatus(saveProductBenefitNoteRequest.getOverrideStatus());
          productEntitylist.add(notesAttachmentOverrideBO);
        }
      }
      productNotes.setNoteList(productEntitylist);
      productNotes.setAction(2);
      bom.persist(productNotes, product, saveProductBenefitNoteRequest.getUser(), true);
      if (saveProductBenefitNoteRequest.getAction() == 1)
      {
        messageList = new ArrayList(1);
        messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_NOTE_ATTACHED));
        saveProductBenefitNoteResponse.setMessages(messageList);
        saveProductBenefitNoteResponse.setSuccess(true);
      }
      else
      {
        messageList = new ArrayList(1);
        messageList.add(new InformationalMessage(BusinessConstants.MSG_NOTE_HIDE));
        saveProductBenefitNoteResponse.setMessages(messageList);
      }
      saveProductBenefitNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), saveProductBenefitNoteRequest.getBenefitId(), secondaryEntityType, 
                                                                             saveProductBenefitNoteRequest.getBenefitComponentId(), saveProductBenefitNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + saveProductBenefitNoteResponse.getClass().getName());
      return saveProductBenefitNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for UnAttaching overrided note Information methode for
   * Standard beenefit associated to Product .
   * 
   * @param deleteProductBenefitNoteRequest
   *            DeleteProductBenefitNoteRequest.
   * @return DeleteProductBenefitNoteResponse.
   * @throws WPDException
   */
  public WPDResponse execute(DeleteProductBenefitNoteRequest deleteProductBenefitNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + deleteProductBenefitNoteRequest.getClass().getName());
      DeleteProductBenefitNoteResponse deleteProductBenefitNoteResponse = 
        (DeleteProductBenefitNoteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_BENEFIT_NOTE_RESPONSE);
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT;
      List messageList = null;
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(deleteProductBenefitNoteRequest);
      NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
      notesAttachmentOverrideBO.setPrimaryEntityId(product.getProductKey());
      notesAttachmentOverrideBO.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
      notesAttachmentOverrideBO.setSecondaryEntityId(deleteProductBenefitNoteRequest.getBenefitId());
      notesAttachmentOverrideBO.setSecondaryEntityType(secondaryEntityType);
      notesAttachmentOverrideBO.setNoteId(deleteProductBenefitNoteRequest.getBenefitComponentNoteId());
      notesAttachmentOverrideBO.setBenefitComponentId(deleteProductBenefitNoteRequest.getBenefitComponentId());
      bom.delete(notesAttachmentOverrideBO, product, deleteProductBenefitNoteRequest.getUser());
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_NOTE_HIDE));
      deleteProductBenefitNoteResponse.setMessages(messageList);
      deleteProductBenefitNoteResponse.setProductNotetList(locateOveridedNotes(product.getProductKey(), deleteProductBenefitNoteRequest.getBenefitId(), secondaryEntityType, 
                                                                               deleteProductBenefitNoteRequest.getBenefitComponentId(), deleteProductBenefitNoteRequest.getUser()));
      Logger.logInfo("Returning  from execute method for request=" + deleteProductBenefitNoteResponse.getClass().getName());
      return deleteProductBenefitNoteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  /**
   * Service method for Retrieving Admin Information.
   * 
   * @param request
   *            RetrieveProductAdminRequest.
   * @return ProductAdminResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductAdminRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      List adminList = null;
      ProductAdminResponse productAdminResponse = (ProductAdminResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PROD_ADMIN_RESPONSE);
      ProductAdapterManager productAdapterManager = new ProductAdapterManager();
      int action = request.getAction();
      ProductBO productBO = getProductBO(request);
      switch (action)
      {
        case RetrieveProductAdminRequest.PRODUCT_ADMIN_ADDED:
          adminList = locateAssociatedAdmin(productBO.getProductKey(), request.getUser());
          break;
        case RetrieveProductAdminRequest.PRODUCT_ADMIN_POPUP:
          adminList = productAdapterManager.getAdminListforPopup(productBO.getProductKey());
          if (adminList == null || adminList.size() == 0)
          {
            productAdminResponse.addMessage(new InformationalMessage(BusinessConstants.MSG_POPUP_ADMIN_NOT_FOUND));
          }
          break;
        case RetrieveProductAdminRequest.PRODUCT_ADMIN_RETRIEVE:

          Map params = new HashMap();
          BusinessObjectManager bom = getBusinessObjectManager();
          //sets benefit component id to the map
          params.put(BusinessConstants.ADMIN_ID, new Integer(request.getAdminId()));

          productBO = (ProductBO) bom.retrieve(productBO, request.getUser(), params);
          if (null != productBO.getAdminList())
          {
            productAdminResponse.setAdminDetails((AdminBO) productBO.getAdminList().get(0));
            //sets the domain details to the response
            productAdminResponse.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(WebConstants.ADMIN_OPTION, productAdminResponse.getAdminDetails().getAdminParentId()));
          }
          break;
      }
      productAdminResponse.setAdminList(adminList);
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return productAdminResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


  public WPDResponse execute(DeleteProductAdminRequest deleteProductAdminRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + deleteProductAdminRequest.getClass().getName());
      ProductAdminDeleteResponse productAdminDeleteResponse = (ProductAdminDeleteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_ADMIN_RESPONSE);
      List messageList = null;
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(deleteProductAdminRequest);
      ProductAdminBO productAdminBO = new ProductAdminBO();
      productAdminBO.setProductKey(product.getProductKey());
      productAdminBO.setAdminKey(deleteProductAdminRequest.getAdminKey());
      bom.delete(productAdminBO, product, deleteProductAdminRequest.getUser());
      
      updateAMVForProdBenefitAdminOption(deleteProductAdminRequest);
      
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRODUCT_ADMINOPTION_DELETE));
      productAdminDeleteResponse.setMessages(messageList);
      Logger.logInfo("Returning  from execute method for request=" + productAdminDeleteResponse.getClass().getName());
      return productAdminDeleteResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }


	/**
	 * @param deleteProductAdminRequest
	 * @throws ServiceException
	 */
	private void updateAMVForProdBenefitAdminOption(DeleteProductAdminRequest deleteProductAdminRequest) throws ServiceException {
		
		// Removed the admin method validations at product level.
//		if(deleteProductAdminRequest != null){
//			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setEntityId(deleteProductAdminRequest.getProductKeyObject().getProductId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			List deletedIds = new ArrayList(1);
//			deletedIds.add(new Integer(deleteProductAdminRequest.getAdminKey()));
//			validationRqst.setChangedIds(deletedIds);
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.DELETE_ADMIN_OPTIONS_IN_PRODUCT);
//			
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		}		
	}


/**
   * Service method for overriding benefitline notes to product level
   * 
   * @param productBenefitLineOverrideNoteRequest
   *            ProductBenefitLineOverrideNoteRequest.
   * @return response ProductLineOverrideNotesResponse.
   * @throws WPDException
   */
  public WPDResponse execute(ProductBenefitLineOverrideNoteRequest productBenefitLineOverrideNoteRequest) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + productBenefitLineOverrideNoteRequest.getClass().getName());
      ProductLineOverrideNotesResponse productLineOverrideNotesResponse = 
        (ProductLineOverrideNotesResponse) WPDResponseFactory.getResponse(WPDResponseFactory.PRODUCT_LINE_OVERRIDE_NOTES_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(productBenefitLineOverrideNoteRequest);
      List messageList = null;
      List notesVOList = productBenefitLineOverrideNoteRequest.getNoteList();
      String secondaryEntityType = WebConstants.ATTACH_BENEFIT_LINE;
      int action = productBenefitLineOverrideNoteRequest.getAction();
      if(action == 2){
	      NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
	      notesAttachmentOverrideBO.setPrimaryEntityId(product.getProductKey());
	      notesAttachmentOverrideBO.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
	      notesAttachmentOverrideBO.setSecondaryEntityId(productBenefitLineOverrideNoteRequest.getBenefitLineId());
	      notesAttachmentOverrideBO.setSecondaryEntityType(secondaryEntityType);
	      notesAttachmentOverrideBO.setBenefitComponentId(productBenefitLineOverrideNoteRequest.getBenefitComponentId());
	      notesAttachmentOverrideBO.setNotesList(notesVOList);
	      if(0!=productBenefitLineOverrideNoteRequest.getTierSysId()){
	      	notesAttachmentOverrideBO.setTierSysId(productBenefitLineOverrideNoteRequest.getTierSysId());
	      }
	
	      bom.persist(notesAttachmentOverrideBO, product, productBenefitLineOverrideNoteRequest.getUser(), false);
      }else if(action == 3){
      	  TierNoteOverRide tierNoteOverRide = new TierNoteOverRide();
	      tierNoteOverRide.setPrimaryEntityId(product.getProductKey());
	      tierNoteOverRide.setPrimaryEntityType(WebConstants.ATTACH_PRODUCT);
	      tierNoteOverRide.setSecondaryEntityId(productBenefitLineOverrideNoteRequest.getBenefitLineId());
	      tierNoteOverRide.setSecondaryEntityType(secondaryEntityType);
	      tierNoteOverRide.setBenefitComponentId(productBenefitLineOverrideNoteRequest.getBenefitComponentId());
	      tierNoteOverRide.setNotesList(notesVOList);
	      tierNoteOverRide.setTierSysId(productBenefitLineOverrideNoteRequest.getTierSysId());
	      
	      bom.persist(tierNoteOverRide, product, productBenefitLineOverrideNoteRequest.getUser(), false);
  
      }
      NotesLookUpLocateCriteria notesLookUpLocateCriteria = new NotesLookUpLocateCriteria();

      // Set the key values to the locateCriteria
      notesLookUpLocateCriteria.setEntityId(productBenefitLineOverrideNoteRequest.getBenefitLineId());
      notesLookUpLocateCriteria.setEntityType(secondaryEntityType);
      notesLookUpLocateCriteria.setAvailableEntityId(product.getProductKey());
      notesLookUpLocateCriteria.setAvailableEntityType(WebConstants.ATTACH_PRODUCT);
      notesLookUpLocateCriteria.setBenefitComponentId(productBenefitLineOverrideNoteRequest.getBenefitComponentId());
      if(0!=productBenefitLineOverrideNoteRequest.getTierSysId()){
      	notesLookUpLocateCriteria.setTierSysId(productBenefitLineOverrideNoteRequest.getTierSysId());
      	notesLookUpLocateCriteria.setAction(101);
      }
      else
      	notesLookUpLocateCriteria.setAction(4);

      // Create an instance of the builder
      NotesAttachmentBusinessObjectBuilder lookupBusinessObjectBuilder = new NotesAttachmentBusinessObjectBuilder();

      // Call the builder method to get the values into the response
      List locateResults = lookupBusinessObjectBuilder.locate(notesLookUpLocateCriteria);
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_CNT_BENLINE_UPDATED));
      addMessagesToResponse(productLineOverrideNotesResponse, messageList);
      // Set the note list to the response
      productLineOverrideNotesResponse.setNotesList(locateResults);
      Logger.logInfo("Returning  from execute method for request=" + productLineOverrideNotesResponse.getClass().getName());
      return productLineOverrideNotesResponse;
    }
    catch (WPDException ex)
    {

      throw new ServiceException("Exception occured while BOM call", null, ex);
    }

  }

/**
   * This method is used to get the Questionnaire list from the table
   * 
   * @param RetrieveBenefitComponentQuestionnairRequest
   * 		  request for loading initial questionnare,changedquestionnaire and view result 
   * @return LocateComponentsBenefitAdministrationResponse
   * 		  response will hold the questionnaire List
   * @throws WPDException 
   */
  public WPDResponse execute(
  		RetrieveProductQuestionnairRequest request)
          throws ServiceException {
  	try {
        Logger.logInfo("Inside  Benefit Administration Search Service");
        LocateComponentsBenefitAdministrationResponse response = null;
        ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
        ComponentsBenefitAdministrationLocateCriteria locateCriteria = new ComponentsBenefitAdministrationLocateCriteria();
        response = (LocateComponentsBenefitAdministrationResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.LOCATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE);
        int action = request.getAction();
        switch(action){
         case RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST:
        locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST);
        locateCriteria.setAdminOptSysId(request.getAdminOptSysId());
        
        locateCriteria.setEntityId(request.getProductId());
        locateCriteria.setEntiityType("PRODUCT");
    	 break;
         case RetrieveBenefitComponentQuestionnairRequest.LOAD_SELECTED_CHILD:
         	locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD);
    	 locateCriteria.setAdminOptSysId(request.getAdminOptSysId());
         locateCriteria.setEntityProductAdministrationBO(request.getEntityProductAdministrationBO());
         locateCriteria.setAnswerId(request.getSelectedAnswerId());
         locateCriteria.setEntityId(request.getProductPrntSysId());
         locateCriteria.setQuestionnareList(request.getQuestionnareList());
         locateCriteria.setQuestionareListIndex(request.getQuestionareListIndex());
         locateCriteria.setProductId(request.getProductId());
    	break;
         case RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW:
         	 locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW);
	         locateCriteria.setAdminOptSysId(request.getAdminOptSysId());
	         locateCriteria.setEntityId(request.getProductId());
       	 
       	break;
        }
        LocateResults locateResults = builder.locate(locateCriteria, request
                .getUser());
        response.setQuestionnareList(locateResults
                .getLocateResults());
        return response;
    } catch (WPDException ex) {
        
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing LocateComponentsBenefitAdministrationRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    }
 }
  public WPDResponse execute(
  		UpdateProductAdministrationRequest request)
        throws ServiceException {
	List messageList = new ArrayList(2);
    Logger.logInfo("Inside Update Admistration Benefit Component Service");
    UpdateComponentsBenefitAdministrationResponse response = null;
    ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
    BusinessObjectManager bom = getBusinessObjectManager();
    ProductBO productBO = new ProductBO();
    productBO.setBusinessDomains(request.getDomainList());
    productBO.setVersion(request.getVersionNumber());
    productBO.setProductName(request.getMainObjectIdentifier());
    
    List questionnareList = request.getQuestionnareList();
   
    ProductQuestionnaireAssnBO administrationBO = new ProductQuestionnaireAssnBO();
    administrationBO
            .setQuestionnareList(request.getQuestionnareList());
    administrationBO.setProductSysId(request.getEntityId());
    administrationBO.setAdminOptionSysId(request.getAdminlevelOptionSysId());
   
    try {
    	bom.persist(administrationBO, productBO,
                request.getUser(), false);
    	updateValidationForAdminOptions(request);
        messageList.add(new InformationalMessage(BusinessConstants.MSG_QUESTIONNARE_SAVE_BC_SUCCESS));
        
    } catch (SevereException ex) {
    	Logger.logError(ex);
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    } catch (WPDException ex) {
    	Logger.logError(ex);
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing UpdateComponentsBenefitAdministrationRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    }

    response = (UpdateComponentsBenefitAdministrationResponse) WPDResponseFactory
            .getResponse(WPDResponseFactory.UPDATE_COMPONENTS_BENEFIT_ADMIN_RESPONSE);
    response.setMessages(messageList);
    return response;
}
  /**
   * Service method to retrieve Benefit Administration .
   * 
   * @param request
   *            RetrieveProductAdminOptionRequest.
   * @return RetrieveProductAdminOptionResponse.
   * @throws WPDException
   */
  public WPDResponse execute(RetrieveProductAdminOptionRequest request) throws ServiceException
  {

    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      RetrieveProductAdminOptionResponse retrieveProductAdminOptionResponse = 
        (RetrieveProductAdminOptionResponse) WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_ADMIN_OPTION_RESPONSE);
      ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();
      ProductAdminOptionLocateCriteria productAdminOptionLocateCriteria = new ProductAdminOptionLocateCriteria();
      productAdminOptionLocateCriteria.setEntityId(request.getProductId());
      productAdminOptionLocateCriteria.setAdminOptSysId(request.getAdminOptSysId());
      productAdminOptionLocateCriteria.setEntiityType(WebConstants.PRODUCT);
      User user = request.getUser();
      LocateResults locateResults = productBusinessObjectBuilder.locate(productAdminOptionLocateCriteria, user);
      retrieveProductAdminOptionResponse.setBenefitAdministrationList(locateResults.getLocateResults());
      Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
      return retrieveProductAdminOptionResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Updates the overridden answers to the db
   * 
   * @param administrationRequest
   * @return WPDResponse
   * @throws ServiceException
   */
  public WPDResponse execute(SaveProductAdministrationRequest administrationRequest) throws ServiceException
  {
    Logger.logInfo("Entering execute method for saving benefit administration");
    // Create the response object from the response factory
    SaveProductBenefitAdministrationResponse administrationResponse = 
      (SaveProductBenefitAdministrationResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT);
    ProductBusinessObjectBuilder productBusinessObjectBuilder = new ProductBusinessObjectBuilder();

    try
    {
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = new ProductBO();
      product.setBusinessDomains(administrationRequest.getDomainList());
      product.setProductName(administrationRequest.getProductKeyObject().getProductName());
      product.setVersion(administrationRequest.getProductKeyObject().getVersion());
      product.setStatus(administrationRequest.getProductKeyObject().getStatus());

      List productAdministrationVOList = administrationRequest.getProductAdministrationVOList();
      EntityProductBenefitAdministration administrationPSBO = new EntityProductBenefitAdministration();
      List benefitAdministrationBOList = null;
      if (null != productAdministrationVOList)
      {
      	int productAdministrationVOListSize = productAdministrationVOList.size();
      	benefitAdministrationBOList  =  new ArrayList(productAdministrationVOListSize);
        for (int i = 0; i < productAdministrationVOListSize; i++)
        {

          // Get the individual BenefitAdministrationVOs
          BenefitAdministrationOverrideVO administrationVO = (BenefitAdministrationOverrideVO) productAdministrationVOList.get(i);
          EntityProductAdministration administration = new EntityProductAdministration();
          // Set the values to the BO from the VO
          administration.setAnswerDesc(administrationVO.getAnswerDesc());
          administration.setAnswerId(administrationVO.getAnswerId());
          administration.setQuestionNumber(administrationVO.getQuestionId());
          administration.setEntityId(administrationRequest.getProductKeyObject().getProductId());
          administration.setEntityType(WebConstants.PRODUCT);
          administration.setAdminOptSysId(administrationRequest.getAdminSysId());
          benefitAdministrationBOList.add(administration);
        }
      }
      administrationPSBO.setBenefitAdministrationList(benefitAdministrationBOList);
      bom.persist(administrationPSBO, product, administrationRequest.getUser(), false);
      
      updateValidationForAdminOptions(administrationRequest);
      
      ProductAdminOptionLocateCriteria productLocateCriteria = new ProductAdminOptionLocateCriteria();
      productLocateCriteria.setEntityId(administrationRequest.getProductKeyObject().getProductId());
      // Set the value of entity Type in the locate criteria
      productLocateCriteria.setEntiityType(WebConstants.PRODUCT);
      // Set the value of admin option sys id in the locate criteria
      productLocateCriteria.setAdminOptSysId(administrationRequest.getAdminSysId());
      // Call the locate method of the builder passing the
      // to get the output list
      LocateResults locateResults = productBusinessObjectBuilder.locate(productLocateCriteria, administrationRequest.getUser());
      // Set the list to the response
      administrationResponse.setProductAdminList(locateResults.getLocateResults());
      // Set the update success message to the response
      List messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.ADMIN_OPTION_UPDATE));
      addMessagesToResponse(administrationResponse, messageList);

    }
    catch (WPDException ex)
    {
      List logParameters = new ArrayList(1);
      logParameters.add(administrationRequest);
      String logMessage = "Failed while processing SaveProductAdministrationRequest";
      throw new ServiceException(logMessage, logParameters, ex);
    }
    Logger.logInfo("Returning execute method for saving benefit administration");
    return administrationResponse;
  }

  /**
   * 
   * @param ruleType
   * @param ruleDesc
   * @param productRuleCodeMap
   * @return
   * @throws SevereException
   */
  private String getRuleCode(String ruleType,String ruleDesc,Map productRuleCodeMap) throws SevereException{
 	return (String)((Map)productRuleCodeMap.get(ruleType)).get(ruleDesc.toUpperCase());
}
  /**
 * @param administrationRequest
 * @throws ServiceException
 */
private void updateValidationForAdminOptions(SaveProductAdministrationRequest request) throws ServiceException {
	
	// Removed the admin method validations at product level.
//	if(null != request){
//		  if(request.isAnswersChanged()){
//				AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//				validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//				validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//				validationRqst.setChangedIds(request.getChangedAnswerIds());
//				validationRqst.setAdminSysId(request.getAdminSysId());
//				validationRqst.setLevel(AdminMethodSynchronousValidationRequest.PRDCT_ADMIN_OPTION_QSTNS_SAVE);
//				
//				AdminMethodSynchronousValidationResponse validationResponse = 
//						(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		  }
//	}
}
private void updateValidationForAdminOptions(UpdateProductAdministrationRequest request) throws ServiceException {
	
//	 Removed the admin method validations at product level.
//	if(null != request){
//		  if(request.isQstnsChanged()){
//				AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//				validationRqst.setEntityId(request.getProductKeyObject().getProductId());
//				validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//				validationRqst.setChangedIds(request.getChangedIds());
//				validationRqst.setAdminSysId(request.getAdminlevelOptionSysId());
//				validationRqst.setLevel(AdminMethodSynchronousValidationRequest.PRDCT_ADMIN_OPTION_QSTNS_SAVE);
//				
//				AdminMethodSynchronousValidationResponse validationResponse = 
//						(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		  }
//	}
}

/**
   * Service method for Denial and Exclusion to product.
   * 
   * @param request
   *            ProductRuleRefDataRequest
   * @return ProductRuleRefDataResponse
   * @throws WPDException
   */
  public WPDResponse execute(ProductRuleRefDataRequest request) throws ServiceException
  {
    try
    {
      ProductRuleRefDataResponse productRuleRefDataResponse = (ProductRuleRefDataResponse) WPDResponseFactory.getResponse(WPDResponseFactory.PRODUCT_RULE_REF_DATA_RESPONSE);

      List refList = null;

      BusinessObjectManager bom = getBusinessObjectManager();
      List messageList = new ArrayList(3);

      ProductBO product = getProductBO(request);
      int action = request.getAction();
      ProductRuleRefDataLocateCriteria locateCriteria = new ProductRuleRefDataLocateCriteria();
      LocateResults locateResults;

      switch (action)
      {
        case ProductRuleRefDataRequest.RULE_TYPE:
          locateCriteria.setQueryName(WebConstants.RULE_TYPE);
          locateResults = bom.locate(locateCriteria, request.getUser());
          refList = locateResults.getLocateResults();
          if (null == refList || 0 == refList.size())
          {
            messageList.add(new InformationalMessage(BusinessConstants.PRODUCT_RULE_TYPE_NOT_FOUND));
          }
          break;
        case ProductRuleRefDataRequest.RULE_ID:
          locateCriteria.setQueryName(WebConstants.RULE_ID);
          locateCriteria.setRuleType(request.getRuleType());
          locateResults = bom.locate(locateCriteria, request.getUser());
          refList = locateResults.getLocateResults();
          if (null == refList || 0 == refList.size())
          {
            messageList.add(new InformationalMessage(BusinessConstants.PRODUCT_RULE_ID_NOT_FOUND));
          }
          break;
        case ProductRuleRefDataRequest.PRODUCT_RULES_RETRIEVE:
          ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
          Map params = new HashMap();
          // fix added 
          //retrieving total specific (um/deniel/exclusion/pnr)rule associated with product 
          int  rulecount = builder.getProductRuleCount(product.getProductKey(),request.getRuleType());
          //processing for find out current totla number of page 
          double tempCount = (double)rulecount;
          double count = Math.ceil(tempCount/WebConstants.REORDS_PER_PAGE);
          int totalPageCount = (int)count;
          if(request.getPageno()>totalPageCount){//comparing with current number of page and previous page 
          	request.setPageno(totalPageCount);
          }
          params.put(BusinessConstants.ACTION, BusinessConstants.RETRIEVE_DENIAL_EXCLUSION);
          params.put(WebConstants.RULE_TYPE, request.getRuleType());
          params.put(WebConstants.PAGE_NO,new Integer((request.getPageno())));
          product = (ProductBO) bom.retrieve(product, request.getUser(), params);
          refList = product.getDenialAndExclusionList();
          productRuleRefDataResponse.setRuleCount(rulecount);
          productRuleRefDataResponse.setPageNum(request.getPageno());
          break;
        default:
          return null;
      }

      productRuleRefDataResponse.setRuleList(refList);
      productRuleRefDataResponse.setMessages(messageList);
      return productRuleRefDataResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for save Denial and Exclusion to product.
   * 
   * @param saveProductRulesRequest
   * @return SaveProductRulesResponse
   * @throws WPDException
   */
  public WPDResponse execute(SaveProductRulesRequest saveProductRulesRequest) throws ServiceException
  {
    try
    {
      SaveProductRulesResponse saveProductRulesResponse = (SaveProductRulesResponse) WPDResponseFactory.getResponse(WPDResponseFactory.SAVE_PRODUCT_RULES_RESPONSE);
      List messageList = new ArrayList(1);
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(saveProductRulesRequest);
      User user = saveProductRulesRequest.getUser();
      ProductBusinessObjectBuilder productBusinessObjectBuilder=new ProductBusinessObjectBuilder();
      ProductRuleAssociationBO productRuleAssociationBO = null;
      int action = saveProductRulesRequest.getAction();
      ProductRuleAssociation productRuleAssociation;
      int productKey = product.getProductKey();
      List rulesList = null;
      switch (action)
      {
        case SaveProductRulesRequest.ADD_PRODUCT_RULES:
          productRuleAssociation = new ProductRuleAssociation();
          
          String genRuleID = "";
          String providerArrangement = "";
          Map productRuleCodeMap=productBusinessObjectBuilder.retrieveProductRuleCodeMap();
          List pvaList=saveProductRulesRequest.getPvaList();
          List ruleIdList=saveProductRulesRequest.getRulesIdList();
          rulesList=new ArrayList();
          for(int i=0;i<pvaList.size();i++)
          	for(int j=0;j<ruleIdList.size();j++){
          		String pvaCode=getRuleCode(BusinessConstants.RULE_PVA_PARAM_TYPE,(String)pvaList.get(i),productRuleCodeMap);
          		String ruleCode=getRuleCode(BusinessConstants.RULE_PARAM_TYPE,saveProductRulesRequest.getRuleType(),productRuleCodeMap);
          		rulesList.add((String)pvaList.get(i)+"~"+ruleIdList.get(j));
          		rulesList.add(ruleCode + pvaCode + ruleIdList.get(j));
          	}
          saveProductRulesRequest.setRulesList(rulesList);		

          //              Validation
          saveProductRulesResponse = (SaveProductRulesResponse) new ValidationServiceController().execute(saveProductRulesRequest);
          if (!saveProductRulesResponse.isValid())
            return saveProductRulesResponse;
          if (null != saveProductRulesResponse.getValidateRulesList())
          {
            Iterator iterComponent = saveProductRulesResponse.getValidateRulesList().iterator();
        	rulesList = new ArrayList(saveProductRulesResponse.getValidateRulesList().size());
            while (iterComponent.hasNext())
            {
              providerArrangement = ((String) iterComponent.next()).toString();
              String pva= providerArrangement.split("~")[0];
              String ruleid=providerArrangement.split("~")[1];
              genRuleID = ((String) iterComponent.next()).toString();

              productRuleAssociationBO = new ProductRuleAssociationBO();
              productRuleAssociationBO.setProviderArrangement(pva);
              productRuleAssociationBO.setGenRuleID(genRuleID);
              productRuleAssociationBO.setRuleID(ruleid);
              productRuleAssociationBO.setProductKey(productKey);
              productRuleAssociationBO.setFlag(WebConstants.FLAG_N);

              rulesList.add(productRuleAssociationBO);
            }
          }
          productRuleAssociation.setRulesList(rulesList);

          bom.persist(productRuleAssociation, product, user, true);
          messageList.add(new InformationalMessage(BusinessConstants.MSG_PRODUCT_RULE_SAVE_SUCCESS));
          saveProductRulesResponse.setMessages(messageList);
          saveProductRulesResponse.setSuccess(true);
          break;
        case SaveProductRulesRequest.UPDATE_PRODUCT_RULES:
          productRuleAssociation = new ProductRuleAssociation();
          Map tempMap = (HashMap) saveProductRulesRequest.getRulesList().get(0);
          rulesList = new ArrayList();
          Iterator ruleIDIter = tempMap.keySet().iterator();
          int proRuleSysID;
          String ruleComment;
          while (ruleIDIter.hasNext())
          {
            Object key = ruleIDIter.next();
            proRuleSysID = Integer.parseInt(key.toString());
            ruleComment = tempMap.get(key).toString().trim();
            /*if (!ruleComment.equals(WebConstants.EMPTY_STRING))
            {*/
              productRuleAssociationBO = new ProductRuleAssociationBO();
              productRuleAssociationBO.setProductRuleSysID(proRuleSysID);
              productRuleAssociationBO.setRuleComment(ruleComment);
              productRuleAssociationBO.setProductKey(productKey);

              rulesList.add(productRuleAssociationBO);
            //}
          } //end while
          productRuleAssociation.setRulesList(rulesList);

          bom.persist(productRuleAssociation, product, user, false);
          messageList.add(new InformationalMessage(BusinessConstants.MSG_PRODUCT_RULE_UPDATE_SUCCESS));
          saveProductRulesResponse.setMessages(messageList);
          saveProductRulesResponse.setSuccess(true);
          break;
      }

      return saveProductRulesResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for delete Denial and Exclusion to product.
   * 
   * @param request
   *            DeleteProductRuleRequest
   * @return DeleteProductRuleResponse
   * @throws WPDException
   */
  public WPDResponse execute(DeleteProductRuleRequest request) throws ServiceException
  {
    try
    {
      DeleteProductRuleResponse deleteProductRuleResponse = (DeleteProductRuleResponse) WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_RULE_RESPONSE);
      List messageList = null;
      BusinessObjectManager bom = getBusinessObjectManager();
      ProductBO product = getProductBO(request);

      ProductRuleAssociationBO productRuleAssociationBO = new ProductRuleAssociationBO();
      productRuleAssociationBO.setProductRuleSysID(request.getProductRuleSysID());
      bom.delete(productRuleAssociationBO, product, request.getUser());

      InformationalMessage message = new InformationalMessage(BusinessConstants.MSG_PRODUCT_RULE_DELETE_SUCCESS);
      message.setParameters(new String[]
          { request.getEwpdGenRuleID() });
      messageList = new ArrayList(1);
      messageList.add(message);
      deleteProductRuleResponse.setMessages(messageList);
      deleteProductRuleResponse.setSuccess(true);
      return deleteProductRuleResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for manfate information to product.
   * 
   * @param productBenefitMandateRetrieveRequest
   * @return
   * @throws WPDException
   */
  public WPDResponse execute(ProductBenefitMandateRetrieveRequest productBenefitMandateRetrieveRequest) throws ServiceException
  {
    try
    {
      ProductBenefitMndateRetrieveResponse productBenefitMndateRetrieveResponse = 
        (ProductBenefitMndateRetrieveResponse) WPDResponseFactory.getResponse(WPDResponseFactory.PRODUCT_BENEFIT_MANDATE_RETRIEVE_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      LocateResults locateResults;
      List benefitMandateBOImplList = new ArrayList();
      BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();
      ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria = new ProductBenefitDefinitionLocateCriteria();
      productBenefitDefinitionLocateCriteria.setBenefitComponentId(productBenefitMandateRetrieveRequest.getBenefitComponentId());
      productBenefitDefinitionLocateCriteria.setBenefitId(productBenefitMandateRetrieveRequest.getBenefitId());
      productBenefitDefinitionLocateCriteria.setProductId(productBenefitMandateRetrieveRequest.getProductId());
      productBenefitDefinitionLocateCriteria.setType(WebConstants.MANDATE_INFO_VIEW);
      locateResults = bom.locate(productBenefitDefinitionLocateCriteria, productBenefitMandateRetrieveRequest.getUser());
      benefitMandateBOImplList.add(0, benefitMandateBOImpl);
      benefitMandateBOImplList = locateResults.getLocateResults();
      benefitMandateBOImpl = (BenefitMandateBO) benefitMandateBOImplList.get(0);
      productBenefitMndateRetrieveResponse.setBenefitMandateBO(benefitMandateBOImpl);
      return productBenefitMndateRetrieveResponse;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * Service method for delete benefit level question.
   * 
   * @param request
   *            DeleteProductRuleRequest
   * @return DeleteProductRuleResponse
   * @throws WPDException
   */
  public

  WPDResponse execute(QuestionDeleteRequest request) throws ServiceException
  {
    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      QuestionDeleteResponse response = (QuestionDeleteResponse) WPDResponseFactory.getResponse(WPDResponseFactory.QUESTION_DELETE_RESPONSE);
      ProductBO product = getProductBO(request);

      EntityBenefitAdministration entityBenefitAdminBO = new EntityBenefitAdministration();
      entityBenefitAdminBO.setQuestionNumber(Integer.parseInt(request.getQuestionID()));
      entityBenefitAdminBO.setEntityId(product.getProductKey());
      entityBenefitAdminBO.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
      entityBenefitAdminBO.setAdminLevelOptionAssnId(request.getAdminLevelOptionAssnId());
      entityBenefitAdminBO.setBeneftComponentId(request.getBenefitComponentId());

      BusinessObjectManager bom = getBusinessObjectManager();
      bom.delete(entityBenefitAdminBO, product, request.getUser());
      List messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.MSG_PRDCT_QUESTION_DELETE));
      response.setMessages(messageList);
      Logger.logInfo("Returning  from execute method for request=" + response.getClass().getName());
      return response;
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
  }


  /**
   * This method get values from SaveAdminOptionRequest and is set in
   * AdministrationOptionBO so as to persist the Admin Option details of a particular
   * product
   * 
   * 
   * @param request
   * @return
   * @throws ServiceException
   */
  public WPDResponse execute(SaveAdminOptionRequest request) throws ServiceException
  {
    SaveAdminOptionResponse response = null;

    try
    {
      Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
      List messageList = null;
      response = (SaveAdminOptionResponse) WPDResponseFactory.getResponse(WPDResponseFactory.ADMIN_OPTION_RESPONSE);
      BusinessObjectManager bom = getBusinessObjectManager();
      HideAdminOptionBO optionBO = null;
      List adminOverideList = request.getAdminOveriddenList();
      Iterator iterAdminOveride = adminOverideList.iterator();
      ProductBO product = getProductBO(request);
      product.setProductKey(request.getProductKeyObject().getProductId());

      List adminBOList = null;
      if(null!=adminOverideList){
      	adminBOList = new ArrayList(adminOverideList.size());
      }
      AdminOptionHideVO adminOptionVO = null;

      while (iterAdminOveride.hasNext())
      {
        adminOptionVO = (AdminOptionHideVO) iterAdminOveride.next();
        optionBO = new HideAdminOptionBO();

        optionBO.setAdminOptionSystemId(adminOptionVO.getAdminOptionId());
        optionBO.setAdminLevelSystemId(adminOptionVO.getAdminLevelId());
        optionBO.setBenefitLevelSysId(adminOptionVO.getBenefitLevelId());
        optionBO.setAdminQuestionHideFlag(adminOptionVO.getQuestionHideFlag());
        optionBO.setQuestionHideFlag(adminOptionVO.getQuestionHideFlag());

        optionBO.setEntityType(request.getProductType());
        optionBO.setEntityId(request.getProductSysId());

        optionBO.setBenefitCompSysId(request.getBenefitComponentId());
        optionBO.setAdminLevelOptionAssnSystemId(adminOptionVO.getAdmnLvlAsscId());
        optionBO.setCreatedUser(request.getUser().getUserId());
        optionBO.setLastUpdatedUser(request.getUser().getUserId());
        optionBO.setBenefitAdminSystemId(adminOptionVO.getBenefitAdminId());
        adminBOList.add(optionBO);
      }

      optionBO.setAdminBOList(adminBOList);
      User user = request.getUser();
      //calls the persist
      bom.persist(optionBO, product, user, false);
      
      updateAMVForProductBenefitAdminOptions(request);
      
      messageList = new ArrayList(1);
      messageList.add(new InformationalMessage(BusinessConstants.ADMIN_OPTION_UPDATE_SUCCESS));
      response.setMessages(messageList);

      AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
      criteria.setBenefitAdministrationSystemId(request.getBenefitAdminId());
      criteria.setBenefitComponentId(request.getBenefitComponentId());

      criteria.setEntityId(request.getProductSysId());
      criteria.setEntityType(request.getProductType());
      criteria.setPSorProduct(request.getPSorProductorBenefit());
      LocateResults locateResults = bom.locate(criteria, request.getUser());
      response.setAdminOptionList(locateResults.getLocateResults());

      Logger.logInfo("Returning from execute method for request=" + request.getClass().getName());
    }
    catch (Exception ex)
    {
      List logParameters = new ArrayList(2);
      logParameters.add(request);
      String logMessage = BusinessConstants.MSG_LOG;
      throw new ServiceException(logMessage, logParameters, ex);
    }
    return response;
  }


/**
 * @param request
 * @throws ServiceException
 */
private void updateAMVForProductBenefitAdminOptions(SaveAdminOptionRequest request) throws ServiceException {
	
	// removed the admin method validations at product level.
//	 if(request.isAdminOptionsChanged()){
//		AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//		validationRqst.setBenefitComponentId(request.getBenefitComponentId());
//		validationRqst.setBenefitAdministrationId(request.getBenefitAdminId());
//		validationRqst.setEntityId(request.getProductSysId());
//		validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//		validationRqst.setChangedIds(request.getChangedAdminOptions());
//		validationRqst.setLevel(AdminMethodSynchronousValidationRequest.ADMIN_OPTION_SAVE_IN_BENEFIT_ADMIN);
//		validationRqst.setBenefitCompName(request.getBenefitCompName());
//		validationRqst.setBenefitId(request.getBenefitSysId());
//		AdminMethodSynchronousValidationResponse validationResponse = 
//				(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//	 }
	
}

/**
 * This method is used to get the Questionnaire list from the table
 * 
 * @param RetrieveBenefitComponentQuestionnairRequest
 * 		  request for loading initial questionnare,changedquestionnaire and view result 
 * @return LocateComponentsBenefitAdministrationResponse
 * 		  response will hold the questionnaire List
 * @throws WPDException
 */
public WPDResponse execute(
		RetrieveProductQuestionareRequest request)
        throws ServiceException {
    try {
    	        
    	
        Logger.logInfo("Inside  Benefit Administration Search Service");
        RetrieveProductQuestionareResponse response = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductAdministrationLocateCriteria locateCriteria = new ProductAdministrationLocateCriteria();
        response = (RetrieveProductQuestionareResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_QUESTIONNAIRE_RESPONSE);
        int action = request.getAction();
        switch(action){
         case RetrieveProductQuestionareRequest.LOAD_QUESTIONNARE_LIST:
        locateCriteria.setAction(ProductAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST);
        locateCriteria.setAdmnLvlAsscId(request.getAdmnLvlOptionAsscId());
        locateCriteria.setAdminOptSysId(request.getAdminOptionSysId());
        locateCriteria.setBenefitComponentId(request.getBenefitComponentSysId());
        locateCriteria.setBenefitId(request.getBenefitId());
        locateCriteria.setEntityId(request.getProductSysId());
        locateCriteria.setEntiityType(BusinessConstants.PRODUCT);
        //   	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
        locateCriteria.setBenefitAdminSysId(request.getBenftAdminSysId());
        locateCriteria.setProductPrntSysId(request.getProductPrntSysId());
        locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
    	 break;
         case RetrieveProductQuestionareRequest.LOAD_SELECTED_CHILD:
    	 locateCriteria.setAction(ProductAdministrationLocateCriteria.LOAD_SELECTED_CHILD);
         locateCriteria.setAdmnLvlAsscId(request.getAdmnLvlOptionAsscId());
    	 locateCriteria.setAdminOptSysId(request.getAdminOptionSysId());
         locateCriteria.setBenefitComponentId(request.getBenefitComponentSysId());
         locateCriteria.setProductQuestionareBO(request.getProductQuestionareBO());
         locateCriteria.setAnswerId(request.getSelectedAnswerId());
         locateCriteria.setProductPrntSysId(request.getProductPrntSysId());
         locateCriteria.setQuestionnareList(request.getQuestionnareList());
         locateCriteria.setQuestionareListIndex(request.getQuestionareListIndex());
         locateCriteria.setEntityId(request.getProductSysId());
         locateCriteria.setAnswerDesc(request.getSelectedAnswerDesc());
         locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());
    	break;
    	case RetrieveProductQuestionareRequest.LOAD_SELECTED_CHILD_TIER:
    	locateCriteria.setAction(ProductAdministrationLocateCriteria.LOAD_SELECTED_CHILD_TIER);
        locateCriteria.setAdmnLvlAsscId(request.getAdmnLvlOptionAsscId());
   	 	locateCriteria.setAdminOptSysId(request.getAdminOptionSysId());
        locateCriteria.setBenefitComponentId(request.getBenefitComponentSysId());
        locateCriteria.setProductQuestionareBO(request.getProductQuestionareBO());
        locateCriteria.setAnswerId(request.getSelectedAnswerId());
        locateCriteria.setProductPrntSysId(request.getProductPrntSysId());
        locateCriteria.setQuestionnareList(request.getQuestionnareList());
        locateCriteria.setQuestionareListIndex(request.getQuestionareListIndex());
        locateCriteria.setEntityId(request.getProductSysId());	
        locateCriteria.setAnswerDesc(request.getSelectedAnswerDesc());
        locateCriteria.setAllPossibleAnswerMap(request.getAllPossibleAnswerMap());	
    		
    	break;	
         /*case RetrieveProductQuestionareRequest.LOAD_QUESTIONNARE_VIEW:
       	 locateCriteria.setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW);
         locateCriteria.setAdmnLvlAsscId(request.getAdmnLvlOptionAsscId());
         locateCriteria.setAdminOptSysId(request.getAdminOptionSysId());
         locateCriteria.setBenefitAdminSysId(request.getBenefitComponentSysId());
         locateCriteria.setEntityId(request.getProductSysId());
       	break;*/
        }
        LocateResults locateResults = bom.locate(locateCriteria, request
                .getUser());
        response.setQuestionareList(locateResults
                .getLocateResults());
        
        return response;
    } catch (WPDException ex) {
        
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing LocateComponentsBenefitAdministrationRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    }
}

/**
 * Method to Save BenefitComponentQuestionnare
 * 
 * @param UpdateComponentsBenefitAdministrationRequest
 * 		  request contains list of Questionnaire,benefitcomponentId,AdminleveloptionSysid 
 * @return UpdateComponentsBenefitAdministrationResponse
 * 		  response contain suucees full message
 * @throws WPDException
 */
public WPDResponse execute(
		UpdateProductQuestionareRequest request)
        throws ServiceException {
	
	    
	List messageList = new ArrayList(2);
    Logger.logInfo("Inside Update Admistration Benefit Component Service");
    UpdateProductQuestionareResponse response = null;
    BusinessObjectManager bom = getBusinessObjectManager();
    ProductBO productBO = new ProductBO();
    productBO.setProductName(request.getMainObjectIdentifier());
    productBO.setBusinessDomains(request.getDomainList());
    productBO.setVersion(request.getVersionNumber());
    List questionnareList = request.getQuestionnareList();
   
    SaveProductQuestionareBO administrationBO = new SaveProductQuestionareBO();
    administrationBO.setQuestionnareList(request.getQuestionnareList());
    administrationBO.setTierList(request.getTierList());
    
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
    administrationBO.setNewQuestions(request.getNewQuestions());
    administrationBO.setModifiedQuestions(request.getModifiedQuestions());
    administrationBO.setRemovedQuestions(request.getRemovedQuestions());
    administrationBO.setNewTieredQuestions(request.getNewTieredQuestions());
    administrationBO.setModifiedTieredQuestions(request.getModifiedTieredQuestions());
    administrationBO.setRemovedTieredQuestions(request.getRemovedTieredQuestions());
    
    administrationBO.setProductSysId(request.getEntityId());
    administrationBO.setAdminLevelOptionSysId(request.getAdminlevelOptionSysId());
    administrationBO.setProductPrntSysId(request.getProductPrntSysId());
    administrationBO.setProductPrntSysId(request.getProductPrntSysId());
    administrationBO.setBenefitComponentid(request.getBenefitComponentId());
    try {
        bom.persist(administrationBO, productBO,
                request.getUser(), false);
        updateAMVForProductBenefitAdministration(request);
        messageList.add(new InformationalMessage(BusinessConstants.MSG_QUESTIONNARE_SAVE_BC_SUCCESS));
        
    } catch (SevereException ex) {
    	Logger.logError(ex);
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing UpdateProductQuestionareRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    } catch (WPDException ex) {
    	Logger.logError(ex);
        List logParameters = new ArrayList(1);
        logParameters.add(request);
        String logMessage = "Failed while processing UpdateProductQuestionareRequest";
        throw new ServiceException(logMessage, logParameters, ex);
    }

    response = (UpdateProductQuestionareResponse) WPDResponseFactory
            .getResponse(WPDResponseFactory.UPDATE_PRODUCT_QUESTIONNAIRE_RESPONSE);
    response.setMessages(messageList);
    
    return response;
}

/**
 * @param request
 * @throws ServiceException
 */
private void updateAMVForProductBenefitAdministration(UpdateProductQuestionareRequest request) throws ServiceException {
	
	// removed the admin method validations at product level
//	if(null != request ){
//		if(request.isQstnsChanged()){
//			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setBenefitComponentId(request.getBenefitComponentId());
//			validationRqst.setBenefitAdministrationId(request.getBenefitAdminId());
//			validationRqst.setBenefitId(request.getBenefitId());
//			validationRqst.setEntityId(request.getProductPrntSysId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			validationRqst.setChangedIds(request.getChangedIds());
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION);
//			validationRqst.setBenefitCompName(request.getBCompName());
//			//CARS start
//			validationRqst.setChangedTiers(request.getChangedTierQuesIds());
//			validationRqst.setChangedTierSysIds(request.getChangedProdTierIds());
//			//CARS end
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		}
//	}
	
}
/**
 * @param request
 * @throws ServiceException
 */
private void updateAMVForProductAdministration(UpdateProductAdministrationRequest request) throws ServiceException {
//	if(null != request ){
//		if(request.isQstnsChanged()){
//			AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//			validationRqst.setBenefitComponentId(request.getBenefitComponentId());
//			validationRqst.setBenefitAdministrationId(request.getBenefitAdminId());
//			validationRqst.setBenefitId(request.getBenefitId());
//			validationRqst.setEntityId(request.getEntityId());
//			validationRqst.setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT);
//			validationRqst.setChangedIds(request.getChangedIds());
//			validationRqst.setLevel(AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION);
//			validationRqst.setBenefitCompName(request.getBCompName());
//			
//			AdminMethodSynchronousValidationResponse validationResponse = 
//					(AdminMethodSynchronousValidationResponse)new ValidationServiceController().execute(validationRqst);
//		}
//	}
//	
}
	/**
	 * 
	 * Retrieves the associated benefit components and benefits
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			RetrieveProductComponentHierarchyRequest request)
			throws ServiceException {
		Logger
				.logInfo("Entering execute method for retrieving component hierarchy");
		RetrieveProductComponentHierarchyResponse hierarchyResponse = null;
		BusinessObjectManager bom = getBusinessObjectManager();
		hierarchyResponse = (RetrieveProductComponentHierarchyResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_COMPONENT_HIERARCHY_RESPONSE);
		LocateResults locateResults = null;
		ComponentLocateCriteria locateCriteria = new ComponentLocateCriteria();
		locateCriteria.setProductKey(request.getProductKeyObject().getProductId());
		locateCriteria.setFlag(true);
		try {		
			locateResults =  bom.locate(
						locateCriteria, request
								.getUser());
			hierarchyResponse.setComponentHierarchyList(locateResults.getLocateResults());
				
			Logger
					.logInfo("Returning execute method for retrieving component hierarchy");
			
			return hierarchyResponse;
		} catch (WPDException ex) {
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveProdStructureComponentHierarchyRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
	}
	/**
	 * method for handling builder calls for overriding process of rule in product component level
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	
	public WPDResponse execute(SaveProductComponentRuleInformationRequest request) throws ServiceException{
		SaveProductComponentRuleInformationResponse response  = (SaveProductComponentRuleInformationResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_COMPONENT_RULE_RESPONSE);
		ProductComponentRule benefitComponents = new ProductComponentRule();
		benefitComponents.setProductId(request.getProductId());
		benefitComponents.setBenefitComponentId(request.getBenefitComponentId());
		benefitComponents.setRuleId(request.getRuleId());
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		try {
			builder.saveRule(benefitComponents,request.getUser().getUserId());
			List messageList = new ArrayList(2);
			messageList.add(new InformationalMessage(
					BusinessConstants.PRODUCT_BC_GENINFO_SAVE));
			addMessagesToResponse(response,
					messageList);
		} catch (SevereException e) {
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			String logMessage = "Failed while processing SaveProductComponentRuleInformationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		
		return response;
	}
	
	public WPDResponse execute(ProductQuestionNotesPopUpRequest request) throws SevereException{
		List messageList = null;
		List noteList =null;
		ProductQuestionNotesPopUpResponse response =(ProductQuestionNotesPopUpResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.PRODUCT_QUESTION_NOTES_POPUP_RESPONSE);
		NotesAttachmentOverrideBO overrideBO = new NotesAttachmentOverrideBO();
		overrideBO.setPrimaryEntityId(new Integer(request.getPrimaryEntityID()).intValue());
		overrideBO.setPrimaryEntityType(request.getPrimaryType());
		overrideBO.setSecondaryEntityId(new Integer(request.getSecondaryId()).intValue());
		overrideBO.setBnftDefnIdString(request.getBenefitDenId());
		overrideBO.setBenefitComponentId(request.getBenefitComponentId());
		overrideBO.setQuestionNumber(request.getQuestionId());
		overrideBO.setSecondaryEntityType(request.getSecondarEntityType());
		if(request.getSearchAction()==2){
			overrideBO.setSearchString(request.getSearchString());	
			overrideBO.setSearchAction(2);
		}	
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		noteList = builder.retrieveQuestionNotes(overrideBO);		
		if(null==noteList ||noteList.size()==0){
    	  	messageList = new ArrayList(2);
    	  	if(request.getSearchAction()==1){
    	  		messageList.add(new InformationalMessage(
        				BusinessConstants.RECORDS_NOT_FOUND));
    	  	}else {
    	  		messageList.add(new InformationalMessage(
        				BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
    	  	}
    	  }
		 addMessagesToResponse(response, messageList);
		if(null!=noteList)
			response.setNotesList(noteList);
		return response;
		
	}
	
	 /**
	 * This method will update the questionnaire taking in values from the
	 * UpdateQuestionnaireRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ProductQuestionNoteProcessRequest request)
			throws ServiceException {
		Logger
				.logInfo("ProductBusinessService - Entering execute(ProductQuestionNoteProcessRequest request)");
		ProductQuestionNoteResponse response = null;

		BusinessObjectManager bom = getBusinessObjectManager();

		List messageList = new ArrayList(2);
		
		ProductBO productBO = new ProductBO();
	    productBO.setProductName(request.getMainObjectIdentifier());
	    productBO.setBusinessDomains(request.getDomainList());
	    productBO.setVersion(request.getVersionNumber());
	    
		NotesToQuestionAttachmentBO attachmentBO = new NotesToQuestionAttachmentBO();
		attachmentBO.setPrimaryId(request.getPrimaryEntityId());
		attachmentBO.setPrimaryEntityType(request.getPrimaryType());
		attachmentBO.setSecondaryId(request.getSecondaryEntityId());
		attachmentBO.setSecondaryEntityType("ATTACHQUESTION");
		attachmentBO.setQuestionId(request.getQuestionId());
		attachmentBO.setNoteId(request.getNoteId());
		attachmentBO.setNoteVersionNumber(request.getNoteVersion());
		attachmentBO.setNoteOverrideStatus("F");
		attachmentBO.setRequestType(request.getNotesAction());
		attachmentBO.setQuestionId(request.getQuestionId());
		attachmentBO.setBenefitCompId(request.getBenefitComponentId());
		attachmentBO.setBnftDefId(new Integer(request.getBenefitDefnId()).toString());
		
		try {
			if(request.getNotesAction()==1){
			bom.persist(attachmentBO, productBO, request
					.getUser(), true);
			messageList.add(new InformationalMessage(
					BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS));
			}
			if(request.getNotesAction()==2){
				bom.persist(attachmentBO, productBO, request
						.getUser(), false);
				messageList.add(new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS));
			}
			if(request.getNotesAction()==3){
				bom.delete(attachmentBO, productBO, request
						.getUser());
				messageList.add(new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS));
			}
			
		} catch (SevereException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		response = (ProductQuestionNoteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.PRODUCT_QUESTION_NOTES__RESPONSE);
		response.setMessages(messageList);
		Logger
				.logInfo("ProductBenefitBusinessService - Exiting execute(ProductQuestionNoteProcessRequest request)");
		return response;
	}
	/**
	 * Mehtod used to insert/update/delete the attached notes in an Admin Option at product level
	 * @param contractAONotesToQuestionAttachmentRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ProductAONotesToQuestionAttachmentRequest notesToQuestionAttachmentRequest)
			throws ServiceException {

		ProductAONotesToQuestionAttachmentResponse notesToQuestionAttachmentResponse = (ProductAONotesToQuestionAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.PROD_AO_NOTES_TO_QUESTION_ATTACHMENT_RESPONSE);

		ContractProductAONotesAttachmentBO bo = setValuesToNotesToQuestionAttachmentBO(notesToQuestionAttachmentRequest);
		BusinessObjectManager bom =getBusinessObjectManager();

		ProductBO productBO= new ProductBO();
		productBO.setProductName(notesToQuestionAttachmentRequest.getNotesToQuestionAttachmentVO().getProdName());
		productBO.setBusinessDomains(notesToQuestionAttachmentRequest.getNotesToQuestionAttachmentVO().getBusinessDomainList());
		productBO.setVersion(notesToQuestionAttachmentRequest.getNotesToQuestionAttachmentVO().getProdVersion());

		List messageList = new ArrayList();

		try {
			if (notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isInsertRequest()) {
				bom.persist(bo, productBO,
						notesToQuestionAttachmentRequest.getUser(),
						true);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
			if (notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isUpdateRequest()) {
				bom.persist(bo, productBO,
						notesToQuestionAttachmentRequest.getUser(),
						false);
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
			if (notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isDeleteRequest()) {
				bom.delete(bo, productBO,
						notesToQuestionAttachmentRequest.getUser());
				InformationalMessage informationalMessage = new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
				messageList.add(informationalMessage);
			}
		} catch (WPDException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);
			
			if(notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isInsertRequest())
			throw new ServiceException(
					"Exception occured in persist method , for persisting the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessService for ContractAO",
					errorParams, exception);
	
			if(notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isUpdateRequest())
			throw new ServiceException(
					"Exception occured in persist method, for updating the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessService for ContractAO",
					errorParams, exception);

			if(notesToQuestionAttachmentRequest
					.getNotesToQuestionAttachmentVO().isDeleteRequest())
			throw new ServiceException(
					"Exception occured in delete method , for delete the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessService for ContractAO",
					errorParams, exception);

		}
		addMessagesToResponse(notesToQuestionAttachmentResponse,
				messageList);
		return notesToQuestionAttachmentResponse;
	}
	
	/**
	 * 
	 * @param benefitTierDeleteRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ProductTierDeleteRequest benefitTierDeleteRequest) throws ServiceException  {
		  Logger.logInfo("Entering execute method, request = " + benefitTierDeleteRequest.getClass().getName());
	      ProductTierDeleteResponse benefitTierDeleteResponse = (ProductTierDeleteResponse) 
	      			WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_PRODUCT_TIER_RESPONSE);
	      List messageList = null;
	      BusinessObjectManager bom = getBusinessObjectManager();
	      ProductBO product = getProductBO(benefitTierDeleteRequest);
	      BenefitTier productBenefitTier = new BenefitTier(benefitTierDeleteRequest.getProductTierSysId());
	      
	    try  {      
	     
	    // Trigger AM Validation	
	    	/* START CARS */
		     // updateAMVForTierProduct(benefitTierDeleteRequest);
		      /* END CARS */
	      
	      bom.delete(productBenefitTier, product, benefitTierDeleteRequest.getUser());
	      
	     
	      
	      messageList = new ArrayList(1);
	      messageList.add(new InformationalMessage(BusinessConstants.BENEFIT_TIER_DELETE));
	     
	    } catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(benefitTierDeleteRequest);
			String logMessage = "Failed while processing ProductTierDeleteRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		addMessagesToResponse(benefitTierDeleteResponse, messageList);
		 Logger.logInfo("Returning  from execute method for request=" + benefitTierDeleteRequest.getClass().getName());
	      return benefitTierDeleteResponse;
	  }

	private ContractProductAONotesAttachmentBO setValuesToNotesToQuestionAttachmentBO(
			ProductAONotesToQuestionAttachmentRequest request) {

		ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO = new ContractProductAONotesAttachmentBO();

		contractProductAONotesAttachmentBO.setRequestType(request
				.getNotesToQuestionAttachmentVO().getRequestType());
		
		contractProductAONotesAttachmentBO.setBenefitCompId(0);
		contractProductAONotesAttachmentBO.setBnftDefId(null);
		
		contractProductAONotesAttachmentBO.setCreatedUser(request.getUser()
				.getUserId());
		contractProductAONotesAttachmentBO.setNoteId(request
				.getNotesToQuestionAttachmentVO().getNoteId());
		contractProductAONotesAttachmentBO.setNoteOverrideStatus(request
				.getNotesToQuestionAttachmentVO().getNoteOverrideStatus());
		contractProductAONotesAttachmentBO.setNoteVersionNumber(request
				.getNotesToQuestionAttachmentVO().getNoteVersionNumber());
		contractProductAONotesAttachmentBO.setPrimaryEntityType(request
				.getNotesToQuestionAttachmentVO().getPrimaryEntityType());
		contractProductAONotesAttachmentBO.setPrimaryId(request
				.getNotesToQuestionAttachmentVO().getPrimaryId());
		contractProductAONotesAttachmentBO.setQuestionId(request
				.getNotesToQuestionAttachmentVO().getQuestionId());
		contractProductAONotesAttachmentBO.setSecondaryEntityType(request
				.getNotesToQuestionAttachmentVO().getSecondaryEntityType());
		contractProductAONotesAttachmentBO.setSecondaryId(request
				.getNotesToQuestionAttachmentVO().getSecondaryId());

		return contractProductAONotesAttachmentBO;
	}

	
	/***
	 * Deletes a Level from all Tiers
	 * @param deleteLevelFromTierRequest
	 * @return DeleteLevelFromTierResponse
	 * @throws ServiceException
	 */
	
	public WPDResponse execute(DeleteLevelFromTierRequest deleteLevelFromTierRequest) throws ServiceException  {
		Logger.logInfo("Entering execute method, request = " + deleteLevelFromTierRequest.getClass().getName());
		
		DeleteLevelFromTierResponse deleteLevelFromTierResponse = (DeleteLevelFromTierResponse)WPDResponseFactory.getResponse(WPDResponseFactory.DELETE_LEVEL_FROM_TIER_RESPONSE);
		
		List messageList = null;
		
		
		BusinessObjectManager bom = getBusinessObjectManager();
		ProductBO product = getProductBO(deleteLevelFromTierRequest);
		TierDefinitionBO tierDefinitionBO = new TierDefinitionBO();
		
		tierDefinitionBO.setProdSysId(deleteLevelFromTierRequest.getProductSysId());
		tierDefinitionBO.setBenCompSysId(deleteLevelFromTierRequest.getBenefitComponentSysId());
		tierDefinitionBO.setBenefitSysId(deleteLevelFromTierRequest.getBenefitSysId());
		tierDefinitionBO.setLevelSysId(deleteLevelFromTierRequest.getLevelSysId());
		
		try  {      
			
			bom.delete(tierDefinitionBO, product, deleteLevelFromTierRequest.getUser());
			
			
			messageList = new ArrayList(1);
			messageList.add(new InformationalMessage(BusinessConstants.LEVEL_IN_BENEFIT_TIER_DELETE));
			
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(deleteLevelFromTierRequest);
			String logMessage = "Failed while processing DeleteLevelFromTierRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		addMessagesToResponse(deleteLevelFromTierResponse, messageList);
		deleteLevelFromTierResponse.setStatusFlag(true);
		Logger.logInfo("Returning  from execute method for request=" + deleteLevelFromTierRequest.getClass().getName());
		return deleteLevelFromTierResponse;
		
	}
	


	/**
	 * This method will fetch the all tier definitions
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(TierDefinitionRetrieveRequest request) throws SevereException {
	
		Logger.logInfo("ProductBusinessService - Entering execute(TierDefinitionRequest request)");
		List tierDefsList = null;
		int productSysId;
		int benCompId;
	    int defnId;
		TierDefinitionRetrieveResponse response = (TierDefinitionRetrieveResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.BENEFIT_TIER_DEFINITION_RESPONSE);
		productSysId = request.getProductSysId();
		benCompId = request.getBenefitComponentSysId();
		defnId = request.getBenefitDefinitionSysId();
		
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		tierDefsList = builder.retrieveTierDefinitions(productSysId,benCompId,defnId);
		if(null!=tierDefsList)
			response.setBenefitTierDefinitonsList(tierDefsList);
		return response;
	}

	
	/**
	 * This service layer method will fetch the Tier Definitions for the Product from 
	 * PROD_TIER_DEFN_OVRD table.
	 * @param retrieveProductTierDefnRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(RetrieveProductTierDefnRequest retrieveProductTierDefnRequest)
    throws WPDException {
	    RetrieveProductTierDefnResponse retrieveProductTierDefnResponse = null;
		Map params = new HashMap();
		
		ProductBusinessObjectBuilder  productBusinessObjectBuilder 
							= new ProductBusinessObjectBuilder();
		retrieveProductTierDefnResponse = (RetrieveProductTierDefnResponse) WPDResponseFactory
		        .getResponse(WPDResponseFactory.PRODUCT_TIER_DEFN_RESPONSE);
		
		ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria 
					= new ProductBenefitDefinitionLocateCriteria();		
		
		productBenefitDefinitionLocateCriteria.setProductId(
		        retrieveProductTierDefnRequest.getProductId());
		productBenefitDefinitionLocateCriteria.setBenefitComponentId(
		        retrieveProductTierDefnRequest.getBenefitComponentId());
		productBenefitDefinitionLocateCriteria.setBenefitId(
		        retrieveProductTierDefnRequest.getBenefitId());		
		
		List bnftTierDefnList  = productBusinessObjectBuilder
				.getTierDescAndDefIdForProductBenefit(productBenefitDefinitionLocateCriteria
						        	,retrieveProductTierDefnRequest.getUser());	
		BenefitTierDefinitionVO benefitTierDefinitionVO = new BenefitTierDefinitionVO();
		if(null!=bnftTierDefnList && bnftTierDefnList.size()>0){		               
		    Iterator bnftTierDefnIterator = bnftTierDefnList.iterator();
		    StringBuffer tierDescriptionAndIdBuffer = new StringBuffer();		   
		    while(bnftTierDefnIterator.hasNext()){		    	          
		        ProductTierDefnOverrideBO productTierDefnOverrideBO = 
		            (ProductTierDefnOverrideBO)bnftTierDefnIterator.next();		        
		        tierDescriptionAndIdBuffer.append(productTierDefnOverrideBO.getTierDescription());
		        tierDescriptionAndIdBuffer.append("~");
		        tierDescriptionAndIdBuffer.append(productTierDefnOverrideBO.getTierDefinitionId());
		        if(bnftTierDefnIterator.hasNext()){	               
		            tierDescriptionAndIdBuffer.append("~");
		        }
		    }		   
		    /*contains tier description and Id.
		     * The string will look like TierDef1~1000~TierDef2~1001~TierDef3~1003  */		    
		    benefitTierDefinitionVO.setTierDescription(tierDescriptionAndIdBuffer.toString());
		}	        
		retrieveProductTierDefnResponse
		        .setBenefitTierDefinitionVO(benefitTierDefinitionVO);
		return retrieveProductTierDefnResponse;
}
		/**
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public WPDResponse execute(ProductTierDefSaveRequest request) throws SevereException, AdapterException {
		
		Logger.logInfo("ProductBusinessService - Entering execute(ProductTierDefSaveRequest request)");
		
		boolean saveStatus;
		int productSysId;
		int benefitCompSysId;
		int benefitCompDefId;
		int tierDefId;
		int levelId;
		Audit audit= new AuditImpl();
		
		String saveStr;
		String tierDefExists;
		BusinessObjectManager bom = getBusinessObjectManager();
		ProductTierDefSaveResponse response = (ProductTierDefSaveResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.PRODUCT_BENEFIT_TIER_DEF_SAVE_RESPONSE);
		
		productSysId = request.getProductSysId();
		benefitCompSysId = request.getBenefitComponentSysId();
		benefitCompDefId = request.getBenefitDefinitionSysId();
		tierDefId = request.getTierDefinitionId();
		levelId = request.getBenefitDefinitionLevelId();
		saveStr = request.getBenefitCriteriaSaveString();
		tierDefExists = request.getTierDefExits();
		audit.setUser(request.getUser().getUserId());
		Date now = new Date();
		audit.setTime(now);
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		
		saveStatus = builder.saveProductTierDefinition(productSysId,benefitCompSysId,benefitCompDefId,tierDefId,levelId,saveStr,tierDefExists,audit);
		response.setStatusFlag(saveStatus);
		
		return response;
	}
	public User getUser()
	 {
	 	UserImpl user = new UserImpl();
	 	user.setUserId("USER");	 	
	 	return user;
	 }
	
	
	/**
	 * This service layer method will save the Benefit General Information
	 * for a Product.
	 * @param updateProductBnftGenInfoRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(UpdateProductBenefitGeneralInformationRequest 
	         updateProductBnftGenInfoRequest) throws ServiceException {
	List messageList = new ArrayList();
    Logger
    .logInfo("ProductBusinessService - Entering execute(): " +
    		"UpdateProductBenefitGeneralInformationRequest");       
    UpdateProductBenefitGeneralInformationResponse updateProductBnftGenInfoResponse 
    = (UpdateProductBenefitGeneralInformationResponse) WPDResponseFactory
    .getResponse(WPDResponseFactory.UPDATE_PRODUCT_BNFT_GENINFO_RESPONSE);       
    ProductBenefitGeneralInformationVO productBnftGenInfoVO = 
    	updateProductBnftGenInfoRequest.getProductBenefitGeneralInformationVO();        
    if (null != updateProductBnftGenInfoResponse){          
        ProductBO product = getProductBO(updateProductBnftGenInfoRequest);	           
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
        		.getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
       
        // to update the TierDefns in the PROD_TIER_DEFN_OVRD
        ProductTierDefnOverrideBO productTierDefnOverrideBO = 
        			getProductTierDefnOverrideBO(productBnftGenInfoVO);       
			
		//to update the Rule in the PROD_BNFT_RULE_OVRD
		ProductRuleIdBO productRuleIdBO = getProductRuleIdBO(productBnftGenInfoVO);
		
		ProductBenefitGeneralInfoBO productBenefitGeneralInfoBO = new
					ProductBenefitGeneralInfoBO();
		productBenefitGeneralInfoBO.setProductTierDefnOverrideBO(productTierDefnOverrideBO);
		productBenefitGeneralInfoBO.setProductRuleIdBO(productRuleIdBO);
		try{
		    boolean resultOfUpdate = bom.persist(productBenefitGeneralInfoBO, product, 
            		updateProductBnftGenInfoRequest
                        .getUser(), false);  
		    updateProductBnftGenInfoResponse.setResultOfUpdate(resultOfUpdate);
			InformationalMessage informationalMessageRule = new InformationalMessage(
					BusinessConstants.PRODUCT_BENEFIT_GENINFO_SAVE);
			messageList.add(informationalMessageRule);			
		}
        catch (WPDException ex) {
	        List logParameters = new ArrayList(1);
	        logParameters.add(updateProductBnftGenInfoRequest);
	        String logMessage = "Failed while processing " +
	        		"UpdateProductBenefitGeneralInformationRequest";
	        throw new ServiceException(logMessage, logParameters, ex);
        }     
        Logger
			.logInfo("ProductBusinessService - Returning execute()" +
    		"Product Benefit General Information Update");
        addMessagesToResponse(updateProductBnftGenInfoResponse,messageList);
    }
    return updateProductBnftGenInfoResponse;
 }
	public WPDResponse execute(ProductQuestionTierNotesPopUpRequest request) throws SevereException{
		List messageList = null;
		List noteList =null;
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		ProductQuestionNotesPopUpResponse response =(ProductQuestionNotesPopUpResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.PRODUCT_QUESTION_NOTES_POPUP_RESPONSE);
		TierNotesAttachmentOverrideBO overrideBO = new TierNotesAttachmentOverrideBO();
		overrideBO.setPrimaryEntityId(new Integer(request.getPrimaryEntityID()).intValue());
		overrideBO.setPrimaryEntityType(request.getPrimaryType());
		overrideBO.setSecondaryEntityId(new Integer(request.getSecondaryId()).intValue());
		overrideBO.setBnftDefnIdString(request.getBenefitDenId());
		overrideBO.setBenefitComponentId(request.getBenefitComponentId());
		overrideBO.setQuestionNumber(request.getQuestionId());
		overrideBO.setSecondaryEntityType(request.getSecondarEntityType());		
		overrideBO.setTierSysId(request.getTierSysId());
		if(request.getAction()==2){
			if(request.getSearchAction()==2){
				overrideBO.setSearchString(request.getSearchString());	
				overrideBO.setSearchAction(2);
			}	
			
			noteList = builder.retrieveQuestionTiernNotes(overrideBO);	
			
			if(null==noteList ||noteList.size()==0){
				messageList = new ArrayList(2);
				if(request.getSearchAction()==1){
					messageList.add(new InformationalMessage(
							BusinessConstants.RECORDS_NOT_FOUND));
				}else {
					messageList.add(new InformationalMessage(
							BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
				}
			}
		}else{
			
			noteList = builder.retrieveQuestionTiernNotesForView(overrideBO);
			if(null==noteList ||noteList.size()==0){
				messageList = new ArrayList(2);
				messageList.add(new InformationalMessage(
						BusinessConstants.RECORDS_NOT_FOUND));
			}
			
		}
		 addMessagesToResponse(response, messageList);
		if(null!=noteList)
			response.setNotesList(noteList);
		return response;
		
	}
	
	 /**
	 * This method will update the questionnaire taking in values from the
	 * UpdateQuestionnaireRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ProductQuestionTierNoteProcessRequest request)
			throws ServiceException {
		Logger
				.logInfo("ProductBusinessService - Entering execute(ProductQuestionNoteProcessRequest request)");
		ProductQuestionNoteResponse response = null;

		BusinessObjectManager bom = getBusinessObjectManager();

		List messageList = new ArrayList(2);
		
		ProductBO productBO = new ProductBO();
	    productBO.setProductName(request.getMainObjectIdentifier());
	    productBO.setBusinessDomains(request.getDomainList());
	    productBO.setVersion(request.getVersionNumber());
	     
	    TierNotesAttachmentOverrideBO attachmentBO = new TierNotesAttachmentOverrideBO();
		attachmentBO.setPrimaryEntityId(request.getPrimaryEntityId());
		attachmentBO.setPrimaryEntityType(request.getPrimaryType());
		attachmentBO.setSecondaryEntityId(request.getSecondaryEntityId());
		attachmentBO.setSecondaryEntityType("ATTACHQUESTION");
		attachmentBO.setQuestionNumber(request.getQuestionId());
		attachmentBO.setNoteId(request.getNoteId());
		attachmentBO.setVersion(request.getNoteVersion());
		attachmentBO.setOverrideStatus("F");
		attachmentBO.setRequestType(request.getNotesAction());
		attachmentBO.setQuestionNumber(request.getQuestionId());
		attachmentBO.setBenefitComponentId(request.getBenefitComponentId());
		attachmentBO.setBnftDefnIdString(new Integer(request.getBenefitDefnId()).toString());
		attachmentBO.setTierSysId(request.getTierSysId());
		
		try {
			if(request.getNotesAction()==1){
			bom.persist(attachmentBO, productBO, request
					.getUser(), true);
			messageList.add(new InformationalMessage(
					BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS));
			}
			if(request.getNotesAction()==2){
				bom.persist(attachmentBO, productBO, request
						.getUser(), false);
				messageList.add(new InformationalMessage(
						BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS));
			}
			if(request.getNotesAction()==3){
				bom.delete(attachmentBO, productBO, request
						.getUser());
				messageList.add(new InformationalMessage(
						BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS));
			}
			
		} catch (SevereException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		} catch (WPDException ex) {
			Logger.logError(ex);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "Failed while processing ProductQuestionNoteProcessRequest in product business service";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		response = (ProductQuestionNoteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.PRODUCT_QUESTION_NOTES__RESPONSE);
		response.setMessages(messageList);
		Logger
				.logInfo("ProductBenefitBusinessService - Exiting execute(ProductQuestionNoteProcessRequest request)");
		return response;
	}
	
	/**
	 * This method will retreive the rule types taking in values from the
	 * RetreiveProductRuleTypeRequest
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetreiveProductRuleTypeRequest request)throws ServiceException {
		Logger.logInfo("ProductBusinessService - Entering execute(RetreiveProductRuleTypeRequest request)");
				
		List ruleList = null;
		List messageList = new ArrayList();
		
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		RetreiveProductRuleTypeResponse response =(RetreiveProductRuleTypeResponse)WPDResponseFactory.
			getResponse(WPDResponseFactory.RETREIVE_RULE_TYPE_PRODUCT_RESPONSE);
		RuleDetailBO ruleDetailBO = new RuleDetailBO();
		
		ruleDetailBO.setProductId(request.getProductSysId());
			
		try {
			ruleList = builder.getRuleListForProduct(ruleDetailBO);
		} catch (SevereException e) {
			Logger.logError(e);
			List logParameters = new ArrayList(1);
			logParameters.add(request);
			String logMessage = "SevereException occurred- ProductBusinessService - Entering execute(RetreiveProductRuleTypeRequest request";
			throw new ServiceException(logMessage, logParameters, e);			
		}	
		 
		if(null!=ruleList && !ruleList.isEmpty()){
			response.setProductRuleList(ruleList);
		}
		ErrorMessage message = new ErrorMessage(BusinessConstants.RULE_TYPE_VALIDATION);
		messageList.add(message);
		response.setMessages(messageList);
		
		return response;
	}
	
	/**
	 * The method creates ProductTierDefnOverrideBO,sets the necessary 
	 * parameters and returns it.
	 * @param productBnftGenInfoVO
	 * @return
	 */
	private ProductTierDefnOverrideBO getProductTierDefnOverrideBO(
				ProductBenefitGeneralInformationVO productBnftGenInfoVO){
		 List productTierDefinitionOvrdBOList =  new ArrayList(); 
	     ProductTierDefnOverrideBO mainProductTierDefnOverrideBO =  
	            new ProductTierDefnOverrideBO();         
	                      
         List tierDefintionsForProduct = productBnftGenInfoVO.
        								getTierDefinitionsList();
         if(null != tierDefintionsForProduct){
            for(int i=0;i<tierDefintionsForProduct.size();i++){            
                ProductTierDefnOverrideBO productTierDefnOverrideBO =  
                    new ProductTierDefnOverrideBO();         
                productTierDefnOverrideBO.setTierDefinitionId((
                        (Integer)tierDefintionsForProduct.get(i)).intValue());
                productTierDefnOverrideBO.setBenefitDefinitionId(
                		productBnftGenInfoVO.getBenefitDefinitionId());
                productTierDefnOverrideBO.setProductId(
                		productBnftGenInfoVO.getProductId());
                productTierDefnOverrideBO.setBenefitComponentId(
                		productBnftGenInfoVO.getBenefitComponentId());
                productTierDefinitionOvrdBOList.add(productTierDefnOverrideBO);
            } 
         }
         mainProductTierDefnOverrideBO.setProductTierDefinitionsList
         (productTierDefinitionOvrdBOList);
         mainProductTierDefnOverrideBO.setBenefitDefinitionId(
        		productBnftGenInfoVO.getBenefitDefinitionId());
         mainProductTierDefnOverrideBO.setProductId(
        		productBnftGenInfoVO.getProductId());
         mainProductTierDefnOverrideBO.setBenefitComponentId(
        		productBnftGenInfoVO.getBenefitComponentId());
         return mainProductTierDefnOverrideBO;
	}
	
	/**
	 * The method creates ProductRuleIdBO,sets the necessary parameters 
	 * and returns it.
	 * @param productBnftGenInfoVO
	 * @return
	 */
	private ProductRuleIdBO getProductRuleIdBO(ProductBenefitGeneralInformationVO 
			productBnftGenInfoVO){		
		ProductRuleIdBO productRuleIdBO = new ProductRuleIdBO();
		productRuleIdBO.setRuleId(productBnftGenInfoVO.getRuleId());
		productRuleIdBO.setProductId(productBnftGenInfoVO.getProductId());
		productRuleIdBO.setBenefitComponentId(productBnftGenInfoVO.getBenefitComponentId());
		productRuleIdBO.setBenefitId(productBnftGenInfoVO.getBenefitId());
		return productRuleIdBO;
	}	
}
