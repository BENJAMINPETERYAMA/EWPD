/*
 * LocateComponentsBenefitAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;


/**
 * Request for Locate Component Benefit Administration. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureQuestionnaireRequest extends WPDRequest{
    
	public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
	// variable declarations
    
	private int adminSysId;
	
	private int parentId;
	
	private int benefitAdminSysId;

	private int productStructureId;
	private int benefitComponentId;
	
	private ProductStructureQuestionnaireBO productStructureQuestionnaireBO;
	
	private List questionnareList;
	
	private int action;
	
	private int selectedAnswerId;
	
	private String selectedAnswerDesc;
	
	private int questionareListIndex;
	
	private int adminOptionAssnSysId;
	
	private int benefitDefinitionId;

	private HashMap allPossibleAnswerMap;
	
	/**
	 * @return Returns the adminOptionAssnSysId.
	 */
	public int getAdminOptionAssnSysId() {
		return adminOptionAssnSysId;
	}
	/**
	 * @param adminOptionAssnSysId The adminOptionAssnSysId to set.
	 */
	public void setAdminOptionAssnSysId(int adminOptionAssnSysId) {
		this.adminOptionAssnSysId = adminOptionAssnSysId;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the productStructureId.
	 */
	public int getProductStructureId() {
		return productStructureId;
	}
	/**
	 * @param productStructureId The productStructureId to set.
	 */
	public void setProductStructureId(int productStructureId) {
		this.productStructureId = productStructureId;
	}
	/**
	 * @return Returns the productStructureQuestionnaireBO.
	 */
	public ProductStructureQuestionnaireBO getProductStructureQuestionnaireBO() {
		return productStructureQuestionnaireBO;
	}
	/**
	 * @param productStructureQuestionnaireBO The productStructureQuestionnaireBO to set.
	 */
	public void setProductStructureQuestionnaireBO(
			ProductStructureQuestionnaireBO productStructureQuestionnaireBO) {
		this.productStructureQuestionnaireBO = productStructureQuestionnaireBO;
	}
    /**
     * @return Returns the adminSysId.
     */
    public int getAdminSysId() {
        return adminSysId;
    }
    /**
     * @param adminSysId The adminSysId to set.
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
     * @param benefitAdminSysId The benefitAdminSysId to set.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
    }
   
   
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
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
     * @return Returns the benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    /**
     * @param benefitDefinitionId The benefitDefinitionId to set.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
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
