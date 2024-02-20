/*
 * ContractBenefitAdministrationLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locateresult;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitAdministrationLocateCriteria  extends LocateCriteria {
    
	
	public static final int QUESTIONNARE_INITIAL =1;
	public static final int LOAD_SELECTED_CHILD = 2;
	public static final int QUESTIONNARE_VIEW_PRINT=3;
	public static final int LOAD_SELECTED_CHILD_TIER=4;
	
    private String entiityType;
	
	private int entityId;
	
	private int adminOptSysId;
	
	private int benefitAdminSysId;
	
	private int benefitComponentId;
	
	private int benefitId;
	
	private int action;
    
    private ContractQuesitionnaireBO contractQuesitionnaireBO;
    
    private int answerId;
    private String answerDesc;
    private HashMap allPossibleAnswerMap;
    
	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
    private List questionnareList;
    
    private int questionareListIndex;
	
    private String referenceId;
    
    //  Code change by minu : 5-1-2011 : eWPD System Stabilization
	private int cntrctParentSysId;
	private int adminLevelOptionSysId;
    /**
     * Returns the adminOptSysId
     * @return int adminOptSysId.
     */
    public int getAdminOptSysId() {
        return adminOptSysId;
    }
    /**
     * Sets the adminOptSysId
     * @param adminOptSysId.
     */
    public void setAdminOptSysId(int adminOptSysId) {
        this.adminOptSysId = adminOptSysId;
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
     * Returns the entiityType
     * @return String entiityType.
     */
    public String getEntiityType() {
        return entiityType;
    }
    /**
     * Sets the entiityType
     * @param entiityType.
     */
    public void setEntiityType(String entiityType) {
        this.entiityType = entiityType;
    }
    /**
     * Returns the entityId
     * @return int entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	
	
	/**
	 * @return Returns the benefitComponentQuesitionnaireBO.
	 */
	public ContractQuesitionnaireBO getContractQuesitionnaireBO() {
		return contractQuesitionnaireBO;
	}
	/**
	 * @param benefitComponentQuesitionnaireBO The benefitComponentQuesitionnaireBO to set.
	 */
	public void setContractQuesitionnaireBO(
			ContractQuesitionnaireBO contractQuesitionnaireBO) {
		this.contractQuesitionnaireBO = contractQuesitionnaireBO;
	}
	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return Returns the questionareListIndex.
	 */
	public int getQuestionareListIndex() {
		return questionareListIndex;
	}
	/**
	 * @param questionareListIndex The questionareListIndex to set.
	 */
	public void setQuestionareListIndex(int questionareListIndex) {
		this.questionareListIndex = questionareListIndex;
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
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
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
	/**
	 * @return Returns the cntrctParentSysId.
	 */
	public int getCntrctParentSysId() {
		return cntrctParentSysId;
	}
	/**
	 * @param cntrctParentSysId The cntrctParentSysId to set.
	 */
	public void setCntrctParentSysId(int cntrctParentSysId) {
		this.cntrctParentSysId = cntrctParentSysId;
	}
	/**
	 * @return Returns the adminLevelOptionSysId.
	 */
	public int getAdminLevelOptionSysId() {
		return adminLevelOptionSysId;
	}
	/**
	 * @param adminLevelOptionSysId The adminLevelOptionSysId to set.
	 */
	public void setAdminLevelOptionSysId(int adminLevelOptionSysId) {
		this.adminLevelOptionSysId = adminLevelOptionSysId;
	}
	/**
	 * @return Returns the allPossibleAnswerMap.
	 */
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	/**
	 * @param allPossibleAnswerMap The allPossibleAnswerMap to set.
	 */
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}
}
