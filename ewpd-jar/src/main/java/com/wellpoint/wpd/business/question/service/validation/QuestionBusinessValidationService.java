/*
 * QuestionBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.question.service.validation;

import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.question.vo.QuestionVO;
import com.wellpoint.wpd.common.question.request.DeleteQuestionRequest;
import com.wellpoint.wpd.common.question.request.SaveQuestionRequest;
import com.wellpoint.wpd.common.question.request.ScheduleForTestQuestionRequest;
import com.wellpoint.wpd.common.question.response.DeleteQuestionResponse;
import com.wellpoint.wpd.common.question.response.SaveQuestionResponse;
import com.wellpoint.wpd.common.question.response.ScheduleForTestQuestionResponse;
import com.wellpoint.wpd.business.question.builder.QuestionBusinessObjectBuilder;

/**
 * Validation service class for question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBusinessValidationService extends WPDService {

    /**
     * 
     * Overrided execute method of WPDService. Do the Business processing
     * corresponding to request and returns response.
     * 
     * @param request
     * @return WPDResponse WPDRespnose.
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        return null;
    }


    /**
     * Validation method for Delete method.
     * 
     * @param deleteQuestionRequest
     * @return WPDResponse deleteQuestionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteQuestionRequest deleteQuestionRequest)
            throws ServiceException {

        DeleteQuestionResponse deleteQuestionResponse = (DeleteQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_QUESTION_RESPONSE);
        QuestionBO questionBO = this
                .getQuestionBusinessObject(deleteQuestionRequest
                        .getQuestionVO());
        List messageList = new ArrayList(2);
        try {
            if (isQuestionInUse(questionBO)) {
                messageList.add(new ErrorMessage(
                        "question.cannot.be.deleted.info"));
                deleteQuestionResponse.setErrorFlag(true);
            }
        } catch (WPDException e) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteQuestionRequest);
            String logMessage = "Failed while processing DeleteQuestionRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        deleteQuestionResponse.setMessages(messageList);
        return deleteQuestionResponse;
    }


    /**
     * Validation method to check whether question is used anywhere.
     * 
     * @param questionBO
     * @return boolean
     * @throws WPDException
     */
    public static boolean isQuestionInUse(QuestionBO questionBO)
            throws WPDException {
        QuestionBusinessObjectBuilder questionBusinessObjectBuilder = new QuestionBusinessObjectBuilder();
        if (questionBusinessObjectBuilder.isQuestionInUse(questionBO))
            return true;
        return false;
    }


    /**
     * Validation method for SaveQuestionRequest.
     * 
     * @param saveQuestionRequest
     * @return WPDResponse saveQuestionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(SaveQuestionRequest saveQuestionRequest)
            throws ServiceException {

        SaveQuestionResponse saveQuestionResponse = (SaveQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_QUESTION_RESPONSE);
        QuestionBO questionBO = this
                .getQuestionBusinessObject(saveQuestionRequest.getQuestionVO());
        List messageList = new ArrayList(3);
        try {
            if (saveQuestionRequest.isValidationFlag()) {
                if (!isValidQuestion(questionBO)) {
                    messageList.add(new ErrorMessage(
                            "question.validation.failure.info"));
                    saveQuestionResponse.setErrorFlag(true);
                }
            }
            if (!saveQuestionRequest.isUpdateFlag()) {
                if (isQuestionDuplicate(questionBO)) {
                    messageList.add(new ErrorMessage(
                            "question.already.exist.info"));
                    saveQuestionResponse.setErrorFlag(true);
                }
            }

        } catch (WPDException e) {
            List logParameters = new ArrayList(2);
            logParameters.add(saveQuestionRequest);
            String logMessage = "Failed while processing SaveQuestionRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        saveQuestionResponse.setMessages(messageList);
        return saveQuestionResponse;
    }


    /**
     * Validation method for SaveQuestionRequest.
     * 
     * @param scheduleForTestQuestionRequest
     * @return WPDResponse saveQuestionResponse.
     * @throws ServiceException
     */
    public WPDResponse execute(
            ScheduleForTestQuestionRequest scheduleForTestQuestionRequest)
            throws ServiceException {

        ScheduleForTestQuestionResponse scheduleForTestQuestionResponse = (ScheduleForTestQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SCHEDULEFORTEST_QUESTION_RESPONSE);
        QuestionBO questionBO = this
                .getQuestionBusinessObject(scheduleForTestQuestionRequest
                        .getQuestionVO());
        List messageList = new ArrayList(3);
        try {
            if (!isValidQuestion(questionBO)) {
                messageList.add(new ErrorMessage(
                        "question.test.validation.failure.info"));
                scheduleForTestQuestionResponse.setValidationErrorFlag(true);
            }

        } catch (WPDException e) {
            List logParameters = new ArrayList(2);
            logParameters.add(scheduleForTestQuestionRequest);
            String logMessage = "Failed while processing ScheduleForTestQuestionRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        scheduleForTestQuestionResponse.setMessages(messageList);
        return scheduleForTestQuestionResponse;
    }


    /**
     * Validation method for question.
     * 
     * @param questionBO
     * @return boolean
     * @throws WPDException
     */
    public static boolean isValidQuestion(QuestionBO questionBO)
            throws WPDException {
        QuestionBusinessObjectBuilder questionBusinessObjectBuilder = new QuestionBusinessObjectBuilder();
        if (questionBusinessObjectBuilder.isValidQuestion(questionBO))
            return true;
        return false;
    }


    /**
     * Validation method for duplicate question.
     * 
     * @param questionBO
     * @return boolean
     * @throws WPDException
     */
    public static boolean isQuestionDuplicate(QuestionBO questionBO)
            throws WPDException {
        QuestionBusinessObjectBuilder questionBusinessObjectBuilder = new QuestionBusinessObjectBuilder();
        questionBO = (QuestionBO) questionBusinessObjectBuilder
                .retrieveByQuestionDesc(questionBO);
        if (null == questionBO)
            return false;
        return true;
    }


    /**
     * To set the values from VO to BO
     * 
     * @param questionVO
     * @return questionBO
     */
    private QuestionBO getQuestionBusinessObject(QuestionVO questionVO) {
        QuestionBO questionBO = new QuestionBO();
        questionBO.setQuestionDesc(questionVO.getQuestionDesc());
        questionBO.setQuestionNumber(questionVO.getQuestionNumber());
        questionBO.setVersion(questionVO.getVersion());
        questionBO.setFunctionalDomainList(questionVO.getFunctionalDomainCDList());
        questionBO.setSpsReference(questionVO.getSpsReference());
        questionBO.setTerm(questionVO.getTerm());
        questionBO.setQualifier(questionVO.getQualifier());
        
        return questionBO;
    }
}