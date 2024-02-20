/*
 * RuleReport.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.report.bo;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
import java.io.Serializable;
import java.util.Date;

public class RuleReportBO  implements Serializable{
	
	private int keyValue;
	private String ruleID;
	private String ruleTypeDesc;
	private String ruleVersion;
	private String tag;
	private int ruleSequenceNo;
	private String benefitComponent;
	private String benefit;
	private String exclusionInd;
	private String claimType;
	private String plcOfSrvc;
	private int patLowAge;
	private int patHighAge;
	private String genderCode;
	private String providerID;
	private String providerSpcltyCode;
	private String bnftAccumNm;
	private String bnftAccumLmtAmt;
	private int bnftAccumLmtNmr;
	private String notifyOnlyInd;
	private String clnlRvwInd;
	private String dollarLimit;
	private String serviceCode;
	private String groupState;
	private int lenOfStay;
	private String itsSpecltyCode;
	private String mbrRelshpCode;
	private String hcpcsModfr2Code;
	private String hcpcsModfrCode;
	private String providerStateCode;
	private String billTypeCode;
	private String ttCode;
	private String atchmtInd;
	private String patmbrCode;
	private String hospFcilCode;
	private int daysFromInjry;
	private int daysFromIlns;
	private String hmoClassCode;
	private String editCode1;
	private String editCode2;
	private String copayInd;
	private String pct100Ind;
	private String ageTypCode;
	private Date srvcStrtDate;
	private Date srvcEndDate;
	private String totlChrgSignVal;
	private String totlChrgAmt;
	private String medcrAsgnmentInd;
	private String sprtgProcCodeInd;
	private String drugCode;
	private String diagCode;
	private String pcpInd;
	private String providerSpecialityIndicator;
	private String provLncsId;
	private String provMedcrId;
	private String billgProvNbr;
	private String rndrgProvNbr;
	private String billgNpiNbr;
	private String rndrgNpiNbr;
	private String elgblExpansSignCode;
	private String elgblExpansAmt;
	
	private int codeSequenceNo;
	private String hcptLowVal;
	private String hcptHighVal;
	private String revLowVal;
	private String revHighVal;
	private String icdpLowVal;
	private String icdpHighVal;
	private String icdpVrsnInd;
	private String diagLowVal;
	private String diagHighVal;
	private String diagvrsnInd;
	private String srvcClassLow;
	private String srvcClassHigh;
	private String lmtClassLow;
	private String lmtClassHigh;
	
	private int clmSeqNumber;
	private String clmSrvcCode;
	private String clmProcdrModfrCode1;
	private String clmProcdrModfrCode2;
	private String clmttCode;
	private String clmPlaceOfSrvc;
	private String clmHmoClassCode;
	private String clmSameDaySvcInd;
	//private String clmDrugCode;
	
	private String clmDiagInd;
	private String clmSprtgProcCdInd;
	
	private int clmCdSeqNumber;
	private String clmHcptLowVal;
	private String clmHcptHighVal;
	private String clmRevLowVal;
	private String clmRevHighVal;
	private String clmIcdpLowVal;
	private String clmIcdpHighVal;
	private String clmIcdVrsnInd;
	private String clmDiagLowVal;
	private String clmDiagHighVal;
	private String clmDiagVrsnInd;
	private String clmSrvcClassLow;
	private String clmSrvcClassHigh;
	private String clmLmtClassLow;
	private String clmLmtClassHigh;
    private String grpRuleId;
    private String ruleTypeCode;
	private String ruleShortDesc;
	private String ruleGrpInd;
	private Date ruleAprvdDate;
	
	
	
	public String getRuleTypeCode() {
		return ruleTypeCode;
	}
	public void setRuleTypeCode(String ruleTypeCode) {
		this.ruleTypeCode = ruleTypeCode;
	}
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	public Date getRuleAprvdDate() {
		return ruleAprvdDate;
	}
	public void setRuleAprvdDate(Date ruleAprvdDate) {
		this.ruleAprvdDate = ruleAprvdDate;
	}
	public int getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(int keyValue) {
		this.keyValue = keyValue;
	}
	public String getRuleID() {
		return ruleID;
	}
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	public String getRuleTypeDesc() {
		return ruleTypeDesc;
	}
	public void setRuleTypeDesc(String ruleTypeDesc) {
		this.ruleTypeDesc = ruleTypeDesc;
	}
	public String getRuleVersion() {
		return ruleVersion;
	}
	public void setRuleVersion(String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getRuleSequenceNo() {
		return ruleSequenceNo;
	}
	public void setRuleSequenceNo(int ruleSequenceNo) {
		this.ruleSequenceNo = ruleSequenceNo;
	}
	public String getBenefitComponent() {
		return benefitComponent;
	}
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}
	public String getBenefit() {
		return benefit;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getExclusionInd() {
		return exclusionInd;
	}
	public void setExclusionInd(String exclusionInd) {
		this.exclusionInd = exclusionInd;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getPlcOfSrvc() {
		return plcOfSrvc;
	}
	public void setPlcOfSrvc(String plcOfSrvc) {
		this.plcOfSrvc = plcOfSrvc;
	}
	public int getPatLowAge() {
		return patLowAge;
	}
	public void setPatLowAge(int patLowAge) {
		this.patLowAge = patLowAge;
	}
	public int getPatHighAge() {
		return patHighAge;
	}
	public void setPatHighAge(int patHighAge) {
		this.patHighAge = patHighAge;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getProviderID() {
		return providerID;
	}
	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}
	public String getProviderSpcltyCode() {
		return providerSpcltyCode;
	}
	public void setProviderSpcltyCode(String providerSpcltyCode) {
		this.providerSpcltyCode = providerSpcltyCode;
	}
	public String getBnftAccumNm() {
		return bnftAccumNm;
	}
	public void setBnftAccumNm(String bnftAccumNm) {
		this.bnftAccumNm = bnftAccumNm;
	}
	public String getBnftAccumLmtAmt() {
		return bnftAccumLmtAmt;
	}
	public void setBnftAccumLmtAmt(String bnftAccumLmtAmt) {
		this.bnftAccumLmtAmt = bnftAccumLmtAmt;
	}
	public int getBnftAccumLmtNmr() {
		return bnftAccumLmtNmr;
	}
	public void setBnftAccumLmtNmr(int bnftAccumLmtNmr) {
		this.bnftAccumLmtNmr = bnftAccumLmtNmr;
	}
	public String getNotifyOnlyInd() {
		return notifyOnlyInd;
	}
	public void setNotifyOnlyInd(String notifyOnlyInd) {
		this.notifyOnlyInd = notifyOnlyInd;
	}
	public String getClnlRvwInd() {
		return clnlRvwInd;
	}
	public void setClnlRvwInd(String clnlRvwInd) {
		this.clnlRvwInd = clnlRvwInd;
	}
	public String getDollarLimit() {
		return dollarLimit;
	}
	public void setDollarLimit(String dollarLimit) {
		this.dollarLimit = dollarLimit;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getGroupState() {
		return groupState;
	}
	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}
	public int getLenOfStay() {
		return lenOfStay;
	}
	public void setLenOfStay(int lenOfStay) {
		this.lenOfStay = lenOfStay;
	}
	public String getItsSpecltyCode() {
		return itsSpecltyCode;
	}
	public void setItsSpecltyCode(String itsSpecltyCode) {
		this.itsSpecltyCode = itsSpecltyCode;
	}
	public String getMbrRelshpCode() {
		return mbrRelshpCode;
	}
	public void setMbrRelshpCode(String mbrRelshpCode) {
		this.mbrRelshpCode = mbrRelshpCode;
	}
	public String getHcpcsModfr2Code() {
		return hcpcsModfr2Code;
	}
	public void setHcpcsModfr2Code(String hcpcsModfr2Code) {
		this.hcpcsModfr2Code = hcpcsModfr2Code;
	}
	public String getHcpcsModfrCode() {
		return hcpcsModfrCode;
	}
	public void setHcpcsModfrCode(String hcpcsModfrCode) {
		this.hcpcsModfrCode = hcpcsModfrCode;
	}
	public String getProviderStateCode() {
		return providerStateCode;
	}
	public void setProviderStateCode(String providerStateCode) {
		this.providerStateCode = providerStateCode;
	}
	public String getBillTypeCode() {
		return billTypeCode;
	}
	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}
	public String getTtCode() {
		return ttCode;
	}
	public void setTtCode(String ttCode) {
		this.ttCode = ttCode;
	}
	public String getAtchmtInd() {
		return atchmtInd;
	}
	public void setAtchmtInd(String atchmtInd) {
		this.atchmtInd = atchmtInd;
	}
	public String getPatmbrCode() {
		return patmbrCode;
	}
	public void setPatmbrCode(String patmbrCode) {
		this.patmbrCode = patmbrCode;
	}
	public String getHospFcilCode() {
		return hospFcilCode;
	}
	public void setHospFcilCode(String hospFcilCode) {
		this.hospFcilCode = hospFcilCode;
	}
	public int getDaysFromInjry() {
		return daysFromInjry;
	}
	public void setDaysFromInjry(int daysFromInjry) {
		this.daysFromInjry = daysFromInjry;
	}
	public int getDaysFromIlns() {
		return daysFromIlns;
	}
	public void setDaysFromIlns(int daysFromIlns) {
		this.daysFromIlns = daysFromIlns;
	}
	public String getHmoClassCode() {
		return hmoClassCode;
	}
	public void setHmoClassCode(String hmoClassCode) {
		this.hmoClassCode = hmoClassCode;
	}
	public String getEditCode1() {
		return editCode1;
	}
	public void setEditCode1(String editCode1) {
		this.editCode1 = editCode1;
	}
	public String getEditCode2() {
		return editCode2;
	}
	public void setEditCode2(String editCode2) {
		this.editCode2 = editCode2;
	}
	public String getCopayInd() {
		return copayInd;
	}
	public void setCopayInd(String copayInd) {
		this.copayInd = copayInd;
	}
	public String getPct100Ind() {
		return pct100Ind;
	}
	public void setPct100Ind(String pct100Ind) {
		this.pct100Ind = pct100Ind;
	}
	public String getAgeTypCode() {
		return ageTypCode;
	}
	public void setAgeTypCode(String ageTypCode) {
		this.ageTypCode = ageTypCode;
	}
	public Date getSrvcStrtDate() {
		return srvcStrtDate;
	}
	public void setSrvcStrtDate(Date srvcStrtDate) {
		this.srvcStrtDate = srvcStrtDate;
	}
	public Date getSrvcEndDate() {
		return srvcEndDate;
	}
	public void setSrvcEndDate(Date srvcEndDate) {
		this.srvcEndDate = srvcEndDate;
	}
	public String getTotlChrgSignVal() {
		return totlChrgSignVal;
	}
	public void setTotlChrgSignVal(String totlChrgSignVal) {
		this.totlChrgSignVal = totlChrgSignVal;
	}
	public String getTotlChrgAmt() {
		return totlChrgAmt;
	}
	public void setTotlChrgAmt(String totlChrgAmt) {
		this.totlChrgAmt = totlChrgAmt;
	}
	public String getMedcrAsgnmentInd() {
		return medcrAsgnmentInd;
	}
	public void setMedcrAsgnmentInd(String medcrAsgnmentInd) {
		this.medcrAsgnmentInd = medcrAsgnmentInd;
	}
	public String getSprtgProcCodeInd() {
		return sprtgProcCodeInd;
	}
	public void setSprtgProcCodeInd(String sprtgProcCodeInd) {
		this.sprtgProcCodeInd = sprtgProcCodeInd;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getPcpInd() {
		return pcpInd;
	}
	public void setPcpInd(String pcpInd) {
		this.pcpInd = pcpInd;
	}
	/*public String getProvSpcltyInd() {
		return provSpcltyInd;
	}
	public void setProvSpcltyInd(String provSpcltyInd) {
		this.provSpcltyInd = provSpcltyInd;
	}*/
	public String getProvLncsId() {
		return provLncsId;
	}
	public void setProvLncsId(String provLncsId) {
		this.provLncsId = provLncsId;
	}
	public String getProvMedcrId() {
		return provMedcrId;
	}
	public void setProvMedcrId(String provMedcrId) {
		this.provMedcrId = provMedcrId;
	}
	public String getBillgProvNbr() {
		return billgProvNbr;
	}
	public void setBillgProvNbr(String billgProvNbr) {
		this.billgProvNbr = billgProvNbr;
	}
	public String getRndrgProvNbr() {
		return rndrgProvNbr;
	}
	public void setRndrgProvNbr(String rndrgProvNbr) {
		this.rndrgProvNbr = rndrgProvNbr;
	}
	public String getBillgNpiNbr() {
		return billgNpiNbr;
	}
	public void setBillgNpiNbr(String billgNpiNbr) {
		this.billgNpiNbr = billgNpiNbr;
	}
	public String getRndrgNpiNbr() {
		return rndrgNpiNbr;
	}
	public void setRndrgNpiNbr(String rndrgNpiNbr) {
		this.rndrgNpiNbr = rndrgNpiNbr;
	}
	public String getElgblExpansSignCode() {
		return elgblExpansSignCode;
	}
	public void setElgblExpansSignCode(String elgblExpansSignCode) {
		this.elgblExpansSignCode = elgblExpansSignCode;
	}
	public String getElgblExpansAmt() {
		return elgblExpansAmt;
	}
	public void setElgblExpansAmt(String elgblExpansAmt) {
		this.elgblExpansAmt = elgblExpansAmt;
	}
	public int getCodeSequenceNo() {
		return codeSequenceNo;
	}
	public void setCodeSequenceNo(int codeSequenceNo) {
		this.codeSequenceNo = codeSequenceNo;
	}
	public String getHcptLowVal() {
		return hcptLowVal;
	}
	public void setHcptLowVal(String hcptLowVal) {
		this.hcptLowVal = hcptLowVal;
	}
	public String getHcptHighVal() {
		return hcptHighVal;
	}
	public void setHcptHighVal(String hcptHighVal) {
		this.hcptHighVal = hcptHighVal;
	}
	public String getRevLowVal() {
		return revLowVal;
	}
	public void setRevLowVal(String revLowVal) {
		this.revLowVal = revLowVal;
	}
	public String getRevHighVal() {
		return revHighVal;
	}
	public void setRevHighVal(String revHighVal) {
		this.revHighVal = revHighVal;
	}
	public String getIcdpLowVal() {
		return icdpLowVal;
	}
	public void setIcdpLowVal(String icdpLowVal) {
		this.icdpLowVal = icdpLowVal;
	}
	public String getIcdpHighVal() {
		return icdpHighVal;
	}
	public void setIcdpHighVal(String icdpHighVal) {
		this.icdpHighVal = icdpHighVal;
	}
	public String getIcdpVrsnInd() {
		return icdpVrsnInd;
	}
	public void setIcdpVrsnInd(String icdpVrsnInd) {
		this.icdpVrsnInd = icdpVrsnInd;
	}
	public String getDiagLowVal() {
		return diagLowVal;
	}
	public void setDiagLowVal(String diagLowVal) {
		this.diagLowVal = diagLowVal;
	}
	public String getDiagHighVal() {
		return diagHighVal;
	}
	public void setDiagHighVal(String diagHighVal) {
		this.diagHighVal = diagHighVal;
	}
	public String getDiagvrsnInd() {
		return diagvrsnInd;
	}
	public void setDiagvrsnInd(String diagvrsnInd) {
		this.diagvrsnInd = diagvrsnInd;
	}
	public String getSrvcClassLow() {
		return srvcClassLow;
	}
	public void setSrvcClassLow(String srvcClassLow) {
		this.srvcClassLow = srvcClassLow;
	}
	public String getSrvcClassHigh() {
		return srvcClassHigh;
	}
	public void setSrvcClassHigh(String srvcClassHigh) {
		this.srvcClassHigh = srvcClassHigh;
	}
	public String getLmtClassLow() {
		return lmtClassLow;
	}
	public void setLmtClassLow(String lmtClassLow) {
		this.lmtClassLow = lmtClassLow;
	}
	public String getLmtClassHigh() {
		return lmtClassHigh;
	}
	public void setLmtClassHigh(String lmtClassHigh) {
		this.lmtClassHigh = lmtClassHigh;
	}
	public int getClmSeqNumber() {
		return clmSeqNumber;
	}
	public void setClmSeqNumber(int clmSeqNumber) {
		this.clmSeqNumber = clmSeqNumber;
	}
	public String getClmSrvcCode() {
		return clmSrvcCode;
	}
	public void setClmSrvcCode(String clmSrvcCode) {
		this.clmSrvcCode = clmSrvcCode;
	}
	public String getClmProcdrModfrCode1() {
		return clmProcdrModfrCode1;
	}
	public void setClmProcdrModfrCode1(String clmProcdrModfrCode1) {
		this.clmProcdrModfrCode1 = clmProcdrModfrCode1;
	}
	public String getClmProcdrModfrCode2() {
		return clmProcdrModfrCode2;
	}
	public void setClmProcdrModfrCode2(String clmProcdrModfrCode2) {
		this.clmProcdrModfrCode2 = clmProcdrModfrCode2;
	}
	public String getClmttCode() {
		return clmttCode;
	}
	public void setClmttCode(String clmttCode) {
		this.clmttCode = clmttCode;
	}
	public String getClmPlaceOfSrvc() {
		return clmPlaceOfSrvc;
	}
	public void setClmPlaceOfSrvc(String clmPlaceOfSrvc) {
		this.clmPlaceOfSrvc = clmPlaceOfSrvc;
	}
	public String getClmHmoClassCode() {
		return clmHmoClassCode;
	}
	public void setClmHmoClassCode(String clmHmoClassCode) {
		this.clmHmoClassCode = clmHmoClassCode;
	}
	public String getClmSameDaySvcInd() {
		return clmSameDaySvcInd;
	}
	public void setClmSameDaySvcInd(String clmSameDaySvcInd) {
		this.clmSameDaySvcInd = clmSameDaySvcInd;
	}
	/*public String getClmDrugCode() {
		return clmDrugCode;
	}
	public void setClmDrugCode(String clmDrugCode) {
		this.clmDrugCode = clmDrugCode;
	}*/
	
	public String getClmDiagInd() {
		return clmDiagInd;
	}
	public void setClmDiagInd(String clmDiagInd) {
		this.clmDiagInd = clmDiagInd;
	}
	public String getClmSprtgProcCdInd() {
		return clmSprtgProcCdInd;
	}
	public void setClmSprtgProcCdInd(String clmSprtgProcCdInd) {
		this.clmSprtgProcCdInd = clmSprtgProcCdInd;
	}
	public int getClmCdSeqNumber() {
		return clmCdSeqNumber;
	}
	public void setClmCdSeqNumber(int clmCdSeqNumber) {
		this.clmCdSeqNumber = clmCdSeqNumber;
	}
	public String getClmHcptLowVal() {
		return clmHcptLowVal;
	}
	public void setClmHcptLowVal(String clmHcptLowVal) {
		this.clmHcptLowVal = clmHcptLowVal;
	}
	public String getClmHcptHighVal() {
		return clmHcptHighVal;
	}
	public void setClmHcptHighVal(String clmHcptHighVal) {
		this.clmHcptHighVal = clmHcptHighVal;
	}
	public String getClmRevLowVal() {
		return clmRevLowVal;
	}
	public void setClmRevLowVal(String clmRevLowVal) {
		this.clmRevLowVal = clmRevLowVal;
	}
	public String getClmRevHighVal() {
		return clmRevHighVal;
	}
	public void setClmRevHighVal(String clmRevHighVal) {
		this.clmRevHighVal = clmRevHighVal;
	}
	public String getClmIcdpLowVal() {
		return clmIcdpLowVal;
	}
	public void setClmIcdpLowVal(String clmIcdpLowVal) {
		this.clmIcdpLowVal = clmIcdpLowVal;
	}
	public String getClmIcdpHighVal() {
		return clmIcdpHighVal;
	}
	public void setClmIcdpHighVal(String clmIcdpHighVal) {
		this.clmIcdpHighVal = clmIcdpHighVal;
	}
	public String getClmIcdVrsnInd() {
		return clmIcdVrsnInd;
	}
	public void setClmIcdVrsnInd(String clmIcdVrsnInd) {
		this.clmIcdVrsnInd = clmIcdVrsnInd;
	}
	public String getClmDiagLowVal() {
		return clmDiagLowVal;
	}
	public void setClmDiagLowVal(String clmDiagLowVal) {
		this.clmDiagLowVal = clmDiagLowVal;
	}
	public String getClmDiagHighVal() {
		return clmDiagHighVal;
	}
	public void setClmDiagHighVal(String clmDiagHighVal) {
		this.clmDiagHighVal = clmDiagHighVal;
	}
	public String getClmDiagVrsnInd() {
		return clmDiagVrsnInd;
	}
	public void setClmDiagVrsnInd(String clmDiagVrsnInd) {
		this.clmDiagVrsnInd = clmDiagVrsnInd;
	}
	public String getClmSrvcClassLow() {
		return clmSrvcClassLow;
	}
	public void setClmSrvcClassLow(String clmSrvcClassLow) {
		this.clmSrvcClassLow = clmSrvcClassLow;
	}
	public String getClmSrvcClassHigh() {
		return clmSrvcClassHigh;
	}
	public void setClmSrvcClassHigh(String clmSrvcClassHigh) {
		this.clmSrvcClassHigh = clmSrvcClassHigh;
	}
	public String getClmLmtClassLow() {
		return clmLmtClassLow;
	}
	public void setClmLmtClassLow(String clmLmtClassLow) {
		this.clmLmtClassLow = clmLmtClassLow;
	}
	public String getClmLmtClassHigh() {
		return clmLmtClassHigh;
	}
	public void setClmLmtClassHigh(String clmLmtClassHigh) {
		this.clmLmtClassHigh = clmLmtClassHigh;
	}
	public String getGrpRuleId() {
		return grpRuleId;
	}
	public void setGrpRuleId(String grpRuleId) {
		this.grpRuleId = grpRuleId;
	}
	/*public String getRuleTypeCode() {
		return ruleTypeCode;
	}
	public void setRuleTypeCode(String ruleTypeCode) {
		this.ruleTypeCode = ruleTypeCode;
	}
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}*/
	public String getRuleGrpInd() {
		return ruleGrpInd;
	}
	public void setRuleGrpInd(String ruleGrpInd) {
		this.ruleGrpInd = ruleGrpInd;
	}
	/*public Date getRuleAprvdDate() {
		return ruleAprvdDate;
	}
	public void setRuleAprvdDate(Date ruleAprvdDate) {
		this.ruleAprvdDate = ruleAprvdDate;
	}*/
	public String getProviderSpecialityIndicator() {
		return providerSpecialityIndicator;
	}
	public void setProviderSpecialityIndicator(
			String providerSpecialityIndicator) {
		this.providerSpecialityIndicator = providerSpecialityIndicator;
	}
	
	
	
	
}
