/*
 * DivRenderer.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.custom;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: DivRenderer.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class DivRendererForPopup extends Renderer {

    
	/**
     *  
     */
    public DivRendererForPopup() {
        super();
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {

    	boolean errorExists = false;
    	StringBuffer msgBuffer = new StringBuffer();
    	((WPDMessage) component).getError();

        WPDMessage message = (WPDMessage) component;
        List vMessageList = message.getVMessage();
        if (null != vMessageList && vMessageList.size() > 0) {
            msgBuffer.append(createMessage(vMessageList,"errorItem"));
            errorExists = true;            
        }
        List eMessageList = message.getEMessage();
        if (null != eMessageList && eMessageList.size() > 0) {
        	msgBuffer.append(createMessage(eMessageList,"errorItem"));
            errorExists = true;
        }
        List iMessageList = message.getIMessage();
        if (null != iMessageList && iMessageList.size() > 0) {
            msgBuffer.append(createMessage(iMessageList,"infoItem"));
            
        }
        if(msgBuffer.toString().length() > 0){
	        if(errorExists){
	        	renderOutMessage(context, "errorDivForPopup", msgBuffer.toString());
	        }else{
	        	renderOutMessage(context, "infoDivForPopup", msgBuffer.toString());
	        }
        }

    }

    public String createMessage(List messages,String style) {
        StringBuffer message = new StringBuffer();
        for (int i = 0; i < messages.size(); i++) {
        	message.append("<li id=" + style + ">");
            message.append((String) messages.get(i));
            message.append("</li>");

        }
        return message.toString();
    }

    public void renderOutMessage(FacesContext context, String messageDiv,
            String message) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        StringBuffer tag = new StringBuffer();
        //Added the id for the div to modify the look and feel of the div in the filter popup - Nov 12th 2008
        tag.append("<div id='wpdMessageDiv' class='");
        tag.append(messageDiv);
        tag.append("' />");
        tag.append(message);
        tag.append("</div>");
        writer.write(tag.toString());

    }

}