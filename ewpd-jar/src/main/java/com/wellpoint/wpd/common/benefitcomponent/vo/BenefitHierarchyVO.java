/*
 * BenefitHierarchyVO.java
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
 * VO for BenefitHierarchy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyVO {

    private int benefitHierarchyId;

    private int benefitComponentId;

    private int benefitComponentParentId;

    private List benefitHierarchiesList;

    private String name;
    
    private String status;

    private int masterVersion;

    private int benefitHierarchyAssociationId;

    private List businessDomainList;

    private boolean insertFlag;

    // changed 26th Nov
    private int benefitId;

    /**
     * @return benefitId
     * 
     * Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * @param benefitId
     * 
     * Sets the benefitId.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
    // change ends
    /**
     * @return Returns the insertFlag.
     */
    public boolean isInsertFlag() {
        return insertFlag;
    }


    /**
     * @param insertFlag
     *            The insertFlag to set.
     */
    public void setInsertFlag(boolean insertFlag) {
        this.insertFlag = insertFlag;
    }


    /**
     * @return Returns the benefitHierarchyAssociationId.
     */
    public int getBenefitHierarchyAssociationId() {
        return benefitHierarchyAssociationId;
    }


    /**
     * @param benefitHierarchyAssociationId
     *            The benefitHierarchyAssociationId to set.
     */
    public void setBenefitHierarchyAssociationId(
            int benefitHierarchyAssociationId) {
        this.benefitHierarchyAssociationId = benefitHierarchyAssociationId;
    }


    /**
     * @return Returns the masterVersion.
     */
    public int getMasterVersion() {
        return masterVersion;
    }


    /**
     * @param masterVersion
     *            The masterVersion to set.
     */
    public void setMasterVersion(int masterVersion) {
        this.masterVersion = masterVersion;
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
     * @return Returns the benefitHierarchiesList.
     */
    public List getBenefitHierarchiesList() {
        return benefitHierarchiesList;
    }


    /**
     * @param benefitHierarchiesList
     *            The benefitHierarchiesList to set.
     */
    public void setBenefitHierarchiesList(List benefitHierarchiesList) {
        this.benefitHierarchiesList = benefitHierarchiesList;
    }


    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the benefitHierarchyId.
     */
    public int getBenefitHierarchyId() {
        return benefitHierarchyId;
    }


    /**
     * @param benefitHierarchyId
     *            The benefitHierarchyId to set.
     */
    public void setBenefitHierarchyId(int benefitHierarchyId) {
        this.benefitHierarchyId = benefitHierarchyId;
    }


    /**
     * Returns the businessDomainList
     * 
     * @return List businessDomainList.
     */
    public List getBusinessDomainList() {
        return businessDomainList;
    }


    /**
     * Sets the businessDomainList
     * 
     * @param businessDomainList.
     */
    public void setBusinessDomainList(List businessDomainList) {
        this.businessDomainList = businessDomainList;
    }


    /**
     * Returns the benefitComponentParentId
     * 
     * @return int benefitComponentParentId.
     */
    public int getBenefitComponentParentId() {
        return benefitComponentParentId;
    }


    /**
     * Sets the benefitComponentParentId
     * 
     * @param benefitComponentParentId.
     */
    public void setBenefitComponentParentId(int benefitComponentParentId) {
        this.benefitComponentParentId = benefitComponentParentId;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitComponentId = ").append(this.benefitComponentId);
        buffer.append(", benefitComponentParentId = ").append(
                this.benefitComponentParentId);
        buffer.append(", benefitHierarchiesList = ").append(
                this.benefitHierarchiesList);
        buffer.append(", name = ").append(this.name);
        buffer.append(", benefitHierarchyId = ")
                .append(this.benefitHierarchyId);
        buffer.append(", benefitHierarchyAssociationId = ").append(
                this.benefitHierarchyAssociationId);
        buffer.append(", masterVersion = ").append(this.masterVersion);
        buffer.append(", businessDomainList = ")
                .append(this.businessDomainList);
        return buffer.toString();
    }

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}