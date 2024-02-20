/*
 * Created on Jul 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.pva;


/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvalidLineInfo {
	
	private int entitySysId;
	
	private int benefitComponentId;
	
	private String benefitSysId;
	
	private String levelSysId;
	
	private String lineSysId;
	
	private String levelFlag;
	
	private String lineFlag;
	
	private String lastUpdatedUser;

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public String getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(String benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	/**
	 * @return Returns the levelFlag.
	 */
	public String getLevelFlag() {
		return levelFlag;
	}
	/**
	 * @param levelFlag The levelFlag to set.
	 */
	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}
	/**
	 * @return Returns the levelSysId.
	 */
	public String getLevelSysId() {
		return levelSysId;
	}
	/**
	 * @param levelSysId The levelSysId to set.
	 */
	public void setLevelSysId(String levelSysId) {
		this.levelSysId = levelSysId;
	}
	/**
	 * @return Returns the lineFlag.
	 */
	public String getLineFlag() {
		return lineFlag;
	}
	/**
	 * @param lineFlag The lineFlag to set.
	 */
	public void setLineFlag(String lineFlag) {
		this.lineFlag = lineFlag;
	}
	/**
	 * @return Returns the lineSysId.
	 */
	public String getLineSysId() {
		return lineSysId;
	}
	/**
	 * @param lineSysId The lineSysId to set.
	 */
	public void setLineSysId(String lineSysId) {
		this.lineSysId = lineSysId;
	}
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
}
