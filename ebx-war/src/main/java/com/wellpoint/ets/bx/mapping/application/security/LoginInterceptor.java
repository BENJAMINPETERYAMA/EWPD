/*
 * Created on Jun 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application.security;

import java.io.IOException;
import org.owasp.esapi.ESAPI;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wellpoint.ets.bx.mapping.web.AppInfo;


/**
 * @author u20622
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = Logger.getLogger(this.getClass());

    private AppInfo appInfo;

    private SecurityService securityService;

    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        String logout = request.getParameter("logout");
        response.addHeader("X-Frame-Options", "DENY");
        if(logout != null && logout.equals("true")) {
            //httpSession.invalidate();
            log.debug("Loging out! Session invalidated");
            RequestDispatcher rd = request.getRequestDispatcher("/logout.jsp");
            rd.forward(request, response);
            return false;
        }

        String requestedSessionId = request.getRequestedSessionId();
        String currentSessionId = httpSession.getId();
        LoginUser user = (LoginUser)httpSession.getAttribute(SecurityConstants.USER);
        String userIdFromEwpd = request.getParameter("userName");
        if (requestedSessionId == null || "".equals(requestedSessionId) || (!request.isRequestedSessionIdValid()) ) {
            log.debug("New User Session!");
            user = setUserToSession(request);
            request.setAttribute(SecurityConstants.USER_NAME, user.getUserId());
            return true;
        }
        if(requestedSessionId.equals(currentSessionId) && user == null){
            if(userIdFromEwpd != null){
                log.debug("New Session initiated from eWPD.");
                user = setUserToSession(request);
                request.setAttribute(SecurityConstants.USER_NAME, user.getUserId());
                return true;
            }else {
                log.debug("Session user Empty. Handiling Session Expiry!");
                handleSessionExpired(request, response);
                return false;
            }
        }
        if(!requestedSessionId.equals(currentSessionId) && user == null) {
            log.debug("Session already expired! Handiling Session Expiry!");
            handleSessionExpired(request, response);
            return false;
        }
        log.debug("Active Session");
        log.debug("Active Session");
        request.setAttribute(SecurityConstants.USER_NAME, user.getUserId());
        return true;
    }


    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if(modelAndView!=null){
            log.info(modelAndView.getViewName());
            Object obj = request.getSession().getAttribute(SecurityConstants.USER);
            if (obj != null && (obj instanceof LoginUser)) {
                LoginUser user = (LoginUser) obj;
                UserUIPermissions userUIPermissions = new UserUIPermissions(
                        modelAndView.getViewName(), user);
                modelAndView.addObject(SecurityConstants.USER_NAME, user.getUserId());
                modelAndView.addObject("userUIPermissions", userUIPermissions);
                modelAndView.addObject("appInfo", appInfo);
            }
        }
        
    }

    /**
     * @throws SecurityException
     * 
     */
    private LoginUser setUserToSession(HttpServletRequest request) throws SecurityException {
        log.debug("Setting User to Session!");
        String userId = ESAPI.encoder().encodeForHTML(request.getHeader(SecurityConstants.SM_USER));
        if(null!=userId && userId.matches("[0-9a-zA-Z_]+")){
            userId =  userId.toUpperCase();
        }
        String roleName = request.getHeader(
                SecurityConstants.SM_ROLES);
        log.debug("UserId From SM "+ESAPI.encoder().encodeForHTML(userId));
        log.debug("Roles From SM "+ESAPI.encoder().encodeForHTML(roleName));
        LoginUser user = securityService.getUser(userId,roleName);
        request.getSession().setAttribute(SecurityConstants.USER, user);
        return user;
    }


    /**
     * @return Returns the securityService.
     */
    public SecurityService getSecurityService() {
        return securityService;
    }
    /**
     * @param securityService The securityService to set.
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }


    public AppInfo getAppInfo() {
        return appInfo;
    }


    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    private void handleSessionExpired(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("User Session Expired!");
        RequestDispatcher rd = request
        .getRequestDispatcher("/sessionExpired.jsp");
        rd.forward(request, response);
    }

}
