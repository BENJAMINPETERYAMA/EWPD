/*
 * ContractCoverageNotesOverrideBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.NotesAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.NotesAttachmentResponse;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentOverrideVO;
import com.wellpoint.wpd.common.product.request.ProductBenefitLineOverrideNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.response.ProductLineOverrideNotesResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductLineNotesOverrideBackingBean extends ProductBackingBean {

    private List notes;

    private List viewNotes;

    private String selectedNotes;

    private String primaryEntityType;

    private int secEntityId;

    private boolean checkedNoteId = true;
    
    private List notesObj;
    
    private String loadNotes;
    
    private int tierSysId;
    
   

	/**
	 * @return Returns the notesObj.
	 */
	public List getNotesObj() {
		return notes;
	}
	/**
	 * @param notesObj The notesObj to set.
	 */
	public void setNotesObj(List notesObj) {
		this.notes = notesObj;
	}
    /**
     * Returns the viewNotes
     * 
     * @return List viewNotes.
     */
    public List getViewNotes() {
        List noteList = null;

        // Get the request object with the values set in it
        NotesAttachmentRequest notesLookUpRequest = getNotesOverrideLookUpRequest();

        notesLookUpRequest.setAction(1);

        // Call the executeService() to get the response
        NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse) this
                .executeService(notesLookUpRequest);

        // Set the response in the backing bean property

        if (null != notesLookUpResponse.getNotesList()
                && !notesLookUpResponse.getNotesList().isEmpty()) {

            noteList = notesLookUpResponse.getNotesList();
            this.setNotes(noteList);
        } else {
            this.setNotes(null);
        }

        return notes;

    }


    /**
     * Sets the viewNotes
     * 
     * @param viewNotes.
     */
    public void setViewNotes(List viewNotes) {
        this.viewNotes = viewNotes;
    }


    /**
     * Returns the checkedNoteId
     * 
     * @return boolean checkedNoteId.
     */
    public boolean isCheckedNoteId() {
        return checkedNoteId;
    }


    /**
     * Sets the checkedNoteId
     * 
     * @param checkedNoteId.
     */
    public void setCheckedNoteId(boolean checkedNoteId) {
        this.checkedNoteId = checkedNoteId;
    }


    /**
     * @return selectedNotes
     * 
     * Returns the selectedNotes.
     */
    public String getSelectedNotes() {
        return selectedNotes;
    }


    /**
     * @param selectedNotes
     * 
     * Sets the selectedNotes.
     */
    public void setSelectedNotes(String selectedNotes) {
        this.selectedNotes = selectedNotes;
    }


    /**
     * @return notes
     * 
     * Returns the notes.
     */
    public List getNotes() {

        List noteList = null;

        List list = (List) getRequest().getAttribute("messages");
        
        // Get the request object with the values set in it
        NotesAttachmentRequest notesLookUpRequest = getNotesOverrideLookUpRequest();
		notesLookUpRequest.setAction(4);
        
        if(0 != this.getTierSysId()){
        	notesLookUpRequest.getAttachmentOverrideVO().setTierSysId(this.getTierSysId());
        	notesLookUpRequest.setAction(101);
        }else{
        	String action = (String)getRequest().getParameter("lookUpAction");
        	if(null!=action){
        		if("4".equals(action)){
        			notesLookUpRequest.setAction(4);
        		}
        	}
        	String tierSysId = (String)getRequest().getParameter("tierSysId");
        	if(null!=tierSysId){
        		this.setTierSysId(Integer.parseInt(tierSysId));
	        	notesLookUpRequest.getAttachmentOverrideVO().setTierSysId(Integer.parseInt(tierSysId));
	        	notesLookUpRequest.setAction(101);
        	}
        }
        
        // Call the executeService() to get the response
        NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse) this
                .executeService(notesLookUpRequest);

        //BenefitLevelOverrideLocateResult locResult = new
        // BenefitLevelOverrideLocateResult();
        // Set the response in the backing bean property

        if (null != notesLookUpResponse.getNotesList()
                && !notesLookUpResponse.getNotesList().isEmpty()) {
            int size = notesLookUpResponse.getNotesList().size();
            noteList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO) (notesLookUpResponse
                        .getNotesList().get(i));
                if (overrideBO.getOverrideStatus().equals(
                        WebConstants.OVERRIDE_CONST_F)) {
                    overrideBO.setCheckedNoteId(true);
                }
                noteList.add(overrideBO);
            }

            //			this.setNotes(notesLookUpResponse.getNotesList());
            this.setNotes(noteList);
        } else {
            this.setNotes(null);
        }
        super.addAllMessagesToRequest(list);
        return notes;
    
    }


    /**
     * This method is used to set all the values in the NotesLookUpRequest
     * object
     * 
     * @return NotesLookUpRequest
     */
    private NotesAttachmentRequest getNotesOverrideLookUpRequest() {

        int secondaryEntityId = 0;
        // Get the parent entity type from the request

        String parentEntityType = getRequest().getParameter("parentEntityType");

        if (null != getRequest().getParameter("secondaryEntityId")) {
            secondaryEntityId = Integer.parseInt(getRequest().getParameter(
                    "secondaryEntityId"));
            getSession().setAttribute("lineId", new Integer(secondaryEntityId));

        }

        // Create an instance of LookUpVO
        NotesAttachmentOverrideVO lookUpVO = new NotesAttachmentOverrideVO();
        Integer secondaryId = (Integer) getSession().getAttribute("lineId");
        // Set the secondary entity id and type in the VO
        lookUpVO.setSecondaryEntityId(secondaryId.intValue());
        lookUpVO.setSecondaryEntityType(WebConstants.ATTACH_BENEFIT_LINE);

        // Get the values from session and set them to the VO
        getValuesFromSession(lookUpVO, WebConstants.ATTACH_PRODUCT);

        // Get the request
        NotesAttachmentRequest notesLookUpRequest = (NotesAttachmentRequest) this
                .getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST);

        // Set the VO in the request
        notesLookUpRequest.setAttachmentOverrideVO(lookUpVO);

        // Return the request
        return notesLookUpRequest;
    }


    /**
     * Method to get the values from session for getting the notes pop up for
     * benefitlevel
     * 
     * @param lookUpVO
     */
    private void getValuesFromSession(NotesAttachmentOverrideVO lookUpVO,
            String parentEntityType) {
        // Get the session
        HttpSession httpSession = getSession();

        int entityId = 0;

        String entityType = "";

        if (parentEntityType.equals(WebConstants.ATTACH_PRODUCT)) {

            // Get the session object
            int productId =

            // Get the id from the object
            entityId = super.getProductKeyFromSession();
            entityType = WebConstants.ATTACH_PRODUCT;
            lookUpVO.setBenefitComponentId(getBenefitComponentIdFromSession());
        }

        // Get the values from the session object to the VO
        lookUpVO.setPrimaryEntityId(entityId);
        lookUpVO.setPrimaryEntityType(entityType);
    }


    /**
     * Method to save the overridden notes of the benefit level
     * 
     * @return String, the forward string
     */
    public String saveNotes() {
        List noteList = getNotes();
        List notesStringList = new ArrayList(1);
        List overriddenNotesList = null;
        boolean flag = true;

        if (null != this.getSelectedNotes() && !this.selectedNotes.equals("")) {
            // Get the id list of the selected notes
            notesStringList = WPDStringUtil.getListFromTildaString(this
                    .getSelectedNotes(), 2, 2, 2);
        }
        int stringList = notesStringList.size();

        for (int i = 0; i < stringList; i++) {
            String noteId = String.valueOf(notesStringList.get(i));
            for (int j = 0; j < noteList.size(); j++) {
                NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO) noteList
                        .get(j);
                if (overrideBO.getNoteId().equals(noteId)) {
                    overrideBO.setAttachNote(true);
                    break;
                }
            }

        }
        overriddenNotesList = new ArrayList(noteList.size());
        for (int j = 0; j < noteList.size(); j++) {
            NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO) noteList
                    .get(j);

            if (!overrideBO.isAttachNote())
                overrideBO.setOverrideStatus(WebConstants.CONST_T);
            else
                overrideBO.setOverrideStatus(WebConstants.OVERRIDE_CONST_F);
            overriddenNotesList.add(overrideBO);
        }
        // Get the request object
        ProductBenefitLineOverrideNoteRequest productBenefitLineOverrideNoteRequest = (ProductBenefitLineOverrideNoteRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_BENEFIT_LINE_OVERRIDE_NOTE_REQUEST);
        //get line id from session
        Integer secondaryId = (Integer) getSession().getAttribute("lineId");
        productBenefitLineOverrideNoteRequest
                .setAction(SaveProductComponentNoteRequest.OVERRIDE_NOTE);
        //set note list to save
        productBenefitLineOverrideNoteRequest.setNoteList(overriddenNotesList);
        //set benefit component id from session object
        productBenefitLineOverrideNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        productBenefitLineOverrideNoteRequest.setBenefitLineId(secondaryId
                .intValue());
        
        if(0!=this.getTierSysId()){
        	productBenefitLineOverrideNoteRequest.setTierSysId(this.getTierSysId());
        	productBenefitLineOverrideNoteRequest.setAction(SaveProductComponentNoteRequest.OVERRIDE_TIER_NOTE);
        }
        //call executeService classa of product business service
        ProductLineOverrideNotesResponse attachNotesResponse = (ProductLineOverrideNotesResponse) this
                .executeService(productBenefitLineOverrideNoteRequest);

        // Set the new notes list to the backing bean
        if (null != attachNotesResponse.getNotesList()
                && !attachNotesResponse.getNotesList().isEmpty()) {
            this.setNotes(attachNotesResponse.getNotesList());

        } else {
            this.setNotes(null);
        }

        return "";
    }


    /**
     * @param notes
     * 
     * Sets the notes.
     */
    public void setNotes(List notes) {
        this.notes = notes;
    }


    /**
     * @return Returns the primaryEntityType.
     */
    public String getPrimaryEntityType() {
        return primaryEntityType;
    }


    /**
     * @param primaryEntityType
     *            The primaryEntityType to set.
     */
    public void setPrimaryEntityType(String primaryEntityType) {
        this.primaryEntityType = primaryEntityType;
    }


    /**
     * @return Returns the secEntityId.
     */
    public int getSecEntityId() {
        return secEntityId;
    }


    /**
     * @param secEntityId
     *            The secEntityId to set.
     */
    public void setSecEntityId(int secEntityId) {
        this.secEntityId = secEntityId;
    }
    
    
	/**
	 * @return Returns the loadNotes.
	 */
	public String getLoadNotes() {
	    getNotes();
		return "";
	}
	/**
	 * @param loadNotes The loadNotes to set.
	 */
	public void setLoadNotes(String loadNotes) {
		this.loadNotes = loadNotes;
	}
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
}

