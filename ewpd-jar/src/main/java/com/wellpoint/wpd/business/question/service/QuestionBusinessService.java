/*
 * QuestionBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.question.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.question.builder.QuestionBusinessObjectBuilder;
import com.wellpoint.wpd.business.question.locateCriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerBO;
import com.wellpoint.wpd.common.benefit.request.QuestionRequest;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.question.bo.FunctionalDomainBO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.question.request.CheckOutQuestionRequest;
import com.wellpoint.wpd.common.question.request.DeleteQuestionRequest;
import com.wellpoint.wpd.common.question.request.PublishQuestionRequest;
import com.wellpoint.wpd.common.question.request.QuestionViewRequest;
import com.wellpoint.wpd.common.question.request.RetrieveQuestionRequest;
import com.wellpoint.wpd.common.question.request.SaveQuestionRequest;
import com.wellpoint.wpd.common.question.request.ScheduleForTestQuestionRequest;
import com.wellpoint.wpd.common.question.request.SearchQuestionRequest;
import com.wellpoint.wpd.common.question.request.TestFailQuestionRequest;
import com.wellpoint.wpd.common.question.request.TestPassQuestionRequest;
import com.wellpoint.wpd.common.question.request.UnlockQuestionRequest;
import com.wellpoint.wpd.common.question.response.CheckOutQuestionResponse;
import com.wellpoint.wpd.common.question.response.DeleteQuestionResponse;
import com.wellpoint.wpd.common.question.response.PublishQuestionResponse;
import com.wellpoint.wpd.common.question.response.QuestionViewResponse;
import com.wellpoint.wpd.common.question.response.RetrieveQuestionResponse;
import com.wellpoint.wpd.common.question.response.SaveQuestionResponse;
import com.wellpoint.wpd.common.question.response.ScheduleForTestQuestionResponse;
import com.wellpoint.wpd.common.question.response.SearchQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestFailQuestionResponse;
import com.wellpoint.wpd.common.question.response.TestPassQuestionResponse;
import com.wellpoint.wpd.common.question.response.UnlockQuestionResponse;
import com.wellpoint.wpd.common.question.vo.PossibleAnswerVO;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * Business service class for questions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBusinessService extends WPDService {

    private static final String VIEW = "view";

    private static final String VIEW_ALL = "viewAll";


    /**
     * Constructor
     */
    public QuestionBusinessService() {
    }


    /**
     * Method to call the service
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        if (request instanceof QuestionRequest)
            return execute((SaveQuestionRequest) request);
        return null;
    }


    /**
     * Method for create/update service
     * 
     * @param saveQuestionRequest
     * @return WPDResponse saveQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SaveQuestionRequest saveQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Save Question Sevice");
        SaveQuestionResponse saveQuestionResponse = (SaveQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_QUESTION_RESPONSE);
        QuestionBusinessObjectBuilder businessObjectBuilder = new QuestionBusinessObjectBuilder();
        try {
            QuestionBO questionBO = copyBusinessObjectFromValueObject(saveQuestionRequest
                    .getQuestionVO());
            List possibleAnswerList = new ArrayList(questionBO.getAnswerList().size() + 1); 
            Iterator iterator = questionBO.getAnswerList().iterator();
            PossibleAnswerBO answerBO = new PossibleAnswerBO();
            answerBO.setPossibleAnswerDesc("Not Answered");
            possibleAnswerList.add(answerBO);
            while(iterator.hasNext()){
            	answerBO = (PossibleAnswerBO)iterator.next();
            	possibleAnswerList.add(answerBO);
            }
            questionBO.setAnswerList(possibleAnswerList);
            
            questionBO.setTerm(saveQuestionRequest.getQuestionVO().getTerm());
            questionBO.setQualifier(saveQuestionRequest.getQuestionVO().getQualifier());
            questionBO.setSpsReference(saveQuestionRequest.getQuestionVO().getSpsReference());
            
            BusinessObjectManager bom = this.getBusinessObjectManager();
            if (saveQuestionRequest.isValidationFlag()) {
                bom.persist(questionBO, saveQuestionRequest.getUser(), false);
                saveQuestionResponse = (SaveQuestionResponse) new ValidationServiceController()
                        .execute(saveQuestionRequest);
                saveQuestionResponse.setQuestionBO(questionBO);
                saveQuestionResponse.addMessage(new InformationalMessage(
                        "question.update.success.info"));
                if (!saveQuestionResponse.isErrorFlag()) {
                    if (saveQuestionRequest.isCheckInFlag()) {
                        try {
                            bom.checkIn(questionBO, saveQuestionRequest
                                    .getUser());
                            questionBO = (QuestionBO) businessObjectBuilder
                                    .retrieveLatestVersion(questionBO);
                            bom.publish(questionBO, saveQuestionRequest
                                    .getUser());
                            InformationalMessage informationalMessage = new InformationalMessage("question.checked.in.success.info");
                            informationalMessage.setParameters(new String[] {questionBO.getQuestionDesc()});
                            saveQuestionResponse
                                    .addMessage(informationalMessage);
                            saveQuestionResponse.setCheckInSuccessFlag(true);
                            return saveQuestionResponse;
                        } catch (WPDException ex) {
                            saveQuestionResponse.addMessage(new ErrorMessage(
                                    "question.checked.in.failure.info"));
                            saveQuestionResponse.setCheckInErrorFlag(true);
                        }
                    } else {
                        saveQuestionResponse.setCheckInErrorFlag(true);
                        saveQuestionResponse
                                .addMessage(new InformationalMessage(
                                        "question.checked.in.validation.success.info"));
                    }
                } else {
                    saveQuestionResponse.setCheckInErrorFlag(true);
                }
            } else {
                if (!saveQuestionRequest.isUpdateFlag()) {
                    saveQuestionResponse = (SaveQuestionResponse) new ValidationServiceController()
                            .execute(saveQuestionRequest);
                    if (saveQuestionResponse.isErrorFlag()) {
                        return saveQuestionResponse;
                    }
                    bom.persist(questionBO, saveQuestionRequest.getUser(),
                                    true);
                    saveQuestionResponse.addMessage(new InformationalMessage(
                            "question.save.success.info"));
                    saveQuestionResponse.setQuestionBO(questionBO);
                    Logger.logInfo("Question Saved Successfully");
                } else {
                    bom.persist(questionBO, saveQuestionRequest.getUser(),
                            false);
                    saveQuestionResponse.addMessage(new InformationalMessage(
                            "question.update.success.info"));
                    Logger.logInfo("Question Updated Successfully");
                    saveQuestionResponse.setQuestionBO(questionBO);
                }
            }
            questionBO = (QuestionBO) bom.retrieve(questionBO,
                    saveQuestionRequest.getUser());
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            saveQuestionResponse.setQuestionVO(questionVO);
            
            List psblAnsrList = new ArrayList(saveQuestionResponse.getQuestionVO().getAnswerList().size() - 1);
            iterator = saveQuestionResponse.getQuestionVO().getAnswerList().iterator();
            while(iterator.hasNext()){
            	PossibleAnswerVO answerVO = (PossibleAnswerVO)iterator.next();
            	if(!answerVO.getPossibleAnswerDesc().equals("Not Answered")){
            		psblAnsrList.add(answerVO);
            	}
            }
            saveQuestionResponse.getQuestionVO().setAnswerList(psblAnsrList);
        } catch (WPDException ex) {
            Logger.logError(ex);
            List logParameters = new ArrayList(2);
            logParameters.add(saveQuestionRequest);
            String logMessage = "Failed while processing SaveQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return saveQuestionResponse;
    }


    /**
     * Method for retrieve service
     * 
     * @param retrieveQuestionRequest
     * @return WPDResponse retrieveQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(RetrieveQuestionRequest retrieveQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Retrieve Question Sevice");
        RetrieveQuestionResponse retrieveQuestionResponse = (RetrieveQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_QUESTION_RESPONSE);
        try {
            QuestionBO questionBO = copyBusinessObjectFromValueObject(retrieveQuestionRequest
                    .getQuestionVO());
            BusinessObjectManager bom = this.getBusinessObjectManager();
            
            boolean lockFlag = bom.lock(questionBO,
                    retrieveQuestionRequest.getUser());
            if(!lockFlag){
            	retrieveQuestionResponse.addMessage(new InformationalMessage("Question being edited by another user."));
            	retrieveQuestionResponse.setUnlockFlag(true);
            }
            else{
            questionBO = (QuestionBO) bom.retrieve(questionBO,
                    retrieveQuestionRequest.getUser());
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            
		    if(null != questionVO.getAnswerList()){
		        List psblAnsrList = new ArrayList(questionVO.getAnswerList().size() - 1);
		        Iterator iterator = questionVO.getAnswerList().iterator();
		        while(iterator.hasNext()){
		        	PossibleAnswerVO answerVO = (PossibleAnswerVO)iterator.next();
		        	if(!answerVO.getPossibleAnswerDesc().equals("Not Answered")){
		        		psblAnsrList.add(answerVO);
		        	}
		        }
		        //questionBO = new QuestionBO();
		        questionVO.setAnswerList(psblAnsrList);
		    }
            retrieveQuestionResponse.setQuestionVO(questionVO);
            retrieveQuestionResponse.setQuestionBO(questionBO);
            Logger.logInfo("Retrieve Question Successful");
            }
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(retrieveQuestionResponse);
            String logMessage = "Failed while processing RetrieveQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return retrieveQuestionResponse;
    }


    /**
     * Method for checkout service
     * 
     * @param checkOutQuestionRequest
     * @return WPDResponse checkOutQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(CheckOutQuestionRequest checkOutQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Checkout Question Sevice");
        CheckOutQuestionResponse checkOutQuestionResponse = (CheckOutQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.CHECKOUT_QUESTION_RESPONSE);

        QuestionBO questionBO = copyBusinessObjectFromValueObject(checkOutQuestionRequest
                .getQuestionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            questionBO = (QuestionBO) bom.checkOut(questionBO,
                    checkOutQuestionRequest.getUser());
            checkOutQuestionResponse.addMessage(new InformationalMessage(
                    "question.checked.out.success.info"));
            Logger.logInfo(" Question Checked out Successfully");
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            checkOutQuestionResponse.setQuestionVO(questionVO);
            checkOutQuestionResponse.setQuestionBO(questionBO);
        } catch (WPDException ex) {
            checkOutQuestionResponse.addMessage(new ErrorMessage(
                    "question.checked.out.failure.info"));
            checkOutQuestionResponse.setCheckOutErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(checkOutQuestionRequest);
            String logMessage = "Failed while processing checkOutQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return checkOutQuestionResponse;

    }


    /**
     * Method for Schedule For Test service
     * 
     * @param scheduleForTestQuestionRequest
     * @return WPDResponse scheduleForTestQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(
            ScheduleForTestQuestionRequest scheduleForTestQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside  Question Schedule For Test Sevice");
        ScheduleForTestQuestionResponse scheduleForTestQuestionResponse = null;
        QuestionBO questionBO = copyBusinessObjectFromValueObject(scheduleForTestQuestionRequest
                .getQuestionVO());
        scheduleForTestQuestionResponse = (ScheduleForTestQuestionResponse) new ValidationServiceController()
                .execute(scheduleForTestQuestionRequest);
        BusinessObjectManager bom = this.getBusinessObjectManager();
        if (!scheduleForTestQuestionResponse.isValidationErrorFlag()) {
            try {

                bom.scheduleForTest(questionBO, scheduleForTestQuestionRequest
                        .getUser());
                scheduleForTestQuestionResponse
                        .addMessage(new InformationalMessage(
                                "schedule.for.test.question.success.info"));
                Logger.logInfo("Question Scheduled For Test Successfully");
                QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
                scheduleForTestQuestionResponse.setQuestionVO(questionVO);
            } catch (WPDException ex) {
                scheduleForTestQuestionResponse.addMessage(new ErrorMessage(
                        "schedule.for.test.question.failure.info"));
                scheduleForTestQuestionResponse
                        .setScheduleForTestErrorFlag(true);
                List logParameters = new ArrayList(2);
                logParameters.add(scheduleForTestQuestionRequest);
                String logMessage = "Failed while processing scheduleForTestQuestionRequest";
                throw new ServiceException(logMessage, logParameters, ex);
            }
        }
        return scheduleForTestQuestionResponse;
    }


    /**
     * Method for Publish
     * 
     * @param publishQuestionRequest
     * @return WPDResponse publishQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(PublishQuestionRequest publishQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Publish Question  Sevice");
        PublishQuestionResponse publishQuestionResponse = (PublishQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.PUBLISH_QUESTION_RESPONSE);
        QuestionBO questionBO = copyBusinessObjectFromValueObject(publishQuestionRequest
                .getQuestionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            bom.publish(questionBO, publishQuestionRequest.getUser());
            publishQuestionResponse.addMessage(new InformationalMessage(
                    "publish.question.success.info"));
            Logger.logInfo(" Question Published Successfully");
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            publishQuestionResponse.setQuestionVO(questionVO);
        } catch (WPDException ex) {
            publishQuestionResponse.addMessage(new ErrorMessage(
                    "publish.question.failure.info"));
            publishQuestionResponse.setPublishErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(publishQuestionRequest);
            String logMessage = "Failed while processing publishQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return publishQuestionResponse;
    }

    
    
    /**
     * Method for Unlock
     * 
     * @param unlockQuestionRequest
     * @return WPDResponse publishQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(UnlockQuestionRequest unlockQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Publish Question  Sevice");
        UnlockQuestionResponse unlockQuestionResponse = (UnlockQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.UNLOCK_QUESTION_RESPONSE);
        QuestionBO questionBO = copyBusinessObjectFromValueObject(unlockQuestionRequest
                .getQuestionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            bom.unlock(questionBO, unlockQuestionRequest.getUser());
            unlockQuestionResponse.addMessage(new InformationalMessage(
                    "unlocked.question.success.info"));
            Logger.logInfo(" Question Unlocked Successfully");
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            unlockQuestionResponse.setQuestionVO(questionVO);
        } catch (WPDException ex) {
        	unlockQuestionResponse.addMessage(new ErrorMessage(
                    "unlocked.question.failure.info"));
            List logParameters = new ArrayList(2);
            logParameters.add(unlockQuestionRequest);
            String logMessage = "Failed while processing unlockQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return unlockQuestionResponse;
    }

    /**
     * Method for Schedule For Test service
     * 
     * @param testPassQuestionRequest
     * @return WPDResponse testPassQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(TestPassQuestionRequest testPassQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Question Test Pass Sevice");
        TestPassQuestionResponse testPassQuestionResponse = (TestPassQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_PASS_QUESTION_RESPONSE);
        QuestionBO questionBO = copyBusinessObjectFromValueObject(testPassQuestionRequest
                .getQuestionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();

        try {
            bom.testPass(questionBO, testPassQuestionRequest.getUser());
            testPassQuestionResponse.addMessage(new InformationalMessage(
                    "testPass.question.success.info"));
            Logger.logInfo("Question Test Pass Done");
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            testPassQuestionResponse.setQuestionVO(questionVO);
        } catch (WPDException ex) {
            testPassQuestionResponse.addMessage(new ErrorMessage(
                    "testPass.question.failure.info"));
            testPassQuestionResponse.setTestPassErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(testPassQuestionRequest);
            String logMessage = "Failed while processing testPassQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return testPassQuestionResponse;
    }


    /**
     * Method for Test Fail service
     * 
     * @param TestFAilQuestionRequest
     * @return WPDResponse testFailQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(TestFailQuestionRequest testFailQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Question Test Fail Sevice");
        TestFailQuestionResponse testFailQuestionResponse = (TestFailQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.TEST_FAIL_QUESTION_RESPONSE);
        QuestionBO questionBO = copyBusinessObjectFromValueObject(testFailQuestionRequest
                .getQuestionVO());
        BusinessObjectManager bom = this.getBusinessObjectManager();
        try {
            bom.testFail(questionBO, testFailQuestionRequest.getUser());
            testFailQuestionResponse.addMessage(new InformationalMessage(
                    "testFail.question.success.info"));
            Logger.logInfo("Question Test Fail Done");
            QuestionVO questionVO = copyValueObjectFromBusinessObject(questionBO);
            testFailQuestionResponse.setQuestionVO(questionVO);
        } catch (WPDException ex) {
            testFailQuestionResponse.addMessage(new ErrorMessage(
                    "testFail.question.failure.info"));
            testFailQuestionResponse.setTestFailErrorFlag(true);
            List logParameters = new ArrayList(2);
            logParameters.add(testFailQuestionRequest);
            String logMessage = "Failed while processing testFailQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return testFailQuestionResponse;
    }


    /**
     * Method for view and view all service
     * 
     * @param questionViewRequest
     * @return WPDResponse questionViewResponse
     * @throws ServiceException
     */
    public WPDResponse execute(QuestionViewRequest questionViewRequest)
            throws ServiceException {
        Logger.logInfo("Inside Question View Sevice");
        QuestionViewResponse questionViewResponse = (QuestionViewResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.QUESTION_VIEW_RESPONSE);
        try {
            List questionSearchResultsList = null;
            LocateResults searchResults = null;
            BusinessObjectManager bom = this.getBusinessObjectManager();
            if (VIEW_ALL.equals(questionViewRequest.getAction())) {
                QuestionLocateCriteria questionLocateCriteria = new QuestionLocateCriteria();
                questionLocateCriteria.setQuestionDesc(questionViewRequest
                        .getQuestionVO().getQuestionDesc());
                questionLocateCriteria.setAction(questionViewRequest
                        .getAction());
                searchResults = bom.locate(questionLocateCriteria,
                        questionViewRequest.getUser());
                questionSearchResultsList = searchResults.getLocateResults();
                
    			for (Iterator iter = questionSearchResultsList.iterator(); iter
    					.hasNext();) {
    				QuestionBO questionBO = (QuestionBO) iter.next();
    				boolean b;
    				if(questionBO.isAdminMethodMapped()){
    					if(questionBO.getState().isValidForCheckOut())
    						questionBO.setAdminMethodMapped(true);	
    					else 
    						questionBO.setAdminMethodMapped(false);
    					
    					if(questionBO.getState().isValidForDelete())
    						questionBO.setDeleteFlag(true);
    					else
    						questionBO.setDeleteFlag(false);
    				}
    			}
            } else if (VIEW.equals(questionViewRequest.getAction())) {
                QuestionBO questionBO = new QuestionBO();
                questionBO.setQuestionNumber(questionViewRequest
                        .getQuestionVO().getQuestionNumber());
                questionBO.setQuestionDesc(questionViewRequest.getQuestionVO()
                        .getQuestionDesc());
                questionBO.setVersion(questionViewRequest.getQuestionVO()
                        .getVersion());
                questionBO.setFunctionalDomainList(questionViewRequest.getQuestionVO().getFunctionalDomainCDList());
                questionBO = (QuestionBO) bom.retrieve(questionBO,
                        questionViewRequest.getUser());
                questionViewResponse.setQuestion(questionBO);
                QuestionLocateCriteria questionLocateCriteria = new QuestionLocateCriteria();
                questionLocateCriteria.setQuestionNumber(questionViewRequest
                        .getQuestionVO().getQuestionNumber());
                questionLocateCriteria.setAction(questionViewRequest
                        .getAction());
                searchResults = bom.locate(questionLocateCriteria,
                        questionViewRequest.getUser());
                questionSearchResultsList = searchResults.getLocateResults();
            }
            questionViewResponse
                    .setQuestionResultList(questionSearchResultsList);
            QuestionBO questionBO = (QuestionBO) questionViewResponse
        			.getQuestionResultList().get(0);
            if(null != questionBO.getAnswerList()){
                List psblAnsrList = new ArrayList(questionBO.getAnswerList().size() - 1);
                Iterator iterator = questionBO.getAnswerList().iterator();
                while(iterator.hasNext()){
                	PossibleAnswerBO answerBO = (PossibleAnswerBO)iterator.next();
                	if(!answerBO.getPossibleAnswerDesc().equals("Not Answered")){
                		psblAnsrList.add(answerBO);
                	}
                }
                questionBO.setAnswerList(psblAnsrList);
            }
            questionViewResponse.getQuestionResultList().set(0,questionBO);          
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(questionViewRequest);
            String logMessage = "Failed while processing QuestionViewRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return questionViewResponse;
    }


    /**
     * Method to copy ValueObject From BusinessObject
     * 
     * @param questionBO
     * @return QuestionVO
     */
    private QuestionVO copyValueObjectFromBusinessObject(QuestionBO questionBO) {
        QuestionVO questionVO = new QuestionVO();
        PossibleAnswerBO possibleAnswerBO;
        PossibleAnswerVO possibleAnswerVO;
        questionVO.setQuestionNumber(questionBO.getQuestionNumber());
        questionVO.setQuestionDesc(questionBO.getQuestionDesc());
        questionVO.setDataTypeId(questionBO.getDataTypeId());
        questionVO.setPva(questionBO.getProviderArrangement());
        questionVO.setVersion(questionBO.getVersion());
        questionVO.setStatus(questionBO.getStatus());
        questionVO.setState(questionBO.getState());
        questionVO.setCreatedUser(questionBO.getCreatedUser());
        questionVO.setCreatedTimestamp(questionBO.getCreatedTimestamp());
        questionVO.setLastUpdatedUser(questionBO.getLastUpdatedUser());
        questionVO.setTerm(questionBO.getTerm());
        questionVO.setQualifier(questionBO.getQualifier());
        questionVO.setSpsReference(questionBO.getSpsReference());
        questionVO.setBenefitLineDataType(questionBO.getBenefitLineDataType());
        questionVO
                .setLastUpdatedTimestamp(questionBO.getLastUpdatedTimestamp());
        questionVO.setFunctionalDomainCDList(questionBO.getFunctionalDomainList());
        List answerList = questionBO.getAnswerList();
        if (null != answerList) {
            List possibleAnswerList = new ArrayList(answerList.size());
   			if(null != possibleAnswerList){
            	Iterator answerListIterator = answerList.iterator();
           		while (answerListIterator.hasNext()) {
                	possibleAnswerVO = new PossibleAnswerVO();
                    possibleAnswerBO = (PossibleAnswerBO) answerListIterator.next();
	                possibleAnswerVO.setQuestionNumber(possibleAnswerBO
    	                    .getQuestionNumber());
        	        possibleAnswerVO.setPossibleAnswerId(possibleAnswerBO
            	            .getPossibleAnswerId());
                	possibleAnswerVO.setPossibleAnswerDesc(possibleAnswerBO
                    	    .getPossibleAnswerDesc());
                	possibleAnswerList.add(possibleAnswerVO);
            	}
        	}
   	        questionVO.setAnswerList(possibleAnswerList);
        }
        return questionVO;
    }


    /**
     * Method to copy BusinessObject From ValueObject
     * 
     * @param questionVO
     * @return QuestionBO
     */
    private QuestionBO copyBusinessObjectFromValueObject(QuestionVO questionVO) {
        QuestionBO questionBO = new QuestionBO();
        PossibleAnswerBO possibleAnswerBO;
        PossibleAnswerVO possibleAnswerVO;
        questionBO.setQuestionNumber(questionVO.getQuestionNumber());
        questionBO.setQuestionDesc(questionVO.getQuestionDesc());
        questionBO.setDataTypeId(questionVO.getDataTypeId());
        questionBO.setProviderArrangement(questionVO.getPva());
        questionBO.setVersion(questionVO.getVersion());
        questionBO.setStatus(questionVO.getStatus());
        questionBO.setQuestionNumberParentSysId(questionVO.getQuestionNumberParentSysId());
        questionBO.setBenefitLineDataType(questionVO.getBenefitLineDataType());
        List answerList = questionVO.getAnswerList();
        List domainList = questionVO.getFunctionalDomainCDList();
        if(null != answerList){
            List possibleAnswerList = new ArrayList(answerList.size());
            Iterator answerListIterator = answerList.iterator();
            while (answerListIterator.hasNext()) {
                possibleAnswerBO = new PossibleAnswerBO();
                possibleAnswerVO = (PossibleAnswerVO) answerListIterator.next();
                possibleAnswerBO.setQuestionNumber(possibleAnswerVO
                        .getQuestionNumber());
                possibleAnswerBO.setPossibleAnswerId(possibleAnswerVO
                        .getPossibleAnswerId());
                possibleAnswerBO.setPossibleAnswerDesc(possibleAnswerVO
                        .getPossibleAnswerDesc());
                possibleAnswerList.add(possibleAnswerBO);
            }
            questionBO.setAnswerList(possibleAnswerList);
        }
        
        if(null != domainList){
            List functionalDomainList = new ArrayList(domainList.size());
            Iterator domainListIterator = domainList.iterator();
            String functionalDomain = null;
            while (domainListIterator.hasNext()) {
            	FunctionalDomainBO domainBO = new FunctionalDomainBO();
            	functionalDomain = (String) domainListIterator.next();
             	domainBO.setFunctionalDomainCD(functionalDomain);
             	functionalDomainList.add(domainBO);
            }
            questionBO.setFunctionalDomainList(functionalDomainList);
        }

        return questionBO;
    }


    /**
     * Method to call search question
     * 
     * @param searchQuestionRequest
     * @return WPDResponse searchQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SearchQuestionRequest searchQuestionRequest)
			throws ServiceException {
		Logger.logInfo("Inside Search Question Sevice");
		SearchQuestionResponse searchQuestionResponse = (SearchQuestionResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_QUESTION_RESPONSE);
		try {
			//List questionSearchResultsList = null;
			List errorList = new ArrayList(3);
			LocateResults searchResults = null;
			QuestionLocateCriteria questionLocateCriteria = getQuestionSearchObject(searchQuestionRequest);
			BusinessObjectManager bom = this.getBusinessObjectManager();
			searchResults = bom.locate(questionLocateCriteria,
					searchQuestionRequest.getUser());
			int searchResultCount = searchResults.getTotalResultsCount();
			List questionSearchResultsList = searchResults.getLocateResults();
			for (Iterator iter = questionSearchResultsList.iterator(); iter
					.hasNext();) {
				QuestionBO questionBO = (QuestionBO) iter.next();
				boolean b;
				if(questionBO.isAdminMethodMapped()){
					if(questionBO.getState().isValidForCheckOut())
						questionBO.setAdminMethodMapped(true);	
					else 
						questionBO.setAdminMethodMapped(false);
					
					if(questionBO.getState().isValidForDelete())
						questionBO.setDeleteFlag(true);
					else
						questionBO.setDeleteFlag(false);
				}
			}
			if (questionSearchResultsList.size() > 0) {
				if (searchResultCount > searchQuestionRequest
						.getQuestionSearchCriteriaVO().getMaxLocateResultSize()) {
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
					searchQuestionResponse
							.setQuestionSearchResultsList(questionSearchResultsList
									.subList(
											0,
											BusinessConstants.MAX_SEARCH_RESULT_SIZE));
					searchQuestionResponse.setMessages(errorList);
				} else {
					searchQuestionResponse
							.setQuestionSearchResultsList(questionSearchResultsList);
				}
				Logger.logInfo(" Question Search Successful");

			} else if (questionSearchResultsList.size() == 0
					&& searchResultCount == 0) {
				errorList.add(new InformationalMessage("search.results.zero"));
				searchQuestionResponse.setMessages(errorList);
			}

		} catch (WPDException ex) {
			List logParameters = new ArrayList(2);
			logParameters.add(searchQuestionRequest);
			String logMessage = "Failed while processing SearchQuestionRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}

		return searchQuestionResponse;
	}


    /**
	 * Method to perform question search
	 * 
	 * @param searchQuestionRequest
	 * @return QuestionLocateCriteria questionLocateCriteria
	 */
    private QuestionLocateCriteria getQuestionSearchObject(
            SearchQuestionRequest searchQuestionRequest) {
        QuestionLocateCriteria questionLocateCriteria = new QuestionLocateCriteria();
        questionLocateCriteria.setQuestionDesc(BusinessUtil.escpeSpecialCharacters(searchQuestionRequest
                .getQuestionSearchCriteriaVO().getQuestionDesc()));
        questionLocateCriteria.setDataTypeList(searchQuestionRequest
                .getQuestionSearchCriteriaVO().getDataTypeList());
        questionLocateCriteria.setMaximumResultSize(searchQuestionRequest
                .getQuestionSearchCriteriaVO().getMaxLocateResultSize());
        questionLocateCriteria.setFunctionalDomainList(searchQuestionRequest.
        		getQuestionSearchCriteriaVO().getFunctionalDomainList());
        questionLocateCriteria.setSpsReference(searchQuestionRequest
                .getQuestionSearchCriteriaVO().getSpsReference());
        return questionLocateCriteria;
    }


    /**
     * Method to perform delete question
     * 
     * @param deleteQuestionRequest
     * @return WPDResponse deleteQuestionResponse
     * @throws ServiceException
     */
    public WPDResponse execute(DeleteQuestionRequest deleteQuestionRequest)
            throws ServiceException {
        Logger.logInfo("Inside Delete Question Sevice");
        DeleteQuestionResponse deleteQuestionResponse = (DeleteQuestionResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_QUESTION_RESPONSE);
        try {
            QuestionBO questionBO = getQuestionDeleteObject(deleteQuestionRequest);
            BusinessObjectManager bom = this.getBusinessObjectManager();
            bom.delete(questionBO, deleteQuestionRequest.getUser());
            deleteQuestionResponse.setDeleteFlag(true);
            deleteQuestionResponse.addMessage(new InformationalMessage(
                    "question.delete.success.info"));
            Logger.logInfo("Question Deleted Successfully");
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            logParameters.add(deleteQuestionRequest);
            String logMessage = "Failed while processing DeleteQuestionRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        return deleteQuestionResponse;
    }


    /**
     * Method to get the delete question object
     * 
     * @param deleteQuestionRequest
     * @return QuestionBO questionBO
     */
    private QuestionBO getQuestionDeleteObject(
            DeleteQuestionRequest deleteQuestionRequest) {
        QuestionBO questionBO = new QuestionBO();
        questionBO.setQuestionNumber(deleteQuestionRequest.getQuestionVO()
                .getQuestionNumber());
        questionBO.setQuestionDesc(deleteQuestionRequest.getQuestionVO()
                .getQuestionDesc());
        questionBO.setVersion(deleteQuestionRequest.getQuestionVO()
                .getVersion());
        return questionBO;
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

}