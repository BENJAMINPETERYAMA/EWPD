/*
 * ContractSearchRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.contract.vo.ContractLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchRequest extends WPDRequest{
	
	public static final int SEARCH_CONTRACT = 1;
	public static final int SEARCH_ALL_VERSION = 2;
	
	private int action;
	
	private String ContractId;
	 
	private ContractLocateCriteriaVO contractLocateCriteriaVO; 
	
	
	
	
	/**
	 * Returns the contractLocateCriteriaVO
	 * @return ContractLocateCriteriaVO contractLocateCriteriaVO.
	 */
	public ContractLocateCriteriaVO getContractLocateCriteriaVO() {
		return contractLocateCriteriaVO;
	}
	/**
	 * Sets the contractLocateCriteriaVO
	 * @param contractLocateCriteriaVO.
	 */
	public void setContractLocateCriteriaVO(
			ContractLocateCriteriaVO contractLocateCriteriaVO) {
		this.contractLocateCriteriaVO = contractLocateCriteriaVO;
	}
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
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return ContractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		ContractId = contractId;
	}
}
