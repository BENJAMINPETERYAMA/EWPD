package com.wellpoint.ewpd.rest.Responses;

import java.util.List;


import com.wellpoint.ewpd.rest.model.Status;

public class ReserveForSalesResponse {
	
	
	private String callingSystem;
	
	private String transactionId;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String transactionTimestamp;
	
	private Status status;
	//private ForSalesInput input;
	private String contractCode;
	
	/**
	 * @return the callingSystem
	 */
	public String getCallingSystem() {
		return callingSystem;
	}
	/**
	 * @param callingSystem the callingSystem to set
	 */
	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the transactionTimestamp
	 */
	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}
	/**
	 * @param transactionTimestamp the transactionTimestamp to set
	 */
	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
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
	 * @return the contractCode
	 */
	public String getContractCode() {
		return contractCode;
	}
	/**
	 * @param contractCode the contractCode to set
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	


}
