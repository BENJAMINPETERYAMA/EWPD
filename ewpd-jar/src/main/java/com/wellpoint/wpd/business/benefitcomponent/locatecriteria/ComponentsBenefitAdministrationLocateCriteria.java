/*
 * ComponentsBenefitAdministrationLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.locatecriteria;


import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentQuesitionnaireBO;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;

/**
 * Locate criteria class for benefit component Administration
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ComponentsBenefitAdministrationLocateCriteria extends
        LocateCriteria {
	
	public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
    private String entiityType;

    private int entityId;

    private int adminOptSysId;

    private int benefitAdminSysId;
    
    private int action;
    
    private BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO;
    
    private int answerId;
    
    private String selectedAnswerDesc;
    
    private List questionnareList;
    
    private int questionareListIndex;

    private  EntityProductAdministration entityProductAdministrationBO; 
    
    private int benefitDefenitionId;
    
    private int productId;
    
    private HashMap allPossibleAnswerMap;
    
    private int adminLvlOptSystemId;
	
	private int beneftCompParentId;
    
	/**
	 * @return Returns the entityProductAdministrationBO.
	 */
	public EntityProductAdministration getEntityProductAdministrationBO() {
		return entityProductAdministrationBO;
	}
	/**
	 * @param entityProductAdministrationBO The entityProductAdministrationBO to set.
	 */
	public void setEntityProductAdministrationBO(
			EntityProductAdministration entityProductAdministrationBO) {
		this.entityProductAdministrationBO = entityProductAdministrationBO;
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
	 * @return Returns the benefitDefenitionId.
	 */
	public int getBenefitDefenitionId() {
		return benefitDefenitionId;
	}
	/**
	 * @param benefitDefenitionId The benefitDefenitionId to set.
	 */
	public void setBenefitDefenitionId(int benefitDefenitionId) {
		this.benefitDefenitionId = benefitDefenitionId;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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