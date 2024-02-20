/*
 * Created on May 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataImpl;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.request.CreateNotesDataDomainRequest;
import com.wellpoint.wpd.common.notes.request.CreateNotesRequest;
import com.wellpoint.wpd.common.notes.request.DeleteNotesRequest;
import com.wellpoint.wpd.common.notes.request.NoteDomainRequest;
import com.wellpoint.wpd.common.notes.request.NotesCheckInRequest;
import com.wellpoint.wpd.common.notes.request.NotesCheckOutRequest;
import com.wellpoint.wpd.common.notes.request.NotesCopyRequest;
import com.wellpoint.wpd.common.notes.request.RetrieveNotesRequest;
import com.wellpoint.wpd.common.notes.request.ViewAllVersionsNotesRequest;
import com.wellpoint.wpd.common.notes.response.CreateNotesDataDomainResponse;
import com.wellpoint.wpd.common.notes.response.CreateNotesResponse;
import com.wellpoint.wpd.common.notes.response.DeleteNotesResponse;
import com.wellpoint.wpd.common.notes.response.NoteDomainResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckInResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckOutResponse;
import com.wellpoint.wpd.common.notes.response.NotesCopyResponse;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.common.notes.response.ViewAllVersionsNotesResponse;
import com.wellpoint.wpd.common.notes.vo.NoteDomainVO;
import com.wellpoint.wpd.common.notes.vo.NotesVO;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.refdata.ReferenceDataBackingBean;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesBackingBean extends WPDBackingBean {	

	boolean dataDomainValdn = false;
	
	boolean nameValdn = false;
	
	boolean textValdn = false;
	
	boolean typeValdn = false;
	
	boolean systemDomainValdn = false;
	
	private String reference;
	
	private String product;
	
	private String benefit;
	
	private String benefitComponent;
	
	private String noteId;
	
	private String name;
	
	private String text;
	
	private String type;
	
	private String systemDomain;
	
	private String systemDomainCode;
	
	private boolean requiredFields;

	private List validationMessages;
	
	private List systemDomainList;
	
	private int version = -1;
	
	private String status;
	
	private State stateObject;
	
	private String state;
	
	private String action;
	
	private List productList;
	
	private List benefitList;
	
	private List componentList;
	
	private String createdUser;
	
	private Date createdTimestamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimestamp;
	
	private boolean checkInFlag;
	
	// for view
	private String viewNotesKey;
	
	private String noteNameForLifeCycles;
	
	private boolean copyFlag;
	
	private String term;
	
	private String qualifier;

	private List termList;

	private List qualifierList;
	
	private List messages;
	
	private String domainId;
	
	private String domainType;
	
	private List allVersionList;
	
	private String printValueForGenInfo;

	private String printValueForDataDomain;
	
	private String loadDataDomainForPrint;
	
	private String printEdit;
	
	private String printValueForGenInfoEdit;
	
	private String printDataDomain;
	
	private String noteTypeDesc;
	
	private List noteTypeList;
	
	private String noDataDomainMessage;
	
	private ReferenceDataImpl referenceDataImpl;
	
	private int termListSize;
	
	private int qualifierListSize;
	
	
	private String productTxt;
	
	private String benefitTxt;
	
	private String benefitComponentTxt;
	
	private boolean checkForEdit = false;
	
	private String loadCompare;
	private String lob;
	
	private boolean notesCompare;
	
	private String loadCompareResults;
    
	private String businessEntity;
	
	private String businessGroup;
	
	private String marketBusinessUnit;
	
	private boolean view;
	
	private String formattedNotes;
		
	private String flagForDomains;
	
	private List productForDeleteList;
	
	private List benefitForDeleteList;
	
	private List compForDeleteList;
	
	private List termForDeleteList;
	
	private List qualifierForDeleteList;
	
	private boolean flagForDelete;
	
	private boolean flagForNavigation;

	private boolean validationForTerm;
	
	private String hiddenNoteTypeDesc;
	
	private String loadNotes;
	
	private String  hiddenEditNote;
	
	private boolean systemDomainFlag = true;
	
	private String printBreadCrumbText;
	
	private String Mode = "Edit";
	
	private boolean notesInActivelyUsedStatus;
	
	private int valueChanged = 0;
	
	private String flagForDomainPage = "false";
	
	private String identifyTab;
	
	private String targetHiddenFieldForDelete;
	
	private String noteDescHidden;
	
	private String questionTxt; 
	
	private String question; 
	
	private List questionList;
	
	private List questionForDeleteList;
	
    /**
     * @return systemDomainFlag
     * 
     * Returns the systemDomainFlag.
     */
    public boolean isSystemDomainFlag() {
        return systemDomainFlag;
    }
    /**
     * @param systemDomainFlag
     * 
     * Sets the systemDomainFlag.
     */
    public void setSystemDomainFlag(boolean systemDomainFlag) {
        this.systemDomainFlag = systemDomainFlag;
    }
    /**
     * @return Returns the termListSize.
     */
    public int getTermListSize() {
        return termListSize;
    }
    /**
     * @param termListSize The termListSize to set.
     */
    public void setTermListSize(int termListSize) {
        this.termListSize = termListSize;
    }
	/**
	 * @return Returns the formattedNotes.
	 */
	public String getFormattedNotes() {
		return formattedNotes;
	}
	/**
	 * @param formattedNotes The formattedNotes to set.
	 */
	public void setFormattedNotes(String formattedNotes) {
		this.formattedNotes = WPDStringUtil.processNoteText(this.text);
	}
	
	/**
	 * Constructor
	 */
	public NotesBackingBean() {
		super();
		this.lob = WebConstants.ALL_99;
        this.businessEntity = WebConstants.ALL_99;
        this.businessGroup = WebConstants.ALL_99;
        this.marketBusinessUnit = WebConstants.ALL_99;
        
        // Modified for refdata
        SubCatalogBO subCatalogBO = new SubCatalogBO();

		this.systemDomainList = new ArrayList();
		Application application = FacesContext.getCurrentInstance().getApplication();
		ReferenceDataBackingBean backingBean =  ((ReferenceDataBackingBean) application.
		        createValueBinding("#{ReferenceDataBackingBeanCommon}").getValue(FacesContext.getCurrentInstance()));
		String requiredValidation = "";
		if(null != this.getSession().getAttribute("requiredValidation")){
			requiredValidation = (String) this.getSession().getAttribute("requiredValidation");
			this.getSession().removeAttribute("requiredValidation");
		}
		if(!requiredValidation.equals("false")){
			this.systemDomainList = backingBean.getSystemDomainResultList();
			
			this.systemDomain = new String();
			// Modified for refdata
	//		this.referenceDataImpl = new ReferenceDataImpl();
			Iterator sys = systemDomainList.iterator();
			while(sys.hasNext()){
	//			Modified for refdata
				subCatalogBO = (SubCatalogBO)sys.next();
				systemDomain = systemDomain + subCatalogBO.getDescription()+ "~" + subCatalogBO.getPrimaryCode()+ "~";
			}
        }
		if(null != this.getSession().getAttribute("checkForEdit")){
			this.setCheckForEdit(((Boolean)this.getSession().getAttribute("checkForEdit")).booleanValue());		
			this.getSession().removeAttribute("checkForEdit");
		}
		this.noteTypeList = backingBean.getNoteTypeListForCombo();
		setBreadCrump();
	}
	
	/**
	 * @return Returns the printValueForDataDomainEdit.
	 */
	public String getPrintValueForDataDomainEdit() {
		return printValueForDataDomainEdit;
	}
	/**
	 * @param printValueForDataDomainEdit The printValueForDataDomainEdit to set.
	 */
	public void setPrintValueForDataDomainEdit(
			String printValueForDataDomainEdit) {
		this.printValueForDataDomainEdit = printValueForDataDomainEdit;
	}
	private String printValueForDataDomainEdit;
	
	/**
	 * @return Returns the loadDataDomainForPrint.
	 */
	public String getLoadDataDomainForPrint() {
		getNoteDomains();
		return loadDataDomainForPrint;
	}
	/**
	 * @param loadDataDomainForPrint The loadDataDomainForPrint to set.
	 */
	public void setLoadDataDomainForPrint(String loadDataDomainForPrint) {
		this.loadDataDomainForPrint = loadDataDomainForPrint;
	}
	/**
	 * @return Returns the printValueForDataDomain.
	 */
	public String getPrintValueForDataDomain() {
		String requestForPrint = (String)getRequest().getParameter("printValueForDataDom");
        if(null != requestForPrint && !requestForPrint.equals("")){
        	printValueForDataDomain = requestForPrint;
        }else{
        	printValueForDataDomain = "";
        }
		return printValueForDataDomain;
	}
	/**
	 * @param printValueForDataDomain The printValueForDataDomain to set.
	 */
	public void setPrintValueForDataDomain(String printValueForDataDomain) {
		this.printValueForDataDomain = printValueForDataDomain;
	}
	/**
	 * @return Returns the printValueForGenInfo.
	 */
	public String getPrintValueForGenInfo() {
		String requestForPrint = (String)getRequest().getParameter("printValueForGenInfo");
        if(null != requestForPrint && !requestForPrint.equals("")){
        	printValueForGenInfo = requestForPrint;
        }else{
        	printValueForGenInfo = "";
        }
		return printValueForGenInfo;
	}
	/**
	 * @param printValueForGenInfo The printValueForGenInfo to set.
	 */
	public void setPrintValueForGenInfo(String printValueForGenInfo) {
		this.printValueForGenInfo = printValueForGenInfo;
	}
	/**
	 * @return Returns the domainId.
	 */
	public String getDomainId() {
		return domainId;
	}
	/**
	 * @param domainId The domainId to set.
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	/**
	 * @return Returns the domainType.
	 */
	public String getDomainType() {
		return domainType;
	}
	/**
	 * @param domainType The domainType to set.
	 */
	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the qualifierList.
	 */
	public List getQualifierList() {
		return qualifierList;
	}
	/**
	 * @param qualifierList The qualifierList to set.
	 */
	public void setQualifierList(List qualifierList) {
		this.qualifierList = qualifierList;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return Returns the termList.
	 */
	public List getTermList() {
		return termList;
	}
	/**
	 * @param termList The termList to set.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the dataDomainValdn.
	 */
	public boolean isDataDomainValdn() {
		return dataDomainValdn;
	}
	/**
	 * @param dataDomainValdn The dataDomainValdn to set.
	 */
	public void setDataDomainValdn(boolean dataDomainValdn) {
		this.dataDomainValdn = dataDomainValdn;
	}
	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}
	/**
	 * @param benefit The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	/**
	 * @return Returns the benefitComponent.
	 */
	public String getBenefitComponent() {
		return benefitComponent;
	}
	/**
	 * @param benefitComponent The benefitComponent to set.
	 */
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}

	/**
	 * @return Returns the product.
	 */
	public String getProduct() {
		return product;
	}
	/**
	 * @param product The product to set.
	 */
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return Returns the productValdn.
	 */
	
	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return Returns the referenceValdn.
	 */
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the nameValdn.
	 */
	public boolean isNameValdn() {
		return nameValdn;
	}
	/**
	 * @param nameValdn The nameValdn to set.
	 */
	public void setNameValdn(boolean nameValdn) {
		this.nameValdn = nameValdn;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return Returns the textValdn.
	 */
	public boolean isTextValdn() {
		return textValdn;
	}
	/**
	 * @param textValdn The textValdn to set.
	 */
	public void setTextValdn(boolean textValdn) {
		this.textValdn = textValdn;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the typeValdn.
	 */
	public boolean isTypeValdn() {
		return typeValdn;
	}
	/**
	 * @param typeValdn The typeValdn to set.
	 */
	public void setTypeValdn(boolean typeValdn) {
		this.typeValdn = typeValdn;
	}
	/**
	 * @return Returns the systemDomain.
	 */
	public String getSystemDomain() {
		return systemDomain;
	}
	/**
	 * @param systemDomain The systemDomain to set.
	 */
	public void setSystemDomain(String systemDomain) {
		this.systemDomain = systemDomain;
	}
	/**
	 * @return Returns the systemDomainValdn.
	 */
	public boolean isSystemDomainValdn() {
		return systemDomainValdn;
	}
	/**
	 * @param systemDomainValdn The systemDomainValdn to set.
	 */
	public void setSystemDomainValdn(boolean systemDomainValdn) {
		this.systemDomainValdn = systemDomainValdn;
	}
	
	/**
	 * @return
	 */
	public String createNote() {
		
		this.setMode("Create");
		//Validating if all the mandotary details are available, before saving.
		// If not, then it will redirects to the same page with error message.
		if (!validateRequiredFileds()) {
		    addAllMessagesToRequest(validationMessages);
		    if(this.systemDomain == null || "".equals(this.systemDomain)){
		        this.systemDomain.equals("");
		        this.systemDomainList = null;
		        this.setSystemDomainFlag(false);
		    }
			return WebConstants.EMPTY_STRING;
		}
		if(this.isCopyFlag()){
			if(null == this.getType()){
				String noteType = (String)this.getSession().getAttribute("noteTypeNew");
				if(this.getVersion() > 0){
					if(null != this.noteTypeList){
						Iterator iterator = this.noteTypeList.listIterator();
				        while(iterator.hasNext()){
				        	SelectItem referenceData = (SelectItem)iterator.next();
				        	if(noteType.equals(referenceData.getLabel())){
				        		this.setType(referenceData.getValue().toString());
				        	}
				        }
					}
				}
				if(null==noteType || "".equalsIgnoreCase(noteType)){
					if(null!=this.getSession().getAttribute("noteType")){
						this.setType((String)this.getSession().getAttribute("noteType"));
					}
				}
			}
			NotesCopyRequest notesCopyRequest = getNotesCopyRequest();
			NotesCopyResponse notesCopyResponse = (NotesCopyResponse)executeService(notesCopyRequest);
			if(null != notesCopyResponse){
				this.setValidationMessages(notesCopyResponse.getMessages());			
				//Adding all the messages to the request
				addAllMessagesToRequest(validationMessages);
				if(!(notesCopyResponse.isErrorMessageInList())){
					copyValuesToBackingBeanFromResponse(notesCopyResponse);
					this.setCopyFlag(false);
					retrieveNotes();
					return "notesEdit";
				}
				return "createNotesPage";
			}
			else{
				return WebConstants.EMPTY_STRING;
			}
			
		}else{
			CreateNotesRequest createNotesRequest = getCreateNotesRequest();
			createNotesRequest.setCreateFlag(true);

		    //Creating the response object
		    CreateNotesResponse createNotesResponse = (CreateNotesResponse) executeService(createNotesRequest);

		    if (null != createNotesResponse) {

				//If there is no error at the time of create, then will navigate to
				// edit page.
				this.setValidationMessages(createNotesResponse.getMessages());
				addAllMessagesToRequest(validationMessages);
				//Flag for validation failure to bypass default value fetching of system domain
				if(createNotesResponse.isErrorFlag()){
					this.getSession().setAttribute("requiredValidation","false");
				}
				if (!createNotesResponse.isErrorFlag()) {
				    copyResponseValuesToBackingBean(createNotesResponse);
				    this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
					return retrieveNotes();
				}
			}

		return WebConstants.EMPTY_STRING;
		}
		
	}
	
	/**
	 * The method will be invoked when the Save button is 
	 * clicked from the Notes Data Domain page
	 * @return
	 */
	public String createDataDomains(){
		this.setFlagForNavigation(false);
		validationMessages = new ArrayList();
		this.valueChanged = 0;
		String termString = convertListToTilda("termList");
		String qualifierString = convertListToTilda("qualifierList");

		 if(!"".equals(qualifierString) && null != qualifierString){
			if("".equals(termString) || null == termString){
				validationMessages.add(new ErrorMessage("term.qualifier.validation"));
				addAllMessagesToRequest(validationMessages);
				this.setMessages(validationMessages);				
				this.productForDeleteList = (List)this.getSession().getAttribute("productList");
				this.compForDeleteList = (List)this.getSession().getAttribute("compList");
				this.benefitForDeleteList = (List)this.getSession().getAttribute("benefitList");
				this.questionForDeleteList = (List)this.getSession().getAttribute("questionList");
				this.termForDeleteList = (List)this.getSession().getAttribute("termList");
				this.qualifierForDeleteList = (List)this.getSession().getAttribute("qualifierList");
				
				String returnValue = loadNoteDomains();
				
				this.productList = this.productForDeleteList;
				this.benefitList = this.benefitForDeleteList;
				this.questionList = this.questionForDeleteList; 
				this.componentList = this.compForDeleteList;
				this.termList = this.termForDeleteList;
				this.qualifierList = this.qualifierForDeleteList;
				
				getRequest().getSession().setAttribute("productList",this.productList);
				getRequest().getSession().setAttribute("compList",this.componentList);
				getRequest().getSession().setAttribute("benefitList",this.benefitList);
				getRequest().getSession().setAttribute("questionList",this.questionList);
				getRequest().getSession().setAttribute("termList",this.termList);
				getRequest().getSession().setAttribute("qualifierList",this.qualifierList);
				
				return returnValue;
			}
		}
		CreateNotesDataDomainRequest createNotesDataDomainRequest = getCreateNotesDataDomainRequest();
		createNotesDataDomainRequest.setCreateFlag(true);
		CreateNotesDataDomainResponse createNotesDataDomainResponse = (CreateNotesDataDomainResponse)executeService(createNotesDataDomainRequest);
		if (null != createNotesDataDomainResponse) {
			this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
				this.setState(createNotesDataDomainResponse.getState());
				this.setValidationMessages(createNotesDataDomainResponse.getMessages());
			String loadNoteDomainpage=	loadNoteDomains();
			if (loadNoteDomainpage != null)
			{
				return loadNoteDomainpage;
			}
		}
		return "dataDomain";
		
	}
	protected void setBreadCrump(){
		if(this.getName() == null)
            this.setBreadCrumbText(WebConstants.NOTES_CREATE_BREADCRUMB);
	}
	
	/**
	 * The method will create a CreateNotesDataDomainRequest object and 
	 * set all the necessary parameters in it.
	 * @return
	 */
	private CreateNotesDataDomainRequest getCreateNotesDataDomainRequest() {
	    //Creating request object
		CreateNotesDataDomainRequest createNotesDataDomainRequest = (CreateNotesDataDomainRequest) this
				.getServiceRequest(ServiceManager.CREATE_NOTES_DATA_DOMAIN_REQUEST);
		createNotesDataDomainRequest.setNoteId(this.getNoteId());
		createNotesDataDomainRequest.setVersion(this.getVersion());
		createNotesDataDomainRequest.setNoteName(this.name);
	
		String productString = convertListToTilda("productList");
		String compString = convertListToTilda("compList");
		String benefitString = convertListToTilda("benefitList");
		String questionString = convertListToTilda("questionList"); 
		String termString = convertListToTilda("termList");
		String qualifierString = convertListToTilda("qualifierList");
		
		createNotesDataDomainRequest.setProductList(WPDStringUtil.getListFromTildaString(productString,2,2,2));
		createNotesDataDomainRequest.setBenefitComponentList(WPDStringUtil.getListFromTildaString(compString,2,2,2));
		createNotesDataDomainRequest.setBenefitList(WPDStringUtil.getListFromTildaString(benefitString,2,2,2));
		createNotesDataDomainRequest.setQuestionList(WPDStringUtil.getListFromTildaString(questionString,2,2,2));
		createNotesDataDomainRequest.setTermList(WPDStringUtil.getListFromTildaString(termString,2,2,2));
		createNotesDataDomainRequest.setQualifierList(WPDStringUtil.getListFromTildaString(qualifierString,2,2,2));
		createNotesDataDomainRequest.setState(this.state);
		return createNotesDataDomainRequest;
	}
	
	
	/**
	 * @param name
	 * @return
	 */
	public String convertListToTilda(String name ){
		List list = (List)this.getSession().getAttribute(name);
		String dataDomainString = "";
		if(null != list){
			Iterator iterator = list.iterator();
			String array[] = new String[100];
			NoteDomainBO noteSystemDomainBO = null;
			
			for(int i=1;i<=list.size();i++){
				noteSystemDomainBO = (NoteDomainBO)list.get(i-1);
				if(i==list.size()){
					dataDomainString = dataDomainString + noteSystemDomainBO.getSystemDomainDescription()+"~"+noteSystemDomainBO.getSystemDomainId();
				}else{
					dataDomainString = dataDomainString + noteSystemDomainBO.getSystemDomainDescription() +"~"+ noteSystemDomainBO.getSystemDomainId()+"~";
				}
			}
			
		}
		return dataDomainString;
	}
	/**
	 * @param createNotesResponse
	 */
	private void copyResponseValuesToBackingBean(CreateNotesResponse createNotesResponse) {
		this.setNoteId(createNotesResponse.getNoteId());
		this.setStatus(createNotesResponse.getStatus());
		this.setVersion(createNotesResponse.getVersion());
		this.setCreatedUser(createNotesResponse.getCreatedUser());
		this.setCreatedTimestamp(createNotesResponse.getCreatedTimestamp());
		this.setLastUpdatedUser(createNotesResponse.getLastUpdatedUser());
		this.setLastUpdatedTimestamp(createNotesResponse.getLastUpdatedTimestamp());
		if(null != createNotesResponse.getStateObject()){
			this.setState(createNotesResponse.getStateObject().getState());
		}
		
	}
	/**
	 * @return Returns the requiredFields.
	 */
	public boolean isRequiredFields() {
		return requiredFields;
	}
	/**
	 * @param requiredFields The requiredFields to set.
	 */
	public void setRequiredFields(boolean requiredFields) {
		this.requiredFields = requiredFields;
	}
	 /**
     * Returns the loadNotes
     * @return String loadNotes.
     */
    public String getLoadNotes() {
        this.systemDomainList = new ArrayList();
		String requiredValidation = "";
		if(null != this.getSession().getAttribute("requiredValidation")){
			requiredValidation = (String) this.getSession().getAttribute("requiredValidation");
			this.getSession().removeAttribute("requiredValidation");
		}

        if(!copyFlag && !requiredValidation.equals("false")){
			Application application = FacesContext.getCurrentInstance().getApplication();
			ReferenceDataBackingBean backingBean =  ((ReferenceDataBackingBean) 
			        application.createValueBinding("#{ReferenceDataBackingBeanCommon}").
			        getValue(FacesContext.getCurrentInstance()));
			this.systemDomainList = backingBean.getSystemDomainResultList();
			SubCatalogBO subCatalogBO = new SubCatalogBO();
			this.systemDomain = new String();
			//this.referenceDataImpl = new ReferenceDataImpl();
			Iterator sys = systemDomainList.iterator();
			while(sys.hasNext()){
			    //referenceDataImpl = (ReferenceDataImpl)sys.next();
			    //systemDomain = systemDomain + referenceDataImpl.getDescription()+ "~" + referenceDataImpl.getCode()+ "~";
				subCatalogBO = (SubCatalogBO)sys.next();
				systemDomain = systemDomain + subCatalogBO.getDescription()+ "~" + subCatalogBO.getPrimaryCode()+ "~";
			}
        }
		if(!this.isSystemDomainFlag()){
		    this.systemDomain = "";
		}
		addAllMessagesToRequest(this.validationMessages);
        return loadNotes;
    }
    /**
     * Sets the loadNotes
     * @param loadNotes.
     */
    public void setLoadNotes(String loadNotes) {
        this.loadNotes = loadNotes;
    }
    
    
    /**
     * Returns the hiddenEditNote
     * @return String hiddenEditNote.
     */
    public String getHiddenEditNote() {
        if(this.text != null && this.text.length()>3000){
            this.hiddenEditNote="true";
        }
        return hiddenEditNote;
    }
    /**
     * Sets the hiddenEditNote
     * @param hiddenEditNote.
     */
    public void setHiddenEditNote(String hiddenEditNote) {
        this.hiddenEditNote = hiddenEditNote;
    }
	/**
	 * Method to check whether all the mandotary fields are available.
	 * 
	 * @return boolean
	 */
	private boolean validateRequiredFileds() {
		this.name =  com.wellpoint.wpd.common.framework.util.StringUtil.singleSpaceSeparation(this.name);
		
		if(null != this.noteDescHidden && !("".equals(this.noteDescHidden))){
			this.text = this.noteDescHidden;
			this.noteDescHidden =null;
		}
		validationMessages = new ArrayList();
		boolean requiredField = false;
		String noteName = this.name.trim();
		boolean requiredValidation = true;
		if (noteName == null || "".equals(noteName)) {
			nameValdn  = true;
			requiredField = true;
		}
		if (this.type == null || "".equals(this.type)) {
			typeValdn  = true;
			requiredField = true;
		}
		if (null == this.text || "".equals(this.text.trim())) {
			textValdn  = true;
			requiredField = true;
		}
		if(this.systemDomain == null || "".equals(this.systemDomain)){
			systemDomainValdn = true;
			requiredField = true;
		}
        if(this.text != null && this.text.length()>3000){
            validationMessages.add(new ErrorMessage(WebConstants.NOTE_TEXT_VALIDATE_MESSAGE));
            requiredValidation = false;
        }
        if(noteName != null && noteName.length()>30){
            validationMessages.add(new ErrorMessage(WebConstants.NOTE_NAME_VALIDATE_MESSAGE));
            requiredValidation = false;
        }
		if (requiredField) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			requiredValidation = false;
		}
		
		this.getSession().setAttribute("requiredValidation",Boolean.valueOf(requiredValidation).toString());
		return requiredValidation;
	}
	
	/**
	 * Method to create CreateNotesRequest and to copy the values from the
	 * backing bean.
	 * 
	 * @return CreateNotesRequest createNotesRequest
	 */
	private CreateNotesRequest getCreateNotesRequest() {

		//Creating request object
		CreateNotesRequest createNotesRequest = (CreateNotesRequest) this
				.getServiceRequest(ServiceManager.CREATE_NOTES_REQUEST);
		createNotesRequest.setNoteId(this.getNoteId());
		createNotesRequest.setName(this.getName().trim());
		createNotesRequest.setType(this.getType());
		createNotesRequest.setText(this.getText().trim());
		this.systemDomainList = WPDStringUtil.getListFromTildaString(this.systemDomain,2,2,2);
		createNotesRequest.setSystemDomain(this.systemDomainList);
		createNotesRequest.setVersion(this.getVersion());
		createNotesRequest.setStatus(this.getStatus());
		createNotesRequest.setState(this.getState());
		return createNotesRequest;
		
	}

	/**
	 * @return Returns the systemDomainList.
	 */
	public List getSystemDomainList() {
		return systemDomainList;
	}
	/**
	 * @param systemDomainList The systemDomainList to set.
	 */
	public void setSystemDomainList(List systemDomainList) {
		this.systemDomainList = systemDomainList;
	}
	/**
	 * @return Returns the stateObject.
	 */
	public State getStateObject() {
		return stateObject;
	}
	/**
	 * @param stateObject The stateObject to set.
	 */
	public void setStateObject(State stateObject) {
		this.stateObject = stateObject;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		if (null != stateObject && !"".equals(stateObject.getState())) {
			stateObject.getState();
		}
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
		
	
	public List replaceAmp(List list){
		List newList = new ArrayList();
		if(null != list){
			for(int i =0;i<list.size();i++){
				String value = (String)list.get(i);
				value = value.replaceAll("&","#&'||'");
				newList.add(value);
				
			}
			return newList;
		}
		return list;

	}
	/**
	 * @param noteDomainRequest
	 * @return
	 */
	public NoteDomainRequest getBusinessDomain(NoteDomainRequest noteDomainRequest){
		
		List lob = WPDStringUtil.getListFromTildaString(getRequest().getParameter("lobValue"),2,2,2);
		List busEntity = WPDStringUtil.getListFromTildaString(getRequest().getParameter("beValue"),2,2,2);
		List busGroup = WPDStringUtil.getListFromTildaString(getRequest().getParameter("bgValue"),2,2,2);
		//******* START CARS **********//
		List marketBusinessUnit = WPDStringUtil.getListFromTildaString(getRequest().getParameter("bUValue"),2,2,2);
		//******* END CARS **********//
		noteDomainRequest.setLobList(lob);
		noteDomainRequest.setBusinessEntityList(busEntity);			
		noteDomainRequest.setBusinessGroupList(busGroup);
		noteDomainRequest.setMarketBusinessUnitList(marketBusinessUnit);
		noteDomainRequest.setMaximumSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
		return noteDomainRequest;
	}
	
	/**
	 * @return
	 */
	public String getProductValues(){
		NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("product");
		String productName=getRequest().getParameter("notesProductValue");
		String product = getRequest().getParameter("notesProductSearch");
		String checkValue = getRequest().getParameter("checkValue");
		String noteId = getRequest().getParameter("noteId");
		if("true".equals(checkValue)){
			noteDomainRequest.setCheck("true");
		}else
			noteDomainRequest.setCheck("false");
		if(null != productName){
			noteDomainRequest.setNoteName(productName.trim()+"%");
		}
		getBusinessDomain(noteDomainRequest);
		noteDomainRequest.setNoteId(noteId);

		//Creating the response object
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
			if(null!=noteDomainResponse.getResults()){
				this.setProductList(noteDomainResponse.getResults());
			}
			
		}
		return "";
	}
	/**
	 * @param value
	 * @return
	 */
	public List splitToList(String value){	    
		List list = new ArrayList();
		String [] valueArray = value.split("~");
		NoteDomainBO noteDomainBO = null;
		for(int i=0;i<valueArray.length;i++){
			noteDomainBO = new NoteDomainBO();
			noteDomainBO.setSystemDomainDescription(valueArray[i]);
			i++;
			noteDomainBO.setSystemDomainId(valueArray[i].toString());
			if(("1").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILPRODUCT");				
			}else if(("2").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILCOMP");
			}else if(("3").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILBENEFIT");
			}else if(("4").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILTERM");
			}else if(("5").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILQUALIFIER");
			}else if(("6").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILQUESTION"); 
			}
			noteDomainBO.setNoteId(this.noteId);
			list.add(noteDomainBO);
		}
		return list;
	}
	
	/**
	 * The method will populate the domain datatable in the data domain page 
	 * when a domain is selected from the popup
	 * @return
	 */
	public String saveToDatatable(){
		
		String productString = convertListToTilda("productList");
		String compString = convertListToTilda("compList");
		String benefitString = convertListToTilda("benefitList");
		String termString = convertListToTilda("termList");
		String qualifierString = convertListToTilda("qualifierList");		
		String questionString = convertListToTilda("questionList");
		
		//The below lists for each domain will contain the newly added NoteDomainBO's - begin		
		
		List newProdList = null;
		String productValue = this.getProduct();
		newProdList = addNewObjectToNewList(productValue,"newProdList");		
		
		List newCompList = null;
		String compValue = this.getBenefitComponent();	
		newCompList = addNewObjectToNewList(compValue,"newCompList");		 
		
		List newBenefitList = null;
		String benefitValue = this.getBenefit();		
		newBenefitList = addNewObjectToNewList(benefitValue,"newBenefitList");		
		
		List newQuestionList =  null;
		String questionValue = this.getQuestion();		
		newQuestionList = addNewObjectToNewList(questionValue,"newQuestionList");				
		
		List newTrmList = null;
		String termvalue = this.getTerm();		
		newTrmList = addNewObjectToNewList(termvalue,"newTrmList");		
		
		List newQlifierList =  null;
		String qualifierValue = this.getQualifier();
		newQlifierList = addNewObjectToNewList(qualifierValue,"newQlifierList");		   
		
		getRequest().getSession().setAttribute("newProdList",newProdList);
		getRequest().getSession().setAttribute("newCompList",newCompList);
		getRequest().getSession().setAttribute("newBenefitList",newBenefitList);
		getRequest().getSession().setAttribute("newQuestionList",newQuestionList); 
		getRequest().getSession().setAttribute("newTrmList",newTrmList);
		getRequest().getSession().setAttribute("newQlifierList",newQlifierList);
		
		// end	
		
		List savedTermList = this.getListFromTildaString(termString);
		List newTermList = this.getListFromTildaString(termvalue);
		int flagForTerm = compareTwoLists(savedTermList,newTermList);
		
		List savedQualifierList = this.getListFromTildaString(qualifierString);
		List newQualifierList = this.getListFromTildaString(qualifierValue);
		int flagForQualifier = compareTwoLists(savedQualifierList,newQualifierList);
		
		if((null != productValue && !"".equals(productValue)) 
		        || (null != questionValue && !"".equals(questionValue)) 
				|| (null != compValue && !"".equals(compValue))
				|| (null != benefitValue && !"".equals(benefitValue))
				|| (null != termvalue && !"".equals(termvalue) && flagForTerm == 0)
				|| (null != qualifierValue && !"".equals(qualifierValue) && flagForQualifier == 0)
				){
			this.valueChanged = 1;
		}				
		
		if( !("".equals(productString)) && "".equals(productValue) ){
			productValue = productString;			
		}
		else if( !("".equals(productString)) && !("".equals(productValue))){
			productValue = productValue + "~" + productString;
		}		
		if( !("".equals(compString)) && "".equals(compValue) ){
			compValue = compString;
		}
		else if( !("".equals(compString)) && !("".equals(compValue))){
			compValue = compValue + "~" + compString;
		}		
		if( !("".equals(benefitString)) && "".equals(benefitValue) ){
			benefitValue = benefitString;
		}
		else if( !("".equals(benefitString)) && !("".equals(benefitValue))){
			benefitValue = benefitValue + "~" + benefitString;
		}
		if( !("".equals(termString)) && "".equals(termvalue) ){
			termvalue = termString;
		}
		else if( !("".equals(termString)) && !("".equals(termvalue))){
			termvalue = termvalue + "~" + termString;
		}		
		if( !("".equals(qualifierString)) && "".equals(qualifierValue) ){
			qualifierValue = qualifierString;
		}
		else if( !("".equals(qualifierString)) && !("".equals(qualifierValue))){
			qualifierValue = qualifierValue + "~" + qualifierString;
		}
		if( !("".equals(questionString)) && "".equals(questionValue) ){
		    questionValue = questionString;
		}
		else if( !("".equals(questionString)) && !("".equals(questionValue))){
		    questionValue = questionValue + "~" + questionString;
		} 		
		List productList = null;
		List compList = null;
		List benefitList = null;
		List termList = null;
		List qualifierList = null;		
		List questionList = null; 
		
		if(null != productValue && (!("").equals(productValue))){
			productList = splitToList(productValue);	
		}
		if(null != compValue && (!("").equals(compValue))){
			compList = splitToList(compValue);			
		}
		if(null != benefitValue && (!("").equals(benefitValue))){	
			benefitList = splitToList(benefitValue);
		}
		if(null != termvalue && (!("").equals(termvalue))){	
			termList = splitToList(termvalue);
		}
		if(null != qualifierValue && (!("").equals(qualifierValue))){	
			qualifierList = splitToList(qualifierValue);
		}
		if(null != questionValue && (!("").equals(questionValue))){	
		    questionList = splitToList(questionValue);
		}  
		
		productList = duplicateCheck(productList);
		compList = duplicateCheck(compList);
		benefitList = duplicateCheck(benefitList);
		questionList = duplicateCheck(questionList);
		//termList = duplicateCheck(termList);
		if(null!=termList && termList.size()>0){
			WPDStringUtil.removeDuplicateWithOrder(termList);
		}
		//	qualifierList = duplicateCheck(qualifierList);
		if(null!=qualifierList && qualifierList.size()>0){
		
			WPDStringUtil.removeDuplicateWithOrder(qualifierList);
		}		
		loadNoteDomains();
		
		if(this.productList != null){
			getBOFromList(productList,1);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(productList,newProdList);
		}
		else
			this.setProductList(setCheckBoxToList(productList));	
		
		if(this.componentList != null){
			getBOFromList(compList,2);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(compList,newCompList);
		}
		else
			this.setComponentList(setCheckBoxToList(compList));

		if(this.benefitList != null){
			getBOFromList(benefitList,3);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(benefitList,newBenefitList);
		}
		else
			this.setBenefitList(setCheckBoxToList(benefitList));
		
		if(this.questionList != null){
			getBOFromList(questionList,6);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(questionList,newQuestionList);
		}
		else
			this.setQuestionList(setCheckBoxToList(questionList)); 

		if(this.termList != null){
			getBOFromList(termList,4);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(termList,newTrmList);
		}
		else
			this.setTermList(setCheckBoxToList(termList));

		if(this.qualifierList != null){
			getBOFromList(qualifierList,5);
			//Comparing the already existing list and the new list to display checkbox 
			compareAndSetCheckBox(qualifierList,newQlifierList);
		}
		else
			this.setQualifierList(setCheckBoxToList(qualifierList));
	
		getRequest().getSession().setAttribute("productList",this.productList);
		getRequest().getSession().setAttribute("compList",this.componentList);
		getRequest().getSession().setAttribute("benefitList",this.benefitList);
		getRequest().getSession().setAttribute("questionList",this.questionList); 
		getRequest().getSession().setAttribute("termList",this.termList);
		getRequest().getSession().setAttribute("qualifierList",this.qualifierList);
		
		if(null == this.productList || this.productList.size()==0){
			this.productList = null;
		}
		if(null == this.componentList || this.componentList.size()==0){
			this.componentList = null;
		}
		if(null == this.benefitList || this.benefitList.size()==0){
			this.benefitList = null;
		}
		if(null == this.termList || this.termList.size()==0){
			this.termList = null;
		}
		if(null == this.qualifierList || this.qualifierList.size()==0){
			this.qualifierList = null;
		}
		if(null == this.questionList || this.questionList.size()==0){
			this.questionList = null; 
		}
		this.setFlagForNavigation(true);	
		return "";
	}
	
	
	/**
	 * @param list
	 * @return
	 */
	public List duplicateCheck(List list){
		if(null != list){
			HashSet hashSet = new HashSet();		
			hashSet.addAll(list);
			list.clear();
			list.addAll(hashSet);
		}
		return list;
	}


	/**
	 * @param list
	 * @param key
	 */
	public void getBOFromList(List list,int key){
		if(null != list){
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				NoteDomainBO noteDomainBO = (NoteDomainBO)iterator.next();				
				checkForDuplicateBO(noteDomainBO,key);
			}
		}
	}

	/**
	 * @param noteDomainBO
	 * @param value
	 */
	public void checkForDuplicateBO(NoteDomainBO noteDomainBO, int value){
		boolean flag = false;
		Iterator iterator2 = null;
		if(value == 1)
			iterator2 = this.getProductList().iterator();
		if(value == 2)
			iterator2 = this.getComponentList().iterator();
		if(value == 3)
			iterator2 = this.getBenefitList().iterator();
		if(value == 4)
			iterator2 = this.getTermList().iterator();
		if(value == 5)
			iterator2 = this.getQualifierList().iterator();
		if(value == 6)
			iterator2 = this.getQuestionList().iterator();
		
		while(iterator2.hasNext()){
			NoteDomainBO noteDomainBO2 = (NoteDomainBO)iterator2.next();
			if(noteDomainBO.getSystemDomainId().equals(noteDomainBO2.getSystemDomainId())){
				flag = true;
				break;
			}
		}
		if(!flag){
			if(value == 1)
				this.getProductList().add(noteDomainBO);
			if(value == 2){
				this.getComponentList().add(noteDomainBO);			
			}
			if(value == 3){
				this.getBenefitList().add(noteDomainBO);			
			}
			if(value == 4){
				this.getTermList().add(noteDomainBO);			
			}
			if(value == 5){
				this.getQualifierList().add(noteDomainBO);			
			}
			if(value == 6){
				this.getQuestionList().add(noteDomainBO);			
			}
		}
	}
	
	/**
	 * @return
	 */
	public String getBenefitValues(){
		NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("benefit");
		String benefitName=getRequest().getParameter("notesBenefitValue");
		String benefit = getRequest().getParameter("notesBenefitSearch");
		String checkValue = getRequest().getParameter("checkValue");
		String noteId = getRequest().getParameter("noteId");
		if("true".equals(checkValue)){
			noteDomainRequest.setCheck("true");
		}else
			noteDomainRequest.setCheck("false");
		
		if(null != benefitName){
			noteDomainRequest.setNoteName(benefitName.trim()+"%");
		}
		getBusinessDomain(noteDomainRequest);
		noteDomainRequest.setNoteId(noteId);
		//Creating the response object
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
			if(null!=noteDomainResponse.getResults()){
				this.setBenefitList(noteDomainResponse.getResults());
			}			
		}
		return "";
	}
	
	/**
	 * The method will fetch all the questions from the Question Master table
	 * to be shown in the Questions popup from the Data Domain tab in the notes module
	 * @return
	 */
	public String getQuestionValues(){
		NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("question");
		noteDomainRequest.setMaximumSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
		String questionName=getRequest().getParameter("notesQuestionValue");
		String question = getRequest().getParameter("notesQuestionSearch");
		String checkValue = getRequest().getParameter("checkValue");
		String noteId = getRequest().getParameter("noteId");
		if("true".equals(checkValue)){
			noteDomainRequest.setCheck("true");
		}else
			noteDomainRequest.setCheck("false");
		
		if(null != questionName){
			noteDomainRequest.setNoteName(questionName.trim()+"%");
		}		
		noteDomainRequest.setNoteId(noteId);
		//Creating the response object
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
			if(null!=noteDomainResponse.getResults()){
				this.setQuestionList(noteDomainResponse.getResults());
			}			
		}
		return "";
	}
	
	/**
	 * @return
	 */
	public String getComponentValues(){
		NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("component");
		String benefitComponentName=getRequest().getParameter("notesBenefitComponentValue");
		String benefitComp = getRequest().getParameter("notesCompSearch");
		String checkValue = getRequest().getParameter("checkValue");
		String noteId = getRequest().getParameter("noteId");
		if("true".equals(checkValue)){
			noteDomainRequest.setCheck("true");
		}else
			noteDomainRequest.setCheck("false");
		
		if(null != benefitComponentName){
			noteDomainRequest.setNoteName(benefitComponentName.trim()+"%");
		}
		getBusinessDomain(noteDomainRequest);
		noteDomainRequest.setNoteId(noteId);
		//Creating the response object
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
			if(null!=noteDomainResponse.getResults()){
				this.setComponentList(noteDomainResponse.getResults());
			}			
		}
		return "";
	}
	
	/** 
	 * This method retrieves the notes to be displayed in Edit Page
	 * @return
	 */
	public String retrieveNotes(){
		// Note Type List for Print functionality.
		List noteTypeList = (List)this.getSession().getAttribute("noteTypeList");
		//Creating RetrieveNotesRequest
		RetrieveNotesRequest retrieveNotesRequest = (RetrieveNotesRequest)this.getServiceRequest(ServiceManager.RETRIEVE_NOTES_REQUEST);
		retrieveNotesRequest.setNoteId(this.getNoteId());
		retrieveNotesRequest.setVersion(this.getVersion());
		String name = this.getName();
		retrieveNotesRequest.setNoteName(name);
		if( null != name && !("".equals(name))){
			getRequest().getSession().setAttribute("noteName",name);
			// **Start** change made for String Conversion
			//getRequest().getSession().setAttribute("noteID",new Integer(this.getNoteId()));
			getRequest().getSession().setAttribute("noteID",this.getNoteId());
			// **End** change made for String Conversion			
			getRequest().getSession().setAttribute("noteVersion",new Integer(this.getVersion()));
		}
		if(null == name || "".equals(name)){
			//this.name = (String)getRequest().getSession().getAttribute("noteName");
			this.setName((String)this.getSession().getAttribute("noteName"));
			// **Start** change made for String Conversion
			//this.setNoteId(((Integer)this.getSession().getAttribute("noteID")).intValue());
			this.setNoteId(this.getSession().getAttribute("noteID").toString());
			// **End** change made for String Conversion			
			this.setVersion(((Integer)this.getSession().getAttribute("noteVersion")).intValue());
			retrieveNotesRequest.setNoteId(this.getNoteId());
			retrieveNotesRequest.setVersion(this.getVersion());
			retrieveNotesRequest.setNoteName(this.getName());
		}
		if(this.getMode().equalsIgnoreCase("Edit")){
			retrieveNotesRequest.setEdit(true);
		}
		RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse)executeService(retrieveNotesRequest);
		List systemDomain = (List) retrieveNotesResponse.getNoteBO().getSystemDomain();
		NoteDomainBO noteSystemDomainBO = null;
		String systemDomainString = "";
		List systemDomainIds = new ArrayList();
		for(int i=1;i<=systemDomain.size();i++){
			noteSystemDomainBO = (NoteDomainBO)systemDomain.get(i-1);
			if(i==systemDomain.size()){
				systemDomainString = systemDomainString + noteSystemDomainBO.getSystemDomainDescription()+"~"+noteSystemDomainBO.getSystemDomainIds().get(0);
			}else{
				systemDomainString = systemDomainString + noteSystemDomainBO.getSystemDomainDescription() +"~"+ noteSystemDomainBO.getSystemDomainIds().get(0)+"~";
			}
			systemDomainIds.add(noteSystemDomainBO.getSystemDomainIds().get(0));
		}
		this.getSession().setAttribute("SystemDomain",systemDomainIds);
		this.systemDomain = systemDomainString;
		
		copyValuesToBackingBeanFromResponse(retrieveNotesResponse);
		
		if(null != noteTypeList){
			Iterator iterator = noteTypeList.listIterator();
	        while(iterator.hasNext()){
	        	SelectItem referenceData = (SelectItem)iterator.next();
	        	if(this.getType().equals(referenceData.getValue())){
	        		this.noteTypeDesc = referenceData.getLabel();
	        	}
	        }
		}
		if(this.getVersion() > 0 && !(this.copyFlag) && !this.getMode().equals("View")){
			if(null != this.getHiddenNoteTypeDesc())
			    //this.setType(this.getHiddenNoteTypeDesc());
				this.setNoteTypeDesc(this.getHiddenNoteTypeDesc());
			else{
				String noteType = (String)this.getSession().getAttribute("noteTypeNew");
				if(null!=this.getSession().getAttribute("noteType")){
					this.setType((String)this.getSession().getAttribute("noteType"));
				}
				this.setNoteTypeDesc(noteType);
			}
			getRequest().getSession().setAttribute("noteType",this.getType());
			getRequest().getSession().setAttribute("noteTypeNew",this.getNoteTypeDesc());
		}
		this.setFormattedNotes(this.text);
		//removing the list from session.
		this.getSession().removeAttribute("notet");
		//Adding all the messages to the request
		addAllMessagesToRequest(validationMessages);

		return "notesEdit";
	}
	
	/**
	 * This method copies values from response to the Backing bean.
	 * @param retrieveNotesResponse
	 */
	private void copyValuesToBackingBeanFromResponse(RetrieveNotesResponse retrieveNotesResponse) {
		this.setNoteId(retrieveNotesResponse.getNoteBO().getNoteId());
		if(this.isCopyFlag()){
			this.setName(WebConstants.EMPTY_STRING);
		}else{
			this.setName(retrieveNotesResponse.getNoteBO().getNoteName());
		}
		this.setNotesInActivelyUsedStatus(retrieveNotesResponse.isNotesInActivelyUsedStatus());
		this.setText(retrieveNotesResponse.getNoteBO().getNoteText());
		this.setType(retrieveNotesResponse.getNoteBO().getNoteType());
		this.setNoteTypeDesc(retrieveNotesResponse.getNoteBO().getNoteTypeDesc());
		this.setSystemDomainList(retrieveNotesResponse.getNoteBO().getNoteSystemDomain());
		this.setStatus(retrieveNotesResponse.getNoteBO().getStatus());
		this.setState(retrieveNotesResponse.getNoteBO().getState().getState());
		this.setCreatedUser(retrieveNotesResponse.getNoteBO().getCreatedUser());
		this.setCreatedTimestamp(retrieveNotesResponse.getNoteBO().getCreatedTimestamp());
		this.setLastUpdatedUser(retrieveNotesResponse.getNoteBO().getLastUpdatedUser());
		this.setLastUpdatedTimestamp(retrieveNotesResponse.getNoteBO().getLastUpdatedTimestamp());
		this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
		//if(this.view == true){
		if(this.Mode.equals("View")){
			this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> View");
		}
		if(this.getStatus().equals("BUILDING")){
			this.setCheckForEdit(true);
			getRequest().getSession().setAttribute("checkForEdit",Boolean.TRUE);			
		}else{
			this.setCheckForEdit(false);
			getRequest().getSession().setAttribute("checkForEdit",Boolean.FALSE);
		}		
		//keeping the values in session
		getRequest().getSession().setAttribute("noteName",this.getName());
		// **Start** change made for String Conversion		
		//getRequest().getSession().setAttribute("noteID",new Integer(this.getNoteId()));
		getRequest().getSession().setAttribute("noteID",this.getNoteId());
		// **End** change made for String Conversion		
		getRequest().getSession().setAttribute("noteVersion",new Integer(this.getVersion()));
		getRequest().getSession().setAttribute("noteDesc",this.getText());
		getRequest().getSession().setAttribute("noteType",this.getType());
		getRequest().getSession().setAttribute("systemDomainList",this.getSystemDomainList());
		getRequest().getSession().setAttribute("status",this.getStatus());
		getRequest().getSession().setAttribute("state",this.getState());
		
	}
	
	public String loadNoteDomains(){
		//this.selectedFromPopup = "false";
		NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("dataDomains");
		//noteDomainRequest.setNoteId(this.noteId);
		// **Start** change made for String Conversion		
		/*if(this.noteId != 0){
			getRequest().getSession().setAttribute("noteId",new Integer(this.noteId));
		}
		if(this.noteId ==0){
			this.setNoteId(((Integer)getRequest().getSession().getAttribute("noteId")).intValue());
		}*/
		if(null != this.noteId && !this.noteId.equals("")){
			getRequest().getSession().setAttribute("noteId",this.noteId);
		}
		if(null == this.noteId || this.noteId.equals("")){
			this.setNoteId(getRequest().getSession().getAttribute("noteId").toString());
		}
		if(null != this.type && !this.type.equals("")){
			getRequest().getSession().setAttribute("noteType",this.type);
		}
		if(null == this.type || this.type.equals("")){
			this.setType(getRequest().getSession().getAttribute("noteType").toString());
		}		
		
		//	**End** change made for String Conversion		
		noteDomainRequest.setNoteId(this.noteId);
		noteDomainRequest.setVersion(this.version);

		//Creating the response object 
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
				this.setNotesInActivelyUsedStatus(noteDomainResponse.isNotesInActivelyUsedStatus());
			if(null!=noteDomainResponse.getResults()){
				HashMap map = (HashMap) noteDomainResponse.getResults().get(0);
				this.setProductList((List) map.get("product"));				
				this.setBenefitList((List)map.get("benefit"));
				this.setComponentList((List)map.get("benefitcomp"));
				this.setQuestionList((List)map.get("question"));
				this.setTermList((List)map.get("term"));
				this.setTermListSize(this.getTermList().size());
				this.setQualifierList((List)map.get("qualifier"));
				this.setQualifierListSize(this.getQualifierList().size());
			}			
		}
		if(null != this.getProductList() || this.getProductList().size() != 0){
			this.setProduct("");
		}
		if(null != this.getBenefitList() || this.getBenefitList().size() != 0){			
			this.setBenefit("");
		}
		if(null != this.getComponentList() || this.getComponentList().size() != 0){			
			this.setBenefitComponent("");
		}
		if(null != this.getQuestionList() || this.getQuestionList().size() != 0){			
			this.setQuestion(""); 
		}
		if(null != this.getTermList() || this.getTermList().size() != 0){		
			this.setTerm("");
			this.getSession().setAttribute("domainTerms",this.getTermList());
			//commented to unselect the items in the term popup
			//this.setTerm(convertListToTilda("domainTerms"));
			this.getSession().removeAttribute("domainTerms");
		}
		if(null != this.getQualifierList() || this.getQualifierList().size() != 0){		
			this.setQualifier("");
			this.getSession().setAttribute("domainQualifier",this.getQualifierList());
			//commented to unselect the items in the qualifier popup
			//this.setQualifier(convertListToTilda("domainQualifier"));
			this.getSession().removeAttribute("domainQualifier");
		}
		if(this.getProductList().size() == 0){
			this.setProductList(null);
		}
		if(this.getBenefitList().size() == 0){
			this.setBenefitList(null);
		}
		if(this.getComponentList().size() == 0){
			this.setComponentList(null);
		}
		if(this.getQuestionList().size() == 0){
			this.setQuestionList(null);
		}
		if(this.getTermList().size() == 0){
			this.setTermList(null);
		}
		if(this.getQualifierList().size() == 0){
			this.setQualifierList(null);
		}

		this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
		addAllMessagesToRequest(validationMessages);
		
		//to sort the data table of selected domains		
		final Comparator STRING_ORDER =	new Comparator(){
			public int compare(Object n1, Object n2) {
			    NoteDomainBO n11 = (NoteDomainBO)n1;
			    NoteDomainBO n22 = (NoteDomainBO)n2;
				return n11.getSystemDomainDescription().
				compareTo(n22.getSystemDomainDescription());
			}
		};	
		if(null != this.productList && this.productList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.productList = setCheckBoxToDataTable(this.productList,"Edit");
		    Collections.sort(this.productList, STRING_ORDER);
		}
		if(null != this.componentList && this.componentList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.componentList = setCheckBoxToDataTable(this.componentList,"Edit");
		    Collections.sort(this.componentList, STRING_ORDER);
		}
		if(null != this.benefitList && this.benefitList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.benefitList = setCheckBoxToDataTable(this.benefitList,"Edit");
		    Collections.sort(this.benefitList, STRING_ORDER);
		}
		if(null != this.questionList && this.questionList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.questionList = setCheckBoxToDataTable(this.questionList,"Edit");
		    Collections.sort(this.questionList, STRING_ORDER);
		}
		if(null != this.termList && this.termList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.termList = setCheckBoxToDataTable(this.termList,"Edit");
		    Collections.sort(this.termList, STRING_ORDER);
		}
		if(null != this.qualifierList && this.qualifierList.size()>0){
		    //setting checkboxes to the newly added domains in the DomainList
		    this.qualifierList = setCheckBoxToDataTable(this.qualifierList,"Edit");
		    Collections.sort(this.qualifierList, STRING_ORDER);
		}	
		getRequest().getSession().setAttribute("productList",this.productList);
		getRequest().getSession().setAttribute("compList",this.componentList);
		getRequest().getSession().setAttribute("benefitList",this.benefitList);
		getRequest().getSession().setAttribute("questionList",this.questionList);		
		getRequest().getSession().setAttribute("termList",this.termList);
		getRequest().getSession().setAttribute("qualifierList",this.qualifierList);

		return "loadNoteDomains";
	}	
	
	/**
	 * The method will be invoked in the View mode of datadomain
     * 
     */
    private void getNoteDomains() {
    	validationMessages = new ArrayList();
        NoteDomainRequest noteDomainRequest =  (NoteDomainRequest)this.getServiceRequest(ServiceManager.NOTE_DOMAIN_REQUEST);
		//set it dynamically
		noteDomainRequest.setAction("dataDomains");
		noteDomainRequest.setNoteId(this.noteId);
		noteDomainRequest.setVersion(this.version);
		// **Start** change made for String Conversion		
		/*if(this.noteId ==0){
			this.setNoteId(((Integer)getRequest().getSession().getAttribute("noteId")).intValue());
			noteDomainRequest.setNoteId(this.noteId);
		}*/
		if(null == this.noteId || this.noteId.equals("")){
			this.setNoteId(getRequest().getSession().getAttribute("noteId").toString());
			noteDomainRequest.setNoteId(this.noteId);
		}
		// **End** change made for String Conversion		
		//Creating the response object
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse) executeService(noteDomainRequest);

		if (null != noteDomainResponse) {
			if(null!=noteDomainResponse.getResults()){
				HashMap map = (HashMap) noteDomainResponse.getResults().get(0);
				this.setProductList((List) map.get("product"));
				this.setBenefitList((List)map.get("benefit"));
				this.setComponentList((List)map.get("benefitcomp"));
				this.setTermList((List)map.get("term"));
				this.setQualifierList((List)map.get("qualifier"));
				this.setQuestionList((List)map.get("question")); 
			}			
		}
		
		 //the below code will put all the domains in the current version alone in a list
		// for each of the domains - begin
		 this.componentList = setCheckBoxToDataTable(this.componentList,"View");
		 this.productList = setCheckBoxToDataTable(this.productList,"View");
		 this.benefitList = setCheckBoxToDataTable(this.benefitList,"View");
		 this.questionList = setCheckBoxToDataTable(this.questionList,"View");
		 this.termList = setCheckBoxToDataTable(this.termList,"View");
		 this.qualifierList = setCheckBoxToDataTable(this.qualifierList,"View");
		 //end
		
		if(null != this.getProductList() || this.getProductList().size() != 0){
			this.setProduct(getTildaStringFromList(this.getProductList()));
		}
		if(null != this.getBenefitList() || this.getBenefitList().size() != 0){
			this.setBenefit( getTildaStringFromList(this.getBenefitList()));
		}
		if(null != this.getComponentList() || this.getComponentList().size() != 0){
			this.setBenefitComponent(getTildaStringFromList(this.getComponentList()));
		}
		if(null != this.getQuestionList() || this.getQuestionList().size() != 0){
			this.setQuestion( getTildaStringFromList(this.getQuestionList()));
		}
		if(null != this.getTermList() || this.getTermList().size() != 0){
			this.setTerm(getTildaStringFromList(this.getTermList()));
		}
		if(null != this.getQualifierList() || this.getQualifierList().size() != 0){		   
			this.setQualifier(getTildaStringFromList(this.getQualifierList()));
		}
		
		//for sorting
		final Comparator STRING_ORDER =	new Comparator(){
			public int compare(Object n1, Object n2) {
			    NoteDomainBO n11 = (NoteDomainBO)n1;
			    NoteDomainBO n22 = (NoteDomainBO)n2;
				return n11.getSystemDomainDescription().
				compareTo(n22.getSystemDomainDescription());
			}
		};	
		if(null != this.productList && this.productList.size()>0){		   
		    Collections.sort(this.productList, STRING_ORDER);
		}
		if(null != this.componentList && this.componentList.size()>0){		  
		    Collections.sort(this.componentList, STRING_ORDER);
		}
		if(null != this.benefitList && this.benefitList.size()>0){		    
		    Collections.sort(this.benefitList, STRING_ORDER);
		}
		if(null != this.questionList && this.questionList.size()>0){		   
		    Collections.sort(this.questionList, STRING_ORDER);
		}
		if(null != this.termList && this.termList.size()>0){		    
		    Collections.sort(this.termList, STRING_ORDER);
		}
		if(null != this.qualifierList && this.qualifierList.size()>0){		    
		    Collections.sort(this.qualifierList, STRING_ORDER);
		}	
		//end
		
		if(this.getProductList().size() == 0){
			this.setProductList(null);
		}
		if(this.getBenefitList().size() == 0){
			this.setBenefitList(null);
		}
		if(this.getQuestionList().size() == 0){
			this.setQuestionList(null);
		}
		if(this.getComponentList().size() == 0){
			this.setComponentList(null);
		}
		if(this.getTermList().size() == 0){
			this.setTermList(null);
		}
		if(this.getQualifierList().size() == 0){
			this.setQualifierList(null);
		}
		this.setStatus(this.getSession().getAttribute("status").toString());
		this.setState(this.getSession().getAttribute("state").toString());
		//getRequest().getSession().setAttribute("noteVersion",new Integer(this.getVersion()));
		//this.setVersion(new Integer(getRequest().getSession().getAttribute("noteVersion")).intValue());
		this.setVersion(((Integer)this.getSession().getAttribute("noteVersion")).intValue());
		addAllMessagesToRequest(messages);
    }
    
  
	/**
	 * @param list
	 * @return
	 */
	private String getTildaStringFromList(List list) {
		NoteDomainBO noteDomainBO = new NoteDomainBO();
		String domainString = new String();
		for(int i=1;i<=list.size();i++){
			noteDomainBO = (NoteDomainBO)list.get(i-1);
			if(i==list.size()){
				domainString = domainString + noteDomainBO.getSystemDomainDescription()+"~"+noteDomainBO.getSystemDomainId();
			}else{
				domainString = domainString + noteDomainBO.getSystemDomainDescription() +"~"+ noteDomainBO.getSystemDomainId()+"~";
			}
		}
		return domainString;
	}

	/**
	 * @param string
	 * @return
	 */
	private List getListFromTildaString(String string){
		String [] valueArray = string.split("~");
		List newList = new ArrayList();
		for(int i=0; i<valueArray.length; i+=2){
			newList.add(valueArray[i]);
		}
		return newList;
	}

	/**
	 * @param savedList
	 * @param newList
	 * @return
	 */
	private int compareTwoLists(List savedList, List newList){
		if(savedList.size() != newList.size())
			return 0;
		for(int i=0; i<newList.size(); i++){
			String tempString = (String) newList.get(i);
			if(!savedList.contains(tempString))
				return 0;
		}
		return 1;
	}
	
	/**
	 * @return
	 */
	public String updateNotes(){
		validationMessages = new ArrayList();
		
		if(null == this.getType()){
			String noteType = (String)this.getSession().getAttribute("noteTypeNew");
			if(this.getVersion() > 0){
				if(null != this.noteTypeList){
					Iterator iterator = this.noteTypeList.listIterator();
			        while(iterator.hasNext()){
			        	SelectItem referenceData = (SelectItem)iterator.next();
			        	if(noteType.equals(referenceData.getLabel())){
			        		this.setType(referenceData.getValue().toString());
			        	}
			        }
				}
			}
			
			if(null==this.getType() || "".equalsIgnoreCase(this.getType())){
				if(null!=this.getSession().getAttribute("noteType")){
					this.setType((String)this.getSession().getAttribute("noteType"));
				}
			}
		}
		
		if (!validateRequiredFileds()) {
			this.setFormattedNotes((String)this.getSession().getAttribute("noteDesc"));
			this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
			addAllMessagesToRequest(validationMessages);
			if(this.getStatus().equals("BUILDING")){
				this.setCheckForEdit(true);
				getRequest().getSession().setAttribute("checkForEdit",Boolean.TRUE);			
			}else{
				this.setCheckForEdit(false);
				getRequest().getSession().setAttribute("checkForEdit",Boolean.FALSE);
			}
			if(this.systemDomain == null || "".equals(this.systemDomain)){
		        this.systemDomain = "";
		        this.setSystemDomainFlag(false);
		    }
			return WebConstants.EMPTY_STRING;
		}
		//		Validating if all the mandotary details are available, before saving.
		// If not, then it will redirects to the same page with error message.
		if ("".equals(this.getText()) || null == this.getText()) {
			this.setText((String)this.getSession().getAttribute("noteDesc"));
		}
		CreateNotesRequest createNotesRequest = getCreateNotesRequest();
		NotesVO notesVO = new NotesVO();
		this.hiddenEditNote = "false";
		notesVO.setNoteName((String)this.getSession().getAttribute("noteName"));
		// **Start** change made for String Conversion		
		//notesVO.setNoteId(((Integer)this.getSession().getAttribute("noteID")).intValue());
		notesVO.setNoteId(this.getSession().getAttribute("noteID").toString());
		// **End** change made for String Conversion		
		notesVO.setVersionNo(((Integer)this.getSession().getAttribute("noteVersion")).intValue());
		if ("".equals(this.getText())) {
			notesVO.setText((String)this.getSession().getAttribute("noteDesc"));
		}else{
			notesVO.setText(this.getText().trim());
		}
		notesVO.setNoteType((String)this.getSession().getAttribute("noteType"));
		notesVO.setSystemDomainList((List)this.getSession().getAttribute("systemDomainList"));
		
		createNotesRequest.setOldNotesVO(notesVO);
		if(this.flagForDomainPage.equals("true")){
			List systemDomainList = (List) this.getSession().getAttribute("SystemDomain");
			createNotesRequest.setSystemDomain(systemDomainList);
		}
		createNotesRequest.setCreateFlag(false);

		//Creating the response object
		CreateNotesResponse createNotesResponse = (CreateNotesResponse) executeService(createNotesRequest);

		if (null != createNotesResponse) {

			//copyResponseValuesToBackingBean(createNotesResponse);
			this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
			//If there is no error at the time of create, then will navigate to
			// edit page.
			this.setValidationMessages(createNotesResponse.getMessages());
			return retrieveNotes();
			//if (!createNotesResponse.isErrorFlag()) {
			//	return retrieveNotes();
			//}
		}
		return WebConstants.EMPTY_STRING;
	}
	
	/**
	 * This method checks in the created Notes.
	 * @return
	 */
	public String checkIn(){
		
		if(null == validationMessages){
			messages = new ArrayList();
		}
		String flag = "";
		if("NOTES".equals(this.identifyTab)){
			flag = this.updateNotes();
		}else if("DATA DOMAIN".equals(this.identifyTab)){
			flag = createDataDomains();
		}
		if(!"".equals(flag)){
			if (this.checkInFlag) {
				NotesCheckInRequest notesCheckInRequest = getNotesCheckInRequest();
				NotesCheckInResponse notesCheckInResponse = (NotesCheckInResponse) executeService(notesCheckInRequest);
				if (null != notesCheckInResponse
						&& notesCheckInResponse.isAction()) {
					this.name = WebConstants.EMPTY_STRING;
					this.type = WebConstants.EMPTY_STRING;
					this.text = WebConstants.EMPTY_STRING;
					this.systemDomain = WebConstants.EMPTY_STRING;
					this.setValidationMessages(notesCheckInResponse.getMessages());
					addAllMessagesToRequest(validationMessages);
					this.setBreadCrumbText("Notes Library >> Note >> Create");
					return "createNotesPage";
				}	
			}else {
				this.setFlagForNavigation(false);
				loadNoteDomains();
				if(null != validationMessages){
					validationMessages.add(new InformationalMessage(
						"note.checkin.validation"));
					addAllMessagesToRequest(validationMessages);
				}else{
					messages.add(new InformationalMessage(
						"note.checkin.validation"));
					addAllMessagesToRequest(messages);
				}
				this.setFormattedNotes((String)this.getSession().getAttribute("noteDesc"));
			}
		}

		return WebConstants.EMPTY_STRING;
	}

	/**
	 * @return
	 */
	public String notesCheckout(){
		String returnMessage = null;
		NotesCheckOutRequest notesCheckOutRequest = getNotesCheckoutRequest();
		NotesCheckOutResponse notesCheckOutResponse = (NotesCheckOutResponse) executeService(notesCheckOutRequest);
		if(null != notesCheckOutResponse){
			this.setValidationMessages(notesCheckOutResponse.getMessages());			
			//Adding all the messages to the request
			addAllMessagesToRequest(validationMessages);
			copyValuesToBackingBeanFromResponse(notesCheckOutResponse);
			if(this.getVersion() > 0 && !(this.copyFlag)){
				if(null != this.getHiddenNoteTypeDesc())
				    //	this.setType(this.getHiddenNoteTypeDesc());
					this.setNoteTypeDesc(this.getHiddenNoteTypeDesc());
				else{
					String noteType = (String)this.getSession().getAttribute("noteTypeNew");
					if(null!=this.getSession().getAttribute("noteType")){
						this.setType((String)this.getSession().getAttribute("noteType"));
					}
					this.setNoteTypeDesc(noteType);
				}
				getRequest().getSession().setAttribute("noteType",this.getType());
				getRequest().getSession().setAttribute("noteTypeNew",this.getNoteTypeDesc());
	
			}
			// **Start** change made for String Conversion			
			//this.getSession().setAttribute("noteID",new Integer(this.getNoteId()));
			this.getSession().setAttribute("noteID",this.getNoteId());
			// **End** change made for String Conversion			
			this.getSession().setAttribute("noteName",this.getName());
			this.getSession().setAttribute("noteVersion",new Integer(this.getVersion()));
			this.getSession().setAttribute("noteDesc",notesCheckOutResponse.getNoteBO().getNoteText());
			return "notesEdit";
		}
		return "";
	}
	
	/**
	 * This method copies values from response to the Backing bean.
	 * @param retrieveNotesResponse
	 */
	private void copyValuesToBackingBeanFromResponse(NotesCheckOutResponse notesCheckOutResponse) {
		this.setNoteId(notesCheckOutResponse.getNoteBO().getNoteId());
		this.setName(notesCheckOutResponse.getNoteBO().getNoteName());
		this.setText(notesCheckOutResponse.getNoteBO().getNoteText());
		this.setFormattedNotes(this.getText());
		this.setType(notesCheckOutResponse.getNoteBO().getNoteType());
		List systemDomain = (List) notesCheckOutResponse.getNoteBO().getSystemDomain();
		NoteDomainBO noteSystemDomainBO = null;
		String systemDomainString = "";
		for(int i=1;i<=systemDomain.size();i++){
			noteSystemDomainBO = (NoteDomainBO)systemDomain.get(i-1);
			if(i==systemDomain.size()){
				systemDomainString = systemDomainString + noteSystemDomainBO.getSystemDomainDescription()+"~"+noteSystemDomainBO.getSystemDomainIds().get(0);
			}else{
				systemDomainString = systemDomainString + noteSystemDomainBO.getSystemDomainDescription() +"~"+ noteSystemDomainBO.getSystemDomainIds().get(0)+"~";
			}
		}
		this.systemDomain = systemDomainString;
		this.setStatus(notesCheckOutResponse.getNoteBO().getStatus());
		this.setState(notesCheckOutResponse.getNoteBO().getState().getState());
		this.setCreatedUser(notesCheckOutResponse.getNoteBO().getCreatedUser());
		this.setCreatedTimestamp(notesCheckOutResponse.getNoteBO().getCreatedTimestamp());
		this.setLastUpdatedUser(notesCheckOutResponse.getNoteBO().getLastUpdatedUser());
		this.setLastUpdatedTimestamp(notesCheckOutResponse.getNoteBO().getLastUpdatedTimestamp());
		this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
		this.setVersion(notesCheckOutResponse.getNoteBO().getVersion());
	}

	/**
	 * Method to get NotesLifeCycleRequest.
	 * 
	 * @return NotesLifeCycleRequest
	 */
	private NotesCheckOutRequest getNotesCheckoutRequest() {
		
		//Creating the request
		NotesCheckOutRequest notesCheckOutRequest = (NotesCheckOutRequest) this
		.getServiceRequest(ServiceManager.NOTES_CHECKOUT_REQUEST);
		
		//Setting the search criteria values to the request
		NotesVO notesVO = new NotesVO();
		notesVO.setVersionNo(this.getVersion());
		notesVO.setNoteName(this.getName());
		notesVO.setNoteId(this.getNoteId());	
		notesCheckOutRequest.setNotesVO(notesVO);
		return notesCheckOutRequest;		
	}

	/**
	 * @return
	 */
	private NotesCheckInRequest getNotesCheckInRequest() {
		//Creating request object
		NotesCheckInRequest notesCheckInRequest = (NotesCheckInRequest) this
				.getServiceRequest(ServiceManager.NOTES_CEHCKIN_REQUEST);
		notesCheckInRequest.setNoteId(this.getNoteId());
		notesCheckInRequest.setName(this.getName());
		notesCheckInRequest.setType(this.getType());
		notesCheckInRequest.setText(this.getText());
		this.systemDomainList = WPDStringUtil.getListFromTildaString(this.systemDomain,2,2,2);
		notesCheckInRequest.setSystemDomain(this.systemDomainList);
		notesCheckInRequest.setVersion(this.getVersion());
		notesCheckInRequest.setStatus(this.getStatus());
		notesCheckInRequest.setState(this.getState());
		notesCheckInRequest.setCheckInFlag(this.checkInFlag);
		return notesCheckInRequest;
	}
	
	/**
	 * @return
	 */
	public String copyNotes(){
		this.setCopyFlag(true);
		
		if(this.getVersion() > 0){
			if(null != this.getHiddenNoteTypeDesc())
			    //	this.setType(this.getHiddenNoteTypeDesc());
				this.setNoteTypeDesc(this.getHiddenNoteTypeDesc());
			else{
				String noteType = (String)this.getSession().getAttribute("noteTypeNew");
				if(null!=this.getSession().getAttribute("noteType")){
					this.setType((String)this.getSession().getAttribute("noteType"));
				}
				this.setNoteTypeDesc(noteType);
			}
			getRequest().getSession().setAttribute("noteType",this.getType());
			getRequest().getSession().setAttribute("noteTypeNew",this.getNoteTypeDesc());
			
		}
		this.setMode("Copy");
		String returnPage = retrieveNotes();
		this.setBreadCrumbText("Notes Library >> Note >> Create");
		return "createNotesPage";
	}
	
	/**
	 * @return
	 */
	public NotesCopyRequest getNotesCopyRequest(){
		NotesCopyRequest notesCopyRequest = (NotesCopyRequest)this.getServiceRequest(ServiceManager.NOTES_COPY_REQUEST);
		
		//Setting the search criteria values to the request
		NotesVO notesVO = new NotesVO();
		
		notesVO.setNoteId(this.getNoteId());
		notesVO.setNoteName(this.getName().toUpperCase().trim());
		notesVO.setNoteType(this.getType());
		this.systemDomainList = WPDStringUtil.getListFromTildaString(this.systemDomain,2,2,2);
		notesVO.setSystemDomainList(this.getSystemDomainList());
		notesVO.setVersionNo(this.getVersion());
		notesVO.setText(this.getText().trim());
		notesVO.setStatus(this.getStatus());
		
		notesCopyRequest.setNotesVO(notesVO);
		return notesCopyRequest;
	}
	
	/**
	 * This method copies values from response to the Backing bean.
	 * @param retrieveNotesResponse
	 */
	private void copyValuesToBackingBeanFromResponse(NotesCopyResponse notesCopyResponse) {
		this.setNoteId(notesCopyResponse.getNoteBO().getNoteId());
		this.setName(notesCopyResponse.getNoteBO().getNoteName());
		this.setText(notesCopyResponse.getNoteBO().getNoteText());
		this.setType(notesCopyResponse.getNoteBO().getNoteType());
		this.setState(notesCopyResponse.getNoteBO().getState().getState());
		List systemDomain = (List) notesCopyResponse.getNoteBO().getSystemDomain();
		NoteDomainBO noteSystemDomainBO = null;
		String systemDomainString = "";
		for(int i=1;i<=systemDomain.size();i++){
			noteSystemDomainBO = (NoteDomainBO)systemDomain.get(i-1);
			if(i==systemDomain.size()){
				systemDomainString = systemDomainString + 
				noteSystemDomainBO.getSystemDomainDescription()+"~"+noteSystemDomainBO.getSystemDomainIds().get(0);
			}else{
				systemDomainString = systemDomainString + 
				noteSystemDomainBO.getSystemDomainDescription() +"~"+ noteSystemDomainBO.getSystemDomainIds().get(0)+"~";
			}
		}
		this.systemDomain = systemDomainString;
		this.setStatus(notesCopyResponse.getNoteBO().getStatus());
		this.setCreatedUser(notesCopyResponse.getNoteBO().getCreatedUser());
		this.setCreatedTimestamp(notesCopyResponse.getNoteBO().getCreatedTimestamp());
		this.setLastUpdatedUser(notesCopyResponse.getNoteBO().getLastUpdatedUser());
		this.setLastUpdatedTimestamp(notesCopyResponse.getNoteBO().getLastUpdatedTimestamp());
		this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
		this.setVersion(notesCopyResponse.getNoteBO().getVersion());
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
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	/**
	 * @return Returns the componentList.
	 */
	public List getComponentList() {
		return componentList;
	}
	/**
	 * @param componentList The componentList to set.
	 */
	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}
	/**
	 * @return Returns the productList.
	 */
	public List getProductList() {
		return productList;
	}
	/**
	 * @param productList The productList to set.
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}
	/**
	 * @return Returns the checkInFlag.
	 */
	public boolean isCheckInFlag() {
		return checkInFlag;
	}
	/**
	 * @param checkInFlag The checkInFlag to set.
	 */
	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}
    /**
     * Returns the viewNotesKey
     * @return int viewNotesKey.
     */

    public String getViewNotesKey() {
    	if(null != getRequest().getParameter("notesId")&& null != getRequest().
    	        getParameter("noteName") && null != getRequest().getParameter("noteVersion")){
	        this.noteId  = (String)(getRequest().getParameter("notesId"));
	        this.name  = (String)(getRequest().getParameter("noteName"));
	        this.version = new Integer(getRequest().getParameter("noteVersion")).intValue();
      	}
      	else if(null == getRequest().getParameter("notesId")){
      		this.noteId  = (String)(getRequest().getSession().getAttribute("noteID"));
	        this.name  = (String)(getRequest().getSession().getAttribute("noteName"));
	        this.version = ((Integer)getRequest().getSession().getAttribute("noteVersion")).intValue();
      	}
        this.setMode("View");
     	retrieveNotes(); 
        return "";
    }
    /**
     * Sets the viewNotesKey
     * @param viewNotesKey.
     */

    public void setViewNotesKey(String viewNotesKey) {
        this.viewNotesKey = viewNotesKey;
    }
    
	/**
	 * Returns the noteNameForLifeCycles
	 * @return String noteNameForLifeCycles.
	 */
	public String getNoteNameForLifeCycles() {
		return noteNameForLifeCycles;
	}
	/**
	 * Sets the noteNameForLifeCycles
	 * @param noteNameForLifeCycles.
	 */
	public void setNoteNameForLifeCycles(String noteNameForLifeCycles) {
		this.noteNameForLifeCycles = noteNameForLifeCycles;
	}
	
	/**
	 * Returns the validationMessages
	 * @return List validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * Sets the validationMessages
	 * @param validationMessages.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	
	/**
	 * Returns the copyFlag
	 * @return boolean copyFlag.
	 */
	public boolean isCopyFlag() {
		return copyFlag;
	}
	/**
	 * Sets the copyFlag
	 * @param copyFlag.
	 */
	public void setCopyFlag(boolean copyFlag) {
		this.copyFlag = copyFlag;
	}
	/**
	 * @return Returns the messages.
	 */
	public List getMessages() {
		return messages;
	}
	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}
	
	/**
	 * This method checks if any data is ready for deleting from screen , not from database
	 * @param list,no,id
	 * 
	 */
	public void checkForSoftDelete(List list,int no,String id){
	     if(null != list){
			Iterator iterator = list.iterator();
			String check = new String();
			while(iterator.hasNext()){
				NoteDomainBO noteDomainBO = null;
				try{
					noteDomainBO = (NoteDomainBO)iterator.next();
				}catch(Exception e){
					break;
				}
				
				check = noteDomainBO.getSystemDomainId();
				if(id.equals(check)){
					if(no == 4){
						boolean flag = validationForDelete(this.getQualifierForDeleteList());
						if(flag){
							this.setProductList(this.getProductForDeleteList());
							this.setComponentList(this.getCompForDeleteList());
							this.setBenefitList(this.getBenefitForDeleteList());
							//this.setTermList(newTermList);
							this.setQuestionList(this.getQuestionForDeleteList());
							this.setQualifierList(this.getQualifierForDeleteList());
							this.setFlagForDelete(true);
							break;
						}
					}
					list.remove(noteDomainBO);
					
					if(no == 1){
						this.setProductList(list);
						this.setComponentList(this.getCompForDeleteList());
						this.setBenefitList(this.getBenefitForDeleteList());
						this.setQuestionList(this.getQuestionForDeleteList());
						this.setTermList(this.getTermForDeleteList());
						this.setQualifierList(this.getQualifierForDeleteList());
					}
					else if(no == 2){
						this.setBenefitList(list);
						this.setProductList(this.getProductForDeleteList());
						this.setQuestionList(this.getQuestionForDeleteList());
						this.setComponentList(this.getCompForDeleteList());
						this.setTermList(this.getTermForDeleteList());
						this.setQualifierList(this.getQualifierForDeleteList());

					}
					else if(no == 3){
						this.setComponentList(list);
						this.setProductList(this.getProductForDeleteList());
						this.setBenefitList(this.getBenefitForDeleteList());
						this.setQuestionList(this.getQuestionForDeleteList());
						this.setTermList(this.getTermForDeleteList());
						this.setQualifierList(this.getQualifierForDeleteList());

					}
					else if(no == 4){
						this.setTermList(list);
						this.setProductList(this.getProductForDeleteList());
						this.setComponentList(this.getCompForDeleteList());
						this.setQuestionList(this.getQuestionForDeleteList());
						this.setBenefitList(this.getBenefitForDeleteList());
						this.setQualifierList(this.getQualifierForDeleteList());

					}
					else if(no == 5){
						this.setQualifierList(list);
						this.setProductList(this.getProductForDeleteList());
						this.setQuestionList(this.getQuestionForDeleteList());
						this.setComponentList(this.getCompForDeleteList());
						this.setBenefitList(this.getBenefitForDeleteList());
						this.setTermList(this.getTermForDeleteList());
					}
					else if(no == 6){
					   	this.setQuestionList(list);
						this.setProductList(this.getProductForDeleteList());
						this.setBenefitList(this.getBenefitForDeleteList());
						this.setComponentList(this.getCompForDeleteList());
						this.setQualifierList(this.getQualifierForDeleteList());
						this.setTermList(this.getTermForDeleteList());
					}
				}
			}
	    }
	}
	
	/**
	 * This method validates whether while deleting, any term is deleted without deleting the qualifier.
	 * @param qualifier
	 * @return boolean
	 */
	public boolean validationForDelete(List qualifier){
		int noOfTerms = 0;
		if (this.termForDeleteList !=null)
			noOfTerms = this.termForDeleteList.size(); 
		if(null != qualifier ){
			int sizeQualifier = qualifier.size();
			int noOfQualifiers = sizeQualifier;
			if(noOfQualifiers > 0 && noOfTerms <= 1 && (this.validationForTerm)){
			    validationMessages = new ArrayList();
				validationMessages.add(new ErrorMessage("term.qualifier.delete.validation"));
				addAllMessagesToRequest(validationMessages);
				this.setValidationForTerm(false);
				return true;
			}if(this.validationForTerm){
				this.setValidationForTerm(true);
				return false;
			}
		}
		this.setValidationForTerm(false);
		return false;
	}	
	
	/**
	 * This function does the multiple delete of the data domain.
	 * @return String
	 */
	public String deleteDataDomain(){	    
		this.valueChanged = 1;
		validationMessages = new ArrayList();
		//gets the values from the session
		this.productForDeleteList = (List)this.getSession().getAttribute("productList");
		this.compForDeleteList = (List)this.getSession().getAttribute("compList");
		this.benefitForDeleteList = (List)this.getSession().getAttribute("benefitList");
		this.termForDeleteList = (List)this.getSession().getAttribute("termList");
		this.qualifierForDeleteList = (List)this.getSession().getAttribute("qualifierList");
		this.questionForDeleteList = (List)this.getSession().getAttribute("questionList");
		//To retain the old values, this part gets the values from the oldList and puts it in new List, in case if any validation check fails.
		List newTermList = null;
		if(null != this.termForDeleteList){
			newTermList = getNewList(this.termForDeleteList,newTermList);	
		}
		
		List newQualifierList = null;
		if(null != this.qualifierForDeleteList){
			newQualifierList = getNewList(this.qualifierForDeleteList,newQualifierList);
		}

		List newProductList = null;
		if(null != this.productForDeleteList){
			newProductList = getNewList(this.productForDeleteList,newProductList);
		}
		
		List newBenCompList = null;
		if(null != this.compForDeleteList){
			newBenCompList = getNewList(this.compForDeleteList,newBenCompList);
		}

		List newBenefitList = null;
		if(null != this.benefitForDeleteList){
			newBenefitList = getNewList(this.benefitForDeleteList,newBenefitList);
		}
		
		List newQuestionList = null;
		if(null != this.questionForDeleteList){
		    newQuestionList = getNewList(this.questionForDeleteList,newQuestionList);
		}
		//removes the values from session
		this.getSession().removeAttribute("productList");
		this.getSession().removeAttribute("compList");
		this.getSession().removeAttribute("benefitList");
		this.getSession().removeAttribute("termList");
		this.getSession().removeAttribute("qualifierList");
		this.getSession().removeAttribute("questionList");
		
		if(!"".equals(this.targetHiddenFieldForDelete)){
			
			//gets the tilda seperated string for deletion
			String domainsForDelete = this.getTargetHiddenFieldForDelete();
			String []domainDelete = domainsForDelete.split("~");
			String id = null;
			
			//looks if any domian values are ready for deleting from screen and not from database.
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getProductForDeleteList(),1,id); 
			}
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getBenefitForDeleteList(),2,id); 
			}
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getCompForDeleteList(),3,id); 
			}
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getQualifierForDeleteList(),5,id); 
			}
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getQuestionForDeleteList(),6,id); 
			}
			for(int i = 0;i<domainDelete.length;i=i+3){
				id = domainDelete[i+0];
				checkForSoftDelete(this.getTermForDeleteList(),4,id); 
				if(isFlagForDelete()){
					this.termList = newTermList;
					this.qualifierList = newQualifierList;
					this.productList = newProductList;
					this.componentList = newBenCompList;
					this.benefitList = newBenefitList;
					this.questionList = newQuestionList;
					break;
				}
			}
		}
		
		//sets the session values
		getRequest().getSession().setAttribute("productList",this.productList);
		getRequest().getSession().setAttribute("compList",this.componentList);
		getRequest().getSession().setAttribute("benefitList",this.benefitList);
		getRequest().getSession().setAttribute("termList",this.termList);
		getRequest().getSession().setAttribute("qualifierList",this.qualifierList);
		getRequest().getSession().setAttribute("questionList",this.questionList);		
		
		if(isFlagForDelete()){
			return "";
		}
		//create the request
		DeleteNotesRequest deleteNotesRequest = (DeleteNotesRequest)
				this.getServiceRequest(ServiceManager.DELETE_NOTES);
		// set the note id in the request
		deleteNotesRequest.setDataDomainDelete(true);
		if(!"".equals(this.targetHiddenFieldForDelete)){
			String domainsForDelete = this.getTargetHiddenFieldForDelete();
			String []domainDelete = domainsForDelete.split("~");
			NoteDomainVO domainVO = null;
			List note = new ArrayList();
			for(int i = 0;i<domainDelete.length;i=i+3){
				domainVO = new NoteDomainVO();
				domainVO.setNotesId(domainDelete[i+2]);
				domainVO.setDomainId(domainDelete[i+0]);
				domainVO.setDomainType(domainDelete[i+1]);
				note.add(domainVO);
			}
			deleteNotesRequest.setNoteDomains(note);
		}
		deleteNotesRequest.setNotesName(this.name);
		deleteNotesRequest.setVersionNumber(this.version);
		deleteNotesRequest.setState(this.state);
		deleteNotesRequest.setNotesId(this.noteId);

		// get the response
		DeleteNotesResponse deleteNotesResponse =  
		    	(DeleteNotesResponse)executeService(deleteNotesRequest);
		if (null != deleteNotesResponse) {
			this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> Edit");
				this.setState(deleteNotesResponse.getState());
				this.setMessages(deleteNotesResponse.getMessages());
				if(null == this.productList || this.productList.size()==0){
					this.productList = null;
				}
				if(null == this.componentList || this.componentList.size()==0){
					this.componentList = null;
				}
				if(null == this.benefitList || this.benefitList.size()==0){
					this.benefitList = null;
				}
				if(null == this.questionList || this.questionList.size()==0){
					this.questionList = null;
				}
				if(null == this.termList || this.termList.size()==0){
					this.termList = null;
				}
				if(null == this.qualifierList || this.qualifierList.size()==0){
					this.qualifierList = null;
				}
				return "";
		}
		return WebConstants.EMPTY_STRING;
	}
	
	/**
	 * This method gets the values from the old list and puts it in the new list.
	 * @param oldList,newList
	 * @return List
	 */
	public List getNewList(List oldList,List newList){
		NoteDomainBO domainBO = null;
		Iterator iterator = null;
		if(null != oldList){
			newList = new ArrayList(oldList.size());
			iterator = oldList.iterator();
			while(iterator.hasNext()){
				domainBO = (NoteDomainBO)iterator.next();
				newList.add(domainBO);
			}
		}
		return newList;
	}
	/**
	 * This method is load the data domain page when the data domain tab 
	 * is clicked for VIEW
	 * @return
	 */
	public String loadDataDomain(){
	    getNoteDomains();
	    this.setBreadCrumbText("Notes Library >> Note (" + this.name + ") >> View");
	    return "loadDataDomainForNotes";
	}
	
	/**
	 * This method is to load the general info page.
	 * @return
	 */
	public String loadGeneralInfoForView(){
	    return "loadGeneralInfoForNotes";
	}
	
	public String getViewAllNotes() {
		List noteTypeList = (List)this.getSession().getAttribute("noteTypeList");		
		ViewAllVersionsNotesRequest viewAllVersionsNotesRequest = (ViewAllVersionsNotesRequest)this.getServiceRequest(ServiceManager.NOTES_ALLVERSIONS_REQUEST);
		if(getRequest().getParameter("noteID")!= null && getRequest().getParameter("noteName")!= null){
			if(null!=getRequest().getParameter("noteID")  && getRequest().getParameter("noteID").matches("^[0-9a-zA-Z_]+$")){
				String noteId = (String)(getRequest().getParameter("noteID"));
			}
			String noteName = (String) (ESAPI.encoder().encodeForHTML(getRequest().getParameter("noteName")));	
			if(null!=noteName ){
				noteName = noteName;
			}else{
				noteName=null;
			}
			this.setBreadCrumbText("Notes Library >> Note (" + noteName + ") >> View All Versions");
			// **Start** change made for String Conversion			
			//viewAllVersionsNotesRequest.setNoteId(Integer.parseInt(noteId));
			viewAllVersionsNotesRequest.setNoteId(noteId);
			// **End** change made for String Conversion			
			viewAllVersionsNotesRequest.setNoteName(noteName);
			this.getSession().setAttribute("noteName",noteName);
			this.getSession().setAttribute("noteID",noteId);
		}else if(this.getSession().getAttribute("noteID") != null && getSession().getAttribute("noteName")!= null){
		    String noteId = (String)(getSession().getAttribute("noteID"));
			String noteName = (String) (getSession().getAttribute("noteName"));	
			this.setBreadCrumbText("Notes Library >> Note (" + noteName + ") >> View All Versions");
			// **Start** change made for String Conversion			
			//viewAllVersionsNotesRequest.setNoteId(Integer.parseInt(noteId));
			viewAllVersionsNotesRequest.setNoteId(noteId);
			// **End** change made for String Conversion			
			viewAllVersionsNotesRequest.setNoteName(noteName);
			this.getSession().setAttribute("noteName",noteName);
			this.getSession().setAttribute("noteID",noteId);
		}
		viewAllVersionsNotesRequest.setAction("allversion");
		ViewAllVersionsNotesResponse viewAllVersionsNotesResponse = 
    	(ViewAllVersionsNotesResponse)executeService(viewAllVersionsNotesRequest);						
		if(viewAllVersionsNotesResponse.getAllVersionList().size() == 0 || null == viewAllVersionsNotesResponse.getAllVersionList()){
		    this.setAllVersionList(null);
		}else{
		List list = viewAllVersionsNotesResponse.getAllVersionList();
		if(null != list && !list.isEmpty()){
			Iterator ite = list.iterator();
			while(ite.hasNext()){
				NoteBO noteBO = (NoteBO)ite.next();
				String desc = noteBO.getNoteText();
				String name = noteBO.getNoteName();
				noteBO.setOldNoteName(name);
				if(desc.length() > 12){
					desc = desc.substring(0,12)+"...";					
				}
				if(name.length()>15){
					name = name.substring(0,15)+"...";
				}
				noteBO.setNoteText(desc);
				noteBO.setNoteName(name);
				
				if(null != noteTypeList){
					Iterator iterator = noteTypeList.listIterator();
			        while(iterator.hasNext()){
			        	SelectItem referenceData = (SelectItem)iterator.next();
			        	if(noteBO.getNoteType().equals(referenceData.getValue())){
			        		noteBO.setNoteType(referenceData.getLabel());
			        	}
			        }
				}
			}
		}
		this. setAllVersionList(list);	
	}
	
	if(this.getSession().getAttribute("lifecyclemessages")!= null){
		validationMessages = (List)(this.getSession().getAttribute("lifecyclemessages"));
	}
	if(this.getSession().getAttribute("deletemessage")!= null){
		validationMessages = (List)(this.getSession().getAttribute("deletemessage"));
	}
	addAllMessagesToRequest(validationMessages);
	this.getSession().removeAttribute("lifecyclemessages");
	this.getSession().removeAttribute("deletemessage");
	return WebConstants.EMPTY_STRING;
	}
	/**
	 * @return Returns the allVersionList.
	 */
	public List getAllVersionList() {
		return allVersionList;
	}
	/**
	 * @param allVersionList The allVersionList to set.
	 */
	public void setAllVersionList(List allVersionList) {
		this.allVersionList = allVersionList;
	}
	
	/**
	 * @return Returns the printEdit.
	 */
	public String getPrintEdit() {
		this.setMode("Print");
		retrieveNotes();
		return "";
	}
	/**
	 * @param printEdit The printEdit to set.
	 */
	public void setPrintEdit(String printEdit) {
		this.printEdit = printEdit;
	}
	/**
	 * @return Returns the printValueForGenInfoEdit.
	 */
	public String getPrintValueForGenInfoEdit() {
		return printValueForGenInfoEdit;
	}
	/**
	 * @param printValueForGenInfoEdit The printValueForGenInfoEdit to set.
	 */
	public void setPrintValueForGenInfoEdit(String printValueForGenInfoEdit) {
		this.printValueForGenInfoEdit = printValueForGenInfoEdit;
	}
	/**
	 * @return Returns the printDataDomain.
	 */
	public String getPrintDataDomain() {
		this.productForDeleteList = (List)this.getSession().getAttribute("productList");
		this.compForDeleteList = (List)this.getSession().getAttribute("compList");
		this.benefitForDeleteList = (List)this.getSession().getAttribute("benefitList");
		this.termForDeleteList = (List)this.getSession().getAttribute("termList");
		this.qualifierForDeleteList = (List)this.getSession().getAttribute("qualifierList");
		this.questionForDeleteList = (List)this.getSession().getAttribute("questionList");
		
		this.getSession().removeAttribute("productList");
		this.getSession().removeAttribute("compList");
		this.getSession().removeAttribute("benefitList");
		this.getSession().removeAttribute("termList");
		this.getSession().removeAttribute("qualifierList");
		this.getSession().removeAttribute("questionList");
		
		loadNoteDomains();
		getRequest().getSession().setAttribute("productList",this.productForDeleteList);
		getRequest().getSession().setAttribute("compList",this.compForDeleteList);
		getRequest().getSession().setAttribute("benefitList",this.benefitForDeleteList);
		getRequest().getSession().setAttribute("termList",this.termForDeleteList);
		getRequest().getSession().setAttribute("qualifierList",this.qualifierForDeleteList);
		getRequest().getSession().setAttribute("questionList",this.questionForDeleteList);		

		return "";
	}
	/**
	 * @param printDataDomain The printDataDomain to set.
	 */
	public void setPrintDataDomain(String printDataDomain) {
		this.printDataDomain = printDataDomain;
	}
	/**
	 * @return Returns the noteTypeDesc.
	 */
	public String getNoteTypeDesc() {
		return noteTypeDesc;
	}
	/**
	 * @param noteTypeDesc The noteTypeDesc to set.
	 */
	public void setNoteTypeDesc(String noteTypeDesc) {
		this.noteTypeDesc = noteTypeDesc;
	}
	/**
	 * @return Returns the noteTypeList.
	 */
	public List getNoteTypeList() {
		return noteTypeList;
	}
	/**
	 * @param noteTypeList The noteTypeList to set.
	 */
	public void setNoteTypeList(List noteTypeList) {
		this.noteTypeList = noteTypeList;
	}
	/**
	 * @return Returns the noDataDomainMessage.
	 */
	public String getNoDataDomainMessage() {
		return noDataDomainMessage;
	}
	/**
	 * @param noDataDomainMessage The noDataDomainMessage to set.
	 */
	public void setNoDataDomainMessage(String noDataDomainMessage) {
		this.noDataDomainMessage = noDataDomainMessage;
	}
    /**
     * @return Returns the qualifierListSize.
     */
    public int getQualifierListSize() {
        return qualifierListSize;
    }
    /**
     * @param qualifierListSize The qualifierListSize to set.
     */
    public void setQualifierListSize(int qualifierListSize) {
        this.qualifierListSize = qualifierListSize;
    }
    
    /**
     * Returns the benefitComponentTxt
     * @return String benefitComponentTxt.
     */
    public String getBenefitComponentTxt() {
        return benefitComponentTxt;
    }
    /**
     * Sets the benefitComponentTxt
     * @param benefitComponentTxt.
     */
    public void setBenefitComponentTxt(String benefitComponentTxt) {
        this.benefitComponentTxt = benefitComponentTxt;
    }
    /**
     * Returns the benefitTxt
     * @return String benefitTxt.
     */
    public String getBenefitTxt() {
        return benefitTxt;
    }
    /**
     * Sets the benefitTxt
     * @param benefitTxt.
     */
    public void setBenefitTxt(String benefitTxt) {
        this.benefitTxt = benefitTxt;
    }
    /**
     * Returns the productTxt
     * @return String productTxt.
     */
    public String getProductTxt() {
        return productTxt;
    }
    /**
     * Sets the productTxt
     * @param productTxt.
     */
    public void setProductTxt(String productTxt) {
        this.productTxt = productTxt;
    }
    
	/**
	 * Returns the checkForEdit
	 * @return boolean checkForEdit.
	 */
	public boolean isCheckForEdit() {
		return checkForEdit;
	}
	/**
	 * Sets the checkForEdit
	 * @param checkForEdit.
	 */
	public void setCheckForEdit(boolean checkForEdit) {
		this.checkForEdit = checkForEdit;
	}
	
	/**
	 * Returns the businessEntity
	 * @return String businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * Sets the businessEntity
	 * @param businessEntity.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * Returns the businessGroup
	 * @return String businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * Sets the businessGroup
	 * @param businessGroup.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * Returns the lob
	 * @return String lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * Sets the lob
	 * @param lob.
	 */
	public void setLob(String lob) {
		this.lob = lob;

	}
	
		/**
	 * @return Returns the view.
	 */
	public boolean isView() {
		return view;
	}
	/**
	 * @param view The view to set.
	 */
	public void setView(boolean view) {
		this.view = view;
	}
	
	/**
	 * Returns the flagForDomains
	 * @return String flagForDomains.
	 */
	public String getFlagForDomains() {
		return flagForDomains;
	}
	/**
	 * Sets the flagForDomains
	 * @param flagForDomains.
	 */
	public void setFlagForDomains(String flagForDomains) {
		this.flagForDomains = flagForDomains;
	}
	
	/**
	 * Returns the benefitForDeleteList
	 * @return List benefitForDeleteList.
	 */
	public List getBenefitForDeleteList() {
		return benefitForDeleteList;
	}
	/**
	 * Sets the benefitForDeleteList
	 * @param benefitForDeleteList.
	 */
	public void setBenefitForDeleteList(List benefitForDeleteList) {
		this.benefitForDeleteList = benefitForDeleteList;
	}
	/**
	 * Returns the compForDeleteList
	 * @return List compForDeleteList.
	 */
	public List getCompForDeleteList() {
		return compForDeleteList;
	}
	/**
	 * Sets the compForDeleteList
	 * @param compForDeleteList.
	 */
	public void setCompForDeleteList(List compForDeleteList) {
		this.compForDeleteList = compForDeleteList;
	}
	/**
	 * Returns the productForDeleteList
	 * @return List productForDeleteList.
	 */
	public List getProductForDeleteList() {
		return productForDeleteList;
	}
	/**
	 * Sets the productForDeleteList
	 * @param productForDeleteList.
	 */
	public void setProductForDeleteList(List productForDeleteList) {
		this.productForDeleteList = productForDeleteList;
	}
	/**
	 * Returns the qualifierForDeleteList
	 * @return List qualifierForDeleteList.
	 */
	public List getQualifierForDeleteList() {
		return qualifierForDeleteList;
	}
	/**
	 * Sets the qualifierForDeleteList
	 * @param qualifierForDeleteList.
	 */
	public void setQualifierForDeleteList(List qualifierForDeleteList) {
		this.qualifierForDeleteList = qualifierForDeleteList;
	}
	/**
	 * Returns the termForDeleteList
	 * @return List termForDeleteList.
	 */
	public List getTermForDeleteList() {
		return termForDeleteList;
	}
	/**
	 * Sets the termForDeleteList
	 * @param termForDeleteList.
	 */
	public void setTermForDeleteList(List termForDeleteList) {
		this.termForDeleteList = termForDeleteList;
	}
	
	/**
	 * Returns the flagForDelete
	 * @return boolean flagForDelete.
	 */
	public boolean isFlagForDelete() {
		return flagForDelete;
	}
	/**
	 * Sets the flagForDelete
	 * @param flagForDelete.
	 */
	public void setFlagForDelete(boolean flagForDelete) {
		this.flagForDelete = flagForDelete;
	}
	
	/**
	 * Returns the flagForNavigation
	 * @return boolean flagForNavigation.
	 */
	public boolean isFlagForNavigation() {
		return flagForNavigation;
	}
	/**
	 * Sets the flagForNavigation
	 * @param flagForNavigation.
	 */
	public void setFlagForNavigation(boolean flagForNavigation) {
		this.flagForNavigation = flagForNavigation;
	}
	
	/**
	 * Returns the validationForTerm
	 * @return boolean validationForTerm.
	 */
	public boolean isValidationForTerm() {
		return validationForTerm;
	}
	/**
	 * Sets the validationForTerm
	 * @param validationForTerm.
	 */
	public void setValidationForTerm(boolean validationForTerm) {
		this.validationForTerm = validationForTerm;
	}
	
	/**
	 * Returns the hiddenNoteTypeDesc
	 * @return String hiddenNoteTypeDesc.
	 */
	public String getHiddenNoteTypeDesc() {
		return hiddenNoteTypeDesc;
	}
	/**
	 * Sets the hiddenNoteTypeDesc
	 * @param hiddenNoteTypeDesc.
	 */
	public void setHiddenNoteTypeDesc(String hiddenNoteTypeDesc) {
		this.hiddenNoteTypeDesc = hiddenNoteTypeDesc;
	}
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Notes Library >> Note ("+getRequest().getSession().getAttribute("noteName")+") >> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
	
	/**
	 * @return Returns the mode.
	 */
	public String getMode() {
		return Mode;
	}
	/**
	 * @param mode The mode to set.
	 */
	public void setMode(String mode) {
		Mode = mode;
	}
	
	/**
	 * @return Returns the systemDomainCode.
	 */
	public String getSystemDomainCode() {
		String noteID = getRequest().getParameter("noteId");
		if(null != noteID && !noteID.equals(WebConstants.EMPTY_STRING)){
			RetrieveNotesRequest retrieveNotesRequest = (RetrieveNotesRequest)
			this.getServiceRequest(ServiceManager.RETRIEVE_NOTES_REQUEST);
			retrieveNotesRequest.setNoteId(noteID);
			retrieveNotesRequest.setAction("targetSystems");
			
			RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse)executeService(retrieveNotesRequest);
			
			this.setNotesInActivelyUsedStatus(retrieveNotesResponse.isNotesInActivelyUsedStatus());	
			if(null!= getRequest().getParameter("notesInActivelyUsedStatus")
					&& this.notesInActivelyUsedStatus)
				return getRequest().getParameter("systemDomain");
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * @param systemDomainCode The systemDomainCode to set.
	 */
	public void setSystemDomainCode(String systemDomainCode) {
		this.systemDomainCode = systemDomainCode;
	}
	
	/**
	 * @return Returns the notesInActivelyUsedStatus.
	 */
	public boolean isNotesInActivelyUsedStatus() {
		return notesInActivelyUsedStatus;
	}
	/**
	 * @param notesInActivelyUsedStatus The notesInActivelyUsedStatus to set.
	 */
	public void setNotesInActivelyUsedStatus(boolean notesInActivelyUsedStatus) {
		this.notesInActivelyUsedStatus = notesInActivelyUsedStatus;
	}
	/**
	 * @return Returns the valueChanged.
	 */
	public int getValueChanged() {
		return valueChanged;
	}
	/**
	 * @param valueChanged The valueChanged to set.
	 */
	public void setValueChanged(int valueChanged) {
		this.valueChanged = valueChanged;
	}
	/**
	 * @return Returns the flagForDomainPage.
	 */
	public String getFlagForDomainPage() {
		return flagForDomainPage;
	}
	/**
	 * @param flagForDomainPage The flagForDomainPage to set.
	 */
	public void setFlagForDomainPage(String flagForDomainPage) {
		this.flagForDomainPage = flagForDomainPage;
	}
	/**
	 * @return Returns the identifyTab.
	 */
	public String getIdentifyTab() {
		return identifyTab;
	}
	/**
	 * @param identifyTab The identifyTab to set.
	 */
	public void setIdentifyTab(String identifyTab) {
		this.identifyTab = identifyTab;
	}
	/**
	 * @return Returns the targetHiddenFieldForDelete.
	 */
	public String getTargetHiddenFieldForDelete() {
		return targetHiddenFieldForDelete;
	}
	/**
	 * @param targetHiddenFieldForDelete The targetHiddenFieldForDelete to set.
	 */
	public void setTargetHiddenFieldForDelete(String targetHiddenFieldForDelete) {
		this.targetHiddenFieldForDelete = targetHiddenFieldForDelete;
	}
	/**
	 * @return Returns the noteDescHidden.
	 */
	public String getNoteDescHidden() { 
		return noteDescHidden;
	}
	/**
	 * @param noteDescHidden The noteDescHidden to set.
	 */
	public void setNoteDescHidden(String noteDescHidden) {
		this.noteDescHidden = noteDescHidden;
	}
    /**
     * @return Returns the questionTxt.
     */
    public String getQuestionTxt() {
        return questionTxt;
    }
    /**
     * @param questionTxt The questionTxt to set.
     */
    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }
    /**
     * @return Returns the question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * @param question The question to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * @return Returns the questionList.
     */
    public List getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList The questionList to set.
     */
    public void setQuestionList(List questionList) {
        this.questionList = questionList;
    }
    /**
     * @return Returns the questionForDeleteList.
     */
    public List getQuestionForDeleteList() {
        return questionForDeleteList;
    }
    /**
     * @param questionForDeleteList The questionForDeleteList to set.
     */
    public void setQuestionForDeleteList(List questionForDeleteList) {
        this.questionForDeleteList = questionForDeleteList;
    }
    

    /**
     * The method will take the list of all the versions of the note
     * and the coresponding domeined entitites.It then creates two different lists, 
     * one for the current version domained objects,and one for the other versions
     * It then iterates the current version List and if any of the objects of
     * the current version exists in the otherversions List, then that NoteDomainBO 
     * object should not have a checkbox in the datadomain page.
     * @param inputList- This list will contain all the versions of the note
     * and the coresponding domeined entitites.
     * @param mode - whether edit or view
     * @return
     */
    private List setCheckBoxToDataTable(List inputList,String mode){
        int currVersion = this.version;
        List currVersionList = new ArrayList();
        List otherVersionsList = new ArrayList();
        
        for(int i=0;i<inputList.size();i++){
            NoteDomainBO noteDomainBO = (NoteDomainBO)inputList.get(i);
            if(noteDomainBO.getVersion() == currVersion){
                // This list will contain all the domained objects in the current version of the note
                currVersionList.add(noteDomainBO); 
            }
            else{
                otherVersionsList.add(noteDomainBO);
            }
        }
        if("Edit".equals(mode)){//if edit mode, then have to check for Checkbox display
	        for(int j=0;j<currVersionList.size();j++){
	            NoteDomainBO noteDomainBO = (NoteDomainBO)currVersionList.get(j);	          
                if(otherVersionsList.contains(noteDomainBO)){
                    noteDomainBO.setCheckBoxVal(false);
                }else{
                    noteDomainBO.setCheckBoxVal(true);
                }	          
	        }
        }
        return currVersionList;
    }
    
    /**
     * The method will set a flag in the elements of a List
     * @param inputList
     * @return
     */
    private List setCheckBoxToList(List inputList){
        if(null!=inputList && inputList.size()>0){
            for(int i=0;i<inputList.size();i++){
                NoteDomainBO noteDomainBO = (NoteDomainBO)inputList.get(i);
                noteDomainBO.setCheckBoxVal(true);
            }
        }        
        return inputList; 
    }
    
    /**
     * The method will compare two lists.If the first list contains any of the 
     * second list elements, a flag in the first list object is set to true.
     * @param listOfAllValues
     * @param lisOfNewValues
     * @return
     */
    private List compareAndSetCheckBox(List listOfAllValues,List lisOfNewValues ){		    
		if((null != listOfAllValues && listOfAllValues.size() >0 ) 
		        && (null != lisOfNewValues && lisOfNewValues.size() >0 )){		    
			for(int i=0;i<listOfAllValues.size();i++){
			   NoteDomainBO noteDomainBO2 =  (NoteDomainBO)listOfAllValues.get(i);
			   for(int j=0;j<lisOfNewValues.size();j++){
			    if(lisOfNewValues.contains(noteDomainBO2)){
			        noteDomainBO2.setCheckBoxVal(true);
			       }
		        else{
		           noteDomainBO2.setCheckBoxVal(false); 
		        }			    
			   }
			}	
		}
		return listOfAllValues;
	}
    
    
	/**
	 * The method will add new objects to a List in Session
	 * @param value - The ~ separated String
	 * @param name - name of the List in session
	 * @return
	 */
	private List addNewObjectToNewList(String value,String name){
	    
		List list = (List)this.getSession().getAttribute(name);
		//if the list is not presesnt in session,creating a new List
		if(null == list){
		    list = new ArrayList();
		}
		//if list is already present in session,but no new 
		//objects to be added in this List,then return this List
		if(null!=list && ("".equals(value) || null == value)){
		    return list;
		}
		String [] valueArray = value.split("~");
		NoteDomainBO noteDomainBO = null;
		for(int i=0;i<valueArray.length;i++){
			noteDomainBO = new NoteDomainBO();
			noteDomainBO.setSystemDomainDescription(valueArray[i]);
			i++;
			noteDomainBO.setSystemDomainId(valueArray[i].toString());
			if(("1").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILPRODUCT");				
			}else if(("2").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILCOMP");
			}else if(("3").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILBENEFIT");
			}else if(("4").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILTERM");
			}else if(("5").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILQUALIFIER");
			}else if(("6").equals(this.getFlagForDomains())){
				noteDomainBO.setEntityType("AVAILQUESTION"); 
			}
			noteDomainBO.setNoteId(this.noteId);
			list.add(noteDomainBO);
		}
		return list;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
