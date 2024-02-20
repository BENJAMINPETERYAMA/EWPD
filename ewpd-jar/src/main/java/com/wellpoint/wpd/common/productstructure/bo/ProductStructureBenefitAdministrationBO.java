/*
 * ComponentsBenefitAdministrationBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.List;

/**
 * Business Object for ComponentsBenefitAdministration
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitAdministrationBO {

    private List benefitAdminstrationList;
    
    private List questionnaireList;
    
    private List questionnaireListToAdd;
    
    private List questionnaireListToUpdate;
    
    private List questionnaireListToRemove;
    
    private int productStructureId;
    
    private int adminLevelOptionSysId;
    
    private int benefitComponentId;
    
    


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
     * @return Returns the benefitAdminstrationList.
     */
    public List getBenefitAdminstrationList() {
        return benefitAdminstrationList;
    }


    /**
     * @param benefitAdminstrationList
     *            The benefitAdminstrationList to set.
     */
    public void setBenefitAdminstrationList(List benefitAdminstrationList) {
        this.benefitAdminstrationList = benefitAdminstrationList;
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