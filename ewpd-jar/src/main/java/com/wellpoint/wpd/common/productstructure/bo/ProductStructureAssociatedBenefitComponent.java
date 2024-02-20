/*
 * ProductStructureAssociatedBenefitComponent.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureAssociatedBenefitComponent {

    private int benefitComponentId;

    private int productStructureId;

    private String name;

    private int sequenceNum;

    private StandardBenefitBO standardBenefitBO;

    private String createdUser;

    private String timeStamp;

    private int versionNo;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private List lineOfBusiness;

    private List businessEntity;

    private List businessGroup;

    private Date effectiveDate;

    private Date expireyDate;
    
    private boolean showHiddenFlag;
    
    private List benefitComponentDeleteList;

    
	/**
	 * @return Returns the showHiddenFlag.
	 */
	public boolean isShowHiddenFlag() {
		return showHiddenFlag;
	}
	/**
	 * @param showHiddenFlag The showHiddenFlag to set.
	 */
	public void setShowHiddenFlag(boolean showHiddenFlag) {
		this.showHiddenFlag = showHiddenFlag;
	}
    /**
     * @return Returns the timeStamp.
     */
    public String getTimeStamp() {
        return timeStamp;
    }


    /**
     * @param timeStamp
     *            The timeStamp to set.
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     *  
     */
    public ProductStructureAssociatedBenefitComponent() {
        super();
    }


    /**
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */

    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the createdTimestamp
     * 
     * @return Date createdTimestamp.
     */

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * Sets the createdTimestamp
     * 
     * @param createdTimestamp.
     */

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * Returns the createdUser
     * 
     * @return String createdUser.
     */

    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * Sets the createdUser
     * 
     * @param createdUser.
     */

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * Returns the lastUpdatedTimestamp
     * 
     * @return Date lastUpdatedTimestamp.
     */

    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * Sets the lastUpdatedTimestamp
     * 
     * @param lastUpdatedTimestamp.
     */

    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * Returns the lastUpdatedUser
     * 
     * @return String lastUpdatedUser.
     */

    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * Sets the lastUpdatedUser
     * 
     * @param lastUpdatedUser.
     */

    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * Returns the name
     * 
     * @return String name.
     */

    public String getName() {
        return name;
    }


    /**
     * Sets the name
     * 
     * @param name.
     */

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * Returns the sequenceNum
     * 
     * @return int sequenceNum.
     */

    public int getSequenceNum() {
        return sequenceNum;
    }


    /**
     * Sets the sequenceNum
     * 
     * @param sequenceNum.
     */

    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }


    /**
     * Returns the standardBenefitBO
     * 
     * @return StandardBenefitBO standardBenefitBO.
     */

    public StandardBenefitBO getStandardBenefitBO() {
        return standardBenefitBO;
    }


    /**
     * Sets the standardBenefitBO
     * 
     * @param standardBenefitBO.
     */

    public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
        this.standardBenefitBO = standardBenefitBO;
    }


    /**
     * @return Returns the versionNo.
     */
    public int getVersionNo() {
        return versionNo;
    }


    /**
     * @param versionNo
     *            The versionNo to set.
     */
    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
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
     * @return Returns the lineOfBusiness.
     */
    public List getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * @param lineOfBusiness
     *            The lineOfBusiness to set.
     */
    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * @return Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * @return Returns the expireyDate.
     */
    public Date getExpireyDate() {
        return expireyDate;
    }


    /**
     * @param expireyDate
     *            The expireyDate to set.
     */
    public void setExpireyDate(Date expireyDate) {
        this.expireyDate = expireyDate;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitComponentId = ").append(
                this.getBenefitComponentId());
        buffer.append("productStructureId = ").append(
                this.getProductStructureId());
        buffer.append(", name = ").append(name);
        buffer.append(", sequenceNum = ").append(sequenceNum);
        buffer.append(", standardBenefitBO = ").append(standardBenefitBO);
        buffer.append(", createdUser = ").append(this.getCreatedUser());
        buffer.append(", createdTimestamp = ").append(
                this.getCreatedTimestamp());
        buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
        buffer.append(", lastUpdatedTimestamp = ").append(
                this.getLastUpdatedTimestamp());
        return buffer.toString();

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