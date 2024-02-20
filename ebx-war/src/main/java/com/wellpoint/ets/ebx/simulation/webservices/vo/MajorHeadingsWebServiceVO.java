package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.Map;

public class MajorHeadingsWebServiceVO {
	private Map<String, MinorHeadingsWebServiceVO> minorHeadings;
	private String descriptionText;

	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public Map<String, MinorHeadingsWebServiceVO> getMinorHeadings() {
		return minorHeadings;
	}

	public void setMinorHeadings(Map<String, MinorHeadingsWebServiceVO> minorHeadings) {
		this.minorHeadings = minorHeadings;
	}
}
