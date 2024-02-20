/*
 * Created on May 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteDomain {
	
	private List availableReferences;
	
	private List availableProdcuts;
	
	private List availableBenefits;
	
	private List availableBenefitComponents;
	private List availableQuestions;

	/**
	 * @return Returns the availableBenefitComponents.
	 */
	public List getAvailableBenefitComponents() {
		return availableBenefitComponents;
	}
	/**
	 * @param availableBenefitComponents The availableBenefitComponents to set.
	 */
	public void setAvailableBenefitComponents(List availableBenefitComponents) {
		this.availableBenefitComponents = availableBenefitComponents;
	}
	/**
	 * @return Returns the availableBenefits.
	 */
	public List getAvailableBenefits() {
		return availableBenefits;
	}
	/**
	 * @param availableBenefits The availableBenefits to set.
	 */
	public void setAvailableBenefits(List availableBenefits) {
		this.availableBenefits = availableBenefits;
	}
	/**
	 * @return Returns the availableProdcuts.
	 */
	public List getAvailableProdcuts() {
		return availableProdcuts;
	}
	/**
	 * @param availableProdcuts The availableProdcuts to set.
	 */
	public void setAvailableProdcuts(List availableProdcuts) {
		this.availableProdcuts = availableProdcuts;
	}
	/**
	 * @return Returns the availableReferences.
	 */
	public List getAvailableReferences() {
		return availableReferences;
	}
	/**
	 * @param availableReferences The availableReference to set.
	 */
	public void setAvailableReferences(List availableReferences) {
		this.availableReferences = availableReferences;
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

    /**
     * @return Returns the availableQuestions.
     */
    public List getAvailableQuestions() {
        return availableQuestions;
    }
    /**
     * @param availableQuestions The availableQuestions to set.
     */
    public void setAvailableQuestions(List availableQuestions) {
        this.availableQuestions = availableQuestions;
    }
}
