/*
 * BenefitAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleTypeValidationAdapterManager {
	
	
	public List locateRules(List benefitIdList,String entityType,String searchQuery) 
	throws SevereException,AdapterException {
		
		SearchResults benefitRuleSearchResults = null;
		HashMap referenceValueSet = new HashMap();
		
		try{
			referenceValueSet.put("entityId",benefitIdList);
			referenceValueSet.put("entityType",entityType);
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(BusinessConstants.RULE_VALIDAT_BO,searchQuery,referenceValueSet);
			benefitRuleSearchResults = AdapterUtil.performSearch(searchCriteria);		
		}
		catch (Exception ex) {
			throw new AdapterException(
					"Exception occured in locate Benefit Rules in locateBenefitComponentRules method in RuleTypeValidationAdapterManager",
					ex);
		}
		return benefitRuleSearchResults.getSearchResults(); 
	}

}