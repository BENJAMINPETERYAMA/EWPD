/*
 * SaveBenefitAdministrationRequest.java
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
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveBenefitAdministrationRequest  extends ContractRequest {
    
    private List benefitAdministrationVOList;
	
	private ProductStructureVO productStructureVO;
	
	private int benefitComponentId;
	
	private int adminSysId;
	
	private int benefitAdminSysId;
	
	private int adminLevelOptionAssnId;
	
	private boolean changed;
	
	private List changedIds;
	
	private String bCompName;
	
	private int benefitId;
	private int dateSegmentSysId; 
	
	private List tierList;
	
	
	private List questionnareList;
	
	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	private List questionnaireListToAdd;
	private List questionnaireListToUpdate;
	private List questionnaireListToRemove;
	private List tierQuestionnaireListToAdd;
	private List tierQuestionnaireListToUpdate;
	private List tierQuestionnaireListToRemove;
	
	/* START CARS */
	private List changedTiers;
	private List changedTierSysIds;

	
	/* END CARS */
	
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
     * Returns the benefitAdministrationVOList
     * @return List benefitAdministrationVOList.
     */
    public List getBenefitAdministrationVOList() {
        return benefitAdministrationVOList;
    }
    /**
     * Sets the benefitAdministrationVOList
     * @param benefitAdministrationVOList.
     */
    public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
        this.benefitAdministrationVOList = benefitAdministrationVOList;
    }
    /**
     * Returns the benefitAdminSysId
     * @return int benefitAdminSysId.
     */
    public int getBenefitAdminSysId() {
        return benefitAdminSysId;
    }
    /**
     * Sets the benefitAdminSysId
     * @param benefitAdminSysId.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
    }
    /**
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the productStructureVO
     * @return ProductStructureVO productStructureVO.
     */
    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }
    /**
     * Sets the productStructureVO
     * @param productStructureVO.
     */
    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }
    /**
     * Returns the adminLevelOptionAssnId
     * @return int adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * Sets the adminLevelOptionAssnId
     * @param adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
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
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	
	 public void setDateSegmentSysId(int dateSegmentSysId) {
        this.dateSegmentSysId = dateSegmentSysId;
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
	 * @return Returns the tierList.
	 */
	public List getTierList() {
		return tierList;
	}
	/**
	 * @param tierList The tierList to set.
	 */
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	
	// CARS START
	/**
	 * @return the changedTiers
	 */
	public List getChangedTiers() {
		return changedTiers;
	}

	/**
	 * @param changedTiers the changedTiers to set
	 */
	public void setChangedTiers(List changedTiers) {
		this.changedTiers = changedTiers;
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
	//CARS END
	
	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	/**
	 * @return Returns the questionnaireListToAdd.
	 */
	public List getQuestionnaireListToAdd() {
		return questionnaireListToAdd;
	}
	/**
	 * @param questionnaireListToAdd The questionnaireListToAdd to set.
	 */
	public void setQuestionnaireListToAdd(List questionnaireListToAdd) {
		this.questionnaireListToAdd = questionnaireListToAdd;
	}
	/**
	 * @return Returns the questionnaireListToRemove.
	 */
	public List getQuestionnaireListToRemove() {
		return questionnaireListToRemove;
	}
	/**
	 * @param questionnaireListToRemove The questionnaireListToRemove to set.
	 */
	public void setQuestionnaireListToRemove(List questionnaireListToRemove) {
		this.questionnaireListToRemove = questionnaireListToRemove;
	}
	/**
	 * @return Returns the questionnaireListToUpdate.
	 */
	public List getQuestionnaireListToUpdate() {
		return questionnaireListToUpdate;
	}
	/**
	 * @param questionnaireListToUpdate The questionnaireListToUpdate to set.
	 */
	public void setQuestionnaireListToUpdate(List questionnaireListToUpdate) {
		this.questionnaireListToUpdate = questionnaireListToUpdate;
	}
	/**
	 * @return Returns the tierQuestionnaireListToAdd.
	 */
	public List getTierQuestionnaireListToAdd() {
		return tierQuestionnaireListToAdd;
	}
	/**
	 * @param tierQuestionnaireListToAdd The tierQuestionnaireListToAdd to set.
	 */
	public void setTierQuestionnaireListToAdd(List tierQuestionnaireListToAdd) {
		this.tierQuestionnaireListToAdd = tierQuestionnaireListToAdd;
	}
	/**
	 * @return Returns the tierQuestionnaireListToRemove.
	 */
	public List getTierQuestionnaireListToRemove() {
		return tierQuestionnaireListToRemove;
	}
	/**
	 * @param tierQuestionnaireListToRemove The tierQuestionnaireListToRemove to set.
	 */
	public void setTierQuestionnaireListToRemove(
			List tierQuestionnaireListToRemove) {
		this.tierQuestionnaireListToRemove = tierQuestionnaireListToRemove;
	}
	/**
	 * @return Returns the tierQuestionnaireListToUpdate.
	 */
	public List getTierQuestionnaireListToUpdate() {
		return tierQuestionnaireListToUpdate;
	}
	/**
	 * @param tierQuestionnaireListToUpdate The tierQuestionnaireListToUpdate to set.
	 */
	public void setTierQuestionnaireListToUpdate(
			List tierQuestionnaireListToUpdate) {
		this.tierQuestionnaireListToUpdate = tierQuestionnaireListToUpdate;
	}

}
