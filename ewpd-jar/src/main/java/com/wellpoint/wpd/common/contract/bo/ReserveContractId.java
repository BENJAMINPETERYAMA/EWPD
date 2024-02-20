/*
 * ReservedContractIdInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ReserveContractId {
    private String contractId;
    private String status;
    private String createdUser;
    private Date createdTimeStamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimeStamp; 
    private int numberOfIdToGenerate;
	 private List businessDomains;
	 private int parentSysId; 
		private Date startDate;
		private Date endDate;
		private int domainId;
	private List reservedList;	
		
    

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
     * Returns the timeStamp
     * @return Date timeStamp.
     */
    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }
    /**
     * Sets the timeStamp
     * @param timeStamp.
     */
    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
    /**
     * Returns the userId
     * @return String userId.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the userId
     * @param userId.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
	/**
	 * Returns the lastUpdatedTimeStamp
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * @return Returns the numberOfIdToGenerate.
	 */
	public int getNumberOfIdToGenerate() {
		return numberOfIdToGenerate;
	}
	/**
	 * @param numberOfIdToGenerate The numberOfIdToGenerate to set.
	 */
	public void setNumberOfIdToGenerate(int numberOfIdToGenerate) {
		this.numberOfIdToGenerate = numberOfIdToGenerate;
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
	 * @return Returns the parentSysId.
	 */
	public int getParentSysId() {
		return parentSysId;
	}
	/**
	 * @param parentSysId The parentSysId to set.
	 */
	public void setParentSysId(int parentSysId) {
		this.parentSysId = parentSysId;
	}
        /**
         * @return Returns the domainId.
         */
        public int getDomainId() {
            return domainId;
        }
        /**
         * @param domainId The domainId to set.
         */
        public void setDomainId(int domainId) {
            this.domainId = domainId;
        }
    /**
     * Returns the reservedList
     * @return List reservedList.
     */
    public List getReservedList() {
        return reservedList;
    }
    /**
     * Sets the reservedList
     * @param reservedList.
     */
    public void setReservedList(List reservedList) {
        this.reservedList = reservedList;
    }
}
