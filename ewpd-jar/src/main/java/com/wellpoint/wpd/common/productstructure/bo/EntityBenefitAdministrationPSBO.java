/*
 * EntityBenefitAdministrationPSBO.java
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
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityBenefitAdministrationPSBO {

    private List benefitAdministrationList;
    private List questionnareList;
    
    private int benefitComponentid;
    
    private int adminLevelOptionSysId;
    
    private int dateSegmentId;
    
    private List tierList;
    
    // Code change by minu : 28-12-2010 : eWPD System Stabilization 
	private List questionnaireListToAdd;
	private List questionnaireListToUpdate;
	private List questionnaireListToRemove;
	private List tierQuestionnaireListToAdd;
	private List tierQuestionnaireListToUpdate;
	private List tierQuestionnaireListToRemove;
	   

    /**
     * Returns the benefitAdministrationList
     * 
     * @return List benefitAdministrationList.
     */

    public List getBenefitAdministrationList() {
        return benefitAdministrationList;
    }


    /**
     * Sets the benefitAdministrationList
     * 
     * @param benefitAdministrationList.
     */

    public void setBenefitAdministrationList(List benefitAdministrationList) {
        this.benefitAdministrationList = benefitAdministrationList;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitAdministrationList = ").append(
                this.benefitAdministrationList);
        return buffer.toString();

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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the tierList.
	 */
	public List getTierList() {
		return tierList;
	}
	/**
	 * @param tierList The tierList to set.
	 */
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	
	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	
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
	/**
	 * @return Returns the tierQuestionnaireListToAdd.
	 */
	public List getTierQuestionnaireListToAdd() {
		return tierQuestionnaireListToAdd;
	}
	/**
	 * @param tierQuestionnaireListToAdd The tierQuestionnaireListToAdd to set.
	 */
	public void setTierQuestionnaireListToAdd(List tierQuestionnaireListToAdd) {
		this.tierQuestionnaireListToAdd = tierQuestionnaireListToAdd;
	}
	/**
	 * @return Returns the tierQuestionnaireListToRemove.
	 */
	public List getTierQuestionnaireListToRemove() {
		return tierQuestionnaireListToRemove;
	}
	/**
	 * @param tierQuestionnaireListToRemove The tierQuestionnaireListToRemove to set.
	 */
	public void setTierQuestionnaireListToRemove(
			List tierQuestionnaireListToRemove) {
		this.tierQuestionnaireListToRemove = tierQuestionnaireListToRemove;
	}
	/**
	 * @return Returns the tierQuestionnaireListToUpdate.
	 */
	public List getTierQuestionnaireListToUpdate() {
		return tierQuestionnaireListToUpdate;
	}
	/**
	 * @param tierQuestionnaireListToUpdate The tierQuestionnaireListToUpdate to set.
	 */
	public void setTierQuestionnaireListToUpdate(
			List tierQuestionnaireListToUpdate) {
		this.tierQuestionnaireListToUpdate = tierQuestionnaireListToUpdate;
	}
}