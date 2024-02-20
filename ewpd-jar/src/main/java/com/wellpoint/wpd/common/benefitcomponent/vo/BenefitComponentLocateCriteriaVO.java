/*
 * BenefitComponentLocateCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.vo;

import java.util.List;

/**
 * VO for benefit component locate criteria
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentLocateCriteriaVO {

    private String name;

    private List lob;

    private List businessEntity;

    private List businessGroup;

    private List marketBusinessUnit;
    
    private String effeciveDate;

    private String expiryDate;

    private int maxSearchResultSize;


    /**
     * Constructor
     */
    public BenefitComponentLocateCriteriaVO() {
        super();
    }


    /**
     * @return Returns the businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }


    /**
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * @return Returns the effeciveDate.
     */
    public String getEffeciveDate() {
        return effeciveDate;
    }


    /**
     * @param effeciveDate
     *            The effeciveDate to set.
     */
    public void setEffeciveDate(String effeciveDate) {
        this.effeciveDate = effeciveDate;
    }


    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * @param expiryDate
     *            The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * @return Returns the lob.
     */
    public List getLob() {
        return lob;
    }


    /**
     * @param lob
     *            The lob to set.
     */
    public void setLob(List lob) {
        this.lob = lob;
    }


    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }


    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Returns the businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }


    /**
     * @param businessEntity
     *            The businessEntity to set.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * @return Returns the maxSearchResultSize.
     */
    public int getMaxSearchResultSize() {
        return maxSearchResultSize;
    }


    /**
     * @param maxSearchResultSize
     *            The maxSearchResultSize to set.
     */
    public void setMaxSearchResultSize(int maxSearchResultSize) {
        this.maxSearchResultSize = maxSearchResultSize;
    }
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}