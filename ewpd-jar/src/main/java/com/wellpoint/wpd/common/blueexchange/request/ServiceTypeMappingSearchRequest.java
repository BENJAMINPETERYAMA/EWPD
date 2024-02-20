/*
 * ServiceTypeMappingSearchRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.blueexchange.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingSearchRequest extends WPDRequest{
	
	private List EB03List;
	
	private String isApplicable;
	
	private List headerRuleList;
	
	private int action;
	
	private String status;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the eB03List.
	 */
	public List getEB03List() {
		return EB03List;
	}
	/**
	 * @param list The eB03List to set.
	 */
	public void setEB03List(List list) {
		EB03List = list;
	}
	/**
	 * @return Returns the headerRuleList.
	 */
	public List getHeaderRuleList() {
		return headerRuleList;
	}
	/**
	 * @param headerRuleList The headerRuleList to set.
	 */
	public void setHeaderRuleList(List headerRuleList) {
		this.headerRuleList = headerRuleList;
	}
	/**
	 * @return Returns the isApplicable.
	 */
	public String getIsApplicable() {
		return isApplicable;
	}
	/**
	 * @param isApplicable The isApplicable to set.
	 */
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}
}
