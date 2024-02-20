/*
 * SaveContractBenefitDefinitionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractBenefitDefinitionRequest extends ContractRequest{

    private List benefitLinesList;

    private List deleteBenefitLevelList;
    
    private int benefitComponentId;
    
    private int dateSegmentId;
    
    private int productSysId;
    
    private List changedBenefitLines;
    
    private String BenefitComponentName;
    
    private int benefitSysId;
    
    private int benefitDefnSysId;
    
	private List benefitTierDefinitionList;
	
	private List benefitTierLevelList;
	
	/* START CARS */
	private List changedTierLineIds;
	
	private List changedTierSysIds;
	/* END CARS */
	private Map notChangedLines;
	private Map changedLines;
	
    
	public List getBenefitTierDefinitionList() {
		return benefitTierDefinitionList;
	}
	public void setBenefitTierDefinitionList(List benefitTierDefinitionList) {
		this.benefitTierDefinitionList = benefitTierDefinitionList;
	}
	public List getBenefitTierLevelList() {
		return benefitTierLevelList;
	}
	public void setBenefitTierLevelList(List benefitTierLevelList) {
		this.benefitTierLevelList = benefitTierLevelList;
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
	 * @return Returns the deleteBenefitLevelList.
	 */
	public List getDeleteBenefitLevelList() {
		return deleteBenefitLevelList;
	}
	/**
	 * @param deleteBenefitLevelList The deleteBenefitLevelList to set.
	 */
	public void setDeleteBenefitLevelList(List deleteBenefitLevelList) {
		this.deleteBenefitLevelList = deleteBenefitLevelList;
	}
    /**
     * Returns the benefitLinesList
     * @return List benefitLinesList.
     */
    public List getBenefitLinesList() {
        return benefitLinesList;
    }
    /**
     * Sets the benefitLinesList
     * @param benefitLinesList.
     */
    public void setBenefitLinesList(List benefitLinesList) {
        this.benefitLinesList = benefitLinesList;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
    
	/**
	 * @return Returns the changedBenefitLines.
	 */
	public List getChangedBenefitLines() {
		return changedBenefitLines;
	}
	/**
	 * @param changedBenefitLines The changedBenefitLines to set.
	 */
	public void setChangedBenefitLines(List changedBenefitLines) {
		this.changedBenefitLines = changedBenefitLines;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return BenefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		BenefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	
	/* START CARS */
	/**
	 * @return the changedTierLineIds
	 */
	public List getChangedTierLineIds() {
		return changedTierLineIds;
	}
	/**
	 * @param changedTierLineIds the changedTierLineIds to set
	 */
	public void setChangedTierLineIds(List changedTierLineIds) {
		this.changedTierLineIds = changedTierLineIds;
	}
	/**
	 * @return the changedTierSysIds
	 */
	public List getChangedTierSysIds() {
		return changedTierSysIds;
	}
	/**
	 * @param changedTierSysIds the changedTierSysIds to set
	 */
	public void setChangedTierSysIds(List changedTierSysIds) {
		this.changedTierSysIds = changedTierSysIds;
	}
	/* END CARS */
	/**
	 * @return Returns the notChangedLines.
	 */
	public Map getNotChangedLines() {
		return notChangedLines;
	}
	/**
	 * @param notChangedLines The notChangedLines to set.
	 */
	public void setNotChangedLines(Map notChangedLines) {
		this.notChangedLines = notChangedLines;
	}
	/**
	 * @return Returns the changedLines.
	 */
	public Map getChangedLines() {
		return changedLines;
	}
	/**
	 * @param changedLines The changedLines to set.
	 */
	public void setChangedLines(Map changedLines) {
		this.changedLines = changedLines;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}


	/**
	 * @return Returns the benefitDefnSysId.
	 */
	public int getBenefitDefnSysId() {
		return benefitDefnSysId;
	}
	/**
	 * @param benefitDefnSysId The benefitDefnSysId to set.
	 */
	public void setBenefitDefnSysId(int benefitDefnSysId) {
		this.benefitDefnSysId = benefitDefnSysId;
	}

}
