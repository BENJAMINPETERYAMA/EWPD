/*
 * SaveBenefitAdministrationRequest.java
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
public class SaveBenefitAdministrationRequest extends WPDRequest {

    private List benefitAdministrationVOList;

    private ProductStructureVO productStructureVO;

    private int benefitComponentId;

    private int adminSysId;

    private int benefitAdminSysId;
    
    private int adminOptionLevelAssnId;
    
    //enhancement
    private String adminOptionHideFlag;
    
    private boolean answerOverrideFlag;
    
    private boolean hideStatusFlag;
    
    private boolean isChanged;
    
    private List changedIds;
    
    private String bCompName;
    
    private int benefitSysId;
    
    


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
	/**
	 * @return hideStatusFlag
	 * 
	 * Returns the hideStatusFlag.
	 */
	public boolean isHideStatusFlag() {
		return hideStatusFlag;
	}
	/**
	 * @param hideStatusFlag
	 * 
	 * Sets the hideStatusFlag.
	 */
	public void setHideStatusFlag(boolean hideStatusFlag) {
		this.hideStatusFlag = hideStatusFlag;
	}
	/**
	 * @return answerOverrideFlag
	 * 
	 * Returns the answerOverrideFlag.
	 */
	public boolean isAnswerOverrideFlag() {
		return answerOverrideFlag;
	}
	/**
	 * @param answerOverrideFlag
	 * 
	 * Sets the answerOverrideFlag.
	 */
	public void setAnswerOverrideFlag(boolean answerOverrideFlag) {
		this.answerOverrideFlag = answerOverrideFlag;
	}
    /**
     * @return Returns the adminSysId.
     */
    public int getAdminSysId() {
        return adminSysId;
    }


    /**
     * @param adminSysId
     *            The adminSysId to set.
     */
    public void setAdminSysId(int adminSysId) {
        this.adminSysId = adminSysId;
    }


    /**
     * @return Returns the benefitAdminSysId.
     */
    public int getBenefitAdminSysId() {
        return benefitAdminSysId;
    }


    /**
     * @param benefitAdminSysId
     *            The benefitAdminSysId to set.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
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
     * @return Returns the benefitAdministrationVOList.
     */
    public List getBenefitAdministrationVOList() {
        return benefitAdministrationVOList;
    }


    /**
     * @param benefitAdministrationVOList
     *            The benefitAdministrationVOList to set.
     */
    public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
        this.benefitAdministrationVOList = benefitAdministrationVOList;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * Returns the adminOptionLevelAssnId
     * @return int adminOptionLevelAssnId.
     */
    public int getAdminOptionLevelAssnId() {
        return adminOptionLevelAssnId;
    }
    /**
     * Sets the adminOptionLevelAssnId
     * @param adminOptionLevelAssnId.
     */
    public void setAdminOptionLevelAssnId(int adminOptionLevelAssnId) {
        this.adminOptionLevelAssnId = adminOptionLevelAssnId;
    }
	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}
	/**
	 * @param adminOptionHideFlag The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
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
	 * @return Returns the isChanged.
	 */
	public boolean isChanged() {
		return isChanged;
	}
	/**
	 * @param isChanged The isChanged to set.
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}
	/**
	 * @param compName The bCompName to set.
	 */
	public void setBCompName(String compName) {
		bCompName = compName;
	}
}