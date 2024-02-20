/*
 * SortRequest.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SortRequest extends WPDRequest{
	private List objectIdentifiers;
	private String fieldToSort;
	private String sortOrder;
	private String objectType;
	
	public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

	/**
	 * @return Returns the fieldToSort.
	 */
	public String getFieldToSort() {
		return fieldToSort;
	}
	/**
	 * @param fieldToSort The fieldToSort to set.
	 */
	public void setFieldToSort(String fieldToSort) {
		this.fieldToSort = fieldToSort;
	}
	/**
	 * @return Returns the objectIdentifiers.
	 */
	public List getObjectIdentifiers() {
		return objectIdentifiers;
	}
	/**
	 * @param objectIdentifiers The objectIdentifiers to set.
	 */
	public void setObjectIdentifiers(List objectIdentifiers) {
		this.objectIdentifiers = objectIdentifiers;
	}
	/**
	 * @return Returns the sortOrder.
	 */
	public String getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * @return Returns the objectType.
	 */
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType The objectType to set.
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
