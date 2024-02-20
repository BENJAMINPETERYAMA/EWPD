/*
 * Created on Sep 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.framework.bo;


import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.business.framework.bo.manager.HistoryLogManager;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.framework.exception.LogException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;



/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates This class implements
 * HistoryLogManager inteface
 */

public class HistoryLogManagerImpl implements HistoryLogManager {

	private int processType = -1;

	/*
	 * Get the object of LifeCycleHistoryLogger Call the getHistoryLogEntry
	 * method inside lifeCycleHistoryLogger class. Call persist() inside
	 * LifeCycleHistoryLogger and pass HistoryLogEntry object to the method
	 */
	public void log(BusinessObject businessObject, Audit audit) throws LogException {
		
		if(SYNCHRONOUS_PROCESS == processType){
			LifeCycleHistoryLogger lifeCycleHistoryLogger = null;
			 {
				
				try {
					lifeCycleHistoryLogger = getLifeCycleHistoryLogger(businessObject);
					HistoryLogEntry historyLogEntry = lifeCycleHistoryLogger
					.getHistoryLogEntry(businessObject, audit);
					lifeCycleHistoryLogger.persist(historyLogEntry);
					
				} catch (WPDException wpdException) {
					
					Logger.logError(wpdException);
					List param= new ArrayList();
					param.add(businessObject);
					throw new LogException("Cannot instantiate lifeCycleHistoryLogger for ",param,wpdException);
				}
				
			 }	
		
			
		}

	}

	/*
	 * Get the object of LifeCycleHistoryLogger Call retrieveLog() inside
	 * LifeCycleHistoryLogger and pass businessObject and list of actions to the
	 * method It returns a List.
	 */
	public List retrieveLog(BusinessObject businessObject, List actions) throws LogException{

		LifeCycleHistoryLogger lifeCycleHistoryLogger;
		try {
			lifeCycleHistoryLogger = getLifeCycleHistoryLogger(businessObject);
			return lifeCycleHistoryLogger.retrieveLog(businessObject, actions);
			
		} catch (WPDException wpdException) {
			
			Logger.logError(wpdException);
			List param= new ArrayList();
			param.add(businessObject);
			throw new LogException("Cannot retreive list for the BO ",param,wpdException);
		}
	}

	/**
	 * @return Returns the processType.
	 */
	public int getProcessType() {
		return processType;
	}

	/**
	 * @param processType
	 *            The processType to set.
	 */
	public void setProcessType(int processType) {
		this.processType = processType;
	}

	/*
	 * Get the object of metaobject from MetaObjectFactory Get the instance of
	 * lifeCycleHistoryLogger by calling getlifeCycleHistoryLoggerObject()
	 * method and pass metaobject and BO to the method Return the object of
	 * LifeCycleHistoryLogger
	 */
	private LifeCycleHistoryLogger getLifeCycleHistoryLogger(
			BusinessObject businessObject) throws WPDException {

		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);
		MetaObject metaObject = null;
		metaObject = metaObjectFactory.getMetaObject(businessObject);
		
		String sLoggerName = metaObject.getLoggerName();

		if ((null != sLoggerName) && !("".equals(sLoggerName))) {

			LifeCycleHistoryLogger lifeCycleHistoryLogger = null;
			try {
				lifeCycleHistoryLogger = (LifeCycleHistoryLogger) getlifeCycleHistoryLogger(
						metaObject, businessObject);
			} catch (SevereException severeException) {
				
				throw new LogException("Logger class name undefined",null,severeException);
			}
			return lifeCycleHistoryLogger;
		}
		throw new LogException("Logger class name undefined",null,null);

	}

	/*
	 * It creats an instance of lifeCycleHistoryLogger by calling class.forname
	 * and passing the fully qualified class path to the method.
	 *  
	 */

	private Object getlifeCycleHistoryLogger(MetaObject metaObject,
			Object businessObject) throws SevereException {
		Class loggerClass = null;
		Object loggerObject = null;
		try {
			loggerClass = Class.forName(metaObject.getLoggerName());
			loggerObject =loggerClass.newInstance();
		} catch (ClassNotFoundException classNotFoundException) {
			List params = new ArrayList();
			params.add(businessObject);
			throw new SevereException(
					"Logger class not found in the classpath.Logger class = "
							+ metaObject.getLoggerName(), params,
					classNotFoundException);
		}
		 catch (InstantiationException instantiationException) {
			List params = new ArrayList();
			params.add(loggerObject);
			throw new SevereException("Logger Instantiation Error", params,
					instantiationException);
		} catch (IllegalAccessException illegalAccessException) {
			List params = new ArrayList();
			params.add(loggerObject);
			throw new SevereException("Logger Instantiation Error", params,
					illegalAccessException);
		}
		return loggerObject;
	}

}

