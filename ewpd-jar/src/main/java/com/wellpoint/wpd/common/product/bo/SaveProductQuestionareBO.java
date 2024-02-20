/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;



/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveProductQuestionareBO {

	private List benefitAdminstrationList;
    
    private List questionnareList;
    
    private int benefitComponentid;
    
    private int adminLevelOptionSysId;
    
    private int productSysId;
    
    private int productPrntSysId;
    
    private List tierList;
    
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
    private List newQuestions;

    private List modifiedQuestions;
    
    private List removedQuestions;
	
    private List newTieredQuestions;

    private List modifiedTieredQuestions;
    
    private List removedTieredQuestions;
	
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
	 * @return Returns the benefitAdminstrationList.
	 */
	public List getBenefitAdminstrationList() {
		return benefitAdminstrationList;
	}
	/**
	 * @param benefitAdminstrationList The benefitAdminstrationList to set.
	 */
	public void setBenefitAdminstrationList(List benefitAdminstrationList) {
		this.benefitAdminstrationList = benefitAdminstrationList;
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
	 * @return Returns the modifiedTieredQuestions.
	 */
	public List getModifiedTieredQuestions() {
		return modifiedTieredQuestions;
	}
	/**
	 * @param modifiedTieredQuestions The modifiedTieredQuestions to set.
	 */
	public void setModifiedTieredQuestions(List modifiedTieredQuestions) {
		this.modifiedTieredQuestions = modifiedTieredQuestions;
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
	 * @return Returns the newTieredQuestions.
	 */
	public List getNewTieredQuestions() {
		return newTieredQuestions;
	}
	/**
	 * @param newTieredQuestions The newTieredQuestions to set.
	 */
	public void setNewTieredQuestions(List newTieredQuestions) {
		this.newTieredQuestions = newTieredQuestions;
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
	/**
	 * @return Returns the removedTieredQuestions.
	 */
	public List getRemovedTieredQuestions() {
		return removedTieredQuestions;
	}
	/**
	 * @param removedTieredQuestions The removedTieredQuestions to set.
	 */
	public void setRemovedTieredQuestions(List removedTieredQuestions) {
		this.removedTieredQuestions = removedTieredQuestions;
	}
}
