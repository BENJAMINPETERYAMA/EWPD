package com.wellpoint.ewpd.rest.Responses;

import java.util.List;

public class BenefitAPIResponse {
	
	private String txnId;
	
	private String txnTimestamp;
	
	private Status status;
	
	private List<HpccAssociations> hpccAssociations;

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

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the hpccAssociations
	 */
	public List<HpccAssociations> getHpccAssociations() {
		return hpccAssociations;
	}

	/**
	 * @param hpccAssociations the hpccAssociations to set
	 */
	public void setHpccAssociations(List<HpccAssociations> hpccAssociations) {
		this.hpccAssociations = hpccAssociations;
	}
	
	
}
