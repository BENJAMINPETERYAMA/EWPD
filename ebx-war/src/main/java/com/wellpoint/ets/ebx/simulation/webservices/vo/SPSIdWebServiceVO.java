package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.List;

public class SPSIdWebServiceVO {
	private String spsId;
	
	private String spsDesc;
	
	private String levelDesc;
	
	private String spsType;
	
	private String linePVA;
	
	private String lineDataType;
	
	private String lineValue;
	
	private List<String> spsDetail;

	
	
	public String getLinePVA() {
		return linePVA;
	}

	public void setLinePVA(String linePVA) {
		this.linePVA = linePVA;
	}

	public String getLineDataType() {
		return lineDataType;
	}

	public void setLineDataType(String lineDataType) {
		this.lineDataType = lineDataType;
	}

	public String getLineValue() {
		return lineValue;
	}

	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	public List<String> getSpsDetail() {
		return spsDetail;
	}

	public void setSpsDetail(List<String> spsDetail) {
		this.spsDetail = spsDetail;
	}

	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}

	public String getSpsDesc() {
		return spsDesc;
	}

	public void setSpsDesc(String spsDesc) {
		this.spsDesc = spsDesc;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	public String getSpsType() {
		return spsType;
	}

	public void setSpsType(String spsType) {
		this.spsType = spsType;
	}
	
	
}
