/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.framework.exception.LogException;



/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class LifeCycleHistoryLogger {

	/*
	 * Method used to insert the log details
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.LifeCycleHistoryLogger#persist(com.wellpoint.wpd.business.framework.bo.HistoryLogEntry)
	 */
	public void persist(HistoryLogEntry historyLogEntry) throws LogException {
		
		HistoryLogDAO historyLogDAO = getBusinessObjectDAO(historyLogEntry
				.getType());
		try {
			historyLogDAO.persist(historyLogEntry);
		} catch (SQLException sqlException) {
			List param= new ArrayList();
			param.add(historyLogEntry.getClass().getName());
			throw new LogException("Persist operation failed for ",param,sqlException);
			
		}
	}

	/*
	 * TO retreive the logged data
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.LifeCycleHistoryLogger#retrieveLog(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      java.util.List)
	 */
	public List retrieveLog(BusinessObject businessObject, List actions) {
		HistoryLogDAO historyLogDAO = getBusinessObjectDAO(businessObject
				.getClass().getName());
		if(null==historyLogDAO){
			throw new RuntimeException(
	                    "Incorrect DAO class specified.  No class called "
	                            + businessObject
	            				.getClass().getName());
	    
		}
		List val = null;
				val = historyLogDAO.retrieveLog(businessObject, actions);
		return val;
	}

	/*
	 * This is abstract method to get HistoryLogEntry object
	 */
	public abstract HistoryLogEntry getHistoryLogEntry(
			BusinessObject businessObject, Audit audit) throws LogException;

	/*
	 * This method is for getting instance of HistoryLogDAO
	 */
	private HistoryLogDAO getBusinessObjectDAO(String businessObjectName) {
		ApplicationContext applicationContext;

		applicationContext = new ClassPathXmlApplicationContext(
				HistoryLogDAO.APPLICATION_CONTEXT);

		return (HistoryLogDAO) applicationContext.getBean(businessObjectName);

	}

}