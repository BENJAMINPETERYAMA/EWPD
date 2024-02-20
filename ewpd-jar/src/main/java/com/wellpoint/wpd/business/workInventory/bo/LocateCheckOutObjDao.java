/*
 * LocateCheckOutObjDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.workInventory.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateCheckOutObjDao.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public interface LocateCheckOutObjDao {

	public List retrieveChkOutObjList(CheckOutLocateCriteria locateCriteria);
}