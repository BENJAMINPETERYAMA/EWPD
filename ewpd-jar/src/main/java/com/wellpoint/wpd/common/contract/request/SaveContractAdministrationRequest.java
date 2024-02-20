/*
 * SaveProductAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractAdministrationRequest extends ContractRequest  {
    
    private int adminSysId;
    
    private List contractBenefitAdministrationVO;
    
    private boolean changed;
    
    private List changedIds;
    
    private List questionnareList;
    
    private int dateSegmentId;
    
    private int benefitId;
    
    private int beneCompId;
    
    
    /**
     * Returns the contractBenefitAdministrationVO
     * @return List contractBenefitAdministrationVO.
     */
    public List getContractBenefitAdministrationVO() {
        return contractBenefitAdministrationVO;
    }
    /**
     * Sets the contractBenefitAdministrationVO
     * @param contractBenefitAdministrationVO.
     */
    public void setContractBenefitAdministrationVO(
            List contractBenefitAdministrationVO) {
        this.contractBenefitAdministrationVO = contractBenefitAdministrationVO;
    }
    /**
     * Returns the adminSysId
     * @return int adminSysId.
     */
    public int getAdminSysId() {
        return adminSysId;
    }
    /**
     * Sets the adminSysId
     * @param adminSysId.
     */
    public void setAdminSysId(int adminSysId) {
        this.adminSysId = adminSysId;
    }
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	

	/**
	 * @return Returns the changed.
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
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
	 * @return Returns the questionnareList.
	 */
	public List getQuestionnareList() {
		return questionnareList;
	}
	/**
	 * @param questionnareList The questionnareList to set.
	 */
	public void setQuestionnareList(List questionnareList) {
		this.questionnareList = questionnareList;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the beneCompId.
	 */
	public int getBeneCompId() {
		return beneCompId;
	}
	/**
	 * @param beneCompId The beneCompId to set.
	 */
	public void setBeneCompId(int beneCompId) {
		this.beneCompId = beneCompId;
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
