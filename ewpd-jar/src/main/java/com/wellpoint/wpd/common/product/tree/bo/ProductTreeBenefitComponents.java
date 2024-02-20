/*
 * ProductTreeBenefitComponents.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.tree.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeBenefitComponents {
    
    private int benefitComponentId;
    
    private String benefitComponentDesc;
    
    private int productId;
    
    private List benefitList;
    
    private String benefitComponentStatus;
    
    private int benefitComponentVersion;
   
    private String benefitComponentHideFlag; 
    private int dateSegmentId;
    private String benefitHideFlag;
    
    
    private String createdUserId;
    private String lastUpdatedUserId;
    private Date createdTimeStamp;
    private Date lastUpdatedTimeStamp;
    
    private String headerRuleId;
    
    
    /**
     * @return Returns the benefitList.
     */
    public List getBenefitList() {
        return benefitList;
    }
    /**
     * @param benefitList The benefitList to set.
     */
    public void setBenefitList(List benefitList) {
        this.benefitList = benefitList;
    }
    /**
     * Returns the benefitComponentDesc
     * @return String benefitComponentDesc.
     */
    public String getBenefitComponentDesc() {
        return benefitComponentDesc;
    }
    
    /**
     * Sets the benefitComponentDesc
     * @param benefitComponentDesc.
     */
    public void setBenefitComponentDesc(String benefitComponentDesc) {
        this.benefitComponentDesc = benefitComponentDesc;
    }
    
    /**
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    
    /**
     * Returns the productId
     * @return int productId.
     */
    public int getProductId() {
        return productId;
    }
    
    /**
     * Sets the productId
     * @param productId.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    /**
     * @return Returns the benefitComponentStatus.
     */
    public String getBenefitComponentStatus() {
        return benefitComponentStatus;
    }
    /**
     * @param benefitComponentStatus The benefitComponentStatus to set.
     */
    public void setBenefitComponentStatus(String benefitComponentStatus) {
        this.benefitComponentStatus = benefitComponentStatus;
    }
    /**
     * @return Returns the benefitComponentVersion.
     */
    public int getBenefitComponentVersion() {
        return benefitComponentVersion;
    }
    /**
     * @param benefitComponentVersion The benefitComponentVersion to set.
     */
    public void setBenefitComponentVersion(int benefitComponentVersion) {
        this.benefitComponentVersion = benefitComponentVersion;
    }
	/**
	 * @return Returns the benefitComponentHideFlag.
	 */
	public String getBenefitComponentHideFlag() {
		return benefitComponentHideFlag;
	}
	/**
	 * @param benefitComponentHideFlag The benefitComponentHideFlag to set.
	 */
	public void setBenefitComponentHideFlag(String benefitComponentHideFlag) {
		this.benefitComponentHideFlag = benefitComponentHideFlag;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the benefitHideFlag.
	 */
	public String getBenefitHideFlag() {
		return benefitHideFlag;
	}
	/**
	 * @param benefitHideFlag The benefitHideFlag to set.
	 */
	public void setBenefitHideFlag(String benefitHideFlag) {
		this.benefitHideFlag = benefitHideFlag;
	}
	/**
	 * @return Returns the createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * @param createdTimeStamp The createdTimeStamp to set.
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	/**
	 * @return Returns the createdUserId.
	 */
	public String getCreatedUserId() {
		return createdUserId;
	}
	/**
	 * @param createdUserId The createdUserId to set.
	 */
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}
	/**
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * @return Returns the lastUpdatedUserId.
	 */
	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}
	/**
	 * @param lastUpdatedUserId The lastUpdatedUserId to set.
	 */
	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

}
