/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLocateCriteria extends LocateCriteria{
	
	private int benefitDefinitionId;
	
	private String selectedBenefitTerm;

	/**
	 * @return Returns the selectedBenefitTerm.
	 */
	public String getSelectedBenefitTerm() {
		return selectedBenefitTerm;
	}
	/**
	 * @param selectedBenefitTerm The selectedBenefitTerm to set.
	 */
	public void setSelectedBenefitTerm(String selectedBenefitTerm) {
		this.selectedBenefitTerm = selectedBenefitTerm;
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
