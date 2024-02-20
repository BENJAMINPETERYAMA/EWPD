/*
 * ContractSPSBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSPSBO extends BusinessObject{

	private String primaryCode ;
	
	private int benefitComponentId;
	
	private int benefitId;
	
	private String benefitValue;
	
	private String benefitLevelDesc;
	
	private String dataTypeLegend;
	
	private String benefitLineDesc;
	
	private int questionNumber;
	
	private String questionDesc;
	
	private String answerDesc;
	
	private int dateSegment;
	
	private int benefitAdministrationId;
	
	private String referenceDesc;
	
	private int benefitLineId;
	
	private int adminLevelOptionSysId;
	
	private int productSysId;
	
	private int parentAdminOptionId;
	
	private int benefitComponentSeq;
	
	private int benefitSeq;
	
	private String providerArrangement;
	
	private String displayIndicator;
	
	private String adminOptionName;
	
	private String benefitName;
	
	private int questionnaireId;
	
	private int contractTierSysId;
	
	private String tierCode;	
	//CARS START
	//DESCRIPTION : holds Frequency value
	private int frequencyValue;	
	//CARS END
	
	private String contractId;
	
	private String deletedContractIndicator;
	
	private String crtriaVal;
	
	private int tierDefSysid;
	
	private int displaySeqNumber;
	
	private String noteText;
	
	public ContractSPSBO() {
		
	}
	
	public ContractSPSBO(ContractDFQuestionnaire question) {
		this.benefitComponentId = question.getComponentSysId();
		this.benefitId = question.getBenefitSysId();
		this.benefitAdministrationId = question.getBenefitAdminId();
		this.primaryCode = question.getReferenceId();
		this.displayIndicator = String.valueOf(question.getDataFeedFlag());
		this.answerDesc = question.getAnswerDesc();
		this.questionDesc = question.getQuestionDesc();
		this.dataTypeLegend = question.getDataType();
		this.parentAdminOptionId = question.getAdminOptionId();
		this.providerArrangement = question.getPva();
		this.adminOptionName = question.getAdminOptionName();
		this.adminLevelOptionSysId = question.getAdminOptionAssnId();
		this.questionnaireId = question.getQuestionnaireId();
		this.noteText = question.getNoteText();
		this.benefitName = question.getBenefitName();
		
		this.tierCode = question.getTierCode();
		this.contractTierSysId = question.getCntrctTierSysId();
	}
	
	public String getNoteText() {
		return noteText;
	}
	
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	/**
	 * @return Returns the crtriaVal.
	 */
	public String getCrtriaVal() {
		return crtriaVal;
	}
	/**
	 * @param crtriaVal The crtriaVal to set.
	 */
	public void setCrtriaVal(String crtriaVal) {
		this.crtriaVal = crtriaVal;
	}
		
	/**
	 * @return Returns the displaySeqNumber.
	 */
	public int getDisplaySeqNumber() {
		return displaySeqNumber;
	}
	/**
	 * @param displaySeqNumber The displaySeqNumber to set.
	 */
	public void setDisplaySeqNumber(int displaySeqNumber) {
		this.displaySeqNumber = displaySeqNumber;
	}
	/**
	 * @return Returns the tierDefSysid.
	 */
	public int getTierDefSysid() {
		return tierDefSysid;
	}
	/**
	 * @param tierDefSysid The tierDefSysid to set.
	 */
	public void setTierDefSysid(int tierDefSysid) {
		this.tierDefSysid = tierDefSysid;
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
	 * @return Returns the benefitAdministrationId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}
	/**
	 * @param benefitAdministrationId The benefitAdministrationId to set.
	 */
	public void setBenefitAdministrationId(int benefitAdministrationId) {
		this.benefitAdministrationId = benefitAdministrationId;
	}
	/**
	 * @return Returns the dateSegment.
	 */
	public int getDateSegment() {
		return dateSegment;
	}
	/**
	 * @param dateSegment The dateSegment to set.
	 */
	public void setDateSegment(int dateSegment) {
		this.dateSegment = dateSegment;
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
	 * @return Returns the benefitLevelDesc.
	 */
	public String getBenefitLevelDesc() {
		return benefitLevelDesc;
	}
	/**
	 * @param benefitLevelDesc The benefitLevelDesc to set.
	 */
	public void setBenefitLevelDesc(String benefitLevelDesc) {
		this.benefitLevelDesc = benefitLevelDesc;
	}
	/**
	 * @return Returns the benefitLineDesc.
	 */
	public String getBenefitLineDesc() {
		return benefitLineDesc;
	}
	/**
	 * @param benefitLineDesc The benefitLineDesc to set.
	 */
	public void setBenefitLineDesc(String benefitLineDesc) {
		this.benefitLineDesc = benefitLineDesc;
	}
	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}
	/**
	 * @param benefitValue The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataTypeLegend() {
		return dataTypeLegend;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataTypeLegend(String dataType) {
		this.dataTypeLegend = dataType;
	}
	/**
	 * @return Returns the primaryCode.
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}
	/**
	 * @param primaryCode The primaryCode to set.
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
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
	 * @return Returns the benefitLineId.
	 */
	public int getBenefitLineId() {
		return benefitLineId;
	}
	/**
	 * @param benefitLineId The benefitLineId to set.
	 */
	public void setBenefitLineId(int benefitLineId) {
		this.benefitLineId = benefitLineId;
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
	 * @return Returns the parentAdminOptionId.
	 */
	public int getParentAdminOptionId() {
		return parentAdminOptionId;
	}
	/**
	 * @param parentAdminOptionId The parentAdminOptionId to set.
	 */
	public void setParentAdminOptionId(int parentAdminOptionId) {
		this.parentAdminOptionId = parentAdminOptionId;
	}
	/**
	 * @return Returns the benefitComponentSeq.
	 */
	public int getBenefitComponentSeq() {
		return benefitComponentSeq;
	}
	/**
	 * @param benefitComponentSeq The benefitComponentSeq to set.
	 */
	public void setBenefitComponentSeq(int benefitComponentSeq) {
		this.benefitComponentSeq = benefitComponentSeq;
	}
	/**
	 * @return Returns the benefitSeq.
	 */
	public int getBenefitSeq() {
		return benefitSeq;
	}
	/**
	 * @param benefitSeq The benefitSeq to set.
	 */
	public void setBenefitSeq(int benefitSeq) {
		this.benefitSeq = benefitSeq;
	}
	/**
	 * @return Returns the providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * @param providerArrangement The providerArrangement to set.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * @return Returns the displayIndicator.
	 */
	public String getDisplayIndicator() {
		return displayIndicator;
	}
	/**
	 * @param displayIndicator The displayIndicator to set.
	 */
	public void setDisplayIndicator(String displayIndicator) {
		this.displayIndicator = displayIndicator;
	}
	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}
	/**
	 * @param adminOptionName The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
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
	 * @return Returns the contractTierSysId.
	 */
	public int getContractTierSysId() {
		return contractTierSysId;
	}
	/**
	 * @param contractTierSysId The contractTierSysId to set.
	 */
	public void setContractTierSysId(int contractTierSysId) {
		this.contractTierSysId = contractTierSysId;
	}
	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}
	/**
	 * @param tierCode The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	/**
	 * @return Returns the frequencyValue.
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}
	/**
	 * @param frequencyValue The frequencyValue to set.
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the deletedContractIndicator.
	 */
	public String getDeletedContractIndicator() {
		return deletedContractIndicator;
	}
	/**
	 * @param deletedContractIndicator The deletedContractIndicator to set.
	 */
	public void setDeletedContractIndicator(String deletedContractIndicator) {
		this.deletedContractIndicator = deletedContractIndicator;
	}
}
