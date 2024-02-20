/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.product.request.DeleteProductNoteRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.response.DeleteProductNoteResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u14768
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductNoteAssociationBackingBean extends ProductBackingBean {

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private List noteList = null;

    private List noteListForPrint = null;

    private String noteString = null;

    private boolean noteAssociationModified = false;

    private List productNoteList = null;

    private Map noteIdMap = new HashMap();

    private int rowId = 0;

    private Map noteKeyMap = new HashMap();

    private boolean valiadationStatus = false;

    List validationMessages = null;

    private boolean checkin;

    private String state = null;

    private String status = null;

    private int version = 0;

    private boolean higherVersion = true;

    boolean noteValdn = true;

    private String productKey;

    private String printValue = null;

    private boolean checkForNotes;

    private boolean securityAccess;
    
    private boolean hasValidationErrors;
    
    private Map notesDeleteMap = new HashMap();
    
    private String hiddenInit;


    //Setting the BreadCumb.
    public ProductNoteAssociationBackingBean() {
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            this.setBreadCumbTextForView();
        } else {
            this.setBreadCumbTextForEdit();
        }
        if (null != getProductSessionObject().getProductKeyObject()) {
            if (getProductSessionObject().getProductKeyObject().getVersion() > 0)
                higherVersion = true;
            else
                higherVersion = false;
        } else {
            higherVersion = false;
        }
    }


    /*
     * Methode to load notes return list of notes attached
     *  
     */
    public String loadNotes() {

        RetrieveProductNoteRequest retrieveProductNoteRequest = (RetrieveProductNoteRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_NOTES_REQUEST);

        RetrieveProductNoteResponse retrieveProductNoteResponse = (RetrieveProductNoteResponse) executeService(retrieveProductNoteRequest);
        this.productNoteList = retrieveProductNoteResponse
                .getProductNotetList();
        this.noteAssociationModified = true;
        return "productNoteAssociation";

    }


    /*
     * Methode to load notes to view return list of notes attached
     *  
     */
    public String loadNotesForView() {
        loadNotes();
        this.setCheckForNotes(true);
        return "productNotesView";
    }


    /*
     * Un attach note associated with a product
     */
    /*public String deleteNote() {
        int rowId = getRowId();
        int productId = super.getProductKeyFromSession();
        String noteKey = (String) noteKeyMap.get(new Long(rowId));
        DeleteProductNoteRequest deleteProductNoteRequest = (DeleteProductNoteRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_NOTE_DELETE);
        if (noteKey != null) {
            deleteProductNoteRequest.setNoteId(noteKey);
        }

        DeleteProductNoteResponse deleteProductNoteResponse = (DeleteProductNoteResponse) executeService(deleteProductNoteRequest);
        if (null != deleteProductNoteResponse) {
            this.productNoteList = deleteProductNoteResponse
                    .getProductNotetList();
        }
        this.noteAssociationModified = true;

        return "productNoteAssociation";

    }*/

    /**
     * Method to delete the attached notes.
     * @return String
     */
    public String deleteNotes() {
    	if(null == this.noteString || "".equals(this.noteString))
	    	getRequest().setAttribute("RETAIN_Value","");
        List noteDeleteList = this.getSelectedNotesForDelete();
        DeleteProductNoteRequest deleteProductNoteRequest = (DeleteProductNoteRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_NOTE_DELETE);
        DeleteProductNoteResponse deleteProductNoteResponse = null;
        if(null != noteDeleteList){
        	for (int i = 0; i < noteDeleteList.size(); i++){
        		String noteKey = (String)noteDeleteList.get(i);
        		if(null != noteKey){
        			deleteProductNoteRequest.setNoteId(noteKey);
        			deleteProductNoteResponse = (DeleteProductNoteResponse) executeService(deleteProductNoteRequest);
        		}
        	}
        }
        
        if (null != deleteProductNoteResponse) {
            this.productNoteList = deleteProductNoteResponse
                    .getProductNotetList();
        }
        this.noteAssociationModified = true;
        return "productNoteAssociation";
    }
    

    /**
     * Method to get the selected Notes.
     * @return
     */
    private List getSelectedNotesForDelete(){
    	// variable declarations
    	List notesDeleteList = null;
    	Set notesKeySet = null;
    	Iterator noteIdIterator = null;
    	Object noteId = null;
    	Boolean isChecked = null;
    	
    	if(null != this.getNotesDeleteMap()){
    		// get the key set from the map
    		notesKeySet = this.getNotesDeleteMap().keySet();
    		// Iterate the key set
    		noteIdIterator = notesKeySet.iterator();
    		notesDeleteList = new ArrayList();
    		while(noteIdIterator.hasNext()){
    			// get the key
    			noteId = noteIdIterator.next();
    			// get the value whether it is checked or not.
    			isChecked = (Boolean) this.getNotesDeleteMap().get(noteId);
    			String noteKey = (String)this.getNoteKeyMap().get(noteId);
    			if(isChecked.booleanValue()){
    				// add the note id to the list
    				notesDeleteList.add(noteKey);
    			}
    		}
    	}
    	
    	// return the selected notes to be deleted.
    	return notesDeleteList;
    }
    
    public String saveProductNotes() {
    	getRequest().setAttribute("RETAIN_Value","");
        List noteKeyList = WPDStringUtil.getListFromTildaString(noteString, 3,
                2, 2);
        List versionList = WPDStringUtil.getListFromTildaString(noteString, 3,
                3, 2);
        noteString = "";
        SaveProductNoteRequest saveProductNoteRequest = (SaveProductNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_NOTE);

        saveProductNoteRequest.setAction(SaveProductNoteRequest.SAVE_NOTE);
        saveProductNoteRequest.setNoteList(noteKeyList);
        saveProductNoteRequest.setVersionList(versionList);
        SaveProductNoteResponse saveProductNoteResponse = null;
        if (null == noteKeyList || noteKeyList.size() == 0) {
            noteValdn = false;
            validationMessages = new ArrayList();
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_NOTE_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return "productNoteAssociation";
        }
        saveProductNoteResponse = (SaveProductNoteResponse) executeService(saveProductNoteRequest);
        if (null != saveProductNoteResponse
                && saveProductNoteResponse.isSuccess()) {
            this.productNoteList = saveProductNoteResponse
                    .getProductNotetList();

            this.noteAssociationModified = true;
            return "productNoteAssociation";
        }

        return "";

    }


    /*
     * methode for checkin process request and response for note attachment
     * @saveProductNoteRequest @SaveProductNoteResponse
     * 
     * request and response for check in @SaveProductRequest SaveProductResponse
     */

    public String done() {
    	getRequest().setAttribute("RETAIN_Value", "");
        List noteKeyList = WPDStringUtil.getListFromTildaString(noteString, 3,
                2, 2);
        List versionList = WPDStringUtil.getListFromTildaString(noteString, 3,
                3, 2);
        noteString = "";
        SaveProductNoteRequest saveProductNoteRequest = (SaveProductNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_NOTE);
        saveProductNoteRequest.setAction(SaveProductNoteRequest.SAVE_NOTE);
        saveProductNoteRequest.setNoteList(noteKeyList);
        saveProductNoteRequest.setVersionList(versionList);
        SaveProductNoteResponse saveProductNoteResponse = null;
        List msgListOne = new ArrayList();
        List msgListTwo = new ArrayList();
        if (null != noteKeyList && noteKeyList.size() != 0) {

            saveProductNoteResponse = (SaveProductNoteResponse) executeService(saveProductNoteRequest);
            if (null != saveProductNoteResponse
                    && saveProductNoteResponse.isSuccess()) {
                this.productNoteList = saveProductNoteResponse
                        .getProductNotetList();
                this.noteAssociationModified = true;
            }
        }
        SaveProductRequest saveProductRequest = (SaveProductRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT);
        SaveProductResponse saveProductResponse = null;
        if (null != saveProductNoteResponse
                && null != saveProductNoteResponse.getMessages())
            msgListOne = saveProductNoteResponse.getMessages();
        saveProductRequest.setAction(SaveProductRequest.CHECKIN_PRODUCT);
        
        if(null!=getSession().getAttribute("AM_BENEFIT"))
			getSession().removeAttribute("AM_BENEFIT");
		if(null!=getSession().getAttribute("AM_BC_KEY"))
			getSession().removeAttribute("AM_BC_KEY");
		if(null!=getSession().getAttribute("AM_ENTITY_KEY"))
			getSession().removeAttribute("AM_ENTITY_KEY");
		if(null!=getSession().getAttribute("DIRECT_CLICK"))
			getSession().removeAttribute("DIRECT_CLICK");
        
        saveProductRequest.setCheckIn(this.checkin);
        saveProductRequest.getProduct().setProductKey(
                super.getProductKeyFromSession());
        saveProductRequest.getProduct().setProductName(
                super.getProductNameFromSession());
        saveProductRequest.getProduct().setProductStructureKey(
        		super.getProductSessionObject().getProductStructKey());
        saveProductResponse = (SaveProductResponse) this
                .executeService(saveProductRequest);
        if (null != saveProductResponse) {
        	// Rule Validation
        	getSession().setAttribute(
					WebConstants.SESSION_DELETED_RULES_LIST, 
					saveProductResponse.getDeletedRulesList());
        	getSession().setAttribute(
					WebConstants.SESSION_UNCODED_RULES_LIST, 
					saveProductResponse.getUnCodedRulesList());
            if (null != saveProductResponse.getMessages())
                msgListTwo = saveProductResponse.getMessages();
            msgListOne.addAll(msgListTwo);
            addAllMessagesToRequest(msgListOne);
			if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAminMethodValidation();
				return "";
			}else if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"productNoteAssociationBackingBean");
				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
						this);
				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
						new Integer(super.getProductKeyFromSession()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
						WebConstants.PROD_TYPE);
				return "validationWait";
			}
            if (saveProductResponse.isSuccess()) {
                if (saveProductRequest.isCheckIn()) {
                    cleanSession();
                    return "createProduct";
                }
            }
        }

        return "productNotes";

    }


    public HtmlPanelGrid getPanel() {
        if (noteAssociationModified) {
            preparePanel(this.productNoteList);
            //   register(this.productNoteList);
        }
        return panel;
    }


    private void register(List noteBOList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(noteBOList, "componentKey", "sequence");
    }


    /**
     * This method prepares the panel grid from benefit component list
     * 
     * @param List
     *            benefitComponentList.
     */

    public void preparePanel(List productNoteList) {
        final String VIEW_IMAGE_PATH = "../../images/view.gif";
        AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
        this.panel = new HtmlPanelGrid();
        if(!this.isCheckForNotes()){
        	panel.setColumns(3);
        }else{
        	panel.setColumns(2);
        }
        if (null != productNoteList && productNoteList.size() != 0) {
            for (int i = 0; i < productNoteList.size(); i++) {

                attachedNotesBO = (AttachedNotesBO) getProductNoteList().get(i);
                HtmlOutputText outputText = new HtmlOutputText();
                HtmlInputHidden noteId = new HtmlInputHidden();
                HtmlInputHidden version = new HtmlInputHidden();
                HtmlOutputLabel lblType = new HtmlOutputLabel();
                lblType.setId("lblType"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputLabel lblType1 = new HtmlOutputLabel();
                lblType1.setId("lblType1"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputLabel lblType2 = new HtmlOutputLabel();
                lblType2.setId("lblType2"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputText htmlOutputText = new HtmlOutputText();
                HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox();
                checkBox.setValue(Boolean.valueOf(false));                   		
                checkBox.setTitle("Delete"); 		
    			checkBox.setId("deleteCheckBox" + i);
    			checkBox.setValueBinding("value",
        										FacesContext.getCurrentInstance().
												getApplication().
												createValueBinding(
													"#{productNoteAssociationBackingBean.notesDeleteMap[" 
													+ i +
													"]}")
											);   
    			  
    			checkBox.setOnclick("changeButton(); return true;");
                htmlOutputText.setId("otxt" + i);
                htmlOutputText.setValue(" ");

                ValueBinding myItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productNoteAssociationBackingBean.noteKeyMap["
                                        + i + "]}");                
                HtmlCommandButton viewButton = new HtmlCommandButton();
                viewButton.setId("viewButton" + i);
                viewButton
                        .setOnclick("ewpdModalWindow_NotesView('../popups/viewNoteDetails.jsp'+getUrl()+'?noteId="
                                + attachedNotesBO.getNoteId()
                                + "&noteName="
                                + attachedNotesBO.getNoteName()
                                + "&version="
                                + attachedNotesBO.getVersion()
                                + "&temp= + Math.random()','dummyDiv','formName:dummyHidden',1,1);return false;");
                viewButton.setImage(VIEW_IMAGE_PATH);
                viewButton.setTitle("View Note");
                //create a hidden field for the benefit component name
                HtmlInputHidden hiddenBnftComponentName = new HtmlInputHidden();

                outputText.setValue(attachedNotesBO.getNoteName());
                noteId.setValue((attachedNotesBO.getNoteId()).toString());
                noteId.setId("noteId" + i);
                noteId.setValueBinding("value", myItem);
                version.setValue(new Integer(attachedNotesBO.getVersion())); 
                version.setId("version" + i);
                lblType.getChildren().add(version);
                lblType.getChildren().add(noteId);
                lblType.getChildren().add(outputText);
                try {
                    if (securityAccess = getUser().isAuthorized(
                            WebConstants.NOTES_MODULE,

                            StateFactory.VIEW_TASK))

                        lblType1.getChildren().add(viewButton);
                } catch (SevereException e) {
    				Logger.logError(e);
                }
                if (!this.isCheckForNotes())
                    try {
                        if (securityAccess = getUser().isAuthorized(
                                WebConstants.NOTES_MODULE,
                                StateFactory.VIEW_TASK))
                            lblType1.getChildren().add(viewButton);
                    } catch (SevereException e) {
        				Logger.logError(e);
                    }
                lblType1.getChildren().add(htmlOutputText);

                if (!this.isCheckForNotes())
                    lblType2.getChildren().add(checkBox);
                panel.getChildren().add(lblType);
                panel.getChildren().add(lblType1);
                if (!this.isCheckForNotes())
                	panel.getChildren().add(lblType2);

            }
        }
    }


    /**
     * @return noteAssociationModified
     * 
     * Returns the noteAssociationModified.
     */
    public boolean isNoteAssociationModified() {
        return noteAssociationModified;
    }


    /**
     * @param noteAssociationModified
     * 
     * Sets the noteAssociationModified.
     */
    public void setNoteAssociationModified(boolean noteAssociationModified) {
        this.noteAssociationModified = noteAssociationModified;
    }


    /**
     * @return noteList
     * 
     * Returns the noteList.
     */
    public List getNoteList() {
        return noteList;
    }


    /**
     * @param noteList
     * 
     * Sets the noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }


    /**
     * @return noteString
     * 
     * Returns the noteString.
     */
    public String getNoteString() {
        return noteString;
    }


    /**
     * @param noteString
     * 
     * Sets the noteString.
     */
    public void setNoteString(String noteString) {
        this.noteString = noteString;
    }


    /**
     * @return productNoteList
     * 
     * Returns the productNoteList.
     */
    public List getProductNoteList() {
        return productNoteList;
    }


    /**
     * @param productNoteList
     * 
     * Sets the productNoteList.
     */
    public void setProductNoteList(List productNoteList) {
        this.productNoteList = productNoteList;
    }


    /**
     * @param panel
     * 
     * Sets the panel.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * @return noteIdMap
     * 
     * Returns the noteIdMap.
     */
    public Map getNoteIdMap() {
        return noteIdMap;
    }


    /**
     * @param noteIdMap
     * 
     * Sets the noteIdMap.
     */
    public void setNoteIdMap(Map noteIdMap) {
        this.noteIdMap = noteIdMap;
    }


    /**
     * @return Returns the rowId.
     */
    public int getRowId() {
        return rowId;
    }


    /**
     * @param rowId
     *            The rowId to set.
     */
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }


    /**
     * @return Returns the noteKeyMap.
     */
    public Map getNoteKeyMap() {
        return noteKeyMap;
    }


    /**
     * @param noteKeyMap
     *            The noteKeyMap to set.
     */
    public void setNoteKeyMap(Map noteKeyMap) {
        this.noteKeyMap = noteKeyMap;
    }


    /**
     * @return Returns the checkin.
     */
    public boolean isCheckin() {
        return checkin;
    }


    /**
     * @param checkin
     *            The checkin to set.
     */
    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }


    /**
     * @return Returns the state.
     */
    public String getState() {
        return getStateFromSession();
    }


    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return super.getStatusFromSession();
    }


    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return getVersionFromSession();
    }


    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * @return Returns the higherVersion.
     */
    public boolean isHigherVersion() {
        return higherVersion;
    }


    /**
     * @param higherVersion
     *            The higherVersion to set.
     */
    public void setHigherVersion(boolean higherVersion) {
        this.higherVersion = higherVersion;
    }


    /**
     * @return Returns the noteValdn.
     */
    public boolean isNoteValdn() {
        return noteValdn;
    }


    /**
     * @param noteValdn
     *            The noteValdn to set.
     */
    public void setNoteValdn(boolean noteValdn) {
        this.noteValdn = noteValdn;
    }


    /**
     * Returns the productKey
     * 
     * @return String productKey.
     */
    public String getProductKey() {
        return productKey;
    }


    /**
     * Sets the productKey
     * 
     * @param productKey.
     */
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }


    /**
     * Returns the checkForNotes
     * 
     * @return boolean checkForNotes.
     */
    public boolean isCheckForNotes() {
        return checkForNotes;
    }


    /**
     * Sets the checkForNotes
     * 
     * @param checkForNotes.
     */
    public void setCheckForNotes(boolean checkForNotes) {
        this.checkForNotes = checkForNotes;
    }


    /**
     * @return noteListForPrint
     * 
     * Returns the noteListForPrint.
     */
    public List getNoteListForPrint() {
        return noteListForPrint;
    }


    /**
     * @param noteListForPrint
     * 
     * Sets the noteListForPrint.
     */
    public void setNoteListForPrint(List noteListForPrint) {
        this.noteListForPrint = noteListForPrint;
    }


    /**
     * @return printValue
     * 
     * Returns the printValue.
     */
    public String getPrintValue() {
        //return printValue;
        String requestForPrint = getRequest()
                .getParameter("printValueForNotes");
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
    }


    /**
     * @param printValue
     * 
     * Sets the printValue.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }
	/**
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}
	/**
	 * @param hasValidationErrors The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}
	/**
	 * @return Returns the notesDeleteMap.
	 */
	public Map getNotesDeleteMap() {
		return notesDeleteMap;
	}
	/**
	 * @param notesDeleteMap The notesDeleteMap to set.
	 */
	public void setNotesDeleteMap(Map notesDeleteMap) {
		this.notesDeleteMap = notesDeleteMap;
	}
	/**
	 * @return Returns the hiddenInit.
	 */
	public String getHiddenInit() {
		RetrieveProductNoteRequest retrieveProductNoteRequest = (RetrieveProductNoteRequest) this
		        .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_NOTES_REQUEST);
		
		RetrieveProductNoteResponse retrieveProductNoteResponse = (RetrieveProductNoteResponse) executeService(retrieveProductNoteRequest);
		if (null != retrieveProductNoteResponse.getProductNotetList()){
		    this.noteListForPrint = retrieveProductNoteResponse
		            .getProductNotetList();
		    this.printValue = "print";
		} else
		    this.printValue = "";
		return hiddenInit;
	}
	/**
	 * @param hiddenInit The hiddenInit to set.
	 */
	public void setHiddenInit(String hiddenInit) {
		this.hiddenInit = hiddenInit;
	}
}
