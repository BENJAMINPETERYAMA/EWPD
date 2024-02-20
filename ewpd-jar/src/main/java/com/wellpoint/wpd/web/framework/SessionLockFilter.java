/*
 * SessionLockFilter.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.framework;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SessionLockFilter.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class SessionLockFilter extends WPDFilter {

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
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		 // Bypassing ajax request to fix performance issue caused by HTTPServer.
	      
	      if (httpRequest.getHeader("AA-ajax-request") != null
	                  && httpRequest.getHeader("AA-ajax-request").equalsIgnoreCase("true")) {
	            Logger.logInfo("Ajax Request Bypassing SessionLock Filter");
	            chain.doFilter(request,httpResponse);
	            return;
	      }

		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("SESSION_LOCKED") != null){
			//session is locked;	
			session.setAttribute("RETURN_CACHED","yes");
			RequestDispatcher rd = request.getRequestDispatcher("/faces/pages/lockHold.jsp"+sessionId);
			rd.forward(request,response);
		}else{
			if(session.getAttribute("RETURN_CACHED") != null){
				//user is seeing the please wait page.  we need to return
				//cached output.
				writeResponse(httpResponse,(String)session.getAttribute("RESPONSE_DATA"));
				session.removeAttribute("RETURN_CACHED");
				session.removeAttribute("SESSION_LOCKED");
				session.removeAttribute("RESPONSE_DATA");
			}else{
				//Normal flow.  The user (nice!) did not submit more than one request.
				//create custom response wrapper
				//pass it along to other filters in the chain
				//get byte[] from response wrapper and set it to session
				//write out data to response
				//unlock session.
				session.setAttribute("SESSION_LOCKED","yes");
				SessionLockResponseWrapper slrw = new SessionLockResponseWrapper(httpResponse);
				chain.doFilter(request,slrw);
				try{
					session.setAttribute("RESPONSE_DATA",slrw.getResponseData());
					if(session.getAttribute("RETURN_CACHED") == null){
						writeResponse(httpResponse,slrw.getResponseData());
						session.removeAttribute("RESPONSE_DATA");
					}
					session.removeAttribute("SESSION_LOCKED");
				}catch(Exception e){
				    //The above code breaks when the session is already invalidated 
				    //in the Servlets or JSPs
				    writeResponse(httpResponse,slrw.getResponseData());
				}
			}
		}
	}

	private void writeResponse(HttpServletResponse response,String data) throws IOException{
		if(data != null && !"".equals(data)){
			response.setContentLength(data.length());
			PrintWriter pw = response.getWriter();
			pw.write(data);
			response.flushBuffer();
		}else{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private class SessionLockResponseWrapper extends HttpServletResponseWrapper{

		ServletOutputStream sos;
		PrintWriter pw;
		CharArrayWriter caw;

		public SessionLockResponseWrapper(HttpServletResponse response){
			super(response);
			caw = new CharArrayWriter();
		}

		public ServletOutputStream getOutputStream() throws IOException{
			if(sos == null){
				sos = new ServletOutputStream(){
					public void write(int i) throws IOException{
						caw.write(i);
					}
				};
			}
			return sos;
		}

		public PrintWriter getWriter() throws IOException{
			if(pw == null){
				pw = new PrintWriter(caw);
			}
			return pw;
		}

		public String getResponseData(){
			return caw.toString();
		}
	}

}
