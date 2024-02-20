/*
 * 
 * AdminOptionQuestionsBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.adminoption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO;
import com.wellpoint.wpd.common.adminoption.request.CheckInAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AssociatedQuestionVO;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * AdminOptionQuestionsBackingBean contains associated questions of Admin Options.
 * 
 * This bean will bind with the jsp pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionQuestionsBackingBean extends WPDBackingBean {

    private int questionNumber = -1;

    private String adminName;

    private int adminId = -1;

    private String question;

    private int questionId = -1;

    private List associatedQuestionsList;

    private String state;

    private String status;
    
    private String breadCrumpText;

    private int version = -1;

    private List validationMessages = null;

    private String selectedsequences;

    private boolean requiredQuestion = false;

    private boolean checkInOpted = false;

    private String referenceDesc;

    private int referenceId;

    private int sequenceId;
    //added new field
    private String hiddenSequences;

    public AdminOptionQuestionsBackingBean(){
        
    }

    /**
     * @return hiddenSequences
     * 
     * Returns the hiddenSequences.
     */
    public String getHiddenSequences() {
        return hiddenSequences;
    }
    /**
     * @param hiddenSequences
     * 
     * Sets the hiddenSequences.
     */
    public void setHiddenSequences(String hiddenSequences) {
        this.hiddenSequences = hiddenSequences;
    }
    /**
     * Method to display Admin Question Tab
     * 
     * @return String returnValue
     */
    public String displayAdminQuestionTab() {
        String returnValue = this.retriveAdminOptionQuestion();
        setBreadCrumbText("Product Configuration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return returnValue;
    }
    
    /**
     * Method to update Admin Question sequence
     * 
     * @return String
     */
    public String updateAdminOptionQuestionSequence() {
        SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest = null;
        SaveAdminOptionQuestionResponse saveAdminOptionQuestionResponse = null;
        // Create the Request Object
        saveAdminOptionQuestionRequest = this
                .getUpdateAdminOptionQuestionRequest();
        // Call the service method
        saveAdminOptionQuestionResponse = (SaveAdminOptionQuestionResponse) this
                .executeService(saveAdminOptionQuestionRequest);
        if (null != saveAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information
            this.associatedQuestionsList = saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList();
            this.registerSequenceObjects(saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList());
            //modified to get the hiddensequence for solving performance issue on 18th Dec 2007
            if(null != saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList())
	            updateHiddenSequenceList(saveAdminOptionQuestionResponse
	                    .getAdminOptionVO().getAssociatedQuestionsList());
	         //modification ends           
        }
        this.question = "";
        setBreadCrumbText("Product Configuration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return "adminOptionQuestionPage";
    }


    /**
     * To get the values in the hiddenSequence whenever a change in the list comes modified on 18th Dec 2007
     */
    private void updateHiddenSequenceList(List hiddenSequenceList) {
        this.hiddenSequences = "";
        Iterator iterator = hiddenSequenceList.iterator();
        while(iterator.hasNext()){
            AssociatedQuestionBO associatedQuestionBO = (AssociatedQuestionBO)iterator.next();
            if(null != this.hiddenSequences && !("".equals(this.hiddenSequences)))
                this.hiddenSequences += "~" + associatedQuestionBO.getReferenceId() + "~" 
                				+ associatedQuestionBO.getAssociatedQuestionId() + "~" + associatedQuestionBO.getSeqNumber();
            else
                this.hiddenSequences = associatedQuestionBO.getReferenceId() + "~" 
                				+ associatedQuestionBO.getAssociatedQuestionId() + "~" + associatedQuestionBO.getSeqNumber();
        }
    }
    /**
     * Method to populate Admin Question request
     * 
     * @return SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest
     */
    private SaveAdminOptionQuestionRequest getUpdateAdminOptionQuestionRequest() {

        SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest = (SaveAdminOptionQuestionRequest) this
                .getServiceRequest(ServiceManager.ADD_ADMIN_OPTION_QUESTION_REQUEST);

        AssociatedQuestionVO associatedQuestionVO;
        List adminAssoQuestionList = new ArrayList();
        SequenceUtil sequenceUtil = new SequenceUtil();
        String[] sequenceArr = this.selectedsequences.split("~");
        //--to get the values in the hidden sequence modified on 18thDec 2007
        String[] hiddenSeqArray = this.hiddenSequences.split("~");
        saveAdminOptionQuestionRequest.setUpdateFlag(true);
        if (null != this.selectedsequences
                && !"".equals(this.selectedsequences)) {
            for (int i = 0; i < sequenceArr.length; i += 4) {
                associatedQuestionVO = new AssociatedQuestionVO();
                associatedQuestionVO.setAdminOptionId(this.adminId);
                if (!sequenceArr[i + 1].equals("null"))
                    associatedQuestionVO.setReferenceId(sequenceArr[i + 1]);
                associatedQuestionVO.setAssociatedQuestionId(Integer
                        .parseInt(sequenceArr[i + 2]));
                if((null != sequenceArr[i + 3]) && (sequenceArr[i + 3].matches("[\\d]+"))){ 
                    associatedQuestionVO.setSeqNumber(Integer
                            .parseInt(sequenceArr[i + 3]));
                }
                else{
                    associatedQuestionVO.setSeqNumber(1);
                }
                adminAssoQuestionList.add(associatedQuestionVO);
            }
        }
        
		//  --to split the values in the hidden sequence and compare the old values with the new ones modified on 18th Dec 2007
        List adminQuestionList = new ArrayList();
        boolean flag = false;
        List newList = new ArrayList();
        if(!hiddenSeqArray.equals("") && null != hiddenSeqArray){
    	    for (int j = 0; j < hiddenSeqArray.length; j += 4) {
    	        
    	        Iterator iterator = adminAssoQuestionList.iterator();
    	        while(iterator.hasNext()){
    	            AssociatedQuestionVO questionVO = (AssociatedQuestionVO)iterator.next();
    	            if(hiddenSeqArray[j+2].equals(new Integer(questionVO.getAssociatedQuestionId()).toString())){
    		            if(hiddenSeqArray[j+3].equals(new Integer(questionVO.getSeqNumber()).toString())){
    		                if(null != questionVO.getReferenceId()){
        		                if(!(hiddenSeqArray[j+1].equals(questionVO.getReferenceId()))){
        		                    questionVO.setModified(true);
        		                }else{
        		                    questionVO.setModified(false);
        		                }
        		                adminQuestionList.add(questionVO);
        		                break;
    		                }else{
    		                    if(!("null".equals(hiddenSeqArray[j+1]))){
        		                    questionVO.setModified(true);
        		                }else{
        		                    questionVO.setModified(false);
        		                }
    		                    adminQuestionList.add(questionVO);
        		                break;
    		                }
    		            }
    		            else{
    		                questionVO.setModified(true);
    		                flag = true;
    		                adminQuestionList.add(questionVO);
    		                break;
    		            }
    		            
    	            }
    	            
    	        }
    	    }
    	    if(flag){
                Iterator iterator = adminQuestionList.iterator();
                while(iterator.hasNext()){
                    AssociatedQuestionVO associatedQuestionVO2 = (AssociatedQuestionVO)iterator.next();
                    associatedQuestionVO2.setModified(true);
                    newList.add(associatedQuestionVO2);
                }
            }
        }
        
        saveAdminOptionQuestionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminId);
        saveAdminOptionQuestionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        saveAdminOptionQuestionRequest.getAdminOptionVO().setVersion(
                this.version);
        //modified on 18th Dec 2007 for getting the list by checking if the seq no has changed or not
        List orderedSequenceList = new ArrayList();
        if(newList.size() > 0){
            orderedSequenceList = sequenceUtil
            	.reOrderObjects(newList);
        }else if(adminQuestionList.size() > 0){
            orderedSequenceList = sequenceUtil
        		.reOrderObjects(adminQuestionList);
            
        }
         else{
             orderedSequenceList = sequenceUtil
             	.reOrderObjects(adminAssoQuestionList);
         }
         //modification ends

        saveAdminOptionQuestionRequest.getAdminOptionVO()
                .setAssociatedQuestionsList(orderedSequenceList);
        saveAdminOptionQuestionRequest.getAssociatedQuestionVO()
                .setAssociatedQuestionsList(orderedSequenceList);

        setBreadCrumbText("Product Configuration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return saveAdminOptionQuestionRequest;
    }


    /**
     * Method to add Admin Question
     * 
     * @return String
     */
    public String addAdminOptionQuestion() {

		//if (!validateField()) {
            //String targetPage = this.retriveAdminOptionQuestion();
           // addAllMessagesToRequest(validationMessages);
           // return targetPage;
       // }
        SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest = null;
        SaveAdminOptionQuestionResponse saveAdminOptionQuestionResponse = null;
        // Create the Request Object
        saveAdminOptionQuestionRequest = this
                .getSaveAdminOptionQuestionRequest();
        // Call the service method
        saveAdminOptionQuestionResponse = (SaveAdminOptionQuestionResponse) this
                .executeService(saveAdminOptionQuestionRequest);
        if (null != saveAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information
            this.question = "";
            this.associatedQuestionsList = saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList();
            this.registerSequenceObjects(saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList());
            if(null != this.getQuestion() || !"".equals(this.getQuestion())){
                this.setQuestion("");
            }
            if(this.getAssociatedQuestionsList().size() == 0){
                this.setAssociatedQuestionsList(null);
            }
            //modified to get the hiddensequence for solving performance issue on 18th Dec 2007
            if(null != saveAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList())
	            updateHiddenSequenceList(saveAdminOptionQuestionResponse
	                    .getAdminOptionVO().getAssociatedQuestionsList());
	         //modification ends           
        }
        setBreadCrumbText("Product Configuration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return "adminOptionQuestionPage";
    }


    /**
     * Method to populate Save AdminOption Question Request
     * 
     * @return SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest
     */
    private SaveAdminOptionQuestionRequest getSaveAdminOptionQuestionRequest() {

        SaveAdminOptionQuestionRequest saveAdminOptionQuestionRequest = (SaveAdminOptionQuestionRequest) this
                .getServiceRequest(ServiceManager.ADD_ADMIN_OPTION_QUESTION_REQUEST);

        saveAdminOptionQuestionRequest.setUpdateFlag(false);

        saveAdminOptionQuestionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminId);
        saveAdminOptionQuestionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        saveAdminOptionQuestionRequest.getAdminOptionVO().setVersion(
                this.version);
        saveAdminOptionQuestionRequest.getAssociatedQuestionVO()
                .setAdminOptionId(this.adminId);
        
        AssociatedQuestionVO  associatedQuestionVO;
        List list = new ArrayList();
        String adminOptionQnValue = this.getQuestion();
        String[] questionIdArr = adminOptionQnValue.split("~");
        
        for(int i = 0;i<questionIdArr.length; i=i+2){
            associatedQuestionVO = new AssociatedQuestionVO();
            associatedQuestionVO.setAdminOptionId(this.adminId);
            associatedQuestionVO.setReferenceId("");
            associatedQuestionVO.setSeqNumber(this.sequenceId);
            associatedQuestionVO.setAssociatedQuestionId(new Integer(questionIdArr[i+1]).intValue());
            list.add(associatedQuestionVO);
        }
        saveAdminOptionQuestionRequest.getAssociatedQuestionVO().setAssociatedQuestionsList(list);
        
        return saveAdminOptionQuestionRequest;
    }
     
    /**
     * Method to delete AdminOptionQuestion
     * 
     * @return SaveAdminOptionQuestionRequest
     */
    public String deleteAdminOptionQuestion() {
        DeleteAdminOptionQuestionRequest deleteAdminOptionQuestionRequest = null;
        DeleteAdminOptionQuestionResponse deleteAdminOptionQuestionResponse = null;
        // Create the Request Object
        deleteAdminOptionQuestionRequest = this
                .getDeleteAdminOptionQuestionRequest();
        // Call the service method
        deleteAdminOptionQuestionResponse = (DeleteAdminOptionQuestionResponse) this
                .executeService(deleteAdminOptionQuestionRequest);
        if (null != deleteAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information

            SequenceUtil sequenceUtil = new SequenceUtil();
            this.associatedQuestionsList = deleteAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList();
            this.associatedQuestionsList = sequenceUtil
                    .reOrderObjects(associatedQuestionsList);
            this.registerSequenceObjects(deleteAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList());
            //modified to get the hiddensequence for solving performance issue on 18th Dec 2007                    
            if(null != deleteAdminOptionQuestionResponse
                    .getAdminOptionVO().getAssociatedQuestionsList())
	            updateHiddenSequenceList(deleteAdminOptionQuestionResponse
	                    .getAdminOptionVO().getAssociatedQuestionsList());
	         // modification ends
        }
        this.question = "";
        setBreadCrumbText("Product Configuration >> Administration Option"
                + "(" + this.adminName + ") >> Edit");
        return "adminOptionQuestionPage";
    }


    /**
     * Method to populate Admin Option Question Request
     * 
     * @return DeleteAdminOptionQuestionRequest
     */
    private DeleteAdminOptionQuestionRequest getDeleteAdminOptionQuestionRequest() {
        DeleteAdminOptionQuestionRequest deleteAdminOptionQuestionRequest = (DeleteAdminOptionQuestionRequest) this
                .getServiceRequest(ServiceManager.DELETE_ADMIN_OPTION_QUESTION_REQUEST);
        deleteAdminOptionQuestionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminId);
        deleteAdminOptionQuestionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        deleteAdminOptionQuestionRequest.getAdminOptionVO().setVersion(
                this.version);
        deleteAdminOptionQuestionRequest.getAssociatedQuestionVO()
                .setAdminOptionId(this.adminId);
        deleteAdminOptionQuestionRequest.getAssociatedQuestionVO()
                .setAssociatedQuestionId(this.getQuestionId());
        return deleteAdminOptionQuestionRequest;
    }


    /**
     * Method to retrieve Admin Option Question
     * 
     * @return String
     */
    public String retriveAdminOptionQuestion() {
        RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest = null;
        RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = null;
        // Create the Request Object
        retrieveAdminOptionQuestionRequest = this
                .getRetrieveAdminOptionQuestionRequest();
        // Call the service method
        retrieveAdminOptionQuestionResponse = (RetrieveAdminOptionQuestionResponse) this
                .executeService(retrieveAdminOptionQuestionRequest);
        if (null != retrieveAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information
            this.associatedQuestionsList = retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList();
            this.registerSequenceObjects(retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList());
            //modified to get the hiddensequence for solving performance issue on 18th Dec 2007                    
            if(null != retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList()){
                updateHiddenSequenceList(retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList());
             //modification ends
            }
            if(null != this.getQuestion() || !"".equals(this.getQuestion())){
                this.setQuestion("");
            }
            if(null == this.associatedQuestionsList){
                if(this.getAssociatedQuestionsList().size() == 0){
                    this.setAssociatedQuestionsList(null);
                }
            }
            
        }
        return "adminOptionQuestionPage";
    }


    /**
     * Method to retrieve Admin Option Question
     * 
     * @return String
     */
    public String viewAdminOptionQuestion() {
        ArrayList validationMessages = new ArrayList();
        this.setAdminId(Integer.parseInt((String) this.getSession()
                .getAttribute("adminId")));
        this.setAdminName((String) this.getSession().getAttribute("adminname"));
        this.setVersion(Integer.parseInt((String) this.getSession()
                .getAttribute("version")));
        RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest = null;
        RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = null;
        // Create the Request Object
        retrieveAdminOptionQuestionRequest = this
                .getRetrieveAdminOptionQuestionRequest();
        AssociatedQuestionVO adminOptionVO = new AssociatedQuestionVO();
        adminOptionVO.setAdminOptionId(this.getAdminId());
        adminOptionVO.setAdminOptionName(this.getAdminName());
        adminOptionVO.setVersion(this.getVersion());
//        retrieveAdminOptionQuestionRequest
//                .setAssociatedQuestionVO(adminOptionVO);

        // Call the service method
        retrieveAdminOptionQuestionResponse = (RetrieveAdminOptionQuestionResponse) this
                .executeService(retrieveAdminOptionQuestionRequest);
        if (null != retrieveAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information
            this.associatedQuestionsList = retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList();
            if (this.associatedQuestionsList == null
                    || this.associatedQuestionsList.size() == 0) {
                validationMessages.add(new InformationalMessage(
                        "no.admin.option.questions.found.info"));
                addAllMessagesToRequest(validationMessages);
            }
        }
        setBreadCrumbText("Product Configuration >> Administration Option "
                + "(" + this.adminName + ") >> View");
        return "adminOptionQuestionViewPage";
    }


    /**
     * Method to Print Admin Option Question
     * 
     * @return String
     */
    public String getPrintAdminOptionQuestion() {
        ArrayList validationMessages = new ArrayList();
        this.setAdminId(Integer.parseInt((String) this.getSession()
                .getAttribute("adminId")));
        this.setAdminName((String) this.getSession().getAttribute("adminname"));
        this.setVersion(Integer.parseInt((String) this.getSession()
                .getAttribute("version")));
        this.setState((String) this.getSession().getAttribute("state"));
        this.setStatus((String) this.getSession().getAttribute("status"));
        RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest = null;
        RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = null;
        // Create the Request Object
        retrieveAdminOptionQuestionRequest = this
                .getRetrieveAdminOptionQuestionRequest();
        AssociatedQuestionVO adminOptionVO = new AssociatedQuestionVO();
        adminOptionVO.setAdminOptionId(this.getAdminId());
        adminOptionVO.setAdminOptionName(this.getAdminName());
        adminOptionVO.setVersion(this.getVersion());
//        retrieveAdminOptionQuestionRequest
//                .setAssociatedQuestionVO(adminOptionVO);

        // Call the service method
        retrieveAdminOptionQuestionResponse = (RetrieveAdminOptionQuestionResponse) this
                .executeService(retrieveAdminOptionQuestionRequest);
        if (null != retrieveAdminOptionQuestionResponse) {
            // Fill the backing bean attributes with updated information
            this.associatedQuestionsList = retrieveAdminOptionQuestionResponse
                    .getAssociatedQuestionList();
            if (this.associatedQuestionsList == null
                    || this.associatedQuestionsList.size() == 0) {
                validationMessages.add(new InformationalMessage(
                        "adminoption.question.not.exist.info"));
                addAllMessagesToRequest(validationMessages);
            }
        }
        return "adminOptionQuestionViewPage";
    }


    /**
     * Method to populate Admin Option Question Request
     * 
     * @return RetrieveAdminOptionQuestionRequest
     *         retrieveAdminOptionQuestionRequest
     */
    private RetrieveAdminOptionQuestionRequest getRetrieveAdminOptionQuestionRequest() {
        RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest = (RetrieveAdminOptionQuestionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_ADMIN_OPTION_QUESTION_REQUEST);
//        retrieveAdminOptionQuestionRequest.getAssociatedQuestionVO()
//                .setAdminOptionId(this.getAdminId());
//        retrieveAdminOptionQuestionRequest.getAssociatedQuestionVO()
//                .setAdminOptionName(this.getAdminName());
        return retrieveAdminOptionQuestionRequest;
    }


    /**
     * Method for sequence updation
     * 
     * @param associatedQuestionsList
     */
    private void registerSequenceObjects(List associatedQuestionsList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(associatedQuestionsList,
                "associatedQuestionId", "seqNumber");
    }


    /**
     * Method to validate Admin Question inputs
     * 
     * @return boolean
     */
//    private boolean validateField() {
//        validationMessages = new ArrayList();
//        boolean requiredField = false;
//        if (this.question == null || "".equals(this.question.trim())) {
//            requiredQuestion = true;
//            requiredField = true;
//        }
//        if (requiredField) {
//            validationMessages.add(new ErrorMessage(
//                    WebConstants.MANDATORY_FIELDS_REQUIRED));
//           return false;
//        }
//       return true;
//    }


    /**
     * Method to check In Admin Option Details.
     * 
     * @return String
     */
    public String checkInAdminOption() {
        updateAdminOptionQuestionSequence();
        CheckInAdminOptionRequest checkInAdminOptionRequest = null;
        CheckInAdminOptionResponse checkInAdminOptionResponse = null;

        //Creating request object
        checkInAdminOptionRequest = (CheckInAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_CHECKIN_REQUEST);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminId);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        checkInAdminOptionRequest.getAdminOptionVO().setVersion(this.version);
        checkInAdminOptionRequest.getAdminOptionVO().setStatus(this.status);
        checkInAdminOptionRequest.getAdminOptionVO().setStateValue(this.state);

        checkInAdminOptionRequest.setCheckInOpted(this.checkInOpted);

        //Creating the response object
        checkInAdminOptionResponse = (CheckInAdminOptionResponse) this
                .executeService(checkInAdminOptionRequest);
        if (null != checkInAdminOptionResponse) {

            this.validationMessages = checkInAdminOptionResponse.getMessages();
            this.version = checkInAdminOptionResponse.getAdminOptionVO()
                    .getVersion();
            this.status = checkInAdminOptionResponse.getAdminOptionVO()
                    .getStatus();

            if (null != checkInAdminOptionResponse.getAdminOptionVO()
                    .getStateObject()) {
                this.state = checkInAdminOptionResponse.getAdminOptionVO()
                        .getStateObject().getState();
            } else {
                this.state = checkInAdminOptionResponse.getAdminOptionVO()
                        .getStateValue();
            }

            if (!checkInAdminOptionResponse.isErrorFlag()) {
                SessionCleanUp
                        .removeManagedBean("createAdminOptionBackingBean");
                return "adminOptionCreatePage";
            }
            String returnString = retriveAdminOptionQuestion();
            this.question = WebConstants.EMPTY_STRING;
            addAllMessagesToRequest(validationMessages);
            setBreadCrumbText("Product Configuration >> Administration Option "
                    + "(" + this.adminName + ") >> Edit");
            return returnString;
        }
        return WebConstants.EMPTY_STRING;
    }


    /**
     * Returns the associatedQuestionsList
     * 
     * @return List associatedQuestionsList.
     */
    public List getAssociatedQuestionsList() {
        if (null != this.associatedQuestionsList
                && this.associatedQuestionsList.size() == 0)
            this.associatedQuestionsList = null;
        return associatedQuestionsList;
    }


    /**
     * Sets the associatedQuestionsList
     * 
     * @param associatedQuestionsList.
     */
    public void setAssociatedQuestionsList(List associatedQuestionsList) {
        this.associatedQuestionsList = associatedQuestionsList;
    }


    /**
     * Returns the question
     * 
     * @return String question.
     */
    public String getQuestion() {
        return question;
    }


    /**
     * Sets the question
     * 
     * @param question.
     */
    public void setQuestion(String question) {
        this.question = question;
    }


    /**
     * Returns the state
     * 
     * @return String state.
     */
    public String getState() {
        return state;
    }


    /**
     * Sets the state
     * 
     * @param state.
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * Returns the status
     * 
     * @return String status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status
     * 
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Returns the adminId
     * 
     * @return int adminId.
     */
    public int getAdminId() {
        return adminId;
    }


    /**
     * Sets the adminId
     * 
     * @param adminId.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }


    /**
     * Returns the adminName
     * 
     * @return String adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * Sets the adminName
     * 
     * @param adminName.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the version
     * 
     * @return int version.
     */
    public int getVersion() {
        if (version == -1) {
            return 0;
        }
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    public String saveQuestions() {
        return "";
    }


    public String deleteQuestion() {
        return "";
    }


    /**
     * Returns the validationMessages.
     * 
     * @return validationMessages
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * The validationMessages to set.
     * 
     * @param validationMessages
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the questionId
     * 
     * @return int questionId.
     */
    public int getQuestionId() {
        return questionId;
    }


    /**
     * Sets the questionId
     * 
     * @param questionId.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    /**
     * Returns the selectedsequences
     * 
     * @return String selectedsequences.
     */
    public String getSelectedsequences() {
        return selectedsequences;
    }


    /**
     * Sets the selectedsequences
     * 
     * @param selectedsequences.
     */
    public void setSelectedsequences(String selectedsequences) {
        this.selectedsequences = selectedsequences;
    }


    /**
     * Returns the questionNumber
     * 
     * @return int questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }


    /**
     * Sets the questionNumber
     * 
     * @param questionNumber.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    /**
     * Returns the requiredQuestion
     * 
     * @return boolean requiredQuestion.
     */
    public boolean isRequiredQuestion() {
        return requiredQuestion;
    }


    /**
     * Sets the requiredQuestion
     * 
     * @param requiredQuestion.
     */
    public void setRequiredQuestion(boolean requiredQuestion) {
        this.requiredQuestion = requiredQuestion;
    }


    /**
     * Returns the checkInOpted.
     * 
     * @return checkInOpted
     */
    public boolean isCheckInOpted() {
        return checkInOpted;
    }


    /**
     * The checkInOpted to set.
     * 
     * @param checkInOpted
     */
    public void setCheckInOpted(boolean checkInOpted) {
        this.checkInOpted = checkInOpted;
    }


    /**
     * Returns the referenceDesc
     * 
     * @return String referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }


    /**
     * Sets the referenceDesc
     * 
     * @param referenceDesc.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }


    /**
     * Returns the referenceId
     * 
     * @return int referenceId.
     */
    public int getReferenceId() {
        return referenceId;
    }


    /**
     * Sets the referenceId
     * 
     * @param referenceId.
     */
    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }


    /**
     * Returns the sequenceId
     * 
     * @return int sequenceId.
     */
    public int getSequenceId() {
        return sequenceId;
    }


    /**
     * Sets the sequenceId
     * 
     * @param sequenceId.
     */
    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getBreadCrumpText() {
		this.breadCrumpText = " Product Configuration >> Administration Option ("+ this.adminName+")>> Print";
		return breadCrumpText;
	}
	/**
	 * @param breadCrumpText The breadCrumpText to set.
	 */
	public void setBreadCrumpText(String breadCrumpText) {
		this.breadCrumpText = breadCrumpText;
	}
}