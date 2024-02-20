/*
 * Created on Aug 11, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u20776
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierDefinitionRetrieveResponse extends WPDResponse{

	private List benefitTierDefinitonsList;
	
	
	/**
	 * @return Returns the benefitTierDefinitonsList.
	 */
	public List getBenefitTierDefinitonsList() {
		return benefitTierDefinitonsList;
	}
	/**
	 * @param benefitTierDefinitonsList The benefitTierDefinitonsList to set.
	 */
	public void setBenefitTierDefinitonsList(List benefitTierDefinitonsList) {
		this.benefitTierDefinitonsList = benefitTierDefinitonsList;
	}
}
