/**
 * 
 */
package com.wellpoint.ewpd.rest.Requests;

import java.util.List;
import java.util.Map;

/**
 * @author AF37766
 *
 */
public class ReleaseRequest {
	
	
	private List<String> contractCodes;
	private String callingSystem;
	private String txnId;
	private String txnTimestamp;
	
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
