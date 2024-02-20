/*
 * SaveServiceTypeMappingRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.request;

import java.util.List;

import com.wellpoint.wpd.common.blueexchange.vo.ServiceTypeMappingVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteServiceTypeMappingRequest extends WPDRequest{
	private int action;
	private List codeList;
	private String ruleId;
	ServiceTypeMappingVO serviceTypeMappingVO;
	/**
	 * @return Returns the serviceTypeMappingVO.
	 */
	public ServiceTypeMappingVO getServiceTypeMappingVO() {
		return serviceTypeMappingVO;
	}
	/**
	 * @param serviceTypeMappingVO The serviceTypeMappingVO to set.
	 */
	public void setServiceTypeMappingVO(
			ServiceTypeMappingVO serviceTypeMappingVO) {
		this.serviceTypeMappingVO = serviceTypeMappingVO;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
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
	 * @return Returns the codeList.
	 */
	public List getCodeList() {
		return codeList;
	}
	/**
	 * @param codeList The codeList to set.
	 */
	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
}
