/*
 * ContractBenefitComponentHeadings.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitComponentHeadings {
    
//  Level Info
    private int levelSysId;
  
    
    //Line Info
    private int lineSysId;
    private int benefitComponentId;
    private String benefitComponentName;
    private List standardBenefitList;

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
     * Returns the benefitComponentName
     * @return String benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * Sets the benefitComponentName
     * @param benefitComponentName.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
/**
 * Returns the levelSysId
 * @return int levelSysId.
 */
public int getLevelSysId() {
    return levelSysId;
}
/**
 * Sets the levelSysId
 * @param levelSysId.
 */
public void setLevelSysId(int levelSysId) {
    this.levelSysId = levelSysId;
}
    /**
     * Returns the lineSysId
     * @return int lineSysId.
     */
    public int getLineSysId() {
        return lineSysId;
    }
    /**
     * Sets the lineSysId
     * @param lineSysId.
     */
    public void setLineSysId(int lineSysId) {
        this.lineSysId = lineSysId;
    }
    /**
     * Returns the standardBenefitList
     * @return List standardBenefitList.
     */
    public List getStandardBenefitList() {
        return standardBenefitList;
    }
    /**
     * Sets the standardBenefitList
     * @param standardBenefitList.
     */
    public void setStandardBenefitList(List standardBenefitList) {
        this.standardBenefitList = standardBenefitList;
    }
}
