/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractRuleValidationRequest extends WPDRequest {

	private int contractSysId;

	private Date effDate;

	private List contractRuleList;

	/**
	 * @return Returns the contractRuleList.
	 */
	public List getContractRuleList() {
		return contractRuleList;
	}

	/**
	 * @param contractRuleList
	 *            The contractRuleList to set.
	 */
	public void setContractRuleList(List contractRuleList) {
		this.contractRuleList = contractRuleList;
	}

	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}

	/**
	 * @param contractSysId
	 *            The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}

	/**
	 * @return Returns the effDate.
	 */
	public Date getEffDate() {
		return effDate;
	}

	/**
	 * @param effDate
	 *            The effDate to set.
	 */
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}