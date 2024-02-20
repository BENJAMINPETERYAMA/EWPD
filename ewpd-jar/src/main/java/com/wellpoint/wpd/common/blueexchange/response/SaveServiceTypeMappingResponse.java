/*
 * SaveServiceTypeMappingResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveServiceTypeMappingResponse extends WPDResponse{

	private ServiceTypeMapping serviceTypeMapping;
	private boolean success;
	private boolean valid;
	
	private RuleMapping ruleMapping;
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
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	/**
	 * @return Returns the ruleMapping.
	 */
	public RuleMapping getRuleMapping() {
		return ruleMapping;
	}
	/**
	 * @param ruleMapping The ruleMapping to set.
	 */
	public void setRuleMapping(RuleMapping ruleMapping) {
		this.ruleMapping = ruleMapping;
	}
}
