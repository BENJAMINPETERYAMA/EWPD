/*
 * CreateDateSegmentRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateDateSegmentRequest extends WPDRequest {

	
	private int contractSysId;
	private String comments;
	private String dateEntered;
	private ContractVO contractVO;
	
	private String dsType;
	private boolean copyLegacyNotes;
	
	
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @return Returns the dateEntered.
	 */
	public String getDateEntered() {
		return dateEntered;
	}
	/**
	 * @param dateEntered The dateEntered to set.
	 */
	public void setDateEntered(String dateEntered) {
		this.dateEntered = dateEntered;
	}
	/**
	 * @return Returns the contractVO.
	 */
	public ContractVO getContractVO() {
		return contractVO;
	}
	/**
	 * @param contractVO The contractVO to set.
	 */
	public void setContractVO(ContractVO contractVO) {
		this.contractVO = contractVO;
	}
	/**
	 * @return Returns the dsType.
	 */
	public String getDsType() {
		return dsType;
	}
	/**
	 * @param dsType The dsType to set.
	 */
	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
	/**
	 * @return Returns the copyLegacyNotes.
	 */
	public boolean isCopyLegacyNotes() {
		return copyLegacyNotes;
	}
	/**
	 * @param copyLegacyNotes The copyLegacyNotes to set.
	 */
	public void setCopyLegacyNotes(boolean copyLegacyNotes) {
		this.copyLegacyNotes = copyLegacyNotes;
	}
}
