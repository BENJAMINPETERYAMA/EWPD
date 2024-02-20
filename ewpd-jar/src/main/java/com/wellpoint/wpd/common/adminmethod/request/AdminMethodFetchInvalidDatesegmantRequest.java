package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class AdminMethodFetchInvalidDatesegmantRequest extends WPDRequest{

	@Override
	public void validate() throws ValidationException {
	
	}
	// TODO Auto-generated method stub
	private String contractId;
	private String entityType;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}


}
