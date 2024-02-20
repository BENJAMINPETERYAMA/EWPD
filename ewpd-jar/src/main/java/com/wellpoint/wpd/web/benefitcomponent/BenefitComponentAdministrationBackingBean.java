/*
 * BenefitComponentAdministrationBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentQuesitionnaireBO;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateComponentsBenefitAdministrationRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.UpdateComponentsBenefitAdministrationRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.BnftCompNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.BnftCompNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.vo.NotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.util.StringUtil;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * BenefitComponentAdministrationBackingBean contains the getters and setters of the 
 * variables and respective functions
 */
public class BenefitComponentAdministrationBackingBean extends WPDBackingBean{
	
	// variable declarations
	
	HtmlPanelGrid questionnarePanel = null;
  
    HtmlPanelGrid headerPanel = null;

    private Map datafieldMap = new LinkedHashMap();

    private Map datafieldMapForQuestionId = new LinkedHashMap();

    private Map datafieldMapForAnswerId = new LinkedHashMap();
    
    private Map datafieldmapForQuestionHideFlag = new LinkedHashMap();

    private List benefitAdminList;
    
    private List benefitAdministrationList = new ArrayList(1);
    
    HtmlPanelGrid viewPanel = null;
    
    private String componentType;
    
    private boolean showHiddenSelected;
    //added new field for solving performance issue
    private HashMap hiddenAnswerMap = new HashMap();
    
    private HashMap hiddenQuestionFlagMap = new HashMap();
    
    private boolean allQuestionsHidden;
    
    private List quesitionnaireList;
    
    private List orgQuestionnaireList;

	private List newQuestions             = null;
	
	private List modifiedQuestions        = null;
	
	private List removedQuestions         = null;
	
	java.util.HashMap possibleAnswerMap;

	public List viewQuestionnaireList;

	private String questionId;

	private String noteId;

	private String noteVersion;

	private String noteStatus;

	private String records;

	private List questionNotes;

	private String searchString;

	int i = 0;

	private String primaryEntityID;

	private String prevNoteIdSelected;

	private int previousNoteVersion;

	private String noteName;

	private String version;

	private String newNoteId;
	
	private String tildaNoteStatus ;
	
	private String noteAttached;
	
	   
	public String getAdminLvlOptionAssnSysId() {
		return adminLvlOptionAssnSysId;
	}
	public void setAdminLvlOptionAssnSysId(String adminLvlOptionAssnSysId) {
		this.adminLvlOptionAssnSysId = adminLvlOptionAssnSysId;
	}
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	public String getBenefitDefnId() {
		return benefitDefnId;
	}
	public void setBenefitDefnId(String benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	public String getPrimaryType() {
		return primaryType;
	}
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	private String primaryType ;
	
	private String benefitComponentId ;
	
	private String benefitDefnId ;
	
	private String adminLvlOptionAssnSysId;
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	public void setRecords(String records) {
		this.records = records;
	}
    List messageList =null;
    private int childCount;
	/**
	 * @return Returns the childCount.
	 */
	public int getChildCount() {
		return childCount;
	}
	/**
	 * @param childCount The childCount to set.
	 */
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
    
	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return Returns the rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
    private int rowNum;
    
    private int answerId;
    
    private String answerDesc="";

	private boolean questionListRetrieved;
    
    /**
     * @return hiddenAnswerMap
     * 
     * Returns the hiddenAnswerMap.
     */
    public HashMap getHiddenAnswerMap() {
        return hiddenAnswerMap;
    }
    /**
     * @param hiddenAnswerMap
     * 
     * Sets the hiddenAnswerMap.
     */
    public void setHiddenAnswerMap(HashMap hiddenAnswerMap) {
        this.hiddenAnswerMap = hiddenAnswerMap;
    }
    /**
     * @return hiddenQuestionFlagMap
     * 
     * Returns the hiddenQuestionFlagMap.
     */
    public HashMap getHiddenQuestionFlagMap() {
        return hiddenQuestionFlagMap;
    }
    /**
     * @param hiddenQuestionFlagMap
     * 
     * Sets the hiddenQuestionFlagMap.
     */
    public void setHiddenQuestionFlagMap(HashMap hiddenQuestionFlagMap) {
        this.hiddenQuestionFlagMap = hiddenQuestionFlagMap;
    }
    /**
     * Constructor to set the bread crumb text.
     *
     */
    public BenefitComponentAdministrationBackingBean(){
        super();
        BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
        if (mode.equals("View")){
        	this.setBreadCrumbText("Product Configuration >> Benefit Component ("
                    + benefitComponentSessionObject.getBenefitComponentName() + ") >> View");
        }
        else{
        this.setBreadCrumbText("Product Configuration >> Benefit Component ("
                + benefitComponentSessionObject.getBenefitComponentName() + ") >> Selected Questions >> Edit");
        }
    }
        
    public String loadQuestionsAction(){
    	if("View".equals(getBenefitComponentSessionObject().getBenefitComponentMode())) {
    		return "benefitComponentQuestionnaireView";
    		
    	} else {
    		
    		RetrieveBenefitComponentQuestionnairRequest retrieveBenefitComponentQuestionnairRequest = 
    			getRetrieveBenefitComponentQuestionnairRequest();
    		
//    		Getting the Map containing questionNumbers & possible answer lists.
    		possibleAnswerMap = getAllPossibleAnswersForAdminOption(); 
    		retrieveBenefitComponentQuestionnairRequest.setAllPossibleAnswerMap(possibleAnswerMap);
    		retrieveBenefitComponentQuestionnairRequest.setSelectedAnswerDesc(answerDesc);   		
    		retrieveBenefitComponentQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST);
            // Call the executeService() to get the response
            LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
            								(LocateComponentsBenefitAdministrationResponse) this
											.executeService(retrieveBenefitComponentQuestionnairRequest);
            if(null!=benefitAdministrationResponse){
    		quesitionnaireList = benefitAdministrationResponse.getQuestionnareList() != null ?
    				benefitAdministrationResponse.getQuestionnareList() : new ArrayList();
    		BenefitComponentSessionObject benefitComponentSessionObject = 
            								(BenefitComponentSessionObject) getSession().
											 getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
    		benefitComponentSessionObject.setAllPossibleAnswerMap(possibleAnswerMap);
    		
    		if(null!=quesitionnaireList&&quesitionnaireList.size()>0){
    		benefitComponentSessionObject.setQuestionareList(quesitionnaireList);
    		benefitComponentSessionObject.setAdminLevelOptionSysId(((BenefitComponentQuesitionnaireBO)quesitionnaireList.get(0)).getAdminLevelOptionSysId());
    		}
//    		Creating original questionnaire list and setting in session
        	orgQuestionnaireList = new ArrayList();
        	Iterator it = quesitionnaireList.iterator();
        	while(it.hasNext()){
        		BenefitComponentQuesitionnaireBO bcQueBO = (BenefitComponentQuesitionnaireBO)it.next();
        		BenefitComponentQuesitionnaireBO orgBcQueBO = new BenefitComponentQuesitionnaireBO();
        		
        		orgBcQueBO.setQuestionnaireId(       bcQueBO.getQuestionnaireId()  );
        		orgBcQueBO.setQuestionId(            bcQueBO.getQuestionId()  );
        		orgBcQueBO.setParentQuestionnaireId( bcQueBO.getParentQuestionnaireId()  );
        		orgBcQueBO.setSelectedAnswerid(      bcQueBO.getSelectedAnswerid()  );
        		orgBcQueBO.setSelectedAnswerDesc(    bcQueBO.getSelectedAnswerDesc()  );
        		orgBcQueBO.setAdminLevelOptionSysId( bcQueBO.getAdminLevelOptionSysId()  );
        		orgBcQueBO.setBenefitComponentId(    bcQueBO.getBenefitComponentId() );
        		orgBcQueBO.setBenefitComponentId(    bcQueBO.getBenefitComponentId()  );
        		orgBcQueBO.setBnftDefId(             bcQueBO.getBnftDefId()  );
        		orgBcQueBO.setChildCount(            bcQueBO.getChildCount()  );
        		orgBcQueBO.setLevel(                 bcQueBO.getLevel()  );
        		orgBcQueBO.setNotesExists(           bcQueBO.getNotesExists() );
        		orgBcQueBO.setPossibleAnswerList(    bcQueBO.getPossibleAnswerList() );
        		orgBcQueBO.setQuestionOrder(         bcQueBO.getQuestionOrder() );
        		orgBcQueBO.setReferenceId(           bcQueBO.getReferenceId() );
        		orgBcQueBO.setTierSysId(             bcQueBO.getTierSysId() );
        		
        		orgQuestionnaireList.add(orgBcQueBO); 
        	}	 
        	// remove not answered questions from list, before setting to session. 
        	benefitComponentSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	
    		preparePanel(quesitionnaireList);  
    		
            }
            return "benefitComponentQuestionnaireEdit";
    	}
    }

	/**
	 * Method to return possible answer list for all questions attached to an admin option.
	 * @return HashMap
	 */
	public java.util.HashMap getAllPossibleAnswersForAdminOption(){
		
    		//14 jan 2011 - Code optimization for - get possible answers
    	RetrieveAllPossibleAnswerRequest retrieveAllPossibleAnswerRequest = getRetrieveAllPossibleAnswerRequest();
	        // Call the executeService() to get the response
    	RetrieveAllPossibleAnswerResponse 
				retrieveAllPossibleAnswerResponse =
					(RetrieveAllPossibleAnswerResponse) this
						.executeService(retrieveAllPossibleAnswerRequest);
    	java.util.HashMap allPossibleAnswerMap = new java.util.HashMap();
	        List allPossibleAnswerList = null;
	        if(null!=retrieveAllPossibleAnswerResponse && null !=retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList() && 
	        		retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList().size() >0){
	        	
	        	allPossibleAnswerList = (List)retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList();
	        	Iterator it1 = allPossibleAnswerList.iterator();
	        	int count =0;
	        		while(it1.hasNext()){
	        			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO)it1.next();
	        			int questionNumber = possibleAnswerBO.getQuestionNumber();
	        			ArrayList possibleAnswerList = allPossibleAnswerMap.get(new Integer(questionNumber))!=null?
	        					(ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)):
	        						new ArrayList();
	        			possibleAnswerList.add(possibleAnswerBO);
	        			allPossibleAnswerMap.put(new Integer(questionNumber),possibleAnswerList);
	        			        			
	        		}
	        	
	        }
	        return allPossibleAnswerMap;
    }
    
    private void loadQuestion(){
    	
    	
    	RetrieveBenefitComponentQuestionnairRequest retrieveBenefitComponentQuestionnairRequest = 
			getRetrieveBenefitComponentQuestionnairRequest();
		 retrieveBenefitComponentQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST);
        // Call the executeService() to get the response
        LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
        								(LocateComponentsBenefitAdministrationResponse) this
										.executeService(retrieveBenefitComponentQuestionnairRequest);
        if(null!=benefitAdministrationResponse){
		quesitionnaireList = benefitAdministrationResponse.getQuestionnareList();
		BenefitComponentSessionObject benefitComponentSessionObject = 
        								(BenefitComponentSessionObject) getSession().
										 getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		if(null!=quesitionnaireList&&quesitionnaireList.size()>0){
		benefitComponentSessionObject.setQuestionareList(quesitionnaireList);
		benefitComponentSessionObject.setAdminLevelOptionSysId(((BenefitComponentQuesitionnaireBO)quesitionnaireList.get(0)).getAdminLevelOptionSysId());
		}
		//this.setq(quesitionnaireList);
		preparePanel(quesitionnaireList);
		
        }
	
    	
    }
    
    public String getLoadQuestionForView() {
		 // Get the request object from the getServiceRequest()
		RetrieveBenefitComponentQuestionnairRequest request = getRetrieveBenefitComponentQuestionnairRequest();
		request.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW);
		// Call the executeService() to get the response
		LocateComponentsBenefitAdministrationResponse response = (LocateComponentsBenefitAdministrationResponse) this.executeService(request);
		if (null != response
				&& null != response.getQuestionnareList()) {
			this.viewQuestionnaireList = response
					.getQuestionnareList();
			for (int i = 0; i < viewQuestionnaireList.size(); i++) {
				BenefitComponentQuesitionnaireBO questionnaireBO = (BenefitComponentQuesitionnaireBO) viewQuestionnaireList.get(i);
				String questionName = questionnaireBO.getQuestionName();
				questionName = getLevelPrefix(questionnaireBO.getLevel())+ questionName;
				questionnaireBO.setQuestionName(questionName);
			}
		}
		setValuesToHidden();
		return "";
    }
    private void setValuesToHidden() {

		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

		this
				.setBenefitComponentId(Integer
						.toString(benefitComponentSessionObject
								.getBenefitComponentId()));
		this.setPrimaryEntityID(Integer.toString(benefitComponentSessionObject
				.getBenefitComponentId()));

	}
    public void setLoadQuestionForView(String temp) {
    	
    }
    /**
     * To save the over ridden administration values to the db
     * 
     * @return String
     */
    public String saveBenefitAdministration() {
    	
        // Create the request object from the getServiceRequest()
    	String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
        UpdateComponentsBenefitAdministrationRequest administrationRequest = (UpdateComponentsBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.UPDATE_BENEFIT_COMPONENT_BENEFIT_ADMINISTRATION);
        BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
			getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        quesitionnaireList = (List)benefitComponentSessionObject.getQuestionareList();
        orgQuestionnaireList = (List)benefitComponentSessionObject.getOrgQuestionnaireList(); 
        
        this.filterQuestionsForUpdate();
        
        if(null!=tildaArray && tildaArray.length>0){
        	processQuestionaireList(quesitionnaireList,tildaArray);
        }
        benefitComponentSessionObject.setQuestionareList(quesitionnaireList);
        tildaArray =null;
        this.tildaNoteStatus =null;	
         int bnftComponentId = benefitComponentSessionObject.getBenefitComponentId(); 
         administrationRequest.setQuestionnareList(quesitionnaireList);
         administrationRequest.setOrgQuestionnareList(orgQuestionnaireList);
         administrationRequest.setEntityId(bnftComponentId);
         administrationRequest.setAdminlevelOptionSysId(benefitComponentSessionObject.getAdminLevelOptionSysId());
         administrationRequest.setMainObjectIdentifier(benefitComponentSessionObject.getBenefitComponentName());
         administrationRequest.setVersionNumber(benefitComponentSessionObject.getBenefitComponentVersionNumber());
         administrationRequest.setDomainList(benefitComponentSessionObject.getBusinessDomainList());
         administrationRequest.setQuestionnaireListToAdd(this.newQuestions);
         administrationRequest.setQuestionnaireListToUpdate(this.modifiedQuestions);
         administrationRequest.setQuestionnaireListToRemove(this.removedQuestions);
         
         UpdateComponentsBenefitAdministrationResponse response = 
         	(UpdateComponentsBenefitAdministrationResponse)this.executeService(administrationRequest);
         if(null!=response){
         	getRequest().setAttribute("RETAIN_Value","");
         	preparePanel(quesitionnaireList);
         	

        	orgQuestionnaireList = new ArrayList();
        	Iterator it = quesitionnaireList.iterator();
        	while(it.hasNext()){
        		BenefitComponentQuesitionnaireBO BcQueBO = (BenefitComponentQuesitionnaireBO)it.next();
        		BenefitComponentQuesitionnaireBO orgBcQueBO = new BenefitComponentQuesitionnaireBO();
        		
        		orgBcQueBO.setQuestionnaireId(       BcQueBO.getQuestionnaireId()  );
        		orgBcQueBO.setQuestionId(            BcQueBO.getQuestionId()  );
        		orgBcQueBO.setParentQuestionnaireId( BcQueBO.getParentQuestionnaireId()  );
        		orgBcQueBO.setSelectedAnswerid(      BcQueBO.getSelectedAnswerid()  );
        		orgBcQueBO.setSelectedAnswerDesc(    BcQueBO.getSelectedAnswerDesc()  );
        		orgBcQueBO.setAdminLevelOptionSysId( BcQueBO.getAdminLevelOptionSysId()  );
        		orgBcQueBO.setBenefitComponentId(    BcQueBO.getBenefitComponentId()  );
        		orgBcQueBO.setBenefitComponentId(    BcQueBO.getBenefitComponentId()  );
        		orgBcQueBO.setBnftDefId(             BcQueBO.getBnftDefId()  );
        		orgBcQueBO.setChildCount(            BcQueBO.getChildCount()  );
        		orgBcQueBO.setLevel(                 BcQueBO.getLevel()  );
        		orgBcQueBO.setNotesExists(           BcQueBO.getNotesExists() );
        		orgBcQueBO.setPossibleAnswerList(    BcQueBO.getPossibleAnswerList() );
        		orgBcQueBO.setQuestionOrder(         BcQueBO.getQuestionOrder() );
        		orgBcQueBO.setReferenceId(           BcQueBO.getReferenceId() );
        		orgBcQueBO.setTierSysId(             BcQueBO.getTierSysId() );
        		
        		orgQuestionnaireList.add(orgBcQueBO); 
        	}	 
        	// remove not answered questions from list, before setting to session.
        	benefitComponentSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	       
        	return "benefitComponentQuestionnaireEdit";
         } 
         return "";
    }
    /**
     * Method to compare the original questionnaire & modified one.
     * 6 questionnaire lists will be prepared
     * 
     */
    
    private void filterQuestionsForUpdate(){ 
    	
    	Logger.logInfo("inside filterQuestionsForUpdate()");
    	
    	BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
			getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
    	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
    	quesitionnaireList = (List)benefitComponentSessionObject.getQuestionareList();	      
	    orgQuestionnaireList = (List)benefitComponentSessionObject.getOrgQuestionnaireList(); 
	    
	    newQuestions = new ArrayList(); 
	    modifiedQuestions = new ArrayList();
	    removedQuestions = new ArrayList();
	    
	    Iterator it = quesitionnaireList.iterator();
	    while(it.hasNext()){
	    	BenefitComponentQuesitionnaireBO bcQuestionnaireBO = (BenefitComponentQuesitionnaireBO)it.next();
		    	if(!"Not Answered".equals(bcQuestionnaireBO.getSelectedAnswerDesc()) ){
		    	int a=0;
		    	
		    	Iterator it1 = orgQuestionnaireList.iterator();
		    	while(it1.hasNext()){
		    		BenefitComponentQuesitionnaireBO bcQuestionnaireBO1 = 
			    		(BenefitComponentQuesitionnaireBO)it1.next(); 
		    		    		if(bcQuestionnaireBO.getQuestionnaireId() == bcQuestionnaireBO1.getQuestionnaireId()){
						    		a++; 
						    		it1.remove();
						    		if(bcQuestionnaireBO.getSelectedAnswerid() != bcQuestionnaireBO1.getSelectedAnswerid()){
							    		modifiedQuestions.add(bcQuestionnaireBO);
							    	}
					    		} 
		    	}
		    	
		    	if(a == 0){ 
		    		newQuestions.add(bcQuestionnaireBO);
		    	}
		    	}//no need to compare not answered questions 
	    }
	    
	      removedQuestions = orgQuestionnaireList;
	      
	}
    
    /**
     * Method to remove not-answered questions from the given list
     * @param req
     */
    public List removeNotAnsweredQuestions(List questionnaireList){
    	if(null != questionnaireList){
    		Iterator it = questionnaireList.iterator();
    		while(it.hasNext()){
    			if( "Not Answered".equals( ((BenefitComponentQuesitionnaireBO)it.next()).getSelectedAnswerDesc().trim() )  ){
		    		it.remove();
		    	} 
    		}
    		
    	}
    	return questionnaireList;
    }
    /**
     * To get the list of over ridden values by taking it from the hidden
     * hashMaps return List
     */
    private List getBenefitAdministrationOverriddenList() {

        // Create a list
        List benefitAdministrationList = new ArrayList(datafieldMapForQuestionId==null?0:datafieldMapForQuestionId.keySet().size());
        
        int count=0;

        // Get the question id from the hidden key set of the HashMap
        Set idSet = datafieldMapForQuestionId.keySet();

        // Create the iterator for the questionId
        Iterator questionIdIter = idSet.iterator();

        // Get the answers key set from the HashMap
        Set answerSet = datafieldMapForAnswerId.keySet();

        // Create the iterator for the answerSet
        Iterator answerIterator = answerSet.iterator();

        // Get the benefitComponent id from the session
        BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        //String benefitComponentId = "100";
        
        // Get the int value of the benefit component sys id
        int bnftComponentId = benefitComponentSessionObject.getBenefitComponentId();
        
        

        // Iterate through the HaspMap and get the individual question and
        // answer values and set them to the list
        while (questionIdIter.hasNext() && answerIterator.hasNext()) {

            // Get the long value
            Long iterationId = (Long) questionIdIter.next();

            // Create an instance of the VO
            BenefitAdministrationOverrideVO administrationOverrideVO = new BenefitAdministrationOverrideVO();

            // Get the value of id form the map
            String questionId = (String) datafieldMapForQuestionId
                    .get(iterationId);

            // Set the value of the question id
            if(null != questionId && !questionId.equals("")){
	            administrationOverrideVO.setQuestionId((new Integer(questionId))
	                    .intValue());
            }

            // Get the value of id form the map
            //String answerId = (String) 
            datafieldMapForAnswerId.get(iterationId);

            // Set the bnftComponentId
            administrationOverrideVO.setEntityId(bnftComponentId);

            // Set the value of the question id
            administrationOverrideVO.setAnswerId(new Integer(
                    ((String) datafieldMap.get(iterationId))).intValue());
            
            //Get the value of hiddenFlag form the map
			String hiddenFlag;
			Boolean hideStatus = (Boolean)datafieldmapForQuestionHideFlag.get(iterationId);
			if("true".equals(hideStatus.toString())){
				hiddenFlag = "T";
				//count of hidden questions
				count++;
			}else{
				hiddenFlag = "F";
			}
			
			//change for performace issue on 13th Dec 2007
        	Object oldAnswerFlag = hiddenAnswerMap.get(iterationId);
        	if((null != oldAnswerFlag && null != ((String) datafieldMap.get(iterationId)))
        	    	&& !((((String) datafieldMap.get(iterationId))).equals(oldAnswerFlag.toString()))){
        	    administrationOverrideVO.setModified(true);
        	    administrationOverrideVO.setAnswerModified(true);
            }
        	 
        	Object oldQuestionFlag = hiddenQuestionFlagMap.get(iterationId);
        	if("true".equals(hideStatus.toString())){
            	if(null != oldQuestionFlag  
            			&& oldQuestionFlag.toString().equals("F")){
            	    	administrationOverrideVO.setModified(true);
            	    	administrationOverrideVO.setIshideModified(true);
                		
            	}
        	}else{
        	    if(null != oldQuestionFlag  
            			&& oldQuestionFlag.toString().equals("T")){
            	    	administrationOverrideVO.setModified(true);
            	    	administrationOverrideVO.setHideView(true);
            	}
        	}
        	//end
			administrationOverrideVO.setQuestionHideFlag(hiddenFlag);
            // Add the VO to the list
            benefitAdministrationList.add(administrationOverrideVO);
				
        }
        
        //Getting the question count from session
        HttpSession httpSession = getSession();
   	    String size = (String)httpSession.getAttribute("QUESTIONS_COUNT");
   	    
	   	 if((Integer.parseInt(size))==count)
	   	 {
	   	 	this.setAllQuestionsHidden(true);
	   	 }
   	    
   	    //for Setting value for adminOptionHideFlag in the list
        for(int i=0;i<benefitAdministrationList.size();i++)
    	{
        	 BenefitAdministrationOverrideVO administrationOverrideVO=(BenefitAdministrationOverrideVO)benefitAdministrationList.get(i);
        	   //Setting adminOptionHideFlag as 'T' if questionCount is equal to hiddenQuestion count
    		   if((Integer.parseInt(size))==count)
    		   {
    		   	administrationOverrideVO.setAdminOptionHideFlag("T");
    		   }
    		   else
    		   	administrationOverrideVO.setAdminOptionHideFlag("F");
    	}

        // Return the list
        return benefitAdministrationList;

    }

    
    
    /**
     * To get the list of benefit administration values from the db
     * 
     * @return List benefitAdministrationList
     */
    private List getBenefitAdministrationValues() {
        // Get the request object from the getServiceRequest()
        LocateComponentsBenefitAdministrationRequest benefitAdministrationRequest = 
        	getLocateBenefitAdministrationRequest();

        // Call the executeService() to get the response
        LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
        	(LocateComponentsBenefitAdministrationResponse) this
                .executeService(benefitAdministrationRequest);

        // Get the list of benefit administration values from the response
        List benefitAdministrationList=new ArrayList(benefitAdministrationResponse.getBenefitAdministrationList()==null?0:benefitAdministrationResponse.getBenefitAdministrationList().size());
        
        if(null!=benefitAdministrationResponse.getBenefitAdministrationList())
        {
        	benefitAdministrationList= benefitAdministrationResponse.getBenefitAdministrationList();
        }	
        
        List benefitAdministrationUnHiddenList = new ArrayList(benefitAdministrationResponse.getBenefitAdministrationList()==null?0:benefitAdministrationResponse.getBenefitAdministrationList().size()); 
        //Setting a copy of benefitAdministrationList to benefitAdministrationUnHiddenList
        benefitAdministrationUnHiddenList.addAll(benefitAdministrationList);
        
        if (benefitAdministrationUnHiddenList != null) {
        	
            for (int i = 0; i < benefitAdministrationUnHiddenList.size(); i++) {
                EntityBenefitAdministration benefitAdministration = (EntityBenefitAdministration) benefitAdministrationUnHiddenList
                        .get(i);
                if("T".equals(benefitAdministration.getQstnHideFlag()))
                {
                	//removing hidden questions from the benefitAdministrationUnHiddenList
                	benefitAdministrationUnHiddenList.remove(i);
                	i--;
                }
            }
        }
        
        //to set the breadcrumb for view mode
        String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
        if (mode.equals("View")){
        	String name = getBenefitComponentSessionObject().getBenefitComponentName();
    		this.setBreadCrumbText("Product Configuration >> Benefit Component " + " (" + name + ") >> View");
        }
        
        //for edit print
      /*  HttpSession httpSession = getSession();
   	    String checked = (String) httpSession.getAttribute("SHOWHIDDEN_CHECKED");
   	    */
        //	 if("true".equals(checked))
        
       	 if(this.showHiddenSelected)	
       	 {
       	 	//if 'ShowHidden' checked,then returning the list with both hidden and unhidden questions
           	return benefitAdministrationList;
       	 }
         else 
         {
         	//if 'ShowHidden' unchecked,then returning the list with only unhidden questions
           	return benefitAdministrationUnHiddenList;
         }
        	
        
    }
    
    /**
     * To get the request object for retrieving the benefit administration list
     * after setting all the values in it
     * 
     * @return RetrieveProductStructureBenefitAdministrationRequest
     */
    private LocateComponentsBenefitAdministrationRequest getLocateBenefitAdministrationRequest() {

        // Create the session object
        //HttpSession httpSession = getSession();

        //String adminSysId = (String) httpSession.getAttribute("OPTION_ID");
        String adminSysId = (String) getSession().getAttribute("SESSION_ADMIN_ID");
        //String adminSysId = "11";

        // Get the int value of the admin sys id
        int adminId = 0;
        if(null != adminSysId && !adminSysId.equals("")){
        	adminId = new Integer(adminSysId).intValue();
        }
        
        // Get the benefit id from session
        //String benefitAminSysId = (String) httpSession.getAttribute("ADMIN_ID");
        String benefitAminSysId = (String) getSession().getAttribute("SESSION_ADMINISTRATION_ID");
        //String benefitAminSysId = "2002";
        
        // Get the int value of the benefit admin sys id
        int beneftAdminId = 0;
        if(null != benefitAminSysId && !benefitAminSysId.equals("")){
        	beneftAdminId = (new Integer(benefitAminSysId)).intValue();
        }

        // Get the benefitComponent id from the session
        BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        //String benefitComponentId = "100";
        
        // Get the int value of the benefit component sys id
        int bnftComponentId = benefitComponentSessionObject.getBenefitComponentId();   

        // Get the request object from the getServiceRequest()
        LocateComponentsBenefitAdministrationRequest benefitAdministrationRequest = (LocateComponentsBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.LOCATE_BENEFIT_COMPONENT_BENEFIT_ADMINISTRATION);

        // Set the administration id to the request
        benefitAdministrationRequest.setAdminSysId(adminId);//
        
        // Set the benefit id to the request
        benefitAdministrationRequest.setBenefitAdminSysId(beneftAdminId);//

        // Set the productStructure id to the request
        benefitAdministrationRequest.setBenefitComponentId(bnftComponentId);//

        // Return the request object
        return benefitAdministrationRequest;
    }
    /**
     * To get the request object for retrieving the Questionnare list
     * 
     * 
     * @return RetrieveBenefitComponentQuestionnairRequest
     */
    private RetrieveBenefitComponentQuestionnairRequest getRetrieveBenefitComponentQuestionnairRequest() {

    	BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        String adminSysId = (String) getSession().getAttribute("SESSION_ADMIN_ID");
         // Get the int value of the admin sys id
        int adminId = 0;
        if(null != adminSysId && !adminSysId.equals("")){
        	adminId = new Integer(adminSysId).intValue();
        }
        // Get the benefit id from session
        String benefitAminSysId = (String) getSession().getAttribute("SESSION_ADMINISTRATION_ID");
  
        // Get the int value of the benefit admin sys id
        int beneftAdminId = 0;
        if(null != benefitAminSysId && !benefitAminSysId.equals("")){
        	beneftAdminId = (new Integer(benefitAminSysId)).intValue();
        }
    
        int bnftComponentId = benefitComponentSessionObject.getBenefitComponentId();   
        int adminLvlOptSystemId = Integer.parseInt(this.getSession().getAttribute("SESSION_ADMIN_OPTION_ASSN_ID").toString());
        int beneftCompParentId = benefitComponentSessionObject.getBenefitComponentParentId();
        
        // Get the request object from the getServiceRequest()
        RetrieveBenefitComponentQuestionnairRequest retrieveBenefitComponentQuestionnairRequest = (RetrieveBenefitComponentQuestionnairRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_COMPONENT_QUESTIONNARE_REQUEST);

        // Set the administration id to the request
        retrieveBenefitComponentQuestionnairRequest.setAdminSysId(adminId);//
        
        // Set the benefit id to the request
        retrieveBenefitComponentQuestionnairRequest.setBenefitAdminSysId(beneftAdminId);//

        // Set the productStructure id to the request
        retrieveBenefitComponentQuestionnairRequest.setBenefitComponentId(bnftComponentId);//

        // Set the AdminLvlOptSystemId to the request
        retrieveBenefitComponentQuestionnairRequest.setAdminLvlOptSystemId(adminLvlOptSystemId);
        
        // Set the beneftCompParentId to the request
        retrieveBenefitComponentQuestionnairRequest.setBeneftCompParentId(beneftCompParentId);
        
        // Return the request object
        return retrieveBenefitComponentQuestionnairRequest;
    }
    /**
     * To group the answer descriptions from the list to possibleAnswerBOs
     * 
     * @param answersList
     */
    private List getPossibleAnswersListForAQuestion(List answersList) {

        // Create a list
        List answerDescriptionList = new ArrayList(answersList==null?0:answersList.size());

        // Check whether the answerList is null or empty
        if (answersList != null && !answersList.isEmpty()) {     	
            // Iterate thorugh the answersList
            for (int i = 0; i < answersList.size(); i++) {

                // Get the individual possible answerBO
                PossibleAnswerBO answerBO = (PossibleAnswerBO) answersList
                        .get(i);

                // Get the answer description from the bo
                String description = answerBO.getPossibleAnswerDesc();

                // Get the answer id from the bo
                int id = answerBO.getPossibleAnswerId();

                // Convert id to string
                String stringId = new Integer(id).toString();

                // Create a selectItem instance
                SelectItem selectItem = new SelectItem();

                // Set the description to the selectItem
                selectItem.setLabel(description);

                // Set the value to the selectItem
                selectItem.setValue(stringId);

                // Set the description to the new list
                answerDescriptionList.add(selectItem);
            }
        }

        // Return the list
        return answerDescriptionList;

    }
    
	
	/**
	 * method for getting the list of benefitAdministration values for print
	 * @return Returns the benefitAdministrationList.
	 */
	public List getBenefitAdministrationList() {		
		
		EntityBenefitAdministration benefitAdministrationBO = new EntityBenefitAdministration();
		//Get the list of benefit administration from the database
		benefitAdministrationList = getBenefitAdministrationValues();
		
		List benefitAdmin = new ArrayList(benefitAdministrationList==null?0:benefitAdministrationList.size());		
		Iterator selectedQuestionListIterator = benefitAdministrationList.iterator();
		
		while (selectedQuestionListIterator.hasNext()) {			
			benefitAdministrationBO  = (EntityBenefitAdministration) selectedQuestionListIterator.next();			
		//	String ans = new Integer(benefitAdministrationBO.getAnswerId()).toString();
			
			List items = benefitAdministrationBO.getAnswers();
			//get the answerdescription corresponding to the answerid
			String answerDescription = getSelectedAnswerDescFromPossibleAnswersList(items,benefitAdministrationBO.getAnswerId());
			//set the answerdescription to BO
			benefitAdministrationBO.setAnswerDesc(answerDescription);
			//add the BO to a list
			benefitAdmin.add(benefitAdministrationBO);
		}
		//return the list
		return benefitAdmin;
	}
	/**
	 * @param benefitAdministrationList The benefitAdministrationList to set.
	 */
	public void setBenefitAdministrationList(List benefitAdministrationList) {
		this.benefitAdministrationList = benefitAdministrationList;
	}
	
	/**
	 * 
	 * @return BenefitComponentSessionObject
	 */
	protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
    	BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)
		getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
	}

    /**
     * @return Returns the benefitAdminList.
     */
    public List getBenefitAdminList() {
        return benefitAdminList;
    }
    /**
     * @param benefitAdminList The benefitAdminList to set.
     */
    public void setBenefitAdminList(List benefitAdminList) {
        this.benefitAdminList = benefitAdminList;
    }
    /**
     * @return Returns the datafieldMap.
     */
    public Map getDatafieldMap() {
        return datafieldMap;
    }
    /**
     * @param datafieldMap The datafieldMap to set.
     */
    public void setDatafieldMap(Map datafieldMap) {
        this.datafieldMap = datafieldMap;
    }
    /**
     * @return Returns the datafieldMapForAnswerId.
     */
    public Map getDatafieldMapForAnswerId() {
        return datafieldMapForAnswerId;
    }
    /**
     * @param datafieldMapForAnswerId The datafieldMapForAnswerId to set.
     */
    public void setDatafieldMapForAnswerId(Map datafieldMapForAnswerId) {
        this.datafieldMapForAnswerId = datafieldMapForAnswerId;
    }
    /**
     * @return Returns the datafieldMapForQuestionId.
     */
    public Map getDatafieldMapForQuestionId() {
        return datafieldMapForQuestionId;
    }
    /**
     * @param datafieldMapForQuestionId The datafieldMapForQuestionId to set.
     */
    public void setDatafieldMapForQuestionId(Map datafieldMapForQuestionId) {
        this.datafieldMapForQuestionId = datafieldMapForQuestionId;
    }
    /**
     * @return Returns the headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {
    	
    	String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
              
        headerPanel = new HtmlPanelGrid();
        headerPanel.setWidth("100%");
        headerPanel.setColumns(4);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");
        headerPanel.setStyleClass("dataTableHeader");

        HtmlOutputText questionText = new HtmlOutputText();
        HtmlOutputText answerText = new HtmlOutputText();
        HtmlOutputText referenceTest = new HtmlOutputText();
        HtmlOutputText notes = new HtmlOutputText();
        
        questionText.setValue("Question");
        questionText.setId("Question");

        answerText.setValue("Answer");
        answerText.setId("Answer");
        
        referenceTest.setValue("Reference");
        referenceTest.setId("Reference");
        
        notes.setValue("Notes");
        notes.setId("Notes");

       	headerPanel.getChildren().add(questionText);
        headerPanel.getChildren().add(answerText);
        headerPanel.getChildren().add(referenceTest);
        headerPanel.getChildren().add(notes);
       
      
        return headerPanel;
    }
    	/**
	 * @return Returns the showHiddenSelected.
	 */
	public boolean isShowHiddenSelected() {
		return showHiddenSelected;
	}
	/**
	 * @param showHiddenSelected The showHiddenSelected to set.
	 */
	public void setShowHiddenSelected(boolean showHiddenSelected) {
		this.showHiddenSelected = showHiddenSelected;
	}
/**
     * @param headerPanel The headerPanel to set.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }
    
    /*
     * method to invoke when the show Hidden Checkbox is selected.
     */	
    public String loadWithHiddenValues(){
    	this.setShowHiddenSelected(true);
    	//Setting the status of 'ShowHidden' checkbox to session
    	 HttpSession httpSession = getSession();
    	 httpSession.setAttribute("SHOWHIDDEN_CHECKED","true");
    	 getRequest().setAttribute("RETAIN_Value", "");
    	 //loading the questions page
    	return "loadBenefitAdministration";
    	
    }
    /*
     * method to invoke when the show Hidden Checkbox is unselected.
     */	
    public String loadWithOutHiddenValues(){
    	
    	this.setShowHiddenSelected(false);
    	//Setting the status of 'ShowHidden' checkbox to session
    	 HttpSession httpSession = getSession();
    	 httpSession.setAttribute("SHOWHIDDEN_CHECKED","false");
    	 getRequest().setAttribute("RETAIN_Value", "");
    	 //loading the questions page
    	return "loadBenefitAdministration";
    }
    
    /**
     * @return Returns the panel.
     */
    public void preparePanel(List questionnareList){
    	StringBuffer line =null;
    	String finalline = null;
    	questionnarePanel = new HtmlPanelGrid();
        questionnarePanel.setWidth("100%");
        questionnarePanel.setColumns(4);	
        questionnarePanel.setBorder(0);
        questionnarePanel.setStyleClass("outputText");
        questionnarePanel.setCellpadding("3");
        questionnarePanel.setCellspacing("1");
        questionnarePanel.setBgcolor("#cccccc");	
        StringBuffer rowClass = new StringBuffer();
        if (questionnareList != null) {
            HtmlSelectOneMenu answerSelectOneMenu = null;
            HtmlOutputText questionOutputText = null;
            HtmlOutputText referenceOutputText = null;
            HtmlInputHidden childCountHidden = null;
            HtmlPanelGroup referenceGroup = null;
            HtmlGraphicImage notesAttachmentImage = null;
            HtmlOutputText notesHidden = null;
            for (int i = 0; i < questionnareList.size(); i++) {
            	BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO=(BenefitComponentQuesitionnaireBO)questionnareList.get(i);
            	questionOutputText = new HtmlOutputText();
            	referenceOutputText = new HtmlOutputText();
            	childCountHidden =new HtmlInputHidden();
            	referenceGroup = new HtmlPanelGroup();
            	notesAttachmentImage = new HtmlGraphicImage();
            	notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i); 
            	
            	if(i>0){
            		rowClass.append(",");	
            	}
            	int level= benefitComponentQuesitionnaireBO.getLevel();
            	if(level>1){
            	finalline = getLevelPrefix(level);
            	questionOutputText.setValue(finalline+benefitComponentQuesitionnaireBO.getQuestionName());
            	rowClass.append("dataTableOddRow");
            	}else{
            		questionOutputText.setValue(benefitComponentQuesitionnaireBO.getQuestionName());
            		rowClass.append("dataTableEvenRow");
            	}
            	 childCountHidden.setId("childCount"+i);
            	 childCountHidden.setValue(new Integer(benefitComponentQuesitionnaireBO.getChildCount()));
            	 List answerList =  benefitComponentQuesitionnaireBO.getPossibleAnswerList();
            	 List possibleAnswersList = (List)getPossibleAnswersListForAQuestion(answerList);
            	 
            	 answerSelectOneMenu = new HtmlSelectOneMenu();
            	 answerSelectOneMenu.setId("selectitem" + i);
                 UISelectItems uis = new UISelectItems();
                 uis.setValue(possibleAnswersList);

                 answerSelectOneMenu.setValue(new Integer(
                 		benefitComponentQuesitionnaireBO.getSelectedAnswerid()).toString());
                 answerSelectOneMenu.setStyleClass("formInputList");
                 answerSelectOneMenu
                         .setStyleClass("formInputFieldBenefitStructure");
                 answerSelectOneMenu.getChildren().add(uis);
                 answerSelectOneMenu.setOnfocus("return storeOldValue(this)");
                 answerSelectOneMenu.setOnchange("return loadNewChild(this)");
                 
                 referenceOutputText.setId("refere"+i);
                 referenceOutputText.setValue(benefitComponentQuesitionnaireBO.getReferenceDesc());
                 referenceGroup.getChildren().add(referenceOutputText);
                 referenceGroup.getChildren().add(childCountHidden);
                 
                 StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
					.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
                 int benefitKey = sessionObject.getStandardBenefitKey();
                
                 BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
						.getAttribute(
								WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

				String primaryType = "ATTACHCOMP";
				HttpSession httpSession = getSession();
				this.setBenefitDefnId(benefitComponentQuesitionnaireBO
						.getBnftDefId());
				if(i==0){
				 getSession().setAttribute("bnftDefId",this.getBenefitDefnId());
				 getSession().setAttribute("adminlevelOptionID",new Integer(benefitComponentQuesitionnaireBO.getAdminLevelOptionSysId()));
				}
				benefitComponentQuesitionnaireBO.setBnftDefId( getSession().getAttribute("bnftDefId").toString());
				if(null!=getSession().getAttribute("adminlevelOptionID") && !("").equals(getSession().getAttribute("adminlevelOptionID").toString())){
				benefitComponentQuesitionnaireBO.setAdminLevelOptionSysId(Integer.parseInt(getSession().getAttribute("adminlevelOptionID").toString()));
				}
				httpSession.setAttribute("primaryType", primaryType);

				if (benefitComponentQuesitionnaireBO.getNotesExists().equals(
						"Y")) {
					notesAttachmentImage.setUrl("../../images/notes_exist.gif");
				} else {
					notesAttachmentImage.setUrl("../../images/page.gif");
				}
				notesAttachmentImage.setStyle("cursor:hand;");
				notesAttachmentImage.setId("notesAttachmentImage" + i);
				notesAttachmentImage
						.setOnclick("loadNotesPopup('../popups/bcQuestionNotesPopup.jsp'+getUrl(),'"
								+ benefitComponentQuesitionnaireBO
										.getQuestionId()
								+ "','"
								+ benefitComponentSessionObject
										.getBenefitComponentId()
								+ "','"
								+ primaryType
								+ "','"
								+ benefitComponentSessionObject
										.getBenefitComponentId()
								+ "','"
								+ benefitComponentQuesitionnaireBO
										.getBnftDefId()
								+ "','"
								+ benefitComponentQuesitionnaireBO
										.getAdminLevelOptionSysId()
								+ "','"
								+"notesAttachmentImage" +i+"','"+i
								+ "');return false;");                             
                 
            	questionnarePanel.getChildren().add(questionOutputText);
            	questionnarePanel.getChildren().add(answerSelectOneMenu); 
            	questionnarePanel.getChildren().add(referenceGroup);
            	
            	if ("Y".equals(benefitComponentQuesitionnaireBO
						.getValidDomainToAttach())) {
					questionnarePanel.getChildren().add(notesAttachmentImage);
				} else {
					questionnarePanel.getChildren().add(notesHidden);
				}
            	
            	
            	//questionnarePanel.getChildren().add(notesAttachmentImage);
            	
            }
        }
      questionnarePanel.setRowClasses(rowClass.toString());
    }
    /*
     * 
     * this method for creating level prefixx
     * 
     */
    public String getLevelPrefix(int level) {

        StringBuffer buffer = new StringBuffer("");

        for(int i=1; i<level; i++) {

                    buffer.append(" - ");

        }

        return buffer.toString();
    }

   
	/**
	 * @param viewPanel The viewPanel to set.
	 */
	public void setViewPanel(HtmlPanelGrid viewPanel) {
		this.viewPanel = viewPanel;
	}
	
    /**
     * Method to return the individual Answer description for the individual answerid
	 * @param items
	 * @return String individualAnswerDesc
	 */
	private String getSelectedAnswerDescFromPossibleAnswersList(List items, int answerId) {
		// Iterate through the answers list 
		for(int i = 0 ; i < items.size() ; i++){
			
			// Get individual AnswerBO from the list
			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) items.get(i);
			// Get the answer id from the list
			int individualAnswerId = possibleAnswerBO.getPossibleAnswerId();
			
			// Get the answer description from the list
			String individualAnswerDesc = possibleAnswerBO.getPossibleAnswerDesc(); 
			
			// Check if the answer id matches the required answer id
			if(answerId == individualAnswerId){
				
				// Return the answer description
				return individualAnswerDesc;
			}
		}
		return null;
	}
	
    /**
     * For displying admin Method tab in benefit component.(in view)
     * 
     * @return String
     */
    public String loadForBenefitComponent() {
        Logger
                .logInfo("Entering the method for loading admin Methods in Benefit Component");

        return "success";
    }

	/**
	 * @return Returns the componentType.
	 */
	public String getComponentType() {
		if(null!=this.getBenefitComponentSessionObject().getBcComponentType())
			componentType = this.getBenefitComponentSessionObject().getBcComponentType();
		return componentType;
	}
	/**
	 * @param componentType The componentType to set.
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	/**
	 * @return Returns the datafieldmapForQuestionHideFlag.
	 */
	public Map getDatafieldmapForQuestionHideFlag() {
		return datafieldmapForQuestionHideFlag;
	}
	/**
	 * @param datafieldmapForQuestionHideFlag The datafieldmapForQuestionHideFlag to set.
	 */
	public void setDatafieldmapForQuestionHideFlag(
			Map datafieldmapForQuestionHideFlag) {
		this.datafieldmapForQuestionHideFlag = datafieldmapForQuestionHideFlag;
	}
	
	
	/**
	 * @return Returns the allQuestionsHidden.
	 */
	public boolean isAllQuestionsHidden() {
		return allQuestionsHidden;
	}
	/**
	 * @param allQuestionsHidden The allQuestionsHidden to set.
	 */
	public void setAllQuestionsHidden(boolean allQuestionsHidden) {
		this.allQuestionsHidden = allQuestionsHidden;
	}
	/*
	 * This method for retrieving  Questionnare while chenging an answer of a Question node.
	 * 
	 * This save new questionnare list to session.
	 *  this call business service method to retrieve Questionnare list.
	 * 
	 */
	public String  selectNewQuestionnreList(){
	    
	    String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}	    
		RetrieveBenefitComponentQuestionnairRequest retrieveBenefitComponentQuestionnairRequest = 
			getRetrieveBenefitComponentQuestionnairRequest();
		int rowNum = this.rowNum;
		int answerId =this.answerId;
		String answerDesc = this.answerDesc;
		BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		quesitionnaireList = (List)benefitComponentSessionObject.getQuestionareList();
		possibleAnswerMap = benefitComponentSessionObject.getAllPossibleAnswerMap();
		
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(quesitionnaireList,tildaArray);
			}
			tildaArray =null;
			this.tildaNoteStatus =null;		
		
		//quesitionnaireList = (List)getSession().getAttribute("QUESTONNARE_LIST");
		BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO=(BenefitComponentQuesitionnaireBO)quesitionnaireList.get(rowNum);
		benefitComponentSessionObject.setAdminLevelOptionSysId(benefitComponentQuesitionnaireBO.getAdminLevelOptionSysId());
		retrieveBenefitComponentQuestionnairRequest.setQuestionareListIndex(rowNum);
		retrieveBenefitComponentQuestionnairRequest.setBenefitComponentQuesitionnaireBO(benefitComponentQuesitionnaireBO);
		retrieveBenefitComponentQuestionnairRequest.setSelectedAnswerId(answerId);
		retrieveBenefitComponentQuestionnairRequest.setSelectedAnswerDesc(answerDesc);
		retrieveBenefitComponentQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_SELECTED_CHILD);
		retrieveBenefitComponentQuestionnairRequest.setQuestionnareList(quesitionnaireList);
		retrieveBenefitComponentQuestionnairRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		
		LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
        	(LocateComponentsBenefitAdministrationResponse) this
                .executeService(retrieveBenefitComponentQuestionnairRequest);
		if(null!=benefitAdministrationResponse){
		 this.quesitionnaireList = benefitAdministrationResponse.getQuestionnareList();
		 if(null!=quesitionnaireList&& quesitionnaireList.size()>0){
		 benefitComponentSessionObject.setQuestionareList(quesitionnaireList);
		 benefitComponentSessionObject.setAdminLevelOptionSysId(((BenefitComponentQuesitionnaireBO)quesitionnaireList.get(0)).getAdminLevelOptionSysId());
		 }
		 preparePanel(quesitionnaireList);
		}
		return "benefitComponentQuestionnaireEdit"; 
	}
	
	 /**
     * To get the request object for retrieving the possible answer list
     * after setting all the values in it.
     * 
     * @return RetrieveAllPossibleAnswerRequest
     */
    private RetrieveAllPossibleAnswerRequest 
		getRetrieveAllPossibleAnswerRequest() {
    	 // Create the session object
    	BenefitComponentSessionObject benefitComponentSessionObject = 
        	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
       
    	 String option_Id = (String)getSession().getAttribute("SESSION_ADMIN_ID");
         int adminOptionId = 0;
         if(null != option_Id && !option_Id.equals("")){
         	adminOptionId = (new Integer(option_Id)).intValue();
         }
        
        RetrieveAllPossibleAnswerRequest
			retrieveAllPossibleAnswerRequest = 
				(RetrieveAllPossibleAnswerRequest) this
                	.getServiceRequest(ServiceManager
                			.RETRIEVE_ALL_POSSIBLE_ANSWER_LIST);

        retrieveAllPossibleAnswerRequest.setAdminOptSysId(adminOptionId);
        // Return the request object
        return retrieveAllPossibleAnswerRequest;
    }
    
	/** 
	 * @param questionaireList
	 * @param tildaArray
	 */
	private void processQuestionaireList(List questionaireList,String[] tildaArray){
		
		int size = questionaireList.size();
		
		for(int i=0;i<size;i++){
			
			for (int j=0;j<tildaArray.length;j++){
				if((new Integer(i).toString()+"Y").equals(tildaArray[j])){
					((BenefitComponentQuesitionnaireBO) questionaireList
					.get(i)).setNotesExists("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((BenefitComponentQuesitionnaireBO) questionaireList
							.get(i)).setNotesExists("N");
							break;
				}
			}
		}		
	}
	
	/**
	 * @return Returns the questionnarePanel.
	 */
	public HtmlPanelGrid getQuestionnarePanel() {
		return questionnarePanel;
	}
	/**
	 * @param questionnarePanel The questionnarePanel to set.
	 */
	public void setQuestionnarePanel(HtmlPanelGrid questionnarePanel) {
		this.questionnarePanel = questionnarePanel;
	}
	
	
	/**
	 * @return Returns the viewQuestionnaireList.
	 */
	public List getViewQuestionnaireList() {
		return this.viewQuestionnaireList;
	}
	/**
	 * @param viewQuestionnaireList The viewQuestionnaireList to set.
	 */
	public void setViewQuestionnaireList(List viewQuestionnaireList) {
		this.viewQuestionnaireList = viewQuestionnaireList;
	}
	/**
	 * Method is called when the user tries to save the changes made in the notes attachement pop-up
	 * @return
	 */
	public String attachNotesToQuestion() {

		BnftCompNotesToQuestionAttachmentRequest bnftCompNotesToQuestionAttachmentRequest = (BnftCompNotesToQuestionAttachmentRequest) this
				.getServiceRequest(ServiceManager.BNFT_COMP_NOTES_TO_QUESTION_ATTACHMENT_REQUEST);

		bnftCompNotesToQuestionAttachmentRequest
				.setNotesToQuestionAttachmentVO(setValuesToNotesToQuestionAttachmentVO());

		BnftCompNotesToQuestionAttachmentResponse notesToQuestionAttachmentResponse = (BnftCompNotesToQuestionAttachmentResponse) this
				.executeService(bnftCompNotesToQuestionAttachmentRequest);

		List messageList = notesToQuestionAttachmentResponse.getMessages();

		refreshQuestionNote(messageList);

		return null;
	}

	/**
	 * Method used to assign values to the VO
	 * 
	 * @return NotesToQuestionAttachmentVO
	 */
	private NotesToQuestionAttachmentVO setValuesToNotesToQuestionAttachmentVO() {

		NotesToQuestionAttachmentVO notesToQuestionAttachmentVO = new NotesToQuestionAttachmentVO();
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
				.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

		notesToQuestionAttachmentVO.setQuestionId(Integer.parseInt(this
				.getQuestionId()));
		notesToQuestionAttachmentVO.setNoteId(this.getNoteId());
		notesToQuestionAttachmentVO.setPrimaryId(benefitComponentSessionObject
				.getBenefitComponentId());
		notesToQuestionAttachmentVO.setPrimaryEntityType("ATTACHCOMP");
		notesToQuestionAttachmentVO
				.setSecondaryId(benefitComponentSessionObject
						.getAdminLevelOptionSysId());
		notesToQuestionAttachmentVO
				.setBenefitCompId(benefitComponentSessionObject
						.getBenefitComponentId());
		
		HttpSession httpSession = getSession();
		if(null==this.benefitDefnId)
			this.benefitDefnId=getSession().getAttribute("bnftDefId").toString();
		
		notesToQuestionAttachmentVO.setBnftDefId(Integer.parseInt(this
				.getBenefitDefnId()));
		notesToQuestionAttachmentVO.setNoteOverrideStatus("F");
		notesToQuestionAttachmentVO.setNoteVersionNumber(Integer.parseInt(this
				.getNoteVersion()));
		notesToQuestionAttachmentVO.setSecondaryEntityType("ATTACHQUESTION");

		notesToQuestionAttachmentVO.setBcName(benefitComponentSessionObject
				.getBenefitComponentName());
		notesToQuestionAttachmentVO
				.setBusinessDomainList(benefitComponentSessionObject
						.getBusinessDomainList());
		notesToQuestionAttachmentVO.setBcVersion(benefitComponentSessionObject
				.getBenefitComponentVersionNumber());

		if (this.noteStatus.equals("update"))
			notesToQuestionAttachmentVO.setUpdateRequest(true);
		if (this.noteStatus.equals("delete"))
			notesToQuestionAttachmentVO.setDeleteRequest(true);
		if (this.noteStatus.equals("insert"))
			notesToQuestionAttachmentVO.setInsertRequest(true);

		return notesToQuestionAttachmentVO;
	}

	private void refreshQuestionNote(List message) {

		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();

		QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
		if (null != response) {
			setValuesToBackinBean(response);
		}
		addAllMessagesToRequest(message);

	}
	public String getRecords() {

		if (null != this.questionNotes)
			return records;
		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();

		QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
		if (null != response) {
			setValuesToBackinBean(response);
		}
		return new String();
	}

	public List getQuestionNotes() {

		i++;
		if (i == 1) {
			String searchString = this.getRequest()
					.getParameter("searchString");
			if (null != searchString) {
				this.searchString = searchString;
				QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
						.executeService(request);
				if (null != response) {
					setValuesToBackinBean(response);
				}
			}
		}

		return questionNotes;
	}
	
	/**
	 * Method used to get the values from the page request and set it to QuestionNotesPopUpRequest
	 * @QuestionNotesPopUpRequest
	 */
	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest() {

		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
				.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);

		if (null != getRequest().getParameter("questionId")
				&& !("").equals(getRequest().getParameter("questionId"))) {
			this.questionId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("questionId"));
			if(StringUtil.regExPatterValidation(this.questionId)){
				this.questionId=this.questionId;
			}else{
				this.questionId=null;
			}
			this.getSession().setAttribute("questionId", questionId);
		}
		if (null != getRequest().getParameter("primaryentityId")
				&& !("").equals(getRequest().getParameter("primaryentityId"))) {
			this.primaryEntityID = ESAPI.encoder().encodeForHTML(getRequest().getParameter("primaryentityId"));
			if(StringUtil.regExPatterValidation(this.primaryEntityID)){
				this.primaryEntityID = this.primaryEntityID ;
			}else{
				this.primaryEntityID=null;
			}
			this.getSession().setAttribute("primaryEntityID", primaryEntityID);
		}
		if (null != getRequest().getParameter("primaryEntytyType")
				&& !("").equals(getRequest().getParameter("primaryEntytyType"))) {
			this.primaryType = ESAPI.encoder().encodeForHTML(getRequest().getParameter("primaryEntytyType"));
			if(StringUtil.regExPatterValidation(this.primaryType)){
				this.primaryType=this.primaryType;
			}else{
				this.primaryType=null;
			}
			this.getSession().setAttribute("primaryType", primaryType);
		}
		if (null != getRequest().getParameter("bcId")
				&& !("").equals(getRequest().getParameter("bcId"))) {
			this.benefitComponentId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("bcId"));
			if(StringUtil.regExPatterValidation(this.benefitComponentId)){
				this.benefitComponentId = this.benefitComponentId;
			}else{
				this.benefitComponentId=null;
			}
			this.getSession().setAttribute("benefitComponentId",
					benefitComponentId);
		}
		if (null != getRequest().getParameter("benefitDefnId")
				&& !("").equals(getRequest().getParameter("benefitDefnId"))) {
			this.benefitDefnId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("benefitDefnId"));
			if(StringUtil.regExPatterValidation(this.benefitDefnId)){
				this.benefitDefnId = this.benefitDefnId;
			}else{
				this.benefitDefnId=null;
			}
			this.getSession().setAttribute("benefitDefnId", benefitDefnId);
		}
		if (null != getRequest().getParameter("adminLvlOptionId")
				&& !("").equals(getRequest().getParameter("adminLvlOptionId"))) {
			this.adminLvlOptionAssnSysId = ESAPI.encoder().encodeForHTML(getRequest().getParameter(
					"adminLvlOptionId"));
			if(StringUtil.regExPatterValidation(this.adminLvlOptionAssnSysId)){
				this.adminLvlOptionAssnSysId = this.adminLvlOptionAssnSysId;
			}else{
				this.adminLvlOptionAssnSysId=null;
			}
			this.getSession().setAttribute("adminLvlOptionAssnSysId",
					adminLvlOptionAssnSysId);
		}

		request.setPrimaryEntityID((this.getSession()
				.getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType")
				.toString());
		if (null != this.getSession().getAttribute("adminlevelOptionID"))
			request.setSecondaryId(this.getSession().getAttribute(
					"adminlevelOptionID").toString());
		if (null != this.getSession().getAttribute("benefitDefnId").toString()
				&& !("").equals(this.getSession().getAttribute("benefitDefnId")
						.toString())
				&& !("null").equals(this.getSession().getAttribute(
						"benefitDefnId").toString())) {
			request.setBenefitDenId(this.getSession().getAttribute(
					"benefitDefnId").toString());
		}
		if (null != this.getSession().getAttribute("benefitComponentId")
				.toString()
				&& !("").equals(this.getSession().getAttribute(
						"benefitComponentId").toString())
				&& !("null").equals(this.getSession().getAttribute(
						"benefitComponentId").toString())) {
			request.setBenefitComponentId(Integer
					.parseInt(getRequest().getSession().getAttribute(
							"benefitComponentId").toString()));
		}
		if (null != this.getSession().getAttribute("questionId").toString()
				&& !("").equals(this.getSession().getAttribute("questionId")
						.toString())
				&& !("null").equals(this.getSession()
						.getAttribute("questionId").toString())) {
			request.setQuestionId(Integer.parseInt(this.getSession()
					.getAttribute("questionId").toString()));
		}

		if (null != searchString && !("").equals(searchString)) {
			String newSearchString = WPDStringUtil.escapeString(searchString);
			request.setSearchString("%" + newSearchString.trim().toUpperCase()
					+ "%");
			request.setSearchAction(2);
		} else {
			request.setSearchAction(1);
		}
		
		request.setSecondaryEntityType("ATTACHQUESTION");

		return request;
	}

	/**
	 * Sets the list from the response to the quesionNotes list
	 * @param response
	 */
	private void setValuesToBackinBean(QuestionNotesPopUpResponse response) {

		this.setQuestionNotes(response.getNotesList());
		
		if(null!= questionNotes && questionNotes.size()>0){
			for(int i=0;i<questionNotes.size();i++){	
				NotesAttachmentOverrideBO overridebo= (NotesAttachmentOverrideBO)questionNotes.get(i);
				
				if(overridebo.getOverrideStatus().equals("Y")){
					this.noteAttached="Y";
					break;
				}else{
					this.noteAttached="N";	
				}
			}
			}
	}
	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public String getNoteVersion() {
		return noteVersion;
	}

	public void setNoteVersion(String noteVersion) {
		this.noteVersion = noteVersion;
	}

	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getPrevNoteIdSelected() {
		return prevNoteIdSelected;
	}

	public void setPrevNoteIdSelected(String prevNoteIdSelected) {
		this.prevNoteIdSelected = prevNoteIdSelected;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getPreviousNoteVersion() {
		return previousNoteVersion;
	}

	public void setPreviousNoteVersion(int previousNoteVersion) {
		this.previousNoteVersion = previousNoteVersion;
	}

	public String getNewNoteId() {
		return newNoteId;
	}

	public void setNewNoteId(String newNoteId) {
		this.newNoteId = newNoteId;
	}
    /**
     * @return Returns the tildaNoteStatus.
     */
    public String getTildaNoteStatus() {
        return tildaNoteStatus;
    }
    /**
     * @param tildaNoteStatus The tildaNoteStatus to set.
     */
    public void setTildaNoteStatus(String tildaNoteStatus) {
        this.tildaNoteStatus = tildaNoteStatus;
    }
	/**
	 * @return Returns the noteAttached.
	 */
	public String getNoteAttached() {
		return noteAttached;
	}
	/**
	 * @param noteAttached The noteAttached to set.
	 */
	public void setNoteAttached(String noteAttached) {
		this.noteAttached = noteAttached;
	}
	
	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
}
