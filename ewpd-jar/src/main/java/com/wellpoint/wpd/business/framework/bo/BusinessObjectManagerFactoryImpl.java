/*
 * BusinessObjectManagerFactoryImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManagerImpl;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessObjectManagerFactoryImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class BusinessObjectManagerFactoryImpl extends ObjectFactory implements
		BusinessObjectManagerFactory {
	
	//private BusinessObjectManagerImpl instance;

	
	public BusinessObjectManager getBusinessObjectManager() {
		
		return new BusinessObjectManagerImpl();
	}	

/*	public BusinessObjectManager getBusinessObjectManager() {
		if (null == instance)
			instance= new BusinessObjectManagerImpl();
		return instance;
	}*/
}
