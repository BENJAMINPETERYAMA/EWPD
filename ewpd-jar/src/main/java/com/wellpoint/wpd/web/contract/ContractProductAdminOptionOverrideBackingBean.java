/*
 * ContractProductAdminOptionOverrideBackingBean.java
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

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitAdministrationRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductAdminOptionOverrideRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractAdministrationRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminOptionOverrideResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdministrationResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ContractAONotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.ContractAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.vo.NotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductAdminOptionOverrideBackingBean extends
		ContractBackingBean {

	private boolean renderFlagForPanel = false;

	private List benefitAdminList;

	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();
	
	private HtmlPanelGrid headerPanelForPrint = new HtmlPanelGrid();

	private HtmlPanelGrid panel = new HtmlPanelGrid();
	
	private HtmlPanelGrid panelForprint = new HtmlPanelGrid();
	
	

	private Map datafieldMap = new LinkedHashMap();

	private Map datafieldMapForQuestionId = new LinkedHashMap();

	private Map datafieldMapForAnswerId = new LinkedHashMap();

	private HtmlPanelGrid viewPanel = null;

	private String answerStates;

	private List questionnaireList = null;

	//private String hiddenInit;
	private HtmlInputHidden hiddenInit =new HtmlInputHidden();
	
	//private String hiddenView;
	private HtmlInputHidden hiddenView =new HtmlInputHidden();
	private int rowNum;

	private int answerId;
	
	private int contractMode = 0;
	
	private String records;

	private List questionNotes;

	int i = 0;

	private String searchString;

	private String questionId;

	private String primaryEntityID;

	private String primaryType;

	private String benefitComponentId;

	private String adminOptionId;

	private String prevNoteIdSelected;

	private String previousNoteVersion;

	private String noteStatus;

	private String noteId;

	private String noteVersion;

	private String noteName;

	private String version;

	private String newNoteId;
	
	private String tildaNoteStatus;
	
	private String noteAttached;


	public ContractProductAdminOptionOverrideBackingBean() {
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

		if (isViewMode()) {
			return "displayContractAdminOptionView";
		} else {
			this.answerStates=null;
			return "displayContractAdminOption";
		}
	}

	/**
	 * To get the list of default questionnaires from the db.
	 * 
	 * @return List benefitAdministrationList
	 */
	private List getDefaultQuestionnaire() {
		Logger
				.logInfo("Entering the method for getting default questionnaires attached to the contract");
		RetrieveContractProductAdminOptionOverrideRequest retrieveContractProductAdminOptionOverrideRequest = 
			(RetrieveContractProductAdminOptionOverrideRequest) getRetrieveContractProductAdminOptionOverrideRequest();
		retrieveContractProductAdminOptionOverrideRequest.setAction(RetrieveContractProductAdminOptionOverrideRequest.LOAD_CONTRACT_QUESTIONNAIRE);

		// Call the executeService() to get the response
		if (null == questionnaireList || questionnaireList.size() == 0) {
			RetrieveContractProductAdminOptionOverrideResponse retrieveContractProductAdminOptionOverrideResponse = (RetrieveContractProductAdminOptionOverrideResponse) this
					.executeService(retrieveContractProductAdminOptionOverrideRequest);

			if (null != retrieveContractProductAdminOptionOverrideResponse
					.getQuestionnaireList()
					&& retrieveContractProductAdminOptionOverrideResponse
							.getQuestionnaireList().size() != 0) {
				// Get the list of benefit administration values from the
				// response
				questionnaireList = retrieveContractProductAdminOptionOverrideResponse
						.getQuestionnaireList();
				this.getContractSession().setQuestionnaireList(
						questionnaireList);
			}
			Logger.logInfo("Returning the method for getting benefit "
					+ "administration values");
		}
		// Return the list to the calling method
		return questionnaireList;
	}

	/**
	 * To get the request object for retrieving the benefit administration list
	 * after setting all the values in it.
	 * 
	 * @return RetrieveProductStructureBenefitAdministrationRequest
	 */
	private RetrieveContractProductAdminOptionOverrideRequest getRetrieveContractProductAdminOptionOverrideRequest() {
		// Create the session object
		int adminOptionId = new Integer(this.getContractSession()
				.getProductAdmin()).intValue();
		int dateSegmentId = this.getContractKeyObject().getDateSegmentId();

		RetrieveContractProductAdminOptionOverrideRequest retrieveContractProductAdminOptionOverrideRequest = (RetrieveContractProductAdminOptionOverrideRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_PRODUCT_ADMINOPTION_OVERRIDE_REQUEST); // u have to change

		retrieveContractProductAdminOptionOverrideRequest
				.setAdminOptSysId(adminOptionId);
		retrieveContractProductAdminOptionOverrideRequest
				.setEntityId(dateSegmentId);
		return retrieveContractProductAdminOptionOverrideRequest;
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

		HtmlOutputText otxtType1 = new HtmlOutputText();
		HtmlOutputText otxtType2 = new HtmlOutputText();
		HtmlOutputText otxtType3 = new HtmlOutputText();
		HtmlOutputText otxtType4 = new HtmlOutputText();
		HtmlOutputText otxtType5 = new HtmlOutputText();
		
		otxtType1.setValue("Question");
		otxtType1.setId("Question");

		otxtType2.setValue("Answer");
		otxtType2.setId("Answer");

		otxtType3.setValue("Reference");
		otxtType3.setId("Reference");
		
		otxtType4.setValue("PVA");
		otxtType4.setId("PVA");
		
		otxtType5.setValue("Notes");
		otxtType5.setId("Notes");

		headerPanel.getChildren().add(otxtType1);
		headerPanel.getChildren().add(otxtType2);
		headerPanel.getChildren().add(otxtType3);
		headerPanel.getChildren().add(otxtType4);
		headerPanel.getChildren().add(otxtType5);

		return headerPanel;
	}
	/**
	 * Returns the headerPanel.
	 * 
	 * @return HtmlPanelGrid headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanelForPrint() {
		//headerPanelForPrint = new HtmlPanelGrid();

		headerPanelForPrint.setWidth("100%");
		headerPanelForPrint.setColumns(4);
		headerPanelForPrint.setBorder(0);
		headerPanelForPrint.setCellpadding("3");
		headerPanelForPrint.setCellspacing("1");
		headerPanelForPrint.setBgcolor("#cccccc");
		headerPanelForPrint.setStyleClass("dataTableHeader");

		HtmlOutputText otxtType1 = new HtmlOutputText();
		HtmlOutputText otxtType2 = new HtmlOutputText();
		HtmlOutputText otxtType3 = new HtmlOutputText();
		HtmlOutputText otxtType4 = new HtmlOutputText();
		//HtmlOutputText otxtType5 = new HtmlOutputText();
		
		otxtType1.setValue("Question");
		otxtType1.setId("Question");

		otxtType2.setValue("Answer");
		otxtType2.setId("Answer");

		otxtType3.setValue("Reference");
		otxtType3.setId("Reference");
		
		otxtType4.setValue("PVA");
		otxtType4.setId("PVA");
		
		//otxtType5.setValue("Notes");
		//otxtType5.setId("Notes");

		headerPanelForPrint.getChildren().add(otxtType1);
		headerPanelForPrint.getChildren().add(otxtType2);
		headerPanelForPrint.getChildren().add(otxtType3);
		headerPanelForPrint.getChildren().add(otxtType4);
	//	headerPanel.getChildren().add(otxtType5);

		return headerPanelForPrint;
	}
	
	
	/**
	 * Returns the panel.
	 * 
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanel() {
		panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		panel.setColumns(5);
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");

		StringBuffer line = null;
		String finalline = null;
		StringBuffer rowClass = new StringBuffer();

		questionnaireList = getDefaultQuestionnaire();
		
		if(this.answerStates==null ||"".equalsIgnoreCase(this.answerStates))
			storeAnswerState(questionnaireList);
		if (questionnaireList != null) {
			
			  HtmlOutputText notesHidden = null;

			 for (int i = 0; i < questionnaireList.size(); i++) {
				ContractAssnQuestionnaireBO contractAssnQuesitionnaireBO = (ContractAssnQuestionnaireBO) questionnaireList
						.get(i);
				HtmlOutputText questionDesc = new HtmlOutputText();
				HtmlOutputText referenceDesc = new HtmlOutputText();
				HtmlSelectOneMenu possibleAnswer = new HtmlSelectOneMenu();
				HtmlPanelGroup referenceGroup = new HtmlPanelGroup();
				HtmlInputHidden childCountHidden = new HtmlInputHidden();
				HtmlOutputText answerDesc = new HtmlOutputText();
				HtmlOutputText providerArrangement = new HtmlOutputText();
				HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
				notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i); 

				questionDesc.setId("questionDesc" + i);
				if (i > 0) {
					rowClass.append(",");
				}
				int level = contractAssnQuesitionnaireBO.getLevel();

				if (level > 1) {
					finalline = getLevelPrefix(level);
					questionDesc.setValue(finalline
							+ contractAssnQuesitionnaireBO.getQuestionName());
					rowClass.append("dataTableOddRow");
				} else {
					questionDesc.setValue(contractAssnQuesitionnaireBO
							.getQuestionName());
					rowClass.append("dataTableEvenRow");
				}
				if (contractMode == 0) {
					childCountHidden.setId("childCount" + i);
					childCountHidden.setValue(new Integer(contractAssnQuesitionnaireBO
							.getChildCount()));
					List answerList = contractAssnQuesitionnaireBO
							.getPossibleAnswerList();
					List possibleAnswersList = (List) getPossibleAnswersListForQuestion(answerList);
	
					possibleAnswer.setId("selectitem" + i);
					UISelectItems uis = new UISelectItems();
					uis.setValue(possibleAnswersList);
	
					possibleAnswer.setValue(new Integer(contractAssnQuesitionnaireBO
							.getSelectedAnswerid()).toString());
					possibleAnswer.setStyleClass("formInputList");
					possibleAnswer.setStyleClass("formInputFieldBenefitStructure");
					possibleAnswer.getChildren().add(uis);
	
					possibleAnswer.setOnchange("return loadNewChild(this)");
				}else{
					answerDesc.setId("answerDesc" + i);
					answerDesc.setValue(contractAssnQuesitionnaireBO
							.getSelectedAnswerDesc());					
				}
				referenceDesc.setId("reference" + i);
				referenceDesc.setValue(contractAssnQuesitionnaireBO
						.getReferenceDesc());
				
				providerArrangement.setId("pva" + i);
				providerArrangement.setValue(contractAssnQuesitionnaireBO.getProviderArrangement());
				
				  
                if (contractAssnQuesitionnaireBO.getNotesExists().equals("Y")) {
					notesAttachmentImage.setUrl("../../images/notes_exist.gif");
				} else {
					notesAttachmentImage.setUrl("../../images/page.gif");
				}
				String primaryType = "ATTACHCONTRACT";

				notesAttachmentImage.setStyle("cursor:hand;");
				notesAttachmentImage.setId("notesAttachmentImage" + i);
				if (contractMode == 0) {
					notesAttachmentImage
							.setOnclick("loadNotesPopup('../popups/contractAdminQuestionNotesPopup.jsp'+getUrl(),'"
									+ contractAssnQuesitionnaireBO
											.getQuestionId()
									+ "','"
									+ contractAssnQuesitionnaireBO
											.getDateSegmentId()
									+ "','"
									+ primaryType
									+ "','"
									+ null
									+ "','"
									+ null
									+ "','"
									+ contractAssnQuesitionnaireBO
											.getAdminOptionId()
									+ "','"
									+"notesAttachmentImage" +i+"','"+i
									+ "');return false;");
				} else {
					notesAttachmentImage
							.setOnclick("loadNotesPopup('../popups/contractAdminQuestionNotesViewPopup.jsp'+getUrl(),'"
									+ contractAssnQuesitionnaireBO
											.getQuestionId()
									+ "','"
									+ contractAssnQuesitionnaireBO
											.getDateSegmentId()
									+ "','"
									+ primaryType
									+ "','"
									+ null
									+ "','"
									+ null
									+ "','"
									+ contractAssnQuesitionnaireBO
											.getAdminOptionId()
									+ "','"
									+"notesAttachmentImage" +i+"','"+i
									+ "');return false;");
				}
			
				panel.getChildren().add(questionDesc);
				if (contractMode == 0) {
					panel.getChildren().add(possibleAnswer);
				}else{
					panel.getChildren().add(answerDesc);
				}

				referenceGroup.getChildren().add(referenceDesc);
				referenceGroup.getChildren().add(childCountHidden);
				panel.getChildren().add(referenceGroup);
				panel.getChildren().add(providerArrangement);
				
				if ("Y".equals(contractAssnQuesitionnaireBO
						.getValidDomainToAttach())) {
					panel.getChildren().add(notesAttachmentImage);
				} else {
					panel.getChildren().add(notesHidden);
				} 
            	
			//	panel.getChildren().add(notesAttachmentImage);
			}
			panel.setRowClasses(rowClass.toString());
		} else {
			panel.setRendered(false);
		}		
		return panel;
	}

	/**
	 * Returns the panel.
	 * 
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanelForprint() {
		panelForprint = new HtmlPanelGrid();
		panelForprint.setWidth("100%");
		panelForprint.setColumns(4);
		panelForprint.setBorder(0);
		panelForprint.setStyleClass("outputText");
		panelForprint.setCellpadding("3");
		panelForprint.setCellspacing("1");
		panelForprint.setBgcolor("#cccccc");

		StringBuffer line = null;
		String finalline = null;
		StringBuffer rowClass = new StringBuffer();

		questionnaireList = getDefaultQuestionnaire();
		
		if(this.answerStates==null ||"".equalsIgnoreCase(this.answerStates))
			storeAnswerState(questionnaireList);
		if (questionnaireList != null) {
			
			 // HtmlOutputText notesHidden = null;

			 for (int i = 0; i < questionnaireList.size(); i++) {
				ContractAssnQuestionnaireBO contractAssnQuesitionnaireBO = (ContractAssnQuestionnaireBO) questionnaireList
						.get(i);
				HtmlOutputText questionDesc = new HtmlOutputText();
				HtmlOutputText referenceDesc = new HtmlOutputText();
				HtmlSelectOneMenu possibleAnswer = new HtmlSelectOneMenu();
				HtmlPanelGroup referenceGroup = new HtmlPanelGroup();
				HtmlInputHidden childCountHidden = new HtmlInputHidden();
				HtmlOutputText answerDesc = new HtmlOutputText();
				HtmlOutputText providerArrangement = new HtmlOutputText();
				//HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
				//notesHidden = new HtmlOutputText();
            	//notesHidden.setId("notesHidden"+i); 

				questionDesc.setId("questionDesc" + i);
				if (i > 0) {
					rowClass.append(",");
				}
				int level = contractAssnQuesitionnaireBO.getLevel();

				if (level > 1) {
					finalline = getLevelPrefix(level);
					questionDesc.setValue(finalline
							+ contractAssnQuesitionnaireBO.getQuestionName());
					rowClass.append("dataTableOddRow");
				} else {
					questionDesc.setValue(contractAssnQuesitionnaireBO
							.getQuestionName());
					rowClass.append("dataTableEvenRow");
				}
				if (contractMode == 0) {
					childCountHidden.setId("childCount" + i);
					childCountHidden.setValue(new Integer(contractAssnQuesitionnaireBO
							.getChildCount()));
					List answerList = contractAssnQuesitionnaireBO
							.getPossibleAnswerList();
					List possibleAnswersList = (List) getPossibleAnswersListForQuestion(answerList);
	
					possibleAnswer.setId("selectitem" + i);
					UISelectItems uis = new UISelectItems();
					uis.setValue(possibleAnswersList);
	
					possibleAnswer.setValue(new Integer(contractAssnQuesitionnaireBO
							.getSelectedAnswerid()).toString());
					possibleAnswer.setStyleClass("formInputList");
					possibleAnswer.setStyleClass("formInputFieldBenefitStructure");
					possibleAnswer.getChildren().add(uis);
	
					possibleAnswer.setOnchange("return loadNewChild(this)");
				}else{
					answerDesc.setId("answerDesc" + i);
					answerDesc.setValue(contractAssnQuesitionnaireBO
							.getSelectedAnswerDesc());					
				}
				referenceDesc.setId("reference" + i);
				referenceDesc.setValue(contractAssnQuesitionnaireBO
						.getReferenceDesc());
				
				providerArrangement.setId("pva" + i);
				providerArrangement.setValue(contractAssnQuesitionnaireBO.getProviderArrangement());
				
				  
//                if (contractAssnQuesitionnaireBO.getNotesExists().equals("Y")) {
//					notesAttachmentImage.setUrl("../../images/notes_exist.gif");
//				} else {
//					notesAttachmentImage.setUrl("../../images/page.gif");
//				}
//				String primaryType = "ATTACHCONTRACT";
//
//				notesAttachmentImage.setStyle("cursor:hand;");
//				notesAttachmentImage.setId("notesAttachmentImage" + i);
//				if (contractMode == 0) {
//					notesAttachmentImage
//							.setOnclick("loadNotesPopup('../popups/contractAdminQuestionNotesPopup.jsp','"
//									+ contractAssnQuesitionnaireBO
//											.getQuestionId()
//									+ "','"
//									+ contractAssnQuesitionnaireBO
//											.getDateSegmentId()
//									+ "','"
//									+ primaryType
//									+ "','"
//									+ null
//									+ "','"
//									+ null
//									+ "','"
//									+ contractAssnQuesitionnaireBO
//											.getAdminOptionId()
//									+ "','"
//									+"notesAttachmentImage" +i+"','"+i
//									+ "');return false;");
//				} else {
//					notesAttachmentImage
//							.setOnclick("loadNotesPopup('../popups/contractAdminQuestionNotesViewPopup.jsp','"
//									+ contractAssnQuesitionnaireBO
//											.getQuestionId()
//									+ "','"
//									+ contractAssnQuesitionnaireBO
//											.getDateSegmentId()
//									+ "','"
//									+ primaryType
//									+ "','"
//									+ null
//									+ "','"
//									+ null
//									+ "','"
//									+ contractAssnQuesitionnaireBO
//											.getAdminOptionId()
//									+ "','"
//									+"notesAttachmentImage" +i+"','"+i
//									+ "');return false;");
//				}
			
				panelForprint.getChildren().add(questionDesc);
				if (contractMode == 0) {
					panelForprint.getChildren().add(possibleAnswer);
				}else{
					panelForprint.getChildren().add(answerDesc);
				}

				referenceGroup.getChildren().add(referenceDesc);
				referenceGroup.getChildren().add(childCountHidden);
				panelForprint.getChildren().add(referenceGroup);
				panelForprint.getChildren().add(providerArrangement);
				
//				if ("Y".equals(contractAssnQuesitionnaireBO
//						.getValidDomainToAttach())) {
//					panelForprint.getChildren().add(notesAttachmentImage);
//				} else {
//					panelForprint.getChildren().add(notesHidden);
//				} 
            	
			//	panel.getChildren().add(notesAttachmentImage);
			}
			panel.setRowClasses(rowClass.toString());
		} else {
			panel.setRendered(false);
		}		
		return panelForprint;
	}
	
	/**
	 * @param benefitAdminList2
	 */
	private void storeAnswerState(List list) {
		if (null != list && list.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			ContractAssnQuestionnaireBO administration = null;
			for (Iterator i = list.iterator(); i.hasNext();) {
				administration = (ContractAssnQuestionnaireBO) i.next();
				buffer.append(administration.getQuestionId());
				buffer.append("~");
				buffer.append(administration.getSelectedAnswerid());
				if (i.hasNext())
					buffer.append(":");
			}
			answerStates = buffer.toString();
		}
	}

	private Map loadAnswerStates() {
		Map map = new HashMap();
		if (null != answerStates && !"".equals(answerStates.trim())) {
			String[] qstnAnswers = answerStates.split(":");
			for (int i = 0; i < qstnAnswers.length; i++) {
				String qstnAnswer = qstnAnswers[i];
				String[] qstnAndAnswewr = qstnAnswer.split("~");
				if (qstnAndAnswewr.length == 2) {
					String qstn = qstnAndAnswewr[0];
					String answer = qstnAndAnswewr[1];
					map.put(qstn, answer);
				}
			}
		}
		return map;
	}

	/**
	 * To save the over ridden administration values to the db.
	 * 
	 * @return String
	 */
	public String saveQuestionnaire() {
		Logger.logInfo("Entering the method for saving questionnaire");

		// Create the request object from the getServiceRequest()
		String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		
		SaveContractAdministrationRequest saveProductAdministrationRequest = (SaveContractAdministrationRequest) this
				.getServiceRequest(ServiceManager.SAVE_CONTRACT_ADMINOPTION_OVERRIDE_REQUEST);

		questionnaireList = (List)this.getContractSession().getQuestionnaireList();
        if(null!=tildaArray && tildaArray.length>0){
        	processQuestionaireList(questionnaireList,tildaArray);
        }
        this.getContractSession().setQuestionnaireList(questionnaireList);
        tildaArray =null;
        this.tildaNoteStatus =null;
        
        
		int adminOptionId = this.getContractSession().getProductAdmin();
		int dateSegmentId = this.getContractKeyObject().getDateSegmentId();
		questionnaireList = this.getContractSession().getQuestionnaireList();
		saveProductAdministrationRequest.setAdminSysId(adminOptionId);
		saveProductAdministrationRequest.setDateSegmentId(dateSegmentId);
		saveProductAdministrationRequest.setQuestionnareList(questionnaireList);
		updateAMVForContract(saveProductAdministrationRequest);

		// Get the response from the executeService()
		SaveContractAdministrationResponse response = (SaveContractAdministrationResponse) this
				.executeService(saveProductAdministrationRequest);
		if(saveProductAdministrationRequest!=null)
		storeAnswerState(saveProductAdministrationRequest.getQuestionnareList());
		if (null != response) {
			List messageList = response.getMessages();
			addAllMessagesToRequest(messageList);
			getRequest().setAttribute("RETAIN_Value", "");
			this.answerStates=null;
			return "displayContractAdminOption";
			
		}

		Logger.logInfo("Returning the method for saving questionnaire");
		// Return the forward String
	
		return "";
	}

	/**
	 * @param saveProductAdministrationRequest
	 */
	private void updateAMVForContract(SaveContractAdministrationRequest req) {
		if(null != req && null != req.getQuestionnareList() &&
				req.getQuestionnareList().size() > 0){
			Map map = loadAnswerStates();
			boolean changed = false;
			List changedIds = new ArrayList();
			for(Iterator i=req.getQuestionnareList().iterator();i.hasNext();){
				ContractAssnQuestionnaireBO quesVO = (ContractAssnQuestionnaireBO)i.next();
				if((map.get(quesVO.getQuestionId() + "")==null)
				   || !(quesVO.getSelectedAnswerid()+"").equals(map.get(quesVO.getQuestionId() + "")))
					changed = true;
				    changedIds.add(new Integer(quesVO.getQuestionId()));
			}
			List quesList=req.getQuestionnareList();
			if(map!=null){
				Set set=map.keySet();
				Iterator iter=set.iterator();
				while(iter.hasNext()){
					boolean contains=false;
					String queskey=""+iter.next();
					
					if(quesList!=null&& quesList.size()>0){
						for(int i=0;i<quesList.size()&&!contains;i++){
							ContractAssnQuestionnaireBO entityBO = (ContractAssnQuestionnaireBO)quesList.get(i);
							if((queskey.equalsIgnoreCase(entityBO.getQuestionId() + "")))
								contains=true;	
						}		
					}
					if(!contains){
						changed = true;
						changedIds.add(new Integer(queskey));
					}
					
				}
			}
			if(changed){
				req.setChanged(true);
				req.setChangedIds(changedIds);
			//	req.setBCompName(getContractSession().getBenefitComponentDesc());
			}
		}

	}

	/**
	 * To get the list of over ridden values by taking it from the hidden.
	 * hashMaps return List
	 */
	private List getBenefitAdministrationOverriddenList() {

		// Create a list
		List productAdministrationList = new ArrayList();

		// Get the question id from the hidden key set of the HashMap
		Set idSet = datafieldMapForQuestionId.keySet();

		// Create the iterator for the questionId
		Iterator questionIdIter = idSet.iterator();

		// Get the answers key set from the HashMap
		Set answerSet = datafieldMapForAnswerId.keySet();
		// Create the iterator for the answerSet
		Iterator answerIterator = answerSet.iterator();

		int entityId = this.getContractSession().getContractKeyObject()
				.getDateSegmentId();

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
			administrationOverrideVO.setQuestionId((new Integer(questionId))
					.intValue());

			// Get the value of id form the map
			administrationOverrideVO.setEntityId(entityId);
			// Set the value of the question id
			administrationOverrideVO.setAnswerId(new Integer(
					((String) datafieldMap.get(iterationId))).intValue());

			// Add the VO to the list
			productAdministrationList.add(administrationOverrideVO);
		}

		// Return the list
		return productAdministrationList;

	}


	/**
	 * @return
	 */
	public String selectChildQuestionnaireList() {
	    
	    String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}	
	    
		RetrieveContractProductAdminOptionOverrideRequest request = (RetrieveContractProductAdminOptionOverrideRequest) getRetrieveContractProductAdminOptionOverrideRequest();

		int rowNum = this.rowNum;
		int answerId = this.answerId;
		int contractSysId = getContractSession().getContractKeyObject()
				.getContractSysId();
		questionnaireList = (List) getContractSession().getQuestionnaireList();
		
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(questionnaireList,tildaArray);
			}
			tildaArray =null;
			this.tildaNoteStatus =null;
		
		ContractAssnQuestionnaireBO contractAssnQuestionnaireBO = (ContractAssnQuestionnaireBO) questionnaireList
				.get(rowNum);

		request
				.setAction(RetrieveContractProductAdminOptionOverrideRequest.LOAD_CHILD_QUESTIONNAIRE);
		request.setQuestionareListIndex(rowNum);
		request.setContractAssnQuestionnaireBO(contractAssnQuestionnaireBO);
		request.setSelectedAnswerId(answerId);
		request.setQuestionnareList(questionnaireList);
		request.setEntityId(contractSysId);

		RetrieveContractProductAdminOptionOverrideResponse response = (RetrieveContractProductAdminOptionOverrideResponse) this
				.executeService(request);

		questionnaireList = response.getQuestionnaireList();

		this.getContractSession().setQuestionnaireList(questionnaireList);

		return "questionnaireList";
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
					((ContractAssnQuestionnaireBO) questionaireList
					.get(i)).setNotesExists("Y"); 
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((ContractAssnQuestionnaireBO) questionaireList
							.get(i)).setNotesExists("N");
							break;
				}
			}
		}		
	}

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
	 * Returns the datafieldMap
	 * @return Map datafieldMap.
	 */
	public Map getDatafieldMap() {
		return datafieldMap;
	}

	/**
	 * Sets the datafieldMap
	 * @param datafieldMap.
	 */
	public void setDatafieldMap(Map datafieldMap) {
		this.datafieldMap = datafieldMap;
	}

	/**
	 * Returns the datafieldMapForAnswerId
	 * @return Map datafieldMapForAnswerId.
	 */
	public Map getDatafieldMapForAnswerId() {
		return datafieldMapForAnswerId;
	}

	/**
	 * Sets the datafieldMapForAnswerId
	 * @param datafieldMapForAnswerId.
	 */
	public void setDatafieldMapForAnswerId(Map datafieldMapForAnswerId) {
		this.datafieldMapForAnswerId = datafieldMapForAnswerId;
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
	 * Sets the headerPanel
	 * @param headerPanel.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
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
	 * @return Returns the hiddenInit.
	 */
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden  getHiddenInit() {
		this.contractMode = ContractSession.VIEW_MODE;
		this.questionnaireList = null;
		this.getPanel();			
		//return hiddenInit;
		hiddenInit.setValue(hiddenInit);
        return hiddenInit;  
	}

	/**
	 * @param hiddenInit The hiddenInit to set.
	 */
	public void setHiddenInit(HtmlInputHidden  hiddenInit) {
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
	 * @return
	 */
	private RetrieveContractBenefitAdministrationRequest getRetrieveBenefitAdministrationRequest() {
		// Create the session object
		int benefitComponentId = new Integer(this.getContractSession()
				.getBenefitComponentId()).intValue();
		int benefitAminSysId = new Integer(this.getContractSession()
				.getBenefitAdminId()).intValue();
		int adminOptionId = new Integer(this.getContractSession()
				.getAdminOptionId()).intValue();
		int dareSegmentId = this.getContractKeyObject().getDateSegmentId();

		// Get the request object from the getServiceRequest()
		RetrieveContractBenefitAdministrationRequest retrieveContractBenefitAdministrationRequest = (RetrieveContractBenefitAdministrationRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_BENEFIT_ADMINISTRATION);

		retrieveContractBenefitAdministrationRequest
				.setAdminOptSysId(adminOptionId);
		retrieveContractBenefitAdministrationRequest
				.setBenefitAdminSysId(benefitAminSysId);
		retrieveContractBenefitAdministrationRequest
				.setBenefitComponentId(benefitComponentId);
		retrieveContractBenefitAdministrationRequest
				.setContractId(dareSegmentId);

		// Return the request object
		return retrieveContractBenefitAdministrationRequest;
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
	 * @return Returns the contractMode.
	 */
	public int getContractMode() {
		this.contractMode = this.getContractSession().getMode();
		return contractMode;
	}
	/**
	 * @param contractMode The contractMode to set.
	 */
	public void setContractMode(int contractMode) {
		this.contractMode = contractMode;
	}
	/**
	 * @return Returns the hiddenView.
	 */
	public HtmlInputHidden getHiddenView() {
		this.contractMode = ContractSession.VIEW_MODE;
		hiddenView.setValue(" ");
		return hiddenView;
	}
	/**
	 * @param hiddenView The hiddenView to set.
	 */
	public void setHiddenView(HtmlInputHidden hiddenView) {
		this.hiddenView = hiddenView;
	}
	public String getRecords() {
		
		if(null!=this.questionNotes) return records;
			QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
		return new String();
		
	}
	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest(){
		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
		.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);
		
		if (null != getRequest().getParameter("questionId")
				&& !("").equals(getRequest().getParameter("questionId"))) {
			if(null!=getRequest().getParameter("questionId")  && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
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
//		if (null != getRequest().getParameter("bcId")
//				&& !("").equals(getRequest().getParameter("bcId"))) {
//			this.benefitComponentId = getRequest().getParameter("bcId");
//			this.getSession().setAttribute("benefitComponentId",
//					benefitComponentId);
//		}
		if (null != getRequest().getParameter("adminOptionId")
				&& !("").equals(getRequest().getParameter("adminOptionId"))) {
			if(null!=getRequest().getParameter("adminOptionId")  && getRequest().getParameter("adminOptionId").matches("^[0-9a-zA-Z_]+$")){
				this.adminOptionId = getRequest().getParameter("adminOptionId");
			this.getSession().setAttribute("adminOptionId", adminOptionId);
			}
		}
		request.setPrimaryEntityID((this.getSession().getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType").toString());
		if(null!= this.getSession().getAttribute("adminOptionId"))
		request.setSecondaryId(this.getSession().getAttribute("adminOptionId").toString());

//		if(null!=this.getSession().getAttribute("benefitComponentId").toString() && !("").equals(this.getSession().getAttribute("benefitComponentId").toString())&& !("null").equals(this.getSession().getAttribute("benefitComponentId").toString())){
//		request.setBenefitComponentId(Integer.parseInt(getRequest().getSession().getAttribute("benefitComponentId").toString()));
//		}
		if(null!=this.getSession().getAttribute("questionId").toString() && !("").equals(this.getSession().getAttribute("questionId").toString())&& !("null").equals(this.getSession().getAttribute("questionId").toString())){
		request.setQuestionId(Integer.parseInt(this.getSession().getAttribute("questionId").toString()));
		}
	
		if(null!=searchString && !("").equals(searchString)){
			String 	newSearchString = WPDStringUtil.escapeString(searchString);
			request.setSearchString("%"+newSearchString.trim().toUpperCase()+"%");
			request.setSearchAction(2);
		}else{
			request.setSearchAction(1);
		}
		request.setSecondaryEntityType("ATTACHADMNQUEST");
		
		return request;
	}
	public void setRecords(String records){
		
		this.records=records;
	}
	public List getQuestionNotes() {

		i++;
		if(i==1){
			String searchString  = this.getRequest().getParameter("searchString");
			if(null!=searchString){
				this.searchString =searchString;
				QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
			}
		}
		
		return questionNotes;
	}
	private void setValuesToBackinBean(QuestionNotesPopUpResponse response){
		
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
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	
	/**
	 * This method is called when the user clicks on the save button in the popup.
	 * @return
	 */
	public String attachNotesToQuestion(){	
		
		ContractAONotesToQuestionAttachmentRequest contractAONotesToQuestionAttachmentRequest = (ContractAONotesToQuestionAttachmentRequest) this
			.getServiceRequest(ServiceManager.CNTRCT_AO_NOTES_TO_QUESTION_ATTACHMENT_REQUEST);	
	
		contractAONotesToQuestionAttachmentRequest.setNotesToQuestionAttachmentVO(setValuesToNotesToQuestionAttachmentVO());

	ContractAONotesToQuestionAttachmentResponse contractAONotesToQuestionAttachmentResponse= (ContractAONotesToQuestionAttachmentResponse) this
	.executeService(contractAONotesToQuestionAttachmentRequest);
	
	List messageList = contractAONotesToQuestionAttachmentResponse.getMessages();
	
	refreshQuestionNote(messageList);
	
	return null;
	}
	
	/**
	 * Methods to set value to the request.
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
				.getAdminOptionId()));
		notesToQuestionAttachmentVO.setBenefitCompId(0);
		notesToQuestionAttachmentVO.setBnftDefId(0);
		notesToQuestionAttachmentVO.setNoteOverrideStatus("F");

		notesToQuestionAttachmentVO.setNoteVersionNumber(Integer.parseInt(this
				.getNoteVersion()));

		notesToQuestionAttachmentVO.setSecondaryEntityType("ATTACHADMNQUEST");

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

	public String getBenefitComponentId() {
		return benefitComponentId;
	}

	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	public String getNewNoteId() {
		return newNoteId;
	}

	public void setNewNoteId(String newNoteId) {
		this.newNoteId = newNoteId;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
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

	public List getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAdminOptionId() {
		return adminOptionId;
	}

	public void setAdminOptionId(String adminOptionId) {
		this.adminOptionId = adminOptionId;
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
}