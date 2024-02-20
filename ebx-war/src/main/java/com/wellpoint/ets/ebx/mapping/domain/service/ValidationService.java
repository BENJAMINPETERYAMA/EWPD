/*
 * <ValidationService.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL This is an interface class for performing the 
 * validation on the Rule , service type code validation and the
 * category code , service type code validation
 */
public interface ValidationService {
	
	/**
	 * Method returns the List of valid values for RULE -EB03
	 * and category code - EB03 combination
	 * @param mapping
	 * @return List
	 */
	public List getServiceTypeCode(Mapping mapping) ;
}
