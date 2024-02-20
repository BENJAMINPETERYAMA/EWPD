/*
 * QuestionService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefit.service;

import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.common.benefit.bo.Question;
import com.wellpoint.wpd.common.benefit.request.QuestionRequest;
import com.wellpoint.wpd.common.benefit.response.QuestionResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionService extends WPDService {

    /**
     * Constructor
     *  
     */
    public QuestionService() {
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
        if (request instanceof QuestionRequest)
            return execute((QuestionRequest) request);
        return null;
    }


    /**
     * Execute method for question request
     * 
     * @param request
     * @return QuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(QuestionRequest request) throws ServiceException {
        QuestionResponse qustionResponse = new QuestionResponse();

        try {
            BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
                    .getFactory(ObjectFactory.BOM);
            BusinessObjectManager bom = bomf.getBusinessObjectManager();

            Question question = getQuestionObject(request);

            bom.persist(question, request.getUser(), true); 

        } catch (Exception ex) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return qustionResponse;
    }


    /**
     * Retrieves question object
     * 
     * @param request
     * @return Question
     */
    private Question getQuestionObject(QuestionRequest request) {
        Question question = new Question();
        question.setQuestionDesc(request.getQuestion());
        question.setDataType(request.getDataType());
        question.setAnswerList(request.getAnswerList());
        if ("$".equals(question.getDataType())) {
            question.setDataTypeId(1);
        } else if ("%".equals(question.getDataType())) {
            question.setDataTypeId(2);
        } else {
            question.setDataTypeId(3);
        }
        return question;
    }

}