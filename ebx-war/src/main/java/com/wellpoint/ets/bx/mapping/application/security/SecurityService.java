/*
 * Created on Jun 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SecurityService {

	LoginUser getUser(String userId, String roleNamesString) throws SecurityException;
	boolean isDataFeedRunning();
	
}
