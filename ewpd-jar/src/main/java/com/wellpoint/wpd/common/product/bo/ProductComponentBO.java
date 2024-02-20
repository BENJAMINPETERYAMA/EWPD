/*
 * ProductComponentBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductComponentBO {
    private int productKey;
    private int productStructureKey;
    private int componentKey;
    private int sequence;
    private String componentId;
    private int componentVersion;
    private String componentDesc;

    private String lastUpdatedUser;
    private String createdUser;
    private Date createdTimestamp;
    private Date lastUpdatedTimestamp;
    private String message_out;
    
    //added for multiple deleteion 
    private List benefitComponentDeleteList;
    /**
     * List used for get all the benefit component with zero visible benefitComponents.
     */
    private List componentIds;
    
	/**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productKey = ").append(this.getProductKey());
        buffer.append(", productStructureKey = ").append(productStructureKey);
        buffer.append(", componentKey = ").append(componentKey);
        buffer.append(", sequence = ").append(sequence);
        buffer.append(", componentId = ").append(componentId);
        buffer.append(", componentVersion = ").append(componentVersion);
        buffer.append(", componentDesc = ").append(componentDesc);
        buffer.append(", lastUpdatedUser = ").append(lastUpdatedUser);
        buffer.append(", createdUser = ").append(createdUser);
        buffer.append(", createdTimestamp = ").append(createdTimestamp);
        buffer.append(", lastUpdatedTimestamp = ").append(lastUpdatedTimestamp);
        return buffer.toString();
    }
    
    /**
	 * @return the componentIds
	 */
	public List getComponentIds() {
		return componentIds;
	}

	/**
	 * @param componentIds the componentIds to set
	 */
	public void setComponentIds(List componentIds) {
		this.componentIds = componentIds;
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
     * Returns the componentDesc
     * @return String componentDesc.
     */
    public String getComponentDesc() {
        return componentDesc;
    }
    /**
     * Sets the componentDesc
     * @param componentDesc.
     */
    public void setComponentDesc(String componentDesc) {
        this.componentDesc = componentDesc;
    }
    /**
     * Returns the componentId
     * @return String componentId.
     */
    public String getComponentId() {
        return componentId;
    }
    /**
     * Sets the componentId
     * @param componentId.
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
    /**
     * Returns the componentKey
     * @return int componentKey.
     */
    public int getComponentKey() {
        return componentKey;
    }
    /**
     * Sets the componentKey
     * @param componentKey.
     */
    public void setComponentKey(int componentKey) {
        this.componentKey = componentKey;
    }
    /**
     * Returns the componentVersion
     * @return int componentVersion.
     */
    public int getComponentVersion() {
        return componentVersion;
    }
    /**
     * Sets the componentVersion
     * @param componentVersion.
     */
    public void setComponentVersion(int componentVersion) {
        this.componentVersion = componentVersion;
    }
    /**
     * Returns the productKey
     * @return int productKey.
     */
    public int getProductKey() {
        return productKey;
    }
    /**
     * Sets the productKey
     * @param productKey.
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }
    /**
     * Returns the productStructureKey
     * @return int productStructureKey.
     */
    public int getProductStructureKey() {
        return productStructureKey;
    }
    /**
     * Sets the productStructureKey
     * @param productStructureKey.
     */
    public void setProductStructureKey(int productStructureKey) {
        this.productStructureKey = productStructureKey;
    }
    
    /**
     * Returns the sequence
     * @return int sequence.
     */
    public int getSequence() {
        return sequence;
    }
    
    /**
     * Sets the sequence
     * @param sequence.
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
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
	 * @return Returns the message_out.
	 */
	public String getMessage_out() {
		return message_out;
	}
	/**
	 * @param message_out The message_out to set.
	 */
	public void setMessage_out(String message_out) {
		this.message_out = message_out;
	}
	
	/**
	 * @return Returns the benefitComponentDeleteList.
	 */
	public List getBenefitComponentDeleteList() {
		return benefitComponentDeleteList;
	}
	/**
	 * @param benefitComponentDeleteList The benefitComponentDeleteList to set.
	 */
	public void setBenefitComponentDeleteList(List benefitComponentDeleteList) {
		this.benefitComponentDeleteList = benefitComponentDeleteList;
	}
}
