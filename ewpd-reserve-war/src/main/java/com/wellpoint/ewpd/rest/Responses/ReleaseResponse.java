/**
 * 
 */
package com.wellpoint.ewpd.rest.Responses;

import com.wellpoint.ewpd.rest.Responses.Input;

/**
 * @author AF37766
 *
 */
public class ReleaseResponse {
	
	private String txnId; 
	private String txnTimestamp;
	private String txnStatus;
	private Input input;
	private Status status;
	
	
	
	
	
	
	
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
	 * @return the txnStatus
	 */
	public String getTxnStatus() {
		return txnStatus;
	}
	/**
	 * @param txnStatus the txnStatus to set
	 */
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
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
	
}
