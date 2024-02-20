/*
 * Created on Jun 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.aop;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.application.security.SecurityService;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataFeedRunningAdvice{
	
	private Logger log = Logger.getLogger(this.getClass());
	private SecurityService securityService;
	
	public void isDataFeedRunning()
	{
		
		log.debug("isDataFeedRunning : inside isDataFeedRunning method");
		boolean datafeedRunningFlag = securityService.isDataFeedRunning();
		
		if(datafeedRunningFlag)
			throw new DomainException("The requested operation is not permitted since Datafeed is running");
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
}
