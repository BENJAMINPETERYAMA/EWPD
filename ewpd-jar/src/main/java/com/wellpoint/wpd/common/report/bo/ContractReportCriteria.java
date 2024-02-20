/*
 * ContractReportCriteria.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.report.bo;

import java.util.List;

/**
 * @author UST Global - www.ust-global.com <br />
 * @version $Id: $
 */
public class ContractReportCriteria {
	private List contractId;
	private List benefitComponents;
	private List benefits;
	private String startDate;
	private boolean retrieveHeaderRule;
	private boolean retrieveBenefitLines;
	private boolean retrieveQuestions;
	private boolean retrieveAdminMethods;
	private boolean retrieveNotes;
	
	/**
	 * @return Returns the contractId.
	 */
	public List getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(List contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the benefitComponents.
	 */
	public List getBenefitComponents() {
		return benefitComponents;
	}
	/**
	 * @param benefitComponents The benefitComponents to set.
	 */
	public void setBenefitComponents(List benefitComponents) {
		this.benefitComponents = benefitComponents;
	}
	/**
	 * @return Returns the benefits.
	 */
	public List getBenefits() {
		return benefits;
	}
	/**
	 * @param benefits The benefits to set.
	 */
	public void setBenefits(List benefits) {
		this.benefits = benefits;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the retrieveHeaderRule.
	 */
	public boolean isRetrieveHeaderRule() {
		return retrieveHeaderRule;
	}
	/**
	 * @param retrieveHeaderRule The retrieveHeaderRule to set.
	 */
	public void setRetrieveHeaderRule(boolean retrieveHeaderRule) {
		this.retrieveHeaderRule = retrieveHeaderRule;
	}
	/**
	 * @return Returns the retrieveBenefitLines.
	 */
	public boolean isRetrieveBenefitLines() {
		return retrieveBenefitLines;
	}
	/**
	 * @param retrieveBenefitLines The retrieveBenefitLines to set.
	 */
	public void setRetrieveBenefitLines(boolean retrieveBenefitLines) {
		this.retrieveBenefitLines = retrieveBenefitLines;
	}
	/**
	 * @return Returns the retrieveQuestions.
	 */
	public boolean isRetrieveQuestions() {
		return retrieveQuestions;
	}
	/**
	 * @param retrieveQuestions The retrieveQuestions to set.
	 */
	public void setRetrieveQuestions(boolean retrieveQuestions) {
		this.retrieveQuestions = retrieveQuestions;
	}
	/**
	 * @return Returns the retrieveAdminMethods.
	 */
	public boolean isRetrieveAdminMethods() {
		return retrieveAdminMethods;
	}
	/**
	 * @param retrieveAdminMethods The retrieveAdminMethods to set.
	 */
	public void setRetrieveAdminMethods(boolean retrieveAdminMethods) {
		this.retrieveAdminMethods = retrieveAdminMethods;
	}
	/**
	 * @return Returns the retrieveNotes.
	 */
	public boolean isRetrieveNotes() {
		return retrieveNotes;
	}
	/**
	 * @param retrieveNotes The retrieveNotes to set.
	 */
	public void setRetrieveNotes(boolean retrieveNotes) {
		this.retrieveNotes = retrieveNotes;
	}
}
