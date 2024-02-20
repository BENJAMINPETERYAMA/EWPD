/*
 * RootQuestionsBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.adminoption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.request.AddRootQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.EditRootQuestionsRequest;
import com.wellpoint.wpd.common.adminoption.request.LocateRootQuestionRequest;
import com.wellpoint.wpd.common.adminoption.response.AddRootQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.LocateRootQuestionResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RootQuestionsBackingBean extends WPDBackingBean {
	
	private HtmlInputHidden rootQuestionLoad=new HtmlInputHidden();
	
	public String selectedQuestions;

	public List selectedQuestionsList = null;

	private int adminId;

	private String adminName;

	private int adminVersion;

	public String lineOfBusiness = "ALL";

	public String businessEntity = "ALL";

	public String businessGroup = "ALL";
	
	public String marketBusinessUnit = "ALL";

	private HtmlPanelGrid headerPanelForAdd = null;

	private HtmlPanelGrid displayPanelForAdd = null;
	
	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

	private HtmlPanelGrid displayPanel = new HtmlPanelGrid();

	private HashMap sequenceMap = new HashMap();

	private HashMap hiddenQuestionnaireHierarchyStaticMap = new HashMap();

	private HashMap answersMap = new HashMap();

	private HashMap hiddenAnswersStaticMap = new HashMap();

	private HashMap hiddenQuestionNumberMap = new HashMap();

	private HashMap hiddenQuestionNumberStaticMap = new HashMap();

	private HashMap hiddenReferenceIdMap = new HashMap();

	private HashMap hiddenLOBMap = new HashMap();

	private HashMap hiddenBusinessEntityMap = new HashMap();

	private HashMap hiddenBusinessGroupMap = new HashMap();
	
	private HashMap hiddenMarketBusinessUnitMap = new HashMap();

	private HashMap deleteMap = new HashMap();

	private String reference;

	private boolean validateFlag = false;

	private boolean quesValidateFlag = false;

	public List validationMessages;

	public List questionListRetrieved;

	private boolean validateReferenceFlag = false;

	private String rootQuestionnairesToDeleted = null;

	private HashMap referenceIdMap = new HashMap();

	private String queryVariables = null;
	
	private boolean closeFlag ;
	
	private String submitPage = null;
	
	/**
	 * Method to set the values into the data table od addRootQuestionPopup page
	 * 
	 * @return String
	 */
	public String setDataTable() {
		this.createRootQuestionList(selectedQuestions);	
		Collections.sort(this.selectedQuestionsList);
		this.getSession().setAttribute("rootQuestionList",
				this.selectedQuestionsList);
		this.selectedQuestions = null;
		preparePanel();
		return "addRootQuestions";
	}	
	
	/**
	 * 
	 * @param selectedQuestions
	 * @return List
	 */
	private void createRootQuestionList(String selectedQuestions) {		
		selectedQuestionsList = new ArrayList();
		StringBuffer questionString = null;
		HashMap map = new HashMap();
		int j = 0;
		if(null != this.getSession().getAttribute("rootQuestionList")){
			this.selectedQuestionsList = (List)this.getSession().getAttribute("rootQuestionList");
			if(!this.selectedQuestionsList.isEmpty()){
				RootQuestionnaireBO rootQuestionnaireBO2 = null;
				for (int i=0; i<selectedQuestionsList.size(); i++){
					rootQuestionnaireBO2 = (RootQuestionnaireBO)selectedQuestionsList.get(i);
					map.put(new Integer(rootQuestionnaireBO2.getQuestionNumber()), rootQuestionnaireBO2.getQuestionDescription());
				}
			}
		}
		List lob = new ArrayList();
		List businessEntity = new ArrayList();
		List businessGroup = new ArrayList();
		List marketBusinessUnit = new ArrayList();
		//RootQuestion having business domain as "ALL", "ALL", "ALL" by default
		lob.add("ALL");
		businessEntity.add("ALL");
		businessGroup.add("ALL");
		marketBusinessUnit.add("ALL");
		String[] questionArray = selectedQuestions.split("~");
		RootQuestionnaireBO rootQuestionnaireBO = null;
		if (null != questionArray
				&& null != getRequest().getSession().getAttribute(
						"rootQuestAdminId")) {
			int adminOptionId = Integer.parseInt((String) getRequest()
					.getSession().getAttribute("rootQuestAdminId"));
			if(null == this.selectedQuestionsList || this.selectedQuestionsList.isEmpty())
				j = 1;
			else
				j = (this.selectedQuestionsList.size())+1;
			for (int i = 0; i < questionArray.length; i++, j++) {
				if (null != questionArray[i]){
					rootQuestionnaireBO = new RootQuestionnaireBO();
					rootQuestionnaireBO.setAdminOptionSystemId(adminOptionId);
					rootQuestionnaireBO.setQuestionDescription(questionArray[i]);
					rootQuestionnaireBO.setLob(lob);
					rootQuestionnaireBO.setBusinessGroup(businessGroup);
					rootQuestionnaireBO.setBusinessEntity(businessEntity);
					rootQuestionnaireBO.setMarketBusinessUnit(marketBusinessUnit);
					i++;
					rootQuestionnaireBO.setSequenceNumber(j);
					rootQuestionnaireBO.setQuestionNumber(Integer
							.parseInt(questionArray[i]));
					i++;
					if("null".equals(questionArray[i]))
						rootQuestionnaireBO.setSpsReference(null);
					else
						rootQuestionnaireBO.setSpsReference(questionArray[i]);
					i++;
					if("null".equals(questionArray[i]))
						rootQuestionnaireBO.setReferenceDescription(null);
					else
						rootQuestionnaireBO.setReferenceDescription(questionArray[i]);
					
					if(null != map && map.isEmpty() && map.size() == 0){
						selectedQuestionsList.add(rootQuestionnaireBO);
					}else if(!map.containsValue((questionArray[i-3]))){						
						selectedQuestionsList.add(rootQuestionnaireBO);
					}
					else{
						if(null == questionString)
							questionString = new StringBuffer();
						else
							questionString.append(", ");
						
						questionString.append(rootQuestionnaireBO.getQuestionDescription());
					}
				}
				
			}
		}
		
		if(null != questionString && !questionString.equals("")){
			if(null == this.validationMessages)
				this.validationMessages = new ArrayList();
			InformationalMessage informationalMessage = new InformationalMessage(WebConstants.DUPLICATE_QUESTION_ADDED);
			informationalMessage.setParameters(new String[] {questionString.toString()});
			this.validationMessages.add(informationalMessage);
			addAllMessagesToRequest(this.validationMessages);
		}
	}	
	

	/**
	 * 
	 * @return String
	 */
	public String addRootQuestions() {
		if (null != this.getSession().getAttribute("rootQuestionList")) {
			this.selectedQuestionsList = (List) this.getSession()
					.getAttribute("rootQuestionList");
		}
		//setReferenceToQuestionsList();
	//	if(validateReference()){
			this.getSession().removeAttribute("rootQuestionList");
			AddRootQuestionRequest addRootQuestionRequest = this
					.createAddRootQuestionRequest();

			AddRootQuestionResponse addRootQuestionResponse = null;
	
			addRootQuestionResponse = (AddRootQuestionResponse) this
					.executeService(addRootQuestionRequest);
			if (addRootQuestionResponse != null) {
				this.selectedQuestionsList = addRootQuestionResponse
						.getRootQuestionList();
			}
			closeFlag = true;
			getRequest().setAttribute("breadCrumbText","Administration >> Administration Option "
	                + "(" + this.adminName + ") >> Edit");
//		}else{
//			closeFlag = false;
//			this.validationMessages = new ArrayList();
//			this.validationMessages.add(new ErrorMessage(WebConstants.EMPTY_REFERENCE));
//			addAllMessagesToRequest(this.validationMessages);
//		}
		return "";

	}

	/**
	 * Method to set the reference value to the already present questions list.
	 */
	private void setReferenceToQuestionsList() {
		if(null != this.selectedQuestionsList && !this.selectedQuestionsList.isEmpty()){
			for(int i = 0; i < this.selectedQuestionsList.size(); i++){
				String refDesc = null;
				String refId = null;
				RootQuestionnaireBO rootQuestionnaireBO = (RootQuestionnaireBO)this.selectedQuestionsList.get(i);
				if(null != this.hiddenReferenceIdMap && !this.hiddenReferenceIdMap.isEmpty()){
					if(null != this.hiddenReferenceIdMap.get(new Long(rootQuestionnaireBO.getQuestionNumber()))){
						refId = (this.hiddenReferenceIdMap.get(new Long
								(rootQuestionnaireBO.getQuestionNumber()))).toString();
					}
				}
				if(null != this.referenceIdMap && !this.referenceIdMap.isEmpty()){
					if(null != this.referenceIdMap.get(new Long(rootQuestionnaireBO.getQuestionNumber()))){
						refDesc = (this.referenceIdMap.get(new Long
								(rootQuestionnaireBO.getQuestionNumber()))).toString();
					}
				}
				rootQuestionnaireBO.setReferenceDescription(refDesc);
				rootQuestionnaireBO.setReferenceId(refId);
			}
		}
		this.getSession().setAttribute("rootQuestionList",this.selectedQuestionsList);
	}

	/**
	 * Method to create the AddRootQuestionRequest
	 * 
	 * @return AddRootQuestionRequest
	 */
	private AddRootQuestionRequest createAddRootQuestionRequest() {
		AddRootQuestionRequest addRootQuestionRequest = (AddRootQuestionRequest) this
				.getServiceRequest(ServiceManager.ADD_ROOT_QUESTION_REQUEST);
		List listToAdd = this.getRootQuestionsToAdd();
		AdminOptionBO adminOptionBO = new AdminOptionBO();
			
		if (null != getRequest().getSession().getAttribute("rootQuestAdminId"))
			adminOptionBO.setAdminOptionId(Integer
					.parseInt((String) getRequest().getSession().getAttribute(
							"rootQuestAdminId")));
		if (null != getRequest().getSession()
				.getAttribute("rootQuestAdminName")){
			adminOptionBO.setAdminName((String) getRequest().getSession()
					.getAttribute("rootQuestAdminName"));
			this.adminName = adminOptionBO.getAdminName();
		}
		if (null != getRequest().getSession().getAttribute(
				"rootQuestAdminVersion"))
			adminOptionBO.setVersion(Integer.parseInt((String) getRequest()
					.getSession().getAttribute("rootQuestAdminVersion")));
		//this.setReferenceForQuestion();
		if(null != listToAdd)
			Collections.sort(listToAdd);
		addRootQuestionRequest.setRootQuestionList(listToAdd);
		addRootQuestionRequest.setAdminOptionBO(adminOptionBO);
		return addRootQuestionRequest;
	}

	/**
	 * To set the reference in to the question list
	 *
	 */
	private void setReferenceForQuestion() {
		if (null != this.reference && !this.reference.equals("")) {
			String[] referenceArray = this.reference.split("~");
			RootQuestionnaireBO rootQuestionnaireBO = null;
			if (null != referenceArray && null != this.selectedQuestionsList && !selectedQuestionsList.isEmpty()) {
				int j = 0;
				for (int i = 0; i < this.selectedQuestionsList.size(); i++, j++) {
					rootQuestionnaireBO = (RootQuestionnaireBO) this.selectedQuestionsList
							.get(i);
					if (rootQuestionnaireBO.getQuestionNumber() == Integer
							.parseInt(referenceArray[j])) {
						j++;
						rootQuestionnaireBO.setReferenceId(referenceArray[j]);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @return String
	 */
	public String getAdminOptionLoad() {
		if (null != getRequest().getAttribute("adminOptionId")) {
			getRequest().getSession().setAttribute("rootQuestAdminId",
					getRequest().getAttribute("adminOptionId"));
			this.adminId = Integer.parseInt((String) getRequest().getAttribute(
					"adminOptionId"));
			this.getSession().removeAttribute("rootQuestionList");
			this.selectedQuestionsList = null;
		}
		if (null != getRequest().getAttribute("adminOptionName")) {
			getRequest().getSession().setAttribute("rootQuestAdminName",
					getRequest().getAttribute("adminOptionName"));
			this.selectedQuestionsList = null;
		}
		if (null != getRequest().getAttribute("adminOptionVersion"))
			getRequest().getSession().setAttribute("rootQuestAdminVersion",
					getRequest().getAttribute("adminOptionVersion"));
		return "";
	}

	/**
	 * @param rootQuestionLoad
	 */
	public void setRootQuestionLoad(HtmlInputHidden rootQuestionLoad) {
		this.rootQuestionLoad = rootQuestionLoad;
	}
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden getRootQuestionLoad() {

		if (null != getRequest().getAttribute("adminOptionName")) {
			getRequest().getSession().setAttribute("rootQuestAdminName",
					getRequest().getAttribute("adminOptionName"));
			this.selectedQuestionsList = null;
		}
		if (null != getRequest().getAttribute("adminOptionVersion"))
			getRequest().getSession().setAttribute("rootQuestAdminVersion",
					getRequest().getAttribute("adminOptionVersion"));
		this.getQuestionListRetrieved();
		rootQuestionLoad.setValue("editRootQuestion");
        return rootQuestionLoad;  
	}

	/**
	 * 
	 * @return LocateRootQuestionRequest
	 */
	private LocateRootQuestionRequest createLocateRootQuestionRequest() {
		LocateRootQuestionRequest locateRootQuestionRequest = (LocateRootQuestionRequest) this
				.getServiceRequest(ServiceManager.LOCATE_ROOT_QUESTION_REQUEST);
		int adminOptId = -1;
		if (null != getRequest().getSession().getAttribute("rootQuestAdminId"))
			adminOptId = Integer.parseInt((String) getRequest().getSession()
					.getAttribute("rootQuestAdminId"));
		locateRootQuestionRequest.setAdminOptionId(adminOptId);
		return locateRootQuestionRequest;
	}

	/**
	 * 
	 * @return String
	 */
	public String updateRootQuestions() {
		if (validate()) {
			EditRootQuestionsRequest requset = this
					.createUpdateRootQuestionRequest();

			LocateRootQuestionResponse locateRootQuestionResponse = (LocateRootQuestionResponse) this
					.executeService(requset);
			if (null != locateRootQuestionResponse) {
				//this.preparePanel();
				this.validationMessages = locateRootQuestionResponse
						.getMessages();
				addAllMessagesToRequest(this.validationMessages);

			}
		} else {
			this.validationMessages = new ArrayList();
			this.validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELD_REQUIRED));
			addAllMessagesToRequest(this.validationMessages);
			return "";
		}
		return "editRootQuestion";
	}

	/**
	 * 
	 * @return String
	 */
	public String deleteRootQuestions() {
			EditRootQuestionsRequest requset = this
					.createDeleteRootQuestionRequest();

			LocateRootQuestionResponse locateRootQuestionResponse = (LocateRootQuestionResponse) this
					.executeService(requset);
			if (null != locateRootQuestionResponse) {
				//this.preparePanel();
				this.validationMessages = locateRootQuestionResponse
						.getMessages();
				addAllMessagesToRequest(this.validationMessages);

			}
		
		return "editRootQuestion";
	}
	
	/**
	 * Method to validate the Question list for mandatory fields
	 * @return boolean
	 */
	private boolean validate() {
		Set questionnaireHierarchyKeySet = null;
		Iterator questionnaireHierarchyIterator = null;
		Object questionnaireHierachyKey = null;		

		// iterate the hiddenQuestionnaireHierarchyStaticMap
		if (null != this.hiddenQuestionnaireHierarchyStaticMap) {
			questionnaireHierarchyKeySet = this.hiddenQuestionnaireHierarchyStaticMap
					.keySet();
			questionnaireHierarchyIterator = questionnaireHierarchyKeySet
					.iterator();
			while (questionnaireHierarchyIterator.hasNext()) {
				questionnaireHierachyKey = questionnaireHierarchyIterator
						.next();

				Long keyValue = null;

				keyValue = Long.valueOf(questionnaireHierachyKey.toString());
				if (null == this.sequenceMap
						|| null == this.sequenceMap.get(keyValue)
						|| this.sequenceMap.get(keyValue).toString().equals("")) {
					return false;
				}
				if (null == this.hiddenReferenceIdMap
						|| null == this.hiddenReferenceIdMap.get(keyValue)
						|| this.hiddenReferenceIdMap.get(keyValue).toString()
								.equals("")) {
					return false;
				}

			}
		}

		return true;
	}

	private boolean validateReference(){
		Set questionSet = null;
		Iterator questionIterator = null;
		Object questionKey = null;
		Long key = null;
		
		if(null != this.hiddenQuestionNumberMap){
			questionSet = hiddenQuestionNumberMap.keySet();
			questionIterator = questionSet.iterator();
			
			while(questionIterator.hasNext()){
				questionKey = questionIterator.next();
				if(null != questionKey){
					key = Long.valueOf(questionKey.toString());
					if(null == this.hiddenReferenceIdMap || null == this.hiddenReferenceIdMap.get(key) 
							|| this.hiddenReferenceIdMap.get(key).toString().equals(""))
						return false;
					
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return EditRootQuestionsRequest
	 */
	private EditRootQuestionsRequest createUpdateRootQuestionRequest() {
		EditRootQuestionsRequest request = (EditRootQuestionsRequest) this
				.getServiceRequest(ServiceManager.EDIT_ROOT_QUESTION_REQUEST);
		List listToUpdate = null;
		listToUpdate = this.generateRootQuestionsForUpdate();

		AdminOptionBO adminOptionBO = new AdminOptionBO();
		if (null != getRequest().getSession().getAttribute("rootQuestAdminId"))
			adminOptionBO.setAdminOptionId(Integer
					.parseInt((String) getRequest().getSession().getAttribute(
							"rootQuestAdminId")));
		if (null != getRequest().getSession()
				.getAttribute("rootQuestAdminName"))
			adminOptionBO.setAdminName((String) getRequest().getSession()
					.getAttribute("rootQuestAdminName"));
		if (null != getRequest().getSession().getAttribute(
				"rootQuestAdminVersion"))
			adminOptionBO.setVersion(Integer.parseInt((String) getRequest()
					.getSession().getAttribute("rootQuestAdminVersion")));
		request.setRootQuestionsList(listToUpdate);
		request.setAdminOptionBO(adminOptionBO);
		//request.setQuestionnaireToDelete(this.rootQuestionnairesToDeleted);
		return request;
	}

	 /** 
	 * @return DeleteRootQuestionsRequest
	 */
	private EditRootQuestionsRequest createDeleteRootQuestionRequest() {
		EditRootQuestionsRequest request = (EditRootQuestionsRequest) this
				.getServiceRequest(ServiceManager.EDIT_ROOT_QUESTION_REQUEST);
		List listToUpdate = null;
		listToUpdate = this.generateRootQuestionsForDelete();
		AdminOptionBO adminOptionBO = new AdminOptionBO();
		if (null != getRequest().getSession().getAttribute("rootQuestAdminId"))
			adminOptionBO.setAdminOptionId(Integer
					.parseInt((String) getRequest().getSession().getAttribute(
							"rootQuestAdminId")));
		if (null != getRequest().getSession()
				.getAttribute("rootQuestAdminName"))
			adminOptionBO.setAdminName((String) getRequest().getSession()
					.getAttribute("rootQuestAdminName"));
		if (null != getRequest().getSession().getAttribute(
				"rootQuestAdminVersion"))
			adminOptionBO.setVersion(Integer.parseInt((String) getRequest()
					.getSession().getAttribute("rootQuestAdminVersion")));
		request.setAdminOptionBO(adminOptionBO);
		request.setRootQuestionsList(listToUpdate);
		request.setDeleteFlag(true);
		request.setQuestionnaireToDelete(this.rootQuestionnairesToDeleted);
		return request;
	}

	
	/**
	 * 
	 * @return List
	 */
	private List generateRootQuestionsForUpdate() {
		Set questionnaireHierarchyKeySet = null;
		Iterator questionnaireHierarchyIterator = null;
		Object questionnaireHierachyKey = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		List rootQuestions = null;

		// iterate the hiddenQuestionnaireHierarchyStaticMap
		if (null != this.hiddenQuestionnaireHierarchyStaticMap) {
			questionnaireHierarchyKeySet = this.hiddenQuestionnaireHierarchyStaticMap
					.keySet();
			questionnaireHierarchyIterator = questionnaireHierarchyKeySet
					.iterator();
			while (questionnaireHierarchyIterator.hasNext()) {
				questionnaireHierachyKey = questionnaireHierarchyIterator
						.next();
				// generate the child bo corresponding to the questionnaire
				// hierarchy id.
				rootQuestionnaireBO = generateRootQuestionnaireBO(questionnaireHierachyKey);
				/*if (null != rootQuestionnaireBO
						&& rootQuestionnaireBO
								.getQuestionnaireHierachySystemId() != 0) {*/
					if (null == rootQuestions)
						rootQuestions = new ArrayList();
					// add the bo to the list.
					rootQuestions.add(rootQuestionnaireBO);
				//}
			}
		}

		if (null != rootQuestions) {
			// regiester the sequence
			SequenceUtil sequenceUtil = new SequenceUtil();
			rootQuestions = sequenceUtil.reOrderObjects(rootQuestions);
		}

		return rootQuestions;
	}

	private List getRootQuestionsToAdd(){
		List rootQuestionsToAdd = null;
		Set questionNumberKeySet = null;
		Iterator questionNumberIterator = null;
		Object questionKey = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		
		if(null != this.selectedQuestionsList){
			//questionNumberKeySet = this.hiddenQuestionNumberMap.keySet();
			questionNumberIterator = this.selectedQuestionsList.iterator();
			// To set the seq
			//int i = 1;
			while(questionNumberIterator.hasNext()){
				rootQuestionnaireBO = (RootQuestionnaireBO)questionNumberIterator.next();
				rootQuestionnaireBO = generateRootQuestionsForAdd(rootQuestionnaireBO,new Integer(rootQuestionnaireBO.getQuestionNumber()));
				if(null != rootQuestionnaireBO && rootQuestionnaireBO.getQuestionNumber() != 0){
					if(null == rootQuestionsToAdd)
						rootQuestionsToAdd = new ArrayList();
					rootQuestionsToAdd.add(rootQuestionnaireBO);
				}
			}
		}
		
		
		return rootQuestionsToAdd;
	}
	
	private RootQuestionnaireBO generateRootQuestionsForAdd(RootQuestionnaireBO rootQuestionnaireBO ,Object key){
		Long keyValue = null;
		List lob = new ArrayList();
		List businessEntity = new ArrayList();
		List businessGroup = new ArrayList();
		List marketBusinessUnit = new ArrayList();
		//RootQuestion having business domain as "ALL", "ALL", "ALL" by default
		lob.add("ALL");
		businessEntity.add("ALL");
		businessGroup.add("ALL");
		marketBusinessUnit.add("ALL");
		if(null != key){
			keyValue = Long.valueOf(key.toString());
			String reference=rootQuestionnaireBO.getSpsReference();
			rootQuestionnaireBO = new RootQuestionnaireBO();
			if(null != hiddenReferenceIdMap && null != hiddenReferenceIdMap.get(keyValue)){
				if(null != hiddenReferenceIdMap.get(keyValue).toString() && 
						hiddenReferenceIdMap.get(keyValue).toString().split("~").length > 1)
					rootQuestionnaireBO.setReferenceId(
							(hiddenReferenceIdMap.get(keyValue).toString()).split("~")[1]);
			}
			rootQuestionnaireBO.setQuestionNumber(Integer.parseInt(key.toString()));
			rootQuestionnaireBO.setLob(lob);
			rootQuestionnaireBO.setBusinessGroup(businessGroup);
			rootQuestionnaireBO.setBusinessEntity(businessEntity);
			rootQuestionnaireBO.setMarketBusinessUnit(marketBusinessUnit);
			rootQuestionnaireBO.setReferenceId(reference);
			
			if(null != getRequest().getSession().getAttribute(
					"rootQuestAdminId"))
				rootQuestionnaireBO.setAdminOptionSystemId(Integer
					.parseInt((String) getRequest().getSession().getAttribute(
					"rootQuestAdminId")));
		}
		
		return rootQuestionnaireBO;
	}
	
	/**
	 * 
	 * @return List
	 */
	private List generateRootQuestionsForDelete() {
		Set questionnaireHierarchyKeySet = null;
		Iterator questionnaireHierarchyIterator = null;
		Object questionnaireHierachyKey = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		List rootQuestions = null;

		// iterate the hiddenQuestionnaireHierarchyStaticMap
		if (null != this.hiddenQuestionnaireHierarchyStaticMap) {
			questionnaireHierarchyKeySet = this.hiddenQuestionnaireHierarchyStaticMap
					.keySet();
			questionnaireHierarchyIterator = questionnaireHierarchyKeySet
					.iterator();
			while (questionnaireHierarchyIterator.hasNext()) {
				questionnaireHierachyKey = questionnaireHierarchyIterator
						.next();
				// generate the child bo corresponding to the questionnaire
				// hierarchy id.
				rootQuestionnaireBO = generateRootQuestionnaireForDelete(questionnaireHierachyKey);
				if (null != rootQuestionnaireBO
						&& rootQuestionnaireBO
								.getQuestionnaireHierachySystemId() != 0) {
					if (null == rootQuestions)
						rootQuestions = new ArrayList();
					// add the bo to the list.
					rootQuestions.add(rootQuestionnaireBO);
				//}
			}
		}

		if (null != rootQuestions) {
			// regiester the sequence
			SequenceUtil sequenceUtil = new SequenceUtil();
			rootQuestions = sequenceUtil.reOrderObjects(rootQuestions);
		}
		}

		return rootQuestions;
	}
	
	/**
	 * 
	 * @param key
	 * @return RootQuestionnaireBO
	 */
	private RootQuestionnaireBO generateRootQuestionnaireBO(Object key) {
		RootQuestionnaireBO rootQuestionnaireBO = null;
		Long keyValue = null;
		if (null != key) {
			keyValue = Long.valueOf(key.toString());
			rootQuestionnaireBO = new RootQuestionnaireBO();
			//if (null != this.deleteMap && null != this.deleteMap.get(keyValue)) {
				//if (!(Boolean.valueOf(this.deleteMap.get(keyValue).toString()))
					//	.booleanValue()) {
					// set the required details to the bo.
					if (null != this.sequenceMap
							&& null != this.sequenceMap.get(keyValue)
							&& !this.sequenceMap.get(keyValue).toString()
									.equals("")) {
						rootQuestionnaireBO.setSequenceNumber(Integer
								.parseInt(this.sequenceMap.get(keyValue)
										.toString()));
					}
					if (null != this.hiddenReferenceIdMap
							&& null != this.hiddenReferenceIdMap.get(keyValue)) {
						if (this.hiddenReferenceIdMap.get(keyValue).toString()
								.split("~").length > 1)
							rootQuestionnaireBO
									.setReferenceId(this.hiddenReferenceIdMap
											.get(keyValue).toString()
											.split("~")[1]);
					}
					rootQuestionnaireBO
							.setQuestionnaireHierachySystemId((Integer
									.parseInt(key.toString())));
				/*} else {
					if (null == rootQuestionnairesToDeleted)
						rootQuestionnairesToDeleted = key.toString();
					else
						rootQuestionnairesToDeleted = rootQuestionnairesToDeleted
								+ "~" + key;*/
				//}
			//}
		}
		return rootQuestionnaireBO;
	}

	/**
	 * 
	 * @param key
	 * 
	 */
	private RootQuestionnaireBO generateRootQuestionnaireForDelete(Object key) {
		Long keyValue = null;
		RootQuestionnaireBO rootQuestionnaireBO = null;
		if (null != key) {
			keyValue = Long.valueOf(key.toString());
			if (null != this.deleteMap && null != this.deleteMap.get(keyValue)) {
				if ((Boolean.valueOf(this.deleteMap.get(keyValue).toString()))
						.booleanValue()) {			
				
					if (null == rootQuestionnairesToDeleted)
						rootQuestionnairesToDeleted = key.toString();
					else
						rootQuestionnairesToDeleted = rootQuestionnairesToDeleted
								+ "~" + key;
				}else{
					rootQuestionnaireBO = new RootQuestionnaireBO();
					rootQuestionnaireBO
						.setQuestionnaireHierachySystemId((Integer
							.parseInt(key.toString())));
					if (null != this.sequenceMap
							&& null != this.sequenceMap.get(keyValue)
							&& !this.sequenceMap.get(keyValue).toString()
									.equals("")) {
						rootQuestionnaireBO.setSequenceNumber(Integer
								.parseInt(this.sequenceMap.get(keyValue)
										.toString()));
					}
					if (null != this.hiddenReferenceIdMap
							&& null != this.hiddenReferenceIdMap.get(keyValue)) {
						if (this.hiddenReferenceIdMap.get(keyValue).toString()
								.split("~").length > 1)
							rootQuestionnaireBO
									.setReferenceId(this.hiddenReferenceIdMap
											.get(keyValue).toString()
											.split("~")[1]);
					}
				}
			}
		}
		return rootQuestionnaireBO;
	}
		
	private void preparePanel(){
		this.getDisplayPanelForAdd();
	}
	
	/**
	 * @return Returns the headerPanelForAdd.
	 */
	public HtmlPanelGrid getHeaderPanelForAdd() {
		
		headerPanelForAdd = new HtmlPanelGrid();
		
		headerPanelForAdd.setBgcolor("#cccccc");
		headerPanelForAdd.setColumns(2);
		headerPanelForAdd.setWidth("100%");
		headerPanelForAdd.setBorder(0);
		headerPanelForAdd.setStyleClass("dataTableHeader");
		
		HtmlOutputText outputText1 = new HtmlOutputText();
		HtmlOutputText outputText2 = new HtmlOutputText();
		
		outputText1.setId("quesHeader");
		outputText1.setValue("Question");
		
		outputText2.setId("refHeader");
		outputText2.setValue("Reference");
		
		headerPanelForAdd.getChildren().add(outputText1);
		headerPanelForAdd.getChildren().add(outputText2);
		
		return headerPanelForAdd;
	}
	/**
	 * @param headerPanelForAdd The headerPanelForAdd to set.
	 */
	public void setHeaderPanelForAdd(HtmlPanelGrid headerPanelForAdd) {
		this.headerPanelForAdd = headerPanelForAdd;
	}
	
	/**
	 * @return Returns the displayPanelForAdd.
	 */
	public HtmlPanelGrid getDisplayPanelForAdd() {
		displayPanelForAdd = new HtmlPanelGrid();
		
		displayPanelForAdd.setBgcolor("#cccccc");
		displayPanelForAdd.setBorder(0);
		displayPanelForAdd.setWidth("100%");
		displayPanelForAdd.setCellspacing("1");
		displayPanelForAdd.setColumns(2);
		
		if(null != this.selectedQuestionsList && !this.selectedQuestionsList.isEmpty()){
			

					
			for(int i=0; i<this.selectedQuestionsList.size(); i++){
				
				RootQuestionnaireBO rootQuestionnaireBO = (RootQuestionnaireBO) this.selectedQuestionsList.get(i);
				rootQuestionnaireBO.setQuestionnaireHierachySystemId(i);
				HtmlOutputText outputLabel1 = getQuestionFieldForPanel(rootQuestionnaireBO);
				HtmlOutputLabel outputLabel2 = getReferenceFieldForPanel(rootQuestionnaireBO);
				outputLabel2.setId("outputLabel2"+i);
				displayPanelForAdd.getChildren().add(outputLabel1);
				displayPanelForAdd.getChildren().add(outputLabel2);
			}
		}
		return displayPanelForAdd;
	}

	
	/**
	 * @param displayPanelForAdd The displayPanelForAdd to set.
	 */
	public void setDisplayPanelForAdd(HtmlPanelGrid displayPanelForAdd) {
		this.displayPanelForAdd = displayPanelForAdd;
	}
	
	/**
	 * @return Returns the displayPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {
		headerPanel = new HtmlPanelGrid();
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setColumns(8);
		headerPanel.setWidth("100%");
		headerPanel.setBorder(0);
		HtmlOutputText otxtType1 = new HtmlOutputText();
		HtmlOutputText otxtType2 = new HtmlOutputText();
		HtmlOutputText otxtType3 = new HtmlOutputText();
		HtmlOutputText otxtType4 = new HtmlOutputText();
		HtmlOutputText otxtType5 = new HtmlOutputText();
		HtmlOutputText otxtType6 = new HtmlOutputText();
		HtmlOutputText otxtType7 = new HtmlOutputText();
		HtmlCommandButton otxtType8 = new HtmlCommandButton();
		HtmlOutputText otxtType9 = new HtmlOutputText();
		otxtType2.setValue("Sequence");
		otxtType2.setId("seq");
		otxtType3.setValue("Question");
		otxtType3.setId("question");

		otxtType4.setValue("Reference");
		otxtType4.setId("reference");

		otxtType5.setValue("Line Of Business");
		otxtType5.setId("lob");

		otxtType6.setValue("Business Entity");
		otxtType7.setId("be");

		otxtType7.setValue("Business Group");
		otxtType7.setId("bg");
		
		otxtType9.setValue("Market Business Unit");
		otxtType9.setId("bu");
		
		otxtType8.setValue("Delete");
		otxtType8.setStyleClass("wpdButton");
		otxtType8.setId("delete");
		otxtType8.setAlt("Delete");
		otxtType8.setDisabled(true);
		otxtType8.setOnclick("javascript:deleteAction();return false;");

		headerPanel.getChildren().add(otxtType2);
		headerPanel.getChildren().add(otxtType3);
		headerPanel.getChildren().add(otxtType4);
		headerPanel.getChildren().add(otxtType5);
		headerPanel.getChildren().add(otxtType6);
		headerPanel.getChildren().add(otxtType7);
		headerPanel.getChildren().add(otxtType9);
		headerPanel.getChildren().add(otxtType8);
		headerPanel.setStyleClass("dataTableHeader");
		return headerPanel;
	}

	/**
	 * @param displayPanel
	 *            The displayPanel to set.
	 */
	public void setDisplayPanel(HtmlPanelGrid displayPanel) {
		this.displayPanel = displayPanel;
	}

	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getDisplayPanel() {
		displayPanel = new HtmlPanelGrid();
		displayPanel.setColumns(8);
		displayPanel.setWidth("100%");
		displayPanel.setBorder(0);
		displayPanel.setCellspacing("1");
		displayPanel.setBgcolor("#cccccc");
		if (null != this.questionListRetrieved) {
			for (int i = 0; i < this.questionListRetrieved.size(); i++) {
				RootQuestionnaireBO rootQuestionnaireBO = (RootQuestionnaireBO) this.questionListRetrieved
						.get(i);
				HtmlOutputLabel outputLabel = getSequenceLabel(rootQuestionnaireBO);
				HtmlOutputText questionLabel = getQuestionLabelForPanel(rootQuestionnaireBO);
				HtmlOutputLabel referenceLabel = getReferenceLabelForPanel(rootQuestionnaireBO);
				referenceLabel.setId("referenceLabel"+i);
				//HtmlOutputLabel lobLabel =
				// getLineOfBusinessLabelForPanel(rootQuestionnaireBO);
				HtmlOutputText lobText = new HtmlOutputText();
				lobText.setId("lobText" + i);
				lobText.setValue("ALL");
				HtmlOutputText entityText = new HtmlOutputText();
				entityText.setId("entityText" + i);
				entityText.setValue("ALL");
				HtmlOutputText groupText = new HtmlOutputText();
				groupText.setId("groupText" + i);
				groupText.setValue("ALL");
				HtmlOutputText unitText = new HtmlOutputText();
				unitText.setId("unitText" + i);
				unitText.setValue("ALL");
				//HtmlOutputLabel businessEntityLabel =
				// getBusinessEntityLabelForPanel(rootQuestionnaireBO);
				//HtmlOutputLabel businessGroupLabel =
				// getBusinessGroupLabelForPanel(rootQuestionnaireBO);
				HtmlOutputLabel deleteLabel = getDeleteLabelForPanel(rootQuestionnaireBO);

				displayPanel.getChildren().add(outputLabel);
				displayPanel.getChildren().add(questionLabel);
				displayPanel.getChildren().add(referenceLabel);
				displayPanel.getChildren().add(lobText);
				displayPanel.getChildren().add(entityText);
				displayPanel.getChildren().add(groupText);
				displayPanel.getChildren().add(unitText);
				displayPanel.getChildren().add(deleteLabel);
			}
		}
		return displayPanel;
	}

	/**
	 * 
	 * @param headerPanel
	 */
	private HtmlOutputLabel getSequenceLabel(
			RootQuestionnaireBO rootQuestionnaireBO) {
		HtmlOutputLabel outputLabel = new HtmlOutputLabel();
		HtmlInputText sequenceInputText = new HtmlInputText();
		HtmlInputHidden quesionnaireHierachyHidden = new HtmlInputHidden();

		outputLabel.setId("outputLabel"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());

		// set the sequence number in the input text.
		sequenceInputText.setStyleClass("formInputField");
		sequenceInputText.setStyle("width:50px;");
		sequenceInputText.setValue(Integer.toString(rootQuestionnaireBO
				.getSequenceNumber()));
		sequenceInputText.setId("sequenceInputText"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		sequenceInputText.setStyleClass("formInputField");
		sequenceInputText.setOnkeypress("isNum1(this.id);");
		sequenceInputText.setMaxlength(7);
		ValueBinding valForSequence = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.sequenceMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		sequenceInputText.setValueBinding("value", valForSequence);

		// set the questionnaire hierarchy id in the hidden map.
		quesionnaireHierachyHidden.setId("hiddenQuestionnaireHierarchy"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		quesionnaireHierachyHidden.setValue(Integer
				.toString(rootQuestionnaireBO
						.getQuestionnaireHierachySystemId()));
		ValueBinding valForHiddenQuestionnaireHierarchy = FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.hiddenQuestionnaireHierarchyStaticMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		quesionnaireHierachyHidden.setValueBinding("value",
				valForHiddenQuestionnaireHierarchy);

		outputLabel.getChildren().add(sequenceInputText);
		outputLabel.getChildren().add(quesionnaireHierachyHidden);
		return outputLabel;
	}

	/**
	 * Method to generate the question label
	 * 
	 * @return
	 */
	private HtmlOutputText getQuestionLabelForPanel(
			RootQuestionnaireBO rootQuestionnaireBO) {
		//HtmlOutputLabel questionLabel = new HtmlOutputLabel();
		HtmlOutputText questionOutputText = new HtmlOutputText();
		HtmlInputHidden questionNumberHidden = new HtmlInputHidden();
		HtmlInputHidden questionNumberStaticHidden = new HtmlInputHidden();

		// set the question description to the question text
		//questionOutputText.setStyleClass("formInputField");
		questionOutputText.setId("questionInputText"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		questionOutputText.setValue(rootQuestionnaireBO
				.getQuestionDescription());

		//questionSelectImage.setOnclick("javascript:selectQuestion('"+rootQuestionnaireBO.getQuestionnaireHierachySystemId()+"');");

		// set the question number in the dynamic hidden map.
		questionNumberHidden.setId("questionNumberHidden"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		questionNumberHidden.setValue(rootQuestionnaireBO
				.getQuestionDescription()
				+ "~"
				+ Integer.toString(rootQuestionnaireBO.getQuestionNumber()));
		ValueBinding valForHiddenQuestionNumber = FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.hiddenQuestionNumberMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		questionNumberHidden.setValueBinding("value",
				valForHiddenQuestionNumber);

		// set the question number in the static hidden map.
		questionNumberStaticHidden.setId("questionNumberStaticHidden"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		questionNumberStaticHidden.setValue(rootQuestionnaireBO
				.getQuestionDescription()
				+ "~"
				+ Integer.toString(rootQuestionnaireBO.getQuestionNumber()));
		ValueBinding valForStaticHiddenQuestionNumber = FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.hiddenQuestionNumberStaticMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		questionNumberStaticHidden.setValueBinding("value",
				valForStaticHiddenQuestionNumber);
		questionOutputText.getChildren().add(questionNumberHidden);
		questionOutputText.getChildren().add(questionNumberStaticHidden);
		return questionOutputText;
	}
	/**
	 * 
	 * @param rootQuestionnaireBO
	 * @return HtmlOutputText
	 */
	private HtmlOutputText getQuestionFieldForPanel(RootQuestionnaireBO rootQuestionnaireBO){
		HtmlOutputText outputText = new HtmlOutputText();
		
		HtmlInputHidden questionHidden = new HtmlInputHidden();
		HtmlInputHidden questionStaticHidden = new HtmlInputHidden();
		
		outputText.setId("questionOutput"+rootQuestionnaireBO.getQuestionNumber());
		outputText.setValue(rootQuestionnaireBO.getQuestionDescription());
		//outputText.setStyleClass("formInputFieldForQuestion");
		
		questionHidden.setId("questionHidden"+rootQuestionnaireBO.getQuestionNumber());
		questionHidden.setValue(rootQuestionnaireBO.getQuestionDescription() + "~" + Integer.toString(rootQuestionnaireBO.getQuestionNumber()));
		ValueBinding bindingForQuestion = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{rootQuestionsBackingBean.hiddenQuestionNumberMap[" + rootQuestionnaireBO.getQuestionNumber() + "]}");
		questionHidden.setValueBinding("value", bindingForQuestion);
		questionStaticHidden.setId("questionStaticHidden" + rootQuestionnaireBO.getQuestionNumber());
		questionStaticHidden.setValue(rootQuestionnaireBO.getQuestionDescription() + "~" + 
				Integer.toString(rootQuestionnaireBO.getQuestionNumber()));
		ValueBinding bindingForStaticQuestion = FacesContext.getCurrentInstance().getApplication()
			.createValueBinding("#{rootQuestionsBackingBean.hiddenQuestionNumberStaticMap["
					+ rootQuestionnaireBO.getQuestionNumber() + "]}");
		questionStaticHidden.setValueBinding("value", bindingForStaticQuestion);
		outputText.getChildren().add(questionHidden);
		outputText.getChildren().add(questionStaticHidden);
		
		return outputText;
	}

	private HtmlOutputLabel getReferenceFieldForPanel(RootQuestionnaireBO rootQuestionnaireBO){
		HtmlOutputLabel outputLabel = new HtmlOutputLabel();
		
		HtmlOutputText outputText = new HtmlOutputText();
		HtmlOutputText reference = new HtmlOutputText();
		
		reference.setValue(rootQuestionnaireBO.getReferenceDescription());
		this.hiddenReferenceIdMap.put(new Integer(rootQuestionnaireBO.getQuestionNumber()),rootQuestionnaireBO.getSpsReference());
		
		//To add empty space between refernce field and select button
		outputText.setValue(" ");
		
		outputLabel.getChildren().add(outputText);
		outputLabel.getChildren().add(reference);
		
		
		return outputLabel;
	}
	
	/**
	 * Method to generate the reference label
	 * 
	 * @return
	 */
	private HtmlOutputLabel getReferenceLabelForPanel(
			RootQuestionnaireBO rootQuestionnaireBO) {
		HtmlOutputLabel referenceLabel = new HtmlOutputLabel();
	//	HtmlInputText referenceInputText = new HtmlInputText();
	//	HtmlGraphicImage referenceSelectImage = new HtmlGraphicImage();
		HtmlInputHidden referenceIdHidden = new HtmlInputHidden();
		
		rootQuestionnaireBO.setSpsReference(rootQuestionnaireBO.getReferenceDescription());
		
		HtmlOutputText reference = new HtmlOutputText();
		reference.setValue(rootQuestionnaireBO.getSpsReference());
		reference.setStyleClass("formInputFieldForReference");
		
		HtmlOutputText space = new HtmlOutputText();
		space.setValue(" ");
		// set the reference description to the question text
		//referenceInputText.setStyleClass("formInputFieldForReference");
		//referenceInputText.setId("referenceInputText"	+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		//referenceInputText.setValue(rootQuestionnaireBO.getReferenceDescription());
		//referenceInputText.setReadonly(true);
		ValueBinding valForReferenceId = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.referenceIdMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		//referenceInputText.setValueBinding("value", valForReferenceId);

		// set the select image for the reference select.
	//	referenceSelectImage.setId("referenceSelectImage"+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
	//	referenceSelectImage.setUrl("../../images/select.gif");
	//	referenceSelectImage.setStyle("cursor:hand;");
	//	referenceSelectImage.setOnclick("ewpdModalWindow_ewpd('../adminoptionspopups/selectOneReferencePopupFilterSearch.jsp?lookUpAction =" + 6
//						+ "&parentCatalog = reference', "
//						+ "'editRootQuestionForm:referenceInputText"
//						+ rootQuestionnaireBO.getQuestionnaireHierachySystemId()
//						+ "', 'editRootQuestionForm:referenceIdHidden"
//						+ rootQuestionnaireBO.getQuestionnaireHierachySystemId()
//						+ "', 1, 1)");

		// set the reference id in the dynamic hidden map.
		referenceIdHidden.setId("referenceIdHidden"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		referenceIdHidden.setValue(rootQuestionnaireBO
				.getReferenceDescription()
				+ "~" + rootQuestionnaireBO.getReferenceId());
		ValueBinding valForHiddenReferenceId = FacesContext
				.getCurrentInstance().getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.hiddenReferenceIdMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		referenceIdHidden.setValueBinding("value", valForHiddenReferenceId);

		//referenceLabel.getChildren().add(referenceInputText);
		referenceLabel.getChildren().add(reference);
		referenceLabel.getChildren().add(space);
	//	referenceLabel.getChildren().add(referenceSelectImage);
		referenceLabel.getChildren().add(referenceIdHidden);
		return referenceLabel;
	}

	/**
	 * Method to generate the delete label
	 * 
	 * @return
	 */
	private HtmlOutputLabel getDeleteLabelForPanel(
			RootQuestionnaireBO rootQuestionnaireBO) {
		HtmlOutputLabel deleteLabel = new HtmlOutputLabel();
		HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();

		deleteCheckBox.setId("deleteCheckBox"
				+ rootQuestionnaireBO.getQuestionnaireHierachySystemId());
		deleteCheckBox.setValue(Boolean.FALSE);
		ValueBinding valForDelete = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{rootQuestionsBackingBean.deleteMap["
								+ rootQuestionnaireBO
										.getQuestionnaireHierachySystemId()
								+ "]}");
		deleteCheckBox.setValueBinding("value", valForDelete);
		deleteCheckBox.setOnclick("javascript:changeButton(this);");
		deleteLabel.getChildren().add(deleteCheckBox);

		return deleteLabel;
	}

	/**
	 * @param headerPanel
	 *            The headerPanel to set.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	/**
	 * @return Returns the questionListRetrieved.
	 */
	public List getQuestionListRetrieved() {
		if (null != getRequest().getAttribute("adminOptionId")) {
			getRequest().getSession().setAttribute("rootQuestAdminId",
					getRequest().getAttribute("adminOptionId"));
			this.adminId = Integer.parseInt((String) getRequest().getAttribute(
					"adminOptionId"));

		}
		LocateRootQuestionRequest locateRootQuestionRequest = this
				.createLocateRootQuestionRequest();
		LocateRootQuestionResponse locateRootQuestionResponse = null;
		locateRootQuestionResponse = (LocateRootQuestionResponse) this
				.executeService(locateRootQuestionRequest);
		if (null != locateRootQuestionResponse
				&& null != locateRootQuestionResponse.getRootQuestionsList()) {
			this.questionListRetrieved = locateRootQuestionResponse
					.getRootQuestionsList();
			this.getSession().setAttribute("rootQuestionList",
					this.questionListRetrieved);
		}
		SequenceUtil sequenceUtil = new SequenceUtil();
		sequenceUtil.registerObjects(questionListRetrieved,
				"questionnaireHierachySystemId", "sequenceNumber");
		return questionListRetrieved;
	}

	/**
	 * @param questionListRetrieved
	 *            The questionListRetrieved to set.
	 */
	public void setQuestionListRetrieved(List questionListRetrieved) {
		this.questionListRetrieved = questionListRetrieved;
	}

	/**
	 * @return Returns the selectedQuestions.
	 */
	public String getSelectedQuestions() {
		return selectedQuestions;
	}

	/**
	 * @param selectedQuestions
	 *            The selectedQuestions to set.
	 */
	public void setSelectedQuestions(String selectedQuestions) {
		this.selectedQuestions = selectedQuestions;
	}

	/**
	 * @return Returns the selectedQuestionsList.
	 */
	public List getSelectedQuestionsList() {
		return selectedQuestionsList;
	}

	/**
	 * @param selectedQuestionsList
	 *            The selectedQuestionsList to set.
	 */
	public void setSelectedQuestionsList(List selectedQuestionsList) {
		this.selectedQuestionsList = selectedQuestionsList;
	}

	/**
	 * @return Returns the adminId.
	 */
	public int getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId
	 *            The adminId to set.
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return Returns the adminName.
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName
	 *            The adminName to set.
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * @return Returns the adminVersion.
	 */
	public int getAdminVersion() {
		return adminVersion;
	}

	/**
	 * @param adminVersion
	 *            The adminVersion to set.
	 */
	public void setAdminVersion(int adminVersion) {
		this.adminVersion = adminVersion;
	}

	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}

	/**
	 * @param businessEntity
	 *            The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	/**
	 * @return Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * @param businessGroup
	 *            The businessGroup to set.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	/**
	 * @return Returns the lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	/**
	 * @param lineOfBusiness
	 *            The lineOfBusiness to set.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return Returns the validateFlag.
	 */
	public boolean isValidateFlag() {
		return validateFlag;
	}

	/**
	 * @param validateFlag
	 *            The validateFlag to set.
	 */
	public void setValidateFlag(boolean validateFlag) {
		this.validateFlag = validateFlag;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the sequenceMap.
	 */
	public HashMap getSequenceMap() {
		return sequenceMap;
	}

	/**
	 * @param sequenceMap
	 *            The sequenceMap to set.
	 */
	public void setSequenceMap(HashMap sequenceMap) {
		this.sequenceMap = sequenceMap;
	}

	/**
	 * @return Returns the answersMap.
	 */
	public HashMap getAnswersMap() {
		return answersMap;
	}

	/**
	 * @param answersMap
	 *            The answersMap to set.
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
	 * @param deleteMap
	 *            The deleteMap to set.
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
	 * @param hiddenAnswersStaticMap
	 *            The hiddenAnswersStaticMap to set.
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
	 * @param hiddenBusinessEntityMap
	 *            The hiddenBusinessEntityMap to set.
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
	 * @param hiddenBusinessGroupMap
	 *            The hiddenBusinessGroupMap to set.
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
	 * @param hiddenLOBMap
	 *            The hiddenLOBMap to set.
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
	 * @param hiddenQuestionnaireHierarchyStaticMap
	 *            The hiddenQuestionnaireHierarchyStaticMap to set.
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
	 * @param hiddenQuestionNumberMap
	 *            The hiddenQuestionNumberMap to set.
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
	 * @param hiddenQuestionNumberStaticMap
	 *            The hiddenQuestionNumberStaticMap to set.
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
	 * @param hiddenReferenceIdMap
	 *            The hiddenReferenceIdMap to set.
	 */
	public void setHiddenReferenceIdMap(HashMap hiddenReferenceIdMap) {
		this.hiddenReferenceIdMap = hiddenReferenceIdMap;
	}

	/**
	 * @return Returns the validateReferenceFlag.
	 */
	public boolean isValidateReferenceFlag() {
		return validateReferenceFlag;
	}

	/**
	 * @param validateReferenceFlag
	 *            The validateReferenceFlag to set.
	 */
	public void setValidateReferenceFlag(boolean validateReferenceFlag) {
		this.validateReferenceFlag = validateReferenceFlag;
	}

	/**
	 * @return Returns the quesValidateFlag.
	 */
	public boolean isQuesValidateFlag() {
		return quesValidateFlag;
	}

	/**
	 * @param quesValidateFlag
	 *            The quesValidateFlag to set.
	 */
	public void setQuesValidateFlag(boolean quesValidateFlag) {
		this.quesValidateFlag = quesValidateFlag;
	}

	/**
	 * @return Returns the rootQuestionnairesToDeleted.
	 */
	public String getRootQuestionnairesToDeleted() {
		return rootQuestionnairesToDeleted;
	}

	/**
	 * @param rootQuestionnairesToDeleted
	 *            The rootQuestionnairesToDeleted to set.
	 */
	public void setRootQuestionnairesToDeleted(
			String rootQuestionnairesToDeleted) {
		this.rootQuestionnairesToDeleted = rootQuestionnairesToDeleted;
	}

	/**
	 * @return Returns the referenceIdMap.
	 */
	public HashMap getReferenceIdMap() {
		return referenceIdMap;
	}

	/**
	 * @param referenceIdMap
	 *            The referenceIdMap to set.
	 */
	public void setReferenceIdMap(HashMap referenceIdMap) {
		this.referenceIdMap = referenceIdMap;
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
		//get the rootQuestAdminId from the request.
		if(null != getRequest().getParameter("adminOptionId")){
			this.adminId = 
						Integer.parseInt(getRequest().
								getParameter("adminOptionId"));
		}
		// set the admin option name and admin option version number
		if(null != getRequest().getParameter("adminOptionName")){
			this.adminName = getRequest().
					getParameter("adminOptionName");
		}
		if(null != getRequest().getParameter("adminOptionVersion")){
			this.adminVersion = Integer.parseInt(getRequest().getParameter("adminOptionVersion"));
		}
		if(null != getRequest().getParameter("submitPage")){
			this.submitPage = getRequest().getParameter("submitPage");
		}
		return "";
	}
	/**
	 * 
	 * @return
	 */
	public String submitAction(){
		this.getRequest().setAttribute("adminOptionId",String.valueOf(this.adminId));
		this.getRequest().setAttribute("adminOptionName",this.adminName);
		this.getRequest().setAttribute("adminOptionVersion",
				String.valueOf(this.adminVersion));
		return "editRootQuestion";
	}
	/**
	 * 
	 * @return
	 */
	public String submitAddAction(){
		this.getRequest().setAttribute("adminOptionId",String.valueOf(this.adminId));
		this.getRequest().setAttribute("adminOptionName",this.adminName);
		this.getRequest().setAttribute("adminOptionVersion",
				String.valueOf(this.adminVersion));
		return "addRootQuestionnaire";
	}
	/**
	 * @return Returns the closeFlag.
	 */
	public boolean isCloseFlag() {
		return closeFlag;
	}
	/**
	 * @param closeFlag The closeFlag to set.
	 */
	public void setCloseFlag(boolean closeFlag) {
		this.closeFlag = closeFlag;
	}
	/**
	 * @return Returns the submitPage.
	 */
	public String getSubmitPage() {
		return submitPage;
	}
	/**
	 * @param submitPage The submitPage to set.
	 */
	public void setSubmitPage(String submitPage) {
		this.submitPage = submitPage;
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
	/**
	 * @return Returns the hiddenMarketBusinessUnitMap.
	 */
	public HashMap getHiddenMarketBusinessUnitMap() {
		return hiddenMarketBusinessUnitMap;
	}
	/**
	 * @param hiddenMarketBusinessUnitMap The hiddenMarketBusinessUnitMap to set.
	 */
	public void setHiddenMarketBusinessUnitMap(
			HashMap hiddenMarketBusinessUnitMap) {
		this.hiddenMarketBusinessUnitMap = hiddenMarketBusinessUnitMap;
	}
}