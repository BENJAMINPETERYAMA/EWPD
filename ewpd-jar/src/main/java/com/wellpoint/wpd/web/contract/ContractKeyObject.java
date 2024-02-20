/*
 * ContractKeyObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractKeyObject {
	
	String dsType;
    int contractSysId;
    String contractId;
    int dateSegmentId;
    int contractParentSysId;
    int productId;
    String contractType;
    String status;
    int version;
    int dateSegmentVersion;
    String dateSegmentStatus;
    String state;
    String noteId;
    String effectiveDate;
    String expiryDate;

    boolean testDateupdate = false;
    
//  ---------------------------------getters/setters-----------------------	    
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
    /**
     * Returns the contractId
     * @return String contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    /**
     * Returns the state
     * @return String state.
     */
    public String getState() {
        return state;
    }
    /**
     * Sets the state
     * @param state.
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * Returns the status
     * @return String status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Returns the version
     * @return int version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * Sets the version
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
	/**
	 * Returns the dateSegmentId
	 * @return int dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * Sets the dateSegmentId
	 * @param dateSegmentId.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * Returns the contractParentSysId
	 * @return int contractParentSysId.
	 */
	public int getContractParentSysId() {
		return contractParentSysId;
	}
	/**
	 * Sets the contractParentSysId
	 * @param contractParentSysId.
	 */
	public void setContractParentSysId(int contractParentSysId) {
		this.contractParentSysId = contractParentSysId;
	}

	/**
	 * Returns the contractType
	 * @return String contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * Sets the contractType
	 * @param contractType.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * Returns the testDateupdate
	 * @return boolean testDateupdate.
	 */
	public boolean isTestDateupdate() {
		return testDateupdate;
	}
	/**
	 * Sets the testDateupdate
	 * @param testDateupdate.
	 */
	public void setTestDateupdate(boolean testDateupdate) {
		this.testDateupdate = testDateupdate;
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
	 * @return Returns the dateSegmentStatus.
	 */
	public String getDateSegmentStatus() {
		return dateSegmentStatus;
	}
	/**
	 * @param dateSegmentStatus The dateSegmentStatus to set.
	 */
	public void setDateSegmentStatus(String dateSegmentStatus) {
		this.dateSegmentStatus = dateSegmentStatus;
	}
	/**
	 * @return Returns the dateSegmentVersion.
	 */
	public int getDateSegmentVersion() {
		return dateSegmentVersion;
	}
	/**
	 * @param dateSegmentVersion The dateSegmentVersion to set.
	 */
	public void setDateSegmentVersion(int dateSegmentVersion) {
		this.dateSegmentVersion = dateSegmentVersion;
	}
}
