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
public class SaveServiceTypeMappingRequest extends WPDRequest{
	private int action;
	public static final int CREATE_ACTION = 1;
	public static final int UPDATE_ACTION = 2;
	public static final int VIEW_ACTION = 3;
	ServiceTypeMappingVO serviceTypeMappingVO;
	private String bxValuePrev;
	
	List sendInfoCodeList;
	List sendInfoCodeValue;
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
	 * @return Returns the bxValuePrev.
	 */
	public String getBxValuePrev() {
		return bxValuePrev;
	}
	/**
	 * @param bxValuePrev The bxValuePrev to set.
	 */
	public void setBxValuePrev(String bxValuePrev) {
		this.bxValuePrev = bxValuePrev;
	}
	/**
	 * @return Returns the sendInfoCodeList.
	 */
	public List getSendInfoCodeList() {
		return sendInfoCodeList;
	}
	/**
	 * @param sendInfoCodeList The sendInfoCodeList to set.
	 */
	public void setSendInfoCodeList(List sendInfoCodeList) {
		this.sendInfoCodeList = sendInfoCodeList;
	}
	/**
	 * @return Returns the sendInfoCodeValue.
	 */
	public List getSendInfoCodeValue() {
		return sendInfoCodeValue;
	}
	/**
	 * @param sendInfoCodeValue The sendInfoCodeValue to set.
	 */
	public void setSendInfoCodeValue(List sendInfoCodeValue) {
		this.sendInfoCodeValue = sendInfoCodeValue;
	}
}
