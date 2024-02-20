package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

public class CreateOrEditMappingResult {

	private int status;

	private List statusCodes = new ArrayList();//Will set this if mapping already exists.

	private Mapping mapping;

	private List inValidHippaSegmentMappings; // This list is to detremine whether the createorEdit() is success or not.

	private List hippaSegmentValidationResultList;

	private List auditTrailList;

	private String userComments;
	
	private boolean autoPopulateSensitiveBenefitIndicator = false;

	private List errorMsgsList = new ArrayList();
	
	private List warningMsgsList = new ArrayList();
	
	private List errorMsgsWthPlaceHoldersList = new ArrayList();
	
	private String previousVariableMappingStatus;

	public String getPreviousVariableMappingStatus() {
		return previousVariableMappingStatus;
	}

	public void setPreviousVariableMappingStatus(
			String previousVariableMappingStatus) {
		this.previousVariableMappingStatus = previousVariableMappingStatus;
	}

	public CreateOrEditMappingResult(Mapping mapping) {
		this.mapping = mapping;
	}

	public CreateOrEditMappingResult() {

	}

	public CreateOrEditMappingResult(List hippaSegmentValidationResultList) {
		this.hippaSegmentValidationResultList = hippaSegmentValidationResultList;
		if (null != hippaSegmentValidationResultList && !hippaSegmentValidationResultList.isEmpty()
				&& hippaSegmentValidationResultList.size() > 0) {
			Iterator HSValdtnRsltIterator = hippaSegmentValidationResultList
					.iterator();
			HippaSegmentValidationResult validationResult = new HippaSegmentValidationResult();


			while (HSValdtnRsltIterator.hasNext()) {
				validationResult = (HippaSegmentValidationResult) HSValdtnRsltIterator
						.next();
				/*if(null != validationResult.getSucessMessages() && !validationResult.getSucessMessages().isEmpty()){
						errorMsgsList.add(validationResult.getSucessMessages());
					}*/
				if(null != validationResult.getFailureMessages() && !validationResult.getFailureMessages().isEmpty()){
					errorMsgsList.add(validationResult.getFailureMessages());
				}
				if(null != validationResult.getWarningMessages() && !validationResult.getWarningMessages().isEmpty()){
					warningMsgsList.add(validationResult.getWarningMessages());
				}
				//Removed as part of July release
				/*if(validationResult.isAccumNotRequired()){
					    autoPopulateSensitiveBenefitIndicator = true;
					}*/
			}
			this.mapping = validationResult.getMapping();
			if (null != errorMsgsList && errorMsgsList.size() > 0 && !errorMsgsList.isEmpty()) {
				setStatus(0);
			} else {
				setStatus(1);
			}
		}
		else {
			//No values in hippaSegmentValidationResultList means validation is success. 
			setStatus(1);
		}


	}

	public List getStatusCodes() {
		return statusCodes;
	}

	private void addStatusCode(String statusCode) {
		this.statusCodes.add(statusCode);

	}

	public void addStatusMappingExists() {
		addStatusCode(WebConstants.MAPPING_EXISTS);
	}

	public boolean isCreateOrEditSucceed() {
		//If the list inValidHippaSegmentMappings is Empty, then the CreateOrEdit is sucessful.
		if (null != inValidHippaSegmentMappings
				&& !inValidHippaSegmentMappings.isEmpty()) {
			status = 1;
		}
		if (status == 0) {
			return true;
		}
		return false;
	}

	public boolean isValidationSucess() {
		// TODO Auto-generated method stub
		if (status == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @return Returns the inValidHippaSegmentMappings.
	 */
	public List getInValidHippaSegmentMappings() {
		return inValidHippaSegmentMappings;
	}

	/**
	 * @param inValidHippaSegmentMappings The inValidHippaSegmentMappings to set.
	 */
	//    public void setInValidHippaSegmentMappings(List inValidHippaSegmentMappings) {
	//        this.inValidHippaSegmentMappings = inValidHippaSegmentMappings;
	//    }
	/**
	 * @return Returns the auditTrailList.
	 */
	public List getAuditTrailList() {
		return auditTrailList;
	}

	/**
	 * @param auditTrailList The auditTrailList to set.
	 */
	public void setAuditTrailList(List auditTrailList) {
		this.auditTrailList = auditTrailList;
	}

	/**
	 * @return Returns the hippaSegmentValidationResultList.
	 */
	public List getHippaSegmentValidationResultList() {
		return hippaSegmentValidationResultList;
	}

	/**
	 * @param hippaSegmentValidationResultList The hippaSegmentValidationResultList to set.
	 */
	public void setHippaSegmentValidationResultList(
			List hippaSegmentValidationResultList) {
		this.hippaSegmentValidationResultList = hippaSegmentValidationResultList;
	}

	/**
	 * @return Returns the mapping.
	 */
	public Mapping getMapping() {
		if(this.mapping == null){
			return new Mapping();
		}
		return mapping;
	}

	/**
	 * @param mapping The mapping to set.
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return Returns the status.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param inValidHippaSegmentMappings The inValidHippaSegmentMappings to set.
	 */
	public void setInValidHippaSegmentMappings(List inValidHippaSegmentMappings) {
		this.inValidHippaSegmentMappings = inValidHippaSegmentMappings;
	}

	/**
	 * @param statusCodes The statusCodes to set.
	 */
	public void setStatusCodes(List statusCodes) {
		this.statusCodes = statusCodes;
	}

	/**
	 * @return Returns the userComments.
	 */
	public String getUserComments() {
		return userComments;
	}

	/**
	 * @param userComments The userComments to set.
	 */
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	/**
	 * @return Returns the errorMsgsList.
	 */
	public List getErrorMsgsList() {
		return errorMsgsList;
	}
	/**
	 * @return Returns the errorMsgsWthPlaceHoldersList.
	 */
	public List getErrorMsgsWthPlaceHoldersList() {
		return errorMsgsWthPlaceHoldersList;
	}
	
	public boolean isMappingLockedByAnotherUser(){
		if(statusCodes == null)return false;
		for (Iterator iter = statusCodes.iterator(); iter.hasNext();) {
			String status = (String) iter.next();
			if(status.equals("MAPPING_LOCKED_ANOTHER_USER")){
				return true;
			}
		}
		return false;
	}
   
	/**
     * @return Returns the warningMsgsList.
     */
    public List getWarningMsgsList() {
        return warningMsgsList;
    }
   
    /**
     * @return Returns the autoPopulateSensitiveBenefitIndicator.
     */
    public boolean isAutoPopulateSensitiveBenefitIndicator() {
        return autoPopulateSensitiveBenefitIndicator;
    }
    /**
     * @param autoPopulateSensitiveBenefitIndicator The autoPopulateSensitiveBenefitIndicator to set.
     */
    public void setAutoPopulateSensitiveBenefitIndicator(
            boolean autoPopulateSensitiveBenefitIndicator) {
        this.autoPopulateSensitiveBenefitIndicator = autoPopulateSensitiveBenefitIndicator;
    }
}