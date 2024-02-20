/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductQuestionareBO extends Questionnaire{
	
	private int benefitDefenitionId;
	
	private int benefitId;

    private String benefitName;
    
    private int questionId;
    
    private int selectedAnswerid;
    
    private List possibleAnswerList;
    
    private String questionName;
    
    private String selectedAnswerDesc;
    
    private int questionOrder;
    
    private int adminLevelOptionSysId;
    
    private String referenceId;
    
    private String referenceDesc;
    
    private int benefitComponentId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int productSysId;
    
    private int admnLvlAsscId;
    
    private int entitySysId;
    
    private String notes_exists;
    
 	private boolean noteAttached = false;

	private boolean noNoteAttached = true;
	
	private String validDomainToAttach;
	
	private boolean unsavedData = true;
	
	private String questionPVA;
	


	public boolean isNoNoteAttached() {
		
		if(("N").equalsIgnoreCase(this.getValidDomainToAttach()))
			return false;

		if ("Y".equalsIgnoreCase(this.notes_exists))
			noNoteAttached = false;

		return noNoteAttached;
	}

	public void setNoNoteAttached(boolean noNoteAttached) {
		this.noNoteAttached = noNoteAttached;
	}

	public boolean isNoteAttached() {

		if(("N").equalsIgnoreCase(this.getValidDomainToAttach()))
			return false;
		
		if ("Y".equalsIgnoreCase(this.notes_exists))
			noteAttached = true;

		return noteAttached;
	}

	public void setNoteAttached(boolean noteAttached) {
		this.noteAttached = noteAttached;
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
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the possibleAnswerList.
	 */
	public List getPossibleAnswerList() {
		return possibleAnswerList;
	}
	/**
	 * @param possibleAnswerList The possibleAnswerList to set.
	 */
	public void setPossibleAnswerList(List possibleAnswerList) {
		this.possibleAnswerList = possibleAnswerList;
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
	 * @return Returns the questionOrder.
	 */
	public int getQuestionOrder() {
		return questionOrder;
	}
	/**
	 * @param questionOrder The questionOrder to set.
	 */
	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
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
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
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
	 * @return Returns the selectedAnswerid.
	 */
	public int getSelectedAnswerid() {
		return selectedAnswerid;
	}
	/**
	 * @param selectedAnswerid The selectedAnswerid to set.
	 */
	public void setSelectedAnswerid(int selectedAnswerid) {
		this.selectedAnswerid = selectedAnswerid;
	}
	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	/**
	 * @return Returns the benefitDefenitionId.
	 */
	public int getBenefitDefenitionId() {
		return benefitDefenitionId;
	}
	/**
	 * @param benefitDefenitionId The benefitDefenitionId to set.
	 */
	public void setBenefitDefenitionId(int benefitDefenitionId) {
		this.benefitDefenitionId = benefitDefenitionId;
	}
	/**
	 * @return Returns the notes_exists.
	 */
	public String getNotes_exists() {
		return notes_exists;
	}
	/**
	 * @param notes_exists The notes_exists to set.
	 */
	public void setNotes_exists(String notes_exists) {
		this.notes_exists = notes_exists;
	}
	public String getValidDomainToAttach() {
		return validDomainToAttach;
	}
	public void setValidDomainToAttach(String validDomainToAttach) {
		this.validDomainToAttach = validDomainToAttach;
	}
	/**
	 * @return Returns the unsavedData.
	 */
	public boolean isUnsavedData() {
		return unsavedData;
	}
	/**
	 * @param unsavedData The unsavedData to set.
	 */
	public void setUnsavedData(boolean unsavedData) {
		this.unsavedData = unsavedData;
	}
	/**
	 * @return Returns the questionPVA.
	 */
	public String getQuestionPVA() {
		return questionPVA;
	}
	/**
	 * @param questionPVA The questionPVA to set.
	 */
	public void setQuestionPVA(String questionPVA) {
		this.questionPVA = questionPVA;
	}
	
	
}
