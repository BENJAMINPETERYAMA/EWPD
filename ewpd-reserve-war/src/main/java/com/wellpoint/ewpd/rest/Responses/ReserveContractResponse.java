/**
 * 
 */

package com.wellpoint.ewpd.rest.Responses;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wellpoint.ewpd.rest.model.Input;
import com.wellpoint.ewpd.rest.model.Status;

/**
 * @author AF37766
 *
 */
public class ReserveContractResponse {

	
	private String txnId;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String txnTimestamp;
	
	private Status status;
	private Input input;
	private List<String> contractCodes;
	
	
	

	
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
	 * @return the input
	 */
	public Input getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Input input) {
		this.input = input;
	}

	/**
	 * @return the contractCodes
	 */
	public List<String> getContractCodes() {
		return contractCodes;
	}

	/**
	 * @param contractCodes the contractCodes to set
	 */
	public void setContractCodes(List<String> contractCodes) {
		this.contractCodes = contractCodes;
	}

	
	
	
	
	
}
