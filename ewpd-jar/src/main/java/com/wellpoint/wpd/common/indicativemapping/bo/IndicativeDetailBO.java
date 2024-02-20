package com.wellpoint.wpd.common.indicativemapping.bo;

import java.util.Date;

public class IndicativeDetailBO {
  
	
	private int indicativeSeq;
	
	private String indicativeSegment;
	
	private String indicativeCode;
	
	private String indValue;
	
	private String dfltIndicativeIndicator;
	
	private Date lastChangeTimestamp;
	
	private String lastChangeUser;
	
	private String createdUser;
	
	private Date createdTimestamp;
	
	private String logicIndicator;
	
	private String indicativeRegion;
	
	private int fieldLength;
	
	private String indComments;
	
	private String indicativeCodeDescText;
	
	private String existsInItem;
	
	public String getIndicativeRegion() {
		return indicativeRegion;
	}

	public void setIndicativeRegion(String indicativeRegion) {
		this.indicativeRegion = indicativeRegion;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getIndComments() {
		return indComments;
	}

	public void setIndComments(String indComments) {
		this.indComments = indComments;
	}

	public String getIndValue() {
		return indValue;
	}

	public void setIndValue(String indValue) {
		this.indValue = indValue;
	}
	
	public String getIndicativeCode() {
		return indicativeCode;
	}

	public void setIndicativeCode(String indicativeCode) {
		this.indicativeCode = indicativeCode;
	}

	public int getIndicativeSeq() {
		return indicativeSeq;
	}

	public void setIndicativeSeq(int indicativeSeq) {
		this.indicativeSeq = indicativeSeq;
	}

	public String getIndicativeSegment() {
		return indicativeSegment;
	}

	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}

	public String getDfltIndicativeIndicator() {
		return dfltIndicativeIndicator;
	}

	public void setDfltIndicativeIndicator(String dfltIndicativeIndicator) {
		this.dfltIndicativeIndicator = dfltIndicativeIndicator;
	}

	public Date getLastChangeTimestamp() {
		return lastChangeTimestamp;
	}

	public void setLastChangeTimestamp(Date lastChangeTimestamp) {
		this.lastChangeTimestamp = lastChangeTimestamp;
	}

	public String getLastChangeUser() {
		return lastChangeUser;
	}

	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getLogicIndicator() {
		return logicIndicator;
	}

	public void setLogicIndicator(String logicIndicator) {
		this.logicIndicator = logicIndicator;
	}

	public String getIndicativeCodeDescText() {
		return indicativeCodeDescText;
	}

	public void setIndicativeCodeDescText(String indicativeCodeDescText) {
		this.indicativeCodeDescText = indicativeCodeDescText;
	}

	public String getExistsInItem() {
		return existsInItem;
	}

	public void setExistsInItem(String existsInItem) {
		this.existsInItem = existsInItem;
	}

}
