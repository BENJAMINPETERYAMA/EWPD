/*
 * ProductBenefitAdminOverrideVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitAdminOverrideVO {

	private int questionId;
	
	private int answerId;
	
	//added the questionHideFlag for Enhancement
	private String questionHideFlag;
	
	 private String adminOptionHideFlag ;
	 
	 private int answrOvrdId;
	
	
	/**
	 * Returns the answerId
	 * @return int answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * Sets the answerId
	 * @param answerId.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * Returns the questionId
	 * @return int questionId.
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * Sets the questionId
	 * @param questionId.
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return Returns the questionHideFlag.
	 */
	public String getQuestionHideFlag() {
		return questionHideFlag;
	}
	/**
	 * @param questionHideFlag The questionHideFlag to set.
	 */
	public void setQuestionHideFlag(String questionHideFlag) {
		this.questionHideFlag = questionHideFlag;
	}
	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}
	/**
	 * @param adminOptionHideFlag The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}
	
	/**
	 * @return Returns the answrOvrdId.
	 */
	public int getAnswrOvrdId() {
		return answrOvrdId;
	}
	/**
	 * @param answrOvrdId The answrOvrdId to set.
	 */
	public void setAnswrOvrdId(int answrOvrdId) {
		this.answrOvrdId = answrOvrdId;
	}
}
