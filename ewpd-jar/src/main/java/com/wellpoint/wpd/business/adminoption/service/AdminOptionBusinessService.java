/*
 * AdminOptionBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.adminoption.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.builder.AdminOptionBusinessObjectBuilder;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionQuestionLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.ChildQuestionnaireLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.RootQuestionnaireLocateCriteria;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.request.AddRootQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.AdminOptionUnlockRequest;
import com.wellpoint.wpd.common.adminoption.request.AdminOptionViewRequest;
import com.wellpoint.wpd.common.adminoption.request.ApproveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CheckInAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CheckOutAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CreateAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAllAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.EditRootQuestionsRequest;
import com.wellpoint.wpd.common.adminoption.request.LocateRootQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.PersistChildQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.PublishAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.RejectAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveChildQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveRootQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.ScheduleForTestAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.SearchAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.SearchDuplicateReferenceRequest;
import com.wellpoint.wpd.common.adminoption.request.TestFailAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.TestPassAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.ViewAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.AddRootQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionUnlockResponse;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionViewResponse;
import com.wellpoint.wpd.common.adminoption.response.ApproveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CheckOutAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CreateAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAllAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.LocateRootQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.PersistChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.PublishAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RejectAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveRootQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.ScheduleForTestAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.SearchAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.SearchDuplicateReferenceResponse;
import com.wellpoint.wpd.common.adminoption.response.TestFailAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.TestPassAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.ViewAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.adminoption.vo.AssociatedQuestionVO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Business Service class for Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionBusinessService extends WPDService {

   
    private static final String ADMINOPTION_VIEW_ALL = WebConstants.VIEW_ALL;

    /**
     * Default constructor
     *  
     */
    public AdminOptionBusinessService() {
    }


    /**
     * Implementation of the method of the super class.
     * 
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        if (request instanceof CreateAdminOptionRequest)
            return execute((CreateAdminOptionRequest) request);
        return null;
    }


    /**
     * Method to retrieve associated questions of Admin Option
     * 
     * @param retrieveAdminOptionQuestionRequest
     * @return WPDResponse retrieveAdminOptionQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest)
            throws ServiceException {
        List questionnaireList = null;
        Logger.logInfo("Inside Admin Option Question Retrieve Service");
        //Getting the response from the response factory
        RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = (RetrieveAdminOptionQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ADMIN_OPTION_QUESTION_RESPONSE);
    
        try {
	          AdminOptionBusinessObjectBuilder builder = new  AdminOptionBusinessObjectBuilder();	          
	          if(retrieveAdminOptionQuestionRequest.getAction() == 1){
	          	questionnaireList = BusinessUtil.getQuestionareHierarchy(
	          			builder.getProductViewQuestionnaire(
	          					retrieveAdminOptionQuestionRequest.getAdminOptionId(),
								retrieveAdminOptionQuestionRequest.getProductId()));
	          }
	          else if(retrieveAdminOptionQuestionRequest.getAction() == 2){
	          	questionnaireList = BusinessUtil.getQuestionareHierarchy(
	          			builder.getContractViewQuestionnaire(
	          					retrieveAdminOptionQuestionRequest.getAdminOptionId(),
								retrieveAdminOptionQuestionRequest.getContractSysId()));
	          }
	          
	          else{
	          	questionnaireList = BusinessUtil.getQuestionareHierarchy(builder.getQuestionnaire(retrieveAdminOptionQuestionRequest.getAdminOptionId()));
	          }
	          if(null != questionnaireList){
		          Iterator itr = questionnaireList.iterator();
		          while(itr.hasNext()){
		              AssociatedQuestionnaireBO bo = (AssociatedQuestionnaireBO)itr.next();
		              DomainDetail detail = DomainUtil.retrieveQuestionnaireDomainDetailInfo(bo.getQuestionnaireId());
		              bo.setDomainDetail(detail);
		          }
	          }
	          retrieveAdminOptionQuestionResponse
	                .setAssociatedQuestionList(questionnaireList);
	          Logger.logInfo("Admin Option Question Retrieved Successfully");
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            String logMessage = "Failed while processing RetrieveAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return retrieveAdminOptionQuestionResponse;
    }
    
    /**
     * Method to retrieve associated questions  having duplicate reference in an Admin Option
     * 
     * @param retrieveAdminOptionQuestionRequest
     * @return WPDResponse retrieveAdminOptionQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
    		SearchDuplicateReferenceRequest request)
    throws ServiceException {
    	SearchDuplicateReferenceResponse searchDuplicateReferenceResponse  = new SearchDuplicateReferenceResponse();
    	AdminOptionBusinessObjectBuilder builder = new  AdminOptionBusinessObjectBuilder();
    	try{
	    	List duplicateQuestionList = builder.getduplicateReferenceQuesations(request.getAdminOptionId());
	    	if(null != duplicateQuestionList )
	    		searchDuplicateReferenceResponse.setResultList(duplicateQuestionList);
    	}catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            String logMessage = "Failed while processing SearchDuplicateReferenceRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }catch(AdapterException ex){
        	 List logParameters = new ArrayList(2);
             String logMessage = "Failed while processing SearchDuplicateReferenceRequest";
             throw new ServiceException(logMessage, logParameters, ex);
        }
    	return searchDuplicateReferenceResponse;
    }

    /**
     * Method to search associated questions of Admin Option
     * 
     * @param adminOptionId
     * @param user
     * @return LocateResults locateResults
     * @throws ServiceException
     */
    private LocateResults searchAdminOptionQuestion(int adminOptionId, User user)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Question Search Service");
        LocateResults locateResults = new LocateResults();
        AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria = new AdminOptionQuestionLocateCriteria();
        BusinessObjectManager businessObjectManager = this
                .getBusinessObjectManager();
        adminOptionQuestionLocateCriteria.setAdminOptionId(adminOptionId);
        try {
            locateResults = businessObjectManager.locate(
                    adminOptionQuestionLocateCriteria, user);
            Logger.logInfo("Admin Option Question Search Successful");
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            String logMessage = "Failed while processing RetrieveAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return locateResults;
    }


    /**
     * Method for Save Admin Option Question
     * 
     * @param saveAdminOptionQuestionRequest
     * @return WPDResponse saveAdminOptionQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Question Save Service");
        // Getting the response from the response factory
        SaveAdminOptionQuestionResponse saveAdminOptionQuestionResponse = (SaveAdminOptionQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_ADMIN_OPTION_QUESTION_RESPONSE);
        try {
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(saveAdminOptionQuestionRequest
                    .getAdminOptionVO());
            AssociatedQuestionBO associatedQuestionBO = copyBusinessObjectFromValueObject(saveAdminOptionQuestionRequest
                    .getAssociatedQuestionVO());
            if (saveAdminOptionQuestionRequest.isUpdateFlag()) {
                businessObjectManager.persist(associatedQuestionBO,
                        adminOptionBO,
                        saveAdminOptionQuestionRequest.getUser(), false);
                saveAdminOptionQuestionResponse
                        .addMessage(new InformationalMessage(
                                BusinessConstants.MSG_QUESTION_SEQUENCE_UPDATE));
                Logger.logInfo("Admin Option  Question Updated Successfully");
            } else {
//                saveAdminOptionQuestionResponse = (SaveAdminOptionQuestionResponse) new ValidationServiceController()
//                        .execute(saveAdminOptionQuestionRequest);
//                associatedQuestionBO.setAssociatedQuestionList(saveAdminOptionQuestionResponse.getAssociatedQuestionVO().getAssociatedQuestionsList());
//                
                businessObjectManager.persist(associatedQuestionBO,
                        adminOptionBO, saveAdminOptionQuestionRequest
                                .getUser(), true);
                saveAdminOptionQuestionResponse
                        .addMessage(new InformationalMessage(
                                BusinessConstants.MSG_QUESTION_SEQUENCE_SAVE));
                Logger
                        .logInfo("Admin Option  Question Inserted Successfully");
            }
            LocateResults locateResults = this.searchAdminOptionQuestion(
                    adminOptionBO.getAdminOptionId(),
                    saveAdminOptionQuestionRequest.getUser());
            saveAdminOptionQuestionResponse.getAdminOptionVO()
                    .setAssociatedQuestionsList(
                            locateResults.getLocateResults());
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(saveAdminOptionQuestionRequest);
            String logMessage = "Failed while processing saveAdminOptionQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return saveAdminOptionQuestionResponse;
    }


    /**
     * Method to delete associated questions of Admin Option.
     * 
     * @param deleteAdminOptionQuestionRequest
     * @return WPDResponse deleteAdminOptionQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            DeleteAdminOptionQuestionRequest deleteAdminOptionQuestionRequest)
            throws ServiceException {

        Logger.logInfo("Inside Admin Option Question Delete Service");
        //Getting the response from the response factory
        DeleteAdminOptionQuestionResponse deleteAdminOptionQuestionResponse = (DeleteAdminOptionQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_ADMIN_OPTION_QUESTION_RESPONSE);

        try {
            AssociatedQuestionVO associatedQuestionVO = deleteAdminOptionQuestionRequest
                    .getAssociatedQuestionVO();
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(deleteAdminOptionQuestionRequest
                    .getAdminOptionVO());
            AssociatedQuestionBO associatedQuestionBO = copyBusinessObjectFromValueObject(associatedQuestionVO);
            deleteAdminOptionQuestionResponse = (DeleteAdminOptionQuestionResponse) new ValidationServiceController()
                    .execute(deleteAdminOptionQuestionRequest);
            if (!deleteAdminOptionQuestionResponse.isErrorFlag()) {
                BusinessObjectManager businessObjectManager = this
                        .getBusinessObjectManager();
                businessObjectManager.delete(associatedQuestionBO,
                        adminOptionBO, deleteAdminOptionQuestionRequest
                                .getUser());
                InformationalMessage infoMessage = new InformationalMessage(
                        BusinessConstants.MSG_QUESTION_SEQUENCE_DELETE);
                infoMessage.setParameters(new String[] {deleteAdminOptionQuestionRequest.getAdminOptionVO().getAdminName()});
                deleteAdminOptionQuestionResponse
                        .addMessage(infoMessage);
            }
            Logger.logInfo("Admin Option Question Deleted Successfully");

            LocateResults locateResults = this.searchAdminOptionQuestion(
                    associatedQuestionBO.getAdminOptionId(),
                    deleteAdminOptionQuestionRequest.getUser());
            deleteAdminOptionQuestionResponse.getAdminOptionVO()
                    .setAssociatedQuestionsList(
                            locateResults.getLocateResults());
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteAdminOptionQuestionRequest);
            String logMessage = "Failed while processing deleteAdminOptionQuestionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return deleteAdminOptionQuestionResponse;
    }


    /**
     * Saves the Admin Option details
     * 
     * @param createAdminOptionRequest
     * @return WPDResponse createAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(CreateAdminOptionRequest createAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Create Service");
        //Getting the response from the response factory
        CreateAdminOptionResponse createAdminOptionResponse = (CreateAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_ADMIN_OPTION_RESPONSE);
        try {
            //Creating AdminOptionBO object by coping the values from the
            // AdminOptionVO object
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(createAdminOptionRequest
                    .getAdminOptionVO());
           
            //Creating BusinessObjectManager object
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();

            //Checking the admin option to be saved is a newly created one or
            // modified one
            if (createAdminOptionRequest.isCreateFlag()) {
            		
                //Checking for validations
                createAdminOptionResponse = (CreateAdminOptionResponse) new ValidationServiceController()
                        .execute(createAdminOptionRequest);

                //If the validation fails
                if (createAdminOptionResponse.isErrorFlag()) {
                    createAdminOptionResponse
                            .setAdminOptionVO(createAdminOptionRequest
                                    .getAdminOptionVO());
                    return createAdminOptionResponse;
                }
               /* if(adminOptionBO.getVersion() <=1){
                	adminOptionBO.setAdminOptionParentSysId(createAdminOptionRequest.getAdminOptionVO().getAdminOptionParentSysId());
                }*/
                businessObjectManager.persist(adminOptionBO,
                        createAdminOptionRequest.getUser(), true);

                createAdminOptionResponse.addMessage(new InformationalMessage(
                        BusinessConstants.ADMIN_OPTION_SAVE));
                Logger.logInfo("Admin Option Saved Successfully");

            } else {
                businessObjectManager.persist(adminOptionBO,
                        createAdminOptionRequest.getUser(), false);

                createAdminOptionResponse.addMessage(new InformationalMessage(
                        "updateadminoption.success.info"));
                Logger.logInfo("Admin Option Updated Successfully");
            }

            //Retrieving the saved admin option details
            adminOptionBO = (AdminOptionBO) businessObjectManager.retrieve(
                    adminOptionBO, createAdminOptionRequest.getUser());

            //Creating AdminOptionVO object by coping the values from the
            // AdminOptionBO object
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);

            //Setting the new AdminOptionVO object to the
            // createAdminOptionResponse
            createAdminOptionResponse.setAdminOptionVO(adminOptionVO);

        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(createAdminOptionRequest);
            String logMessage = "Failed while processing CreateAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return createAdminOptionResponse;
    }

    /**
     * Add the root questions
     * 
     * @param AddRootQuestionRequest
     * @return WPDResponse addRootQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
    		AddRootQuestionRequest addRootQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Question Save Service");
        // Getting the response from the response factory
        AddRootQuestionResponse addRootQuestionResponse = (AddRootQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ADD_ROOT_QUESTION_RESPONSE);
        try {
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            RootQuestionnaireBO rootQuestionnaireBO = new RootQuestionnaireBO();
            rootQuestionnaireBO.setRootQuestionnaires(addRootQuestionRequest.getRootQuestionList());
	        businessObjectManager.persist(rootQuestionnaireBO, addRootQuestionRequest.getAdminOptionBO(),
	            			addRootQuestionRequest.getUser(), true);
	        
            addRootQuestionResponse.addMessage(new InformationalMessage (BusinessConstants.INSERT_ROOT_QUESTION_SUCCESS));        
           
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(addRootQuestionRequest);
            String logMessage = "Failed while processing addRootQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return addRootQuestionResponse;
    }
    
    /**
     * To edit the root questions
     * 
     * @param EditRootQuestionsRequest
     * @return WPDResponse response
     * @throws ServiceException
     */
    public WPDResponse execute(
    		EditRootQuestionsRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Question Save Service");
        // Getting the response from the response factory
        LocateRootQuestionResponse response = (LocateRootQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_ROOT_QUESTION_RESPONSE);
        List updatedResultList = null;
        try {
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            RootQuestionnaireBO rootQuestionnaireBO = new RootQuestionnaireBO();
        	rootQuestionnaireBO.setQuestionnairesToDelete(request.getQuestionnaireToDelete());
        	rootQuestionnaireBO.setRootQuestionnaires(request.getRootQuestionsList());
        	businessObjectManager.persist(rootQuestionnaireBO, request.getAdminOptionBO(),
        			request.getUser(), false);
	        if(request.isDeleteFlag())
	        	response.addMessage(new InformationalMessage (BusinessConstants.DELETE_ROOT_QUESTION_SUCCESS));
	        else
	        	response.addMessage(new InformationalMessage (BusinessConstants.UPDATE_ROOT_QUESTION_SUCCESS));
           
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing addRootQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return response;
    }
    
    /**
     * To locate the root questions
     * 
     * @param LocateRootQuestionRequest
     * @return WPDResponse locateRootQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
    		LocateRootQuestionRequest locateRootQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Question Save Service");
        // Getting the response from the response factory
        LocateRootQuestionResponse locateRootQuestionResponse = (LocateRootQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.LOCATE_ROOT_QUESTION_RESPONSE);
        AdminOptionQuestionLocateCriteria locateCriteria = new AdminOptionQuestionLocateCriteria();
        List locateResultList = null;
        try {
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            locateCriteria.setAdminOptionId(locateRootQuestionRequest.getAdminOptionId());
            locateCriteria.setAction(BusinessConstants.LOCATE_ROOT_QUESTIONS);
            LocateResults locateResults = businessObjectManager.locate(
            		locateCriteria,
            		locateRootQuestionRequest.getUser());
			if (null != locateResults) {
				locateResultList = locateResults.getLocateResults();
					
				locateRootQuestionResponse.setRootQuestionsList(locateResultList);
				
			}
           
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(locateRootQuestionRequest);
            String logMessage = "Failed while processing LocateRootQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return locateRootQuestionResponse;
    }

    /**
     * Retrieves the Admin Option details
     * 
     * @param retrieveAdminOptionRequest
     * @return WPDResponse retrieveAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            RetrieveAdminOptionRequest retrieveAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Retrieve Service");
        //Getting the response from the response factory
        RetrieveAdminOptionResponse retrieveAdminOptionResponse = (RetrieveAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ADMIN_OPTION_RESPONSE);
        try {
            //Creating AdminOptionBO object by coping the values from the
            // AdminOptionVO object
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(retrieveAdminOptionRequest
                    .getAdminOptionVO());

            //Creating BusinessObjectManager
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            boolean adminLock = businessObjectManager.lock(adminOptionBO,retrieveAdminOptionRequest.getUser());
            if(!adminLock){
            	retrieveAdminOptionResponse.addMessage(new InformationalMessage("Admin being edited by another user."));
            	retrieveAdminOptionResponse.setUnlockFlag(true);
            }
            else{
	            adminOptionBO = (AdminOptionBO) businessObjectManager.retrieve(
	                    adminOptionBO, retrieveAdminOptionRequest.getUser());
	
	            //Creating AdminOptionVO object by coping the values from the
	            // AdminOptionBO object
	            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
	
	            //Setting the new AdminOptionVO object to the
	            // retrieveAdminOptionResponse
	            retrieveAdminOptionResponse.setAdminOptionVO(adminOptionVO);
	            Logger.logInfo("Admin Option Retrieved Successfully");
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(retrieveAdminOptionRequest);
            String logMessage = "Failed while processing RetrieveAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return retrieveAdminOptionResponse;
    }


    /**
     * Check In the Admin Option details
     * 
     * @param checkInAdminOptionRequest
     * @return WPDResponse checkInAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            CheckInAdminOptionRequest checkInAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Checkin Admin Option Service");
        //Getting the response from the response factory
        CheckInAdminOptionResponse checkInAdminOptionResponse = (CheckInAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CHECKIN_ADMIN_OPTION_RESPONSE);

        try {

            //Creating AdminOptionBO object by coping the values from the
            // AdminOptionVO object
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(checkInAdminOptionRequest
                    .getAdminOptionVO());

            //Checking for validations
            checkInAdminOptionResponse = (CheckInAdminOptionResponse) new ValidationServiceController()
                    .execute(checkInAdminOptionRequest);

            //If validation not fails
            if (!checkInAdminOptionResponse.isErrorFlag()) {

                if (checkInAdminOptionRequest.isCheckInOpted()) {
                    //Creating BusinessObjectManager
                    BusinessObjectManager businessObjectManager = this
                            .getBusinessObjectManager();
                    businessObjectManager.checkIn(adminOptionBO,
                            checkInAdminOptionRequest.getUser());
                    
                  	InformationalMessage infoMessage = new InformationalMessage(
                            BusinessConstants.ADMIN_OPTION_CHECKEDIN);
    				infoMessage.setParameters(new String[] {checkInAdminOptionRequest.getAdminOptionVO().getAdminName()});
                    checkInAdminOptionResponse
                            .addMessage(infoMessage);
                    
                } else {
                    InformationalMessage infoMessage = new InformationalMessage(
                            BusinessConstants.ADMIN_OPTION_CHECKEDIN_VALIDATION);
    				infoMessage.setParameters(new String[] {checkInAdminOptionRequest.getAdminOptionVO().getAdminName()});
                    checkInAdminOptionResponse
                            .addMessage(infoMessage);
                    checkInAdminOptionResponse.setErrorFlag(true);
                }
            }

            //Creating AdminOptionVO object by coping the values from the
            // AdminOptionBO object
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);

            //Setting the new AdminOptionVO object to the
            // checkInAdminOptionResponse
            checkInAdminOptionResponse.setAdminOptionVO(adminOptionVO);
            Logger.logInfo("Admin Option Checked in Successfully");

        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_CHECKEDIN_FAILURE);
            errorMessage.setParameters(new String[] {checkInAdminOptionRequest.getAdminOptionVO().getAdminName()});
            checkInAdminOptionResponse.addMessage(errorMessage);
            checkInAdminOptionResponse.setErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(checkInAdminOptionRequest);
            String logMessage = "Failed while Checkin Admin Option";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return checkInAdminOptionResponse;
    }


    /**
     * Check Out the Admin Option details
     * 
     * @param checkOutAdminOptionRequest
     * @return WPDResponse checkOutAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            CheckOutAdminOptionRequest checkOutAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Checkout Admin Option Service");
        //Getting the response from the response factory
        CheckOutAdminOptionResponse checkOutAdminOptionResponse = (CheckOutAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CHECKOUT_ADMIN_OPTION_RESPONSE);

        try {

            //Creating AdminOptionBO object by coping the values from the
            // AdminOptionVO object
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(checkOutAdminOptionRequest
                    .getAdminOptionVO());

            //Creating BusinessObjectManager
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();

            //Getting the new admin option details after the Checked Out
            adminOptionBO = (AdminOptionBO) businessObjectManager.checkOut(
                    adminOptionBO, checkOutAdminOptionRequest.getUser());

            //Setting success message to the response
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_CHECKEDOUT);
            infoMessage.setParameters(new String[]{checkOutAdminOptionRequest.getAdminOptionVO().getAdminName()});
            checkOutAdminOptionResponse.addMessage(infoMessage);

            //Creating AdminOptionVO object by coping the values from the
            // AdminOptionBO object
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);

            //Setting the new AdminOptionVO object to the
            // checkOutAdminOptionResponse
            checkOutAdminOptionResponse.setAdminOptionVO(adminOptionVO);
            Logger.logInfo("Admin Option Checked out Successfully");
        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_CHECKEDOUT_FAILURE);
            errorMessage.setParameters(new String[]{checkOutAdminOptionRequest.getAdminOptionVO().getAdminName()});
            checkOutAdminOptionResponse.addMessage(errorMessage);
            checkOutAdminOptionResponse.setErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(checkOutAdminOptionRequest);
            String logMessage = "Failed while checkOutAdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return checkOutAdminOptionResponse;
    }


    /**
     * Method for Schedule For Test service
     * 
     * @param scheduleForTestAdminOptionRequest
     * @return WPDResponse scheduleForTestAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            ScheduleForTestAdminOptionRequest scheduleForTestAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Schedule For Test Service");
        ScheduleForTestAdminOptionResponse scheduleForTestAdminOptionResponse = (ScheduleForTestAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SCHEDULEFORTEST_ADMINOPTION_RESPONSE);
        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(scheduleForTestAdminOptionRequest
                .getAdminOptionVO());

        try {
            //Checking for validations
            scheduleForTestAdminOptionResponse = (ScheduleForTestAdminOptionResponse) new ValidationServiceController()
                    .execute(scheduleForTestAdminOptionRequest);

            //If validation succeeds
            if (!scheduleForTestAdminOptionResponse
                    .isScheduleForTestErrorFlag()) {
                BusinessObjectManager bom = this.getBusinessObjectManager();
                bom.scheduleForTest(adminOptionBO,
                        scheduleForTestAdminOptionRequest.getUser());
                InformationalMessage infoMessage = new InformationalMessage(
                        BusinessConstants.ADMIN_OPTION_SCHEDULED_FOR_TEST);
                infoMessage.setParameters(new String[] {scheduleForTestAdminOptionRequest.getAdminOptionVO().getAdminName()});
                scheduleForTestAdminOptionResponse
                        .addMessage(infoMessage);
                AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
                scheduleForTestAdminOptionResponse
                        .setAdminOptionVO(adminOptionVO);
                Logger.logInfo("Admin Option Scheduled For Test Successfully");
            } else {
                ErrorMessage errorMessage = new ErrorMessage(
                        BusinessConstants.ADMIN_OPTION_SCHEDULED_FOR_TEST_FAILURE);
                errorMessage.setParameters(new String[] {scheduleForTestAdminOptionRequest.getAdminOptionVO().getAdminName()});
                scheduleForTestAdminOptionResponse
                        .addMessage(errorMessage);
            }
        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_SCHEDULED_FOR_TEST_FAILURE);
            errorMessage.setParameters(new String[] {scheduleForTestAdminOptionRequest.getAdminOptionVO().getAdminName()});
            scheduleForTestAdminOptionResponse
                    .addMessage(errorMessage);
            scheduleForTestAdminOptionResponse
                    .setScheduleForTestErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(scheduleForTestAdminOptionRequest);
            String logMessage = "Failed while ScheduleForTestAdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
            
        }

        return scheduleForTestAdminOptionResponse;
    }


    /**
     * Method for Publish Admin Option
     * 
     * @param publishAdminOptionRequest
     * @return WPDResponse publishAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            PublishAdminOptionRequest publishAdminOptionRequest)
            throws ServiceException {

        Logger.logInfo("Inside Publish Admin Option Service");

        PublishAdminOptionResponse publishAdminOptionResponse = (PublishAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PUBLISH_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(publishAdminOptionRequest
                .getAdminOptionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            bom.publish(adminOptionBO, publishAdminOptionRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_PUBLISH);
            infoMessage.setParameters(new String[] {publishAdminOptionRequest.getAdminOptionVO().getAdminName()});
            publishAdminOptionResponse.addMessage(infoMessage);
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            publishAdminOptionResponse.setAdminOptionVO(adminOptionVO);
            Logger.logInfo("Admin Option Published Successfully");
        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_PUBLISH_FAILURE);
            errorMessage.setParameters(new String[] {publishAdminOptionRequest.getAdminOptionVO().getAdminName()});
            publishAdminOptionResponse.addMessage(errorMessage);
            publishAdminOptionResponse.setPublishErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(publishAdminOptionRequest);
            String logMessage = "Failed while publishAdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return publishAdminOptionResponse;
    }


    /**
     * Method for Test Pass Admin Option
     * 
     * @param testPassAdminOptionRequest
     * @return WPDResponse testPassAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            TestPassAdminOptionRequest testPassAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Test Pass Service");

        TestPassAdminOptionResponse testPassAdminOptionResponse = (TestPassAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_PASS_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(testPassAdminOptionRequest
                .getAdminOptionVO());

        try {
            BusinessObjectManager bom = this.getBusinessObjectManager();
            bom.testPass(adminOptionBO, testPassAdminOptionRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_PASS);
            infoMessage.setParameters(new String[] {testPassAdminOptionRequest.getAdminOptionVO().getAdminName()});
            testPassAdminOptionResponse.addMessage(infoMessage);
            Logger.logInfo("Admin Option Test Pass Done");
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            testPassAdminOptionResponse.setAdminOptionVO(adminOptionVO);

        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_PASS_FAILURE);
            errorMessage.setParameters(new String[] {testPassAdminOptionRequest.getAdminOptionVO().getAdminName()});
            testPassAdminOptionResponse.addMessage(errorMessage);
            testPassAdminOptionResponse.setTestPassErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(testPassAdminOptionRequest);
            String logMessage = "Failed while testPass AdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return testPassAdminOptionResponse;
    }


    /**
     * Method for Test Pass Admin Option
     * 
     * @param approveAdminOptionRequest
     * @return WPDResponse approveAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            ApproveAdminOptionRequest approveAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Approve Service");

        ApproveAdminOptionResponse approveAdminOptionResponse = (ApproveAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.APPROVE_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(approveAdminOptionRequest
                .getAdminOptionVO());

        try {
            BusinessObjectManager bom = this.getBusinessObjectManager();
            bom.approve(adminOptionBO, approveAdminOptionRequest.getUser());
            bom.publish(adminOptionBO, approveAdminOptionRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_APPROVE);
            infoMessage.setParameters(new String[] {approveAdminOptionRequest.getAdminOptionVO().getAdminName()});
            approveAdminOptionResponse.addMessage(infoMessage);
            Logger.logInfo("Admin Option Approved");
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            approveAdminOptionResponse.setAdminOptionVO(adminOptionVO);

        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_PASS_FAILURE);
            errorMessage.setParameters(new String[] {approveAdminOptionRequest.getAdminOptionVO().getAdminName()});
            approveAdminOptionResponse.addMessage(errorMessage);
            approveAdminOptionResponse.setApproveErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(approveAdminOptionRequest);
            String logMessage = "Failed while approve AdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return approveAdminOptionResponse;
    }
    
    /**
     * Method for Test Pass Admin Option
     * 
     * @param approveAdminOptionRequest
     * @return WPDResponse approveAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
    		AdminOptionUnlockRequest adminOptionUnlockRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Approve Service");

        AdminOptionUnlockResponse unlockAdminOptionResponse = (AdminOptionUnlockResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.UNLOCK_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(adminOptionUnlockRequest
                .getAdminOptionVO());

        try {
            BusinessObjectManager bom = this.getBusinessObjectManager();
           
            bom.unlock(adminOptionBO, adminOptionUnlockRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_UNLOCKED);
            infoMessage.setParameters(new String[] {adminOptionUnlockRequest.getAdminOptionVO().getAdminName()});
            unlockAdminOptionResponse.addMessage(infoMessage);
            Logger.logInfo("Admin Option Unlocked");
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            unlockAdminOptionResponse.setAdminOptionVO(adminOptionVO);

        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_PASS_FAILURE);
            errorMessage.setParameters(new String[] {adminOptionUnlockRequest.getAdminOptionVO().getAdminName()});
            unlockAdminOptionResponse.addMessage(errorMessage);
           // approveAdminOptionResponse.setApproveErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(adminOptionUnlockRequest);
            String logMessage = "Failed while approve AdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return unlockAdminOptionResponse;
    }


    /**
     * Method for Test Pass Admin Option
     * 
     * @param rejectAdminOptionRequest
     * @return WPDResponse rejectAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RejectAdminOptionRequest rejectAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Reject Service");

        RejectAdminOptionResponse rejectAdminOptionResponse = (RejectAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.REJECT_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(rejectAdminOptionRequest
                .getAdminOptionVO());

        try {
            BusinessObjectManager bom = this.getBusinessObjectManager();
            bom.reject(adminOptionBO, rejectAdminOptionRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_REJECT_SUCCESS);
            infoMessage.setParameters(new String[] {rejectAdminOptionRequest.getAdminOptionVO().getAdminName()});
            rejectAdminOptionResponse.addMessage(infoMessage);
            Logger.logInfo("Admin Option Rejected");
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            rejectAdminOptionResponse.setAdminOptionVO(adminOptionVO);

        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_PASS_FAILURE);
            errorMessage.setParameters(new String[] {rejectAdminOptionRequest.getAdminOptionVO().getAdminName()});
            rejectAdminOptionResponse.addMessage(errorMessage);
            rejectAdminOptionResponse.setRejectErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(rejectAdminOptionRequest);
            String logMessage = "Failed while reject AdminOption";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return rejectAdminOptionResponse;
    }


    /**
     * Method for Test Fail Service
     * 
     * @param testFailAdminOptionRequest
     * @return WPDResponse testFailAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            TestFailAdminOptionRequest testFailAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Admin Option Test Fail Service");

        TestFailAdminOptionResponse testFailAdminOptionResponse = (TestFailAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_FAIL_ADMIN_OPTION_RESPONSE);

        AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(testFailAdminOptionRequest
                .getAdminOptionVO());

        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            bom.testFail(adminOptionBO, testFailAdminOptionRequest.getUser());
            InformationalMessage infoMessage = new InformationalMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_FAIL_SUCCESS);
            infoMessage.setParameters(new String[] {testFailAdminOptionRequest.getAdminOptionVO().getAdminName()});
            testFailAdminOptionResponse.addMessage(infoMessage);
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);
            testFailAdminOptionResponse.setAdminOptionVO(adminOptionVO);
            Logger.logInfo("Admin Option Test Fail Done");
        } catch (WPDException ex) {
            ErrorMessage errorMessage = new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_TEST_FAIL_FAILURE);
            errorMessage.setParameters(new String[] {testFailAdminOptionRequest.getAdminOptionVO().getAdminName()});
            testFailAdminOptionResponse.addMessage(errorMessage);
            testFailAdminOptionResponse.setTestFailErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(testFailAdminOptionRequest);
            String logMessage = " AdminOption TestFail Failed";
            throw new ServiceException(logMessage, logParameters, ex);
            
            
        }

        return testFailAdminOptionResponse;
    }


    /**
     * Copying the contents of a AdminOption Business Object to AdminOption
     * Value Object.
     * 
     * @param adminOptionBO
     * @return adminOptionVO
     */
    private AdminOptionVO copyValueObjectFromBusinessObject(
            AdminOptionBO adminOptionBO) {

        List associatedQuestionsList = null;

        AdminOptionVO adminOptionVO = new AdminOptionVO();

        adminOptionVO.setAdminOptionId(adminOptionBO.getAdminOptionId());
        adminOptionVO.setAdminName(adminOptionBO.getAdminName());
        adminOptionVO.setQualifierId(adminOptionBO.getQualifierId());
        adminOptionVO.setReferenceId(adminOptionBO.getReferenceId());
        adminOptionVO.setTermId(adminOptionBO.getTermId());
        adminOptionVO.setQualifierDesc(adminOptionBO.getQualifierDesc());
        adminOptionVO.setReferenceDesc(adminOptionBO.getReferenceDesc());
        adminOptionVO.setTermDesc(adminOptionBO.getTermDesc());
        adminOptionVO.setCreatedUser(adminOptionBO.getCreatedUser());
        adminOptionVO.setCreatedTimestamp(adminOptionBO.getCreatedTimestamp());
        adminOptionVO.setLastUpdatedUser(adminOptionBO.getLastUpdatedUser());
        adminOptionVO.setLastUpdatedTimestamp(adminOptionBO
                .getLastUpdatedTimestamp());
        adminOptionVO.setVersion(adminOptionBO.getVersion());
        adminOptionVO.setStatus(adminOptionBO.getStatus());
        adminOptionVO.setStateObject(adminOptionBO.getState());
        adminOptionVO.setStateValue(adminOptionBO.getStateValue());

        List questionsList = adminOptionBO.getAssociatedQuestionsList();

        if (null != questionsList) {
            AssociatedQuestionBO associatedQuestionBO;
            AssociatedQuestionVO tempAssociatedQuestionVO;
            associatedQuestionsList = new ArrayList(questionsList.size());
            if(null != associatedQuestionsList){
                Iterator questionsListIterator = questionsList.iterator();
                while (questionsListIterator.hasNext()) {
                    tempAssociatedQuestionVO = new AssociatedQuestionVO();
                    associatedQuestionBO = (AssociatedQuestionBO) questionsListIterator
                            .next();
                    tempAssociatedQuestionVO.setAdminOptionId(associatedQuestionBO
                            .getAdminOptionId());
                    tempAssociatedQuestionVO
                            .setAssociatedQuestionId(associatedQuestionBO
                                    .getAssociatedQuestionId());
                    tempAssociatedQuestionVO.setSeqNumber(associatedQuestionBO
                            .getSeqNumber());

                    tempAssociatedQuestionVO.setReferenceId(associatedQuestionBO
                            .getReferenceId());

                    associatedQuestionsList.add(tempAssociatedQuestionVO);
                }
            }
        }
        adminOptionVO.setAssociatedQuestionsList(associatedQuestionsList);
        return adminOptionVO;
    }


    /**
     * Copying the contents of a AdminOption Business Object to AdminOption
     * Value Object.
     * 
     * @param associatedQuestionBO
     * @return AssociatedQuestionVO
     */
//    private AssociatedQuestionVO copyValueObjectFromBusinessObject(
//            AssociatedQuestionBO associatedQuestionBO) {
//        AssociatedQuestionVO associatedQuestionVO = new AssociatedQuestionVO();
//        associatedQuestionVO.setAdminOptionId(associatedQuestionBO
//                .getAdminOptionId());
//        associatedQuestionVO.setAssociatedQuestionId(associatedQuestionBO
//                .getAssociatedQuestionId());
//        associatedQuestionVO.setSeqNumber(associatedQuestionBO.getSeqNumber());
//
//        return associatedQuestionVO;
//    }


    /**
     * Copying the contents of a AdminOption Value Object to AdminOption
     * Business Object.
     * 
     * @param adminOptionVO
     * @return adminOptionBO
     */
    private AdminOptionBO copyBusinessObjectFromValueObject(
            AdminOptionVO adminOptionVO) {

        List associatedQuestionsList = null;

        //Creating new AdminOptionBO
        AdminOptionBO adminOptionBO = new AdminOptionBO();

        //Copying the admin option details from VO to BO
        adminOptionBO.setAdminOptionId(adminOptionVO.getAdminOptionId());
        /*if(adminOptionBO.getVersion() <=1){
        	adminOptionBO.setAdminOptionParentSysId(adminOptionVO.getAdminOptionParentSysId());
        }*/
        adminOptionBO.setAdminOptionParentSysId(adminOptionVO.getAdminOptionParentSysId());
        adminOptionBO.setAdminName(adminOptionVO.getAdminName().toUpperCase());
        adminOptionBO.setQualifierId(adminOptionVO.getQualifierId());
        adminOptionBO.setReferenceId(adminOptionVO.getReferenceId());
        adminOptionBO.setTermId(adminOptionVO.getTermId());
        adminOptionBO.setQualifierDesc(adminOptionVO.getQualifierDesc());
        adminOptionBO.setReferenceDesc(adminOptionVO.getReferenceDesc());
        adminOptionBO.setTermDesc(adminOptionVO.getTermDesc());
        adminOptionBO.setVersion(adminOptionVO.getVersion());
        adminOptionBO.setStatus(adminOptionVO.getStatus());
        adminOptionBO.setStateValue(adminOptionVO.getStateValue());
        adminOptionBO.setCreatedUser(adminOptionVO.getCreatedUser());
        adminOptionBO.setCreatedTimestamp(adminOptionVO.getCreatedTimestamp());
        adminOptionBO.setLastUpdatedUser(adminOptionVO.getLastUpdatedUser());
        adminOptionBO.setLastUpdatedTimestamp(adminOptionVO
                .getLastUpdatedTimestamp());

        List questionsList = adminOptionVO.getAssociatedQuestionsList();

        if (null != questionsList) {
            AssociatedQuestionBO tempAssociatedQuestionBO;
            AssociatedQuestionVO associatedQuestionVO;
            associatedQuestionsList = new ArrayList(questionsList.size());
            if(null != associatedQuestionsList){
                Iterator questionsListIterator = questionsList.iterator();
                while (questionsListIterator.hasNext()) {
                    tempAssociatedQuestionBO = new AssociatedQuestionBO();
                    associatedQuestionVO = (AssociatedQuestionVO) questionsListIterator
                            .next();
                    tempAssociatedQuestionBO.setAdminOptionId(associatedQuestionVO
                            .getAdminOptionId());
                    tempAssociatedQuestionBO
                            .setAssociatedQuestionId(associatedQuestionVO
                                    .getAssociatedQuestionId());
                    tempAssociatedQuestionBO.setSeqNumber(associatedQuestionVO
                            .getSeqNumber());
                    if("null".equalsIgnoreCase(associatedQuestionVO
                            .getReferenceId()))
                    	tempAssociatedQuestionBO.setReferenceId(null);	
                    else
                    tempAssociatedQuestionBO.setReferenceId(associatedQuestionVO
                            .getReferenceId());

                    associatedQuestionsList.add(tempAssociatedQuestionBO);
                }
            }
        }
        adminOptionBO.setAssociatedQuestionsList(associatedQuestionsList);

        return adminOptionBO;
    }


    /**
     * Copying the contents of a AdminOption Value Object to AdminOption
     * Business Object.
     * 
     * @param associatedQuestionVO
     * @return associatedQuestionBO
     */
    private AssociatedQuestionBO copyBusinessObjectFromValueObject(
            AssociatedQuestionVO associatedQuestionVO) {

        //Creating new AssociatedQuestionBO
        AssociatedQuestionBO associatedQuestionBO = new AssociatedQuestionBO();

        //Copying the associated question details from VO to BO
        associatedQuestionBO.setAdminOptionId(associatedQuestionVO
                .getAdminOptionId());
        associatedQuestionBO.setAssociatedQuestionId(associatedQuestionVO
                .getAssociatedQuestionId());
        associatedQuestionBO.setSeqNumber(associatedQuestionVO.getSeqNumber());
        associatedQuestionBO.setReferenceId(associatedQuestionVO
                .getReferenceId());
      
       List questionsList = associatedQuestionVO.getAssociatedQuestionsList();

       List associatedQuestionsList = null;
       if (null != questionsList) {
           AssociatedQuestionBO tempAssociatedQuestionBO;
           associatedQuestionsList = new ArrayList(questionsList.size());
           if(null != associatedQuestionsList){
            Iterator questionsListIterator = questionsList.iterator();
            while (questionsListIterator.hasNext()) {
                tempAssociatedQuestionBO = new AssociatedQuestionBO();
                associatedQuestionVO = (AssociatedQuestionVO) questionsListIterator
                        .next();
                tempAssociatedQuestionBO.setAdminOptionId(associatedQuestionVO
                        .getAdminOptionId());
                tempAssociatedQuestionBO
                        .setAssociatedQuestionId(associatedQuestionVO
                                .getAssociatedQuestionId());
                tempAssociatedQuestionBO.setSeqNumber(associatedQuestionVO
                        .getSeqNumber());
                tempAssociatedQuestionBO.setReferenceId(associatedQuestionVO
                        .getReferenceId());
                //modified for solving performance issue on 18th Dec 2007
                tempAssociatedQuestionBO.setModified(associatedQuestionVO.isModified());
                //modification ends

                associatedQuestionsList.add(tempAssociatedQuestionBO);
            }
           }
       }   
        associatedQuestionBO.setAssociatedQuestionList(associatedQuestionsList);

        return associatedQuestionBO;
    }


    /**
     * Method to perform search admin option
     * 
     * @param searchAdminOptionRequest
     * @return WPDResponse searchAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SearchAdminOptionRequest searchAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Search Service");
        //Getting the response object from the Response Factory
        SearchAdminOptionResponse searchAdminOptionResponse = (SearchAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_ADMIN_OPTION_RESPONSE);

        try {
            int searchResultCount = 0;
            //List adminOptionSearchResultList = null;
            List errorList = new ArrayList(3);
            LocateResults locateResults = null;
            AdminOptionLocateCriteria adminOptionLocateCriteria = getAdminOptionSearchObject(searchAdminOptionRequest);

            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();

            locateResults = businessObjectManager.locate(
                    adminOptionLocateCriteria, searchAdminOptionRequest
                            .getUser());
            searchResultCount = locateResults.getTotalResultsCount();
            List adminOptionSearchResultList = locateResults.getLocateResults();
            if (adminOptionSearchResultList.size() > 0) {
                searchAdminOptionResponse
                        .setAdminOptionSearchResultList(adminOptionSearchResultList);

                if (searchResultCount > searchAdminOptionRequest
                        .getAdminOptionVO().getMaxLocateResultSize()) {
                    errorList.add(new InformationalMessage(
                            BusinessConstants.SEARCH_RESULT_EXCEEDS));
                    searchAdminOptionResponse.setMessages(errorList);
                }
                Logger.logInfo("Admin Option Search Successful");

            } else if (adminOptionSearchResultList.size() == 0
                    && searchResultCount == 0) {
//                errorList.add(new InformationalMessage(
//                        BusinessConstants.ADMIN_OPTION_SEARCH_RESULT_NOTFOUND));
            	  errorList.add(new InformationalMessage(
                        WebConstants.SEARCH_RESULTS_ZERO));
                searchAdminOptionResponse.setMessages(errorList);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(searchAdminOptionRequest);
            String logMessage = "Failed while processing SearchAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return searchAdminOptionResponse;
    }


    /**
     * Method to perform search admin option Business Object.
     * 
     * @param searchAdminOptionRequest
     * @return AdminOptionLocateCriteria adminOptionLocateCriteria
     */
    private AdminOptionLocateCriteria getAdminOptionSearchObject(
            SearchAdminOptionRequest searchAdminOptionRequest) {
        AdminOptionLocateCriteria adminOptionLocateCriteria = new AdminOptionLocateCriteria();
        adminOptionLocateCriteria.setAdminNameCriteria(BusinessUtil.escpeSpecialCharacters(searchAdminOptionRequest
                .getAdminOptionVO().getAdminName()));
        adminOptionLocateCriteria
                .setBenefitQualifierList(searchAdminOptionRequest
                        .getAdminOptionVO().getBenefitQualifierList());
        adminOptionLocateCriteria.setBenefitTermList(searchAdminOptionRequest
                .getAdminOptionVO().getBenefitTermList());
        adminOptionLocateCriteria.setMaximumResultSize(searchAdminOptionRequest
                .getAdminOptionVO().getMaxLocateResultSize());
        return adminOptionLocateCriteria;
    }


    /**
     * Method to perform delete admin option Business Object.
     * 
     * @param deleteAdminOptionRequest
     * @return WPDResponse adminOptionDeleteResponse
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteAdminOptionRequest deleteAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Delete Service");
        //Getting response object from the Response Factory
        DeleteAdminOptionResponse adminOptionDeleteResponse = (DeleteAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_ADMIN_OPTION_RESPONSE);
        try {
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(deleteAdminOptionRequest
                    .getAdminOptionVO());
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            if (businessObjectManager.delete(adminOptionBO,
                    deleteAdminOptionRequest.getUser())) {
                adminOptionDeleteResponse.setDeleteFlag(true);
                if (null == adminOptionBO.getStatus()) {
                    InformationalMessage infoMessage = new InformationalMessage(
                            BusinessConstants.ADMIN_OPTION_DELETE_SUCCESS);
                    infoMessage.setParameters(new String[] {deleteAdminOptionRequest.getAdminOptionVO().getAdminName()});
                    adminOptionDeleteResponse
                            .addMessage(infoMessage);
                } else if (adminOptionBO.getStatus().equals(
                        "MARKED_FOR_DELETION")) {
                    InformationalMessage infoMessage = new InformationalMessage(
                            BusinessConstants.ADMIN_OPTION_MARK_FOR_DELETE_SUCCESS);
                    infoMessage.setParameters(new String[] {deleteAdminOptionRequest.getAdminOptionVO().getAdminName()});
                    adminOptionDeleteResponse
                            .addMessage(infoMessage);
                }

                Logger.logInfo("Inside Admin Option Deleted Successfully");
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteAdminOptionRequest);
            String logMessage = "Failed while processing DeleteAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return adminOptionDeleteResponse;
    }

    
    /**
     * Method to perform delete all versions of an Admin Option Business Object.
     * 
     * @param deleteAllAdminOptionRequest
     * @return WPDResponse deleteAllAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteAllAdminOptionRequest deleteAllAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Delete All Versions Service");
        
        //Getting response object from the Response Factory
        DeleteAllAdminOptionResponse deleteAllAdminOptionResponse = (DeleteAllAdminOptionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_ALL_ADMIN_OPTION_RESPONSE);
        
        try {
            //Creating Admin Option Business Object by coping the details from VO
        	AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(deleteAllAdminOptionRequest
                    .getAdminOptionVO());
        	
        	//Creating the object of BusinessObjectManager class 
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            
            //Invoking the method of businessObjectManager to delete all the versions of the Admin Option
            if (businessObjectManager.deleteAll(adminOptionBO,
                    deleteAllAdminOptionRequest.getUser())) {
            	
            	//If delete is sucessfull, setting the flag as true in the reponse object
                deleteAllAdminOptionResponse.setDeleteFlag(true);
                
                //Setting the messgage in the response
                InformationalMessage infoMessage = new InformationalMessage(
                        BusinessConstants.ADMIN_OPTION_DELETE_ALL_SUCCESS);
                infoMessage.setParameters(new String[] {deleteAllAdminOptionRequest.getAdminOptionVO().getAdminName()});
                deleteAllAdminOptionResponse
                        .addMessage(infoMessage);
            
                Logger.logInfo("Admin Option Deleted All Successfully");
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteAllAdminOptionRequest);
            String logMessage = "Failed while processing DeleteAllAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return deleteAllAdminOptionResponse;
    }
    

    /**
     * Method to view all versions of admin option selected.
     * 
     * @param adminOptionViewRequest
     * @return WPDResponse adminOptionViewResponse
     * @throws ServiceException
     */
    public WPDResponse execute(AdminOptionViewRequest adminOptionViewRequest)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option View Service");
        AdminOptionViewResponse adminOptionViewResponse = (AdminOptionViewResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.ADMIN_OPTION_VIEW_RESPONSE);
        try {
            List adminOptionResultsList = null;
            LocateResults searchResults = null;
            BusinessObjectManager bom = this.getBusinessObjectManager();
            if (ADMINOPTION_VIEW_ALL.equals(adminOptionViewRequest.getAction())) {
                AdminOptionLocateCriteria adminOptionLocateCriteria = new AdminOptionLocateCriteria();
                adminOptionLocateCriteria
                        .setAdminNameCriteria(adminOptionViewRequest
                                .getAdminOptionVO().getAdminName());
                adminOptionLocateCriteria.setAction(adminOptionViewRequest
                        .getAction());
            //    adminOptionLocateCriteria.setAdminOptionParentSysId(adminOptionViewRequest.getAdminOptionVO().getAdminOptionId());
                searchResults = bom.locate(adminOptionLocateCriteria,
                        adminOptionViewRequest.getUser());
                adminOptionResultsList = searchResults.getLocateResults();
            }
            adminOptionViewResponse
                    .setAdminOptionResultList(adminOptionResultsList);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(adminOptionViewRequest);
            String logMessage = "Failed while processing AdminOptionViewRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return adminOptionViewResponse;
    }

    /**
     * Method to get Business Object Manager
     * 
     * @return BusinessObjectManager
     */
    private BusinessObjectManager getBusinessObjectManager() {
        BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                .getFactory(ObjectFactory.BOM);
        BusinessObjectManager bom = bomf.getBusinessObjectManager();
        return bom;
    }


    /**
     * Method to view Admin Option.
     * 
     * @param viewAdminOptionRequest
     * @return WPDResponse viewAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(ViewAdminOptionRequest viewAdminOptionRequest)
            throws ServiceException {
        Logger.logInfo("Inside View Admin Option  Service");
        //Getting the response from the response factory
        ViewAdminOptionResponse viewAdminOptionResponse = new ViewAdminOptionResponse();
        try {
            //Creating AdminOptionBO object by coping the values from the
            // AdminOptionVO object
            AdminOptionBO adminOptionBO = copyBusinessObjectFromValueObject(viewAdminOptionRequest
                    .getAdminOptionVO());

            //Creating BusinessObjectManager
            BusinessObjectManager businessObjectManager = this
                    .getBusinessObjectManager();
            adminOptionBO = (AdminOptionBO) businessObjectManager.retrieve(
                    adminOptionBO, viewAdminOptionRequest.getUser());

            //Creating AdminOptionVO object by coping the values from the
            // AdminOptionBO object
            AdminOptionVO adminOptionVO = copyValueObjectFromBusinessObject(adminOptionBO);

            //Setting the new AdminOptionVO object to the
            // retrieveAdminOptionResponse
            viewAdminOptionResponse.setAdminOptionVO(adminOptionVO);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(viewAdminOptionRequest);
            String logMessage = "Failed while processing viewAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return viewAdminOptionResponse;
    }
    
    
    /**
     * Method to delete attached question from the questionnaire of an adminoption. Multiple delete functionality is 
     * implemented.
     * 
     * @param deleteQuestionnaireRequest
     * @return WPDResponse viewAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteQuestionnaireRequest deleteQuestionnaireRequest)
            throws ServiceException {
        Logger.logInfo("Inside Delete Questionnaire Service");
        String qustnrId = null;
        //Getting the response from the response factory
        DeleteQuestionnaireResponse deleteQuestionnaireResponse = new DeleteQuestionnaireResponse();
        try {
	        AdminOptionBO adminOptionBO = new AdminOptionBO();
	        List messagesList = null;
	        // Creating BusinessObjectManager
	        BusinessObjectManager businessObjectManager = this
	                .getBusinessObjectManager();
	        AdminOptionBusinessObjectBuilder builder = new AdminOptionBusinessObjectBuilder();
	        List DeleteQuestList = deleteQuestionnaireRequest.getQuestionnaireIds();
	        Iterator itr = DeleteQuestList.iterator();
	        List remainingQuesList = null;
	        while(itr.hasNext()){
	            qustnrId =(String) itr.next();
	            remainingQuesList = builder.getRemainingQuestions(qustnrId,deleteQuestionnaireRequest.isRootQuestion());
	        }
	        if(null != remainingQuesList && !remainingQuesList.isEmpty()){
		        SequenceUtil seq = new SequenceUtil();
		        seq.registerObjects(remainingQuesList,"questionnaireId","sequence");
		        for(int i = 0;i < remainingQuesList.size();i++){
		            AssociatedQuestionnaireBO associatedQuestionnaireBO = 
		                (AssociatedQuestionnaireBO)remainingQuesList.get(i);
		            if(associatedQuestionnaireBO.getQuestionnaireId() == Integer.parseInt(qustnrId))
		                remainingQuesList.remove(associatedQuestionnaireBO);
		        }
		        List reOrderedList = seq.reOrderObjects(remainingQuesList);
		        if(null != reOrderedList && !reOrderedList.isEmpty()){
			        Iterator itrReOrder =  reOrderedList.iterator();
			        while (itrReOrder.hasNext()){
			            builder.updateReOrderedQuestions((AssociatedQuestionnaireBO)itrReOrder.next(),deleteQuestionnaireRequest.getUser().getUserId());
			        }
		        }
	        }
	        AssociatedQuestionnaireBO associatedQuestionnaireBO = new AssociatedQuestionnaireBO();
	        associatedQuestionnaireBO.setAdminOptionId(deleteQuestionnaireRequest.getAdminoptionId());
	        associatedQuestionnaireBO.setQuestionnaireList(deleteQuestionnaireRequest.getQuestionnaireIds());
	        adminOptionBO.setAdminOptionId(deleteQuestionnaireRequest.getAdminoptionId());
	        adminOptionBO.setAdminName(deleteQuestionnaireRequest.getAdminName());
	        adminOptionBO.setVersion(deleteQuestionnaireRequest.getVersion());
       
    		messagesList = new ArrayList();
        	if(businessObjectManager.delete(associatedQuestionnaireBO,adminOptionBO,deleteQuestionnaireRequest.getUser())){
        		messagesList.add(new InformationalMessage(BusinessConstants.DELETE_QUESTIONNAIRE_SUCCESS));
        	}
        	else{
        		messagesList.add(new ErrorMessage(BusinessConstants.DELETE_QUESTIONNAIRE_FAILURE));
        	}
            deleteQuestionnaireResponse.setMessages(messagesList);
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteQuestionnaireRequest);
            String logMessage = "Failed while processing DeleteQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }catch (AdapterException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteQuestionnaireRequest);
            String logMessage = "Failed while processing DeleteQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("Exiting Delete Questionnaire  Service");
        return deleteQuestionnaireResponse;
    }
    
    /**
     * Method to retrieve the parent questionnaire details for edit.
     * 
     * @param viewAdminOptionRequest
     * @return WPDResponse viewAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveRootQuestionnaireRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Retrieve Root Questionnaire Service");
        
        RetrieveRootQuestionnaireResponse response = null;
        BusinessObjectManager businessObjectManager = null;
        List rootQuestionResultsList = null;
        LocateResults searchResults = null;
        RootQuestionnaireLocateCriteria locateCriteria = null;
        
        response = (RetrieveRootQuestionnaireResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_ROOT_QUESTIONNAIRE_RESPONSE);
        try {
            businessObjectManager = this.getBusinessObjectManager();
            locateCriteria = new RootQuestionnaireLocateCriteria();
            locateCriteria.setQuestionnaireHierarchySystemId
						(request.getQuestionnaireHierarchySystemId());
            searchResults = businessObjectManager.locate
						(locateCriteria, request.getUser());
            rootQuestionResultsList = searchResults.getLocateResults();
            if(null != rootQuestionResultsList && 
            		!rootQuestionResultsList.isEmpty()){
            	response.setRootQuestionnaireBO(
            			(RootQuestionnaireBO)rootQuestionResultsList.get(0));
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveRootQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("Exiting Retrieve Questionnaire  Service");
        return response;
    }
    
    /**
     * Method to retrieve the parent questionnaire details for edit.
     * 
     * @param viewAdminOptionRequest
     * @return WPDResponse viewAdminOptionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveChildQuestionnaireRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Retrieve Child Questionnaire Service");
        
        RetrieveChildQuestionnaireResponse response = null;
        BusinessObjectManager businessObjectManager = null;
        List childQuestionResultsList = null;
        LocateResults searchResults = null;
        ChildQuestionnaireLocateCriteria locateCriteria = null;
        
        response = (RetrieveChildQuestionnaireResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_CHILD_QUESTIONNAIRE_RESPONSE);
        try {
            businessObjectManager = this.getBusinessObjectManager();
            locateCriteria = new ChildQuestionnaireLocateCriteria();
            locateCriteria.setParentQuestionnaireSysId
						(request.getParentQuestionnaireSysId());
            locateCriteria.setAction(request.getAction());
            locateCriteria.setAdminOptionId(request.getAdminOptionId());
            searchResults = businessObjectManager.locate
						(locateCriteria, request.getUser());
            childQuestionResultsList = searchResults.getLocateResults();
            if(null != childQuestionResultsList && 
            		!childQuestionResultsList.isEmpty()){
            	response.setChildQuestionnaires(childQuestionResultsList);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing RetrieveChildQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return response;
    }
    
    /**
     * Method to persist/update the child questionnaires.
     * 
     * @param request
     * @return WPDResponse 
     * @throws ServiceException
     */
    public WPDResponse execute(
            PersistChildQuestionnaireRequest request)
            throws ServiceException {
        Logger.logInfo("Inside Admin Option Child Questionnaire Persist/Update Service");
        
        BusinessObjectManager businessObjectManager = null;
        AdminOptionBO adminOptionBO = null;
        PersistChildQuestionnaireResponse response = null;
        ChildQuestionnaireBO childQuestionnaireBO = null;
        List messageList = null;
        
        response = (PersistChildQuestionnaireResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PERSIST_CHILD_QUESTIONNAIRE_RESPONSE);
        
        try {
            businessObjectManager = this.getBusinessObjectManager();
            // set the details in the main object
            adminOptionBO = new AdminOptionBO();
            adminOptionBO.setAdminName(request.getAdminOptionName());
            adminOptionBO.setVersion(request.getAdminOptionVersion());
            adminOptionBO.setAdminOptionId(request.getAdminOptionId());
            // set the list of bo to the sub object
            childQuestionnaireBO = new ChildQuestionnaireBO();
            childQuestionnaireBO.setChildQuestionnaires
						(request.getChildQuestionnaires());
            childQuestionnaireBO.setChildsToDeleted(
            		request.getQuestionnairesToDeleted());
            childQuestionnaireBO.setParentQuestionnaireId(
            		request.getParentQuestionnaireId());
            childQuestionnaireBO.setDeleteFlag(request.isDeleteFlag());
            businessObjectManager.persist(
                		childQuestionnaireBO, adminOptionBO,
                        request.getUser(), request.isInsertFlag());
            if(request.isInsertFlag()){
            	messageList = new ArrayList();
            	messageList.add(new InformationalMessage("child.questions.added"));
            	response.setMessages(messageList);
            }else{
            	messageList = new ArrayList();
            	messageList.add(new InformationalMessage("child.questions.updated"));
            	response.setMessages(messageList);
            }
            if(request.isDeleteFlag()){
            	messageList = new ArrayList();
            	messageList.add(new InformationalMessage("child.questions.deleted"));
            	response.setMessages(messageList);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing PersistChildQuestionnaireRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("Exiting Admin Option Child Questionnaire Persist/Update Service");
        return response;
    }    
    
   
    public WPDResponse execute(SaveAdminOptionQuestionnaireRequest saveAdminOptionQuestionnaireRequest)
    throws ServiceException {
    	Logger.logInfo("Inside Admin Option Save Questionnaire Parent Reqd  Service");
    	//Getting the response from the response factory
    	SaveAdminOptionQuestionnaireResponse saveAdminOptionQuestionnaireResponse = (SaveAdminOptionQuestionnaireResponse) WPDResponseFactory
        .getResponse(WPDResponseFactory.QUESTIONNAIRE_PR_RESPONSE);
    	
    	AdminOptionBO adminOptionBO = new AdminOptionBO();
        adminOptionBO.setAdminName(saveAdminOptionQuestionnaireRequest.getAdminOptionName());
        adminOptionBO.setVersion(saveAdminOptionQuestionnaireRequest.getAdminOptionVersion());
        adminOptionBO.setAdminOptionId(saveAdminOptionQuestionnaireRequest.getAdminOptionId());
    	List childQuestionsList = null;
    	
    	AdminOptionBusinessObjectBuilder builder = new AdminOptionBusinessObjectBuilder();
    	Audit audit = getAudit(saveAdminOptionQuestionnaireRequest.getUser());
    	try {  
			Map questionaireIDPR = saveAdminOptionQuestionnaireRequest.getQuestionnaireIdPRMap();
			List questionaireIdList = new ArrayList();
			Set listOfKeys = questionaireIDPR.keySet();
			Iterator iterator = listOfKeys.iterator();
			while(iterator.hasNext()){
				Integer questionnaireId = (Integer)iterator.next();
				questionaireIdList.add(questionnaireId);
			}
			
			childQuestionsList = builder.getQuestionnaireForPR(questionaireIdList);
			Iterator childQuestionsIterator = childQuestionsList.iterator();
			while(childQuestionsIterator.hasNext()){
				ChildQuestionnaireBO childQuestionnaireBO = (ChildQuestionnaireBO)childQuestionsIterator.next();
				childQuestionnaireBO.setParentRequired((String)questionaireIDPR.get
						(new Integer(childQuestionnaireBO.getQuestionnaireHierarchySystemId())));
			}
			ChildQuestionnaireBO childQuestionnaireBO  = new ChildQuestionnaireBO ();    			
    		childQuestionnaireBO.setChildQuestionnaires(childQuestionsList);
			builder.persistParentRequired(childQuestionnaireBO,adminOptionBO,audit,false);
    		    		
	        saveAdminOptionQuestionnaireResponse.addMessage(new InformationalMessage(
	                "updateadminoption.success.info"));
	        Logger.logInfo("Admin Option Updated Successfully");   
	
			} 
    	catch (WPDException ex) {
		    List logParameters = new ArrayList(2);
		    logParameters.add(saveAdminOptionQuestionnaireRequest);
		    String logMessage = "Failed while processing SaveAdminOptionQuestionnaireRequest";
		    throw new ServiceException(logMessage, logParameters, ex);
		}		
		return saveAdminOptionQuestionnaireResponse;
}
    
    private Audit getAudit(User user) {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}

}