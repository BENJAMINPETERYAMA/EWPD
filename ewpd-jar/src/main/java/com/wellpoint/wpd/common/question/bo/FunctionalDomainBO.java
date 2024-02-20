/*
 * Created on Mar 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.question.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FunctionalDomainBO extends BusinessObject {
	private int questionNumber;
	
	private String functionalDomainCD;
	
	private String functionalDomainDesc;
	
	private String secondaryCode;

	/**
	 * @return Returns the functionalDomainCD.
	 */
	public String getFunctionalDomainCD() {
		return functionalDomainCD;
	}
	/**
	 * @param functionalDomainCD The functionalDomainCD to set.
	 */
	public void setFunctionalDomainCD(String functionalDomainCD) {
		this.functionalDomainCD = functionalDomainCD;
	}
	/**
	 * @return Returns the functionalDomainDesc.
	 */
	public String getFunctionalDomainDesc() {
		return functionalDomainDesc;
	}
	/**
	 * @param functionalDomainDesc The functionalDomainDesc to set.
	 */
	public void setFunctionalDomainDesc(String functionalDomainDesc) {
		this.functionalDomainDesc = functionalDomainDesc;
	}
	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
        StringBuffer buffer = new StringBuffer();
        buffer.append("questionNumber = ").append(this.questionNumber);
         buffer.append(", functionalDomainDesc = ")
                .append(this.functionalDomainDesc);
         buffer.append(", functionalDomainCD = ")
         .append(this.functionalDomainCD);
        return buffer.toString();
	}

	public String getSecondaryCode() {
		return secondaryCode;
	}
	public void setSecondaryCode(String secondaryCode) {
		this.secondaryCode = secondaryCode;
	}
}
