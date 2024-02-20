/*
 * <MajorHeadingsVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.vo;

import java.util.Map;

/**
 * @author UST-GLOBAL
 * 
 * Value Object Class for storing MajorHeading/Benefit Components
 * details.
 * 
 */
public class MajorHeadingsVO {
	private Map minorHeadings;
	private String descriptionText;
	
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	public Map getMinorHeadings() {
		return minorHeadings;
	}
	public void setMinorHeadings(Map minorHeadings) {
		this.minorHeadings = minorHeadings;
	}
	
	
}
