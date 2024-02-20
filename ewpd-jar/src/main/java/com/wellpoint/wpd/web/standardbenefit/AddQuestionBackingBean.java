/*
 * AddQuestionBackingBean.java
 * 
 * Created on Feb 20, 2007
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.business.standardbenefit.locatecriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.AnswerBO;
import com.wellpoint.wpd.common.standardbenefit.bo.Question;
import com.wellpoint.wpd.common.standardbenefit.bo.SelectedQuestionListBO;
import com.wellpoint.wpd.common.standardbenefit.request.HideQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateSelectedQuestionListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.PossibleAnswersRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveOpenQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.SaveQuestionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.HideQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateSelectedQuestionListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PossibleAnswersResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveOpenQuestionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.SaveQuestionResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
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
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletRequest;

public class AddQuestionBackingBean extends WPDBackingBean {

    private HtmlPanelGrid headerPanelForPrint = new HtmlPanelGrid();

    private HtmlPanelGrid panelForPrint = new HtmlPanelGrid();

    private HtmlPanelGrid headerPanelForView = new HtmlPanelGrid();

    private HtmlPanelGrid panelForView = new HtmlPanelGrid();

    private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private HtmlPanelGrid viewHeaderPanel = new HtmlPanelGrid();

    private HtmlPanelGrid viewPanel = new HtmlPanelGrid();

    private Map quesForHidMap = new HashMap();

    List locateResultList;

    List questionViewList;

    List hiddenQuesList;

    private Question question;

    private Map sessionScope;

    private String selectedQuestions;

    private String selectedAnswers;

    // adminSysId
    private int adminOptionSysId;

    private List openQuestionList;

    private List answerList;

    private List hiddenQuestionList;

    private String loadPurposeForprint;

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD;

    // map for value binding
    private HashMap seqNoMap = new HashMap();

    private HashMap quesNoMap = new HashMap();

    private HashMap answerMap = new HashMap();
    
    private HashMap dataHiddenValReference = new HashMap();
    
    private HashMap dataHiddenValReferenceCode = new HashMap();

    private String selectedRow;

    private String loadPurpose;

    private List messages;

    private String tabindexForAddQuestionButton;

    private String hiddenQuestionListSize = "0";
    
    private String hiddenOpenQuestionListSize = "0";
    
    private int standardBenefitIdForRefData;
    
    private HashMap datahiddenReferenceText = new HashMap();
    
    private HashMap datahiddenReferenceCodeText = new HashMap();
    
    private String openQuestionRecords;
	//added new fields for solving performance issue
    private HashMap hiddenReferenceMap = new HashMap();
    private HashMap hiddenSequenceMap = new HashMap();
    private HashMap hiddenAnswerMap = new HashMap();
    private HashMap deleteQuestionCheckBoxMap = new HashMap();
   
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
     * @return hiddenReferenceMap
     * 
     * Returns the hiddenReferenceMap.
     */
    public HashMap getHiddenReferenceMap() {
        return hiddenReferenceMap;
    }
    /**
     * @param hiddenReferenceMap
     * 
     * Sets the hiddenReferenceMap.
     */
    public void setHiddenReferenceMap(HashMap hiddenReferenceMap) {
        this.hiddenReferenceMap = hiddenReferenceMap;
    }
    /**
     * @return hiddenSequenceMap
     * 
     * Returns the hiddenSequenceMap.
     */
    public HashMap getHiddenSequenceMap() {
        return hiddenSequenceMap;
    }
    /**
     * @param hiddenSequenceMap
     * 
     * Sets the hiddenSequenceMap.
     */
    public void setHiddenSequenceMap(HashMap hiddenSequenceMap) {
        this.hiddenSequenceMap = hiddenSequenceMap;
    }
	/**
	 * @return Returns the openQuestionRecords.
	 */
	public String getOpenQuestionRecords() {
		
		 //openQuestionList = new ArrayList();

        RetrieveOpenQuestionRequest retrieveOpenQuestionRequest = (RetrieveOpenQuestionRequest) this
                .getServiceRequest("retrieveOpenQuestionRequest");
       int standardBenefitKey = getStandardBenefitSessionObject().getStandardBenefitKey();
       
        QuestionLocateCriteria questionLocateCriteria = new QuestionLocateCriteria();
        questionLocateCriteria.setLocateAction(1);
        //questionLocateCriteria.setAdminOptionsSysId(111);
        questionLocateCriteria.setSystemId(new Integer(getSession()
                .getAttribute(WebConstants.SESSION_ADMIN_OPTN_ID).toString())
                .intValue());
        questionLocateCriteria.setStandardBenefitKey(new Integer(standardBenefitKey)
                .intValue());
        questionLocateCriteria.setAdminOptionsSysId(new Integer(getSession()
                .getAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN).toString())
                .intValue());
        retrieveOpenQuestionRequest
                .setOpenQuestionLocateCriteria(questionLocateCriteria);
        RetrieveOpenQuestionResponse retrieveOpenQuestionResponse = (RetrieveOpenQuestionResponse) this
                .executeService(retrieveOpenQuestionRequest);
        openQuestionList = retrieveOpenQuestionResponse.getOpenQuestionList();
        hiddenOpenQuestionListSize = new Integer(openQuestionList.size()).toString();
        this.setOpenQuestionList(openQuestionList);
        
		return openQuestionRecords;
	}
	/**
	 * @param openQuestionRecords The openQuestionRecords to set.
	 */
	public void setOpenQuestionRecords(String openQuestionRecords) {
		this.openQuestionRecords = openQuestionRecords;
	}
        /**
     * @return datahiddenReferenceCodeText
     * 
     * Returns the datahiddenReferenceCodeText.
     */
    public HashMap getDatahiddenReferenceCodeText() {
        return datahiddenReferenceCodeText;
    }
    /**
     * @param datahiddenReferenceCodeText
     * 
     * Sets the datahiddenReferenceCodeText.
     */
    public void setDatahiddenReferenceCodeText(
            HashMap datahiddenReferenceCodeText) {
        this.datahiddenReferenceCodeText = datahiddenReferenceCodeText;
    }
    /**
     * @return datahiddenReferenceText
     * 
     * Returns the datahiddenReferenceText.
     */
    public HashMap getDatahiddenReferenceText() {
        return datahiddenReferenceText;
    }
    /**
     * @param datahiddenReferenceText
     * 
     * Sets the datahiddenReferenceText.
     */
    public void setDatahiddenReferenceText(HashMap datahiddenReferenceText) {
        this.datahiddenReferenceText = datahiddenReferenceText;
    }
/**
     * @return standardBenefitIdForRefData
     * 
     * Returns the standardBenefitIdForRefData.
     */
    public int getStandardBenefitIdForRefData() {
        return standardBenefitIdForRefData;
    }
    /**
     * @param standardBenefitIdForRefData
     * 
     * Sets the standardBenefitIdForRefData.
     */
    public void setStandardBenefitIdForRefData(int standardBenefitIdForRefData) {
        this.standardBenefitIdForRefData = standardBenefitIdForRefData;
    }
    /**
     * @return dataHiddenValReferenceCode
     * 
     * Returns the dataHiddenValReferenceCode.
     */
    public HashMap getDataHiddenValReferenceCode() {
        return dataHiddenValReferenceCode;
    }
    /**
     * @param dataHiddenValReferenceCode
     * 
     * Sets the dataHiddenValReferenceCode.
     */
    public void setDataHiddenValReferenceCode(HashMap dataHiddenValReferenceCode) {
        this.dataHiddenValReferenceCode = dataHiddenValReferenceCode;
    }
    /**
     * @return dataHiddenValReference
     * 
     * Returns the dataHiddenValReference.
     */
    public HashMap getDataHiddenValReference() {
        return dataHiddenValReference;
    }
    /**
     * @param dataHiddenValReference
     * 
     * Sets the dataHiddenValReference.
     */
    public void setDataHiddenValReference(HashMap dataHiddenValReference) {
        this.dataHiddenValReference = dataHiddenValReference;
    }
    /**
     * @return Returns the loadPurpose.
     */
    public String getLoadPurpose() {
        loadPanel();
        if (null != messages && !messages.isEmpty()) {
            HttpServletRequest request = getRequest();
            request.setAttribute("messages", messages);
        }
        return loadPurpose;
    }


    /**
     * @param loadPurpose
     *            The loadPurpose to set.
     */
    public void setLoadPurpose(String loadPurpose) {
        this.loadPurpose = loadPurpose;
    }


    /**
     * This method is to get the possible answers from the table based on the
     * selectedQuestion
     * 
     * @return
     */
    private List getPossibleAnswersForSelecteQuestion(String questionNo,
            PossibleAnswersRequest possibleAnswersRequest) {
        List possibleAnswers = new ArrayList();
        // create the request
        possibleAnswersRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        // set the searchQuestionNumber in the request
        if (null != questionNo && !"".equals(questionNo)) {
            possibleAnswersRequest.setQuestionNumber(new Integer(questionNo)
                    .intValue());
        }
        // get the response
        PossibleAnswersResponse possibleAnswersResponse = (PossibleAnswersResponse) executeService(possibleAnswersRequest);
        // get the list of possibleAnswersBO from the response
        List possibleAnswersFromResponse = possibleAnswersResponse
                .getPossibleAnswers();
        // iterate the list and get the BO and generate the new SelectItem and
        // add to the list
        if (null != possibleAnswersFromResponse
                && !possibleAnswersFromResponse.isEmpty()) {
            for (int i = 0; i < possibleAnswersFromResponse.size(); i++) {
                // get the bo from the list
                AnswerBO answerBO = (AnswerBO) possibleAnswersFromResponse
                        .get(i);
                // create the new SelectItem with the answerid and desc
                SelectItem selectItem = new SelectItem(new Integer(answerBO
                        .getAnswerId()).toString(), answerBO.getAnswerDesc());
                // add it to the possibleAnswersList
                possibleAnswers.add(selectItem);
            }
        }

        // return the list
        return possibleAnswers;
    }

    /**
     * This method is to update the SeqNo
     * 
     * @return
     */
    public String updateSeqNo() {
        // get the all details from the map and make each row in the page as a
        // Question Object
        List updatedQuestionList = new ArrayList();
        if (null != this.getQuesNoMap() && null != this.getSeqNoMap()
                && null != this.getAnswerMap() && null != this.getDataHiddenValReferenceCode() 
                			&& null != this.getDatahiddenReferenceCodeText()) {
            // get the key set from the map
            Set seqNoKeySet = this.getSeqNoMap().keySet();
            Set quesNoKeySet = this.getQuesNoMap().keySet();
            Set answerKeySet = this.getAnswerMap().keySet();
            Set referenceCodeSet = this.getDataHiddenValReferenceCode().keySet();
            Set referencTextSet = this.getDataHiddenValReference().keySet();
            
            Set referenceTextCodeSet = this.getDatahiddenReferenceCodeText().keySet();
            Set referenceTextDescSet = this.getDatahiddenReferenceText().keySet();

            // iterate the key set
            Iterator seqNoIterator = seqNoKeySet.iterator();
            Iterator quesNoIterator = quesNoKeySet.iterator();
            Iterator answerIterator = answerKeySet.iterator();
            Iterator referenceCodeIterator = referenceCodeSet.iterator();
            Iterator referenceTextIterator = referenceTextCodeSet.iterator();
            boolean isSeqChanged = false;
            
            Object quesNoKey = null;
            // iterate the seqNoKeySet
            while (seqNoIterator.hasNext()) {
                
                // get the seqNo Key
                Object seqNoKey = seqNoIterator.next();
                // Create the question Object
                Question updatedQuestion = new Question();
                // set the seqNo in the Question object
                String seqNo = this.getSeqNoMap().get(seqNoKey).toString();
                String questionNo = updatedQuestion.getQuestionNumber();
                if(null != seqNoKey && seqNo.matches("[\\d]+")){ 
                    updatedQuestion.setSeqNumber(Integer.parseInt(seqNo));
    				
    			}else{
    			    ErrorMessage errorMessage = new ErrorMessage("enter.integers.for.sequence.number");
    			    this.messages = new ArrayList(1);
            	    this.messages.add(errorMessage);
                    return "";
    			    //updatedQuestion.setSeqNumber(1);
    			}
                
//              change for performace
                Object oldSeqValue = hiddenSequenceMap.get(new Long(seqNoKey
                        .toString()));
                if((isSeqChanged)||null != oldSeqValue && null != seqNo && !(seqNo.equals(oldSeqValue.toString()))){
                    updatedQuestion.setModified(true);
                	isSeqChanged = true;
                }
                
                //end
                
                
                // iterate the questionNoKeySet
                while (quesNoIterator.hasNext()) {
                    // get the quesNoKey
                    quesNoKey = quesNoIterator.next();
//                    // compare the key with the seqNoKey
//                    if (seqNoKey.equals(quesNoKey)) {
                        // set the quesNo in the Question object
                    updatedQuestion.setQuestionNumber(this.getQuesNoMap()
                            .get(quesNoKey).toString());
                        break;
                    //}
                }
                while (answerIterator.hasNext()) {
                    // get the answerKey
                    Object answerKey = answerIterator.next();
                    // compare the answer key with the seqNoKey
                    if (quesNoKey.equals(answerKey)) {
                        // set the answer in the Question object
                        //updatedQuestion.setAnswer(this.getAnswerMap().get(answerKey).toString());
                        if (null != this.getAnswerMap().get(answerKey)
                                .toString()
                                && !"".equals(this.getAnswerMap()
                                        .get(answerKey).toString())) {
                            updatedQuestion.setAnswerId(new Integer(this
                                    .getAnswerMap().get(answerKey).toString())
                                    .intValue());
                            //change for performace
                            Object oldAnsValue = hiddenAnswerMap.get(new Long(seqNoKey
                                    .toString()));
                            if((isSeqChanged)|| (null != oldAnsValue && null != this.getAnswerMap().get(answerKey)
                                    .toString() && !(this.getAnswerMap().get(answerKey)
                                            .toString().equals(oldAnsValue.toString())))){
                                updatedQuestion.setModified(true);
                            }
                            //end
                        }
                        break;
                    }
                }
                
                while (referenceCodeIterator.hasNext()) {
                    // get the answerKey
                    Object referenceKey = referenceCodeIterator.next();
                    int ansNo = updatedQuestion.getAnswerId();
                    // compare the answer key with the seqNoKey
                   
                        if (quesNoKey.equals(referenceKey)){
                            // set the answer in the Question object
                            //updatedQuestion.setAnswer(this.getAnswerMap().get(answerKey).toString());
                            if (null != this.getDataHiddenValReferenceCode().get(referenceKey)
                                    .toString()){
                                   // && !"".equals(this.getDataHiddenValReferenceCode()
                                           // .get(referenceKey).toString())) {
                                String reference = this.getDataHiddenValReferenceCode()
                                						.get(referenceKey).toString();
                                List referenceList = WPDStringUtil
                                		.getListFromTildaString(reference, 2, 2, 2);
                                
                                // change for performace
	                            Object oldReferenceValue = hiddenReferenceMap.get(new Long(seqNoKey
	                                    .toString()));
	                            
	                            
	                            if("".equals(reference)){
                                    if(!reference.equals(oldReferenceValue.toString())){
                                        updatedQuestion.setModified(true);
                                    }
                                }else if("null~null".equals(reference) || "null".equals(reference)){
                                    if(! oldReferenceValue.equals("null")){
                                        updatedQuestion.setModified(true);
                                    }
                                }else if(null != referenceList && referenceList.size() > 0){
	                                if(!( ((String) referenceList
                                            .get(0)).trim()
                                            .equals(oldReferenceValue.toString()))){
	                                    updatedQuestion.setModified(true);
	                                }
	                            }
	                            
	                            //end
    		                    if (null != referenceList && referenceList.size() > 0
    		                            && null != referenceList.get(0)){
    		                        if(!"null".equals(referenceList.get(0).toString().trim())){
    		                            updatedQuestion
    		                                    .setReferenceId(((String) referenceList
    		                                            .get(0)).trim());
    		                            
    		                            break;
    		                        }
    		                    }
                            }
                           
                        }
                    
                }
                referenceCodeIterator = referenceCodeSet.iterator();
                while (referenceTextIterator.hasNext()) {
                    // get the answerKey
                    Object referenceKey = referenceTextIterator.next();
                    // compare the answer key with the seqNoKey
                    questionNo = updatedQuestion.getQuestionNumber();
                    
                    if(null != questionNo){
                        if (quesNoKey.equals(referenceKey)) {
                            // set the answer in the Question object
                            //updatedQuestion.setAnswer(this.getAnswerMap().get(answerKey).toString());
                            if (null != this.getDatahiddenReferenceCodeText().get(referenceKey)
                                    .toString()
                                    && !"".equals(this.getDatahiddenReferenceCodeText()
                                            .get(referenceKey).toString())) {
                                String reference = this.getDatahiddenReferenceCodeText()
                                						.get(referenceKey).toString();
                                List referenceList = WPDStringUtil
                                		.getListFromTildaString(reference, 2, 2, 2);
    		                    if (null != referenceList && referenceList.size() > 0
    		                            && null != referenceList.get(0)){
    		                        if(!"null".equals(referenceList.get(0).toString().trim())){
    		                            updatedQuestion
    		                                    .setReferenceId(((String) referenceList
    		                                            .get(0)).trim());
    		                            //change for performace
    		                            Object oldReferenceValue = hiddenReferenceMap.get(new Long(seqNoKey
    		                                    .toString()));
    		                            if(null != oldReferenceValue){
    		                                if( ("".equals(oldReferenceValue) && ((String) referenceList
	                                            .get(0)).trim().equals(null)) || !( ((String) referenceList
	    	                                            .get(0)).trim()
    		                                            .equals(oldReferenceValue.toString()))){
        		                                updatedQuestion.setModified(true);
    		                                }
    		                            }
    		                            //end
    		                            break;
    		                        }
    		                    }
                            }
                           
                        }
                    }
                }
                referenceTextIterator = referenceTextCodeSet.iterator();
                // set the adminOptionId in teh Question object
                updatedQuestion.setAdminOptionSysId(new Integer(getSession()
                        .getAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN)
                        .toString()).intValue());
                // add the updatedQuestion to the list
                updatedQuestionList.add(updatedQuestion);
            }
			 //modified for solving performance issue on 13th Dec 2007	             
            List newList = new ArrayList();
            SaveQuestionResponse updateQuestionResponse = new SaveQuestionResponse();
            if(isSeqChanged){
                Iterator iterator = updatedQuestionList.iterator();
                //updatedQuestionList.clear();
                while(iterator.hasNext()){
                    Question updatedQuestion = (Question) iterator.next();
                    updatedQuestion.setModified(true);  
                    newList.add(updatedQuestion);
                }
                updateQuestionResponse = getUpdatedSeqResponse(newList);
            }else{
                updateQuestionResponse = getUpdatedSeqResponse(updatedQuestionList);
            }
            //modification ends
            // get the message
            if (null != updateQuestionResponse)
                this.messages = updateQuestionResponse.getMessages();
        }
        getRequest().setAttribute("RETAIN_Value", "");
        return "ADMINPANEL";
    }


    /**
     * @param updatedQuestionList
     * @return
     */
    private SaveQuestionResponse getUpdatedSeqResponse(List updatedQuestionList) {
        // reorder the updatedQuestionList
        SequenceUtil sequenceUtil = new SequenceUtil();
        List reOrderedQuestionList = sequenceUtil
                .reOrderObjects(updatedQuestionList);
        /* connect to the database and update the details of questions */
        // create the request
        SaveQuestionRequest updateQuestionRequest = (SaveQuestionRequest) this
                .getServiceRequest(ServiceManager.SAVE_UPDATE_QUESTION_REQUEST);
        // set the updatedQuestionList to the request
        updateQuestionRequest.setQuestion(reOrderedQuestionList);
        // set the action to the request to find whether it is update or insert
        // 1 --> Insert
        // 2 --> Update
        updateQuestionRequest.setAction(2);
        // set the version, key and identifier
        updateQuestionRequest
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        updateQuestionRequest
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        updateQuestionRequest.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        updateQuestionRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        updateQuestionRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        updateQuestionRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        // get the response
        SaveQuestionResponse updateQuestionResponse = (SaveQuestionResponse) executeService(updateQuestionRequest);
        return updateQuestionResponse;
    }


    /**
     * @return Returns the selectedRow.
     */
    public String getSelectedRow() {
        return selectedRow;
    }


    /**
     * @param selectedRow
     *            The selectedRow to set.
     */
    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }


    /**
     * @return Returns the adminOptionSysId.
     */
    public int getAdminOptionSysId() {
        return adminOptionSysId;
    }


    /**
     * @param adminOptionSysId
     *            The adminOptionSysId to set.
     */
    public void setAdminOptionSysId(int adminOptionSysId) {
        this.adminOptionSysId = adminOptionSysId;
    }


    /**
     * 
     * Constructor to set the breadCrumb text.
     */
    public AddQuestionBackingBean() {
        super();
        // set the bread crumb text
        /*
         * this.setBreadCrumbText("Product Configuration >> Standard Benefit(" +
         * getStandardBenefitSessionObject().getStandardBenefitName() + ") >>
         * Edit");
         */

        if (null != this.getStandardBenefitSessionObject()
                .getStandardBenefitMode()
                && (WebConstants.BENEFIT_VIEW).equals(this
                        .getStandardBenefitSessionObject()
                        .getStandardBenefitMode())) {
            this.setBreadCrumbText(WebConstants.BENEFIT_LEVEL_BREADCRUMB
                    + getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> View");
        } else
            this.setBreadCrumbText(WebConstants.BENEFIT_LEVEL_BREADCRUMB
                    + getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> Edit");
    }


    /**
     * @return Saves the selected questions.
     */
    public String saveSelectedQuestions() {
        String[] questionArray = selectedQuestions.split("~");

        List questionList = new ArrayList();
        for (int i = 0; i < questionArray.length; i += 4) {
            questionList.add(questionArray[i]);
        }

        List questionNumberList = new ArrayList();
        for (int i = 1; i < questionArray.length; i += 4) {
            questionNumberList.add(questionArray[i]);
        }

        List sequenceNumberList = new ArrayList();
        for (int i = 2; i < questionArray.length; i += 4) {
            sequenceNumberList.add(questionArray[i]);
        }
        List answerIdList = new ArrayList();
        for (int i = 3; i < questionArray.length; i += 4) {
            answerIdList.add(questionArray[i]);
        }

        SaveQuestionRequest saveQuestionRequest = (SaveQuestionRequest) this
                .getServiceRequest(ServiceManager.SAVE_UPDATE_QUESTION_REQUEST);
        
        List questionObjectList = new ArrayList();
        Iterator iter = questionNumberList.iterator();
        Iterator iter1 = questionList.iterator();
        Iterator iterSeq = sequenceNumberList.iterator();
        Iterator iterAnswerId = answerIdList.iterator();
        while (iter.hasNext()) {
            Question question = new Question();
            // TODO
            question.setAdminOptionSysId(new Integer(getSession().getAttribute(
                    WebConstants.SESSION_ADMIN_OPTN_ASSN).toString())
                    .intValue());
            question.setSystemId(new Integer(getSession().getAttribute(
                    WebConstants.SESSION_ADMIN_OPTN_ID).toString()).intValue());
            //question.setAdminOptionSysId(111);
            question.setQuestionNumber((String) iter.next());
            question.setQuestion((String) iter1.next());
            question.setSeqNumber(Integer.parseInt(iterSeq.next().toString()));
            question.setAnswerId(Integer.parseInt(iterAnswerId.next().toString()));
            
            if(0 == question.getSeqNumber()){
                question.setReferenceId(null);
                question.setIsOpen("T");
            }else{
                
                question.setReferenceId("notOpen");
                question.setIsOpen("F");
            }
            questionObjectList.add(question);
        }
        saveQuestionRequest.setQuestion(questionObjectList);
        saveQuestionRequest.setAction(1);
        saveQuestionRequest
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        saveQuestionRequest
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        saveQuestionRequest.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        saveQuestionRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        saveQuestionRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        saveQuestionRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());

        SaveQuestionResponse saveQuestionResponse = (SaveQuestionResponse) this
                .executeService(saveQuestionRequest);
        return "ADMINPANEL";
    }


    /**
     * @return Returns the headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {

        HtmlPanelGrid headerPanel = new HtmlPanelGrid();
        headerPanel.setWidth("670");
        headerPanel.setColumns(5);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("1");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");

        HtmlOutputText htmlOutputText1 = new HtmlOutputText();
        HtmlCommandButton updateButton = new HtmlCommandButton();
        HtmlOutputText htmlOutputText2 = new HtmlOutputText();
        HtmlOutputText htmlOutputText3 = new HtmlOutputText();
        HtmlOutputText htmlOutputText4 = new HtmlOutputText();
//        HtmlCommandButton deleteQuestionButton = new HtmlCommandButton();
        HtmlOutputText deleteHeading = new HtmlOutputText();

        updateButton.setTitle("Update");
        updateButton.setId("updt");
        updateButton.setStyleClass("wpdbutton");
        updateButton.setValue("Update");
        updateButton.setTabindex("1");
        updateButton.setOnmousedown("javascript:savePageAction(this.id);");

        MethodBinding methodBinding = getMethodBinding("AddQuestionBackingBean", "updateSeqNo");
        updateButton.setAction(methodBinding);
        
        MethodBinding questionHideMethod = getMethodBinding("AddQuestionBackingBean","deleteQuestions");
//        deleteQuestionButton.setAction(questionHideMethod);
//        deleteQuestionButton.setId("questionDeleteButton");
//        deleteQuestionButton.setTitle("Delete");
//        deleteQuestionButton.setStyleClass("wpdbutton");
//        deleteQuestionButton.setValue("Delete");
//        deleteQuestionButton.setImage("../../images/delete.gif");
//        deleteQuestionButton.setStyle("border:0;");
//        updateButton.setTabindex("1");
        deleteHeading.setValue("Delete");
        
        htmlOutputText2.setValue("Question");
        htmlOutputText3.setValue("Answer");
        htmlOutputText4.setValue("Reference");
        headerPanel.setStyleClass("dataTableHeader");

        headerPanel.getChildren().add(updateButton);
        headerPanel.getChildren().add(htmlOutputText2);
        headerPanel.getChildren().add(htmlOutputText3);
        headerPanel.getChildren().add(htmlOutputText4);
//        headerPanel.getChildren().add(htmlOutputText1);
        headerPanel.getChildren().add(deleteHeading);

        return headerPanel;
    }

    private MethodBinding getMethodBinding(String backingBean, String method) {
    	return FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + backingBean + "." + method + "}", new Class[] {});
    }
    
    public String deleteQuestions() {
    	Set qnDeleteflagSet = this.deleteQuestionCheckBoxMap.keySet();
    	Iterator qnDeleteflagSetIterator =  qnDeleteflagSet.iterator();
    	List questionNumbers = new ArrayList();
    	while(qnDeleteflagSetIterator.hasNext()) {
    		Long qnNumber = (Long) qnDeleteflagSetIterator.next();
    		Boolean checkBoxValue = (Boolean)deleteQuestionCheckBoxMap.get(qnNumber);
    		// If checked
    		if (checkBoxValue.booleanValue() == true)  {
    			questionNumbers.add(qnNumber);
    		}
    	}
    	
    	if(!questionNumbers.isEmpty()) {
		    HideQuestionRequest request = (HideQuestionRequest) this.getServiceRequest(ServiceManager.HIDE_QUESTION_REQUEST);
		    // set the adminOptionSysId to the request
		    request.setAdminOptionSysId(new Integer(getSession().getAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue());
		    // set the questionNumbers to the request
		    request.setQuestionNumbers(questionNumbers);
		    // set the version, key and identifier
		    request.setStandardBenefitKey(getStandardBenefitSessionObject().getStandardBenefitKey());
		    request.setBenefitIdentifier(getStandardBenefitSessionObject().getStandardBenefitName());
		    request.setVersion(getStandardBenefitSessionObject().getStandardBenefitVersionNumber());
		    request.setBusinessDomains(getStandardBenefitSessionObject().getBusinessDomains());
		    request.setStandardBenefitParentKey(getStandardBenefitSessionObject().getStandardBenefitParentKey());
		    request.setStandardBenefitStatus(getStandardBenefitSessionObject().getStandardBenefitStatus());
		    // get the response
		    HideQuestionResponse response = (HideQuestionResponse) executeService(request);
		    // get the message
		    if (null != response) {
		        this.messages = response.getMessages();
		        loadPanel();
		        getRequest().setAttribute("RETAIN_Value", "");
		        return "ADMINPANEL";
		    }
    	}
		return "";    	
    }
	
    /**
     * Header Panel for View
     */
    public HtmlPanelGrid getHeaderPanelForView() {
        HtmlPanelGrid headerPanel = new HtmlPanelGrid();
        headerPanel.setWidth("100%");
        headerPanel.setColumns(5);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("1");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");

        HtmlOutputText htmlOutputText2 = new HtmlOutputText();
        HtmlOutputText htmlOutputText3 = new HtmlOutputText();
        HtmlOutputText htmlOutputText4 = new HtmlOutputText();
        HtmlOutputText htmlOutputText5 = new HtmlOutputText();

        htmlOutputText4.setValue("Sequence");
        htmlOutputText2.setValue("Question");
        htmlOutputText3.setValue("Answer");
        htmlOutputText5.setValue("Reference");
        headerPanel.setStyleClass("dataTableHeader");

        headerPanel.getChildren().add(htmlOutputText4);
        headerPanel.getChildren().add(htmlOutputText2);
        headerPanel.getChildren().add(htmlOutputText3);
        headerPanel.getChildren().add(htmlOutputText5);

        return headerPanel;
    }


    /**
     * Panel for View
     *  
     */

    /**
     * @param headerPanel
     *            The headerPanel to set.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }
    /**
     * 
     */
    private void clearMapBindings() {
       this.hiddenSequenceMap.clear();
       this.hiddenReferenceMap.clear();
       this.hiddenAnswerMap.clear();
    }


    private void loadPanel() {
    	
      
    }
    
    private ValueBinding getValueBinding(String backingBean, String mapName, long key) {
    	return FacesContext.getCurrentInstance().getApplication()
        	.createValueBinding("#{" + backingBean +"." + mapName + "[" + key + "]}");
    }

    /**
     * @return Returns the panel.
     */
    public HtmlPanelGrid getPanel() {
    	  PossibleAnswersRequest possibleAnswersRequest = (PossibleAnswersRequest) this
				.getServiceRequest(ServiceManager.POSSIBLE_ANSWERS_REQUEST);

		//HtmlPanelGrid panel = new HtmlPanelGrid();
		panel.getChildren().clear();
		panel.setWidth("670");
		panel.setColumns(5);
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setStyleClass("outputText");
		panel.setBgcolor("#cccccc");
		clearMapBindings();
		List list = getSelectedQuestionList();

		// register the list to the sequence util
		SequenceUtil sequenceUtil = new SequenceUtil();
		sequenceUtil.registerObjects(list, "questionNumber", "seqNumber");

		if (null == list) {
			return null;
		}
		Iterator iterator = list.iterator();
		int i = 0;
		int tabindexIter = 2;
		while (iterator.hasNext()) {
			i++;
			// declare the UI component
			HtmlInputText htmlInputTextForSeqNo = new HtmlInputText();
			HtmlOutputText htmlOutputTextForQuestionDesc = new HtmlOutputText();
			HtmlInputHidden htmlInputHiddenForQuestionNo = new HtmlInputHidden();
			HtmlSelectOneMenu seloneMenuForAnswer = new HtmlSelectOneMenu();
			//      HtmlCommandButton hideButton = new HtmlCommandButton();
			//modified to include input text field for reference
			HtmlInputHidden htmlTextForReferenceDesc = new HtmlInputHidden();
			HtmlInputText inputText3 = new HtmlInputText();
			HtmlInputHidden hiddenReferenceCode = new HtmlInputHidden();
			HtmlInputHidden hiddenReferenceCodeText = new HtmlInputHidden();
			HtmlInputHidden hiddenReferenceText = new HtmlInputHidden();
			HtmlGraphicImage selectImage = new HtmlGraphicImage();

			// get the question bean from the list and get the datas to be
			// displayed
			Question question = (Question) iterator.next();
			String seqNo = new Integer(question.getSeqNumber()).toString();
			String ques = question.getQuestion();
			String quesNo = question.getQuestionNumber();
			int ansNo = question.getAnswerId();
			String ans = new Integer(question.getAnswerId()).toString();
			String referenceDesc = question.getReferenceDesc();
			// set the corresponding values in the UI component
			// sequence no
			htmlInputTextForSeqNo.setValue(seqNo);
			htmlInputTextForSeqNo.setOnkeypress("isNum();");
			htmlInputTextForSeqNo.setSize(5);
			htmlInputTextForSeqNo.setMaxlength(7);
			htmlInputTextForSeqNo.setStyleClass("formInputFieldForSequenceNo");
			// set the value to the map
			ValueBinding seqNoValBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.seqNoMap[" + quesNo
									+ "]}");
			htmlInputTextForSeqNo.setValueBinding("value", seqNoValBind);
			String tabindex = (new Integer(tabindexIter)).toString();
			tabindexIter = tabindexIter + 1;
			htmlInputTextForSeqNo.setTabindex(tabindex);

			// changed for performance issue on 6th dec 2007
			HtmlInputHidden hiddenSequence = new HtmlInputHidden();
			hiddenSequence.setId("hiddenSequence" + quesNo);
			hiddenSequence.setValue("" + question.getSeqNumber());
			ValueBinding valForhiddenSequence = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{AddQuestionBackingBean.hiddenSequenceMap["
									+ quesNo + "]}");
			hiddenSequence.setValueBinding("value", valForhiddenSequence);
			//change ends

			inputText3.setStyleClass("formInputField");
			// **Change**
			//inputText3.setId("Reference" + i);
			//inputText3.setValue(benefitLevelVO.getReference());
			inputText3.setId("Reference" + i);
			inputText3.setValue(question.getReferenceDesc());
			// **End**
			inputText3.setStyle("width:140px;");
			//inputText3.setReadonly(true);

			//Enhancement
			htmlTextForReferenceDesc.setValue(referenceDesc);
			//      if(null != referenceDesc)
			//          htmlTextForReferenceDesc.setValue(referenceDesc);
			//      else
			//          htmlTextForReferenceDesc.setValue(referenceDesc);
			htmlTextForReferenceDesc.setId("QuestionReference" + i);

			ValueBinding hidValItemRef = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.dataHiddenValReference["
									+ quesNo + "]}");
			// **End**
			htmlTextForReferenceDesc.setValueBinding("value", hidValItemRef);

			// changed for performance issue on 6th dec 2007
			HtmlInputHidden hiddenReference = new HtmlInputHidden();
			hiddenReference.setId("hiddenReference" + quesNo);
			hiddenReference.setValue("" + question.getReferenceId());
			ValueBinding valForhiddenReference = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{AddQuestionBackingBean.hiddenReferenceMap["
									+ quesNo + "]}");
			hiddenReference.setValueBinding("value", valForhiddenReference);
			//change ends

			// **Change**

			hiddenReferenceCode.setId("TxtReferenceCode" + i);
			// **Change for including notes exist Start
			//hiddenReferenceCode.setValue(benefitLineVO.getReference()+"~"+benefitLineVO.getReferenceCode());
			hiddenReferenceCode.setValue(question.getReferenceDesc() + "~"
					+ question.getReferenceId());
			// **Change for including notes exist End
			ValueBinding hidValItemRefCode = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.dataHiddenValReferenceCode["
									+ quesNo + "]}");
			// **End**

			hiddenReferenceCode.setValueBinding("value", hidValItemRefCode);

			/*
			 * change starts - putting the reference code and text of
			 * outputtext- reference
			 */

			hiddenReferenceText.setValue(referenceDesc);
			hiddenReferenceText.setId("QuestionReference" + i);

			ValueBinding hiddenReferenceTextBinding = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{AddQuestionBackingBean.datahiddenReferenceText["
									+ quesNo + "]}");
			hiddenReferenceText.setValueBinding("value",
					hiddenReferenceTextBinding);

			hiddenReferenceCodeText.setId("TxtReferenceCode" + i);
			hiddenReferenceCodeText.setValue(question.getReferenceDesc() + "~"
					+ question.getReferenceId());
			ValueBinding hiddenReferenceCodeTextBinding = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{AddQuestionBackingBean.datahiddenReferenceCodeText["
									+ quesNo + "]}");
			hiddenReferenceCodeText.setValueBinding("value",
					hiddenReferenceCodeTextBinding);

			/*
			 * change ends
			 */

			HtmlOutputText dummytext = new HtmlOutputText();
			dummytext.setValue("   ");

			selectImage.setUrl("../../images/select.gif");
			selectImage.setStyle("cursor:hand;");
			selectImage.setId("selectImage" + i);
			// **Change**
			/*
			 * selectImage .setOnclick("ewpdModalWindow_ewpd(
			 * '../adminoptionspopups/selectOneReferencePopup.jsp',
			 * 'benefitLevelForm:Reference" + i +
			 * "','benefitLevelForm:TxtReferenceCode" + i + "',2,1)");
			 */
			//      selectImage
			//              .setOnclick("ewpdModalWindow_ewpd(
			// '../adminoptionspopups/selectOneReferencePopup.jsp',
			// 'benefitLevelForm:Reference"
			//                      + idCount
			//                      + "','benefitLevelForm:TxtReferenceCode"
			//                      + idCount + "',2,1)");
			//** Modified for refdata
			String entityType = "stdbenefit";
			String lookUpAction = "4";
			String parentCatalog = "reference";
			selectImage
					.setOnclick("ewpdModalWindow_ewpd( '../adminoptionspopups/selectOneReferencePopupFilterSearch.jsp'+getUrl()+'?lookUpAction="
							+ lookUpAction
							+ "&parentCatalog="
							+ parentCatalog
							+ "&entityId="
							+ this.standardBenefitIdForRefData
							+ "&entityType="
							+ entityType
							+ "', 'addQuestionForm:Reference"
							+ i
							+ "','addQuestionForm:TxtReferenceCode"
							+ i
							+ "',2,1)");
			//** Modification ends
			//      HtmlOutputLabel referenceLabel = new HtmlOutputLabel();
			//      referenceLabel.setFor("QuestionReference" + i);
			//      referenceLabel.getChildren().add(htmlOutputTextForReferenceDesc);
			// End - Enhancement

			HtmlOutputText htmlOutputTextForReferenceDesc = new HtmlOutputText();

			htmlOutputTextForReferenceDesc.setValue(referenceDesc);
			htmlOutputTextForReferenceDesc.setId("OutputTextQuestionReference"
					+ i);

			// question desc
			htmlOutputTextForQuestionDesc.setValue(ques);
			htmlOutputTextForQuestionDesc.setId("QuestionDesc" + i);

			// question number
			htmlInputHiddenForQuestionNo.setValue(quesNo);
			// set the value to the map
			ValueBinding queNoValBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.quesNoMap[" + quesNo
									+ "]}");
			htmlInputHiddenForQuestionNo.setValueBinding("value", queNoValBind);

			// combining question desc and number into the HtmlOutputLabel
			HtmlOutputLabel questionLabel = new HtmlOutputLabel();
			questionLabel.setFor("QuestionDesc" + i);
			questionLabel.getChildren().add(htmlOutputTextForQuestionDesc);
			questionLabel.getChildren().add(htmlInputHiddenForQuestionNo);

			// answers
			UISelectItems selectItems = new UISelectItems();
			// search the answer and the reference for the selected Question
			// Number from the possible answers table
			// and make it as a list
			List possibleAnswers = getPossibleAnswersForSelecteQuestion(quesNo,
					possibleAnswersRequest);

			selectItems.setValue(possibleAnswers);
			seloneMenuForAnswer.getChildren().add(selectItems);
			seloneMenuForAnswer.setValue(ans);
			UIComponent object;
			object = (HtmlSelectOneMenu) seloneMenuForAnswer;
			// set the value to the map
			ValueBinding answerValBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.answerMap[" + quesNo
									+ "]}");
			seloneMenuForAnswer.setValueBinding("value", answerValBind);
			tabindex = (new Integer(tabindexIter)).toString();
			seloneMenuForAnswer.setTabindex(tabindex);
			tabindexIter = tabindexIter + 1;

			// changed for performance issue on 6th dec 2007
			HtmlInputHidden hiddenAnswer = new HtmlInputHidden();
			hiddenAnswer.setId("hiddenAnswer" + quesNo);
			hiddenAnswer.setValue("" + question.getAnswerId());
			ValueBinding valForhiddenAnswer = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{AddQuestionBackingBean.hiddenAnswerMap["
									+ quesNo + "]}");
			hiddenAnswer.setValueBinding("value", valForhiddenAnswer);
			//change ends

			// Check box for hiding question, multiple questions at a time.
			HtmlSelectBooleanCheckbox deleteQnCheckBox = new HtmlSelectBooleanCheckbox();
			deleteQnCheckBox.setId("qnCheckbox" + i);
			deleteQnCheckBox.setOnclick("test(this)");
			ValueBinding deleteQnCheckBoxValueBinding = getValueBinding(
					"AddQuestionBackingBean", "deleteQuestionCheckBoxMap", Long
							.parseLong(quesNo));
			deleteQnCheckBox.setValueBinding("value",
					deleteQnCheckBoxValueBinding);
			// hide button
			//      hideButton.setImage("../../images/delete.gif");
			//      hideButton.setValue("HideButton");
			//      hideButton.setId("hideButton" + i);
			//      hideButton.setStyle("border:0;");
			//      hideButton.setAlt("Delete");
			//      // set the action to the hideButton
			//      MethodBinding hideMetBind = FacesContext.getCurrentInstance()
			//              .getApplication().createMethodBinding(
			//                      "#{AddQuestionBackingBean.hideSelectedQuesion}",
			//                      new Class[] {});
			//      hideButton.setAction(hideMetBind);
			//      hideButton.setOnclick("return getSelectedRow(" + quesNo + ")");

			HtmlOutputLabel lblReference = new HtmlOutputLabel();
			lblReference.setFor("lblSeq" + i);
			lblReference.setId("lblReference" + i);

			if ("T".equals(question.getIsOpen())) {
				lblReference.getChildren().add(inputText3);
				lblReference.getChildren().add(htmlTextForReferenceDesc);
				lblReference.getChildren().add(hiddenReferenceCode);
				lblReference.getChildren().add(hiddenReference);

				lblReference.getChildren().add(dummytext);
				lblReference.getChildren().add(selectImage);
			}

			else {
				lblReference.setFor("QuestionReference" + i);
				lblReference.getChildren().add(htmlOutputTextForReferenceDesc);
				lblReference.getChildren().add(hiddenReferenceText);
				lblReference.getChildren().add(hiddenReferenceCodeText);
			}
			lblReference.getChildren().add(hiddenAnswer);
			lblReference.getChildren().add(hiddenSequence);
			//lblReference.getChildren().add(dummytext);

			// add the UI components to the panel
			panel.getChildren().add(htmlInputTextForSeqNo);
			panel.getChildren().add(questionLabel);
			panel.getChildren().add(object);
			panel.getChildren().add(lblReference);
			panel.getChildren().add(deleteQnCheckBox);
		}
		setTabindexForAddQuestionButton(new Integer(tabindexIter).toString());
		return panel;

    }


    /**
	 * @param panel
	 *            The panel to set.
	 */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
	 * @return Returns the selectedQuestionList.
	 */
    public List getSelectedQuestionList() {
        /*
		 * connect to the database and get the list of mandates from the mandate
		 * table
		 */
        // create the request
        List selectedQuestionList = new ArrayList();
        LocateSelectedQuestionListRequest locateSelectedQuestionListRequest = (LocateSelectedQuestionListRequest) this
                .getServiceRequest(ServiceManager.SELECTED_QUESTION_LIST);
        // get the adminId and add in the request
        this.setAdminOptionSysId(new Integer(getSession().getAttribute(
                WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue());
        // Enhancement
        int adminOptionId = new Integer(getSession().getAttribute(
                WebConstants.SESSION_ADMIN_OPTN_ID).toString()).intValue();
        locateSelectedQuestionListRequest.setAdminOptionId(adminOptionId);
        // End - Enhancement
        locateSelectedQuestionListRequest.setAdminOptionSysId(this
                .getAdminOptionSysId());
        locateSelectedQuestionListRequest
                .setMaxSearchResultSize(Integer.MAX_VALUE);
        // get the response
        LocateSelectedQuestionListResponse locateSelectedQuestionListResponse = (LocateSelectedQuestionListResponse) executeService(locateSelectedQuestionListRequest);
        // get the list from response and copy it to the mandate list
        List selectedQuestionListFromResponse = locateSelectedQuestionListResponse
                .getSelectedQuestionList();
        // iterate the list and set the values of the SelectedQuestionListBO to
        // the Question

        Iterator selectedQuestionListIterator = selectedQuestionListFromResponse
                .iterator();
        while (selectedQuestionListIterator.hasNext()) {
            // get the selectedQuestionListBO from the list
            SelectedQuestionListBO selectedQuestionListBO = (SelectedQuestionListBO) selectedQuestionListIterator
                    .next();
            // create the Question Object
            Question selectedQuestion = new Question();
            // get the values from the selectedQuestionListBO and set it in the
            // Question
            selectedQuestion.setAnswerId(selectedQuestionListBO.getAnswerId());
            selectedQuestion.setQuestion(selectedQuestionListBO
                    .getQuestionDesc());
            selectedQuestion.setSeqNumber(selectedQuestionListBO
                    .getSequenceNumber());
            selectedQuestion.setQuestionNumber(new Integer(
                    selectedQuestionListBO.getQuestionNumber()).toString());
            selectedQuestion.setAdminOptionSysId(selectedQuestionListBO
                    .getAdminOptionsSysId());
            // Enhancement
            selectedQuestion.setReferenceDesc(selectedQuestionListBO
                    .getReferenceDesc());
            selectedQuestion.setReferenceId(selectedQuestionListBO
                    .getReferenceId());
            selectedQuestion.setIsOpen(selectedQuestionListBO.getIsOpen());
            // End - Enhancement
            // add the question object to the selectedQuestionList
            selectedQuestionList.add(selectedQuestion);
        }
        // return the list
        return selectedQuestionList;
    }


    /**
     * @return Returns the sessionScope.
     */
    public Map getSessionScope() {
        return sessionScope;
    }


    /**
     * @param sessionScope
     *            The sessionScope to set.
     */
    public void setSessionScope(Map sessionScope) {
        this.sessionScope = sessionScope;
    }


    public String updateQuestion() {
        return "";
    }


    /**
     * Method to hide the selected Question
     *  
     */
    /*
     * public String hideQuestion() {
     * 
     * if (selectedQuestions.length() == 0) { return "selectedQuestion"; }
     * 
     * String[] questionArray = selectedQuestions.split(" <br> ");
     * 
     * List questionList = new ArrayList(); for (int i = 0; i <
     * questionArray.length; i++) { questionList.add(questionArray[i]); }
     * 
     * String[] answerArray = selectedAnswers.split("~");
     * 
     * List answerList = new ArrayList(); for (int i = 1; i <
     * answerArray.length; i += 2) { answerList.add(answerArray[i]); }
     * 
     * for (int i = 0; i < questionArray.length; i++) { Question question1 = new
     * Question(); question1.setQuestion((String) questionList.get(i));
     * question1.setAnswer((String) answerList.get(i));
     * selectedQuestionList.add(question1);
     * setSelectedQuestionList(selectedQuestionList); }
     * 
     * List selectedQuestionList = getSelectedQuestionList();
     * 
     * List updatedQuestionList = new ArrayList(); Map checkBoxMap1 =
     * getQuesForHidMap();
     * 
     * int keys = checkBoxMap1.keySet().size(); Iterator iterator =
     * checkBoxMap1.keySet().iterator();
     * 
     * while (iterator.hasNext()) { Object key = iterator.next(); int count = 0;
     * if (!((Boolean) checkBoxMap1.get(key)).booleanValue()) {
     * updatedQuestionList.add(count, selectedQuestionList.get(Integer
     * .parseInt(key.toString()) - 1)); count++; } }
     * this.setSelectedQuestionList(updatedQuestionList);
     * this.getQuesForHidMap().clear();
     * 
     * return "selectedQuestion"; }
     */
    /**
     * @return Returns the quesForHidMap.
     */
    public Map getQuesForHidMap() {
        return quesForHidMap;
    }


    /**
     * @param quesForHidMap
     *            The quesForHidMap to set.
     */
    public void setQuesForHidMap(Map quesForHidMap) {
        this.quesForHidMap = quesForHidMap;
    }


    /**
     * @return Returns the viewHeaderPanel.
     */
    public HtmlPanelGrid getViewHeaderPanel() {

        HtmlPanelGrid viewHeaderPanel = new HtmlPanelGrid();
        viewHeaderPanel.setWidth("100%");
        viewHeaderPanel.setColumns(2);
        viewHeaderPanel.setBorder(0);
        viewHeaderPanel.setCellpadding("1");
        viewHeaderPanel.setCellspacing("1");
        viewHeaderPanel.setBgcolor("#ccccccc");

        HtmlOutputText htmlOutputText1 = new HtmlOutputText();
        HtmlOutputText htmlOutputText2 = new HtmlOutputText();

        htmlOutputText1.setValue(" Questions");
        htmlOutputText2.setValue("Answer");

        viewHeaderPanel.setStyleClass("dataTableHeader");

        viewHeaderPanel.getChildren().add(htmlOutputText1);
        viewHeaderPanel.getChildren().add(htmlOutputText2);

        return viewHeaderPanel;
    }


    /**
     * @param viewHeaderPanel
     *            The viewHeaderPanel to set.
     */
    public void setViewHeaderPanel(HtmlPanelGrid viewHeaderPanel) {
        this.viewHeaderPanel = viewHeaderPanel;
    }


    /**
     * @return Returns the viewPanel.
     */
    public HtmlPanelGrid getViewPanel() {

        HtmlPanelGrid viewPanel = new HtmlPanelGrid();
        viewPanel.setWidth("100%");
        viewPanel.setColumns(2);
        viewPanel.setBorder(0);
        viewPanel.setCellpadding("1");
        viewPanel.setCellspacing("1");
        viewPanel.setBgcolor("#ccccccc");
        List list = this.getQuestionViewList();
        Iterator iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;

            HtmlOutputText htmlOutputText1 = new HtmlOutputText();
            HtmlOutputText htmlOutputText2 = new HtmlOutputText();

            Question question = (Question) iterator.next();

            String ques = question.getQuestion();
            String ans = question.getAnswer();
            htmlOutputText1.setValue(ques);
            htmlOutputText2.setValue(ans);

            viewPanel.setStyleClass("outputText");

            viewPanel.getChildren().add(htmlOutputText1);
            viewPanel.getChildren().add(htmlOutputText2);

        }

        return viewPanel;
    }


    /**
     * @param viewPanel
     *            The viewPanel to set.
     */
    public void setViewPanel(HtmlPanelGrid viewPanel) {
        this.viewPanel = viewPanel;
    }


    /**
     * @return Returns the questionViewList.
     */
    public List getQuestionViewList() {

        List list = new ArrayList(3);
        for (int i = 1; i < 4; i++) {
            question = new Question();
            question.setQuestion("Question" + i);
            if (i == 2) {
                question.setAnswer("NO");
            } else {
                question.setAnswer("YES");
            }

            list.add(question);
        }
        return list;
    }


    /**
     * @param questionViewList
     *            The questionViewList to set.
     */
    public void setQuestionViewList(List questionViewList) {
        this.questionViewList = questionViewList;
    }


    /**
     * Method to save the selected Question
     *  
     */
    /*
     * public String addQuestion() {
     * 
     * if (selectedQuestions.length() == 0) { return "selectedQuestion"; }
     * 
     * String[] questionArray = selectedQuestions.split(" <br> ");
     * 
     * List questionList = new ArrayList(); for (int i = 0; i <
     * questionArray.length; i++) { questionList.add(questionArray[i]); }
     * 
     * String[] answerArray = selectedAnswers.split("~");
     * 
     * List answerList = new ArrayList(); for (int i = 1; i <
     * answerArray.length; i += 2) { answerList.add(answerArray[i]); }
     * 
     * if (questionArray == null) { return ""; } for (int i = 0; i <
     * questionArray.length; i++) { Question question1 = new Question();
     * question1.setQuestion((String) questionList.get(i));
     * question1.setAnswer((String) answerList.get(i));
     * selectedQuestionList.add(question1);
     * setSelectedQuestionList(selectedQuestionList); }
     * 
     * return "selectedQuestion"; }
     */
    /**
     * @return Returns the selectedAnswers.
     */
    public String getSelectedAnswers() {
        return selectedAnswers;
    }


    /**
     * @param selectedAnswers
     *            The selectedAnswers to set.
     */
    public void setSelectedAnswers(String selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
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
     * @return Returns the hiddenQuesList.
     */
    public List getHiddenQuesList() {
        return hiddenQuesList;
    }


    /**
     * @param hiddenQuesList
     *            The hiddenQuesList to set.
     */
    public void setHiddenQuesList(List hiddenQuesList) {
        this.hiddenQuesList = hiddenQuesList;
    }


    /**
     * @return Returns the openQuestionList.
     */
    public List getOpenQuestionList() {
        return openQuestionList;
    }


    /**
     * @param openQuestionList
     *            The openQuestionList to set.
     */
    public void setOpenQuestionList(List openQuestionList) {
        this.openQuestionList = openQuestionList;
    }


    /**
     * @return Returns the answerList.
     */
    public List getAnswerList() {
        answerList = new ArrayList(2);
        answerList.add(new SelectItem("YES", "YES"));
        answerList.add(new SelectItem("NO", "NO"));

        return answerList;
    }


    /**
     * @param answerList
     *            The answerList to set.
     */
    public void setAnswerList(List answerList) {
        this.answerList = answerList;
    }


    /**
     * @return Returns the hiddenQuestionList.
     */
    public List getHiddenQuestionList() {
        RetrieveOpenQuestionRequest retrieveOpenQuestionRequest = (RetrieveOpenQuestionRequest) this
                .getServiceRequest("retrieveOpenQuestionRequest");
        QuestionLocateCriteria questionLocateCriteria = new QuestionLocateCriteria();
        questionLocateCriteria.setLocateAction(2);
        //TODO
        //questionLocateCriteria.setSystemId(92);
        //questionLocateCriteria.setAdminOptionsSysId(79);
        questionLocateCriteria.setSystemId(new Integer(getSession()
                .getAttribute(WebConstants.SESSION_ADMIN_OPTN_ID).toString())
                .intValue());
        questionLocateCriteria.setAdminOptionsSysId(new Integer(getSession()
                .getAttribute(WebConstants.SESSION_ADMIN_OPTN_ASSN).toString())
                .intValue());
        retrieveOpenQuestionRequest
                .setOpenQuestionLocateCriteria(questionLocateCriteria);
        RetrieveOpenQuestionResponse retrieveOpenQuestionResponse = (RetrieveOpenQuestionResponse) this
                .executeService(retrieveOpenQuestionRequest);
        hiddenQuestionList = retrieveOpenQuestionResponse.getOpenQuestionList();
        hiddenQuestionListSize = new Integer(hiddenQuestionList.size())
                .toString();
        return hiddenQuestionList;
    }


    /**
     * @param hiddenQuestionList
     *            The hiddenQuestionList to set.
     */
    public void setHiddenQuestionList(List hiddenQuestionList) {
        this.hiddenQuestionList = hiddenQuestionList;
    }


    /**
     * @return Returns the answerMap.
     */
    public HashMap getAnswerMap() {
        return answerMap;
    }


    /**
     * @param answerMap
     *            The answerMap to set.
     */
    public void setAnswerMap(HashMap answerMap) {
        this.answerMap = answerMap;
    }


    /**
     * @return Returns the quesNoMap.
     */
    public HashMap getQuesNoMap() {
        return quesNoMap;
    }


    /**
     * @param quesNoMap
     *            The quesNoMap to set.
     */
    public void setQuesNoMap(HashMap quesNoMap) {
        this.quesNoMap = quesNoMap;
    }


    /**
     * @return Returns the seqNoMap.
     */
    public HashMap getSeqNoMap() {
        return seqNoMap;
    }


    /**
     * @param seqNoMap
     *            The seqNoMap to set.
     */
    public void setSeqNoMap(HashMap seqNoMap) {
        this.seqNoMap = seqNoMap;
    }


    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }


    /**
     * @param headerPanelForPrint
     *            The headerPanelForPrint to set.
     */
    public void setHeaderPanelForPrint(HtmlPanelGrid headerPanelForPrint) {
        this.headerPanelForPrint = headerPanelForPrint;
    }


    /**
     * @param panelForPrint
     *            The panelForPrint to set.
     */
    public void setPanelForPrint(HtmlPanelGrid panelForPrint) {
        this.panelForPrint = panelForPrint;
    }


    /**
     * @return Returns the loadPurposeForprint.
     */
    /**
     * @param loadPurposeForprint
     *            The loadPurposeForprint to set.
     */
    public void setLoadPurposeForprint(String loadPurposeForprint) {
        this.loadPurposeForprint = loadPurposeForprint;
    }


    public HtmlPanelGrid getPanelForPrint() {
        return panel;
    }


    /**
     * @param headerPanelForView
     *            The headerPanelForView to set.
     */
    public void setHeaderPanelForView(HtmlPanelGrid headerPanelForView) {
        this.headerPanelForView = headerPanelForView;
    }


    /**
     * @param panelForView
     *            The panelForView to set.
     */
    public void setPanelForView(HtmlPanelGrid panelForView) {
        this.panelForView = panelForView;
    }


    /**
     * @return Returns the panelForView.
     */
    public HtmlPanelGrid getPanelForView() {
        PossibleAnswersRequest possibleAnswersRequest = (PossibleAnswersRequest) this
                .getServiceRequest(ServiceManager.POSSIBLE_ANSWERS_REQUEST);

        HtmlPanelGrid panel = new HtmlPanelGrid();
        panel.getChildren().clear();
        panel.setWidth("100%");
        panel.setColumns(4);
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setStyleClass("outputText");
        panel.setBgcolor("#cccccc");

        List list = getSelectedQuestionList();

        // register the list to the sequence util
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(list, "questionNumber", "seqNumber");

        if (null == list) {
            return panel;
        }
        Iterator iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            // declare the UI component
            HtmlOutputText htmlOutputTextForSeqNo = new HtmlOutputText();
            HtmlOutputText htmlOutputTextForQuestionDesc = new HtmlOutputText();
            HtmlOutputText htmlOutputTextForAnswer = new HtmlOutputText();
            HtmlOutputText htmlOutputTextForReference = new HtmlOutputText();
            SelectItem selectItem;

            // get the question bean from the list and get the datas to be
            // displayed
            Question question = (Question) iterator.next();
            String seqNo = new Integer(question.getSeqNumber()).toString();
            String ques = question.getQuestion();
            String quesNo = question.getQuestionNumber();
            String reference = question.getReferenceDesc();
            String ans = new Integer(question.getAnswerId()).toString();
            String ansDesc = "";

            htmlOutputTextForSeqNo.setValue(seqNo);
            htmlOutputTextForSeqNo.setId("Sequence" + i);

            htmlOutputTextForQuestionDesc.setValue(ques);
            htmlOutputTextForQuestionDesc.setId("QuestionDesc" + i);

            htmlOutputTextForReference.setValue(reference);
            htmlOutputTextForReference.setId("ReferenceDesc" + i);
            
            List possibleAnswers = getPossibleAnswersForSelecteQuestion(quesNo,
                    possibleAnswersRequest);
            Iterator iter = possibleAnswers.iterator();
            while (iter.hasNext()) {
                selectItem = (SelectItem) iter.next();
                String id = (String) selectItem.getValue();
                if (id.equals(ans))
                    ansDesc = selectItem.getLabel();
            }
            htmlOutputTextForAnswer.setValue(ansDesc);
            htmlOutputTextForAnswer.setId("Answer" + i);

            panel.getChildren().add(htmlOutputTextForSeqNo);
            panel.getChildren().add(htmlOutputTextForQuestionDesc);
            panel.getChildren().add(htmlOutputTextForAnswer);
            panel.getChildren().add(htmlOutputTextForReference);
        }

        return panel;
    }


    /**
     * Returns the hiddenQuestionListSize
     * 
     * @return String hiddenQuestionListSize.
     */
    public String getHiddenQuestionListSize() {
        return hiddenQuestionListSize;
    }


    /**
     * Sets the hiddenQuestionListSize
     * 
     * @param hiddenQuestionListSize.
     */
    public void setHiddenQuestionListSize(String hiddenQuestionListSize) {
        this.hiddenQuestionListSize = hiddenQuestionListSize;
    }


    /**
     * @return Returns the locateResultList.
     */
    public List getLocateResultList() {
        return locateResultList;
    }


    public String getLoadPurposeForprint() {

        PossibleAnswersRequest possibleAnswersRequest = (PossibleAnswersRequest) this
                .getServiceRequest(ServiceManager.POSSIBLE_ANSWERS_REQUEST);
        SelectItem selectItem;
        String ansDesc = "";
        List selectedQuestionList = new ArrayList();
        LocateSelectedQuestionListRequest locateSelectedQuestionListRequest = (LocateSelectedQuestionListRequest) this
                .getServiceRequest(ServiceManager.SELECTED_QUESTION_LIST);
        // 	get the adminId and add in the request
        this.setAdminOptionSysId(new Integer(getSession().getAttribute(
                WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue());
        locateSelectedQuestionListRequest.setAdminOptionSysId(this
                .getAdminOptionSysId());
        locateSelectedQuestionListRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        // get the response
        LocateSelectedQuestionListResponse locateSelectedQuestionListResponse = (LocateSelectedQuestionListResponse) executeService(locateSelectedQuestionListRequest);
        // get the list from response and copy it to the mandate list
        List selectedQuestionListFromResponse = locateSelectedQuestionListResponse
                .getSelectedQuestionList();
        // iterate the list and set the values of the SelectedQuestionListBO to
        // the Question

        Iterator selectedQuestionListIterator = selectedQuestionListFromResponse
                .iterator();
        while (selectedQuestionListIterator.hasNext()) {
            // get the selectedQuestionListBO from the list
            SelectedQuestionListBO selectedQuestionListBO = (SelectedQuestionListBO) selectedQuestionListIterator
                    .next();
            // create the Question Object
            Question selectedQuestion = new Question();
            // get the values from the selectedQuestionListBO and set it in the
            // Question
            selectedQuestion.setAnswerId(selectedQuestionListBO.getAnswerId());
            selectedQuestion.setQuestion(selectedQuestionListBO
                    .getQuestionDesc());
            selectedQuestion.setSeqNumber(selectedQuestionListBO
                    .getSequenceNumber());
            selectedQuestion.setQuestionNumber(new Integer(
                    selectedQuestionListBO.getQuestionNumber()).toString());
            selectedQuestion.setReferenceDesc(selectedQuestionListBO.getReferenceDesc());
            String ans = new Integer(selectedQuestionListBO.getAnswerId())
                    .toString();
            List possibleAnswers = getPossibleAnswersForPrintSelecteQuestion(
                    new Integer(selectedQuestionListBO.getQuestionNumber())
                            .toString(), possibleAnswersRequest);
            Iterator iter = possibleAnswers.iterator();
            while (iter.hasNext()) {
                AnswerBO answerBO = (AnswerBO) iter.next();
                String id = new Integer(answerBO.getAnswerId()).toString();
                if (id.equals(ans)) {
                    ansDesc = answerBO.getAnswerDesc();
                    selectedQuestion.setAnswer(ansDesc);
                }
            }
            // add the question object to the selectedQuestionList
            selectedQuestionList.add(selectedQuestion);
        }
        if(null != selectedQuestionList && selectedQuestionList.size()>0){
        this.setLocateResultList(selectedQuestionList);
        }else{
        	this.setLocateResultList(null);
        }
        return loadPurposeForprint;
    }


    /**
     * @param locateResultList
     *            The locateResultList to set.
     */
    public void setLocateResultList(List locateResultList) {
        this.locateResultList = locateResultList;
    }


    private List getPossibleAnswersForPrintSelecteQuestion(String questionNo,
            PossibleAnswersRequest possibleAnswersRequest) {
        List possibleAnswers = new ArrayList();
        // create the request
        possibleAnswersRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        // set the searchQuestionNumber in the request
        if (null != questionNo && !"".equals(questionNo)) {
            possibleAnswersRequest.setQuestionNumber(new Integer(questionNo)
                    .intValue());
        }
        // get the response
        PossibleAnswersResponse possibleAnswersResponse = (PossibleAnswersResponse) executeService(possibleAnswersRequest);
        // get the list of possibleAnswersBO from the response
        List possibleAnswersFromResponse = possibleAnswersResponse
                .getPossibleAnswers();
        // iterate the list and get the BO and generate the new SelectItem and
        // add to the list
        if (null != possibleAnswersFromResponse
                && !possibleAnswersFromResponse.isEmpty()) {
            for (int i = 0; i < possibleAnswersFromResponse.size(); i++) {
                // get the bo from the list
                AnswerBO answerBO = (AnswerBO) possibleAnswersFromResponse
                        .get(i);
                answerBO.setAnswerDesc(answerBO.getAnswerDesc());
                answerBO.setAnswerId(answerBO.getAnswerId());
                // add it to the possibleAnswersList
                possibleAnswers.add(answerBO);
            }
        }
        // return the list
        return possibleAnswers;
    }


    /**
     * @return Returns the tabindexForAddQuestionButton.
     */
    public String getTabindexForAddQuestionButton() {
        return tabindexForAddQuestionButton;
    }


    /**
     * @param tabindexForAddQuestionButton
     *            The tabindexForAddQuestionButton to set.
     */
    public void setTabindexForAddQuestionButton(
            String tabindexForAddQuestionButton) {
        this.tabindexForAddQuestionButton = tabindexForAddQuestionButton;
    }

	/**
	 * @return Returns the hiddenOpenQuestionListSize.
	 */
	public String getHiddenOpenQuestionListSize() {
		return hiddenOpenQuestionListSize;
	}
	/**
	 * @param hiddenOpenQuestionListSize The hiddenOpenQuestionListSize to set.
	 */
	public void setHiddenOpenQuestionListSize(String hiddenOpenQuestionListSize) {
		this.hiddenOpenQuestionListSize = hiddenOpenQuestionListSize;
	}
	/**
	 * @return Returns the deleteQuestionCheckBoxMap.
	 */
	public HashMap getDeleteQuestionCheckBoxMap() {
		return deleteQuestionCheckBoxMap;
	}
	/**
	 * @param deleteQuestionCheckBoxMap The deleteQuestionCheckBoxMap to set.
	 */
	public void setDeleteQuestionCheckBoxMap(HashMap deleteQuestionCheckBoxMap) {
		this.deleteQuestionCheckBoxMap = deleteQuestionCheckBoxMap;
	}
}