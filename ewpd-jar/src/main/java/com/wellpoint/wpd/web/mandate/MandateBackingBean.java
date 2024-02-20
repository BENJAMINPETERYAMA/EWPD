/*
 * MandateBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.mandate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.business.mandate.locateResult.MandateLocateResult;
import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.mandate.request.CopyMandateRequest;
import com.wellpoint.wpd.common.mandate.request.CreateMandateRequest;
import com.wellpoint.wpd.common.mandate.request.MandateCheckInRequest;
import com.wellpoint.wpd.common.mandate.request.RetrieveMandateRequest;
import com.wellpoint.wpd.common.mandate.request.ViewAllMandateRequest;
import com.wellpoint.wpd.common.mandate.response.CopyMandateResponse;
import com.wellpoint.wpd.common.mandate.response.CreateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.MandateCheckInResponse;
import com.wellpoint.wpd.common.mandate.response.RetrieveMandateResponse;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing bean for Mandates.
 * 
 * This bean will bind with the jsp pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateBackingBean extends WPDBackingBean {

	//Id associated with the Mandate.
	private int mandateId = -1;
	
	//Citation number of the Mandate.
	private String citationNumber;
	
	//Version number of Mandate.
	private int version;
	
	//Variable used for view Mandate	
	private int viewMandateKey;
	
	//Effective date of the start of Mandate.
	private String effectiveDate;
	
	//Date of expeiry of Mandate
	private String expiryDate;
	
	//Type of Mandate.
	private String mandateType;
	
	//Id associated with the jurisdiction.
	private String jurisdictionId;
	
	//Description about the jurisdiction.
	private String jurisdictionDesc;
	
	//Jurisdiction of the Mandate.
	private String jurisdiction;
	
	//Size of group of the Mandate.
	private String groupSize;
	
	//Funding arrangements of the Mandate
	private String fundingArrangement;
	
	//Description about Mandate.
	private String description;
	
	//The Mandate name.
	private String mandateName;
	
	//Description about the funding arrangements.
	private String fundingArrangementDesc;
	
	//Description about the group size.			
	private String groupSizeDesc;
	
	//Description about the Mandate type.
	private String mandateTypeDesc;
	
	//User name of the Mandate created.
	private String createdUser;
	
	//Timestamp of the Mandate created.
	private String createdTimestamp;
	
	//User name of the last updated Mandate.
	private String lastUpdatedUser;
	
	//Timestamp of the last updated Mandate.
	private String lastUpdatedTimestamp;
	
	//Status of Mandate.
	private String status;
	
	//Effective date required by the Mandate for validation.
	private boolean requiredEffectiveDate;
	
	//Expiry date required by the Mandate for validation.
	private boolean requiredExpiryDate;
	
	//Mandate type required for validation.
	private boolean requiredMandateType;
	
	//The jurisdiction required as per validation
	private boolean requiredJurisdiction;
	
	//Size of the group required for validation.
	private boolean requiredGroupSize;
	
	//Funding arrangements to validate the requirement.
	private boolean requiredFundingArrangement;
	
	//Description required for Mandate as per the requirement.
	private boolean requiredDescription;
	
	//The requirement for citation number.
	private boolean requiredCitationNumber;
	
	//The requirement for the name of the Mandate.
	private boolean requiredMandateName;
	
	//List of citation numbers.
	private List citationNumberList;
	
	//List of jurisdiction.
	private List jurisdictionList;
	
	//Messages displayed after validating.
	ArrayList validationMessages = null;
	
	//To check the status of check-in
	private boolean checkIn;
	
	//To check the status of checkout.
	private boolean checkoutFlag = false;
	
	//List of messages for display.
	private List messages;
	
	//List for storing the results from the response.
	private List viewAllMandateList;
	
	//State of the Mandate.
	private String state;
	
	//Object associated with the state of the mandate.
	private State stateObj;
	
	//Status of the check-in.
	private boolean checkInFlag = false;
	
	//To display the current functionality of the Mandate in the page.
	private String breadCrumpCreate;
	

	/**
	 * Returns the checkInFlag.
	 * 
	 * @return checkInFlag
	 */
	public boolean isCheckInFlag() {
		return checkInFlag;
	}

	/**
	 * The checkInFlag to set.
	 * 
	 * @param checkInFlag
     */
	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}

	/**
	 * Returns the state
	 * 
	 * @return state.
	 */
	public String getState() {
		if (null != stateObj && !"".equals(stateObj.getState())) {
			stateObj.getState();
		}
		return state;
	}

	/**
	 * Sets the state
	 * 
	 * @param state.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the stateObj
	 * 
	 * @return stateObj.
	 */
	public State getStateObj() {
		return stateObj;
	}

	/**
	 * Sets the stateObj
	 * 
	 * @param stateObj.
	 */
	public void setStateObj(State stateObj) {
		this.stateObj = stateObj;
	}

	/**
	 * Returns the viewAllMandateList.
	 * 
	 * @return viewAllMandateList
	 */
	public List getViewAllMandateList() {
		return viewAllMandateList;
	}

	/**
	 * The viewAllMandateList to set.
	 * 
	 * @param viewAllMandateList
	 */
	public void setViewAllMandateList(List viewAllMandateList) {
		this.viewAllMandateList = viewAllMandateList;
	}
	

	/**
	 * Returns the citationNumber.
	 * 
	 * @return citationNumber
	 */
	public String getCitationNumber() {
		return citationNumber;
	}

	/**
	 * Sets the citationNumber.
	 * 
	 * @param citationNumber
	 */
	public void setCitationNumber(String citationNumber) {
		this.citationNumber = citationNumber;
	}

	/**
	 * Returns the citationNumberList.
	 * 
	 * @return citationNumberList
	 */
	public List getCitationNumberList() {
		return citationNumberList;
	}

	/**
	 * Sets the citationNumberList.
	 * 
	 * @param citationNumberList
	 */
	public void setCitationNumberList(List citationNumberList) {
		this.citationNumberList = citationNumberList;
	}

	/**
	 * Returns the createdTimestamp.
	 * 
	 * @return createdTimestamp
	 */
	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * Sets the createdTimestamp.
	 * 
	 * @param createdTimestamp
	 */
	public void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * Returns the createdUser.
	 * 
	 * @return createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * Sets the createdUser.
	 * 
	 * @param createdUser
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the effectiveDate.
	 * 
	 * @return effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effectiveDate.
	 * 
	 * @param effectiveDate
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the expiryDate.
	 * 
	 * @return expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiryDate.
	 * 
	 * @param expiryDate
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Returns the fundingArrangement.
	 * 
	 * @return fundingArrangement
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}

	/**
	 * Sets the fundingArrangement.
	 * 
	 * @param fundingArrangement
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}

	/**
	 * Returns the groupSize.
	 * 
	 * @return groupSize
	 */
	public String getGroupSize() {
		return groupSize;
	}

	/**
	 * Sets the groupSize.
	 * 
	 * @param groupSize
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	/**
	 * Returns the jurisdiction.
	 * 
	 * @return jurisdiction
	 */
	public String getJurisdiction() {
		return jurisdiction;
	}

	/**
	 * Sets the jurisdiction.
	 * 
	 * @param jurisdiction
	 */
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * Returns the jurisdictionDesc.
	 * 
	 * @return jurisdictionDesc
	 */
	public String getJurisdictionDesc() {
		return jurisdictionDesc;
	}

	/**
	 * The jurisdictionDesc to set.
	 * 
	 * @param jurisdictionDesc
	 */
	public void setJurisdictionDesc(String jurisdictionDesc) {
		this.jurisdictionDesc = jurisdictionDesc;
	}

	/**
	 * Returns the jurisdictionId.
	 * 
	 * @return jurisdictionId
	 */
	public String getJurisdictionId() {
		return jurisdictionId;
	}

	/**
	 * The jurisdictionId to set.
	 * 
	 * @param jurisdictionId
	 */
	public void setJurisdictionId(String jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}

	/**
	 * Returns the jurisdictionList.
	 * 
	 * @return jurisdictionList
	 */
	public List getJurisdictionList() {
		return jurisdictionList;
	}

	/**
	 * The jurisdictionList to set.
	 * 
	 * @param jurisdictionList
	 */         
	public void setJurisdictionList(List jurisdictionList) {
		this.jurisdictionList = jurisdictionList;
	}

	/**
	 * Returns the lastUpdatedTimestamp.
	 * 
	 * @return lastUpdatedTimestamp
	 */
	public String getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	/**
	 * The lastUpdatedTimestamp to set.
	 * 
	 * @param lastUpdatedTimestamp
	 */          
	 public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	/**
	 * Returns the lastUpdatedUser.
	 * 
	 * @return lastUpdatedUser
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * The lastUpdatedUser to set.
	 * 
	 * @param lastUpdatedUser
	 */          
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * Returns the mandateId.
	 * 
	 * @return mandateId
	 */
	public int getMandateId() {
		return mandateId;
	}

	/**
	 * The mandateId to set.
	 * 
	 * @param mandateId
	 */
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}

	/**
	 * Returns the mandateName.
	 * 
	 * @return mandateName
	 */
	public String getMandateName() {
		return mandateName;
	}

	/**
	 * The mandateName to set.
	 * 
	 * @param mandateName
	 */          
	 public void setMandateName(String mandateName) {
		this.mandateName = mandateName;
	}

	/**
	 * Returns the mandateType.
	 * 
	 * @return mandateType
	 */
	public String getMandateType() {
		return mandateType;
	}

	/**
	 * The mandateType to set.
	 * 
	 * @param mandateType
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	/**
	 * Returns the status.
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * The status to set.
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the validationMessages.
	 * 
	 * @return validationMessages
	 */
	public ArrayList getValidationMessages() {
		return validationMessages;
	}

	/**
	 * The validationMessages to set.
	 * 
	 * @param validationMessages
	 */
	public void setValidationMessages(ArrayList validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Returns the version.
	 * 
	 * @return version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * The version to set.
	 * 
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the requiredCitationNumber.
	 * 
	 * @return requiredCitationNumber.
	 */
	public boolean isRequiredCitationNumber() {
		return requiredCitationNumber;
	}

	/**
	 * The requiredCitationNumber to set.
	 * 
	 * @param requiredCitationNumber
	 */
	public void setRequiredCitationNumber(boolean requiredCitationNumber) {
		this.requiredCitationNumber = requiredCitationNumber;
	}

	/**
	 * Returns the requiredDescription.
	 * 
	 * @return requiredDescription
	 */
	public boolean isRequiredDescription() {
		return requiredDescription;
	}

	/**
	 * The requiredDescription to set.
	 * 
	 * @param requiredDescription
	 */
	public void setRequiredDescription(boolean requiredDescription) {
		this.requiredDescription = requiredDescription;
	}

	/**
	 * Returns the requiredEffectiveDate.
	 * 
	 * @return requiredEffectiveDate
	 */
	public boolean isRequiredEffectiveDate() {
		return requiredEffectiveDate;
	}

	/**
	 * The requiredEffectiveDate to set.
	 * 
	 * @param requiredEffectiveDate
	 */
	public void setRequiredEffectiveDate(boolean requiredEffectiveDate) {
		this.requiredEffectiveDate = requiredEffectiveDate;
	}

	/**
	 * The requiredExpiryDate to set.
	 * 
	 * @return requiredExpiryDate
	 */
	public boolean isRequiredExpiryDate() {
		return requiredExpiryDate;
	}

	/**
	 * The requiredExpiryDate to set.
	 * 
	 * @param requiredExpiryDate
	 *            
	 */
	public void setRequiredExpiryDate(boolean requiredExpiryDate) {
		this.requiredExpiryDate = requiredExpiryDate;
	}

	/**
	 * Returns the requiredFundingArrangement.
	 * 
	 * @return requiredFundingArrangement
	 */
	public boolean isRequiredFundingArrangement() {
		return requiredFundingArrangement;
	}

	/**
	 * The requiredFundingArrangement to set.
	 * 
	 * @param requiredFundingArrangement
	 */
	public void setRequiredFundingArrangement(boolean requiredFundingArrangement) {
		this.requiredFundingArrangement = requiredFundingArrangement;
	}

	/**
	 * Returns the requiredGroupSize.
	 * 
	 * @return requiredGroupSize
	 */
	public boolean isRequiredGroupSize() {
		return requiredGroupSize;
	}

	/**
	 * The requiredGroupSize to set.
	 * 
	 * @param requiredGroupSize
	 */           
	 public void setRequiredGroupSize(boolean requiredGroupSize) {
		this.requiredGroupSize = requiredGroupSize;
	}

	/**
	 * Returns the requiredJurisdiction.
	 * 
	 * @return requiredJurisdiction
	 */
	public boolean isRequiredJurisdiction() {
		return requiredJurisdiction;
	}

	/**
	 * The requiredJurisdiction to set.
	 * 
	 * @param requiredJurisdiction
	 */
	public void setRequiredJurisdiction(boolean requiredJurisdiction) {
		this.requiredJurisdiction = requiredJurisdiction;
	}

	/**
	 * Returns the requiredMandateName.
	 * 
	 * @return requiredMandateName.
	 */
	public boolean isRequiredMandateName() {
		return requiredMandateName;
	}

	/**
	 * The requiredMandateName to set.
	 * 
	 * @param requiredMandateName
	 */           
	public void setRequiredMandateName(boolean requiredMandateName) {
		this.requiredMandateName = requiredMandateName;
	}

	/**
	 * Returns the requiredMandateType.
	 * 
	 * @return requiredMandateType.
	 */
	public boolean isRequiredMandateType() {
		return requiredMandateType;
	}

	/**
	 * The requiredMandateType to set.
	 * 
	 * @param requiredMandateType.
	 */           
	 public void setRequiredMandateType(boolean requiredMandateType) {
		this.requiredMandateType = requiredMandateType;
	}

	/**
	 * Returns the fundingArrangementDesc.
	 * 
	 * @return fundingArrangementDesc.
	 */
	public String getFundingArrangementDesc() {
		return fundingArrangementDesc;
	}

	/**
	 * The fundingArrangementDesc to set.
	 * 
	 * @param fundingArrangementDesc.
	 */            
	public void setFundingArrangementDesc(String fundingArrangementDesc) {
		this.fundingArrangementDesc = fundingArrangementDesc;
	}

	/**
	 * Returns the groupSizeDesc.
	 * 
	 * @return groupSizeDesc
	 */
	public String getGroupSizeDesc() {
		return groupSizeDesc;
	}

	/**
	 * The groupSizeDesc to set.
	 * 
	 * @param groupSizeDesc
	 */
	public void setGroupSizeDesc(String groupSizeDesc) {
		this.groupSizeDesc = groupSizeDesc;
	}

	/**
	 * Returns the mandateTypeDesc.
	 * 
	 * @return mandateTypeDesc
	 */
	public String getMandateTypeDesc() {
		return mandateTypeDesc;
	}

	/**
	 * The mandateTypeDesc to set.
	 * 
	 * @param mandateTypeDesc
	 */          
	public void setMandateTypeDesc(String mandateTypeDesc) {
		this.mandateTypeDesc = mandateTypeDesc;
	}
	
	/**
	 * Returns the checkIn.
	 * 
	 * @return checkIn
	 */
	public boolean isCheckIn() {
		return checkIn;
	}

	/**
	 * The checkIn to set.
	 * 
	 * @param checkIn
	 */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
	
	/**
	 * Returns the breadCrumpCreate
	 * 
	 * @return breadCrumpCreate.
	 */
	public String getBreadCrumpCreate() {
		this.setBreadCrumbText("Product Configuration >> Mandate >> Create");
		return breadCrumpCreate;
	}
	
	/**
	 * Sets the breadCrumpCreate
	 * 
	 * @param breadCrumpCreate.
	 */
	public void setBreadCrumpCreate(String breadCrumpCreate) {
		this.breadCrumpCreate = breadCrumpCreate;
	}
	
	/**
	 * Sets the viewMandateKey.
	 * 
	 * @param viewMandateKey
	 */
	public void setViewMandateKey(int viewMandateKey) {
		this.viewMandateKey = viewMandateKey;
	}

	/**
	 * Returns the checkoutFlag.
	 * 
	 * @return checkoutFlag
	 */
	public boolean isCheckoutFlag() {
		return checkoutFlag;
	}

	/**
	 * The checkoutFlag to set.
	 * 
	 * @param  checkoutFlag
	 */         
	 public void setCheckoutFlag(boolean checkoutFlag) {
		this.checkoutFlag = checkoutFlag;
	}

	/**
	 * Returns the messages.
	 * 
	 * @return messages
	 */
	public List getMessages() {
		return messages;
	}

	/**
	 * The messages to set.
	 * 
	 * @param messages
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}

	

	/**
	 * Method to save the newly created Mandate.
	 * 
	 * @return EMPTY_STRING
	 */
	public String createMandate() {

		 validationMessages = new ArrayList();

		//Validating if all the mandotary details are available, before saving.
		// If not, then it will redirects to the same page with error message.
		if (!validateRequiredFileds()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		
		//Validating the mandate name length.
		if(!validateMandateNameLength()){
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating the Citation Number
		/*if (!validateCitationNumber()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}*/

		// Validating if the date format is correct
		if (!validateDateFormat()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating if the effective date is greater than expiry date
		if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
			validationMessages.add(new ErrorMessage(
					WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		//Validating if the year is less than 1900
		if(this.dateLimit(this.getEffectiveDate())) {
			validationMessages.add(new ErrorMessage(
					WebConstants.INVALID_EFFECTIVE_DATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating for thee length of the Mandate Description
		if (!validateDescriptionLength()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Creating CreateMandateRequest
		CreateMandateRequest createMandateRequest = getCreateMandateRequest();
		createMandateRequest.setCreateFlag(true);

		//Creating the response object
		CreateMandateResponse createMandateResponse = (CreateMandateResponse) executeService(createMandateRequest);

		if (null != createMandateResponse) {

			//Getting the mandateVO from the response
			MandateVO mandateVO = createMandateResponse.getMandateVO();
			//Coping the values from the mandateVO to the backing bean
			copyResponseValuesToBackingBean(mandateVO);
			this.setBreadCrumbText("Product Configuration >> Mandate (" + this.mandateName + ") >> Edit");
			//If there is no error at the time of create, then will navigate to
			// edit page.
			if (!createMandateResponse.isErrorFlag()) {
				return WebConstants.SUCCESS;
			}
		}

		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Method to copy Mandate.
	 * 
	 * @return  WebConstants.SUCCESS
	 */
	public String copyMandate() {
		
		CopyMandateRequest copyMandateRequest = (CopyMandateRequest) this
				.getServiceRequest(ServiceManager.COPY_MANDATE_REQUEST);
		copyMandateRequest.setMandateId(this.getMandateId());
		CopyMandateResponse copyMandateResponse = (CopyMandateResponse) executeService(copyMandateRequest);
		MandateLocateResult mandateLocateResult = (MandateLocateResult) (copyMandateResponse
				.getMandateSearchResultList().get(0));
		this.setMandateId(mandateLocateResult.getMandateId());
		this.setMandateName(mandateLocateResult.getMandateName());
		this.setVersion(mandateLocateResult.getVersionNo());
		this.setCitationNumber(mandateLocateResult.getCitationNumber());
		this.setEffectiveDate(WPDStringUtil.getStringDate(mandateLocateResult
				.getEffectiveDate()));
		this.setExpiryDate(WPDStringUtil.getStringDate(mandateLocateResult
				.getExpiryDate()));
		this.setJurisdictionId(mandateLocateResult.getJurisdictionId());
		this.setJurisdictionDesc(mandateLocateResult.getJurisdictionDesc());
		this.setJurisdiction(this.jurisdictionDesc + '~' + this.jurisdictionId);
		this.setGroupSize(mandateLocateResult.getGroupSizeId());
		this.setMandateType(mandateLocateResult.getMandateTypeId());
		this.setFundingArrangement(mandateLocateResult
				.getFundingArrangementId());
		this.setDescription(mandateLocateResult.getDescription());
		return WebConstants.SUCCESS;
	}

	/**
	 * Method to save the updated Mandate.
	 * 
	 * @return EMPTY_STRING
	 */
	public String updateMandate() {
		
		CreateMandateRequest createMandateRequest = null;
		CreateMandateResponse createMandateResponse = null;
		//Validating if all the mandotary details are available, before saving.
		// If not, then it will redirects to the same page with error message.
		if (!validateRequiredFileds()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating the Citation Number
		/*if (!validateCitationNumber()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}*/

		//Validating if the date format is correct
		if (!validateDateFormat()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating if the effective date is greater than expiry date
		if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
			validationMessages.add(new ErrorMessage(
					WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		//Validating if the year is less than 1900
		if(this.dateLimit(this.getEffectiveDate())) {
			validationMessages.add(new ErrorMessage(
					WebConstants.INVALID_EFFECTIVE_DATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		//Validating for the length of the Mandate Description
		if (!validateDescriptionLength()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		//Creating createMandateRequest object
		createMandateRequest = this.getCreateMandateRequest();

		createMandateRequest.setCreateFlag(false);

		//Creating the response object
		createMandateResponse = (CreateMandateResponse) this
				.executeService(createMandateRequest);
		//Checking if the response is not null
		if (null != createMandateResponse) {
			//Coping the Mandate details to the backing bean
			this.copyResponseValuesToBackingBean(createMandateResponse
					.getMandateVO());
			this.setBreadCrumbText("Product Configuration >> Mandate (" + this.mandateName + ") >> Edit");
			return WebConstants.SUCCESS;
		}
		addAllMessagesToRequest(validationMessages);
		return WebConstants.EMPTY_STRING;

	}

	/**
	 * Method to create CreateMandateRequest and to copy the values from the
	 * backing bean.
	 * 
	 * @return  createMandateRequest
	 */
	private CreateMandateRequest getCreateMandateRequest() {

		//Creating response object
		CreateMandateRequest createMandateRequest = (CreateMandateRequest) this
				.getServiceRequest(ServiceManager.CREATE_MANDATE_REQUEST);

		//Creating mandateVO
		MandateVO mandateVO = new MandateVO();

		//Splitting the Id and description of jurisdiction, which is given from
		// the jsp page. jurisdiction is of the format: description~Id
		String[] array = jurisdiction.split("~");
		jurisdictionId = array[1];
		jurisdictionDesc = array[0];

		//Coping the values to the mandateVO from the bean
		mandateVO.setCitationNumber(this.getCitationNumber());
		mandateVO.setEffectiveDate(WPDStringUtil
				.getDateFromString(this.effectiveDate));
		mandateVO.setExpiryDate(WPDStringUtil
				.getDateFromString(this.expiryDate));
		mandateVO.setMandateTypeId(this.getMandateType());
		mandateVO.setFundingArrangementId(this.getFundingArrangement());
		mandateVO.setGroupSizeId(this.getGroupSize());
		mandateVO.setJurisdictionDesc(this.getJurisdictionDesc());
		mandateVO.setJurisdictionId(this.getJurisdictionId());
		mandateVO.setJurisdictionList(this.getJurisdictionList());
		mandateVO.setDescription(this.getDescription().trim());
		mandateVO.setMandateName(this.getMandateName().trim());
		mandateVO.setMandateId(this.mandateId);
		mandateVO.setVersion(this.version);
		mandateVO.setStatus(this.status);

		//Setting the mandateVo to the request object
		createMandateRequest.setMandateVO(mandateVO);

		//Returning the request object
		return createMandateRequest;
	}

	/**
	 * For Validating the numeric field.
	 * 
	 * @return boolean
	 *//*  
	private boolean validateCitationNumber() {
		try {
			validationMessages = new ArrayList();
			Long.parseLong(this.citationNumber);
		} catch (NumberFormatException ex) {
			validationMessages.add(new ErrorMessage(
					WebConstants.INVALID_CITATION_NUMBER));
			return false;
		}
		return true;
	}*/

	/**
	 * Method to validate the date format.
	 * 
	 * @return booleanvalue
	 */
	public boolean validateDateFormat() {

		validationMessages = new ArrayList();
		boolean requiredDate = false;

		//Checking the effective date is valid
		if (!WPDStringUtil.isValidDate(this.effectiveDate)) {
			requiredDate = true;
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage
					.setParameters(new String[] { WebConstants.EFFECTIVE_DATE });
			validationMessages.add(errorMessage);
		}

		//Checking the expiry date is valid
		if (!WPDStringUtil.isValidDate(this.expiryDate)) {
			requiredDate = true;
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage
					.setParameters(new String[] { WebConstants.EXPIRY_DATE });
			validationMessages.add(errorMessage);
		}

		if (requiredDate) {
			return false;
		}
		return true;
	}

	/**
	 * Method to compare two dates.
	 * 
	 * @param String effectiveDate
	 * @param String expiryDate
	 * @return booleanvalue
	 */
	public boolean dateComparison(String effectiveDate, String expiryDate) {
		//Extracting the month,day and year from the date string effectiveDate
		int effectiveMonth = Integer.parseInt(effectiveDate.substring(0,
				effectiveDate.indexOf("/")));
		int effectiveDay = Integer
				.parseInt(effectiveDate.substring(
						effectiveDate.indexOf("/") + 1, effectiveDate
								.lastIndexOf("/")));
		int effectiveYear = Integer.parseInt(effectiveDate.substring(
				effectiveDate.lastIndexOf("/") + 1, effectiveDate.length()));
		//Extracting the month,day and year from the date string expiryDate
		int expiryMonth = Integer.parseInt(expiryDate.substring(0, expiryDate
				.indexOf("/")));
		int expiryDay = Integer.parseInt(expiryDate.substring(expiryDate
				.indexOf("/") + 1, expiryDate.lastIndexOf("/")));
		int expiryYear = Integer.parseInt(expiryDate.substring(expiryDate
				.lastIndexOf("/") + 1, expiryDate.length()));

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		//Setting the extracted values to the calendar
		cal1.set(effectiveYear, effectiveMonth, effectiveDay);
		cal2.set(expiryYear, expiryMonth, expiryDay);
		//Check if the expiry date is greater than the effective date.
		if (cal2.before(cal1) || cal2.equals(cal1))
			return true;

		return false;

	}

	/**
	 * Method to check the limit
	 * 
	 * @param effectiveDate
	 * @return booleanvalue
	 */
	public boolean dateLimit(String effectiveDate) {
		//Extracting the year from the date string
		int effectiveYear = Integer.parseInt(effectiveDate.substring(
				effectiveDate.lastIndexOf("/") + 1, effectiveDate.length()));
		if(effectiveYear < 1900){
			return true;			
		}
		
		return false;

	}

	
	/**
	 * Method to validate the Mandate description given from the JSP page.
	 * 
	 * @return booleanvalue
	 */
	public boolean validateDescriptionLength() {
		validationMessages = new ArrayList();
		int descLength = this.description.trim().length();
		//Checking the description for the required length. 
		if (descLength < 10 || descLength > 500) {
			validationMessages.add(new ErrorMessage(
					WebConstants.INVALID_MANDATE_DESCRIPTION));
			return false;
		}
		return true;
	}
	
	/**
	 * This method is to validate the Mandate Name Length from the JSP Page.
	 * @return booleanvalue
	 */
	public boolean validateMandateNameLength(){
		validationMessages = new ArrayList();
		int mandateNameLength = this.mandateName.trim().length();
		//Checking the name for the required length.
		if(mandateNameLength > 35){
			validationMessages.add(new ErrorMessage(WebConstants.INVALID_MANDATE_NAME_LENGTH));
			return false;
		}
		return true;
	}

	/**
	 * Method to copy the values from the Response object to the backing bean.
	 * 
	 * @param mandateVo
	 */
	private void copyResponseValuesToBackingBean(MandateVO mandateVo) {

		this.setMandateId(mandateVo.getMandateId());
		this.setMandateName(mandateVo.getMandateName());
		this.setVersion(mandateVo.getVersion());
		this.setStatus(mandateVo.getStatus());
		if(null!=mandateVo.getState()){
			this.setState(mandateVo.getState().getState());
		}
		this.setCitationNumber(mandateVo.getCitationNumber());
		this.setEffectiveDate(WPDStringUtil.getStringDate(mandateVo
				.getEffectiveDate()));
		this.setExpiryDate(WPDStringUtil.getStringDate(mandateVo
				.getExpiryDate()));
		this.setJurisdictionId(mandateVo.getJurisdictionId());
		this.setJurisdictionDesc(mandateVo.getJurisdictionDesc());
		this.setJurisdiction(this.jurisdictionDesc + '~' + this.jurisdictionId);
		this.setGroupSize(mandateVo.getGroupSizeId());
		this.setMandateType(mandateVo.getMandateTypeId());
		this.setFundingArrangement(mandateVo.getFundingArrangementId());
		this.setDescription(mandateVo.getDescription());
		this.setCreatedUser(mandateVo.getCreatedUser());
		this.setCreatedTimestamp(WPDStringUtil.getStringDate(mandateVo
				.getCreatedTimestamp()));
		this.setLastUpdatedUser(mandateVo.getLastUpdatedUser());
		this.setLastUpdatedTimestamp(WPDStringUtil.getStringDate(mandateVo
				.getLastUpdatedTimestamp()));

	}

	/**
	 * Method to check whether all the mandotary fields are available.
	 * 
	 * @return booleanvalue
	 */
	private boolean validateRequiredFileds() {
		validationMessages = new ArrayList();
		boolean requiredField = false;

		requiredEffectiveDate = false;
		requiredExpiryDate = false;
		requiredMandateType = false;
		requiredJurisdiction = false;
		requiredGroupSize = false;
		requiredFundingArrangement = false;
		requiredDescription = false;
		requiredCitationNumber = false;
		requiredMandateName = false;

		if (this.effectiveDate == null || "".equals(this.effectiveDate.trim())) {
			requiredEffectiveDate = true;
			requiredField = true;
		}
		if (this.expiryDate == null || "".equals(this.expiryDate.trim())) {
			requiredExpiryDate = true;
			requiredField = true;
		}
		if (this.mandateType == null || "".equals(this.mandateType)) {
			requiredMandateType = true;
			requiredField = true;
		}
		if (this.jurisdiction == null || "".equals(this.jurisdiction)) {
			requiredJurisdiction = true;
			requiredField = true;
		}
		if (this.fundingArrangement == null
				|| "".equals(this.fundingArrangement)) {
			requiredFundingArrangement = true;
			requiredField = true;
		}
		if (this.groupSize == null || "".equals(this.groupSize)) {
			requiredGroupSize = true;
			requiredField = true;
		}
		if (this.description == null || "".equals(this.description.trim())) {
			requiredDescription = true;
			requiredField = true;
		}

		if (this.citationNumber == null
				|| "".equals(this.citationNumber.trim())) {
			requiredCitationNumber = true;
			requiredField = true;
		}

		if (this.mandateName == null || "".equals(this.mandateName.trim())) {
			requiredMandateName = true;
			requiredField = true;
		}
		if (requiredField) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		}
		return true;
	}

	/**
	 * Method to add messages to request.
	 * 
	 * @param messageList
	 */
	protected void addAllMessagesToRequest(List messageList) {
		HttpServletRequest request = getRequest();
		request.setAttribute("messages", messageList);
	}


	/**
	 * Method to retrieve Mandate.
	 * 
	 * @return 'mandateEditPage'
	 */
	public String retrieveMandate() {

		//Creating retrieveMandateRequest object
		RetrieveMandateRequest retrieveMandateRequest = (RetrieveMandateRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_MANDATE_REQUEST);

		//Setting the values to the request
		retrieveMandateRequest.getMandateVO().setMandateId(this.mandateId);
		retrieveMandateRequest.getMandateVO().setMandateName(this.mandateName);
		retrieveMandateRequest.getMandateVO().setVersion(this.version);
		//Setting the values in the session
		this.getSession().setAttribute("mandateId",new Integer(this.getMandateId()).toString());
	    this.getSession().setAttribute("mandateName",this.getMandateName());
		//Getting the response after the retrieve
		RetrieveMandateResponse retreiveMandateResponse = (RetrieveMandateResponse) executeService(retrieveMandateRequest);

		if (null != retreiveMandateResponse) {
			//Getting the mandateVO from the response
			MandateVO mandateVO = retreiveMandateResponse.getMandateVO();

			//Coping the values from the response to the backing bean.
			copyResponseValuesToBackingBean(mandateVO);
			addAllMessagesToRequest(messages);

		}
		this.setBreadCrumbText("Product Configuration >> Mandate (" + this.mandateName + ") >> Edit");
		return "mandateEditPage";
	}

	/**
	 * Method to view the mandate details.
	 * 
	 * @return viewMandateKey
	 */
	public int getViewMandateKey() {
		//Extracting the values from the url.
		String keyString = (String) (getRequest().getParameter("mandatekey"));
		if(null!=keyString  && keyString.matches("^[0-9a-zA-Z_]+$")){
			keyString = keyString;
       }else{
    	   keyString=null;
       }
		String keyName = (String) (getRequest().getParameter("mandatename"));
		if(null!=keyName  && keyName.matches("^[0-9a-zA-Z_]+$")){
			keyName = keyName;
       }else{
    	   keyName=null;
       }
		RetrieveMandateRequest retrieveMandateRequest = (RetrieveMandateRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_MANDATE_REQUEST);
		MandateVO mandateVo = new MandateVO();
		//Checking for null values
		if (null != keyString && null != keyName) {
			//Setting the values of the url to the mandateVo.
			int viewMandateKey = Integer.parseInt(keyString);
			mandateVo.setMandateId(viewMandateKey);
			mandateVo.setMandateName(keyName);
			this.getSession().setAttribute("mandateId", keyString);
			this.getSession().setAttribute("mandateName", keyName);
		}
		 else{
		 	//Loading the mandateVo with the values present in the session.
		    if (null != this.getSession().getAttribute("mandateId"))
		         mandateVo.setMandateId(Integer.parseInt((String)this.getSession().getAttribute("mandateId")));
		    if (null != this.getSession().getAttribute("mandateName"))
		        mandateVo.setMandateName((String)this.getSession().getAttribute("mandateName"));
		 	}


		retrieveMandateRequest.setMandateVO(mandateVo);
		RetrieveMandateResponse retreiveMandateResponse = (RetrieveMandateResponse) executeService(retrieveMandateRequest);
		
		this.mandateId = retreiveMandateResponse.getMandateVO().getMandateId();
		this.citationNumber = retreiveMandateResponse.getMandateVO()
				.getCitationNumber();
		this.description = retreiveMandateResponse.getMandateVO()
				.getDescription();
		this.effectiveDate = WPDStringUtil
				.getStringDate(retreiveMandateResponse.getMandateVO()
						.getEffectiveDate());
		this.expiryDate = WPDStringUtil.getStringDate(retreiveMandateResponse
				.getMandateVO().getExpiryDate());
		this.mandateName = retreiveMandateResponse.getMandateVO()
				.getMandateName();
		this.mandateTypeDesc = retreiveMandateResponse.getMandateVO()
				.getMandateTypeDesc();
		this.groupSizeDesc = retreiveMandateResponse.getMandateVO()
				.getGroupSizeDesc();
		this.jurisdictionDesc = retreiveMandateResponse.getMandateVO()
				.getJurisdictionDesc();
		this.fundingArrangementDesc = retreiveMandateResponse.getMandateVO()
				.getFundingArrangementDesc();
		this.createdUser = retreiveMandateResponse.getMandateVO()
				.getCreatedUser();
		this.createdTimestamp = WPDStringUtil
				.getStringDate(retreiveMandateResponse.getMandateVO()
						.getCreatedTimestamp());
		this.lastUpdatedUser = retreiveMandateResponse.getMandateVO()
				.getLastUpdatedUser();
		this.lastUpdatedTimestamp = WPDStringUtil
				.getStringDate(retreiveMandateResponse.getMandateVO()
						.getLastUpdatedTimestamp());
		this.setVersion(retreiveMandateResponse.getMandateVO().getVersion());
		this.setStatus(retreiveMandateResponse.getMandateVO().getStatus());
		this.setState(retreiveMandateResponse.getMandateVO().getState().getState());
		this.setBreadCrumbText("Product Configuration >> Mandate (" + this.mandateName + ") >> View");
		return viewMandateKey;
	}

	/**
	 * This method does the check in functionality for Mandate.
	 * 
	 * @return WebConstants.EMPTY_STRING
	 */
	public String done() {
		//Validating if all the mandotary details are available, before saving.
		// If not, then it will redirects to the same page with error message.
		if (!validateRequiredFileds()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		/*//Validating the Citation Number
		if (!validateCitationNumber()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}*/

		//Validating if the date format is correct
		if (!validateDateFormat()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating if the effective date is greater than expiry date
		if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
			validationMessages.add(new ErrorMessage(
					WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}

		//Validating for the length of the Mandate Description
		if (!validateDescriptionLength()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		List validationMessageList = new ArrayList();
		boolean checkIn = this.isCheckInFlag();
		if (checkIn) {
			MandateCheckInRequest mandateCheckInRequest = getMandateCheckInRequest();
			MandateCheckInResponse mandateCheckInResponse = (MandateCheckInResponse) executeService(mandateCheckInRequest);
			if (null != mandateCheckInResponse
					&& mandateCheckInResponse.isAction()) {
				this.mandateName = WebConstants.EMPTY_STRING;
				this.citationNumber = WebConstants.EMPTY_STRING;
				this.effectiveDate = WebConstants.EMPTY_STRING;
				this.expiryDate = WebConstants.EMPTY_STRING;
				this.mandateType = WebConstants.EMPTY_STRING;
				this.jurisdiction = WebConstants.EMPTY_STRING;
				this.groupSize = WebConstants.EMPTY_STRING;
				this.fundingArrangement = WebConstants.EMPTY_STRING;
				this.description = WebConstants.EMPTY_STRING;
				return "mandateCreatePage";
			}
		} else {
			validationMessageList.add(new InformationalMessage(
								"mandate.checkin.validation"));
			addAllMessagesToRequest(validationMessageList);
		}
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Mandate for marking the request for check-in
	 * 
	 * @return mandateCheckInRequest
	 */
	private MandateCheckInRequest getMandateCheckInRequest() {
		//Creating response object
		MandateCheckInRequest mandateCheckInRequest = (MandateCheckInRequest) this
				.getServiceRequest(ServiceManager.MANDATE_CHECKIN_REQUEST);

		//Creating mandateVO
		MandateVO mandateVO = new MandateVO();

		//Splitting the Id and description of jurisdiction, which is given from
		// the jsp page. jurisdiction is of the format: description~Id
		String[] array = jurisdiction.split("~");
		jurisdictionId = array[1];
		jurisdictionDesc = array[0];

		//Coping the values to the mandateVO from the bean
		mandateVO.setCitationNumber(this.getCitationNumber());
		mandateVO.setEffectiveDate(WPDStringUtil
				.getDateFromString(this.effectiveDate));
		mandateVO.setExpiryDate(WPDStringUtil
				.getDateFromString(this.expiryDate));
		mandateVO.setMandateTypeId(this.getMandateType());
		mandateVO.setFundingArrangementId(this.getFundingArrangement());
		mandateVO.setGroupSizeId(this.getGroupSize());
		mandateVO.setJurisdictionDesc(this.getJurisdictionDesc());
		mandateVO.setJurisdictionId(this.getJurisdictionId());
		mandateVO.setJurisdictionList(this.getJurisdictionList());
		mandateVO.setDescription(this.getDescription().trim());
		mandateVO.setMandateName(this.getMandateName().trim());
		mandateVO.setMandateId(this.mandateId);
		mandateVO.setVersion(this.version);
		mandateVO.setStatus(this.status);
		mandateVO.setCheckIn(this.checkIn);
		//Setting the mandateVo to the request object
		mandateCheckInRequest.setMandateVO(mandateVO);

		//Returning the request object
		return mandateCheckInRequest;
	}
	
	/**
	 * Method for View All functionality of Mandate
	 * 
	 * @return WebConstants.EMPTY_STRING
	 */
	public String getViewAllMandateKey() {
		List validationMessages = null;
		MandateVO mandateVo = new MandateVO();
		//Extracting the values from the url
		String keyString = (String) (getRequest().getParameter("mandatekey"));
		String keyName = (String) (ESAPI.encoder().encodeForHTML(getRequest().getParameter("mandatename")));
		if(null!=keyName  && keyName.matches("[0-9a-zA-Z_]+")){
			keyName = keyName;
		}
		String actionViewAll = (String) (getRequest().getParameter("action"));
		ViewAllMandateRequest viewAllMandateRequest = (ViewAllMandateRequest) this
				.getServiceRequest(ServiceManager.VIEW_ALL_MANDATE_REQUEST);
		//Checking for null values.
		if(keyString!=null && keyName!=null & actionViewAll!=null){
			//Loading the Vo's with the values from the url
			mandateVo.setMandateId(Integer.parseInt(keyString));
			mandateVo.setMandateName(keyName);
			mandateVo.setAction(actionViewAll);
		}else{
			//Setting the values in the mandateVo
			mandateVo.setMandateId(this.mandateId);
			mandateVo.setMandateName(this.mandateName);
			mandateVo.setAction("viewAll");
		}

		viewAllMandateRequest.setMandateVO(mandateVo);
		RetrieveMandateResponse viewAllMandateResponse = (RetrieveMandateResponse) executeService(viewAllMandateRequest);
		//Getting the result from the response.
		List viewAllListResult = viewAllMandateResponse.getViewAllList();
		if(viewAllListResult.size() == 0){
			this.setViewAllMandateList(null);
		}else{
			this.setViewAllMandateList(viewAllListResult);
		}
		this.setBreadCrumbText("Product Configuration >> Mandate (" + keyName + ") >> View All Versions");
		validationMessages = (List)(this.getSession().getAttribute("information"));
		addAllMessagesToRequest(validationMessages);
		//Resetting the session values
		this.getSession().setAttribute("information", null);
		return "";
	}
}