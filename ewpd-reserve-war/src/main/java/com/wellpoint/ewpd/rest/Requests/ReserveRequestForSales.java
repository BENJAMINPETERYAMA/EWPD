package com.wellpoint.ewpd.rest.Requests;

import java.util.ArrayList;

import com.wellpoint.ewpd.rest.model.Riders;

/**
 * @author AF17779
 *
 */

public class ReserveRequestForSales {
	
	
	String baseContract;
	String planModifiedInd;
	String actwiseIndicator;
	String engageIndicators;
	String hpcc;
	String fundingTypeCode;
	String stateCode;
	String quoteLineItem;
	String effectiveDate;
	String groupSize;
	String baseContractType;
	String cepIndicator;
	String performanceGuarantee;
	String wgsCaseId;
	String wgsGroupId;
	String poolingStatus;
	String callingSystem;
	String transactionId;
	String transactionTimestamp;
	String lob;
	String implRecordId;
	String wgsGroupName;
	String groupType;
	String wgsGroupMigrationDate;
	String hcrStatus;
	ArrayList<Riders> riders;
	
	
	public ReserveRequestForSales(){
		
	}
	
	
	public ReserveRequestForSales(String baseContract, String planModifiedInd,
			String actwiseIndicator, String engageIndicators, String hpcc,
			String fundingTypeCode, String stateCode, String quoteLineItem,
			String effectiveDate, String groupSize,String baseContractType,
			String cepIndicator, String performanceGuarantee, String wgsCaseId,
			String wgsGroupId, String poolingStatus, String callingSystem,
			String transactionId, String transactionTimestamp,String lob,
			String implRecordId,String wgsGroupName,String groupType,
			String wgsGroupMigrationDate,String hcrStatus,ArrayList<Riders> riders) {
		super();
		this.baseContract = baseContract;
		this.planModifiedInd = planModifiedInd;
		this.actwiseIndicator = actwiseIndicator;
		this.engageIndicators = engageIndicators;
		this.hpcc = hpcc;
		this.fundingTypeCode = fundingTypeCode;
		this.stateCode = stateCode;
		this.quoteLineItem = quoteLineItem;
		this.effectiveDate = effectiveDate;
		this.groupSize = groupSize;
		this.baseContractType = baseContractType;
		this.cepIndicator = cepIndicator;
		this.performanceGuarantee = performanceGuarantee;
		this.wgsCaseId = wgsCaseId;
		this.wgsGroupId = wgsGroupId;
		this.poolingStatus = poolingStatus;
		this.callingSystem = callingSystem;
		this.transactionId = transactionId;
		this.transactionTimestamp = transactionTimestamp;
		this.lob=lob;
		this.implRecordId = implRecordId;
		this.wgsGroupName= wgsGroupName;
		this.groupType=groupType;
		this.wgsGroupMigrationDate = wgsGroupMigrationDate;
		this.hcrStatus=hcrStatus;
		this.riders = riders;
	}
	


	/**
	 * @return the baseContract
	 */
	public String getBaseContract() {
		return baseContract;
	}


	/**
	 * @param baseContract the baseContract to set
	 */
	public void setBaseContract(String baseContract) {
		this.baseContract = baseContract;
	}


	/**
	 * @return the planModifiedInd
	 */
	public String getPlanModifiedInd() {
		return planModifiedInd;
	}


	/**
	 * @param planModifiedInd the planModifiedInd to set
	 */
	public void setPlanModifiedInd(String planModifiedInd) {
		this.planModifiedInd = planModifiedInd;
	}


	/**
	 * @return the actwiseIndicator
	 */
	public String getActwiseIndicator() {
		return actwiseIndicator;
	}


	/**
	 * @param actwiseIndicator the actwiseIndicator to set
	 */
	public void setActwiseIndicator(String actwiseIndicator) {
		this.actwiseIndicator = actwiseIndicator;
	}


	/**
	 * @return the engageIndicators
	 */
	public String getEngageIndicators() {
		return engageIndicators;
	}


	/**
	 * @param engageIndicators the engageIndicators to set
	 */
	public void setEngageIndicators(String engageIndicators) {
		this.engageIndicators = engageIndicators;
	}


	/**
	 * @return the hpcc
	 */
	public String getHpcc() {
		return hpcc;
	}


	/**
	 * @param hpcc the hpcc to set
	 */
	public void setHpcc(String hpcc) {
		this.hpcc = hpcc;
	}


	/**
	 * @return the fundingTypeCode
	 */
	public String getFundingTypeCode() {
		return fundingTypeCode;
	}


	/**
	 * @param fundingTypeCode the fundingTypeCode to set
	 */
	public void setFundingTypeCode(String fundingTypeCode) {
		this.fundingTypeCode = fundingTypeCode;
	}


	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}


	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


	/**
	 * @return the qouteLineItem
	 */
	public String getQuoteLineItem() {
		return quoteLineItem;
	}


	/**
	 * @param quoteLineItem the qouteLineItem to set
	 */
	public void setQouteLineItem(String quoteLineItem) {
		this.quoteLineItem = quoteLineItem;
	}


	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}


	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	/**
	 * @return the groupSize
	 */
	public String getGroupSize() {
		return groupSize;
	}


	/**
	 * @param groupSize the groupSize to set
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	
	/**
	 * @return the baseContractType
	 */
	public String getBaseContractType() {
		return baseContractType;
	}


	/**
	 * @param baseContractType the baseContractType to set
	 */
	public void setBaseContractType(String baseContractType) {
		this.baseContractType = baseContractType;
	}


	/**
	 * @return the cepIndicator
	 */
	public String getCepIndicator() {
		return cepIndicator;
	}


	/**
	 * @param cepIndicator the cepIndicator to set
	 */
	public void setCepIndicator(String cepIndicator) {
		this.cepIndicator = cepIndicator;
	}


	/**
	 * @return the performanceGuarantee
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}


	/**
	 * @param performanceGuarantee the performanceGuarantee to set
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}


	/**
	 * @return the wgsCaseId
	 */
	public String getWgsCaseId() {
		return wgsCaseId;
	}


	/**
	 * @param wgsCaseId the wgsCaseId to set
	 */
	public void setWgsCaseId(String wgsCaseId) {
		this.wgsCaseId = wgsCaseId;
	}


	/**
	 * @return the wgsGroupId
	 */
	public String getWgsGroupId() {
		return wgsGroupId;
	}


	/**
	 * @param wgsGroupId the wgsGroupId to set
	 */
	public void setWgsGroupId(String wgsGroupId) {
		this.wgsGroupId = wgsGroupId;
	}


	/**
	 * @return the poolingStatus
	 */
	public String getPoolingStatus() {
		return poolingStatus;
	}


	/**
	 * @param poolingStatus the poolingStatus to set
	 */
	public void setPoolingStatus(String poolingStatus) {
		this.poolingStatus = poolingStatus;
	}


	/**
	 * @return the callingSystem
	 */
	public String getCallingSystem() {
		return callingSystem;
	}


	/**
	 * @param callingSystem the callingSystem to set
	 */
	public void setCallingSystem(String callingSystem) {
		this.callingSystem = callingSystem;
	}


	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}


	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	/**
	 * @return the transactionTimestamp
	 */
	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}


	/**
	 * @param transactionTimestamp the transactionTimestamp to set
	 */
	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}


	/**
	 * @return the lob
	 */
	public String getLob() {
		return lob;
	}


	/**
	 * @param lob the lob to set
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}


	/**
	 * @return the implRecordId
	 */
	public String getImplRecordId() {
		return implRecordId;
	}


	/**
	 * @param implRecordId the implRecordId to set
	 */
	public void setImplRecordId(String implRecordId) {
		this.implRecordId = implRecordId;
	}


	/**
	 * @return the wgsGroupName
	 */
	public String getWgsGroupName() {
		return wgsGroupName;
	}


	/**
	 * @param wgsGroupName the wgsGroupName to set
	 */
	public void setWgsGroupName(String wgsGroupName) {
		this.wgsGroupName = wgsGroupName;
	}


	/**
	 * @return the groupType
	 */
	public String getGroupType() {
		return groupType;
	}


	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}


	/**
	 * @return the wgsGroupMigrationDate
	 */
	public String getWgsGroupMigrationDate() {
		return wgsGroupMigrationDate;
	}


	/**
	 * @param wgsGroupMigrationDate the wgsGroupMigrationDate to set
	 */
	public void setWgsGroupMigrationDate(String wgsGroupMigrationDate) {
		this.wgsGroupMigrationDate = wgsGroupMigrationDate;
	}


	/**
	 * @return the hcrStatus
	 */
	public String getHcrStatus() {
		return hcrStatus;
	}


	/**
	 * @param hcrStatus the hcrStatus to set
	 */
	public void setHcrStatus(String hcrStatus) {
		this.hcrStatus = hcrStatus;
	}



	/**
	 * @return the riders
	 */
	public ArrayList<Riders> getRiders() {
		return riders;
	}


	/**
	 * @param riders the riders to set
	 */
	public void setRiders(ArrayList<Riders> riders) {
		this.riders = riders;
	}


	/**
	 * @param quoteLineItem the quoteLineItem to set
	 */
	public void setQuoteLineItem(String quoteLineItem) {
		this.quoteLineItem = quoteLineItem;
	}

	
	
	
}
