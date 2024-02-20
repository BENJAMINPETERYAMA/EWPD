/*
 * PopupBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.popup.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.popup.builder.PopupBuisinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.popup.request.PopupRequest;
import com.wellpoint.wpd.common.popup.response.PopupResponse;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PopupBusinessService extends WPDService {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	 /**
     * Method to retrieve list of records while popup loading ,also retrieve according to the fileter criteria
     * @param PopupRequest
     * @return WPDResponse PopupResponse
     * @throws ServiceException
     * 
     */
    public WPDResponse execute(
    		PopupRequest request)
            throws ServiceException {
        Logger.logInfo("Entering execute method of PopupBusinessService class, request = " + request.getClass().getName());
        List messageList = null;
        //Getting the response from the response factory
        PopupResponse response = (PopupResponse)WPDResponseFactory.getResponse(WPDResponseFactory.POPUP_RESPONSE);;
        try {
        	  List searchList = new ArrayList();
        	  PopupBuisinessObjectBuilder builder = new PopupBuisinessObjectBuilder();          
        	  PopupFilterBO popupBO = getPopupFilterBO(request);
        	  
        	  
        	  searchList = builder.locatePopupResult(popupBO);
        	  
        	  if(null==searchList ||searchList.size()==0){
        	  	messageList = new ArrayList(2);
        	  	if(request.getPopAction()==1){
        	  		messageList.add(new InformationalMessage(
            				BusinessConstants.RECORDS_NOT_FOUND));
        	  	}else {
        	  		messageList.add(new InformationalMessage(
            				BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
        	  	}
        	  }
        	  
        	  
        	  response.setResultList(searchList);
        	  response.setEntitySysId(popupBO.getEntitySysId());
        	  response.setQueryName(popupBO.getQueryName());
        	  response.setHeaderName(popupBO.getHeaderName());
        	  Logger.logInfo("Returning  from execute method of PopupBusinessService class for request=" + request.getClass().getName());
        } catch (WPDException ex) {
            List logParameters = new ArrayList(2);
            String logMessage = "Failed while processing Retrieve Popup searchList in of PopupBusinessService class=" + request.getClass().getName();
            throw new ServiceException(logMessage, logParameters, ex);
        }
        addMessagesToResponse(response, messageList);
        return response;
    }
    /*
     * this methose will create a PopupFilterBO object using input reuest values 
     * @ param PopupRequest
     * @ returns popupFilterBO
     * 
     */
    private PopupFilterBO getPopupFilterBO(PopupRequest request){
    	
    	PopupFilterBO popupFilterBO = new PopupFilterBO();
    	popupFilterBO.setQueryName(request.getQueryName());
    	popupFilterBO.setHeaderName(request.getHeaderName());
    	popupFilterBO.setEntitySysId(request.getEntityid());
    	if("getAdminOptionListForBenefitLevel".equals(request.getQueryName()))
    	{
    		popupFilterBO.setBenefitLvlAdmnSysId(request.getBenefitLvlid());
    	}
    	if(("retrieveAdminMethodNumber").equals(request.getQueryName())){
    		popupFilterBO.setSpsId(request.getSpsId());
    	}
    	popupFilterBO.setHashMap(request.getHashMap());
    	return popupFilterBO;
    	
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
}
