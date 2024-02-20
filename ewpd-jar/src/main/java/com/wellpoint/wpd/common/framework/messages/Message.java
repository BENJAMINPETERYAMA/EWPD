/*
 * Message.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.messages;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Message.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class Message {

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    protected String id;

    protected String[] parameters;
    
    protected String link;
    
    protected String message;

    /**
     *  
     */
    public Message(String id) {
        super();
        this.id = id;
    }

    /**
     * @return Returns the parameters.
     */
    public String[] getParameters() {
        return parameters;
    }

    /**
     * @param parameters The parameters to set.
     */
    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id).append(",");
        if (parameters != null) {
            StringBuffer sb2 = null;
            for (int i = 0; i < parameters.length; i++) {
                if (sb2 == null) {
                    sb2 = new StringBuffer();
                    sb2.append("[");
                } else {
                    sb2.append(",").append(parameters[i]);
                }
            }
            if (sb2 != null) {
                sb2.append("]");
                sb.append(sb2.toString());
            }
        } else {
            sb.append("parameters=null");
        }
        return sb.toString();
    }
    

	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}