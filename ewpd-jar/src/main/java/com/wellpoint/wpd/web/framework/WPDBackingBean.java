/*
 * WPDBackingBean.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.request.RetrieveUserRequest;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.RetrieveUserResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.security.SecurityConstants;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.Environment;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDBackingBean.java 30199 2007-08-13 23:11:24Z U10347 $
 */
public class WPDBackingBean {

	protected static String USER_CONST = "user";
    public static final String SITEMINDER_ROLE_SEPARATOR ="^";

    protected String userId;

    protected String roleName;

    protected List messages;
    
	protected String duplicateData = "";
    

    public WPDResponse executeService(WPDRequest request) {
        WPDResponse response = null;
        User user = null;
        try {
        	user = getUser();
        	request.setUser(user);
            response = ServiceManager.execute(ServiceManager.BUSINESS_SERVICE, request);
            messages = response.getMessages();
            addAllMessagesToRequest(this.messages);
            //Added for setting the timezone to the request for displaying the timezone all over the application.
            //Modified on Sep 20th 2007.
            addTimeZoneToRequest(response.getTimeZone().getDisplayName(false,java.util.TimeZone.SHORT));
        } catch (ServiceException se) {

        	if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
                if (se.getLogParameters() == null) {
                    se.setLogParameters(new ArrayList());
                }
                se.getLogParameters().add(request);
                se.getLogParameters().add(user);
                Logger.logException(se);
            }
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);
        } catch (Exception e) {
            List params = new ArrayList();
            params.add(request);
            params.add(user);
            SevereException se = new SevereException(
                    "Unexpected error caught in WPDBackingBean.executeService",
                    params, e);
            Logger.logException(se);
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);

        }
        return response;
    }
    
    /**
     * Method for setting the timezone to the request for displaying the timezone all over the application.
     * @param displayName
     */
    private void addTimeZoneToRequest(String displayName) {
        HttpServletRequest request = getRequest();
        request.setAttribute("timezone", displayName);
    }

    public WPDRequest getServiceRequest(String serviceName){
        try{
            return ServiceManager.getServiceRequest(serviceName);
        }catch(ServiceException se){
            //TODO handle exception
        	Logger.logError(se);
        }
        return null;
    }

    public User getUser() throws SevereException{
        HttpSession session = getSession();
        WPDResponse response;
        try {
            if (null != session) {
                User user = (User) session.getAttribute(USER_CONST);
                if (null == user) {
                    userId = getRequest().getHeader(SecurityConstants.SM_USER);
                    roleName = getRequest().getHeader(
                            SecurityConstants.SM_ROLES);
					if(roleName != null){
						roleName = roleName.toUpperCase();
					}
                    //logging code added to aid in debugging. Should be removed
                    // in future.
                    Logger.logError("User id/role:" + userId + "/" + roleName);
                    if((userId == null || userId.trim().length() == 0 || roleName == null || roleName.trim().length() == 0) 
                    		&& (Environment.isUserAcceptanceTest() || Environment.isProduction() || Environment.isMigration())){
                    	List params = new ArrayList();
                    	params.add(userId);
                    	params.add(roleName);
                    	throw new SevereException("User Id and or Role name not received from SiteMinder",params,null);
                    }
                    if(userId == null){
                        userId = SecurityConstants.USER;
                    }
                    if (roleName == null) {
                        roleName = SecurityConstants.DEFAULT_ROLE;
                    }                 
                    
                    WPDRequest userRequestObject = ServiceManager.getServiceRequest(ServiceManager.RETRIEVE_USER);
                    ((RetrieveUserRequest)userRequestObject).setUserId(userId);
                    ((RetrieveUserRequest)userRequestObject).setRoleNames(parseRoleNameString(roleName));
                    
                    response = ServiceManager.execute(
                            ServiceManager.BUSINESS_SERVICE, userRequestObject);
                    if (null != response
                            && response instanceof RetrieveUserResponse) {
                        user = ((RetrieveUserResponse) response).getUser();
                        if (user != null) {
                            if((user.getRoles() == null || user.getRoles().isEmpty())
                                    && (Environment.isUserAcceptanceTest() || Environment.isProduction() || Environment.isMigration())){
                               	List params = new ArrayList();
                            	params.add(user);
                            	params.add(roleName);
                            	throw new SevereException("No Role specified in eWPD for the name received from SiteMinder",params,null);                            
                            }
                            session.setAttribute(USER_CONST, user);
                        } else {
                          	List params = new ArrayList();
                        	params.add(userId);
                        	params.add(roleName);                            
                            throw new SevereException("No User object returned from service",params,null);
                        }
                    }
                }
                return user;
            }
        } catch (ServiceException se) {
			Logger.logError(se);
        }
        return null;
    }
    
    protected ServletContext getApplicationContext() {
        HttpSession session = getSession();
        ServletContext appContext = session.getServletContext();
        return appContext;
    }

    protected HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        return session;
    }

    protected HttpServletRequest getRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        return request;
    }

    protected void addAllMessagesToRequest(List messageList) {
//        HttpServletRequest request = getRequest();
//        request.setAttribute("messages", messageList);
    	/*
    	 * Code Modified NOT to override existing message in request, but to append
    	 * to the existing messages.
    	 */
        if(messageList != null && !messageList.isEmpty()) {
        	for (Iterator iterator = messageList.iterator(); iterator.hasNext();) {
				Message message = (Message) iterator.next();
				addMessageToRequest(message);
			}
        }
    }
    /* WAS6.0 migration changes - changed the condition for checking whether the passing message is existing one or not */
    protected boolean addMessageToRequest(Message message){
    	if(message != null){
    		HttpServletRequest request = getRequest();
    		List messages = (List)request.getAttribute("messages");
    		if(messages == null){
    			messages = new ArrayList();
    			request.setAttribute("messages", messages);
    		}
    		boolean exists = false;
    			for(int i=0;i<messages.size();i++){
    				Message msg = (Message)messages.get(i);
    				if(msg.getId().equals(message.getId())){
    					exists = true;break;
    				}
    			}
        		if(!exists) {
            		messages.add(message);    			
        		}
    		return true;
    	}
    	return false;
    }

    /**
     * @return Returns the roleName.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return Returns the messages.
     */
    public List getMessages() {
        return messages;
    }
    
    public void setBreadCrumbText(String breadCrumbText){
    	getRequest().setAttribute("breadCrumbText",breadCrumbText);
    }
    
    protected List parseRoleNameString(String roleName){
    	List roleEmptyList = null;
    	if(roleName != null){
    		List roles = new ArrayList();
    		StringTokenizer st = new StringTokenizer(roleName,SITEMINDER_ROLE_SEPARATOR);
    		while(st.hasMoreTokens()){
    			roles.add(st.nextToken());
    		}
    		return roles;
    	}
    	return roleEmptyList;
    }

	
	/**
	 * @return Returns the duplicateData.
	 */
	public String getDuplicateData() {
		if(getRequest().getAttribute("RETAIN_Value") != null){
			duplicateData = "";
		}
		return duplicateData;
	}
	/**
	 * @param duplicateData The duplicateData to set.
	 */
	public void setDuplicateData(String duplicateData) {
		this.duplicateData = duplicateData;
	}
}