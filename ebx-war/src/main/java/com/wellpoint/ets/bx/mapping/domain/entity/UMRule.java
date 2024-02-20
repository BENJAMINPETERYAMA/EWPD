package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;

/**
 * The class will hold all the indicators of a UM rule along with the rule descriptions and search words.
 *
 */
public class UMRule {
	
	private HippaSegment hippaSegment;

	private String searchWord1;
	private String searchWord2;
	private String searchWord3;
	
	private String ruleGrpInd;
	
	private String ruleSqncNbr;
	private String exclsnInd;
	private String clmType;
	private String plcOfSrvc;
	private String patLowAge;
	private String patHighAge;
	private String gndrCode;
	private String prvdrId;
	private String prvdrSpcltyCd;
	private String bnftAccumNm;
	private String bnftAccumLmtAmt;
	private String bnftAccumLmtNbr;
	private String ntfyOnlyInd;
	private String clnlRvwInd;
	private String dlrLmt;
	private String serivceCd;
	private String grpSt;
	private String lenOfStay;
	private String itsSpcltyCd;
	private Date srvcStrtDt;
	private Date srvcEndDt;
	private String mbrRelshpCde;
	private String prcdrModfrCde;
	private String editCde1;
	private String editCde2;
	private String prvdrStCd;
	private String billTypCd;
	private String ttCd;
	private String atchmtInd;
	private String patMbrCd;
	private String hospFcilCd;
	private String daysFromInjry;
	private String daysFromIlns;
	private String hmoClsCd;
	private String totChrgSign;
	private String totChargeAmount;
	private String wpdDelFlag;
	private String clmSrvcCd;
	private String cpayInd;
	private String hndrdPctInd;
	private String medcrAsgnInd;
	private String procMdfrCd2;
	private String sprtHcpsInd;
	private String diagInd;
	private String pcpInd;
	private String pvdrLicId;
	private String pvdrMdcrId;
	private String blngPvdrNr;
	private String rndrPvdrNr;
	private String blngNpi;
	private String rndrNpi;
	private String elgblExpnsSignCd;
	private String elgblExpnsAmt;
	private String ageTypeCd;
	private String drgCd;
	private String provSpecCdInd;
	
	
	//not used
	private String clmProcMdfrCd;
	private String clmTtCd;
	private String clmPlaceOfSrvc;
	private String clmHmoClsCd;
	private String clmSameDaySvcInd;
	private String clmDiagInd;
	private String clmProcMdfrCd2;
	private String clmSprtHcpsInd;
	private String codeType;
	private String codeTypeValue;
	private String clmIcdVrsnIndicator;  //ICD10 Enhancement
	//not used
	
	
	private int ruleVerNmbr;
	private String tag;
	private String longDescription;
	
	public int getRuleVerNmbr() {
		return ruleVerNmbr;
	}

	public void setRuleVerNmbr(int ruleVerNmbr) {
		this.ruleVerNmbr = ruleVerNmbr;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	//Getters and Setters
	public HippaSegment getHippaSegment() {
		return hippaSegment;
	}

	public void setHippaSegment(HippaSegment hippaSegment) {
		this.hippaSegment = hippaSegment;
	}

	public String getSearchWord1() {
		return searchWord1;
	}

	public void setSearchWord1(String searchWord1) {
		this.searchWord1 = searchWord1;
	}

	public String getSearchWord2() {
		return searchWord2;
	}

	public void setSearchWord2(String searchWord2) {
		this.searchWord2 = searchWord2;
	}

	public String getSearchWord3() {
		return searchWord3;
	}

	public void setSearchWord3(String searchWord3) {
		this.searchWord3 = searchWord3;
	}
	public String getRuleGrpInd() {
		return ruleGrpInd;
	}
	public void setRuleGrpInd(String ruleGrpInd) {
		this.ruleGrpInd = ruleGrpInd;
	}
	public String getRuleSqncNbr() {
		return ruleSqncNbr;
	}
	public void setRuleSqncNbr(String ruleSqncNbr) {
		this.ruleSqncNbr = ruleSqncNbr;
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
	public String getPatLowAge() {
		return patLowAge;
	}
	public void setPatLowAge(String patLowAge) {
		this.patLowAge = patLowAge;
	}
	public String getPatHighAge() {
		return patHighAge;
	}
	public void setPatHighAge(String patHighAge) {
		this.patHighAge = patHighAge;
	}
	public String getPlcOfSrvc() {
		return plcOfSrvc;
	}
	public void setPlcOfSrvc(String plcOfSrvc) {
		this.plcOfSrvc = plcOfSrvc;
	}
	public String getGndrCode() {
		return gndrCode;
	}
	public void setGndrCode(String gndrCode) {
		this.gndrCode = gndrCode;
	}
	public String getPrvdrId() {
		return prvdrId;
	}
	public void setPrvdrId(String prvdrId) {
		this.prvdrId = prvdrId;
	}
	public String getPrvdrSpcltyCd() {
		return prvdrSpcltyCd;
	}
	public void setPrvdrSpcltyCd(String prvdrSpcltyCd) {
		this.prvdrSpcltyCd = prvdrSpcltyCd;
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
	public String getBnftAccumLmtNbr() {
		return bnftAccumLmtNbr;
	}
	public void setBnftAccumLmtNbr(String bnftAccumLmtNbr) {
		this.bnftAccumLmtNbr = bnftAccumLmtNbr;
	}
	public String getNtfyOnlyInd() {
		return ntfyOnlyInd;
	}
	public void setNtfyOnlyInd(String ntfyOnlyInd) {
		this.ntfyOnlyInd = ntfyOnlyInd;
	}
	public String getClnlRvwInd() {
		return clnlRvwInd;
	}
	public void setClnlRvwInd(String clnlRvwInd) {
		this.clnlRvwInd = clnlRvwInd;
	}
	public String getDlrLmt() {
		return dlrLmt;
	}
	public void setDlrLmt(String dlrLmt) {
		this.dlrLmt = dlrLmt;
	}
	public String getSerivceCd() {
		return serivceCd;
	}
	public void setSerivceCd(String serivceCd) {
		this.serivceCd = serivceCd;
	}
	public String getGrpSt() {
		return grpSt;
	}
	public void setGrpSt(String grpSt) {
		this.grpSt = grpSt;
	}
	public String getLenOfStay() {
		return lenOfStay;
	}
	public void setLenOfStay(String lenOfStay) {
		this.lenOfStay = lenOfStay;
	}
	public String getItsSpcltyCd() {
		return itsSpcltyCd;
	}
	public void setItsSpcltyCd(String itsSpcltyCd) {
		this.itsSpcltyCd = itsSpcltyCd;
	}
	
	/**
	 * @return Returns the srvcEndDt.
	 */
	public Date getSrvcEndDt() {
		return srvcEndDt;
	}
	/**
	 * @param srvcEndDt The srvcEndDt to set.
	 */
	public void setSrvcEndDt(Date srvcEndDt) {
		this.srvcEndDt = srvcEndDt;
	}
	/**
	 * @return Returns the srvcStrtDt.
	 */
	public Date getSrvcStrtDt() {
		return srvcStrtDt;
	}
	/**
	 * @param srvcStrtDt The srvcStrtDt to set.
	 */
	public void setSrvcStrtDt(Date srvcStrtDt) {
		this.srvcStrtDt = srvcStrtDt;
	}
	public String getMbrRelshpCde() {
		return mbrRelshpCde;
	}
	public void setMbrRelshpCde(String mbrRelshpCde) {
		this.mbrRelshpCde = mbrRelshpCde;
	}
	public String getPrcdrModfrCde() {
		return prcdrModfrCde;
	}
	public void setPrcdrModfrCde(String prcdrModfrCde) {
		this.prcdrModfrCde = prcdrModfrCde;
	}
	public String getEditCde1() {
		return editCde1;
	}
	public void setEditCde1(String editCde1) {
		this.editCde1 = editCde1;
	}
	public String getEditCde2() {
		return editCde2;
	}
	public void setEditCde2(String editCde2) {
		this.editCde2 = editCde2;
	}
	public String getPrvdrStCd() {
		return prvdrStCd;
	}
	public void setPrvdrStCd(String prvdrStCd) {
		this.prvdrStCd = prvdrStCd;
	}
	public String getBillTypCd() {
		return billTypCd;
	}
	public void setBillTypCd(String billTypCd) {
		this.billTypCd = billTypCd;
	}
	public String getTtCd() {
		return ttCd;
	}
	public void setTtCd(String ttCd) {
		this.ttCd = ttCd;
	}
	public String getAtchmtInd() {
		return atchmtInd;
	}
	public void setAtchmtInd(String atchmtInd) {
		this.atchmtInd = atchmtInd;
	}
	public String getPatMbrCd() {
		return patMbrCd;
	}
	public void setPatMbrCd(String patMbrCd) {
		this.patMbrCd = patMbrCd;
	}
	public String getHospFcilCd() {
		return hospFcilCd;
	}
	public void setHospFcilCd(String hospFcilCd) {
		this.hospFcilCd = hospFcilCd;
	}
	
	public String getDaysFromInjry() {
		return daysFromInjry;
	}
	public void setDaysFromInjry(String daysFromInjry) {
		this.daysFromInjry = daysFromInjry;
	}
	public String getDaysFromIlns() {
		return daysFromIlns;
	}
	public void setDaysFromIlns(String daysFromIlns) {
		this.daysFromIlns = daysFromIlns;
	}
	public String getHmoClsCd() {
		return hmoClsCd;
	}
	public void setHmoClsCd(String hmoClsCd) {
		this.hmoClsCd = hmoClsCd;
	}
	public String getTotChrgSign() {
		return totChrgSign;
	}
	public void setTotChrgSign(String totChrgSign) {
		this.totChrgSign = totChrgSign;
	}
	public String getTotChargeAmount() {
		return totChargeAmount;
	}
	public void setTotChargeAmount(String totChargeAmount) {
		this.totChargeAmount = totChargeAmount;
	}
	public String getWpdDelFlag() {
		return wpdDelFlag;
	}
	public void setWpdDelFlag(String wpdDelFlag) {
		this.wpdDelFlag = wpdDelFlag;
	}
	
	public String getClmSrvcCd() {
		return clmSrvcCd;
	}
	public void setClmSrvcCd(String clmSrvcCd) {
		this.clmSrvcCd = clmSrvcCd;
	}
	
	public String getClmProcMdfrCd() {
		return clmProcMdfrCd;
	}
	public void setClmProcMdfrCd(String clmProcMdfrCd) {
		this.clmProcMdfrCd = clmProcMdfrCd;
	}
	public String getClmTtCd() {
		return clmTtCd;
	}
	public void setClmTtCd(String clmTtCd) {
		this.clmTtCd = clmTtCd;
	}
	public String getClmPlaceOfSrvc() {
		return clmPlaceOfSrvc;
	}
	public void setClmPlaceOfSrvc(String clmPlaceOfSrvc) {
		this.clmPlaceOfSrvc = clmPlaceOfSrvc;
	}
	public String getClmHmoClsCd() {
		return clmHmoClsCd;
	}
	public void setClmHmoClsCd(String clmHmoClsCd) {
		this.clmHmoClsCd = clmHmoClsCd;
	}
	public String getClmSameDaySvcInd() {
		return clmSameDaySvcInd;
	}
	public void setClmSameDaySvcInd(String clmSameDaySvcInd) {
		this.clmSameDaySvcInd = clmSameDaySvcInd;
	}
	
	public String getCpayInd() {
		return cpayInd;
	}
	public void setCpayInd(String cpayInd) {
		this.cpayInd = cpayInd;
	}
	public String getHndrdPctInd() {
		return hndrdPctInd;
	}
	public void setHndrdPctInd(String hndrdPctInd) {
		this.hndrdPctInd = hndrdPctInd;
	}
	public String getMedcrAsgnInd() {
		return medcrAsgnInd;
	}
	public void setMedcrAsgnInd(String medcrAsgnInd) {
		this.medcrAsgnInd = medcrAsgnInd;
	}
	
	public String getDiagInd() {
		return diagInd;
	}
	public void setDiagInd(String diagInd) {
		this.diagInd = diagInd;
	}
	public String getClmDiagInd() {
		return clmDiagInd;
	}
	public void setClmDiagInd(String clmDiagInd) {
		this.clmDiagInd = clmDiagInd;
	}
	public String getPcpInd() {
		return pcpInd;
	}
	public void setPcpInd(String pcpInd) {
		this.pcpInd = pcpInd;
	}
	public String getProcMdfrCd2() {
		return procMdfrCd2;
	}
	public void setProcMdfrCd2(String procMdfrCd2) {
		this.procMdfrCd2 = procMdfrCd2;
	}
	public String getClmProcMdfrCd2() {
		return clmProcMdfrCd2;
	}
	public void setClmProcMdfrCd2(String clmProcMdfrCd2) {
		this.clmProcMdfrCd2 = clmProcMdfrCd2;
	}
	public String getSprtHcpsInd() {
		return sprtHcpsInd;
	}
	public void setSprtHcpsInd(String sprtHcpsInd) {
		this.sprtHcpsInd = sprtHcpsInd;
	}
	public String getClmSprtHcpsInd() {
		return clmSprtHcpsInd;
	}
	public void setClmSprtHcpsInd(String clmSprtHcpsInd) {
		this.clmSprtHcpsInd = clmSprtHcpsInd;
	}
	public String getCodeTypeValue() {
		return codeTypeValue;
	}
	public void setCodeTypeValue(String codeTypeValue) {
		this.codeTypeValue = codeTypeValue;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public void setClmIcdVrsnIndicator(String clmIcdVrsnIndicator) {
		this.clmIcdVrsnIndicator = clmIcdVrsnIndicator;
	}

	public String getClmIcdVrsnIndicator() {
		return clmIcdVrsnIndicator;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	

	public String getPvdrLicId() {
		return pvdrLicId;
	}

	public String getPvdrMdcrId() {
		return pvdrMdcrId;
	}

	public String getBlngPvdrNr() {
		return blngPvdrNr;
	}

	public String getRndrPvdrNr() {
		return rndrPvdrNr;
	}

	public String getBlngNpi() {
		return blngNpi;
	}

	public String getRndrNpi() {
		return rndrNpi;
	}

	public String getElgblExpnsSignCd() {
		return elgblExpnsSignCd;
	}

	public String getElgblExpnsAmt() {
		return elgblExpnsAmt;
	}

	public String getAgeTypeCd() {
		return ageTypeCd;
	}

	public String getDrgCd() {
		return drgCd;
	}

	public String getProvSpecCdInd() {
		return provSpecCdInd;
	}

	public void setPvdrLicId(String pvdrLicId) {
		this.pvdrLicId = pvdrLicId;
	}

	public void setPvdrMdcrId(String pvdrMdcrId) {
		this.pvdrMdcrId = pvdrMdcrId;
	}

	public void setBlngPvdrNr(String blngPvdrNr) {
		this.blngPvdrNr = blngPvdrNr;
	}

	public void setRndrPvdrNr(String rndrPvdrNr) {
		this.rndrPvdrNr = rndrPvdrNr;
	}

	public void setBlngNpi(String blngNpi) {
		this.blngNpi = blngNpi;
	}

	public void setRndrNpi(String rndrNpi) {
		this.rndrNpi = rndrNpi;
	}

	public void setElgblExpnsSignCd(String elgblExpnsSignCd) {
		this.elgblExpnsSignCd = elgblExpnsSignCd;
	}

	public void setElgblExpnsAmt(String elgblExpnsAmt) {
		this.elgblExpnsAmt = elgblExpnsAmt;
	}

	public void setAgeTypeCd(String ageTypeCd) {
		this.ageTypeCd = ageTypeCd;
	}

	public void setDrgCd(String drgCd) {
		this.drgCd = drgCd;
	}

	public void setProvSpecCdInd(String provSpecCdInd) {
		this.provSpecCdInd = provSpecCdInd;
	}

}
