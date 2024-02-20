/*
 * ContractSpecificInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

//import com.tivoli.pd.jasn1.boolean32;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractSpecificInfoRequest extends ContractRequest {

	private ContractVO contractVO;
	
	private DateSegment dateSegmentVO;
	
	private boolean copyNeeded=false;
	
	private boolean save=true;
	
    /**
     * Returns the dateSegmentVO
     * @return DateSegmentVO dateSegmentVO.
     */
    public DateSegment getDateSegmentVO() {
        return dateSegmentVO;
    }
    /**
     * Sets the dateSegmentVO
     * @param dateSegmentVO.
     */
    public void setDateSegmentVO(DateSegment dateSegmentVO) {
        this.dateSegmentVO = dateSegmentVO;
    }
	/**
	 * Returns the contractVO
	 * @return ContractVO contractVO.
	 */
	public ContractVO getContractVO() {
		return contractVO;
	}
	/**
	 * Sets the contractVO
	 * @param contractVO.
	 */
	public void setContractVO(ContractVO contractVO) {
		this.contractVO = contractVO;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

    /**
     * Returns the copyNeeded
     * @return boolean copyNeeded.
     */
    public boolean isCopyNeeded() {
        return copyNeeded;
    }
    /**
     * Sets the copyNeeded
     * @param copyNeeded.
     */
    public void setCopyNeeded(boolean copyNeeded) {
        this.copyNeeded = copyNeeded;
    }
    /**
     * Returns the save
     * @return boolean save.
     */
    public boolean isSave() {
        return save;
    }
    /**
     * Sets the save
     * @param save.
     */
    public void setSave(boolean save) {
        this.save = save;
    }
}
