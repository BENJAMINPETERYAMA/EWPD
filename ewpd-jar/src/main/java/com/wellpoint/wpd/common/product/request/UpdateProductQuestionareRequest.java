/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateProductQuestionareRequest extends WPDRequest{

	private List benefitAdministrationVOList;
	
	private String mainObjectIdentifier;
	
	private List domainList;
	   
	private int entityId;
	
	private int versionNumber;
	
	private int adminOptionAssnId;
	
	private List questionnareList;
	
	private int adminlevelOptionSysId;
	
	private int productPrntSysId;
	
	private int benefitComponentId;
	
	private List changedIds;
	private boolean changed;
	private String bCompName;
	private int benefitAdminId;
	
	//	 CARS start
	private List changedProdTierIds;
	
	private List changedTierQuesIds;
	
	private boolean tierQstnsChanged = false;
	// CARS end

	private boolean qstnsChanged = false;
	
	private int benefitId;
	
	private List tierList;
	
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
	private List newQuestions;
	
	private List modifiedQuestions;
	
	private List removedQuestions;
	
	private List newTieredQuestions;
	
	private List modifiedTieredQuestions;
	
	private List removedTieredQuestions;
	
	/**
	 * @return Returns the adminlevelOptionSysId.
	 */
	public int getAdminlevelOptionSysId() {
		return adminlevelOptionSysId;
	}
	/**
	 * @param adminlevelOptionSysId The adminlevelOptionSysId to set.
	 */
	public void setAdminlevelOptionSysId(int adminlevelOptionSysId) {
		this.adminlevelOptionSysId = adminlevelOptionSysId;
	}
	/**
	 * @return Returns the adminOptionAssnId.
	 */
	public int getAdminOptionAssnId() {
		return adminOptionAssnId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	/**
	 * @param adminOptionAssnId The adminOptionAssnId to set.
	 */
	public void setAdminOptionAssnId(int adminOptionAssnId) {
		this.adminOptionAssnId = adminOptionAssnId;
	}
	/**
	 * @return Returns the benefitAdministrationVOList.
	 */
	public List getBenefitAdministrationVOList() {
		return benefitAdministrationVOList;
	}
	/**
	 * @param benefitAdministrationVOList The benefitAdministrationVOList to set.
	 */
	public void setBenefitAdministrationVOList(List benefitAdministrationVOList) {
		this.benefitAdministrationVOList = benefitAdministrationVOList;
	}
	/**
	 * @return Returns the domainList.
	 */
	public List getDomainList() {
		return domainList;
	}
	/**
	 * @param domainList The domainList to set.
	 */
	public void setDomainList(List domainList) {
		this.domainList = domainList;
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
	 * @return Returns the mainObjectIdentifier.
	 */
	public String getMainObjectIdentifier() {
		return mainObjectIdentifier;
	}
	/**
	 * @param mainObjectIdentifier The mainObjectIdentifier to set.
	 */
	public void setMainObjectIdentifier(String mainObjectIdentifier) {
		this.mainObjectIdentifier = mainObjectIdentifier;
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
	 * @return Returns the versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
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
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	/**
	 * @return Returns the changed.
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}
	/**
	 * @param compName The bCompName to set.
	 */
	public void setBCompName(String compName) {
		bCompName = compName;
	}
	/**
	 * @return Returns the qstnsChanged.
	 */
	public boolean isQstnsChanged() {
		return qstnsChanged;
	}
	/**
	 * @param qstnsChanged The qstnsChanged to set.
	 */
	public void setQstnsChanged(boolean qstnsChanged) {
		this.qstnsChanged = qstnsChanged;
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
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
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
	//CARS start
	/**
	 * @return the changedProdTierIds
	 */
	public List getChangedProdTierIds() {
		return changedProdTierIds;
	}
	/**
	 * @param changedProdTierIds the changedProdTierIds to set
	 */
	public void setChangedProdTierIds(List changedProdTierIds) {
		this.changedProdTierIds = changedProdTierIds;
	}
	/**
	 * @return the changedTierQuesIds
	 */
	public List getChangedTierQuesIds() {
		return changedTierQuesIds;
	}
	/**
	 * @param changedTierQuesIds the changedTierQuesIds to set
	 */
	public void setChangedTierQuesIds(List changedTierQuesIds) {
		this.changedTierQuesIds = changedTierQuesIds;
	}
	/**
	 * @return the tierQstnsChanged
	 */
	public boolean isTierQstnsChanged() {
		return tierQstnsChanged;
	}
	/**
	 * @param tierQstnsChanged the tierQstnsChanged to set
	 */
	public void setTierQstnsChanged(boolean tierQstnsChanged) {
		this.tierQstnsChanged = tierQstnsChanged;
	}
	//CARS end
	
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
