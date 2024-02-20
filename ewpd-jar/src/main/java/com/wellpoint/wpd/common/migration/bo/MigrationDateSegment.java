/*
 * MigrationDateSegment.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationDateSegment {
    
	private int systemId;
	private int contractSysId;
	private String contractId;
	private String contractType;
	private Date effectiveDate;
	private Date expiryDate;
	private String groupSize;
	private String productCode;
	private String productCodeDesc;
	private String standardPlanCode;
	private String standardPlanCodeDesc;
	private String benefitPlan;
	private String corporatePlanCode;
	private String corporatePlanCodeDesc;
	private String brandName;
	private String fundingArrangement;
	private String productFamily;
	private String fundingArrangementDesc;
	private String groupSizeDesc;
	private String headQuartersState;
	private String headQuartersStateDesc;
	private String createdUser;
	private Date createdTimeStamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimeStamp;
	
	private String baseContractId;
	private int baseContractSysId;
	private int baseDateSegmentSysId;
	private Date baseDateSegmentEffectiveDate;
	
	private String lastAccessedPage;
	private String stepCompleted;
	private Date legacyStartDate;
	private String mappingSysId;
	
	private String cobAdjudInd;
	private String medicareAdjudInd;
	private String itsHomeAdjInd;
	
	private String regulatoryAgency;
	private String regulatoryAgencyDesc;
	private String complianceStatus;
	private String prodProjNameCode;
	private String prodProjNameCodeDesc;
	private Date contractTermDate;
	private String multiplanIndicator;
	private String performanceGuarantee;
	private Date salesMarketDate;
	private String contractNoteMigrateFlag;
	private int productParentSysId;
	private int ewpdProductSystemId;
	private int mgrtdProductStructureMappingSysId;

	private boolean autoPopulateValues = false; 
	
	private String productName;
	
	/**
	 * @return Returns the contractNoteMigrateFlag.
	 */
	public String getContractNoteMigrateFlag() {
		return contractNoteMigrateFlag;
	}
	/**
	 * @param contractNoteMigrateFlag The contractNoteMigrateFlag to set.
	 */
	public void setContractNoteMigrateFlag(String contractNoteMigrateFlag) {
		this.contractNoteMigrateFlag = contractNoteMigrateFlag;
	}
    /**
     * @return cobAdjudInd
     * 
     * Returns the cobAdjudInd.
     */
    public String getCobAdjudInd() {
        return cobAdjudInd;
    }
    /**
     * @param cobAdjudInd
     * 
     * Sets the cobAdjudInd.
     */
    public void setCobAdjudInd(String cobAdjudInd) {
        this.cobAdjudInd = cobAdjudInd;
    }
    /**
     * @return itsHomeAdjInd
     * 
     * Returns the itsHomeAdjInd.
     */
    public String getItsHomeAdjInd() {
        return itsHomeAdjInd;
    }
    /**
     * @param itsHomeAdjInd
     * 
     * Sets the itsHomeAdjInd.
     */
    public void setItsHomeAdjInd(String itsHomeAdjInd) {
        this.itsHomeAdjInd = itsHomeAdjInd;
    }
    /**
     * @return medicareAdjudInd
     * 
     * Returns the medicareAdjudInd.
     */
    public String getMedicareAdjudInd() {
        return medicareAdjudInd;
    }
    /**
     * @param medicareAdjudInd
     * 
     * Sets the medicareAdjudInd.
     */
    public void setMedicareAdjudInd(String medicareAdjudInd) {
        this.medicareAdjudInd = medicareAdjudInd;
    }
    /**
     * @return mappingSysId
     * 
     * Returns the mappingSysId.
     */
    public String getMappingSysId() {
        return mappingSysId;
    }
    /**
     * @param mappingSysId
     * 
     * Sets the mappingSysId.
     */
    public void setMappingSysId(String mappingSysId) {
        this.mappingSysId = mappingSysId;
    }
    /**
     * Returns the baseDateSegmentEffectiveDate
     * @return Date baseDateSegmentEffectiveDate.
     */
    public Date getBaseDateSegmentEffectiveDate() {
        return baseDateSegmentEffectiveDate;
    }
    /**
     * Sets the baseDateSegmentEffectiveDate
     * @param baseDateSegmentEffectiveDate.
     */
    public void setBaseDateSegmentEffectiveDate(
            Date baseDateSegmentEffectiveDate) {
        this.baseDateSegmentEffectiveDate = baseDateSegmentEffectiveDate;
    }
	/**
	 * @return Returns the legacyStartDate.
	 */
	public Date getLegacyStartDate() {
		return legacyStartDate;
	}
	/**
	 * @param legacyStartDate The legacyStartDate to set.
	 */
	public void setLegacyStartDate(Date legacyStartDate) {
		this.legacyStartDate = legacyStartDate;
	}
	/**
	 * @return Returns the lastAccessedPage.
	 */
	public String getLastAccessedPage() {
		return lastAccessedPage;
	}
	/**
	 * @param lastAccessedPage The lastAccessedPage to set.
	 */
	public void setLastAccessedPage(String lastAccessedPage) {
		this.lastAccessedPage = lastAccessedPage;
	}
	/**
	 * @return Returns the stepCompleted.
	 */
	public String getStepCompleted() {
		return stepCompleted;
	}
	/**
	 * @param stepCompleted The stepCompleted to set.
	 */
	public void setStepCompleted(String stepCompleted) {
		this.stepCompleted = stepCompleted;
	}
	
	
    /**
     * Returns the baseContractId
     * @return String baseContractId.
     */
    public String getBaseContractId() {
        return baseContractId;
    }
    /**
     * Sets the baseContractId
     * @param baseContractId.
     */
    public void setBaseContractId(String baseContractId) {
        this.baseContractId = baseContractId;
    }
    /**
     * Returns the baseDateSegmentSysId
     * @return int baseDateSegmentSysId.
     */
    public int getBaseDateSegmentSysId() {
        return baseDateSegmentSysId;
    }
    /**
     * Sets the baseDateSegmentSysId
     * @param baseDateSegmentSysId.
     */
    public void setBaseDateSegmentSysId(int baseDateSegmentSysId) {
        this.baseDateSegmentSysId = baseDateSegmentSysId;
    }
    /**
     * Returns the contractType
     * @return String contractType.
     */
    public String getContractType() {
        return contractType;
    }
    /**
     * Sets the contractType
     * @param contractType.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    /**
     * @return benefitPlan
     * 
     * Returns the benefitPlan.
     */
    public String getBenefitPlan() {
        return benefitPlan;
    }
    /**
     * @param benefitPlan
     * 
     * Sets the benefitPlan.
     */
    public void setBenefitPlan(String benefitPlan) {
        this.benefitPlan = benefitPlan;
    }
    /**
     * @return brandName
     * 
     * Returns the brandName.
     */
    public String getBrandName() {
        return brandName;
    }
    /**
     * @param brandName
     * 
     * Sets the brandName.
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    /**
     * @return contractSysId
     * 
     * Returns the contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * @param contractSysId
     * 
     * Sets the contractSysId.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    /**
     * @return corporatePlanCode
     * 
     * Returns the corporatePlanCode.
     */
    public String getCorporatePlanCode() {
        return corporatePlanCode;
    }
    /**
     * @param corporatePlanCode
     * 
     * Sets the corporatePlanCode.
     */
    public void setCorporatePlanCode(String corporatePlanCode) {
        this.corporatePlanCode = corporatePlanCode;
    }
    /**
     * @return corporatePlanCodeDesc
     * 
     * Returns the corporatePlanCodeDesc.
     */
    public String getCorporatePlanCodeDesc() {
        return corporatePlanCodeDesc;
    }
    /**
     * @param corporatePlanCodeDesc
     * 
     * Sets the corporatePlanCodeDesc.
     */
    public void setCorporatePlanCodeDesc(String corporatePlanCodeDesc) {
        this.corporatePlanCodeDesc = corporatePlanCodeDesc;
    }
    /**
     * @return createdTimeStamp
     * 
     * Returns the createdTimeStamp.
     */
    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }
    /**
     * @param createdTimeStamp
     * 
     * Sets the createdTimeStamp.
     */
    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
    /**
     * @return createdUser
     * 
     * Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     * 
     * Sets the createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return effectiveDate
     * 
     * Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * @param effectiveDate
     * 
     * Sets the effectiveDate.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * @return expiryDate
     * 
     * Returns the expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    /**
     * @param expiryDate
     * 
     * Sets the expiryDate.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * @return fundingArrangement
     * 
     * Returns the fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }
    /**
     * @param fundingArrangement
     * 
     * Sets the fundingArrangement.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }
    /**
     * @return fundingArrangementDesc
     * 
     * Returns the fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }
    /**
     * @param fundingArrangementDesc
     * 
     * Sets the fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }
    /**
     * @return groupSize
     * 
     * Returns the groupSize.
     */
    public String getGroupSize() {
        return groupSize;
    }
    /**
     * @param groupSize
     * 
     * Sets the groupSize.
     */
    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }
    /**
     * @return groupSizeDesc
     * 
     * Returns the groupSizeDesc.
     */
    public String getGroupSizeDesc() {
        return groupSizeDesc;
    }
    /**
     * @param groupSizeDesc
     * 
     * Sets the groupSizeDesc.
     */
    public void setGroupSizeDesc(String groupSizeDesc) {
        this.groupSizeDesc = groupSizeDesc;
    }
    /**
     * @return headQuartersState
     * 
     * Returns the headQuartersState.
     */
    public String getHeadQuartersState() {
        return headQuartersState;
    }
    /**
     * @param headQuartersState
     * 
     * Sets the headQuartersState.
     */
    public void setHeadQuartersState(String headQuartersState) {
        this.headQuartersState = headQuartersState;
    }
    /**
     * @return headQuartersStateDesc
     * 
     * Returns the headQuartersStateDesc.
     */
    public String getHeadQuartersStateDesc() {
        return headQuartersStateDesc;
    }
    /**
     * @param headQuartersStateDesc
     * 
     * Sets the headQuartersStateDesc.
     */
    public void setHeadQuartersStateDesc(String headQuartersStateDesc) {
        this.headQuartersStateDesc = headQuartersStateDesc;
    }
    /**
     * @return lastUpdatedTimeStamp
     * 
     * Returns the lastUpdatedTimeStamp.
     */
    public Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }
    /**
     * @param lastUpdatedTimeStamp
     * 
     * Sets the lastUpdatedTimeStamp.
     */
    public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }
    /**
     * @return lastUpdatedUser
     * 
     * Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     * 
     * Sets the lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return productCode
     * 
     * Returns the productCode.
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * @param productCode
     * 
     * Sets the productCode.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**
     * @return productCodeDesc
     * 
     * Returns the productCodeDesc.
     */
    public String getProductCodeDesc() {
        return productCodeDesc;
    }
    /**
     * @param productCodeDesc
     * 
     * Sets the productCodeDesc.
     */
    public void setProductCodeDesc(String productCodeDesc) {
        this.productCodeDesc = productCodeDesc;
    }
    /**
     * @return productFamily
     * 
     * Returns the productFamily.
     */
    public String getProductFamily() {
        return productFamily;
    }
    /**
     * @param productFamily
     * 
     * Sets the productFamily.
     */
    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }
    /**
     * @return standardPlanCode
     * 
     * Returns the standardPlanCode.
     */
    public String getStandardPlanCode() {
        return standardPlanCode;
    }
    /**
     * @param standardPlanCode
     * 
     * Sets the standardPlanCode.
     */
    public void setStandardPlanCode(String standardPlanCode) {
        this.standardPlanCode = standardPlanCode;
    }
    /**
     * @return standardPlanCodeDesc
     * 
     * Returns the standardPlanCodeDesc.
     */
    public String getStandardPlanCodeDesc() {
        return standardPlanCodeDesc;
    }
    /**
     * @param standardPlanCodeDesc
     * 
     * Sets the standardPlanCodeDesc.
     */
    public void setStandardPlanCodeDesc(String standardPlanCodeDesc) {
        this.standardPlanCodeDesc = standardPlanCodeDesc;
    }
    /**
     * @return systemId
     * 
     * Returns the systemId.
     */
    public int getSystemId() {
        return systemId;
    }
    /**
     * @param systemId
     * 
     * Sets the systemId.
     */
    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the baseContractSysId.
	 * @return int baseContractSysId.
	 */
	public int getBaseContractSysId() {
		return baseContractSysId;
	}
	/**
	 * Sets the baseContractSysId.
	 * @param baseContractSysId.
	 */
	public void setBaseContractSysId(int baseContractSysId) {
		this.baseContractSysId = baseContractSysId;
	}
	
	/**
	 * @return Returns the complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}
	/**
	 * @param complianceStatus The complianceStatus to set.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}
	/**
	 * @return Returns the multiplanIndicator.
	 */
	public String getMultiplanIndicator() {
		return multiplanIndicator;
	}
	/**
	 * @param multiplanIndicator The multiplanIndicator to set.
	 */
	public void setMultiplanIndicator(String multiplanIndicator) {
		this.multiplanIndicator = multiplanIndicator;
	}
	/**
	 * @return Returns the performanceGuarantee.
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}
	/**
	 * @param performanceGuarantee The performanceGuarantee to set.
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}
	/**
	 * @return Returns the prodProjNameCode.
	 */
	public String getProdProjNameCode() {
		return prodProjNameCode;
	}
	/**
	 * @param prodProjNameCode The prodProjNameCode to set.
	 */
	public void setProdProjNameCode(String prodProjNameCode) {
		this.prodProjNameCode = prodProjNameCode;
	}
	/**
	 * @return Returns the regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}
	/**
	 * @param regulatoryAgency The regulatoryAgency to set.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}
	/**
	 * @return Returns the contractTermDate.
	 */
	public Date getContractTermDate() {
		return contractTermDate;
	}
	/**
	 * @param contractTermDate The contractTermDate to set.
	 */
	public void setContractTermDate(Date contractTermDate) {
		this.contractTermDate = contractTermDate;
	}
	/**
	 * @return Returns the salesMarketDate.
	 */
	public Date getSalesMarketDate() {
		return salesMarketDate;
	}
	/**
	 * @param salesMarketDate The salesMarketDate to set.
	 */
	public void setSalesMarketDate(Date salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}
	/**
	 * @return Returns the prodProjNameCodeDesc.
	 */
	public String getProdProjNameCodeDesc() {
		return prodProjNameCodeDesc;
	}
	/**
	 * @param prodProjNameCodeDesc The prodProjNameCodeDesc to set.
	 */
	public void setProdProjNameCodeDesc(String prodProjNameCodeDesc) {
		this.prodProjNameCodeDesc = prodProjNameCodeDesc;
	}
	/**
	 * @return Returns the regulatoryAgencyDesc.
	 */
	public String getRegulatoryAgencyDesc() {
		return regulatoryAgencyDesc;
	}
	/**
	 * @param regulatoryAgencyDesc The regulatoryAgencyDesc to set.
	 */
	public void setRegulatoryAgencyDesc(String regulatoryAgencyDesc) {
		this.regulatoryAgencyDesc = regulatoryAgencyDesc;
	}
	public String getStartDateString() {
    	String dateString = null;
        if (this.effectiveDate !=  null) {
        	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        	dateString = df.format(this.effectiveDate);
        }
        return dateString;
	}
	
	public void setStartDateString(String str) {
		
	}
	/**
	 * @return Returns the productParentSysId.
	 */
	public int getProductParentSysId() {
		return productParentSysId;
	}
	/**
	 * @param productParentSysId The productParentSysId to set.
	 */
	public void setProductParentSysId(int productParentSysId) {
		this.productParentSysId = productParentSysId;
	}

	/**
	 * @return Returns the ewpdProductSystemId.
	 */
	public int getEwpdProductSystemId() {
		return ewpdProductSystemId;
	}
	/**
	 * @param ewpdProductSystemId The ewpdProductSystemId to set.
	 */
	public void setEwpdProductSystemId(int ewpdProductSystemId) {
		this.ewpdProductSystemId = ewpdProductSystemId;
	}
	/**
	 * @return Returns the mgrtdProductStructureMappingSysId.
	 */
	public int getMgrtdProductStructureMappingSysId() {
		return mgrtdProductStructureMappingSysId;
	}
	/**
	 * @param mgrtdProductStructureMappingSysId The mgrtdProductStructureMappingSysId to set.
	 */
	public void setMgrtdProductStructureMappingSysId(
			int mgrtdProductStructureMappingSysId) {
		this.mgrtdProductStructureMappingSysId = mgrtdProductStructureMappingSysId;
	}
	/**
	 * @return Returns the autoPopulateValues.
	 */
	public boolean isAutoPopulateValues() {
		return autoPopulateValues;
	}
	/**
	 * @param autoPopulateValues The autoPopulateValues to set.
	 */
	public void setAutoPopulateValues(boolean autoPopulateValues) {
		this.autoPopulateValues = autoPopulateValues;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
