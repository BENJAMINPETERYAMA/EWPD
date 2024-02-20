/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.viewall.service;

import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.business.adminmethod.viewall.bo.AdminMethodViewAllFilterBO;
import com.wellpoint.wpd.business.adminmethod.viewall.builder.AdminmethodViewAllBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.request.AdminmethodViewAllRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminmethodViewAllResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminmethodViewAllBusinessService extends WPDBusinessService{

	private AdminmethodViewAllBusinessObjectBuilder adminmethodViewAllBusinessObjectBuilder;
	
	  public WPDResponse execute( AdminmethodViewAllRequest request) throws ServiceException {
	  	
        Logger.logInfo("Entering execute method of AdminmethodViewAllBusinessService class, request = " + request.getClass().getName());
        List messageList = null;
        //Getting the response from the response factory
        AdminmethodViewAllResponse response = (AdminmethodViewAllResponse)WPDResponseFactory.getResponse(WPDResponseFactory.ADMINMETHOD_VIEWALL_RESPONSE);
        try {
        	  List searchList = new ArrayList();
        	  AdminmethodViewAllBusinessObjectBuilder adminmethodViewAllBusinessObjectBuilder = new AdminmethodViewAllBusinessObjectBuilder();          
        	  AdminMethodViewAllFilterBO adminMethodViewAllFilterBO = getAdminMethodViewAllFilterBO(request);        	  
        	  
        	  // Builder call for retrieving the data based on the search criteria.
        	  searchList = adminmethodViewAllBusinessObjectBuilder.adminMethodSearchList(adminMethodViewAllFilterBO);
        	  
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
        	  response.setAdminMethodDescription(adminMethodViewAllFilterBO.getAdminMethodDescription());
        	  
        	  response.setQueryName(adminMethodViewAllFilterBO.getQueryName());
        	  
        	  
        	  Logger.logInfo("Returning  from execute method of AdminmethodViewAllBusinessService class for request=" + request.getClass().getName());
        } catch (SevereException ex) {
            List logParameters = new ArrayList(2);
            String logMessage = "Failed while processing Retrieve Popup searchList in of AdminmethodViewAllBusinessService class=" + request.getClass().getName();
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
	    private AdminMethodViewAllFilterBO getAdminMethodViewAllFilterBO(AdminmethodViewAllRequest request){
	    	
	    	AdminMethodViewAllFilterBO adminMethodViewAllFilterBO = new AdminMethodViewAllFilterBO();
	    	adminMethodViewAllFilterBO.setQueryName(request.getQueryName());	
	    	adminMethodViewAllFilterBO.setAdminMethodNo(request.getAdminMethodNo());
	    	adminMethodViewAllFilterBO.setAdminMethodDescription(request.getAdminMethodDescription());	
	    	adminMethodViewAllFilterBO.setProcessingMethod(request.getProcessingMethods());
	    	return adminMethodViewAllFilterBO;
	    	
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
