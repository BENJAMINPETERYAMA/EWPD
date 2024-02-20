/*
 * RetrieveServiceTypeMappingResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.RuleServiceTypeAssociation;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveServiceTypeMappingResponse extends WPDResponse{
	private ServiceTypeMapping serviceTypeMapping;
	private RuleServiceTypeAssociation ruleServiceTypeAssociationBO;
	private RuleMapping ruleMappingBO;

	/**
	 * @return Returns the serviceTypeMapping.
	 */
	public ServiceTypeMapping getServiceTypeMapping() {
		return serviceTypeMapping;
	}
	/**
	 * @param serviceTypeMapping The serviceTypeMapping to set.
	 */
	public void setServiceTypeMapping(ServiceTypeMapping serviceTypeMapping) {
		this.serviceTypeMapping = serviceTypeMapping;
	}
	/**
	 * @return Returns the ruleServiceTypeAssociationBO.
	 */
	public RuleServiceTypeAssociation getRuleServiceTypeAssociationBO() {
		return ruleServiceTypeAssociationBO;
	}
	/**
	 * @param ruleServiceTypeAssociationBO The ruleServiceTypeAssociationBO to set.
	 */
	public void setRuleServiceTypeAssociationBO(
			RuleServiceTypeAssociation ruleServiceTypeAssociationBO) {
		this.ruleServiceTypeAssociationBO = ruleServiceTypeAssociationBO;
	}
	/**
	 * @return Returns the ruleMappingBO.
	 */
	public RuleMapping getRuleMappingBO() {
		return ruleMappingBO;
	}
	/**
	 * @param ruleMappingBO The ruleMappingBO to set.
	 */
	public void setRuleMappingBO(RuleMapping ruleMappingBO) {
		this.ruleMappingBO = ruleMappingBO;
	}
}
