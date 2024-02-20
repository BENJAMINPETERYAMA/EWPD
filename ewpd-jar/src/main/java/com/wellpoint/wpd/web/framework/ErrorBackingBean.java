/*
 * ErrorBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ErrorBackingBean.java 8376 2006-10-24 04:00:33Z U14231 $
 */
public class ErrorBackingBean extends WPDBackingBean {

    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            String logId = log(fc.getExternalContext());
            sb.append("Log Id : " + logId);
        } catch (Exception e) {
            try {
                FacesContext fc = FacesContext.getCurrentInstance();
                sb.append(getMessageForScreen(fc.getExternalContext()));
            } catch (Exception ex) {
                sb.append("No Trace available<br>");
                sb.append(ex.getMessage());
            }
        }
        return sb.toString();
    }

    protected String log(ExternalContext ec) throws Exception {

        Map requestMap = ec.getRequestMap();
        Exception e = (Exception) requestMap
                .get("javax.servlet.error.exception");

        List params = new ArrayList();
        params.add(ec.getRequestParameterMap());
        params.add(requestMap);
        params.add(ec.getSessionMap());

        String message = "Error 500";
        String ids = null;
        Object obj = requestMap.get("messages");
        if (obj != null) {
            List messages = (List) obj;
            if (messages.size() > 0) {
                Iterator msgIter = messages.iterator();
                while (msgIter.hasNext()) {
                    Object msgobj = msgIter.next();
                    if (msgobj instanceof ErrorMessage) {
                        String id = ((ErrorMessage) msgobj).getLogId();
                        if (id != null) {
                            if (ids == null) {
                                ids = "  Refer Log Id(s): " + id;
                            } else {
                                ids += ", " + id;
                            }
                        }
                    }
                }
                if (ids != null) {
                    message += ids;
                }
            }

        }
        SevereException se = new SevereException(message, params, e);

        Logger.logException(se);
        if (se.getLogId() == null || "0".equals(se.getLogId())) {
            throw new Exception("Error logging to DB");
        }
        return se.getLogId();
    }

    protected String getMessageForScreen(ExternalContext ec) {
        StringBuffer sb = new StringBuffer();

        Map requestMap = ec.getRequestMap();
        Exception e = (Exception) requestMap
                .get("javax.servlet.error.exception");

        sb.append(getStackTraceAsString(e));

        sb.append("<p>Request Parameters</p>");
        sb.append(ec.getRequestParameterMap());

        sb.append("<p>Request Attributes</p>");
        sb.append(ec.getRequestMap());

        sb.append("<p>Session Attributes</p>");
        sb.append(ec.getSessionMap());
        return sb.toString();
    }

    protected String getMapDataAsString(Map map) {
        StringBuffer sb = new StringBuffer();
        Set keys = map.keySet();
        if (keys != null) {
            Iterator keysIter = keys.iterator();
            while (keysIter.hasNext()) {
                Object key = keysIter.next();
                sb.append(key).append("=").append(
                        map.get(key) != null ? map.get(key) : "null");
                sb.append("<br>");
            }
        }
        return sb.toString();
    }

    protected String getStackTraceAsString(Exception e) {
        if (e != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.close();
                sw.close();
                return replaceNewlineTab(sw.toString());
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }

    protected String replaceNewlineTab(String input) {
        String temp = input.replaceAll("\n", "<BR>");
        return temp.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
    }

}