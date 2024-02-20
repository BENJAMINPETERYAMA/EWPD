/*
 * RetrieveContractProductAdminOptionOverrideRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractProductAdminOptionOverrideRequest  extends ContractRequest {
    
    private int adminOptSysId;
    
    private int entityId;
    
    public static final int LOAD_CONTRACT_QUESTIONNAIRE = 1;
    
    public static final int LOAD_CHILD_QUESTIONNAIRE = 2;
    
    public static final int LOAD_CONTRACT_QUESTIONNAIRE_DF = 3;
    
    private int action;
    
	 private List questionnareList;
	 
	 private int selectedAnswerId;
	 
	 private int questionareListIndex;

	 private ContractAssnQuestionnaireBO contractAssnQuestionnaireBO;
    
    public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the contractAssnQuestionnaireBO.
	 */
	public ContractAssnQuestionnaireBO getContractAssnQuestionnaireBO() {
		return contractAssnQuestionnaireBO;
	}
	/**
	 * @param contractAssnQuestionnaireBO The contractAssnQuestionnaireBO to set.
	 */
	public void setContractAssnQuestionnaireBO(
			ContractAssnQuestionnaireBO contractAssnQuestionnaireBO) {
		this.contractAssnQuestionnaireBO = contractAssnQuestionnaireBO;
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
}
