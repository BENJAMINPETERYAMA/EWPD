package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.ArrayList;
import java.util.List;

public class ContractMappingWebServiceVO extends MappingWebServiceVO{
	private HippaSegmentWebServiceVO eb04;
	private HippaSegmentWebServiceVO eb05;
	private HippaSegmentWebServiceVO eb07;
	private HippaSegmentWebServiceVO eb08;
	private HippaSegmentWebServiceVO eb10;
	private HippaSegmentWebServiceVO eb11;
	private HippaSegmentWebServiceVO eb12;
	private HippaSegmentWebServiceVO benefitEffectiveStartDate;
	private HippaSegmentWebServiceVO benefitEffectiveEndDate;
	private String iiiMessage;
	
	 
	/**
     * Variable to hold the Tier description-July Release
     */
    private String tierDescription;

	private List<ErrorDetailWebServiceVO> errorCodesList = new ArrayList<ErrorDetailWebServiceVO>();

	public HippaSegmentWebServiceVO getEb04() {
		return eb04;
	}

	public void setEb04(HippaSegmentWebServiceVO eb04) {
		this.eb04 = eb04;
	}

	public HippaSegmentWebServiceVO getEb05() {
		return eb05;
	}

	public void setEb05(HippaSegmentWebServiceVO eb05) {
		this.eb05 = eb05;
	}

	public HippaSegmentWebServiceVO getEb07() {
		return eb07;
	}

	public void setEb07(HippaSegmentWebServiceVO eb07) {
		this.eb07 = eb07;
	}

	public HippaSegmentWebServiceVO getEb08() {
		return eb08;
	}

	public void setEb08(HippaSegmentWebServiceVO eb08) {
		this.eb08 = eb08;
	}

	public HippaSegmentWebServiceVO getEb10() {
		return eb10;
	}

	public void setEb10(HippaSegmentWebServiceVO eb10) {
		this.eb10 = eb10;
	}

	public HippaSegmentWebServiceVO getEb11() {
		return eb11;
	}

	public void setEb11(HippaSegmentWebServiceVO eb11) {
		this.eb11 = eb11;
	}

	public HippaSegmentWebServiceVO getEb12() {
		return eb12;
	}

	public void setEb12(HippaSegmentWebServiceVO eb12) {
		this.eb12 = eb12;
	}

	public HippaSegmentWebServiceVO getBenefitEffectiveStartDate() {
		return benefitEffectiveStartDate;
	}

	public void setBenefitEffectiveStartDate(
			HippaSegmentWebServiceVO benefitEffectiveStartDate) {
		this.benefitEffectiveStartDate = benefitEffectiveStartDate;
	}

	public HippaSegmentWebServiceVO getBenefitEffectiveEndDate() {
		return benefitEffectiveEndDate;
	}

	public void setBenefitEffectiveEndDate(
			HippaSegmentWebServiceVO benefitEffectiveEndDate) {
		this.benefitEffectiveEndDate = benefitEffectiveEndDate;
	}

	public String getIiiMessage() {
		return iiiMessage;
	}

	public void setIiiMessage(String iiiMessage) {
		this.iiiMessage = iiiMessage;
	}

	public String getTierDescription() {
		return tierDescription;
	}

	public void setTierDescription(String tierDescription) {
		this.tierDescription = tierDescription;
	}

	public List<ErrorDetailWebServiceVO> getErrorCodesList() {
		return errorCodesList;
	}

	public void setErrorCodesList(List<ErrorDetailWebServiceVO> errorCodesList) {
		this.errorCodesList = errorCodesList;
	}
	
	

}
