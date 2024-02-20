/*
 * RuleSequenceBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleSequenceBO extends BusinessObject {
	
	private String ruleId;
	private int ruleSequenceNumber;
	private String ruleTypeCode;
	private String ruleGrpInd;
	private Date ruleAprvdDate;
	private String benefitComponent;
	private String benefit;
	private String exclsnInd;
	private String clmType;
	private String placeOfService;
	private int patLowAge;
	private int patHighAge;
	private String genderCode;
	private String providerId;
	private String providerSpecialityCode;
	private String benefitAccmNum;
	private String benfitAccumLimtAmnt;
	private int benefitAccumLimtNum;
	private String ntfyOnlyInd;
	private String clnlRevwInd;
	private String dlrLimit;
	private String serviceCode;
	private String grpSt;
	private int lenOfStay;
	private String itsSpecialityCode;
	private Date servcStartDate;
	private Date servcEndDate;
	private String membrRelnCode;
	private String precedrModifierCode;
	private String editCode1;
	private String editCode2;
	private String providerStCode;
	private String billTypeCode;
	private String ttCode;
	private String attachmentInd;
	private String patMembrCode;
	private String hosptlFacilCode;   
	private int daysFrmInjury;
	private int daysFrmIllness;
	private String hmoClsCode;
	private String totalChargeSign;
	private String totalChargeAmnt;
	private String wpdDelFlag;
	private String clmLevelDataInd;
	private String copayIndicator;
	private String hunPerIndicator; 
	private String ageTypCode;
	private String medicareAssgnIndicator; 
	private String clmDrgCd;
	private String procedureModifierSecondaryCode;
	private String supportHcpIndicator;
	private String claimSupportHcpIndicator;
	private String diagnosisIndicator;
	private String pcpIndicator;
	private String provLncsId;
	private String provMedcrId;
	private String billgProvNbr;
	private String rndrgProvNbr;
	private String billgNpiNbr;
	private String rndrgNpiNbr;
	private String elgblExpansSignCode;
	private String elgblExpansAmt;
	private String drugCode; 
	private String providerSpecialityIndicator; 
	
	private Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap;
	private Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap;
	
	
	
	
	
	public String getRuleTypeCode() {
		return ruleTypeCode;
	}

	public void setRuleTypeCode(String ruleTypeCode) {
		this.ruleTypeCode = ruleTypeCode;
	}

	public String getRuleGrpInd() {
		return ruleGrpInd;
	}

	public void setRuleGrpInd(String ruleGrpInd) {
		this.ruleGrpInd = ruleGrpInd;
	}

	public Date getRuleAprvdDate() {
		return ruleAprvdDate;
	}

	public void setRuleAprvdDate(Date ruleAprvdDate) {
		this.ruleAprvdDate = ruleAprvdDate;
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
	
	public String getAgeTypCode() {
		return ageTypCode;
	}

	public void setAgeTypCode(String ageTypCode) {
		this.ageTypCode = ageTypCode;
	}
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public int getRuleSequenceNumber() {
		return ruleSequenceNumber;
	}

	public void setRuleSequenceNumber(int ruleSequenceNumber) {
		this.ruleSequenceNumber = ruleSequenceNumber;
	}

	public String getExclsnInd() {
		return exclsnInd;
	}

	public void setExclsnInd(String exclsnInd) {
		this.exclsnInd = exclsnInd;
	}

	public String getClmType() {
		return clmType;
	}

	public void setClmType(String clmType) {
		this.clmType = clmType;
	}

	public String getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}


	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
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

	
	
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderSpecialityCode() {
		return providerSpecialityCode;
	}

	public void setProviderSpecialityCode(String providerSpecialityCode) {
		this.providerSpecialityCode = providerSpecialityCode;
	}

	public String getBenefitAccmNum() {
		return benefitAccmNum;
	}

	public void setBenefitAccmNum(String benefitAccmNum) {
		this.benefitAccmNum = benefitAccmNum;
	}

	

	public String getBenfitAccumLimtAmnt() {
		return benfitAccumLimtAmnt;
	}

	public void setBenfitAccumLimtAmnt(String benfitAccumLimtAmnt) {
		this.benfitAccumLimtAmnt = benfitAccumLimtAmnt;
	}

	public int getBenefitAccumLimtNum() {
		return benefitAccumLimtNum;
	}

	public void setBenefitAccumLimtNum(int benefitAccumLimtNum) {
		this.benefitAccumLimtNum = benefitAccumLimtNum;
	}

	public String getNtfyOnlyInd() {
		return ntfyOnlyInd;
	}

	public void setNtfyOnlyInd(String ntfyOnlyInd) {
		this.ntfyOnlyInd = ntfyOnlyInd;
	}

	public String getClnlRevwInd() {
		return clnlRevwInd;
	}

	public void setClnlRevwInd(String clnlRevwInd) {
		this.clnlRevwInd = clnlRevwInd;
	}

	

	public String getDlrLimit() {
		return dlrLimit;
	}

	public void setDlrLimit(String dlrLimit) {
		this.dlrLimit = dlrLimit;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getGrpSt() {
		return grpSt;
	}

	public void setGrpSt(String grpSt) {
		this.grpSt = grpSt;
	}



	public int getLenOfStay() {
		return lenOfStay;
	}

	public void setLenOfStay(int lenOfStay) {
		this.lenOfStay = lenOfStay;
	}

	public String getItsSpecialityCode() {
		return itsSpecialityCode;
	}

	public void setItsSpecialityCode(String itsSpecialityCode) {
		this.itsSpecialityCode = itsSpecialityCode;
	}

	public Date getServcStartDate() {
		return servcStartDate;
	}

	public void setServcStartDate(Date servcStartDate) {
		this.servcStartDate = servcStartDate;
	}

	public Date getServcEndDate() {
		return servcEndDate;
	}

	public void setServcEndDate(Date servcEndDate) {
		this.servcEndDate = servcEndDate;
	}

	public String getMembrRelnCode() {
		return membrRelnCode;
	}

	public void setMembrRelnCode(String membrRelnCode) {
		this.membrRelnCode = membrRelnCode;
	}

	public String getPrecedrModifierCode() {
		return precedrModifierCode;
	}

	public void setPrecedrModifierCode(String precedrModifierCode) {
		this.precedrModifierCode = precedrModifierCode;
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

	public String getProviderStCode() {
		return providerStCode;
	}

	public void setProviderStCode(String providerStCode) {
		this.providerStCode = providerStCode;
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

	public String getAttachmentInd() {
		return attachmentInd;
	}

	public void setAttachmentInd(String attachmentInd) {
		this.attachmentInd = attachmentInd;
	}

	public String getPatMembrCode() {
		return patMembrCode;
	}

	public void setPatMembrCode(String patMembrCode) {
		this.patMembrCode = patMembrCode;
	}

	public String getHosptlFacilCode() {
		return hosptlFacilCode;
	}

	public void setHosptlFacilCode(String hosptlFacilCode) {
		this.hosptlFacilCode = hosptlFacilCode;
	}

	

	public int getDaysFrmInjury() {
		return daysFrmInjury;
	}

	public void setDaysFrmInjury(int daysFrmInjury) {
		this.daysFrmInjury = daysFrmInjury;
	}

	public int getDaysFrmIllness() {
		return daysFrmIllness;
	}

	public void setDaysFrmIllness(int daysFrmIllness) {
		this.daysFrmIllness = daysFrmIllness;
	}

	public String getHmoClsCode() {
		return hmoClsCode;
	}

	public void setHmoClsCode(String hmoClsCode) {
		this.hmoClsCode = hmoClsCode;
	}

	public String getTotalChargeSign() {
		return totalChargeSign;
	}

	public void setTotalChargeSign(String totalChargeSign) {
		this.totalChargeSign = totalChargeSign;
	}

	public String getTotalChargeAmnt() {
		return totalChargeAmnt;
	}

	public void setTotalChargeAmnt(String totalChargeAmnt) {
		this.totalChargeAmnt = totalChargeAmnt;
	}

	public String getWpdDelFlag() {
		return wpdDelFlag;
	}

	public void setWpdDelFlag(String wpdDelFlag) {
		this.wpdDelFlag = wpdDelFlag;
	}

	public String getClmLevelDataInd() {
		return clmLevelDataInd;
	}

	public void setClmLevelDataInd(String clmLevelDataInd) {
		this.clmLevelDataInd = clmLevelDataInd;
	}

	public String getCopayIndicator() {
		return copayIndicator;
	}

	public void setCopayIndicator(String copayIndicator) {
		this.copayIndicator = copayIndicator;
	}

	public String getHunPerIndicator() {
		return hunPerIndicator;
	}

	public void setHunPerIndicator(String hunPerIndicator) {
		this.hunPerIndicator = hunPerIndicator;
	}
	
	
	public String getMedicareAssgnIndicator() {
		return medicareAssgnIndicator;
	}

	public void setMedicareAssgnIndicator(String medicareAssgnIndicator) {
		this.medicareAssgnIndicator = medicareAssgnIndicator;
	}

	public String getClmDrgCd() {
		return clmDrgCd;
	}

	public void setClmDrgCd(String clmDrgCd) {
		this.clmDrgCd = clmDrgCd;
	}

	public String getProcedureModifierSecondaryCode() {
		return procedureModifierSecondaryCode;
	}

	public void setProcedureModifierSecondaryCode(
			String procedureModifierSecondaryCode) {
		this.procedureModifierSecondaryCode = procedureModifierSecondaryCode;
	}

	public String getSupportHcpIndicator() {
		return supportHcpIndicator;
	}

	public void setSupportHcpIndicator(String supportHcpIndicator) {
		this.supportHcpIndicator = supportHcpIndicator;
	}

	public String getClaimSupportHcpIndicator() {
		return claimSupportHcpIndicator;
	}

	public void setClaimSupportHcpIndicator(String claimSupportHcpIndicator) {
		this.claimSupportHcpIndicator = claimSupportHcpIndicator;
	}

	public String getDiagnosisIndicator() {
		return diagnosisIndicator;
	}

	public void setDiagnosisIndicator(String diagnosisIndicator) {
		this.diagnosisIndicator = diagnosisIndicator;
	}

	public String getPcpIndicator() {
		return pcpIndicator;
	}

	public void setPcpIndicator(String pcpIndicator) {
		this.pcpIndicator = pcpIndicator;
	}

	public Map<Integer, RuleCodeSequenceBO> getRuleCodeSequenceMap() {
		return ruleCodeSequenceMap;
	}

	public void setRuleCodeSequenceMap(
			Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap) {
		this.ruleCodeSequenceMap = ruleCodeSequenceMap;
	}

	public Map<Integer, RuleClaimSequenceBO> getRuleClaimSequenceMap() {
		return ruleClaimSequenceMap;
	}

	public void setRuleClaimSequenceMap(
			Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap) {
		this.ruleClaimSequenceMap = ruleClaimSequenceMap;
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RuleSequenceBO(){
		this.ruleCodeSequenceMap = new HashMap<Integer, RuleCodeSequenceBO>();
		this.ruleClaimSequenceMap = new HashMap<Integer, RuleClaimSequenceBO>();
	}

	public String getDrugCode() {
		return drugCode;
	}

	public String getProviderSpecialityIndicator() {
		return providerSpecialityIndicator;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public void setProviderSpecialityIndicator(String providerSpecialityIndicator) {
		this.providerSpecialityIndicator = providerSpecialityIndicator;
	}
}
