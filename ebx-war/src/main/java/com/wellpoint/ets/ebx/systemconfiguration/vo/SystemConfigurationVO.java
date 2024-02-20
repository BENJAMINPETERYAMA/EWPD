package com.wellpoint.ets.ebx.systemconfiguration.vo;

public class SystemConfigurationVO {
	
	private String systemConfigurationID;
	private String system;
	private String functionality;
	private String environment;
	private String backEndRegion;
	private String backEndRegionDescription;
	private String senderID;
	private String sourceEnvironment;
	private String destinationEnvironment;
	
	/**
	 * 
	 * @return
	 */
	public String getSystemConfigurationID() {
		return systemConfigurationID;
	}
	
	/**
	 * 
	 * @param systemConfigurationID
	 */
	public void setSystemConfigurationID(String systemConfigurationID) {
		this.systemConfigurationID = systemConfigurationID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSystem() {
		return system;
	}
	
	/**
	 * 
	 * @param system
	 */
	public void setSystem(String system) {
		this.system = system;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFunctionality() {
		return functionality;
	}
	
	/**
	 * 
	 * @param functionality
	 */
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEnvironment() {
		return environment;
	}
	
	/**
	 * 
	 * @param environment
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBackEndRegion() {
		return backEndRegion;
	}
	
	/**
	 * 
	 * @param backEndRegion
	 */
	public void setBackEndRegion(String backEndRegion) {
		this.backEndRegion = backEndRegion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBackEndRegionDescription() {
		return backEndRegionDescription;
	}
	
	/**
	 * 
	 * @param backEndRegionDescription
	 */
	public void setBackEndRegionDescription(String backEndRegionDescription) {
		this.backEndRegionDescription = backEndRegionDescription;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSenderID() {
		return senderID;
	}
	
	/**
	 * 
	 * @param senderID
	 */
	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSourceEnvironment() {
		return sourceEnvironment;
	}
	
	/**
	 * 
	 * @param sourceEnvironment
	 */
	public void setSourceEnvironment(String sourceEnvironment) {
		this.sourceEnvironment = sourceEnvironment;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDestinationEnvironment() {
		return destinationEnvironment;
	}
	
	/**
	 * 
	 * @param destinationEnvironment
	 */
	public void setDestinationEnvironment(String destinationEnvironment) {
		this.destinationEnvironment = destinationEnvironment;
	}
}
