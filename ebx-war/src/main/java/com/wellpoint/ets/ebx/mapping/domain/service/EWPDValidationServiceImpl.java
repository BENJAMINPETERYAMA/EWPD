/*
 * <EWPDValidationServiceImpl.java>
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
import com.wellpoint.ets.ebx.mapping.repository.ValidationRepository;

/**
 * @author UST-GLOBAL This is an implementation class for performing the 
 * validation of category code , service type code
 */
public class EWPDValidationServiceImpl implements ValidationService {
	
	private ValidationRepository ewpdValidationRepository;
	/**
	 * Method returns the List of valid values for RULE -EB03
	 * @param mapping
	 * @return List
	 */
	public List getServiceTypeCode(Mapping mapping) {
		
		List ruleEb03List = ewpdValidationRepository.getServiceTypeCode(mapping);
		
		return ruleEb03List;
	}

	public ValidationRepository getEwpdValidationRepository() {
		return ewpdValidationRepository;
	}

	public void setEwpdValidationRepository(
			ValidationRepository ewpdValidationRepository) {
		this.ewpdValidationRepository = ewpdValidationRepository;
	}

}
