/*
 * SaveReservedContractRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.contract.vo.ReserveContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveReservedContractRequest extends WPDRequest {

	public static final int CREATE_RESERVED_CONTRACT = 1;
	public static final int UPDATE_RESERVED_CONTRACT = 2;
	private int action;
	private ReserveContractVO reservedContractVO; 
	List contractIdList;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the reservedContractVO.
	 */
	public ReserveContractVO getReservedContractVO() {
		return reservedContractVO;
	}
	/**
	 * @param reservedContractVO The reservedContractVO to set.
	 */
	public void setReservedContractVO(ReserveContractVO reservedContractVO) {
		this.reservedContractVO = reservedContractVO;
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
	 * @return Returns the contractIdList.
	 */
	public List getContractIdList() {
		return contractIdList;
	}
	/**
	 * @param contractIdList The contractIdList to set.
	 */
	public void setContractIdList(List contractIdList) {
		this.contractIdList = contractIdList;
	}
}
