/*
 * SaveMigGeneralInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;

import java.util.Date;
import java.util.List;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveMigGeneralInfoRequest extends MigrationContractRequest{
    
	public static final int BUSINESS_DOMAIN_CHANGE = 1;
	
	private int action;
	
    private int dateSegmentSysId;
    
    private int contractSysId;
    
    private String headQuartersStateDesc;
    
    private String headQuartersStateCode;
    
    private List  lineOfBusinessList;
    
    private List businessEntityList;
    
    private List businessGroupList;
    /*START CARS*/
    private List marketBusinessUnitList;
    /*END CARS*/
    private List domainList;
    
    private String baseContractId;
    
    private int baseDateSegmentSysId;
    
    private String contractId; 
    
    private Date startDate;
    
    private Date endDate;
    
        
    private String contractType;
    
    private String groupSize;
    
    private String groupSizeDesc;
    
    private String groupSizeDiv;
    
    private String headQuartersState;
    
	private String productCode;
	
	private String standardPlanCode;
	
	private String benefitPlan;
	
	private String groupName;
	
	private String corporatePlanCode;
	
	private String fundingArrangement;
	
	private String brandName;
	
	private Date legacyStartDate;
	
	public static final int SAVE_MIG_GENERAL_INFO =1;
	
	public static final int RETRIEVE_PRODUCT_ATTACHED = 2;
	public static final int RETRIEVE_MAPPED_PRODUCT_ID = 3;
	public static final int RETRIEVE_BY_CY_CONFLICT_MESSAGE = 5;
	public static final int RETRIEVE_PRODUCT_PARENT_SYS_ID = 6;

	private String cobAdjudInd;
	private String medicareAdjudInd;
	private String itsHomeAdjInd;
    
	private String regulatoryAgency;
	private String complianceStatus;
	private String prodProjNameCode;
	private String contractTermDate;
	private String multiplanIndicator;
	private String performanceGuarantee;
	private String salesMarketDate;
	
	private String system;
	private String option;
	private String doneFlag;
	
	private int baseContractProductSysId;

    
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
     * Returns the domainList
     * @return List domainList.
     */
    public List getDomainList() {
        return domainList;
    }
    /**
     * Sets the domainList
     * @param domainList.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
    /**
     * Returns the businessEntityList
     * @return List businessEntityList.
     */
    public List getBusinessEntityList() {
        return businessEntityList;
    }
    /**
     * Sets the businessEntityList
     * @param businessEntityList.
     */
    public void setBusinessEntityList(List businessEntityList) {
        this.businessEntityList = businessEntityList;
    }
    /**
     * Returns the businessGroupList
     * @return List businessGroupList.
     */
    public List getBusinessGroupList() {
        return businessGroupList;
    }
    /**
     * Sets the businessGroupList
     * @param businessGroupList.
     */
    public void setBusinessGroupList(List businessGroupList) {
        this.businessGroupList = businessGroupList;
    }
    /**
     * Returns the lineOfBusinessList
     * @return List lineOfBusinessList.
     */
    public List getLineOfBusinessList() {
        return lineOfBusinessList;
    }
    /**
     * Sets the lineOfBusinessList
     * @param lineOfBusinessList.
     */
    public void setLineOfBusinessList(List lineOfBusinessList) {
        this.lineOfBusinessList = lineOfBusinessList;
    }
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    /**
     * Returns the dateSegmentSysId
     * @return int dateSegmentSysId.
     */
    public int getDateSegmentSysId() {
        return dateSegmentSysId;
    }
    /**
     * Sets the dateSegmentSysId
     * @param dateSegmentSysId.
     */
    public void setDateSegmentSysId(int dateSegmentSysId) {
        this.dateSegmentSysId = dateSegmentSysId;
    }
    /**
     * Returns the groupSizeDesc
     * @return String groupSizeDesc.
     */
    public String getGroupSizeDesc() {
        return groupSizeDesc;
    }
    /**
     * Sets the groupSizeDesc
     * @param groupSizeDesc.
     */
    public void setGroupSizeDesc(String groupSizeDesc) {
        this.groupSizeDesc = groupSizeDesc;
    }
    /**
     * Returns the groupSize
     * @return String groupSize.
     */
    public String getGroupSize() {
        return groupSize;
    }
    /**
     * Sets the groupSize
     * @param groupSize.
     */
    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }
    
    /**
     * Returns the benefitPlan
     * @return String benefitPlan.
     */
    public String getBenefitPlan() {
        return benefitPlan;
    }
    /**
     * Sets the benefitPlan
     * @param benefitPlan.
     */
    public void setBenefitPlan(String benefitPlan) {
        this.benefitPlan = benefitPlan;
    }
    /**
     * Returns the brandName
     * @return String brandName.
     */
    public String getBrandName() {
        return brandName;
    }
    /**
     * Sets the brandName
     * @param brandName.
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
     * Returns the corporatePlanCode
     * @return String corporatePlanCode.
     */
    public String getCorporatePlanCode() {
        return corporatePlanCode;
    }
    /**
     * Sets the corporatePlanCode
     * @param corporatePlanCode.
     */
    public void setCorporatePlanCode(String corporatePlanCode) {
        this.corporatePlanCode = corporatePlanCode;
    }
    
    
    /**
     * Returns the fundingArrangement
     * @return String fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }
    /**
     * Sets the fundingArrangement
     * @param fundingArrangement.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }
    /**
     * Returns the groupName
     * @return String groupName.
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * Sets the groupName
     * @param groupName.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * Returns the groupSizeDiv
     * @return String groupSizeDiv.
     */
    public String getGroupSizeDiv() {
        return groupSizeDiv;
    }
    /**
     * Sets the groupSizeDiv
     * @param groupSizeDiv.
     */
    public void setGroupSizeDiv(String groupSizeDiv) {
        this.groupSizeDiv = groupSizeDiv;
    }
    /**
     * Returns the headQuartersState
     * @return String headQuartersState.
     */
    public String getHeadQuartersState() {
        return headQuartersState;
    }
    /**
     * Sets the headQuartersState
     * @param headQuartersState.
     */
    public void setHeadQuartersState(String headQuartersState) {
        this.headQuartersState = headQuartersState;
    }
   
    /**
     * Returns the headQuartersStateCode
     * @return String headQuartersStateCode.
     */
    public String getHeadQuartersStateCode() {
        return headQuartersStateCode;
    }
    /**
     * Sets the headQuartersStateCode
     * @param headQuartersStateCode.
     */
    public void setHeadQuartersStateCode(String headQuartersStateCode) {
        this.headQuartersStateCode = headQuartersStateCode;
    }
    /**
     * Returns the headQuartersStateDesc
     * @return String headQuartersStateDesc.
     */
    public String getHeadQuartersStateDesc() {
        return headQuartersStateDesc;
    }
    /**
     * Sets the headQuartersStateDesc
     * @param headQuartersStateDesc.
     */
    public void setHeadQuartersStateDesc(String headQuartersStateDesc) {
        this.headQuartersStateDesc = headQuartersStateDesc;
    }
    /**
     * Returns the productCode
     * @return String productCode.
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * Sets the productCode
     * @param productCode.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**
     * Returns the standardPlanCode
     * @return String standardPlanCode.
     */
    public String getStandardPlanCode() {
        return standardPlanCode;
    }
    /**
     * Sets the standardPlanCode
     * @param standardPlanCode.
     */
    public void setStandardPlanCode(String standardPlanCode) {
        this.standardPlanCode = standardPlanCode;
    }
    
    /**
     * Returns the endDate
     * @return Date endDate.
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * Sets the endDate
     * @param endDate.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * Returns the startDate
     * @return Date startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Sets the startDate
     * @param startDate.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the cobAdjudInd.
	 */
	public String getCobAdjudInd() {
		return cobAdjudInd;
	}
	/**
	 * @param cobAdjudInd The cobAdjudInd to set.
	 */
	public void setCobAdjudInd(String cobAdjudInd) {
		this.cobAdjudInd = cobAdjudInd;
	}
	/**
	 * @return Returns the itsHomeAdjInd.
	 */
	public String getItsHomeAdjInd() {
		return itsHomeAdjInd;
	}
	/**
	 * @param itsHomeAdjInd The itsHomeAdjInd to set.
	 */
	public void setItsHomeAdjInd(String itsHomeAdjInd) {
		this.itsHomeAdjInd = itsHomeAdjInd;
	}
	/**
	 * @return Returns the medicareAdjudInd.
	 */
	public String getMedicareAdjudInd() {
		return medicareAdjudInd;
	}
	/**
	 * @param medicareAdjudInd The medicareAdjudInd to set.
	 */
	public void setMedicareAdjudInd(String medicareAdjudInd) {
		this.medicareAdjudInd = medicareAdjudInd;
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
	 * @return Returns the contractTermDate.
	 */
	public String getContractTermDate() {
		return contractTermDate;
	}
	/**
	 * @param contractTermDate The contractTermDate to set.
	 */
	public void setContractTermDate(String contractTermDate) {
		this.contractTermDate = contractTermDate;
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
	 * @return Returns the salesMarketDate.
	 */
	public String getSalesMarketDate() {
		return salesMarketDate;
	}
	/**
	 * @param salesMarketDate The salesMarketDate to set.
	 */
	public void setSalesMarketDate(String salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}
	/**
	 * @return Returns the bUSINESS_DOMAIN_CHANGE.
	 */
	public static int getBUSINESS_DOMAIN_CHANGE() {
		return BUSINESS_DOMAIN_CHANGE;
	}
	/**
	 * @return Returns the doneFlag.
	 */
	public String getDoneFlag() {
		return doneFlag;
	}
	/**
	 * @param doneFlag The doneFlag to set.
	 */
	public void setDoneFlag(String doneFlag) {
		this.doneFlag = doneFlag;
	}
	/**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
	}
	/**
	 * @return Returns the system.
	 */
	public String getSystem() {
		return system;
	}
	/**
	 * @param system The system to set.
	 */
	public void setSystem(String system) {
		this.system = system;
	}
	/**
	 * @return Returns the baseContractProductSysId.
	 */
	public int getBaseContractProductSysId() {
		return baseContractProductSysId;
	}
	/**
	 * @param baseContractProductSysId The baseContractProductSysId to set.
	 */
	public void setBaseContractProductSysId(int baseContractProductSysId) {
		this.baseContractProductSysId = baseContractProductSysId;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}
