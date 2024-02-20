/*
 * <ContractDataObjectVO.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.vo;

import java.util.List;
import java.util.Map;




/**
 * @author UST-GLOBAL
 * 
 * Value Object Class for storing Contract Details from DB
 * 
 */
public class ContractDataObjectVO {
	
    private String  contractId = "";

    private String  spsParameterId = "";
    private String  spsDescription = "";
    private String  levelDescription;
    private String  accumrSPSParameterId;
    private String  ruleId;

    private String  majorHeading = "";
    private String  minorHeading = "";
    private String  variableId = "";
    private String  mappedVariableId;
    private String  format = "";
    private String  variableValue = "";
    private String  pva = "";
    private String  hippaSegment;
    private String  hippaValue;

    private String eb01 = "";
    private String eb02 = "";
    private String eb06 = "";
    private String eb09 = "";
    private String hsd1 = "";
    private String hsd2 = "";
    private String hsd3 = "";
    private String hsd4 = "";
    private String hsd5 = "";
    private String hsd6 = "";
    private String hsd7 = "";
    private String hsd8 = "";

    private String productFamily;
    private String serviceTypeCode;
    private String sensitiveBenefitIndicator;

    private String businessEntity;
    private String groupStateHQ;
    private String endDate;
    private String startDate;
    private String revisionDate;
    private String  variableDescription = "";
    
    private String effectiveDate = "";
    private String variableFormat = "";
    private String eb03 = "";
    private String iii02 = "";
    private String message = "";
    private String msgReqType = "";
    private String medssaeType = "";
    private String extndMsgTxt1 = "";
	private String extndMsgTxt2 = "";
	private String extndMsgTxt3 = "";	
	private String eb12ChangeInd = "";
	private String eb01_extndMsgTxt1;
	private String eb01_extndMsgTxt2;
	private String eb01_extndMsgTxt3;	
	private String eb01_eb12ChangeInd;
	private String highPrfrmnTierdMsg = "";
	private String highPrfrmnNonTierdMsg = ""; 
    private String accum1 = "";
    private String accum2 = "";
    private String accum3 = "";
    private String accum4 = "";
    private String accum5 = "";
    private String accum6 = "";
    private String accum7 = "";
    private String accum8 = "";
    private String accum9 = "";
    private String accum10 = "";
    private String sensitiveInd = "";
    private String finalized = "";
    private String notApplicable = "";
    
    private String benefitComponent = "";
    private String benefit = "";
    private String spsIdDesc = "";
    private String spsId = "";
    private String spsPva = "";
    private String spsDataType = "";
    private String lineValue = "";

    private String mappingStatus;
    private String mappingComplete;
    
    private String lineOfBusiness;
    private String productCode;
    private String contractAdministration;
    
    private String categoryCode;
    
    private String umRule;
    
    private String startAge;
    private String endAge;
    private String version;
    private String noteType;
    private String WPDAccumulator;
    
    /**
     * Variable to hold the value of EB03Association as a change in SSCR 19537
     */
    private String eb03Association;
    
    /**
     * Variable to hold the value of EB03Association indicator as a change in SSCR 19537
     */
    private String indvdlEb03AssnIndicator;

    /**
     *  Variable to hold the group name of the contract -- SSCR 16331 Nov Release
     */
    private String hpnProduct;
    private String hpnProductSDesc;
    private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getUmRule() {
		return umRule;
	}


	public void setUmRule(String umRule) {
		this.umRule = umRule;
	}


	/**
     * Variable to hold the Tier description-July Release
     */
    private String tierDescription;
    
    /**
     * Variable to hold the eWPD Business Group to identify whether the contract is LG/ISG - July Release
     */
    private String businessGroup;
    
    /**
     *  set the value of the corporate plan - September release.
     */
    private String corporatePlan;
    
    /**
     *  set the value of the benefitStructure - September release.
     */
    private String benefitStructure;

    public String getVariableId() {
        return variableId;
    }


    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }


    public String getHippaValue() {
        return hippaValue;
    }


    public void setHippaValue(String hippaValue) {
        this.hippaValue = hippaValue;
    }


    public String getHippaSegment() {
        return hippaSegment;
    }


    public void setHippaSegment(String hippaSegment) {
        this.hippaSegment = hippaSegment;
    }


    public String getMajorHeading() {
        return majorHeading;
    }


    public void setMajorHeading(String majorHeading) {
        this.majorHeading = majorHeading;
    }


    public String getMinorHeading() {
        return minorHeading;
    }


    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }


    public String getRuleId() {
        return ruleId;
    }


    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }


    public String getSpsParameterId() {
        return spsParameterId;
    }


    public void setSpsParameterId(String spsParameterId) {
        this.spsParameterId = spsParameterId;
    }



    public String getContractId() {
        return contractId;
    }


    public void setContractId(String contractId) {
        this.contractId = contractId;
    }


    public String getFormat() {
        return format;
    }


    public void setFormat(String format) {
        this.format = format;
    }


    public String getMappedVariableId() {
        return mappedVariableId;
    }


    public void setMappedVariableId(String mappedVariableId) {
        this.mappedVariableId = mappedVariableId;
    }


    public String getPva() {
        return pva;
    }


    public void setPva(String pva) {
        this.pva = pva;
    }


    public String getVariableValue() {
        return variableValue;
    }


    /**
	 * @return the startAge
	 */
	public String getStartAge() {
		return startAge;
	}


	/**
	 * @param startAge the startAge to set
	 */
	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}


	/**
	 * @return the endAge
	 */
	public String getEndAge() {
		return endAge;
	}


	/**
	 * @param endAge the endAge to set
	 */
	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}


	public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }


    public String getProductFamily() {
        return productFamily;
    }


    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }


    public String getEB01() {
        return eb01;
    }


    public void setEB01(String eb01) {
        this.eb01 = eb01;
    }


    public String getEB02() {
        return eb02;
    }


    public void setEB02(String eb02) {
        this.eb02 = eb02;
    }


    public String getEB06() {
        return eb06;
    }


    public void setEB06(String eb06) {
        this.eb06 = eb06;
    }


    public String getEB09() {
        return eb09;
    }


    public void setEB09(String eb09) {
        this.eb09 = eb09;
    }


    public String getHSD1() {
        return hsd1;
    }


    public void setHSD1(String hsd1) {
        this.hsd1 = hsd1;
    }


    public String getHSD2() {
        return hsd2;
    }


    public void setHSD2(String hsd2) {
        this.hsd2 = hsd2;
    }


    public String getHSD3() {
        return hsd3;
    }


    public void setHSD3(String hsd3) {
        this.hsd3 = hsd3;
    }


    public String getHSD4() {
        return hsd4;
    }


    public void setHSD4(String hsd4) {
        this.hsd4 = hsd4;
    }


    public String getHSD5() {
        return hsd5;
    }


    public void setHSD5(String hsd5) {
        this.hsd5 = hsd5;
    }


    public String getHSD6() {
        return hsd6;
    }


    public void setHSD6(String hsd6) {
        this.hsd6 = hsd6;
    }


    public String getHSD7() {
        return hsd7;
    }


    public void setHSD7(String hsd7) {
        this.hsd7 = hsd7;
    }


    public String getHSD8() {
        return hsd8;
    }


    public void setHSD8(String hsd8) {
        this.hsd8 = hsd8;
    }


    public String getAccumrSPSParameterId() {
        return accumrSPSParameterId;
    }


    public void setAccumrSPSParameterId(String accumrSPSParameterId) {
        this.accumrSPSParameterId = accumrSPSParameterId;
    }


    public String getServiceTypeCode() {
        return serviceTypeCode;
    }


    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }


    public String getSensitiveBenefitIndicator() {
        return sensitiveBenefitIndicator;
    }


    public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
        this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
    }


    public String getBusinessEntity() {
        return businessEntity;
    }


    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }


    public String getEndDate() {
        return endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getGroupStateHQ() {
        return groupStateHQ;
    }


    public void setGroupStateHQ(String groupStateHQ) {
        this.groupStateHQ = groupStateHQ;
    }


    public String getStartDate() {
        return startDate;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


	public String getSpsDescription() {
		return spsDescription;
	}


	public void setSpsDescription(String spsDescription) {
		this.spsDescription = spsDescription;
	}


	public String getVariableDescription() {
		return variableDescription;
	}


	public void setVariableDescription(String variableDescription) {
		this.variableDescription = variableDescription;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getLevelDescription() {
		return levelDescription;
	}
	
	
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
     public String getRevisionDate() {
		return revisionDate;
	}
	

	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}


	public String getHsd1() {
		return hsd1;
	}


	public void setHsd1(String hsd1) {
		this.hsd1 = hsd1;
	}


	public String getHsd2() {
		return hsd2;
	}


	public void setHsd2(String hsd2) {
		this.hsd2 = hsd2;
	}


	public String getHsd3() {
		return hsd3;
	}


	public void setHsd3(String hsd3) {
		this.hsd3 = hsd3;
	}


	public String getHsd4() {
		return hsd4;
	}


	public void setHsd4(String hsd4) {
		this.hsd4 = hsd4;
	}


	public String getHsd5() {
		return hsd5;
	}


	public void setHsd5(String hsd5) {
		this.hsd5 = hsd5;
	}


	public String getHsd6() {
		return hsd6;
	}


	public void setHsd6(String hsd6) {
		this.hsd6 = hsd6;
	}


	public String getHsd7() {
		return hsd7;
	}


	public void setHsd7(String hsd7) {
		this.hsd7 = hsd7;
	}


	public String getHsd8() {
		return hsd8;
	}


	public void setHsd8(String hsd8) {
		this.hsd8 = hsd8;
	}

	public String getIii02() {
		return iii02;
	}


	public void setIii02(String iii02) {
		this.iii02 = iii02;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMsgReqType() {
		return msgReqType;
	}


	public void setMsgReqType(String msgReqType) {
		this.msgReqType = msgReqType;
	}


	public String getMedssaeType() {
		return medssaeType;
	}


	public void setMedssaeType(String medssaeType) {
		this.medssaeType = medssaeType;
	}


	public String getExtndMsgTxt1() {
		return extndMsgTxt1;
	}

	public void setExtndMsgTxt1(String extndMsgTxt1) {
		this.extndMsgTxt1 = extndMsgTxt1;
	}

	public String getExtndMsgTxt2() {
		return extndMsgTxt2;
	}

	public void setExtndMsgTxt2(String extndMsgTxt2) {
		this.extndMsgTxt2 = extndMsgTxt2;
	}

	public String getExtndMsgTxt3() {
		return extndMsgTxt3;
	}

	public void setExtndMsgTxt3(String extndMsgTxt3) {
		this.extndMsgTxt3 = extndMsgTxt3;
	}

	public String getEb12ChangeInd() {
		return eb12ChangeInd;
	}

	public void setEb12ChangeInd(String eb12ChangeInd) {
		this.eb12ChangeInd = eb12ChangeInd;
	}

	public String getEb01_extndMsgTxt1() {
		return eb01_extndMsgTxt1;
	}

	public void setEb01_extndMsgTxt1(String eb01_extndMsgTxt1) {
		this.eb01_extndMsgTxt1 = eb01_extndMsgTxt1;
	}

	public String getEb01_extndMsgTxt2() {
		return eb01_extndMsgTxt2;
	}

	public void setEb01_extndMsgTxt2(String eb01_extndMsgTxt2) {
		this.eb01_extndMsgTxt2 = eb01_extndMsgTxt2;
	}

	public String getEb01_extndMsgTxt3() {
		return eb01_extndMsgTxt3;
	}

	public void setEb01_extndMsgTxt3(String eb01_extndMsgTxt3) {
		this.eb01_extndMsgTxt3 = eb01_extndMsgTxt3;
	}

	public String getEb01_eb12ChangeInd() {
		return eb01_eb12ChangeInd;
	}

	public void setEb01_eb12ChangeInd(String eb01_eb12ChangeInd) {
		this.eb01_eb12ChangeInd = eb01_eb12ChangeInd;
	}

	public String getHighPrfrmnTierdMsg() {
		return highPrfrmnTierdMsg;
	}

	public void setHighPrfrmnTierdMsg(String highPrfrmnTierdMsg) {
		this.highPrfrmnTierdMsg = highPrfrmnTierdMsg;
	}

	public String getHighPrfrmnNonTierdMsg() {
		return highPrfrmnNonTierdMsg;
	}

	public void setHighPrfrmnNonTierdMsg(String highPrfrmnNonTierdMsg) {
		this.highPrfrmnNonTierdMsg = highPrfrmnNonTierdMsg;
	}

	public String getSensitiveInd() {
		return sensitiveInd;
	}


	public void setSensitiveInd(String sensitiveInd) {
		this.sensitiveInd = sensitiveInd;
	}


	public String getVariableFormat() {
		return variableFormat;
	}


	public void setVariableFormat(String variableFormat) {
		this.variableFormat = variableFormat;
	}


	public String getEb01() {
		return eb01;
	}


	public void setEb01(String eb01) {
		this.eb01 = eb01;
	}


	public String getEb02() {
		return eb02;
	}


	public void setEb02(String eb02) {
		this.eb02 = eb02;
	}


	public String getEb06() {
		return eb06;
	}


	public void setEb06(String eb06) {
		this.eb06 = eb06;
	}


	public String getEb09() {
		return eb09;
	}


	public void setEb09(String eb09) {
		this.eb09 = eb09;
	}


	public String getEb03() {
		return eb03;
	}


	public void setEb03(String eb03) {
		this.eb03 = eb03;
	}


	public String getAccum1() {
		return accum1;
	}


	public void setAccum1(String accum1) {
		this.accum1 = accum1;
	}


	public String getAccum2() {
		return accum2;
	}


	public void setAccum2(String accum2) {
		this.accum2 = accum2;
	}


	public String getAccum3() {
		return accum3;
	}


	public void setAccum3(String accum3) {
		this.accum3 = accum3;
	}


	public String getAccum4() {
		return accum4;
	}


	public void setAccum4(String accum4) {
		this.accum4 = accum4;
	}


	public String getAccum5() {
		return accum5;
	}


	public void setAccum5(String accum5) {
		this.accum5 = accum5;
	}


	public String getAccum6() {
		return accum6;
	}


	public void setAccum6(String accum6) {
		this.accum6 = accum6;
	}


	public String getAccum7() {
		return accum7;
	}


	public void setAccum7(String accum7) {
		this.accum7 = accum7;
	}


	public String getAccum8() {
		return accum8;
	}


	public void setAccum8(String accum8) {
		this.accum8 = accum8;
	}


	public String getAccum9() {
		return accum9;
	}


	public void setAccum9(String accum9) {
		this.accum9 = accum9;
	}


	public String getAccum10() {
		return accum10;
	}


	public void setAccum10(String accum10) {
		this.accum10 = accum10;
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


	public String getSpsIdDesc() {
		return spsIdDesc;
	}


	public void setSpsIdDesc(String spsIdDesc) {
		this.spsIdDesc = spsIdDesc;
	}


	public String getSpsId() {
		return spsId;
	}


	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}


	public String getSpsPva() {
		return spsPva;
	}


	public void setSpsPva(String spsPva) {
		this.spsPva = spsPva;
	}


	public String getSpsDataType() {
		return spsDataType;
	}


	public void setSpsDataType(String spsDataType) {
		this.spsDataType = spsDataType;
	}


	public String getLineValue() {
		return lineValue;
	}


	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}


	public String getMappingStatus() {
		return mappingStatus;
	}


	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}


	public String getMappingComplete() {
		return mappingComplete;
	}


	public void setMappingComplete(String mappingComplete) {
		this.mappingComplete = mappingComplete;
	}


	public String getFinalized() {
		return finalized;
	}


	public void setFinalized(String finalized) {
		this.finalized = finalized;
	}


	public String getNotApplicable() {
		return notApplicable;
	}


	public void setNotApplicable(String notApplicable) {
		this.notApplicable = notApplicable;
	}


	public String getLineOfBusiness() {
		return lineOfBusiness;
	}


	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getContractAdministration() {
		return contractAdministration;
	}


	public void setContractAdministration(String contractAdministration) {
		this.contractAdministration = contractAdministration;
	}


	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}


	/**
	 * @param categoryCode the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	/**
	 * @return the tierDescription
	 */
	public String getTierDescription() {
		return tierDescription;
	}


	/**
	 * @param tierDescription the tierDescription to set
	 */
	public void setTierDescription(String tierDescription) {
		this.tierDescription = tierDescription;
	}
	public String getBusinessGroup() {
		return businessGroup;
	}


	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}


	public String getCorporatePlan() {
		return corporatePlan;
	}


	public void setCorporatePlan(String corporatePlan) {
		this.corporatePlan = corporatePlan;
	}


	public String getBenefitStructure() {
		return benefitStructure;
	}


	public void setBenefitStructure(String benefitStructure) {
		this.benefitStructure = benefitStructure;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getWPDAccumulator() {
		return WPDAccumulator;
	}

	public void setWPDAccumulator(String wPDAccumulator) {
		WPDAccumulator = wPDAccumulator;
	}

	public String getEb03Association() {
		return eb03Association;
	}

	public void setEb03Association(String eb03Association) {
		this.eb03Association = eb03Association;
	}

	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}

	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	/**
	 * @return the indvdlEb03AssnIndicator
	 */
	public String getIndvdlEb03AssnIndicator() {
		return indvdlEb03AssnIndicator;
	}

	/**
	 * @param indvdlEb03AssnIndicator the indvdlEb03AssnIndicator to set
	 */
	public void setIndvdlEb03AssnIndicator(String indvdlEb03AssnIndicator) {
		this.indvdlEb03AssnIndicator = indvdlEb03AssnIndicator;
	}

	public String getHpnProduct() {
		return hpnProduct;
	}

	public void setHpnProduct(String hpnProduct) {
		this.hpnProduct = hpnProduct;
	}

	public String getHpnProductSDesc() {
		return hpnProductSDesc;
	}

	public void setHpnProductSDesc(String hpnProductSDesc) {
		this.hpnProductSDesc = hpnProductSDesc;
	}

	

	

}
