/*
 * <SPSId.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.List;

/**
 * @author UST-GLOBAL
 * This is an entity class to represent a SPS id i.e a id and description
 */
public class SPSId {
	
	private String spsId;
	
	private String spsDesc;
	
	private String levelDesc;
	
	private String spsType;
	
	private String linePVA;
	
	private String lineDataType;
	
	private String lineValue;
	
	private List spsDetail;

	/**
	 * @return
	 */
	public String getLineDataType() {
		return lineDataType;
	}

	/**
	 * @param lineDataType
	 */
	public void setLineDataType(String spsDataType) {
		this.lineDataType = spsDataType;
	}

	/**
	 * @return
	 */
	public String getSpsDesc() {
		return spsDesc;
	}

	/**
	 * @param spsDesc
	 */
	public void setSpsDesc(String spsDesc) {
		this.spsDesc = spsDesc;
	}

	/**
	 * @return
	 */
	public String getSpsId() {
		return spsId;
	}

	/**
	 * @param spsId
	 */
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}

	/**
	 * @return
	 */
	public String getLinePVA() {
		return linePVA;
	}

	/**
	 * @param linePVA
	 */
	public void setLinePVA(String spsPVA) {
		this.linePVA = spsPVA;
	}

	/**
	 * @return
	 */
	public String getSpsType() {
		return spsType;
	}

	/**
	 * @param spsType
	 */
	public void setSpsType(String spsType) {
		this.spsType = spsType;
	}

	public String getLineValue() {
		return lineValue;
	}
 
	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public List getSpsDetail() {
		return spsDetail;
	}

	public void setSpsDetail(List spsDetail) {
		this.spsDetail = spsDetail;
	}
	
}
