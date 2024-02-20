/*
 * Created on Jul 31, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitTierDefinitionVO {
    
	private boolean tierCheckBx;
    private int tierDefnSysId;
    private String dataType;
    private String tierDescription;
    private String tierCode;
    private String benefitName;
    private int benefitDefinitionId;
    private List tierDefinitionsList;
    private List businessDomains;
    private int benefitVersion;  
	
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
	public boolean isTierCheckBx() {
		return tierCheckBx;
	}
	public void setTierCheckBx(boolean tierCheckBx) {
		this.tierCheckBx = tierCheckBx;
	}   
}

