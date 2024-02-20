/*
 * Created on Aug 13, 2009
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
public class ProductTierDefnOverrideBO  extends BusinessObject {  
    
    private int productId;    
    private int benefitComponentId;
    private int benefitDefinitionId;
    private int tierDefinitionId;       
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;      
    
    private List productTierDefinitionsList;
    private String tierDescription;
	
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
     * @return Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId The benefitDefinitionId to set.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
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
     * @return Returns the tierDefinitionId.
     */
    public int getTierDefinitionId() {
        return tierDefinitionId;
    }
    /**
     * @param tierDefinitionId The tierDefinitionId to set.
     */
    public void setTierDefinitionId(int tierDefinitionId) {
        this.tierDefinitionId = tierDefinitionId;
    }    
	    
	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		
		return false;

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
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}	
  
    /**
     * @return Returns the productTierDefinitionsList.
     */
    public List getProductTierDefinitionsList() {
        return productTierDefinitionsList;
    }
    /**
     * @param productTierDefinitionsList The productTierDefinitionsList to set.
     */
    public void setProductTierDefinitionsList(List productTierDefinitionsList) {
        this.productTierDefinitionsList = productTierDefinitionsList;
    }
    /**
     * @return Returns the tierDescription.
     */
    public String getTierDescription() {
        return tierDescription;
    }
    /**
     * @param tierDescription The tierDescription to set.
     */
    public void setTierDescription(String tierDescription) {
        this.tierDescription = tierDescription;
    }
}

