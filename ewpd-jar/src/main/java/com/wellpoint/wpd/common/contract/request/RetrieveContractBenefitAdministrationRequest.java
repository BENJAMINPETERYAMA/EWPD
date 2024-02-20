/*
 * RetrieveContractBenefitAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br /> 
 * @version $Id: $
 */
public class RetrieveContractBenefitAdministrationRequest extends ContractRequest {
    
    private int adminOptSysId;
	
	private int benefitAdminSysId;
	
	private int benefitComponentId;
	
	private int benefitId;
	
    
	 private int contractId;
	
    public static final int QUESTIONNARE_INITIAL = 1;
    
    public static final int LOAD_SELECTED_CHILD =2;
    public static final int QUESTIONNARE_VIEW_PRINT = 3;
    public static final int LOAD_SELECTED_CHILD_TIER=4;
    
    private int action ;
	private int adminSysId;
	
	
	private ContractQuesitionnaireBO contractQuesitionnaireBO;
	
	private List questionnareList;
	
	private HashMap allPossibleAnswerMap;
		
	private int selectedAnswerId;
	private String selectedAnswerDesc;
	
	/**
	 * @return Returns the selectedAnswerDesc.
	 */
	public String getSelectedAnswerDesc() {
		return selectedAnswerDesc;
	}
	/**
	 * @param selectedAnswerDesc The selectedAnswerDesc to set.
	 */
	public void setSelectedAnswerDesc(String selectedAnswerDesc) {
		this.selectedAnswerDesc = selectedAnswerDesc;
	}
	private int questionareListIndex;
		
	//  Code change by minu : 5-1-2011 : eWPD System Stabilization
	private int cntrctParentSysId;
	private int adminLevelOptionSysId;
	
    public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

    
    
   
    /**
     * Returns the contractId
     * @return int contractId.
     */
    public int getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
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
	 * @return Returns the selectedAnswerId.
	 */
	public int getSelectedAnswerId() {
		return selectedAnswerId;
	}
	/**
	 * @param selectedAnswerId The selectedAnswerId to set.
	 */
	public void setSelectedAnswerId(int selectedAnswerId) {
		this.selectedAnswerId = selectedAnswerId;
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
	 * @return Returns the allPossibleAnswerList.
	 */
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	/**
	 * @param allPossibleAnswerList The allPossibleAnswerList to set.
	 */
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}
}
