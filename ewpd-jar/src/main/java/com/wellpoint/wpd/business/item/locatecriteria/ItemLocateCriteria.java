/*
 * Created on Jun 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.item.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

public class ItemLocateCriteria extends LocateCriteria{
	private int catalogId;
	private String catalogName;
    private String primaryCode;
    private String secondaryCode;
    private List catalogIdList;
    private String srdaFlag;
    private String description;

    /**
     * @return Returns the catalogIdList.
     * @return List catalogIdList
     */
    public List getCatalogIdList() {
        return catalogIdList;
    }
    /**
     * Sets the catalogIdList
     * @param catalogIdList
     */
    public void setCatalogIdList(List catalogIdList) {
        this.catalogIdList = catalogIdList;
    }
	/**
	 * @return Returns the catalogId.
	 */
	public int getCatalogId() {
		return catalogId;
	}
	/**
	 * @param catalogId The catalogId to set.
	 */
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
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
    /**
     * @return Returns the primaryCode.
     */
    public String getPrimaryCode() {
        return primaryCode;
    }
    /**
     * @param primaryCode The primaryCode to set.
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
     * @param secondaryCode The secondaryCode to set.
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }
	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}
	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
	/**
	 * @return the discription
	 */
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
    
}
