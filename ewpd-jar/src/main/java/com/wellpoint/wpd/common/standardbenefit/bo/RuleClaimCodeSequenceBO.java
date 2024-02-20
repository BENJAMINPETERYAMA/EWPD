/*
 * RuleClaimCodeSequenceBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.contract.locateresult.RuleCodeRanges;
import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleClaimCodeSequenceBO extends BusinessObject {
	
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
	
	private List<RuleCodeRanges> claimCodeList;
		
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

	public void setClmIcdLowVal(String clmIcdLowVal) {
		this.clmIcdLowVal = clmIcdLowVal;
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

	public String getClmDiagVrsnInd() {
		return clmDiagVrsnInd;
	}

	public void setClmDiagVrsnInd(String clmDiagVrsnInd) {
		this.clmDiagVrsnInd = clmDiagVrsnInd;
	}

	public List<RuleCodeRanges> getClaimCodeList() {
		return claimCodeList;
	}

	public void setClaimCodeList(List<RuleCodeRanges> claimCodeList) {
		this.claimCodeList = claimCodeList;
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

}
