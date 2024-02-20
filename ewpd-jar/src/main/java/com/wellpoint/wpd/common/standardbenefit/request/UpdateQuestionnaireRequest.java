/*
 * UpdateQuestionnaireRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class UpdateQuestionnaireRequest extends WPDRequest {
	
	
	private List domainList;
	   
	private int versionNumber;
	
	private List questionnareList;
	
	private List questionnaireListToAdd;
	
	private List questionnaireListToUpdate;
	
	private List questionnaireListToRemove;
	
	private int adminlevelOptionSysId;
	
	private int benefitId;
	
	private String mainObjectIdentifier;
	
	private String status;
	
	private int parentSystemId;

	/**
	 * @return Returns the parentSystemId.
	 */
	public int getParentSystemId() {
		return parentSystemId;
	}
	/**
	 * @param parentSystemId The parentSystemId to set.
	 */
	public void setParentSystemId(int parentSystemId) {
		this.parentSystemId = parentSystemId;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the mainObjectIdentifier.
	 */
	public String getMainObjectIdentifier() {
		return mainObjectIdentifier;
	}
	/**
	 * @param mainObjectIdentifier The mainObjectIdentifier to set.
	 */
	public void setMainObjectIdentifier(String mainObjectIdentifier) {
		this.mainObjectIdentifier = mainObjectIdentifier;
	}
	/**
	 * @return Returns the adminlevelOptionSysId.
	 */
	public int getAdminlevelOptionSysId() {
		return adminlevelOptionSysId;
	}
	/**
	 * @param adminlevelOptionSysId The adminlevelOptionSysId to set.
	 */
	public void setAdminlevelOptionSysId(int adminlevelOptionSysId) {
		this.adminlevelOptionSysId = adminlevelOptionSysId;
	}
	/**
	 * @return Returns the domainList.
	 */
	public List getDomainList() {
		return domainList;
	}
	/**
	 * @param domainList The domainList to set.
	 */
	public void setDomainList(List domainList) {
		this.domainList = domainList;
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
	 * @return Returns the versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

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
}
