
/*
 * ContractBenefitAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;


import org.apache.commons.lang.RandomStringUtils;

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitAdministrationRequest;
import com.wellpoint.wpd.common.contract.request.SaveBenefitAdministrationRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitAdministrationResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ContractNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.ContractNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.vo.NotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.framework.util.StringUtil;



/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractBenefitAdministrationBackingBean extends ContractBackingBean {
    
    private boolean renderFlagForPanel = false;
    private String load; 
    
    private HtmlPanelGrid headerPanel = null;
    
    private HtmlPanelGrid tierHeaderPanel =null;
    
    private HtmlPanelGrid headerPanelForPrint = null;
    
    private HtmlInputHidden loadQuestionForView=new HtmlInputHidden(); 
    
    private HtmlPanelGrid panel = null;

    private List benefitAdminList;
    
    private Map datafieldMap = new LinkedHashMap();
    
    private Map datafieldMapForQuestionId = new LinkedHashMap();
    
    private Map datafieldMapForAnswerId = new LinkedHashMap(); 

    //private String loadPrintPage;
    private HtmlInputHidden loadPrintPage=new HtmlInputHidden();
    private String hiddenInit;
    
    private boolean printMode;
    
    private String hiddenAdminOption;
    
    HtmlPanelGrid viewPanel = null;
    
    private String asnwerStates;
    
    private String  contractType;
    

    private List questionnaireList;
    
	private List tierList = null;
	
	// Code change by minu : 28-12-2010 : eWPD System Stabilization     
	private List orgQuestionnaireList;
	private List orgTierList = null;
	
	private List newQuestions             = null;
	private List modifiedQuestions        = null;
	private List removedQuestions         = null;
	private List newTieredQuestions       = null;
	private List modifiedTieredQuestions  = null;
	private List removedTieredQuestions   = null;
	
//	14 jan 2011 - Code optimization for - get possible answers
	java.util.HashMap possibleAnswerMap;
	
    private int rowNum;
    
    private int answerId;
    private String answerDesc="";

	private boolean questionListRetrieved;
	private int childCount;	
	
	public List viewQuestionnaireList;
	
	private String records;

	private List questionNotes;

	int i = 0;

	private String searchString;

	private String questionId;

	private String primaryEntityID;

	private String primaryType;

	private String benefitComponentId;

	private String benefitDefnId;

	private String adminLvlOptionAssnSysId;

	private String prevNoteIdSelected;

	private String previousNoteVersion;

	private String noteStatus;

	private String noteId;

	private String noteVersion;

	private String noteName;

	private String version;

	private String newNoteId;

	private String tildaNoteStatus ;
	
	HtmlPanelGrid questionnarePanel = null;
	
	private String noteAttached;
	
	private int dateSegmentId;
	
	private int adminlvloptionid;
	
	private String tieredQuestionsStates;
	
	private HtmlPanelGrid tierQuestionarePanel;
	
	private String panelTierSysId;
	
	private String tildaTierNoteStatus;
	
	Map tierQstnIdMap=new HashMap();
    
  //  private String hiddenForQuestionare;
    
    public ContractBenefitAdministrationBackingBean(){
        super();
        this.setBreadCrumbText();
    }
	
    /**
     * To go to the Contract BenefitAdministration page when navigating
     * through the tree.
     * 
     * @return String forward String
     */
    public String displayContractBenefitAdministration() {
    	
        benefitAdminList = getBenefitAdministrationValues();
        if(isViewMode())
        {
        	return "displayContractBenefitAdministrationView";   
        }else if(this.isPrintMode()){
        	return "";
        }
        else{
        	return "displayContractBenefitAdministration";
        }
    }
    /**
     * To get the list of benefit administration values from the db.
     * 
     * @return List benefitAdministrationList
     */
    private List getBenefitAdministrationValues() {
        Logger.logInfo("Entering the method for getting benefit " 
        		+ "administration values");
        RetrieveContractBenefitAdministrationRequest
			retrieveContractBenefitAdministrationRequest = getRetrieveBenefitAdministrationRequest();
        retrieveContractBenefitAdministrationRequest.setAction(RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_VIEW_PRINT);
        // Call the executeService() to get the response
        RetrieveContractBenefitAdministrationResponse 
			retrieveContractBenefitAdministrationResponse =
				(RetrieveContractBenefitAdministrationResponse) this
					.executeService(retrieveContractBenefitAdministrationRequest);

        List benefitAdministrationList = null;
        if(null!=retrieveContractBenefitAdministrationResponse){
        if (null != retrieveContractBenefitAdministrationResponse
                .getBenefitAdministrationList()
                && retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList()
                        .size() != 0) {
            // Get the list of benefit administration values from the response
            benefitAdministrationList =  (List)retrieveContractBenefitAdministrationResponse
                    .getBenefitAdministrationList().get(0);
            if(retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList()
                    .size() >1 ){
             tierList = (List)retrieveContractBenefitAdministrationResponse
             .getBenefitAdministrationList().get(1);
              prepareTierPanel(tierList,isPrintMode());
                     
            }else{
            	tierQuestionarePanel = null;
            }
        }
        }
        Logger.logInfo("Returning the method for getting benefit " 
        		+ "administration values");
        // Return the list to the calling method
        return benefitAdministrationList;
    }
    /**
     * To get the request object for retrieving the benefit administration list
     * after setting all the values in it.
     * 
     * @return RetrieveProductStructureBenefitAdministrationRequest
     */
    private RetrieveContractBenefitAdministrationRequest 
		getRetrieveBenefitAdministrationRequest() {
        // Create the session object
        int benefitComponentId = new Integer(this.getContractSession().getBenefitComponentId()).intValue();
        int benefitAminSysId = new Integer(this.getContractSession().getBenefitAdminId()).intValue();
        int adminOptionId = new Integer(this.getContractSession().getAdminOptionId()).intValue();
        int benefitId = new Integer(this.getContractSession().getBenefitId()).intValue();
        int dateSegmentId = this.getContractKeyObject().getDateSegmentId();
        
        //      Code change by minu : 5-1-2011 : eWPD System Stabilization 
        int adminLevelOptionSysId = this.getContractSession().getAdminLevelOptionAssnId();
        int cntrctParentSysId = this.getContractKeyObject().getContractParentSysId();
        
        // Get the request object from the getServiceRequest()
        RetrieveContractBenefitAdministrationRequest
			retrieveContractBenefitAdministrationRequest = 
				(RetrieveContractBenefitAdministrationRequest) this
                	.getServiceRequest(ServiceManager
                			.RETRIEVE_CONTRACT_BENEFIT_ADMINISTRATION);


        retrieveContractBenefitAdministrationRequest.setAdminOptSysId(adminOptionId);
        retrieveContractBenefitAdministrationRequest.setBenefitAdminSysId(benefitAminSysId);
        retrieveContractBenefitAdministrationRequest.setBenefitComponentId(benefitComponentId);
        retrieveContractBenefitAdministrationRequest.setBenefitId(benefitId);
        retrieveContractBenefitAdministrationRequest.setContractId(dateSegmentId);
        //    Code change by minu : 5-1-2011 : eWPD System Stabilization
        retrieveContractBenefitAdministrationRequest.setCntrctParentSysId(cntrctParentSysId); 
        retrieveContractBenefitAdministrationRequest.setAdminLevelOptionSysId(adminLevelOptionSysId);
        // Return the request object
        return retrieveContractBenefitAdministrationRequest;
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
        int adminOptionId = new Integer(this.getContractSession().getAdminOptionId()).intValue();
        
        int benefitComponentId = new Integer(this.getContractSession().getBenefitComponentId()).intValue();
        int benefitAminSysId = new Integer(this.getContractSession().getBenefitAdminId()).intValue();
        int benefitId = new Integer(this.getContractSession().getBenefitId()).intValue();
        int dateSegmentSysId = this.getContractKeyObject().getDateSegmentId();
        int contractParentSysId = this.getContractKeyObject().getContractParentSysId();
        
        
        RetrieveAllPossibleAnswerRequest
			retrieveAllPossibleAnswerRequest = 
				(RetrieveAllPossibleAnswerRequest) this
                	.getServiceRequest(ServiceManager
                			.RETRIEVE_ALL_POSSIBLE_ANSWER_LIST);
        
        retrieveAllPossibleAnswerRequest.setBenefitComponentId(benefitComponentId);
        retrieveAllPossibleAnswerRequest.setBenefitAminSysId(benefitAminSysId);
        retrieveAllPossibleAnswerRequest.setBenefitId(benefitId);
        retrieveAllPossibleAnswerRequest.setAdminOptSysId(adminOptionId);
        retrieveAllPossibleAnswerRequest.setDateSegmentSysId(dateSegmentSysId);
        retrieveAllPossibleAnswerRequest.setContractParentSysId(contractParentSysId);
        // Return the request object
        return retrieveAllPossibleAnswerRequest;
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
		String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}
		questionnaireList = (List)getContractSession().getQuestionnaireList();
		
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(questionnaireList,tildaArray);
			}
			tildaArray =null;
			this.tildaNoteStatus =null;
			
		tierList = (List) getContractSession().getTierQuestionnaireList();	
		if(null!=tildaTierArray && tildaTierArray.length>0){
	 			processTierQuestionaireList(tierList,tildaTierArray);
	 		}
	 		tildaTierArray =null;
	 		this.tildaTierNoteStatus =null;
	 		
		RetrieveContractBenefitAdministrationRequest retrieveContractQuestionnairRequest = 
			getRetrieveBenefitAdministrationRequest();
		
		possibleAnswerMap = getContractSession().getAllPossibleAnswerMap();
		retrieveContractQuestionnairRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		int rowNum = this.rowNum;
		int answerId =this.answerId;
		String answerDesc = this.answerDesc;
		
		int contractSysId= getContractSession().getContractKeyObject().getContractSysId();
		retrieveContractQuestionnairRequest.setContractId(contractSysId);
		//quesitionnaireList = (List)getSession().getAttribute("QUESTONNARE_LIST");
		ContractQuesitionnaireBO contractQuesitionnaireBO=(ContractQuesitionnaireBO)questionnaireList.get(rowNum);
		setValuesTOContractQuesitionnaireBO(
											contractQuesitionnaireBO,
											((ContractQuesitionnaireBO)questionnaireList.get(0)));
		getContractSession().setAdminLevelOptionSysId(contractQuesitionnaireBO.getAdminLevelOptionSysId());
		retrieveContractQuestionnairRequest.setQuestionareListIndex(rowNum);
		retrieveContractQuestionnairRequest.setContractQuesitionnaireBO(contractQuesitionnaireBO);
		retrieveContractQuestionnairRequest.setSelectedAnswerId(answerId);
		retrieveContractQuestionnairRequest.setSelectedAnswerDesc(answerDesc);
		retrieveContractQuestionnairRequest.setAction(RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD);
		retrieveContractQuestionnairRequest.setQuestionnareList(questionnaireList);
		RetrieveContractBenefitAdministrationResponse benefitAdministrationResponse = 
        	(RetrieveContractBenefitAdministrationResponse) this
                .executeService(retrieveContractQuestionnairRequest);
		if(null!=benefitAdministrationResponse){
		 this.questionnaireList = benefitAdministrationResponse.getBenefitAdministrationList();
		 getContractSession().setQuestionnaireList(questionnaireList);
		 getContractSession().setTierQuestionnaireList(tierList);
		 getContractSession().setAdminLevelOptionSysId(((ContractQuesitionnaireBO)questionnaireList.get(0)).getAdminLevelOptionSysId());
		 preparePanel(questionnaireList);
		 prepareTierPanel(tierList,false);
		}
		return "contractComponentQuestionnaireEdit";
		
	} 
	/*
	 * method for setting dateSegmentId,BcId and adminLevelOptionId to ContractQuesitionnaireBO
	 * @param ContractQuesitionnaireBO,adminLevelAssociationId
	 * 
	 * 
	 */
	private  void setValuesTOContractQuesitionnaireBO(ContractQuesitionnaireBO contractQuesitionnaireBO,ContractQuesitionnaireBO contractQuesitionnaireBOParent){
		
		int benefitComponentId = contractQuesitionnaireBOParent.getBenefitComponentId();
		contractQuesitionnaireBO.setBenefitComponentId(benefitComponentId);
		int dateSegmentId = this.getContractKeyObject().getDateSegmentId();
		contractQuesitionnaireBO.setDateSegmentId(dateSegmentId);
		contractQuesitionnaireBO.setAdminLevelOptionSysId(contractQuesitionnaireBOParent.getAdminLevelOptionSysId());
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
					((ContractQuesitionnaireBO) questionaireList
					.get(i)).setNoteExist("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((ContractQuesitionnaireBO) questionaireList
							.get(i)).setNoteExist("N");
							break;
				}
			}
		}		
	}
	
    
    /**
     * Returns the headerPanel.
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {
    	
        headerPanel = new HtmlPanelGrid();
        headerPanel.setWidth("100%");
        headerPanel.setColumns(5);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");
        headerPanel.setStyleClass("dataTableHeader");

        HtmlOutputText questionText = new HtmlOutputText();
        HtmlOutputText answerText = new HtmlOutputText();
        HtmlOutputText referenceTest = new HtmlOutputText();
        HtmlOutputText providerArrangement = new HtmlOutputText();
        HtmlOutputText notes = new HtmlOutputText();
        
        questionText.setValue("Question");
        questionText.setId("Question");

        answerText.setValue("Answer");
        answerText.setId("Answer");
        
        referenceTest.setValue("Reference");
        referenceTest.setId("Reference");
        
        providerArrangement.setValue("PVA");
        providerArrangement.setId("PVA");
        
        notes.setValue("Notes");
        notes.setId("Notes");

       	headerPanel.getChildren().add(questionText);
        headerPanel.getChildren().add(answerText);
        headerPanel.getChildren().add(referenceTest);
        headerPanel.getChildren().add(providerArrangement);
        headerPanel.getChildren().add(notes);
       
      
        return headerPanel;
    }
    
    public String selectNewTierQuestionnreList(){
    	
    	String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}	    
		String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		
 		RetrieveContractBenefitAdministrationRequest retrieveContractQuestionnairRequest = 
 			getRetrieveBenefitAdministrationRequest();
 		possibleAnswerMap = getContractSession().getAllPossibleAnswerMap();
		retrieveContractQuestionnairRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		
 		int rowNum = this.rowNum;
 		int answerId =this.answerId;
 		String answerDesc = this.answerDesc;
		// questionnaireList = (List)getContractSession().getQuestionnaireList();
 		tierList = (List) getContractSession().getTierQuestionnaireList();
 		questionnaireList = (List) getContractSession().getQuestionnaireList();
 		List tierQuestionList =getTierQuestionList (tierList);
 		//			start -- code for saving notes status to the questionnare list
 		if(null!=tildaTierArray && tildaTierArray.length>0){
 			processTierQuestionaireList(tierList,tildaTierArray);
 		}
 		tildaTierArray =null;
 		this.tildaTierNoteStatus =null;
 		
 		questionnaireList = (List)getContractSession().getQuestionnaireList();
 		
 		if(null!=tildaArray && tildaArray.length>0){
 			processQuestionaireList(questionnaireList,tildaArray);
 		}
 		tildaArray =null;
 		this.tildaNoteStatus =null;
 		
 		int contractSysId= getContractSession().getContractKeyObject().getContractSysId();
 		retrieveContractQuestionnairRequest.setContractId(contractSysId);
 		//quesitionnaireList = (List)getSession().getAttribute("QUESTONNARE_LIST");
 		ContractQuesitionnaireBO contractQuesitionnaireBO=(ContractQuesitionnaireBO)tierQuestionList.get(rowNum);
 		setValuesTOContractQuesitionnaireBO(
 											contractQuesitionnaireBO,
 											((ContractQuesitionnaireBO)tierQuestionList.get(0)));
 		getContractSession().setAdminLevelOptionSysId(contractQuesitionnaireBO.getAdminLevelOptionSysId());
 		retrieveContractQuestionnairRequest.setQuestionareListIndex(rowNum);
 		retrieveContractQuestionnairRequest.setContractQuesitionnaireBO(contractQuesitionnaireBO);
 		retrieveContractQuestionnairRequest.setSelectedAnswerId(answerId);
 		retrieveContractQuestionnairRequest.setSelectedAnswerDesc(answerDesc);
 		retrieveContractQuestionnairRequest.setAction(RetrieveContractBenefitAdministrationRequest.LOAD_SELECTED_CHILD_TIER);
 		retrieveContractQuestionnairRequest.setQuestionnareList(tierQuestionList);
 		RetrieveContractBenefitAdministrationResponse benefitAdministrationResponse = 
         	(RetrieveContractBenefitAdministrationResponse) this
                 .executeService(retrieveContractQuestionnairRequest);
 		if(null!=benefitAdministrationResponse){
 		 this.tierList = setTierQuestionList(tierList,benefitAdministrationResponse.getBenefitAdministrationList());
 		 getContractSession().setQuestionnaireList(questionnaireList);
 		 getContractSession().setTierQuestionnaireList(tierList);
 		 getContractSession().setAdminLevelOptionSysId(((ContractQuesitionnaireBO)questionnaireList.get(0)).getAdminLevelOptionSysId());
 		 preparePanel(questionnaireList);
 		 prepareTierPanel(tierList,false);
 		}
 		return "contractComponentQuestionnaireEdit";
    }
    /** 
	 * @param questionaireList
	 * @param tildaArray
	 */
	private void processTierQuestionaireList(List tierList,String[] tildaArray){
		
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				List questionnarelist = tierBo.getQuestionnaireList();
				int size = questionnarelist.size();
				for(int j=0;j<size;j++){
					for (int m=0;m<tildaArray.length;m++){
						if((new Integer(tierBo.getBenefitTierSysId()).toString()+new Integer(j).toString()+"Y").equals(tildaArray[m])){
							((ContractQuesitionnaireBO) questionnarelist.get(j)).setNoteExist("Y");
							break;
						}else if((new Integer(tierBo.getBenefitTierSysId()).toString()+new Integer(j).toString()+"N").equals(tildaArray[m])){
							((ContractQuesitionnaireBO) questionnarelist.get(j)).setNoteExist("N");
							break;
						}
						
					}
					
				}
			}
		}
		
	}
    
    /*
	 * Method for setting a questionnaire List to a tier definition List
	 */
	private List setTierQuestionList(List tierList, List questionList){
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				if(tierBo.getBenefitTierSysId() == Integer.parseInt(panelTierSysId)){
					tierBo.setQuestionnaireList(questionList);
				}
			}
		}
		return tierList;
	}
	
    /*
	 * Method to get the questionnarielIst corresponding to the selected tier 
	 */
	private List getTierQuestionList(List tierList){
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				if(tierBo.getBenefitTierSysId() == Integer.parseInt(panelTierSysId)){
					return tierBo.getQuestionnaireList();
				}
			}
		}
		return null;
	}
    /**
     * Returns the headerPanel.
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getHeaderPanelForPrint() {
    	
        headerPanelForPrint = new HtmlPanelGrid();
        headerPanelForPrint.setWidth("100%");
        headerPanelForPrint.setColumns(4);
        headerPanelForPrint.setBorder(0);
        headerPanelForPrint.setCellpadding("3");
        headerPanelForPrint.setCellspacing("1");
        headerPanelForPrint.setBgcolor("#cccccc");
        headerPanelForPrint.setStyleClass("dataTableHeader");

        HtmlOutputText questionText = new HtmlOutputText();
        HtmlOutputText answerText = new HtmlOutputText();
        HtmlOutputText referenceTest = new HtmlOutputText();
        HtmlOutputText providerArrangement = new HtmlOutputText();
       
        
        questionText.setValue("Question");
        questionText.setId("Question");

        answerText.setValue("Answer");
        answerText.setId("Answer");
        
        referenceTest.setValue("Reference");
        referenceTest.setId("Reference");
        
        providerArrangement.setValue("PVA");
        providerArrangement.setId("PVA");
        
       

        headerPanelForPrint.getChildren().add(questionText);
        headerPanelForPrint.getChildren().add(answerText);
        headerPanelForPrint.getChildren().add(referenceTest);
        headerPanelForPrint.getChildren().add(providerArrangement);
       
       
      
        return headerPanelForPrint;
    }
    /**
     * @return Returns the panel.
     */
    public HtmlPanelGrid getPanel() {
    	Logger.logInfo("getPanel() ");
    	StringBuffer line =null;
    	String finalline = null;
        panel = new HtmlPanelGrid();
        panel.setWidth("100%");
/*        if(componentType.equalsIgnoreCase("STANDARD"))
        	panel.setColumns(3);
        else*/
        panel.setColumns(4);	
        panel.setBorder(0);
        panel.setStyleClass("outputText");
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");
       
        StringBuffer rowClass = new StringBuffer();
        
        // Get the list of benefit administration from the database
        //quesitionnaireList = getBenefitAdministrationValues();
        
        //Getting the question count and setting it to session
//      //  int size=benefitAdminList.size();
//        HttpSession httpSession = getSession();
//   	    httpSession.setAttribute("QUESTIONS_COUNT",new Integer(size).toString());
   	    questionnaireList = getQuestionnaireList();
        if (questionnaireList != null) {
        	HtmlOutputText htmlOutputAnswer = null;
            HtmlSelectOneMenu htmlSelectOneMenu = null;
            for (int i = 0; i < questionnaireList.size(); i++) {
            	ContractQuesitionnaireBO contractQuesitionnaireBO=(ContractQuesitionnaireBO)questionnaireList.get(i);
            	HtmlOutputText htmlOutputText1 = new HtmlOutputText();
            	HtmlOutputText htmlOutputText2 = new HtmlOutputText();
            	HtmlOutputText htmlOutputText3 = new HtmlOutputText();
            	HtmlOutputText providerArrangement = new HtmlOutputText();
            	if(i>0){
            		rowClass.append(",");	
            	}
            	int level= contractQuesitionnaireBO.getLevel();
            	
            	if(level>1){
            		line = new StringBuffer("-");
            	for(int k=3;k<=level;k++){
            		if(k>1){
            			line.append("-");
            		}
            	}
            	finalline =line.toString() ;
            	htmlOutputText1.setValue(finalline+contractQuesitionnaireBO.getQuestionName());
            	rowClass.append("dataTableOddRow");
            	}else{
            		htmlOutputText1.setValue(contractQuesitionnaireBO.getQuestionName());
            		rowClass.append("dataTableEvenRow");
            	}
            	 List answerList =  contractQuesitionnaireBO.getPossibleAnswerList();
            	 List possibleAnswersList = (List)getPossibleAnswersListForAQuestion(answerList);
            	 
                 htmlSelectOneMenu = new HtmlSelectOneMenu();
                 htmlSelectOneMenu.setId("selectitem" + i);
                 UISelectItems uis = new UISelectItems();
                 uis.setValue(possibleAnswersList);

                 htmlSelectOneMenu.setValue(new Integer(
                 		contractQuesitionnaireBO.getSelectedAnswerid()).toString());
                 htmlSelectOneMenu.setStyleClass("formInputList");
                 htmlSelectOneMenu
                         .setStyleClass("formInputFieldBenefitStructure");
                 htmlSelectOneMenu.getChildren().add(uis);
                 htmlSelectOneMenu.setOnclick("return setCurrentValue(this)");                
                 htmlSelectOneMenu.setOnchange("return loadNewChild(this)");
                 
                 htmlOutputText3.setId("refere"+i);
                 htmlOutputText3.setValue(contractQuesitionnaireBO.getReferenceDesc());
                 
                 providerArrangement.setId("pva"+i);
                 providerArrangement.setValue(contractQuesitionnaireBO.getProviderArrangement());
                 
            	panel.getChildren().add(htmlOutputText1);
            	panel.getChildren().add(htmlSelectOneMenu); 
            	panel.getChildren().add(htmlOutputText3); 
            	panel.getChildren().add(providerArrangement); 
            }
        }
      panel.setRowClasses(rowClass.toString());
        return panel;
    }
    /**
	 * @param benefitAdminList2
	 */
	private void storeAnswerStates(List list) {
		if(null != list && list.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator i=list.iterator();i.hasNext();){
				ContractQuesitionnaireBO administration = (ContractQuesitionnaireBO)i.next();
				buffer.append(administration.getQuestionId());
				buffer.append("~");
				buffer.append(administration.getSelectedAnswerid());
				if(i.hasNext())
					buffer.append(":");
			}
			asnwerStates = buffer.toString();
		}
	}
	private void storeTieredAnswerStates(List tierList) {
		if(null != tierList && tierList.size()>0){
			for(int i =0;i<tierList.size();i++){
				BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
				List benefitTierList = defnBo.getBenefitTiers();
				for (int k =0; k<benefitTierList.size();k++){
					BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
					List list = tierBo.getQuestionnaireList();
					if (null != list && list.size() > 0) {
						StringBuffer buffer = new StringBuffer();
						for (Iterator l = list.iterator(); l.hasNext();) {
							ContractQuesitionnaireBO administration = (ContractQuesitionnaireBO) l
									.next();
							buffer.append(administration.getTierSysId());
							buffer.append("-");
							buffer.append(administration.getQuestionId());
							buffer.append("~");
							buffer.append(administration.getSelectedAnswerid());
							if (l.hasNext())
								buffer.append(":");
						}
						tieredQuestionsStates = buffer.toString();
					}
				}
			}
		}
		
	}
	
	
	private Map loadAnswerStates(String states){
		Map map = new HashMap();
		if(null != states && !"".equals(states.trim())){
			String[] qstnAsnwers = states.split(":");
			for(int i=0;i<qstnAsnwers.length;i++){
				String[] qstnAnswer = qstnAsnwers[i].split("~");
				map.put(qstnAnswer[0],qstnAnswer[1]);
			}
		}
		return map;
	}

	/**
     * To group the answer descriptions from the list to possibleAnswerBOs.
     * 
     * @param answersList
     */
    private List getPossibleAnswersListForAQuestion(List answersList) {
        Logger.logInfo("Entering the method for getting possible answer " 
        		+ "list for a question");
        // Create a list
        List answerDescriptionList = new ArrayList();

        // Check whether the answerList is null or empty
        if (null != answersList && !answersList.isEmpty()) {

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
        Logger.logInfo("Returning the method for getting possible answer " 
        		+ "list for a question");
        // Return the list
        return answerDescriptionList;

    }
    
   
    /**
     * To save the over ridden administration values to the db.
     * 
     * @return String
     */
    public String saveBenefitAdministration() {
    	
    	Logger.logInfo("Entering the method for saving benefit administration");
        
        String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}	
		
        SaveBenefitAdministrationRequest administrationRequest = 
        	(SaveBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager
                		.SAVE_CONTRACT_BENEFIT_ADMINISTRATION);
        
        
        questionnaireList = (List)this.getContractSession().getQuestionnaireList();
        tierList =(List)this.getContractSession().getTierQuestionnaireList();
        // Code change by minu : 28-12-2010 : eWPD System Stabilization 
        orgQuestionnaireList = (List)this.getContractSession().getOrgQuestionnaireList();     
        orgTierList =(List)this.getContractSession().getOrgTierQuestionnaireList();
        
        this.filterQuestionsForUpdate();
        
        
        if(null!=tildaArray && tildaArray.length>0){
        	processQuestionaireList(questionnaireList,tildaArray);
        }
        this.getContractSession().setQuestionnaireList(questionnaireList);
        tildaArray =null;
        this.tildaNoteStatus =null;
        
       // tierList = (List) getContractSession().getTierQuestionnaireList();	
		if(null!=tildaTierArray && tildaTierArray.length>0){
	 			processTierQuestionaireList(tierList,tildaTierArray);
	 		}
	 		tildaTierArray =null;
	 		this.tildaTierNoteStatus =null;

        int benefitComponentId = new Integer(this.getContractSession().getBenefitComponentId()).intValue();
        int benefitAminSysId = new Integer(this.getContractSession().getBenefitAdminId()).intValue();
        int adminOptionId = new Integer(this.getContractSession().getAdminOptionId()).intValue();
        // int dateSegmentId = this.getContractKeyObject().getDateSegmentId();
        
        int adminLevelOptionAssnId = this.getContractSession().getAdminLevelOptionAssnId();

        // Set the benefitcomponent id to the request
        administrationRequest.setBenefitComponentId(benefitComponentId);

        // Set the admin sys id to the request
        administrationRequest.setAdminSysId(adminOptionId);

        // Set the benefit admin sys id to the request
        administrationRequest.setBenefitAdminSysId(benefitAminSysId);
        
        //Sets the admin level option association id to request
        administrationRequest.setAdminLevelOptionAssnId(adminLevelOptionAssnId);
        
        administrationRequest.setBenefitId(this.getContractSession().getBenefitId());
        
        int dareSegmentId = this.getContractKeyObject().getDateSegmentId();
        administrationRequest.setDateSegmentSysId(dareSegmentId);

        administrationRequest.setQuestionnareList(getContractSession().getQuestionnaireList());
        administrationRequest.setTierList(getContractSession().getTierQuestionnaireList());
        // Code change by minu : 28-12-2010 : eWPD System Stabilization   
        administrationRequest.setQuestionnaireListToAdd(this.newQuestions);
        administrationRequest.setQuestionnaireListToUpdate(this.modifiedQuestions);
        administrationRequest.setQuestionnaireListToRemove(this.removedQuestions);
        administrationRequest.setTierQuestionnaireListToAdd(this.newTieredQuestions);
        administrationRequest.setTierQuestionnaireListToUpdate(this.modifiedTieredQuestions);
        administrationRequest.setTierQuestionnaireListToRemove(this.removedTieredQuestions);
        
        updateAMVForPS(administrationRequest);
        SaveContractBenefitAdministrationResponse administrationResponse = 
        	(SaveContractBenefitAdministrationResponse) this
                .executeService(administrationRequest);
        // cleaning the old values
        this.asnwerStates=null;
        this.tieredQuestionsStates = null;
        if(this.asnwerStates==null ||"".equalsIgnoreCase(this.asnwerStates)){
        	storeAnswerStates(administrationRequest.getQuestionnareList());
        	storeTieredAnswerStates(administrationRequest.getTierList());
        }	


        if(null !=administrationResponse ){
         	getRequest().setAttribute("RETAIN_Value","");
         	preparePanel(questionnaireList);
         	prepareTierPanel(tierList,false);
         	
         	//  Code change by minu : 28-12-2010 : eWPD System Stabilization 
         	//Creating original questionnaire list and setting in session
        	orgQuestionnaireList = new ArrayList();
        	for(int i=0;i<questionnaireList.size();i++){
        		ContractQuesitionnaireBO CtQueBO = new ContractQuesitionnaireBO();
        		CtQueBO.setQuestionnaireId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getQuestionnaireId()  );
        		CtQueBO.setQuestionId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getQuestionId()  );
        		CtQueBO.setParentQuestionnaireId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getParentQuestionnaireId()  );
        		CtQueBO.setSelectedAnswerid( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getSelectedAnswerid()  );
        		CtQueBO.setAdminLevelOptionSysId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getAdminLevelOptionSysId()  );
        		CtQueBO.setDateSegmentId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getDateSegmentId()  );
        		CtQueBO.setBenefitComponentId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getBenefitComponentId()  );
        		CtQueBO.setSelectedAnswerDesc(  ((ContractQuesitionnaireBO)questionnaireList.get(i)).getSelectedAnswerDesc()  );
        		orgQuestionnaireList.add(CtQueBO); 
        	}	 
        	// remove not answered questions from list, before setting to session.
        	getContractSession().setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	
        	//  Code change by minu : 28-12-2010 : eWPD System Stabilization 
        	//Creating original tier questionnaire list and setting in session
        	orgTierList = new ArrayList();
        	if(null != tierList){
        		
        		for(int j =0;j<tierList.size();j++){
					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
					List benefitTierList = defnBo.getBenefitTiers();
					for (int k =0; k<benefitTierList.size();k++){
						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						List questionList = tierBo.getQuestionnaireList();
						for(int l=0;l<questionList.size();l++){
							ContractQuesitionnaireBO contractQuesitionnaireBO = new ContractQuesitionnaireBO();
							contractQuesitionnaireBO.setQuestionnaireId( ((ContractQuesitionnaireBO)questionList.get(l)).getQuestionnaireId()  );
							contractQuesitionnaireBO.setQuestionId( ((ContractQuesitionnaireBO)questionList.get(l)).getQuestionId()  );
							contractQuesitionnaireBO.setParentQuestionnaireId( ((ContractQuesitionnaireBO)questionList.get(l)).getParentQuestionnaireId()  );
							contractQuesitionnaireBO.setSelectedAnswerid( ((ContractQuesitionnaireBO)questionList.get(l)).getSelectedAnswerid()  );
							contractQuesitionnaireBO.setTierSysId( ((ContractQuesitionnaireBO)questionList.get(l)).getTierSysId()  );
							contractQuesitionnaireBO.setAdminLevelOptionSysId( ((ContractQuesitionnaireBO)questionList.get(l)).getAdminLevelOptionSysId()  );
							contractQuesitionnaireBO.setDateSegmentId( ((ContractQuesitionnaireBO)questionList.get(l)).getDateSegmentId()  );
							contractQuesitionnaireBO.setBenefitComponentId( ((ContractQuesitionnaireBO)questionList.get(l)).getBenefitComponentId()  );
							contractQuesitionnaireBO.setSelectedAnswerDesc(  ((ContractQuesitionnaireBO)questionList.get(l)).getSelectedAnswerDesc() );
							orgTierList.add(contractQuesitionnaireBO);   
						}
					}
				}
        	
        	}
        	
        	getContractSession().setOrgTierQuestionnaireList(removeNotAnsweredQuestions(orgTierList));       	
        	
         	return "contractComponentQuestionnaireEdit";
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
    	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
    	questionnaireList = (List)this.getContractSession().getQuestionnaireList();	      
	    orgQuestionnaireList = (List)this.getContractSession().getOrgQuestionnaireList(); 
	    
	    newQuestions = new ArrayList(); 
	    modifiedQuestions = new ArrayList();
	    removedQuestions = new ArrayList();
	    
	    //Logic for non-tiered admin options
	    //for(int i=0;i<questionnaireList.size();i++){
	    Iterator it = questionnaireList.iterator();
	    while(it.hasNext()){
		    	ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO)it.next();
		    	//contractQuesitionnaireBO = (ContractQuesitionnaireBO)questionnaireList.get(i);
		    	if(!"Not Answered".equals(contractQuesitionnaireBO.getSelectedAnswerDesc()) ){
		    	int a=0;
		    	
		    	Iterator it1 = orgQuestionnaireList.iterator();
		    	while(it1.hasNext()){
		    		ContractQuesitionnaireBO contractQuesitionnaireBO1 = 
			    		(ContractQuesitionnaireBO)it1.next(); 
		    		    		if(contractQuesitionnaireBO.getQuestionnaireId() == contractQuesitionnaireBO1.getQuestionnaireId()){
						    		a++; 
						    		it1.remove();
						    		if(contractQuesitionnaireBO.getSelectedAnswerid() != contractQuesitionnaireBO1.getSelectedAnswerid()){
							    		modifiedQuestions.add(contractQuesitionnaireBO);
							    	}
					    		} 
		    	}
		    	
		    	if(a == 0){ 
		    		newQuestions.add(contractQuesitionnaireBO);
		    	}
		    	}//no need to compare not answered questions 
	    }
	    
	      removedQuestions = orgQuestionnaireList;
	    
	    tierList    = (List)this.getContractSession().getTierQuestionnaireList();
	    orgTierList = (List)this.getContractSession().getOrgTierQuestionnaireList();
	    
	    newTieredQuestions      = new ArrayList();
	    modifiedTieredQuestions = new ArrayList();
	    removedTieredQuestions  = new ArrayList();
	    
	    //Prepare Tiered questionnaireList to filter
	    ArrayList tierListToFilter = new ArrayList();
    	if(null != tierList){
    		
        		for(int j =0;j<tierList.size();j++){
					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
					List benefitTierList = defnBo.getBenefitTiers();
					for (int k =0; k<benefitTierList.size();k++){
						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						List questionList = tierBo.getQuestionnaireList();
						for(int l=0;l<questionList.size();l++){
							ContractQuesitionnaireBO contractQuesitionnaireBO = new ContractQuesitionnaireBO();
							ContractQuesitionnaireBO bo1 = (ContractQuesitionnaireBO)questionList.get(l);
							contractQuesitionnaireBO.setQuestionnaireId( bo1.getQuestionnaireId()  );
							contractQuesitionnaireBO.setQuestionId( bo1.getQuestionId()  );
							contractQuesitionnaireBO.setAdminLevelOptionSysId( bo1.getAdminLevelOptionSysId()  );
							contractQuesitionnaireBO.setDateSegmentId( bo1.getDateSegmentId()  );
							contractQuesitionnaireBO.setBenefitComponentId( bo1.getBenefitComponentId()  );
							contractQuesitionnaireBO.setParentQuestionnaireId( bo1.getParentQuestionnaireId()  );
							contractQuesitionnaireBO.setSelectedAnswerid( bo1.getSelectedAnswerid()  );
							contractQuesitionnaireBO.setSelectedAnswerDesc( bo1.getSelectedAnswerDesc()  );
							contractQuesitionnaireBO.setTierSysId( bo1.getTierSysId()  );
							contractQuesitionnaireBO.setBenefitId( bo1.getBenefitId());
							contractQuesitionnaireBO.setBenefitName(bo1.getBenefitName());
							contractQuesitionnaireBO.setBnftDefId(bo1.getBnftDefId());
							contractQuesitionnaireBO.setChildCount(bo1.getChildCount());
							contractQuesitionnaireBO.setCntrctParntSysId(bo1.getCntrctParntSysId());
							contractQuesitionnaireBO.setIsExcluded(bo1.getIsExcluded());
							contractQuesitionnaireBO.setLevel(bo1.getLevel());
							contractQuesitionnaireBO.setNoteExist(bo1.getNoteExist());
							contractQuesitionnaireBO.setPossibleAnswerList(bo1.getPossibleAnswerList());
							contractQuesitionnaireBO.setProviderArrangement(bo1.getProviderArrangement());
							contractQuesitionnaireBO.setQuestionName(bo1.getQuestionName());
							contractQuesitionnaireBO.setQuestionOrder(bo1.getQuestionOrder());
							contractQuesitionnaireBO.setReferenceDesc(bo1.getReferenceDesc());
							contractQuesitionnaireBO.setReferenceId(bo1.getReferenceId());
							contractQuesitionnaireBO.setSequenceNumber(bo1.getSequenceNumber());
							contractQuesitionnaireBO.setValidDomainToAttach(bo1.getValidDomainToAttach());
							tierListToFilter.add(contractQuesitionnaireBO);   
						}
					}
				}
        	
    	}
    	//  End : Prepare Tiered questionnaireList to filter
    	//	  Logic for tiered admin options
	    for(int m=0;m<tierListToFilter.size();m++){
		    	ContractQuesitionnaireBO contractQuesitionnaireBO4 = new ContractQuesitionnaireBO();
		    	contractQuesitionnaireBO4 = (ContractQuesitionnaireBO)tierListToFilter.get(m); 
		    	if(!"Not Answered".equals(contractQuesitionnaireBO4.getSelectedAnswerDesc()) ){
			    	int c=0;
			    				    	
			    	Iterator it2 = orgTierList.iterator();
			    	while(it2.hasNext()){
			    		ContractQuesitionnaireBO contractQuesitionnaireBO5 = 
				    		(ContractQuesitionnaireBO)it2.next(); 
			    		if((contractQuesitionnaireBO4.getQuestionnaireId() == contractQuesitionnaireBO5.getQuestionnaireId()) && 
				    			(contractQuesitionnaireBO4.getTierSysId() == contractQuesitionnaireBO5.getTierSysId())  ){
			    			c++;
				    		it2.remove();
				    		if(contractQuesitionnaireBO4.getSelectedAnswerid() != contractQuesitionnaireBO5.getSelectedAnswerid()){
					    		modifiedTieredQuestions.add(contractQuesitionnaireBO4);
					    	}
				    	}
			    	}
			    	
			    	if(c == 0){
			    		newTieredQuestions.add(contractQuesitionnaireBO4);
			    	}
		    	}// if not ansrd
	    }
	    removedTieredQuestions = orgTierList;
	    
	}
    
    
    /**
     * Method to remove not-answered questions from the given list
     * @param req
     */
    public List removeNotAnsweredQuestions(List questionnaireList){
    	if(null != questionnaireList){
    		Iterator it = questionnaireList.iterator();
    		while(it.hasNext()){
    			if( "Not Answered".equals( ((ContractQuesitionnaireBO)it.next()).getSelectedAnswerDesc().trim() )  ){
		    		it.remove();
		    	} 
    		}
    		
    	}
    	return questionnaireList;
    }
    /**
	 * @param administrationRequest
	 */
    private void updateAMVForPS(SaveBenefitAdministrationRequest req) {
    	boolean changed = false;
		List changedIds = new ArrayList();
		
		for (Iterator iter = req.getQuestionnaireListToAdd().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			changedIds.add(quesitionnaireBO.getQuestionId()+"");
		}
		for (Iterator iter = req.getQuestionnaireListToRemove().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			changedIds.add(quesitionnaireBO.getQuestionId()+"");
		}
		for (Iterator iter = req.getQuestionnaireListToUpdate().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			changedIds.add(quesitionnaireBO.getQuestionId()+"");
		}
		Set changedIdSet= new HashSet(changedIds);
		changedIds= new ArrayList(changedIdSet);
		
		List tierIdList= new ArrayList();
		List questionIdList= new ArrayList();
		
		for (Iterator iter = req.getTierQuestionnaireListToAdd().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			tierIdList.add(quesitionnaireBO.getTierSysId()+"");
			questionIdList.add(quesitionnaireBO.getQuestionId()+"");
		}
		for (Iterator iter = req.getTierQuestionnaireListToRemove().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			tierIdList.add(quesitionnaireBO.getTierSysId()+"");
			questionIdList.add(quesitionnaireBO.getQuestionId()+"");
		}
		for (Iterator iter = req.getTierQuestionnaireListToUpdate().iterator(); iter.hasNext();) {
			ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO) iter.next();				
			tierIdList.add(quesitionnaireBO.getTierSysId()+"");
			questionIdList.add(quesitionnaireBO.getQuestionId()+"");
		}
		if(questionIdList.size()>0){
			Set tierIdSet= new HashSet(tierIdList);
			tierIdList= new ArrayList(tierIdSet);
			
			Set questionIdSet= new HashSet(questionIdList);
			questionIdList= new ArrayList(questionIdSet);
		}
		if(	(tierIdList.size()>0 && questionIdList.size()>0) || (changedIds.size() >0) ){
			changed = true;
		}
		if(changed){
			req.setChanged(true);
			req.setChangedIds(changedIds);
			/* START CARS */
			req.setChangedTiers(questionIdList);
			req.setChangedTierSysIds(tierIdList);
			//req.setTierQstnIdMap(tierQstnIdMap);
			/* END CARS */
			req.setBCompName(getContractSession().getBenefitComponentDesc());
		}
	}

	/**
	 * @return
	 */
	private String getQuestionIdFromKey(String key){
		String[] values = key.split("-");
		if(values.length ==2){
			return values[1];
		}
		return "";
	}	
	/**
     * To get the list of over ridden values by taking it from the hidden.
     * hashMaps return List
     */
    private List getBenefitAdministrationOverriddenList() {

        // Create a list
        List benefitAdministrationList = new ArrayList();

        // Get the question id from the hidden key set of the HashMap
        Set idSet = datafieldMapForQuestionId.keySet();

        // Create the iterator for the questionId
        Iterator questionIdIter = idSet.iterator();

        // Get the answers key set from the HashMap
        Set answerSet = datafieldMapForAnswerId.keySet();
        
        

        // Create the iterator for the answerSet
        Iterator answerIterator = answerSet.iterator();

        int dateSegmentId = this.getContractKeyObject().getDateSegmentId();
        

        // Iterate through the HaspMap and get the individual question and
        // answer values and set them to the list
        while (questionIdIter.hasNext() && answerIterator.hasNext()) {

            // Get the long value
            Long iterationId = (Long) questionIdIter.next();

            // Create an instance of the VO
            BenefitAdministrationOverrideVO administrationOverrideVO = 
            	new BenefitAdministrationOverrideVO();

            // Get the value of id form the map
            String questionId = (String) datafieldMapForQuestionId
                    .get(iterationId);

            // Set the value of the question id
            administrationOverrideVO.setQuestionId((new Integer(questionId))
                    .intValue());

            // Get the value of id form the map
//            String answerId = (String) datafieldMapForAnswerId.get(iterationId);
            


			
            // Set the productStructureId
            administrationOverrideVO.setEntityId(dateSegmentId);
            
			

            // Set the value of the question id
            administrationOverrideVO.setAnswerId(new Integer(
                    ((String) datafieldMap.get(iterationId))).intValue());

            // Add the VO to the list
            benefitAdministrationList.add(administrationOverrideVO);
        }

        // Return the list
        return benefitAdministrationList;

    }
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
     * 
     * @return panel for view
     */
    public HtmlPanelGrid getViewPanel() {
    	Logger.logInfo("getViewPanel");
        panel = new HtmlPanelGrid();
        panel.setWidth("100%");
        panel.setColumns(4);
        panel.setBorder(0);
        panel.setStyleClass("outputText");
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        //panel.setBgcolor("#cccccc");
        StringBuffer line =null;
    	String finalline = null;
        
        // Get the list of benefit administration from the database
        benefitAdminList = getBenefitAdministrationValues();
        StringBuffer rowClass = new StringBuffer();

        if (benefitAdminList != null) {

            for (int i = 0; i < benefitAdminList.size(); i++) {
//                EntityBenefitAdministration benefitAdministration = (EntityBenefitAdministration) benefitAdminList
//                        .get(i);
            	ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO)benefitAdminList.get(i);

                HtmlOutputLabel lblForQuestion = new HtmlOutputLabel();
                lblForQuestion.setId("lblForQuestion" + RandomStringUtils.randomAlphanumeric(15));
                lblForQuestion.setFor("lblForQuestion" + i);

                HtmlOutputText htmlOutputText1 = new HtmlOutputText();
                htmlOutputText1.setStyleClass("mandatoryNormal");
                htmlOutputText1.setId("question" + i);
                htmlOutputText1.setValue(contractQuesitionnaireBO
                        .getQuestionName());

                HtmlInputHidden htmlInputHiddenForQuestionId = new HtmlInputHidden();
                
                if(i>0){
            		rowClass.append(",");	
            	}
            	int level= contractQuesitionnaireBO.getLevel();
            	
            	if(level>1){
            		line = new StringBuffer("-");
            	for(int k=3;k<=level;k++){
            		if(k>1){
            			line.append("-");
            		}
            	}
            	finalline =line.toString() ;
            	htmlOutputText1.setValue(finalline+contractQuesitionnaireBO.getQuestionName());
            	rowClass.append("dataTableOddRow");
            	}else{
            		htmlOutputText1.setValue(contractQuesitionnaireBO.getQuestionName());
            		rowClass.append("dataTableEvenRow");
            	}
                
                htmlInputHiddenForQuestionId.setValue(new Integer(
                		contractQuesitionnaireBO.getQuestionId()));
                htmlInputHiddenForQuestionId
                        .setId("htmlInputHiddenForQuestionId" + i);
                ValueBinding quesItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{contractBenefitAdministrationBackingBean.datafieldMapForQuestionId["
                                        + i + "]}");
                htmlInputHiddenForQuestionId.setValueBinding("value", quesItem);

                lblForQuestion.getChildren().add(htmlOutputText1);
                lblForQuestion.getChildren().add(htmlInputHiddenForQuestionId);

                HtmlOutputLabel lblForAnswer = new HtmlOutputLabel();
                lblForAnswer.setId("lblForAnswer" + RandomStringUtils.randomAlphanumeric(15));
                lblForAnswer.setFor("lblForAnswer" + i);

                HtmlInputHidden htmlInputHiddenForAnswerId = new HtmlInputHidden();
                htmlInputHiddenForAnswerId.setValue(new Integer(
                		contractQuesitionnaireBO.getSelectedAnswerid()));
                htmlInputHiddenForAnswerId.setId("htmlInputHiddenForAnswerId"
                        + i);
                Logger.logInfo("setting htmlInputHiddenForAnswerId ../..");
                ValueBinding answerItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{contractBenefitAdministrationBackingBean.datafieldMapForAnswerId["
                                        + i + "]}");
                htmlInputHiddenForAnswerId.setValueBinding("value", answerItem);

                List items = contractQuesitionnaireBO.getPossibleAnswerList();

               
               // String answerDescription = getSelectedAnswerDescFromPossibleAnswersList(items,contractQuesitionnaireBO.getSelectedAnswerid());
                String answerDescription = contractQuesitionnaireBO.getSelectedAnswerDesc();
                HtmlOutputText htmlOutputText3 = new HtmlOutputText();
                htmlOutputText3.setId("selectitem" + i);

                if(null != answerDescription){
                	htmlOutputText3.setValue(answerDescription);
                }
                htmlOutputText3.setStyleClass("mandatoryNormal");             

                HtmlOutputText htmlOutputText = new HtmlOutputText();
                htmlOutputText.setId("reference" + i);
                htmlOutputText.setValue(contractQuesitionnaireBO.getReferenceDesc()
                        );
                htmlOutputText.setStyleClass("mandatoryNormal");
                
                HtmlOutputText providerArrangement = new HtmlOutputText();
                providerArrangement.setId("pva" + i);
                providerArrangement.setValue(contractQuesitionnaireBO.getProviderArrangement());
                providerArrangement.setStyleClass("mandatoryNormal");


                lblForAnswer.getChildren().add(htmlOutputText3);
                lblForAnswer.getChildren().add(htmlInputHiddenForAnswerId);

                panel.getChildren().add(lblForQuestion);
//                panel.getChildren().add(htmlOutputText2);
                panel.getChildren().add(lblForAnswer);
                panel.getChildren().add(htmlOutputText);
                panel.getChildren().add(providerArrangement);
                
                this.renderFlagForPanel = true;
            }
        }
        else{
            this.renderFlagForPanel = false;
        }
        return panel;
    }
    
    
    public String loadQuestionsAction(){
    	
    	Logger.logDebug("Printing from loadQuestionsAction");
    	if((getContractSession().getMode()== ContractSession.VIEW_MODE)) {
    		return "displayContractBenefitAdministrationView";
    		
    	} else {
    		//14 jan 2011 - Code optimization for - get possible answers
    		possibleAnswerMap = getAllPossibleAnswersForAdminOption(); 
    		getContractSession().setAllPossibleAnswerMap(possibleAnswerMap);
    		 RetrieveContractBenefitAdministrationRequest
				retrieveContractBenefitAdministrationRequest = getRetrieveBenefitAdministrationRequest();
	        retrieveContractBenefitAdministrationRequest.setAction(RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_INITIAL);
	        retrieveContractBenefitAdministrationRequest.setAllPossibleAnswerMap(possibleAnswerMap);
	        // Call the executeService() to get the response
	        RetrieveContractBenefitAdministrationResponse 
				retrieveContractBenefitAdministrationResponse =
					(RetrieveContractBenefitAdministrationResponse) this
						.executeService(retrieveContractBenefitAdministrationRequest);

	        List benefitAdministrationList = null;
	        if(null!=retrieveContractBenefitAdministrationResponse && null !=retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList() && 
	        		retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList().size() >0){
	        	questionnaireList = (List)retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList().get(0);
	        	if(null!=questionnaireList && questionnaireList.size()!=0){
	        		
	            // Get the list of benefit administration values from the response
	        		
	        		
	        	getContractSession().setQuestionnaireList(questionnaireList);
	        	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	        	//Creating original questionnaire list and setting in session
	        	orgQuestionnaireList = new ArrayList();
	        	for(int i=0;i<questionnaireList.size();i++){
	        		ContractQuesitionnaireBO CtQueBO = new ContractQuesitionnaireBO();
	        		CtQueBO.setQuestionnaireId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getQuestionnaireId()  );
	        		CtQueBO.setQuestionId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getQuestionId()  );
	        		CtQueBO.setParentQuestionnaireId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getParentQuestionnaireId()  );
	        		CtQueBO.setSelectedAnswerid( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getSelectedAnswerid()  );
	        		CtQueBO.setAdminLevelOptionSysId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getAdminLevelOptionSysId()  );
	        		CtQueBO.setDateSegmentId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getDateSegmentId()  );
	        		CtQueBO.setBenefitComponentId( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getBenefitComponentId()  );
	        		CtQueBO.setSelectedAnswerDesc( ((ContractQuesitionnaireBO)questionnaireList.get(i)).getSelectedAnswerDesc() );
	        		orgQuestionnaireList.add(CtQueBO); 
	        	}	 
	        	getContractSession().setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
	        	
	        	getContractSession().setAdminLevelOptionSysId(((ContractQuesitionnaireBO)questionnaireList.get(0)).getAdminLevelOptionSysId());
	        	this.asnwerStates=null;
	        	if(retrieveContractBenefitAdministrationResponse
						.getBenefitAdministrationList().size() > 1){
					tierList=(List)retrieveContractBenefitAdministrationResponse
					.getBenefitAdministrationList().get(1);
					
				}
	        	getContractSession().setTierQuestionnaireList(tierList);
	        	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	        	//Creating original tier questionnaire list and setting in session
	        	orgTierList = new ArrayList();
	        	if(null != tierList){
	        		
	            		for(int j =0;j<tierList.size();j++){
	    					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
	    					List benefitTierList = defnBo.getBenefitTiers();
	    					for (int k =0; k<benefitTierList.size();k++){
	    						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
	    						List questionList = tierBo.getQuestionnaireList();
	    						for(int l=0;l<questionList.size();l++){
	    							ContractQuesitionnaireBO contractQuesitionnaireBO = new ContractQuesitionnaireBO();
	    							contractQuesitionnaireBO.setQuestionnaireId( ((ContractQuesitionnaireBO)questionList.get(l)).getQuestionnaireId()  );
	    							contractQuesitionnaireBO.setQuestionId( ((ContractQuesitionnaireBO)questionList.get(l)).getQuestionId()  );
	    							contractQuesitionnaireBO.setParentQuestionnaireId( ((ContractQuesitionnaireBO)questionList.get(l)).getParentQuestionnaireId()  );
	    							contractQuesitionnaireBO.setSelectedAnswerid( ((ContractQuesitionnaireBO)questionList.get(l)).getSelectedAnswerid()  );
	    							contractQuesitionnaireBO.setTierSysId( ((ContractQuesitionnaireBO)questionList.get(l)).getTierSysId()  );
	    							contractQuesitionnaireBO.setAdminLevelOptionSysId( ((ContractQuesitionnaireBO)questionList.get(l)).getAdminLevelOptionSysId()  );
	    							contractQuesitionnaireBO.setDateSegmentId( ((ContractQuesitionnaireBO)questionList.get(l)).getDateSegmentId()  );
	    							contractQuesitionnaireBO.setBenefitComponentId( ((ContractQuesitionnaireBO)questionList.get(l)).getBenefitComponentId()  );
	    							contractQuesitionnaireBO.setSelectedAnswerDesc( ((ContractQuesitionnaireBO)questionList.get(l)).getSelectedAnswerDesc() );
	    							orgTierList.add(contractQuesitionnaireBO);   
	    						}
	    					}
	    				}
	            	
	        	}
	        	getContractSession().setOrgTierQuestionnaireList(removeNotAnsweredQuestions(orgTierList));
	        	preparePanel(questionnaireList);
	        	prepareTierPanel(tierList,false);
	    		
	        	}else{
	        		this.questionnarePanel = null;
	        	}
	        }
	        return "contractComponentQuestionnaireEdit";
    	}
    }
    
    public java.util.HashMap getAllPossibleAnswersForAdminOption(){
    	
    		//14 jan 2011 - Code optimization for - get possible answers
    	RetrieveAllPossibleAnswerRequest retrieveAllPossibleAnswerRequest = getRetrieveAllPossibleAnswerRequest();
	        // Call the executeService() to get the response
    	RetrieveAllPossibleAnswerResponse 
				retrieveAllPossibleAnswerResponse =
					(RetrieveAllPossibleAnswerResponse) this
						.executeService(retrieveAllPossibleAnswerRequest);
    	return retrieveAllPossibleAnswerResponse.getAllPossibleAnswerMap();
    }
    
    public void prepareTierPanel(List tierList,boolean isPrintMode){
    	Logger.logInfo("prepareTierPanel");
		if(null != tierList && tierList.size()>0){
			
			sortTiers(tierList);
			tierQuestionarePanel = new HtmlPanelGrid();
			tierQuestionarePanel.setColumns(1);
			tierQuestionarePanel.setWidth("100%");
			tierQuestionarePanel.setBorder(0);
			tierQuestionarePanel.setCellpadding("0");
			tierQuestionarePanel.setCellspacing("0");
			HtmlPanelGrid tierDefPanel = null;
			HtmlPanelGrid questionpanel = null;
			// iterating definition  
			for (int i=0; i<tierList.size();i++){
				BenefitTierDefinition tierDefinition = (BenefitTierDefinition)tierList.get(i);
				tierDefPanel = new HtmlPanelGrid();
				tierDefPanel.setColumns(1);
				tierDefPanel.setWidth("100%");
				tierDefPanel.setBorder(0);
				tierDefPanel.setCellpadding("0");
				tierDefPanel.setCellspacing("0");
				if(!isPrintMode()){
				    tierDefPanel.setBgcolor("#cccccc");
				}				
				
				HtmlOutputLabel defLabel = new HtmlOutputLabel();
				defLabel.setId("NN"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText tierDef = new HtmlOutputText();
				tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
				if(!isPrintMode()){
				    tierDef.setStyle("color:blue");
				}				
				defLabel.getChildren().add(tierDef);
				tierDefPanel.getChildren().add(defLabel);
				tierQuestionarePanel.getChildren().add(tierDefPanel);
				List benefitTierList = tierDefinition.getBenefitTiers();
				// iterating the tiers
				if(null !=benefitTierList){
					if (this.tieredQuestionsStates == null
							|| "".equalsIgnoreCase(this.tieredQuestionsStates))
						storeTieredAnswerStates(tierList);
					for(int j=0;j<benefitTierList.size();j++){
						BenefitTier tierBO=(BenefitTier)benefitTierList.get(j);
						
						tierDefPanel = new HtmlPanelGrid();
						tierDefPanel.setColumns(1);
						tierDefPanel.setWidth("100%");
						tierDefPanel.setBorder(0);
						tierDefPanel.setCellpadding("3");
						tierDefPanel.setCellspacing("1");
						if(!isPrintMode()){
						    tierDefPanel.setBgcolor("#ffffff");
						}						
						List criteriaList = tierBO.getBenefitTierCriteriaList();
						HtmlOutputLabel criteriaLabel = new HtmlOutputLabel();
						criteriaLabel.setId("mm"+RandomStringUtils.randomAlphanumeric(15));
						HtmlOutputText criteriaValues = new HtmlOutputText();
						StringBuffer criteriaStr = new StringBuffer();
						if(null!=criteriaList){
							// iterating the criteria
							for(int k=0;k<criteriaList.size();k++){
								BenefitTierCriteria criteriaBO = (BenefitTierCriteria)criteriaList.get(k);
								criteriaStr =criteriaStr.append(criteriaBO.getBenefitTierCriteriaName()).append(" : ").append(criteriaBO.getBenefitTierCriteriaValue()).append("  ");
							}
						}
						criteriaValues.setValue(criteriaStr.toString());
						if(!isPrintMode()){
						    criteriaValues.setStyle("color:blue");
						}						
						criteriaLabel.getChildren().add(criteriaValues);
						tierDefPanel.getChildren().add(criteriaLabel);
						tierQuestionarePanel.getChildren().add(tierDefPanel);
						
						List tierQuestionList =tierBO.getQuestionnaireList();
						String finalline = null;
						questionpanel = new HtmlPanelGrid();
						questionpanel.setWidth("100%");						
						if(!isPrintMode()){
						    questionpanel.setColumns(5);						
						    questionpanel.setColumnClasses("gridColumn30,gridColumn20,gridColumn25,gridColumn15,gridColumn10");
						}
						else{
						    questionpanel.setColumns(4);
						    questionpanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn25,gridColumn15");
						}
						questionpanel.setBorder(0);
						questionpanel.setStyleClass("outputText");
						questionpanel.setCellpadding("3");
						questionpanel.setCellspacing("1");
						if(!isPrintMode()){
						    questionpanel.setBgcolor("#cccccc");
						}						
						StringBuffer rowClass = new StringBuffer();
						if(null != tierQuestionList){
							
							
							HtmlSelectOneMenu answerSelectOneMenu = null;
							HtmlOutputText questionOutputText = null;
							HtmlOutputText referenceOutputText = null;
							HtmlOutputText pvaOutputText = null;
							HtmlInputHidden childCountHidden = null;
							HtmlOutputText answerOutputText = null;
							HtmlPanelGroup referenceGroup = null;
							HtmlPanelGroup pvaGroup = null;
							HtmlGraphicImage notesAttachmentImage =null;
							 HtmlOutputText notesHidden = null;
							// iterating questions in tier
							for(int q=0;q<tierQuestionList.size();q++){
								
								ContractQuesitionnaireBO contractQuestionareBO = (ContractQuesitionnaireBO) tierQuestionList
								.get(q);
								questionOutputText = new HtmlOutputText();
								referenceOutputText = new HtmlOutputText();
								pvaOutputText = new HtmlOutputText();
								childCountHidden = new HtmlInputHidden();
								referenceGroup = new HtmlPanelGroup();
								pvaGroup = new HtmlPanelGroup();
								notesAttachmentImage = new HtmlGraphicImage();
								notesHidden = new HtmlOutputText();
				            	notesHidden.setId("notesHidden"+q+"tier"+contractQuestionareBO.getTierSysId()); 
								if (q > 0) {
									rowClass.append(",");
								}
								int level = contractQuestionareBO.getLevel();
								if (level > 1) {
									finalline = getLevelPrefix(level);
									questionOutputText.setValue(finalline
											+ contractQuestionareBO.getQuestionName());
									rowClass.append("dataTableOddRow");
								} else {
									questionOutputText.setValue(contractQuestionareBO
											.getQuestionName());
									if(!isPrintMode()){
									    rowClass.append("dataTableEvenRow");
									}
								}
								childCountHidden.setId("childCount" + q+"tier"+contractQuestionareBO.getTierSysId());
								childCountHidden.setValue(new Integer(contractQuestionareBO
										.getChildCount()));
								List answerList = contractQuestionareBO.getPossibleAnswerList();
								String selectedAnswer = contractQuestionareBO.getSelectedAnswerDesc();	
								if(super.isViewMode() || isPrintMode()){
									answerOutputText = new HtmlOutputText();
									answerOutputText.setValue(selectedAnswer);			
								}else{
									List possibleAnswersList = (List) getPossibleAnswersListForAQuestion(answerList);
									answerSelectOneMenu = new HtmlSelectOneMenu();
									answerSelectOneMenu.setId("selectitem" + q+"tier"+contractQuestionareBO.getTierSysId());
									UISelectItems uis = new UISelectItems();
									uis.setValue(possibleAnswersList);
			
									answerSelectOneMenu.setValue(new Integer(contractQuestionareBO
											.getSelectedAnswerid()).toString());
									answerSelectOneMenu.setStyleClass("formInputList");
									answerSelectOneMenu
											.setStyleClass("formInputFieldBenefitStructure");
									answerSelectOneMenu.getChildren().add(uis);
									answerSelectOneMenu.setOnclick("return setCurrentValue(this)");
									answerSelectOneMenu.setOnchange("return loadNewChildTier(this)");
								}	
								referenceOutputText.setId("refere" + q+"tier"+contractQuestionareBO.getTierSysId());
								referenceOutputText.setValue(contractQuestionareBO
										.getReferenceDesc());
								referenceGroup.getChildren().add(referenceOutputText);
								referenceGroup.getChildren().add(childCountHidden);
								
								
								pvaOutputText.setId("pva" + q+"tier"+contractQuestionareBO.getTierSysId());
								pvaOutputText.setValue(contractQuestionareBO
										.getProviderArrangement());
								pvaGroup.getChildren().add(pvaOutputText);
						//		pvaGroup.getChildren().add(childCountHidden);
								
								 
		//						start notes
								ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);
								
								int bcId = getBenefitComponentIdFromSession();
								String imageid= new Integer(i).toString();
								  if(q==0){
				                 	this.dateSegmentId =contractQuestionareBO.getDateSegmentId();
				                 	this.adminlvloptionid = contractQuestionareBO.getAdminLevelOptionSysId();
				                 }
								String primaryType= "ATTACHCONTRACT";
								if(contractQuestionareBO.getNoteExist().equals("Y")){
								notesAttachmentImage.setUrl("../../images/notes_exist.gif");
								}else{
									notesAttachmentImage.setUrl("../../images/page.gif");
								} 
								notesAttachmentImage.setId("notesAttachmentImage"+ q+"tier"+contractQuestionareBO.getTierSysId());
								notesAttachmentImage.setStyle("cursor:hand;");
								if(super.isViewMode()){
									notesAttachmentImage.setOnclick("loadTierNotes('../popups/contractTieredQuestionNotesViewPopup.jsp'+getUrl(),'"
											+ contractQuestionareBO.getTierSysId()
											+ "','"
	                                        + contractQuestionareBO.getQuestionId()
	                                        + "','"
	                                        + dateSegmentId
	                                        + "','"
	                                        + primaryType
	                                        + "','"
												+getContractSession().getBenefitComponentId()
												+ "','"
												+contractQuestionareBO.getBnftDefId()
												+ "','"
												+adminlvloptionid
											+"','"
											+"notesAttachmentImage" +q+"','"+q
											 +"');return false;");   			
								}else{
								
								notesAttachmentImage.setOnclick("loadTierNotes('../popups/contractTieredQuestionNotesPopup.jsp'+getUrl(),'"
										+ contractQuestionareBO.getTierSysId()
										+ "','"
                                        + contractQuestionareBO.getQuestionId()
                                        + "','"
                                        + dateSegmentId
                                        + "','"
                                        + primaryType
                                        + "','"
											+getContractSession().getBenefitComponentId()
											+ "','"
											+contractQuestionareBO.getBnftDefId()
											+ "','"
											+adminlvloptionid
										+"','"
										+"notesAttachmentImage" +q+"','"+q
										 +"');return false;");                     
								//end of notes
								}
				                questionpanel.getChildren().add(questionOutputText);
				                if(super.isViewMode()|| isPrintMode()){
				                	questionpanel.getChildren().add(answerOutputText);
				                }else{
				                	questionpanel.getChildren().add(answerSelectOneMenu);
				                }
				                questionpanel.getChildren().add(referenceGroup);
				                questionpanel.getChildren().add(pvaGroup);
								
								if ("Y".equals(contractQuestionareBO
										.getValidDomainToAttach()) && !isPrintMode()) {
									questionpanel.getChildren().add(notesAttachmentImage);
								} else if (!"Y".equals(contractQuestionareBO
										.getValidDomainToAttach()) && !isPrintMode()){
									questionpanel.getChildren().add(notesHidden);
								}
									
							}
							questionpanel.setRowClasses(rowClass.toString());
							tierQuestionarePanel.getChildren().add(questionpanel);
						}
					}
				}
			}
		}else{
			tierQuestionarePanel= null;
		}
	}
    
    private void sortTiers(List benefitDefinitonsList) {
		if(null!=benefitDefinitonsList){
		   //Collections.sort(benefitDefinitonsList);
		   for (Iterator iter = benefitDefinitonsList.iterator(); iter.hasNext();) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) iter.next();
			 if(null != tierDef) {
			 	Collections.sort(tierDef.getBenefitTiers());
			 }
			
		   }
		   //		 removing the tiers which does not have any questions 
		   for (Iterator iterDef = benefitDefinitonsList.iterator(); iterDef.hasNext();) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) iterDef.next();
			if(null !=tierDef){
				 List tierList = tierDef.getBenefitTiers();
				 for (int i=0; i<tierList.size();i++){
				 	BenefitTier tierBo = (BenefitTier)tierList.get(i);
				 	if (null !=tierBo &&( null ==tierBo.getQuestionnaireList() || tierBo.getQuestionnaireList().size() ==0)){
				 		tierList.remove(tierBo);
				 		i--;
				 	}
				 }
			}	
			
		   }
		   //removing the tierDefinitions which doesnothave any tiers
		   for (int i=0;i<benefitDefinitonsList.size();i++) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) benefitDefinitonsList.get(i);
				if(null !=tierDef && (null ==tierDef.getBenefitTiers() || tierDef.getBenefitTiers().size() ==0)){
					benefitDefinitonsList.remove(tierDef);
					i--;
				}
		   }
		}
	}
	//WAS 7.0 Changes - Binding variable LoadQuestionForView modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0

    public HtmlInputHidden getLoadQuestionForView() {
    	
    	List benefitAdministrationList= getBenefitAdministrationValues();
    	int dateSegmentId=0;
    	
    	if ( null != benefitAdministrationList) {
    		this.viewQuestionnaireList =benefitAdministrationList;
			
			for (int i = 0; i < viewQuestionnaireList.size(); i++) {
				ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO) viewQuestionnaireList.get(i);
				String questionName = contractQuesitionnaireBO.getQuestionName();
				questionName = getLevelPrefix(contractQuesitionnaireBO.getLevel())+ questionName;
				dateSegmentId=contractQuesitionnaireBO.getDateSegmentId();
				contractQuesitionnaireBO.setQuestionName(questionName);
			}
		}	
    	
    	setValuesToHidden(dateSegmentId);
    	loadQuestionForView.setValue("");
		return loadQuestionForView;
    }
    private void setValuesToHidden(int dateSegmentId){
    	
    	this.setBenefitComponentId(Integer.toString(getContractSession().getBenefitComponentId()));
		this.setPrimaryEntityID(Integer.toString(dateSegmentId));
    
    }
   
   public void setLoadQuestionForView(HtmlInputHidden temp) {
   	
   }
    /**
     * @return Returns the panel.
     */
    public void preparePanel(List questionnareList){
    	StringBuffer line =null;
    	String finalline = null;
    	questionnarePanel = new HtmlPanelGrid();
        questionnarePanel.setWidth("100%");
        questionnarePanel.setColumns(5);	
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
            HtmlOutputText providerArrangement = null;
            HtmlGraphicImage notesAttachmentImage = null;
            HtmlOutputText notesHidden = null;
            if(this.asnwerStates==null ||"".equalsIgnoreCase(this.asnwerStates))
        	storeAnswerStates(questionnareList);
            for (int i = 0; i < questionnareList.size(); i++) {
            	ContractQuesitionnaireBO contractQuesitionnaireBO=(ContractQuesitionnaireBO)questionnareList.get(i);
            	questionOutputText = new HtmlOutputText();
            	referenceOutputText = new HtmlOutputText();
            	childCountHidden =new HtmlInputHidden();
            	referenceGroup = new HtmlPanelGroup();
            	providerArrangement = new HtmlOutputText();
            	notesAttachmentImage = new HtmlGraphicImage();
            	notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i); 
            	
            	if(i>0){
            		rowClass.append(",");	
            	}
            	int level= contractQuesitionnaireBO.getLevel();
            	if(level>1){
            	finalline = getLevelPrefix(level);
            	questionOutputText.setValue(finalline+contractQuesitionnaireBO.getQuestionName());
            	rowClass.append("dataTableOddRow");
            	}else{
            		questionOutputText.setValue(contractQuesitionnaireBO.getQuestionName());
            		rowClass.append("dataTableEvenRow");
            	}
            	 childCountHidden.setId("childCount"+i);
            	 childCountHidden.setValue(new Integer(contractQuesitionnaireBO.getChildCount()));
            	 List answerList =  contractQuesitionnaireBO.getPossibleAnswerList();
            	 List possibleAnswersList = (List)getPossibleAnswersListForAQuestion(answerList);
            	 
            	 answerSelectOneMenu = new HtmlSelectOneMenu();
            	 answerSelectOneMenu.setId("selectitem" + i);
                 UISelectItems uis = new UISelectItems();
                 uis.setValue(possibleAnswersList);

                 answerSelectOneMenu.setValue(new Integer(
                 		contractQuesitionnaireBO.getSelectedAnswerid()).toString());
                 answerSelectOneMenu.setStyleClass("formInputList");
                 answerSelectOneMenu
                         .setStyleClass("formInputFieldBenefitStructure");
                 answerSelectOneMenu.getChildren().add(uis);
                 answerSelectOneMenu.setOnclick("return setCurrentValue(this)");
                 answerSelectOneMenu.setOnchange("return loadNewChild(this)");
                 
                 referenceOutputText.setId("refere"+i);
                 referenceOutputText.setValue(contractQuesitionnaireBO.getReferenceDesc());
                 
                 providerArrangement.setId("pva"+i);
                 providerArrangement.setValue(contractQuesitionnaireBO.getProviderArrangement());
                 if(i==0){
                 	this.dateSegmentId =contractQuesitionnaireBO.getDateSegmentId();
                 	this.adminlvloptionid = contractQuesitionnaireBO.getAdminLevelOptionSysId();
                 }
                 
                 if(contractQuesitionnaireBO.getNoteExist().equals("Y")){
    				notesAttachmentImage.setUrl("../../images/notes_exist.gif");
    				}else{
    					notesAttachmentImage.setUrl("../../images/page.gif");
    				}
        	 String primaryType="ATTACHCONTRACT";
        	 
        	this.setBenefitDefnId(contractQuesitionnaireBO.getBnftDefId());
               
				 notesAttachmentImage.setStyle("cursor:hand;");
				 notesAttachmentImage.setId("notesAttachmentImage"+ i);
             notesAttachmentImage.setOnclick("loadNotesPopup('../popups/contractQuestionNotesPopup.jsp'+getUrl(),'"
                                                 + contractQuesitionnaireBO.getQuestionId()
                                                 + "','"
                                                 + dateSegmentId
                                                 + "','"
                                                 + primaryType
                                                 + "','"
													+getContractSession().getBenefitComponentId()
													+ "','"
													+contractQuesitionnaireBO.getBnftDefId()
													+ "','"
													+adminlvloptionid
												+"','"
												+"notesAttachmentImage" +i+"','"+i
        										 +"');return false;");    
                 
                 
                 referenceGroup.getChildren().add(referenceOutputText);
                 referenceGroup.getChildren().add(childCountHidden);
            	questionnarePanel.getChildren().add(questionOutputText);
            	questionnarePanel.getChildren().add(answerSelectOneMenu); 
            	questionnarePanel.getChildren().add(referenceGroup);
            	questionnarePanel.getChildren().add(providerArrangement);  
            	
            	// Check for the functional domain.
            	if ("Y".equals(contractQuesitionnaireBO
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
     * @return contractType
     * 
     * Returns the contractType.
     */
    public String getContractType() {
        
        contractType = super.getContractKeyObject().getContractType();
        return contractType;
    }
    /**
     * @param contractType
     * 
     * Sets the contractType.
     */
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    /**
     * Sets the headerPanel.
     * 
     * @param headerPanel.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }
    /**
     * @return Returns the datafieldMapForAnswerId.
     */
    public Map getDatafieldMapForAnswerId() {
        return datafieldMapForAnswerId;
    }

    /**
     * @param datafieldMapForAnswerId
     *            The datafieldMapForAnswerId to set.
     */
    public void setDatafieldMapForAnswerId(Map datafieldMapForAnswerId) {
        this.datafieldMapForAnswerId = datafieldMapForAnswerId;
    }

    /** 
     * Returns the datafieldMap.
     * @return Map datafieldMap.
     * 
     */
    public Map getDatafieldMap() {
        return datafieldMap;
    }

    /** 
     * Sets the datafieldMap.
     * @param datafieldMap.
     * 
     */
    public void setDatafieldMap(Map datafieldMap) {
        this.datafieldMap = datafieldMap;
    }
    
    /**
	 * Returns the datafieldMapForQuestionId
	 * @return Map datafieldMapForQuestionId.
	 */
	public Map getDatafieldMapForQuestionId() {
		return datafieldMapForQuestionId;
	}
	/**
	 * Sets the datafieldMapForQuestionId
	 * @param datafieldMapForQuestionId.
	 */
	public void setDatafieldMapForQuestionId(Map datafieldMapForQuestionId) {
		this.datafieldMapForQuestionId = datafieldMapForQuestionId;
	}

    /**
     * Returns the benefitAdminList
     * @return List benefitAdminList.
     */
    public List getBenefitAdminList() {
        return benefitAdminList;
    }
    /**
     * Sets the benefitAdminList
     * @param benefitAdminList.
     */
    public void setBenefitAdminList(List benefitAdminList) {
        this.benefitAdminList = benefitAdminList;
    }
    
    
    /**
     * Returns the load
     * @return String load.
     */
    public String getLoad() {
        displayContractBenefitAdministration();
        return load;
    }
    /**
     * Sets the load
     * @param load.
     */
    public void setLoad(String load) {
        this.load = load;
    }
   
    /**
     * Sets the panel
     * @param panel.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }
   
    
    /**
     * Sets the viewPanel
     * @param viewPanel.
     */
    public void setViewPanel(HtmlPanelGrid viewPanel) {
        this.viewPanel = viewPanel;
    }
    
	/**
	 * @return Returns the loadPrintPage.
	 */
  //WAS 7.0 Changes - Binding variable LoadPrintPage modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	public HtmlInputHidden getLoadPrintPage() {
	    this.printMode = true;
		displayContractBenefitAdministration();	
		loadPrintPage.setValue("");
		//return "";
		return loadPrintPage;
	}
	/**
	 * @param loadPrintPage The loadPrintPage to set.
	 */
	public void setLoadPrintPage(HtmlInputHidden loadPrintPage) {
		this.loadPrintPage = loadPrintPage;
	}
	/**
	 * @return Returns the printMode.
	 */
	public boolean isPrintMode() {
		return printMode;
	}
	/**
	 * @param printMode The printMode to set.
	 */
	public void setPrintMode(boolean printMode) {
		this.printMode = printMode;
	}
    /**
     * @return hiddenInit
     * 
     * Returns the hiddenInit.
     */
    public String getHiddenInit() {
        return hiddenInit;
    }
    /**
     * @param hiddenInit
     * 
     * Sets the hiddenInit.
     */
    public void setHiddenInit(String hiddenInit) {
        this.hiddenInit = hiddenInit;
    }
    /**
     * @return renderFlagForPanel
     * 
     * Returns the renderFlagForPanel.
     */
    public boolean isRenderFlagForPanel() {
        return renderFlagForPanel;
    }
    /**
     * @param renderFlagForPanel
     * 
     * Sets the renderFlagForPanel.
     */
    public void setRenderFlagForPanel(boolean renderFlagForPanel) {
        this.renderFlagForPanel = renderFlagForPanel;
    }
    /**
     * @return hiddenAdminOption
     * 
     * Returns the hiddenAdminOption.
     */
    public String getHiddenAdminOption() {
       
        return hiddenAdminOption;
    }
    /**
     * @param hiddenAdminOption
     * 
     * Sets the hiddenAdminOption.
     */
    public void setHiddenAdminOption(String hiddenAdminOption) {
        this.hiddenAdminOption = hiddenAdminOption;
    }
	/**
	 * @return Returns the asnwerStates.
	 */
	public String getAsnwerStates() {
		return asnwerStates;
	}
	/**
	 * @param asnwerStates The asnwerStates to set.
	 */
	public void setAsnwerStates(String asnwerStates) {
		this.asnwerStates = asnwerStates;
	}

	/**
	 * @return Returns the hiddenForQuestionare.
	 *//*
	public String getHiddenForQuestionare() {
		this.quesitionnaireList  = this.getQuesitionnaireList();
		return hiddenForQuestionare;
	}
	*//**
	 * @param hiddenForQuestionare The hiddenForQuestionare to set.
	 *//*
	public void setHiddenForQuestionare(String hiddenForQuestionare) {
		this.hiddenForQuestionare = hiddenForQuestionare;
	}*/
	/**
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		Logger.logInfo("Entering the method for getting benefit " 
        		+ "administration values");
        RetrieveContractBenefitAdministrationRequest
			retrieveContractBenefitAdministrationRequest = getRetrieveBenefitAdministrationRequest();
        retrieveContractBenefitAdministrationRequest.setAction(RetrieveContractBenefitAdministrationRequest.QUESTIONNARE_INITIAL);
        // Call the executeService() to get the response
        RetrieveContractBenefitAdministrationResponse 
			retrieveContractBenefitAdministrationResponse =
				(RetrieveContractBenefitAdministrationResponse) this
					.executeService(retrieveContractBenefitAdministrationRequest);

        List benefitAdministrationList = null;
        if(null!=retrieveContractBenefitAdministrationResponse){
        	if(null==questionnaireList || questionnaireList.size()==0){
            // Get the list of benefit administration values from the response
        		questionnaireList = retrieveContractBenefitAdministrationResponse.getBenefitAdministrationList();
       		getSession().setAttribute("CONTRACT_QUESTONNARE_LIST",questionnaireList);
           
        }
        }
        Logger.logInfo("Returning the method for getting benefit " 
        		+ "administration values");
        // Return the list to the calling method
        return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
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
	 * @return Returns the questionListRetrieved.
	 */
	public boolean isQuestionListRetrieved() {
		return questionListRetrieved;
	}
	/**
	 * @param questionListRetrieved The questionListRetrieved to set.
	 */
	public void setQuestionListRetrieved(boolean questionListRetrieved) {
		this.questionListRetrieved = questionListRetrieved;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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
	/**
	 * @param rowNum The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @return Returns the rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}
	
	/**
	 * @return Returns the viewQuestionnaireList.
	 */
	public List getViewQuestionnaireList() {
		return viewQuestionnaireList;
	}

	/**
	 * @param viewQuestionnaireList
	 *            The viewQuestionnaireList to set.
	 */
	public void setViewQuestionnaireList(List viewQuestionnaireList) {
		this.viewQuestionnaireList = viewQuestionnaireList;
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

	public void setRecords(String records) {
		this.records = records;
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

	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest() {
		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
				.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);

		if (null != getRequest().getParameter("questionId")
				&& !("").equals(getRequest().getParameter("questionId"))) {
			this.questionId = getRequest().getParameter("questionId");
			if(StringUtil.regExPatterValidation(this.questionId))
			{
				this.questionId=this.questionId;
				this.getSession().setAttribute("questionId", questionId);
			}else{	
				this.questionId=null;
				}
			}
		if (null != getRequest().getParameter("primaryentityId")
				&& !("").equals(getRequest().getParameter("primaryentityId"))) {
			this.primaryEntityID = getRequest().getParameter("primaryentityId");
			if(StringUtil.regExPatterValidation(this.primaryEntityID))
			{
				this.primaryEntityID=this.primaryEntityID;
				this.getSession().setAttribute("primaryEntityID", primaryEntityID);
			}else{
				this.primaryEntityID=null;
			}
		}
		if (null != getRequest().getParameter("primaryEntytyType")
				&& !("").equals(getRequest().getParameter("primaryEntytyType"))) {
			this.primaryType = getRequest().getParameter("primaryEntytyType");
			if(StringUtil.regExPatterValidation(this.primaryType))
			{
				this.primaryType =this.primaryType ;
				this.getSession().setAttribute("primaryType", primaryType);
			}else{
				this.primaryType =null;
			}
		}
		if (null != getRequest().getParameter("bcId")
				&& !("").equals(getRequest().getParameter("bcId"))) {
			this.benefitComponentId = getRequest().getParameter("bcId");
			if(StringUtil.regExPatterValidation(this.benefitComponentId))
			{
				this.benefitComponentId=this.benefitComponentId;
				this.getSession().setAttribute("benefitComponentId",
					benefitComponentId);
			}else{
				this.benefitComponentId=null;
			}
		}
		if (null != getRequest().getParameter("benefitDefnId")
				&& !("").equals(getRequest().getParameter("benefitDefnId"))) {
			this.benefitDefnId = getRequest().getParameter("benefitDefnId");
			if(StringUtil.regExPatterValidation(this.benefitDefnId))
			{
				this.benefitDefnId=this.benefitDefnId;
				this.getSession().setAttribute("benefitDefnId", benefitDefnId);
			}else{
				this.benefitDefnId=null;
			}
		}
		if (null != getRequest().getParameter("adminLvlOptionId")
				&& !("").equals(getRequest().getParameter("adminLvlOptionId"))) {
			this.adminLvlOptionAssnSysId = getRequest().getParameter("adminLvlOptionId");
			if(StringUtil.regExPatterValidation(this.adminLvlOptionAssnSysId))
			{
				this.adminLvlOptionAssnSysId=this.adminLvlOptionAssnSysId;
			this.getSession().setAttribute("adminLvlOptionAssnSysId",
					adminLvlOptionAssnSysId);
			}else{
				this.adminLvlOptionAssnSysId=null;
			}
		}
		request.setPrimaryEntityID((this.getSession()
				.getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType")
				.toString());
		if (null != this.getSession().getAttribute("adminLvlOptionAssnSysId"))
			request.setSecondaryId(this.getSession().getAttribute(
					"adminLvlOptionAssnSysId").toString());
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

	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	/**
	 * This method is called when the user clicks on the save button.
	 * @return
	 */
	public String attachNotesToQuestion() {

		ContractNotesToQuestionAttachmentRequest contractNotesToQuestionAttachmentRequest = (ContractNotesToQuestionAttachmentRequest) this
				.getServiceRequest(ServiceManager.CNTRCT_NOTES_TO_QUESTION_ATTACHMENT_REQUEST);

		contractNotesToQuestionAttachmentRequest
				.setNotesToQuestionAttachmentVO(setValuesToNotesToQuestionAttachmentVO());

		ContractNotesToQuestionAttachmentResponse contractNotesToQuestionAttachmentResponse = (ContractNotesToQuestionAttachmentResponse) this
				.executeService(contractNotesToQuestionAttachmentRequest);

		List messageList = contractNotesToQuestionAttachmentResponse
				.getMessages();

		refreshQuestionNote(messageList);

		return null;
	}
	/**
	 * Method used to set values to the request
	 * @return
	 */
	private NotesToQuestionAttachmentVO setValuesToNotesToQuestionAttachmentVO() {
		NotesToQuestionAttachmentVO notesToQuestionAttachmentVO = new NotesToQuestionAttachmentVO();

		notesToQuestionAttachmentVO.setQuestionId(Integer.parseInt(this
				.getQuestionId()));
		notesToQuestionAttachmentVO.setNoteId(this.getNoteId());

		notesToQuestionAttachmentVO.setPrimaryId(Integer.parseInt(this
				.getPrimaryEntityID()));
		notesToQuestionAttachmentVO.setPrimaryEntityType("ATTACHCONTRACT");
		notesToQuestionAttachmentVO.setSecondaryId(Integer.parseInt(this
				.getAdminLvlOptionAssnSysId()));
		notesToQuestionAttachmentVO.setBenefitCompId(Integer.parseInt(this
				.getBenefitComponentId()));
		notesToQuestionAttachmentVO.setBnftDefId(Integer.parseInt(this
				.getBenefitDefnId()));
		notesToQuestionAttachmentVO.setNoteOverrideStatus("F");

		notesToQuestionAttachmentVO.setNoteVersionNumber(Integer.parseInt(this
				.getNoteVersion()));

		notesToQuestionAttachmentVO.setSecondaryEntityType("ATTACHQUESTION");

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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getPreviousNoteVersion() {
		return previousNoteVersion;
	}

	public void setPreviousNoteVersion(String previousNoteVersion) {
		this.previousNoteVersion = previousNoteVersion;
	}

	public String getPrevNoteIdSelected() {
		return prevNoteIdSelected;
	}

	public void setPrevNoteIdSelected(String prevNoteIdSelected) {
		this.prevNoteIdSelected = prevNoteIdSelected;
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

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteVersion() {
		return noteVersion;
	}

	public void setNoteVersion(String noteVersion) {
		this.noteVersion = noteVersion;
	}

	public String getNewNoteId() {
		return newNoteId;
	}

	public void setNewNoteId(String newNoteId) {
		this.newNoteId = newNoteId;
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
	 * @param headerPanelForPrint The headerPanelForPrint to set.
	 */
	public void setHeaderPanelForPrint(HtmlPanelGrid headerPanelForPrint) {
		this.headerPanelForPrint = headerPanelForPrint;
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the adminlvloptionid.
	 */
	public int getAdminlvloptionid() {
		return adminlvloptionid;
	}
	/**
	 * @param adminlvloptionid The adminlvloptionid to set.
	 */
	public void setAdminlvloptionid(int adminlvloptionid) {
		this.adminlvloptionid = adminlvloptionid;
	}
	/**
	 * @return Returns the tierList.
	 */
	public List getTierList() {
		return tierList;
	}
	/**
	 * @param tierList The tierList to set.
	 */
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	/**
	 * @return Returns the tierHeaderPanel.
	 */
	public HtmlPanelGrid getTierHeaderPanel() {
		tierHeaderPanel = new HtmlPanelGrid();
		tierHeaderPanel.setWidth("100%");
		if(!isPrintMode()){
		    tierHeaderPanel.setColumns(5);
		    tierHeaderPanel.setColumnClasses("gridColumn30,gridColumn20,gridColumn25,gridColumn15,gridColumn10");
		}else{
		    tierHeaderPanel.setColumns(4);
		    tierHeaderPanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn25,gridColumn15");
		}		
		tierHeaderPanel.setBorder(0);
		tierHeaderPanel.setCellpadding("3");
		tierHeaderPanel.setCellspacing("1");
		tierHeaderPanel.setBgcolor("#cccccc");
		tierHeaderPanel.setStyleClass("dataTableHeader");

		HtmlOutputText questionText = new HtmlOutputText();
		HtmlOutputText answerText = new HtmlOutputText();
		HtmlOutputText referenceTest = new HtmlOutputText();
		HtmlOutputText pva = new HtmlOutputText();
		HtmlOutputText noteColumn = null;
		if(!isPrintMode()){
		    noteColumn = new HtmlOutputText();
		}

		questionText.setValue("Question");
		questionText.setId("QuestionTier");

		answerText.setValue("Answer");
		answerText.setId("Answertier");

		referenceTest.setValue("Reference");
		referenceTest.setId("ReferenceTier");
		
		pva.setValue("PVA");
		pva.setId("PVATier");
		
		if(!isPrintMode()){
		    noteColumn.setValue("Note");
		    noteColumn.setId("noteIdTier");
		}

		tierHeaderPanel.getChildren().add(questionText);
		tierHeaderPanel.getChildren().add(answerText);
		tierHeaderPanel.getChildren().add(referenceTest);
		tierHeaderPanel.getChildren().add(pva);
		if(!isPrintMode()){
		    tierHeaderPanel.getChildren().add(noteColumn);
		}		
		return tierHeaderPanel;
	}
	/**
	 * @param tierHeaderPanel The tierHeaderPanel to set.
	 */
	public void setTierHeaderPanel(HtmlPanelGrid tierHeaderPanel) {
		this.tierHeaderPanel = tierHeaderPanel;
	}
	/**
	 * @return Returns the tierQuestionarePanel.
	 */
	public HtmlPanelGrid getTierQuestionarePanel() {
		return tierQuestionarePanel;
	}
	/**
	 * @param tierQuestionarePanel The tierQuestionarePanel to set.
	 */
	public void setTierQuestionarePanel(HtmlPanelGrid tierQuestionarePanel) {
		this.tierQuestionarePanel = tierQuestionarePanel;
	}
	/**
	 * @return Returns the tieredQuestionsStates.
	 */
	public String getTieredQuestionsStates() {
		return tieredQuestionsStates;
	}
	/**
	 * @param tieredQuestionsStates The tieredQuestionsStates to set.
	 */
	public void setTieredQuestionsStates(String tieredQuestionsStates) {
		this.tieredQuestionsStates = tieredQuestionsStates;
	}

	/**
	 * @return Returns the panelTierSysId.
	 */
	public String getPanelTierSysId() {
		return panelTierSysId;
	}
	/**
	 * @param panelTierSysId The panelTierSysId to set.
	 */
	public void setPanelTierSysId(String panelTierSysId) {
		this.panelTierSysId = panelTierSysId;
	}
	/**
	 * @return Returns the tildaTierNoteStatus.
	 */
	public String getTildaTierNoteStatus() {
		return tildaTierNoteStatus;
	}
	/**
	 * @param tildaTierNoteStatus The tildaTierNoteStatus to set.
	 */
	public void setTildaTierNoteStatus(String tildaTierNoteStatus) {
		this.tildaTierNoteStatus = tildaTierNoteStatus;
	}
	
   public boolean getEditMode(){
       if(isEditMode()){
           return true;
       }else{
           return false;
       }
    }

}
