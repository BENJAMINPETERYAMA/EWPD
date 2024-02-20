/*
 * ContractAdminOptionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAdminOptionLocateCriteria extends LocateCriteria {
    
    private String entityType;
	
	private int entityId;
	
	private int adminOptSysId;
	
	private int action;
	
	private int selectedAnswerId;
	
	private int parentQuestionnaireId;
	
	private int contractSytemId;
	
	private List questionnaireList;
	
	private int questionareListIndex;
	
	private ContractAssnQuestionnaireBO contractAssnQuestionnaireBO;
	
	private String referenceId;

	/**
	 * @return Returns the adminOptSysId.
	 */
	public int getAdminOptSysId() {
		return adminOptSysId;
	}
	/**
	 * @param adminOptSysId The adminOptSysId to set.
	 */
	public void setAdminOptSysId(int adminOptSysId) {
		this.adminOptSysId = adminOptSysId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
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
	 * @return Returns the contractSytemId.
	 */
	public int getContractSytemId() {
		return contractSytemId;
	}
	/**
	 * @param contractSytemId The contractSytemId to set.
	 */
	public void setContractSytemId(int contractSytemId) {
		this.contractSytemId = contractSytemId;
	}
	/**
	 * @return Returns the parentQuestionnaireId.
	 */
	public int getParentQuestionnaireId() {
		return parentQuestionnaireId;
	}
	/**
	 * @param parentQuestionnaireId The parentQuestionnaireId to set.
	 */
	public void setParentQuestionnaireId(int parentQuestionnaireId) {
		this.parentQuestionnaireId = parentQuestionnaireId;
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
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
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
}
