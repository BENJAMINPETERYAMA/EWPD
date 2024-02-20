/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdministrationLocateCriteria extends LocateCriteria{
	
	public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
	public static final int LOAD_SELECTED_CHILD_TIER =4;
	
    private String entiityType;

    private int entityId;

    private int adminOptSysId;

    private int benefitAdminSysId;
    
    private int action;
    
    private ProductQuestionareBO productQuestionareBO;
    
    private int answerId;
    
    private String answerDesc;
    
    private List questionnareList;
    
    private int questionareListIndex;
    
    private int benefitComponentId;
    
    private int admnLvlAsscId;
    
    private int productPrntSysId;
    
    private  EntityProductAdministration entityProductAdministrationBO;
    
    private int benefitDefnId;
    
    private int benefitId;
    
    private HashMap allPossibleAnswerMap;
    
    
    
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
	 * @return Returns the adminOptSysId.
	 */
	public int getAdminOptSysId() {
		return adminOptSysId;
	}
	/**
	 * @param adminOptSysId The adminOptSysId to set.
	 */
	public void setAdminOptSysId(int adminOptSysId) {
		this.adminOptSysId = adminOptSysId;
	}
	/**
	 * @return Returns the admnLvlAsscId.
	 */
	public int getAdmnLvlAsscId() {
		return admnLvlAsscId;
	}
	/**
	 * @param admnLvlAsscId The admnLvlAsscId to set.
	 */
	public void setAdmnLvlAsscId(int admnLvlAsscId) {
		this.admnLvlAsscId = admnLvlAsscId;
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
	 * @return Returns the entiityType.
	 */
	public String getEntiityType() {
		return entiityType;
	}
	/**
	 * @param entiityType The entiityType to set.
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
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
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
	 * @return Returns the productQuestionareBO.
	 */
	public ProductQuestionareBO getProductQuestionareBO() {
		return productQuestionareBO;
	}
	/**
	 * @param productQuestionareBO The productQuestionareBO to set.
	 */
	public void setProductQuestionareBO(
			ProductQuestionareBO productQuestionareBO) {
		this.productQuestionareBO = productQuestionareBO;
	}
	/**
	 * @return Returns the productPrntSysId.
	 */
	public int getProductPrntSysId() {
		return productPrntSysId;
	}
	/**
	 * @param productPrntSysId The productPrntSysId to set.
	 */
	public void setProductPrntSysId(int productPrntSysId) {
		this.productPrntSysId = productPrntSysId;
	}
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
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
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
}
