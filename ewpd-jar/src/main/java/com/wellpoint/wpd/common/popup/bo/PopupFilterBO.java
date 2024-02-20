/*
 * Created on Oct 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.popup.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.wpd.business.util.BusinessConstants;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PopupFilterBO {
	
	private int code;
	
	private String description;
	
	private String segment;	
	
	private String spsId;	
	
	private String cobolValue;	

	private String segmentNumber;
	
	private String headerName;
	
	private int entitySysId;
	
	HashMap hashMap = new HashMap();
	
	private String queryName;
	
	private String ruleId;
	
	private String rulePVA;
	
	private String generatedRuleId;
	
	private String productRuleSysId;
	
	private int benefitLvlAdmnSysId;
	
	private String keyValue;
	
	private String ruleType;
	
	private Date effectiveDate;
	private List spsIdList;
	private int adminMethodNo;
	private int adminMethodSysId;
	


	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * @return Returns the keyValue.
	 */
	public String getKeyValue() {
		return keyValue;
	}
	/**
	 * @param keyValue The keyValue to set.
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	/**
	 * @return Returns the cobolValue.
	 */
	public String getCobolValue() {
		return cobolValue;
	}
	/**
	 * @param cobolValue The cobolValue to set.
	 */
	public void setCobolValue(String cobolValue) {
		this.cobolValue = cobolValue;
	}
	/**
	 * @return Returns the spsId.
	 */
	public String getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	/**
	 * @return Returns the segment.
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * @param segment The segment to set.
	 */
	public void setSegment(String segment) {
		this.segment = segment;
	}
	/**
	 * @return Returns the segmentNumber.
	 */
	public String getSegmentNumber() {
		return segmentNumber;
	}
	/**
	 * @param segmentNumber The segmentNumber to set.
	 */
	public void setSegmentNumber(String segmentNumber) {
		this.segmentNumber = segmentNumber;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the headerName.
	 */
	public String getHeaderName() {
		return headerName;
	}
	/**
	 * @param headerName The headerName to set.
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	/**
	 * @return Returns the code.
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(int code) {
		this.code = code;
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
	 * @return Returns the hashMap.
	 */
	public HashMap getHashMap() {
		return hashMap;
	}
	/**
	 * @param hashMap The hashMap to set.
	 */
	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}
	/**
	 * @return Returns the queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName The queryName to set.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the generatedRuleId.
	 */
	public String getGeneratedRuleId() {
		return generatedRuleId;
	}
	/**
	 * @param generatedRuleId The generatedRuleId to set.
	 */
	public void setGeneratedRuleId(String generatedRuleId) {
		this.generatedRuleId = generatedRuleId;
	}
	/**
	 * @return Returns the rulePVA.
	 */
	public String getRulePVA() {
		return rulePVA;
	}
	/**
	 * @param rulePVA The rulePVA to set.
	 */
	public void setRulePVA(String rulePVA) {
		this.rulePVA = rulePVA;
	}
	/**
	 * @return Returns the productRuleSysId.
	 */
	public String getProductRuleSysId() {
		return productRuleSysId;
	}
	/**
	 * @param productRuleSysId The productRuleSysId to set.
	 */
	public void setProductRuleSysId(String productRuleSysId) {
		this.productRuleSysId = productRuleSysId;
	}
		/**
	 * @param benefitLvlAdmnSysId The benefitLvlAdmnSysId to set.
	 */
	public void setBenefitLvlAdmnSysId(int benefitLvlAdmnSysId) {
		this.benefitLvlAdmnSysId = benefitLvlAdmnSysId;
	}
	/**
	 * @return Returns the benefitLvlAdmnSysId.
	 */
	public int getBenefitLvlAdmnSysId() {
		return benefitLvlAdmnSysId;
	}
	
	/**
	 * The method returns whether the rule is a Header or Blaze rule.
	 * This is for display purposes in the rule popup.
	 * @param ruleType
	 * @return
	 */
	public String getRuleTypeName() {
		String ruleTypeName = null;
		//if rule type is "WPDAUTOBG",then return "HEADER"
		if(BusinessConstants.RULE_TYPE_CD_WPDAUTOBG.equals(this.ruleType)){
			ruleTypeName = BusinessConstants.RULE_TYPE_HEADER;
		}else if(BusinessConstants.RULE_TYPE_CD_BLZWPDAB.equals(this.ruleType)){
         //if rule type is "BLZWPDAB",then return "BLAZE"
			ruleTypeName = BusinessConstants.RULE_TYPE_BLAZE;
		}
		return ruleTypeName;
	}
	/**
	 * @return Returns the spsIdList.
	 */
	public List getSpsIdList() {
		return spsIdList;
	}
	/**
	 * @param spsIdList The spsIdList to set.
	 */
	public void setSpsIdList(List spsIdList) {
		this.spsIdList = spsIdList;
	}
	/**
	 * @return Returns the adminMethodNo.
	 */
	public int getAdminMethodNo() {
		return adminMethodNo;
	}
	/**
	 * @param adminMethodNo The adminMethodNo to set.
	 */
	public void setAdminMethodNo(int adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}
	/**
	 * @return Returns the adminMethodSysId.
	 */
	public int getAdminMethodSysId() {
		return adminMethodSysId;
	}
	/**
	 * @param adminMethodSysId The adminMethodSysId to set.
	 */
	public void setAdminMethodSysId(int adminMethodSysId) {
		this.adminMethodSysId = adminMethodSysId;
	}
}
