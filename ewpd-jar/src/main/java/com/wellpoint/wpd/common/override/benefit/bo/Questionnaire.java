/*
 * QuestionAnswered.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public abstract class Questionnaire {
	int questionnaireId;
	int parentQuestionnaireId;
	int level;
	int childCount;
	int tierSysId;
	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
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
	 * @return Returns the questionnaireId.
	 */
	public int getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * @param questionnaireId The questionnaireId to set.
	 */
	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * @return Returns the childCount.
	 */
	public int getChildCount() {
		return childCount;
	}
	/**
	 * @param childCount The childCount to set.
	 */
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	
	public boolean compareTo(Questionnaire Questionnaire) {
	    int questionnaireId = ((Questionnaire) Questionnaire).getQuestionnaireId();  
	    if(questionnaireId==this.questionnaireId){
	    	return true;
	    }
	      return false;
	  }
}
