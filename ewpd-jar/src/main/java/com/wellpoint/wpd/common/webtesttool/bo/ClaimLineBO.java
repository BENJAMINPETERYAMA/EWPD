/*
 * Created on Aug 6, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.bo;

import java.util.List;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClaimLineBO{
	
	private String claimLineId;
	private String testCaseId;
	private String diagnosisCode;
	private String hcpcCode;
	private String modifierCode;
	private String ttCode;
	private String revenueCode;
	private String typeOfBill;
	private String placeOfService;
	
	private String expectedBenefitComponent;
	private String expectedBenefit;
	private String expectedBasicBenefit;
	private String expectedRiderBenefit;
	
	private String benefitCmptName;
	private int benefitCmptSysId;
	
	private String benefitName;
	private int 	benefitSysId;
	private String benefitCategory;
	private String benefitType;
	
	private List claimLineList;
	
	private String actualBenefitComponent;
	private String actualBenefit;
	private String actualBasicBenefit;
	private String actualRiderBenefit;
	private boolean status;
	private boolean accidentRider;
	
	private String expectedBenefitComponentDesc;
	private String expectedBasicBenefitDesc;
	private String expectedMajBenefitDesc;
	private String expectedRiderBenefitDesc;
	private String typeOfBillCode;
	
	/**
	 * @return Returns the expectedBasicBenefitDesc.
	 */
	public String getExpectedBasicBenefitDesc() {
		String temp="";
		if((expectedBasicBenefit != null) && (expectedBasicBenefit.length()>0) )
			temp = expectedBasicBenefit.split("~")[1];
		return temp;
	}
	/**
	 * @param expectedBasicBenefitDesc The expectedBasicBenefitDesc to set.
	 */
	public void setExpectedBasicBenefitDesc(String expectedBasicBenefitDesc) {
		this.expectedBasicBenefitDesc = expectedBasicBenefitDesc;
	}
	/**
	 * @return Returns the expectedBenefitComponentDesc.
	 */
	public String getExpectedBenefitComponentDesc() {
		String temp="";
		if((expectedBenefitComponent != null) && (expectedBenefitComponent.length()>0) )
			temp = expectedBenefitComponent.split("~")[1];
		return temp;
	}
	/**
	 * @param expectedBenefitComponentDesc The expectedBenefitComponentDesc to set.
	 */
	public void setExpectedBenefitComponentDesc(
			String expectedBenefitComponentDesc) {
		this.expectedBenefitComponentDesc = expectedBenefitComponentDesc;
	}
	/**
	 * @return Returns the expectedMajBenefitDesc.
	 */
	public String getExpectedMajBenefitDesc() {
		String temp="";
		if((expectedBenefit != null) && (expectedBenefit.length()>0) )
			temp = expectedBenefit.split("~")[1];
		return temp;
	}
	/**
	 * @param expectedMajBenefitDesc The expectedMajBenefitDesc to set.
	 */
	public void setExpectedMajBenefitDesc(String expectedMajBenefitDesc) {
		this.expectedMajBenefitDesc = expectedMajBenefitDesc;
	}
	/**
	 * @return Returns the expectedRiderBenefitDesc.
	 */
	public String getExpectedRiderBenefitDesc() {
		String temp="";
		if((expectedRiderBenefit != null) && (expectedRiderBenefit.length()>0) )
			temp = expectedRiderBenefit.split("~")[1];
		return temp;
	}
	/**
	 * @param expectedRiderBenefitDesc The expectedRiderBenefitDesc to set.
	 */
	public void setExpectedRiderBenefitDesc(String expectedRiderBenefitDesc) {
		this.expectedRiderBenefitDesc = expectedRiderBenefitDesc;
	}
	/**
	 * @return Returns the typeOfBillCode.
	 */
	public String getTypeOfBillCode() {
		return typeOfBillCode;
	}
	/**
	 * @param typeOfBillCode The typeOfBillCode to set.
	 */
	public void setTypeOfBillCode(String typeOfBillCode) {
		this.typeOfBillCode = typeOfBillCode;
	}
	/**
	 * @return Returns the actualBasicBenefit.
	 */
	public String getActualBasicBenefit() {
		return actualBasicBenefit;
	}
	/**
	 * @param actualBasicBenefit The actualBasicBenefit to set.
	 */
	public void setActualBasicBenefit(String actualBasicBenefit) {
		this.actualBasicBenefit = actualBasicBenefit;
	}
	/**
	 * @return Returns the actualBenefit.
	 */
	public String getActualBenefit() {
		return actualBenefit;
	}
	/**
	 * @param actualBenefit The actualBenefit to set.
	 */
	public void setActualBenefit(String actualBenefit) {
		this.actualBenefit = actualBenefit;
	}
	/**
	 * @return Returns the actualBenefitComponent.
	 */
	public String getActualBenefitComponent() {
		return actualBenefitComponent;
	}
	/**
	 * @param actualBenefitComponent The actualBenefitComponent to set.
	 */
	public void setActualBenefitComponent(String actualBenefitComponent) {
		this.actualBenefitComponent = actualBenefitComponent;
	}
	/**
	 * @return Returns the actualRiderBenefit.
	 */
	public String getActualRiderBenefit() {
		return actualRiderBenefit;
	}
	/**
	 * @param actualRiderBenefit The actualRiderBenefit to set.
	 */
	public void setActualRiderBenefit(String actualRiderBenefit) {
		this.actualRiderBenefit = actualRiderBenefit;
	}
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}
	/**
	 * @return Returns the benefitCmptName.
	 */
	public String getBenefitCmptName() {
		return benefitCmptName;
	}
	/**
	 * @param benefitCmptName The benefitCmptName to set.
	 */
	public void setBenefitCmptName(String benefitCmptName) {
		this.benefitCmptName = benefitCmptName;
	}
	/**
	 * @return Returns the benefitCmptSysId.
	 */
	public int getBenefitCmptSysId() {
		return benefitCmptSysId;
	}
	/**
	 * @param benefitCmptSysId The benefitCmptSysId to set.
	 */
	public void setBenefitCmptSysId(int benefitCmptSysId) {
		this.benefitCmptSysId = benefitCmptSysId;
	}
	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the benefitType.
	 */
	public String getBenefitType() {
		return benefitType;
	}
	/**
	 * @param benefitType The benefitType to set.
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	/**
	 * @return Returns the claimLineId.
	 */
	public String getClaimLineId() {
		return claimLineId;
	}
	/**
	 * @param claimLineId The claimLineId to set.
	 */
	public void setClaimLineId(String claimLineId) {
		this.claimLineId = claimLineId;
	}
	/**
	 * @return Returns the claimLineList.
	 */
	public List getClaimLineList() {
		return claimLineList;
	}
	/**
	 * @param claimLineList The claimLineList to set.
	 */
	public void setClaimLineList(List claimLineList) {
		this.claimLineList = claimLineList;
	}
	/**
	 * @return Returns the diagnosisCode.
	 */
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	/**
	 * @param diagnosisCode The diagnosisCode to set.
	 */
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	/**
	 * @return Returns the expectedBasicBenefit.
	 */
	public String getExpectedBasicBenefit() {
		return expectedBasicBenefit;
	}
	/**
	 * @param expectedBasicBenefit The expectedBasicBenefit to set.
	 */
	public void setExpectedBasicBenefit(String expectedBasicBenefit) {
		this.expectedBasicBenefit = expectedBasicBenefit;
	}
	/**
	 * @return Returns the expectedBenefit.
	 */
	public String getExpectedBenefit() {
		return expectedBenefit;
	}
	/**
	 * @param expectedBenefit The expectedBenefit to set.
	 */
	public void setExpectedBenefit(String expectedBenefit) {
		this.expectedBenefit = expectedBenefit;
	}
	/**
	 * @return Returns the expectedBenefitComponent.
	 */
	public String getExpectedBenefitComponent() {
		return expectedBenefitComponent;
	}
	/**
	 * @param expectedBenefitComponent The expectedBenefitComponent to set.
	 */
	public void setExpectedBenefitComponent(String expectedBenefitComponent) {
		this.expectedBenefitComponent = expectedBenefitComponent;
	}
	/**
	 * @return Returns the expectedRiderBenefit.
	 */
	public String getExpectedRiderBenefit() {
		return expectedRiderBenefit;
	}
	/**
	 * @param expectedRiderBenefit The expectedRiderBenefit to set.
	 */
	public void setExpectedRiderBenefit(String expectedRiderBenefit) {
		this.expectedRiderBenefit = expectedRiderBenefit;
	}
	/**
	 * @return Returns the hcpcCode.
	 */
	public String getHcpcCode() {
		return hcpcCode;
	}
	/**
	 * @param hcpcCode The hcpcCode to set.
	 */
	public void setHcpcCode(String hcpcCode) {
		this.hcpcCode = hcpcCode;
	}
	/**
	 * @return Returns the modifierCode.
	 */
	public String getModifierCode() {
		return modifierCode;
	}
	/**
	 * @param modifierCode The modifierCode to set.
	 */
	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode;
	}
	/**
	 * @return Returns the placeOfService.
	 */
	public String getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the revenueCode.
	 */
	public String getRevenueCode() {
		return revenueCode;
	}
	/**
	 * @param revenueCode The revenueCode to set.
	 */
	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return Returns the testCaseId.
	 */
	public String getTestCaseId() {
		return testCaseId;
	}
	/**
	 * @param testCaseId The testCaseId to set.
	 */
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	/**
	 * @return Returns the ttCode.
	 */
	public String getTtCode() {
		return ttCode;
	}
	/**
	 * @param ttCode The ttCode to set.
	 */
	public void setTtCode(String ttCode) {
		this.ttCode = ttCode;
	}
	/**
	 * @return Returns the typeOfBill.
	 */
	public String getTypeOfBill() {
		return typeOfBill;
	}
	/**
	 * @param typeOfBill The typeOfBill to set.
	 */
	public void setTypeOfBill(String typeOfBill) {
		this.typeOfBill = typeOfBill;
	}
	/**
	 * @return Returns the accidentRider.
	 */
	public boolean isAccidentRider() {
		return accidentRider;
	}
	/**
	 * @param accidentRider The accidentRider to set.
	 */
	public void setAccidentRider(boolean accidentRider) {
		this.accidentRider = accidentRider;
	}
}
