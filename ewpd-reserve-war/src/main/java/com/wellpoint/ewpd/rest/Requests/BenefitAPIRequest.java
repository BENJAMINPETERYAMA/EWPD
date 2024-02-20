package com.wellpoint.ewpd.rest.Requests;

public class BenefitAPIRequest {
	
	private String action;
	
	private String medicalContract;
	
	private String hpcc;
	
	private String assnEffectiveDate;
	
	private String txnId;
	
	private String txnTimestamp;

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the medicalContract
	 */
	public String getMedicalContract() {
		return medicalContract;
	}

	/**
	 * @param medicalContract the medicalContract to set
	 */
	public void setMedicalContract(String medicalContract) {
		this.medicalContract = medicalContract;
	}

	/**
	 * @return the hpcc
	 */
	public String getHpcc() {
		return hpcc;
	}

	/**
	 * @param hpcc the hpcc to set
	 */
	public void setHpcc(String hpcc) {
		this.hpcc = hpcc;
	}

	/**
	 * @return the assnEffectiveDate
	 */
	public String getAssnEffectiveDate() {
		return assnEffectiveDate;
	}

	/**
	 * @param assnEffectiveDate the assnEffectiveDate to set
	 */
	public void setAssnEffectiveDate(String assnEffectiveDate) {
		this.assnEffectiveDate = assnEffectiveDate;
	}

	/**
	 * @return the txnId
	 */
	public String getTxnId() {
		return txnId;
	}

	/**
	 * @param txnId the txnId to set
	 */
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	/**
	 * @return the txnTimestamp
	 */
	public String getTxnTimestamp() {
		return txnTimestamp;
	}

	/**
	 * @param txnTimestamp the txnTimestamp to set
	 */
	public void setTxnTimestamp(String txnTimestamp) {
		this.txnTimestamp = txnTimestamp;
	}

}
