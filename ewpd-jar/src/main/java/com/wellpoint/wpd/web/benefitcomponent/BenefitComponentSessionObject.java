/*
 * BenefitComponentSessionObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModelBase;

import com.wellpoint.wpd.common.bo.State;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSessionObject {
	
	int benefitComponentId;	
	int benefitComponentParentId;	 
	int benefitComponentVersionNumber;
	int benefitHierarchyId;
	String benefitComponentName;
	String benefitComponentMode;
	List businessDomainList;
	Date effectiveDate;
	Date expiryDate;
	State state;
	String status;
	int version;
	String componentState;	
	
	List businessDomains;
	boolean checkout; 
	boolean copy;
	int benefitComponentParentKey;
	// Enhancements
	String bcComponentType;
	String bcMandateType;
	String bcStateId;
	List bcRuleIdList;
	String entityType;
	// End - Enhancements
	// Questionnare---start
	List questionareList;
	List orgQuestionnaireList;
	int adminLevelOptionSysId;
	// Questionnare ---End 
	HashMap allPossibleAnswerMap;
	
	//Stabilization release maps
	private Map dataHiddenOutputValDescFromSession;
	private Map dataHiddenLowerLevelValDescFromSession;
	private Map levelIdMapFromSession;
	private Map hiddenLevelFlagMapSession;
	private Map dataHiddenValTermSession;
	private Map dataHiddenValQualifierFromSession;
	private Map hiddenLineFreqValueMapFromSession;
	private Map hiddenLowerLevelFreqValueMapFromSession;
	private Map hiddenLineFlagMapFromSession;
	private Map lineIdMapFromSession;
	//Code change for benefit component tree rendering optimization
	private boolean treeStructureUpdated = false;
	private TreeModelBase benefitCmpntTree = null;
	/**
	 * @return Returns the state.
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Returns the benefitComponentId
	 * @return int benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * Sets the benefitComponentId
	 * @param benefitComponentId.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * Returns the benefitComponentMode
	 * @return String benefitComponentMode.
	 */
	public String getBenefitComponentMode() {
		return benefitComponentMode;
	}
	/**
	 * Sets the benefitComponentMode
	 * @param benefitComponentMode.
	 */
	public void setBenefitComponentMode(String benefitComponentMode) {
		this.benefitComponentMode = benefitComponentMode;
	}
	/**
	 * Returns the benefitComponentName
	 * @return String benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * Sets the benefitComponentName
	 * @param benefitComponentName.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * Returns the benefitComponentVersionNumber
	 * @return int benefitComponentVersionNumber.
	 */
	public int getBenefitComponentVersionNumber() {
		return benefitComponentVersionNumber;
	}
	/**
	 * Sets the benefitComponentVersionNumber
	 * @param benefitComponentVersionNumber.
	 */
	public void setBenefitComponentVersionNumber(
			int benefitComponentVersionNumber) {
		this.benefitComponentVersionNumber = benefitComponentVersionNumber;
	}
	
	
	/**
	 * @return Returns the businessDomainList.
	 */
	public List getBusinessDomainList() {
		return businessDomainList;
	}
	/**
	 * @param businessDomainList The businessDomainList to set.
	 */
	public void setBusinessDomainList(List businessDomainList) {
		this.businessDomainList = businessDomainList;
	}
	
	
	/**
	 * Returns the effectiveDate
	 * @return Date effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return Date expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the benefitComponentParentId
	 * @return int benefitComponentParentId.
	 */
	public int getBenefitComponentParentId() {
		return benefitComponentParentId;
	}
	/**
	 * Sets the benefitComponentParentId
	 * @param benefitComponentParentId.
	 */
	public void setBenefitComponentParentId(int benefitComponentParentId) {
		this.benefitComponentParentId = benefitComponentParentId;
	}
	
	/**
	 * Returns the benefitHierarchyId
	 * @return int benefitHierarchyId.
	 */
	public int getBenefitHierarchyId() {
		return benefitHierarchyId;
	}
	/**
	 * Sets the benefitHierarchyId
	 * @param benefitHierarchyId.
	 */
	public void setBenefitHierarchyId(int benefitHierarchyId) {
		this.benefitHierarchyId = benefitHierarchyId;
	}
	/**
	 * @return Returns the bcComponentType.
	 */
	public String getBcComponentType() {
		return bcComponentType;
	}
	/**
	 * @param bcComponentType The bcComponentType to set.
	 */
	public void setBcComponentType(String bcComponentType) {
		this.bcComponentType = bcComponentType;
	}
	/**
	 * @return Returns the bcMandateType.
	 */
	public String getBcMandateType() {
		return bcMandateType;
	}
	/**
	 * @param bcMandateType The bcMandateType to set.
	 */
	public void setBcMandateType(String bcMandateType) {
		this.bcMandateType = bcMandateType;
	}
	/**
	 * @return Returns the bcRuleIdList.
	 */
	public List getBcRuleIdList() {
		return bcRuleIdList;
	}
	/**
	 * @param bcRuleIdList The bcRuleIdList to set.
	 */
	public void setBcRuleIdList(List bcRuleIdList) {
		this.bcRuleIdList = bcRuleIdList;
	}
	
	/**
	 * @return Returns the bcStateId.
	 */
	public String getBcStateId() {
		return bcStateId;
	}
	/**
	 * @param bcStateId The bcStateId to set.
	 */
	public void setBcStateId(String bcStateId) {
		this.bcStateId = bcStateId;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
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
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * @return Returns the checkout.
	 */
	public boolean isCheckout() {
		return checkout;
	}
	/**
	 * @param checkout The checkout to set.
	 */
	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}
	/**
	 * @return Returns the copy.
	 */
	public boolean isCopy() {
		return copy;
	}
	/**
	 * @param copy The copy to set.
	 */
	public void setCopy(boolean copy) {
		this.copy = copy;
	}
	/**
	 * @return Returns the benefitComponentParentKey.
	 */
	public int getBenefitComponentParentKey() {
		return benefitComponentParentKey;
	}
	/**
	 * @param benefitComponentParentKey The benefitComponentParentKey to set.
	 */
	public void setBenefitComponentParentKey(int benefitComponentParentKey) {
		this.benefitComponentParentKey = benefitComponentParentKey;
	}
	
	/**
	 * @return Returns the componentState.
	 */
	public String getComponentState() {
		return componentState;
	}
	/**
	 * @param componentState The componentState to set.
	 */
	public void setComponentState(String componentState) {
		this.componentState = componentState;
	}
	/**
	 * @return Returns the questionareList.
	 */
	public List getQuestionareList() {
		return questionareList;
	}
	/**
	 * @param questionareList The questionareList to set.
	 */
	public void setQuestionareList(List questionareList) {
		this.questionareList = questionareList;
	}
	/**
	 * @return Returns the adminLevelOptionSysId.
	 */
	public int getAdminLevelOptionSysId() {
		return adminLevelOptionSysId;
	}
	/**
	 * @param adminLevelOptionSysId The adminLevelOptionSysId to set.
	 */
	public void setAdminLevelOptionSysId(int adminLevelOptionSysId) {
		this.adminLevelOptionSysId = adminLevelOptionSysId;
	}
	/**
	 * @return Returns the treeStructureUpdated.
	 */
	public boolean isTreeStructureUpdated() {
		return treeStructureUpdated;
	}
	/**
	 * @param treeStructureUpdated The treeStructureUpdated to set.
	 */
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		this.treeStructureUpdated = treeStructureUpdated;
	}
	/**
	 * @return Returns the benefitCmpntTree.
	 */
	public TreeModelBase getBenefitCmpntTree() {
		return benefitCmpntTree;
	}
	/**
	 * @param benefitCmpntTree The benefitCmpntTree to set.
	 */
	public void setBenefitCmpntTree(TreeModelBase benefitCmpntTree) {
		this.benefitCmpntTree = benefitCmpntTree;
	}
	/**
	 * @return Returns the allPossibleAnswerMap.
	 */
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	/**
	 * @param allPossibleAnswerMap The allPossibleAnswerMap to set.
	 */
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}
	
	/**
	 * @return Returns the orgQuestionnaireList.
	 */
	public List getOrgQuestionnaireList() {
		return orgQuestionnaireList;
	}
	/**
	 * @param orgQuestionnaireList The orgQuestionnaireList to set.
	 */
	public void setOrgQuestionnaireList(List orgQuestionnaireList) {
		this.orgQuestionnaireList = orgQuestionnaireList;
	}
	/**
	 * @return Returns the dataHiddenOutputValDescFromSession.
	 */
	public Map getDataHiddenOutputValDescFromSession() {
		return dataHiddenOutputValDescFromSession;
	}
	/**
	 * @param dataHiddenOutputValDescFromSession The dataHiddenOutputValDescFromSession to set.
	 */
	public void setDataHiddenOutputValDescFromSession(
			Map dataHiddenOutputValDescFromSession) {
		this.dataHiddenOutputValDescFromSession = dataHiddenOutputValDescFromSession;
	}
	/**
	 * @return Returns the dataHiddenLowerLevelValDescFromSession.
	 */
	public Map getDataHiddenLowerLevelValDescFromSession() {
		return dataHiddenLowerLevelValDescFromSession;
	}
	/**
	 * @param dataHiddenLowerLevelValDescFromSession The dataHiddenLowerLevelValDescFromSession to set.
	 */
	public void setDataHiddenLowerLevelValDescFromSession(
			Map dataHiddenLowerLevelValDescFromSession) {
		this.dataHiddenLowerLevelValDescFromSession = dataHiddenLowerLevelValDescFromSession;
	}
	/**
	 * @return Returns the levelIdMapFromSession.
	 */
	public Map getLevelIdMapFromSession() {
		return levelIdMapFromSession;
	}
	/**
	 * @param levelIdMapFromSession The levelIdMapFromSession to set.
	 */
	public void setLevelIdMapFromSession(Map levelIdMapFromSession) {
		this.levelIdMapFromSession = levelIdMapFromSession;
	}
	/**
	 * @return Returns the hiddenLevelFlagMapSession.
	 */
	public Map getHiddenLevelFlagMapSession() {
		return hiddenLevelFlagMapSession;
	}
	/**
	 * @param hiddenLevelFlagMapSession The hiddenLevelFlagMapSession to set.
	 */
	public void setHiddenLevelFlagMapSession(Map hiddenLevelFlagMapSession) {
		this.hiddenLevelFlagMapSession = hiddenLevelFlagMapSession;
	}
	/**
	 * @return Returns the dataHiddenValTermSession.
	 */
	public Map getDataHiddenValTermSession() {
		return dataHiddenValTermSession;
	}
	/**
	 * @param dataHiddenValTermSession The dataHiddenValTermSession to set.
	 */
	public void setDataHiddenValTermSession(Map dataHiddenValTermSession) {
		this.dataHiddenValTermSession = dataHiddenValTermSession;
	}
	/**
	 * @return Returns the dataHiddenValQualifierFromSession.
	 */
	public Map getDataHiddenValQualifierFromSession() {
		return dataHiddenValQualifierFromSession;
	}
	/**
	 * @param dataHiddenValQualifierFromSession The dataHiddenValQualifierFromSession to set.
	 */
	public void setDataHiddenValQualifierFromSession(
			Map dataHiddenValQualifierFromSession) {
		this.dataHiddenValQualifierFromSession = dataHiddenValQualifierFromSession;
	}
	/**
	 * @return Returns the hiddenLineFreqValueMapFromSession.
	 */
	public Map getHiddenLineFreqValueMapFromSession() {
		return hiddenLineFreqValueMapFromSession;
	}
	/**
	 * @param hiddenLineFreqValueMapFromSession The hiddenLineFreqValueMapFromSession to set.
	 */
	public void setHiddenLineFreqValueMapFromSession(
			Map hiddenLineFreqValueMapFromSession) {
		this.hiddenLineFreqValueMapFromSession = hiddenLineFreqValueMapFromSession;
	}
	/**
	 * @return Returns the hiddenLowerLevelFreqValueMapFromSession.
	 */
	public Map getHiddenLowerLevelFreqValueMapFromSession() {
		return hiddenLowerLevelFreqValueMapFromSession;
	}
	/**
	 * @param hiddenLowerLevelFreqValueMapFromSession The hiddenLowerLevelFreqValueMapFromSession to set.
	 */
	public void setHiddenLowerLevelFreqValueMapFromSession(
			Map hiddenLowerLevelFreqValueMapFromSession) {
		this.hiddenLowerLevelFreqValueMapFromSession = hiddenLowerLevelFreqValueMapFromSession;
	}
	/**
	 * @return Returns the hiddenLineFlagMapFromSession.
	 */
	public Map getHiddenLineFlagMapFromSession() {
		return hiddenLineFlagMapFromSession;
	}
	/**
	 * @param hiddenLineFlagMapFromSession The hiddenLineFlagMapFromSession to set.
	 */
	public void setHiddenLineFlagMapFromSession(Map hiddenLineFlagMapFromSession) {
		this.hiddenLineFlagMapFromSession = hiddenLineFlagMapFromSession;
	}
	/**
	 * @return Returns the lineIdMapFromSession.
	 */
	public Map getLineIdMapFromSession() {
		return lineIdMapFromSession;
	}
	/**
	 * @param lineIdMapFromSession The lineIdMapFromSession to set.
	 */
	public void setLineIdMapFromSession(Map lineIdMapFromSession) {
		this.lineIdMapFromSession = lineIdMapFromSession;
	}
}
