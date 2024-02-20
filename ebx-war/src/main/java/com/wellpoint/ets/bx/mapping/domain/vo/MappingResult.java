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
import java.util.Iterator;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
/**
 * @author UST-GLOBAL
 * This is a VO class to represent a mapping result
 */
public class MappingResult {

	private boolean status;
	
	private Mapping mapping;
	
	private List errorMsgsList = new ArrayList();
	
	private List warningMsgsList = new ArrayList();
	
	private String previousVariableMappingStatus;
	
	

	public MappingResult() {

	}
	
	public MappingResult(List hippaSegmentValidationResultList) {
	
			if (null != hippaSegmentValidationResultList && !hippaSegmentValidationResultList.isEmpty()
					&& hippaSegmentValidationResultList.size() > 0) {
				Iterator HSValdtnRsltIterator = hippaSegmentValidationResultList
						.iterator();
				HippaSegmentValidationResult validationResult = new HippaSegmentValidationResult();				
				
				while (HSValdtnRsltIterator.hasNext()) {
					validationResult = (HippaSegmentValidationResult) HSValdtnRsltIterator
							.next();					
					if(null != validationResult.getFailureMessages() && !validationResult.getFailureMessages().isEmpty()){
						errorMsgsList.add(validationResult.getFailureMessages());
					}
					if(null != validationResult.getWarningMessages() && !validationResult.getWarningMessages().isEmpty()){
						warningMsgsList.add(validationResult.getWarningMessages());
					}					
				}
				this.mapping = validationResult.getMapping();
				if (null != errorMsgsList && errorMsgsList.size() > 0 && !errorMsgsList.isEmpty()) {
						status = false;
					} else {
						status = true;
					}
				}
		}	
	/**
	 * @return
	 */
	public List getErrorMsgsList() {
		return errorMsgsList;
	}

	/**
	 * @param errorMsgsList
	 */
	public void setErrorMsgsList(List errorMsgsList) {
		this.errorMsgsList = errorMsgsList;
	}

	/**
	 * @return
	 */
	public Mapping getMapping() {
		return mapping;
	}

	/**
	 * @param mapping
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return
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
	 * @return
	 */
	public List getWarningMsgsList() {
		return warningMsgsList;
	}

	/**
	 * @param warningMsgsList
	 */
	public void setWarningMsgsList(List warningMsgsList) {
		this.warningMsgsList = warningMsgsList;
	}
	public String getPreviousVariableMappingStatus() {
		return previousVariableMappingStatus;
	}

	public void setPreviousVariableMappingStatus(
			String previousVariableMappingStatus) {
		this.previousVariableMappingStatus = previousVariableMappingStatus;
	}
}
