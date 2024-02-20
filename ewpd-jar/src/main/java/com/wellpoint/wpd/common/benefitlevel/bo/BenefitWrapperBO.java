/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

import java.util.List;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitWrapperBO extends BusinessObject{

	private int benefitDefinitionId;
	
	private List benefitLevelsList;	

	private int masterVersion;
	
	private String benefitIdentifier;
	
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
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return Returns the masterVersion.
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	/**
	 * @param masterVersion The masterVersion to set.
	 */
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
