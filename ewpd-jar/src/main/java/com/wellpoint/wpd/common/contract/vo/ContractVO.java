/*
 * ContractVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.vo;


import java.util.Date;
import java.util.List;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.wpd.common.contract.bo.ContractStatusBo;




/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractVO {
	 private int contractSysId;
	  
	 private String contractId;
	    
	 private int baseDateSegmentSysId;
	    
	 private List domainList;
	    
	 private String contractType;
	 
	 private String groupSizeDesc;
	    
	 private int parentSysId;
	 
	 private String baseContractId;
	 
	 private int baseContractSysId;
	 
	 private Date baseContractDate;
	    
	 private int productSysId;
	    
	 private List dateSegmentList;
	 
	 private List lobList;
	 
	 private List beList;
	 
	 private List bgList;
	 
	 /*START CARS*/
	 private List mbuList;
	 /*END cARS*/
	 
	 private Date effectiveDate;
		
	 private Date expiryDate;
		
	 private String groupSize;
	 
	 private int dateSegmentSysId;	 
	 
	 private Date dateEntered;
	 
     private int version;
     
     private String State;
     
     private String status;
     
     private String createdUser;
     
     private Date createdTimestamp;
     
     private String lastUpdatedUser;
     
     private Date lastUpdatedTimestamp;

     private String product;
     
     private String headQuartersStateDesc;
     
     private String headQuartersStateCode;
     
     private String productCode;
     
     private int oldProductSysId;
     
     
     private String productStatus;
     
     private String noteStatus;
     
     private String testDateSegment;
     
     private String contractIdForCopy;
     
     private ContractStatusBo contractStatusBo;
     
   
     
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
		 * Returns the parentSysId
		 * @return int parentSysId.
		 */
		public int getParentSysId() {
			return parentSysId;
		}
		/**
		 * Sets the parentSysId
		 * @param parentSysId.
		 */
		public void setParentSysId(int parentSysId) {
			this.parentSysId = parentSysId;
		}
		/**
		 * Returns the productSysId
		 * @return String productSysId.
		 */
		public int getProductSysId() {
			return productSysId;
		}
		/**
		 * Sets the productSysId
		 * @param productSysId.
		 */
		public void setProductSysId(int productSysId) {
			this.productSysId = productSysId;
		}
	
		/**
		 * Returns the dateSegmentList
		 * @return List dateSegmentList.
		 */
		public List getDateSegmentList() {
			return dateSegmentList;
		}
		/**
		 * Sets the dateSegmentList
		 * @param dateSegmentList.
		 */
		public void setDateSegmentList(List dateSegmentList) {
			this.dateSegmentList = dateSegmentList;
		}
		/**
		 * Returns the domainList
		 * @return List domainList.
		 */
		public List getDomainList() {
			return domainList;
		}
		/**
		 * Sets the domainList
		 * @param domainList.
		 */
		public void setDomainList(List domainList) {
			this.domainList = domainList;
		}
		/**
		 * Returns the baseDateSegmentSysId
		 * @return int baseDateSegmentSysId.
		 */
		public int getBaseDateSegmentSysId() {
			return baseDateSegmentSysId;
		}
		/**
		 * Sets the baseDateSegmentSysId
		 * @param baseDateSegmentSysId.
		 */
		public void setBaseDateSegmentSysId(int baseDateSegmentSysId) {
			this.baseDateSegmentSysId = baseDateSegmentSysId;
		}

	
	

    /**
     * Returns the createdTimestamp
     * @return Date createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the lastUpdatedTimestamp
     * @return Date lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
     * Returns the state
     * @return String state.
     */
    public String getState() {
        return State;
    }
    /**
     * Sets the state
     * @param state.
     */
    public void setState(String state) {
        State = state;
    }

	/**
	 * Returns the baseContractDate
	 * @return Date baseContractDate.
	 */
	public Date getBaseContractDate() {
		return baseContractDate;
	}
	/**
	 * Sets the baseContractDate
	 * @param baseContractDate.
	 */
	public void setBaseContractDate(Date baseContractDate) {
		this.baseContractDate = baseContractDate;
	}
	/**
	 * Returns the baseContractId
	 * @return String baseContractId.
	 */
	public String getBaseContractId() {
		return baseContractId;
	}
	/**
	 * Sets the baseContractId
	 * @param baseContractId.
	 */
	public void setBaseContractId(String baseContractId) {
		this.baseContractId = baseContractId;
	}


	/**
	 * @return Returns the dateEntered.
	 */
	public Date getDateEntered() {
		return dateEntered;
	}
	/**
	 * @param dateEntered The dateEntered to set.
	 */
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}


	/**
	 * Returns the baseContractSysId
	 * @return int baseContractSysId.
	 */
	public int getBaseContractSysId() {
		return baseContractSysId;
	}
	/**
	 * Sets the baseContractSysId
	 * @param baseContractSysId.
	 */
	public void setBaseContractSysId(int baseContractSysId) {
		this.baseContractSysId = baseContractSysId;
	}

	/**
	 * Returns the beList
	 * @return List beList.
	 */
	public List getBeList() {
		return beList;
	}
	/**
	 * Sets the beList
	 * @param beList.
	 */
	public void setBeList(List beList) {
		this.beList = beList;
	}
	/**
	 * Returns the bgList
	 * @return List bgList.
	 */
	public List getBgList() {
		return bgList;
	}
	/**
	 * Sets the bgList
	 * @param bgList.
	 */
	public void setBgList(List bgList) {
		this.bgList = bgList;
	}
	/**
	 * Returns the lobList
	 * @return List lobList.
	 */
	public List getLobList() {
		return lobList;
	}
	/**
	 * Sets the lobList
	 * @param lobList.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}
	/**
	 * Returns the effectiveDate
	 * @return Date effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return Date expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the groupSize
	 * @return String groupSize.
	 */
	public String getGroupSize() {
		return groupSize;
	}
	/**
	 * Sets the groupSize
	 * @param groupSize.
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}
	/**
	 * Returns the groupSizeDesc
	 * @return String groupSizeDesc.
	 */
	public String getGroupSizeDesc() {
		return groupSizeDesc;
	}
	/**
	 * Sets the groupSizeDesc
	 * @param groupSizeDesc.
	 */
	public void setGroupSizeDesc(String groupSizeDesc) {
		this.groupSizeDesc = groupSizeDesc;
	}
	/**
	 * Returns the dateSegmentSysId
	 * @return int dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * Sets the dateSegmentSysId
	 * @param dateSegmentSysId.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	   
	/**
	 * @return Returns the product.
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product The product to set.
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return Returns the headQuartersStateCode.
	 */
	public String getHeadQuartersStateCode() {
		return headQuartersStateCode;
	}
	/**
	 * @param headQuartersStateCode The headQuartersStateCode to set.
	 */
	public void setHeadQuartersStateCode(String headQuartersStateCode) {
		this.headQuartersStateCode = headQuartersStateCode;
	}
	/**
	 * @return Returns the headQuartersStateDesc.
	 */
	public String getHeadQuartersStateDesc() {
		return headQuartersStateDesc;
	}
	/**
	 * @param headQuartersStateDesc The headQuartersStateDesc to set.
	 */
	public void setHeadQuartersStateDesc(String headQuartersStateDesc) {
		this.headQuartersStateDesc = headQuartersStateDesc;
	}
	/**
	 * @return Returns the productCode.
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * @param productCode The productCode to set.
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Returns the oldProductSysId
	 * @return int oldProductSysId.
	 */
	public int getOldProductSysId() {
		return oldProductSysId;
	}
	/**
	 * Sets the oldProductSysId
	 * @param oldProductSysId.
	 */
	public void setOldProductSysId(int oldProductSysId) {
		this.oldProductSysId = oldProductSysId;
	}
	/**
	 * Returns the noteStatus
	 * @return String noteStatus.
	 */
	public String getNoteStatus() {
		return noteStatus;
	}
	/**
	 * Sets the noteStatus
	 * @param noteStatus.
	 */
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	/**
	 * Returns the productStatus
	 * @return String productStatus.
	 */
	public String getProductStatus() {
		return productStatus;
	}
	/**
	 * Sets the productStatus
	 * @param productStatus.
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * Returns the testDateSegment
	 * @return String testDateSegment.
	 */
	public String getTestDateSegment() {
		return testDateSegment;
	}
	/**
	 * Sets the testDateSegment
	 * @param testDateSegment.
	 */
	public void setTestDateSegment(String testDateSegment) {
		this.testDateSegment = testDateSegment;
	}
	
	/**
	 * @return Returns the contractIdForCopy.
	 */
	public String getContractIdForCopy() {
		return contractIdForCopy;
	}
	/**
	 * @param contractIdForCopy The contractIdForCopy to set.
	 */
	public void setContractIdForCopy(String contractIdForCopy) {
		this.contractIdForCopy = contractIdForCopy;
	}
	
	/*START CARS*/
	/**
	 * @return Returns the mbuList.
	 */
	public List getMbuList() {
		return mbuList;
	}
	/**
	 * @param mbuList The mbuList to set.
	 */
	public void setMbuList(List mbuList) {
		this.mbuList = mbuList;
	}
	/*START CARS*/
	//added for contract status
	public ContractStatusBo getContractStatusBo() {
		return contractStatusBo;
	}
	public void setContractStatusBo(ContractStatusBo contractStatusBo) {
		this.contractStatusBo = contractStatusBo;
	}	
}
