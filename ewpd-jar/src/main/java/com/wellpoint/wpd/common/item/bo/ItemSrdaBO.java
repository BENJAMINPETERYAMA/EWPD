package com.wellpoint.wpd.common.item.bo;

import java.util.Date;

import com.wellpoint.wpd.common.framework.util.StringUtil;

public class ItemSrdaBO implements Comparable{


    
    private int catalogId;

    private String primaryCode;

    private String secondaryCode;

    private String description;

    private String catalogName;

    //	User ID of the Item created.
    private String createdUser;

    //The created time stamp of the Item.
    private Date createdTimestamp;

    //User name of the last updated Item.
    private String lastUpdatedUser;

    //Time stamp of the last updated Item
    private Date lastUpdatedTimestamp;
    //CARS START
    //DESCRIPTION : frequency flag for qualifier
    private String frequencyRequired;    
    //CARS END
    private String hcsCode;  
    
    /**
     * @return Returns the catalogId.
     */
    public int getCatalogId() {
        return catalogId;
    }


    /**
     * @param catalogId
     *            The catalogId to set.
     */
    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }


    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
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
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
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
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * @return Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }


    /**
     * @param primaryCode
     *            The primaryCode to set.
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }


    /**
     * @return Returns the secondaryCode.
     */
    public String getSecondaryCode() {
        return secondaryCode;
    }


    /**
     * @param secondaryCode
     *            The secondaryCode to set.
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }


    /**
     * @return Returns the catalogName.
     */
    public String getCatalogName() {
        return catalogName;
    }


    /**
     * @param catalogName The catalogName to set.
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (!(obj instanceof ItemBO))
			throw new ClassCastException("Item object Expected.");

		ItemBO item = (ItemBO) obj;
		if (StringUtil.isInteger(this.primaryCode)
				&& StringUtil.isInteger(item.getPrimaryCode())) {
			
			int val1 = Integer.parseInt(this.primaryCode);
			int val2 = Integer.parseInt(item.getPrimaryCode());
			return (val1 - val2);
			
		} else if (!StringUtil.isInteger(this.primaryCode)
				&& StringUtil.isInteger(item.getPrimaryCode())) {
			
			return 1;
			
		} else if (StringUtil.isInteger(this.primaryCode)
				&& !StringUtil.isInteger(item.getPrimaryCode())) {
			
			return -1;
			
		} else if (!StringUtil.isInteger(this.primaryCode)
				&& !StringUtil.isInteger(item.getPrimaryCode())) {
			
			int val = this.primaryCode.compareTo(item.getPrimaryCode());
			return val;
		}
		return 0;
	}
	/**
	 * @return Returns the frequencyRequired.
	 */
	public String getFrequencyRequired() {
		return frequencyRequired;
	}
	/**
	 * @param frequencyRequired The frequencyRequired to set.
	 */
	public void setFrequencyRequired(String frequencyRequired) {
		this.frequencyRequired = frequencyRequired;
	}


	/**
	 * @return the hcsCode
	 */
	public String getHcsCode() {
		return hcsCode;
	}


	/**
	 * @param hcsCode the hcsCode to set
	 */
	public void setHcsCode(String hcsCode) {
		this.hcsCode = hcsCode;
	}

}
