/*
 * ContractCoverageNotesOverrideBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.contract.request.BenefitLevelNotesOverrideRequest;
import com.wellpoint.wpd.common.contract.response.ContractAttachNotesResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.NotesAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.NotesAttachmentResponse;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentOverrideVO;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.util.StringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractCoverageNotesOverrideBackingBean extends ContractBackingBean{
    
    private List notes;
    
    private List benefitComponentNotes;
    
    private List viewNotes;
    
    private String selectedNotes;
    
    private String primaryEntityType;
    
    private int secEntityId;
    
    private boolean checkedNoteId = true;
    
    private String action ;
    
    private String checkedNote = "";
    
    private String checkedNoteName = "";
   
    private List validationMessages = new ArrayList();
    
    private List legacyNotesList ;
    private List ewpdNotesList ;
    
    private List benefitComponentNoteObj;
    
    private List contractNoteObj;
    
    private List viewNotesObj;
    
    private String loadNotes;
    
    private String loadCntNotes;
    
    private int tierSysId;
    
    
    
	/**
	 * @return Returns the viewNotesObj.
	 */
	public List getViewNotesObj() {
		return notes;
	}
	/**
	 * @param viewNotesObj The viewNotesObj to set.
	 */
	public void setViewNotesObj(List viewNotesObj) {
		this.notes = viewNotesObj;
	}
	/**
	 * @return Returns the contractNoteObj.
	 */
	public List getContractNoteObj() {
		return notes;
	}
	/**
	 * @param contractNoteObj The contractNoteObj to set.
	 */
	public void setContractNoteObj(List contractNoteObj) {
		this.notes = contractNoteObj;
	}
	/**
	 * @return Returns the benefitComponentNoteObj.
	 */
	public List getBenefitComponentNoteObj() {
		return benefitComponentNotes;
	}
	/**
	 * @param benefitComponentNoteObj The benefitComponentNoteObj to set.
	 */
	public void setBenefitComponentNoteObj(List benefitComponentNoteObj) {
		this.benefitComponentNotes = benefitComponentNoteObj;
	}
	/**
	 * @return Returns the ewpdNotesList.
	 */
	public List getEwpdNotesList() {
		return ewpdNotesList;
	}
	/**
	 * @param ewpdNotesList The ewpdNotesList to set.
	 */
	public void setEwpdNotesList(List ewpdNotesList) {
		this.ewpdNotesList = ewpdNotesList;
	}
	/**
	 * @return Returns the legacyNotesList.
	 */
	public List getLegacyNotesList() {
		return legacyNotesList;
	}
	/**
	 * @param legacyNotesList The legacyNotesList to set.
	 */
	public void setLegacyNotesList(List legacyNotesList) {
		this.legacyNotesList = legacyNotesList;
	}
    public ContractCoverageNotesOverrideBackingBean(){
    	
    	super();
    }
    
    

    /**
     * Returns the checkedNote
     * @return String checkedNote.
     */
    public String getCheckedNote() {
        return checkedNote;
    }
    /**
     * Sets the checkedNote
     * @param checkedNote.
     */
    public void setCheckedNote(String checkedNote) {
        this.checkedNote = checkedNote;
    }
    /**
     * Returns the viewNotes
     * @return List viewNotes.
     */
    public List getViewNotes() {
        List noteList = new ArrayList();
        
        String tierSysId = (String)getRequest().getParameter("tierSysId");
        // Get the request object with the values set in it
         NotesAttachmentRequest notesLookUpRequest = getNotesOverrideLookUpRequest();
         notesLookUpRequest.setAction(6);
         if(null!=tierSysId){
         	notesLookUpRequest.getAttachmentOverrideVO().setTierSysId(Integer.parseInt(tierSysId));
         	notesLookUpRequest.setAction(102);
         }
         
        // Call the executeService() to get the response
		NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse)
			this.executeService(notesLookUpRequest);
        
		// Set the response in the backing bean property
		
		
		if(null != notesLookUpResponse.getNotesList() && 
				!notesLookUpResponse.getNotesList().isEmpty()){
		    
		    noteList = notesLookUpResponse.getNotesList();
		    this.setNotes(noteList);
		}else{
			this.setNotes(null);
		}
		
        return notes;       
        
    }
    /**
     * Sets the viewNotes
     * @param viewNotes.
     */
    public void setViewNotes(List viewNotes) {
        this.viewNotes = viewNotes;
    }
    /**
     * Returns the checkedNoteId
     * @return boolean checkedNoteId.
     */
    public boolean isCheckedNoteId() {
        return checkedNoteId;
    }
    /**
     * Sets the checkedNoteId
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
    	if(notes == null) {
    	List list = (List)getRequest().getAttribute("messages");
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
		NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse)
			this.executeService(notesLookUpRequest);
        
		//BenefitLevelOverrideLocateResult locResult = new BenefitLevelOverrideLocateResult();
        // Set the response in the backing bean property
		int iFlag = 0;
		List getNotesList = notesLookUpResponse.getNotesList();
		this.setCheckedNote("");
		if(null != getNotesList && 
				!getNotesList.isEmpty()){
		    int size = getNotesList.size();
		    for(int i=0;i<size;i++){
		        NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)(getNotesList.get(i));
		        if(overrideBO.getOverrideStatus().equals("F")){
		        	iFlag = 1;
		            overrideBO.setCheckedNoteId(true);
		            this.setCheckedNote(overrideBO.getNoteId());
		            this.setCheckedNoteName(overrideBO.getNoteName());
		        }
		    }
			
//			this.setNotes(notesLookUpResponse.getNotesList());
		    if(!super.isViewMode()){ // not required in view mode
		    	this.setNotes(getNotesList);
		    }
		}else{
			this.setNotes(null);
		}
		// Enhancement for legacy note migration.Need to check why the same query is executed multiple times.
		if(notes != null){
			Iterator it = notes.iterator();
			legacyNotesList = null;
			ewpdNotesList = null;
			while(it.hasNext()){
				NotesAttachmentOverrideBO note = (NotesAttachmentOverrideBO)it.next();
				if(note.getLegacyIndicator().equals("Y")){
					if(legacyNotesList == null)legacyNotesList = new ArrayList();
					legacyNotesList.add(note);
				}else{
					if(ewpdNotesList == null)ewpdNotesList = new ArrayList();
						ewpdNotesList.add(note);
				}
			}
		}
		
		if (null ==  list ){
			list =  new ArrayList();
		}		
		if(iFlag == 1 && list.size()==0){
			list.add(new InformationalMessage(WebConstants.NOTE_ATTACHED_ALREADY_BENEFITLINE));
		}		
		super.addAllMessagesToRequest(list);
    	}
        return notes;       
    } 
    

    
    /**
	 * This method is used to set all the values in the NotesLookUpRequest object
	 * @return NotesLookUpRequest
	 */
	private NotesAttachmentRequest getNotesOverrideLookUpRequest() {
		
		int secondaryEntityId = 0;
		String parentEntityType = null;
	    // Get the parent entity type from the request
			
		if(null!=getRequest().getParameter("parentEntityType")){
			parentEntityType = getRequest().getParameter("parentEntityType"); 
			if(StringUtil.regExPatterValidation(parentEntityType)){
				parentEntityType = parentEntityType; 
			getSession().setAttribute("prmryEntityType",new String(parentEntityType));
			}else{
				parentEntityType=null;
			}
		}
		
		if(null!=getRequest().getParameter("secondaryEntityId")){
		    secondaryEntityId = Integer.parseInt(getRequest().getParameter("secondaryEntityId"));
		    getSession().setAttribute("lineId",new Integer(secondaryEntityId));
		    
		}
		
		// Create an instance of LookUpVO
		NotesAttachmentOverrideVO lookUpVO = new NotesAttachmentOverrideVO();
		Integer secondaryId=(Integer)getSession().getAttribute("lineId");
		// Set the secondary entity id and type in the VO
		lookUpVO.setSecondaryEntityId(secondaryId.intValue());
		lookUpVO.setSecondaryEntityType("ATTACHBNFTLINE");
						
		// Get the  values from session and set them to the VO
		getValuesFromSession(lookUpVO);
		
		// Get the request
		NotesAttachmentRequest notesLookUpRequest = (NotesAttachmentRequest)
			this.getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST);
		
		
		// Set the VO in the request
		notesLookUpRequest.setAttachmentOverrideVO(lookUpVO);
		
		// Return the request
		return notesLookUpRequest;
	}
    
    

    
    /**
     * Method to get the values from session for getting the notes pop up for 
     * benefitlevel
     * @param lookUpVO
     */
    private void getValuesFromSession(NotesAttachmentOverrideVO lookUpVO) {
        // Get the session 
        HttpSession httpSession = getSession();
      
        int entityId = 0;
        
        String entityType = "";
        
        String parentEntityType = (String)getSession().getAttribute("prmryEntityType");
        
        if("ATTACHCONTRACT".equals(parentEntityType)){
            
            // Get the session object
            ContractSession sessionObject = (ContractSession)httpSession.getAttribute("contract");
                
            // Get the id from the object
            entityId = sessionObject.getContractKeyObject().getDateSegmentId();           
            entityType = "ATTACHCONTRACT";
            lookUpVO.setBenefitComponentId(sessionObject.getBenefitComponentId());
           
        }
        if("ATTACHCOMP".equals(parentEntityType)){
        	 // Get the session object
        	 BenefitComponentSessionObject sessionObject = (BenefitComponentSessionObject)
         	httpSession.getAttribute("benefitComponent");       
        	 
        	 
            // Get the id from the object
        	  entityId = sessionObject.getBenefitComponentId();  
        	  entityType = "ATTACHCOMP";
            lookUpVO.setBenefitComponentId(entityId);
           
        }
        // Get the values from the session object to the VO
        lookUpVO.setPrimaryEntityId(entityId);
        lookUpVO.setPrimaryEntityType(entityType);
        
        
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
     * Method to save the overridden notes of the benefit level
     * @return String, the forward string
     */
    public String saveNotes(){
        
        List noteList = getNotes();
        List notesStringList = new ArrayList();
        List overriddenNotesList = new ArrayList();
              
        if(null != this.getSelectedNotes() && !this.selectedNotes.equals("")){
	        // Get the id list of the selected notes
            notesStringList = WPDStringUtil.getListFromTildaString(this.getSelectedNotes(), 2, 2, 2) ;
        }
        int stringList = notesStringList.size();
        
        for(int i = 0; i<stringList ; i++){
            String noteId = String.valueOf(notesStringList.get(i));
            for(int j = 0 ; j < noteList.size() ; j++){
               NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)
    			noteList.get(j);
               if(overrideBO.getNoteId().equals(noteId)){
                   overrideBO.setAttachNote(true);
//                   overrideBO.setOverrideStatus("F");
                   break;
               }
             }
            
        }
        for(int j = 0 ; j < noteList.size() ; j++){
            NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)
			noteList.get(j);
            
            if(!overrideBO.isAttachNote())
                overrideBO.setOverrideStatus("T");
            else
                overrideBO.setOverrideStatus("F");
            overriddenNotesList.add(overrideBO);
        }
  
        // Get the request object
        BenefitLevelNotesOverrideRequest overrideRequest = (BenefitLevelNotesOverrideRequest)
        	this.getServiceRequest(ServiceManager.BENEFIT_LEVEL_NOTES_OVERRIDE_REQUEST);
        
        // Get the values from session and set them to the VO
        getValuesFromSessionForSave(overrideRequest);
        
        if(0!=this.getTierSysId()){
        	overrideRequest.getOverrideVO().setTierSysId(this.getTierSysId());
        	overrideRequest.setAction("TIEROVERRIDE");
        }
        	
        // Set the value in the request
        overrideRequest.setNotesList(overriddenNotesList);
                
        // Call the executeService() to get the response
        ContractAttachNotesResponse attachNotesResponse = (ContractAttachNotesResponse)
       		this.executeService(overrideRequest);
        
        List getNotesList = attachNotesResponse.getNotesList();
//        // Set the new notes list to the backing bean
        if(null != getNotesList && 
				!getNotesList.isEmpty()){
			this.setNotes(getNotesList);
			
			
		}else{
			this.setNotes(null);
		}
        
        return "";
    }
    
    public String saveBenefitCompNotes(){
        
        List noteList = getBenefitComponentNotes();
        List notesStringList = new ArrayList();
        List overriddenNotesList = new ArrayList();
              
        if(null != this.getSelectedNotes() && !this.selectedNotes.equals("")){
	        // Get the id list of the selected notes
            notesStringList = WPDStringUtil.getListFromTildaString(this.getSelectedNotes(), 2, 2, 2) ;
        }
        int stringList = notesStringList.size();
        
        for(int i = 0; i<stringList ; i++){
            String noteId = String.valueOf(notesStringList.get(i));
            for(int j = 0 ; j < noteList.size() ; j++){
               NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)
    			noteList.get(j);
               if(overrideBO.getNoteId().equals(noteId)){
                   overrideBO.setAttachNote(true);
//                   overrideBO.setOverrideStatus("F");
                   break;
               }
             }
            
        }
        for(int j = 0 ; j < noteList.size() ; j++){
            NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)
			noteList.get(j);
            
            if(!overrideBO.isAttachNote())
                overrideBO.setOverrideStatus("T");
            else
                overrideBO.setOverrideStatus("F");
            overriddenNotesList.add(overrideBO);
        }
  
        // Get the request object
        BenefitLevelNotesOverrideRequest overrideRequest = (BenefitLevelNotesOverrideRequest)
        	this.getServiceRequest(ServiceManager.BENEFIT_LEVEL_NOTES_OVERRIDE_REQUEST);
        
        // Get the values from session and set them to the VO
        getValuesFromSessionForSave(overrideRequest);
        
        // Set the value in the request
        overrideRequest.setNotesList(overriddenNotesList);
                
        // Call the executeService() to get the response
        ContractAttachNotesResponse attachNotesResponse = (ContractAttachNotesResponse)
       		this.executeService(overrideRequest);
        
        List getNotesList = attachNotesResponse.getNotesList();
//        // Set the new notes list to the backing bean
        if(null != getNotesList && 
				!getNotesList.isEmpty()){
			this.setBenefitComponentNotes(getNotesList);
			
			
		}else{
			this.setBenefitComponentNotes(null);
		}
        
        return "";
    }
    /**
     * Method to set the values in the request for saving the overrided notes list
     * for benefit level
     * @param overrideRequest
     */
    private void getValuesFromSessionForSave(BenefitLevelNotesOverrideRequest overrideRequest) {
        // Get the session
        HttpSession httpSession = getSession();
        String parentEntityType = (String)getSession().getAttribute("prmryEntityType");
        
        if("ATTACHCONTRACT".equals(parentEntityType)){
	        ContractSession contractSession = (ContractSession)httpSession.getAttribute("contract");
	        
	        ContractVO contractVO = new ContractVO();
	        contractVO.setContractSysId(contractSession.getContractKeyObject().contractSysId);
	        contractVO.setContractId(contractSession.getContractKeyObject().contractId);
	        contractVO.setVersion(contractSession.getContractKeyObject().getVersion());
	        
	        NotesAttachmentOverrideVO overrideVO = new NotesAttachmentOverrideVO();
	        overrideVO.setBenefitComponentId(contractSession.getBenefitComponentId());
	        overrideVO.setDateSegmentId(contractSession.getContractKeyObject().getDateSegmentId());
	        Integer secondaryId=(Integer)getSession().getAttribute("lineId");
	        
	        
			// Set the secondary entity id and type in the VO
	        overrideVO.setSecondaryEntityId(secondaryId.intValue());
	//        overrideVO.setSecondaryEntityId(this.secEntityId);
	        
	        // Set the vo in the request
	        overrideRequest.setContractVO(contractVO);
	        overrideRequest.setOverrideVO(overrideVO);
	        overrideRequest.setAction("CONTRACTOVERRIDE");
	        
	        
        }else if("ATTACHCOMP".equals(parentEntityType)){
        	// Get the benefit component session object
            BenefitComponentSessionObject sessionObject = (BenefitComponentSessionObject)
            	httpSession.getAttribute("benefitComponent");
            
            // Create benefitcomponent vo
            BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
            NotesAttachmentOverrideVO overrideVO = new NotesAttachmentOverrideVO();
            
            // Set the values in the VO
            benefitComponentVO.setBenefitComponentId(sessionObject.getBenefitComponentId());
            benefitComponentVO.setBenefitComponentName(sessionObject.getBenefitComponentName());
            benefitComponentVO.setBusinessDomainList(sessionObject.getBusinessDomainList());
            benefitComponentVO.setVersion(sessionObject.getBenefitComponentVersionNumber());
            
            Integer secondaryId=(Integer)getSession().getAttribute("lineId");
            overrideVO.setSecondaryEntityId(secondaryId.intValue());
            overrideRequest.setBenefitLevelId(secondaryId.intValue());
            // Set the vo in the request
            overrideRequest.setBenefitComponentVO(benefitComponentVO);
            overrideRequest.setOverrideVO(overrideVO);
            // Get the standard benefit session object
//            StandardBenefitSessionObject benefitSessionObject = (StandardBenefitSessionObject)
//            	httpSession.getAttribute("standard");
     
            // Set the standard benefit id in the request
            //overrideRequest.setStdBenefitId(benefitSessionObject.getStandardBenefitKey());
            overrideRequest.setAction("BCOVERRIDE");
        }   
    }
    
   

    
    
	/**
	 * @return Returns the primaryEntityType.
	 */
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	/**
	 * @param primaryEntityType The primaryEntityType to set.
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
	 * @param secEntityId The secEntityId to set.
	 */
	public void setSecEntityId(int secEntityId) {
		this.secEntityId = secEntityId;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
    /**
     * Returns the checkedNoteName
     * @return String checkedNoteName.
     */
    public String getCheckedNoteName() {
        return checkedNoteName;
    }
    /**
     * Sets the checkedNoteName
     * @param checkedNoteName.
     */
    public void setCheckedNoteName(String checkedNoteName) {
        this.checkedNoteName = checkedNoteName;
    }
	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	/**
	 * @return Returns the benefitComponentNotes.
	 */
	public List getBenefitComponentNotes() {
		if(benefitComponentNotes == null) {
	    	List list = (List)getRequest().getAttribute("messages");
	        // Get the request object with the values set in it
	         NotesAttachmentRequest notesLookUpRequest = getNotesOverrideLookUpRequest();
	         
	         // Set the action as override look up action in the request
	 		notesLookUpRequest.setAction(4);
	 		
	        // Call the executeService() to get the response
			NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse)
				this.executeService(notesLookUpRequest);
	        
			//BenefitLevelOverrideLocateResult locResult = new BenefitLevelOverrideLocateResult();
	        // Set the response in the backing bean property
			
			List getNotesList = notesLookUpResponse.getNotesList();
			this.setCheckedNote("");
			if(null != getNotesList && 
					!getNotesList.isEmpty()){
			    int size = getNotesList.size();
			    for(int i=0;i<size;i++){
			        NotesAttachmentOverrideBO overrideBO = (NotesAttachmentOverrideBO)(getNotesList.get(i));
			        if(overrideBO.getOverrideStatus().equals("F")){
			            overrideBO.setCheckedNoteId(true);
			            this.setCheckedNote(overrideBO.getNoteId());
			            this.setCheckedNoteName(overrideBO.getNoteName());
			        }
			    }
				
//				this.setNotes(notesLookUpResponse.getNotesList());
			    this.setBenefitComponentNotes(getNotesList);
			}else{
				this.setBenefitComponentNotes(null);
			}
			
			if (null ==  list ){
				list =  new ArrayList();
			}
//			if(iFlag == 1 && list.size()==0){
//				list.add(new InformationalMessage(WebConstants.NOTE_ATTACHED_ALREADY_BENEFITLINE));
//			}
			super.addAllMessagesToRequest(list);
	    	}
	        
		return benefitComponentNotes;
	}
	/**
	 * @param benefitComponentNotes The benefitComponentNotes to set.
	 */
	public void setBenefitComponentNotes(List benefitComponentNotes) {
		this.benefitComponentNotes = benefitComponentNotes;
	}
	
	/**
	 * @return Returns the loadNotes.
	 */
	public String getLoadNotes() {
		getBenefitComponentNotes();
		return "";
	}
	/**
	 * @param loadNotes The loadNotes to set.
	 */
	public void setLoadNotes(String loadNotes) {
		this.loadNotes = loadNotes;
	}
	
	/**
	 * @return Returns the loadCntNotes.
	 */
	public String getLoadCntNotes() {
		getNotes();
		return "";
	}
	/**
	 * @param loadCntNotes The loadCntNotes to set.
	 */
	public void setLoadCntNotes(String loadCntNotes) {
		this.loadCntNotes = loadCntNotes;
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


