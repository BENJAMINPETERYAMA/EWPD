/*
 * AddQuestionaireBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitQuestionnaireAssnBO;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveQuestionaireRequest;
import com.wellpoint.wpd.common.standardbenefit.request.UpdateQuestionnaireRequest;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveQuestionaireResponse;
import com.wellpoint.wpd.common.standardbenefit.response.UpdateQuestionnaireResponse;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AddQuestionaireBackingBean extends WPDBackingBean {

	private int rowNum;

	private int answerId;
	
	private String answerDesc="";

	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();
	private HtmlPanelGrid headerPanelForPrint = new HtmlPanelGrid();

	private HtmlPanelGrid panel = new HtmlPanelGrid();
	private HtmlPanelGrid panelForPrint = new HtmlPanelGrid();

	private List questionaireList = null;
	
	private List orgQuestionnaireList;
	
	private List newQuestions             = null;
	
	private List modifiedQuestions        = null;
	
	private List removedQuestions         = null;
	
	java.util.HashMap possibleAnswerMap;

	//private String benefitMode = null;
	private HtmlInputHidden benefitMode = new HtmlInputHidden();

	private HtmlInputHidden  loadPrint ;
	
	private String noteId;
	
	private String newNoteIdSelected;
	
	private String noteStatus;
	
	private String tildaNoteStatus ;
	
	

	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {

		HtmlPanelGrid headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		headerPanel.setColumns(4);
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");

		HtmlOutputText htmlOutputText1 = new HtmlOutputText();
		HtmlOutputText htmlOutputText2 = new HtmlOutputText();
		HtmlOutputText htmlOutputText3 = new HtmlOutputText();
		//output text for note attachment
		HtmlOutputText outputTextNote = new HtmlOutputText();

		htmlOutputText1.setValue("Question");
		htmlOutputText2.setValue("Answer");
		htmlOutputText3.setValue("Reference");
		outputTextNote.setValue("Notes");

		headerPanel.setStyleClass("dataTableHeader");
		headerPanel.getChildren().add(htmlOutputText1);
		headerPanel.getChildren().add(htmlOutputText2);
		headerPanel.getChildren().add(htmlOutputText3);
		headerPanel.getChildren().add(outputTextNote);

		return headerPanel;
	}
	
	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanelForPrint() {

		HtmlPanelGrid headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		headerPanel.setColumns(3);
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");

		HtmlOutputText htmlOutputText1 = new HtmlOutputText();
		HtmlOutputText htmlOutputText2 = new HtmlOutputText();
		HtmlOutputText htmlOutputText3 = new HtmlOutputText();
		//output text for note attachment
		//HtmlOutputText outputText_Note = new HtmlOutputText();

		htmlOutputText1.setValue("Question");
		htmlOutputText2.setValue("Answer");
		htmlOutputText3.setValue("Reference");
	

		headerPanel.setStyleClass("dataTableHeader");
		headerPanel.getChildren().add(htmlOutputText1);
		headerPanel.getChildren().add(htmlOutputText2);
		headerPanel.getChildren().add(htmlOutputText3);
	

		return headerPanel;
	}

	/**
	 * @param headerPanel
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	/**
	 * @return Returns the panel showing questionnaire.
	 */
	public HtmlPanelGrid getPanel() {
		
		panel.getChildren().clear();
		panel.setWidth("100%");
		panel.setColumns(4);
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");

		StringBuffer line = null;
		String finalline = null;
		StringBuffer rowClass = new StringBuffer();
		HtmlOutputText answerDesc = null;
		HtmlOutputText notesHidden = null;
		questionaireList = getSelectedQuestionaireList();

		if (null != questionaireList) {

			for (int i = 0; i < questionaireList.size(); i++) {
				HtmlOutputText questionDesc = new HtmlOutputText();
				HtmlOutputText referenceDesc = new HtmlOutputText();
				HtmlSelectOneMenu possibleAnswer = new HtmlSelectOneMenu();
//				Notes Attachment Image
                HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
                
				HtmlPanelGroup referenceGroup = new HtmlPanelGroup();
				HtmlInputHidden childCountHidden = new HtmlInputHidden();
				notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i); 
				questionDesc.setId("questionDesc" + i);

				BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) questionaireList
						.get(i);
				if (i > 0) {
					rowClass.append(",");
				}
				int level = benefitQuestionnaireAssnBO.getLevel();

				if (level > 1) {
					finalline = getLevelPrefix(level);
					questionDesc.setValue(finalline
							+ benefitQuestionnaireAssnBO.getQuestionDesc());
					rowClass.append("dataTableOddRow");
				} else {
					questionDesc.setValue(benefitQuestionnaireAssnBO
							.getQuestionDesc());
					rowClass.append("dataTableEvenRow");
				}
				if (this.benefitMode !=null && this.benefitMode.getValue().equals("Mode")) {
					childCountHidden.setId("childCount" + i);
					childCountHidden.setValue(new Integer(
							benefitQuestionnaireAssnBO.getChildCount()));
					List answerList = benefitQuestionnaireAssnBO
							.getPossibleAnswerList();
					List possibleAnswersList = (List) getPossibleAnswersListForQuestion(answerList);

					possibleAnswer.setId("selectitem" + i);
					UISelectItems uis = new UISelectItems();
					uis.setValue(possibleAnswersList);

					possibleAnswer.setValue(new Integer(
							benefitQuestionnaireAssnBO.getAnswerId())
							.toString());
					possibleAnswer.setStyleClass("formInputList");
					possibleAnswer
							.setStyleClass("formInputFieldBenefitStructure");
					possibleAnswer.getChildren().add(uis);
					possibleAnswer.setOnclick("storeOldValue(this);return false;");
					possibleAnswer.setOnchange("return loadNewChild(this)");
				} else if (this.benefitMode !=null && this.benefitMode.getValue().equals(WebConstants.BENEFIT_VIEW)) {
					answerDesc = new HtmlOutputText();
					answerDesc.setId("answerDesc" + i);
					answerDesc.setValue(benefitQuestionnaireAssnBO
							.getAnswerDesc());
					childCountHidden.setId("childCount" + i);
				}
				referenceDesc.setId("reference" + i);
				referenceDesc.setValue(benefitQuestionnaireAssnBO
						.getReferenceDesc());

				panel.getChildren().add(questionDesc);				
				if (this.benefitMode !=null && this.benefitMode.getValue().equals("Mode")) {
					panel.getChildren().add(possibleAnswer);
				} else if (this.benefitMode !=null && this.benefitMode.getValue().equals(WebConstants.BENEFIT_VIEW)) {
					panel.getChildren().add(answerDesc);
				}
				//start notes
				StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
				int adminOptionLevelSysId = new Integer(getSession().getAttribute(
						WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();
				int benifitKey = sessionObject.getStandardBenefitKey();
				String imageid= new Integer(i).toString();
				//String benfitDfnKey=sessionObject.benefitDefinitionKey;
				String primaryType= WebConstants.ATTACH_BENEFIT;
				if(benefitQuestionnaireAssnBO.getNotes_exists().equals("Y")){
				notesAttachmentImage.setUrl("../../images/notes_exist.gif");
				}else{
					notesAttachmentImage.setUrl("../../images/page.gif");
				}
				notesAttachmentImage.setStyle("cursor:hand;");
				notesAttachmentImage.setId("notesAttachmentImage"+ i);               
				if (this.benefitMode !=null && this.benefitMode.getValue().equals("Mode")) {
				notesAttachmentImage.setOnclick("loadNotesPopup('../question/questionNotesPopup.jsp'+getUrl(),'"
                                                    + benefitQuestionnaireAssnBO.getQuestionId()
                                                    + "','"
                                                    + benifitKey
                                                    + "','"
                                                    + primaryType
                                                    + "','"
													+null
													+ "','"
													+null
													+ "','"
													+adminOptionLevelSysId
													+"','"
													+"notesAttachmentImage" +i+"','"+i
            										 +"');return false;");  
				
				}else if (this.benefitMode !=null && this.benefitMode.getValue().equals(WebConstants.BENEFIT_VIEW)) {
					notesAttachmentImage.setOnclick("loadNotesPopup('../popups/questionNotesViewPopup.jsp','"
                            + benefitQuestionnaireAssnBO.getQuestionId()
                            + "','"
                            + benifitKey
                            + "','"
                            + primaryType
                            + "','"
							+null
							+ "','"
							+null
							+ "','"
							+adminOptionLevelSysId
							+"','"
							+"notesAttachmentImage" +i
							 +"');return false;");  	
				}
				//end of notes
				referenceGroup.getChildren().add(referenceDesc);
				referenceGroup.getChildren().add(childCountHidden);				
				panel.getChildren().add(referenceGroup);
				if("Y".equals(benefitQuestionnaireAssnBO.getValidDomainToAttach())){             	   
            	     panel.getChildren().add(notesAttachmentImage);               	   
            	 }else{
            	    panel.getChildren().add(notesHidden);  
            	 }
			}
		}
		panel.setRowClasses(rowClass.toString());
		return panel;
	}
	

	/**
	 * @return Returns the panel showing questionnaire.
	 */
	public HtmlPanelGrid getPanelForPrint() {
		
		panel.getChildren().clear();
		panel.setWidth("100%");
		panel.setColumns(3);
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");

		StringBuffer line = null;
		String finalline = null;
		StringBuffer rowClass = new StringBuffer();
		HtmlOutputText answerDesc = null;
		HtmlOutputText notesHidden = null;
		questionaireList = getSelectedQuestionaireList();

		if (null != questionaireList) {

			for (int i = 0; i < questionaireList.size(); i++) {
				HtmlOutputText questionDesc = new HtmlOutputText();
				HtmlOutputText referenceDesc = new HtmlOutputText();
				HtmlSelectOneMenu possibleAnswer = new HtmlSelectOneMenu();
//				Notes Attachment Image
                //HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
                
				HtmlPanelGroup referenceGroup = new HtmlPanelGroup();
				HtmlInputHidden childCountHidden = new HtmlInputHidden();
				notesHidden = new HtmlOutputText();
            	//notesHidden.setId("notesHidden"+i); 
				questionDesc.setId("questionDesc" + i);

				BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) questionaireList
						.get(i);
				if (i > 0) {
					rowClass.append(",");
				}
				int level = benefitQuestionnaireAssnBO.getLevel();

				if (level > 1) {
					finalline = getLevelPrefix(level);
					questionDesc.setValue(finalline
							+ benefitQuestionnaireAssnBO.getQuestionDesc());
					rowClass.append("dataTableOddRow");
				} else {
					questionDesc.setValue(benefitQuestionnaireAssnBO
							.getQuestionDesc());
					rowClass.append("dataTableEvenRow");
				}
				if (this.benefitMode.getValue().equals("Mode")) {
					childCountHidden.setId("childCount" + i);
					childCountHidden.setValue(new Integer(
							benefitQuestionnaireAssnBO.getChildCount()));
					List answerList = benefitQuestionnaireAssnBO
							.getPossibleAnswerList();
					List possibleAnswersList = (List) getPossibleAnswersListForQuestion(answerList);

					possibleAnswer.setId("selectitem" + i);
					UISelectItems uis = new UISelectItems();
					uis.setValue(possibleAnswersList);

					possibleAnswer.setValue(new Integer(
							benefitQuestionnaireAssnBO.getAnswerId())
							.toString());
					possibleAnswer.setStyleClass("formInputList");
					possibleAnswer
							.setStyleClass("formInputFieldBenefitStructure");
					possibleAnswer.getChildren().add(uis);
					possibleAnswer.setOnclick("storeOldValue(this);return false;");
					possibleAnswer.setOnchange("return loadNewChild(this)");
				} else if (this.benefitMode.getValue().equals(WebConstants.BENEFIT_VIEW)) {
					answerDesc = new HtmlOutputText();
					answerDesc.setId("answerDesc" + i);
					answerDesc.setValue(benefitQuestionnaireAssnBO
							.getAnswerDesc());
				}
				referenceDesc.setId("reference" + i);
				referenceDesc.setValue(benefitQuestionnaireAssnBO
						.getReferenceDesc());

				panel.getChildren().add(questionDesc);				
				if (this.benefitMode.getValue().equals("Mode")) {
					panel.getChildren().add(possibleAnswer);
				} else if (this.benefitMode.getValue().equals(WebConstants.BENEFIT_VIEW)) {
					panel.getChildren().add(answerDesc);
				}
				//start notes
				StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
				int adminOptionLevelSysId = new Integer(getSession().getAttribute(
						WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();
				int benifitKey = sessionObject.getStandardBenefitKey();
				String imageid= new Integer(i).toString();
				//String benfitDfnKey=sessionObject.benefitDefinitionKey;
				String primaryType= WebConstants.ATTACH_BENEFIT;
//				if(benefitQuestionnaireAssnBO.getNotes_exists().equals("Y")){
//				notesAttachmentImage.setUrl("../../images/notes_exist.gif");
//				}else{
//					notesAttachmentImage.setUrl("../../images/page.gif");
//				}
//				notesAttachmentImage.setStyle("cursor:hand;");
//				notesAttachmentImage.setId("notesAttachmentImage"+ i);               
//				if (this.benefitMode.equals("Mode")) {
//				notesAttachmentImage.setOnclick("loadNotesPopup('../question/questionNotesPopup.jsp','"
//                                                    + benefitQuestionnaireAssnBO.getQuestionId()
//                                                    + "','"
//                                                    + benifitKey
//                                                    + "','"
//                                                    + primaryType
//                                                    + "','"
//													+null
//													+ "','"
//													+null
//													+ "','"
//													+adminOptionLevelSysId
//													+"','"
//													+"notesAttachmentImage" +i+"','"+i
//            										 +"');return false;");  
//				
//				}else if (this.benefitMode.equals(WebConstants.BENEFIT_VIEW)) {
//					notesAttachmentImage.setOnclick("loadNotesPopup('../popups/questionNotesViewPopup.jsp','"
//                            + benefitQuestionnaireAssnBO.getQuestionId()
//                            + "','"
//                            + benifitKey
//                            + "','"
//                            + primaryType
//                            + "','"
//							+null
//							+ "','"
//							+null
//							+ "','"
//							+adminOptionLevelSysId
//							+"','"
//							+"notesAttachmentImage" +i
//							 +"');return false;");  	
//				}
				//end of notes
				referenceGroup.getChildren().add(referenceDesc);
				referenceGroup.getChildren().add(childCountHidden);				
				panel.getChildren().add(referenceGroup);
//				if("Y".equals(benefitQuestionnaireAssnBO.getValidDomainToAttach())){             	   
//            	     panel.getChildren().add(notesAttachmentImage);               	   
//            	 }else{
//            	    panel.getChildren().add(notesHidden);  
//            	 }
			}
		}
		panel.setRowClasses(rowClass.toString());
		return panel;
	}
	
	
	/**
	 * @param panel
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * @return List Of questionnaire This method returns the list of
	 *         questionnaires that are attached to an admin option.
	 */
	public List getSelectedQuestionaireList() {
		
		RetrieveQuestionaireRequest retrieveQuestionaireRequest = (RetrieveQuestionaireRequest) this
				.getServiceRequest(ServiceManager.RETRIVE_QUESTIONAIRE_REQUEST);
		
		StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		
		possibleAnswerMap = getAllPossibleAnswersForAdminOption(); 
		sessionObject.setAllPossibleAnswerMap(possibleAnswerMap);
		retrieveQuestionaireRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		
		int adminOptionLevelSysId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();
		int adminOptionId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ID).toString()).intValue();
		int benefitId = sessionObject.getStandardBenefitKey();
		int parentId = sessionObject.getStandardBenefitParentKey();

		retrieveQuestionaireRequest
				.setAdminLevelOptionAssnSysId(adminOptionLevelSysId);
		retrieveQuestionaireRequest.setAdminOptionId(adminOptionId);
		retrieveQuestionaireRequest.setBenefitId(benefitId);
		retrieveQuestionaireRequest.setMaxSearchResultSize(Integer.MAX_VALUE);
		retrieveQuestionaireRequest
				.setAction(BusinessConstants.RETRIEVE_QUESTIONAIRE);
		retrieveQuestionaireRequest
				.setParentId(parentId);
		
		if (null == questionaireList || questionaireList.size() == 0) {
			RetrieveQuestionaireResponse retrieveQuestionaireResponse = (RetrieveQuestionaireResponse) this
					.executeService(retrieveQuestionaireRequest);			
			questionaireList = retrieveQuestionaireResponse
					.getSelectedQuestionaireList();
			sessionObject.setQuestionnaireList(questionaireList);
			
//			Creating original questionnaire list and setting in session
        	orgQuestionnaireList = new ArrayList();
        	Iterator it = questionaireList.iterator();
        	while(it.hasNext()){
        		
        		BenefitQuestionnaireAssnBO bQueBO = (BenefitQuestionnaireAssnBO)it.next();
        		BenefitQuestionnaireAssnBO orgBQueBO = new BenefitQuestionnaireAssnBO();
        		orgBQueBO.setQuestionnaireId( bQueBO.getQuestionnaireId()  );
        		orgBQueBO.setQuestionId( bQueBO.getQuestionId()  );
        		orgBQueBO.setParentQuestionnaireId( bQueBO.getParentQuestionnaireId()  );
        		orgBQueBO.setAnswerId( bQueBO.getAnswerId()  );
        		orgBQueBO.setAnswerDesc(  bQueBO.getAnswerDesc()  );
        		orgBQueBO.setAdminLvlOptionAssnSysId( bQueBO.getAdminLvlOptionAssnSysId()  );
        		orgBQueBO.setBenefitAdministrationId( bQueBO.getBenefitAdministrationId()  );
        		orgBQueBO.setBenefitId( bQueBO.getBenefitId()  );
        		orgBQueBO.setChildCount( bQueBO.getChildCount()  );
        		orgBQueBO.setLevel( bQueBO.getLevel()  );
        		orgBQueBO.setNotes_exists( bQueBO.getNotes_exists() );
        		orgBQueBO.setPossibleAnswerList( bQueBO.getPossibleAnswerList() );
        		orgBQueBO.setQuestionOrder( bQueBO.getQuestionOrder() );
        		orgBQueBO.setValidDomainToAttach( bQueBO.getValidDomainToAttach() );
        		orgBQueBO.setReferenceId( bQueBO.getReferenceId() );
        		orgBQueBO.setSequenceNumber( bQueBO.getSequenceNumber() );
        		orgBQueBO.setTierSysId( bQueBO.getTierSysId() );
        		
        		
        		orgQuestionnaireList.add(orgBQueBO); 
        	}	  
        	sessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	
		}
		return questionaireList;
	}


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
     * To get the request object for retrieving the possible answer list
     * after setting all the values in it.
     * 
     * @return RetrieveAllPossibleAnswerRequest
     */
    private RetrieveAllPossibleAnswerRequest 
		getRetrieveAllPossibleAnswerRequest() {
        // Create the session object
    	StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
		.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
    	
        int adminOptionId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ID).toString()).intValue();
        
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
	 * @param answersList
	 * @return List of possible answers This method changes the answers to a
	 *         select item list of the possible answers.
	 */
	private List getPossibleAnswersListForQuestion(List answersList) {
		List possibleAnswers = new ArrayList();
		if (null != answersList && !answersList.isEmpty()) {
			for (int i = 0; i < answersList.size(); i++) {
				// get the bo from the list
				PossibleAnswerBO answerBO = (PossibleAnswerBO) answersList
						.get(i);
				// create the new SelectItem with the answerid and desc
				SelectItem selectItem = new SelectItem(new Integer(answerBO
						.getPossibleAnswerId()).toString(), answerBO
						.getPossibleAnswerDesc());
				// add it to the possibleAnswersList
				possibleAnswers.add(selectItem);
			}
		}
		return possibleAnswers;
	}

	/**
	 * @return This method loads all the child questions available in the
	 *         questionnire for the particular answer id
	 */
	public String selectChildQuestionnaireList() {
		//getSelectedQuestionaireList();
		String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		RetrieveQuestionaireRequest retrieveQuestionaireRequest = (RetrieveQuestionaireRequest) this
				.getServiceRequest(ServiceManager.RETRIVE_QUESTIONAIRE_REQUEST);
		int rowNum = this.getRowNum();
		int answerId = this.getAnswerId();
		String answerDesc = this.answerDesc;
		
		int adminOptionLevelSysId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();

		StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		questionaireList = sessionObject.getQuestionnaireList();
		if(null!=tildaArray && tildaArray.length>0){
		processQuestionaireList(questionaireList,tildaArray);
		}
		tildaArray =null;
		this.tildaNoteStatus =null;
		int benefitId = sessionObject.getStandardBenefitKey();

		BenefitQuestionnaireAssnBO benefitQuestionnaireAssnBO = (BenefitQuestionnaireAssnBO) questionaireList
				.get(rowNum);
		retrieveQuestionaireRequest
				.setAction(BusinessConstants.RETRIEVE_CHILD_QUESTIONAIRE);
		retrieveQuestionaireRequest.setQuestionareListIndex(rowNum);
		retrieveQuestionaireRequest.setQuestionnareList(questionaireList);
		retrieveQuestionaireRequest.setSelectedAnswerId(answerId);
		retrieveQuestionaireRequest.setSelectedAnswerDesc(answerDesc);
		retrieveQuestionaireRequest
				.setBenefitQuestionnaireAssnBO(benefitQuestionnaireAssnBO);
		retrieveQuestionaireRequest.setBenefitId(benefitId);
		retrieveQuestionaireRequest
				.setAdminLevelOptionAssnSysId(adminOptionLevelSysId);
		
		possibleAnswerMap = sessionObject.getAllPossibleAnswerMap();
		retrieveQuestionaireRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		
		RetrieveQuestionaireResponse retrieveQuestionaireResponse = (RetrieveQuestionaireResponse) this
				.executeService(retrieveQuestionaireRequest);
		questionaireList = retrieveQuestionaireResponse
				.getSelectedQuestionaireList();
		sessionObject.setQuestionnaireList(questionaireList);

		return "questionaireList";
	}

	
	private void processQuestionaireList(List questionaireList,String[] tildaArray){
		
		int size = questionaireList.size();
		
		for(int i=0;i<size;i++){
			
			for (int j=0;j<tildaArray.length;j++){
				if((new Integer(i).toString()+"Y").equals(tildaArray[j])){
					((BenefitQuestionnaireAssnBO) questionaireList
					.get(i)).setNotes_exists("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((BenefitQuestionnaireAssnBO) questionaireList
							.get(i)).setNotes_exists("N");
							break;
				}
			}
		}
		
	}
	/**
	 * @return This method saves the questionnaire as answered
	 */
	public String saveQuestionnaire() {
		
		UpdateQuestionnaireRequest updateQuestionnaireRequest = (UpdateQuestionnaireRequest) this
				.getServiceRequest(ServiceManager.UPDATE_QUESTIONAIRE_REQUEST);
		int adminOptionLevelSysId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();
		StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		updateQuestionnaireRequest.setBenefitId(sessionObject
				.getStandardBenefitKey());
		updateQuestionnaireRequest.setQuestionnareList(sessionObject
				.getQuestionnaireList());
		updateQuestionnaireRequest
				.setAdminlevelOptionSysId(adminOptionLevelSysId);
		updateQuestionnaireRequest.setMainObjectIdentifier(sessionObject
				.getStandardBenefitName());
		updateQuestionnaireRequest.setVersionNumber(sessionObject
				.getStandardBenefitVersionNumber());
		updateQuestionnaireRequest.setDomainList(sessionObject
				.getBusinessDomains());
		updateQuestionnaireRequest.setStatus(sessionObject
				.getStandardBenefitStatus());
		updateQuestionnaireRequest.setParentSystemId(sessionObject
				.getStandardBenefitParentKey());
		
		orgQuestionnaireList = (List)sessionObject.getOrgQuestionnaireList();   
		this.filterQuestionsForUpdate();
		updateQuestionnaireRequest.setQuestionnaireListToAdd(this.newQuestions);
		updateQuestionnaireRequest.setQuestionnaireListToUpdate(this.modifiedQuestions);
		updateQuestionnaireRequest.setQuestionnaireListToRemove(this.removedQuestions);

		UpdateQuestionnaireResponse response = (UpdateQuestionnaireResponse) this
				.executeService(updateQuestionnaireRequest);
		if (null != response) {
			List messageList = response.getMessages();
			addAllMessagesToRequest(messageList);
			
			//	Creating original questionnaire list and setting in session
        	orgQuestionnaireList = new ArrayList();
        	Iterator it = questionaireList.iterator();
        	while(it.hasNext()){
        		BenefitQuestionnaireAssnBO bQueBO = (BenefitQuestionnaireAssnBO)it.next();
        		BenefitQuestionnaireAssnBO orgBQueBO = new BenefitQuestionnaireAssnBO();
        		
        		orgBQueBO.setQuestionnaireId( bQueBO.getQuestionnaireId()  );
        		orgBQueBO.setQuestionId( bQueBO.getQuestionId()  );
        		orgBQueBO.setParentQuestionnaireId( bQueBO.getParentQuestionnaireId()  );
        		orgBQueBO.setAnswerId( bQueBO.getAnswerId()  );
        		orgBQueBO.setAnswerDesc(  bQueBO.getAnswerDesc()  );
        		orgBQueBO.setAdminLvlOptionAssnSysId( bQueBO.getAdminLvlOptionAssnSysId()  );
        		orgBQueBO.setBenefitAdministrationId( bQueBO.getBenefitAdministrationId()  );
        		orgBQueBO.setBenefitId( bQueBO.getBenefitId()  );
        		orgBQueBO.setChildCount( bQueBO.getChildCount()  );
        		orgBQueBO.setLevel( bQueBO.getLevel()  );
        		orgBQueBO.setNotes_exists( bQueBO.getNotes_exists() );
        		orgBQueBO.setPossibleAnswerList( bQueBO.getPossibleAnswerList() );
        		orgBQueBO.setQuestionOrder( bQueBO.getQuestionOrder() );
        		orgBQueBO.setValidDomainToAttach( bQueBO.getValidDomainToAttach() );
        		orgBQueBO.setReferenceId( bQueBO.getReferenceId() );
        		orgBQueBO.setSequenceNumber( bQueBO.getSequenceNumber() );
        		orgBQueBO.setTierSysId( bQueBO.getTierSysId() );
        		
        		orgQuestionnaireList.add(orgBQueBO); 
        	}	  
        	sessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	
		}
		getRequest().setAttribute("RETAIN_Value", "");

		return "saveQuestionaireList";
	}

	/**
     * Method to compare the original questionnaire & modified one.
     * 6 questionnaire lists will be prepared
     * 
     */
    
    private void filterQuestionsForUpdate(){ 
    	Logger.logInfo("inside filterQuestionsForUpdate()");
    	StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
		.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
    	// Code change by minu : 28-12-2010 : eWPD System Stabilization 
    	questionaireList = (List)sessionObject.getQuestionnaireList();	      
	    orgQuestionnaireList = (List)sessionObject.getOrgQuestionnaireList(); 
	    
	    newQuestions = new ArrayList(); 
	    modifiedQuestions = new ArrayList();
	    removedQuestions = new ArrayList();
	    
	    //Logic for non-tiered admin options
	    //for(int i=0;i<questionnaireList.size();i++){
	    Iterator it = questionaireList.iterator();
	    while(it.hasNext()){
	    		BenefitQuestionnaireAssnBO benefitQuesitionnaireBO = (BenefitQuestionnaireAssnBO)it.next();
		    	if(!"Not Answered".equals(benefitQuesitionnaireBO.getAnswerDesc()) ){
		    	int a=0;
		    	
		    	Iterator it1 = orgQuestionnaireList.iterator();
		    	while(it1.hasNext()){
		    		BenefitQuestionnaireAssnBO benefitQuesitionnaireBO1 = 
			    		(BenefitQuestionnaireAssnBO)it1.next(); 
		    		    		if(benefitQuesitionnaireBO.getQuestionnaireId() == benefitQuesitionnaireBO1.getQuestionnaireId()){
						    		a++; 
						    		it1.remove();
						    		if(benefitQuesitionnaireBO.getAnswerId() != benefitQuesitionnaireBO1.getAnswerId()){
							    		modifiedQuestions.add(benefitQuesitionnaireBO);
							    	}
					    		} 
		    	}
		    	
		    	if(a == 0){ 
		    		newQuestions.add(benefitQuesitionnaireBO);
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
    			if( "Not Answered".equals( ((BenefitQuestionnaireAssnBO)it.next()).getAnswerDesc().trim() )  ){
		    		it.remove();
		    	} 
    		}
    		
    	}
    	return questionnaireList;
    }
    
	/**
	 * @return Returns the rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * @param rowNum
	 *            The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	/**
	 * @param level
	 * @return
	 */
	public String getLevelPrefix(int level) {
		StringBuffer buffer = new StringBuffer("");
		for (int i = 1; i < level; i++) {
			buffer.append(" - ");
		}
		return buffer.toString();
	}

	/**
	 * @return Returns the benefitMode.
	 */
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden getBenefitMode() {
		// get the mode from the Standard benefit session object
		StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
				.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		
		if (null != sessionObject) {
			//this.benefitMode = sessionObject.getStandardBenefitMode();
			this.benefitMode.setValue(sessionObject.getStandardBenefitMode());
		}

		return benefitMode;
	}

	/**
	 * @param benefitMode
	 *            The benefitMode to set.
	 */
	public void setBenefitMode(HtmlInputHidden benefitMode) {
		this.benefitMode = benefitMode;
	}

	/**
	 * @return Returns the loadPrint.
	 */
	//WAS 7.0 Changes - Binding variable loadPrint  modifieRd to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/** 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden getLoadPrint() {
		//this.benefitMode = WebConstants.BENEFIT_VIEW;
		this.benefitMode.setValue(WebConstants.BENEFIT_VIEW);
		this.questionaireList = null;
		this.getPanel();
		return loadPrint;
	}

	/**
	 * @param loadPrint
	 *            The loadPrint to set.
	 */
	public void setLoadPrint(HtmlInputHidden loadPrint) {
		this.loadPrint = loadPrint;
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
	 * @return Returns the noteStatus.
	 */
	public String getNoteStatus() {
		return noteStatus;
	}
	/**
	 * @param noteStatus The noteStatus to set.
	 */
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	/**
	 * @return Returns the newNoteIdSelected.
	 */
	public String getNewNoteIdSelected() {
		return newNoteIdSelected;
	}
	/**
	 * @param newNoteIdSelected The newNoteIdSelected to set.
	 */
	public void setNewNoteIdSelected(String newNoteIdSelected) {
		this.newNoteIdSelected = newNoteIdSelected;
	}
	/**
	 * @return Returns the questionaireList.
	 */
	public List getQuestionaireList() {
		return questionaireList;
	}
	/**
	 * @param questionaireList The questionaireList to set.
	 */
	public void setQuestionaireList(List questionaireList) {
		this.questionaireList = questionaireList;
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
	 * @param panelForPrint The panelForPrint to set.
	 */
	public void setPanelForPrint(HtmlPanelGrid panelForPrint) {
		this.panelForPrint = panelForPrint;
	}
	 public String loadNextPagePrint(){
	 	return "success";
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