/*
 * Created on Apr 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.locateresult;

import java.util.Date;
import java.util.List;

/**
 * @author u13531
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleSequenceResults {

	private int rowCount;
	private int ruleSequenceNumber;
	private String ruleId;
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
	private String ruleTypCd; 
	private String tag; 
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
	
	private int cdSqncNbr;
	private String lineHcptLowVal;
	private String lineHcptHighVal;
	private String lineDiagLowVal;
	private String lineDiagHighVal;
	private String lineRevLowVal;
    private String lineRevHighVal;	
 	private String lineIcdpLowVal;	
 	private String lineIcdpHighVal;
 	private String lineSrvcClsHigh;
 	private String lineSrvcClsLow;
 	private String lineLmtClsHigh;
 	private String lineLmtClsLow; 
 	private String lineIcdpVrsnInd;
 	private String lineDiagVrsnInd;
 	private String lineIcdpCategoryCode;
 	private String lineIcdpClsfctnId;
 	private String lineDiagCategoryCode;
 	private String lineDiagClsfctnId;
 	
 	private int clmSqncNbr;  
 	private String clmServiceCode; 
 	private String clmProcessModifierCode; 
 	private String clmProcessModifierSecondaryCode; 
 	private String clmttCd; 
 	private String clmPosCd; 
 	private String clmHMOClassCode;
 	private String clmSameDaySrvcInd;           	 		
	private String clmSprtgProcCdInd;
	private String claimDiagnosisIndicator;    
	
	private int clmCdSqncNbr;    
	private String clmHcptLowVal;
	private String clmHcptHighVal;
	private String clmDiagLowVal;
	private String clmDiagHighVal;
	private String clmRevLowVal;
	private String clmRevHighVal;
		
	private String clmIcdLowVal;
	private String clmIcdHighVal;
	private String clmServiceClassHigh;
	private String clmServiceClassLow;
	private String clmLimitClassHigh;
	private String clmLimitClassLow;
	private String clmIcdpVrsnInd;
	private String clmDiagVrsnInd;
	private String clmIcdpCategoryCode;
 	private String clmIcdpClsfctnId;
 	private String clmDiagCategoryCode;
 	private String clmDiagClsfctnId;
 	
	private String ruleVersion;
	
	private String ruleSearchWrd1;
	private String ruleSearchWrd2;
	private String ruleSearchWrd3;
	private String ruleShortDesc;
	private List codeList;
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getRuleSequenceNumber() {
		return ruleSequenceNumber;
	}
	public void setRuleSequenceNumber(int ruleSequenceNumber) {
		this.ruleSequenceNumber = ruleSequenceNumber;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
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
	public void setProcedureModifierSecondaryCode(String procedureModifierSecondaryCode) {
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
	public String getRuleTypCd() {
		return ruleTypCd;
	}
	public void setRuleTypCd(String ruleTypCd) {
		this.ruleTypCd = ruleTypCd;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCdSqncNbr() {
		return cdSqncNbr;
	}
	public void setCdSqncNbr(int cdSqncNbr) {
		this.cdSqncNbr = cdSqncNbr;
	}
	public String getLineHcptLowVal() {
		return lineHcptLowVal;
	}
	public void setLineHcptLowVal(String lineHcptLowVal) {
		this.lineHcptLowVal = lineHcptLowVal;
	}
	public String getLineHcptHighVal() {
		return lineHcptHighVal;
	}
	public void setLineHcptHighVal(String lineHcptHighVal) {
		this.lineHcptHighVal = lineHcptHighVal;
	}
	public String getLineDiagLowVal() {
		return lineDiagLowVal;
	}
	public void setLineDiagLowVal(String lineDiagLowVal) {
		this.lineDiagLowVal = lineDiagLowVal;
	}
	public String getLineDiagHighVal() {
		return lineDiagHighVal;
	}
	public void setLineDiagHighVal(String lineDiagHighVal) {
		this.lineDiagHighVal = lineDiagHighVal;
	}
	public String getLineRevLowVal() {
		return lineRevLowVal;
	}
	public void setLineRevLowVal(String lineRevLowVal) {
		this.lineRevLowVal = lineRevLowVal;
	}
	public String getLineRevHighVal() {
		return lineRevHighVal;
	}
	public void setLineRevHighVal(String lineRevHighVal) {
		this.lineRevHighVal = lineRevHighVal;
	}
	public String getLineIcdpLowVal() {
		return lineIcdpLowVal;
	}
	public void setLineIcdpLowVal(String lineIcdpLowVal) {
		this.lineIcdpLowVal = lineIcdpLowVal;
	}
	public String getLineIcdpHighVal() {
		return lineIcdpHighVal;
	}
	public void setLineIcdpHighVal(String lineIcdpHighVal) {
		this.lineIcdpHighVal = lineIcdpHighVal;
	}
	public String getLineSrvcClsHigh() {
		return lineSrvcClsHigh;
	}
	public void setLineSrvcClsHigh(String lineSrvcClsHigh) {
		this.lineSrvcClsHigh = lineSrvcClsHigh;
	}
	public String getLineSrvcClsLow() {
		return lineSrvcClsLow;
	}
	public void setLineSrvcClsLow(String lineSrvcClsLow) {
		this.lineSrvcClsLow = lineSrvcClsLow;
	}
	public String getLineLmtClsHigh() {
		return lineLmtClsHigh;
	}
	public void setLineLmtClsHigh(String lineLmtClsHigh) {
		this.lineLmtClsHigh = lineLmtClsHigh;
	}
	public String getLineLmtClsLow() {
		return lineLmtClsLow;
	}
	public void setLineLmtClsLow(String lineLmtClsLow) {
		this.lineLmtClsLow = lineLmtClsLow;
	}
	public String getLineIcdpVrsnInd() {
		return lineIcdpVrsnInd;
	}
	public void setLineIcdpVrsnInd(String lineIcdpVrsnInd) {
		this.lineIcdpVrsnInd = lineIcdpVrsnInd;
	}
	public String getLineDiagVrsnInd() {
		return lineDiagVrsnInd;
	}
	public void setLineDiagVrsnInd(String lineDiagVrsnInd) {
		this.lineDiagVrsnInd = lineDiagVrsnInd;
	}
	public int getClmSqncNbr() {
		return clmSqncNbr;
	}
	public void setClmSqncNbr(int clmSqncNbr) {
		this.clmSqncNbr = clmSqncNbr;
	}
	public String getClmServiceCode() {
		return clmServiceCode;
	}
	public void setClmServiceCode(String clmServiceCode) {
		this.clmServiceCode = clmServiceCode;
	}
	
	public String getClmProcessModifierCode() {
		return clmProcessModifierCode;
	}
	public void setClmProcessModifierCode(String clmProcessModifierCode) {
		this.clmProcessModifierCode = clmProcessModifierCode;
	}
	public String getClmProcessModifierSecondaryCode() {
		return clmProcessModifierSecondaryCode;
	}
	public void setClmProcessModifierSecondaryCode(
			String clmProcessModifierSecondaryCode) {
		this.clmProcessModifierSecondaryCode = clmProcessModifierSecondaryCode;
	}
	public String getClmttCd() {
		return clmttCd;
	}
	public void setClmttCd(String clmttCd) {
		this.clmttCd = clmttCd;
	}
	public String getClmPosCd() {
		return clmPosCd;
	}
	public void setClmPosCd(String clmPosCd) {
		this.clmPosCd = clmPosCd;
	}
	public String getClmHMOClassCode() {
		return clmHMOClassCode;
	}
	public void setClmHMOClassCode(String clmHMOClassCode) {
		this.clmHMOClassCode = clmHMOClassCode;
	}
	public String getClmSameDaySrvcInd() {
		return clmSameDaySrvcInd;
	}
	public void setClmSameDaySrvcInd(String clmSameDaySrvcInd) {
		this.clmSameDaySrvcInd = clmSameDaySrvcInd;
	}
	public String getClmSprtgProcCdInd() {
		return clmSprtgProcCdInd;
	}
	public void setClmSprtgProcCdInd(String clmSprtgProcCdInd) {
		this.clmSprtgProcCdInd = clmSprtgProcCdInd;
	}
	public String getClaimDiagnosisIndicator() {
		return claimDiagnosisIndicator;
	}
	public void setClaimDiagnosisIndicator(String claimDiagnosisIndicator) {
		this.claimDiagnosisIndicator = claimDiagnosisIndicator;
	}
	public int getClmCdSqncNbr() {
		return clmCdSqncNbr;
	}
	public void setClmCdSqncNbr(int clmCdSqncNbr) {
		this.clmCdSqncNbr = clmCdSqncNbr;
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
	public String getClmIcdLowVal() {
		return clmIcdLowVal;
	}
	public void setClmIcdLowVal(String clmIcpLowVal) {
		this.clmIcdLowVal = clmIcpLowVal;
	}
	public String getClmIcdHighVal() {
		return clmIcdHighVal;
	}
	public void setClmIcdHighVal(String clmIcdHighVal) {
		this.clmIcdHighVal = clmIcdHighVal;
	}
	public String getClmServiceClassHigh() {
		return clmServiceClassHigh;
	}
	public void setClmServiceClassHigh(String clmServiceClassHigh) {
		this.clmServiceClassHigh = clmServiceClassHigh;
	}
	public String getClmServiceClassLow() {
		return clmServiceClassLow;
	}
	public void setClmServiceClassLow(String clmServiceClassLow) {
		this.clmServiceClassLow = clmServiceClassLow;
	}
	public String getClmLimitClassHigh() {
		return clmLimitClassHigh;
	}
	public void setClmLimitClassHigh(String clmLimitClassHigh) {
		this.clmLimitClassHigh = clmLimitClassHigh;
	}
	public String getClmLimitClassLow() {
		return clmLimitClassLow;
	}
	public void setClmLimitClassLow(String clmLimitClassLow) {
		this.clmLimitClassLow = clmLimitClassLow;
	}
	public String getClmIcdpVrsnInd() {
		return clmIcdpVrsnInd;
	}
	public void setClmIcdpVrsnInd(String clmIcdpVrsnInd) {
		this.clmIcdpVrsnInd = clmIcdpVrsnInd;
	}
	public String getRuleSearchWrd1() {
		return ruleSearchWrd1;
	}
	public void setRuleSearchWrd1(String ruleSearchWrd1) {
		this.ruleSearchWrd1 = ruleSearchWrd1;
	}
	public String getRuleSearchWrd2() {
		return ruleSearchWrd2;
	}
	public void setRuleSearchWrd2(String ruleSearchWrd2) {
		this.ruleSearchWrd2 = ruleSearchWrd2;
	}
	public String getRuleSearchWrd3() {
		return ruleSearchWrd3;
	}
	public void setRuleSearchWrd3(String ruleSearchWrd3) {
		this.ruleSearchWrd3 = ruleSearchWrd3;
	}
	public String getRuleShortDesc() {
		return ruleShortDesc;
	}
	public void setRuleShortDesc(String ruleShortDesc) {
		this.ruleShortDesc = ruleShortDesc;
	}
	public List getCodeList() {
		return codeList;
	}
	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}
	public String getClmDiagVrsnInd() {
		return clmDiagVrsnInd;
	}
	public void setClmDiagVrsnInd(String clmDiagVrsnInd) {
		this.clmDiagVrsnInd = clmDiagVrsnInd;
	}
	public String getRuleVersion() {
		return ruleVersion;
	}
	public void setRuleVersion(String ruleVersion) {
		this.ruleVersion = ruleVersion;
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
	public String getLineIcdpCategoryCode() {
		return lineIcdpCategoryCode;
	}
	public String getLineIcdpClsfctnId() {
		return lineIcdpClsfctnId;
	}
	public String getLineDiagCategoryCode() {
		return lineDiagCategoryCode;
	}
	public String getLineDiagClsfctnId() {
		return lineDiagClsfctnId;
	}
	public void setLineIcdpCategoryCode(String lineIcdpCategoryCode) {
		this.lineIcdpCategoryCode = lineIcdpCategoryCode;
	}
	public void setLineIcdpClsfctnId(String lineIcdpClsfctnId) {
		this.lineIcdpClsfctnId = lineIcdpClsfctnId;
	}
	public void setLineDiagCategoryCode(String lineDiagCategoryCode) {
		this.lineDiagCategoryCode = lineDiagCategoryCode;
	}
	public void setLineDiagClsfctnId(String lineDiagClsfctnId) {
		this.lineDiagClsfctnId = lineDiagClsfctnId;
	}
	public String getClmIcdpCategoryCode() {
		return clmIcdpCategoryCode;
	}
	public String getClmIcdpClsfctnId() {
		return clmIcdpClsfctnId;
	}
	public String getClmDiagCategoryCode() {
		return clmDiagCategoryCode;
	}
	public String getClmDiagClsfctnId() {
		return clmDiagClsfctnId;
	}
	public void setClmIcdpCategoryCode(String clmIcdpCategoryCode) {
		this.clmIcdpCategoryCode = clmIcdpCategoryCode;
	}
	public void setClmIcdpClsfctnId(String clmIcdpClsfctnId) {
		this.clmIcdpClsfctnId = clmIcdpClsfctnId;
	}
	public void setClmDiagCategoryCode(String clmDiagCategoryCode) {
		this.clmDiagCategoryCode = clmDiagCategoryCode;
	}
	public void setClmDiagClsfctnId(String clmDiagClsfctnId) {
		this.clmDiagClsfctnId = clmDiagClsfctnId;
	}
	public String getProvLncsId() {
		return provLncsId;
	}
	public String getProvMedcrId() {
		return provMedcrId;
	}
	public String getBillgProvNbr() {
		return billgProvNbr;
	}
	public String getRndrgProvNbr() {
		return rndrgProvNbr;
	}
	public String getBillgNpiNbr() {
		return billgNpiNbr;
	}
	public String getRndrgNpiNbr() {
		return rndrgNpiNbr;
	}
	public String getElgblExpansSignCode() {
		return elgblExpansSignCode;
	}
	public String getElgblExpansAmt() {
		return elgblExpansAmt;
	}
	public void setProvLncsId(String provLncsId) {
		this.provLncsId = provLncsId;
	}
	public void setProvMedcrId(String provMedcrId) {
		this.provMedcrId = provMedcrId;
	}
	public void setBillgProvNbr(String billgProvNbr) {
		this.billgProvNbr = billgProvNbr;
	}
	public void setRndrgProvNbr(String rndrgProvNbr) {
		this.rndrgProvNbr = rndrgProvNbr;
	}
	public void setBillgNpiNbr(String billgNpiNbr) {
		this.billgNpiNbr = billgNpiNbr;
	}
	public void setRndrgNpiNbr(String rndrgNpiNbr) {
		this.rndrgNpiNbr = rndrgNpiNbr;
	}
	public void setElgblExpansSignCode(String elgblExpansSignCode) {
		this.elgblExpansSignCode = elgblExpansSignCode;
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
	
	
}
