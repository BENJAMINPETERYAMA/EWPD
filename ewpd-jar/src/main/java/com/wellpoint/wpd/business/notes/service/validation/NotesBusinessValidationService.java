/*
 * Created on May 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.service.validation;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.notes.builder.NotesBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.request.CreateNotesRequest;
import com.wellpoint.wpd.common.notes.request.NotesCopyRequest;
import com.wellpoint.wpd.common.notes.response.CreateNotesResponse;
import com.wellpoint.wpd.common.notes.response.NotesCopyResponse;
import com.wellpoint.wpd.common.notes.vo.NotesVO;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesBusinessValidationService extends WPDService {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Validation method for CreateMandateRequest.
	 * 
	 * @param request
	 *            CreateMandateRequest
	 * @return WPDResponse CreateMandateResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(CreateNotesRequest createNotesRequest)
			throws ServiceException {

		CreateNotesResponse createNotesResponse = new CreateNotesResponse();

		NoteBO noteBO = copyBusinessObjectFromRequestObject(createNotesRequest);
		List messageList = new ArrayList();
		boolean valid = true;
		try {
			if(createNotesRequest.isCreateFlag()){
				if (isNoteDuplicate(noteBO, createNotesRequest
						.getUser())) {
					messageList.add(new ErrorMessage(
							"notes.already.present.info"));
					createNotesResponse.setMessages(messageList);
					createNotesResponse.setErrorFlag(true);
				}
			}else{
				if(isNoteDuplicate(noteBO, createNotesRequest
						.getUser())){
					messageList.add(new ErrorMessage(
					"notes.already.present.info"));
					createNotesResponse.setMessages(messageList);
					createNotesResponse.setErrorFlag(true);
				}else{
					NoteBO oldNoteBO = copyOldBusinessObjectFromRequestObject(createNotesRequest);
	        	    //checks the key values have been changed
	        	    if(isKeyValuesChanged(noteBO,oldNoteBO)){
	        	    	createNotesResponse.setKeyValueChanged(true);
	        	        if(noteBO.getVersion() > 0) {
	        	            messageList.add(new ErrorMessage("product.key.change.invalid"));
	        	            valid = false;
	        	        }
	        	    }
	        	    createNotesResponse.setMessages(messageList);
				}
				
			}
		} catch (WPDException e) {
        	Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(createNotesRequest);
			String logMessage = "Failed while processing CreateNotesRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		createNotesResponse.setMessages(messageList);
		return createNotesResponse;
	}
    /**
     * This method checks whether the key values have been changed 
     * @param request
     * @return
     * @throws SevereException
     */
    private boolean isKeyValuesChanged(NoteBO noteBO, NoteBO oldNoteBO) throws SevereException{
        if(! oldNoteBO.getNoteName().equalsIgnoreCase(noteBO.getNoteName()))
            return true;
        return false;
    }

	/**
	 * Validation method for NotesCopyRequest.
	 * 
	 * @param request
	 *            NotesCopyRequest
	 * @return WPDResponse NotesCopyResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(NotesCopyRequest notesCopyRequest)
			throws ServiceException {

		NotesCopyResponse notesCopyResponse = new NotesCopyResponse();
		NotesVO notesVO = notesCopyRequest.getNotesVO();
		NoteBO noteBO = copyBusinessObject(notesVO);
		List messageList = new ArrayList();
		try {
			if (isNoteDuplicate(noteBO, notesCopyRequest.getUser())) {
				messageList.add(new ErrorMessage(
						"notes.already.present.info"));
				notesCopyResponse.setMessages(messageList);
				notesCopyResponse.setSuccess(true);
			}
		} catch (WPDException e) {
        	Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(notesCopyRequest);
			String logMessage = "Failed while processing CreateNotesRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		notesCopyResponse.setMessages(messageList);
		return notesCopyResponse;
	}
	

	/**
	 * This method copies the values to NoteBO from the CreateNotesRequest.
	 * @param createNotesRequest
	 * @return NoteBO noteBO
	 */
	private NoteBO copyBusinessObjectFromRequestObject(CreateNotesRequest createNotesRequest) {
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteText(createNotesRequest.getText());
		noteBO.setNoteType(createNotesRequest.getType());
		noteBO.setSystemDomain(createNotesRequest.getSystemDomain());
		if(null!=createNotesRequest.getText())
			noteBO.setNoteText(createNotesRequest.getText().toUpperCase());
		if(null!=createNotesRequest.getName())
			noteBO.setNoteName(createNotesRequest.getName().toUpperCase());
		
		return noteBO;
	}
	/**
	 * This method copies the values to NoteBO from the CreateNotesRequest.
	 * @param createNotesRequest
	 * @return NoteBO noteBO
	 */
	private NoteBO copyOldBusinessObjectFromRequestObject(CreateNotesRequest createNotesRequest) {
		NoteBO oldNoteBO = new NoteBO();
		oldNoteBO.setNoteText(createNotesRequest.getOldNotesVO().getText());
		oldNoteBO.setNoteType(createNotesRequest.getOldNotesVO().getNoteType());
		oldNoteBO.setSystemDomain(createNotesRequest.getOldNotesVO().getSystemDomainList());
		if(null!=createNotesRequest.getOldNotesVO().getText())
			oldNoteBO.setNoteText(createNotesRequest.getOldNotesVO().getText().toUpperCase());
		if(null!=createNotesRequest.getOldNotesVO().getNoteName())
			oldNoteBO.setNoteName(createNotesRequest.getOldNotesVO().getNoteName().toUpperCase());
		
		return oldNoteBO;
	}
	
	
	/**
	 * This method copies the values to NoteBO from the value object of NotesLifeCycleRequest.
	 * @param retrieveNotesRequest
	 * @return
	 */
	private NoteBO copyBusinessObject(NotesVO notesVO) {
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(notesVO.getNoteId());
		noteBO.setNoteName(notesVO.getNoteName());
		noteBO.setNoteType(notesVO.getNoteType());
		noteBO.setNoteText(notesVO.getText());
		noteBO.setSystemDomain(notesVO.getSystemDomainList());
		noteBO.setStatus(notesVO.getStatus());
		noteBO.setVersion(notesVO.getVersionNo());
		return noteBO;
	}
	
	/**
	 * Validation method for duplicate Note.
	 * 
	 * @param NoteBO
	 *            Object
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean isNoteDuplicate(NoteBO noteBO, User user)
			throws WPDException {
		NotesBusinessObjectBuilder notesObjectBuilder = new NotesBusinessObjectBuilder();
		noteBO = (NoteBO) notesObjectBuilder
		.retrieveByNoteName(noteBO, user);
		if (null == noteBO) {
			return false;
		}
		return true;
	}
	/**
	 * Validation method for duplicate Note.
	 * 
	 * @param NoteBO
	 *            Object
	 * @return boolean
	 * @throws WPDException
	 */
	public boolean isNoteDuplicateForEdit(NoteBO noteBO, User user)
			throws WPDException {
		NotesBusinessObjectBuilder notesObjectBuilder = new NotesBusinessObjectBuilder();
		noteBO = (NoteBO) notesObjectBuilder
		.retrieveByNoteNameForEdit(noteBO, user);
		if (null == noteBO) {
			return false;
		}
		return true;
	}
	
}
