package com.wellpoint.ets.bx.mapping.domain.entity;


public class LockAuditTrail extends AuditTrail{

	private String contractId;
	private String dateSegment;
	private String currentValue;
	private String newValue;
	private String systemIndicator;
	private String system;
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getDateSegment() {
		return dateSegment;
	}
	public void setDateSegment(String dateSegment) {
		this.dateSegment = dateSegment;
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getSystemIndicator() {
		return systemIndicator;
	}
	public void setSystemIndicator(String systemIndicator) {
		this.systemIndicator = systemIndicator;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}

}
