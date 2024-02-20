/*
 * IndicativeMappingBackUpBO.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.indicativemapping.bo;

import java.util.Date;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * 
 * @author U42506
 * 
 */

public class IndicativeDetailBackUpBO extends BusinessObject {

	private int indicativeSegmentSeq;

	private String indicativeSegmentNumber;

	private String indicativeSegmentCode;

	private String indValue;

	private String defaultIndicativeValue;

	private String logicIndValue;

	private String indicativeRegion;

	private int indicativeSegLength;

	private String comment;

	private Date backUpTimestamp;

	private String backUpUser;

	public int getIndicativeSegmentSeq() {
		return indicativeSegmentSeq;
	}

	public void setIndicativeSegmentSeq(int indicativeSegmentSeq) {
		this.indicativeSegmentSeq = indicativeSegmentSeq;
	}

	public String getIndicativeSegmentNumber() {
		return indicativeSegmentNumber;
	}

	public void setIndicativeSegmentNumber(String indicativeSegmentNumber) {
		this.indicativeSegmentNumber = indicativeSegmentNumber;
	}

	public String getIndicativeSegmentCode() {
		return indicativeSegmentCode;
	}

	public void setIndicativeSegmentCode(String indicativeSegmentCode) {
		this.indicativeSegmentCode = indicativeSegmentCode;
	}

	public String getIndValue() {
		return indValue;
	}

	public void setIndValue(String indValue) {
		this.indValue = indValue;
	}

	public String getDefaultIndicativeValue() {
		return defaultIndicativeValue;
	}

	public void setDefaultIndicativeValue(String defaultIndicativeValue) {
		this.defaultIndicativeValue = defaultIndicativeValue;
	}

	public String getLogicIndValue() {
		return logicIndValue;
	}

	public void setLogicIndValue(String logicIndValue) {
		this.logicIndValue = logicIndValue;
	}

	public String getIndicativeRegion() {
		return indicativeRegion;
	}

	public void setIndicativeRegion(String indicativeRegion) {
		this.indicativeRegion = indicativeRegion;
	}

	public int getIndicativeSegLength() {
		return indicativeSegLength;
	}

	public void setIndicativeSegLength(int indicativeSegLength) {
		this.indicativeSegLength = indicativeSegLength;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getBackUpTimestamp() {
		return backUpTimestamp;
	}

	public void setBackUpTimestamp(Date backUpTimestamp) {
		this.backUpTimestamp = backUpTimestamp;
	}

	public String getBackUpUser() {
		return backUpUser;
	}

	public void setBackUpUser(String backUpUser) {
		this.backUpUser = backUpUser;
	}

	@Override
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
