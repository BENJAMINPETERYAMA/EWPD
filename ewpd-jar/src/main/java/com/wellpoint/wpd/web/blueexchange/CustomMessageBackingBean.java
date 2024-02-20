/*
 * CustomMessageBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.blueexchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.blueexchange.request.CustomMessageRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageTextCreateRequest;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageTextUpdateRequest;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageRetrieveResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing Bean for Cusstom message text create and edit.
 */
public class CustomMessageBackingBean extends WPDBackingBean {
	private String headerRule;

	private String SPSParameter;

	private String customMessage;

	private String messageRequired = "N";

	private boolean requiredHeaderRule = false;

	private boolean requiredSPSParameter = false;

	private boolean requiredCustomMessage = false;

	private List validationMessages;

	private String createdUser;

	private String lastChangedUser;

	private Date createdDate;

	private Date lastChangedDate;
	
	private String noteTypeCode;

	public CustomMessageBackingBean() {
		super();
	}

	/**
	 * Method to create Custom Message text.
	 * 
	 * @param
	 * @return string
	 *  
	 */
	public String createCustomMessage() {

		if (validateCustomMessage()) {

			CustomMessageTextCreateRequest customMessageTextCreateRequest = (CustomMessageTextCreateRequest) this
					.getServiceRequest(ServiceManager.CUSTOM_MESSAGE_CREATE_REQUEST);
			customMessageTextCreateRequest.setCustomMessageTextVO(this
					.getCustomMessageTextVO());
			CustomMessageTextCreateResponse customMessageTextCreateResponse = (CustomMessageTextCreateResponse) this
					.executeService(customMessageTextCreateRequest);
			if (null != customMessageTextCreateResponse.getMessages()
					&& !customMessageTextCreateResponse.getMessages().isEmpty()) {
				this.setValidationMessages(customMessageTextCreateResponse
						.getMessages());
				this.addAllMessagesToRequest(this.validationMessages);
				if (null != customMessageTextCreateResponse
						.getCustomMessageTextVO()) {
					CustomMessageTextVO customMessageTextVO = customMessageTextCreateResponse
							.getCustomMessageTextVO();
					this.setValuesToBackingBean(customMessageTextVO);
				}
			}
			if (customMessageTextCreateResponse.isValidationSuccessFlag())
				return WebConstants.CUSTOM_MESSAGE_EDIT;

		}

		this.setBreadCrumbText(WebConstants.CUSTOM_MESSAGE_CREATE_BREADCRUMB);
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Method to update Custom Message text.
	 * 
	 * @param
	 * @return string
	 *  
	 */
	public String updateCustomMessage() {
		if (validateCustomMessage()) {

			CustomMessageTextUpdateRequest customMessageTextUpdateRequest = (CustomMessageTextUpdateRequest) this
					.getServiceRequest(ServiceManager.CUSTOM_MESSAGE_UPDATE_REQUEST);
			customMessageTextUpdateRequest.setCustomMessageTextVO(this
					.getCustomMessageTextVO());
			CustomMessageTextUpdateResponse customMessagetTextUpdateResponse = (CustomMessageTextUpdateResponse) this
					.executeService(customMessageTextUpdateRequest);
			if (customMessagetTextUpdateResponse.getMessages() != null
					&& !customMessagetTextUpdateResponse.getMessages()
							.isEmpty()) {
				this.setValidationMessages(customMessagetTextUpdateResponse
						.getMessages());
				if (null != customMessagetTextUpdateResponse
						.getCustomMessageTextVO()) {
					CustomMessageTextVO customMessageTextVO = customMessagetTextUpdateResponse
							.getCustomMessageTextVO();
					this.setValuesToBackingBean(customMessageTextVO);
				}
			}

		} else {
			this
					.setBreadCrumbText("Administration >> Blue Exchange >> Custom Message Text ("
							+ (this.headerRule != null
									&& !"".equalsIgnoreCase(this.headerRule) ? this.headerRule
									.split("~")[1]
									: "")
							+ " - "
							+ (this.SPSParameter != null
									&& !"".equalsIgnoreCase(this.SPSParameter) ? this.SPSParameter
									.split("~")[1]
									: "") + ") >> Edit");
		}

		this.addAllMessagesToRequest(this.validationMessages);
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Method to load Custom Message text for edit.
	 * 
	 * @param
	 * @return string
	 *  
	 */
	public String loadCustomMessage() {
		CustomMessageRetrieveRequest customMessageRetrieveRequest = (CustomMessageRetrieveRequest) this
				.getServiceRequest(ServiceManager.CUSTOM_MESSAGE_RETRIEVE_REQUEST);
		Application application = FacesContext.getCurrentInstance()
				.getApplication();
		CustomMessageTextVO customMessageTextVO = new CustomMessageTextVO();
		CustomMessageSearchBackingBean customMessageSearchBackingBean = ((CustomMessageSearchBackingBean) application
				.createValueBinding("#{customMessageSearchBackingBean}")
				.getValue(FacesContext.getCurrentInstance()));
		if (null != customMessageSearchBackingBean.getSpsId()
				&& null != customMessageSearchBackingBean.getHeaderRuleId()) {
			customMessageTextVO
					.setSpsParameterId(customMessageSearchBackingBean
							.getSpsId());
			customMessageTextVO.setHeaderRuleId(customMessageSearchBackingBean
					.getHeaderRuleId());

		}
		customMessageRetrieveRequest.setMessageTextVO(customMessageTextVO);
		CustomMessageRetrieveResponse customMessageRetrieveResponse = (CustomMessageRetrieveResponse) this
				.executeService(customMessageRetrieveRequest);
		if (null != customMessageRetrieveResponse) {
			setValuesToBackingBean(customMessageRetrieveResponse
					.getMessageTextVO());
		}
		return WebConstants.CUSTOM_MESSAGE_EDIT;
	}

	/**
	 * Method to get the CustomMessageTextVO.
	 * 
	 * @param
	 * @return CustomMessageTextVO
	 *  
	 */
	private CustomMessageTextVO getCustomMessageTextVO() {
		CustomMessageTextVO customMessageTextVO = new CustomMessageTextVO();
		customMessageTextVO.setHeaderRuleId(getIdFromCharSeperatedString(this
				.getHeaderRule(), "~"));
		customMessageTextVO.setSpsParameterId(getIdFromCharSeperatedString(this
				.getSPSParameter(), "~"));
		customMessageTextVO.setMessagetext(this.getCustomMessage());
		customMessageTextVO.setMessageReqIndicator(this.messageRequired.trim());
		customMessageTextVO.setNoteTypeCode(getIdFromCharSeperatedString(this.getNoteTypeCode(),"~"));
		return customMessageTextVO;
	}

	/**
	 * Method to get the ID from '~' separated string.
	 * 
	 * @param
	 * @return String
	 *  
	 */
	private String getIdFromCharSeperatedString(String value, String delim) {
		return value == null ? null
				: (("".equalsIgnoreCase(value) || value == null) ? null : value
						.split(delim)[1]);
	}

	/**
	 * @return Returns the customMessage.
	 */
	public String getCustomMessage() {
		if (null != customMessage)
			customMessage = this.customMessage.trim();
		return customMessage;
	}

	/**
	 * @param customMessage
	 *            The customMessage to set.
	 */
	public void setCustomMessage(String customMessage) {
		StringTokenizer tokenizer = new StringTokenizer(customMessage, " \t");
		StringBuffer buffer = new StringBuffer();
		while (tokenizer.hasMoreTokens()) {
			buffer.append(" ").append(tokenizer.nextToken());
		}
		customMessage = buffer.toString().trim().toUpperCase();
		this.customMessage = customMessage;
	}

	/**
	 * @return Returns the headerRule.
	 */
	public String getHeaderRule() {
		return headerRule;
	}

	/**
	 * @param headerRule
	 *            The headerRule to set.
	 */
	public void setHeaderRule(String headerRule) {
		this.headerRule = headerRule;
	}

	/**
	 * @return Returns the messageRequired.
	 */
	public String getMessageRequired() {
		return messageRequired;
	}

	/**
	 * @param messageRequired
	 *            The messageRequired to set.
	 */
	public void setMessageRequired(String messageRequired) {
		this.messageRequired = messageRequired;
	}

	/**
	 * @return Returns the sPSParameter.
	 */
	public String getSPSParameter() {
		return SPSParameter;
	}

	/**
	 * @param parameter
	 *            The sPSParameter to set.
	 */
	public void setSPSParameter(String parameter) {
		SPSParameter = parameter;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the requiredCustomMessage.
	 */
	public boolean isRequiredCustomMessage() {
		return requiredCustomMessage;
	}

	/**
	 * @param requiredCustomMessage
	 *            The requiredCustomMessage to set.
	 */
	public void setRequiredCustomMessage(boolean requiredCustomMessage) {
		this.requiredCustomMessage = requiredCustomMessage;
	}

	/**
	 * @return Returns the requiredHeaderRule.
	 */
	public boolean isRequiredHeaderRule() {
		return requiredHeaderRule;
	}

	/**
	 * @param requiredHeaderRule
	 *            The requiredHeaderRule to set.
	 */
	public void setRequiredHeaderRule(boolean requiredHeaderRule) {
		this.requiredHeaderRule = requiredHeaderRule;
	}

	/**
	 * @return Returns the requiredSPSParameter.
	 */
	public boolean isRequiredSPSParameter() {
		return requiredSPSParameter;
	}

	/**
	 * @param requiredSPSParameter
	 *            The requiredSPSParameter to set.
	 */
	public void setRequiredSPSParameter(boolean requiredSPSParameter) {
		this.requiredSPSParameter = requiredSPSParameter;
	}

	/**
	 * Method to validate the Custom Message text. This method validates
	 * mandatory field and length of message text.
	 * 
	 * @param
	 * @return boolean
	 *  
	 */
	private boolean validateCustomMessage() {
		validationMessages = new ArrayList();
		boolean valid = true;
		if (this.customMessage == null
				|| "".equalsIgnoreCase(this.customMessage.trim())) {
			valid = false;
			this.setRequiredCustomMessage(true);
		}
		if (this.headerRule == null
				|| "".equalsIgnoreCase(this.headerRule.trim())) {
			valid = false;
			this.setRequiredHeaderRule(true);
		}
		if (this.SPSParameter == null
				|| "".equalsIgnoreCase(this.SPSParameter.trim())) {
			valid = false;
			this.setRequiredSPSParameter(true);
		}
		if (valid) {
			if (this.getCustomMessage().trim().length() > 40) {
				valid = false;
				this.setRequiredCustomMessage(true);
				this.validationMessages.add(new ErrorMessage(
						WebConstants.CUSTOM_MESSAGE_LENGTH));
				this.addAllMessagesToRequest(this.validationMessages);
			}
		} else {
			this.validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELD_REQUIRED));
			this.addAllMessagesToRequest(this.validationMessages);
		}
		return valid;
	}

	/**
	 * Method to set the values to backing bean from the response.
	 * 
	 * @param CustomMessageTextVO
	 * @return
	 *  
	 */
	private void setValuesToBackingBean(CustomMessageTextVO customMessageTextVO) {
		this.headerRule = customMessageTextVO.getHeaderRuleDesc() + "~"
				+ customMessageTextVO.getHeaderRuleId();
		this.SPSParameter = customMessageTextVO.getSpsParameterDesc() + "~"
				+ customMessageTextVO.getSpsParameterId();
		this.customMessage = customMessageTextVO.getMessagetext();
		this.messageRequired = customMessageTextVO.getMessageReqIndicator();
		this.createdUser = customMessageTextVO.getCreatedUser();
		this.createdDate = customMessageTextVO.getCreatedTimestamp();
		this.lastChangedUser = customMessageTextVO.getLastUpdatedUser();
		this.lastChangedDate = customMessageTextVO.getLastUpdatedTimestamp();
		if(null != customMessageTextVO.getNoteTypeCode()){
			this.noteTypeCode =customMessageTextVO.getNoteTypeDesc()+ "~" +customMessageTextVO.getNoteTypeCode();
		}
		CustomMessageSessionObject messageSessionObject = new CustomMessageSessionObject();
		messageSessionObject.setHeaderRuleId(customMessageTextVO
				.getHeaderRuleId());
		messageSessionObject.setSpsParameterId(customMessageTextVO
				.getSpsParameterId());
		getSession().setAttribute("customMessageSession", messageSessionObject);
		this
				.setBreadCrumbText("Administration >> Blue Exchange >> Custom Message Text ("
						+ (this.headerRule != null
								&& !"".equalsIgnoreCase(this.headerRule) ? this.headerRule
								.split("~")[1]
								: "")
						+ " - "
						+ (this.SPSParameter != null
								&& !"".equalsIgnoreCase(this.SPSParameter) ? this.SPSParameter
								.split("~")[1]
								: "") + ") >> Edit");
	}

	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return Returns the lastChangedDate.
	 */
	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	/**
	 * @param lastChangedDate
	 *            The lastChangedDate to set.
	 */
	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	/**
	 * @return Returns the lastChangedUser.
	 */
	public String getLastChangedUser() {
		return lastChangedUser;
	}

	/**
	 * @param lastChangedUser
	 *            The lastChangedUser to set.
	 */
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}
	/**
	 * @return Returns the noteTypeCode.
	 */
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	/**
	 * @param noteTypeCode The noteTypeCode to set.
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
}