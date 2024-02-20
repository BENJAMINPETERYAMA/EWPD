/*
 * WPDFilter.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework;

import java.io.IOException;

import javax.servlet.*;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDFilter.java 30 2006-06-21 15:55:08Z U10347 $
 */
public abstract class WPDFilter implements Filter{

    public WPDFilter() {
        super();
    }

	public void init(FilterConfig arg0) throws ServletException {
		//common implementation comes here		
	}
	
	public void destroy() {
		//common implementation comes here. 
	}

	public abstract void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException;

}
