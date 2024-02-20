/*
 * Created on Feb 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.List;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MandateBOImpl implements MandateBO {
	private List lobList;
	private List beList;
	private List bgList;
	private String mandateType;
	private List jurisdictionList;
	private String fundingArrangement;
	private String description;
	private List citationNumberList;
	private String jurisdictionCode;
	private String jurisdictionName;
	
	
	/**
	 * @return Returns the jurisdictionCode.
	 */
	public String getJurisdictionCode() {
		return jurisdictionCode;
	}
	/**
	 * @param jurisdictionCode The jurisdictionCode to set.
	 */
	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}
	/**
	 * @return Returns the jurisdictionName.
	 */
	public String getJurisdictionName() {
		return jurisdictionName;
	}
	/**
	 * @param jurisdictionName The jurisdictionName to set.
	 */
	public void setJurisdictionName(String jurisdictionName) {
		this.jurisdictionName = jurisdictionName;
	}
	/**
	 * @return Returns the beList.
	 */
	public List getBeList() {
		return beList;
	}
	/**
	 * @param beList The beList to set.
	 */
	public void setBeList(List beList) {
		this.beList = beList;
	}
	/**
	 * @return Returns the bgList.
	 */
	public List getBgList() {
		return bgList;
	}
	/**
	 * @param bgList The bgList to set.
	 */
	public void setBgList(List bgList) {
		this.bgList = bgList;
	}
	/**
	 * @return Returns the citationNumberList.
	 */
	public List getCitationNumberList() {
		return citationNumberList;
	}
	/**
	 * @param citationNumberList The citationNumberList to set.
	 */
	public void setCitationNumberList(List citationNumberList) {
		this.citationNumberList = citationNumberList;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	/**
	 * @param fundingArrangement The fundingArrangement to set.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	/**
	 * @return Returns the jurisdictionList.
	 */
	public List getJurisdictionList() {
		return jurisdictionList;
	}
	/**
	 * @param jurisdictionList The jurisdictionList to set.
	 */
	public void setJurisdictionList(List jurisdictionList) {
		this.jurisdictionList = jurisdictionList;
	}
	/**
	 * @return Returns the lobList.
	 */
	public List getLobList() {
		return lobList;
	}
	/**
	 * @param lobList The lobList to set.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}
	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
}
