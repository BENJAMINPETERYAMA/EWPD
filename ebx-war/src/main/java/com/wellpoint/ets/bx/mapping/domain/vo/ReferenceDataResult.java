/*
 * <MappingResult.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL This is a VO class to represent a Reference Data Result
 */
public class ReferenceDataResult {

	private boolean status;

	private Mapping mapping;

	private List errorMsgsList = new ArrayList();

	private List warningMsgsList = new ArrayList();

	public ReferenceDataResult() {

	}

	/**
	 * @return List of error messages
	 */
	public List getErrorMsgsList() {
		return errorMsgsList;
	}

	/**
	 * @param errorMsgsList - List of error messages
	 */
	public void setErrorMsgsList(List errorMsgsList) {
		this.errorMsgsList = errorMsgsList;
	}

	/**
	 * @return - Mapping
	 */
	public Mapping getMapping() {
		return mapping;
	}

	/**
	 * @param mapping - Mapping Object
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return - True when reference data status is true
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return - List of Warning Message.
	 */
	public List getWarningMsgsList() {
		return warningMsgsList;
	}

	/**
	 * @param warningMsgsList - List of Warning Message.
	 */
	public void setWarningMsgsList(List warningMsgsList) {
		this.warningMsgsList = warningMsgsList;
	}

}
