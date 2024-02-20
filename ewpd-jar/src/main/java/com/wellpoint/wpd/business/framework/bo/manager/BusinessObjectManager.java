/*
 * BusinessObjectManager.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.manager;

import java.util.Map;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: BusinessObjectManager.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface BusinessObjectManager {
	public BusinessObject checkOut(BusinessObject object,User user) throws WPDException;
	public BusinessObject checkOut(BusinessObject object,User user,int duration) throws WPDException;
	public boolean assign(BusinessObject object, User admin, User user);
	public boolean checkIn(BusinessObject object, User user) throws WPDException;      
	public boolean cancelCheckOut(BusinessObject object, User user) throws WPDException;
	public boolean extendCheckout(BusinessObject object, User user, int duration) throws WPDException;
	public boolean publish(BusinessObject object, User user) throws WPDException;
	public boolean delete(BusinessObject object, User user) throws WPDException ;
	public boolean delete(Object subObject,BusinessObject mainObject, User user) throws WPDException ;
	public boolean approve(BusinessObject object, User user) throws WPDException ;
	public boolean reject(BusinessObject object, User user) throws WPDException ;
	public boolean scheduleForTest(BusinessObject object, User user) throws WPDException ;
	public boolean scheduleForApproval(BusinessObject object, User user)throws WPDException ;
	public boolean testPass(BusinessObject object, User user) throws WPDException;
	public boolean testFail(BusinessObject object, User user) throws WPDException;
	public BusinessObject retrieve(BusinessObject object, User user) throws WPDException;
	public BusinessObject retrieve(BusinessObject object, User user,Map params) throws WPDException;
	public boolean copy(BusinessObject source, BusinessObject target, User user, Map parameters) throws WPDException;
	public boolean copy(BusinessObject source, BusinessObject target, User user) throws WPDException;
	public boolean persist(BusinessObject object, User user,boolean insertFlag) throws WPDException ; 
	public boolean persist(Object object, BusinessObject mainObject,User user,boolean insertFlag ) throws WPDException;
	public boolean transfer(BusinessObject object, User user) throws WPDException;
	public LocateResults locate(LocateCriteria locateCriteria,User user)throws WPDException;
	public boolean changeIdentity(BusinessObject oldBOKey, BusinessObject newBOKey, User user) throws WPDException;
	public boolean scheduleForProduction(BusinessObject object, User user)throws WPDException;
	public boolean unlock(BusinessObject mainObject, User user)throws WPDException;
	public boolean lock(BusinessObject mainObject, User user)throws WPDException;
	public boolean deleteAll(BusinessObject object, User user) throws WPDException;
}
