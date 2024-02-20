/*
 * Created on Oct 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleIdBO  extends BusinessObject {  
    
    private int benefitId;
    private String ruleId;
    private String ruleDesc;
    private String ruleType;
    private int benefitComponentId;
    private int productId;
    private String lastUpdatedUser;
    private Date lastUpdatedTimeStamp;
    private String createdUser;
    private Date createdTimeStamp;
    
    private List ruleNameList;
    private List ruleIdList;   

    /**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {		
		return false;

	}
	
	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();		
		return buffer.toString();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
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
     * @return Returns the ruleDesc.
     */
    public String getRuleDesc() {
        return ruleDesc;
    }
    /**
     * @param ruleDesc The ruleDesc to set.
     */
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
    /**
     * @return Returns the ruleId.
     */
    public String getRuleId() {
        return ruleId;
    }
    /**
     * @param ruleId The ruleId to set.
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
    /**
     * @return Returns the ruleIdList.
     */
    public List getRuleIdList() {
        return ruleIdList;
    }
    /**
     * @param ruleIdList The ruleIdList to set.
     */
    public void setRuleIdList(List ruleIdList) {
        this.ruleIdList = ruleIdList;
    }
    /**
     * @return Returns the ruleNameList.
     */
    public List getRuleNameList() {
        return ruleNameList;
    }
    /**
     * @param ruleNameList The ruleNameList to set.
     */
    public void setRuleNameList(List ruleNameList) {
        this.ruleNameList = ruleNameList;
    }
   
	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
}

