/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo.manager;


import java.util.List;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.framework.exception.LogException;


/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface HistoryLogManager {

	public static final int SYNCHRONOUS_PROCESS = 1;

	public static final int ASYNCHRONOUS_PROCESS = 2;

	// To insert the log file
	public void log(BusinessObject businessObject, Audit audit) throws LogException;

	// To retreive the logged data
	public List retrieveLog(BusinessObject businessObject, List actions) throws LogException;

	public int getProcessType();
}