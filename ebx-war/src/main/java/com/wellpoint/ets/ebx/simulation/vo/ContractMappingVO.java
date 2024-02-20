/*
 * <ContractMappingVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.vo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL
 * 
 * Value Object Class for storing  mapping details and error details.
 * This class extends Mapping class
 * 
 */
public class ContractMappingVO extends Mapping{


	private HippaSegment eb04;
	private HippaSegment eb05;
	private HippaSegment eb07;
	private HippaSegment eb08;
	private HippaSegment eb10;
	private HippaSegment eb11;
	private HippaSegment eb12;
	private HippaSegment benefitEffectiveStartDate;
	private HippaSegment benefitEffectiveEndDate;
	private String iiiMessage;
	
	 
	/**
     * Variable to hold the Tier description-July Release
     */
    private String tierDescription;

	private List errorCodesList = new ArrayList();
	
	/**
	 * @return the eb04
	 */
	public HippaSegment getEb04() {
		return eb04;
	}
	/**
	 * @param eb04 the eb04 to set
	 */
	public void setEb04(HippaSegment eb04) {
		this.eb04 = eb04;
	}
	/**
	 * @return the eb05
	 */
	public HippaSegment getEb05() {
		return eb05;
	}
	/**
	 * @param eb05 the eb05 to set
	 */
	public void setEb05(HippaSegment eb05) {
		this.eb05 = eb05;
	}
	/**
	 * @return the eb07
	 */
	public HippaSegment getEb07() {
		return eb07;
	}
	/**
	 * @param eb07 the eb07 to set
	 */
	public void setEb07(HippaSegment eb07) {
		this.eb07 = eb07;
	}
	/**
	 * @return the eb08
	 */
	public HippaSegment getEb08() {
		return eb08;
	}
	/**
	 * @param eb08 the eb08 to set
	 */
	public void setEb08(HippaSegment eb08) {
		this.eb08 = eb08;
	}
	/**
	 * @return the eb10
	 */
	public HippaSegment getEb10() {
		return eb10;
	}
	/**
	 * @param eb10 the eb10 to set
	 */
	public void setEb10(HippaSegment eb10) {
		this.eb10 = eb10;
	}
	/**
	 * @return the eb11
	 */
	public HippaSegment getEb11() {
		return eb11;
	}
	/**
	 * @param eb11 the eb11 to set
	 */
	public void setEb11(HippaSegment eb11) {
		this.eb11 = eb11;
	}
	/**
	 * @return the eb12
	 */
	public HippaSegment getEb12() {
		return eb12;
	}
	/**
	 * @param eb12 the eb12 to set
	 */
	public void setEb12(HippaSegment eb12) {
		this.eb12 = eb12;
	}

	public List getErrorCodesList() {
		return errorCodesList;
	}

	public void setErrorCodesList(List errorCodesList) {
		this.errorCodesList = errorCodesList;
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
	public HippaSegment getBenefitEffectiveStartDate() {
		return benefitEffectiveStartDate;
	}
	public void setBenefitEffectiveStartDate(HippaSegment benefitEffectiveStartDate) {
		this.benefitEffectiveStartDate = benefitEffectiveStartDate;
	}
	public HippaSegment getBenefitEffectiveEndDate() {
		return benefitEffectiveEndDate;
	}
	public void setBenefitEffectiveEndDate(HippaSegment benefitEffectiveEndDate) {
		this.benefitEffectiveEndDate = benefitEffectiveEndDate;
	}
	
	public String getIiiMessage() {
		return iiiMessage;
	}
	public void setIiiMessage(String iiiMessage) {
		this.iiiMessage = iiiMessage;
	}
}
