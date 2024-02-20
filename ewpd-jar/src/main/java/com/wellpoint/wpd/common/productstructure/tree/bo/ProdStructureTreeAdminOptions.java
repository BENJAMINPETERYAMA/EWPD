/*
 * ProdStructureTreeAdminOptions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureTreeAdminOptions {

    private int adminId;

    private int lvlId;

    private String optionDesc;

    private int optionIdentifier;
    
    private int adminLevelOptionAssnId;

    private int entitySysId;
    
    private int benefitComponentId;
    
    private int sequenceNumber;
    
    
    

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
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
    /**
     * @return Returns the lvlId.
     */
    public int getLvlId() {
        return lvlId;
    }


    /**
     * @param lvlId
     *            The lvlId to set.
     */
    public void setLvlId(int lvlId) {
        this.lvlId = lvlId;
    }


    /**
     * @return Returns the optionDesc.
     */
    public String getOptionDesc() {
        return optionDesc;
    }


    /**
     * @param optionDesc
     *            The optionDesc to set.
     */
    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }


    /**
     * @return Returns the adminId.
     */
    public int getAdminId() {
        return adminId;
    }


    /**
     * @param adminId
     *            The adminId to set.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


    /**
     * Returns the optionIdentifier
     * 
     * @return int optionIdentifier.
     */
    public int getOptionIdentifier() {
        return optionIdentifier;
    }


    /**
     * Sets the optionIdentifier
     * 
     * @param optionIdentifier.
     */
    public void setOptionIdentifier(int optionIdentifier) {
        this.optionIdentifier = optionIdentifier;
    }
    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
    }
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
}