	/*
 * 
 * EditQuestionnaireBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.adminoption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.request.PersistChildQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveChildQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveRootQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.response.PersistChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveChildQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveRootQuestionnaireResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.AnswerBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * EditQuestionnaireBackingBean contains associated child questions 
 * for the corresponding root questions associated to the admin option.
 * 
 * This bean will bind with the jsp pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EditQuestionnaireBackingBean extends WPDBackingBean {
	
	private boolean requiredAnswer = false;
	
	private boolean requiredQuestion = false;
	
	private int rootQuestionnaireHierarchySystemId = 0;//hidden
	
	private int adminOptionSystemId = 0;//hidden
	
	private String adminOptionName = null;//hidden
	
	private int adminOptionVersion = 0;//hidden
	
	private String rootQuestionDescAndReference = null;
	
	private List possibleAnswerList = null;
	
	private String selectedAnswerId = null;
	
	private String selectedQuestionId = null;//hidden
	
	//private String loadEditQuestionnairePopUp = null;
	private HtmlInputHidden loadEditQuestionnairePopUp= new HtmlInputHidden(); 
	
	private String childQuestionnairesToDeleted = null;
	
	private HtmlPanelGrid headerPanel = null;
	
	private HtmlPanelGrid childQuestionsPanel = null;
	
	private HashMap sequenceMap = new HashMap();
	
	private HashMap hiddenQuestionnaireHierarchyStaticMap = new HashMap();
	
	private HashMap answersMap = new HashMap();
	
	private HashMap hiddenAnswersStaticMap = new HashMap();
	
	private HashMap questionInputMap = new HashMap();
	
	private HashMap hiddenQuestionNumberMap = new HashMap();
	
	private HashMap hiddenQuestionNumberStaticMap = new HashMap();
	
	private HashMap referenceInputMap = new HashMap();
	
	private HashMap hiddenReferenceIdMap = new HashMap();
	
	private HashMap lineOfBusinessInputMap = new HashMap();
	
	private HashMap hiddenLOBMap = new HashMap();
	
	private HashMap businessEntityInputMap = new HashMap();
	
	private HashMap hiddenBusinessEntityMap = new HashMap();
	
	private HashMap businessGroupInputMap = new HashMap();
	
	private HashMap hiddenBusinessGroupMap = new HashMap();
	
	private HashMap marketBusinessUnitInputMap = new HashMap();
	
	private HashMap hiddenmarketBusinessUnitMap = new HashMap();
	
	private HashMap deleteMap = new HashMap();
	
	private List validationMessages = null;
	
	private String queryVariables = null;
	
	private String panelData = null;
	
	private String duplicateData = null;
	
	private boolean deleteFlag = false;
	
	private HashMap hiddenParentRequiredMap = new HashMap();
	
	/**
	 * Method to retrieve the root questions details (Description, Possible Answers).
	 * @return RootQuestionBO
	 */
	private RootQuestionnaireBO retrieveQuestionDetails(){
		
		// varaible declarations.
		RootQuestionnaireBO rootQuestionnaireBO = null;
		
		// get the rootQuestionnaireHierachySystemId from the request.
		if(null != getRequest().getAttribute("parentQuestionnaireHierarchyId")){
			rootQuestionnaireHierarchySystemId = 
						Integer.parseInt((getRequest().
								getAttribute("parentQuestionnaireHierarchyId")).toString());
			rootQuestionnaireBO = getParentQuestionDetails();
		}
		// set the admin option name and admin option version number
		if(null != getRequest().getAttribute("adminOptionVersion")){
			adminOptionVersion = Integer.parseInt((getRequest().
					getAttribute("adminOptionVersion")).toString());
		}
		if(null != getRequest().getAttribute("adminOptionName")){
			getSession().setAttribute("AdminOptionName", 
					getRequest().getAttribute("adminOptionName"));
			adminOptionName = (getRequest().getAttribute("adminOptionName")).toString();
		}
		
		return rootQuestionnaireBO;
		
	}
	
	/**
	 * @param rootQuestionnaireBO
	 * @return
	 */
	private RootQuestionnaireBO getParentQuestionDetails() {
		RetrieveRootQuestionnaireRequest request = null;
		RetrieveRootQuestionnaireResponse response = null;
		RootQuestionnaireBO rootQuestionnaireBO = new RootQuestionnaireBO();
		// create the request.
		request = (RetrieveRootQuestionnaireRequest) this.getServiceRequest
						(ServiceManager.RETRIEVE_ROOT_QUESTIONNAIRE_REQUEST);
		request.setQuestionnaireHierarchySystemId
					(this.rootQuestionnaireHierarchySystemId);
		// get the response.
		response = (RetrieveRootQuestionnaireResponse) executeService(request);
		if(null != response){
			rootQuestionnaireBO = response.getRootQuestionnaireBO();
			// set the rootQuestionBO in the session
			getSession().setAttribute(
					"ParentQuestionnaireDetails", rootQuestionnaireBO);
		}else{
			getSession().setAttribute(
					"ParentQuestionnaireDetails", null);
		}
		return rootQuestionnaireBO;
	}

	/**
	 * Method to convert the answers list to the selected item list.
	 * @param answersList
	 * @return
	 */
	private List convertAnswersToSelectItem(List answersList){
		List convertedAnswersList = null;
		AnswerBO answerBO = null;
		SelectItem selectItem = null;
		int size = 0;
		if(null != answersList && !answersList.isEmpty()){
			size = answersList.size();
			for(int i = 0; i < size; i++){
				answerBO = (AnswerBO) answersList.get(i);
				if(null != answerBO){
					if(!("NOT ANSWERED").equals(answerBO.getAnswerDesc().trim().toUpperCase())){
						selectItem = new SelectItem(String.valueOf
								(answerBO.getAnswerId()), answerBO.getAnswerDesc());
						if(null == convertedAnswersList)
							convertedAnswersList = new ArrayList();
						convertedAnswersList.add(selectItem);
					}
				}
			}
		}
		return convertedAnswersList;
	}
	
	/**
	 * Method to retrieve the associated child questions for the root questions.
	 * @return List
	 */
	private List retrieveChildQuestions(String action){
		
		List childQuestions = null;
		RetrieveChildQuestionnaireRequest request = null;
		RetrieveChildQuestionnaireResponse response = null;
		
		// create the request 
		request = (RetrieveChildQuestionnaireRequest) this.getServiceRequest
							(ServiceManager.RETRIEVE_CHILD_QUESTIONNAIRE_REQUEST);
		request.setParentQuestionnaireSysId
					(this.rootQuestionnaireHierarchySystemId);
		request.setAction(action);
		request.setAdminOptionId(this.adminOptionSystemId);
		// get the response
		response = (RetrieveChildQuestionnaireResponse) 
								this.executeService(request);
		if(null != response){
			if(null != response.getMessages() && !response.getMessages().isEmpty())
				addAllMessagesToRequest(response.getMessages());
			childQuestions = response.getChildQuestionnaires();
		}
		return childQuestions;
		
	}
	
	/**
	 * Method to sort the child questions according to the answers.
	 * @return
	 */
	private Map splitChildByAnswers(List childQuestions){
		
		Map sortedQuestions = null;
		ChildQuestionnaireBO childQuestionnaireBO = null;
		SelectItem answerIdDesc = null;
		int answerId = 0;
		List splitedChildQuestions = null;
		int psblAnswerSize = 0;
		int childQstnsSize = 0;
		if(null != childQuestions && !childQuestions.isEmpty() && 
				null != possibleAnswerList && !possibleAnswerList.isEmpty()){
			psblAnswerSize = possibleAnswerList.size();
			// iterate the possible answer list of root question.
			for(int i = 0; i < psblAnswerSize; i++){
				splitedChildQuestions = null;
				answerIdDesc = (SelectItem) possibleAnswerList.get(i);
				if(null != answerIdDesc.getValue()){
					answerId = Integer.parseInt(
							answerIdDesc.getValue().toString());
				}
				childQstnsSize = childQuestions.size();
				// iterate the associated child question list of root question.
				for(int j = 0; j < childQstnsSize; j++){
					childQuestionnaireBO = (ChildQuestionnaireBO) childQuestions.get(j);
					// split the list according to the answer
					if(answerId == childQuestionnaireBO.getAnswerId()){
						if(null == splitedChildQuestions){
							splitedChildQuestions = new ArrayList();
						}
						splitedChildQuestions.add(childQuestionnaireBO);
					}
				}
				// add the splited questions list to the map.
				if(null != splitedChildQuestions && !splitedChildQuestions.isEmpty()){
					if(null == sortedQuestions)
						sortedQuestions = new HashMap(); 
					sortedQuestions.put(
						String.valueOf(answerId), splitedChildQuestions);
				}					
			}
		}
		
		return sortedQuestions;
		
	}
	
	/**
	 * Method to prepare the panel to display the results for the user.
	 * @return
	 */
	private void preparePanel(Map sortedQuestions){
		
		childQuestionsPanel = new HtmlPanelGrid();
		SelectItem answerIdAndDesc = null;
		List childQuestions = null;
		ChildQuestionnaireBO childQuestionnaireBO = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		boolean isDomainReadOnly = true;
		int psblAnswerSize = 0;
		int childQstnSize = 0;
		childQuestionsPanel.setBgcolor("#cccccc");
		childQuestionsPanel.setWidth("100%");
		childQuestionsPanel.setColumns(9);
		childQuestionsPanel.setCellpadding("2");
		childQuestionsPanel.setCellspacing("1");
		childQuestionsPanel.setColumnClasses("w8,w8,w20,w11,w11,w11,w11,w11,w9");
		
		if(null != sortedQuestions && !sortedQuestions.isEmpty() &&
				null != possibleAnswerList && !possibleAnswerList.isEmpty()){
			rootQuestionnaireBO = (RootQuestionnaireBO) getSession().
								getAttribute("ParentQuestionnaireDetails");
			if("ALL".equals(WPDStringUtil.getCommaSeperatedString(rootQuestionnaireBO.getLob())) &&
					"ALL".equals(WPDStringUtil.getCommaSeperatedString(rootQuestionnaireBO.getBusinessEntity())) &&
					"ALL".equals(WPDStringUtil.getCommaSeperatedString(rootQuestionnaireBO.getBusinessGroup())) &&
					"ALL".equals(WPDStringUtil.getCommaSeperatedString(rootQuestionnaireBO.getMarketBusinessUnit()))
					){
				isDomainReadOnly = false;
			}
			psblAnswerSize = possibleAnswerList.size();
			// iterate the possible answer list and get the answer id.
			for(int i = 0; i < psblAnswerSize; i++){
				answerIdAndDesc = (SelectItem) possibleAnswerList.get(i);
				if(null != answerIdAndDesc && null != answerIdAndDesc.getValue()){
					// get the childquestion list corresponding to the answer id from the map.
					childQuestions = (List) sortedQuestions.get
								(answerIdAndDesc.getValue().toString());
				}
				if(null != childQuestions && !childQuestions.isEmpty()){
					childQstnSize = childQuestions.size();
					// iterate the child question list and set to the panel.
					for(int j = 0; j < childQstnSize; j++){
						// get the child question bo from the list.
						childQuestionnaireBO = (ChildQuestionnaireBO) childQuestions.get(j);
						if(null != childQuestionnaireBO){
							HtmlOutputLabel sequenceLabel = 
								getSequenceLableForPanel(childQuestionnaireBO);
							sequenceLabel.setId("sequenceLabel"+j);
							HtmlOutputLabel answerLabel = 
								getAnswerLabelForPanel(childQuestionnaireBO);
							answerLabel.setId("answerLabel"+j);
							HtmlOutputLabel questionLabel = 
								getQuestionLabelForPanel(childQuestionnaireBO, i, j);
							questionLabel.setId("questionLabel"+j);
							HtmlOutputLabel referenceLabel = 
								getReferenceLabelForPanel(childQuestionnaireBO, i, j);
							referenceLabel.setId("referenceLabel"+j);
							HtmlOutputLabel lineOfBusinessLabel = 
								getLineOfBusinessLabelForPanel(childQuestionnaireBO, isDomainReadOnly, i, j);
							lineOfBusinessLabel.setId("lineOfBusinessLabel"+j);
							HtmlOutputLabel businessEntityLabel = 
								getBusinessEntityLabelForPanel(childQuestionnaireBO, isDomainReadOnly, i, j);
							businessEntityLabel.setId("businessEntityLabel"+j);
							HtmlOutputLabel businessGroupLabel = 
								getBusinessGroupLabelForPanel(childQuestionnaireBO, isDomainReadOnly, i, j);
							businessGroupLabel.setId("businessGroupLabel"+j);
							HtmlOutputLabel marketBusinessUnitLabel =
								getMarketBusinessUnitLabelForPanel(childQuestionnaireBO, isDomainReadOnly, i, j);
							marketBusinessUnitLabel.setId("marketBusinessUnitLabel"+j);
							HtmlOutputLabel deleteLabel = 
								getDeleteLabelForPanel(childQuestionnaireBO);
							deleteLabel.setId("deleteLabel"+j);
							
							childQuestionsPanel.getChildren().add(sequenceLabel);
							childQuestionsPanel.getChildren().add(answerLabel);
							childQuestionsPanel.getChildren().add(questionLabel);
							childQuestionsPanel.getChildren().add(referenceLabel);
							childQuestionsPanel.getChildren().add(lineOfBusinessLabel);
							childQuestionsPanel.getChildren().add(businessEntityLabel);
							childQuestionsPanel.getChildren().add(businessGroupLabel);
							childQuestionsPanel.getChildren().add(marketBusinessUnitLabel);
							childQuestionsPanel.getChildren().add(deleteLabel);
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * Method to generate the sequence label
	 * @return
	 */
	private HtmlOutputLabel getSequenceLableForPanel(ChildQuestionnaireBO childQuestionnaireBO){
		HtmlOutputLabel sequenceLabel = new HtmlOutputLabel();
		HtmlInputText sequenceInputText = new HtmlInputText();
		HtmlInputHidden quesionnaireHierachyHidden =  new HtmlInputHidden();
		
		sequenceLabel.setId("sequenceLabel" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		
		// set the sequence number in the input text.
        sequenceInputText.setStyleClass("formInputField");
        sequenceInputText.setStyle("width:55px;");
		sequenceInputText.setValue(Integer.toString
				(childQuestionnaireBO.getSequenceNumber()));
		sequenceInputText.setId("sequenceInputText" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		sequenceInputText.setStyleClass("formInputField");
		sequenceInputText.setOnkeypress("isNum1(this.id);");
		sequenceInputText.setMaxlength(7);
        ValueBinding valForSequence = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
				"#{editQuestionnaireBackingBean.sequenceMap["
				+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
				+ "]}");
        sequenceInputText.setValueBinding("value", valForSequence);
        
		// set the questionnaire hierarchy id in the hidden map.
        quesionnaireHierachyHidden.setId("hiddenQuestionnaireHierarchy" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
        quesionnaireHierachyHidden.setValue(Integer.toString
				(childQuestionnaireBO.getQuestionnaireHierarchySystemId()));
        ValueBinding valForHiddenQuestionnaireHierarchy = FacesContext.getCurrentInstance()
        			.getApplication().createValueBinding(
        					"#{editQuestionnaireBackingBean.hiddenQuestionnaireHierarchyStaticMap["
        					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
							+ "]}");
        quesionnaireHierachyHidden.setValueBinding("value", valForHiddenQuestionnaireHierarchy);
		
		sequenceLabel.getChildren().add(sequenceInputText);
		sequenceLabel.getChildren().add(quesionnaireHierachyHidden);
		
		return sequenceLabel;
	}
	
	/**
	 * Method to generate the answer label
	 * @return
	 */
	private HtmlOutputLabel getAnswerLabelForPanel(ChildQuestionnaireBO childQuestionnaireBO){
		HtmlOutputLabel answerLabel = new HtmlOutputLabel();
		HtmlSelectOneMenu answersSelectOneMenu = new HtmlSelectOneMenu();
		UISelectItems answersSelectItems = new UISelectItems();
		HtmlInputHidden answersInputHidden = new HtmlInputHidden();
		
		// set the answers list to the select one menu.
		answersSelectOneMenu.setId("answersSelectOneMenu" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		answersSelectOneMenu.setValue(
				Integer.toString(childQuestionnaireBO.getAnswerId()));
        ValueBinding valForAnswers = 
        			FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
							"#{editQuestionnaireBackingBean.answersMap["
							+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
							+ "]}");
        answersSelectOneMenu.setValueBinding("value", valForAnswers);
        answersSelectItems.setValue(this.possibleAnswerList);
        answersSelectOneMenu.getChildren().add(answersSelectItems);
		answersSelectOneMenu.setStyle("width:50px;");
		// set the answers to the hidden map.
        answersInputHidden.setId("answersInputHidden" +
        		childQuestionnaireBO.getQuestionnaireHierarchySystemId());
        answersInputHidden.setValue(
        		Integer.toString(childQuestionnaireBO.getAnswerId()));
        ValueBinding valForHiddenAnswers = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenAnswersStaticMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        answersInputHidden.setValueBinding("value", valForHiddenAnswers);
		
		answerLabel.getChildren().add(answersSelectOneMenu);
		answerLabel.getChildren().add(answersInputHidden);
		return answerLabel;
	}
	
	/**
	 * Method to generate the question label
	 * @return
	 */
	private HtmlOutputLabel getQuestionLabelForPanel(ChildQuestionnaireBO childQuestionnaireBO, int i, int j){
		HtmlOutputLabel questionLabel = new HtmlOutputLabel();
		HtmlInputText questionInputText = new HtmlInputText();
		HtmlGraphicImage questionSelectImage =  new HtmlGraphicImage();
		HtmlInputHidden questionNumberHidden = new HtmlInputHidden();
		HtmlInputHidden questionNumberStaticHidden = new HtmlInputHidden();
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		// set the question description to the question text
		questionInputText.setStyleClass("formInputFieldForReference");
		
		questionInputText.setId("questionInputText" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		questionInputText.setValue(childQuestionnaireBO.getQuestionDescription());
		questionInputText.setOnmouseover("setTitle(this)");
		//questionInputText.setReadonly(true);
        ValueBinding valForQuestion = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.questionInputMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        questionInputText.setValueBinding("value", valForQuestion);		
        // set the select image for the question select.
        questionSelectImage.setId("questionSelectImage" +
        		i + "_" + j);
        questionSelectImage.setUrl("../../images/select.gif");
        questionSelectImage.setStyle("cursor:hand;");
		questionSelectImage.setOnclick("ewpdModalWindow_ewpd('../adminoptionspopups/selectOneQuestionPopup.jsp'+getUrl()+'?temp='+Math.random()+'&&adminOptionId="
				+ this.adminOptionSystemId + "', "
				+ "'editQuestionnaireForm:questionInputText"
				+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
				+ "', 'editQuestionnaireForm:questionNumberHidden"
				+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
				+ "', 2, 1);showRef('"+"questionNumberHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId()+"','" +"reference"+childQuestionnaireBO.getQuestionNumber()+"');return false;");
        //questionSelectImage.setOnclick("javascript:selectQuestion('"+childQuestionnaireBO.getQuestionnaireHierarchySystemId()+"');");
		// set the question number in the dynamic hidden map.
		questionNumberHidden.setId("questionNumberHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		questionNumberHidden.setValue(
				childQuestionnaireBO.getQuestionDescription() 
				+ "~" + Integer.toString(childQuestionnaireBO.getQuestionNumber()));
        ValueBinding valForHiddenQuestionNumber = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenQuestionNumberMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        questionNumberHidden.setValueBinding("value", valForHiddenQuestionNumber);
        
        // set the question number in the static hidden map.
        questionNumberStaticHidden.setId("questionNumberStaticHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
        questionNumberStaticHidden.setValue(
        		childQuestionnaireBO.getQuestionDescription() 
				+ "~" + Integer.toString(childQuestionnaireBO.getQuestionNumber()));
        ValueBinding valForStaticHiddenQuestionNumber = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenQuestionNumberStaticMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        questionNumberStaticHidden.setValueBinding("value", valForStaticHiddenQuestionNumber);
		
		questionLabel.getChildren().add(questionInputText);
		questionLabel.getChildren().add(space);
		questionLabel.getChildren().add(questionSelectImage);
		questionLabel.getChildren().add(questionNumberHidden);
		questionLabel.getChildren().add(questionNumberStaticHidden);
		return questionLabel;
	}
	
	/**
	 * Method to generate the reference label
	 * @return
	 */
	private HtmlOutputLabel getReferenceLabelForPanel(ChildQuestionnaireBO childQuestionnaireBO, int i, int j){
		HtmlOutputLabel referenceLabel = new HtmlOutputLabel();
		HtmlOutputText reference = new HtmlOutputText();

		reference.setValue(childQuestionnaireBO.getReferenceDescription());
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		
        ValueBinding valForHiddenReferenceId = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenReferenceIdMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        

		HtmlInputHidden referenceInputText = new HtmlInputHidden();
		referenceInputText.setValue(childQuestionnaireBO.getReferenceId());
		referenceInputText.setId("reference"+childQuestionnaireBO.getQuestionNumber());
		referenceInputText.setValueBinding("value",valForHiddenReferenceId);

		referenceLabel.getChildren().add(reference);
		 referenceLabel.getChildren().add(referenceInputText); 
        referenceLabel.getChildren().add(space);
        
		return referenceLabel;
	}
	
	/**
	 * Method to generate the lineOfBusiness label
	 * @return
	 */
	private HtmlOutputLabel getLineOfBusinessLabelForPanel(
			ChildQuestionnaireBO childQuestionnaireBO, boolean isDomainReadOnly, int i, int j){
		HtmlOutputLabel lineOfBusinessLabel = new HtmlOutputLabel();
		HtmlInputText lobInputText = null;
		HtmlGraphicImage lobSelectImage =  null;
		HtmlInputHidden lobHidden = new HtmlInputHidden();
		String lob = null;
		String lobCommaSeperated = null;
		HtmlOutputText lobOutputText = null;
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		
		if(null != childQuestionnaireBO.getLob() && 
				!childQuestionnaireBO.getLob().isEmpty()){
			lob = WPDStringUtil.getTildaString(childQuestionnaireBO.getLob());
			lobCommaSeperated = WPDStringUtil.getCommaSeperatedString
										(childQuestionnaireBO.getLob());
		}
		if(isDomainReadOnly){
			lobOutputText = new HtmlOutputText();
			lobOutputText.setValue(lobCommaSeperated);
			lobOutputText.setId("lobOutputText" + childQuestionnaireBO.
									getQuestionnaireHierarchySystemId());
		}else{		
		// set the lob description to the question text	
			lobInputText = new HtmlInputText();
			lobInputText.setStyleClass("formInputField");
			lobInputText.setId("lobInputText" + 
					childQuestionnaireBO.getQuestionnaireHierarchySystemId());
			lobInputText.setValue(lobCommaSeperated);
			lobInputText.setOnmouseover("setTitle(this)");
			lobInputText.setStyle("width:80px;");
			//lobInputText.setReadonly(true);
	        ValueBinding valForLOBInputText = 
				FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
						"#{editQuestionnaireBackingBean.lineOfBusinessInputMap["
						+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
						+ "]}");
	        lobInputText.setValueBinding("value", valForLOBInputText);
			
	        // set the select image for the lob select.
			lobSelectImage = new HtmlGraphicImage();
			lobSelectImage.setId("lobSelectImage" +
					i + "_" + j);
			lobSelectImage.setUrl("../../images/select.gif");
			lobSelectImage.setStyle("cursor:hand;");
			lobSelectImage.setOnclick("ewpdModalWindow_ewpd_Domain('../popups/lineOfBusinessPopup.jsp'+getUrl()+'?lookUpAction=1&parentCatalog=Line of Business', "
					+ "'editQuestionnaireForm:lobInputText"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 'editQuestionnaireForm:lobHidden"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 2, 2)");
		}
		
		// set the lob in the dynamic hidden map.
		lobHidden.setId("lobHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		lobHidden.setValue(lob);
        ValueBinding valForLOB = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenLOBMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        lobHidden.setValueBinding("value", valForLOB);
		
        if(isDomainReadOnly){
        	lineOfBusinessLabel.getChildren().add(lobOutputText);
        }else{
	        lineOfBusinessLabel.getChildren().add(lobInputText);
	        lineOfBusinessLabel.getChildren().add(space);
	        lineOfBusinessLabel.getChildren().add(lobSelectImage);
        }
        lineOfBusinessLabel.getChildren().add(lobHidden);
		return lineOfBusinessLabel;
	}
	
	/**
	 * Method to generate the businessEntity label
	 * @return
	 */
	private HtmlOutputLabel getBusinessEntityLabelForPanel(
			ChildQuestionnaireBO childQuestionnaireBO, boolean isDomainReadOnly, int i, int j){
		HtmlOutputLabel businessEntityLabel = new HtmlOutputLabel();
		HtmlInputText businessEntityInputText = null;
		HtmlGraphicImage businessEntitySelectImage =  null;
		HtmlInputHidden businessEntityHidden = new HtmlInputHidden();
		String businessEntity = null;
		String businessEntityCommaSeperated = null;
		HtmlOutputText businessEntityOutputText = null;
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		
		if(null != childQuestionnaireBO.getBusinessEntity() 
				&& !childQuestionnaireBO.getBusinessEntity().isEmpty()){
			businessEntity = WPDStringUtil.getTildaString
						(childQuestionnaireBO.getBusinessEntity());
			businessEntityCommaSeperated = WPDStringUtil.
					getCommaSeperatedString(
							childQuestionnaireBO.getBusinessEntity());
		}
		
		if(isDomainReadOnly){
			businessEntityOutputText = new HtmlOutputText();
			businessEntityOutputText.setValue(businessEntityCommaSeperated);
			businessEntityOutputText.setId("businessEntityOutputText" + 
					childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		}else{		
			// set the businessEntity description to the question text	
			businessEntityInputText = new HtmlInputText();
			businessEntityInputText.setStyleClass("formInputField");	
			businessEntityInputText.setId("businessEntityInputText" + 
					childQuestionnaireBO.getQuestionnaireHierarchySystemId());
			businessEntityInputText.setValue(businessEntityCommaSeperated);
			businessEntityInputText.setOnmouseover("setTitle(this)");
			//businessEntityInputText.setReadonly(true);
			businessEntityInputText.setStyle("width:80px;");
	        ValueBinding valForBusinessEntityInput = 
				FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
						"#{editQuestionnaireBackingBean.businessEntityInputMap["
						+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
						+ "]}");
	        businessEntityInputText.setValueBinding("value", valForBusinessEntityInput);
			
	        // set the select image for the businessEntity select.
			businessEntitySelectImage = new HtmlGraphicImage();
			businessEntitySelectImage.setId("businessEntitySelectImage" +
					i + "_" + j);
			businessEntitySelectImage.setUrl("../../images/select.gif");
			businessEntitySelectImage.setStyle("cursor:hand;");
			businessEntitySelectImage.setOnclick("ewpdModalWindow_ewpd_Domain('../popups/businessEntityPopup.jsp'+getUrl()+'?lookUpAction=1&parentCatalog=Business Entity', "
					+ "'editQuestionnaireForm:businessEntityInputText"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 'editQuestionnaireForm:businessEntityHidden"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 2, 2)");
		}
		
		// set the businessEntity in the dynamic hidden map.
		businessEntityHidden.setId("businessEntityHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		businessEntityHidden.setValue(businessEntity);
        ValueBinding valForBusinessEntity = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenBusinessEntityMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        businessEntityHidden.setValueBinding("value", valForBusinessEntity);
		
        if(isDomainReadOnly){
        	businessEntityLabel.getChildren().add(businessEntityOutputText);
        }else{
	        businessEntityLabel.getChildren().add(businessEntityInputText);
	        businessEntityLabel.getChildren().add(space);
	        businessEntityLabel.getChildren().add(businessEntitySelectImage);
        }
        businessEntityLabel.getChildren().add(businessEntityHidden);
		return businessEntityLabel;
	}
	
	/**
	 * Method to generate the businessGroup label
	 * @return
	 */
	private HtmlOutputLabel getBusinessGroupLabelForPanel(
			ChildQuestionnaireBO childQuestionnaireBO, boolean isDomainReadOnly, int i, int j){
		HtmlOutputLabel businessGroupLabel = new HtmlOutputLabel();
		HtmlInputText businessGroupInputText = null;
		HtmlGraphicImage businessGroupSelectImage =  null;
		HtmlInputHidden businessGroupHidden = new HtmlInputHidden();
		String businessGroup = null;
		String businessGroupCommaSeperated = null;
		HtmlOutputText businessGroupOutputText = null;
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		
		if(null != childQuestionnaireBO.getBusinessGroup() && 
				!childQuestionnaireBO.getBusinessGroup().isEmpty()){
			businessGroup = WPDStringUtil.getTildaString
							(childQuestionnaireBO.getBusinessGroup());
			businessGroupCommaSeperated = WPDStringUtil.
					getCommaSeperatedString(
							childQuestionnaireBO.getBusinessGroup());
		}
		
		if(isDomainReadOnly){
			businessGroupOutputText = new HtmlOutputText();
			businessGroupOutputText.setValue(businessGroupCommaSeperated);
			businessGroupOutputText.setId("businessGroupOutputText" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		}else{
			// set the businessGroup description to the question text	
			businessGroupInputText = new HtmlInputText();
			businessGroupInputText.setStyleClass("formInputField");		
			businessGroupInputText.setId("businessGroupInputText" + 
					childQuestionnaireBO.getQuestionnaireHierarchySystemId());
			businessGroupInputText.setValue(businessGroupCommaSeperated);
			businessGroupInputText.setOnmouseover("setTitle(this)");
			businessGroupInputText.setStyle("width:80px;");
			//businessGroupInputText.setReadonly(true);
	        ValueBinding valForBusinessGroupInput = 
				FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
						"#{editQuestionnaireBackingBean.businessGroupInputMap["
						+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
						+ "]}");
	        businessGroupInputText.setValueBinding("value", valForBusinessGroupInput);
			
	        // set the select image for the businessGroup select.
			businessGroupSelectImage = new HtmlGraphicImage();
			businessGroupSelectImage.setId("businessGroupSelectImage" +
					i + "_" + j);
			businessGroupSelectImage.setUrl("../../images/select.gif");
			businessGroupSelectImage.setStyle("cursor:hand;");
			businessGroupSelectImage.setOnclick("ewpdModalWindow_ewpd_Domain('../popups/businessGroupPopup.jsp'+getUrl()+'?lookUpAction=1&parentCatalog=business group', "
					+ "'editQuestionnaireForm:businessGroupInputText"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 'editQuestionnaireForm:businessGroupHidden"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 2, 2)");
		}
		
		// set the businessGroup in the dynamic hidden map.
		businessGroupHidden.setId("businessGroupHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		businessGroupHidden.setValue(businessGroup);
        ValueBinding valForBusinessGroup = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenBusinessGroupMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        businessGroupHidden.setValueBinding("value", valForBusinessGroup);
		
        if(isDomainReadOnly){
        	businessGroupLabel.getChildren().add(businessGroupOutputText);
        }else{
	        businessGroupLabel.getChildren().add(businessGroupInputText);
	        businessGroupLabel.getChildren().add(space);
	        businessGroupLabel.getChildren().add(businessGroupSelectImage);
        }
        businessGroupLabel.getChildren().add(businessGroupHidden);
        //Parent Required Status Hidden field for the child Questionnaire is also set.
        businessGroupLabel.getChildren().add(getParentRequiredHiddenForPanel(childQuestionnaireBO));
		return businessGroupLabel;
	}
	/**
	 * Method to generate the lineOfBusiness label
	 * @return
	 */
	private HtmlOutputLabel getMarketBusinessUnitLabelForPanel(
			ChildQuestionnaireBO childQuestionnaireBO, boolean isDomainReadOnly, int i, int j){
		HtmlOutputLabel marketBusinessUnitLabel = new HtmlOutputLabel();
		HtmlInputText mbuInputText = null;
		HtmlGraphicImage mbuSelectImage =  null;
		HtmlInputHidden mbuHidden = new HtmlInputHidden();
		String mbu = null;
		String mbuCommaSeperated = null;
		HtmlOutputText mbuOutputText = null;
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		
		if(null != childQuestionnaireBO.getMarketBusinessUnit() && 
				!childQuestionnaireBO.getMarketBusinessUnit().isEmpty()){
			mbu = WPDStringUtil.getTildaString(childQuestionnaireBO.getMarketBusinessUnit());
			mbuCommaSeperated = WPDStringUtil.getCommaSeperatedString
										(childQuestionnaireBO.getMarketBusinessUnit());
		}
		if(isDomainReadOnly){
			mbuOutputText = new HtmlOutputText();
			mbuOutputText.setValue(mbuCommaSeperated);
			mbuOutputText.setId("mbuOutputText" + childQuestionnaireBO.
									getQuestionnaireHierarchySystemId());
		}else{		
		// set the lob description to the question text	
			mbuInputText = new HtmlInputText();
			mbuInputText.setStyleClass("formInputField");
			mbuInputText.setId("mbuInputText" + 
					childQuestionnaireBO.getQuestionnaireHierarchySystemId());
			mbuInputText.setValue(mbuCommaSeperated);
			mbuInputText.setOnmouseover("setTitle(this)");
			mbuInputText.setStyle("width:80px;");
			//lobInputText.setReadonly(true);
	        ValueBinding valForMBUInputText = 
				FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
						"#{editQuestionnaireBackingBean.marketBusinessUnitInputMap["
						+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
						+ "]}");
	        mbuInputText.setValueBinding("value", valForMBUInputText);
			
	        // set the select image for the lob select.
			mbuSelectImage = new HtmlGraphicImage();
			mbuSelectImage.setId("mbuSelectImage" +
					i + "_" + j);
			mbuSelectImage.setUrl("../../images/select.gif");
			mbuSelectImage.setStyle("cursor:hand;");
			mbuSelectImage.setOnclick("ewpdModalWindow_ewpd_Domain('../popups/marketBusinessUnit.jsp'+getUrl()+'?lookUpAction=1&parentCatalog=Market Business Unit', "
					+ "'editQuestionnaireForm:mbuInputText"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 'editQuestionnaireForm:mbuHidden"
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "', 2, 2)");
		}
		
		// set the lob in the dynamic hidden map.
		mbuHidden.setId("mbuHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		mbuHidden.setValue(mbu);
        ValueBinding valForMBU = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenmarketBusinessUnitMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        mbuHidden.setValueBinding("value", valForMBU);
		
        if(isDomainReadOnly){
        	marketBusinessUnitLabel.getChildren().add(mbuOutputText);
        }else{
        	marketBusinessUnitLabel.getChildren().add(mbuInputText);
        	marketBusinessUnitLabel.getChildren().add(space);
        	marketBusinessUnitLabel.getChildren().add(mbuSelectImage);
        }
        marketBusinessUnitLabel.getChildren().add(mbuHidden);
		return marketBusinessUnitLabel;
	}
	/**
	 * Method to generate the delete label
	 * @return
	 */
	private HtmlOutputLabel getDeleteLabelForPanel(ChildQuestionnaireBO childQuestionnaireBO){
		HtmlOutputLabel deleteLabel = new HtmlOutputLabel();
		HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
		
		deleteCheckBox.setId("deleteCheckBox" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		deleteCheckBox.setValue(Boolean.FALSE);
        ValueBinding valForDelete = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.deleteMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        deleteCheckBox.setValueBinding("value", valForDelete);	
        deleteCheckBox.setOnclick("javascript:enableDeleteIfSelected(this);");
		
		deleteLabel.getChildren().add(deleteCheckBox);
		
		return deleteLabel;
	}
	
	/**
	 * Method to generate the list of child questions to be added.
	 * @return
	 */
	private List getListOfChildQuestions(){
		List childQuestionsBOToInsert = null;
		List selectedQuestions = null;
		List selectedReferences = null;
		ChildQuestionnaireBO childQuestionnaireBO = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		HashMap childQuestionnairesMap = null;
		int maxSequence = 0;
		List seqChildQuestionnaires = null;
		int selectedQstnSize = 0;
		// get the selected question ids
		if(null != this.getSelectedQuestionId()){
			selectedQuestions = WPDStringUtil.
				getListFromTildaString(this.getSelectedQuestionId(), 4, 2, 2);
			selectedReferences = WPDStringUtil.
			getListFromTildaString(this.getSelectedQuestionId(), 4, 3, 2);
		}
		
		// iterate the question ids and form the list of child quesiton bo's.
		if(null != selectedQuestions && !selectedQuestions.isEmpty()){
			selectedQstnSize = selectedQuestions.size();
			for(int i = 0; i < selectedQstnSize; i++){
				if(null != selectedQuestions.get(i)){
					childQuestionnaireBO = new ChildQuestionnaireBO();
					childQuestionnaireBO.setAdminOptionSystemId
									(this.adminOptionSystemId);
					if("null".equalsIgnoreCase((String)selectedReferences.get(i)))
						childQuestionnaireBO.setReferenceId(null);
					else
					childQuestionnaireBO.setReferenceId((String)selectedReferences.get(i));
					if(null != this.selectedAnswerId){
						childQuestionnaireBO.setAnswerId(
								Integer.parseInt(this.selectedAnswerId));
						// get the max sequence
						if(maxSequence == 0){
							if(null != getSession().getAttribute("ChildQuestionnairesMap")){
								childQuestionnairesMap = (HashMap) getSession().
											getAttribute("ChildQuestionnairesMap");
								seqChildQuestionnaires = (List) childQuestionnairesMap.
											get(String.valueOf(this.selectedAnswerId));
								if(null != seqChildQuestionnaires && !seqChildQuestionnaires.isEmpty()){
									maxSequence = seqChildQuestionnaires.size();
								}
							}
						}
					}
					// get the parent questionnarie details from the session.
					if(null != getSession() && 	null != getSession().
							getAttribute("ParentQuestionnaireDetails")){
						rootQuestionnaireBO = (RootQuestionnaireBO) getSession().
								getAttribute("ParentQuestionnaireDetails");
						if(null != rootQuestionnaireBO){
							childQuestionnaireBO.setLob(
									WPDStringUtil.getListOfDomainIds(
											rootQuestionnaireBO.getLob()));
							childQuestionnaireBO.setBusinessEntity
								(WPDStringUtil.getListOfDomainIds(
										rootQuestionnaireBO.getBusinessEntity()));
							childQuestionnaireBO.setBusinessGroup
								(WPDStringUtil.getListOfDomainIds(
										rootQuestionnaireBO.getBusinessGroup()));
							childQuestionnaireBO.setMarketBusinessUnit(WPDStringUtil.getListOfDomainIds(
									rootQuestionnaireBO.getMarketBusinessUnit()));
						}
					}
					childQuestionnaireBO.setParentQuestionnaireId
								(this.rootQuestionnaireHierarchySystemId);
					childQuestionnaireBO.setQuestionNumber(
						Integer.parseInt(selectedQuestions.get(i).toString()));
					childQuestionnaireBO.setSequenceNumber(++maxSequence); 
					if(null == childQuestionsBOToInsert)
						childQuestionsBOToInsert = new ArrayList();
					childQuestionsBOToInsert.add(childQuestionnaireBO);
				}
			}
		}
		
		return childQuestionsBOToInsert;
	}
	
	/**
	 * Method to generate the childs list corresponding to the answers.
	 * @return
	 */
	private Map generateChildsByAnswers(){
		Map childsByAnswers = null;
		Set questionnaireHierarchyKeySet = null;
		Iterator questionnaireHierarchyIterator = null;
		Object questionnaireHierachyKey = null;
		ChildQuestionnaireBO childQuestionnaireBO = null;
		List childQuestions = null;
		
		// iterate the hiddenQuestionnaireHierarchyStaticMap
		if(null != this.hiddenQuestionnaireHierarchyStaticMap){
			questionnaireHierarchyKeySet = 
				this.hiddenQuestionnaireHierarchyStaticMap.keySet();
				questionnaireHierarchyIterator = 
					questionnaireHierarchyKeySet.iterator();
				while(questionnaireHierarchyIterator.hasNext()){
					questionnaireHierachyKey = 
						questionnaireHierarchyIterator.next();
					// generate the child bo corresponding to the questionnaire hierarchy id.
					if(!this.deleteFlag){
						childQuestionnaireBO = generateChildQuestionnaireBO
												(questionnaireHierachyKey);
					}else{
						childQuestionnaireBO = generateChildQuestionnaireBOForDelete
												(questionnaireHierachyKey);
					}
					if(null != childQuestionnaireBO){
						if(null == childQuestions)
							childQuestions = new ArrayList();
						// add the bo to the list.
						childQuestions.add(childQuestionnaireBO);
					}
				}
		}
		
		// split the child questions list corresponding to the answer and form a map.
		if(null != childQuestions)
			childsByAnswers = splitChildByAnswers(childQuestions);
		
		return childsByAnswers;
	}
	
	/**
	 * Method to generate the child question bo corresponding the questionnaire id
	 * @param key
	 * @return
	 */
	private ChildQuestionnaireBO generateChildQuestionnaireBO(Object key){
		ChildQuestionnaireBO childQuestionnaireBO = null;
		Long keyValue = null;
		List lob = null;
		List businessEntity = null;
		List businessGroup = null;
		List marketBusinessUnit = null;
		List businessDomains = null;
	
		if(null != key){
			keyValue = Long.valueOf(key.toString());
//			if(null != this.deleteMap && null != this.deleteMap.get(keyValue)){
//				if(!(Boolean.valueOf(this.deleteMap.get(keyValue).toString())).booleanValue()){
					childQuestionnaireBO = new ChildQuestionnaireBO();
					// set the required details to the bo.
					if(null != this.sequenceMap && 
							null != this.sequenceMap.get(keyValue) &&
							!"".equals(this.sequenceMap.get(keyValue).toString())){
						childQuestionnaireBO.setSequenceNumber(Integer.parseInt
									(this.sequenceMap.get(keyValue).toString()));
					}
					if(null != this.answersMap && null != this.answersMap.get(keyValue)){
						childQuestionnaireBO.setAnswerId(Integer.parseInt
								(this.answersMap.get(keyValue).toString()));
					}
					if(null != this.hiddenQuestionNumberMap && 
							null != this.hiddenQuestionNumberMap.get(keyValue)){
						if(this.hiddenQuestionNumberMap.get(keyValue).toString().split("~").length > 1)
							childQuestionnaireBO.setQuestionNumber(Integer.parseInt
									(this.hiddenQuestionNumberMap.get(keyValue).toString().split("~")[1]));
					}
					if(null != this.hiddenReferenceIdMap && 
							null != this.hiddenReferenceIdMap.get(keyValue)){
						if("null".equalsIgnoreCase(this.hiddenReferenceIdMap.get(keyValue).toString()))
							childQuestionnaireBO.setReferenceId(null);
						else
						childQuestionnaireBO.setReferenceId(
								this.hiddenReferenceIdMap.get(keyValue).toString());
					}
					if(null != this.hiddenLOBMap && 
							null != this.hiddenLOBMap.get(keyValue)){
						childQuestionnaireBO.setLob(WPDStringUtil.getListFromTildaString
								(this.hiddenLOBMap.get(keyValue).toString(), 2, 2, 2));
					}
					if(null != this.hiddenBusinessEntityMap && 
							null != this.hiddenBusinessEntityMap.get(keyValue)){
						childQuestionnaireBO.setBusinessEntity(
								WPDStringUtil.getListFromTildaString
									(this.hiddenBusinessEntityMap.
											get(keyValue).toString(), 2, 2, 2));
					}
					if(null != this.hiddenBusinessGroupMap &&
							null != this.hiddenBusinessGroupMap.get(keyValue)){
						childQuestionnaireBO.setBusinessGroup(
								WPDStringUtil.getListFromTildaString
									(this.hiddenBusinessGroupMap.
											get(keyValue).toString(),2, 2, 2));
					}
					if(null != this.hiddenmarketBusinessUnitMap &&
							null != this.hiddenmarketBusinessUnitMap.get(keyValue)){
						childQuestionnaireBO.setMarketBusinessUnit(
								WPDStringUtil.getListFromTildaString
								(this.hiddenmarketBusinessUnitMap.
										get(keyValue).toString(),2, 2, 2)
								);
					}
					/* The parent Required status for the child questionnairebo is taken from 
					 * hiddenParentRequiredMap and set */
					if(null != this.hiddenParentRequiredMap &&
							null != this.hiddenParentRequiredMap.get(keyValue) && !isQuestionChanged(keyValue)){
						childQuestionnaireBO.setParentRequired(this.hiddenParentRequiredMap.
											get(keyValue).toString());						 
					}
					childQuestionnaireBO.setQuestionnaireHierarchySystemId
										(Integer.parseInt(key.toString()));
					childQuestionnaireBO.setAdminOptionSystemId(this.adminOptionSystemId);
					childQuestionnaireBO.setParentQuestionnaireId
							(this.rootQuestionnaireHierarchySystemId);
					if(null != this.hiddenQuestionNumberMap && 
							null != this.hiddenQuestionNumberMap.get(keyValue) &&
							null != this.hiddenQuestionNumberStaticMap &&
							null != this.hiddenQuestionNumberStaticMap.get(keyValue) &&
							this.hiddenQuestionNumberMap.get(keyValue).toString().split("~").length > 1 &&
							this.hiddenQuestionNumberStaticMap.get(keyValue).toString().split("~").length > 1){
						if(Integer.parseInt(this.hiddenQuestionNumberMap.get(keyValue).toString().split("~")[1])
								!= Integer.parseInt(this.hiddenQuestionNumberStaticMap.get(keyValue).toString().split("~")[1])){
							childQuestionnaireBO.setDeleteChildFlag('T');
						}else{
							childQuestionnaireBO.setDeleteChildFlag('F');					
						}
					}else{
						childQuestionnaireBO.setDeleteChildFlag('F');	
					}
/*				}else{
					if(null == childQuestionnairesToDeleted){
						childQuestionnairesToDeleted = key.toString();
					}
					else{
						childQuestionnairesToDeleted = 
							childQuestionnairesToDeleted + "~" + key;
					}
				}
			}
*/		}
		return childQuestionnaireBO;
	}
	
	/**
	 * Method to get the questionnaire ids to be deleted.
	 * @param key
	 * @return
	 */
	private ChildQuestionnaireBO generateChildQuestionnaireBOForDelete(Object key){
		ChildQuestionnaireBO childQuestionnaireBO = null;
		ChildQuestionnaireBO childQuestionnaireBOForCompare = null;
		Long keyValue = null;
		List childQuestionnaires = null;
		if(null != key){
			keyValue = Long.valueOf(key.toString());
			if(null != this.deleteMap && null != this.deleteMap.get(keyValue)){
				if(!(Boolean.valueOf(this.deleteMap.get(keyValue).toString())).booleanValue()){
					childQuestionnaireBO = new ChildQuestionnaireBO();
					if(null != getSession().getAttribute("ChildQuestionnairesList")){
						childQuestionnaires = (List) getSession().getAttribute("ChildQuestionnairesList");
						if(null != childQuestionnaires && !childQuestionnaires.isEmpty()){
							for(int i = 0; i < childQuestionnaires.size(); i++){
								childQuestionnaireBOForCompare = 
									(ChildQuestionnaireBO) childQuestionnaires.get(i);
								if(childQuestionnaireBOForCompare.getQuestionnaireHierarchySystemId() 
										== Integer.parseInt(key.toString())){
									childQuestionnaireBO.setSequenceNumber
										(childQuestionnaireBOForCompare.getSequenceNumber());
									childQuestionnaireBO.setAnswerId
										(childQuestionnaireBOForCompare.getAnswerId());
									childQuestionnaireBO.setQuestionNumber
										(childQuestionnaireBOForCompare.getQuestionNumber());
									childQuestionnaireBO.setReferenceId
										(childQuestionnaireBOForCompare.getReferenceId());
								}
							}
						}
					}
					childQuestionnaireBO.setQuestionnaireHierarchySystemId
										(Integer.parseInt(key.toString()));
					childQuestionnaireBO.setAdminOptionSystemId(this.adminOptionSystemId);
					childQuestionnaireBO.setParentQuestionnaireId
							(this.rootQuestionnaireHierarchySystemId);
				}else{
					if(null == childQuestionnairesToDeleted){
						childQuestionnairesToDeleted = key.toString();
					}
					else{
						childQuestionnairesToDeleted = 
							childQuestionnairesToDeleted + "~" + key;
					}
				}
			}
		}
		return childQuestionnaireBO;
	}
	
	/**
	 * Method to validate the mandatory fields while adding the dependent questions.
	 * @return
	 */
	private boolean validateMandatoryFieldsForAdd(){
		boolean isValidated = true;
		if(null == this.selectedQuestionId 
				|| "".equals(this.selectedQuestionId.trim())){
			isValidated = false;
			this.requiredQuestion = true;
		}
		if(null == this.selectedAnswerId ||
				"".equals(this.selectedAnswerId.trim())){
			isValidated = false;
			this.requiredAnswer = true;
		}
		return isValidated;
	}
	
	/**
	 * Reorder the sequence of the lists and converting to a single list.
	 * @param childQuestionnairesByAnswers
	 * @return
	 */
	private List reOrderAndConvertToList(Map childQuestionnairesByAnswers){
		List reOrderedList = null;
		Set keySet = null;
		Iterator keySetIterator = null;
		List childQuestionnaires = null;
		SequenceUtil sequenceUtil = null;
		Object key = null;
		List registeredAnswers = null;
		List registeredChildQuestionnaires = null;
		HashMap registeredChildMap = null;
		List listToReOrder = null;
		List listNotToReOrder = null;
		ChildQuestionnaireBO registeredChildBO = null;
		ChildQuestionnaireBO notRegisteredChildBO = null;
		boolean flagNotToReOrder = false;
		boolean flagNewAnswerOrder = false;
		ChildQuestionnaireBO sequenceUpdateBO = null;
		int regAnswerSize = 0;
		int childSize = 0;
		int size = 0;
		int size1 = 0;
		int size2 = 0;
		HashMap childQuestionnairesMap = 
			new HashMap(childQuestionnairesByAnswers);

		if(null != getSession().getAttribute("registeredAnswers")){
			registeredAnswers = (List) getSession().getAttribute("registeredAnswers");
		}
		if(null != getSession().getAttribute("ChildQuestionnairesMap")){
			registeredChildMap = (HashMap) getSession().getAttribute("ChildQuestionnairesMap");
		}
		keySet = childQuestionnairesMap.keySet();
		keySetIterator = keySet.iterator();
		while(keySetIterator.hasNext()){
			listNotToReOrder = null;
			listToReOrder = null;
			flagNotToReOrder = true;
			flagNewAnswerOrder = true;
			key = keySetIterator.next();
			// get the child questionnaires list corresponding to the answers.
			childQuestionnaires = (List) childQuestionnairesMap.get(key);
			if(null == sequenceUtil){
				sequenceUtil = new SequenceUtil();
			}
			// reorder the list.
			if(null != registeredAnswers && !registeredAnswers.isEmpty()){
				regAnswerSize = registeredAnswers.size();
				for(int j = 0; j < regAnswerSize; j++){
					if(String.valueOf(key).trim().equals(
							String.valueOf(registeredAnswers.get(j)).trim())){
						flagNewAnswerOrder = false;
						if(null != registeredChildMap && !registeredChildMap.isEmpty()){
							registeredChildQuestionnaires = (List) registeredChildMap.get(key);
							childSize = childQuestionnaires.size();
							for(int p = 0; p < childSize; p++){
								notRegisteredChildBO = (ChildQuestionnaireBO) childQuestionnaires.get(p);
								flagNotToReOrder = true;
								size1 = registeredChildQuestionnaires.size();
								for(int q = 0; q < size1; q++){
									registeredChildBO = (ChildQuestionnaireBO) registeredChildQuestionnaires.get(q);
									if(registeredChildBO.getQuestionnaireHierarchySystemId() 
											== notRegisteredChildBO.getQuestionnaireHierarchySystemId()){
										if(null == listToReOrder)
											listToReOrder = new ArrayList();
										listToReOrder.add(notRegisteredChildBO);
										flagNotToReOrder = false;
									}
								}
								if(flagNotToReOrder){
									if(null == listNotToReOrder)
										listNotToReOrder = new ArrayList();
									listNotToReOrder.add(notRegisteredChildBO);
								}
							}
							// reorder
							if(null != listToReOrder && !listToReOrder.isEmpty())
								listToReOrder = sequenceUtil.reOrderObjects(listToReOrder, String.valueOf(key));
							if(null != listNotToReOrder && !listNotToReOrder.isEmpty()){
								size2 = listNotToReOrder.size();
								if(null != listToReOrder && !listToReOrder.isEmpty()){
									for(int r = 0; r < size2; r++){
										sequenceUpdateBO = (ChildQuestionnaireBO) listNotToReOrder.get(r);
										sequenceUpdateBO.setSequenceNumber(listToReOrder.size() + 1);
										listToReOrder.add(sequenceUpdateBO);
									}
								}
								// Added for sequence number fix.
								else{
									listToReOrder = new ArrayList();
									for(int r = 0; r < size2; r++){
										sequenceUpdateBO = (ChildQuestionnaireBO) listNotToReOrder.get(r);
										sequenceUpdateBO.setSequenceNumber(listToReOrder.size() + 1);
										listToReOrder.add(sequenceUpdateBO);
									}
								}
							}
						}
						if(null != listToReOrder && !listToReOrder.isEmpty())
							childQuestionnaires = listToReOrder;				
					}
				}
			}
			if(flagNewAnswerOrder){
				if(null != childQuestionnaires && !childQuestionnaires.isEmpty()){
					size = childQuestionnaires.size();
					for(int x = 0; x < size; x++){
						if(null == sequenceUpdateBO)
							sequenceUpdateBO = new ChildQuestionnaireBO();
						sequenceUpdateBO = (ChildQuestionnaireBO) childQuestionnaires.get(x);
						sequenceUpdateBO.setSequenceNumber(x + 1);
					}
				}
			}
			if(null == reOrderedList){
				reOrderedList = new ArrayList();
			}
			size = childQuestionnaires.size();
			// put it in a list.
			for(int i = 0; i < size; i++){
				reOrderedList.add(childQuestionnaires.get(i));
			}
		}
		// remove the registered answers from the session.
		getSession().removeAttribute("registeredAnswers");
		return reOrderedList;
	}
	
	/**
	 * Method to registed the sequence while retrieving the child questionnaires.
	 * @param childQuestionnairesMap
	 */
	private void registerSequence(Map childQuestionnairesMap){
		SequenceUtil sequenceUtil = new SequenceUtil();
		Set keySet = null;
		Iterator keySetIterator = null;
		List childQuestionnairesList = null;
		Object key = null;
		HashMap childQuestionnairesHashMap = new HashMap(childQuestionnairesMap);
		List registeredAnswersKey = null;
		
		keySet = childQuestionnairesHashMap.keySet();
		keySetIterator = keySet.iterator();
		while(keySetIterator.hasNext()){
			key = keySetIterator.next();
			childQuestionnairesList = (List) childQuestionnairesHashMap.
												get(key);
			Collections.sort(childQuestionnairesList);
			// reqister the list of childQuestionnaires with respect to 
			// answers for sequence registration.
			sequenceUtil.registerObjects(childQuestionnairesList, 
									"questionnaireHierarchySystemId", 
									"sequenceNumber", String.valueOf(key));
			if(null == registeredAnswersKey)
				registeredAnswersKey = new ArrayList();
			registeredAnswersKey.add(key);			
		}
		if(null != registeredAnswersKey){
			// set the registered Answers key in the sesion
			getSession().setAttribute("registeredAnswers", registeredAnswersKey);
		}
	}
	
	/**
	 * Method to validate for the mandatory fields in the child Questionnaires.
	 *
	 */
	private boolean validateMandatoryFieldsForSave(){
		boolean isVaild = true;
		boolean isSequenceValid = true;
		boolean isAnswerValid = true;
		boolean isQuestionValid = true;
		boolean isReferenceValid = true;
		boolean isLOBValid = true;
		boolean isBusinessEntityValid = true;
		boolean isBusinessGroupValid = true;
		boolean isMarketBusinessUnitValid = true;
		Set questionnaireHierarchyKeySet = null;
		Iterator questionnaireHierarchyIterator = null;
		Object questionnaireHierachyKey = null;

		if(null != this.hiddenQuestionnaireHierarchyStaticMap){
			questionnaireHierarchyKeySet = 
				this.hiddenQuestionnaireHierarchyStaticMap.keySet();
			questionnaireHierarchyIterator = 
				questionnaireHierarchyKeySet.iterator();
			while(questionnaireHierarchyIterator.hasNext()){
				questionnaireHierachyKey = 
					questionnaireHierarchyIterator.next();
				if((null == this.sequenceMap || 
						null == this.sequenceMap.get(questionnaireHierachyKey) ||
						"".equals(this.sequenceMap.get(questionnaireHierachyKey).toString()))
						&& isSequenceValid){
						isVaild = false;
						isSequenceValid = false;
						if(null == validationMessages)
							validationMessages = new ArrayList();						
						validationMessages.add(new ErrorMessage("questionnaire.sequence.mandatory.fields"));
				}
				if((null == this.answersMap ||
						null == this.answersMap.get(questionnaireHierachyKey) ||
						"".equals(this.answersMap.get(questionnaireHierachyKey).toString()))
						&& isAnswerValid){
					isVaild = false;
					isAnswerValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.answer.mandatory.fields"));
				}
				if((null == this.hiddenQuestionNumberMap || 
						null == this.hiddenQuestionNumberMap.get(questionnaireHierachyKey) ||
						"".equals(this.hiddenQuestionNumberMap.get(questionnaireHierachyKey).toString()))
						&& isQuestionValid){
					isVaild = false;
					isQuestionValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.question.mandatory.fields"));
				}
//				if((null == this.hiddenReferenceIdMap || 
//						null == this.hiddenReferenceIdMap.get(questionnaireHierachyKey) ||
//						"".equals(this.hiddenReferenceIdMap.get(questionnaireHierachyKey).toString()))
//						&& isReferenceValid){
//					isVaild = false;
//					isReferenceValid = false;
//					if(null == validationMessages)
//						validationMessages = new ArrayList();						
//					validationMessages.add(new ErrorMessage("questionnaire.reference.mandatory.fields"));
//				}
				if((null == this.hiddenLOBMap ||
						null == this.hiddenLOBMap.get(questionnaireHierachyKey) ||
						"".equals(this.hiddenLOBMap.get(questionnaireHierachyKey).toString()))
						&& isLOBValid){
					isVaild = false;
					isLOBValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.lob.mandatory.fields"));
				}
				if((null == this.hiddenBusinessEntityMap ||
						null == this.hiddenBusinessEntityMap.get(questionnaireHierachyKey) ||
						"".equals(this.hiddenBusinessEntityMap.get(questionnaireHierachyKey).toString()))
						&& isBusinessEntityValid){
					isVaild = false;
					isBusinessEntityValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.businessentity.mandatory.field"));
				}
				if((null == this.hiddenBusinessGroupMap ||
						null == this.hiddenBusinessGroupMap.get(questionnaireHierachyKey) ||
						"".equals(this.hiddenBusinessGroupMap.get(questionnaireHierachyKey).toString()))
						&& isBusinessGroupValid){
					isVaild = false;
					isBusinessGroupValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.businessgroup.mandatory.fields"));
				}
				if((null == this.hiddenmarketBusinessUnitMap ||
						null == this.hiddenmarketBusinessUnitMap.get(questionnaireHierachyKey) ||
						"".equals(this.hiddenmarketBusinessUnitMap.get(questionnaireHierachyKey).toString()))
						&& isMarketBusinessUnitValid){
					isVaild = false;
					isMarketBusinessUnitValid = false;
					if(null == validationMessages)
						validationMessages = new ArrayList();						
					validationMessages.add(new ErrorMessage("questionnaire.businessgroup.mandatory.fields"));
				}
			}
		}
		if(!isVaild){
			addAllMessagesToRequest(validationMessages);
		}
		return isVaild;
	}
	
	/**
	 * Method to clear the session and class attributes.
	 *
	 */
	private void clearSessionAndClassAttributes(){
		this.selectedQuestionId = null;
		this.adminOptionName = null;
		this.adminOptionSystemId = 0;
		this.childQuestionnairesToDeleted = null;
		this.childQuestionsPanel = null;
		this.headerPanel = null;
		this.adminOptionVersion = 0;
		this.possibleAnswerList = null;
		getSession().removeAttribute("ChildQuestionnairesMap");
		getSession().removeAttribute("ParentQuestionnaireDetails");
		getSession().removeAttribute("registeredAnswers");
		getSession().removeAttribute("AdminOptionName");
		//getSession().removeAttribute("ChildQuestionnairesList");
	}
	
	/**
	 * Method to add selected question to the db.
	 * @return
	 */
	public String addDependentQuestions(){
		List questionsToInsert = null;
		boolean isValidated = false;
		PersistChildQuestionnaireRequest request = null;
		PersistChildQuestionnaireResponse response = null;
		
		// validate the mandatory fields.
		isValidated = validateMandatoryFieldsForAdd();
		
		if(!isValidated){
			validationMessages = new ArrayList();
			validationMessages.add(new ErrorMessage("questionnaire.add.mandatory.fields"));
			addAllMessagesToRequest(validationMessages);
			return "";
		}
		// get the required details and set it in bo.
		questionsToInsert = getListOfChildQuestions();
		

		
		if(null != questionsToInsert && !questionsToInsert.isEmpty()){
			// set the list of bo in the request.
			request = (PersistChildQuestionnaireRequest) this.getServiceRequest
							(ServiceManager.PERSIST_CHILD_QUESTIONNAIRE_REQUEST); 
			request.setChildQuestionnaires(questionsToInsert);
			request.setInsertFlag(true);
			request.setAdminOptionName(this.adminOptionName);
			request.setAdminOptionVersion(this.adminOptionVersion);
			
			// get the message from the response and display.
			response = (PersistChildQuestionnaireResponse) 
									this.executeService(request);
			
			if(null != response){
				validationMessages = response.getMessages();
			}
			this.selectedQuestionId = null;
		}
		return "editQuestionnairePopUp";
	}
	
	/**
	 * Method to save the modification made by the user.
	 * @return
	 */
	public String save(){
		Map formChildsByAnswers = null;
		List childQuestionnairesToUpdate = null;
		PersistChildQuestionnaireRequest request = null;
		PersistChildQuestionnaireResponse response = null;
		boolean isValidated = false;
		ErrorMessage errorMessage = null;
		
		if(!this.deleteFlag){
			// validate whether all the fields are entered.
			isValidated = validateMandatoryFieldsForSave();
			
			if(!isValidated){
				return "";
			}
		}
		
		// split the childs corresponding to the answer and form a map.
		formChildsByAnswers = generateChildsByAnswers();
		
		if(null != formChildsByAnswers && !formChildsByAnswers.isEmpty()){
			// reorder the sequence and convert into single list of bo's.
			childQuestionnairesToUpdate = reOrderAndConvertToList(formChildsByAnswers);
		}
		// set the childs bo in the request.
		request = (PersistChildQuestionnaireRequest) this.getServiceRequest
							(ServiceManager.PERSIST_CHILD_QUESTIONNAIRE_REQUEST);
		request.setAdminOptionName(this.adminOptionName);
		request.setAdminOptionVersion(this.adminOptionVersion);
		request.setChildQuestionnaires(childQuestionnairesToUpdate);
		request.setInsertFlag(false);
		request.setQuestionnairesToDeleted(this.childQuestionnairesToDeleted);
		request.setAdminOptionId(this.adminOptionSystemId);
		request.setParentQuestionnaireId(this.rootQuestionnaireHierarchySystemId);
		request.setDeleteFlag(this.deleteFlag);
		
		// get the message from the response and display.
		response = (PersistChildQuestionnaireResponse) 
								this.executeService(request);
		
		if(null != response){
			validationMessages = response.getMessages();
		}
		getRequest().setAttribute("RETAIN_Value", "");
		this.duplicateData = null;
		return "editQuestionnairePopUp";
	}
	
	/**
	 * Method to clear the session while closing the edit questionnaire popup,
	 * and validations for the invalid datas.
	 *
	 */
	public String close(){
		List childQuestions = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		if(null != getSession().getAttribute("ParentQuestionnaireDetails")){
			rootQuestionnaireBO = (RootQuestionnaireBO) 
				getSession().getAttribute("ParentQuestionnaireDetails");
			this.rootQuestionnaireHierarchySystemId = rootQuestionnaireBO.getQuestionnaireHierachySystemId();
			this.adminOptionSystemId = rootQuestionnaireBO.getAdminOptionSystemId();
		}
		childQuestions = retrieveChildQuestions("ON_CLOSE");
		if(null != getSession().getAttribute("AdminOptionName")){
			this.adminOptionName = String.valueOf(getSession().getAttribute("AdminOptionName"));
			setBreadCrumbText("Administration >> Administration Option "
	                + "(" + this.adminOptionName + ") >> Edit");
		}
		clearSessionAndClassAttributes();
		return "";
	}
	
	public String delete(){
		this.setDeleteFlag(true);
		save();
		return "editQuestionnairePopUp";
	}

	/**
	 * @return Returns the childQuestionsPanel.
	 */
	public HtmlPanelGrid getChildQuestionsPanel() {
		List childQuestions = null;
		Map sortedQuestions = null;
		
		// Retrieve the childQuestions Details.
		childQuestions = retrieveChildQuestions("ON_LOAD");
		
		getSession().setAttribute("ChildQuestionnairesList", childQuestions);
		
		// iterate the list and split the bo according to the answers
		// and set it in a map.
		sortedQuestions = splitChildByAnswers(childQuestions);
		
		
		if(null != sortedQuestions && !sortedQuestions.isEmpty()){
			// register the sequence.
			registerSequence(sortedQuestions);
			
			// set the map in the session
			getSession().setAttribute("ChildQuestionnairesMap", sortedQuestions);
		
			// iterate the map and display in the panel.
			preparePanel(sortedQuestions);
		}else{
			childQuestionsPanel = null;
			getSession().setAttribute("ChildQuestionnairesMap", null);
		}
		return childQuestionsPanel;
		
	}
	/**
	 * @param childQuestionsPanel The childQuestionsPanel to set.
	 */
	public void setChildQuestionsPanel(HtmlPanelGrid childQuestionsPanel) {
		
		this.childQuestionsPanel = childQuestionsPanel;
		
	}
	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {
		
		headerPanel = new HtmlPanelGrid();
		HtmlOutputText sequence = new HtmlOutputText();
		HtmlOutputText answer = new HtmlOutputText();
		HtmlOutputText question = new HtmlOutputText();
		HtmlOutputText reference = new HtmlOutputText();
		HtmlOutputText lineOfBusiness = new HtmlOutputText();
		HtmlOutputText businessEntity = new HtmlOutputText();
		HtmlOutputText businessGroup = new HtmlOutputText();
		HtmlOutputText marketBusinssUnit = new HtmlOutputText();
		//HtmlOutputText delete = new HtmlOutputText();
		HtmlCommandButton delete = new HtmlCommandButton();
		
		sequence.setValue("Sequence");
		sequence.setId("Sequence");
		
		answer.setValue("Answer");
		answer.setId("Answer");
		
		question.setValue("Question");
		question.setValue("Question");
		
		reference.setValue("Reference");
		reference.setId("Reference");
		
		lineOfBusiness.setValue("Line Of Business");
		lineOfBusiness.setId("LineOfBusiness");
		
		businessEntity.setValue("Business Entity");
		businessEntity.setId("BusinessEntity");
		
		businessGroup.setValue("Business Group");
		businessGroup.setId("BusinessGroup");
		
		marketBusinssUnit.setValue("Market Business Unit");
		marketBusinssUnit.setId("MarketBusinessUnit");
		
		delete.setValue("Delete");
		delete.setId("Delete");
		delete.setTitle("Delete");
		delete.setStyleClass("wpdbutton");
		/*delete.setAction(FacesContext.getCurrentInstance().getApplication().
				createMethodBinding("#{editQuestionnaireBackingBean.delete}", 
						new Class[] {}));*/
		delete.setDisabled(true);
		delete.setOnclick("javascript:deleteAction();return false;");
		
		headerPanel.setColumns(9);
		headerPanel.setWidth("100%");
		headerPanel.setCellspacing("1");
		headerPanel.setCellpadding("2");
		headerPanel.setColumnClasses("w8,w8,w20,w11,w11,w11,w11,w11,w9");
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setStyleClass("dataTableHeader");		
		headerPanel.getChildren().add(sequence);
		headerPanel.getChildren().add(answer);
		headerPanel.getChildren().add(question);
		headerPanel.getChildren().add(reference);
		headerPanel.getChildren().add(lineOfBusiness);
		headerPanel.getChildren().add(businessEntity);
		headerPanel.getChildren().add(businessGroup);
		headerPanel.getChildren().add(marketBusinssUnit);
		headerPanel.getChildren().add(delete);
		
		return headerPanel;
	}
	/**
	 * @param headerPanel The headerPanel to set.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}
	/**
	 * @return Returns the possibleAnswerList.
	 */
	public List getPossibleAnswerList() {
		RootQuestionnaireBO rootQuestionnaireBO = (RootQuestionnaireBO)
			this.getSession().getAttribute("ParentQuestionnaireDetails");
		if(null != rootQuestionnaireBO ){
			
			if(null!=rootQuestionnaireBO.getReferenceDescription()&&!"".equals(rootQuestionnaireBO.getReferenceDescription()) && !"null".equals(rootQuestionnaireBO.getReferenceDescription()))
			rootQuestionDescAndReference = 
				rootQuestionnaireBO.getQuestionDescription() + " [" +
				rootQuestionnaireBO.getReferenceDescription() + " ]" ;
			else
				rootQuestionDescAndReference = 
					rootQuestionnaireBO.getQuestionDescription() ;
			
			possibleAnswerList = convertAnswersToSelectItem
					(rootQuestionnaireBO.getPossibleAnswerList());	
		}
		return possibleAnswerList;
	}
	/**
	 * @param possibleAnswerList The possibleAnswerList to set.
	 */
	public void setPossibleAnswerList(List possibleAnswerList) {
		this.possibleAnswerList = possibleAnswerList;
	}
	/**
	 * @return Returns the rootQuestionDescAndReference.
	 */
	public String getRootQuestionDescAndReference() {
		return rootQuestionDescAndReference;
	}
	/**
	 * @param rootQuestionDescAndReference The rootQuestionDescAndReference to set.
	 */
	public void setRootQuestionDescAndReference(
			String rootQuestionDescAndReference) {
		this.rootQuestionDescAndReference = rootQuestionDescAndReference;
	}
	/**
	 * @return Returns the selectedQuestionId.
	 */
	public String getSelectedQuestionId() {
		return selectedQuestionId;
	}
	/**
	 * @param selectedQuestionId The selectedQuestionId to set.
	 */
	public void setSelectedQuestionId(String selectedQuestionId) {
		this.selectedQuestionId = selectedQuestionId;
	}
	/**
	 * @return Returns the answersMap.
	 */
	public HashMap getAnswersMap() {
		return answersMap;
	}
	/**
	 * @param answersMap The answersMap to set.
	 */
	public void setAnswersMap(HashMap answersMap) {
		this.answersMap = answersMap;
	}
	/**
	 * @return Returns the deleteMap.
	 */
	public HashMap getDeleteMap() {
		return deleteMap;
	}
	/**
	 * @param deleteMap The deleteMap to set.
	 */
	public void setDeleteMap(HashMap deleteMap) {
		this.deleteMap = deleteMap;
	}
	/**
	 * @return Returns the hiddenAnswersStaticMap.
	 */
	public HashMap getHiddenAnswersStaticMap() {
		return hiddenAnswersStaticMap;
	}
	/**
	 * @param hiddenAnswersStaticMap The hiddenAnswersStaticMap to set.
	 */
	public void setHiddenAnswersStaticMap(HashMap hiddenAnswersStaticMap) {
		this.hiddenAnswersStaticMap = hiddenAnswersStaticMap;
	}
	/**
	 * @return Returns the hiddenBusinessEntityMap.
	 */
	public HashMap getHiddenBusinessEntityMap() {
		return hiddenBusinessEntityMap;
	}
	/**
	 * @param hiddenBusinessEntityMap The hiddenBusinessEntityMap to set.
	 */
	public void setHiddenBusinessEntityMap(HashMap hiddenBusinessEntityMap) {
		this.hiddenBusinessEntityMap = hiddenBusinessEntityMap;
	}
	/**
	 * @return Returns the hiddenBusinessGroupMap.
	 */
	public HashMap getHiddenBusinessGroupMap() {
		return hiddenBusinessGroupMap;
	}
	/**
	 * @param hiddenBusinessGroupMap The hiddenBusinessGroupMap to set.
	 */
	public void setHiddenBusinessGroupMap(HashMap hiddenBusinessGroupMap) {
		this.hiddenBusinessGroupMap = hiddenBusinessGroupMap;
	}
	/**
	 * @return Returns the hiddenLOBMap.
	 */
	public HashMap getHiddenLOBMap() {
		return hiddenLOBMap;
	}
	/**
	 * @param hiddenLOBMap The hiddenLOBMap to set.
	 */
	public void setHiddenLOBMap(HashMap hiddenLOBMap) {
		this.hiddenLOBMap = hiddenLOBMap;
	}
	/**
	 * @return Returns the hiddenQuestionnaireHierarchyStaticMap.
	 */
	public HashMap getHiddenQuestionnaireHierarchyStaticMap() {
		return hiddenQuestionnaireHierarchyStaticMap;
	}
	/**
	 * @param hiddenQuestionnaireHierarchyStaticMap The hiddenQuestionnaireHierarchyStaticMap to set.
	 */
	public void setHiddenQuestionnaireHierarchyStaticMap(
			HashMap hiddenQuestionnaireHierarchyStaticMap) {
		this.hiddenQuestionnaireHierarchyStaticMap = hiddenQuestionnaireHierarchyStaticMap;
	}
	/**
	 * @return Returns the hiddenQuestionNumberMap.
	 */
	public HashMap getHiddenQuestionNumberMap() {
		return hiddenQuestionNumberMap;
	}
	/**
	 * @param hiddenQuestionNumberMap The hiddenQuestionNumberMap to set.
	 */
	public void setHiddenQuestionNumberMap(HashMap hiddenQuestionNumberMap) {
		this.hiddenQuestionNumberMap = hiddenQuestionNumberMap;
	}
	/**
	 * @return Returns the hiddenQuestionNumberStaticMap.
	 */
	public HashMap getHiddenQuestionNumberStaticMap() {
		return hiddenQuestionNumberStaticMap;
	}
	/**
	 * @param hiddenQuestionNumberStaticMap The hiddenQuestionNumberStaticMap to set.
	 */
	public void setHiddenQuestionNumberStaticMap(
			HashMap hiddenQuestionNumberStaticMap) {
		this.hiddenQuestionNumberStaticMap = hiddenQuestionNumberStaticMap;
	}
	/**
	 * @return Returns the hiddenReferenceIdMap.
	 */
	public HashMap getHiddenReferenceIdMap() {
		return hiddenReferenceIdMap;
	}
	/**
	 * @param hiddenReferenceIdMap The hiddenReferenceIdMap to set.
	 */
	public void setHiddenReferenceIdMap(HashMap hiddenReferenceIdMap) {
		this.hiddenReferenceIdMap = hiddenReferenceIdMap;
	}
	/**
	 * @return Returns the rootQuestionnaireHierarchySystemId.
	 */
	public int getRootQuestionnaireHierarchySystemId() {
		return rootQuestionnaireHierarchySystemId;
	}
	/**
	 * @param rootQuestionnaireHierarchySystemId The rootQuestionnaireHierarchySystemId to set.
	 */
	public void setRootQuestionnaireHierarchySystemId(
			int rootQuestionnaireHierarchySystemId) {
		this.rootQuestionnaireHierarchySystemId = rootQuestionnaireHierarchySystemId;
	}
	/**
	 * @return Returns the sequenceMap.
	 */
	public HashMap getSequenceMap() {
		return sequenceMap;
	}
	/**
	 * @param sequenceMap The sequenceMap to set.
	 */
	public void setSequenceMap(HashMap sequenceMap) {
		this.sequenceMap = sequenceMap;
	}
	/**
	 * @return Returns the selectedAnswerId.
	 */
	public String getSelectedAnswerId() {
		return selectedAnswerId;
	}
	/**
	 * @param selectedAnswerId The selectedAnswerId to set.
	 */
	public void setSelectedAnswerId(String selectedAnswerId) {
		this.selectedAnswerId = selectedAnswerId;
	}
	/**
	 * @return Returns the adminOptionSystemId.
	 */
	public int getAdminOptionSystemId() {
		return adminOptionSystemId;
	}
	/**
	 * @param adminOptionSystemId The adminOptionSystemId to set.
	 */
	public void setAdminOptionSystemId(int adminOptionSystemId) {
		this.adminOptionSystemId = adminOptionSystemId;
	}
	/**
	 * @return Returns the requiredAnswer.
	 */
	public boolean isRequiredAnswer() {
		return requiredAnswer;
	}
	/**
	 * @param requiredAnswer The requiredAnswer to set.
	 */
	public void setRequiredAnswer(boolean requiredAnswer) {
		this.requiredAnswer = requiredAnswer;
	}
	/**
	 * @return Returns the requiredQuestion.
	 */
	public boolean isRequiredQuestion() {
		return requiredQuestion;
	}
	/**
	 * @param requiredQuestion The requiredQuestion to set.
	 */
	public void setRequiredQuestion(boolean requiredQuestion) {
		this.requiredQuestion = requiredQuestion;
	}
	/**
	 * Method to load the neccessary details (Root Question Details, Corresponding child questions)
	 * required while loading the edit quesionnaire popup.
	 * @return Returns the loadEditQuestionnairePopUp.
	 */
	//WAS 7.0 Changes - Binding variable LoadEditQuestionnairePopUp modified to HtmlInputHidden instead of String, Since getter method call failed,

	// while the variable defined in String in WAS 7.0
	
	public HtmlInputHidden getLoadEditQuestionnairePopUp() {

		
		RootQuestionnaireBO rootQuestionnaireBO = null;
		
		// Retrieve the Corresponding Question Details.
		rootQuestionnaireBO = retrieveQuestionDetails();
		
		// set to the local variables.
		if(null != rootQuestionnaireBO){
			if(null!=rootQuestionnaireBO.getReferenceDescription() && !"".equals(rootQuestionnaireBO.getReferenceDescription()) && !"null".equals(rootQuestionnaireBO.getReferenceDescription()))
			rootQuestionDescAndReference = 
				rootQuestionnaireBO.getQuestionDescription() + " [" +
				rootQuestionnaireBO.getReferenceDescription() + " ]" ;
			else
				rootQuestionDescAndReference = 
					rootQuestionnaireBO.getQuestionDescription();
			possibleAnswerList = convertAnswersToSelectItem
					(rootQuestionnaireBO.getPossibleAnswerList());	
			adminOptionSystemId = rootQuestionnaireBO.getAdminOptionSystemId();
		}/*else{
			rootQuestionDescAndReference = "";
			possibleAnswerList = null;
		}*/
		if(null != getRequest().getAttribute("parentQuestionnaireHierarchyId")){
			loadEditQuestionnairePopUp.setValue("editQuestionnairePopUp");
			return loadEditQuestionnairePopUp;
		}
		else
			loadEditQuestionnairePopUp.setValue("");
		return loadEditQuestionnairePopUp;
	}
	/**
	 * @param loadEditQuestionnairePopUp The loadEditQuestionnairePopUp to set.
	 */
	public void setLoadEditQuestionnairePopUp(HtmlInputHidden loadEditQuestionnairePopUp) {
		this.loadEditQuestionnairePopUp = loadEditQuestionnairePopUp;
	}
	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}
	/**
	 * @param adminOptionName The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}
	/**
	 * @return Returns the adminOptionVersion.
	 */
	public int getAdminOptionVersion() {
		return adminOptionVersion;
	}
	/**
	 * @param adminOptionVersion The adminOptionVersion to set.
	 */
	public void setAdminOptionVersion(int adminOptionVersion) {
		this.adminOptionVersion = adminOptionVersion;
	}
	/**
	 * @return Returns the childQuestionnairesToDeleted.
	 */
	public String getChildQuestionnairesToDeleted() {
		return childQuestionnairesToDeleted;
	}
	/**
	 * @param childQuestionnairesToDeleted The childQuestionnairesToDeleted to set.
	 */
	public void setChildQuestionnairesToDeleted(
			String childQuestionnairesToDeleted) {
		this.childQuestionnairesToDeleted = childQuestionnairesToDeleted;
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
	 * @return Returns the businessEntityInputMap.
	 */
	public HashMap getBusinessEntityInputMap() {
		return businessEntityInputMap;
	}
	/**
	 * @param businessEntityInputMap The businessEntityInputMap to set.
	 */
	public void setBusinessEntityInputMap(HashMap businessEntityInputMap) {
		this.businessEntityInputMap = businessEntityInputMap;
	}
	/**
	 * @return Returns the businessGroupInputMap.
	 */
	public HashMap getBusinessGroupInputMap() {
		return businessGroupInputMap;
	}
	/**
	 * @param businessGroupInputMap The businessGroupInputMap to set.
	 */
	public void setBusinessGroupInputMap(HashMap businessGroupInputMap) {
		this.businessGroupInputMap = businessGroupInputMap;
	}
	/**
	 * @return Returns the questionInputMap.
	 */
	public HashMap getQuestionInputMap() {
		return questionInputMap;
	}
	/**
	 * @param questionInputMap The questionInputMap to set.
	 */
	public void setQuestionInputMap(HashMap questionInputMap) {
		this.questionInputMap = questionInputMap;
	}
	/**
	 * @return Returns the referenceInputMap.
	 */
	public HashMap getReferenceInputMap() {
		return referenceInputMap;
	}
	/**
	 * @param referenceInputMap The referenceInputMap to set.
	 */
	public void setReferenceInputMap(HashMap referenceInputMap) {
		this.referenceInputMap = referenceInputMap;
	}
	/**
	 * @return Returns the lineOfBusinessInputMap.
	 */
	public HashMap getLineOfBusinessInputMap() {
		return lineOfBusinessInputMap;
	}
	/**
	 * @param lineOfBusinessInputMap The lineOfBusinessInputMap to set.
	 */
	public void setLineOfBusinessInputMap(HashMap lineOfBusinessInputMap) {
		this.lineOfBusinessInputMap = lineOfBusinessInputMap;
	}
	/**
	 * 
	 * @param queryVariables
	 */
	public void setQueryVariables(String queryVariables){
		this.queryVariables = queryVariables;
	}
	/**
	 * 
	 * @return
	 */
	public String getQueryVariables(){
//		 get the rootQuestionnaireHierachySystemId from the request.
		if(null != getRequest().getParameter("parentQuestionnaireHierarchyId")){
			rootQuestionnaireHierarchySystemId = 
						Integer.parseInt((getRequest().
								getParameter("parentQuestionnaireHierarchyId")).toString());
		}
		// set the admin option name and admin option version number
		if(null != getRequest().getParameter("adminOptionVersion")){
			adminOptionVersion = Integer.parseInt((getRequest().
					getParameter("adminOptionVersion")).toString());
		}
		if(null != getRequest().getParameter("adminOptionName")){
			adminOptionName = (getRequest().getParameter("adminOptionName")).toString();
		}
		
		return "";
	}
	/**
	 * 
	 * @return
	 */
	public String submitAction(){
		this.getRequest().setAttribute("adminOptionName",this.getAdminOptionName());
		this.getRequest().setAttribute("adminOptionVersion",String.valueOf(this.getAdminOptionVersion()));
		this.getRequest().setAttribute("parentQuestionnaireHierarchyId",
				String.valueOf(this.getRootQuestionnaireHierarchySystemId()));
		return "editQuestionnairePopUp";
	}
	/**
	 * @return Returns the duplicateData.
	 */
	public String getDuplicateData() {
		return duplicateData;
	}
	/**
	 * @param duplicateData The duplicateData to set.
	 */
	public void setDuplicateData(String duplicateData) {
		this.duplicateData = duplicateData;
	}
	/**
	 * @return Returns the panelData.
	 */
	public String getPanelData() {
		return panelData;
	}
	/**
	 * @param panelData The panelData to set.
	 */
	public void setPanelData(String panelData) {
		this.panelData = panelData;
	}
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    /**
     * @return Returns the hiddenParentRequiredMap.
     */
    public HashMap getHiddenParentRequiredMap() {
        return hiddenParentRequiredMap;
    }
    /**
     * @param hiddenParentRequiredMap The hiddenParentRequiredMap to set.
     */
    public void setHiddenParentRequiredMap(HashMap hiddenParentRequiredMap) {
        this.hiddenParentRequiredMap = hiddenParentRequiredMap;
    }
    
    /**
     * The method will map QuestionnaireHierarchySystemId and the Parent Required status
     * in hiddenParentRequiredMap of the editQuestionnaireBackingBean.
     * This is invoked when preparing the panel of child questions 
     * for the editQuestionnairePopup.jsp
     * @param childQuestionnaireBO
     * @return
     */
    private HtmlInputHidden getParentRequiredHiddenForPanel(
			ChildQuestionnaireBO childQuestionnaireBO){		
        HtmlInputHidden parentRequiredHidden = new HtmlInputHidden();
		String parentRequired = null;
		
		if(null != childQuestionnaireBO.getParentRequired()){
		    parentRequired = childQuestionnaireBO.getParentRequired();			
		}		
		// set the businessGroup in the dynamic hidden map.
		parentRequiredHidden.setId("parentRequiredHidden" + 
				childQuestionnaireBO.getQuestionnaireHierarchySystemId());
		parentRequiredHidden.setValue(parentRequired);
        ValueBinding valForParentRequired = 
			FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
					"#{editQuestionnaireBackingBean.hiddenParentRequiredMap["
					+ childQuestionnaireBO.getQuestionnaireHierarchySystemId()
					+ "]}");
        parentRequiredHidden.setValueBinding("value", valForParentRequired);       
		return parentRequiredHidden;
	}
    
    /**
     * The method checks whether the question has been changed
     * @param questionnaireHirerachySysId
     * @return
     */
    private boolean isQuestionChanged(Long questionnaireHirerachySysId){        
        boolean isQuestionChanged = false;
        HashMap questionNumberMapNew = this.getHiddenQuestionNumberMap(); //Map containing new values
        HashMap questionNumberMapOld = this.getHiddenQuestionNumberStaticMap();//Map containing old values
        if (!questionNumberMapNew.get(questionnaireHirerachySysId).
                equals(questionNumberMapOld.get(questionnaireHirerachySysId))){
            isQuestionChanged = true;
        }       
        return isQuestionChanged;
    }
	/**
	 * @return Returns the hiddenmarketBusinessUnitMap.
	 */
	public HashMap getHiddenmarketBusinessUnitMap() {
		return hiddenmarketBusinessUnitMap;
	}
	/**
	 * @param hiddenmarketBusinessUnitMap The hiddenmarketBusinessUnitMap to set.
	 */
	public void setHiddenmarketBusinessUnitMap(
			HashMap hiddenmarketBusinessUnitMap) {
		this.hiddenmarketBusinessUnitMap = hiddenmarketBusinessUnitMap;
	}
	/**
	 * @return Returns the marketBusinessUnitInputMap.
	 */
	public HashMap getMarketBusinessUnitInputMap() {
		return marketBusinessUnitInputMap;
	}
	/**
	 * @param marketBusinessUnitInputMap The marketBusinessUnitInputMap to set.
	 */
	public void setMarketBusinessUnitInputMap(HashMap marketBusinessUnitInputMap) {
		this.marketBusinessUnitInputMap = marketBusinessUnitInputMap;
	}
}
