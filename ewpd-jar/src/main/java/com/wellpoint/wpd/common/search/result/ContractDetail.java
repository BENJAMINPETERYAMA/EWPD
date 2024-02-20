/*
 * ContractDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ContractDetail.java 27808 2007-07-20 20:44:15Z U12046 $
 */
public class ContractDetail extends ObjectDetail {
	private Date startDate;
	private String contractId;
	private Date endDate;
	private String contractType;
	private String status;
	private ContractIdentifier ci;
	private int versionNo;
	
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#getIdentifier()
     * @return
     */
    public ObjectIdentifier getIdentifier() {
        return ci;
    } 
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
     * @param identifier
     */
    public void setIdentifier(ObjectIdentifier identifier) {
        if(identifier != null && identifier instanceof ContractIdentifier){
            ci = (ContractIdentifier)identifier;
        }else{
            throw new IllegalArgumentException(
                    "setIdentifier method in ContractDetail.  Parameter is null or of incorrect type "
                            + identifier);
        }
        
    }
}
