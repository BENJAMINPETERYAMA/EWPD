package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;

public class ContractStatusBo {
	
	private boolean persist = false;
	
	private String contractId;
	
	private String statusCode;
	
	private Date statusDate;
	
	private String reasonCode;
	
	private boolean newlyCreated = false;
	
	private String createdUserId;
	
	private String lastChangedUserId;
	
	private Date createdDate;
	
	private Date lastChangedDate;
	
	private String reasonCodeDescription;
	
	private String termedContractId;

	public String getTermedContractId() {
		return termedContractId;
	}

	public void setTermedContractId(String termedContractId) {
		this.termedContractId = termedContractId;
	}

	public String getReasonCodeDescription() {
		return reasonCodeDescription;
	}

	public void setReasonCodeDescription(String reasonCodeDescription) {
		this.reasonCodeDescription = reasonCodeDescription;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public boolean isNewlyCreated() {
		return newlyCreated;
	}

	public void setNewlyCreated(boolean newlyCreated) {
		this.newlyCreated = newlyCreated;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	public String getLastChangedUserId() {
		return lastChangedUserId;
	}

	public void setLastChangedUserId(String lastChangedUserId) {
		this.lastChangedUserId = lastChangedUserId;
	}

	public boolean isPersist() {
		return persist;
	}

	public void setPersist(boolean persist) {
		this.persist = persist;
	}
	
	
	

}
