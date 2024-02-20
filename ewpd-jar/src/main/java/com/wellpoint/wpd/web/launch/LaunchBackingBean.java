/*
 * Created on Oct 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.launch;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;




/**
 * @author aa85936
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LaunchBackingBean extends WPDBackingBean {
	private User user;
	
		
	private boolean isAuthorized(String module, String task) {
	    if (user == null) {
	        try {
	            user = getUser();
	            if (user == null)
	                return false;
	        } catch (SevereException se) {
	            if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
	                Logger.logException(se);
	            }
	            ErrorMessage em = new ErrorMessage(
	                    WebConstants.DEFAULT_ERROR_MSG, se.getLogId());
	            List messages = new ArrayList();
	            messages.add(em);
	            addAllMessagesToRequest(messages);
	            return false;
	        }
	    }
	    return user.isAuthorized(module, task);
	}

	
}



/**
 * Method for checking whether user is authorized for a task
 * 
 * @return boolean AuthorizedForTask
 */
