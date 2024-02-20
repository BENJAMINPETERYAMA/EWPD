/*
 * WPDMessage.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.custom;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDMessage.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class WPDMessage extends UIComponentBase {

    protected Object value;
    protected List iMessage;
    protected List eMessage;
    protected List vMessage;

    public static final String ERROR_MSG_FAMILY = "ERRORMSGFAMILY";
    private static final String MESSAGE_RESOURCE_FILE = "com.wellpoint.wpd.common.resources.UserMessages";
    private static final String LOG_ID_MESSAGE_KEY = "common.log.id";
    private static final String DEFAULT_MESSAGE_KEY = "common.default";

    /**
     *  
     */
    public WPDMessage() {
        super();
    }
    /**
     * 
     *
     */
    public void getError() {

        StringBuffer message;
        FacesContext context = getFacesContext();
        vMessage = new ArrayList();
        Iterator it = context.getClientIdsWithMessages();
        while (it.hasNext()) {
            String clientId = (String) it.next();
            Iterator it2 = context.getMessages(clientId);
            while (it2.hasNext()) {
                message = new StringBuffer();
                FacesMessage facesMessage = (FacesMessage) it2.next();
                message
                        .append(clientId.substring(
                                clientId.lastIndexOf(":") + 1).toUpperCase());
                message.append("-" + facesMessage.getDetail());
                vMessage.add(message.toString());
            }

        }

        //List messageList = (List) getValue();
        List messageList = (List) getRequest().getAttribute(WebConstants.MESSAGE_NAME_REQUEST);

        eMessage = new ArrayList();
        iMessage = new ArrayList();
        if (null != messageList) {
            for (int i = 0; i < messageList.size(); i++) {
                Message obj = (Message) messageList.get(i);
                Object[] linkarr= null;
                if(obj.getLink()!=null){
                	String link=obj.getLink();int k;
                	linkarr=new Object[2];
                    ResourceBundle myResources = ResourceBundle
	                .getBundle(MESSAGE_RESOURCE_FILE);
                    String returnMsg = myResources.getString(link);
            		Link linkObj=new Link();
            		linkObj.setLink(returnMsg);
            		linkarr[1]=linkObj;
                              	
                }
                if (obj instanceof ErrorMessage) {
                	String messagevalue =getMessage(obj.getId(), obj
                            .getParameters(),((ErrorMessage)obj).getLogId());
                	if(linkarr==null){
                		eMessage.add(messagevalue);
                	}else{
                		linkarr[0]=messagevalue;
                		eMessage.add(linkarr);
                	}
                } else if (obj instanceof InformationalMessage) {
                    //iExceptionList.add(obj);
                	String messagevalue =getMessage(obj.getId(), obj
                            .getParameters());
                	if(linkarr==null){
                		iMessage.add(messagevalue);
                	}else{
                		linkarr[0]=messagevalue;
                		iMessage.add(linkarr);
                	}
                }

            }

        }

    }

    /**
     * @return Returns the value.
     */
    public Object getValue() {
        if (null != value) {
            return value;
        }
        ValueBinding vb = getValueBinding("value");
        if (null != vb) {
            return (vb.getValue(getFacesContext()));
        } else {
            return null;
        }
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public void setValueBinding(String name, ValueBinding binding) {
        super.setValueBinding(name, binding);
    }


    public String getMessage(String id, String[] parameters) {
        if(id == null){
            id = DEFAULT_MESSAGE_KEY;
        }
        ResourceBundle myResources = ResourceBundle
                .getBundle(MESSAGE_RESOURCE_FILE);
        String returnMsg = myResources.getString(id);
        String result = MessageFormat.format(returnMsg, parameters);
        return result;
    }
    
    public String getMessage(String id, String[] parameters, String logId){
        String msg = getMessage(id,parameters);
        if(logId != null && logId.trim().length() > 0){
            String idMsg = getMessage(LOG_ID_MESSAGE_KEY, new String[]{logId});
            return msg + "  " + idMsg;
        }
        return msg;
    }

    public String getFamily() {
        return ERROR_MSG_FAMILY;
    }

    /**
     * @return Returns the eMessage.
     */
    public List getEMessage() {
        return eMessage;
    }

    /**
     * @param message
     *            The eMessage to set.
     */
    public void setEMessage(List message) {
        eMessage = message;
    }

    /**
     * @return Returns the iMessage.
     */
    public List getIMessage() {
        return iMessage;
    }

    /**
     * @param message
     *            The iMessage to set.
     */
    public void setIMessage(List message) {
        iMessage = message;
    }

    /**
     * @return Returns the vMessage.
     */
    public List getVMessage() {
        return vMessage;
    }

    /**
     * @param message
     *            The vMessage to set.
     */
    public void setVMessage(List message) {
        vMessage = message;
    }
    
	private HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}

}