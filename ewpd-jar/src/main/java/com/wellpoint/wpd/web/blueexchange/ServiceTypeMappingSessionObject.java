/*
 * Created on May 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.blueexchange;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceTypeMappingSessionObject {
	
	private String headerRuleId;
	private String eb03Identifier;
	private String eb03Desc;
	private String placeOfService;
	private String placeOfServiceDesc;

	/**
	 * @return Returns the eb03Desc.
	 */
	public String getEb03Desc() {
		return eb03Desc;
	}
	/**
	 * @param eb03Desc The eb03Desc to set.
	 */
	public void setEb03Desc(String eb03Desc) {
		this.eb03Desc = eb03Desc;
	}
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
	 * @return Returns the placeOfService.
	 */
	public String getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the placeOfServiceDesc.
	 */
	public String getPlaceOfServiceDesc() {
		return placeOfServiceDesc;
	}
	/**
	 * @param placeOfServiceDesc The placeOfServiceDesc to set.
	 */
	public void setPlaceOfServiceDesc(String placeOfServiceDesc) {
		this.placeOfServiceDesc = placeOfServiceDesc;
	}
}
