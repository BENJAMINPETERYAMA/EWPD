/*
 * RuleCodeSequenceBO.java
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
public class RuleCodeSequenceBO extends BusinessObject {
	
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
	
	private List<RuleCodeRanges> codeList;
		
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

	public List<RuleCodeRanges> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<RuleCodeRanges> codeList) {
		this.codeList = codeList;
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

}
