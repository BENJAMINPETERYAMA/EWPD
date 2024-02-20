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
public interface MandateBO {

	/**
	 * @return Returns the jurisdictionCode.
	 */
	public String getJurisdictionCode();
	/**
	 * @param jurisdictionCode The jurisdictionCode to set.
	 */
	public void setJurisdictionCode(String jurisdictionCode);
	/**
	 * @return Returns the jurisdictionName.
	 */
	public String getJurisdictionName();
	/**
	 * @param jurisdictionName The jurisdictionName to set.
	 */
	public void setJurisdictionName(String jurisdictionName);
	
	public List getBeList();
	/**
	 * @param beList The beList to set.
	 */
	public void setBeList(List beList);
	/**
	 * @return Returns the bgList.
	 */
	public List getBgList();
	/**
	 * @param bgList The bgList to set.
	 */
	public void setBgList(List bgList);
	/**
	 * @return Returns the citationNumberList.
	 */
	public List getCitationNumberList();
	/**
	 * @param citationNumberList The citationNumberList to set.
	 */
	public void setCitationNumberList(List citationNumberList);
	/**
	 * @return Returns the description.
	 */
	public String getDescription();
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description);
	/**
	 * @return Returns the fundingArrangement.
	 */
	public String getFundingArrangement();
	/**
	 * @param fundingArrangement The fundingArrangement to set.
	 */
	public void setFundingArrangement(String fundingArrangement);
	/**
	 * @return Returns the jurisdictionList.
	 */
	public List getJurisdictionList();
	/**
	 * @param jurisdictionList The jurisdictionList to set.
	 */
	public void setJurisdictionList(List jurisdictionList);
	/**
	 * @return Returns the lobList.
	 */
	public List getLobList();
	/**
	 * @param lobList The lobList to set.
	 */
	public void setLobList(List lobList);
	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType();
	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType);
}
