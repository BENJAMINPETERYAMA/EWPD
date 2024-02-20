/*
 * <WPDValidationServiceImpl.java>
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
public class WPDValidationServiceImpl implements ValidationService {
	
	private ValidationRepository wpdValidationRepository;
	
	/**
	 * Method returns the List of valid values for category code - EB03 combination
	 * @param mapping
	 * @return List
	 */
	public List getServiceTypeCode(Mapping mapping) {
		
		List eb03CategoryCodeList = wpdValidationRepository.getServiceTypeCode(mapping);		
		
		return eb03CategoryCodeList;
	}

	public ValidationRepository getWpdValidationRepository() {
		return wpdValidationRepository;
	}

	public void setWpdValidationRepository(
			ValidationRepository wpdValidationRepository) {
		this.wpdValidationRepository = wpdValidationRepository;
	}

}
