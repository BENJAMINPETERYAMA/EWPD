/*
 * ContractLifeCycleHistoryLogger.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.InformationElement;
import com.wellpoint.wpd.common.bo.MetaObject;
import com.wellpoint.wpd.common.framework.exception.LogException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.exception.bo.StatusChangeValidationException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.ReflectionUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractLifeCycleHistoryLogger extends LifeCycleHistoryLogger {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.bo.LifeCycleHistoryLogger#getHistoryLogEntry(com.wellpoint.wpd.common.bo.BusinessObject,
	 *      com.wellpoint.wpd.common.bo.Audit) Sets the value to HistoryLogEntry
	 *      from Bo and Audit objects Action= Status Id= Key value
	 *  
	 */

	public HistoryLogEntry getHistoryLogEntry(BusinessObject businessObject,
			Audit audit) throws LogException {
		HistoryLogEntry historyLogEntry = new HistoryLogEntry();
		try {
		// Calls a method to get the status from the BO
		historyLogEntry.setAction(getStatus(businessObject));
		
			//	Calls a method to get the keyvalue from the BO
			historyLogEntry.setId(getKeyValue(businessObject));
			historyLogEntry.setTimestamp(audit.getTime());
			historyLogEntry.setUserId(audit.getUser());
			//historyLogEntry.setVersion(businessObject.getVersion());
			historyLogEntry.setDateSegmentId(getDateSegmentId(businessObject));
			historyLogEntry.setEffectiveDate(getEffectiveDate(businessObject));
			historyLogEntry.setType(businessObject.getClass().getName());
			
		} catch (SevereException severeException) {

			Logger.logError(severeException);
			List param = new ArrayList();
			param.add(businessObject);
			
			throw new LogException("Key value is null for the contract object ",param,severeException);
		}
		catch (WPDException wpdException){
			Logger.logError(wpdException);
			
			List param = new ArrayList();
			param.add(businessObject);
			
			new LogException("Metaobject not created for the Business Object ",param,wpdException);
		}

		return historyLogEntry;

	}

	/*
	 * This method will return the status of the object will get the status from
	 * BO by calling a method.
	 *  
	 */
	private String getStatus(BusinessObject businessObject)throws  StatusChangeValidationException{

		String status = null;
		try {
			status = (String) ReflectionUtil
					.getValueOfBusinessAttributeByReflection(businessObject,
							BusinessObject.BUSINESS_OBJECT_STATUS_FIELD_NAME);
			if (status == null)
				throw new StatusChangeValidationException(
						"Status is not populated in the business object", null,
						null);
			return status;
		} catch (SevereException severeException) {
			  throw new StatusChangeValidationException(
			          "Error in retrieving the value of the status attribute",
			  null, severeException);
		}
		
	}

	/*
	 * This method will return the key attribute. key attribute is obtained by
	 * using metadata object. We will get a list of key attributes and will pass
	 * this list to another method to get the key attribute.
	 *  
	 */
	private String getKeyValue(BusinessObject businessObject) throws WPDException {

		MetaObjectFactory metaObjectFactory = (MetaObjectFactory) ObjectFactory
				.getFactory(ObjectFactory.METAOBJECT);

		MetaObject metaObject = null;
		
			metaObject = metaObjectFactory.getMetaObject(businessObject);
			List keyAttributes = metaObject.getKeyAttributes();
			return getKeyValues(businessObject, keyAttributes);
		
		
	}
	

	/*
	 * This method will fetch the key value from the list and return the key
	 * value
	 */
	private String getKeyValues(Object object, List keyAttributes)
			throws SevereException {

		for (int keyCnt = 0; keyCnt < keyAttributes.size(); keyCnt++) {
			InformationElement infoElmnt = (InformationElement) keyAttributes
					.get(keyCnt);

			Object keyValue = null;
			try {
				keyValue = ReflectionUtil
						.getValueOfBusinessAttributeByReflection(object,
								infoElmnt.getElementName());
				if (null != keyValue && !("".equals(keyValue))) {
					return keyValue.toString();

				}

			} catch (SevereException severeException) {
				Logger.logException(severeException);
				throw severeException;
			}
		}
		return null;
	}
	/*
	 * This method will fetch the effectiveDate value from the list and return the key
	 * value
	 */
	private Date getEffectiveDate(BusinessObject businessObject) throws SevereException{
		try{
			return (Date)ReflectionUtil
			.getValueOfBusinessAttributeByReflection(businessObject,
					"effectiveDate");
		}catch(SevereException severeException){
			Logger.logException(severeException);
			throw severeException;
		}
	}	
	/*
	 * This method will fetch the effectiveDate value from the list and return the key
	 * value
	 */
	private int getDateSegmentId(BusinessObject businessObject)throws SevereException{
		try{
			return Integer.parseInt((ReflectionUtil
			.getValueOfBusinessAttributeByReflection(businessObject,
					"dateSegmentSysId")).toString());
		}catch(SevereException severeException){
			Logger.logException(severeException);
			throw severeException;
		}
	}
}