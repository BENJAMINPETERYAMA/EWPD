/*
 * ProductBenefitDefinitionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitDefinitionLocateCriteria extends LocateCriteria{

    private int benefitId;
    
    private int productId;
    
    private int benefitComponentId;
    
    BenefitMandateBO benefitMandateBO;
    
    private String type;
    
    private int benefitMandateId;
    
    private String benefitLevelHideFlag;
    
    private String benefitLineHideFlag;
    
    private List tierSysIdList;
    
    private int productStructureId;
    
    private int benefitDefinitionId;
    
    /**
     * @return Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId The benefitDefinitionId to set.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
    }
    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }
    /**
     * @param productStructureId The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }
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
     * Returns the benefitId
     * @return int benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * Sets the benefitId
     * @param benefitId.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
    /**
     * Returns the productId
     * @return int productId.
     */
    public int getProductId() {
        return productId;
    }
    /**
     * Sets the productId
     * @param productId.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
	/**
	 * @return Returns the benefitMandateBO.
	 */
	public BenefitMandateBO getBenefitMandateBO() {
		return benefitMandateBO;
	}
	/**
	 * @param benefitMandateBO The benefitMandateBO to set.
	 */
	public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
		this.benefitMandateBO = benefitMandateBO;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * @param benefitMandateId The benefitMandateId to set.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
	/**
	 * @return Returns the benefitLevelHideFlag.
	 */
	public String getBenefitLevelHideFlag() {
		return benefitLevelHideFlag;
	}
	/**
	 * @param benefitLevelHideFlag The benefitLevelHideFlag to set.
	 */
	public void setBenefitLevelHideFlag(String benefitLevelHideFlag) {
		this.benefitLevelHideFlag = benefitLevelHideFlag;
	}
	/**
	 * @return Returns the benefitLineHideFlag.
	 */
	public String getBenefitLineHideFlag() {
		return benefitLineHideFlag;
	}
	/**
	 * @param benefitLineHideFlag The benefitLineHideFlag to set.
	 */
	public void setBenefitLineHideFlag(String benefitLineHideFlag) {
		this.benefitLineHideFlag = benefitLineHideFlag;
	}
	/**
	 * @return Returns the tierSysIdList.
	 */
	public List getTierSysIdList() {
		return tierSysIdList;
	}
	/**
	 * @param tierSysIdList The tierSysIdList to set.
	 */
	public void setTierSysIdList(List tierSysIdList) {
		this.tierSysIdList = tierSysIdList;
	}
}
