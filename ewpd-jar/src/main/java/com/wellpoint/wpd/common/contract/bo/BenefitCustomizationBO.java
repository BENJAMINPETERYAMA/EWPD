/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitCustomizationBO {
	
	private int dateSegmentId;
	
	private int benefitComponentId;
	
	private int productId;
	
	private int benefitId;
	
	private Date lastUpdatedTime;
	    
	private String lastUpdatedUser;
	    
	private String isHidden;
	
	private List benefitList;

	
    private String questionsFlag;
    private String adminOptionFlag;
    private String entityType;
    
    private Map benefitFlagHiddenMap=new HashMap();
    
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
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the isHidden.
	 */
	public String getIsHidden() {
		return isHidden;
	}
	/**
	 * @param isHidden The isHidden to set.
	 */
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
	/**
	 * @return Returns the lastUpdatedTime.
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	/**
	 * @param lastUpdatedTime The lastUpdatedTime to set.
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the adminOptionFlag.
	 */
	public String getAdminOptionFlag() {
		return adminOptionFlag;
	}
	/**
	 * @param adminOptionFlag The adminOptionFlag to set.
	 */
	public void setAdminOptionFlag(String adminOptionFlag) {
		this.adminOptionFlag = adminOptionFlag;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the questionsFlag.
	 */
	public String getQuestionsFlag() {
		return questionsFlag;
	}
	/**
	 * @param questionsFlag The questionsFlag to set.
	 */
	public void setQuestionsFlag(String questionsFlag) {
		this.questionsFlag = questionsFlag;
	}
	public Map getBenefitFlagHiddenMap() {
		return benefitFlagHiddenMap;
	}
	public void setBenefitFlagHiddenMap(Map benefitFlagHiddenMap) {
		this.benefitFlagHiddenMap = benefitFlagHiddenMap;
	}
	
	
	
	
}
