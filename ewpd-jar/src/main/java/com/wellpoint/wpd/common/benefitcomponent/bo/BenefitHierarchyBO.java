/*
 * BenefitHierarchyBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import java.util.List;

/**
 * Business Object for BenefitHierarchy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyBO {

    private int benefitHierarchyId;

    private int benefitComponentId;

    private List benefitHierarchiesList;

    private String name;

    private int masterVersion;

    private int benefitHierarchyAssociationId;
    // changed nov 26th
    private int benefitId;
    
    private String action;


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
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitHierarchyId = ").append(this.benefitHierarchyId);
        buffer.append(", benefitComponentId = ")
                .append(this.benefitComponentId);
        buffer.append(", benefitHierarchiesList = ").append(
                this.benefitHierarchiesList);
        buffer.append(", name = ").append(this.name);
        buffer.append(", masterVersion = ").append(this.masterVersion);
        buffer.append(", benefitHierarchyAssociationId = ").append(
                this.benefitHierarchyAssociationId);
        return buffer.toString();
    }

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}