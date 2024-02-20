/*
 * ProductStructureBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.UncategorizedSQLException;

import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureBenefitComponentLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureMandatesLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureNotesLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ProductStructureShowHiddenLocateCriteria;
import com.wellpoint.wpd.business.productstructure.locatecriteria.ViewAllVersionsLocateCriteria;
import com.wellpoint.wpd.business.pva.builder.PVABusinessObjectBuilder;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdministrativeOptionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionHideVO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.ProductStructureBenefitCustomizedBO;
import com.wellpoint.wpd.common.override.benefit.vo.ProductStructureBenefitCustomizedVO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
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
import com.wellpoint.wpd.common.productstructure.request.AttachNotesToQuestionRequestForPS;
import com.wellpoint.wpd.common.productstructure.request.CheckoutProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.DeleteProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureLifeCycleRequest;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureNotesRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitComponentPopUpRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitComponentRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitDefenitionRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitMandatesRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveComponentFromTreeRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProdStructureComponentHierarchyRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureQuestionnaireRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureVersionsRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveAdminOptionRequestForPS;
import com.wellpoint.wpd.common.productstructure.request.SaveBenefitAdministrationRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureBenefitComponentRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureBenefitDefinitionRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.SearchProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.UpdateProductStructureBenefitAdministrationRequest;
import com.wellpoint.wpd.common.productstructure.response.AttachNotesToQuestionResponseForPS;
import com.wellpoint.wpd.common.productstructure.response.DeleteProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureLifeCycleResponse;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureNotesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitComponentPopUpResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitComponentResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitDefenitionResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitMandatesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveComponentFromTreeResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProdStructureComponentHierarchyResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureVersionsResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveAdminOptionResponseForPS;
import com.wellpoint.wpd.common.productstructure.response.SaveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureBenefitDefinitionResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.SearchProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.productstructure.vo.ProdStructNotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureLocateCriteriaVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.refdata.request.RefDataDomainValidationRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataDomainValidationResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBusinessService extends WPDService {
	
	boolean validateQuestionPva;

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
     * Retrieves benefit defenition general information
     * 
     * @param retrieveBenefitDefenitionRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveBenefitDefenitionRequest retrieveBenefitDefenitionRequest)
            throws ServiceException {
    	
//    	TimeHandler th = new TimeHandler();
//    	Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBusinessService","execute"));
    	
        Logger
                .logInfo("Entering execute method for retrieving Benefit Defenition");
        Map params = new HashMap();
        RetrieveBenefitDefenitionResponse retrieveBenefitDefenitionResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        retrieveBenefitDefenitionResponse = (RetrieveBenefitDefenitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_DEFENITION_RESPONSE);

        // Get the value of action from the request
        int action = retrieveBenefitDefenitionRequest.getAction();
        // Check the value of action to see the mode
        if (action == RetrieveBenefitDefenitionRequest.GET_BENEFIT_DEFINITION) {

            // Create an instance of the locate criteria
            ProductStructureBenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new ProductStructureBenefitDefinitionLocateCriteria();

            // Set the value of benefitId in the locate criteria from the
            // request
            benefitDefinitionLocateCriteria
                    .setStandardBenefitId(retrieveBenefitDefenitionRequest
                            .getStandardBenefitKey());

            // Set the value of benefitComponentId in the locate criteria from
            // the request
            benefitDefinitionLocateCriteria
                    .setBenefitComponentId(retrieveBenefitDefenitionRequest
                            .getBenefitComponentId());

            // Get the productStructureId from the VO set in the request
            int productStructureId = retrieveBenefitDefenitionRequest
                    .getProductStructureVO().getProductStructureId();

            // Set the value of productStructureId in the locate criteria
            benefitDefinitionLocateCriteria
                    .setProductStructureId(productStructureId);

            //Set the benefit level flag status for retrieving the benefit
            // definition
            benefitDefinitionLocateCriteria
                    .setBenefitLevelHideFlag(retrieveBenefitDefenitionRequest
                            .getBenefitLevelHideFlag());

            //Set the benefit line flag status for retrieving the benefit
            // definition
            benefitDefinitionLocateCriteria
                    .setBenefitLineHideFlag(retrieveBenefitDefenitionRequest
                            .getBenefitLineHideFlag());

            try {

                // Call the locate() of the builder to get the locateResults
                LocateResults locateResults = (LocateResults) bom.locate(
                        benefitDefinitionLocateCriteria,
                        retrieveBenefitDefenitionRequest.getUser());

                //				 Set the list of benefitDefinitions to the resposne
                retrieveBenefitDefenitionResponse
                        .setBenefitDefinitionList(locateResults
                                .getLocateResults());
            } catch (WPDException ex) {
                List logParameters = new ArrayList(1);
                logParameters.add(retrieveBenefitDefenitionRequest);
                String logMessage = "Failed while processing RetrieveBenefitDefenitionRequest";
                throw new ServiceException(logMessage, logParameters, ex);
            }

        } else {

            params.put(BusinessConstants.BENEFIT_KEY, new Integer(
                    retrieveBenefitDefenitionRequest.getStandardBenefitKey()));
            ProductStructureBO productStructureBO = getProductStructureBO(retrieveBenefitDefenitionRequest
                    .getProductStructureVO());
            try {
                productStructureBO = (ProductStructureBO) bom.retrieve(
                        productStructureBO, retrieveBenefitDefenitionRequest
                                .getUser(), params);
                ProductStructureAssociatedBenefitComponent benefitComponent = (ProductStructureAssociatedBenefitComponent) productStructureBO
                        .getAssociatedBenefitComponentList().get(0);
                retrieveBenefitDefenitionResponse
                        .setStandardBenefitBO(benefitComponent
                                .getStandardBenefitBO());
                retrieveBenefitDefenitionResponse.setDomainDetail(DomainUtil
                        .retrieveDomainDetailInfo(BusinessConstants.STDBENEFIT,
                                benefitComponent.getStandardBenefitBO()
                                        .getParentSystemId()));

            } catch (WPDException ex) {
                List logParameters = new ArrayList(1);
                logParameters.add(retrieveBenefitDefenitionRequest);
                String logMessage = "Failed while processing RetrieveBenefitDefenitionRequest for View";
                throw new ServiceException(logMessage, logParameters, ex);
            }
        }
        Logger
                .logInfo("Returning execute method for retrieving Benefit Defenition");
        
//        Logger.logInfo(th.endPerfLogging());
        return retrieveBenefitDefenitionResponse;
    }

    /**
     * Saves benefit defenition general information
     * 
     * @param SaveProductStructureBenefitDefinitionRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveProductStructureBenefitDefinitionRequest request)
            throws ServiceException {
    	
//    	TimeHandler th = new TimeHandler();
//    	Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBusinessService","execute(Save)"));
    	
        Logger
                .logInfo("Entering execute method for saving product structure benefit defenition");
        SaveProductStructureBenefitDefinitionResponse response = (SaveProductStructureBenefitDefinitionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_PRODUCT_STRUCTURE_BENEFIT_DEFINITION_RESPONSE);

        // Get the VO from the request
        ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(request
                .getProductStructureVO());

        //gets the updated line list from request
        List benefitLineVoList = request.getBenefitLinesList();
        List benefitLevelsToBeDeleted = request.getDeleteBenefitLevelList();
        //gets the benefit status from request
        String benefitHideFlag = request.getBenefitHideFlag();
        BusinessObjectManager bom = getBusinessObjectManager();
        User user = request.getUser();

        //creates the sub object
        ProductStructureBenefitDefinitions productStructureBenefitDefinitions = new ProductStructureBenefitDefinitions();
        List updatedLinesList = new ArrayList(benefitLineVoList == null ? 0
                : benefitLineVoList.size());
        try {
            //iterates the list to get the individual VOs n they r converted in
            // to BOs
            if (null != benefitLineVoList) {
                for (int i = 0; i < benefitLineVoList.size(); i++) {
                    ProductStructureBenefitCustomizedBO productBenefitDefinitionBO = new ProductStructureBenefitCustomizedBO();
                    ProductStructureBenefitCustomizedVO entityBenefitLine = (ProductStructureBenefitCustomizedVO) benefitLineVoList
                            .get(i);
                    productBenefitDefinitionBO
                            .setBenefitValue(entityBenefitLine
                                    .getOverridedValue());
                    productBenefitDefinitionBO
                            .setBenefitLineId(entityBenefitLine
                                    .getBenefitLineId());
                    productBenefitDefinitionBO
                            .setLineHideFlag(entityBenefitLine
                                    .getBenefitLineHideFlag());
                    productBenefitDefinitionBO.setBenefitComponentSysId(request
                            .getBenefitComponentId());
                    productBenefitDefinitionBO
                            .setBenefitCustomizedSysId(entityBenefitLine
                                    .getBenefitCustomizedSysId());
                    productBenefitDefinitionBO
                            .setLevelHideFlag(entityBenefitLine
                                    .getBenefitLevelHideFlag());
                    productBenefitDefinitionBO.setBenefitSysId(request
                            .getStandardBenefitKey());
                    productBenefitDefinitionBO
                            .setBenefitHideFlag(benefitHideFlag);
                    productBenefitDefinitionBO.setModified(entityBenefitLine
                            .isModified());
                    //CARS Start
                    //Setting the description value from VO to BO
                    productBenefitDefinitionBO.setBenefitLevelDescription(entityBenefitLine.getBenefitLevelDescription());
                    //Setting the frequency value from VO to BO
                    productBenefitDefinitionBO.setBenefitLevelFrequency(entityBenefitLine.getBenefitLevelFrequency());
                    //CARS End
                    updatedLinesList.add(productBenefitDefinitionBO);

                }
            }
            productStructureBenefitDefinitions
                    .setUpdatedBenefitLines(updatedLinesList);
            //calls the persist method for update
            bom.persist(productStructureBenefitDefinitions, productStructureBO,
                    user, false);

//            if (request.isFlagChanged()) {
//                AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//                validationRqst.setBenefitComponentId(request
//                        .getBenefitComponentId());
//                validationRqst.setEntityId(request.getProductStructureVO()
//                        .getProductStructureId());
//                validationRqst
//                        .setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT_STRUCTURE);
//                validationRqst.setChangedIds(request.getChangedIds());
//                validationRqst.setBenefitCompName(request.getBCompName());
//                validationRqst.setBenefitId(request.getStandardBenefitKey());
//                validationRqst
//                        .setLevel(AdminMethodSynchronousValidationRequest.BENEFITLEVELS_IN_ENTITY);
//
//                AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
//                        .execute(validationRqst);
//            }

            List deleteLevelList = new ArrayList(
                    benefitLevelsToBeDeleted == null ? 0
                            : benefitLevelsToBeDeleted.size());
            if (null != benefitLevelsToBeDeleted
                    && !benefitLevelsToBeDeleted.isEmpty()) {
                for (int i = 0; i < benefitLevelsToBeDeleted.size(); i++) {
                    BenefitLine productBenefitLevelDeleteBO = new BenefitLine();
                    productBenefitLevelDeleteBO.setBenefitComponentId(request
                            .getBenefitComponentId());
                    productBenefitLevelDeleteBO
                            .setLevelSysId(((Integer) benefitLevelsToBeDeleted
                                    .get(i)).intValue());
                    deleteLevelList.add(productBenefitLevelDeleteBO);
                }
                productStructureBenefitDefinitions
                        .setDeletedBenefitLevels(deleteLevelList);
                bom.delete(productStructureBenefitDefinitions,
                        productStructureBO, request.getUser());
            }

            //		 Create an instance of the locate criteria
            ProductStructureBenefitDefinitionLocateCriteria benefitDefinitionLocateCriteria = new ProductStructureBenefitDefinitionLocateCriteria();

            // Set the value of benefitId in the locate criteria from the
            // request
            benefitDefinitionLocateCriteria.setStandardBenefitId(request
                    .getStandardBenefitKey());

            // Set the value of benefitComponentId in the locate criteria from
            // the request
            benefitDefinitionLocateCriteria.setBenefitComponentId(request
                    .getBenefitComponentId());

            // Get the productStructureId from the VO set in the request
            int productStructureId = request.getProductStructureVO()
                    .getProductStructureId();

            // Set the value of productStructureId in the locate criteria
            benefitDefinitionLocateCriteria
                    .setProductStructureId(productStructureId);
            // Set the values of level and line hide flag for the retrieval
            //of visible levels
            benefitDefinitionLocateCriteria.setBenefitLevelHideFlag("F");
            benefitDefinitionLocateCriteria.setBenefitLineHideFlag("F");
            // Call the locate() of the builder to get the locateResults
            LocateResults locateResults = (LocateResults) bom.locate(
                    benefitDefinitionLocateCriteria, request.getUser());

            //			 Set the list of benefitDefinitions to the resposne
            response.setBenefitDefiniitonList(locateResults.getLocateResults());

            //			 Set the update success message to the response
            List messageList = new ArrayList(1);
            //Setting the successful updated message only if the list is not
            // empty
            //if the result is empty,the coverage page is navigated to
            // associated benefits page
            //So message is not displayed
           
                //messageList.add(new InformationalMessage(
                //        BusinessConstants.BENEFIT_LINE_UPDATED_SUCCESSFULLY));                
                //addMessagesToResponse(response, messageList);
                response.addMessage(new InformationalMessage(
                        BusinessConstants.BENEFIT_LINE_UPDATED_SUCCESSFULLY));
            

        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveBenefitDefenitionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("Returning execute method for saving product structure benefit defenition");
//        Logger.logInfo(th.endPerfLogging());
        return response;

    }

    /**
     * Retrieves the benefit mandates for display in the view page
     * 
     * @param retrieveBenefitMandatesRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveBenefitMandatesRequest retrieveBenefitMandatesRequest)
            throws ServiceException {
        try {
            Logger
                    .logInfo("Entering execute method for retrieving benefit mandates");
            // Get the BusinessObjectManager
            BusinessObjectManager bom = getBusinessObjectManager();

            List benefitMandateBOImplList = new ArrayList(1);
            BenefitMandateBO benefitMandateBO = new BenefitMandateBO();

            //Create the locateCriteria object
            ProductStructureMandatesLocateCriteria locateCriteria = new ProductStructureMandatesLocateCriteria();

            // Set the locate criteria from the request to the crteriaObject
            locateCriteria.setBenefitId(retrieveBenefitMandatesRequest
                    .getMandatesVO().getBenefitId());

            // Call the locateCriteria method of the BOM to get the list
            // of mandates

            LocateResults locateResults = bom.locate(locateCriteria,
                    retrieveBenefitMandatesRequest.getUser());

            // Create an instance of the Response object
            RetrieveBenefitMandatesResponse retrieveBenefitMandatesResponse = (RetrieveBenefitMandatesResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.RETRIEVE_MANDATES_RESPONSE);
            benefitMandateBOImplList.add(0, benefitMandateBO);
            benefitMandateBOImplList = locateResults.getLocateResults();
            benefitMandateBO = (BenefitMandateBO) benefitMandateBOImplList
                    .get(0);

            //Set the above retrieved list in the response object
            retrieveBenefitMandatesResponse
                    .setBenefitMandateBO(benefitMandateBO);
            Logger
                    .logInfo("Returning execute method for retrieving benefit mandates");
            return retrieveBenefitMandatesResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveBenefitMandatesRequest);
            String logMessage = "Failed while processing RetrieveBenefitMandatesResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * To insert /update a product strcture
     * 
     * @param createProductStructureRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveProductStructureRequest createProductStructureRequest)
            throws ServiceException {
        try {
            Logger
                    .logInfo("Entering execute method for saving product structure");
            SaveProductStructureResponse response = null;
            List removedComponentList = null;
            BusinessObjectManager bom = getBusinessObjectManager();
            ProductStructureVO productStructureVO = (ProductStructureVO) createProductStructureRequest
                    .getProductStructureVO();
            ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBOForSave(productStructureVO);
            response = (SaveProductStructureResponse) new ValidationServiceController()
                    .execute(createProductStructureRequest);
            List messageList = new ArrayList(2);
            if (null != response && response.isSuccess()) {
                if (createProductStructureRequest.getAction() == SaveProductStructureRequest.CREATE_PRODUCT_STRUCTURE) {
                    productStructureBO
                            .setProductStructureParentId(productStructureBO
                                    .getProductStructureId());
                    /*
                     * Checking Too many domain combinations
                     */
	            	try{
	                    bom.persist(productStructureBO,
	                            createProductStructureRequest.getUser(), true);
	            	}catch(UncategorizedSQLException e){
	            		response = null;
	            		return response;
	            	}
                    response.addMessage(new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_SAVED));
                } else if (createProductStructureRequest.getAction() == SaveProductStructureRequest.UPDATE_PRODUCT_STRUCTURE) {
                    ProductStructureBO oldKeyproductStructureBO = (ProductStructureBO) getProductStructureBO(createProductStructureRequest
                            .getOldKeyproductStructureBO());

                    if (isKeyValueChanged(productStructureBO,
                            oldKeyproductStructureBO)) {
                        bom.changeIdentity(oldKeyproductStructureBO,
                                productStructureBO,
                                createProductStructureRequest.getUser());
                    } else {
                    	String oldproductFamily = getProductFamily(productStructureBO.getProductStructureId());
                        bom.persist(productStructureBO,
                                createProductStructureRequest.getUser(), false);
                        
                        if(!oldproductFamily.equals(productStructureBO.getProductFamilyId())){
                        	//retrieve bc listfor validating line and questions 
                        	Map params = new HashMap();
                        	productStructureBO = (ProductStructureBO) bom.retrieve(
                        			productStructureBO, createProductStructureRequest
									.getUser(), params);
                        	if(null!=productStructureBO.getAssociatedBenefitComponentList() && productStructureBO.getAssociatedBenefitComponentList().size()>0){
                        		List benefitComponentList = productStructureBO.getAssociatedBenefitComponentList();
                        		PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
//                              doing pva validation
                        		validateQuestionPva = true;
                        		builder.doPVAvalidation(productStructureBO,benefitComponentList,createProductStructureRequest.getUser(),validateQuestionPva);
                        		InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_PS_UPDATE_PRODUCTFAMILY);
                        		messageList.add(message);
                        		response.setMessages(messageList);
                        	}
                        }
                    }
                   
                    response.addMessage(new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_UPDATED));
                  //  messageList.add(new InformationalMessage(
                       //      BusinessConstants.PRODUCT_STRUCTURE_UPDATED));
                    
                } else if (createProductStructureRequest.getAction() == SaveProductStructureRequest.DONE) {
                    if (createProductStructureRequest.isActionFromBC() != true) {
                        ProductStructureBO oldKeyproductStructureBO = (ProductStructureBO) getProductStructureBO(createProductStructureRequest
                                .getOldKeyproductStructureBO());

                        if (isKeyValueChanged(productStructureBO,
                                oldKeyproductStructureBO)) {
                            bom.changeIdentity(oldKeyproductStructureBO,
                                    productStructureBO,
                                    createProductStructureRequest.getUser());
                        } else {
                        	String oldproductFamily = getProductFamily(productStructureBO.getProductStructureId());
                            bom.persist(productStructureBO,
                                    createProductStructureRequest.getUser(),
                                    false);
                            if(!oldproductFamily.equals(productStructureBO.getProductFamilyId())){
                            	Map params = new HashMap();
                            	productStructureBO = (ProductStructureBO) bom.retrieve(
                            			productStructureBO, createProductStructureRequest
										.getUser(), params);
                            	if(null!=productStructureBO.getAssociatedBenefitComponentList() && 
                            			productStructureBO.getAssociatedBenefitComponentList().size()>0){
                            		List benefitComponentList = productStructureBO.getAssociatedBenefitComponentList();
                            		PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
//                                  doing pva validation
                            		validateQuestionPva = true;
                            		builder.doPVAvalidation(productStructureBO,benefitComponentList,createProductStructureRequest.getUser(),validateQuestionPva);
                            			InformationalMessage message = new InformationalMessage
										(BusinessConstants.BENEFIT_COMPONENT_PS_UPDATE_PRODUCTFAMILY);
                            			response.addMessage(message);
                            		}
                            		
                            	}
                          
                            
                           
                        }

                        response.addMessage(new InformationalMessage(
                                BusinessConstants.PRODUCT_STRUCTURE_UPDATED));
                    }
                    if (createProductStructureRequest.isCheckInFlag()) {
                            response
                                    .addMessage(new InformationalMessage(
                                            BusinessConstants.PRODUCT_STRUCTURE_CANBE_CHECKED_IN));
                    }

                } else if (createProductStructureRequest.getAction() == SaveProductStructureRequest.CHECK_IN_PRODUCT_STRUCTURE) {
                    // reference data validations
                    RefDataDomainValidationRequest refDataDomainValidationRequest = refDataBenefitValidation(createProductStructureRequest);
                    RefDataDomainValidationResponse refDataDomainValidationResponse = new RefDataDomainValidationResponse();
                    refDataDomainValidationResponse = (RefDataDomainValidationResponse) new ValidationServiceController()
                            .execute(refDataDomainValidationRequest);
                    String oldproductFamily = getProductFamily(productStructureBO.getProductStructureId());

                    if (null != refDataDomainValidationResponse
                            && !refDataDomainValidationResponse.isSuccess()) {
                        ErrorMessage errorMessage = new ErrorMessage(
                                WebConstants.REFERENCE_DATA_VALIDATION_FAILURE);
                        errorMessage.setParameters(new String[] { " "
                                + refDataDomainValidationResponse
                                        .getErrorMessage() });
                        response.addMessage(errorMessage);
                    }
          
                    if (createProductStructureRequest.isActionFromBC() != true) {
                        ProductStructureBO oldKeyproductStructureBO = (ProductStructureBO) getProductStructureBO(createProductStructureRequest
                                .getOldKeyproductStructureBO());

                        if (isKeyValueChanged(productStructureBO,
                                oldKeyproductStructureBO)) {
                            bom.changeIdentity(oldKeyproductStructureBO,
                                    productStructureBO,
                                    createProductStructureRequest.getUser());
                        } else {
                            bom.persist(productStructureBO,
                                    createProductStructureRequest.getUser(),
                                    false);
                        }

                        response.addMessage(new InformationalMessage(
                                BusinessConstants.PRODUCT_STRUCTURE_UPDATED));
                    }
                    productStructureBO = getProductStructureBO(productStructureVO);
                    if (refDataDomainValidationResponse.isSuccess()) {
                        if (createProductStructureRequest.isCheckInFlag()) {


                        	
                            if(!oldproductFamily.equals(productStructureBO.getProductFamilyId())){
                            	Map params = new HashMap();
                            	productStructureBO = (ProductStructureBO) bom.retrieve(
                            			productStructureBO, createProductStructureRequest
										.getUser(), params);
                            	if(null!=productStructureBO.getAssociatedBenefitComponentList() && 
                            			productStructureBO.getAssociatedBenefitComponentList().size()>0){
                            		List benefitComponentList = productStructureBO.getAssociatedBenefitComponentList();
                            		PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
                            		validateQuestionPva = true;
                            		builder.doPVAvalidation(productStructureBO,benefitComponentList,createProductStructureRequest.getUser(),validateQuestionPva);
                            		}
                            		
                            	}
                        	
                                bom
                                        .checkIn(productStructureBO,
                                                createProductStructureRequest
                                                        .getUser());
                                InformationalMessage informationalMessage = new InformationalMessage(
                                        BusinessConstants.PRODUCT_STRUCTURE_CHECKED_IN);
                                informationalMessage
                                        .setParameters(new String[] { productStructureBO
                                                .getProductStructureName() });
                                response.addMessage(informationalMessage);
//                            }
                        } else {
                            response.setSuccess(false);
                        }
                    } else {
                        response.setSuccess(false);
                    }
                } else {

                    ProductStructureBO srcProductStructureBO = createTargetBOForCopy(productStructureBO);
                    bom.persist(productStructureBO,
                            createProductStructureRequest.getUser(), true);

                    bom.copy(srcProductStructureBO, productStructureBO,
                            createProductStructureRequest.getUser());
                    createProductStructureRequest
                            .setTargetId(productStructureBO
                                    .getProductStructureId());
                    InformationalMessage informationalMessage = new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_COPIED);
                    informationalMessage
                            .setParameters(new String[] { srcProductStructureBO
                                    .getProductStructureName() });
                    response.addMessage(informationalMessage);

                }
                if (createProductStructureRequest.getAction() != SaveProductStructureRequest.CREATE_PRODUCT_STRUCTURE) {
                    if (createProductStructureRequest
                            .isDeleteBenefitComponent()) {
                        ProductStructureComponent productStructureComponent = new ProductStructureComponent();
                        if (createProductStructureRequest.getAction() == BusinessConstants.INT_4)
                            productStructureComponent
                                    .setProductStructureId(createProductStructureRequest
                                            .getTargetId());
                        else
                            productStructureComponent
                                    .setProductStructureId(createProductStructureRequest
                                            .getProductStructureVO()
                                            .getProductStructureId());
                        bom.delete(productStructureComponent,
                                productStructureBO,
                                createProductStructureRequest.getUser());
                        response.addMessage(new InformationalMessage(
                                BusinessConstants.ASSO_COMP_DELETED));

                    } else if (createProductStructureRequest.isDomainChange()
                            && (null != response.getBenefitComponentList() && response
                                    .getBenefitComponentList().size() > 0)) {
                        List removeList = new ArrayList(0);

                        removeList = response.getBenefitComponentList();
                        removedComponentList = new ArrayList(
                                removeList == null ? 0 : removeList.size());
                        for (int count = 0; count < removeList.size(); count++) {
                            BenefitComponentBO benefitComponentBO = (BenefitComponentBO) removeList
                                    .get(count);
                            ProductStructureAssociatedComponent productStructureAssociatedComponent = new ProductStructureAssociatedComponent();
                            productStructureAssociatedComponent
                                    .setProductStructureId(productStructureBO
                                            .getProductStructureId());
                            productStructureAssociatedComponent
                                    .setBenefitComponentId(benefitComponentBO
                                            .getBenefitComponentId());
                            if (null != benefitComponentBO.getName())
                                removedComponentList.add(benefitComponentBO
                                        .getName());
                            bom.delete(productStructureAssociatedComponent,
                                    productStructureBO,
                                    createProductStructureRequest.getUser());
                        }

                        if (null != removedComponentList
                                && !(removedComponentList.isEmpty())) {
                            String benefitComponentsName = removedComponentList
                                    .get(0).toString();
                            for (int i = 1; i < removedComponentList.size(); i++) {
                                benefitComponentsName = benefitComponentsName
                                        + ", "
                                        + removedComponentList.get(i)
                                                .toString();
                            }
                            InformationalMessage informationalMessage = new InformationalMessage(
                                    BusinessConstants.ASSO_COMP_DELETED_DOMAIN);
                            informationalMessage
                                    .setParameters(new String[] { benefitComponentsName
                                            .toString() });
                            response.addMessage(informationalMessage);
                        }
                    }
                    if (!(createProductStructureRequest
                            .isDeleteBenefitComponent())
                            && createProductStructureRequest.isDateChange()
                            && (null != response.getComponentInvalidDateList() && response
                                    .getComponentInvalidDateList().size() > 0)) {
                        List removeComponentList = new ArrayList(0);

                        removeComponentList = response
                                .getComponentInvalidDateList();
                        removedComponentList = new ArrayList(
                                removeComponentList == null ? 0
                                        : removeComponentList.size());
                        for (int count = 0; count < removeComponentList.size(); count++) {
                            ProductStructureAssociatedBenefitComponent benefitComponentBO = (ProductStructureAssociatedBenefitComponent) removeComponentList
                                    .get(count);
                            ProductStructureAssociatedComponent productStructureAssociatedComponent = new ProductStructureAssociatedComponent();
                            productStructureAssociatedComponent
                                    .setProductStructureId(productStructureBO
                                            .getProductStructureId());
                            productStructureAssociatedComponent
                                    .setBenefitComponentId(benefitComponentBO
                                            .getBenefitComponentId());
                            if (null != benefitComponentBO.getName())
                                removedComponentList.add(benefitComponentBO
                                        .getName());
                            bom.delete(productStructureAssociatedComponent,
                                    productStructureBO,
                                    createProductStructureRequest.getUser());
                        }
                        if (null != removedComponentList
                                && !(removedComponentList.isEmpty())) {
                            String benefitComponentsName = removedComponentList
                                    .get(0).toString();
                            for (int i = 1; i < removedComponentList.size(); i++) {
                                benefitComponentsName = benefitComponentsName
                                        + ", "
                                        + removedComponentList.get(i)
                                                .toString();
                            }
                            InformationalMessage informationalMessage = new InformationalMessage(
                                    BusinessConstants.ASSO_COMP_DELETED_DATE);
                            informationalMessage
                                    .setParameters(new String[] { benefitComponentsName
                                            .toString() });
                            response.addMessage(informationalMessage);
                        }
                    }
                }
                response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
                        BusinessConstants.PRODSTRUCTURE, productStructureBO
                                .getProductStructureParentId()));
                response.setProductStructure(productStructureBO);
            }
            Logger
                    .logInfo("Returning execute method for saving product structure");
            return response;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(createProductStructureRequest);
            String logMessage = "Failed while processing CreateProductStructureRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /*
     * Validation methode for referance date
     * 
     * @StandardBenefitCheckInRequest
     * 
     * return RefDataDomainValidationRequest
     */
    private RefDataDomainValidationRequest refDataBenefitValidation(
            SaveProductStructureRequest request) throws ServiceException {
        String structureType = request.getProductStructureVO()
                .getStructureType();
        RefDataDomainValidationRequest refDataDomainValidationRequest = new RefDataDomainValidationRequest();
        ProductStructureBO productStructureBO = new ProductStructureBO();
        ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
        List stateList = new ArrayList(1);
        List locateResults = new ArrayList(0);
        HashMap selectedItemMap = new HashMap();
        List parentCatalogList = new ArrayList(1);
        SubCatalogVO subCatalogVO = new SubCatalogVO();
        subCatalogVO.setEntityId(request.getProductStructureVO()
                .getParentProductStructureId());
        subCatalogVO.setEntityType(BusinessConstants.PRODSTRUCTURE);

        if (!BusinessConstants.STANDARD.equalsIgnoreCase(structureType))
            parentCatalogList.add(BusinessConstants.STATE_CODE);

        productStructureBO.setProductStructureId(request
                .getProductStructureVO().getParentProductStructureId());
        try {
            productStructureBO = (ProductStructureBO) builder
                    .retrieveForReference(productStructureBO);
        } catch (SevereException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing CheckInRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        stateList.add(productStructureBO.getStateId());
        refDataDomainValidationRequest.setParentCatalogList(parentCatalogList);
        refDataDomainValidationRequest.setSubCatalogVO(subCatalogVO);

        if (!BusinessConstants.STANDARD.equalsIgnoreCase(structureType)) {
            selectedItemMap.put(BusinessConstants.STATE_CODE, stateList);
        }
        refDataDomainValidationRequest.setSelectedItemMap(selectedItemMap);
        return refDataDomainValidationRequest;
    }

    /**
     * 
     * Retrieves the benefit component from the tree for display in the view
     * page
     * 
     * @param retrieveComponentFromTreeRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for retrieving component from tree");

        Map params = new HashMap();
        RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        retrieveComponentFromTreeResponse = (RetrieveComponentFromTreeResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_COMPONENT_FROM_TREE_RESPONSE);
        params.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(
                retrieveComponentFromTreeRequest.getBenefitComponentId()));
        ProductStructureBO productStructureBO = getProductStructureBO(retrieveComponentFromTreeRequest
                .getProductStructure());
        try {

            if (retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_1
                    || retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_2
                    || retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_3) {

                ProductStructureShowHiddenLocateCriteria productStructureLocateCriteria = new ProductStructureShowHiddenLocateCriteria();
                ProductStructureAssociatedBenefit productStructureAssociatedBenefits = new ProductStructureAssociatedBenefit();

                if (retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_1) {

                    List benefitAllDetailsList = null;

                    productStructureLocateCriteria
                            .setBenefitComponentId(retrieveComponentFromTreeRequest
                                    .getBenefitComponentId());
                    productStructureLocateCriteria
                            .setProductStructureId(productStructureBO
                                    .getProductStructureId());

                    productStructureLocateCriteria.setHiddenFlag(true);

                    benefitAllDetailsList = bom.locate(
                            productStructureLocateCriteria,
                            retrieveComponentFromTreeRequest.getUser())
                            .getLocateResults();

                    //sets the benefit details to the response
                    retrieveComponentFromTreeResponse
                            .setBenefitDetails(benefitAllDetailsList);

                }
                if (retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_2) {

                    List benefitDetailsList = null;

                    productStructureLocateCriteria
                            .setBenefitComponentId(retrieveComponentFromTreeRequest
                                    .getBenefitComponentId());
                    productStructureLocateCriteria
                            .setProductStructureId(productStructureBO
                                    .getProductStructureId());

                    //Call to builder to retrieve all the visible benefits at
                    // the time of load.
                    benefitDetailsList = bom.locate(
                            productStructureLocateCriteria,
                            retrieveComponentFromTreeRequest.getUser())
                            .getLocateResults();

                    //sets the benefit details to the response
                    retrieveComponentFromTreeResponse
                            .setBenefitDetails(benefitDetailsList);

                }
                if (retrieveComponentFromTreeRequest.getAction() == BusinessConstants.INT_3) {

                    // Setting all the required values from the request to the
                    // BO
                    productStructureAssociatedBenefits
                            .setBenefitDetailsList(retrieveComponentFromTreeRequest
                                    .getBenefitDetailsList());
                    productStructureAssociatedBenefits
                            .setBenefitComponentId(retrieveComponentFromTreeRequest
                                    .getBenefitComponentId());
                    productStructureAssociatedBenefits
                            .setProductStructureId(productStructureBO
                                    .getProductStructureId());
                    productStructureAssociatedBenefits
                            .setShowHiddenFlag(retrieveComponentFromTreeRequest
                                    .isShowHiddenStatus());
                    //productStructureAssociatedBenefits.setBenefitComponentHideFlag(retrieveComponentFromTreeRequest.getBenefitComponentHideFlag());
                    try {
                       bom.persist(productStructureAssociatedBenefits,productStructureBO,retrieveComponentFromTreeRequest.getUser(),false);
                       /** removing PVA Validation while hide/unhide since pva validation is done at the time of attaching the BC eWPD Stabilization Release**/
                      /** List components = new ArrayList();
                        ProductStructureAssociatedBenefitComponent associatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
                        associatedBenefitComponent.setBenefitComponentId(retrieveComponentFromTreeRequest
                                .getBenefitComponentId());
                        components.add(associatedBenefitComponent);
                        PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
//                      doing pva while unhiding benefit .
                        validateQuestionPva = true;
                	    PVAvalidationResult validationResult=builder.doPVAvalidation(productStructureBO,components,
                	    		retrieveComponentFromTreeRequest.getUser(),validateQuestionPva); **/
						
//                        updateAMVForBnftInBnftComponentInPrdctStructure(retrieveComponentFromTreeRequest);
                        // updateAMVForGBAssctdBenefits(retrieveComponentFromTreeRequest);

                        productStructureLocateCriteria
                                .setBenefitComponentId(productStructureAssociatedBenefits
                                        .getBenefitComponentId());
                        productStructureLocateCriteria
                                .setProductStructureId(productStructureAssociatedBenefits
                                        .getProductStructureId());
                        productStructureLocateCriteria
                                .setHiddenFlag(productStructureAssociatedBenefits
                                        .isShowHiddenFlag());

                        List benefitDetailsUpdatedList = bom.locate(
                                productStructureLocateCriteria,
                                retrieveComponentFromTreeRequest.getUser())
                                .getLocateResults();
                        //sets the benefit component details to the response
                        retrieveComponentFromTreeResponse
                                .setBenefitDetails(benefitDetailsUpdatedList);
                    } catch (SevereException e) {
                        e.printStackTrace();
                    }
                    List messageList = new ArrayList(1);

                    messageList
                            .add(new InformationalMessage(
                                    BusinessConstants.PRODUCT_STRUCTURE_BENEFIT_HIDE_STATUS));
                    addMessagesToResponse(retrieveComponentFromTreeResponse,
                            messageList);

                }

            }

            else {

                productStructureBO = (ProductStructureBO) bom.retrieve(
                        productStructureBO, retrieveComponentFromTreeRequest
                                .getUser(), params);

                retrieveComponentFromTreeResponse
                        .setBenefitComponent((BenefitComponentBO) productStructureBO
                                .getAssociatedBenefitComponentList().get(0));
                retrieveComponentFromTreeResponse.setDetail(DomainUtil
                        .retrieveDomainDetailInfo("BENEFITCOMP",
                                retrieveComponentFromTreeResponse
                                        .getBenefitComponent()
                                        .getBenefitComponentParentId()));
                Logger
                        .logInfo("Returning execute method for retrieving component from tree");
            }
            return retrieveComponentFromTreeResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveComponentFromTreeRequest);
            String logMessage = "Failed while processing RetrieveComponentFromTreeRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }


    /**
     * Retrieves the benefit component list
     * 
     * @param retrieveBenefitComponentRequest
     * @return WPDResponse
     * @throws ServiceException
     */

    public WPDResponse execute(
            RetrieveBenefitComponentRequest retrieveBenefitComponentRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for retrieving benefit component");
        Map params = new HashMap();
        RetrieveBenefitComponentResponse retrieveBenefitComponentResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(retrieveBenefitComponentRequest
                .getProductStructureVO());

        retrieveBenefitComponentResponse = (RetrieveBenefitComponentResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_BENEFIT_COMPONENT_RESPONSE);
        try {
            productStructureBO = (ProductStructureBO) bom.retrieve(
                    productStructureBO, retrieveBenefitComponentRequest
                            .getUser(), params);
            retrieveBenefitComponentResponse
                    .setProductStructureBO(productStructureBO);
            Logger
                    .logInfo("Returning execute method for retrieving benefit component");
            return retrieveBenefitComponentResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveBenefitComponentRequest);
            String logMessage = "Failed while processing RetrieveBenefitComponentRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Retrieves all the versions of a product structure
     * 
     * @param retrieveProductStructureVersionsRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveProductStructureVersionsRequest retrieveProductStructureVersionsRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for retrieving product structure version");
        RetrieveProductStructureVersionsResponse retrieveProductStructureVersionsResponse = (RetrieveProductStructureVersionsResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_STRUCTURE_VERSIONS_RESPONSE);
        try {
            BusinessObjectManager bom = getBusinessObjectManager();
            ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria = new ViewAllVersionsLocateCriteria();
            viewAllVersionsLocateCriteria
                    .setProductStructureId(retrieveProductStructureVersionsRequest
                            .getProductStructureId());
            LocateResults locateResults = bom.locate(
                    viewAllVersionsLocateCriteria,
                    retrieveProductStructureVersionsRequest.getUser());
            if (null != locateResults) {
                retrieveProductStructureVersionsResponse
                        .setVersionList(locateResults.getLocateResults());
            }
            Logger
                    .logInfo("Returning execute method for retrieving product structure version");
            return retrieveProductStructureVersionsResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveProductStructureVersionsRequest);
            String logMessage = "Failed while processing RetrieveProductStructureVersionsRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Gets the benefitAdministration List from the db
     * 
     * @param benefitAdministrationRequest
     * @return WPDResponse
     * @throws ServiceException
     */

    public WPDResponse execute(
            RetrieveProductStructureQuestionnaireRequest benefitAdministrationRequest)
            throws ServiceException {
        try {
            Logger.logInfo("Entering execute method, request = "
                    + benefitAdministrationRequest.getClass().getName());

            // Get the response object from the WPDResponseFactory
            RetrieveProductStructureBenefitAdministrationResponse response = (RetrieveProductStructureBenefitAdministrationResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_STRUCTURE_BENEFIT_ADMINISTATION_RESPONSE);
            BusinessObjectManager bom = getBusinessObjectManager();
            ProductStructureBenefitAdministrationLocateCriteria benefitAdministrationLocateCriteria = new ProductStructureBenefitAdministrationLocateCriteria();

            int action = benefitAdministrationRequest.getAction();
            switch (action) {
            case RetrieveProductStructureQuestionnaireRequest.LOAD_QUESTIONNARE_LIST:
                benefitAdministrationLocateCriteria.setAction(1);
                benefitAdministrationLocateCriteria
                        .setEntityId(benefitAdministrationRequest
                                .getProductStructureId());
                benefitAdministrationLocateCriteria
                        .setEntiityType(BusinessConstants.PRODSTRUCTURE);
                benefitAdministrationLocateCriteria
                        .setAdminLvlAssnSysId(benefitAdministrationRequest
                                .getAdminOptionAssnSysId());
                benefitAdministrationLocateCriteria
                        .setBenefitComponentId(benefitAdministrationRequest
                                .getBenefitComponentId());
                benefitAdministrationLocateCriteria
                        .setBenefitAdminSysId(benefitAdministrationRequest
                                .getBenefitAdminSysId());
                benefitAdministrationLocateCriteria
						.setAdminOptSysId(benefitAdministrationRequest
								.getAdminSysId());
                benefitAdministrationLocateCriteria
						.setParentId(benefitAdministrationRequest
                				.getParentId());
                benefitAdministrationLocateCriteria
						.setAllPossibleAnswerMap(benefitAdministrationRequest
								.getAllPossibleAnswerMap());
                break;
            case RetrieveProductStructureQuestionnaireRequest.LOAD_SELECTED_CHILD:
                benefitAdministrationLocateCriteria
                        .setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD);
                benefitAdministrationLocateCriteria
                        .setEntityId(benefitAdministrationRequest
                                .getProductStructureId());
                benefitAdministrationLocateCriteria
                        .setQuestionnareList(benefitAdministrationRequest
                                .getQuestionnareList());
                benefitAdministrationLocateCriteria
                        .setQuestionareListIndex(benefitAdministrationRequest
                                .getQuestionareListIndex());
                benefitAdministrationLocateCriteria
                        .setAdminOptSysId(benefitAdministrationRequest
                                .getAdminSysId());
                benefitAdministrationLocateCriteria
                        .setBenefitAdminSysId(benefitAdministrationRequest
                                .getAdminOptionAssnSysId());
                benefitAdministrationLocateCriteria
                        .setProductStructureQuestionnaireBO(benefitAdministrationRequest
                                .getProductStructureQuestionnaireBO());
                benefitAdministrationLocateCriteria
                        .setAnswerId(benefitAdministrationRequest
                                .getSelectedAnswerId());
                benefitAdministrationLocateCriteria
                .setAnswerDesc(benefitAdministrationRequest
                        .getSelectedAnswerDesc());
                
                benefitAdministrationLocateCriteria
                        .setAdminLvlAssnSysId(benefitAdministrationRequest
                                .getAdminOptionAssnSysId());
                benefitAdministrationLocateCriteria
                        .setBenefitComponentId(benefitAdministrationRequest
                                .getBenefitComponentId());
                benefitAdministrationLocateCriteria
                        .setBenefitDefnId(benefitAdministrationRequest
                                .getBenefitDefinitionId());
                benefitAdministrationLocateCriteria
				.setAllPossibleAnswerMap(benefitAdministrationRequest
						.getAllPossibleAnswerMap());
                break;
            case RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW:
                benefitAdministrationLocateCriteria
                        .setAction(ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW);
                benefitAdministrationLocateCriteria
                        .setAdminLvlAssnSysId(benefitAdministrationRequest
                                .getAdminOptionAssnSysId());
                benefitAdministrationLocateCriteria
                        .setBenefitAdminSysId(benefitAdministrationRequest
                                .getBenefitAdminSysId());
                benefitAdministrationLocateCriteria
                        .setEntityId(benefitAdministrationRequest
                                .getProductStructureId());
                benefitAdministrationLocateCriteria
                        .setBenefitComponentId(benefitAdministrationRequest
                                .getBenefitComponentId());
                benefitAdministrationLocateCriteria
						.setAdminOptSysId(benefitAdministrationRequest
								.getAdminSysId());
                benefitAdministrationLocateCriteria
						.setParentId(benefitAdministrationRequest
								.getParentId());
                benefitAdministrationLocateCriteria
				.setAllPossibleAnswerMap(benefitAdministrationRequest
						.getAllPossibleAnswerMap());
                break;
            }
            LocateResults locateResults = bom.locate(
                    benefitAdministrationLocateCriteria,
                    benefitAdministrationRequest.getUser());
            response.setBenefitAdministrationList(locateResults
                    .getLocateResults());//Questionnaire list
            Logger.logInfo("Exiting execute method, request = "
                    + benefitAdministrationRequest.getClass().getName());
            return response;
        } catch (SevereException ex) {
            Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(benefitAdministrationRequest);
            String logMessage = "Failed while processing RetrieveProductStructureQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        } catch (WPDException ex) {
            Logger.logError(ex);
            List logParameters = new ArrayList(1);
            logParameters.add(benefitAdministrationRequest);
            String logMessage = "Failed while processing RetrieveProductStructureQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Updates the benefit Administrations for Product Structure
     * 
     * @param UpdateProductStructureBenefitAdministrationRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            UpdateProductStructureBenefitAdministrationRequest request)
            throws ServiceException {
        List messageList = new ArrayList(2);
        Logger.logInfo("Entering execute method, request = "
                + request.getClass().getName());
        SaveBenefitAdministrationResponse response = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureBO productStructureBO = new ProductStructureBO();
        productStructureBO.setProductStructureName(request
                .getMainObjectIdentifier());
        productStructureBO.setBenifitComponentId(request
                .getBenefitComponentId());
        productStructureBO.setBusinessDomains(request.getDomainList());
        productStructureBO.setVersion(request.getVersionNumber());
        List questionnareList = request.getQuestionnareList();

        ProductStructureBenefitAdministrationBO administrationBO = new ProductStructureBenefitAdministrationBO();
        administrationBO.setQuestionnaireList(request.getQuestionnareList());
        administrationBO.setQuestionnaireListToAdd(request.getQuestionnaireListToAdd());
        administrationBO.setQuestionnaireListToUpdate(request.getQuestionnaireListToUpdate());
        administrationBO.setQuestionnaireListToRemove(request.getQuestionnaireListToRemove());
		
        administrationBO.setProductStructureId(request.getEntityId());
        administrationBO.setAdminLevelOptionSysId(request
                .getAdminlevelOptionSysId());
        administrationBO.setBenefitComponentId(request.getBenefitComponentId());
        try {
            bom.persist(administrationBO, productStructureBO,
                    request.getUser(), false);
//            updateAMVforBnftAdmin(request);
            messageList.add(new InformationalMessage(
                    BusinessConstants.MSG_QUESTIONNARE_SAVE_BC_SUCCESS));
            Logger.logInfo("Exiting execute method, request = "
                    + request.getClass().getName());

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

        response = new SaveBenefitAdministrationResponse();
        response.setMessages(messageList);
        return response;
    }

    /**
     * Performs the checkout of a product structure
     * 
     * @param checkoutProductStructureRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            CheckoutProductStructureRequest checkoutProductStructureRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for checking out product structure");
        // Get the response object from the WPDResponseFactory
        RetrieveProductStructureResponse response = (RetrieveProductStructureResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_STRUCTURE_RESPONSE);
        // Get the productStructureVO from the request
        ProductStructureVO productStructureVO = checkoutProductStructureRequest
                .getProductStructureVO();

        // Create the productStructureBO from the request
        ProductStructureBO productStructureBO = getProductStructureBO(productStructureVO);
        // Get the BusinessObjectManager
        BusinessObjectManager bom = getBusinessObjectManager();
        try {
            productStructureBO = (ProductStructureBO) bom.checkOut(
                    productStructureBO, checkoutProductStructureRequest
                            .getUser());
            response.setSuccess(true);
            response.setProductStructureBO(productStructureBO);
            response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
                    BusinessConstants.PRODSTRUCTURE, productStructureBO
                            .getProductStructureParentId()));
            List messageList = new ArrayList(1);
            InformationalMessage informationalMessage = new InformationalMessage(
                    BusinessConstants.CHECK_OUT_SUCCESS);
            informationalMessage
                    .setParameters(new String[] { productStructureBO
                            .getProductStructureName() });
            messageList.add(informationalMessage);
            addMessagesToResponse(response, messageList);
            Logger
                    .logInfo("Returning execute method for checking out product structure");
            return response;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(checkoutProductStructureRequest);
            String logMessage = "Failed while processing CheckoutProductStructureRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Updates the overridden answers to the db
     * 
     * @param SaveBenefitAdministrationRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveBenefitAdministrationRequest administrationRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for saving benefit administration");
        // Create the response object from the response factory
        SaveBenefitAdministrationResponse administrationResponse = (SaveBenefitAdministrationResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_BENEFIT_ADMINISTRATION_FOR_PRODUCT_STRUCTURE);

        try {
            // Get the BusinessObjectManager
            BusinessObjectManager bom = getBusinessObjectManager();

            // Get the ProductStructureVO from the request
            ProductStructureVO productStructureVO = administrationRequest
                    .getProductStructureVO();

            //enhancement
            String aoHideFlag = administrationRequest.getAdminOptionHideFlag();

            // Create the BO using the VO
            ProductStructureBO productStructureBO = getProductStructureBO(productStructureVO);

            // Get the list of benefitAdministrationVOs from the request
            List benefitAdministrationVOList = administrationRequest
                    .getBenefitAdministrationVOList();

            // Get the BO
            EntityBenefitAdministrationPSBO administrationPSBO = new EntityBenefitAdministrationPSBO();

            // Create a BO List
            List benefitAdministrationBOList = new ArrayList(
                    benefitAdministrationVOList.size());
            ProductStructureBenefitAdministration productStructureBenefitAdministration = new ProductStructureBenefitAdministration();

            // Iterate through the list and save the administration options one
            // by one
            for (int i = 0; i < benefitAdministrationVOList.size(); i++) {

                // Get the individual BenefitAdministrationVOs
                BenefitAdministrationOverrideVO administrationVO = (BenefitAdministrationOverrideVO) benefitAdministrationVOList
                        .get(i);

                // Create an instance of the BO
                EntityBenefitAdministration administration = new EntityBenefitAdministration();

                // Set the values to the BO from the VO
                administration.setAnswerDesc(administrationVO.getAnswerDesc());
                administration.setAnswerId(administrationVO.getAnswerId());
                administration.setQuestionNumber(administrationVO
                        .getQuestionId());
                administration.setEntityId(administrationVO.getEntityId());
                administration.setBeneftComponentId(administrationRequest
                        .getBenefitComponentId());
                administration.setEntityType(BusinessConstants.PRODSTRUCTURE);
                administration.setAdminLevelOptionAssnId(administrationRequest
                        .getAdminOptionLevelAssnId());
                administration.setAdminOptionHideFlag(administrationRequest
                        .getAdminOptionHideFlag());
                //enhancement
                administration.setQstnHideFlag(administrationVO
                        .getQuestionHideFlag());
                //productStructureBenefitAdministration.setProductStructureId(administrationVO.getEntityId());

                // Set the BO to the BOList
                benefitAdministrationBOList.add(administration);

            }
            //enhancement

            // Set the BOList to the BO
            administrationPSBO
                    .setBenefitAdministrationList(benefitAdministrationBOList);

            // Call the persisit method of the bom to persist the
            // overridden values to the db
            bom.persist(administrationPSBO, productStructureBO,
                    administrationRequest.getUser(), false);

            //			updateAMVforBnftAdmin(administrationRequest);

            if (administrationRequest.getAdminOptionHideFlag().equals(
                    BusinessConstants.HIDE_FLAG_T)) {
                productStructureBenefitAdministration
                        .setEnityid(administrationRequest
                                .getProductStructureVO()
                                .getProductStructureId());
                productStructureBenefitAdministration
                        .setAdminLevelOptionId(administrationRequest
                                .getAdminOptionLevelAssnId());
                productStructureBenefitAdministration
                        .setEntityType(BusinessConstants.PRODSTRUCTURE);
                productStructureBenefitAdministration
                        .setAdminOptionHideFlag(BusinessConstants.HIDE_FLAG_T);
                bom.persist(productStructureBenefitAdministration,
                        productStructureBO, administrationRequest.getUser(),
                        false);
            }

            // Create locate criteria object
            ProductStructureBenefitAdministrationLocateCriteria benefitAdministrationLocateCriteria = new ProductStructureBenefitAdministrationLocateCriteria();

            // Set the value of entity id in the locate criteria
            benefitAdministrationLocateCriteria.setEntityId(productStructureVO
                    .getProductStructureId());

            // Set the value of entity Type in the locate criteria
            benefitAdministrationLocateCriteria
                    .setEntiityType(BusinessConstants.PRODSTRUCTURE);

            // Set the value of admin option sys id in the locate criteria
            benefitAdministrationLocateCriteria
                    .setAdminOptSysId(administrationRequest.getAdminSysId());

            // Set the value of benefitComponent id in the locate criteria
            benefitAdministrationLocateCriteria
                    .setBenefitComponentId(administrationRequest
                            .getBenefitComponentId());

            // Set the benefit admin sys id in the locate criteria
            benefitAdministrationLocateCriteria
                    .setBenefitAdminSysId(administrationRequest
                            .getBenefitAdminSysId());

            // Call the locate method of the builder passing the
            // to get the output list
            LocateResults locateResults = bom.locate(
                    benefitAdministrationLocateCriteria, administrationRequest
                            .getUser());

            // Set the list to the response
            administrationResponse.setBenefitAdminList(locateResults
                    .getLocateResults());

            // Set the update success message to the response
            List messageList = new ArrayList(2);

            if (administrationRequest.isAnswerOverrideFlag()) {
                messageList.add(new InformationalMessage(
                        BusinessConstants.ANSWER_OVERRIDDEN));
            }
            if (administrationRequest.isHideStatusFlag()) {
                messageList.add(new InformationalMessage(
                        BusinessConstants.QUESTION_HIDE_STATUS_UPDATED));

            }
            addMessagesToResponse(administrationResponse, messageList);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(administrationRequest);
            String logMessage = "Failed while processing SaveBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("Returning execute method for saving benefit administration");
        return administrationResponse;
    }

//    /**
//     * @param administrationRequest
//     */
//    private void updateAMVforBnftAdmin(
//            UpdateProductStructureBenefitAdministrationRequest administrationRequest)
//            throws ServiceException {
//        if (administrationRequest.isChanged()) {
//            AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//            validationRqst.setBenefitComponentId(administrationRequest
//                    .getBenefitComponentId());
//            validationRqst.setEntityId(administrationRequest.getEntityId());
//            validationRqst
//                    .setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT_STRUCTURE);
//            validationRqst.setChangedIds(administrationRequest.getChangedIds());
//            validationRqst.setBenefitAdministrationId(administrationRequest
//                    .getBenefitAdminSysId());
//            validationRqst
//                    .setLevel(AdminMethodSynchronousValidationRequest.BNFT_ADMIN_QUESTION);
//            validationRqst.setBenefitCompName(administrationRequest
//                    .getBCompName());
//            validationRqst.setBenefitId(administrationRequest.getBenefitId());
//            AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
//                    .execute(validationRqst);
//        }
//
//    }

    /**
     * Deletes a product structure
     * 
     * @param deleteProductStructureRequest
     * @return
     * @throws ServiceException
     */

    public WPDResponse execute(
            DeleteProductStructureRequest deleteProductStructureRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for deleting product structure");
        Map params = new HashMap();
        List errorList = new ArrayList(1);
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureVO productStructureVO = deleteProductStructureRequest
                .getProductStructureVO();
        DeleteProductStructureResponse deleteProductStructureResponse = (DeleteProductStructureResponse) WPDResponseFactory
                .getDeleteProductStructureResponse();

        ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(productStructureVO);
        try {
            // Get the value of the action variable of the request object
            int action = deleteProductStructureRequest.getAction();
            // Check whether to delete Product Structure
            if (action == DeleteProductStructureRequest.DELETE_PRODUCT_STRUCTURE) {

                bom.delete(productStructureBO, deleteProductStructureRequest
                        .getUser());
                InformationalMessage informationalMessage = new InformationalMessage(
                        BusinessConstants.PRODUCT_STRUCTURE_DELETE);
                informationalMessage
                        .setParameters(new String[] { productStructureBO
                                .getProductStructureName() });
                errorList.add(informationalMessage);
                deleteProductStructureResponse.setMessages(errorList);

                // Get the locateCriteria from the request
                ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO = deleteProductStructureRequest
                        .getProductStructureLocateCriteriaVO();
                LocateResults locateResults = null;
                if (productStructureLocateCriteriaVO != null) {
                    // Set the values in the locateCriteriaBO from the VO
                    ProductStructureLocateCriteria productStructureLocateCriteriaBO = (ProductStructureLocateCriteria) getProductStructureLocateCriteriaBO(productStructureLocateCriteriaVO);

                    // Call the locate method to get the updated
                    // ProductStructure
                    // List
                    locateResults = bom.locate(
                            productStructureLocateCriteriaBO,
                            deleteProductStructureRequest.getUser());

                } else if (deleteProductStructureRequest
                        .getProductStructureId() != 0) {
                    ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria = new ViewAllVersionsLocateCriteria();
                    viewAllVersionsLocateCriteria
                            .setProductStructureId(deleteProductStructureRequest
                                    .getProductStructureId());
                    locateResults = bom.locate(viewAllVersionsLocateCriteria,
                            deleteProductStructureRequest.getUser());

                }
                // Check whether the result List is null
                if (null != locateResults) {
                    // Get the resultList from the locateResult
                    deleteProductStructureResponse
                            .setProductStructureList(locateResults
                                    .getLocateResults());
                }
            }
            //Check whether to delete Benefit Component Associated with
            // Product Structure
            else if (action == DeleteProductStructureRequest.DELETE_BENEFIT_COMPONENT) {
                //				modified for mulitiple benefit component delete
                ProductStructureAssociatedBenefitComponent associatedBenefitComponent = getProductStructureAssociatedBenefitComponent(
                        deleteProductStructureRequest.getBenefitComponentList(),
                        productStructureBO.getProductStructureId());
                bom.delete(associatedBenefitComponent, productStructureBO,
                        deleteProductStructureRequest.getUser());
                deleteProductStructureResponse = (DeleteProductStructureResponse) WPDResponseFactory
                        .getResponse(WPDResponseFactory.DELETE_PRODUCT_STRUCTURE_RESPONSE);
                deleteProductStructureResponse.setSuccess(true);
                productStructureBO = (ProductStructureBO) bom.retrieve(
                        productStructureBO, deleteProductStructureRequest
                                .getUser(), params);
                errorList.add(new InformationalMessage(
                        BusinessConstants.BENEFIT_COMPONENT_DELETED));
                deleteProductStructureResponse
                        .setProductStructureBO(productStructureBO);
                deleteProductStructureResponse.setMessages(errorList);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(deleteProductStructureRequest);
            String logMessage = "Failed while processing DeleteProductStructureRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("Returning execute method for deleting product structure");
        return deleteProductStructureResponse;
    }

    /**
     * Retrieves the product structure details
     * 
     * @param retrieveProductStructureRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveProductStructureRequest retrieveProductStructureRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for retrieving product structure");
        boolean lockAquired = true;
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureVO productStructureVO = (ProductStructureVO) retrieveProductStructureRequest
                .getProductStructureVO();
        ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(productStructureVO);
        List messageList = new ArrayList(0);
        try {
            if (retrieveProductStructureRequest.isEditMode()) {
                lockAquired = bom.lock(productStructureBO,
                        retrieveProductStructureRequest.getUser());
            }
            if (lockAquired) {
                bom.retrieve(productStructureBO,
                        retrieveProductStructureRequest.getUser());
            }

            List messages = new ArrayList(1);
            retrieveProductStructureResponse = (RetrieveProductStructureResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.RETRIEVE_PRODUCT_STRUCTURE_RESPONSE);
            retrieveProductStructureResponse.setLockAquired(lockAquired);
            if (lockAquired) {
                retrieveProductStructureResponse.setSuccess(true);
                retrieveProductStructureResponse
                        .setProductStructureBO(productStructureBO);
                retrieveProductStructureResponse.setDomainDetail(DomainUtil
                        .retrieveDomainDetailInfo(
                                BusinessConstants.PRODSTRUCTURE,
                                productStructureBO
                                        .getProductStructureParentId()));
            } else {
                messages.add(new InformationalMessage(
                        BusinessConstants.MSG_LOCKED_USER));
                retrieveProductStructureResponse.setMessages(messages);
            }
            Logger
                    .logInfo("Returning execute method for retrieving product structure");
            return retrieveProductStructureResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(retrieveProductStructureRequest);
            String logMessage = "Failed while processing RetrieveProductStructureRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * To get the valid BenefitComponents that can be added to the
     * ProductStructure
     * 
     * @param benefitComponentPopUpRequest
     * @return RetrieveBenefitComponentPopUpResponse
     * @throws WPDException
     */
    public WPDResponse execute(
            RetrieveBenefitComponentPopUpRequest benefitComponentPopUpRequest)
            throws ServiceException {
        try {
            Logger
                    .logInfo("Entering execute method for retrieving benefit component pop up");
            // Get the BusinessObjectManager
            BusinessObjectManager bom = getBusinessObjectManager();

            // Create the locateCriteria object
            ProductStructureBenefitComponentLocateCriteria locateCriteria = new ProductStructureBenefitComponentLocateCriteria();

            //FIXME We are not supposed to call adapter directly from service
            // class.
            // Set the locate criteria from the request to the crteriaObject
            BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();

            // Create an instance of the Response object
            RetrieveBenefitComponentPopUpResponse benefitComponentPopUpResponse = new RetrieveBenefitComponentPopUpResponse();

            ProductStructureVO productStructureVO = benefitComponentPopUpRequest
                    .getProductStructureVO();

            List businessDomain = productStructureVO.getBusinessDomains();
            List lob = BusinessUtil.getLobList(businessDomain);
            List be = BusinessUtil.getbusinessEntityList(businessDomain);
            List bg = BusinessUtil.getBusinessGroupList(businessDomain);
            List mbu = BusinessUtil.getMarketBusinessUnitList(businessDomain);
            // Set the above retrieved list in the response object
            benefitComponentPopUpResponse
                    .setBenefitComponentList(adapterManager.locate(
                            benefitComponentPopUpRequest
                                    .getProductStructureId(), lob, be, bg,mbu));
            Logger
                    .logInfo("Returning execute method for retrieving benefit component pop up");
            return benefitComponentPopUpResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(benefitComponentPopUpRequest);
            String logMessage = "Failed while processing RetrieveBenefitComponentPopUpRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Saves the benefit components chosen by the user to the db
     * 
     * @param saveProductStructureBenefitComponentRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveProductStructureBenefitComponentRequest saveProductStructureBenefitComponentRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for saving product structure benefit component");
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureVO productStructureVO = saveProductStructureBenefitComponentRequest
                .getProductStructureVO();
        ProductStructureBO productStructure = getProductStructureBO(productStructureVO);
        SaveProductStructureResponse response = null;
        List messageList = new ArrayList(2);
        try {
            List benefitComponentList = productStructureVO
                    .getBenefitComponentList();
            if (saveProductStructureBenefitComponentRequest.getAction() == SaveProductStructureBenefitComponentRequest.CREATE_PRODUCT_STRUCTURE_BENEFIT) {
                bom.persist(benefitComponentList, productStructure,
                        saveProductStructureBenefitComponentRequest.getUser(),
                        true);
                /** Removed PVA Validation for lines from java -- eWPD Stabilization release**/
              /**  PVABusinessObjectBuilder builder = new PVABusinessObjectBuilder();
              doing pva validation while attaching benefit component .
                validateQuestionPva = false;
        	    PVAvalidationResult validationResult=builder.doPVAvalidation(productStructure,benefitComponentList,saveProductStructureBenefitComponentRequest.getUser(),validateQuestionPva);
        	    List contains deleted list while PVA validation
        		List deletedComponentList = validationResult.getDletedComponentList();
                if(null!= deletedComponentList && deletedComponentList.size()>0){
                	List bcNameList = new ArrayList();
                	for(int i=0;i<deletedComponentList.size();i++){
                		ProductStructureAssociatedBenefitComponent componentBO = (ProductStructureAssociatedBenefitComponent)deletedComponentList.get(i);
        				bcNameList.add(componentBO.getName());
        			}
        			String bcString =null;
        			if(null!= bcNameList && bcNameList.size()>0){
        				bcString = StringUtil.commaSeperate(bcNameList);
        			}
                InformationalMessage message = new InformationalMessage(BusinessConstants.BENEFIT_COMPONENT_WITH_INAVLID_LINES);
		        message.setParameters(new String[] {bcString});
		        messageList.add(message);
                }
                if(null!=deletedComponentList){
                if(benefitComponentList.size()>deletedComponentList.size()){
                messageList.add(new InformationalMessage(
                        BusinessConstants.BENEFIT_COMPONENT_ADDED));
                }
                }else{                              **/
                	 messageList.add(new InformationalMessage(
                            BusinessConstants.BENEFIT_COMPONENT_ADDED));
              //  }
            } else {
                bom.persist(benefitComponentList, productStructure,
                        saveProductStructureBenefitComponentRequest.getUser(),
                        false);
                messageList.add(new InformationalMessage(
                        BusinessConstants.BENEFIT_COMPONENT_UPDATED));
            }
            productStructure = (ProductStructureBO) bom.retrieve(
                    productStructure,
                    saveProductStructureBenefitComponentRequest.getUser(),
                    new HashMap());
            if (saveProductStructureBenefitComponentRequest.getAction() == SaveProductStructureBenefitComponentRequest.CREATE_PRODUCT_STRUCTURE_BENEFIT) {
                List changedIds = getChangedIds(
                        saveProductStructureBenefitComponentRequest
                                .getProductStructureVO()
                                .getBenefitComponentList(), productStructure);
//                if (!changedIds.isEmpty()) {
//                    AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//                    validationRqst
//                            .setEntityId(saveProductStructureBenefitComponentRequest
//                                    .getProductStructureVO()
//                                    .getProductStructureId());
//                    validationRqst
//                            .setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT_STRUCTURE);
//                    validationRqst.setChangedIds(changedIds);
//                    validationRqst
//                            .setLevel(AdminMethodSynchronousValidationRequest.BC_LIST_INPRODUCT);
//
//                    AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
//                            .execute(validationRqst);
//                }
            }
            response = (SaveProductStructureResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.SAVE_PRODUCT_STRUCTURE_RESPONSE);
            response.setProductStructure(productStructure);
            
            response.setMessages(messageList);
            
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(saveProductStructureBenefitComponentRequest);
            String logMessage = "Failed while processing SaveProductStructureBenefitComponentRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("Returning execute method for saving product structure benefit component");
        return response;
    }

    /**
     * @param benefitComponentList
     * @param productStructure
     */
    private List getChangedIds(List bcList, ProductStructureBO ps) {
        List changedIds = new ArrayList(bcList == null ? 0 : bcList.size());
        if (null != bcList && ps != null && bcList.size() > 0) {
            List affectedList = bcList;
            List totalList = ps.getAssociatedBenefitComponentList();
            List affectedIds = new ArrayList(bcList.size());
            boolean generalBenefitsPresnt = false;
            for (Iterator i = affectedList.iterator(); i.hasNext();) {
                ProductStructureAssociatedBenefitComponent component = (ProductStructureAssociatedBenefitComponent) i
                        .next();
                if (BusinessConstants.GENERAL_BENEFITS.equals(component
                        .getName())) {
                    generalBenefitsPresnt = true;
                    changedIds.add(new Integer(component
                            .getBenefitComponentId()));
                    break;
                } else {
                    affectedIds.add(new Integer(component
                            .getBenefitComponentId()));
                }
            }

            if (!generalBenefitsPresnt) {
                for (Iterator i = totalList.iterator(); i.hasNext();) {
                    ProductStructureAssociatedBenefitComponent component = (ProductStructureAssociatedBenefitComponent) i
                            .next();
                    if (BusinessConstants.GENERAL_BENEFITS.equals(component
                            .getName())) {
                        changedIds = affectedIds;
                        break;
                    }
                }
            }
        }
        return changedIds;

    }

    /**
     * Searches a product strcture
     * 
     * @param searchProductStructureRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            SearchProductStructureRequest searchProductStructureRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for searching product structure");
        List locateResultList = null;
        int locateResultCount = 0;
        List errorList = new ArrayList(3);
        SearchProductStructureResponse response = null;
        ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO = (ProductStructureLocateCriteriaVO) searchProductStructureRequest
                .getProductStructureLocateCriteriaVO();
        ProductStructureLocateCriteria productStructureLocateCriteriaBO = (ProductStructureLocateCriteria) getProductStructureLocateCriteriaBO(productStructureLocateCriteriaVO);

        response = (SearchProductStructureResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_PRODUCT_STRUCTURE_RESPONSE);
        if (isAllFieldsBlank(productStructureLocateCriteriaVO)) {
            errorList.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));
            response.setMessages(errorList);
        } else {
            BusinessObjectManager bom = getBusinessObjectManager();
            try {
                LocateResults locateResults = bom.locate(
                        productStructureLocateCriteriaBO,
                        searchProductStructureRequest.getUser());
                if (null != locateResults) {
                    locateResultList = locateResults.getLocateResults();
                    locateResultCount = locateResultList.size();
                    if (locateResultCount > 0 && locateResultCount <= 50) {

                        response.setProductStructureList(locateResultList);
                    } else if (locateResultCount > 50) {
                        locateResultList.remove(50);
                        response.setProductStructureList(locateResultList);
                        errorList.add(new InformationalMessage(
                                BusinessConstants.SEARCH_RESULT_EXCEEDS));
                        response.setMessages(errorList);
                    } else if (locateResultCount == 0) {
                        errorList.add(new InformationalMessage(
                                BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                        response.setMessages(errorList);
                    }
                }
            } catch (WPDException ex) {
                List logParameters = new ArrayList(1);
                logParameters.add(searchProductStructureRequest);
                String logMessage = "Failed while processing SearchProductStructureRequest";
                throw new ServiceException(logMessage, logParameters, ex);
            }
        }
        Logger
                .logInfo("Returning execute method for searching product structure");
        return response;

    }

    /**
     * Changes the status,state and version details of a ProductStructure
     * 
     * @param productStructureLifeCycleRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            ProductStructureLifeCycleRequest productStructureLifeCycleRequest)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for product structure life cycle");
        ProductStructureLifeCycleResponse productStructureLifeCycleResponse = (ProductStructureLifeCycleResponse) new ValidationServiceController()
                .execute(productStructureLifeCycleRequest);
        List messageList = new ArrayList(7);
        LocateResults locateResults = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        ProductStructureVO productStructureVO = (ProductStructureVO) productStructureLifeCycleRequest
                .getProductStructureVO();
        ProductStructureBO productStructureBO = (ProductStructureBO) getProductStructureBO(productStructureVO);

        try {
            if (null != productStructureLifeCycleResponse
                    && productStructureLifeCycleResponse.isSuccess()) {
                if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.SCHEDULE_FOR_TEST_PRODUCT_STRUCTURE) {
                    bom.scheduleForTest(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    messageList
                            .add(new InformationalMessage(
                                    BusinessConstants.PRODUCT_STRUCTURE_SCHEDULED_TO_TEST));

                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.TEST_PASS_PRODUCT_STRUCTURE) {
                    bom.testPass(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    messageList.add(new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_PASSED_TEST));
                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.TEST_FAIL_PRODUCT_STRUCTURE) {

                    bom.testFail(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    messageList.add(new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_FAILED_TEST));
                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.PUBLISH_PRODUCT_STRUCTURE) {

                    bom.publish(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    messageList.add(new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_PUBLISHED));

                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.APPROVE_PRODUCT_STRUCTURE) {
                    bom.approve(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    bom.publish(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    InformationalMessage informationalMessage = new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_APPROVED);
                    informationalMessage
                            .setParameters(new String[] { productStructureBO
                                    .getProductStructureName() });
                    messageList.add(informationalMessage);

                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.REJECT_PRODUCT_STRUCTURE) {
                    bom.reject(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    InformationalMessage informationalMessage = new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_REJECTED);
                    informationalMessage
                            .setParameters(new String[] { productStructureBO
                                    .getProductStructureName() });
                    messageList.add(informationalMessage);
                } else if (productStructureLifeCycleRequest.getAction() == ProductStructureLifeCycleRequest.UNLOCK_PRODUCT_STRUCTURE) {
                    bom.unlock(productStructureBO,
                            productStructureLifeCycleRequest.getUser());
                    InformationalMessage informationalMessage = new InformationalMessage(
                            BusinessConstants.PRODUCT_STRUCTURE_UNLOCKED);
                    informationalMessage
                            .setParameters(new String[] { productStructureBO
                                    .getProductStructureName() });
                    messageList.add(informationalMessage);
                }

                productStructureLifeCycleResponse.setMessages(messageList);
            }
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO = (ProductStructureLocateCriteriaVO) productStructureLifeCycleRequest
                    .getProductStructureLocateCriteriaVO();

            if (productStructureLocateCriteriaVO != null) {
                ProductStructureLocateCriteria productStructureLocateCriteriaBO = (ProductStructureLocateCriteria) getProductStructureLocateCriteriaBO(productStructureLocateCriteriaVO);
                locateResults = bom.locate(productStructureLocateCriteriaBO,
                        productStructureLifeCycleRequest.getUser());
            } else if (productStructureLifeCycleRequest.getProductStructureId() != 0) {
                ViewAllVersionsLocateCriteria viewAllVersionsLocateCriteria = new ViewAllVersionsLocateCriteria();
                viewAllVersionsLocateCriteria
                        .setProductStructureId(productStructureLifeCycleRequest
                                .getProductStructureId());
                locateResults = bom.locate(viewAllVersionsLocateCriteria,
                        productStructureLifeCycleRequest.getUser());
            }

            if (null != locateResults) {
                List locateResultList = locateResults.getLocateResults();
                int locateResultCount = locateResultList.size();
                productStructureLifeCycleResponse
                        .setProductStructureList(locateResultList);
                if (locateResultCount == 0) {
                    messageList.add(new InformationalMessage(
                            BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                    productStructureLifeCycleResponse.setMessages(messageList);
                }
            }

            productStructureLifeCycleResponse.setSuccess(true);
            Logger
                    .logInfo("Returning execute method for product structure life cycle");
            return productStructureLifeCycleResponse;
        } catch (WPDException ex) {
            List logParameters = new ArrayList(1);
            logParameters.add(productStructureLifeCycleRequest);
            String logMessage = "Failed while processing ProductStructureLifeCycleRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
    }

    /**
     * Sets the values from VO to BO
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
        if (null != productStructureVO.getBusinessEntity()) {
            List lineOfBusiness = productStructureVO.getLineOfBusiness();
            List businessEntity = productStructureVO.getBusinessEntity();
            List businessGroup = productStructureVO.getBusinessGroup();
            //CARS START
            List marketBusinessUnit = productStructureVO.getMarketBusinessUnit();
            productStructureBO.setBusinessDomains(BusinessUtil
                    .convertToDomains(lineOfBusiness, businessEntity,
                            businessGroup, marketBusinessUnit));
            //CARS END
        } else
            productStructureBO.setBusinessDomains(productStructureVO
                    .getBusinessDomains());
        productStructureBO.setStatus(productStructureVO.getStatus());
        productStructureBO.setVersion(productStructureVO.getVersion());
        productStructureBO.setStructureType(productStructureVO
                .getStructureType());
        productStructureBO.setMandateType(productStructureVO.getMandateType());
        productStructureBO.setStateId(productStructureVO.getStateId());
        productStructureBO.setStateDesc(productStructureVO.getStateDesc());
        productStructureBO.setProductFamilyId(productStructureVO.getProductFamilyId());
        //Setting the status of the checkbox
        return productStructureBO;

    }

    /**
     * Sets the values from VO to BO
     * 
     * @param productStructureVO
     * @return ProductStructureBO
     */
    private ProductStructureBO getProductStructureBOForSave(
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
        productStructureBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));
        //CARS END
        productStructureBO.setStatus(productStructureVO.getStatus());
        productStructureBO.setVersion(productStructureVO.getVersion());
        productStructureBO.setStructureType(productStructureVO
                .getStructureType());
        productStructureBO.setMandateType(productStructureVO.getMandateType());
        productStructureBO.setStateId(productStructureVO.getStateId());
        productStructureBO.setStateDesc(productStructureVO.getStateDesc());
        productStructureBO.setProductFamilyId(productStructureVO.getProductFamilyId());
        return productStructureBO;
    }

    /**
     * Sets the values from Locate Criteria VO to BO
     * 
     * @param productStructureLocateCriteriaVO
     * @return productStructureLocateCriteriaBO
     */
    private ProductStructureLocateCriteria getProductStructureLocateCriteriaBO(
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO) {
        ProductStructureLocateCriteria productStructureLocateCriteriaBO = new ProductStructureLocateCriteria();
        productStructureLocateCriteriaBO.setProductStructureName(BusinessUtil
                .escpeSpecialCharacters(productStructureLocateCriteriaVO
                        .getProductStructureName()));
        productStructureLocateCriteriaBO
                .setLineOfBusiness(productStructureLocateCriteriaVO
                        .getLineOfBusiness());
        productStructureLocateCriteriaBO
                .setBusinessEntity(productStructureLocateCriteriaVO
                        .getBusinessEntity());
        productStructureLocateCriteriaBO
                .setBusinessGroup(productStructureLocateCriteriaVO
                        .getBusinessGroup());
        //CARS START
        //Setting the VO to BO
        productStructureLocateCriteriaBO
				.setMarketBusinessUnit(productStructureLocateCriteriaVO
						.getMarketBusinessUnit());
        //CARS END
        productStructureLocateCriteriaBO
                .setEffectiveDate(productStructureLocateCriteriaVO
                        .getEffectiveDate());
        productStructureLocateCriteriaBO
                .setExpiryDate(productStructureLocateCriteriaVO.getExpiryDate());
        return productStructureLocateCriteriaBO;
    }

    /**
     * Checks whether the fields are empty
     * 
     * @param productStructureLocateCriteriaVO
     * @return boolean
     */
    private boolean isAllFieldsBlank(
            ProductStructureLocateCriteriaVO productStructureLocateCriteriaVO) {
        if ((null == productStructureLocateCriteriaVO.getProductStructureName() || ""
                .equals(productStructureLocateCriteriaVO
                        .getProductStructureName()))
                && (null == productStructureLocateCriteriaVO
                        .getLineOfBusiness() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getLineOfBusiness()))
                && (null == productStructureLocateCriteriaVO
                        .getBusinessEntity() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getBusinessEntity()))
                && (null == productStructureLocateCriteriaVO.getBusinessGroup() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getBusinessGroup()))
                && (null == productStructureLocateCriteriaVO.getMarketBusinessUnit() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getMarketBusinessUnit()))
                && (null == productStructureLocateCriteriaVO.getEffectiveDate() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getEffectiveDate()))
                && (null == productStructureLocateCriteriaVO.getExpiryDate() || ""
                        .equals(productStructureLocateCriteriaVO
                                .getExpiryDate()))) {

            return true;
        }
        return false;
    }

    /**
     * Method for creating target BO
     * 
     * @param productStructureBO
     * @return ProductStructureBO
     */
    private ProductStructureBO createTargetBOForCopy(
            ProductStructureBO productStructureBO) {
        ProductStructureBO srcProductStructureBO = new ProductStructureBO();
        srcProductStructureBO.setProductStructureId(productStructureBO
                .getProductStructureId());
        srcProductStructureBO.setProductStructureParentId(productStructureBO
                .getProductStructureParentId());
        srcProductStructureBO.setProductStructureName(productStructureBO
                .getProductStructureName());
        srcProductStructureBO.setDescription(productStructureBO
                .getDescription());
        srcProductStructureBO.setEffectiveDate(productStructureBO
                .getEffectiveDate());
        srcProductStructureBO.setExpiryDate(productStructureBO.getExpiryDate());
        srcProductStructureBO
                .setAssociatedBenefitComponentList(productStructureBO
                        .getAssociatedBenefitComponentList());
        srcProductStructureBO.setBusinessDomains(productStructureBO
                .getBusinessDomains());
        srcProductStructureBO.setStatus(productStructureBO.getStatus());
        srcProductStructureBO.setVersion(productStructureBO.getVersion());
        srcProductStructureBO.setStructureType(productStructureBO
                .getStructureType());
        srcProductStructureBO.setMandateType(productStructureBO
                .getMandateType());
        srcProductStructureBO.setStateId(productStructureBO.getStateId());
        srcProductStructureBO.setStateDesc(productStructureBO.getStateDesc());
        productStructureBO.setProductStructureParentId(productStructureBO
                .getProductStructureId());

        return srcProductStructureBO;

    }

    /**
     * Gets the bom
     * 
     * @return
     */
    private BusinessObjectManager getBusinessObjectManager() {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        return bom;
    }

    /**
     * Gets the associated benefit component
     * 
     * @param benefitComponetId
     * @param productStructureId
     * @return
     */
    private ProductStructureAssociatedBenefitComponent getProductStructureAssociatedBenefitComponent(
            List benefitComponentDeleteList, int productStructureId) {
        ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
        productStructureAssociatedBenefitComponent
                .setBenefitComponentDeleteList(benefitComponentDeleteList);
        productStructureAssociatedBenefitComponent
                .setProductStructureId(productStructureId);
        return productStructureAssociatedBenefitComponent;

    }

    /**
     * Checks if the key values are changed
     * 
     * @param productStructureBO
     * @param oldKeyproductStructureBO
     * @return
     */
    private boolean isKeyValueChanged(ProductStructureBO productStructureBO,
            ProductStructureBO oldKeyproductStructureBO) {
        if (!productStructureBO.getProductStructureName().equals(
                oldKeyproductStructureBO.getProductStructureName()))
            return true;
        if (!BusinessUtil.isBuisnessDomainsEqual(productStructureBO
                .getBusinessDomains(), oldKeyproductStructureBO
                .getBusinessDomains()))
            return true;
        return false;
    }

    /**
     * Adds the message to the response
     * 
     * @param response
     * @param messages
     */
    private void addMessagesToResponse(WPDResponse response, List messages) {
        if (null == messages || messages.size() == 0)
            return;
        if (null == response.getMessages())
            response.setMessages(messages);
        else
            response.getMessages().addAll(messages);
    }

    /**
     * Locate for Notes attached to BenefitComponent
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(ProductStructureNotesRequest request)
            throws SevereException {
        ProductStructureNotesResponse productStructureNotesResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        productStructureNotesResponse = (ProductStructureNotesResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PRODUCT_STRUCTURE_NOTES_RESPONSE);
        ProductStructureNotesLocateCriteria notesLocateCriteria = new ProductStructureNotesLocateCriteria();
        if (request.getAction() == BusinessConstants.INT_1) {
            notesLocateCriteria.setEntityId(request.getEntityId());
            notesLocateCriteria.setEntityType(request.getEntityType());
            notesLocateCriteria
                    .setMaximumResultSize(request.getMaxResultSize());
            notesLocateCriteria.setAction(BusinessConstants.INT_1);
        } else if (request.getAction() == BusinessConstants.INT_2) {
            notesLocateCriteria.setEntityId(request.getEntityId());
            notesLocateCriteria.setEntityType(request.getEntityType());
            notesLocateCriteria
                    .setMaximumResultSize(request.getMaxResultSize());
            notesLocateCriteria.setSecEntityId(request.getSecEntityId());
            notesLocateCriteria.setSecEntityType(request.getSecEntityType());
            notesLocateCriteria.setBenefitComponentId(request
                    .getBenefitComponentId());
            notesLocateCriteria.setAction(BusinessConstants.INT_2);
        }

        LocateResults locateResults;
        try {
            locateResults = bom.locate(notesLocateCriteria, request.getUser());
        } catch (WPDException e) {
            List logParameters = new ArrayList(1);
            logParameters.add(request);
            String logMessage = "Failed while processing LocateBenefitComponentNotesRequest";
            throw new SevereException(logMessage, logParameters, e);

        }
        if (null != locateResults && null != locateResults.getLocateResults()
                && !locateResults.getLocateResults().isEmpty()) {
            productStructureNotesResponse
                    .setBenefitComponentNotesList(locateResults
                            .getLocateResults());
        }
        return productStructureNotesResponse;
    }

    /**
     * Saves admin options for Product Structure
     * 
     * @param SaveAdminOptionRequestForPS
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SaveAdminOptionRequestForPS request)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for saving benefit administration");
        // Create the response object from the response factory
        SaveAdminOptionResponseForPS response = (SaveAdminOptionResponseForPS) WPDResponseFactory
                .getResponse(WPDResponseFactory.ADMIN_OPTION_RESPONSE_FOR_PS);

        try {
            // Get the BusinessObjectManager
            BusinessObjectManager bom = getBusinessObjectManager();
            // Get the ProductStructureVO from the request
            ProductStructureVO productStructureVO = (ProductStructureVO) request
                    .getProductStructureVO();
            // Create the BO using the VO
            ProductStructureBO productStructureBO = getProductStructureBO(productStructureVO);

            HideAdminOption hideAdminOption = null;

            List adminOverideList = request.getAdminOveriddenList();
            Iterator iterAdminOveride = adminOverideList.iterator();
            ProductBO product = new ProductBO();

            List adminBOList = new ArrayList(adminOverideList.size());

            AdminOptionHideVO adminOptionVO = null;

            // Iterate through the list and save the administration options one
            // by one
            for (int i = 0; i < adminOverideList.size(); i++) {

                adminOptionVO = new AdminOptionHideVO();
                adminOptionVO = (AdminOptionHideVO) iterAdminOveride.next();
                hideAdminOption = new HideAdminOption();
                hideAdminOption.setAdminOptionSystemId(adminOptionVO
                        .getAdminOptionId());
                hideAdminOption.setAdminLevelSystemId(adminOptionVO
                        .getAdminLevelId());
                hideAdminOption.setBenefitLevelSysId(adminOptionVO
                        .getBenefitLevelId());
                hideAdminOption.setAdminQuestionHideFlag(adminOptionVO
                        .getQuestionHideFlag());
                hideAdminOption.setQuestionHideFlag(adminOptionVO
                        .getQuestionHideFlag());
                //optionBO.setEntityType(request.getEntityType());
                hideAdminOption.setEntityId(request.getProductStructureVO()
                        .getProductStructureId());
                hideAdminOption.setBenefitCompSysId(request
                        .getBenefitComponentId());
                hideAdminOption.setAdminLevelOptionAssnSystemId(adminOptionVO
                        .getAdmnLvlAsscId());
                hideAdminOption.setCreatedUser(request.getUser().getUserId());
                hideAdminOption.setLastUpdatedUser(request.getUser()
                        .getUserId());
                hideAdminOption.setBenefitAdminSystemId(adminOptionVO
                        .getBenefitAdminId());
                adminBOList.add(hideAdminOption);

            }

            // Set the BOList to the BO
            hideAdminOption.setAdminBOList(adminBOList);

            // Call the persisit method of the bom to persist the
            // overridden values to the db
            bom.persist(hideAdminOption, productStructureBO, request.getUser(),
                    false);

//            if (request.isChanged()) {
//                AdminMethodSynchronousValidationRequest validationRqst = new AdminMethodSynchronousValidationRequest();
//                validationRqst.setBenefitComponentId(request
//                        .getBenefitComponentId());
//                validationRqst.setEntityId(request.getProductStructureVO()
//                        .getProductStructureId());
//                validationRqst
//                        .setEntityType(AdminMethodSynchronousValidationRequest.TYPE_PRODUCT_STRUCTURE);
//                validationRqst.setChangedIds(request.getChangedIds());
//                validationRqst.setBenefitAdministrationId(request
//                        .getBenefitAdminId());
//                validationRqst.setBenefitCompName(request.getBCompName());
//                validationRqst
//                        .setLevel(AdminMethodSynchronousValidationRequest.ADMIN_OPTION_SAVE_IN_BENEFIT_ADMIN);
//                validationRqst.setBenefitId(request.getBenefitSysId());
//                AdminMethodSynchronousValidationResponse validationResponse = (AdminMethodSynchronousValidationResponse) new ValidationServiceController()
//                        .execute(validationRqst);
//            }

            AdministrativeOptionLocateCriteria criteria = new AdministrativeOptionLocateCriteria();
            criteria.setBenefitAdministrationSystemId(request
                    .getBenefitAdminId());
            criteria.setBenefitComponentId(request.getBenefitComponentId());

            criteria.setEntityId(request.getProductStructureVO()
                    .getProductStructureId());
            criteria.setEntityType(request.getEntityType());
            criteria.setPSorProduct(request.getPSorProductorBenefit());
            LocateResults locateResults = bom.locate(criteria, request
                    .getUser());
            response.setAdminOptionList(locateResults.getLocateResults());

            List messageList = new ArrayList(1);
            messageList.add(new InformationalMessage(
                    BusinessConstants.ADMIN_OPTIONS_UPDATE));
            addMessagesToResponse(response, messageList);

        } catch (WPDException ex) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing SaveBenefitAdministrationRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger
                .logInfo("Returning execute method for saving benefit administration");
        return response;
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
            RetrieveProdStructureComponentHierarchyRequest request)
            throws ServiceException {
        Logger
                .logInfo("Entering execute method for retrieving component hierarchy");
        RetrieveProdStructureComponentHierarchyResponse hierarchyResponse = null;
        BusinessObjectManager bom = getBusinessObjectManager();
        hierarchyResponse = (RetrieveProdStructureComponentHierarchyResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PROD_STRUCTURE_HIERARCHY_RESPONSE);
        ProductStructureBO productStructureBO = getProductStructureBO(request
                .getProductStructureVO());
        LocateResults locateResults = null;
        ProductStructureShowHiddenLocateCriteria locateCriteria = new ProductStructureShowHiddenLocateCriteria();
        locateCriteria.setProductStructureId(productStructureBO
                .getProductStructureId());
        locateCriteria.setHierarchyFlag(true);
        try {
            locateResults = bom.locate(locateCriteria, request.getUser());
            hierarchyResponse.setComponentHierarchyList(locateResults
                    .getLocateResults());

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
     * The method is invoked when the note is attached or detached or delete from a question
     * in the product structure module,benefit level admin options.
     * @param attachNotesToQuestionRequestForPS
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(
            AttachNotesToQuestionRequestForPS attachNotesToQuestionRequestForPS)
            throws ServiceException {
        AttachNotesToQuestionResponseForPS attachNotesToQuestionResponseForPS = 
            (AttachNotesToQuestionResponseForPS) WPDResponseFactory
                .getResponse(WPDResponseFactory.ATTACH_NOTESTOQUESTION_PS);
        NotesToQuestionAttachmentBO notesToQuestionAttachmentBO = 
            getNotesToQuestionAttachmentBO(attachNotesToQuestionRequestForPS
                .getNotesAttachVO());

        BusinessObjectManager bom = getBusinessObjectManager();

        ProductStructureBO productStructureBO = new ProductStructureBO();
        productStructureBO
                .setProductStructureName(attachNotesToQuestionRequestForPS
                        .getNotesAttachVO().getPrimaryEntityName());
        productStructureBO.setBusinessDomains(attachNotesToQuestionRequestForPS
                .getNotesAttachVO().getBusinessDomainList());
        productStructureBO.setVersion(attachNotesToQuestionRequestForPS
                .getNotesAttachVO().getProdStructVersionNumber());

        List messageList = new ArrayList();
        String action = attachNotesToQuestionRequestForPS.getNotesAttachVO().getAction();
        try {
            if (WebConstants.INSERT.equals(action)) {
                bom.persist(notesToQuestionAttachmentBO, productStructureBO, // for attaching a note
                        attachNotesToQuestionRequestForPS.getUser(), true);
                InformationalMessage informationalMessage = new InformationalMessage(
                        BusinessConstants.SAVE_QUESTION_NOTE__SUCCESS);
                messageList.add(informationalMessage);
            }else if (WebConstants.UPDATE.equals(action)) { //for updating a note
                bom.persist(notesToQuestionAttachmentBO, productStructureBO,
                        attachNotesToQuestionRequestForPS.getUser(), false);
                InformationalMessage informationalMessage = new InformationalMessage(
                        BusinessConstants.UPDATE_QUESTION_NOTE__SUCCESS);
                messageList.add(informationalMessage);
            }else if (WebConstants.DELETE.equals(action)) { //for detaching note
                bom.delete(notesToQuestionAttachmentBO, productStructureBO,
                        attachNotesToQuestionRequestForPS.getUser());
                InformationalMessage informationalMessage = new InformationalMessage(
                        BusinessConstants.DELETE_QUESTION_NOTE__SUCCESS);
                messageList.add(informationalMessage);
            }
        } catch (WPDException exception) {

            List errorParams = new ArrayList();
            String obj = exception.getClass().getName();
            errorParams.add(obj);

            throw new ServiceException(
                    "Exception occured in execute method ,while persisting the "
                            + "BusinessObject NotesToQuestionAttachmentBO, in PSBusinessService",
                    errorParams, exception);

        }       
        addMessagesToResponse(attachNotesToQuestionResponseForPS, messageList);
        return attachNotesToQuestionResponseForPS;
    }

    /**
     * The method creates a NotesToQuestionAttachmentBO and sets all the necessary parameters 
     * in it and returns the object.
     * @param prodStructNotesToQuestionAttachmentVO
     * @return
     */
    private NotesToQuestionAttachmentBO getNotesToQuestionAttachmentBO(
            ProdStructNotesToQuestionAttachmentVO prodStructNotesToQuestionAttachmentVO) {
        NotesToQuestionAttachmentBO notesToQuestionAttachmentBO = new NotesToQuestionAttachmentBO();
        notesToQuestionAttachmentBO
                .setBenefitCompId(prodStructNotesToQuestionAttachmentVO
                        .getBenefitCompId());
        notesToQuestionAttachmentBO.setBnftDefId(String
                .valueOf(prodStructNotesToQuestionAttachmentVO.getBnftDefId()));
        notesToQuestionAttachmentBO
                .setNoteId(prodStructNotesToQuestionAttachmentVO.getNoteId());
        notesToQuestionAttachmentBO
                .setNoteOverrideStatus(prodStructNotesToQuestionAttachmentVO
                        .getNoteOverrideStatus());
        notesToQuestionAttachmentBO
                .setNoteVersionNumber(prodStructNotesToQuestionAttachmentVO
                        .getNoteVersionNumber());
        notesToQuestionAttachmentBO
                .setPrimaryEntityType(prodStructNotesToQuestionAttachmentVO
                        .getPrimaryEntityType());
        notesToQuestionAttachmentBO
                .setPrimaryId(prodStructNotesToQuestionAttachmentVO
                        .getPrimaryId());
        notesToQuestionAttachmentBO
                .setQuestionId(prodStructNotesToQuestionAttachmentVO
                        .getQuestionId());
        notesToQuestionAttachmentBO
                .setSecondaryEntityType(prodStructNotesToQuestionAttachmentVO
                        .getSecondaryEntityType());
        notesToQuestionAttachmentBO
                .setSecondaryId(prodStructNotesToQuestionAttachmentVO
                        .getSecondaryId());
        /*notesToQuestionAttachmentBO
                .setAction(prodStructNotesToQuestionAttachmentVO.getAction());*/
        return notesToQuestionAttachmentBO;
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
}