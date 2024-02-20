/*
 * ApplicationStatusFilter.java
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
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ibm.websphere.cache.DistributedMap;
import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ApplicationStatusFilter.java 41497 2008-01-09 14:08:56Z U15427 $
 */
public class ApplicationStatusFilter extends WPDFilter {

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
        HttpServletRequest httpRequest = (HttpServletRequest) request;

//        ServletContext appContext = httpRequest.getSession()
//                .getServletContext();
        DistributedMap applicationLockMap = null;
        try {
            InitialContext ic = new InitialContext();
            applicationLockMap = (DistributedMap) ic
                    .lookup("java:comp/env/wsbEwpd/application_lock_cache");
        } catch (NamingException e) {
            Logger
                    .logError("Error in retrieving the distributed map for Application Lock");
        }
        if (applicationLockMap != null) {
            // Check if the Application lock is already expired
            Date applicationLockExpiryTimeDt = (Date) applicationLockMap
                    .get("LOCK_EXPIRY_TIME");
            long applicationLockExpiryTime = 0;
            if (applicationLockExpiryTimeDt != null) {
                applicationLockExpiryTime = applicationLockExpiryTimeDt.getTime();
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime > applicationLockExpiryTime) {
                applicationLockMap.put("LOCKED_FLAG", "N");
                applicationLockMap.put("LOCKED_TIME", null);
                applicationLockMap.put("LOCK_EXPIRY_TIME", null);
                applicationLockMap.put("LOCKED_USER", null);
            }

            // If the request is from any page other than
            // admin/applicationStatus page (the admin page to change the
            // application lock status), check the LOCKED_FLAG in the
            // applicationLockMap and forward to the error page if it is set
            // to "Y"
            StringBuffer requestURL = httpRequest.getRequestURL();
            String urlStr = null;
            if (requestURL != null) {
                urlStr = requestURL.toString();
            }
            if (urlStr == null || "".equals(urlStr)
                    || urlStr.indexOf("/admin/applicationStatus.jsp") == -1) {
                String lockFlag = (String) applicationLockMap
                        .get("LOCKED_FLAG");
                if ("Y".equals(lockFlag)) {
                    RequestDispatcher rd = httpRequest
                            .getRequestDispatcher("/faces/pages/admin/applicationLock.jsp"+sessionId);
                    rd.forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

}