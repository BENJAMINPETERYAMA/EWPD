package com.wellpoint.wpd.common.contract.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

public class IMAGEReadyBusinessDomainBO extends BusinessObject {
	
	private int domainSysID;
	private String description;

	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDomainSysID() {
		return domainSysID;
	}

	public void setDomainSysID(int domainSysID) {
		this.domainSysID = domainSysID;
	}
	

}
