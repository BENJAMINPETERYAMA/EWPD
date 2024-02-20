/*
 * RuleClaimSequenceBO.java
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
public class RuleClaimSequenceBO extends BusinessObject {
	
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
	private String clmProvSpecCdInd;
	
	private Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap;
	
	
	public String getClmProvSpecCdInd() {
		return clmProvSpecCdInd;
	}

	public void setClmProvSpecCdInd(String clmProvSpecCdInd) {
		this.clmProvSpecCdInd = clmProvSpecCdInd;
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

	public Map<Integer, RuleClaimCodeSequenceBO> getRuleClaimCodeSequenceMap() {
		return ruleClaimCodeSequenceMap;
	}

	public void setRuleClaimCodeSequenceMap(
			Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap) {
		this.ruleClaimCodeSequenceMap = ruleClaimCodeSequenceMap;
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
	public RuleClaimSequenceBO(){
		this.ruleClaimCodeSequenceMap = 
			new HashMap<Integer, RuleClaimCodeSequenceBO>();
		
	}
}
