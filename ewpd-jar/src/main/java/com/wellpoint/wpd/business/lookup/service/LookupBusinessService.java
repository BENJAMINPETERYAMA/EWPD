/*
 * LookupBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.lookup.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.lookup.builder.LookupBusinessObjectBuilder;
import com.wellpoint.wpd.business.lookup.locateCriteria.LookupAdminQuestionLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.lookup.request.LookupAdminQuestionRequest;
import com.wellpoint.wpd.common.lookup.response.LookupAdminQuestionResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupBusinessService extends WPDService {
	
	public LookupBusinessService(){
	}
	
	 public WPDResponse execute(WPDRequest request) throws ServiceException {
       
        return null;
    }
    
	
	/**
     * Method to call search question
     * 
     * @param lookupAdminQuestionRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(LookupAdminQuestionRequest lookupAdminQuestionRequest)throws ServiceException {
    	LookupAdminQuestionResponse lookupAdminQuestionResponse = new LookupAdminQuestionResponse();
        try {
        	List questionSearchResultsList = null;
        	List errorList = new ArrayList();
        	LocateResults searchResults = null;
        	LookupAdminQuestionLocateCriteria lookupAdminQuestionLocateCriteria = getQuestionSearchObject(lookupAdminQuestionRequest);
        	LookupBusinessObjectBuilder lookupBusinessObjectBuilder = new LookupBusinessObjectBuilder();
            searchResults = lookupBusinessObjectBuilder.locate(lookupAdminQuestionLocateCriteria, lookupAdminQuestionRequest.getUser()); 
            int searchResultCount = searchResults.getTotalResultsCount();
            questionSearchResultsList = searchResults.getLocateResults();
            if(questionSearchResultsList != null && questionSearchResultsList.size() == 0 && lookupAdminQuestionRequest.getLookupAdminQuestionVO().getCriteriaQuestion().length() > 0){
                lookupAdminQuestionLocateCriteria.setQuestion("%");
                searchResults = lookupBusinessObjectBuilder.locate(lookupAdminQuestionLocateCriteria, lookupAdminQuestionRequest.getUser()); 
                questionSearchResultsList = searchResults.getLocateResults();
            }
 	        if (questionSearchResultsList.size() > 0) {
 	        	lookupAdminQuestionResponse.setAdminOptionQuestionList(questionSearchResultsList);

 	        } else if (questionSearchResultsList.size() == 0 && searchResultCount == 0) {
 	            errorList.add(new InformationalMessage(
 	                    "mandate.question.searchresult.notfound"));
 	           lookupAdminQuestionResponse.setMessages(errorList);
 	        }

        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(lookupAdminQuestionRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return lookupAdminQuestionResponse;
    }
   
    /**
     * Method to perform search
     * 
     * @param lookupAdminQuestionRequest
     * @return lookupAdminQuestionLocateCriteria
     */
	private LookupAdminQuestionLocateCriteria getQuestionSearchObject(LookupAdminQuestionRequest lookupAdminQuestionRequest) {
		LookupAdminQuestionLocateCriteria lookupAdminQuestionLocateCriteria = new LookupAdminQuestionLocateCriteria();
		lookupAdminQuestionLocateCriteria.setQuestion(lookupAdminQuestionRequest.getLookupAdminQuestionVO().getCriteriaQuestion());
		lookupAdminQuestionLocateCriteria.setAdminOptionId(lookupAdminQuestionRequest.getLookupAdminQuestionVO().getAdminOptionId());
        return lookupAdminQuestionLocateCriteria;
	}
	


}
