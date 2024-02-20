/*
 * SessionFilter.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.framework;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SessionFilter.java 16329 2007-03-29 23:54:42Z U10567 $
 */
public class SessionFilter extends WPDFilter {
	/**
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SecureRandom securityRandom = new SecureRandom();
        byte sessBytes[] = new byte[32];
        securityRandom.nextBytes(sessBytes);
        String sessionId = new String(sessBytes);
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestedSessionId = httpRequest.getRequestedSessionId();
		//For the first request, the requestedSessionId will be null
		if(requestedSessionId == null || "".equals(requestedSessionId) || (!httpRequest.isRequestedSessionIdValid()) ){
			chain.doFilter(request, response);
		}else{
			HttpSession session = httpRequest.getSession();
			String currentSessionId = session.getId();
			SimpleDateFormat formatter
		     = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");

			//Check if the session is the same
			if(requestedSessionId.equals(currentSessionId)){
				//System.out.println("Start**~!~*******************"+httpRequest.getRequestURI()+" At " +formatter.format(new Date()));
				Logger.logInfo("Start**~!~*******************"+httpRequest.getRequestURI()+" At " +formatter.format(new Date()));
				chain.doFilter(request, response);
			    //System.out.println("END**~!~*******************"+httpRequest.getRequestURI()+" At " +formatter.format(new Date()));
			    Logger.logInfo("END**~!~*******************"+httpRequest.getRequestURI()+" At " +formatter.format(new Date()));
			}else{
				//Display the error page
				RequestDispatcher rd = request.getRequestDispatcher("/faces/pages/sessionEnd.jsp"+sessionId);
				rd.forward(request,response);
			}
		}
	}
}
