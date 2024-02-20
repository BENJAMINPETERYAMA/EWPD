/*
 * CP2000Contract.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class CP2000Contract extends LegacyContract {
	private int rowStatus;
	private String structure;
	private String variation;
	private String valueId;
	private String contractStatus;
	private String adjudicationInd;
	private String showNotesInd;
	private String docComplianceInd;
	private String gmisInd;
	private String multiPlanInd;
	private String benefitInqInd;
	private String comment;
	private Date adjLastUpdatedDate;
	private String itsHomeAdjInd;
	private String sendToMainframeInd;
	private String cobAdjudInd;
	private String medicareAdjudInd;
	private String asoInd;
	private String guaranteeInd;
	private String prodInd;
	private String testInd;
	private boolean displayFlag = false;
    
	/**
	 * @return Returns the displayFlag.
	 */
	public boolean isDisplayFlag() {
		return displayFlag;
	}
	/**
	 * @param displayFlag The displayFlag to set.
	 */
	public void setDisplayFlag(boolean displayFlag) {
		this.displayFlag = displayFlag;
	}
    public CP2000Contract(){
    	super(LegacyObject.SYSTEM_CP2000);
    }
    public CP2000Contract(LegacyContract contract){
    	super.copyFrom(contract);
    	super.setSystemCP2000();
    }

	/**
	 * Returns the rowStatus.
	 * @return int rowStatus.
	 */
	public int getRowStatus() {
		return rowStatus;
	}
	/**
	 * Sets the rowStatus.
	 * @param rowStatus.
	 */
	
	public void setRowStatus(int rowStatus) {
		this.rowStatus = rowStatus;
	}

	/**
	 * Returns the structure.
	 * @return String structure.
	 */
	public String getStructure() {
		return structure;
	}
	/**
	 * Sets the structure.
	 * @param structure.
	 */
	
	public void setStructure(String structure) {
		this.structure = structure;
	}


	/**
	 * Returns the variation.
	 * @return String variation.
	 */
	public String getVariation() {
		return variation;
	}
	/**
	 * Sets the variation.
	 * @param variation.
	 */
	
	public void setVariation(String variation) {
		this.variation = variation;
	}
	/**
	 * Returns the contractStatus.
	 * @return String contractStatus.
	 */
	public String getContractStatus() {
		return contractStatus;
	}
	/**
	 * Sets the contractStatus.
	 * @param contractStatus.
	 */
	
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	/**
	 * Returns the adjudicationInd.
	 * @return String adjudicationInd.
	 */
	public String getAdjudicationInd() {
		return adjudicationInd;
	}
	/**
	 * Sets the adjudicationInd.
	 * @param adjudicationInd.
	 */
	
	public void setAdjudicationInd(String adjudicationInd) {
		this.adjudicationInd = adjudicationInd;
	}

	/**
	 * Returns the showNotesInd.
	 * @return String showNotesInd.
	 */
	public String getShowNotesInd() {
		return showNotesInd;
	}
	/**
	 * Sets the showNotesInd.
	 * @param showNotesInd.
	 */
	
	public void setShowNotesInd(String showNotesInd) {
		this.showNotesInd = showNotesInd;
	}

	/**
	 * Returns the docComplianceInd.
	 * @return String docComplianceInd.
	 */
	public String getDocComplianceInd() {
		return docComplianceInd;
	}
	/**
	 * Sets the docComplianceInd.
	 * @param docComplianceInd.
	 */
	
	public void setDocComplianceInd(String docComplianceInd) {
		this.docComplianceInd = docComplianceInd;
	}
	/**
	 * Returns the gmisInd.
	 * @return String gmisInd.
	 */
	public String getGmisInd() {
		return gmisInd;
	}
	/**
	 * Sets the gmisInd.
	 * @param gmisInd.
	 */
	
	public void setGmisInd(String gmisInd) {
		this.gmisInd = gmisInd;
	}
	/**
	 * Returns the multiPlanInd.
	 * @return String multiPlanInd.
	 */
	public String getMultiPlanInd() {
		return multiPlanInd;
	}
	/**
	 * Sets the multiPlanInd.
	 * @param multiPlanInd.
	 */
	
	public void setMultiPlanInd(String multiPlanInd) {
		this.multiPlanInd = multiPlanInd;
	}
	/**
	 * Returns the benefitInqInd.
	 * @return String benefitInqInd.
	 */
	public String getBenefitInqInd() {
		return benefitInqInd;
	}
	/**
	 * Sets the benefitInqInd.
	 * @param benefitInqInd.
	 */
	
	public void setBenefitInqInd(String benefitInqInd) {
		this.benefitInqInd = benefitInqInd;
	}

	/**
	 * Returns the comment.
	 * @return String comment.
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * Sets the comment.
	 * @param comment.
	 */
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the adjLastUpdatedDate.
	 * @return Date adjLastUpdatedDate.
	 */
	public Date getAdjLastUpdatedDate() {
		return adjLastUpdatedDate;
	}
	/**
	 * Sets the adjLastUpdatedDate.
	 * @param adjLastUpdatedDate.
	 */
	
	public void setAdjLastUpdatedDate(Date adjLastUpdatedDate) {
		this.adjLastUpdatedDate = adjLastUpdatedDate;
	}
	/**
	 * Returns the itsHomeAdjInd.
	 * @return String itsHomeAdjInd.
	 */
	public String getItsHomeAdjInd() {
		return itsHomeAdjInd;
	}
	/**
	 * Sets the itsHomeAdjInd.
	 * @param itsHomeAdjInd.
	 */
	
	public void setItsHomeAdjInd(String itsHomeAdjInd) {
		this.itsHomeAdjInd = itsHomeAdjInd;
	}
	/**
	 * Returns the sendToMainframeInd.
	 * @return String sendToMainframeInd.
	 */
	public String getSendToMainframeInd() {
		return sendToMainframeInd;
	}
	/**
	 * Sets the sendToMainframeInd.
	 * @param sendToMainframeInd.
	 */
	
	public void setSendToMainframeInd(String sendToMainframeInd) {
		this.sendToMainframeInd = sendToMainframeInd;
	}
	/**
	 * Returns the cobAdjudInd.
	 * @return String cobAdjudInd.
	 */
	public String getCobAdjudInd() {
		return cobAdjudInd;
	}
	/**
	 * Sets the cobAdjudInd.
	 * @param cobAdjudInd.
	 */
	
	public void setCobAdjudInd(String cobAdjudInd) {
		this.cobAdjudInd = cobAdjudInd;
	}
	/**
	 * Returns the medicareAdjudInd.
	 * @return String medicareAdjudInd.
	 */
	public String getMedicareAdjudInd() {
		return medicareAdjudInd;
	}
	/**
	 * Sets the medicareAdjudInd.
	 * @param medicareAdjudInd.
	 */
	
	public void setMedicareAdjudInd(String medicareAdjudInd) {
		this.medicareAdjudInd = medicareAdjudInd;
	}

	/**
	 * Returns the asoInd.
	 * @return String asoInd.
	 */
	public String getAsoInd() {
		return asoInd;
	}
	/**
	 * Sets the asoInd.
	 * @param asoInd.
	 */
	
	public void setAsoInd(String asoInd) {
		this.asoInd = asoInd;
	}
	/**
	 * Returns the guaranteeInd.
	 * @return String guaranteeInd.
	 */
	public String getGuaranteeInd() {
		return guaranteeInd;
	}
	/**
	 * Sets the guaranteeInd.
	 * @param guaranteeInd.
	 */
	
	public void setGuaranteeInd(String guaranteeInd) {
		this.guaranteeInd = guaranteeInd;
	}

	/**
	 * Returns the prodInd.
	 * @return String prodInd.
	 */
	public String getProdInd() {
		return prodInd;
	}
	/**
	 * Sets the prodInd.
	 * @param prodInd.
	 */
	
	public void setProdInd(String prodInd) {
		this.prodInd = prodInd;
	}
	/**
	 * Returns the testInd.
	 * @return String testInd.
	 */
	public String getTestInd() {
		return testInd;
	}
	/**
	 * Sets the testInd.
	 * @param testInd.
	 */
	
	public void setTestInd(String testInd) {
		this.testInd = testInd;
	}
	/**
	 * Returns the valueId.
	 * @return String valueId.
	 */
	public String getValueId() {
		return valueId;
	}
	/**
	 * Sets the valueId.
	 * @param valueId.
	 */
	
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
}
