/*
 * ConfirmMigrationContractBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.migration;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.migration.request.ConfirmMigrationContractRequest;
import com.wellpoint.wpd.common.migration.response.ConfirmMigrationContractResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ConfirmMigrationContractBackingBean extends
		MigrationBaseBackingBean {

	protected java.util.List validationMessages;

	protected String updateLastAccessed;

	protected String referenceID;

	private static final String PATH = "/WEB-INF/references.properties";

	private static final String REFERENCE = "reference.id";
	
	private String benefitYrIndWarningMessage;

	/**
	 * Constructor
	 */
	public ConfirmMigrationContractBackingBean() {
		super();
		String contractId = getMigrationContractSession()
				.getMigrationContract().getContractId();
		String startDate = WPDStringUtil.convertDateToString(this
				.getMigrationContractSession().getStartDateEwpd());
		String endDate = WPDStringUtil.convertDateToString(this
				.getMigrationContractSession().getEndDate());
		this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
				+ contractId + " (" + startDate + " - " + endDate + ")");

	}

	/**
	 * @return String
	 */
	public String confirmMapping() {
		ConfirmMigrationContractRequest confirmMigrationContractRequest = (ConfirmMigrationContractRequest) this
				.getServiceRequest(ServiceManager.CONFIRM_MIG_CONTRACT_REQUEST);
		confirmMigrationContractRequest
				.setAction(ConfirmMigrationContractRequest.CONFIRM_MIG_CONTRACT_REQUEST);
		getReferenceFromResourceBundle();
		confirmMigrationContractRequest.setReferenceID(this.getReferenceID());
		this.setToRequest(confirmMigrationContractRequest);

		ConfirmMigrationContractResponse confirmMigrationContractResponse = (ConfirmMigrationContractResponse) this
				.executeService(confirmMigrationContractRequest);

		if (null != confirmMigrationContractResponse) {
			java.util.List messages = confirmMigrationContractResponse
					.getMessages();
			if (null != messages && !messages.isEmpty()) {
				this.addAllMessagesToRequest(messages);
				if (!(confirmMigrationContractResponse.isSuccess())) {
					if ((messages.get(0)) instanceof ErrorMessage) {
						return "";
					}
				} else {
//					 hide popup that used for to replace current product with latest product in second time migration. 
					//this.getSession().setAttribute(WebConstants.HIDE_PRODUCT_POPUP, WebConstants.FALSE);

					Logger
							.logInfo("************MIGRATION IS SUCCESSFUL FOR CONTRACT ID "
									+ super.getMigrationContractSession()
											.getMigrationContract()
											.getContractId()
									+ " ****************");
				}
			}
		}
		getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
		getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
		getSession().setAttribute(WebConstants.SESSION_TREE_STATE, null);
		return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP1);
	}

	/**
	 * @return
	 */
	public String getPreviousPage() {
		return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
	}

	/**
	 * Returns the validationMessages
	 * 
	 * @return java.util.List validationMessages.
	 */
	public java.util.List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * Sets the validationMessages
	 * 
	 * @param validationMessages.
	 */
	public void setValidationMessages(java.util.List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Returns the updateLastAccessed
	 * 
	 * @return String updateLastAccessed.
	 */
	public String getUpdateLastAccessed() {
		List messages = (List)getRequest().getAttribute("messages");
		ConfirmMigrationContractRequest confirmMigrationContractRequest = (ConfirmMigrationContractRequest) this
				.getServiceRequest(ServiceManager.CONFIRM_MIG_CONTRACT_REQUEST);
		if (null != super.getMigrationContractSession().getNavigationInfo()) {
			// Commented If Condition As Service needs to be called always as BY/CY conflict message needs to be loaded while loading the page.
//			if (!super.getMigrationContractSession().getNavigationInfo()
//					.getLastAccessedPage().equals(
//							WebConstants.MIG_CONTRACT_STEP9)) {
				confirmMigrationContractRequest
						.setAction(ConfirmMigrationContractRequest.RETRIEVE_MIG_CONTRACT_REQUEST);
				this.getMigrationContractSession().getNavigationInfo()
						.setUpdateLastAccessedPageOnly(true);
				this.setToRequest(confirmMigrationContractRequest);
				this.getMigrationContractSession().getNavigationInfo()
						.setNavigationFlag(true);
				ConfirmMigrationContractResponse response = (ConfirmMigrationContractResponse)executeService(confirmMigrationContractRequest);
				if(response != null) {
					InformationalMessage msg = response.getBenefitYearIndConflictMessage();
					if(msg != null) {
				        ResourceBundle myResources = ResourceBundle.getBundle("com.wellpoint.wpd.common.resources.UserMessages");
				        String returnMsg = myResources.getString(msg.getId());
				        this.benefitYrIndWarningMessage  = MessageFormat.format(returnMsg, msg.getParameters());
					} else {
						this.benefitYrIndWarningMessage = "";
					}
				}
				List list = (List) getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3);
				if (null != list && list.size() > 0) {
					addAllMessagesToRequest(list);
				}
				if (null != getSession().getAttribute(
						WebConstants.MESSAGE_LIST_STEP3))
					getSession().removeAttribute(
							WebConstants.MESSAGE_LIST_STEP3);
//			}
		}
		super.addAllMessagesToRequest(messages);
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Sets the updateLastAccessed
	 * 
	 * @param updateLastAccessed.
	 */
	public void setUpdateLastAccessed(String updateLastAccessed) {
		this.updateLastAccessed = updateLastAccessed;
	}

	public void getReferenceFromResourceBundle() {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		InputStream inputStream = facesContext.getExternalContext()
				.getResourceAsStream(PATH);
		Properties properties = new Properties();
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				SevereException se = new SevereException(
						"Unexpected error caught in ContractBackingBean", null,
						e);
				Logger.logException(se);
			}
		}
		this.setReferenceID(properties.getProperty(REFERENCE));
	}

	/**
	 * @return Returns the referenceID.
	 */
	public String getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID
	 *            The referenceID to set.
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	/**
	 * @return Returns the benefitYrIndWarningMessage.
	 */
	public String getBenefitYrIndWarningMessage() {
		return benefitYrIndWarningMessage;
	}
	/**
	 * @param benefitYrIndWarningMessage The benefitYrIndWarningMessage to set.
	 */
	public void setBenefitYrIndWarningMessage(String benefitYrIndWarningMessage) {
		this.benefitYrIndWarningMessage = benefitYrIndWarningMessage;
	}
}