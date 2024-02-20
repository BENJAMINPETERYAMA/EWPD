/*
 * NotesAttachmentBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.notes.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.lookup.locateCriteria.NotesLookUpLocateCriteria;
import com.wellpoint.wpd.business.notes.builder.NotesAttachmentBusinessObjectBuilder;
import com.wellpoint.wpd.business.product.locatecriteria.ProductComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.request.BenefitLevelNotesOverrideRequest;
import com.wellpoint.wpd.common.contract.response.ContractAttachNotesResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.BenefitLineNotesRequest;
import com.wellpoint.wpd.common.notes.request.NotesAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.BenefitLineNotesResponse;
import com.wellpoint.wpd.common.notes.response.NotesAttachmentResponse;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentOverrideVO;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentVO;
import com.wellpoint.wpd.common.override.benefit.bo.TierNoteOverRide;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentBusinessService extends WPDBusinessService {

	/**
	 * To view the details of the attached note to a component
	 * 
	 * @param notesAttachmentRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(NotesAttachmentRequest notesAttachmentRequest)
			throws WPDException {

		// Get the response object
		NotesAttachmentResponse attachmentResponse = (NotesAttachmentResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.NOTES_ATTACHMENT_RESPONSE);

		// Get the action from the request
		int action = notesAttachmentRequest.getAction();

		NotesAttachmentOverrideVO attachmentOverrideVO = null;

		//Get the VO from the request
		NotesAttachmentVO attachmentVO = notesAttachmentRequest
				.getAttachmentVO();

		//Check whether the action to be performed is view note details
		if (action == NotesAttachmentRequest.VIEW_NOTE_DESCRIPTION) {

			// Create an instance of the NoteBO
			NoteBO noteBO = new NoteBO();

			// Set the values to the BO
			noteBO.setNoteId(attachmentVO.getNoteId());

			noteBO.setNoteName(attachmentVO.getNoteName());

			noteBO.setVersion(attachmentVO.getVersion());
			noteBO.setNotesAction("notesAttachment");

			// Get the BOM
			BusinessObjectManager bom = getBusinessObjectManager();

			NoteBO noteResponseBO = null;
			try {
				// Call the builder method passing the input to get the response
				noteResponseBO = (NoteBO) bom.retrieve(noteBO,
						notesAttachmentRequest.getUser());
			} catch (Exception e) {
				Logger.logError(e);
				List logParameters = new ArrayList(2);
				logParameters.add(notesAttachmentRequest);
				String logMessage = "Failed while processing View Note Details Request";
				throw new ServiceException(logMessage, logParameters, e);
			}

			// Set the response BO to the response
			attachmentResponse.setNoteBO(noteResponseBO);
		} else {

			// Create an instance of the locateCriteria
			NotesLookUpLocateCriteria notesLookUpLocateCriteria = new NotesLookUpLocateCriteria();

			if (action != 4 && action != 6 && action != 200 && action != 555
					&& action != 666 && action !=101 && action!=102) {
				// Set the key values from the VO to the LocateCriteria
				notesLookUpLocateCriteria.setEntityId(attachmentVO
						.getEntityId());
				notesLookUpLocateCriteria.setEntityType(attachmentVO
						.getEntityType());
				notesLookUpLocateCriteria.setAvailableEntityId(attachmentVO
						.getAvailableEntityId());
				notesLookUpLocateCriteria.setAvailableEntityType(attachmentVO
						.getAvailableEntityType());
				notesLookUpLocateCriteria.setBenefitComponentId(attachmentVO
						.getBenefitComponentId());
				if (null != notesAttachmentRequest.getNoteName())
					notesLookUpLocateCriteria
							.setNoteName(notesAttachmentRequest.getNoteName());
			} else if (action == 4 || action == 6) {

				// Get the VO for benefit level override
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityId(attachmentOverrideVO
						.getSecondaryEntityId());
				notesLookUpLocateCriteria.setEntityType(attachmentOverrideVO
						.getSecondaryEntityType());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getPrimaryEntityId());
				notesLookUpLocateCriteria
						.setAvailableEntityType(attachmentOverrideVO
								.getPrimaryEntityType());
				notesLookUpLocateCriteria
						.setBenefitComponentId(attachmentOverrideVO
								.getBenefitComponentId());
				//			    notesLookUpLocateCriteria.setOverrideStatus(attachmentOverrideVO.getOverrideStatus());
			} else if (action == 200) {
				//Case for retrieving the benefit line notes for datafeed
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityIdList(attachmentOverrideVO
						.getSecondaryEntityIdList());
				notesLookUpLocateCriteria.setEntityType(attachmentOverrideVO
						.getSecondaryEntityType());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getPrimaryEntityId());
				notesLookUpLocateCriteria
						.setAvailableEntityType(attachmentOverrideVO
								.getPrimaryEntityType());
				notesLookUpLocateCriteria
						.setBenefitComponentId(attachmentOverrideVO
								.getBenefitComponentId());
				notesLookUpLocateCriteria.setBenefitId(attachmentOverrideVO
						.getBenefitId());
			} else if (action == 555) {
				//Case for retrieving the contract admin option question notes
				// for datafeed
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityIdList(attachmentOverrideVO
						.getSecondaryEntityIdList());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getPrimaryEntityId());
			} else if (action == 666) {
				//Case for retrieving the benefit admin option question notes
				// for datafeed
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityIdList(attachmentOverrideVO
						.getPrimaryEntityIdList());
				notesLookUpLocateCriteria
						.setSecondaryEntityIdList(attachmentOverrideVO
								.getSecondaryEntityIdList());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getDateSegmentId());
				notesLookUpLocateCriteria
						.setBenefitComponentId(attachmentOverrideVO
								.getBenefitComponentId());
				notesLookUpLocateCriteria.setBenefitId(attachmentOverrideVO
						.getBenefitId());
			}else if (action == 101) {

				// Get the VO for benefit level override
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityId(attachmentOverrideVO
						.getSecondaryEntityId());
				notesLookUpLocateCriteria.setEntityType(attachmentOverrideVO
						.getSecondaryEntityType());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getPrimaryEntityId());
				notesLookUpLocateCriteria
						.setAvailableEntityType(attachmentOverrideVO
								.getPrimaryEntityType());
				notesLookUpLocateCriteria
						.setBenefitComponentId(attachmentOverrideVO
								.getBenefitComponentId());
				notesLookUpLocateCriteria.setTierSysId(attachmentOverrideVO.getTierSysId());
				//			    notesLookUpLocateCriteria.setOverrideStatus(attachmentOverrideVO.getOverrideStatus());
			}else if (action == 102) {

				// Get the VO for benefit level override
				attachmentOverrideVO = notesAttachmentRequest
						.getAttachmentOverrideVO();

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityId(attachmentOverrideVO
						.getSecondaryEntityId());
				notesLookUpLocateCriteria.setEntityType(attachmentOverrideVO
						.getSecondaryEntityType());
				notesLookUpLocateCriteria
						.setAvailableEntityId(attachmentOverrideVO
								.getPrimaryEntityId());
				notesLookUpLocateCriteria
						.setAvailableEntityType(attachmentOverrideVO
								.getPrimaryEntityType());
				notesLookUpLocateCriteria
						.setBenefitComponentId(attachmentOverrideVO
								.getBenefitComponentId());
				notesLookUpLocateCriteria.setTierSysId(attachmentOverrideVO.getTierSysId());
				//			    notesLookUpLocateCriteria.setOverrideStatus(attachmentOverrideVO.getOverrideStatus());
			}

			notesLookUpLocateCriteria.setAction(action);

			// Create an instance of the builder
			NotesAttachmentBusinessObjectBuilder lookupBusinessObjectBuilder = new NotesAttachmentBusinessObjectBuilder();

			List locateResults = null;
			try {

				// Call the builder method to get the values into the response
				locateResults = lookupBusinessObjectBuilder
						.locate(notesLookUpLocateCriteria);

			} catch (Exception e) {
				Logger.logError(e);
				List logParameters = new ArrayList(3);
				logParameters.add(notesAttachmentRequest);
				String logMessage = "Failed while processing notes attachment Look up Request";
				throw new ServiceException(logMessage, logParameters, e);
			}
			List messageList = new ArrayList(3);
			messageList.add(new InformationalMessage(
					BusinessConstants.MGS_NO_NOTES_AVAILABLE));
			// Set the list in the response
			if (null != locateResults && !locateResults.isEmpty())
				attachmentResponse.setNotesList(locateResults);
			else
				addMessagesToResponse(attachmentResponse, messageList);
		}

		return attachmentResponse;
	}

	/**
	 * Method to override benefit level notes attached to the product
	 * 
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(BenefitLevelNotesOverrideRequest overrideRequest)
			throws SevereException {

		// Get the response from the WPDFactory
		ContractAttachNotesResponse attachNotesResponse = (ContractAttachNotesResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.OVERRIDE_BENFITLINE_NOTES_RESPONSE);

		// Create an instance of the sub bo
		NotesAttachmentOverrideBO attachmentOverrideBO = new NotesAttachmentOverrideBO();
		TierNoteOverRide tierNoteOverRide = new TierNoteOverRide();
		// Create an instance of the locateCriteria
		NotesLookUpLocateCriteria notesLookUpLocateCriteria = new NotesLookUpLocateCriteria();
		//Create an instance of the builder
		NotesAttachmentBusinessObjectBuilder lookupBusinessObjectBuilder = new NotesAttachmentBusinessObjectBuilder();

		if ("CONTRACTOVERRIDE".equals(overrideRequest.getAction())) {

			//Set the values in the sub bo from the request
			attachmentOverrideBO.setPrimaryEntityId(overrideRequest
					.getOverrideVO().getDateSegmentId());
			attachmentOverrideBO.setPrimaryEntityType("ATTACHCONTRACT");
			attachmentOverrideBO.setSecondaryEntityId(overrideRequest
					.getOverrideVO().getSecondaryEntityId());
			attachmentOverrideBO.setSecondaryEntityType("ATTACHBNFTLINE");

			attachmentOverrideBO.setBenefitComponentId(overrideRequest
					.getOverrideVO().getBenefitComponentId());
			attachmentOverrideBO.setDateSegmentId(overrideRequest
					.getOverrideVO().getDateSegmentId());
			attachmentOverrideBO.setNotesList(overrideRequest.getNotesList());
			int dateSegmentId = overrideRequest.getOverrideVO()
					.getDateSegmentId();

			//Get the values from the VO and set them to the BO
			Contract contract = new Contract();
			contract.setContractId(overrideRequest.getContractVO()
					.getContractId());
			contract.setContractSysId(overrideRequest.getContractVO()
					.getContractSysId());
			contract.setVersion(overrideRequest.getContractVO().getVersion());

			// Get the BOM
			BusinessObjectManager bom = getBusinessObjectManager();

			// NotesAttachmentBusinessObjectBuilder builder = new
			// NotesAttachmentBusinessObjectBuilder();
			ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
			try {

				// Call the builder method to get the list of notes to override
				bom.persist(attachmentOverrideBO, contract, overrideRequest
						.getUser(), true);

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityId(overrideRequest
						.getBenefitLevelId());
				notesLookUpLocateCriteria.setEntityType("ATTACHBNFTLINE");
				notesLookUpLocateCriteria.setAvailableEntityId(overrideRequest
						.getOverrideVO().getDateSegmentId());
				notesLookUpLocateCriteria
						.setAvailableEntityType("ATTACHCONTRACT");
				notesLookUpLocateCriteria.setBenefitComponentId(overrideRequest
						.getOverrideVO().getBenefitComponentId());
				notesLookUpLocateCriteria.setAction(4);

				// Call the builder method to get the values into the response
				List locateResults = lookupBusinessObjectBuilder
						.locate(notesLookUpLocateCriteria);
				attachNotesResponse.setNotesList(locateResults);

			} catch (Exception e) {
				Logger.logError(e);
				List logParameters = new ArrayList(2);
				logParameters.add(overrideRequest);
				String logMessage = "Failed while processing BenefitLevelNotesOverrideRequest";
				throw new ServiceException(logMessage, logParameters, e);
			}
		} else if ("BCOVERRIDE".equals(overrideRequest.getAction())) {
			//Get the values from the VO and set them to the BO
			BenefitComponentBO benefitComponentBO = new BenefitComponentBO();

			benefitComponentBO.setBenefitComponentId(overrideRequest
					.getBenefitComponentVO().getBenefitComponentId());
			benefitComponentBO.setName(overrideRequest.getBenefitComponentVO()
					.getBenefitComponentName());
			// Uncomment after testing
			benefitComponentBO.setBusinessDomainList(overrideRequest
					.getBenefitComponentVO().getBusinessDomainList());
			benefitComponentBO.setVersion(overrideRequest
					.getBenefitComponentVO().getVersion());

			// Set the values in the sun bo from the request
			attachmentOverrideBO.setPrimaryEntityId(overrideRequest
					.getBenefitComponentVO().getBenefitComponentId());
			attachmentOverrideBO.setPrimaryEntityType("ATTACHCOMP");
			attachmentOverrideBO.setSecondaryEntityId(overrideRequest
					.getOverrideVO().getSecondaryEntityId());
			attachmentOverrideBO.setSecondaryEntityType("ATTACHBNFTLINE");
			attachmentOverrideBO.setBenefitComponentId(overrideRequest
					.getBenefitComponentVO().getBenefitComponentId());

			attachmentOverrideBO.setNotesList(overrideRequest.getNotesList());
			attachmentOverrideBO.setAction("BENEFITLINENOTESOVERRIDE");
			//Get the BOM
			BusinessObjectManager bom = getBusinessObjectManager();

			try {
				// Call the builder method to get the list of notes to override
				// - UNCOMMENT
				bom.persist(attachmentOverrideBO, benefitComponentBO,
						overrideRequest.getUser(), true);

				// Set values to locateCriteria for search
				notesLookUpLocateCriteria.setEntityId(overrideRequest
						.getBenefitLevelId());
				notesLookUpLocateCriteria.setEntityType("ATTACHBNFTLINE");
				notesLookUpLocateCriteria.setAvailableEntityId(overrideRequest
						.getBenefitComponentVO().getBenefitComponentId());
				notesLookUpLocateCriteria.setAvailableEntityType("ATTACHCOMP");
				notesLookUpLocateCriteria.setBenefitComponentId(overrideRequest
						.getOverrideVO().getBenefitComponentId());
				notesLookUpLocateCriteria.setAction(4);

				// Call the builder method to get the values into the response
				List locateResults = lookupBusinessObjectBuilder
						.locate(notesLookUpLocateCriteria);
				attachNotesResponse.setNotesList(locateResults);

			} catch (Exception e) {
				Logger.logError(e);
				List logParameters = new ArrayList(2);
				logParameters.add(overrideRequest);
				String logMessage = "Failed while processing BenefitLevelNotesOverrideRequest";
				throw new ServiceException(logMessage, logParameters, e);
			}
		}else if("TIEROVERRIDE".equals(overrideRequest.getAction())){
//			Set the values in the sub bo from the request
			tierNoteOverRide.setPrimaryEntityId(overrideRequest
					.getOverrideVO().getDateSegmentId());
			tierNoteOverRide.setPrimaryEntityType("ATTACHCONTRACT");
			tierNoteOverRide.setSecondaryEntityId(overrideRequest
					.getOverrideVO().getSecondaryEntityId());
			tierNoteOverRide.setSecondaryEntityType("ATTACHBNFTLINE");

			tierNoteOverRide.setBenefitComponentId(overrideRequest
					.getOverrideVO().getBenefitComponentId());
			tierNoteOverRide.setDateSegmentId(overrideRequest
					.getOverrideVO().getDateSegmentId());
			tierNoteOverRide.setNotesList(overrideRequest.getNotesList());
			tierNoteOverRide.setTierSysId(overrideRequest.getOverrideVO().getTierSysId());
			
			int dateSegmentId = overrideRequest.getOverrideVO()
					.getDateSegmentId();

			//Get the values from the VO and set them to the BO
			Contract contract = new Contract();
			contract.setContractId(overrideRequest.getContractVO()
					.getContractId());
			contract.setContractSysId(overrideRequest.getContractVO()
					.getContractSysId());
			contract.setVersion(overrideRequest.getContractVO().getVersion());

			// Get the BOM
			BusinessObjectManager bom = getBusinessObjectManager();

			// NotesAttachmentBusinessObjectBuilder builder = new
			// NotesAttachmentBusinessObjectBuilder();
			ContractBusinessObjectBuilder contractBuilder = new ContractBusinessObjectBuilder();
			try {

				// Call the builder method to get the list of notes to override
				bom.persist(tierNoteOverRide, contract, overrideRequest
						.getUser(), true);

				// Set the key values to the locateCriteria
				notesLookUpLocateCriteria.setEntityId(overrideRequest
						.getBenefitLevelId());
				notesLookUpLocateCriteria.setEntityType("ATTACHBNFTLINE");
				notesLookUpLocateCriteria.setAvailableEntityId(overrideRequest
						.getOverrideVO().getDateSegmentId());
				notesLookUpLocateCriteria
						.setAvailableEntityType("ATTACHCONTRACT");
				notesLookUpLocateCriteria.setBenefitComponentId(overrideRequest
						.getOverrideVO().getBenefitComponentId());
				notesLookUpLocateCriteria.setTierSysId(overrideRequest.getOverrideVO().getTierSysId());
				notesLookUpLocateCriteria.setAction(101);

				// Call the builder method to get the values into the response
				List locateResults = lookupBusinessObjectBuilder
						.locate(notesLookUpLocateCriteria);
				attachNotesResponse.setNotesList(locateResults);

			} catch (Exception e) {
				Logger.logError(e);
				List logParameters = new ArrayList(2);
				logParameters.add(overrideRequest);
				String logMessage = "Failed while processing BenefitLevelNotesOverrideRequest";
				throw new ServiceException(logMessage, logParameters, e);
			}
		}

		List messageList = new ArrayList(3);

		messageList.add(new InformationalMessage(
				BusinessConstants.MSG_CNT_BENLINE_UPDATED));
		addMessagesToResponse(attachNotesResponse, messageList);

		// Return the response to the backing bean
		return attachNotesResponse;
	}

	/**
	 * Method to set the list of messages to response.
	 * 
	 * @param response
	 *            WPDResponse
	 * @param messages
	 *            List.
	 */
	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}

	/**
	 * Gets the bom
	 * 
	 * @return
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}

	/**
	 * To view the details of the attached note to a component
	 * 
	 * @param notesAttachmentRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(BenefitLineNotesRequest benefitLineNotesRequest)
			throws WPDException {

		Logger.logInfo("Entering execute method, request = "
				+ benefitLineNotesRequest.getClass().getName());
		// Get the response object
		User user = benefitLineNotesRequest.getUser();
		LocateResults locateResults;
		BenefitLineNotesResponse benefitLineNotesResponse = (BenefitLineNotesResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.BENEFIT_LINE_NOTES_RESPONSE);
		BusinessObjectManager bom = getBusinessObjectManager();
		if ((WebConstants.ATTACH_PRODUCT).equals(benefitLineNotesRequest
				.getPrimaryType())) {
			ProductComponentNotesLocateCriteria productComponentNotesLocateCriteria = new ProductComponentNotesLocateCriteria();
			productComponentNotesLocateCriteria.setPrimaryEntityId(Integer
					.parseInt(benefitLineNotesRequest.getPrimaryId()));
			productComponentNotesLocateCriteria
					.setPrimaryEntityType(benefitLineNotesRequest
							.getPrimaryType());
			productComponentNotesLocateCriteria.setSecondaryEntityId(Integer
					.parseInt(benefitLineNotesRequest.getSecondaryID()));
			productComponentNotesLocateCriteria
					.setSecondaryEntityType(benefitLineNotesRequest
							.getSecondaryType());
			productComponentNotesLocateCriteria.setBenefitComponentId(Integer
					.parseInt(benefitLineNotesRequest.getBenefitComponentId()));
			productComponentNotesLocateCriteria.setTierSysId(benefitLineNotesRequest.getTierSysId());
			locateResults = bom.locate(productComponentNotesLocateCriteria,
					user);
			benefitLineNotesResponse.setBenefitLineNotesList(locateResults
					.getLocateResults());
		} else if ((WebConstants.ATTACH_BENEFIT_COMP)
				.equals(benefitLineNotesRequest.getPrimaryType())) {
			BenefitComponentNotesLocateCriteria notesLocateCriteria = new BenefitComponentNotesLocateCriteria();
			notesLocateCriteria.setEntityId(Integer
					.parseInt(benefitLineNotesRequest.getPrimaryId()));
			notesLocateCriteria.setEntityType(benefitLineNotesRequest
					.getPrimaryType());
			notesLocateCriteria.setMaximumResultSize(benefitLineNotesRequest
					.getMaxResultSize());
			notesLocateCriteria.setSecEntityId(Integer
					.parseInt(benefitLineNotesRequest.getSecondaryID()));
			notesLocateCriteria.setSecEntityType(benefitLineNotesRequest
					.getSecondaryType());
			notesLocateCriteria.setBenefitComponentId(Integer
					.parseInt(benefitLineNotesRequest.getBenefitComponentId()));
			notesLocateCriteria.setAction(2);
			locateResults = bom.locate(notesLocateCriteria, user);
			benefitLineNotesResponse.setBenefitLineNotesList(locateResults
					.getLocateResults());
		}

		return benefitLineNotesResponse;
	}
}