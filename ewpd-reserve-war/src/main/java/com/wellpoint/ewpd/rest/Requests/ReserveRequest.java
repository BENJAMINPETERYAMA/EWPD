/**
 * 
 */
package com.wellpoint.ewpd.rest.Requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wellpoint.ewpd.rest.model.Domain;

/**
 * @author AF37766
 *
 */
public class ReserveRequest {
	
	
	
	public ReserveRequest() {
		
	}
	
	
	/**
	 * @param domain
	 * @param effectiveDate
	 * @param expiryDate
	 * @param comment
	 * @param contractCodeCount
	 * @param callingSystem
	 * @param txnId
	 * @param txnTimestamp
	 */
	public ReserveRequest(Domain domain, String effectiveDate, String expiryDate,
			String comment, String contractCodeCount, String callingSystem,
			String txnId, String txnTimestamp) {
		super();
		this.domain = domain;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.comment = comment;
		this.contractCodeCount = contractCodeCount;
		this.callingSystem = callingSystem;
		this.txnId = txnId;
		this.txnTimestamp = txnTimestamp;
	}
	private Domain domain;
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String effectiveDate;
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String expiryDate;
	private String comment;
	private String contractCodeCount;
	private String callingSystem;
	private String txnId;
	private String txnTimestamp;
	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	
	
	
	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}


	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}


	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment.trim().toUpperCase();
	}
	


	/**
	 * @return the contractCodeCount
	 */
	public String getContractCodeCount() {
		return contractCodeCount;
	}


	/**
	 * @param contractCodeCount the contractCodeCount to set
	 */
	public void setContractCodeCount(String contractCodeCount) {
		this.contractCodeCount = contractCodeCount;
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
		this.callingSystem = callingSystem.trim().toUpperCase();
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
