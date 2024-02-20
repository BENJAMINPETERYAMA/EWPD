/**
 * 
 */
package com.wellpoint.ewpd.rest.Responses;

import java.util.List;
import java.util.Map;

/**
 * @author AF37766
 *
 */
public class Status {
	
	//private List<Map <String, String>> contractCodes;
	
	private String code;
	private String message;
	
	
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	private List<ContractCodes> contractCodes;

	/**
	 * @return the contractCodes
	 */
	public List<ContractCodes> getContractCodes() {
		return contractCodes;
	}

	/**
	 * @param contractCodes the contractCodes to set
	 */
	public void setContractCodes(List<ContractCodes> contractCodes) {
		this.contractCodes = contractCodes;
	}
	
	

}
