/*
 * MigrationContract.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationContract {
	String migrationSystemId;
	String contractId;
	String contractType;
	String system;
	String doneFlag;
	String option;
	String productFamily;
	String ewpdProductSystemId;
	String structreProductMappingId;
	List migrationDateSegments;
	private String createdUser;
	private Date createdTimeStamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimeStamp;
	List DenialAndExclusionList;
	private String baseDateSegmentId;
	private String headQuartersState;
	private String productName;
	private String benefitCompName;
	private String stdBenefitName;	
	private String migratedProdStructureMappingSysID;
	private int LastAccessMigDateSegmentSysID;
	private int baseContrProductSysID ;
	private boolean mappedProductFlag;
	
	private int mgrtdDatesegmentId;
    /**
     * @return mappedProductFlag
     * 
     * Returns the mappedProductFlag.
     */
    public boolean getMappedProductFlag() {
        return this.mappedProductFlag;
    }
    /**
     * @param mappedProductFlag
     * 
     * Sets the mappedProductFlag.
     */
    public void setMappedProductFlag(boolean mappedProductFlag) {
        this.mappedProductFlag = mappedProductFlag;
    }
	//moved to migrated contract master table from DateSegment table
	
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
	 * @return Returns the stdBenefitId.
	 */
	public int getStdBenefitId() {
		return stdBenefitId;
	}
	/**
	 * @param stdBenefitId The stdBenefitId to set.
	 */
	public void setStdBenefitId(int stdBenefitId) {
		this.stdBenefitId = stdBenefitId;
	}
	private int benefitComponentId;
	private int stdBenefitId;
	/**
	 * @return Returns the baseDateSegmentId.
	 */
	public String getBaseDateSegmentId() {
		return baseDateSegmentId;
	}
	/**
	 * @param baseDateSegmentId The baseDateSegmentId to set.
	 */
	public void setBaseDateSegmentId(String baseDateSegmentId) {
		this.baseDateSegmentId = baseDateSegmentId;
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
     * @return ewpdProductSystemId
     * 
     * Returns the ewpdProductSystemId.
     */
    public String getEwpdProductSystemId() {
        return ewpdProductSystemId;
    }
    /**
     * @param ewpdProductSystemId
     * 
     * Sets the ewpdProductSystemId.
     */
    public void setEwpdProductSystemId(String ewpdProductSystemId) {
        this.ewpdProductSystemId = ewpdProductSystemId;
    }
    /**
     * @return structreProductMappingId
     * 
     * Returns the structreProductMappingId.
     */
    public String getStructreProductMappingId() {
        return structreProductMappingId;
    }
    /**
     * @param structreProductMappingId
     * 
     * Sets the structreProductMappingId.
     */
    public void setStructreProductMappingId(String structreProductMappingId) {
        this.structreProductMappingId = structreProductMappingId;
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
     * @return contractId
     * 
     * Returns the contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * @param contractId
     * 
     * Sets the contractId.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * @return contractType
     * 
     * Returns the contractType.
     */
    public String getContractType() {
        return contractType;
    }
    /**
     * @param contractType
     * 
     * Sets the contractType.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
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
     * @return doneFlag
     * 
     * Returns the doneFlag.
     */
    public String getDoneFlag() {
        return doneFlag;
    }
    /**
     * @param doneFlag
     * 
     * Sets the doneFlag.
     */
    public void setDoneFlag(String doneFlag) {
        this.doneFlag = doneFlag;
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
     * @return migrationDateSegments
     * 
     * Returns the migrationDateSegments.
     */
    public List getMigrationDateSegments() {
        return migrationDateSegments;
    }
    /**
     * @param migrationDateSegments
     * 
     * Sets the migrationDateSegments.
     */
    public void setMigrationDateSegments(List migrationDateSegments) {
        this.migrationDateSegments = migrationDateSegments;
    }
   
    /**
     * @return migrationSystemId
     * 
     * Returns the migrationSystemId.
     */
    public String getMigrationSystemId() {
        return migrationSystemId;
    }
    /**
     * @param migrationSystemId
     * 
     * Sets the migrationSystemId.
     */
    public void setMigrationSystemId(String migrationSystemId) {
        this.migrationSystemId = migrationSystemId;
    }
    /**
     * @return option
     * 
     * Returns the option.
     */
    public String getOption() {
        return option;
    }
    /**
     * @param option
     * 
     * Sets the option.
     */
    public void setOption(String option) {
        this.option = option;
    }
  
    
    /**
     * @return system
     * 
     * Returns the system.
     */
    public String getSystem() {
        return system;
    }
    /**
     * @param system
     * 
     * Sets the system.
     */
    public void setSystem(String system) {
        this.system = system;
    }
	/**
	 * Returns the denialAndExclusionList
	 * @return List denialAndExclusionList.
	 */
	public List getDenialAndExclusionList() {
		return DenialAndExclusionList;
	}
	/**
	 * Sets the denialAndExclusionList
	 * @param denialAndExclusionList.
	 */
	public void setDenialAndExclusionList(List denialAndExclusionList) {
		DenialAndExclusionList = denialAndExclusionList;
	}

	/**
	 * @return Returns the benefitCompName.
	 */
	public String getBenefitCompName() {
		return benefitCompName;
	}
	/**
	 * @param benefitCompName The benefitCompName to set.
	 */
	public void setBenefitCompName(String benefitCompName) {
		this.benefitCompName = benefitCompName;
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
	/**
	 * @return Returns the stdBenefitName.
	 */
	public String getStdBenefitName() {
		return stdBenefitName;
	}
	/**
	 * @param stdBenefitName The stdBenefitName to set.
	 */
	public void setStdBenefitName(String stdBenefitName) {
		this.stdBenefitName = stdBenefitName;
	}

	/**
	 * Returns the migratedProdStructureMappingSysID
	 * @return int migratedProdStructureMappingSysID.
	 */
	public String getMigratedProdStructureMappingSysID() {
		return migratedProdStructureMappingSysID;
	}
	/**
	 * Sets the migratedProdStructureMappingSysID
	 * @param migratedProdStructureMappingSysID.
	 */
	public void setMigratedProdStructureMappingSysID(
			String migratedProdStructureMappingSysID) {
		this.migratedProdStructureMappingSysID = migratedProdStructureMappingSysID;
	}

    /**
     * @return Returns the lastAccessMigDateSegmentSysID.
     */
    public int getLastAccessMigDateSegmentSysID() {
        return LastAccessMigDateSegmentSysID;
    }
    /**
     * @param lastAccessMigDateSegmentSysID The lastAccessMigDateSegmentSysID to set.
     */
    public void setLastAccessMigDateSegmentSysID(
            int lastAccessMigDateSegmentSysID) {
        LastAccessMigDateSegmentSysID = lastAccessMigDateSegmentSysID;
    }
    /**
     * @return baseContrProductSysID
     * 
     * Returns the baseContrProductSysID.
     */
    public int getBaseContrProductSysID() {
        return baseContrProductSysID;
    }
    /**
     * @param baseContrProductSysID
     * 
     * Sets the baseContrProductSysID.
     */
    public void setBaseContrProductSysID(int baseContrProductSysID) {
        this.baseContrProductSysID = baseContrProductSysID;
    }
	/**
	 * @return Returns the mgrtdDatesegmentId.
	 */
	public int getMgrtdDatesegmentId() {
		return mgrtdDatesegmentId;
	}
	/**
	 * @param mgrtdDatesegmentId The mgrtdDatesegmentId to set.
	 */
	public void setMgrtdDatesegmentId(int mgrtdDatesegmentId) {
		this.mgrtdDatesegmentId = mgrtdDatesegmentId;
	}
}
