/*
 * MandateBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.mandate.service;

import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.mandate.locateCriteria.MandateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.common.mandate.request.CheckOutMandateRequest;
import com.wellpoint.wpd.common.mandate.request.CopyMandateRequest;
import com.wellpoint.wpd.common.mandate.request.CreateMandateRequest;
import com.wellpoint.wpd.common.mandate.request.DeleteMandateRequest;
import com.wellpoint.wpd.common.mandate.request.LocateMandateRequest;
import com.wellpoint.wpd.common.mandate.request.MandateCheckInRequest;
import com.wellpoint.wpd.common.mandate.request.MandatePublishRequest;
import com.wellpoint.wpd.common.mandate.request.MandateSendForTestRequest;
import com.wellpoint.wpd.common.mandate.request.MandateTestFailRequest;
import com.wellpoint.wpd.common.mandate.request.MandateTestPassRequest;
import com.wellpoint.wpd.common.mandate.request.RetrieveMandateRequest;
import com.wellpoint.wpd.common.mandate.request.ViewAllMandateRequest;
import com.wellpoint.wpd.common.mandate.response.CheckOutMandateResponse;
import com.wellpoint.wpd.common.mandate.response.CopyMandateResponse;
import com.wellpoint.wpd.common.mandate.response.CreateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.DeleteMandateResponse;
import com.wellpoint.wpd.common.mandate.response.LocateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.MandateCheckInResponse;
import com.wellpoint.wpd.common.mandate.response.RetrieveMandateResponse;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;

import java.util.ArrayList;
import java.util.List;


/**
 * Business Service class for Mandate
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateBusinessService extends WPDService {
	
	/**
	 * Default constructor
	 *  
	 */
	public MandateBusinessService() {
	}
	
	/**
	 * Implementation of the method of the super class.
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request WPDRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		
		return null;
	}
	
	/**
	 * Saves the Mandate details.
	 * 
	 * @param createMandateRequest CreateMandateRequest
	 * @return createMandateResponse CreateMandateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(CreateMandateRequest createMandateRequest)
	throws ServiceException {
		
		Logger.logInfo("Entering execute method for creating Mandate");
		//Creating CreateMandateResponse object
		CreateMandateResponse createMandateResponse = (CreateMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.CREATE_MANDATE_RESPONSE);
		try {
			//Creating MandateBO object by coping the values from the MandateVO
			MandateBO mandateBO = copyBusinessObjectFromValueObject(createMandateRequest
					.getMandateVO());
			
			//Creating BusinessObjectManager object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			//MandateBusinessObjectBuilder mandateBusinessObjectBuilder = new
			// MandateBusinessObjectBuilder();
			
			if (createMandateRequest.isCreateFlag()) {
				createMandateResponse = (CreateMandateResponse) new ValidationServiceController()
				.execute(createMandateRequest);
				if (createMandateResponse.isErrorFlag()) {
					createMandateResponse.setMandateVO(createMandateRequest
							.getMandateVO());
					return createMandateResponse;
				}
				businessObjectManager.persist(mandateBO, createMandateRequest
						.getUser(), true);
				
				createMandateResponse.addMessage(new InformationalMessage(
				"mandate.save.success.info"));
			} else {
				businessObjectManager.persist(mandateBO, createMandateRequest
						.getUser(), false);
				createMandateResponse.addMessage(new InformationalMessage(
				"mandate.update.success.info"));
			}
			
			mandateBO = (MandateBO) businessObjectManager.retrieve(mandateBO,
					createMandateRequest.getUser());
			
			//Creating MandateVO object by coping the values from the MandateBO
			MandateVO mandateVO = copyValueObjectFromBusinessObject(mandateBO);
			
			//Setting the new MandateVO object to the CreateMandateResponse
			createMandateResponse.setMandateVO(mandateVO);
			
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList();
			logParameters.add(createMandateRequest);
			String logMessage = "Failed while processing CreateMandateRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		 Logger
          .logInfo("Returning execute method for creating Mandate");
		return createMandateResponse;
	}
	
	/**
	 * Retrieves the Mandate details.
	 * 
	 * @param retrieveMandateRequest RetrieveMandateRequest
	 * @return retrieveMandateResponse WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveMandateRequest retrieveMandateRequest)
	throws ServiceException {
		
		Logger.logInfo("Entering execute method for retrieving Mandate");
		//Creating RetrieveMandateResponse object
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		try {
			
			//Creating MandateBO object by coping the values from the object
			MandateBO mandateBO = copyBusinessObjectFromValueObject(retrieveMandateRequest
					.getMandateVO());
			
			// Creating BusinessObjectManagerFactory object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			//Retrieving the Mandate details from thr database.
			mandateBO = (MandateBO) businessObjectManager.retrieve(mandateBO,
					retrieveMandateRequest.getUser());
			
			//Creating MandateVO object by coping the values from the MandateBO
			MandateVO mandateVO = copyValueObjectFromBusinessObject(mandateBO);
			
			//Setting the new MandateVO object to the CreateMandateResponse
			retrieveMandateResponse.setMandateVO(mandateVO);
			
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList();
			logParameters.add(retrieveMandateRequest);
			String logMessage = "Failed while processing CreateMandateRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		 Logger
          .logInfo("Returning  execute method for retrieving Mandate");
		return retrieveMandateResponse;
	}
	
	/**
	 * Method to search Mandate.
	 * 
	 * @param locateMandateRequest LocateMandateRequest
	 * @return locateMandateResponse WPDResponse
	 */
	public WPDResponse execute(LocateMandateRequest locateMandateRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method for locating Mandate");
		int searchResultCount = 0;
		List mandateSearchResultList = null;
		List errorList = null;
		LocateResults locateResults = null;
		
		LocateMandateResponse locateMandateResponse = (LocateMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.LOCATE_MANDATE_RESPONSE);
		
		try {
			
			//Creating a MandateLocateCriteria object
			MandateLocateCriteria mandateLocateCriteria = getMandateSearchObject(locateMandateRequest);
			
			//Creating BusinessObjectManager instance.
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			//Get the locateResults from the businessObjectManager
			locateResults = businessObjectManager.locate(mandateLocateCriteria,
					locateMandateRequest.getUser());
			mandateSearchResultList = locateResults.getLocateResults();
			searchResultCount = mandateSearchResultList.size();
			errorList = new ArrayList();
			
			if (mandateSearchResultList.size() > 0) {
				
				//Setting the search results to the response
				locateMandateResponse
				.setMandateSearchResultList(mandateSearchResultList);
				
			} else if (mandateSearchResultList.size() == 0
					&& searchResultCount == 0) {
				
				
				//Setting thr error message to the response
				errorList.add(new InformationalMessage(
				"mandate.searchresult.notfound"));
				locateMandateResponse.setMessages(errorList);
			} 
			if(searchResultCount>mandateLocateCriteria.getMaximumResultSize()){
				mandateSearchResultList.remove(mandateLocateCriteria.getMaximumResultSize());
				locateMandateResponse.setMandateSearchResultList(mandateSearchResultList);
				errorList.add(new InformationalMessage(
						BusinessConstants.SEARCH_RESULT_EXCEEDS));
				locateMandateResponse.setMessages(errorList);
			}
			
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList();
			logParameters.add(locateMandateRequest);
			String logMessage = "Failed while processing LocateMandateRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
        .logInfo("Returning execute method for locating Mandate");
		return locateMandateResponse;
	}
	
	/**
	 * Method to create a MandateLocateCriteria object and to copy the search
	 * criteria values from the request to this object.
	 * 
	 * @param locateMandateRequest LocateMandateRequest
	 * @return mandateLocateCriteria MandateLocateCriteria
	 */
	private MandateLocateCriteria getMandateSearchObject(
			LocateMandateRequest locateMandateRequest) {
		
		MandateLocateCriteria mandateLocateCriteria = new MandateLocateCriteria();
		
		//Coping the values from the request to mandateLocateCriteria
		mandateLocateCriteria.setCitationNumberList(locateMandateRequest
				.getMandateVO().getCitationNumberList());
		mandateLocateCriteria.setEffectiveDate(locateMandateRequest
				.getMandateVO().getEffectiveDate());
		mandateLocateCriteria.setExpiryDate(locateMandateRequest.getMandateVO()
				.getExpiryDate());
		mandateLocateCriteria.setJurisdictionList(locateMandateRequest
				.getMandateVO().getJurisdictionList());
		mandateLocateCriteria.setGroupSizeId(locateMandateRequest
				.getMandateVO().getGroupSizeId());
		mandateLocateCriteria.setFundingArrangementId(locateMandateRequest
				.getMandateVO().getFundingArrangementId());
		mandateLocateCriteria.setMandateTypeId(locateMandateRequest
				.getMandateVO().getMandateTypeId());
		mandateLocateCriteria.setMaximumResultSize(locateMandateRequest.getMaxSize());
		return mandateLocateCriteria;
	}
	
	/**
	 * Method to perform delete Mandate Business Object.
	 * 
	 * @param deleteMandateRequest DeleteMandateRequest
	 * @return mandateDeleteResponse WPDResponse
	 */
	public WPDResponse execute(DeleteMandateRequest deleteMandateRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method for deleting Mandate");
		DeleteMandateResponse mandateDeleteResponse = (DeleteMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.DELETE_MANDATE_RESPONSE);
		try {
			
			//Creating MandateBO by coping the values from MandateVO
			MandateBO mandateBO = copyBusinessObjectFromValueObject(deleteMandateRequest
					.getMandateVO());
			
			mandateDeleteResponse = (DeleteMandateResponse) new ValidationServiceController().execute(deleteMandateRequest);
			if (mandateDeleteResponse.isErrorFlag()) {
				return mandateDeleteResponse;
			}
			
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			// Deleting the Mandate using the businessObjectManager
			if (businessObjectManager.delete(mandateBO, deleteMandateRequest
					.getUser())) {
				
				//Setting the deletion success flag in the response
				mandateDeleteResponse.setDeleteFlag(true);
				
				//Setting success messages to the rresponse
				mandateDeleteResponse.addMessage(new InformationalMessage(
				"mandate.delete.success.info"));
			}
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList();
			logParameters.add(deleteMandateRequest);
			String logMessage = "Failed while processing DeleteQuestionRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger.logInfo("Returning execute method for deleting Mandate");
		return mandateDeleteResponse;
	}
	
	
	
	/**
	 * This method checks out a mandate.A new version of the mandate checked out is created.
	 * 
	 * @param checkOutMandateRequest CheckOutMandateRequest
	 * @return checkOutMandateResponse WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(CheckOutMandateRequest checkOutMandateRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method for checking out Mandate");
		CheckOutMandateResponse checkOutMandateResponse = (CheckOutMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.CHECKOUT_MANDATE_RESPONSE);
		try{
			
			MandateBO mandateBO = copyBusinessObjectFromValueObject(checkOutMandateRequest.getMandateVO());
			// Creating BusinessObjectManagerFactory object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			//Method call for checkOut.
			mandateBO = (MandateBO) businessObjectManager.checkOut(mandateBO, checkOutMandateRequest.getUser());
			checkOutMandateResponse.setMandateBO(mandateBO);
			checkOutMandateResponse.addMessage(new InformationalMessage(
			"mandate.checkout.success.info"));
			checkOutMandateResponse.setAction(true);
			return checkOutMandateResponse;
		}
		catch (Exception e) {
			Logger.logInfo(e);
			checkOutMandateResponse.setAction(false);
			checkOutMandateResponse.addMessage(new ErrorMessage(
			"mandate.checkout.failed.info"));
		}
		Logger.logInfo("Returning execute method for checking out Mandate");
		return checkOutMandateResponse;
		
	}
	
	/**
	 * Method to check in Mandate.
	 * 
	 * @param mandateCheckInRequest MandateCheckInRequest
	 * @return mandateCheckInResponse MandateCheckInResponse
	 */
	public WPDResponse execute(MandateCheckInRequest mandateCheckInRequest)throws ServiceException{
		Logger.logInfo("Entering execute method for checking in Mandate");
		MandateCheckInResponse mandateCheckInResponse = (MandateCheckInResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.CHECKIN_MANDATE_RESPONSE);
		
		try {
			//Creating MandateCheckInResponse object
			mandateCheckInResponse = new MandateCheckInResponse();
			MandateBO mandateBO = copyBusinessObjectFromValueObject(mandateCheckInRequest.getMandateVO());
			//Creating BusinessObjectManager object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			boolean action = businessObjectManager.checkIn(mandateBO, mandateCheckInRequest.getUser());
			if(action){
				mandateCheckInResponse.setAction(true);
				mandateCheckInResponse.addMessage(new InformationalMessage(
				"mandate.checkin.success.info"));
			}else {
				mandateCheckInResponse.setAction(false);
				mandateCheckInResponse.addMessage(new ErrorMessage(
				"mandate.checkin.failed.info"));
			}
		} catch (Exception e) {
			Logger.logInfo(e);
			mandateCheckInResponse.setAction(false);
			mandateCheckInResponse.addMessage(new ErrorMessage(
			"mandate.checkin.failed.info"));
		}
		
		Logger.logInfo("Returning execute method for checking in Mandate");
		return mandateCheckInResponse;
		
	}
	
	/**
	 * Method to copy a mandate to create a new mandate.
	 * 
	 * @param copyMandateRequest CopyMandateRequest
	 * @return copyMandateResponse CopyMandateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(CopyMandateRequest copyMandateRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method for copying Mandate");
		CopyMandateResponse copyMandateResponse = (CopyMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.COPY_MANDATE_RESPONSE);
		MandateLocateCriteria mandateLocateCriteria = new MandateLocateCriteria();
		LocateResults locateResults = null;
		List mandateSearchResultList = null;
		mandateLocateCriteria.setAction("copy");
		mandateLocateCriteria.setMandateId(copyMandateRequest.getMandateId());
		
		//Creating BusinessObjectManager object
		BusinessObjectManager businessObjectManager = this
		.getBusinessObjectManager();
		try {
			locateResults = businessObjectManager.locate(mandateLocateCriteria,
					copyMandateRequest.getUser());
			mandateSearchResultList = locateResults.getLocateResults();
			
			//Setting the new MandateVO object to the CreateMandateResponse
			copyMandateResponse.setMandateSearchResultList(mandateSearchResultList);
		} catch (WPDException e) {
			Logger.logInfo(e);
			List logParameters = new ArrayList();
			logParameters.add(copyMandateRequest);
			String logMessage = "Failed while processing CopyMandateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for copying Mandate");
		return copyMandateResponse;
		
	}
	
	/**
	 * Copying the contents of a Mandate Business Object to Mandate Value
	 * Object.
	 * 
	 * @param mandateBO MandateBO
	 * @return mandateVO MandateVO
	 */
	private MandateVO copyValueObjectFromBusinessObject(MandateBO mandateBO) {
		
		MandateVO mandateVO = new MandateVO();
		
		mandateVO.setMandateId(mandateBO.getMandateId());
		mandateVO.setCitationNumber(mandateBO.getCitationNumber());
		
		mandateVO.setEffectiveDate(mandateBO.getEffectiveDate());
		mandateVO.setExpiryDate(mandateBO.getExpiryDate());
		mandateVO.setMandateTypeId(mandateBO.getMandateTypeId());
		mandateVO.setMandateTypeDesc(mandateBO.getMandateTypeDesc());
		mandateVO.setGroupSizeId(mandateBO.getGroupSizeId());
		mandateVO.setGroupSizeDesc(mandateBO.getGroupSizeDesc());
		mandateVO.setJurisdictionId(mandateBO.getJurisdictionId());
		mandateVO.setJurisdictionDesc(mandateBO.getJurisdictionDesc());
		mandateVO.setFundingArrangementId(mandateBO.getFundingArrangementId());
		mandateVO.setFundingArrangementDesc(mandateBO
				.getFundingArrangementDesc());
		mandateVO.setDescription(mandateBO.getDescription());
		mandateVO.setMandateName(mandateBO.getMandateName());
		
		mandateVO.setVersion(mandateBO.getVersion());
		mandateVO.setStatus(mandateBO.getStatus());
		mandateVO.setState(mandateBO.getState());
		mandateVO.setCreatedUser(mandateBO.getCreatedUser());
		mandateVO.setCreatedTimestamp(mandateBO.getCreatedTimestamp());
		mandateVO.setLastUpdatedUser(mandateBO.getLastUpdatedUser());
		mandateVO.setLastUpdatedTimestamp(mandateBO.getLastUpdatedTimestamp());
		return mandateVO;
	}
	
	/**
	 * Copying the contents of a Mandate Value Object to Mandate Business
	 * Object.
	 * 
	 * @param mandateVO MandateVO
	 * @return mandateBO MandateBO
	 */
	private MandateBO copyBusinessObjectFromValueObject(MandateVO mandateVO) {
		
		MandateBO mandateBO = new MandateBO();
		
		mandateBO.setMandateId(mandateVO.getMandateId());
		mandateBO.setVersion(mandateVO.getVersion());
		mandateBO.setStatus(mandateVO.getStatus());
		mandateBO.setCitationNumber(mandateVO.getCitationNumber());
		
		mandateBO.setEffectiveDate(mandateVO.getEffectiveDate());
		mandateBO.setExpiryDate(mandateVO.getExpiryDate());
		mandateBO.setMandateTypeId(mandateVO.getMandateTypeId());
		mandateBO.setMandateTypeDesc(mandateVO.getMandateTypeDesc());
		mandateBO.setGroupSizeId(mandateVO.getGroupSizeId());
		mandateBO.setGroupSizeDesc(mandateVO.getGroupSizeDesc());
		mandateBO.setJurisdictionId(mandateVO.getJurisdictionId());
		mandateBO.setJurisdictionDesc(mandateVO.getJurisdictionDesc());
		mandateBO.setFundingArrangementId(mandateVO.getFundingArrangementId());
		mandateBO.setFundingArrangementDesc(mandateVO
				.getFundingArrangementDesc());
		if(null!=mandateVO.getDescription())
			mandateBO.setDescription(mandateVO.getDescription().toUpperCase());
		if(null!=mandateVO.getMandateName())
			mandateBO.setMandateName(mandateVO.getMandateName().toUpperCase());
		
		return mandateBO;
	}
	
	/**
	 * Method to get Business Object Manager
	 * 
	 * @return bom BusinessObjectManager
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
		.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
	
	/**
	 * This method returns all versions of a mandate
	 * @param viewAllMandateRequest ViewAllMandateRequest
	 * @return retrieveMandateResponse RetrieveMandateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(ViewAllMandateRequest viewAllMandateRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for retrieving all versions of a Mandate");
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		try {
			List viewAllSearchResultsList = null;
			LocateResults searchResults = null;
			int viewAllSearchResultsCount = 0;
			BusinessObjectManager bom = this.getBusinessObjectManager();
			
			MandateLocateCriteria mandateLocateCriteria = new MandateLocateCriteria();
			mandateLocateCriteria.setMandateName(viewAllMandateRequest.getMandateVO().getMandateName());
			mandateLocateCriteria.setAction(viewAllMandateRequest.getMandateVO().getAction());
			
			searchResults = bom.locate(mandateLocateCriteria,viewAllMandateRequest.getUser()); 
			viewAllSearchResultsList = searchResults.getLocateResults();
			viewAllSearchResultsCount = searchResults.getTotalResultsCount();
			
			
			retrieveMandateResponse.setViewAllList(viewAllSearchResultsList);
			
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList();
			logParameters.add(viewAllMandateRequest);
			String logMessage = "Failed while processing ViewAllMandateRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger.logInfo("Returning execute method for retrieving all versions of a Mandate");
		return retrieveMandateResponse;
	}
	
	/**
	 * This method sends a particular mandate for test.
	 * 
	 * @param mandateSendForTestRequest MandateSendForTestRequest
	 * @return retrieveMandateResponse RetrieveMandateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(MandateSendForTestRequest mandateSendForTestRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for sending Mandate for test");
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		MandateBO mandateBO = copyBusinessObjectFromValueObject(mandateSendForTestRequest.getMandateVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		try {
			boolean status = bom.scheduleForTest(mandateBO,mandateSendForTestRequest.getUser());
			retrieveMandateResponse.addMessage(new InformationalMessage("sendForTest.mandate.success.info"));
		} catch (WPDException e) {
			Logger.logInfo(e);
			retrieveMandateResponse.addMessage(new ErrorMessage("sendForTest.mandate.faliure.info"));
			List logParameters = new ArrayList();
			logParameters.add(mandateSendForTestRequest);
			String logMessage = "Failed while processing mandateSendForTestRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for sending Mandate for test");
		return retrieveMandateResponse;
	}

	/**
	 * This method publishes a particular mandate.
	 * 
	 * @param mandatePublishRequest
	 * @return retrieveMandateResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(MandatePublishRequest mandatePublishRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for publishing Mandate");
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		MandateBO mandateBO = copyBusinessObjectFromValueObject(mandatePublishRequest.getMandateVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		try {
			boolean status = bom.publish(mandateBO,mandatePublishRequest.getUser());
			retrieveMandateResponse.addMessage(new InformationalMessage("publish.mandate.success.info"));
		} catch (WPDException e) {
			Logger.logInfo(e);
			retrieveMandateResponse.addMessage(new ErrorMessage("publish.mandate.faliure.info"));
			List logParameters = new ArrayList();
			logParameters.add(mandatePublishRequest);
			String logMessage = "Failed while processing MandatePublishRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for publishing Mandate");
		return retrieveMandateResponse;
	}
	/**
	 * This method passes a particular mandate tested.
	 * 
	 * @param mandateTestPassRequest
	 * @return retrieveMandateResponse WPDResponse
	 * @throws ServiceException
	 */
	
	public WPDResponse execute(MandateTestPassRequest mandateTestPassRequest)throws ServiceException{
		Logger.logInfo("Entering execute method for making Mandate test pass");
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		MandateBO mandateBO = copyBusinessObjectFromValueObject(mandateTestPassRequest.getMandateVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		try {
			boolean status = bom.testPass(mandateBO,mandateTestPassRequest.getUser());
			retrieveMandateResponse.addMessage(new InformationalMessage("testpass.mandate.success.info"));
		} catch (WPDException e) {
			Logger.logInfo(e);
			retrieveMandateResponse.addMessage(new ErrorMessage("testpass.mandate.faliure.info"));
			List logParameters = new ArrayList();
			logParameters.add(mandateTestPassRequest);
			String logMessage = "Failed while processing mandateTestPassRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for making Mandate test pass");
		return retrieveMandateResponse;
	}
	
	/**
	 * This method fails a particular mandate tested.
	 * 
	 * @param mandateTestFailRequest MandateTestFailRequest
	 * @return retrieveMandateResponse WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(MandateTestFailRequest mandateTestFailRequest)throws ServiceException{
		Logger.logInfo("Entering execute method for making Mandate test fail");
		RetrieveMandateResponse retrieveMandateResponse = (RetrieveMandateResponse)WPDResponseFactory.getResponse(WPDResponseFactory.RETRIEVE_MANDATE_RESPONSE);
		MandateBO mandateBO = copyBusinessObjectFromValueObject(mandateTestFailRequest.getMandateVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		try {
			boolean status = bom.testFail(mandateBO,mandateTestFailRequest.getUser());
			retrieveMandateResponse.addMessage(new InformationalMessage("testfail.mandate.success.info"));
		} catch (WPDException e) {
			Logger.logInfo(e);
			retrieveMandateResponse.addMessage(new ErrorMessage("testfail.mandate.faliure.info"));
			List logParameters = new ArrayList();
			logParameters.add(mandateTestFailRequest);
			String logMessage = "Failed while processing MandateTestFailRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for making Mandate test fail");
		return retrieveMandateResponse;
	}
	
}