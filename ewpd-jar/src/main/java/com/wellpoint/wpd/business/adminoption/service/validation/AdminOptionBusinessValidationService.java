/*
 * AdminOptionBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.adminoption.service.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.adminoption.builder.AdminOptionBusinessObjectBuilder;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionQuestionLocateCriteria;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO;
import com.wellpoint.wpd.common.adminoption.request.CheckInAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CreateAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.ScheduleForTestAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CreateAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.ScheduleForTestAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.adminoption.vo.AssociatedQuestionVO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * Business Validation class for Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionBusinessValidationService extends WPDService {

    /**
     * Overridden execute method of WPDService. Do the Business processing
     * corresponding to request and returns response.
     * 
     * @param request.
     * @return WPDResponse WPDRespnose.
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        return null;
    }


    /**
     * Validation method for CreateAdminOptionRequest.
     * 
     * @param saveAdminOptionQuestionRequest
     * @return WPDResponse CreateAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(
            SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest)
            throws ServiceException {
        SaveAdminOptionQuestionResponse saveAdminOptionQuestionResponse = new SaveAdminOptionQuestionResponse();
        
        AssociatedQuestionVO associatedQuestionVO = saveAdminOptionQuestionRequest.getAssociatedQuestionVO();
        
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
                    try{
    	                if (!isAdminOptionQuestionDuplicate(tempAssociatedQuestionBO)) {
    	                    associatedQuestionsList.add(tempAssociatedQuestionBO);
    	                }
                    } catch (WPDException ex) {
                        List logParameters = new ArrayList(2);
                        logParameters.add(saveAdminOptionQuestionRequest);
                        String logMessage = "Failed while processing CreateAdminOptionRequest";
                        throw new ServiceException(logMessage, logParameters, ex);
                    }
                }
                AssociatedQuestionVO associatedQuestionVO2 = new AssociatedQuestionVO();
                associatedQuestionVO2.setAssociatedQuestionsList(associatedQuestionsList);
                saveAdminOptionQuestionResponse.setAssociatedQuestionVO(associatedQuestionVO2);
            }
        }
        return saveAdminOptionQuestionResponse;
    }


    /**
     * Validation method for deleteAdminOptionRequest.
     * 
     * @param deleteAdminOptionQuestionRequest
     * @return WPDResponse deleteAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(
            DeleteAdminOptionQuestionRequest deleteAdminOptionQuestionRequest)
            throws ServiceException {
        DeleteAdminOptionQuestionResponse deleteAdminOptionQuestionResponse = new DeleteAdminOptionQuestionResponse();
        AssociatedQuestionBO associatedQuestionBO = this
                .getAssociatedQuestionBusinessObject(deleteAdminOptionQuestionRequest
                        .getAssociatedQuestionVO());
        List messageList = new ArrayList(2);
        try {
            if (isAdminOptionQuestionInUse(associatedQuestionBO)) {
                messageList.add(new ErrorMessage(
                        BusinessConstants.ADMIN_OPTION_QN_CANNOT_BE_DELETED));
                deleteAdminOptionQuestionResponse.setErrorFlag(true);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteAdminOptionQuestionRequest);
            String logMessage = "Failed while processing DeleteAdminOptionQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        deleteAdminOptionQuestionResponse.setMessages(messageList);
        return deleteAdminOptionQuestionResponse;
    }


    /**
     * Validation method for duplicate AdminOption.
     * 
     * @param associatedQuestionBO
     * @return boolean
     * @throws WPDException
     */
    private static boolean isAdminOptionQuestionInUse(
            AssociatedQuestionBO associatedQuestionBO) throws WPDException {
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        if (adminOptionObjectBuilder
                .isAdminOptionQuestionInUse(associatedQuestionBO)) {
            return true;
        }
        return false;
    }


    /**
     * Validation method for deleteAdminOptionRequest.
     * 
     * @param deleteAdminOptionRequest
     * @return WPDResponse deleteAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteAdminOptionRequest deleteAdminOptionRequest)
            throws ServiceException {
        DeleteAdminOptionResponse deleteAdminOptionResponse = new DeleteAdminOptionResponse();
        AdminOptionBO adminOptionBO = this
                .getAdminOptionBusinessObject(deleteAdminOptionRequest
                        .getAdminOptionVO());
        List messageList = new ArrayList(2);
        try {
            if (isAdminOptionInUse(adminOptionBO)) {
                ErrorMessage errorMessage = new ErrorMessage(
                        BusinessConstants.ADMIN_OPTION_CANNOT_BE_DELETED);
                errorMessage.setParameters(new String[] {deleteAdminOptionRequest.getAdminOptionVO().getAdminName()});
                messageList.add(errorMessage);
                deleteAdminOptionResponse.setErrorFlag(true);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteAdminOptionRequest);
            String logMessage = "Failed while processing CreateAdminOptionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        deleteAdminOptionResponse.setMessages(messageList);
        return deleteAdminOptionResponse;
    }


    /**
     * Validation method for duplicate AdminOption.
     * 
     * @param adminOptionBO
     * @return boolean
     * @throws WPDException
     */
    private static boolean isAdminOptionInUse(AdminOptionBO adminOptionBO)
            throws WPDException {
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        if (adminOptionObjectBuilder.isAdminOptionInUse(adminOptionBO)) {
            return true;
        }
        return false;
    }


    /**
     * To set the values from VO to BO
     * 
     * @param associatedQuestionVO
     * @return AssociatedQuestionBO associatedQuestionBO
     */
    private AssociatedQuestionBO getAssociatedQuestionBusinessObject(
            AssociatedQuestionVO associatedQuestionVO) {
        AssociatedQuestionBO associatedQuestionBO = new AssociatedQuestionBO();
        associatedQuestionBO.setAdminOptionId(associatedQuestionVO
                .getAdminOptionId());
        associatedQuestionBO.setAssociatedQuestionId(associatedQuestionVO
                .getAssociatedQuestionId());
        return associatedQuestionBO;
    }


    /**
     * Validation method for duplicate AdminOption.
     * 
     * @param associatedQuestionBO
     * @return boolean
     * @throws WPDException
     */
    public static boolean isAdminOptionQuestionDuplicate(
            AssociatedQuestionBO associatedQuestionBO) throws WPDException {
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        associatedQuestionBO = adminOptionObjectBuilder
                .checkDuplicateAdminOptionQuestion(associatedQuestionBO);
        if (null == associatedQuestionBO) {
            return false;
        }
        return true;
    }


    /**
     * Validation method for CreateAdminOptionRequest.
     * 
     * @param createAdminOptionRequest
     * @return WPDResponse createAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(CreateAdminOptionRequest createAdminOptionRequest)
            throws ServiceException {
        CreateAdminOptionResponse createAdminOptionResponse = new CreateAdminOptionResponse();
        AdminOptionBO adminOptionBO = this
                .getAdminOptionBusinessObject(createAdminOptionRequest
                        .getAdminOptionVO());
        List messageList = new ArrayList(2);
        try {
            if (isAdminOptionDuplicate(adminOptionBO, createAdminOptionRequest
                    .getUser())) {
                messageList.add(new ErrorMessage(
                        BusinessConstants.ADMIN_OPTION_ALREADY_EXIST));
                createAdminOptionResponse.setErrorFlag(true);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(createAdminOptionRequest);
            String logMessage = "Failed while processing CreateAdminOptionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        createAdminOptionResponse.setMessages(messageList);
        return createAdminOptionResponse;
    }


    /**
     * Validation method for CheckInAdminOptionRequest.
     * 
     * @param checkInAdminOptionRequest
     * @return WPDResponse checkInAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(
            CheckInAdminOptionRequest checkInAdminOptionRequest)
            throws ServiceException {
        CheckInAdminOptionResponse checkInAdminOptionResponse = new CheckInAdminOptionResponse();
        AdminOptionBO adminOptionBO = this
                .getAdminOptionBusinessObject(checkInAdminOptionRequest
                        .getAdminOptionVO());
        List messageList = new ArrayList(2);
        try {
            if (!isValid(adminOptionBO, checkInAdminOptionRequest.getUser(),
                    messageList)) {
                checkInAdminOptionResponse.setErrorFlag(true);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(checkInAdminOptionRequest);
            String logMessage = "Failed while processing CheckInAdminOptionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        checkInAdminOptionResponse.setMessages(messageList);
        return checkInAdminOptionResponse;
    }


    /**
     * Validation method for ScheduleForTestAdminOptionRequest.
     * 
     * @param request
     * @return WPDResponse scheduleForTestAdminOptionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(ScheduleForTestAdminOptionRequest request)
            throws ServiceException {
        ScheduleForTestAdminOptionResponse scheduleForTestAdminOptionResponse = new ScheduleForTestAdminOptionResponse();
        AdminOptionBO adminOptionBO = this.getAdminOptionBusinessObject(request
                .getAdminOptionVO());
        List messageList = new ArrayList(2);
        try {
            if (!isAssociatedQuestionExists(adminOptionBO, request.getUser())) {
                messageList.add(new ErrorMessage(
                        BusinessConstants.ADMIN_OPTION_QN_NOT_EXIST));
                scheduleForTestAdminOptionResponse
                        .setScheduleForTestErrorFlag(true);
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(request);
            String logMessage = "Failed while processing ScheduleForTestAdminOptionResponse";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        scheduleForTestAdminOptionResponse.setMessages(messageList);
        return scheduleForTestAdminOptionResponse;
    }
   

    /**
     * Validation method for duplicate AdminOption.
     * 
     * @param adminOptionBO
     * @param user
     * @return boolean
     * @throws WPDException
     */
    private static boolean isAdminOptionDuplicate(AdminOptionBO adminOptionBO,
            User user) throws WPDException {
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        adminOptionBO = adminOptionObjectBuilder
                .retrieveAdminName(adminOptionBO, user);
        if (null == adminOptionBO) {
            return false;
        }
        return true;
    }


    /**
     * Validation method for checking associated questions exists for an Admin
     * Option.
     * 
     * @param adminOptionBO
     * @param user
     * @return boolean
     * @throws WPDException
     */
    private static boolean isAssociatedQuestionExists(
            AdminOptionBO adminOptionBO, User user) throws WPDException {
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria = new AdminOptionQuestionLocateCriteria();
        adminOptionQuestionLocateCriteria.setAdminOptionId(adminOptionBO
                .getAdminOptionId());
        LocateResults locateResults = adminOptionObjectBuilder.locate(
                adminOptionQuestionLocateCriteria, user);
        int searchResultCount = locateResults.getTotalResultsCount();
        List adminOptionSearchResultList = locateResults.getLocateResults();
        if (adminOptionSearchResultList.size() == 0 && searchResultCount == 0) {
            return false;
        }
        return true;
    }


    /**
     * Validation method for checking associated references exists for a
     * question for an Admin Option.
     * 
     * @param adminOptionBO
     * @param user
     * @return boolean
     * @throws WPDException
     */
//    private static boolean isReferenceExists(AdminOptionBO adminOptionBO,
//            User user) throws WPDException {
//        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
//        AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria = new AdminOptionQuestionLocateCriteria();
//        adminOptionQuestionLocateCriteria.setAdminOptionId(adminOptionBO
//                .getAdminOptionId());
//        LocateResults locateResults = adminOptionObjectBuilder.locate(
//                adminOptionQuestionLocateCriteria, user);
//        int searchResultCount = locateResults.getTotalResultsCount();
//        List adminOptionSearchResultList = locateResults.getLocateResults();
//        Iterator iterator = adminOptionSearchResultList.iterator();
//        while (iterator.hasNext()) {
//            AssociatedQuestionBO associatedQuestionBO = (AssociatedQuestionBO) iterator
//                    .next();
//            if (null == associatedQuestionBO.getReferenceDesc()) {
//                return false;
//            }
//        }
//        return true;
//    }


    /**
     * CheckIn Admin Option Validation 1.Question Exist 2. Reference Exist 3.
     * Reference Unique
     * 
     * @param adminOptionBO
     * @param user
     * @param messageList
     * @return boolean
     * @throws WPDException
     */
    private static boolean isValid(AdminOptionBO adminOptionBO, User user,
            List messageList) throws WPDException {
        List referenceDesc = null;
        int searchResultCount = 0;
        List adminOptionSearchResultList = null;
        AdminOptionBusinessObjectBuilder adminOptionObjectBuilder = new AdminOptionBusinessObjectBuilder();
        adminOptionSearchResultList = adminOptionObjectBuilder.getQuestionnaire(adminOptionBO
                .getAdminOptionId());
        if (adminOptionSearchResultList.isEmpty()) {
        	 // validation for is Question Exist
            messageList.add(new ErrorMessage(
                    BusinessConstants.ADMIN_OPTION_QN_NOT_EXIST));
            return false;
        }
        return true;
    }


    /**
     * To set the values from VO to BO
     * 
     * @param adminOptionVO
     * @return adminOptionBO
     */
    private AdminOptionBO getAdminOptionBusinessObject(
            AdminOptionVO adminOptionVO) {
        AdminOptionBO adminOptionBO = new AdminOptionBO();
        adminOptionBO.setAdminOptionId(adminOptionVO.getAdminOptionId());
        adminOptionBO.setAdminName(adminOptionVO.getAdminName());
        return adminOptionBO;
    }
}