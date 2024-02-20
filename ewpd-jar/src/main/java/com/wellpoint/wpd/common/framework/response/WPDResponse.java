/*
 * WPDResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.response;

import java.util.TimeZone;
import java.util.Vector;

import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.Message;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDResponse.java 8376 2006-10-24 04:00:33Z U14231 $
 */
public class WPDResponse {

    private java.util.List messages;

    private TimeZone timeZone;

    /**
     *  
     */
    public WPDResponse() {
        super();
        try{
            this.timeZone = ((AuditFactory) ObjectFactory
                .getFactory(ObjectFactory.AUDIT)).getAudit().getTimeZone();
        }catch(Exception e){
            //nothing to do. 
        }
    }

    /**
     * Add an exception to the List of exceptions in WPDResponse object. The
     * order in which the exceptions are added is maintained.
     * 
     * @param message:
     *            The Message to be added to the List.
     * @deprecated #addMessage(Message)
     * @see #addMessage(Message)
     */
    public void addException(Message message) {
        if (message == null) {
            return;
        }
        if (messages == null) {
            messages = new Vector();
        }
        messages.add(message);
    }
   
    /**
     * Add a Message to the List of Messages in WPDResponse object. The order in
     * which the Messages are added is maintained.
     * 
     * @param message:
     *            The Message to be added to the List.
     */
    public void addMessage(Message message) {
        if (message == null) {
            return;
        }
        if (messages == null) {
            messages = new Vector();
        }
        messages.add(message);
    }

    /**
     * @return Returns the messages.
     */
    public java.util.List getMessages() {
        return messages;
    }

    /**
     * @param messages
     *            The messages to set.
     */
    public void setMessages(java.util.List messages) {
        this.messages = messages;
    }

    /**
     * checks if there are error messages in messages list.
     * 
     * @return boolean (true/false). Returns true if there is an instance of
     *         ErrorMessage in the messages list.
     */
    public boolean isErrorMessageInList() {
        boolean retValue = false;
        if (this.messages != null) {
            int count = this.messages.size();
            for (int j = 0; j < count; j++) {
                if (messages.get(j) instanceof ErrorMessage) {
                    retValue = true;
                    break;
                }
            }
        }
        return retValue;
    }

    /**
     * @return Returns the timeZone.
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone
     *            The timeZone to set.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}