/*
 * ProductStructureBenefitAdministrationLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitAdministrationLocateCriteria extends
        LocateCriteria {


    private int benefitComponentId;
    
    private ProductStructureQuestionnaireBO productStructureQuestionnaireBO;
    
    public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
    private String entiityType;

    private int entityId;

    private int adminOptSysId;
    
    private int parentId;

    private int benefitAdminSysId;
    
    private int action;
    
    private int answerId;
    
    private String answerDesc;
    
    private List questionnareList;
    
    private int questionareListIndex;
    
    private int adminLvlAssnSysId;
    
    private int benefitDefnId;

    private HashMap allPossibleAnswerMap;
    
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
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the adminOptSysId.
     */
    public int getAdminOptSysId() {
        return adminOptSysId;
    }


    /**
     * @param adminOptSysId
     *            The adminOptSysId to set.
     */
    public void setAdminOptSysId(int adminOptSysId) {
        this.adminOptSysId = adminOptSysId;
    }


    /**
     * @return Returns the benefitAdminSysId.
     */
    public int getBenefitAdminSysId() {
        return benefitAdminSysId;
    }


    /**
     * @param benefitAdminSysId
     *            The benefitAdminSysId to set.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
    }


    /**
     * @return Returns the entiityType.
     */
    public String getEntiityType() {
        return entiityType;
    }


    /**
     * @param entiityType
     *            The entiityType to set.
     */
    public void setEntiityType(String entiityType) {
        this.entiityType = entiityType;
    }


    /**
     * @return Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }


    /**
     * @param entityId
     *            The entityId to set.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @return Returns the adminLvlAssnSysId.
     */
    public int getAdminLvlAssnSysId() {
        return adminLvlAssnSysId;
    }
    /**
     * @param adminLvlAssnSysId The adminLvlAssnSysId to set.
     */
    public void setAdminLvlAssnSysId(int adminLvlAssnSysId) {
        this.adminLvlAssnSysId = adminLvlAssnSysId;
    }
    /**
     * @return Returns the benefitDefnId.
     */
    public int getBenefitDefnId() {
        return benefitDefnId;
    }
    /**
     * @param benefitDefnId The benefitDefnId to set.
     */
    public void setBenefitDefnId(int benefitDefnId) {
        this.benefitDefnId = benefitDefnId;
    }
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