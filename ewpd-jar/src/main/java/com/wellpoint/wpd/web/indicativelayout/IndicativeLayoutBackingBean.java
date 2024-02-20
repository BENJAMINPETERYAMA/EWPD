/*
 * IndicativeLayoutBackingBean.java 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.indicativelayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.util.ExcelFileUtility;
import com.wellpoint.wpd.common.indicativemapping.request.ConfirmImportIndicativeMappingRequest;
import com.wellpoint.wpd.common.indicativemapping.request.IndicativeDetailRequest;
import com.wellpoint.wpd.common.indicativemapping.response.ConfirmImportIndicativeMappingResponse;
import com.wellpoint.wpd.common.indicativemapping.response.IndicativeDetailResponse;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * 
 * @author U42506
 * 
 */
public class IndicativeLayoutBackingBean extends WPDBackingBean {
	private String indicativeSegment = "1";
	private String applicationEnviroment = "Test";	
	private boolean confirmDisable = false;
	private boolean displayDataTable = false;
	private String openImportPopUp = "false";
	private List validationMessages;
	private String openCopyToProdWindow = "false";
	private String operationPerformed;
	private List<IndicativeDetailVO> indicativeDetailVOList = new ArrayList<IndicativeDetailVO>();
	private String copyToProdTriggered = "false";
	private UploadedFile upload;
	
	public String getCopyToProdTriggered() {
		return copyToProdTriggered;
	}

	public void setCopyToProdTriggered(String copyToProdTriggered) {
		this.copyToProdTriggered = copyToProdTriggered;
	}

	/**
	 * To get the Indicative Segment i.e, either 1, 2, 3, 4
	 * 
	 * @return Indicative Segment
	 */
	public String getIndicativeSegment() {
		return indicativeSegment;
	}

	/**
	 * Set the Indicative Segment
	 * 
	 * @param indicativeSegment
	 */
	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}
	
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
	 * ApplicationEnviroment Test or Production
	 * 
	 * @return "Test" or "Prod"
	 */
	public String getApplicationEnviroment() {
		return applicationEnviroment;
	}

	/**
	 * Set the ApplicationEnviroment i.e, "Test" or "Production"
	 * 
	 * @param applicationEnviroment
	 */
	public void setApplicationEnviroment(String applicationEnviroment) {
		this.applicationEnviroment = applicationEnviroment;
	}

	
	public boolean isConfirmDisable() {
		return confirmDisable;
	}

	public void setConfirmDisable(boolean confirmDisable) {
		this.confirmDisable = confirmDisable;
	}

	public String getOperationPerformed() {
		return operationPerformed;
	}

	public void setOperationPerformed(String operationPerformed) {
		this.operationPerformed = operationPerformed;
	}	
	
	public List<IndicativeDetailVO> getIndicativeDetailVOList() {
		return indicativeDetailVOList;
	}

	public void setIndicativeDetailVOList(
			List<IndicativeDetailVO> indicativeDetailVOList) {
		this.indicativeDetailVOList = indicativeDetailVOList;
	}

	/**
	 * This method will be called onclick of "confirm" button from the UI
	 * 
	 * @return
	 * @throws SevereException 
	 */
	public String confirmIndicativeMapping() throws SevereException {
		Map<String, List<IndicativeDetailVO>> processedList = (Map<String, List<IndicativeDetailVO>>) getSession()
				.getAttribute("processedDetails");
		ConfirmImportIndicativeMappingRequest confirmImportRequest = (ConfirmImportIndicativeMappingRequest) this
				.getServiceRequest(ServiceManager.CONFIRM_INDICATIVE_REQUEST);		
		String segmentId = (String) getSession().getAttribute("segmentNo");
		String region = (String) getSession().getAttribute("region");
		confirmImportRequest.setIndicativeSegmentNumber(segmentId);
		confirmImportRequest.setRegion(region.toUpperCase());
		confirmImportRequest.setUser(getUser());
		confirmImportRequest.setProcessedExcelIndicativeSeg(processedList);
		ConfirmImportIndicativeMappingResponse confirmImportResponse = (ConfirmImportIndicativeMappingResponse) this
				.executeService(confirmImportRequest);
		confirmDisable = true;
		if (confirmImportResponse.getMessages() != null
				&& confirmImportResponse.getMessages() != null
				&& confirmImportResponse.getMessages().size() > 0) {
			List<Message> errorMsgs = confirmImportResponse.getMessages();
			for (Message errMsg : errorMsgs) {
				addMessageToRequest(errMsg);
			}
			setBreadCrumbText("Administration >> Indicative Layout >> Create");

		}
		displayDataTable = false;  
		getSession().removeAttribute("segmentNo");
		getSession().removeAttribute("region");
		getSession().removeAttribute("processedDetails");
		getSession().removeAttribute("operationPerformed");
		return "";
	}

	
	public String getOpenImportPopUp() {
		return openImportPopUp;
	}

	public void setOpenImportPopUp(String openImportPopUp) {
		this.openImportPopUp = openImportPopUp;
	}

	public String getOpenCopyToProdWindow() {
		return openCopyToProdWindow;
	}

	public void setOpenCopyToProdWindow(String openCopyToProdWindow) {
		this.openCopyToProdWindow = openCopyToProdWindow;
	}
	
	/**
	 * This method gets triggered when either import or copy to prod is clicked from
	 * create indicative layout mapping page
	 * @return String
	 */
	public String validateImportOrCopyToProd(){
		openImportPopUp = "false";
		openCopyToProdWindow = "false";
		String region = getApplicationEnviroment().toUpperCase();		
		List<ErrorMessage> errorMessageList = null;
		if(operationPerformed.equalsIgnoreCase(BusinessConstants.INDICATIVE_MAPPING_OPERATION_PERFORMED_IMPORT)){
			if(!region.equalsIgnoreCase(BusinessConstants.REGION_TEST)){
				errorMessageList = new ArrayList<ErrorMessage>();
				errorMessageList.add(new ErrorMessage("INVALID_REGION_IMPORT"));				
			}else{
				openImportPopUp = "true";
			}
		}else if(operationPerformed.equalsIgnoreCase(BusinessConstants.INDICATIVE_MAPPING_OPERATION_PERFORMED_COPY_TO_PROD)){
			if(!region.equalsIgnoreCase(BusinessConstants.REGION_PROD)){
				errorMessageList = new ArrayList<ErrorMessage>();
				errorMessageList.add(new ErrorMessage("INVALID_REGION_COPY_TO_PROD"));				
			}else{
				openCopyToProdWindow = "true";
			}
		}
		if(errorMessageList != null && errorMessageList.size() > 0){
			addMessageToRequest(errorMessageList.get(0));		
		}else{
			getSession().setAttribute("segmentNo",indicativeSegment);	
			getSession().setAttribute("region", region.toUpperCase());
			getSession().setAttribute("operationPerformed", operationPerformed);
		}
		return "";
	}
	
	
	public String triggerCopyToProd() throws SevereException{
		return indicativeSegmentImportOrCopyToProd();
		
	}
	
	/**
	 * This method gets triggered when upload button is clicked from import upload button or 
	 * when copy to prod functionality is triggered
	 * @return String
	 * @throws SevereException 
	 */
	public String indicativeSegmentImportOrCopyToProd() throws SevereException{		
		String returnString = "";
		Map<Integer, List<? extends Object>> processedResultFromExcel = null;
		boolean isValidationExists = false;
		String operationPerformed = (String)getSession().getAttribute("operationPerformed");
		String region = (String)getSession().getAttribute("region");
		String indicativeSegment = (String)getSession().getAttribute("segmentNo");
		IndicativeDetailRequest indicativeDetailRequest = null;
		List<IndicativeDetailVO> processedIndicativeDetailVOList = null;
		List<ErrorMessage> validationMessages = null;
		if(operationPerformed.equalsIgnoreCase(BusinessConstants.INDICATIVE_MAPPING_OPERATION_PERFORMED_IMPORT)){
			returnString = "confirmImport";
			validationMessages = validateImportFileUpload(upload);
			if(validationMessages != null && validationMessages.size()>0){
				isValidationExists = true;
				for (ErrorMessage errorMessage : validationMessages) {
					addMessageToRequest(errorMessage);
				}
				returnString = "";
			}else{
				processedResultFromExcel = ExcelFileUtility.processExcelToBeImported(upload,
						indicativeSegment,region.toUpperCase()); 
				validationMessages = 
					(List<ErrorMessage>)processedResultFromExcel.get(BusinessConstants.VALIDATIONS_WHILE_PROCESSING_IMPORT_EXCEL);
				if(validationMessages != null && validationMessages.size()>0){
					isValidationExists = true;
					for (ErrorMessage errorMessage : validationMessages) {
						addMessageToRequest(errorMessage);
					}
					returnString = "";
				}else{
					processedIndicativeDetailVOList = 
						(List<IndicativeDetailVO>)processedResultFromExcel.get(BusinessConstants.CORRECT_INDICATIVE_CODES);
				}
			}
		}else{
			returnString = "confirmCopyToProd";
		}
		if(!isValidationExists){
			indicativeDetailRequest = (IndicativeDetailRequest) this
			.getServiceRequest(ServiceManager.INDICATIVE_DETAIL_REQUEST);
			indicativeDetailRequest.setInidcativeDetailListToCompare(processedIndicativeDetailVOList);
			indicativeDetailRequest.setIndicativeSegment(indicativeSegment);
			indicativeDetailRequest.setRegion(region.toUpperCase());
			indicativeDetailRequest.setUser(getUser());
			indicativeDetailRequest.setAction(operationPerformed);
			IndicativeDetailResponse indicativeDetailResponse = (IndicativeDetailResponse) this
			.executeService(indicativeDetailRequest);
			
			validationMessages = indicativeDetailResponse.getErrorMessageList();
			if(validationMessages != null && validationMessages.size()>0){
				isValidationExists = true;
				for (ErrorMessage errorMessage : validationMessages) {
					addMessageToRequest(errorMessage);
				}
			}else{
				Map<String, List<IndicativeDetailVO>> processedDetails = indicativeDetailResponse.getProcessedDetailsForConfirmation();
				if(processedDetails != null){
					getConfirmationMessage(processedDetails);
				}
				if (processedDetails == null || this.indicativeDetailVOList == null || this.indicativeDetailVOList.size() == 0){
					InformationalMessage informationMessage = new InformationalMessage(BusinessConstants.NO_CHANGES_IDENTIFIED);
					addMessageToRequest(informationMessage);
					confirmDisable = true;
					displayDataTable = false;
				}else{
					displayDataTable = true;
					getSession().setAttribute("processedDetails", processedDetails);
				}
			}			
		}
		return returnString;
	}
	
	/**
	 * This method creates the confirmation message to be displayed in the confirmation page
	 * @param indiSegCodes
	 * @return
	 */
	private void getConfirmationMessage(Map<String, List<IndicativeDetailVO>> processedDetails) {	
		List<IndicativeDetailVO> indicativeDetailVOList  =  processedDetails.get(BusinessConstants.ADDED_INDICATIVES);		
		if(indicativeDetailVOList != null && indicativeDetailVOList.size()>0){
			setActionForConfirmationDisplay(indicativeDetailVOList,WebConstants.INDICATIVE_SEGMENT_ITEM_ADDED);
			this.indicativeDetailVOList.addAll(indicativeDetailVOList);
		}
		
		indicativeDetailVOList = processedDetails.get(BusinessConstants.UPDATED_INDICATIVES);
		if(indicativeDetailVOList != null && indicativeDetailVOList.size()>0){
			setActionForConfirmationDisplay(indicativeDetailVOList,WebConstants.INDICATIVE_SEGMENT_ITEM_UPDATED);
			this.indicativeDetailVOList.addAll(indicativeDetailVOList);
		}
		indicativeDetailVOList = processedDetails.get(BusinessConstants.DELETED_INDICATIVES);
		if(indicativeDetailVOList != null && indicativeDetailVOList.size()>0){
			setActionForConfirmationDisplay(indicativeDetailVOList,WebConstants.INDICATIVE_SEGMENT_ITEM_DELETED);
			this.indicativeDetailVOList.addAll(indicativeDetailVOList);
		}
	}
		
	/**
	 * This private method sets the action for received list after processing.
	 * @param indicativeDetailsVoList
	 * @param action
	 */
	private void setActionForConfirmationDisplay(List<IndicativeDetailVO> indicativeDetailsVoList, String action){
		for (IndicativeDetailVO indicativeDetailVO : indicativeDetailsVoList) {
			indicativeDetailVO.setAction(action);
		}
	}

	/**
	 * validating the uploaded file while doing import
	 * @return List<ErrorMessage>
	 */
	private List<ErrorMessage> validateImportFileUpload(UploadedFile upload){
		List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
		ErrorMessage errorMessage = null;
		String fileName = null;
		if (null == upload || null == upload.getName()  || upload.getName().isEmpty()) {
			errorMessage = new ErrorMessage(BusinessConstants.FILENAME_MANDATORY);	
			errorMessageList.add(errorMessage);
		}else{
			fileName = upload.getName();
			if (!(fileName.endsWith(".xls"))) {
				errorMessage = new ErrorMessage(BusinessConstants.INVALID_FILE);
				errorMessage.setParameters(new String[] { fileName });
				errorMessageList.add(errorMessage);
			}				
		}	
		return errorMessageList;
	}

	public boolean isDisplayDataTable() {
		return displayDataTable;
	}

	public void setDisplayDataTable(boolean displayDataTable) {
		this.displayDataTable = displayDataTable;
	}

	public UploadedFile getUpload() {
		return upload;
	}

	public void setUpload(UploadedFile upload) {
		this.upload = upload;
	}
}
