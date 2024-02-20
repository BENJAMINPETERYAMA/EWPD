/*
 * LocateComponentsBenefitAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentQuesitionnaireBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request for Locate Component Benefit Administration. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitComponentQuestionnairRequest extends WPDRequest{
    
    // variable declarations
	public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
	private int adminSysId;
	
	private int benefitAdminSysId;

	private int benefitComponentId;
	
	private BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO;
	
	private List questionnareList;
	
	private int action;
	
	private int selectedAnswerId;
	
	private String selectedAnswerDesc;
	
	private int questionareListIndex;
	
	private HashMap allPossibleAnswerMap;
	
	private int adminLvlOptSystemId;
	
	private int beneftCompParentId;

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
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

	/**
	 * @return Returns the benefitComponentQuesitionnaireBO.
	 */
	public BenefitComponentQuesitionnaireBO getBenefitComponentQuesitionnaireBO() {
		return benefitComponentQuesitionnaireBO;
	}
	/**
	 * @param benefitComponentQuesitionnaireBO The benefitComponentQuesitionnaireBO to set.
	 */
	public void setBenefitComponentQuesitionnaireBO(
			BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO) {
		this.benefitComponentQuesitionnaireBO = benefitComponentQuesitionnaireBO;
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
	 * @return Returns the adminLvlOptSystemId.
	 */
	public int getAdminLvlOptSystemId() {
		return adminLvlOptSystemId;
	}
	/**
	 * @param adminLvlOptSystemId The adminLvlOptSystemId to set.
	 */
	public void setAdminLvlOptSystemId(int adminLvlOptSystemId) {
		this.adminLvlOptSystemId = adminLvlOptSystemId;
	}
	/**
	 * @return Returns the beneftCompParentId.
	 */
	public int getBeneftCompParentId() {
		return beneftCompParentId;
	}
	/**
	 * @param beneftCompParentId The beneftCompParentId to set.
	 */
	public void setBeneftCompParentId(int beneftCompParentId) {
		this.beneftCompParentId = beneftCompParentId;
	}
}
