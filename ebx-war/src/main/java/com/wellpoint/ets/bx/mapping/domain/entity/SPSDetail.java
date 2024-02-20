package com.wellpoint.ets.bx.mapping.domain.entity;

public class SPSDetail {

	private String spsType;
	
	private String spsPVA;
	
	private String spsDataType;
	
	private String spsDescription;

	
	public String getSpsDescription() {
		return spsDescription;
	}

	public void setSpsDescription(String spsDescription) {
		this.spsDescription = spsDescription;
	}

	public String getSpsDataType() {
		return spsDataType;
	}

	public void setSpsDataType(String spsDataType) {
		this.spsDataType = spsDataType;
	}

	public String getSpsPVA() {
		return spsPVA;
	}

	public void setSpsPVA(String spsPVA) {
		this.spsPVA = spsPVA;
	}

	public String getSpsType() {
		return spsType;
	}

	public void setSpsType(String spsType) {
		this.spsType = spsType;
	}
}
