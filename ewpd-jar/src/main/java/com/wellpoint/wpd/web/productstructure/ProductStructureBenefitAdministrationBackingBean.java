/*
 * ProductStructureBenefitAdministrationBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose
 * or use Confidential Information  without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;

import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.productstructure.request.AttachNotesToQuestionRequestForPS;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureBenefitAdministrationRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureQuestionnaireRequest;
import com.wellpoint.wpd.common.productstructure.request.UpdateProductStructureBenefitAdministrationRequest;
import com.wellpoint.wpd.common.productstructure.response.AttachNotesToQuestionResponseForPS;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.productstructure.vo.ProdStructNotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for benefit administration tab.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitAdministrationBackingBean extends
		ProductStructureBackingBean { 

	/**
	 * panel used for displaying title.
	 */ 
	private HtmlPanelGrid headerPanel = null;

	/**
	 * panel used for displaying administration data.
	 */
	private HtmlPanelGrid panel = null;

	/**
	 * Panel used in view page.
	 */
	private HtmlPanelGrid viewPanel = null;

	/**
	 * Map for answer id.
	 */
	private Map datafieldMap = new LinkedHashMap();

	/**
	 * Map for question id.
	 */
	private Map datafieldMapForQuestionId = new LinkedHashMap();

	/**
	 * Map for answer id.
	 */
	private Map datafieldMapForAnswerId = new LinkedHashMap();

	private Map datafieldmapForQuestionHideFlag = new LinkedHashMap();

	private Map datafieldmapForAOHideFlag = new LinkedHashMap();

	private Map datafieldMapForCstmzdId = new LinkedHashMap();

	/**
	 * List of administrion values retrieved from db.
	 */
	private List benefitAdminList;

	private List questionnaireList;
	
	private List orgQuestionnaireList;
	
	private List newQuestions             = null;
	
	private List modifiedQuestions        = null;
	
	private List removedQuestions         = null;
	
	java.util.HashMap possibleAnswerMap;

	private String loadBenefitAdminValues;

	private String adminOptionHideFlag;

	private boolean showHiddenSelected;

	private boolean answerOverrideFlag = false;

	private boolean hideStatusFlag = false;
	
	private String answerStates;

	private boolean adminListRetrievedInLoadAction = false;
	
	private int rowNum;
    
    private int answerId;
    
    private String answerDesc="";
    
    private String loadQuestionForView;
    
    private List viewQuestionnaireList;
    
    private String questionsStates;
    
    private String selectedNoteId;
    
    private String questionId;
    
    private String primaryEntityID;
	
	private String primaryType ;
	
	private String noteId;
    
    private int productStructureId;
    
    private String benefitComponentId;
    
    private String benefitDefnId;
    
    private String adminLvlOptionAssnSysId; 
    
    private String noteVersion;
    
    private String previousNoteVersion;
	
    private String noteAction;
    
    private List questionNotes;
    private String mode;
    
    private String searchString;
    
    private String records;
    
    private int i = 0;
    
    private String noteName;
		
    private String version;
    
    private String prevNoteIdSelected;
    
    private String tildaNoteStatus ;
    
    private String noteAttached;
	/**
	 * @return
	 */
	public String selectNewQuestionnreList(){  
	    
	    String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}		
		
		RetrieveProductStructureQuestionnaireRequest retrieveProductStructureQuestionnaireRequest = 
			getRetrieveProductStructureQuestionnaireRequest();
		
		
		int rowNum = this.rowNum;
		int answerId =this.answerId;
		String answerDesc = this.answerDesc;
		ProductStructureSessionObject productStructureSessionObject = 
         	(ProductStructureSessionObject) getSession().
 				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
		questionnaireList = (List)productStructureSessionObject.getQuestionareList();
		possibleAnswerMap = productStructureSessionObject.getAllPossibleAnswerMap();
		retrieveProductStructureQuestionnaireRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(questionnaireList,tildaArray);
			}
			tildaArray =null;
			this.tildaNoteStatus =null;
		
		ProductStructureQuestionnaireBO productStructureQuestionnaireBO=
		    (ProductStructureQuestionnaireBO)questionnaireList.get(rowNum);
		
		productStructureSessionObject.setAdminLevelOptionSysId
		(productStructureQuestionnaireBO.getAdminLevelOptionSysId());
		retrieveProductStructureQuestionnaireRequest.setQuestionareListIndex(rowNum);
		retrieveProductStructureQuestionnaireRequest.setProductStructureQuestionnaireBO
		(productStructureQuestionnaireBO);
		retrieveProductStructureQuestionnaireRequest.setSelectedAnswerId(answerId);	
		retrieveProductStructureQuestionnaireRequest.setSelectedAnswerDesc(answerDesc);
		retrieveProductStructureQuestionnaireRequest.setAdminOptionAssnSysId
		(productStructureQuestionnaireBO.getAdminLevelOptionSysId());		
		String benefitComponentKey = (String) getSession().getAttribute("BNFT_CMPNT_KEY");
		retrieveProductStructureQuestionnaireRequest.setBenefitComponentId(Integer.parseInt(benefitComponentKey));		
		Integer benefitDefinitionId = (Integer)getSession().getAttribute("SESSION_BNFTDEFNID");	
		retrieveProductStructureQuestionnaireRequest.setBenefitDefinitionId(benefitDefinitionId.intValue());		
		retrieveProductStructureQuestionnaireRequest.setAction
		(RetrieveProductStructureQuestionnaireRequest.LOAD_SELECTED_CHILD);
		retrieveProductStructureQuestionnaireRequest.setQuestionnareList(questionnaireList);
		RetrieveProductStructureBenefitAdministrationResponse benefitAdministrationResponse = 
        	(RetrieveProductStructureBenefitAdministrationResponse) this
                .executeService(retrieveProductStructureQuestionnaireRequest);
		if(null!=benefitAdministrationResponse){
		 this.questionnaireList = benefitAdministrationResponse.getBenefitAdministrationList();
		 if(null!=questionnaireList&& questionnaireList.size()>0){
		 	productStructureSessionObject.setQuestionareList(questionnaireList);
		 	productStructureSessionObject.setAdminLevelOptionSysId(((ProductStructureQuestionnaireBO)
		 	        questionnaireList.get(0)).getAdminLevelOptionSysId());
		 }
		 preparePanel(questionnaireList);
		}
		return "productStructureQuestionnaireEdit";
		
		
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
					((ProductStructureQuestionnaireBO) questionaireList
					.get(i)).setNotesExists("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((ProductStructureQuestionnaireBO) questionaireList
							.get(i)).setNotesExists("N");
							break;
				}
			}
		}		
	}
	
	
	/**
	 * @return
	 */
	private RetrieveProductStructureQuestionnaireRequest 
	getRetrieveProductStructureQuestionnaireRequest() {
		 // Get the admin sys id from session
         
         String benefitAdminSysId = (String) getSession().getAttribute("SESSION_BNFT_ADMIN_ID");
         
         String adminLvlOptionAssnSysId = (String) getSession().getAttribute("ADMIN_OPTION_ASSN_ID");
         
         String benefitComponentKey = (String) getSession().getAttribute("BNFT_CMPNT_KEY");
         
         String option_Id = (String) getSession().getAttribute("OPTION_ID");
         
         int adminOptionId = 0;
         if(null != option_Id && !option_Id.equals("")){
         	adminOptionId = (new Integer(option_Id)).intValue();
         }
         
         // Get the int value of the benefit admin sys id
         int beneftAdminId = 0;
         if(null != benefitAdminSysId && !benefitAdminSysId.equals("")){
         	beneftAdminId = (new Integer(benefitAdminSysId)).intValue();
         }
         
         int admnLvlOptionId = 0;
         if(null != adminLvlOptionAssnSysId && !adminLvlOptionAssnSysId.equals("")){
         	admnLvlOptionId = (new Integer(adminLvlOptionAssnSysId)).intValue();
         }
         
         int benefitComponentId = 0;
         if(null != benefitComponentKey && !benefitComponentKey.equals("")){
         	benefitComponentId = (new Integer(benefitComponentKey)).intValue();
         }
         
         
         //Get the productStructure id from the session
         ProductStructureSessionObject productStructureSessionObject = 
         	(ProductStructureSessionObject) getSession().
 				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
        
         // Get the int value of the benefit component sys id
         //int bnftComponentId = productStructureSessionObject.getBenefitComponentId();
        
         int prodStructureId = productStructureSessionObject.getId();//check friday 13
         
         int parentId = productStructureSessionObject.getParentId();
         // Get the request object from the getServiceRequest()
         RetrieveProductStructureQuestionnaireRequest retrieveProductStructureQuestionnaireRequest 
         = (RetrieveProductStructureQuestionnaireRequest) this
                 .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_STRUCTURE_QUESTIONNARE_REQUEST);

         // Set the administration id to the request
         //retrieveProductStructureQuestionnaireRequest.setAdminSysId(benefitAdminSysId);//
         
         // Set the benefit id to the request
         retrieveProductStructureQuestionnaireRequest.setBenefitAdminSysId(beneftAdminId);//

         // Set the productStructure id to the request
         retrieveProductStructureQuestionnaireRequest.setBenefitComponentId(benefitComponentId);//
         
         retrieveProductStructureQuestionnaireRequest.setProductStructureId(prodStructureId);
         
         retrieveProductStructureQuestionnaireRequest.setAdminOptionAssnSysId(admnLvlOptionId);
         retrieveProductStructureQuestionnaireRequest.setAdminSysId(adminOptionId);
         
         retrieveProductStructureQuestionnaireRequest.setParentId(parentId);

		return retrieveProductStructureQuestionnaireRequest;
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
    	 ProductStructureSessionObject productStructureSessionObject = 
        	(ProductStructureSessionObject) getSession().
				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
       
    	 String option_Id = (String) getSession().getAttribute("OPTION_ID");
         
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
     * Stores answer states.
	 * @param list
	 */
	private void storeAnswerStates(List list) {
		if(null != list && list.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator answerListIter=list.iterator();answerListIter.hasNext();){
				ProductStructureQuestionnaireBO productStructureQuestionnaireBO=
				    (ProductStructureQuestionnaireBO)answerListIter.next();
				buffer.append(productStructureQuestionnaireBO.getQuestionId());
				buffer.append("~");
				buffer.append(productStructureQuestionnaireBO.getSelectedAnswerId());
				if(answerListIter.hasNext())
					buffer.append(":");
			}
			questionsStates = buffer.toString();
		}
	}
	/**
	 * @return
	 */
	private Map loadAnswerStates(){
		Map map = new HashMap();
		if(null != questionsStates && !"".equals(questionsStates.trim())){
			String[] qstnAsnwers = questionsStates.split(":");
			for(int i=0;i<qstnAsnwers.length;i++){
				String[] qstnAnswer = qstnAsnwers[i].split("~");
				map.put(qstnAnswer[0],qstnAnswer[1]);
			}
		}
		return map;
	}

	/**
	 * Returns the benefitAdminList.
	 * 
	 * @return benefitAdminList.
	 */
	public List getBenefitAdminList() {
		return benefitAdminList;
	}

	/**
	 * @param benefitAdminList
	 *            The benefitAdminList to set.
	 */
	public void setBenefitAdminList(List benefitAdminList) {
		this.benefitAdminList = benefitAdminList;
	}

	/**
	 * Constructor.
	 */
	public ProductStructureBenefitAdministrationBackingBean() {
		super();
		this.setBreadCrumbTextForPS();
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
	 * @return Returns the datafieldMapForQuestionId.
	 */
	public Map getDatafieldMapForQuestionId() {
		return datafieldMapForQuestionId;
	}

	/**
	 * @param datafieldMapForQuestionId
	 *            The datafieldMapForQuestionId to set.
	 */
	public void setDatafieldMapForQuestionId(Map datafieldMapForQuestionId) {
		this.datafieldMapForQuestionId = datafieldMapForQuestionId;
	}

	/**
	 * Returns the datafieldMap.
	 * 
	 * @return Map datafieldMap.
	 *  
	 */
	public Map getDatafieldMap() {
		return datafieldMap;
	}

	/**
	 * Sets the datafieldMap.
	 * 
	 * @param datafieldMap.
	 *  
	 */
	public void setDatafieldMap(Map datafieldMap) {
		this.datafieldMap = datafieldMap;
	}

	/**
	 * Returns the headerPanel.
	 * 
	 * @return HtmlPanelGrid headerPanel.
	 */
	 public HtmlPanelGrid getHeaderPanel() {
    	
        headerPanel = new HtmlPanelGrid();
        headerPanel.setWidth("100%");
        //headerPanel.setColumns(2);
        headerPanel.setColumns(4);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");
        headerPanel.setStyleClass("dataTableHeader");

        HtmlOutputText otxtType1 = new HtmlOutputText();
        HtmlOutputText otxtType2 = new HtmlOutputText();
        HtmlOutputText otxtType3 = new HtmlOutputText();
        HtmlOutputText otxtType4 = new HtmlOutputText();
        
	   
        otxtType1.setValue("Question");    
        otxtType1.setId("Question");

        otxtType2.setValue("Answer");
        otxtType2.setId("Answer");
        
        otxtType3.setValue("Reference");
        otxtType3.setId("Reference");
        
        otxtType4.setValue("Notes");
        otxtType4.setId("Notes");      
        
       	headerPanel.getChildren().add(otxtType1);
        headerPanel.getChildren().add(otxtType2);
        headerPanel.getChildren().add(otxtType3);
        headerPanel.getChildren().add(otxtType4);
             
        return headerPanel;
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
	 * Returns the panel.
	 * 
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanel() {

		return panel;
	}
	/**
     * @return Returns the panel.
     */
    public void preparePanel(List questionnareList){
    	StringBuffer line =null;
    	String finalline = null;
    	String adminLevelOptionId=null;
    	String benefitDefnIdfromSession =null;
    	panel = new HtmlPanelGrid();
    	panel.setWidth("100%");
    	panel.setColumns(4);	
    	panel.setBorder(0);
    	panel.setStyleClass("outputText");
    	panel.setCellpadding("3");
    	panel.setCellspacing("1");
    	panel.setBgcolor("#cccccc");
        StringBuffer rowClass = new StringBuffer();
        if (questionnareList != null) {
            HtmlSelectOneMenu answerSelectOneMenu = null;
            HtmlOutputText questionOutputText = null;
            HtmlOutputText referenceOutputText = null;
            HtmlInputHidden childCountHidden = null;
            HtmlPanelGroup referenceGroup = null;
            HtmlOutputText notesHidden = null;
            
            HtmlGraphicImage notesAttachmentImage = null;
            ProductStructureSessionObject productStructureSessionObject = 
             	(ProductStructureSessionObject) getSession().
     				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
            int prodStructureId = productStructureSessionObject.getId();
            if(this.questionsStates==null ||"".equalsIgnoreCase(this.questionsStates))
            	storeAnswerStates(questionnareList);

            for (int i = 0; i < questionnareList.size(); i++) {
            	ProductStructureQuestionnaireBO productStructureQuestionnaireBO=
            	    (ProductStructureQuestionnaireBO)questionnareList.get(i);
            	notesAttachmentImage = new HtmlGraphicImage();   
            	questionOutputText = new HtmlOutputText();
            	referenceOutputText = new HtmlOutputText();
            	notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i);            	
            	childCountHidden =new HtmlInputHidden();
            	referenceGroup = new HtmlPanelGroup();
               	// 	start notes
				
				int benefitId = Integer.valueOf((String)getSession().
				        getAttribute(WebConstants.STANDARD_BNFT_KEY)).intValue();
				
				String primaryType= "ATTACHPRODSTRCT";
							
				this.benefitDefnId=String.valueOf(productStructureQuestionnaireBO.getBenefitDefinitionId());
				String benefitComponentKey = (String) getSession().getAttribute("BNFT_CMPNT_KEY");
				if(i==0){
					if(null!=this.benefitDefnId){
					getSession().setAttribute("SESSION_BNFTDEFNID",new Integer(benefitDefnId));
					}
					int adminlevelid = productStructureQuestionnaireBO.getAdminLevelOptionSysId();
					if(adminlevelid!=0)
					getSession().setAttribute("adminLvlOptionIDToSession",new Integer(adminlevelid));
				}
				if(null!=getSession().getAttribute("SESSION_BNFTDEFNID").toString()){
				benefitDefnIdfromSession = getSession().getAttribute("SESSION_BNFTDEFNID").toString();
			//	productStructureQuestionnaireBO.setBenefitDefinitionId(benefitDefnIdfromSession);
				}
				if(null!=getSession().getAttribute("adminLvlOptionIDToSession").toString()){
				adminLevelOptionId = getSession().getAttribute("adminLvlOptionIDToSession").toString();
				productStructureQuestionnaireBO.setAdminLevelOptionSysId(Integer.parseInt(adminLevelOptionId));
				}
				notesAttachmentImage.setStyle("cursor:hand;");
				notesAttachmentImage.setId("notesAttachmentImage"+ i);			
                notesAttachmentImage.setOnclick("loadNotesPopup('../popups/prodStructQuestionNotesPopup.jsp','"
                                                    + productStructureQuestionnaireBO.getQuestionId()
                                                    + "','"
                                                    + prodStructureId
                                                    + "','"
                                                    + primaryType
                                                    + "','"
													+Integer.parseInt(benefitComponentKey)
													+ "','"
													+ benefitDefnIdfromSession 
													+ "','"
													+adminLevelOptionId
													+"','"
													+"notesAttachmentImage" +i+"','"+i
            										 +"');return false;");                            
                                            
				//end of notes         
            	
            	if(i>0){
            		rowClass.append(",");	
            	}
            	int level= productStructureQuestionnaireBO.getLevel();
            	if(level>1){
            	finalline = getLevelPrefix(level);
            	questionOutputText.setValue(finalline+productStructureQuestionnaireBO.getQuestionName());
            	rowClass.append("dataTableOddRow");
            	}else{
            		questionOutputText.setValue(productStructureQuestionnaireBO.getQuestionName());
            		rowClass.append("dataTableEvenRow");
            	}
            	 childCountHidden.setId("childCount"+i);
            	 childCountHidden.setValue(new Integer(productStructureQuestionnaireBO.getChildCount()));
            	 List answerList =  productStructureQuestionnaireBO.getPossibleAnswerList();
            	 List possibleAnswersList = (List)getPossibleAnswersListForAQuestion(answerList);
            	 
            	 answerSelectOneMenu = new HtmlSelectOneMenu();
            	 answerSelectOneMenu.setId("selectitem" + i);
                 UISelectItems uis = new UISelectItems();
                 uis.setValue(possibleAnswersList);

                 answerSelectOneMenu.setValue(new Integer(
                 		productStructureQuestionnaireBO.getSelectedAnswerId()).toString());
                 answerSelectOneMenu.setStyleClass("formInputList");
                 answerSelectOneMenu
                         .setStyleClass("formInputFieldBenefitStructure");
                 answerSelectOneMenu.getChildren().add(uis);
                 answerSelectOneMenu.setOnclick("storeOldValue(this);return false;");
                 answerSelectOneMenu.setOnchange("return loadNewChild(this)");
                           
                 referenceOutputText.setId("refere"+i);
                 referenceOutputText.setValue(productStructureQuestionnaireBO.getReferenceDesc());
                 referenceGroup.getChildren().add(referenceOutputText);
                 referenceGroup.getChildren().add(childCountHidden);
                 
                 
                 if("Y".equals(productStructureQuestionnaireBO.getNotesExists())){   
    				    notesAttachmentImage.setUrl("../../images/notes_exist.gif");
          	     }else{          	     
          	        notesAttachmentImage.setUrl("../../images/page.gif");
          	     }
                 
                 panel.getChildren().add(questionOutputText);
                 panel.getChildren().add(answerSelectOneMenu); 
            	 panel.getChildren().add(referenceGroup);
            	 //panel.getChildren().add(notesAttachmentImage); 
            	 
            	 if("Y".equals(productStructureQuestionnaireBO.getValidDomainToAttach())){             	   
             	     panel.getChildren().add(notesAttachmentImage);               	   
             	 }else{
             	    panel.getChildren().add(notesHidden);  
             	 }    	        	
            }
            
        }
        panel.setRowClasses(rowClass.toString());    	
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
	
	private Map loadAdminState(){
		Map map = new HashMap();
		if(null != questionsStates && !"".equals(questionsStates.trim())){
			String[] admins = questionsStates.split(":");
			for(int i=0;i< admins.length;i++){
				String[] values =  admins[i].split("~");
				map.put(new Integer(values[0]), new Object[]{values[0], values[1]});
			}
		}
		return map;
	}

	/**
	 * To go to the ProduStructure BenefitAdministration page when navigating
	 * through the tree.
	 * 
	 * @return String forward String
	 */
	public String displayProductStructureBenefitAdministration() {
		
		if(getActionFromSession().equals("VIEW")) {
    	return "viewBenefitAdmn"; 
    		
    	} else {
    		RetrieveProductStructureQuestionnaireRequest retrieveProductStructureQuestionnaireRequest = 
    			getRetrieveProductStructureQuestionnaireRequest();
    		
    		//Getting the Map containing questionNumbers & possible answer lists.
    		possibleAnswerMap = getAllPossibleAnswersForAdminOption(); 
    		retrieveProductStructureQuestionnaireRequest.setAllPossibleAnswerMap(possibleAnswerMap);
    		
    		retrieveProductStructureQuestionnaireRequest.setAction
    		(RetrieveProductStructureQuestionnaireRequest.LOAD_QUESTIONNARE_LIST);
            // Call the executeService() to get the response
    		RetrieveProductStructureBenefitAdministrationResponse benefitAdministrationResponse = 
            	(RetrieveProductStructureBenefitAdministrationResponse) this
                    .executeService(retrieveProductStructureQuestionnaireRequest);
            if(null!=benefitAdministrationResponse){
    		questionnaireList = (null != benefitAdministrationResponse.
    		getBenefitAdministrationList() ? benefitAdministrationResponse.
    	    		getBenefitAdministrationList() : new ArrayList());
            }
		
		// Get the list of benefit administration from the database
		this.benefitAdminList = questionnaireList;
		ProductStructureSessionObject productStructureSessionObject = 
         	(ProductStructureSessionObject) getSession().
 				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
		
		productStructureSessionObject.setAllPossibleAnswerMap(possibleAnswerMap);
		
		if(null!=benefitAdminList&&benefitAdminList.size()>0){
			productStructureSessionObject.setQuestionareList(benefitAdminList);
			productStructureSessionObject.setAdminLevelOptionSysId(((ProductStructureQuestionnaireBO)
			        benefitAdminList.get(0)).getAdminLevelOptionSysId());
    		}
		//	Creating original questionnaire list and setting in session
    	orgQuestionnaireList = new ArrayList();
    	Iterator it = questionnaireList.iterator();
    	while(it.hasNext()){
    		ProductStructureQuestionnaireBO PsQueBO = (ProductStructureQuestionnaireBO)it.next();
    		ProductStructureQuestionnaireBO orgPsQueBO = new ProductStructureQuestionnaireBO();
    		
    		orgPsQueBO.setQuestionnaireId( PsQueBO.getQuestionnaireId()  );
    		orgPsQueBO.setQuestionId( PsQueBO.getQuestionId()  );
    		orgPsQueBO.setParentQuestionnaireId( PsQueBO.getParentQuestionnaireId()  );
    		orgPsQueBO.setSelectedAnswerId( PsQueBO.getSelectedAnswerId()  );
    		orgPsQueBO.setSelectedAnswerDesc(  PsQueBO.getSelectedAnswerDesc()  );
    		orgPsQueBO.setAdminLevelOptionSysId( PsQueBO.getAdminLevelOptionSysId()  );
    		orgPsQueBO.setProductStructureId( PsQueBO.getProductStructureId()  );
    		orgPsQueBO.setBenefitComponentId( PsQueBO.getBenefitComponentId()  );
    		orgPsQueBO.setBenefitDefinitionId( PsQueBO.getBenefitDefinitionId()  );
    		orgPsQueBO.setChildCount( PsQueBO.getChildCount()  );
    		orgPsQueBO.setLevel( PsQueBO.getLevel()  );
    		orgPsQueBO.setNotesExists( PsQueBO.getNotesExists() );
    		orgPsQueBO.setPossibleAnswerList( PsQueBO.getPossibleAnswerList() );
    		orgPsQueBO.setQuestionOrder( PsQueBO.getQuestionOrder() );
    		orgPsQueBO.setQuestionPVA( PsQueBO.getQuestionPVA() );
    		orgPsQueBO.setReferenceId( PsQueBO.getReferenceId() );
    		orgPsQueBO.setSequenceNumber( PsQueBO.getSequenceNumber() );
    		orgPsQueBO.setTierSysId( PsQueBO.getTierSysId() );
    		
    		orgQuestionnaireList.add(orgPsQueBO); 
    	}	 
    	// remove not answered questions from list, before setting to session.
    	productStructureSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
    	
		this.questionsStates=null;
		
		preparePanel(benefitAdminList);
		
		//this.benefitAdminValueList = listToDisplay(this.benefitAdminList);
		return "productStructureQuestionnaireEdit";
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
    
	/**
	 * To get the list of benefit administration values from the db.
	 * 
	 * @return List benefitAdministrationList
	 */
	private List getBenefitAdministrationValues() {
		Logger.logInfo("Entering the method for getting benefit "
				+ "administration values");
		// Get the request object from the getServiceRequest()
		RetrieveProductStructureBenefitAdministrationRequest benefitAdministrationRequest 
		= getRetrieveBenefitAdministrationRequest();

		// Call the executeService() to get the response
		RetrieveProductStructureBenefitAdministrationResponse benefitAdministrationResponse = 
		    (RetrieveProductStructureBenefitAdministrationResponse) this
				.executeService(benefitAdministrationRequest);

		List benefitAdministrationList = null;

		if (null != benefitAdministrationResponse
				.getBenefitAdministrationList()
				&& benefitAdministrationResponse.getBenefitAdministrationList()
						.size() != 0) {
			// Get the list of benefit administration values from the response
			benefitAdministrationList = benefitAdministrationResponse
					.getBenefitAdministrationList();

			// Get the action from session
			String action = getActionFromSession();

			// Check whether the action in session is print
			if (action.equals("PRINT")) {
				// Iterate through the admin list to set the answerDescription
				//to BO for Print
				for (int i = 0; i < benefitAdministrationList.size(); i++) {
					// Get the individual BO
					EntityBenefitAdministration administration = 
					    (EntityBenefitAdministration) benefitAdministrationList
							.get(i);
					// Get the answerList from the BO
					List answers = administration.getAnswers();

					// Call the method to get the required answer desc
					String answerDescription = getSelectedAnswerDescFromPossibleAnswersList(
							answers, administration.getAnswerId());

					// Set the answer desc to the BO property
					administration.setAnswerDesc(answerDescription);
				}
			}
		}
		Logger.logInfo("Returning the method for getting benefit "
				+ "administration values");
		// Return the list to the calling method
		return benefitAdministrationList;
	}

	/**
	 * method to populate list according to the checkbox
	 * 
	 * @param benefitAdministrationList
	 * @return
	 */
	public List listToDisplay(List benefitAdministrationList) {
		//if(!getProductStructureSessionObject().isQuestionHiddenFlag()){
		if (!isShowHiddenSelected()) {
			List listWithoutHiddenQuestions = new ArrayList
			(benefitAdministrationList == null ? 0:benefitAdministrationList.size());
			EntityBenefitAdministration entityBenefitAdministration = new EntityBenefitAdministration();
			for (int i = 0; i < benefitAdministrationList.size(); i++) {
				entityBenefitAdministration = (EntityBenefitAdministration) 
				benefitAdministrationList
						.get(i);
				//if the question is not hidden then add it to another list
				if (entityBenefitAdministration.getQstnHideFlag().equals("F")) {
					listWithoutHiddenQuestions.add(entityBenefitAdministration);
				}
			}
			return listWithoutHiddenQuestions;
		} else {
			return benefitAdministrationList;
		}
	}

	/**
	 * To set the benefit administration values to list.
	 * 
	 * @return
	 */
	public String getPrintBenefitAdministrationValues() {
		int currentAction = 0;
		if (null != getActionFromSession()) {
			if (getActionFromSession().equals("VIEW")) {
				currentAction = 1;
			} else {
				currentAction = 0;
			}
		}
		setActionToSession("PRINT");
		benefitAdminList = listToDisplay(getBenefitAdministrationValues());
		if (currentAction == 1) {
			setActionToSession("VIEW");
		}
		return "";
	}

	/**
	 * Setter method for benefit administration values.
	 *  
	 */
	public void setPrintBenefitAdministrationValues() {

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
		List answerDescriptionList = new ArrayList(answersList == null ? 0:answersList.size());

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
		Logger.logInfo("Returning the method for getting possible answer "
				+ "list for a question");
		// Return the list
		return answerDescriptionList;

	}

	/**
	 * To get the request object for retrieving the benefit administration list
	 * after setting all the values in it.
	 * 
	 * @return RetrieveProductStructureBenefitAdministrationRequest
	 */
	private RetrieveProductStructureBenefitAdministrationRequest 
	getRetrieveBenefitAdministrationRequest() {
		// Create the session object
		HttpSession httpSession = getSession();

		// Get the admin sys id from session
		String adminSysId = (String) httpSession.getAttribute("OPTION_ID");

		// Get the int value of the admin sys id
		int adminId = new Integer(adminSysId).intValue();

		// Get the benefit id from session
		String benefitAminSysId = (String) httpSession.getAttribute("ADMIN_ID");

		// Get the int value of the benefit admin sys id
		int beneftAdminId = (new Integer(benefitAminSysId)).intValue();

		// Get the benefit id from session
		String benefitComponentId = (String) httpSession
				.getAttribute("BNFT_CMPNT_KEY");

		// Get the int value of the benefit admin sys id
		int beneftCompId = (new Integer(benefitComponentId)).intValue();

		// Create an instance of productStructureVO
		ProductStructureVO productStructureVO = new ProductStructureVO();

		// Get the productStructureVO
		productStructureVO = this
				.getProductStructureFromSession(productStructureVO);

		// Get the request object from the getServiceRequest()
		RetrieveProductStructureBenefitAdministrationRequest 
		benefitAdministrationRequest 
		= (RetrieveProductStructureBenefitAdministrationRequest) this
				.getServiceRequest(ServiceManager.
				        RETRIEVE_PRODUCT_STUCTURE_BENEFIT_ADMINISTRATION);

		// Set the administration id to the request
		benefitAdministrationRequest.setAdminSysId(adminId);

		// Set the benefit id to the request
		benefitAdministrationRequest.setBenefitAdminSysId(beneftAdminId);

		// Set the benefitComponentId to the request
		benefitAdministrationRequest.setBenefitComponentId(beneftCompId);

		// Set the productStructure id to the request
		benefitAdministrationRequest.setProductStructureVO(productStructureVO);

		// Return the request object
		return benefitAdministrationRequest;
	}

	/**
	 * save benefit administration for product structure.
	 */
	 public String saveBenefitAdministration() {
        
	 	// Create the request object from the getServiceRequest()
	 	String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		
       UpdateProductStructureBenefitAdministrationRequest administrationRequest = 
           (UpdateProductStructureBenefitAdministrationRequest) this
               .getServiceRequest(ServiceManager.UPDATE_PRODUCT_STRUCTURE_BENEFIT_ADMINISTRATION);
       ProductStructureSessionObject productStructureSessionObject = 
           (ProductStructureSessionObject) getSession()
							.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);
       
       questionnaireList = (List)productStructureSessionObject.getQuestionareList();
       orgQuestionnaireList = (List)productStructureSessionObject.getOrgQuestionnaireList(); 
       
       this.filterQuestionsForUpdate();
       
       if(null!=tildaArray && tildaArray.length>0){
       	processQuestionaireList(questionnaireList,tildaArray);
       }
       productStructureSessionObject.setQuestionareList(questionnaireList);
       tildaArray =null;
       this.tildaNoteStatus =null;	
       
        int productStructureId = productStructureSessionObject.getId();   
        administrationRequest.setQuestionnareList(productStructureSessionObject.
                getQuestionareList());
        administrationRequest.setOrgQuestionnareList(orgQuestionnaireList);
        administrationRequest.setEntityId(productStructureId);
        int benefitComponentId = Integer.valueOf((String)getSession().
                getAttribute(WebConstants.BNFT_CMPNT_KEY)).intValue();
        int benefitId = Integer.valueOf((String)getSession().
                getAttribute(WebConstants.STANDARD_BNFT_KEY)).intValue();
        // Get the benefit id from session
		String benefitAminSysId = (String) getSession().getAttribute(WebConstants.ADMN_ID);
		// Get the int value of the benefit admin sys id
		int beneftAdminId = (Integer.valueOf(benefitAminSysId)).intValue();
		administrationRequest.setBenefitAdminSysId(beneftAdminId);
        administrationRequest.setBenefitComponentId(benefitComponentId);
        administrationRequest.setBenefitId(benefitId);
        administrationRequest.setAdminlevelOptionSysId(productStructureSessionObject.
                getAdminLevelOptionSysId());
        administrationRequest.setMainObjectIdentifier(productStructureSessionObject.getName());
        administrationRequest.setVersionNumber(productStructureSessionObject.getVersion());
        administrationRequest.setDomainList(productStructureSessionObject.getBusinessDomains());
        administrationRequest.setQuestionnaireListToAdd(this.newQuestions);
        administrationRequest.setQuestionnaireListToUpdate(this.modifiedQuestions);
        administrationRequest.setQuestionnaireListToRemove(this.removedQuestions);
        

        updateBenefitAdministrationForPS(administrationRequest);
        
        SaveBenefitAdministrationResponse response = 
        	(SaveBenefitAdministrationResponse)this.executeService(administrationRequest);
        if(administrationRequest.getQuestionnareList()!=null)
        storeAnswerStates(administrationRequest.getQuestionnareList());
        if(null!=response){
        	getRequest().setAttribute("RETAIN_Value","");
        	
        	preparePanel(questionnaireList);
        	
        	orgQuestionnaireList = new ArrayList();
        	Iterator it = questionnaireList.iterator();
        	while(it.hasNext()){
        		ProductStructureQuestionnaireBO PsQueBO = (ProductStructureQuestionnaireBO)it.next();
        		ProductStructureQuestionnaireBO orgPsQueBO = new ProductStructureQuestionnaireBO();
        		
        		orgPsQueBO.setQuestionnaireId( PsQueBO.getQuestionnaireId()  );
        		orgPsQueBO.setQuestionId( PsQueBO.getQuestionId()  );
        		orgPsQueBO.setParentQuestionnaireId( PsQueBO.getParentQuestionnaireId()  );
        		orgPsQueBO.setSelectedAnswerId( PsQueBO.getSelectedAnswerId()  );
        		orgPsQueBO.setSelectedAnswerDesc(  PsQueBO.getSelectedAnswerDesc()  );
        		orgPsQueBO.setAdminLevelOptionSysId( PsQueBO.getAdminLevelOptionSysId()  );
        		orgPsQueBO.setProductStructureId( PsQueBO.getProductStructureId()  );
        		orgPsQueBO.setBenefitComponentId( PsQueBO.getBenefitComponentId()  );
        		orgPsQueBO.setBenefitDefinitionId( PsQueBO.getBenefitDefinitionId()  );
        		orgPsQueBO.setChildCount( PsQueBO.getChildCount()  );
        		orgPsQueBO.setLevel( PsQueBO.getLevel()  );
        		orgPsQueBO.setNotesExists( PsQueBO.getNotesExists() );
        		orgPsQueBO.setPossibleAnswerList( PsQueBO.getPossibleAnswerList() );
        		orgPsQueBO.setQuestionOrder( PsQueBO.getQuestionOrder() );
        		orgPsQueBO.setQuestionPVA( PsQueBO.getQuestionPVA() );
        		orgPsQueBO.setReferenceId( PsQueBO.getReferenceId() );
        		orgPsQueBO.setSequenceNumber( PsQueBO.getSequenceNumber() );
        		orgPsQueBO.setTierSysId( PsQueBO.getTierSysId() );
        		
        		orgQuestionnaireList.add(orgPsQueBO); 
        	}	 
        	// remove not answered questions from list, before setting to session.
        	productStructureSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	       	
        	return "productStructureQuestionnaireEdit";
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
	    	
	    	ProductStructureSessionObject productStructureSessionObject = 
			    (ProductStructureSessionObject) getSession()
					.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);
	    	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
	    	questionnaireList = (List)productStructureSessionObject.getQuestionareList();	      
		    orgQuestionnaireList = (List)productStructureSessionObject.getOrgQuestionnaireList(); 
		    
		    newQuestions = new ArrayList(); 
		    modifiedQuestions = new ArrayList();
		    removedQuestions = new ArrayList();
		    
		    Iterator it = questionnaireList.iterator();
		    while(it.hasNext()){
		    	ProductStructureQuestionnaireBO psQuestionnaireBO = (ProductStructureQuestionnaireBO)it.next();
			    	if(!"Not Answered".equals(psQuestionnaireBO.getSelectedAnswerDesc()) ){
			    	int a=0;
			    	
			    	Iterator it1 = orgQuestionnaireList.iterator();
			    	while(it1.hasNext()){
			    		ProductStructureQuestionnaireBO psQuestionnaireBO1 = 
				    		(ProductStructureQuestionnaireBO)it1.next(); 
			    		    		if(psQuestionnaireBO.getQuestionnaireId() == psQuestionnaireBO1.getQuestionnaireId()){
							    		a++; 
							    		it1.remove();
							    		if(psQuestionnaireBO.getSelectedAnswerId() != psQuestionnaireBO1.getSelectedAnswerId()){
								    		modifiedQuestions.add(psQuestionnaireBO);
								    	}
						    		} 
			    	}
			    	
			    	if(a == 0){ 
			    		newQuestions.add(psQuestionnaireBO);
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
	    			if( "Not Answered".equals( ((ProductStructureQuestionnaireBO)it.next()).getSelectedAnswerDesc().trim() )  ){
			    		it.remove();
			    	} 
	    		}
	    		
	    	}
	    	return questionnaireList;
	    }
	/**
	 * updates administration for product structure.
	 * @param administrationRequest
	 */
	private void updateBenefitAdministrationForPS(UpdateProductStructureBenefitAdministrationRequest 
	        administrationRequest) {		
		if(null != administrationRequest &&
				administrationRequest.getQuestionnareList().size() > 0){
			Map map = loadAdminState();
			boolean qstnsChanged = false;
			List changeIds = null;
			List quesList = administrationRequest.getQuestionnareList();
			int listSize = quesList.size();
			changeIds = (null!=quesList)?(new ArrayList(listSize)):(new ArrayList());
			Iterator iterator = quesList.iterator();
			while(iterator.hasNext()){
				ProductStructureQuestionnaireBO entityBO = 
				    (ProductStructureQuestionnaireBO)iterator.next();
				String qstnKey = entityBO.getQuestionId() + "";
				String[] values = (String[])map.get(qstnKey);
				if(values != null){
					
					if(!values[0].equals(entityBO.getSelectedAnswerId()+"")){
						qstnsChanged = true;
						changeIds.add(qstnKey);
					}
				}else{
					qstnsChanged = true;
					changeIds.add(qstnKey);
				}
			}
			if(map!=null){
				Set set=map.keySet();
				Iterator iter=set.iterator();
				while(iter.hasNext()){
					boolean contains=false;
					String queskey=""+iter.next();
					if(quesList!=null&& quesList.size()>0){
						for(int i=0;i<quesList.size()&&!contains;i++){
							ProductStructureQuestionnaireBO entityBO = 
							    (ProductStructureQuestionnaireBO)quesList.get(i);
							if((queskey.equalsIgnoreCase(entityBO.getQuestionId() + "")))
								contains=true;	
						}		
					}
					if(!contains){
						qstnsChanged = true;
						changeIds.add(queskey);
					}					
				}
			}
			
			administrationRequest.setChanged(qstnsChanged);
			administrationRequest.setChangedIds(changeIds);
			administrationRequest.setBCompName((String)getSession().
			        getAttribute("BENEFIT_COMP_NAME"));
		}		
		
	}

	/**
	 * 
	 *  
	 */
	private List listToUpdate(List benefitAdminValueList,
			List benefitAdministrationList) {

		List listToUpdate = new ArrayList(benefitAdministrationList == null ? 0: 
		    benefitAdministrationList.size());
		boolean answerOverRideFlag = false;
		boolean questionHideFlag = false;

		Iterator dbListIter = benefitAdminValueList.iterator();
		Iterator pageListIter = benefitAdministrationList.iterator();

		EntityBenefitAdministration entityBenefitAdministration = null;
		BenefitAdministrationOverrideVO voObject = null;

		while (dbListIter.hasNext()) {

			entityBenefitAdministration = (EntityBenefitAdministration) dbListIter
					.next();

			while (pageListIter.hasNext()) {

				voObject = (BenefitAdministrationOverrideVO) pageListIter
						.next();

				if (entityBenefitAdministration.getAnsOvrdCustmzdSysId() == voObject
						.getAnswerOvrdCstmzdId()) {

					if ((entityBenefitAdministration.getAnswerId() != voObject
							.getAnswerId())) {
						answerOverRideFlag = true;
						this.setAnswerOverrideFlag(true);
						//listToUpdate.add(voObject);
					}
					if (!entityBenefitAdministration.getQstnHideFlag().equals(
							voObject.getQuestionHideFlag())) {
						questionHideFlag = true;
						this.setHideStatusFlag(true);

					}
					
					if(answerOverRideFlag || questionHideFlag){
						answerOverRideFlag = false;
						questionHideFlag = false;
						listToUpdate.add(voObject);
					}
				}
			}

			pageListIter = benefitAdministrationList.iterator();
		}

		return listToUpdate;
	}

	/**
	 * To get the list of over ridden values by taking it from the hidden.
	 * hashMaps return List
	 */
	private List getBenefitAdministrationOverriddenList() {

		// Create a list
		List benefitAdministrationList = new ArrayList(datafieldMapForQuestionId == null ? 0: 
		    datafieldMapForQuestionId.size());

		// Get the question id from the hidden key set of the HashMap
		Set idSet = datafieldMapForQuestionId.keySet();

		// Create the iterator for the questionId
		Iterator questionIdIter = idSet.iterator();

		// Get the answers key set from the HashMap
		Set answerSet = datafieldMapForAnswerId.keySet();

		// Create the iterator for the answerSet
		Iterator answerIterator = answerSet.iterator();

		// Get the ProductStructure id from the session
		int productStructureId = this.getIdFromSession();

		// Iterate through the HaspMap and get the individual question ,
		// answer and ShowHidden values and set them to the list
		while (questionIdIter.hasNext() && answerIterator.hasNext()) {

			// Get the long value
			Long iterationId = (Long) questionIdIter.next();

			// Create an instance of the VO
			BenefitAdministrationOverrideVO administrationOverrideVO = 
			    new BenefitAdministrationOverrideVO();

			String cstmzdId = (String) datafieldMapForCstmzdId.get(iterationId);

			administrationOverrideVO
					.setAnswerOvrdCstmzdId(new Integer(cstmzdId).intValue());

			// Get the value of id form the map
			String questionId = (String) datafieldMapForQuestionId
					.get(iterationId);

			// Set the value of the question id
			administrationOverrideVO.setQuestionId((new Integer(questionId))
					.intValue());

			// Get the value of id form the map
			//String answerId = (String)
			// datafieldMapForAnswerId.get(iterationId);

			// Set the productStructureId
			administrationOverrideVO.setEntityId(productStructureId);

			// Set the value of the question id
			administrationOverrideVO.setAnswerId(new Integer(
					((String) datafieldMap.get(iterationId))).intValue());

			//enhancement
			String hiddenFlag = "F";
			ProductStructureSessionObject productStructureSessionObject = 
			    (ProductStructureSessionObject) getSession()
					.getAttribute(PRODUCT_STRUCTURE_SESSION_KEY);
			String benefitComName = (String) getSession().getAttribute(
					"BENEFIT_COMP_NAME");
			if (!productStructureSessionObject.getStructureType()
					.equalsIgnoreCase("MANDATE")) {
				Boolean hideStatus = (Boolean) datafieldmapForQuestionHideFlag
						.get(iterationId);
				if ("true".equalsIgnoreCase(hideStatus.toString())) {
					hiddenFlag = "T";
				} else {
					hiddenFlag = "F";
				}
			}

			// Set the value of the question hide flag
			administrationOverrideVO.setQuestionHideFlag(hiddenFlag);

			String adminOptionHideFlagVal = (String) datafieldmapForAOHideFlag
					.get(iterationId);
			administrationOverrideVO
					.setAdminOptionHideFlag(adminOptionHideFlagVal);

			// Add the VO to the list
			benefitAdministrationList.add(administrationOverrideVO);
		}

		// Return the list
		return benefitAdministrationList;

	}

	/**
	 * Sets the panel.
	 * 
	 * @param panel.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * @param items
	 * @return String
	 */
	private String getSelectedAnswerDescFromPossibleAnswersList(List items,
			int answerId) {
		// Iterate through the answers list
		for (int i = 0; i < items.size(); i++) {

			// Get individual AnswerBO from the list
			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) items.get(i);
			// Get the answer id from the list
			int individualAnswerId = possibleAnswerBO.getPossibleAnswerId();

			// Get the answer description from the list
			String individualAnswerDesc = possibleAnswerBO
					.getPossibleAnswerDesc();

			// Check if the answer id matches the required answer id
			if (answerId == individualAnswerId) {

				// Return the answer description
				return individualAnswerDesc;
			}
		}
		return null;
	}

	public String loadBenefitAdmins() {
		adminListRetrievedInLoadAction = true;
		getRequest().setAttribute("RETAIN_Value", "");
		return getLoadBenefitAdminValues();
	}
	/**
	 * Sets the viewPanel.
	 * 
	 * @param viewPanel.
	 */

	public void setViewPanel(HtmlPanelGrid viewPanel) {
		this.viewPanel = viewPanel;
	}

	/**
	 * For loading benefit administration page.
	 * 
	 * @return String.
	 */
	public String load() {
		return "benefitAdminPage";
	}

	/**
	 * For loading benefit administration view page.
	 * 
	 * @return String
	 */
	public String loadForView() {
		return "benefitAdminViewPage";
	}
	

	/**
	 * @return Returns the loadBenefitAdminValues.
	 */
	public String getLoadBenefitAdminValues() {
	    //if(!adminListRetrievedInLoadAction) {
		//	getProductStructureSessionObject().setQuestionHiddenFlag(isShowHiddenSelected());
		//	displayProductStructureBenefitAdministration();
	    //	}
		return "loadBenefitAdminValues";
	}

	/**
	 * @param loadBenefitAdminValues
	 *            The loadBenefitAdminValues to set.
	 */
	public void setLoadBenefitAdminValues(String loadBenefitAdminValues) {
		this.loadBenefitAdminValues = loadBenefitAdminValues;
	}

	/**
	 * @return Returns the datafieldmapForQuestionHideFlag.
	 */
	public Map getDatafieldmapForQuestionHideFlag() {
		return datafieldmapForQuestionHideFlag;
	}

	/**
	 * @param datafieldmapForQuestionHideFlag
	 *            The datafieldmapForQuestionHideFlag to set.
	 */
	public void setDatafieldmapForQuestionHideFlag(
			Map datafieldmapForQuestionHideFlag) {
		this.datafieldmapForQuestionHideFlag = datafieldmapForQuestionHideFlag;
	}

	/**
	 * @return Returns the showHiddenSelected.
	 */
	public boolean isShowHiddenSelected() {
		return showHiddenSelected;
	}

	/**
	 * @param showHiddenSelected
	 *            The showHiddenSelected to set.
	 */
	public void setShowHiddenSelected(boolean showHiddenSelected) {
		this.showHiddenSelected = showHiddenSelected;
	}

	/**
	 * @return Returns the datafieldmapForAOHideFlag.
	 */
	public Map getDatafieldmapForAOHideFlag() {
		return datafieldmapForAOHideFlag;
	}

	/**
	 * @param datafieldmapForAOHideFlag
	 *            The datafieldmapForAOHideFlag to set.
	 */
	public void setDatafieldmapForAOHideFlag(Map datafieldmapForAOHideFlag) {
		this.datafieldmapForAOHideFlag = datafieldmapForAOHideFlag;
	}

	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}

	/**
	 * @param adminOptionHideFlag
	 *            The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}

	/**
	 * @return answerOverrideFlag
	 * 
	 * Returns the answerOverrideFlag.
	 */
	public boolean getAnswerOverrideFlag() {
		return answerOverrideFlag;
	}

	/**
	 * @param answerOverrideFlag
	 * 
	 * Sets the answerOverrideFlag.
	 */
	public void setAnswerOverrideFlag(boolean answerOverrideFlag) {
		this.answerOverrideFlag = answerOverrideFlag;
	}

	/**
	 * @return datafieldMapForCstmzdId
	 * 
	 * Returns the datafieldMapForCstmzdId.
	 */
	public Map getDatafieldMapForCstmzdId() {
		return datafieldMapForCstmzdId;
	}

	/**
	 * @param datafieldMapForCstmzdId
	 * 
	 * Sets the datafieldMapForCstmzdId.
	 */
	public void setDatafieldMapForCstmzdId(Map datafieldMapForCstmzdId) {
		this.datafieldMapForCstmzdId = datafieldMapForCstmzdId;
	}

	/**
	 * @return hideStatusFlag
	 * 
	 * Returns the hideStatusFlag.
	 */
	public boolean getHideStatusFlag() {
		return hideStatusFlag;
	}

	/**
	 * @param hideStatusFlag
	 * 
	 * Sets the hideStatusFlag.
	 */
	public void setHideStatusFlag(boolean hideStatusFlag) {
		this.hideStatusFlag = hideStatusFlag;
	}
	/**
	 * @return Returns the answerStates.
	 */
	public String getAnswerStates() {
		return answerStates;
	}
	/**
	 * @param answerStates The answerStates to set.
	 */
	public void setAnswerStates(String answerStates) {
		this.answerStates = answerStates;
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
	/**
	 * @return Returns the viewPanel.
	 */
	public HtmlPanelGrid getViewPanel() {
		return viewPanel;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	/**
	 * @return Returns the loadQuestionForView.
	 */
	public String getLoadQuestionForView() {
	    setMode("View");
		RetrieveProductStructureQuestionnaireRequest request = 
			getRetrieveProductStructureQuestionnaireRequest();
		request.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW);
		RetrieveProductStructureBenefitAdministrationResponse response = 
        	(RetrieveProductStructureBenefitAdministrationResponse) this
                .executeService(request);
		if (null != response
				&& null != response.getBenefitAdministrationList()) {
			this.viewQuestionnaireList = response.getBenefitAdministrationList();
		    ProductStructureSessionObject productStructureSessionObject = 
             	(ProductStructureSessionObject) getSession().
     				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);
            setProductStructureId(productStructureSessionObject.getId());	
            String benefitComponentKey = (String) getSession().getAttribute("BNFT_CMPNT_KEY"); 
            setBenefitComponentId(benefitComponentKey);               
            setAdminLvlOptionAssnSysId((String)getSession().
                    getAttribute("ADMIN_OPTION_ASSN_ID"));
            
			for (int i = 0; i < viewQuestionnaireList.size(); i++) {
				ProductStructureQuestionnaireBO questionnaireBO = 
				    (ProductStructureQuestionnaireBO) viewQuestionnaireList.get(i);
				String questionName = questionnaireBO.getQuestionName();
				if(null == benefitDefnId){
				this.benefitDefnId = String.valueOf(questionnaireBO.getBenefitDefinitionId());
				}
				questionName = getLevelPrefix(questionnaireBO.getLevel())+ questionName;
				questionnaireBO.setQuestionName(questionName);
				((ProductStructureQuestionnaireBO)viewQuestionnaireList.get(i)).setQuestionName(questionName);
			}
		}	
		return "";
	}
	/**
	 * @param loadQuestionForView The loadQuestionForView to set.
	 */
	public void setLoadQuestionForView(String loadQuestionForView) {
		this.loadQuestionForView = loadQuestionForView;
	}
	/**
	 * @return Returns the viewQuestionnaireList.
	 */
	public List getViewQuestionnaireList() {
		return viewQuestionnaireList;
	}
	/**
	 * @param viewQuestionnaireList The viewQuestionnaireList to set.
	 */
	public void setViewQuestionnaireList(List viewQuestionnaireList) {
		this.viewQuestionnaireList = viewQuestionnaireList;
	}
	
	
	/**
	 * @return Returns the questionsStates.
	 */
	public String getQuestionsStates() {
		return questionsStates;
	}
	/**
	 * @param questionsStates The questionsStates to set.
	 */
	public void setQuestionsStates(String questionsStates) {
		this.questionsStates = questionsStates;
	}
	
	/**
	 * The method will be invoked when the Save button is clicked 
	 * from the Notes Attachment popup
	 * @return
	 */
	public String saveAction(){	  
	    AttachNotesToQuestionRequestForPS notesToQuestionAttachmentRequestPS = 
	        (AttachNotesToQuestionRequestForPS) this
        .getServiceRequest(ServiceManager.NOTES_TO_QUESTION_ATTACHMENT_REQUEST_PS);                                
	    notesToQuestionAttachmentRequestPS.setNotesAttachVO(setValuesToProdStructNotesToQuestionAttachmentVO()); 	                          
	    AttachNotesToQuestionResponseForPS notesToQuestionAttachmentResponse = 
	        (AttachNotesToQuestionResponseForPS) this
		.executeService(notesToQuestionAttachmentRequestPS); 
	    refreshQuestionNote(notesToQuestionAttachmentResponse.getMessages());
		return null; 
	
	}
	
	  
	/**
     * Method used to assign values to the VO
     * @return NotesToQuestionAttachmentVO
     */
    private ProdStructNotesToQuestionAttachmentVO setValuesToProdStructNotesToQuestionAttachmentVO(){
          
        ProductStructureSessionObject productStructureSessionObject = 
        	(ProductStructureSessionObject) getSession().
				getAttribute(WebConstants.PRODUCT_STRUCTURE_SESSION_KEY);       
       
        int primaryId = productStructureSessionObject.getId();        
        String primaryType = "ATTACHPRODSTRCT";
        String benefitComponentKey = (String) getSession().getAttribute("BNFT_CMPNT_KEY");        
        int benefitCompId = new Integer(benefitComponentKey).intValue();        
        int secondaryId = productStructureSessionObject.getAdminLevelOptionSysId();      
        String secondaryType = "ATTACHQUESTION";
        String noteId = getSelectedNoteId();           
        
        ProdStructNotesToQuestionAttachmentVO notesToQuestionAttachmentVO= 
            new ProdStructNotesToQuestionAttachmentVO(); 
        notesToQuestionAttachmentVO.setProdStructVersionNumber
        (productStructureSessionObject.getVersion());
        notesToQuestionAttachmentVO.setPrimaryId(primaryId);
        notesToQuestionAttachmentVO.setPrimaryEntityType(primaryType);
        notesToQuestionAttachmentVO.setSecondaryId(secondaryId);
        notesToQuestionAttachmentVO.setSecondaryEntityType(secondaryType);
        notesToQuestionAttachmentVO.setNoteId(noteId);
        notesToQuestionAttachmentVO.setNoteOverrideStatus("F");
        int benefitDefinitionId = ((Integer)getSession().getAttribute("SESSION_BNFTDEFNID")).intValue();       
        notesToQuestionAttachmentVO.setBnftDefId(benefitDefinitionId); 
        notesToQuestionAttachmentVO.setQuestionId(Integer.parseInt(getQuestionId()));  
        notesToQuestionAttachmentVO.setPrimaryEntityName(productStructureSessionObject.getName());
        notesToQuestionAttachmentVO.setBenefitCompId(benefitCompId);
        if(!"".equals(getNoteVersion())){
            notesToQuestionAttachmentVO.setNoteVersionNumber(Integer.parseInt(getNoteVersion()));
        }       
        notesToQuestionAttachmentVO.setAction(this.getNoteAction());
        notesToQuestionAttachmentVO.setBusinessDomainList
        (productStructureSessionObject.getBusinessDomains());
        
        return notesToQuestionAttachmentVO;
    }

    /**
     * @return Returns the selectedNoteId.
     */
    public String getSelectedNoteId() {
        return selectedNoteId;
    }
    /**
     * @param selectedNoteId The selectedNoteId to set.
     */
    public void setSelectedNoteId(String selectedNoteId) {
        this.selectedNoteId = selectedNoteId;
    }
   
    
       
    /**
     * @return Returns the noteVersion.
     */
    public String getNoteVersion() {
        return noteVersion;
    }
    /**
     * @param noteVersion The noteVersion to set.
     */
    public void setNoteVersion(String noteVersion) {
        this.noteVersion = noteVersion;
    }
   
    /**
     * @param noteAction The noteAction to set.
     */
    public void setNoteAction(String noteAction) {
        this.noteAction = noteAction;
    }
    /**
     * @return Returns the previousNoteVersion.
     */
    public String getPreviousNoteVersion() {
        return previousNoteVersion;
    }
    /**
     * @param previousNoteVersion The previousNoteVersion to set.
     */
    public void setPreviousNoteVersion(String previousNoteVersion) {
        this.previousNoteVersion = previousNoteVersion;
    }
    
   
    /**
     * @return Returns the noteAction.
     */
    public String getNoteAction() {
        return noteAction;
    }
    /**
     * @return Returns the mode.
     */
    public String getMode() {
        return mode;
    }
    /**
     * @param mode The mode to set.
     */
    public void setMode(String mode) {
        this.mode = mode;
    }
    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }
    /**
     * @param productStructureId The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
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
		addAllMessagesToRequest(response.getMessages());
		return new String();
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
			if(null!=getRequest().getParameter("questionId") && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
				this.questionId = getRequest().getParameter("questionId");
				this.getSession().setAttribute("questionId", questionId);
				}
		}
		if (null != getRequest().getParameter("primaryentityId")
				&& !("").equals(getRequest().getParameter("primaryentityId"))) {
			if(null!=getRequest().getParameter("primaryentityId") && getRequest().getParameter("primaryentityId").matches("^[0-9a-zA-Z_]+$")){
				this.primaryEntityID = getRequest().getParameter("primaryentityId");
				this.getSession().setAttribute("primaryEntityID", primaryEntityID);
				}
		}
		if (null != getRequest().getParameter("primaryEntytyType")
				&& !("").equals(getRequest().getParameter("primaryEntytyType"))) {
			if(null!=getRequest().getParameter("primaryEntytyType") && getRequest().getParameter("primaryEntytyType").matches("^[0-9a-zA-Z_]+$")){
				this.primaryType = getRequest().getParameter("primaryEntytyType");
				this.getSession().setAttribute("primaryType", primaryType);
				}
		}
		if (null != getRequest().getParameter("bcId")
				&& !("").equals(getRequest().getParameter("bcId"))) {
			if(null!=getRequest().getParameter("bcId") && getRequest().getParameter("bcId").matches("^[0-9a-zA-Z_]+$")){
				this.benefitComponentId = getRequest().getParameter("bcId");
				this.getSession().setAttribute("benefitComponentId",
						benefitComponentId);
				}
		}
		if (null != getRequest().getParameter("benefitDefnId")
				&& !("").equals(getRequest().getParameter("benefitDefnId"))) {
			if(null!=getRequest().getParameter("benefitDefnId") && getRequest().getParameter("benefitDefnId").matches("^[0-9a-zA-Z_]+$")){
				this.benefitDefnId = getRequest().getParameter("benefitDefnId");
				this.getSession().setAttribute("benefitDefnId", benefitDefnId);
				}
		}
		if (null != getRequest().getParameter("adminLvlOptionId")
				&& !("").equals(getRequest().getParameter("adminLvlOptionId"))) {
			if(null!=getRequest().getParameter("adminLvlOptionId") && getRequest().getParameter("adminLvlOptionId").matches("^[0-9a-zA-Z_]+$")){
				this.adminLvlOptionAssnSysId = getRequest().getParameter("adminLvlOptionId");
				this.getSession().setAttribute("adminLvlOptionAssnSysId",
						adminLvlOptionAssnSysId);
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
    /**
     * @return Returns the searchString.
     */
    public String getSearchString() {
        return searchString;
    }
    /**
     * @param searchString The searchString to set.
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    /**
     * @param questionNotes The questionNotes to set.
     */
    public void setQuestionNotes(List questionNotes) {
        this.questionNotes = questionNotes;
    }
    /**
     * @param records The records to set.
     */
    public void setRecords(String records) {
        this.records = records;
    }
    /**
     * @return Returns the questionId.
     */
    public String getQuestionId() {
        return questionId;
    }
    /**
     * @param questionId The questionId to set.
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    /**
     * @return Returns the benefitComponentId.
     */
    public String getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId The benefitComponentId to set.
     */
    public void setBenefitComponentId(String benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * @return Returns the benefitDefnId.
     */
    public String getBenefitDefnId() {
        return benefitDefnId;
    }
    /**
     * @param benefitDefnId The benefitDefnId to set.
     */
    public void setBenefitDefnId(String benefitDefnId) {
        this.benefitDefnId = benefitDefnId;
    }
    /**
     * @return Returns the adminLvlOptionAssnSysId.
     */
    public String getAdminLvlOptionAssnSysId() {
        return adminLvlOptionAssnSysId;
    }
    /**
     * @param adminLvlOptionAssnSysId The adminLvlOptionAssnSysId to set.
     */
    public void setAdminLvlOptionAssnSysId(String adminLvlOptionAssnSysId) {
        this.adminLvlOptionAssnSysId = adminLvlOptionAssnSysId;
    }
    /**
     * @return Returns the primaryEntityID.
     */
    public String getPrimaryEntityID() {
        return primaryEntityID;
    }
    /**
     * @param primaryEntityID The primaryEntityID to set.
     */
    public void setPrimaryEntityID(String primaryEntityID) {
        this.primaryEntityID = primaryEntityID;
    }
    /**
     * @return Returns the primaryType.
     */
    public String getPrimaryType() {
        return primaryType;
    }
    /**
     * @param primaryType The primaryType to set.
     */
    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
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
    /**
     * @return Returns the noteName.
     */
    public String getNoteName() {
        return noteName;
    }
    /**
     * @param noteName The noteName to set.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    /**
     * @return Returns the version.
     */
    public String getVersion() {
        return version;
    }
    /**
     * @param version The version to set.
     */
    public void setVersion(String version) {
        this.version = version;
    }
    /**
     * @return Returns the prevNoteIdSelected.
     */
    public String getPrevNoteIdSelected() {
        return prevNoteIdSelected;
    }
    /**
     * @param prevNoteIdSelected The prevNoteIdSelected to set.
     */
    public void setPrevNoteIdSelected(String prevNoteIdSelected) {
        this.prevNoteIdSelected = prevNoteIdSelected;
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