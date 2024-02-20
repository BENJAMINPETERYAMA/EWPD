/*
 * RetrieveQuestionaireRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveQuestionaireRequest extends WPDRequest {

	 private int adminLevelOptionAssnSysId;
	 
	 private int action;
	 
	 private int maxSearchResultSize;
	 
	 private List questionnareList;
	 
	 private int selectedAnswerId;
	 
	 private String selectedAnswerDesc;
	 
	 private int questionareListIndex;
	 
	 private int benefitId;
	 
	 private BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO;
	 
	 private int adminOptionId;
	 
	 private HashMap allPossibleAnswerMap;
	 
	 private int parentId;
	 
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {

	}

	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	/**
	 * @return Returns the adminLevelOptionAssnSysId.
	 */
	public int getAdminLevelOptionAssnSysId() {
		return adminLevelOptionAssnSysId;
	}
	/**
	 * @param adminLevelOptionAssnSysId The adminLevelOptionAssnSysId to set.
	 */
	public void setAdminLevelOptionAssnSysId(int adminLevelOptionAssnSysId) {
		this.adminLevelOptionAssnSysId = adminLevelOptionAssnSysId;
	}
	/**
	 * @return Returns the maxSearchResultSize.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSize The maxSearchResultSize to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
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
	 * @return Returns the benefitQuestionnaireAssnBO.
	 */
	public BenefitQuestionnaireAssnBO getBenefitQuestionnaireAssnBO() {
		return benefitQuestionnaireAssnBO;
	}
	/**
	 * @param benefitQuestionnaireAssnBO The benefitQuestionnaireAssnBO to set.
	 */
	public void setBenefitQuestionnaireAssnBO(
			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO) {
		this.benefitQuestionnaireAssnBO = benefitQuestionnaireAssnBO;
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
	/**
	 * @return Returns the parentId.
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
