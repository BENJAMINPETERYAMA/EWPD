/*
 * Created on Jun 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductQuestionnaireAssnBO  extends BusinessObject {
	
	
	private int productSysId;
    private int componentSysId;
    private int adminOptionSysId;
    private int questionId;
    private int referenceId;
    private String referenceDesc;
    
    
    private int answerOvrdId;

	/**
	 * @return Returns the answerOvrdId.
	 */
	public int getAnswerOvrdId() {
		return answerOvrdId;
	}
	/**
	 * @param answerOvrdId The answerOvrdId to set.
	 */
	public void setAnswerOvrdId(int answerOvrdId) {
		this.answerOvrdId = answerOvrdId;
	}
    private String questionName;
    private String selectedAnswerDesc;
    
    
   
    
    private List questionnareList;
    
    
    

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
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		
		return false;

	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		return buffer.toString();
	}
	/**
	 * @return Returns the adminOptionSysId.
	 */
	public int getAdminOptionSysId() {
		return adminOptionSysId;
	}
	/**
	 * @param adminOptionSysId The adminOptionSysId to set.
	 */
	public void setAdminOptionSysId(int adminOptionSysId) {
		this.adminOptionSysId = adminOptionSysId;
	}
	
	/**
	 * @return Returns the questionId.
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return Returns the questionName.
	 */
	public String getQuestionName() {
		return questionName;
	}
	/**
	 * @param questionName The questionName to set.
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}
	/**
	 * @return Returns the referenceId.
	 */
	public int getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
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
	 * @return Returns the componentSysId.
	 */
	public int getComponentSysId() {
		return componentSysId;
	}
	/**
	 * @param componentSysId The componentSysId to set.
	 */
	public void setComponentSysId(int componentSysId) {
		this.componentSysId = componentSysId;
	}
	
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
}
