package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * @author UST-GLOBAL
 * This is an POJO class for search result.
 * 
 */
public class ReportWrapper {
	
	public List getVariableDetailList() {
		return variableDetailList;
	}

	public void setVariableDetailList(List variableDetailList) {
		this.variableDetailList = variableDetailList;
	}

	public Map getVariableHeaderList() {
		return variableHeaderList;
	}

	public void setVariableHeaderList(Map variableHeaderList) {
		this.variableHeaderList = variableHeaderList;
	}

	/**
	 * Wrapper class for Advance search report
	 */
	private List variableDetailList;
	
	private Map variableHeaderList; 
}
