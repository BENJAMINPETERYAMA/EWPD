/*
 * SaveProductStructureBenefitDefinitionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductStructureBenefitDefinitionRequest extends WPDRequest {

    private ProductStructureVO productStructureVO;

    private List benefitLinesList;

    private List deleteBenefitLevelList;

    private int standardBenefitKey;

    private int benefitComponentId;
    
    private String benefitHideFlag;
    
    private boolean flagChanged;
    
    private List changedIds;
    
    private String bCompName;


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
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }


    /**
     * @param standardBenefitKey
     *            The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }


    /**
     * @return Returns the productStructureVO.
     */
    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * @param productStructureVO
     *            The productStructureVO to set.
     */
    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * @return Returns the deleteBenefitLevelList.
     */
    public List getDeleteBenefitLevelList() {
        return deleteBenefitLevelList;
    }


    /**
     * @param deleteBenefitLevelList
     *            The deleteBenefitLevelList to set.
     */
    public void setDeleteBenefitLevelList(List deleteBenefitLevelList) {
        this.deleteBenefitLevelList = deleteBenefitLevelList;
    }


    /**
     * Returns the benefitLinesList
     * 
     * @return List benefitLinesList.
     */
    public List getBenefitLinesList() {
        return benefitLinesList;
    }


    /**
     * Sets the benefitLinesList
     * 
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

    }

	/**
	 * @return Returns the benefitHideFlag.
	 */
	public String getBenefitHideFlag() {
		return benefitHideFlag;
	}
	/**
	 * @param benefitHideFlag The benefitHideFlag to set.
	 */
	public void setBenefitHideFlag(String benefitHideFlag) {
		this.benefitHideFlag = benefitHideFlag;
	}
	/**
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	/**
	 * @return Returns the flagChanged.
	 */
	public boolean isFlagChanged() {
		return flagChanged;
	}
	/**
	 * @param flagChanged The flagChanged to set.
	 */
	public void setFlagChanged(boolean flagChanged) {
		this.flagChanged = flagChanged;
	}
	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}
	/**
	 * @param compId The bCompName to set.
	 */
	public void setBCompName(String compId) {
		bCompName = compId;
	}
}