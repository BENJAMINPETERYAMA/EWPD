/*
 * Created on Jul 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;

/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAssociatedBenefit{
	
    private int productKey;
    private int benefitSeqNo;
    private int benCompSeqNo;
    private int componentId;
    private String componentDesc;

    private String lastUpdatedUser;
    private String createdUser;
    private Date createdTimestamp;
    private Date lastUpdatedTimestamp;
    
    private String benefitName;
    private int benefitId;
    
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	/**
	 * @return Returns the componentDesc.
	 */
	public String getComponentDesc() {
		return componentDesc;
	}
	/**
	 * @param componentDesc The componentDesc to set.
	 */
	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}
	/**
	 * @return Returns the componentId.
	 */
	public int getComponentId() {
		return componentId;
	}
	/**
	 * @param componentId The componentId to set.
	 */
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * @param productKey The productKey to set.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}

	/**
	 * @return Returns the benCompSeqNo.
	 */
	public int getBenCompSeqNo() {
		return benCompSeqNo;
	}
	/**
	 * @param benCompSeqNo The benCompSeqNo to set.
	 */
	public void setBenCompSeqNo(int benCompSeqNo) {
		this.benCompSeqNo = benCompSeqNo;
	}
	/**
	 * @return Returns the benefitSeqNo.
	 */
	public int getBenefitSeqNo() {
		return benefitSeqNo;
	}
	/**
	 * @param benefitSeqNo The benefitSeqNo to set.
	 */
	public void setBenefitSeqNo(int benefitSeqNo) {
		this.benefitSeqNo = benefitSeqNo;
	}
}
