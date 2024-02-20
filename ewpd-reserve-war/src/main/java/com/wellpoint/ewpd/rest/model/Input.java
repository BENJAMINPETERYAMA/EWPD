/**
 * 
 */
package com.wellpoint.ewpd.rest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author AF37766
 *
 */
public class Input {
	
	
	private Domain domain;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String effectiveDate;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	private String  expiryDate;
	private String comment;
	private long contractCodeCount;
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
		this.comment = comment;
	}
	/**
	 * @return the contractCodeCount
	 */
	public long getContractCodeCount() {
		return contractCodeCount;
	}
	/**
	 * @param contractCodeCount the contractCodeCount to set
	 */
	public void setContractCodeCount(long contractCodeCount) {
		this.contractCodeCount = contractCodeCount;
	}
	

}
