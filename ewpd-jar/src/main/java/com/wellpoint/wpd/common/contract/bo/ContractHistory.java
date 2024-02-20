/*
 * ContractHistory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractHistory {
    
    private int sourceDateSegmentId;
    
    private String sourceContractCode;
    
    private Date sourceDateSegmentEffectiveDate;
    
    private int targetDateSegmentId;
    
    private String targetContractCode;
    
    private Date targetDateSegmentEffectiveDate; 
    
    private String createdUser;
	
	private Date createdTimeStamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimeStamp;

    /**
     * Returns the sourceContractCode
     * @return String sourceContractCode.
     */
    public String getSourceContractCode() {
        return sourceContractCode;
    }
    /**
     * Sets the sourceContractCode
     * @param sourceContractCode.
     */
    public void setSourceContractCode(String sourceContractCode) {
        this.sourceContractCode = sourceContractCode;
    }
    /**
     * Returns the sourceDateSegmentEffectiveDate
     * @return Date sourceDateSegmentEffectiveDate.
     */
    public Date getSourceDateSegmentEffectiveDate() {
        return sourceDateSegmentEffectiveDate;
    }
    /**
     * Sets the sourceDateSegmentEffectiveDate
     * @param sourceDateSegmentEffectiveDate.
     */
    public void setSourceDateSegmentEffectiveDate(
            Date sourceDateSegmentEffectiveDate) {
        this.sourceDateSegmentEffectiveDate = sourceDateSegmentEffectiveDate;
    }
    /**
     * Returns the sourceDateSegmentId
     * @return int sourceDateSegmentId.
     */
    public int getSourceDateSegmentId() {
        return sourceDateSegmentId;
    }
    /**
     * Sets the sourceDateSegmentId
     * @param sourceDateSegmentId.
     */
    public void setSourceDateSegmentId(int sourceDateSegmentId) {
        this.sourceDateSegmentId = sourceDateSegmentId;
    }
    /**
     * Returns the targetContractCode
     * @return String targetContractCode.
     */
    public String getTargetContractCode() {
        return targetContractCode;
    }
    /**
     * Sets the targetContractCode
     * @param targetContractCode.
     */
    public void setTargetContractCode(String targetContractCode) {
        this.targetContractCode = targetContractCode;
    }
    /**
     * Returns the targetDateSegmentEffectiveDate
     * @return Date targetDateSegmentEffectiveDate.
     */
    public Date getTargetDateSegmentEffectiveDate() {
        return targetDateSegmentEffectiveDate;
    }
    /**
     * Sets the targetDateSegmentEffectiveDate
     * @param targetDateSegmentEffectiveDate.
     */
    public void setTargetDateSegmentEffectiveDate(
            Date targetDateSegmentEffectiveDate) {
        this.targetDateSegmentEffectiveDate = targetDateSegmentEffectiveDate;
    }
    /**
     * Returns the targetDateSegmnetId
     * @return int targetDateSegmnetId.
     */
    public int getTargetDateSegmentId() {
        return targetDateSegmentId;
    }
    /**
     * Sets the targetDateSegmnetId
     * @param targetDateSegmnetId.
     */
    public void setTargetDateSegmentId(int targetDateSegmentId) {
        this.targetDateSegmentId = targetDateSegmentId;
    }
    /**
     * Returns the createdTimeStamp
     * @return Date createdTimeStamp.
     */
    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }
    /**
     * Sets the createdTimeStamp
     * @param createdTimeStamp.
     */
    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the lastUpdatedTimeStamp
     * @return Date lastUpdatedTimeStamp.
     */
    public Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }
    /**
     * Sets the lastUpdatedTimeStamp
     * @param lastUpdatedTimeStamp.
     */
    public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }
    /**
     * Returns the lastUpdatedUser
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
}
