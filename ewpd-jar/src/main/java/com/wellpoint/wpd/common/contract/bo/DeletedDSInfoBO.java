/*
 * DeletedDSInfoBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeletedDSInfoBO extends BusinessObject {

	private String contractId;

	private Date dateSegmentEffectiveDate;

	private String deletedTestProdIndicator;

	private int contractSysId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		return null;
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return Returns the dateSegmentEffectiveDate.
	 */
	public Date getDateSegmentEffectiveDate() {
		return dateSegmentEffectiveDate;
	}

	/**
	 * @param dateSegmentEffectiveDate
	 *            The dateSegmentEffectiveDate to set.
	 */
	public void setDateSegmentEffectiveDate(Date dateSegmentEffectiveDate) {
		this.dateSegmentEffectiveDate = dateSegmentEffectiveDate;
	}

	/**
	 * @return Returns the deletedTestProdIndicator.
	 */
	public String getDeletedTestProdIndicator() {
		return deletedTestProdIndicator;
	}

	/**
	 * @param deletedTestProdIndicator
	 *            The deletedTestProdIndicator to set.
	 */
	public void setDeletedTestProdIndicator(String deletedTestProdIndicator) {
		this.deletedTestProdIndicator = deletedTestProdIndicator;
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

}