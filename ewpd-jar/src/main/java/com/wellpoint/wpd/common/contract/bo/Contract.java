/*
 * Contract.java
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


import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Contract extends BusinessObject{
	 private int contractSysId;

	 private int contractDateSegmentSysId;
	 
	 private String contractId;
	    
	 private int baseDateSegmentSysId;
	    
	 private List businessDomains;
	    
	 private String contractType;
	    
	 private int parentSysId;
	 
	 private String baseContractId;
	 
	 private int baseContractSysId;
	 
	 private Date baseContractDate;
	 
	 private String contractDataFeedIndicator;
	    
	 private List dateSegmentList;
	 
	 //added
	 private List componentList;

	 private String stateCode;
	 
	 private String stateDesc;

	 private String productCode;
	 
	 private int productId;
	 
	 private List selectedHeadingsList;
	 
	 private String effectiveDateForCopy;
	 
	 private List providerSpecialityCodeList;
	 /**
	  * contain all the check in date segments in the contract.It populate while check in is called.
	  */
	 private List checkInDateSegmentList;
	 
	 private String readyForImageRewrite;
	 
	 private String productStatus; 
	 
	    /*
	     * BusinessObject Base class Attributes.  
	     * private int version;
	     * private String status;
	     * private String createdUser;
	     * private Date createdTimestamp;
	     * private String lastUpdatedUser;
	     * private Date lastUpdatedTimestamp;
	     */

	    

    public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

		/**
	 * @return the checkInDateSegmentList
	 */
	public List getCheckInDateSegmentList() {
		return checkInDateSegmentList;
	}

	/**
	 * @param checkInDateSegmentList the checkInDateSegmentList to set
	 */
	public void setCheckInDateSegmentList(List checkInDateSegmentList) {
		this.checkInDateSegmentList = checkInDateSegmentList;
	}

		public boolean equals(BusinessObject object) {
	        // TODO Auto-generated method stub
	        return false;
	    }

	    public int hashCode() {
	        // TODO Auto-generated method stub
	        return 0;
	    }

	    public String toString() {
	        // TODO Auto-generated method stub
	        return null;
	    }    

	   
	/**
	 * Returns the contractDataFeedIndicator
	 * @return String contractDataFeedIndicator.
	 */
	public String getContractDataFeedIndicator() {
		return contractDataFeedIndicator;
	}
	/**
	 * Sets the contractDataFeedIndicator
	 * @param contractDataFeedIndicator.
	 */
	public void setContractDataFeedIndicator(String contractDataFeedIndicator) {
		this.contractDataFeedIndicator = contractDataFeedIndicator;
	}
    /**
     * Returns the componentList
     * @return List componentList.
     */
    public List getComponentList() {
        return componentList;
    }
    /**
     * Sets the componentList
     * @param componentList.
     */
    public void setComponentList(List componentList) {
        this.componentList = componentList;
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
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the contractDateSegmentSysId
	 * @return int contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * Sets the contractDateSegmentSysId
	 * @param contractDateSegmentSysId.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
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
	 * @return Returns the stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}
	/**
	 * @param stateCode The stateCode to set.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * @return Returns the stateDesc.
	 */
	public String getStateDesc() {
		return stateDesc;
	}
	/**
	 * @param stateDesc The stateDesc to set.
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
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
	 * @return Returns the selectedHeadingsList.
	 */
	public List getSelectedHeadingsList() {
		return selectedHeadingsList;
	}
	/**
	 * @param selectedHeadingsList The selectedHeadingsList to set.
	 */
	public void setSelectedHeadingsList(List selectedHeadingsList) {
		this.selectedHeadingsList = selectedHeadingsList;
	}
	/**
	 * @return Returns the effectiveDateForCopy.
	 */
	public String getEffectiveDateForCopy() {
		return effectiveDateForCopy;
	}
	/**
	 * @param effectiveDateForCopy The effectiveDateForCopy to set.
	 */
	public void setEffectiveDateForCopy(String effectiveDateForCopy) {
		this.effectiveDateForCopy = effectiveDateForCopy;
	}


	/**
	 * @return Returns the providerSpecialityCodeList.
	 */
	public List getProviderSpecialityCodeList() {
		return providerSpecialityCodeList;
	}
	/**
	 * @param providerSpecialityCodeList The providerSpecialityCodeList to set.
	 */
	public void setProviderSpecialityCodeList(List providerSpecialityCodeList) {
		this.providerSpecialityCodeList = providerSpecialityCodeList;
	}
	public String getReadyForImageRewrite() {
        return readyForImageRewrite;
  }

  public void setReadyForImageRewrite(String readyForImageRewrite) {
        this.readyForImageRewrite = readyForImageRewrite;
  }

	
	

}
