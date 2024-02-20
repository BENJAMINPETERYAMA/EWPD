/*
 * ComponentsBenefitAdministrationBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import java.util.List;

/**
 * Business Object for ComponentsBenefitAdministration
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ComponentsBenefitAdministrationBO {

    private List benefitAdminstrationList;
    
    private List questionnareList;
    
    private List newQuestions;
    
    private List modifiedQuestions;
	
    private List removedQuestions;
    
    private int benefitComponentid;
    
    private int adminLevelOptionSysId;


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
	 * @return Returns the benefitComponentid.
	 */
	public int getBenefitComponentid() {
		return benefitComponentid;
	}
	/**
	 * @param benefitComponentid The benefitComponentid to set.
	 */
	public void setBenefitComponentid(int benefitComponentid) {
		this.benefitComponentid = benefitComponentid;
	}
	/**
	 * @return Returns the modifiedQuestions.
	 */
	public List getModifiedQuestions() {
		return modifiedQuestions;
	}
	/**
	 * @param modifiedQuestions The modifiedQuestions to set.
	 */
	public void setModifiedQuestions(List modifiedQuestions) {
		this.modifiedQuestions = modifiedQuestions;
	}
	/**
	 * @return Returns the newQuestions.
	 */
	public List getNewQuestions() {
		return newQuestions;
	}
	/**
	 * @param newQuestions The newQuestions to set.
	 */
	public void setNewQuestions(List newQuestions) {
		this.newQuestions = newQuestions;
	}
	/**
	 * @return Returns the removedQuestions.
	 */
	public List getRemovedQuestions() {
		return removedQuestions;
	}
	/**
	 * @param removedQuestions The removedQuestions to set.
	 */
	public void setRemovedQuestions(List removedQuestions) {
		this.removedQuestions = removedQuestions;
	}
}