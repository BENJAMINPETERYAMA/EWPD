/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author u13680
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelTermLocateCriteria extends LocateCriteria {
	
	private int benefitDefinitionId;
	
	/**
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}
	/**
	 * @param benefitDefinitionId The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}
}
