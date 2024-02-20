/**
 * 
 */
package com.wellpoint.ewpd.rest.model;

import org.apache.log4j.Logger;

import com.wellpoint.ewpd.rest.dao.ReserveRequestDao;

/**
 * @author AF37766
 *
 */
public class EnquiryResponse {
	
	
	private static final Logger logger = Logger.getLogger(EnquiryResponse.class); 
	
	

	private String message;
	
	private Domain domain;
	
	private String contractCode;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	//private String effectiveDate;
	
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	//@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd")
	//private String  expiryDate;
	//private String comment;
	//private long contractCodeCount;
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
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	



}
