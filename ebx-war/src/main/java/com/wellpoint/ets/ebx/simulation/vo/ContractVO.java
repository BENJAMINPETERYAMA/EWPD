/*
 * <ContractVO.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionDetailVO;

/**
 * @author UST-GLOBAL
 * 
 *         Value Object Class for storing contract details like mappings, error
 *         codes.
 * 
 */
public class ContractVO {
	private String contractId;
	private String system;
	private String effectiveDate;
	private String expiryDate;
	private String revisionDate;
	private String productFamily;
	private int version;
	private String businessEntity;
	private String groupStateHQ;
	private List errorExclusionVOList;
	private String startDate;
	private String endDate;
	private String WPDAccum;
	private String dateOfBirth;
	//HPN product code and short description
	private String hpnProduct;
	private String hpnProductSDesc;
	
	/**
	 * List of Contract Mapping VOs.
	 */
	private List contractMappingVOList = new ArrayList();

	private boolean cdhpFlag = false;
	private boolean flagDrugRiderBenefit = false;
	private boolean flagHMO = false;
	private boolean flagEPO = false;
	private boolean flagHCR_E016And17 = false;
	private boolean flagHCR_E018 = false;
	private boolean flagSensitiveBenefit = false;
	private boolean productionFlag = false;
	
	private boolean isEB01U;
	private List listOfEB03forEB01U;

	

	private List errorCodesList = new ArrayList();
	private List listOfEB0360forE010 = new ArrayList();

	private Map majorHeadings;
	private boolean flagEB0360_In = false;
	private boolean flagEB0360_Out = false;

	private boolean isMedSUPContract;
	private String answerCalYearOrBenYear;
	private String lineOfBusiness;
	private String productCode;
	private String serviceTypeCode;
	//Flags added for E034 validation
	private String copayIndicatorFlag="";
	private String dedIndicatorFlag="";
	/**
	 * To indicate whether there are more records to be iterated through in
	 * Benefit accum response
	 */
	private String moreDataForPagination;
	/**
	 * To indicate the size of the next page in Benefit-accum response
	 */
	private String nextPageSize;
	/**
	 * Sets a map of sps id and its corresponding coded value set --July Release
	 */
	private Map spsCodedValue;
	/**
	 * Sets a list of accum values for WPD/eWPD --July Release
	 */
	private Set accumHippaSegmentList;
	/**
	 * Comment for <code>errorExclusionDetailVO</code>
	 * Error exclusion detail vo stored in a contract.
	 */
	private ErrorExclusionDetailVO errorExclusionDetailVO = new ErrorExclusionDetailVO();
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

    /**
     *  Variable to hold the group name of the contract -- SSCR 16331 Nov Release
     */
    private String groupName;
    /**
     *  Variable which would be set to true if the LG contract has an MCS/TPA arrangement -- SSCR 16331 Nov Release
     */
    private boolean contractMCSTPA = false;


    
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public boolean isFlagEB0360_In() {
		return flagEB0360_In;
	}

	public void setFlagEB0360_In(boolean flagEB0360_In) {
		this.flagEB0360_In = flagEB0360_In;
	}

	public boolean isFlagEB0360_Out() {
		return flagEB0360_Out;
	}

	public void setFlagEB0360_Out(boolean flagEB0360_Out) {
		this.flagEB0360_Out = flagEB0360_Out;
	}

	public Map getMajorHeadings() {
		return majorHeadings;
	}

	public void setMajorHeadings(Map majorHeadings) {
		this.majorHeadings = majorHeadings;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List getErrorCodesList() {
		return errorCodesList;
	}

	public void setErrorCodesList(List errorCodesList) {
		this.errorCodesList = errorCodesList;
	}

	public String getProductFamily() {
		return productFamily;
	}

	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	public boolean isCDHPFlag() {
		return cdhpFlag;
	}

	public void setFlagCDHP(boolean cdhpFlag) {
		this.cdhpFlag = cdhpFlag;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isFlagDrugRiderBenefit() {
		return flagDrugRiderBenefit;
	}

	public void setFlagDrugRiderBenefit(boolean flagDrugRiderBenefit) {
		this.flagDrugRiderBenefit = flagDrugRiderBenefit;
	}

	public boolean isFlagEPO() {
		return flagEPO;
	}

	public void setFlagEPO(boolean flagEPO) {
		this.flagEPO = flagEPO;
	}

	public boolean isFlagHMO() {
		return flagHMO;
	}

	public void setFlagHMO(boolean flagHMO) {
		this.flagHMO = flagHMO;
	}

	public boolean isFlagHCR_E016And17() {
		return flagHCR_E016And17;
	}

	public void setFlagHCR_E016And17(boolean flagHCR_E016And17) {
		this.flagHCR_E016And17 = flagHCR_E016And17;
	}

	public boolean isFlagHCR_E018() {
		return flagHCR_E018;
	}

	public void setFlagHCR_E018(boolean flagHCR_E018) {
		this.flagHCR_E018 = flagHCR_E018;
	}

	public boolean isFlagSensitiveBenefit() {
		return flagSensitiveBenefit;
	}

	public void setFlagSensitiveBenefit(boolean flagSensitiveBenefit) {
		this.flagSensitiveBenefit = flagSensitiveBenefit;
	}

	public boolean isEB01U() {
		return isEB01U;
	}

	public void setEB01U(boolean isEB01U) {
		this.isEB01U = isEB01U;
	}

	public List getListOfEB03forEB01U() {
		return listOfEB03forEB01U;
	}

	public void setListOfEB03forEB01U(List listOfEB03forEB01U) {
		this.listOfEB03forEB01U = listOfEB03forEB01U;
	}

	public List getListOfEB0360forE010() {
		return listOfEB0360forE010;
	}

	public void setListOfEB0360forE010(List listOfEB0360forE010) {
		this.listOfEB0360forE010 = listOfEB0360forE010;
	}

	public String getBusinessEntity() {
		return businessEntity;
	}

	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	public String getGroupStateHQ() {
		return groupStateHQ;
	}

	public void setGroupStateHQ(String groupStateHQ) {
		this.groupStateHQ = groupStateHQ;
	}

	public String getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}

	public boolean isMedSUPContract() {
		return isMedSUPContract;
	}

	public void setMedSUPContract(boolean isMedSUPContract) {
		this.isMedSUPContract = isMedSUPContract;
	}

	public String getAnswerCalYearOrBenYear() {
		return answerCalYearOrBenYear;
	}

	public void setAnswerCalYearOrBenYear(String answerCalYearOrBenYear) {
		this.answerCalYearOrBenYear = answerCalYearOrBenYear;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public List getErrorExclusionVOList() {
		return errorExclusionVOList;
	}

	public void setErrorExclusionVOList(List errorExclusionVOList) {
		this.errorExclusionVOList = errorExclusionVOList;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	public String getMoreDataForPagination() {
		return moreDataForPagination;
	}

	public void setMoreDataForPagination(String moreDataForPagination) {
		this.moreDataForPagination = moreDataForPagination;
	}

	public String getNextPageSize() {
		return nextPageSize;
	}

	public void setNextPageSize(String nextPageSize) {
		this.nextPageSize = nextPageSize;
	}

	public List getContractMappingVOList() {
		return contractMappingVOList;
	}

	public void setContractMappingVOList(List contractMappingVOList) {
		this.contractMappingVOList = contractMappingVOList;
	}

	public ErrorExclusionDetailVO getErrorExclusionDetailVO() {
		return errorExclusionDetailVO;
	}

	public void setErrorExclusionDetailVO(
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		this.errorExclusionDetailVO = errorExclusionDetailVO;
	}

	public String getCopayIndicatorFlag() {
		return copayIndicatorFlag;
	}

	public void setCopayIndicatorFlag(String copayIndicatorFlag) {
		this.copayIndicatorFlag = copayIndicatorFlag;
	}

	public String getDedIndicatorFlag() {
		return dedIndicatorFlag;
	}

	public void setDedIndicatorFlag(String dedIndicatorFlag) {
		this.dedIndicatorFlag = dedIndicatorFlag;
	}
	
	public Map getSpsCodedValue() {
		return spsCodedValue;
	}

	public void setSpsCodedValue(Map spsCodedValue) {
		this.spsCodedValue = spsCodedValue;
	}


	public void setAccumHippaSegmentList(Set accumHippaSegmentList) {
		this.accumHippaSegmentList = accumHippaSegmentList;
	}

	public Set getAccumHippaSegmentList() {
		return accumHippaSegmentList;
	}

	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	public String getBusinessGroup() {
		return businessGroup;
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

	public boolean isProductionFlag() {
		return productionFlag;
	}

	public void setProductionFlag(boolean productionFlag) {
		this.productionFlag = productionFlag;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isContractMCSTPA() {
		return contractMCSTPA;
	}

	public void setContractMCSTPA(boolean contractMCSTPA) {
		this.contractMCSTPA = contractMCSTPA;
	}
	
	public String getWPDAccum() {
		return WPDAccum;
	}

	public void setWPDAccum(String wPDAccum) {
		WPDAccum = wPDAccum;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
