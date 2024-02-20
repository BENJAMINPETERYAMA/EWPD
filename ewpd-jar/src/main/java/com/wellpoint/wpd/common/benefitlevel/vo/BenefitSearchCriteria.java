/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.vo;

import java.util.List;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitSearchCriteria {
	
	private int benefitDefinitionId;
	
	private List benefitLevelsList;
	/**
	 * @return Returns the benefitLevelsList.
	 */
	public List getBenefitLevelsList() {
		return benefitLevelsList;
	}
	/**
	 * @param benefitLevelsList The benefitLevelsList to set.
	 */
	public void setBenefitLevelsList(List benefitLevelsList) {
		this.benefitLevelsList = benefitLevelsList;
	}
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
