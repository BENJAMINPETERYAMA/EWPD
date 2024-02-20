/*
 * MandateSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.mandate;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.common.mandate.request.CheckOutMandateRequest;
import com.wellpoint.wpd.common.mandate.request.DeleteMandateRequest;
import com.wellpoint.wpd.common.mandate.request.LocateMandateRequest;
import com.wellpoint.wpd.common.mandate.request.MandatePublishRequest;
import com.wellpoint.wpd.common.mandate.request.MandateSendForTestRequest;
import com.wellpoint.wpd.common.mandate.request.MandateTestFailRequest;
import com.wellpoint.wpd.common.mandate.request.MandateTestPassRequest;
import com.wellpoint.wpd.common.mandate.response.CheckOutMandateResponse;
import com.wellpoint.wpd.common.mandate.response.DeleteMandateResponse;
import com.wellpoint.wpd.common.mandate.response.LocateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.RetrieveMandateResponse;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


/**
 * Backing bean for searching Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateSearchBackingBean extends WPDBackingBean {
	
	private int mandateId;
	
	private int versionNo;
	
	private String citationNumber;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	private String mandateType;
	
	private String jurisdiction;
	
	private String groupSize;
	
	private String fundingArrangement;
	
	private String mandateName;
	
	private List citationNumberList;
	
	private List jurisdictionList;
	
	private List mandateSearchResultList;
	
	private Date dateOfEffective;
	
	private Date dateOfExpiry;
	
	private boolean effectiveDateInvalid = false;
	
	private boolean expiryDateInvalid = false;
	
	private boolean invalidFieldMessage = false;
	
	private List validationMessages = new ArrayList();
	
	private List messages;
	
	private boolean checkoutFlag = false;
	
	private String action;
	
	private static final int SIZE = 50;
	
	/**
	 * Default Constructor
	 *  
	 */
	public MandateSearchBackingBean() {
		this.setBreadCrump();
	}
	
	/**
	 * Sets the bread crump text.
	 */
	protected void setBreadCrump(){
		this.setBreadCrumbText("Product Configuration >> Mandate >> Locate");
	}
	
	/**
	 * Method to perform search action.
	 * 
	 * @return String
	 */
	public String locateMandate() {
		
		LocateMandateRequest locateMandateRequest = null;
		LocateMandateResponse locateMandateResponse = null;
		
		//Checking all the required fields are available
		if (!validateRequiredFields()) {
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		
		//Checking the dates are valid
		if (isAllDatessValid()) {
			
			//Getting the locateMandateRequest object
			locateMandateRequest = this.getLocateMandateRequest();
			
			//Getting the locateMandateResponse after performing Search
			locateMandateResponse = (LocateMandateResponse) this
			.executeService(locateMandateRequest);
			
			if (null != locateMandateResponse) {
				
				//Setting the search result from the response to the backing
				// bean
				this.setMandateSearchResultList(locateMandateResponse
						.getMandateSearchResultList());
			}
			
		} else {
			//Adding all the validation messages to the request
			addAllMessagesToRequest(validationMessages);
		}
		
		return WebConstants.EMPTY_STRING;
	}
	
	/**
	 * Method to get LocateMandateRequest.
	 * 
	 * @return locateMandateRequest LocateMandateRequest
	 */
	private LocateMandateRequest getLocateMandateRequest() {
		
		//Creating the request
		LocateMandateRequest locateMandateRequest = (LocateMandateRequest) this
		.getServiceRequest(ServiceManager.MANDATE_LOCATE_REQUEST);
		
		//Setting the search criteria values to the request
		locateMandateRequest.getMandateVO().setCitationNumberList(
				this.getCitationNumberList());
		locateMandateRequest.getMandateVO().setEffectiveDate(
				this.dateOfEffective);
		locateMandateRequest.getMandateVO().setExpiryDate(this.dateOfExpiry);
		locateMandateRequest.getMandateVO().setFundingArrangementId(
				this.getFundingArrangement());
		locateMandateRequest.getMandateVO().setGroupSizeId(this.getGroupSize());
		locateMandateRequest.getMandateVO().setJurisdictionList(
				this.getJurisdictionList());
		locateMandateRequest.getMandateVO().setMandateTypeId(
				this.getMandateType());
		locateMandateRequest.setMaxSize(MandateSearchBackingBean.SIZE);
		return locateMandateRequest;
		
	}
	
	/**
	 * Method to perform the delete action.
	 * 
	 * @return returnMessages String
	 */
	public String deleteMandate() {
		String returnMessages = "";
		DeleteMandateRequest deleteMandateRequest = null;
		DeleteMandateResponse deleteMandateResponse = null;
		
		//Getting the deleteMandateRequest object 
		deleteMandateRequest = getDeleteMandateRequest();
		
		//Getting the deleteMandateResponse after performing Delete
		deleteMandateResponse = (DeleteMandateResponse) this
		.executeService(deleteMandateRequest);
		if (null != deleteMandateResponse) {
			//Setting the messages from the deleteMandateResponse to the bean
			this.setMessages(deleteMandateResponse.getMessages());
			if("viewAll".equals(this.getAction())){
				addAllMessagesToRequest(messages);
				//Gets the messages from session.
				this.getSession().setAttribute("information",messages);
			}else{
				returnMessages = this.locateMandate();
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
			}
		}else{
			if("viewAll".equals(this.getAction())){
				messages = (List)(getRequest().getAttribute("messages"));
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}
		}
		
		return returnMessages;
	}
	
	/**
	 * Method to get the DeleteMandateRequest object.
	 * 
	 * @return deleteMandateRequest DeleteMandateRequest
	 */
	private DeleteMandateRequest getDeleteMandateRequest() {
		
		//Creating request object the of type DeleteMandateRequest
		DeleteMandateRequest deleteMandateRequest = (DeleteMandateRequest) this
		.getServiceRequest(ServiceManager.MANDATE_DELETE_REQUEST);
		
		//Setting the values from the bean to the request.
		deleteMandateRequest.getMandateVO().setMandateId(this.getMandateId());
		deleteMandateRequest.getMandateVO().setMandateName(
				this.getMandateName());
		deleteMandateRequest.getMandateVO().setVersion(this.getVersionNo());
		
		return deleteMandateRequest;
	}
	
	/**
	 * This method checks out Mandate.
	 * 
	 * @return returnMessage String 
	 */
	public String checkOutMandate(){
		String returnMessage;
		this.setCheckoutFlag(true);
		//creating CheckOutMandateRequest
		CheckOutMandateRequest checkOutMandateRequest =  (CheckOutMandateRequest) this
		.getServiceRequest(ServiceManager.CHECKOUT_MANDATE_REQUEST);
		
		MandateVO mandateVO = checkOutMandateRequest.getMandateVO();
		//Setting the values to the request
		mandateVO.setMandateId(this.mandateId);
		mandateVO.setMandateName(this.mandateName);
		mandateVO.setVersion(this.versionNo);
		
		CheckOutMandateResponse checkOutMandateResponse = (CheckOutMandateResponse)executeService(checkOutMandateRequest);
		
		if (null != checkOutMandateResponse && checkOutMandateResponse.isAction()) {
			
			//Getting the mandateBO from the response
			MandateBO mandateBO = checkOutMandateResponse.getMandateBO();
			Application application = FacesContext.getCurrentInstance().getApplication();
			//Creating Mandate Backing Bean
			MandateBackingBean mandateBackingBean =  ((MandateBackingBean) application.createValueBinding("#{mandateBackingBean}").getValue(FacesContext.getCurrentInstance()));
			mandateBackingBean.setMandateId(mandateBO.getMandateId());
			mandateBackingBean.setMandateName(mandateBO.getMandateName());
			mandateBackingBean.setVersion(mandateBO.getVersion());
			mandateBackingBean.setMessages(checkOutMandateResponse.getMessages());
			mandateBackingBean.setCheckoutFlag(true);
			return mandateBackingBean.retrieveMandate();
		} else {
			this.setMessages(checkOutMandateResponse.getMessages());
			returnMessage = this.locateMandate();
			addAllMessagesToRequest(messages);
			return returnMessage;
			
		}
	}
	
	/**
	 * Returns the mandateId.
	 * 
	 * @return mandateId int
	 */
	public int getMandateId() {
		return mandateId;
	}
	
	
	/**
	 * Sets the mandateId.
	 * 
	 * @param mandateId int
	 */
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}
	
	
	/**
	 * Returns the citationNumber
	 * 
	 * @return citationNumber String
	 */
	public String getCitationNumber() {
		return citationNumber;
	}
	
	
	/**
	 * Sets the citationNumber.
	 * 
	 * @param citationNumber String
	 */
	public void setCitationNumber(String citationNumber) {
		this.citationNumber = citationNumber;
	}
	
	/**
	 * Returns the citationNumberList
	 * 
	 * @return citationNumberList List
	 */
	public List getCitationNumberList() {
		citationNumberList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(this
				.getCitationNumber(), "~");
		int tokenCount = tokenizer.countTokens();
		for (int i = 0; i < tokenCount; i++) {
			String citationNo = tokenizer.nextToken();
			if (null != citationNo && ((i % 2) == 1)) {
				citationNumberList.add(citationNo);
			}
		}
		return citationNumberList;
	}
	
	/**
	 * Sets the citationNumberList.
	 * 
	 * @param citationNumberList List
	 */
	public void setCitationNumberList(List citationNumberList) {
		this.citationNumberList = citationNumberList;
	}
	
	/**
	 * Returns the effectiveDate.
	 * 
	 * @return effectiveDate String 
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	
	/**
	 * Sets the effectiveDate.
	 * 
	 * @param effectiveDate String
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * Returns the effectiveDateInvalid.
	 * 
	 * @return effectiveDateInvalid boolean
	 */
	public boolean isEffectiveDateInvalid() {
		return effectiveDateInvalid;
	}
	
	/**
	 * Sets the effectiveDateInvalid
	 * 
	 * @param effectiveDateInvalid boolean
	 */
	public void setEffectiveDateInvalid(boolean effectiveDateInvalid) {
		this.effectiveDateInvalid = effectiveDateInvalid;
	}
	
	/**
	 * Returns the expiryDate
	 * 
	 * @return expiryDate String
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	
	/**
	 * Sets the expiryDate
	 * 
	 * @param expiryDate String
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 * Returns the expiryDateInvalid
	 * 
	 * @return expiryDateInvalid boolean
	 */
	public boolean isExpiryDateInvalid() {
		return expiryDateInvalid;
	}
	
	/**
	 * Sets the expiryDateInvalid
	 * 
	 * @param expiryDateInvalid boolean
	 */
	public void setExpiryDateInvalid(boolean expiryDateInvalid) {
		this.expiryDateInvalid = expiryDateInvalid;
	}
	
	/**
	 * Returns the fundingArrangement
	 * 
	 * @return fundingArrangement String 
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	
	/**
	 * Sets the fundingArrangement
	 * 
	 * @param fundingArrangement String 
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	
	/**
	 * Returns the groupSize
	 * 
	 * @return groupSize String 
	 */
	public String getGroupSize() {
		return groupSize;
	}
	
	/**
	 * Sets the groupSize
	 * 
	 * @param groupSize String 
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}
	
	/**
	 * Returns the jurisdiction
	 * 
	 * @return jurisdiction String 
	 */
	public String getJurisdiction() {
		return jurisdiction;
	}
	
	/**
	 * Sets the jurisdiction
	 * 
	 * @param jurisdiction String 
	 */
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	
	/**
	 * Returns the jurisdictionList
	 * 
	 * @return jurisdictionList List
	 */
	public List getJurisdictionList() {
		jurisdictionList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(this.getJurisdiction(),
		"~");
		int tokenCount = tokenizer.countTokens();
		for (int i = 0; i < tokenCount; i++) {
			String jurisdiction = tokenizer.nextToken();
			if (null != jurisdiction && ((i % 2) == 1)) {
				jurisdictionList.add(new Integer(jurisdiction));
			}
		}
		return jurisdictionList;
	}
	
	/**
	 * Sets the jurisdictionList
	 * 
	 * @param jurisdictionList List
	 */
	public void setJurisdictionList(List jurisdictionList) {
		this.jurisdictionList = jurisdictionList;
	}
	
	/**
	 * Returns the mandateSearchResultList
	 * 
	 * @return mandateSearchResultList List
	 */
	public List getMandateSearchResultList() {
		return mandateSearchResultList;
	}
	
	/**
	 * Sets the mandateSearchResultList
	 * 
	 * @param mandateSearchResultList  List
	 */
	public void setMandateSearchResultList(List mandateSearchResultList) {
		this.mandateSearchResultList = mandateSearchResultList;
	}
	
	/**
	 * Returns the mandateType
	 * 
	 * @return mandateType String 
	 */
	public String getMandateType() {
		return mandateType;
	}
	
	/**
	 * Sets the mandateType
	 * 
	 * @param mandateType String 
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	
	/**
	 * Returns the validationMessages
	 * 
	 * @return validationMessages List
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	
	/**
	 * Sets the validationMessages
	 * 
	 * @param validationMessages List
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	
	/**
	 * Returns the mandateName.
	 * 
	 * @return mandateName String
	 */
	public String getMandateName() {
		return mandateName;
	}
	
	/**
	 * Sets the mandateName
	 * 
	 * @param mandateName String
	 */
	public void setMandateName(String mandateName) {
		this.mandateName = mandateName;
	}
	
	/**
	 * Returns the versionNo.
	 * 
	 * @return versionNo int
	 */
	public int getVersionNo() {
		return versionNo;
	}
	
	/**
	 * Sets the versionNo.
	 * 
	 * @param versionNo int
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	
	/**
	 * Returns the messages.
	 * 
	 * @return messages List
	 */
	public List getMessages() {
		return messages;
	}
	
	/**
	 * Sets the messages
	 * 
	 * @param messages List
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}
	
	/**
	 * Returns the checkoutFlag.
	 * 
	 * @return checkoutFlag boolean
	 */
	public boolean isCheckoutFlag() {
		return checkoutFlag;
	}
	
	/**
	 * The checkoutFlag to set.
	 * 
	 * @param checkoutFlag boolean
	 */
	public void setCheckoutFlag(boolean checkoutFlag) {
		this.checkoutFlag = checkoutFlag;
	}
	
	/**
	 * Returns the action.
	 * 
	 * @return action  String
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * The action to set.
	 * 
	 * @param action String
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * Mathod to validate whether all the mandatory fields are available..
	 * 
	 * @return boolean
	 */
	private boolean validateRequiredFields() {
		validationMessages = new ArrayList();
		boolean valid = false;
		if ((null == this.citationNumber || "".equals(this.citationNumber.trim()))
				&& (null == this.effectiveDate || "".equals(this.effectiveDate.trim()))
				&& (null == this.expiryDate || "".equals(this.expiryDate.trim()))
				&& (null == this.fundingArrangement || "".equals(this.fundingArrangement.trim()))
				&& (null == this.jurisdiction || "".equals(this.jurisdiction.trim()))
				&& (null == this.mandateType || "".equals(this.mandateType.trim()))
				&& (null == this.groupSize || "".equals(this.groupSize.trim())))
			
			valid = true;
		if (valid) {
			validationMessages.add(new ErrorMessage("all.fields.blank"));
			mandateSearchResultList = null;
			return false;
		}
		return true;
	}
	
	/**
	 * Method to check the given date are valid.
	 * 
	 * @return boolean
	 */
	private boolean isAllDatessValid() {
		
		if (!(WPDStringUtil.isValidDate(this.effectiveDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Effective Date" });
			validationMessages.add(errorMessage);
			invalidFieldMessage = true;
			effectiveDateInvalid = true;
		} else if (!(WPDStringUtil.isValidDate(this.expiryDate))) {
			
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Expiry Date" });
			validationMessages.add(errorMessage);
			invalidFieldMessage = true;
			expiryDateInvalid = true;
		} else if (WPDStringUtil.isValidDate(this.effectiveDate)
				&& WPDStringUtil.isValidDate(this.expiryDate)) {
			dateOfEffective = new Date();
			dateOfEffective = WPDStringUtil
			.getDateFromString(this.effectiveDate);
			dateOfExpiry = new Date();
			dateOfExpiry = WPDStringUtil.getDateFromString(this.expiryDate);
			if (effectiveDate.compareTo(expiryDate) > 0
					&& (!"".equals(this.expiryDate))) {
				validationMessages.add(new ErrorMessage(
						WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
				invalidFieldMessage = true;
				effectiveDateInvalid = true;
				expiryDateInvalid = true;
			}
		}
		if (effectiveDateInvalid || expiryDateInvalid ) {
			return false;
			
		} else {
			return true;
		}
	}
	
	/**
	 * Method to send mandate for test.
	 * 
	 * @return returnMessage String
	 */
	public String sendForTest() {
		String returnMessage = null;
		MandateSendForTestRequest mandateSendForTestRequest = (MandateSendForTestRequest) this
		.getServiceRequest(ServiceManager.SEND_FOR_TEST_MANDATE_REQUEST);
		//Sets values from backing bean to the request.
		mandateSendForTestRequest.getMandateVO().setMandateId(this.getMandateId());
		mandateSendForTestRequest.getMandateVO().setMandateName(this.getMandateName());
		mandateSendForTestRequest.getMandateVO().setVersion(this.getVersionNo());
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse) executeService(mandateSendForTestRequest);
		if(null != retrieveMandateResponse){
			this.setMessages(retrieveMandateResponse.getMessages());
			if("viewAll".equals(this.getAction())){
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}else{
				returnMessage = this.locateMandate();
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
			}
		}else{
			if("viewAll".equals(this.getAction())){
				messages = (List)(getRequest().getAttribute("messages"));
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}
		}
		return returnMessage;
	}
	
	/**
	 * Method to publish a Mandate.
	 * 
	 * @return String returnMessage
	 */
	public String publish() {
		String returnMessage = null;
		MandatePublishRequest mandatePublishRequest = (MandatePublishRequest) this
		.getServiceRequest(ServiceManager.PUBLISH_MANDATE_REQUEST);
		//Sets values from backing bean to the request.
		mandatePublishRequest.getMandateVO().setMandateId(this.getMandateId());
		mandatePublishRequest.getMandateVO().setMandateName(this.getMandateName());
		mandatePublishRequest.getMandateVO().setVersion(this.getVersionNo());
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse) executeService(mandatePublishRequest);
		if(null != retrieveMandateResponse){
			this.setMessages(retrieveMandateResponse.getMessages());
			if("viewAll".equals(this.getAction())){
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}else{
				returnMessage = this.locateMandate();
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
			}
		}else{
			if("viewAll".equals(this.getAction())){
				messages = (List)(getRequest().getAttribute("messages"));
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}
		}
		return returnMessage;
	}
	
	/**
	 * Method to pass mandate test.
	 * 
	 * @return String returnMessage
	 */
	public String testPass(){
		String returnMessage = null;
		MandateTestPassRequest mandateTestPassRequest = (MandateTestPassRequest)this.getServiceRequest(ServiceManager.TEST_PASS_MANDATE_REQUEST);
		//Sets values from backing bean to the request.		
		mandateTestPassRequest.getMandateVO().setMandateId(this.getMandateId());
		mandateTestPassRequest.getMandateVO().setMandateName(this.getMandateName());
		mandateTestPassRequest.getMandateVO().setVersion(this.getVersionNo());
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)executeService(mandateTestPassRequest);
		if(null != retrieveMandateResponse){
			this.setMessages(retrieveMandateResponse.getMessages());
			if("viewAll".equals(this.getAction())){
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}else{
				returnMessage = this.locateMandate();
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
			}
		}else{
			if("viewAll".equals(this.getAction())){
				messages = (List)(getRequest().getAttribute("messages"));
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}
		}
		return returnMessage;
	}
	
	/**
	 * Method to make a mandate test fail.
	 * 
	 * @return returnMessage String 
	 */
	public String testFail(){
		String returnMessage = null;
		MandateTestFailRequest mandateTestFailRequest = (MandateTestFailRequest)this.getServiceRequest(ServiceManager.TEST_FAIL_MANDATE_REQUEST);
		//Sets values from backing bean to the request.		
		mandateTestFailRequest.getMandateVO().setMandateId(this.getMandateId());
		mandateTestFailRequest.getMandateVO().setMandateName(this.getMandateName());
		mandateTestFailRequest.getMandateVO().setVersion(this.getVersionNo());
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)executeService(mandateTestFailRequest);
		if(null != retrieveMandateResponse){
			this.setMessages(retrieveMandateResponse.getMessages());
			if("viewAll".equals(this.getAction())){
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}else{
				returnMessage = this.locateMandate();
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
			}
		}else{
			if("viewAll".equals(this.getAction())){
				messages = (List)(getRequest().getAttribute("messages"));
				addAllMessagesToRequest(messages);
				this.getSession().setAttribute("information",messages);
			}
		}
		return returnMessage;
	}
	

}