/*
 * Created on Aug 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.vo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitGeneralInformationVO {
    
    private int tierDefnSysId;
    private String dataType;
    private String tierDescription;
    private String tierCode;
    private String benefitName;
    private int benefitDefinitionId;
    private List tierDefinitionsList;
    private List businessDomains;
    private int benefitVersion;  
    private int productId;
    private int productStructureId;
    private int benefitComponentId;
    private int benefitId;
    
    private String ruleId;
	private String ruleDesc;

	
	/** 
	 * The method returns the rule description.
	 * For example ALCOHOL ABUSE DAYCARE
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}
	/**
	 * @param ruleDesc The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	/**
	 * The method returns the ruleId to be persisted in the 
	 * PROD_BNFT_RULE_OVRD table.
	 * For example AADGRPMN1
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
    /**
     * @return Returns the dataType.
     */
    public String getDataType() {
        return dataType;
    }
    /**
     * @param dataType The dataType to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    /**
     * @return Returns the tierCode.
     */
    public String getTierCode() {
        return tierCode;
    }
    /**
     * @param tierCode The tierCode to set.
     */
    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }
    /**
     * @return Returns the tierDefnSysId.
     */
    public int getTierDefnSysId() {
        return tierDefnSysId;
    }
    /**
     * @param tierDefnSysId The tierDefnSysId to set.
     */
    public void setTierDefnSysId(int tierDefnSysId) {
        this.tierDefnSysId = tierDefnSysId;
    }
    /**
     * @return Returns the tierDescription.
     */
    public String getTierDescription() {
        return tierDescription;
    }
    /**
     * @param tierDescription The tierDescription to set.
     */
    public void setTierDescription(String tierDescription) {
        this.tierDescription = tierDescription;
    }
    /**
     * @return Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }
    /**
     * @param benefitName The benefitName to set.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
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
     * @return Returns the tierDefinitionsList.
     */
    public List getTierDefinitionsList() {
        return tierDefinitionsList;
    }
    /**
     * @param tierDefinitionsList The tierDefinitionsList to set.
     */
    public void setTierDefinitionsList(List tierDefinitionsList) {
        this.tierDefinitionsList = tierDefinitionsList;
    }
    /**
     * @return Returns the businessDomains.
     */
    public List getBusinessDomains() {
        return businessDomains;
    }
    /**
     * @param businessDomains The businessDomains to set.
     */
    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }
    /**
     * @return Returns the benefitVersion.
     */
    public int getBenefitVersion() {
        return benefitVersion;
    }
    /**
     * @param benefitVersion The benefitVersion to set.
     */
    public void setBenefitVersion(int benefitVersion) {
        this.benefitVersion = benefitVersion;
    }   
    /**
     * @return Returns the productId.
     */
    public int getProductId() {
        return productId;
    }
    /**
     * @param productId The productId to set.
     */
    public void setProductId(int productId) {
        this.productId = productId;
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
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * @param benefitId The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
}

