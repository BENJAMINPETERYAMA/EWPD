/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MembershipInformation implements Serializable{
	
	private String caseNumberName;
	
	private Date caseeffectiveCancelDate;
	
	private String caseHQState;
	
	private String groupNumberName;
	
	private Date groupeffectiveCancelDate;
	
	private String fundingArrangement;
	
	private String mbuCodeValue;
	
	private String rerateCodeValue;
	
	/**
	 * @return Returns the caseeffectiveCancelDate.
	 */
	public Date getCaseeffectiveCancelDate() {
		return caseeffectiveCancelDate;
	}
	/**
	 * @param caseeffectiveCancelDate The caseeffectiveCancelDate to set.
	 */
	public void setCaseeffectiveCancelDate(Date caseeffectiveCancelDate) {
		this.caseeffectiveCancelDate = caseeffectiveCancelDate;
	}
	/**
	 * @return Returns the caseHQState.
	 */
	public String getCaseHQState() {
		return caseHQState;
	}
	/**
	 * @param caseHQState The caseHQState to set.
	 */
	public void setCaseHQState(String caseHQState) {
		this.caseHQState = caseHQState;
	}
	/**
	 * @return Returns the caseNumberName.
	 */
	public String getCaseNumberName() {
		return caseNumberName;
	}
	/**
	 * @param caseNumberName The caseNumberName to set.
	 */
	public void setCaseNumberName(String caseNumberName) {
		this.caseNumberName = caseNumberName;
	}
	/**
	 * @return Returns the fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	/**
	 * @param fundingArrangement The fundingArrangement to set.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	/**
	 * @return Returns the groupeffectiveCancelDate.
	 */
	public Date getGroupeffectiveCancelDate() {
		return groupeffectiveCancelDate;
	}
	/**
	 * @param groupeffectiveCancelDate The groupeffectiveCancelDate to set.
	 */
	public void setGroupeffectiveCancelDate(Date groupeffectiveCancelDate) {
		this.groupeffectiveCancelDate = groupeffectiveCancelDate;
	}
	/**
	 * @return Returns the groupNumberName.
	 */
	public String getGroupNumberName() {
		return groupNumberName;
	}
	/**
	 * @param groupNumberName The groupNumberName to set.
	 */
	public void setGroupNumberName(String groupNumberName) {
		this.groupNumberName = groupNumberName;
	}
	/**
	 * @return Returns the mbuCodeValue.
	 */
	public String getMbuCodeValue() {
		return mbuCodeValue;
	}
	/**
	 * @param mbuCodeValue The mbuCodeValue to set.
	 */
	public void setMbuCodeValue(String mbuCodeValue) {
		this.mbuCodeValue = mbuCodeValue;
	}
	/**
	 * @return Returns the rerateCodeValue.
	 */
	public String getRerateCodeValue() {
		return rerateCodeValue;
	}
	/**
	 * @param rerateCodeValue The rerateCodeValue to set.
	 */
	public void setRerateCodeValue(String rerateCodeValue) {
		this.rerateCodeValue = rerateCodeValue;
	}
}
