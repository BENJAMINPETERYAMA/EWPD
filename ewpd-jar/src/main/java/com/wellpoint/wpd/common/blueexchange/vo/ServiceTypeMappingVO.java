/*
 * ServiceTypeMappingVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingVO {
	
	private String eb03Identifier;
	
	private String headerRuleId;
	
	private List eb03codeList;
	
	private String blueExcahngeApplicable;
	
	private String sendDynamicInfo;
	
	
	/**
	 * @return Returns the eb03Identifier.
	 */
	public String getEb03Identifier() {
		return eb03Identifier;
	}
	/**
	 * @param eb03Identifier The eb03Identifier to set.
	 */
	public void setEb03Identifier(String eb03Identifier) {
		this.eb03Identifier = eb03Identifier;
	}
	
	
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	/**
	 * @return Returns the blueExcahngeApplicable.
	 */
	public String getBlueExcahngeApplicable() {
		return blueExcahngeApplicable;
	}
	/**
	 * @param blueExcahngeApplicable The blueExcahngeApplicable to set.
	 */
	public void setBlueExcahngeApplicable(String blueExcahngeApplicable) {
		this.blueExcahngeApplicable = blueExcahngeApplicable;
	}
	/**
	 * @return Returns the eb03codeList.
	 */
	public List getEb03codeList() {
		return eb03codeList;
	}
	/**
	 * @param eb03codeList The eb03codeList to set.
	 */
	public void setEb03codeList(List eb03codeList) {
		this.eb03codeList = eb03codeList;
	}
	/**
	 * @return Returns the sendDynamicInfo.
	 */
	public String getSendDynamicInfo() {
		return sendDynamicInfo;
	}
	/**
	 * @param sendDynamicInfo The sendDynamicInfo to set.
	 */
	public void setSendDynamicInfo(String sendDynamicInfo) {
		this.sendDynamicInfo = sendDynamicInfo;
	}
}
