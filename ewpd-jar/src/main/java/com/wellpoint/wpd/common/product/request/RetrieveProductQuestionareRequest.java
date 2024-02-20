/*
 * Created on Jun 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveProductQuestionareRequest extends WPDRequest{

	public static final int LOAD_QUESTIONNARE_LIST =1;
	
	public static final int LOAD_SELECTED_CHILD =2;
	
	public static final int LOAD_QUESTIONNARE_VIEW =3;
	
	public static final int LOAD_SELECTED_CHILD_TIER =4;
	
	private int productSysId;
	
	private int benefitComponentSysId;
	
	private int admnLvlOptionAsscId;
	
	private int adminOptionSysId;
	
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
	private int benftAdminSysId;
	
	private ProductQuestionareBO productQuestionareBO;
	//private BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO;
	
	private List questionnareList;
	
	private List tierQuestionnareList;
	
	private int action;
	
	private int selectedAnswerId;
	
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
	private String selectedAnswerDesc;
	HashMap allPossibleAnswerMap;
	
	private int questionareListIndex;
	
	private int productPrntSysId;
	
	private int benefitId;
	
	
	
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
	 * @return Returns the admnLvlOptionAsscId.
	 */
	public int getAdmnLvlOptionAsscId() {
		return admnLvlOptionAsscId;
	}
	/**
	 * @param admnLvlOptionAsscId The admnLvlOptionAsscId to set.
	 */
	public void setAdmnLvlOptionAsscId(int admnLvlOptionAsscId) {
		this.admnLvlOptionAsscId = admnLvlOptionAsscId;
	}
	/**
	 * @return Returns the benefitComponentSysId.
	 */
	public int getBenefitComponentSysId() {
		return benefitComponentSysId;
	}
	/**
	 * @param benefitComponentSysId The benefitComponentSysId to set.
	 */
	public void setBenefitComponentSysId(int benefitComponentSysId) {
		this.benefitComponentSysId = benefitComponentSysId;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the tierQuestionnareList.
	 */
	public List getTierQuestionnareList() {
		return tierQuestionnareList;
	}
	/**
	 * @param tierQuestionnareList The tierQuestionnareList to set.
	 */
	public void setTierQuestionnareList(List tierQuestionnareList) {
		this.tierQuestionnareList = tierQuestionnareList;
	}
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
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
	 * @return Returns the benftAdminSysId.
	 */
	public int getBenftAdminSysId() {
		return benftAdminSysId;
	}
	/**
	 * @param benftAdminSysId The benftAdminSysId to set.
	 */
	public void setBenftAdminSysId(int benftAdminSysId) {
		this.benftAdminSysId = benftAdminSysId;
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
