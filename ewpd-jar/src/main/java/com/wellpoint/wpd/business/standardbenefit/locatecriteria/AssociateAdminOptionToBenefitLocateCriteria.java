/*
 * AssociateAdminOptionToBenefitLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AssociateAdminOptionToBenefitLocateCriteria extends LocateCriteria {

	private AdministrationOptionBO administrationOptionBO = null;
	
	private BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = null;
	
	/**
	 * @return Returns the administrationOptionBO.
	 */
	public AdministrationOptionBO getAdministrationOptionBO() {
		return administrationOptionBO;
	}
	/**
	 * @param administrationOptionBO The administrationOptionBO to set.
	 */
	public void setAdministrationOptionBO(
			AdministrationOptionBO administrationOptionBO) {
		this.administrationOptionBO = administrationOptionBO;
	}
	/**
	 * @return Returns the benefitQuestionnaireAssnBO.
	 */
	public BenefitQuestionnaireAssnBO getBenefitQuestionnaireAssnBO() {
		return benefitQuestionnaireAssnBO;
	}
	/**
	 * @param benefitQuestionnaireAssnBO The benefitQuestionnaireAssnBO to set.
	 */
	public void setBenefitQuestionnaireAssnBO(
			BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO) {
		this.benefitQuestionnaireAssnBO = benefitQuestionnaireAssnBO;
	}
}
