/*
 * <EB03Association.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author u23708
 *
 */
public class EB03Association implements Comparable<Object> {

	
	/** Attribute to hold the eb03 value and description */
	HippaCodeValue eb03;
	
	/** Attribute to hold the Note Type value and description */
	HippaCodeValue noteType;
	
	/** List to hold the III02 values associated to single EB03 */
	private List <HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>();
	
	/** Message Text against a single EB03*/
	String message;
	
	/** Message Required indicator against the message text-EB03*/
	String msgReqdInd;
	
	String eb03String;
	Long seq_num;
	
	private String extndMsg1;
	private String extndMsg2;
	private String extndMsg3;	
	private String networkInd;
	private String highPrfrmnTierdMsg;
	private String highPrfrmnNonTierdMsg;
	
    /** 
     * VAR_MAPG_VAL_SYS_ID in BX_CNTRCT_VAR_MAPG_VAL/TEMP_BX_CNTRCT_VAR_MAPG_VAL associated with Message - 
     * This value is used particularly for production flow of variable datafeed 
     */
	Long mesgValueSysId;
	
	 /** 
     * VAR_MAPG_VAL_SYS_ID in BX_CNTRCT_VAR_MAPG_VAL/TEMP_BX_CNTRCT_VAR_MAPG_VAL associated with Message Required Indicator - 
     * This value is used particularly for production flow of variable datafeed 
     */
	Long mesgReqIndValueSysId;
	
	
	
	/**
	 * @return the eb03
	 */
	public HippaCodeValue getEb03() {
		return eb03;
	}



	/**
	 * @param eb03 the eb03 to set
	 */
	public void setEb03(HippaCodeValue eb03) {
		this.eb03 = eb03;
	}



	/**
	 * @return the noteType
	 */
	public HippaCodeValue getNoteType() {
		return noteType;
	}



	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(HippaCodeValue noteType) {
		this.noteType = noteType;
	}



	/**
	 * @return the iii02List
	 */
	public List<HippaCodeValue> getIii02List() {
		return iii02List;
	}



	/**
	 * @param iii02List the iii02List to set
	 */
	public void setIii02List(List<HippaCodeValue> iii02List) {
		this.iii02List = iii02List;
	}



	/**
	 * @return the eb03String
	 */
	public String getEb03String() {
		return eb03String;
	}



	/**
	 * @param eb03String the eb03String to set
	 */
	public void setEb03String(String eb03String) {
		this.eb03String = eb03String;
	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



	/**
	 * @return the msgReqdInd
	 */
	public String getMsgReqdInd() {
		return msgReqdInd;
	}



	/**
	 * @param msgReqdInd the msgReqdInd to set
	 */
	public void setMsgReqdInd(String msgReqdInd) {
		this.msgReqdInd = msgReqdInd;
	}



	/**
	 * @return the seq_num
	 */
	public Long getSeq_num() {
		return seq_num;
	}



	/**
	 * @param seq_num the seq_num to set
	 */
	public void setSeq_num(Long seq_num) {
		this.seq_num = seq_num;
	}



	@Override
	public int compareTo(Object obj1) {
		int compare = 0;
		if (obj1 instanceof EB03Association) {
			EB03Association eb03Association = (EB03Association) obj1;
			if (null != this.getSeq_num() && null != eb03Association
					&& null != eb03Association.getSeq_num()) {
				compare = this.getSeq_num().compareTo(
						eb03Association.getSeq_num());
			}
		}
		return compare;
	}
	
	public String commaSeparatedIII02String;

	/**
	 * @return the commaSeparatedIII02String
	 */
	public String getCommaSeparatedIII02String() {
		return commaSeparatedIII02String;
	}

	/**
	 * @param commaSeparatedIII02String the commaSeparatedIII02String to set
	 */
	public void setCommaSeparatedIII02String(String commaSeparatedIII02String) {
		this.commaSeparatedIII02String = commaSeparatedIII02String;
	}
	

	public String commaSeparatedIII02StringWithDesc;

	/**
	 * @return the commaSeparatedIII02String
	 */
	public String getCommaSeparatedIII02StringWithDesc() {
		return commaSeparatedIII02StringWithDesc;
	}

	/**
	 * @param commaSeparatedIII02String the commaSeparatedIII02String to set
	 */
	public void setCommaSeparatedIII02StringWithDesc(String commaSeparatedIII02StringWithDesc) {
		this.commaSeparatedIII02StringWithDesc = commaSeparatedIII02StringWithDesc;
	}



	public Long getMesgValueSysId() {
		return mesgValueSysId;
	}



	public void setMesgValueSysId(Long mesgValueSysId) {
		this.mesgValueSysId = mesgValueSysId;
	}



	public Long getMesgReqIndValueSysId() {
		return mesgReqIndValueSysId;
	}



	public void setMesgReqIndValueSysId(Long mesgReqIndValueSysId) {
		this.mesgReqIndValueSysId = mesgReqIndValueSysId;
	}



	public String getExtndMsg1() {
		return extndMsg1;
	}



	public void setExtndMsg1(String extndMsg1) {
		this.extndMsg1 = extndMsg1;
	}



	public String getExtndMsg2() {
		return extndMsg2;
	}



	public void setExtndMsg2(String extndMsg2) {
		this.extndMsg2 = extndMsg2;
	}



	public String getExtndMsg3() {
		return extndMsg3;
	}



	public void setExtndMsg3(String extndMsg3) {
		this.extndMsg3 = extndMsg3;
	}



	public String getNetworkInd() {
		return networkInd;
	}



	public void setNetworkInd(String networkInd) {
		this.networkInd = networkInd;
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
}
